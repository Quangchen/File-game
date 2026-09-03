public final class AutoHD9x extends Auto {
    private int a;
    private int b;
    private boolean[] c;
    private boolean enteredHang;
    private boolean waitingServerClose;
    private long lastJoinAt;
    private int reportedMap;
    private long stageAt;
  
    public AutoHD9x() {
       this(157);
    }

    public AutoHD9x(int map) {
       super.a();
       super.isHang = true;
       super.zoneID = -2;
       this.b = 0;
       this.c = new boolean[3];
       this.enteredHang = false;
       this.waitingServerClose = false;
       this.lastJoinAt = 0L;
       this.reportedMap = -1;
       this.stageAt = 0L;
       this.setStageByMap(map);
    }
  
    public final void run() {
       if (AutoHD9xManager.handleRunnerLocation(this.enteredHang)) {
          return;
       }

       if (super.isDead()) {
          Auto.autoRemap(true);
       } else if (!this.enteredHang && TileMap.isTruong(TileMap.mapID)) {
          if (System.currentTimeMillis() - this.lastJoinAt >= 2000L) {
             this.lastJoinAt = System.currentTimeMillis();
             GameScr.PickNpc(0, 2, 6);
          }
       } else if (AutoHD9xManager.isHD9xMap(TileMap.mapID)) {
          this.enteredHang = true;
          if (this.waitingServerClose) {
             this.pickUpItem(-1);
             return;
           }
 
           if (super.mapID != TileMap.mapID) {
              this.goMap(AutoHD9xManager.getTravelMap(TileMap.mapID, super.mapID), super.zoneID, super.k, super.l);
              return;
           }

          if (this.reportedMap != super.mapID) {
             this.reportedMap = super.mapID;
             AutoHD9xManager.reportReachedTarget(super.mapID);
          }
           this.runInHang();
        } else if (super.mapID != TileMap.mapID) {
           this.goMap(AutoHD9xManager.getTravelMap(TileMap.mapID, super.mapID), super.zoneID, super.k, super.l);
        } else {
          this.runInHang();
       }
    }

    private void runInHang() {
          Char var2 = Char.getMyChar();
          if (!Auto.isAnThan()) {
             label387: {
                Mob var3;
                int var5;
                Mob var6;
                if ((var3 = var2.mobFocus) == null || var3.h == 0 || !var3.isBoss || System.currentTimeMillis() - super.x > 5000L) {
                   AutoHD9x var10 = this;
                   MyVector var4 = GameScr.vMobAttack;
                   var5 = 0;
 
                   Mob var10000;
                   while(true) {
                      if (var5 >= var4.size()) {
                         var10000 = null;
                         break;
                      }
 
                      if ((var6 = (Mob)var4.elementAt(var5)) != null && var6.hp > 0 && var6.h != 0 && var6.h != 1 && var6.id == var10.a && var6.levelBoss == var10.b) {
                         var10000 = var6;
                         break;
                      }
 
                      ++var5;
                   }
 
                   var3 = var10000;
                    if (var10000 == null && System.currentTimeMillis() - this.stageAt > 3000L && !this.d()) {
                       this.c[this.a - 198] = true;
                       if (this.c[0] && this.c[1] && this.c[2]) {
                          if (this.b == 0) {
                             this.b = 4;
                             this.c = new boolean[3];
                             this.setStageByMap(157);
                          } else if (AutoHD9xManager.isRoundActive()) {
                             this.waitingServerClose = true;
                          } else {
                             Code var19 = Code.instance;
                             Code.backToInstance();
                          }

                          break label387;
                       }
 
                      switch (this.a) {
                         case 198:
                            this.setStageByMap(157);
                            break label387;
                         case 199:
                            this.setStageByMap(158);
                            break label387;
                         case 200:
                            this.setStageByMap(159);
                         default:
                            break label387;
                      }
                   }
                }
 
                if (Char.tickDanhTheoNhom && GameScr.vParty.size() > 0 && var2.nClass.classId == 6 && var2.cHP > 0) {
                   for(int var11 = 0; var11 < var2.vSkillFight.size(); ++var11) {
                      Skill var13;
                      if ((var13 = (Skill)var2.vSkillFight.elementAt(var11)) != null && var13.template.type == 4) {
                         if (!var13.isCooldown()) {
                            for(int var14 = 0; var14 < GameScr.vParty.size(); ++var14) {
                               Party var7;
                               if ((var7 = (Party)GameScr.vParty.elementAt(var14)).a != var2.charID && var7.f != null && var7.f.cHP <= 0) {
                                   try {
                                       Char var8 = var7.f;
                                       if (Math.abs(var2.cx - var8.cx) > 50 || Math.abs(var2.cy - var8.cy) > 50) {
                                           Char.charMove(var8.cx, var8.cy);
                                       }
                                       
                                       Thread.sleep(500L);
                                       Service.getInstance().buffLive(var7.a);
                                       var13.lastTimeUseThisSkill = System.currentTimeMillis();
                                       var13.paintCanNotUseSkill = true;
                                       var2.b(GameScr.skillPaints[var13.template.id], 0);
                                       Thread.sleep(1000L);
                                       break label387;
                                   } catch (InterruptedException ex) {
                                       
                                   }
                               }
                            }
                         }
                         break;
                      }
                   }
                }
 
                if (Auto.selectSkill != null && var3 != null && var3.isBoss && var3.id == this.a && var3.levelBoss == this.b) {
                   Skill var12;
                   if ((var12 = Auto.selectSkill).isCooldown() && Char.tickAutoBuff) {
                      label371: {
                          try {
                              var5 = 0;
                              
                              Skill var15;
                              label266:
                              while(true) {
                                  if (var5 >= var2.vSkillFight.size()) {
                                      break label371;
                                  }
                                  
                                  if ((var15 = (Skill)var2.vSkillFight.elementAt(var5)) != null && System.currentTimeMillis() - var15.lastTimeUseThisSkill >= (long)var15.coolDown - 300L) {
                                      if (var15.template.type == 2) {
                                          if ((var2.d == null && Char.dk || !Auto.isPhanThanSkillId(var15.template.id)) && (Char.dl || var15.template.id != 31) && (var15.template.id != 15 || var2.cHP < var2.cMaxHP * Char.aHpValue / 100)) {
                                              int var16 = (int)(System.currentTimeMillis() / 1000L);
                                              int var17 = 0;
                                              
                                              while(true) {
                                                  if (var17 >= var2.vEff.size()) {
                                                      break label266;
                                                  }
                                                  
                                                  Effect var9;
                                                  if ((var9 = (Effect)var2.vEff.elementAt(var17)) != null && (var9.e.c == var15.template.iconId || var15.template.id == 58 && var9.e.b == 7) && var9.c - (var16 - var9.b) >= 2) {
                                                      break;
                                                  }
                                                  
                                                  ++var17;
                                              }
                                          }
                                      } else if (var15.template.type == 3 && var3.levelBoss == 0 && var3.hp > var3.maxHp / 2) {
                                          if (var15.template.id != 4 || Char.dm && var2.cHP < var2.cMaxHP * Char.aHpValue / 100) {
                                              break;
                                          }
                                      } else if ((var15.template.id == 7 || var15.template.id == 16 || var15.template.id == 25 || var15.template.id == 34 || var15.template.id == 43) && (var3.levelBoss != 0 || var3.hp >= var3.maxHp / 2) && (var15.template.id != 7 && var15.template.id != 16 || !var3.p) && (var15.template.id != 25 && var15.template.id != 34 || var3.q) && (var15.template.id != 43 || var3.r)) {
                                          break;
                                      }
                                  }
                                  
                                  ++var5;
                              }
                              
                              var12 = var15;
                              Thread.sleep(500L);
                          } catch (InterruptedException ex) {
                              
                          }
                      }
                   }
 
                   if ((var12.template.type == 1 || var12.template.type == 3) && (Res.e(var2.cx - var3.cx) > var12.dx || Res.e(var2.cy - var3.cy) > var12.dy)) {
                      this.c(var3);
                   }
 
                   Service.getInstance().selectSkill(var12.template.id);
                   if (var12.template.type == 2) {
                      Service.getInstance().r();
                   } else {
                      Auto.v.removeAllElements();
                      Auto.w.removeAllElements();
                      if (var3 != null) {
                         Auto.v.addElement(var3);
 
                         for(var5 = 0; var5 < GameScr.vMobAttack.size() && Auto.v.size() + Auto.w.size() < var12.maxFight; ++var5) {
                            if ((var6 = (Mob)GameScr.vMobAttack.elementAt(var5)).h != 0 && var6.h != 1 && !var6.equals(var3) && var3.cx - 100 <= var6.cx && var6.cx <= var3.cx + 100 && var3.cy - 50 <= var6.cy && var6.cy <= var3.cy + 50) {
                               Auto.v.addElement(var6);
                            }
                         }
                      }
 
                      Service.getInstance().a(Auto.v, Auto.w, 1);
                   }
 
                   if (System.currentTimeMillis() - var12.lastTimeUseThisSkill >= (long)var12.coolDown) {
                      var12.lastTimeUseThisSkill = System.currentTimeMillis();
                      var12.paintCanNotUseSkill = true;
                      if (!Code.isBangSkill) {
                         var2.b(GameScr.skillPaints[var12.template.id], 0);
                      }
                   }
 
                   super.x = System.currentTimeMillis();
                   if (var12.template.id == 15) {
                       try {
                           Thread.sleep(2000L);
                       } catch (InterruptedException ex) {
                           
                       }
                   }
                }
             }
          }
 
          this.pickUpItem(-1);
    }

    public final void syncTargetWithMap() {
       this.setStageByMap(super.mapID);
    }

    private void setStageByMap(int map) {
       this.stageAt = System.currentTimeMillis();
       if (map == 158) {
          super.mapID = 158;
          this.a = 200;
       } else if (map == 159) {
          super.mapID = 159;
          this.a = 198;
       } else {
          super.mapID = 157;
          this.a = 199;
       }
    }
 
    public final String toString() {
       return "Hang 9x cấp " + this.b;
    }
 }
 
