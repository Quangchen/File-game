

final class ReLogin implements Runnable {
    private Session_ME a;
 
    ReLogin(Session_ME var1) {
       this.a = var1;
    }
 
    public final void run() {
       do {
          try {
             GameCanvas.showWaiting();
             this.a.b();
             Thread.sleep(5000L);
             this.a.connect(Session_ME.ip, Session_ME.port);
             GameMidlet.typeArea = Session_ME.o;
             Service.getInstance().login(SelectServerScr.uname, SelectServerScr.pass, GameMidlet.CLIENT_VERSION);
             Session_ME.a(10000L);
             Service.getInstance().b(SelectCharScr.k);
             Session_ME.a(5000L);
          } catch (InterruptedException var2) {
             var2.printStackTrace();
          }
       } while(Session_ME.p && GameCanvas.mScreen != GameScr.a);
 
    }
 }
 
