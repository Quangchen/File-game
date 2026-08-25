public final class SelectServerScr extends mScreen implements IActionListener {
    private int e;
    private int f;
    private int g;
    private int h;
    private int i = -1;
    private static String[] menu;
    public static String uname = RMS.loadRMSString("acc");
    public static String pass = RMS.loadRMSString("pass");
    public static String unameChange = "";
    public static String passChange = "";
    private static Command cmdChoiMoi = null;
    private static Command cmdDoiTaiKhoan = null;
    private static Command cmdChoiTiep = null;
    private static Command cmdChonServer = null;
    private static Command cmdUpdateServer = null;
    private static Command[][] cmd = null;
 
    static {
       if (uname == null) {
          uname = "";
       }
 
       if (pass == null) {
          pass = "";
       }
 
    }
 
    public SelectServerScr() {
        GameCanvas.menu.menuSelectedItem = ServerInfo.serverIndex[0];
        ServerInfo.selectServer(GameCanvas.menu.menuSelectedItem);

        for(int i = 0; i < ServerInfo.serverIndex.length; ++i) {
            if (RMS.d("indServer") == ServerInfo.serverIndex[i]) {
                GameCanvas.menu.menuSelectedItem = ServerInfo.serverIndex[i];
                ServerInfo.selectServer(i);
                return;
            }
        }
    }
 
    public final void update() {
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
 
       if ((TileMap.bgID = (byte)((int)(System.currentTimeMillis() % 9L))) == 5 || TileMap.bgID == 6) {
          TileMap.bgID = 4;
       }
 
       GameScr.a(true);
       GameScr.cmx = 100;
       this.e = 170;
       this.f = 175;
       if (GameCanvas.width == 128 || GameCanvas.height <= 208) {
          this.e = 126;
          this.f = 160;
       }
 
       this.g = GameCanvas.width / 2 - this.e / 2;
       this.h = GameCanvas.height / 2 - this.f / 2;
       if (GameCanvas.height <= 250) {
          this.h -= 10;
       }
 
       super.right = new Command(mResources.exit, GameCanvas.instance, 8885, (Object)null);
       this.i = -1;
       if (!GameCanvas.isTouch) {
          this.i = 0;
       }
 
       if (GameCanvas.isTouch && GameCanvas.width >= 320) {
          super.right.x = GameCanvas.width / 2 + 88;
       }
 
       if (cmdChoiMoi == null) {
          cmdChoiMoi = new Command(GameCanvas.isTouch ? "" : mResources.okey, this, 1000, (Object)null);
          cmdDoiTaiKhoan = new Command(GameCanvas.isTouch ? "" : mResources.okey, this, 1001, (Object)null);
          cmdChonServer = new Command(GameCanvas.isTouch ? "" : mResources.okey, this, 1002, (Object)null);
          cmdChoiTiep = new Command(GameCanvas.isTouch ? "" : mResources.okey, this, 1003, (Object)null);
          cmdUpdateServer = new Command(GameCanvas.isTouch ? "" : mResources.okey, this, 1004, (Object)null);
          cmd = new Command[][]{{cmdChoiMoi, cmdDoiTaiKhoan, cmdChonServer, cmdUpdateServer}, {cmdChoiTiep, cmdChoiMoi, cmdDoiTaiKhoan, cmdChonServer, cmdUpdateServer}};
       }
 
       if ((uname == null || uname.equals("")) && unameChange.equals("")) {
          menu = new String[]{mResources.si, mResources.doiTaiKhoan, mResources.mayChu, mResources.updateMayChu};
       } else {
          menu = new String[]{mResources.choiTiep, mResources.si, mResources.doiTaiKhoan, mResources.mayChu, mResources.updateMayChu};
       }
 
       GameCanvas.menu.menuSelectedItem = ServerInfo.serverIndex[0];
       ServerInfo.selectServer(GameCanvas.menu.menuSelectedItem);
 
       for(int i = 0; i < ServerInfo.serverIndex.length; ++i) {
          if (RMS.d("indServer") == ServerInfo.serverIndex[i]) {
             GameCanvas.menu.menuSelectedItem = ServerInfo.serverIndex[i];
             ServerInfo.selectServer(i);
             return;
          }
       }
 
       if (RMS.loadRMSString("random") == null) {
          RMS.writeRecord("random", e());
       }
 
    }
 
    public final void paint(mGraphics graphic) {
       graphic.setColor(0);
       graphic.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
       GameCanvas.paint(graphic);
       graphic.drawImage(LoginScr.imgTitle, GameCanvas.centerX - LoginScr.imgTitle.getWidth() / 2, this.h + 10 - LoginScr.imgTitle.getHeight() / 2, 0);
       if (GameCanvas.menu.menuSelectedItem == -1) {
          GameCanvas.menu.menuSelectedItem = 0;
       }
 
       int indexPaint = this.h + 50;
 
       for(int i = 0; i < menu.length; ++i) {
          graphic.setColor(Paint.COLORDARK);
          graphic.fillRect(this.g + 10, indexPaint + i * 35, this.e - 20, 28);
          Paint.b(this.g + 10, indexPaint + i * 35, this.e - 20, 28, graphic);
          if (i == this.i) {
             graphic.setColor(Paint.b);
             graphic.fillRect(this.g + 10, indexPaint + i * 35, this.e - 20, 28);
             Paint.b(this.g + 10, indexPaint + i * 35, this.e - 20, 28, graphic);
          }
 
          if (i < menu.length) {
             if (uname.equals("") && unameChange.equals("")) {
                if (i == 2) {
                   mFont.tahoma_7b_white.writeText(graphic, menu[i] + ServerInfo.listName[GameCanvas.menu.menuSelectedItem], this.g + this.e / 2, indexPaint + i * 35 + 8, 2);
                } else {
                   mFont.tahoma_7b_white.writeText(graphic, menu[i], this.g + this.e / 2, indexPaint + i * 35 + 8, 2);
                }
             } else if (i == 0) {
                mFont.tahoma_7b_white.writeText(graphic, menu[i] + (!unameChange.equals("") ? ": " + unameChange : (uname.startsWith("tmpusr") ? "" : ": " + uname)), this.g + this.e / 2, indexPaint + i * 35 + 8, 2);
             } else if (i == 3) {
                mFont.tahoma_7b_white.writeText(graphic, menu[i] + ServerInfo.listName[GameCanvas.menu.menuSelectedItem], this.g + this.e / 2, indexPaint + i * 35 + 8, 2);
             } else {
                mFont.tahoma_7b_white.writeText(graphic, menu[i], this.g + this.e / 2, indexPaint + i * 35 + 8, 2);
             }
          }
       }
 
       if (GameCanvas.currentDialog == null) {
          Paint.a(graphic, super.left, super.center, super.right);
       }
 
       super.paint(graphic);
    }
 
    public final void c() {
       if (uname.equals("") && unameChange.equals("")) {
          if (this.i >= 0 && this.i < cmd[0].length) {
             super.center = cmd[0][this.i];
          }
       } else if (this.i >= 0 && this.i < cmd[1].length) {
          super.center = cmd[1][this.i];
       }
 
       if (++GameScr.cmx > GameCanvas.width * 3 + 100) {
          GameScr.cmx = 100;
       }
 
       super.c();
    }
 
    public final void b() {
       if (!GameCanvas.keyPressedz[2] && !GameCanvas.keyPressedz[4]) {
          if (GameCanvas.keyPressedz[8] || GameCanvas.keyPressedz[6]) {
             ++this.i;
             if (this.i > menu.length - 1) {
                this.i = 0;
             }
          }
       } else {
          --this.i;
          if (this.i < 0) {
             this.i = menu.length - 1;
          }
       }
 
       if (GameCanvas.isPointerJustRelease && GameCanvas.b(this.g + 10, this.h + 45, this.e - 10, 170)) {
          if (GameCanvas.isPointerClick) {
             this.i = (GameCanvas.q - (this.h + 45)) / 35;
          }
 
          if (uname.equals("") && unameChange.equals("")) {
             if (this.i >= 0 && this.i < cmd[0].length) {
                cmd[0][this.i].a();
             }
          } else if (this.i >= 0 && this.i < cmd[1].length) {
             cmd[1][this.i].a();
          }
       }
 
       super.b();
       GameCanvas.k();
    }
 
    private static void f() {
       if (!Session_ME.getInstance().connected) {
          GameCanvas.c();
       }
 
       GameCanvas.pleaseWait();
    }
 
    public static boolean a() {
       return uname != null && (uname.startsWith("tmpusr") || uname.equals(""));
    }
 
    public static String e() {
       String var0 = "";
 
       for(int var1 = 0; var1 < 12; ++var1) {
          String var2 = Integer.toString(Res.b(0, 9));
          var0 = var0 + var2;
       }
 
       return var0;
    }
 
    public final void perform(int idCommand, Object var2) {
       switch (idCommand) {
          case 1000:
             if (a() && !uname.equals("")) {
                GameCanvas.a(mResources.sh, new Command(mResources.choiTiep, this, 10001, (Object)null), new Command(mResources.ca, GameCanvas.instance, 8882, (Object)null));
                return;
             }
 
             f();
             Service.getInstance().login("-1", "12345", GameMidlet.CLIENT_VERSION);
             return;
          case 1001:
             if (a() && !uname.equals("") && unameChange.equals("")) {
                GameCanvas.a(mResources.sh, new Command(mResources.sm, this, 10004, (Object)null), new Command(mResources.ca, GameCanvas.instance, 8882, (Object)null));
                return;
             }
 
             GameCanvas.ag.update();
             return;
          case 1002:
             MyVector _menu = new MyVector();
              for (int iServer = 0; iServer < ServerInfo.serverIndex.length; ++iServer) {
                  _menu.addElement(new Command(ServerInfo.listName[iServer], this, 20000 + iServer, (Object)null));
              }
             GameCanvas.menu.showMenu(_menu);
             if (RMS.d("indServer") != -1 && !GameCanvas.isTouch) {
                GameCanvas.menu.menuSelectedItem = RMS.d("indServer");
             }
             return;
          case 1003:
             f();
             if (!unameChange.equals("")) {
                uname = unameChange;
                pass = passChange;
                unameChange = "";
                passChange = "";
                RMS.writeRecord("acc", uname);
                RMS.writeRecord("pass", pass);
             }
 
             Service.getInstance().login(uname, pass, GameMidlet.CLIENT_VERSION);
             return;
          case 1004:
                (new Thread(new Runnable() {
                    public void run() {
                        GameCanvas.pleaseWait();
                        ServerInfo.updateListServer();
                        ServerInfo.readListServerInfo();
                        ServerInfo.selectServer(GameCanvas.menu.menuSelectedItem);
                        GameCanvas.currentDialog = null;
                    }
                })).start();
              return;
          case 10001:
             f();
             Service.getInstance().login("-1", "12345", GameMidlet.CLIENT_VERSION);
             if (!unameChange.equals("")) {
                uname = unameChange;
                pass = passChange;
                unameChange = "";
                passChange = "";
                RMS.writeRecord("acc", uname);
                RMS.writeRecord("pass", pass);
                return;
             }
             break;
          case 10004:
             GameCanvas.currentDialog = null;
             GameCanvas.ag.update();
             return;
       }
       if (idCommand >= 20000 && idCommand < 20000 + ServerInfo.serverIndex.length) {
            int indexServer = ServerInfo.serverIndex[idCommand - 20000];
            GameCanvas.menu.menuSelectedItem = indexServer;
            GameCanvas.menu.showMenu = false;
            ServerInfo.selectServer(indexServer);
            RMS.writeRecord("indServer", ServerInfo.serverIndex[indexServer]);
        }
    }
 }
 
