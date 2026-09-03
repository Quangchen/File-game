

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Enumeration;
import javax.microedition.lcdui.Image;

public final class SmallImage {
   public static int[][] smallImg;
   private static Image[] imgbig;
   private static mHashtable imgNew = new mHashtable();
   public short x;
   public short y;
   public short w;
   public short h;

   public SmallImage(int var1, int var2, int var3, int var4) {
      this.x = (short)var1;
      this.y = (short)var2;
      this.w = (short)var3;
      this.h = (short)var4;
   }

   public static void a() {
      try {
         Enumeration var0 = imgNew.keys();

         while(var0.hasMoreElements()) {
            String var1 = (String)var0.nextElement();
            MyImage var2 = (MyImage)imgNew.get(var1);
            if (System.currentTimeMillis() - var2.timeUse > 180000L) {
               imgNew.remove(var1);
            }
         }

      } catch (Exception var3) {
      }
   }

   public static void b() {
      imgbig = null;
      System.gc();
   }

   public static void clearDynamicCache() {
      try {
         imgNew = new mHashtable();
      } catch (Exception var0) {
      }
   }

   public static void c() {
      imgbig = null;
      System.gc();
      imgbig = new Image[]{GameCanvas.loadImage("/img/Big0.png"), GameCanvas.loadImage("/img/Big1.png"), GameCanvas.loadImage("/img/Big2.png"), GameCanvas.loadImage("/img/Big3.png"), GameCanvas.loadImage("/img/Big4.png")};
      Image.createRGBImage(new int[]{-2013265920}, 1, 1, true);
   }

   public SmallImage() {
      e();
   }

   public static void d() {
      new SmallImage();
   }

   private static void e() {
      try {
         DataInputStream var0;
         short var1;
         smallImg = new int[var1 = (var0 = new DataInputStream(new ByteArrayInputStream(RMS.getRecord("nj_image")))).readShort()][5];

         for(int var2 = 0; var2 < var1; ++var2) {
            smallImg[var2][0] = var0.readUnsignedByte();
            smallImg[var2][1] = var0.readShort();
            smallImg[var2][2] = var0.readShort();
            smallImg[var2][3] = var0.readShort();
            smallImg[var2][4] = var0.readShort();
         }

      } catch (Exception var3) {
         var3.printStackTrace();
      }
   }

   public static void a(int var0, byte[] var1) {
      MyImage var2;
      if ((var2 = (MyImage)imgNew.get(String.valueOf(var0))) == null) {
         var2 = new MyImage();
         imgNew.put(String.valueOf(var0), var2);
      }

      var2.img = Controller.a(var1);
      if (GameMidlet.CLIENT_TYPE != 1) {
         RMS.writeRecord(String.valueOf(var0), var1);
      }

   }

   public static boolean a(int var0) {
      if (var0 >= smallImg.length || smallImg[var0][1] >= imgbig[smallImg[var0][0]].getWidth() || smallImg[var0][3] >= imgbig[smallImg[var0][0]].getWidth() || smallImg[var0][2] >= imgbig[smallImg[var0][0]].getHeight() || smallImg[var0][4] >= imgbig[smallImg[var0][0]].getHeight()) {
         MyImage var1;
         if ((var1 = (MyImage)imgNew.get(String.valueOf(var0))) == null) {
            var1 = new MyImage();
            imgNew.put(String.valueOf(var0), var1);
            var1.img = Controller.a(RMS.getRecord(String.valueOf(var0)));
            if (var1.img == null) {
               var1.timerequest = System.currentTimeMillis();
               Service.getInstance().requestIcon(var0);
            }

            return false;
         }

         if (var1.img != null) {
            return true;
         }

         if (var1.img == null && System.currentTimeMillis() - var1.timerequest > 60000L) {
            var1.timerequest = System.currentTimeMillis();
            Service.getInstance().requestIcon(var0);
            return false;
         }
      }

      return true;
   }

   public static int b(int var0) {
      return smallImg[var0][4];
   }
   public static void paintImage(mGraphics graphic, int iconID, int x, int y, int transform, int anchor) {
      if (iconID < smallImg.length && smallImg[iconID][1] < imgbig[smallImg[iconID][0]].getWidth() && smallImg[iconID][3] < imgbig[smallImg[iconID][0]].getWidth() && smallImg[iconID][2] < imgbig[smallImg[iconID][0]].getHeight() && smallImg[iconID][4] < imgbig[smallImg[iconID][0]].getHeight()) {
         graphic.drawRegion(imgbig[smallImg[iconID][0]], smallImg[iconID][1], smallImg[iconID][2], smallImg[iconID][3], smallImg[iconID][4], transform, x, y, anchor);
      } else {
         MyImage myImg;
         if ((myImg = (MyImage)imgNew.get(String.valueOf(iconID))) == null) {
            myImg = new MyImage();
            imgNew.put(String.valueOf(iconID), myImg);
            myImg.img = Controller.a(RMS.getRecord(String.valueOf(iconID)));
            if (myImg.img == null) {
               myImg.timerequest = System.currentTimeMillis();
               Service.getInstance().requestIcon(iconID);
            }
         } else if (myImg.img == null && System.currentTimeMillis() - myImg.timerequest > 60000L) {
            myImg.timerequest = System.currentTimeMillis();
            Service.getInstance().requestIcon(iconID);
         }

         if (myImg != null) {
            if (myImg.img != null) {
                graphic.drawRegion(myImg.img, 0, 0, myImg.img.getWidth(), myImg.img.getHeight(), transform, x, y, anchor);
            }

            myImg.timeUse = System.currentTimeMillis();
         }
      }
   }
   
   public static void paintImageAnim(mGraphics graphic, int iconID, int x, int y, int transform, int anchor) {
      if (iconID < smallImg.length && smallImg[iconID][1] < imgbig[smallImg[iconID][0]].getWidth() && smallImg[iconID][3] < imgbig[smallImg[iconID][0]].getWidth() && smallImg[iconID][2] < imgbig[smallImg[iconID][0]].getHeight() && smallImg[iconID][4] < imgbig[smallImg[iconID][0]].getHeight()) {
         graphic.drawRegion(imgbig[smallImg[iconID][0]], smallImg[iconID][1], smallImg[iconID][2], smallImg[iconID][3], smallImg[iconID][4], transform, x, y, anchor);
      } else {
         MyImage myImg;
         if ((myImg = (MyImage)imgNew.get(String.valueOf(iconID))) == null) {
            myImg = new MyImage();
            imgNew.put(String.valueOf(iconID), myImg);
            myImg.img = Controller.a(RMS.getRecord(String.valueOf(iconID)));
            if (myImg.img == null) {
               myImg.timerequest = System.currentTimeMillis();
               Service.getInstance().requestIcon(iconID);
            }
         } else if (myImg.img == null && System.currentTimeMillis() - myImg.timerequest > 60000L) {
            myImg.timerequest = System.currentTimeMillis();
            Service.getInstance().requestIcon(iconID);
         }

         if (myImg != null) {
            if (myImg.img != null) {
                if(myImg.img.getHeight() > 35){
                    int w = myImg.img.getWidth();
                    int h = myImg.img.getHeight() / 3;
                    if (GameCanvas.gameTick % 6 == 0) {
                        myImg.indexFrame = (byte) ((myImg.indexFrame + 1) % 3);
                    }
                    graphic.drawRegion(myImg.img, 0, myImg.indexFrame * h, w, h, 0, x, y, anchor);
                }
                else{
                    graphic.drawRegion(myImg.img, 0, 0, myImg.img.getWidth(), myImg.img.getHeight(), transform, x, y, anchor);
                }
            }

            myImg.timeUse = System.currentTimeMillis();
         }
      }
   }
   
   public static void paintImage(mGraphics graphic, int iconID, int x, int y, int transform, int anchor, int indexFrame) {
      if (iconID < smallImg.length && smallImg[iconID][1] < imgbig[smallImg[iconID][0]].getWidth() && smallImg[iconID][3] < imgbig[smallImg[iconID][0]].getWidth() && smallImg[iconID][2] < imgbig[smallImg[iconID][0]].getHeight() && smallImg[iconID][4] < imgbig[smallImg[iconID][0]].getHeight()) {
         graphic.drawRegion(imgbig[smallImg[iconID][0]], smallImg[iconID][1], smallImg[iconID][2], smallImg[iconID][3], smallImg[iconID][4], transform, x, y, anchor);
      } else {
         MyImage myImg;
         if ((myImg = (MyImage)imgNew.get(String.valueOf(iconID))) == null) {
            myImg = new MyImage();
            imgNew.put(String.valueOf(iconID), myImg);
            myImg.img = Controller.a(RMS.getRecord(String.valueOf(iconID)));
            if (myImg.img == null) {
               myImg.timerequest = System.currentTimeMillis();
               Service.getInstance().requestIcon(iconID);
            }
         } else if (myImg.img == null && System.currentTimeMillis() - myImg.timerequest > 60000L) {
            myImg.timerequest = System.currentTimeMillis();
            Service.getInstance().requestIcon(iconID);
         }

         if (myImg != null) {
            if (myImg.img != null) {
                if(myImg.img.getHeight() > 35){
                    int w = myImg.img.getWidth();
                    int h = myImg.img.getHeight() / 3;
                    graphic.drawRegion(myImg.img, 0, indexFrame * h, w, h, 0, x, y, anchor);
                }
                else{
                    graphic.drawRegion(myImg.img, 0, 0, myImg.img.getWidth(), myImg.img.getHeight(), transform, x, y, anchor);
                }
            }

            myImg.timeUse = System.currentTimeMillis();
         }
      }
   }
}
