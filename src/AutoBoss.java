
// Source code is decompiled from instance .class file using FernFlower decompiler.
public final class AutoBoss extends Auto {
    private int a;
 
    public AutoBoss(int var1) {
       this.a = var1;
    }
 
    public final void a(String var1) {
       if (var1.startsWith("Thần thú đã xuất hiện tại")) {
          short var2 = -1;
          if (this.a >= 40 && this.a < 50) {
             if (var1.indexOf("Đảo Hebi") > 0) {
                var2 = 34;
             }
 
             if (var1.indexOf("Hang Meiro") > 0) {
                var2 = 35;
             }
 
             if (var1.indexOf("Rừng Kappa") > 0) {
                var2 = 52;
             }
 
             if (var1.indexOf("Rừng Aokigahara") > 0) {
                var2 = 14;
             }
 
             if (var1.indexOf("Vách núi Ito") > 0) {
                var2 = 15;
             }
 
             if (var1.indexOf("Núi Anzen") > 0) {
                var2 = 68;
             }
 
             if (var1.indexOf("Thung lũng Taira") > 0) {
                var2 = 16;
             }
          }
 
          if (this.a >= 50 && this.a < 60) {
             if (var1.indexOf("Núi Ontake") > 0) {
                var2 = 67;
             }
 
             if (var1.indexOf("Đỉnh Okama") > 0) {
                var2 = 44;
             }
          }
 
          if (this.a >= 60 && this.a < 70) {
             if (var1.indexOf("Khu đá đỏ Akai") > 0) {
                var2 = 41;
             }
 
             if (var1.indexOf("Mũi Hone") > 0) {
                var2 = 59;
             }
 
             if (var1.indexOf("Đỉnh Ichidai") > 0) {
                var2 = 24;
             }
 
             if (var1.indexOf("Hang núi Kurai") > 0) {
                var2 = 45;
             }
          }
 
          if (this.a >= 70 && this.a < 80) {
             if (var1.indexOf("Ngôi đền Orochi") > 0) {
                var2 = 19;
             }
 
             if (var1.indexOf("Đồng Kisei") > 0) {
                var2 = 36;
             }
 
             if (var1.indexOf("Đền Harumoto") > 0) {
                var2 = 54;
             }
          }
 
          if (this.a >= 90 && this.a < 100 && var1.indexOf("Đoạn Sơn") > 0) {
             var2 = 141;
          }
 
          if (this.a >= 100 && this.a < 110 && var1.indexOf("Đảo Quỷ") > 0) {
             var2 = 142;
          }
 
          if (this.a >= 110 && var1.indexOf("Sinh Tử Kiều") > 0) {
             var2 = 143;
          }
 
          if (var2 > 0) {
             Code var10000 = Code.instance;
             Code.setAuto(new AutoPKBoss(var2));
          }
       }
 
    }
 
    protected final void run() {
    }
 
    public final String toString() {
       return "Chờ boss " + this.a;
    }
 }
 