public final class LockGame {
    private static boolean c = false;
    public static boolean a = false;
    public static boolean b = false;
    private static boolean d = false;
    private static boolean e = false;
    private static boolean f = false;
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;
    private static boolean l = false;
    private static boolean m = false;
    private static boolean n = false;
    private static boolean o = false;
    private static boolean p = false;
    private static boolean lockDV = false;
    private static Object lock = new Object();
    private static int r;
    private static boolean s = false;
 
    public static void a() {
       c = true;
       synchronized(lock) {
          try {
             lock.wait(2000L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void b() {
       if (c) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          c = false;
       }
 
    }
 
    public static boolean c() {
       b = true;
       long var0 = System.currentTimeMillis();
       synchronized(lock) {
          try {
             lock.wait(500L);
          } catch (InterruptedException var3) {
          }
       }
 
       return System.currentTimeMillis() - var0 < 500L;
    }
 
    public static void d() {
       if (b) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          b = false;
       }
 
    }
 
    public static void e() {
       d = true;
       synchronized(lock) {
          try {
             lock.wait(2000L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void f() {
       if (d) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          d = false;
       }
 
    }
 
    public static void g() {
       e = true;
       synchronized(lock) {
          try {
             lock.wait(2000L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void h() {
       if (e) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          e = false;
       }
 
    }
 
    public static void i() {
       j = true;
       synchronized(lock) {
          try {
             lock.wait();
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void j() {
       if (j) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          j = false;
       }
 
    }
 
    public static void k() {
       f = true;
       synchronized(lock) {
          try {
             lock.wait(2000L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void l() {
       if (f) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          f = false;
       }
 
    }
 
    public static void m() {
       g = true;
       synchronized(lock) {
          try {
             lock.wait(2000L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void n() {
       if (g) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          g = false;
       }
 
    }
 
    public static void o() {
       h = true;
       synchronized(lock) {
          try {
             lock.wait(2000L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void p() {
       if (h) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          h = false;
       }
 
    }
 
    public static void q() {
       i = true;
       synchronized(lock) {
          try {
             lock.wait(2000L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void r() {
       if (i) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          i = false;
       }
 
    }
 
    public static boolean s() {
       k = true;
       long var0 = System.currentTimeMillis();
       synchronized(lock) {
          try {
             lock.wait(7000L);
          } catch (InterruptedException var3) {
          }
       }
 
       return System.currentTimeMillis() - var0 < 7000L;
    }
 
    public static void t() {
       if (k) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          k = false;
       }
 
    }
 
    public static boolean u() {
       r = Auto.selectSkill != null ? Auto.selectSkill.point : 0;
       l = true;
       synchronized(lock) {
          try {
             lock.wait(3000L);
          } catch (InterruptedException var1) {
          }
       }
 
       return Auto.selectSkill == null || Auto.selectSkill.point > r;
    }
 
    public static void v() {
       if (l) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          l = false;
       }
 
    }
 
    public static void w() {
       m = true;
       synchronized(lock) {
          try {
             lock.wait(3000L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void x() {
       if (m) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          m = false;
       }
 
    }
 
    public static boolean y() {
       n = true;
       long var0 = System.currentTimeMillis();
       synchronized(lock) {
          try {
             lock.wait(5000L);
          } catch (InterruptedException var3) {
          }
       }
 
       return System.currentTimeMillis() - var0 < 5000L;
    }
 
    public static void z() {
       if (n) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          n = false;
       }
 
    }
 
    public static boolean a(long var0) {
       o = true;
       if (var0 < 0L) {
          synchronized(lock) {
             try {
                lock.wait();
             } catch (InterruptedException var4) {
             }
          }
 
          if (AutoSell.a) {
             AutoSell.a = false;
             return false;
          } else {
             return true;
          }
       } else {
          long var2 = System.currentTimeMillis();
          synchronized(lock) {
             try {
                lock.wait(20000L);
             } catch (InterruptedException var6) {
             }
          }
 
          if (AutoSell.a) {
             AutoSell.a = false;
             return false;
          } else {
             return System.currentTimeMillis() - var2 < 20000L;
          }
       }
    }
 
    public static void aa() {
       if (o) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          o = false;
       }
 
    }
 
    public static void ab() {
       p = true;
       synchronized(lock) {
          try {
             lock.wait(500L);
          } catch (InterruptedException var1) {
          }
 
       }
    }
 
    public static void ac() {
       if (p) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          p = false;
       }
 
    }
    
    public static boolean waitDV() {
      System.out.println("WaitDV");
      lockDV = true;
      long var0 = System.currentTimeMillis();
      synchronized(lock) {
         try {
            lock.wait(2000L);
         } catch (InterruptedException var3) {
         }
      }

      return System.currentTimeMillis() - var0 < 2000L;
   }

   public static void notifyDV() {
      System.out.println("NotifyDV");
      if (lockDV) {
         synchronized(lock) {
            lock.notifyAll();
         }

         lockDV = false;
      }

   }
 
    public static boolean ad() {
       int var0 = 0;
 
       label31:
       while(var0 < Code.h.size()) {
          String var1 = (String)Code.h.elementAt(var0);
 
          for(int var2 = 0; var2 < GameScr.vParty.size(); ++var2) {
             Party var3;
             if ((var3 = (Party)GameScr.vParty.elementAt(var2)).d.equals(var1)) {
                if (var3.f == null || Res.distance(Char.getMyChar().cx, Char.getMyChar().cy, var3.f.cx, var3.f.cy) > 100) {
                   return false;
                }
 
                ++var0;
                continue label31;
             }
          }
 
          return false;
       }
 
       return true;
    }
 
    public static boolean ae() {
       label23:
       for(int var0 = 0; var0 < Code.h.size(); ++var0) {
          String var1 = (String)Code.h.elementAt(var0);
 
          for(int var2 = 0; var2 < GameScr.vParty.size(); ++var2) {
             if (((Party)GameScr.vParty.elementAt(var2)).d.equals(var1)) {
                continue label23;
             }
          }
 
          return false;
       }
 
       return true;
    }
 
    public static void b(long var0) {
       if (Code.g != null) {
          long var2 = System.currentTimeMillis();
 
          while(!ad() && System.currentTimeMillis() - var2 < var0) {
              try {
                  Thread.sleep(2000L);
              } catch (InterruptedException ex) {
                  
              }
          }
 
       }
    }
 
    public static void c(long var0) {
       if (Code.g != null) {
          long var2 = System.currentTimeMillis();
 
          while(true) {
             int var6 = 0;
 
             boolean var10000;
             label37:
             while(true) {
                if (var6 >= Code.h.size()) {
                   var10000 = true;
                   break;
                }
 
                String var1 = (String)Code.h.elementAt(var6);
 
                for(int var4 = 0; var4 < GameScr.vParty.size(); ++var4) {
                   Party var5;
                   if ((var5 = (Party)GameScr.vParty.elementAt(var4)).d.equals(var1)) {
                      if (var5.f == null) {
                         var10000 = false;
                         break label37;
                      }
 
                      ++var6;
                      continue label37;
                   }
                }
 
                var10000 = false;
                break;
             }
 
             if (var10000 || System.currentTimeMillis() - var2 >= 300000L) {
                return;
             }
 
              try {
                  Thread.sleep(2000L);
              } catch (InterruptedException ex) {
                  
              }
          }
       }
    }
 
    public static void d(long var0) {
       if (Code.g != null) {
          long var2 = System.currentTimeMillis();
 
          while(!ae() && System.currentTimeMillis() - var2 < 60000L) {
              try {
                  Thread.sleep(2000L);
              } catch (InterruptedException ex) {
                  
              }
          }
 
       }
    }
 
    public static void tatAuto() {
       synchronized(lock) {
          lock.notifyAll();
       }
 
       b = false;
       d = false;
       e = false;
       f = false;
       g = false;
       h = false;
       j = false;
       c = false;
       s = false;
       i = false;
       a = false;
       k = false;
       l = false;
       m = false;
       n = false;
       o = false;
       p = false;
       TileMap.ag = false;
       TileMap.h();
    }
 
    public static boolean e(long var0) {
       s = true;
       if (var0 < 0L) {
          synchronized(lock) {
             try {
                lock.wait();
             } catch (InterruptedException var5) {
             }
          }
 
          if (AutoReceiver.ab) {
             AutoReceiver.ab = false;
             return false;
          } else {
             return true;
          }
       } else {
          long var2 = System.currentTimeMillis();
          synchronized(lock) {
             try {
                lock.wait(var0);
             } catch (InterruptedException var7) {
             }
          }
 
          if (AutoReceiver.ab) {
             AutoReceiver.ab = false;
             return false;
          } else {
             return System.currentTimeMillis() - var2 < var0;
          }
       }
    }
 
    public static void ag() {
       if (s) {
          synchronized(lock) {
             lock.notifyAll();
          }
 
          s = false;
       }
 
    }
 }
 