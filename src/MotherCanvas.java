
// Source code is decompiled from instance .class file using FernFlower decompiler.
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class MotherCanvas extends Canvas implements Runnable {
   public static MotherCanvas instance;
   public GameGraphics tCanvas;
   private int d = 1;
   public static boolean c;

   public MotherCanvas() {
      this.setFullScreenMode(true);
      mGraphics.zoomLevel = this.d = 1;
   }

   public static MotherCanvas getInstance() {
      if (instance == null) {
         instance = new MotherCanvas();
      }

      return instance;
   }

   protected final void paint(Graphics var1) {
      this.tCanvas.a(var1);
   }

   protected final void keyPressed(int var1) {
      this.tCanvas.a(var1);
   }

   protected final void keyReleased(int var1) {
      this.tCanvas.b(var1);
   }

   protected final void pointerDragged(int var1, int var2) {
      var1 /= this.d;
      var2 /= this.d;
      this.tCanvas.b(var1, var2);
   }

   protected final void pointerPressed(int var1, int var2) {
      var1 /= this.d;
      var2 /= this.d;
      this.tCanvas.a(var1, var2);
   }

   protected final void pointerReleased(int var1, int var2) {
      var1 /= this.d;
      var2 /= this.d;
      this.tCanvas.c(var1, var2);
   }

   public final int b() {
      return this.d == 1 ? this.getHeight() : 0;
   }

   public final int c() {
      return this.d == 1 ? this.getWidth() : 0;
   }

   public final void run() {
      try {
         Thread.sleep(10L);
      } catch (InterruptedException var8) {
      }

      c = true;

      while(c) {
         try {
            long var1 = System.currentTimeMillis();
            this.tCanvas.a();
            long var3 = System.currentTimeMillis() - var1;

            try {
               Thread.sleep(var3 < (long)Code.speedGame ? (long)Code.speedGame - var3 : 1L);
            } catch (InterruptedException var6) {
            }
         } catch (Exception var7) {
            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var5) {
               var5.printStackTrace();
            }

            var7.printStackTrace();
         }
      }

   }
}
