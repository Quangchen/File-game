

final class AutoTimBoss implements Runnable {
    AutoTimBoss(Code var1) {
    }
 
    public final void run() {
       for(int var1 = 0; var1 < 30; ++var1) {
          Npc var3;
          if (TileMap.zoneID != var1 && (var3 = GameScr.findNpc(13)) != null) {
             if (Math.abs(var3.cx - Char.getMyChar().cx) > 22 || Math.abs(var3.cy - Char.getMyChar().cy) > 22) {
                Char.charMove(var3.cx, var3.cy);
             }
 
             Service.getInstance().requestChangeZone(var1, -1);
             TileMap.g();
 
             try {
                Thread.sleep(100L);
             } catch (InterruptedException var4) {
             }
          }
 
          for(int var2 = 0; var2 < GameScr.vMobAttack.size(); ++var2) {
             Mob var5;
             if (AutoBossSchedule.isBossTarget(var5 = (Mob)GameScr.vMobAttack.elementAt(var2))) {
                return;
             }
          }
       }
 
    }
 }
 
