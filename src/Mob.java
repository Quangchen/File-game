
// Source code is decompiled from instance .class file using FernFlower decompiler.
import javax.microedition.lcdui.Image;

public final class Mob extends MainObject {
   public static MobTemplate[] mobTemplates;
   public int hp;
   public int maxHp;
   public int curX;
   public int curY;
   private int ae;
   public int g = 1;
   private int af = 1;
   public int h;
   private int ag;
   private int ah;
   private int ai;
   public int cx;
   public int cy;
   public int k;
   public int l;
   private int aj;
   public short m;
   public boolean n;
   public boolean o;
   public boolean p;
   public boolean q;
   public boolean r;
   private MyVector ak = new MyVector();
   public int id;
   private Char al;
   private BuNhin am;
   public int t;
   public int u;
   public int sys;
   private int an;
   public short levelBoss;
   public short lv;
   public boolean isBoss;
   private long ao = 0L;
   private int ap = 0;
   public static Char z;
   public static MyVector aa = new MyVector();
   private static EggMonters aq;
   private static long ar;
   private boolean as;
   public boolean ab = true;
   private long at;
   public Char ac;
   public boolean ad = false;
   private Mob au;
   private Char av;
   private short aw;
   private byte ax;
   private byte ay;
   private byte az = -1;
   private static byte[][] ba = new byte[][]{{3, 4, 5, 6}, new byte[1], {2, 2, 2, 2, 3, 3, 3, 3}, {0, 1}, {0, 1}, {3, 4, 5}, new byte[1], {3, 3, 4, 4, 5, 5}, new byte[1], {3, 4, 5}, {0, 1, 2, 3, 4}, {3, 4, 5}, {4, 5, 6}, new byte[1], {0, 1}, {0, 1}, {3, 3, 4, 4, 5, 5}, {0, 1, 2}, {0, 1, 2}, {5, 6, 7, 8}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {3, 4, 5, 6}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}, new byte[1], new byte[1], new byte[1], new byte[1], {0, 1, 2}, new byte[1], new byte[1], {0, 0, 1, 1, 2, 2}, {0, 0, 1, 1, 2, 2, 3, 3, 4, 4}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 1}, {0, 1}};
   private static byte[][] bb = new byte[][]{{0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, new byte[1]};
   private static byte[][] bc = new byte[][]{{5, 4, 3, 2, 1}, new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, {5, 4, 3, 2, 1}, new byte[0], {5, 4, 3, 2, 1}, new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], new byte[0], {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, new byte[1]};

   public Mob(short var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, int var9, int var10, int var11, short var12, short var13, byte var14, byte var15, boolean var16, boolean var17) {
      this.n = var2;
      this.o = var3;
      this.p = var4;
      this.q = var5;
      this.r = var6;
      this.sys = var8;
      this.m = var1;
      this.id = var7;
      this.hp = var9;
      this.lv = (short)var10;
      this.cx = this.curX = var12;
      this.cy = this.curY = var13;
      if (var7 != 168 && var7 != 179 && var7 != 175 && var7 != 177 && var7 != 202) {
         this.h = var14;
      } else {
         this.h = 8;
      }

      this.maxHp = var11;
      this.levelBoss = var15;
      this.isBoss = var16;
      if (var7 == 202) {
         aq = new EggMonters(this.cx, this.cy - 100);
         aa.addElement(aq);
         EggMonters.f = this;
      }

      if (mobTemplates[var7].h == null) {
         mobTemplates[var7].h = new Image[0];
         Service.getInstance().a(var7);
      }

      ar = (long)Res.b(2000, 3500);
      this.as = var17;
   }

   public final void a() {
      if (this.hp > 0) {
         this.aj = 4;
         this.h = 7;
      }

   }

   public final void a(Char var1) {
      label78: {
         this.ab = true;
         this.al = var1;
         this.ag = 0;
         this.ah = 0;
         this.h = 3;
         if (this.id != 209 && this.id != 210) {
            label77: {
               if (this.id != 168 && this.id != 176 && this.id != 177 && this.id != 179) {
                  if (this.id != 169 && this.id != 171 && this.id != 172 && this.id != 182) {
                     if (this.id == 175) {
                        this.ap = 7;
                        break label78;
                     }

                     if (this.id != 181 && this.id != 185 && this.id != 188 && this.id != 194 && this.id != 192) {
                        if (this.id != 183 && this.id != 170 && this.id != 193) {
                           if (this.id != 187 && this.id != 168 && this.id != 175 && this.id != 176 && this.id != 179 && this.id != 174) {
                              break label77;
                           }

                           this.ap = this.d(GameCanvas.gameTick);
                           break label78;
                        }

                        this.ap = 4;
                        break label78;
                     }

                     this.ap = 3;
                     break label78;
                  }

                  this.ap = 2;
                  break label78;
               }

               this.ap = 6;
               break label78;
            }
         }

         this.ap = 0;
      }

      this.an = 0;
   }

   public final void a(BuNhin var1) {
      this.am = var1;
      this.ag = 0;
      this.ah = 0;
      this.h = 3;
      this.an = 1;
   }

   private void h() {
      if (!Code.isBangMob) {
         this.ae = this.isBoss ? (this.getMobTemplate().e == 204 ? 9 : (this.getMobTemplate().e == 203 ? 9 : (this.getMobTemplate().e == 139 ? 4 : (this.getMobTemplate().e == 160 ? 12 : 10)))) : 2;
         if (this.getMobTemplate().e != 209 && this.getMobTemplate().e != 210) {
            if (this.getMobTemplate().e == 141) {
               this.ae = 13;
            } else if (this.getMobTemplate().e != 169 && this.getMobTemplate().e != 170 && this.getMobTemplate().e != 171 && this.getMobTemplate().e != 172 && this.getMobTemplate().e != 182) {
               if (this.getMobTemplate().e != 168 && this.getMobTemplate().e != 176 && this.getMobTemplate().e != 177 && this.getMobTemplate().e != 179 && this.getMobTemplate().e != 180) {
                  if (this.getMobTemplate().e != 173 && this.getMobTemplate().e != 184) {
                     if (this.getMobTemplate().e != 181 && this.getMobTemplate().e != 178 && this.getMobTemplate().e != 185 && this.getMobTemplate().e != 202) {
                        if (this.getMobTemplate().e == 174) {
                           this.ae = 10;
                        } else if (this.getMobTemplate().e == 183) {
                           this.ae = 5;
                        } else if (this.getMobTemplate().e == 175) {
                           this.ae = 8;
                        }
                     } else {
                        this.ae = 4;
                     }
                  } else {
                     this.ae = 6;
                  }
               } else {
                  this.ae = 7;
               }
            } else {
               this.ae = 3;
            }
         } else {
            this.ae = 3;
         }

         --this.aj;
         if (this.aj > 0) {
            if (mobTemplates[this.id].c != 0) {
               int var1 = -this.ac.cDir << 1;
               if (this.curX > this.cx - mobTemplates[this.id].a && this.curX < this.cx + mobTemplates[this.id].a) {
                  this.curX -= var1;
                  return;
               }
            }
         } else {
            if ((this.ac == null || !this.ad) && this.hp != 0) {
               this.h = 5;
               if (this.ac != null) {
                  this.g = -this.ac.cDir;
                  if (Res.e(this.curX - this.ac.cx) < 24) {
                     this.h = 2;
                  }
               }

               this.ag = this.ah = this.ai = 0;
               this.aj = 0;
            } else {
               this.h = 1;
               this.ah = this.ac.cDir << 3;
               this.ag = -5;
               this.ai = 0;
            }

            this.ac = null;
         }

      }
   }

   private void i() {
      try {
         if (Code.isBangMob) {
            return;
         }

         if (this.ad) {
            this.h = 1;
            this.ah = this.ac.cDir << 3;
            this.ag = -5;
            this.ai = 0;
         }

         if (!this.q) {
            if (!this.o && !this.r) {
               byte var1;
               switch (mobTemplates[this.id].c) {
                  case 0:
                     if (this.id == 176) {
                        this.h = 9;
                     }

                     this.ae = 0;
                     return;
                  case 1:
                  case 2:
                  case 3:
                     var1 = mobTemplates[this.id].b;
                     if (mobTemplates[this.id].b == 1) {
                        if (GameCanvas.gameTick % 2 == 1) {
                           return;
                        }
                     } else if (var1 > 2) {
                        var1 = (byte)(var1 + this.m % 2);
                     } else if (GameCanvas.gameTick % 2 == 1) {
                        --var1;
                     }

                     this.curX += var1 * this.g;
                     if (Res.b(0, mobTemplates[this.id].a) == mobTemplates[this.id].a / 3) {
                        this.h = 2;
                        this.at = System.currentTimeMillis();
                     }

                     if (this.curX > this.cx + mobTemplates[this.id].a) {
                        this.g = -1;
                        if (this.id == 168 || this.id == 177) {
                           this.h = 9;
                           this.ag = 0;
                        }
                     } else if (this.curX < this.cx - mobTemplates[this.id].a) {
                        this.g = 1;
                        if (this.id == 168 || this.id == 177) {
                           this.h = 9;
                           this.ag = 0;
                        }
                     }

                     if (!this.isBoss) {
                        if (this.id < 168) {
                           this.ae = GameCanvas.gameTick % 4 > 1 ? 0 : 1;
                           return;
                        }

                        this.ae = this.c(GameCanvas.gameTick);
                        return;
                     }

                     this.ae = mobTemplates[this.id].k[this.ap];
                     return;
                  case 4:
                     var1 = (byte)(mobTemplates[this.id].b + this.m % 2);
                     this.curX += var1 * this.g;
                     if (GameCanvas.gameTick % 10 > 2 && this.id != 205 && this.id != 206 && this.id != 207 && this.id != 208) {
                        this.az = -1;
                        this.curY += var1 * this.af;
                     }

                     if (this.curX > this.cx + mobTemplates[this.id].a) {
                        this.g = -1;
                        if (this.id == 179 || this.id == 175) {
                           this.h = 9;
                        }

                        this.ag = 0;
                     } else if (this.curX < this.cx - mobTemplates[this.id].a) {
                        this.g = 1;
                        if (this.id == 179 || this.id == 175) {
                           this.h = 9;
                        }

                        this.ag = 0;
                     }

                     if (this.curY > this.cy + 24) {
                        this.af = -1;
                     } else if (this.curY < this.cy - (20 + GameCanvas.gameTick % 10)) {
                        this.af = 1;
                     }

                     if (!this.isBoss) {
                        if (this.id < 168) {
                           this.ae = GameCanvas.gameTick % 4 > 1 ? 0 : 1;
                           return;
                        }

                        this.ae = this.c(GameCanvas.gameTick);
                        return;
                     }

                     this.ae = mobTemplates[this.id].k[this.ap];
                     return;
                  case 5:
                     var1 = (byte)(mobTemplates[this.id].b + this.m % 2);
                     this.curX += var1 * this.g;
                     var1 = (byte)(var1 + (GameCanvas.gameTick + this.m) % 2);
                     if (GameCanvas.gameTick % 10 > 2) {
                        this.curY += var1 * this.af;
                     }

                     if (this.curX > this.cx + mobTemplates[this.id].a) {
                        this.g = -1;
                        if (this.id != 179 && this.id != 175) {
                           this.h = 2;
                        } else {
                           this.h = 9;
                        }

                        this.ag = 0;
                     } else if (this.curX < this.cx - mobTemplates[this.id].a) {
                        this.g = 1;
                        if (this.id != 179 && this.id != 175) {
                           this.h = 2;
                        } else {
                           this.h = 9;
                        }

                        this.ag = 0;
                     }

                     if (this.curY > this.cy + 24) {
                        this.af = -1;
                     } else if (this.curY < this.cy - (20 + GameCanvas.gameTick % 10)) {
                        this.af = 1;
                     }

                     if (TileMap.a(this.curX, this.curY, 2)) {
                        if (GameCanvas.gameTick % 10 > 5) {
                           this.curY = TileMap.b(this.curY);
                           this.h = 4;
                           this.ag = 0;
                        }

                        this.af = -1;
                     }

                     if (!this.isBoss) {
                        if (this.id < 168) {
                           this.ae = GameCanvas.gameTick % 4 > 1 ? 3 : 1;
                           return;
                        }

                        this.ae = this.c(GameCanvas.gameTick);
                        return;
                     }

                     this.ae = mobTemplates[this.id].k[this.ap];
                  default:
                     return;
               }
            }

            this.ae = 0;
            return;
         }
      } catch (Exception var2) {
      }

   }

   public final MobTemplate getMobTemplate() {
      return mobTemplates[this.id];
   }

   public final boolean c() {
      if (this.curX < GameScr.cmx) {
         return false;
      } else if (this.curX > GameScr.cmx + GameScr.gW) {
         return false;
      } else if (this.curY < GameScr.cmy) {
         return false;
      } else if (this.curY > GameScr.cmy + GameScr.gH + 30) {
         return false;
      } else if (mobTemplates[this.id] == null) {
         return false;
      } else {
         if (!this.isBoss) {
            if (this.ae >= mobTemplates[this.id].h.length) {
               return false;
            }

            if (mobTemplates[this.id].h[this.ae] == null) {
               return false;
            }

            if (this.id != 179 && this.id != 175 && this.id != 202) {
               if (this.id == 176 && (this.ae == 1 || this.ae == 0)) {
                  return false;
               }
            } else if (this.h == 8) {
               return false;
            }
         }

         return this.h != 0;
      }
   }

   public final void a(mGraphics var1) {
      if (FormToiUu.isHideAll()) {
         return;
      }

      if (this.c()) {
         int var2 = this.curY;
         if (this.id == 205 || this.id == 206 || this.id == 207 || this.id == 208) {
            ++this.az;
            if (this.az > Char.fs.length - 1) {
               this.az = 0;
            }
         }

         if (this.az >= 0) {
            var2 += Char.fs[this.az];
         }

         MobTemplate var3 = mobTemplates[this.id];
         if (!this.isBoss) {
            if (this.k == 0) {
               this.k = mGraphics.getWidth(var3.h[0]);
            }

            if (this.l == 0) {
               this.l = mGraphics.getHeight(var3.h[0]);
            }
         } else {
            this.k = 40;
            this.l = 40;
         }

         this.b(var1, this.curX, var2, 0);

         int var4;
         byte var5;
         int var9;
         try {
            var4 = this.g > 0 ? 0 : 2;
            if (this.id == 219) {
               var4 = 0;
            }

            if ((this.id == 98 || this.id == 99) && this.h == 1) {
               long var11;
               if ((var11 = System.currentTimeMillis()) - this.ao < 400L) {
                  var1.drawRegion(var3.h[this.ae], 0, 0, mGraphics.getWidth(var3.h[this.ae]), mGraphics.getHeight(var3.h[this.ae]), var4, this.curX, var2, StaticObj.d);
               } else if (var11 - this.ao < 800L) {
                  var1.drawRegion(var3.h[this.ae], 0, 0, mGraphics.getWidth(var3.h[this.ae]), 3 * mGraphics.getHeight(var3.h[this.ae]) / 5, var4, this.curX, var2, StaticObj.d);
               } else if (var11 - this.ao < 1200L) {
                  var1.drawRegion(var3.h[this.ae], 0, 0, mGraphics.getWidth(var3.h[this.ae]), mGraphics.getHeight(var3.h[this.ae]) / 3, var4, this.curX, var2, StaticObj.d);
               }

               if (GameCanvas.gameTick % 8 < 2) {
                  SmallImage.paintImage(var1, 457, this.curX, var2, 0, StaticObj.d);
               } else if (GameCanvas.gameTick % 8 < 4) {
                  SmallImage.paintImage(var1, 458, this.curX, var2, 0, StaticObj.d);
               } else if (GameCanvas.gameTick % 8 < 6) {
                  SmallImage.paintImage(var1, 459, this.curX, var2, 0, StaticObj.d);
               }
            } else if (this.isBoss) {
               if (this.getMobTemplate().j != null) {
                  Frame var6 = this.getMobTemplate().j[this.ae];

                  for(var9 = 0; var9 < var6.a.length; ++var9) {
                     MobTemplate var7 = this.getMobTemplate();
                     var5 = var6.c[var9];
                     ImageInfo var10 = var7.i[var5];
                     if (this.g > 0) {
                        var1.drawRegion(this.getMobTemplate().h[0], var10.a, var10.b, var10.c, var10.d, 0, this.curX + var6.a[var9], var2 + var6.b[var9] - 1, 20);
                     } else {
                        var1.drawRegion(this.getMobTemplate().h[0], var10.a, var10.b, var10.c, var10.d, 2, this.curX - var6.a[var9], var2 + var6.b[var9] - 1, 24);
                     }
                  }
               }
            } else {
               if (this.id == 168) {
                  int var10000 = this.h;
                  boolean var10001 = true;
               }

               var1.drawRegion(var3.h[this.ae], 0, 0, mGraphics.getWidth(var3.h[this.ae]), mGraphics.getHeight(var3.h[this.ae]), var4, this.curX, var2, StaticObj.d);
            }

            this.a(var1, this.curX, var2, 0);
         } catch (Exception var8) {
            var8.printStackTrace();
         }

         var4 = var2;
         int var12;
         if (Char.getMyChar().mobFocus != null && Char.getMyChar().mobFocus.equals(this) && this.h != 1) {
            int var13;
            if (this.isBoss) {
               var12 = this.maxHp;
               if (this.maxHp < this.hp) {
                  var12 = this.hp;
               }

               var9 = (int)((long)this.hp * 100L / (long)var12);
               var13 = this.k;
               var5 = 4;
               if (this.levelBoss == 1 || this.levelBoss == 2 || this.levelBoss == 3 || this.isBoss) {
                  var5 = 6;
                  var13 += var13 / 2;
               }

               var13 += 2;
               if ((var9 = var13 * var9 / 100) < 2) {
                  var9 = 2;
               }

               if (this.id == 140 || this.id == 160) {
                  var4 = var2 - 20;
               }

               if (this.id != 142 && this.id != 143) {
                  var1.setColor(16777215);
                  var1.fillRect(this.curX - var13 / 2 - 1, var4 - this.l - 12, var13, var5);
                  var1.setColor(this.d());
                  var1.fillRect(this.curX - var13 / 2 - 1, var4 - this.l - 12, var9, var5);
                  var1.setColor(0);
                  var1.drawRect(this.curX - var13 / 2 - 1, var4 - this.l - 12, var13, var5);
               } else {
                  SmallImage.paintImage(var1, 988, this.curX, var4 - this.l, 0, 33);
               }
            } else {
               var12 = this.maxHp;
               if (this.maxHp < this.hp) {
                  var12 = this.hp;
               }

               var9 = (int)((long)this.hp * 100L / (long)var12);
               var13 = this.k;
               if (this.id > 167) {
                  var13 = this.k / 2;
               }

               var5 = 4;
               if (this.levelBoss == 1 || this.levelBoss == 2 || this.levelBoss == 3 || this.isBoss) {
                  var5 = 6;
                  var13 += var13 / 2;
               }

               var13 += 2;
               if ((var9 = var13 * var9 / 100) < 2) {
                  var9 = 2;
               }

               if (this.id == 140 || this.id == 160) {
                  var4 = var2 - 20;
               }

               if (this.id != 142 && this.id != 143) {
                  var1.setColor(16777215);
                  var1.fillRect(this.curX - var13 / 2 - 1, var4 - this.l - 12, var13, var5);
                  var1.setColor(this.d());
                  var1.fillRect(this.curX - var13 / 2 - 1, var4 - this.l - 12, var9, var5);
                  var1.setColor(0);
                  var1.drawRect(this.curX - var13 / 2 - 1, var4 - this.l - 12, var13, var5);
               } else {
                  SmallImage.paintImage(var1, 988, this.curX, var4 - this.l, 0, 33);
               }
            }

            if (this.levelBoss > 0) {
               if (this.levelBoss == 1) {
                  mFont.tahoma_7_yellow.writeText(var1, mResources.je[this.levelBoss], this.curX, var4 - this.l - 26, 2, mFont.tahoma_7_grey);
               } else if (this.levelBoss == 2) {
                  mFont.tahoma_7_yellow.writeText(var1, mResources.je[this.levelBoss], this.curX, var4 - this.l - 26, 2, mFont.tahoma_7_grey);
               } else if (this.levelBoss == 3) {
                  mFont.tahoma_7_blue1.writeText(var1, mResources.je[this.levelBoss], this.curX, var4 - this.l - 26, 2, mFont.tahoma_7_grey);
               }

               if (this.n) {
                  SmallImage.paintImage(var1, 494, this.curX, var4 - this.l - 28, 0, 33);
               }
            } else if (this.n) {
               SmallImage.paintImage(var1, 494, this.curX, var4 - this.l - 15, 0, 33);
            }
         } else if (this.levelBoss > 0) {
            if (this.levelBoss == 1) {
               mFont.tahoma_7_yellow.writeText(var1, mResources.je[this.levelBoss], this.curX, var2 - this.l - 20, 2, mFont.tahoma_7_grey);
            } else if (this.levelBoss == 2) {
               mFont.tahoma_7_yellow.writeText(var1, mResources.je[this.levelBoss], this.curX, var2 - this.l - 20, 2, mFont.tahoma_7_grey);
            } else if (this.levelBoss == 3) {
               mFont.tahoma_7_blue1.writeText(var1, mResources.je[this.levelBoss], this.curX, var2 - this.l - 20, 2, mFont.tahoma_7_grey);
            }

            if (this.n) {
               SmallImage.paintImage(var1, 494, this.curX, var2 - this.l - 22, 0, 33);
            }
         } else if (this.n) {
            SmallImage.paintImage(var1, 494, this.curX, var2 - this.l - 5, 0, 33);
         }

         if (Code.showMobNameId && this.hp > 0 && mobTemplates[this.id] != null) {
            mFont.tahoma_7_yellow.writeText(var1, mobTemplates[this.id].name + "(" + this.id + ")", this.curX, var2 - this.l - 32, 2, mFont.tahoma_7_grey);
         }

         if (this.o) {
            if (GameCanvas.gameTick % 2 == 0) {
               SmallImage.paintImage(var1, 1082, this.curX, var2 - this.l / 2, 0, 3);
            } else {
               SmallImage.paintImage(var1, 1084, this.curX, var2 - this.l / 2, 0, 3);
            }
         }

         if (this.q) {
            SmallImage.paintImage(var1, 290, this.curX, var2, 0, 33);
         }

         if (this.r) {
            if ((var12 = GameCanvas.gameTick % 6) != 0 && var12 != 1) {
               if (var12 != 2 && var12 != 3) {
                  if (var12 == 4 || var12 == 5) {
                     SmallImage.paintImage(var1, 1000, this.curX, var2 - this.l - 5, 0, 3);
                  }
               } else {
                  SmallImage.paintImage(var1, 999, this.curX, var2 - this.l - 5, 0, 3);
               }
            } else {
               SmallImage.paintImage(var1, 998, this.curX, var2 - this.l - 5, 0, 3);
            }
         }

         if (this.p) {
            if ((var12 = GameCanvas.gameTick % 16) == 0) {
               SmallImage.paintImage(var1, 1013, this.curX - this.k / 2, var2 - this.l + this.l / 4, 0, 3);
               return;
            }

            if (var12 == 1) {
               SmallImage.paintImage(var1, 1014, this.curX - this.k / 2, var2 - this.l + this.l / 4, 0, 3);
               return;
            }

            if (var12 == 2) {
               SmallImage.paintImage(var1, 1015, this.curX - this.k / 2, var2 - this.l + this.l / 4, 0, 3);
               return;
            }

            if (var12 == 3) {
               SmallImage.paintImage(var1, 1016, this.curX - this.k / 2, var2 - this.l + this.l / 4, 0, 3);
               return;
            }

            if (var12 == 4) {
               SmallImage.paintImage(var1, 1013, this.curX + this.k / 2, var2 - this.l, 0, 3);
               return;
            }

            if (var12 == 5) {
               SmallImage.paintImage(var1, 1014, this.curX + this.k / 2, var2 - this.l, 0, 3);
               return;
            }

            if (var12 == 6) {
               SmallImage.paintImage(var1, 1015, this.curX + this.k / 2, var2 - this.l, 0, 3);
               return;
            }

            if (var12 == 7) {
               SmallImage.paintImage(var1, 1016, this.curX + this.k / 2, var2 - this.l, 0, 3);
               return;
            }

            if (var12 == 8) {
               SmallImage.paintImage(var1, 1013, this.curX - this.k / 2, var2, 0, 3);
               return;
            }

            if (var12 == 9) {
               SmallImage.paintImage(var1, 1014, this.curX - this.k / 2, var2, 0, 3);
               return;
            }

            if (var12 == 10) {
               SmallImage.paintImage(var1, 1015, this.curX - this.k / 2, var2, 0, 3);
               return;
            }

            if (var12 == 11) {
               SmallImage.paintImage(var1, 1016, this.curX - this.k / 2, var2, 0, 3);
               return;
            }

            if (var12 == 12) {
               SmallImage.paintImage(var1, 1013, this.curX + this.k / 2, var2 - this.l / 4, 0, 3);
               return;
            }

            if (var12 == 13) {
               SmallImage.paintImage(var1, 1014, this.curX + this.k / 2, var2 - this.l / 4, 0, 3);
               return;
            }

            if (var12 == 14) {
               SmallImage.paintImage(var1, 1015, this.curX + this.k / 2, var2 - this.l / 4, 0, 3);
               return;
            }

            if (var12 == 15) {
               SmallImage.paintImage(var1, 1016, this.curX + this.k / 2, var2 - this.l / 4, 0, 3);
            }
         }
      }

   }

   public final int d() {
      if (this.sys > 1) {
         if (this.sys == 2) {
            return 33023;
         }

         if (this.sys == 3) {
            return 7443811;
         }
      }

      return 16711680;
   }

   public final void e() {
      this.hp = 0;
      this.ao = System.currentTimeMillis();
      if (this.ac != null) {
         this.ad = true;
      } else {
         this.ad = true;
         this.hp = 0;
         this.h = 1;
         this.ag = -5;
         this.ah = -this.g << 2;
         this.ai = 0;
      }
   }

   public final void a(Mob var1) {
      this.au = var1;
   }

   public final void b(Char var1) {
      this.av = var1;
   }

   private void j() {
      int var1;
      int var2;
      if (this.au != null) {
         if (this.isBoss) {
            this.h = 3;
         } else {
            var1 = this.au.curX - this.curX;
            var2 = this.au.curY - this.curY;
            this.curX += var1 / 4;
            this.curY += var2 / 4;
            this.g = this.curX >= this.au.curX ? 0 : 1;
            if (this.au.h == 1 || this.au.h == 0 || Res.e(var1) < 20 && Res.e(var2) < 20) {
               if (this.ay == 0) {
                  ServerEffect.a(this.aw == -1 ? 59 : this.aw, this.au.curX, this.au.curY, this.g == 0 ? -1 : 1);
               } else if (this.ay == 1 && this.aw >= 0) {
                  EffectAuto.a(this.aw, this.au.curX, this.au.curY, (byte)1, (short)-1, this.g == 0 ? -1 : 1);
               }

               this.au = null;
            }
         }
      }

      if (this.av != null) {
         if (this.isBoss) {
            this.h = 3;
            return;
         }

         var1 = this.av.cx - this.curX;
         var2 = this.av.cy - this.curY;
         this.curX += var1 / 4;
         this.curY += var2 / 4;
         this.g = this.curX >= this.av.cx ? 0 : 1;
         if (this.av.statusMe == 5 || this.av.statusMe == 14 || Res.e(var1) < 20 && Res.e(var2) < 20) {
            if (this.ay == 0) {
               ServerEffect.a(this.aw == -1 ? 59 : this.aw, this.av.cx, this.av.cy, this.g == 0 ? -1 : 1);
            } else if (this.ay == 1 && this.aw >= 0) {
               EffectAuto.a(this.aw, this.av.cx, this.av.cy, (byte)1, (short)-1, this.g == 0 ? -1 : 1);
            }

            this.av = null;
         }
      }

   }

   public final void a(short var1, byte var2, byte var3) {
      this.aw = var1;
      this.ax = var2;
      this.ay = var3;
   }

   public final void f() {
      if (this.id == 116) {
         ServerEffect.a(84, Char.getMyChar(), 1);
      } else if (this.id == 115) {
         ServerEffect.a(81, Char.getMyChar(), 1);
      } else if (this.id == 138) {
         ServerEffect.a(90, Char.getMyChar(), 1);
      } else if (this.id == 139) {
         ServerEffect.a(91, Char.getMyChar(), 1);
      } else {
         if (this.id != 140 && this.id != 161) {
            if (this.id == 141 || this.id == 162) {
               ServerEffect.a(121, Char.getMyChar(), 1);
               return;
            }

            if (this.id == 144 || this.id == 163) {
               ServerEffect.a(121, Char.getMyChar(), 1);
               return;
            }

            if (this.id == 160) {
               ServerEffect.a(124, Char.getMyChar(), 1);
               return;
            }

            if (this.id == 164 || this.id == 165) {
               ServerEffect.a(126, this.al, 1);
               return;
            }

            if (this.id == 166) {
               ServerEffect.a(103, this.al, 1);
               return;
            }

            if (this.id == 166) {
               ServerEffect.a(105, this.al, 1);
               return;
            }
         } else {
            ServerEffect.a(110, Char.getMyChar(), 2);
         }

      }
   }

   private int c(int var1) {
      var1 %= ba[this.id - 168].length;
      return this.id != 198 && this.id != 199 && this.id != 200 && this.id != 201 && this.id != 203 && this.id != 204 ? ba[this.id - 168][var1] : 0;
   }

   private int d(int var1) {
      var1 %= 5;
      if (this.id == 178) {
         switch (var1) {
            case 0:
               return 5;
            case 1:
               return 6;
            case 2:
               return 7;
            case 3:
               return 8;
         }
      } else if (this.id == 168) {
         switch (var1) {
            case 0:
               return 1;
            case 1:
               return 2;
            case 2:
               return 3;
            case 3:
               return 4;
            case 4:
               return 5;
         }
      } else if (this.id == 179) {
         switch (var1) {
            case 0:
               return 0;
            case 1:
               return 2;
            case 2:
               return 3;
            case 3:
               return 4;
            case 4:
               return 5;
         }
      } else if (this.id == 175) {
         switch (var1) {
            case 0:
               return 0;
            case 1:
               return 2;
            case 2:
               return 3;
            case 3:
               return 4;
            case 4:
               return 5;
         }
      } else if (this.id == 176) {
         switch (var1) {
            case 0:
               return 2;
            case 1:
               return 3;
            case 2:
               return 4;
            case 3:
               return 5;
            case 4:
               return 6;
         }
      } else if (this.id == 177) {
         switch (var1) {
            case 0:
               return 1;
            case 1:
               return 2;
            case 2:
               return 3;
            case 3:
               return 4;
            case 4:
               return 5;
         }
      } else if (this.id == 174) {
         switch (var1) {
            case 0:
               return 5;
            case 1:
               return 6;
            case 2:
               return 7;
            case 3:
               return 8;
            case 4:
               return 9;
         }
      }

      return 0;
   }

   private void k() {
      if (this.ag == 0) {
         int var1 = 0;
         int var2 = 0;
         if (this.au != null) {
            var1 = this.au.curX;
            var2 = this.au.curY;
         }

         if (this.av != null) {
            var1 = this.av.cx;
            var2 = this.av.cy;
         }

         this.an = this.ax;
         this.g = this.curX >= var1 ? 0 : 1;
         if (this.isBoss) {
            ++this.ap;
            if (this.ap >= mobTemplates[this.id].l[this.an].length) {
               this.ap = 0;
               this.h = 2;
               this.au = null;
               this.av = null;
               this.ag = 0;
               this.ah = 0;
            }

            if (this.ap == mobTemplates[this.id].l[this.an].length - 2) {
               if (this.ay == 0) {
                  ServerEffect.a(this.aw == -1 ? 59 : this.aw, var1, var2, 1, (byte)(this.g == 0 ? -1 : 1));
               } else if (this.ay == 1 && this.aw >= 0) {
                  EffectAuto.a(this.aw, var1, var2, (byte)1, (short)-1, this.g == 0 ? -1 : 1);
               }
            }

            this.ae = mobTemplates[this.id].l[this.ax][this.ap];
         }

         if (mobTemplates[this.id].c != 0 && !this.o && this.q && this.r) {
            this.curX += (var1 - this.curX) / 3;
         }

         if (this.curX > this.cx + mobTemplates[this.id].a) {
            this.ag = 1;
         }

         if (this.curX < this.cx - mobTemplates[this.id].a) {
            this.ag = 1;
            return;
         }
      } else if (this.ag == 1) {
         if (mobTemplates[this.id].c != 0 && !this.o && !this.q && !this.r) {
            this.curX += (this.cx - this.curX) / 4;
            this.curY += (this.cy - this.curY) / 4;
         }

         if (Res.e(this.cx - this.curX) < 5 && Res.e(this.cy - this.curY) < 5) {
            this.h = 2;
            this.ap = 0;
            this.ag = 0;
            this.ah = 0;
         }
      }

   }

   public static Mob a(int var0) {
      for(int var1 = GameScr.vMobAttack.size() - 1; var1 >= 0; --var1) {
         Mob var2;
         if ((var2 = (Mob)GameScr.vMobAttack.elementAt(var1)) != null && var2.m == var0) {
            return var2;
         }
      }

      return null;
   }

   public static Mob b(int var0) {
      for(int var1 = GameScr.vMobAttack.size() - 1; var1 >= 0; --var1) {
         Mob var2;
         if ((var2 = (Mob)GameScr.vMobAttack.elementAt(var1)) != null && var2.lv == var0 && !var2.isBoss) {
            return var2;
         }
      }

      return null;
   }

   public final void g() {
      if (mobTemplates[this.id] != null && mobTemplates[this.id].h != null && (this.isBoss || this.ae < mobTemplates[this.id].h.length && mobTemplates[this.id].h[this.ae] != null) && this.h != 0) {
         if (this.al == null && (this.id == 168 || this.id == 179 || this.id == 175)) {
            this.h = 8;
         }

         if (Code.isBangMob && this.hp > 0) {
            return;
         }

         if (this.ak != null || mobTemplates[this.id].a == 0) {
            if (this.h != 3 && this.ab) {
               if (this.al != null) {
                  this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                  this.al = null;
               }

               this.ab = false;
            }

            if (this.hp <= 0 && this.m != -1) {
               this.h = 1;
            }

            int var1;
            int var2;
            int var3;
            Mob var4;
            label961:
            switch (this.h) {
               case 1:
                  this.n = false;
                  this.o = false;
                  this.p = false;
                  this.q = false;
                  this.r = false;
                  if (this.id != 98 && this.id != 99) {
                     ++this.ag;
                     this.curY += this.ag;
                     if (z != null) {
                        if (z.selectSkill != null) {
                           if (z.selectSkill.template.id > 72) {
                              if (GameCanvas.gameTick % 9 == 0) {
                                 if (this.ah > 1) {
                                    this.ah += 5;
                                 } else if (this.ah < -1) {
                                    this.ah -= 5;
                                 }
                              }
                           } else if (GameCanvas.gameTick % 2 == 0) {
                              if (this.ah > 1) {
                                 --this.ah;
                              } else if (this.ah < -1) {
                                 ++this.ah;
                              }
                           }
                        }
                     } else if (Char.getMyChar() != null && Char.getMyChar().selectSkill != null) {
                        if (Char.getMyChar().selectSkill.template.id > 72) {
                           if (GameCanvas.gameTick % 9 == 0) {
                              if (this.ah > 1) {
                                 this.ah += 5;
                              } else if (this.ah < -1) {
                                 this.ah -= 5;
                              }
                           }
                        } else if (GameCanvas.gameTick % 2 == 0) {
                           if (this.ah > 1) {
                              --this.ah;
                           } else if (this.ah < -1) {
                              ++this.ah;
                           }
                        }
                     }

                     this.curX += this.ah;
                     if (this.id != 209 && this.id != 210) {
                        if (this.id != 168 && this.id != 176 && this.id != 177 && this.id != 179 && this.id != 180 && this.id != 191) {
                           if (this.id != 178 && this.id != 181 && this.id != 183 && this.id != 185 && this.id != 188 && this.id != 192 && this.id != 194) {
                              if (this.id != 173 && this.id != 184) {
                                 if (this.id == 175) {
                                    var4 = this;
                                    var1 = 8;
                                 } else if (this.id != 170 && this.id != 195 && this.id != 196 && this.id != 197 && this.id != 186 && this.id != 189 && this.id != 190) {
                                    if (this.id == 187) {
                                       var4 = this;
                                       var1 = 9;
                                    } else if (this.id == 193) {
                                       var4 = this;
                                       var1 = 5;
                                    } else if (this.id == 174) {
                                       var4 = this;
                                       var1 = 10;
                                    } else {
                                       var4 = this;
                                       var1 = this.isBoss ? 10 : 2;
                                    }
                                 } else {
                                    var4 = this;
                                    var1 = 3;
                                 }
                              } else {
                                 var4 = this;
                                 var1 = 6;
                              }
                           } else {
                              var4 = this;
                              var1 = 4;
                           }
                        } else {
                           var4 = this;
                           var1 = 7;
                        }
                     } else {
                        var4 = this;
                        var1 = 6;
                     }

                     var4.ae = var1;
                     if (this.curY > GameScr.p * 24 || this.curX < GameScr.m * 24 || this.curX > GameScr.o * 24) {
                        this.ag = 0;
                        this.ah = 0;
                        this.curX = this.curY = 0;
                        this.hp = this.getMobTemplate().f;
                        this.h = 0;
                        if (this.id < 168) {
                           this.ae = 0;
                        } else {
                           var2 = GameCanvas.gameTick;
                           var3 = GameCanvas.gameTick % bb[this.id - 168].length;
                           this.ae = bb[this.id - 168][var3];
                        }

                        this.aj = 0;
                        return;
                     }

                     if (this.ai == 0 && (TileMap.a(this.curX, this.curY) & 2) == 2) {
                        this.ag = this.ag > 4 ? -4 : -this.ag;
                        this.ai = 16;
                     }

                     if (this.ai > 0) {
                        --this.ai;
                     }
                  } else if (System.currentTimeMillis() - this.ao > 1200L) {
                     this.h = 0;
                  }
                  break;
               case 2:
                  this.aj = 0;
                  switch (mobTemplates[this.id].c) {
                     case 0:
                     case 1:
                     case 2:
                     case 3:
                        if (this.id != 209 && this.id != 210) {
                           if (this.id != 173 && this.id != 175 && this.id != 176 && this.id != 177 && this.id != 179 && this.id != 180 && this.id != 181 && this.id != 183 && this.id != 184 && this.id != 185) {
                              if (this.id != 168 && this.id != 179) {
                                 if (this.id == 174) {
                                    this.ae = 4;
                                 } else {
                                    this.ae = 0;
                                 }
                              } else {
                                 this.ae = 6;
                              }
                           } else {
                              this.ae = 1;
                           }
                        } else {
                           this.ae = 1;
                        }

                        ++this.ag;
                        if (this.ag > 10 + this.m % 10 && System.currentTimeMillis() - (this.at + ar) >= 0L) {
                           this.h = 5;
                        }

                        if (this.isBoss) {
                           this.ae = GameCanvas.gameTick % 101 > 1 ? 0 : 1;
                        }
                        break label961;
                     case 4:
                     case 5:
                        if (!this.isBoss) {
                           if (this.id < 168) {
                              this.ae = GameCanvas.gameTick % 4 > 1 ? 0 : 1;
                           } else {
                              this.ae = this.c(GameCanvas.gameTick);
                           }
                        } else {
                           this.ae = mobTemplates[this.id].k[this.ap];
                        }

                        ++this.ag;
                        if (this.ag > this.m % 3) {
                           this.h = 5;
                        }
                     default:
                        break label961;
                  }
               case 3:
                  if (this.au == null && this.av == null) {
                     if (this.aw < 0) {
                        if (this.id != 209 && this.id != 210) {
                           if (this.id != 176 && this.id != 177 && this.id != 179) {
                              if (this.id == 175) {
                                 var4 = this;
                                 var1 = 7;
                              } else if (this.id != 180 && this.id != 181 && this.id != 183 && this.id != 184 && this.id != 173 && this.id != 188 && this.id != 192 && this.id != 194 && this.id != 202) {
                                 if (this.id == 193) {
                                    var4 = this;
                                    var1 = 4;
                                 } else if (this.id != 187 && this.id != 168 && this.id != 175 && this.id != 176 && this.id != 179 && this.id != 174) {
                                    var4 = this;
                                    var1 = GameCanvas.gameTick % 4 > 1 ? (mobTemplates[this.id].c == 5 ? 3 : 0) : 1;
                                 } else {
                                    var4 = this;
                                    var1 = this.d(GameCanvas.gameTick);
                                 }
                              } else {
                                 var4 = this;
                                 var1 = 3;
                              }
                           } else {
                              var4 = this;
                              var1 = 6;
                           }
                        } else {
                           var4 = this;
                           var1 = 6;
                        }

                        var4.ae = var1;
                        if (this.ag == 0) {
                           var2 = 0;
                           var3 = 0;
                           if (this.an == 0) {
                              var2 = this.al.cx;
                              var3 = this.al.cy;
                           } else if (this.an == 1) {
                              var2 = this.am.a;
                              var3 = this.am.b;
                           }

                           if (Res.e(var2 - this.curX) < 24 || Res.e(var2 - this.curX) < 5 || mobTemplates[this.id].c == 0) {
                              if (this.id != 168 && this.id != 176 && this.id != 177 && this.id != 179) {
                                 if (this.id == 175) {
                                    var4 = this;
                                    var1 = 7;
                                 } else {
                                    label1061: {
                                       if (this.id != 180 && this.id != 181 && this.id != 183 && this.id != 184 && this.id != 173 && this.id != 202) {
                                          if (this.id == 187 || this.id == 168 || this.id == 179 || this.id == 174) {
                                             var4 = this;
                                             var1 = this.d(GameCanvas.gameTick);
                                             break label1061;
                                          }

                                          var4 = this;
                                          if (mobTemplates[this.id].h.length == 3) {
                                             var1 = 0;
                                             break label1061;
                                          }
                                       } else {
                                          var4 = this;
                                       }

                                       var1 = 3;
                                    }
                                 }
                              } else {
                                 var4 = this;
                                 var1 = 6;
                              }

                              var4.ae = var1;
                           }

                           if (this.isBoss && (Res.e(var2 - this.curX) < 48 || Res.e(var2 - this.curX) < 10 || mobTemplates[this.id].c == 0)) {
                              this.ae = mobTemplates[this.id].h.length == 3 ? 0 : 3;
                           }

                           if (this.isBoss) {
                              ++this.ap;
                              if (Res.e(var2 - this.curX) >= 48 && Res.e(var3 - this.curY) >= 10) {
                                 if (this.ap >= mobTemplates[this.id].l[1].length) {
                                    this.ap = 0;
                                 }

                                 this.ae = mobTemplates[this.id].l[1][this.ap];
                              } else {
                                 if (this.ap >= mobTemplates[this.id].l[0].length) {
                                    this.ap = 0;
                                 }

                                 this.ae = mobTemplates[this.id].l[0][this.ap];
                              }
                           }

                           if (this.ae == 3 || this.ae == 6 || this.ae == 7) {
                              this.ag = 1;
                           }

                           if (mobTemplates[this.id].c != 0 && !this.o && this.q && this.r) {
                              this.curX += (var2 - this.curX) / 3;
                           }

                           if (this.curX > this.cx + mobTemplates[this.id].a) {
                              this.ag = 1;
                           }

                           if (this.curX < this.cx - mobTemplates[this.id].a) {
                              this.ag = 1;
                           }

                           if ((mobTemplates[this.id].c == 4 || mobTemplates[this.id].c == 5) && !this.o) {
                              this.curY += (var3 - this.curY) / 20;
                           }

                           ++this.ah;
                           if (this.isBoss && Res.e(var2 - this.curX) < 48 && Res.e(var3 - this.curY) < 15 || Res.e(var2 - this.curX) < 12 && Res.e(var3 - this.curY) < 12 || this.ah > 12 || this.ag == 1 || mobTemplates != null && mobTemplates[this.id].l != null && this.ap == mobTemplates[this.id].l[0].length - 2 && (this.getMobTemplate().e == 166 || this.getMobTemplate().e == 167)) {
                              label1062: {
                                 this.ag = 1;
                                 if (this.an == 0) {
                                    if (this.isBoss && Res.e(var2 - this.curX) < 48 && Res.e(var3 - this.curY) < 15) {
                                       this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                       this.ab = false;
                                       if (this.getMobTemplate().e == 210) {
                                          if (this.al.cx > this.curX) {
                                             ServerEffect.a(142, this.curX, this.curY, 1);
                                          } else {
                                             ServerEffect.a(142, this.curX, this.curY, 1, (byte)-1);
                                          }
                                          break label1062;
                                       }

                                       if (this.getMobTemplate().e == 209) {
                                          if (this.al.cx > this.curX) {
                                             ServerEffect.a(108, this.curX, this.curY, 1);
                                          } else {
                                             ServerEffect.a(108, this.curX, this.curY, 1, (byte)-1);
                                          }
                                          break label1062;
                                       }

                                       if (this.getMobTemplate().e == 114) {
                                          ServerEffect.a(79, this.al, 3);
                                          break label1062;
                                       }

                                       if (this.getMobTemplate().e == 115) {
                                          if (this.al == Char.getMyChar()) {
                                             GameScr.eq = 1;
                                          }

                                          GameScr.er = 0;
                                          ServerEffect.a(81, this.al.cx, this.cy + TileMap.size, 2);
                                          ServerEffect.a(81, this.al.cx - 40, this.cy + TileMap.size, 2);
                                          ServerEffect.a(81, this.al.cx + 40, this.cy + TileMap.size, 2);
                                          break label1062;
                                       }

                                       if (this.getMobTemplate().e == 116) {
                                          if (this.al == Char.getMyChar()) {
                                             GameScr.eq = 1;
                                             GameScr.er = 0;
                                          }

                                          if (this.al.cx > this.curX) {
                                             ServerEffect.a(86, this.curX, this.curY - this.l / 2 + 5, 1);
                                          } else {
                                             ServerEffect.a(88, this.curX, this.curY - this.l / 2 + 5, 1);
                                          }

                                          ServerEffect.a(87, this.al.cx, this.al.cy - this.al.bj / 2, 2);
                                          ServerEffect.a(87, this.al.cx - 40, this.al.cy - this.al.bj / 2, 2);
                                          ServerEffect.a(87, this.al.cx + 40, this.al.cy - this.al.bj / 2, 2);
                                          break label1062;
                                       }

                                       if (this.getMobTemplate().e == 138) {
                                          if (this.al.cx > this.curX) {
                                             ServerEffect.a(89, this.curX + this.k / 2, this.curY - this.l / 2 - 5, 1);
                                          } else {
                                             ServerEffect.a(89, this.curX - this.k / 2, this.curY - this.l / 2 - 5, 1, (byte)-1);
                                          }

                                          ServerEffect.a(90, this.al, 2);
                                          break label1062;
                                       }

                                       if (this.getMobTemplate().e == 139) {
                                          if (this.al == Char.getMyChar()) {
                                             GameScr.eq = 1;
                                             GameScr.er = 0;
                                          }

                                          ServerEffect.a(91, this.al, 2);
                                          break label1062;
                                       }

                                       if (this.getMobTemplate().e != 140 && this.getMobTemplate().e != 161) {
                                          if (this.getMobTemplate().e != 141 && this.getMobTemplate().e != 162) {
                                             if (this.getMobTemplate().e != 144 && this.getMobTemplate().e != 163) {
                                                if (this.getMobTemplate().e == 160) {
                                                   if (this.al.cx > this.curX) {
                                                      ServerEffect.a(123, this.curX + this.k / 2, this.curY - 5, 1);
                                                   } else {
                                                      ServerEffect.a(123, this.curX - this.k / 2, this.curY - 5, 1, (byte)-1);
                                                   }

                                                   ServerEffect.a(91, this.al, 1);
                                                   break label1062;
                                                }

                                                if (this.getMobTemplate().e != 164 && this.getMobTemplate().e != 165) {
                                                   if (this.getMobTemplate().e == 167) {
                                                      if (this.al.cx > this.curX) {
                                                         ServerEffect.a(125, this.curX + this.k / 2, this.curY, 1);
                                                      } else {
                                                         ServerEffect.a(125, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                      }
                                                   } else if (this.getMobTemplate().e == 166) {
                                                      if (this.al.cx > this.curX) {
                                                         ServerEffect.a(108, this.curX + this.k / 2, this.curY, 1);
                                                      } else {
                                                         ServerEffect.a(108, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                      }
                                                   } else if (this.getMobTemplate().e == 198) {
                                                      if (this.al.cx > this.curX) {
                                                         ServerEffect.a(143, this.curX + this.k / 2, this.curY, 1);
                                                      } else {
                                                         ServerEffect.a(143, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                      }
                                                   } else if (this.getMobTemplate().e == 199) {
                                                      if (this.al.cx > this.curX) {
                                                         ServerEffect.a(144, this.curX + this.k / 2, this.curY, 1);
                                                      } else {
                                                         ServerEffect.a(144, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                      }
                                                   } else if (this.getMobTemplate().e == 200) {
                                                      if (this.al.cx > this.curX) {
                                                         ServerEffect.a(142, this.curX + this.k / 2, this.curY, 1);
                                                      } else {
                                                         ServerEffect.a(142, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                      }
                                                   } else if (this.getMobTemplate().e == 201) {
                                                      if (this.al.cx > this.curX) {
                                                         ServerEffect.a(144, this.curX + this.k / 2, this.curY, 1);
                                                      } else {
                                                         ServerEffect.a(144, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                      }
                                                   } else if (this.getMobTemplate().e == 203) {
                                                      if (this.al.cx > this.curX) {
                                                         ServerEffect.a(159, this.curX + this.k / 2, this.curY, 1);
                                                         ServerEffect.a(156, this.al.cx, this.al.cy, 1);
                                                      } else {
                                                         ServerEffect.a(159, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                         ServerEffect.a(156, this.al.cx, this.al.cy, -1);
                                                      }
                                                   } else if (this.getMobTemplate().e == 204) {
                                                      if (this.al.cx > this.curX) {
                                                         ServerEffect.a(159, this.curX + this.k / 2, this.curY, 1);
                                                         ServerEffect.a(173, this.al.cx, this.al.cy, 1);
                                                      } else {
                                                         ServerEffect.a(159, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                         ServerEffect.a(173, this.al.cx, this.al.cy, -1);
                                                      }
                                                   }
                                                   break label1062;
                                                }

                                                if (this.al.cx > this.curX) {
                                                   ServerEffect.a(125, this.curX + this.k / 2, this.curY, 1);
                                                } else {
                                                   ServerEffect.a(125, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                                }

                                                ServerEffect.a(90, this.al, 1);
                                                break label1062;
                                             }

                                             if (this.al == Char.getMyChar()) {
                                                GameScr.eq = 1;
                                                GameScr.er = 0;
                                             }

                                             ServerEffect.a(112, this.al, 2);
                                             ServerEffect.a(109, this.al.cx - 40, this.al.cy - 40, 1);
                                             ServerEffect.a(109, this.al.cx + 40, this.al.cy - 40, 1);
                                             ServerEffect.a(109, this.al.cx - 20, this.al.cy, 2);
                                             ServerEffect.a(109, this.al.cx + 20, this.al.cy, 2);
                                             break label1062;
                                          }

                                          if (this.al.cx > this.curX) {
                                             ServerEffect.a(108, this.curX + this.k / 2, this.curY, 1);
                                          } else {
                                             ServerEffect.a(108, this.curX - this.k / 2, this.curY, 1, (byte)-1);
                                          }

                                          ServerEffect.a(122, this.curX, this.curY, 1, (byte)this.g);
                                          ServerEffect.a(91, this.al, 1);
                                          break label1062;
                                       }

                                       if (this.al == Char.getMyChar()) {
                                          GameScr.eq = 1;
                                          GameScr.er = 0;
                                       }

                                       ServerEffect.a(112, this.al, 2);
                                       ServerEffect.a(109, this.al.cx - 40, this.al.cy - 40, 1);
                                       ServerEffect.a(109, this.al.cx + 40, this.al.cy - 40, 1);
                                       ServerEffect.a(109, this.al.cx - 20, this.al.cy, 2);
                                       ServerEffect.a(109, this.al.cx + 20, this.al.cy, 2);
                                       break label1062;
                                    }

                                    if (Res.e(var2 - this.curX) >= 24 || Res.e(var3 - this.curY) >= 15) {
                                       if (this.isBoss) {
                                          if (this.getMobTemplate().e == 210) {
                                             if (this.al.cx > this.curX) {
                                                ServerEffect.a(178, this.al.cx, this.al.cy, 1);
                                             } else {
                                                ServerEffect.a(178, this.al.cx, this.al.cy, 1, (byte)-1);
                                             }
                                          } else if (this.getMobTemplate().e == 209) {
                                             if (this.al.cx > this.curX) {
                                                ServerEffect.a(179, this.al.cx, this.al.cy, 1);
                                             } else {
                                                ServerEffect.a(179, this.al.cx, this.al.cy, 1, (byte)-1);
                                             }
                                          } else if (this.getMobTemplate().e != 114 && this.getMobTemplate().e != 115) {
                                             if (this.getMobTemplate().e == 116) {
                                                ServerEffect.a(84, this.al, 2);
                                                this.ab = false;
                                                this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                             } else if (this.getMobTemplate().e == 138) {
                                                if (this.al == Char.getMyChar()) {
                                                   GameScr.eq = 1;
                                                   GameScr.er = 0;
                                                }

                                                ServerEffect.a(83, this.al, 2);
                                                this.ab = false;
                                                this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                             } else if (this.getMobTemplate().e == 139) {
                                                MonsterDart.a(this.curX + (this.g - 1) * 30, this.curY - 30, this.isBoss, this.levelBoss, this.getMobTemplate().e, this.t, this.u, this.al);
                                             } else if (this.getMobTemplate().e != 140 && this.getMobTemplate().e != 161) {
                                                if (this.getMobTemplate().e != 141 && this.getMobTemplate().e != 162) {
                                                   if (this.getMobTemplate().e != 144 && this.getMobTemplate().e != 163) {
                                                      if (this.getMobTemplate().e == 160) {
                                                         ServerEffect.a(124, this.al, 2);
                                                         this.ab = false;
                                                         this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                      } else if (this.getMobTemplate().e != 164 && this.getMobTemplate().e != 165) {
                                                         if (this.getMobTemplate().e == 167) {
                                                            ServerEffect.a(112, this.al.cx + 5, this.al.cy, 1);
                                                            ServerEffect.a(112, this.al.cx - 5, this.al.cy, 1);
                                                            ServerEffect.a(112, this.al, 1);
                                                            this.ab = false;
                                                            this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                         } else if (this.getMobTemplate().e == 166) {
                                                            ServerEffect.a(92, this.al.cx + 5, this.al.cy, 1);
                                                            ServerEffect.a(92, this.al.cx - 5, this.al.cy, 1);
                                                            ServerEffect.a(92, this.al, 1);
                                                            this.ab = false;
                                                            this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                         } else if (this.getMobTemplate().e == 198) {
                                                            ServerEffect.a(142, this.al.cx + 5, this.al.cy, 1);
                                                            ServerEffect.a(142, this.al.cx - 5, this.al.cy, 1);
                                                            ServerEffect.a(142, this.al, 1);
                                                            this.ab = false;
                                                            this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                         } else if (this.getMobTemplate().e == 199) {
                                                            ServerEffect.a(143, this.al.cx + 5, this.al.cy, 1);
                                                            ServerEffect.a(143, this.al.cx - 5, this.al.cy, 1);
                                                            ServerEffect.a(143, this.al, 1);
                                                            this.ab = false;
                                                            this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                         } else if (this.getMobTemplate().e == 200) {
                                                            ServerEffect.a(144, this.al.cx + 5, this.al.cy, 1);
                                                            ServerEffect.a(144, this.al.cx - 5, this.al.cy, 1);
                                                            ServerEffect.a(144, this.al, 1);
                                                            this.ab = false;
                                                            this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                         } else if (this.getMobTemplate().e == 201) {
                                                            ServerEffect.a(108, this.al.cx + 5, this.al.cy, 1);
                                                            ServerEffect.a(108, this.al.cx - 5, this.al.cy, 1);
                                                            ServerEffect.a(108, this.al, 1);
                                                            this.ab = false;
                                                            this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                         } else if (this.getMobTemplate().e == 203) {
                                                            ServerEffect.a(149, this.al.cx + 5, this.al.cy, 1);
                                                            ServerEffect.a(149, this.al.cx - 5, this.al.cy, 1);
                                                            ServerEffect.a(149, this.al, 1);
                                                            ServerEffect.a(156, this.al, 1);
                                                            this.ab = false;
                                                            this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                         } else if (this.getMobTemplate().e == 204) {
                                                            ServerEffect.a(153, this.al.cx + 5, this.al.cy, 1);
                                                            ServerEffect.a(153, this.al.cx - 5, this.al.cy, 1);
                                                            ServerEffect.a(153, this.al, 1);
                                                            ServerEffect.a(173, this.al, 1);
                                                            this.ab = false;
                                                            this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                         }
                                                      } else {
                                                         ServerEffect.a(126, this.al, 1);
                                                         this.ab = false;
                                                         this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                      }
                                                   } else {
                                                      MonsterDart.a(this.curX + (this.g - 1) * 15, this.curY - 20, this.isBoss, this.levelBoss, this.getMobTemplate().e, this.t, this.u, this.al);
                                                   }
                                                } else {
                                                   if (this.al == Char.getMyChar()) {
                                                      GameScr.eq = 1;
                                                      GameScr.er = 0;
                                                   }

                                                   ServerEffect.a(121, this.al, 1);
                                                   this.ab = false;
                                                   this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                                }
                                             } else {
                                                if (this.al == Char.getMyChar()) {
                                                   GameScr.eq = 1;
                                                   GameScr.er = 0;
                                                }

                                                ServerEffect.a(110, this.al, 2);
                                                ServerEffect.a(104, this.al.cx - 20, this.al.cy, 2);
                                                ServerEffect.a(104, this.al.cx + 20, this.al.cy, 2);
                                                this.ab = false;
                                                this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                             }
                                          } else {
                                             MonsterDart.a(this.curX + (this.g - 1) * 15, this.curY - 20, this.isBoss, this.levelBoss, this.getMobTemplate().e, this.t, this.u, this.al);
                                          }
                                       } else {
                                          MonsterDart.a(this.curX - 5, this.curY + this.g * 10, this.isBoss, this.lv, this.getMobTemplate().e, this.t, this.u, this.al);
                                       }

                                       this.ab = false;
                                       break label1062;
                                    }

                                    this.al.a(this.t, this.u, this.isBoss, this.getMobTemplate().e);
                                 } else {
                                    if (this.an != 1) {
                                       break label1062;
                                    }

                                    if (Res.e(var2 - this.curX) < 24 && Res.e(var3 - this.curY) < 15) {
                                       this.am.d = true;
                                    } else if (this.isBoss) {
                                       MonsterDart.a(this.curX - 5, this.curY + this.g * 10 - 20, this.am);
                                    } else {
                                       MonsterDart.a(this.curX - 5, this.curY + this.g * 10, this.am);
                                    }
                                 }

                                 this.ab = false;
                              }
                           }

                           this.g = this.curX < var2 ? 1 : -1;
                        } else if (this.ag == 1) {
                           if (mobTemplates[this.id].c != 0 && !this.o && !this.q && !this.r) {
                              this.curX += (this.cx - this.curX) / 4;
                              this.curY += (this.cy - this.curY) / 4;
                           }

                           if (Res.e(this.cx - this.curX) < 5 && Res.e(this.cy - this.curY) < 5) {
                              this.h = 2;
                              this.ag = 0;
                              this.ah = 0;
                           }
                        }
                     } else if (this.ag == 0) {
                        var2 = this.al.cx;
                        var3 = this.al.cy;
                        this.an = this.ax;
                        this.g = this.curX >= var2 ? 0 : 1;
                        if (this.isBoss) {
                           ++this.ap;
                           if (this.ap >= mobTemplates[this.id].l[this.an].length) {
                              this.ap = 0;
                              this.h = 2;
                              this.au = null;
                              this.av = null;
                              this.ag = 0;
                              this.ah = 0;
                           }

                           if (this.ap == mobTemplates[this.id].l[this.an].length - 1) {
                              if (this.ay == 0) {
                                 ServerEffect.a(this.aw, var2, var3, 1, (byte)(this.g == 0 ? -1 : 1));
                              } else if (this.ay == 1 && this.aw >= 0) {
                                 EffectAuto.a(this.aw, var2, var3, (byte)1, (short)-1, this.g == 0 ? -1 : 1);
                              }
                           }

                           this.ae = mobTemplates[this.id].l[this.ax][this.ap];
                        } else {
                           ServerEffect.a(this.aw, var2, var3, 1, (byte)(this.g == 0 ? -1 : 1));
                        }

                        if (mobTemplates[this.id].c != 0 && !this.o && this.q && this.r) {
                           this.curX += (var2 - this.curX) / 3;
                        }

                        if (this.curX > this.cx + mobTemplates[this.id].a) {
                           this.ag = 1;
                        }

                        if (this.curX < this.cx - mobTemplates[this.id].a) {
                           this.ag = 1;
                        }
                     } else if (this.ag == 1) {
                        if (mobTemplates[this.id].c != 0 && !this.o && !this.q && !this.r) {
                           this.curX += (this.cx - this.curX) / 4;
                           this.curY += (this.cy - this.curY) / 4;
                        }

                        if (Res.e(this.cx - this.curX) < 5 && Res.e(this.cy - this.curY) < 5) {
                           this.h = 2;
                           this.ap = 0;
                           this.ag = 0;
                           this.ah = 0;
                        }
                     }
                  } else {
                     this.k();
                  }
                  break;
               case 4:
                  this.aj = 0;
                  this.ae = 0;
                  ++this.ag;
                  if (this.ag > 40 + this.m % 5) {
                     this.curY -= 2;
                     this.h = 5;
                     this.ag = 0;
                  }
                  break;
               case 5:
                  if (GameCanvas.gameTick % 4 == 0 && this.isBoss) {
                     ++this.ap;
                     if (this.ap > mobTemplates[this.id].k.length - 1) {
                        this.ap = 0;
                     }
                  }

                  this.aj = 0;
                  this.i();
                  break;
               case 6:
                  this.aj = 0;
                  ++this.ag;
                  this.curY += this.ag;
                  if (this.curY >= this.cy) {
                     this.curY = this.cy;
                     this.ag = 0;
                     this.h = 5;
                  }
                  break;
               case 7:
                  this.h();
                  break;
               case 8:
                  this.ae = 0;
                  break;
               case 9:
                  var3 = GameCanvas.gameTick;
                  var3 = GameCanvas.gameTick % bc[this.id - 168].length;
                  this.ae = bc[this.id - 168][var3];
                  if (this.ae == 1) {
                     this.h = 8;
                  }
            }

            if (this.as && this.hp <= 0) {
               GameScr.vMobAttack.removeElement(this);
            }

            this.a((byte)1, this.h);
         }
      }

   }
}
