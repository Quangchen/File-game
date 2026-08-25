

public final class SelectCharScr extends mScreen implements IActionListener {
    private static SelectCharScr l;
    private int m = 48;
    private int n = 85;
    private int o;
    private int p;
    private int q;
    public int a;
    public int[] b;
    public int[] c;
    public int[] d;
    public int[] e;
    public int[] f;
    public String[] g;
    public String[] h;
    public byte[] i;
    private Command r;
    private int s;
    public boolean j = true;
    public static String k = "";
    private static boolean quickLogin = RMS.d("quickLogin") == 1;
 
    public static SelectCharScr a() {
       if (l == null) {
          l = new SelectCharScr();
       }
 
       return l;
    }
 
    public SelectCharScr() {
       if (GameCanvas.width < 160) {
          this.m = 32;
       }
 
       this.o = 7;
       this.p = (GameCanvas.width - 3 * this.m >> 1) - 5;
       this.q = GameCanvas.centerY - (this.n >> 1) + 10;
       if (GameCanvas.isTouch && GameCanvas.width > 200) {
          this.m = 74;
          this.o = 25;
          this.n = 110;
          this.p = (GameCanvas.width - 3 * this.m >> 1) - 20;
          this.q = GameCanvas.centerY - (this.n >> 1);
          if (GameCanvas.width < 320) {
             this.o = 6;
             this.p = (GameCanvas.width - 3 * this.m >> 1) - 6;
          }
       }
 
       super.left = null;
       this.r = new Command(mResources.chon, this, 1000, (Object)null);
       super.center = new Command("", this, 1000, (Object)null);
       super.right = new Command(mResources.exit, this, 1001, (Object)null);
       super.left = this.r;
 
       if (GameCanvas.isTouch && GameCanvas.width >= 320) {
          super.right.x = GameCanvas.width / 2 + 88;
          super.right.y = GameCanvas.height - 26;
       }
 
    }
 
    private void e() {
       if (this.g[this.a] != null) {
          k = this.g[this.a];
          Service.getInstance().b(this.g[this.a]);
          GameCanvas.showDialogWait(mResources.textLoading);
          GameCanvas.e = true;
       } else {
          CreateCharScr.a().update();
       }
    }

    public static boolean isQuickLogin() {
       return quickLogin;
    }

    public static void toggleQuickLogin() {
       quickLogin = !quickLogin;
       RMS.writeRecord("quickLogin", quickLogin ? 1 : 0);
    }

    public static void setQuickLogin(boolean enabled) {
       quickLogin = enabled;
       RMS.writeRecord("quickLogin", quickLogin ? 1 : 0);
    }

    public final void tryQuickLoginFirstSlot() {
       if (!quickLogin || this.g == null || this.g.length == 0 || this.g[0] == null) {
          return;
       }

       this.a = 0;
       this.e();
    }
 
    public final void b() {
       super.b();
       if (GameCanvas.keyPressedz[6]) {
          ++this.a;
          if (this.a >= 3) {
             this.a = 0;
          }
       }
 
       if (GameCanvas.keyPressedz[4]) {
          --this.a;
          if (this.a < 0) {
             this.a = 2;
          }
       }
 
       if (GameCanvas.m && GameCanvas.b(this.p, this.q, 3 * (this.m + this.o), this.n)) {
          int var1;
          if ((var1 = (GameCanvas.p - this.p) / (this.m + this.o)) > 2) {
             var1 = 2;
          }
 
          if (var1 < 0) {
             var1 = 0;
          }
 
          this.a = var1;
       }
 
       if (GameCanvas.isPointerJustRelease) {
          if (GameCanvas.b(this.p, this.q, 3 * (this.m + this.o), this.n)) {
             this.s = 5;
          } else {
             this.a = -1;
          }
       }
 
       GameCanvas.l();
       GameCanvas.k();
    }
 
    public final void c() {
       if (++GameScr.cmx > GameCanvas.width * 3 + 100) {
          GameScr.cmx = 100;
       }
 
       if (this.s > 0) {
          --this.s;
          if (this.s == 0 && this.a >= 0) {
             this.e();
          }
       }
 
    }
 
    public final void update() {
       TileMap.c();
       System.gc();
       super.update();
 
       for(int var1 = 0; var1 < this.g.length; ++var1) {
          if (this.g[var1] != null) {
             this.j = false;
             break;
          }
       }
 
       if (this.j) {
          CreateCharScr.a().update();
       }
 
    }
 
    public final void paint(mGraphics var1) {
       GameCanvas.paint(var1);
 
       int var2;
       for(var2 = 0; var2 < 3; ++var2) {
          if (this.a == var2) {
             Paint.d(this.p + var2 * (this.m + this.o), this.q, this.m, this.n, var1);
          } else {
             Paint.c(this.p + var2 * (this.m + this.o), this.q, this.m, this.n, var1);
          }
 
          Paint.b(this.p + var2 * (this.m + this.o), this.q, this.m, this.n, var1);
       }
 
       for(var2 = 0; var2 < 3; ++var2) {
          if (this.g[var2] != null) {
             Part var3 = GameScr.parts[this.b[var2]];
             Part var4 = GameScr.parts[this.c[var2]];
             Part var5 = GameScr.parts[this.d[var2]];
             Part var6 = GameScr.parts[this.e[var2]];
             int var7;
             if (var3.partImages != null && var3.partImages.length >= 8) {
                for(var7 = 0; var7 < var3.partImages.length; ++var7) {
                   if (var3.partImages[var7] == null || !SmallImage.a(var3.partImages[var7].id)) {
                      Char.getMyChar();
                      var3 = Char.b(this.i[var2]);
                      break;
                   }
                }
             } else {
                Char.getMyChar();
                var3 = Char.b(this.i[var2]);
             }
 
             var7 = this.p + var2 * (this.m + this.o) + this.m / 2;
             int var8;
             if (!GameCanvas.isTouch) {
                var8 = this.q + this.n / 2 + 16;
                SmallImage.paintImage(var1, var6.partImages[Char.fe[0][3][0]].id, var7 + Char.fe[0][3][1] + var6.partImages[Char.fe[0][3][0]].dx, var8 - Char.fe[0][3][2] + var6.partImages[Char.fe[0][3][0]].dy, 0, 0);
                SmallImage.paintImage(var1, var3.partImages[Char.fe[0][0][0]].id, var7 + Char.fe[0][0][1] + var3.partImages[Char.fe[0][0][0]].dx, var8 - Char.fe[0][0][2] + var3.partImages[Char.fe[0][0][0]].dy, 0, 0);
                SmallImage.paintImage(var1, var4.partImages[Char.fe[0][1][0]].id, var7 + Char.fe[0][1][1] + var4.partImages[Char.fe[0][1][0]].dx, var8 - Char.fe[0][1][2] + var4.partImages[Char.fe[0][1][0]].dy, 0, 0);
                SmallImage.paintImage(var1, var5.partImages[Char.fe[0][2][0]].id, var7 + Char.fe[0][2][1] + var5.partImages[Char.fe[0][2][0]].dx, var8 - Char.fe[0][2][2] + var5.partImages[Char.fe[0][2][0]].dy, 0, 0);
                if (this.a == var2) {
                   mFont.tahoma_8b.writeText(var1, mResources.mv[0] + ": " + this.g[var2], GameCanvas.centerX, this.q - 45, 2);
                   mFont.tahoma_7b_white.writeText(var1, mResources.mv[1] + ": " + this.f[var2], GameCanvas.centerX, this.q - 28, 2, mFont.tahoma_7b_blue);
                   mFont.tahoma_7b_white.writeText(var1, this.h[var2], GameCanvas.centerX, this.q - 16, 2, mFont.tahoma_7b_blue);
                }
             } else {
                var8 = this.q + this.n / 2 - 5;
                SmallImage.paintImage(var1, var6.partImages[Char.fe[0][3][0]].id, var7 + Char.fe[0][3][1] + var6.partImages[Char.fe[0][3][0]].dx, var8 - Char.fe[0][3][2] + var6.partImages[Char.fe[0][3][0]].dy, 0, 0);
                SmallImage.paintImage(var1, var3.partImages[Char.fe[0][0][0]].id, var7 + Char.fe[0][0][1] + var3.partImages[Char.fe[0][0][0]].dx, var8 - Char.fe[0][0][2] + var3.partImages[Char.fe[0][0][0]].dy, 0, 0);
                SmallImage.paintImage(var1, var4.partImages[Char.fe[0][1][0]].id, var7 + Char.fe[0][1][1] + var4.partImages[Char.fe[0][1][0]].dx, var8 - Char.fe[0][1][2] + var4.partImages[Char.fe[0][1][0]].dy, 0, 0);
                SmallImage.paintImage(var1, var5.partImages[Char.fe[0][2][0]].id, var7 + Char.fe[0][2][1] + var5.partImages[Char.fe[0][2][0]].dx, var8 - Char.fe[0][2][2] + var5.partImages[Char.fe[0][2][0]].dy, 0, 0);
                mFont.tahoma_8b.writeText(var1, this.g[var2], var7, this.q + this.n / 2 + 5, 2);
                mFont.tahoma_7b_white.writeText(var1, mResources.mv[1] + ": " + this.f[var2], var7, this.q + this.n / 2 + 22, 2);
                if (GameCanvas.width > 200) {
                   mFont.tahoma_7b_white.writeText(var1, this.h[var2], var7, this.q + this.n / 2 + 34, 2);
                }
             }
          }
       }
 
       super.paint(var1);
    }
 
    public final void perform(int var1, Object var2) {
       switch (var1) {
          case 1000:
             this.e();
             return;
          case 1001:
             Session_ME.getInstance().b();
             GameCanvas.instance.q();
          default:
       }
    }
 }
 
