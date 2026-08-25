
public final class AutoFinishTask extends Auto {
    private As50 earlyAuto;
    private int classId;

    public AutoFinishTask() {
        super.a();
    }

    public AutoFinishTask(int classId) {
        super.a();
        this.classId = normalizeClassId(classId);
    }

    protected final void run() {
        Char me = Char.getMyChar();
        if (me == null) {
            return;
        }

        if (me.ctaskId < 9 || me.ctaskId == 9 && (me.nClass == null || me.nClass.classId == 0)) {
            runEarlyTask(me);
            return;
        }

        if (me.nClass == null || me.nClass.classId == 0) {
            GameScr.chatPopup("Chưa vào lớp");
            Code.backToInstance();
            return;
        }

        if (isBagFull(me)) {
            GameScr.chatPopup("Hành trang đầy, dừng auto");
            Code.backToInstance();
            return;
        }

        if (me.cHP <= 0) {
            Auto.tuSat();
            return;
        }

        byte mapId = getTaskMap(me);
        byte npcId = getTaskNpc(me);

        if (me.ctaskId == 9 && me.nClass != null && me.nClass.classId > 0) {
            receiveHeadmasterTaskHard(me, mapId, npcId);
            return;
        }

        if (me.taskMaint == null) {
            if (me.ctaskId >= 43) {
                GameScr.chatPopup("Đã hoàn thành nhiệm vụ");
                Code.backToInstance();
                return;
            }

            if (TileMap.mapID != mapId) {
                if (me.ctaskId >= 33) {
                    handleTask33(me);
                } else {
                    this.goMap(mapId, -2, -1, -1);
                }
            } else {
                int oldTaskId = me.ctaskId;
                int oldTaskIndex = getTaskIndex(me);
                GameScr.PickNpc(npcId, 0, 0);
                Service.getInstance().getTask(npcId, 0);
                waitTaskChange(oldTaskId, oldTaskIndex, 800L);
                super.zoneID = -1;
            }
        } else {
            finishByLuong(me);
        }
    }

    private void receiveHeadmasterTaskHard(Char me, byte mapId, byte npcId) {
        if (mapId < 0 || npcId < 0) {
            return;
        }

        if (TileMap.mapID != mapId) {
            this.goMap(mapId, -2, -1, -1);
            return;
        }

        int oldTaskId = me.ctaskId;
        int oldTaskIndex = getTaskIndex(me);

        for (int i = 0; i < 3; ++i) {
            GameScr.PickNpc(npcId, 0, 0);
            Service.getInstance().getTask(npcId, 0);
            waitTaskChange(oldTaskId, oldTaskIndex, 700L);
            me = Char.getMyChar();
            if (me == null || me.ctaskId != oldTaskId || getTaskIndex(me) != oldTaskIndex) {
                break;
            }

            Auto.sleep(150L);
        }

        this.zoneID = -1;
    }

    private void runEarlyTask(Char me) {
        int autoClass = this.classId;
        if (autoClass == 0 && me.nClass != null && me.nClass.classId > 0 && me.nClass.classId <= 7) {
            autoClass = me.nClass.classId;
        }

        if (me.ctaskId == 9 && autoClass == 0) {
            GameScr.chatPopup("ASALL: dùng asallk/t/ku/c/d/q/th để chọn phái");
            Code.backToInstance();
            return;
        }

        if (this.earlyAuto == null || this.classId != autoClass) {
            this.classId = autoClass;
            this.earlyAuto = new As50(autoClass);
        }

        this.earlyAuto.run();
    }

    private int normalizeClassId(int value) {
        return value >= 1 && value <= 7 ? value : 0;
    }

    private byte getTaskMap(Char me) {
        try {
            if (me.ctaskId >= 9 && me.ctaskId <= 15) {
                return getClassMap(me);
            }

            return GameScr.ad();
        } catch (Exception e) {
            return -1;
        }
    }

    private byte getTaskNpc(Char me) {
        try {
            if (me.ctaskId >= 9 && me.ctaskId <= 15) {
                return getClassNpcByTask(me);
            }

            return GameScr.ae();
        } catch (Exception e) {
            return -1;
        }
    }

    private byte getClassMap(Char me) {
        if (me == null || me.nClass == null) {
            return -1;
        }

        switch (me.nClass.classId) {
            case 1:
            case 2:
            case 7:
                return 1;
            case 3:
            case 4:
                return 72;
            case 5:
            case 6:
                return 27;
            default:
                return -1;
        }
    }

    private byte getClassNpc(Char me) {
        if (me == null || me.nClass == null) {
            return -1;
        }

        switch (me.nClass.classId) {
            case 1:
            case 2:
            case 7:
                return 9;
            case 3:
            case 4:
                return 10;
            case 5:
            case 6:
                return 11;
            default:
                return -1;
        }
    }

    private byte getClassNpcByTask(Char me) {
        switch (me.ctaskId) {
            case 9:
                return getClassNpc(me);
            case 10:
            case 11:
            case 12:
                return getClassNpc(me);
            case 13:
                return 0;
            case 14:
                return 2;
            case 15:
                return 1;
            default:
                return -1;
        }
    }

    private void handleTask33(Char me) {
        try {
            if (TileMap.mapID != 22) {
                this.goMap(22, -2, -1, -1);
                return;
            }

            Npc npc = GameScr.findNpc(7);
            if (npc == null) {
                GameScr.chatPopup("Không thấy NPC 7 ở map 22");
                return;
            }

            if (Math.abs(me.cx - npc.cx) > 30 || Math.abs(me.cy - npc.cy) > 30) {
                Char.charMove(npc.cx, npc.cy);
                return;
            }

            Char.getMyChar().npcFocus = npc;
            int oldTaskId = me.ctaskId;
            int oldTaskIndex = getTaskIndex(me);
            Service.getInstance().openMenu(7);
            Auto.sleep(80L);
            Service.getInstance().menu(7, 2, 0);
            waitTaskChange(oldTaskId, oldTaskIndex, 800L);
            this.zoneID = -1;
        } catch (Exception e) {
        }
    }

    private void finishByLuong(Char me) {
        try {
            int cost = me.ctaskId * 500;
            if (me.luong < cost) {
                GameScr.chatPopup("Không đủ lượng. Cần " + cost + ", hiện có " + me.luong);
                Code.backToInstance();
                return;
            }

            if (TileMap.mapID != 22) {
                this.goMap(22, -2, -1, -1);
                return;
            }

            Npc npc = GameScr.findNpc(37);
            if (npc == null) {
                GameScr.chatPopup("Không thấy NPC 37 ở map 22");
                return;
            }

            if (Math.abs(me.cx - npc.cx) > 30 || Math.abs(me.cy - npc.cy) > 30) {
                Char.charMove(npc.cx, npc.cy);
                return;
            }

            Char.getMyChar().npcFocus = npc;
            int oldTaskId = me.ctaskId;
            int oldTaskIndex = getTaskIndex(me);
            Service.getInstance().openMenu(37);
            Auto.sleep(80L);

            int menuIndex = findFinishTaskMenuIndex(cost);
            if (menuIndex == -1) {
                GameScr.chatPopup("Không thấy nút hoàn thành nhiệm vụ");
                return;
            }

            Service.getInstance().menu(37, menuIndex, 0);
            waitTaskChange(oldTaskId, oldTaskIndex, 800L);
            this.zoneID = -1;
        } catch (Exception e) {
        }
    }

    private int getTaskIndex(Char me) {
        try {
            return me.taskMaint == null ? -1 : me.taskMaint.a;
        } catch (Exception e) {
            return -2;
        }
    }

    private void waitTaskChange(int oldTaskId, int oldTaskIndex, long maxWait) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < maxWait) {
            Char me = Char.getMyChar();
            if (me == null) {
                return;
            }

            if (me.ctaskId != oldTaskId || getTaskIndex(me) != oldTaskIndex) {
                return;
            }

            Auto.sleep(80L);
        }
    }

    private int findFinishTaskMenuIndex(int cost) {
        String s1 = "Hoàn thành nhiệm vụ (" + cost + " lượng)";
        String s2 = "hoàn thành nhiệm vụ (" + cost + " lượng)";
        String s3 = "Hoàn thành nhiệm vụ";
        String s4 = "hoàn thành nhiệm vụ";

        int index = GameCanvas.menu.findIndexByCaptionEquals(s1);
        if (index == -1) {
            index = GameCanvas.menu.findIndexByCaptionEquals(s2);
        }
        if (index == -1) {
            index = GameCanvas.menu.findIndexByCaptionContains(s3);
        }
        if (index == -1) {
            index = GameCanvas.menu.findIndexByCaptionContains(s4);
        }

        return index;
    }

    private boolean isBagFull(Char me) {
        if (me.arrItemBag == null) {
            return false;
        }

        for (int i = 0; i < me.arrItemBag.length; ++i) {
            if (me.arrItemBag[i] == null) {
                return false;
            }
        }

        return true;
    }

    public final String toString() {
        return "Auto Hoàn Thành Nhiệm Vụ";
    }
}
