/**
 *
 * @author baomi
 */
public final class AutoBiCuuSat extends Auto {
   public final void a() {
      super.a();
   }

   public final void run() {
      if (isDead()) {
         if (Char.tickReMap) {
            Auto.autoRemap(true);
         }
      } else if (Char.tickReMap) {
         this.goMap(SettingNVDV.mapCuuSat, SettingNVDV.khuCuuSat, -1, -1);
      }

   }

   public final String toString() {
      return "Bị Cừu Sát !";
   }
}
