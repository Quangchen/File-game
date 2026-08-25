
public final class AutoSell extends Auto {
    private int c;
    private int d;
    private boolean e;
    private boolean f;
    private boolean ab;
    private String ac;
    private MyVector ad;
    private MyVector ae;
    private long af;
    public static boolean a;
    public static boolean b;
 
    public AutoSell() {
    }
 
    public final void a(String var1, int var2) {
       if (SettingAutoMuaBan.c()) {
          Service.getInstance().l(var2);
          this.ac = var1;
          this.ab = true;
          this.f = true;
          this.ad = null;
          this.ae = null;
          this.e = true;
       } else {
          for(int var7 = 0; var7 < Code.splitString(AutoReceiver.stringNameCharNhanDo, ",").length; ++var7) {
             if (var1.equals(Code.splitString(AutoReceiver.stringNameCharNhanDo, ",")[var7])) {
                Service.getInstance().l(var2);
                b = true;
                return;
             }
          }
 
          Service.getInstance().h();
          Code.a(var1, "<Hiện mk ko mua vp nào>");
       }
    }
 
    public final void a(String var1, String var2) {
       String[] var95 = Code.splitString(var2, " ");
       if (this.e) {
          if (!GameScr.ci) {
             Code.a(var1, "<Đang giao dịch với người khác. Mong bạn chờ giây lát>");
          }
 
       } else {
          int var96;
          MyVector var3;
          MyVector var4;
          int var5;
          int var6;
          int var7;
          String var8;
          String var9;
          RMS var10;
          int var11;
          int var12;
          Item var15;
          int var92;
          if (var95[0].toLowerCase().equals("buy")) {
             var3 = new MyVector();
             var4 = new MyVector();
             var5 = 0;
             var6 = 1;
 
             for(var7 = var95.length - 1; var6 <= var7; var6 += 2) {
                var8 = var95[var6];
                var9 = var95[var6 + 1];
                if ((var10 = SettingAutoMuaBan.a(var8)) != null && var10.c > 0) {
                   if (var10.c > 0 && Char.k(var10.a.id) <= var10.e) {
                      Code.a(var1, "<" + var8 + " hết hàng, chờ mình một tý mình đang gọi ck mình về bơm>");
 
                      for(var92 = 0; var92 < Char.getMyChar().arrItemBox.length; ++var92) {
                         var15 = Char.getMyChar().arrItemBox[var92];
                         if (var15 != null && var15.template.id == var10.a.id) {
                            Service.getInstance().d(var15.indexUI);
                         }
                      }
 
                      for(var96 = 0; var96 < Code.splitString(AutoReceiver.stringNameCharNhanDo, ",").length; ++var96) {
                         Service.getInstance().a(Code.splitString(AutoReceiver.stringNameCharNhanDo, ",")[var96], "anxin " + TileMap.mapID + " " + TileMap.zoneID + " " + var10.a.id);
                      }
                   } else {
                      try {
                         var11 = Integer.parseInt(var9);
                      } catch (Exception var93) {
                         Code.a(var1, "<" + var8 + " số lượng lỗi>");
                         continue;
                      }
 
                      if (var10.d() < var11) {
                         Code.a(var1, "<" + var8 + " số lượng Item này hiện không đủ, chờ mình một tý mình đang gọi ck mình về bơm>");
 
                         for(var92 = 0; var92 < Char.getMyChar().arrItemBox.length; ++var92) {
                            var15 = Char.getMyChar().arrItemBox[var92];
                            if (var15 != null && var15.template.id == var10.a.id) {
                               Service.getInstance().d(var15.indexUI);
                            }
                         }
 
                         for(var96 = 0; var96 < Code.splitString(AutoReceiver.stringNameCharNhanDo, ",").length; ++var96) {
                            Service.getInstance().a(Code.splitString(AutoReceiver.stringNameCharNhanDo, ",")[var96], "anxin " + TileMap.mapID + " " + TileMap.zoneID + " " + var10.a.id);
                         }
                      } else {
                         var12 = var10.a.isUpToUp ? 1 : var11;
                         if (var5 + var12 > 12) {
                            Code.a(var1, "<" + var8 + " số lượng đầy. VP sau sẽ k dc tính>");
                            break;
                         }
 
                         var5 += var12;
                         var3.addElement(var10);
                         var4.addElement(new Integer(var11));
                      }
                   }
                } else {
                   Code.a(var1, "<" + var8 + " ko tồn tại hoặc mk ko bán vp này>");
                }
             }
 
             if (var3.size() == 0) {
                Code.a(var1, "<Giao dịch rỗng>");
             } else {
                this.ac = var1;
                this.ab = false;
                this.ad = var3;
                this.ae = var4;
                this.e = true;
             }
          } else if (!var95[0].toLowerCase().equals("mua")) {
             if (var95[0].toLowerCase().equals("donate")) {
                this.ac = var1;
                this.ab = false;
                this.ad = null;
                this.ae = null;
                this.e = true;
             } else if (var95[0].toLowerCase().equals("sell")) {
                if (SettingAutoMuaBan.c()) {
                   this.ac = var1;
                   this.ab = true;
                   this.f = false;
                   this.ad = null;
                   this.ae = null;
                   this.e = true;
                } else {
                   Code.a(var1, "<Hiện mk ko mua vp nào>");
                }
             } else {
                Code.a(var1, "<Bán hàng tự động v4 By Leo>");
                Code.a(var1, "<Danh sách hàng>");
                SettingAutoMuaBan.b(var1);
                Code.a(var1, "<-------------------------->");
                SettingAutoMuaBan.c(var1);
                Code.a(var1, "<-------------------------->");
             }
          } else {
             var3 = new MyVector();
             var4 = new MyVector();
             var5 = 0;
             var6 = 1;
 
             for(var7 = var95.length - 1; var6 <= var7; var6 += 2) {
                var8 = var95[var6];
                if ((var10 = SettingAutoMuaBan.a(var9 = var95[var6 + 1])) != null && var10.c > 0) {
                   if (var10.c > 0 && Char.k(var10.a.id) <= var10.e) {
                      Code.a(var1, "<" + var9 + " hết hàng, chờ mình một tý mình đang gọi ck mình về bơm>");
 
                      for(var92 = 0; var92 < Char.getMyChar().arrItemBox.length; ++var92) {
                         var15 = Char.getMyChar().arrItemBox[var92];
                         if (var15 != null && var15.template.id == var10.a.id) {
                            Service.getInstance().d(var15.indexUI);
                         }
                      }
 
                      for(var96 = 0; var96 < Code.splitString(AutoReceiver.stringNameCharNhanDo, ",").length; ++var96) {
                         Service.getInstance().a(Code.splitString(AutoReceiver.stringNameCharNhanDo, ",")[var96], "anxin " + TileMap.mapID + " " + TileMap.zoneID + " " + var10.a.id);
                      }
                   } else {
                      try {
                         var11 = Integer.parseInt(var8);
                      } catch (Exception var94) {
                         Code.a(var1, "<" + var9 + " số lượng lỗi>");
                         continue;
                      }
 
                      if (var10.d() < var11) {
                         Code.a(var1, "<" + var9 + " số lượng Item này hiện không đủ, chờ mình một tý mình đang gọi ck mình về bơm>");
 
                         for(var92 = 0; var92 < Char.getMyChar().arrItemBox.length; ++var92) {
                            var15 = Char.getMyChar().arrItemBox[var92];
                            if (var15 != null && var15.template.id == var10.a.id) {
                               Service.getInstance().d(var15.indexUI);
                            }
                         }
 
                         boolean var20 = false;
 
                         for(var96 = 0; var96 < Code.splitString(AutoReceiver.stringNameCharNhanDo, ",").length; ++var96) {
                            Service.getInstance().a(Code.splitString(AutoReceiver.stringNameCharNhanDo, ",")[var96], "anxin " + TileMap.mapID + " " + TileMap.zoneID + " " + var10.a.id);
                         }
                      } else {
                         var12 = var10.a.isUpToUp ? 1 : var11;
                         if (var5 + var12 > 12) {
                            Code.a(var1, "<" + var9 + " số lượng đầy. VP sau sẽ k dc tính>");
                            break;
                         }
 
                         var5 += var12;
                         var3.addElement(var10);
                         var4.addElement(new Integer(var11));
                      }
                   }
                } else {
                   Code.a(var1, "<" + var9 + " ko tồn tại hoặc mk ko bán vp này>");
                }
             }
 
             if (var3.size() == 0) {
                Code.a(var1, "<Giao dịch rỗng>");
             } else {
                this.ac = var1;
                this.ab = false;
                this.ad = var3;
                this.ae = var4;
                this.e = true;
             }
          }
       }
    }
 
    public final void a() {
       super.a();
       super.mapID = TileMap.mapID;
       super.zoneID = TileMap.zoneID;
       this.c = Char.getMyChar().cx;
       this.d = Char.getMyChar().cy;
       this.af = 0L;
       this.e = false;
       SettingAutoMuaBan.a = -1;
       (new Thread(new AutoMuaBanKTG())).start();
    }
 
    private void a(Char var1) {
       Item[] var2 = new Item[12];
       a = false;
       long var3;
       if (!this.f) {
          if (!Char.d(var1.cx, var1.cy)) {
             Code.a(this.ac, "<Hãy đứng ở nơi dễ GD>");
             return;
          }
 
          Service.getInstance().tradeInvite(var1.charID);
          if (!LockGame.a(20000L)) {
             if (GameScr.ci) {
                 try {
                     Service.getInstance().i();
                     Thread.sleep(1000L);
                 } catch (InterruptedException ex) {
                     
                 }
             }
 
             Code.a(this.ac, "<Hãy đồng ý giao dịch>");
             return;
          }
       } else {
          var3 = System.currentTimeMillis();
 
          while(!GameScr.ci || System.currentTimeMillis() - var3 >= 10000L) {
              try {
                  Thread.sleep(1000L);
              } catch (InterruptedException ex) {
                  
              }
          }
       }
 
       var3 = System.currentTimeMillis();
 
       while(GameScr.getInstance().da != 1) {
           try {
               if (a || System.currentTimeMillis() - var3 >= 20000L) {
                   if (GameScr.ci) {
                       try {
                           Service.getInstance().i();
                           Thread.sleep(1000L);
                       } catch (InterruptedException ex) {
                           
                       }
                   }
                   
                   Code.a(this.ac, "<Hãy giao dịch lại>");
                   return;
               }
               
               Thread.sleep(200L);
           } catch (InterruptedException ex) {
               
           }
       }
 
       MyVector var8 = new MyVector();
       MyVector var9 = new MyVector();
 
       int var4;
       int var7;
       int var10;
       for(var4 = 0; var4 < GameScr.arrItemTradeOrder.length; ++var4) {
          if (GameScr.arrItemTradeOrder[var4] != null) {
             RMS var5;
             if ((var5 = SettingAutoMuaBan.a(GameScr.arrItemTradeOrder[var4].template.id)) == null || !var5.c()) {
                if (GameScr.ci) {
                    try {
                        Service.getInstance().i();
                        Thread.sleep(1000L);
                    } catch (InterruptedException ex) {
                        
                    }
                }
 
                Code.a(this.ac, "<Item " + var4 + ": bạn đã GD vp mk ko mua>");
                return;
             }
 
             int var6 = GameScr.arrItemTradeOrder[var4].template.isUpToUp ? GameScr.arrItemTradeOrder[var4].quantity : 1;
             if (var8.contains(var5)) {
                var7 = var8.indexOf(var5);
                var10 = ((Integer)var9.elementAt(var7)).intValue() + var6;
                var9.setElementAt(new Integer(var10), var7);
             } else {
                var8.addElement(var5);
                var9.addElement(new Integer(var6));
             }
          }
       }
 
       if (var8.size() == 0) {
          if (GameScr.ci) {
              try {
                  Service.getInstance().i();
                  Thread.sleep(1000L);
              } catch (InterruptedException ex) {
                  
              }
          }
 
          Code.a(this.ac, "<Giao dịch trống>");
       } else {
          var4 = 0;
 
          for(var10 = 0; var10 < var8.size(); ++var10) {
             RMS var11 = (RMS)var8.elementAt(var10);
             var7 = ((Integer)var9.elementAt(var10)).intValue();
             AutoLuckyCard.d = var8.size();
             System.out.println("Số vp đang có" + Char.k(var11.a.id));
             System.out.println("Lượng giao dịch" + var7);
             if (Char.k(var11.a.id) + var7 > var11.h) {
                if (GameScr.ci) {
                    try {
                        Service.getInstance().i();
                        Thread.sleep(1000L);
                    } catch (InterruptedException ex) {
                        
                    }
                }
 
                Code.a(this.ac, "<" + var11.a.name + " bạn đá bán quá số mk cần mua>");
                return;
             }
 
             var4 += var7 * var11.g * 1000 / var11.f;
          }
 
          if (var4 > Char.getMyChar().xu) {
             if (GameScr.ci) {
                Service.getInstance().i();
             }
 
             Code.a(this.ac, "<Hiện mk ko đủ xu, xin bạn quay lại sau>");
          } else {
             GameScr.getInstance().db = var4;
             GameScr.arrItemTradeMe = var2;
             Service.getInstance().tradeLock(var4, var2);
             GameScr.getInstance().cz = 1;
             var3 = System.currentTimeMillis();
 
             while(System.currentTimeMillis() - var3 < 5000L) {
                 try {
                     if (a) {
                         Code.a(this.ac, "<Hãy giao dịch lại>");
                         GameScr.chatPopup(String.valueOf(AutoLuckyCard.d));
                         return;
                     }
                     
                     Thread.sleep(200L);
                 } catch (InterruptedException ex) {
                     
                 }
             }
 
             Service.getInstance().j();
             GameScr.getInstance().cz = 2;
 
             while(GameScr.getInstance().db < 2) {
                 try {
                     if (a || System.currentTimeMillis() - var3 >= 20000L) {
                         if (GameScr.ci) {
                             try {
                                 Service.getInstance().i();
                                 Thread.sleep(1000L);
                             } catch (InterruptedException ex) {
                                 
                             }
                         }
                         
                         Code.a(this.ac, "<Hãy giao dịch lại>");
                         return;
                     }
                     
                     Thread.sleep(200L);
                 } catch (InterruptedException ex) {
                     
                 }
             }
 
          }
       }
    }
 
    private void f(Char var1) {
       Item[] var2 = new Item[12];
       int var3 = 0;
       int var4 = 0;
       boolean var5 = false;
       int var6;
       int var8;
       if (this.ad != null) {
          for(var6 = 0; var6 < this.ad.size(); ++var6) {
             RMS var7 = (RMS)this.ad.elementAt(var6);
             var8 = ((Integer)this.ae.elementAt(var6)).intValue();
             if (var7.a.isUpToUp) {
                Item var14;
                if ((var14 = Char.getItemByID(var7.a.id)) == null) {
                   Code.a(this.ac, "<Xin lỗi, có lỗi chọn item>");
                   return;
                }
 
                Service.getInstance().k(var14.indexUI, var8);
                LockGame.s();
                if ((var14 = Char.b(var7.a.id, var8)) == null) {
                   Code.a(this.ac, "<Xin lỗi, có lỗi chọn item>");
                   return;
                }
 
                var2[var3++] = var14;
                var5 = true;
             } else {
                Char var9 = Char.getMyChar();
                int var10 = 0;
 
                label184:
                for(int var11 = 0; var10 < var8; ++var10) {
                   while(var11 < var9.arrItemBag.length) {
                      Item var12;
                      if ((var12 = var9.arrItemBag[var11]) != null && var12.template.id == var7.a.id) {
                         var2[var3++] = var12;
                         ++var11;
                         continue label184;
                      }
 
                      ++var11;
                   }
 
                   Code.a(this.ac, "<Xin lỗi, có lỗi chọn item>");
                   return;
                }
             }
 
             var4 += var8 * var7.d * 1000 / var7.c;
          }
 
          if (var4 == 0) {
             var4 = 1;
          }
       }
 
       a = false;
       if (!Char.d(var1.cx, var1.cy)) {
          Code.a(this.ac, "<Hãy đứng ở nơi dễ GD>");
          if (var5) {
             Service.getInstance().bagSort();
             LockGame.s();
          }
 
       } else {
          Service.getInstance().tradeInvite(var1.charID);
          if (!LockGame.a(20000L)) {
             if (GameScr.ci) {
                Service.getInstance().i();
                 try {
                     Thread.sleep(1000L);
                 } catch (InterruptedException ex) {
                     
                 }
             }
 
             if (var5) {
                Service.getInstance().bagSort();
                LockGame.s();
             }
 
             Code.a(this.ac, "<Hãy đồng ý giao dịch>");
          } else {
             GameScr.getInstance().db = 0;
             GameScr.arrItemTradeMe = new Item[12];
 
             for(var6 = 0; var6 < 12; ++var6) {
                GameScr.arrItemTradeMe[var6] = var2[var6];
                if (var2[var6] != null && var2[var6].indexUI >= 0 && var2[var6].indexUI < Char.getMyChar().arrItemBag.length) {
                   int var10001 = var2[var6].indexUI;
                   Char.getMyChar().arrItemBag[var10001] = null;
                }
             }
 
             Service.getInstance().tradeLock(0, var2);
             GameScr.getInstance().cz = 1;
             long var13 = System.currentTimeMillis();
 
             while(GameScr.getInstance().da != 1) {
                 try {
                     if (a || System.currentTimeMillis() - var13 >= 20000L) {
                         if (GameScr.ci) {
                             Service.getInstance().i();
                             Thread.sleep(1000L);
                         }
                         
                         if (var5) {
                             Service.getInstance().bagSort();
                             LockGame.s();
                         }
                         
                         Code.a(this.ac, "<Hãy giao dịch lại>");
                         return;
                     }
                     
                     Thread.sleep(200L);
                 } catch (InterruptedException ex) {
                     
                 }
             }
 
             var13 = System.currentTimeMillis();
             if (GameScr.getInstance().dc < var4) {
                if (GameScr.ci) {
                    try {
                        Service.getInstance().i();
                        Thread.sleep(1000L);
                    } catch (InterruptedException ex) {
                        
                    }
                }
 
                if (var5) {
                   Service.getInstance().bagSort();
                   LockGame.s();
                }
 
                Code.a(this.ac, "<Không đủ xu>");
             } else {
                for(var8 = 0; var8 < GameScr.arrItemTradeOrder.length; ++var8) {
                   if (GameScr.arrItemTradeOrder[var8] != null && SettingAutoMuaBan.a(GameScr.arrItemTradeOrder[var8].template.id) == null) {
                      if (GameScr.ci) {
                          try {
                              Service.getInstance().i();
                              Thread.sleep(1000L);
                          } catch (InterruptedException ex) {
                              
                          }
                      }
 
                      if (var5) {
                         Service.getInstance().bagSort();
                         LockGame.s();
                      }
 
                      Code.a(this.ac, "<Chỉ GD xu>");
                      return;
                   }
                }
 
                while(System.currentTimeMillis() - var13 < 6000L) {
                    try {
                        if (a) {
                            if (var5) {
                                Service.getInstance().bagSort();
                                LockGame.s();
                            }
                            
                            Code.a(this.ac, "<Hãy giao dịch lại>");
                            return;
                        }
                        
                        Thread.sleep(200L);
                    } catch (InterruptedException ex) {
                        
                    }
                }
 
                Service.getInstance().j();
                GameScr.getInstance().cz = 2;
 
                while(GameScr.getInstance().da < 2) {
                    try {
                        if (a || System.currentTimeMillis() - var13 >= 20000L) {
                            if (GameScr.ci) {
                                try {
                                    Service.getInstance().i();
                                    Thread.sleep(1000L);
                                } catch (InterruptedException ex) {
                                    
                                }
                            }
                            
                            if (var5) {
                                Service.getInstance().bagSort();
                                LockGame.s();
                            }
                            
                            Code.a(this.ac, "<Hãy giao dịch lại>");
                            return;
                        }
                        
                        Thread.sleep(200L);
                    } catch (InterruptedException ex) {
                        
                    }
                }
 
             }
          }
       }
    }
 
    public final void run() {
       if (super.isDead()) {
          Auto.autoRemap(true);
       } else if (b) {
          LockGame.a(-1L);
          a = false;
          long var4 = System.currentTimeMillis();
 
          while(GameScr.getInstance().da != 1) {
             if (a || System.currentTimeMillis() - var4 >= 20000L) {
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
          b = false;
       } else if (super.mapID == TileMap.mapID && super.zoneID == TileMap.zoneID) {
          if (this.e) {
             String var1 = this.ac;
             int var2 = 0;
 
             Char var10000;
             while(true) {
                if (var2 >= GameScr.vCharInMap.size()) {
                   var10000 = null;
                   break;
                }
 
                Char var3;
                if ((var3 = (Char)GameScr.vCharInMap.elementAt(var2)).charName.equals(var1)) {
                   var10000 = var3;
                   break;
                }
 
                ++var2;
             }
 
             Char var6 = var10000;
             if (var10000 == null) {
                if (this.ac != null) {
                   Code.a(this.ac, "<Hãy đến " + TileMap.mapNames[TileMap.mapID] + " khu " + TileMap.zoneID + " để giao dịch>");
                }
 
                this.e = false;
             } else {
                 try {
                     if (this.ab) {
                         this.a(var6);
                     } else {
                         this.f(var6);
                     }
                     
                     Thread.sleep(500L);
                     this.e = false;
                 } catch (InterruptedException ex) {
                     
                 }
             }
          } else {
             if (Char.getMyChar().cx != this.c || Char.getMyChar().cy != this.d) {
                 try {
                     Char.charMove(this.c, this.d);
                     Thread.sleep(500L);
                 } catch (InterruptedException ex) {
                     
                 }
             }
 
             if (SettingMuaBan.c == 1 && System.currentTimeMillis() - this.af >= 5000L && !SettingAutoMuaBan.b().equals("")) {
                Service.getInstance().c(SettingAutoMuaBan.b());
                this.af = System.currentTimeMillis();
             }
 
          }
       } else {
          this.goMap(super.mapID, super.zoneID, -1, -1);
       }
    }
 
    public final String toString() {
       return "Auto Sell";
    }
 }
 