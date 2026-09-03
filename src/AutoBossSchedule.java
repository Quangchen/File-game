public final class AutoBossSchedule extends Auto {

    private static final long SESSION_TIMEOUT = 7200000L;
    private static final long MAP_WAIT = 1200L;
    private static final long ZONE_WAIT = 2000L;
    private static final long ZONE_SCAN_GRACE = 2000L;
    private static final long DEATH_RECOVER_SCAN_GRACE = 15000L;
    private static final long BOSS_GONE_CONFIRM_WAIT = 3500L;
    private static final long RETRY_DELAY = 2000L;
    private static final long ZONE_RETRY_STUCK_WAIT = 2500L;
    private static final int MAX_ZONE_RETRIES = 4;
    private static final int LANG_CO_ZONE_COUNT = 15;
    private static final int LANG_TT_ZONE_COUNT = 15;
    private static final int VDMQ_ZONE_COUNT = 30;
    private static final int LANG_CO_HUB_MAP = 138;
    private static final int LANG_TT_HUB_MAP = 162;
    private static final int ITEM_KHA_DI_LENH = 35;
    private static final int ITEM_VO_HAN_KHA_DI_LENH = 37;
    private static final int MOB_MY_HAU_VUONG = 210;

    private static final int[] MAP_LANG_CO = new int[]{134, 135, 136, 137};
    private static final int[] MAP_LTT = new int[]{163, 164, 165};
    private static final int[] MAP_VDMQ = new int[]{141, 142, 143};

    private int[] maps;
    private int mapIndex = 0;
    private int scanZone = 0;
    private int zoneCount = -1;
    private int requestedZone = -1;
    private int loadedZone = -1;
    private int zoneListAttempts = 0;
    private int zoneRequestAttempts = 0;
    private int bossId = -1;
    private int killedBossCount = 0;
    private int mapStartKillCount = 0;
    private boolean zoneCountFromServer = false;
    private long startedAt = System.currentTimeMillis();
    private long mapAt = 0L;
    private long zoneListAt = 0L;
    private long zoneAt = 0L;
    private long zoneScanAt = 0L;
    private long bossGoneAt = 0L;
    private long buyKhaDiAt = 0L;
    private long lastDeathRemapAt = 0L;
    private boolean recoveringAfterDeath = false;
    private int recoverMapIndex = -1;
    private int recoverZone = -1;
    private long recoverScanUntil = 0L;
    private long recoverNoticeAt = 0L;

    public AutoBossSchedule(int eventType) {
        super.a();
        this.maps = buildMaps(eventType);
        super.mapID = this.maps.length > 0 ? this.maps[0] : -1;
        super.zoneID = -2;
        super.isHang = false;
        super.k = -1;
        super.l = -1;
        GameScr.chatPopup(this.maps.length > 0 ? "Auto boss: bắt đầu quét " + this.maps.length + " map" : "Auto boss: không có map hợp lệ");
    }

    protected final void run() {
        if (this.maps.length == 0 || System.currentTimeMillis() - this.startedAt > SESSION_TIMEOUT) {
            this.finish();
            return;
        }

        if (super.isDead()) {
            this.markDeathRecoveryPoint();
            if (System.currentTimeMillis() - this.lastDeathRemapAt < 3000L) {
                return;
            }

            this.lastDeathRemapAt = System.currentTimeMillis();
            Auto.autoRemap(false);
            return;
        }

        if (this.mapIndex >= this.maps.length) {
            if (this.restoreRecoverPointIfNeeded()) {
                return;
            }
            this.finish();
            return;
        }

        this.restoreRecoverPointIfNeeded();
        int targetMap = this.maps[this.mapIndex];
        this.enableTicketRoute(targetMap);

        if (this.buyKhaDiLenhAtLangCoEntrance(targetMap)) {
            return;
        }

        if (TileMap.mapID != targetMap) {
            this.markUnexpectedDropFromTarget(targetMap);
            this.noticeRecovering(targetMap);

            if (this.mustLeaveSpecialRegion(targetMap)) {
                Auto.tuSat();
                return;
            }

            int routeMap = this.getSpecialHubMap(targetMap);
            this.goMap(routeMap >= 0 ? routeMap : targetMap, -2, -1, -1);
            this.mapAt = System.currentTimeMillis();
            this.resetZoneScan();
            return;
        }

        if (this.recoveringAfterDeath) {
            this.restoreRecoverPointIfNeeded();
            if (this.zoneCount > 0 && this.scanZone >= 0 && TileMap.zoneID != this.scanZone) {
                this.changeScanZone(this.scanZone);
                return;
            }

            this.recoveringAfterDeath = false;
            this.loadedZone = -1;
            this.zoneScanAt = 0L;
            this.mapAt = 0L;
            this.recoverScanUntil = System.currentTimeMillis() + DEATH_RECOVER_SCAN_GRACE;
            GameScr.chatPopup("Auto boss: đã quay lại, tiếp tục săn");
        }

        if (this.mapAt == 0L) {
            this.mapAt = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() - this.mapAt < MAP_WAIT) {
            return;
        }

        if (this.zoneCount < 0) {
            this.requestZoneCount();
            return;
        }

        if (this.scanZone >= this.zoneCount) {
            this.nextMap();
            return;
        }

        if (TileMap.zoneID != this.scanZone) {
            this.changeScanZone(this.scanZone);
            return;
        }

        if (this.requestedZone == TileMap.zoneID && System.currentTimeMillis() - this.zoneAt < ZONE_WAIT) {
            return;
        }

        if (this.loadedZone != TileMap.zoneID) {
            this.loadedZone = TileMap.zoneID;
            this.zoneScanAt = System.currentTimeMillis();
        }

        Mob boss = findBoss();

        if (boss != null) {
            this.bossId = getBossAttackId(boss);
            this.bossGoneAt = 0L;
            this.pickUpItem(-1);
            this.attack(this.bossId, -1);
            return;
        }

        this.pickUpItem(-1);

        if (this.bossId >= 0) {
            if (this.bossGoneAt == 0L) {
                this.bossGoneAt = System.currentTimeMillis();
                return;
            }

            if (System.currentTimeMillis() - this.bossGoneAt < BOSS_GONE_CONFIRM_WAIT) {
                return;
            }

            this.killedBossCount++;
            this.bossId = -1;
            this.bossGoneAt = 0L;
            if (isSingleBossMap(targetMap) && this.killedBossCount > this.mapStartKillCount) {
                this.nextMap();
                return;
            }
        }

        if (System.currentTimeMillis() - this.zoneScanAt < ZONE_SCAN_GRACE
                || System.currentTimeMillis() < this.recoverScanUntil) {
            return;
        }

        this.scanZone++;

        if (this.scanZone >= this.zoneCount) {
            this.nextMap();
        }
    }

    private void changeScanZone(int zone) {
        long now = System.currentTimeMillis();

        if (this.requestedZone == zone && now - this.zoneAt < RETRY_DELAY) {
            return;
        }

        if (this.requestedZone != zone) {
            this.requestedZone = zone;
            this.zoneRequestAttempts = 0;
        }

        if (this.zoneRequestAttempts >= MAX_ZONE_RETRIES) {
            if (this.shouldRetryZoneUntilLoaded()) {
                GameScr.chatPopup("Auto boss: kẹt khu " + zone + ", thử lại");
                this.requestedZone = -1;
                this.zoneRequestAttempts = 0;
                this.zoneAt = now + ZONE_RETRY_STUCK_WAIT - RETRY_DELAY;
                this.resetZoneScan();
                return;
            }

            GameScr.chatPopup("Auto boss: bỏ qua khu dự phòng " + zone);
            this.scanZone++;
            this.requestedZone = -1;
            this.zoneRequestAttempts = 0;
            this.resetZoneScan();
            return;
        }

        Service.getInstance().requestChangeZone(zone, this.prepareZoneChange());
        TileMap.g();
        this.zoneRequestAttempts++;
        this.zoneAt = now;
        this.resetZoneScan();
    }

    private void requestZoneCount() {
        long now = System.currentTimeMillis();
        int strictZoneCount = getStrictZoneCount(TileMap.mapID);

        if (strictZoneCount > 0) {
            this.applyZoneCount(strictZoneCount, true);
            GameScr.chatPopup("Auto boss: map " + TileMap.mapID + " quét khu 0-" + (strictZoneCount - 1));
            return;
        }

        if (this.zoneListAttempts >= MAX_ZONE_RETRIES) {
            int knownZoneCount = getKnownZoneCount(TileMap.mapID);
            if (knownZoneCount > 0) {
                GameScr.chatPopup("Auto boss: dùng số khu dự phòng " + knownZoneCount);
                this.applyZoneCount(knownZoneCount, false);
                return;
            }

            GameScr.chatPopup("Auto boss: bỏ qua map " + TileMap.mapID);
            this.nextMap();
            return;
        }

        if (now - this.zoneListAt < RETRY_DELAY) {
            return;
        }

        if (this.prepareZoneChange() == -2) {
            this.zoneListAttempts++;
            this.zoneListAt = now;
            return;
        }

        Service.getInstance().openUIZone();
        this.zoneListAttempts++;
        this.zoneListAt = now;
    }

    private int prepareZoneChange() {
        Npc npc = GameScr.findNpc(13);

        if (npc != null && npc.statusMe != 15) {
            if (Math.abs(npc.cx - Char.getMyChar().cx) > 22 || Math.abs(npc.cy - Char.getMyChar().cy) > 22) {
                Char.charMove(npc.cx, npc.cy);
            }

            return -1;
        }

        if (TileMap.isLangCo(TileMap.mapID)) {
            int itemIndex = Char.getIndexItemById(ITEM_VO_HAN_KHA_DI_LENH);

            if (itemIndex < 0) {
                itemIndex = Char.getIndexItemById(ITEM_KHA_DI_LENH);
            }

            return itemIndex >= 0 ? itemIndex : -1;
        }

        return -1;
    }

    private boolean buyKhaDiLenhAtLangCoEntrance(int targetMap) {
        if (!TileMap.isLangCo(targetMap) || TileMap.mapID != 138
                || Char.hasItem(ITEM_KHA_DI_LENH) || Char.hasItem(ITEM_VO_HAN_KHA_DI_LENH)) {
            return false;
        }

        long now = System.currentTimeMillis();

        if (now - this.buyKhaDiAt < RETRY_DELAY) {
            return true;
        }

        GameScr.chatPopup("Auto boss: mua Khả Di Lệnh tại NPC 4");
        GameScr.PickNpc(4, 0, 0);
        Service.getInstance().buyItem1(9, 6, 1);
        LockGame.g();
        this.buyKhaDiAt = now;
        return true;
    }

    public final boolean receiveZoneCount(int count) {
        if (this.zoneCount >= 0 || this.zoneListAt == 0L || count <= 0) {
            return false;
        }

        this.applyZoneCount(count, true);
        GameScr.chatPopup("Auto boss: map " + TileMap.mapID + " có " + this.zoneCount + " khu");
        return true;
    }

    private void applyZoneCount(int count) {
        this.applyZoneCount(count, false);
    }

    private void applyZoneCount(int count, boolean fromServer) {
        if (count <= 0) {
            count = TileMap.zoneID + 1;
        }

        this.zoneCount = count;
        this.zoneCountFromServer = fromServer;
        this.scanZone = 0;
        this.requestedZone = -1;
        this.resetZoneScan();
        this.zoneListAttempts = 0;
        this.zoneRequestAttempts = 0;
    }

    private void nextMap() {
        this.mapIndex++;
        this.mapStartKillCount = this.killedBossCount;
        this.scanZone = 0;
        this.zoneCount = -1;
        this.zoneCountFromServer = false;
        this.requestedZone = -1;
        this.resetZoneScan();
        this.zoneListAttempts = 0;
        this.zoneRequestAttempts = 0;
        this.bossId = -1;
        this.bossGoneAt = 0L;
        this.mapAt = 0L;
        this.zoneListAt = 0L;
        this.recoverScanUntil = 0L;

        if (this.mapIndex < this.maps.length) {
            super.mapID = this.maps[this.mapIndex];
            GameScr.chatPopup("Auto boss: quét map " + super.mapID);
        }
    }

    private void finish() {
        if (TileMap.isLangCo(TileMap.mapID) || TileMap.isLangTT(TileMap.mapID) || TileMap.isVDMQ(TileMap.mapID)) {
            if (Auto.goTruongIfNeeded()) {
                return;
            }
        }

        GameScr.chatPopup("Auto boss: đã quét xong");
        Code.backToInstance();
    }

    private void enableTicketRoute(int map) {
        if (TileMap.isLangCo(map)) {
            Char.tickAutoCoLenh = true;
            Char.tickAutoMuaCoLenh = true;
        }

        if (TileMap.isLangTT(map)) {
            Char.tickAutoLangThuyenThuyet = true;
            Char.tickAutoMuaTruyenThuyetLenh = true;
        }
    }

    private void noticeRecovering(int targetMap) {
        if (!this.recoveringAfterDeath) {
            return;
        }

        long now = System.currentTimeMillis();

        if (now - this.recoverNoticeAt < 2500L) {
            return;
        }

        GameScr.chatPopup("Auto boss: bị rơi map, quay lại " + targetMap);
        this.recoverNoticeAt = now;
    }

    private void markUnexpectedDropFromTarget(int targetMap) {
        if (this.recoveringAfterDeath || this.zoneCount <= 0 || this.scanZone < 0 || this.scanZone >= this.zoneCount) {
            return;
        }

        if (this.loadedZone < 0 && this.zoneScanAt == 0L && this.bossId < 0 && this.bossGoneAt == 0L) {
            return;
        }

        this.recoveringAfterDeath = true;
        this.recoverMapIndex = this.mapIndex;
        this.recoverZone = this.scanZone;
        this.bossId = -1;
        this.bossGoneAt = 0L;
        this.loadedZone = -1;
        this.zoneScanAt = 0L;
        this.mapAt = 0L;
        this.requestedZone = -1;
        this.zoneRequestAttempts = 0;
        this.zoneAt = 0L;
    }

    private void markDeathRecoveryPoint() {
        this.recoveringAfterDeath = true;
        if (this.mapIndex >= 0 && this.mapIndex < this.maps.length) {
            this.recoverMapIndex = this.mapIndex;
            super.mapID = this.maps[this.mapIndex];
        }

        if (this.zoneCount > 0 && this.scanZone >= 0 && this.scanZone < this.zoneCount) {
            this.recoverZone = this.scanZone;
        } else if (TileMap.mapID == super.mapID && TileMap.zoneID >= 0) {
            this.recoverZone = TileMap.zoneID;
            this.scanZone = TileMap.zoneID;
        }

        if (this.recoverZone >= 0) {
            this.scanZone = this.recoverZone;
        }

        this.bossId = -1;
        this.bossGoneAt = 0L;
        this.loadedZone = -1;
        this.zoneScanAt = 0L;
        this.requestedZone = -1;
        this.zoneRequestAttempts = 0;
        this.zoneAt = 0L;
        this.mapAt = 0L;
    }

    private boolean restoreRecoverPointIfNeeded() {
        if (!this.recoveringAfterDeath || this.recoverMapIndex < 0 || this.recoverMapIndex >= this.maps.length) {
            return false;
        }

        if (this.mapIndex != this.recoverMapIndex) {
            this.mapIndex = this.recoverMapIndex;
            super.mapID = this.maps[this.mapIndex];
        }

        if (this.recoverZone >= 0) {
            this.scanZone = this.recoverZone;
        }

        return true;
    }

    private boolean mustLeaveSpecialRegion(int targetMap) {
        return TileMap.isLangCo(TileMap.mapID) && !TileMap.isLangCo(targetMap)
                || TileMap.isLangTT(TileMap.mapID) && !TileMap.isLangTT(targetMap);
    }

    private int getSpecialHubMap(int targetMap) {
        if (TileMap.isLangTT(targetMap)
                && TileMap.isLangTT(TileMap.mapID)
                && TileMap.mapID != LANG_TT_HUB_MAP
                && TileMap.mapID != targetMap) {
            return LANG_TT_HUB_MAP;
        }

        if (TileMap.isLangCo(targetMap)
                && TileMap.isLangCo(TileMap.mapID)
                && TileMap.mapID != LANG_CO_HUB_MAP
                && TileMap.mapID != targetMap) {
            return LANG_CO_HUB_MAP;
        }

        return -1;
    }

    private void resetZoneScan() {
        this.loadedZone = -1;
        this.zoneScanAt = 0L;
    }

    private static int getKnownZoneCount(int map) {
        if (TileMap.isLangCo(map)) {
            return LANG_CO_ZONE_COUNT;
        }

        if (TileMap.isLangTT(map)) {
            return LANG_TT_ZONE_COUNT;
        }

        if (TileMap.isVDMQ(map)) {
            return VDMQ_ZONE_COUNT;
        }

        return -1;
    }

    private boolean shouldRetryZoneUntilLoaded() {
        int targetMap = this.mapIndex >= 0 && this.mapIndex < this.maps.length ? this.maps[this.mapIndex] : TileMap.mapID;
        return isStrictPerZoneBossMap(targetMap) || this.zoneCountFromServer;
    }

    private static boolean isStrictPerZoneBossMap(int map) {
        return TileMap.isLangCo(map) || TileMap.isLangTT(map);
    }

    private static boolean isSingleBossMap(int map) {
        return TileMap.isVDMQ(map);
    }

    private static int getStrictZoneCount(int map) {
        if (TileMap.isLangCo(map)) {
            return LANG_CO_ZONE_COUNT;
        }

        if (TileMap.isLangTT(map)) {
            return LANG_TT_ZONE_COUNT;
        }

        return -1;
    }

    static boolean isBossTarget(Mob mob) {
        return mob != null && (mob.isBoss || mob.id == MOB_MY_HAU_VUONG || getMobTemplateServerId(mob) == MOB_MY_HAU_VUONG) && mob.hp > 0 && mob.h != 0 && mob.h != 1;
    }

    private static int getBossAttackId(Mob mob) {
        int serverId = getMobTemplateServerId(mob);
        return serverId > 0 ? serverId : mob.id;
    }

    private static Mob findBoss() {
        for (int i = 0; i < GameScr.vMobAttack.size(); i++) {
            Mob mob = (Mob) GameScr.vMobAttack.elementAt(i);

            if (isBossTarget(mob)) {
                return mob;
            }
        }

        return null;
    }

    private static int[] buildMaps(int eventType) {
        int count = 0;

        if ((eventType == 0 || eventType == 1) && FormAutoBoss.LangCo) {
            count += countSelectedMaps(MAP_LANG_CO);
        }

        if ((eventType == 0 || eventType == 1) && FormAutoBoss.LTT) {
            count += countSelectedMaps(MAP_LTT);
        }

        if ((eventType == 0 || eventType == 2) && FormAutoBoss.VDMQ) {
            count += countSelectedMaps(MAP_VDMQ);
        }

        int[] result = new int[count];
        int index = 0;

        if ((eventType == 0 || eventType == 1) && FormAutoBoss.LangCo) {
            index = appendSelected(result, index, MAP_LANG_CO);
        }

        if ((eventType == 0 || eventType == 1) && FormAutoBoss.LTT) {
            index = appendSelected(result, index, MAP_LTT);
        }

        if ((eventType == 0 || eventType == 2) && FormAutoBoss.VDMQ) {
            appendSelected(result, index, MAP_VDMQ);
        }

        return result;
    }

    private static int countSelectedMaps(int[] values) {
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            if (FormAutoBoss.isMapAllowed(values[i])) {
                count++;
            }
        }

        return count;
    }

    private static int appendSelected(int[] result, int index, int[] values) {
        for (int i = 0; i < values.length; i++) {
            if (FormAutoBoss.isMapAllowed(values[i])) {
                result[index++] = values[i];
            }
        }

        return index;
    }

    static boolean hasAnnouncedMap(int eventType, String message) {
        if (message == null || TileMap.mapNames == null) {
            return false;
        }

        if ((eventType == 0 || eventType == 1) && FormAutoBoss.LangCo && containsMapName(MAP_LANG_CO, message)) {
            return true;
        }

        if ((eventType == 0 || eventType == 1) && FormAutoBoss.LTT && containsMapName(MAP_LTT, message)) {
            return true;
        }

        return (eventType == 0 || eventType == 2) && FormAutoBoss.VDMQ && containsMapName(MAP_VDMQ, message);
    }

    private static boolean containsMapName(int[] maps, String message) {
        for (int i = 0; i < maps.length; i++) {
            int map = maps[i];

            if (map >= 0 && map < TileMap.mapNames.length && TileMap.mapNames[map] != null
                    && message.indexOf(TileMap.mapNames[map]) >= 0) {
                return true;
            }
        }

        return false;
    }

    public final String toString() {
        return "Auto boss map " + super.mapID + " khu " + TileMap.zoneID + " td:" + this.killedBossCount;
    }
}
