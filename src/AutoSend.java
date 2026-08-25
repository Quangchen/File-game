
public final class AutoSend extends Auto {
    private boolean d;
    private String e;
    private String[] targets;
    private int targetIndex;
    private boolean manual;
    public static boolean a;
    public static boolean b;
    public static boolean c;

    public AutoSend(int var1, int var2, String var3) {
       this(var1, var2, var3, false);
    }

    public AutoSend(int var1, int var2, String var3, boolean var4) {
       super.a();
       this.d = false;
       super.mapID = var1;
       super.zoneID = var2;
       this.targets = parseTargets(var3);
       this.targetIndex = 0;
       this.e = this.targets.length > 0 ? this.targets[0] : null;
       this.manual = var4;
    }

    public final void run() {
       if (Char.getMyChar().cHP <= 0) {
          Auto.autoRemap(true);
       } else {
          if (super.mapID == TileMap.mapID && super.zoneID == TileMap.zoneID) {
             if (!this.d) {
                AutoSell.a = false;
                a = false;
                b = false;
                c = false;

                if (this.e == null || this.e.length() == 0) {
                   this.d = true;
                   return;
                }

                int guard = 0;
                boolean triedTrade = false;
                while((e() > 0 || f() > 0 || !triedTrade && SettingGomDo.tradeCoinValue == 0 && Char.getMyChar().xu > 0) && guard++ < 30) {
                   if (e() <= 0 && f() > 0) {
                      withdrawGatherFromBox();
                      Auto.sleep(1500L);
                      if (e() <= 0) {
                         break;
                      }
                   }

                   AutoReceiver.a(this.e);
                   triedTrade = true;
                   if (e() <= 0) {
                      if (f() <= 0) {
                         break;
                      }

                      withdrawGatherFromBox();
                   }

                   if (c) {
                      break;
                   }

                   if (a) {
                      GameScr.chatPopup("Hanh trang doi phuong khong con du cho trong!");
                      break;
                   }

                   GameScr.chatPopup("Van con vat pham, se giao dich lai sau 30s");
                   Auto.sleep(32000L);
                }

                if (!a) {
                   GameScr.chatPopup("Da het vat pham can giao dich, hoac doi phuong khong co trong khu nay!");
                }

                this.d = true;
             } else if (nextTarget()) {
                GameScr.chatPopup("Gom do: den " + this.e);
             } else if (this.manual || Res.getCurrentTime().get(12) != AutoReceiver.phutNhanDo) {
                Code.backToInstance();
             }
          } else {
             this.goMap(super.mapID, super.zoneID, -1, -1);
          }

       }
    }

    public final String toString() {
       return this.manual ? "Gom do ngay" : "Gui do";
    }

    private boolean nextTarget() {
       if (this.targets == null) {
          return false;
       }

       ++this.targetIndex;
       if (this.targetIndex >= this.targets.length) {
          return false;
       }

       this.e = this.targets[this.targetIndex];
       this.d = false;
       a = false;
       b = false;
       c = false;
       return true;
    }

    private static String[] parseTargets(String var0) {
       if (var0 == null) {
          return new String[0];
       }

       String[] parts = Code.splitString(var0, ",");
       String[] temp = new String[parts.length];
       int count = 0;
       for(int i = 0; i < parts.length; ++i) {
          if (parts[i] != null) {
             String name = parts[i].trim();
             if (name.length() > 0) {
                temp[count++] = name;
             }
          }
       }

       String[] result = new String[count];
       System.arraycopy(temp, 0, result, 0, count);
       return result;
    }

    private static void withdrawGatherFromBox() {
       try {
          if (Char.getMyChar().arrItemBox == null) {
             return;
          }

          for(int var7 = 0; var7 < AutoReceiver.gatherListID.length; ++var7) {
             for(int var8 = 0; var8 < Char.getMyChar().arrItemBox.length; ++var8) {
                Item var9 = Char.getMyChar().arrItemBox[var8];
                if (var9 != null && var9.template.id == AutoReceiver.gatherListID[var7] && !var9.isLock) {
                   Service.getInstance().d(var9.indexUI);
                }
             }
          }
       } catch (Exception e) {
       }
    }

    public static int e() {
       Char me = Char.getMyChar();
       if (me == null) {
          return 0;
       }
       Item[] var5 = me.arrItemBag;
       if (var5 == null) {
          return 0;
       }
       int var7 = 0;

       for(int var8 = 0; var8 < AutoReceiver.gatherListID.length; ++var8) {
          for(int var9 = 0; var9 < var5.length; ++var9) {
             Item var6;
             if ((var6 = var5[var9]) != null && var6.template.id == AutoReceiver.gatherListID[var8] && !var6.isLock) {
                ++var7;
             }
          }
       }

       return var7;
    }

    public static int f() {
       Char me = Char.getMyChar();
       if (me == null) {
          return 0;
       }
       Item[] var5 = me.arrItemBox;
       if (var5 == null) {
          return 0;
       }
       int var7 = 0;

       for(int var8 = 0; var8 < AutoReceiver.gatherListID.length; ++var8) {
          for(int var9 = 0; var9 < var5.length; ++var9) {
             Item var6;
             if ((var6 = var5[var9]) != null && var6.template.id == AutoReceiver.gatherListID[var8] && !var6.isLock) {
                ++var7;
             }
          }
       }

       return var7;
    }
}
