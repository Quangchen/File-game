
public final class AutoPKBoss extends Auto {
    private static final int MOB_MY_HAU_VUONG = 210;
    private int a;
    private int b;
 
    public AutoPKBoss(int var1) {
       super.a();
       super.mapID = var1;
       super.zoneID = -2;
       this.a = 0;
    }
 
    private static boolean a(ItemTemplate var0) {
       if (var0.type == 19) {
          return false;
       } else if (var0.type != 16 && var0.type != 17) {
          if (var0.type == 26) {
             return var0.level >= 6;
          } else if (var0.isTypeBody()) {
             return var0.level >= 70;
          } else if (var0.isTypeTask()) {
             return false;
          } else {
             if (var0.type == 27) {
                if (var0.id == 38 || var0.description.startsWith("Vật phẩm sự kiện") || var0.description.startsWith("Vật phẩm Sự kiện")) {
                   return false;
                }
 
                if (var0.name.startsWith("Sách võ công")) {
                   if (var0.level == 100) {
                      return true;
                   }
 
                   return false;
                }
             }
 
             return true;
          }
       } else {
          return false;
       }
    }
 
    private static void e() {
       Char var0 = Char.getMyChar();
 
       for(int var2 = 0; var2 < GameScr.vItemMap.size(); ++var2) {
          ItemMap var1;
          if (a((var1 = (ItemMap)GameScr.vItemMap.elementAt(var2)).template) && Res.distance(var0.cx, var0.cy, var1.xEnd, var1.yEnd) <= 100 && Char.canPickItemTemplate(var1.template)) {
              try {
                  Service.getInstance().pickItem(var1.itemMapID);
                  Thread.sleep(50L);
              } catch (InterruptedException ex) {
                  
              }
          }
       }
 
    }

    private static boolean isBossLike(Mob var0) {
       return var0 != null && (var0.isBoss || var0.id == MOB_MY_HAU_VUONG || getMobTemplateServerId(var0) == MOB_MY_HAU_VUONG) && var0.hp > 0 && var0.h != 0 && var0.h != 1;
    }

    private static boolean isPkBossTarget(Mob var0) {
       return isBossLike(var0);
    }
 
    protected final boolean d() {
       Char var1 = Char.getMyChar();
 
       for(int var2 = 0; var2 < GameScr.vItemMap.size(); ++var2) {
          ItemMap var3;
          if (a((var3 = (ItemMap)GameScr.vItemMap.elementAt(var2)).template) && Res.distance(var1.cx, var1.cy, var3.xEnd, var3.yEnd) <= 100 && Char.canPickItemTemplate(var3.template)) {
             return true;
          }
       }
 
       return false;
    }
 
    private void f() {
       Char var1 = Char.getMyChar();
       if (!Auto.isAnThan()) {
          Mob var2 = var1.mobFocus;
          Char var3 = var1.charFocus;
          if (Code.an && (var3 == null || Code.c(var3.charName) || !Auto.a(var1, var3))) {
             var3 = this.e(var1);
          }
 
          int var4;
          if (var3 == null && var2 == null || !isPkBossTarget(var2) || System.currentTimeMillis() - super.x > 60000L) {
             int var10000 = var1.cx;
             var10000 = var1.cy;
             MyVector var10 = GameScr.vMobAttack;
             var4 = 0;
 
             Mob var18;
             while(true) {
                if (var4 >= var10.size()) {
                   var18 = null;
                   break;
                }
 
                Mob var5;
                if (isPkBossTarget(var5 = (Mob)var10.elementAt(var4))) {
                   var18 = var5;
                   break;
                }
 
                ++var4;
             }
 
             var2 = var18;
             if (var18 == null && (System.currentTimeMillis() - super.x > 60000L || System.currentTimeMillis() - super.x > 10000L && !this.d())) {
                Code var19 = Code.instance;
                Code.backToInstance();
                return;
             }
 
             this.c(var2);
          }
 
          int var6;
          Char var8;
          if (GameScr.vParty.size() > 0 && var1.nClass.classId == 6 && var1.cHP > 0) {
             for(var4 = 0; var4 < var1.vSkillFight.size(); ++var4) {
                Skill var12;
                if ((var12 = (Skill)var1.vSkillFight.elementAt(var4)) != null && var12.template.type == 4) {
                   if (!var12.isCooldown()) {
                      for(var6 = 0; var6 < GameScr.vParty.size(); ++var6) {
                         Party var7;
                         if ((var7 = (Party)GameScr.vParty.elementAt(var6)).a != var1.charID && var7.f != null && var7.f.cHP <= 0) {
                             try {
                                 var8 = var7.f;
                                 if (Math.abs(var1.cx - var8.cx) > 50 || Math.abs(var1.cy - var8.cy) > 50) {
                                     Char.charMove(var8.cx, var8.cy);
                                 }
                                 
                                 Thread.sleep(500L);
                                 Service.getInstance().buffLive(var7.a);
                                 var12.lastTimeUseThisSkill = System.currentTimeMillis();
                                 var12.paintCanNotUseSkill = true;
                                 var1.b(GameScr.skillPaints[var12.template.id], 0);
                                 Thread.sleep(1000L);
                                 return;
                             } catch (InterruptedException ex) {
                                 
                             }
                         }
                      }
                   }
                   break;
                }
             }
          }
 
          if (Auto.selectSkill != null) {
             Skill var11;
             int var13;
             int var15;
             if ((var11 = Auto.selectSkill).isCooldown() && Char.tickAutoBuff) {
                label518: {
                    try {
                        var13 = 0;
                        
                        Skill var14;
                        label445:
                        while(true) {
                            if (var13 >= var1.vSkillFight.size()) {
                                break label518;
                            }
                            
                            if ((var14 = (Skill)var1.vSkillFight.elementAt(var13)) != null && System.currentTimeMillis() - var14.lastTimeUseThisSkill >= (long)var14.coolDown - 300L) {
                                if (var14.template.type == 2) {
                                    if ((var1.d == null && Char.dk || var14.template.id < 67 || var14.template.id > 72) && (Char.dl || var14.template.id != 31) && (var14.template.id != 15 || var1.cHP < var1.cMaxHP * Char.aHpValue / 100 && var1.isHuman) && (var14.template.id != 6 || var1.isHuman)) {
                                        var15 = (int)(System.currentTimeMillis() / 1000L);
                                        int var16 = 0;
                                        
                                        while(true) {
                                            if (var16 >= var1.vEff.size()) {
                                                break label445;
                                            }
                                            
                                            Effect var9;
                                            if ((var9 = (Effect)var1.vEff.elementAt(var16)) != null && (var9.e.c == var14.template.iconId || var14.template.id == 58 && var9.e.b == 7) && var9.c - (var15 - var9.b) >= 2) {
                                                break;
                                            }
                                            
                                            ++var16;
                                        }
                                    }
                                } else if (var14.template.type == 3 && var2 != null && var2.levelBoss == 0 && var2.hp > var2.maxHp / 2) {
                                    if (var14.template.id != 4 || Char.dm && var1.cHP < var1.cMaxHP * Char.aHpValue / 100) {
                                        break;
                                    }
                                } else if ((var14.template.id == 7 || var14.template.id == 16 || var14.template.id == 25 || var14.template.id == 34 || var14.template.id == 43) && var2 != null && (var2.levelBoss != 0 || var2.hp >= var2.maxHp / 2) && (var14.template.id != 7 && var14.template.id != 16 || !var2.p) && (var14.template.id != 25 && var14.template.id != 34 || var2.q) && (var14.template.id != 43 || var2.r)) {
                                    break;
                                }
                            }
                            
                            ++var13;
                        }
                        
                        var11 = var14;
                        Thread.sleep(500L);
                    } catch (InterruptedException ex) {
                        
                    }
                }
             }
 
             Service.getInstance().selectSkill(var11.template.id);
             if (var11.template.type == 2) {
                Service.getInstance().r();
             } else {
                Mob var17;
                if (Code.an && var3 != null && !Auto.checkDead(var3) && Auto.a(var1, var3)) {
                   var13 = var11.dx;
                   var6 = var11.dy;
                   Auto.v.removeAllElements();
                   Auto.w.removeAllElements();
                   Auto.w.addElement(var3);
 
                   for(var15 = 0; var15 < GameScr.vCharInMap.size() && Auto.v.size() + Auto.w.size() < var11.maxFight; ++var15) {
                      if ((var8 = (Char)GameScr.vCharInMap.elementAt(var15)).cHP > 0 && var8.statusMe != 14 && var8.statusMe != 5 && var8.statusMe != 15 && !var8.equals(var3) && (var8.cTypePk == 3 || var1.cTypePk == 3 || var8.cTypePk == 1 && var1.cTypePk == 1 || var1.da >= 0 && var1.da == var8.charID || var1.cz >= 0 && var1.cz == var8.charID || var8.da == var1.charID) && !Code.d(var8.charName) && var3.cx - var13 <= var8.cx && var8.cx <= var3.cx + var13 && var3.cy - var6 <= var8.cy && var8.cy <= var3.cy + var6) {
                         Auto.w.addElement(var8);
                      }
                   }
 
                   for(var15 = 0; var15 < GameScr.vMobAttack.size() && Auto.v.size() + Auto.w.size() < var11.maxFight; ++var15) {
                      if ((var17 = (Mob)GameScr.vMobAttack.elementAt(var15)).h != 0 && var17.h != 1 && isBossLike(var17) && var3.cx - var13 <= var17.curX && var17.curX <= var3.cx + var13 && var3.cy - var6 <= var17.curY && var17.curY <= var3.cy + var6) {
                         Auto.v.addElement(var17);
                      }
                   }
 
                   Service.getInstance().a(Auto.v, Auto.w, 2);
                } else {
                   if (!isPkBossTarget(var2)) {
                      return;
                   }
 
                   int bossX = getMobAttackX(var2);
                   int bossY = getMobAttackY(var2);
                   if ((var11.template.type == 1 || var11.template.type == 3) && (Res.e(var1.cx - bossX) > var11.dx + 30 || Res.e(var1.cy - bossY) > var11.dy + 30)) {
                      this.c(var2);
                   }
 
                   var13 = var11.dx;
                   var6 = var11.dy;
                   Auto.v.removeAllElements();
                   Auto.w.removeAllElements();
                   Auto.v.addElement(var2);
 
                   for(var15 = 0; var15 < GameScr.vMobAttack.size() && Auto.v.size() + Auto.w.size() < var11.maxFight; ++var15) {
                      if ((var17 = (Mob)GameScr.vMobAttack.elementAt(var15)).h != 0 && var17.h != 1 && !var17.equals(var2) && isBossLike(var17) && bossX - 100 <= getMobAttackX(var17) && getMobAttackX(var17) <= bossX + 100 && bossY - 50 <= getMobAttackY(var17) && getMobAttackY(var17) <= bossY + 50) {
                         Auto.v.addElement(var17);
                      }
                   }
 
                   for(var15 = 0; var15 < GameScr.vCharInMap.size() && Auto.v.size() + Auto.w.size() < var11.maxFight; ++var15) {
                      if ((var8 = (Char)GameScr.vCharInMap.elementAt(var15)).cHP > 0 && var8.statusMe != 14 && var8.statusMe != 5 && var8.statusMe != 15 && (var8.cTypePk == 3 || var1.cTypePk == 3 || var8.cTypePk == 1 && var1.cTypePk == 1 || var1.da >= 0 && var1.da == var8.charID || var1.cz >= 0 && var1.cz == var8.charID || var8.da == var1.charID) && !Code.d(var8.charName) && var2.curX - var13 <= var8.cx && var8.cx <= var2.curX + var13 && var2.curY - var6 <= var8.cy && var8.cy <= var2.curY + var6) {
                         Auto.w.addElement(var8);
                      }
                   }
 
                   Service.getInstance().a(Auto.v, Auto.w, 1);
                }
             }
 
             if (System.currentTimeMillis() - var11.lastTimeUseThisSkill >= (long)var11.coolDown) {
                var11.lastTimeUseThisSkill = System.currentTimeMillis();
                var11.paintCanNotUseSkill = true;
                if (!Code.isBangSkill) {
                   var1.b(GameScr.skillPaints[var11.template.id], 0);
                }
             }
 
             super.x = System.currentTimeMillis();
             if (var11.template.id == 15) {
                 try {
                     Thread.sleep(2000L);
                 } catch (InterruptedException ex) {
                     
                 }
             }
          }
 
       }
    }
 
    public final void run() {
       if (super.isDead()) {
          boolean var9;
          if (!Char.tickReMap) {
             var9 = true;
          } else {
             label98: {
                label137: {
                   if (TileMap.mapID == super.mapID && TileMap.zoneID == super.zoneID) {
                      int var5 = 0;
 
                      label91:
                      while(true) {
                         Party var7;
                         if (var5 >= GameScr.vParty.size()) {
                            var5 = 0;
 
                            while(true) {
                               if (var5 >= GameScr.vParty.size()) {
                                  break label137;
                               }
 
                               if ((var7 = (Party)GameScr.vParty.elementAt(var5)).f != null) {
                                  if (var7.d.equals(Char.getMyChar().charName)) {
                                     break label137;
                                  }
                                  break label91;
                               }
 
                               ++var5;
                            }
                         }
 
                         if ((var7 = (Party)GameScr.vParty.elementAt(var5)).f != null && var7.f.cHP > 0) {
                            break;
                         }
 
                         ++var5;
                      }
                   }
 
                   var9 = false;
                   break label98;
                }
 
                var9 = true;
             }
          }
 
          if (!var9) {
             Auto.autoRemap(true);
          }
 
       } else {
          Char var1 = Char.getMyChar();
          boolean var2 = Code.g == null || var1.charName.equals(Code.g);
          int var6;
          if (super.zoneID == -2) {
             if (super.mapID != TileMap.mapID) {
                this.goMap(super.mapID, -2, -1, -1);
                return;
             }
 
             if (var2) {
                this.b = (this.b - 1) % 30;
                if (this.b < 15) {
                   this.b = 29;
                }
 
                Auto.changeZone(this.b);
 
                for(var6 = 0; var6 < GameScr.vMobAttack.size(); ++var6) {
                   Mob var3;
                   if (isBossLike(var3 = (Mob)GameScr.vMobAttack.elementAt(var6))) {
                      Service.getInstance().k("pkm " + super.mapID);
                      Service.getInstance().k("pkk " + (super.zoneID = TileMap.zoneID));
                      break;
                   }
                }
 
                if (this.b == 15 && ++this.a == 2) {
                   Service.getInstance().k("pke");
                   Code var10000 = Code.instance;
                   Code.backToInstance();
                   return;
                }
             }
          } else if (super.mapID == TileMap.mapID && super.zoneID == TileMap.zoneID) {
             this.f();
             e();
             if (var1.cMP < var1.cMaxMP * Char.aMpValue / 100) {
                var1.e(17);
             }
 
             if (var1.cHP < var1.cMaxHP * Char.aHpValue / 100) {
                var6 = (int)(System.currentTimeMillis() / 1000L);
 
                for(int var8 = 0; var8 < var1.vEff.size(); ++var8) {
                   Effect var4;
                   if ((var4 = (Effect)var1.vEff.elementAt(var8)).e.a == 21 && var4.c - (var6 - var4.b) >= 2) {
                      return;
                   }
                }
 
                Char.getMyChar().e(16);
                return;
             }
          } else {
             this.goMap(super.mapID, super.zoneID, super.k, super.l);
          }
 
       }
    }
 
    public final String toString() {
       return "PK Boss";
    }
 }
 
