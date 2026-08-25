import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;

public final class AutoReceiver extends Auto {
   public static String stringNameCharNhanDo;
   public static byte gioNhanDo;
   public static byte phutNhanDo;
   public static int mapNhanDo;
   public static byte khuNhanDo;
   public static boolean f;
   public static boolean ab;
   public static short[] gatherListID = new short[120];
   private static int randomSeed;

   public AutoReceiver() {
      super.a();
      super.mapID = TileMap.mapID;
      super.zoneID = TileMap.zoneID;
   }

   public static boolean isInTimeTrade() {
      Calendar var0;
      return SettingGomDo.onOffValue == 0 && stringNameCharNhanDo != null && (var0 = Res.getCurrentTime()).get(11) == gioNhanDo && var0.get(12) == phutNhanDo;
   }

   private static int randomInt(int maxExclusive) {
      if (randomSeed == 0) {
         randomSeed = (int)(System.currentTimeMillis() & 2147483647L);
         if (randomSeed == 0) {
            randomSeed = 1;
         }
      }

      randomSeed = randomSeed * 1103515245 + 12345;
      return (randomSeed >>> 1) % maxExclusive;
   }

   private static void randomDefaultTime() {
      gioNhanDo = (byte)randomInt(24);
      phutNhanDo = (byte)randomInt(60);
   }

   public static void load() {
      try {
        for (int i = 0; i < gatherListID.length; ++i) {
            gatherListID[i] = -1;
        }
         byte[] data = RMS.getRecord("V6Rec");
         if (data == null || data.length == 0) {
            stringNameCharNhanDo = "ytakim";
            randomDefaultTime();
            mapNhanDo = 22;
            khuNhanDo = 20;
            SettingGomDo.tradeCoinValue = 1;
            save();
            return;
         }

         ByteArrayInputStream var9 = new ByteArrayInputStream(data);
         DataInputStream var10;
         if ((stringNameCharNhanDo = (var10 = new DataInputStream(var9)).readUTF()).equals("")) {
            stringNameCharNhanDo = null;
         }

         if ((SettingGomDo.stringItemCat = var10.readUTF()).equals("")) {
            SettingGomDo.stringItemCat = null;
         }

         gioNhanDo = var10.readByte();
         phutNhanDo = var10.readByte();
         mapNhanDo = var10.readInt();
         khuNhanDo = var10.readByte();
         SettingGomDo.onOffValue = var10.readByte();
         SettingGomDo.tradeCoinValue = var10.readByte();
         int sizeArray = var10.readInt();
         int index;
         if (gatherListID.length < sizeArray) {
            gatherListID = new short[10 * (sizeArray / 10 + 1)];
         }
         
         for(index = 0; index < sizeArray; ++index) {
            gatherListID[index] = var10.readShort();
         }
         var10.close();
         var9.close();
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   public static void save() {
      ByteArrayOutputStream var9 = new ByteArrayOutputStream();
      DataOutputStream var10 = new DataOutputStream(var9);

      try {
         var10.writeUTF(stringNameCharNhanDo == null ? "" : stringNameCharNhanDo);
         var10.writeUTF(SettingGomDo.stringItemCat == null ? "" : SettingGomDo.stringItemCat);
         var10.writeByte(gioNhanDo);
         var10.writeByte(phutNhanDo);
         var10.writeInt(mapNhanDo);
         var10.writeByte(khuNhanDo);
         var10.writeByte(SettingGomDo.onOffValue);
         var10.writeByte(SettingGomDo.tradeCoinValue);
         int sizeArray = 0;
         for(int index = 0; index < gatherListID.length; ++index) {
            if (gatherListID[index] >= 0) {
               ++sizeArray;
            }
         }

         var10.writeInt(sizeArray);
         for(int index = 0; index < gatherListID.length; ++index) {
            if (gatherListID[index] >= 0) {
               var10.writeShort(gatherListID[index]);
            }
         }
         var10.flush();
         var9.flush();
         RMS.writeRecord("V6Rec", var9.toByteArray());
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   public final void run() {
      if (Char.getMyChar().cHP <= 0) {
         Auto.autoRemap(true);
      } else if (super.mapID == TileMap.mapID && super.zoneID == TileMap.zoneID) {
         LockGame.a(-1L);
         AutoSell.a = false;
         long var1 = System.currentTimeMillis();

         while(GameScr.getInstance().da != 1) {
            if (AutoSell.a || System.currentTimeMillis() - var1 >= 20000L) {
               return;
            }

            Auto.sleep(200L);
         }

         GameScr.getInstance().db = 0;
         GameScr.arrItemTradeMe = new Item[12];
         Service.getInstance().tradeLock(0, GameScr.arrItemTradeMe);
         GameScr.getInstance().cz = 1;
         Auto.sleep(5000L);
         Service.getInstance().j();
         LockGame.a(20000L);
      } else {
         this.goMap(super.mapID, super.zoneID, -1, -1);
      }
   }

   public final String toString() {
      return "Auto Receiver";
   }

   static {
      load();
   }

   public static void a(String var0) {
      Char p = Char.getMyChar();
      int i = 0;

      Char pl;
      while(true) {
         if (i >= GameScr.vCharInMap.size()) {
            pl = null;
            break;
         }

         Char var3;
         if ((var3 = (Char)GameScr.vCharInMap.elementAt(i)).charName.equals(var0)) {
            pl = var3;
            break;
         }

         ++i;
      }

      if (pl == null) {
         AutoSend.c = true;
      } else {
         do {
            if (Res.distance(p.cx, p.cy, pl.cx, pl.cy) >= 50) {
                try {
                    Char.charMove(pl.cx, pl.cy);
                    Thread.sleep(1000L);
                } catch (InterruptedException ex) {
                    
                }
            } else {
               Service.getInstance().tradeInvite(pl.charID);
            }
         } while(!GameScr.ci && !LockGame.a(1000L));

         Item[] var9 = new Item[12];
         i = 0;

         for(int j = 0; j < AutoReceiver.gatherListID.length; ++j) {
            for(int var4 = 0; var4 < p.arrItemBag.length; ++var4) {
               Item var5 = p.arrItemBag[var4];
               if (i < 12 && var5 != null && var5.template.id == AutoReceiver.gatherListID[j] && !var5.isLock) {
                  var9[i++] = var5;
               }
            }
         }

         
         int coinTrade = 0;
         if(Char.getMyChar().xu > 0 && SettingGomDo.tradeCoinValue == 0){
             if(Char.getMyChar().xu > 500000000){
                 coinTrade = 500000000;
             }
             else{
                 coinTrade = Char.getMyChar().xu;
             }
         }
         GameScr.getInstance().db = coinTrade;
         GameScr.arrItemTradeMe = var9;
         Service.getInstance().tradeLock(coinTrade, var9);
         GameScr.getInstance().cz = 1;
         long var11 = System.currentTimeMillis();

         while(GameScr.getInstance().da != 1) {
             try {
                 if (AutoSell.a || System.currentTimeMillis() - var11 >= 20000L) {
                     return;
                 }
                 
                 Thread.sleep(200L);
             } catch (InterruptedException ex) {
                 
             }
         }

         var11 = System.currentTimeMillis();

         while(System.currentTimeMillis() - var11 < 5000L) {
             try {
                 if (AutoSell.a) {
                     return;
                 }
                 
                 Thread.sleep(200L);
             } catch (InterruptedException ex) {
                 
             }
         }

         Service.getInstance().j();
         if (LockGame.a(20000L)) {
            for(int var10 = 0; var10 < 12; ++var10) {
               if (var9[var10] != null) {
                  int var10001 = var9[var10].indexUI;
                  Char.getMyChar().arrItemBag[var10001] = null;
               }
            }
         }

      }
   }
   
   public static boolean containItemGather(short var0) {
        for (int var1 = 0; var1 < gatherListID.length; ++var1) {
            if (gatherListID[var1] == var0) {
                return true;
            }
        }
        return false;
    }

    public static void removeItemGather(short var0) {
        for (int var1 = 0; var1 < gatherListID.length; ++var1) {
            if (gatherListID[var1] == var0) {
                gatherListID[var1] = -1;
            }
        }
    }

    public static void addItemGather(short var0) {
        int var1;
        for (var1 = 0; var1 < gatherListID.length; ++var1) {
            if (gatherListID[var1] == var0) {
                return;
            }
        }

        var1 = -1;

        for (int var2 = 0; var2 < gatherListID.length; ++var2) {
            if (gatherListID[var2] < 0) {
                var1 = var2;
                break;
            }
        }

        if (var1 == -1) {
            var1 = gatherListID.length;
            short[] old = gatherListID;
            short[] var4;
            var4 = new short[gatherListID.length + 10];
            System.arraycopy(old, 0, var4, 0, old.length);

            for (int var3 = old.length; var3 < var4.length; ++var3) {
                var4[var3] = -1;
            }

            gatherListID = var4;
        }

        gatherListID[var1] = var0;
    }

    public static void clearGatherItems() {
        for (int i = 0; i < gatherListID.length; ++i) {
            gatherListID[i] = -1;
        }
    }

    public static String getGatherItemsCsv() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < gatherListID.length; ++i) {
            if (gatherListID[i] >= 0) {
                if (buffer.length() > 0) {
                    buffer.append(",");
                }
                buffer.append(gatherListID[i]);
            }
        }
        return buffer.toString();
    }

    public static void setGatherItemsFromCsv(String csv) {
        clearGatherItems();
        if (csv != null && csv.trim().length() > 0) {
            String[] parts = Code.splitString(csv, ",");
            for (int i = 0; i < parts.length; ++i) {
                try {
                    String item = parts[i].trim();
                    if (item.length() > 0) {
                        addItemGather(Short.parseShort(item));
                    }
                } catch (Exception e) {
                }
            }
        }
        sortListGather();
        save();
    }
    
    public static void sortListGather() {
        for (int var0 = 0; var0 < gatherListID.length; ++var0) {
            if (gatherListID[var0] > 0) {
                for (int var1 = 0; var1 <= var0; ++var1) {
                    if (gatherListID[var1] == -1) {
                        gatherListID[var1] = gatherListID[var0];
                        gatherListID[var0] = -1;
                        break;
                    }
                }
            }
        }
    }
}
