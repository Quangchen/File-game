public final class AutoEventTrade extends Auto {

    private static final String CLAN_PREFIX = "gomskgt;";
    private static final String REQ_PREFIX = "gomsk_req;";
    private static final String READY_PREFIX = "gomsk_ready;";
    private static final String WAIT_PREFIX = "gomsk_wait;";

    private static String waitClone = "";
    private static String waitToken = "";
    private static int readyMap = -1;
    private static int readyZone = -1;
    private static int readyCount = 0;
    private static int readyStacks = 0;
    private static long readyAt = 0L;
    private static boolean cloneSending = false;
    private static final long CLONE_TRADE_PAUSE_MS = 30000L;
    private static Auto pausedCloneAuto = null;
    private static long pausedCloneAutoUntil = 0L;
    private static long lastClonePauseAt = 0L;
    private static boolean pauseWatcherRunning = false;

    private int index = 0;
    private int retry = 0;
    private int state = 0;
    private long stateAt = 0L;
    private String[] clones;
    private String currentClone = "";
    private String token = "";
    private int targetMap = -1;
    private int targetZone = -1;
    private long lastNotifyWaitAt = 0L;

    public AutoEventTrade() {
        super.a();
        this.clones = FormEventTrade.getCloneArray();
    }

    public static void startMain() {
        if (!FormEventTrade.isMain()) {
            GameScr.chatPopup("Gom Sự Kiện: hãy chọn vai trò Acc chính");
            return;
        }
        if (FormEventTrade.getCloneArray().length == 0) {
            GameScr.chatPopup("Gom Sự Kiện: chưa có list clone");
            return;
        }
        if (FormEventTrade.TenAccChinh == null || FormEventTrade.TenAccChinh.trim().length() == 0) {
            FormEventTrade.TenAccChinh = Char.getMyChar().charName;
            FormEventTrade.save();
        }
        Code.setAuto(new AutoEventTrade());
        GameScr.chatPopup("Bắt đầu Gom Sự Kiện");
    }

    public static void callClanFromForm() {
        try {
            if (Char.getMyChar().ctypeClan != 4) {
                GameScr.chatPopup("Gom Sự Kiện: chỉ tộc trưởng mới call");
                return;
            }

            String main = FormEventTrade.TenAccChinh;
            if (main == null || main.trim().length() == 0) {
                main = Char.getMyChar().charName;
                FormEventTrade.TenAccChinh = main;
                FormEventTrade.save();
            }

            String clones = FormEventTrade.ListClone == null ? "" : FormEventTrade.ListClone.trim();
            String items = AutoReceiver.getGatherItemsCsv();
            if (items.length() == 0) {
                GameScr.chatPopup("Gom Sự Kiện: list item gom đang rỗng");
                return;
            }

            Service.getInstance().m(CLAN_PREFIX + main + ";" + clones + ";" + items);
            GameScr.chatPopup("Đã call clan Gom Sự Kiện");
        } catch (Exception e) {
            GameScr.chatPopup("Lỗi call Gom Sự Kiện");
        }
    }

    public static boolean onClanChat(String name, String text) {
        try {
            if (text == null || !text.startsWith(CLAN_PREFIX)) {
                return false;
            }
            if (name != null && name.equals(Char.getMyChar().charName)) {
                return true;
            }

            String[] data = Code.splitString(text, ";");
            if (data.length < 4) {
                return true;
            }

            String main = data[1].trim();
            String clones = data[2].trim();
            String items = data[3].trim();

            FormEventTrade.Bat = true;
            FormEventTrade.VaiTro = sameName(Char.getMyChar().charName, main) ? FormEventTrade.ROLE_MAIN : FormEventTrade.ROLE_CLONE;
            FormEventTrade.TenAccChinh = main;
            FormEventTrade.ListClone = clones;
            FormEventTrade.save();

            AutoReceiver.setGatherItemsFromCsv(items);
            GameScr.chatPopup("Gom Sự Kiện: đã nhận call, item gom " + items);
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean onPrivateMessage(String name, String text) {
        try {
            if (text == null) {
                return false;
            }

            if (text.startsWith(REQ_PREFIX)) {
                String[] data = Code.splitString(text, ";");
                if (data.length < 3) {
                    return true;
                }
                String token = data[1];
                String main = data[2];
                if (!FormEventTrade.Bat || !FormEventTrade.isClone()) {
                    return true;
                }
                if (!sameName(name, FormEventTrade.TenAccChinh) || !sameName(main, FormEventTrade.TenAccChinh)) {
                    return true;
                }
                Service.getInstance().a(name, READY_PREFIX + token + ";" + TileMap.mapID + ";" + TileMap.zoneID + ";" + countGatherItems() + ";" + countGatherStacks());
                return true;
            }

            if (text.startsWith(WAIT_PREFIX)) {
                String[] data = Code.splitString(text, ";");
                if (data.length < 3) {
                    return true;
                }
                if (!FormEventTrade.Bat || !FormEventTrade.isClone()) {
                    return true;
                }
                if (!sameName(name, FormEventTrade.TenAccChinh) || !sameName(data[2], FormEventTrade.TenAccChinh)) {
                    return true;
                }
                pauseCloneAutoForTrade("chờ giao dịch");
                return true;
            }

            if (text.startsWith(READY_PREFIX)) {
                String[] data = Code.splitString(text, ";");
                if (data.length < 5) {
                    return true;
                }
                if (!FormEventTrade.isMain()) {
                    return true;
                }
                if (!sameName(name, waitClone) || !data[1].equals(waitToken)) {
                    return true;
                }
                readyMap = Integer.parseInt(data[2]);
                readyZone = Integer.parseInt(data[3]);
                readyCount = Integer.parseInt(data[4]);
                readyStacks = data.length >= 6 ? Integer.parseInt(data[5]) : readyCount;
                readyAt = System.currentTimeMillis();
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static boolean onTradeInvite(String name, int charId) {
        try {
            if (!FormEventTrade.Bat || !FormEventTrade.isClone()) {
                return false;
            }
            if (!sameName(name, FormEventTrade.TenAccChinh)) {
                return false;
            }
            pauseCloneAutoForTrade("nhận giao dịch");
            Service.getInstance().l(charId);
            LockGame.ag();
            startCloneSendThread();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void run() {
        if (!FormEventTrade.isMain()) {
            Code.backToInstance();
            return;
        }

        if (this.clones == null || this.clones.length == 0) {
            GameScr.chatPopup("Gom Sự Kiện: hết clone");
            Code.backToInstance();
            return;
        }

        while (this.index < this.clones.length && (this.clones[this.index] == null || this.clones[this.index].trim().length() == 0)) {
            ++this.index;
        }

        if (this.index >= this.clones.length) {
            GameScr.chatPopup("Gom Sự Kiện: xong list clone");
            Code.backToInstance();
            return;
        }

        this.currentClone = this.clones[this.index].trim();
        switch (this.state) {
            case 0:
                requestCloneInfo();
                return;
            case 1:
                waitCloneInfo();
                return;
            case 2:
                goToCloneMap();
                return;
            case 3:
                waitMap();
                return;
            case 4:
                goToCloneZone();
                return;
            case 5:
                waitZone();
                return;
            case 6:
                tradeCurrentClone();
                return;
            default:
                nextClone();
        }
    }

    public String toString() {
        return "Gom Sự Kiện";
    }

    private void requestCloneInfo() {
        this.token = String.valueOf(System.currentTimeMillis());
        waitClone = this.currentClone;
        waitToken = this.token;
        readyMap = -1;
        readyZone = -1;
        readyCount = 0;
        readyStacks = 0;
        readyAt = 0L;
        Service.getInstance().a(this.currentClone, REQ_PREFIX + this.token + ";" + FormEventTrade.TenAccChinh);
        this.stateAt = System.currentTimeMillis();
        this.state = 1;
        GameScr.chatPopup("Gom Sự Kiện: hỏi " + this.currentClone);
    }

    private void waitCloneInfo() {
        if (readyAt > 0L && sameName(waitClone, this.currentClone) && waitToken.equals(this.token)) {
            this.targetMap = readyMap;
            this.targetZone = readyZone;
            if (readyStacks <= 0) {
                GameScr.chatPopup("Gom Sự Kiện: " + this.currentClone + " hết item");
                nextClone();
                return;
            }
            GameScr.chatPopup("Gom Sự Kiện: " + this.currentClone + " map " + this.targetMap + " khu " + this.targetZone + " item " + readyCount);
            notifyCloneWaiting();
            this.lastNotifyWaitAt = System.currentTimeMillis();
            this.retry = 0;
            this.state = 2;
            return;
        }

        if (System.currentTimeMillis() - this.stateAt > 8000L) {
            if (++this.retry >= FormEventTrade.Retry) {
                GameScr.chatPopup("Gom Sự Kiện: bỏ qua " + this.currentClone + " không trả lời");
                nextClone();
            } else {
                requestCloneInfo();
            }
        }
    }

    private void goToCloneMap() {
        notifyCloneWaitingIfNeeded();
        if (TileMap.mapID == this.targetMap && TileMap.zoneID == this.targetZone) {
            this.state = 6;
            return;
        }
        if (TileMap.mapID == this.targetMap) {
            this.state = 4;
            return;
        }
        if (AutoVBL.useTo(this.currentClone)) {
            this.stateAt = System.currentTimeMillis();
            this.state = 3;
            return;
        }
        tryFastTeleport(this.targetMap);
        TileMap.gomap(this.targetMap);
        this.stateAt = System.currentTimeMillis();
        this.state = 3;
    }

    private void waitMap() {
        notifyCloneWaitingIfNeeded();
        if (TileMap.mapID == this.targetMap) {
            this.state = 4;
            return;
        }
        if (System.currentTimeMillis() - this.stateAt > 45000L) {
            GameScr.chatPopup("Gom Sự Kiện: lỗi đến map " + this.currentClone);
            nextClone();
        }
    }

    private void goToCloneZone() {
        notifyCloneWaitingIfNeeded();
        if (TileMap.zoneID == this.targetZone) {
            this.state = 6;
            return;
        }
        changeZoneFast(this.targetZone);
        this.stateAt = System.currentTimeMillis();
        this.state = 5;
    }

    private void waitZone() {
        notifyCloneWaitingIfNeeded();
        if (TileMap.zoneID == this.targetZone) {
            this.state = 6;
            return;
        }
        if (System.currentTimeMillis() - this.stateAt > 15000L) {
            GameScr.chatPopup("Gom Sự Kiện: lỗi đến khu " + this.currentClone);
            nextClone();
        }
    }

    private void tradeCurrentClone() {
        notifyCloneWaiting();
        Char clone = findChar(this.currentClone);
        if (clone == null) {
            if (++this.retry >= FormEventTrade.Retry) {
                GameScr.chatPopup("Gom Sự Kiện: không thấy " + this.currentClone);
                nextClone();
            } else {
                requestCloneInfo();
            }
            return;
        }

        boolean ok = inviteAndReceive(clone);
        GameScr.chatPopup("Gom Sự Kiện: " + this.currentClone + (ok ? " xong" : " lỗi"));
        Auto.sleep(FormEventTrade.Delay);
        if (ok) {
            requestCloneInfo();
        } else {
            nextClone();
        }
    }

    private boolean inviteAndReceive(Char clone) {
        try {
            Char me = Char.getMyChar();
            if (Res.distance(me.cx, me.cy, clone.cx, clone.cy) >= 50) {
                Char.charMove(clone.cx, clone.cy);
                Auto.sleep(1000L);
            }

            AutoSell.a = false;
            Service.getInstance().tradeInvite(clone.charID);
            long start = System.currentTimeMillis();
            while (!GameScr.ci && System.currentTimeMillis() - start < 12000L) {
                Auto.sleep(200L);
            }

            GameScr.getInstance().db = 0;
            GameScr.arrItemTradeMe = new Item[12];
            Service.getInstance().tradeLock(0, GameScr.arrItemTradeMe);
            GameScr.getInstance().cz = 1;

            start = System.currentTimeMillis();
            while (GameScr.getInstance().da != 1 && System.currentTimeMillis() - start < 20000L) {
                if (AutoSell.a) {
                    return false;
                }
                Auto.sleep(200L);
            }

            Auto.sleep(3000L);
            Service.getInstance().j();
            return LockGame.a(20000L);
        } catch (Exception e) {
            return false;
        }
    }

    private void nextClone() {
        ++this.index;
        this.retry = 0;
        this.state = 0;
        this.stateAt = 0L;
        this.currentClone = "";
        this.lastNotifyWaitAt = 0L;
    }

    private void notifyCloneWaiting() {
        try {
            Service.getInstance().a(this.currentClone, WAIT_PREFIX + this.token + ";" + FormEventTrade.TenAccChinh);
        } catch (Exception e) {
        }
    }

    private void notifyCloneWaitingIfNeeded() {
        if (System.currentTimeMillis() - this.lastNotifyWaitAt >= 10000L) {
            notifyCloneWaiting();
            this.lastNotifyWaitAt = System.currentTimeMillis();
        }
    }

    private static void tryFastTeleport(int map) {
        int option = getFastTeleportOption(map);
        if (option < 0) {
            return;
        }

        int index = Char.getIndexItemById(37);
        if (index < 0) {
            index = Char.getIndexItemById(35);
        }

        if (index < 0) {
            return;
        }

        try {
            Item item = Char.getMyChar().arrItemBag[index];
            if (item == null) {
                return;
            }
            Service.getInstance().useItemChangeMap(item.indexUI, option);
            TileMap.g();
            Auto.sleep(500L);
        } catch (Exception e) {
        }
    }

    private static void changeZoneFast(int zone) {
        try {
            if (TileMap.zoneID == zone) {
                return;
            }

            int itemIndex = -1;
            Npc npc = GameScr.findNpc(13);
            if ((npc == null || npc.statusMe == 15)
                    && (TileMap.mapID == 99 || TileMap.mapID == 103 || TileMap.mapID == 134 || TileMap.mapID == 135 || TileMap.mapID == 136 || TileMap.mapID == 137)) {
                itemIndex = Char.getIndexItemById(37);
                if (itemIndex < 0) {
                    itemIndex = Char.getIndexItemById(35);
                }
            }
            Service.getInstance().requestChangeZone(zone, itemIndex);
            TileMap.g();
            Auto.sleep(500L);
        } catch (Exception e) {
            try {
                GameScr.getInstance().j(zone);
            } catch (Exception ex) {
            }
        }
    }

    private static int getFastTeleportOption(int map) {
        switch (map) {
            case 1:
                return 0;
            case 27:
                return 1;
            case 72:
                return 2;
            case 10:
                return 3;
            case 17:
                return 4;
            case 22:
                return 5;
            case 32:
                return 6;
            case 38:
                return 7;
            case 43:
                return 8;
            case 48:
                return 9;
            default:
                if (map >= 24 && map <= 37) {
                    return 1;
                }
                if (map >= 39 && map <= 56 || map >= 62 && map <= 68 || map >= 70 && map <= 72) {
                    return 2;
                }
                if (map > 1 && map <= 23 || map == 57 || map == 58 || map == 69 || map >= 73 && map <= 79 || map >= 91 && map <= 97 || map >= 105 && map <= 109 || map >= 114 && map <= 128 || map >= 157 && map <= 159) {
                    return 0;
                }
                return -1;
        }
    }

    private static void startCloneSendThread() {
        if (cloneSending) {
            return;
        }
        cloneSending = true;
        final long tradeStartedAt = System.currentTimeMillis();
        (new Thread(new Runnable() {
            public void run() {
                try {
                    sendGatherToMain();
                } finally {
                    cloneSending = false;
                    restoreCloneAutoAfterTrade(tradeStartedAt);
                }
            }
        })).start();
    }

    private static void sendGatherToMain() {
        try {
            long start = System.currentTimeMillis();
            while (!GameScr.ci && System.currentTimeMillis() - start < 12000L) {
                Auto.sleep(200L);
            }

            Item[] items = buildTradeItems();
            GameScr.getInstance().db = 0;
            GameScr.arrItemTradeMe = items;
            Service.getInstance().tradeLock(0, items);
            GameScr.getInstance().cz = 1;

            start = System.currentTimeMillis();
            while (GameScr.getInstance().da != 1 && System.currentTimeMillis() - start < 20000L) {
                if (AutoSell.a) {
                    return;
                }
                Auto.sleep(200L);
            }

            Auto.sleep(3000L);
            Service.getInstance().j();
            if (LockGame.a(20000L)) {
                for (int i = 0; i < items.length; ++i) {
                    if (items[i] != null) {
                        Char.getMyChar().arrItemBag[items[i].indexUI] = null;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private static Item[] buildTradeItems() {
        Item[] result = new Item[12];
        int count = 0;
        Item[] bag = Char.getMyChar().arrItemBag;
        for (int i = 0; i < AutoReceiver.gatherListID.length; ++i) {
            for (int j = 0; j < bag.length; ++j) {
                Item item = bag[j];
                if (count < 12 && item != null && item.template.id == AutoReceiver.gatherListID[i] && !item.isLock) {
                    result[count++] = item;
                }
            }
        }
        return result;
    }

    private static int countGatherItems() {
        int count = 0;
        Item[] bag = Char.getMyChar().arrItemBag;
        for (int i = 0; i < AutoReceiver.gatherListID.length; ++i) {
            for (int j = 0; j < bag.length; ++j) {
                Item item = bag[j];
                if (item != null && item.template.id == AutoReceiver.gatherListID[i] && !item.isLock) {
                    count += item.template.isUpToUp ? item.quantity : 1;
                }
            }
        }
        return count;
    }

    private static int countGatherStacks() {
        int count = 0;
        Item[] bag = Char.getMyChar().arrItemBag;
        for (int i = 0; i < AutoReceiver.gatherListID.length; ++i) {
            for (int j = 0; j < bag.length; ++j) {
                Item item = bag[j];
                if (item != null && item.template.id == AutoReceiver.gatherListID[i] && !item.isLock) {
                    ++count;
                }
            }
        }
        return count;
    }

    private static Char findChar(String name) {
        for (int i = 0; i < GameScr.vCharInMap.size(); ++i) {
            Char c = (Char) GameScr.vCharInMap.elementAt(i);
            if (c != null && sameName(c.charName, name)) {
                return c;
            }
        }
        return null;
    }

    private static void pauseCloneAutoForTrade(String reason) {
        try {
            if (!FormEventTrade.isClone()) {
                return;
            }

            pausedCloneAutoUntil = System.currentTimeMillis() + CLONE_TRADE_PAUSE_MS;
            lastClonePauseAt = System.currentTimeMillis();
            if (pausedCloneAuto == null && Code.auto != null && !(Code.auto instanceof AutoEventTrade)) {
                pausedCloneAuto = Code.auto;
                LockGame.tatAuto();
                Code.auto = null;
                GameScr.chatPopup("Gom Sự Kiện: tạm dừng auto " + reason);
            }

            startPauseWatcher();
        } catch (Exception e) {
        }
    }

    private static void startPauseWatcher() {
        try {
            if (pauseWatcherRunning) {
                return;
            }

            pauseWatcherRunning = true;
            (new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            long wait = pausedCloneAutoUntil - System.currentTimeMillis();
                            if (wait <= 0L) {
                                if (cloneSending) {
                                    pausedCloneAutoUntil = System.currentTimeMillis() + 1000L;
                                } else {
                                    break;
                                }
                            }
                            Auto.sleep(500L);
                        }
                        restoreCloneAuto("hết 30s");
                    } catch (Exception e) {
                    } finally {
                        pauseWatcherRunning = false;
                    }
                }
            })).start();
        } catch (Exception e) {
            pauseWatcherRunning = false;
        }
    }

    private static void restoreCloneAutoAfterTrade(long tradeStartedAt) {
        try {
            if (lastClonePauseAt > tradeStartedAt && pausedCloneAutoUntil > System.currentTimeMillis()) {
                return;
            }
            restoreCloneAuto("sau gd");
        } catch (Exception e) {
        }
    }

    private static void restoreCloneAuto(String reason) {
        try {
            if (pausedCloneAuto == null) {
                pausedCloneAutoUntil = 0L;
                lastClonePauseAt = 0L;
                return;
            }

            if (Code.auto == null) {
                Code.auto = pausedCloneAuto;
                GameScr.chatPopup("Gom Sự Kiện: bật lại auto " + reason);
            }
            pausedCloneAuto = null;
            pausedCloneAutoUntil = 0L;
            lastClonePauseAt = 0L;
        } catch (Exception e) {
        }
    }

    private static boolean sameName(String a, String b) {
        return a != null && b != null && a.trim().equalsIgnoreCase(b.trim());
    }
}
