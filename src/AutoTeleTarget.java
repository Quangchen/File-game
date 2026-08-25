public final class AutoTeleTarget {
    private static final int NEAR_DISTANCE = 25;
    private static long lastMoveAt;

    private AutoTeleTarget() {
    }

    public static boolean moveToNpc(Npc npc) {
        return npc != null && moveNear(npc.cx, npc.cy);
    }

    public static boolean moveToChar(Char c) {
        return c != null && moveNear(c.cx, c.cy);
    }

    public static boolean moveToMob(Mob mob) {
        return mob != null && moveNear(mob.curX, mob.curY);
    }

    public static boolean moveToItem(ItemMap item) {
        return item != null && moveNear(item.x, item.y);
    }

    public static void selectNpc(Npc npc) {
        if (Code.teleTarget && npc != null) {
            moveToNpc(npc);
            openNpc(npc);
        }
    }

    public static void selectChar(Char c) {
        if (Code.teleTarget) {
            moveToChar(c);
        }
    }

    public static void selectMob(Mob mob) {
        if (Code.teleTarget) {
            moveToMob(mob);
        }
    }

    public static void selectItem(ItemMap item) {
        if (Code.teleTarget) {
            moveToItem(item);
        }
    }

    public static void openNpc(Npc npc) {
        if (npc == null) {
            return;
        }

        if (npc.template.npcTemplateId == 13) {
            InfoDlg.b();
            Service.getInstance().openUIZone();
        } else {
            Service.getInstance().openMenu(npc.template.npcTemplateId);
            InfoDlg.b();
        }
    }

    private static boolean moveNear(int targetX, int targetY) {
        Char me = Char.getMyChar();
        if (!Code.teleTarget || me == null || me.statusMe == 14 || me.statusMe == 5 || me.statusMe == 15) {
            return false;
        }

        if (Res.e(me.cx - targetX) < 60 && Res.e(me.cy - targetY) < 40) {
            return false;
        }

        long now = System.currentTimeMillis();
        if (now - lastMoveAt < 350L) {
            return true;
        }

        lastMoveAt = now;
        int x = targetX + (me.cx <= targetX ? -NEAR_DISTANCE : NEAR_DISTANCE);
        if (x < 24) {
            x = targetX + NEAR_DISTANCE;
        }

        if (x > TileMap.c - 24) {
            x = targetX - NEAR_DISTANCE;
        }

        if (x < 24) {
            x = 24;
        }

        if (x > TileMap.c - 24) {
            x = TileMap.c - 24;
        }

        int[] pos = new int[2];
        if (TileMap.a(x, targetY, pos)) {
            x = pos[0];
            targetY = pos[1];
        } else {
            targetY = TileMap.d(x, targetY);
        }

        return Char.charMove(x, targetY);
    }
}
