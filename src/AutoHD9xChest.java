public final class AutoHD9xChest extends Auto {

    private static final int SAFE_BOSS_DISTANCE = 120;
    private final int targetMap;
    private boolean enteredHang;
    private boolean reportedTarget;
    private long lastJoinAt;

    public AutoHD9xChest(int map) {
        super.a();
        this.targetMap = map < 157 || map > 159 ? 157 : map;
        super.mapID = this.targetMap;
        super.zoneID = -2;
        super.isHang = true;
        this.enteredHang = false;
        this.reportedTarget = false;
        this.lastJoinAt = 0L;
    }

    public final void run() {
        if (AutoHD9xManager.handleRunnerLocation(this.enteredHang)) {
            return;
        }

        if (this.isDead()) {
            Auto.autoRemap(true);
            return;
        }

        if (AutoHD9xManager.isHD9xMap(TileMap.mapID)) {
            this.enteredHang = true;
        }

        if (!this.enteredHang) {
            this.joinHang();
            return;
        }

        if (TileMap.mapID != this.targetMap) {
            this.goMap(AutoHD9xManager.getTravelMap(TileMap.mapID, this.targetMap), -2, -1, -1);
            return;
        }

        if (!this.reportedTarget) {
            this.reportedTarget = true;
            AutoHD9xManager.reportReachedTarget(this.targetMap);
        }

        Char p = Char.getMyChar();
        Mob mob = this.findNormalMob(p);

        if (mob == null) {
            p.mobFocus = null;
        } else {
            p.mobFocus = mob;
            this.attackNormalMob(p, mob);
        }

        this.pickUpItem(-1);
    }

    private void joinHang() {
        if (TileMap.isTruong(TileMap.mapID)) {
            if (System.currentTimeMillis() - this.lastJoinAt >= 2000L) {
                this.lastJoinAt = System.currentTimeMillis();
                GameScr.PickNpc(0, 2, 6);
            }

            return;
        }

        Auto.goTruongIfNeeded();
    }

    private Mob findNormalMob(Char p) {
        Mob selected = p.mobFocus;

        if (!this.isSafeNormalMob(selected)) {
            selected = null;
        }

        int bestDistance = -1;

        for (int i = 0; i < GameScr.vMobAttack.size(); i++) {
            Mob mob = (Mob) GameScr.vMobAttack.elementAt(i);

            if (this.isSafeNormalMob(mob)) {
                int distance = Res.distance(p.cx, p.cy, mob.cx, mob.cy);

                if (selected == null || bestDistance < 0 || distance < bestDistance) {
                    selected = mob;
                    bestDistance = distance;
                }
            }
        }

        return selected;
    }

    private boolean isSafeNormalMob(Mob mob) {
        return mob != null && mob.hp > 0 && mob.h != 0 && mob.h != 1 && !mob.isBoss && !this.isNearBoss(mob.cx, mob.cy);
    }

    private boolean isNearBoss(int x, int y) {
        for (int i = 0; i < GameScr.vMobAttack.size(); i++) {
            Mob mob = (Mob) GameScr.vMobAttack.elementAt(i);

            if (mob != null && mob.isBoss && mob.hp > 0 && mob.h != 0 && mob.h != 1
                    && Res.distance(x, y, mob.cx, mob.cy) <= SAFE_BOSS_DISTANCE) {
                return true;
            }
        }

        return false;
    }

    private void attackNormalMob(Char p, Mob focus) {
        Skill skill = this.getReadyAttackSkill(p);

        if (skill == null) {
            return;
        }

        if ((skill.template.type == 1 || skill.template.type == 3)
                && (Res.e(p.cx - focus.cx) > skill.dx || Res.e(p.cy - focus.cy) > skill.dy)) {
            this.c(focus);
            return;
        }

        Service.getInstance().selectSkill(skill.template.id);
        Auto.v.removeAllElements();
        Auto.w.removeAllElements();
        Auto.v.addElement(focus);

        for (int i = 0; i < GameScr.vMobAttack.size() && Auto.v.size() < skill.maxFight; i++) {
            Mob mob = (Mob) GameScr.vMobAttack.elementAt(i);

            if (!focus.equals(mob) && this.isSafeNormalMob(mob)
                    && focus.cx - 100 <= mob.cx && mob.cx <= focus.cx + 100
                    && focus.cy - 50 <= mob.cy && mob.cy <= focus.cy + 50) {
                Auto.v.addElement(mob);
            }
        }

        Service.getInstance().a(Auto.v, Auto.w, 1);
        skill.lastTimeUseThisSkill = System.currentTimeMillis();
        skill.paintCanNotUseSkill = true;

        if (!Code.isBangSkill) {
            p.b(GameScr.skillPaints[skill.template.id], 0);
        }

        super.x = System.currentTimeMillis();
    }

    private Skill getReadyAttackSkill(Char p) {
        if (Auto.selectSkill != null && !Auto.selectSkill.isCooldown()
                && (Auto.selectSkill.template.type == 1 || Auto.selectSkill.template.type == 3)) {
            return Auto.selectSkill;
        }

        for (int i = 0; i < p.vSkillFight.size(); i++) {
            Skill skill = (Skill) p.vSkillFight.elementAt(i);

            if (skill != null && !skill.isCooldown()
                    && (skill.template.type == 1 || skill.template.type == 3)) {
                return skill;
            }
        }

        return null;
    }

    public final int getTargetMap() {
        return this.targetMap;
    }

    public final String toString() {
        return "HD9x farm rương map " + this.targetMap;
    }
}
