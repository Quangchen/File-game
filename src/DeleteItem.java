final class DeleteItem implements Runnable {
    private static final int OP_NONE = 0;
    private static final int OP_SELL = 1;
    private static final int OP_THROW = 2;
    private static final int MAX_MANUAL_DELETE = 200;
    private static final long REQUEST_TIMEOUT = 10000L;
    private static final long RESYNC_COOLDOWN = 8000L;
    private static final long SORT_GUARD_DELAY = 1000L;
    private static final long SEND_GAP = 30L;
    private static final long POLL_DELAY = 5L;
    private static final int FAST_AUTO_BATCH = 6;
    private static final int FAST_MANUAL_BATCH = 18;
    private static final long FAST_REPEAT_GUARD = 1500L;
    private static final long FAST_SORT_GUARD_DELAY = 300L;

    private static boolean running = false;
    private static int pendingOp = OP_NONE;
    private static int pendingIndex = -1;
    private static short pendingId = -1;
    private static int pendingQuantity = 0;
    private static long pendingAt = 0L;
    private static Item pendingItem = null;
    private static long lastRequestAt = 0L;
    private static long resyncUntil = 0L;

    DeleteItem(Code var1) {
    }

    public final void run() {
        if (running) {
            GameScr.chatPopup("Xóa item đang chạy");
            return;
        }

        running = true;

        try {
            Char me = Char.getMyChar();
            int sent = 0;

            if (!FormNsoChenSetting.isExactDelete()) {
                sent = runFastManual(me);
                GameScr.chatPopup(sent > 0 ? "Xóa nhanh: đã gửi " + sent + " món" : "Xóa nhanh: không có món cần xóa");
                return;
            }

            while (me != null && sent < MAX_MANUAL_DELETE) {
                if (!waitPending(me)) {
                    break;
                }

                if (sendNext(me, false) == 0) {
                    if (isCoolingDown()) {
                        sleepSmall();
                        continue;
                    }
                    break;
                }

                ++sent;
            }

            GameScr.chatPopup(sent > 0 ? "Xóa item: đã gửi " + sent + " món" : "Xóa item: không có món cần xóa");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            running = false;
        }
    }

    static int cleanBagAuto(Char me) {
        if (!FormNsoChenSetting.isExactDelete()) {
            return running || pendingOp != OP_NONE ? 0 : sendFastBatch(me, true);
        }

        if (running || !waitPendingNoBlock(me)) {
            return 0;
        }

        return sendNext(me, true);
    }

    static boolean isBusy() {
        if (!FormNsoChenSetting.isExactDelete()) {
            return running || pendingOp != OP_NONE || System.currentTimeMillis() - lastRequestAt < FAST_SORT_GUARD_DELAY;
        }

        waitPendingNoBlock(Char.getMyChar());
        long now = System.currentTimeMillis();
        return running || pendingOp != OP_NONE || now < resyncUntil || now - lastRequestAt < SORT_GUARD_DELAY;
    }

    private static int runFastManual(Char me) {
        int sent = 0;
        while (me != null && sent < MAX_MANUAL_DELETE) {
            int count = sendFastBatch(me, false);
            if (count <= 0) {
                break;
            }
            sent += count;
            sleepSmall();
        }

        return sent;
    }

    private static int sendFastBatch(Char me, boolean autoMode) {
        if (pendingOp != OP_NONE || me == null || me.arrItemBag == null || !isBagReady(me)) {
            return 0;
        }

        long now = System.currentTimeMillis();
        int max = autoMode ? FAST_AUTO_BATCH : FAST_MANUAL_BATCH;
        int sent = 0;
        for (int i = 0; i < me.arrItemBag.length && sent < max; ++i) {
            Item item = me.arrItemBag[i];
            if (!canSend(me, i, item) || now - item.u < FAST_REPEAT_GUARD) {
                continue;
            }

            if (Code.isItemDel(item)) {
                item.u = now;
                lastRequestAt = now;
                Service.getInstance().saleItem1(item.indexUI, item.quantity);
                ++sent;
            } else if (autoMode && Code.isThrowItem(item) && canSend(me, i, item)) {
                item.u = now;
                lastRequestAt = now;
                Service.getInstance().throwItem(item.indexUI);
                ++sent;
            }
        }

        return sent;
    }

    private static int sendNext(Char me, boolean autoMode) {
        long now = System.currentTimeMillis();
        if (pendingOp != OP_NONE || now < resyncUntil || now - lastRequestAt < SEND_GAP) {
            return 0;
        }

        if (me == null || me.arrItemBag == null || !isBagReady(me)) {
            return 0;
        }

        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (!canSend(me, i, item)) {
                continue;
            }

            if (Code.isItemDel(item)) {
                sendSell(item);
                return 1;
            }

            if (autoMode && Code.isThrowItem(item) && canSend(me, i, item)) {
                sendThrow(item);
                return 1;
            }
        }

        return 0;
    }

    private static boolean isCoolingDown() {
        long now = System.currentTimeMillis();
        return pendingOp == OP_NONE && (now < resyncUntil || now - lastRequestAt < SEND_GAP);
    }

    private static void sleepSmall() {
        try {
            Thread.sleep(POLL_DELAY);
        } catch (Exception e) {
        }
    }

    private static void sendSell(Item item) {
        markPending(OP_SELL, item);
        Service.getInstance().saleItem1(item.indexUI, item.quantity);
    }

    private static void sendThrow(Item item) {
        markPending(OP_THROW, item);
        Service.getInstance().throwItem(item.indexUI);
    }

    private static void markPending(int op, Item item) {
        pendingOp = op;
        pendingIndex = item.indexUI;
        pendingId = (short)item.template.id;
        pendingQuantity = item.quantity;
        pendingItem = item;
        pendingAt = System.currentTimeMillis();
        lastRequestAt = pendingAt;
        item.u = pendingAt;
    }

    private static boolean waitPending(Char me) {
        if (pendingOp == OP_NONE) {
            return System.currentTimeMillis() >= resyncUntil;
        }

        long end = System.currentTimeMillis() + REQUEST_TIMEOUT;
        while (pendingOp != OP_NONE && System.currentTimeMillis() < end) {
            if (isPendingDone(me)) {
                clearPending();
                return true;
            }

            try {
                Thread.sleep(POLL_DELAY);
            } catch (Exception e) {
                return false;
            }
        }

        if (pendingOp != OP_NONE) {
            triggerResync();
            return false;
        }

        return true;
    }

    private static boolean waitPendingNoBlock(Char me) {
        if (pendingOp == OP_NONE) {
            return System.currentTimeMillis() >= resyncUntil;
        }

        if (isPendingDone(me)) {
            clearPending();
            return System.currentTimeMillis() >= resyncUntil;
        }

        if (System.currentTimeMillis() - pendingAt > REQUEST_TIMEOUT) {
            triggerResync();
        }

        return false;
    }

    private static void triggerResync() {
        clearPending();
        lastRequestAt = System.currentTimeMillis();
        resyncUntil = lastRequestAt + RESYNC_COOLDOWN;
        Service.getInstance().requestItem(3);
        GameScr.chatPopup("Xóa item: mạng lag, đang sync lại hành trang");
    }

    private static boolean isPendingDone(Char me) {
        if (me == null || me.arrItemBag == null || pendingIndex < 0 || pendingIndex >= me.arrItemBag.length) {
            return false;
        }

        Item now = me.arrItemBag[pendingIndex];
        if (now == null) {
            return true;
        }

        if (now != pendingItem || now.template == null || now.template.id != pendingId) {
            return true;
        }

        return (pendingOp == OP_SELL || pendingOp == OP_THROW) && now.quantity < pendingQuantity;
    }

    private static void clearPending() {
        pendingOp = OP_NONE;
        pendingIndex = -1;
        pendingId = -1;
        pendingQuantity = 0;
        pendingAt = 0L;
        pendingItem = null;
    }

    private static boolean canSend(Char me, int index, Item item) {
        return item != null && item.template != null && item.typeUI == 3 && item.indexUI == index
                && index >= 0 && index < me.arrItemBag.length && me.arrItemBag[index] == item;
    }

    private static boolean isBagReady(Char me) {
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.indexUI != i) {
                return false;
            }
        }

        return true;
    }
}
