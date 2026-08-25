
/**
 *
 * @author quang
 */
public final class AutoLDGT extends Auto {

    private static final int MAP_OUTSIDE = 1;
    private static final int MAP_WAIT = 80;
    private static final int MAP_BOSS = 90;
    private static final int MAP_LAST = 167;

    private static final int NPC_KANATA = 0;
    private static final int NPC_OPEN = 27;

    private static final int ITEM_BOSS = 261;
    private static final int ITEM_LENH_BAI_GT = 281;

    private static final int TIME_WAIT = 65;

    public static boolean clanCall = false;
    public static long clanCallAt = 0L;
    public static boolean clanOpen80 = false;
    public static long clanOpen80At = 0L;
    public static boolean clanKey87 = false;
    public static boolean clanKey88 = false;
    public static boolean clanKey89 = false;
    public static boolean clanFinish = false;
    public static String clanRoundId = "";
    public static String clanFinishedRoundId = "";
    private static int lastScheduleDayKey = -1;
    private static long lastScheduleCheckAt = 0L;

    private long lastActionAt;
    private long lastInfoAt;
    private long enteredWaitMapAt;
    private long waitLastMapAt;
    private long waitFinishAt;
    private long lastUseItemAt;

    private boolean invited;
    private int inviteRetryCount;
    private long lastInviteRetryAt;
    private boolean waitingNext;
    private int waitingNextMap;
    private long waitingOpenedAt;
    private long lastNextTryAt;

    private boolean waitingRealFinish;
    private long waitingRealFinishAt;
    private boolean tryingUseClanCard;
    private boolean usedClanCardThisAuto;
    private boolean sentUseClanCard;

    private long useClanCardStartAt;
    private long lastRequestClanStoreAt;

    private int clanCardQtyBefore;
    private int tryEnterCount;
    private int clanCardBefore = -1;

    public AutoLDGT() {
        super.a();
        this.lastActionAt = 0L;
        this.lastInfoAt = 0L;
        this.enteredWaitMapAt = 0L;
        this.waitLastMapAt = 0L;
        this.waitFinishAt = 0L;
        this.lastUseItemAt = 0L;
        this.invited = false;
        this.inviteRetryCount = 0;
        this.lastInviteRetryAt = 0L;
        this.waitingNext = false;
        this.waitingNextMap = -1;
        this.waitingOpenedAt = 0L;
        this.lastNextTryAt = 0L;
        this.waitingRealFinish = false;
        this.waitingRealFinishAt = 0L;
        this.tryingUseClanCard = false;
        this.usedClanCardThisAuto = false;
        this.sentUseClanCard = false;
        this.useClanCardStartAt = 0L;
        this.lastRequestClanStoreAt = 0L;
        this.clanCardQtyBefore = -1;
        this.tryEnterCount = 0;
    }

    public static void resetClanSignals() {
        clanCall = false;
        clanCallAt = 0L;
        clanOpen80 = false;
        clanOpen80At = 0L;
        clanKey87 = false;
        clanKey88 = false;
        clanKey89 = false;
        clanFinish = false;
        clanRoundId = "";
        clanFinishedRoundId = "";
    }

    public static void onClanMessage(String from, String text) {
        try {
            if (text == null) {
                return;
            }

            String s = text.trim();
            String cmd = s;
            String roundId = "";

            int p = s.indexOf('|');
            if (p >= 0) {
                cmd = s.substring(0, p).trim();
                roundId = s.substring(p + 1).trim();
            }

            if (cmd.equals("LDGT_CALL")) {
                if (roundId.equals("") || roundId.equals(clanFinishedRoundId)) {
                    return;
                }

                if (FormLDGT.isKhongDi()) {
                    clanCall = false;
                    clanCallAt = 0L;
                    return;
                }

                boolean sameRound = roundId.equals(clanRoundId);
                clanRoundId = roundId;
                clanCall = true;
                clanCallAt = System.currentTimeMillis();

                if (!sameRound) {
                    clanOpen80 = false;
                    clanOpen80At = 0L;
                    clanKey87 = false;
                    clanKey88 = false;
                    clanKey89 = false;
                    clanFinish = false;
                }

                Code.instance.a();
                tryClanAutoCall();
                return;
            }

            if (clanRoundId.equals("") || !clanRoundId.equals(roundId)) {
                return;
            }

            if (cmd.equals("LDGT_OPEN_80")) {
                clanOpen80 = true;
                clanOpen80At = System.currentTimeMillis();
            } else if (cmd.equals("LDGT_KEY_87")) {
                clanKey87 = true;
            } else if (cmd.equals("LDGT_KEY_88")) {
                clanKey88 = true;
            } else if (cmd.equals("LDGT_KEY_89")) {
                clanKey89 = true;
            } else if (cmd.equals("LDGT_FINISH")) {
                markClanFinished();
            }
        } catch (Exception e) {
        }
    }

    public static void onInfoMessage(String text) {
        try {
            if (text == null) {
                return;
            }
            String s = text.toLowerCase();
            if (s.indexOf("hành trình lãnh địa gia tộc đã kết thúc") >= 0
                    || s.indexOf("hanh trinh lanh dia gia toc da ket thuc") >= 0) {
                markClanFinished();
            }
        } catch (Exception e) {
        }
    }

    private static void markClanFinished() {
        if (!clanRoundId.equals("")) {
            clanFinishedRoundId = clanRoundId;
        }

        if (FormLDGT.isTocTruong()) {
            AutoLDGT current = getRunningAutoLDGT();

            if (current != null && !current.usedClanCardThisAuto) {
                current.startWaitingClanCard();
                return;
            }
        }

        clanFinish = true;
    }

    public static void updateSchedule() {
        try {
            if (!FormLDGT.HenGio || !FormLDGT.isTocTruong() || Char.getMyChar() == null) {
                return;
            }

            long now = System.currentTimeMillis();
            if (now - lastScheduleCheckAt < 1000L) {
                return;
            }
            lastScheduleCheckAt = now;

            java.util.Calendar cur = Res.getCurrentTime();
            if (cur.get(11) != FormLDGT.GioLDGT || cur.get(12) != FormLDGT.PhutLDGT) {
                return;
            }

            int dayKey = cur.get(1) * 1000 + cur.get(6);
            if (lastScheduleDayKey == dayKey) {
                return;
            }

            lastScheduleDayKey = dayKey;
            if (isAutoLDGTRunning()) {
                return;
            }

            resetClanSignals();
            Code.setAuto(new AutoLDGT());
            Code.instance.a();
            GameScr.chatPopup("LDGT: đến giờ, tự động bắt đầu");
        } catch (Exception e) {
        }
    }

    public static void tryClanAutoCall() {
        try {
            if (!clanCall) {
                return;
            }

            if (System.currentTimeMillis() - clanCallAt > 120000L) {
                clanCall = false;
                return;
            }

            if (Char.getMyChar() == null) {
                return;
            }
            if (FormLDGT.isKhongDi()) {
                clanCall = false;
                return;
            }
            if (FormLDGT.isTocTruong()) {
                clanCall = false;
                return;
            }

            if (!FormLDGT.isCua1() && !FormLDGT.isCua2()
                    && !FormLDGT.isCua3() && !FormLDGT.isClone()) {
                return;
            }

            if (isAutoLDGTRunning()) {
                clanCall = false;
                return;
            }

            Code.setAuto(new AutoLDGT());
            clanCall = false;
        } catch (Exception e) {
        }
    }

    private static AutoLDGT getRunningAutoLDGT() {
        Auto current = Code.auto;

        while (current != null) {
            if (current instanceof AutoLDGT) {
                return (AutoLDGT) current;
            }

            current = current.instance;
        }

        return null;
    }

    private static boolean isAutoLDGTRunning() {
        return getRunningAutoLDGT() != null;
    }

    protected final void run() {
        try {
            Char c = Char.getMyChar();
            if (c == null) {
                return;
            }

            if (c.cHP <= 0) {
                Auto.autoRemap(true);
                return;
            }

            if (this.waitingRealFinish) {
                if (!TileMap.isClanDun()) {
                    this.waitingRealFinish = false;
                    this.waitingRealFinishAt = 0L;
                    this.startUseClanCardAfterFinish();
                } else if (System.currentTimeMillis() - this.waitingRealFinishAt > 60000L) {
                    this.info("LDGT: Quá 60s vẫn chưa rời map, kết thúc auto");
                    this.finishAndReturn();
                }
                return;
            }

            if (this.tryingUseClanCard) {
                this.handleUseClanCardAfterFinish();
                return;
            }

            if (clanFinish) {
                if (FormLDGT.isTocTruong() && !this.usedClanCardThisAuto) {
                    this.startWaitingClanCard();
                } else {
                    this.finishAndReturn();
                }
                return;
            }

            if (this.waitingNext) {
                this.handleWaitingNext();
                return;
            }

            if (TileMap.mapID == MAP_LAST) {
                this.handleLastMap();
                return;
            }

            if (TileMap.mapID == MAP_BOSS) {
                this.handleBossMap();
                return;
            }

            if (TileMap.mapID >= 87 && TileMap.mapID <= 89) {
                this.handleFinalLineMaps();
                return;
            }

            if (TileMap.mapID >= 81 && TileMap.mapID <= 86) {
                this.handleNormalLineMaps();
                return;
            }

            if (TileMap.mapID == MAP_WAIT) {
                this.handleWaitMap();
                this.tryEnterCount = 0;
                
                return;
            }

            this.handleGoToTerritory();
        } catch (Exception e) {
        }
    }

    private void handleGoToTerritory() {
        if (this.tryEnterCount >= 3) {
            this.info("LDGT: Không vào được map 80 sau 3 lần, dừng auto");
            this.finishAndReturn();
            return;
        }

        if (!this.canDoAction(1200L)) {
            return;
        }

        this.tryEnterCount++;
        this.lastActionAt = System.currentTimeMillis();

        if (FormLDGT.isTocTruong()) {
            this.info("LDGT: Tộc trưởng vào map chờ");
        } else {
            this.info("LDGT: Thành viên vào map chờ");
        }

        this.openTerritoryByNpc0();
    }

    private void handleWaitMap() {
        if (this.enteredWaitMapAt == 0L) {
            this.enteredWaitMapAt = System.currentTimeMillis();
        }

        if (FormLDGT.isTocTruong()) {
            this.handleLeaderWaitMap();
        } else {
            this.handleMemberWaitMap();
        }
    }

    private void handleLeaderWaitMap() {
        long stay = System.currentTimeMillis() - this.enteredWaitMapAt;

        if (!this.invited) {
            if (stay < 5000L) {
                int sec = (int) ((5000L - stay) / 1000L);
                if (sec < 0) {
                    sec = 0;
                }
                this.info("LDGT: Đợi " + sec + "s rồi mời");
                return;
            }

            this.invited = true;
            this.lastActionAt = System.currentTimeMillis();
            this.info("LDGT: Mời thành viên");

            if (clanRoundId.equals("")) {
                clanRoundId = String.valueOf(System.currentTimeMillis());
            }

            this.callAndInviteMembers();
            return;
        }

        if (this.inviteRetryCount < 3
                && System.currentTimeMillis() - this.lastInviteRetryAt >= 5000L) {
            this.info("LDGT: Goi lai thanh vien lan " + (this.inviteRetryCount + 1));
            this.callAndInviteMembers();
        }

        if (stay < TIME_WAIT * 1000) {
            int sec = (int) ((TIME_WAIT * 1000 - stay) / 1000L);
            if (sec < 0) {
                sec = 0;
            }
            this.info("LDGT: Chờ " + sec + "s");
            return;
        }

        if (!this.canDoAction(1500L)) {
            return;
        }

        this.lastActionAt = System.currentTimeMillis();
        this.info("LDGT: Tộc trưởng mở cửa 80");
        this.openNpc27();
        Service.getInstance().m("LDGT_OPEN_80|" + clanRoundId);
        this.startWaitingNext(MAP_WAIT);
    }

    private void handleMemberWaitMap() {
        if (!clanOpen80) {
            this.info("LDGT: Chờ tộc trưởng mở cửa");
            return;
        }

        this.startWaitingNext(MAP_WAIT);
    }

    private void handleNormalLineMaps() {
        this.enteredWaitMapAt = 0L;

        if (this.hasLivingMob()) {
            this.waitFinishAt = 0L;
            this.attackAllMobs();
            return;
        }

        if (!this.canDoAction(1200L)) {
            return;
        }

        this.lastActionAt = System.currentTimeMillis();
        this.info("LDGT: Hết mob, mở cửa");
        this.openNpc27();
        this.startWaitingNext(TileMap.mapID);
    }

    private void handleFinalLineMaps() {
        this.enteredWaitMapAt = 0L;

        this.pickItemGround(261);

        if (this.hasLivingMob()) {
            this.waitFinishAt = 0L;
            this.attackAllMobs();
            return;
        }

        this.pickItemGround(261);

        if (!this.hasItemInBag(261)) {
            this.info("LDGT: Chờ nhặt 261");
            return;
        }

        if (!this.canDoAction(1200L)) {
            return;
        }

        this.lastActionAt = System.currentTimeMillis();
        this.info("LDGT: Có 261, mở cửa cuối");
        this.openNpc27();

        if (TileMap.mapID == 87) {
            Service.getInstance().m("LDGT_KEY_87|" + clanRoundId);
            clanKey87 = true;
        } else if (TileMap.mapID == 88) {
            Service.getInstance().m("LDGT_KEY_88|" + clanRoundId);
            clanKey88 = true;
        } else if (TileMap.mapID == 89) {
            Service.getInstance().m("LDGT_KEY_89|" + clanRoundId);
            clanKey89 = true;
        }

        this.startWaitingNext(TileMap.mapID);
    }

    private void handleBossMap() {
        this.enteredWaitMapAt = 0L;

        this.pickItemGround(261);
        this.useItem261IfNeed();

        if (this.hasLivingMob()) {
            this.waitLastMapAt = 0L;
            this.attackAllMobs();
            return;
        }

        if (this.waitLastMapAt == 0L) {
            this.waitLastMapAt = System.currentTimeMillis();
            this.info("LDGT: Chờ sang map cuối");
        }
    }

    private void handleLastMap() {
        this.enteredWaitMapAt = 0L;

        if (this.hasLivingMob()) {
            this.waitFinishAt = 0L;
            this.attackAllMobs();
            return;
        }

        if (this.waitFinishAt == 0L) {
            this.waitFinishAt = System.currentTimeMillis();
            this.info("LDGT: Chờ kết thúc");
            return;
        }

        if (System.currentTimeMillis() - this.waitFinishAt > 8000L) {
            Service.getInstance().m("LDGT_FINISH|" + clanRoundId);
            clanFinishedRoundId = clanRoundId;

            if (FormLDGT.isTocTruong() && !this.usedClanCardThisAuto && this.isLastLDGTMap()) {
                this.startWaitingClanCard();
            } else {
                clanFinish = true;
            }
        }
    }

    private void handleWaitingNext() {
        if (TileMap.mapID != this.waitingNextMap) {
            this.resetWaitingNext();
            return;
        }

        long delay = this.waitingNextMap == MAP_WAIT ? 3000L : 1500L;
        if (System.currentTimeMillis() - this.waitingOpenedAt < delay) {
            return;
        }

        if (this.lastNextTryAt == 0L || System.currentTimeMillis() - this.lastNextTryAt > 10000L) {
            this.lastNextTryAt = System.currentTimeMillis();
            this.info("LDGT: Thử qua cửa");
            this.nextThroughCurrentGate();
        }
    }

    private void startWaitingNext(int mapId) {
        if (!this.waitingNext || this.waitingNextMap != mapId) {
            this.waitingNext = true;
            this.waitingNextMap = mapId;
            this.waitingOpenedAt = System.currentTimeMillis();
            this.lastNextTryAt = 0L;
        }
    }

    private void resetWaitingNext() {
        this.waitingNext = false;
        this.waitingNextMap = -1;
        this.waitingOpenedAt = 0L;
        this.lastNextTryAt = 0L;
    }

    private void nextThroughCurrentGate() {
        int map = TileMap.mapID;

        if (map == 80) {
            if (FormLDGT.isCua1()) {
                this.moveThroughGate(0, 24, 252, 276);
            } else if (FormLDGT.isCua2()) {
                this.moveThroughGate(240, 264, 324, 348);
            } else if (FormLDGT.isCua3()) {
                this.moveThroughGate(575, 599, 276, 300);
            }
            return;
        }

        switch (map) {
            case 81:
                this.moveThroughGate(0, 24, 252, 276);
                return;
            case 82:
                this.moveThroughGate(33, 57, 324, 348);
                return;
            case 83:
                this.moveThroughGate(817, 841, 84, 108);
                return;
            case 84:
                this.moveThroughGate(0, 24, 348, 372);
                return;
            case 85:
                this.moveThroughGate(536, 572, 564, 588);
                return;
            case 86:
                this.moveThroughGate(721, 745, 228, 252);
                return;
            case 87:
                if (!clanKey87 || !clanKey88 || !clanKey89) {
                    this.info("LDGT: Chờ đủ 3 cửa");
                    return;
                }
                this.useItem261IfNeed();
                this.moveThroughGate(0, 24, 180, 204);
                return;
            case 88:
                if (!clanKey87 || !clanKey88 || !clanKey89) {
                    this.info("LDGT: Chờ đủ 3 cửa");
                    return;
                }
                this.useItem261IfNeed();
                this.moveThroughGate(541, 565, 690, 714);
                return;
            case 89:
                if (!clanKey87 || !clanKey88 || !clanKey89) {
                    this.info("LDGT: Chờ đủ 3 cửa");
                    return;
                }
                this.useItem261IfNeed();
                this.moveThroughGate(938, 962, 252, 276);
                return;
        }
    }

    private boolean moveThroughGate(int minX, int maxX, int minY, int maxY) {
        try {
            Char c = Char.getMyChar();
            if (c == null) {
                return false;
            }

            int x = (minX + maxX) / 2;
            int y = (minY + maxY) / 2;

            if (c.cx < minX || c.cx > maxX || c.cy < minY || c.cy > maxY) {
                Char.charMove(x, y);
                return false;
            }

            for (int i = 0; i < 3; i++) {
                Service.getInstance().d();
                Thread.sleep(120L);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void openTerritoryByNpc0() {
        try {
            Code.setAuto(new AutoNpc(MAP_OUTSIDE, 0, NPC_KANATA, "1,0", "", 1, 120));
            this.zoneID = 0;
        } catch (Exception e) {
        }
    }

    private void openNpc27() {
        try {
            Code.setAuto(new AutoNpc(TileMap.mapID, 0, NPC_OPEN, "0", "", 1, 120));
            this.zoneID = 0;
        } catch (Exception e) {
        }
    }

    private void callAndInviteMembers() {
        this.lastInviteRetryAt = System.currentTimeMillis();
        ++this.inviteRetryCount;
        Service.getInstance().requestClanMember();
        Service.getInstance().m("LDGT_CALL|" + clanRoundId);
        this.inviteAllTerritory();
    }

    private void inviteAllTerritory() {
        try {
            if (GameScr.vClan == null || GameScr.vClan.size() == 0) {
                return;
            }

            Char c = Char.getMyChar();
            String myName = c != null ? c.charName : "";

            for (int i = 0; i < GameScr.vClan.size(); i++) {
                try {
                    Member m = (Member) GameScr.vClan.elementAt(i);

                    if (m == null || m.name == null || m.name.equals("")) {
                        continue;
                    }
                    if (m.name.equals(myName)) {
                        continue;
                    }
                    if (!m.isOnline) {
                        continue;
                    }

                    Service.getInstance().inviteClanDun(m.name);
                    Thread.sleep(200L);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }

    private void attackAllMobs() {
        try {
            this.attack(-1, this.a(true, true, true, true));
        } catch (Exception e) {
        }
    }

    private boolean hasLivingMob() {
        try {
            for (int i = 0; i < GameScr.vMobAttack.size(); i++) {
                Mob m = (Mob) GameScr.vMobAttack.elementAt(i);
                if (m != null && m.hp > 0 && m.h != 0 && m.h != 1) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void useItem261IfNeed() {
        try {
            if (System.currentTimeMillis() - this.lastUseItemAt < 3000L) {
                return;
            }

            Char c = Char.getMyChar();
            if (c == null || c.arrItemBag == null) {
                return;
            }

            for (int i = 0; i < c.arrItemBag.length; i++) {
                Item it = c.arrItemBag[i];
                if (it != null && it.template != null && it.template.id == ITEM_BOSS) {
                    this.lastUseItemAt = System.currentTimeMillis();
                    Service.getInstance().useItem(i);
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    private boolean canDoAction(long delay) {
        return System.currentTimeMillis() - this.lastActionAt >= delay;
    }

    private void info(String s) {
        if (System.currentTimeMillis() - this.lastInfoAt > 3000L) {
            this.lastInfoAt = System.currentTimeMillis();
            GameScr.chatPopup(s);
        }
    }

    private void startUseClanCardAfterFinish() {
        try {
            if (!FormLDGT.isTocTruong()) {
                return;
            }

            this.tryingUseClanCard = true;
            this.waitingRealFinish = false;
            this.waitingRealFinishAt = 0L;
            this.sentUseClanCard = false;
            this.usedClanCardThisAuto = true;

            this.useClanCardStartAt = System.currentTimeMillis();
            this.lastRequestClanStoreAt = 0L;
            this.clanCardQtyBefore = -1;

            this.info("LDGT: Mở kho gia tộc kiểm tra lệnh bài 281");

            GameScr.getInstance().resetButton();
            if (Char.clan != null) {
                Char.clan.items = null; // clear cache cũ
            }
            Service.getInstance().requestClanItem();
        } catch (Exception e) {
        }
    }

    private void startWaitingClanCard() {
        clanFinish = false;
        this.waitingRealFinish = true;

        if (this.waitingRealFinishAt == 0L) {
            this.waitingRealFinishAt = System.currentTimeMillis();
        }
    }

    private void handleUseClanCardAfterFinish() {
        try {
            long now = System.currentTimeMillis();

            if (now - this.useClanCardStartAt > 30000L) {
                this.info("LDGT: 30s không thêm được lượt, kết thúc auto");
                this.tryingUseClanCard = false;
                clanFinish = true;
                this.finishAndReturn();
                return;
            }

            if (now - this.lastRequestClanStoreAt > 3000L) {
                this.lastRequestClanStoreAt = now;
                Service.getInstance().requestClanItem();
            }

            if (Char.clan == null) {
                this.info("LDGT: Chờ clan info");
                Service.getInstance().requestClanInfo();
                return;
            }

            if (Char.clan.items == null) {
                this.info("LDGT: Chờ load kho mới");
                return;
            }

            int index = -1;
            int qty = 0;

            for (int i = 0; i < Char.clan.items.length; i++) {
                Item it = Char.clan.items[i];

                if (it != null && it.template != null && it.template.id == ITEM_LENH_BAI_GT) {
                    index = i;
                    qty = it.quantity;
                    break;
                }
            }

            if (!this.sentUseClanCard) {
                if (index < 0 || qty <= 0) {
                    this.info("LDGT: Không có lệnh bài gia tộc 281 trong kho");
                    return;
                }

                this.clanCardQtyBefore = qty;
                this.sentUseClanCard = true;

                GameScr.indexSelect = index;
                Service.getInstance().ai();

                this.info("LDGT: Dùng lệnh bài 281, số lượng trước: " + qty);
                return;
            }

            if (index < 0 || qty < this.clanCardQtyBefore) {
                this.info("LDGT: Lệnh bài 281 đã giảm, bắt đầu đi lượt 2");

                this.tryingUseClanCard = false;
                this.waitingRealFinish = false;

                clanFinish = false;
                resetClanSignals();

                this.invited = false;
                this.inviteRetryCount = 0;
                this.lastInviteRetryAt = 0L;
                this.enteredWaitMapAt = 0L;
                this.waitLastMapAt = 0L;
                this.waitFinishAt = 0L;

                this.resetWaitingNext();

                this.lastActionAt = 0L;

                this.openTerritoryByNpc0();
                return;
            }

            this.info("LDGT: Chờ xác nhận số lượng lệnh bài giảm");
        } catch (Exception e) {
        }
    }

    private void finishAndReturn() {
        try {
            clanCall = false;
            clanCallAt = 0L;
            clanOpen80 = false;
            clanOpen80At = 0L;
            clanKey87 = false;
            clanKey88 = false;
            clanKey89 = false;
            clanFinish = false;

            this.waitingRealFinish = false;
            this.waitingRealFinishAt = 0L;
            this.tryingUseClanCard = false;
            this.sentUseClanCard = false;
            this.useClanCardStartAt = 0L;
            this.lastRequestClanStoreAt = 0L;
            this.clanCardQtyBefore = -1;
            this.inviteRetryCount = 0;
            this.lastInviteRetryAt = 0L;

            this.resetWaitingNext();

            if (this.instance != null) {
                Code.backToInstance();
            } else {
                Code.tatAuto();
            }
        } catch (Exception e) {
        }
    }

    private boolean hasItemInBag(int itemId) {
        try {
            Char c = Char.getMyChar();
            if (c == null || c.arrItemBag == null) {
                return false;
            }

            for (int i = 0; i < c.arrItemBag.length; i++) {
                Item it = c.arrItemBag[i];
                if (it != null && it.template != null && it.template.id == itemId) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void pickItemGround(int itemId) {
        try {
            this.pickUpItem(itemId);
        } catch (Exception e) {
        }
    }

    private boolean isLastLDGTMap() {
        return TileMap.mapID == MAP_LAST;
    }

    public final String toString() {
        return "Auto LDGT";
    }
}
