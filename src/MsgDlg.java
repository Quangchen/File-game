public final class MsgDlg extends Dialog {
    private String[] f;
    public boolean d;
    public int e;
    private int g;
    private int h = 30;
 
    public MsgDlg() {
       if (GameCanvas.width <= 176) {
          this.h = 10;
       }
 
    }
 
    public final void update() {
       this.a(mResources.textLoading, (Command)null, (Command)null, (Command)null);
       GameCanvas.currentDialog = this;
    }
 
    public final void a(String var1, Command var2, Command var3, Command var4) {
       this.f = mFont.tahoma_8b.b(var1, GameCanvas.width - ((this.h << 1) + 40));
       super.left = var2;
       super.center = var3;
       super.right = var4;
       if (var3 != null) {
          super.center.x = GameCanvas.width / 2 - 35;
          super.center.y = GameCanvas.height - 26;
          if (var2 != null) {
             super.left.x = GameCanvas.width / 2 - 115;
             super.left.y = GameCanvas.height - 26;
          }
 
          if (var4 != null) {
             super.right.x = GameCanvas.width / 2 + 45;
             super.right.y = GameCanvas.height - 26;
          }
       } else {
          if (var2 != null) {
             super.left.x = GameCanvas.width / 2 - 80;
             super.left.y = GameCanvas.height - 26;
          }
 
          if (var4 != null) {
             super.right.x = GameCanvas.width / 2 + 10;
             super.right.y = GameCanvas.height - 26;
          }
       }
 
       this.d = false;
       this.g = 80;
       if (this.f.length >= 5) {
          this.g = this.f.length * mFont.tahoma_8b.getHeight() + 20;
       }
 
    }
 
    public final void a(mGraphics var1) {
       int var2 = GameCanvas.height - this.g - 38;
       Paint.a(this.h, var2, GameCanvas.width - (this.h << 1), this.g, var1);
       var2 = var2 + (this.g - this.f.length * mFont.tahoma_8b.getHeight()) / 2 - 2;
       if (this.d) {
          var2 += 8;
          GameCanvas.a(GameCanvas.centerX, var2 - 12, var1);
       }
 
       for(int var3 = 0; var3 < this.f.length; var2 += mFont.tahoma_8b.getHeight()) {
          mFont.tahoma_8b.writeText(var1, this.f[var3], GameCanvas.centerX, var2, 2);
          ++var3;
       }
 
       super.a(var1);
    }
 
    public final void a() {
       if (this.e > 0) {
          --this.e;
          if (this.e == 1) {
             GameCanvas.setMaxTextLenght();
             this.e = 0;
          }
       }
 
       super.a();
    }
 }
 