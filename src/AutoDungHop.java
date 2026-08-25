public final class AutoDungHop implements Runnable {

    private static final int NPC_KANATA = 0;
    private static final int ITEM_BAO_HIEM_DUNG_HOP = 1214;
    private static final int ITEM_THE_DUNG_HOP_1 = 1215;
    private static final int ITEM_THE_DUNG_HOP_6 = 1220;
    private static final int OPTION_DO_TINH_LUYEN = 85;
    private static final int OPTION_HUYEN_TINH_NGOC = 109;
    private static final int OPTION_HUYET_NGOC = 110;
    private static final int OPTION_LAM_TINH_NGOC = 111;
    private static final int OPTION_LUC_NGOC = 112;
    private static final int OPTION_DO_BEN = 155;
    private static final int OPTION_NGUONG_KHAI_HOA = 156;
    private static final int OPTION_GIA_TRI_TANG = 176;
    private static final int FULL_SIX_X_BASE_OPTION_COUNT = 11;
    private static final int FULL_SIX_X_TOTAL_OPTION_COUNT = 13;
    private static final int CONFIRM_ID = 1;
    private static final int SHOP_VU_KHI = 2;
    private static final int SHOP_LIEN = 16;
    private static final int SHOP_NHAN = 17;
    private static final int SHOP_NGOC_BOI = 18;
    private static final int SHOP_PHU = 19;
    private static final int SHOP_NON_NAM = 20;
    private static final int SHOP_NON_NU = 21;
    private static final int SHOP_AO_NAM = 22;
    private static final int SHOP_AO_NU = 23;
    private static final int SHOP_GANG_NAM = 24;
    private static final int SHOP_GANG_NU = 25;
    private static final int SHOP_QUAN_NAM = 26;
    private static final int SHOP_QUAN_NU = 27;
    private static final int SHOP_GIAY_NAM = 28;
    private static final int SHOP_GIAY_NU = 29;

    private static final AutoDungHopStatus AUTO_STATUS = new AutoDungHopStatus();
    private static boolean running = false;
    private static Thread thread;
    private static String currentText = "";
    private static String lastBuyPhoiError = "";
    private static boolean reuseDungHopBox = false;
    private static boolean reusedCurrentBox = false;
    private static int activeType = -1;
    private static int activeSys = -999;
    private static int activeGender = -99;
    private static int activeLevelX = -1;

    public static void start() {
        if (running) {
            GameScr.chatPopup("Auto dung hợp đang chạy");
            return;
        }

        FormAutoDungHop.load();
        running = true;
        clearActive();
        setAutoStatus();
        thread = new Thread(new AutoDungHop());
        thread.start();
        GameScr.chatPopup(getAutoText());
    }

    public static void stop() {
        if (!running) {
            return;
        }

        running = false;
        restoreAutoStatus();
        GameScr.chatPopup("Dừng auto dung hợp");
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
        if (FormAutoDungHop.HuntFullSixX) {
            return hasHuntRules() ? "Săn dung hợp 6x chỉ số" : "Săn dung hợp 6x đủ dòng";
        }
        return "Dung hợp -> " + FormAutoDungHop.TargetLevelX + "x";
    }

    public void run() {
        int attempts = 0;
        try {
            while (running) {
                Char me = Char.getMyChar();
                if (me == null || me.arrItemBag == null) {
                    sleep(1000L);
                    continue;
                }
                if (!me.isHuman) {
                    finish("Auto dung hợp chỉ chạy ở nhân vật chính");
                    break;
                }

                if (FormAutoDungHop.HuntFullSixX) {
                    int result = doHuntSixXAttempt(me, attempts);
                    if (result < 0) {
                        break;
                    }
                    attempts += result;
                    sleep(FormAutoDungHop.DelayMs);
                    continue;
                }

                Item main = resolveMainItem(me);
                if (main == null) {
                    finish(FormAutoDungHop.MainIndex >= 0 ? "Không thấy đồ chính dung hợp" : "Hết đồ dung hợp hợp lệ");
                    break;
                }

                int targetX = FormAutoDungHop.TargetLevelX;
                int levelX = levelX(main);
                if (levelX >= targetX) {
                    clearActive();
                    if (FormAutoDungHop.MainIndex >= 0) {
                        finish("Đồ chính đã đạt cấp yêu cầu");
                        break;
                    }
                    sleep(300L);
                    continue;
                }

                Item phoi = findPhoi(me, main);
                if (phoi == null) {
                    int needX = levelX + 1;
                    if (FormAutoDungHop.AutoBuyPhoi && (needX == 7 || needX == 8)) {
                        currentText = "Mua phôi " + needX + "x cho " + shortName(main.template.name);
                        if (tryBuyPhoi(main, needX)) {
                            sleep(FormAutoDungHop.DelayMs);
                            continue;
                        }
                        finish(lastBuyPhoiError.length() > 0 ? lastBuyPhoiError : "Không mua được phôi " + needX + "x");
                        break;
                    }
                    finish("Thiếu phôi " + needX + "x cùng loại/giới/hệ");
                    break;
                }

                Item protect = null;
                if (FormAutoDungHop.UseProtect) {
                    protect = findItemById(me, ITEM_BAO_HIEM_DUNG_HOP);
                    if (protect == null) {
                        finish("Thiếu bảo hiểm dung hợp 1214");
                        break;
                    }
                } else if (!hasMoney(me, main)) {
                    finish(FormAutoDungHop.HoldLine ? "Không đủ lượng nâng giữ dòng" : "Không đủ yên nâng thường");
                    break;
                }

                int cardNeed = numberCardUpgrade(main);
                if (!hasCards(me, cardNeed)) {
                    finish("Thiếu " + cardNeed + " thẻ dung hợp mỗi loại");
                    break;
                }

                if (attempts >= FormAutoDungHop.MaxAttempts) {
                    finish("Dung hợp đạt số lần tối đa");
                    break;
                }

                setActive(main);
                currentText = "Dung hợp " + shortName(main.template.name) + " " + levelX + "x->" + targetX + "x";
                if (!doUpgradeAttempt(main, phoi, protect)) {
                    finish("Không mở được nâng cấp dung hợp");
                    break;
                }
                attempts++;
                sleep(FormAutoDungHop.DelayMs * 2L);
            }
        } catch (Exception e) {
            finish("Lỗi auto dung hợp");
        } finally {
            restoreAutoStatus();
        }
    }

    private static int doHuntSixXAttempt(Char me, int attempts) {
        if (attempts >= FormAutoDungHop.MaxAttempts) {
            finish("Săn 6x đạt số lần tối đa");
            return -1;
        }
        if (me == null || me.arrItemBag == null) {
            return 0;
        }
        if (!hasCards(me, FormAutoDungHop.HuntSixXCardNeed)) {
            finish("Thiếu " + FormAutoDungHop.HuntSixXCardNeed + " thẻ dung hợp mỗi loại");
            return -1;
        }

        int[] emptySlots = getEmptyBagSlots(me);
        if (emptySlots.length == 0) {
            finish("Hành trang đầy, không thể săn 6x");
            return -1;
        }

        currentText = "Săn đồ 6x lần " + (attempts + 1) + "/" + FormAutoDungHop.MaxAttempts;
        if (!openCreateSixXMenu()) {
            finish("Không mở được menu tạo 6x");
            return -1;
        }

        Item created = waitCreatedSixXItem(emptySlots, 15000L);
        if (created == null) {
            finish("Không thấy đồ dung hợp 6x mới");
            return -1;
        }

        if (isProtectedSixXFusionItem(created)) {
            finish("Đã săn được đồ 6x đạt yêu cầu index " + created.indexUI + " " + sixXSummary(created));
            return -1;
        }

        String reason = sixXFailText(created);
        if (!FormAutoDungHop.SellBadSixX) {
            finish("Đồ 6x chưa đạt index " + created.indexUI + ": " + reason);
            return -1;
        }

        currentText = "Bán đồ 6x chưa đạt: " + reason;
        if (!sellBadSixX(created)) {
            finish("Không bán được đồ 6x chưa đạt");
            return -1;
        }

        return 1;
    }

    private static boolean openCreateSixXMenu() {
        try {
            if (!ensureNpc(FormAutoDungHop.HuntSixXNpcId)) {
                return false;
            }
            closeClientPopups();
            Service.getInstance().openMenu(FormAutoDungHop.HuntSixXNpcId);
            sleepDelay();
            int[] path = parsePath(FormAutoDungHop.HuntSixXMenuPath);
            if (path == null || path.length == 0) {
                return false;
            }
            for (int i = 0; running && i < path.length; i++) {
                Service.getInstance().menu(FormAutoDungHop.HuntSixXNpcId, path[i], 0);
                sleepDelay();
            }
            closeClientPopups();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static int[] getEmptyBagSlots(Char me) {
        if (me == null || me.arrItemBag == null) {
            return new int[0];
        }
        int count = 0;
        for (int i = 0; i < me.arrItemBag.length; i++) {
            if (me.arrItemBag[i] == null) {
                count++;
            }
        }
        int[] slots = new int[count];
        int index = 0;
        for (int i = 0; i < me.arrItemBag.length; i++) {
            if (me.arrItemBag[i] == null) {
                slots[index++] = i;
            }
        }
        return slots;
    }

    private static Item waitCreatedSixXItem(int[] emptySlots, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Item item = findCreatedSixXItem(emptySlots);
            if (item != null) {
                return item;
            }
            sleep(150L);
        }
        return null;
    }

    private static Item findCreatedSixXItem(int[] emptySlots) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null || emptySlots == null) {
            return null;
        }
        for (int i = 0; i < emptySlots.length; i++) {
            int slot = emptySlots[i];
            if (slot < 0 || slot >= me.arrItemBag.length) {
                continue;
            }
            Item item = me.arrItemBag[slot];
            if (!isSixXBodyCandidate(item)) {
                continue;
            }
            if (ensureBagItemInfo(item) && isSixXFusionItem(item)) {
                return item;
            }
        }
        return null;
    }

    private static boolean ensureBagItemInfo(Item item) {
        try {
            if (item == null) {
                return false;
            }
            if (item.options != null && item.options.size() > 0) {
                return true;
            }
            Service.getInstance().requestItemInfo(item.typeUI, item.indexUI);
            long start = System.currentTimeMillis();
            while (running && System.currentTimeMillis() - start < 2500L) {
                if (item.options != null && item.options.size() > 0) {
                    return true;
                }
                sleep(100L);
            }
            return item.options != null && item.options.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isSixXBodyCandidate(Item item) {
        return item != null
                && item.template != null
                && item.template.type >= 0
                && item.template.type <= 9
                && item.template.level >= 60
                && item.template.level <= 69;
    }

    private static boolean isSixXFusionItem(Item item) {
        return isSixXBodyCandidate(item)
                && getOptionParam(item, OPTION_DO_BEN) >= 0
                && getOptionParam(item, OPTION_GIA_TRI_TANG) >= 0;
    }

    private static boolean isFullSixXFusionItem(Item item) {
        return isSixXFusionItem(item)
                && optionCount(item) == FULL_SIX_X_TOTAL_OPTION_COUNT
                && baseFusionOptionCount(item) == FULL_SIX_X_BASE_OPTION_COUNT;
    }

    public static boolean isProtectedSixXFusionItem(Item item) {
        FormAutoDungHop.load();
        if (!isFullSixXFusionItem(item)) {
            return false;
        }
        return !hasHuntRules() || matchesHuntRules(item);
    }

    private static boolean sellBadSixX(Item item) {
        try {
            if (item == null || item.template == null) {
                return false;
            }
            int index = item.indexUI;
            int itemId = item.template.id;
            Item cur = getBagItem(index);
            if (cur == null || cur.template == null || cur.template.id != itemId) {
                return true;
            }
            if (!ensureBagItemInfo(cur)) {
                return false;
            }
            if (isProtectedSixXFusionItem(cur)) {
                return false;
            }
            Service.getInstance().saleItem1(index, 1);
            return waitBagIndexChanged(index, itemId, 8000L);
        } catch (Exception e) {
            return false;
        }
    }

    private static Item getBagItem(int index) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null || index < 0 || index >= me.arrItemBag.length) {
            return null;
        }
        return me.arrItemBag[index];
    }

    private static String sixXFailText(Item item) {
        int count = optionCount(item);
        int baseCount = baseFusionOptionCount(item);
        if (!isFullSixXFusionItem(item)) {
            return count + "/13, gốc " + baseCount + "/11";
        }
        return hasHuntRules() ? "sai chỉ số " + ruleProgress(item) : "chưa đạt";
    }

    private static String sixXSummary(Item item) {
        if (!hasHuntRules()) {
            return "13/13";
        }
        return ruleProgress(item);
    }

    private static boolean hasHuntRules() {
        return FormAutoDungHop.HuntSixXByOption
                && FormAutoDungHop.HuntSixXRuleText != null
                && FormAutoDungHop.HuntSixXRuleText.trim().length() > 0;
    }

    private static boolean matchesHuntRules(Item item) {
        if (item == null || item.options == null) {
            return false;
        }

        String[] parts = splitRuleParts(FormAutoDungHop.HuntSixXRuleText);
        int valid = 0;
        int ok = 0;
        for (int i = 0; i < parts.length; ++i) {
            Rule rule = parseRule(parts[i]);
            if (rule == null) {
                continue;
            }
            ++valid;
            int param = getBestOptionParam(item, rule.optionId);
            boolean pass = rule.onlyExists ? param != Integer.MIN_VALUE : param >= rule.minParam;
            if (pass) {
                ++ok;
                if (!FormAutoDungHop.HuntSixXRequireAll) {
                    return true;
                }
            } else if (FormAutoDungHop.HuntSixXRequireAll) {
                return false;
            }
        }

        return valid > 0 && ok == valid;
    }

    private static String ruleProgress(Item item) {
        String[] parts = splitRuleParts(FormAutoDungHop.HuntSixXRuleText);
        int valid = 0;
        int ok = 0;
        for (int i = 0; i < parts.length; ++i) {
            Rule rule = parseRule(parts[i]);
            if (rule == null) {
                continue;
            }
            ++valid;
            int param = getBestOptionParam(item, rule.optionId);
            if (rule.onlyExists ? param != Integer.MIN_VALUE : param >= rule.minParam) {
                ++ok;
            }
        }
        return ok + "/" + valid + " rule";
    }

    private static String[] splitRuleParts(String text) {
        if (text == null) {
            return new String[0];
        }
        return Code.splitString(text.replace(';', ','), ",");
    }

    private static Rule parseRule(String text) {
        try {
            if (text == null) {
                return null;
            }
            String s = text.trim();
            if (s.length() == 0) {
                return null;
            }

            int pos = s.indexOf(">=");
            if (pos >= 0) {
                return new Rule(Integer.parseInt(s.substring(0, pos).trim()), Integer.parseInt(s.substring(pos + 2).trim()), false);
            }
            pos = s.indexOf("=");
            if (pos >= 0) {
                return new Rule(Integer.parseInt(s.substring(0, pos).trim()), Integer.parseInt(s.substring(pos + 1).trim()), false);
            }
            pos = s.indexOf(":");
            if (pos >= 0) {
                return new Rule(Integer.parseInt(s.substring(0, pos).trim()), Integer.parseInt(s.substring(pos + 1).trim()), false);
            }
            return new Rule(Integer.parseInt(s), 0, true);
        } catch (Exception e) {
            return null;
        }
    }

    private static int getBestOptionParam(Item item, int optionId) {
        if (item == null || item.options == null) {
            return Integer.MIN_VALUE;
        }
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < item.options.size(); i++) {
            ItemOption opt = (ItemOption) item.options.elementAt(i);
            if (opt != null && opt.optionTemplate != null && opt.optionTemplate.id == optionId && opt.param > best) {
                best = opt.param;
            }
        }
        return best;
    }

    private static int optionCount(Item item) {
        return item != null && item.options != null ? item.options.size() : 0;
    }

    private static int baseFusionOptionCount(Item item) {
        int count = 0;
        if (item == null || item.options == null) {
            return 0;
        }
        for (int i = 0; i < item.options.size(); i++) {
            ItemOption opt = (ItemOption) item.options.elementAt(i);
            if (opt != null && opt.optionTemplate != null
                    && opt.optionTemplate.id != OPTION_DO_BEN
                    && opt.optionTemplate.id != OPTION_GIA_TRI_TANG) {
                count++;
            }
        }
        return count;
    }

    private static boolean waitBagIndexChanged(int index, int itemId, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null || index < 0 || index >= me.arrItemBag.length) {
                return true;
            }
            Item item = me.arrItemBag[index];
            if (item == null || item.template == null || item.template.id != itemId) {
                return true;
            }
            sleep(100L);
        }
        return false;
    }

    private static boolean doUpgradeAttempt(Item main, Item phoi, Item protect) {
        try {
            if (!openDungHopBox(main)) {
                return false;
            }

            if (!reusedCurrentBox) {
                Service.getInstance().e(main.indexUI);
                if (!waitBoxSlot(8, main, 5000L)) {
                    reuseDungHopBox = false;
                    return false;
                }
            }
            Service.getInstance().e(phoi.indexUI);
            if (!waitBoxSlot(9, phoi, 5000L)) {
                reuseDungHopBox = false;
                return false;
            }
            if (protect != null) {
                Service.getInstance().e(protect.indexUI);
                if (!waitBoxSlot(6, protect, 5000L)) {
                    reuseDungHopBox = false;
                    return false;
                }
            }

            Service.getInstance().d(8);
            sleepDelay();
            Service.getInstance().menu(0, FormAutoDungHop.HoldLine ? 1 : 0, 0);
            sleepDelay();
            Service.getInstance().o(CONFIRM_ID);
            closeClientPopups();
            reuseDungHopBox = waitReusableBoxAfterFail(main, 5000L);
            return true;
        } catch (Exception e) {
            reuseDungHopBox = false;
            return false;
        }
    }

    private static boolean openDungHopBox(Item main) {
        try {
            reusedCurrentBox = false;
            if (canReuseDungHopBox(main)) {
                reusedCurrentBox = true;
                closeClientPopups();
                return true;
            }
            reuseDungHopBox = false;
            if (!ensureKanata()) {
                return false;
            }
            resetStaleCommandBox();
            Service.getInstance().openMenu(NPC_KANATA);
            sleepDelay();
            int[] path = parsePath(FormAutoDungHop.MenuPath);
            if (path == null || path.length == 0) {
                return false;
            }
            for (int i = 0; running && i < path.length; i++) {
                Service.getInstance().menu(0, path[i], 0);
                sleepDelay();
            }
            return waitDungHopCommandBox(8000L);
        } catch (Exception e) {
            reuseDungHopBox = false;
            return false;
        }
    }

    private static boolean canReuseDungHopBox(Item main) {
        try {
            if (!reuseDungHopBox || main == null || main.template == null || !isDungHopCommandBoxReady()) {
                return false;
            }
            Char me = Char.getMyChar();
            if (me == null || me.arrItemBox == null || me.arrItemBox.length <= 9) {
                return false;
            }
            Item boxMain = me.arrItemBox[8];
            Item boxPhoi = me.arrItemBox[9];
            return boxPhoi == null && sameFusionMain(boxMain, main) && isValidMain(boxMain);
        } catch (Exception e) {
            return false;
        }
    }

    private static void resetStaleCommandBox() {
        try {
            closeClientPopups();
            GameScr.svTitle = "";
            GameScr.svAction = "";
            if (Char.getMyChar() != null) {
                Char.getMyChar().arrItemBox = null;
            }
        } catch (Exception e) {
        }
    }

    private static boolean waitDungHopCommandBox(long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            if (isDungHopCommandBoxReady()) {
                closeClientPopups();
                return true;
            }
            sleep(100L);
        }
        return false;
    }

    private static boolean isDungHopCommandBoxReady() {
        try {
            Char me = Char.getMyChar();
            return me != null && me.arrItemBox != null && me.arrItemBox.length >= 10
                    && GameScr.svTitle != null && GameScr.svTitle.length() > 0
                    && GameScr.svAction != null && GameScr.svAction.length() > 0
                    && isKanataDungHopTitle(GameScr.svTitle);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isKanataDungHopTitle(String title) {
        if (title == null) {
            return false;
        }
        String lower = title.toLowerCase();
        return lower.indexOf("nâng") >= 0 || lower.indexOf("nang") >= 0;
    }

    private static boolean waitBoxSlot(int slot, Item expected, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            try {
                Char me = Char.getMyChar();
                if (me != null && me.arrItemBox != null && slot >= 0 && slot < me.arrItemBox.length) {
                    Item item = me.arrItemBox[slot];
                    if (sameTemplate(item, expected)) {
                        return true;
                    }
                }
            } catch (Exception e) {
            }
            sleep(100L);
        }
        return false;
    }

    private static boolean sameTemplate(Item item, Item expected) {
        return item != null && expected != null
                && item.template != null && expected.template != null
                && item.template.id == expected.template.id;
    }

    private static boolean sameFusionMain(Item item, Item expected) {
        return item != null && expected != null
                && item.template != null && expected.template != null
                && item.template.id == expected.template.id
                && item.template.type == expected.template.type
                && item.template.gender == expected.template.gender
                && item.sys == expected.sys;
    }

    private static boolean waitReusableBoxAfterFail(Item main, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            try {
                Char me = Char.getMyChar();
                if (me != null && me.arrItemBox != null && me.arrItemBox.length > 9) {
                    Item boxMain = me.arrItemBox[8];
                    Item boxPhoi = me.arrItemBox[9];
                    if (boxMain == null) {
                        return false;
                    }
                    if (boxPhoi == null && sameFusionMain(boxMain, main) && isValidMain(boxMain)) {
                        return true;
                    }
                }
            } catch (Exception e) {
            }
            sleep(100L);
        }
        return false;
    }

    private static boolean ensureKanata() {
        return ensureNpc(NPC_KANATA);
    }

    private static boolean ensureNpc(int npcId) {
        try {
            if (FormAutoDungHop.AutoGoKanata && !TileMap.isTruong(TileMap.mapID)) {
                TileMap.direction(1);
                long waitMap = System.currentTimeMillis();
                while (running && !TileMap.isTruong(TileMap.mapID) && System.currentTimeMillis() - waitMap < 25000L) {
                    sleep(200L);
                }
            }

            Npc npc = waitNpc(npcId, 6000L);
            if (npc == null) {
                return false;
            }
            Char.charMove(npc.cx, npc.cy);
            waitMove(npc.cx, npc.cy, 5000L);
            Char.getMyChar().npcFocus = npc;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Item resolveMainItem(Char me) {
        Item active = findActiveMain(me);
        if (active != null) {
            return active;
        }
        clearActive();

        if (FormAutoDungHop.MainIndex >= 0) {
            if (FormAutoDungHop.MainIndex >= me.arrItemBag.length) {
                return null;
            }
            Item item = me.arrItemBag[FormAutoDungHop.MainIndex];
            if (isValidMain(item)) {
                setActive(item);
                return item;
            }
            return null;
        }

        for (int i = 0; i < me.arrItemBag.length; i++) {
            Item item = me.arrItemBag[i];
            if (isValidMain(item) && levelX(item) < FormAutoDungHop.TargetLevelX) {
                setActive(item);
                return item;
            }
        }
        return null;
    }

    private static Item findActiveMain(Char me) {
        if (activeType < 0) {
            return null;
        }
        Item best = null;
        for (int i = 0; i < me.arrItemBag.length; i++) {
            Item item = me.arrItemBag[i];
            if (isValidMain(item)
                    && item.template.type == activeType
                    && item.sys == activeSys
                    && item.template.gender == activeGender
                    && levelX(item) >= activeLevelX
                    && levelX(item) < FormAutoDungHop.TargetLevelX) {
                if (best == null || levelX(item) > levelX(best)) {
                    best = item;
                }
            }
        }
        if (best != null) {
            setActive(best);
        }
        return best;
    }

    private static void setActive(Item item) {
        if (item == null || item.template == null) {
            return;
        }
        activeType = item.template.type;
        activeSys = item.sys;
        activeGender = item.template.gender;
        activeLevelX = levelX(item);
    }

    private static void clearActive() {
        activeType = -1;
        activeSys = -999;
        activeGender = -99;
        activeLevelX = -1;
        currentText = "";
        reuseDungHopBox = false;
        reusedCurrentBox = false;
    }

    private static boolean isValidMain(Item item) {
        return item != null
                && item.template != null
                && (item.isTypeClothe() || item.isTypeAdorn() || item.isTypeWeapon())
                && item.upgrade == 0
                && getOptionParam(item, OPTION_DO_BEN) > 0
                && !hasBadFusionOption(item);
    }

    private static boolean isValidPhoi(Item item, Item main) {
        if (item == null || item.template == null || main == null || main.template == null) {
            return false;
        }
        if (!(item.isTypeClothe() || item.isTypeAdorn() || item.isTypeWeapon())) {
            return false;
        }
        if (item.upgrade > 0 || getOptionParam(item, OPTION_DO_BEN) >= 0 || hasBadFusionOption(item)) {
            return false;
        }
        return item.template.type == main.template.type
                && item.template.gender == main.template.gender
                && item.sys == main.sys
                && levelX(item) == levelX(main) + 1;
    }

    private static boolean hasBadFusionOption(Item item) {
        return item.hasOption(OPTION_DO_TINH_LUYEN)
                || item.hasOption(OPTION_NGUONG_KHAI_HOA)
                || item.hasOption(OPTION_HUYEN_TINH_NGOC)
                || item.hasOption(OPTION_HUYET_NGOC)
                || item.hasOption(OPTION_LAM_TINH_NGOC)
                || item.hasOption(OPTION_LUC_NGOC);
    }

    private static Item findPhoi(Char me, Item main) {
        Item best = null;
        int bestScore = -1;
        for (int i = 0; i < me.arrItemBag.length; i++) {
            Item item = me.arrItemBag[i];
            if (isValidPhoi(item, main)) {
                if (!FormAutoDungHop.HoldLine) {
                    return item;
                }
                int score = scorePhoi(item, main);
                if (best == null || score > bestScore) {
                    best = item;
                    bestScore = score;
                }
            }
        }
        return best;
    }

    private static boolean tryBuyPhoi(Item main, int needX) {
        lastBuyPhoiError = "";
        reuseDungHopBox = false;
        reusedCurrentBox = false;
        try {
            if (main == null || main.template == null) {
                lastBuyPhoiError = "Không xác định được đồ chính để mua phôi";
                return false;
            }
            if (Char.countNullSlot() <= 0) {
                lastBuyPhoiError = "Hành trang đầy, không thể mua phôi";
                return false;
            }
            int shopType = shopTypeFor(main);
            if (shopType < 0) {
                lastBuyPhoiError = "Không xác định được shop mua phôi";
                return false;
            }
            if (!goSchoolForPhoi()) {
                lastBuyPhoiError = "Không về được trường để mua phôi";
                return false;
            }

            GameScr.chatPopup("Tự mua phôi " + needX + "x");
            requestShop(shopType);
            Item shopItem = findShopPhoi(shopType, main, needX);
            if (shopItem == null) {
                sleep(700L);
                requestShop(shopType);
                shopItem = findShopPhoi(shopType, main, needX);
            }
            if (shopItem == null) {
                lastBuyPhoiError = "Không thấy phôi " + needX + "x cùng loại/giới/hệ trong shop";
                restoreGameMenu();
                return false;
            }

            Service.getInstance().buyItem1(shopItem.typeUI, shopItem.indexUI, 1);
            if (waitPhoiInBag(main, 6000L)) {
                restoreGameMenu();
                GameScr.chatPopup("Đã mua phôi " + needX + "x");
                return true;
            }
            Service.getInstance().viewInfo(Char.getMyChar().charName);
            sleep(800L);
            restoreGameMenu();
            if (waitPhoiInBag(main, 2500L)) {
                GameScr.chatPopup("Đã mua phôi " + needX + "x");
                return true;
            }
            lastBuyPhoiError = "Mua phôi " + needX + "x thất bại";
            return false;
        } catch (Exception e) {
            lastBuyPhoiError = "Lỗi khi mua phôi dung hợp";
            restoreGameMenu();
            return false;
        }
    }

    private static boolean goSchoolForPhoi() {
        try {
            if (TileMap.isHang(TileMap.mapID) || TileMap.isClanDun()) {
                return false;
            }
            if (!TileMap.isTruong(TileMap.mapID)) {
                TileMap.direction(1);
                long start = System.currentTimeMillis();
                while (running && !TileMap.isTruong(TileMap.mapID) && System.currentTimeMillis() - start < 20000L) {
                    sleep(300L);
                }
                if (!TileMap.isTruong(TileMap.mapID)) {
                    Auto.goTruongIfNeeded();
                }
            }
            long wait = System.currentTimeMillis();
            while (running && !TileMap.isTruong(TileMap.mapID) && System.currentTimeMillis() - wait < 25000L) {
                sleep(300L);
            }
            return TileMap.isTruong(TileMap.mapID);
        } catch (Exception e) {
            return false;
        }
    }

    private static void requestShop(int shopType) {
        try {
            GameScr.getInstance().openUI(shopType);
            Service.getInstance().requestItem(shopType);
            sleep(900L);
        } catch (Exception e) {
        }
    }

    private static Item findShopPhoi(int shopType, Item main, int needX) {
        Item[] arr = getShopArray(shopType);
        if (arr == null) {
            return null;
        }
        for (int i = 0; running && i < arr.length; i++) {
            Item item = arr[i];
            if (!isShopPhoiCandidate(item, main, needX, false)) {
                continue;
            }
            if (!ensureShopItemInfo(item)) {
                continue;
            }
            if (isShopPhoiCandidate(item, main, needX, true)) {
                return item;
            }
        }
        return null;
    }

    private static boolean ensureShopItemInfo(Item item) {
        try {
            if (item == null || item.s) {
                return item != null;
            }
            Service.getInstance().requestItemInfo(item.typeUI, item.indexUI);
            long start = System.currentTimeMillis();
            while (running && !item.s && System.currentTimeMillis() - start < 1800L) {
                sleep(100L);
            }
            return item.s;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isShopPhoiCandidate(Item item, Item main, int needX, boolean checkSys) {
        if (item == null || item.template == null || main == null || main.template == null) {
            return false;
        }
        if (item.template.type != main.template.type || item.template.gender != main.template.gender) {
            return false;
        }
        if (item.template.level / 10 != needX) {
            return false;
        }
        return !checkSys || item.sys == main.sys;
    }

    private static boolean waitPhoiInBag(Item main, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Char me = Char.getMyChar();
            if (me != null && me.arrItemBag != null && findPhoi(me, main) != null) {
                return true;
            }
            sleep(250L);
        }
        return false;
    }

    private static int shopTypeFor(Item item) {
        if (item == null || item.template == null) {
            return -1;
        }
        int gender = item.template.gender;
        switch (item.template.type) {
            case 0:
                return gender == 1 ? SHOP_NON_NAM : SHOP_NON_NU;
            case 1:
                return SHOP_VU_KHI;
            case 2:
                return gender == 1 ? SHOP_AO_NAM : SHOP_AO_NU;
            case 3:
                return SHOP_LIEN;
            case 4:
                return gender == 1 ? SHOP_GANG_NAM : SHOP_GANG_NU;
            case 5:
                return SHOP_NHAN;
            case 6:
                return gender == 1 ? SHOP_QUAN_NAM : SHOP_QUAN_NU;
            case 7:
                return SHOP_NGOC_BOI;
            case 8:
                return gender == 1 ? SHOP_GIAY_NAM : SHOP_GIAY_NU;
            case 9:
                return SHOP_PHU;
        }
        return -1;
    }

    private static Item[] getShopArray(int shopType) {
        switch (shopType) {
            case SHOP_VU_KHI:
                return GameScr.arrItemWeapon;
            case SHOP_LIEN:
                return GameScr.arrItemLien;
            case SHOP_NHAN:
                return GameScr.arrItemNhan;
            case SHOP_NGOC_BOI:
                return GameScr.arrItemNgocBoi;
            case SHOP_PHU:
                return GameScr.arrItemPhu;
            case SHOP_NON_NAM:
                return GameScr.arrItemNonNam;
            case SHOP_NON_NU:
                return GameScr.arrItemNonNu;
            case SHOP_AO_NAM:
                return GameScr.arrItemAoNam;
            case SHOP_AO_NU:
                return GameScr.arrItemAoNu;
            case SHOP_GANG_NAM:
                return GameScr.arrItemGangTayNam;
            case SHOP_GANG_NU:
                return GameScr.arrItemGangTayNu;
            case SHOP_QUAN_NAM:
                return GameScr.arrItemQuanNam;
            case SHOP_QUAN_NU:
                return GameScr.arrItemQuanNu;
            case SHOP_GIAY_NAM:
                return GameScr.arrItemGiayNam;
            case SHOP_GIAY_NU:
                return GameScr.arrItemGiayNu;
        }
        return null;
    }

    private static int scorePhoi(Item phoi, Item main) {
        int score = 0;
        if (main.options == null || phoi.options == null) {
            return 0;
        }
        for (int i = 0; i < main.options.size(); i++) {
            ItemOption opt = (ItemOption) main.options.elementAt(i);
            if (opt != null && opt.optionTemplate != null
                    && opt.optionTemplate.id != OPTION_DO_BEN
                    && opt.optionTemplate.id != OPTION_GIA_TRI_TANG) {
                int value = getOptionParam(phoi, opt.optionTemplate.id);
                if (value > 0) {
                    score += value;
                }
            }
        }
        return score;
    }

    private static boolean hasCards(Char me, int need) {
        for (int id = ITEM_THE_DUNG_HOP_1; id <= ITEM_THE_DUNG_HOP_6; id++) {
            if (countItem(me, id) < need) {
                return false;
            }
        }
        return true;
    }

    private static int countItem(Char me, int id) {
        int count = 0;
        if (me == null || me.arrItemBag == null) {
            return 0;
        }
        for (int i = 0; i < me.arrItemBag.length; i++) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template != null && item.template.id == id) {
                count += item.quantity <= 0 ? 1 : item.quantity;
            }
        }
        return count;
    }

    private static Item findItemById(Char me, int id) {
        if (me == null || me.arrItemBag == null) {
            return null;
        }
        for (int i = 0; i < me.arrItemBag.length; i++) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template != null && item.template.id == id) {
                return item;
            }
        }
        return null;
    }

    private static boolean hasMoney(Char me, Item main) {
        int levelX = levelX(main);
        if (FormAutoDungHop.HoldLine) {
            return me.luong >= levelX * 1000;
        }
        return me.yen >= levelX * 1000000;
    }

    private static int numberCardUpgrade(Item item) {
        int x = levelX(item);
        if (x == 7) {
            return 7;
        }
        if (x == 8) {
            return 9;
        }
        if (x == 9) {
            return 11;
        }
        if (x == 10) {
            return 13;
        }
        if (x == 11) {
            return 15;
        }
        return 5;
    }

    private static int levelX(Item item) {
        if (item == null || item.template == null) {
            return 0;
        }
        return item.template.level / 10;
    }

    private static int getOptionParam(Item item, int optionId) {
        if (item == null || item.options == null) {
            return -1;
        }
        for (int i = 0; i < item.options.size(); i++) {
            ItemOption opt = (ItemOption) item.options.elementAt(i);
            if (opt != null && opt.optionTemplate != null && opt.optionTemplate.id == optionId) {
                return opt.param;
            }
        }
        return -1;
    }

    private static int[] parsePath(String text) {
        try {
            if (text == null || text.trim().length() == 0) {
                return null;
            }
            MyVector values = new MyVector();
            String s = text.trim() + ",";
            int start = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ',' || c == ';' || c == '-') {
                    String part = s.substring(start, i).trim();
                    if (part.length() > 0) {
                        values.addElement(new Integer(Integer.parseInt(part)));
                    }
                    start = i + 1;
                }
            }
            int[] result = new int[values.size()];
            for (int i = 0; i < values.size(); i++) {
                result[i] = ((Integer) values.elementAt(i)).intValue();
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private static Npc waitNpc(int npcId, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            try {
                if (GameScr.ah != null) {
                    for (int i = 0; i < GameScr.ah.size(); i++) {
                        Npc npc = (Npc) GameScr.ah.elementAt(i);
                        if (npc != null && npc.template != null && npc.template.npcTemplateId == npcId) {
                            return npc;
                        }
                    }
                }
            } catch (Exception e) {
            }
            sleep(100L);
        }
        return null;
    }

    private static void waitMove(int x, int y, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Char me = Char.getMyChar();
            if (me != null && Math.abs(me.cx - x) <= 36 && Math.abs(me.cy - y) <= 36) {
                return;
            }
            sleep(100L);
        }
    }

    private static void closeClientPopups() {
        try {
            GameCanvas.currentDialog = null;
            if (GameCanvas.menu != null) {
                GameCanvas.menu.showMenu = false;
            }
        } catch (Exception e) {
        }
    }

    private static void restoreGameMenu() {
        try {
            GameScr gameScr = GameScr.getInstance();
            if (gameScr != null) {
                gameScr.resetButton();
            }
            GameCanvas.setMaxTextLenght();
            closeClientPopups();
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

    private static void finish(String text) {
        running = false;
        GameScr.chatPopup(text);
    }

    private static void sleepDelay() {
        sleep(FormAutoDungHop.DelayMs);
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    private static String shortName(String text) {
        if (text == null) {
            return "";
        }
        return text.length() > 14 ? text.substring(0, 14) : text;
    }

    private static final class Rule {
        int optionId;
        int minParam;
        boolean onlyExists;

        Rule(int optionId, int minParam, boolean onlyExists) {
            this.optionId = optionId;
            this.minParam = minParam;
            this.onlyExists = onlyExists;
        }
    }
}

final class AutoDungHopStatus extends Auto {

    protected void run() {
        if (!AutoDungHop.isRunning()) {
            AutoDungHop.restoreAutoStatus();
        }
    }

    public String toString() {
        return AutoDungHop.getAutoText();
    }
}
