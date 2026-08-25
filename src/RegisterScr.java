

public final class RegisterScr extends mScreen implements IActionListener {
    public static RegisterScr a;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private TField[] j = new TField[7];
    private String[] k = new String[]{"Họ và tên", "Ngày, tháng, năm sinh", "Địa chỉ thường trú", "CMND", "Ngày cấp", "Nơi cấp", "Số điện thoại, địa chỉ thư điện tử (nếu có).", "* Dưới 18 tuổi chỉ có thể chơi 180p 1 ngày"};
    private String[] l = new String[]{"Nguyen Van A", "17-03-1991", "34 Phan A p.12 q.BT", "987654321", "01-01-2011", "TP.Hồ Chí Minh", "abc@gmail.com", ""};
    public Scroll b = new Scroll();
 
    public RegisterScr() {
       a = this;
       this.c = GameCanvas.centerX - 100;
       this.d = 10;
       this.e = 200;
       this.f = GameCanvas.height - 40;
       this.g = this.e - 40;
       this.h = GameCanvas.centerX - this.g / 2;
 
       for(int var1 = 0; var1 < this.j.length; ++var1) {
          this.j[var1] = new TField();
          this.j[var1].name = this.k[var1];
          this.j[var1].a = this.h;
          this.j[var1].b = (var1 + 1) * 50;
          this.j[var1].width = this.g;
          this.j[var1].height = mScreen.fo + 2;
          this.j[var1].isFocus = false;
          this.j[var1].c(3);
          this.j[var1].a(this.l[var1]);
          if (var1 == 0) {
             this.j[var1].isFocus = true;
          }
       }
 
       super.left = new Command(mResources.co, this, 1, (Object)null);
    }
 
    public final void c() {
       super.c();
       if (++GameScr.cmx > GameCanvas.width * 3 + 100) {
          GameScr.cmx = 100;
       }
 
       int var1;
       for(var1 = 0; var1 < this.j.length; ++var1) {
          this.j[var1].c();
       }
 
       new ScrollResult();
       ScrollResult var2;
       if ((var2 = this.b.b()).a || var2.c) {
          this.i = (byte)var2.b;
       }
 
       this.b.c();
       if (this.i == -1) {
          for(var1 = 0; var1 < this.j.length; ++var1) {
             this.j[var1].isFocus = false;
          }
       }
 
    }
 
    public final void a(int var1) {
       super.a(var1);
 
       for(int var2 = 0; var2 < this.j.length; ++var2) {
          if (this.j[var2].isFocus) {
             this.j[var2].a(var1);
          }
       }
 
    }
 
    public final void b() {
       if (GameCanvas.keyPressedz[2]) {
          --this.i;
          if (this.i < 0) {
             this.i = this.k.length;
          }
 
          this.b.a(this.i * this.b.h);
       } else if (GameCanvas.keyPressedz[8]) {
          ++this.i;
          if (this.i > this.k.length) {
             this.i = 0;
          }
 
          this.b.a(this.i * this.b.h);
       }
 
       int var1;
       if (GameCanvas.keyPressedz[2] || GameCanvas.keyPressedz[8]) {
          GameCanvas.k();
 
          for(var1 = 0; var1 < this.j.length; ++var1) {
             this.j[var1].isFocus = false;
          }
 
          if (this.i < this.j.length) {
             this.j[this.i].isFocus = true;
          }
 
          this.b.a(this.i * this.b.h);
       }
 
       if (GameCanvas.b(this.c, this.d, this.e, this.f) && GameCanvas.isPointerJustRelease) {
          for(var1 = 0; var1 < this.j.length; ++var1) {
             if (GameCanvas.b(this.j[var1].a, this.j[var1].b, this.j[var1].width, this.j[var1].height)) {
                this.i = var1;
             }
          }
       }
 
       super.b();
       GameCanvas.k();
    }
 
    public final void paint(mGraphics var1) {
       var1.setColor(0);
       var1.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
       GameCanvas.paint(var1);
       Paint.a(this.c, this.d, this.e, this.f, var1);
       mFont.tahoma_7b_white.writeText(var1, mResources.co, GameCanvas.centerX, this.d + 10, 2);
       this.b.a(this.k.length, 50, this.c, this.d + 25, this.e, this.f - 25, true, 0);
       this.b.a(var1, this.c, this.d + 25, this.e, this.f - 25);
 
       int var2;
       for(var2 = 0; var2 < this.k.length; ++var2) {
          if (var2 != this.k.length - 1) {
             mFont.tahoma_7_yellow.writeText(var1, this.k[var2], GameCanvas.centerX, (var2 + 1) * 50 - 13, 2);
          } else {
             mFont.tahoma_7_red.writeText(var1, this.k[var2], GameCanvas.centerX, (var2 + 1) * 50 - 13, 2);
          }
       }
 
       for(var2 = 0; var2 < this.j.length; ++var2) {
          this.j[var2].b(var1);
       }
 
       super.paint(var1);
    }
 
    public final void update() {
       super.update();
       this.b.a();
    }
 
    public final void perform(int var1, Object var2) {
       switch (var1) {
          case 1:
             Service.getInstance().a(this.j[0].getText(), this.j[1].getText(), this.j[2].getText(), this.j[3].getText(), this.j[4].getText(), this.j[5].getText(), this.j[6].getText());
             GameCanvas.av = false;
             GameScr.getInstance().update();
          default:
       }
    }
 }
 