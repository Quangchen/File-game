

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class SavePK {
   private static MyVector a;

   static {
      (a = new MyVector()).removeAllElements();

      try {
         ByteArrayInputStream var0 = new ByteArrayInputStream(RMS.getRecord("V6PKS"));
         DataInputStream var1;
         int var2 = (var1 = new DataInputStream(var0)).readInt();

         for(int var3 = 0; var3 < var2; ++var3) {
            a.addElement(var1.readUTF());
         }

         var1.close();
         var0.close();
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   private static void c() {
      ByteArrayOutputStream var0 = new ByteArrayOutputStream();
      DataOutputStream var1 = new DataOutputStream(var0);

      try {
         var1.writeInt(a.size());

         for(int var2 = 0; var2 < a.size(); ++var2) {
            var1.writeUTF((String)a.elementAt(var2));
         }

         var1.flush();
         var0.flush();
         RMS.writeRecord("V6PKS", var0.toByteArray());
      } catch (Exception var3) {
         var3.printStackTrace();
      }
   }

   public static MyVector a() {
      MyVector var0 = new MyVector();

      for(int var1 = 0; var1 < a.size(); ++var1) {
         var0.addElement(var1 + ". " + (String)a.elementAt(var1));
      }

      return var0;
   }

   public static void a(String var0) {
      if (!a.contains(var0)) {
         a.addElement(var0);
         c();
      }

   }

   public static void b(String var0) {
      if (a.contains(var0)) {
         a.removeElement(var0);
         c();
      }

   }

   public static void b() {
      a.removeAllElements();
      c();
   }

   public static boolean c(String var0) {
      return a.contains(var0);
   }
}
