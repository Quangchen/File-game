public final class DomSang {
    private int b;
    private int c;
    public int a = 0;
    private int d;
    private int e = 0;
 
    public DomSang(int var1, int var2, int var3) {
       this.b = var1;
       this.c = var2;
       this.d = var3;
    }
 
    public final void a() {
       if (this.d == 1) {
          ++this.e;
          if (this.e % 2 == 0) {
             ++this.a;
             return;
          }
       } else {
          if (this.d == 0) {
             ++this.a;
             return;
          }
 
          if (this.d == 2 || this.d == 3 || this.d == 4 || this.d == 5) {
             ++this.e;
             if (this.e % 2 == 0) {
                ++this.a;
             }
          }
       }
 
    }
 
    public final void a(mGraphics var1) {
       if (this.d == 0) {
          var1.drawRegion(GameScr.du, 0, this.a * 3, 3, 3, 0, this.b, this.c, 0);
       } else if (this.d == 1) {
          var1.drawRegion(GameScr.dv, 0, this.a * 20, 20, 20, 0, this.b, this.c, 33);
       } else if (this.d == 2) {
          var1.drawRegion(GameScr.en, 0, this.a * 15, 14, 15, 0, this.b + 20, this.c + 4, 20);
       } else if (this.d == 3) {
          var1.drawRegion(GameScr.ek, 0, this.a << 3, 8, 8, 0, this.b + 20, this.c + 4, 20);
       } else if (this.d == 4) {
          var1.drawRegion(GameScr.el, 0, this.a << 3, 8, 8, 0, this.b + 20, this.c + 4, 20);
       } else {
          if (this.d == 5) {
             var1.drawRegion(GameScr.em, 0, this.a * 14, 14, 14, 0, this.b + 20, this.c + 4, 20);
          }
 
       }
    }
 }
 