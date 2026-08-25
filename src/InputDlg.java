

import javax.microedition.lcdui.Image;

public final class InputDlg extends Dialog {
   private String[] info;
   public TField tfInput;
   private int padLeft = 40;

   public InputDlg() {
      if (GameCanvas.width <= 176) {
         this.padLeft = 10;
      }

      this.tfInput = new TField();
      this.tfInput.a = this.padLeft + 10;
      this.tfInput.b = GameCanvas.height - mScreen.fo - 43;
      this.tfInput.width = GameCanvas.width - 2 * (this.padLeft + 10);
      this.tfInput.height = mScreen.fo + 2;
      this.tfInput.isFocus = true;
      super.right = this.tfInput.cmdClear;
   }

   public final void a(String var1, Command var2, int var3) {
      try {
         this.tfInput.a("");
         this.tfInput.c(var3);
         this.info = mFont.tahoma_8b.b(var1, GameCanvas.width - (this.padLeft << 1));
         super.left = new Command(mResources.am, GameCanvas.b(), 8882, (Object)null);
         super.center = var2;
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

         GameCanvas.currentDialog = this;
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   public final void a(mGraphics var1) {
      Paint var10000 = GameCanvas.am;
      Paint.a(var1, this.padLeft, GameCanvas.height - 77 - mScreen.cmdH, GameCanvas.width - (this.padLeft << 1), 69, this.info, (Image)null);
      this.tfInput.a(var1);
      super.a(var1);
   }

   public final void a(int var1) {
      this.tfInput.a(var1);
      super.a(var1);
   }

   public final void a() {
      this.tfInput.c();
      super.a();
   }
}
