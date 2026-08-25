

public final class EggMonters {
    public int a;
    public int b;
    public int c = 0;
    public byte d = 0;
    public int e;
    public static Mob f;
 
    public final boolean a() {
       if (this.a < GameScr.cmx) {
          return false;
       } else if (this.a > GameScr.cmx + GameScr.gW) {
          return false;
       } else if (this.b < GameScr.cmy) {
          return false;
       } else if (this.b > GameScr.cmy + GameScr.gH + 30) {
          return false;
       } else {
          return f == null || f.h != 8;
       }
    }
 
    public EggMonters(int var1, int var2) {
       this.a = var1;
       this.b = var2;
    }
 }
 