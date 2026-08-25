import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class AutoUseItem {

    private static final String STORE_NAME = "AutoUseItemCfg";
    private static final int MAX_RULE = 60;
    private static final long CHECK_DELAY = 500L;
    private static final long BUY_DELAY = 5000L;
    private static final long BUY_FAIL_DELAY = 15000L;
    private static final long USE_PENDING_TIMEOUT = 3000L;

    private static short[] itemIds = new short[MAX_RULE];
    private static boolean[] enabled = new boolean[MAX_RULE];
    private static boolean[] autoBuy = new boolean[MAX_RULE];
    private static boolean[] checkEffect = new boolean[MAX_RULE];
    private static int[] delayMs = new int[MAX_RULE];
    private static int[] shopIds = new int[MAX_RULE];
    private static int[] buyCounts = new int[MAX_RULE];
    private static int[] bodyIndex = new int[MAX_RULE];
    private static long[] lastUseAt = new long[MAX_RULE];
    private static long[] lastBuyAt = new long[MAX_RULE];
    private static long[] lastBuyFailAt = new long[MAX_RULE];
    private static long lastCheckAt = 0L;
    private static long lastBuyAttemptAt = 0L;
    private static int pendingUseRule = -1;
    private static int pendingUseItemId = -1;
    private static int pendingUseBodySlot = -1;
    private static long pendingUseAt = 0L;

    private AutoUseItem() {
    }

    public static short[] getListIds() {
        return itemIds;
    }

    public static boolean contains(int itemId) {
        return findIndexById(itemId) >= 0;
    }

    public static int getItemIdAt(int index) {
        return index >= 0 && index < itemIds.length ? itemIds[index] : 0;
    }

    public static boolean isEnabled(int index) {
        return index >= 0 && index < enabled.length && enabled[index];
    }

    public static boolean isAutoBuy(int index) {
        return index >= 0 && index < autoBuy.length && autoBuy[index];
    }

    public static boolean isCheckEffect(int index) {
        return index >= 0 && index < checkEffect.length && checkEffect[index];
    }

    public static int getDelayMs(int index) {
        return index >= 0 && index < delayMs.length ? delayMs[index] : 2000;
    }

    public static int getShopId(int index) {
        return index >= 0 && index < shopIds.length ? shopIds[index] : 14;
    }

    public static int getBuyCount(int index) {
        return index >= 0 && index < buyCounts.length ? buyCounts[index] : 1;
    }

    public static int getBodyIndex(int index) {
        return index >= 0 && index < bodyIndex.length ? bodyIndex[index] : -1;
    }

    public static int findIndexById(int itemId) {
        for (int i = 0; i < itemIds.length; ++i) {
            if (itemIds[i] == itemId) {
                return i;
            }
        }

        return -1;
    }

    public static int addDefault(int itemId) {
        if (itemId <= 0) {
            return -1;
        }

        int index = findIndexById(itemId);
        boolean isNew = index < 0;
        if (index < 0) {
            index = findEmptyIndex();
            if (index < 0) {
                sort();
                index = findEmptyIndex();
            }
        }

        if (index >= 0) {
            itemIds[index] = (short) itemId;
            if (isNew || delayMs[index] <= 0) {
                delayMs[index] = 2000;
            }
            if (isNew || shopIds[index] <= 0) {
                shopIds[index] = 14;
            }
            if (isNew || buyCounts[index] <= 0) {
                buyCounts[index] = 1;
            }
            if (isNew) {
                bodyIndex[index] = -1;
                enabled[index] = true;
                checkEffect[index] = true;
            } else if (bodyIndex[index] == 0 && !autoBuy[index] && !checkEffect[index]) {
                bodyIndex[index] = -1;
            }
            save();
        }

        return index;
    }

    public static void updateRule(int index, int itemId, boolean isEnabled, boolean isAutoBuy, boolean isCheckEffect, int delay, int shopId, int buyCount, int bodySlot) {
        if (index < 0 || index >= itemIds.length || itemId <= 0) {
            return;
        }

        itemIds[index] = (short) itemId;
        enabled[index] = isEnabled;
        autoBuy[index] = isAutoBuy;
        checkEffect[index] = isCheckEffect;
        delayMs[index] = delay < 500 ? 500 : delay;
        shopIds[index] = shopId;
        buyCounts[index] = buyCount <= 0 ? 1 : buyCount;
        bodyIndex[index] = bodySlot;
        if (pendingUseRule == index) {
            clearPendingUse();
        }
        save();
    }

    public static void removeAt(int index) {
        if (index < 0 || index >= itemIds.length) {
            return;
        }

        clear(index);
        save();
    }

    public static void remove(int itemId) {
        int index = findIndexById(itemId);
        if (index >= 0) {
            removeAt(index);
        }
    }

    public static void sort() {
        clearPendingUse();
        int write = 0;
        for (int read = 0; read < itemIds.length; ++read) {
            if (itemIds[read] > 0) {
                if (write != read) {
                    copy(read, write);
                    clear(read);
                }
                ++write;
            }
        }

        save();
    }

    public static void update() {
        try {
            long now = System.currentTimeMillis();
            if (now - lastCheckAt < CHECK_DELAY || isBusy()) {
                return;
            }
            lastCheckAt = now;

            if (hasPendingUse(now)) {
                return;
            }

            for (int i = 0; i < itemIds.length; ++i) {
                int itemId = itemIds[i];
                if (itemId <= 0 || !enabled[i]) {
                    continue;
                }

                int delay = delayMs[i] < 500 ? 500 : delayMs[i];
                if (now - lastUseAt[i] < delay) {
                    continue;
                }

                if (!shouldUse(i, itemId)) {
                    continue;
                }

                int indexBag = Char.getIndexItemById(itemId);
                if (indexBag >= 0) {
                    Service.getInstance().useItem(indexBag);
                    lastUseAt[i] = now;
                    markPendingUse(i, itemId, now);
                    return;
                }

                if (autoBuy[i] && now - lastBuyAt[i] >= BUY_DELAY && now - lastBuyAttemptAt >= BUY_DELAY && now - lastBuyFailAt[i] >= BUY_FAIL_DELAY) {
                    lastBuyAt[i] = now;
                    lastBuyAttemptAt = now;
                    if (!AutoBuyShop.buyNow(itemId, shopIds[i], buyCounts[i])) {
                        lastBuyFailAt[i] = now;
                    }
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    private static boolean shouldUse(int index, int itemId) {
        int slot = bodyIndex[index];
        if (slot >= 0) {
            Char me = Char.getMyChar();
            return me != null && me.arrItemBody != null && slot < me.arrItemBody.length && me.arrItemBody[slot] == null;
        }

        return !checkEffect[index] || !hasEffect(itemId);
    }

    private static void markPendingUse(int index, int itemId, long now) {
        pendingUseRule = index;
        pendingUseItemId = itemId;
        pendingUseBodySlot = index >= 0 && index < bodyIndex.length ? bodyIndex[index] : -1;
        pendingUseAt = now;
    }

    private static boolean hasPendingUse(long now) {
        if (pendingUseRule < 0) {
            return false;
        }

        if (isPendingUseDone() || now - pendingUseAt >= USE_PENDING_TIMEOUT) {
            clearPendingUse();
            return false;
        }

        return true;
    }

    private static boolean isPendingUseDone() {
        try {
            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null) {
                return true;
            }

            if (pendingUseBodySlot >= 0 && me.arrItemBody != null && pendingUseBodySlot < me.arrItemBody.length && me.arrItemBody[pendingUseBodySlot] != null) {
                return true;
            }

            if (Char.getIndexItemById(pendingUseItemId) < 0) {
                return true;
            }

            if (pendingUseRule >= 0 && pendingUseRule < MAX_RULE && pendingUseBodySlot < 0 && !shouldUse(pendingUseRule, pendingUseItemId)) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }

        return false;
    }

    private static void clearPendingUse() {
        pendingUseRule = -1;
        pendingUseItemId = -1;
        pendingUseBodySlot = -1;
        pendingUseAt = 0L;
    }

    private static boolean hasEffect(int itemId) {
        try {
            ItemTemplate template = ItemTemplateManager.get((short) itemId);
            if (template == null) {
                return false;
            }

            Char me = Char.getMyChar();
            if (me == null || me.vEff == null) {
                return false;
            }

            for (int i = 0; i < me.vEff.size(); ++i) {
                Effect effect = (Effect) me.vEff.elementAt(i);
                if (effect != null && effect.e != null && effect.e.c == template.iconID) {
                    return true;
                }
            }
        } catch (Exception e) {
        }

        return false;
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

    private static int findEmptyIndex() {
        for (int i = 0; i < itemIds.length; ++i) {
            if (itemIds[i] <= 0) {
                return i;
            }
        }

        return -1;
    }

    private static void clear(int index) {
        itemIds[index] = 0;
        enabled[index] = false;
        autoBuy[index] = false;
        checkEffect[index] = false;
        delayMs[index] = 0;
        shopIds[index] = 0;
        buyCounts[index] = 0;
        bodyIndex[index] = -1;
        lastUseAt[index] = 0L;
        lastBuyAt[index] = 0L;
        lastBuyFailAt[index] = 0L;
        if (pendingUseRule == index) {
            clearPendingUse();
        }
    }

    private static void copy(int from, int to) {
        itemIds[to] = itemIds[from];
        enabled[to] = enabled[from];
        autoBuy[to] = autoBuy[from];
        checkEffect[to] = checkEffect[from];
        delayMs[to] = delayMs[from];
        shopIds[to] = shopIds[from];
        buyCounts[to] = buyCounts[from];
        bodyIndex[to] = bodyIndex[from];
        lastUseAt[to] = lastUseAt[from];
        lastBuyAt[to] = lastBuyAt[from];
        lastBuyFailAt[to] = lastBuyFailAt[from];
    }

    public static void save() {
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeInt(MAX_RULE);
            for (int i = 0; i < MAX_RULE; ++i) {
                dataout.writeShort(itemIds[i]);
                dataout.writeBoolean(enabled[i]);
                dataout.writeBoolean(autoBuy[i]);
                dataout.writeBoolean(checkEffect[i]);
                dataout.writeInt(delayMs[i]);
                dataout.writeInt(shopIds[i]);
                dataout.writeInt(buyCounts[i]);
                dataout.writeInt(bodyIndex[i]);
            }
            dataout.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            dataout.close();
            byteout.close();
        } catch (Exception e) {
        }
    }

    public static void load() {
        try {
            byte[] data = RMS.getRecord(STORE_NAME);
            if (data == null) {
                return;
            }

            ByteArrayInputStream bytein = new ByteArrayInputStream(data);
            DataInputStream datain = new DataInputStream(bytein);
            int count = datain.readInt();
            if (count > MAX_RULE) {
                count = MAX_RULE;
            }
            for (int i = 0; i < count; ++i) {
                itemIds[i] = datain.readShort();
                enabled[i] = datain.readBoolean();
                autoBuy[i] = datain.readBoolean();
                checkEffect[i] = datain.readBoolean();
                delayMs[i] = datain.readInt();
                shopIds[i] = datain.readInt();
                buyCounts[i] = datain.readInt();
                bodyIndex[i] = datain.readInt();
            }
            datain.close();
            bytein.close();
        } catch (Exception e) {
        }
    }

    static {
        for (int i = 0; i < MAX_RULE; ++i) {
            bodyIndex[i] = -1;
        }
        load();
    }
}
