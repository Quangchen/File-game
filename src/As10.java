
// Source code is decompiled from instance .class file using FernFlower decompiler.
public class As10 extends Auto {
    public As10() {
       super.a();
    }

    private int getTaskIndex(Char me) {
       try {
          return me.taskMaint == null ? -1 : me.taskMaint.a;
       } catch (Exception var3) {
          return -2;
       }
    }

    private void waitTaskChange(int oldTaskId, int oldTaskIndex, long maxWait) {
       long start = System.currentTimeMillis();

       while(System.currentTimeMillis() - start < maxWait) {
          Char me = Char.getMyChar();
          if (me == null || me.ctaskId != oldTaskId || this.getTaskIndex(me) != oldTaskIndex) {
             return;
          }

          sleep(80L);
       }

    }

    private void waitClassChange(int oldClassId, long maxWait) {
       long start = System.currentTimeMillis();

       while(System.currentTimeMillis() - start < maxWait) {
          Char me = Char.getMyChar();
          int classId = me != null && me.nClass != null ? me.nClass.classId : 0;
          if (classId > 0 && classId != oldClassId) {
             return;
          }

          sleep(100L);
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

    protected final void receiveCurrentTask(Char me, byte mapId, byte npcId) {
       if (TileMap.mapID != mapId) {
          super.goMap(mapId, -2, -1, -1);
       } else {
          int oldTaskId = me.ctaskId;
          int oldTaskIndex = this.getTaskIndex(me);
          GameScr.PickNpc(npcId, 0, 0);
          Service.getInstance().getTask(npcId, 0);
          this.waitTaskChange(oldTaskId, oldTaskIndex, 800L);
          super.zoneID = -1;
       }

    }

    private boolean finishByLuong(Char me) {
       try {
          int cost = me.ctaskId * 500;
          if (me.luong < cost) {
             GameScr.chatPopup("Không đủ lượng. Cần " + cost + ", hiện có " + me.luong);
             Code.tatAuto();
             return false;
          }

          if (TileMap.mapID != 22) {
             super.goMap(22, -2, -1, -1);
             return false;
          }

          Npc npc = GameScr.findNpc(37);
          if (npc == null) {
             GameScr.chatPopup("Không thấy NPC 37 ở map 22");
             return false;
          }

          if (Math.abs(me.cx - npc.cx) > 30 || Math.abs(me.cy - npc.cy) > 30) {
             Char.charMove(npc.cx, npc.cy);
             return false;
          }

          Char.getMyChar().npcFocus = npc;
          int oldTaskId = me.ctaskId;
          int oldTaskIndex = this.getTaskIndex(me);
          Service.getInstance().openMenu(37);
          sleep(80L);
          int menuIndex = this.findFinishTaskMenuIndex(cost);
          if (menuIndex == -1) {
             GameScr.chatPopup("Không thấy nút hoàn thành nhiệm vụ");
             return false;
          }

          Service.getInstance().menu(37, menuIndex, 0);
          this.waitTaskChange(oldTaskId, oldTaskIndex, 900L);
          super.zoneID = -1;
          return true;
       } catch (Exception var7) {
          return false;
       }
    }

    protected final byte getClassMap(Char me) {
       if (me == null || me.nClass == null) {
          return -1;
       }

       switch(me.nClass.classId) {
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

    protected final byte getClassNpc(Char me) {
       if (me == null || me.nClass == null) {
          return -1;
       }

       switch(me.nClass.classId) {
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

    protected final void receiveHeadmasterTask() {
       Char me = Char.getMyChar();
       if (me != null && me.nClass != null && me.nClass.classId > 0) {
          byte mapId = this.getClassMap(me);
          byte npcId = this.getClassNpc(me);
          if (mapId >= 0 && npcId >= 0) {
             this.receiveHeadmasterTask(mapId, npcId);
          }
       }

    }

    protected final void receiveHeadmasterTask(byte mapId, byte npcId) {
       Char me = Char.getMyChar();
       if (me == null || mapId < 0 || npcId < 0) {
          return;
       }

       if (TileMap.mapID != mapId) {
          super.goMap(mapId, -2, -1, -1);
          return;
       }

       int oldTaskId = me.ctaskId;
       int oldTaskIndex = this.getTaskIndex(me);

       for (int i = 0; i < 3; ++i) {
          GameScr.PickNpc(npcId, 0, 0);
          Service.getInstance().getTask(npcId, 0);
          this.waitTaskChange(oldTaskId, oldTaskIndex, 700L);
          me = Char.getMyChar();
          if (me == null || me.ctaskId != oldTaskId || this.getTaskIndex(me) != oldTaskIndex) {
             break;
          }

          sleep(150L);
       }

       super.zoneID = -1;
    }
 
    public boolean isDone(Char var1) {
       return var1.ctaskId >= 9;
    }
 
    public void doTask(Char var1, byte var2, byte var3) {
       switch (var1.ctaskId) {
          case 0:
             if (var1.taskMaint.a == 0) {
                GameScr.PickNpc(var3, 2, 0);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 1) {
                GameScr.PickNpc(var3, 2, 0);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 2) {
                GameScr.PickNpc(var3, 8, 0);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 3) {
                GameScr.PickNpc(var3, 3, 2);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 4) {
                GameScr.PickNpc(var3, 4, 0);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 5) {
                GameScr.PickNpc(var3, 0, 0);
                LockGame.o();
                return;
             }
 
             return;
          case 1:
             if (var1.taskMaint.a == 0) {
                GameScr.PickNpc(var3, 0, 0);
                Service.getInstance().getTask(var3, 2);
                Service.getInstance().getTask(var3, 1);
                Service.getInstance().getTask(var3, 3);
                Service.getInstance().getTask(var3, 3);
                Service.getInstance().getTask(var3, 1);
                Service.getInstance().getTask(var3, 2);
                LockGame.o();
                return;
             }
             break;
          case 2:
             if (var1.taskMaint.a == 0) {
                if (var1.arrItemBag[0] != null) {
                   Service.getInstance().useItem(0);
                }
 
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 1) {
                if (TileMap.mapID == 22) {
                   this.pickUpItem(-1);
                   this.attack(0, 1);
                   return;
                }
 
                this.goMap(22, -1, -1, -1);
                return;
             }
             break;
          case 3:
             if (var1.taskMaint.a == 0) {
           try {
               Thread.sleep(2000L);
               GameScr.PickNpc(4, 0, 0);
               Service.getInstance().buyItem(9, 0, 3);
               LockGame.o();
               return;
           } catch (InterruptedException ex) {
               
           }
             }
 
             if (var1.taskMaint.a == 1) {
                if (var1.arrItemBag[0] != null) {
                   Service.getInstance().useItem(0);
                }
 
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 2) {
                if (TileMap.mapID == 23) {
                   this.pickUpItem(-1);
                   this.attack(1, 1);
                   return;
                }
 
                this.goMap(23, -1, -1, -1);
                return;
             }
 
             if (var1.taskMaint.a == 3) {
                if (TileMap.mapID == 23) {
                   this.pickUpItem(-1);
                   this.attack(2, 1);
                   return;
                }
 
                this.goMap(23, -1, -1, -1);
                return;
             }
             break;
          case 4:
             if (var1.taskMaint.a == 0) {
                if (TileMap.mapID == 21) {
                   this.pickUpItem(-1);
                   this.attack(-1, 1);
                   return;
                }
 
                this.goMap(21, -1, -1, -1);
                return;
             }
 
             if (var1.taskMaint.a == 1) {
                if (TileMap.mapID == 21) {
                   this.pickUpItem(209);
                   this.attack(3, 1);
                   return;
                }
 
                this.goMap(21, -1, -1, -1);
                return;
             }
 
             if (var1.taskMaint.a == 2) {
                if (TileMap.mapID == 23) {
                   this.pickUpItem(210);
                   this.attack(4, 1);
                   return;
                }
 
                this.goMap(23, -1, -1, -1);
                return;
             }
             break;
          case 5:
             if (var1.taskMaint.a == 0) {
                if (TileMap.mapID == 20) {
                   this.pickUpItem(-1);
                   this.attack(3, 1);
                   return;
                }
 
                this.goMap(20, -1, -1, -1);
                return;
             }
 
             if (var1.taskMaint.a == 1) {
                if (TileMap.mapID == 20) {
                   this.pickUpItem(211);
                   this.attack(54, 1);
                   return;
                }
 
                this.goMap(20, -1, -1, -1);
                return;
             }
             break;
          case 6:
             if (var1.taskMaint.a == 0) {
                if (TileMap.mapID == 26) {
                   this.pickUpItem(-1);
                   this.attack(-1, 1);
                   return;
                }
 
                this.goMap(26, -1, -1, -1);
                return;
             }
 
             if (var1.taskMaint.a == 1) {
           try {
               super.goMap(2, -2, -1, -1);
               Thread.sleep(500L);
               return;
           } catch (InterruptedException ex) {
               
           }
             }
 
             if (var1.taskMaint.a == 2) {
           try {
               super.goMap(71, -2, -1, -1);
               Thread.sleep(500L);
               return;
           } catch (InterruptedException ex) {
               
           }
             }
 
             if (var1.taskMaint.a == 3) {
           try {
               super.goMap(26, -2, -1, -1);
               Thread.sleep(500L);
               return;
           } catch (InterruptedException ex) {
               
           }
             }
             break;
          case 7:
             if (var1.taskMaint.a == 0) {
                if (TileMap.mapID == 71) {
                   this.pickUpItem(-1);
                   this.attack(-1, 1);
                   return;
                }
 
                this.goMap(71, -1, -1, -1);
                return;
             }
 
             if (var1.taskMaint.a == 1) {
                super.goMap(var2, -2, -1, -1);
                GameScr.PickNpc(var3, 0, 0);
                Service.getInstance().getTask(var3, 1);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 2) {
                Service.getInstance().getTask(var3, 0);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 3) {
                Service.getInstance().getTask(var3, 1);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 4) {
                Service.getInstance().getTask(var3, 1);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 5) {
                Service.getInstance().getTask(var3, 2);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 6) {
                GameScr.PickNpc(var3, 0, 0);
                Service.getInstance().getTask(var3, 2);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 7) {
                Service.getInstance().getTask(var3, 0);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 8) {
                Service.getInstance().getTask(var3, 2);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 9) {
                Service.getInstance().getTask(var3, 2);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 10) {
                Service.getInstance().getTask(var3, 1);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 11) {
                GameScr.PickNpc(var3, 0, 0);
                Service.getInstance().getTask(var3, 0);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 12) {
                Service.getInstance().getTask(var3, 1);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 13) {
                Service.getInstance().getTask(var3, 2);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 14) {
                Service.getInstance().getTask(var3, 2);
                LockGame.o();
                return;
             }
 
             if (var1.taskMaint.a == 15) {
                Service.getInstance().getTask(var3, 1);
                LockGame.o();
                return;
             }
             break;
          case 8:
             if (var1.taskMaint.a == 0) {
                if (TileMap.mapID == 26) {
                   this.pickUpItem(-1);
                   this.attack(-1, 1);
                   return;
                }
 
                this.goMap(26, -1, -1, -1);
                return;
             }
 
             super.goMap(var2, -2, -1, -1);
             GameScr.goNPC(var3);
             NpcTemplate var5 = Npc.arrNpcTemplate[var3];
 
             for(int var4 = 0; var4 < var5.menu.length; ++var4) {
                if (var5.menu[var4][0].equals("Nói chuyện")) {
                    try {
                        GameScr.PickNpc(var3, var4, 0);
                        LockGame.o();
                        Thread.sleep(1000L);
                        return;
                    } catch (InterruptedException ex) {
                        
                    }
                }
             }
       }
 
    }
 
    public final void run() {
       Char var1 = Char.getMyChar();
       if (this.isDone(var1)) {
          GameScr.chatPopup("Xong!");
          Code.tatAuto();
       } else {
          byte var2 = GameScr.ad();
          byte var3 = GameScr.ae();
          if (Char.getMyChar().cHP <= 0) {
             Auto.autoRemap(false);
          } else {
             int oldClassId = var1.nClass == null ? 0 : var1.nClass.classId;
             if (var1.ctaskId == 9 && oldClassId == 0) {
                this.doTask(var1, var2, var3);
                this.waitClassChange(oldClassId, 1500L);
                this.receiveHeadmasterTask();
                return;
             }

             if (var1.taskMaint == null) {
                this.receiveCurrentTask(var1, var2, var3);
                return;
             }

             int oldTaskId = var1.ctaskId;
             if (this.finishByLuong(var1)) {
                Char me = Char.getMyChar();
                if (me != null && me.ctaskId != oldTaskId && me.ctaskId < 9 && me.taskMaint == null) {
                   this.receiveCurrentTask(me, GameScr.ad(), GameScr.ae());
                }
             }
          }
       }
    }
 
    public String toString() {
       return "Auto Nhiệm Vụ 10";
    }
 }
 
