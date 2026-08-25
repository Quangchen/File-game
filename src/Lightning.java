
// Source code is decompiled from update .class file using FernFlower decompiler.
public final class Lightning extends Effect2 {
    private int[] a = new int[]{16579837, 11188220};
    private MyVector b = new MyVector();
    private MyVector[] g;
    private mResources h;
    private long i = 0L;
    private boolean j = false;
    private boolean k = true;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 7;
 
    public Lightning() {
    }
 
    public static void a(MyVector var0, mResources var1, int var2) {
       Lightning var3;
       (var3 = new Lightning()).a[1] = var2;
 
       for(var2 = 0; var2 < var0.size(); ++var2) {
          mResources var4;
          if ((var4 = (mResources)var0.elementAt(var2)) != null && (Res.e(var4.a - var1.a) >= 100 || Res.e(var4.b - var1.b) >= 50)) {
             var0.removeElementAt(var2);
          }
       }
 
       var3.a(var0, var1, true);
       Effect2.vEffect2.addElement(var3);
    }
 
    private void a(MyVector var1, mResources var2, boolean var3) {
       if (var1.size() != 0) {
          this.j = var3;
          MyVector var4;
          int var5;
          int var6;
          mResources var7;
          int var8;
          mResources var9;
          if (!var3) {
             var4 = var1;
             var5 = var1.size();
 
             for(var6 = 0; var6 < var5 - 1; ++var6) {
                var7 = (mResources)var4.elementAt(var6);
 
                for(var8 = var6 + 1; var8 < var5; ++var8) {
                   var9 = (mResources)var4.elementAt(var8);
                   if (var7.a > var9.a) {
                      var4.setElementAt(var7, var8);
                      var4.setElementAt(var9, var6);
                      var7 = var9;
                   }
                }
             }
          }
 
          this.b = var1;
          this.h = var2;
          this.g = new MyVector[var1.size()];
 
          int var17;
          for(var17 = 0; var17 < this.g.length; ++var17) {
             this.g[var17] = new MyVector();
          }
 
          var2.c = -1;
          this.g[0].addElement(var2);
          var17 = -1;
 
          int var10;
          int var11;
          int var12;
          int var13;
          int var14;
          mResources var18;
          int var19;
          int var20;
          mResources var22;
          for(var5 = 0; var5 < var1.size(); ++var5) {
             var10 = var2.a;
             int var15 = var2.b;
             if (var3 && var17 != -1) {
                var10 = (var18 = (mResources)var1.elementAt(var17)).a;
                var15 = var18.b;
             }
 
             if (var3) {
                ++var17;
             } else {
                var4 = var1;
                var6 = 0;
 
                for(var19 = 0; var19 < var4.size(); ++var19) {
                   if (((mResources)var4.elementAt(var19)).d == -1) {
                      ++var6;
                   }
                }
 
                int var16;
                label97: {
                   if (var6 != 0) {
                      var6 = Res.d(var6);
                      var19 = 0;
 
                      for(var8 = 0; var8 < var4.size(); ++var8) {
                         if ((var9 = (mResources)var4.elementAt(var8)).d == -1) {
                            if (var6 == var19) {
                               var9.d = 0;
                               var16 = var8;
                               break label97;
                            }
 
                            ++var19;
                         }
                      }
                   }
 
                   var16 = -1;
                }
 
                var17 = var16;
             }
 
             var6 = this.g[var17].size() - 1;
             var8 = Res.a((var7 = (mResources)var1.elementAt(var17)).a - var10, -(var7.b - var15));
             var14 = Res.d(15) + 10;
             var20 = 0;
 
             while(true) {
                var11 = 0;
                if (var20 != 0) {
                   var11 = var8 - 5 + Res.d(10);
                }
 
                var11 = Res.c(var11);
                var12 = var14 * var20 * Res.b(var11) >> 10;
                var13 = -(var14 * var20 * Res.a(var11)) >> 10;
                var22 = new mResources(var10 + var12, var15 + var13, var6++);
                this.g[var17].addElement(var22);
                if (Res.distance(var10, var15, var10 + var12, var15 + var13) >= Res.distance(var10, var15, var7.a, var7.b) - 20) {
                   break;
                }
 
                ++var20;
             }
          }
 
          for(var5 = 0; var5 < this.g.length; ++var5) {
             var10 = this.g[var5].size();
             mResources var21;
             (var21 = (mResources)var1.elementAt(var5)).c = (byte)(this.g[var5].size() - 1);
             var21.d = -1;
             (var18 = new mResources(var21.a, var21.b, var21.c)).d = -1;
             this.g[var5].addElement(var18);
 
             for(var19 = 1; var19 < var10; ++var19) {
                var22 = (mResources)this.g[var5].elementAt(var19);
                var14 = Res.d(2);
 
                for(var20 = 0; var20 < var14; ++var20) {
                   var11 = 180 + Res.d(180);
                   var13 = (var12 = 5 + Res.d(10)) * Res.b(Res.c(var11)) >> 10;
                   var11 = -(var12 * Res.a(Res.c(var11))) >> 10;
                   (var2 = new mResources(var22.a + var13, var22.b + var11, var19)).d = 0;
                   this.g[var5].addElement(var2);
                }
             }
          }
       }
 
    }
 
    public final void update() {
       if (this.h == null) {
          Effect2.vRemoveEffect2.addElement(this);
       } else {
          try {
             if (GameCanvas.gameTick % 2 == 1) {
                this.h.c = -1;
                this.h.d = -1;
 
                for(int var1 = 0; var1 < this.b.size(); ++var1) {
                   mResources var2;
                   (var2 = (mResources)this.b.elementAt(var1)).d = -1;
                   var2.c = -1;
                }
 
                if (this.j && this.k && this.b.size() > 1 && System.currentTimeMillis() / 10L - this.i > 30L) {
                   this.i = System.currentTimeMillis() / 10L;
                   this.h = (mResources)this.b.elementAt(0);
                   this.b.removeElementAt(0);
                }
 
                this.a(this.b, this.h, this.j);
                if (this.l > 3) {
                   this.o = 7;
                   Effect2.vEffect2.removeElement(this);
                }
 
                ++this.l;
                return;
             }
          } catch (Exception var3) {
             var3.printStackTrace();
          }
 
       }
    }
 
    public final void paint(mGraphics var1) {
       this.n = 0;
       if (++this.m >= 12) {
          this.m = 0;
       }
 
       if (this.g != null) {
          for(int var2 = 0; var2 < this.g.length; ++var2) {
             for(int var3 = 0; var3 < this.g[var2].size(); ++var3) {
                mResources var4;
                if ((var4 = (mResources)this.g[var2].elementAt(var3)).c >= 0 && var4.c < this.g[var2].size()) {
                   mResources var5 = (mResources)this.g[var2].elementAt(var4.c);
                   if (GameCanvas.e(var4.a, var4.b) && GameCanvas.e(var5.a, var5.b)) {
                      var1.setColor(this.a[0]);
                      var1.drawLine(var4.a, var4.b, var5.a, var5.b);
                      if (var4.d == -1) {
                         var1.setColor(this.a[1]);
                         var1.drawLine(var4.a - 1, var4.b, var5.a - 1, var5.b);
                         if (this.j && this.k) {
                            var1.drawLine(var4.a + 1, var4.b, var5.a + 1, var5.b);
                         }
                      }
                   }
 
                   if (this.j && this.k) {
                      ++this.n;
                      if (this.n >= this.o) {
                         this.o += 7;
                         return;
                      }
                   }
                }
             }
          }
       }
 
    }
 }
 