public final class AutoHD9xGather extends Auto {

    public AutoHD9xGather() {
        super.a();
    }

    public final void run() {
        if (!AutoHD9xManager.isRoundActive()) {
            if (super.instance != null) {
                Code.backToInstance();
            } else {
                Code.tatAuto();
            }
        } else if (this.isDead()) {
            Auto.autoRemap(true);
        } else if (TileMap.mapID == 1 && TileMap.zoneID == 21) {
            AutoHD9xManager.onLocalAtSchool();
            Auto.sleep(500L);
        } else {
            if (!Auto.goTruongIfNeeded()) {
                this.goMap(1, 21, -1, -1);
            }
        }
    }

    public final String toString() {
        return "HD9x tập hợp";
    }
}
