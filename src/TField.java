import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.TextBox;

public final class TField implements IActionListener {
   public int a;
   public int b;
   public int width;
   public int height;
   public boolean isFocus;
   private static int typeXpeed = 2;
   private static final int[] m = new int[]{18, 14, 11, 9, 6, 4, 2};
   private static int n = 0;
   private static String[] o = new String[]{" 0", ".,@?!_1\"/$-():*+<=>;%&~#%^&*{}[];'/1", "abc2\u00e1\u00e0\u1ea3\u00e3\u1ea1\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb72", "def3\u0111\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec73", "ghi4\u00ed\u00ec\u1ec9\u0129\u1ecb4", "jkl5", "mno6\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee36", "pqrs7", "tuv8\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef18", "wxyz9\u00fd\u1ef3\u1ef7\u1ef9\u1ef59", "*", "#"};
   private static String[] p = new String[]{"0", "1", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9", "0", "0"};
   public String f = "";
   public String title_Null = "";
   private String text = "";
   private String passwordText = "";
   private String paintedText = "";
   private int caretPos = 0;
   private int counter = 0;
   private int maxTextLenght = 500;
   private int offsetX = 0;
   private int lastKey = -1984;
   private int keyInActiveState = 0;
   private int indexOfActiveChar = 0;
   private int showCaretCounter = 10;
   private int inputType = 0;
   public static boolean isQwerty;
   private int ac = 0;
   private static int ad = 11;
   public boolean i;
   public String name = "";
   public Command cmdClear;

   public final void a() {
      TextBox var1;
      (var1 = new TextBox(this.name, "", this.maxTextLenght, 0)).addCommand(new javax.microedition.lcdui.Command(mResources.okey, 4, 0));
      var1.addCommand(new javax.microedition.lcdui.Command("Cancel", 3, 0));
      var1.setCommandListener(new Command_Listener(this, var1));

      try {
         if (this.inputType == 2) {
            var1.setConstraints(65536);
         } else if (this.inputType == 1) {
            var1.setConstraints(2);
         } else {
            var1.setConstraints(0);
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      var1.setString(this.text);
      var1.setMaxSize(this.maxTextLenght);
      Display.getDisplay(GameMidlet.instance).setCurrent(var1);
   }

   public static boolean a(char var0) {
      return var0 >= '0' && var0 <= '9' || var0 >= 'A' && var0 <= 'Z' || var0 >= 'a' && var0 <= 'z';
   }

   public TField() {
      this.text = "";
      n = mFont.tahoma_8b.getHeight() + 1;
      this.cmdClear = new Command(mResources.bb, this, 1000, (Object)null);
      if (RMS.d("qwerty") == 1) {
         isQwerty = true;
      }

   }

   public final void b() {
      if (this.caretPos > 0 && this.text.length() > 0) {
         this.text = this.text.substring(0, this.caretPos - 1) + this.text.substring(this.caretPos, this.text.length());
         --this.caretPos;
         this.e();
         this.f();
      }

   }

   private void e() {
      if (this.inputType == 2) {
         this.paintedText = this.passwordText;
      } else {
         this.paintedText = this.text;
      }

      if (this.offsetX < 0 && mFont.tahoma_8b.a(this.paintedText) + this.offsetX < this.width - 4 - 13) {
         this.offsetX = this.width - 10 - mFont.tahoma_8b.a(this.paintedText);
      }

      if (this.offsetX + mFont.tahoma_8b.a(this.paintedText.substring(0, this.caretPos)) <= 0) {
         this.offsetX = -mFont.tahoma_8b.a(this.paintedText.substring(0, this.caretPos));
         this.offsetX += 40;
      } else if (this.offsetX + mFont.tahoma_8b.a(this.paintedText.substring(0, this.caretPos)) >= this.width - 12) {
         this.offsetX = this.width - 10 - mFont.tahoma_8b.a(this.paintedText.substring(0, this.caretPos)) - 8;
      }

      if (this.offsetX > 0) {
         this.offsetX = 0;
      }

   }

   private void d(int var1) {
      if ((this.inputType != 2 && this.inputType != 3 || var1 >= 48 && var1 <= 57 || var1 >= 65 && var1 <= 90 || var1 >= 97 && var1 <= 122) && this.text.length() < this.maxTextLenght) {
         String var2 = this.text.substring(0, this.caretPos) + (char)var1;
         if (this.caretPos < this.text.length()) {
            var2 = var2 + this.text.substring(this.caretPos, this.text.length());
         }

         this.text = var2;
         ++this.caretPos;
         this.f();
         this.e();
      }

   }

   public final boolean a(int var1) {
      if (var1 != 8 && var1 != -8 && var1 != 204) {
         if (var1 >= 65 && var1 <= 122 && !isQwerty) {
            isQwerty = true;
            RMS.writeRecord("qwerty", 1);
         }

         if (isQwerty) {
            if (var1 == 45) {
               if (var1 == this.lastKey && this.keyInActiveState < m[typeXpeed]) {
                  this.text = this.text.substring(0, this.caretPos - 1) + '_';
                  this.paintedText = this.text;
                  this.f();
                  this.e();
                  this.lastKey = -1984;
                  return false;
               }

               this.lastKey = 45;
            }

            if (var1 >= 32) {
               this.d(var1);
               return false;
            }
         }

         if (var1 == ad) {
            ++this.ac;
            if (this.ac > 3) {
               this.ac = 0;
            }

            this.keyInActiveState = 1;
            this.lastKey = var1;
            return false;
         } else {
            if (var1 == 42) {
               var1 = 58;
            }

            if (var1 == 35) {
               var1 = 59;
            }

            if (var1 >= 48 && var1 <= 59) {
               if (this.inputType != 0 && this.inputType != 2 && this.inputType != 3) {
                  if (this.inputType == 1) {
                     this.d(var1);
                     this.keyInActiveState = 1;
                  }
               } else {
                  String[] var2;
                  if (this.inputType != 2 && this.inputType != 3) {
                     var2 = o;
                  } else {
                     var2 = p;
                  }

                  char var3;
                  String var4;
                  if (var1 == this.lastKey) {
                     this.indexOfActiveChar = (this.indexOfActiveChar + 1) % var2[var1 - 48].length();
                     var3 = var2[var1 - 48].charAt(this.indexOfActiveChar);
                     if (this.ac == 0) {
                        var3 = Character.toLowerCase(var3);
                     } else if (this.ac == 1) {
                        var3 = Character.toUpperCase(var3);
                     } else if (this.ac == 2) {
                        var3 = Character.toUpperCase(var3);
                     } else {
                        var3 = var2[var1 - 48].charAt(var2[var1 - 48].length() - 1);
                     }

                     var4 = this.text.substring(0, this.caretPos - 1) + var3;
                     if (this.caretPos < this.text.length()) {
                        var4 = var4 + this.text.substring(this.caretPos, this.text.length());
                     }

                     this.text = var4;
                     this.keyInActiveState = m[typeXpeed];
                     this.f();
                  } else if (this.text.length() < this.maxTextLenght) {
                     if (this.ac == 1 && this.lastKey != -1984) {
                        this.ac = 0;
                     }

                     this.indexOfActiveChar = 0;
                     var3 = var2[var1 - 48].charAt(this.indexOfActiveChar);
                     if (this.ac == 0) {
                        var3 = Character.toLowerCase(var3);
                     } else if (this.ac == 1) {
                        var3 = Character.toUpperCase(var3);
                     } else if (this.ac == 2) {
                        var3 = Character.toUpperCase(var3);
                     } else {
                        var3 = var2[var1 - 48].charAt(var2[var1 - 48].length() - 1);
                     }

                     var4 = this.text.substring(0, this.caretPos) + var3;
                     if (this.caretPos < this.text.length()) {
                        var4 = var4 + this.text.substring(this.caretPos, this.text.length());
                     }

                     this.text = var4;
                     this.keyInActiveState = m[typeXpeed];
                     ++this.caretPos;
                     this.f();
                     this.e();
                  }

                  this.lastKey = var1;
               }
            } else {
               this.indexOfActiveChar = 0;
               this.lastKey = -1984;
               if (var1 == 14) {
                  if (this.caretPos > 0) {
                     --this.caretPos;
                     this.e();
                     this.showCaretCounter = 10;
                     return false;
                  }
               } else if (var1 == 15) {
                  if (this.caretPos < this.text.length()) {
                     ++this.caretPos;
                     this.e();
                     this.showCaretCounter = 10;
                     return false;
                  }
               } else {
                  if (var1 == 19) {
                     this.b();
                     return false;
                  }

                  this.lastKey = var1;
               }
            }

            return true;
         }
      } else {
         this.b();
         return true;
      }
   }

   public final void a(mGraphics var1) {
      var1.setClip(0, 0, GameCanvas.width, GameCanvas.height);
      boolean var2 = this.isFocus;
      if (this.inputType == 2) {
         this.paintedText = this.passwordText;
      } else {
         this.paintedText = this.text;
      }

      if (this.paintedText.equals("")) {
         this.paintedText = this.title_Null;
      }

      Paint.a(var1, var2, this.a, this.b, this.width, this.height, 4 + this.offsetX + this.a, this.b + (this.height - mFont.tahoma_8b.getHeight()) / 2, this.paintedText);
      var1.setClip(this.a + 3, this.b + 1, this.width - 4, this.height - 4);
      var1.setColor(0);
      if (this.isFocus && this.keyInActiveState == 0 && (this.showCaretCounter > 0 || this.counter / 5 % 2 == 0)) {
         var1.setColor(11184810);
         var1.fillRect(5 + this.offsetX + this.a + mFont.tahoma_8b.a(this.paintedText.substring(0, this.caretPos)) - 1, this.b + (this.height - n) / 2 + 1, 1, n);
      }

   }

   public final void b(mGraphics var1) {
      boolean var2 = this.isFocus;
      if (this.inputType == 2) {
         this.paintedText = this.passwordText;
      } else {
         this.paintedText = this.text;
      }

      if (this.paintedText.equals("")) {
         this.paintedText = this.title_Null;
      }

      Paint.a(var1, var2, this.a, this.b, this.width, 4 + this.offsetX + this.a, this.b + (this.height - mFont.tahoma_8b.getHeight()) / 2, this.paintedText);
      var1.setColor(0);
      if (this.isFocus && this.keyInActiveState == 0 && (this.showCaretCounter > 0 || this.counter / 5 % 2 == 0)) {
         var1.setColor(11184810);
         var1.fillRect(5 + this.offsetX + this.a + mFont.tahoma_8b.a(this.paintedText.substring(0, this.caretPos)) - 1, this.b + (this.height - n) / 2 + 1, 1, n);
      }

   }

   private void f() {
      if (this.inputType == 2) {
         this.passwordText = "";

         for(int var1 = 0; var1 < this.text.length(); ++var1) {
            this.passwordText = this.passwordText + "*";
         }

         if (this.keyInActiveState > 0 && this.caretPos > 0) {
            this.passwordText = this.passwordText.substring(0, this.caretPos - 1) + this.text.charAt(this.caretPos - 1) + this.passwordText.substring(this.caretPos, this.passwordText.length());
         }
      }

   }

   public final void c() {
      ++this.counter;
      if (this.keyInActiveState > 0) {
         --this.keyInActiveState;
         if (this.keyInActiveState == 0) {
            this.indexOfActiveChar = 0;
            if (this.ac == 1 && this.lastKey != ad) {
               this.ac = 0;
            }

            this.lastKey = -1984;
            this.f();
         }
      }

      if (this.showCaretCounter > 0) {
         --this.showCaretCounter;
      }

      if (GameCanvas.isPointerJustRelease) {
         if (GameCanvas.mScreen == RegisterScr.a) {
            if (RegisterScr.a == null) {
               RegisterScr.a = new RegisterScr();
            }

            int var1 = RegisterScr.a.b.b;
            if (!GameCanvas.b(this.a, this.b - var1, this.width, this.height)) {
               this.isFocus = false;
               return;
            }

            this.a();
            return;
         }

         if (GameCanvas.b(this.a, this.b, this.width, this.height)) {
            this.a();
            return;
         }

         this.isFocus = false;
      }

   }

   public final String getText() {
      return this.text;
   }

   public final void a(String var1) {
      if (var1 != null) {
         this.lastKey = -1984;
         this.keyInActiveState = 0;
         this.indexOfActiveChar = 0;
         this.text = var1;
         this.paintedText = var1;
         this.f();
         this.caretPos = var1.length();
         this.e();
      }

   }

   public final void setMaxTextLenght(int var1) {
      this.maxTextLenght = var1;
   }

   public final void c(int var1) {
      this.inputType = var1;
   }

   public final void perform(int var1, Object var2) {
      switch (var1) {
         case 1000:
            this.b();
         default:
      }
   }
}
