

public class InfoDlg {
    int a;
    int b;
    int c;
    int d;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k = 28;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private boolean q = false;
    static boolean e;
    private static String r;
    private static String s;
    private static int t;
    public static boolean f;
 
    public InfoDlg() {
    }
 
    private static boolean e() {
       if (Char.getMyChar().statusMe != 3) {
          for(int var0 = 2; var0 > 0; --var0) {
             int var1;
             try {
                if (Res.e(GameCanvas.t[var0].a - GameCanvas.t[var0 - 1].a) <= 2) {
                   continue;
                }
 
                var1 = Res.e(GameCanvas.t[var0].b - GameCanvas.t[var0 - 1].b);
             } catch (Exception var2) {
                var2.printStackTrace();
                return true;
             }
 
             if (var1 > 2) {
                return false;
             }
          }
       }
 
       return true;
    }
 
    private static void f() {
       GameCanvas.l[1] = false;
       GameCanvas.l[2] = false;
       GameCanvas.l[3] = false;
       GameCanvas.l[4] = false;
       GameCanvas.l[6] = false;
    }
 
    public final void a() {
       if (GameCanvas.m && !GameCanvas.isPointerJustRelease) {
          this.g = GameCanvas.r;
          this.h = GameCanvas.s;
          if (this.g <= (GameCanvas.width >> 1) - 100 && this.h >= GameCanvas.height >> 1) {
             if (!this.q) {
                this.a = this.c = this.g;
                this.b = this.d = this.h;
             }
 
             this.q = true;
             this.m = GameCanvas.p - this.a;
             this.n = GameCanvas.q - this.b;
             this.o = this.m * this.m + this.n * this.n;
             this.l = Res.f(this.o);
             if (Math.abs(this.m) > 4 || Math.abs(this.n) > 4) {
                this.p = Res.a(this.m, this.n);
                if (!GameCanvas.b(this.a - this.k, this.b - this.k, this.k << 1, this.k << 1)) {
                   if (this.l != 0) {
                      this.d = this.n * this.k / this.l;
                      this.c = this.m * this.k / this.l;
                      this.c += this.a;
                      this.d += this.b;
                      if (!Res.a(this.a - this.k, this.b - this.k, this.k << 1, this.k << 1, this.c, this.d)) {
                         this.c = this.i;
                         this.d = this.j;
                      } else {
                         this.i = this.c;
                         this.j = this.d;
                      }
                   } else {
                      this.c = this.i;
                      this.d = this.j;
                   }
                } else {
                   this.c = GameCanvas.p;
                   this.d = GameCanvas.q;
                }
 
                f();
                if (!e()) {
                   f();
                   return;
                }
 
                if (this.p <= 360 && this.p > 340 || this.p > 0 && this.p <= 90) {
                   GameScr.es = 0;
                   GameCanvas.l[6] = true;
                   GameCanvas.keyPressedz[6] = true;
                   return;
                }
 
                if (this.p > 290 && this.p <= 340) {
                   GameScr.es = 0;
                   GameCanvas.l[3] = true;
                   GameCanvas.keyPressedz[3] = true;
                   return;
                }
 
                if (this.p > 250 && this.p <= 290) {
                   GameScr.es = 0;
                   GameCanvas.l[2] = true;
                   GameCanvas.keyPressedz[2] = true;
                   return;
                }
 
                if (this.p > 200 && this.p <= 250) {
                   GameScr.es = 0;
                   GameCanvas.l[1] = true;
                   GameCanvas.keyPressedz[1] = true;
                   return;
                }
 
                if (this.p > 90 && this.p <= 200) {
                   GameScr.es = 0;
                   GameCanvas.l[4] = true;
                   GameCanvas.keyPressedz[4] = true;
                   return;
                }
             }
          }
       } else {
          this.a = this.c = 50;
          this.b = this.d = GameCanvas.height - 50;
          this.q = false;
          f();
       }
 
    }
 
    public static void a(String var0, String var1, int var2) {
       if (var0 != null) {
          e = true;
          r = var0;
          s = var1;
          t = var2;
       }
 
    }
 
    public static void b() {
       a(mResources.textLoading, (String)null, 5000);
       f = true;
    }
 
    public static void a(String var0) {
       a(var0, (String)null, 5000);
       f = true;
    }
 
    public static void a(mGraphics var0) {
       String var1 = r;
       if (TileMap.mapName1 != null) {
          var1 = TileMap.mapName1;
       }
 
       if (e && (!f || t <= 4990) && !GameScr.ce) {
          Paint.a(GameCanvas.centerX - 64, 10, 128, 40, var0);
          if (f) {
             GameCanvas.a(GameCanvas.centerX - mFont.tahoma_8b.a(var1) / 2 - 10, 30, var0);
             mFont.tahoma_8b.writeText(var0, var1, GameCanvas.centerX + 5, 23, 2);
             return;
          }
 
          if (s != null) {
             mFont.tahoma_8b.writeText(var0, var1, GameCanvas.centerX, 18, 2);
             mFont.tahoma_7_white.writeText(var0, s, GameCanvas.centerX, 32, 2);
             return;
          }
 
          mFont.tahoma_8b.writeText(var0, var1, GameCanvas.centerX, 23, 2);
       }
 
    }
 
    public static void c() {
       if (t > 0 && --t == 0) {
          d();
       }
 
    }
 
    public static void d() {
       r = "";
       s = null;
       f = false;
       t = 0;
       e = false;
    }
 }
 