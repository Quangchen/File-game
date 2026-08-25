public final class AutoVBL {

    private static long lastUseAt = 0L;
    private static final int ITEM_VBL = 279;
    private static final long USE_COOLDOWN_MS = 20000L;

    private AutoVBL() {
    }

    public static boolean useTo(String name) {
        try {
            if (name == null || name.trim().length() == 0) {
                return false;
            }
            if (System.currentTimeMillis() - lastUseAt < USE_COOLDOWN_MS) {
                return false;
            }

            int index = Char.getIndexItemById(ITEM_VBL);
            if (index < 0) {
                buyVblIfMissing();
                index = Char.getIndexItemById(ITEM_VBL);
                if (index < 0) {
                    return false;
                }
            }

            lastUseAt = System.currentTimeMillis();
            GameScr.getInstance().closeDialog();
            Service.getInstance().useItem(index);
            Auto.sleep(200L);
            Service.getInstance().useVbl((short) 1, name.trim());
            Auto.sleep(200L);
            GameScr.getInstance().closeDialog();
            GameCanvas.setMaxTextLenght();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void buyVblIfMissing() {
        try {
            if (!goShopMapIfNeeded()) {
                return;
            }

            GameScr.getInstance().openUI(14);
            Auto.sleep(700L);
            Item item = findShopItemById(ITEM_VBL);
            if (item == null) {
                Service.getInstance().requestItem(14);
                Auto.sleep(700L);
                item = findShopItemById(ITEM_VBL);
            }

            if (item != null) {
                Service.getInstance().buyItem1(item.typeUI, item.indexUI, 1);
                Auto.sleep(1000L);
            }
            restoreGameMenu();
        } catch (Exception e) {
        }
    }

    private static boolean goShopMapIfNeeded() {
        try {
            if (TileMap.isTruong(TileMap.mapID) || TileMap.isLang(TileMap.mapID)) {
                return true;
            }

            TileMap.gomap(1);
            long start = System.currentTimeMillis();
            while (!TileMap.isTruong(TileMap.mapID) && !TileMap.isLang(TileMap.mapID) && System.currentTimeMillis() - start < 20000L) {
                Auto.sleep(500L);
            }
            return TileMap.isTruong(TileMap.mapID) || TileMap.isLang(TileMap.mapID);
        } catch (Exception e) {
            return false;
        }
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
}
