
public class As50 extends As10 {
    private int a;
    private static final int[] b = new int[]{0, 1, 1, 72, 72, 27, 27, 1};
    private static final int[] c = new int[]{0, 9, 9, 10, 10, 11, 11, 9};
    private static final int[] d = new int[]{0, 0, 1, 0, 1, 0, 1, 2};
    private static final int[] e = new int[]{0, 94, 114, 99, 109, 105, 119, 1199};
    private static final int[] f = new int[]{-1, 40, 49, 58, 67, 76, 85, 1186};
    private static final int[] ab = new int[]{-1, 41, 50, 59, 68, 77, 86, 1187};
    private static final int[] ac = new int[]{-1, 42, 51, 60, 69, 78, 87, 1188};
    private static final int[] ad = new int[]{0, 2, 2, 71, 71, 26, 26, 2};
 
    public As50(int var1) {
       super.a();
       this.a = var1;
    }

    private boolean isWoodSwordEquipped(Char var1) {
       try {
          return var1 != null && var1.arrItemBody != null && var1.arrItemBody[1] != null && var1.arrItemBody[1].template.id == 194;
       } catch (Exception var3) {
          return false;
       }
    }

    private void selectAttackSkill() {
       Char var1 = Char.getMyChar();
       if (var1 != null && var1.vSkillFight != null) {
          for (int var2 = 0; var2 < var1.vSkillFight.size(); ++var2) {
             Skill var3;
             if ((var3 = (Skill)var1.vSkillFight.elementAt(var2)) != null && (var3.template.type == 1 || var3.template.type == 3)) {
                var1.selectSkill = var3;
                Auto.selectSkill = var3;
                Service.getInstance().selectSkill(var3.template.id);
                return;
             }
          }
       }

    }

    private void ensureWoodSword(Char var1) throws InterruptedException {
       if (!this.isWoodSwordEquipped(var1)) {
          int var2 = Char.getIndexItemById(194);
          if (var2 >= 0) {
             GameScr.chatPopup("Dung kiem go 194");
             Service.getInstance().useItem(var2);

             for (int var3 = 0; var3 < 12 && !this.isWoodSwordEquipped(Char.getMyChar()); ++var3) {
                Thread.sleep(150L);
             }
          } else if (var1.arrItemBody == null || var1.arrItemBody[1] == null) {
             GameScr.chatPopup("Khong co kiem go 194");
          }
       }

       this.selectAttackSkill();
    }
 
    public boolean isDone(Char var1) {
       return var1.cLevel >= 100;
    }
 
    public void doTask(Char var1, byte var2, byte var3) {
       if (var1.ctaskId < 9) {
          super.doTask(var1, (byte)var2, (byte)var3);
       } else {
           try {
               int var4;
               int var5;
               Item var6;
               int var7;
               int var9;
               int var11;
               Char var12;
               Effect var13;
               Skill var14;
               Item var15;
               int var24;
               int var27;
               Item var31;
               Code var36;
               switch (var1.ctaskId) {
                   case 9:
                       if (var1.nClass.classId != 0) {
                           this.receiveHeadmasterTask();
                           return;
                       }
                       
                       if (this.a == 0) {
                           GameScr.chatPopup("Hãy vào lớp!");
                           var36 = Code.instance;
                           Code.tatAuto();
                           return;
                       }

                       if (var1.cLevel < 10) {
                           var4 = ad[this.a];
                           if (TileMap.mapID == var4) {
                               this.ensureWoodSword(var1);
                               this.pickUpItem(-1);
                               this.attack(-1, 1);
                               return;
                           }

                           GameScr.chatPopup("Chưa đủ cấp 10, đi map cạnh up cấp");
                           this.goMap(var4, -1, -1, -1);
                           return;
                       }
                       
                       var4 = b[this.a];
                       if (TileMap.mapID != var4) {
                           this.goMap(var4, -2, -1, -1);
                           return;
                       }
                       
                       GameScr.PickNpc(5, 1, 0);
                       
                       for(var5 = 0; var5 < var1.arrItemBag.length; ++var5) {
                           if ((var15 = var1.arrItemBag[var5]) != null && (var15.template.type == 22 || var15.template.type == 27)) {
                               Service.getInstance().useItem(var15.indexUI);
                           }
                       }
                       
                       Thread.sleep(300L);
                       if ((var31 = var1.arrItemBody[1]) != null) {
                           if (Char.countNullSlot() <= 0) {
                               GameScr.chatPopup("Cần trống 1 ô hành trang để tháo vũ khí");
                               return;
                           }

                           Service.getInstance().itemBodyToBag(var31.indexUI);
                           LockGame.q();

                           for(var5 = 0; var5 < 10 && Char.getMyChar().arrItemBody[1] != null; ++var5) {
                               Thread.sleep(200L);
                           }

                           if (Char.getMyChar().arrItemBody[1] != null) {
                               GameScr.chatPopup("Chưa tháo được vũ khí, chờ vòng sau");
                               return;
                           }
                       }
                       
                       GameScr.PickNpc(c[this.a], 1, d[this.a]);
                       
                       for(var5 = 0; var5 < 20 && Char.getItemByID(e[this.a]) == null; ++var5) {
                           Thread.sleep(200L);
                       }
                       
                       if ((var31 = Char.getItemByID(f[this.a])) != null) {
                           Service.getInstance().useItem(var31.indexUI);
                       }
                       
                       if ((var31 = Char.getItemByID(e[this.a])) != null) {
                           Service.getInstance().useItem(var31.indexUI);
                       }
                       
                       Thread.sleep(300L);
                       this.receiveHeadmasterTask((byte)b[this.a], (byte)c[this.a]);
                       return;
                   case 10:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 28) {
                               this.pickUpItem(-1);
                               this.attack(5, 1);
                               return;
                           }
                           
                           this.goMap(28, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 4) {
                               this.pickUpItem(-1);
                               this.attack(6, 1);
                               return;
                           }
                           
                           this.goMap(4, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 2) {
                           if (TileMap.mapID == 46) {
                               this.pickUpItem(-1);
                               this.attack(7, 1);
                               return;
                           }
                           
                           this.goMap(46, -1, -1, -1);
                           return;
                       }
                       break;
                   case 11:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 28) {
                               this.pickUpItem(-1);
                               this.attack(-1, 1);
                               return;
                           }
                           
                           this.goMap(28, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a != 1) {
                           break;
                       }
                       
                       for(var4 = 0; var4 < GameScr.vCharInMap.size(); ++var4) {
                           Char var33;
                           if ((var33 = (Char)GameScr.vCharInMap.elementAt(var4)) != null) {
                               Service.getInstance().addFriend(var33.charName);
                           }
                       }
                       
                       var2 = (byte) super.zoneID;
                       GameScr var28 = GameScr.getInstance();
                       Npc var32;
                       if ((var32 = GameScr.findNpc(13)) == null || var32.statusMe == 15) {
                           super.zoneID = TileMap.zoneID;
                           return;
                       }
                       
                       if (Math.abs(var32.cx - Char.getMyChar().cx) > 22 || Math.abs(var32.cy - Char.getMyChar().cy) > 22) {
                           Char.charMove(var32.cx, var32.cy);
                       }
                       
                       Service.getInstance().openUIZone();
                       LockGame.e();
                       var4 = -1;
                       if (var2 < 0) {
                           var2 = (byte) (var28.zones.length - 1);
                       } else if (var2 >= var28.zones.length) {
                           var2 = 0;
                       }
                       
                       var5 = 0;
                       
                       for(var24 = (var2 + 1) % var28.zones.length; var24 != var2; var24 = (var24 + 1) % var28.zones.length) {
                           if (var28.zones[var24] < 20 && var28.zones[var24] > var5) {
                               var4 = var24;
                               var5 = var28.zones[var24];
                           }
                       }
                       
                       super.zoneID = var4;
                       Service.getInstance().requestChangeZone(var4, -1);
                       TileMap.g();
                       Thread.sleep(100L);
                       break;
                   case 12:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 3) {
                               this.pickUpItem(-1);
                               this.attack(-1, 1);
                               return;
                           }
                           
                           this.goMap(3, -1, -1, -1);
                           return;
                       }
                       
                       boolean var20 = false;
                       var5 = -1;
                       var15 = null;
                       if (var1.taskMaint.a == 1) {
                           var20 = true;
                           var5 = (new int[]{194, 94, 114, 99, 109, 105, 119, 1199})[var1.nClass.classId];
                           if ((var15 = var1.arrItemBody[1]) == null) {
                               var20 = false;
                               var15 = Char.getItemByID(var5);
                           }
                       } else if (var1.taskMaint.a == 2) {
                           var20 = true;
                           var5 = 174;
                           if ((var15 = var1.arrItemBody[9]) == null) {
                               var20 = false;
                               var15 = Char.getItemByID(174);
                           }
                       } else if (var1.taskMaint.a == 3) {
                           var20 = true;
                           var5 = var1.cgender == 1 ? 124 : 125;
                           if ((var15 = var1.arrItemBody[8]) == null) {
                               var20 = false;
                               var15 = Char.getItemByID(var5);
                           }
                       }
                       
                       if (var15 == null) {
                           if (TileMap.mapID == 4) {
                               this.pickUpItem(var5);
                               this.attack(-1, 1);
                               return;
                           }
                           
                           this.goMap(4, -1, -1, -1);
                           return;
                       }
                       
                       var3 = 0;
                       var24 = 0;
                       if (var15.isTypeClothe()) {
                           var3 = (byte) (GameScr.upClothe[var15.upgrade] / 2);
                           var24 = GameScr.coinUpClothes[var15.upgrade];
                       } else if (var15.isTypeAdorn()) {
                           var3 = (byte) (GameScr.upAdorn[var15.upgrade] / 2);
                           var24 = GameScr.coinUpAdorns[var15.upgrade];
                       } else if (var15.isTypeWeapon()) {
                           var3 = (byte) (GameScr.upWeapon[var15.upgrade] / 2);
                           var24 = GameScr.coinUpWeapons[var15.upgrade];
                       }
                       
                       if (var3 << 1 > Char.af() || var24 << 1 > var1.yen) {
                           if (TileMap.mapID == 46) {
                               this.pickUpItem(1);
                               this.attack(-1, 1);
                               return;
                           }
                           
                           this.goMap(46, -1, -1, -1);
                           return;
                       }
                       
                       if (TileMap.mapID != 22) {
                           this.goMap(22, -2, -1, -1);
                           return;
                       }
                       
                       if (var20) {
                           Service.getInstance().b(var15.template.type);
                           LockGame.q();
                       }
                       
                       var7 = var15.upgrade;
                       GameScr.PickNpc(6, 0, 0);
                       LockGame.q();
                       GameScr.itemUpGrade = var15;
                       
                       for(var27 = 0; var27 < 2 && var15.upgrade == var7; ++var27) {
                           GameScr.arrItemUpGrade = new Item[18];
                           var9 = 0;
                           int var30 = 0;
                           
                           for(var4 = 0; var4 < var1.arrItemBag.length && var30 < var3; ++var4) {
                               if ((var31 = var1.arrItemBag[var4]) != null && var31.template.type == 26 && var31.template.id <= 3) {
                                   var1.arrItemBag[var4] = null;
                                   GameScr.arrItemUpGrade[var9++] = var31;
                                   var30 += GameScr.upClothe[var31.template.id];
                               }
                           }
                           
                           do {
                               try {
                                   Thread.sleep(3000L);
                                   Service.getInstance().upgradeItem1(var15, GameScr.arrItemUpGrade, false);
                                   LockGame.q();
                               } catch (InterruptedException ex) {
                                   
                               }
                           } while(GameScr.arrItemUpGrade[0] != null);
                       }
                       
                       GameScr.itemUpGrade = null;
                       Service.getInstance().useItem(var15.indexUI);
                       if (var15.upgrade > var7) {
                           LockGame.o();
                           return;
                       }
                       break;
                   case 13:
                       Item var18;
                       if ((var18 = var1.arrItemBody[1]) != null && var18.upgrade < 2) {
                           var5 = GameScr.upWeapon[var18.upgrade] / 2;
                           var2 = (byte) GameScr.coinUpWeapons[var18.upgrade];
                           if (var5 << 1 <= Char.af() && var2 << 1 <= var1.yen) {
                               if (TileMap.mapID != 22) {
                                   this.goMap(22, -2, -1, -1);
                                   return;
                               }
                               
                               Service.getInstance().b(var18.template.type);
                               LockGame.q();
                               var3 = (byte) var18.upgrade;
                               GameScr.PickNpc(6, 0, 0);
                               LockGame.q();
                               GameScr.itemUpGrade = var18;
                               
                               for(var24 = 0; var24 < 2 && var18.upgrade == var3; ++var24) {
                                   GameScr.arrItemUpGrade = new Item[18];
                                   var7 = 0;
                                   var27 = 0;
                                   
                                   for(var9 = 0; var9 < var1.arrItemBag.length && var27 < var5; ++var9) {
                                       Item var10;
                                       if ((var10 = var1.arrItemBag[var9]) != null && var10.template.type == 26 && var10.template.id <= 3) {
                                           var1.arrItemBag[var9] = null;
                                           GameScr.arrItemUpGrade[var7++] = var10;
                                           var27 += GameScr.upClothe[var10.template.id];
                                       }
                                   }
                                   
                                   do {
                                       Thread.sleep(3000L);
                                       Service.getInstance().upgradeItem(var18, GameScr.arrItemUpGrade, false);
                                       LockGame.q();
                                   } while(GameScr.arrItemUpGrade[0] != null);
                               }
                               
                               GameScr.itemUpGrade = null;
                               Service.getInstance().useItem(var18.indexUI);
                               return;
                           }
                           
                           if (TileMap.mapID == 4) {
                               this.pickUpItem(1);
                               this.attack(-1, 1);
                               return;
                           }
                           
                           this.goMap(4, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 4) {
                               this.pickUpItem(-1);
                               this.attack(-1, 1);
                               return;
                           }
                           
                           this.goMap(4, -1, -1, -1);
                           return;
                       }
                       
                       var5 = var1.taskMaint.a == 1 ? 56 : (var1.taskMaint.a == 2 ? 0 : 73);
                       if (TileMap.mapID != var5) {
                           return;
                       }
                       
                       if (var1.cHP < var1.cMaxHP / 2 && var1.cHP > 0) {
                           var1.e(16);
                       }
                       
                       if (var1.cMP < var1.cMaxMP / 2 && var1.cHP > 0) {
                           var1.e(17);
                       }
                       
                       if (GameScr.vCharInMap.size() > 0 && (var12 = (Char)GameScr.vCharInMap.elementAt(0)) != null) {
                           var14 = Auto.selectSkill;
                           if (Res.e(var1.cx - var12.cx) > var14.dx || Res.e(var1.cy - var12.cy) > var14.dy) {
                               Char.charMove(var12.cx < TileMap.c ? var12.cx : TileMap.c - 50, var12.cy);
                           }
                           
                           Auto.v.removeAllElements();
                           Auto.w.removeAllElements();
                           Auto.w.addElement(var12);
                           Service.getInstance().a(Auto.v, Auto.w, 1);
                           if (System.currentTimeMillis() - var14.lastTimeUseThisSkill >= (long)var14.coolDown) {
                               var14.lastTimeUseThisSkill = System.currentTimeMillis();
                               var14.paintCanNotUseSkill = true;
                               var1.b(GameScr.skillPaints[var14.template.id], 0);
                               return;
                           }
                       }
                       break;
                   case 14:
                       if (var1.cLevel >= 15 && (var15 = Char.getItemByID(ab[var1.nClass.classId])) != null) {
                           GameScr.chatPopup("Học sách kĩ năng");
                           Service.getInstance().useItem(var15.indexUI);
                           Thread.sleep(1000L);
                       }
                       
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 29) {
                               this.pickUpItem(-1);
                               this.attack(-1, 1);
                               return;
                           }
                           
                           this.goMap(29, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 29 && super.zoneID == TileMap.zoneID) {
                               var5 = Code.khoangCachNhat < 0 ? -1 : Code.khoangCachNhat * Code.khoangCachNhat;
                               ItemMap var16 = null;
                               
                               for(var3 = 0; var3 < GameScr.vItemMap.size(); ++var3) {
                                   ItemMap var29;
                                   var7 = Math.abs((var29 = (ItemMap)GameScr.vItemMap.elementAt(var3)).x - var1.cx);
                                   var27 = Math.abs(var29.y - var1.cy);
                                   var9 = var7 * var7 + var27 * var27;
                                   if (!var29.isPickedUp && var29.template.id == 212 && Char.canPickItemTemplate(var29.template) && (var5 < 0 || var9 < var5)) {
                                       var5 = var9;
                                       var16 = var29;
                                   }
                               }
                               
                               if (var16 == null) {
                                   super.zoneID = (super.zoneID + 1) % 30;
                                   return;
                               }
                               
                               Char.charMove(var16.xEnd, var16.yEnd);
                               Service.getInstance().pickItem(var16.itemMapID);
                               
                               for(var3 = 0; var3 < 5 && !LockGame.c(); ++var3) {
                               }
                               
                               var16.isPickedUp = true;
                               return;
                           }
                           
                           this.goMap(29, super.zoneID, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 2) {
                           if (TileMap.mapID == 40) {
                               this.attack(15, 1);
                               this.pickUpItem(213);
                               return;
                           }
                           
                           this.goMap(40, -1, -1, -1);
                           return;
                       }
                       break;
                   case 15:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 8) {
                               this.pickUpItem(-1);
                               this.attack(-1, 1);
                               return;
                           }
                           
                           this.goMap(8, -1, -1, -1);
                           return;
                       }
                       
                       if (TileMap.mapID != var2) {
                           super.goMap(var2, -2, -1, -1);
                           return;
                       }
                       
                       GameScr.PickNpc(var3, 0, 0);
                       LockGame.o();
                       Auto.tuSat();
                       return;
                   case 16:
                       if (var1.cLevel >= 20 && (var15 = Char.getItemByID(ac[var1.nClass.classId])) != null) {
                           GameScr.chatPopup("Học sách kĩ năng");
                           Service.getInstance().useItem(var15.indexUI);
                           Thread.sleep(1000L);
                       }
                       
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 63) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(63, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 63) {
                               this.pickUpItem(-1);
                               this.attack(23, 1);
                               return;
                           }
                           
                           this.goMap(63, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 2) {
                           if (TileMap.mapID == 47) {
                               this.pickUpItem(-1);
                               this.attack(24, 1);
                               return;
                           }
                           
                           this.goMap(47, -1, -1, -1);
                           return;
                       }
                       break;
                   case 17:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 33) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(33, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 33) {
                               if ((var1 = Char.getMyChar()).ctaskId != 17 || var1.taskMaint.a >= var1.taskMaint.e.length - 1) {
                                   var36 = Code.instance;
                                   Code.tatAuto();
                                   return;
                               }
                               
                               if (var1.e == null) {
                                   GameScr.PickNpc(17, 0, 0);
                                   Thread.sleep(2000L);
                                   return;
                               }
                               
                               if (Res.distance(var1.cx, var1.cy, var1.e.cx, var1.e.cy) > 200) {
                                   AutoDanTre.a(var1.e);
                               }
                               
                               var12 = Char.getMyChar();
                               var14 = Auto.selectSkill;
                               if (Char.tickAutoBuff) {
                                   for(var4 = 0; var4 < var12.vSkillFight.size(); ++var4) {
                                       Skill var19;
                                       if ((var19 = (Skill)var12.vSkillFight.elementAt(var4)) != null && !var19.isCooldown() && var19.template.type == 2 && (var19.template.id != 47 || var12.cHP < var12.cMaxHP * Char.aHpValue / 100)) {
                                           boolean var22 = false;
                                           
                                           for(var7 = 0; var7 < var12.vEff.size(); ++var7) {
                                               Effect var8;
                                               if ((var8 = (Effect)var12.vEff.elementAt(var7)) != null && var8.e.c == var19.template.iconId) {
                                                   var22 = true;
                                                   break;
                                               }
                                           }
                                           
                                           if (!var22) {
                                               var14 = var19;
                                               Thread.sleep(500L);
                                               break;
                                           }
                                       }
                                   }
                               }
                               
                               label789: {
                                   if (var14.template.type == 2) {
                                       Service.getInstance().r();
                                   } else {
                                       Char var17;
                                       MyVector var21;
                                       Char var34;
                                       label786: {
                                           if ((var17 = Char.getMyChar()).e != null) {
                                               var21 = GameScr.vCharInMap;
                                               
                                               for(var24 = 0; var24 < var21.size(); ++var24) {
                                                   Char var25;
                                                   if ((var25 = (Char)var21.elementAt(var24)) != null && var25.statusMe != 14 && var25.statusMe != 5 && var25.statusMe != 15 && (var25.cTypePk == 3 || var17.cTypePk == 3 || var25.cTypePk == 1 && var17.cTypePk == 1 || var17.da >= 0 && var17.da == var25.charID || var17.cz >= 0 && var17.cz == var25.charID) && Res.distance(var25.cx, var25.cy, var17.e.cx, var17.e.cy) < 200) {
                                                       var34 = var25;
                                                       break label786;
                                                   }
                                               }
                                           }
                                           
                                           var34 = null;
                                       }
                                       
                                       var17 = var34;
                                       if (var34 != null) {
                                           if (Res.distance(var17.cx, var17.cy, var12.e.cx, var12.e.cy) < 200 && (Res.e(var12.cx - var17.cx) > var14.dx || Res.e(var12.cy - var17.cy) > var14.dy)) {
                                               AutoDanTre.a(var17);
                                           }
                                           
                                           Service.getInstance().selectSkill(var14.template.id);
                                           Auto.v.removeAllElements();
                                           Auto.w.removeAllElements();
                                           Auto.w.addElement(var17);
                                           Service.getInstance().a(Auto.v, Auto.w, 2);
                                       } else {
                                           Mob var35;
                                           label744: {
                                               if ((var17 = Char.getMyChar()).e != null) {
                                                   var21 = GameScr.vMobAttack;
                                                   
                                                   for(var24 = 0; var24 < var21.size(); ++var24) {
                                                       Mob var26;
                                                       if ((var26 = (Mob)var21.elementAt(var24)) != null && var26.hp > 0 && var26.h != 0 && var26.h != 1 && Res.distance(var26.curX, var26.curY, var17.e.cx, var17.e.cy) < 200) {
                                                           var35 = var26;
                                                           break label744;
                                                       }
                                                   }
                                               }
                                               
                                               var35 = null;
                                           }
                                           
                                           Mob var23 = var35;
                                           if (var35 == null) {
                                               break label789;
                                           }
                                           
                                           if (Res.distance(var23.curX, var23.curY, var12.e.cx, var12.e.cy) < 200 && (Res.e(var12.cx - var23.curX) > var14.dx || Res.e(var12.cy - var23.curY) > var14.dy)) {
                                               this.c(var23);
                                           }
                                           
                                           Service.getInstance().selectSkill(var14.template.id);
                                           Auto.v.removeAllElements();
                                           Auto.w.removeAllElements();
                                           Auto.v.addElement(var23);
                                           Service.getInstance().a(Auto.v, Auto.w, 1);
                                       }
                                   }
                                   
                                   if (System.currentTimeMillis() - var14.lastTimeUseThisSkill >= (long)var14.coolDown) {
                                       var14.lastTimeUseThisSkill = System.currentTimeMillis();
                                       var14.paintCanNotUseSkill = true;
                                       if (!Code.isBangSkill) {
                                           var12.b(GameScr.skillPaints[var14.template.id], 0);
                                       }
                                   }
                               }
                               
                               if (Char.getMyChar().cMP < Char.getMyChar().cMaxMP * Char.aMpValue / 100) {
                                   Char.getMyChar().e(17);
                               }
                               
                               if (Char.getMyChar().cHP < Char.getMyChar().cMaxHP * Char.aHpValue / 100) {
                                   var11 = (int)(System.currentTimeMillis() / 1000L);
                                   
                                   for(var2 = 0; var2 < Char.getMyChar().vEff.size(); ++var2) {
                                       if ((var13 = (Effect)Char.getMyChar().vEff.elementAt(var2)).e.a == 21 && var13.c - (var11 - var13.b) >= 2) {
                                           return;
                                       }
                                   }
                                   
                                   Char.getMyChar().e(16);
                                   return;
                               }
                           } else {
                               this.goMap(33, super.zoneID, -1, -1);
                           }
                           
                           return;
                       }
                       break;
                   case 18:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 50) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(50, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 50) {
                               this.pickUpItem(216);
                               this.attack(26, 1);
                               return;
                           }
                           
                           this.goMap(50, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 2) {
                           if (TileMap.mapID == 11) {
                               this.pickUpItem(217);
                               this.attack(27, 1);
                               return;
                           }
                           
                           this.goMap(11, -1, -1, -1);
                           return;
                       }
                       break;
                   case 19:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 11) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(11, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a != 1) {
                           break;
                       }
                       
                       if (TileMap.mapID == 63) {
                           Char.charMove(1691, 336);
                           var4 = 0;
                           
                           while(true) {
                               for(var5 = 0; var5 < Char.getMyChar().arrItemBag.length; ++var5) {
                                   var6 = Char.getMyChar().arrItemBag[var5];
                                   if (var6 != null && var6.template.id == 219) {
                                       Service.getInstance().useItem(var6.indexUI);
                                       ++var4;
                                       GameScr.chatPopup("Lấy Được " + var4 + " bình nước");
                                       Thread.sleep(4000L);
                                   }
                               }
                           }
                       }
                       
                       this.goMap(63, -1, -1, -1);
                   case 22:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 35) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(35, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 35) {
                               this.pickUpItem(230);
                               this.attack(33, 1);
                               return;
                           }
                           
                           this.goMap(35, -1, -1, -1);
                           return;
                       }
                       
                       return;
                   case 20:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 50) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(50, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID != 74) {
                               this.goMap(8, -2, -1, -1);
                               long var10000 = 5000L;
                               GameScr.PickNpc(15, 1, 0);
                               return;
                           }
                           
                           this.attack(69, -1);
                           this.pickUpItem(221);
                           if (Char.getMyChar().cMP < Char.getMyChar().cMaxMP / 2) {
                               Char.getMyChar().e(17);
                           }
                           
                           if (Char.getMyChar().cHP < Char.getMyChar().cMaxHP * 3 / 4) {
                               var11 = (int)(System.currentTimeMillis() / 1000L);
                               
                               for(var2 = 0; var2 < Char.getMyChar().vEff.size(); ++var2) {
                                   if ((var13 = (Effect)Char.getMyChar().vEff.elementAt(var2)).e.a == 21 && var13.c - (var11 - var13.b) >= 2) {
                                       return;
                                   }
                               }
                               
                               Char.getMyChar().e(16);
                               return;
                           }
                       }
                       break;
                   case 21:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 12) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(12, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 12) {
                               this.pickUpItem(-1);
                               this.attack(30, 1);
                               return;
                           }
                           
                           this.goMap(12, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 2) {
                           if (TileMap.mapID == 34) {
                               this.pickUpItem(-1);
                               this.attack(31, 1);
                               return;
                           }
                           
                           this.goMap(34, -1, -1, -1);
                           return;
                       }
                       
                       return;
                   case 23:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 35) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(35, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 78) {
                               this.pickUpItem(232);
                               this.attack(-1, -1);
                               return;
                           }
                           
                           this.goMap(35, -1, -1, -1);
                           Char.charMove(1816, 432);
                           
                           for(var5 = 0; var5 < Char.getMyChar().arrItemBag.length; ++var5) {
                               var6 = Char.getMyChar().arrItemBag[var5];
                               if (var6 != null && var6.template.id == 231) {
                                   Service.getInstance().useItem(var6.indexUI);
                               }
                           }
                           
                           Thread.sleep(2000L);
                           return;
                       }
                       
                       return;
                   case 24:
                       return;
                   case 25:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 13) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(13, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 13) {
                               this.pickUpItem(-1);
                               this.attack(37, 1);
                               return;
                           }
                           
                           this.goMap(13, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 2) {
                           if (TileMap.mapID == 52) {
                               this.pickUpItem(-1);
                               this.attack(38, 1);
                               return;
                           }
                           
                           this.goMap(52, -1, -1, -1);
                           return;
                       }
                       
                       return;
                   case 26:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 13) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(13, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 64) {
                               this.pickUpItem(236);
                               this.attack(1, 1);
                               return;
                           }
                           
                           this.goMap(64, -1, -1, -1);
                           return;
                       }
                       
                       return;
                   case 27:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 13) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(13, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 14) {
                               this.pickUpItem(238);
                               this.attack(41, 1);
                               return;
                           }
                           
                           this.goMap(14, -1, -1, -1);
                           return;
                       }
                       
                       return;
                   case 28:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 14) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           } else {
                               this.goMap(14, -1, -1, -1);
                               return;
                           }
                       } else if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 14) {
                               this.pickUpItem(-1);
                               this.attack(42, 1);
                               return;
                           } else {
                               this.goMap(14, -1, -1, -1);
                               return;
                           }
                       } else if (var1.taskMaint.a == 2) {
                           if (TileMap.mapID == 15) {
                               this.pickUpItem(-1);
                               this.attack(43, 1);
                               return;
                           } else {
                               this.goMap(15, -1, -1, -1);
                               return;
                           }
                       } else {
                           return;
                       }
                   case 29:
                       if (var1.taskMaint.a == 0) {
                           if (TileMap.mapID == 15) {
                               this.pickUpItem(-1);
                               this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(15, -1, -1, -1);
                           return;
                       }
                       
                       if (var1.taskMaint.a == 1) {
                           if (TileMap.mapID == 15) {
                               this.pickUpItem(-1);
                               this.attack(44, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                               return;
                           }
                           
                           this.goMap(15, -1, -1, -1);
                           return;
                       }
                       
                       return;
               }} catch (InterruptedException ex) {
               
           }
 
       }
    }
 
    public String toString() {
       return "Auto Nhiệm Vụ 50";
    }
 }
 
