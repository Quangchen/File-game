

public final class SplashScr extends mScreen {
    public static int a;
 
    public SplashScr() {
    }
 
    public final void c() {
       if (a++ > 5) {
          if (RMS.d("indLanguage") >= 0) {
             GameCanvas.d();
             GameCanvas.af.update();
             return;
          }
 
          GameCanvas.d();
          GameCanvas.ah.update();
       }
 
    }
 }
 