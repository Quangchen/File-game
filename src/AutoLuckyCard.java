public final class AutoLuckyCard implements Runnable {

    private static final String STORE_NAME = "AutoLuckyCardCfg";
    public static long a = 50L;
    public static int c = 50;
    public static int d;
    public static boolean keepStackable = true;
    public static String keepTypes = "26";
    public static boolean autoBuyTicket = true;
    public static int buyTicketCount = 5000;

    private static final int LUCKY_CARD_ID = 340;
    private static final int YEN_ITEM_ID = 12;
    private static final int LUCKY_CARD_MAP_ID = 72;
    private static final int TICKET_SHOP_ID = 14;
    private static final int RESULT_WAIT_TIMEOUT = 5000;
    private static final int BAG_RESULT_WAIT_TIMEOUT = 5000;
    private static final int DELETE_CONFIRM_TIMEOUT = 5000;
    private static final int MAP_WAIT_TIMEOUT = 20000;
    private static final int LEAVE_SPECIAL_MAP_TIMEOUT = 30000;
    private static final Object RESULT_LOCK = new Object();
    private static final AutoLuckyCardStatus AUTO_STATUS = new AutoLuckyCardStatus();

    private static boolean loaded = false;
    private static volatile boolean running = false;
    private static volatile Thread thread;
    private static int targetCount = 0;
    private static int flippedCount = 0;
    private static volatile int lastRewardTemplateId = -1;
    private static volatile int lastSignaledResultCount = 0;
    private static volatile int targetRewardId = -1;
    private static String status = "Tắt";

    private final int count;

    AutoLuckyCard(int count) {
        this.count = count;
    }

    public static void start() {
        start(0);
    }

    public static synchronized void start(int count) {
        startInternal(count, -1);
    }

    public static synchronized boolean startForTarget(int itemId, int count) {
        if (itemId <= 0) {
            return false;
        }
        return startInternal(count, itemId);
    }

    private static boolean startInternal(int count, int wantedItemId) {
        load();
        if (running) {
            GameScr.chatPopup("Auto lật hình đang chạy");
            return false;
        }
        if (thread != null) {
            GameScr.chatPopup("Auto l\u1eadt h\u00ecnh \u0111ang d\u1eebng, vui l\u00f2ng ch\u1edd");
            return false;
        }
        Char me = Char.getMyChar();
        if (!(GameCanvas.mScreen instanceof GameScr) || me == null || me.arrItemBag == null) {
            GameScr.chatPopup("Ch\u01b0a th\u1ec3 ch\u1ea1y auto l\u1eadt h\u00ecnh");
            return false;
        }
        if (AutoDapDo.isRunning()) {
            GameScr.chatPopup("Auto đập đồ đang lật hình, không chạy lật riêng");
            return false;
        }
        if (AutoRuocDen.isBusy()) {
            GameScr.chatPopup("Ch\u1edd auto r\u01b0\u1edbc \u0111\u00e8n xong r\u1ed3i l\u1eadt h\u00ecnh");
            return false;
        }
        if (count <= 0) {
            count = c;
        }
        if (count <= 0) {
            count = 50;
        }
        running = true;
        targetRewardId = wantedItemId;
        targetCount = count;
        flippedCount = 0;
        status = "Chuáº©n bá»‹";
        status = "Chu\u1ea9n b\u1ecb";
        setAutoStatus();
        try {
            thread = new Thread(new AutoLuckyCard(count));
            thread.start();
        } catch (Exception e) {
            running = false;
            thread = null;
            targetRewardId = -1;
            restoreAutoStatus();
            GameScr.chatPopup("KhÃ´ng thá»ƒ cháº¡y auto láº­t hÃ¬nh");
            return false;
        }
        return true;
    }

    public static void toggle() {
        if (running) {
            stop();
        } else {
            start();
        }
    }

    public static synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        synchronized (RESULT_LOCK) {
            RESULT_LOCK.notifyAll();
        }
        AutoDapDo.hideLuckyCardUiNow();
        status = "Đã dừng";
        GameScr.chatPopup("Dừng auto lật hình");
    }

    public static boolean isRunning() {
        return running;
    }

    public static String getAutoText() {
        if (!running) {
            return "";
        }
        return "Auto LH " + flippedCount + "/" + targetCount + " " + status;
    }

    public static String getStatusText() {
        return running ? getAutoText() : "Tắt";
    }

    public final void run() {
        if (this.count <= 0 || !running) {
            running = false;
            thread = null;
            restoreAutoStatus();
            targetRewardId = -1;
            return;
        }

        targetCount = this.count;
        flippedCount = 0;
        status = "Chuẩn bị";
        status = "Chu\u1ea9n b\u1ecb";
        boolean oldHideLuckyCardUi = AutoDapDo.isHidingLuckyCardUi();
        AutoDapDo.setHideLuckyCardUi(true);

        try {
            if (TileMap.mapID != LUCKY_CARD_MAP_ID && !goToLuckyCardMap()) {
                status = "Không đến được map 72";
                GameScr.chatPopup(status);
                return;
            }

            if (!waitProducerWindow()) {
                return;
            }
            if (TileMap.mapID != LUCKY_CARD_MAP_ID && !goToLuckyCardMap()) {
                status = "KhÃ´ng quay láº¡i Ä‘Æ°á»£c map 72";
                return;
            }

            if (Char.k(LUCKY_CARD_ID) <= 0 && !handleOutOfTicket()) {
                status = "Hết phiếu";
                GameScr.chatPopup("Hết phiếu lật hình 340");
                return;
            }

            openLuckyCardNpc();
            while (running && flippedCount < targetCount && !hasTargetReward()) {
                if (!waitProducerWindow()) {
                    break;
                }
                if (TileMap.mapID != LUCKY_CARD_MAP_ID) {
                    status = "Quay láº¡i map 72";
                    if (!goToLuckyCardMap()) {
                        status = "KhÃ´ng quay láº¡i Ä‘Æ°á»£c map 72";
                        break;
                    }
                    openLuckyCardNpc();
                }
                if (Char.k(LUCKY_CARD_ID) <= 0 && !handleOutOfTicket()) {
                    status = "Hết phiếu";
                    break;
                }
                if (Char.countNullSlot() <= 0) {
                    status = "Full hành trang";
                    break;
                }

                LuckyCardBagSnapshot beforeBag = snapshotBag();
                GameScr.indexSelect = 0;
                int beforeResult = requestLuckyCard();
                status = "Lật";
                waitLuckyCardResult(beforeResult);
                if (AutoDapDo.getLuckyCardResultCount() == beforeResult) {
                    status = "Không nhận kết quả";
                    break;
                }

                ++flippedCount;
                if (!cleanLuckyCardResult(beforeBag, lastRewardTemplateId)) {
                    status = "ChÆ°a Ä‘á»“ng bá»™ pháº§n thÆ°á»Ÿng";
                    break;
                }
                if (hasTargetReward()) {
                    status = "Da tim thay item " + targetRewardId;
                    break;
                }
                sleep(getDelayMs());
            }
        } finally {
            AutoDapDo.hideLuckyCardUiNow();
            AutoDapDo.setHideLuckyCardUi(oldHideLuckyCardUi);
            running = false;
            thread = null;
            status = flippedCount >= targetCount || hasTargetReward() ? "Xong" : status;
            restoreAutoStatus();
            targetRewardId = -1;
        }
    }

    private static boolean goToLuckyCardMap() {
        try {
            if (!running) {
                return false;
            }
            if (TileMap.mapID == LUCKY_CARD_MAP_ID) {
                return true;
            }

            clearCombatFocus();
            if (TileMap.direction(LUCKY_CARD_MAP_ID) && waitMap(LUCKY_CARD_MAP_ID, MAP_WAIT_TIMEOUT)) {
                return true;
            }

            if (!leaveCurrentMap(LEAVE_SPECIAL_MAP_TIMEOUT)) {
                return false;
            }
            TileMap.direction(LUCKY_CARD_MAP_ID);
            return waitMap(LUCKY_CARD_MAP_ID, MAP_WAIT_TIMEOUT);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean leaveCurrentMap(long timeout) {
        long start = System.currentTimeMillis();
        long lastReturnTown = 0L;
        long lastSuicide = 0L;
        Auto.goTruongIfNeeded();
        while (running && System.currentTimeMillis() - start < timeout) {
            clearCombatFocus();
            if (TileMap.mapID == LUCKY_CARD_MAP_ID || isTicketShopMap()) {
                return true;
            }

            Char me = Char.getMyChar();
            long now = System.currentTimeMillis();
            if (me != null && Auto.checkDead(me)) {
                if (now - lastReturnTown >= 1200L) {
                    lastReturnTown = now;
                    Service.getInstance().returnTownFromDead();
                }
            } else if (now - lastSuicide >= 6000L) {
                lastSuicide = now;
                Auto.tuSat();
            }
            sleep(300L);
        }
        return TileMap.mapID == LUCKY_CARD_MAP_ID || isTicketShopMap();
    }

    private static boolean waitMap(int mapId, long timeout) {
        long start = System.currentTimeMillis();
        while (running && TileMap.mapID != mapId && System.currentTimeMillis() - start < timeout) {
            sleep(200L);
        }
        return TileMap.mapID == mapId;
    }

    private static void clearCombatFocus() {
        try {
            Char me = Char.getMyChar();
            if (me != null) {
                me.mobFocus = null;
                me.charFocus = null;
                me.itemFocus = null;
            }
        } catch (Exception e) {
        }
    }

    private static boolean waitProducerWindow() {
        while (running && (AutoDoiLongDen.shouldPauseProducers() || AutoRuocDen.isBusy())) {
            status = "Chá» dá»n hÃ nh trang";
            sleep(100L);
        }
        if (running && Code.auto != AUTO_STATUS) {
            status = "D\u1eebng do auto kh\u00e1c \u0111ang ch\u1ea1y";
            running = false;
        }
        return running;
    }

    private static boolean handleOutOfTicket() {
        status = "Hết vé";
        if (!autoBuyTicket && targetRewardId <= 0) {
            GameScr.chatPopup("Hết vé lật hình 340");
            return false;
        }
        if (!tryBuyTicket()) {
            GameScr.chatPopup("Không mua được vé lật 340");
            return false;
        }
        if (!goToLuckyCardMap()) {
            status = "Không về được map 72";
            GameScr.chatPopup(status);
            return false;
        }
        openLuckyCardNpc();
        return Char.k(LUCKY_CARD_ID) > 0;
    }

    private static boolean tryBuyTicket() {
        try {
            int count = getBuyTicketCount();
            int before = Char.k(LUCKY_CARD_ID);
            status = "Mua vé x" + count;
            if (!ensureTicketShopMap()) {
                return false;
            }

            if (AutoBuyShop.buyNow(LUCKY_CARD_ID, TICKET_SHOP_ID, count)) {
                AutoDapDo.hideLuckyCardUiNow();
                return Char.k(LUCKY_CARD_ID) > before;
            }

            GameScr.getInstance().openUI(TICKET_SHOP_ID);
            sleep(700L);
            Item shopItem = findShopItemById(LUCKY_CARD_ID);
            if (shopItem == null) {
                Service.getInstance().requestItem(TICKET_SHOP_ID);
                sleep(700L);
                shopItem = findShopItemById(LUCKY_CARD_ID);
            }
            if (shopItem == null) {
                restoreGameMenu();
                return false;
            }

            Service.getInstance().buyItem1(shopItem.typeUI, shopItem.indexUI, count);
            sleep(1000L);
            Char me = Char.getMyChar();
            if (me != null && Char.k(LUCKY_CARD_ID) <= before) {
                Service.getInstance().viewInfo(me.charName);
                sleep(700L);
            }
            restoreGameMenu();
            AutoDapDo.hideLuckyCardUiNow();
            return Char.k(LUCKY_CARD_ID) > before;
        } catch (Exception e) {
            restoreGameMenu();
            return false;
        }
    }

    private static boolean ensureTicketShopMap() {
        try {
            if (isTicketShopMap()) {
                return true;
            }
            if (TileMap.mapID == LUCKY_CARD_MAP_ID) {
                TileMap.direction(1);
                if (waitTicketShopMap(20000L)) {
                    return true;
                }
            }

            Auto.goTruongIfNeeded();
            if (waitTicketShopMap(20000L)) {
                return true;
            }

            TileMap.direction(1);
            return waitTicketShopMap(20000L);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isTicketShopMap() {
        return TileMap.isLang(TileMap.mapID) || TileMap.isTruong(TileMap.mapID) && TileMap.mapID != LUCKY_CARD_MAP_ID;
    }

    private static boolean waitTicketShopMap(long timeout) {
        long start = System.currentTimeMillis();
        while (running && !isTicketShopMap() && System.currentTimeMillis() - start < timeout) {
            sleep(250L);
        }
        return isTicketShopMap();
    }

    private static void openLuckyCardNpc() {
        GameScr.PickNpc(30, 0, 0);
        sleep(50L);
        AutoDapDo.hideLuckyCardUiNow();
    }

    private static void waitLuckyCardResult(int beforeResult) {
        long end = System.currentTimeMillis() + RESULT_WAIT_TIMEOUT;
        synchronized (RESULT_LOCK) {
            while (running && lastSignaledResultCount <= beforeResult) {
                long remaining = end - System.currentTimeMillis();
                if (remaining <= 0L) {
                    break;
                }
                try {
                    RESULT_LOCK.wait(remaining);
                } catch (Exception e) {
                    break;
                }
            }
        }
        AutoDapDo.hideLuckyCardUiNow();
    }

    private static int requestLuckyCard() {
        synchronized (RESULT_LOCK) {
            lastRewardTemplateId = -1;
            int beforeResult = AutoDapDo.getLuckyCardResultCount();
            Service.getInstance().ah();
            return beforeResult;
        }
    }

    static void captureLuckyCardReward() {
        synchronized (RESULT_LOCK) {
            try {
                int index = GameScr.indexSelect;
                if (GameScr.arrItemSprin != null && index >= 0 && index < GameScr.arrItemSprin.length) {
                    lastRewardTemplateId = GameScr.arrItemSprin[index];
                }
            } catch (Exception e) {
                lastRewardTemplateId = -1;
            }
        }
    }

    static void signalLuckyCardResult(int resultCount) {
        synchronized (RESULT_LOCK) {
            lastSignaledResultCount = resultCount;
            RESULT_LOCK.notifyAll();
        }
    }

    static int getLastRewardTemplateId() {
        return lastRewardTemplateId;
    }

    private static LuckyCardBagSnapshot snapshotBag() {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return new LuckyCardBagSnapshot(0);
        }
        LuckyCardBagSnapshot snapshot = new LuckyCardBagSnapshot(me.arrItemBag.length);
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            snapshot.items[i] = item;
            snapshot.ids[i] = item == null || item.template == null ? -1 : item.template.id;
            snapshot.types[i] = item == null || item.template == null ? -1 : item.template.type;
            snapshot.quantities[i] = item == null ? 0 : item.quantity;
        }
        return snapshot;
    }

    private static boolean cleanLuckyCardResult(LuckyCardBagSnapshot before, int rewardTemplateId) {
        if (rewardTemplateId < 0) {
            return false;
        }
        if (rewardTemplateId == YEN_ITEM_ID) {
            return true;
        }
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < BAG_RESULT_WAIT_TIMEOUT) {
            int result = cleanLuckyCardResultNow(before, rewardTemplateId);
            if (result > 0) {
                return true;
            }
            if (result < 0) {
                return false;
            }
            sleep(40L);
        }
        return false;
    }

    private static int cleanLuckyCardResultNow(LuckyCardBagSnapshot before, int rewardTemplateId) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return 0;
        }

        boolean found = hasRewardIncrease(before, rewardTemplateId);
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (!isLuckyCardNewItem(before, i, item, rewardTemplateId)) {
                continue;
            }

            found = true;
            if (shouldKeepItem(item)) {
                continue;
            }

            int quantity = getNewQuantity(before, i, item);
            if (quantity > 0) {
                if (!deleteRewardAndWait(i, item, quantity)) {
                    return -1;
                }
            }
        }
        return found ? 1 : 0;
    }

    private static boolean isLuckyCardNewItem(LuckyCardBagSnapshot before, int index, Item item, int rewardTemplateId) {
        if (item == null || item.template == null) {
            return false;
        }
        if (rewardTemplateId >= 0 && item.template.id != rewardTemplateId) {
            return false;
        }
        if (item.template.id == LUCKY_CARD_ID) {
            return false;
        }
        if (before == null || index < 0 || index >= before.items.length) {
            return true;
        }
        if (sameSnapshotItem(before, index, item)) {
            return item.quantity > before.quantities[index];
        }
        if (before.ids[index] >= 0 || containsReference(before, item)) {
            return false;
        }

        int oldTotal = getSnapshotQuantity(before, item.template.id, item.template.type);
        int newTotal = getBagQuantity(item.template.id, item.template.type);
        if (newTotal <= oldTotal) {
            return false;
        }
        return true;
    }

    private static int getNewQuantity(LuckyCardBagSnapshot before, int index, Item item) {
        if (item == null) {
            return 0;
        }
        int quantity = item.quantity > 0 ? item.quantity : 1;
        if (sameSnapshotItem(before, index, item)) {
            quantity -= before.quantities[index];
        } else if (before != null && index >= 0 && index < before.items.length && before.ids[index] < 0
                && item.template != null) {
            int delta = getBagQuantity(item.template.id, item.template.type)
                    - getSnapshotQuantity(before, item.template.id, item.template.type);
            if (delta < quantity) {
                quantity = delta;
            }
        }
        return quantity > 0 ? quantity : 0;
    }

    private static boolean sameSnapshotItem(LuckyCardBagSnapshot before, int index, Item item) {
        return before != null && item != null && item.template != null && index >= 0 && index < before.items.length
                && before.ids[index] == item.template.id && before.types[index] == item.template.type;
    }

    private static boolean containsReference(LuckyCardBagSnapshot before, Item item) {
        if (before == null || item == null) {
            return false;
        }
        for (int i = 0; i < before.items.length; ++i) {
            if (before.items[i] == item) {
                return true;
            }
        }
        return false;
    }

    private static int getSnapshotQuantity(LuckyCardBagSnapshot before, int id, int type) {
        if (before == null) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < before.ids.length; ++i) {
            if (before.ids[i] == id && before.types[i] == type) {
                total += before.quantities[i] > 0 ? before.quantities[i] : 1;
            }
        }
        return total;
    }

    private static int getBagQuantity(int id, int type) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template != null && item.template.id == id && item.template.type == type) {
                total += item.quantity > 0 ? item.quantity : 1;
            }
        }
        return total;
    }

    private static boolean hasRewardIncrease(LuckyCardBagSnapshot before, int rewardTemplateId) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return false;
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item == null || item.template == null || item.template.id == LUCKY_CARD_ID) {
                continue;
            }
            if (rewardTemplateId >= 0 && item.template.id != rewardTemplateId) {
                continue;
            }
            if (getBagQuantity(item.template.id, item.template.type)
                    > getSnapshotQuantity(before, item.template.id, item.template.type)) {
                return true;
            }
        }
        return false;
    }

    private static boolean deleteRewardAndWait(int index, Item expected, int quantity) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null || index < 0 || index >= me.arrItemBag.length || expected == null
                || expected.template == null || expected.indexUI != index || me.arrItemBag[index] != expected) {
            return false;
        }

        int id = expected.template.id;
        int type = expected.template.type;
        int beforeQuantity = expected.quantity > 0 ? expected.quantity : 1;
        int deleteQuantity = quantity > beforeQuantity ? beforeQuantity : quantity;
        if (deleteQuantity <= 0) {
            return true;
        }

        Service.getInstance().saleItem1(index, deleteQuantity);
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < DELETE_CONFIRM_TIMEOUT) {
            Item current = me.arrItemBag[index];
            if (current == null || current.template == null || current.template.id != id || current.template.type != type) {
                return true;
            }
            int currentQuantity = current.quantity > 0 ? current.quantity : 1;
            if (currentQuantity <= beforeQuantity - deleteQuantity) {
                return true;
            }
            sleep(40L);
        }
        return false;
    }

    private static boolean shouldKeepItem(Item item) {
        if (item == null || item.template == null) {
            return true;
        }
        if (item.template.id == LUCKY_CARD_ID) {
            return true;
        }
        if (item.template.id == targetRewardId) {
            return true;
        }
        if (AutoDapDo.shouldProtectDeleteItem(item)) {
            return true;
        }
        if (isKeepType(item.template.type)) {
            return true;
        }
        return keepStackable && (item.template.isUpToUp || item.quantity > 1);
    }

    static boolean isTargetingItem(int itemId) {
        return running && targetRewardId == itemId;
    }

    private static boolean hasTargetReward() {
        int wanted = targetRewardId;
        if (wanted <= 0) {
            return false;
        }
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return false;
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template != null && item.template.id == wanted) {
                return true;
            }
        }
        return false;
    }

    private static boolean isKeepType(int type) {
        String text = keepTypes == null ? "" : keepTypes.trim();
        if (text.length() == 0) {
            return false;
        }
        text = normalizeSeparators(text);
        String[] parts = Code.splitString(text, ",");
        for (int i = 0; i < parts.length; ++i) {
            try {
                if (Integer.parseInt(parts[i].trim()) == type) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    private static String normalizeSeparators(String text) {
        String result = text;
        result = NinjaUtil.replace(result, ";", ",");
        result = NinjaUtil.replace(result, " ", ",");
        while (result.indexOf(",,") >= 0) {
            result = NinjaUtil.replace(result, ",,", ",");
        }
        return result;
    }

    private static int getDelayMs() {
        return a < 1L ? 1 : (int)a;
    }

    public static int getBuyTicketCount() {
        if (buyTicketCount < 1) {
            return 1;
        }
        return buyTicketCount > 30000 ? 30000 : buyTicketCount;
    }

    private static Item findShopItemById(int id) {
        Item item;
        if ((item = findInArray(GameScr.arrItemStore, id)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemStack, id)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemStackLock, id)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemGrocery, id)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemGroceryLock, id)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemElites, id)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemClanShop, id)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemBook, id)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemFashion, id)) != null) {
            return item;
        }
        return null;
    }

    private static Item findInArray(Item[] arr, int id) {
        if (arr == null) {
            return null;
        }
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != null && arr[i].template != null && arr[i].template.id == id) {
                return arr[i];
            }
        }
        return null;
    }

    private static void restoreGameMenu() {
        try {
            GameScr gameScr = GameScr.getInstance();
            if (gameScr != null) {
                gameScr.ef = null;
                gameScr.resetButton();
            }
            GameCanvas.setMaxTextLenght();
        } catch (Exception e) {
        }
    }

    private static void setAutoStatus() {
        try {
            if (Code.auto == AUTO_STATUS) {
                Code.auto = AUTO_STATUS.instance;
                AUTO_STATUS.instance = null;
            }
            AUTO_STATUS.a();
            Code.setAuto(AUTO_STATUS);
        } catch (Exception e) {
        }
    }

    static void restoreAutoStatus() {
        try {
            if (Code.auto == AUTO_STATUS) {
                Code.auto = AUTO_STATUS.instance;
                AUTO_STATUS.instance = null;
            }
        } catch (Exception e) {
        }
    }

    public static void save() {
        try {
            java.io.ByteArrayOutputStream byteout = new java.io.ByteArrayOutputStream();
            java.io.DataOutputStream out = new java.io.DataOutputStream(byteout);
            out.writeInt(c);
            out.writeInt((int)a);
            out.writeBoolean(keepStackable);
            out.writeUTF(keepTypes == null ? "" : keepTypes);
            out.writeBoolean(autoBuyTicket);
            out.writeInt(getBuyTicketCount());
            out.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            loaded = true;
        } catch (Exception e) {
        }
    }

    public static void load() {
        if (loaded) {
            return;
        }
        try {
            byte[] data = RMS.getRecord(STORE_NAME);
            if (data == null) {
                loaded = true;
                save();
                return;
            }
            java.io.DataInputStream in = new java.io.DataInputStream(new java.io.ByteArrayInputStream(data));
            c = in.readInt();
            a = (long)in.readInt();
            keepStackable = in.readBoolean();
            keepTypes = in.readUTF();
            if (in.available() > 0) {
                autoBuyTicket = in.readBoolean();
            }
            if (in.available() >= 4) {
                buyTicketCount = in.readInt();
            }
        } catch (Exception e) {
        }
        if (c <= 0) {
            c = 50;
        }
        if (a < 1L) {
            a = 1L;
        }
        if (keepTypes == null) {
            keepTypes = "";
        }
        buyTicketCount = getBuyTicketCount();
        loaded = true;
    }

    private static void sleep(long time) {
        try {
            if (time > 0L) {
                Thread.sleep(time);
            }
        } catch (Exception e) {
        }
    }

    private static final class LuckyCardBagSnapshot {
        Item[] items;
        int[] ids;
        int[] types;
        int[] quantities;

        LuckyCardBagSnapshot(int size) {
            this.items = new Item[size];
            this.ids = new int[size];
            this.types = new int[size];
            this.quantities = new int[size];
        }
    }
}

final class AutoLuckyCardStatus extends Auto {

    protected void run() {
        if (!AutoLuckyCard.isRunning()) {
            AutoLuckyCard.restoreAutoStatus();
        }
    }

    public String toString() {
        return AutoLuckyCard.getAutoText();
    }
}
