

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

public final class Res {
   private static short[] a = new short[]{0, 18, 36, 54, 71, 89, 107, 125, 143, 160, 178, 195, 213, 230, 248, 265, 282, 299, 316, 333, 350, 367, 384, 400, 416, 433, 449, 465, 481, 496, 512, 527, 543, 558, 573, 587, 602, 616, 630, 644, 658, 672, 685, 698, 711, 724, 737, 749, 761, 773, 784, 796, 807, 818, 828, 839, 849, 859, 868, 878, 887, 896, 904, 912, 920, 928, 935, 943, 949, 956, 962, 968, 974, 979, 984, 989, 994, 998, 1002, 1005, 1008, 1011, 1014, 1016, 1018, 1020, 1022, 1023, 1023, 1024, 1024};
   private static short[] b;
   private static int[] c;
   private static Random d = new Random();

   public static void a() {
      b = new short[91];
      c = new int[91];

      for(int var0 = 0; var0 <= 90; ++var0) {
         b[var0] = a[90 - var0];
         if (b[var0] == 0) {
            c[var0] = Integer.MAX_VALUE;
         } else {
            c[var0] = (a[var0] << 10) / b[var0];
         }
      }

   }

   public static final int a(int var0) {
      if ((var0 = c(var0)) >= 0 && var0 < 90) {
         return a[var0];
      } else if (var0 >= 90 && var0 < 180) {
         return a[180 - var0];
      } else {
         return var0 >= 180 && var0 < 270 ? -a[var0 - 180] : -a[360 - var0];
      }
   }

   public static final int b(int var0) {
      if ((var0 = c(var0)) >= 0 && var0 < 90) {
         return b[var0];
      } else if (var0 >= 90 && var0 < 180) {
         return -b[180 - var0];
      } else {
         return var0 >= 180 && var0 < 270 ? -b[var0 - 180] : b[360 - var0];
      }
   }

   public static final int a(int var0, int var1) {
      int var10000;
      if (var0 == 0) {
         var10000 = var1 > 0 ? 90 : 270;
      } else {
         int var2 = Math.abs((var1 << 10) / var0);
         int var3 = 0;

         while(true) {
            if (var3 > 90) {
               var2 = 0;
            } else {
               if (c[var3] < var2) {
                  ++var3;
                  continue;
               }

               var2 = var3;
            }

            var2 = var2;
            if (var1 >= 0 && var0 < 0) {
               var2 = 180 - var2;
            }

            if (var1 < 0 && var0 < 0) {
               var2 += 180;
            }

            if (var1 >= 0 || var0 < 0) {
               return var2;
            }

            var10000 = 360 - var2;
            break;
         }
      }

      return var10000;
   }

   public static final int c(int var0) {
      if (var0 >= 360) {
         var0 -= 360;
      }

      if (var0 < 0) {
         var0 += 360;
      }

      return var0;
   }

   public static int b(int var0, int var1) {
      return var0 + d.nextInt(var1 - var0);
   }

   public static int distance(int var0, int var1, int var2, int var3) {
      if ((var0 = (var0 - var2) * (var0 - var2) + (var1 - var3) * (var1 - var3)) <= 0) {
         return 0;
      } else {
         var1 = (var0 + 1) / 2;

         do {
            var2 = var1;
            var1 = var1 / 2 + var0 / (var1 << 1);
         } while(Math.abs(var2 - var1) > 1);

         return var1;
      }
   }

   public static int d(int var0) {
      return d.nextInt(var0);
   }

   public static int e(int var0) {
      return var0 > 0 ? var0 : -var0;
   }

   public static void a(mGraphics var0) {
      var0.translateXY(-var0.getTranslateX(), -var0.getTranslateY());
      var0.setClip(0, 0, GameCanvas.width, GameCanvas.height);
   }

   public static String[] a(String var0, String var1, int var2) {
      int var3;
      String[] var4;
      if ((var3 = var0.indexOf(var1)) >= 0) {
         var4 = a(var0.substring(var3 + var1.length()), var1, var2 + 1);
      } else {
         var4 = new String[var2 + 1];
         var3 = var0.length();
      }

      var4[var2] = var0.substring(0, var3);
      return var4;
   }

   public static String a(long var0, int var2) {
      String var3 = "";
      long var4;
      if ((var4 = (var0 + (long)var2 - System.currentTimeMillis()) / 1000L) <= 0L) {
         return "";
      } else {
         long var6 = 0L;
         long var8 = 0L;
         long var10 = var4 / 60L;
         long var12 = var4;
         if (var4 >= 86400L) {
            var6 = var4 / 86400L;
            var8 = var4 % 86400L / 3600L;
         } else if (var4 >= 3600L) {
            var8 = var4 / 3600L;
            var10 = var4 % 3600L / 60L;
         } else if (var4 >= 60L) {
            var12 = var4 % 60L;
         } else {
            var12 = var4;
         }

         String var14;
         if (var6 > 0L) {
            if (var6 >= 10L) {
               var14 = var8 < 1L ? var6 + "d" : (var8 < 10L ? var6 + "d0" + var8 + "h" : var6 + "d" + var8 + "h");
            } else {
               if (var6 >= 10L) {
                  return var3;
               }

               var14 = var8 < 1L ? var6 + "d" : (var8 < 10L ? var6 + "d0" + var8 + "h" : var6 + "d" + var8 + "h");
            }
         } else if (var8 > 0L) {
            if (var8 >= 10L) {
               var14 = var10 < 1L ? var8 + "h" : (var10 < 10L ? var8 + "h0" + var10 + "m" : var8 + "h" + var10 + "m");
            } else {
               if (var8 >= 10L) {
                  return var3;
               }

               var14 = var10 < 1L ? var8 + "h" : (var10 < 10L ? var8 + "h0" + var10 + "m" : var8 + "h" + var10 + "m");
            }
         } else {
            String var10000;
            if (var10 > 0L) {
               if (var10 >= 10L) {
                  if (var12 >= 10L) {
                     var10000 = var10 + "m" + var12 + "s";
                  } else {
                     if (var12 >= 10L) {
                        return var3;
                     }

                     var10000 = var10 + "m0" + var12 + "s";
                  }
               } else {
                  if (var10 >= 10L) {
                     return var3;
                  }

                  if (var12 >= 10L) {
                     var10000 = var10 + "m" + var12 + "s";
                  } else {
                     if (var12 >= 10L) {
                        return var3;
                     }

                     var10000 = var10 + "m0" + var12 + "s";
                  }
               }
            } else {
               var10000 = var12 < 10L ? "0" + var12 + "s" : var12 + "s";
            }

            var14 = var10000;
         }

         return var14;
      }
   }

   public static DataInputStream a(String var0) {
      DataInputStream var1 = null;
      InputStream var2;
      if ((var2 = "".getClass().getResourceAsStream(var0)) != null) {
         var1 = new DataInputStream(var2);
      }

      return var1;
   }

   public static boolean a(int var0, int var1, int var2, int var3, int var4, int var5) {
      return var0 >= var2 && var0 < var2 + var4 && var1 >= var3 && var1 < var3 + var5;
   }

   public static int f(int var0) {
      if (var0 <= 0) {
         return 0;
      } else {
         int var1 = (var0 + 1) / 2;

         while(true) {
            int var2 = var1 / 2 + var0 / (var1 << 1);
            if (Math.abs(var1 - var2) <= 1) {
               return var2;
            }

            var1 = var2;
         }
      }
   }

   public static Calendar getCurrentTime() {
      return Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
   }
}
