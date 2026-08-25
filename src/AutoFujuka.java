/**
 * Auto chinh phuc THANH DIA FUJUKA (map 168).
 *
 * Co che server (FujukaSanctuary):
 *  - Dungeon solo: 6 phong, moi phong 1 "di ban" (bot) la ban sao cua minh.
 *    Bot co charID am: -101..-106 (phong 1..6).
 *  - Dam vao cua phong -> server tu teleport vao trong danh tay doi (1v1).
 *  - Giet het 6 phong -> ca 6 di ban hoi sinh, don ve vi tri minh (boss tong, danh AoE).
 *  - Moi lan MINH CHET -> con bot phong do +10% mau & +10% damage (vinh vien) => tranh chet.
 *  - Minh dung BINH MAU -> hieu ung hoi mau copy sang ca bot => KHONG dung binh mau.
 *  - Thuong ti le nghich thoi gian con lai => danh nhanh.
 *
 * Cach dung: vao Thanh dia Fujuka (gap NPC Deidara, chon "Thanh dia Fujuka"),
 * CHON 1 skill danh (skill se sang o thanh skill), roi chat:  fuji
 *
 * @author auto
 */
public final class AutoFujuka extends Auto {

    /** Map id cua Thanh dia Fujuka (server: MapName.THANH_DIA_FUJUKA = 168). */
    private static final int MAP_FUJUKA = 168;

    /** Toa do (pixel) cua cua 6 phong = ((minX+maxX)/2, maxY) lay tu server initMap(). */
    private static final int[] DOOR_X = {600, 720, 840, 1080, 1200, 1320};
    private static final int[] DOOR_Y = {504, 456, 504, 504, 456, 504};

    /** Y < nguong nay coi nhu dang O TRONG phong (phong nam tren dinh, y 0..192). */
    private static final int INSIDE_ROOM_Y = 264;

    /** Khoang cach (px) du gan de bat dau danh (trong phong / boss tong vay quanh). */
    private static final int ENGAGE_RANGE = 260;

    /** Ban kinh gom muc tieu cho don AoE khi boss tong. */
    private static final int GATHER_RANGE = 240;

    /** % mau bat dau ne (kite) de khoi chet. */
    private static final int HP_KITE_PERCENT = 30;
    /** % mau du an toan de quay lai danh. */
    private static final int HP_RESUME_PERCENT = 55;

    private boolean kiting;
    private long lastMoveToTarget;
    private boolean warnedNoSkill;

    public AutoFujuka() {
        super.a();
        this.kiting = false;
        this.lastMoveToTarget = 0L;
        this.warnedNoSkill = false;
        GameScr.chatPopup("Auto Thánh địa Fujuka: Bật");
    }

    public final String toString() {
        return "Auto Thanh dia Fujuka";
    }

    protected final void run() {
        // Roi map dungeon (server da dong / da hoan thanh -> ve Lang Shiiba): dung auto.
        if (TileMap.mapID != MAP_FUJUKA) {
            GameScr.chatPopup("Đã rời Thánh địa Fujuka. Dừng auto.");
            Code.auto = null;
            return;
        }

        Char me = Char.getMyChar();

        // Dang chet: server tu hoi sinh tai cua, chi can yeu cau hoi sinh roi cho.
        // KHONG tu sat, KHONG remap (de giu nguyen trong dungeon).
        if (isDead()) {
            Service.getInstance().returnTownFromDead();
            NinjaUtil.sleep(800L);
            return;
        }

        MyVector live = liveEnemies(me);
        if (live.size() == 0) {
            // Chua thay di ban (dang chuyen canh / cho server): cho nhe.
            NinjaUtil.sleep(150L);
            return;
        }

        // ----- CHONG CHET: ne khi mau thap, KHONG dung binh mau -----
        int hp = hpPercent(me);
        if (this.kiting) {
            if (hp >= HP_RESUME_PERCENT) {
                this.kiting = false;
            }
        } else if (hp <= HP_KITE_PERCENT) {
            this.kiting = true;
        }
        if (this.kiting) {
            kiteAway(me, nearest(me, live));
            return;
        }

        // ----- CHIEN DAU / DI CHUYEN -----
        Char near = nearest(me, live);
        int dist = Res.distance(me.cx, me.cy, near.cx, near.cy);

        if (dist <= ENGAGE_RANGE || me.cy < INSIDE_ROOM_Y) {
            // Da o trong phong hoac di ban da vay quanh (boss tong) -> danh.
            attack(me, targetsNear(me, live));
        } else {
            // Dang o hanh lang: di toi cua cua di ban con song co chi so phong nho nhat,
            // dam vao cua -> server teleport vao phong.
            int[] door = doorOfLowestLiveBot(me, live);
            Char.charMove(door[0], door[1]);
            NinjaUtil.sleep(120L);
        }
    }

    // ===================== HELPERS =====================

    /** Danh sach di ban con song (moi Char khac minh, cHP > 0). */
    private MyVector liveEnemies(Char me) {
        MyVector r = new MyVector();
        for (int i = 0; i < GameScr.vCharInMap.size(); ++i) {
            Char c = (Char) GameScr.vCharInMap.elementAt(i);
            if (c == null) {
                continue;
            }
            if (c.charID == me.charID) {
                continue;
            }
            if (c.cHP <= 0) {
                continue;
            }
            r.addElement(c);
        }
        return r;
    }

    /** Di ban gan minh nhat. */
    private Char nearest(Char me, MyVector live) {
        Char best = null;
        int bestD = Integer.MAX_VALUE;
        for (int i = 0; i < live.size(); ++i) {
            Char c = (Char) live.elementAt(i);
            int d = Res.distance(me.cx, me.cy, c.cx, c.cy);
            if (d < bestD) {
                bestD = d;
                best = c;
            }
        }
        return best;
    }

    /** Cac di ban trong ban kinh GATHER_RANGE (de danh AoE nhieu muc tieu). */
    private MyVector targetsNear(Char me, MyVector live) {
        MyVector r = new MyVector();
        for (int i = 0; i < live.size(); ++i) {
            Char c = (Char) live.elementAt(i);
            if (Res.distance(me.cx, me.cy, c.cx, c.cy) <= GATHER_RANGE) {
                r.addElement(c);
            }
        }
        // Luon co it nhat muc tieu gan nhat.
        if (r.size() == 0) {
            Char n = nearest(me, live);
            if (n != null) {
                r.addElement(n);
            }
        }
        return r;
    }

    /** Cua cua di ban con song co chi so phong nho nhat (clear trai -> phai). */
    private int[] doorOfLowestLiveBot(Char me, MyVector live) {
        int bestRoom = 99;
        int bx = -1, by = -1;
        for (int i = 0; i < live.size(); ++i) {
            Char c = (Char) live.elementAt(i);
            int room = (-c.charID) - 101; // -101->0 ... -106->5
            if (room >= 0 && room < 6 && room < bestRoom) {
                bestRoom = room;
                bx = DOOR_X[room];
                by = DOOR_Y[room];
            }
        }
        if (bx == -1) {
            // Du phong: charID khong map duoc -> tien ve cot cua di ban gan nhat.
            Char n = nearest(me, live);
            int x = n.cx;
            if (x < 40) {
                x = 40;
            }
            if (x > 1880) {
                x = 1880;
            }
            return new int[]{x, 504};
        }
        return new int[]{bx, by};
    }

    /** Ne ra xa di ban gan nhat de hoi mau (khong dung binh mau). */
    private void kiteAway(Char me, Char enemy) {
        int tx = me.cx;
        if (enemy != null) {
            tx = (me.cx >= enemy.cx) ? me.cx + 140 : me.cx - 140;
        }
        if (tx < 40) {
            tx = 40;
        }
        if (tx > 1880) {
            tx = 1880;
        }
        int ty = me.cy;
        if (ty < INSIDE_ROOM_Y + 6) {
            ty = INSIDE_ROOM_Y + 36; // o hanh lang van giu trong vung di chuyen
        }
        if (ty > 690) {
            ty = 680;
        }
        Char.charMove(tx, ty);
        NinjaUtil.sleep(120L);
    }

    /** Danh muc tieu bang skill dang chon (Auto.selectSkill), ho tro AoE nhieu muc tieu. */
    private void attack(Char me, MyVector targets) {
        Skill s = Auto.selectSkill;
        if (s == null) {
            if (!this.warnedNoSkill) {
                GameScr.chatPopup("Hãy chọn 1 skill đánh rồi chat: fuji");
                this.warnedNoSkill = true;
            }
            NinjaUtil.sleep(200L);
            return;
        }
        if (targets == null || targets.size() == 0) {
            return;
        }

        if (s.template.type == 2) {
            // Skill quanh than (AoE tu than) -> rat hop boss tong.
            Service.getInstance().selectSkill(s.template.id);
            Service.getInstance().r();
        } else {
            Char t = (Char) targets.elementAt(0);
            // Skill tam xa (1/3): neu ngoai tam thi tien lai gan.
            if ((s.template.type == 1 || s.template.type == 3)
                    && (Res.e(me.cx - t.cx) > s.dx + 30 || Res.e(me.cy - t.cy) > s.dy + 30)
                    && System.currentTimeMillis() - this.lastMoveToTarget > 1200L) {
                Auto.d(t);
                this.lastMoveToTarget = System.currentTimeMillis();
            }
            Auto.v.removeAllElements();
            Auto.w.removeAllElements();
            for (int i = 0; i < targets.size(); ++i) {
                Auto.w.addElement(targets.elementAt(i));
            }
            Service.getInstance().selectSkill(s.template.id);
            Service.getInstance().a((MyVector) Auto.v, (MyVector) Auto.w, (int) 2);
        }

        // Hieu ung "dang hoi chieu" (chi de hien thi, giong cac auto khac).
        if (System.currentTimeMillis() - s.lastTimeUseThisSkill >= (long) s.coolDown + 50L) {
            s.lastTimeUseThisSkill = System.currentTimeMillis();
            s.paintCanNotUseSkill = true;
            if (!Code.isBangSkill) {
                me.b(GameScr.skillPaints[s.template.id], 0);
            }
        }
        super.x = System.currentTimeMillis();
    }

    private int hpPercent(Char me) {
        if (me.cMaxHP <= 0) {
            return 100;
        }
        return (int) ((long) me.cHP * 100L / (long) me.cMaxHP);
    }
}
