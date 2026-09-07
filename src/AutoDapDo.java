public final class AutoDapDo implements Runnable {

    private static final int[] ADORN_TYPES = new int[]{3, 5, 7, 9};
    private static final int[] CLOTHE_TYPES = new int[]{0, 2, 4, 6, 8};
    private static final int MIN_EMPTY_SLOT_AFTER_FLIP = 6;
    private static final int LUCKY_CARD_ID = 340;
    private static final int YEN_ITEM_ID = 12;
    private static final int LUCKY_CARD_BUY_COUNT = 5000;
    private static final int LUCKY_CARD_CHECK_BATCH = 50;
    private static final int LUCKY_CARD_WAIT_TIMEOUT = 5000;
    private static final int LUCKY_CARD_WAIT_STEP = 40;
    private static final int LUCKY_CARD_DELETE_TIMEOUT = 5000;
    private static final int EXCHANGE_YEN_MAP = 1;
    private static final int EXCHANGE_YEN_NPC = 24;
    private static final long EXCHANGE_YEN_TIMEOUT = 45000L;

    private static final AutoDapDoStatus AUTO_STATUS = new AutoDapDoStatus();
    private static volatile boolean running = false;
    private static volatile boolean hideLuckyCardUi = false;
    private static volatile int luckyCardResultCount = 0;
    private static Thread thread;
    private static boolean tempConfig = false;
    private static int oldMode = FormAutoDapDo.MODE_BAG_INDEX0;
    private static int oldModeMask = FormAutoDapDo.MASK_BAG_INDEX0;
    private static int oldTargetUpgrade = 16;
    private static boolean oldUseXuWhenLackYen = false;
    private static boolean oldExchangeLuongToYen = false;
    private static boolean oldReEquipWhenDone = true;
    private static boolean oldAutoFlip = true;
    private static boolean oldUseProtectUnder14 = true;
    private static boolean oldAutoBuyProtect475 = true;
    private static boolean oldCleanLuckyCardNonCrystal = false;
    private static boolean cleanLuckyCardNonCrystal = false;
    private static int oldRequiredTier = -1;
    private static int requiredTier = -1;
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
    private static boolean exchangeYenTried = false;

    public static void start() {
        if (running) {
            GameScr.chatPopup("Auto đập đồ đang chạy");
            return;
        }
        if (AutoLuckyCard.isRunning()) {
            GameScr.chatPopup("Auto l\u1eadt h\u00ecnh \u0111ang ch\u1ea1y");
            return;
        }

        if (!tempConfig) {
            cleanLuckyCardNonCrystal = FormAutoDapDo.AutoFlip;
        }
        checkProtectDelList(FormAutoDapDo.TargetUpgrade);
        exchangeYenTried = false;
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
        startForAutoUp(modeMask, targetUpgrade, useXuWhenLackYen, useProtect, false, false);
    }

    public static void startForAutoUp(int modeMask, int targetUpgrade, boolean useXuWhenLackYen, boolean useProtect, boolean autoFlip, boolean cleanNonCrystal) {
        startForAutoUp(modeMask, targetUpgrade, useXuWhenLackYen, useProtect, autoFlip, cleanNonCrystal, -1);
    }

    public static void startForAutoUp(int modeMask, int targetUpgrade, boolean useXuWhenLackYen, boolean useProtect, boolean autoFlip, boolean cleanNonCrystal, int targetTier) {
        if (running) {
            return;
        }
        if (AutoLuckyCard.isRunning()) {
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
        oldExchangeLuongToYen = FormAutoDapDo.ExchangeLuongToYen;
        oldReEquipWhenDone = FormAutoDapDo.ReEquipWhenDone;
        oldAutoFlip = FormAutoDapDo.AutoFlip;
        oldUseProtectUnder14 = FormAutoDapDo.UseProtectUnder14;
        oldAutoBuyProtect475 = FormAutoDapDo.AutoBuyProtect475;
        oldCleanLuckyCardNonCrystal = cleanLuckyCardNonCrystal;
        oldRequiredTier = requiredTier;
        tempConfig = true;

        FormAutoDapDo.ModeMask = modeMask;
        FormAutoDapDo.Mode = getPrimaryModeFromMask(modeMask);
        FormAutoDapDo.TargetUpgrade = targetUpgrade;
        FormAutoDapDo.UseXuWhenLackYen = useXuWhenLackYen;
        FormAutoDapDo.ExchangeLuongToYen = FormAutoUpFull.ExchangeLuongToYen;
        FormAutoDapDo.ReEquipWhenDone = true;
        FormAutoDapDo.AutoFlip = autoFlip;
        FormAutoDapDo.UseProtectUnder14 = useProtect;
        FormAutoDapDo.AutoBuyProtect475 = useProtect;
        cleanLuckyCardNonCrystal = autoFlip && cleanNonCrystal;
        requiredTier = targetTier > 0 ? targetTier : -1;
        checkProtectDelList(targetUpgrade);
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

    public static boolean shouldProtectDeleteItem(Item item) {
        if (item == null || item.template == null) {
            return false;
        }

        if (AutoUpFullSupport.shouldProtectExpandBagItem(item)) {
            return true;
        }

        if (isProtectItemId(item.template.id)) {
            return isUpgradeMaterialProtected() && (running ? FormAutoDapDo.UseProtectUnder14 : FormAutoUpFull.UseProtectUpgrade);
        }

        return isUpgradeCrystal(item) && isUpgradeMaterialProtected();
    }

    static boolean isHidingLuckyCardUi() {
        return hideLuckyCardUi;
    }

    static boolean isLuckyCardBusy() {
        return running && hideLuckyCardUi;
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
        AutoLuckyCard.captureLuckyCardReward();
        int resultCount = luckyCardResultCount + 1;
        luckyCardResultCount = resultCount;
        AutoLuckyCard.signalLuckyCardResult(resultCount);
    }

    public void run() {
        try {
            while (running) {
                Char me = Char.getMyChar();
                if (me == null || me.arrItemBag == null || me.arrItemBody == null) {
                    sleep(1000L);
                    continue;
                }
                if (FormAutoDapDo.AutoFlip) {
                    clearCombatFocus(me);
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

                if (FormAutoDapDo.AutoFlip && TileMap.mapID != 72) {
                    if (!goToLuckyCardMap()) {
                        finish("Không đến được map 72 để lật hình");
                        break;
                    }
                    sleep(500L);
                    continue;
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
        FormAutoDapDo.ExchangeLuongToYen = oldExchangeLuongToYen;
        FormAutoDapDo.ReEquipWhenDone = oldReEquipWhenDone;
        FormAutoDapDo.AutoFlip = oldAutoFlip;
        FormAutoDapDo.UseProtectUnder14 = oldUseProtectUnder14;
        FormAutoDapDo.AutoBuyProtect475 = oldAutoBuyProtect475;
        cleanLuckyCardNonCrystal = oldCleanLuckyCardNonCrystal;
        requiredTier = oldRequiredTier;
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

        int moneyState = checkMoney(me, item);
        if (moneyState < 0) {
            return true;
        }
        if (moneyState == 0) {
            finish("Không đủ yên/xu/lượng để đập");
            return false;
        }

        Item[] mats = new Item[18];
        int matCount = 0;
        int protectId = getProtectId(item.upgrade);
        if (protectId > 0) {
            Item protect = findBagItemById(me, protectId);
            if (protect == null && FormAutoDapDo.AutoBuyProtect475) {
                boolean bought = tryBuyShopItem(me, protectId, 1);
                me = Char.getMyChar();
                if (bought && FormAutoDapDo.AutoFlip && TileMap.mapID != 72) {
                    return true;
                }
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
            if (tryFlipCards(me, item, matCount)) {
                return true;
            }
            if (!running) {
                return false;
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
        active = findActiveBodyItem(me);
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

    private static Item findActiveBodyItem(Char me) {
        if (activeTemplateId < 0 || me == null || me.arrItemBody == null) {
            return null;
        }
        if (activeBodyType >= 0 && activeBodyType < me.arrItemBody.length && matchesActiveIdentity(me.arrItemBody[activeBodyType])) {
            return me.arrItemBody[activeBodyType];
        }

        Item found = null;
        int foundType = -1;
        for (int i = 0; i < me.arrItemBody.length; ++i) {
            Item item = me.arrItemBody[i];
            if (matchesActiveIdentity(item)) {
                if (found != null) {
                    return null;
                }
                found = item;
                foundType = i;
            }
        }
        if (found != null) {
            activeBodyType = foundType;
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
        if (item.typeUI == 5) {
            activeBodyType = item.indexUI;
        }
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
                && item.sys == activeSys && item.upgrade == activeUpgrade && isRequiredTier(item);
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
        return item != null && isTargetType(item) && isRequiredTier(item) && isUpgradeable(item) && item.upgrade < getTargetUpgrade(item);
    }

    private static boolean isUpgradeable(Item item) {
        return item != null && item.isTypeBody() && item.template.type < 10 && item.template.level >= 10 && !item.isUpMax();
    }

    private static boolean isRequiredTier(Item item) {
        if (requiredTier <= 0) {
            return true;
        }
        if (item == null || item.template == null) {
            return false;
        }
        int level = getEffectiveGearLevel(item.template.type, item.template.level);
        return level / 10 == requiredTier;
    }

    private static int getEffectiveGearLevel(int type, int level) {
        if (level != 85) {
            return level;
        }
        switch (type) {
            case 0:
                return 89;
            case 1:
                return 80;
            case 2:
                return 87;
            case 3:
                return 88;
            case 4:
                return 85;
            case 5:
                return 86;
            case 6:
                return 83;
            case 7:
                return 84;
            case 8:
                return 81;
            case 9:
                return 82;
        }
        return level;
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

    private static boolean isProtectItemId(int id) {
        return id == 242 || id == 284 || id == 285 || id == 475;
    }

    private static boolean isUpgradeCrystal(Item item) {
        return item != null && item.template != null && item.template.type == 26
                && GameScr.crystals != null && item.template.id >= 0 && item.template.id < GameScr.crystals.length
                && GameScr.crystals[item.template.id] > 0;
    }

    private static boolean isUpgradeMaterialProtected() {
        return running || AutoUpLevel.isRunningFullMode() && FormAutoUpFull.UpgradeGear;
    }

    private static void checkProtectDelList(int targetUpgrade) {
        boolean conflict = FormAutoDapDo.UseProtectUnder14
                && (targetUpgrade > 7 && Code.containDelItem((short) 242)
                || targetUpgrade > 10 && Code.containDelItem((short) 284)
                || targetUpgrade > 13 && Code.containDelItem((short) 285)
                || targetUpgrade > 15 && Code.containDelItem((short) 475))
                || hasCrystalInDelList();
        if (conflict) {
            GameScr.chatPopup("Da/bao hiem dang nam trong list del, tam bo qua khi dap");
        }
    }

    private static boolean hasCrystalInDelList() {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return false;
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (isUpgradeCrystal(item) && Code.containDelItem((short) item.template.id)) {
                return true;
            }
        }
        return false;
    }

    private static Item findBagItemById(Char me, int id) {
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template.id == id && item.quantity > 0) {
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
                && GameScr.crystals != null && item.template.id < GameScr.crystals.length
                && GameScr.crystals[item.template.id] > 0;
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

    private static int checkMoney(Char me, Item item) {
        int coin;
        if (item.isTypeWeapon()) {
            coin = GameScr.coinUpWeapons[item.upgrade];
        } else if (item.isTypeAdorn()) {
            coin = GameScr.coinUpAdorns[item.upgrade];
        } else {
            coin = GameScr.coinUpClothes[item.upgrade];
        }

        if (coin <= me.yen) {
            exchangeYenTried = false;
        } else if (FormAutoDapDo.ExchangeLuongToYen && tryExchangeLuongToYen(me)) {
            return -1;
        }

        if (FormAutoDapDo.UseXuWhenLackYen) {
            if (coin > me.xu + me.yen) {
                return 0;
            }
        } else if (coin > me.yen) {
            return 0;
        }

        return !FormAutoDapDo.Careful || GameScr.goldUps[item.upgrade] <= me.luong ? 1 : 0;
    }

    private static boolean tryExchangeLuongToYen(Char me) {
        if (exchangeYenTried || me == null || me.luong <= 0 || Code.auto != AUTO_STATUS || AutoNpc.running) {
            return false;
        }

        exchangeYenTried = true;
        GameScr.chatPopup("Auto \u0111\u1eadp \u0111\u1ed3: \u0111\u1ed5i L\u01b0\u1ee3ng ra Y\u00ean");
        Code.setAuto(new AutoNpc(EXCHANGE_YEN_MAP, -1, EXCHANGE_YEN_NPC, "0,4", "", 1, 500));

        long start = System.currentTimeMillis();
        boolean npcStarted = false;
        while (running && System.currentTimeMillis() - start < EXCHANGE_YEN_TIMEOUT) {
            if (AutoNpc.running) {
                npcStarted = true;
            }
            if (Code.auto == AUTO_STATUS && (npcStarted || System.currentTimeMillis() - start > 1500L)) {
                break;
            }
            sleep(100L);
        }
        return true;
    }

    private static boolean tryFlipCards(Char me, Item upgradeItem, int matStart) {
        if (!FormAutoDapDo.AutoFlip) {
            return false;
        }
        if (Char.countNullSlot() <= MIN_EMPTY_SLOT_AFTER_FLIP) {
            AutoUpFullSupport.compactCrystalsForUpgrade(true);
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
            me = Char.getMyChar();
            if (me == null || me.arrItemBag == null) {
                return false;
            }
        }

        boolean flipped = false;
        boolean oldHideLuckyCardUi = hideLuckyCardUi;
        hideLuckyCardUi = true;
        try {
            openLuckyCardNpc();
            int batchFlips = 0;
            while (running && Char.countNullSlot() > MIN_EMPTY_SLOT_AFTER_FLIP) {
                while (running && (AutoDoiLongDen.shouldPauseProducers() || AutoRuocDen.isBusy())) {
                    sleep(100L);
                }
                if (!running) {
                    break;
                }
                if (Code.auto != AUTO_STATUS) {
                    finish("D\u1eebng l\u1eadt h\u00ecnh do auto kh\u00e1c \u0111ang ch\u1ea1y");
                    return false;
                }
                if (Char.countNullSlot() <= AutoUpFullSupport.getCrystalPickEmptyReserve()) {
                    AutoUpFullSupport.compactCrystalsForUpgrade(false);
                }
                if (Char.k(LUCKY_CARD_ID) <= 0) {
                    if (!tryBuyShopItem(me, LUCKY_CARD_ID, LUCKY_CARD_BUY_COUNT)) {
                        GameScr.chatPopup("Hết phiếu lật hình");
                        break;
                    }
                    if (!goToLuckyCardMap()) {
                        GameScr.chatPopup("Khong quay lai duoc map 72");
                        break;
                    }
                    me = Char.getMyChar();
                    openLuckyCardNpc();
                }

                GameScr.indexSelect = 0;
                int beforeResult = luckyCardResultCount;
                LuckyCardBagSnapshot beforeBag = cleanLuckyCardNonCrystal ? snapshotLuckyCardBag() : null;
                Service.getInstance().ah();
                waitLuckyCardResult(beforeResult);
                if (luckyCardResultCount == beforeResult) {
                    finish("Kh\u00f4ng nh\u1eadn k\u1ebft qu\u1ea3 l\u1eadt h\u00ecnh");
                    return false;
                }
                flipped = true;
                ++batchFlips;
                if (cleanLuckyCardNonCrystal) {
                    int rewardTemplateId = AutoLuckyCard.getLastRewardTemplateId();
                    if (!cleanLuckyCardResult(beforeBag, rewardTemplateId)) {
                        finish("Ch\u01b0a \u0111\u1ed3ng b\u1ed9 ph\u1ea7n th\u01b0\u1edfng l\u1eadt h\u00ecnh");
                        return false;
                    }
                }
                if (batchFlips >= LUCKY_CARD_CHECK_BATCH) {
                    batchFlips = 0;
                    AutoUpFullSupport.compactCrystalsForUpgrade(true);
                    if (hasEnoughCrystalForUpgrade(upgradeItem, matStart)) {
                        return true;
                    }
                }
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

    private static boolean hasEnoughCrystalForUpgrade(Item upgradeItem, int matStart) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null || upgradeItem == null) {
            return false;
        }

        Item item = resolveLockedBagItem(me, upgradeItem);
        if (item == null) {
            item = upgradeItem;
        }

        Item[] mats = new Item[18];
        return selectCrystals(me, item, mats, matStart) > 0;
    }

    private static boolean goToLuckyCardMap() {
        try {
            clearCombatFocus(Char.getMyChar());
            if (TileMap.mapID == 72) {
                return true;
            }

            GameScr.chatPopup("Auto đập đồ: về map 72 lật hình");
            if (!TileMap.isTruong(TileMap.mapID)) {
                int oldMap = TileMap.mapID;
                leaveCurrentMapForLuckyCard(oldMap, 30000L);
                if (TileMap.mapID == 72) {
                    return true;
                }
            }

            if (TileMap.direction(72) && TileMap.mapID == 72) {
                return true;
            }
            if (waitMap(72, 20000L)) {
                return true;
            }

            leaveCurrentMapForLuckyCard(TileMap.mapID, 20000L);
            TileMap.direction(72);
            return waitMap(72, 20000L);
        } catch (Exception e) {
            return false;
        }
    }

    private static void clearCombatFocus(Char me) {
        try {
            if (me != null) {
                me.mobFocus = null;
                me.charFocus = null;
                me.itemFocus = null;
            }
        } catch (Exception e) {
        }
    }

    private static boolean leaveCurrentMapForLuckyCard(int oldMap, long timeout) {
        long start = System.currentTimeMillis();
        long lastReturnTown = 0L;
        long lastSuicide = 0L;
        Auto.goTruongIfNeeded();
        while (running && System.currentTimeMillis() - start < timeout) {
            clearCombatFocus(Char.getMyChar());
            if (TileMap.mapID == 72 || TileMap.isTruong(TileMap.mapID) || TileMap.isLang(TileMap.mapID)) {
                return true;
            }

            Char me = Char.getMyChar();
            long now = System.currentTimeMillis();
            if (me != null && (me.cHP <= 0 || me.statusMe == 14 || me.statusMe == 5)) {
                if (now - lastReturnTown > 1200L) {
                    lastReturnTown = now;
                    Service.getInstance().returnTownFromDead();
                }
            } else if (TileMap.mapID == oldMap && now - lastSuicide > 6000L) {
                lastSuicide = now;
                Auto.tuSat();
            }
            sleep(300L);
        }
        return TileMap.mapID == 72 || TileMap.isTruong(TileMap.mapID) || TileMap.isLang(TileMap.mapID);
    }

    private static boolean leaveCurrentMapForShop(int oldMap, long timeout) {
        long start = System.currentTimeMillis();
        long lastReturnTown = 0L;
        long lastSuicide = 0L;
        Auto.goTruongIfNeeded();
        while (running && System.currentTimeMillis() - start < timeout) {
            clearCombatFocus(Char.getMyChar());
            if (isShop14Map()) {
                return true;
            }

            Char me = Char.getMyChar();
            long now = System.currentTimeMillis();
            if (me != null && (me.cHP <= 0 || me.statusMe == 14 || me.statusMe == 5)) {
                if (now - lastReturnTown > 1200L) {
                    lastReturnTown = now;
                    Service.getInstance().returnTownFromDead();
                }
            } else if (TileMap.mapID == oldMap && now - lastSuicide > 6000L) {
                lastSuicide = now;
                Auto.tuSat();
            } else if (now - lastSuicide > 10000L) {
                lastSuicide = now;
                Auto.goTruongIfNeeded();
            }
            sleep(300L);
        }
        return isShop14Map();
    }

    private static boolean waitMap(int mapId, long timeout) {
        long start = System.currentTimeMillis();
        while (running && TileMap.mapID != mapId && System.currentTimeMillis() - start < timeout) {
            sleep(200L);
        }
        return TileMap.mapID == mapId;
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

    private static LuckyCardBagSnapshot snapshotLuckyCardBag() {
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
        while (running && System.currentTimeMillis() - start < LUCKY_CARD_WAIT_TIMEOUT) {
            int result = cleanLuckyCardResultNow(before, rewardTemplateId);
            if (result > 0) {
                return true;
            }
            if (result < 0) {
                return false;
            }
            sleep((long) LUCKY_CARD_WAIT_STEP);
        }
        return false;
    }

    private static int cleanLuckyCardResultNow(LuckyCardBagSnapshot before, int rewardTemplateId) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return 0;
        }

        boolean foundResult = hasLuckyCardRewardIncrease(before, rewardTemplateId);
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (!isLuckyCardNewItem(before, i, item, rewardTemplateId)) {
                continue;
            }

            foundResult = true;
            if (shouldKeepLuckyCardItem(item)) {
                continue;
            }

            int quantity = getNewLuckyCardQuantity(before, i, item);
            if (quantity > 0) {
                if (!deleteLuckyCardRewardAndWait(i, item, quantity)) {
                    return -1;
                }
            }
        }
        return foundResult ? 1 : 0;
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

        if (sameLuckyCardSnapshotItem(before, index, item)) {
            return item.quantity > before.quantities[index];
        }
        if (before.ids[index] >= 0 || containsLuckyCardReference(before, item)) {
            return false;
        }
        return getLuckyCardBagQuantity(item.template.id, item.template.type)
                > getLuckyCardSnapshotQuantity(before, item.template.id, item.template.type);
    }

    private static int getNewLuckyCardQuantity(LuckyCardBagSnapshot before, int index, Item item) {
        if (item == null) {
            return 0;
        }
        int quantity = item.quantity > 0 ? item.quantity : 1;
        if (sameLuckyCardSnapshotItem(before, index, item)) {
            quantity -= before.quantities[index];
        } else if (before != null && index >= 0 && index < before.items.length && before.ids[index] < 0
                && item.template != null) {
            int delta = getLuckyCardBagQuantity(item.template.id, item.template.type)
                    - getLuckyCardSnapshotQuantity(before, item.template.id, item.template.type);
            if (delta < quantity) {
                quantity = delta;
            }
        }
        return quantity > 0 ? quantity : 0;
    }

    private static boolean sameLuckyCardSnapshotItem(LuckyCardBagSnapshot before, int index, Item item) {
        return before != null && item != null && item.template != null && index >= 0 && index < before.items.length
                && before.ids[index] == item.template.id && before.types[index] == item.template.type;
    }

    private static boolean containsLuckyCardReference(LuckyCardBagSnapshot before, Item item) {
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

    private static int getLuckyCardSnapshotQuantity(LuckyCardBagSnapshot before, int id, int type) {
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

    private static int getLuckyCardBagQuantity(int id, int type) {
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

    private static boolean hasLuckyCardRewardIncrease(LuckyCardBagSnapshot before, int rewardTemplateId) {
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
            return getLuckyCardBagQuantity(item.template.id, item.template.type)
                    > getLuckyCardSnapshotQuantity(before, item.template.id, item.template.type);
        }
        return false;
    }

    private static boolean deleteLuckyCardRewardAndWait(int index, Item expected, int quantity) {
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
        while (running && System.currentTimeMillis() - start < LUCKY_CARD_DELETE_TIMEOUT) {
            Item current = me.arrItemBag[index];
            if (current == null || current.template == null || current.template.id != id || current.template.type != type) {
                return true;
            }
            int currentQuantity = current.quantity > 0 ? current.quantity : 1;
            if (currentQuantity <= beforeQuantity - deleteQuantity) {
                return true;
            }
            sleep((long) LUCKY_CARD_WAIT_STEP);
        }
        return false;
    }

    private static boolean shouldKeepLuckyCardItem(Item item) {
        if (item == null || item.template == null) {
            return true;
        }
        if (item.template.id == LUCKY_CARD_ID) {
            return true;
        }
        if (AutoUpFullSupport.shouldProtectExpandBagItem(item)) {
            return true;
        }
        if (isProtectItemId(item.template.id)) {
            return true;
        }
        if (isKeepLuckyCardType(item.template.type)) {
            return true;
        }
        return FormAutoDapDo.KeepLuckyCardStackable && (item.template.isUpToUp || item.quantity > 1);
    }

    private static boolean isKeepLuckyCardType(int type) {
        String text = FormAutoDapDo.KeepLuckyCardTypes == null ? "26" : FormAutoDapDo.KeepLuckyCardTypes.trim();
        if (text.length() == 0) {
            text = "26";
        }
        text = normalizeTypeList(text);
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

    private static String normalizeTypeList(String text) {
        String result = text;
        result = NinjaUtil.replace(result, ";", ",");
        result = NinjaUtil.replace(result, " ", ",");
        while (result.indexOf(",,") >= 0) {
            result = NinjaUtil.replace(result, ",,", ",");
        }
        return result;
    }

    private static boolean tryBuyShopItem(Char me, int itemId, int quantity) {
        int before = Char.k(itemId);
        if (AutoBuyShop.buyNow(itemId, 14, quantity)) {
            return true;
        }
        if (!ensureShop14Map()) {
            GameScr.chatPopup("Không về được làng/trường để mở f14");
            return false;
        }
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

    private static boolean ensureShop14Map() {
        try {
            if (isShop14Map()) {
                return true;
            }
            if (FormAutoDapDo.AutoFlip) {
                int oldMap = TileMap.mapID;
                leaveCurrentMapForShop(oldMap, 30000L);
                return isShop14Map();
            }

            int oldMap = TileMap.mapID;
            leaveCurrentMapForLuckyCard(oldMap, 30000L);
            return isShop14Map();
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isShop14Map() {
        return TileMap.mapID != 72 && (TileMap.isLang(TileMap.mapID) || TileMap.isTruong(TileMap.mapID));
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
