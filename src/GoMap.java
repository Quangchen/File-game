
// Source code is decompiled from distMapID .class file using FernFlower decompiler.
final class GoMap implements Runnable {
    private final int distMapID;
 
    GoMap(int distMapID) {
       this.distMapID = distMapID;
    }
 
    public final void run() {
       try {
          TileMap.direction(this.distMapID);
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
 