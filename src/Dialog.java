

public abstract class Dialog {
    public Command left;
    public Command center;
    public Command right;
 
    public Dialog() {
    }
 
    public void a(mGraphics var1) {
       var1.translateXY(-var1.getTranslateX(), -var1.getTranslateY());
       var1.setClip(0, 0, GameCanvas.width, GameCanvas.height);
       Paint.a(var1);
       Paint.a(var1, this.left, this.center, this.right);
    }
 
    public void a(int var1) {
       switch (var1) {
          case -39:
          case -2:
             GameCanvas.l[8] = true;
             GameCanvas.keyPressedz[8] = true;
             return;
          case -38:
          case -1:
             GameCanvas.l[2] = true;
             GameCanvas.keyPressedz[2] = true;
             return;
          case -22:
          case -7:
             GameCanvas.l[13] = true;
             GameCanvas.keyPressedz[13] = true;
             return;
          case -21:
          case -6:
             GameCanvas.l[12] = true;
             GameCanvas.keyPressedz[12] = true;
             return;
          case -5:
          case 10:
             GameCanvas.l[5] = true;
             GameCanvas.keyPressedz[5] = true;
             return;
          default:
       }
    }
 
    public void a() {
       if (this.center != null && (GameCanvas.keyPressedz[5] || mScreen.a(this.center))) {
          GameCanvas.keyPressedz[5] = false;
          GameCanvas.isPointerClick = false;
          mScreen.fr = -1;
          GameCanvas.isPointerJustRelease = false;
          if (this.center != null) {
             this.center.a();
          }
 
          mScreen.fr = -1;
       }
 
       if (this.left != null && (GameCanvas.keyPressedz[12] || mScreen.a(this.left))) {
          GameCanvas.keyPressedz[12] = false;
          GameCanvas.isPointerClick = false;
          mScreen.fr = -1;
          GameCanvas.isPointerJustRelease = false;
          if (this.left != null) {
             this.left.a();
          }
 
          mScreen.fr = -1;
       }
 
       if (this.right != null && (GameCanvas.keyPressedz[13] || mScreen.a(this.right))) {
          GameCanvas.keyPressedz[13] = false;
          GameCanvas.isPointerClick = false;
          GameCanvas.isPointerJustRelease = false;
          mScreen.fr = -1;
          if (this.right != null) {
             this.right.a();
          }
 
          mScreen.fr = -1;
       }
 
       GameCanvas.k();
       GameCanvas.l();
    }
 }
 