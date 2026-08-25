/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class CountDownWait implements Runnable{

    private final String text;
    private final int secondWait;
    
    public CountDownWait(String text, int secondWait) {
        this.text = text;
        this.secondWait = secondWait;
    }
    
    
    public void run() {
        for(int i = secondWait; i >= 0; i--){
            GameScr.chatPopup(text + " (" + i + " giây)");
            Auto.sleep(1000L);
        }
    }
    
}
