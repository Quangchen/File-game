
public final class AutoNpc extends Auto {

    public static boolean running = false;

    private final int mapID;
    private final int khuID;
    private final int npcID;
    private final String duongDanNut;
    private final String giaTriNhap;
    private final int soLan;
    private final int delay;

    public AutoNpc(int mapID, int khuID, int npcID, String duongDanNut, String giaTriNhap, int soLan, int delay) {
        this.mapID = mapID;
        this.khuID = khuID;
        this.npcID = npcID;
        this.duongDanNut = duongDanNut;
        this.giaTriNhap = giaTriNhap == null ? "" : giaTriNhap.trim();
        this.soLan = soLan < 1 ? 1 : soLan;
        this.delay = delay < 20 ? 20 : delay;
    }

    public final void run() {
        if (running) {
            GameScr.chatPopup("Auto NPC đang chạy");
            return;
        }
        if (super.isDead()) {
            Auto.autoRemap(true);
            return;
        }
        if (!this.isLDGTInternalNpc() && !TileMap.isClanDun()) {
            if (Auto.goTruongIfNeeded()) {
                return;
            }
            Auto.sleep(100L);
        }

        running = true;
        try {

            if (!goToConfiguredMap()) {
                GameScr.chatPopup("Không đến được map/khu đã cài");
                return;
            }

            int[] path = parsePath4(this.duongDanNut);

            if (path == null || path.length == 0) {
                GameScr.chatPopup("Đường dẫn nút không hợp lệ");
                return;
            }

            for (int lap = 0; lap < this.soLan && running; ++lap) {
                Npc npc = waitNpc(this.npcID, 5000L);

                if (npc == null) {
                    GameScr.chatPopup("Không tìm thấy NPC ID " + this.npcID);
                    return;
                }

                Char.charMove(npc.cx, npc.cy);
                waitMove(npc.cx, npc.cy, 5000L);

                Char.getMyChar().npcFocus = npc;

                Service.getInstance().openMenu(this.npcID);

                Thread.sleep((long) this.delay);

                clickPathStepByStep(this.npcID, path, this.delay);
                submitInputIfPresent(this.giaTriNhap, this.delay);

                if (lap < this.soLan - 1) {
                    Thread.sleep((long) this.delay);
                }
            }

            GameScr.chatPopup("Auto NPC xong");
        } catch (Exception e) {
            GameScr.chatPopup("Lỗi Auto NPC");
        } finally {
            running = false;
            Code.backToInstance();
        }
    }

    private boolean goToConfiguredMap() {
        try {

            if (TileMap.mapID != this.mapID) {
                if (this.isLDGTInternalNpc()) {
                    return false;
                }

                GameScr.chatPopup("Đang go map " + this.mapID);

                TileMap.direction(this.mapID);

                long tMap = System.currentTimeMillis();

                while (System.currentTimeMillis() - tMap < 30000L) {
                    if (TileMap.mapID == this.mapID) {
                        break;
                    }

                    Sleep(200L);
                }

                if (TileMap.mapID != this.mapID) {
                    return false;
                }
            }
            if (!this.isLDGTInternalNpc() && this.khuID >= 0 && TileMap.zoneID != this.khuID) {
                GameScr.chatPopup("Đổi khu " + this.khuID);

                Service.getInstance().requestChangeZone(this.khuID, -1);

                long tZone = System.currentTimeMillis();

                while (System.currentTimeMillis() - tZone < 8000L) {
                    if (TileMap.zoneID == this.khuID) {
                        break;
                    }

                    Sleep(150L);
                }

                if (TileMap.zoneID != this.khuID) {
                    return false;
                }
            }

            return TileMap.mapID == this.mapID
                    && (this.isLDGTInternalNpc() || this.khuID < 0 || TileMap.zoneID == this.khuID);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isLDGTInternalNpc() {
        if (this.mapID < 80 || this.mapID > 90) {
            return false;
        }

        Auto parent = this.instance;

        while (parent != null) {
            if (parent instanceof AutoLDGT) {
                return true;
            }

            parent = parent.instance;
        }

        return false;
    }

    private static void clickPathStepByStep(int npcID, int[] path, int delay) throws Exception {
        for (int i = 0; i < path.length && running; ++i) {
            Service.getInstance().menu(npcID, path[i], 0);

            if (i < path.length - 1) {
                Thread.sleep((long) delay);
            }
        }
    }

    private static void submitInputIfPresent(String giaTriNhap, int delay) {
        if (giaTriNhap == null || giaTriNhap.trim().equals("")) {
            return;
        }

        long timeout = (long) delay * 20L;
        if (timeout < 3000L) {
            timeout = 3000L;
        }

        if (!waitInputDialog(timeout)) {
            return;
        }

        try {
            GameCanvas.ak.tfInput.a(giaTriNhap.trim());

            Sleep((long) delay);

            if (GameCanvas.ak.center != null) {
                GameCanvas.ak.center.a();
            }

            Sleep((long) delay);

            GameCanvas.currentDialog = null;
        } catch (Exception e) {
            GameScr.chatPopup("Lỗi xác nhận nhập liệu");
        }
    }

    private static boolean waitInputDialog(long timeout) {
        long t = System.currentTimeMillis();

        while (running && System.currentTimeMillis() - t < timeout) {
            try {
                if (GameCanvas.currentDialog == GameCanvas.ak
                        && GameCanvas.ak != null
                        && GameCanvas.ak.tfInput != null
                        && GameCanvas.ak.center != null) {
                    return true;
                }
            } catch (Exception e) {
            }

            Sleep(50L);
        }

        return false;
    }

    private static int[] parsePath4(String s) {
        try {
            if (s == null) {
                return null;
            }

            String text = s.trim();

            if (text.equals("")) {
                return null;
            }

            String[] arr = split(text, ',');

            if (arr == null || arr.length == 0 || arr.length > 4) {
                return null;
            }

            int[] rs = new int[arr.length];

            for (int i = 0; i < arr.length; ++i) {
                rs[i] = Integer.parseInt(arr[i].trim());

                if (rs[i] < 0) {
                    return null;
                }
            }

            return rs;
        } catch (Exception e) {
            return null;
        }
    }

    private static String[] split(String s, char c) {
        int count = 1;
        int i;

        for (i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++count;
            }
        }

        String[] arr = new String[count];
        int start = 0;
        int index = 0;

        for (i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                arr[index++] = s.substring(start, i);
                start = i + 1;
            }
        }

        arr[index] = s.substring(start);

        return arr;
    }

    private static Npc waitNpc(int npcID, long timeout) {
        long t = System.currentTimeMillis();

        while (System.currentTimeMillis() - t < timeout) {
            Npc npc = GameScr.findNpc(npcID);

            if (npc != null) {
                return npc;
            }

            Sleep(100L);
        }

        return null;
    }

    private static void waitMove(int tx, int ty, long timeout) {
        long t = System.currentTimeMillis();

        while (System.currentTimeMillis() - t < timeout) {
            Char c = Char.getMyChar();

            if (c != null && Math.abs(c.cx - tx) <= 24 && Math.abs(c.cy - ty) <= 24) {
                return;
            }

            Sleep(100L);
        }
    }

    private static void Sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    public static void startFormConfig() {
        Code.setAuto(new AutoNpc(
                FormAutoNpc.MapID,
                FormAutoNpc.KhuID,
                FormAutoNpc.NpcID,
                FormAutoNpc.DuongDanNut,
                FormAutoNpc.GiaTriNhap,
                FormAutoNpc.SoLan,
                FormAutoNpc.Delay
        ));
    }

    public static void stopAuto() {
        running = false;
    }

    private static final String ANPCGT_PREFIX = "anpcgt;";

    public static void callClanFromForm() {
        try {
            if (Char.getMyChar().ctypeClan != 4) {
                GameScr.chatPopup("Chỉ tộc trưởng mới call");
                return;
            }

            String path = FormAutoNpc.DuongDanNut == null ? "" : FormAutoNpc.DuongDanNut.trim();
            String input = FormAutoNpc.GiaTriNhap == null ? "" : FormAutoNpc.GiaTriNhap.trim();

            int soLan = FormAutoNpc.SoLan;
            int delay = FormAutoNpc.Delay;

            String text = ANPCGT_PREFIX
                    + FormAutoNpc.MapID + ";"
                    + FormAutoNpc.KhuID + ";"
                    + FormAutoNpc.NpcID + ";"
                    + path + ";"
                    + input + ";"
                    + soLan + ";"
                    + delay;

            Service.getInstance().m(text);

            GameScr.chatPopup("Đã call AutoNpc clan");

        } catch (Exception e) {
            GameScr.chatPopup("Lỗi call anpcgt");
        }
    }

    public static boolean onClanChat(String name, String text) {
        try {
            if (text == null || !text.startsWith(ANPCGT_PREFIX)) {
                return false;
            }

            if (name != null && name.equals(Char.getMyChar().charName)) {
                return true;
            }

            String data = text.substring(ANPCGT_PREFIX.length());
            String[] arr = split(data, ';');

            if (arr == null || arr.length < 7) {
                GameScr.chatPopup("Sai dữ liệu anpcgt");
                return true;
            }

            int mapID = Integer.parseInt(arr[0]);
            int khuID = Integer.parseInt(arr[1]);
            int npcID = Integer.parseInt(arr[2]);
            String path = arr[3];
            String input = arr[4];
            int soLan = Integer.parseInt(arr[5]); // 👈 lấy từ tộc trưởng
            int delay = Integer.parseInt(arr[6]);

            Code.setAuto(new AutoNpc(
                    mapID,
                    khuID,
                    npcID,
                    path,
                    input,
                    soLan,
                    delay
            ));

            GameScr.chatPopup("Nhận AutoNpc (" + soLan + " lần)");

            return true;

        } catch (Exception e) {
            GameScr.chatPopup("Lỗi nhận anpcgt");
            return true;
        }
    }

    public final String toString() {
        return "Auto NPC(" + this.npcID + ") " + this.soLan + " lần";
    }
}
