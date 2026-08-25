

public final class ChatTextField implements IActionListener {
    private static ChatTextField h;
    public TField a;
    public boolean isShow = false;
    public IChatable c;
    private long i = 0L;
    public Command d;
    public Command e;
    public Command f;
    public String g;
 
    public static ChatTextField a() {
       return h == null ? (h = new ChatTextField()) : h;
    }
 
    protected ChatTextField() {
       this.d = new Command(mResources.cc, this, 8000, (Object)null, 1, GameCanvas.height - mScreen.cmdH + 1);
       this.e = new Command(mResources.bb, this, 8001, (Object)null, GameCanvas.width - 53, GameCanvas.height - mScreen.cmdH + 1);
       this.f = null;
       this.a = new TField();
       this.a.name = "chat";
       this.a.a = 16;
       GameCanvas var10001 = GameCanvas.instance;
       this.a.width = MotherCanvas.instance.c() - 32;
       this.a.height = mScreen.fo + 2;
       this.a.isFocus = true;
       this.a.setMaxTextLenght(40);
    }
 
    public final void a(int var1, IChatable var2, String var3) {
       this.e.caption = mResources.am;
       this.g = var3;
       this.a.a(var1);
       if (!this.a.getText().equals("") && GameCanvas.currentDialog == null) {
          this.c = var2;
          this.isShow = true;
       }
 
       this.a.title_Null = var3;
    }
 
    public final void a(String var1) {
       this.e.caption = mResources.am;
       this.g = var1;
       if (GameCanvas.currentDialog == null) {
          this.isShow = true;
          if (GameCanvas.isTouch) {
             this.a.a();
          }
       }
 
       this.a.title_Null = var1;
    }
 
    public final void a(mGraphics var1) {
       if (this.isShow) {
          this.a.a(var1);
       }
 
    }
 
    public final void perform(int var1, Object var2) {
       switch (var1) {
          case 8000:
             if (this.c != null) {
                long var3;
                if ((var3 = System.currentTimeMillis()) - this.i < 1000L) {
                   return;
                }
 
                this.i = var3;
                this.c.a(this.a.getText(), this.g);
                this.a.a("");
                this.e.caption = mResources.am;
                return;
             }
             break;
          case 8001:
             this.a.b();
             if (this.a.getText().equals("")) {
                this.isShow = false;
                this.c.aj();
             }
       }
 
    }
 }
 