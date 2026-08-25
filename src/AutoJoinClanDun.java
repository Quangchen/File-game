/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class AutoJoinClanDun extends Auto {

    protected void run() {
        if (super.isDead()) {
            Auto.autoRemap(true);
            return;
        } else {
            if (TileMap.mapID == 139 || TileMap.mapID == 140 || TileMap.mapID == 141 || TileMap.mapID == 142 || TileMap.mapID == 143 || TileMap.mapID == 144 || TileMap.mapID == 145 || TileMap.mapID == 146 || TileMap.mapID == 148 || TileMap.mapID == 147) {
                Code.n();
                Auto.sleep(1000L);
                Auto.autoRemap(true);
            }

            if (TileMap.isLangCo(TileMap.mapID) || TileMap.isLangTT(TileMap.mapID)) {
                Auto.tuSat();
                return;
            }
            
            if (!TileMap.isTruong(TileMap.mapID) && !TileMap.isClanDun()) {
                TileMap.gomap(1);
            }
        }
    }

    public void joinClanDun() {
        GameScr.chatPopup("Vào LDGT");
        GameScr.PickNpc(0, 1, 0);
    }

    public String toString() {
        return "Chờ vào lãnh địa";
    }

}
