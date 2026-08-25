import java.util.Calendar;

public final class AutoTaskScheduler {

    private static int lastNvhnKey = -1;
    private static int lastTaThuKey = -1;
    private static int lastDanhVongKey = -1;
    private static int pendingNvhnKey = -1;
    private static int pendingTaThuKey = -1;
    private static int pendingDanhVongKey = -1;
    private static long pendingNvhnAt = 0L;
    private static long pendingTaThuAt = 0L;
    private static long pendingDanhVongAt = 0L;
    private static long lastCheck = 0L;
    private static final long PENDING_TIMEOUT = 1800000L;
    private static final int CATCHUP_MINUTES = 30;

    public static void update() {
        try {
            if (Char.getMyChar() == null) {
                return;
            }

            long now = System.currentTimeMillis();
            if (now - lastCheck < 1000L) {
                return;
            }

            lastCheck = now;

            Calendar cur = Res.getCurrentTime();
            int hour = cur.get(11);
            int minute = cur.get(12);
            int dayBase = cur.get(6) * 1440;

            int nvhnKey = getScheduleKey(dayBase, FormAutoTask.gioNvhn, FormAutoTask.phutNvhn);
            if (FormAutoTask.batNvhn && isScheduleDue(hour, minute, FormAutoTask.gioNvhn, FormAutoTask.phutNvhn) && lastNvhnKey != nvhnKey) {
                lastNvhnKey = nvhnKey;
                pendingNvhnKey = nvhnKey;
                pendingNvhnAt = now;
                GameScr.chatPopup("Den gio NVHN");
            }

            int taThuKey = getScheduleKey(dayBase, FormAutoTask.gioTaThu, FormAutoTask.phutTaThu);
            if (FormAutoTask.batTaThu && isScheduleDue(hour, minute, FormAutoTask.gioTaThu, FormAutoTask.phutTaThu) && lastTaThuKey != taThuKey) {
                lastTaThuKey = taThuKey;
                pendingTaThuKey = taThuKey;
                pendingTaThuAt = now;
                GameScr.chatPopup("Den gio Ta Thu");
            }

            int danhVongKey = getScheduleKey(dayBase, SettingNVDV.gioADV, SettingNVDV.phutADV);
            if (SettingNVDV.tickHenGioLamDV == 0 && isScheduleDue(hour, minute, SettingNVDV.gioADV, SettingNVDV.phutADV) && lastDanhVongKey != danhVongKey) {
                lastDanhVongKey = danhVongKey;
                pendingDanhVongKey = danhVongKey;
                pendingDanhVongAt = now;
                GameScr.chatPopup("Den gio Danh Vong");
            }

            if (AutoBossScheduleManager.isActive() || AutoHD9xManager.isRoundActive() || Code.auto instanceof AutoLDGT) {
                return;
            }

            if (pendingNvhnKey != -1) {
                if (!FormAutoTask.batNvhn || now - pendingNvhnAt > PENDING_TIMEOUT) {
                    pendingNvhnKey = -1;
                } else if (Code.auto instanceof AutoNVHN) {
                    pendingNvhnKey = -1;
                } else if (!(Code.auto instanceof AutoTaThuSolo) && !(Code.auto instanceof AutoTaThu) && !(Code.auto instanceof AutoTaskPrepare)) {
                    pendingNvhnKey = -1;
                    startPrepare(AutoTaskPrepare.TYPE_NVHN);
                    return;
                }
            }

            if (pendingTaThuKey != -1) {
                if (!FormAutoTask.batTaThu || now - pendingTaThuAt > PENDING_TIMEOUT) {
                    pendingTaThuKey = -1;
                } else if (Code.auto instanceof AutoTaThuSolo || Code.auto instanceof AutoTaThu) {
                    pendingTaThuKey = -1;
                } else if (!(Code.auto instanceof AutoNVHN) && !(Code.auto instanceof AutoTaskPrepare)) {
                    pendingTaThuKey = -1;
                    startPrepare(AutoTaskPrepare.TYPE_TA_THU);
                    return;
                }
            }

            if (pendingDanhVongKey != -1) {
                if (SettingNVDV.tickHenGioLamDV != 0 || now - pendingDanhVongAt > PENDING_TIMEOUT) {
                    pendingDanhVongKey = -1;
                } else if (Code.auto instanceof AutoDV) {
                    pendingDanhVongKey = -1;
                } else if (!(Code.auto instanceof AutoNVHN) && !(Code.auto instanceof AutoTaThuSolo) && !(Code.auto instanceof AutoTaThu) && !(Code.auto instanceof AutoTaskPrepare)) {
                    pendingDanhVongKey = -1;
                    startPrepare(AutoTaskPrepare.TYPE_DANH_VONG);
                }
            }
        } catch (Exception e) {
        }
    }

    private static void startPrepare(int type) {
        AutoTaskPrepare prepare = new AutoTaskPrepare(type);
        prepare.a();
        Code.setAuto(prepare);
        Code.instance.a();
    }

    private static int getScheduleKey(int dayBase, int hour, int minute) {
        return dayBase + hour * 60 + minute;
    }

    private static boolean isScheduleDue(int hour, int minute, int targetHour, int targetMinute) {
        int nowMinute = hour * 60 + minute;
        int target = targetHour * 60 + targetMinute;
        int late = nowMinute - target;
        return late >= 0 && late <= CATCHUP_MINUTES;
    }
}
