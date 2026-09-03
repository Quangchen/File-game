public final class AutoDapDo implements Runnable {

    private static final int[] ADORN_TYPES = new int[]{3, 5, 7, 9};
    private static final int[] CLOTHE_TYPES = new int[]{0, 2, 4, 6, 8};
    private static final int MIN_EMPTY_SLOT_AFTER_FLIP = 6;
    private static final int LUCKY_CARD_ID = 340;
    private static final int LUCKY_CARD_BUY_COUNT = 5000;
    private static final int LUCKY_CARD_WAIT_TIMEOUT = 900;
    private static final int LUCKY_CARD_WAIT_STEP = 20;

    private static final AutoDapDoStatus AUTO_STATUS = new AutoDapDoStatus();
    private static boolean running = false;
    private static boolean hideLuckyCardUi = false;
    private static int luckyCardResultCount = 0;
    private static Thread thread;
    private static boolean tempConfig = false;
    private static int oldMode = FormAutoDapDo.MODE_BAG_INDEX0;
    private static int oldModeMask = FormAutoDapDo.MASK_BAG_INDEX0;
    private static int oldTargetUpgrade = 16;
    private static boolean oldUseXuWhenLackYen = false;
    private static boolean oldReEquipWhenDone = true;
    private static boolean oldAutoFlip = true;
    private static boolean oldUseProtectUnder14 = true;
    private static boolean oldAutoBuyProtect475 = true;
    private static int activeItemId = -1;
    private static int activeTemplateId = -1;
    private static int activeType = -1;
    private static int activeSys = -999;
    private static int activeUpgrade = -1;
    private static int activeBodyType = -1;
    private static Item activeItemRef;
    private static String currentItemName = "";
    private static int currentItemUpgrade = -1;
    private static int currentTargetUpgrade = -1;

    public static void start() {
        if (running) {
            GameScr.chatPopup("Auto đập đồ đang chạy");
            return;
        }

        running = true;
        setAutoStatus();
        thread = new Thread(new AutoDapDo());
        thread.start();
        GameScr.chatPopup(getAutoText());
    }

    public static void startForAutoUp(int modeMask, int targetUpgrade, boolean useXuWhenLackYen) {
        startForAutoUp(modeMask, targetUpgrade, useXuWhenLackYen, true);
    }

    public static void startForAutoUp(int modeMask, int targetUpgrade, boolean useXuWhenLackYen, boolean useProtect) {
        if (running) {
            return;
        }
        if (modeMask == 0) {
            return;
        }
        if (targetUpgrade < 1) {
            targetUpgrade = 1;
        }
        if (targetUpgrade > 16) {
            targetUpgrade = 16;
        }

        oldMode = FormAutoDapDo.Mode;
        oldModeMask = FormAutoDapDo.ModeMask;
        oldTargetUpgrade = FormAutoDapDo.TargetUpgrade;
        oldUseXuWhenLackYen = FormAutoDapDo.UseXuWhenLackYen;
        oldReEquipWhenDone = FormAutoDapDo.ReEquipWhenDone;
        oldAutoFlip = FormAutoDapDo.AutoFlip;
        oldUseProtectUnder14 = FormAutoDapDo.UseProtectUnder14;
        oldAutoBuyProtect475 = FormAutoDapDo.AutoBuyProtect475;
        tempConfig = true;

        FormAutoDapDo.ModeMask = modeMask;
        FormAutoDapDo.Mode = getPrimaryModeFromMask(modeMask);
        FormAutoDapDo.TargetUpgrade = targetUpgrade;
        FormAutoDapDo.UseXuWhenLackYen = useXuWhenLackYen;
        FormAutoDapDo.ReEquipWhenDone = true;
        FormAutoDapDo.AutoFlip = false;
        FormAutoDapDo.UseProtectUnder14 = useProtect;
        FormAutoDapDo.AutoBuyProtect475 = useProtect;
        start();
    }

    public static void stop() {
        running = false;
        restoreAutoStatus();
        GameScr.chatPopup("Dừng auto đập đồ");
    }

    public static void toggle() {
        if (running) {
            stop();
        } else {
            start();
        }
    }

    public static String getStatusText() {
        return running ? "Đang chạy" : "Đang tắt";
    }

    public static String getAutoText() {
        String target = currentTargetUpgrade > 0 ? String.valueOf(currentTargetUpgrade) : String.valueOf(FormAutoDapDo.TargetUpgrade);
        if (currentItemName != null && currentItemName.length() > 0) {
            return "Đập " + shortName(currentItemName) + " +" + currentItemUpgrade + "->+" + target;
        }
        return "Đập " + FormAutoDapDo.getModeName() + " -> +" + target;
    }

    static boolean isRunning() {
        return running;
    }

    static boolean isHidingLuckyCardUi() {
        return hideLuckyCardUi;
    }

    static void setHideLuckyCardUi(boolean hide) {
        hideLuckyCardUi = hide;
        if (hide) {
            hideLuckyCardUiNow();
        }
    }

    static int getLuckyCardResultCount() {
        return luckyCardResultCount;
    }

    static void hideLuckyCardUiNow() {
        try {
            GameScr.hideLuckyCardUi();
        } catch (Exception e) {
        }
    }

    static void onLuckyCardResult() {
        ++luckyCardResultCount;
    }

    public void run() {
        try {
            while (running) {
                Char me = Char.getMyChar();
                if (me == null || me.arrItemBag == null || me.arrItemBody == null) {
                    sleep(1000L);
                    continue;
                }

                Item item = resolveTargetItem(me);
                if (item == null) {
                    finish(activeTemplateId >= 0 ? "Không xác định đúng đồ đang đập" : "Không còn đồ cần đập");
                    break;
                }

                int target = getTargetUpgrade(item);
                updateCurrentItemText(item, target);
                if (item.upgrade >= target) {
                    reEquipActiveIfNeeded(me);
                    if (FormAutoDapDo.isBagIndex0Mode()) {
                        finish("Đã đạt cấp yêu cầu");
                        break;
                    }
                    clearActive();
                    sleep(500L);
                    continue;
                }

                if (!isUpgradeable(item)) {
                    finish("Đồ không thể đập");
                    break;
                }

                if (item.typeUI == 5) {
                    if (!detachBodyItem(me, item)) {
                        break;
                    }
                    item = waitActiveInBag();
                    if (item == null) {
                        finish("Không tìm thấy đồ sau khi tháo");
                        break;
                    }
                }

                if (!upgradeOnce(me, item)) {
                    break;
                }

                sleep(FormAutoDapDo.DelayMs);
            }
        } catch (Exception e) {
            GameScr.chatPopup("Lỗi auto đập đồ");
        } finally {
            running = false;
            hideLuckyCardUi = false;
            reEquipActiveIfNeeded(Char.getMyChar());
            GameScr.itemUpGrade = null;
            GameScr.arrItemUpGrade = null;
            restoreGameMenu();
            clearCurrentItemText();
            restoreAutoStatus();
            restoreTempConfig();
        }
    }

    private static int getPrimaryModeFromMask(int mask) {
        if ((mask & FormAutoDapDo.MASK_WEAPON) != 0) {
            return FormAutoDapDo.MODE_WEAPON;
        }
        if ((mask & FormAutoDapDo.MASK_ADORN) != 0) {
            return FormAutoDapDo.MODE_ADORN;
        }
        if ((mask & FormAutoDapDo.MASK_CLOTHE) != 0) {
            return FormAutoDapDo.MODE_CLOTHE;
        }
        return FormAutoDapDo.MODE_BAG_INDEX0;
    }

    private static void restoreTempConfig() {
        if (!tempConfig) {
            return;
        }
        FormAutoDapDo.Mode = oldMode;
        FormAutoDapDo.ModeMask = oldModeMask;
        FormAutoDapDo.TargetUpgrade = oldTargetUpgrade;
        FormAutoDapDo.UseXuWhenLackYen = oldUseXuWhenLackYen;
        FormAutoDapDo.ReEquipWhenDone = oldReEquipWhenDone;
        FormAutoDapDo.AutoFlip = oldAutoFlip;
        FormAutoDapDo.UseProtectUnder14 = oldUseProtectUnder14;
        FormAutoDapDo.AutoBuyProtect475 = oldAutoBuyProtect475;
        tempConfig = false;
    }

    private static boolean detachBodyItem(Char me, Item item) {
        if (Char.countNullSlot() <= 0) {
            finish("Cần trống 1 ô hành trang để tháo đồ");
            return false;
        }

        lockTargetItem(item);
        activeBodyType = item.indexUI;
        Service.getInstance().itemBodyToBag(item.indexUI);
        return true;
    }

    private static boolean upgradeOnce(Char me, Item item) {
        item = resolveLockedBagItem(me, item);
        if (item == null) {
            finish("Không xác nhận đúng đồ trước khi đập");
            return false;
        }

        if (!checkMoney(me, item)) {
            finish("Không đủ yên/xu/lượng để đập");
            return false;
        }

        Item[] mats = new Item[18];
        int matCount = 0;
        int protectId = getProtectId(item.upgrade);
        if (protectId > 0) {
            Item protect = findBagItemById(me, protectId);
            if (protect == null && FormAutoDapDo.AutoBuyProtect475) {
                tryBuyShopItem(me, protectId, 1);
                protect = findBagItemById(me, protectId);
            }
            if (protect == null) {
                finish("Thieu bao hiem " + protectId);
                return false;
            }
            mats[matCount++] = protect;
        }

        int crystalCount = selectCrystals(me, item, mats, matCount);
        if (crystalCount == 0) {
            if (tryFlipCards(me)) {
                return true;
            }
            finish("Thiếu đá nâng cấp");
            return false;
        }

        GameScr.itemUpGrade = item;
        GameScr.arrItemUpGrade = mats;
        removeMaterialsFromBag(me, mats);
        Service.getInstance().upgradeItem(item, mats, FormAutoDapDo.Careful);

        if (!waitUpgradeResult(mats)) {
            restoreMaterialsToBag(me, mats);
            GameScr.arrItemUpGrade = null;
            finish("Chưa nhận kết quả đập đồ");
            return false;
        }

        GameScr.arrItemUpGrade = null;
        refreshActiveIdentity(item);
        return true;
    }

    private static Item resolveTargetItem(Char me) {
        Item active = findActiveInBag(me);
        if (active != null) {
            return active;
        }
        if (activeTemplateId >= 0) {
            return null;
        }

        if (FormAutoDapDo.isBagIndex0Mode()) {
            Item item = me.arrItemBag.length > 0 ? me.arrItemBag[0] : null;
            lockTargetItem(item);
            return item;
        }

        Item body = findBestBodyItem(me);
        if (body != null) {
            lockTargetItem(body);
            return body;
        }

        return null;
    }

    private static Item findBestBodyItem(Char me) {
        int[] types = getTypesForMode();
        Item best = null;
        for (int i = 0; i < types.length; ++i) {
            int type = types[i];
            if (type >= 0 && type < me.arrItemBody.length) {
                Item item = me.arrItemBody[type];
                if (isEligible(item) && (best == null || item.upgrade < best.upgrade)) {
                    best = item;
                }
            }
        }
        return best;
    }

    private static Item findBestBagItem(Char me) {
        Item best = null;
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (isEligible(item) && (best == null || item.upgrade < best.upgrade)) {
                best = item;
            }
        }
        return best;
    }

    private static Item resolveLockedBagItem(Char me, Item fallback) {
        if (me == null || me.arrItemBag == null) {
            return null;
        }
        if (fallback != null && fallback.indexUI >= 0 && fallback.indexUI < me.arrItemBag.length
                && me.arrItemBag[fallback.indexUI] == fallback && matchesActiveIdentity(fallback)) {
            return fallback;
        }
        return findActiveInBag(me);
    }

    private static Item findActiveInBag(Char me) {
        if (activeTemplateId < 0 || me == null || me.arrItemBag == null) {
            return null;
        }
        if (isActiveBodyStillEquipped(me)) {
            return null;
        }

        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item == activeItemRef && matchesActiveIdentity(item)) {
                return item;
            }
        }

        Item found = null;
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (matchesActiveIdentity(item)) {
                if (found != null) {
                    return null;
                }
                found = item;
            }
        }
        return found;
    }

    private static boolean isActiveBodyStillEquipped(Char me) {
        if (activeBodyType < 0 || me == null || me.arrItemBody == null || activeBodyType >= me.arrItemBody.length) {
            return false;
        }
        return matchesActiveIdentity(me.arrItemBody[activeBodyType]);
    }

    private static void lockTargetItem(Item item) {
        if (item == null || item.template == null) {
            return;
        }
        activeItemId = item.itemId;
        activeTemplateId = item.template.id;
        activeType = item.template.type;
        activeSys = item.sys;
        activeUpgrade = item.upgrade;
        activeItemRef = item;
    }

    private static void refreshActiveIdentity(Item item) {
        if (item == null || item.template == null || activeTemplateId < 0) {
            return;
        }
        activeItemId = item.itemId;
        activeTemplateId = item.template.id;
        activeType = item.template.type;
        activeSys = item.sys;
        activeUpgrade = item.upgrade;
        activeItemRef = item;
    }

    private static boolean matchesActiveIdentity(Item item) {
        if (item == null || item.template == null || activeTemplateId < 0) {
            return false;
        }
        if (activeItemId > 0 && item.itemId != activeItemId) {
            return false;
        }
        return item.template.id == activeTemplateId && item.template.type == activeType
                && item.sys == activeSys && item.upgrade == activeUpgrade;
    }

    private static Item waitActiveInBag() {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 7000L) {
            Char me = Char.getMyChar();
            Item item = findActiveInBag(me);
            if (item != null) {
                return item;
            }
            sleep(150L);
        }
        return null;
    }

    private static boolean isEligible(Item item) {
        return item != null && isTargetType(item) && isUpgradeable(item) && item.upgrade < getTargetUpgrade(item);
    }

    private static boolean isUpgradeable(Item item) {
        return item != null && item.isTypeBody() && item.template.type < 10 && item.template.level >= 10 && !item.isUpMax();
    }

    private static boolean isTargetType(Item item) {
        if (item == null) {
            return false;
        }
        if (FormAutoDapDo.isBagIndex0Mode()) {
            return true;
        }
        if (FormAutoDapDo.isWeaponMode() && item.isTypeWeapon()) {
            return true;
        }
        if (FormAutoDapDo.isAdornMode() && item.isTypeAdorn()) {
            return true;
        }
        if (FormAutoDapDo.isClotheMode() && item.isTypeClothe()) {
            return true;
        }
        return false;
    }

    private static int[] getTypesForMode() {
        return FormAutoDapDo.getSelectedBodyTypes();
    }

    private static int getTargetUpgrade(Item item) {
        int max = item.q();
        int target = FormAutoDapDo.TargetUpgrade;
        return target > max ? max : target;
    }

    private static int getProtectId(int upgrade) {
        if (!FormAutoDapDo.UseProtectUnder14) {
            return -1;
        }

        if (upgrade == 7) {
            return 242;
        }
        if (upgrade == 10 || upgrade == 11) {
            return 284;
        }
        if (upgrade == 13) {
            return 285;
        }
        if (upgrade == 15) {
            return 475;
        }
        return -1;
    }

    private static Item findBagItemById(Char me, int id) {
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template.id == id && item.quantity == 1) {
                return item;
            }
        }
        return null;
    }

    private static int selectCrystals(Char me, Item item, Item[] mats, int start) {
        int required = getRequiredCrystalValue(item);
        int total = 0;
        int count = 0;
        int index = start;
        boolean[] used = new boolean[me.arrItemBag.length];

        while (total < required && index < mats.length) {
            int crystalIndex = chooseCrystalIndex(me, used, required - total, mats.length - index);
            if (crystalIndex < 0) {
                break;
            }

            Item crystal = me.arrItemBag[crystalIndex];
            used[crystalIndex] = true;
            mats[index++] = crystal;
            total += GameScr.crystals[crystal.template.id];
            ++count;
        }

        if (total < required) {
            for (int i = start; i < mats.length; ++i) {
                mats[i] = null;
            }
            return 0;
        }

        return count;
    }

    private static int chooseCrystalIndex(Char me, boolean[] used, int remaining, int slotsLeft) {
        if (GameScr.crystals == null || slotsLeft <= 0) {
            return -1;
        }

        int averageNeed = ceilDiv(remaining, slotsLeft);
        int bestUnder = -1;
        int bestUnderValue = -1;
        int bestOver = -1;
        int bestOverValue = 2147483647;

        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item crystal = me.arrItemBag[i];
            if (used[i] || !isValidCrystal(crystal)) {
                continue;
            }

            int value = GameScr.crystals[crystal.template.id];
            if (value <= remaining) {
                if (value > bestUnderValue) {
                    bestUnder = i;
                    bestUnderValue = value;
                }
            } else if (value < bestOverValue) {
                bestOver = i;
                bestOverValue = value;
            }
        }

        if (bestUnder >= 0 && (bestUnderValue >= averageNeed || bestOver < 0)) {
            return bestUnder;
        }
        if (bestOver >= 0) {
            return bestOver;
        }
        return bestUnder;
    }

    private static boolean isValidCrystal(Item item) {
        return item != null && item.template.type == 26 && item.quantity == 1 && item.template.id >= 0
                && GameScr.crystals != null && item.template.id < GameScr.crystals.length;
    }

    private static int getRequiredCrystalValue(Item item) {
        int percent = getServerMaxPercent(item.upgrade);

        if (item.isTypeWeapon()) {
            return ceilDiv(GameScr.upWeapon[item.upgrade] * percent, 95);
        }
        if (item.isTypeAdorn()) {
            return ceilDiv(GameScr.upAdorn[item.upgrade] * percent, 100);
        }
        return ceilDiv(GameScr.upClothe[item.upgrade] * percent, 100);
    }

    private static int getServerMaxPercent(int upgrade) {
        int maxPercent = GameScr.maxPercents[upgrade];
        if (upgrade >= 14) {
            --maxPercent;
        }
        return maxPercent < 1 ? 1 : maxPercent;
    }

    private static boolean checkMoney(Char me, Item item) {
        int coin;
        if (item.isTypeWeapon()) {
            coin = GameScr.coinUpWeapons[item.upgrade];
        } else if (item.isTypeAdorn()) {
            coin = GameScr.coinUpAdorns[item.upgrade];
        } else {
            coin = GameScr.coinUpClothes[item.upgrade];
        }

        if (FormAutoDapDo.UseXuWhenLackYen) {
            if (coin > me.xu + me.yen) {
                return false;
            }
        } else if (coin > me.yen) {
            return false;
        }

        return !FormAutoDapDo.Careful || GameScr.goldUps[item.upgrade] <= me.luong;
    }

    private static boolean tryFlipCards(Char me) {
        if (!FormAutoDapDo.AutoFlip) {
            return false;
        }
        if (Char.countNullSlot() <= MIN_EMPTY_SLOT_AFTER_FLIP) {
            GameScr.chatPopup("Còn 6 ô trống, dừng lật hình để đập");
            return false;
        }
        if (TileMap.mapID != 72) {
            if (!goToLuckyCardMap()) {
                GameScr.chatPopup("Không đến được map 72");
                return false;
            }
        }

        boolean flipped = false;
        boolean oldHideLuckyCardUi = hideLuckyCardUi;
        hideLuckyCardUi = true;
        try {
            openLuckyCardNpc();
            while (running && Char.countNullSlot() > MIN_EMPTY_SLOT_AFTER_FLIP) {
                if (Char.k(LUCKY_CARD_ID) <= 0) {
                    if (!tryBuyShopItem(me, LUCKY_CARD_ID, LUCKY_CARD_BUY_COUNT)) {
                        GameScr.chatPopup("Hết phiếu lật hình");
                        break;
                    }
                    openLuckyCardNpc();
                }

                GameScr.indexSelect = 0;
                int beforeResult = luckyCardResultCount;
                Service.getInstance().ah();
                flipped = true;
                waitLuckyCardResult(beforeResult);
            }

            if (flipped) {
                sleep(120L);
            }
            return flipped;
        } finally {
            hideLuckyCardUiNow();
            hideLuckyCardUi = oldHideLuckyCardUi;
        }
    }

    private static boolean goToLuckyCardMap() {
        try {
            GameScr.chatPopup("Auto đập đồ: đi map 72 lật hình");
            if (!TileMap.direction(72)) {
                return false;
            }
            long start = System.currentTimeMillis();
            while (running && TileMap.mapID != 72 && System.currentTimeMillis() - start < 15000L) {
                sleep(200L);
            }
            return TileMap.mapID == 72;
        } catch (Exception e) {
            return false;
        }
    }

    private static void openLuckyCardNpc() {
        GameScr.PickNpc(30, 0, 0);
        sleep(50L);
        if (isHidingLuckyCardUi()) {
            hideLuckyCardUiNow();
        }
    }

    private static void waitLuckyCardResult(int beforeResult) {
        long start = System.currentTimeMillis();
        while (running && luckyCardResultCount == beforeResult && System.currentTimeMillis() - start < LUCKY_CARD_WAIT_TIMEOUT) {
            sleep((long) LUCKY_CARD_WAIT_STEP);
        }
        if (isHidingLuckyCardUi()) {
            hideLuckyCardUiNow();
        }
    }

    static void waitLuckyCardResultSilent(int beforeResult) {
        long start = System.currentTimeMillis();
        while (luckyCardResultCount == beforeResult && System.currentTimeMillis() - start < LUCKY_CARD_WAIT_TIMEOUT) {
            sleep((long) LUCKY_CARD_WAIT_STEP);
        }
        if (isHidingLuckyCardUi()) {
            hideLuckyCardUiNow();
        }
    }

    private static boolean tryBuyShopItem(Char me, int itemId, int quantity) {
        int before = Char.k(itemId);
        GameScr.getInstance().openUI(14);
        sleep(700L);
        Item shopItem = findShopItemById(itemId);
        if (shopItem == null) {
            Service.getInstance().requestItem(14);
            sleep(700L);
            shopItem = findShopItemById(itemId);
        }

        if (shopItem != null) {
            Service.getInstance().buyItem1(shopItem.typeUI, shopItem.indexUI, quantity);
            sleep(1000L);
            if (Char.k(itemId) <= before) {
                Service.getInstance().viewInfo(me.charName);
                sleep(700L);
            }
        }
        restoreGameMenu();
        return Char.k(itemId) > before;
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
            if (arr[i] != null && arr[i].template.id == id) {
                return arr[i];
            }
        }
        return null;
    }

    private static void removeMaterialsFromBag(Char me, Item[] mats) {
        for (int i = 0; i < mats.length; ++i) {
            Item item = mats[i];
            if (item != null && item.indexUI >= 0 && item.indexUI < me.arrItemBag.length) {
                me.arrItemBag[item.indexUI] = null;
            }
        }
    }

    private static void restoreMaterialsToBag(Char me, Item[] mats) {
        if (me == null || me.arrItemBag == null) {
            return;
        }
        for (int i = 0; i < mats.length; ++i) {
            Item item = mats[i];
            if (item != null && item.indexUI >= 0 && item.indexUI < me.arrItemBag.length && me.arrItemBag[item.indexUI] == null) {
                me.arrItemBag[item.indexUI] = item;
            }
        }
    }

    private static void restoreGameMenu() {
        try {
            GameScr.arrItemSprin = null;
            GameScr.itemUpGrade = null;
            GameScr.arrItemUpGrade = null;
            GameScr gameScr = GameScr.getInstance();
            if (gameScr != null) {
                gameScr.ef = null;
                gameScr.resetButton();
            }
            GameCanvas.setMaxTextLenght();
        } catch (Exception e) {
        }
    }

    private static boolean waitUpgradeResult(Item[] mats) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 6000L) {
            if (isAllNull(mats)) {
                return true;
            }
            sleep(120L);
        }
        return false;
    }

    private static boolean isAllNull(Item[] arr) {
        if (arr == null) {
            return true;
        }
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != null) {
                return false;
            }
        }
        return true;
    }

    private static void reEquipActiveIfNeeded(Char me) {
        if (!FormAutoDapDo.ReEquipWhenDone || activeTemplateId < 0 || me == null || me.arrItemBag == null) {
            return;
        }
        Item item = findActiveInBag(me);
        if (item != null && activeBodyType >= 0 && item.template.type == activeBodyType) {
            Service.getInstance().useItem(item.indexUI);
            sleep(700L);
        }
        clearActive();
    }

    private static void clearActive() {
        activeItemId = -1;
        activeTemplateId = -1;
        activeType = -1;
        activeSys = -999;
        activeUpgrade = -1;
        activeBodyType = -1;
        activeItemRef = null;
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

    private static void updateCurrentItemText(Item item, int target) {
        if (item == null || item.template == null) {
            clearCurrentItemText();
            return;
        }
        currentItemName = item.template.name;
        currentItemUpgrade = item.upgrade;
        currentTargetUpgrade = target;
    }

    private static void clearCurrentItemText() {
        currentItemName = "";
        currentItemUpgrade = -1;
        currentTargetUpgrade = -1;
    }

    private static String shortName(String text) {
        if (text == null) {
            return "";
        }
        return text.length() > 14 ? text.substring(0, 14) : text;
    }

    private static void finish(String text) {
        running = false;
        GameScr.chatPopup(text);
    }

    private static int ceilDiv(int value, int div) {
        return (value + div - 1) / div;
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }
}

final class AutoDapDoStatus extends Auto {

    protected void run() {
        if (!AutoDapDo.isRunning()) {
            AutoDapDo.restoreAutoStatus();
        }
    }

    public String toString() {
        return AutoDapDo.getAutoText();
    }
}
