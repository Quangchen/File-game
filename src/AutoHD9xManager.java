public final class AutoHD9xManager {

    private static final int STATE_IDLE = 0;
    private static final int STATE_GATHER = 1;
    private static final int STATE_WAIT_LEADER = 2;
    private static final int STATE_WAIT_MEMBER = 3;
    private static final int STATE_RUNNING = 4;
    private static final long INVITE_DELAY = 4000L;
    private static final long STAGE_TIMEOUT = 15000L;
    private static final long GATHER_DELAY = 10000L;
    private static final int[] MEMBER_ORDER = new int[]{3, 4, 1, 2, 0};
    private static final int[] MEMBER_MAP = new int[]{159, 159, 158, 158, 157};

    private static boolean active = false;
    private static boolean leaderRound = false;
    private static boolean closed = false;
    private static String roundId = "";
    private static String currentLeader = "";
    private static int state = STATE_IDLE;
    private static long stateAt = 0L;
    private static long lastInviteAt = 0L;
    private static long lastPrepareAt = 0L;
    private static long lastReadyAt = 0L;
    private static long allMembersInZoneAt = 0L;
    private static int lastScheduleDayKey = -1;
    private static long lastScheduleCheckAt = 0L;
    private static final MyVector readyMembers = new MyVector();
    private static boolean leaderReached = false;
    private static int memberOrderIndex = 0;
    private static String waitingMember = "";
    private static int waitingMap = -1;
    private static boolean waitingMemberReached = false;

    private AutoHD9xManager() {
    }

    public static void update() {
        try {
            updateSchedule();

            if (!active || !leaderRound) {
                return;
            }

            if (state == STATE_GATHER) {
                updateGather();
            } else if (state == STATE_WAIT_LEADER) {
                if (leaderReached) {
                    startNextMember();
                } else if (System.currentTimeMillis() - stateAt >= STAGE_TIMEOUT) {
                    startRunner(FormHD9x.CheDo, 157);
                    stateAt = System.currentTimeMillis();
                }
            } else if (state == STATE_WAIT_MEMBER) {
                if (waitingMemberReached) {
                    memberOrderIndex++;
                    startNextMember();
                } else if (System.currentTimeMillis() - stateAt >= STAGE_TIMEOUT) {
                    startMember(MEMBER_ORDER[memberOrderIndex], MEMBER_MAP[memberOrderIndex]);
                    stateAt = System.currentTimeMillis();
                }
            }
        } catch (Exception e) {
        }
    }

    public static void startManual() {
        if (FormHD9x.isLeader()) {
            startLeaderRound();
        } else if (FormHD9x.CheDo == FormHD9x.MODE_BOSS) {
            active = true;
            leaderRound = false;
            closed = false;
            roundId = String.valueOf(System.currentTimeMillis());
            startRunner(FormHD9x.MODE_BOSS, 157);
        } else {
            GameScr.chatPopup("HD9x: acc farm chờ lệnh nhóm trưởng");
        }
    }

    public static boolean onPrivateMessage(String from, String text) {
        try {
            if (from == null || text == null || !text.startsWith("HD9X_")) {
                return false;
            }

            String[] part = Code.splitString(text, "|");
            String cmd = part[0];

            if (cmd.equals("HD9X_PREPARE") && part.length >= 2 && canAcceptPrepare(from)) {
                prepareMember(from, part[1]);
                return true;
            }

            if (cmd.equals("HD9X_START") && part.length >= 4 && canReceiveLeaderCommand(from)) {
                if (active && roundId.equals(part[1])) {
                    startRunner(Integer.parseInt(part[2]), Integer.parseInt(part[3]));
                }

                return true;
            }

            if (cmd.equals("HD9X_LEAVE") && part.length >= 2 && canReceiveLeaderCommand(from)) {
                if (active && roundId.equals(part[1])) {
                    startReward();
                }

                return true;
            }

            if (!FormHD9x.isLeader() || !leaderRound || !FormHD9x.isConfiguredMember(from) || part.length < 2 || !roundId.equals(part[1])) {
                return true;
            }

            if (cmd.equals("HD9X_READY")) {
                addUnique(readyMembers, from);
            } else if (cmd.equals("HD9X_REACHED") && part.length >= 3) {
                int map = Integer.parseInt(part[2]);

                if (sameName(from, waitingMember) && map == waitingMap) {
                    waitingMemberReached = true;
                }
            }

            return true;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean shouldAcceptPartyInvite(String from) {
        return active && !leaderRound && sameName(from, currentLeader);
    }

    public static boolean shouldUsePartyLeader(String leader) {
        return active && sameName(leader, currentLeader);
    }

    public static int getTravelMap(int currentMap, int targetMap) {
        return currentMap == 157 && targetMap == 158 ? 159 : targetMap;
    }

    public static void onLocalAtSchool() {
        if (!active) {
            return;
        }

        if (leaderRound) {
            return;
        }

        if (System.currentTimeMillis() - lastReadyAt < 3000L) {
            return;
        }

        lastReadyAt = System.currentTimeMillis();
        sendPrivate(currentLeader, "HD9X_READY|" + roundId);
    }

    public static void reportReachedTarget(int map) {
        if (!active) {
            return;
        }

        if (leaderRound) {
            if (map == 157) {
                leaderReached = true;
            }
        } else {
            sendPrivate(currentLeader, "HD9X_REACHED|" + roundId + "|" + map);
        }
    }

    public static void onInfoMessage(String text) {
        if (!active || text == null) {
            return;
        }

        if (text.equals("C\u1eeda hang \u0111\u1ed9ng \u0111\u00e3 \u0111\u01b0\u1ee3c kh\u00e9p l\u1ea1i.")) {
            closed = true;
            startReward();
        }
    }

    public static void onNoMoreEntries() {
        if (active) {
            closed = true;
            startReward();
        }
    }

    public static boolean handleRunnerLocation(boolean enteredHang) {
        if (!active) {
            return false;
        }

        if (closed || enteredHang && !isHD9xMap(TileMap.mapID)) {
            startReward();
            return true;
        }

        return false;
    }

    public static boolean isHD9xMap(int map) {
        return map == 157 || map == 158 || map == 159;
    }

    public static boolean isRoundActive() {
        return active;
    }

    public static void stopRound() {
        if (active) {
            reset();
        }
    }

    public static void startReward() {
        if (!active || Code.auto instanceof AutoHD9xReward) {
            return;
        }

        Code.setAuto(new AutoHD9xReward());
        Code.instance.a();
    }

    public static void finishReward() {
        try {
            if (leaderRound) {
                sendAll("HD9X_LEAVE|" + roundId);
            }

            if (GameScr.vParty.size() > 0) {
                Service.getInstance().t();
            }
        } catch (Exception e) {
        }

        reset();
        restorePreviousAuto();
    }

    private static void updateSchedule() {
        if (!FormHD9x.HenGio || !FormHD9x.isLeader() || Char.getMyChar() == null) {
            return;
        }

        long now = System.currentTimeMillis();

        if (now - lastScheduleCheckAt < 1000L) {
            return;
        }

        lastScheduleCheckAt = now;
        java.util.Calendar cur = Res.getCurrentTime();

        if (cur.get(11) != FormHD9x.Gio || cur.get(12) != FormHD9x.Phut) {
            return;
        }

        int dayKey = cur.get(1) * 1000 + cur.get(6);

        if (lastScheduleDayKey == dayKey || active) {
            return;
        }

        lastScheduleDayKey = dayKey;
        startLeaderRound();
    }

    private static void startLeaderRound() {
        reset();
        active = true;
        leaderRound = true;
        closed = false;
        roundId = String.valueOf(System.currentTimeMillis());
        currentLeader = Char.getMyChar() == null ? "" : Char.getMyChar().charName;
        state = STATE_GATHER;
        stateAt = System.currentTimeMillis();

        if (GameScr.vParty.size() > 0) {
            Service.getInstance().t();
        }

        sendPrepare();
        startGather();
        Code.instance.a();
        GameScr.chatPopup("HD9x: tập hợp nhóm " + FormHD9x.getModeName());
    }

    private static void prepareMember(String leader, String id) {
        if (active && !leaderRound && roundId.equals(id) && sameName(currentLeader, leader)) {
            if (TileMap.mapID == 1 && TileMap.zoneID == 21) {
                onLocalAtSchool();
            }

            return;
        }

        reset();
        active = true;
        leaderRound = false;
        closed = false;
        roundId = id;
        currentLeader = leader;
        state = STATE_GATHER;
        stateAt = System.currentTimeMillis();

        if (GameScr.vParty.size() > 0) {
            Service.getInstance().t();
        }

        startGather();
        Code.instance.a();
        GameScr.chatPopup("HD9x: về trường tập hợp");
    }

    private static void updateGather() {
        long now = System.currentTimeMillis();

        if (now - lastPrepareAt >= 10000L) {
            sendPrepare();
        }

        if (TileMap.mapID != 1 || TileMap.zoneID != 21) {
            allMembersInZoneAt = 0L;
            return;
        }

        if (now - lastInviteAt >= INVITE_DELAY) {
            lastInviteAt = now;

            for (int i = 0; i < FormHD9x.Acc.length; i++) {
                String name = FormHD9x.Acc[i];

                if (name != null && name.trim().length() > 0 && !isInParty(name)) {
                    Service.getInstance().addParty(name);
                }
            }
        }

        if (GameScr.vParty.size() < FormHD9x.countMembers() + 1 || !hasAllConfiguredPartyMembers()) {
            allMembersInZoneAt = 0L;
            return;
        }

        if (!areAllMembersInCurrentZone()) {
            allMembersInZoneAt = 0L;
            return;
        }

        if (allMembersInZoneAt == 0L) {
            allMembersInZoneAt = now;
            GameScr.chatPopup("HD9x: đủ acc trong khu, chờ 10 giây");
        }

        if (now - allMembersInZoneAt < GATHER_DELAY) {
            return;
        }

        startRunner(FormHD9x.CheDo, 157);
        state = STATE_WAIT_LEADER;
        stateAt = now;
    }

    private static void sendPrepare() {
        lastPrepareAt = System.currentTimeMillis();
        sendAll("HD9X_PREPARE|" + roundId);
    }

    private static void startNextMember() {
        while (memberOrderIndex < MEMBER_ORDER.length) {
            String name = FormHD9x.Acc[MEMBER_ORDER[memberOrderIndex]];

            if (name != null && name.trim().length() > 0) {
                break;
            }

            memberOrderIndex++;
        }

        if (memberOrderIndex >= MEMBER_ORDER.length) {
            waitingMember = "";
            waitingMap = -1;
            waitingMemberReached = false;
            state = STATE_RUNNING;
            stateAt = System.currentTimeMillis();
            GameScr.chatPopup("HD9x: đã đưa đủ acc vào hang");
            return;
        }

        int slot = MEMBER_ORDER[memberOrderIndex];
        waitingMember = FormHD9x.Acc[slot];
        waitingMap = MEMBER_MAP[memberOrderIndex];
        waitingMemberReached = false;
        startMember(slot, waitingMap);
        state = STATE_WAIT_MEMBER;
        stateAt = System.currentTimeMillis();
    }

    private static void startMember(int slot, int map) {
        if (slot < 0 || slot >= FormHD9x.Acc.length) {
            return;
        }

        String name = FormHD9x.Acc[slot];

        if (name != null && name.trim().length() > 0) {
            sendPrivate(name, "HD9X_START|" + roundId + "|" + FormHD9x.CheDo + "|" + map);
        }
    }

    private static void startRunner(int mode, int map) {
        if (mode == FormHD9x.MODE_FARM_CHEST) {
            if (Code.auto instanceof AutoHD9xChest && ((AutoHD9xChest) Code.auto).getTargetMap() == map) {
                if (TileMap.mapID == map) {
                    reportReachedTarget(map);
                }

                return;
            }

            Code.setAuto(new AutoHD9xChest(map));
        } else {
            if (Code.auto instanceof AutoHD9x && Code.auto.mapID == map) {
                if (TileMap.mapID == map) {
                    reportReachedTarget(map);
                }

                return;
            }

            Code.setAuto(new AutoHD9x(map));
        }

        Code.instance.a();
    }

    private static void startGather() {
        if (!(Code.auto instanceof AutoHD9xGather)) {
            Code.setAuto(new AutoHD9xGather());
        }
    }

    private static boolean canReceiveLeaderCommand(String from) {
        String leader = FormHD9x.getLeaderName();
        return sameName(from, currentLeader) || leader.length() > 0 && sameName(from, leader);
    }

    private static boolean canAcceptPrepare(String from) {
        if (from == null || FormHD9x.isLeader()) {
            return false;
        }

        String leader = FormHD9x.getLeaderName();
        return sameName(from, currentLeader) || leader.length() == 0 || sameName(from, leader);
    }

    private static boolean isInParty(String name) {
        if (name == null) {
            return false;
        }

        for (int i = 0; i < GameScr.vParty.size(); i++) {
            Party p = (Party) GameScr.vParty.elementAt(i);

            if (p != null && p.d != null && name.trim().equalsIgnoreCase(p.d)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasAllConfiguredPartyMembers() {
        for (int i = 0; i < FormHD9x.Acc.length; i++) {
            String name = FormHD9x.Acc[i];

            if (name != null && name.trim().length() > 0 && !isInParty(name)) {
                return false;
            }
        }

        return true;
    }

    private static boolean areAllMembersInCurrentZone() {
        for (int i = 0; i < FormHD9x.Acc.length; i++) {
            String name = FormHD9x.Acc[i];

            if (name != null && name.trim().length() > 0 && !isCharInCurrentZone(name)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isCharInCurrentZone(String name) {
        for (int i = 0; i < GameScr.vCharInMap.size(); i++) {
            Char member = (Char) GameScr.vCharInMap.elementAt(i);

            if (member != null && member.charName != null && name.trim().equalsIgnoreCase(member.charName)) {
                return true;
            }
        }

        return false;
    }

    private static void sendAll(String text) {
        for (int i = 0; i < FormHD9x.Acc.length; i++) {
            sendPrivate(FormHD9x.Acc[i], text);
        }
    }

    private static void sendPrivate(String name, String text) {
        if (name != null && name.trim().length() > 0) {
            Code.a(name.trim(), text);
        }
    }

    private static void addUnique(MyVector vector, String value) {
        if (!vector.contains(value)) {
            vector.addElement(value);
        }
    }

    private static boolean sameName(String first, String second) {
        return first != null && second != null && first.equalsIgnoreCase(second);
    }

    private static void restorePreviousAuto() {
        while (isHD9xAuto(Code.auto)) {
            Code.backToInstance();
        }
    }

    private static boolean isHD9xAuto(Auto auto) {
        return auto instanceof AutoHD9xReward || auto instanceof AutoHD9xGather
                || auto instanceof AutoHD9x || auto instanceof AutoHD9xChest;
    }

    private static void reset() {
        active = false;
        leaderRound = false;
        closed = false;
        roundId = "";
        currentLeader = "";
        state = STATE_IDLE;
        stateAt = 0L;
        lastInviteAt = 0L;
        lastPrepareAt = 0L;
        lastReadyAt = 0L;
        allMembersInZoneAt = 0L;
        readyMembers.removeAllElements();
        leaderReached = false;
        memberOrderIndex = 0;
        waitingMember = "";
        waitingMap = -1;
        waitingMemberReached = false;
    }
}
