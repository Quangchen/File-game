
// Source code is decompiled from instance .class file using FernFlower decompiler.
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.io.SocketConnection;

public final class Session_ME implements ISession_ME {
   public static Session_ME instance = new Session_ME();
   public DataOutputStream b;
   public DataInputStream c;
   public IController d;
   public SocketConnection e;
   public boolean connected;
   public boolean g;
   private Sender q = new Sender(this);
   private MessageCollector r = new MessageCollector(this);
   private Thread s;
   private Thread t;
   private Thread u;
   private Thread v;
   public int h;
   public int i;
   public long j;
   private boolean getKeyComplete;
   public byte[] k = null;
   private byte x;
   private byte y;
   private long z;
   public String l = "";
   public static String ip;
   public static int port;
   public static byte o;
   public static boolean p;
   private static Object aa = new Object();
   public static long ping = 0;

   public Session_ME() {
   }

   public static Session_ME getInstance() {
      return instance;
   }

   public final void connect(String ip, int port) {
      String var3 = ip;
      if (!this.connected && !this.g) {
         this.getKeyComplete = false;
         this.e = null;
         this.s = new Thread(new NetworkConnector(this, var3));
         this.s.start();
         Session_ME.ip = ip;
         Session_ME.port = port;
         o = GameMidlet.typeArea;
      }

   }

   public final void sendMessage(Message ms) {
      this.q.addMessage(ms);
   }

   private synchronized void sendCommand(Message ms) {
       
        byte[] var2 = ms.getData();

        try {
              if (this.getKeyComplete) {
                 this.b.writeByte(this.a(ms.command));
              } else {
                 this.b.writeByte(ms.command);
              }

              if (var2 != null) {
                 int var3 = var2.length;
                 int var5;
                 if (ms.command != -31 && this.getKeyComplete) {
                    var5 = this.a((byte)(var3 >> 8));
                    this.b.writeByte(var5);
                    var5 = this.a((byte)var3);
                    this.b.writeByte(var5);
                 } else {
                    this.b.writeShort(var3);
                 }

                 if (this.getKeyComplete) {
                    for(var5 = 0; var5 < var2.length; ++var5) {
                       var2[var5] = this.a(var2[var5]);
                    }
                 }

                 this.b.write(var2);
                 this.h += 5 + var2.length;
              } else {
                 this.b.writeShort(0);
                 this.h += 5;
              }

           this.b.flush();

        } catch (Exception var4) {
           var4.printStackTrace();
        }
    }

   private byte a(byte var1) {
      byte[] var10000 = this.k;
      byte var10003 = this.y;
      this.y = (byte)(var10003 + 1);
      var1 = (byte)(var10000[var10003] & 255 ^ var1 & 255);
      if (this.y >= this.k.length) {
         this.y = (byte)(this.y % this.k.length);
      }

      return var1;
   }

   public final void b() {
      Code var10000 = Code.instance;
      Code.b();
      this.c();
   }

   public final void c() {
      this.getKeyComplete = false;
      this.connected = false;
      this.g = false;
      this.k = null;
      this.x = 0;
      this.y = 0;
      this.q.a();

      try {
         if (this.e != null) {
            this.e.close();
            this.e = null;
         }

         if (this.b != null) {
            this.b.close();
            this.b = null;
         }

         if (this.c != null) {
            this.c.close();
            this.c = null;
         }

         if (this.t != null) {
            if (this.t.isAlive()) {
               this.t.interrupt();
            }

            this.t = null;
         }

         if (this.s != null) {
            if (this.s.isAlive()) {
               this.s.interrupt();
            }

            this.s = null;
         }

         if (this.u != null) {
            if (this.u.isAlive()) {
               this.u.interrupt();
            }

            this.u = null;
         }

         if (this.v != null) {
            if (this.v.isAlive()) {
               this.v.interrupt();
            }

            this.v = null;
         }

         System.gc();
      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   public final void d() {
      if (GameCanvas.mScreen != GameCanvas.af) {
         GameCanvas.instance.e();
      }

      if (p) {
         e();
      } else {
         p = true;
         (new Thread(new ReLogin(this))).start();
      }
   }

   public static void e() {
      synchronized(aa) {
         aa.notifyAll();
      }
   }

   static Thread a(Session_ME var0) {
      return var0.s;
   }

   static void a(Session_ME var0, Thread var1) {
      var0.t = var1;
   }

   static Thread b(Session_ME var0) {
      return var0.t;
   }

   static Sender c(Session_ME var0) {
      return var0.q;
   }

   static void b(Session_ME var0, Thread var1) {
      var0.u = var1;
   }

   static MessageCollector d(Session_ME var0) {
      return var0.r;
   }

   static void c(Session_ME var0, Thread var1) {
      var0.v = var1;
   }

   static Thread e(Session_ME var0) {
      return var0.u;
   }

   static Thread f(Session_ME var0) {
      return var0.v;
   }

   static void a(Session_ME var0, long var1) {
      var0.z = var1;
   }

   static void sendCommand(Session_ME session, Message ms) {
      session.sendCommand(ms);
   }

   static boolean g(Session_ME ss) {
      return ss.getKeyComplete;
   }

   static byte a(Session_ME var0, byte var1) {
      byte[] var10000 = (var0 = var0).k;
      byte var10003 = var0.x;
      var0.x = (byte)(var10003 + 1);
      var1 = (byte)(var10000[var10003] & 255 ^ var1 & 255);
      if (var0.x >= var0.k.length) {
         var0.x = (byte)(var0.x % var0.k.length);
      }

      return var1;
   }

   static long h(Session_ME var0) {
      return var0.z;
   }

   static void a(Session_ME var0, boolean var1) {
      var0.getKeyComplete = true;
   }

   static void a(long var0) {
      long var2 = var0;
      synchronized(aa) {
         try {
            aa.wait(var2);
         } catch (Exception var4) {
         }

      }
   }
}
