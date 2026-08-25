

final class ChangeZone implements Runnable {
    private final int a;
 
    ChangeZone(GameScr var1, int var2) {
       this.a = var2;
    }
 
    public final void run() {
       Npc var1 = GameScr.findNpc(13);
       int var2 = -1;
       if (var1 != null && var1.statusMe != 15) {
          if (Math.abs(var1.cx - Char.getMyChar().cx) > 22 || Math.abs(var1.cy - Char.getMyChar().cy) > 22) {
             Char.charMove(var1.cx, var1.cy);
          }
       } else {
          if (TileMap.mapID != 99 && TileMap.mapID != 103 && TileMap.mapID != 134 && TileMap.mapID != 135 && TileMap.mapID != 136 && TileMap.mapID != 137) {
             return;
          }
 
          if ((var2 = Char.getIndexItemById(37)) < 0 && (var2 = Char.getIndexItemById(35)) < 0) {
             return;
          }
       }
 
       Service.getInstance().requestChangeZone(this.a, var2);
       TileMap.g();
 
       try {
          Thread.sleep(100L);
       } catch (InterruptedException var3) {
          var3.printStackTrace();
       }
    }
 }
 