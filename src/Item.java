

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.microedition.lcdui.Image;

public final class Item {
   public int indexFrame;
   public ItemTemplate template;
   public MyVector options;
   public int itemId;
   public int indexUI;
   public int quantity;
   public long expires;
   public boolean isLock;
   public int sys;
   public int upgrade;
   public int buyCoin;
   public int buyCoinLock;
   public int buyGold;
   public int saleCoinLock;
   public int typeUI;
   public boolean isExpires;
   public EffectCharPaint eff;
   public int indexEff;
   public Image img;
   public boolean s = false;
   public long t = 0L;
   public long u = 0L;
   public boolean v = false;
   public boolean w = false;
   public int x = 0;
   public long y = 0L;
   public long timeThrow = 0L;
   public boolean isCheckThrow = false;

   public Item() {
   }

   public final Item clone() {
      Item newItem;
      (newItem = new Item()).template = this.template;
      if (this.options != null) {
         newItem.options = new MyVector();

         for(int var2 = 0; var2 < this.options.size(); ++var2) {
            ItemOption var3;
            (var3 = new ItemOption()).optionTemplate = ((ItemOption)this.options.elementAt(var2)).optionTemplate;
            var3.param = ((ItemOption)this.options.elementAt(var2)).param;
            newItem.options.addElement(var3);
         }
      }

      newItem.itemId = this.itemId;
      newItem.indexUI = this.indexUI;
      newItem.quantity = this.quantity;
      newItem.expires = this.expires;
      newItem.isLock = this.isLock;
      newItem.sys = this.sys;
      newItem.upgrade = this.upgrade;
      newItem.buyCoin = this.buyCoin;
      newItem.buyCoinLock = this.buyCoinLock;
      newItem.buyGold = this.buyGold;
      newItem.saleCoinLock = this.saleCoinLock;
      newItem.typeUI = this.typeUI;
      newItem.isExpires = this.isExpires;
      return newItem;
   }

   public final Item viewNext(int var1) {
      Item var2;
      (var2 = this.clone()).isLock = true;
      int var3;
      if ((var3 = var1 - var2.upgrade) == 0) {
         return var2;
      } else {
         var2.upgrade = var1;
         if (var2.options != null) {
            for(var1 = 0; var1 < var2.options.size(); ++var1) {
               ItemOption var4;
               if ((var4 = (ItemOption)var2.options.elementAt(var1)).optionTemplate.id != 6 && var4.optionTemplate.id != 7) {
                  if (var4.optionTemplate.id != 8 && var4.optionTemplate.id != 9 && var4.optionTemplate.id != 19) {
                     if (var4.optionTemplate.id != 10 && var4.optionTemplate.id != 11 && var4.optionTemplate.id != 12 && var4.optionTemplate.id != 13 && var4.optionTemplate.id != 14 && var4.optionTemplate.id != 15 && var4.optionTemplate.id != 17 && var4.optionTemplate.id != 18 && var4.optionTemplate.id != 20) {
                        if (var4.optionTemplate.id != 21 && var4.optionTemplate.id != 22 && var4.optionTemplate.id != 23 && var4.optionTemplate.id != 24 && var4.optionTemplate.id != 25 && var4.optionTemplate.id != 26) {
                           if (var4.optionTemplate.id == 16) {
                              var4.param += var3 * 3;
                           }
                        } else {
                           var4.param += var3 * 150;
                        }
                     } else {
                        var4.param += var3 * 5;
                     }
                  } else {
                     var4.param += var3 * 10;
                  }
               } else {
                  var4.param += var3 * 15;
               }
            }
         }

         return var2;
      }
   }

   public final boolean isTypeBody() {
      return this.template.type >= 0 && this.template.type <= 15;
   }

   public final boolean isTypeMounts() {
      return 29 <= this.template.type && this.template.type <= 33;
   }
   
   public final boolean isTypeBijuus() {
      return 35 <= this.template.type && this.template.type <= 38;
   }

   public final boolean isTypeNgocKham() {
      return this.template.type == 34;
   }

   public final String getExpiresString() {
      if (this.expires <= 0L) {
         return mResources.er;
      } else {
         Calendar var1;
         (var1 = Calendar.getInstance()).setTimeZone(TimeZone.getTimeZone("GMT+7"));
         var1.setTime(new Date(this.expires));
         int var2 = var1.get(11);
         int var3 = var1.get(12);
         int var4 = var1.get(5);
         int var5 = var1.get(2) + 1;
         int var6 = var1.get(1);
         return var4 + "/" + var5 + "/" + var6 + " " + var2 + "h" + var3 + "'";
      }
   }

   public final String getExpiresShopString() {
      if (this.expires <= 0L) {
         return mResources.er;
      } else if (this.expires / 1000L >= 2592000L) {
         return this.expires / 1000L / 2592000L + " " + mResources.kz;
      } else if (this.expires / 1000L >= 604800L) {
         return this.expires / 1000L / 604800L + " " + mResources.la;
      } else if (this.expires / 1000L >= 86400L) {
         return this.expires / 1000L / 86400L + " " + mResources.lb;
      } else if (this.expires / 1000L >= 3600L) {
         return this.expires / 1000L / 3600L + " " + mResources.lc;
      } else {
         return this.expires / 1000L >= 60L ? this.expires / 1000L / 60L + " " + mResources.le : "";
      }
   }

   public final void clearExpire() {
      if (!this.isTypeMounts()) {
         this.expires = 0L;
      }

   }

   public final boolean isTypeUIMe() {
      return this.typeUI == 5 || this.typeUI == 3 || this.typeUI == 4 || this.typeUI == 39;
   }

   public final boolean i() {
      return this.isTypeUIShop() || this.l() || this.m() || this.n() || this.o();
   }

   public final boolean isTypeUIShop() {
      return this.typeUI == 20 || this.typeUI == 21 || this.typeUI == 22 || this.typeUI == 23 || this.typeUI == 24 || this.typeUI == 25 || this.typeUI == 26 || this.typeUI == 27 || this.typeUI == 28 || this.typeUI == 29 || this.typeUI == 16 || this.typeUI == 17 || this.typeUI == 18 || this.typeUI == 19 || this.typeUI == 2 || this.typeUI == 6 || this.typeUI == 8 || this.typeUI == 34;
   }

   public final boolean isTypeUIShopLock() {
      return this.typeUI == 7 || this.typeUI == 9;
   }

   public final boolean l() {
      return this.typeUI == 14;
   }

   public final boolean m() {
      return this.typeUI == 15;
   }

   public final boolean n() {
      return this.typeUI == 32;
   }

   public final boolean o() {
      return this.typeUI == 34;
   }

   public final boolean isUpMax() {
      return this.q() == this.upgrade;
   }

   public final int q() {
      if (this.template.level > 0 && this.template.level < 20) {
         return 4;
      } else if (this.template.level >= 20 && this.template.level < 40) {
         return 8;
      } else if (this.template.level >= 40 && this.template.level < 50) {
         return 12;
      } else {
         return this.template.level >= 50 && this.template.level < 60 ? 14 : 16;
      }
   }

   public final boolean isTypeClothe() {
      return this.template.type == 0 || this.template.type == 2 || this.template.type == 4 || this.template.type == 6 || this.template.type == 8;
   }

   public final boolean isTypeAdorn() {
      return this.template.type == 3 || this.template.type == 5 || this.template.type == 7 || this.template.type == 9;
   }

   public final boolean isTypeWeapon() {
      return this.template.type == 1;
   }
   
   public final boolean isNewitem() {
        if (this.options != null) {
            for (int i = 0; i < this.options.size(); ++i) {
                if (((ItemOption) this.options.elementAt(i)).optionTemplate.id == 155) {
                    return true;
                }
            }
        }

        return false;
    }

   public final int getTinhLuyen(int var1) {
      if (this.options != null) {
         for(var1 = 0; var1 < this.options.size(); ++var1) {
            ItemOption var2;
            if ((var2 = (ItemOption)this.options.elementAt(var1)) != null && var2.optionTemplate.id == 85) {
               return var2.param;
            }
         }
      }

      return -1;
   }
   
   public final int getTinhLuyen() {
        if (this.options == null || this.options.size() == 0) {
            return -1;
        }
        if (this.options != null) {
           for(int i = 0; i < this.options.size(); ++i) {
              ItemOption option = (ItemOption)this.options.elementAt(i);
              if (option != null && option.optionTemplate.id == 85) {
                 return option.param;
              }
           }
        }
      return -1;
   }

   public final boolean hasOption(int optionID) {
      if (this.options != null) {
         for(int var2 = 0; var2 < this.options.size(); ++var2) {
            ItemOption var3;
            if ((var3 = (ItemOption)this.options.elementAt(var2)) != null && var3.optionTemplate.id == optionID) {
               return true;
            }
         }
      }

      return false;
   }

   public final int countOptionByType(int type) {
      int var2 = 0;
      if (this.options != null) {
         for(int var3 = 0; var3 < this.options.size(); ++var3) {
            ItemOption var4;
            if ((var4 = (ItemOption)this.options.elementAt(var3)) != null && var4.optionTemplate.type == type) {
               ++var2;
            }
         }
      }

      return var2;
   }
}
