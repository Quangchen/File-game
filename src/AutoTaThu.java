public final class AutoTaThu extends Auto {
    private TaskOrder task;
    public int killId;
    public static boolean b;
    public static long c;
 
    public AutoTaThu() {
    }
 
    public final void a() {
       super.a();
       this.task = Char.getTaskOrderById(1);
       if (this.task != null) {
          this.killId = this.task.killId;
          super.mapID = this.task.mapId;
          if (TileMap.mapID == this.task.mapId && TileMap.zoneID % 5 == 0) {
             super.zoneID = TileMap.zoneID;
             return;
          }
       }
 
       super.zoneID = 5;
       b = false;
    }
 
    public final void a(int var1, int var2) {
       super.a();
       this.task = null;
       this.killId = var2;
       super.mapID = var1;
       if (TileMap.mapID == var1 && TileMap.zoneID % 5 == 0) {
          super.zoneID = TileMap.zoneID;
       } else {
          super.zoneID = 5;
       }
    }
 
    public final void b() {
       this.task = Char.getTaskOrderById(1);
       super.b();
    }
 
    public final void run() {
       if (super.mapID >= 0 && (!(super.instance instanceof Stanima) || System.currentTimeMillis() - super.o < 3600000L)) {
          int var1;
          boolean var5;
          if (super.isDead()) {
             if (Char.tickDanhTheoNhom && TileMap.mapID == super.mapID && TileMap.zoneID == super.zoneID && Char.getMyChar().mobFocus != null && Char.getMyChar().mobFocus.hp < Char.getMyChar().mobFocus.maxHp / 20) {
                var1 = 0;
 
                while(true) {
                   if (var1 >= GameScr.vParty.size()) {
                      var5 = false;
                      break;
                   }
 
                   Party var2;
                   if ((var2 = (Party)GameScr.vParty.elementAt(var1)).f != null && var2.f.cHP > 0) {
                      var5 = true;
                      break;
                   }
 
                   ++var1;
                }
             } else {
                var5 = false;
             }
 
              if (!var5) {
                 Auto.autoRemap(true);
                 return;
              }
           } else if (Auto.goTruongAndLuuToaDoIfNeeded()) {
              return;
           } else if (TileMap.mapID == super.mapID && TileMap.zoneID == super.zoneID) {
             if (this.task != null && this.task.count >= this.task.maxCount) {
                GameScr.chatPopup("Xong Tà Thú");
                Code.backToInstance();
                return;
             }
 
             if (Char.getMyChar().charName.equals(Code.g)) {
                if (Char.getMyChar().mobFocus != null && Char.getMyChar().mobFocus.hp < Char.getMyChar().mobFocus.maxHp / 10) {
                   if (!LockGame.ad()) {
                      Service.getInstance().k("waitGr");
                      LockGame.b(200000L);
                      Service.getInstance().k("notifyGr");
                   }
 
                   var5 = false;
                } else {
                   var5 = false;
                }
             } else {
                if (b && System.currentTimeMillis() - c > 120000L) {
                   b = false;
                }
 
                var5 = b;
             }
 
             if (!var5) {
                this.attack(this.killId, 8);
             }
 
             if (Char.getMyChar().cMP < Char.getMyChar().cMaxMP * Char.aMpValue / 100) {
                Char.getMyChar().e(17);
             }
 
             if (Char.getMyChar().cHP < Char.getMyChar().cMaxHP * Char.aHpValue / 100) {
                var1 = (int)(System.currentTimeMillis() / 1000L);
 
                for(int var4 = 0; var4 < Char.getMyChar().vEff.size(); ++var4) {
                   Effect var3;
                   if ((var3 = (Effect)Char.getMyChar().vEff.elementAt(var4)).e.a == 21 && var3.c - (var1 - var3.b) >= 2) {
                      return;
                   }
                }
 
                Char.getMyChar().e(16);
                return;
             }
          } else {
             this.goMap(super.mapID, super.zoneID, super.k, super.l);
          }
 
       } else {
          Code.backToInstance();
       }
    }
 
    public final String toString() {
       return "Auto Tà Thú: " + Mob.mobTemplates[this.task.killId].name + "(" + this.task.count + "/" + this.task.maxCount + ")";
    }
 }
 
