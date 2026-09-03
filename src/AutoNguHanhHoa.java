public final class AutoNguHanhHoa {

    private static final String STORE_NAME = "AutoNguHanhHoaCfg";
    private static final int ITEM_NGU_HANH_HOA = 988;
    private static final int OPTION_SINH_LUC = 150;
    private static final int SINH_LUC_THRESHOLD = 200;
    private static final int FLOWER_RECOVER = 150;
    private static final long CHECK_DELAY = 600000L;
    private static final long USE_DELAY = 1500L;
    private static final long PENDING_TIMEOUT = 15000L;
    private static final long POPUP_DELAY = 60000L;

    public static boolean enabled = RMS.d(STORE_NAME) != 0;

    private static boolean loaded = false;
    private static String status = "Tat";
    private static long lastCheckAt = 0L;
    private static long lastUseAt = 0L;
    private static long lastPopupAt = 0L;
    private static long pendingAt = 0L;
    private static int pendingHp = -1;
    private static int pendingFlowerCount = -1;
    private static boolean pendingProbe = false;
    private static boolean toppingUp = false;
    private static boolean loginUsePending = false;
    private static Char lastCharRef = null;
    private static String lastCharName = "";

    private AutoNguHanhHoa() {
    }

    public static void toggle() {
        load();
        setEnabled(!enabled);
        GameScr.chatPopup("Ngu Hanh Hoa: " + (enabled ? "Bat" : "Tat"));
    }

    public static void setEnabled(boolean value) {
        load();
        if (enabled != value) {
            resetRuntime();
        }
        enabled = value;
        status = enabled ? "Khoi dong" : "Tat";
        if (enabled) {
            loginUsePending = true;
        }
        save();
    }

    public static boolean isEnabled() {
        load();
        return enabled;
    }

    public static String getStatusText() {
        load();
        if (!enabled) {
            return "Tat";
        }
        Item bijuu = getMainBijuu();
        if (bijuu != null) {
            int hp = getSinhLuc(bijuu);
            int maxHp = getSinhLucMax(bijuu);
            if (maxHp > 0) {
                return hp + "/" + maxHp + ", hoa " + countFlower() + ", " + status;
            }
        }
        return status;
    }

    public static String getAutoText() {
        load();
        if (!enabled) {
            return "";
        }

        Item bijuu = getMainBijuu();
        int flower = countFlower();
        if (bijuu == null) {
            return "NHH: chua deo VT";
        }

        int hp = getSinhLuc(bijuu);
        int maxHp = getSinhLucMax(bijuu);
        if (maxHp > 0) {
            return "NHH: " + hp + "/" + maxHp + " hoa:" + flower;
        }
        return "NHH: " + status + " hoa:" + flower;
    }

    public static void update() {
        try {
            load();
            if (!enabled || !(GameCanvas.mScreen instanceof GameScr)) {
                return;
            }

            long now = System.currentTimeMillis();
            updateLoginState();
            if (hasPending(now)) {
                return;
            }

            if (!toppingUp && !loginUsePending && now - lastCheckAt < CHECK_DELAY) {
                return;
            }
            if (isBusy()) {
                return;
            }

            if (loginUsePending) {
                useProbe(now);
                return;
            }

            Item bijuu = getMainBijuu();
            if (bijuu == null) {
                status = "Chua deo Vi Thu";
                lastCheckAt = now;
                popup("Chua deo Vi Thu");
                return;
            }

            int hp = getSinhLuc(bijuu);
            int maxHp = getSinhLucMax(bijuu);
            if (maxHp <= 0) {
                status = "Cho du lieu sinh luc";
                lastCheckAt = now;
                return;
            }

            if (hp >= maxHp) {
                status = "Day sinh luc";
                toppingUp = false;
                loginUsePending = false;
                lastCheckAt = now;
                return;
            }

            int remain = maxHp - hp;
            if (remain > 0 && remain < FLOWER_RECOVER) {
                status = "Gan day " + hp + "/" + maxHp;
                toppingUp = false;
                loginUsePending = false;
                lastCheckAt = now;
                return;
            }

            if (!toppingUp && !loginUsePending && hp >= SINH_LUC_THRESHOLD) {
                status = "Cho 10 phut";
                toppingUp = false;
                lastCheckAt = now;
                return;
            }

            toppingUp = true;
            if (now - lastUseAt < USE_DELAY) {
                return;
            }

            Item flower = findBagItemById(ITEM_NGU_HANH_HOA);
            if (flower == null) {
                status = "Het hoa 988";
                toppingUp = false;
                loginUsePending = false;
                lastCheckAt = now;
                popup("Het Ngu Hanh Hoa 988");
                return;
            }

            int flowerCount = countFlower();
            Service.getInstance().useItem(flower.indexUI);
            lastUseAt = now;
            pendingAt = now;
            pendingHp = hp;
            pendingFlowerCount = flowerCount;
            pendingProbe = false;
            loginUsePending = false;
            status = "Dung hoa " + hp + "/" + maxHp;
        } catch (Exception e) {
            status = "Loi auto hoa";
        }
    }

    private static void useProbe(long now) {
        if (now - lastUseAt < USE_DELAY) {
            return;
        }

        Item flower = findBagItemById(ITEM_NGU_HANH_HOA);
        if (flower == null) {
            stopAndDisable("Het Ngu Hanh Hoa 988", true);
            return;
        }

        int flowerCount = countFlower();
        Service.getInstance().useItem(flower.indexUI);
        lastUseAt = now;
        pendingAt = now;
        pendingHp = getCurrentSinhLuc();
        pendingFlowerCount = flowerCount;
        pendingProbe = true;
        loginUsePending = false;
        status = "Dung thu hoa sau login";
    }

    private static void updateLoginState() {
        try {
            Char me = Char.getMyChar();
            if (me == null || me.charName == null || me.charName.length() == 0) {
                return;
            }
            if (me != lastCharRef || !me.charName.equals(lastCharName)) {
                lastCharRef = me;
                lastCharName = me.charName;
                loginUsePending = true;
                lastCheckAt = 0L;
                status = "Moi login";
            }
        } catch (Exception e) {
        }
    }

    private static Item getMainBijuu() {
        try {
            Char me = Char.getMyChar();
            if (me != null && me.arrItemBijuus != null && me.arrItemBijuus.length > 4) {
                return me.arrItemBijuus[4];
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static int getCurrentSinhLuc() {
        Item bijuu = getMainBijuu();
        return bijuu == null ? -1 : getSinhLuc(bijuu);
    }

    private static int getSinhLuc(Item bijuu) {
        return getOptionParam(bijuu, OPTION_SINH_LUC);
    }

    private static int getSinhLucMax(Item bijuu) {
        int max = 0;
        try {
            if (bijuu != null && bijuu.options != null) {
                for (int i = 0; i < bijuu.options.size(); ++i) {
                    ItemOption option = (ItemOption) bijuu.options.elementAt(i);
                    if (option == null || option.optionTemplate == null) {
                        continue;
                    }
                    int id = option.optionTemplate.id;
                    if (id == 140) {
                        max += 1000;
                    } else if (id == 141) {
                        max += 1500;
                    } else if (id == 142) {
                        max += 2000;
                    } else if (id == 143) {
                        max += 3000;
                    } else if (id == 147) {
                        max += option.param * 10;
                    }
                }
            }
        } catch (Exception e) {
        }
        return max;
    }

    private static int getOptionParam(Item item, int optionId) {
        try {
            if (item != null && item.options != null) {
                for (int i = 0; i < item.options.size(); ++i) {
                    ItemOption option = (ItemOption) item.options.elementAt(i);
                    if (option != null && option.optionTemplate != null && option.optionTemplate.id == optionId) {
                        return option.param;
                    }
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    private static int countFlower() {
        try {
            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null) {
                return 0;
            }
            return Char.k(ITEM_NGU_HANH_HOA);
        } catch (Exception e) {
            return 0;
        }
    }

    private static boolean hasPending(long now) {
        if (pendingAt <= 0L) {
            return false;
        }

        Item bijuu = getMainBijuu();
        int hp = getSinhLuc(bijuu);
        int flowerCount = countFlower();
        boolean increased = pendingHp >= 0 && hp > pendingHp;
        boolean acceptedFromUnknown = pendingProbe && pendingHp < 0 && hp >= 0 && flowerCount < pendingFlowerCount;
        if (increased || acceptedFromUnknown) {
            pendingAt = 0L;
            pendingHp = -1;
            pendingFlowerCount = -1;
            pendingProbe = false;
            toppingUp = true;
            status = "Hoa co tac dung";
            return false;
        }

        if (now - pendingAt >= PENDING_TIMEOUT) {
            boolean wasProbe = pendingProbe;
            pendingAt = 0L;
            pendingHp = -1;
            pendingFlowerCount = -1;
            pendingProbe = false;
            if (wasProbe) {
                if (bijuu != null) {
                    toppingUp = false;
                    loginUsePending = false;
                    lastCheckAt = now;
                    status = "Da hien Vi Thu";
                    return false;
                }
                stopAndDisable("Khong co Vi Thu, da tat auto hoa", true);
                return true;
            }
            return false;
        }

        return true;
    }

    private static void resetRuntime() {
        lastCheckAt = 0L;
        lastUseAt = 0L;
        pendingAt = 0L;
        pendingHp = -1;
        pendingFlowerCount = -1;
        pendingProbe = false;
        toppingUp = false;
        loginUsePending = false;
    }

    private static void stopAndDisable(String text, boolean showPopup) {
        enabled = false;
        toppingUp = false;
        loginUsePending = false;
        pendingAt = 0L;
        pendingHp = -1;
        pendingFlowerCount = -1;
        pendingProbe = false;
        status = text;
        save();
        if (showPopup) {
            GameScr.chatPopup(text);
        }
    }

    private static boolean isBusy() {
        try {
            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null || !me.isHuman) {
                return true;
            }
            if (AutoDoiLongDen.shouldPauseProducers() || AutoRuocDen.isBusy()) {
                return true;
            }
            if (GameCanvas.currentDialog != null || GameCanvas.menu != null && GameCanvas.menu.showMenu || TileMap.ag) {
                return true;
            }
            return Code.auto instanceof AutoReceiver
                    || Code.auto instanceof AutoSend
                    || Code.auto instanceof AutoGiaoDich
                    || Code.auto instanceof AutoGiaoDich2
                    || Code.auto instanceof AutoNpc
                    || Code.auto instanceof AutoEventTrade;
        } catch (Exception e) {
            return true;
        }
    }

    private static Item findBagItemById(int itemId) {
        try {
            Item[] bag = Char.getMyChar().arrItemBag;
            for (int i = 0; i < bag.length; ++i) {
                if (bag[i] != null && bag[i].template != null && bag[i].template.id == itemId) {
                    return bag[i];
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static void popup(String text) {
        long now = System.currentTimeMillis();
        if (now - lastPopupAt >= POPUP_DELAY) {
            lastPopupAt = now;
            GameScr.chatPopup(text);
        }
    }

    public static void load() {
        if (loaded) {
            return;
        }
        enabled = RMS.d(STORE_NAME) != 0;
        status = enabled ? "Khoi dong" : "Tat";
        loaded = true;
    }

    public static void save() {
        RMS.writeRecord(STORE_NAME, enabled ? 1 : 0);
        loaded = true;
    }
}
