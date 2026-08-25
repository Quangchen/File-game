public final class AutoBiKip implements Runnable {

    private static final int NPC_TASHINO = 39;
    private static final int TYPE_BIKIP = 15;
    private static final int OPTION_CAP_BIKIP = 85;
    private static final int CONFIRM_LUYEN_BIKIP = 110;
    private static final int FEE_GOLD = 1000;

    private static boolean running = false;
    private static Thread thread;
    private static String currentText = "";
    private static int attempts = 0;
    private static int spentGold = 0;

    public static void start() {
        if (running) {
            GameScr.chatPopup("Auto bí kíp đang chạy");
            return;
        }
        FormAutoBiKip.load();
        if (FormAutoBiKip.countSelected() == 0) {
            GameScr.chatPopup("Auto bí kíp: chưa chọn chỉ số");
            return;
        }
        running = true;
        attempts = 0;
        spentGold = 0;
        currentText = "";
        thread = new Thread(new AutoBiKip());
        thread.start();
        GameScr.chatPopup("Bật auto săn bí kíp");
    }

    public static void stop() {
        if (!running) {
            return;
        }

        running = false;
        GameScr.chatPopup("Dừng auto săn bí kíp");
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
        return "Săn bí kíp";
    }

    public void run() {
        try {
            while (running) {
                Char me = Char.getMyChar();
                if (me == null || me.arrItemBody == null) {
                    sleep(1000L);
                    continue;
                }
                if (!me.isHuman) {
                    finish("Auto bí kíp chỉ chạy ở nhân vật chính");
                    break;
                }

                Item biKip = getBiKip(me);
                if (biKip == null) {
                    finish("Chưa mặc bí kíp");
                    break;
                }
                if (isUpgradedBiKip(biKip)) {
                    finish("Bí kíp đã nâng cấp, không thể luyện");
                    break;
                }

                int matched = countMatched(biKip);
                currentText = "Bí kíp " + matched + "/" + FormAutoBiKip.NeedCount + " lần:" + attempts + " tốn:" + spentGold;
                if (matched >= FormAutoBiKip.NeedCount) {
                    finish("Bí kíp đã đạt chỉ số yêu cầu");
                    break;
                }
                if (attempts >= FormAutoBiKip.MaxAttempts) {
                    finish("Auto bí kíp đạt số lần tối đa");
                    break;
                }
                if (me.luong < FEE_GOLD + FormAutoBiKip.KeepGold) {
                    finish("Không đủ lượng luyện bí kíp");
                    break;
                }

                int beforeSignature = optionSignature(biKip);
                if (!doTrainAttempt()) {
                    finish("Không mở được luyện bí kíp");
                    break;
                }
                attempts++;
                spentGold += FEE_GOLD;
                waitBiKipChanged(beforeSignature, FormAutoBiKip.DelayMs + 5000L);
                sleep(FormAutoBiKip.DelayMs);
            }
        } catch (Exception e) {
            finish("Lỗi auto săn bí kíp");
        }
    }

    private static boolean doTrainAttempt() {
        try {
            if (!openLuyenBiKipBox()) {
                return false;
            }
            Service.getInstance().d(0);
            hideWhileWait(600L);
            Service.getInstance().o(CONFIRM_LUYEN_BIKIP);
            hideWhileWait(800L);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean openLuyenBiKipBox() {
        try {
            if (isLuyenBiKipBoxReady()) {
                closeClientPopups();
                return true;
            }
            if (!ensureTashino()) {
                return false;
            }
            resetStaleCommandBox();
            Service.getInstance().openMenu(NPC_TASHINO);
            sleep(FormAutoBiKip.DelayMs);
            int[] path = parsePath(FormAutoBiKip.MenuPath);
            if (path == null || path.length == 0) {
                return false;
            }
            for (int i = 0; running && i < path.length; i++) {
                Service.getInstance().menu(NPC_TASHINO, path[i], 0);
                sleep(FormAutoBiKip.DelayMs);
            }
            boolean ready = waitLuyenBiKipBox(8000L);
            if (ready) {
                closeClientPopups();
            }
            return ready;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean ensureTashino() {
        Npc npc = GameScr.findNpc(NPC_TASHINO);
        if (npc == null) {
            GameScr.chatPopup("Không thấy NPC Tashino");
            return false;
        }
        Char me = Char.getMyChar();
        if (me == null) {
            return false;
        }
        Char.charMove(npc.cx, npc.cy);
        me.npcFocus = npc;
        return true;
    }

    private static boolean waitLuyenBiKipBox(long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            closeClientPopups();
            if (isLuyenBiKipBoxReady()) {
                closeClientPopups();
                return true;
            }
            sleep(200L);
        }
        return false;
    }

    private static boolean isLuyenBiKipBoxReady() {
        try {
            Char me = Char.getMyChar();
            if (me == null || me.arrItemBox == null || me.arrItemBox.length == 0) {
                return false;
            }
            Item item = me.arrItemBox[0];
            return item != null && item.template != null && item.template.type == TYPE_BIKIP;
        } catch (Exception e) {
            return false;
        }
    }

    private static Item getBiKip(Char me) {
        try {
            if (me == null || me.arrItemBody == null || me.arrItemBody.length <= TYPE_BIKIP) {
                return null;
            }
            Item item = me.arrItemBody[TYPE_BIKIP];
            if (item != null && item.template != null && item.template.type == TYPE_BIKIP) {
                return item;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static boolean isUpgradedBiKip(Item item) {
        if (item == null) {
            return false;
        }
        if (item.upgrade > 0) {
            return true;
        }
        return getOptionParam(item, OPTION_CAP_BIKIP) > 0;
    }

    private static int countMatched(Item item) {
        int count = 0;
        for (int i = 0; i < FormAutoBiKip.OPTION_IDS.length; i++) {
            if (FormAutoBiKip.Selected[i]) {
                int optionId = FormAutoBiKip.OPTION_IDS[i];
                int param = getOptionParam(item, optionId);
                if (param >= minParam(optionId)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int minParam(int optionId) {
        try {
            String cfg = FormAutoBiKip.MinParams;
            if (cfg == null || cfg.length() == 0) {
                return 1;
            }
            String[] parts = Code.splitString(cfg, ",");
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i].trim();
                int p = part.indexOf(':');
                if (p < 0) {
                    p = part.indexOf('=');
                }
                if (p > 0) {
                    int id = Integer.parseInt(part.substring(0, p).trim());
                    if (id == optionId) {
                        return Integer.parseInt(part.substring(p + 1).trim());
                    }
                }
            }
        } catch (Exception e) {
        }
        return 1;
    }

    private static int getOptionParam(Item item, int optionId) {
        try {
            if (item == null || item.options == null) {
                return 0;
            }
            for (int i = 0; i < item.options.size(); i++) {
                ItemOption option = (ItemOption) item.options.elementAt(i);
                if (option != null && option.optionTemplate != null && option.optionTemplate.id == optionId) {
                    return option.param;
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    private static int optionSignature(Item item) {
        int sig = 17;
        try {
            if (item != null && item.options != null) {
                for (int i = 0; i < item.options.size(); i++) {
                    ItemOption option = (ItemOption) item.options.elementAt(i);
                    if (option != null && option.optionTemplate != null) {
                        sig = sig * 31 + option.optionTemplate.id;
                        sig = sig * 31 + option.param;
                    }
                }
            }
        } catch (Exception e) {
        }
        return sig;
    }

    private static void waitBiKipChanged(int beforeSignature, long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            closeClientPopups();
            Item biKip = getBiKip(Char.getMyChar());
            if (biKip != null && optionSignature(biKip) != beforeSignature) {
                closeClientPopups();
                return;
            }
            sleep(200L);
        }
    }

    private static int[] parsePath(String path) {
        try {
            if (path == null || path.trim().length() == 0) {
                return new int[0];
            }
            String[] parts = Code.splitString(path, ",");
            int[] result = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                result[i] = Integer.parseInt(parts[i].trim());
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private static void resetStaleCommandBox() {
        try {
            Char me = Char.getMyChar();
            if (me != null) {
                me.arrItemBox = null;
            }
            GameScr.svTitle = "";
            GameScr.svAction = "";
        } catch (Exception e) {
        }
    }

    private static void closeClientPopups() {
        try {
            GameScr.hideCommandBoxUi();
        } catch (Exception e) {
        }
        try {
            GameCanvas.menu.showMenu = false;
        } catch (Exception e) {
        }
        try {
            GameCanvas.currentDialog = null;
        } catch (Exception e) {
        }
    }

    private static void hideWhileWait(long timeout) {
        long start = System.currentTimeMillis();
        while (running && System.currentTimeMillis() - start < timeout) {
            closeClientPopups();
            sleep(100L);
        }
    }

    private static void finish(String text) {
        currentText = text;
        running = false;
        GameScr.chatPopup(text);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }
}
