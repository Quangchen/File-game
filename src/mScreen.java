

public class mScreen {
    public Command left;
    public Command center;
    public Command right;
    public static final int fo;
    public static int cmdW;
    public static int cmdH;
    public static int fr;
 
    static {
       fo = mFont.tahoma_8b.getHeight() + 8;
       cmdW = 70 * mGraphics.zoomLevel;
       cmdH = 22;
       fr = -1;
    }
 
    public mScreen() {
    }
 
    public void update() {
       GameCanvas.k();
       GameCanvas.l();
       if (GameCanvas.mScreen != null) {
          GameCanvas.mScreen.am();
       }
 
       GameCanvas.mScreen = this;
       MotherCanvas.instance.setFullScreenMode(true);
    }
 
    public void am() {
    }
 
    public static void ap() {
       if (GameCanvas.isTouch) {
          cmdH = 26;
       } else {
          cmdH = 24;
       }
    }
 
    public void a(int var1) {
    }
 
    public void c() {
    }
 
    public void b() {
       if (GameCanvas.keyPressedz[5] || a(GameCanvas.mScreen.center)) {
          GameCanvas.keyPressedz[5] = false;
          fr = -1;
          GameCanvas.isPointerJustRelease = false;
          if (this.center != null) {
             this.center.a();
          }
       }
 
       if (GameCanvas.keyPressedz[12] || a(GameCanvas.mScreen.left)) {
          GameCanvas.keyPressedz[12] = false;
          fr = -1;
          GameCanvas.isPointerJustRelease = false;
          if (ChatTextField.a().isShow) {
             if (ChatTextField.a().d != null) {
                ChatTextField.a().d.a();
             }
          } else if (this.left != null) {
             this.left.a();
          }
       }
 
       if (GameCanvas.keyPressedz[13] || a(GameCanvas.mScreen.right)) {
          GameCanvas.keyPressedz[13] = false;
          fr = -1;
          GameCanvas.isPointerJustRelease = false;
          if (ChatTextField.a().isShow) {
             if (ChatTextField.a().e != null) {
                ChatTextField.a().e.a();
             }
          } else if (this.right != null) {
             this.right.a();
          }
       }
 
    }
 
    public static boolean a(Command var0) {
       if (var0 == null) {
          return false;
       } else if (var0.x != 0 && var0.y != 0) {
          return var0.b();
       } else {
          if (GameCanvas.currentDialog != null) {
             if (GameCanvas.currentDialog.center != null && GameCanvas.b(GameCanvas.width - cmdW >> 1, GameCanvas.height - cmdH - 5, cmdW, cmdH + 10)) {
                fr = 1;
                if (var0 == GameCanvas.currentDialog.center && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                   return true;
                }
             }
 
             if (GameCanvas.currentDialog.left != null && GameCanvas.b(0, GameCanvas.height - cmdH - 5, cmdW, cmdH + 10)) {
                fr = 0;
                if (var0 == GameCanvas.currentDialog.left && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                   return true;
                }
             }
 
             if (GameCanvas.currentDialog.right != null && GameCanvas.b(GameCanvas.width - cmdW, GameCanvas.height - cmdH - 5, cmdW, cmdH + 10)) {
                fr = 2;
                if ((var0 == GameCanvas.currentDialog.right || var0 == ChatTextField.a().e) && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                   return true;
                }
             }
          } else {
             if (var0 == GameCanvas.mScreen.left && GameCanvas.b(0, GameCanvas.height - cmdH - 5, cmdW, cmdH + 10)) {
                fr = 0;
                if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                   return true;
                }
             }
 
             if (var0 == GameCanvas.mScreen.right && GameCanvas.b(GameCanvas.width - cmdW, GameCanvas.height - cmdH - 5, cmdW, cmdH + 10)) {
                fr = 2;
                if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                   return true;
                }
             }
 
             if ((var0 == GameCanvas.mScreen.center || ChatPopup.currentMultilineChatPopup != null) && GameCanvas.b(GameCanvas.width - cmdW >> 1, GameCanvas.height - cmdH - 5, cmdW, cmdH + 10)) {
                fr = 1;
                if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                   return true;
                }
             }
          }
 
          return false;
       }
    }
 
    public void paint(mGraphics var1) {
       var1.translateXY(-var1.getTranslateX(), -var1.getTranslateY());
       var1.setClip(0, 0, GameCanvas.width, GameCanvas.height + 1);
       Paint.a(var1);
       if (ChatPopup.currentMultilineChatPopup != null) {
          Paint.a(var1, (Command)null, ChatPopup.currentMultilineChatPopup.a, (Command)null);
       } else if (ChatTextField.a().isShow) {
          Paint.a(var1, ChatTextField.a().d, ChatTextField.a().f, ChatTextField.a().e);
       } else {
          if (GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu) {
             Paint.a(var1, this.left, this.center, this.right);
          }
 
       }
    }
 }
 