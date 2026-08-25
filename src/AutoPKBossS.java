
public final class AutoPKBossS extends Auto {
    public int a;
    public int b;
    private int c;
    private MyVector d;
    private MyVector e;
    private long f;
 
    public AutoPKBossS(MyVector var1, MyVector var2, int var3) {
       this.a = 0;
       this.b = -1;
       this.c = var3;
       this.d = var1;
       this.e = var2;
    }
 
    public AutoPKBossS(int var1) {
       this.a = 1;
       this.b = -1;
       this.c = var1;
       this.f = System.currentTimeMillis();
    }
 
    protected final void run() {
       if (Char.getMyChar().cHP <= 0) {
          Auto.autoRemap(true);
       } else {
           try {
               Code var10000;
               switch (this.a) {
                   case 0:
                       if (TileMap.mapID == 38 && TileMap.zoneID == 21) {
                           LockGame.d(60000L);
                           this.a = 2;
                           return;
                       }
                       
                       this.goMap(38, 21, -1, -1);
                       return;
                   case 1:
                       if (System.currentTimeMillis() - this.f > 180000L) {
                           var10000 = Code.instance;
                           Code.backToInstance();
                           return;
                       }
                       
                       if (TileMap.mapID != 38 || TileMap.zoneID != 21) {
                           this.goMap(38, 21, -1, -1);
                           return;
                       }
                       break;
                   case 2:
                       if (this.d.size() > 0) {
                           int var1 = this.d.size() - 1;
                           super.mapID = ((Integer)this.d.elementAt(var1)).intValue();
                           this.b = ((Integer)this.e.elementAt(var1)).intValue();
                           this.d.removeElementAt(var1);
                           this.e.removeElementAt(var1);
                           this.a = 3;
                           Service.getInstance().k("pkms " + super.mapID + " " + this.b);
                           return;
                       }
                       
                       this.a = 4;
                       Service.getInstance().k("pkes");
                       return;
                   case 3:
                       if (this.b > 0) {
                           if (TileMap.mapID != this.b) {
                               this.goMap(this.b, -2, -1, -1);
                               return;
                           }
                           
                           GameScr.PickNpc(5, 1, 0);
                           if (LockGame.y()) {
                               this.a = 5;
                               var10000 = Code.instance;
                               Code.setAuto(new AutoPKBoss(super.mapID));
                               return;
                           }
                       }
                       break;
                   case 4:
                       if (this.c > 0) {
                           if (TileMap.mapID != this.c) {
                               this.goMap(this.c, -2, -1, -1);
                               return;
                           }
                           
                           GameScr.PickNpc(5, 1, 0);
                           if (LockGame.y()) {
                               var10000 = Code.instance;
                               Code.backToInstance();
                               return;
                           }
                       } else {
                           this.c = 22;
                       }
                       break;
                   case 5:
                       Thread.sleep(10000L);
                       if (Code.g != null && !Char.getMyChar().charName.equals(Code.g)) {
                           this.a = 1;
                           this.f = System.currentTimeMillis();
                           return;
                       } else {
                           this.a = 2;
                           return;
                       }
               }} catch (InterruptedException ex) {
               
           }
 
       }
    }
 
    public final String toString() {
       return "PKBoss S";
    }
 }
 