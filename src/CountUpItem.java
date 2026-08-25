

public final class CountUpItem implements Runnable {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
 
    public CountUpItem() {
    }
 
    public static int a(int var0) {
       for(int var5 = 0; var5 < Char.getMyChar().arrItemBag.length; ++var5) {
          Item var6 = Char.getMyChar().arrItemBag[var5];
          if (var6 != null && var6.template.id == var0) {
             return var6.quantity;
          }
       }
 
       return -1;
    }
 
    public final void run() {
       for(int var1 = 0; var1 < Char.getMyChar().arrItemBag.length; ++var1) {
          Item var8 = Char.getMyChar().arrItemBag[var1];
          if (var8 != null && var8.template.id == 454) {
             a = var8.quantity;
          }
 
          if (var8 != null && var8.template.id == 455) {
             b = var8.quantity;
          }
 
          if (var8 != null && var8.template.id == 456) {
             c = var8.quantity;
          }
 
          if (var8 != null && var8.template.id == 457) {
             d = var8.quantity;
          }
       }
 
    }
 }
 