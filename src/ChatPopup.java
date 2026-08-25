
// Source code is decompiled from update .class file using FernFlower decompiler.
public final class ChatPopup extends Effect2 implements IActionListener {
    private int g = 100;
    private int h;
    private int i;
    private String[] j;
    private int k;
    private int l;
    private int m;
    private Char n;
    private int o;
    private String[] p;
    public Command a;
    public static ChatPopup currentMultilineChatPopup;
 
    public ChatPopup() {
    }
 
    public static void a(String var0, Char var1) {
       String[] var2;
       if ((var2 = Res.a(var0, "\n", 0)).length == 1) {
          chat(var2[0], 1000, var1);
       } else {
          (currentMultilineChatPopup = chat(var2[0], 1000, var1)).o = 0;
          currentMultilineChatPopup.p = var2;
          currentMultilineChatPopup.a = new Command(mResources.bh, currentMultilineChatPopup, 8000, (Object)null);
       }
    }
 
    public static ChatPopup chat(String var0, int var1, Char var2) {
       ChatPopup var3 = new ChatPopup();
       if (var0.length() < 10) {
          var3.g = 64;
       }
 
       if (GameCanvas.width == 128) {
          var3.g = 128;
       }
 
       var3.j = mFont.tahoma_7_red.b(var0, var3.g - 10);
       var3.h = var1;
       var3.n = var2;
       var3.k = var2.cx;
       var3.l = var2.cy;
       var2.chatPopup = var3;
       var3.i = 7;
       Effect2.vEffect2.addElement(var3);
       return var3;
    }
 
    public final void update() {
       if (this.n != null) {
          this.k = this.n.cx;
          this.l = this.n.cy;
          this.m = this.n.bj + 10;
       }
 
       if (this.h > 0) {
          --this.h;
       }
 
       if (this.i > 1) {
          --this.i;
       }
 
       if (this.n != null && this.n.chatPopup != null && this.n.chatPopup != this || this.n != null && this.n.chatPopup == null || this.h == 0) {
          Effect2.vEffect2Outside.removeElement(this);
          Effect2.vEffect2.removeElement(this);
       }
 
    }
 
    public final void paint(mGraphics var1) {
       int var2 = this.k;
       int var3 = this.l;
       var1.setColor(16777215);
       var1.fillRoundRect(var2 - this.g / 2 - 1, var3 - this.m - 15 + this.i - this.j.length * 12 - 10, this.g + 2, (this.j.length + 1) * 12 + 1, 12, 12);
       var1.setColor(0);
       var1.drawRoundRect(var2 - this.g / 2 - 1, var3 - this.m - 15 + this.i - this.j.length * 12 - 10, this.g + 1, (this.j.length + 1) * 12, 12, 12);
       SmallImage.paintImage(var1, 941, var2 - 3, var3 - this.m - 15 + this.i + 2, 0, 17);
 
       for(int var4 = 0; var4 < this.j.length; ++var4) {
          mFont.tahoma_7.writeText(var1, this.j[var4], var2, var3 - this.m - 15 + this.i + var4 * 12 - this.j.length * 12 - 4, 2);
       }
 
    }
 
    public final void perform(int var1, Object var2) {
       if (var1 == 8000) {
          var1 = currentMultilineChatPopup.o;
          ++var1;
          if (var1 >= currentMultilineChatPopup.p.length) {
             currentMultilineChatPopup.n.chatPopup = null;
             currentMultilineChatPopup = null;
             return;
          }
 
          ChatPopup var3;
          (var3 = chat(currentMultilineChatPopup.p[var1], currentMultilineChatPopup.h, currentMultilineChatPopup.n)).o = var1;
          var3.p = currentMultilineChatPopup.p;
          var3.a = currentMultilineChatPopup.a;
          currentMultilineChatPopup = var3;
       }
 
    }
 }
 