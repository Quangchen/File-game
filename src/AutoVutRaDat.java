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
            while (index != -1) {
                for(int i = 0; i < Char.getMyChar().arrItemBag.length; i++){
                    Item item = Char.getMyChar().arrItemBag[i];
                    if(item != null && item.template.id == idItem && (System.currentTimeMillis() - item.timeThrow) >= 5000L){
                        item.timeThrow  = System.currentTimeMillis();
                        Service.getInstance().throwItem(i);
                    }
                }
                index = Char.getIndexItemById(idItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
