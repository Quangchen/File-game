public final class LanguageScr extends mScreen implements IActionListener {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e = -1;
 
    public LanguageScr() {
    }
 
    public final void update() {
       GameScr.gH = GameCanvas.height;
       if (GameCanvas.typeBg == 2) {
          GameCanvas.setBackground(0);
       } else {
          GameCanvas.setBackground(TileMap.bgID);
       }
 
       super.update();
       if (GameScr.a != null) {
          GameScr.a = null;
       }
 
       if ((TileMap.bgID = (byte)((int)(System.currentTimeMillis() % 9L))) == 5 || TileMap.bgID == 6) {
          TileMap.bgID = 4;
       }
 
       GameScr.a(true);
       GameScr.cmx = 100;
       this.a = 170;
       this.b = 175;
       if (GameCanvas.width == 128 || GameCanvas.height <= 208) {
          this.a = 126;
          this.b = 160;
       }
 
       this.c = GameCanvas.width / 2 - this.a / 2;
       this.d = GameCanvas.height / 2 - this.b / 2;
       if (GameCanvas.height <= 250) {
          this.d -= 10;
       }
 
       super.center = new Command(GameCanvas.isTouch ? "" : mResources.okey, this, 1000, (Object)null);
       super.right = new Command(mResources.exit, GameCanvas.instance, 8885, (Object)null);
       this.e = -1;
       if (!GameCanvas.isTouch) {
          this.e = 0;
       }
 
       if (GameCanvas.isTouch && GameCanvas.width >= 320) {
          super.center.x = GameCanvas.width / 2 - 35;
          super.right.x = GameCanvas.width / 2 + 88;
          super.center.y = super.right.y = GameCanvas.height - 26;
       }
 
    }
 
    public final void paint(mGraphics var1) {
       var1.setColor(0);
       var1.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
       GameCanvas.paint(var1);
       Paint.a(this.c, this.d, this.a, this.b, var1);
       var1.setColor(Paint.COLORDARK);
       var1.fillRoundRect(GameCanvas.centerX - mFont.tahoma_8b.a(mResources.ai) / 2 - 12, this.d + 7, mFont.tahoma_8b.a(mResources.ai) + 22, 24, 6, 6);
       var1.setColor(Paint.b);
       var1.drawRoundRect(GameCanvas.centerX - mFont.tahoma_8b.a(mResources.ai) / 2 - 12, this.d + 7, mFont.tahoma_8b.a(mResources.ai) + 22, 24, 6, 6);
       mFont.tahoma_8b.writeText(var1, mResources.ai, GameCanvas.centerX, this.d + 12, 2);
       String[] var2 = mResources.ak;
       int var3 = this.d + 50;
 
       for(int var4 = 0; var4 < var2.length; ++var4) {
          var1.setColor(Paint.COLORDARK);
          var1.fillRect(this.c + 10, var3 + var4 * 35, this.a - 20, 28);
          var1.setColor(5720393);
          var1.drawRect(this.c + 10, var3 + var4 * 35, this.a - 20, 28);
          if (var4 == this.e) {
             var1.setColor(Paint.b);
             var1.fillRect(this.c + 10, var3 + var4 * 35, this.a - 20, 28);
             var1.setColor(11053224);
             var1.drawRect(this.c + 10, var3 + var4 * 35, this.a - 20, 28);
          }
 
          mFont.tahoma_7b_white.writeText(var1, var2[var4], this.c + this.a / 2, var3 + var4 * 35 + 8, 2);
       }
 
       super.paint(var1);
       Paint.a(var1, super.left, super.center, super.right);
    }
 
    public final void c() {
       if (++GameScr.cmx > GameCanvas.width * 3 + 100) {
          GameScr.cmx = 100;
       }
 
       super.c();
    }
 
    public final void b() {
       if (GameCanvas.keyPressedz[2] || GameCanvas.keyPressedz[4] || GameCanvas.keyPressedz[6] || GameCanvas.keyPressedz[8]) {
          this.e = this.e == 0 ? 1 : 0;
       }
 
       if (GameCanvas.isPointerJustRelease && GameCanvas.b(this.c + 10, this.d + 45, this.a - 10, 70)) {
          if (GameCanvas.isPointerClick) {
             this.e = (GameCanvas.q - (this.d + 45)) / 35;
          }
 
          this.perform(1000, (Object)null);
       }
 
       super.b();
       GameCanvas.k();
    }
 
    public final void perform(int var1, Object var2) {
       switch (var1) {
          case 1000:
             GameCanvas.currentDialog = null;
             var1 = mResources.e = this.e == 0 ? 0 : 1;
             RMS.writeRecord("indLanguage", var1);
             mResources.a();
             RMS.a();
             GameCanvas.d();
             GameCanvas.af.update();
          default:
       }
    }
 }
 