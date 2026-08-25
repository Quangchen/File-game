

final class ConnectionMonitor implements Runnable {
    private NetworkConnector a;
 
    ConnectionMonitor(NetworkConnector var1) {
       this.a = var1;
    }
 
    public final void run() {
       try {
          Thread.sleep(20000L);
       } catch (InterruptedException var3) {
       }
 
       try {
          if (this.a.sessionME.g) {
             Session_ME.a(this.a.sessionME).interrupt();
             return;
          }
       } catch (Exception var2) {
       }
 
    }
 }
 