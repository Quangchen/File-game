

final class NextMap implements Runnable {
    private final int a;
 
    NextMap(int var1) {
       this.a = var1;
    }
 
    public final void run() {
       try {
          int var1;
          if ((var1 = this.a) < 0) {
             var1 = 0;
          }
 
          if (var1 >= TileMap.vGo.size()) {
             var1 = TileMap.vGo.size() - 1;
          }
 
          TileMap.j(var1);
       } catch (Exception var2) {
          var2.printStackTrace();
       }
 
       System.gc();
       if (Session_ME.getInstance().connected) {
          GameScr.getInstance().update();
       }
 
       GameCanvas.setMaxTextLenght();
       GameCanvas.e = false;
    }
 }
 