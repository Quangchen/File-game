import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class AutoGiftCode extends Auto {

    private static final String STORE_NAME = "AutoGiftCodeCfg";
    private static final String DEFAULT_CODES =
            "tanthu\n"
            + "chaomung\n"
            + "opensv\n"
            + "hoatuyet\n"
            + "nhamthach\n"
            + "phale\n"
            + "300tv2025\n"
            + "noelvuive\n"
            + "danhvong\n"
            + "newbie\n"
            + "giangsinhanlanh\n"
            + "500tv\n"
            + "happynewyear\n"
            + "ngaygiaret\n"
            + "tetnguyendan2024\n"
            + "trungvithu\n"
            + "30t41t5\n"
            + "gianghatay\n"
            + "nhagiaovietnam\n"
            + "sinhnhat1tuoi\n"
            + "happynewyear2025\n"
            + "gang4xc12tl9\n";

    public static int MapID = 72;
    public static int KhuID = -1;
    public static int NpcID = 30;
    public static String MenuPath = "1";
    public static int DelayMs = 2000;
    public static boolean AutoAfterLogin = false;
    public static boolean SkipAttempted = true;
    public static boolean StopWhenBagFull = true;

    private static boolean loaded = false;
    private static boolean running = false;
    private static String status = "Tat";
    private static String loginChar = "";
    private static String startedLoginChar = "";
    private static long loginReadyAt = 0L;

    public static void start() {
        load();
        if (running) {
            GameScr.chatPopup("Auto giftcode dang chay");
            return;
        }
        Code.setAuto(new AutoGiftCode());
    }

    public static void stop() {
        if (!running) {
            return;
        }
        running = false;
        GameScr.chatPopup("Dung auto giftcode");
    }

    public static boolean isRunning() {
        return running;
    }

    public static String getStatusText() {
        return running ? status : "Tat";
    }

    public static int getCodeCount() {
        return loadCodes().size();
    }

    public static void updateAutoStartAfterLogin() {
        try {
            load();
            if (!AutoAfterLogin || running || Code.auto instanceof AutoGiftCode) {
                return;
            }

            Char me = Char.getMyChar();
            if (me == null || me.charName == null || me.charName.equals("") || me.arrItemBag == null) {
                return;
            }

            String name = me.charName;
            long now = System.currentTimeMillis();
            if (!name.equals(loginChar)) {
                loginChar = name;
                loginReadyAt = now + 10000L;
                startedLoginChar = "";
                return;
            }

            if (now < loginReadyAt || name.equals(startedLoginChar)) {
                return;
            }

            if (!hasPendingCode()) {
                startedLoginChar = name;
                return;
            }

            startedLoginChar = name;
            GameScr.chatPopup("Auto giftcode sau login");
            start();
        } catch (Exception e) {
        }
    }

    public static void clearHistory() {
        try {
            RMS.writeRecord(historyKey(), "");
            GameScr.chatPopup("Da xoa lich su giftcode");
        } catch (Exception e) {
        }
    }

    public static boolean hasPendingCode() {
        try {
            MyVector codes = loadCodes();
            String history = loadHistory();
            for (int i = 0; i < codes.size(); i++) {
                String code = (String) codes.elementAt(i);
                if (!SkipAttempted || !isAttempted(history, code)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public final void run() {
        if (running) {
            return;
        }

        running = true;
        int sent = 0;
        int skipped = 0;
        try {
            load();
            MyVector codes = loadCodes();
            if (codes.size() == 0) {
                GameScr.chatPopup("giftcodes.txt rong");
                return;
            }

            String history = loadHistory();
            for (int i = 0; running && i < codes.size(); i++) {
                String code = (String) codes.elementAt(i);
                if (code == null || code.length() == 0) {
                    continue;
                }

                if (SkipAttempted && isAttempted(history, code)) {
                    skipped++;
                    continue;
                }

                if (StopWhenBagFull && Char.countNullSlot() <= 0) {
                    GameScr.chatPopup("Giftcode: full hanh trang");
                    break;
                }

                status = "Giftcode " + (i + 1) + "/" + codes.size() + ": " + code;
                GameScr.chatPopup(status);

                if (!submitGiftCode(code)) {
                    GameScr.chatPopup("Giftcode loi: " + code);
                    break;
                }

                sent++;
                history = markAttempted(history, code);
                sleepMs((long) DelayMs);
                closePopups();
            }

            GameScr.chatPopup("Giftcode xong: " + sent + " code, bo qua " + skipped);
        } catch (Exception e) {
            GameScr.chatPopup("Loi auto giftcode");
        } finally {
            status = "Tat";
            running = false;
            if (Code.auto == this) {
                Code.backToInstance();
            }
        }
    }

    private static boolean submitGiftCode(String code) {
        try {
            if (!ensureNpcReady()) {
                return false;
            }

            Npc npc = GameScr.findNpc(NpcID);
            if (npc == null) {
                return false;
            }

            Char.charMove(npc.cx, npc.cy);
            waitMove(npc.cx, npc.cy, 5000L);
            Char.getMyChar().npcFocus = npc;

            closePopups();
            Service.getInstance().openMenu(NpcID);
            sleepMs((long) DelayMs);

            int[] path = parsePath(MenuPath);
            if (path == null || path.length == 0) {
                return false;
            }

            for (int i = 0; running && i < path.length; i++) {
                Service.getInstance().menu(NpcID, path[i], 0);
                sleepMs((long) DelayMs);
            }

            if (!waitInputDialog((long) DelayMs * 10L + 3000L)) {
                return false;
            }

            GameCanvas.ak.tfInput.a(code);
            sleepMs((long) DelayMs);
            if (GameCanvas.ak.center != null) {
                GameCanvas.ak.center.a();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean ensureNpcReady() {
        try {
            if (GameScr.findNpc(NpcID) != null) {
                return true;
            }

            if (MapID >= 0 && TileMap.mapID != MapID) {
                GameScr.chatPopup("Giftcode go map " + MapID);
                TileMap.direction(MapID);
                if (!waitMap(MapID, 30000L)) {
                    return false;
                }
            }

            if (KhuID >= 0 && TileMap.zoneID != KhuID) {
                Service.getInstance().requestChangeZone(KhuID, -1);
                if (!waitZone(KhuID, 8000L)) {
                    return false;
                }
            }

            return waitNpc(NpcID, 7000L) != null;
        } catch (Exception e) {
            return false;
        }
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

    private static boolean waitMap(int map, long timeout) {
        long start = System.currentTimeMillis();
        while (running && TileMap.mapID != map && System.currentTimeMillis() - start < timeout) {
            sleepMs(250L);
        }
        return TileMap.mapID == map;
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
            if (me != null && Math.abs(me.cx - tx) <= 24 && Math.abs(me.cy - ty) <= 24) {
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
            GameScr game = GameScr.getInstance();
            if (game != null) {
                game.closeDialog();
                game.resetButton();
            }
        } catch (Exception e) {
        }
    }

    private static int[] parsePath(String text) {
        try {
            if (text == null) {
                return null;
            }
            text = text.trim();
            if (text.equals("")) {
                return null;
            }

            String[] arr = Code.splitString(text, ",");
            int[] result = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                result[i] = Integer.parseInt(arr[i].trim());
                if (result[i] < 0) {
                    return null;
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private static MyVector loadCodes() {
        MyVector result = new MyVector();
        String text = Code.h("giftcodes.txt");
        if (text == null || text.trim().equals("")) {
            text = DEFAULT_CODES;
        }

        StringBuffer current = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '\n' || c == '\r' || c == ',' || c == ';' || c == ' ' || c == '\t') {
                addCode(result, current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        addCode(result, current.toString());
        return result;
    }

    private static void addCode(MyVector result, String code) {
        if (code == null) {
            return;
        }
        code = code.trim().toLowerCase();
        if (code.equals("") || code.startsWith("#")) {
            return;
        }
        for (int i = 0; i < result.size(); i++) {
            if (code.equals(result.elementAt(i))) {
                return;
            }
        }
        result.addElement(code);
    }

    private static String historyKey() {
        try {
            Char me = Char.getMyChar();
            String name = me == null || me.charName == null ? "none" : me.charName;
            return "AGC" + name.hashCode();
        } catch (Exception e) {
            return "AGC0";
        }
    }

    private static String loadHistory() {
        String s = RMS.loadRMSString(historyKey());
        return s == null ? "" : s;
    }

    private static boolean isAttempted(String history, String code) {
        return history != null && code != null && history.indexOf("|" + code + "|") >= 0;
    }

    private static String markAttempted(String history, String code) {
        try {
            if (history == null) {
                history = "";
            }
            if (!isAttempted(history, code)) {
                history = history + "|" + code + "|";
                RMS.writeRecord(historyKey(), history);
            }
        } catch (Exception e) {
        }
        return history;
    }

    public static void save() {
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeInt(MapID);
            dataout.writeInt(KhuID);
            dataout.writeInt(NpcID);
            dataout.writeUTF(MenuPath == null ? "" : MenuPath);
            dataout.writeInt(DelayMs);
            dataout.writeBoolean(AutoAfterLogin);
            dataout.writeBoolean(SkipAttempted);
            dataout.writeBoolean(StopWhenBagFull);
            dataout.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            dataout.close();
            byteout.close();
            loaded = true;
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
                MapID = datain.readInt();
                KhuID = datain.readInt();
                NpcID = datain.readInt();
                MenuPath = datain.readUTF();
                DelayMs = datain.readInt();
                AutoAfterLogin = datain.readBoolean();
                SkipAttempted = datain.readBoolean();
                StopWhenBagFull = datain.readBoolean();
                datain.close();
                bytein.close();
            }
        } catch (Exception e) {
        }

        if (DelayMs < 1000) {
            DelayMs = 1000;
        }
        if (MenuPath == null || MenuPath.trim().equals("")) {
            MenuPath = "1";
        }
        if (NpcID < 0) {
            NpcID = 30;
        }
        loaded = true;
    }

    private static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    public final String toString() {
        return getStatusText();
    }

    static {
        load();
    }
}
