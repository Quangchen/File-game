

public final class Party {
    public int a;
    public int b;
    public short c;
    public String d;
    public boolean e;
    public Char f;
    public int g;
 
    public Party(byte var1, int var2, String var3, int var4) {
       switch (var1) {
          case 0:
             this.c = 647;
             break;
          case 1:
             this.c = 1182;
             break;
          case 2:
             this.c = 1181;
             break;
          case 3:
             this.c = 643;
             break;
          case 4:
             this.c = 645;
             break;
          case 5:
             this.c = 676;
             break;
          case 6:
             this.c = 1119;
             break;
          case 7:
             this.c = 3632;
       }
 
       this.d = var3;
       this.b = var2;
       this.g = var4;
    }
 
    public Party(int var1, byte var2, String var3, boolean var4) {
       this.a = var1;
       this.e = var4;
       switch (var2) {
          case 0:
             this.c = 647;
             break;
          case 1:
             this.c = 1182;
             break;
          case 2:
             this.c = 1181;
             break;
          case 3:
             this.c = 643;
             break;
          case 4:
             this.c = 645;
             break;
          case 5:
             this.c = 676;
             break;
          case 6:
             this.c = 1119;
             break;
          case 7:
             this.c = 3632;
       }
 
       this.d = var3;
       if (var1 == Char.getMyChar().charID) {
          this.f = Char.getMyChar();
       } else {
          this.f = GameScr.getCharByID(var1);
       }
    }
 
    public static void a() {
       for(int var0 = 0; var0 < GameScr.vParty.size(); ++var0) {
          Party var1;
          if ((var1 = (Party)GameScr.vParty.elementAt(var0)).a != Char.getMyChar().charID) {
             var1.f = GameScr.getCharByID(var1.a);
          }
       }
 
    }
 
    public static void a(int var0) {
       for(int var1 = 0; var1 < GameScr.vParty.size(); ++var1) {
          Party var2;
          if ((var2 = (Party)GameScr.vParty.elementAt(var1)).a == var0) {
             var2.f = null;
             return;
          }
       }
 
    }
 }
 