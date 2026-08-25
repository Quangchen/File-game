public class AutoLuyenNgoc implements Runnable {

    private static final int[][] NGOC_KHAM_EXP = new int[][]{
        new int[]{0, 0},
        new int[]{200, 10},
        new int[]{500, 20},
        new int[]{1000, 50},
        new int[]{2000, 100},
        new int[]{5000, 200},
        new int[]{10000, 500},
        new int[]{20000, 1000},
        new int[]{50000, 2000},
        new int[]{100000, 5000},
        new int[]{100000, 10000}
    };

    private static final int OPTION_NGOC_EXP = 104;
    private static final int[] HUNT_GEM_IDS = new int[]{652, 653, 654, 655};
    private static final int[][] HUNT_MAX_OPTION_IDS = new int[][]{
        new int[]{102, 126, 114},
        new int[]{73, 124, 115},
        new int[]{103, 121, 116},
        new int[]{105, 125, 117}
    };
    private static final int[][] HUNT_MAX_OPTION_VALUES = new int[][]{
        new int[]{500, 5, 5},
        new int[]{100, 10, 10},
        new int[]{200, 5, 10},
        new int[]{500, 50, 50}
    };
    private static final int MODE_SELECTED = 0;
    private static final int MODE_SLOT0 = 1;
    private static final long SILENT_RESPONSE_TIMEOUT = 15000L;
    private static boolean runningSlot0;
    private static int silentRequestCount;
    private static long lastSilentRequestAt;

    private Item item;
    private int mode;
    private int indexUI;

    AutoLuyenNgoc(Item item) {
        this.item = item;
        this.indexUI = item == null ? -1 : item.indexUI;
        this.mode = MODE_SELECTED;
    }

    private AutoLuyenNgoc(int indexUI) {
        this.indexUI = indexUI;
        this.mode = MODE_SLOT0;
    }

    public static boolean isSlot0Running() {
        return runningSlot0;
    }

    public static boolean isSilentMode() {
        long now = System.currentTimeMillis();
        if (silentRequestCount > 0) {
            if (now - lastSilentRequestAt <= SILENT_RESPONSE_TIMEOUT) {
                --silentRequestCount;
                return true;
            }

            silentRequestCount = 0;
        }

        return runningSlot0;
    }

    public static void stopSlot0() {
        runningSlot0 = false;
    }

    public static void toggleSlot0() {
        if (runningSlot0) {
            runningSlot0 = false;
            GameScr.chatPopup("Đã tắt luyện ngọc ô 0");
            return;
        }

        runningSlot0 = true;
        (new Thread(new AutoLuyenNgoc(0))).start();
        GameScr.chatPopup("Đã bật luyện ngọc ô 0");
    }

    public static String getStatusText() {
        Item base = getNgocAt(0);
        if (base == null) {
            return "Ô 0 không có ngọc cần luyện";
        }

        return "Ô 0: +" + base.upgrade + " EXP " + getNgocCurrentExp(base) + " / mục tiêu +" + getTargetUpgrade() + (runningSlot0 ? " (đang chạy)" : " (đang tắt)");
    }

    public static String getAutoText() {
        if (!runningSlot0) {
            return "";
        }

        Item base = getNgocAt(0);
        if (base == null) {
            return "LN ô0: thiếu ngọc";
        }

        int nextUpgrade = base.upgrade + 1;
        if (nextUpgrade >= NGOC_KHAM_EXP.length) {
            nextUpgrade = NGOC_KHAM_EXP.length - 1;
        }

        return "LN ô0: +" + base.upgrade + " " + getNgocCurrentExp(base) + "/" + NGOC_KHAM_EXP[nextUpgrade][0] + " -> +" + getTargetUpgrade();
    }

    public void run() {
        if (this.mode == MODE_SLOT0) {
            runSlot0Silent();
        } else {
            runSelectedWithUI();
        }
    }

    private void runSelectedWithUI() {
        try {
            while (GameCanvas.mScreen instanceof GameScr) {
                waitDoiLongDenBusy();
                Item base = getCurrentBase();
                if (base == null) {
                    GameScr.chatPopup("Không thấy ngọc cần luyện");
                    break;
                }

                if (!canLuyen(base)) {
                    break;
                }

                Item[] ngocPhoi = new Item[24];
                int countNgocPhoi = fillNgocPhoiTietKiem(base, ngocPhoi);
                if (countNgocPhoi <= 0) {
                    GameScr.chatPopup("Hết ngọc phôi cấp 1 không khóa");
                    break;
                }

                GameScr.getInstance().closeDialog();
                GameScr.getInstance().openUI(46);
                GameScr.itemSplit = base;
                GameScr.arrItemSplit = ngocPhoi;
                removePhoiFromBag(ngocPhoi);
                markSilentRequest();
                Service.getInstance().ngockham((byte)1, (Item)null, base, ngocPhoi);
                Auto.sleep(getDelayMs());
                Service.getInstance().viewInfo(Char.getMyChar().charName);
                Auto.sleep(getDelayMs());
            }

            GameScr.getInstance().closeDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runSlot0Silent() {
        try {
            while (runningSlot0 && GameCanvas.mScreen instanceof GameScr) {
                waitDoiLongDenBusy();
                Item base = getNgocAt(0);
                if (base == null) {
                    GameScr.chatPopup("Ô 0 không có ngọc cần luyện");
                    break;
                }

                if (!canLuyen(base)) {
                    break;
                }

                Item[] ngocPhoi = new Item[24];
                int countNgocPhoi = fillNgocPhoiTietKiem(base, ngocPhoi);
                if (countNgocPhoi <= 0) {
                    int scanDelaySeconds = FormAutoLuyenNgoc.getScanDelaySeconds();
                    GameScr.chatPopup("Không còn ngọc phôi, quét " + scanDelaySeconds + "s...");
                    if (!waitForNgocPhoi((long)FormAutoLuyenNgoc.getScanDelayMs())) {
                        GameScr.chatPopup("Sau " + scanDelaySeconds + "s vẫn không có ngọc phôi, đã tắt luyện ngọc ô 0");
                        break;
                    }

                    base = getNgocAt(0);
                    if (base == null || !canLuyen(base)) {
                        break;
                    }

                    ngocPhoi = new Item[24];
                    countNgocPhoi = fillNgocPhoiTietKiem(base, ngocPhoi);
                    if (countNgocPhoi <= 0) {
                        GameScr.chatPopup("Sau " + scanDelaySeconds + "s vẫn không có ngọc phôi, đã tắt luyện ngọc ô 0");
                        break;
                    }
                }

                GameScr.itemSplit = base;
                GameScr.arrItemSplit = ngocPhoi;
                removePhoiFromBag(ngocPhoi);
                markSilentRequest();
                Service.getInstance().ngockhamSilent((byte)1, (Item)null, base, ngocPhoi);
                Auto.sleep(getDelayMs());
                Service.getInstance().viewInfo(Char.getMyChar().charName);
                Auto.sleep(getDelayMs());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        runningSlot0 = false;
    }

    private static void markSilentRequest() {
        if (silentRequestCount < 10) {
            ++silentRequestCount;
        }

        lastSilentRequestAt = System.currentTimeMillis();
    }

    private static boolean waitForNgocPhoi(long maxWait) {
        long start = System.currentTimeMillis();
        long lastRefresh = 0L;

        while (runningSlot0 && GameCanvas.mScreen instanceof GameScr && System.currentTimeMillis() - start < maxWait) {
            waitDoiLongDenBusy();
            Item base = getNgocAt(0);
            if (base == null) {
                return false;
            }

            Item[] testPhoi = new Item[24];
            if (fillNgocPhoiTietKiem(base, testPhoi) > 0) {
                return true;
            }

            long now = System.currentTimeMillis();
            if (now - lastRefresh >= 1000L) {
                Service.getInstance().viewInfo(Char.getMyChar().charName);
                lastRefresh = now;
            }

            Auto.sleep(500L);
        }

        Item base = getNgocAt(0);
        if (base == null) {
            return false;
        }

        Item[] testPhoi = new Item[24];
        return fillNgocPhoiTietKiem(base, testPhoi) > 0;
    }

    private static void waitDoiLongDenBusy() {
        while ((AutoDoiLongDen.shouldPauseProducers() || AutoRuocDen.isBusy()) && GameCanvas.mScreen instanceof GameScr) {
            Auto.sleep(100L);
        }
    }

    private static boolean canLuyen(Item base) {
        if (base.upgrade < 2) {
            GameScr.chatPopup("Ngọc cần luyện phải từ cấp 2");
            return false;
        }

        int target = getTargetUpgrade();
        if (base.upgrade >= target) {
            GameScr.chatPopup("Ngọc đã đạt +" + target);
            return false;
        }

        return true;
    }

    private Item getCurrentBase() {
        if (this.indexUI < 0) {
            return null;
        }

        Item base = getNgocAt(this.indexUI);
        if (base != null) {
            this.item = base;
        }

        return base;
    }

    private static Item getNgocAt(int indexUI) {
        if (Char.getMyChar() == null || Char.getMyChar().arrItemBag == null || indexUI < 0 || indexUI >= Char.getMyChar().arrItemBag.length) {
            return null;
        }

        Item base = Char.getMyChar().arrItemBag[indexUI];
        if (base == null || base.template == null || !isNgocEvent(base.template.id)) {
            return null;
        }

        return base;
    }

    private static void removePhoiFromBag(Item[] ngocPhoi) {
        for (int i = 0; i < ngocPhoi.length; ++i) {
            if (ngocPhoi[i] != null && ngocPhoi[i].indexUI >= 0 && ngocPhoi[i].indexUI < Char.getMyChar().arrItemBag.length) {
                Char.getMyChar().arrItemBag[ngocPhoi[i].indexUI] = null;
            }
        }
    }

    private static int fillNgocPhoiTietKiem(Item base, Item[] ngocPhoi) {
        int count = 0;
        int totalExp = 0;
        int missingExp = getMissingExpToNextUpgrade(base);
        if (missingExp <= 0) {
            missingExp = 1;
        }

        Item[] bag = Char.getMyChar().arrItemBag;
        for (int indexUI = 0; indexUI < bag.length; ++indexUI) {
            if (indexUI == base.indexUI) {
                continue;
            }

            Item ngocRac = bag[indexUI];
            if (ngocRac == null || ngocRac.template == null || !isNgocEvent(ngocRac.template.id)) {
                continue;
            }

            if (isWaitingHuntGemInfo(ngocRac)) {
                continue;
            }

            if (isProtectedHuntGem(ngocRac)) {
                continue;
            }

            if (ngocRac.isLock || ngocRac.upgrade != 1) {
                continue;
            }

            if (count >= ngocPhoi.length) {
                break;
            }

            ngocPhoi[count++] = ngocRac;
            totalExp += getNgocPhoiExp(ngocRac);
            if (totalExp >= missingExp) {
                break;
            }
        }

        return count;
    }

    private static int getMissingExpToNextUpgrade(Item base) {
        int nextUpgrade = base.upgrade + 1;
        if (nextUpgrade >= NGOC_KHAM_EXP.length) {
            nextUpgrade = NGOC_KHAM_EXP.length - 1;
        }

        int missing = NGOC_KHAM_EXP[nextUpgrade][0] - getNgocCurrentExp(base);
        return missing > 0 ? missing : 0;
    }

    private static int getNgocCurrentExp(Item item) {
        if (item == null || item.options == null) {
            return 0;
        }

        for (int i = 0; i < item.options.size(); ++i) {
            ItemOption option = (ItemOption)item.options.elementAt(i);
            if (option != null && option.optionTemplate != null && option.optionTemplate.id == OPTION_NGOC_EXP) {
                return option.param;
            }
        }

        return 0;
    }

    private static int getNgocPhoiExp(Item item) {
        int upgrade = item.upgrade;
        if (upgrade < 0) {
            upgrade = 0;
        }
        if (upgrade >= NGOC_KHAM_EXP.length) {
            upgrade = NGOC_KHAM_EXP.length - 1;
        }

        return NGOC_KHAM_EXP[upgrade][1];
    }

    private static int getTargetUpgrade() {
        int target = FormAutoLuyenNgoc.targetUpgrade;
        if (target < 2) {
            target = 2;
        }
        if (target >= NGOC_KHAM_EXP.length) {
            target = NGOC_KHAM_EXP.length - 1;
        }

        return target;
    }

    private static int getDelayMs() {
        return FormAutoLuyenNgoc.delayMs < 200 ? 200 : FormAutoLuyenNgoc.delayMs;
    }

    private static boolean isWaitingHuntGemInfo(Item item) {
        if (!FormAutoLuyenNgoc.huntMaxNgoc || item == null || item.template == null) {
            return false;
        }

        int gemIndex = getHuntGemIndex(item.template.id);
        if (gemIndex < 0 || !FormAutoLuyenNgoc.isHuntGemSelected(gemIndex)) {
            return false;
        }

        if (item.options != null && item.options.size() > 0) {
            return false;
        }

        if (!item.s && System.currentTimeMillis() - item.t > 3000L) {
            item.t = System.currentTimeMillis();
            Service.getInstance().requestItemInfo(item.typeUI, item.indexUI);
        }
        return true;
    }

    public static boolean isProtectedHuntGem(Item item) {
        if (!FormAutoLuyenNgoc.huntMaxNgoc || item == null || item.template == null || item.options == null) {
            return false;
        }

        int gemIndex = getHuntGemIndex(item.template.id);
        if (gemIndex < 0 || !FormAutoLuyenNgoc.isHuntGemSelected(gemIndex)) {
            return false;
        }

        for (int roleIndex = 0; roleIndex < HUNT_MAX_OPTION_IDS[gemIndex].length; ++roleIndex) {
            if (!FormAutoLuyenNgoc.isHuntRoleSelected(roleIndex)) {
                continue;
            }

            int optionId = HUNT_MAX_OPTION_IDS[gemIndex][roleIndex];
            int maxValue = HUNT_MAX_OPTION_VALUES[gemIndex][roleIndex];
            if (getOptionParam(item, optionId) >= maxValue) {
                return true;
            }
        }

        return false;
    }

    private static int getHuntGemIndex(int itemId) {
        for (int i = 0; i < HUNT_GEM_IDS.length; ++i) {
            if (HUNT_GEM_IDS[i] == itemId) {
                return i;
            }
        }

        return -1;
    }

    private static int getOptionParam(Item item, int optionId) {
        if (item == null || item.options == null) {
            return 0;
        }

        int best = 0;
        for (int i = 0; i < item.options.size(); ++i) {
            ItemOption option = (ItemOption)item.options.elementAt(i);
            if (option != null && option.optionTemplate != null && option.optionTemplate.id == optionId) {
                if (option.param > best) {
                    best = option.param;
                }
            }
        }

        return best;
    }

    private static boolean isNgocEvent(int id) {
        return id == 652 || id == 653 || id == 654 || id == 655;
    }
}
