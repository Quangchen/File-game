

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Enumeration;

public final class EffectAuto extends Effect2 {
   private short a;
   private MyImage b;
   private int g;
   private int h;
   private int i;
   private int j;
   private byte k = 0;
   private long l = 0L;
   private static EffAtutoTemp[] m = new EffAtutoTemp[20];
   private static mHashtable n = new mHashtable();
   private static mHashtable o = new mHashtable();
   private boolean p;

   public EffectAuto() {
   }

   public static void a(short var0, int var1, int var2, byte var3, short var4, int var5) {
      EffectAuto var6;
      (var6 = new EffectAuto()).a = var0;
      var6.g = var1;
      var6.h = var2;
      var6.k = var3;
      if (var6.k < 0) {
         var6.p = true;
      } else {
         var6.p = false;
      }

      if (var4 > 0) {
         var6.l = System.currentTimeMillis() + (long)(var4 * 1000);
      } else {
         var6.l = -1L;
      }

      var6.j = var5;
      Effect2.vEffect2.addElement(var6);
   }

   private EffAtutoTemp d() {
      return m[this.a];
   }

   public final void paint(mGraphics var1) {
      if (this.b != null && this.b.img != null && this.d().c != null) {
         Frame var2 = this.d().b[this.d().c[this.i]];

         for(int var3 = 0; var3 < var2.a.length; ++var3) {
            EffAtutoTemp var4 = this.d();
            byte var5 = var2.c[var3];
            ImageInfo var6 = var4.a[var5];
            if (this.j > 0) {
               var1.drawRegion(this.b.img, var6.a, var6.b, var6.c, var6.d, 0, this.g + var2.a[var3], this.h + var2.b[var3] - 1, 20);
            } else {
               var1.drawRegion(this.b.img, var6.a, var6.b, var6.c, var6.d, 2, this.g - var2.a[var3], this.h + var2.b[var3] - 1, 24);
            }
         }
      }

   }

   public final void update() {
      try {
         m[this.a] = (EffAtutoTemp)o.get(String.valueOf(this.a));
         if (m[this.a] == null) {
            m[this.a] = new EffAtutoTemp();
            o.put(String.valueOf(this.a), m[this.a]);
            this.d().d = System.currentTimeMillis();
            Service.getInstance().a((byte)1, this.a);
         } else if (this.d().c == null && System.currentTimeMillis() - this.d().d > 3000L) {
            this.d().d = System.currentTimeMillis();
            Service.getInstance().a((byte)1, this.a);
         }

         if (this.d().c != null) {
            this.b = (MyImage)n.get(String.valueOf(this.a));
            if (this.b == null) {
               this.b = new MyImage();
               n.put(String.valueOf(this.a), this.b);
               this.b.img = Controller.a(RMS.getRecord("effauto " + this.a));
               if (this.b.img == null) {
                  this.b.timerequest = System.currentTimeMillis();
                  Service.getInstance().a((byte)0, this.a);
               }
            } else if (this.b.img == null && System.currentTimeMillis() - this.b.timerequest > 6000L) {
               this.b.timerequest = System.currentTimeMillis();
               Service.getInstance().a((byte)0, this.a);
            }
         }

         if (this.b != null && this.b.img != null && this.d().c != null) {
            ++this.i;
            if (this.i >= this.d().c.length) {
               if (this.l != -1L) {
                  if (this.l - System.currentTimeMillis() <= 0L) {
                     Effect2.vEffect2.removeElement(this);
                  } else {
                     this.i = 0;
                  }
               } else {
                  label62: {
                     if (!this.p) {
                        --this.k;
                        if (this.k <= 0) {
                           Effect2.vEffect2.removeElement(this);
                           break label62;
                        }
                     }

                     this.i = 0;
                  }
               }
            }

            this.b.timeUse = System.currentTimeMillis();
            return;
         }
      } catch (Exception var2) {
         System.out.println("Err update effauto:" + var2.toString());
      }

   }

   public static void a(short var0, byte[] var1) {
      if (((EffAtutoTemp)o.get(String.valueOf(var0))).c == null) {
         new EffAtutoTemp();
         EffAtutoTemp var2 = a(var1);
         o.put(String.valueOf(var0), var2);
      }

   }

   private static EffAtutoTemp a(byte[] var0) {
      try {
         EffAtutoTemp var1 = new EffAtutoTemp();
         ByteArrayInputStream var6 = new ByteArrayInputStream(var0);
         DataInputStream var7 = new DataInputStream(var6);
         var1.a = new ImageInfo[var7.readByte()];

         int var2;
         for(var2 = 0; var2 < var1.a.length; ++var2) {
            var1.a[var2] = new ImageInfo();
            var7.readByte();
            var1.a[var2].a = var7.readUnsignedByte();
            var1.a[var2].b = var7.readUnsignedByte();
            var1.a[var2].c = var7.readUnsignedByte();
            var1.a[var2].d = var7.readUnsignedByte();
         }

         var1.b = new Frame[var7.readShort()];

         short var3;
         int var4;
         for(var2 = 0; var2 < var1.b.length; ++var2) {
            var1.b[var2] = new Frame();
            var3 = var7.readByte();
            var1.b[var2].a = new short[var3];
            var1.b[var2].b = new short[var3];
            var1.b[var2].c = new byte[var3];

            for(var4 = 0; var4 < var3; ++var4) {
               var1.b[var2].a[var4] = var7.readShort();
               var1.b[var2].b[var4] = var7.readShort();
               var1.b[var2].c[var4] = var7.readByte();
            }
         }

         var3 = var7.readShort();
         var1.c = new short[var3];

         for(var4 = 0; var4 < var3; ++var4) {
            var1.c[var4] = var7.readShort();
         }

         return var1;
      } catch (Exception var5) {
         return null;
      }
   }

   public static void a(byte var0, byte[] var1) {
      MyImage var2;
      if ((var2 = (MyImage)n.get(String.valueOf(var0))) == null) {
         var2 = new MyImage();
         n.put(String.valueOf(var0), var2);
      }

      var2.img = Controller.a(var1);
      if (GameMidlet.CLIENT_TYPE != 1) {
         RMS.writeRecord("effauto " + var0, var1);
      }

   }

   public static void b() {
      try {
         Enumeration var0 = n.keys();

         while(var0.hasMoreElements()) {
            String var1 = (String)var0.nextElement();
            MyImage var2 = (MyImage)n.get(var1);
            if (System.currentTimeMillis() - var2.timeUse > 60000L) {
               n.remove(var1);
            }
         }

      } catch (Exception var3) {
      }
   }

   public static void c() {
      try {
         Enumeration var0 = o.keys();

         while(var0.hasMoreElements()) {
            String var1 = (String)var0.nextElement();
            EffAtutoTemp var2 = (EffAtutoTemp)o.get(var1);
            if (System.currentTimeMillis() - var2.e > 600000L) {
               o.remove(var1);
            }
         }

      } catch (Exception var3) {
      }
   }
}
