
// Source code is decompiled from setAuto .class file using FernFlower decompiler.
public final class SettingAutoMuaBan {
    private static MyVector b = new MyVector();
    public static int a = -1;
 
    public static void a(ItemTemplate var0, String var1) {
       RMS var4;
       if ((var4 = RMS.a(var0, var1)) != null) {
          for(int var2 = 0; var2 < b.size(); ++var2) {
             if (((RMS)b.elementAt(var2)).a.id == var0.id) {
                b.setElementAt(var4, var2);
                return;
             }
          }
 
          b.addElement(var4);
       }
 
    }
 
    public static void a(ItemTemplate var0) {
       for(int var1 = 0; var1 < b.size(); ++var1) {
          if (((RMS)b.elementAt(var1)).a.id == var0.id) {
             b.removeElementAt(var1);
             return;
          }
       }
 
    }
 
    public static RMS a(short var0) {
       for(int var1 = 0; var1 < b.size(); ++var1) {
          RMS var2;
          if ((var2 = (RMS)b.elementAt(var1)).a.id == var0) {
             return var2;
          }
       }
 
       return null;
    }
 
    public static RMS a(String var0) {
       for(int var1 = 0; var1 < b.size(); ++var1) {
          RMS var2;
          if ((var2 = (RMS)b.elementAt(var1)).b.equals(var0)) {
             return var2;
          }
       }
 
       return null;
    }
 
    public static MyVector a() {
       MyVector var0 = new MyVector();
 
       for(int var1 = 0; var1 < b.size(); ++var1) {
          RMS var2 = (RMS)b.elementAt(var1);
          var0.addElement(var1 + ". " + var2.a.name + " id " + var2.b);
          if (var2.f > 0) {
             var0.addElement("Mua " + var2.g + "k/" + var2.f + " max " + var2.h);
          }
 
          if (var2.c > 0) {
             var0.addElement("Bán " + var2.d + "k/" + var2.c + " min " + var2.e);
          }
       }
 
       return var0;
    }
 
    public static String b() {
       if (b.size() == 0) {
          return "";
       } else {
          if (a >= b.size() || a < 0) {
             a = 0;
          }
 
          int var0 = a;
 
          do {
             RMS var1;
             boolean var2 = (var1 = (RMS)b.elementAt(a = (a + 1) % b.size())).b();
             boolean var3 = var1.c();
             if (var2 && var3) {
                return "@" + Char.getMyChar().charName + " bán " + var1.b + " " + var1.d + "k " + var1.c + " cái, mua " + var1.g + "k " + var1.f + " cai, mua bán pm";
             }
 
             if (var2) {
                return "@" + Char.getMyChar().charName + " bán " + var1.b + " " + var1.d + "k " + var1.c + " cái, mua pm";
             }
 
             if (var3) {
                return "@" + Char.getMyChar().charName + " mua " + var1.b + " " + var1.g + "k " + var1.f + " cái, bán pm hoặc gd";
             }
          } while(var0 != a);
 
          return "";
       }
    }
 
    public static void b(String var0) {
       for(int var1 = 0; var1 < b.size(); ++var1) {
          RMS var2;
          if (Char.getItemByID((var2 = (RMS)b.elementAt(var1)).a.id) != null) {
             boolean var3 = var2.b();
             boolean var4 = var2.c();
             if (var3 && var4) {
                Code.a(var0, "id: " + var2.b + " " + var2.a.name + " bán: " + var2.d + "k/" + var2.c + "cái, mua: " + var2.g + "k/" + var2.f + " cái. Còn " + var2.d() + " cái.");
             } else if (var3) {
                Code.a(var0, "id: " + var2.b + " " + var2.a.name + " bán: " + var2.d + "k/" + var2.c + "cái. Còn " + var2.d() + " cái.");
             } else if (var4) {
                Code.a(var0, "id: " + var2.b + " " + var2.a.name + " mua: " + var2.g + "k/" + var2.f + " cái. Còn mua: " + var2.e());
             }
          }
       }
 
    }
 
    public static boolean c() {
       for(int var0 = 0; var0 < b.size(); ++var0) {
          if (((RMS)b.elementAt(var0)).c()) {
             return true;
          }
       }
 
       return false;
    }
 
    public static void c(String var0) {
       MyVector var1 = new MyVector();
 
       RMS var3;
       for(int var2 = 0; var2 < b.size(); ++var2) {
          if ((var3 = (RMS)b.elementAt(var2)).b()) {
             var1.addElement(var3);
          }
       }
 
       if (var1.size() > 0) {
          RMS var4;
          if (var1.size() == 1) {
             var4 = (RMS)var1.firstElement();
             Code.a(var0, "- Để mua hàng chat buy idvp solg hoặc mua solg idvp");
             Code.a(var0, "     Vd mua 12 " + var4.a.name);
             Code.a(var0, "     chat mua 12 " + var4.b + " hoặc buy " + var4.b + " 12");
          } else {
             var4 = (RMS)var1.elementAt(0);
             var3 = (RMS)var1.elementAt(1);
             Code.a(var0, "- Để mua hàng chat buy idvp1 solg1 idvp2 solg2.... hoặc mua solg1 idvp1 solg2 idvp2...");
             Code.a(var0, "     Vd mua 5 " + var4.a.name + " 5 " + var3.a.name);
             Code.a(var0, "     chat mua 5 " + var4.b + " 5 " + var3.b + " hoặc buy " + var4.b + " 5 " + var3.b + " 5");
          }
       }
 
       if (c()) {
          Code.a(var0, "- Để bán hàng chat sell hoặc giao dịch");
       }
 
       Code.a(var0, "- Để Donate chat donate. Thank");
    }
 }
 