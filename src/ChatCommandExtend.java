
// Source code is decompiled from instance .class file using FernFlower decompiler.
public class ChatCommandExtend {
    public static ChatCommandExtend a = new ChatCommandExtend();
    public static Code b = new Code();
    public static boolean c;
    public static short d = 136;
 
    public ChatCommandExtend() {
    }
 
    public final boolean a(String var1) {
       int var7 = 0;
       StringBuffer var8 = new StringBuffer();
       StringBuffer var9 = new StringBuffer();
 
       int var10;
       label228:
       for(var10 = 0; var10 < var1.length(); ++var10) {
          char var11;
          if ((var11 = var1.charAt(var10)) >= '0' && var11 <= '9' || var11 == ' ') {
             while(true) {
                if (var10 >= var1.length() || (var11 = var1.charAt(var10)) < '0' || var11 > '9') {
                   break label228;
                }
 
                var9.append((char)var11);
                ++var10;
             }
          }
 
          var8.append((char)var11);
       }
 
       String var5 = var8.toString().toLowerCase();
       if (var9.length() > 0) {
          try {
             var7 = Integer.parseInt(var9.toString());
          } catch (Exception var12) {
          }
       }
 
       if (var1.equals("hsl")) {
          GameScr.chatPopup((c ? "Tắt" : "Bật") + " Auto Hồi Sinh Lượng");
          c = !c;
          return true;
       } else if (var5.equals("toiuu")) {
          GameScr.chatPopup("Sieu toi uu: " + (FormToiUu.toggleHideAll() ? "Bat" : "Tat"));
          return true;
       } else {
          int var6;
          if (var5.equals("cnl")) {
             if (Code.auto == null) {
                GameScr.chatPopup("Bạn chưa up nhuyên liệu");
             } else {
                var6 = CountUpItem.e - CountUpItem.a;
                var7 = CountUpItem.f - CountUpItem.b;
                int var3 = CountUpItem.g - CountUpItem.c;
                int var4 = CountUpItem.h - CountUpItem.d;
                var10 = (int)((System.currentTimeMillis() - Code.auto.o) / 1000L);
                GameScr.chatPopup("Up " + ItemTemplateManager.get((short)454).name + " : " + var6 + " " + ItemTemplateManager.get((short)455).name + " : " + var7 + " " + ItemTemplateManager.get((short)456).name + " : " + var3 + " " + ItemTemplateManager.get((short)457).name + " : " + var4 + " trong " + NinjaUtil.b(var10) + " TB / 1h: " + ItemTemplateManager.get((short)454).name + " = " + 3600 * var6 / var10 + " " + ItemTemplateManager.get((short)455).name + " = " + 3600 * var7 / var10 + " " + ItemTemplateManager.get((short)456).name + " = " + 3600 * var3 / var10 + " " + ItemTemplateManager.get((short)457).name + " = " + 3600 * var4 / var10);
             }
 
             return true;
          } else if (var1.equals("full")) {
             Code.s.removeAllElements();
             Code.t.removeAllElements();
             var6 = 0;
 
             for(var7 = 0; var7 < GameScr.vMobAttack.size(); ++var7) {
                if (!((Mob)GameScr.vMobAttack.elementAt(var7)).isBoss) {
                   Code.s.addElement(new Integer(((Mob)GameScr.vMobAttack.elementAt(var7)).cx));
                   Code.t.addElement(new Integer(((Mob)GameScr.vMobAttack.elementAt(var7)).cy));
                   if (Char.tickDanhTheoNhom) {
                      Service.getInstance().k("avt " + ((Mob)GameScr.vMobAttack.elementAt(var7)).cx + " " + ((Mob)GameScr.vMobAttack.elementAt(var7)).cy);
                   }
 
                   ++var6;
                }
             }
 
             GameScr.chatPopup("Thêm toàn bộ vị trí quái trong map!");
             return true;
          } else if (var1.startsWith("xem")) {
             GameScr.chatPopup(ItemTemplateManager.get((short)607).name + " : " + CountUpItem.e + " " + ItemTemplateManager.get((short)608).name + " : " + CountUpItem.f + " " + ItemTemplateManager.get((short)609).name + " : " + CountUpItem.g + " " + ItemTemplateManager.get((short)610).name + " : " + CountUpItem.h);
             return true;
          } else if (var1.startsWith("map")) {
             GameScr.chatPopup("" + TileMap.mapID);
             return true;
          } else if (var5.equals("ttgt")) {
             return b.f(var1);
          } else if (var1.startsWith("tt")) {
             if (TileMap.mapID == 22) {
                GameScr.goNPC(12);
                Service.getInstance().getTask(12, 3);
             }
 
             return true;
          } else if (var1.startsWith("vdmq")) {
             Code.pickUpListID[0] = 454;
             Code.pickUpListID[1] = 455;
             Code.pickUpListID[2] = 456;
             Code.pickUpListID[3] = 457;
             Code.pickUpListID[4] = 573;
             Code.pickUpListID[5] = 574;
             Code.pickUpListID[6] = 575;
             Code.pickUpListID[7] = 38;
             Code.pickUpListID[8] = 383;
             Code.pickUpListID[9] = 384;
             GameScr.chatPopup("Thêm: " + ItemTemplateManager.get(Code.pickUpListID[0]).name + "," + ItemTemplateManager.get(Code.pickUpListID[1]).name + "," + ItemTemplateManager.get(Code.pickUpListID[2]).name + "," + ItemTemplateManager.get(Code.pickUpListID[3]).name + "," + ItemTemplateManager.get(Code.pickUpListID[4]).name + "," + ItemTemplateManager.get(Code.pickUpListID[5]).name + "," + ItemTemplateManager.get(Code.pickUpListID[6]).name + "," + ItemTemplateManager.get(Code.pickUpListID[7]).name + "," + ItemTemplateManager.get(Code.pickUpListID[8]).name + "," + ItemTemplateManager.get(Code.pickUpListID[9]).name + ",");
             return true;
          } else if (var1.equals("nhan")) {
             if (TileMap.mapID != AutoReceiver.mapNhanDo) {
                GameScr.chatPopup("Hãy Về " + TileMap.mapNames[AutoReceiver.mapNhanDo]);
             } else {
                if (TileMap.zoneID != AutoReceiver.khuNhanDo) {
                   GameScr.getInstance().j(AutoReceiver.khuNhanDo);
                   GameScr.chatPopup("Chat lại: nhan để bật auto nhận đồ!");
                }
 
                if (TileMap.zoneID == AutoReceiver.khuNhanDo) {
                  GameScr.chatPopup("Auto nhận đồ");
                  Code.setAuto(new AutoReceiver());
                }
             }
 
             return true;
          } else if (var5.equals("gdn")) {
             if (Char.getMyChar().charFocus != null) {
                Code.setAuto(new AutoGiaoDich2(Char.getMyChar().charFocus.charName));
                GameScr.chatPopup("Đã gửi lời mời gd vật phẩm ! ");
             } else {
                GameScr.chatPopup("Hãy chỉ vào đối phương <VPGD>");
             }
 
             return true;
          } else if (var5.equals("gds")) {
             AutoGiaoDich.a = 455;
             if (Char.getMyChar().charFocus != null) {
                Code.setAuto(new AutoGiaoDich(Char.getMyChar().charFocus.charName));
                GameScr.chatPopup("Đã gửi lời mời gd vật phẩm ! ");
             } else {
                GameScr.chatPopup("Hãy chỉ vào đối phương <VPGD>");
             }
 
             return true;
          } else if (var5.equals("gdt")) {
             AutoGiaoDich.a = 456;
             if (Char.getMyChar().charFocus != null) {
                Code.setAuto(new AutoGiaoDich(Char.getMyChar().charFocus.charName));
                GameScr.chatPopup("Đã gửi lời mời gd vật phẩm ! ");
             } else {
                GameScr.chatPopup("Hãy chỉ vào đối phương <VPGD>");
             }
 
             return true;
          } else if (var5.equals("gd")) {
             AutoGiaoDich.a = (short)var7;
             if (Char.getMyChar().charFocus != null) {
                Code.setAuto(new AutoGiaoDich(Char.getMyChar().charFocus.charName));
                GameScr.chatPopup("Đã gửi lời mời gd vật phẩm ! ");
             } else {
                GameScr.chatPopup("Hãy chỉ vào đối phương <VPGD>");
             }
 
             return true;
          } else if (var5.equals("ctt")) {
             AutoGiaoDich.a = 454;
             if (Char.getMyChar().charFocus != null) {
                Code.setAuto(new AutoGiaoDich(Char.getMyChar().charFocus.charName));
                GameScr.chatPopup("Đã gửi lời mời gd vật phẩm ! ");
             } else {
                GameScr.chatPopup("Hãy chỉ vào đối phương <VPGD>");
             }
 
             return true;
          } else if (var5.equals("gdc")) {
             AutoGiaoDich.a = 457;
             if (Char.getMyChar().charFocus != null) {
                Code.setAuto(new AutoGiaoDich(Char.getMyChar().charFocus.charName));
                GameScr.chatPopup("Đã gửi lời mời gd vật phẩm ! ");
             } else {
                GameScr.chatPopup("Hãy chỉ vào đối phương <VPGD>");
             }
 
             return true;
          } else if (!var1.equals("gui")) {
             if (var1.equals("check")) {
                GameScr.chatPopup("" + AutoSend.e() + "Task: " + Char.getMyChar().ctaskId);
                return true;
             } else if (var1.equals("fuji")) {
                if (TileMap.mapID == 168) {
                   Code.setAuto(new AutoFujuka());
                } else {
                   GameScr.chatPopup("Hay vao Thanh dia Fujuka (gap Deidara) roi chat: fuji");
                }

                return true;
             } else if (var1.equals("kb")) {
                for(var6 = 0; var6 < GameScr.vCharInMap.size(); ++var6) {
                   Char var13;
                   if ((var13 = (Char)GameScr.vCharInMap.elementAt(var6)) != null) {
                      Service.getInstance().a(var13.charName);
                   }
                }
 
                return true;
             } else if (var1.equals("ktg")) {
                AutoMuaBanKTG.b = !AutoMuaBanKTG.b;
                (new Thread(new AutoMuaBanKTG())).start();
                GameScr.chatPopup(AutoMuaBanKTG.b ? "Auto Mua Bán KTG : Bật" : "Auto Mua Bán KTG : Tắt");
                return true;
             } else if (var1.startsWith("ct")) {
                if (TileMap.mapID == 22) {
                   GameScr.goNPC(12);
                   Service.getInstance().getTask(12, 4);
                }
 
                return true;
             } else {
                return b.f(var1);
             }
          } else {
             return Code.startGomDoNow();
          }
       }
    }
 }
 
