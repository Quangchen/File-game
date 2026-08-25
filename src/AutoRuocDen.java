import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;

public final class AutoRuocDen extends Auto {

    private static final String STORE_NAME = "AutoRuocDenCfg";
    private static final int ITEM_HOP_DIEM = 310;
    private static final int ITEM_PHOI_LONG_DEN = 1221;
    private static final int NPC_LONG_DEN = 34;
    private static final int DEFAULT_MAP = 26;
    private static final int MENU_RUOC_DEN = 0;
    private static final long MOVE_DELAY = 350L;
    private static final long ROUTE_DELAY = 3000L;
    private static final long PICK_DELAY = 450L;

    public static boolean AutoTime = false;
    public static boolean RepeatWhileHasMatch = false;
    public static boolean PickDroppedPhoi = true;
    public static int MapID = DEFAULT_MAP;
    public static int ZoneID = -1;
    public static int Hour = 20;
    public static int Minute = 0;
    public static int MatchCount = 1;
    public static int DelayMs = 700;
    public static int MaxEscortMinute = 10;

    private static boolean loaded = false;
    private static boolean running = false;
    private static String status = "Tắt";
    private static int lastScheduleKey = -1;
    private static long lastScheduleCheck = 0L;

    private long lastMoveAt;
    private long lastRouteAt;
    private long lastPickAt;
    private int startPhoi;
    private int npcX;
    private int npcY;
    private int targetX;
    private int lanternID;

    public static void start() {
        load();
        if (running || Code.auto instanceof AutoRuocDen) {
            GameScr.chatPopup("Auto rước đèn đang chạy");
            return;
        }

        AutoRuocDen auto = new AutoRuocDen();
        auto.init();
        Code.setAuto(auto);
        GameScr.chatPopup("Bật auto rước đèn");
    }

    public static void toggle() {
        if (running || Code.auto instanceof AutoRuocDen) {
            stop();
        } else {
            start();
        }
    }

    public static void stop() {
        running = false;
        if (Code.auto instanceof AutoRuocDen) {
            Code.backToInstance();
        }
        status = "Tắt";
        GameScr.chatPopup("Dừng auto rước đèn");
    }

    public static boolean isRunning() {
        return running || Code.auto instanceof AutoRuocDen;
    }

    public static boolean isBusy() {
        return isRunning();
    }

    public static String getStatusText() {
        load();
        return isRunning() ? status : "Tắt";
    }

    public static String getAutoText() {
        load();
        if (Code.auto instanceof AutoRuocDen) {
            return "";
        }
        if (running) {
            return "RĐ: " + status;
        }
        if (AutoTime) {
            return "RĐ: " + formatTime(Hour, Minute) + " map" + MapID;
        }
        return "";
    }

    public static void updateSchedule() {
        try {
            load();
            if (!AutoTime || running || Code.auto instanceof AutoRuocDen || !(GameCanvas.mScreen instanceof GameScr)) {
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
            if (hour != Hour || minute != Minute || lastScheduleKey == key) {
                return;
            }

            if (Char.k(ITEM_HOP_DIEM) <= 0) {
                status = "Đến giờ nhưng thiếu hộp diêm";
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

    private void init() {
        super.a();
        super.mapID = MapID;
        super.zoneID = ZoneID;
        super.g = true;
        this.lastMoveAt = 0L;
        this.lastRouteAt = 0L;
        this.lastPickAt = 0L;
        this.startPhoi = 0;
        this.npcX = -1;
        this.npcY = -1;
        this.targetX = getTargetX(MapID);
        this.lanternID = 0;
        status = "Khởi động";
    }

    protected final void run() {
        if (running) {
            return;
        }

        running = true;
        try {
            load();
            do {
                if (!runOneRound()) {
                    break;
                }
                sleepMs(1000L);
            } while (running && RepeatWhileHasMatch && Char.k(ITEM_HOP_DIEM) >= getMatchCount());
        } catch (Exception e) {
            status = "Lỗi auto rước đèn";
            sleepMs(1000L);
        } finally {
            running = false;
            status = "Tắt";
            if (Code.auto == this) {
                Code.backToInstance();
            }
        }
    }

    private boolean runOneRound() {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            status = "Chờ nhân vật";
            return false;
        }
        if (!me.isHuman) {
            status = "Chỉ chạy ở chủ thân";
            return false;
        }
        if (me.cLevel < 20) {
            status = "Cần cấp 20";
            GameScr.chatPopup(status);
            return false;
        }
        if (Char.k(ITEM_HOP_DIEM) < getMatchCount()) {
            status = "Thiếu hộp diêm 310";
            GameScr.chatPopup(status);
            return false;
        }

        this.startPhoi = Char.k(ITEM_PHOI_LONG_DEN);
        if (!ensureMapAndNpc()) {
            return false;
        }
        if (!startEscort()) {
            return false;
        }

        return followLantern();
    }

    private boolean ensureMapAndNpc() {
        long start = System.currentTimeMillis();
        while (running && GameCanvas.mScreen instanceof GameScr && System.currentTimeMillis() - start < 60000L) {
            if (TileMap.mapID != MapID) {
                long now = System.currentTimeMillis();
                status = "Đi map " + MapID;
                if (!TileMap.ag && now - this.lastRouteAt >= ROUTE_DELAY) {
                    this.lastRouteAt = now;
                    routeToEscortMap();
                }
                sleepMs(300L);
                continue;
            }

            if (ZoneID >= 0 && TileMap.zoneID != ZoneID) {
                status = "Đổi khu " + ZoneID;
                Service.getInstance().requestChangeZone(ZoneID, -1);
                if (!waitZone(ZoneID, 10000L)) {
                    return false;
                }
            }

            Npc npc = waitNpc(NPC_LONG_DEN, 10000L);
            if (npc == null) {
                status = "Không thấy NPC lồng đèn";
                GameScr.chatPopup(status);
                return false;
            }

            this.npcX = npc.cx;
            this.npcY = npc.cy;
            this.targetX = getTargetX(TileMap.mapID);
            return true;
        }

        status = "Không đi được map " + MapID;
        GameScr.chatPopup(status);
        return false;
    }

    private boolean startEscort() {
        try {
            Npc npc = GameScr.findNpc(NPC_LONG_DEN);
            if (npc == null) {
                return false;
            }

            status = "Mở NPC rước đèn";
            moveNear(npc.cx, npc.cy, true);
            waitMove(npc.cx, npc.cy, 6000L);
            Char.getMyChar().npcFocus = npc;

            closePopups();
            Service.getInstance().openMenu(NPC_LONG_DEN);
            sleepMs((long) getDelayMs());
            Service.getInstance().menu(NPC_LONG_DEN, MENU_RUOC_DEN, 0);

            if (!waitInputDialog((long) getDelayMs() * 10L + 5000L)) {
                status = "Không thấy ô nhập số lượng";
                GameScr.chatPopup(status);
                return false;
            }

            GameCanvas.ak.tfInput.a(String.valueOf(getMatchCount()));
            sleepMs((long) getDelayMs());
            if (GameCanvas.ak.center != null) {
                GameCanvas.ak.center.a();
            }

            closePopups();
            status = "Đã thắp đèn";
            return waitLanternBot(12000L) != null;
        } catch (Exception e) {
            status = "Lỗi mở NPC rước đèn";
            return false;
        }
    }

    private boolean followLantern() {
        long start = System.currentTimeMillis();
        long lastSeen = System.currentTimeMillis();
        long timeout = (long) MaxEscortMinute * 60000L;
        if (timeout < 60000L) {
            timeout = 60000L;
        }

        while (running && GameCanvas.mScreen instanceof GameScr && System.currentTimeMillis() - start < timeout) {
            Char lantern = findLanternBot();
            if (lantern != null) {
                lastSeen = System.currentTimeMillis();
                this.lanternID = lantern.charID;
                status = "Rước đèn map " + TileMap.mapID + " khu " + TileMap.zoneID;
                followChar(lantern);
            } else {
                if (PickDroppedPhoi) {
                    pickDroppedPhoi();
                }

                if (System.currentTimeMillis() - lastSeen >= 8000L) {
                    int gain = Char.k(ITEM_PHOI_LONG_DEN) - this.startPhoi;
                    status = "Kết thúc rước, phôi +" + gain;
                    GameScr.chatPopup(status);
                    return gain > 0 || !RepeatWhileHasMatch;
                }
            }

            if (PickDroppedPhoi) {
                pickDroppedPhoi();
            }
            sleepMs(250L);
        }

        status = "Hết thời gian rước";
        GameScr.chatPopup(status);
        return false;
    }

    private Char waitLanternBot(long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Char c = findLanternBot();
            if (c != null) {
                this.lanternID = c.charID;
                return c;
            }
            sleepMs(150L);
        }
        return null;
    }

    private Char findLanternBot() {
        try {
            Char best = null;
            int bestDistance = 2147483647;
            for (int i = 0; i < GameScr.vCharInMap.size(); ++i) {
                Char c = (Char) GameScr.vCharInMap.elementAt(i);
                if (c == null || c == Char.getMyChar()) {
                    continue;
                }

                if (this.lanternID != 0 && c.charID == this.lanternID) {
                    return c;
                }

                if (!isLanternCandidate(c)) {
                    continue;
                }

                int distance = Math.abs(c.cx - this.npcX) + Math.abs(c.cy - this.npcY);
                if (best == null || distance < bestDistance) {
                    best = c;
                    bestDistance = distance;
                }
            }
            return best;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isLanternCandidate(Char c) {
        if (c.charID >= 0) {
            return false;
        }

        if (c.charName != null) {
            String name = c.charName.toLowerCase();
            if (name.indexOf("den") >= 0 || name.indexOf("đèn") >= 0 || name.indexOf("lồng") >= 0 || name.indexOf("long") >= 0) {
                return true;
            }
        }

        return this.npcX >= 0 && Math.abs(c.cx - this.npcX) <= 220 && Math.abs(c.cy - this.npcY) <= 120;
    }

    private void followChar(Char lantern) {
        Char me = Char.getMyChar();
        if (me == null || lantern == null) {
            return;
        }

        int dir = this.targetX >= this.npcX ? 1 : -1;
        int x = lantern.cx - dir * 24;
        if (x < 24) {
            x = 24;
        }
        if (x > TileMap.c - 24) {
            x = TileMap.c - 24;
        }
        int y = TileMap.d(x, lantern.cy);
        int distance = Math.abs(me.cx - lantern.cx) + Math.abs(me.cy - lantern.cy);
        if (distance >= 42 || Math.abs(me.cx - x) > 20 || Math.abs(me.cy - y) > 30) {
            moveNear(x, y, false);
        }
    }

    private void moveNear(int x, int y, boolean force) {
        long now = System.currentTimeMillis();
        if (!force && now - this.lastMoveAt < MOVE_DELAY) {
            return;
        }

        this.lastMoveAt = now;
        int[] pos = new int[2];
        if (TileMap.a(x, y, pos)) {
            x = pos[0];
            y = pos[1];
        } else {
            y = TileMap.d(x, y);
        }
        Char.charMove(x, y);
    }

    private void pickDroppedPhoi() {
        try {
            long now = System.currentTimeMillis();
            if (now - this.lastPickAt < PICK_DELAY || GameScr.vItemMap == null) {
                return;
            }

            ItemMap best = null;
            int bestDistance = 2147483647;
            Char me = Char.getMyChar();
            for (int i = 0; i < GameScr.vItemMap.size(); ++i) {
                ItemMap item = (ItemMap) GameScr.vItemMap.elementAt(i);
                if (item == null || item.template == null || item.template.id != ITEM_PHOI_LONG_DEN || item.isPickedUp) {
                    continue;
                }

                int distance = me == null ? 0 : Math.abs(me.cx - item.x) + Math.abs(me.cy - item.y);
                if (best == null || distance < bestDistance) {
                    best = item;
                    bestDistance = distance;
                }
            }

            if (best == null) {
                return;
            }

            this.lastPickAt = now;
            if (me != null && bestDistance > 48) {
                moveNear(best.x, best.y, true);
                return;
            }
            Service.getInstance().pickItem(best.itemMapID);
        } catch (Exception e) {
        }
    }

    private static Npc waitNpc(int npcID, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Npc npc = GameScr.findNpc(npcID);
            if (npc != null) {
                return npc;
            }
            sleepMs(100L);
        }
        return null;
    }

    private static boolean waitInputDialog(long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            try {
                if (GameCanvas.currentDialog == GameCanvas.ak
                        && GameCanvas.ak != null
                        && GameCanvas.ak.tfInput != null
                        && GameCanvas.ak.center != null) {
                    return true;
                }
            } catch (Exception e) {
            }
            sleepMs(50L);
        }
        return false;
    }

    private static boolean waitZone(int zone, long timeout) {
        long start = System.currentTimeMillis();
        while (running && TileMap.zoneID != zone && System.currentTimeMillis() - start < timeout) {
            sleepMs(150L);
        }
        return TileMap.zoneID == zone;
    }

    private static void waitMove(int tx, int ty, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Char me = Char.getMyChar();
            if (me != null && Math.abs(me.cx - tx) <= 36 && Math.abs(me.cy - ty) <= 36) {
                return;
            }
            sleepMs(100L);
        }
    }

    private static void closePopups() {
        try {
            GameCanvas.setMaxTextLenght();
            GameCanvas.currentDialog = null;
            GameCanvas.e = false;
            GameScr.hideCommandBoxUi();
            GameScr game = GameScr.getInstance();
            if (game != null) {
                game.closeDialog();
                game.resetButton();
            }
        } catch (Exception e) {
        }
    }

    private static int getMatchCount() {
        load();
        if (MatchCount < 1) {
            MatchCount = 1;
        }
        if (MatchCount > 1000) {
            MatchCount = 1000;
        }
        return MatchCount;
    }

    private static int getDelayMs() {
        load();
        return DelayMs < 500 ? 500 : DelayMs;
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

    private static int getTargetX(int map) {
        switch (map) {
            case 2:
                return 1380;
            case 3:
                return 60;
            case 26:
                return 3516;
            case 28:
                return 60;
            case 39:
                return 1757;
            case 71:
                return 1620;
        }
        return 3516;
    }

    public static void save() {
        try {
            normalize();
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(AutoTime);
            dataout.writeBoolean(RepeatWhileHasMatch);
            dataout.writeBoolean(PickDroppedPhoi);
            dataout.writeInt(MapID);
            dataout.writeInt(ZoneID);
            dataout.writeInt(Hour);
            dataout.writeInt(Minute);
            dataout.writeInt(MatchCount);
            dataout.writeInt(DelayMs);
            dataout.writeInt(MaxEscortMinute);
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
                RepeatWhileHasMatch = datain.readBoolean();
                PickDroppedPhoi = datain.readBoolean();
                MapID = datain.readInt();
                ZoneID = datain.readInt();
                Hour = datain.readInt();
                Minute = datain.readInt();
                MatchCount = datain.readInt();
                DelayMs = datain.readInt();
                if (datain.available() > 0) {
                    MaxEscortMinute = datain.readInt();
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
        if (!isEscortMap(MapID)) {
            MapID = DEFAULT_MAP;
        }
        if (ZoneID < -1) {
            ZoneID = -1;
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
        if (MatchCount < 1) {
            MatchCount = 1;
        }
        if (MatchCount > 1000) {
            MatchCount = 1000;
        }
        if (DelayMs < 500) {
            DelayMs = 500;
        }
        if (MaxEscortMinute < 1) {
            MaxEscortMinute = 1;
        }
    }

    private static boolean isEscortMap(int map) {
        return map == 2 || map == 3 || map == 26 || map == 28 || map == 39 || map == 71;
    }

    private static void routeToEscortMap() {
        if (TileMap.isVDMQ(TileMap.mapID)
                || TileMap.isLangCo(TileMap.mapID)
                || TileMap.isLangTT(TileMap.mapID)) {
            Auto.goTruongIfNeeded();
            return;
        }

        TileMap.direction(MapID);
    }

    private static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    public final String toString() {
        return "Auto Rước Đèn " + status;
    }

    static {
        load();
    }
}
