final class UseAllItem implements Runnable {

    private static final String DELAY_STORE = "UseAllItemDelay";
    private static final int DEFAULT_DELAY_MS = 100;
    private static final int MIN_DELAY_MS = 10;
    private static final int MAX_DELAY_MS = 10000;
    private static boolean running;
    private static int delayMs = -1;
    private Item item;

    private UseAllItem(Item item) {
        this.item = item;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void stop() {
        running = false;
    }

    public static int getDelayMs() {
        if (delayMs < 0) {
            loadDelay();
        }

        return delayMs;
    }

    public static void setDelayMs(int value) {
        delayMs = normalizeDelay(value);
        RMS.writeRecord(DELAY_STORE, String.valueOf(delayMs));
    }

    public static boolean setDelayText(String text) {
        try {
            setDelayMs(Integer.parseInt(text.trim()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void start(Item item) {
        if (item == null) {
            return;
        }

        if (running) {
            running = false;
            return;
        }

        running = true;
        (new Thread(new UseAllItem(item))).start();
    }

    private static boolean waitForFreeSlot(long maxWait) throws Exception {
        long start = System.currentTimeMillis();
        long lastRefresh = 0L;

        while (running && GameCanvas.mScreen instanceof GameScr && System.currentTimeMillis() - start < maxWait) {
            if (Char.countNullSlot() >= 3) {
                return true;
            }

            long now = System.currentTimeMillis();
            if (now - lastRefresh >= 1000L) {
                Service.getInstance().viewInfo(Char.getMyChar().charName);
                lastRefresh = now;
            }

            Thread.sleep(500L);
        }

        return Char.countNullSlot() >= 3;
    }

    private static void loadDelay() {
        try {
            String data = RMS.loadRMSString(DELAY_STORE);
            delayMs = data == null ? DEFAULT_DELAY_MS : normalizeDelay(Integer.parseInt(data.trim()));
        } catch (Exception e) {
            delayMs = DEFAULT_DELAY_MS;
        }
    }

    private static int normalizeDelay(int value) {
        if (value < MIN_DELAY_MS) {
            return MIN_DELAY_MS;
        }

        return value > MAX_DELAY_MS ? MAX_DELAY_MS : value;
    }

    public final void run() {
        GameScr.fg = true;

        try {
            int index = this.item.quantity;
            int delay = getDelayMs();

            while (running && index > 0 && GameCanvas.mScreen instanceof GameScr) {
                waitDoiLongDen();
                if (Char.countNullSlot() < 3) {
                    GameScr.chatPopup("Hành trang gần đầy, quét 10s...");
                    if (!waitForFreeSlot(10000L)) {
                        GameScr.chatPopup("Sau 10s hành trang vẫn đầy, đã tắt Mở all");
                        break;
                    }

                    this.item = Char.getMyChar().arrItemBag[this.item.indexUI];
                    if (this.item != null) {
                        index = this.item.quantity;
                    } else {
                        break;
                    }

                    continue;
                }

                Service.getInstance().useItem(this.item.indexUI);
                Thread.sleep((long) delay);
                --index;

                if (index <= 1) {
                    Service.getInstance().viewInfo(Char.getMyChar().charName);
                    Thread.sleep(500L);
                    waitDoiLongDen();
                    this.item = Char.getMyChar().arrItemBag[this.item.indexUI];
                    if (this.item != null) {
                        index = this.item.quantity;
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }

        running = false;
        GameScr.fg = false;
    }

    private static void waitDoiLongDen() throws Exception {
        while (running && (AutoDoiLongDen.shouldPauseProducers() || AutoRuocDen.isBusy()) && GameCanvas.mScreen instanceof GameScr) {
            Thread.sleep(100L);
        }
    }
}
