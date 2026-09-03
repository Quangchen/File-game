
public final class AutoUp extends Auto {

    private int mobId;
    private int[] mobIds;
    private long noTargetSince;

    public AutoUp() {
    }

    public final void init(int mapId, int zoneId) {
        this.init(mapId, zoneId, FormAutoUp.getTargetMobIds());
    }

    public final void init(int mapId, int zoneId, int[] targetMobIds) {
        super.a();
        super.mapID = mapId;
        super.zoneID = zoneId;
        super.g = true;
        super.isHang = false;
        super.k = -1;
        super.l = -1;
        this.mobId = -1;
        this.mobIds = copyMobIds(targetMobIds);
        this.noTargetSince = 0L;
    }

    protected final void run() {
        if (super.isDead()) {
            Auto.autoRemap(true);
            return;
        }

        if (super.mapID != TileMap.mapID || super.zoneID >= 0 && super.zoneID != TileMap.zoneID) {
            this.goMap(super.mapID, super.zoneID, super.k, super.l);
            return;
        }

        int targetMobId = this.getAttackMobId();
        if (targetMobId == -2) {
            this.handleNoTargetMob();
            this.pickUpItem(-1);
            return;
        }

        this.attack(targetMobId, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
        this.pickUpItem(-1);
    }

    public final String toString() {
        return "Auto Up map " + super.mapID + " khu " + super.zoneID + this.getMobIdsText();
    }

    private int getAttackMobId() {
        if (this.mobIds == null || this.mobIds.length == 0) {
            this.mobId = -1;
            this.noTargetSince = 0L;
            return -1;
        }

        Char me = Char.getMyChar();
        Mob best = null;
        int bestId = -2;
        int bestDistance = 999999;
        for (int i = 0; i < GameScr.vMobAttack.size(); ++i) {
            Mob mob = (Mob) GameScr.vMobAttack.elementAt(i);
            int matchedId = this.getMatchedMobId(mob);
            if (matchedId >= 0 && mob.hp > 0 && mob.h != 0 && mob.h != 1 && !mob.isBoss) {
                int distance = me == null ? 0 : Res.distance(me.cx, me.cy, Auto.getMobAttackX(mob), Auto.getMobAttackY(mob));
                if (best == null || distance < bestDistance) {
                    best = mob;
                    bestId = matchedId;
                    bestDistance = distance;
                }
            }
        }

        if (best != null) {
            this.mobId = bestId;
            this.noTargetSince = 0L;
            return bestId;
        }

        return -2;
    }

    private int getMatchedMobId(Mob mob) {
        if (mob == null || this.mobIds == null) {
            return -1;
        }

        int templateId = Auto.getMobTemplateServerId(mob);
        for (int i = 0; i < this.mobIds.length; ++i) {
            if (mob.id == this.mobIds[i] || templateId == this.mobIds[i]) {
                return this.mobIds[i];
            }
        }

        return -1;
    }

    private void handleNoTargetMob() {
        Char me = Char.getMyChar();
        if (me != null) {
            me.mobFocus = null;
        }

        long now = System.currentTimeMillis();
        if (this.noTargetSince <= 0L) {
            this.noTargetSince = now;
            return;
        }

        if (now - this.noTargetSince >= 5000L) {
            this.noTargetSince = now;
            this.b(TileMap.zoneID);
        }
    }

    private static int[] copyMobIds(int[] ids) {
        if (ids == null || ids.length == 0) {
            return new int[0];
        }

        int[] result = new int[ids.length];
        System.arraycopy(ids, 0, result, 0, ids.length);
        return result;
    }

    private String getMobIdsText() {
        if (this.mobIds == null || this.mobIds.length == 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer(" quai ");
        for (int i = 0; i < this.mobIds.length; ++i) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(this.mobIds[i]);
        }

        return sb.toString();
    }

    public static Auto getRunningAutoUp(Auto auto) {
        Auto current = auto;

        for (int i = 0; current != null && i < 12; i++) {
            if (current instanceof AutoUp || current instanceof AutoUpLevel) {
                return current;
            }

            current = current.instance;
        }

        return null;
    }

    public static boolean hasRunningAutoUp(Auto auto) {
        return getRunningAutoUp(auto) != null;
    }

    public static String getEarnedYenText(Auto auto) {
        Char me = Char.getMyChar();
        auto = getRunningAutoUp(auto);
        if (me == null || auto == null) {
            return "Yen up: 0";
        }

        int yen = me.yen - auto.m;
        if (yen < 0) {
            yen = 0;
        }
        long perHour = getPerHour((long) yen, auto);
        return "Yen: " + NinjaUtil.a(String.valueOf(yen)) + " | Time: " + getElapsedTimeText(auto) + " | PerH: " + NinjaUtil.a(String.valueOf(perHour));
    }

    public static String getEarnedYenChatText(Auto auto) {
        Char me = Char.getMyChar();
        auto = getRunningAutoUp(auto);
        if (me == null || auto == null) {
            return "Chua co du lieu up yen";
        }

        int yen = me.yen - auto.m;
        if (yen < 0) {
            yen = 0;
        }

        long perHour = getPerHour((long) yen, auto);
        return "Up " + NinjaUtil.a(String.valueOf(yen)) + " yen trong " + getElapsedTimeText(auto) + " perh=" + NinjaUtil.a(String.valueOf(perHour));
    }

    public static String getEarnedExpText(Auto auto) {
        Char me = Char.getMyChar();
        auto = getRunningAutoUp(auto);
        if (me == null || auto == null) {
            return "Exp up: 0";
        }

        long exp = getEarnedExp(auto);
        long perHour = getPerHour(exp, auto);
        long levelExp = getCurrentLevelExpNeed(me);
        if (levelExp <= 0L) {
            return "Exp: " + NinjaUtil.a(String.valueOf(exp)) + " | PerH: " + NinjaUtil.a(String.valueOf(perHour));
        }

        long percent = getPercentX100(exp, levelExp);
        long percentPerHour = getPercentX100(perHour, levelExp);
        return "Exp: " + NinjaUtil.a(String.valueOf(exp)) + " - " + formatPercent(percent) + "% | PerH: " + NinjaUtil.a(String.valueOf(perHour)) + " - " + formatPercent(percentPerHour) + "%";
    }

    public static String getEarnedExpChatText(Auto auto) {
        Char me = Char.getMyChar();
        auto = getRunningAutoUp(auto);
        if (me == null || auto == null) {
            return "Chua co du lieu up level";
        }

        long exp = getEarnedExp(auto);
        long perHour = getPerHour(exp, auto);
        long levelExp = getCurrentLevelExpNeed(me);
        if (levelExp <= 0L) {
            return "Up " + NinjaUtil.a(String.valueOf(exp)) + " exp trong " + getElapsedTimeText(auto) + " perh=" + NinjaUtil.a(String.valueOf(perHour));
        }

        return "Up " + NinjaUtil.a(String.valueOf(exp)) + " exp - " + formatPercent(getPercentX100(exp, levelExp)) + "% trong " + getElapsedTimeText(auto) + " perh=" + NinjaUtil.a(String.valueOf(perHour)) + " - " + formatPercent(getPercentX100(perHour, levelExp)) + "%";
    }

    public static String getCurrentLevelText() {
        Char me = Char.getMyChar();
        if (me == null) {
            return "LV: ?";
        }

        long levelExp = getCurrentLevelExpNeed(me);
        if (levelExp <= 0L) {
            return "LV: " + me.cLevel;
        }

        long currentExp = me.cExpDown > 0L ? me.cExpDown : me.ah;
        long percent = getPercentX100(currentExp, levelExp);
        return "LV: " + me.cLevel + " + " + (me.cExpDown > 0L ? "-" : "") + formatPercent(percent) + "%";
    }

    public static boolean shouldShowEarnedLuong(Auto auto) {
        auto = getRunningAutoUp(auto);
        return auto != null && (TileMap.isLangCo(TileMap.mapID) || TileMap.isLangTT(TileMap.mapID)
                || TileMap.isLangCo(auto.mapID) || TileMap.isLangTT(auto.mapID)
                || (auto instanceof AutoUp && (TileMap.isLangCo(FormAutoUp.mapUp) || TileMap.isLangTT(FormAutoUp.mapUp))));
    }

    public static String getEarnedLuongText(Auto auto) {
        Char me = Char.getMyChar();
        auto = getRunningAutoUp(auto);
        if (me == null || auto == null) {
            return "Luong up: 0";
        }

        int luong = me.luong - auto.startLuong;
        if (luong < 0) {
            luong = 0;
        }
        long perHour = getPerHour((long) luong, auto);
        return "Luong: " + NinjaUtil.a(String.valueOf(luong)) + " | Time: " + getElapsedTimeText(auto) + " | PerH: " + NinjaUtil.a(String.valueOf(perHour));
    }

    private static long getPerHour(long value, Auto auto) {
        long seconds = getElapsedSeconds(auto);
        return value * 3600L / seconds;
    }

    private static long getEarnedExp(Auto auto) {
        Char me = Char.getMyChar();
        if (me == null || auto == null) {
            return 0L;
        }

        long exp = me.cEXP - auto.n;
        return exp < 0L ? 0L : exp;
    }

    private static long getCurrentLevelExpNeed(Char me) {
        if (me == null || GameScr.exps == null || me.cLevel < 0 || me.cLevel >= GameScr.exps.length) {
            return 0L;
        }

        return GameScr.exps[me.cLevel] > 0L ? GameScr.exps[me.cLevel] : 0L;
    }

    private static long getPercentX100(long value, long total) {
        if (value <= 0L || total <= 0L) {
            return 0L;
        }

        if (value > 922337203685477L) {
            return value / total * 10000L;
        }

        return value * 10000L / total;
    }

    private static long getElapsedSeconds(Auto auto) {
        if (auto == null || auto.o <= 0L) {
            return 1L;
        }

        long seconds = (System.currentTimeMillis() - auto.o) / 1000L;
        return seconds < 1L ? 1L : seconds;
    }

    private static String getElapsedTimeText(Auto auto) {
        return NinjaUtil.b((int) getElapsedSeconds(auto));
    }

    private static String formatPercent(long percent) {
        long decimal = percent % 100L;
        return percent / 100L + "." + (decimal < 10L ? "0" + decimal : String.valueOf(decimal));
    }
}
