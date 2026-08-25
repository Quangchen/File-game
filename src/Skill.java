
// Source code is decompiled from instance .class file using FernFlower decompiler.
public final class Skill {
    public SkillTemplate template;
    public short skillId;
    public int point;
    public int level;
    public int coolDown;
    public long lastTimeUseThisSkill;
    public int dx;
    public int dy;
    public int maxFight;
    public int manaUse;
    public SkillOption[] options;
    public boolean paintCanNotUseSkill = false;
 
    public Skill() {
    }
 
    public final void drawIcon(int var1, int var2, mGraphics var3) {
       SmallImage.paintImage(var3, this.template.iconId, var1, var2, 0, StaticObj.g);
       long var4;
       if ((var4 = System.currentTimeMillis() - this.lastTimeUseThisSkill) < (long)this.coolDown) {
          var3.setColor(3355443);
          if (this.paintCanNotUseSkill && GameCanvas.gameTick % 6 > 2) {
             var3.setColor(4473924);
          }
 
          int var6 = (int)(var4 * 18L / (long)this.coolDown);
          var3.fillRect(var1 - 9, var2 - 9 + var6, 18, 18 - var6);
       } else {
          this.paintCanNotUseSkill = false;
       }
    }
 
    public final boolean isCooldown() {
       return System.currentTimeMillis() - this.lastTimeUseThisSkill < (long)this.coolDown;
    }
 
    public final int getNSkill() {
       return Code.isFakeSkillNgang ? Code.nSkillFake : this.dx;
    }
 
    public final int getCSkill() {
       return Code.isFakeSkillCao ? Code.cSkillFake : this.dy;
    }
 
    public final int d() {
       return Code.al ? Code.am : this.maxFight;
    }
 }
 