
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Vector;

public final class Code implements Runnable {

    public static Code instance = new Code();
    private static boolean aq = false;
    private static Thread ar;
    public static Auto auto;
    private static AutoTanSat autoTanSat = new AutoTanSat();
    public static Stanima c = new Stanima();
    public static AutoUp autoUp = new AutoUp();
    public static AutoNVHN autoNVHN = new AutoNVHN();
    public static AutoTaThu e = new AutoTaThu();
    public static AutoTaThuSolo autoTaThuSolo = new AutoTaThuSolo();
    private static AutoTuDanh at = new AutoTuDanh();
    private static AutoBuff au = new AutoBuff();
    public static AutoSell f = new AutoSell();
    public static AutoDV autoDV = new AutoDV();
    public static AutoJoinClanDun autoJoinClanDun = new AutoJoinClanDun();
    public static AutoLoiDai autoLoiDai = new AutoLoiDai();
    public static AutoLoseLoiDai autoLoseLoiDai = new AutoLoseLoiDai();
    public static AutoBiCuuSat autoBiCuuSat = new AutoBiCuuSat();
    public static AutoNTGT autoNTGT = new AutoNTGT();
    public static String g = null;
    public static MyVector h = new MyVector();
    public static MyVector i = new MyVector();
    private static long av;
    private static long aw;
    public static short[] pickUpListID;
    public static short[] delListID;
    public static int khoangCachNhat;
    public static int m;
    public static int n;
    public static int o;
    public static boolean isHutVP;
    public static boolean attackChangePosition;
    public static int r;
    public static MyVector s;
    public static MyVector t;
    public static boolean attackChangeZone;
    public static int v;
    public static int[] w;
    public static boolean keepLevel, tbNhanVP, tbNhanExp;
    public static int speedGame;
    public static int thoiGianChoChuyenKhu;
    public static int speedTinhLuyen;
    private static long ax;
    private static MyVector tuDung;
    public static MyVector listTLItem;
    private static long ba;
    private static long lastAutoBoxAt = 0L;
    private static long lastAutoBagSortAt = 0L;
    private static final long AUTO_BOX_DELAY = 1200L;
    private static final long AUTO_BAG_SORT_DELAY = 30000L;
    public static MyVector z;
    public static MyVector aa;
    public static long ab;
    public static long ac;
    public static boolean isBangMob;
    public static boolean isBangSkill;
    public static boolean af;
    public static int ag;
    public static boolean isFakeSkillNgang;
    public static int nSkillFake;
    public static boolean isFakeSkillCao;
    public static int cSkillFake;
    public static boolean al;
    public static int am;
    public static boolean an;
    private static String[] bb;
    public static short[] throwListID;
    private static long daa;
    public static MyVector dapdo;
    public static boolean cda;
    private static boolean fastGopXuClan = false;
    private static long lastTTGTUse = 0L;
    private static long lastTTGTChat = 0L;
    private static boolean ttgtChoosing604 = false;
    private static long ttgtOpen604Time = 0L;
    private static int ttgtChatCount = 0;
    private static String lastClanInviteName = "";
    private static long lastClanInviteAt = 0L;
    private static long lastAutoNVHNStart = 0L;
    private static final String RMS_NSOCHEN_MENU = "NsoChenMenu";
    public static boolean showNsoChenMenu = RMS.d(RMS_NSOCHEN_MENU) == 1;
    public static boolean teleTarget = true;
    public static boolean showMobNameId = false;

    public Code() {
    }

    public final void a() {
        if (!aq) {
            if (auto != null) {
                auto.b();
            }

            ax = System.currentTimeMillis();
            aq = true;
            (ar = new Thread(this)).start();
        }

    }

    public static void b() {
        aq = false;
        FormAutoUp.resetAutoStartAfterLogin();
        if (ar != null) {
            LockGame.tatAuto();
            ar.interrupt();
        }

    }

    public static boolean isInClan(Char c) {
        return c != null && c.cClanName != null && !c.cClanName.equals("");
    }

    private static void resetAutoTTGTState() {
        ttgtChoosing604 = false;
        ttgtOpen604Time = 0L;
        lastTTGTUse = 0L;
        lastTTGTChat = 0L;
        ttgtChatCount = 0;
    }

    private static void saveNsoChenMenuState() {
        try {
            RMS.writeRecord(RMS_NSOCHEN_MENU, showNsoChenMenu ? 1 : 0);
        } catch (Exception e) {
        }
    }

    public static void setAutoTTGT(boolean enabled) {
        Char.tickAutoTTGT = enabled;
        resetAutoTTGTState();
        Char.saveAuto();
    }

    public static void setTTGTOption(int option) {
        if (option < 0) {
            option = 0;
        }

        if (option > 2) {
            option = 2;
        }

        Char.ttgtOption = option;
        Char.saveAuto();
    }

    public static int nextTTGTOption() {
        int option = Char.ttgtOption + 1;

        if (option > 2) {
            option = 0;
        }

        setTTGTOption(option);
        return Char.ttgtOption;
    }

    private static int getAsallClassId(String command, int numericClass) {
        if (numericClass >= 1 && numericClass <= 7) {
            return numericClass;
        }

        if (command == null) {
            return 0;
        }

        String suffix = command.toLowerCase().trim();
        if (!suffix.startsWith("asall")) {
            return 0;
        }

        suffix = suffix.substring(5).trim();
        if (suffix.startsWith("+")) {
            suffix = suffix.substring(1).trim();
        }

        if (suffix.equals("k")) {
            return 1;
        }

        if (suffix.equals("t")) {
            return 2;
        }

        if (suffix.equals("ku") || suffix.equals("u")) {
            return 3;
        }

        if (suffix.equals("c")) {
            return 4;
        }

        if (suffix.equals("d")) {
            return 5;
        }

        if (suffix.equals("q")) {
            return 6;
        }

        return suffix.equals("th") ? 7 : 0;
    }

    private static String getAsallClassName(int classId) {
        switch (classId) {
            case 1:
                return "Kiếm";
            case 2:
                return "Tiêu";
            case 3:
                return "Kunai";
            case 4:
                return "Cung";
            case 5:
                return "Đao";
            case 6:
                return "Quạt";
            case 7:
                return "Thương";
            default:
                return "chưa chọn phái";
        }
    }

    private static void autoTTGT(Char c) {
        try {
            if (!Char.tickAutoTTGT || c == null || c.arrItemBody == null || c.arrItemBag == null) {
                return;
            }

            if (!isInClan(c)) {
                setAutoTTGT(false);
                GameScr.chatPopup("TTGT: không có gia tộc, tự tắt");
                return;
            }

            if (c.arrItemBody.length <= 13) {
                return;
            }

            long now = System.currentTimeMillis();

            boolean hasPet = c.arrItemBody[10] != null;
            boolean hasNTGT5 = c.arrItemBody[13] != null;

            if (c.cLevel < 70) {
                ttgtChoosing604 = false;
                ttgtOpen604Time = 0L;

                if (hasNTGT5) {
                    ttgtChatCount = 0;
                    return;
                }

                handleNTGT5(c, now);
                return;
            }

            if (hasPet && hasNTGT5) {
                ttgtChoosing604 = false;
                ttgtOpen604Time = 0L;
                ttgtChatCount = 0;
                return;
            }

            if (ttgtChoosing604) {
                if (GameCanvas.menu != null && GameCanvas.menu.showMenu) {
                    int opt = Char.ttgtOption;

                    if (opt < 0) {
                        opt = 0;
                    }

                    if (opt > 2) {
                        opt = 2;
                    }

                    GameCanvas.menu.menuSelectedItem = opt;
                    GameCanvas.menu.showMenu = false;
                    GameCanvas.instance.perform(88817, null);

                    GameScr.chatPopup("TTGT: chọn 604 option " + opt);

                    ttgtChoosing604 = false;
                    ttgtOpen604Time = 0L;
                    lastTTGTUse = now;
                    return;
                }

                if (now - ttgtOpen604Time > 2500L) {
                    ttgtChoosing604 = false;
                    ttgtOpen604Time = 0L;
                    GameScr.chatPopup("TTGT: không thấy menu 604");
                }

                return;
            }

            if (!hasPet) {
                int indexPet = findBagIndex(c, 583);

                if (indexPet < 0) {
                    indexPet = findBagIndex(c, 586);
                }

                if (indexPet < 0) {
                    indexPet = findBagIndex(c, 589);
                }

                if (indexPet >= 0) {
                    if (now - lastTTGTUse > 3000L) {
                        Item itemPet = c.arrItemBag[indexPet];

                        if (itemPet != null && itemPet.template != null) {
                            Service.getInstance().useItem(indexPet);
                            lastTTGTUse = now;
                            ttgtChatCount = 0;
                            GameScr.chatPopup("TTGT: dùng item thú " + itemPet.template.id);
                        }
                    }

                    return;
                }

                int index604 = findBagIndex(c, 604);

                if (index604 >= 0) {
                    if (now - lastTTGTUse > 3000L) {
                        Item item604 = c.arrItemBag[index604];

                        if (item604 != null && item604.template != null) {
                            Service.getInstance().useItem(index604);

                            ttgtChoosing604 = true;
                            ttgtOpen604Time = now;
                            lastTTGTUse = now;
                            ttgtChatCount = 0;

                            GameScr.chatPopup("TTGT: mở item 604");
                        }
                    }

                    return;
                }

                if (now - lastTTGTChat > 15000L) {
                    Service.getInstance().m("ttgt");

                    lastTTGTChat = now;
                    ttgtChatCount++;

                    GameScr.chatPopup("TTGT: chat ttgt lần " + ttgtChatCount);

                    if (ttgtChatCount >= 3) {
                        setAutoTTGT(false);
                        GameScr.chatPopup("TTGT: quá 3 lần không nhận item, tự tắt");
                    }
                }

                return;
            }

            if (!hasNTGT5) {
                handleNTGT5(c, now);
            }
        } catch (Exception e) {
        }
    }

    private static void handleNTGT5(Char c, long now) {
        int index427 = findBagIndex(c, 427);

        if (index427 >= 0) {
            if (now - lastTTGTUse > 3000L) {
                Item item427 = c.arrItemBag[index427];

                if (item427 != null && item427.template != null) {
                    Service.getInstance().useItem(index427);
                    lastTTGTUse = now;
                    ttgtChatCount = 0;
                    GameScr.chatPopup("TTGT: dùng NTGT5 item 427");
                }
            }

            return;
        }

        if (now - lastTTGTChat > 15000L) {
            Service.getInstance().m("ntgt5");

            lastTTGTChat = now;
            ttgtChatCount++;

            GameScr.chatPopup("TTGT: chat ntgt5 lần " + ttgtChatCount);

            if (ttgtChatCount >= 3) {
                setAutoTTGT(false);
                GameScr.chatPopup("TTGT: quá 3 lần không nhận NTGT5, tự tắt");
            }
        }
    }

    private static int findBagIndex(Char c, int itemId) {
        try {
            if (c == null || c.arrItemBag == null) {
                return -1;
            }

            for (int i = 0; i < c.arrItemBag.length; i++) {
                Item item = c.arrItemBag[i];

                if (item != null && item.template != null && item.template.id == itemId) {
                    return i;
                }
            }
        } catch (Exception e) {
        }

        return -1;
    }

    private static void fastGopXuClan(final int amount) {
        if (fastGopXuClan) {
            GameScr.chatPopup("Góp GT: đang chạy");
            return;
        }

        if (Char.getMyChar() == null || Char.getMyChar().xu <= 0) {
            GameScr.chatPopup("Góp GT: không có xu");
            return;
        }

        fastGopXuClan = true;

        new Thread(new Runnable() {
            public void run() {
                try {
                    if (Char.getMyChar().cClanName == null || Char.getMyChar().cClanName.equals("")) {
                        GameScr.chatPopup("Góp GT: nhân vật chưa có gia tộc");
                        fastGopXuClan = false;
                        return;
                    }

                    if (Char.clan == null || Char.clan.name == null || Char.clan.name.equals("")) {
                        GameScr.chatPopup("Góp GT: tải dữ liệu gia tộc");
                        Service.getInstance().requestClanInfo();

                        long wait = System.currentTimeMillis();

                        while (fastGopXuClan
                                && (Char.clan == null || Char.clan.name == null || Char.clan.name.equals(""))
                                && System.currentTimeMillis() - wait < 5000L) {
                            try {
                                Thread.sleep(300L);
                            } catch (Exception e) {
                            }
                        }
                    }

                    if (Char.clan == null || Char.clan.name == null || Char.clan.name.equals("")) {
                        GameScr.chatPopup("Góp GT: chưa tải được dữ liệu gia tộc");
                        fastGopXuClan = false;
                        return;
                    }

                    int total = amount;

                    if (total <= 0 || total > Char.getMyChar().xu) {
                        total = Char.getMyChar().xu;
                    }

                    int done = 0;

                    while (fastGopXuClan && total > 0 && Char.getMyChar().xu > 0) {
                        int one = total;

                        if (one > 10000000) {
                            one = 10000000;
                        }

                        if (one > Char.getMyChar().xu) {
                            one = Char.getMyChar().xu;
                        }

                        if (one <= 0) {
                            break;
                        }

                        Service.getInstance().ai(one);

                        done += one;
                        total -= one;

                        GameScr.chatPopup("Góp GT: " + NinjaUtil.a(String.valueOf(done)));
                        try {
                            Thread.sleep(700L);
                        } catch (Exception e) {
                        }
                    }

                    GameScr.chatPopup("Góp GT: xong");
                } catch (Exception e) {
                    GameScr.chatPopup("Góp GT: lỗi");
                }

                fastGopXuClan = false;
            }
        }).start();
    }

    public static void setAuto(Auto var0) {
        var0.instance = auto;
        auto = var0;
    }

    public static void backToInstance() {
        LockGame.tatAuto();
        auto = auto.instance;
    }

    public static boolean startGomDoNow() {
        try {
            if (AutoReceiver.stringNameCharNhanDo == null || AutoReceiver.stringNameCharNhanDo.trim().length() == 0) {
                GameScr.chatPopup("Gom do: chua cai ten nhan do");
                return true;
            }

            if (AutoReceiver.mapNhanDo < 0 || AutoReceiver.khuNhanDo < 0) {
                GameScr.chatPopup("Gom do: map/khu nhan do loi");
                return true;
            }

            Char me = Char.getMyChar();
            if (me == null || me.arrItemBag == null) {
                GameScr.chatPopup("Gom do: chua load hanh trang");
                return true;
            }

            boolean hasCoin = me != null && SettingGomDo.tradeCoinValue == 0 && me.xu > 0;
            if (AutoSend.e() <= 0 && AutoSend.f() <= 0 && !hasCoin) {
                GameScr.chatPopup("Gom do: khong co vat pham hoac xu de giao dich");
                return true;
            }

            setAuto(new AutoSend(AutoReceiver.mapNhanDo, AutoReceiver.khuNhanDo, AutoReceiver.stringNameCharNhanDo, true));
            GameScr.chatPopup("Gom do: bat dau giao dich ngay");
        } catch (Exception e) {
            GameScr.chatPopup("Gom do: loi cau hinh");
        }

        return true;
    }

    public final void tanSat(int var1, int var2) {
        autoTanSat.init(var1, var2, Char.tickTSMapEmpty ? -1 : TileMap.zoneID);
        setAuto((Auto) autoTanSat);
    }

    private static MobTemplate getMobTemplateByCommandId(int id) {
        if (Mob.mobTemplates == null || id < 0) {
            return null;
        }

        if (id < Mob.mobTemplates.length && Mob.mobTemplates[id] != null) {
            return Mob.mobTemplates[id];
        }

        for (int i = 0; i < Mob.mobTemplates.length; i++) {
            if (Mob.mobTemplates[i] != null && Mob.mobTemplates[i].e == id) {
                return Mob.mobTemplates[i];
            }
        }

        return null;
    }

    private void c(int var1, int var2) {
        c.a(var1, var2, Char.tickTSMapEmpty ? -1 : TileMap.zoneID, false, false);
        setAuto((Auto) c);
    }

    private void a(boolean var1, boolean var2) {
        c.a(-1, TileMap.mapID, TileMap.zoneID, var1, var2);
        c.g = true;
        setAuto((Auto) c);
    }

    public final void startAutoNVHN() {
        autoNVHN.a();
        setAuto((Auto) autoNVHN);
    }

    public final void startAutoTaThuSolo() {
        autoTaThuSolo.a();
        setAuto((Auto) autoTaThuSolo);
    }

    public final void e() {
        e.a();
        setAuto((Auto) e);
    }

    private void p() {
        at.a();
        setAuto((Auto) at);
    }

    private void b(boolean var1, boolean var2) {
        au.a(TileMap.mapID, TileMap.zoneID, var1, var2);
        setAuto((Auto) au);
    }

    private void q() {
        f.a();
        setAuto((Auto) f);
    }

    public static void tatAuto() {
        LockGame.tatAuto();
        auto = null;
    }

    public static void stopCurrentAuto() {
        try {
            if (AutoDapDo.isRunning()) {
                AutoDapDo.stop();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoDungHop.isRunning()) {
                AutoDungHop.stop();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoTinhLuyen.isRunning()) {
                AutoTinhLuyen.stop();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoBiKip.isRunning()) {
                AutoBiKip.stop();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoGiftCode.isRunning()) {
                AutoGiftCode.stop();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoRuocDen.isRunning()) {
                AutoRuocDen.stop();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoViThu.isRunning()) {
                AutoViThu.stop();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoLuyenNgoc.isSlot0Running()) {
                AutoLuyenNgoc.stopSlot0();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoNpc.running) {
                AutoNpc.stopAuto();
            }
        } catch (Exception e) {
        }

        try {
            if (AutoHD9xManager.isRoundActive()) {
                AutoHD9xManager.stopRound();
            }
        } catch (Exception e) {
        }

        try {
            if (UseAllItem.isRunning()) {
                UseAllItem.stop();
            }
        } catch (Exception e) {
        }

        tatAuto();
    }

    public final void startAutoDV() {
        autoDV.init();
        setAuto((Auto) autoDV);
    }

    public final void startJoinClanDun() {
        setAuto((Auto) autoJoinClanDun);
    }

    public static MyVector g() {
        MyVector var0 = new MyVector();

        for (int var1 = 0; var1 < i.size(); ++var1) {
            var0.addElement(var1 + ". " + (String) i.elementAt(var1));
        }

        return var0;
    }

    private static void i(String var0) {
        if (!i.contains(var0)) {
            i.addElement(var0);
            s();
        }

    }

    private static void j(String var0) {
        if (i.contains(var0)) {
            i.removeElement(var0);
            s();
        }

    }

    private static void r() {
        i.removeAllElements();
        s();
    }

    public static boolean a(String var0) {
        return i.contains(var0);
    }

    private static void s() {
        ByteArrayOutputStream var0 = new ByteArrayOutputStream();
        DataOutputStream var1 = new DataOutputStream(var0);

        try {
            var1.writeUTF(g == null ? "" : g);
            var1.writeByte(h.size());

            int var2;
            for (var2 = 0; var2 < h.size(); ++var2) {
                var1.writeUTF((String) h.elementAt(var2));
            }

            var1.writeInt(i.size());

            for (var2 = 0; var2 < i.size(); ++var2) {
                var1.writeUTF((String) i.elementAt(var2));
            }

            var1.flush();
            var0.flush();
            RMS.writeRecord("V6Group", var0.toByteArray());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static boolean b(String var0) {
        if (g != null && !d(var0)) {
            String var1;
            if ((var1 = Char.getMyChar().charName).equals(g)) {
                if (c(var0)) {
                    return true;
                }
            } else if (GameScr.vParty.size() > 1 && var1.equals(((Party) GameScr.vParty.firstElement()).d) && var0.equals(g)) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    public static boolean c(String var0) {
        for (int var1 = 0; var1 < h.size(); ++var1) {
            if (var0.equals(h.elementAt(var1))) {
                return true;
            }
        }

        return false;
    }

    public static boolean d(String var0) {
        if (var0.equals(Char.getMyChar().charName)) {
            return true;
        } else {
            for (int var1 = 0; var1 < GameScr.vParty.size(); ++var1) {
                if (((Party) GameScr.vParty.elementAt(var1)).d.equals(var0)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean h() {
        for (int var0 = 0; var0 < Char.ex - 1; ++var0) {
            if (Char.k(var0) >= 4) {
                return true;
            }
        }

        return false;
    }

    private static int g(int var0) {
        int var2 = 0;
        Char var3 = Char.getMyChar();

        for (int var1 = 0; var1 < var3.arrItemBag.length; ++var1) {
            Item var4;
            if ((var4 = var3.arrItemBag[var1]) != null && var4.template.type == 18 && var4.template.level == var0) {
                ++var2;
            }
        }

        return var2;
    }

    private static void checkAutoNVHNSchedule() {
        try {
            if (!FormAutoTask.batNvhn || auto instanceof AutoNVHN) {
                return;
            }

            Calendar curCal = Res.getCurrentTime();
            if (curCal.get(11) != FormAutoTask.gioNvhn || curCal.get(12) != FormAutoTask.phutNvhn) {
                return;
            }

            long now = System.currentTimeMillis();
            if (now - lastAutoNVHNStart < 60000L) {
                return;
            }

            lastAutoNVHNStart = now;
            GameScr.chatPopup("Bắt đầu làm nhiệm vụ hằng ngày");
            instance.startAutoNVHN();
        } catch (Exception e) {
        }
    }

    public final void run() {
        while (true) {
            try {
                if (aq) {
                    long var1 = System.currentTimeMillis();

                    try {
                        Char var3 = Char.getMyChar();
                        FormAutoUp.updateAutoStartAfterLogin();
                        FormAutoUp.updateMaintenanceRestart();
                        AutoLDGT.updateSchedule();
                        AutoLDGT.tryClanAutoCall();
                        AutoHD9xManager.update();
                        AutoBossScheduleManager.update();
                        AutoBuyShop.update();
                        AutoUseItem.update();
                        AutoChat.update();
                        AutoDoiLongDen.update();
                        AutoGiftCode.updateAutoStartAfterLogin();
                        AutoRuocDen.updateSchedule();
                        AutoViThu.updateSchedule();
                        autoTTGT(var3);
                        AutoTaskScheduler.update();
                        int var4 = Char.countNullSlot();
                        int var6;
                        int var11;
                        int var12;
                        int var13;
                        Item var14;
                        int var26;
                        int var31;
                        int var34;
                        int var35;
                        int var7;
                        if (auto != null) {
                            if (!(auto instanceof AutoReceiver) && !(auto instanceof AutoSend) && AutoReceiver.isInTimeTrade() && AutoReceiver.stringNameCharNhanDo != null) {
                                setAuto((Auto) (new AutoSend(AutoReceiver.mapNhanDo, AutoReceiver.khuNhanDo, AutoReceiver.stringNameCharNhanDo)));
                            }

                            if (g != null && System.currentTimeMillis() - av > 5000L) {
                                if (g.equals(var3.charName)) {
                                    if (!Auto.q && GameScr.vParty.size() <= 0) {
                                        Service.getInstance().s();
                                    }
                                } else if (GameScr.getCharByName(g) != null && GameScr.vParty.size() == 0) {
                                    Service.getInstance().pleaseInputParty(g);
                                }

                                av = System.currentTimeMillis();
                            }

                            if (ac > 0L) {
                                long var5;
                                if ((var5 = System.currentTimeMillis()) - ab >= ac) {
                                    ac = 0L;
                                    LockGame.tatAuto();
                                    auto = null;
                                    Session_ME.instance.c();
                                    Controller.getInstance().d();
                                    return;
                                }

                                ac -= var5 - ab;
                                ab = var5;
                            }

                            if (auto.checkStuckGuard()) {
                                auto.run();
                            }
                            if (var3.isHuman == Auto.q && Auto.selectSkill != null && (var3.selectSkill == null || var3.selectSkill.template.id != Auto.selectSkill.template.id)) {
                                var3.selectSkill = Auto.selectSkill;
                            }

                            if (Char.tickDieKhiHetMP && Auto.isHetMP) {
                                Auto.isHetMP = false;
                                if (!(auto instanceof AutoTaThu) && !(auto instanceof AutoTaThuSolo) && !(auto instanceof AutoPKBoss) && !(auto instanceof AutoBossSchedule) && !TileMap.isLangCo(TileMap.mapID) && !TileMap.isLangTT(TileMap.mapID)) {
                                    Auto.tuSat();
                                }
                            }

                            boolean var24;
                            if (var3.statusMe != 14 && var3.statusMe != 5 && var3.cHP > 0) {
                                if (Char.tickAutoUseMP && System.currentTimeMillis() - aw > 500L && Char.getMyChar().cMP < Char.getMyChar().cMaxMP * Char.aMpValue / 100) {
                                    Char.getMyChar().e(17);
                                    aw = System.currentTimeMillis();
                                }

                                if (Char.tickAutoUseHP && System.currentTimeMillis() - var3.fq > 2000L && Char.getMyChar().cHP < Char.getMyChar().cMaxHP * Char.aHpValue / 100) {
                                    var24 = false;
                                    var6 = (int) (System.currentTimeMillis() / 1000L);

                                    for (var7 = 0; var7 < Char.getMyChar().vEff.size(); ++var7) {
                                        Effect var8;
                                        if ((var8 = (Effect) Char.getMyChar().vEff.elementAt(var7)).e.a == 21 && var8.c - (var6 - var8.b) >= 2) {
                                            var24 = true;
                                            break;
                                        }
                                    }

                                    if (!var24) {
                                        Char.getMyChar().e(16);
                                        var3.fq = System.currentTimeMillis();
                                    }
                                }
                            }

                            if (var3.aj > 0 && (Char.tickCongKyNang || auto instanceof As50) && Auto.selectSkill != null && Auto.selectSkill.point < Auto.selectSkill.template.maxPoint) {
                                SkillTemplate var25 = Auto.selectSkill.template;
                                var6 = 0;

                                for (var7 = Auto.selectSkill.point + 1; var7 <= var25.maxPoint && var25.skills[var7].level <= var3.cLevel && var6 < var3.aj; ++var7) {
                                    ++var6;
                                }

                                if (var6 > 0) {
                                    GameScr.chatPopup("Cộng skill " + var25.name + " " + var6 + " điểm");
                                    Service.getInstance().f(var25.id, var6);
                                }
                            }

                            if (var3.ai > 0 && (Char.tickCongTiemNang || auto instanceof As50)) {
                                var26 = var3.isNoiCong() ? 3 : 0;
                                if (var3.ai >= 100) {
                                    GameScr.chatPopup("Cộng tiềm năng " + mResources.iz[var26] + " 60 điểm, " + mResources.iz[2] + " 40 điểm");
                                    Service.getInstance().e(2, 40);
                                    Service.getInstance().e(var26, 60);
                                } else {
                                    GameScr.chatPopup("Cộng tiềm năng " + mResources.iz[var26] + " " + var3.ai + " điểm");
                                    Service.getInstance().e(var26, var3.ai);
                                }

                                LockGame.w();
                            }

                            Item var27;
                            if (!AutoDoiLongDen.isBusy()) {
                                DeleteItem.cleanBagAuto(var3);
                            }

                            Item var9;
                            if (!DeleteItem.isBusy() && Char.countNullSlotBox() > 0 && SettingGomDo.c == 0 && System.currentTimeMillis() - lastAutoBoxAt >= AUTO_BOX_DELAY) {
                                String[] autoBoxIds = splitString(SettingGomDo.stringItemCat, ",");
                                boolean autoBoxSent = false;

                                for (var7 = 0; var7 < autoBoxIds.length && !autoBoxSent; ++var7) {
                                    try {
                                        int autoBoxId = Integer.parseInt(autoBoxIds[var7].trim());

                                        for (var31 = 0; var31 < Char.getMyChar().arrItemBag.length; ++var31) {
                                            var9 = Char.getMyChar().arrItemBag[var31];
                                            if (var9 != null && var9.template.id == autoBoxId) {
                                                Service.getInstance().e(var9.indexUI);
                                                lastAutoBoxAt = System.currentTimeMillis();
                                                autoBoxSent = true;
                                                break;
                                            }
                                        }
                                    } catch (Exception boxEx) {
                                    }
                                }
                            }

                            Item var33;
                            if (listTLItem.size() > 0) {
                                int[] var30 = new int[]{150000, 247500, 408375, 673819, 1111801, 2056832, 4010822, 7420021, 12243035};
                                byte[] var28 = new byte[]{3, 5, 9, 4, 7, 10, 5, 7, 9};

                                for (var7 = 0; var7 < listTLItem.size(); ++var7) {
                                    var34 = (var33 = (Item) listTLItem.elementAt(var7)).getTinhLuyen(85);
                                    if (var33.w) {
                                        if (System.currentTimeMillis() - var33.y > speedTinhLuyen * 1000 || var33.x < var34) {
                                            var33.w = false;
                                        }
                                    } else if (var34 >= 0 && var34 < 9) {
                                        MyVector var10 = Char.getListItemByID(var34 < 3 ? 455 : (var34 < 6 ? 456 : 457));
                                        var11 = var30[var34];
                                        var12 = var28[var34];
                                        if (var3.yen >= var11 && var10.size() >= var12) {
                                            Item[] var37 = new Item[24];

                                            for (var13 = 0; var13 < var12; ++var13) {
                                                var14 = (Item) var10.elementAt(var10.size() - 1);
                                                var37[var13] = var14;
                                                var3.arrItemBag[var14.indexUI] = null;
                                                var10.removeElementAt(var10.size() - 1);
                                            }

                                            Service.getInstance().b(var33, var37);
                                            var33.w = true;
                                            var33.x = var34;
                                            var33.y = System.currentTimeMillis();
                                        }
                                    } else {
                                        listTLItem.removeElementAt(var7--);
                                    }
                                }
                            }

                            Item[] var29;
                            MyVector var32;
                            if (Char.tickAutoTTT && var4 > 0) {
                                var32 = Char.getListItemByID(455);

                                while (var32.size() >= 9) {
                                    var29 = new Item[24];

                                    for (var7 = 0; var7 < 9; ++var7) {
                                        var33 = (Item) var32.elementAt(var32.size() - 1);
                                        var29[var7] = var33;
                                        var3.arrItemBag[var33.indexUI] = null;
                                        var32.removeElementAt(var32.size() - 1);
                                    }

                                    Service.getInstance().e(var29);
                                }

                                var4 = Char.countNullSlot();
                            }

                            if (Char.tickAutoTTC && var4 > 0) {
                                var32 = Char.getListItemByID(456);

                                while (var32.size() >= 9) {
                                    var29 = new Item[24];

                                    for (var7 = 0; var7 < 9; ++var7) {
                                        var33 = (Item) var32.elementAt(var32.size() - 1);
                                        var29[var7] = var33;
                                        var3.arrItemBag[var33.indexUI] = null;
                                        var32.removeElementAt(var32.size() - 1);
                                    }

                                    Service.getInstance().e(var29);
                                }

                                var4 = Char.countNullSlot();
                            }

                            if (System.currentTimeMillis() - ba > 2000L) {
                                for (var26 = 0; var26 < z.size(); ++var26) {
                                    var6 = ((Integer) z.elementAt(var26)).intValue();
                                    if ((var7 = ((Integer) aa.elementAt(var26)).intValue()) < 5000) {
                                        z.removeElementAt(var26);
                                        aa.removeElementAt(var26);
                                        --var26;
                                    } else if ((var33 = Char.f(var6)) != null) {
                                        Service.getInstance().sendToSaleItem(var33, var7);
                                    }
                                }

                                var4 = Char.countNullSlot();
                                ba = System.currentTimeMillis();
                            }

                            if (TileMap.mapID != 138 && TileMap.isLangCo(TileMap.mapID) && Char.tickAutoFood && Char.tickAutoMuaTA && Char.aFoodValue <= 50 && var4 > 1 && g(Char.aFoodValue) == 0) {
                                TileMap.j(0);
                                TileMap.g();
                            }

                            if (TileMap.isLang(TileMap.mapID) || TileMap.isTruong(TileMap.mapID)) {
                                if ((Char.tickAutoMuaTA || auto instanceof As10) && var4 > 1 && var3.ctaskId > 3 && (var7 = auto instanceof As10 ? (var3.ctaskId >= 9 ? 10 : 1) : Char.aFoodValue) <= 50 && g(var7) == 0) {
                                    var6 = 2;

                                    for (var26 = 0; var26 < var3.vEff.size(); ++var26) {
                                        if (((Effect) var3.vEff.elementAt(var26)).e.b == 0) {
                                            --var6;
                                            break;
                                        }
                                    }

                                    GameScr.PickNpc(4, 0, 0);
                                    if (var7 == 50) {
                                        Service.getInstance().buyItem1(9, 7, var6);
                                    } else {
                                        Service.getInstance().buyItem1(9, var7 / 10, var6);
                                    }

                                    LockGame.g();
                                }

                                if (TileMap.mapID == 138 && var4 > 1 && !Char.hasItem(35) && !Char.hasItem(37)) {
                                    GameScr.PickNpc(4, 0, 0);
                                    Service.getInstance().buyItem1(9, 6, 1);
                                    LockGame.g();
                                    ++var4;
                                }

                                if (var4 < 10 && !(auto instanceof As10)) {
                                    if (Char.tickLuyenDaMax && var3.ctaskId > 9 && var4 > 0 && h()) {
                                        if (var24 = TileMap.isTruong(TileMap.mapID)) {
                                            if ((var27 = Char.getItemByID(37)) == null && (var27 = Char.getItemByID(35)) == null) {
                                                GameScr.PickNpc(4, 0, 0);
                                                Service.getInstance().buyItem1(9, 6, 1);
                                                LockGame.g();
                                                Thread.sleep(100L);
                                                var27 = Char.getItemByID(35);
                                            }

                                            if (var27 != null) {
                                                Service.getInstance().useItemChangeMap(var27.indexUI, 5);
                                                TileMap.g();
                                            }
                                        }

                                        if (TileMap.isLang(TileMap.mapID)) {
                                            GameScr.PickNpc(6, 1, 1);
                                            LockGame.q();
                                            Vector var38 = new Vector();

                                            label438:
                                            for (var7 = 0; var7 < Char.ex - 1; ++var7) {
                                                var38.removeAllElements();

                                                for (var31 = 0; var31 < var3.arrItemBag.length; ++var31) {
                                                    if ((var9 = var3.arrItemBag[var31]) != null && var9.template.id == var7) {
                                                        var38.addElement(var9);
                                                    }
                                                }

                                                while (var38.size() >= 4) {
                                                    var31 = 1;

                                                    for (var34 = var7; var34 < Char.ex - 1 && GameScr.coinUpCrystals[var34] <= var3.yen && var31 << 2 <= var38.size() && var31 < 16; ++var34) {
                                                        var31 <<= 2;
                                                    }

                                                    if (var31 == 1) {
                                                        break label438;
                                                    }

                                                    GameScr.arrItemUpPeal = new Item[24];

                                                    for (var35 = 0; var35 < var31; ++var35) {
                                                        Item var39 = (Item) var38.elementAt(0);
                                                        GameScr.arrItemUpPeal[var35] = var39;
                                                        var3.arrItemBag[var39.indexUI] = null;
                                                        var38.removeElementAt(0);
                                                    }

                                                    Service.getInstance().crystalCollectLock1(GameScr.arrItemUpPeal);
                                                    LockGame.a();
                                                    if (GameScr.arrItemUpPeal[0] != null) {
                                                        var3.arrItemBag[GameScr.arrItemUpPeal[0].indexUI] = GameScr.arrItemUpPeal[0];
                                                    }
                                                }
                                            }

                                            GameCanvas.setMaxTextLenght();
                                        }

                                        if (Char.getMyChar().arrItemBox == null) {
                                            Service.getInstance().requestItem(4);
                                            LockGame.s();
                                        }

                                        GameScr.PickNpc(5, 0, 0);
                                        var6 = 0;

                                        for (var7 = Char.countNullSlotBox(); var6 < var3.arrItemBag.length; ++var6) {
                                            if ((var33 = var3.arrItemBag[var6]) != null && var33.template.id == Char.ex - 1 && var7 > 0) {
                                                Service.getInstance().e(var33.indexUI);
                                                --var7;
                                            }
                                        }

                                        if (var24) {
                                            Auto.tuSat();
                                        }
                                    }

                                    var4 = Char.countNullSlot();
                                    if (!DeleteItem.isBusy() && System.currentTimeMillis() - lastAutoBagSortAt >= AUTO_BAG_SORT_DELAY) {
                                        Service.getInstance().bagSort();
                                        lastAutoBagSortAt = System.currentTimeMillis();
                                        LockGame.s();
                                    }
                                }
                            }
                        }

                        if (keepLevel && var3.ah * 100L / (long) GameScr.crystals[var3.cLevel] >= 98L) {
                            LockGame.tatAuto();
                            auto = null;
                            Session_ME.getInstance().b();
                        }

                        if (System.currentTimeMillis() - ax > 2000L) {
                            var26 = 0;

                            while (true) {
                                if (var26 >= tuDung.size()) {
                                    ax = System.currentTimeMillis();
                                    break;
                                }

                                label690:
                                {
                                    ItemTemplate var42 = ItemTemplateManager.get((short) (var6 = ((Integer) tuDung.elementAt(var26)).intValue()));
                                    if (Char.hasItem(var6)) {
                                        for (var31 = 0; var31 < var3.vEff.size(); ++var31) {
                                            Effect var36;
                                            if ((var36 = (Effect) var3.vEff.elementAt(var31)) != null && var36.e.c == var42.iconID) {
                                                break label690;
                                            }
                                        }

                                        if ((var31 = Char.getIndexItemById(var6)) >= 0) {
                                            Service.getInstance().useItem(var31);
                                            break label690;
                                        }
                                    }

                                    tuDung.removeElementAt(var26);
                                    --var26;
                                }

                                ++var26;
                            }
                        }
                        if (Code.isHutVP) {
                            int minDistance = 100;
                            for (int i = 0; i < GameScr.vItemMap.size(); i++) {
                                Char me = Char.getMyChar();
                                ItemMap item = (ItemMap) GameScr.vItemMap.elementAt(i);
                                if (item == null) {
                                    continue;
                                }
                                int d = Res.distance(me.cx, me.cy, item.xEnd, item.yEnd);
                                if (d > minDistance) {
                                    continue;
                                }
                                boolean canPick = Code.isItemCanPickUp(item.template) || (me.nClass.classId == 1 && item.template.id == 218);
                                boolean valid = Char.canPickItemTemplate(item.template);

                                if (canPick && valid) {
                                    minDistance = d;
                                    if (item.isPickedUp == false) {
                                        Service.getInstance().pickItem(item.itemMapID);
                                        item.lastTimePickup = System.currentTimeMillis();
                                        item.isPickedUp = true;
                                    }
                                }
                            }

                        }
                    } catch (Exception var23) {
                        var23.printStackTrace();
                    }

                    if (Char.getMyChar().isCaptcha) {
                        LockGame.i();
                    }

                    Thread.sleep((var1 = System.currentTimeMillis() - var1) < 100L ? 100L - var1 : 0L);
                    continue;
                }
            } catch (Exception var24) {
                var24.printStackTrace();
            }

            return;
        }
    }

    public static boolean hasTuDung(int var0) {
        return tuDung.contains(new Integer(var0)) || AutoUseItem.contains(var0);
    }

    public static boolean hasTuDungSimple(int var0) {
        return tuDung.contains(new Integer(var0));
    }

    public static void addTuDung(int var0) {
        Integer var1 = new Integer(var0);
        if (!tuDung.contains(var1)) {
            tuDung.addElement(var1);
        }
    }

    public static void removeTuDung(int var0) {
        tuDung.removeElement(new Integer(var0));
    }

    public static int addTuDungList(int var0) {
        return AutoUseItem.addDefault(var0);
    }

    public static void ara() {
        for (int var0 = 0; var0 < dapdo.size(); ++var0) {
            Object var1 = dapdo.elementAt(var0);
            int var2 = 0;
            int var3 = 0;
            if (((Item) var1).isTypeClothe()) {
                var2 = GameScr.upClothe[((Item) var1).upgrade] / 2;
                var3 = GameScr.coinUpClothes[((Item) var1).upgrade];
            } else if (((Item) var1).isTypeAdorn()) {
                var2 = GameScr.upAdorn[((Item) var1).upgrade] / 2;
                var3 = GameScr.coinUpAdorns[((Item) var1).upgrade];
            } else if (((Item) var1).isTypeWeapon()) {
                var2 = GameScr.upWeapon[((Item) var1).upgrade] / 2;
                var3 = GameScr.coinUpWeapons[((Item) var1).upgrade];
            }

            if (var2 << 1 <= Char.getTotalValueCrystals() && var3 << 1 <= Char.getMyChar().yen && ((Item) var1).upgrade < SettingNVDV.upgrade) {
                int var4 = ((Item) var1).upgrade;
                if (cda) {
                    GameScr.getInstance().openUI((int) 10);
                }

                GameScr.itemUpGrade = (Item) var1;

                for (int var5 = 0; var5 < 1 && ((Item) var1).upgrade == var4; ++var5) {
                    GameScr.arrItemUpGrade = new Item[18];
                    int var6 = 0;
                    int var7 = 0;

                    for (int var8 = 0; var8 < Char.getMyChar().arrItemBag.length && var7 < var2; ++var8) {
                        Item var9 = Char.getMyChar().arrItemBag[var8];
                        if (((Item) var1).upgrade == 7) {
                            if (Char.getIndexItemById(242) < 0) {
                                if (Char.getMyChar().luong >= 10) {
                                    Service.getInstance().buyItem1(14, 23, 1);
                                    LockGame.g();
                                } else {
                                    GameScr.chatPopup("Hết Lượng Mua BHSC");
                                }
                            } else {
                                GameScr.arrItemUpGrade[0] = AutoDV.getBaoHiem(242);
                            }
                        }

                        if (var9 != null && var9.template.type == 26 && var9.template.id <= SettingNVDV.daUpgrade - 1) {
                            Char.getMyChar().arrItemBag[var8] = null;
                            GameScr.arrItemUpGrade[var6++] = var9;
                            var7 += GameScr.upClothe[var9.template.id];
                        }
                    }

                    do {
                        try {
                            Thread.sleep(1500L);
                        } catch (InterruptedException var10) {
                        }

                        Service.getInstance().upgradeItem1((Item) var1, GameScr.arrItemUpGrade, false);
                        Service.getInstance().viewInfo(Char.getMyChar().charName);
                        LockGame.q();
                    } while (GameScr.arrItemUpGrade[0] != null);
                }

                GameScr.itemUpGrade = null;
            } else if (((Item) var1).upgrade >= SettingNVDV.upgrade) {
                dapdo.removeElementAt(var0--);
            }
        }

    }

    public static boolean hasDapDo(Item var0) {
        return dapdo.contains(var0);
    }

    public static void addDapDo(Item var0) {
        if (!dapdo.contains(var0)) {
            dapdo.addElement(var0);
        }

    }

    public static void delDapDo(Item var0) {
        dapdo.removeElement(var0);
    }

    public static boolean a(Item var0) {
        return listTLItem.contains(var0);
    }

    public static void b(Item var0) {
        if (!listTLItem.contains(var0)) {
            listTLItem.addElement(var0);
        }

    }

    public static void c(Item var0) {
        listTLItem.removeElement(var0);
    }

    public static boolean d(int var0) {
        return z.contains(new Integer(var0));
    }

    public static int e(int var0) {
        return (var0 = z.indexOf(new Integer(var0))) >= 0 ? ((Integer) aa.elementAt(var0)).intValue() : 0;
    }

    public static boolean d1(int var0) {
        return z.contains(new Integer(var0));
    }

    public static int e1(int var0) {
        return (var0 = z.indexOf(new Integer(var0))) >= 0 ? ((Integer) aa.elementAt(var0)).intValue() : 0;
    }

    public static void b(int var0, int var1) {
        Integer var2 = new Integer(var0);
        if (!z.contains(var2)) {
            z.addElement(var2);
            aa.addElement(new Integer(var1));
        }

    }

    public static void fsw(int var0) {
        if ((var0 = z.indexOf(new Integer(var0))) >= 0) {
            z.removeElementAt(var0);
            aa.removeElementAt(var0);
        }

    }

    public static MyVector i() {
        MyVector var0 = new MyVector();

        for (int var1 = 0; var1 < z.size(); ++var1) {
            int var2 = ((Integer) z.elementAt(var1)).intValue();
            int var3 = ((Integer) aa.elementAt(var1)).intValue();
            ItemTemplate var4 = ItemTemplateManager.get((short) var2);
            var0.addElement(var1 + ". " + var4.name + " id " + var2 + " giá " + var3);
        }

        return var0;
    }

    public static void j() {
        Char var0 = Char.getMyChar();

        for (int var1 = 0; var1 < listTLItem.size(); ++var1) {
            Item var2;
            if ((var2 = (Item) listTLItem.elementAt(var1)).indexUI >= 0 && var2.indexUI < var0.arrItemBag.length) {
                if (var0.arrItemBag[var2.indexUI] != null && var0.arrItemBag[var2.indexUI].getTinhLuyen(85) >= 0 && var0.arrItemBag[var2.indexUI].getTinhLuyen(85) < 9) {
                    listTLItem.setElementAt(var0.arrItemBag[var2.indexUI], var1);
                } else {
                    listTLItem.removeElementAt(var1--);
                }
            }
        }

    }

    public static String k() {
        String var0 = "";

        for (int var1 = 0; var1 < w.length; ++var1) {
            var0 = var0 + (var1 == w.length - 1 ? String.valueOf(w[var1]) : w[var1] + " ");
        }

        return var0;
    }

    public static void e(String var0) {
        String[] var4;
        int[] var1 = new int[(var4 = splitString(var0, " ")).length];

        for (int var2 = 0; var2 < var4.length; ++var2) {
            try {
                var1[var2] = Integer.parseInt(var4[var2]);
            } catch (Exception var5) {
                var1[var2] = -1;
            }
        }

        w = var1;
    }

    public static void a(short var0) {
        int var1;
        for (var1 = 0; var1 < pickUpListID.length; ++var1) {
            if (pickUpListID[var1] == var0) {
                return;
            }
        }

        var1 = -1;

        for (int var2 = 0; var2 < pickUpListID.length; ++var2) {
            if (pickUpListID[var2] < 0) {
                var1 = var2;
                break;
            }
        }

        if (var1 == -1) {
            var1 = pickUpListID.length;
            short[] var4;
            System.arraycopy(var4 = new short[pickUpListID.length + 10], 0, pickUpListID, 0, pickUpListID.length);

            for (int var3 = pickUpListID.length; var3 < var4.length; ++var3) {
                var4[var3] = -1;
            }

            pickUpListID = var4;
        }

        pickUpListID[var1] = var0;
    }

    public static void b(short var0) {
        for (int var1 = 0; var1 < pickUpListID.length; ++var1) {
            if (pickUpListID[var1] == var0) {
                pickUpListID[var1] = -1;
            }
        }

    }

    public static void l() {
        for (int var0 = 0; var0 < pickUpListID.length; ++var0) {
            if (pickUpListID[var0] > 0) {
                for (int var1 = 0; var1 <= var0; ++var1) {
                    if (pickUpListID[var1] == -1) {
                        pickUpListID[var1] = pickUpListID[var0];
                        pickUpListID[var0] = -1;
                        break;
                    }
                }
            }
        }

    }

    public static void addDelItem(short var0) {
        int var1;
        for (var1 = 0; var1 < delListID.length; ++var1) {
            if (delListID[var1] == var0) {
                return;
            }
        }

        var1 = -1;

        for (int var2 = 0; var2 < delListID.length; ++var2) {
            if (delListID[var2] < 0) {
                var1 = var2;
                break;
            }
        }

        if (var1 == -1) {
            var1 = delListID.length;
            short[] var4 = delListID;
            short[] var5 = new short[delListID.length + 10];
            System.arraycopy(var4, 0, var5, 0, var4.length);

            for (int var3 = var4.length; var3 < var5.length; ++var3) {
                var5[var3] = -1;
            }

            delListID = var5;
        }

        delListID[var1] = var0;
    }

    public static void removeDelItem(short var0) {
        for (int var1 = 0; var1 < delListID.length; ++var1) {
            if (delListID[var1] == var0) {
                delListID[var1] = -1;
            }
        }
    }

    public static boolean containDelItem(short var0) {
        for (int var1 = 0; var1 < delListID.length; ++var1) {
            if (delListID[var1] == var0) {
                return true;
            }
        }
        return false;
    }

    public static void sortDelItem() {
        for (int var0 = 0; var0 < delListID.length; ++var0) {
            if (delListID[var0] > 0) {
                for (int var1 = 0; var1 <= var0; ++var1) {
                    if (delListID[var1] == -1) {
                        delListID[var1] = delListID[var0];
                        delListID[var0] = -1;
                        break;
                    }
                }
            }
        }

    }

    public static boolean isItemCanPickUp(ItemTemplate itemTemplate) {
        if (auto instanceof As50) {
            if (itemTemplate.type == 19) {
                return true;
            } else if ((itemTemplate.type == 16 || itemTemplate.type == 17) && itemTemplate.level == 10) {
                return true;
            } else {
                Char var3 = Char.getMyChar();
                if (Char.countNullSlot() <= 6) {
                    return false;
                } else if ((var3.ctaskId < 13 || var3.ctaskId == 13 && var3.arrItemBody[1] != null && var3.arrItemBody[1].upgrade < 2) && itemTemplate.type == 26 && itemTemplate.id > 0) {
                    return true;
                } else {
                    int var2 = var3.cgender == 1 ? 124 : 125;
                    return var3.ctaskId <= 12 && (itemTemplate.id == 174 && !Char.hasItem(174) || itemTemplate.id == var2 && !Char.hasItem(var2));
                }
            }
        } else if (auto instanceof As10) {
            return itemTemplate.type == 19;
        } else if (itemTemplate.type == 19) {
            return Char.dn;
        } else if (itemTemplate.type != 16 && itemTemplate.type != 17) {
            if (itemTemplate.type == 26) {
                return Char.tickNhatDa && itemTemplate.id >= Char.ew - 1;
            } else if (itemTemplate.isTypeBody()) {
                return (Char.tickNhatTrangBi || auto instanceof Stanima) && itemTemplate.level >= Char.ey;
            } else if (itemTemplate.isTypeTask()) {
                return Char.tickNhatVPNhiemVu;
            } else if (itemTemplate.isTypeMounts() || itemTemplate.isTypeBijuu()) {
                return Char.tickNhatTrangBiLa;
            } else {
                if (itemTemplate.type == 27) {
                    if (itemTemplate.description.startsWith("Vật phẩm sự kiện") || itemTemplate.description.startsWith("Vật phẩm Sự kiện") || itemTemplate.description.startsWith("Item sự kiện") || itemTemplate.description.startsWith("Sự kiện")) {
                        return Char.tickNhatVPSK;
                    }

                    if (itemTemplate.name.startsWith("Sách võ công")) {
                        return Char.tickNhatSVC;
                    }

                    if (TileMap.isLangCo(TileMap.mapID) && itemTemplate.id == 38) {
                        return false;
                    }
                }

                for (int var1 = 0; var1 < pickUpListID.length; ++var1) {
                    if (pickUpListID[var1] > 0 && itemTemplate.id == pickUpListID[var1]) {
                        return true;
                    }
                }

                return Char.tickNhatTatCa;
            }
        } else {
            return Char.doa && itemTemplate.level >= Char.ev;
        }
    }

    public static boolean isItemDel(Item item) {
        if (auto instanceof As10) {
            return false;
        } else if (item == null) {
            return false;
        } else if (item.upgrade > 0) {
            item.v = true;
            return false;
        } else {
            for (int i = 0; i < delListID.length; ++i) {
                if (delListID[i] > 0 && item.template.id == delListID[i]) {
                    if (isSixXFusionCandidate(item)) {
                        if (item.options == null || item.options.size() == 0) {
                            if (!item.s && System.currentTimeMillis() - item.t > 5000L) {
                                item.t = System.currentTimeMillis();
                                Service.getInstance().requestItemInfo(item.typeUI, item.indexUI);
                            }
                            return false;
                        }

                        if (AutoDungHop.isProtectedSixXFusionItem(item)) {
                            item.v = true;
                            return false;
                        }
                    }

                    if (item.template.type >= 29 && item.template.type <= 32 && item.getTinhLuyen() > 0) {
                        item.v = true;
                        return false;
                    }
                    return true;
                }
            }

            if (!item.v && System.currentTimeMillis() - item.u >= 5000L) {
                if ((auto instanceof Stanima || auto instanceof AutoUp) && item.template.type < 10 && item.template.level < 70) {
                    return true;
                } else if (auto instanceof AutoDV) {
                    if (item.template.type < 10) {
                        if (AutoDV.itemTemplate2 != null && item.template.id == AutoDV.itemTemplate2.id) {
                            return false;
                        }
                        return item.template.level < 50 && item.template.type != 1 && (AutoDV.itemTemplate == null || item.template.id != AutoDV.itemTemplate.id);
                    }
                    if (AutoDV.idQuest != 4 && item.template.type == 26) {
                        return true;
                    }
                    return false;
                } else if (item.template.type == 26 && item.template.id < (Char.tickNhatDa ? Char.ew : Char.ex) - 1) {
                    return true;
                } else {
                    if (item.template.type < 10 || item.template.type >= 29 && item.template.type <= 32) {
                        if (!Char.tickAutoLocDo) {
                            return false;
                        }
                        if (!item.s && System.currentTimeMillis() - item.t > 5000L) {
                            item.t = System.currentTimeMillis();
                            Service.getInstance().requestItemInfo(item.typeUI, item.indexUI);
                            if (!LockGame.s() || !item.s) {
                                return false;
                            }
                        }

                        if (item.template.type >= 29 && item.template.type <= 32) {
                            if (item.saleCoinLock != 0 || item.getTinhLuyen() > 0) {
                                item.v = true;
                                return false;
                            }
                            return true;
                        }

                        if (item.saleCoinLock == 5) {
                            return true;
                        }

                        if (item.hasOption(85)) {
                            item.v = true;
                            return false;
                        }

                        if (item.template.type == 1) {
                            if (item.hasOption(0) && item.hasOption(1)) {
                                if (!item.hasOption(8) && !item.hasOption(9)) {
                                    return true;
                                }

                                if (!item.hasOption(10)) {
                                    return true;
                                }

                                item.v = true;
                                return false;
                            }

                            return true;
                        }

                        if (!item.hasOption(6) || !item.hasOption(7)) {
                            return true;
                        }

                        if (item.countOptionByType(0) < 2) {
                            return true;
                        }

                        if (item.template.type == 8 && !item.hasOption(16)) {
                            return true;
                        }
                    }

                    item.v = true;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    private static boolean isSixXFusionCandidate(Item item) {
        return item != null
                && item.template != null
                && item.template.type >= 0
                && item.template.type <= 9
                && item.template.level >= 60
                && item.template.level <= 69;
    }

    private static boolean isFullSixXFusionItem(Item item) {
        if (!isSixXFusionCandidate(item) || item.options == null) {
            return false;
        }

        boolean hasDoBen = false;
        boolean hasGiaTriTang = false;
        int baseCount = 0;
        for (int i = 0; i < item.options.size(); i++) {
            ItemOption opt = (ItemOption) item.options.elementAt(i);
            if (opt == null || opt.optionTemplate == null) {
                continue;
            }

            int id = opt.optionTemplate.id;
            if (id == 155) {
                hasDoBen = true;
            } else if (id == 176) {
                hasGiaTriTang = true;
            } else {
                baseCount++;
            }
        }

        return hasDoBen && hasGiaTriTang && item.options.size() == 13 && baseCount == 11;
    }

    public static void n() {
        Char var0 = Char.getMyChar();
        if (!Char.hasItem(37) && !Char.hasItem(35)) {
            Npc var1;
            if ((var1 = GameScr.findNpc(13)) != null && Math.abs(var1.cx - var0.cx) <= 200 && Math.abs(var1.cy - var0.cy) <= 200) {
                Char.charMove(var1.cx > 200 ? var1.cx - 200 : var1.cx + 200, var1.cy);
            }

            Service.getInstance().openUIZone();
        } else {
            Char.charMove(var0.cx, TileMap.d);
        }

    }

    public final boolean f(String var1) {
        int value = 0;
        StringBuffer var2 = new StringBuffer();
        StringBuffer var4 = new StringBuffer();

        int var5;
        int var7;
        label1068:
        for (var5 = 0; var5 < var1.length(); ++var5) {
            if ((var7 = var1.charAt(var5)) >= 48 && var7 <= 57 || var7 == 32) {
                while (true) {
                    if (var5 >= var1.length() || (var7 = var1.charAt(var5)) < 48 || var7 > 57) {
                        break label1068;
                    }

                    var4.append((char) var7);
                    ++var5;
                }
            }

            var2.append((char) var7);
        }

        String var22 = var2.toString().toLowerCase();
        if (var4.length() > 0) {
            try {
                value = Integer.parseInt(var4.toString());
            } catch (Exception var29) {
            }
        }

        if (var22.equals("s")) {
            if (value == 0) {
                GameScr.chatPopup("Chạy đi đou với tốc độ 0?");
            } else if (value > 100) {
                GameScr.chatPopup("Tốc giày nên để <= 100 để ko bị giật!");
            } else {
                GameScr.chatPopup("Fake tốc chạy " + value);
                ag = value;
                af = true;
            }

            return true;
        } else if (var22.equals("rs")) {
            GameScr.chatPopup("Reset tốc chạy");
            af = false;
            return true;
        } else if (var22.equals("n")) {
            if (value == 0) {
                value = 100;
            }

            GameScr.chatPopup("Fake tầm ngang " + value);
            isFakeSkillNgang = true;
            nSkillFake = value;
            return true;
        } else if (var22.equals("c")) {
            if (value == 0) {
                value = 100;
            }

            GameScr.chatPopup("Fake tầm cao " + value);
            isFakeSkillCao = true;
            cSkillFake = value;
            return true;
        } else if (var22.equals("m")) {
            if (value == 0) {
                value = 1;
            }

            GameScr.chatPopup("Fake lan " + value);
            al = true;
            am = value;
            return true;
        } else if (var22.equals("rsk")) {
            GameScr.chatPopup("Reset fake tầm lan skill");
            al = false;
            isFakeSkillNgang = false;
            isFakeSkillCao = false;
            return true;
        } else if (var22.equals("set")) {
            (new SettingNVDV()).show();
            return true;
        } else if (var22.equals("gopgt")) {
            fastGopXuClan(value);
            return true;
        } else if (var22.equals("stopgopgt")) {
            fastGopXuClan = false;
            GameScr.chatPopup("Đã dừng góp xu gia tộc");
            return true;
        } else if (!var22.equals("bang") && !var22.equals("fz")) {
            if (!var22.equals("bangb") && !var22.equals("fb")) {
                if (!var22.equals("bangs") && !var22.equals("fs")) {
                    if (!var22.equals("pbang") && !var22.equals("wz")) {
                        if (var22.equals("u")) {
                            if (value == 0) {
                                value = 50;
                            }

                            GameScr.chatPopup("Khinh kông " + value);
                            Char.charMove(Char.getMyChar().cx, Char.getMyChar().cy - value);
                            return true;
                        } else if (var22.equals("d")) {
                            if (value == 0) {
                                value = 50;
                            }

                            GameScr.chatPopup("Độn thổ " + value);
                            Char.charMove(Char.getMyChar().cx, Char.getMyChar().cy + value);
                            return true;
                        } else if (var22.equals("l")) {
                            if (value == 0) {
                                value = 50;
                            }

                            GameScr.chatPopup("Dịch trái " + value);
                            Char.charMove(Char.getMyChar().cx - value, Char.getMyChar().cy);
                            return true;
                        } else if (var22.equals("r")) {
                            if (value == 0) {
                                value = 50;
                            }

                            GameScr.chatPopup("Dịch phải " + value);
                            Char.charMove(Char.getMyChar().cx + value, Char.getMyChar().cy);
                            return true;
                        } else {
                            Char var36;
                            if (var22.equals("g")) {
                                if ((var36 = Char.getMyChar()).charFocus != null) {
                                    GameScr.chatPopup("MoveTo " + var36.charFocus.charName);
                                    Char.charMove(var36.charFocus.cx, var36.charFocus.cy);
                                } else if (var36.npcFocus != null) {
                                    GameScr.chatPopup("MoveTo " + var36.npcFocus.charName);
                                    Char.charMove(var36.npcFocus.cx, var36.npcFocus.cy);
                                } else if (var36.mobFocus != null) {
                                    GameScr.chatPopup("MoveTo " + var36.mobFocus.getMobTemplate().name);
                                    Char.charMove(var36.mobFocus.cx, var36.mobFocus.cy);
                                } else if (var36.itemFocus != null) {
                                    GameScr.chatPopup("MoveTo " + var36.itemFocus.template.name);
                                    Char.charMove(var36.itemFocus.x, var36.itemFocus.y);
                                }

                                return true;
                            } else if (var22.equals("ta")) {
                                GameScr.getInstance().openUI(9);
                                return true;
                            } else if (var22.equals("nsochen")) {
                                showNsoChenMenu = !showNsoChenMenu;
                                saveNsoChenMenuState();
                                GameScr.chatPopup("Menu NSO Chen: " + (showNsoChenMenu ? "Bật" : "Tắt"));
                                return true;
                            } else if (var22.equals("tmt") || var22.equals("telemt")) {
                                teleTarget = !teleTarget;
                                GameScr.chatPopup("Tele mục tiêu: " + (teleTarget ? "Bật" : "Tắt"));
                                return true;
                            } else if (var22.equals("mobid") || var22.equals("tenquai")) {
                                showMobNameId = !showMobNameId;
                                GameScr.chatPopup("Tên quái(ID): " + (showMobNameId ? "Bật" : "Tắt"));
                                return true;
                            } else if (var22.equals("sw")) {
                                GameScr.getInstance().openUI(36);
                                return true;
                            } else if (var22.equals("aq")) {
                                if ((var36 = Char.getMyChar()).mobFocus != null) {
                                    GameScr.vMobAttack.removeElement(var36.mobFocus);
                                }

                                return true;
                            } else if (var22.equals("z")) {
                                GameScr.chatPopup((Char.tickChuyenMapHetBoss ? "Tắt" : "Bật") + " auto chuyển map");
                                Char.tickChuyenMapHetBoss = !Char.tickChuyenMapHetBoss;
                                return true;
                            } else if (var22.equals("rm")) {
                                GameScr.chatPopup((Char.tickReMap ? "Tắt" : "Bật") + " auto next map");
                                Char.tickReMap = !Char.tickReMap;
                                return true;
                            } else if (var22.equals("aq")) {
                                if ((var36 = Char.getMyChar()).mobFocus != null) {
                                    GameScr.vMobAttack.removeElement(var36.mobFocus);
                                }

                                return true;
                            } else if (var22.equals("x")) {
                                if (value == 0) {
                                    value = -1;
                                }

                                GameScr.chatPopup("KC Nhặt " + value);
                                khoangCachNhat = value;
                                return true;
                            } else if (var22.equals("kts")) {
                                if (value == 0) {
                                    value = -1;
                                }

                                GameScr.chatPopup("KC Tàn sát " + value);
                                n = Char.getMyChar().cx;
                                o = Char.getMyChar().cy;
                                m = value;
                                return true;
                            } else {
                                Mob var33;
                                if (var22.equals("ts")) {
                                    if ((var33 = Mob.b(value)) == null) {
                                        GameScr.chatPopup("Tàn sát all");
                                        this.tanSat(-1, TileMap.mapID);
                                    } else {
                                        GameScr.chatPopup("Tàn sát " + var33.getMobTemplate().name + " lv " + value);
                                        this.tanSat(var33.id, TileMap.mapID);
                                    }

                                    return true;
                                } else {
                                    MobTemplate var10000;
                                    if (var22.equals("tsx")) {
                                        var10000 = getMobTemplateByCommandId(value);
                                        if (var10000 == null) {
                                            GameScr.chatPopup("Tàn sát all");
                                            GameScr.chatPopup("Tan sat id " + value);
                                            this.tanSat(value, TileMap.mapID);
                                        } else {
                                            GameScr.chatPopup("Tàn sát " + var10000.name + " id " + value);
                                            this.tanSat(value, TileMap.mapID);
                                        }

                                        return true;
                                    } else if (var22.equals("tsa")) {
                                        GameScr.chatPopup("Tàn sát all");
                                        this.tanSat(-1, TileMap.mapID);
                                        return true;
                                    } else if (var22.equals("anv")) {
                                        GameScr.chatPopup("Auto Nhiệm Vụ Hằng Ngày");
                                        this.startAutoNVHN();
                                        return true;
                                    } else if (var22.startsWith("asall")) {
                                        int asallClass = getAsallClassId(var1, value);
                                        GameScr.chatPopup("Auto nhiệm vụ all: " + getAsallClassName(asallClass));
                                        setAuto((Auto) (new AutoFinishTask(asallClass)));
                                        return true;
                                    } else if (var22.equals("att")) {
                                        GameScr.chatPopup("Auto Tà Thú");
                                        this.e();
                                        return true;
                                    } else if (var22.equals("atts")) {
                                        GameScr.chatPopup("Auto Tà Thú by Chen");
                                        Code.instance.startAutoTaThuSolo();
                                        return true;
                                    } else if (var22.equals("ak")) {
                                        if (auto == at) {
                                            GameScr.chatPopup("Tắt tự đánh");
                                            tatAuto();
                                        } else {
                                            GameScr.chatPopup("Bật tự đánh");
                                            this.p();
                                        }

                                        return true;
                                    } else if (var22.equals("jgt")) {
                                        GameScr.chatPopup("Auto Vào LDGT");
                                        startJoinClanDun();
                                        return true;
                                    } else if (var22.equals("lh")) {
                                        if (value <= 0) {
                                            value = 50;
                                        }
                                        (new Thread(new AutoLuckyCard(value))).start();
                                        GameCanvas.setMaxTextLenght();
                                        return true;
                                    } else if (var22.equals("adv")) {
                                        GameScr.chatPopup("Auto Danh Vọng");
                                        startAutoDV();
                                        return true;
                                    } else if (var22.equals("nw")) {
                                        if (auto == autoLoiDai) {
                                            GameScr.chatPopup("Tắt auto lôi đài win");
                                            tatAuto();
                                        } else {
                                            GameScr.chatPopup("Bật auto lôi đài win");
                                            autoLoiDai.init();
                                            setAuto((Auto) autoLoiDai);
                                        }
                                        return true;
                                    } else if (var22.equals("nl")) {
                                        if (auto == autoLoseLoiDai) {
                                            GameScr.chatPopup("Tắt auto lôi đài lose");
                                            tatAuto();
                                        } else {
                                            GameScr.chatPopup("Bật auto lôi đài lose");
                                            autoLoseLoiDai.aea();
                                            setAuto((Auto) autoLoseLoiDai);
                                        }
                                        return true;
                                    } else if (var22.equals("ntgt")) {
                                        if (auto == autoNTGT) {
                                            GameScr.chatPopup("Tắt auto NTGT");
                                            tatAuto();
                                        } else {
                                            GameScr.chatPopup("Bật auto NTGT");
                                            if (!isInClan(Char.getMyChar())) {
                                                GameScr.chatPopup("NTGT: chưa có gia tộc, không bật");
                                                return true;
                                            }

                                            setAuto((Auto) autoNTGT);
                                        }
                                        return true;

                                    } else if (var22.equals("ttgt")) {
                                        String ttgtArg = var1.length() > 4 ? var1.substring(4).trim() : "";

                                        if (ttgtArg.length() > 0) {
                                            try {
                                                int option = Integer.parseInt(ttgtArg);

                                                if (option < 0 || option > 2) {
                                                    GameScr.chatPopup("TTGT option chỉ nhận 0-2");
                                                    return true;
                                                }

                                                setTTGTOption(option);
                                                GameScr.chatPopup("TTGT option 604: " + Char.ttgtOption);
                                            } catch (Exception e) {
                                                GameScr.chatPopup("Dùng: ttgt0, ttgt1, ttgt2");
                                            }

                                            return true;
                                        }

                                        boolean enableTTGT = !Char.tickAutoTTGT;

                                        if (enableTTGT && !isInClan(Char.getMyChar())) {
                                            setAutoTTGT(false);
                                            GameScr.chatPopup("TTGT: chưa có gia tộc, không bật");
                                        } else {
                                            setAutoTTGT(enableTTGT);
                                            GameScr.chatPopup((Char.tickAutoTTGT ? "Bật" : "Tắt") + " auto TTGT");
                                        }

                                        return true;

                                    } else if (var22.equals("ldgt")) {
                                        AutoLDGT.resetClanSignals();
                                        Code.setAuto(new AutoLDGT());
                                        GameScr.chatPopup("Auto LDGT: " + FormLDGT.getRoleName());
                                        return true;
                                    } else if (var22.equals("menuldgt")) {
                                        new FormLDGT().select();
                                        return true;
                                    } else if (var1.equals("menuhd9x")) {
                                        new FormHD9x().select();
                                        return true;
                                    } else if (var1.equals("hd9x")) {
                                        AutoHD9xManager.startManual();
                                        return true;
                                    } else if (var22.equals("menuboss")) {
                                        new FormAutoBoss().select();
                                        return true;
                                    } else if (var22.equals("boss") || var22.equals("bossall")) {
                                        AutoBossScheduleManager.startManual();
                                        return true;
                                    } else if (var22.equals("menugomsk")) {
                                        new FormEventTrade().select();
                                        return true;
                                    } else if (var22.equals("menumua") || var22.equals("menumuashop")) {
                                        new FormAutoBuyShop().select();
                                        return true;
                                    } else if (var22.equals("setcc") || var22.equals("menucauca")) {
                                        new FormAutoCauCa().select();
                                        return true;
                                    } else if (var22.equals("cc") || var22.equals("cauca")) {
                                        AutoCauCa.toggle();
                                        return true;
                                    } else if (var22.equals("stopcc") || var22.equals("dungcc")) {
                                        AutoCauCa.stop();
                                        return true;
                                    } else if (var22.equals("menugift") || var22.equals("menugc")) {
                                        new FormAutoGiftCode().select();
                                        return true;
                                    } else if (var22.equals("gift") || var22.equals("giftcode") || var22.equals("autogift")) {
                                        AutoGiftCode.start();
                                        return true;
                                    } else if (var22.equals("stopgift") || var22.equals("dunggift")) {
                                        AutoGiftCode.stop();
                                        return true;
                                    } else if (var22.equals("cleargift") || var22.equals("xoagift")) {
                                        AutoGiftCode.clearHistory();
                                        return true;
                                    } else if (var22.equals("setchat") || var22.equals("menuchat") || var22.equals("setautochat")) {
                                        new FormAutoChat().select();
                                        return true;
                                    } else if (var22.equals("achat") || var22.equals("autochat")) {
                                        AutoChat.toggle();
                                        return true;
                                    } else if (var22.equals("achatnow") || var22.equals("chatnow")) {
                                        AutoChat.sendNow();
                                        return true;
                                    } else if (var22.equals("stopchat") || var22.equals("stopachat")) {
                                        AutoChat.stop();
                                        return true;
                                    } else if (var22.equals("autoshop")) {
                                        AutoBuyShop.enabled = !AutoBuyShop.enabled;
                                        AutoBuyShop.save();
                                        GameScr.chatPopup("Tự mua shop: " + (AutoBuyShop.enabled ? "Bật" : "Tắt"));
                                        return true;
                                    } else if (var22.equals("gui") || var22.equals("gomdo") || var22.equals("gdngay")) {
                                        return startGomDoNow();
                                    } else if (var22.equals("gomsk")) {
                                        AutoEventTrade.startMain();
                                        return true;
                                    } else if (var22.equals("gomskgt") || var22.equals("setgomskgt") || var22.equals("setgomsk")) {
                                        AutoEventTrade.callClanFromForm();
                                        return true;
                                    } else if (var22.equals("menudd")) {
                                        new FormAutoDapDo().select();
                                        return true;
                                    } else if (var22.equals("dd") || var22.equals("dapdo")) {
                                        AutoDapDo.toggle();
                                        return true;
                                    } else if (var22.equals("stopdd")) {
                                        AutoDapDo.stop();
                                        return true;
                                    } else if (var22.equals("menudh")) {
                                        new FormAutoDungHop().select();
                                        return true;
                                    } else if (var22.equals("dh") || var22.equals("dunghop")) {
                                        AutoDungHop.toggle();
                                        return true;
                                    } else if (var22.equals("stopdh")) {
                                        AutoDungHop.stop();
                                        return true;
                                    } else if (var22.equals("menubk")) {
                                        new FormAutoBiKip().select();
                                        return true;
                                    } else if (var22.equals("bk") || var22.equals("bikip")) {
                                        AutoBiKip.toggle();
                                        return true;
                                    } else if (var22.equals("stopbk")) {
                                        AutoBiKip.stop();
                                        return true;
                                    } else if (var22.equals("menutask") || var22.equals("settime")) {
                                        new FormAutoTask().select();
                                        return true;
                                    } else if (var22.equals("setln")) {
                                        new FormAutoLuyenNgoc().select();
                                        return true;
                                    } else if (var22.equals("setden") || var22.equals("menuden")) {
                                        new FormAutoLongDen().select();
                                        return true;
                                    } else if (var22.equals("den") || var22.equals("doiden")) {
                                        AutoDoiLongDen.toggle();
                                        return true;
                                    } else if (var22.equals("stopden")) {
                                        AutoDoiLongDen.stop();
                                        return true;
                                    } else if (var22.equals("setrd") || var22.equals("menurd")) {
                                        new FormAutoRuocDen().select();
                                        return true;
                                    } else if (var22.equals("rd") || var22.equals("ruocden")) {
                                        AutoRuocDen.toggle();
                                        return true;
                                    } else if (var22.equals("stoprd")) {
                                        AutoRuocDen.stop();
                                        return true;
                                    } else if (var22.equals("setvt") || var22.equals("menuvt") || var22.equals("menuvithu")) {
                                        new FormAutoViThu().select();
                                        return true;
                                    } else if (var22.equals("settrungvt")) {
                                        new FormAutoViThu().select();
                                        return true;
                                    } else if (var22.equals("trungvt") || var22.equals("motrungvt")) {
                                        AutoViThu.toggleOpenEgg();
                                        return true;
                                    } else if (var22.equals("stoptrungvt")) {
                                        AutoViThu.stopOpenEgg();
                                        return true;
                                    } else if (var22.equals("vt") || var22.equals("vithu")) {
                                        AutoViThu.toggle();
                                        return true;
                                    } else if (var22.equals("stopvt")) {
                                        AutoViThu.stop();
                                        return true;
                                    } else if (var22.equals("menutl") || var22.equals("menutinhluyen")) {
                                        new FormAutoTinhLuyen().select();
                                        return true;
                                    } else if (var22.equals("tl") || var22.equals("tinhluyen")) {
                                        AutoTinhLuyen.toggle();
                                        return true;
                                    } else if (var22.equals("stoptl") || var22.equals("dungtl")) {
                                        AutoTinhLuyen.stop();
                                        return true;
                                    } else if (var22.equals("dc")) {
                                        AutoTinhLuyen.startConvertOnly();
                                        return true;
                                    }
                                    if (var22.equals("formnpc")) {
                                        new FormAutoNpc().select();
                                        return true;
                                    }

                                    if (var22.equals("anpc")) {
                                        AutoNpc.startFormConfig();
                                        return true;
                                    }
                                    if (var22.equals("dungnpc")) {
                                        AutoNpc.stopAuto();
                                        GameScr.chatPopup("Đã dừng Auto NPC");
                                        return true;
                                    }
                                    if (var22.equals("anpcgt")) {
                                        AutoNpc.callClanFromForm();
                                        return true;
                                    } else if (var22.equals("aln")) {
                                        AutoLuyenNgoc.toggleSlot0();
                                        return true;
                                    } else if (var22.equals("uplv")) {
                                        return AutoUpLevel.start(value);
                                    } else if (var22.equals("uplvpt")) {
                                        return AutoUpLevel.start(value, true);
                                    } else if (var22.equals("stopuplv")) {
                                        return AutoUpLevel.stop();
                                    } else if (var22.equals("up")) {
                                        FormAutoUp.startAutoUp();
                                        return true;
                                    } else if (var22.equals("pk")) {
                                        an = !an;
                                        GameScr.chatPopup((an ? " Bật " : " Tắt ") + "auto pk!");
                                        return true;
                                    } else if (!var22.equals("e") && !var22.equals("pe")) {
                                        if (var22.equals("k")) {
                                            GameScr.chatPopup("Chuyển Khu: " + value);
                                            GameScr.getInstance().j(value);
                                            return true;
                                        } else if (var22.equals("ltd")) {
                                            if ((auto instanceof AutoNVHN || auto instanceof AutoTaThu || auto instanceof AutoTaThuSolo) && Auto.goTruongAndLuuToaDoIfNeeded()) {
                                                return true;
                                            }

                                            if (!TileMap.isTruong(TileMap.mapID) && !TileMap.isLang(TileMap.mapID)) {
                                                GameScr.chatPopup("Hãy đứng ở làng hoặc trường để lưu tọa độ");
                                            } else {
                                                Auto.luuToaDoHienTai();
                                            }

                                            return true;
                                        } else if (var22.equals("nm")) {
                                            GameScr.chatPopup("Next map: " + value);
                                            TileMap.m(value);
                                            return true;
                                        } else if (var22.equals("gm")) {
                                            if (value < TileMap.mapNames.length && value >= 0) {
                                                GameScr.chatPopup("Go to: " + TileMap.mapNames[value]);
                                                TileMap.gomap(value);
                                                return true;
                                            } else {
                                                return true;
                                            }
                                        } else if (var22.equals("npc")) {
                                            if (value < Npc.arrNpcTemplate.length) {
                                                GameScr.chatPopup("Act NPC: " + Npc.arrNpcTemplate[value].name);
                                                GameScr.goNPC(value);
                                            }

                                            return true;
                                        } else if (var22.equals("hs")) {
                                            GameScr.chatPopup("Next to hirosaki");
                                            TileMap.gomap(1);
                                            return true;
                                        } else if (var22.equals("hr")) {
                                            GameScr.chatPopup("Next to haruna");
                                            TileMap.gomap(27);
                                            return true;
                                        } else if (var22.equals("oz")) {
                                            GameScr.chatPopup("Next to Ozawa(Oozaka)");
                                            TileMap.gomap(72);
                                            return true;
                                        } else if (var22.equals("kj")) {
                                            GameScr.chatPopup("Next to Kojin");
                                            TileMap.gomap(10);
                                            return true;
                                        } else if (var22.equals("sz")) {
                                            GameScr.chatPopup("Next to Sanzu");
                                            TileMap.gomap(17);
                                            return true;
                                        } else if (var22.equals("tn")) {
                                            GameScr.chatPopup("Next to Tone");
                                            TileMap.gomap(22);
                                            return true;
                                        } else if (var22.equals("lc")) {
                                            GameScr.chatPopup("Next to Chài");
                                            TileMap.gomap(32);
                                            return true;
                                        } else if (var22.equals("ck")) {
                                            GameScr.chatPopup("Next to Chakumi");
                                            TileMap.gomap(38);
                                            return true;
                                        } else if (var22.equals("eg")) {
                                            GameScr.chatPopup("Next to Echigo");
                                            TileMap.gomap(43);
                                            return true;
                                        } else if (var22.equals("os")) {
                                            GameScr.chatPopup("Next to Oshin");
                                            TileMap.gomap(48);
                                            return true;
                                        } else if (var22.equals("mnv")) {
                                            GameScr.chatPopup("Next to Map Nhiệm Vụ");
                                            TileMap.gomap(GameScr.ad());
                                            return true;
                                        } else if (var22.equals("mnvp")) {
                                            GameScr.chatPopup("Next to Map Nhiệm Vụ Phụ");
                                            TaskOrder var39;
                                            if ((var39 = Char.getTaskOrderById(0)) != null) {
                                                TileMap.gomap(var39.mapId);
                                            }

                                            return true;
                                        } else {
                                            ItemMap var38;
                                            if (var22.equals("add")) {
                                                GameScr.chatPopup("Thêm vật phẩm vào ds nhặt");
                                                if ((var38 = Char.getMyChar().itemFocus) != null) {
                                                    a(var38.template.id);
                                                }

                                                return true;
                                            } else if (var22.equals("del")) {
                                                GameScr.chatPopup("Xóa vật phẩm khỏi ds nhặt");
                                                if ((var38 = Char.getMyChar().itemFocus) != null) {
                                                    b(var38.template.id);
                                                }

                                                return true;
                                            } else {
                                                ItemTemplate var37;
                                                if (var22.equals("ait")) {
                                                    if ((var37 = ItemTemplateManager.get((short) value)) != null) {
                                                        GameScr.chatPopup("Thêm " + var37.name + " vào ds nhặt");
                                                        a(var37.id);
                                                    }

                                                    return true;
                                                } else if (var22.equals("dit")) {
                                                    if ((var37 = ItemTemplateManager.get((short) value)) != null) {
                                                        GameScr.chatPopup("Xóa " + var37.name + " khỏi ds nhặt");
                                                        b(var37.id);
                                                    }

                                                    return true;
                                                } else if (var22.equals("ajt")) {
                                                    if ((var37 = ItemTemplateManager.get((short) value)) != null) {
                                                        GameScr.chatPopup("Thêm " + var37.name + " vào ds nhặt");
                                                        addDelItem(var37.id);
                                                    }

                                                    return true;
                                                } else if (var22.equals("djt")) {
                                                    if ((var37 = ItemTemplateManager.get((short) value)) != null) {
                                                        GameScr.chatPopup("Xóa " + var37.name + " khỏi ds nhặt");
                                                        removeDelItem(var37.id);
                                                    }

                                                    return true;
                                                } else if (var22.equals("cnhat")) {
                                                    if (isHutVP) {
                                                        GameScr.chatPopup("Bật nhặt xa");
                                                    } else {
                                                        GameScr.chatPopup("Bật hút VP");
                                                    }

                                                    isHutVP = !isHutVP;
                                                    return true;
                                                } else if (var22.equals("ruong")) {
                                                    GameScr.getInstance().showTabBag();
                                                    return true;
                                                } else if (var22.equals("vpnhat")) {
                                                    GameScr.getInstance().openUI(46);
                                                    return true;
                                                } else if (var22.equals("die")) {
                                                    n();
                                                    return true;
                                                } else if (var22.equals("dcvt")) {
                                                    if (attackChangePosition) {
                                                        GameScr.chatPopup("Tắt đánh chuyển vị trí");
                                                    } else {
                                                        GameScr.chatPopup("Bật đánh chuyển vị trí");
                                                    }

                                                    attackChangePosition = !attackChangePosition;
                                                    if (Char.tickDanhTheoNhom) {
                                                        Service.getInstance().k("dcvt " + (attackChangePosition ? 1 : 0));
                                                    }

                                                    return true;
                                                } else if (var22.equals("avt")) {
                                                    GameScr.chatPopup("Thêm vị trí " + s.size());
                                                    s.addElement(new Integer(Char.getMyChar().cx));
                                                    t.addElement(new Integer(Char.getMyChar().cy));
                                                    if (Char.tickDanhTheoNhom) {
                                                        Service.getInstance().k("avt " + Char.getMyChar().cx + " " + Char.getMyChar().cy);
                                                    }

                                                    return true;
                                                } else if (var22.equals("dvt")) {
                                                    GameScr.chatPopup("Xóa hết vị trí");
                                                    s.removeAllElements();
                                                    t.removeAllElements();
                                                    if (Char.tickDanhTheoNhom) {
                                                        Service.getInstance().k("dvt");
                                                    }

                                                    return true;
                                                } else if (var22.equals("dvtx")) {
                                                    GameScr.chatPopup("Xóa vị trí " + value);
                                                    s.removeElementAt(value);
                                                    t.removeElementAt(value);
                                                    if (Char.tickDanhTheoNhom) {
                                                        Service.getInstance().k("dtvx " + value);
                                                    }

                                                    return true;
                                                } else if (var22.equals("dck")) {
                                                    if (attackChangeZone = !attackChangeZone) {
                                                        GameScr.chatPopup("Tắt đánh chuyển khu");
                                                    } else {
                                                        GameScr.chatPopup("Bật đánh chuyển khu");
                                                        GameCanvas.ak.a("Khu", new Command("Đặt", 1100090), 1);
                                                        GameCanvas.ak.tfInput.a(k());
                                                    }

                                                    return true;
                                                } else if (var22.equals("glv")) {
                                                    if (keepLevel) {
                                                        GameScr.chatPopup("Tắt giữ lv");
                                                    } else {
                                                        GameScr.chatPopup("Bật giữ lv");
                                                    }

                                                    keepLevel = !keepLevel;
                                                    return true;
                                                } else if (var22.equals("addn")) {
                                                    GameScr.chatPopup("Thêm nhóm");
                                                    if ((var36 = Char.getMyChar().charFocus) != null) {
                                                        if (!c(var36.charName)) {
                                                            h.addElement(var36.charName);
                                                        }

                                                        Service.getInstance().addParty(var36.charName);
                                                    }

                                                    return true;
                                                } else if (var22.equals("cn")) {
                                                    GameScr.chatPopup("Xóa nhóm");
                                                    g = null;
                                                    h.removeAllElements();
                                                    s();
                                                    return true;
                                                } else {
                                                    String var30;
                                                    if (var22.equals("pt")) {
                                                        if (!Char.getMyChar().charName.equals(g)) {
                                                            GameScr.chatPopup("Bạn không là nhóm trưởng");
                                                            return true;
                                                        } else {
                                                            GameScr.chatPopup("PT nhóm");

                                                            for (var5 = 0; var5 < h.size(); ++var5) {
                                                                if (!d(var30 = (String) h.elementAt(var5))) {
                                                                    Service.getInstance().addParty(var30);
                                                                }

                                                                if (auto instanceof AutoPKBoss) {
                                                                    Service.getInstance().a(var30, "pkm " + auto.mapID);
                                                                    Service.getInstance().a(var30, "pkk " + auto.zoneID);
                                                                } else if (auto != null) {
                                                                    Service.getInstance().a(var30, "map " + auto.mapID);
                                                                    Service.getInstance().a(var30, "khu " + auto.zoneID);
                                                                }
                                                            }

                                                            return true;
                                                        }
                                                    } else if (var22.equals("sn")) {
                                                        GameScr.chatPopup("Lưu nhóm");
                                                        s();
                                                        return true;
                                                    } else if (var22.equals("tsn")) {
                                                        if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID) {
                                                            if ((var33 = Mob.b(value)) == null) {
                                                                GameScr.chatPopup("Tàn sát nhóm all");
                                                                this.tanSat(-1, TileMap.mapID);
                                                            } else {
                                                                GameScr.chatPopup("Tàn sát nhóm " + var33.getMobTemplate().name + " lv " + value);
                                                                this.tanSat(var33.id, TileMap.mapID);
                                                            }

                                                            autoTanSat.g = true;
                                                            Service.getInstance().k("ts " + autoTanSat.mapID + " " + autoTanSat.zoneID + " " + autoTanSat.modID);
                                                            return true;
                                                        } else {
                                                            GameScr.chatPopup("Chưa có nhóm hoặc bạn không là nhóm trưởng");
                                                            return true;
                                                        }
                                                    } else if (var22.equals("tsnx")) {
                                                        if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID) {
                                                            var10000 = getMobTemplateByCommandId(value);
                                                            if (var10000 == null) {
                                                                GameScr.chatPopup("Tàn sát nhóm all");
                                                                this.tanSat(-1, TileMap.mapID);
                                                            } else {
                                                                GameScr.chatPopup("Tàn sát nhóm " + var10000.name + " id " + value);
                                                                this.tanSat(value, TileMap.mapID);
                                                            }

                                                            autoTanSat.g = true;
                                                            Service.getInstance().k("ts " + autoTanSat.mapID + " " + autoTanSat.zoneID + " " + autoTanSat.modID);
                                                            return true;
                                                        } else {
                                                            GameScr.chatPopup("Chưa có nhóm hoặc bạn không là nhóm trưởng");
                                                            return true;
                                                        }
                                                    } else if (var22.equals("tsan")) {
                                                        if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID) {
                                                            GameScr.chatPopup("Tàn sát nhóm all");
                                                            this.tanSat(-1, TileMap.mapID);
                                                            autoTanSat.g = true;
                                                            Service.getInstance().k("tsa " + autoTanSat.mapID + " " + autoTanSat.zoneID);
                                                            return true;
                                                        } else {
                                                            GameScr.chatPopup("Chưa có nhóm hoặc bạn không là nhóm trưởng");
                                                            return true;
                                                        }
                                                    } else if (var22.equals("attn")) {
                                                        if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID) {
                                                            GameScr.chatPopup("Auto Tà Thú Nhóm");
                                                            this.e();
                                                            e.g = true;
                                                            Service.getInstance().k("att " + e.mapID + " " + e.zoneID + " " + e.killId);
                                                            return true;
                                                        } else {
                                                            GameScr.chatPopup("Chưa có nhóm hoặc bạn không là nhóm trưởng");
                                                            return true;
                                                        }
                                                    } else if (var22.equals("buff")) {
                                                        GameScr.chatPopup("Bật Buff HS Xa");
                                                        this.b(true, true);
                                                        return true;
                                                    } else if (var22.equals("bux")) {
                                                        GameScr.chatPopup("Bật Buff Xa");
                                                        this.b(true, false);
                                                        return true;
                                                    } else if (var22.equals("hsx")) {
                                                        GameScr.chatPopup("Bật HS Xa");
                                                        this.b(false, true);
                                                        return true;
                                                    } else {
                                                        int var8;
                                                        if (var22.equals("cy")) {
                                                            if (!AutoUp.hasRunningAutoUp(auto)) {
                                                                GameScr.chatPopup("Bạn chưa up yên");
                                                            } else {
                                                                GameScr.chatPopup(AutoUp.getEarnedYenChatText(auto));
                                                            }

                                                            return true;
                                                        } else {
                                                            int var24;
                                                            if (var22.equals("clv")) {
                                                                if (!AutoUp.hasRunningAutoUp(auto)) {
                                                                    GameScr.chatPopup("Bạn chưa up level");
                                                                } else {
                                                                    GameScr.chatPopup(AutoUp.getEarnedExpChatText(auto));
                                                                }

                                                                return true;
                                                            } else if (var22.equals("st")) {
                                                                if ((var33 = Mob.b(value)) == null) {
                                                                    GameScr.chatPopup("Stanima all");
                                                                    this.c(-1, TileMap.mapID);
                                                                } else {
                                                                    GameScr.chatPopup("Stanima " + var33.getMobTemplate().name + " lv " + value);
                                                                    this.c(var33.id, TileMap.mapID);
                                                                }

                                                                return true;
                                                            } else if (var22.equals("sta")) {
                                                                GameScr.chatPopup("Stanima all");
                                                                this.c(-1, TileMap.mapID);
                                                                return true;
                                                            } else if (var22.equals("stn")) {
                                                                if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID) {
                                                                    if ((var33 = Mob.b(value)) == null) {
                                                                        GameScr.chatPopup("Stanima nhóm all");
                                                                        this.c(-1, TileMap.mapID);
                                                                    } else {
                                                                        GameScr.chatPopup("Stanima nhóm " + var33.getMobTemplate().name + " lv " + value);
                                                                        this.c(var33.id, TileMap.mapID);
                                                                    }

                                                                    c.g = true;
                                                                    Service.getInstance().k("st " + c.mapID + " " + c.zoneID + " " + c.b);
                                                                    return true;
                                                                } else {
                                                                    GameScr.chatPopup("Chưa có nhóm hoặc bạn không là nhóm trưởng");
                                                                    return true;
                                                                }
                                                            } else if (var22.equals("stan")) {
                                                                if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID) {
                                                                    GameScr.chatPopup("Stanima nhóm all");
                                                                    this.c(-1, TileMap.mapID);
                                                                    c.g = true;
                                                                    Service.getInstance().k("sta " + c.mapID + " " + c.zoneID);
                                                                    return true;
                                                                } else {
                                                                    GameScr.chatPopup("Chưa có nhóm hoặc bạn không là nhóm trưởng");
                                                                    return true;
                                                                }
                                                            } else if (var22.equals("stx")) {
                                                                var10000 = value >= 0 && value < Mob.mobTemplates.length ? Mob.mobTemplates[value] : null;
                                                                if (var10000 == null) {
                                                                    GameScr.chatPopup("Tàn sát all");
                                                                    this.tanSat(-1, TileMap.mapID);
                                                                } else {
                                                                    GameScr.chatPopup("Tàn sát " + var10000.name + " id " + value);
                                                                    this.c(value, TileMap.mapID);
                                                                }

                                                                return true;
                                                            } else if (!var22.equals("stnx")) {
                                                                if (var22.equals("sts")) {
                                                                    GameScr.chatPopup("Step Stanima");
                                                                    c.e();
                                                                    if (Char.getMyChar().charName.equals(g) && GameScr.vParty.size() > 0) {
                                                                        Service.getInstance().k("sts");
                                                                    }

                                                                    return true;
                                                                } else if (var22.equals("stb")) {
                                                                    if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a != Char.getMyChar().charID) {
                                                                        if (Char.getMyChar().nClass.classId != 6) {
                                                                            GameScr.chatPopup("Bạn không phải là quạt");
                                                                            return true;
                                                                        } else {
                                                                            GameScr.chatPopup("Stanima Buff HS");
                                                                            this.a(true, true);
                                                                            return true;
                                                                        }
                                                                    } else {
                                                                        GameScr.chatPopup("Chưa có nhóm hoặc bạn là nhóm trưởng");
                                                                        return true;
                                                                    }
                                                                } else if (var22.equals("stbx")) {
                                                                    if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a != Char.getMyChar().charID) {
                                                                        if (Char.getMyChar().nClass.classId != 6) {
                                                                            GameScr.chatPopup("Bạn không phải là quạt");
                                                                            return true;
                                                                        } else {
                                                                            GameScr.chatPopup("Stanima Buff");
                                                                            this.a(true, false);
                                                                            return true;
                                                                        }
                                                                    } else {
                                                                        GameScr.chatPopup("Chưa có nhóm hoặc bạn là nhóm trưởng");
                                                                        return true;
                                                                    }
                                                                } else if (var22.equals("sths")) {
                                                                    if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a != Char.getMyChar().charID) {
                                                                        if (Char.getMyChar().nClass.classId != 6) {
                                                                            GameScr.chatPopup("Bạn không phải là quạt");
                                                                            return true;
                                                                        } else {
                                                                            GameScr.chatPopup("Stanima HS");
                                                                            this.a(false, true);
                                                                            return true;
                                                                        }
                                                                    } else {
                                                                        GameScr.chatPopup("Chưa có nhóm hoặc bạn là nhóm trưởng");
                                                                        return true;
                                                                    }
                                                                } else if (var22.equals("pkb")) {
                                                                    GameScr.chatPopup("PK Thần Thú");
                                                                    setAuto((Auto) (new AutoPKBoss(TileMap.mapID)));
                                                                    if (g != null && Char.getMyChar().charName.equals(g) && GameScr.vParty.size() > 1) {
                                                                        Service.getInstance().k("pkm " + TileMap.mapID);
                                                                    }

                                                                    return true;
                                                                } else if (var22.equals("pkk")) {
                                                                    GameScr.chatPopup("PK Thần Thú");
                                                                    AutoPKBoss var32;
                                                                    (var32 = new AutoPKBoss(TileMap.mapID)).zoneID = value;
                                                                    setAuto((Auto) var32);
                                                                    if (g != null && Char.getMyChar().charName.equals(g) && GameScr.vParty.size() > 1) {
                                                                        Service.getInstance().k("pkm " + TileMap.mapID);
                                                                        Service.getInstance().k("pkk " + value);
                                                                    }

                                                                    return true;
                                                                } else if (var22.equals("lb")) {
                                                                    var30 = "";

                                                                    for (var8 = 0; var8 < GameScr.vMobAttack.size(); ++var8) {
                                                                        Mob var23;
                                                                        if ((var23 = (Mob) GameScr.vMobAttack.elementAt(var8)).isBoss) {
                                                                            var30 = var30 + var23.getMobTemplate().name + " lv: " + var23.lv + ", ";
                                                                        }
                                                                    }

                                                                    GameScr.chatPopup("Mob: " + var30);
                                                                    return true;
                                                                } else if (var22.equals("tb")) {
                                                                    (new Thread(new AutoTimBoss(this))).start();
                                                                    return true;
                                                                } else if (var22.equals("sell")) {
                                                                    GameScr.chatPopup("Auto Sell");
                                                                    this.q();
                                                                    return true;
                                                                } else if (var22.equals("h")) {
                                                                    Calendar var31 = Res.getCurrentTime();
                                                                    GameScr.chatPopup("Time " + var31.get(11) + ":" + var31.get(12) + ":" + var31.get(13));
                                                                    return true;
                                                                } else if (var22.equals("dt")) {
                                                                    setAuto((Auto) (new AutoDanTre()));
                                                                    return true;
                                                                } else if (var22.equals("dh")) {
                                                                    setAuto((Auto) (new AutoDanhHeo()));
                                                                    return true;
                                                                } else if (var22.equals("nv")) {
                                                                    setAuto((Auto) (new TraNhiemVu()));
                                                                    return true;
                                                                } else if (var22.equals("ld")) {
                                                                    (new Thread(new DeleteItem(this))).start();
                                                                    return true;
                                                                } else if (var22.equals("f")) {
                                                                    GameScr.getInstance().openUI(value);
                                                                    return true;
                                                                } else if (var1.equals("hd9x")) {
                                                                    GameScr.chatPopup("Hang động 9x");
                                                                    setAuto((Auto) (new AutoHD9x()));
                                                                    if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID) {
                                                                        Service.getInstance().k("hd9x");
                                                                    }

                                                                    return true;
                                                                } else {
                                                                    if (var1.length() == 4) {
                                                                        if (var1.equals("as10")) {
                                                                            setAuto((Auto) (new As10()));
                                                                            return true;
                                                                        }

                                                                        if (var1.equals("as20")) {
                                                                            setAuto((Auto) (new As50(0)));
                                                                            return true;
                                                                        }

                                                                        if (var1.equals("a20k")) {
                                                                            setAuto((Auto) (new As50(1)));
                                                                            return true;
                                                                        }

                                                                        if (var1.equals("a20t")) {
                                                                            setAuto((Auto) (new As50(2)));
                                                                            return true;
                                                                        }

                                                                        if (var1.equals("a20u")) {
                                                                            setAuto((Auto) (new As50(3)));
                                                                            return true;
                                                                        }

                                                                        if (var1.equals("a20c")) {
                                                                            setAuto((Auto) (new As50(4)));
                                                                            return true;
                                                                        }

                                                                        if (var1.equals("a20d")) {
                                                                            setAuto((Auto) (new As50(5)));
                                                                            return true;
                                                                        }

                                                                        if (var1.equals("a20q")) {
                                                                            setAuto((Auto) (new As50(6)));
                                                                            return true;
                                                                        }
                                                                    } else {
                                                                        if (var22.equals("boss")) {
                                                                            GameScr.chatPopup("Auto Boss " + value);
                                                                            setAuto((Auto) (new AutoBoss(value)));
                                                                            return true;
                                                                        }

                                                                        if (var22.equals("kpk")) {
                                                                            GameScr.chatPopup("Khu PK " + value);
                                                                            Auto.u = value;
                                                                            return true;
                                                                        }

                                                                        if (var22.equals("cpk")) {
                                                                            GameScr.chatPopup("Xóa ds PK");
                                                                            SavePK.b();
                                                                            return true;
                                                                        }

                                                                        String[] var29;
                                                                        if (var1.startsWith("apk")) {
                                                                            if ((var29 = splitString(var1, " ")).length > 1) {
                                                                                GameScr.chatPopup("Thêm " + var29[1] + " vào ds PK");
                                                                                SavePK.a(var29[1]);
                                                                            } else if (Char.getMyChar().charFocus != null) {
                                                                                GameScr.chatPopup("Thêm " + Char.getMyChar().charFocus.charName + " vào ds PK");
                                                                                SavePK.a(Char.getMyChar().charFocus.charName);
                                                                            }

                                                                            return true;
                                                                        }

                                                                        if (var1.startsWith("dpk")) {
                                                                            if ((var29 = splitString(var1, " ")).length > 1) {
                                                                                GameScr.chatPopup("Xóa " + var29[1] + " khỏi ds PK");
                                                                                SavePK.b(var29[1]);
                                                                            } else if (Char.getMyChar().charFocus != null) {
                                                                                GameScr.chatPopup("Xóa " + Char.getMyChar().charFocus.charName + " khỏi ds PK");
                                                                                SavePK.b(Char.getMyChar().charFocus.charName);
                                                                            }

                                                                            return true;
                                                                        }

                                                                        if (var22.equals("chs")) {
                                                                            GameScr.chatPopup("Xóa ds HS");
                                                                            r();
                                                                            return true;
                                                                        }

                                                                        if (var1.startsWith("ahs")) {
                                                                            if ((var29 = splitString(var1, " ")).length > 1) {
                                                                                GameScr.chatPopup("Thêm " + var29[1] + " vào ds HS");
                                                                                i(var29[1]);
                                                                            } else if (Char.getMyChar().charFocus != null) {
                                                                                GameScr.chatPopup("Thêm " + Char.getMyChar().charFocus.charName + " vào ds HS");
                                                                                i(Char.getMyChar().charFocus.charName);
                                                                            }

                                                                            return true;
                                                                        }

                                                                        if (var22.equals("dhs")) {
                                                                            if ((var29 = splitString(var1, " ")).length > 1) {
                                                                                GameScr.chatPopup("Xóa " + var29[1] + " khỏi ds HS");
                                                                                j(var29[1]);
                                                                            } else if (Char.getMyChar().charFocus != null) {
                                                                                GameScr.chatPopup("Xóa " + Char.getMyChar().charFocus.charName + " khỏi ds PK");
                                                                                j(Char.getMyChar().charFocus.charName);
                                                                            }

                                                                            return true;
                                                                        }

                                                                        if (var1.startsWith("a20s")) {
                                                                            if ((var29 = splitString(var1, " ")).length > 1) {
                                                                                setAuto((Auto) (new As20(6, var29[1])));
                                                                            }

                                                                            return true;
                                                                        }

                                                                        ItemTemplate var21;
                                                                        if (var1.startsWith("dg")) {
                                                                            if ((var8 = (var30 = var1.substring(3)).indexOf(32)) > 0) {
                                                                                try {
                                                                                    SettingAutoMuaBan.a(var21 = ItemTemplateManager.get(Short.parseShort(var30.substring(0, var8))), var30.substring(var8 + 1, var30.length()));
                                                                                    GameScr.chatPopup("Đặt giá: " + var21.name);
                                                                                } catch (Exception var23) {
                                                                                    var23.printStackTrace();
                                                                                }
                                                                            }

                                                                            return true;
                                                                        }

                                                                        int var20;
                                                                        if (var1.startsWith("asw")) {
                                                                            var29 = splitString(var1, " ");

                                                                            try {
                                                                                var8 = Integer.parseInt(var29[1]);
                                                                                var20 = Integer.parseInt(var29[2]);
                                                                                ItemTemplate var26 = ItemTemplateManager.get((short) var8);
                                                                                GameScr.chatPopup("Thêm: " + var26.name + " giá: " + var20 + " vào ds bán Shinwa");
                                                                                b(var8, var20);
                                                                            } catch (Exception var25) {
                                                                                var25.printStackTrace();
                                                                            }

                                                                            return true;
                                                                        }

                                                                        if (var1.startsWith("rsw")) {
                                                                            var29 = splitString(var1, " ");

                                                                            try {
                                                                                var21 = ItemTemplateManager.get((short) (var8 = Integer.parseInt(var29[1])));
                                                                                if (d(var8)) {
                                                                                    var24 = e(var8);
                                                                                    GameScr.chatPopup("Xóa: " + var21.name + " giá: " + var24 + " khỏi ds bán Shinwa");
                                                                                    fsw(var8);
                                                                                } else {
                                                                                    GameScr.chatPopup("Item " + var21.name + " chưa có trong ds bán Shinwa");
                                                                                }
                                                                            } catch (Exception var25) {
                                                                                var25.printStackTrace();
                                                                            }

                                                                            return true;
                                                                        }
                                                                    }

                                                                    return false;
                                                                }
                                                            } else if (GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID) {
                                                                var10000 = value >= 0 && value < Mob.mobTemplates.length ? Mob.mobTemplates[value] : null;
                                                                if (var10000 == null) {
                                                                    GameScr.chatPopup("Stanima nhóm all");
                                                                    this.c(-1, TileMap.mapID);
                                                                } else {
                                                                    GameScr.chatPopup("Stanima nhóm " + var10000.name + " id " + value);
                                                                    this.c(value, TileMap.mapID);
                                                                }

                                                                c.g = true;
                                                                Service.getInstance().k("st " + c.mapID + " " + c.zoneID + " " + c.b);
                                                                return true;
                                                            } else {
                                                                GameScr.chatPopup("Chưa có nhóm hoặc bạn không là nhóm trưởng");
                                                                return true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        GameScr.chatPopup("End Auto");
                                        tatAuto();
                                        if (Char.tickDanhTheoNhom) {
                                            Service.getInstance().k("pe");
                                        }

                                        return true;
                                    }
                                }
                            }
                        }
                    } else {
                        GameScr.chatPopup("Phá băng");
                        isBangMob = false;
                        isBangSkill = false;
                        return true;
                    }
                } else {
                    GameScr.chatPopup("Băng skill");
                    isBangSkill = true;
                    return true;
                }
            } else {
                GameScr.chatPopup("Băng boss");
                isBangMob = true;
                return true;
            }
        } else {
            GameScr.chatPopup("Đóng băng");
            isBangMob = true;
            isBangSkill = true;
            return true;
        }
    }

    public static void g(String var0) {
        for (int var1 = 0; var1 < bb.length; ++var1) {
            a(var0, bb[var1].trim());
        }

    }

    public static void a(String var0, String var1) {
        ChatManager.getInstance().chat(var0, Char.getMyChar().charName, var1);
        Service.getInstance().a(var0, var1);
        NinjaUtil.sleep(20L);
    }

    public static String h(String var0) {
        InputStream var3 = RMS.a("/" + var0);

        try {
            byte[] var1 = new byte[var3.available()];
            var3.read(var1);
            var0 = new String(var1, "UTF-8");
        } catch (Exception var4) {
            var0 = "";
        }

        return var0;
    }

    public final void collectMsg(String nameChar, String msg) {
        if (AutoHD9xManager.onPrivateMessage(nameChar, msg)) {
            return;
        }

        if (Char.tickDanhTheoNhom && g != null && nameChar.equals(g) && !Char.getMyChar().charName.equals(g)) {
            this.d(nameChar, msg);
        }

        ChatTab var3;
        boolean var10000;
        if ((var3 = ChatManager.getInstance().a(nameChar)) == null) {
            var10000 = true;
        } else if (System.currentTimeMillis() - var3.d > 1000L) {
            var3.d = System.currentTimeMillis();
            var10000 = true;
        } else {
            var10000 = false;
        }

        if (var10000) {
            if (handleClanInviteRequest(nameChar, msg)) {
                return;
            }

            Char var15 = Char.getMyChar();

            int var4;
            String[] var5String = splitString(SettingNVDV.nameCharLoiDai, ",");
            for (var4 = 0; var4 < var5String.length; ++var4) {
                if (nameChar.equals(var5String[var4])) {
                    if (msg.toLowerCase().equals("lodai")) {
                        tatAuto();
                        autoLoseLoiDai.aea();
                        setAuto((Auto) autoLoseLoiDai);
                    }

                    if (msg.toLowerCase().equals("cusat")) {
                        tatAuto();
                        autoBiCuuSat.a();
                        setAuto((Auto) autoBiCuuSat);
                    }
                }
            }
            for (var4 = 0; var4 < splitString(AutoReceiver.stringNameCharNhanDo, ",").length; ++var4) {
                if (nameChar.equals(splitString(AutoReceiver.stringNameCharNhanDo, ",")[var4]) && msg.startsWith("anxin") && splitString(AutoReceiver.stringNameCharNhanDo, ",")[var4] != null) {
                    AutoReceiver.addItemGather(Short.parseShort((splitString(msg, " ")[3])));
                    setAuto((Auto) (new AutoSend(Integer.parseInt(splitString(msg, " ")[1]), Integer.parseInt(splitString(msg, " ")[2]), splitString(AutoReceiver.stringNameCharNhanDo, ",")[var4])));
                    return;
                }
            }

            if (msg.toLowerCase().equals("yenxu")) {
                a(nameChar, "Yên: " + var15.yen + " Xu: " + var15.xu + " Lượng: " + var15.luong);
                if (auto != null) {
                    a(nameChar, AutoUp.getEarnedYenChatText(auto));
                    return;
                }
            } else if (msg.toLowerCase().equals("level")) {
                a(nameChar, AutoUp.getCurrentLevelText());
                if (auto != null) {
                    a(nameChar, AutoUp.getEarnedExpChatText(auto));
                    return;
                }
            } else if (auto != null && ac > 0L) {
                if (msg.toLowerCase().equals("time")) {
                    a(nameChar, "Thời gian còn lại: " + NinjaUtil.b((int) (ac / 1000L)));
                    return;
                }
            } else {
                if (auto instanceof AutoSell) {
                    f.a(nameChar, msg);
                    return;
                }

                if (msg.equals(SelectServerScr.pass)) {
                    LockGame.tatAuto();
                    auto = null;
                    Session_ME.getInstance().b();
                }
            }
        }

    }

    private static boolean handleClanInviteRequest(String nameChar, String msg) {
        try {
            if (nameChar == null || msg == null || !msg.trim().equalsIgnoreCase("xingt")) {
                return false;
            }

            Char me = Char.getMyChar();
            if (me == null) {
                return true;
            }

            if (me.ctypeClan != 4 && me.ctypeClan != 3) {
                a(nameChar, "Chỉ tộc trưởng/tổ phó mới được mời vào gia tộc.");
                return true;
            }

            Char target = findCharInMapByName(nameChar);
            if (target == null) {
                a(nameChar, "Hãy đứng cùng map/khu để mình mời vào gia tộc.");
                return true;
            }

            if (target.cClanName != null && !target.cClanName.equals("")) {
                a(nameChar, "Bạn đang có gia tộc rồi.");
                return true;
            }

            long now = System.currentTimeMillis();
            if (nameChar.equals(lastClanInviteName) && now - lastClanInviteAt < 5000L) {
                return true;
            }

            lastClanInviteName = nameChar;
            lastClanInviteAt = now;
            Service.getInstance().clanInvite(target.charID);
            GameScr.chatPopup("Mời gia tộc: " + nameChar);
            a(nameChar, "Đã gửi lời mời gia tộc.");
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    private static Char findCharInMapByName(String name) {
        try {
            Char c = GameScr.getCharByName(name);
            if (c != null) {
                return c;
            }

            for (int i = 0; i < GameScr.vCharInMap.size(); i++) {
                c = (Char) GameScr.vCharInMap.elementAt(i);
                if (c != null && c.charName != null && c.charName.equalsIgnoreCase(name)) {
                    return c;
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

    public static String[] splitString(String var0, String var1) {
        int var2 = 0;
        int var3 = var1.length();

        int var4;
        for (var4 = var0.indexOf(var1, 0); var4 != -1; ++var2) {
            var4 += var3;
            var4 = var0.indexOf(var1, var4);
        }

        String[] var7 = new String[var2 + 1];
        var4 = var0.indexOf(var1);
        int var5 = 0;

        int var6;
        for (var6 = 0; var4 != -1; ++var6) {
            var7[var6] = var0.substring(var5, var4);
            var5 = var4 + var3;
            var4 = var0.indexOf(var1, var5);
        }

        var7[var6] = var0.substring(var5, var0.length());
        return var7;
    }

    public final void d(String var1, String var2) {
        if (AutoHD9xManager.onPrivateMessage(var1, var2)) {
            return;
        }

        if (Char.tickDanhTheoNhom && g != null && var1.equals(g) && !Char.getMyChar().charName.equals(g)) {
            String[] var6 = splitString(var2, " ");

            try {
                if (var6[0].equals("dcvt")) {
                    attackChangePosition = Integer.parseInt(var6[1]) == 1;
                    return;
                }

                if (var6[0].equals("avt")) {
                    GameScr.chatPopup("Thêm vị trí " + s.size());
                    s.addElement(Integer.valueOf(var6[1]));
                    t.addElement(Integer.valueOf(var6[2]));
                    return;
                }

                if (var6[0].equals("dvt")) {
                    GameScr.chatPopup("Xóa hết vị trí");
                    s.removeAllElements();
                    t.removeAllElements();
                    return;
                }

                int var9;
                if (var6[0].equals("dvtx")) {
                    var9 = Integer.parseInt(var6[1]);
                    GameScr.chatPopup("Xóa vị trí " + var9);
                    s.removeElementAt(var9);
                    t.removeElementAt(var9);
                    return;
                }

                if (var6[0].equals("pe")) {
                    GameScr.chatPopup("End Auto");
                    LockGame.tatAuto();
                    auto = null;
                    return;
                }

                if (var6[0].equals("tsa")) {
                    if (auto == au) {
                        au.mapID = Integer.parseInt(var6[1]);
                        au.zoneID = Integer.parseInt(var6[2]);
                        return;
                    }

                    autoTanSat.init(-1, Integer.parseInt(var6[1]), Integer.parseInt(var6[2]));
                    autoTanSat.g = true;
                    setAuto((Auto) autoTanSat);
                    return;
                }

                if (var6[0].equals("ts")) {
                    if (auto == au) {
                        au.mapID = Integer.parseInt(var6[1]);
                        au.zoneID = Integer.parseInt(var6[2]);
                        return;
                    }

                    autoTanSat.init(Integer.parseInt(var6[3]), Integer.parseInt(var6[1]), Integer.parseInt(var6[2]));
                    autoTanSat.g = true;
                    setAuto((Auto) autoTanSat);
                    return;
                }

                if (var6[0].equals("att")) {
                    if (auto == au) {
                        au.mapID = Integer.parseInt(var6[1]);
                        au.zoneID = Integer.parseInt(var6[2]);
                        return;
                    }

                    var9 = Integer.parseInt(var6[1]);
                    int var3 = Integer.parseInt(var6[3]);
                    TaskOrder var4;
                    if ((var4 = Char.getTaskOrderById(1)) != null && var4.mapId == var9) {
                        e.a();
                    } else {
                        e.a(var9, var3);
                    }

                    e.zoneID = Integer.parseInt(var6[2]);
                    e.g = true;
                    setAuto((Auto) e);
                    return;
                }

                if (var6[0].equals("sta")) {
                    if (auto == au) {
                        au.mapID = Integer.parseInt(var6[1]);
                        au.zoneID = Integer.parseInt(var6[2]);
                        return;
                    }

                    c.a(-1, Integer.parseInt(var6[1]), Integer.parseInt(var6[2]), false, false);
                    c.g = true;
                    setAuto((Auto) c);
                    return;
                }

                if (var6[0].equals("st")) {
                    if (auto == au) {
                        au.mapID = Integer.parseInt(var6[1]);
                        au.zoneID = Integer.parseInt(var6[2]);
                        return;
                    }

                    c.a(Integer.parseInt(var6[3]), Integer.parseInt(var6[1]), Integer.parseInt(var6[2]), false, false);
                    c.g = true;
                    setAuto((Auto) c);
                    return;
                }

                if (var6[0].equals("EEEEE") || var6[0].equals("hd9x")) {
                    setAuto((Auto) (new AutoHD9x()));
                    return;
                }

                if (var6[0].equals("pkms")) {
                    if (auto instanceof AutoPKBossS) {
                        AutoPKBossS var8;
                        (var8 = (AutoPKBossS) auto).mapID = Integer.parseInt(var6[1]);
                        var8.b = Integer.parseInt(var6[2]);
                        var8.a = 3;
                        return;
                    }
                } else if (var6[0].equals("pkes")) {
                    if (auto instanceof AutoPKBossS) {
                        ((AutoPKBossS) auto).a = 4;
                        return;
                    }
                } else {
                    if (var6[0].equals("pkm")) {
                        if (auto == au) {
                            au.mapID = Integer.parseInt(var6[1]);
                            return;
                        }

                        Auto var7 = auto instanceof AutoPKBoss ? auto.instance : auto;
                        setAuto((Auto) (new AutoPKBoss(Integer.parseInt(var6[1]))));
                        auto.instance = var7;
                        return;
                    }

                    if (var6[0].equals("pkk")) {
                        if (auto instanceof AutoPKBoss || auto == au) {
                            auto.zoneID = Integer.parseInt(var6[1]);
                            return;
                        }
                    } else if (var6[0].equals("pke")) {
                        if (auto instanceof AutoPKBoss) {
                            backToInstance();
                            return;
                        }
                    } else if (auto != null) {
                        if (var6[0].equals("map")) {
                            auto.mapID = Integer.parseInt(var6[1]);
                            return;
                        }

                        if (var6[0].equals("khu")) {
                            auto.zoneID = Integer.parseInt(var6[1]);
                            return;
                        }

                        if (auto instanceof AutoTaThu) {
                            if (var6[0].equals("waitGr")) {
                                AutoTaThu.c = System.currentTimeMillis();
                                AutoTaThu.b = true;
                                return;
                            }

                            if (var6[0].equals("notifyGr")) {
                                AutoTaThu.b = false;
                                return;
                            }
                        } else if (auto instanceof Stanima && var6[0].equals("sts")) {
                            c.e();
                            return;
                        }
                    }
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

    }

    public static boolean containItemThrow(short var0) {
        for (int var1 = 0; var1 < throwListID.length; ++var1) {
            if (throwListID[var1] == var0) {
                return true;
            }
        }
        return false;
    }

    public static void removeItemThrow(short var0) {
        for (int var1 = 0; var1 < throwListID.length; ++var1) {
            if (throwListID[var1] == var0) {
                throwListID[var1] = -1;
            }
        }
    }

    public static void addItemThrow(short var0) {
        int var1;
        for (var1 = 0; var1 < throwListID.length; ++var1) {
            if (throwListID[var1] == var0) {
                return;
            }
        }

        var1 = -1;

        for (int var2 = 0; var2 < throwListID.length; ++var2) {
            if (throwListID[var2] < 0) {
                var1 = var2;
                break;
            }
        }

        if (var1 == -1) {
            var1 = throwListID.length;
            short[] var4 = throwListID;
            short[] var5 = new short[throwListID.length + 10];
            System.arraycopy(var4, 0, var5, 0, var4.length);

            for (int var3 = var4.length; var3 < var5.length; ++var3) {
                var5[var3] = -1;
            }

            throwListID = var5;
        }

        throwListID[var1] = var0;
    }

    public static void o() {
        for (int var0 = 0; var0 < throwListID.length; ++var0) {
            if (throwListID[var0] > 0) {
                for (int var1 = 0; var1 <= var0; ++var1) {
                    if (throwListID[var1] == -1) {
                        throwListID[var1] = throwListID[var0];
                        throwListID[var0] = -1;
                        break;
                    }
                }
            }
        }

    }

    public static boolean isThrowItem(Item var0) {
        if (var0 == null) {
            return false;
        }
        if (AutoLuyenNgoc.isProtectedHuntGem(var0)) {
            var0.v = true;
            return false;
        }
        if (System.currentTimeMillis() - var0.timeThrow >= 5000L) {
            var0.timeThrow = System.currentTimeMillis();
            return containItemThrow(var0.template.id) && !var0.isLock;
        }
        return false;
    }

    public static void apa() {
        if (System.currentTimeMillis() - daa > 60000L) {
            daa = System.currentTimeMillis();
            MyVector var0;
            (var0 = new MyVector()).addElement(Char.getMyChar());
            Service.getInstance().a((MyVector) (new MyVector()), (MyVector) var0, (int) 2);
        }

    }

    public static void loadListPick() {
        try {
            ByteArrayInputStream var0 = new ByteArrayInputStream(RMS.getRecord("PickListSetting"));
            DataInputStream var1 = new DataInputStream(var0);
            int lentPick = var1.readInt();
            System.out.println("lentPick Load: " + lentPick);
            if (Code.pickUpListID.length < lentPick) {
                Code.pickUpListID = new short[10 * (lentPick / 10 + 1)];
            }
            for (int index = 0; index < lentPick; index++) {
                Code.pickUpListID[index] = var1.readShort();
            }

            var0.close();
            var1.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static void loadListThrow() {
        try {
            ByteArrayInputStream var0 = new ByteArrayInputStream(RMS.getRecord("ThrowListSetting"));
            DataInputStream var1 = new DataInputStream(var0);

            int lentThrow = var1.readInt();
            System.out.println("lentThrow Load: " + lentThrow);
            if (Code.throwListID.length < lentThrow) {
                Code.throwListID = new short[10 * (lentThrow / 10 + 1)];
            }

            for (int index = 0; index < lentThrow; index++) {
                Code.throwListID[index] = var1.readShort();
            }
            var0.close();
            var1.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static void loadListDel() {
        try {
            ByteArrayInputStream var0 = new ByteArrayInputStream(RMS.getRecord("DelListSetting"));
            DataInputStream var1 = new DataInputStream(var0);
            int lentDel = var1.readInt();
            System.out.println("lentDel Load: " + lentDel);
            if (Code.delListID.length < lentDel) {
                Code.delListID = new short[10 * (lentDel / 10 + 1)];
            }
            for (int index = 0; index < lentDel; index++) {
                Code.delListID[index] = var1.readShort();
            }
            var0.close();
            var1.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static void saveListPick() {
        System.out.println("saveListPick");
        ByteArrayOutputStream var0 = new ByteArrayOutputStream();
        DataOutputStream var1 = new DataOutputStream(var0);

        try {
            int varPick1 = 0;
            int varPick2;
            for (varPick2 = 0; varPick2 < Code.pickUpListID.length; varPick2++) {
                if (Code.pickUpListID[varPick2] >= 0) {
                    ++varPick1;
                }
            }
            System.out.println("lentPick Save: " + varPick1);
            var1.writeInt(varPick1);
            for (varPick2 = 0; varPick2 < Code.pickUpListID.length; varPick2++) {
                if (Code.pickUpListID[varPick2] >= 0) {
                    var1.writeShort(Code.pickUpListID[varPick2]);
                }
            }
            var1.flush();
            var0.flush();
            RMS.writeRecord("PickListSetting", var0.toByteArray());
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static void saveListDel() {
        System.out.println("saveListDel");
        ByteArrayOutputStream var0 = new ByteArrayOutputStream();
        DataOutputStream var1 = new DataOutputStream(var0);

        try {
            int varDel1 = 0;
            int varDel2;
            for (varDel2 = 0; varDel2 < Code.delListID.length; ++varDel2) {
                if (Code.delListID[varDel2] >= 0) {
                    ++varDel1;
                }
            }
            System.out.println("lentDel Save: " + varDel1);
            var1.writeInt(varDel1);
            for (varDel2 = 0; varDel2 < Code.delListID.length; ++varDel2) {
                if (Code.delListID[varDel2] >= 0) {
                    var1.writeShort(Code.delListID[varDel2]);
                }
            }
            var1.flush();
            var0.flush();
            RMS.writeRecord("DelListSetting", var0.toByteArray());
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static void saveListThrow() {
        System.out.println("saveListThrow");
        ByteArrayOutputStream var0 = new ByteArrayOutputStream();
        DataOutputStream var1 = new DataOutputStream(var0);

        try {

            int varThrow1 = 0;
            int varThrow2;
            for (varThrow2 = 0; varThrow2 < Code.throwListID.length; varThrow2++) {
                if (Code.throwListID[varThrow2] >= 0) {
                    ++varThrow1;
                }
            }
            System.out.println("lentThrow Save: " + varThrow1);
            var1.writeInt(varThrow1);
            for (varThrow2 = 0; varThrow2 < Code.throwListID.length; varThrow2++) {
                if (Code.throwListID[varThrow2] >= 0) {
                    var1.writeShort(Code.throwListID[varThrow2]);
                }
            }
            var1.flush();
            var0.flush();
            RMS.writeRecord("ThrowListSetting", var0.toByteArray());
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    static {
        try {
            ByteArrayInputStream var0 = new ByteArrayInputStream(RMS.getRecord("V6Group"));
            DataInputStream var1;
            if ((g = (var1 = new DataInputStream(var0)).readUTF()).equals("")) {
                g = null;
            }

            int var2 = var1.readByte();

            int var3;
            for (var3 = 0; var3 < var2; ++var3) {
                h.addElement(var1.readUTF());
            }

            var2 = var1.readInt();

            for (var3 = 0; var3 < var2; ++var3) {
                i.addElement(var1.readUTF());
            }

            var1.close();
            var0.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        av = 0L;
        aw = 0L;
        pickUpListID = new short[120];
        delListID = new short[120];
        throwListID = new short[120];
        khoangCachNhat = -1;
        m = -1;
        n = -1;
        o = -1;
        isHutVP = false;
        attackChangePosition = false;
        s = new MyVector();
        t = new MyVector();
        attackChangeZone = false;
        w = new int[0];
        keepLevel = false;
        tbNhanVP = true;
        tbNhanExp = false;
        speedGame = 13;
        thoiGianChoChuyenKhu = 0;
        speedTinhLuyen = 5;
        ax = 0L;
        tuDung = new MyVector();
        dapdo = new MyVector();
        listTLItem = new MyVector();
        ba = 0L;
        z = new MyVector();
        aa = new MyVector();

        int var6;
        for (var6 = 0; var6 < pickUpListID.length; ++var6) {
            pickUpListID[var6] = -1;
        }

        for (var6 = 0; var6 < delListID.length; ++var6) {
            delListID[var6] = -1;
        }

        for (var6 = 0; var6 < throwListID.length; ++var6) {
            throwListID[var6] = -1;
        }

        ab = 0L;
        ac = 0L;
        isBangMob = true;
        isBangSkill = true;
        af = false;
        ag = 5;
        isFakeSkillNgang = false;
        nSkillFake = 100;
        isFakeSkillCao = false;
        cSkillFake = 100;
        al = false;
        am = 100;

        an = false;

        try {
            bb = splitString(h("text.txt"), "\n");
        } catch (Exception var4) {
            var4.printStackTrace();
            bb = new String[0];
        }
        loadListDel();
        loadListPick();
        loadListThrow();
        AutoReceiver.load();
        Char.loadAuto();
    }
}
