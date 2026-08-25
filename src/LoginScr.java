

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.Image;

public final class LoginScr extends mScreen implements IActionListener {
   private TField d;
   private TField e;
   private TField f;
   private TField g;
   private static LoginScr instance;
   private int i;
   private int j;
   private int k;
   private int l;
   private boolean m = false;
   private boolean n = false;
   private Command o;
   private Command p;
   private Command q;
   private Command r;
   public static int a = 0;
   public static Image imgTitle = GameCanvas.loadImage("/tt.png");
   private int s;
   private String[] t;
   private int u = -1;
   private int v = 2;
   private int w = 0;
   private int x = -40;
   private int y = 1;
   public static boolean c;
   private String z = "";

   public final void update() {
      if (RMS.loadRMSString("random") == null) {
         RMS.writeRecord("random", SelectServerScr.e());
      }

      this.k = -50;
      this.n = false;
      GameScr.gH = GameCanvas.height;
      if (GameCanvas.typeBg == 2) {
         GameCanvas.setBackground(0);
      } else {
         GameCanvas.setBackground(TileMap.bgID);
      }

      super.update();
      if (GameScr.a != null) {
         GameScr.a = null;
      }

      if (GameCanvas.menu != null) {
         GameCanvas.menu = new Menu();
      }

      GameCanvas.c = false;
      int var1;
      if ((var1 = RMS.d("isSoftKey")) <= 0) {
         RMS.writeRecord("isSoftKey", 1);
         GameScr.ff = true;
      } else if (var1 == 1) {
         GameScr.ff = true;
      } else if (var1 == 2) {
         GameScr.ff = false;
      }

      super.left = this.r = new Command("Q.Mật Khẩu", this, 2005, (Object)null);
   }

   public final void a() {
      this.n = true;
      this.k = -50;
      GameScr.gH = GameCanvas.height;
      if (GameCanvas.typeBg == 2) {
         GameCanvas.setBackground(0);
      } else {
         GameCanvas.setBackground(TileMap.bgID);
      }

      super.update();
      if (GameScr.a != null) {
         GameScr.a = null;
      }

      if (GameCanvas.menu != null) {
         GameCanvas.menu = new Menu();
      }

      GameCanvas.c = false;
      super.left = this.r = new Command("Hủy", this, 20051, (Object)null);
   }

   public LoginScr() {
      instance = this;
      this.n = false;
      if ((TileMap.bgID = (byte)((int)(System.currentTimeMillis() % 9L))) == 5 || TileMap.bgID == 6) {
         TileMap.bgID = 4;
      }

      GameScr.a(true);
      GameScr.cmx = 100;
      if (GameCanvas.height > 200) {
         this.l = GameCanvas.centerY - 80;
      } else {
         this.l = GameCanvas.centerY - 65;
      }

      this.k = -50;
      this.j = GameCanvas.width - 30;
      if (this.j < 135) {
         this.j = 135;
      }

      if (this.j > 155) {
         this.j = 155;
      }

      this.s = GameCanvas.centerY - mScreen.fo - 5;
      if (GameCanvas.height <= 160) {
         this.s = 20;
      }

      this.d = new TField();
      this.d.name = mResources.ce;
      this.d.a = GameCanvas.centerX - 20 - 57;
      this.d.b = this.s;
      this.d.width = this.j;
      this.d.height = mScreen.fo + 2;
      this.d.isFocus = true;
      this.d.c(3);
      this.e = new TField();
      this.e.name = mResources.cf;
      this.e.a = GameCanvas.centerX - 20 - 57;
      this.e.b = this.s += 35;
      this.e.width = this.j;
      this.e.height = mScreen.fo + 2;
      this.e.isFocus = false;
      this.e.c(2);
      this.f = new TField();
      this.f.name = mResources.cl;
      this.f.a = GameCanvas.centerX - 20 - 57;
      this.f.b = this.s += 35;
      this.f.width = this.j;
      this.f.height = mScreen.fo + 2;
      this.f.isFocus = false;
      this.f.c(2);
      this.g = new TField();
      this.g.name = "Email/Số di động";
      this.g.a = GameCanvas.centerX - 20 - 57;
      this.g.b = this.s += 35;
      this.g.width = this.j;
      this.g.height = mScreen.fo + 2;
      this.g.isFocus = false;
      this.g.c(3);
      this.m = true;
      if (SelectServerScr.uname != null) {
         if (SelectServerScr.uname.startsWith("tmpusr")) {
            this.d.a("");
            this.e.a("");
         } else {
            this.d.a(SelectServerScr.uname);
            this.e.a(SelectServerScr.pass);
         }
      }

      this.i = 0;
      this.o = new Command(mResources.okey, this, 2000, (Object)null);
      this.p = new Command(mResources.cm, this, 2001, (Object)null);
      this.q = new Command(mResources.okey, this, 2002, (Object)null);
      new Command(mResources.cp, this, 2004, (Object)null);
      if (!this.n) {
         super.left = this.r = new Command("Q.Mật Khẩu", this, 2005, (Object)null);
      } else {
         super.left = this.r = new Command("Hủy", this, 20051, (Object)null);
      }

      if (GameCanvas.isTouch && GameCanvas.width >= 320) {
         super.center = null;
         super.right = this.o;
      } else {
         super.center = this.o;
         super.right = this.d.cmdClear;
      }
   }

   public static LoginScr e() {
      return instance;
   }

   private static void a(boolean var0) {
      GameCanvas.c = var0;
      RMS.writeRecord("isGPRS", var0 ? 1 : 2);
   }

   private void a(String var1) {
      ServerInfo.selectServer(0);
      GameCanvas.showDialogWait(mResources.dp);
      GameCanvas.c();
      GameCanvas.showDialogWait(mResources.dq);
      Service.getInstance().setClientType();
      Service.getInstance().b(var1, this.e.getText(), this.g.getText());
   }

   private void f() {
      this.u = GameCanvas.gameTick % mResources.ac.length;
      this.t = mFont.tahoma_7_white.b(mResources.ac[this.u], GameCanvas.width - 40);
      String var1 = this.d.getText().toLowerCase().trim();
      String var2 = this.e.getText().toLowerCase().trim();
      if (var1.equals("a") && var2.equals("a")) {
         a = 1;
      } else if (var1.equals("b") && var2.equals("b")) {
         a = 2;
      }

      if (var1 != null && var2 != null && !var1.equals("")) {
         if (var2.equals("")) {
            this.i = 1;
            this.d.isFocus = false;
            this.e.isFocus = true;
            super.right = this.e.cmdClear;
            return;
         }

         GameCanvas.showDialogWait(mResources.dp);
         GameCanvas.c();
         GameCanvas.showDialogWait(mResources.dr);
         Service.getInstance().login(var1, var2, GameMidlet.CLIENT_VERSION);
         c = true;
         if (this.m) {
            RMS.writeRecord("check", 1);
            RMS.writeRecord("acc", var1);
            RMS.writeRecord("pass", var2);
         } else {
            RMS.writeRecord("check", 2);
            RMS.writeRecord("acc", "");
            RMS.writeRecord("pass", "");
         }

         this.i = 0;
      }

   }

   public final void c() {
      if (++GameScr.cmx > GameCanvas.width * 3 + 100) {
         GameScr.cmx = 100;
      }

      this.d.c();
      this.e.c();
      if (this.n) {
         this.f.c();
         this.g.c();
      }

      if (this.l != this.k) {
         this.k += this.l - this.k >> 1;
      }

      if (GameCanvas.isTouch) {
         super.center = null;
         if (this.n) {
            super.right = this.q;
         } else {
            super.right = this.o;
         }
      } else if (this.n) {
         super.center = this.q;
      } else if (this.i == 2) {
         super.center = this.p;
         if (this.m) {
            super.center.caption = mResources.cn;
         } else {
            super.center.caption = mResources.cm;
         }
      } else {
         super.center = this.o;
      }

      if (this.w >= 0) {
         this.x += this.y * this.w;
         this.w += this.y * this.v;
         if (this.w <= 0) {
            this.y = -this.y;
         }

         if (this.x > 0) {
            this.y = -this.y;
            this.w -= 2 * this.v;
         }
      }

      if (this.u >= 0 && GameCanvas.gameTick % 100 == 0) {
         ++this.u;
         if (this.u >= mResources.ac.length) {
            this.u = 0;
         }

         this.t = mFont.tahoma_7_white.b(mResources.ac[this.u], GameCanvas.width - 40);
      }

   }

   public final void a(int var1) {
      if (this.d.isFocus) {
         this.d.a(var1);
      } else if (this.e.isFocus) {
         this.e.a(var1);
      } else if (this.n && this.f.isFocus) {
         this.f.a(var1);
      } else if (this.n && this.g.isFocus) {
         this.g.a(var1);
      }

      super.a(var1);
   }

   public final void am() {
      super.am();
   }

   public final void paint(mGraphics var1) {
      var1.setColor(0);
      var1.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
      GameCanvas.paint(var1);
      int var2 = this.d.b - 45;
      if (GameCanvas.height <= 220) {
         var2 += 5;
      }

      if (GameCanvas.currentDialog == null) {
         if (this.n) {
            Paint.a(GameCanvas.centerX - 85, this.d.b - 15, 170, 150, var1);
         } else {
            Paint.a(GameCanvas.centerX - 85, this.d.b - 15, 170, 90, var1);
         }

         if (GameCanvas.height > 160 && imgTitle != null) {
            var1.drawImage(imgTitle, GameCanvas.centerX, var2 - 2, 3);
         }

         this.d.a(var1);
         this.e.a(var1);
         if (this.n) {
            this.f.a(var1);
            this.g.a(var1);
         }

         var1.setClip(0, 0, GameCanvas.width, GameCanvas.height);
         if (GameCanvas.width > 200) {
            if (this.d.getText().equals("")) {
               if (!this.d.isFocus) {
                  mFont.tahoma_7b_white.writeText(var1, mResources.ce, this.d.a + 5, this.d.b + 7, 0);
               } else {
                  mFont.tahoma_7_grey.writeText(var1, mResources.ce, this.d.a + 5, this.d.b + 7, 0);
               }
            }

            if (this.e.getText().equals("")) {
               if (!this.e.isFocus) {
                  mFont.tahoma_7b_white.writeText(var1, mResources.cf, this.e.a + 5, this.e.b + 7, 0);
               } else {
                  mFont.tahoma_7_grey.writeText(var1, mResources.cf, this.e.a + 5, this.e.b + 7, 0);
               }
            }

            if (this.n) {
               if (this.f.getText().equals("")) {
                  if (!this.f.isFocus) {
                     mFont.tahoma_7b_white.writeText(var1, mResources.cg, this.f.a + 5, this.f.b + 7, 0);
                     mFont.tahoma_7b_white.writeText(var1, mResources.cf, this.f.a + 50, this.f.b + 7, 0);
                  } else {
                     mFont.tahoma_7_grey.writeText(var1, mResources.cg, this.f.a + 5, this.f.b + 7, 0);
                     mFont.tahoma_7_grey.writeText(var1, mResources.cf, this.f.a + 50, this.f.b + 7, 0);
                  }
               }

               if (this.g.getText().equals("")) {
                  if (!this.g.isFocus) {
                     mFont.tahoma_7b_white.writeText(var1, "Email/số di động", this.g.a + 5, this.g.b + 5, 0);
                  } else {
                     mFont.tahoma_7_grey.writeText(var1, "Email/số di động", this.g.a + 5, this.g.b + 5, 0);
                  }
               }
            }
         } else {
            if (this.d.getText().equals("")) {
               mFont.tahoma_7b_white.writeText(var1, mResources.ch, this.d.a - 35, this.d.b + 7, 0);
            }

            if (this.e.getText().equals("")) {
               mFont.tahoma_7b_white.writeText(var1, mResources.ci, this.e.a - 35, this.e.b + 7, 0);
            }

            if (this.n) {
               mFont.tahoma_7b_white.writeText(var1, mResources.cj, this.f.a - 35, this.f.b - 1, 0);
               mFont.tahoma_7b_white.writeText(var1, mResources.ci, this.f.a - 35, this.f.b + 13, 0);
               mFont.tahoma_7b_white.writeText(var1, "Email/số di động", this.g.a - 35, this.g.b + 5, 0);
            }
         }
      } else if (this.t != null) {
         for(var2 = 0; var2 < this.t.length; ++var2) {
            mFont.tahoma_7_white.writeText(var1, this.t[var2], GameCanvas.width / 2, this.d.b - 15 + var2 * 10, 2, mFont.tahoma_7_grey);
         }
      }

      String var3 = "1.6.7";
      if (c) {
         var3 = Session_ME.getInstance().l;
      }

      mFont.tahoma_7_grey.writeText(var1, var3, GameCanvas.width - 5, 5, 1);
      super.paint(var1);
   }

   public final void b() {
      if (GameCanvas.keyPressedz[2]) {
         --this.i;
         if (this.i < 0) {
            this.i = 3;
         }
      } else if (GameCanvas.keyPressedz[8]) {
         ++this.i;
         if (this.i > 3) {
            this.i = 0;
         }
      }

      if (GameCanvas.keyPressedz[2] || GameCanvas.keyPressedz[8]) {
         GameCanvas.k();
         if (this.i == 1) {
            this.d.isFocus = false;
            this.e.isFocus = true;
            this.f.isFocus = false;
            this.g.isFocus = false;
            super.right = this.e.cmdClear;
         } else if (this.i == 0) {
            this.d.isFocus = true;
            this.e.isFocus = false;
            this.f.isFocus = false;
            this.g.isFocus = false;
            super.right = this.d.cmdClear;
         } else {
            this.d.isFocus = false;
            this.e.isFocus = false;
            if (this.n) {
               if (this.i == 2) {
                  this.f.isFocus = true;
                  this.g.isFocus = false;
                  super.right = this.f.cmdClear;
               } else if (this.i == 3) {
                  this.g.isFocus = true;
                  this.f.isFocus = false;
                  super.right = this.g.cmdClear;
               }
            }
         }
      }

      if (GameCanvas.isPointerJustRelease) {
         if (GameCanvas.b(this.d.a, this.d.b, this.d.width, this.d.height)) {
            this.i = 0;
         } else if (GameCanvas.b(this.e.a, this.e.b, this.e.width, this.e.height)) {
            this.i = 1;
         } else {
            if (this.n) {
               if (GameCanvas.b(this.f.a, this.f.b, this.f.width, this.f.height)) {
                  this.i = 2;
               } else if (GameCanvas.b(this.g.a, this.g.b, this.g.width, this.g.height)) {
                  this.i = 3;
               }
            } else if (GameCanvas.b(this.d.a - 20, GameCanvas.centerY + 40, 80, 20)) {
               this.m = !this.m;
            }

            this.i = 2;
         }
      }

      super.b();
      GameCanvas.k();
   }

   public final void perform(int var1, Object var2) {
      switch (var1) {
         case 1002:
            this.n = true;
            this.f.isFocus = false;
            this.g.isFocus = false;
            this.e.isFocus = false;
            this.d.isFocus = true;
            super.right = this.d.cmdClear;
            super.left = new Command(mResources.huy, this, 10021, (Object)null);
            return;
         case 1003:
            try {
               GameMidlet.instance.platformRequest("http://ninjaschool.vn");
               return;
            } catch (ConnectionNotFoundException var3) {
               var3.printStackTrace();
               return;
            }
         case 1004:
            MyVector var6 = new MyVector();
            var1 = RMS.d("lowGraphic");
            if (!GameCanvas.isTouch) {
               if (var1 == 1) {
                  var6.addElement(new Command(mResources.cq, this, 10041, (Object)null));
               } else {
                  var6.addElement(new Command(mResources.cr, this, 10042, (Object)null));
               }
            }

            var6.addElement(new Command(mResources.ai, this, 1006, (Object)null));
            if (GameCanvas.mScreen == this) {
               var6.addElement(new Command(mResources.aj, this, 1009, (Object)null));
            }

            GameCanvas.menu.showMenu(var6);
            return;
         case 1005:
            GameCanvas.a(mResources.cz, new Command("3G/Wifi", this, 3000, (Object)null), new Command("GPRS", this, 3001, (Object)null));
            return;
         case 1006:
            GameCanvas.a(mResources.al, new Command(mResources.di, this, 10061, (Object)null), new Command(mResources.ca, GameCanvas.b(), 8882, (Object)null));
            return;
         case 1009:
            RMS.a();
            return;
         case 2000:
            if (!this.d.getText().equals("") && !this.e.getText().equals("")) {
               SelectServerScr.unameChange = this.d.getText();
               SelectServerScr.passChange = this.e.getText();
            }

            GameCanvas.af.update();
            return;
         case 2001:
            if (this.m) {
               this.m = false;
               return;
            }

            this.m = true;
            return;
         case 2002:
            if (this.d.getText().equals("")) {
               GameCanvas.setText(mResources.cs);
               return;
            } else {
               char[] var5 = this.d.getText().toCharArray();

               for(var1 = 0; var1 < var5.length; ++var1) {
                  if (!TField.a(var5[var1])) {
                     GameCanvas.setText(mResources.ct);
                     return;
                  }
               }

               if (this.e.getText().equals("")) {
                  GameCanvas.setText(mResources.cu);
                  return;
               } else if (this.f.getText().equals("")) {
                  GameCanvas.setText(mResources.cv);
                  return;
               } else {
                  this.f.getText().equals("");
                  if (this.d.getText().length() < 5) {
                     GameCanvas.setText(mResources.cw);
                     return;
                  } else if (!this.e.getText().equals(this.f.getText())) {
                     GameCanvas.setText(mResources.cx);
                     return;
                  } else if (!this.g.getText().equals("")) {
                     GameCanvas.msgdlg.a(mResources.cy[0] + " " + this.d.getText() + ", " + mResources.cy[1], new Command(mResources.di, this, 4000, (Object)null), (Command)null, new Command(mResources.ca, GameCanvas.instance, 8882, (Object)null));
                     GameCanvas.currentDialog = GameCanvas.msgdlg;
                     return;
                  } else {
                     GameCanvas.a("Bạn chưa nhập Email/số di động, Email/số di động giúp bạn lấy lại mật khẩu khi mất mật khẩu", new Command("Tiếp Tục", this, 4001, (Object)null), new Command(mResources.ca, GameCanvas.instance, 8882, (Object)null));
                     return;
                  }
               }
            }
         case 2003:
            GameMidlet.a("https://nsokudo.com");
            return;
         case 2004:
            GameCanvas.ak.a(mResources.kd, new Command(mResources.okey, this, 20041, (Object)null), 0);
            return;
         case 2005:
            GameCanvas.a("Bạn có muốn reset mật khẩu không?", new Command(mResources.okey, this, 20052, (Object)null), new Command(mResources.ca, GameCanvas.instance, 8882, (Object)null));
            return;
         case 3000:
            a(false);
            GameCanvas.setMaxTextLenght();
            return;
         case 3001:
            a(true);
            GameCanvas.setMaxTextLenght();
            return;
         case 4000:
            this.a(this.d.getText());
            return;
         case 4001:
            this.a(this.d.getText());
            return;
         case 10021:
            this.n = false;
            this.f.isFocus = false;
            this.e.isFocus = false;
            this.d.isFocus = true;
            super.right = this.d.cmdClear;
            super.left = this.r;
            return;
         case 10041:
            RMS.writeRecord("lowGraphic", 0);
            GameCanvas.a(mResources.ck, 8885);
            return;
         case 10042:
            RMS.writeRecord("lowGraphic", 1);
            GameCanvas.a(mResources.ck, 8885);
            return;
         case 10051:
            RMS.writeRecord("isSoftKey", 1);
            GameScr.ff = true;
            return;
         case 10052:
            RMS.writeRecord("isSoftKey", 2);
            GameScr.ff = false;
            return;
         case 10061:
            GameCanvas.setMaxTextLenght();
            RMS.writeRecord("indLanguage", -1);
            GameMidlet.instance.notifyDestroyed();
            return;
         
         case 20041:
            this.z = GameCanvas.ak.tfInput.getText().toString();
            GameCanvas.setMaxTextLenght();
            if (this.z.equals("")) {
               GameCanvas.setText(mResources.cs);
               return;
            }

            GameCanvas.a(mResources.da, new Command(mResources.bn, this, 200421, (Object)null), new Command(mResources.ca, this, 200422, (Object)null));
            return;
         case 20051:
            GameScr.getInstance().update();
            return;
         case 20052:
            GameMidlet.a("https://nsokudo.com");
            return;
         case 200422:
            GameCanvas.setText(mResources.a(mResources.pf, this.z));
            return;
         default:
      }
   }
}
