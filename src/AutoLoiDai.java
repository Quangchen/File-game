/**
 *
 * @author Admin
 */
public final class AutoLoiDai extends Auto {
   private static boolean aaa;
   private static boolean aba;
   private long aca;
   private long ada;
   private long aea = 0L;
   private long bha;

   public final void init() {
      super.a();
      aaa = false;
      aba = false;
   }

   public final void run() {
      if (isDead()) {
         if (TileMap.mapID == 160) {
            try {
               Thread.sleep(500L);
            } catch (InterruptedException var6) {
            }
         } else {
            Auto.autoRemap(true);
         }
      } else {
         int var2;
         String[] var3;
         int var4;
         Npc var11;
         if (!aaa && !aba) {
            if (TileMap.mapID == 110) {
               aaa = true;
               aba = false;
               return;
            }

            if (TileMap.mapID == SettingNVDV.mapLoiDai && TileMap.zoneID == SettingNVDV.khuLoiDai) {
               var11 = GameScr.findNpc(0);
               if (Char.getMyChar().cx == var11.cx && Char.getMyChar().cy == var11.cy) {
                  for(var2 = 0; var2 < GameScr.vCharInMap.size(); ++var2) {
                     var3 = Code.splitString(SettingNVDV.nameCharLoiDai, ",");

                     for(var4 = 0; var4 < var3.length; ++var4) {
                        if (var3[var4].equals(((Char)GameScr.vCharInMap.elementAt(var2)).charName) && System.currentTimeMillis() - this.ada >= 5000L) {
                           GameScr.PickNpc(0, 3, 0);
                           Service.getInstance().setXuCuocLoiDai((short)11211, (String)var3[var4]);
                           GameCanvas.setMaxTextLenght();
                           this.ada = System.currentTimeMillis();
                        }
                     }
                  }

                  return;
               }

               Char.charMove(var11.cx, var11.cy);

               try {
                  Thread.sleep(1000L);
                  return;
               } catch (InterruptedException var7) {
                  return;
               }
            }

            if (TileMap.mapID != SettingNVDV.mapLoiDai) {
               if (TileMap.mapID != 160 && TileMap.mapID != 129 && TileMap.mapID != 149) {
                  this.goMap(SettingNVDV.mapLoiDai, SettingNVDV.khuLoiDai, -1, -1);
                  return;
               }

               try {
                  Thread.sleep(1000L);
               } catch (InterruptedException var10) {
               }

               Auto.tuSat();
            } else {
               Auto.changeZone(SettingNVDV.khuLoiDai);
            }
         } else if (aaa && !aba) {
            if (TileMap.mapID == 160) {
               this.aca = System.currentTimeMillis();
               aaa = true;
               aba = true;
               return;
            }

            if (TileMap.mapID == 110) {
               var11 = GameScr.findNpc(0);
               if (Char.getMyChar().cx != var11.cx || Char.getMyChar().cy != var11.cy) {
                  Char.charMove(var11.cx, var11.cy);

                  try {
                     Thread.sleep(1000L);
                  } catch (InterruptedException var8) {
                  }

                  this.aea = System.currentTimeMillis();
                  return;
               }

               if (System.currentTimeMillis() - this.aea >= 3000L) {
                  GameScr.PickNpc(0, 1, 0);
                  Service.getInstance().setXuCuocLoiDai((short)11212, (String)String.valueOf(SettingNVDV.xuCuocLoiDai));
                  GameCanvas.setMaxTextLenght();

                  try {
                     Thread.sleep(3000L);
                  } catch (InterruptedException var9) {
                  }

                  this.aea = System.currentTimeMillis();
               }
            }
         } else if (aaa && aba) {
            if (TileMap.mapID != 160) {
               aaa = false;
               aba = false;
               this.aca = System.currentTimeMillis();
               return;
            }

            if (System.currentTimeMillis() - this.aca >= 59000L) {
               Char var1 = null;

               for(var2 = 0; var2 < GameScr.vCharInMap.size(); ++var2) {
                  var3 = Code.splitString(SettingNVDV.nameCharLoiDai, ",");

                  for(var4 = 0; var4 < var3.length; ++var4) {
                     Char var5;
                     if ((var5 = (Char)GameScr.vCharInMap.elementAt(var2)).charName.equals(var3[var4])) {
                        var1 = var5;
                        break;
                     }
                  }
               }

               if (var1 == null) {
                  aaa = false;
                  aba = false;
                  this.aca = System.currentTimeMillis();
                  return;
               }

               if (Auto.selectSkill != null && var1.cHP > 0) {
                  Skill var12 = Auto.selectSkill;
                  Char var13 = Char.getMyChar();
                  if (var12.template.type == 2) {
                     Service.getInstance().selectSkill(var12.template.id);
                     Service.getInstance().r();
                  } else {
                     if ((var12.template.type == 1 || var12.template.type == 3) && (Res.e(var13.cx - var1.cx) > var12.dx + 30 || Res.e(var13.cy - var1.cy) > var12.dy + 30) && System.currentTimeMillis() - this.bha > 1500L) {
                        Auto.d(var1);
                        this.bha = System.currentTimeMillis();
                     }

                     Auto.v.removeAllElements();
                     Auto.w.removeAllElements();
                     Auto.w.addElement(var1);
                     Service.getInstance().selectSkill(var12.template.id);
                     Service.getInstance().a((MyVector)Auto.v, (MyVector)Auto.w, (int)2);
                  }

                  if (System.currentTimeMillis() - var12.lastTimeUseThisSkill >= (long)var12.coolDown + 50L) {
                     var12.lastTimeUseThisSkill = System.currentTimeMillis();
                     var12.paintCanNotUseSkill = true;
                     if (!Code.isBangSkill) {
                        var13.b(GameScr.skillPaints[var12.template.id], 0);
                     }
                  }

                  super.x = System.currentTimeMillis();
               }
            }
         }

      }
   }

   public final String toString() {
      return "Lôi đài win";
   }

   public static void afa() {
      aaa = false;
      aba = false;
   }

   static {
      afa();
   }

   public static void aga() {
      aaa = false;
      aba = false;
   }
}
