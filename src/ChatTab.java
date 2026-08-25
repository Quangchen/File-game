

public final class ChatTab {
    public int a;
    public String b;
    public MyVector c = new MyVector();
    public long d = 0L;
 
    public ChatTab(String var1, int var2) {
       this.b = var1;
       this.a = var2;
    }
 
    public final void a(String name, String msg) {
       boolean var3 = false;
       if (GameScr.ck && ChatManager.getInstance().e() == this && GameScr.indexRow == this.c.size() - 1) {
          var3 = true;
       }
 
       this.c.addElement("c3@" + name);
       MyVector var4 = mFont.tahoma_7_white.a(msg, 160);
 
       for(int var5 = 0; var5 < var4.size(); ++var5) {
          this.c.addElement("c0" + var4.elementAt(var5));
       }
 
       if (var3) {
          GameScr.getInstance().ah();
       }
 
       this.a();
    }
 
    private void a() {
       while(this.c.size() > 50) {
          this.c.removeElementAt(1);
       }
 
    }
 
    public final void a(String var1) {
       boolean var2 = false;
       if (GameScr.ck && ChatManager.getInstance().e() == this && GameScr.indexRow == this.c.size() - 1) {
          var2 = true;
       }
 
       MyVector var4 = mFont.tahoma_7_white.a(var1, 160);
 
       for(int var3 = 0; var3 < var4.size(); ++var3) {
          this.c.addElement(var4.elementAt(var3));
       }
 
       if (var2) {
          GameScr.getInstance().ah();
       }
 
       this.a();
    }
 }
 