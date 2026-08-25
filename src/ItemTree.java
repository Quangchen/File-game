

import javax.microedition.lcdui.Image;

public final class ItemTree {
   public int a;
   private int b;
   private int c;

   public ItemTree(int var1, int var2) {
      this.b = var1 * 24 + 12;
      this.c = var2 * 24 + 24 + 3;
   }

   private Object a() {
      Object var1;
      return (var1 = TileMap.itemMap.get(String.valueOf(this.a))) != null ? var1 : null;
   }

   public final void a(mGraphics var1) {
      if (!GameCanvas.lowGraphic && this.a() != null) {
         var1.drawImage((Image)this.a(), this.b, this.c, 33);
      }

   }
}
