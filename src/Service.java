
import java.io.IOException;

public final class Service {
   private ISession_ME session = Session_ME.getInstance();
   private static Service instance;
   private int c;
   private int d;

   public Service() {
   }

   public static Service getInstance() {
      if (instance == null) {
         instance = new Service();
      }

      return instance;
   }

   private static Message messageNotLogin(byte var0) {
      Message var1 = new Message((byte)-29);
       try {
           var1.writer().writeByte(var0);
       } catch (IOException ex) {
           
       }
      return var1;
   }

   private static Message messageNotMap(byte var0) {
      Message var1 = new Message((byte)-28);
       try {
           var1.writer().writeByte(var0);
       } catch (IOException ex) {
           
       }
      return var1;
   }

   private static Message messageSubCommand(byte var0) {
      Message var1 = new Message((byte)-30);
       try {
           var1.writer().writeByte(var0);
       } catch (IOException ex) {
           
       }
      return var1;
   }

   public final void setClientType() {
      try {
         Message var1;
         (var1 = messageNotLogin((byte)-125)).writer().writeByte(GameMidlet.CLIENT_TYPE);
         var1.writer().writeByte(mGraphics.zoomLevel);
         var1.writer().writeBoolean(GameCanvas.c);
         var1.writer().writeInt(GameCanvas.width);
         var1.writer().writeInt(GameCanvas.height);
         var1.writer().writeBoolean(TField.isQwerty);
         var1.writer().writeBoolean(GameCanvas.isTouch);
         var1.writer().writeUTF(System.getProperty("microedition.platform"));
         var1.writer().writeByte(0);
         var1.writer().writeInt(0);
         var1.writer().writeByte(mResources.e);
         var1.writer().writeInt(GameMidlet.userProvider);
         var1.writer().writeUTF(GameMidlet.clientAgent);
         this.session.sendMessage(var1);
         var1.cleanup();
      } catch (IOException var2) {
         var2.printStackTrace();
      }
   }

   public final void login(String var1, String var2, String var3) {
      this.setClientType();

      try {
         Message var4;
         (var4 = messageNotLogin((byte)-127)).writer().writeUTF(var1);
         var4.writer().writeUTF(var2);
         var4.writer().writeUTF(var3);
         var4.writer().writeUTF("");
         var4.writer().writeUTF("");
         var4.writer().writeUTF(RMS.loadRMSString("random"));
         var4.writer().writeByte(GameMidlet.typeArea);
         this.session.sendMessage(var4);
         var4.cleanup();
      } catch (IOException var5) {
         var5.printStackTrace();
      }
   }

   public final void b(String var1, String var2, String var3) {
      try {
         Message var4;
         (var4 = new Message((byte)118)).writer().writeUTF(var1);
         var4.writer().writeUTF(var2);
         var4.writer().writeUTF(var3);
         this.session.sendMessage(var4);
         var4.cleanup();
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }

   public final void a(String var1) {
      try {
         Message var2;
         (var2 = messageNotLogin((byte)-122)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         var2.cleanup();
      } catch (IOException var3) {
         var3.printStackTrace();
      }
   }

   public final void c() {
      Message var1 = new Message((byte)-17);
      this.session.sendMessage(var1);
      var1.cleanup();
   }

   public final void requestChangeZone(int var1, int var2) {
      Message var3 = new Message((byte)28);

      try {
         var3.writer().writeByte(var1);
         var3.writer().writeByte(var2);
         this.session.sendMessage(var3);
         var3.cleanup();
      } catch (Exception var4) {
      }
   }

   public final void charMove(int var1, int var2) {
      try {
         if (var1 - this.c != 0 || var2 - this.d != 0) {
            Message var3;
            (var3 = new Message((byte)1)).writer().writeShort(var1);
            var3.writer().writeShort(var2);
            this.c = var1;
            this.d = var2;
            this.session.sendMessage(var3);
            var3.cleanup();
            return;
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public final void b(String var1) {
      Message var2 = new Message((byte)-28);

      try {
         var2.writer().writeByte(-126);
         var2.writer().writeUTF(var1);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      this.session.sendMessage(var2);
   }

   public final void a(String var1, int var2, int var3) {
      Message var4 = new Message((byte)-28);

      try {
         var4.writer().writeByte(-125);
         var4.writer().writeUTF(var1);
         var4.writer().writeByte(var2);
         var4.writer().writeByte(var3);
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      this.session.sendMessage(var4);
   }

   public final void a(int var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-108)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void requestItemInfo(int typeUI, int indexUI) {
      Message ms = null;

      try {
         (ms = new Message((byte)42)).writer().writeByte(typeUI);
         ms.writer().writeByte(indexUI);
         this.session.sendMessage(ms);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         ms.cleanup();
      }

   }

   public final void requestItemPlayer(int var1, int var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)94)).writer().writeInt(var1);
         var3.writer().writeByte(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void e(int var1, int var2) {
      Message var3 = null;

      try {
         (var3 = messageSubCommand((byte)-109)).writer().writeByte(var1);
         var3.writer().writeShort(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void f(int var1, int var2) {
      Message var3 = null;

      try {
         (var3 = messageSubCommand((byte)-108)).writer().writeShort(var1);
         var3.writer().writeByte(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void itemBodyToBag(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)15)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void itemMonToBag(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)108)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }
   
   public final void doRemoveVithu(int var1) {
        Message var2 = null;

        try {
            (var2 = new Message((byte) 117)).writer().writeByte(0);
            var2.writer().writeByte(var1);
            this.session.sendMessage(var2);
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

   public final void d(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)16)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void e(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)17)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void useItem(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)11)).writer().writeByte(var1);
         this.session.sendMessage(var2);
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var2.cleanup();
      }

      if (Char.getMyChar().arrItemBag[var1] != null && Char.getMyChar().arrItemBag[var1].template.type == 24) {
         GameScr.getInstance().resetButton();
         InfoDlg.a("");
      }

   }

   public final void saleItem(int var1, int var2) {
      GameCanvas.msgdlg.update();
      Message var3 = null;

      try {
         (var3 = new Message((byte)14)).writer().writeByte(var1);
         if (var2 > 1) {
            var3.writer().writeInt(var2);
         }

         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void saleItem1(int var1, int var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)14)).writer().writeByte(var1);
         if (var2 > 1) {
            var3.writer().writeInt(var2);
         }

         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void buyItem(int var1, int var2, int var3) {
      GameCanvas.msgdlg.update();
      Message var4 = null;

      try {
         (var4 = new Message((byte)13)).writer().writeByte(var1);
         var4.writer().writeByte(var2);
         if (var3 > 1) {
            var4.writer().writeShort(var3);
         }

         this.session.sendMessage(var4);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var4.cleanup();
      }

   }

   public final void buyItem1(int var1, int var2, int var3) {
      Message var4 = null;

      try {
         (var4 = new Message((byte)13)).writer().writeByte(var1);
         var4.writer().writeByte(var2);
         if (var3 > 1) {
            var4.writer().writeShort(var3);
         }

         this.session.sendMessage(var4);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var4.cleanup();
      }

   }

   public final void selectSkill(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)41)).writer().writeShort(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void d() {
      Message var1 = null;

      try {
         var1 = messageSubCommand((byte)-77);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void openUIZone() {
      Message var1 = null;

      try {
         var1 = new Message((byte)36);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void useItemChangeMap(int var1, int var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)12)).writer().writeByte(var1);
         var3.writer().writeByte(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void useVbl(short type, String name) {
      Message msg = null;

      try {
         (msg = new Message((byte)92)).writer().writeShort(type);
         msg.writer().writeUTF(name);
         this.session.sendMessage(msg);
         return;
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         msg.cleanup();
      }

   }

   public final void openMenu(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)40)).writer().writeShort(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void menu(int var1, int var2, int var3) {
      Message var4 = null;

      try {
         var4 = new Message((byte)29);
         if (GameMidlet.VERSION_250_PLUS) {
            var4.writer().writeByte(0);
         }
         var4.writer().writeByte(var1);
         var4.writer().writeByte(var2);
         var4.writer().writeByte(var3);
         this.session.sendMessage(var4);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var4.cleanup();
      }

   }

   public final void a(short var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)34)).writer().writeShort(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void setXuCuocLoiDai(short var1, String var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)92)).writer().writeShort(var1);
         var3.writer().writeUTF(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void requestItem(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-103)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void bagSort() {
      Message var1 = null;

      try {
         var1 = messageSubCommand((byte)-107);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void boxSort() {
      Message var1 = null;

      try {
         var1 = messageSubCommand((byte)-106);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void boxCoinIn(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-105)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void boxCoinOut(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-104)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void crystalCollect(Item[] var1) {
      GameCanvas.msgdlg.update();
      Message var2 = null;

      try {
         var2 = new Message((byte)19);

         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3] != null) {
               var2.writer().writeByte(var1[var3].indexUI);
            }
         }

         this.session.sendMessage(var2);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void upgradeItem(Item var1, Item[] var2, boolean var3) {
      GameCanvas.msgdlg.update();
      Message var4 = null;

      try {
         (var4 = new Message((byte)21)).writer().writeBoolean(var3);
         var4.writer().writeByte(var1.indexUI);

         for(int var9 = 0; var9 < var2.length; ++var9) {
            if (var2[var9] != null) {
               var4.writer().writeByte(var2[var9].indexUI);
            }
         }

         this.session.sendMessage(var4);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var4.cleanup();
      }

   }

   public final void upgradeItem1(Item var1, Item[] var2, boolean var3) {
      Message var9 = null;

      try {
         (var9 = new Message((byte)21)).writer().writeBoolean(false);
         var9.writer().writeByte(var1.indexUI);

         for(int var8 = 0; var8 < var2.length; ++var8) {
            if (var2[var8] != null) {
               var9.writer().writeByte(var2[var8].indexUI);
            }
         }

         this.session.sendMessage(var9);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var9.cleanup();
      }

   }

   public final void crystalCollectLock(Item[] var1) {
      GameCanvas.msgdlg.update();
      Message var2 = null;

      try {
         var2 = new Message((byte)20);

         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3] != null) {
               var2.writer().writeByte(var1[var3].indexUI);
            }
         }

         this.session.sendMessage(var2);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void crystalCollectLock1(Item[] var1) {
      Message var2 = null;

      try {
         var2 = new Message((byte)20);

         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3] != null) {
               var2.writer().writeByte(var1[var3].indexUI);
            }
         }

         this.session.sendMessage(var2);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void splitItem(Item var1) {
      GameCanvas.msgdlg.update();
      Message var2 = null;

      try {
         (var2 = new Message((byte)22)).writer().writeByte(var1.indexUI);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void l(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)44)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void m(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)99)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void n(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)106)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void o(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)107)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void p(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)66)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void h() {
      Message var1 = null;

      try {
         var1 = new Message((byte)56);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void i() {
      Message var1 = null;

      try {
         var1 = new Message((byte)57);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void j() {
      Message var1 = null;

      try {
         var1 = new Message((byte)46);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void tradeLock(int var1, Item[] var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)45)).writer().writeInt(var1);
         var1 = 0;

         int var4;
         for(var4 = 0; var4 < var2.length; ++var4) {
            if (var2[var4] != null) {
               ++var1;
            }
         }

         var3.writer().writeByte(var1);

         for(var4 = 0; var4 < var2.length; ++var4) {
            if (var2[var4] != null) {
               var3.writer().writeByte(var2[var4].indexUI);
            }
         }

         this.session.sendMessage(var3);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void a(MyVector var1, MyVector var2, int var3) {
      Message var4 = null;
      if (var3 != 0) {
         try {
            Mob var5;
            Char var7;
            if (var1.size() > 0 && var2.size() > 0) {
               if (var3 == 1) {
                  var4 = new Message((byte)4);
               } else if (var3 == 2) {
                  var4 = new Message((byte)73);
               }

               var4.writer().writeByte(var1.size());

               for(var3 = 0; var3 < var1.size(); ++var3) {
                  var5 = (Mob)var1.elementAt(var3);
                  var4.writer().writeByte(var5.m);
               }

               for(var3 = 0; var3 < var2.size(); ++var3) {
                  if ((var7 = (Char)var2.elementAt(var3)) != null) {
                     var4.writer().writeInt(var7.charID);
                  } else {
                     var4.writer().writeInt(-1);
                  }
               }
            } else if (var1.size() > 0) {
               var4 = new Message((byte)60);

               for(var3 = 0; var3 < var1.size(); ++var3) {
                  var5 = (Mob)var1.elementAt(var3);
                  var4.writer().writeByte(var5.m);
               }
            } else if (var2.size() > 0) {
               var4 = new Message((byte)61);

               for(var3 = 0; var3 < var2.size(); ++var3) {
                  var7 = (Char)var2.elementAt(var3);
                  var4.writer().writeInt(var7.charID);
               }
            }

            if (var4 != null) {
               this.session.sendMessage(var4);
               return;
            }
         } catch (Exception var6) {
         }
      }

   }

   public final void pickItem(int var1) {
      if (!Char.canPickItemMapID(var1)) {
         return;
      }
      Message var2 = null;

      try {
         (var2 = new Message((byte)-14)).writer().writeShort(var1);
         this.session.sendMessage(var2);
         Char.reservePickItemMapID(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         if (var2 != null) {
            var2.cleanup();
         }
      }

   }

   public final void throwItem(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)-12)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void returnTownFromDead() {
      Message var1 = null;

      try {
         var1 = new Message((byte)-9);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void l() {
      Message var1 = null;

      try {
         var1 = new Message((byte)-10);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void getTask(int var1, int var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)47)).writer().writeByte(var1);
         var3.writer().writeByte(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void c(String var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)-23)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void d(String var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-78)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void m() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-122);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void n() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-121);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void o() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-120);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void updateItem() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-119);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void clientOk() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-101);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void tradeInvite(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)43)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void addFriend(String var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)59)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void t(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)80)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void u(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)81)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void testInvite(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)65)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void addCuuSat(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)68)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void buffLive(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-79)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void addParty(String var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)79)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void y(int var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-109)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void z(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-93)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void aa(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-87)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void ab(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-86)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void r() {
      Message var1 = null;

      try {
         (var1 = new Message((byte)74)).writer().writeByte(Char.getMyChar().cDir);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void s() {
      Message var1 = null;

      try {
         var1 = messageSubCommand((byte)-88);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void a(boolean var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-76)).writer().writeBoolean(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void t() {
      Message var1 = null;

      try {
         var1 = new Message((byte)83);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void u() {
      Message var1 = null;

      try {
         var1 = messageSubCommand((byte)-85);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void ac(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)100)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void a(MyVector var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)25)).writer().writeByte(var1.size());

         for(int var3 = 0; var3 < var1.size(); ++var3) {
            Char var4 = (Char)var1.elementAt(var3);
            var2.writer().writeInt(var4.charID);
         }

         this.session.sendMessage(var2);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void v() {
      Message var1 = null;

      try {
         var1 = messageSubCommand((byte)-84);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void g(String var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-83)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void pleaseInputParty(String var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)23)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void i(String var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)24)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void viewInfo(String name) {
      Message ms = null;

      try {
         (ms = new Message((byte)93)).writer().writeUTF(name);
         ms.writer().writeByte(0);
         this.session.sendMessage(ms);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         ms.cleanup();
      }

   }

   public final void k(String var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)-20)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void l(String var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)-21)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void a(String var1, String var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)-22)).writer().writeUTF(var1);
         var3.writer().writeUTF(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void m(String var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)-19)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void b(String var1, String var2) {
      Message var3 = null;

      try {
         (var3 = messageNotMap((byte)-99)).writer().writeUTF(var1);
         var3.writer().writeUTF(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void a(String var1, byte[] var2, byte var3) {
      Message var4 = null;

      try {
         (var4 = messageSubCommand((byte)-67)).writer().writeUTF(var1);
         var4.writer().writeInt(var2.length);
         var4.writer().write(var2);
         var4.writer().writeByte(var3);
         this.session.sendMessage(var4);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var4.cleanup();
      }

   }

   public final void n(String var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-65)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void a(String var1, int var2) {
      Message var3 = null;

      try {
         (var3 = messageNotMap((byte)-97)).writer().writeInt(var2);
         var3.writer().writeUTF(var1);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void requestIcon(int var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-115)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void requestClanInfo() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-113);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void requestClanMember() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-112);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void requestClanItem() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-111);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void requestClanLog() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-114);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void clanInvite(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-63)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void clanPlease(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-61)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void ag(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-62)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void ah(int var1) {
      Message var2 = null;

      try {
         (var2 = messageSubCommand((byte)-60)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void o(String var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-95)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void b(String var1, int var2) {
      Message var3 = null;

      try {
         (var3 = messageNotMap((byte)-94)).writer().writeUTF(var1);
         var3.writer().writeByte(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void p(String var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-93)).writer().writeUTF(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void aa() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-92);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void ai(int var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-90)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void aj(int var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-89)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void ab() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-91);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void d(int var1, int var2, int var3) {
      Message var4 = null;

      try {
         (var4 = messageNotMap((byte)-88)).writer().writeByte(var1);
         var4.writer().writeByte(var2);
         var4.writer().writeByte(var3);
         this.session.sendMessage(var4);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var4.cleanup();
      }

   }

   public final void inviteClanDun(String charName) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-87)).writer().writeUTF(charName);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void inviteClanBattlefield(String charName) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-70)).writer().writeUTF(charName);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void ac() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-68);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void ad() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-69);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void ae() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-62);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void k(int var1, int var2) {
      Message var3 = null;

      try {
         (var3 = messageNotMap((byte)-85)).writer().writeByte(var1);
         var3.writer().writeInt(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void ak(int var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-105)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void al(int var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-102)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void l(int var1, int var2) {
      Message var3 = null;

      try {
         (var3 = messageNotMap((byte)-104)).writer().writeInt(var1);
         var3.writer().writeInt(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void am(int var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-103)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void af() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-82);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void rewardCT() {
      Message var1 = null;

      try {
         var1 = messageNotMap((byte)-79);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void sendToSaleItem(Item var1, int var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)102)).writer().writeByte(var1.indexUI);
         var3.writer().writeInt(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void requestItemAuction(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)104)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void ao(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)105)).writer().writeInt(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void ah() {
      Message var1 = null;

      try {
         (var1 = messageNotMap((byte)-72)).writer().writeByte(GameScr.indexSelect);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void sendClanItem(String charName) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)-61)).writer().writeByte(GameScr.indexSelect);
         var2.writer().writeUTF(charName);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }
   }
   
   public final void sendClanItem(String charName, int indexClanItem) {
      Message message = null;

      try {
         (message = messageNotMap((byte)-61)).writer().writeByte(indexClanItem);
         message.writer().writeUTF(charName);
         this.session.sendMessage(message);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         message.cleanup();
      }
   }

   public final void ai() {
      Message var1 = null;

      try {
         (var1 = messageNotMap((byte)-60)).writer().writeByte(GameScr.indexSelect);
         this.session.sendMessage(var1);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var1.cleanup();
      }

   }

   public final void d(Item[] var1) {
      GameCanvas.msgdlg.update();
      Message var2 = null;

      try {
         var2 = new Message((byte)110);

         int var3;
         for(var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3] != null && (var1[var3].template.id == 10 || var1[var3].template.id == 11)) {
               var2.writer().writeByte(var1[var3].indexUI);
               break;
            }
         }

         for(var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3] != null && (var1[var3].template.id == 455 || var1[var3].template.id == 456)) {
               var2.writer().writeByte(var1[var3].indexUI);
            }
         }

         this.session.sendMessage(var2);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void e(Item[] var1) {
      Message var2 = null;

      try {
         var2 = new Message((byte)110);

         int var3;
         for(var3 = 0; var3 < 24; ++var3) {
            if (var1[var3] != null && (var1[var3].template.id == 10 || var1[var3].template.id == 11)) {
               var2.writer().writeByte(var1[var3].indexUI);
               break;
            }
         }

         for(var3 = 0; var3 < 24; ++var3) {
            if (var1[var3] != null && (var1[var3].template.id == 455 || var1[var3].template.id == 456)) {
               var2.writer().writeByte(var1[var3].indexUI);
            }
         }

         this.session.sendMessage(var2);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void a(Item var1, Item[] var2) {
      GameCanvas.msgdlg.update();
      Message var3 = null;

      try {
         (var3 = new Message((byte)111)).writer().writeByte(var1.indexUI);

         for(int var8 = 0; var8 < var2.length; ++var8) {
            if (var2[var8] != null && (var2[var8].template.id == 455 || var2[var8].template.id == 456 || var2[var8].template.id == 457)) {
               var3.writer().writeByte(var2[var8].indexUI);
            }
         }

         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void b(Item var1, Item[] var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)111)).writer().writeByte(var1.indexUI);

         for(int var8 = 0; var8 < 24; ++var8) {
            if (var2[var8] != null && (var2[var8].template.id == 455 || var2[var8].template.id == 456 || var2[var8].template.id == 457)) {
               var3.writer().writeByte(var2[var8].indexUI);
            }
         }

         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void c(Item var1, Item[] var2) {
      GameCanvas.msgdlg.update();
      Message var3 = null;

      try {
         (var3 = new Message((byte)112)).writer().writeByte(var1.indexUI);
         int var9 = 0;

         for(int var4 = 0; var4 < var2.length; ++var4) {
            if (var2[var4] != null && var2[var4].template.id == 454) {
               var3.writer().writeByte(var2[var4].indexUI);
               ++var9;
            }
         }

         if (var9 < 20) {
            GameCanvas.setText(mResources.ry);
            return;
         }

         this.session.sendMessage(var3);
      } catch (Exception var7) {
         var7.printStackTrace();
         return;
      } finally {
         var3.cleanup();
      }

   }

   public final void ap(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)113)).writer().writeShort(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void a(String var1, String var2, byte var3) {
      Message var4 = null;
      var1 = var1 + " " + var2;

      try {
         (var4 = new Message((byte)-21)).writer().writeUTF(var1);
         var4.writer().writeByte(var3);
         this.session.sendMessage(var4);
         return;
      } catch (Exception var7) {
      } finally {
         var4.cleanup();
      }

   }

   public final void dichChuyenSilent(Item var1, Item[] var2) {
      Message var3 = null;

      try {
         if (var1 == null || var2 == null) {
            return;
         }

         (var3 = new Message((byte)112)).writer().writeByte(var1.indexUI);
         int var9 = 0;

         for(int var4 = 0; var4 < var2.length; ++var4) {
            if (var2[var4] != null && var2[var4].template.id == 454) {
               var3.writer().writeByte(var2[var4].indexUI);
               ++var9;
            }
         }

         if (var9 < 20) {
            return;
         }

         this.session.sendMessage(var3);
      } catch (Exception var7) {
         var7.printStackTrace();
         return;
      } finally {
         if (var3 != null) {
            var3.cleanup();
         }
      }

   }

   public final void b(short var1, String var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)118)).writer().writeUTF(var2);
         var3.writer().writeShort(var1);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void b(short var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)119)).writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void c(String var1, String var2, String var3) {
      try {
         Message var4;
         (var4 = messageNotLogin((byte)-123)).writer().writeUTF(var1);
         var4.writer().writeUTF(var2);
         var4.writer().writeUTF(var3);
         this.session.sendMessage(var4);
         var4.cleanup();
      } catch (IOException var5) {
         var5.printStackTrace();
      }
   }

   public final void a(byte var1, String var2) {
      Message var3 = null;

      try {
         (var3 = new Message((byte)121)).writer().writeByte(var1);
         var3.writer().writeUTF(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void a(byte var1, short var2) {
      Message var3 = null;

      try {
         (var3 = messageNotMap((byte)122)).writer().writeByte(var1);
         var3.writer().writeByte(var2);
         this.session.sendMessage(var3);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var3.cleanup();
      }

   }

   public final void a(short var1, String var2, byte var3) {
      Message var4 = null;

      try {
         (var4 = new Message((byte)92)).writer().writeShort(var1);
         var4.writer().writeUTF(var2);
         var4.writer().writeByte(var3);
         this.session.sendMessage(var4);
         return;
      } catch (Exception var7) {
         var7.printStackTrace();
      } finally {
         var4.cleanup();
      }

   }

   public final void a(String var1, String var2, String var3, String var4, String var5, String var6, String var7) {
      try {
         Message var8;
         (var8 = new Message((byte)123)).writer().writeUTF(var1);
         var8.writer().writeUTF(var2);
         var8.writer().writeUTF(var3);
         var8.writer().writeUTF(var4);
         var8.writer().writeUTF(var5);
         var8.writer().writeUTF(var6);
         var8.writer().writeUTF(var7);
         this.session.sendMessage(var8);
         var8.cleanup();
      } catch (Exception var9) {
         var9.printStackTrace();
      }
   }

   public final void a(byte var1) {
      Message var2 = null;

      try {
         (var2 = messageNotMap((byte)122)).writer().writeByte(4);
         var2.writer().writeByte(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void ngockham(byte var1, Item var2, Item var3, Item[] var4) {
      GameCanvas.msgdlg.update();
      Message var5 = null;

      try {
         (var5 = new Message((byte)124)).writer().writeByte(var1);
         if (var1 == 0) {
            var5.writer().writeByte(var2.indexUI);
            var5.writer().writeByte(var3.indexUI);

            for(var1 = 0; var1 < var4.length; ++var1) {
               if (var4[var1] != null) {
                  var5.writer().writeByte(var4[var1].indexUI);
               }
            }
         } else if (var1 == 1) {
            var5.writer().writeByte(var3.indexUI);

            for(var1 = 0; var1 < var4.length; ++var1) {
               if (var4[var1] != null) {
                  var5.writer().writeByte(var4[var1].indexUI);
               }
            }
         } else if (var1 == 2 || var1 == 3) {
            var5.writer().writeByte(var3.indexUI);
         }

         this.session.sendMessage(var5);
         return;
      } catch (Exception var8) {
         var8.printStackTrace();
      } finally {
         var5.cleanup();
      }

   }

   public final void ngockhamSilent(byte var1, Item var2, Item var3, Item[] var4) {
      Message var5 = null;

      try {
         (var5 = new Message((byte)124)).writer().writeByte(var1);
         if (var1 == 0) {
            var5.writer().writeByte(var2.indexUI);
            var5.writer().writeByte(var3.indexUI);

            for(var1 = 0; var1 < var4.length; ++var1) {
               if (var4[var1] != null) {
                  var5.writer().writeByte(var4[var1].indexUI);
               }
            }
         } else if (var1 == 1) {
            var5.writer().writeByte(var3.indexUI);

            for(var1 = 0; var1 < var4.length; ++var1) {
               if (var4[var1] != null) {
                  var5.writer().writeByte(var4[var1].indexUI);
               }
            }
         } else if (var1 == 2 || var1 == 3) {
            var5.writer().writeByte(var3.indexUI);
         }

         this.session.sendMessage(var5);
         return;
      } catch (Exception var8) {
         var8.printStackTrace();
      } finally {
         if (var5 != null) {
            var5.cleanup();
         }
      }

   }

   public final void f(Item[] var1) {
      GameCanvas.msgdlg.update();
      Message var2 = null;

      try {
         var2 = new Message((byte)126);

         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3] != null) {
               var2.writer().writeByte(var1[var3].indexUI);
            }
         }

         this.session.sendMessage(var2);
         return;
      } catch (Exception var6) {
         var6.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void c(short var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)125)).writer().writeByte(1);
         var2.writer().writeShort(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }

   public final void aq(int var1) {
      Message var2 = null;

      try {
         (var2 = new Message((byte)125)).writer().writeByte(2);
         var2.writer().writeShort(var1);
         this.session.sendMessage(var2);
         return;
      } catch (Exception var5) {
         var5.printStackTrace();
      } finally {
         var2.cleanup();
      }

   }
}
