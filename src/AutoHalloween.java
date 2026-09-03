import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;

public final class AutoHalloween extends Auto {

    private static final String STORE_NAME = "AutoHalloweenCfg";
    private static final int NPC_TABEMONO = 4;
    private static final int MAP_NYMOZ = 176;
    private static final int ITEM_THU_MOI = 1071;
    private static final int MASK_MIN_ID = 814;
    private static final int MASK_MAX_ID = 818;
    private static final int MASK_MIN_PART = 50;
    private static final int MASK_MAX_PART = 54;
    private static final int MAX_FLIP = 5;
    private static final long SESSION_TIMEOUT = 25L * 60000L;
    private static final long ACTION_DELAY = 1200L;
    private static final long ROUTE_DELAY = 2500L;
    private static final long BOSS_GONE_WAIT = 9000L;
    private static final long PICK_DELAY = 450L;

    private static final int STAGE_PREPARE = 0;
    private static final int STAGE_ENTER = 1;
    private static final int STAGE_FIGHT = 2;
    private static final int STAGE_REWARD = 3;

    public static boolean AutoTime = false;
    public static boolean Leader = true;
    public static boolean AutoFlip = true;
    public static boolean PickMask = true;
    public static String PartnerNames = "";
    public static int Hour = 19;
    public static int Minute = 0;
    public static int DelayMs = 1200;

    private static boolean loaded = false;
    private static int lastScheduleKey = -1;
    private static long lastScheduleCheck = 0L;
    private static String status = "T\u1eaft";

    private final boolean leaderRunner;
    private final String leaderName;
    private int stage;
    private long startedAt;
    private long lastActionAt;
    private long lastRouteAt;
    private long noBossAt;
    private long lastPickAt;
    private boolean usedInviteItem;
    private boolean announcedEnter;
    private boolean oldAutoPk;
    private int flipCount;

    public AutoHalloween(boolean leaderRunner, String leaderName) {
        super.a();
        super.mapID = MAP_NYMOZ;
        super.zoneID = -1;
        super.isHang = true;
        this.leaderRunner = leaderRunner;
        this.leaderName = leaderName == null ? "" : leaderName;
        this.stage = STAGE_PREPARE;
        this.startedAt = System.currentTimeMillis();
        this.lastActionAt = 0L;
        this.lastRouteAt = 0L;
        this.noBossAt = 0L;
        this.lastPickAt = 0L;
        this.usedInviteItem = false;
        this.announcedEnter = false;
        this.oldAutoPk = Code.an;
        this.flipCount = 0;
        status = "\u0110ang chu\u1ea9n b\u1ecb";
    }

    public static void start() {
        AutoHalloweenManager.startManual();
    }

    static void startRunner(boolean leaderRunner, String leaderName) {
        load();
        if (Code.auto instanceof AutoHalloween) {
            return;
        }

        Code.setAuto(new AutoHalloween(leaderRunner, leaderName));
        Code.instance.a();
    }

    public static void toggle() {
        if (isRunning()) {
            stop();
        } else {
            start();
        }
    }

    public static void stop() {
        if (Code.auto instanceof AutoHalloween) {
            ((AutoHalloween) Code.auto).restorePk();
            Code.backToInstance();
        }
        AutoHalloweenManager.reset();
        status = "T\u1eaft";
        GameScr.chatPopup("D\u1eebng auto h\u00f3a trang");
    }

    public static boolean isRunning() {
        return Code.auto instanceof AutoHalloween;
    }

    public static String getStatusText() {
        load();
        return isRunning() ? status : "T\u1eaft";
    }

    public static String getAutoText() {
        load();
        if (isRunning()) {
            return "HT: " + status;
        }
        if (AutoTime) {
            return "HT:" + formatTime(Hour, Minute);
        }
        return "";
    }

    public static void updateSchedule() {
        try {
            load();
            if (!AutoTime || isRunning() || AutoHalloweenManager.isActive() || !(GameCanvas.mScreen instanceof GameScr)) {
                return;
            }

            long now = System.currentTimeMillis();
            if (now - lastScheduleCheck < 10000L) {
                return;
            }
            lastScheduleCheck = now;

            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null || !me.isHuman) {
                return;
            }

            Calendar calendar = Res.getCurrentTime();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int key = calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DATE);
            if (hour != Hour || minute != Minute || key == lastScheduleKey) {
                return;
            }

            lastScheduleKey = key;
            AutoHalloweenManager.startManual();
        } catch (Exception e) {
        }
    }

    public static void resetSchedule() {
        lastScheduleKey = -1;
        lastScheduleCheck = 0L;
    }

    protected final void run() {
        try {
            if (System.currentTimeMillis() - this.startedAt > SESSION_TIMEOUT) {
                finish("H\u00f3a trang: h\u1ebft th\u1eddi gian");
                return;
            }

            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null) {
                status = "Ch\u1edd nh\u00e2n v\u1eadt";
                return;
            }
            if (!me.isHuman) {
                finish("H\u00f3a trang ch\u1ec9 ch\u1ea1y \u1edf ch\u1ee7 th\u00e2n");
                return;
            }
            if (super.isDead()) {
                status = "\u0110ang h\u1ed3i sinh";
                if (System.currentTimeMillis() - this.lastActionAt >= 3000L) {
                    this.lastActionAt = System.currentTimeMillis();
                    Auto.autoRemap(false);
                }
                return;
            }

            if (TileMap.mapID == MAP_NYMOZ && this.stage < STAGE_FIGHT) {
                this.stage = STAGE_FIGHT;
                this.noBossAt = 0L;
                status = "\u0110\u00e3 v\u00e0o hang";
            }

            switch (this.stage) {
                case STAGE_PREPARE:
                    prepare();
                    return;
                case STAGE_ENTER:
                    enterFestival();
                    return;
                case STAGE_FIGHT:
                    fightFestival();
                    return;
                case STAGE_REWARD:
                    reward();
                    return;
            }
        } catch (Exception e) {
            finish("H\u00f3a trang: l\u1ed7i auto");
        }
    }

    private void prepare() {
        if (!isEventTime()) {
            finish("H\u00f3a trang ch\u1ec9 m\u1edf 19h-23h");
            return;
        }

        if (!ensureTabemonoMap()) {
            return;
        }

        if (!ensureReadyItems()) {
            return;
        }

        if (!ensurePartyReady()) {
            return;
        }

        this.stage = STAGE_ENTER;
        this.lastActionAt = 0L;
        status = "M\u1edf Tabemono";
    }

    private boolean ensureTabemonoMap() {
        if (GameScr.findNpc(NPC_TABEMONO) != null) {
            return true;
        }

        long now = System.currentTimeMillis();
        status = "\u0110i t\u1edbi Tabemono";
        if (now - this.lastRouteAt < ROUTE_DELAY) {
            return false;
        }

        this.lastRouteAt = now;
        if (TileMap.isVDMQ(TileMap.mapID) || TileMap.isLangCo(TileMap.mapID) || TileMap.isLangTT(TileMap.mapID)) {
            Auto.goTruongIfNeeded();
        } else if (!TileMap.isTruong(TileMap.mapID) && !TileMap.isLang(TileMap.mapID)) {
            Auto.goTruongIfNeeded();
        } else {
            TileMap.direction(1);
        }
        return false;
    }

    private boolean ensureReadyItems() {
        if (Char.countNullSlot() <= 0) {
            status = "C\u1ea7n tr\u1ed1ng 1 \u00f4 h\u00e0nh trang";
            return false;
        }

        if (!this.usedInviteItem) {
            Item invite = findBagItem(ITEM_THU_MOI);
            if (invite != null && System.currentTimeMillis() - this.lastActionAt >= getDelayMs()) {
                status = "D\u00f9ng Th\u01b0 M\u1eddi";
                Service.getInstance().useItem(invite.indexUI);
                this.usedInviteItem = true;
                this.lastActionAt = System.currentTimeMillis();
                return false;
            }
        }

        if (!hasHalloweenMaskEquipped()) {
            Item mask = findBagMask();
            if (mask == null) {
                status = "Thi\u1ebfu m\u1eb7t n\u1ea1 814-818";
                return false;
            }

            if (System.currentTimeMillis() - this.lastActionAt >= getDelayMs()) {
                status = "M\u1eb7c m\u1eb7t n\u1ea1 " + mask.template.id;
                Service.getInstance().useItem(mask.indexUI);
                this.lastActionAt = System.currentTimeMillis();
            }
            return false;
        }

        return true;
    }

    private boolean ensurePartyReady() {
        if (this.leaderRunner) {
            AutoHalloweenManager.leaderKeepAlive();
            if (GameScr.vParty.size() > 0 && !isMePartyLeader()) {
                status = "R\u1eddi nh\u00f3m c\u0169";
                if (System.currentTimeMillis() - this.lastActionAt >= getDelayMs()) {
                    Service.getInstance().t();
                    this.lastActionAt = System.currentTimeMillis();
                }
                return false;
            }

            if (!AutoHalloweenManager.hasConfiguredPartners()) {
                if (GameScr.vParty.size() >= 2) {
                    return true;
                }
                status = "Ch\u01b0a c\u00f3 nh\u00f3m 2 ng\u01b0\u1eddi";
                return false;
            }

            AutoHalloweenManager.invitePartnersIfNeeded();
            if (!AutoHalloweenManager.hasAllPartnersInParty()) {
                status = "M\u1eddi nh\u00f3m Halloween";
                return false;
            }
            if (!AutoHalloweenManager.hasAllPartnersInMap()) {
                status = "Ch\u1edd acc ph\u1ee5 t\u1edbi NPC";
                return false;
            }
            return true;
        }

        if (GameScr.vParty.size() >= 2 && AutoHalloweenManager.shouldUsePartyLeader(getPartyLeaderName())) {
            if (!AutoHalloweenManager.canMemberEnter()) {
                status = "Ch\u1edd tr\u01b0\u1edfng m\u1edf hang";
                return false;
            }
            return true;
        }

        status = "Ch\u1edd l\u1eddi m\u1eddi nh\u00f3m";
        return false;
    }

    private void enterFestival() {
        if (TileMap.mapID == MAP_NYMOZ) {
            this.stage = STAGE_FIGHT;
            this.noBossAt = 0L;
            return;
        }

        if (!ensureTabemonoMap()) {
            this.stage = STAGE_PREPARE;
            return;
        }

        if (!this.leaderRunner && !AutoHalloweenManager.canMemberEnter()) {
            status = "Ch\u1edd tr\u01b0\u1edfng v\u00e0o tr\u01b0\u1edbc";
            return;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastActionAt < getDelayMs()) {
            return;
        }

        if (this.leaderRunner && !this.announcedEnter) {
            AutoHalloweenManager.sendEnterCommand();
            this.announcedEnter = true;
        }

        if (openHalloweenNpc()) {
            status = "\u0110ang v\u00e0o l\u1ec5 h\u1ed9i";
            this.lastActionAt = now;
        } else {
            status = "Kh\u00f4ng th\u1ea5y menu h\u00f3a trang";
            if (now - this.lastActionAt > 10000L) {
                finish("NPC ch\u01b0a m\u1edf l\u1ec5 h\u1ed9i");
            }
            this.lastActionAt = now;
        }
    }

    private void fightFestival() {
        if (TileMap.mapID != MAP_NYMOZ) {
            this.stage = STAGE_ENTER;
            this.noBossAt = 0L;
            status = "Quay l\u1ea1i hang h\u00f3a trang";
            return;
        }

        if (PickMask) {
            pickMaskDrop();
        }

        if (GameScr.arrItemSprin != null) {
            this.stage = STAGE_REWARD;
            return;
        }

        Char boss = findHalloweenBoss();
        if (boss != null) {
            this.noBossAt = 0L;
            Code.an = true;
            Char me = Char.getMyChar();
            me.mobFocus = null;
            me.charFocus = boss;
            status = "\u0110\u00e1nh " + boss.charName;
            if (Math.abs(me.cx - boss.cx) > me.getNSkillSelect() + 30 || Math.abs(me.cy - boss.cy) > me.getCSkillSelect() + 30) {
                d(boss);
            }
            this.attack(-1, -1);
            return;
        }

        this.pickUpItem(-1);
        if (this.noBossAt == 0L) {
            this.noBossAt = System.currentTimeMillis();
            status = "Ch\u1edd th\u1eafng";
            return;
        }

        if (System.currentTimeMillis() - this.noBossAt >= BOSS_GONE_WAIT) {
            this.stage = STAGE_REWARD;
            status = "Nh\u1eadn th\u01b0\u1edfng";
        }
    }

    private void reward() {
        if (PickMask) {
            pickMaskDrop();
        }

        if (AutoFlip && GameScr.arrItemSprin != null && this.flipCount < MAX_FLIP && Char.countNullSlot() > 0) {
            boolean oldHide = AutoDapDo.isHidingLuckyCardUi();
            AutoDapDo.setHideLuckyCardUi(true);
            try {
                int before = AutoDapDo.getLuckyCardResultCount();
                GameScr.indexSelect = this.flipCount % 9;
                status = "L\u1eadt th\u1ebb " + (this.flipCount + 1);
                Service.getInstance().ah();
                AutoDapDo.waitLuckyCardResultSilent(before);
                if (AutoDapDo.getLuckyCardResultCount() == before) {
                    this.flipCount = MAX_FLIP;
                } else {
                    ++this.flipCount;
                }
            } finally {
                AutoDapDo.hideLuckyCardUiNow();
                AutoDapDo.setHideLuckyCardUi(oldHide);
            }
            return;
        }

        finish("H\u00f3a trang: xong");
    }

    private boolean openHalloweenNpc() {
        Npc npc = GameScr.findNpc(NPC_TABEMONO);
        if (npc == null) {
            return false;
        }

        closePopups();
        Char.charMove(npc.cx, npc.cy);
        Char.getMyChar().npcFocus = npc;
        Service.getInstance().openMenu(NPC_TABEMONO);

        int menuIndex = waitHalloweenMenuIndex(getDelayMs() + 1800L);
        if (menuIndex < 0) {
            closePopups();
            return false;
        }

        try {
            GameCanvas.menu.showMenu = false;
        } catch (Exception e) {
        }
        Service.getInstance().menu(NPC_TABEMONO, menuIndex, 0);
        return true;
    }

    private static int waitHalloweenMenuIndex(long timeout) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeout) {
            try {
                if (GameCanvas.menu != null && GameCanvas.menu.showMenu) {
                    int index = GameCanvas.menu.findIndexByCaptionContains("h\u00f3a trang");
                    if (index < 0) {
                        index = GameCanvas.menu.findIndexByCaptionContains("H\u00f3a Trang");
                    }
                    if (index < 0) {
                        index = GameCanvas.menu.findIndexByCaptionContains("hoa trang");
                    }
                    if (index < 0) {
                        index = GameCanvas.menu.findIndexByCaptionContains("trang");
                    }
                    if (index >= 0) {
                        return index;
                    }
                }
            } catch (Exception e) {
            }
            Auto.sleep(60L);
        }
        return -1;
    }

    private static Char findHalloweenBoss() {
        Char best = null;
        int bestDistance = 2147483647;
        Char me = Char.getMyChar();
        try {
            for (int i = 0; i < GameScr.vCharInMap.size(); ++i) {
                Char c = (Char) GameScr.vCharInMap.elementAt(i);
                if (c == null || !isHalloweenBoss(c) || checkDead(c)) {
                    continue;
                }

                int distance = me == null ? 0 : Math.abs(me.cx - c.cx) + Math.abs(me.cy - c.cy);
                if (best == null || distance < bestDistance) {
                    best = c;
                    bestDistance = distance;
                }
            }
        } catch (Exception e) {
        }
        return best;
    }

    private static boolean isHalloweenBoss(Char c) {
        if (c.charID >= -9999 && c.charID <= -9995) {
            return true;
        }
        if (c.charName == null) {
            return false;
        }
        String name = c.charName.toLowerCase();
        return name.indexOf("shin") >= 0 || name.indexOf("oni") >= 0 || name.indexOf("kuma") >= 0
                || name.indexOf("inu") >= 0 || name.indexOf("di\u1ec7n") >= 0 || name.indexOf("dien") >= 0;
    }

    private void pickMaskDrop() {
        long now = System.currentTimeMillis();
        if (now - this.lastPickAt < PICK_DELAY || GameScr.vItemMap == null || Char.countNullSlot() <= 0) {
            return;
        }

        ItemMap best = null;
        int bestDistance = 2147483647;
        Char me = Char.getMyChar();
        try {
            for (int i = 0; i < GameScr.vItemMap.size(); ++i) {
                ItemMap item = (ItemMap) GameScr.vItemMap.elementAt(i);
                if (item == null || item.template == null || item.isPickedUp || !isMaskId(item.template.id)) {
                    continue;
                }

                int distance = me == null ? 0 : Math.abs(me.cx - item.xEnd) + Math.abs(me.cy - item.yEnd);
                if (best == null || distance < bestDistance) {
                    best = item;
                    bestDistance = distance;
                }
            }

            if (best == null) {
                return;
            }

            this.lastPickAt = now;
            if (me != null && bestDistance > 50) {
                Char.charMove(best.xEnd, TileMap.d(best.xEnd, best.yEnd));
            }
            Service.getInstance().pickItem(best.itemMapID);
            best.isPickedUp = true;
        } catch (Exception e) {
        }
    }

    private void finish(String text) {
        status = text;
        GameScr.chatPopup(text);
        restorePk();
        AutoHalloweenManager.finishRound();
        if (Code.auto == this) {
            Code.backToInstance();
        }
    }

    private void restorePk() {
        Code.an = this.oldAutoPk;
    }

    private static boolean isEventTime() {
        Calendar calendar = Res.getCurrentTime();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 19 && hour < 23;
    }

    private static boolean hasHalloweenMaskEquipped() {
        try {
            int mask = Char.getMyChar().ID_MAT_NA;
            return mask >= MASK_MIN_PART && mask <= MASK_MAX_PART;
        } catch (Exception e) {
            return false;
        }
    }

    private static Item findBagMask() {
        try {
            Item[] bag = Char.getMyChar().arrItemBag;
            for (int i = 0; i < bag.length; ++i) {
                Item item = bag[i];
                if (item != null && item.template != null && isMaskId(item.template.id)) {
                    return item;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static Item findBagItem(int itemId) {
        try {
            Item[] bag = Char.getMyChar().arrItemBag;
            for (int i = 0; i < bag.length; ++i) {
                Item item = bag[i];
                if (item != null && item.template != null && item.template.id == itemId) {
                    return item;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static boolean isMaskId(int id) {
        return id >= MASK_MIN_ID && id <= MASK_MAX_ID;
    }

    private static boolean isMePartyLeader() {
        try {
            return GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID;
        } catch (Exception e) {
            return false;
        }
    }

    private static String getPartyLeaderName() {
        try {
            if (GameScr.vParty.size() > 0) {
                return ((Party) GameScr.vParty.firstElement()).d;
            }
        } catch (Exception e) {
        }
        return "";
    }

    private static int getDelayMs() {
        load();
        return DelayMs < 500 ? 500 : DelayMs;
    }

    static String[] getPartnerArray() {
        load();
        if (PartnerNames == null || PartnerNames.trim().length() == 0) {
            return new String[0];
        }
        return Code.splitString(PartnerNames, ",");
    }

    private static void closePopups() {
        try {
            GameCanvas.setMaxTextLenght();
            GameCanvas.currentDialog = null;
            if (GameCanvas.menu != null) {
                GameCanvas.menu.showMenu = false;
            }
            GameScr game = GameScr.getInstance();
            if (game != null) {
                game.closeDialog();
                game.resetButton();
            }
        } catch (Exception e) {
        }
    }

    static String formatTime(int hour, int minute) {
        if (hour < 0) {
            hour = 0;
        }
        if (minute < 0) {
            minute = 0;
        }
        hour %= 24;
        minute %= 60;
        StringBuffer sb = new StringBuffer();
        if (hour < 10) {
            sb.append('0');
        }
        sb.append(hour).append(':');
        if (minute < 10) {
            sb.append('0');
        }
        sb.append(minute);
        return sb.toString();
    }

    public static void save() {
        try {
            normalize();
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(AutoTime);
            dataout.writeBoolean(Leader);
            dataout.writeBoolean(AutoFlip);
            dataout.writeBoolean(PickMask);
            dataout.writeUTF(PartnerNames == null ? "" : PartnerNames);
            dataout.writeInt(Hour);
            dataout.writeInt(Minute);
            dataout.writeInt(DelayMs);
            dataout.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            dataout.close();
            byteout.close();
            loaded = true;
            resetSchedule();
        } catch (Exception e) {
        }
    }

    public static void load() {
        if (loaded) {
            return;
        }

        try {
            byte[] data = RMS.getRecord(STORE_NAME);
            if (data != null) {
                ByteArrayInputStream bytein = new ByteArrayInputStream(data);
                DataInputStream datain = new DataInputStream(bytein);
                AutoTime = datain.readBoolean();
                Leader = datain.readBoolean();
                AutoFlip = datain.readBoolean();
                PickMask = datain.readBoolean();
                PartnerNames = datain.readUTF();
                Hour = datain.readInt();
                Minute = datain.readInt();
                if (datain.available() > 0) {
                    DelayMs = datain.readInt();
                }
                datain.close();
                bytein.close();
            }
        } catch (Exception e) {
        }

        normalize();
        loaded = true;
    }

    private static void normalize() {
        if (PartnerNames == null) {
            PartnerNames = "";
        }
        if (Hour < 0) {
            Hour = 0;
        }
        if (Hour > 23) {
            Hour = 23;
        }
        if (Minute < 0) {
            Minute = 0;
        }
        if (Minute > 59) {
            Minute = 59;
        }
        if (DelayMs < 500) {
            DelayMs = 500;
        }
        if (DelayMs > 10000) {
            DelayMs = 10000;
        }
    }

    public final String toString() {
        return "Auto H\u00f3a Trang " + status;
    }

    static {
        load();
    }
}

final class AutoHalloweenManager {

    private static final long INVITE_DELAY = 4000L;
    private static final long PREPARE_DELAY = 8000L;
    private static final long MEMBER_ENTER_DELAY = 2500L;

    private static boolean active = false;
    private static boolean leaderRound = false;
    private static String roundId = "";
    private static String currentLeader = "";
    private static long lastInviteAt = 0L;
    private static long lastPrepareAt = 0L;
    private static long memberEnterAt = 0L;

    private AutoHalloweenManager() {
    }

    public static void startManual() {
        AutoHalloween.load();
        if (AutoHalloween.isRunning()) {
            AutoHalloween.stop();
            return;
        }

        if (AutoHalloween.Leader) {
            startLeaderRound();
        } else {
            startMemberWait();
        }
    }

    private static void startLeaderRound() {
        reset();
        active = true;
        leaderRound = true;
        roundId = String.valueOf(System.currentTimeMillis());
        currentLeader = Char.getMyChar() == null ? "" : Char.getMyChar().charName;
        AutoHalloween.startRunner(true, currentLeader);
        sendPrepare();
        GameScr.chatPopup("H\u00f3a trang: t\u1eadp h\u1ee3p nh\u00f3m");
    }

    private static void startMemberWait() {
        reset();
        active = true;
        leaderRound = false;
        currentLeader = firstConfiguredName();
        AutoHalloween.startRunner(false, currentLeader);
        GameScr.chatPopup("H\u00f3a trang: ch\u1edd tr\u01b0\u1edfng nh\u00f3m");
    }

    public static boolean onPrivateMessage(String from, String text) {
        try {
            if (from == null || text == null || !text.startsWith("HT_")) {
                return false;
            }

            String[] part = Code.splitString(text, "|");
            String cmd = part[0];

            if (cmd.equals("HT_PREPARE") && part.length >= 2 && canAcceptLeader(from)) {
                active = true;
                leaderRound = false;
                roundId = part[1];
                currentLeader = from;
                memberEnterAt = 0L;
                AutoHalloween.startRunner(false, from);
                sendPrivate(from, "HT_READY|" + roundId);
                return true;
            }

            if (cmd.equals("HT_ENTER") && part.length >= 2 && canAcceptLeader(from)) {
                active = true;
                leaderRound = false;
                roundId = part[1];
                currentLeader = from;
                memberEnterAt = System.currentTimeMillis() + MEMBER_ENTER_DELAY;
                AutoHalloween.startRunner(false, from);
                return true;
            }

            if (cmd.equals("HT_STOP") && part.length >= 2 && roundId.equals(part[1]) && canAcceptLeader(from)) {
                AutoHalloween.stop();
                return true;
            }

            if (active && leaderRound && cmd.equals("HT_READY")) {
                return true;
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

    public static boolean isActive() {
        return active;
    }

    public static boolean canMemberEnter() {
        return !leaderRound && active && memberEnterAt > 0L && System.currentTimeMillis() >= memberEnterAt;
    }

    public static void leaderKeepAlive() {
        if (!active || !leaderRound) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - lastPrepareAt >= PREPARE_DELAY) {
            sendPrepare();
        }
    }

    public static void invitePartnersIfNeeded() {
        if (!active || !leaderRound) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - lastInviteAt < INVITE_DELAY) {
            return;
        }
        lastInviteAt = now;

        String[] names = AutoHalloween.getPartnerArray();
        for (int i = 0; i < names.length; ++i) {
            String name = normalizeName(names[i]);
            if (name.length() > 0 && !isInParty(name)) {
                Service.getInstance().addParty(name);
            }
        }
    }

    public static boolean hasConfiguredPartners() {
        String[] names = AutoHalloween.getPartnerArray();
        for (int i = 0; i < names.length; ++i) {
            if (normalizeName(names[i]).length() > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAllPartnersInParty() {
        String[] names = AutoHalloween.getPartnerArray();
        boolean hasName = false;
        for (int i = 0; i < names.length; ++i) {
            String name = normalizeName(names[i]);
            if (name.length() <= 0) {
                continue;
            }
            hasName = true;
            if (!isInParty(name)) {
                return false;
            }
        }
        return hasName && GameScr.vParty.size() >= 2;
    }

    public static boolean hasAllPartnersInMap() {
        String[] names = AutoHalloween.getPartnerArray();
        boolean hasName = false;
        for (int i = 0; i < names.length; ++i) {
            String name = normalizeName(names[i]);
            if (name.length() <= 0) {
                continue;
            }
            hasName = true;
            if (!isCharInCurrentMap(name)) {
                return false;
            }
        }
        return hasName;
    }

    public static void sendEnterCommand() {
        if (!active || !leaderRound) {
            return;
        }
        String[] names = AutoHalloween.getPartnerArray();
        for (int i = 0; i < names.length; ++i) {
            sendPrivate(names[i], "HT_ENTER|" + roundId);
        }
    }

    public static void finishRound() {
        try {
            if (active && leaderRound) {
                String[] names = AutoHalloween.getPartnerArray();
                for (int i = 0; i < names.length; ++i) {
                    sendPrivate(names[i], "HT_STOP|" + roundId);
                }
            }
            if (active && GameScr.vParty.size() > 0) {
                Service.getInstance().t();
            }
        } catch (Exception e) {
        }
        reset();
    }

    public static void reset() {
        active = false;
        leaderRound = false;
        roundId = "";
        currentLeader = "";
        lastInviteAt = 0L;
        lastPrepareAt = 0L;
        memberEnterAt = 0L;
    }

    private static void sendPrepare() {
        lastPrepareAt = System.currentTimeMillis();
        String[] names = AutoHalloween.getPartnerArray();
        for (int i = 0; i < names.length; ++i) {
            sendPrivate(names[i], "HT_PREPARE|" + roundId);
        }
    }

    private static boolean canAcceptLeader(String from) {
        if (AutoHalloween.Leader) {
            return false;
        }

        String[] names = AutoHalloween.getPartnerArray();
        boolean hasName = false;
        for (int i = 0; i < names.length; ++i) {
            String name = normalizeName(names[i]);
            if (name.length() <= 0) {
                continue;
            }
            hasName = true;
            if (sameName(name, from)) {
                return true;
            }
        }
        return !hasName || sameName(currentLeader, from);
    }

    private static String firstConfiguredName() {
        String[] names = AutoHalloween.getPartnerArray();
        for (int i = 0; i < names.length; ++i) {
            String name = normalizeName(names[i]);
            if (name.length() > 0) {
                return name;
            }
        }
        return "";
    }

    private static boolean isInParty(String name) {
        for (int i = 0; i < GameScr.vParty.size(); ++i) {
            Party party = (Party) GameScr.vParty.elementAt(i);
            if (party != null && sameName(party.d, name)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCharInCurrentMap(String name) {
        for (int i = 0; i < GameScr.vCharInMap.size(); ++i) {
            Char c = (Char) GameScr.vCharInMap.elementAt(i);
            if (c != null && sameName(c.charName, name)) {
                return true;
            }
        }
        return false;
    }

    private static void sendPrivate(String name, String text) {
        name = normalizeName(name);
        if (name.length() > 0) {
            Code.a(name, text);
        }
    }

    private static String normalizeName(String name) {
        return name == null ? "" : name.trim();
    }

    private static boolean sameName(String first, String second) {
        return first != null && second != null && first.trim().equalsIgnoreCase(second.trim());
    }
}
