
public final class AutoUp extends Auto {

    private int mobId;

    public AutoUp() {
    }

    public final void init(int mapId, int zoneId) {
        super.a();
        super.mapID = mapId;
        super.zoneID = zoneId;
        super.g = true;
        super.isHang = false;
        super.k = -1;
        super.l = -1;
        this.mobId = -1;
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

        this.attack(this.mobId, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
        this.pickUpItem(-1);
    }

    public final String toString() {
        return "Auto Up map " + super.mapID + " khu " + super.zoneID;
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
