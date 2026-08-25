public final class AutoGiaoDich2 extends Auto {
    private String b;
    private boolean c;
    public static short a;
 
    public AutoGiaoDich2(String var1) {
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
             if (var2 < 12 && var5 != null && (var5.template.id == 652 || var5.template.id == 653 || var5.template.id == 654 || var5.template.id == 655) && !var5.isLock) {
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
                GameScr.chatPopup("H\u1ebft!");
                break;
             }
 
             for(int var7 = 0; var7 < 30; ++var7) {
                Auto.sleep(1000L);
                GameScr.chatPopup("Ch\u1edd " + (30 - var7) + " s \u0111\u1ec3 gd ti\u1ebfp !");
             }
          }
 
          GameScr.chatPopup("H\u00e0nh trang \u0111\u1ed1i ph\u01b0\u01a1ng kh\u00f4ng c\u00f2n \u0111\u1ee7 ch\u1ed7 tr\u1ed1ng!");
          if (!AutoSend.a) {
             GameScr.chatPopup("\u0110\u00e3 h\u1ebft vp c\u1ea7n gd trong h\u00e0nh trang, Ho\u1eb7c \u0111\u1ed1i ph\u01b0\u01a1ng k c\u00f3 trong khu v\u1ef1c n\u00e0y ! !");
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
          if ((var3 = var0[var2]) != null && (var3.template.id == 652 || var3.template.id == 653 || var3.template.id == 654 || var3.template.id == 655) && !var3.isLock) {
             ++var1;
          }
       }
 
       return var1;
    }
 }
 