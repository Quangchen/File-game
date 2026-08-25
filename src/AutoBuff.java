public final class AutoBuff extends Auto {
    private boolean a;
    private boolean b;
    private long c = 0L;
 
    public AutoBuff() {
    }
 
    public final void a(int var1, int var2, boolean var3, boolean var4) {
       super.a();
       super.mapID = var1;
       super.zoneID = var2;
       super.isHang = TileMap.isHang(var1);
       this.a = var3;
       this.b = var4;
       super.g = true;
    }
 
    public final void run() {
       if (super.isDead()) {
          Auto.autoRemap(true);
       } else {
          if (super.mapID == TileMap.mapID && (TileMap.isHang(super.mapID) || super.zoneID == TileMap.zoneID)) {
             Char var1 = Char.getMyChar();
             int var4;
             int var5;
             int var6;
             Char var9;
             if (this.b && (GameScr.vParty.size() > 1 || Code.i.size() > 0) && var1.nClass.classId == 6) {
                for(int var2 = 0; var2 < var1.vSkillFight.size(); ++var2) {
                   Skill var3;
                   if ((var3 = (Skill)var1.vSkillFight.elementAt(var2)) != null && var3.template.type == 4) {
                      if (System.currentTimeMillis() - this.c > 3000L) {
                         for(var4 = 0; var4 < GameScr.vParty.size(); ++var4) {
                            Party var8;
                            if ((var8 = (Party)GameScr.vParty.elementAt(var4)).a != var1.charID && var8.f != null && var8.f.cHP <= 0) {
                                try {
                                    var5 = var1.cx;
                                    var6 = var1.cy;
                                    Char var14;
                                    Char.charMove((var14 = var8.f).cx, var14.cy);
                                    Thread.sleep(500L);
                                    if (Auto.checkDead(var14)) {
                                        this.c = System.currentTimeMillis();
                                        Service.getInstance().buffLive(var8.a);
                                        var3.lastTimeUseThisSkill = System.currentTimeMillis();
                                        var3.paintCanNotUseSkill = true;
                                        var1.b(GameScr.skillPaints[var3.template.id], 0);
                                        Thread.sleep(1000L);
                                    }
                                    
                                    Char.charMove(var5, var6);
                                    return;
                                } catch (InterruptedException ex) {
                                    
                                }
                            }
                         }
 
                         for(var4 = 0; var4 < GameScr.vCharInMap.size(); ++var4) {
                            if ((var9 = (Char)GameScr.vCharInMap.elementAt(var4)) != null && Auto.checkDead(var9) && Code.a(var9.charName)) {
                                try {
                                    var5 = var1.cx;
                                    var6 = var1.cy;
                                    Char.charMove(var9.cx, var9.cy);
                                    Thread.sleep(500L);
                                    if (Auto.checkDead(var9)) {
                                        this.c = System.currentTimeMillis();
                                        Service.getInstance().buffLive(var9.charID);
                                        var3.lastTimeUseThisSkill = System.currentTimeMillis();
                                        var3.paintCanNotUseSkill = true;
                                        var1.b(GameScr.skillPaints[var3.template.id], 0);
                                        Thread.sleep(1000L);
                                    }
                                    
                                    Char.charMove(var5, var6);
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
 
             var9 = GameScr.vParty.size() > 0 ? ((Party)GameScr.vParty.firstElement()).f : null;
             if (this.a && this.k() && var9 != null && var1.nClass.classId == 6) {
                for(int var10 = 0; var10 < var1.vSkillFight.size(); ++var10) {
                   Skill var15;
                   if ((var15 = (Skill)var1.vSkillFight.elementAt(var10)) != null && !var15.isCooldown() && var15.template.type == 2 && (var15.template.id < 67 || var15.template.id > 72) && (var15.template.id != 47 || var9.cHP < var9.cMaxHP * Char.aHpValue / 100)) {
                       try {
                           System.currentTimeMillis();
                           
                           for(var5 = 0; var5 < var9.vEff.size(); ++var5) {
                               var9.vEff.elementAt(var5);
                           }
                           
                           var5 = var1.cx;
                           var6 = var1.cy;
                           Char.charMove(var9.cx, var9.cy);
                           Service.getInstance().selectSkill(var15.template.id);
                           Service.getInstance().r();
                           var15.lastTimeUseThisSkill = System.currentTimeMillis();
                           var15.paintCanNotUseSkill = true;
                           var1.b(GameScr.skillPaints[var15.template.id], 0);
                           Thread.sleep(1000L);
                           Char.charMove(var5, var6);
                           return;
                       } catch (InterruptedException ex) {
                           
                       }
                   }
                }
             }
 
             if (Code.an) {
                Char var12;
                if (((var12 = var1.charFocus) == null || !SavePK.c(var12.charName) && !Auto.a(var1, var12)) && (var12 = this.a(var1, -1)) == null) {
                   var12 = this.e(var1);
                }
 
                boolean var16 = var12 != null && SavePK.c(var12.charName);
                if (var12 == null && super.aa) {
                   Service.getInstance().z(0);
                   super.aa = false;
                }
 
                if (var1.hieuChien >= 5 && System.currentTimeMillis() - super.z > 5000L) {
                   Item var11;
                   if ((var11 = Char.getItemByID(257)) != null && var11.template.id == 257) {
                      Service.getInstance().useItem(var11.indexUI);
                   }
 
                   super.z = System.currentTimeMillis();
                }
 
                if (var12 != null && var12 != null && !Auto.checkDead(var12) && (var16 || Auto.a(var1, var12))) {
                   Skill var13 = Auto.selectSkill;
                   if (var16) {
                      if ((var13.template.type == 1 || var13.template.type == 3) && (Res.e(var1.cx - var12.cx) > var13.dx + 30 || Res.e(var1.cy - var12.cy) > var13.dy + 30) && System.currentTimeMillis() - super.y > 1500L) {
                         Auto.d(var12);
                         super.y = System.currentTimeMillis();
                      }
 
                      if (var12.da != var1.charID && var12.cTypePk != 3) {
                         super.aa = true;
                         Service.getInstance().z(3);
                      }
                   }
 
                   var5 = var13.dx;
                   var6 = var13.dy;
                   Auto.v.removeAllElements();
                   Auto.w.removeAllElements();
                   Auto.w.addElement(var12);
 
                   for(var4 = 0; var4 < GameScr.vCharInMap.size() && Auto.v.size() + Auto.w.size() < var13.maxFight; ++var4) {
                      Char var7;
                      if ((var7 = (Char)GameScr.vCharInMap.elementAt(var4)).cHP > 0 && var7.statusMe != 14 && var7.statusMe != 5 && var7.statusMe != 15 && !var7.equals(var12) && (var7.cTypePk == 3 || var1.cTypePk == 3 || var7.cTypePk == 1 && var1.cTypePk == 1 || var1.da >= 0 && var1.da == var7.charID || var1.cz >= 0 && var1.cz == var7.charID) && !Code.d(var7.charName) && var12.cx - var5 <= var7.cx && var7.cx <= var12.cx + var5 && var12.cy - var6 <= var7.cy && var7.cy <= var12.cy + var6) {
                         Auto.w.addElement(var7);
                      }
                   }
 
                   Service.getInstance().a(Auto.v, Auto.w, 2);
                   if (System.currentTimeMillis() - var13.lastTimeUseThisSkill >= (long)var13.coolDown) {
                      var13.lastTimeUseThisSkill = System.currentTimeMillis();
                      var13.paintCanNotUseSkill = true;
                      if (!Code.isBangSkill) {
                         var1.b(GameScr.skillPaints[var13.template.id], 0);
                      }
                   }
 
                   super.x = System.currentTimeMillis();
                   return;
                }
             }
          } else {
             this.goMap(super.mapID, super.zoneID, -1, -1);
          }
 
       }
    }
 
    public final String toString() {
       if (this.a && this.b) {
          return "Buff HS Xa";
       } else {
          return this.b ? "HSinh Xa" : "Buff Xa";
       }
    }
 }
 