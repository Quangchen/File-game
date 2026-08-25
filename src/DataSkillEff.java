import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public final class DataSkillEff {
   public MyVector a = new MyVector();
   public SmallImage[] b;
   public byte[][] c;
   public byte[] d;
   private byte l;
   private byte m;
   private boolean n;
   public short e;
   private byte o;
   public byte[] f;
   public long g;
   private long p;
   public int h;
   public boolean i;
   private boolean q;
   private boolean r;
   public byte[] ActionStand;
   public byte[] gameAH;
   public byte[] gameAI;
   public byte[] gameAJ;
   public byte[] FrameChar;
   public byte[] Dx_Char;
   public byte[] Dy_Char;
   public byte gameAN;
   public byte dxHorse;
   public byte dyHorse;
   public int j;
   public int k;

   public DataSkillEff() {
      new MyVector();
      this.c = new byte[4][];
      new MyVector();
      this.e = 0;
      this.o = 0;
      this.f = new byte[4];
      new MyVector();
   }

   public DataSkillEff(short var1, boolean var2) {
      this();
      this.e = var1;
      this.r = var2;
      this.b();
   }

   private void b() {
      try {
         EffectData var1;
         if ((var1 = (EffectData)GameData.b.get("" + this.e)) == null) {
            DataInputStream var4;
            if ((var4 = Res.a("/effdata/" + this.e)) != null) {
               byte[] var2 = new byte[(short)var4.available()];
               var4.read(var2, 0, var2.length);
               this.a(var2);
               return;
            }

            GameData.a(this.e);
            return;
         }

         System.currentTimeMillis();
          if (this.r && var1 != null && var1.i != null) {
             this.a(var1.i);
             return;
          }

          if (var1 != null && var1.h) {
             this.a = var1.a;
             this.b = var1.b;
             this.j = var1.e;
            this.k = var1.f;
            this.d = var1.d;
            this.f = var1.g;
            this.c = var1.c;
            this.n = var1.h;
            if (this.r) {
               this.parseHorseInfo(this.d);
            }

            return;
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         GameData.a(this.e);
      }

   }

   public final void a(byte[] var1) {
      if (var1 != null) {
         DataInputStream var2 = null;

         try {
            this.a.removeAllElements();
            this.b = null;
            short var20 = (var2 = new DataInputStream(new ByteArrayInputStream(var1))).readByte();
            this.b = new SmallImage[var20];

            int var3;
            for(var3 = 0; var3 < var20; ++var3) {
               SmallImage[] var10000 = this.b;
               var2.readUnsignedByte();
               var10000[var3] = new SmallImage(var2.readUnsignedByte(), var2.readUnsignedByte(), var2.readUnsignedByte(), var2.readUnsignedByte());
            }

            var3 = 0;
            var20 = var2.readShort();

            int var5;
            for(int var4 = 0; var4 < var20; ++var4) {
               var5 = var2.readByte();
               MyVector var6 = new MyVector();
               MyVector var7 = new MyVector();

               for(int var8 = 0; var8 < var5; ++var8) {
                  PartFrame var9;
                  (var9 = new PartFrame(var2.readShort(), var2.readShort(), var2.readByte())).d = var2.readByte();
                  var9.e = var2.readByte();
                  if (var9.e == 0) {
                     var6.addElement(var9);
                  } else {
                     var7.addElement(var9);
                  }

                  if (var3 < Res.e(var9.c)) {
                     var3 = Res.e(var9.c);
                  }
               }

               this.a.addElement(new FrameEff(var6, var7));
            }

            this.j = this.b[0].w;
            this.k = (short)var3;
            short var21 = (short)var2.readUnsignedByte();
            this.d = new byte[var21];

            for(var5 = 0; var5 < var21; ++var5) {
               this.d[var5] = (byte)var2.readShort();
            }

            if (this.r) {
               this.parseHorseInfo(this.d);
            }

            var2.readByte();
            byte var22 = var2.readByte();
            this.c[0] = new byte[var22];

            for(var5 = 0; var5 < var22; ++var5) {
               this.c[0][var5] = var2.readByte();
            }

            var22 = var2.readByte();
            this.c[1] = new byte[var22];

            for(var5 = 0; var5 < var22; ++var5) {
               this.c[1][var5] = var2.readByte();
            }

            var22 = var2.readByte();
            this.c[3] = new byte[var22];

            for(var5 = 0; var5 < var22; ++var5) {
               this.c[3][var5] = var2.readByte();
            }

            this.n = true;

            try {
               this.f[0] = (byte)(this.c[0].length - 7);
               this.f[1] = (byte)(this.c[1].length - 7);
               this.f[2] = (byte)(this.c[3].length - 7);
               this.f[3] = (byte)(this.c[3].length - 7);
            } catch (Exception var17) {
            }

            this.f[0] = var2.readByte();
            this.f[1] = var2.readByte();
            this.f[2] = var2.readByte();
            this.f[3] = this.f[2];
            return;
         } catch (Exception var18) {
         } finally {
            try {
               var2.close();
            } catch (Exception var16) {
            }

         }

      }
   }

   public DataSkillEff(short var1, long var2, int var4) {
      new MyVector();
      this.c = new byte[4][];
      new MyVector();
      this.e = 0;
      this.o = 0;
      this.f = new byte[4];
      new MyVector();
      this.e = var1;
      this.g = var2;
      this.h = var4;
      this.b();
      if (var2 == -1L) {
         this.o = 3;
      } else if (var2 == 0L) {
         this.o = 1;
      } else {
         this.o = 2;
      }
   }

   private static byte[] toByteArray(MyVector var0) {
      byte[] var1 = new byte[var0.size()];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = Byte.parseByte((String)var0.elementAt(var2));
      }

      return var1;
   }

   private void parseHorseInfo(byte[] var1) {
      if (var1 == null || var1.length < 4) {
         return;
      }

      MyVector var2 = new MyVector();
      MyVector var3 = new MyVector();
      MyVector var4 = new MyVector();
      MyVector var5 = new MyVector();
      MyVector var6 = new MyVector();
      MyVector var7 = new MyVector();
      MyVector var8 = new MyVector();
      int var9 = 0;

      this.gameAN = var1[0];
      this.dxHorse = var1[2];
      this.dyHorse = var1[3];

      for(int var10 = 0; var10 < var1.length; ++var10) {
         if (var1[var10] == -1) {
            ++var9;
         } else if (var9 == 2) {
            var2.addElement(String.valueOf(var1[var10]));
         } else if (var9 == 3) {
            var3.addElement(String.valueOf(var1[var10]));
         } else if (var9 == 4) {
            var4.addElement(String.valueOf(var1[var10]));
         } else if (var9 == 5) {
            var5.addElement(String.valueOf(var1[var10]));
         } else if (var9 == 6) {
            var6.addElement(String.valueOf(var1[var10]));
         } else if (var9 == 7) {
            var7.addElement(String.valueOf(var1[var10]));
         } else if (var9 == 8) {
            var8.addElement(String.valueOf(var1[var10]));
         }
      }

      this.FrameChar = toByteArray(var2);
      this.ActionStand = toByteArray(var3);
      this.gameAH = toByteArray(var4);
      this.gameAI = toByteArray(var5);
      this.gameAJ = toByteArray(var6);
      this.Dx_Char = toByteArray(var7);
      this.Dy_Char = toByteArray(var8);
      if (this.gameAN <= 0) {
         this.gameAN = 1;
      }

      if (this.ActionStand == null || this.ActionStand.length == 0) {
         this.ActionStand = new byte[]{0};
      }

      if (this.gameAH == null || this.gameAH.length == 0) {
         this.gameAH = this.ActionStand;
      }

      if (this.gameAI == null || this.gameAI.length == 0) {
         this.gameAI = this.ActionStand;
      }

      if (this.gameAJ == null || this.gameAJ.length == 0) {
         this.gameAJ = this.ActionStand;
      }

      if (this.Dx_Char == null || this.Dx_Char.length == 0) {
         this.Dx_Char = new byte[this.gameAN];
      }

      if (this.Dy_Char == null || this.Dy_Char.length == 0) {
         this.Dy_Char = new byte[this.gameAN];
      }
   }

   private boolean c() {
      if (this.n) {
         return true;
      } else {
         this.b();
         return false;
      }
   }

   public final void a(mGraphics var1, int var2, int var3) {
      try {
         if (!this.c()) {
            return;
         }

         if (this.q) {
            return;
         }

         if (this.l < this.a.size()) {
            MyVector var4 = ((FrameEff)this.a.elementAt(this.l)).a;

            for(int var5 = 0; var5 < var4.size(); ++var5) {
               PartFrame var6 = (PartFrame)var4.elementAt(var5);
               SmallImage var7 = this.b[var6.a];
               ImageIcon var8;
               if ((var8 = GameData.b(this.e)) != null && var8.a != null) {
                  short var9 = var6.b;
                  int var10 = var7.w;
                  int var11 = var7.h;
                  short var12 = var7.x;
                  short var14 = var7.y;
                  if (var12 > var8.a.getWidth()) {
                     var12 = 0;
                  }

                  if (var14 > var8.a.getHeight()) {
                     var14 = 0;
                  }

                  if (var12 + var10 > var8.a.getWidth()) {
                     var10 = var8.a.getWidth() - var12;
                  }

                  if (var14 + var11 > var8.a.getHeight()) {
                     var11 = var8.a.getHeight() - var14;
                  }

                  var1.drawRegion(var8.a, var12, var14, var10, var11, var6.d == 1 ? 2 : 0, var2 + var9, var3 + var6.c, 0);
               }
            }

            return;
         }
      } catch (Exception var13) {
         var13.printStackTrace();
         System.err.println("Err DataSkillEff  paintBottomEff:" + this.e);
      }

   }

   public final void b(mGraphics var1, int var2, int var3) {
      try {
         if (!this.c()) {
            return;
         }

         if (this.q) {
            return;
         }

         if (this.l < this.a.size()) {
            MyVector var4 = ((FrameEff)this.a.elementAt(this.l)).b;

            for(int var5 = 0; var5 < var4.size(); ++var5) {
               PartFrame var6 = (PartFrame)var4.elementAt(var5);
               SmallImage var7 = this.b[var6.a];
               ImageIcon var8;
               if ((var8 = GameData.b(this.e)) != null && var8.a != null) {
                  short var9 = var6.b;
                  int var10 = var7.w;
                  int var11 = var7.h;
                  short var12 = var7.x;
                  short var14 = var7.y;
                  if (var12 > var8.a.getWidth()) {
                     var12 = 0;
                  }

                  if (var14 > var8.a.getHeight()) {
                     var14 = 0;
                  }

                  if (var12 + var10 > var8.a.getWidth()) {
                     var10 = var8.a.getWidth() - var12;
                  }

                  if (var14 + var11 > var8.a.getHeight()) {
                     var11 = var8.a.getHeight() - var14;
                  }

                  var1.drawRegion(var8.a, var12, var14, var10, var11, var6.d == 1 ? 2 : 0, var2 + var9, var3 + var6.c, 0);
               }
            }

            return;
         }
      } catch (Exception var13) {
         var13.printStackTrace();
         System.err.println("Err DataSkillEff  paintBottomEff:" + this.e);
      }

   }

   private void paintFrame(mGraphics var1, int var2, int var3, int var4, int var5, boolean var6) {
      try {
         if (!this.c() || this.q || this.a.size() <= 0) {
            return;
         }

         if (var4 < 0) {
            var4 = 0;
         }

         var4 %= this.a.size();
         MyVector var7 = var6 ? ((FrameEff)this.a.elementAt(var4)).b : ((FrameEff)this.a.elementAt(var4)).a;

         for(int var8 = 0; var8 < var7.size(); ++var8) {
            PartFrame var9 = (PartFrame)var7.elementAt(var8);
            SmallImage var10 = this.b[var9.a];
            ImageIcon var11;
            if ((var11 = GameData.b(this.e)) != null && var11.a != null) {
               short var12 = var9.b;
               int var13 = var10.w;
               int var14 = var10.h;
               short var15 = var10.x;
               short var16 = var10.y;
               if (var15 > var11.a.getWidth()) {
                  var15 = 0;
               }

               if (var16 > var11.a.getHeight()) {
                  var16 = 0;
               }

               if (var15 + var13 > var11.a.getWidth()) {
                  var13 = var11.a.getWidth() - var15;
               }

               if (var16 + var14 > var11.a.getHeight()) {
                  var14 = var11.a.getHeight() - var16;
               }

               int var17 = var9.d == 1 ? 2 : 0;
               if (var5 == 2 || var5 == 6) {
                  var17 = var17 == 2 ? 0 : 2;
                  var12 = (short)(-(var12 + var13));
               }

               var1.drawRegion(var11.a, var15, var16, var13, var14, var17, var2 + var12, var3 + var9.c, 0);
            }
         }
      } catch (Exception var19) {
      }
   }

   public final void paintBottomFrame(mGraphics var1, int var2, int var3, int var4, int var5) {
      this.paintFrame(var1, var2, var3, var4, var5, false);
   }

   public final void paintTopFrame(mGraphics var1, int var2, int var3, int var4, int var5) {
      this.paintFrame(var1, var2, var3, var4, var5, true);
   }

   public final boolean isLoaded() {
      return this.c();
   }

   public final int frameCount() {
      return this.c() ? this.a.size() : 0;
   }

   private void a(boolean var1) {
      if (this.h > 0) {
         this.q = var1;
         if (this.q) {
            this.p = System.currentTimeMillis() + (long)this.h;
            return;
         }
      } else {
         this.q = false;
      }

   }

   public final void a() {
      if (this.a.size() > 0) {
         try {
            if (!this.q) {
               switch (this.o) {
                  case 0:
                     ++this.m;
                     if (this.m > this.d.length) {
                        this.i = true;
                        this.m = 0;
                     }

                     this.l = this.d[this.m];
                     return;
                  case 1:
                     ++this.m;
                     if (this.m > this.d.length) {
                        this.m = 0;
                        this.i = true;
                     }

                     this.l = this.d[this.m];
                     return;
                  case 2:
                     ++this.m;
                     if (this.m == (byte)(this.d.length - 1) && this.g - System.currentTimeMillis() < 0L) {
                        this.i = true;
                     }

                     if (this.m > this.d.length) {
                        this.m = 0;
                        this.a(true);
                     }

                     this.l = this.d[this.m];
                     return;
                  case 3:
                     ++this.m;
                     if (this.m > this.d.length) {
                        this.m = 0;
                        this.a(true);
                     }

                     this.l = this.d[this.m];
                     return;
                  default:
                     return;
               }
            }

            if (this.p - System.currentTimeMillis() < 0L) {
               this.a(false);
               return;
            }
         } catch (Exception var1) {
         }
      }

   }
}
