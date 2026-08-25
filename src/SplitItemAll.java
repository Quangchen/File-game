/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class SplitItemAll implements Runnable {

    private int index;
    private int number;

    SplitItemAll(int index, int number) {
        this.index = index;
        this.number = number;
    }

    public final void run() {
        while(number > 0 && number < Char.getMyChar().arrItemBag[index].quantity && Char.getMyChar().countNullSlot() > 0){
            Service.getInstance().k(index, number);
            Service.getInstance().viewInfo(Char.getMyChar().charName);
            NinjaUtil.sleep(200L);
        }
        GameCanvas.currentDialog = null;
    }
}
