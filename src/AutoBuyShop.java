import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class AutoBuyShop {

    private static final String STORE_NAME = "AutoBuyShopCfg";
    private static final int MAP_LANG_CO_SHOP = 138;
    private static final int MAP_LTT_SHOP = 162;
    private static final long BUY_COOLDOWN = 3000L;
    private static final long FAIL_COOLDOWN = 15000L;

    public static boolean enabled = false;
    public static boolean allowGoBuy = true;
    public static boolean requireEmptySlot = true;
    public static String itemIds = "545";
    public static String shopIds = "14";
    public static String buyCounts = "1";
    public static int delayMs = 5000;

    private static int[] itemList = new int[0];
    private static int[] shopList = new int[0];
    private static int[] countList = new int[0];
    private static long[] lastBuyAt = new long[0];
    private static long[] lastFailAt = new long[0];
    private static long lastCheckAt = 0L;
    private static boolean loaded = false;

    private AutoBuyShop() {
    }

    public static void update() {
        try {
            ensureLoaded();
            if (!enabled || itemList.length == 0) {
                return;
            }

            long now = System.currentTimeMillis();
            int checkDelay = delayMs < 1000 ? 1000 : delayMs;
            if (now - lastCheckAt < checkDelay) {
                return;
            }
            lastCheckAt = now;

            if (isBusy()) {
                return;
            }

            for (int i = 0; i < itemList.length; ++i) {
                int itemId = itemList[i];
                if (itemId <= 0) {
                    continue;
                }

                if (Char.k(itemId) > 0) {
                    continue;
                }

                if (i < lastBuyAt.length && now - lastBuyAt[i] < BUY_COOLDOWN) {
                    continue;
                }

                if (i < lastFailAt.length && now - lastFailAt[i] < FAIL_COOLDOWN) {
                    continue;
                }

                if (requireEmptySlot && Char.countNullSlot() <= 0) {
                    markFail(i, "Tự mua: full hành trang");
                    continue;
                }

                if (!canLeaveCurrentMap()) {
                    markFail(i, "Tự mua: thiếu " + itemId + ", đang map đặc biệt");
                    continue;
                }

                if (!allowGoBuy && !isAtShopMap()) {
                    markFail(i, "Tự mua: thiếu " + itemId + ", chưa ở làng/trường");
                    continue;
                }

                int shopId = i < shopList.length ? shopList[i] : -1;
                int count = i < countList.length ? countList[i] : 1;
                if (count <= 0) {
                    count = 1;
                }

                if (buyMissingItem(i, itemId, shopId, count)) {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    private static boolean buyMissingItem(int index, int itemId, int shopId, int count) {
        try {
            if (shopId < 0) {
                markFail(index, "Tự mua: shop lỗi " + itemId);
                return false;
            }

            int before = Char.k(itemId);
            if (!goShopMapForBuy()) {
                markFail(index, "Tự mua: không tới map mua " + itemId);
                return false;
            }

            GameScr.chatPopup("Tự mua: " + itemId + " x" + count);
            requestShop(shopId);
            waitShopItems(shopId, 5000L);
            Item shopItem = findShopItem(shopId, itemId);
            if (shopItem == null) {
                Auto.sleep(600L);
                requestShop(shopId);
                waitShopItems(shopId, 5000L);
                shopItem = findShopItem(shopId, itemId);
            }

            if (shopItem == null) {
                markFail(index, "Tự mua: không thấy " + itemId + " shop " + shopId);
                restoreGameMenu();
                return false;
            }

            Service.getInstance().buyItem1(shopItem.typeUI, shopItem.indexUI, count);
            LockGame.g();
            setLastBuy(index);
            Auto.sleep(500L);
            if (Char.k(itemId) <= before) {
                Service.getInstance().viewInfo(Char.getMyChar().charName);
                Auto.sleep(700L);
            }

            restoreGameMenu();
            if (Char.k(itemId) > before) {
                GameScr.chatPopup("Tự mua xong: " + itemId);
                return true;
            }

            markFail(index, "Tự mua: mua thất bại " + itemId);
        } catch (Exception e) {
            markFail(index, "Tự mua: lỗi " + itemId);
        }

        restoreGameMenu();
        return false;
    }

    public static boolean buyNow(int itemId, int shopId, int count) {
        try {
            ensureLoaded();
            if (isBusy()) {
                return false;
            }
            return buyMissingItem(-1, itemId, shopId, count);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean prepareShopForBuy(int shopId) {
        try {
            ensureLoaded();
            if (isBusy()) {
                return false;
            }
            if (!goShopMapForBuy()) {
                return false;
            }
            requestShop(shopId);
            boolean loaded = waitShopItems(shopId, 5000L);
            if (!loaded) {
                restoreGameMenu();
            }
            return loaded;
        } catch (Exception e) {
            restoreGameMenu();
            return false;
        }
    }

    public static void restoreAfterBuy() {
        restoreGameMenu();
    }

    public static Item[] getShopItems(int shopId) {
        switch (shopId) {
            case 2:
                return GameScr.arrItemWeapon;
            case 6:
                return GameScr.arrItemStack;
            case 7:
                return GameScr.arrItemStackLock;
            case 8:
                return GameScr.arrItemGrocery;
            case 9:
                return GameScr.arrItemGroceryLock;
            case 14:
                return GameScr.arrItemStore;
            case 15:
                return GameScr.arrItemBook;
            case 16:
                return GameScr.arrItemLien;
            case 17:
                return GameScr.arrItemNhan;
            case 18:
                return GameScr.arrItemNgocBoi;
            case 19:
                return GameScr.arrItemPhu;
            case 20:
                return GameScr.arrItemNonNam;
            case 21:
                return GameScr.arrItemNonNu;
            case 22:
                return GameScr.arrItemAoNam;
            case 23:
                return GameScr.arrItemAoNu;
            case 24:
                return GameScr.arrItemGangTayNam;
            case 25:
                return GameScr.arrItemGangTayNu;
            case 26:
                return GameScr.arrItemQuanNam;
            case 27:
                return GameScr.arrItemQuanNu;
            case 28:
                return GameScr.arrItemGiayNam;
            case 29:
                return GameScr.arrItemGiayNu;
            case 32:
                return GameScr.arrItemFashion;
            case 34:
                return GameScr.arrItemClanShop;
            case 35:
                return GameScr.arrItemElites;
        }
        return null;
    }

    private static void requestShop(int shopId) {
        try {
            GameScr.getInstance().openUI(shopId);
            Auto.sleep(300L);
            if (getShopItems(shopId) == null) {
                Service.getInstance().requestItem(shopId);
            }
            Auto.sleep(700L);
        } catch (Exception e) {
        }
    }

    private static boolean waitShopItems(int shopId, long timeout) {
        long start = System.currentTimeMillis();
        while (getShopItems(shopId) == null && System.currentTimeMillis() - start < timeout) {
            Auto.sleep(100L);
        }
        return getShopItems(shopId) != null;
    }


    private static boolean goShopMapForBuy() {
        try {
            if (isAtShopMap()) {
                return true;
            }

            if (TileMap.isHang(TileMap.mapID) || TileMap.isClanDun()) {
                return false;
            }

            if (TileMap.isLangTT(TileMap.mapID)) {
                TileMap.direction(MAP_LTT_SHOP);
                return waitMap(MAP_LTT_SHOP, 20000L);
            }

            if (TileMap.isLangCo(TileMap.mapID)) {
                TileMap.direction(MAP_LANG_CO_SHOP);
                return waitMap(MAP_LANG_CO_SHOP, 20000L);
            }

            if (TileMap.isVDMQ(TileMap.mapID)) {
                Auto.goTruongIfNeeded();
                return waitTruongOrLang(25000L);
            }

            TileMap.direction(1);
            if (!waitTruongOrLang(20000L)) {
                Auto.goTruongIfNeeded();
            }

            return waitTruongOrLang(25000L);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isAtShopMap() {
        return TileMap.isTruong(TileMap.mapID) || TileMap.isLang(TileMap.mapID);
    }

    private static boolean waitMap(int map, long timeout) {
        long start = System.currentTimeMillis();
        while (TileMap.mapID != map && System.currentTimeMillis() - start < timeout) {
            Auto.sleep(500L);
        }
        return TileMap.mapID == map;
    }

    private static boolean waitTruongOrLang(long timeout) {
        long start = System.currentTimeMillis();
        while (!isAtShopMap() && System.currentTimeMillis() - start < timeout) {
            Auto.sleep(500L);
        }
        return isAtShopMap();
    }

    private static boolean canLeaveCurrentMap() {
        if (TileMap.isHang(TileMap.mapID) || TileMap.isClanDun()) {
            return false;
        }

        return !(Code.auto instanceof AutoHD9x)
                && !(Code.auto instanceof AutoHD9xGather)
                && !(Code.auto instanceof AutoHD9xChest)
                && !(Code.auto instanceof AutoHD9xReward)
                && !(Code.auto instanceof AutoLDGT)
                && !(Code.auto instanceof AutoJoinClanDun);
    }

    private static boolean isBusy() {
        try {
            if (Char.getMyChar() == null || Char.getMyChar().arrItemBag == null) {
                return true;
            }

            if (AutoDoiLongDen.shouldPauseProducers()) {
                return true;
            }

            if (AutoRuocDen.isBusy()) {
                return true;
            }

            if (GameCanvas.currentDialog != null || GameCanvas.menu != null && GameCanvas.menu.showMenu || TileMap.ag) {
                return true;
            }

            GameScr game = GameScr.getInstance();
            if (game != null && game.da != 0) {
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

    private static Item findShopItem(int shopId, int itemId) {
        Item item = findShopItemByType(shopId, itemId);
        if (item != null) {
            return item;
        }

        if ((item = findInArray(GameScr.arrItemStore, itemId)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemStack, itemId)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemStackLock, itemId)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemGrocery, itemId)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemGroceryLock, itemId)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemElites, itemId)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemClanShop, itemId)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemBook, itemId)) != null) {
            return item;
        }
        if ((item = findInArray(GameScr.arrItemFashion, itemId)) != null) {
            return item;
        }
        return null;
    }

    private static Item findShopItemByType(int shopId, int itemId) {
        switch (shopId) {
            case 2:
                return findInArray(GameScr.arrItemWeapon, itemId);
            case 6:
                return findInArray(GameScr.arrItemStack, itemId);
            case 7:
                return findInArray(GameScr.arrItemStackLock, itemId);
            case 8:
                return findInArray(GameScr.arrItemGrocery, itemId);
            case 9:
                return findInArray(GameScr.arrItemGroceryLock, itemId);
            case 14:
                return findInArray(GameScr.arrItemStore, itemId);
            case 15:
                return findInArray(GameScr.arrItemBook, itemId);
            case 16:
                return findInArray(GameScr.arrItemLien, itemId);
            case 17:
                return findInArray(GameScr.arrItemNhan, itemId);
            case 18:
                return findInArray(GameScr.arrItemNgocBoi, itemId);
            case 19:
                return findInArray(GameScr.arrItemPhu, itemId);
            case 20:
                return findInArray(GameScr.arrItemNonNam, itemId);
            case 21:
                return findInArray(GameScr.arrItemNonNu, itemId);
            case 22:
                return findInArray(GameScr.arrItemAoNam, itemId);
            case 23:
                return findInArray(GameScr.arrItemAoNu, itemId);
            case 24:
                return findInArray(GameScr.arrItemGangTayNam, itemId);
            case 25:
                return findInArray(GameScr.arrItemGangTayNu, itemId);
            case 26:
                return findInArray(GameScr.arrItemQuanNam, itemId);
            case 27:
                return findInArray(GameScr.arrItemQuanNu, itemId);
            case 28:
                return findInArray(GameScr.arrItemGiayNam, itemId);
            case 29:
                return findInArray(GameScr.arrItemGiayNu, itemId);
            case 32:
                return findInArray(GameScr.arrItemFashion, itemId);
            case 34:
                return findInArray(GameScr.arrItemClanShop, itemId);
            case 35:
                return findInArray(GameScr.arrItemElites, itemId);
            default:
                return null;
        }
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

    private static void setLastBuy(int index) {
        if (index >= 0 && index < lastBuyAt.length) {
            lastBuyAt[index] = System.currentTimeMillis();
        }
    }

    private static void markFail(int index, String message) {
        if (index >= 0 && index < lastFailAt.length) {
            lastFailAt[index] = System.currentTimeMillis();
        }
        GameScr.chatPopup(message);
    }

    private static void restoreGameMenu() {
        try {
            GameScr gameScr = GameScr.getInstance();
            if (gameScr != null) {
                gameScr.resetButton();
            }
            GameCanvas.setMaxTextLenght();
        } catch (Exception e) {
        }
    }

    public static void reloadConfig() {
        itemList = parseList(itemIds);
        shopList = parseList(shopIds);
        countList = parseList(buyCounts);
        int len = itemList.length;
        lastBuyAt = new long[len];
        lastFailAt = new long[len];
        lastCheckAt = 0L;
    }

    private static int[] parseList(String text) {
        if (text == null) {
            return new int[0];
        }

        String[] parts = Code.splitString(text, ",");
        int count = 0;
        int[] temp = new int[parts.length];
        for (int i = 0; i < parts.length; ++i) {
            try {
                int value = Integer.parseInt(parts[i].trim());
                temp[count++] = value;
            } catch (Exception e) {
            }
        }

        int[] result = new int[count];
        System.arraycopy(temp, 0, result, 0, count);
        return result;
    }

    public static boolean isConfigValid() {
        reloadConfig();
        return itemList.length > 0 && itemList.length == shopList.length && itemList.length == countList.length;
    }

    public static String getConfigSummary() {
        reloadConfig();
        return itemList.length + " item";
    }

    private static void ensureLoaded() {
        if (!loaded) {
            load();
        }
    }

    public static void save() {
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(enabled);
            dataout.writeBoolean(allowGoBuy);
            dataout.writeBoolean(requireEmptySlot);
            dataout.writeUTF(itemIds == null ? "" : itemIds);
            dataout.writeUTF(shopIds == null ? "" : shopIds);
            dataout.writeUTF(buyCounts == null ? "" : buyCounts);
            dataout.writeInt(delayMs);
            dataout.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            dataout.close();
            byteout.close();
            reloadConfig();
        } catch (Exception e) {
        }
    }

    public static void load() {
        try {
            byte[] data = RMS.getRecord(STORE_NAME);
            if (data != null) {
                ByteArrayInputStream bytein = new ByteArrayInputStream(data);
                DataInputStream datain = new DataInputStream(bytein);
                enabled = datain.readBoolean();
                allowGoBuy = datain.readBoolean();
                requireEmptySlot = datain.readBoolean();
                itemIds = datain.readUTF();
                shopIds = datain.readUTF();
                buyCounts = datain.readUTF();
                delayMs = datain.readInt();
                datain.close();
                bytein.close();
            }
        } catch (Exception e) {
        }

        if (delayMs < 1000) {
            delayMs = 1000;
        }
        loaded = true;
        reloadConfig();
    }

    static {
        load();
    }
}
