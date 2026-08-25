public final class AutoTinhLuyen implements Runnable {

    private static final int[] ADORN_TYPES = new int[]{3, 5, 7, 9};
    private static final int[] CLOTHE_TYPES = new int[]{0, 2, 4, 6, 8};
    private static final int[] ALL_BODY_TYPES = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int[] YEN_NEED = new int[]{150000, 247500, 408375, 673819, 1111801, 2056832, 4010822, 7420021, 12243035};
    private static final byte[] STONE_NEED = new byte[]{3, 5, 9, 4, 7, 10, 5, 7, 9};
    private static final int ITEM_CHUYEN_TINH_THACH = 454;
    private static final int ITEM_TU_TINH_SO = 455;
    private static final int ITEM_TU_TINH_TRUNG = 456;
    private static final int ITEM_TU_TINH_CAO = 457;
    private static final int ITEM_DA_11 = 10;
    private static final int ITEM_DA_12 = 11;
    private static final long WAIT_MISSING_MS = 3000L;
    private static final long COMBINE_STONE_TIMEOUT_MS = 2500L;
    private static final long BACKGROUND_COMBINE_COOLDOWN_MS = 2500L;
    private static final int[] PICK_RESOURCE_IDS = new int[]{454, 455, 456, 457};
    private static final int ACTION_NONE = 0;
    private static final int ACTION_CONVERT = 1;
    private static final int ACTION_REFINE = 2;

    private static boolean running = false;
    private static Thread thread;
    private static int activeItemId = -1;
    private static int activeTemplateId = -1;
    private static int activeType = -1;
    private static int activeSys = -999;
    private static int activeUpgrade = -1;
    private static int activeBodyType = -1;
    private static Item activeItemRef;
    private static String currentText = "";
    private static int convertCount = 0;
    private static int refineCount = 0;
    private static int failCount = 0;
    private static int[] pickReserve = new int[]{0, 0, 0, 0};
    private static int[] pickReserveHave = new int[]{-1, -1, -1, -1};
    private static long pickReserveAt = 0L;
    private static long lastBackgroundCombineAt = 0L;

    public static void start() {
        FormAutoTinhLuyen.load();
        startInternal();
    }

    public static void startConvertOnly() {
        FormAutoTinhLuyen.load();
        FormAutoTinhLuyen.ActionMode = FormAutoTinhLuyen.ACTION_CONVERT_ONLY;
        startInternal();
    }

    private static void startInternal() {
        if (running) {
            GameScr.chatPopup("Auto tinh luyện đang chạy");
            return;
        }

        running = true;
        convertCount = 0;
        refineCount = 0;
        failCount = 0;
        clearActive();
        thread = new Thread(new AutoTinhLuyen());
        thread.start();
        GameScr.chatPopup(getAutoText());
    }

    public static void stop() {
        if (!running) {
            return;
        }

        running = false;
        GameScr.chatPopup("Dừng auto tinh luyện");
    }

    public static void toggle() {
        if (running) {
            stop();
        } else {
            start();
        }
    }

    public static boolean isRunning() {
        return running;
    }

    public static String getStatusText() {
        return running ? "Đang chạy" : "Đang tắt";
    }

    public static String getAutoText() {
        if (currentText != null && currentText.length() > 0) {
            return currentText;
        }
        if (FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_CONVERT_ONLY) {
            return "Dịch chuyển " + FormAutoTinhLuyen.getModeName();
        }
        return "Tinh luyện " + FormAutoTinhLuyen.getModeName() + " -> " + FormAutoTinhLuyen.TargetLevel;
    }

    public static boolean canPickItemTemplate(ItemTemplate itemTemplate) {
        if (!running || itemTemplate == null) {
            return true;
        }
        int index = getPickResourceIndex(itemTemplate.id);
        if (index < 0) {
            return itemTemplate.type == 19;
        }
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null || me.arrItemBody == null) {
            return false;
        }
        if (itemTemplate.id == ITEM_CHUYEN_TINH_THACH && !hasPendingConvertTarget(me)) {
            return false;
        }
        refreshPickResourceReserve(me);
        int limit = getPickResourceLimit(me, itemTemplate.id);
        return limit > 0 && countItemEntries(me, itemTemplate.id) + pickReserve[index] < limit;
    }

    public static void reservePickItemTemplate(ItemTemplate itemTemplate) {
        if (!running || itemTemplate == null) {
            return;
        }
        int index = getPickResourceIndex(itemTemplate.id);
        if (index < 0) {
            return;
        }
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return;
        }
        refreshPickResourceReserve(me);
        ++pickReserve[index];
        pickReserveAt = System.currentTimeMillis();
    }

    public void run() {
        int attempts = 0;
        try {
            while (running) {
                Char me = Char.getMyChar();
                if (me == null || me.arrItemBag == null || me.arrItemBody == null) {
                    sleep(1000L);
                    continue;
                }
                if (!me.isHuman) {
                    finish("Auto tinh luyện chỉ chạy ở nhân vật chính");
                    break;
                }

                Item item = resolveTargetItem(me);
                if (item == null) {
                    if (currentText == null || currentText.length() == 0) {
                        currentText = FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_BAG_INDEX0
                                ? "TL: chờ đồ ở index 0"
                                : "TL: chờ đồ phù hợp";
                    }
                    clearActive();
                    waitMissing();
                    continue;
                }

                int action = getActionForItem(item);
                if (action == ACTION_NONE) {
                    currentText = "TL: chờ đồ hợp lệ " + resourceSummary(me, item);
                    clearActive();
                    waitMissing();
                    continue;
                }

                if (!prepareResourcesBeforeDetach(me, item, action)) {
                    waitMissing();
                    continue;
                }

                if (item.typeUI == 5 || item.typeUI == 41) {
                    if (!detachEquippedItem(me, item)) {
                        waitMissing();
                        continue;
                    }
                    item = waitActiveInBag();
                    if (item == null) {
                        currentText = "TL: chờ đồ sau khi tháo";
                        reEquipActiveIfNeeded(Char.getMyChar());
                        waitMissing();
                        continue;
                    }
                    action = getActionForItem(item);
                }

                Code.c(item);
                int tinhLuyen = item.getTinhLuyen(85);
                updateCurrentText(item, tinhLuyen);

                if (action == ACTION_CONVERT) {
                    if (!convertOnce(me, item)) {
                        reEquipActiveIfNeeded(Char.getMyChar());
                        clearActive();
                        waitMissing();
                        continue;
                    }
                    ++convertCount;
                    reEquipActiveIfNeeded(Char.getMyChar());
                    clearActive();
                    sleep(FormAutoTinhLuyen.DelayMs);
                    continue;
                }

                if (FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_CONVERT_ONLY) {
                    completeOneItem(me, "Đã dịch chuyển xong");
                    waitMissing();
                    continue;
                }

                if (action != ACTION_REFINE) {
                    currentText = "TL: chờ đủ điều kiện " + resourceSummary(me, item);
                    reEquipActiveIfNeeded(Char.getMyChar());
                    clearActive();
                    waitMissing();
                    continue;
                }

                int beforeLevel = item.getTinhLuyen(85);
                if (!refineOnce(me, item, beforeLevel)) {
                    reEquipActiveIfNeeded(Char.getMyChar());
                    clearActive();
                    waitMissing();
                    continue;
                }
                ++attempts;
                reEquipActiveIfNeeded(Char.getMyChar());
                clearActive();
                sleep(FormAutoTinhLuyen.DelayMs);
            }
        } catch (Exception e) {
            finish("Lỗi auto tinh luyện");
        } finally {
            running = false;
            reEquipActiveIfNeeded(Char.getMyChar());
            clearActive();
            restoreGameMenu();
            currentText = "";
        }
    }

    private static boolean convertOnce(Char me, Item item) {
        if (item.upgrade < 12) {
            currentText = "TL: chờ đồ +12 để dịch chuyển";
            return false;
        }

        Item[] mats = selectItems(me, ITEM_CHUYEN_TINH_THACH, 20);
        if (mats == null) {
            currentText = "TL: chờ 20 chuyển tinh thạch 454 " + resourceSummary(me, item);
            return false;
        }

        int beforeStone = countItemEntries(me, ITEM_CHUYEN_TINH_THACH);
        int beforeLevel = item.getTinhLuyen(85);
        currentText = "Dịch chuyển " + shortName(item.template.name) + " +" + item.upgrade;
        Service.getInstance().dichChuyenSilent(item, mats);

        if (!waitConvertResult(item, beforeLevel, beforeStone)) {
            requestSelfInfo(me);
            if (!waitConvertResult(item, beforeLevel, beforeStone)) {
                currentText = "TL: chờ kết quả dịch chuyển";
                return false;
            }
        }
        Item refreshed = findActiveInBag(Char.getMyChar());
        refreshActiveIdentity(refreshed != null ? refreshed : item);
        return true;
    }

    private static boolean refineOnce(Char me, Item item, int level) {
        int stoneId = getStoneId(level);
        int quantity = STONE_NEED[level];
        Item[] mats = selectItems(me, stoneId, quantity);
        if (mats == null) {
            currentText = "TL: chờ " + quantity + " thạch " + stoneId + " " + resourceSummary(me, item);
            return false;
        }

        int beforeStone = countItemEntries(me, stoneId);
        int beforeLevel = item.getTinhLuyen(85);
        currentText = "TL " + shortName(item.template.name) + " " + beforeLevel + "->" + FormAutoTinhLuyen.TargetLevel
                + " OK:" + refineCount + " Xịt:" + failCount;
        Service.getInstance().b(item, mats);

        int afterLevel = waitRefineResult(item, beforeLevel, stoneId, beforeStone);
        if (afterLevel < -1) {
            requestSelfInfo(me);
            afterLevel = waitRefineResult(item, beforeLevel, stoneId, beforeStone);
        }
        if (afterLevel < -1) {
            currentText = "TL: chờ kết quả tinh luyện";
            return false;
        }

        if (afterLevel > beforeLevel) {
            ++refineCount;
            Item refreshed = findActiveInBag(Char.getMyChar());
            refreshActiveIdentity(refreshed != null ? refreshed : item);
        } else {
            ++failCount;
        }
        return true;
    }

    private static boolean ensureStoneForLevel(Char me, int level) {
        int stoneId = getStoneId(level);
        int need = STONE_NEED[level];
        if (countItemEntries(me, stoneId) >= need) {
            return true;
        }
        if (!FormAutoTinhLuyen.AutoCombineStone) {
            return false;
        }

        if (!combineOneStepTowardStone(me, stoneId)) {
            return false;
        }
        me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return false;
        }
        return countItemEntries(me, stoneId) >= need;
    }

    private static boolean combineOneStepTowardStone(Char me, int stoneId) {
        if (stoneId == ITEM_TU_TINH_TRUNG) {
            if (!canActivateStoneCombine(me, 3)) {
                return false;
            }
            return combineStone(me, ITEM_TU_TINH_SO, ITEM_TU_TINH_TRUNG, ITEM_DA_11);
        }
        if (stoneId == ITEM_TU_TINH_CAO) {
            if (!canActivateStoneCombine(me, 6)) {
                return false;
            }
            if (canCombineOne(me, ITEM_TU_TINH_TRUNG, ITEM_DA_12)) {
                return combineStone(me, ITEM_TU_TINH_TRUNG, ITEM_TU_TINH_CAO, ITEM_DA_12);
            }
            return combineStone(me, ITEM_TU_TINH_SO, ITEM_TU_TINH_TRUNG, ITEM_DA_11);
        }
        return false;
    }

    private static boolean tryBackgroundCombineForItem(Char me, Item item) {
        if (!FormAutoTinhLuyen.AutoCombineStone || item == null || getActionForItem(item) != ACTION_REFINE) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - lastBackgroundCombineAt < BACKGROUND_COMBINE_COOLDOWN_MS) {
            return false;
        }
        int level = item.getTinhLuyen(85);
        if (level < 0 || level >= STONE_NEED.length) {
            return false;
        }
        int stoneId = getStoneId(level);
        if (countItemEntries(me, stoneId) >= STONE_NEED[level]) {
            return false;
        }
        if (!combineOneStepTowardStone(me, stoneId)) {
            return false;
        }
        lastBackgroundCombineAt = now;
        return true;
    }

    private static boolean tryBackgroundCombineForQueue(Char me) {
        if (!FormAutoTinhLuyen.AutoCombineStone || me == null || me.arrItemBag == null || me.arrItemBody == null) {
            return false;
        }
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_BAG_INDEX0) {
            return tryBackgroundCombineForItem(me, me.arrItemBag.length > 0 ? me.arrItemBag[0] : null);
        }

        int[] types = getTypesForMode();
        for (int i = 0; i < types.length; ++i) {
            if (tryBackgroundCombineForItem(me, getEquippedItemByType(me, types[i]))) {
                return true;
            }
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            if (tryBackgroundCombineForItem(me, me.arrItemBag[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean combineStone(Char me, int sourceId, int targetId, int catalystId) {
        int beforeSource = countItemEntries(me, sourceId);
        int beforeTarget = countItemEntries(me, targetId);
        Item[] mats;
        if (beforeSource >= 9) {
            if (Char.countNullSlot() <= 0) {
                currentText = "TL: chờ trống 1 ô để luyện thạch";
                return false;
            }
            mats = selectItems(me, sourceId, 9);
        } else if (FormAutoTinhLuyen.UseCatalystStone && beforeSource >= 3 && countItemEntries(me, catalystId) >= 1) {
            if (Char.countNullSlot() < 3) {
                currentText = "TL: chờ trống 3 ô để luyện thạch";
                return false;
            }
            mats = new Item[24];
            Item catalyst = selectFirstItem(me, catalystId);
            if (catalyst == null) {
                return false;
            }
            mats[0] = catalyst;
            Item[] sources = selectItems(me, sourceId, 3);
            if (sources == null) {
                return false;
            }
            int idx = 1;
            for (int i = 0; i < sources.length && idx < mats.length; ++i) {
                if (sources[i] != null) {
                    mats[idx++] = sources[i];
                }
            }
        } else {
            return false;
        }

        currentText = "Luyện thạch " + sourceId + " -> " + targetId;
        Service.getInstance().e(mats);
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < COMBINE_STONE_TIMEOUT_MS) {
            Char now = Char.getMyChar();
            if (now != null && now.arrItemBag != null) {
                if (countItemEntries(now, targetId) > beforeTarget || countItemEntries(now, sourceId) < beforeSource) {
                    return true;
                }
            }
            sleep(150L);
        }
        return false;
    }

    private static Item resolveTargetItem(Char me) {
        Item active = findActiveInBag(me);
        if (active != null) {
            if (hasResourcesForAction(me, active)) {
                return active;
            }
            if (tryBackgroundCombineForItem(me, active)) {
                Char refreshed = Char.getMyChar();
                if (hasImmediateResourcesForAction(refreshed, active)) {
                    return active;
                }
            }
            currentText = "TL: chờ tài nguyên " + resourceSummary(me, active);
            reEquipActiveIfNeeded(me);
            clearActive();
        } else if (activeTemplateId >= 0) {
            clearActive();
        }

        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_BAG_INDEX0) {
            Item item = me.arrItemBag.length > 0 ? me.arrItemBag[0] : null;
            if (isCandidate(item)) {
                if (hasResourcesForAction(me, item)) {
                    lockTargetItem(item);
                    return item;
                }
                currentText = "TL: chờ tài nguyên index 0 " + resourceSummary(me, item);
            }
            return null;
        }

        Item ready = findReadyTarget(me);
        if (ready != null) {
            lockTargetItem(ready);
            return ready;
        }

        Item pending = findPendingTarget(me);
        if (pending != null) {
            tryBackgroundCombineForQueue(me);
            currentText = "TL: chờ tài nguyên " + queueResourceSummary(me);
        }
        return null;
    }

    private static Item findReadyTarget(Char me) {
        Item equipped = findBestEquippedItem(me, true);
        if (equipped != null) {
            return equipped;
        }
        return findBestBagItem(me, true);
    }

    private static Item findPendingTarget(Char me) {
        Item pending = findBestEquippedItem(me, false);
        if (pending != null) {
            return pending;
        }
        return findBestBagItem(me, false);
    }

    private static Item findBestEquippedItem(Char me, boolean requireResources) {
        int[] types = getTypesForMode();
        for (int i = 0; i < types.length; ++i) {
            Item item = getEquippedItemByType(me, types[i]);
            if (isCandidate(item) && (!requireResources || hasImmediateResourcesForAction(me, item))) {
                return item;
            }
        }
        return null;
    }

    private static Item getEquippedItemByType(Char me, int type) {
        if (me == null) {
            return null;
        }
        if (type >= 29 && type <= 33) {
            int mountIndex = type - 29;
            return me.arrItemMounts != null && mountIndex >= 0 && mountIndex < me.arrItemMounts.length
                    ? me.arrItemMounts[mountIndex]
                    : null;
        }
        return me.arrItemBody != null && type >= 0 && type < me.arrItemBody.length
                ? me.arrItemBody[type]
                : null;
    }

    private static Item findBestBagItem(Char me, boolean requireResources) {
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (isCandidate(item) && isTargetType(item) && (!requireResources || hasImmediateResourcesForAction(me, item))) {
                return item;
            }
        }
        return null;
    }

    private static boolean isCandidate(Item item) {
        return getActionForItem(item) != ACTION_NONE;
    }

    private static int getActionForItem(Item item) {
        if (!isSupportedEquip(item) || !isTargetType(item)) {
            return ACTION_NONE;
        }
        int tinhLuyen = item.getTinhLuyen(85);
        if (tinhLuyen < 0) {
            if (FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_REFINE_ONLY || item.upgrade < 12) {
                return ACTION_NONE;
            }
            return ACTION_CONVERT;
        }
        if (FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_CONVERT_ONLY
                || tinhLuyen >= FormAutoTinhLuyen.TargetLevel || tinhLuyen >= 9) {
            return ACTION_NONE;
        }
        return ACTION_REFINE;
    }

    private static boolean prepareResourcesBeforeDetach(Char me, Item item, int action) {
        if (action == ACTION_CONVERT) {
            if (countItemEntries(me, ITEM_CHUYEN_TINH_THACH) < 20) {
                currentText = "TL: thiếu chuyển tinh thạch " + resourceSummary(me, item);
                return false;
            }
            return true;
        }
        if (action == ACTION_REFINE) {
            int level = item.getTinhLuyen(85);
            if (!checkMoney(me, level)) {
                currentText = "TL: thiếu yên/xu " + resourceSummary(me, item);
                return false;
            }
            if (!ensureStoneForLevel(me, level)) {
                currentText = "TL: thiếu thạch " + resourceSummary(Char.getMyChar(), item);
                return false;
            }
            return true;
        }
        return false;
    }

    private static boolean hasResourcesForAction(Char me, Item item) {
        return hasImmediateResourcesForAction(me, item);
    }

    private static boolean hasImmediateResourcesForAction(Char me, Item item) {
        if (item != null && (item.typeUI == 5 || item.typeUI == 41) && Char.countNullSlot() <= 0) {
            return false;
        }
        int action = getActionForItem(item);
        if (action == ACTION_CONVERT) {
            return countItemEntries(me, ITEM_CHUYEN_TINH_THACH) >= 20;
        }
        if (action == ACTION_REFINE) {
            int level = item.getTinhLuyen(85);
            return checkMoney(me, level) && hasStoneForLevel(me, level);
        }
        return false;
    }

    private static boolean hasStoneForLevel(Char me, int level) {
        if (level < 0 || level >= STONE_NEED.length) {
            return false;
        }
        return countItemEntries(me, getStoneId(level)) >= STONE_NEED[level];
    }

    private static boolean canHaveStoneForLevel(Char me, int level) {
        if (level < 0 || level >= STONE_NEED.length) {
            return false;
        }
        int stoneId = getStoneId(level);
        int need = STONE_NEED[level];
        return estimateStoneAfterCombine(me, stoneId) >= need;
    }

    private static int estimateStoneAfterCombine(Char me, int stoneId) {
        if (stoneId == ITEM_TU_TINH_SO) {
            return countItemEntries(me, ITEM_TU_TINH_SO);
        }
        if (stoneId == ITEM_TU_TINH_TRUNG) {
            return countItemEntries(me, ITEM_TU_TINH_TRUNG)
                    + estimateCombineResult(countItemEntries(me, ITEM_TU_TINH_SO), countItemEntries(me, ITEM_DA_11));
        }
        if (stoneId == ITEM_TU_TINH_CAO) {
            int trung = countItemEntries(me, ITEM_TU_TINH_TRUNG)
                    + estimateCombineResult(countItemEntries(me, ITEM_TU_TINH_SO), countItemEntries(me, ITEM_DA_11));
            return countItemEntries(me, ITEM_TU_TINH_CAO)
                    + estimateCombineResult(trung, countItemEntries(me, ITEM_DA_12));
        }
        return 0;
    }

    private static int estimateCombineResult(int source, int catalyst) {
        int result = 0;
        if (FormAutoTinhLuyen.UseCatalystStone) {
            int byCatalyst = source / 3;
            if (byCatalyst > catalyst) {
                byCatalyst = catalyst;
            }
            result += byCatalyst;
            source -= byCatalyst * 3;
        }
        return result + source / 9;
    }

    private static boolean canCombineOne(Char me, int sourceId, int catalystId) {
        int source = countItemEntries(me, sourceId);
        if (source >= 9) {
            return true;
        }
        return FormAutoTinhLuyen.UseCatalystStone && source >= 3 && countItemEntries(me, catalystId) >= 1;
    }

    private static boolean canActivateStoneCombine(Char me, int minTinhLuyen) {
        if (me == null || me.arrItemBag == null || me.arrItemBody == null
                || FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_CONVERT_ONLY
                || FormAutoTinhLuyen.TargetLevel <= minTinhLuyen) {
            return false;
        }
        int[] stats = new int[]{0, 0};
        scanStoneStageTargets(me, minTinhLuyen, stats);
        return stats[0] > 0 && stats[0] == stats[1];
    }

    private static void scanStoneStageTargets(Char me, int minTinhLuyen, int[] stats) {
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_BAG_INDEX0) {
            addStoneStageTarget(me.arrItemBag.length > 0 ? me.arrItemBag[0] : null, minTinhLuyen, stats);
            return;
        }

        int[] types = getTypesForMode();
        for (int i = 0; i < types.length; ++i) {
            addStoneStageTarget(getEquippedItemByType(me, types[i]), minTinhLuyen, stats);
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            addStoneStageTarget(me.arrItemBag[i], minTinhLuyen, stats);
        }
    }

    private static void addStoneStageTarget(Item item, int minTinhLuyen, int[] stats) {
        if (!isSupportedEquip(item) || !isTargetType(item) || stats == null) {
            return;
        }
        int tinhLuyen = item.getTinhLuyen(85);
        boolean needWork = false;
        if (tinhLuyen < 0) {
            needWork = FormAutoTinhLuyen.ActionMode != FormAutoTinhLuyen.ACTION_REFINE_ONLY && item.upgrade >= 12;
        } else if (tinhLuyen < FormAutoTinhLuyen.TargetLevel && tinhLuyen < 9) {
            needWork = true;
        }
        if (!needWork) {
            return;
        }
        ++stats[0];
        if (tinhLuyen >= minTinhLuyen) {
            ++stats[1];
        }
    }

    private static int getPickResourceIndex(int itemId) {
        for (int i = 0; i < PICK_RESOURCE_IDS.length; ++i) {
            if (PICK_RESOURCE_IDS[i] == itemId) {
                return i;
            }
        }
        return -1;
    }

    private static void refreshPickResourceReserve(Char me) {
        long now = System.currentTimeMillis();
        boolean reset = now - pickReserveAt > 4000L;
        for (int i = 0; i < PICK_RESOURCE_IDS.length; ++i) {
            int have = countItemEntries(me, PICK_RESOURCE_IDS[i]);
            if (pickReserveHave[i] < 0 || pickReserveHave[i] != have) {
                reset = true;
            }
        }
        if (reset) {
            for (int i = 0; i < PICK_RESOURCE_IDS.length; ++i) {
                pickReserve[i] = 0;
                pickReserveHave[i] = countItemEntries(me, PICK_RESOURCE_IDS[i]);
            }
            pickReserveAt = now;
        }
    }

    private static int getPickResourceLimit(Char me, int itemId) {
        int index = getPickResourceIndex(itemId);
        if (index < 0) {
            return 0;
        }
        int[] needs = buildPickResourceNeeds(me);
        return needs[index];
    }

    private static int[] buildPickResourceNeeds(Char me) {
        int[] needs = new int[]{0, 0, 0, 0};
        int[] stats = new int[]{0, 0, 0};
        scanPickTargets(me, stats, null);
        if (stats[0] > 0 && stats[0] == stats[1] && FormAutoTinhLuyen.ActionMode != FormAutoTinhLuyen.ACTION_REFINE_ONLY) {
            needs[0] = stats[1] * 20;
            if (FormAutoTinhLuyen.ActionMode != FormAutoTinhLuyen.ACTION_CONVERT_ONLY) {
                needs[1] = getWarmupTuTinhSoNeed(stats[1]);
            }
            addIntermediateStonePickNeeds(me, needs);
            return needs;
        }
        scanPickTargets(me, null, needs);
        addIntermediateStonePickNeeds(me, needs);
        return needs;
    }

    private static void addIntermediateStonePickNeeds(Char me, int[] needs) {
        if (!FormAutoTinhLuyen.AutoCombineStone || me == null || needs == null || needs.length < 4) {
            return;
        }

        int haveCao = countItemEntries(me, ITEM_TU_TINH_CAO);
        int missingCao = needs[3] - haveCao;
        if (missingCao > 0) {
            if (needs[2] < 9) {
                needs[2] = 9;
            }
            if (countItemEntries(me, ITEM_TU_TINH_TRUNG) < 9 && needs[1] < 9) {
                needs[1] = 9;
            }
        }

        int haveTrung = countItemEntries(me, ITEM_TU_TINH_TRUNG);
        int missingTrung = needs[2] - haveTrung;
        if (missingTrung > 0 && needs[1] < 9) {
            needs[1] = 9;
        }
    }

    private static int getWarmupTuTinhSoNeed(int itemCount) {
        int target = FormAutoTinhLuyen.TargetLevel;
        if (target > 3) {
            target = 3;
        }
        if (target > 9) {
            target = 9;
        }
        int need = 0;
        for (int level = 0; level < target; ++level) {
            need += STONE_NEED[level];
        }
        return need * itemCount;
    }

    private static void scanPickTargets(Char me, int[] stats, int[] needs) {
        if (me == null || me.arrItemBag == null || me.arrItemBody == null) {
            return;
        }
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_BAG_INDEX0) {
            addPickTarget(me.arrItemBag.length > 0 ? me.arrItemBag[0] : null, stats, needs);
            return;
        }

        int[] types = getTypesForMode();
        for (int i = 0; i < types.length; ++i) {
            addPickTarget(getEquippedItemByType(me, types[i]), stats, needs);
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            addPickTarget(me.arrItemBag[i], stats, needs);
        }
    }

    private static boolean hasPendingConvertTarget(Char me) {
        if (FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_REFINE_ONLY
                || me == null || me.arrItemBag == null || me.arrItemBody == null) {
            return false;
        }
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_BAG_INDEX0) {
            return isPendingConvertTarget(me.arrItemBag.length > 0 ? me.arrItemBag[0] : null);
        }

        int[] types = getTypesForMode();
        for (int i = 0; i < types.length; ++i) {
            if (isPendingConvertTarget(getEquippedItemByType(me, types[i]))) {
                return true;
            }
        }
        if (FormAutoTinhLuyen.hasSelectedSlots() || FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_ALL_BODY) {
            return false;
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            if (isPendingConvertTarget(me.arrItemBag[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPendingConvertTarget(Item item) {
        return getActionForItem(item) == ACTION_CONVERT;
    }

    private static void addPickTarget(Item item, int[] stats, int[] needs) {
        if (!isSupportedEquip(item) || !isTargetType(item)) {
            return;
        }
        int tinhLuyen = item.getTinhLuyen(85);
        if (tinhLuyen < 0) {
            if (FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_REFINE_ONLY || item.upgrade < 12) {
                return;
            }
            if (stats != null) {
                ++stats[0];
                ++stats[1];
            }
            if (needs != null) {
                needs[0] += 20;
                if (FormAutoTinhLuyen.ActionMode != FormAutoTinhLuyen.ACTION_CONVERT_ONLY) {
                    addStoneNeeds(needs, 0);
                }
            }
            return;
        }
        if (FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_CONVERT_ONLY
                || tinhLuyen >= FormAutoTinhLuyen.TargetLevel || tinhLuyen >= 9) {
            return;
        }
        if (stats != null) {
            ++stats[0];
            ++stats[2];
        }
        if (needs != null) {
            addStoneNeeds(needs, tinhLuyen);
        }
    }

    private static void addStoneNeeds(int[] needs, int startLevel) {
        int target = FormAutoTinhLuyen.TargetLevel;
        if (target > 9) {
            target = 9;
        }
        for (int level = startLevel; level < target; ++level) {
            int index = getPickResourceIndex(getStoneId(level));
            if (index >= 0) {
                needs[index] += STONE_NEED[level];
            }
        }
    }

    private static boolean isSupportedEquip(Item item) {
        return item != null && item.template != null
                && (item.isTypeClothe() || item.isTypeAdorn() || item.isTypeWeapon()
                || item.template.type == 12 || item.isTypeMounts());
    }

    private static boolean isTargetType(Item item) {
        if (item == null) {
            return false;
        }
        if (FormAutoTinhLuyen.hasSelectedSlots()) {
            return item.template != null && FormAutoTinhLuyen.isSelectedType(item.template.type);
        }
        switch (FormAutoTinhLuyen.Mode) {
            case FormAutoTinhLuyen.MODE_WEAPON:
                return item.isTypeWeapon();
            case FormAutoTinhLuyen.MODE_ADORN:
                return item.isTypeAdorn();
            case FormAutoTinhLuyen.MODE_CLOTHE:
                return item.isTypeClothe();
            case FormAutoTinhLuyen.MODE_ALL_BODY:
                return item.isTypeClothe() || item.isTypeAdorn() || item.isTypeWeapon();
            default:
                return true;
        }
    }

    private static int[] getTypesForMode() {
        if (FormAutoTinhLuyen.hasSelectedSlots()) {
            return FormAutoTinhLuyen.getSelectedTypes();
        }
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_WEAPON) {
            return new int[]{1};
        }
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_ADORN) {
            return ADORN_TYPES;
        }
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_CLOTHE) {
            return CLOTHE_TYPES;
        }
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_ALL_BODY) {
            return ALL_BODY_TYPES;
        }
        return new int[0];
    }

    private static boolean detachEquippedItem(Char me, Item item) {
        if (Char.countNullSlot() <= 0) {
            currentText = "TL: chờ trống 1 ô để tháo đồ";
            return false;
        }
        lockTargetItem(item);
        activeBodyType = item.template.type;
        if (item.typeUI == 41 || item.isTypeMounts()) {
            Service.getInstance().itemMonToBag(item.indexUI);
        } else {
            Service.getInstance().itemBodyToBag(item.indexUI);
        }
        return true;
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

    private static boolean matchesActiveBase(Item item) {
        if (item == null || item.template == null || activeTemplateId < 0) {
            return false;
        }
        return item.template.id == activeTemplateId && item.template.type == activeType
                && item.sys == activeSys;
    }

    private static Item findActiveInBag(Char me) {
        if (activeTemplateId < 0 || me == null || me.arrItemBag == null) {
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
        if (found != null) {
            activeItemRef = found;
        }
        return found;
    }

    private static Item findActiveInBagForEquip(Char me) {
        Item item = findActiveInBag(me);
        if (item != null) {
            return item;
        }
        if (activeTemplateId < 0 || me == null || me.arrItemBag == null) {
            return null;
        }

        if (activeItemRef != null) {
            for (int i = 0; i < me.arrItemBag.length; ++i) {
                item = me.arrItemBag[i];
                if (item == activeItemRef && matchesActiveBase(item)) {
                    return item;
                }
            }
        }

        Item found = null;
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            item = me.arrItemBag[i];
            if (matchesActiveBase(item)) {
                if (activeItemId > 0 && item.itemId == activeItemId) {
                    activeItemRef = item;
                    return item;
                }
                if (item.upgrade == activeUpgrade) {
                    if (found != null) {
                        return null;
                    }
                    found = item;
                }
            }
        }
        if (found != null) {
            activeItemRef = found;
        }
        return found;
    }

    private static Item waitActiveInBag() {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < 7000L) {
            Item item = findActiveInBag(Char.getMyChar());
            if (item != null) {
                return item;
            }
            sleep(150L);
        }
        return null;
    }

    private static void completeOneItem(Char me, String message) {
        reEquipActiveIfNeeded(me);
        clearActive();
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_BAG_INDEX0) {
            currentText = "TL: " + message + ", đang chờ";
        } else {
            GameScr.chatPopup(message);
            sleep(500L);
        }
    }

    private static void reEquipActiveIfNeeded(Char me) {
        if (activeTemplateId < 0 || activeBodyType < 0 || me == null || me.arrItemBag == null) {
            return;
        }
        if (isActiveEquipped(me)) {
            return;
        }
        Item item = findActiveInBagForEquip(me);
        if (item != null && item.template.type == activeBodyType) {
            Service.getInstance().useItem(item.indexUI);
            waitActiveEquipped();
        }
    }

    private static boolean isActiveEquipped(Char me) {
        if (me == null || activeBodyType < 0) {
            return false;
        }
        Item item = getEquippedItemByType(me, activeBodyType);
        return item != null && matchesActiveBase(item);
    }

    private static void waitActiveEquipped() {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 2500L) {
            if (isActiveEquipped(Char.getMyChar())) {
                return;
            }
            sleep(150L);
        }
    }

    private static Item[] selectItems(Char me, int id, int count) {
        if (me == null || me.arrItemBag == null || count <= 0) {
            return null;
        }
        Item[] mats = new Item[24];
        int idx = 0;
        for (int i = 0; i < me.arrItemBag.length && idx < count && idx < mats.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template != null && item.template.id == id) {
                mats[idx++] = item;
            }
        }
        return idx >= count ? mats : null;
    }

    private static Item selectFirstItem(Char me, int id) {
        if (me == null || me.arrItemBag == null) {
            return null;
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template != null && item.template.id == id) {
                return item;
            }
        }
        return null;
    }

    private static int countItemEntries(Char me, int id) {
        if (me == null || me.arrItemBag == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template != null && item.template.id == id) {
                ++count;
            }
        }
        return count;
    }

    private static int getStoneId(int level) {
        if (level < 3) {
            return ITEM_TU_TINH_SO;
        }
        return level < 6 ? ITEM_TU_TINH_TRUNG : ITEM_TU_TINH_CAO;
    }

    private static boolean checkMoney(Char me, int level) {
        if (level < 0 || level >= YEN_NEED.length) {
            return false;
        }
        return (long) me.yen + (long) me.xu >= (long) YEN_NEED[level];
    }

    private static String queueResourceSummary(Char me) {
        if (me == null) {
            return "";
        }
        int[] needs = new int[]{0, 0, 0, 0, 0};
        scanQueueResourceNeeds(me, needs);
        long money = (long) me.yen + (long) me.xu;
        return "ctt " + countItemEntries(me, ITEM_CHUYEN_TINH_THACH) + "/" + needs[0]
                + " ttts " + countItemEntries(me, ITEM_TU_TINH_SO) + "/" + needs[1]
                + " tttt " + countItemEntries(me, ITEM_TU_TINH_TRUNG) + "/" + needs[2]
                + " tttc " + countItemEntries(me, ITEM_TU_TINH_CAO) + "/" + needs[3]
                + " y " + money + "/" + needs[4];
    }

    private static void scanQueueResourceNeeds(Char me, int[] needs) {
        if (me == null || me.arrItemBag == null || me.arrItemBody == null || needs == null) {
            return;
        }
        if (FormAutoTinhLuyen.Mode == FormAutoTinhLuyen.MODE_BAG_INDEX0) {
            addQueueResourceNeed(me.arrItemBag.length > 0 ? me.arrItemBag[0] : null, needs);
            return;
        }

        int[] types = getTypesForMode();
        for (int i = 0; i < types.length; ++i) {
            addQueueResourceNeed(getEquippedItemByType(me, types[i]), needs);
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            addQueueResourceNeed(me.arrItemBag[i], needs);
        }
    }

    private static void addQueueResourceNeed(Item item, int[] needs) {
        int action = getActionForItem(item);
        if (action == ACTION_CONVERT) {
            setQueueNeed(needs, 0, 20);
            return;
        }
        if (action != ACTION_REFINE) {
            return;
        }

        int level = item.getTinhLuyen(85);
        if (level < 0 || level >= STONE_NEED.length) {
            return;
        }
        int index = getPickResourceIndex(getStoneId(level));
        if (index >= 0) {
            setQueueNeed(needs, index, STONE_NEED[level]);
        }
        setQueueNeed(needs, 4, YEN_NEED[level]);
    }

    private static void setQueueNeed(int[] needs, int index, int need) {
        if (needs == null || index < 0 || index >= needs.length || need <= 0) {
            return;
        }
        if (needs[index] == 0 || need < needs[index]) {
            needs[index] = need;
        }
    }

    private static String resourceSummary(Char me, Item item) {
        if (me == null || item == null) {
            return "";
        }
        int needCtt = 0;
        int needSo = 0;
        int needTrung = 0;
        int needCao = 0;
        int needMoney = 0;
        int action = getActionForItem(item);
        if (action == ACTION_CONVERT) {
            needCtt = 20;
        } else if (action == ACTION_REFINE) {
            int level = item.getTinhLuyen(85);
            if (level >= 0 && level < STONE_NEED.length) {
                needMoney = YEN_NEED[level];
                int stoneId = getStoneId(level);
                if (stoneId == ITEM_TU_TINH_SO) {
                    needSo = STONE_NEED[level];
                } else if (stoneId == ITEM_TU_TINH_TRUNG) {
                    needTrung = STONE_NEED[level];
                } else if (stoneId == ITEM_TU_TINH_CAO) {
                    needCao = STONE_NEED[level];
                }
            }
        }
        long money = (long) me.yen + (long) me.xu;
        return "ctt " + countItemEntries(me, ITEM_CHUYEN_TINH_THACH) + "/" + needCtt
                + " ttts " + countItemEntries(me, ITEM_TU_TINH_SO) + "/" + needSo
                + " tttt " + countItemEntries(me, ITEM_TU_TINH_TRUNG) + "/" + needTrung
                + " tttc " + countItemEntries(me, ITEM_TU_TINH_CAO) + "/" + needCao
                + " y " + money + "/" + needMoney;
    }

    private static boolean waitConvertResult(Item item, int beforeLevel, int beforeStone) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < 8000L) {
            Item current = findActiveInBag(Char.getMyChar());
            if (current != null) {
                item = current;
            }
            if (item != null && item.getTinhLuyen(85) >= 0 && item.getTinhLuyen(85) != beforeLevel) {
                return true;
            }
            Char me = Char.getMyChar();
            if (me != null && countItemEntries(me, ITEM_CHUYEN_TINH_THACH) < beforeStone && item != null && item.getTinhLuyen(85) >= 0) {
                return true;
            }
            sleep(150L);
        }
        return false;
    }

    private static int waitRefineResult(Item item, int beforeLevel, int stoneId, int beforeStone) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < 8000L) {
            Item current = findActiveInBag(Char.getMyChar());
            if (current != null) {
                item = current;
            }
            int nowLevel = item != null ? item.getTinhLuyen(85) : -1;
            if (nowLevel > beforeLevel) {
                return nowLevel;
            }
            Char me = Char.getMyChar();
            if (me != null && countItemEntries(me, stoneId) < beforeStone) {
                return nowLevel;
            }
            sleep(150L);
        }
        return -2;
    }

    private static void requestSelfInfo(Char me) {
        try {
            if (me != null && me.charName != null) {
                Service.getInstance().viewInfo(me.charName);
                sleep(1000L);
            }
        } catch (Exception e) {
        }
    }

    private static void updateCurrentText(Item item, int tinhLuyen) {
        if (item == null || item.template == null) {
            currentText = "";
            return;
        }
        if (FormAutoTinhLuyen.ActionMode == FormAutoTinhLuyen.ACTION_CONVERT_ONLY || tinhLuyen < 0) {
            currentText = "Dịch chuyển " + shortName(item.template.name) + " +" + item.upgrade
                    + " " + resourceSummary(Char.getMyChar(), item);
        } else {
            currentText = "TL " + shortName(item.template.name) + " " + tinhLuyen + "->" + FormAutoTinhLuyen.TargetLevel
                    + " OK:" + refineCount + " Xịt:" + failCount + " "
                    + resourceSummary(Char.getMyChar(), item);
        }
    }

    private static void restoreGameMenu() {
        try {
            GameScr.itemSplit = null;
            GameScr.arrItemSplit = null;
            GameScr.arrItemTradeMe = null;
            GameScr.cl = false;
            GameScr gameScr = GameScr.getInstance();
            if (gameScr != null) {
                gameScr.ef = null;
                gameScr.resetButton();
            }
            GameCanvas.setMaxTextLenght();
        } catch (Exception e) {
        }
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

    private static String shortName(String text) {
        if (text == null) {
            return "";
        }
        return text.length() > 14 ? text.substring(0, 14) : text;
    }

    private static void finish(String text) {
        running = false;
        if (text != null && text.length() > 0) {
            GameScr.chatPopup(text);
        }
    }

    private static void waitMissing() {
        sleep(WAIT_MISSING_MS);
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }
}
