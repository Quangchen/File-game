
import java.io.IOException;

final class MessageCollector implements Runnable {
    private Session_ME session;
    
 
    MessageCollector(Session_ME ss) {
       this.session = ss;
    }
 
    public final void run() {
       try {
          while(this.session.connected) {
              long startTime = System.currentTimeMillis();
             byte var1 = this.session.c.readByte();
             if (Session_ME.g(this.session)) {
                var1 = Session_ME.a(this.session, var1);
             }
 
             int var2;
             byte var3;
             int var4;
             int var5;
             if (var1 == -32) {
                var1 = this.session.c.readByte();
                if (Session_ME.g(this.session)) {
                   var1 = Session_ME.a(this.session, var1);
                }
 
                var2 = Session_ME.a(this.session, this.session.c.readByte());
                var3 = Session_ME.a(this.session, this.session.c.readByte());
                var4 = Session_ME.a(this.session, this.session.c.readByte());
                var5 = Session_ME.a(this.session, this.session.c.readByte());
                var2 = (var2 & 255) << 24 | (var3 & 255) << 16 | (var4 & 255) << 8 | var5 & 255;
             } else if (Session_ME.g(this.session)) {
                byte var8 = this.session.c.readByte();
                var3 = this.session.c.readByte();
                var2 = (Session_ME.a(this.session, var8) & 255) << 8 | Session_ME.a(this.session, var3) & 255;
             } else {
                var2 = this.session.c.readUnsignedShort();
             }
 
             byte[] var10 = new byte[var2];
             var5 = 0;
             int var6 = 0;
 
             while(var6 != -1 && var5 < var2) {
                if ((var6 = this.session.c.read(var10, var5, var2 - var5)) > 0) {
                   var5 += var6;
                   Session_ME var10000 = this.session;
                   var10000.i += var5 + 5;
                   var4 = this.session.i + this.session.h;
                   this.session.l = var4 / 1024 + "." + var4 % 1024 / 102 + "Kb";
                }
             }
 
             if (Session_ME.g(this.session)) {
                for(var4 = 0; var4 < var10.length; ++var4) {
                   var10[var4] = Session_ME.a(this.session, var10[var4]);
                }
             }
 
             this.session.j = System.currentTimeMillis();
             Message var9;
             if ((var9 = new Message(var1, var10)).command == -27) {
                this.a(var9);
             } else {
                this.session.d.a(var9);
             }
                long endTime = System.currentTimeMillis();
                int ping = (int)(endTime - startTime);
                
          }
 
       } catch (Exception var7) {
          if (this.session.connected) {
             if (this.session.e != null) {
                this.session.c();
             }
 
             if (this.session.d != null) {
                if (System.currentTimeMillis() - Session_ME.h(this.session) > 500L) {
                   this.session.d.d();
                   return;
                }
 
                this.session.d.c();
             }
          }
 
       }
    }
 
    private void a(Message var1) {
        try {
            byte var2 = var1.reader().readByte();
            this.session.k = new byte[var2];
            
            int var3;
            for(var3 = 0; var3 < var2; ++var3) {
                this.session.k[var3] = var1.reader().readByte();
            }
            
            for(var3 = 0; var3 < this.session.k.length - 1; ++var3) {
                byte[] var10000 = this.session.k;
                var10000[var3 + 1] ^= this.session.k[var3];
            }
            
            Session_ME.a(this.session, true);
        } catch (IOException ex) {
            
        }
    }
 }
 