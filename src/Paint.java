

import javax.microedition.lcdui.Image;

public final class Paint {
   public static int a = 6562304;
   public static int b = 9581056;
   public static int COLORDARK = 3937280;
   public static int d = 15224576;
   public static int e = 16777215;
   public static int f = 24;

   public Paint() {
   }

   public static void a(mGraphics var0, Command var1, Command var2, Command var3) {
      if (FormToiUu.shouldHideGameButtons()) {
         paintOptimizeCommandBorders(var0, var1, var2, var3);
         return;
      }
      mFont var4 = GameCanvas.isTouch ? mFont.tahoma_7b_yellow : mFont.tahoma_8b;
      int var5 = GameCanvas.isTouch ? 3 : 1;
      if (!GameCanvas.isTouch) {
         if (var1 != null) {
            var4.writeText(var0, var1.caption, 5, GameCanvas.height - mScreen.cmdH + 4 + var5, 0);
         }

         if (var2 != null) {
            var4.writeText(var0, var2.caption, GameCanvas.centerX, GameCanvas.height - mScreen.cmdH + 4 + var5, 2);
         }

         if (var3 != null) {
            if (var3.e != null) {
               var0.drawImage(var3.e, GameCanvas.width - 5, GameCanvas.height - 11, 10);
               return;
            }

            var4.writeText(var0, var3.caption, GameCanvas.width - 5, GameCanvas.height - mScreen.cmdH + 4 + var5, 1);
            return;
         }
      } else {
         if (var1 != null && var4.a(var1.caption) > 0) {
            if (var1.x > 0 && var1.y > 0) {
               var1.a(var0);
            } else {
               if (mScreen.fr == 0) {
                  var0.drawImage(GameScr.dt, 1, GameCanvas.height - mScreen.cmdH + 1, 0);
               } else {
                  var0.drawImage(GameScr.ds, 1, GameCanvas.height - mScreen.cmdH + 1, 0);
               }

               var4.writeText(var0, var1.caption, 35, GameCanvas.height - mScreen.cmdH + 4 + var5, 2);
            }
         }

         if (var2 != null && var4.a(var2.caption) > 0) {
            if (var2.x > 0 && var2.y > 0) {
               var2.a(var0);
            } else {
               if (mScreen.fr == 1) {
                  var0.drawImage(GameScr.dt, GameCanvas.centerX - 35, GameCanvas.height - mScreen.cmdH + 1, 0);
               } else {
                  var0.drawImage(GameScr.ds, GameCanvas.centerX - 35, GameCanvas.height - mScreen.cmdH + 1, 0);
               }

               var4.writeText(var0, var2.caption, GameCanvas.centerX, GameCanvas.height - mScreen.cmdH + 4 + var5, 2);
            }
         }

         if (var3 != null && var4.a(var3.caption) > 0) {
            if (var3.x > 0 && var3.y > 0) {
               var3.a(var0);
               return;
            }

            if (mScreen.fr == 2) {
               var0.drawImage(GameScr.dt, GameCanvas.width - 71, GameCanvas.height - mScreen.cmdH + 1, 0);
            } else {
               var0.drawImage(GameScr.ds, GameCanvas.width - 71, GameCanvas.height - mScreen.cmdH + 1, 0);
            }

            var4.writeText(var0, var3.caption, GameCanvas.width - 35, GameCanvas.height - mScreen.cmdH + 4 + var5, 2);
         }
      }

   }

   private static void paintOptimizeCommandBorders(mGraphics var0, Command var1, Command var2, Command var3) {
      if (var1 != null && hasPaintCommand(var1)) {
         paintOptimizeCommandBorder(var0, var1, 0);
      }

      if (var2 != null && hasPaintCommand(var2)) {
         paintOptimizeCommandBorder(var0, var2, 1);
      }

      if (var3 != null && hasPaintCommand(var3)) {
         paintOptimizeCommandBorder(var0, var3, 2);
      }
   }

   private static boolean hasPaintCommand(Command var0) {
      return var0.e != null || var0.caption != null && var0.caption.length() > 0;
   }

   private static void paintOptimizeCommandBorder(mGraphics var0, Command var1, int var2) {
      int var3;
      int var4;
      int var5;
      int var6;
      if (var1.x > 0 && var1.y > 0) {
         var3 = var1.x - 3;
         var4 = var1.y - 3;
         var5 = var1.w + 6;
         var6 = var1.h + 6;
      } else {
         var5 = mScreen.cmdW;
         var6 = mScreen.cmdH + 10;
         var4 = GameCanvas.height - mScreen.cmdH - 5;
         if (var2 == 1) {
            var3 = GameCanvas.width - mScreen.cmdW >> 1;
         } else if (var2 == 2) {
            var3 = GameCanvas.width - mScreen.cmdW;
         } else {
            var3 = 0;
         }
      }

      drawOptimizeBorder(var0, var3, var4, var5, var6);
   }

   private static void drawOptimizeBorder(mGraphics var0, int var1, int var2, int var3, int var4) {
      var0.setColor(16711680);
      var0.drawRect(var1, var2, var3, var4);
      if (var3 > 4 && var4 > 4) {
         var0.drawRect(var1 + 1, var2 + 1, var3 - 2, var4 - 2);
      }
   }

   public static void a(mGraphics var0) {
      if (FormToiUu.shouldHideGameButtons()) {
         return;
      }
      if (!GameCanvas.isTouch) {
         var0.setColor(0);
         var0.fillRect(0, GameCanvas.height - f, GameCanvas.width, f + 1);
         var0.setColor(8947848);
         var0.fillRect(0, GameCanvas.height - (f - 1), GameCanvas.width, 1);
      }

   }

   public static void a(mGraphics var0, boolean var1, int var2, int var3, int var4, int var5, int var6, int var7, String var8) {
      var0.setColor(0);
      int var9;
      if (var1) {
         var0.drawRegion(GameScr.dq, 0, 81, 29, 27, 0, var2, var3, 0);
         var0.drawRegion(GameScr.dq, 0, 135, 29, 27, 0, var2 + var4 - 29, var3, 0);
         var0.drawRegion(GameScr.dq, 0, 108, 29, 27, 0, var2 + var4 - 58, var3, 0);

         for(var9 = 0; var9 < (var4 - 58) / 29; ++var9) {
            var0.drawRegion(GameScr.dq, 0, 108, 29, 27, 0, var2 + 29 + var9 * 29, var3, 0);
         }
      } else {
         var0.drawRegion(GameScr.dq, 0, 0, 29, 27, 0, var2, var3, 0);
         var0.drawRegion(GameScr.dq, 0, 54, 29, 27, 0, var2 + var4 - 29, var3, 0);
         var0.drawRegion(GameScr.dq, 0, 27, 29, 27, 0, var2 + var4 - 58, var3, 0);

         for(var9 = 0; var9 < (var4 - 58) / 29; ++var9) {
            var0.drawRegion(GameScr.dq, 0, 27, 29, 27, 0, var2 + 29 + var9 * 29, var3, 0);
         }
      }

      var0.setClip(var2 + 3, var3 + 1, var4 - 4, var5 - 4);
      mFont.tahoma_8b.writeText(var0, var8, var6, var7, 0);
   }

   public static void a(mGraphics var0, boolean var1, int var2, int var3, int var4, int var5, int var6, String var7) {
      var0.setColor(0);
      int var8;
      if (var1) {
         var0.drawRegion(GameScr.dq, 0, 81, 29, 27, 0, var2, var3, 0);
         var0.drawRegion(GameScr.dq, 0, 135, 29, 27, 0, var2 + var4 - 29, var3, 0);
         var0.drawRegion(GameScr.dq, 0, 108, 29, 27, 0, var2 + var4 - 58, var3, 0);

         for(var8 = 0; var8 < (var4 - 58) / 29; ++var8) {
            var0.drawRegion(GameScr.dq, 0, 108, 29, 27, 0, var2 + 29 + var8 * 29, var3, 0);
         }
      } else {
         var0.drawRegion(GameScr.dq, 0, 0, 29, 27, 0, var2, var3, 0);
         var0.drawRegion(GameScr.dq, 0, 54, 29, 27, 0, var2 + var4 - 29, var3, 0);
         var0.drawRegion(GameScr.dq, 0, 27, 29, 27, 0, var2 + var4 - 58, var3, 0);

         for(var8 = 0; var8 < (var4 - 58) / 29; ++var8) {
            var0.drawRegion(GameScr.dq, 0, 27, 29, 27, 0, var2 + 29 + var8 * 29, var3, 0);
         }
      }

      mFont.tahoma_8b.writeText(var0, var7, var5, var6, 0);
   }

   public static void a(mGraphics var0, int var1, int var2, int var3, int var4, String[] var5, Image var6) {
      a(var1, var2, var3, var4, var0);
      var2 = var2 + 20 - mFont.tahoma_8b.getHeight();

      for(var4 = 0; var4 < var5.length; var2 += mFont.tahoma_8b.getHeight()) {
         mFont.tahoma_8b.writeText(var0, var5[var4], var1 + var3 / 2, var2, 2);
         ++var4;
      }

   }

   public static void a(int var0, int var1, int var2, int var3, mGraphics var4) {
      var4.setColor(a);
      var4.fillRect(var0, var1, var2, var3);
      var4.setColor(0);
      var4.drawRect(var0 - 2, var1 - 2, var2 + 3, var3 + 3);
      var4.setColor(13948116);
      var4.drawRect(var0 - 1, var1 - 1, var2 + 1, var3 + 1);
      var4.setColor(5720393);
      var4.drawRect(var0, var1, var2 - 1, var3 - 1);
      if (GameCanvas.isTouch) {
         var4.drawImage(GameCanvas.ap[0], var0 - 4, var1 - 3, 20);
         var4.drawRegion(GameCanvas.ap[0], 0, 0, GameCanvas.aq, GameCanvas.ar, 2, var0 + var2 + 4, var1 - 3, StaticObj.c);
         var4.drawRegion(GameCanvas.ap[0], 0, 0, GameCanvas.aq, GameCanvas.ar, 1, var0 - 4, var1 + var3 + 3, StaticObj.e);
         var4.drawRegion(GameCanvas.ap[0], 0, 0, GameCanvas.aq, GameCanvas.ar, 3, var0 + var2 + 4, var1 + var3 + 3, StaticObj.f);
         var4.drawImage(GameCanvas.ap[1], var0 + var2 / 2, var1 - 4, StaticObj.a);
      }

   }

   public static void b(int var0, int var1, int var2, int var3, mGraphics var4) {
      var4.setColor(0);
      var4.drawRect(var0 - 2, var1 - 2, var2 + 3, var3 + 3);
      var4.setColor(13948116);
      var4.drawRect(var0 - 1, var1 - 1, var2 + 1, var3 + 1);
      var4.setColor(5720393);
      var4.drawRect(var0, var1, var2 - 1, var3 - 1);
      if (GameCanvas.isTouch) {
         var4.drawImage(GameCanvas.ap[0], var0 - 4, var1 - 3, 20);
         var4.drawRegion(GameCanvas.ap[0], 0, 0, GameCanvas.aq, GameCanvas.ar, 2, var0 + var2 + 4, var1 - 3, StaticObj.c);
         var4.drawRegion(GameCanvas.ap[0], 0, 0, GameCanvas.aq, GameCanvas.ar, 1, var0 - 4, var1 + var3 + 3, StaticObj.e);
         var4.drawRegion(GameCanvas.ap[0], 0, 0, GameCanvas.aq, GameCanvas.ar, 3, var0 + var2 + 4, var1 + var3 + 3, StaticObj.f);
         var4.drawImage(GameCanvas.ap[1], var0 + var2 / 2, var1 - 4, StaticObj.a);
      }

   }

   public static void c(int var0, int var1, int var2, int var3, mGraphics var4) {
      var4.setColor(a);
      var4.fillRect(var0, var1, var2, var3);
   }

   public static void d(int var0, int var1, int var2, int var3, mGraphics var4) {
      var4.setColor(b);
      var4.fillRect(var0, var1, var2, var3);
   }
}
