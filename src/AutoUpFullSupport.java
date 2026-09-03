public final class AutoUpFullSupport {

    private static final int SHOP_VU_KHI = 2;
    private static final int SHOP_BOOK = 15;
    private static final int SHOP_LIEN = 16;
    private static final int SHOP_NHAN = 17;
    private static final int SHOP_NGOC_BOI = 18;
    private static final int SHOP_PHU = 19;
    private static final int SHOP_NON_NAM = 20;
    private static final int SHOP_NON_NU = 21;
    private static final int SHOP_AO_NAM = 22;
    private static final int SHOP_AO_NU = 23;
    private static final int SHOP_GANG_NAM = 24;
    private static final int SHOP_GANG_NU = 25;
    private static final int SHOP_QUAN_NAM = 26;
    private static final int SHOP_QUAN_NU = 27;
    private static final int SHOP_GIAY_NAM = 28;
    private static final int SHOP_GIAY_NU = 29;
    private static final int SHOP_STORE = 14;
    private static final int ITEM_NAM_LINH_CHI = 248;
    private static final int EFFECT_NAM_LINH_CHI = 22;
    private static final int EXCHANGE_YEN_MAP = 1;
    private static final int EXCHANGE_YEN_NPC = 24;
    private static final long EXCHANGE_YEN_DELAY = 30000L;
    private static final long NAM_LINH_CHI_CHECK_DELAY = 3000L;
    private static final long NAM_LINH_CHI_USE_DELAY = 3000L;
    private static final long NAM_LINH_CHI_BUY_DELAY = 30000L;
    private static final long NAM_LINH_CHI_FAIL_DELAY = 60000L;
    private static final int[] UPGRADE_WEAPON_SLOTS = new int[]{1};
    private static final int[] UPGRADE_ADORN_SLOTS = new int[]{3, 5, 7, 9};
    private static final int[] UPGRADE_CLOTHE_SLOTS = new int[]{0, 2, 4, 6, 8};

    private static long lastCheckAt = 0L;
    private static long lastPotentialActionAt = 0L;
    private static long lastSkillActionAt = 0L;
    private static long lastBookActionAt = 0L;
    private static long lastGearActionAt = 0L;
    private static long lastUpgradeActionAt = 0L;
    private static String statusText = "";
    private static boolean crystalPickOverride = false;
    private static boolean savedCrystalPickSetting = false;
    private static boolean oldTickNhatDa = false;
    private static int oldEw = 3;
    private static long lastNamLinhChiCheckAt = 0L;
    private static long lastNamLinhChiUseAt = 0L;
    private static long lastNamLinhChiBuyAt = 0L;
    private static long lastNamLinhChiFailAt = 0L;
    private static long lastExchangeYenAt = 0L;
    private static boolean exchangeYenTried = false;

    private AutoUpFullSupport() {
    }

    public static boolean handle(AutoUpLevel owner, Char me) {
        try {
            if (owner == null || me == null || me.arrItemBag == null || me.arrItemBody == null) {
                return false;
            }
            if (!me.isHuman) {
                return false;
            }
            if (AutoDapDo.isRunning()) {
                statusText = AutoDapDo.getAutoText();
                return true;
            }

            long now = System.currentTimeMillis();
            if (FormAutoUpFull.UseNamLinhChiX2 && handleNamLinhChi(me, now)) {
                return true;
            }

            int delay = FormAutoUpFull.CheckDelayMs;
            if (delay < 3000) {
                delay = 3000;
            }
            if (now - lastCheckAt < (long) delay) {
                return false;
            }
            lastCheckAt = now;

            if (FormAutoUpFull.AutoPotential && handlePotential(me, now)) {
                return true;
            }
            if (FormAutoUpFull.LearnBooks && handleBook(me, now)) {
                return true;
            }
            if (FormAutoUpFull.AutoSkill && handleSkill(me, now)) {
                return true;
            }
            if ((FormAutoUpFull.BuyGear || FormAutoUpFull.UpgradeGear) && FormAutoUpFull.getTargetTierForLevel(me.cLevel) > 0) {
                if (FormAutoUpFull.UpgradeGear) {
                    ensureCrystalPickForUpgrade();
                } else {
                    restoreCrystalPickForUpgrade();
                }
                if (FormAutoUpFull.BuyGear && handleGear(me, now)) {
                    return true;
                }
                if (FormAutoUpFull.UpgradeGear && handleUpgrade(me, now)) {
                    return true;
                }
            } else {
                restoreCrystalPickForUpgrade();
            }
        } catch (Exception e) {
        }
        statusText = "";
        return false;
    }

    public static String getStatusText() {
        return statusText == null ? "" : statusText;
    }

    public static boolean shouldWaitNextCheck() {
        if (statusText == null || statusText.length() == 0 || lastCheckAt <= 0L) {
            return false;
        }
        int delay = FormAutoUpFull.CheckDelayMs;
        if (delay < 3000) {
            delay = 3000;
        }
        return System.currentTimeMillis() - lastCheckAt < (long) delay;
    }

    public static void reset() {
        lastCheckAt = 0L;
        lastPotentialActionAt = 0L;
        lastSkillActionAt = 0L;
        lastBookActionAt = 0L;
        lastGearActionAt = 0L;
        lastUpgradeActionAt = 0L;
        lastNamLinhChiCheckAt = 0L;
        lastNamLinhChiUseAt = 0L;
        lastNamLinhChiBuyAt = 0L;
        lastNamLinhChiFailAt = 0L;
        lastExchangeYenAt = 0L;
        exchangeYenTried = false;
        statusText = "";
        restoreCrystalPickForUpgrade();
    }

    private static boolean handleNamLinhChi(Char me, long now) {
        try {
            if (now - lastNamLinhChiCheckAt < NAM_LINH_CHI_CHECK_DELAY || isNamLinhChiBusy()) {
                return false;
            }
            lastNamLinhChiCheckAt = now;

            if (hasNamLinhChiEffect(me)) {
                return false;
            }

            Item item = findNamLinhChi(me, true);
            if (item != null) {
                if (now - lastNamLinhChiUseAt < NAM_LINH_CHI_USE_DELAY) {
                    return false;
                }
                lastNamLinhChiUseAt = now;
                statusText = "Dung Nam linh chi x2";
                GameScr.chatPopup("Auto Up Tong: dung Nam linh chi x2");
                Service.getInstance().useItem(item.indexUI);
                Auto.sleep(1000L);
                return true;
            }

            if (findNamLinhChi(me, false) != null) {
                if (now - lastNamLinhChiFailAt >= NAM_LINH_CHI_FAIL_DELAY) {
                    lastNamLinhChiFailAt = now;
                    statusText = "Nam linh chi dang stack >1";
                    GameScr.chatPopup("Auto Up Tong: 248 dang stack >1, khong tu dung de tranh mat ca stack");
                }
                return false;
            }

            if (now - lastNamLinhChiBuyAt < NAM_LINH_CHI_BUY_DELAY || now - lastNamLinhChiFailAt < NAM_LINH_CHI_FAIL_DELAY) {
                return false;
            }
            if (Char.countNullSlot() <= 0) {
                lastNamLinhChiFailAt = now;
                statusText = "Full hanh trang, khong mua 248";
                GameScr.chatPopup("Auto Up Tong: full hanh trang, khong mua 248");
                return false;
            }

            lastNamLinhChiBuyAt = now;
            statusText = "Mua Nam linh chi 248";
            GameScr.chatPopup("Auto Up Tong: mua Nam linh chi 248");
            if (!AutoBuyShop.buyNow(ITEM_NAM_LINH_CHI, SHOP_STORE, 1)) {
                lastNamLinhChiFailAt = now;
                statusText = "Mua Nam linh chi that bai";
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean hasNamLinhChiEffect(Char me) {
        return getNamLinhChiEffectRemain(me) > 60;
    }

    private static int getNamLinhChiEffectRemain(Char me) {
        try {
            if (me == null || me.vEff == null) {
                return 0;
            }
            int nowSec = (int) (System.currentTimeMillis() / 1000L);
            for (int i = 0; i < me.vEff.size(); ++i) {
                Effect effect = (Effect) me.vEff.elementAt(i);
                if (effect == null || effect.e == null) {
                    continue;
                }
                boolean match = effect.e.a == EFFECT_NAM_LINH_CHI;
                if (!match && Effect.a != null && EFFECT_NAM_LINH_CHI >= 0 && EFFECT_NAM_LINH_CHI < Effect.a.length) {
                    match = effect.e == Effect.a[EFFECT_NAM_LINH_CHI];
                }
                if (match) {
                    int remain = effect.c - (nowSec - effect.b);
                    return remain > 0 ? remain : 0;
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    private static Item findNamLinhChi(Char me, boolean onlySafeStack) {
        try {
            if (me == null || me.arrItemBag == null) {
                return null;
            }
            for (int i = 0; i < me.arrItemBag.length; ++i) {
                Item item = me.arrItemBag[i];
                if (item == null || item.template == null || item.template.id != ITEM_NAM_LINH_CHI) {
                    continue;
                }
                if (!onlySafeStack || item.quantity <= 1) {
                    return item;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static boolean isNamLinhChiBusy() {
        try {
            if (GameCanvas.currentDialog != null || GameCanvas.menu != null && GameCanvas.menu.showMenu || TileMap.ag) {
                return true;
            }
            if (AutoDoiLongDen.shouldPauseProducers() || AutoRuocDen.isBusy()) {
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

    public static boolean canPickCrystalForUpgrade(ItemTemplate itemTemplate) {
        return crystalPickOverride && itemTemplate != null && itemTemplate.type == 26
                && Char.tickNhatDa && itemTemplate.id >= Char.ew - 1
                && GameScr.crystals != null && itemTemplate.id >= 0 && itemTemplate.id < GameScr.crystals.length
                && GameScr.crystals[itemTemplate.id] > 0;
    }

    private static void ensureCrystalPickForUpgrade() {
        if (!savedCrystalPickSetting) {
            oldTickNhatDa = Char.tickNhatDa;
            oldEw = Char.ew;
            savedCrystalPickSetting = true;
        }
        crystalPickOverride = true;
        Char.tickNhatDa = true;
        Char.ew = 4;
    }

    private static void restoreCrystalPickForUpgrade() {
        if (!savedCrystalPickSetting) {
            crystalPickOverride = false;
            return;
        }
        Char.tickNhatDa = oldTickNhatDa;
        Char.ew = oldEw;
        crystalPickOverride = false;
        savedCrystalPickSetting = false;
    }

    private static boolean handlePotential(Char me, long now) {
        if (me == null || me.ai <= 0 || now - lastPotentialActionAt < 2000L || isNamLinhChiBusy()) {
            return false;
        }

        int stat = me.isNoiCong() ? 3 : 0;
        int add = me.ai;
        if (add > 32767) {
            add = 32767;
        }
        if (add <= 0) {
            return false;
        }

        lastPotentialActionAt = now;
        statusText = "Cong tiem nang " + mResources.iz[stat] + " +" + add;
        GameScr.chatPopup("Auto Up Tong: cong tiem nang " + mResources.iz[stat] + " +" + add);
        Service.getInstance().e(stat, add);
        LockGame.w();
        Auto.sleep(700L);
        return true;
    }

    private static boolean handleSkill(Char me, long now) {
        if (me == null || me.aj <= 0 || now - lastSkillActionAt < 2000L || isNamLinhChiBusy()) {
            return false;
        }

        Skill skill = findPriorityAttackSkill(me);
        if (skill == null || skill.template == null) {
            selectBestAttackSkill(me);
            return false;
        }

        int add = countSkillAddPoint(me, skill);
        if (add <= 0) {
            selectAttackSkill(me, skill);
            return false;
        }

        lastSkillActionAt = now;
        selectAttackSkill(me, skill);
        statusText = "Cong ky nang " + skill.template.name + " +" + add;
        GameScr.chatPopup("Auto Up Tong: cong ky nang " + skill.template.name + " +" + add);
        Service.getInstance().f(skill.template.id, add);
        Auto.sleep(700L);
        return true;
    }

    private static Skill findPriorityAttackSkill(Char me) {
        int[] levels = new int[]{10, 20, 30, 50};
        for (int i = 0; i < levels.length; ++i) {
            Skill skill = findAttackSkillByLevelRange(me, levels[i], levels[i] + 9, true);
            if (skill != null) {
                return skill;
            }
        }
        return null;
    }

    private static Skill findAttackSkillByLevelRange(Char me, int minLevel, int maxLevel, boolean requireAddPoint) {
        if (me == null || me.vSkill == null) {
            return null;
        }

        Skill best = null;
        for (int i = 0; i < me.vSkill.size(); ++i) {
            Skill skill = (Skill) me.vSkill.elementAt(i);
            if (!isAttackSkill(skill)) {
                continue;
            }
            int level = getLearnLevel(skill.template);
            if (level < minLevel || level > maxLevel) {
                continue;
            }
            if (requireAddPoint && countSkillAddPoint(me, skill) <= 0) {
                continue;
            }
            if (best == null || skill.point < best.point || skill.point == best.point && level > getLearnLevel(best.template)) {
                best = skill;
            }
        }
        return best;
    }

    private static void selectBestAttackSkill(Char me) {
        int[] levels = new int[]{50, 30, 20, 10};
        for (int i = 0; i < levels.length; ++i) {
            Skill skill = findAttackSkillByLevelRange(me, levels[i], levels[i] + 9, false);
            if (skill != null) {
                selectAttackSkill(me, skill);
                return;
            }
        }
    }

    private static boolean isAttackSkill(Skill skill) {
        return skill != null && skill.template != null && (skill.template.type == 1 || skill.template.type == 3)
                && skill.template.maxPoint > 0;
    }

    private static int countSkillAddPoint(Char me, Skill skill) {
        if (me == null || skill == null || skill.template == null || skill.template.skills == null || me.aj <= 0) {
            return 0;
        }

        int add = 0;
        int point = skill.point + 1;
        while (add < me.aj && point <= skill.template.maxPoint && point < skill.template.skills.length) {
            Skill next = skill.template.skills[point];
            if (next == null || next.level > me.cLevel) {
                break;
            }
            ++add;
            ++point;
        }
        return add;
    }

    private static void selectAttackSkill(Char me, Skill skill) {
        try {
            if (me == null || skill == null || skill.template == null) {
                return;
            }
            if (me.selectSkill == null || me.selectSkill.template == null || me.selectSkill.template.id != skill.template.id) {
                me.selectSkill = skill;
                me.gi = skill;
                Service.getInstance().selectSkill(skill.template.id);
            }
            Auto.selectSkill = skill;
        } catch (Exception e) {
        }
    }

    private static boolean handleBook(Char me, long now) {
        if (me.nClass == null || me.nClass.classId <= 0 || me.nClass.skillTemplates == null) {
            return false;
        }
        if (now - lastBookActionAt < 5000L) {
            return false;
        }

        int bookId = findMissingBookId(me);
        if (bookId <= 0) {
            return false;
        }

        lastBookActionAt = now;
        int index = Char.getIndexItemById(bookId);
        if (index >= 0) {
            statusText = "Hoc sach " + bookId;
            GameScr.chatPopup("Auto Up Tong: hoc sach " + bookId);
            Service.getInstance().useItem(index);
            Auto.sleep(1200L);
            return true;
        }

        if (Char.countNullSlot() <= 0) {
            statusText = "Full hanh trang, khong mua sach";
            GameScr.chatPopup("Auto Up Tong: full hanh trang, khong mua sach");
            return false;
        }

        statusText = "Mua sach " + bookId;
        GameScr.chatPopup("Auto Up Tong: mua sach " + bookId);
        AutoBuyShop.buyNow(bookId, SHOP_BOOK, 1);
        return true;
    }

    private static int findMissingBookId(Char me) {
        SkillTemplate[] templates = me.nClass.skillTemplates;
        for (int i = 0; i < templates.length; ++i) {
            SkillTemplate template = templates[i];
            if (template == null) {
                continue;
            }

            int level = getLearnLevel(template);
            if (level <= 0 || level > me.cLevel || level > FormAutoUpFull.MaxBookLevel) {
                continue;
            }
            if (hasSkill(me, template.id)) {
                continue;
            }

            int bookId = getBookId(template.id);
            if (bookId > 0) {
                return bookId;
            }
        }
        return -1;
    }

    private static int getLearnLevel(SkillTemplate template) {
        if (template.skills == null || template.skills.length == 0) {
            return 0;
        }
        int first = 999;
        for (int i = 0; i < template.skills.length; ++i) {
            Skill skill = template.skills[i];
            if (skill == null) {
                continue;
            }
            if (skill.point == 1) {
                return skill.level;
            }
            if (skill.level > 0 && skill.level < first) {
                first = skill.level;
            }
        }
        return first == 999 ? 0 : first;
    }

    private static boolean hasSkill(Char me, int templateId) {
        if (me.vSkill == null) {
            return false;
        }
        for (int i = 0; i < me.vSkill.size(); ++i) {
            Skill skill = (Skill) me.vSkill.elementAt(i);
            if (skill != null && skill.template != null && skill.template.id == templateId) {
                return true;
            }
        }
        return false;
    }

    private static int getBookId(int templateId) {
        if (templateId >= 1 && templateId <= 54) {
            return templateId + 39;
        }
        if (templateId >= 55 && templateId <= 60) {
            return templateId + 256;
        }
        if (templateId >= 61 && templateId <= 66) {
            return templateId + 314;
        }
        if (templateId >= 67 && templateId <= 72 || templateId == 97) {
            return 547;
        }
        if (templateId == 73) {
            return 552;
        }
        if (templateId == 74) {
            return 556;
        }
        if (templateId == 75) {
            return 554;
        }
        if (templateId == 76) {
            return 555;
        }
        if (templateId == 77) {
            return 557;
        }
        if (templateId == 78) {
            return 553;
        }
        if (templateId == 79) {
            return 558;
        }
        if (templateId == 80) {
            return 562;
        }
        if (templateId == 81) {
            return 560;
        }
        if (templateId == 82) {
            return 561;
        }
        if (templateId == 83) {
            return 559;
        }
        if (templateId == 84) {
            return 563;
        }
        if (templateId >= 85 && templateId <= 93) {
            return templateId + 1101;
        }
        if (templateId == 94) {
            return 1195;
        }
        if (templateId == 95) {
            return 1196;
        }
        if (templateId == 96) {
            return 1197;
        }
        if (templateId == 98) {
            return 1198;
        }
        if (templateId == 99) {
            return 844;
        }
        if (templateId == 100) {
            return 841;
        }
        if (templateId == 101) {
            return 842;
        }
        if (templateId == 102) {
            return 839;
        }
        if (templateId == 103) {
            return 843;
        }
        if (templateId == 104) {
            return 840;
        }
        if (templateId == 105) {
            return 1161;
        }
        return -1;
    }

    private static boolean handleGear(Char me, long now) {
        if (me.nClass == null || me.nClass.classId <= 0) {
            return false;
        }
        if (now - lastGearActionAt < 5000L) {
            return false;
        }

        int tier = FormAutoUpFull.getTargetTierForLevel(me.cLevel);
        int[] slots = FormAutoUpFull.getSelectedBodyTypes();
        for (int i = 0; i < slots.length; ++i) {
            int slot = slots[i];
            if (!needBetterGear(me, slot, tier)) {
                continue;
            }
            int requiredLevel = getRequiredLevelForSlot(slot, tier);
            if (requiredLevel <= 0 || me.cLevel < requiredLevel) {
                continue;
            }

            Item bag = findGearInBag(me, slot, tier);
            if (bag != null) {
                lastGearActionAt = now;
                statusText = "Mac do " + tier + "x slot " + slot;
                GameScr.chatPopup("Auto Up Tong: mac do " + tier + "x slot " + slot);
                Service.getInstance().useItem(bag.indexUI);
                Auto.sleep(1000L);
                return true;
            }

            if (buyGear(me, slot, tier)) {
                lastGearActionAt = now;
                return true;
            }
        }
        return false;
    }

    private static boolean needBetterGear(Char me, int slot, int tier) {
        if (slot < 0 || slot >= me.arrItemBody.length) {
            return false;
        }
        Item body = me.arrItemBody[slot];
        if (body == null || body.template == null) {
            return true;
        }
        return !isGearOkForSlot(body, me, slot, tier, true);
    }

    private static Item findGearInBag(Char me, int slot, int tier) {
        Item best = null;
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (!isGearOkForSlot(item, me, slot, tier, true)) {
                continue;
            }
            if (best == null || item.template.level > best.template.level || item.template.level == best.template.level && item.upgrade > best.upgrade) {
                best = item;
            }
        }
        return best;
    }

    private static boolean buyGear(Char me, int slot, int tier) {
        if (Char.countNullSlot() <= 0) {
            statusText = "Full hanh trang, khong mua do";
            GameScr.chatPopup("Auto Up Tong: full hanh trang, khong mua do");
            return false;
        }

        int requiredLevel = getRequiredLevelForSlot(slot, tier);
        if (requiredLevel <= 0 || me.cLevel < requiredLevel) {
            return false;
        }

        int shopType = getShopType(slot, me.cgender);
        if (shopType < 0) {
            return false;
        }
        statusText = "Mua do lv " + requiredLevel + " slot " + slot;
        GameScr.chatPopup("Auto Up Tong: mua do lv " + requiredLevel + " slot " + slot);
        AutoBuyShop.restoreAfterBuy();
        Auto.sleep(150L);
        if (!prepareGearShop(slot, shopType)) {
            statusText = "Cho mo shop " + shopType;
            return false;
        }

        Item shopItem = findShopGear(me, shopType, slot, tier);
        if (shopItem == null) {
            Auto.sleep(700L);
            Service.getInstance().requestItem(shopType);
            Auto.sleep(900L);
            shopItem = findShopGear(me, shopType, slot, tier);
        }

        shopItem = resolveShopGearSys(me, shopType, slot, tier, shopItem);

        if (shopItem == null) {
            GameScr.chatPopup("Auto Up Tong: khong thay do lv " + requiredLevel + " shop " + shopType);
            AutoBuyShop.restoreAfterBuy();
            return false;
        }

        int buyId = shopItem.template.id;
        int buyLevel = shopItem.template.level;
        int buyTypeUI = shopItem.typeUI;
        int buyIndexUI = shopItem.indexUI;
        int before = countGearInBag(me, buyId);
        GameScr.chatPopup("Auto Up Tong: mua id " + buyId + " lv " + buyLevel + " shop " + buyTypeUI + " index " + buyIndexUI);
        Service.getInstance().buyItem1(buyTypeUI, buyIndexUI, 1);
        LockGame.g();
        if (!waitGearInBag(buyId, before, 6000L)) {
            Service.getInstance().viewInfo(me.charName);
            Auto.sleep(800L);
        }
        AutoBuyShop.restoreAfterBuy();
        return true;
    }

    private static Item resolveShopGearSys(Char me, int shopType, int slot, int tier, Item item) {
        int sys = getMySys(me);
        if (item == null || sys <= 0) {
            return item;
        }

        if (!item.s) {
            Service.getInstance().requestItemInfo(item.typeUI, item.indexUI);
            waitItemInfo(item, 900L);
        }
        if (isResolvedShopGearCandidate(item, me, slot, tier)) {
            return item;
        }

        Item[] arr = AutoBuyShop.getShopItems(shopType);
        Item best = null;
        Item fallback = slot == 1 && !item.s && isGearShopCandidate(item, me, slot, tier, false) ? item : null;
        if (arr == null) {
            return fallback;
        }

        for (int i = 0; i < arr.length; ++i) {
            Item candidate = arr[i];
            if (!isGearShopCandidate(candidate, me, slot, tier, false)) {
                continue;
            }
            if (!candidate.s) {
                Service.getInstance().requestItemInfo(candidate.typeUI, candidate.indexUI);
                waitItemInfo(candidate, 900L);
            }
            if (!candidate.s) {
                if (slot == 1 && (fallback == null || scoreShopGear(candidate, tier) > scoreShopGear(fallback, tier))) {
                    fallback = candidate;
                }
                continue;
            }
            if (isResolvedShopGearCandidate(candidate, me, slot, tier)
                    && (best == null || scoreShopGear(candidate, tier) > scoreShopGear(best, tier))) {
                best = candidate;
            }
        }
        return best != null ? best : fallback;
    }

    private static Item findShopGear(Char me, int shopType, int slot, int tier) {
        Item[] arr = AutoBuyShop.getShopItems(shopType);
        if (arr == null) {
            return null;
        }
        Item best = null;
        for (int i = 0; i < arr.length; ++i) {
            Item item = arr[i];
            if (isGearShopCandidate(item, me, slot, tier, false)
                    && (best == null || scoreShopGear(item, tier) > scoreShopGear(best, tier))) {
                best = item;
            }
        }
        return best;
    }

    private static boolean isGearShopCandidate(Item item, Char me, int slot, int tier, boolean checkSys) {
        if (item == null || item.template == null || item.template.type != slot) {
            return false;
        }
        int requiredLevel = getRequiredLevelForSlot(slot, tier);
        int itemLevel = getEffectiveGearLevel(item.template.type, item.template.level);
        if (requiredLevel <= 0 || itemLevel != requiredLevel || itemLevel > me.cLevel) {
            return false;
        }
        if (!isGenderOk(item, me, slot)) {
            return false;
        }
        if (slot == 1 && !isWeaponForClass(item.template.id, me.nClass.classId)) {
            return false;
        }
        return !checkSys || slot == 1 || item.sys == getMySys(me);
    }

    private static int scoreShopGear(Item item, int tier) {
        int score = getEffectiveGearLevel(item.template.type, item.template.level);
        if (score / 10 == tier) {
            score += 1000;
        }
        if (item.sys > 0 && getMySys(Char.getMyChar()) == item.sys) {
            score += 100;
        }
        return score;
    }

    private static boolean isGearOkForSlot(Item item, Char me, int slot, int tier, boolean allowHigher) {
        if (item == null || item.template == null || item.template.type != slot) {
            return false;
        }
        int itemLevel = getEffectiveGearLevel(item.template.type, item.template.level);
        int itemTier = itemLevel / 10;
        if (allowHigher) {
            if (itemTier < tier) {
                return false;
            }
        } else if (itemTier != tier) {
            return false;
        }
        if (itemLevel > me.cLevel) {
            return false;
        }
        if (!isGenderOk(item, me, slot)) {
            return false;
        }
        if (slot == 1 && !isWeaponForClass(item.template.id, me.nClass.classId)) {
            return false;
        }
        return slot == 1 || item.sys <= 0 || getMySys(me) <= 0 || item.sys == getMySys(me);
    }

    private static boolean isGenderOk(Item item, Char me, int slot) {
        if (slot == 0 || slot == 2 || slot == 4 || slot == 6 || slot == 8) {
            return item.template.gender >= 2 || item.template.gender == me.cgender;
        }
        return true;
    }

    private static boolean prepareGearShop(int slot, int shopType) {
        return AutoBuyShop.prepareShopForBuy(shopType);
    }

    private static boolean isResolvedShopGearCandidate(Item item, Char me, int slot, int tier) {
        return item != null && item.s && isGearShopCandidate(item, me, slot, tier, true);
    }

    private static int getRequiredLevelForSlot(int slot, int tier) {
        if (tier <= 0) {
            return -1;
        }
        int base = tier * 10;
        switch (slot) {
            case 1:
                return base;
            case 8:
                return base + 1;
            case 9:
                return base + 2;
            case 6:
                return base + 3;
            case 7:
                return base + 4;
            case 4:
                return base + 5;
            case 5:
                return base + 6;
            case 2:
                return base + 7;
            case 3:
                return base + 8;
            case 0:
                return base + 9;
        }
        return -1;
    }

    private static int getEffectiveGearLevel(int type, int level) {
        if (level != 85) {
            return level;
        }
        switch (type) {
            case 0:
                return 89;
            case 1:
                return 80;
            case 2:
                return 87;
            case 3:
                return 88;
            case 4:
                return 85;
            case 5:
                return 86;
            case 6:
                return 83;
            case 7:
                return 84;
            case 8:
                return 81;
            case 9:
                return 82;
        }
        return level;
    }

    private static boolean handleUpgrade(Char me, long now) {
        if (now - lastUpgradeActionAt < 30000L) {
            return false;
        }
        int tier = FormAutoUpFull.getTargetTierForLevel(me.cLevel);
        int target = FormAutoUpFull.getTargetUpgradeForTier(tier);
        if (target <= 0 || !hasUpgradeableGear(me, tier, target)) {
            return false;
        }
        int modeMask = selectReadyUpgradeMask(me, tier, target, now);
        if (modeMask < 0) {
            lastUpgradeActionAt = now;
            return true;
        }
        if (modeMask == 0) {
            return false;
        }

        lastUpgradeActionAt = now;
        statusText = "Dap set " + tier + "x len +" + target;
        GameScr.chatPopup("Auto Up Tong: dap set " + tier + "x len +" + target);
        AutoDapDo.startForAutoUp(modeMask, target, FormAutoUpFull.UseXuWhenLackYen, FormAutoUpFull.UseProtectUpgrade);
        return true;
    }

    private static int selectReadyUpgradeMask(Char me, int tier, int target, long now) {
        int configured = FormAutoUpFull.getUpgradeModeMask();
        int result;
        if ((configured & FormAutoDapDo.MASK_WEAPON) != 0) {
            result = getReadyUpgradeMask(me, UPGRADE_WEAPON_SLOTS, FormAutoDapDo.MASK_WEAPON, tier, target, now);
            if (result != 0) {
                return result;
            }
            if (findNextUpgradeItem(me, UPGRADE_WEAPON_SLOTS, tier, target) != null) {
                return 0;
            }
        }
        if ((configured & FormAutoDapDo.MASK_ADORN) != 0) {
            result = getReadyUpgradeMask(me, UPGRADE_ADORN_SLOTS, FormAutoDapDo.MASK_ADORN, tier, target, now);
            if (result != 0) {
                return result;
            }
        }
        if ((configured & FormAutoDapDo.MASK_CLOTHE) != 0) {
            result = getReadyUpgradeMask(me, UPGRADE_CLOTHE_SLOTS, FormAutoDapDo.MASK_CLOTHE, tier, target, now);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    private static int getReadyUpgradeMask(Char me, int[] slots, int mask, int tier, int target, long now) {
        Item item = findNextUpgradeItem(me, slots, tier, target);
        if (item == null) {
            return 0;
        }
        int moneyState = checkMoneyForUpgrade(me, item, now);
        if (moneyState < 0) {
            return -1;
        }
        if (moneyState == 0 || !hasProtectForUpgrade(me, item)) {
            return 0;
        }

        int protectSlots = getProtectIdForUpgrade(item.upgrade) > 0 ? 1 : 0;
        int required = getRequiredCrystalValueForUpgrade(item);
        int available = getSelectableCrystalValue(me, item, protectSlots, required);
        if (required <= 0 || available < required) {
            statusText = "Cho nhat da dap " + available + "/" + required;
            return 0;
        }
        return mask;
    }

    private static Item findNextUpgradeItem(Char me, int[] slots, int tier, int target) {
        if (me == null || me.arrItemBody == null || slots == null) {
            return null;
        }
        Item best = null;
        for (int i = 0; i < slots.length; ++i) {
            int slot = slots[i];
            if (slot < 0 || slot >= me.arrItemBody.length) {
                continue;
            }
            Item item = me.arrItemBody[slot];
            if (isUpgradeCandidate(item, me, slot, tier, target)
                    && (best == null || item.upgrade < best.upgrade)) {
                best = item;
            }
        }
        return best;
    }

    private static boolean isUpgradeCandidate(Item item, Char me, int slot, int tier, int target) {
        return isGearOkForSlot(item, me, slot, tier, true) && item.upgrade < target && item.upgrade < item.q();
    }

    private static int checkMoneyForUpgrade(Char me, Item item, long now) {
        int coin = getUpgradeCoin(item);
        if (coin < 0) {
            return 0;
        }
        if (coin <= me.yen) {
            exchangeYenTried = false;
        } else {
            if (FormAutoUpFull.ExchangeLuongToYen && tryExchangeLuongToYen(me, now)) {
                return -1;
            }
            if (!FormAutoUpFull.UseXuWhenLackYen || coin > me.xu + me.yen) {
                statusText = "Thieu yen dap do";
                return 0;
            }
        }

        if (FormAutoDapDo.Careful) {
            int gold = getUpgradeGold(item);
            if (gold > me.luong) {
                statusText = "Thieu luong dap can than";
                return 0;
            }
        }
        return 1;
    }

    private static boolean tryExchangeLuongToYen(Char me, long now) {
        if (exchangeYenTried || me == null || me.luong <= 0 || now - lastExchangeYenAt < EXCHANGE_YEN_DELAY || isNamLinhChiBusy()) {
            return false;
        }
        exchangeYenTried = true;
        lastExchangeYenAt = now;
        statusText = "Doi luong ra yen";
        GameScr.chatPopup("Auto Up Tong: doi luong ra yen");
        Code.setAuto(new AutoNpc(EXCHANGE_YEN_MAP, -1, EXCHANGE_YEN_NPC, "0,4", "", 1, 500));
        return true;
    }

    private static boolean hasProtectForUpgrade(Char me, Item item) {
        int protectId = getProtectIdForUpgrade(item.upgrade);
        if (protectId <= 0) {
            return true;
        }
        if (Char.getIndexItemById(protectId) >= 0 || Char.k(protectId) > 0) {
            return true;
        }
        if (me.luong > 0 && Char.countNullSlot() > 0) {
            return true;
        }
        statusText = "Thieu bao hiem " + protectId;
        return false;
    }

    private static int getProtectIdForUpgrade(int upgrade) {
        if (!FormAutoUpFull.UseProtectUpgrade) {
            return -1;
        }
        if (upgrade == 7) {
            return 242;
        }
        if (upgrade == 10 || upgrade == 11) {
            return 284;
        }
        if (upgrade == 13) {
            return 285;
        }
        if (upgrade == 15) {
            return 475;
        }
        return -1;
    }

    private static int getSelectableCrystalValue(Char me, Item item, int start, int required) {
        if (me == null || me.arrItemBag == null || GameScr.crystals == null || required <= 0 || start >= 18) {
            return 0;
        }
        int total = 0;
        int index = start;
        boolean[] used = new boolean[me.arrItemBag.length];
        while (total < required && index < 18) {
            int crystalIndex = chooseCrystalIndex(me, used, required - total, 18 - index);
            if (crystalIndex < 0) {
                break;
            }
            Item crystal = me.arrItemBag[crystalIndex];
            used[crystalIndex] = true;
            total += GameScr.crystals[crystal.template.id];
            ++index;
        }
        return total;
    }

    private static int chooseCrystalIndex(Char me, boolean[] used, int remaining, int slotsLeft) {
        if (GameScr.crystals == null || slotsLeft <= 0) {
            return -1;
        }
        int averageNeed = ceilDiv(remaining, slotsLeft);
        int bestUnder = -1;
        int bestUnderValue = -1;
        int bestOver = -1;
        int bestOverValue = 2147483647;

        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item crystal = me.arrItemBag[i];
            if (used[i] || !isValidCrystal(crystal)) {
                continue;
            }
            int value = GameScr.crystals[crystal.template.id];
            if (value <= remaining) {
                if (value > bestUnderValue) {
                    bestUnder = i;
                    bestUnderValue = value;
                }
            } else if (value < bestOverValue) {
                bestOver = i;
                bestOverValue = value;
            }
        }

        if (bestUnder >= 0 && (bestUnderValue >= averageNeed || bestOver < 0)) {
            return bestUnder;
        }
        if (bestOver >= 0) {
            return bestOver;
        }
        return bestUnder;
    }

    private static boolean isValidCrystal(Item item) {
        return item != null && item.template != null && item.template.type == 26 && item.quantity == 1
                && item.template.id >= 0 && item.template.id < GameScr.crystals.length
                && GameScr.crystals[item.template.id] > 0;
    }

    private static int getRequiredCrystalValueForUpgrade(Item item) {
        try {
            int upgrade = item.upgrade;
            int percent = getServerMaxPercentForUpgrade(upgrade);
            if (item.isTypeWeapon()) {
                return ceilDiv(GameScr.upWeapon[upgrade] * percent, 95);
            }
            if (item.isTypeAdorn()) {
                return ceilDiv(GameScr.upAdorn[upgrade] * percent, 100);
            }
            return ceilDiv(GameScr.upClothe[upgrade] * percent, 100);
        } catch (Exception e) {
            return 0;
        }
    }

    private static int getServerMaxPercentForUpgrade(int upgrade) {
        int maxPercent = GameScr.maxPercents[upgrade];
        if (upgrade >= 14) {
            --maxPercent;
        }
        return maxPercent < 1 ? 1 : maxPercent;
    }

    private static int getUpgradeCoin(Item item) {
        try {
            if (item.isTypeWeapon()) {
                return GameScr.coinUpWeapons[item.upgrade];
            }
            if (item.isTypeAdorn()) {
                return GameScr.coinUpAdorns[item.upgrade];
            }
            return GameScr.coinUpClothes[item.upgrade];
        } catch (Exception e) {
            return -1;
        }
    }

    private static int getUpgradeGold(Item item) {
        try {
            return GameScr.goldUps[item.upgrade];
        } catch (Exception e) {
            return 0;
        }
    }

    private static boolean allSelectedGearReady(Char me, int tier) {
        int[] slots = FormAutoUpFull.getSelectedBodyTypes();
        if (slots.length == 0) {
            return false;
        }
        for (int i = 0; i < slots.length; ++i) {
            int slot = slots[i];
            if (slot < 0 || slot >= me.arrItemBody.length || !isGearOkForSlot(me.arrItemBody[slot], me, slot, tier, true)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasUpgradeableGear(Char me, int tier, int target) {
        int[] slots = FormAutoUpFull.getSelectedBodyTypes();
        for (int i = 0; i < slots.length; ++i) {
            int slot = slots[i];
            if (slot >= 0 && slot < me.arrItemBody.length) {
                Item item = me.arrItemBody[slot];
                if (isGearOkForSlot(item, me, slot, tier, true) && item.upgrade < target && item.upgrade < item.q()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int ceilDiv(int value, int div) {
        return div <= 0 ? 0 : (value + div - 1) / div;
    }

    private static int getShopType(int slot, int gender) {
        switch (slot) {
            case 0:
                return gender == 1 ? SHOP_NON_NAM : SHOP_NON_NU;
            case 1:
                return SHOP_VU_KHI;
            case 2:
                return gender == 1 ? SHOP_AO_NAM : SHOP_AO_NU;
            case 3:
                return SHOP_LIEN;
            case 4:
                return gender == 1 ? SHOP_GANG_NAM : SHOP_GANG_NU;
            case 5:
                return SHOP_NHAN;
            case 6:
                return gender == 1 ? SHOP_QUAN_NAM : SHOP_QUAN_NU;
            case 7:
                return SHOP_NGOC_BOI;
            case 8:
                return gender == 1 ? SHOP_GIAY_NAM : SHOP_GIAY_NU;
            case 9:
                return SHOP_PHU;
        }
        return -1;
    }

    private static boolean waitGearInBag(int templateId, int before, long timeout) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < timeout) {
            Char me = Char.getMyChar();
            if (countGearInBag(me, templateId) > before) {
                return true;
            }
            Auto.sleep(250L);
        }
        return false;
    }

    private static void waitItemInfo(Item item, long timeout) {
        long start = System.currentTimeMillis();
        while (item != null && !item.s && System.currentTimeMillis() - start < timeout) {
            Auto.sleep(100L);
        }
    }

    private static int getMySys(Char me) {
        try {
            return me != null && me.nClass != null ? me.getSys() : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static int countGearInBag(Char me, int templateId) {
        int count = 0;
        if (me == null || me.arrItemBag == null) {
            return 0;
        }
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (item != null && item.template != null && item.template.id == templateId) {
                ++count;
            }
        }
        return count;
    }

    private static boolean isWeaponForClass(int id, int classId) {
        switch (classId) {
            case 0:
                return id == 194 || isKiem(id);
            case 1:
                return isKiem(id);
            case 2:
                return isTieu(id);
            case 3:
                return isKunai(id);
            case 4:
                return isCung(id);
            case 5:
                return isDao(id);
            case 6:
                return isQuat(id);
            case 7:
                return isThuong(id);
        }
        return false;
    }

    private static boolean isKiem(int id) {
        return id == 94 || id == 95 || id == 96 || id == 97 || id == 98 || id == 369 || id == 506 || id == 632
                || id == 331 || id == 1129 || id == 1085;
    }

    private static boolean isTieu(int id) {
        return id == 114 || id == 115 || id == 116 || id == 117 || id == 118 || id == 370 || id == 332
                || id == 507 || id == 633 || id == 1130 || id == 1086;
    }

    private static boolean isKunai(int id) {
        return id == 99 || id == 100 || id == 101 || id == 102 || id == 103 || id == 333 || id == 508
                || id == 634 || id == 371 || id == 1131 || id == 1087;
    }

    private static boolean isCung(int id) {
        return id == 109 || id == 110 || id == 111 || id == 112 || id == 113 || id == 372 || id == 334
                || id == 509 || id == 635 || id == 1132 || id == 1088;
    }

    private static boolean isDao(int id) {
        return id == 104 || id == 105 || id == 106 || id == 107 || id == 108 || id == 373 || id == 335
                || id == 510 || id == 636 || id == 1133 || id == 1089;
    }

    private static boolean isQuat(int id) {
        return id == 119 || id == 120 || id == 121 || id == 122 || id == 123 || id == 374 || id == 336
                || id == 511 || id == 637 || id == 1134 || id == 1090;
    }

    private static boolean isThuong(int id) {
        return id == 1199 || id == 1200 || id == 1202 || id == 1203 || id == 1204 || id == 1205 || id == 1206
                || id == 1207 || id == 1208 || id == 1135;
    }
}
