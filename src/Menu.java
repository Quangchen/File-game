

import javax.microedition.lcdui.Image;

public final class Menu implements IActionListener {
   public boolean showMenu;
   private MyVector menuItems;
   public int menuSelectedItem;
   private int e;
   private int f;
   private int g;
   private int h;
   private int i;
   private static int j;
   private static int k;
   private static int l;
   private static int m;
   private Command left;
   private Command right;
   private Command center;
   private static Image q = GameCanvas.loadImage("/hd/btnlBig0.png");
   private static Image r = GameCanvas.loadImage("/hd/btnlBig1.png");
   boolean c;
   private int s;
   private int t;
   private int u;
   private int[] v;
   private boolean w;
   private boolean x;
   private int y;
   private int z;
   private int aa;
   private int ab;

   public Menu() {
      this.left = new Command(mResources.chon, 0);
      this.right = GameCanvas.isTouch ? null : new Command(mResources.am, GameCanvas.width - 71, GameCanvas.height - mScreen.cmdH + 1);
      this.center = null;
      this.v = new int[3];
   }

   public final void showMenu(MyVector menus) {
      this.c = false;
      ChatPopup.currentMultilineChatPopup = null;
      InfoDlg.d();
      if (menus.size() != 0) {
         this.menuItems = menus;
         this.g = 60;
         this.h = 60;

         for(int i = 0; i < menus.size(); ++i) {
            Command var3 = (Command)menus.elementAt(i);
            if (mFont.tahoma_7_yellow.a(var3.caption) > this.g - 8) {
               var3.subCaption = mFont.tahoma_7_yellow.b(var3.caption, this.g - 8);
            }
         }

         this.e = (GameCanvas.width - menus.size() * this.g) / 2;
         if (this.e <= 0) {
            this.e = 1;
         }

         this.f = GameCanvas.height - this.h - (Paint.f + 1);
         if (GameCanvas.isTouch) {
            this.f -= 3;
         }

         this.i = this.f;
         this.showMenu = true;
         this.menuSelectedItem = 0;
         if ((l = this.menuItems.size() * this.g - GameCanvas.width) < 0) {
            l = 0;
         }

         j = 0;
         k = 0;
         m = 50;
         this.s = menus.size() * this.g - 1;
         if (this.s > GameCanvas.width - 2) {
            this.s = GameCanvas.width - 2;
         }

         if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
         }
      }

   }

   public final void update() {
      if (this.showMenu) {
         boolean flag = false;
         if (!GameCanvas.keyPressedz[2] && !GameCanvas.keyPressedz[4]) {
            if (!GameCanvas.keyPressedz[8] && !GameCanvas.keyPressedz[6]) {
               if (GameCanvas.keyPressedz[5]) {
                  if (this.center != null) {
                     if (this.center.idAction > 0) {
                        if (this.center.actionListener == GameScr.getInstance()) {
                           GameScr.getInstance().b(this.center.idAction, this.center.obj);
                        } else {
                           this.perform(this.center.idAction, this.center.obj);
                        }
                     }
                  } else {
                     this.y = 2;
                  }
               } else if (GameCanvas.keyPressedz[12]) {
                  if (this.left.idAction > 0) {
                     this.perform(this.left.idAction, this.left.obj);
                  } else {
                     this.y = 2;
                  }
               } else if (!this.c && (GameCanvas.keyPressedz[13] || mScreen.a(this.right))) {
                  this.showMenu = false;
                  InfoDlg.d();
               }
            } else {
               flag = true;
               ++this.menuSelectedItem;
               if (this.menuSelectedItem > this.menuItems.size() - 1) {
                  this.menuSelectedItem = 0;
               }
            }
         } else {
            flag = true;
            --this.menuSelectedItem;
            if (this.menuSelectedItem < 0) {
               this.menuSelectedItem = this.menuItems.size() - 1;
            }
         }

         this.center = null;
         if (GameScr.cc && !GameCanvas.isTouch && this.menuSelectedItem != -1) {
            Command var2 = (Command)this.menuItems.elementAt(this.menuSelectedItem);
            ChatTab var3;
            if ((var3 = ChatManager.getInstance().a(var2.caption)) != null && var3.a == 2) {
               this.center = new Command(mResources.an, this, 1000, var3);
            }
         }

         if (flag) {
            if ((j = this.menuSelectedItem * this.g + this.g - GameCanvas.width / 2) > l) {
               j = l;
            }

            if (j < 0) {
               j = 0;
            }

            if (this.menuSelectedItem == this.menuItems.size() - 1 || this.menuSelectedItem == 0) {
               k = j;
            }
         }

         if (!this.c && GameCanvas.isPointerJustRelease && !GameCanvas.c(this.e, this.f, this.s, this.h) && !this.w) {
            this.t = this.u = 0;
            this.w = false;
            this.showMenu = false;
            GameCanvas.isPointerJustRelease = false;
            return;
         }

         int var4;
         int var6;
         if (GameCanvas.m) {
            if (!this.w && GameCanvas.c(this.e, this.f, this.s, this.h)) {
               for(var4 = 0; var4 < this.v.length; ++var4) {
                  this.v[0] = GameCanvas.p;
               }

               this.u = GameCanvas.p;
               this.w = true;
               this.x = this.z != 0;
               this.z = 0;
            } else if (this.w) {
               ++this.t;
               if (this.t > 5 && this.u == GameCanvas.p && !this.x) {
                  this.u = -1000;
                  this.menuSelectedItem = (j + GameCanvas.p - this.e) / this.g;
               }

               if ((var4 = GameCanvas.p - this.v[0]) != 0 && this.menuSelectedItem != -1) {
                  this.menuSelectedItem = -1;
               }

               for(var6 = this.v.length - 1; var6 > 0; --var6) {
                  this.v[var6] = this.v[var6 - 1];
               }

               this.v[0] = GameCanvas.p;
               if ((j -= var4) < 0) {
                  j = 0;
               }

               if (j > l) {
                  j = l;
               }

               if (k < 0 || k > l) {
                  var4 /= 2;
               }

               k -= var4;
            }
         }

         if (GameCanvas.isPointerJustRelease && this.w) {
            var4 = GameCanvas.p - this.v[0];
            GameCanvas.isPointerJustRelease = false;
            if (Res.e(var4) < 20 && Res.e(GameCanvas.p - this.u) < 20 && !this.x) {
               this.z = 0;
               j = k;
               this.u = -1000;
               this.menuSelectedItem = (j + GameCanvas.p - this.e) / this.g;
               this.t = 0;
               this.y = 10;
            } else if (this.menuSelectedItem != -1 && this.t > 5) {
               this.t = 0;
               this.y = 1;
            } else if (this.menuSelectedItem == -1 && !this.x) {
               if (k < 0) {
                  j = 0;
               } else if (k > l) {
                  j = l;
               } else {
                  byte var5;
                  if ((var6 = GameCanvas.p - this.v[0] + (this.v[0] - this.v[1]) + (this.v[1] - this.v[2])) > 10) {
                     var5 = 10;
                  } else if (var6 < -10) {
                     var5 = -10;
                  } else {
                     var5 = 0;
                  }

                  this.z = -var5 * 100;
               }
            }

            this.w = false;
            this.t = 0;
            GameCanvas.isPointerJustRelease = false;
         }

         GameCanvas.k();
         GameCanvas.l();
      }

   }

   public final void a(mGraphics var1) {
      try {
         var1.translateXY(-var1.getTranslateX(), -var1.getTranslateY());
         var1.translateXY(-k, 0);
         int var2;
         String[] var3;
         int var4;
         int var5;
         if (GameCanvas.isTouch) {
            for(var2 = 0; var2 < this.menuItems.size(); ++var2) {
               if (var2 == this.menuSelectedItem) {
                  var1.drawImage(r, this.e + var2 * this.g + 1, this.i + 1, 0);
               } else {
                  var1.drawImage(q, this.e + var2 * this.g + 1, this.i + 1, 0);
               }

               if ((var3 = ((Command)this.menuItems.elementAt(var2)).subCaption) == null) {
                  var3 = new String[]{((Command)this.menuItems.elementAt(var2)).caption};
               }

               var4 = this.i + (this.h - var3.length * 14) / 2 + 1;

               for(var5 = 0; var5 < var3.length; ++var5) {
                  if (GameScr.cc) {
                     if (ChatManager.getInstance().d(var3[var5])) {
                        if (GameCanvas.gameTick % 10 > 5) {
                           mFont.tahoma_7_red.writeText(var1, var3[var5], this.e + var2 * this.g + this.g / 2 - 2, var4 + var5 * 14, 2);
                        } else {
                           mFont.tahoma_7_yellow.writeText(var1, var3[var5], this.e + var2 * this.g + this.g / 2 - 2, var4 + var5 * 14, 2);
                        }
                     } else {
                        mFont.tahoma_7_yellow.writeText(var1, var3[var5], this.e + var2 * this.g + this.g / 2 - 2, var4 + var5 * 14, 2);
                     }
                  } else {
                     mFont.tahoma_7_yellow.writeText(var1, var3[var5], this.e + var2 * this.g + this.g / 2 - 2, var4 + var5 * 14, 2);
                  }
               }
            }
         } else {
            for(var2 = 0; var2 < this.menuItems.size(); ++var2) {
               if (var2 == this.menuSelectedItem) {
                  var1.drawImage(r, this.e + var2 * this.g + 1, this.i + 1 - 23, 0);
               } else {
                  var1.drawImage(q, this.e + var2 * this.g + 1, this.i + 1 - 23, 0);
               }

               if ((var3 = ((Command)this.menuItems.elementAt(var2)).subCaption) == null) {
                  var3 = new String[]{((Command)this.menuItems.elementAt(var2)).caption};
               }

               var4 = this.i + (this.h - var3.length * 14) / 2 + 1 - 23;

               for(var5 = 0; var5 < var3.length; ++var5) {
                  if (GameScr.cc) {
                     if (ChatManager.getInstance().d(var3[var5])) {
                        if (GameCanvas.gameTick % 10 > 5) {
                           mFont.tahoma_7_red.writeText(var1, var3[var5], this.e + var2 * this.g + this.g / 2 - 2, var4 + var5 * 14, 2);
                        } else {
                           mFont.tahoma_7_yellow.writeText(var1, var3[var5], this.e + var2 * this.g + this.g / 2 - 2, var4 + var5 * 14, 2);
                        }
                     } else {
                        mFont.tahoma_7_yellow.writeText(var1, var3[var5], this.e + var2 * this.g + this.g / 2 - 2, var4 + var5 * 14, 2);
                     }
                  } else {
                     mFont.tahoma_7_yellow.writeText(var1, var3[var5], this.e + var2 * this.g + this.g / 2 - 2, var4 + var5 * 14, 2);
                  }
               }
            }
         }

         var1.translateXY(-var1.getTranslateX(), -var1.getTranslateY());
      } catch (Exception var6) {
         var6.printStackTrace();
      }
   }

   public final void b() {
      if (this.z != 0 && !this.w) {
         if ((j += this.z / 100) < 0) {
            j = 0;
         } else if (j > l) {
            j = l;
         } else {
            k = j;
         }

         this.z = this.z * 9 / 10;
         if (this.z < 100 && this.z > -100) {
            this.z = 0;
         }
      }

      if (k != j && !this.w) {
         this.aa = j - k << 2;
         this.ab += this.aa;
         k += this.ab >> 4;
         this.ab &= 15;
      }

      if (this.i > this.f) {
         int var1;
         if ((var1 = this.i - this.f >> 1) <= 0) {
            var1 = 1;
         }

         this.i -= var1;
      }

      if (m != 0 && (m >>= 1) < 0) {
         m = 0;
      }

      if (this.y > 0) {
         --this.y;
         GameScr.cc = false;
         if (this.y == 0) {
            this.showMenu = false;
            Command var2;
            if (this.menuSelectedItem >= 0 && (var2 = (Command)this.menuItems.elementAt(this.menuSelectedItem)) != null) {
               var2.a();
            }
         }
      }

   }

   public final int findIndexByCaptionEquals(String text) {
      if (text == null || this.menuItems == null) {
         return -1;
      }

      for(int i = 0; i < this.menuItems.size(); ++i) {
         Command cmd = (Command)this.menuItems.elementAt(i);
         if (cmd != null && cmd.caption != null && cmd.caption.equals(text)) {
            return i;
         }
      }

      return -1;
   }

   public final int findIndexByCaptionContains(String text) {
      if (text == null || this.menuItems == null) {
         return -1;
      }

      for(int i = 0; i < this.menuItems.size(); ++i) {
         Command cmd = (Command)this.menuItems.elementAt(i);
         if (cmd != null && cmd.caption != null && cmd.caption.indexOf(text) >= 0) {
            return i;
         }
      }

      return -1;
   }

   public final void perform(int var1, Object var2) {
      if (var1 == 1000) {
         ChatTab var3 = (ChatTab)var2;
         this.menuItems.removeAllElements();
         ChatManager.getInstance().e(var3.b);
         ChatManager.getInstance().a.removeElement(var3);

         for(var1 = 0; var1 < ChatManager.getInstance().a.size(); ++var1) {
            ChatTab var4 = (ChatTab)ChatManager.getInstance().a.elementAt(var1);
            this.menuItems.addElement(new Command(var4.b, (IActionListener)null, 12001, new Integer(var1)));
         }

         this.menuItems.addElement(new Command(mResources.pv, (IActionListener)null, 12006, (Object)null));
         this.menuItems.addElement(new Command(mResources.pw, (IActionListener)null, 12008, (Object)null));

         for(var1 = 0; var1 < this.menuItems.size(); ++var1) {
            Command var5 = (Command)this.menuItems.elementAt(var1);
            if (mFont.tahoma_7_yellow.a(var5.caption) > this.g - 8) {
               var5.subCaption = mFont.tahoma_7_yellow.b(var5.caption, this.g - 8);
            }
         }

         l = this.menuItems.size() * this.g - GameCanvas.width;
         if ((j = this.menuSelectedItem * this.g + this.g - GameCanvas.width / 2) > l) {
            j = l;
         }

         if (j < 0) {
            j = 0;
         }

         if (this.menuSelectedItem == this.menuItems.size() - 1 || this.menuSelectedItem == 0) {
            k = j;
         }
      }

   }
}
