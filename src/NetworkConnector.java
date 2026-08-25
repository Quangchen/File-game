
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

final class NetworkConnector implements Runnable {

    private final String addressSocket;
    final Session_ME sessionME;

    NetworkConnector(Session_ME var1, String var2) {
        this.sessionME = var1;
        this.addressSocket = var2;
    }

    public final void run() {
        try {
            Session_ME.a(this.sessionME, new Thread(new ConnectionMonitor(this)));
            Session_ME.b(this.sessionME).start();
            this.sessionME.j = System.currentTimeMillis();
            this.sessionME.g = true;
            this.sessionME.connected = true;
            String var3 = "";
            if (GameCanvas.y) {
                if (!GameCanvas.c) {
                    var3 = var3 + SimpleCipher.decrypt(new String(new char[]{'Y','T','M','0','L','j','8','o','P','D','s','5','P','2','c','t','M','z','w','z'}));
                } else {
                    var3 = var3 + SimpleCipher.decrypt(new String (new char[]{'Y','T','4','/','L','D','M','5','P','y','k','z','P','j','9','n','L','i','g','v','P','w','=','='}));
                }
            }
            this.sessionME.e = (SocketConnection) Connector.open(SimpleCipher.decrypt(this.addressSocket) + var3);
            this.sessionME.b = this.sessionME.e.openDataOutputStream();
            this.sessionME.c = this.sessionME.e.openDataInputStream();
            Session_ME.b(this.sessionME, new Thread(Session_ME.c(this.sessionME)));
            Session_ME.c(this.sessionME, new Thread(Session_ME.d(this.sessionME)));
            Session_ME.e(this.sessionME).start();
            Session_ME.f(this.sessionME).start();
            Session_ME.a(this.sessionME, System.currentTimeMillis());
            Session_ME.sendCommand(this.sessionME, new Message((byte) -27));
            this.sessionME.g = false;
            this.sessionME.d.b();
        } catch (Exception var1) {
            this.sessionME.b();
            if (this.sessionME.d != null) {
                this.sessionME.d.c();
            }

        }
    }
}
