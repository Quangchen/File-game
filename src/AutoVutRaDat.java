/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baomi
 */
public class AutoVutRaDat implements Runnable{

    private int idItem;

    AutoVutRaDat(int idItem) {
        this.idItem = idItem;
    }
    
    public void run() {
        try {
            int index = Char.getIndexItemById(idItem);
            int previousQuantity = countItemQuantity();
            long lastProgressAt = System.currentTimeMillis();
            while (index != -1) {
                for(int i = 0; i < Char.getMyChar().arrItemBag.length; i++){
                    Item item = Char.getMyChar().arrItemBag[i];
                    if(item != null && item.template.id == idItem && (System.currentTimeMillis() - item.timeThrow) >= 5000L){
                        item.timeThrow  = System.currentTimeMillis();
                        Service.getInstance().throwItem(i);
                    }
                }

                Thread.sleep(100L);
                int quantity = countItemQuantity();
                if (quantity < previousQuantity) {
                    previousQuantity = quantity;
                    lastProgressAt = System.currentTimeMillis();
                } else if (System.currentTimeMillis() - lastProgressAt >= 20000L) {
                    GameScr.chatPopup("Dừng vứt vật phẩm: máy chủ không phản hồi");
                    break;
                }
                index = Char.getIndexItemById(idItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int countItemQuantity() {
        int total = 0;
        Item[] bag = Char.getMyChar().arrItemBag;
        if (bag == null) {
            return total;
        }

        for (int i = 0; i < bag.length; ++i) {
            Item item = bag[i];
            if (item != null && item.template.id == idItem) {
                total += item.quantity;
            }
        }
        return total;
    }
    
}
