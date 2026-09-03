public final class AutoUpLevel extends Auto {

    private static final int ITEM_PHAN_THAN_LENH = 545;
    private static final int ITEM_THI_LUYEN_THIEP = 564;
    private static final int SHOP_PHAN_THAN_LENH = 14;
    private static final int SHOP_THI_LUYEN_THIEP = 8;
    private static final int NPC_TAJIMA = 12;
    private static final int EFFECT_VDMQ = 34;
    private static final long ACTION_DELAY = 2500L;
    private static final long BUY_RETRY_DELAY = 60000L;
    private static final int VDMQ_EFFECT_MIN_SECONDS = 300;

    private static final int[][] NORMAL_LEVEL_MAPS = new int[][]{
        new int[]{21, 3, 5, 6},
        new int[]{23, 3, 5, 8},
        new int[]{69, 3, 5, 6},
        new int[]{2, 5, 6, 8},
        new int[]{6, 5, 6, 8},
        new int[]{25, 5, 6, 8},
        new int[]{70, 5, 6, 8},
        new int[]{20, 6, 8},
        new int[]{26, 6, 8},
        new int[]{71, 6, 8},
        new int[]{3, 10},
        new int[]{28, 10},
        new int[]{39, 10, 11, 12},
        new int[]{60, 10, 11},
        new int[]{4, 11, 12},
        new int[]{46, 12},
        new int[]{5, 13, 14},
        new int[]{29, 13, 14},
        new int[]{40, 15, 17},
        new int[]{7, 16, 17, 19},
        new int[]{30, 16, 18},
        new int[]{65, 17, 19},
        new int[]{31, 18, 19, 20},
        new int[]{9, 19, 20, 21, 22},
        new int[]{8, 20, 21},
        new int[]{63, 23, 28},
        new int[]{47, 24, 25},
        new int[]{11, 25, 27},
        new int[]{33, 25, 26, 27},
        new int[]{61, 25, 26},
        new int[]{50, 26, 27},
        new int[]{12, 28, 30},
        new int[]{49, 28, 29},
        new int[]{51, 30, 32},
        new int[]{74, 30},
        new int[]{34, 31, 32},
        new int[]{57, 31, 32},
        new int[]{35, 32, 33},
        new int[]{13, 34, 37},
        new int[]{66, 34, 36},
        new int[]{52, 35, 38},
        new int[]{78, 35, 36, 37},
        new int[]{64, 39, 40},
        new int[]{14, 41, 42},
        new int[]{15, 43, 44},
        new int[]{67, 45, 46},
        new int[]{16, 47, 50},
        new int[]{68, 48, 49},
        new int[]{41, 51, 52},
        new int[]{42, 53, 55},
        new int[]{62, 56, 57},
        new int[]{44, 58, 59},
        new int[]{18, 60, 61},
        new int[]{24, 62, 65},
        new int[]{59, 63, 64},
        new int[]{45, 66, 67},
        new int[]{53, 66, 67, 68},
        new int[]{19, 71},
        new int[]{36, 74, 77},
        new int[]{54, 80},
        new int[]{37, 84, 88},
        new int[]{55, 92},
        new int[]{58, 96, 100}
    };

    private static final int[][] VDMQ_LEVEL_MAPS = new int[][]{
        new int[]{139, 64, 68, 85},
        new int[]{140, 74, 77},
        new int[]{141, 83, 85, 88},
        new int[]{142, 85, 92, 96},
        new int[]{143, 103, 107},
        new int[]{144, 114, 117},
        new int[]{145, 123, 126},
        new int[]{146, 131, 137},
        new int[]{147, 142, 148},
        new int[]{148, 153, 159}
    };

    private int targetLevel;
    private int lastLevel;
    private long noMobSince;
    private long lastZoneTry;
    private long badMapUntil;
    private long lastPopup;
    private int zoneTryCount;
    private int badMap = -1;
    private boolean usePhanThan;
    private boolean fullMode;
    private boolean oldUsePhanThan;
    private boolean savedUsePhanThan;
    private boolean needBuyThiLuyenThiep;
    private long lastCloneAction;
    private long lastBuyPhanThanLenh;
    private long lastBuyThiLuyenThiep;
    private long lastUseThiLuyenThiep;
    private long lastSkillUp;
    private long lastUsePhanThanLenh;
    private int switchToCloneTry;
    private int switchToHumanTry;

    public AutoUpLevel(int targetLevel) {
        this(targetLevel, false);
    }

    public AutoUpLevel(int targetLevel, boolean usePhanThan) {
        this.init(targetLevel, usePhanThan, false);
    }

    public AutoUpLevel(int targetLevel, boolean usePhanThan, boolean fullMode) {
        this.init(targetLevel, usePhanThan, fullMode);
    }

    public final void init(int targetLevel) {
        this.init(targetLevel, false);
    }

    public final void init(int targetLevel, boolean usePhanThan) {
        this.init(targetLevel, usePhanThan, false);
    }

    public final void init(int targetLevel, boolean usePhanThan, boolean fullMode) {
        super.a();
        this.targetLevel = targetLevel;
        this.usePhanThan = usePhanThan;
        this.fullMode = fullMode;
        this.savedUsePhanThan = false;
        this.lastLevel = -1;
        super.mapID = -1;
        super.zoneID = -1;
        super.g = true;
        super.isHang = false;
        super.k = -1;
        super.l = -1;
        this.needBuyThiLuyenThiep = false;
        this.lastCloneAction = 0L;
        this.lastUsePhanThanLenh = 0L;
        this.switchToCloneTry = 0;
        this.switchToHumanTry = 0;
        this.resetMapState();
        AutoUpFullSupport.reset();
    }

    public static boolean start(int targetLevel) {
        return start(targetLevel, false);
    }

    public static boolean start(int targetLevel, boolean usePhanThan) {
        Char me = Char.getMyChar();
        if (me == null) {
            return true;
        }

        if (targetLevel <= 0) {
            GameScr.chatPopup(usePhanThan ? "Dung: uplvpt70" : "Dung: uplv70");
            return true;
        }

        if (usePhanThan) {
            String error = getPhanThanOwnerError(me);
            if (error != null) {
                GameScr.chatPopup(error);
                return true;
            }
        }

        if (!usePhanThan && targetLevel <= me.cLevel) {
            GameScr.chatPopup("Auto Up LV: da dat level " + me.cLevel);
            return true;
        }

        Code.setAuto(new AutoUpLevel(targetLevel, usePhanThan));
        GameScr.chatPopup((usePhanThan ? "Auto Up PT den " : "Auto Up LV den ") + targetLevel);
        return true;
    }

    public static boolean startFull(int targetLevel) {
        FormAutoUpFull.load();
        if (targetLevel <= 0) {
            targetLevel = FormAutoUpFull.TargetLevel;
        }

        Char me = Char.getMyChar();
        if (me == null) {
            return true;
        }

        if (targetLevel <= 0) {
            GameScr.chatPopup("Dung: uplvfull70");
            return true;
        }

        Code.setAuto(new AutoUpLevel(targetLevel, false, true));
        GameScr.chatPopup("Auto Up Tong den " + targetLevel + " (" + FormAutoUpFull.getSummary() + ")");
        return true;
    }

    public static boolean stop() {
        if (Code.auto instanceof AutoUpLevel) {
            ((AutoUpLevel) Code.auto).restorePhanThanSetting();
            Code.backToInstance();
            GameScr.chatPopup("Da dung Auto Up LV");
        } else {
            GameScr.chatPopup("Auto Up LV chua chay");
        }

        return true;
    }

    protected final void run() {
        Char me = Char.getMyChar();
        if (me == null) {
            return;
        }

        if (this.usePhanThan && this.preparePhanThanMode(me)) {
            return;
        }

        me = Char.getMyChar();
        if (me == null) {
            return;
        }

        if (!this.fullMode && me.cLevel >= this.targetLevel) {
            this.finish((this.usePhanThan ? "Auto Up PT: xong level " : "Auto Up LV: xong level ") + this.targetLevel);
            return;
        }

        if (super.isDead()) {
            Auto.autoRemap(true);
            return;
        }

        if (this.lastLevel != me.cLevel) {
            this.lastLevel = me.cLevel;
            this.resetMapState();
            me.mobFocus = null;
        }

        if (this.fullMode && AutoUpFullSupport.handle(this, me)) {
            this.markAutoProgress(AutoUpFullSupport.getStatusText());
            return;
        }

        if (this.fullMode && me.cLevel >= this.targetLevel && AutoUpFullSupport.shouldWaitNextCheck()) {
            this.markAutoProgress(AutoUpFullSupport.getStatusText());
            return;
        }

        if (me.cLevel >= this.targetLevel) {
            this.finish((this.fullMode ? "Auto Up Tong: xong level " : this.usePhanThan ? "Auto Up PT: xong level " : "Auto Up LV: xong level ") + this.targetLevel);
            return;
        }

        int targetMap = this.selectTargetMap(me);
        if (targetMap < 0) {
            this.popupSlow("Auto Up LV: chua co map phu hop");
            return;
        }

        if (this.usePhanThan && this.prepareVdmqForPhanThan(me, targetMap)) {
            return;
        }

        me = Char.getMyChar();
        if (me == null) {
            return;
        }

        super.mapID = targetMap;
        super.zoneID = -1;
        if (TileMap.mapID != targetMap) {
            this.goMap(targetMap, -1, -1, -1);
            return;
        }

        Mob mob = this.findBestMob(me);
        if (mob == null) {
            this.handleNoMob();
            this.pickUpItem(-1);
            return;
        }

        this.noMobSince = 0L;
        this.zoneTryCount = 0;
        if (me.mobFocus == null || !this.isValidMob(me.mobFocus, me)) {
            this.c(mob);
        }

        this.attack(mob.id, this.getAttackFlags());
        this.pickUpItem(-1);
        this.markAutoProgress((this.usePhanThan ? "Up PT " : "Up LV ") + me.cLevel + "/" + this.targetLevel);
    }

    public final String toString() {
        Char me = Char.getMyChar();
        int level = me == null ? 0 : me.cLevel;
        if (this.fullMode) {
            String text = AutoUpFullSupport.getStatusText();
            return "Auto Up Tong " + level + "/" + this.targetLevel + (text.length() > 0 ? " " + text : "") + " map " + super.mapID + " khu " + TileMap.zoneID;
        }
        return (this.usePhanThan ? "Auto Up PT " : "Auto Up LV ") + level + "/" + this.targetLevel + " map " + super.mapID + " khu " + TileMap.zoneID;
    }

    private void finish(String text) {
        GameScr.chatPopup(text);
        if (this.fullMode) {
            AutoUpFullSupport.reset();
        }
        this.restorePhanThanSetting();
        if (Code.auto == this) {
            Code.backToInstance();
        }
    }

    private void resetMapState() {
        this.noMobSince = 0L;
        this.lastZoneTry = 0L;
        this.zoneTryCount = 0;
        this.badMap = -1;
        this.badMapUntil = 0L;
    }

    private int[][] getLevelMaps(Char me) {
        return me.cLevel >= 100 && this.targetLevel > 100 ? VDMQ_LEVEL_MAPS : NORMAL_LEVEL_MAPS;
    }

    private boolean preparePhanThanMode(Char me) {
        if (!this.savedUsePhanThan) {
            this.oldUsePhanThan = Char.dk;
            this.savedUsePhanThan = true;
        }
        Char.dk = true;

        if (me.isHuman) {
            String error = getPhanThanOwnerError(me);
            if (error != null) {
                this.finish(error);
                return true;
            }
        }

        if (!me.isHuman) {
            this.switchToCloneTry = 0;
            return false;
        }

        if (this.ensureCloneSummoned(me)) {
            return true;
        }

        if (this.upCloneSkillIfPossible(me)) {
            return true;
        }

        if (this.needBuyThiLuyenThiep) {
            if (Char.k(ITEM_THI_LUYEN_THIEP) <= 0) {
                long now = System.currentTimeMillis();
                if (now - this.lastBuyThiLuyenThiep > BUY_RETRY_DELAY) {
                    this.lastBuyThiLuyenThiep = now;
                    this.popupSlow("PT: mua thi luyen thiep");
                    AutoBuyShop.buyNow(ITEM_THI_LUYEN_THIEP, SHOP_THI_LUYEN_THIEP, 1);
                }
                return true;
            }

            this.needBuyThiLuyenThiep = false;
            return this.switchToClone();
        }

        return this.switchToClone();
    }

    private boolean prepareVdmqForPhanThan(Char me, int targetMap) {
        if (!TileMap.isVDMQ(targetMap)) {
            return false;
        }

        if (me.isHuman) {
            return this.switchToClone();
        }

        if (this.getEffectSecondsLeft(me, EFFECT_VDMQ) < VDMQ_EFFECT_MIN_SECONDS) {
            int index = Char.getIndexItemById(ITEM_THI_LUYEN_THIEP);
            if (index >= 0) {
                long now = System.currentTimeMillis();
                if (now - this.lastUseThiLuyenThiep > ACTION_DELAY) {
                    this.lastUseThiLuyenThiep = now;
                    Service.getInstance().useItem(index);
                    this.popupSlow("PT: dung thi luyen thiep");
                }
                return true;
            }

            this.needBuyThiLuyenThiep = true;
            this.switchToHuman();
            return true;
        }

        return false;
    }

    private boolean ensureCloneSummoned(Char me) {
        if (me.d != null) {
            return false;
        }

        this.switchToCloneTry = 0;
        Skill skill = this.findCloneSkill(me);
        if (skill == null) {
            this.finish("Auto Up PT: chua co skill phan than");
            return true;
        }

        long now = System.currentTimeMillis();
        boolean hasItem = Char.getIndexItemById(ITEM_PHAN_THAN_LENH) >= 0;
        boolean skillInFight = this.isCloneSkillInFightList(me, skill);

        if (!skill.isCooldown() && skillInFight && me.cMP < skill.manaUse) {
            me.e(17);
            Auto.sleep(500L);
            return true;
        }

        if (!skill.isCooldown() && skillInFight && now - this.lastCloneAction > ACTION_DELAY) {
            this.lastCloneAction = now;
            this.usePhanThanSkill(me, skill);
            this.popupSlow("PT: goi Kage Bunshin " + skill.template.id);
            if (this.waitCloneCreated(2500L)) {
                return true;
            }

            hasItem = Char.getIndexItemById(ITEM_PHAN_THAN_LENH) >= 0;
        }

        if (hasItem && now - this.lastUsePhanThanLenh > 3500L) {
            this.lastUsePhanThanLenh = now;
            this.popupSlow(skillInFight ? "PT: dung phan than lenh" : "PT: dung lenh goi PT");
            if (this.usePhanThanLenhFallback()) {
                return true;
            }
        }

        if (Char.getMyChar() != null && Char.getMyChar().d == null && !hasItem && now - this.lastBuyPhanThanLenh > BUY_RETRY_DELAY) {
            this.lastBuyPhanThanLenh = now;
            this.popupSlow("PT: mua phan than lenh");
            AutoBuyShop.buyNow(ITEM_PHAN_THAN_LENH, SHOP_PHAN_THAN_LENH, 1);
        }

        return true;
    }

    private void usePhanThanSkill(Char me, Skill skill) {
        try {
            if (me == null || skill == null || skill.template == null) {
                return;
            }

            Skill oldSkill = me.selectSkill;
            me.selectSkill = skill;
            me.gi = skill;
            Service.getInstance().selectSkill(skill.template.id);
            Auto.sleep(250L);
            Service.getInstance().r();
            LockGame.ab();
            if (oldSkill != null && oldSkill != skill && Char.getMyChar() != null) {
                Char.getMyChar().selectSkill = oldSkill;
                Char.getMyChar().gi = oldSkill;
            }
            me.m();
        } catch (Exception e) {
        }
    }

    private boolean usePhanThanLenhFallback() {
        try {
            int index = Char.getIndexItemById(ITEM_PHAN_THAN_LENH);
            if (index >= 0) {
                Service.getInstance().useItem(index);
                LockGame.ab();
                return this.waitCloneCreated(2500L);
            }
        } catch (Exception e) {
        }

        return false;
    }

    private boolean waitCloneCreated(long timeout) {
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end) {
            Char me = Char.getMyChar();
            if (me != null && me.d != null) {
                return true;
            }

            Auto.sleep(250L);
        }

        Char me = Char.getMyChar();
        return me != null && me.d != null;
    }

    private boolean isCloneSkillInFightList(Char me, Skill skill) {
        if (me == null || skill == null || skill.template == null || me.vSkillFight == null) {
            return false;
        }

        for (int i = 0; i < me.vSkillFight.size(); ++i) {
            Skill fightSkill = (Skill) me.vSkillFight.elementAt(i);
            if (fightSkill != null && fightSkill.template != null && fightSkill.template.id == skill.template.id) {
                return true;
            }
        }

        return false;
    }

    private boolean upCloneSkillIfPossible(Char me) {
        if (me == null || !me.isHuman || me.d == null || me.aj <= 0) {
            return false;
        }

        Skill skill = this.findCloneSkill(me);
        if (skill == null || skill.template == null || skill.template.skills == null) {
            return false;
        }

        int add = 0;
        int point = skill.point + 1;
        while (add < me.aj && point <= skill.template.maxPoint && point < skill.template.skills.length) {
            Skill next = skill.template.skills[point];
            if (next == null || next.level > me.cLevel || point * 10 > me.d.cLevel) {
                break;
            }
            ++add;
            ++point;
        }

        if (add <= 0) {
            return false;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastSkillUp <= ACTION_DELAY) {
            return true;
        }

        this.lastSkillUp = now;
        Service.getInstance().f(skill.template.id, add);
        this.popupSlow("PT: cong skill +" + add);
        Auto.sleep(700L);
        return true;
    }

    private boolean switchToClone() {
        Char me = Char.getMyChar();
        if (me == null) {
            return true;
        }

        if (!me.isHuman) {
            this.switchToCloneTry = 0;
            return false;
        }

        if (me.d == null) {
            this.switchToCloneTry = 0;
            return this.ensureCloneSummoned(me);
        }

        if (TileMap.mapID != 22) {
            this.goMap(22, -2, -1, -1);
            return true;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastCloneAction <= ACTION_DELAY) {
            return true;
        }

        this.lastCloneAction = now;
        ++this.switchToCloneTry;
        this.useTajimaSwitch(3, false);
        this.popupSlow("PT: chuyen thu than " + this.switchToCloneTry);
        if (this.waitPhanThanState(false, 3500L)) {
            this.switchToCloneTry = 0;
            return true;
        }

        me = Char.getMyChar();
        if (this.switchToCloneTry >= 3 && me != null && me.isHuman) {
            me.d = null;
            this.switchToCloneTry = 0;
            this.lastCloneAction = 0L;
            this.popupSlow("PT: goi lai Kage Bunshin");
        }

        return true;
    }

    private boolean switchToHuman() {
        Char me = Char.getMyChar();
        if (me == null) {
            return true;
        }

        if (me.isHuman) {
            this.switchToHumanTry = 0;
            return this.buyThiLuyenThiepIfNeeded();
        }

        if (TileMap.mapID != 22) {
            this.goMap(22, -2, -1, -1);
            return true;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastCloneAction <= ACTION_DELAY) {
            return true;
        }

        this.lastCloneAction = now;
        ++this.switchToHumanTry;
        this.useTajimaSwitch(4, true);
        if (this.waitPhanThanState(true, 3500L)) {
            this.switchToHumanTry = 0;
            return this.buyThiLuyenThiepIfNeeded();
        }

        if (this.switchToHumanTry >= 3) {
            this.switchToHumanTry = 0;
            this.lastCloneAction = 0L;
            this.popupSlow("PT: doi chu than lai");
        }

        return true;
    }

    private boolean buyThiLuyenThiepIfNeeded() {
        long now = System.currentTimeMillis();
        if (Char.k(ITEM_THI_LUYEN_THIEP) <= 0 && now - this.lastBuyThiLuyenThiep > BUY_RETRY_DELAY) {
            this.lastBuyThiLuyenThiep = now;
            this.popupSlow("PT: mua thi luyen thiep");
            AutoBuyShop.buyNow(ITEM_THI_LUYEN_THIEP, SHOP_THI_LUYEN_THIEP, 1);
        }

        return true;
    }

    private boolean waitPhanThanState(boolean human, long timeout) {
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end) {
            Char me = Char.getMyChar();
            if (me != null && me.isHuman == human) {
                return true;
            }

            Auto.sleep(250L);
        }

        Char me = Char.getMyChar();
        return me != null && me.isHuman == human;
    }

    private void useTajimaSwitch(int option, boolean human) {
        try {
            if (TileMap.mapID != 22) {
                this.goMap(22, -2, -1, -1);
                return;
            }

            Npc npc = GameScr.findNpc(NPC_TAJIMA);
            if (npc != null) {
                Char.charMove(npc.cx, npc.cy);
                Char.getMyChar().npcFocus = npc;
                Service.getInstance().openMenu(NPC_TAJIMA);
                Auto.sleep(800L);
            }

            Service.getInstance().menu(NPC_TAJIMA, option, 0);
            Auto.sleep(1200L);
            Char me = Char.getMyChar();
            if (me != null && me.isHuman != human) {
                Service.getInstance().menu(NPC_TAJIMA, option, 0);
                Auto.sleep(1000L);
            }
        } catch (Exception e) {
        }
    }

    private Skill findCloneSkill(Char me) {
        return findOpenedCloneSkill(me);
    }

    private static String getPhanThanOwnerError(Char me) {
        if (me == null) {
            return "Auto Up PT: chua load nhan vat";
        }

        if (!me.isHuman) {
            return "Auto Up PT: hay chuyen chu than de bat";
        }

        if (me.cLevel <= 90) {
            return "Auto Up PT: chu than phai tren lv90";
        }

        return findOpenedCloneSkill(me) == null ? "Auto Up PT: chua mo skill phan than" : null;
    }

    private static Skill findOpenedCloneSkill(Char me) {
        if (me == null || me.vSkillFight == null) {
            return null;
        }

        for (int i = 0; i < me.vSkillFight.size(); ++i) {
            Skill skill = (Skill) me.vSkillFight.elementAt(i);
            if (isOpenedCloneSkill(skill)) {
                return skill;
            }
        }

        if (me.vSkill != null) {
            for (int i = 0; i < me.vSkill.size(); ++i) {
                Skill skill = (Skill) me.vSkill.elementAt(i);
                if (isOpenedCloneSkill(skill)) {
                    return skill;
                }
            }
        }

        return null;
    }

    private static boolean isOpenedCloneSkill(Skill skill) {
        return isCloneSkill(skill) && (skill.template.maxPoint == 0 || skill.point > 0);
    }

    private static boolean isCloneSkill(Skill skill) {
        if (skill == null || skill.template == null) {
            return false;
        }

        return Auto.isPhanThanSkillId(skill.template.id);
    }

    private int getEffectSecondsLeft(Char me, int effectId) {
        if (me == null || me.vEff == null) {
            return 0;
        }

        int now = (int) (System.currentTimeMillis() / 1000L);
        int max = 0;
        for (int i = 0; i < me.vEff.size(); ++i) {
            Effect effect = (Effect) me.vEff.elementAt(i);
            if (effect != null && effect.e != null && effect.e.a == effectId) {
                int left = effect.c - (now - effect.b);
                if (left > max) {
                    max = left;
                }
            }
        }

        return max;
    }

    private void restorePhanThanSetting() {
        if (this.savedUsePhanThan) {
            Char.dk = this.oldUsePhanThan;
            this.savedUsePhanThan = false;
        }
    }

    private int getMinMobLevel(Char me) {
        int[][] maps = this.getLevelMaps(me);
        int max = me.cLevel + 8;
        if (this.hasMapInRange(maps, me.cLevel + 2, max)) {
            return me.cLevel + 2;
        }

        if (this.hasMapInRange(maps, me.cLevel + 1, max)) {
            return me.cLevel + 1;
        }

        return this.hasMapInRange(maps, me.cLevel, max) ? me.cLevel : me.cLevel + 2;
    }

    private int getMaxMobLevel(Char me) {
        return me.cLevel + 8;
    }

    private int selectTargetMap(Char me) {
        int[][] maps = this.getLevelMaps(me);
        int minLevel = this.getMinMobLevel(me);
        int maxLevel = this.getMaxMobLevel(me);
        if (this.isMapInTable(TileMap.mapID, maps) && this.findBestMob(me) != null) {
            return TileMap.mapID;
        }

        int map = this.selectMapFromTable(maps, minLevel, maxLevel, true);
        if (map >= 0) {
            return map;
        }

        map = this.selectMapFromTable(maps, me.cLevel, maxLevel, false);
        if (map >= 0) {
            return map;
        }

        return this.selectClosestHigherMap(maps, me.cLevel);
    }

    private int selectMapFromTable(int[][] maps, int minLevel, int maxLevel, boolean strict) {
        long now = System.currentTimeMillis();
        int desired = (minLevel + maxLevel) / 2;
        int bestMap = -1;
        int bestScore = 999999;
        boolean skippedBad = false;

        for (int i = 0; i < maps.length; ++i) {
            int[] row = maps[i];
            int rowMap = row[0];
            if (rowMap == this.badMap && now < this.badMapUntil) {
                skippedBad = true;
                continue;
            }

            int score = this.scoreRow(row, minLevel, maxLevel, desired);
            if (score < bestScore) {
                bestScore = score;
                bestMap = rowMap;
            }
        }

        if (bestMap < 0 && skippedBad) {
            this.badMap = -1;
            this.badMapUntil = 0L;
            return this.selectMapFromTable(maps, minLevel, maxLevel, strict);
        }

        return strict && bestScore >= 999999 ? -1 : bestMap;
    }

    private int selectClosestHigherMap(int[][] maps, int level) {
        int bestMap = -1;
        int bestScore = 999999;
        for (int i = 0; i < maps.length; ++i) {
            int[] row = maps[i];
            for (int j = 1; j < row.length; ++j) {
                int mobLevel = row[j];
                if (mobLevel >= level && mobLevel - level < bestScore) {
                    bestScore = mobLevel - level;
                    bestMap = row[0];
                }
            }
        }

        return bestMap;
    }

    private int scoreRow(int[] row, int minLevel, int maxLevel, int desired) {
        int best = 999999;
        for (int i = 1; i < row.length; ++i) {
            int level = row[i];
            if (level >= minLevel && level <= maxLevel) {
                int score = Math.abs(level - desired) * 10 + Math.abs(row[0] - TileMap.mapID);
                if (score < best) {
                    best = score;
                }
            }
        }

        return best;
    }

    private boolean hasMapInRange(int[][] maps, int minLevel, int maxLevel) {
        for (int i = 0; i < maps.length; ++i) {
            if (this.scoreRow(maps[i], minLevel, maxLevel, (minLevel + maxLevel) / 2) < 999999) {
                return true;
            }
        }

        return false;
    }

    private boolean isMapInTable(int map, int[][] maps) {
        for (int i = 0; i < maps.length; ++i) {
            if (maps[i][0] == map) {
                return true;
            }
        }

        return false;
    }

    private Mob findBestMob(Char me) {
        Mob best = null;
        int bestScore = 999999;
        int desired = me.cLevel + 5;
        for (int i = 0; i < GameScr.vMobAttack.size(); ++i) {
            Mob mob = (Mob) GameScr.vMobAttack.elementAt(i);
            if (this.isValidMob(mob, me)) {
                int score = Math.abs(mob.lv - desired) * 100 + Res.distance(me.cx, me.cy, mob.curX, mob.curY);
                if (score < bestScore) {
                    bestScore = score;
                    best = mob;
                }
            }
        }

        return best;
    }

    private boolean isValidMob(Mob mob, Char me) {
        if (mob == null || mob.hp <= 0 || mob.h == 0 || mob.h == 1 || mob.isBoss || mob.id == 202 && mob.h == 8) {
            return false;
        }

        int flags = this.getAttackFlags();
        if (mob.levelBoss == 0 && (flags & 1) == 0 || mob.levelBoss == 1 && (flags & 2) == 0 || mob.levelBoss == 2 && (flags & 4) == 0 || mob.levelBoss == 3 && (flags & 8) == 0) {
            return false;
        }

        int minLevel = this.getMinMobLevel(me);
        int maxLevel = this.getMaxMobLevel(me);
        return mob.lv >= minLevel && mob.lv <= maxLevel;
    }

    private int getAttackFlags() {
        boolean normal = Char.tickDanhQuaiThuong || !Char.tickDanhTinhAnh && !Char.tickDanhThuLinh;
        return this.a(normal, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false);
    }

    private void handleNoMob() {
        long now = System.currentTimeMillis();
        if (this.noMobSince <= 0L) {
            this.noMobSince = now;
            return;
        }

        if (now - this.noMobSince < 5000L) {
            return;
        }

        if (this.zoneTryCount < 2 && now - this.lastZoneTry > 3500L) {
            ++this.zoneTryCount;
            this.lastZoneTry = now;
            this.b(TileMap.zoneID);
            this.noMobSince = now;
            return;
        }

        this.badMap = TileMap.mapID;
        this.badMapUntil = now + 30000L;
        this.noMobSince = 0L;
        this.zoneTryCount = 0;
        Char.getMyChar().mobFocus = null;
    }

    private void popupSlow(String text) {
        long now = System.currentTimeMillis();
        if (now - this.lastPopup > 5000L) {
            GameScr.chatPopup(text);
            this.lastPopup = now;
        }
    }
}
