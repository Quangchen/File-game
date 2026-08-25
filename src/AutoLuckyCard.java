

public final class AutoLuckyCard implements Runnable {
    public static long a = 10L;
    private static final int LUCKY_CARD_ID = 340;
    private static final int LUCKY_CARD_MAP_ID = 72;
    public final int b;
    public static int c;
    public static int d;
 
    AutoLuckyCard(int var1) {
       this.b = var1;
   }
 
   public final void run() {
      if (this.b <= 0) {
         return;
      }

      if (TileMap.mapID != LUCKY_CARD_MAP_ID && !goToLuckyCardMap()) {
         return;
      }

      if (Char.k(LUCKY_CARD_ID) <= 0) {
         return;
      }

      boolean oldHideLuckyCardUi = AutoDapDo.isHidingLuckyCardUi();
      AutoDapDo.setHideLuckyCardUi(true);

      try {
         openLuckyCardNpc();
         int count = 0;
         while (count < this.b) {
            if (Char.k(LUCKY_CARD_ID) <= 0) {
               break;
            }

            GameScr.indexSelect = 0;
            int beforeResult = AutoDapDo.getLuckyCardResultCount();
            Service.getInstance().ah();
            ++count;
            AutoDapDo.waitLuckyCardResultSilent(beforeResult);
            sleep(a);
         }
      } finally {
         AutoDapDo.hideLuckyCardUiNow();
         AutoDapDo.setHideLuckyCardUi(oldHideLuckyCardUi);
      }
   }

   private static boolean goToLuckyCardMap() {
      try {
         if (!TileMap.direction(LUCKY_CARD_MAP_ID)) {
            return false;
         }

         long start = System.currentTimeMillis();
         while (TileMap.mapID != LUCKY_CARD_MAP_ID && System.currentTimeMillis() - start < 15000L) {
            sleep(200L);
         }

         return TileMap.mapID == LUCKY_CARD_MAP_ID;
      } catch (Exception e) {
         return false;
      }
   }

   private static void openLuckyCardNpc() {
      GameScr.PickNpc(30, 0, 0);
      sleep(50L);
      AutoDapDo.hideLuckyCardUiNow();
   }

   private static void sleep(long time) {
      try {
         if (time > 0L) {
            Thread.sleep(time);
         }
      } catch (Exception e) {
      }
   }
}
 
