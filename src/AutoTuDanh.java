
// Source code is decompiled from instance .class file using FernFlower decompiler.
public final class AutoTuDanh extends Auto {

    public AutoTuDanh() {
    }

    public final void a() {
        super.a();
        super.mapID = TileMap.mapID;
        super.zoneID = TileMap.zoneID;
        super.k = Char.getMyChar().cx;
        super.l = Char.getMyChar().cy;
    }

    public final void run() {
        if (super.isDead()) {
            if (Char.tickReMap) {
                Auto.autoRemap(true);
                return;
            }
        } else {
            if (!Auto.q && Char.getMyChar().isHuman) {
                this.l();
                return;
            }

            if (super.mapID == TileMap.mapID && (super.isHang || super.zoneID == TileMap.zoneID)) {
                this.pickUpItem(-1);
                this.attack(-1, -1);
                return;
            }

            if (Char.tickReMap) {
                this.goMap(super.mapID, super.zoneID, super.k, super.l);
            }
        }

    }

    protected final Mob a(Char var1, int var2, int var3, Char var4, boolean var5) {
        if (Code.attackChangePosition && Code.s.size() > 0) {
            this.goToNextMob(var3, var5);
            return Auto.findNearMob(var1.cx, var1.cy);
        } else {
            return Auto.findNearMob(var1.cx, var1.cy);
        }
    }

    public final String toString() {
        return "Tự đánh";
    }
}
