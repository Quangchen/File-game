/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baomi
 */
public class AutoNTGT extends Auto{
    private long lastTimeUpdate = 0;
    
    protected void run() {
        if (!Code.isInClan(Char.getMyChar())) {
            GameScr.chatPopup("NTGT: không có gia tộc, tự tắt");
            Code.tatAuto();
            return;
        }

        if(System.currentTimeMillis() - lastTimeUpdate >= 60000){
            Service.getInstance().requestClanInfo();
            Service.getInstance().requestClanItem();
            lastTimeUpdate = System.currentTimeMillis();
            GameScr.chatPopup("Update Clan: " + StringUtils.getDateString(lastTimeUpdate));
        }
    }

    public String toString() {
        return "Auto phát NTGT";
    }
    
}
