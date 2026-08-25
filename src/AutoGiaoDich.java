
public final class AutoGiaoDich extends Auto {
    private String b;
    private boolean c;
    public static short a;
 
    public AutoGiaoDich(String var1) {
       super.a();
       this.b = var1;
    }
 
    public static void a(String var0) {
       Char var1 = Char.getMyChar();
       int var2 = 0;
 
       Char var10000;
       while(true) {
          if (var2 >= GameScr.vCharInMap.size()) {
             var10000 = null;
             break;
          }
 
          Char var3;
          if ((var3 = (Char)GameScr.vCharInMap.elementAt(var2)).charName.equals(var0)) {
             var10000 = var3;
             break;
          }
 
          ++var2;
       }
 
       Char var8 = var10000;
       if (var10000 == null) {
          AutoSend.c = true;
       } else {
          do {
             if (Res.distance(var1.cx, var1.cy, var8.cx, var8.cy) >= 50) {
                 try {
                     Char.charMove(var8.cx, var8.cy);
                     Thread.sleep(1000L);
                 } catch (InterruptedException ex) {
                     
                 }
             } else {
                Service.getInstance().tradeInvite(var8.charID);
             }
          } while(!GameScr.ci && !LockGame.a(1000L));
 
          Item[] var9 = new Item[12];
          var2 = 0;
 
          for(int var4 = 0; var4 < var1.arrItemBag.length; ++var4) {
             Item var5 = var1.arrItemBag[var4];
             if (var2 < 12 && var5 != null && var5.template.id == a && !var5.isLock) {
                var9[var2++] = var5;
             }
          }
 
          GameScr.getInstance().db = 0;
          GameScr.arrItemTradeMe = var9;
          Service.getInstance().tradeLock(0, var9);
          GameScr.getInstance().cz = 1;
          long var11 = System.currentTimeMillis();
 
          while(GameScr.getInstance().da != 1) {
              try {
                  if (AutoSell.a || System.currentTimeMillis() - var11 >= 20000L) {
                      return;
                  }
                  
                  Thread.sleep(200L);
              } catch (InterruptedException ex) {
                  
              }
          }
 
          var11 = System.currentTimeMillis();
 
          while(System.currentTimeMillis() - var11 < 5000L) {
              try {
                  if (AutoSell.a) {
                      return;
                  }
                  
                  Thread.sleep(200L);
              } catch (InterruptedException ex) {
                  
              }
          }
 
          Service.getInstance().j();
          if (LockGame.a(20000L)) {
             for(int var10 = 0; var10 < 12; ++var10) {
                if (var9[var10] != null) {
                   int var10001 = var9[var10].indexUI;
                   Char.getMyChar().arrItemBag[var10001] = null;
                }
             }
          }
 
       }
    }
 
    public final void run() {
       if (Char.getMyChar().cHP <= 0) {
          Auto.autoRemap(true);
       } else if (!this.c) {
          AutoSell.a = false;
 
          while(e() > 0) {
             a(this.b);
             if (AutoSend.a) {
                break;
             }
 
             if (e() <= 0) {
                GameScr.chatPopup("Hết!");
                break;
             }
 
             for(int var7 = 0; var7 < 30; ++var7) {
                Auto.sleep(1000L);
                GameScr.chatPopup("Chờ " + (30 - var7) + " s để gd tiếp !");
             }
          }
 
          GameScr.chatPopup("Hành trang đối phương không còn đủ chỗ trống!");
          if (!AutoSend.a) {
             GameScr.chatPopup("Đã hết vp cần gd trong hành trang, Hoặc đối phương k có trong khu vực này ! !");
          }
 
          this.c = true;
          Code var1 = Code.instance;
          Code.backToInstance();
       }
    }
 
    public final String toString() {
       return "Auto GD";
    }
 
    public static int e() {
       Item[] var0 = Char.getMyChar().arrItemBag;
       int var1 = 0;
 
       for(int var2 = 0; var2 < var0.length; ++var2) {
          Item var3;
          if ((var3 = var0[var2]) != null && var3.template.id == a && !var3.isLock) {
             ++var1;
          }
       }
 
       return var1;
    }
 }
 