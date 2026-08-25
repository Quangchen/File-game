

import javax.microedition.lcdui.Image;

public final class MapScr extends mScreen implements IActionListener {

    private static MapScr a;
    private boolean b;
    private static Image c = null;
    private static Image d;
    private static Image e;
    private static int f;
    private static int g;
    private static int h;
    private static int i;
    private static int mpoint;
    private static int k;
    private static int l;
    private static int m;
    private static int n;
    private static int o;
    private static int p;
    private static int q;
    private static int r;
    private static int s;
    private static int t;
    private static int u;
    private static int v;
    private static int w = 0;
    private static int x = 0;
    private static int[] y = new int[]{1, 156, 140, 174, 196, 195, 125, 148, 156, 173, 199, 203, 222, 264, 283, 277, 298, 307, 311, 315, 116, 90, 59, 31, 252, 55, 81, 111, 148, 187, 219, 253, 278, 304, 311, 310, 284, 309, 294, 62, 92, 117, 99, 134, 154, 175, 34, 52, 40, 78, 59, 82, 114, 179, 158, 142, 1, 215, 291, 242, 147, 301, 71, 23, 116, 126, 305, 286, 264, 20, 46, 70, 78, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private static int[] z = new int[]{1, 68, 75, 88, 80, 107, 87, 114, 136, 160, 168, 196, 216, 219, 248, 265, 276, 260, 232, 204, 111, 82, 79, 59, 168, 33, 28, 34, 45, 20, 54, 44, 19, 40, 60, 100, 175, 165, 134, 181, 199, 208, 221, 220, 219, 221, 195, 217, 246, 244, 250, 263, 262, 241, 252, 244, 2, 240, 197, 139, 16, 18, 208, 223, 239, 186, 120, 119, 135, 107, 125, 126, 148, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private boolean ae = false;
    private int af;
    private int ag;

    public MapScr() {
        super.right = new Command(mResources.am, this, 1000, (Object) null);
        super.center = new Command(mResources.fh, this, 1001, (Object) null);
        super.left = new Command("Chuyển đến", this, 14004, (Object) null);
    }

    private void e() {
        if (this.b) {
            super.left = null;
        } else {
            super.left = new Command("Chuyển đến", this, 14004, (Object) null);
        }

        int var1 = mGraphics.getWidth(TileMap.imgMiniMap);
        this.ac = (GameCanvas.width - var1) / 2;
        this.ad = (GameCanvas.height - 20 - mGraphics.getHeight(TileMap.imgMiniMap)) / 2;
        if (this.ac < 0) {
            this.ac = 0;
        }

        if (this.ad < 0) {
            this.ad = 0;
        }

        if (this.b) {
            t = var1 + 20 - GameCanvas.width;
            u = mGraphics.getHeight(TileMap.imgMiniMap) + 40 - GameCanvas.height;
            this.aa = var1 + 20;
            this.ab = var1 + 40;
            if (this.ab < GameCanvas.height - 26) {
                this.ab = GameCanvas.height - 26;
            }

            if (this.aa < GameCanvas.width) {
                this.aa = GameCanvas.width;
            }

            h = this.ac + Char.getMyChar().cx / 12;
            i = this.ad + Char.getMyChar().cy / 12;
        } else {
            t = 340 - GameCanvas.width;
            u = 340 - GameCanvas.height;
            h = y[TileMap.mapID] + w;
            i = z[TileMap.mapID] + x;
            this.aa = 330 + w;
            this.ab = 310 + x;
        }

        this.aa -= 10;
        this.ab -= 10;
        if (t < 0) {
            t = 0;
        }

        if (u < 0) {
            u = 0;
        }

        s = 0;
        r = 0;
        m = 0;
        l = 0;
        l = h - GameCanvas.centerX;
        m = i - GameCanvas.centerY;
    }

    public static MapScr a() {
        if (a == null) {
            a = new MapScr();
        }

        return a;
    }

    public final void update() {
        super.update();
        SmallImage.b();
        TileMap.c();
        super.right = new Command(mResources.am, this, 1000, (Object) null);
        super.center = new Command(mResources.fh, this, 1001, (Object) null);
        super.left = new Command("Chuyển đến", this, 14004, (Object) null);
        if (c == null) {
            c = GameCanvas.loadImage("/wm.png");
            d = GameCanvas.loadImage("/u/x.png");
            e = GameCanvas.loadImage("/u/wpt1.png");
            f = mGraphics.getHeight(c);
            g = mGraphics.getHeight(c);
        }

        if (GameCanvas.width > f) {
            w = GameCanvas.centerX - f / 2 - 12;
        }

        if (GameCanvas.height > g) {
            x = GameCanvas.centerY - g / 2;
        }

        this.e();
        f();
        mFont.number_green.clean();
        mFont.number_orange.clean();
        mFont.number_red.clean();
        mFont.number_white.clean();
        mFont.number_white.clean();
        mFont.number_yellow.clean();
        mFont.tahoma_7.clean();
        mFont.tahoma_7_blue.clean();
        mFont.tahoma_7_blue1.clean();
        mFont.tahoma_7_green.clean();
        mFont.tahoma_7_red.clean();
        mFont.tahoma_7b_blue.clean();
        mFont.tahoma_7b_purple.clean();
        mFont.tahoma_7b_red.clean();
        mFont.tahoma_7b_white.clean();
        System.gc();
        TileMap.p = TileMap.mapID;
    }

    public final void am() {
        super.am();
        c = null;
        d = null;
        System.gc();
        TileMap.k();
        SmallImage.c();
        mFont.number_green.load();
        mFont.number_orange.load();
        mFont.number_red.load();
        mFont.number_white.load();
        mFont.number_white.load();
        mFont.number_yellow.load();
        mFont.tahoma_7.load();
        mFont.tahoma_7_blue.load();
        mFont.tahoma_7_blue1.load();
        mFont.tahoma_7_green.load();
        mFont.tahoma_7_red.load();
        mFont.tahoma_7b_blue.load();
        mFont.tahoma_7b_purple.load();
        mFont.tahoma_7b_red.load();
        mFont.tahoma_7b_white.load();
    }

    public final void paint(mGraphics var1) {
        boolean var2 = false;
        var1.setColor(0);
        var1.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
        var1.translateXY(10, 10);
        var1.translateXY(-r, -s);
        int var3;
        int var4;
        int var5;
        int var6;
        if (this.b) {
            var1.drawImage(TileMap.imgMiniMap, this.ac, this.ad, 0);

            for (var5 = 0; var5 < GameScr.vMobAttack.size(); ++var5) {
                Mob var7;
                var3 = (var7 = (Mob) GameScr.vMobAttack.elementAt(var5)).curX / 12;
                var4 = var7.curY / 12;
                if (var7.lv < Char.getMyChar().cLevel - 2) {
                    var1.setColor(11184810);
                } else if (var7.lv > Char.getMyChar().cLevel + 2) {
                    var1.setColor(16711680);
                } else {
                    var1.setColor(16776960);
                }

                var1.fillRect(this.ac + var3 - 1, this.ad + var4 - 1, 3, 3);
            }

            for (var5 = 0; var5 < GameScr.vParty.size(); ++var5) {
                Party var9;
                if ((var9 = (Party) GameScr.vParty.elementAt(var5)).f != null && var9.f != Char.getMyChar()) {
                    var3 = var9.f.cx / 12;
                    var4 = var9.f.cy / 12;
                    if (GameCanvas.gameTick % 10 < 8) {
                        var1.setColor(16777215);
                        var1.fillRect(this.ac + var3 - 2, this.ad + var4 - 2, 5, 5);
                        var1.setColor(65280);
                        var1.fillRect(this.ac + var3 - 1, this.ad + var4 - 1, 3, 3);
                    }
                }
            }

            GameScr.getInstance();
            GameScr.ae();

            for (var6 = 0; var6 < GameScr.ah.size(); ++var6) {
                Npc var10;
                var3 = (var10 = (Npc) GameScr.ah.elementAt(var6)).cx / 12;
                var4 = var10.cy / 12;
                var1.setColor(16777215);
                var1.fillRect(this.ac + var3 - 2, this.ad + var4 - 2, 5, 5);
                var1.setColor(65280);
                var1.fillRect(this.ac + var3 - 1, this.ad + var4 - 1, 3, 3);
                var1.drawImage(d, this.ac + var3, this.ad + var4, 3);
                var2 = true;
            }

            for (byte var11 = 0; var11 < TileMap.vGo.size(); ++var11) {
                Waypoint var8;
                var3 = ((var8 = (Waypoint) TileMap.vGo.elementAt(var11)).a + var8.c) / 2 / 12;
                var4 = (var8.b + var8.d) / 2 / 12;
                if (GameCanvas.gameTick % 10 < 8) {
                    var1.setColor(0);
                    var1.fillRect(this.ac + var3 - 2, this.ad + var4 - 2, 5, 5);
                    var1.setColor(16777215);
                    var1.fillRect(this.ac + var3 - 1, this.ad + var4 - 1, 3, 3);
                }
            }

            var3 = Char.getMyChar().cx / 12;
            var4 = Char.getMyChar().cy / 12;
            var1.setColor(16777215);
            var1.fillRect(this.ac + var3 - 2, this.ad + var4 - 2, 5, 5);
            if (GameCanvas.gameTick % 10 > 5) {
                var1.setColor(255);
                var1.fillRect(this.ac + var3 - 1, this.ad + var4 - 1, 3, 3);
            }

            var1.drawImage(e, h - 2, i, 0);
            super.paint(var1);
            if (!var2) {
                mFont.tahoma_7_white.writeText(var1, TileMap.mapName, 10, GameCanvas.height - 17, 0);
            }
        } else {
            if (GameCanvas.width > f && GameCanvas.height > g) {
                var1.drawImage(c, GameCanvas.centerX, GameCanvas.centerY, StaticObj.g);
            } else if (GameCanvas.width > f) {
                var1.drawImage(c, GameCanvas.centerX, 0, StaticObj.a);
            } else if (GameCanvas.height > g) {
                var1.drawImage(c, 0, GameCanvas.centerY, StaticObj.h);
            } else {
                var1.drawImage(c, 0, 0, 0);
            }

            if (TileMap.mapID < TileMap.mapNames.length && TileMap.mapID >= 0) {
                var3 = 0;
                if (y[TileMap.mapID] != 1 || z[TileMap.mapID] != 1) {
                    var3 = y[TileMap.mapID] < 100 ? 0 : (y[TileMap.mapID] > 200 ? 1 : 2);
                    GameCanvas.a(y[TileMap.mapID] + w, z[TileMap.mapID] + x, var1);
                }

                var5 = 0;
                if (v >= 0) {
                    var3 = y[v] < 100 ? 0 : (y[v] > 200 ? 1 : 2);
                    mFont.tahoma_7_white.writeText(var1, TileMap.mapNames[v], y[v] + w, z[v] + x - 20, var3, mFont.tahoma_7_grey);
                    var1.drawImage(d, y[v] + w, z[v] + x, 3);
                    var2 = true;
                    var5 = z[v] - 20;
                } else if (y[TileMap.mapID] != 1 || z[TileMap.mapID] != 1) {
                    var5 = z[TileMap.mapID] - 20;
                    mFont.tahoma_7_yellow.writeText(var1, TileMap.mapNames[TileMap.mapID], y[TileMap.mapID] + w, z[TileMap.mapID] + x - 20, var3, mFont.tahoma_7_grey);
                }

                for (var6 = 0; var6 < Char.getMyChar().taskOrders.size(); ++var6) {
                    TaskOrder var12;
                    if ((var12 = (TaskOrder) Char.getMyChar().taskOrders.elementAt(var6)).mapId >= 0 && var12.mapId < y.length) {
                        var1.drawImage(d, y[var12.mapId] + w, z[var12.mapId] + x, 3);
                    }
                }

                if (mpoint >= 0 && (v < 0 && TileMap.mapID != mpoint || v >= 0 && mpoint != v)) {
                    var3 = y[mpoint] < 100 ? 0 : (y[mpoint] > 200 ? 1 : 2);
                    var6 = y[mpoint];
                    if ((var4 = z[mpoint] - 20) > var5 && var4 - var5 < 30) {
                        var4 += 40;
                    }

                    if (var4 < var5 && var5 - var4 < 20) {
                        var4 -= 5;
                    }

                    mFont.tahoma_7_yellow.writeText(var1, TileMap.mapNames[mpoint] + "[MapID:"+mpoint+"]", var6 + w, var4 + x, var3, mFont.tahoma_7_grey);
                }
            }

            var1.drawImage(e, h - 2, i, 0);
            var1.translateXY(-var1.getTranslateX(), -var1.getTranslateY());
            super.paint(var1);
        }
        if (var2) {
            var1.drawImage(d, 10, 10, 3);
            mFont.tahoma_7_white.writeText(var1, mResources.ic, 20, 5, 0);
        }

    }

    public final void b() {
        super.b();
        if (++k > 10000) {
            k = 0;
        }

        if (r != l || s != m) {
            n = l - r << 1;
            o = m - s << 1;
            p += n;
            r += p >> 4;
            p &= 15;
            q += o;
            s += q >> 4;
            q &= 15;
            if (r < 0) {
                r = 0;
            }

            if (r > t) {
                r = t;
            }

            if (s < 0) {
                s = 0;
            }

            if (s > u) {
                s = u;
            }
        }

        boolean var1 = false;
        if (GameCanvas.l[2]) {
            if ((i -= 4) < x - 10) {
                i = x - 10;
            }

            var1 = true;
        }

        if (GameCanvas.l[8]) {
            if ((i += 4) > this.ab) {
                i = this.ab;
            }

            var1 = true;
        }

        if (GameCanvas.l[4]) {
            if ((h -= 4) < w - 10) {
                h = w - 10;
            }

            var1 = true;
        }

        if (GameCanvas.l[6]) {
            if ((h += 4) > this.aa) {
                h = this.aa;
            }

            var1 = true;
        }

        if (var1) {
            l = h - GameCanvas.centerX;
            m = i - GameCanvas.centerY;
            f();
        }

        GameScr.getInstance();
        v = GameScr.ad();
        if (GameCanvas.isPointerClick && GameCanvas.q < GameCanvas.height - mScreen.cmdH) {
            GameCanvas.isPointerClick = false;
            this.ae = true;
            this.af = GameCanvas.p;
            this.ag = GameCanvas.q;
        } else if (GameCanvas.m && this.ae) {
            l -= GameCanvas.p - this.af;
            m -= GameCanvas.q - this.ag;
            if (l < 0) {
                l = 0;
            }

            if (m < 0) {
                m = 0;
            }

            if (l > t) {
                l = t;
            }

            if (m > u) {
                m = u;
            }

            r = l;
            s = m;
            this.af = GameCanvas.p;
            this.ag = GameCanvas.q;
        }

        if (GameCanvas.isPointerJustRelease) {
            int var3 = GameCanvas.r - GameCanvas.p;
            int var2 = GameCanvas.s - GameCanvas.q;
            if (var3 < 10 && var2 < 10) {
                h = r + GameCanvas.r - 8;
                i = s + GameCanvas.s - 8;
                f();
            }

            this.ae = false;
            GameCanvas.isPointerJustRelease = false;
        }

        if (GameCanvas.isTouch && GameCanvas.width >= 320) {
            super.left.x = GameCanvas.width / 2 - 160;
            super.center.x = GameCanvas.width / 2 - 35;
            super.right.x = GameCanvas.width / 2 + 88;
            super.left.y = super.center.y = super.right.y = GameCanvas.height - 26;
        }

    }

    private static void f() {
        mpoint = -1;

        for (int var0 = 0; var0 < y.length; ++var0) {
            if (Res.e(h - (y[var0] + w)) < 10 && Res.e(i - (z[var0] + x)) < 10) {
                mpoint = var0;
                return;
            }
        }

    }

    public final void perform(int var1, Object var2) {
        switch (var1) {
            case 1000:
                if (Char.getMyChar().cHP <= 0 || Char.getMyChar().statusMe == 14 || Char.getMyChar().statusMe == 5) {
                    super.center = GameScr.getInstance().fa;
                    Char.getMyChar().cHP = 0;
                }

                GameScr.getInstance().update();
                return;
            case 1001:
                this.b = !this.b;
                this.e();
                return;
            case 14004:
                TileMap.gomap(mpoint);
                return;
            default:
        }
    }
}
