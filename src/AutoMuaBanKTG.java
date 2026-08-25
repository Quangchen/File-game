

public final class AutoMuaBanKTG implements Runnable {
    public static long a;
    public static boolean b;
 
    public AutoMuaBanKTG() {
    }
 
    public final void run() {
       while(true) {
          if (b) {
             if (System.currentTimeMillis() - a >= SettingMuaBan.b && !SettingAutoMuaBan.b().equals("")) {
                Service.getInstance().l(" " + SettingAutoMuaBan.b() + " , " + TileMap.mapNames[TileMap.mapID] + " , khu: " + TileMap.zoneID + ", chát hd để xem hướng dẫn ! ");
                a = System.currentTimeMillis();
                continue;
             }
 
             if (!SettingAutoMuaBan.b().equals("")) {
                continue;
             }
 
             GameScr.chatPopup("Chưa cài đặt mua bán");
             b = false;
          }
 
          return;
       }
    }
 }
 