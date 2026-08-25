public final class AutoHD9xReward extends Auto {

    private static final int HD9X_REWARD_ID = 647;
    private static final long REWARD_WAIT_MS = 8000L;

    private boolean rewardRequested;
    private int beforeRewardCount;
    private long requestedAt;
    private long lastActionAt;

    public AutoHD9xReward() {
        super.a();
        this.rewardRequested = false;
        this.beforeRewardCount = 0;
        this.requestedAt = 0L;
        this.lastActionAt = 0L;
    }

    public final void run() {
        if (this.isDead()) {
            Auto.autoRemap(true);
            return;
        }

        if (AutoHD9xManager.isHD9xMap(TileMap.mapID)) {
            return;
        }

        if (!TileMap.isTruong(TileMap.mapID)) {
            Auto.goTruongIfNeeded();
            return;
        }

        if (System.currentTimeMillis() - this.lastActionAt < 1500L) {
            return;
        }

        this.lastActionAt = System.currentTimeMillis();

        if (this.rewardRequested) {
            if (Char.k(HD9X_REWARD_ID) > this.beforeRewardCount) {
                GameScr.chatPopup("HD9x: đã nhận rương");
                AutoHD9xManager.finishReward();
                return;
            }

            if (System.currentTimeMillis() - this.requestedAt < REWARD_WAIT_MS) {
                return;
            }

            this.rewardRequested = false;
        }

        if (Char.countNullSlot() <= 0) {
            GameScr.chatPopup("HD9x: hành trang đầy, chưa nhận rương");
            return;
        }

        this.beforeRewardCount = Char.k(HD9X_REWARD_ID);
        this.requestedAt = System.currentTimeMillis();
        this.rewardRequested = true;
        GameScr.PickNpc(0, 2, 0);
        Service.getInstance().af();
    }

    public final String toString() {
        return "HD9x nhận rương";
    }
}
