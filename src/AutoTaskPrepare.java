public final class AutoTaskPrepare extends Auto {

    public static final int TYPE_NVHN = 0;
    public static final int TYPE_TA_THU = 1;
    public static final int TYPE_DANH_VONG = 2;

    private final int type;
    private long lastNoticeAt = 0L;

    public AutoTaskPrepare(int type) {
        this.type = type;
    }

    public final void a() {
        super.a();
        super.mapID = 1;
        super.zoneID = -1;
    }

    protected final void run() {
        if (super.isDead()) {
            Auto.autoRemap(false);
            return;
        }

        if (Auto.goTruongIfNeeded()) {
            this.noticeWaiting();
            return;
        }

        Auto returnAuto = super.instance;

        if (this.type == TYPE_NVHN) {
            Code.autoNVHN.a();
            Code.autoNVHN.instance = returnAuto;
            Code.auto = Code.autoNVHN;
            GameScr.chatPopup("Bắt đầu Auto NVHN");
        } else if (this.type == TYPE_TA_THU) {
            Code.autoTaThuSolo.a();
            Code.autoTaThuSolo.instance = returnAuto;
            Code.auto = Code.autoTaThuSolo;
            GameScr.chatPopup("Bắt đầu Auto Tà Thú");
        } else {
            Code.autoDV.init();
            Code.autoDV.instance = returnAuto;
            Code.auto = Code.autoDV;
            GameScr.chatPopup("Bắt đầu Auto Danh Vọng");
        }
    }

    private void noticeWaiting() {
        long now = System.currentTimeMillis();

        if (now - this.lastNoticeAt < 500L) {
            return;
        }

        GameScr.chatPopup(this.getName() + ": đang về trường");
        this.lastNoticeAt = now;
    }

    public final String toString() {
        return "Chuẩn bị " + this.getName();
    }

    private String getName() {
        if (this.type == TYPE_NVHN) {
            return "NVHN";
        }

        if (this.type == TYPE_TA_THU) {
            return "Tà Thú";
        }

        return "Danh Vọng";
    }
}
