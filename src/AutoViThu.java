import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;

public final class AutoViThu extends Auto {

    private static final String STORE_NAME = "AutoViThuCfg";
    private static final int ITEM_VI_THU_LENH = 983;
    private static final int ITEM_DAY_NENSHI = 946;
    private static final int ITEM_TRUNG_VI_THU = 993;
    private static final int ITEM_KHA_DI_LENH = 35;
    private static final int ITEM_TAMAMIZU = 989;
    private static final int ITEM_TAMAKIRO = 990;
    private static final int ITEM_TAMAMURA = 991;
    private static final int ITEM_TAMADAI = 992;
    private static final int SHOP_TYPE = 8;
    private static final int SHOP_KHA_DI = 9;
    private static final int NENSHI_NEED = 5;
    private static final int MAP_BOSS = 169;
    private static final int MAP_WAIT = 171;
    private static final int MOB_BOSS = 237;
    private static final int MOB_EGG = 236;
    private static final long SESSION_TIMEOUT = 12000000L;
    private static final long ACTION_DELAY = 8000L;
    private static final long BUY_DELAY = 5000L;
    private static final long EGG_PICK_TIME = 12000L;
    private static final long NO_EGG_DELAY = 2500L;
    private static final long REMAP_DELAY = 2000L;
    private static final long DEAD_REMAP_DELAY = 3500L;
    private static final long EGG_INFO_TIMEOUT = 3500L;
    private static final int MIN_OPEN_EGG_DELAY = 500;
    private static final int MAX_EGG_RECHECK = 3;

    private static final int STAGE_PREPARE = 0;
    private static final int STAGE_WAIT = 1;
    private static final int STAGE_BOSS = 2;
    private static final int STAGE_EGG = 3;

    public static boolean AutoTime = false;
    public static boolean AttackBoss = true;
    public static boolean PickEgg = true;
    public static boolean AutoOpenEggAfterHang = false;
    public static boolean OpenForeverChildEgg = true;
    public static boolean DeleteTimedEgg = true;
    public static int OpenEggDelayMs = 1500;
    public static int Hour = 21;
    public static int Minute = 45;

    private static boolean loaded = false;
    private static int lastScheduleKey = -1;
    private static long lastScheduleCheck = 0L;
    private static String status = "Tat";
    private static boolean openEggRunning = false;
    private static String openEggStatus = "Tat";
    private static int openEggUsed = 0;
    private static int openEggKept = 0;
    private static int openEggDeleted = 0;

    private int stage;
    private long startedAt;
    private long lastActionAt;
    private long bossMapAt;
    private boolean bossSeen;
    private Mob eggTarget;
    private long eggStartAt;
    private int eggBefore;
    private int eggDone;
    private long noEggAt;
    private int eggRecheckCount;
    private int eggRecheckStep;
    private boolean needBuyNenshi;
    private boolean usedKhaDiReturn;
    private boolean returningHomeAfterNoEgg;
    private long lastDeadRemapAt;

    public AutoViThu() {
        super.a();
        super.mapID = MAP_WAIT;
        super.zoneID = -1;
        super.isHang = true;
        this.stage = STAGE_PREPARE;
        this.startedAt = System.currentTimeMillis();
        this.lastActionAt = 0L;
        this.bossMapAt = 0L;
        this.bossSeen = false;
        this.eggTarget = null;
        this.eggStartAt = 0L;
        this.eggBefore = 0;
        this.eggDone = 0;
        this.noEggAt = 0L;
        this.eggRecheckCount = 0;
        this.eggRecheckStep = 0;
        this.needBuyNenshi = false;
        this.usedKhaDiReturn = false;
        this.returningHomeAfterNoEgg = false;
        this.lastDeadRemapAt = 0L;
        status = "Khoi dong";
    }

    public static void start() {
        load();
        if (Code.auto instanceof AutoViThu) {
            GameScr.chatPopup("Auto Vi Thu dang chay");
            return;
        }

        Code.setAuto(new AutoViThu());
        Code.instance.a();
        GameScr.chatPopup("Bat auto Vi Thu");
    }

    public static void toggle() {
        if (Code.auto instanceof AutoViThu) {
            stop();
        } else {
            start();
        }
    }

    public static void stop() {
        if (Code.auto instanceof AutoViThu) {
            Code.backToInstance();
        }
        status = "Tat";
        GameScr.chatPopup("Dung auto Vi Thu");
    }

    public static boolean isRunning() {
        return Code.auto instanceof AutoViThu;
    }

    public static String getStatusText() {
        load();
        if (isRunning()) {
            return status;
        }
        return openEggRunning ? "Mo trung: " + openEggStatus : "Tat";
    }

    public static String getAutoText() {
        load();
        if (isRunning()) {
            return "VT: " + status;
        }
        if (openEggRunning) {
            return "Trung VT: " + openEggStatus;
        }
        if (AutoTime) {
            return "VT: " + formatTime(Hour, Minute);
        }
        return "";
    }

    public static boolean isOpenEggRunning() {
        return openEggRunning;
    }

    public static String getOpenEggStatusText() {
        return openEggRunning ? openEggStatus : "Tat";
    }

    public static void toggleOpenEgg() {
        if (openEggRunning) {
            stopOpenEgg();
        } else {
            startOpenEgg();
        }
    }

    public static void startOpenEgg() {
        load();
        normalize();
        if (openEggRunning) {
            GameScr.chatPopup("Auto mo trung Vi Thu dang chay");
            return;
        }

        openEggRunning = true;
        openEggStatus = "Khoi dong";
        openEggUsed = 0;
        openEggKept = 0;
        openEggDeleted = 0;
        (new Thread(new AutoOpenEggTask())).start();
        GameScr.chatPopup("Bat auto mo trung Vi Thu");
    }

    public static void stopOpenEgg() {
        openEggRunning = false;
        openEggStatus = "Tat";
        GameScr.chatPopup("Dung auto mo trung Vi Thu");
    }

    public static void updateSchedule() {
        try {
            load();
            if (!AutoTime || isRunning() || !(GameCanvas.mScreen instanceof GameScr)) {
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
            start();
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
                finish("Vi Thu: het thoi gian");
                return;
            }

            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null) {
                status = "Cho nhan vat";
                return;
            }
            if (!me.isHuman) {
                finish("Vi Thu chi chay o chu than");
                return;
            }
            if (super.isDead()) {
                this.handleDead();
                return;
            }
            this.lastDeadRemapAt = 0L;

            if (TileMap.mapID == MAP_BOSS && this.bossMapAt == 0L) {
                this.bossMapAt = System.currentTimeMillis();
            }

            switch (this.stage) {
                case STAGE_PREPARE:
                    this.prepareAndEnter();
                    return;
                case STAGE_WAIT:
                    this.waitBossMap();
                    return;
                case STAGE_BOSS:
                    this.runBossStage();
                    return;
                case STAGE_EGG:
                    this.runEggStage();
                    return;
            }
        } catch (Exception e) {
            finish("Vi Thu: loi auto");
        }
    }

    private void handleDead() {
        long now = System.currentTimeMillis();
        status = "Chet, dang hoi sinh";
        this.clearEggPickup();
        if (now - this.lastDeadRemapAt < DEAD_REMAP_DELAY) {
            return;
        }

        this.lastDeadRemapAt = now;
        this.lastActionAt = now;
        Auto.autoRemap(false);
    }

    private void prepareAndEnter() {
        if (TileMap.mapID == MAP_BOSS) {
            this.stage = STAGE_BOSS;
            return;
        }
        if (TileMap.mapID == MAP_WAIT) {
            this.stage = STAGE_WAIT;
            return;
        }
        if (isHangViThu(TileMap.mapID)) {
            this.routeInHang(MAP_BOSS);
            return;
        }

        if (!this.ensureItem(ITEM_VI_THU_LENH, 1)) {
            return;
        }
        if (!this.ensureItem(ITEM_DAY_NENSHI, NENSHI_NEED)) {
            return;
        }

        Item item = Char.getItemByID(ITEM_VI_THU_LENH);
        if (item == null) {
            status = "Thieu Vi Thu Lenh";
            return;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastActionAt < ACTION_DELAY) {
            return;
        }

        status = "Dung lenh vao hang";
        Service.getInstance().useItem(item.indexUI);
        this.lastActionAt = now;
    }

    private boolean ensureItem(int itemId, int need) {
        int have = Char.k(itemId);
        if (have >= need) {
            return true;
        }

        if (Char.countNullSlot() <= 0) {
            status = "Full hanh trang, thieu " + itemId;
            return false;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastActionAt < BUY_DELAY) {
            return false;
        }

        status = "Mua " + itemId + " " + have + "/" + need;
        this.lastActionAt = now;
        AutoBuyShop.buyNow(itemId, SHOP_TYPE, need - have);
        return Char.k(itemId) >= need;
    }

    private void waitBossMap() {
        if (TileMap.mapID == MAP_BOSS) {
            this.stage = STAGE_BOSS;
            this.bossMapAt = System.currentTimeMillis();
            return;
        }
        if (!isHangViThu(TileMap.mapID)) {
            this.stage = STAGE_PREPARE;
            return;
        }

        long now = System.currentTimeMillis();
        status = AttackBoss ? "Cho boss mo cua" : "Cho boss die";
        if (now - this.lastActionAt >= ACTION_DELAY) {
            this.routeInHang(MAP_BOSS);
            this.lastActionAt = now;
        }
    }

    private void runBossStage() {
        if (TileMap.mapID != MAP_BOSS) {
            this.stage = isHangViThu(TileMap.mapID) ? STAGE_WAIT : STAGE_PREPARE;
            this.bossMapAt = 0L;
            return;
        }

        if (TileMap.zoneID != 0) {
            this.stage = STAGE_EGG;
            this.clearEggPickup();
            return;
        }

        Mob boss = findMob(MOB_BOSS);
        if (boss != null) {
            this.bossSeen = true;
            if (AttackBoss) {
                status = "Danh boss 237";
                this.pickUpItem(-1);
                this.attack(boss.id, -1);
            } else {
                status = "Dung cho boss die";
                moveSafeInBossMap();
            }
            return;
        }

        if (this.bossSeen || System.currentTimeMillis() - this.bossMapAt > 10000L) {
            status = "Tim trung";
            this.stage = STAGE_EGG;
            this.clearEggPickup();
        } else {
            status = "Cho boss xuat hien";
        }
    }

    private void runEggStage() {
        if (!PickEgg) {
            finish("Vi Thu: khong bat nhat trung");
            return;
        }
        if (this.eggRecheckStep != 0) {
            this.continueEggRecheck();
            return;
        }
        if (this.returningHomeAfterNoEgg) {
            this.returnHomeAfterEggs();
            return;
        }
        if (TileMap.mapID != MAP_BOSS) {
            if (TileMap.mapID == MAP_WAIT && this.needBuyNenshi) {
                this.buyOneNenshiAndReturn();
                return;
            }
            if (isHangViThu(TileMap.mapID) && System.currentTimeMillis() - this.lastActionAt >= ACTION_DELAY) {
                status = "Vao map trung";
                this.routeInHang(MAP_BOSS);
                this.lastActionAt = System.currentTimeMillis();
            } else if (!isHangViThu(TileMap.mapID)) {
                this.stage = STAGE_PREPARE;
            }
            return;
        }

        if (this.usedKhaDiReturn) {
            return;
        }
        if (TileMap.zoneID == 0) {
            Mob boss = findMob(MOB_BOSS);
            if (boss != null) {
                this.stage = STAGE_BOSS;
                return;
            }
            status = "Cho chia khu trung";
            return;
        }

        if (this.eggTarget != null) {
            if (System.currentTimeMillis() - this.eggStartAt < EGG_PICK_TIME) {
                status = "Dang nhat trung " + (this.eggDone + 1);
                return;
            }

            if (Char.k(ITEM_TRUNG_VI_THU) > this.eggBefore) {
                ++this.eggDone;
                status = "Da nhat trung " + this.eggDone;
            } else {
                status = "Thu lai trung";
            }
            this.clearEggPickup();
            return;
        }

        Mob egg = findMob(MOB_EGG);
        if (egg == null) {
            this.pickUpItem(ITEM_TRUNG_VI_THU);
            this.handleNoEgg();
            return;
        }

        this.resetEggRecheck();
        if (Char.k(ITEM_DAY_NENSHI) <= 0) {
            this.buyOneNenshiAndReturn();
            return;
        }
        if (Char.countNullSlot() <= 0) {
            status = "Can trong 1 o nhat trung";
            return;
        }

        Char me = Char.getMyChar();
        int tx = egg.curX + (me.cx <= egg.curX ? -24 : 24);
        if (tx < 24) {
            tx = 24;
        }
        if (tx > TileMap.c - 24) {
            tx = TileMap.c - 24;
        }
        int ty = TileMap.d(tx, egg.curY);
        if (Math.abs(me.cx - egg.curX) > 80 || Math.abs(me.cy - egg.curY) > 70) {
            status = "Den gan trung 236";
            Char.charMove(tx, ty);
            return;
        }

        status = "Buoc day Nenshi";
        this.eggTarget = egg;
        this.eggBefore = Char.k(ITEM_TRUNG_VI_THU);
        this.eggStartAt = System.currentTimeMillis();
        me.mobFocus = egg;
        this.attack(MOB_EGG, -1);
    }

    private void handleNoEgg() {
        long now = System.currentTimeMillis();
        if (this.noEggAt == 0L) {
            this.noEggAt = now;
            status = "Cho kiem tra trung";
            return;
        }
        if (now - this.noEggAt < NO_EGG_DELAY) {
            status = "Kiem tra trung";
            return;
        }
        if (this.eggRecheckCount < MAX_EGG_RECHECK) {
            status = "Remap kiem trung " + (this.eggRecheckCount + 1) + "/" + MAX_EGG_RECHECK;
            this.eggRecheckStep = 1;
            this.lastActionAt = 0L;
            this.continueEggRecheck();
            return;
        }

        this.returningHomeAfterNoEgg = true;
        this.returnHomeAfterEggs();
    }

    private void continueEggRecheck() {
        long now = System.currentTimeMillis();
        if (!isHangViThu(TileMap.mapID)) {
            this.stage = STAGE_PREPARE;
            this.eggRecheckStep = 0;
            return;
        }

        if (this.eggRecheckStep == 1) {
            if (TileMap.mapID == MAP_WAIT) {
                this.eggRecheckStep = 2;
                this.lastActionAt = now;
                status = "Cho quay lai 169";
                return;
            }
            if (now - this.lastActionAt >= ACTION_DELAY) {
                status = "Ra 171 kiem trung";
                this.routeInHang(MAP_WAIT);
                this.lastActionAt = now;
            }
            return;
        }

        if (this.eggRecheckStep == 2) {
            if (TileMap.mapID == MAP_BOSS) {
                ++this.eggRecheckCount;
                this.eggRecheckStep = 0;
                this.noEggAt = 0L;
                status = "Kiem lai trung " + this.eggRecheckCount + "/" + MAX_EGG_RECHECK;
                return;
            }
            if (now - this.lastActionAt >= REMAP_DELAY) {
                status = "Vao lai 169 kiem trung";
                this.routeInHang(MAP_BOSS);
                this.lastActionAt = now;
            }
        }
    }

    private void buyOneNenshiAndReturn() {
        if (TileMap.mapID != MAP_WAIT) {
            long now = System.currentTimeMillis();
            status = "Het day, ve 171 mua";
            this.needBuyNenshi = true;
            if (now - this.lastActionAt >= ACTION_DELAY) {
                this.routeInHang(MAP_WAIT);
                this.lastActionAt = now;
            }
            return;
        }

        if (Char.k(ITEM_DAY_NENSHI) <= 0) {
            this.buyDirectItem(ITEM_DAY_NENSHI, SHOP_TYPE, 1);
            return;
        }

        if (System.currentTimeMillis() - this.lastActionAt >= REMAP_DELAY) {
            status = "Co day, vao lai 169";
            this.needBuyNenshi = false;
            this.routeInHang(MAP_BOSS);
            this.lastActionAt = System.currentTimeMillis();
        }
    }

    private void returnHomeAfterEggs() {
        if (this.usedKhaDiReturn) {
            return;
        }

        long now = System.currentTimeMillis();
        if (TileMap.mapID != MAP_WAIT) {
            status = "Het trung, ve 171";
            this.needBuyNenshi = false;
            this.returningHomeAfterNoEgg = true;
            if (now - this.lastActionAt >= ACTION_DELAY) {
                this.routeInHang(MAP_WAIT);
                this.lastActionAt = now;
            }
            return;
        }

        if (Char.getIndexItemById(ITEM_KHA_DI_LENH) < 0) {
            if (!this.buyDirectItem(ITEM_KHA_DI_LENH, SHOP_KHA_DI, 1)) {
                return;
            }
        }

        int index = Char.getIndexItemById(ITEM_KHA_DI_LENH);
        if (index < 0) {
            status = "Cho mua Kha Di Lenh";
            return;
        }

        try {
            Item item = Char.getMyChar().arrItemBag[index];
            if (item == null) {
                status = "Loi item Kha Di Lenh";
                return;
            }

            status = "Dung Kha Di Lenh ve truong";
            this.usedKhaDiReturn = true;
            Service.getInstance().useItemChangeMap(item.indexUI, 0);
            TileMap.g();
            Auto.sleep(800L);
            finish("Vi Thu: het trung, ve truong");
        } catch (Exception e) {
            status = "Loi ve truong";
        }
    }

    private boolean buyDirectItem(int itemId, int shopType, int quantity) {
        if (quantity <= 0) {
            quantity = 1;
        }
        if (Char.getIndexItemById(itemId) < 0 && Char.countNullSlot() <= 0) {
            status = "Full hanh trang, khong mua " + itemId;
            return false;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastActionAt < BUY_DELAY) {
            return false;
        }

        int before = Char.k(itemId);
        status = "Mua " + itemId + " shop " + shopType;
        this.lastActionAt = now;
        try {
            Service.getInstance().requestItem(shopType);
            Auto.sleep(700L);
            Item shopItem = findShopItem(shopType, itemId);
            if (shopItem == null) {
                status = "Khong thay " + itemId + " shop " + shopType;
                return false;
            }

            Service.getInstance().buyItem1(shopItem.typeUI, shopItem.indexUI, quantity);
            Auto.sleep(900L);
            return Char.k(itemId) > before || Char.getIndexItemById(itemId) >= 0;
        } catch (Exception e) {
            status = "Loi mua " + itemId;
            return false;
        }
    }

    private static Item findShopItem(int shopType, int itemId) {
        if (shopType == 8) {
            return findInArray(GameScr.arrItemGrocery, itemId);
        }
        if (shopType == 9) {
            return findInArray(GameScr.arrItemGroceryLock, itemId);
        }
        return null;
    }

    private static Item findInArray(Item[] arr, int itemId) {
        if (arr == null) {
            return null;
        }
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != null && arr[i].template != null && arr[i].template.id == itemId) {
                return arr[i];
            }
        }
        return null;
    }

    private void resetEggRecheck() {
        this.noEggAt = 0L;
        this.eggRecheckCount = 0;
        this.eggRecheckStep = 0;
        this.returningHomeAfterNoEgg = false;
    }

    private void routeInHang(int targetMap) {
        if (TileMap.mapID == MAP_WAIT && targetMap == MAP_BOSS) {
            this.goWaitMapGate();
            return;
        }
        if (TileMap.mapID == MAP_BOSS && targetMap == MAP_WAIT) {
            this.goBossMapBackGate();
            return;
        }

        TileMap.direction(targetMap);
    }

    private static boolean isHangViThu(int map) {
        return map >= 169 && map <= 175;
    }

    private void goWaitMapGate() {
        int gate = findRightMostWaypoint();
        if (gate < 0) {
            status = "Khong thay cong 171->169";
            return;
        }

        short from = TileMap.mapID;
        status = "Vao cong 171->169";
        TileMap.j(gate);
        long start = System.currentTimeMillis();
        while (TileMap.mapID == from && System.currentTimeMillis() - start < 2500L) {
            Auto.sleep(120L);
        }
    }

    private void goBossMapBackGate() {
        int gate = findLeftMostWaypoint();
        if (gate < 0) {
            status = "Khong thay cong 169->171";
            return;
        }

        short from = TileMap.mapID;
        status = "Ra cong 169->171";
        TileMap.j(gate);
        long start = System.currentTimeMillis();
        while (TileMap.mapID == from && System.currentTimeMillis() - start < 2500L) {
            Auto.sleep(120L);
        }
    }

    private static int findRightMostWaypoint() {
        int result = -1;
        int bestX = -1;
        int bestDy = 999999;
        Char me = Char.getMyChar();
        int y = me != null ? me.cy : TileMap.d / 2;

        try {
            for (int i = 0; i < TileMap.vGo.size(); ++i) {
                Waypoint waypoint = (Waypoint) TileMap.vGo.elementAt(i);
                int rightX = waypoint.a > waypoint.c ? waypoint.a : waypoint.c;
                int midY = (waypoint.b + waypoint.d) / 2;
                int dy = Math.abs(midY - y);
                if (rightX > bestX || rightX == bestX && dy < bestDy) {
                    bestX = rightX;
                    bestDy = dy;
                    result = i;
                }
            }
        } catch (Exception e) {
        }

        return result;
    }

    private static int findLeftMostWaypoint() {
        int result = -1;
        int bestX = 999999;
        int bestDy = 999999;
        Char me = Char.getMyChar();
        int y = me != null ? me.cy : TileMap.d / 2;

        try {
            for (int i = 0; i < TileMap.vGo.size(); ++i) {
                Waypoint waypoint = (Waypoint) TileMap.vGo.elementAt(i);
                int leftX = waypoint.a < waypoint.c ? waypoint.a : waypoint.c;
                int midY = (waypoint.b + waypoint.d) / 2;
                int dy = Math.abs(midY - y);
                if (leftX < bestX || leftX == bestX && dy < bestDy) {
                    bestX = leftX;
                    bestDy = dy;
                    result = i;
                }
            }
        } catch (Exception e) {
        }

        return result;
    }

    private static void moveSafeInBossMap() {
        int x = 120;
        int y = TileMap.d(x, 384);
        Char me = Char.getMyChar();
        if (me != null && (Math.abs(me.cx - x) > 36 || Math.abs(me.cy - y) > 36)) {
            Char.charMove(x, y);
        }
    }

    private static Mob findMob(int id) {
        try {
            for (int i = 0; i < GameScr.vMobAttack.size(); ++i) {
                Mob mob = (Mob) GameScr.vMobAttack.elementAt(i);
                if (mob != null && mob.id == id && mob.hp > 0 && mob.h != 0 && mob.h != 1) {
                    return mob;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private void clearEggPickup() {
        this.eggTarget = null;
        this.eggStartAt = 0L;
        this.eggBefore = 0;
    }

    private static final class AutoOpenEggTask implements Runnable {
        public final void run() {
            Auto.sleep(1200L);
            try {
                while (openEggRunning) {
                    if (!openEggStep()) {
                        break;
                    }
                    Auto.sleep((long) OpenEggDelayMs);
                }
            } catch (Exception e) {
                openEggStatus = "Loi mo trung";
            }
            openEggRunning = false;
        }
    }

    private static boolean openEggStep() {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            openEggStatus = "Cho nhan vat";
            return true;
        }
        if (me.cHP <= 0 || me.statusMe == 14 || me.statusMe == 5) {
            openEggStatus = "Nhan vat dang chet";
            return true;
        }

        Item item;
        if (DeleteTimedEgg) {
            item = findTimedViThuResult();
            if (item != null) {
                if (deleteExactViThuResult(item)) {
                    ++openEggDeleted;
                    openEggStatus = "Xoa do co han " + item.template.id + " x" + openEggDeleted;
                } else {
                    openEggStatus = "Khong xoa duoc " + item.template.id;
                    return false;
                }
                return true;
            }
        }

        if (OpenForeverChildEgg) {
            item = findForeverChildEgg();
            if (item != null) {
                if (Char.countNullSlot() <= 0) {
                    openEggStatus = "Can trong 1 o mo trung phu";
                    return false;
                }
                if (useExactViThuItem(item)) {
                    ++openEggUsed;
                    openEggStatus = "Mo trung phu vv " + item.template.id;
                } else {
                    openEggStatus = "Loi mo trung phu";
                    return false;
                }
                return true;
            }
        }

        item = findBaseEgg();
        if (item != null) {
            if (Char.countNullSlot() <= 0) {
                openEggStatus = "Can trong 1 o mo trung 993";
                return false;
            }
            if (me.yen < 1000000) {
                openEggStatus = "Thieu 1m yen mo trung";
                return false;
            }
            if (useExactViThuItem(item)) {
                ++openEggUsed;
                openEggStatus = "Mo trung 993 lan " + openEggUsed;
            } else {
                openEggStatus = "Loi mo trung 993";
                return false;
            }
            return true;
        }

        openEggStatus = "Xong mo:" + openEggUsed + " giu:" + countForeverViThuResult() + " xoa:" + openEggDeleted;
        return false;
    }

    private static Item findBaseEgg() {
        return findBagItemById(ITEM_TRUNG_VI_THU);
    }

    private static Item findForeverChildEgg() {
        Item[] bag = Char.getMyChar().arrItemBag;
        for (int i = 0; i < bag.length; ++i) {
            Item item = bag[i];
            if (item != null && isChildEgg(item) && ensureItemInfo(item, EGG_INFO_TIMEOUT)) {
                item = getBagItem(item.indexUI);
                if (item != null && isChildEgg(item) && item.expires < 0L) {
                    return item;
                }
            }
        }
        return null;
    }

    private static Item findTimedViThuResult() {
        Item[] bag = Char.getMyChar().arrItemBag;
        for (int i = 0; i < bag.length; ++i) {
            Item item = bag[i];
            if (item != null && (isChildEgg(item) || isBijuuResult(item)) && ensureItemInfo(item, EGG_INFO_TIMEOUT)) {
                item = getBagItem(item.indexUI);
                if (item != null && (isChildEgg(item) || isBijuuResult(item)) && item.expires > 0L) {
                    return item;
                }
            }
        }
        return null;
    }

    private static int countForeverViThuResult() {
        int count = 0;
        try {
            Item[] bag = Char.getMyChar().arrItemBag;
            for (int i = 0; i < bag.length; ++i) {
                Item item = bag[i];
                if (item != null && (isChildEgg(item) || isBijuuResult(item)) && item.expires < 0L) {
                    ++count;
                }
            }
        } catch (Exception e) {
        }
        return count;
    }

    private static boolean useExactViThuItem(Item item) {
        Item cur = getBagItem(item.indexUI);
        if (cur == null || cur.template == null || cur.template.id != item.template.id) {
            return false;
        }
        GameScr.getInstance().closeDialog();
        Service.getInstance().useItem(cur.indexUI);
        Auto.sleep((long) OpenEggDelayMs);
        return true;
    }

    private static boolean deleteExactViThuResult(Item item) {
        Item cur = getBagItem(item.indexUI);
        if (cur == null || cur.template == null || cur.template.id != item.template.id) {
            return false;
        }
        if (!isChildEgg(cur) && !isBijuuResult(cur)) {
            return false;
        }
        if (!ensureItemInfo(cur, EGG_INFO_TIMEOUT)) {
            return false;
        }

        cur = getBagItem(item.indexUI);
        if (cur == null || cur.template == null || cur.template.id != item.template.id || cur.expires <= 0L) {
            return false;
        }

        int index = cur.indexUI;
        int id = cur.template.id;
        Service.getInstance().saleItem1(index, 1);
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 5000L) {
            Item now = getBagItem(index);
            if (now == null || now.template == null || now.template.id != id) {
                return true;
            }
            Auto.sleep(100L);
        }
        return false;
    }

    private static boolean ensureItemInfo(Item item, long timeout) {
        if (item == null) {
            return false;
        }
        long start = System.currentTimeMillis();
        long lastRequest = 0L;
        while (openEggRunning && System.currentTimeMillis() - start < timeout) {
            Item cur = getBagItem(item.indexUI);
            if (cur == null || cur.template == null || cur.template.id != item.template.id) {
                return false;
            }
            if (cur.expires != 0L) {
                return true;
            }

            long now = System.currentTimeMillis();
            if (now - lastRequest >= 700L) {
                Service.getInstance().requestItemInfo(cur.typeUI, cur.indexUI);
                lastRequest = now;
            }
            Auto.sleep(100L);
        }

        Item cur = getBagItem(item.indexUI);
        return cur != null && cur.template != null && cur.template.id == item.template.id && cur.expires != 0L;
    }

    private static Item findBagItemById(int itemId) {
        try {
            Item[] bag = Char.getMyChar().arrItemBag;
            for (int i = 0; i < bag.length; ++i) {
                if (bag[i] != null && bag[i].template != null && bag[i].template.id == itemId) {
                    return bag[i];
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static Item getBagItem(int index) {
        try {
            Item[] bag = Char.getMyChar().arrItemBag;
            if (index >= 0 && index < bag.length) {
                return bag[index];
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static boolean isChildEgg(Item item) {
        if (item == null || item.template == null) {
            return false;
        }
        int id = item.template.id;
        return id == ITEM_TAMAMIZU || id == ITEM_TAMAKIRO || id == ITEM_TAMAMURA || id == ITEM_TAMADAI;
    }

    private static boolean isBijuuResult(Item item) {
        if (item == null || item.template == null) {
            return false;
        }
        int id = item.template.id;
        return id >= 924 && id <= 941 || id >= 994 && id <= 1047;
    }

    private void finish(String text) {
        status = text;
        GameScr.chatPopup(text);
        if (AutoOpenEggAfterHang && text.indexOf("het trung") >= 0 && !openEggRunning) {
            startOpenEgg();
        }
        Code.backToInstance();
    }

    private static String formatTime(int hour, int minute) {
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
            dataout.writeBoolean(AttackBoss);
            dataout.writeBoolean(PickEgg);
            dataout.writeInt(Hour);
            dataout.writeInt(Minute);
            dataout.writeBoolean(AutoOpenEggAfterHang);
            dataout.writeBoolean(OpenForeverChildEgg);
            dataout.writeBoolean(DeleteTimedEgg);
            dataout.writeInt(OpenEggDelayMs);
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
                AttackBoss = datain.readBoolean();
                PickEgg = datain.readBoolean();
                Hour = datain.readInt();
                Minute = datain.readInt();
                if (datain.available() > 0) {
                    AutoOpenEggAfterHang = datain.readBoolean();
                }
                if (datain.available() > 0) {
                    OpenForeverChildEgg = datain.readBoolean();
                }
                if (datain.available() > 0) {
                    DeleteTimedEgg = datain.readBoolean();
                }
                if (datain.available() > 0) {
                    OpenEggDelayMs = datain.readInt();
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
        if (OpenEggDelayMs < MIN_OPEN_EGG_DELAY) {
            OpenEggDelayMs = MIN_OPEN_EGG_DELAY;
        }
    }

    public final String toString() {
        return "Auto Vi Thu " + status;
    }

    static {
        load();
    }
}
