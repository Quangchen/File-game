
/**
 *
 * @author quang
 */

public final class AutoTaThuSolo extends Auto {
    private TaskOrder task;
    private int mobId;
    private long lastNotice;
    private long lastReceive;
    private boolean daMuaLenh;
    private int failNhanFree;

    private static final int MAP_NPC = 1;
    private static final int ZONE_NPC = 21;
    private static final int NPC_TA_THU = 25;
    private static final int ITEM_LENH_TA_THU = 268;
    private static final int INDEX_MUA_LENH = 17;

    private int getTaThuZone(int mapId) {
        if (TileMap.mapID == mapId && TileMap.zoneID % 5 == 0) {
            return TileMap.zoneID;
        }

        return 5;
    }

    public final void a() {
        super.a();
        this.task = Char.getTaskOrderById(1);
        this.lastNotice = 0L;
        this.lastReceive = 0L;
        this.daMuaLenh = false;
        this.failNhanFree = 0;

        if (this.task != null) {
            this.mobId = this.task.killId;
            super.mapID = this.task.mapId;
            super.zoneID = this.getTaThuZone(this.task.mapId);
        } else {
            super.mapID = MAP_NPC;
            super.zoneID = ZONE_NPC;
        }
    }

    public final void b() {
        this.task = Char.getTaskOrderById(1);
        super.b();
    }

    private void veNpc() {
        if (TileMap.mapID != MAP_NPC || TileMap.zoneID != ZONE_NPC) {
            GameScr.chatPopup("Auto TT Solo: ve NPC");
            this.goMap(MAP_NPC, ZONE_NPC, -1, -1);
        }
    }

    private boolean nhanNhiemVu() {
        try {
            if (System.currentTimeMillis() - this.lastReceive < 1500L) {
                return false;
            }

            this.lastReceive = System.currentTimeMillis();

            GameScr.chatPopup("Auto TT Solo: nhận nhiệm vụ");
            GameScr.PickNpc(NPC_TA_THU, 2, 0);
            Auto.sleep(1000L);

            this.task = Char.getTaskOrderById(1);

            if (this.task != null) {
                this.failNhanFree = 0;
                this.mobId = this.task.killId;
                super.mapID = this.task.mapId;
                super.zoneID = this.getTaThuZone(this.task.mapId);
                return true;
            }

            ++this.failNhanFree;
        } catch (Exception e) {
            ++this.failNhanFree;
        }

        return false;
    }

    private void traNhiemVu() {
        try {
            GameScr.chatPopup("Auto TT Solo: trả nhiệm vụ");
            GameScr.PickNpc(NPC_TA_THU, 2, 2);
            Auto.sleep(1000L);
            this.task = Char.getTaskOrderById(1);
        } catch (Exception e) {
        }
    }

    private boolean muaVaDungLenh() {
        try {
            if (this.daMuaLenh) {
                GameScr.chatPopup("Auto TT Solo: hết lượt, dừng auto");
                Code.backToInstance();
                return false;
            }

            if (Char.countNullSlot() <= 0) {
                GameScr.chatPopup("Auto TT Solo: hành trang đầy");
                Code.backToInstance();
                return false;
            }

            int truoc = Char.k(ITEM_LENH_TA_THU);

            GameScr.chatPopup("Auto TT Solo: mua lệnh tà thú");
            Service.getInstance().buyItem1(14, INDEX_MUA_LENH, 2);
            Auto.sleep(1200L);

            if (Char.k(ITEM_LENH_TA_THU) <= truoc && Char.getIndexItemById(ITEM_LENH_TA_THU) < 0) {
                GameScr.chatPopup("Auto TT Solo: mua lệnh thất bại");
                Code.backToInstance();
                return false;
            }

            boolean daDung = false;

            for (int i = 0; i < Char.getMyChar().arrItemBag.length; ++i) {
                Item item = Char.getMyChar().arrItemBag[i];

                if (item != null && item.template.id == ITEM_LENH_TA_THU) {
                    GameScr.chatPopup("Auto TT Solo: dung lenh bai");
                    Service.getInstance().useItem(item.indexUI);
                    Auto.sleep(1000L);
                    daDung = true;
                }
            }

            if (!daDung) {
                GameScr.chatPopup("Auto TT Solo: không có lệnh bài");
                Code.backToInstance();
                return false;
            }

            this.daMuaLenh = true;
            this.failNhanFree = 0;
            this.task = Char.getTaskOrderById(1);
            return true;
        } catch (Exception e) {
            GameScr.chatPopup("Auto TT Solo: lỗi mua/dùng lệnh");
            Code.backToInstance();
            return false;
        }
    }

    private void diMapLamNv() {
        if (this.task == null) {
            return;
        }

        this.mobId = this.task.killId;
        super.mapID = this.task.mapId;

        if (super.zoneID < 0 || super.zoneID > 29 || super.zoneID % 5 != 0) {
            super.zoneID = this.getTaThuZone(super.mapID);
        }

        if (TileMap.mapID != super.mapID || TileMap.zoneID != super.zoneID) {
            GameScr.chatPopup("Auto TT Solo: di map " + super.mapID + " khu " + super.zoneID);
            this.goMap(super.mapID, super.zoneID, -1, -1);
        }
    }

    private void bomHpMp() {
        if (Char.getMyChar().cMP < Char.getMyChar().cMaxMP * Char.aMpValue / 100) {
            Char.getMyChar().e(17);
        }

        if (Char.getMyChar().cHP < Char.getMyChar().cMaxHP * Char.aHpValue / 100) {
            int now = (int)(System.currentTimeMillis() / 1000L);

            for (int i = 0; i < Char.getMyChar().vEff.size(); ++i) {
                Effect eff = (Effect)Char.getMyChar().vEff.elementAt(i);
                if (eff.e.a == 21 && eff.c - (now - eff.b) >= 2) {
                    return;
                }
            }

            Char.getMyChar().e(16);
        }
    }

    public final void run() {
        if (Char.getMyChar().cHP <= 0) {
            Auto.autoRemap(false);
            return;
        }

        if (Auto.goTruongAndLuuToaDoIfNeeded()) {
            return;
        }

        this.task = Char.getTaskOrderById(1);

        if (this.task != null) {
            this.mobId = this.task.killId;
            super.mapID = this.task.mapId;

            if (super.zoneID < 0 || super.zoneID > 29 || super.zoneID % 5 != 0) {
                super.zoneID = this.getTaThuZone(this.task.mapId);
            }

            if (this.task.count >= this.task.maxCount) {
                if (TileMap.mapID != MAP_NPC || TileMap.zoneID != ZONE_NPC) {
                    this.veNpc();
                    return;
                }

                this.traNhiemVu();
                return;
            }

            if (TileMap.mapID != this.task.mapId || TileMap.zoneID != super.zoneID) {
                this.diMapLamNv();
                return;
            }

            if (!Auto.q && Char.getMyChar().isHuman) {
                this.l();
                return;
            }

            this.attack(this.mobId, 8);
            this.bomHpMp();

            if (System.currentTimeMillis() - this.lastNotice > 5000L) {
                GameScr.chatPopup("Auto TT Solo: " + this.task.count + "/" + this.task.maxCount + " khu " + TileMap.zoneID);
                this.lastNotice = System.currentTimeMillis();
            }

            return;
        }

        if (Auto.goTruongIfNeeded()) {
            return;
        }

        if (TileMap.mapID != MAP_NPC || TileMap.zoneID != ZONE_NPC) {
            this.veNpc();
            return;
        }

        if (this.nhanNhiemVu()) {
            return;
        }

        if (this.failNhanFree >= 2) {
            this.muaVaDungLenh();
        }
    }

    public final String toString() {
        if (this.task != null) {
            return "Auto Tà Thú by Chen: " + this.task.count + "/" + this.task.maxCount;
        }

        return "Auto Tà Thú by Chen";
    }
}
