

import javax.microedition.lcdui.Image;

public final class Input2Dlg extends Dialog implements IActionListener {
   private String[] f;
   public TField d;
   public TField e;
   private int g;
   private int h;
   private int i;
   private int j;

   public Input2Dlg() {
      new Command("", 1000, (Object)null);
      this.d = new TField();
      this.e = new TField();
   }

   public final void a(String var1, String var2) {
      this.j = mFont.tahoma_8b.a(var1) > mFont.tahoma_8b.a(var2) ? mFont.tahoma_8b.a(var1) : mFont.tahoma_8b.a(var2);
      this.g = 40;
      this.h = 0;
      if (GameCanvas.width <= 176) {
         this.g = 10;
      }

      this.d.a = this.g + this.j + 10;
      this.d.b = GameCanvas.height - 2 * mScreen.fo - 50;
      this.d.width = GameCanvas.width - 2 * this.g - this.j - 20;
      this.d.height = mScreen.fo + 2;
      this.d.isFocus = true;
      this.d.f = var1;
      this.e.a = this.g + this.j + 10;
      this.e.b = GameCanvas.height - mScreen.fo - 43;
      this.e.width = GameCanvas.width - 2 * this.g - this.j - 20;
      this.e.height = mScreen.fo + 2;
      this.e.f = var2;
      super.right = this.d.cmdClear;
   }

   public final void a(String var1, Command var2, Command var3, int var4, int var5) {
      this.d.a("");
      this.d.c(var4);
      this.e.a("");
      this.e.c(var5);
      this.f = mFont.tahoma_8b.b(var1, GameCanvas.width - this.g * 3);
      MyVector var6 = mFont.tahoma_8b.a(var1, GameCanvas.width - this.g * 3);
      this.i = var6.size();
      super.left = var2;
      super.center = var3;
      GameCanvas.currentDialog = this;
   }

   public final void a(mGraphics var1) {
      Paint var10000 = GameCanvas.am;
      Paint.a(var1, this.g, GameCanvas.height - 85 - mScreen.cmdH - this.i * 13, GameCanvas.width - (this.g << 1), 80 + this.i * 13, this.f, (Image)null);
      mFont.tahoma_8b.writeText(var1, this.d.f + ": ", this.d.a - this.j - 5, this.d.b + 5, 0);
      this.d.a(var1);
      var1.setClip(0, 0, GameCanvas.width, GameCanvas.height);
      mFont.tahoma_8b.writeText(var1, this.e.f + ": ", this.e.a - this.j - 5, this.e.b + 5, 0);
      this.e.a(var1);
      super.a(var1);
   }

   public final void a(int var1) {
      if (this.h == 0) {
         this.d.a(var1);
      } else {
         this.e.a(var1);
      }

      super.a(var1);
   }

   public final void a() {
      if (GameCanvas.keyPressedz[2]) {
         this.h = 0;
      }

      if (GameCanvas.keyPressedz[8]) {
         this.h = 1;
      }

      if (this.h == 0) {
         this.d.isFocus = true;
         this.e.isFocus = false;
         super.right = this.d.cmdClear;
         this.d.c();
      } else {
         this.d.isFocus = false;
         this.e.isFocus = true;
         super.right = this.e.cmdClear;
         this.e.c();
      }

      if (GameCanvas.isTouch && GameCanvas.isPointerJustRelease && GameCanvas.isPointerClick) {
         if (GameCanvas.b(this.d.a, this.d.b, this.d.width, this.d.height)) {
            this.h = 0;
         }

         if (GameCanvas.b(this.e.a, this.e.b, this.e.width, this.e.height)) {
            this.h = 1;
         }
      }

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

      super.a();
   }

   public final void perform(int var1, Object var2) {
   }
}
