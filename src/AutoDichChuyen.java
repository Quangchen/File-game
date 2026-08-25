
public final class AutoDichChuyen implements Runnable {
    private final Item a;
 
    AutoDichChuyen(Item var1) {
       this.a = var1;
    }
 
    public final void run() {
       try {
          GameScr.getInstance().closeDialog();
          MyVector var7 = Char.getListItemByID(454);
          int var2 = var7.size();
          GameScr.itemSplit = this.a;
          if (var2 >= 20) {
             GameScr.cl = true;
             GameScr.arrItemSplit = new Item[24];
             int var3 = 0;
 
             for(int var4 = 0; var4 < 20; ++var4) {
                Item var9 = (Item)var7.elementAt(var7.size() - 1);
                GameScr.arrItemSplit[var3++] = var9;
                var7.removeElementAt(var7.size() - 1);
             }
 
             Service.getInstance().dichChuyenSilent(GameScr.itemSplit, GameScr.arrItemSplit);
             if (Char.getMyChar() != null && Char.getMyChar().charName != null) {
                Service.getInstance().viewInfo(Char.getMyChar().charName);
             }
             Auto.sleep(2000L);
          }
       } catch (Exception var10) {
//          GameScr.chatPopup("Lỗi");
       } finally {
          GameScr.itemSplit = null;
          GameScr.arrItemSplit = null;
          GameScr.cl = false;
       }
 
    }
 }
 
