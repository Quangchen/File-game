import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class AutoDoiLongDen implements Runnable {

    private static final String STORE_NAME = "AutoDoiLongDenCfg";
    private static final int NPC_TIEN_NU = 33;
    private static final int PHOI_LONG_DEN = 1221;
    private static final int COIN_PER_EXCHANGE = 50000;
    private static final int GOLD_PER_EXCHANGE = 1000;
    private static final int CURRENCY_XU = 0;
    private static final int CURRENCY_LUONG = 1;
    private static final int[] BASE_LANTERN_IDS = new int[]{568, 569, 570, 571};
    private static final int FASHION_LANTERN_MIN = 1048;
    private static final int FASHION_LANTERN_MAX = 1055;
    private static final int REQUIRED_EMPTY_SLOT = 1;
    private static final int PRODUCER_RESERVE_EXTRA_SLOT = 1;
    private static final long BOX_TIMEOUT = 8000L;
    private static final long RESULT_TIMEOUT = 9000L;
    private static final long OPTION_TIMEOUT = 6000L;

    public static boolean enabled = false;
    public static boolean huntOption = false;
    public static boolean autoDeleteFail = true;
    public static boolean requireAll = true;
    public static boolean autoGoNpc = false;
    public static int delayMs = 1200;
    public static int minEmptySlot = 1;
    public static int currencyMode = CURRENCY_XU;
    public static String menuPathXu = "2";
    public static String menuPathLuong = "2,1";
    public static String ruleText = "87>=4500";

    private static boolean loaded = false;
    private static boolean running = false;
    private static boolean busy = false;
    private static boolean producerPaused = false;
    private static String status = "Tắt";
    private static int exchanged = 0;
    private static int kept = 0;
    private static int deleted = 0;
    private static long lastMoveNpcAt = 0L;

    public static void toggle() {
        load();
        if (running) {
            stop();
        } else {
            enabled = true;
            save();
            start();
        }
    }

    public static void start() {
        start(true);
    }

    private static void start(boolean showPopup) {
        load();
        if (running) {
            if (showPopup) {
                GameScr.chatPopup("Auto đổi lồng đèn đang chạy");
            }
            return;
        }

        running = true;
        enabled = true;
        (new Thread(new AutoDoiLongDen())).start();
        if (showPopup) {
            GameScr.chatPopup("Bật auto đổi lồng đèn");
        }
    }

    public static void update() {
        try {
            load();
            if (!enabled || running || !(GameCanvas.mScreen instanceof GameScr)) {
                return;
            }
            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null) {
                return;
            }
            start(false);
        } catch (Exception e) {
        }
    }

    public static void stop() {
        running = false;
        enabled = false;
        busy = false;
        producerPaused = false;
        save();
        status = "Tắt";
        GameScr.chatPopup("Dừng auto đổi lồng đèn");
    }

    public static boolean isRunning() {
        return running;
    }

    public static boolean isBusy() {
        return busy;
    }

    public static boolean shouldPauseProducers() {
        try {
            load();
            producerPaused = shouldReserveBag(true);
            return producerPaused;
        } catch (Exception e) {
            producerPaused = false;
            return false;
        }
    }

    public static boolean isProducerPaused() {
        return producerPaused;
    }

    public static String getStatusText() {
        load();
        return (running ? status : "Tắt") + " | đổi:" + exchanged + " giữ:" + kept + " xóa:" + deleted;
    }

    public static String getAutoText() {
        load();
        if (!enabled && !running) {
            return "";
        }

        return "Đổi LĐ: " + (running ? status : "chờ") + " đổi:" + exchanged + " giữ:" + kept + " xóa:" + deleted;
    }

    public final void run() {
        load();
        while (running && enabled && GameCanvas.mScreen instanceof GameScr) {
            try {
                if (!canWorkNow()) {
                    shouldPauseProducers();
                    if (producerPaused) {
                        sleepMs(300L);
                    } else {
                        sleepOrWakeOnReserve((long) getDelayMs());
                    }
                    continue;
                }

                doOneExchange();
                shouldPauseProducers();
                if (producerPaused) {
                    sleepMs(100L);
                } else {
                    sleepOrWakeOnReserve((long) getDelayMs());
                }
            } catch (Exception e) {
                status = "Lỗi auto đèn";
                busy = false;
                producerPaused = false;
                sleepMs(1000L);
            }
        }

        running = false;
        busy = false;
        producerPaused = false;
        status = "Tắt";
    }

    private static boolean canWorkNow() {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            status = "Chờ nhân vật";
            return false;
        }

        if (!me.isHuman) {
            status = "Chỉ đổi ở chủ thân";
            return false;
        }

        int requiredEmptySlot = getRequiredEmptySlot();
        if (Char.countNullSlot() < requiredEmptySlot) {
            status = "Thiếu ô trống " + Char.countNullSlot() + "/" + requiredEmptySlot;
            return false;
        }

        if (currencyMode == CURRENCY_LUONG) {
            if (me.luong < GOLD_PER_EXCHANGE) {
                status = "Thiếu lượng 1000";
                return false;
            }
        } else {
            if (me.xu < COIN_PER_EXCHANGE) {
                status = "Thiếu xu 50k";
                return false;
            }
        }

        if (Char.k(PHOI_LONG_DEN) <= 0) {
            status = "Thiếu phôi 1221";
            return false;
        }

        if (countBaseLantern() <= 0) {
            status = "Chờ lồng đèn 568-571";
            return false;
        }

        return true;
    }

    private static void doOneExchange() {
        busy = true;
        producerPaused = true;
        try {
            if (!canWorkNow()) {
                return;
            }

            if (!ensureNpcReady()) {
                return;
            }

            int[] before = snapshotBagIds();
            Item boxItem = openExchangeBoxAndFindItem();
            if (boxItem == null) {
                status = "Không mở được UI đổi đèn";
                return;
            }

            status = "Đổi lồng đèn index " + boxItem.indexUI;
            Service.getInstance().d(boxItem.indexUI);

            Item result = waitNewFashionLantern(before, RESULT_TIMEOUT);
            if (result == null) {
                status = "Chưa thấy đèn mới";
                Service.getInstance().viewInfo(Char.getMyChar().charName);
                return;
            }

            ++exchanged;
            handleResult(result);
        } finally {
            GameScr.hideCommandBoxUi();
            busy = false;
            producerPaused = shouldReserveBag(false);
        }
    }

    private static boolean shouldReserveBag(boolean updateStatus) {
        if (!enabled || !running || !(GameCanvas.mScreen instanceof GameScr)) {
            return false;
        }

        if (busy) {
            return true;
        }

        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null || !me.isHuman) {
            return false;
        }

        if (!hasEnoughCurrency(me) || Char.k(PHOI_LONG_DEN) <= 0 || countBaseLantern() <= 0) {
            return false;
        }

        int emptySlot = Char.countNullSlot();
        int requiredEmptySlot = getRequiredEmptySlot();
        if (emptySlot > requiredEmptySlot + PRODUCER_RESERVE_EXTRA_SLOT) {
            return false;
        }

        if (updateStatus) {
            if (emptySlot < requiredEmptySlot) {
                status = "Tạm dừng mở all: thiếu ô trống " + emptySlot + "/" + requiredEmptySlot;
            } else if (!busy) {
                status = "Tạm dừng mở all để đổi đèn";
            }
        }

        return true;
    }

    private static boolean hasEnoughCurrency(Char me) {
        if (me == null) {
            return false;
        }

        return currencyMode == CURRENCY_LUONG ? me.luong >= GOLD_PER_EXCHANGE : me.xu >= COIN_PER_EXCHANGE;
    }

    private static boolean ensureNpcReady() {
        Npc npc = GameScr.findNpc(NPC_TIEN_NU);
        if (npc == null && autoGoNpc) {
            status = "Tìm NPC Tiên Nữ";
            Auto.goTruongIfNeeded();
            long start = System.currentTimeMillis();
            while (running && System.currentTimeMillis() - start < 25000L && npc == null) {
                sleepMs(500L);
                npc = GameScr.findNpc(NPC_TIEN_NU);
            }
        }

        if (npc == null) {
            status = "Chờ NPC Tiên Nữ";
            return false;
        }

        Char me = Char.getMyChar();
        long now = System.currentTimeMillis();
        if (me != null && (Math.abs(me.cx - npc.cx) > 30 || Math.abs(me.cy - npc.cy) > 30) && now - lastMoveNpcAt >= 1000L) {
            lastMoveNpcAt = now;
            Char.charMove(npc.cx, npc.cy);
            waitMove(npc.cx, npc.cy, 5000L);
        }
        Char.getMyChar().npcFocus = npc;
        return true;
    }

    private static Item openExchangeBoxAndFindItem() {
        try {
            Char.getMyChar().arrItemBox = null;
            GameScr.hideCommandBoxUi();
            Service.getInstance().openMenu(NPC_TIEN_NU);
            sleepMs(350L);
            int[] path = parseMenuPath(getMenuPath());
            if (path.length == 0) {
                status = "Chưa cài menu đổi đèn";
                return null;
            }

            for (int i = 0; i < path.length; ++i) {
                Service.getInstance().menu(NPC_TIEN_NU, path[i], 0);
                Item item = waitExchangeBox(i == path.length - 1 ? BOX_TIMEOUT : 1400L);
                if (item != null) {
                    if (isRightCurrencyUi()) {
                        GameScr.hideCommandBoxUi();
                        return item;
                    }

                    status = currencyMode == CURRENCY_LUONG ? "UI không phải đổi lượng" : "UI không phải đổi xu";
                    return null;
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

    private static Item waitExchangeBox(long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Item item = findBaseLanternInBox();
            if (item != null) {
                return item;
            }
            GameScr.hideCommandBoxUi();
            sleepMs(100L);
        }
        return null;
    }

    private static String getMenuPath() {
        return currencyMode == CURRENCY_LUONG ? menuPathLuong : menuPathXu;
    }

    private static int[] parseMenuPath(String text) {
        if (text == null) {
            return new int[0];
        }

        String[] parts = Code.splitString(text, ",");
        int[] temp = new int[parts.length];
        int count = 0;
        for (int i = 0; i < parts.length; ++i) {
            try {
                String part = parts[i].trim();
                if (part.length() > 0) {
                    temp[count++] = Integer.parseInt(part);
                }
            } catch (Exception e) {
            }
        }

        int[] result = new int[count];
        System.arraycopy(temp, 0, result, 0, count);
        return result;
    }

    private static boolean isRightCurrencyUi() {
        String title = GameScr.svTitle == null ? "" : GameScr.svTitle.toLowerCase();
        boolean luongTitle = title.indexOf("lượng") >= 0 || title.indexOf("luong") >= 0 || title.indexOf("1000") >= 0;
        return currencyMode == CURRENCY_LUONG ? luongTitle : !luongTitle;
    }

    private static Item findBaseLanternInBox() {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBox == null) {
            return null;
        }

        for (int i = 0; i < me.arrItemBox.length; ++i) {
            Item item = me.arrItemBox[i];
            if (isBaseLantern(item)) {
                return item;
            }
        }

        return null;
    }

    private static void handleResult(Item result) {
        if (!huntOption || ruleText == null || ruleText.trim().length() == 0) {
            ++kept;
            status = "Giữ đèn " + getItemId(result) + " (không lọc)";
            return;
        }

        if (!waitItemOptions(result, OPTION_TIMEOUT)) {
            ++kept;
            status = "Chưa đọc option, giữ đèn " + getItemId(result);
            return;
        }

        result = getBagItem(result.indexUI);
        if (!isFashionLantern(result)) {
            status = "Đèn mới đã đổi vị trí";
            return;
        }

        if (matchesRules(result)) {
            ++kept;
            status = "Giữ đèn đạt CS " + getItemId(result);
            return;
        }

        if (!autoDeleteFail) {
            ++kept;
            status = "Đèn không đạt, giữ lại";
            return;
        }

        if (deleteExactResult(result)) {
            ++deleted;
            status = "Xóa đèn không đạt " + getItemId(result);
        } else {
            status = "Không xóa được đèn " + getItemId(result);
        }
    }

    private static boolean waitItemOptions(Item item, long timeout) {
        long start = System.currentTimeMillis();
        long lastRequest = 0L;
        while (running && System.currentTimeMillis() - start < timeout) {
            Item cur = getBagItem(item.indexUI);
            if (cur == null || !isFashionLantern(cur)) {
                return false;
            }
            if (cur.options != null && cur.options.size() > 0) {
                return true;
            }

            long now = System.currentTimeMillis();
            if (!cur.s && now - lastRequest >= 700L) {
                cur.t = now;
                lastRequest = now;
                Service.getInstance().requestItemInfo(cur.typeUI, cur.indexUI);
            }
            sleepMs(100L);
        }

        Item cur = getBagItem(item.indexUI);
        return cur != null && cur.options != null && cur.options.size() > 0;
    }

    private static boolean deleteExactResult(Item item) {
        Item cur = getBagItem(item.indexUI);
        if (cur == null || !isFashionLantern(cur) || getItemId(cur) != getItemId(item)) {
            return false;
        }

        if (!waitItemOptions(cur, 2000L) || matchesRules(getBagItem(cur.indexUI))) {
            return false;
        }

        int index = cur.indexUI;
        Service.getInstance().saleItem1(index, 1);
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < 5000L) {
            Item nowItem = getBagItem(index);
            if (nowItem == null || !isFashionLantern(nowItem) || getItemId(nowItem) != getItemId(item)) {
                return true;
            }
            sleepMs(100L);
        }

        Service.getInstance().viewInfo(Char.getMyChar().charName);
        return false;
    }

    private static boolean matchesRules(Item item) {
        if (item == null || item.options == null) {
            return false;
        }

        String[] parts = Code.splitString(ruleText, ",");
        int valid = 0;
        int ok = 0;
        for (int i = 0; i < parts.length; ++i) {
            Rule rule = parseRule(parts[i]);
            if (rule == null) {
                continue;
            }
            ++valid;
            int param = getOptionParam(item, rule.optionId);
            if (rule.onlyExists ? param != -2147483648 : param >= rule.minParam) {
                ++ok;
                if (!requireAll) {
                    return true;
                }
            } else if (requireAll) {
                return false;
            }
        }

        return valid > 0 && ok == valid;
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

    private static int getOptionParam(Item item, int optionId) {
        if (item == null || item.options == null) {
            return -2147483648;
        }

        int best = -2147483648;
        for (int i = 0; i < item.options.size(); ++i) {
            ItemOption option = (ItemOption)item.options.elementAt(i);
            if (option != null && option.optionTemplate != null && option.optionTemplate.id == optionId && option.param > best) {
                best = option.param;
            }
        }
        return best;
    }

    private static Item waitNewFashionLantern(int[] before, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Item item = findChangedFashionLantern(before);
            if (item != null) {
                return item;
            }
            sleepMs(100L);
        }
        return findChangedFashionLantern(before);
    }

    private static Item findChangedFashionLantern(int[] before) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return null;
        }

        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (isFashionLantern(item)) {
                int oldId = i < before.length ? before[i] : -1;
                if (oldId != getItemId(item)) {
                    return item;
                }
            }
        }
        return null;
    }

    private static int[] snapshotBagIds() {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null) {
            return new int[0];
        }

        int[] ids = new int[me.arrItemBag.length];
        for (int i = 0; i < ids.length; ++i) {
            ids[i] = getItemId(me.arrItemBag[i]);
        }
        return ids;
    }

    private static int countBaseLantern() {
        int count = 0;
        for (int i = 0; i < BASE_LANTERN_IDS.length; ++i) {
            count += Char.k(BASE_LANTERN_IDS[i]);
        }
        return count;
    }

    private static Item getBagItem(int index) {
        Char me = Char.getMyChar();
        if (me == null || me.arrItemBag == null || index < 0 || index >= me.arrItemBag.length) {
            return null;
        }
        return me.arrItemBag[index];
    }

    private static boolean isBaseLantern(Item item) {
        int id = getItemId(item);
        for (int i = 0; i < BASE_LANTERN_IDS.length; ++i) {
            if (BASE_LANTERN_IDS[i] == id) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFashionLantern(Item item) {
        int id = getItemId(item);
        return id >= FASHION_LANTERN_MIN && id <= FASHION_LANTERN_MAX;
    }

    private static int getItemId(Item item) {
        return item == null || item.template == null ? -1 : item.template.id;
    }

    private static int getDelayMs() {
        if (delayMs < 500) {
            return 500;
        }
        return delayMs > 60000 ? 60000 : delayMs;
    }

    private static int getRequiredEmptySlot() {
        return minEmptySlot < REQUIRED_EMPTY_SLOT ? REQUIRED_EMPTY_SLOT : minEmptySlot;
    }

    private static void waitMove(int x, int y, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            Char me = Char.getMyChar();
            if (me != null && Math.abs(me.cx - x) <= 30 && Math.abs(me.cy - y) <= 30) {
                return;
            }
            sleepMs(100L);
        }
    }

    private static void sleepOrWakeOnReserve(long ms) {
        long end = System.currentTimeMillis() + ms;
        while (running && enabled && GameCanvas.mScreen instanceof GameScr && System.currentTimeMillis() < end) {
            if (shouldReserveBag(false)) {
                producerPaused = true;
                return;
            }

            long remain = end - System.currentTimeMillis();
            sleepMs(remain < 200L ? remain : 200L);
        }
    }

    private static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    public static void save() {
        try {
            normalize();
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(enabled);
            dataout.writeBoolean(huntOption);
            dataout.writeBoolean(autoDeleteFail);
            dataout.writeBoolean(requireAll);
            dataout.writeBoolean(autoGoNpc);
            dataout.writeInt(delayMs);
            dataout.writeInt(minEmptySlot);
            dataout.writeUTF(ruleText == null ? "" : ruleText);
            dataout.writeInt(currencyMode);
            dataout.writeUTF(menuPathXu == null ? "" : menuPathXu);
            dataout.writeUTF(menuPathLuong == null ? "" : menuPathLuong);
            dataout.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            dataout.close();
            byteout.close();
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
            if (data != null) {
                ByteArrayInputStream bytein = new ByteArrayInputStream(data);
                DataInputStream datain = new DataInputStream(bytein);
                enabled = datain.readBoolean();
                huntOption = datain.readBoolean();
                autoDeleteFail = datain.readBoolean();
                requireAll = datain.readBoolean();
                autoGoNpc = datain.readBoolean();
                delayMs = datain.readInt();
                minEmptySlot = datain.readInt();
                ruleText = datain.readUTF();
                if (datain.available() > 0) {
                    currencyMode = datain.readInt();
                    menuPathXu = datain.readUTF();
                    menuPathLuong = datain.readUTF();
                }
                datain.close();
                bytein.close();
            }
        } catch (Exception e) {
        }

        normalize();
        loaded = true;
    }

    private static void normalize() {
        if (delayMs < 500) {
            delayMs = 500;
        }
        if (delayMs > 60000) {
            delayMs = 60000;
        }
        if (minEmptySlot < REQUIRED_EMPTY_SLOT) {
            minEmptySlot = REQUIRED_EMPTY_SLOT;
        }
        if (ruleText == null) {
            ruleText = "";
        }
        if (currencyMode != CURRENCY_LUONG) {
            currencyMode = CURRENCY_XU;
        }
        if (menuPathXu == null || menuPathXu.trim().length() == 0) {
            menuPathXu = "2";
        }
        if (menuPathLuong == null || menuPathLuong.trim().length() == 0) {
            menuPathLuong = "2,1";
        }
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

    static {
        load();
    }
}
