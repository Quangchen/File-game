

final class Sender implements Runnable {
    private MyVector vMessage;
    private Session_ME session;
 
    public Sender(Session_ME var1) {
       this.session = var1;
       this.vMessage = new MyVector();
    }
 
    public final void a() {
       this.vMessage.removeAllElements();
    }
 
    public final void addMessage(Message var1) {
       this.vMessage.addElement(var1);
    }
 
    public final void run() {
       try {
          for(; this.session.connected; Thread.sleep(10L)) {
             if (Session_ME.g(this.session)) {
                while(this.vMessage.size() > 0) {
                   Message ms = (Message)this.vMessage.elementAt(0);
                   this.vMessage.removeElementAt(0);
                   Session_ME.sendCommand(this.session, ms);
                }
             }
          }
 
       } catch (InterruptedException var2) {
       }
    }
 }
 