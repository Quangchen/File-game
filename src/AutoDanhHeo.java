
// Source code is decompiled from instance .class file using FernFlower decompiler.
public final class AutoDanhHeo extends Auto {
    public AutoDanhHeo() {
       super.a();
    }
 
    protected final void run() {
       Char var1;
       if ((var1 = Char.getMyChar()).cHP <= 0) {
          Auto.autoRemap(true);
       } else if (var1.ctaskId == 20 && var1.taskMaint.a < var1.taskMaint.e.length - 1) {
          if (TileMap.mapID == 74) {
             this.attack(69, -1);
             this.pickUpItem(221);
             if (Char.getMyChar().cMP < Char.getMyChar().cMaxMP / 2) {
                Char.getMyChar().e(17);
             }
 
             if (Char.getMyChar().cHP < Char.getMyChar().cMaxHP * 3 / 4) {
                int var4 = (int)(System.currentTimeMillis() / 1000L);
 
                for(int var2 = 0; var2 < Char.getMyChar().vEff.size(); ++var2) {
                   Effect var3;
                   if ((var3 = (Effect)Char.getMyChar().vEff.elementAt(var2)).e.a == 21 && var3.c - (var4 - var3.b) >= 2) {
                      return;
                   }
                }
 
                Char.getMyChar().e(16);
                return;
             }
          } else {
             this.goMap(74, -2, -1, -1);
          }
 
       } else {
          Code var10000 = Code.instance;
          Code.tatAuto();
       }
    }
 
    public final String toString() {
       return "Đánh Heo";
    }
 }
 