

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class mGraphics {
   public Graphics graphics;
   public static int zoomLevel = 1;

   public mGraphics(Graphics grph) {
      this.graphics = grph;
   }

   public mGraphics() {
   }

   public final void drawImage(Image image, int x, int y, int anchor) {
      x *= zoomLevel;
      y *= zoomLevel;
      this.graphics.drawImage(image, x, y, anchor);
   }

   public final void drawLine(int x1, int y1, int x2, int y2) {
      x1 *= zoomLevel;
      y1 *= zoomLevel;
      x2 *= zoomLevel;
      y2 *= zoomLevel;
      this.graphics.drawLine(x1, y1, x2, y2);
   }

   public final void drawRect(int x, int y, int width, int height) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      this.graphics.drawRect(x, y, width, height);
   }

   public final void drawRegion(Image src, int x_src, int y_src, int width, int height, int transfrom, int x_dest, int y_dest, int anchor) {
      x_dest *= zoomLevel;
      y_dest *= zoomLevel;
      x_src *= zoomLevel;
      y_src *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      this.graphics.drawRegion(src, x_src, y_src, width, height, transfrom, x_dest, y_dest, anchor);
   }

   public final void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      arcWidth *= zoomLevel;
      arcHeight *= zoomLevel;
      this.graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
   }

   public final void fillRect(int x, int y, int width, int height) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      this.graphics.fillRect(x, y, width, height);
   }

   public final void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      arcWidth *= zoomLevel;
      arcHeight *= zoomLevel;
      this.graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
   }

   public final int getTranslateX() {
      return this.graphics.getTranslateX() / zoomLevel;
   }

   public final int getTranslateY() {
      return this.graphics.getTranslateY() / zoomLevel;
   }

   public final void setClip(int x, int y, int width, int height) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      this.graphics.setClip(x, y, width, height);
   }

   public final void setColor(int color) {
      this.graphics.setColor(color);
   }

   public final void translateXY(int x, int y) {
      x *= zoomLevel;
      y *= zoomLevel;
      this.graphics.translate(x, y);
   }

   public static int getWidth(Image image) {
      return image.getWidth() / zoomLevel;
   }

   public static int getHeight(Image image) {
      return image.getHeight() / zoomLevel;
   }

   public final void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
      x *= zoomLevel;
      y *= zoomLevel;
      width *= zoomLevel;
      height *= zoomLevel;
      this.graphics.drawArc(x, y, width, height, startAngle, arcAngle);
   }
}
