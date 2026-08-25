
// Source code is decompiled from instance .class file using FernFlower decompiler.
public final class AutoNVHN extends Auto {

    public static boolean a = false;
    private static String[] b = new String[]{"Hôm nay con đã làm hết nhiệm vụ ta giao. Hãy quay lại vào ngày hôm sau..", "Đây là lần nhận nhiệm vụ thứ ", " trong ngày hôm nay. Mỗi ngày được nhận tối đa 20 lần con nhé."};
    private static int countNV;
    private TaskOrder task;

    public AutoNVHN() {
    }

    public static void a(String var0) {
        if (var0.equals(b[0])) {
            countNV = 21;
            LockGame.l();
        } else {
            int var1;
            if ((var1 = var0.indexOf(b[1])) >= 0) {
                var0 = var0.substring(var1 + b[1].length(), var0.indexOf(b[2])).trim();

                try {
                    countNV = Integer.parseInt(var0);
                } catch (NumberFormatException var3) {
                }
            }

        }
    }

    public final void a() {
        countNV = 0;
        this.task = Char.getTaskOrderById(0);
        super.a();
    }

    public final void b() {
        this.task = Char.getTaskOrderById(0);
    }

    public final void run() {
        if (countNV <= 20 && (!(super.instance instanceof Stanima) || System.currentTimeMillis() - super.o < 3600000L)) {
            if (super.isDead()) {
                Auto.autoRemap(false);
                return;
            }

            if (Auto.goTruongAndLuuToaDoIfNeeded()) {
                return;
            }

            if (TileMap.isLangCo(TileMap.mapID) || TileMap.isLangTT(TileMap.mapID)) {
                Auto.goTruongIfNeeded();
                return;
            }

            if (this.task == null && !TileMap.isTruong(TileMap.mapID)) {
                GameScr.chatPopup("Auto NVHN: ve truong");
                Auto.goTruongIfNeeded();
                return;
            }

            if (TileMap.isTruong(TileMap.mapID)) {
                if (this.task == null) {
                    GameScr.chatPopup("Nhận NV " + (countNV + 1) + "/20");
                    GameScr.PickNpc(25, GameScr.fj, 0);
                    LockGame.k();
                    this.task = Char.getTaskOrderById(0);
                    return;
                }

                if (this.task.count < this.task.maxCount) {
                    GameScr.chatPopup("Đi làm NV " + countNV + "/20");
                    GameScr.PickNpc(25, GameScr.fj, 3);
                    TileMap.g();
                    this.b(super.zoneID);
                    return;
                }

                if (Char.countNullSlot() <= 0) {
                    GameScr.chatPopup("Hành trang đầy");
                    return;
                }

                GameScr.chatPopup("Hoàn thành NV " + countNV + "/20");
                GameScr.PickNpc(25, GameScr.fj, 2);
                this.task = null;
            } else if (this.task != null && TileMap.mapID == this.task.mapId) {
                if (this.task.count >= this.task.maxCount) {
                    Auto.tuSat();
                    return;
                }

                this.attack(this.task.killId, 1);
                this.pickUpItem(-1);
                if (a) {
                    GameScr.chatPopup("Nhiệm vụ " + countNV + "/20: " + this.task.count + "/" + this.task.maxCount + " " + Mob.mobTemplates[this.task.killId].name);
                    a = false;
                }
            }
        } else {
            GameScr.chatPopup("Hoàn thành!");
            Code.backToInstance();
        }

    }

    public final String toString() {
        if (this.task == null) {
            return "Auto NVHN(" + countNV + "/20)";
        }

        return "Auto NVHN(" + countNV + "/20): " + Mob.mobTemplates[this.task.killId].name + "(" + this.task.count + "/" + this.task.maxCount + ")";
    }
}
