/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baomi
 */
public final class AutoLoseLoiDai extends Auto {
   private static boolean aaa;
   private static boolean aba;
   private long aca = 0L;
   private long ada;

   public final void aea() {
      super.a();
      aaa = false;
      aba = false;
      this.ada = System.currentTimeMillis();
   }

   public final void run() {
      if (isDead()) {
         if (TileMap.mapID == 160) {
            try {
               Thread.sleep(500L);
            } catch (InterruptedException var3) {
            }
         } else {
            Auto.autoRemap(true);
         }
      } else {
         if (System.currentTimeMillis() - this.ada >= 30000L) {
            this.ada = System.currentTimeMillis();
         }

         Code.apa();
         if (!aaa && !aba) {
            if (TileMap.mapID == 110) {
               aaa = true;
               aba = false;
               return;
            }

            if (TileMap.mapID != SettingNVDV.mapLoiDai) {
               if (TileMap.mapID != 160 && TileMap.mapID != 129 && TileMap.mapID != 149) {
                  this.goMap(SettingNVDV.mapLoiDai, SettingNVDV.khuLoiDai, -1, -1);
                  return;
               }

               try {
                  Thread.sleep(1000L);
               } catch (InterruptedException var4) {
               }

               Auto.tuSat();
               return;
            }

            if (TileMap.zoneID != SettingNVDV.khuLoiDai) {
               Auto.changeZone(SettingNVDV.khuLoiDai);
            }
         } else if (aaa && !aba) {
            if (TileMap.mapID == 160) {
               aaa = true;
               aba = true;
               Session_ME.getInstance().c();
               Controller.getInstance().d();
               return;
            }

            if (TileMap.mapID == 110) {
               Npc var7 = GameScr.findNpc(0);
               if (Char.getMyChar().cx != var7.cx || Char.getMyChar().cy != var7.cy) {
                  Char.charMove(var7.cx, var7.cy);

                  try {
                     Thread.sleep(1000L);
                  } catch (InterruptedException var5) {
                  }

                  this.aca = System.currentTimeMillis();
                  return;
               }

               if (System.currentTimeMillis() - this.aca >= 2000L) {
                  GameScr.PickNpc(0, 1, 0);
                  Service.getInstance().setXuCuocLoiDai((short)11212, (String)String.valueOf(SettingNVDV.xuCuocLoiDai));
                  GameCanvas.setMaxTextLenght();

                  try {
                     Thread.sleep(3000L);
                  } catch (InterruptedException var6) {
                  }

                  this.aca = System.currentTimeMillis();
               }
            }
         } else if (aaa && aba && TileMap.mapID != 160) {
            aaa = false;
            aba = false;
         }

      }
   }

   public final String toString() {
      return "Lôi đài lose";
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
