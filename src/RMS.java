
// Source code is decompiled from setAuto .class file using FernFlower decompiler.
import java.io.InputStream;
import javax.microedition.rms.RecordStore;

public class RMS {
   public ItemTemplate a;
   public String b;
   public int c;
   public int d;
   public int e;
   public int f;
   public int g;
   public int h;
   public static int i;
   public static long j;
   public static boolean k;

   public static InputStream a(String var0) {
      return "".getClass().getResourceAsStream(var0);
   }

   public static void writeRecord(String var0, byte[] var1) {
      try {
         RecordStore var3;
         if ((var3 = RecordStore.openRecordStore("vj" + var0, true)).getNumRecords() > 0) {
            var3.setRecord(1, var1, 0, var1.length);
         } else {
            var3.addRecord(var1, 0, var1.length);
         }

         var3.closeRecordStore();
      } catch (Exception var2) {
      }
   }

   public static byte[] getRecord(String var0) {
      try {
         RecordStore var3;
         byte[] var1 = (var3 = RecordStore.openRecordStore("vj" + var0, false)).getRecord(1);
         var3.closeRecordStore();
         return var1;
      } catch (Exception var2) {
         return null;
      }
   }

   public static void writeRecord(String var0, int var1) {
      try {
            RMS.writeRecord(var0, new byte[]{(byte)var1});
      } catch (Exception var2) {
      }
   }

   public static void writeRecord(String var0, String var1) {
      try {
            RMS.writeRecord(var0, var1.getBytes("UTF-8"));
      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   public static String loadRMSString(String var0) {
      byte[] var2;
      if ((var2 = getRecord(var0)) == null) {
         return null;
      } else {
         try {
            return new String(var2, "UTF-8");
         } catch (Exception var1) {
            return new String(var2);
         }
      }
   }

   public static int d(String var0) {
      byte[] var1;
      return (var1 = getRecord(var0)) == null ? -1 : var1[0];
   }

   private static void deleteRecord(String var0) {
      try {
         RecordStore.deleteRecordStore("vj" + var0);
      } catch (Exception var1) {
      }
   }

   public static void a() {
      deleteRecord("nj_arrow");
      deleteRecord("nj_effect");
      deleteRecord("nj_image");
      deleteRecord("nj_part");
      deleteRecord("nj_skill");
      deleteRecord("data");
      deleteRecord("dataVersion");
      deleteRecord("map");
      deleteRecord("mapVersion");
      deleteRecord("skill");
      deleteRecord("killVersion");
      deleteRecord("item");
      deleteRecord("itemVersion");
   }

   public RMS() {
   }

   public static RMS a(ItemTemplate var0, String var1) {
      RMS var2;
      (var2 = new RMS()).a = var0;
      String[] var5 = Code.splitString(var1, " ");
      var2.b = var5[0];

      try {
         var2.d = Integer.parseInt(var5[1]);
         var2.c = Integer.parseInt(var5[2]);
         var2.e = Integer.parseInt(var5[3]);
      } catch (Exception var4) {
         var2.g = 0;
         var2.f = 0;
      }

      try {
         var2.g = Integer.parseInt(var5[4]);
         var2.f = Integer.parseInt(var5[5]);
         var2.h = Integer.parseInt(var5[6]);
      } catch (Exception var3) {
         var2.g = 0;
         var2.f = 0;
      }

      return var2.c == 0 && var2.f == 0 ? null : var2;
   }

   public boolean b() {
      return this.c > 0 && Char.k(this.a.id) > this.e;
   }

   public boolean c() {
      return this.f > 0 && Char.k(this.a.id) < this.h;
   }

   public int d() {
      return this.c > 0 && Char.k(this.a.id) > this.e ? Char.k(this.a.id) - this.e : 0;
   }

   public int e() {
      return this.f > 0 && Char.k(this.a.id) < this.h ? this.h - Char.k(this.a.id) : 0;
   }

   public String f() {
      return this.b + " " + this.d + " " + this.c + " " + this.e + " " + this.g + " " + this.f + " " + this.h;
   }
}
