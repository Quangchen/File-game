

import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public final class SMS implements Runnable {
   private final String a;
   private final String b;
   private final String c;
   private final Command d;
   private final Command e;

   public static void a(String var0, String var1, Command var2, Command var3) {
      (new Thread(new SMS(var1, var0, var1, var2, var3))).start();
   }

   private SMS(String var1, String var2, String var3, Command var4, Command var5) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
      this.e = var5;
   }

   public final void run() {
      try {
         MessageConnection var1;
         TextMessage var2;
         (var2 = (TextMessage)(var1 = (MessageConnection)Connector.open(this.a)).newMessage("text")).setAddress(this.a);
         var2.setPayloadText(this.b);
         var1.send(var2);
         System.out.println("SMS data: " + this.b + ", to: " + this.c);
         this.d.a();
      } catch (Exception var3) {
         this.e.a();
      }
   }
}
