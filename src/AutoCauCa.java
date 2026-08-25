public final class AutoCauCa extends Auto {

    private static final int MAP_LANG_CHAI = 32;
    private static final int TILE_WATERFLOW = 64;
    private static final int FISH_HUYET_LONG = 598;
    private static final int FISH_TUYET_SA = 599;
    private static final int FISH_LINH_TAM = 600;
    private static final long MOVE_DELAY = 1200L;
    private static final long ROUTE_DELAY = 3000L;
    private static final long BUY_DELAY = 5000L;

    private long lastUseAt;
    private long lastMoveAt;
    private long lastRouteAt;
    private long lastBuyAt;
    private int targetX = -1;
    private int targetY = -1;
    private int attempts;
    private String status = "Khởi động";

    public static void start() {
        if (Code.auto instanceof AutoCauCa) {
            GameScr.chatPopup("Auto câu cá đang chạy");
            return;
        }

        FormAutoCauCa.load();
        AutoCauCa auto = new AutoCauCa();
        auto.init();
        Code.setAuto(auto);
        GameScr.chatPopup("Bật auto câu cá");
    }

    public static void toggle() {
        if (Code.auto instanceof AutoCauCa) {
            stop();
        } else {
            start();
        }
    }

    public static void stop() {
        if (Code.auto instanceof AutoCauCa) {
            Code.backToInstance();
            GameScr.chatPopup("Dừng auto câu cá");
        }
    }

    private void init() {
        super.a();
        super.mapID = MAP_LANG_CHAI;
        super.zoneID = -1;
        super.g = true;
        this.lastUseAt = 0L;
        this.lastMoveAt = 0L;
        this.lastRouteAt = 0L;
        this.lastBuyAt = 0L;
        this.targetX = -1;
        this.targetY = -1;
        this.attempts = 0;
        this.status = "Khởi động";
    }

    protected final void run() {
        try {
            FormAutoCauCa.load();
            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null) {
                this.status = "Chờ nhân vật";
                sleep(500L);
                return;
            }

            if (!me.isHuman) {
                this.status = "Chỉ câu ở chủ thân";
                sleep(1000L);
                return;
            }

            if (TileMap.mapID != MAP_LANG_CHAI) {
                goLangChai();
                return;
            }

            if (Char.countNullSlot() <= FormAutoCauCa.MinEmptySlot) {
                this.status = "Thiếu ô trống";
                sleep(1000L);
                return;
            }

            if (!ensureItems()) {
                sleep(800L);
                return;
            }

            if (!ensureFishingPoint(me)) {
                sleep(300L);
                return;
            }

            long now = System.currentTimeMillis();
            int delay = FormAutoCauCa.getDelayMs();
            if (now - this.lastUseAt < (long) delay) {
                return;
            }

            int rodIndex = Char.getIndexItemById(FormAutoCauCa.RodItemId);
            if (rodIndex < 0) {
                this.status = "Thiếu cần";
                return;
            }

            Service.getInstance().useItem(rodIndex);
            this.lastUseAt = now;
            ++this.attempts;
            this.status = "Câu " + this.attempts + " cá:" + countFish();
        } catch (Exception e) {
            this.status = "Lỗi auto câu";
            sleep(1000L);
        }
    }

    private void goLangChai() {
        long now = System.currentTimeMillis();
        this.status = "Đi Làng Chài";
        if (TileMap.ag || now - this.lastRouteAt < ROUTE_DELAY) {
            sleep(300L);
            return;
        }

        this.lastRouteAt = now;
        TileMap.direction(MAP_LANG_CHAI);
    }

    private boolean ensureItems() {
        boolean hasRod = Char.k(FormAutoCauCa.RodItemId) > 0;
        boolean hasBait = Char.k(FormAutoCauCa.BaitItemId) > 0 || FormAutoCauCa.BaitItemId2 > 0 && Char.k(FormAutoCauCa.BaitItemId2) > 0;
        long now = System.currentTimeMillis();

        if (hasRod && hasBait) {
            return true;
        }

        if (now - this.lastBuyAt < BUY_DELAY) {
            this.status = !hasRod ? "Chờ mua cần" : "Chờ mua mồi";
            return false;
        }

        this.lastBuyAt = now;
        if (!hasRod) {
            this.status = "Mua cần " + FormAutoCauCa.RodItemId;
            if (FormAutoCauCa.AutoBuyRod) {
                AutoBuyShop.buyNow(FormAutoCauCa.RodItemId, FormAutoCauCa.RodShopId, FormAutoCauCa.RodBuyCount);
            }
            return false;
        }

        this.status = "Mua mồi";
        if (FormAutoCauCa.AutoBuyBait) {
            if (FormAutoCauCa.BaitItemId > 0) {
                AutoBuyShop.buyNow(FormAutoCauCa.BaitItemId, FormAutoCauCa.BaitShopId, FormAutoCauCa.BaitBuyCount);
            }
            if (FormAutoCauCa.BaitItemId2 > 0 && Char.k(FormAutoCauCa.BaitItemId) <= 0) {
                AutoBuyShop.buyNow(FormAutoCauCa.BaitItemId2, FormAutoCauCa.BaitShopId2, FormAutoCauCa.BaitBuyCount2);
            }
        }
        return false;
    }

    private boolean ensureFishingPoint(Char me) {
        if (isFishingWater(me.cx, me.cy)) {
            return true;
        }

        int x = FormAutoCauCa.FishX;
        int y = FormAutoCauCa.FishY;
        if (x < 0 || y < 0 || !isFishingWater(x, y)) {
            if (this.targetX < 0 || this.targetY < 0 || !isFishingWater(this.targetX, this.targetY)) {
                findFishingPoint(me);
            }
            x = this.targetX;
            y = this.targetY;
        }

        if (x < 0 || y < 0) {
            this.status = "Không thấy nước";
            return false;
        }

        long now = System.currentTimeMillis();
        this.status = "Đứng điểm câu";
        if (now - this.lastMoveAt >= MOVE_DELAY) {
            this.lastMoveAt = now;
            Char.charMove(x, y);
        }
        return isFishingWater(Char.getMyChar().cx, Char.getMyChar().cy);
    }

    private void findFishingPoint(Char me) {
        int bestX = -1;
        int bestY = -1;
        int bestDistance = 2147483647;

        if (TileMap.types == null) {
            this.targetX = -1;
            this.targetY = -1;
            return;
        }

        for (int ty = 0; ty < TileMap.b; ++ty) {
            for (int tx = 0; tx < TileMap.a; ++tx) {
                int index = ty * TileMap.a + tx;
                if (index >= 0 && index < TileMap.types.length && (TileMap.types[index] & TILE_WATERFLOW) == TILE_WATERFLOW) {
                    int px = tx * TileMap.size + TileMap.size / 2;
                    int py = ty * TileMap.size + TileMap.size;
                    if (!isFishingWater(px, py)) {
                        py = ty * TileMap.size + TileMap.size / 2;
                    }

                    if (isFishingWater(px, py)) {
                        int distance = Math.abs(px - me.cx) + Math.abs(py - me.cy);
                        if (distance < bestDistance) {
                            bestDistance = distance;
                            bestX = px;
                            bestY = py;
                        }
                    }
                }
            }
        }

        this.targetX = bestX;
        this.targetY = bestY;
    }

    private static boolean isFishingWater(int x, int y) {
        return TileMap.mapID == MAP_LANG_CHAI && TileMap.a(x, y - 12, TILE_WATERFLOW);
    }

    private static int countFish() {
        return Char.k(FISH_HUYET_LONG) + Char.k(FISH_TUYET_SA) + Char.k(FISH_LINH_TAM);
    }

    public final String toString() {
        return "Auto Câu Cá " + this.status;
    }
}
