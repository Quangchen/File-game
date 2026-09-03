
public abstract class Auto {

    public boolean g;
    public int mapID;
    public int zoneID;
    public boolean isHang;
    public int k;
    public int l;
    private int a;
    private int b;
    public int m;
    public int startLuong;
    public long n;
    public long o;
    public Auto instance;
    public static boolean q;
    public static Skill selectSkill;
    public static boolean isHetMP;
    public static MyVector t = new MyVector();
    private static MyVector c = new MyVector();
    public static int u = 0;
    private static boolean isRecentlyRevived = false;
    private static long lastTimeWaitRevive = -1L;
    public static MyVector v = new MyVector();
    public static MyVector w = new MyVector();
    protected long x = 0L;
    protected long y = 0L;
    protected long z = 0L;
    protected boolean aa = false;
    private static MyVector f = new MyVector();
    private static long lastTimeChangeZone = 0L;
    private static boolean pendingLuuToaDoTruong = false;
    private static long lastLuuToaDoTruong = 0L;
    private String stuckStatus = null;
    private long stuckStatusTime = 0L;
    private long lastTeleMobAttackAt = 0L;

    public Auto() {
    }

    public static void a(Mob var0) {
        if (var0.isBoss || var0.h != 0 && var0.levelBoss != 3 && var0.maxHp != var0.getMobTemplate().f) {
            if (!var0.isBoss && var0.levelBoss == 0) {
                if (var0.maxHp == 10 * var0.getMobTemplate().f) {
                    var0.levelBoss = 1;
                } else {
                    if (var0.maxHp != 100 * var0.getMobTemplate().f && var0.id != 89) {
                        return;
                    }

                    var0.levelBoss = 2;
                }
            }

            if (!t.contains(var0)) {
                t.addElement(var0);
            }
        }

    }

    public static void b(Mob var0) {
        t.removeElement(var0);
    }

    public static void g() {
        t.removeAllElements();
    }

    public static void b(Char var0) {
        if (var0 != Char.getMyChar()) {
            if (c.contains(var0)) {
                if (var0.cTypePk != 3 && var0.da != Char.getMyChar().charID) {
                    c.removeElement(var0);
                    return;
                }
            } else if (var0.cTypePk == 3 || var0.da == Char.getMyChar().charID) {
                c.addElement(var0);
                if (LockGame.b && Res.e(Char.getMyChar().cx - var0.cx) <= 300 && Res.e(Char.getMyChar().cy - var0.cy) <= 300) {
                    LockGame.d();
                }
            }
        }

    }

    public static void h() {
        c.removeAllElements();
    }

    public void a() {
        (new Thread(new CountUpItem())).start();
        this.mapID = -1;
        this.zoneID = -1;
        this.isHang = false;
        this.instance = null;
        this.m = Char.getMyChar().yen;
        this.startLuong = Char.getMyChar().luong;
        this.n = Char.getMyChar().cEXP;
        this.o = System.currentTimeMillis();
        this.g = false;
        Code.r = -1;
        Code.v = 0;
        q = Char.getMyChar().isHuman;
        selectSkill = Char.getMyChar().selectSkill;
        this.resetStuckGuard();
        this.b();
    }

    protected void b() {
        isHetMP = false;
        Code.ab = System.currentTimeMillis();
    }

    protected static boolean checkDead(Char var0) {
        return var0.cHP <= 0 || var0.statusMe == 14 || var0.statusMe == 5;
    }

    protected final boolean isDead() {
        return checkDead(Char.getMyChar());
    }

    protected final void goMap(int idMap, int zoneID, int var3, int var4) {
        if ((idMap < 139 || idMap > 148) && TileMap.mapID >= 139 && TileMap.mapID <= 148) {
            tuSat();
        } else {
            if (TileMap.isLangCo(idMap) && Char.getMyChar().hieuChien > 0) {
                idMap = 23;
                zoneID = u;
                var4 = -1;
                var3 = -1;
                if (Char.getMyChar().cTypePk != 3) {
                    this.aa = true;
                    Service.getInstance().z(3);
                }
            }

            if (TileMap.mapID != idMap) {
                if (!TileMap.direction(idMap)) {
                    if (TileMap.isLangCo(idMap) || TileMap.isLangTT(idMap)) {
                        NinjaUtil.sleep(1L);
                    }
                    return;
                }

                NinjaUtil.sleep(1L);
            }

            if (this.acceptCurrentLttZone(idMap, zoneID)) {
                return;
            }

            if (zoneID == -1) {
                if (Code.attackChangeZone) {
                    int[] var10001 = Code.w;
                    Code.v = 0;
                    changeZone(this.zoneID = var10001[0]);
                } else {
                    this.b(zoneID);
                }
            } else if (zoneID >= 0) {
                changeZone(zoneID);
            }

            if (var3 > 0 && var4 > 0) {
                if (this instanceof AutoTuDanh || this instanceof AutoPKBoss) {
                    Char.charMove(var3, var4);
                    return;
                }

                this.c(findNearMob(var3, var4));
            }
        }

    }

    private boolean acceptCurrentLttZone(int targetMap, int targetZone) {
        if (this instanceof AutoUp
                && TileMap.isLangTT(TileMap.mapID)
                && TileMap.isLangTT(targetMap)
                && TileMap.mapID == targetMap
                && targetZone >= 0
                && TileMap.zoneID != targetZone) {
            this.zoneID = TileMap.zoneID;
            this.markAutoProgress(null);
            return true;
        }

        return false;
    }

    protected static void tuSat() {
        Char p = Char.getMyChar();
        if (!Char.hasItem(37) && !Char.hasItem(35)) {
            Npc var1;
            if ((var1 = GameScr.findNpc(13)) != null && Math.abs(var1.cx - p.cx) <= 400 && Math.abs(var1.cy - p.cy) <= 400) {
                Char.charMove(var1.cx > 400 ? var1.cx - 400 : var1.cx + 400, var1.cy);
            }

            Service.getInstance().openUIZone();
        } else {
            Char.charMove(p.cx, TileMap.d);
        }

        long var3 = System.currentTimeMillis();

        while (p.cHP > 0 && System.currentTimeMillis() - var3 < 5000L) {
            NinjaUtil.sleep(100L);
        }

    }

    protected static void autoRemap(boolean waitRevive) {
        Char var1 = Char.getMyChar();
        if (waitRevive) {
            if (isRecentlyRevived) {
                if (System.currentTimeMillis() - lastTimeWaitRevive < 2000L) {
                    return;
                }

                isRecentlyRevived = false;
            } else if (Char.tickDanhTheoNhom && GameScr.vParty.size() > 0) {
                for (int var3 = 0; var3 < GameScr.vParty.size(); ++var3) {
                    Party var2;
                    if ((var2 = (Party) GameScr.vParty.elementAt(var3)).a != var1.charID && var2.f != null && var2.f.cHP > 0 && var2.f.nClass.classId == 6) {
                        GameScr.chatPopup("Chờ hồi sinh!");
                        lastTimeWaitRevive = System.currentTimeMillis();
                        isRecentlyRevived = true;
                        return;
                    }
                }
            }
        }

        t.removeAllElements();
        isHetMP = false;
        LockGame.a = true;
        if (ChatCommandExtend.c && Char.getMyChar().luong > 0) {
            Service.getInstance().l();
        } else {
            Service.getInstance().returnTownFromDead();
        }

        LockGame.a = false;
    }

    protected static void changeZone(int var0) {
        if (TileMap.zoneID != var0) {
            Npc var1 = GameScr.findNpc(13);
            int var2 = -1;
            if (var1 != null && var1.statusMe != 15) {
                if (Math.abs(var1.cx - Char.getMyChar().cx) > 22 || Math.abs(var1.cy - Char.getMyChar().cy) > 22) {
                    Char.charMove(var1.cx, var1.cy);
                }
            } else {
                if (TileMap.mapID != 99 && TileMap.mapID != 103 && TileMap.mapID != 134 && TileMap.mapID != 135 && TileMap.mapID != 136 && TileMap.mapID != 137) {
                    return;
                }

                if ((var2 = Char.getIndexItemById(37)) < 0 && (var2 = Char.getIndexItemById(35)) < 0) {
                    return;
                }
            }

            if (System.currentTimeMillis() - lastTimeChangeZone >= Code.thoiGianChoChuyenKhu * 1000) {
                Service.getInstance().requestChangeZone(var0, var2);
                TileMap.g();
                lastTimeChangeZone = System.currentTimeMillis();
                NinjaUtil.sleep(100L);
            }
        }

    }

    protected final void b(int var1) {
        if (!this.g || Code.g == null || Char.getMyChar().charName.equals(Code.g)) {
            GameScr var2 = GameScr.getInstance();
            Npc var3 = GameScr.findNpc(13);
            int var4 = -1;
            if (var3 != null && var3.statusMe != 15) {
                if (Math.abs(var3.cx - Char.getMyChar().cx) > 22 || Math.abs(var3.cy - Char.getMyChar().cy) > 22) {
                    Char.charMove(var3.cx, var3.cy);
                }
            } else {
                if (TileMap.mapID != 99 && TileMap.mapID != 103 && TileMap.mapID != 134 && TileMap.mapID != 135 && TileMap.mapID != 136 && TileMap.mapID != 137) {
                    this.zoneID = TileMap.zoneID;
                    lastTimeChangeZone = System.currentTimeMillis();
                    return;
                }

                if ((var4 = Char.getIndexItemById(37)) < 0 && (var4 = Char.getIndexItemById(35)) < 0) {
                    this.zoneID = TileMap.zoneID;
                    lastTimeChangeZone = System.currentTimeMillis();
                    return;
                }
            }

            if (System.currentTimeMillis() - lastTimeChangeZone >= 3000L) {
                Service.getInstance().openUIZone();
                LockGame.e();
                int var7 = -1;
                if (var1 < 0) {
                    var1 = var2.zones.length - 1;
                } else if (var1 >= var2.zones.length) {
                    var1 = 0;
                }

                if (this instanceof AutoTaThu || this instanceof AutoTaThuSolo) {
                    var7 = (var1 / 5 + 1) * 5 % var2.zones.length;
                } else if (!Char.tickDanhQuaiThuong) {
                    var7 = (var1 + 1) % var2.zones.length;
                } else {
                    int var5 = -1;

                    for (int var6 = (var1 + 1) % var2.zones.length; var6 != var1; var6 = (var6 + 1) % var2.zones.length) {
                        if (var5 == -1 || var2.zones[var6] < var5) {
                            var7 = var6;
                            var5 = var2.zones[var6];
                        }
                    }
                }

                Service.getInstance().requestChangeZone(var7, var4);
                this.zoneID = var7;
                TileMap.g();
                if (this.e()) {
                    Service.getInstance().k("khu " + var7);
                }

                lastTimeChangeZone = System.currentTimeMillis();
                NinjaUtil.sleep(100L);
            }
        }

    }

    protected static int getMobTemplateServerId(Mob var0) {
        if (var0 == null || Mob.mobTemplates == null || var0.id < 0 || var0.id >= Mob.mobTemplates.length || Mob.mobTemplates[var0.id] == null) {
            return -1;
        }

        return Mob.mobTemplates[var0.id].e;
    }

    protected static boolean isTargetMobId(Mob var0, int var1) {
        return var1 < 0 || var0 != null && (var0.id == var1 || getMobTemplateServerId(var0) == var1);
    }

    private static boolean a(Mob var0, int var1) {
        if (var0.id == 202 && var0.h == 8) {
            return false;
        } else {
            return isTargetMobId(var0, var1);
        }
    }

    private static boolean a(int var0, int var1) {
        return var1 < 0 || var0 == 0 && (var1 & 1) > 0 || var0 == 1 && (var1 & 2) > 0 || var0 == 2 && (var1 & 4) > 0 || var0 == 3 && (var1 & 8) > 0;
    }

    protected static boolean isDynamicBoss(Mob var0) {
        return var0 != null && (var0.id == 209 || var0.id == 210 || getMobTemplateServerId(var0) == 209 || getMobTemplateServerId(var0) == 210);
    }

    protected static int getMobAttackX(Mob var0) {
        return isDynamicBoss(var0) ? var0.curX : var0.cx;
    }

    protected static int getMobAttackY(Mob var0) {
        return isDynamicBoss(var0) ? var0.curY : var0.cy;
    }

    protected static int getMobStandY(Mob var0) {
        return TileMap.d(getMobAttackX(var0), getMobAttackY(var0));
    }

    public final int a(boolean var1, boolean var2, boolean var3, boolean var4) {
        if (this.isHang) {
            return -1;
        } else {
            int var5 = 0;
            if (var1) {
                var5 = 1;
            }

            if (var2) {
                var5 |= 2;
            }

            if (var3) {
                var5 |= 4;
            }

            return var5;
        }
    }

    protected static boolean a(Char var0, Char var1) {
        return var1.statusMe != 14 && var1.statusMe != 5 && var1.statusMe != 15 && (var1.cTypePk == 3 || var0.cTypePk == 3 || var1.cTypePk == 1 && var0.cTypePk == 1 || var1.cTypePk == 5 && var0.cTypePk == 4 || var1.cTypePk == 4 && var0.cTypePk == 5 || var0.da >= 0 && var0.da == var1.charID || var0.cz >= 0 && var0.cz == var1.charID || var1.da >= 0 && var1.da == var0.charID);
    }

    protected final void c(Mob var1) {
        if (var1 != null) {
            int var2 = getMobAttackX(var1);
            int var3 = isDynamicBoss(var1) ? getMobStandY(var1) : var1.cy;
            Char var4 = Char.getMyChar();
            if (TileMap.mapID == 35) {
                if (var1.cx == 1428 && var1.cy == 528) {
                    var2 = 1452;
                    var3 = 552;
                } else if (var1.cx == 1284 && var1.cy == 528) {
                    var2 = 1308;
                    var3 = 552;
                } else if (var1.cx == 1836 && var1.cy == 648) {
                    var2 = 1812;
                    var3 = 672;
                }
            } else if (TileMap.mapID == 37) {
                if ((var1.cx == 876 || var1.cx == 900) && var1.cy == 408) {
                    var2 = 900;
                    var3 = 432;
                } else if ((var1.cx == 828 || var1.cx == 852) && var1.cy == 360) {
                    var2 = 852;
                    var3 = 384;
                } else if ((var1.cx == 924 || var1.cx == 876) && var1.cy == 624) {
                    var2 = 924;
                    var3 = 648;
                } else if (var1.cx == 732 && var1.cy == 600 || var1.cx == 756 && var1.cy == 576) {
                    var2 = 756;
                    var3 = 600;
                }
            }

            if (Char.d(var2, var3)) {
                this.k = this.a;
                this.l = this.b;
                this.k = var4.cx;
                this.l = var4.cy;
                var4.mobFocus = var1;

                try {
                    NinjaUtil.sleep(1L);
                    return;
                } catch (Exception var6) {
                    return;
                }
            }

            var4.mobFocus = null;
        }

    }

    protected final boolean teleMobForAttack(Mob mob, Skill skill) {
        if (!Code.teleTarget || mob == null || skill == null) {
            return false;
        }

        if (skill.template.type != 1 && skill.template.type != 3) {
            return false;
        }

        Char me = Char.getMyChar();
        int x = getMobAttackX(mob);
        int y = getMobAttackY(mob);
        if (Res.e(me.cx - x) <= skill.dx + 30 && Res.e(me.cy - y) <= skill.dy + 30) {
            return true;
        }

        long now = System.currentTimeMillis();
        if (now - this.lastTeleMobAttackAt < 450L) {
            return true;
        }

        this.lastTeleMobAttackAt = now;
        this.c(mob);
        return me.mobFocus == mob;
    }

    protected static void d(Char var0) {
        if (var0 != null) {
            Char var1 = Char.getMyChar();
            Char.charMove(var0.cx, TileMap.d(var0.cx, var0.cy));
            var1.charFocus = var0;
            NinjaUtil.sleep(100L);
        }

    }

    public static void a(SkillPaint var0) {
        if (v.size() > 0 || w.size() > 0) {
            EffectPaint[] var1 = new EffectPaint[v.size() + w.size()];

            int var2;
            for (var2 = 0; var2 < v.size(); ++var2) {
                var1[var2] = new EffectPaint();
                var1[var2].d = GameScr.efs[var0.id - 1];
                var1[var2].b = (Mob) v.elementAt(var2);
            }

            for (var2 = 0; var2 < w.size(); ++var2) {
                var1[var2 + v.size()] = new EffectPaint();
                var1[var2 + v.size()].d = GameScr.efs[var0.id - 1];
                var1[var2 + v.size()].c = (Char) w.elementAt(var2);
            }

            if (var1.length > 1) {
                mResources var5 = new mResources();
                if (var1[0].b != null) {
                    var5 = new mResources(var1[0].b.curX, var1[0].b.curY);
                } else if (var1[0].c != null) {
                    var5 = new mResources(var1[0].c.cx, var1[0].c.cy);
                }

                MyVector var4 = new MyVector();

                for (int var3 = 1; var3 < var1.length; ++var3) {
                    if (var1[var3].b != null) {
                        var4.addElement(new mResources(var1[var3].b.curX, var1[var3].b.curY));
                    } else if (var1[var3].c != null) {
                        var4.addElement(new mResources(var1[var3].c.cx, var1[var3].c.cy));
                    }

                    if (var3 > 5) {
                        break;
                    }
                }

                Lightning.a(var4, var5, Char.getMyChar().w());
            }

            Char.getMyChar().gd = var1;
        }

    }

    private boolean e() {
        return this.g && GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a == Char.getMyChar().charID;
    }

    protected final boolean k() {
        return this.g && GameScr.vParty.size() > 0 && ((Party) GameScr.vParty.firstElement()).a != Char.getMyChar().charID;
    }

    private void changeOtherZone() {
        if (this instanceof AutoUp && TileMap.isLangTT(TileMap.mapID) && TileMap.isLangTT(this.mapID)) {
            this.zoneID = TileMap.zoneID;
            this.markAutoProgress(null);
            return;
        }

        if (Code.attackChangeZone) {
            changeZone(this.zoneID = Code.w[Code.v = (Code.v + 1) % Code.w.length]);
            if (this.e()) {
                Service.getInstance().k("khu " + this.zoneID);
                return;
            }
        } else {
            this.b(TileMap.zoneID);
        }

    }

    private boolean needNeMob(int var1, int var2, int var3) {
        if (var1 >= 4) {
            return false;
        } else {
            for (int var4 = 0; var4 < t.size(); ++var4) {
                Mob var5;
                if ((var5 = (Mob) t.elementAt(var4)).levelBoss != 0 && var5.hp > 0 && var5.h != 0) {
                    boolean var10000;
                    label71:
                    {
                        if (var5.levelBoss == 3) {
                            if (this instanceof AutoTaThu || this instanceof AutoTaThuSolo || this instanceof AutoTuDanh) {
                                var10000 = false;
                                break label71;
                            }
                        } else if ((!var5.isBoss || (var1 & 6) == 6) && (var5.levelBoss != 1 || (var1 & 2) != 0) && (var5.levelBoss != 2 || (var1 & 4) != 0)) {
                            var10000 = false;
                            break label71;
                        }

                        var10000 = true;
                    }

                    if (var10000 && Res.e(var2 - var5.cx) <= 90 && Res.e(var3 - var5.cy) <= 90) {
                        return true;
                    }
                } else {
                    t.removeElement(var5);
                    var4 += 255;
                }
            }

            return false;
        }
    }

    private boolean needNePK(int var1, int var2) {
        if (Char.tickNePK && !(this instanceof AutoTaThu) && !(this instanceof AutoTaThuSolo)) {
            for (int var3 = 0; var3 < c.size(); ++var3) {
                Char var4;
                if (!checkDead(var4 = (Char) c.elementAt(var3)) && Res.e(var1 - var4.cx) <= 300 && Res.e(var2 - var4.cy) <= 300) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    protected static Mob findNearMob(int curX, int curY) {
        Mob mob = null;
        Char p = Char.getMyChar();
        int rLeft = curX - p.getNSkillSelect() - 10;// - 10
        int rRight = curX + p.getNSkillSelect() + 10;// +10
        int rBottom = curY - p.getCSkillSelect() - (p.nClass.classId != 0 && p.nClass.classId != 1 && p.nClass.classId != 3 && p.nClass.classId != 5 && p.nClass.classId != 7 ? 0 : 40);
        int rTop;
        if ((rTop = curY + p.getCSkillSelect()) > curY + 30) { // curY + 30
            rTop = curY + 30;
//            rTop = curY + 30;
        }

        int distanceX = -1;

        for (int index = 0; index < GameScr.vMobAttack.size(); ++index) {
            Mob mobTemp = (Mob) GameScr.vMobAttack.elementAt(index);
            int rangeX = Math.abs(curX - mobTemp.curX);
            int rangeY = Math.abs(curY - mobTemp.curY);
            rangeX = rangeX > rangeY ? rangeX : rangeY;
            if (rLeft <= mobTemp.curX && mobTemp.curX <= rRight && rBottom <= mobTemp.curY && mobTemp.curY <= rTop && mobTemp.h != 0 && mobTemp.h != 1 && (distanceX == -1 || rangeX < distanceX)) {
                mob = mobTemp;
                distanceX = rangeX;
            }
        }

        return mob;
    }

    protected final void goToNextMob(int var1, boolean var2) {
        if (Code.r < 0 || Code.r >= Code.s.size()) {
            Code.r = 0;
        }

        while (true) {
            int var3 = ((Integer) Code.s.elementAt(Code.r)).intValue();
            int var4 = ((Integer) Code.t.elementAt(Code.r)).intValue();
            Mob mob = findNearMob(var3, var4);
            if (!this.needNeMob(var1, var3, var4) && !this.needNePK(var3, var4) && mob != null && !this.needNeMob(var1, mob.curX, mob.curY)) {
                this.a = Char.getMyChar().cx;
                this.b = Char.getMyChar().cy;
                Char.charMove(var3, var4);
                Char.getMyChar().mobFocus = mob;
                Service.getInstance().b(mob.m);
                NinjaUtil.sleep(100L);
                return;
            }

            if (++Code.r == Code.s.size()) {
                Code.r = 0;
                if (Char.tickChuyenMapHetBoss && var2) {
                    this.changeOtherZone();
                }
            }
        }
    }

    protected Mob a(Char p, int var2, int var3, Char var4, boolean var5) {
        if (Code.attackChangePosition && Code.s.size() > 0) {
            this.goToNextMob(var3, var5);
            return findNearMob(p.cx, p.cy);
        } else {
            Char var7 = var4;
            int var6 = var3;
            int var25 = var2;
            var3 = p.cy;
            var2 = p.cx;
            Auto var22 = this;
            int var8 = -1;
            int var9 = -1;
            int var10 = -1;
            Mob var11 = null;
            MyVector var12 = GameScr.vMobAttack;
            int var13 = 0;

            Mob var10000;
            int var19;
            while (true) {
                if (var13 >= var12.size()) {
                    var10000 = var11;
                    break;
                }

                Mob var14;
                if ((var14 = (Mob) var12.elementAt(var13)) != null && var14.hp > 0 && var14.h != 0 && var14.h != 1 && a(var14, var25) && a(var14.levelBoss, var6) && (var7 == null || var7.charID == Char.getMyChar().charID || Res.distance(var14.cx, var14.cy, var7.cx, var7.cy) <= 1000) && !var22.needNeMob(var6, var14.curX, var14.curY) && !var22.needNePK(var14.curX, var14.curY)) {
                    if (var22.isHang) {
                        if (var22.mapID == 157 || var22.mapID == 158 || var22.mapID == 159) {
                            var10000 = var14;
                            break;
                        }

                        if (var8 == -1 || var14.levelBoss < var10 || var14.cy < var8 || var14.cy == var8 && var14.cx < var9) {
                            var10 = var14.levelBoss;
                            var8 = var14.cy;
                            var9 = var14.cx;
                            var11 = var14;
                        }
                    } else if (Code.m == -1 || Res.distance(Code.n, Code.o, var14.cx, var14.cy) <= Code.m) {
                        var19 = var6;
                        int var18 = var25;
                        MyVector var17 = var12;
                        Mob var16 = var14;
                        int var15 = 0;

                        int var26;
                        for (var26 = 0; var26 < var17.size(); ++var26) {
                            Mob var21;
                            if ((var21 = (Mob) var17.elementAt(var26)) != null && var21.hp > 0 && var21.h != 0 && var21.h != 1 && a(var16, var18) && a(var16.levelBoss, var19) && Res.e(var21.curX - var16.curX) <= 100 && Res.e(var21.curY - var16.curY) <= 50) {
                                ++var15;
                            }
                        }

                        if (var15 > selectSkill.maxFight) {
                            var15 = selectSkill.maxFight;
                        }

                        var15 = var16.levelBoss << 4 | var15 & 15;
                        var26 = var7 != null && var7.charID != Char.getMyChar().charID ? Res.distance(var7.cx, var7.cy, var14.cx, var14.cy) : Res.distance(var2, var3, var14.cx, var14.cy);
                        if (var15 > var10 || var15 == var10 && var26 < var8) {
                            var10 = var15;
                            var8 = var26;
                            var11 = var14;
                        }
                    }
                }

                ++var13;
            }

            if (var10000 != null) {
                this.c(var10000);
                return var10000;
            } else {
                if (System.currentTimeMillis() - this.x > 1000L && !this.d()) {
                    if (this.isHang) {
                        if ((var19 = TileMap.h(TileMap.mapID)) >= 0) {
                            this.mapID = var19;
                        }

                        this.k = this.l = -1;
                        NinjaUtil.sleep(500L);
                    } else if (var5 && Char.tickChuyenMapHetBoss) {
                        this.changeOtherZone();
                    }
                }

                return null;
            }
        }
    }

    protected final Char a(Char var1, int var2) {
        for (int var3 = 0; var3 < GameScr.vCharInMap.size(); ++var3) {
            Char var4;
            if ((var4 = (Char) GameScr.vCharInMap.elementAt(var3)) != null && !checkDead(var4) && !this.needNeMob(var2, var4.cx, var4.cy) && !this.needNePK(var4.cx, var4.cy) && !Code.d(var4.charName) && SavePK.c(var4.charName) && (var4.cTypePk == 1 || var4.da == var1.charID || var1.hieuChien < 15)) {
                return var4;
            }
        }

        return null;
    }

    protected final Char e(Char var1) {
        Char var10000 = var1;
        int var3 = var1.cy;
        int var2 = var1.cx;
        var1 = null;
        Char var4 = Char.getMyChar();
        int var5 = var2 - var4.getNSkillSelect() - 10;
        int var6 = var2 + var4.getNSkillSelect() + 10;
        int var7 = var3 - var4.getCSkillSelect() - (var4.nClass.classId != 0 && var4.nClass.classId != 1 && var4.nClass.classId != 3 && var4.nClass.classId != 5 && var4.nClass.classId != 7 ? 0 : 40);
        int var8 = var3 + var4.getCSkillSelect() + (var4.nClass.classId != 0 && var4.nClass.classId != 1 && var4.nClass.classId != 3 && var4.nClass.classId != 5 && var4.nClass.classId != 7 ? 0 : 40);
        int var9 = -1;

        for (int var10 = 0; var10 < GameScr.vCharInMap.size(); ++var10) {
            Char var11 = (Char) GameScr.vCharInMap.elementAt(var10);
            int var12 = Math.abs(var2 - var11.cx);
            int var13 = Math.abs(var3 - var11.cy);
            var12 = var12 > var13 ? var12 : var13;
            if (var11 != null && var5 <= var11.cx && var11.cx <= var6 && var7 <= var11.cy && var11.cy <= var8 && !checkDead(var11) && a(var4, var11) && !Code.d(var11.charName) && (var9 == -1 || var12 < var9)) {
                var1 = var11;
                var9 = var12;
            }
        }

        return var10000.charFocus = var1;
    }

    protected final void attack(int var1, int var2) {
        Char p = Char.getMyChar();
        Mob mobFocus = p.mobFocus;
        int var17;
        int var21;
        for (int i = 0; i < GameScr.keySkill.length; i++) {
            if (GameScr.keySkill[i] != null && (GameScr.keySkill[i].template.type == 1 || GameScr.keySkill[i].template.type == 3) && !GameScr.keySkill[i].isCooldown()) {
                Attack(GameScr.keySkill[i], var1, var2);
            }
        }
        for (int i = 0; i < GameScr.arrSkill.length; i++) {
            if (GameScr.arrSkill[i] != null && (GameScr.arrSkill[i].template.type == 1 || GameScr.arrSkill[i].template.type == 3) && !GameScr.arrSkill[i].isCooldown()) {
                Attack(GameScr.arrSkill[i], var1, var2);
            }
        }
        if (selectSkill != null && !selectSkill.isCooldown()) {
            Attack(selectSkill, var1, var2);
        } else if (selectSkill != null && (Char.tickAutoBuff || this instanceof As50)) {
            label811:
            {
                var17 = 0;
                Skill skill;
                label597:
                while (true) {
                    if (var17 >= p.vSkillFight.size()) {
                        break label811;
                    }
                    if ((skill = (Skill) p.vSkillFight.elementAt(var17)) != null && System.currentTimeMillis() - skill.lastTimeUseThisSkill >= (long) skill.coolDown - 300L) {
                        if (skill.template.type == 2) {
                            if ((p.d == null && Char.dk || !isPhanThanSkillId(skill.template.id)) && (Char.dl || skill.template.id != 31) && (skill.template.id != 15 || !Char.dm || p.cHP < p.cMaxHP * Char.aHpValue / 100 && p.isHuman) && (skill.template.id != 6 || p.isHuman)) {
                                var21 = (int) (System.currentTimeMillis() / 1000L);
                                int var22 = 0;

                                while (true) {
                                    if (var22 >= p.vEff.size()) {
                                        break label597;
                                    }

                                    Effect var12;
                                    if ((var12 = (Effect) p.vEff.elementAt(var22)) != null && (var12.e.c == skill.template.iconId || skill.template.id == 58 && var12.e.b == 7) && var12.c - (var21 - var12.b) >= 2) {
                                        break;
                                    }

                                    ++var22;
                                }
                            }
                        } else if (skill.template.type == 3 && mobFocus != null && mobFocus.levelBoss == 0 && mobFocus.hp > mobFocus.maxHp / 2) {
                            if (skill.template.id != 4 || Char.dm && p.cHP < p.cMaxHP * Char.aHpValue / 100) {
                                break;
                            }
                        } else if ((skill.template.id == 7 || skill.template.id == 16 || skill.template.id == 25 || skill.template.id == 34 || skill.template.id == 43) && mobFocus != null && (mobFocus.levelBoss != 0 || mobFocus.hp >= mobFocus.maxHp / 2) && (skill.template.id != 7 && skill.template.id != 16 || !mobFocus.p) && (skill.template.id != 25 && skill.template.id != 34 || mobFocus.q) && (skill.template.id != 43 || mobFocus.r)) {
                            break;
                        }
                    }
                    ++var17;
                }
                NinjaUtil.sleep(500);
                Attack(skill, var1, var2);
            }
        }
    }

    private void Attack(Skill skill, int var1, int var2) {
        Skill currentSkill = skill;
        Char p = Char.getMyChar();
        Mob mobFocus = p.mobFocus;
        Char charFocus = p.charFocus;
        Char tempChar;
        int var17;
        int var20;
        int var21;
        boolean var8 = charFocus != null && SavePK.c(charFocus.charName);
        if (!isAnThan()) {
            Char var4 = this.g && GameScr.vParty.size() > 0 ? ((Party) GameScr.vParty.firstElement()).f : null;
            boolean needChangeOtherZone = !this.g || Code.g == null || p.charName.equals(Code.g) && LockGame.ae();
            Char var7 = p.charFocus;
            if (Code.an && (var7 == null || Code.d(var7.charName) || !SavePK.c(var7.charName) && !a(p, var7)) && (var7 = this.a(p, var2)) == null) {
                var7 = this.e(p);
            }
            if (var7 == null && this.aa) {
                Service.getInstance().z(0);
                this.aa = false;
            }

            if (Code.an && p.hieuChien >= 5 && System.currentTimeMillis() - this.z > 5000L) {
                Item var9;
                if ((var9 = Char.getItemByID(257)) != null && var9.template.id == 257) {
                    Service.getInstance().useItem(var9.indexUI);
                }

                this.z = System.currentTimeMillis();
            }

            if (Code.attackChangePosition && Code.s.size() > 0 && Code.r < 0) {
                this.goToNextMob(var2, needChangeOtherZone);
            } else {
                boolean needGoNextPosition = false;
                if (this.needNeMob(var2, p.cx, p.cy) || this.needNePK(p.cx, p.cy) || mobFocus != null && this.needNeMob(var2, mobFocus.curX, mobFocus.curY)) {
                    GameScr.chatPopup("Né");
                    if (Char.tickChuyenMapHetBoss && needChangeOtherZone) {
                        this.changeOtherZone();
                        return;
                    }

                    needGoNextPosition = true;
                    mobFocus = null;
                }

                if (mobFocus == null || mobFocus.h == 0 || !a(mobFocus, var1) || !a(mobFocus.levelBoss, var2) || System.currentTimeMillis() - this.x > 5000L) {
                    mobFocus = this.a(p, var1, var2, var4, needChangeOtherZone);
                }

                if (mobFocus == null && needGoNextPosition && this.a > 0 && this.b > 0) {
                    Char.charMove(this.a, this.b);
                }
                Char var11;
                if (Char.tickDanhTheoNhom && GameScr.vParty.size() > 0 && p.nClass.classId == 6 && p.cHP > 0) {
                    for (int var13 = 0; var13 < p.vSkillFight.size(); ++var13) {
                        Skill var15;
                        if ((var15 = (Skill) p.vSkillFight.elementAt(var13)) != null && var15.template.type == 4) {
                            if (!var15.isCooldown()) {
                                for (var20 = 0; var20 < GameScr.vParty.size(); ++var20) {
                                    Party var10;
                                    if ((var10 = (Party) GameScr.vParty.elementAt(var20)).a != p.charID && var10.f != null && var10.f.cHP <= 0) {
                                        var11 = var10.f;
                                        if (Math.abs(p.cx - var11.cx) > 50 || Math.abs(p.cy - var11.cy) > 50) {
                                            Char.charMove(var11.cx, var11.cy);
                                        }

                                        NinjaUtil.sleep(500L);
                                        Service.getInstance().buffLive(var10.a);
                                        var15.lastTimeUseThisSkill = System.currentTimeMillis();
                                        var15.paintCanNotUseSkill = true;
                                        p.b(GameScr.skillPaints[var15.template.id], 0);
                                        NinjaUtil.sleep(1000L);
                                        return;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }

                if (Char.tickSanTATL && !this.isHang && (mobFocus == null || mobFocus.levelBoss == 0 && (var2 & 6) != 0)) {
                    boolean var14 = (var2 & 2) != 0;
                    needChangeOtherZone = (var2 & 4) != 0;

                    for (var20 = 0; var20 < t.size(); ++var20) {
                        Mob var19;
                        if ((var19 = (Mob) t.elementAt(var20)).hp > 0 && var19.h != 0 && var19.h != 1 && !this.needNeMob(var2, var19.curX, var19.curY) && !this.needNePK(var19.curX, var19.curY) && a(var19, var1) && (var14 && var19.levelBoss == 1 || needChangeOtherZone && var19.levelBoss == 2)) {
                            mobFocus = var19;
                            this.c(var19);
                            break;
                        }
                    }
                }
            }

            Service.getInstance().selectSkill(currentSkill.template.id);
            if (currentSkill.template.type == 2) {
                Service.getInstance().r();
            } else {
                Mob var24;
                if (Code.an && charFocus != null && !checkDead(charFocus) && (var8 || a(p, charFocus))) {
                    if (var8) {
                        if ((currentSkill.template.type == 1 || currentSkill.template.type == 3) && (Res.e(p.cx - charFocus.cx) > currentSkill.dx + 30 || Res.e(p.cy - charFocus.cy) > currentSkill.dy + 30) && System.currentTimeMillis() - this.y > 1500L) {//this.y > 1500L
                            d(charFocus);
                            this.y = System.currentTimeMillis();
                        }

                        if (charFocus.da != p.charID && charFocus.cTypePk != 3) {
                            this.aa = true;
                            Service.getInstance().z(3);
                        }
                    }

                    var17 = currentSkill.dx;
                    var20 = currentSkill.dy;
                    v.removeAllElements();
                    w.removeAllElements();
                    w.addElement(charFocus);

                    for (var21 = 0; var21 < GameScr.vCharInMap.size() && v.size() + w.size() < currentSkill.maxFight; ++var21) {
                        if ((tempChar = (Char) GameScr.vCharInMap.elementAt(var21)).cHP > 0 && tempChar.statusMe != 14 && tempChar.statusMe != 5 && tempChar.statusMe != 15 && !tempChar.equals(charFocus) && (tempChar.cTypePk == 3 || p.cTypePk == 3 || tempChar.cTypePk == 1 && p.cTypePk == 1 || p.da >= 0 && p.da == tempChar.charID || p.cz >= 0 && p.cz == tempChar.charID || tempChar.da == p.charID) && !Code.d(tempChar.charName) && charFocus.cx - var17 <= tempChar.cx && tempChar.cx <= charFocus.cx + var17 && charFocus.cy - var20 <= tempChar.cy && tempChar.cy <= charFocus.cy + var20) {
                            w.addElement(tempChar);
                        }
                    }

                    for (var21 = 0; var21 < GameScr.vMobAttack.size() && v.size() + w.size() < currentSkill.maxFight; ++var21) {
                        if ((var24 = (Mob) GameScr.vMobAttack.elementAt(var21)).h != 0 && var24.h != 1 && charFocus.cx - var17 <= var24.curX && var24.curX <= charFocus.cx + var17 && charFocus.cy - var20 <= var24.curY && var24.curY <= charFocus.cy + var20 && a(var24.levelBoss, var2) && isTargetMobId(var24, var1)) {
                            v.addElement(var24);
                        }
                    }

                    Service.getInstance().a(v, w, 2);
                } else {
                    if (mobFocus == null || !isTargetMobId(mobFocus, var1) || !a(mobFocus.levelBoss, var2)) {
                        return;
                    }

                    int focusX = getMobAttackX(mobFocus);
                    int focusY = getMobAttackY(mobFocus);
                    if ((currentSkill.template.type == 1 || currentSkill.template.type == 3) && (Res.e(p.cx - focusX) > currentSkill.dx + 30 || Res.e(p.cy - focusY) > currentSkill.dy + 30)) {
                        if (!this.teleMobForAttack(mobFocus, currentSkill)) {
                            p.mobFocus = null;
                            return;
                        }

                        focusX = getMobAttackX(mobFocus);
                        focusY = getMobAttackY(mobFocus);
                        if (Res.e(p.cx - focusX) > currentSkill.dx + 30 || Res.e(p.cy - focusY) > currentSkill.dy + 30) {
                            return;
                        }
                    }

                    var17 = currentSkill.dx;
                    var20 = currentSkill.dy;
                    v.removeAllElements();
                    w.removeAllElements();
                    v.addElement(mobFocus);

                    for (var21 = 0; var21 < GameScr.vMobAttack.size() && v.size() + w.size() < currentSkill.maxFight; ++var21) {
                        if ((var24 = (Mob) GameScr.vMobAttack.elementAt(var21)).h != 0 && var24.h != 1 && !var24.equals(mobFocus) && focusX - 100 <= getMobAttackX(var24) && getMobAttackX(var24) <= focusX + 100 && focusY - 50 <= getMobAttackY(var24) && getMobAttackY(var24) <= focusY + 50 && a(var24.levelBoss, var2) && isTargetMobId(var24, var1)) {
                            v.addElement(var24);
                        }
                    }

                    for (var21 = 0; var21 < GameScr.vCharInMap.size() && v.size() + w.size() < currentSkill.maxFight; ++var21) {
                        if ((tempChar = (Char) GameScr.vCharInMap.elementAt(var21)).cHP > 0 && tempChar.statusMe != 14 && tempChar.statusMe != 5 && tempChar.statusMe != 15 && (tempChar.cTypePk == 3 || p.cTypePk == 3 || tempChar.cTypePk == 1 && p.cTypePk == 1 || p.da >= 0 && p.da == tempChar.charID || p.cz >= 0 && p.cz == tempChar.charID || tempChar.da == p.charID) && !Code.d(tempChar.charName) && mobFocus.curX - var17 <= tempChar.cx && tempChar.cx <= mobFocus.curX + var17 && mobFocus.curY - var20 <= tempChar.cy && tempChar.cy <= mobFocus.curY + var20) {
                            w.addElement(tempChar);
                        }
                    }

                    Service.getInstance().a(v, w, 1);
                }
            }

            if (System.currentTimeMillis() - currentSkill.lastTimeUseThisSkill >= (long) (currentSkill.coolDown - 100)) {
                currentSkill.lastTimeUseThisSkill = System.currentTimeMillis();
                currentSkill.paintCanNotUseSkill = true;
                if (!Code.isBangSkill) {
                    p.b(GameScr.skillPaints[currentSkill.template.id], 0);
                }
            }

            this.x = System.currentTimeMillis();
            if (currentSkill.template.id == 15) {
                NinjaUtil.sleep(2000L);
            }
        }

    }

    protected boolean d() {
        if (!(this instanceof AutoTaThu) && !(this instanceof AutoTaThuSolo) && !Code.isHutVP) {
            Char var1 = Char.getMyChar();
            int var2 = Code.khoangCachNhat < 0 ? -1 : Code.khoangCachNhat;

            for (int var3 = 0; var3 < GameScr.vItemMap.size(); ++var3) {
                ItemMap var4;
                if (!(var4 = (ItemMap) GameScr.vItemMap.elementAt(var3)).isPickedUp && (var1.nClass.classId == 1 && var4.template.id == 218 || var4.template.type == 19 || Code.isItemCanPickUp(var4.template)) && Char.canPickItemTemplate(var4.template) && (var2 < 0 || Res.distance(var1.cx, var1.cy, var4.xEnd, var4.yEnd) < var2) && !this.needNePK(var4.x, var4.y)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    protected final void pickUpItem(int var1) {
        if (!Code.isHutVP) {
            Char var2 = Char.getMyChar();
            if (!isAnThan()) {
                f.removeAllElements();
                int var3 = this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false);

                int var4;
                for (var4 = 0; var4 < GameScr.vItemMap.size(); ++var4) {
                    ItemMap itemMap;
                    if (!(itemMap = (ItemMap) GameScr.vItemMap.elementAt(var4)).isPickedUp && (var2.nClass.classId == 1 && itemMap.template.id == 218 || Code.isItemCanPickUp(itemMap.template) || itemMap.template.id == var1) && Char.canPickItemTemplate(itemMap.template) && !this.needNeMob(var3, itemMap.xEnd, itemMap.yEnd) && !this.needNePK(itemMap.xEnd, itemMap.yEnd) && (Code.khoangCachNhat < 0 || Res.distance(var2.cx, var2.cy, itemMap.xEnd, itemMap.yEnd) < Code.khoangCachNhat)) {
                        f.addElement(itemMap);
                    }
                }

                if (f.size() > 0) {
                    var4 = var2.cx;
                    int var9 = var2.cy;
                    Mob var8 = var2.mobFocus;

                    label58:
                    for (var3 = 0; var3 < f.size(); ++var3) {
                        ItemMap var6;
                        Char.charMove((var6 = (ItemMap) f.elementAt(var3)).xEnd, TileMap.d(var6.xEnd, var6.yEnd));
                        var2.itemFocus = var6;

                        for (int var7 = 0; var7 < 4 && var6.status != 2 && !var6.isPickedUp; ++var7) {
                            Service.getInstance().pickItem(var6.itemMapID);
                            if (LockGame.c()) {
                                break;
                            }

                            if (this.needNePK(var2.cx, var2.cy) || var2.cHP <= 0) {
                                break label58;
                            }
                        }

                        var6.isPickedUp = true;
                        var6.lastTimePickup = System.currentTimeMillis();
                        this.markAutoProgress(null);
                    }

                    Char.charMove(var4, var9);
                    var2.mobFocus = var8;
                }
            }
        }

    }

    protected final void l() {
        if (TileMap.mapID != 22) {
            this.goMap(22, -2, -1, -1);
        } else {
            Char var1 = Char.getMyChar();
            NinjaUtil.sleep(200L);

            for (int var2 = 0; var2 < var1.vSkillFight.size(); ++var2) {
                Skill var3;
                if ((var3 = (Skill) var1.vSkillFight.elementAt(var2)) != null && !var3.isCooldown() && isPhanThanSkillId(var3.template.id)) {
                    Service.getInstance().selectSkill(var3.template.id);
                    Service.getInstance().r();
                    LockGame.ab();
                    break;
                }
            }

            GameScr.goNPC(12);
            LockGame.ab();
            Service.getInstance().getTask(12, 3);
            LockGame.ab();
        }

    }

    protected static boolean isAnThan() {
        Char var0;
        if ((var0 = Char.getMyChar()).isHuman && var0.cHP < var0.cMaxHP) {
            for (int var1 = 0; var1 < var0.vEff.size(); ++var1) {
                Effect var2;
                if ((var2 = (Effect) var0.vEff.elementAt(var1)) != null && var2.e.b == 12) {
                    return true;
                }
            }
        }

        return false;
    }

    protected static boolean isPhanThanSkillId(int id) {
        return id >= 67 && id <= 72 || id == 97;
    }

    public static boolean goTruongIfNeeded() {
        if (TileMap.isVDMQ(TileMap.mapID)) {
            Code.n();
            sleep(1000L);
            autoRemap(true);
            return true;
        }

        if (TileMap.isLangCo(TileMap.mapID) || TileMap.isLangTT(TileMap.mapID) || !TileMap.isTruong(TileMap.mapID)) {
            if (TileMap.isLang(TileMap.mapID) && !TileMap.isLangCo(TileMap.mapID) && !TileMap.isLangTT(TileMap.mapID)) {
                TileMap.direction(1);
                sleep(500L);
                return true;
            }

            tuSat();
            return true;
        }

        return false;
    }

    private static boolean isLangThuong(int mapId) {
        return TileMap.isLang(mapId) && !TileMap.isLangCo(mapId) && !TileMap.isLangTT(mapId);
    }

    public static void luuToaDoHienTai() {
        GameScr.goNPC(5);
        Service.getInstance().openMenu(5);
        Service.getInstance().menu(5, 1, 0);
    }

    public static boolean goTruongAndLuuToaDoIfNeeded() {
        if (isLangThuong(TileMap.mapID)) {
            pendingLuuToaDoTruong = true;
            GameScr.chatPopup("Về trường lưu tọa độ");
            TileMap.direction(1);
            sleep(500L);
            return true;
        }

        if (pendingLuuToaDoTruong && TileMap.isTruong(TileMap.mapID)) {
            if (System.currentTimeMillis() - lastLuuToaDoTruong < 3000L) {
                return true;
            }

            GameScr.chatPopup("Lưu tọa độ về trường");
            luuToaDoHienTai();
            lastLuuToaDoTruong = System.currentTimeMillis();
            pendingLuuToaDoTruong = false;
            sleep(800L);
            return true;
        }

        return false;
    }

    protected abstract void run();

    public abstract String toString();

    private void resetStuckGuard() {
        this.stuckStatus = null;
        this.stuckStatusTime = 0L;
    }

    protected final void markAutoProgress(String status) {
        if (status != null) {
            this.stuckStatus = status;
            this.stuckStatusTime = System.currentTimeMillis();
        }
    }

    private boolean canUseStuckGuard() {
        return this instanceof AutoNVHN
                || this instanceof AutoTaThu
                || this instanceof AutoTaThuSolo
                || this instanceof AutoDV
                || this instanceof AutoTanSat
                || this instanceof AutoUp
                || this instanceof AutoUpLevel;
    }

    public final boolean checkStuckGuard() {
        if (!this.canUseStuckGuard()) {
            return true;
        }

        try {
            if (TileMap.isWaypointStuck()) {
                TileMap.retryWaypointIfStuck();
                return false;
            }
        } catch (Exception e) {
        }

        return true;
    }

    public final String getStuckStatusText() {
        if (!this.canUseStuckGuard()) {
            return null;
        }

        long now = System.currentTimeMillis();
        if (this.stuckStatus != null && now - this.stuckStatusTime < 15000L) {
            return this.stuckStatus;
        }

        return null;
    }

    public static void sleep(long var0) {
        try {
            NinjaUtil.sleep(var0);
        } catch (Exception var3) {
        }

    }
}
