

public final class CreateCharScr extends mScreen implements IActionListener {
    private static CreateCharScr a;
    private static TField b;
    private static int c;
    private static int d;
    private static int e;
    private static int[][] f = new int[][]{{11, 26, 27, 28}, {2, 23, 24, 25}};
    private static int[] g = new int[]{9, 0};
    private static int[] h = new int[]{10, 1};
    private Command i;
    private Command j;
    private int k = 0;
 
    public static CreateCharScr a() {
       if (a == null) {
          a = new CreateCharScr();
       }
 
       return a;
    }
 
    public CreateCharScr() {
       if (GameCanvas.width == 128) {
          GameScr.b(128, 120);
          GameScr.popupX = (GameCanvas.width - 128) / 2;
          GameScr.popupY = 0;
       } else {
          GameScr.b(170, 190);
          GameScr.popupX = (GameCanvas.width - 170) / 2;
          GameScr.popupY = (GameCanvas.height - 220) / 2;
       }
 
       c = 1;
       (b = new TField()).name = mResources.eh;
       b.width = 100;
       b.a = GameScr.popupX + 45;
       b.a = GameScr.popupX + GameScr.ew / 2 - b.width / 2;
       b.b = GameScr.popupY + 62;
       if (GameCanvas.width == 128) {
          b.width = 60;
       }
 
       b.height = 26;
       b.isFocus = true;
       b.c(0);
       c = 1;
       d = 0;
       super.center = new Command(mResources.eg, this, 8000, (Object)null);
       super.left = new Command(mResources.el, this, 8001, (Object)null);
       this.i = new Command("", this, 8002, (Object)null, GameScr.popupX + GameScr.ew / 2 - mScreen.cmdW / 2, GameScr.popupY + 70 + 30);
       this.j = new Command("", this, 8003, (Object)null, GameScr.popupX + GameScr.ew / 2 - mScreen.cmdW / 2, GameScr.popupY + 70 + 70);
       super.right = b.cmdClear;
    }
 
    public final void update() {
       c = GameCanvas.gameTick % 2;
       d = GameCanvas.gameTick % 4;
       super.update();
    }
 
    public final void a(int var1) {
       b.a(var1);
    }
 
    public final void c() {
       if (++GameScr.cmx > GameCanvas.width * 3 + 100) {
          GameScr.cmx = 100;
       }
 
       if (GameCanvas.isTouch && GameCanvas.width >= 320) {
          if (super.left != null) {
             super.left.x = GameCanvas.width / 2 - 160;
             super.left.y = GameCanvas.height - 26;
          }
 
          if (super.center != null) {
             super.center.x = GameCanvas.width / 2 - 35;
             super.center.y = GameCanvas.height - 26;
          }
 
          if (super.right != null) {
             super.right.x = GameCanvas.width / 2 + 88;
             super.right.y = GameCanvas.height - 26;
          }
       }
 
       if (this.j != null) {
          this.j.c();
       }
 
       if (this.i != null) {
          this.i.c();
       }
 
    }
 
    public final void b() {
       if (GameCanvas.keyPressedz[2] && --e < 0) {
          e = mResources.ix.length - 1;
       }
 
       if (GameCanvas.keyPressedz[8] && ++e >= mResources.ix.length) {
          e = 0;
       }
 
       if (e == 0) {
          super.right = b.cmdClear;
          b.c();
       }
 
       if (e == 1) {
          if (GameCanvas.keyPressedz[4] && --c < 0) {
             c = mResources.iy.length - 1;
          }
 
          if (GameCanvas.keyPressedz[6] && ++c > mResources.iy.length - 1) {
             c = 0;
          }
 
          super.right = null;
       }
 
       if (e == 2) {
          if (GameCanvas.keyPressedz[4] && --d < 0) {
             d = mResources.fm[0].length - 1;
          }
 
          if (GameCanvas.keyPressedz[6] && ++d > mResources.fm[0].length - 1) {
             d = 0;
          }
       }
 
       if (GameCanvas.isPointerJustRelease && GameCanvas.b(GameScr.popupX + 5, GameScr.popupY + 65, GameScr.ew - 5, mScreen.fo)) {
          e = 0;
       }
 
       super.b();
       GameCanvas.l();
       GameCanvas.k();
    }
 
    public final void paint(mGraphics var1) {
       try {
          GameCanvas.paint(var1);
          Paint.a(GameScr.popupX, GameScr.popupY, GameScr.ew, GameScr.ex, var1);
          byte var2 = 40;
          if (GameCanvas.width == 128) {
             var2 = 20;
          }
 
          int var3 = f[c][d];
          int var4 = g[c];
          int var5 = h[c];
          Part var9 = GameScr.parts[var3];
          Part var10 = GameScr.parts[var4];
          Part var11 = GameScr.parts[var5];
          int var6 = GameCanvas.width / 2;
          int var7 = GameScr.popupY + 50;
          SmallImage.paintImage(var1, var9.partImages[Char.fe[0][0][0]].id, var6 + Char.fe[0][0][1] + var9.partImages[Char.fe[0][0][0]].dx, var7 - Char.fe[0][0][2] + var9.partImages[Char.fe[0][0][0]].dy, 0, 0);
          SmallImage.paintImage(var1, var10.partImages[Char.fe[0][1][0]].id, var6 + Char.fe[0][1][1] + var10.partImages[Char.fe[0][1][0]].dx, var7 - Char.fe[0][1][2] + var10.partImages[Char.fe[0][1][0]].dy, 0, 0);
          SmallImage.paintImage(var1, var11.partImages[Char.fe[0][2][0]].id, var6 + Char.fe[0][2][1] + var11.partImages[Char.fe[0][2][0]].dx, var7 - Char.fe[0][2][2] + var11.partImages[Char.fe[0][2][0]].dy, 0, 0);
          if (!GameCanvas.isTouch) {
             for(var3 = 0; var3 < mResources.ix.length; ++var3) {
                if (e == var3) {
                   var4 = var3 > 0 ? -5 : 0;
                   SmallImage.paintImage(var1, 989, GameScr.popupX + 10 + (GameCanvas.gameTick % 7 > 3 ? 1 : 0), GameScr.popupY + 76 + var3 * var2 + var4, 2, StaticObj.g);
                   SmallImage.paintImage(var1, 989, GameScr.popupX + GameScr.ew - 15 - (GameCanvas.gameTick % 7 > 3 ? 1 : 0), GameScr.popupY + 76 + var3 * var2 + var4, 0, StaticObj.g);
                }
             }
          }
 
          if (GameCanvas.isTouch) {
             this.i.caption = mResources.iy[c];
             this.i.a(var1);
             this.j.caption = mResources.fm[c][d];
             this.j.a(var1);
          } else {
             Paint.b(this.i.x, this.i.y, this.i.w, this.i.h, var1);
             if (e == 1) {
                var1.setColor(Paint.b);
                var1.fillRect(this.i.x, this.i.y, this.i.w, this.i.h);
                Paint.b(this.i.x, this.i.y, this.i.w, this.i.h, var1);
             }
 
             Paint.b(this.j.x, this.j.y, this.j.w, this.j.h, var1);
             if (e == 2) {
                var1.setColor(Paint.b);
                var1.fillRect(this.j.x, this.j.y, this.j.w, this.j.h);
                Paint.b(this.j.x, this.j.y, this.j.w, this.j.h, var1);
             }
 
             mFont.tahoma_7b_white.writeText(var1, mResources.iy[c], GameScr.popupX + 85, GameScr.popupY + 66 + var2, 2);
             mFont.tahoma_7b_white.writeText(var1, mResources.fm[c][d], GameScr.popupX + 85, GameScr.popupY + 66 + (var2 << 1), 2);
          }
 
          b.a = GameScr.popupX + GameScr.ew / 2 - b.width / 2;
          b.b = GameScr.popupY + 62;
          b.title_Null = mResources.eh;
          b.a(var1);
          super.paint(var1);
          var1.setColor(0);
       } catch (Exception var8) {
          System.out.println("CreateCharScr.paint(): 0");
          var8.printStackTrace();
       }
    }
 
    public final void perform(int var1, Object var2) {
       switch (var1) {
          case 8000:
             Service.getInstance().a(b.getText(), c, f[c][d]);
             return;
          case 8001:
             if (SelectCharScr.a().j) {
                GameCanvas.af.update();
                return;
             }
 
             SelectCharScr.a().update();
             return;
          case 8002:
             this.k = 1;
             e = 1;
             if (this.k == e && --c < 0) {
                c = mResources.iy.length - 1;
                return;
             }
             break;
          case 8003:
             this.k = 2;
             e = 2;
             if (this.k == e && ++d > mResources.fm[0].length - 1) {
                d = 0;
             }
       }
 
    }
 }
 