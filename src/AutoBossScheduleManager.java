public final class AutoBossScheduleManager {

    private static final int EVENT_FOUR_HOURS = 1;
    private static final int EVENT_VDMQ = 2;
    private static final long CHECK_DELAY = 500L;
    private static final long START_DELAY_AFTER_NOTICE = 120000L;
    private static final long PENDING_TIMEOUT = 300000L;
    private static final int START_SECOND = 5;

    private static long lastCheckAt = 0L;
    private static int lastFourHourKey = -1;
    private static int lastVDMQKey = -1;
    private static int pendingEventType = -1;
    private static int pendingKey = -1;
    private static long pendingStartAt = 0L;
    private static long pendingExpireAt = 0L;

    private AutoBossScheduleManager() {
    }

    public static void update() {
        try {
            if (Char.getMyChar() == null || System.currentTimeMillis() - lastCheckAt < CHECK_DELAY) {
                return;
            }

            lastCheckAt = System.currentTimeMillis();

            if (runPending()) {
                return;
            }

            if (isActive() || AutoHD9xManager.isRoundActive() || Code.auto instanceof AutoLDGT) {
                return;
            }

            java.util.Calendar cur = Res.getCurrentTime();
            int hour = cur.get(11);
            int minute = cur.get(12);
            int second = cur.get(13);
            int key = cur.get(1) * 100000 + cur.get(6) * 100 + hour;

            if (minute != 0 || second < START_SECOND) {
                return;
            }

            if (hour % 4 == 0 && lastFourHourKey != key && (FormAutoBoss.LangCo || FormAutoBoss.LTT)) {
                lastFourHourKey = key;
                schedulePending(EVENT_FOUR_HOURS, key, false);
                return;
            }

            if (hour % 6 == 3 && lastVDMQKey != key && FormAutoBoss.VDMQ) {
                lastVDMQKey = key;
                schedulePending(EVENT_VDMQ, key, false);
            }
        } catch (Exception e) {
        }
    }

    public static void onWorldMessage(String message) {
        try {
            int eventType = detectEventType(message);

            if (eventType < 0) {
                return;
            }

            java.util.Calendar cur = Res.getCurrentTime();
            int key = cur.get(1) * 100000 + cur.get(6) * 100 + cur.get(11);

            if (eventType == EVENT_FOUR_HOURS) {
                lastFourHourKey = key;
            } else if (eventType == EVENT_VDMQ) {
                lastVDMQKey = key;
            }

            schedulePending(eventType, key, true);
        } catch (Exception e) {
        }
    }

    public static void startManual() {
        if (!FormAutoBoss.hasSelectedRegion()) {
            GameScr.chatPopup("Auto boss: chưa chọn khu vực");
            return;
        }

        if (isActive()) {
            GameScr.chatPopup("Auto boss đang chạy");
            return;
        }

        clearPending();
        start(0);
    }

    public static boolean isActive() {
        return Code.auto instanceof AutoBossSchedule;
    }

    private static void start(int eventType) {
        if (AutoHD9xManager.isRoundActive() || Code.auto instanceof AutoLDGT) {
            GameScr.chatPopup("Auto boss: đang có auto ưu tiên");
            return;
        }

        Code.setAuto(new AutoBossSchedule(eventType));
        Code.instance.a();
    }

    private static void schedulePending(int eventType, int key, boolean fromWorld) {
        if (!FormAutoBoss.hasSelectedRegion()) {
            return;
        }

        if (pendingEventType == eventType && pendingKey == key) {
            return;
        }

        pendingEventType = eventType;
        pendingKey = key;
        pendingStartAt = System.currentTimeMillis() + START_DELAY_AFTER_NOTICE;
        pendingExpireAt = pendingStartAt + PENDING_TIMEOUT;
        GameScr.chatPopup(fromWorld ? "Auto boss: đợi 120s sau thông báo" : "Auto boss: hẹn 120s nữa sẽ săn");
    }

    private static boolean runPending() {
        if (pendingEventType < 0) {
            return false;
        }

        if (!FormAutoBoss.hasSelectedRegion()) {
            clearPending();
            return false;
        }

        long now = System.currentTimeMillis();

        if (now < pendingStartAt) {
            return true;
        }

        if (isActive()) {
            clearPending();
            return true;
        }

        if (AutoHD9xManager.isRoundActive() || Code.auto instanceof AutoLDGT) {
            if (now < pendingExpireAt) {
                return true;
            }

            GameScr.chatPopup("Auto boss: quá thời gian chờ");
            clearPending();
            return true;
        }

        int eventType = pendingEventType;
        clearPending();
        start(eventType);
        return true;
    }

    private static void clearPending() {
        pendingEventType = -1;
        pendingKey = -1;
        pendingStartAt = 0L;
        pendingExpireAt = 0L;
    }

    private static int detectEventType(String message) {
        if (message == null || !FormAutoBoss.hasSelectedRegion()) {
            return -1;
        }

        if (AutoBossSchedule.hasAnnouncedMap(EVENT_FOUR_HOURS, message)) {
            return EVENT_FOUR_HOURS;
        }

        if (AutoBossSchedule.hasAnnouncedMap(EVENT_VDMQ, message)) {
            return EVENT_VDMQ;
        }

        return -1;
    }
}
