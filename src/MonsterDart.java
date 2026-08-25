
// Source code is decompiled from update .class file using FernFlower decompiler.
import java.util.Vector;

public final class MonsterDart extends Effect2 {
   private int a;
   private int b;
   private int g;
   private short h = 256;
   private int i;
   private int j;
   private int k;
   private int l;
   private int m;
   private int n;
   private Char o;
   private BuNhin p;
   private boolean q;
   private int r;
   private int s = 1;
   private static int t;
   private static Vector u = new Vector();
   private static int v = 0;
   private int w = 0;
   private int x;
   private static byte[] y = new byte[]{0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0};
   private static int[] z = new int[]{0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};
   private static int[] aa = new int[]{0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};

   private void a(int var1) {
      this.a = var1;
      this.b = this.h * Res.b(var1) >> 10;
      this.g = this.h * Res.a(var1) >> 10;
   }

   public static void a(int var0, int var1, boolean var2, short var3, int var4, int var5, int var6, Char var7) {
      t = var3;
      Effect2.vEffect2.addElement(new MonsterDart(var0, var1, var2, var4, var5, var6, var7));
   }

   public static void a(int var0, int var1, BuNhin var2) {
      Effect2.vEffect2.addElement(new MonsterDart(var0, var1, var2));
   }

   private MonsterDart(int var1, int var2, boolean var3, int var4, int var5, int var6, Char var7) {
      this.n = 0;
      this.i = var1;
      this.j = var2;
      this.q = var3;
      this.r = var4;
      this.l = var5;
      this.m = var6;
      this.o = var7;
      if (var3) {
         this.a(this.s * 90);
         ++this.s;
         if (this.s > 3) {
            this.s = 1;
            return;
         }
      } else {
         if (var1 > var7.cx) {
            this.a(240);
            return;
         }

         this.a(300);
      }

   }

   private MonsterDart(int var1, int var2, BuNhin var3) {
      this.n = 1;
      this.i = var1;
      this.j = var2;
      this.p = var3;
      if (var1 > var3.a) {
         this.a(240);
      } else {
         this.a(300);
      }

      this.h = 256;
      this.a = 180;
      this.b = this.h * Res.b(this.a) >> 10;
      this.g = this.h * Res.a(this.a) >> 10;
   }

   public final void update() {
      DomSang var1;
      if (t > 100) {
         var1 = new DomSang(this.i, this.j, 2);
         u.addElement(var1);
      } else if (t > 50 && t <= 100) {
         var1 = new DomSang(this.i, this.j, 5);
         u.addElement(var1);
      } else if (t > 30 && t <= 50) {
         var1 = new DomSang(this.i, this.j, 4);
         u.addElement(var1);
      } else if (t > 0 && t <= 30) {
         var1 = new DomSang(this.i, this.j, 3);
         u.addElement(var1);
      }

      int var2;
      int var3;
      int var4;
      if (this.o != null) {
         var4 = this.o.cx - this.i;
         var2 = this.o.cy - (this.o.bj >> 1) - this.j;
         ++this.k;
         if ((Res.e(var4) >= 16 || Res.e(var2) >= 16) && this.k <= 60) {
            if (Math.abs((var3 = Res.a(var4, var2)) - this.a) < 90 || var4 * var4 + var2 * var2 > 4096) {
               if (Math.abs(var3 - this.a) < 15) {
                  this.a = var3;
               } else if ((var3 - this.a < 0 || var3 - this.a >= 180) && var3 - this.a >= -180) {
                  this.a = Res.c(this.a - 15);
               } else {
                  this.a = Res.c(this.a + 15);
               }
            }

            if (this.h < 8192) {
               this.h = (short)(this.h + 1024);
            }

            this.b = this.h * Res.b(this.a) >> 10;
            this.g = this.h * Res.a(this.a) >> 10;
            var4 = var4 + this.b >> 10;
            this.i += var4;
            var2 = var2 + this.g >> 10;
            this.j += var2;
            var4 = Res.a(var4, -var2);
            var2 = 0;

            while(true) {
               if (var2 >= aa.length - 1) {
                  var4 = 0;
                  break;
               }

               if (var4 >= aa[var2] && var4 <= aa[var2 + 1]) {
                  var4 = var2 >= 16 ? 0 : var2;
                  break;
               }

               ++var2;
            }

            this.x = var4;
            this.w = y[this.x];
            v = z[this.x];
         }
      }

      var3 = 0;
      var4 = 0;
      if (this.n != 0) {
         if (this.n == 1) {
            var3 = this.p.a - this.i;
            var4 = this.p.b - 10 - this.j;
            ++this.k;
            if (Res.e(var3) < 16 && Res.e(var4) < 16 || this.k > 60) {
               this.p.d = true;
               Effect2.vEffect2.removeElement(this);
               return;
            }
         }
      } else {
         var3 = this.o.cx - this.i;
         var4 = this.o.cy - 10 - this.j;
         ++this.k;
         if (this.o.statusMe == 5 || this.o.statusMe == 14) {
            this.i += (this.o.cx - this.i) / 2;
            this.j += (this.o.cy - this.j) / 2;
         }

         if (Res.e(var3) < 16 && Res.e(var4) < 16 || this.k > 60) {
            this.o.a(this.l, this.m, this.q, this.r);
            Effect2.vEffect2.removeElement(this);
            return;
         }
      }

      if (Math.abs((var2 = Res.a(var3, var4)) - this.a) < 90 || var3 * var3 + var4 * var4 > 4096) {
         if (Math.abs(var2 - this.a) < 15) {
            this.a = var2;
         } else if ((var2 - this.a < 0 || var2 - this.a >= 180) && var2 - this.a >= -180) {
            this.a = Res.c(this.a - 15);
         } else {
            this.a = Res.c(this.a + 15);
         }
      }

      var3 = var3 + this.b >> 10;
      this.i += var3;
      var3 = var4 + this.g >> 10;
      this.j += var3;

      for(var3 = 0; var3 < u.size(); ++var3) {
         (var1 = (DomSang)u.elementAt(var3)).a();
         if (var1.a > 3) {
            u.removeElementAt(var3);
         }
      }

   }

   public final void paint(mGraphics var1) {
      int var2;
      if (this.q) {
         var2 = GameCanvas.gameTick % 7;
         if (this.r == 114) {
            if (var2 < 4) {
               SmallImage.paintImage(var1, 1299, this.i, this.j, 0, 3);
               return;
            }

            SmallImage.paintImage(var1, 1307, this.i, this.j, 0, 3);
            return;
         }

         if (this.r == 115) {
            if ((var2 = GameCanvas.gameTick % 20) < 4) {
               SmallImage.paintImage(var1, 1379, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 8) {
               SmallImage.paintImage(var1, 1380, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 12) {
               SmallImage.paintImage(var1, 1379, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 16) {
               SmallImage.paintImage(var1, 1382, this.i, this.j, 0, 3);
               return;
            }
         } else if (this.r == 116) {
            if ((var2 = GameCanvas.gameTick % 17) < 4) {
               SmallImage.paintImage(var1, 1399, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 8) {
               SmallImage.paintImage(var1, 1400, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 12) {
               SmallImage.paintImage(var1, 1401, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 16) {
               SmallImage.paintImage(var1, 1402, this.i, this.j, 0, 3);
               return;
            }
         } else if (this.r == 139) {
            if ((var2 = GameCanvas.gameTick % 20) < 4) {
               SmallImage.paintImage(var1, 1459, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 8) {
               SmallImage.paintImage(var1, 1380, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 12) {
               SmallImage.paintImage(var1, 1461, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 16) {
               SmallImage.paintImage(var1, 1382, this.i, this.j, 0, 3);
               return;
            }
         } else if (this.r == 144 || this.r == 163) {
            if ((var2 = GameCanvas.gameTick % 20) < 4) {
               SmallImage.paintImage(var1, 1459, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 8) {
               SmallImage.paintImage(var1, 1380, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 12) {
               SmallImage.paintImage(var1, 1461, this.i, this.j, 0, 3);
               return;
            }

            if (var2 < 16) {
               SmallImage.paintImage(var1, 1382, this.i, this.j, 0, 3);
               return;
            }
         }
      } else {
         if (t > 100) {
            var1.drawRegion(GameScr.ej, 0, this.w * 23, 31, 23, v, this.i, this.j, 0);
         } else if (t > 50 && t <= 100) {
            var1.drawRegion(GameScr.ej, 0, 0, 14, 14, v, this.i, this.j, 0);
         } else if (t > 30 && t <= 50) {
            var1.drawRegion(GameScr.ej, 0, 0, 8, 8, v, this.i, this.j, 0);
         } else if (t > 0 && t <= 30) {
            var1.drawRegion(GameScr.ej, 0, 0, 8, 8, v, this.i, this.j, 0);
         }

         for(var2 = 0; var2 < u.size(); ++var2) {
            DomSang var3;
            if ((var3 = (DomSang)u.elementAt(var2)) != null) {
               var3.a(var1);
            }
         }
      }

   }
}
