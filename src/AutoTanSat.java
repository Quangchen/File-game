
// Source code is decompiled from instance .class file using FernFlower decompiler.
import java.util.Calendar;

public final class AutoTanSat extends Auto {

    public int modID;
    public static long b;

    public AutoTanSat() {
    }

    public final void init(int var1, int var2, int var3) {
        super.a();
        super.mapID = var2;
        super.zoneID = var3;
        super.isHang = TileMap.isHang(var2);
        this.modID = var1;
    }

    public final void run() {
        if (super.isDead()) {
            if (Char.tickReMap) {
                Auto.autoRemap(true);
            }
        } else {
            Calendar curCal;
            int currentHour = (curCal = Res.getCurrentTime()).get(11);
            int currentMinute = curCal.get(12);

            if (FormAutoTask.batNvhn && currentHour == FormAutoTask.gioNvhn && currentMinute == FormAutoTask.phutNvhn && System.currentTimeMillis() - b >= 5000L) {
                if (Auto.goTruongIfNeeded()) {
                    return;
                }
                Auto.sleep(2000L);
                GameScr.PickNpc(25, 1, 0);
                for(int i = 60; i >= 0; i--){
                    GameScr.chatPopup("Bắt đầu làm nhiệm vụ hàng ngày" + " (" + i + " giây)");
                    Auto.sleep(1000L);
                }
                Code.instance.startAutoNVHN();
                b = System.currentTimeMillis();
                return;
            }

            if (SettingNVDV.tickHenGioLamDV == 0 && currentHour == SettingNVDV.gioADV && currentMinute == SettingNVDV.phutADV && System.currentTimeMillis() - b >= 5000L) {
                if (Auto.goTruongIfNeeded()) {
                    return;
                }
                Auto.sleep(2000L);
                GameScr.PickNpc(25, 1, 0);
                GameScr.chatPopup("Nhận nhiệm vụ hàng ngày đã, đi làm nhiệm vụ cho nhanh");
                Auto.sleep(2000L);
                GameScr.goNPC(2);
                for(int i = 60; i >= 0; i--){
                    GameScr.chatPopup("Bắt đầu làm nhiệm vụ danh vọng" + " (" + i + " giây)");
                    Auto.sleep(1000L);
                }
                Code.instance.startAutoDV();
                b = System.currentTimeMillis();
                return;
            }

            if (!Auto.q && Char.getMyChar().isHuman) {
                this.l();
                return;
            }

            if (Char.tickLuyenDaMax && Code.h() && Char.countNullSlot() < 5 && !TileMap.isLangCo(TileMap.mapID)) {
                if(!TileMap.isLang(TileMap.mapID)){
                    Auto.tuSat();
                }
                return;
            }
            
            if (super.mapID == TileMap.mapID && (super.isHang || super.zoneID == TileMap.zoneID)) {
                this.pickUpItem(-1);
                int attackMask = this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false);
                if (this.hasTargetBossInMap()) {
                    attackMask |= 8;
                }

                this.attack(this.modID, attackMask);
                return;
            }

            if (Char.tickReMap) {
                this.goMap(super.mapID, super.zoneID, super.k, super.l);
            }
        }

    }

    private boolean hasTargetBossInMap() {
        if (this.modID < 0) {
            return false;
        }

        for (int i = 0; i < GameScr.vMobAttack.size(); i++) {
            Mob mob = (Mob) GameScr.vMobAttack.elementAt(i);
            if (mob != null && mob.hp > 0 && mob.h != 0 && mob.h != 1 && (mob.isBoss || mob.levelBoss == 3) && isTargetMobId(mob, this.modID)) {
                return true;
            }
        }

        return false;
    }

    public final String toString() {
        return this.modID >= 0 && this.modID < Mob.mobTemplates.length ? "Tàn sát " + Mob.mobTemplates[this.modID].name : "Tàn sát";
    }
}
