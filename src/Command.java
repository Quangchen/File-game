

import javax.microedition.lcdui.Image;

public final class Command {
   public String caption;
   public String[] subCaption;
   public IActionListener actionListener;
   public int idAction;
   public Image e;
   public int x = 0;
   public int y = 0;
   public int w;
   public int h;
   private boolean isFocus;
   public Object obj;

   public Command(String var1, IActionListener var2, int var3, Object var4, int var5, int var6) {
      this.w = mScreen.cmdW;
      this.h = mScreen.cmdH;
      this.isFocus = false;
      this.caption = var1;
      this.idAction = var3;
      this.actionListener = var2;
      this.obj = null;
      this.x = var5;
      this.y = var6;
      this.w = mScreen.cmdW;
      this.h = mScreen.cmdH;
   }

   public Command(String var1, IActionListener var2, int var3, Object var4) {
      this.w = mScreen.cmdW;
      this.h = mScreen.cmdH;
      this.isFocus = false;
      this.caption = var1;
      this.idAction = var3;
      this.actionListener = var2;
      this.obj = var4;
   }

   public Command(String var1, int var2, Object var3) {
      this.w = mScreen.cmdW;
      this.h = mScreen.cmdH;
      this.isFocus = false;
      this.caption = var1;
      this.idAction = var2;
      this.obj = var3;
   }

   public Command(String var1, int var2) {
      this.w = mScreen.cmdW;
      this.h = mScreen.cmdH;
      this.isFocus = false;
      this.caption = var1;
      this.idAction = var2;
   }

   public Command(String var1, int var2, int var3) {
      this.w = mScreen.cmdW;
      this.h = mScreen.cmdH;
      this.isFocus = false;
      this.caption = var1;
      this.idAction = 0;
      this.x = var2;
      this.y = var3;
   }

   public final void a() {
      if (this.idAction > 0) {
         if (this.actionListener != null) {
            this.actionListener.perform(this.idAction, this.obj);
            return;
         }

         GameScr.getInstance().b(this.idAction, this.obj);
      }

   }

   public final void a(mGraphics var1) {
      if (FormToiUu.shouldHideGameButtons()) {
         var1.setColor(16711680);
         var1.drawRect(this.x - 3, this.y - 3, this.w + 6, this.h + 6);
         if (this.w > 4 && this.h > 4) {
            var1.drawRect(this.x - 2, this.y - 2, this.w + 4, this.h + 4);
         }
         return;
      }

      if (this.e != null) {
         var1.drawImage(this.e, this.x + mGraphics.getWidth(this.e) / 2, this.y + mGraphics.getHeight(this.e) / 2, 3);
      } else {
         if (this.caption != "") {
            if (!this.isFocus) {
               var1.drawImage(GameScr.ds, this.x, this.y, 0);
            } else {
               var1.drawImage(GameScr.dt, this.x, this.y, 0);
            }
         }

         mFont.tahoma_7b_yellow.writeText(var1, this.caption, this.x + 36, this.y + 6, 2);
      }
   }

   public final boolean b() {
      this.isFocus = false;
      if (GameCanvas.b(this.x - 3, this.y - 3, this.w + 6, this.h + 6)) {
         if (GameCanvas.m) {
            this.isFocus = true;
         }

         if (GameCanvas.isPointerJustRelease && GameCanvas.isPointerClick) {
            return true;
         }
      }

      return false;
   }

   public final void c() {
      if (this.x > 0 && this.y > 0 && this.b()) {
         this.a();
         GameCanvas.m = false;
         this.isFocus = false;
      }

   }
}
