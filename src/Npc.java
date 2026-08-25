
// Source code is decompiled from idEf .class file using FernFlower decompiler.
public final class Npc extends Char {
    public NpcTemplate template;
    public static NpcTemplate[] arrNpcTemplate;
 
    public Npc(int var1, int var2, int var3, int var4) {
       super.cx = var2;
       super.cy = var3;
       super.statusMe = var1;
       this.template = arrNpcTemplate[var4];
    }
 
    public static void a() {
       for(int var0 = 0; var0 < GameScr.ah.size(); ++var0) {
          Npc var1;
          (var1 = (Npc)GameScr.ah.elementAt(var0)).ga = null;
          var1.fz = -1;
       }
 
    }
 
    public final void b() {
       if (super.ga == null) {
          label45: {
             label51: {
                byte[] var1 = new byte[]{-1, 9, 9, 10, 10, 11, 11};
                if (Char.getMyChar().ctaskId >= 9 && Char.getMyChar().ctaskId <= 10 && Char.getMyChar().nClass.classId > 0 && var1[Char.getMyChar().nClass.classId] == this.template.npcTemplateId) {
                   if (Char.getMyChar().taskMaint != null) {
                      if (Char.getMyChar().taskMaint == null || Char.getMyChar().taskMaint.a + 1 != Char.getMyChar().taskMaint.e.length) {
                         break label45;
                      }
 
                      super.ga = GameScr.efs[62];
                      break label51;
                   }
                } else {
                   GameScr.getInstance();
                   byte var2 = GameScr.ae();
                   if (Char.getMyChar().taskMaint != null || var2 != this.template.npcTemplateId) {
                      if (Char.getMyChar().taskMaint == null || var2 != this.template.npcTemplateId) {
                         break label45;
                      }
 
                      if (Char.getMyChar().taskMaint.a + 1 == Char.getMyChar().taskMaint.e.length) {
                         super.ga = GameScr.efs[62];
                         break label51;
                      }
                   }
                }
 
                super.ga = GameScr.efs[57];
             }
 
             super.fz = 0;
          }
       }
 
       super.b();
    }
 
    public final void a(mGraphics var1) {
       if (this.u() && super.statusMe != 15) {
          if (super.cTypePk != 0) {
             super.a(var1);
             return;
          }
 
          if (this.template != null) {
             if (this.template.npcTemplateId == 13) {
                if (Char.getMyChar().npcFocus != null && Char.getMyChar().npcFocus.equals(this)) {
                   SmallImage.paintImage(var1, 988, super.cx, super.cy - super.bj - 1, 0, 33);
                }
 
                SmallImage.paintImage(var1, 1060, super.cx, super.cy, 0, 33);
                mFont.tahoma_7_white.writeText(var1, String.valueOf(TileMap.zoneID), super.cx, super.cy - 10 - 2 - mFont.tahoma_7.getHeight(), 2);
                return;
             }
 
             if (this.template.npcTemplateId == 31) {
                if (Char.getMyChar().npcFocus != null && Char.getMyChar().npcFocus.equals(this)) {
                   SmallImage.paintImage(var1, 988, super.cx, super.cy - super.bj - 1, 0, 33);
                }
 
                SmallImage.paintImage(var1, 1291, super.cx, super.cy, 0, 33);
                return;
             }
 
             if (this.template.npcTemplateId == 27) {
                if (Char.getMyChar().npcFocus != null && Char.getMyChar().npcFocus.equals(this)) {
                   SmallImage.paintImage(var1, 988, super.cx, super.cy - super.bj - 1, 0, 33);
                }
 
                SmallImage.paintImage(var1, 1224, super.cx, super.cy, 0, 33);
                return;
             }
 
             Part var2 = GameScr.parts[this.template.head];
             Part var3 = GameScr.parts[this.template.leg];
             Part var4 = GameScr.parts[this.template.body];
             if (super.cDir == 1) {
                SmallImage.paintImage(var1, var2.partImages[Char.fe[super.bn][0][0]].id, super.cx + Char.fe[super.bn][0][1] + var2.partImages[Char.fe[super.bn][0][0]].dx, super.cy - Char.fe[super.bn][0][2] + var2.partImages[Char.fe[super.bn][0][0]].dy, 0, 0);
                SmallImage.paintImage(var1, var3.partImages[Char.fe[super.bn][1][0]].id, super.cx + Char.fe[super.bn][1][1] + var3.partImages[Char.fe[super.bn][1][0]].dx, super.cy - Char.fe[super.bn][1][2] + var3.partImages[Char.fe[super.bn][1][0]].dy, 0, 0);
                SmallImage.paintImage(var1, var4.partImages[Char.fe[super.bn][2][0]].id, super.cx + Char.fe[super.bn][2][1] + var4.partImages[Char.fe[super.bn][2][0]].dx, super.cy - Char.fe[super.bn][2][2] + var4.partImages[Char.fe[super.bn][2][0]].dy, 0, 0);
             } else {
                SmallImage.paintImage(var1, var2.partImages[Char.fe[super.bn][0][0]].id, super.cx - Char.fe[super.bn][0][1] - var2.partImages[Char.fe[super.bn][0][0]].dx, super.cy - Char.fe[super.bn][0][2] + var2.partImages[Char.fe[super.bn][0][0]].dy, 2, 24);
                SmallImage.paintImage(var1, var3.partImages[Char.fe[super.bn][1][0]].id, super.cx - Char.fe[super.bn][1][1] - var3.partImages[Char.fe[super.bn][1][0]].dx, super.cy - Char.fe[super.bn][1][2] + var3.partImages[Char.fe[super.bn][1][0]].dy, 2, 24);
                SmallImage.paintImage(var1, var4.partImages[Char.fe[super.bn][2][0]].id, super.cx - Char.fe[super.bn][2][1] - var4.partImages[Char.fe[super.bn][2][0]].dx, super.cy - Char.fe[super.bn][2][2] + var4.partImages[Char.fe[super.bn][2][0]].dy, 2, 24);
             }
 
             if (super.fz >= 0 && super.ga != null && super.cTypePk == 0) {
                SmallImage.paintImage(var1, super.ga.arrEfInfo[super.fz].c, super.cx + super.ga.arrEfInfo[super.fz].a, super.cy + super.ga.arrEfInfo[super.fz].b, 0, 3);
                if (GameCanvas.gameTick % 2 == 0) {
                   ++super.fz;
                   if (super.fz >= super.ga.arrEfInfo.length) {
                      super.fz = 0;
                   }
                }
             }
 
             if (Char.getMyChar().npcFocus != null && Char.getMyChar().npcFocus.equals(this)) {
                mFont.tahoma_7_yellow.writeText(var1, "[" + this.template.npcTemplateId + "]" + this.template.name, super.cx, super.cy - super.bj - mFont.tahoma_7.getHeight() - 7, 2, mFont.tahoma_7_grey);
                SmallImage.paintImage(var1, 988, super.cx, super.cy - super.bj - 2, 0, 33);
                return;
             }
 
             mFont.tahoma_7_yellow.writeText(var1, "[" + this.template.npcTemplateId + "]" + this.template.name, super.cx, super.cy - super.bj - 3 - mFont.tahoma_7.getHeight(), 2, mFont.tahoma_7_grey);
          }
       }
 
    }
 }
 