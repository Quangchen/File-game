
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;

public final class GameScr extends mScreen implements IActionListener, IChatable {

    public static GameScr a;
    public static int gW;
    public static int gH;
    public static int d;
    private static int fs;
    private static int ft;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
    public static int cmx;
    public static int cmy;
    private static int fu;
    private static int fv;
    private static int fw;
    private static int fx;
    public static int k;
    public static int l;
    private static int fy;
    private static int fz;
    public static int m;
    public static int n;
    public static int o;
    public static int p;
    private Command ga;
    private Command gb;
    private Command gc;
    private Command gd;
    private Command ge;
    private Command gf;
    public static int q;
    public static int r;
    public static SkillPaint[] skillPaints;
    public static Arrowpaint[] t;
    public static Part[] parts;
    public static EffectCharPaint[] efs;
    private int gg = 0;
    private boolean gh = false;
    public static MyVector w = new MyVector();
    public static MyVector vClan = new MyVector();
    public static MyVector vParty = new MyVector();
    public static MyVector z = new MyVector();
    public static MyVector aa = new MyVector();
    public static MyVector ab = new MyVector();
    public static MyVector ac = new MyVector();
    public static MyVector ad = new MyVector();
    public static MyVector vCharInMap = new MyVector();
    public static MyVector vItemMap = new MyVector();
    public static MyVector vMobAttack = new MyVector();
    public static MyVector ah = new MyVector();
    public static MyVector ai = new MyVector();
    private static MyVector gi = new MyVector();
    public static NClass[] nClass;
    private static int wItem = 28;
    private static int gk = 0;
    public static int indexSelect = 0;
    public static int indexRow = -1;
    private static int totalRowSetting;
    public static int indexMenu = 0;
    public static int an = -1;
    private static final int QUICK_STOP_AUTO_W = 22;
    private static final int QUICK_STOP_AUTO_H = 14;
    private static int quickStopAutoX = -1;
    private static int quickStopAutoY = -1;
    private Item currentItem;
    public static ItemOptionTemplate[] iOptionTemplates;
    public static SkillOptionTemplate[] sOptionTemplates;
    private static Scroll gn = new Scroll();
    public static Scroll scrMain = new Scroll();
    public static Item[] arrItemNonNam;
    public static Item[] arrItemNonNu;
    public static Item[] arrItemAoNam;
    public static Item[] arrItemAoNu;
    public static Item[] arrItemGangTayNam;
    public static Item[] arrItemGangTayNu;
    public static Item[] arrItemQuanNam;
    public static Item[] arrItemQuanNu;
    public static Item[] arrItemGiayNam;
    public static Item[] arrItemGiayNu;
    public static Item[] arrItemLien;
    public static Item[] arrItemNhan;
    public static Item[] arrItemNgocBoi;
    public static Item[] arrItemPhu;
    public static Item[] arrItemWeapon;
    public static Item[] arrItemStack;
    public static Item[] arrItemStackLock;
    public static Item[] arrItemGrocery;
    public static Item[] arrItemGroceryLock;
    public static Item[] arrItemStore;
    public static Item[] arrItemElites;
    public static Item[] arrItemClanShop;
    public static Item[] arrItemBook;
    public static Item[] arrItemFashion;
    public static Item[] arrItemUpPeal;
    public static Item[] arrItemUpGrade;
    public static Item[] arrItemSplit;
    public static Item[] arrItemTradeMe;
    public static Item[] arrItemTradeOrder;
    public static Item[] arrItemConvert;
    public static ItemStands[] arrItemStands;
    public static short[] arrItemSprin;
    public int numSprinLeft;
    public static Item itemUpGrade;
    public static Item itemSplit;
    public static Item itemSell;
    private static boolean go;
    private static boolean gp;
    private static boolean gq = false;
    private static boolean gr = false;
    public static boolean cb = true;
    private static boolean gs;
    public static boolean cc = false;
    private static boolean gt = false;
    public static boolean cd = false;
    public static boolean ce = false;
    private static boolean gu = false;
    private static boolean gv = false;
    private static boolean gw = false;
    public static boolean cf = false;
    private static boolean gx = false;
    private static boolean gy = false;
    public static boolean cg = false;
    private static boolean gz = false;
    public static boolean isPaintInfoMe = false;
    private static boolean ha = false;
    private static boolean hb = false;
    private static boolean hc = false;
    private static boolean hd = false;
    private static boolean he = false;
    private static boolean hf = false;
    private static boolean hg = false;
    private static boolean hh = false;
    private static boolean hi = false;
    private static boolean hj = false;
    private static boolean hk = false;
    private static boolean hl = false;
    private static boolean hm = false;
    private static boolean hn = false;
    private static boolean ho = false;
    private static boolean hp = false;
    private static boolean hq = false;
    private static boolean hr = false;
    private static boolean hs = false;
    private static boolean ht = false;
    private static boolean hu = false;
    private static boolean hv = false;
    private static boolean hw = false;
    private static boolean hx = false;
    private static boolean hy = false;
    private static boolean showBox = false;
    private static boolean ia = false;
    private static boolean ib = false;
    public static boolean ci = false;
    private static boolean showZoneDialog = false;
    public static boolean showAutoPanel = false;
    public static boolean ck = false;
    private static boolean id = false;
    private static boolean ie = false;
    private static boolean ifa = false;
    private static boolean ig = false;
    private static boolean ih;
    private static boolean ii;
    public static boolean cl;
    private static boolean ij;
    private static boolean ik;
    private static boolean il;
    private static boolean im;
    private static boolean in = false;
    private static boolean io = false;
    public static Char currentCharViewInfo;
    public static long[] exps;
    public static int[] crystals;
    public static int[] upClothe;
    public static int[] upAdorn;
    public static int[] upWeapon;
    public static int[] coinUpCrystals;
    public static int[] coinUpClothes;
    public static int[] coinUpAdorns;
    public static int[] coinUpWeapons;
    public static int[] maxPercents;
    public static int[] goldUps;
    private static int[] ip = new int[]{0, 5000, 40000, 135000, 320000, 625000, 1080000, 1715000, 2560000, 3645000, 5000000};
    private int iq = 6;
    public int[] zones;
    private int[] ir;
    public int cz = 0;
    public int da = 0;
    public int db = 0;
    public int dc = 0;
    public int dd = 0;
    private int is = 0;
    private int it = 0;
    private int iu = 0;
    private int iv = -1;
    public int de = -1;
    public int df = -1;
    private boolean iw;
    public static byte[][] dg;
    public static byte[][] dh;
    private MyVector ix;
    private String iy;
    private TField iz = null;
    public static byte di;
    public static byte dj;
    public static byte dk;
    public static byte dl;
    public static byte dm;
    public static byte dn;
    public static byte doa;
    public static byte dp;
    private static Image ja;
    private static Image jb;
    private static Image jc;
    private static Image jd;
    private static Image je;
    private static Image jf;
    private static Image jg;
    private static Image jh;
    private static Image ji;
    private static Image jj;
    private static Image jk;
    private static Image jl;
    private static Image jm;
    private static Image jn;
    public static Image dq;
    public static Image dr;
    public static Image ds;
    public static Image dt;
    private static Image jo;
    public static Image du;
    public static Image dv;
    public String dw = "";
    public String dx = "";
    public int dy;
    public int dz;
    private static byte jp = 0;
    public static byte ea = 0;
    private int[] jq = new int[2];
    private int[] jr = new int[2];
    private int[] js;
    private int[] jt;
    public long eb;
    public String[] ec = new String[]{"10000", "20000", "30000", "50000", "100000", "200000", "500000", "1000000", "5000000"};
    public int ed;
    public int ee;
    public String[] ef;
    public static MyVector eg = new MyVector();
    public static MyVector eh = new MyVector();
    public static MyVector ei = new MyVector();
    private static Image ju;
    public static Image ej;
    public static Image ek;
    public static Image el;
    public static Image em;
    public static Image en;
    public static boolean eo = false;
    public static boolean ep = false;
    public static Skill[] keySkill;
    public static Skill[] arrSkill;
    private static byte[] jx;
    private static byte[] jy;
    private Command jz;
    private Command ka;
    private Command kb;
    private Command kc;
    static int eq;
    static int er;
    private long kd;
    public static int es;
    public boolean et = false;
    private int ke = 0;
    private int kf = -1;
    private long kg;
    private int kh = 0;
    private static int ki;
    private static int kj;
    private static int kk;
    private static int kl;
    private static int km;
    private static int kn;
    private static int ko;
    private static int kp;
    private static int kq;
    private static int kr;
    private static int ks;
    private static int kt;
    private static int ku;
    private static int kv;
    private static int kw;
    private static int kx;
    private static int ky;
    private static int[] dxTableSkill;
    private static int[] dyTableSkill;
    private static int marginLeftTableSkill;
    private static int marginTopTableSkill;
    private static int gapTableSkill;
    private static String[] le;
    private static String[] optimizeMobNameCache;
    private static long optimizeStatusCacheAt;
    private static String optimizeStatusLine1 = "";
    private static String optimizeStatusLine2 = "";
    private static String optimizeStatusLine3 = "";
    private static int[] lf;
    private static int[] lg;
    private static int[] lh;
    private static int[] li;
    private static int[] lj;
    private static int[] lk;
    private static int[] ll;
    private static int[] lm;
    private static int[] ln;
    private static int[] lo;
    private static int[] lp;
    private static Image[] lq;
    private static int lr;
    private static int ls;
    private static int lt;
    private static int lu;
    private static int lv;
    private static int lw;
    private static int lx;
    private static int ly;
    private static int lz;
    private static int ma;
    private static int mb;
    private static Image[] mc;
    public static int popupY;
    public static int popupX;
    private int md = 0;
    private String[] me = null;
    private String[] mf = null;
    private int mg = 0;
    private Command mh;
    private Command mi;
    private Command mj;
    private Command mk;
    private Command ml;
    private Command mm;
    private Command mn;
    private Command mo;
    private Command mp;
    private Command mq;
    private Command mr;
    private Command ms;
    private Command mt;
    private Command mu;
    private Command mv;
    private Command mw;
    private Command mx;
    private Command my;
    private Command mz;
    private Command na;
    private Command nb;
    private Command nc;
    private Command nd;
    private Command ne;
    private Command nf;
    private Command ng;
    private Command nh;
    private Command ni;
    private Command nj;
    private Command nk;
    private Command nl;
    private Command nm;
    private Command nn;
    private Command no;
    private Command np;
    private Command nq;
    private Command nr;
    private Command ns;
    private String nt;
    private String nu;
    private static int leftMargin;
    private static int nw;
    public static int ew;
    public static int ex;
    private static int nx;
    private static int ny;
    private static int nz;
    private static int oa;
    private static int ob;
    private static int oc;
    private int od = 0;
    private int[] oe = new int[]{0, 0, 0, 0, 600841, 600841, 667658, 667658, 3346944, 3346688, 4199680, 5052928, 3276851, 3932211, 4587571, 5046280, 6684682, 3359744};
    private int[][] of = new int[][]{{18687, 16869, 15052, 13235, 11161, 9344}, {45824, 39168, 32768, 26112, 19712, 13056}, {16744192, 15037184, 13395456, 11753728, 10046464, 8404992}, {13500671, 12058853, 10682572, 9371827, 7995545, 6684800}, {16711705, 15007767, 13369364, 11730962, 10027023, 8388621}};
    private int[] og = new int[]{2, 1, 1, 1, 1, 1};
    public static int ey;
    public static EffectCharPaint ez;
    private static int oh;
    private static int oi;
    private static int oj;
    private static int ok;
    public Command fa;
    private Command ol;
    private Command om;
    private Command on;
    private Command oo;
    private Command op;
    private Command oq;
    private Command or;
    public static String svTitle;
    public static String svAction;
    private int os;
    private int ot;
    private String ou;
    private long ov;
    private static long ow;
    private static int ox;
    private static int[] oy;
    private static int[] oz;
    public static int fb;
    public static int fc;
    public static int fd;
    private short pa;
    private short pb;
    private short pc;
    private String pd;
    private String pe;
    private String pf;
    private String pg;
    private String ph;
    private long pi;
    private boolean pj;
    public static byte fe;
    private mFont pk;
    private byte[] pl;
    private byte pm;
    private MyVector pn;
    private int po;
    private int pp;
    private int pq;
    private int pr;
    private int ps;
    private int pt;
    private int pu;
    private int pv;
    private int pw;
    private int px;
    private int py;
    private int pz;
    private int qa;
    private int qb;
    private int qc;
    private int qd;
    private String qe;
    private int[][] qf;
    public static boolean ff = true;
    private static InfoDlg qg = new InfoDlg();
    public static boolean fg;
    public String[] fh = new String[]{"LEFT", "UP", "RIGHT"};
    protected int[] fi = new int[600];
    private static boolean showPickItem = false;
    private static boolean showDelItem = false;
    private static MyVector qj;
    public static int fj;
    public static boolean fk;
    private static long qk;
    private static boolean showItemThrow = false;
    private static boolean showItemGather = false;
    private static boolean showAutoUseItem = false;
    public static boolean showFashion = false;
    private static int[] dyRowAuto = new int[mResources.labelSettingAuto.length];

    public static long b(int var0) {
        long var1 = 0L;

        for (int var3 = 0; var3 <= var0; ++var3) {
            var1 += exps[var3];
        }

        return var1;
    }

    public static void a() {
        vCharInMap.removeAllElements();
        vItemMap.removeAllElements();
        w.removeAllElements();
        Effect2.vEffect2.removeAllElements();
        Effect2.vAnimateEffect.removeAllElements();
        Effect2.vEffect2Outside.removeAllElements();
        vMobAttack.removeAllElements();
        ah.removeAllElements();
        ai.removeAllElements();
        Char.getMyChar().fj.removeAllElements();
    }

    public static void e() {
        Service.getInstance().n("KSkill");
        Service.getInstance().n("OSkill");
        Service.getInstance().n("CSkill");
    }

    public static void f() {
        Service.getInstance().n("KSkill");
        Service.getInstance().n("OSkill");
        Service.getInstance().n("CSkill");
    }

    public static void a(byte[] var0) {
        arrSkill = new Skill[5];
        int var1;
        int var2;
        Skill var3;
        if (var0 == null) {
            if (jx == null) {
                for (var1 = 0; var1 < arrSkill.length && var1 < Char.getMyChar().vSkillFight.size(); ++var1) {
                    Skill var4 = (Skill) Char.getMyChar().vSkillFight.elementAt(var1);
                    arrSkill[var1] = var4;
                }
            } else {
                for (var1 = 0; var1 < jx.length; ++var1) {
                    for (var2 = 0; var2 < Char.getMyChar().vSkillFight.size(); ++var2) {
                        if ((var3 = (Skill) Char.getMyChar().vSkillFight.elementAt(var2)).template.id == jx[var1]) {
                            arrSkill[var1] = var3;
                            break;
                        }
                    }
                }
            }

            aq();
        } else {
            for (var1 = 0; var1 < var0.length; ++var1) {
                for (var2 = 0; var2 < Char.getMyChar().vSkillFight.size(); ++var2) {
                    if ((var3 = (Skill) Char.getMyChar().vSkillFight.elementAt(var2)).template.id == var0[var1]) {
                        arrSkill[var1] = var3;
                        break;
                    }
                }
            }
        }

    }

    public static void b(byte[] var0) {
        keySkill = new Skill[3];
        int var1;
        int var2;
        Skill var3;
        if (var0 == null) {
            if (jy == null) {
                for (var1 = 0; var1 < keySkill.length && var1 < Char.getMyChar().vSkillFight.size(); ++var1) {
                    Skill var4 = (Skill) Char.getMyChar().vSkillFight.elementAt(var1);
                    keySkill[var1] = var4;
                }
            } else {
                for (var1 = 0; var1 < jy.length; ++var1) {
                    for (var2 = 0; var2 < Char.getMyChar().vSkillFight.size(); ++var2) {
                        if ((var3 = (Skill) Char.getMyChar().vSkillFight.elementAt(var2)).template.id == jy[var1]) {
                            keySkill[var1] = var3;
                            break;
                        }
                    }
                }
            }

            ar();
        } else {
            for (var1 = 0; var1 < var0.length; ++var1) {
                for (var2 = 0; var2 < Char.getMyChar().vSkillFight.size(); ++var2) {
                    if ((var3 = (Skill) Char.getMyChar().vSkillFight.elementAt(var2)).template.id == var0[var1]) {
                        keySkill[var1] = var3;
                        break;
                    }
                }
            }
        }

    }

    public static void c(byte[] var0) {
        if (var0 != null && var0.length != 0) {
            for (int var1 = 0; var1 < Char.getMyChar().vSkillFight.size(); ++var1) {
                Skill var2;
                if ((var2 = (Skill) Char.getMyChar().vSkillFight.elementAt(var1)).template.id == var0[0]) {
                    Char.getMyChar().selectSkill = var2;
                    Char.getMyChar().gi = var2;
                    break;
                }
            }
        } else if (Char.getMyChar().vSkillFight.size() > 0) {
            Char.getMyChar().selectSkill = (Skill) Char.getMyChar().vSkillFight.elementAt(0);
        }

        if (Char.getMyChar().selectSkill != null) {
            Service.getInstance().selectSkill(Char.getMyChar().selectSkill.template.id);
            Char.getMyChar();
        }

    }

    private static void a(SkillTemplate var0) {
        Skill var5 = Char.getMyChar().a(var0);
        MyVector var1 = new MyVector();

        for (int var2 = 0; var2 < 5; ++var2) {
            boolean var3 = false;
            if (arrSkill[var2] == null) {
                var3 = true;
            }

            Object[] var4;
            (var4 = new Object[2])[0] = var5;
            var4[1] = String.valueOf(var2);
            var1.addElement(new Command(mResources.fj + " " + (var2 + 1), 11120, var4));
            if (var3) {
                break;
            }
        }

        GameCanvas.menu.showMenu(var1);
    }

    private static void b(SkillTemplate var0) {
        Skill var5 = Char.getMyChar().a(var0);
        String[] var1 = TField.isQwerty ? mResources.shortCutQwerty : mResources.shortCutNumber;
        MyVector var2 = new MyVector();

        for (int var3 = 0; var3 < 3; ++var3) {
            Object[] var4;
            (var4 = new Object[2])[0] = var5;
            var4[1] = String.valueOf(var3);
            var2.addElement(new Command(var1[var3], 11121, var4));
        }

        GameCanvas.menu.showMenu(var2);
    }

    private static void aq() {
        byte[] var0 = new byte[arrSkill.length];

        for (int var1 = 0; var1 < arrSkill.length; ++var1) {
            if (arrSkill[var1] == null) {
                var0[var1] = -1;
            } else {
                var0[var1] = arrSkill[var1].template.id;
            }
        }

        if (Char.getMyChar().isHuman) {
            Service.getInstance().a("OSkill", var0, (byte) 0);
        } else {
            Service.getInstance().a("OSkill", var0, (byte) 1);
        }

    }

    private static void ar() {
        byte[] var0 = new byte[keySkill.length];

        for (int var1 = 0; var1 < keySkill.length; ++var1) {
            if (keySkill[var1] == null) {
                var0[var1] = -1;
            } else {
                var0[var1] = keySkill[var1].template.id;
            }
        }

        if (Char.getMyChar().isHuman) {
            Service.getInstance().a("KSkill", var0, (byte) 0);
        } else {
            Service.getInstance().a("KSkill", var0, (byte) 1);
        }

    }

    public static void a(Skill var0) {
        if (var0.template.type != 0) {
            int var1;
            for (var1 = 0; var1 < arrSkill.length; ++var1) {
                if (arrSkill[var1] == null) {
                    arrSkill[var1] = var0;
                    break;
                }
            }

            for (var1 = 0; var1 < keySkill.length; ++var1) {
                if (keySkill[var1] == null) {
                    keySkill[var1] = var0;
                    break;
                }
            }

            if (Char.getMyChar().selectSkill == null) {
                Char.getMyChar().selectSkill = var0;
                if (Code.auto instanceof As50) {
                    Auto.selectSkill = var0;
                }
            }

            ar();
            aq();
        }

    }

    public static boolean g() {
        for (int var0 = Char.getMyChar().arrItemBag.length - 1; var0 >= 0; --var0) {
            if (Char.getMyChar().arrItemBag[var0] == null) {
                return false;
            }
        }

        return true;
    }

    public static void a(String[] var0, Npc var1) {
        MyVector var2 = new MyVector();

        for (int var3 = 0; var3 < var0.length; ++var3) {
            var2.addElement(new Command(var0[var3], 11057, var1));
        }

        GameCanvas.menu.showMenu(var2);
    }

    public final void showTabBag() {
        currentCharViewInfo = Char.getMyChar();
        indexMenu = 0;
        this.showMenuIndex();
    }

    private void showTabSkill() {
        currentCharViewInfo = Char.getMyChar();
        indexMenu = 1;
        this.showMenuIndex();
    }

    private void showTabPotential() {
        currentCharViewInfo = Char.getMyChar();
        indexMenu = 2;
        this.showMenuIndex();
    }

    private void showTabInfoChar() {
        currentCharViewInfo = Char.getMyChar();
        indexMenu = 3;
        this.showMenuIndex();
    }

    private void showTabEquip() {
        currentCharViewInfo = Char.getMyChar();
        indexMenu = 4;
        this.showMenuIndex();
    }

    private void showTabMount() {
        currentCharViewInfo = Char.getMyChar();
        indexMenu = 5;
        this.showMenuIndex();
    }

    private void showTabBijuu() {
        currentCharViewInfo = Char.getMyChar();
        indexMenu = 6;
        this.showMenuIndex();
    }

    public static void i() {
        DataInputStream var0 = null;

        try {
            short var1;
            parts = new Part[var1 = (var0 = new DataInputStream(new ByteArrayInputStream(RMS.getRecord("nj_part")))).readShort()];

            for (int var2 = 0; var2 < var1; ++var2) {
                int var3 = var0.readByte();
                parts[var2] = new Part(var3);

                for (var3 = 0; var3 < parts[var2].partImages.length; ++var3) {
                    parts[var2].partImages[var3] = new PartImage();
                    parts[var2].partImages[var3].id = var0.readShort();
                    parts[var2].partImages[var3].dx = var0.readByte();
                    parts[var2].partImages[var3].dy = var0.readByte();
                }
            }

            return;
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                var0.close();
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

    }

    public static void j() {
        DataInputStream var0 = null;

        try {
            short var1;
            efs = new EffectCharPaint[var1 = (var0 = new DataInputStream(new ByteArrayInputStream(RMS.getRecord("nj_effect")))).readShort()];

            for (int var2 = 0; var2 < var1; ++var2) {
                efs[var2] = new EffectCharPaint();
                efs[var2].idEf = var0.readShort();
                efs[var2].arrEfInfo = new EffectInfoPaint[var0.readByte()];

                for (int var3 = 0; var3 < efs[var2].arrEfInfo.length; ++var3) {
                    efs[var2].arrEfInfo[var3] = new EffectInfoPaint();
                    efs[var2].arrEfInfo[var3].c = var0.readShort();
                    efs[var2].arrEfInfo[var3].a = var0.readByte();
                    efs[var2].arrEfInfo[var3].b = var0.readByte();
                }
            }

            return;
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                var0.close();
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

    }

    public static void k() {
        DataInputStream var0 = null;

        try {
            short var1;
            t = new Arrowpaint[var1 = (var0 = new DataInputStream(new ByteArrayInputStream(RMS.getRecord("nj_arrow")))).readShort()];

            for (int var2 = 0; var2 < var1; ++var2) {
                t[var2] = new Arrowpaint();
                var0.readShort();
                t[var2].a[0] = var0.readShort();
                t[var2].a[1] = var0.readShort();
                t[var2].a[2] = var0.readShort();
            }

            return;
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            try {
                var0.close();
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }

    }

    public static void l() {
        DataInputStream var0 = null;

        try {
            short var1 = (var0 = new DataInputStream(new ByteArrayInputStream(RMS.getRecord("nj_skill")))).readShort();
            int var2 = 0;

            int var3;
            for (var3 = 0; var3 < nClass.length; ++var3) {
                var2 += nClass[var3].skillTemplates.length;
            }

            skillPaints = new SkillPaint[var2];

            for (var3 = 0; var3 < var1; ++var3) {
                short var13 = var0.readShort();
                skillPaints[var13] = new SkillPaint();
                skillPaints[var13].id = var0.readShort();
                var0.readByte();
                skillPaints[var13].skillStand = new SkillInfoPaint[var0.readByte()];

                int var4;
                for (var4 = 0; var4 < skillPaints[var13].skillStand.length; ++var4) {
                    skillPaints[var13].skillStand[var4] = new SkillInfoPaint();
                    skillPaints[var13].skillStand[var4].a = var0.readByte();
                    skillPaints[var13].skillStand[var4].b = var0.readShort();
                    skillPaints[var13].skillStand[var4].c = var0.readShort();
                    skillPaints[var13].skillStand[var4].d = var0.readShort();
                    skillPaints[var13].skillStand[var4].e = var0.readShort();
                    skillPaints[var13].skillStand[var4].f = var0.readShort();
                    skillPaints[var13].skillStand[var4].g = var0.readShort();
                    skillPaints[var13].skillStand[var4].h = var0.readShort();
                    skillPaints[var13].skillStand[var4].i = var0.readShort();
                    skillPaints[var13].skillStand[var4].j = var0.readShort();
                    skillPaints[var13].skillStand[var4].k = var0.readShort();
                    skillPaints[var13].skillStand[var4].l = var0.readShort();
                    skillPaints[var13].skillStand[var4].m = var0.readShort();
                }

                skillPaints[var13].skillfly = new SkillInfoPaint[var0.readByte()];

                for (var4 = 0; var4 < skillPaints[var13].skillfly.length; ++var4) {
                    skillPaints[var13].skillfly[var4] = new SkillInfoPaint();
                    skillPaints[var13].skillfly[var4].a = var0.readByte();
                    skillPaints[var13].skillfly[var4].b = var0.readShort();
                    skillPaints[var13].skillfly[var4].c = var0.readShort();
                    skillPaints[var13].skillfly[var4].d = var0.readShort();
                    skillPaints[var13].skillfly[var4].e = var0.readShort();
                    skillPaints[var13].skillfly[var4].f = var0.readShort();
                    skillPaints[var13].skillfly[var4].g = var0.readShort();
                    skillPaints[var13].skillfly[var4].h = var0.readShort();
                    skillPaints[var13].skillfly[var4].i = var0.readShort();
                    skillPaints[var13].skillfly[var4].j = var0.readShort();
                    skillPaints[var13].skillfly[var4].k = var0.readShort();
                    skillPaints[var13].skillfly[var4].l = var0.readShort();
                    skillPaints[var13].skillfly[var4].m = var0.readShort();
                }
            }

            return;
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            try {
                var0.close();
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

    }

    public static void a(long var0) {
        long var2 = var0;

        int var4;
        for (var4 = 0; var4 < exps.length && var2 >= exps[var4]; ++var4) {
            var2 -= exps[var4];
        }

        long[] var5 = new long[]{(long) var4, var2};
        Char.getMyChar().cLevel = (int) var5[0];
        Char.getMyChar().ah = var5[1];
    }

    public static GameScr getInstance() {
        if (a == null) {
            a = new GameScr();
        }

        return a;
    }

    public static void n() {
        a = null;
        arrItemTradeOrder = null;
        arrItemTradeMe = null;
        arrItemSplit = null;
        arrItemUpGrade = null;
        arrItemUpPeal = null;
        itemSplit = null;
        itemUpGrade = null;
    }

    public final void o() {
        if (lq == null) {
            lq = new Image[3];

            for (int var1 = 0; var1 < 3; ++var1) {
                lq[var1] = GameCanvas.loadImage("/e/sp" + var1 + ".png");
            }
        }

        ll = new int[2];
        lm = new int[2];
        ln = new int[2];
        lo = new int[2];
        lp = new int[2];
        ln[0] = ln[1] = -1;
        this.br();
        Res.a();
    }

    public GameScr() {
        this.fa = new Command(mResources.nj[0], 11038);
        this.os = 30;
        this.ot = 0;
        this.ou = "";
        this.pk = mFont.tahoma_7b_yellow;
        this.pl = new byte[]{-1, -1, -1, -1, -1, -1};
        this.pm = 0;
        this.pn = new MyVector();
        this.qe = "";
        this.qf = new int[][]{new int[2], {200, 10}, {500, 20}, {1000, 50}, {2000, 100}, {5000, 200}, {10000, 500}, {20000, 1000}, {50000, 2000}, {100000, 5000}, {100000, 10000}};
        if (GameCanvas.width == 128 || GameCanvas.height <= 208) {
            wItem = 20;
        }

        this.kc = new Command(mResources.di, 11002);
        this.kb = new Command(mResources.he, 11003);
        this.ns = new Command(GameCanvas.isTouch ? mResources.xem : "", 11004);
        this.nr = new Command(mResources.by, 11005);
        this.ka = new Command(mResources.he, 11006);
        this.nq = new Command(GameCanvas.isTouch ? mResources.xem : "", 11007);
        this.np = new Command(mResources.by, 11008);
        this.no = new Command(GameCanvas.isTouch ? mResources.xem : "", 11009);
        this.nn = new Command(mResources.by, 11010);
        this.nm = new Command(GameCanvas.isTouch ? mResources.xem : "", 11011);
        this.nl = new Command(mResources.by, 11012);
        this.nk = new Command(GameCanvas.isTouch ? mResources.xem : "", 11013);
        this.nj = new Command(mResources.by, 11014);
        this.ni = new Command(GameCanvas.isTouch ? mResources.xem : "", 11015);
        this.nh = new Command(mResources.by, 11016);
        this.ne = new Command(GameCanvas.isTouch ? mResources.xem : "", 11017);
        this.ng = new Command(GameCanvas.isTouch ? mResources.xem : "", 13001);
        this.nd = new Command(mResources.by, 11018);
        this.nf = new Command(mResources.by, 13002);
        this.na = new Command(GameCanvas.isTouch ? mResources.xem : "", 11019);
        this.mz = new Command(mResources.by, 11020);
        this.mx = new Command(mResources.by, 14022);
        this.my = new Command(GameCanvas.isTouch ? mResources.xem : "", 14023);
        this.nc = new Command(GameCanvas.isTouch ? mResources.xem : "", 14018);
        this.nb = new Command(mResources.by, 14019);
        this.mw = new Command(mResources.am, 11021);
        this.or = new Command(mResources.chon, 11022);
        this.oq = new Command(GameCanvas.isTouch ? mResources.xem : "", 11023);
        this.oo = new Command(mResources.bk, 11024);
        this.op = new Command(mResources.bl, 110244);
        this.on = new Command(mResources.am, 11025);
        this.ol = new Command(mResources.suDung, 11026);
        this.om = new Command(mResources.sapXep, 110221);
        this.mv = new Command(mResources.chon, 11027);
        this.mu = new Command(mResources.chon, 11028);
        this.mt = new Command(mResources.chon, 11029);
        this.ms = new Command(GameCanvas.isTouch ? mResources.xem : "", 11030);
        new Command(mResources.el, 11021);
        this.jz = new Command(mResources.af, 11000);
        this.gf = new Command("Focus", 11001);
        this.mi = new Command(mResources.gx, 11032);
        this.mj = new Command(mResources.di, 11033);
        this.mn = new Command(mResources.boRa, 11034);
        this.mo = new Command(mResources.boRa, 14014);
        this.mp = new Command(mResources.boRa, 11035);
        this.mq = new Command(mResources.bs, 11036);
        this.mr = new Command(mResources.boRa, 11037);
        this.mk = new Command(mResources.boRa, 339);
        this.ml = new Command(mResources.boRa, 340);
        this.mm = new Command(mResources.boRa, 343);
        this.mh = new Command(mResources.boRa, 402);
        new Command("240", 110381);
        new Command("360", 1103911);
        new Command("Toàn Map", 110401);
        if (GameCanvas.isTouch && GameCanvas.isMinWidth240) {
            this.jz.x = gW - 135;
            this.jz.y = 6;
            this.jz.e = jf;
            this.gf.x = gW;
            this.gf.y = gH;
            if (GameCanvas.isMaxWidth320) {
                this.jz.x = gW / 2 - 38;
                this.jz.y = gH - 34;
            }
        }

        this.gf.e = GameCanvas.loadImage("/u/fc.png");
        super.left = this.jz;
        super.right = this.gf;
    }

    private void ax() {
        if (!GameCanvas.isTouch || GameCanvas.isTouch && GameCanvas.width < 320 || isPaintInfoMe && indexMenu > 0 || id && indexMenu == 0) {
            cg = false;
        }

        gp = false;
        if (z()) {
            this.ab();
            super.right = this.mw;
        } else {
            this.showButtonIndexMenu();
        }

    }

    private void ay() {
        if (gk > 0 && gk <= 4 || GameCanvas.isTouch) {
            GameCanvas.ak.a(mResources.ka, this.ka, 1);
        }

    }

    private void az() {
        if (gk > 0 && gk <= 4) {
            GameCanvas.ak.a(mResources.ka, this.kb, 1);
        }

    }

    private void ba() {
        if (cf) {
            GameCanvas.ak.a(mResources.ei, this.kc, 0);
        }

    }

    private void showMenuIndex() {
        isPaintInfoMe = true;
        b(175, 200);
        this.showButtonIndexMenu();
        if (indexMenu == 3 && currentCharViewInfo.charID == Char.getMyChar().charID) {
            Service.getInstance().viewInfo(currentCharViewInfo.charName);
        }

        if (indexMenu == 5) {
            this.js = new int[5];
            this.jt = new int[5];
            leftMargin = popupX + 5;
            nw = popupY + 35;
            this.js[0] = leftMargin + 5;
            this.jt[0] = nw + 35;
            this.js[1] = leftMargin + 5;
            this.jt[1] = nw + 70;
            this.js[2] = leftMargin + 131;
            this.jt[2] = nw + 35;
            this.js[3] = leftMargin + 131;
            this.jt[3] = nw + 70;
            this.js[4] = this.js[0] + wItem + 7;
            this.jt[4] = this.jt[0] - 5;
        }
        if (indexMenu == 6) {
            this.js = new int[5];
            this.jt = new int[5];
            leftMargin = popupX + 5;
            nw = popupY + 35;
            this.js[0] = leftMargin + 5;
            this.jt[0] = nw + 35;
            this.js[1] = leftMargin + 5;
            this.jt[1] = nw + 70;
            this.js[2] = leftMargin + 131;
            this.jt[2] = nw + 35;
            this.js[3] = leftMargin + 131;
            this.jt[3] = nw + 70;
            this.js[4] = this.js[0] + wItem + 7;
            this.jt[4] = this.jt[0] - 5;
        }
        super.right = new Command(mResources.el, 11060);
    }

    private void bc() {
        scrMain.a();
        gn.a();
        gp = false;
        this.ga = new Command(mResources.nameMenuIndex[0], 1100011);
        this.gb = new Command(mResources.nameMenuIndex[1], 1100012);
        this.gc = new Command(mResources.nameMenuIndex[2], 1100013);
        this.ge = new Command(mResources.nameMenuIndex[3], 1100014);
        this.gd = new Command(mResources.nameMenuIndex[4], 1100015);
        MyVector var1;
        (var1 = new MyVector()).addElement(this.ga);
        var1.addElement(this.gb);
        var1.addElement(this.gc);
        var1.addElement(this.ge);
        var1.addElement(this.gd);
        var1.addElement(new Command(mResources.trangBi2, 1100018));
        var1.addElement(new Command(mResources.nameMenuIndex[5], 1100016));
        var1.addElement(new Command(mResources.nameMenuIndex[6], 1100017));

        GameCanvas.menu.showMenu(var1);
    }

    private static void bd() {
        MyVector var0 = new MyVector();
        if (Code.auto != null) {
            var0.addElement(new Command("Tắt Auto", 1100073));
        } else {
            var0.addElement(new Command("Tàn sát", 1100069));
            if (TileMap.mapID == 1 || TileMap.mapID == 27 || TileMap.mapID == 72) {
                var0.addElement(new Command("Auto NVHN", 1100074));
                var0.addElement(new Command("ADV", 1100099));
                var0.addElement(new Command("Auto Tà thú ", 1100075));
            }
        }

        var0.addElement(new Command("Lật hình", 11000800));
        var0.addElement(new Command("NPC", 1100071));
        var0.addElement(new Command("Auto NPC", 11000814));
        var0.addElement(new Command("DS PK", 1100093));
        var0.addElement(new Command("DS HS", 1100094));
        var0.addElement(new Command("Item Sell", 1100091));
        var0.addElement(new Command("Item Nhặt", 1100076));
        var0.addElement(new Command("Item Del", 11000761));
        var0.addElement(new Command("Item Throw", 11000762));
        var0.addElement(new Command(Code.isHutVP ? "Hút VP" : "Nhặt Xa", 1100080));
        var0.addElement(new Command(Code.khoangCachNhat > 0 ? "KC Nhặt: " + Code.khoangCachNhat : "Nhặt Full", 1100081));
        var0.addElement(new Command(Code.m > 0 ? "KCTS: " + Code.m : "TS Full", 1100082));
        var0.addElement(new Command("Đánh CVT: " + (Code.attackChangePosition ? "Bật" : "Tắt"), 1100083));
        var0.addElement(new Command("Đánh CK: " + (Code.attackChangeZone ? "Bật" : "Tắt"), 1100089));
        var0.addElement(new Command("Giữ Lvl: " + (Code.keepLevel ? "Bật" : "Tắt"), 1100084));
        var0.addElement(new Command("SPGame: " + Code.speedGame, 1100087));
        var0.addElement(new Command(mResources.nw[7], 1100068));
        GameCanvas.menu.showMenu(var0);
    }

    private static void be() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.nx[0], 110002));
        var0.addElement(new Command(mResources.nx[1], 1100032));
        var0.addElement(new Command(mResources.nx[2], 1100033));
        var0.addElement(new Command(mResources.w, 1100034));
        var0.addElement(new Command(mResources.cauHinh, LoginScr.e(), 1004, (Object) null));
        GameCanvas.menu.showMenu(var0);
    }

    private static void bf() {
        MyVector var0 = new MyVector();
        if (Char.getMyChar().cLevel >= 3 && SelectServerScr.a()) {
            var0.addElement(new Command("Xác thực tài khoản", 1100181));
        }

        var0.addElement(new Command(mResources.titleUI[1], 110001));
        var0.addElement(new Command("MenuAuto", 110021));
        if (Code.showNsoChenMenu) {
            var0.addElement(new Command("NSO Chen", 11000912));
        }

        var0.addElement(new Command("Cài Đặt", 110008000));
        var0.addElement(new Command("Hướng Dẫn", 11000808));
        var0.addElement(new Command(mResources.titleUI[3], 110003));
        var0.addElement(new Command(mResources.titleUI[6], 110006));
        var0.addElement(new Command(mResources.titleUI[18], 110018));
        var0.addElement(new Command(mResources.titleUI[14], 110014));
        var0.addElement(new Command("Tự sát", 110020));
        var0.addElement(new Command(mResources.titleUI[16], 110016));
        var0.addElement(new Command(mResources.titleUI[4], 110004));
        GameCanvas.menu.showMenu(var0);
    }

    private void bg() {
        this.resetButton();
        gu = true;
        indexMenu = this.gg;
        this.iw = true;
        b(175, 200);
        super.right = this.mw;
        super.left = new Command(mResources.titleUI[2], 110002);
        super.center = new Command(mResources.fh, 110019);
    }

    private void bh() {
        this.resetButton();
        gw = true;
        this.iw = true;
        b(175, 200);
        super.right = this.mw;
        Service.getInstance().d();
        this.r();
    }

    private void bi() {
        this.resetButton();
        if (this.de > 0) {
            indexRow = Char.d(this.de);
        } else {
            indexRow = 0;
            this.de = -1;
        }

        ib = true;
        this.iw = true;
        b(175, 200);
        super.right = this.mw;
    }

    private void bj() {
        this.resetButton();
        gv = true;
        this.iw = true;
        b(175, 200);
        super.right = this.mw;
        this.s();
    }

    public final void p() {
        this.resetButton();
        gt = true;
        this.iw = true;
        b(175, 200);
        super.right = this.mw;
        super.left = super.center = null;
        indexRow = 0;
    }

    public final void q() {
        this.resetButton();
        gx = true;
        this.iw = true;
        b(175, 200);
        super.right = this.mw;
        super.left = super.center = null;
        indexRow = 0;
    }

    private void bk() {
        this.resetButton();
        cf = true;
        this.iw = true;
        b(175, 200);
        super.right = this.mw;
        super.left = new Command(mResources.dk, 11044);
        super.center = null;
        indexRow = 0;
        Service.getInstance().u();
    }

    private void bl() {
        this.resetButton();
        gy = true;
        this.iw = true;
        b(175, 200);
        super.right = this.mw;
        super.left = new Command(mResources.dk, 14017);
        super.center = null;
        indexRow = 0;
        Service.getInstance().v();
    }

    public final void r() {
        if (gw) {
            super.left = super.center = null;
            super.left = new Command(mResources.af, 11045);
            Party var1;
            if (z.size() > 0 && indexRow >= 0 && indexRow < z.size() && (var1 = (Party) z.elementAt(indexRow)) != null && !Char.getMyChar().charName.equals(var1.d)) {
                super.center = new Command(mResources.chon, 11046);
            }
        }

    }

    public final void s() {
        if (gv) {
            super.left = super.center = null;
            indexRow = 0;
            if (vParty.size() == 0) {
                super.center = null;
                super.left = new Command(mResources.af, 11047);
                return;
            }

            Party var1;
            if ((var1 = (Party) vParty.firstElement()).a == Char.getMyChar().charID) {
                super.left = new Command(mResources.nn, 11070, var1);
                return;
            }

            super.left = new Command(mResources.nm, 11071);
        }

    }

    private static void bm() {
        if (TileMap.typeMap != 1) {
            MapScr.a().update();
        }

    }

    public final void showZoneDialog(Message var1) {
        InfoDlg.d();

        try {
            this.zones = new int[var1.reader().readByte()];
            this.ir = new int[this.zones.length];

            for (int var2 = 0; var2 < this.zones.length; ++var2) {
                this.zones[var2] = var1.reader().readByte();
                this.ir[var2] = var1.reader().readByte();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        if (Code.auto instanceof AutoBossSchedule && ((AutoBossSchedule) Code.auto).receiveZoneCount(this.zones.length)) {
            return;
        }

        showZoneDialog = true;
        indexSelect = TileMap.zoneID;
        b(175, 200);
        super.left = new Command(mResources.chon, 11067);
        super.center = new Command("", 11067);
        super.right = this.mw;
    }

    public final void t() {
        try {
            this.resetButton();
            this.dx = "";
            this.cz = 0;
            this.cz = this.da = this.db = this.dc = 0;
            ci = true;
            arrItemTradeMe = new Item[12];
            arrItemTradeOrder = new Item[12];
            indexMenu = 0;
            b(175, 200);
            super.right = this.mw;
        } catch (Exception var2) {
        }

    }

    public static final void a(boolean var0) {
        gW = GameCanvas.width;
        if (GameCanvas.isTouch && (!GameCanvas.isTouch || GameCanvas.isMinWidth240)) {
            g = 8;
        } else {
            g = 36;
            if (GameCanvas.isTouch) {
                g += 3;
            }
        }

        gH = GameCanvas.height - g - 20;
        if (var0) {
            gH = GameCanvas.height;
        }

        if (GameCanvas.isTouch && GameCanvas.isMinWidth240) {
            gH = GameCanvas.height;
        }

        if (GameCanvas.height == 160) {
            gH = 150;
        }

        ls = gW;
        if (GameCanvas.width > 176) {
            ls -= 50;
        }

        lr = GameCanvas.height - Paint.f - g;
        int var10000 = GameCanvas.height;
        d = gW >> 1;
        f = gH >> 1;
        e = 2 * gH / 3;
        h = gW / 6;
        fs = gW / TileMap.size + 2;
        ft = gH / TileMap.size + 2;
        if (gW % 24 != 0) {
            ++fs;
        }

        fy = (TileMap.a - 1) * TileMap.size - gW;
        fz = (TileMap.b - 1) * TileMap.size - gH;
        if (GameCanvas.isTouch && GameCanvas.isMinWidth240) {
            fz += 60;
        }

        cmx = k = Char.getMyChar().cx - d + h * Char.getMyChar().cDir;
        cmy = l = Char.getMyChar().cy - e;
        if (cmx < 24) {
            cmx = 24;
        }

        if (cmx > fy) {
            cmx = fy;
        }

        if (cmy < 0) {
            cmy = 0;
        }

        if (cmy > fz) {
            cmy = fz;
        }

        if ((m = cmx / TileMap.size - 1) < 0) {
            m = 0;
        }

        n = cmy / TileMap.size;
        o = m + fs;
        p = n + ft;
        if (n < 0) {
            n = 0;
        }

        if (p > TileMap.b - 1) {
            p = TileMap.b - 1;
        }

        if ((TileMap.ad = o - m << 2) > TileMap.a) {
            TileMap.ad = TileMap.a;
        }

        if ((TileMap.ae = p - n << 2) > TileMap.b) {
            TileMap.ae = TileMap.b;
        }

        if ((TileMap.z = (Char.getMyChar().cx - 2 * gW) / TileMap.size) < 0) {
            TileMap.z = 0;
        }

        if ((TileMap.aa = TileMap.z + TileMap.ad) > TileMap.a) {
            TileMap.aa = TileMap.a;
        }

        if ((TileMap.ab = (Char.getMyChar().cy - 2 * gH) / TileMap.size) < 0) {
            TileMap.ab = 0;
        }

        if ((TileMap.ac = TileMap.ab + TileMap.ae) > TileMap.b) {
            TileMap.ac = TileMap.b;
        }

        ChatTextField.a().c = a;
        ChatTextField.a().a.b = GameCanvas.height - 35 - ChatTextField.a().a.height;
        if (GameCanvas.isTouch && (!GameCanvas.isTouch || GameCanvas.isMinWidth240)) {
            TileMap.a(GameCanvas.width - 60, 0, 60, 42);
        } else {
            TileMap.a(GameCanvas.width - 51, lr - 4, 50, 40);
        }

        if (GameCanvas.isTouch) {
            ki = gH - 88;
            kl = gW - 100;
            km = 2;
            if (GameCanvas.isMaxWidth320) {
                kl = gW / 2 - 2;
                km = ki + 50;
            }

            kj = 1;
            kk = ki + 50;
            kn = 42;
            ko = ki + 50;
            kp = gW - 50;
            kq = ki + 35;
            kr = 22;
            ks = ki + 19;
            kt = gW - 74;
            ku = ki + 13;
            kv = gW - 85;
            kw = ki + 50;
            kx = gW - 37;
            ky = ki - 1;
            if (GameCanvas.width >= 450) {
                ks -= 15;
                kr += 28;
                kn += 45;
                kj += 10;
                ky -= 12;
                ku -= 7;
                kp -= 18;
                kx -= 10;
                kt -= 17;
                kv -= 24;
            } else if (GameCanvas.width >= 360) {
                ks -= 5;
                kr += 6;
                kn += 12;
                ky -= 2;
                ku -= 2;
                kt -= 2;
                kv -= 2;
            }
        }

        dxTableSkill = new int[arrSkill.length];
        dyTableSkill = new int[arrSkill.length];
        int var1;
        if (GameCanvas.isTouch) {
            if (GameCanvas.isMaxWidth320) {
                marginLeftTableSkill = 2;
                marginTopTableSkill = 55;
                gapTableSkill = 5;

                for (var1 = 0; var1 < dxTableSkill.length; ++var1) {
                    dxTableSkill[var1] = var1 * (25 + gapTableSkill);
                    dyTableSkill[var1] = marginTopTableSkill;
                }
            } else {
                if (GameCanvas.width <= 320) {
                    marginLeftTableSkill = d - arrSkill.length * 25 / 2 - 15;
                } else {
                    marginLeftTableSkill = d - arrSkill.length * 25 / 2;
                }

                marginTopTableSkill = ki + 58;
                gapTableSkill = 5;

                for (var1 = 0; var1 < dxTableSkill.length; ++var1) {
                    dxTableSkill[var1] = var1 * (25 + gapTableSkill);
                    dyTableSkill[var1] = marginTopTableSkill;
                }
            }
        } else {
            marginLeftTableSkill = 0;

            for (var1 = 0; var1 < dyTableSkill.length; ++var1) {
                dxTableSkill[var1] = 2;
                dyTableSkill[var1] = 2 + var1 * 25;
            }
        }

    }

    private static boolean bn() {
        if (Char.getMyChar().selectSkill != null && Char.getMyChar().cMP < Char.getMyChar().selectSkill.manaUse) {
            InfoMe.a(mResources.ji);
            return false;
        } else if (Char.getMyChar().selectSkill != null && (Char.getMyChar().selectSkill.template.maxPoint <= 0 || Char.getMyChar().selectSkill.point != 0)) {
            if (Char.getMyChar().arrItemBody[1] == null) {
                GameCanvas.setText(mResources.mx);
                return false;
            } else {
                return true;
            }
        } else {
            GameCanvas.setText(mResources.mw);
            return false;
        }
    }

    public final void resetButton() {
        if (Char.getMyChar().arrItemBag != null) {
            int var10001;
            int var1;
            if ((hy || ih) && arrItemUpPeal != null) {
                for (var1 = 0; var1 < arrItemUpPeal.length; ++var1) {
                    if (arrItemUpPeal[var1] != null) {
                        var10001 = arrItemUpPeal[var1].indexUI;
                        Char.getMyChar().arrItemBag[var10001] = arrItemUpPeal[var1];
                        arrItemUpPeal[var1] = null;
                    }
                }
            }

            if (hv) {
                if (itemUpGrade != null) {
                    Char.getMyChar().arrItemBag[itemUpGrade.indexUI] = itemUpGrade;
                    itemUpGrade = null;
                }

                if (arrItemUpGrade != null) {
                    for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                        if (arrItemUpGrade[var1] != null) {
                            var10001 = arrItemUpGrade[var1].indexUI;
                            Char.getMyChar().arrItemBag[var10001] = arrItemUpGrade[var1];
                            arrItemUpGrade[var1] = null;
                        }
                    }
                }
            }

            if (ik) {
                if (itemUpGrade != null) {
                    Char.getMyChar().arrItemBag[itemUpGrade.indexUI] = itemUpGrade;
                    itemUpGrade = null;
                }

                if (itemSplit != null) {
                    Char.getMyChar().arrItemBag[itemSplit.indexUI] = itemSplit;
                    itemSplit = null;
                }

                if (arrItemUpGrade != null) {
                    for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                        if (arrItemUpGrade[var1] != null) {
                            var10001 = arrItemUpGrade[var1].indexUI;
                            Char.getMyChar().arrItemBag[var10001] = arrItemUpGrade[var1];
                            arrItemUpGrade[var1] = null;
                        }
                    }
                }
            }

            if (io && arrItemUpGrade != null) {
                for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                    if (arrItemUpGrade[var1] != null) {
                        var10001 = arrItemUpGrade[var1].indexUI;
                        Char.getMyChar().arrItemBag[var10001] = arrItemUpGrade[var1];
                        arrItemUpGrade[var1] = null;
                    }
                }
            }

            if (cd && itemSell != null) {
                Char.getMyChar().arrItemBag[itemSell.indexUI] = itemSell;
                itemSell = null;
            }

            if (hw && arrItemConvert != null) {
                for (var1 = 0; var1 < arrItemConvert.length; ++var1) {
                    if (arrItemConvert[var1] != null) {
                        var10001 = arrItemConvert[var1].indexUI;
                        Char.getMyChar().arrItemBag[var10001] = arrItemConvert[var1];
                        arrItemConvert[var1] = null;
                    }
                }
            }

            if (ia || cl || ii || ij) {
                if (itemSplit != null) {
                    Char.getMyChar().arrItemBag[itemSplit.indexUI] = itemSplit;
                    itemSplit = null;
                }

                if (arrItemSplit != null) {
                    for (var1 = 0; var1 < arrItemSplit.length; ++var1) {
                        if (arrItemSplit[var1] != null) {
                            if (ii || cl || ij) {
                                var10001 = arrItemSplit[var1].indexUI;
                                Char.getMyChar().arrItemBag[var10001] = arrItemSplit[var1];
                            }

                            arrItemSplit[var1] = null;
                        }
                    }
                }
            }

            if (io && arrItemSplit != null) {
                for (var1 = 0; var1 < arrItemSplit.length; ++var1) {
                    if (arrItemSplit[var1] != null) {
                        var10001 = arrItemSplit[var1].indexUI;
                        Char.getMyChar().arrItemBag[var10001] = arrItemSplit[var1];
                        arrItemSplit[var1] = null;
                    }
                }
            }

            if (ci) {
                InfoDlg.d();
                if (this.db > 0) {
                    Char var10000 = Char.getMyChar();
                    var10000.xu += this.db;
                }

                if (arrItemTradeMe != null) {
                    for (var1 = 0; var1 < arrItemTradeMe.length; ++var1) {
                        if (arrItemTradeMe[var1] != null) {
                            var10001 = arrItemTradeMe[var1].indexUI;
                            Char.getMyChar().arrItemBag[var10001] = arrItemTradeMe[var1];
                            arrItemTradeMe[var1] = null;
                        }
                    }
                }

                if (arrItemTradeOrder != null) {
                    for (var1 = 0; var1 < arrItemTradeOrder.length; ++var1) {
                        arrItemTradeOrder[var1] = null;
                    }
                }
            }

            if ((il || im) && itemSplit != null) {
                Char.getMyChar().arrItemBag[itemSplit.indexUI] = itemSplit;
                itemSplit = null;
            }
        }

        if (showAutoPanel || showPickItem || showDelItem || showItemThrow || showItemGather || showAutoUseItem) {
            Char.saveAuto();
        }

        if (ci) {
            Service.getInstance().i();
        }

        GameCanvas.menu.showMenu = false;
        ChatTextField var2;
        (var2 = ChatTextField.a()).a.a("");
        var2.isShow = false;
        ChatTextField.a().f = null;
        if (!GameCanvas.isTouch) {
            gz = false;
        }

        cc = false;
        hx = false;
        this.iw = false;
        showZoneDialog = false;
        showAutoPanel = false;
        isPaintInfoMe = false;
        cg = false;
        gu = false;
        gv = false;
        ck = false;
        id = false;
        ifa = false;
        ie = false;
        ib = false;
        gw = false;
        cf = false;
        ig = false;
        gx = false;
        gt = false;
        gy = false;
        ce = false;
        in = false;
        this.cz = 0;
        ha = false;
        hb = false;
        hc = false;
        hd = false;
        he = false;
        hf = false;
        hg = false;
        hh = false;
        hi = false;
        hj = false;
        hk = false;
        hl = false;
        hm = false;
        hn = false;
        ho = false;
        hp = false;
        hq = false;
        hr = false;
        hs = false;
        ht = false;
        hu = false;
        hv = false;
        cd = false;
        hw = false;
        cl = false;
        ii = false;
        ia = false;
        ci = false;
        ih = false;
        hy = false;
        showBox = false;
        showPickItem = false;
        showDelItem = false;
        showItemThrow = false;
        showItemGather = false;
        showAutoUseItem = false;
        ik = false;
        ij = false;
        io = false;
        il = false;
        im = false;
        indexMenu = 0;
        indexSelect = 0;
        this.iv = -1;
        indexRow = -1;
        totalRowSetting = 0;
        gk = 0;
        this.cz = this.da = 0;
        super.left = this.jz;
        super.right = this.gf;
        this.js = this.jt = null;
        super.center = null;
        if (Char.getMyChar().cHP <= 0 || Char.getMyChar().statusMe == 14 || Char.getMyChar().statusMe == 5) {
            if (GameCanvas.isMaxWidth320) {
                this.fa.caption = "";
            }

            super.center = this.fa;
        }

        scrMain.a();
    }

    public final void a(int var1) {
        if (this.iz != null && this.iz.isFocus) {
            this.iz.a(var1);
        }

        super.a(var1);
    }

    public final void b() {
        if (!GameCanvas.menu.showMenu && !InfoDlg.f) {
            ensureTouchUiReady();
            int var1;
            boolean var5;
            if (GameCanvas.isTouch && !ChatTextField.a().isShow && !GameCanvas.menu.showMenu) {
                GameScr var3 = this;
                int var4 = -1;
                if (GameCanvas.isPointerClick) {
                    for (var1 = 0; var1 < var3.jq.length; ++var1) {
                        if (GameCanvas.b(var3.jq[var1], var3.jr[var1], 100, 12) && GameCanvas.isPointerJustRelease) {
                            var4 = var1;
                            break;
                        }
                    }
                }

                if (var4 != -1 && !cf() && !z() && !ch()) {
                    if (var4 != 0) {
                        if (ChatManager.f) {
                            ChatManager.getInstance().a(1);
                        } else if (ChatManager.e) {
                            ChatManager.getInstance().a(3);
                        }

                        this.fj();
                        this.jq[1] = this.jr[1] = -1;
                    } else if (ChatManager.getInstance().g.size() > 0) {
                        ChatManager var2 = ChatManager.getInstance();
                        var1 = 3;

                        int var11;
                        label1500:
                        while (true) {
                            if (var1 >= var2.a.size()) {
                                var11 = -1;
                                break;
                            }

                            ChatTab var9 = (ChatTab) var2.a.elementAt(var1);

                            for (var4 = 0; var4 < var2.g.size(); ++var4) {
                                if (var9.b.equals(var2.g.elementAt(var4).toString())) {
                                    var11 = var1;
                                    break label1500;
                                }
                            }

                            ++var1;
                        }

                        ChatManager.getInstance().a(var11);
                        this.fj();
                        this.jq[0] = this.jr[0] = -1;
                    }
                }

                var5 = false;
                mScreen.fr = -1;
                if (checkQuickStopAutoButton()) {
                    var5 = true;
                }
                if (GameCanvas.b(TileMap.posMiniMapX, TileMap.posMiniMapY, TileMap.wMiniMap, TileMap.hMiniMap) && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                    bm();
                    var5 = true;
                }

                if (GameCanvas.isTouch && (!GameCanvas.menu.showMenu || !GameCanvas.isMaxWidth320) && GameCanvas.currentDialog == null && ChatPopup.currentMultilineChatPopup == null && !GameCanvas.menu.showMenu && !cf()) {
                    if (GameCanvas.b(kl, km, 34, 34)) {
                        mScreen.fr = 15;
                        if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                            ChatTextField.a().a(mResources.on[0]);
                            var5 = true;
                            GameCanvas.isPointerJustRelease = false;
                            GameCanvas.isPointerClick = false;
                        }
                    }

                    if (!this.cg()) {
                        if (!Char.getMyChar().isCaptcha) {
                            if (ff) {
                                if (GameCanvas.b(kr, ks, 34, 34)) {
                                    mScreen.fr = 3;
                                    GameCanvas.l[2] = true;
                                    this.bo();
                                    var5 = true;
                                } else if (GameCanvas.m) {
                                    GameCanvas.l[2] = false;
                                }

                                if (GameCanvas.b(kr - 30, ks, 30, 34)) {
                                    GameCanvas.l[1] = true;
                                    this.bo();
                                    var5 = true;
                                } else if (GameCanvas.m) {
                                    GameCanvas.l[1] = false;
                                }

                                if (GameCanvas.b(kr + 34, ks, 30, 34)) {
                                    GameCanvas.l[3] = true;
                                    this.bo();
                                    var5 = true;
                                } else if (GameCanvas.m) {
                                    GameCanvas.l[3] = false;
                                }

                                if (GameCanvas.b(kj, kk, 34, 34)) {
                                    mScreen.fr = 4;
                                    GameCanvas.l[4] = true;
                                    this.bo();
                                    var5 = true;
                                } else if (GameCanvas.m) {
                                    GameCanvas.l[4] = false;
                                }

                                if (GameCanvas.b(kn - 5, ko, 40, 34)) {
                                    mScreen.fr = 6;
                                    GameCanvas.l[6] = true;
                                    this.bo();
                                    var5 = true;
                                } else if (GameCanvas.m) {
                                    GameCanvas.l[6] = false;
                                }
                            } else {
                                qg.a();
                            }

                            if (GameCanvas.b(kp, kq, 54, 54)) {
                                GameCanvas.l[5] = true;
                                mScreen.fr = 5;
                                if (GameCanvas.isPointerJustRelease) {
                                    GameCanvas.keyPressedz[5] = true;
                                    var5 = true;
                                }
                            }
                        } else {
                            if (GameCanvas.c(kj, kk, 34, 34) && GameCanvas.isPointerJustRelease) {
                                this.e((byte) 0);
                                GameCanvas.l();
                            }

                            if (GameCanvas.c(kr, ks, 34, 34) && GameCanvas.isPointerJustRelease) {
                                this.e((byte) 1);
                                GameCanvas.l();
                            }

                            if (GameCanvas.c(kn - 5, ko, 40, 34) && GameCanvas.isPointerJustRelease) {
                                this.e((byte) 2);
                                GameCanvas.l();
                            }
                        }

                        if (Char.getMyChar().ctaskId > 1) {
                            if (GameCanvas.b(kv, kw, 34, 34)) {
                                mScreen.fr = 11;
                                if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                                    GameCanvas.keyPressedz[11] = true;
                                    var5 = true;
                                }
                            }

                            if (GameCanvas.b(kt, ku, 34, 34)) {
                                mScreen.fr = 10;
                                if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                                    GameCanvas.keyPressedz[10] = true;
                                    var5 = true;
                                }
                            }

                            if (GameCanvas.b(kx, ky, 34, 34)) {
                                mScreen.fr = 13;
                                if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                                    Char.getMyChar().x();
                                    var5 = true;
                                }
                            }
                        }

                        if (Char.getMyChar().vSkill.size() >= 2 && (GameCanvas.b(marginLeftTableSkill + dxTableSkill[0], dyTableSkill[0], arrSkill.length * 30, 30) || !GameCanvas.isMinWidth240 && GameCanvas.b(marginLeftTableSkill + dxTableSkill[0], dyTableSkill[0], 30, arrSkill.length * 25)) && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                            if (!GameCanvas.isMinWidth240) {
                                var4 = (GameCanvas.s - (marginTopTableSkill + dyTableSkill[0])) / 25;
                            } else {
                                var4 = (GameCanvas.r - (marginLeftTableSkill + dxTableSkill[0])) / 30;
                            }

                            this.kf = var4;
                            if (indexSelect < 0) {
                                indexSelect = 0;
                            }

                            if (this.kf > arrSkill.length - 1) {
                                this.kf = arrSkill.length - 1;
                            }

                            var5 = true;
                            Skill var6 = arrSkill[this.kf];
                            this.a(var6, false);
                            this.gh = true;
                        }

                        if (GameCanvas.isPointerJustRelease) {
                            GameCanvas.l[1] = false;
                            GameCanvas.l[2] = false;
                            GameCanvas.l[3] = false;
                            GameCanvas.l[4] = false;
                            GameCanvas.l[6] = false;
                        }

                        if (!var5 && !cf() && !z() && !ch() && GameCanvas.isPointerClick) {
                            var1 = 0;

                            label1448:
                            while (true) {
                                if (var1 >= vMobAttack.size()) {
                                    for (var1 = 0; var1 < ah.size(); ++var1) {
                                        Npc var8;
                                        if ((var8 = (Npc) ah.elementAt(var1)).u() && GameCanvas.a(var8.cx - var8.bi / 2, var8.cy - var8.bj, var8.bi, var8.bj) && GameCanvas.isPointerJustRelease) {
                                            Char.getMyChar().mobFocus = null;
                                            Char.getMyChar().y();
                                            Char.getMyChar().npcFocus = var8;
                                            Char.getMyChar().charFocus = null;
                                            Char.getMyChar().itemFocus = null;
                                            Char.ge = true;
                                            AutoTeleTarget.selectNpc(var8);
                                            break label1448;
                                        }
                                    }

                                    for (var1 = 0; var1 < vCharInMap.size(); ++var1) {
                                        Char var10;
                                        if ((var10 = (Char) vCharInMap.elementAt(var1)).u() && !var10.ae() && GameCanvas.a(var10.cx - var10.bi / 2, var10.cy - var10.bj, var10.bi, var10.bj) && GameCanvas.isPointerJustRelease) {
                                            Char.getMyChar().mobFocus = null;
                                            Char.getMyChar().y();
                                            Char.getMyChar().charFocus = var10;
                                            Char.getMyChar().itemFocus = null;
                                            Char.ge = true;
                                            AutoTeleTarget.selectChar(var10);
                                            break label1448;
                                        }
                                    }

                                    var1 = 0;

                                    while (true) {
                                        if (var1 >= vItemMap.size()) {
                                            break label1448;
                                        }

                                        ItemMap var12;
                                        if (GameCanvas.a((var12 = (ItemMap) vItemMap.elementAt(var1)).x - 12, var12.y - 24, 24, 24) && GameCanvas.isPointerJustRelease) {
                                            Char.getMyChar().mobFocus = null;
                                            Char.getMyChar().y();
                                            Char.getMyChar().charFocus = null;
                                            Char.getMyChar().itemFocus = var12;
                                            Char.ge = true;
                                            AutoTeleTarget.selectItem(var12);
                                            break label1448;
                                        }

                                        ++var1;
                                    }
                                }

                                Mob var7;
                                if ((var7 = (Mob) vMobAttack.elementAt(var1)).c() && GameCanvas.a(var7.curX - var7.k / 2, var7.curY - var7.l, var7.k, var7.l) && GameCanvas.isPointerJustRelease) {
                                    Char.getMyChar().mobFocus = var7;
                                    Char.getMyChar().y();
                                    Char.getMyChar().charFocus = null;
                                    Char.getMyChar().itemFocus = null;
                                    Char.ge = true;
                                    AutoTeleTarget.selectMob(var7);
                                    break;
                                }

                                ++var1;
                            }
                        }
                    }
                }
            }

            if (TileMap.mapID != 130 && !bp()) {
                label1619:
                {
                    long var14 = System.currentTimeMillis();
                    if (GameCanvas.keyPressedz[2] || GameCanvas.keyPressedz[4] || GameCanvas.keyPressedz[6] || GameCanvas.keyPressedz[1] || GameCanvas.keyPressedz[3]) {
                        es = 0;
                        if (this.et) {
                            Char.getMyChar().fm = false;
                            this.et = false;
                        }
                    }

                    if (GameCanvas.keyPressedz[5] && !cf()) {
                        if (es == 0) {
                            if (var14 - this.kd < 800L && (Char.getMyChar().selectSkill == null || Char.getMyChar().cMP >= Char.getMyChar().selectSkill.manaUse) && Char.getMyChar().selectSkill != null && (Char.getMyChar().selectSkill.template.maxPoint <= 0 || Char.getMyChar().selectSkill.point != 0) && Char.getMyChar().arrItemBody[1] != null && Char.getMyChar().mobFocus != null) {
                                es = 10;
                                GameCanvas.keyPressedz[5] = false;
                            }
                        } else {
                            if (!this.et && Char.getMyChar().statusMe != 14) {
                                this.et = !this.et;
                                Char.getMyChar().fm = !Char.getMyChar().fm;
                                this.kd = var14;
                                break label1619;
                            }

                            es = 0;
                            if (this.et) {
                                Char.getMyChar().fm = false;
                                this.et = false;
                            }

                            GameCanvas.keyPressedz[4] = GameCanvas.keyPressedz[6] = false;
                        }

                        this.kd = var14;
                    }

                    if (GameCanvas.gameTick % 10 == 0 && es > 0 && (Char.getMyChar().mobFocus != null || Char.getMyChar().itemFocus != null)) {
                        this.b(true);
                    }

                    if (es > 1) {
                        --es;
                    }
                }
            }

            if (GameCanvas.isTouch) {
                if (GameCanvas.m && !GameCanvas.isPointerJustRelease && GameCanvas.b(kx, ky, 34, 34) && !ib && GameCanvas.isPointerClick && GameCanvas.j()) {
                    this.bi();
                }
            } else if (GameCanvas.l[13] && !ib && GameCanvas.j()) {
                this.bi();
            }

            if (ChatPopup.currentMultilineChatPopup != null) {
                Command var15 = ChatPopup.currentMultilineChatPopup.a;
                if ((GameCanvas.keyPressedz[5] || mScreen.a(var15)) && var15 != null) {
                    GameCanvas.isPointerJustRelease = false;
                    GameCanvas.keyPressedz[5] = false;
                    mScreen.fr = -1;
                    if (var15 != null) {
                        var15.a();
                    }
                }
            } else if (!ChatTextField.a().isShow) {
                if ((GameCanvas.keyPressedz[12] || mScreen.a(GameCanvas.mScreen.left)) && super.left != null) {
                    GameCanvas.isPointerJustRelease = false;
                    GameCanvas.isPointerClick = false;
                    GameCanvas.keyPressedz[12] = false;
                    mScreen.fr = -1;
                    if (super.left != null) {
                        super.left.a();
                    }
                }

                if ((GameCanvas.keyPressedz[13] || mScreen.a(GameCanvas.mScreen.right)) && super.right != null) {
                    GameCanvas.isPointerJustRelease = false;
                    GameCanvas.isPointerClick = false;
                    GameCanvas.keyPressedz[13] = false;
                    mScreen.fr = -1;
                    if (super.right != null) {
                        super.right.a();
                    }
                }

                if ((GameCanvas.keyPressedz[5] || mScreen.a(GameCanvas.mScreen.center)) && super.center != null) {
                    GameCanvas.isPointerJustRelease = false;
                    GameCanvas.keyPressedz[5] = false;
                    mScreen.fr = -1;
                    if (super.center != null) {
                        super.center.a();
                    }
                }
            } else {
                if (ChatTextField.a().d != null && (GameCanvas.keyPressedz[12] || mScreen.a(ChatTextField.a().d)) && ChatTextField.a().d != null) {
                    ChatTextField.a().d.a();
                }

                if (ChatTextField.a().e != null && (GameCanvas.keyPressedz[13] || mScreen.a(ChatTextField.a().e)) && ChatTextField.a().e != null) {
                    ChatTextField.a().e.a();
                }

                if (ChatTextField.a().f != null && (GameCanvas.keyPressedz[5] || mScreen.a(ChatTextField.a().f)) && ChatTextField.a().f != null) {
                    ChatTextField.a().f.a();
                }
            }

            ScrollResult var16;
            if (showZoneDialog && GameCanvas.currentDialog == null) {
                var5 = false;
                if (GameCanvas.keyPressedz[4]) {
                    if (--indexSelect < 0) {
                        indexSelect = this.zones.length - 1;
                    }

                    var5 = true;
                } else if (GameCanvas.keyPressedz[6]) {
                    if (++indexSelect >= this.zones.length) {
                        indexSelect = 0;
                    }

                    var5 = true;
                } else if (GameCanvas.keyPressedz[8]) {
                    if (indexSelect + this.iq <= this.zones.length - 1) {
                        indexSelect += this.iq;
                    }

                    var5 = true;
                } else if (GameCanvas.keyPressedz[2]) {
                    if (indexSelect - this.iq >= 0) {
                        indexSelect -= this.iq;
                    }

                    var5 = true;
                }

                if (var5) {
                    scrMain.a(indexSelect / ob * scrMain.h);
                    GameCanvas.l();
                    GameCanvas.k();
                }

                if (GameCanvas.isTouch && ((var16 = scrMain.b()).a || var16.c)) {
                    indexSelect = var16.b;
                }
            }

            ScrollResult var18;
            if (gt || gv || gw || cf || gy || ib || gx || ig) {
                if (ig) {
                    if (ig && ((var18 = scrMain.b()).a || var18.c)) {
                        indexSelect = var18.b;
                        if (var18.b >= arrItemStands.length) {
                            indexSelect = -1;
                        }

                        if (indexSelect >= 0) {
                            gk = 1;
                        }

                        this.ab();
                    }
                } else {
                    label1613:
                    {
                        if (gv) {
                            if (vParty.size() == 0) {
                                break label1613;
                            }

                            if (GameCanvas.keyPressedz[8]) {
                                if (++indexRow >= vParty.size()) {
                                    indexRow = vParty.size() - 1;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (--indexRow < 0) {
                                    indexRow = 0;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            }

                            this.ce();
                        } else if (gw) {
                            if (GameCanvas.keyPressedz[8]) {
                                if (++indexRow >= z.size()) {
                                    indexRow = z.size() - 1;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (--indexRow < 0) {
                                    indexRow = 0;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            }

                            this.r();
                        } else if (cf) {
                            if (GameCanvas.keyPressedz[8]) {
                                if (++indexRow >= totalRowSetting) {
                                    indexRow = 0;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (--indexRow < 0) {
                                    indexRow = totalRowSetting - 1;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            }

                            this.cd();
                        } else if (gy) {
                            if (GameCanvas.keyPressedz[8]) {
                                if (++indexRow >= totalRowSetting) {
                                    indexRow = 0;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (--indexRow < 0) {
                                    indexRow = totalRowSetting - 1;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            }

                            this.cc();
                        } else if (ib) {
                            if (GameCanvas.keyPressedz[8]) {
                                if (++indexRow >= vCharInMap.size()) {
                                    indexRow = vCharInMap.size() - 1;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (--indexRow < 0) {
                                    indexRow = 0;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            }

                            if (this.de > 0 && !GameCanvas.isTouch) {
                                scrMain.a(indexRow * scrMain.h);
                            }

                            this.bz();
                        } else if (gx) {
                            if (GameCanvas.keyPressedz[8]) {
                                if (++indexRow >= ab.size()) {
                                    indexRow = ab.size() - 1;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (--indexRow < 0) {
                                    indexRow = 0;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            }

                            this.cb();
                        } else if (gt) {
                            if (GameCanvas.keyPressedz[8]) {
                                if (++indexRow >= ab.size()) {
                                    indexRow = ab.size() - 1;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (--indexRow < 0) {
                                    indexRow = 0;
                                }

                                scrMain.a(indexRow * scrMain.h);
                            }

                            this.ca();
                        }

                        if (GameCanvas.isTouch && GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu) {
                            MyVector var13 = null;
                            if (gv) {
                                var13 = vParty;
                            } else if (cf) {
                                var13 = aa;
                            } else if (gy) {
                                var13 = ad;
                            } else if (gw) {
                                var13 = z;
                            } else if (ib) {
                                var13 = vCharInMap;
                            } else if (gx) {
                                var13 = ab;
                            } else if (gt) {
                                var13 = ab;
                            }

                            if ((var16 = scrMain.b()).a || var16.c) {
                                indexRow = var16.b;
                                if (var16.b >= var13.size()) {
                                    indexRow = -1;
                                }

                                if (gv) {
                                    this.ce();
                                } else if (cf) {
                                    this.cd();
                                } else if (gy) {
                                    this.cc();
                                } else if (ib) {
                                    this.bz();
                                } else if (gx) {
                                    this.cb();
                                } else if (gt) {
                                    this.ca();
                                }
                            }
                        }

                        GameCanvas.l();
                        GameCanvas.k();
                    }
                }
            }

            this.aa();
            if (isPaintInfoMe && indexMenu != -1 && GameCanvas.currentDialog == null) {
                if (gk == 0) {
                    super.left = super.center = null;
                    if (indexMenu == 0) {
                        super.left = new Command(mResources.sapXep, 110221);
                    }

                    if (GameCanvas.keyPressedz[8]) {
                        gk = 1;
                        indexSelect = 0;
                        indexRow = 0;
                        scrMain.a();
                        gn.a();
                    }

                    if (GameCanvas.keyPressedz[4]) {
                        indexSelect = 0;
                        indexRow = -1;
                        --indexMenu;
                        scrMain.a();
                        gn.a();
                        if (currentCharViewInfo.charID != Char.getMyChar().charID) {
                            if (indexMenu < 3) {
                                indexMenu = 5;
                            }
                        } else if (indexMenu < 0) {
                            indexMenu = mResources.nameMenuIndex.length - 1;
                        }

                        this.showMenuIndex();
                    }

                    if (GameCanvas.keyPressedz[6]) {
                        indexSelect = 0;
                        indexRow = -1;
                        ++indexMenu;
                        scrMain.a();
                        gn.a();
                        if (currentCharViewInfo.charID != Char.getMyChar().charID) {
                            if (indexMenu > 5) {
                                indexMenu = 3;
                            }
                        } else if (indexMenu > mResources.nameMenuIndex.length - 1) {
                            indexMenu = 0;
                        }

                        this.showMenuIndex();
                    }

                    this.showButtonIndexMenu();
                } else if (cg) {
                    if (GameCanvas.keyPressedz[2]) {
                        if (--indexRow < 0) {
                            indexRow = totalRowSetting - 1;
                        }

                        gn.a(indexRow * gn.h);
                    } else if (GameCanvas.keyPressedz[8]) {
                        if (++indexRow >= totalRowSetting) {
                            indexRow = 0;
                        }

                        gn.a(indexRow * gn.h);
                    }
                } else if (indexMenu == 0) {
                    if (GameCanvas.keyPressedz[4]) {
                        if (--indexSelect < 0) {
                            indexSelect = Char.getMyChar().arrItemBag.length - 1;
                        }

                        super.left = super.center = null;
                        this.showButtonIndexMenu();
                        scrMain.a(indexSelect / ob * scrMain.h);
                    } else if (GameCanvas.keyPressedz[6]) {
                        if (++indexSelect >= Char.getMyChar().arrItemBag.length) {
                            indexSelect = 0;
                        }

                        super.left = super.center = null;
                        this.showButtonIndexMenu();
                        scrMain.a(indexSelect / ob * scrMain.h);
                    } else if (GameCanvas.keyPressedz[8]) {
                        if (indexSelect + ob <= Char.getMyChar().arrItemBag.length - 1) {
                            indexSelect += ob;
                        }

                        super.left = super.center = null;
                        this.showButtonIndexMenu();
                        scrMain.a(indexSelect / ob * scrMain.h);
                    } else if (GameCanvas.keyPressedz[2]) {
                        if (indexSelect >= 0 && indexSelect < ob) {
                            gk = 0;
                            indexSelect = 0;
                        } else if (indexSelect - ob >= 0) {
                            indexSelect -= ob;
                        }

                        super.left = super.center = null;
                        this.showButtonIndexMenu();
                        scrMain.a(indexSelect / ob * scrMain.h);
                    }
                } else {
                    label1595:
                    {
                        if (indexMenu == 1) {
                            if (GameCanvas.keyPressedz[2]) {
                                if (gk == 1 && indexRow == -1) {
                                    --gk;
                                } else if (gk == 1 && indexRow >= 0) {
                                    --indexRow;
                                }

                                gn.a(indexRow * gn.h);
                                break label1595;
                            }

                            if (!GameCanvas.keyPressedz[8]) {
                                if (GameCanvas.keyPressedz[4]) {
                                    indexRow = -1;
                                    if (gk == 1 && --indexSelect < 0) {
                                        indexSelect = Char.getMyChar().nClass.skillTemplates.length - 1;
                                    }

                                    super.left = super.center = null;
                                    this.showButtonIndexMenu();
                                    scrMain.a(indexSelect * scrMain.h);
                                    gn.a();
                                    indexRow = 0;
                                } else if (GameCanvas.keyPressedz[6]) {
                                    indexRow = -1;
                                    if (gk == 1 && ++indexSelect >= Char.getMyChar().nClass.skillTemplates.length) {
                                        indexSelect = 0;
                                    }

                                    super.left = super.center = null;
                                    this.showButtonIndexMenu();
                                    scrMain.a(indexSelect * scrMain.h);
                                    gn.a();
                                    indexRow = 0;
                                }
                                break label1595;
                            }

                            if (gk == 0) {
                                ++gk;
                            } else if (gk == 1) {
                                if (++indexRow >= totalRowSetting) {
                                    indexRow = 0;
                                }

                                gn.a(indexRow * gn.h);
                            }

                            super.left = super.center = null;
                        } else if (indexMenu == 2) {
                            if (GameCanvas.keyPressedz[2]) {
                                --gk;
                                break label1595;
                            }

                            if (!GameCanvas.keyPressedz[8]) {
                                break label1595;
                            }

                            if (++gk >= 5) {
                                gk = 1;
                            }

                            super.left = super.center = null;
                        } else {
                            if (indexMenu == 3) {
                                if (indexRow < 0) {
                                    indexRow = 0;
                                }

                                if (GameCanvas.keyPressedz[2]) {
                                    if (indexRow == 0) {
                                        --gk;
                                        indexRow = -1;
                                    } else {
                                        --indexRow;
                                    }

                                    scrMain.a(indexRow * scrMain.h);
                                } else if (GameCanvas.keyPressedz[8]) {
                                    if (++indexRow >= totalRowSetting) {
                                        indexRow = 0;
                                    }

                                    scrMain.a(indexRow * scrMain.h);
                                }
                                break label1595;
                            }

                            if (indexMenu == 4) {
                                label1623:
                                {
                                    var1 = indexSelect;
                                    if (indexSelect != 11 && indexSelect != 12 && indexSelect != 13 && indexSelect != 14) {
                                        if (indexSelect == 9) {
                                            if (GameCanvas.keyPressedz[2]) {
                                                indexSelect -= 2;
                                                break label1623;
                                            }

                                            if (GameCanvas.keyPressedz[8]) {
                                                indexSelect = 15;
                                                break label1623;
                                            }

                                            if (!GameCanvas.keyPressedz[4]) {
                                                if (GameCanvas.keyPressedz[6]) {
                                                    ++indexSelect;
                                                }
                                                break label1623;
                                            }
                                        } else if (indexSelect == 10) {
                                            if (GameCanvas.keyPressedz[2]) {
                                                indexSelect -= 2;
                                                break label1623;
                                            }

                                            if (!GameCanvas.keyPressedz[4]) {
                                                if (GameCanvas.keyPressedz[6] || GameCanvas.keyPressedz[8]) {
                                                    ++indexSelect;
                                                }
                                                break label1623;
                                            }
                                        } else {
                                            if (indexSelect != 15) {
                                                if (GameCanvas.keyPressedz[2]) {
                                                    if (indexSelect <= 1) {
                                                        indexSelect = 0;
                                                        gk = 0;
                                                    } else {
                                                        indexSelect -= 2;
                                                    }
                                                } else if (GameCanvas.keyPressedz[8]) {
                                                    if ((indexSelect += 2) > 15) {
                                                        indexSelect = 0;
                                                    }
                                                } else if (GameCanvas.keyPressedz[4]) {
                                                    if (--indexSelect < 0) {
                                                        indexSelect = 15;
                                                    }
                                                } else if (GameCanvas.keyPressedz[6] && ++indexSelect > 11) {
                                                    indexSelect = 0;
                                                }
                                                break label1623;
                                            }

                                            if (GameCanvas.keyPressedz[2]) {
                                                indexSelect = 9;
                                                break label1623;
                                            }

                                            if (!GameCanvas.keyPressedz[4]) {
                                                if (GameCanvas.keyPressedz[8] || GameCanvas.keyPressedz[6]) {
                                                    indexSelect = 0;
                                                }
                                                break label1623;
                                            }
                                        }
                                    } else if (!GameCanvas.keyPressedz[2] && !GameCanvas.keyPressedz[4]) {
                                        if (GameCanvas.keyPressedz[6] || GameCanvas.keyPressedz[8]) {
                                            ++indexSelect;
                                        }
                                        break label1623;
                                    }

                                    --indexSelect;
                                }

                                if (var1 == indexSelect) {
                                    break label1595;
                                }

                                super.left = super.center = null;
                            } else if (indexMenu == 5) {
                                if (indexMenu != 5) {
                                    break label1595;
                                }

                                if (GameCanvas.keyPressedz[2]) {
                                    if (indexSelect == 4) {
                                        indexSelect = 0;
                                        --gk;
                                    } else if (--indexSelect < 0) {
                                        indexSelect = 0;
                                        --gk;
                                    }

                                    this.showButtonIndexMenu();
                                    break label1595;
                                }

                                if (GameCanvas.keyPressedz[4]) {
                                    if (indexSelect >= 2 && indexSelect != 4) {
                                        indexSelect = 4;
                                    } else {
                                        indexSelect = 0;
                                    }

                                    this.showButtonIndexMenu();
                                    break label1595;
                                }

                                if (GameCanvas.keyPressedz[6]) {
                                    if (indexSelect < 2) {
                                        indexSelect = 4;
                                    } else {
                                        indexSelect = 2;
                                    }

                                    this.showButtonIndexMenu();
                                    break label1595;
                                }

                                if (!GameCanvas.keyPressedz[8]) {
                                    break label1595;
                                }

                                if (++indexSelect >= 4) {
                                    indexSelect = 0;
                                }
                            } else if (indexMenu == 6) {
                                if (indexMenu != 6) {
                                    break label1595;
                                }

                                if (GameCanvas.keyPressedz[2]) {
                                    if (indexSelect == 4) {
                                        indexSelect = 0;
                                        --gk;
                                    } else if (--indexSelect < 0) {
                                        indexSelect = 0;
                                        --gk;
                                    }

                                    this.showButtonIndexMenu();
                                    break label1595;
                                }

                                if (GameCanvas.keyPressedz[4]) {
                                    if (indexSelect >= 2 && indexSelect != 4) {
                                        indexSelect = 4;
                                    } else {
                                        indexSelect = 0;
                                    }

                                    this.showButtonIndexMenu();
                                    break label1595;
                                }

                                if (GameCanvas.keyPressedz[6]) {
                                    if (indexSelect < 2) {
                                        indexSelect = 4;
                                    } else {
                                        indexSelect = 2;
                                    }

                                    this.showButtonIndexMenu();
                                    break label1595;
                                }

                                if (!GameCanvas.keyPressedz[8]) {
                                    break label1595;
                                }

                                if (++indexSelect >= 4) {
                                    indexSelect = 0;
                                }
                            }
                        }

                        this.showButtonIndexMenu();
                    }
                }

                if (GameCanvas.isTouch) {
                    this.bt();
                }

                GameCanvas.l();
                GameCanvas.k();
            }

            this.bu();
            if (gu) {
                if (gk == 0) {
                    if (gk == 0 && GameCanvas.keyPressedz[8]) {
                        gk = 1;
                        indexRow = -1;
                        scrMain.a();
                        gn.a();
                    }
                } else {
                    if (indexRow < 0) {
                        indexRow = 0;
                    }

                    if (GameCanvas.keyPressedz[2]) {
                        if (indexRow == 0) {
                            --gk;
                            indexRow = -1;
                        } else {
                            --indexRow;
                        }

                        scrMain.a(indexRow * scrMain.h);
                    } else if (GameCanvas.keyPressedz[8]) {
                        if (++indexRow >= totalRowSetting) {
                            indexRow = 0;
                        }

                        scrMain.a(indexRow * scrMain.h);
                    }
                }

                if (GameCanvas.isTouch && ((var18 = scrMain.b()).a || var18.c)) {
                    indexRow = var18.b;
                    gk = 1;
                }
            }

            this.bx();
            this.by();
            int var19;
            if (Char.getMyChar().fp != null) {
                for (var19 = 0; var19 < GameCanvas.keyPressedz.length; ++var19) {
                    if (GameCanvas.keyPressedz[var19]) {
                        Char.getMyChar().fp = null;
                        break;
                    }
                }
            }

            if (ChatTextField.a().isShow && GameCanvas.at != 0) {
                ChatTextField var20 = ChatTextField.a();
                var1 = GameCanvas.at;
                if (var20.isShow) {
                    var20.a.a(var1);
                }

                if (var20.a.getText().equals("")) {
                    var20.e.caption = mResources.am;
                } else {
                    var20.e.caption = mResources.bb;
                }

                GameCanvas.at = 0;
            }

            if (this.iw) {
                GameCanvas.l();
            } else {
                if (GameCanvas.menu.showMenu || ch() || Char.fl) {
                    return;
                }

                if (GameCanvas.keyPressedz[10]) {
                    GameCanvas.keyPressedz[10] = false;
                    w();
                    GameCanvas.k();
                }

                if (GameCanvas.keyPressedz[11]) {
                    GameCanvas.keyPressedz[11] = false;
                    v();
                    GameCanvas.k();
                }

                if (GameCanvas.at != 0 && TField.isQwerty) {
                    if (GameCanvas.at == 32) {
                        w();
                        GameCanvas.at = 0;
                        GameCanvas.k();
                    } else if (GameCanvas.at == 64) {
                        v();
                        GameCanvas.at = 0;
                        GameCanvas.k();
                    } else if (GameCanvas.at == 48) {
                        v();
                        GameCanvas.at = 0;
                        GameCanvas.k();
                    } else if (GameCanvas.at == 63) {
                        v();
                        GameCanvas.at = 0;
                        GameCanvas.k();
                    }
                }

                if (Char.getMyChar().gc != null) {
                    return;
                }

                if (Char.getMyChar().isCaptcha) {
                    this.hj();
                } else {
                    if (Char.getMyChar().statusMe == 1) {
                        if (GameCanvas.keyPressedz[5]) {
                            GameCanvas.keyPressedz[5] = false;
                            this.b(false);
                        } else if (GameCanvas.l[2]) {
                            if (!Char.getMyChar().fm && !Char.getMyChar().fo) {
                                k(0);
                            }
                        } else if (GameCanvas.l[1]) {
                            Char.getMyChar().cDir = -1;
                            if (!Char.getMyChar().fm && !Char.getMyChar().fo) {
                                k(-4);
                            }
                        } else if (GameCanvas.l[3]) {
                            Char.getMyChar().cDir = 1;
                            if (!Char.getMyChar().fm && !Char.getMyChar().fo) {
                                k(4);
                            }
                        } else if (GameCanvas.l[4]) {
                            Char.getMyChar().bm = false;
                            if (Char.getMyChar().cDir == 1) {
                                Char.getMyChar().cDir = -1;
                            } else if (!Char.getMyChar().fm && !Char.getMyChar().fo) {
                                Char.getMyChar().statusMe = 2;
                                Char.getMyChar().cvx = -Char.getMyChar().getSpeed();
                            }
                        } else if (GameCanvas.l[6]) {
                            Char.getMyChar().bm = false;
                            if (Char.getMyChar().cDir == -1) {
                                Char.getMyChar().cDir = 1;
                            } else if (!Char.getMyChar().fm && !Char.getMyChar().fo) {
                                Char.getMyChar().statusMe = 2;
                                Char.getMyChar().cvx = Char.getMyChar().getSpeed();
                            }
                        }
                    } else if (Char.getMyChar().statusMe == 2) {
                        if (GameCanvas.keyPressedz[5]) {
                            GameCanvas.keyPressedz[5] = false;
                            this.b(false);
                        } else if (GameCanvas.l[2]) {
                            Char.getMyChar().cvy = Char.getMyChar().bk ? -10 : -8;
                            Char.getMyChar().statusMe = 3;
                            Char.getMyChar().cp1 = 0;
                        } else if (GameCanvas.l[1]) {
                            Char.getMyChar().cDir = -1;
                            Char.getMyChar().cvy = Char.getMyChar().bk ? -10 : -8;
                            Char.getMyChar().cvx = -4;
                            Char.getMyChar().statusMe = 3;
                            Char.getMyChar().cp1 = 0;
                        } else if (GameCanvas.l[3]) {
                            Char.getMyChar().cDir = 1;
                            Char.getMyChar().cvy = Char.getMyChar().bk ? -10 : -8;
                            Char.getMyChar().cvx = 4;
                            Char.getMyChar().statusMe = 3;
                            Char.getMyChar().cp1 = 0;
                        } else {
                            Char var17;
                            if (GameCanvas.l[4]) {
                                if (Char.getMyChar().cDir == 1) {
                                    Char.getMyChar().cDir = -1;
                                } else {
                                    var17 = Char.getMyChar();
                                    var19 = -Char.getMyChar().getSpeed();
                                    Char.getMyChar();
                                    var17.cvx = var19;
                                }
                            } else if (GameCanvas.l[6]) {
                                if (Char.getMyChar().cDir == -1) {
                                    Char.getMyChar().cDir = 1;
                                } else {
                                    var17 = Char.getMyChar();
                                    var19 = Char.getMyChar().getSpeed();
                                    Char.getMyChar();
                                    var17.cvx = var19;
                                }
                            }
                        }
                    } else if (Char.getMyChar().statusMe == 3) {
                        if (GameCanvas.keyPressedz[5]) {
                            GameCanvas.keyPressedz[5] = false;
                            this.b(false);
                        }

                        if (!GameCanvas.l[4] && !GameCanvas.l[1]) {
                            if (GameCanvas.l[6] || GameCanvas.l[3]) {
                                if (Char.getMyChar().cDir == -1) {
                                    Char.getMyChar().cDir = 1;
                                } else {
                                    Char.getMyChar().cvx = Char.getMyChar().getSpeed();
                                }
                            }
                        } else if (Char.getMyChar().cDir == 1) {
                            Char.getMyChar().cDir = -1;
                        } else {
                            Char.getMyChar().cvx = -Char.getMyChar().getSpeed();
                        }

                        if ((GameCanvas.l[2] || GameCanvas.l[1] || GameCanvas.l[3]) && Char.getMyChar().bk && Char.getMyChar().cp1 == 0 && Char.getMyChar().cvy > -4) {
                            ++Char.getMyChar().cp1;
                            Char.getMyChar().cvy = -7;
                        }
                    } else if (Char.getMyChar().statusMe == 4) {
                        if (GameCanvas.keyPressedz[5]) {
                            GameCanvas.keyPressedz[5] = false;
                            this.b(false);
                        }

                        if (GameCanvas.keyPressedz[2]) {
                            GameCanvas.k();
                        }

                        if (GameCanvas.l[4]) {
                            if (Char.getMyChar().cDir == 1) {
                                Char.getMyChar().cDir = -1;
                            } else {
                                Char.getMyChar().cvx = -Char.getMyChar().getSpeed();
                            }
                        } else if (GameCanvas.l[6]) {
                            if (Char.getMyChar().cDir == -1) {
                                Char.getMyChar().cDir = 1;
                            } else {
                                Char.getMyChar().cvx = Char.getMyChar().getSpeed();
                            }
                        }
                    } else if (Char.getMyChar().statusMe == 10) {
                        if (GameCanvas.keyPressedz[5]) {
                            GameCanvas.keyPressedz[5] = false;
                            this.b(false);
                        }

                        if (GameCanvas.l[2]) {
                            Char.getMyChar().cvy = -10;
                            Char.getMyChar().statusMe = 3;
                            Char.getMyChar().cp1 = 0;
                        } else if (GameCanvas.l[4]) {
                            if (Char.getMyChar().cDir == 1) {
                                Char.getMyChar().cDir = -1;
                            } else {
                                Char.getMyChar().cvx = -5;
                            }
                        } else if (GameCanvas.l[6]) {
                            if (Char.getMyChar().cDir == -1) {
                                Char.getMyChar().cDir = 1;
                            } else {
                                Char.getMyChar().cvx = 5;
                            }
                        }
                    } else if (Char.getMyChar().statusMe == 7) {
                        if (GameCanvas.keyPressedz[5]) {
                            GameCanvas.keyPressedz[5] = false;
                        }

                        if (GameCanvas.l[4]) {
                            if (Char.getMyChar().cDir == 1) {
                                Char.getMyChar().cDir = -1;
                            } else {
                                Char.getMyChar().cvx = -Char.getMyChar().getSpeed() + 2;
                            }
                        } else if (GameCanvas.l[6]) {
                            if (Char.getMyChar().cDir == -1) {
                                Char.getMyChar().cDir = 1;
                            } else {
                                Char.getMyChar().cvx = Char.getMyChar().getSpeed() - 2;
                            }
                        }
                    } else if (Char.getMyChar().statusMe == 11) {
                        if (GameCanvas.keyPressedz[5]) {
                            GameCanvas.keyPressedz[5] = false;
                            this.b(false);
                        }

                        if (GameCanvas.l[2]) {
                            Char.getMyChar().cvy = -10;
                            Char.getMyChar().statusMe = 3;
                            Char.getMyChar().cp1 = 0;
                        }
                    }

                    if (GameCanvas.keyPressedz[8] && GameCanvas.at != 56) {
                        GameCanvas.keyPressedz[8] = false;
                        this.bq();
                    }
                }

                if (GameCanvas.at != 0) {
                    if (TField.isQwerty) {
                        if (GameCanvas.at == 113) {
                            this.gh = true;
                            if (keySkill[0] != null) {
                                this.a(keySkill[0], true);
                            }
                        } else if (GameCanvas.at == 119) {
                            this.gh = true;
                            if (keySkill[1] != null) {
                                this.a(keySkill[1], true);
                            }
                        } else if (GameCanvas.at == 101) {
                            this.gh = true;
                            if (keySkill[2] != null) {
                                this.a(keySkill[2], true);
                            }
                        } else {
                            ChatTextField.a().a(GameCanvas.at, this, mResources.on[0]);
                        }
                    } else if (!GameCanvas.b) {
                        ChatTextField.a().a(GameCanvas.at, this, mResources.on[0]);
                    } else if (GameCanvas.at == 55) {
                        this.gh = true;
                        if (keySkill[0] != null) {
                            this.a(keySkill[0], true);
                        }
                    } else if (GameCanvas.at == 56) {
                        this.gh = true;
                        if (keySkill[1] != null) {
                            this.a(keySkill[1], true);
                        }
                    } else if (GameCanvas.at == 57) {
                        this.gh = true;
                        if (keySkill[2] != null) {
                            this.a(keySkill[2], true);
                        }
                    } else if (GameCanvas.at == 48) {
                        ChatTextField.a().a(mResources.on[0]);
                    }

                    GameCanvas.at = 0;
                }
            }

            GameCanvas.k();
        }

    }

    private void bo() {
        es = 0;
        this.et = Char.getMyChar().fm = false;
    }

    public static void v() {
        if (!Char.getMyChar().e(17)) {
            for (int var0 = 0; var0 < Char.getMyChar().arrItemBag.length; ++var0) {
                if (Char.getMyChar().arrItemBag[var0] != null && Char.getMyChar().arrItemBag[var0].template.type == 17) {
                    InfoMe.a(mResources.nd);
                    return;
                }
            }

            if (es != 1) {
                InfoMe.a(mResources.ne);
            }
        }

    }

    public static void w() {
        int var0 = (int) (System.currentTimeMillis() / 1000L);

        int var1;
        for (var1 = 0; var1 < Char.getMyChar().vEff.size(); ++var1) {
            Effect var2;
            if ((var2 = (Effect) Char.getMyChar().vEff.elementAt(var1)).e.a == 21 && var2.c - (var0 - var2.b) >= 2) {
                return;
            }
        }

        if (!Char.getMyChar().e(16)) {
            for (var1 = 0; var1 < Char.getMyChar().arrItemBag.length; ++var1) {
                if (Char.getMyChar().arrItemBag[var1] != null && Char.getMyChar().arrItemBag[var1].template.type == 16) {
                    InfoMe.a(mResources.nd);
                    return;
                }
            }

            if (es != 1) {
                InfoMe.a(mResources.nf);
            }
        }

    }

    private static boolean bp() {
        if (Char.getMyChar().mobFocus == null) {
            return false;
        } else {
            return Char.getMyChar().mobFocus.getMobTemplate().e == 142 && Char.getMyChar().cTypePk == 4 || Char.getMyChar().mobFocus.getMobTemplate().e == 143 && Char.getMyChar().cTypePk == 5;
        }
    }

    private void b(boolean var1) {
        if (Char.getMyChar().statusMe != 14) {
            boolean var4;
            MyVector var5;
            label432:
            {
                label407:
                {
                    if (!InfoDlg.f && !Char.getMyChar().fn && !Char.fl && !Char.getMyChar().fo) {
                        label433:
                        {
                            if (Char.getMyChar().mobFocus != null && (Char.getMyChar().mobFocus.id == 97 && Char.getMyChar().cTypePk == 4 || Char.getMyChar().mobFocus.id == 98 && Char.getMyChar().cTypePk == 4 || Char.getMyChar().mobFocus.id == 96 && Char.getMyChar().cTypePk == 5 || Char.getMyChar().mobFocus.id == 99 && Char.getMyChar().cTypePk == 5)) {
                                var4 = false;
                                break label432;
                            }

                            if (Char.getMyChar().selectSkill != null && Char.getMyChar().selectSkill.template.type == 2 && Char.getMyChar().npcFocus == null) {
                                var4 = bn();
                                break label432;
                            }

                            if (Char.getMyChar().gc != null || Char.getMyChar().charFocus != null && Char.getMyChar().charFocus.isNhanban || Char.getMyChar().mobFocus == null && Char.getMyChar().npcFocus == null && Char.getMyChar().charFocus == null && Char.getMyChar().itemFocus == null) {
                                var4 = false;
                                break label432;
                            }

                            int var2;
                            int var3;
                            if (Char.getMyChar().mobFocus != null) {
                                if (Char.getMyChar().selectSkill == null) {
                                    break label433;
                                }

                                if (Char.getMyChar().arrItemBody[1] == null) {
                                    InfoMe.a(mResources.oh);
                                    break label433;
                                }

                                if (Char.getMyChar().mobFocus.h == 1 || Char.getMyChar().mobFocus.h == 0 || Char.getMyChar().selectSkill.template.type == 4) {
                                    var4 = false;
                                    break label432;
                                }

                                if (!bn()) {
                                    break label433;
                                }

                                if (Char.getMyChar().cx < Char.getMyChar().mobFocus.curX) {
                                    Char.getMyChar().cDir = 1;
                                } else {
                                    Char.getMyChar().cDir = -1;
                                }

                                var2 = Math.abs(Char.getMyChar().cx - Char.getMyChar().mobFocus.curX);
                                var3 = Math.abs(Char.getMyChar().cy - Char.getMyChar().mobFocus.curY);
                                Char.getMyChar().cvx = 0;
                                if (Char.getMyChar().isNoiCong()) {
                                    if (var2 > Char.getMyChar().selectSkill.getNSkill() || var3 > Char.getMyChar().selectSkill.getCSkill()) {
                                        if (AutoTeleTarget.moveToMob(Char.getMyChar().mobFocus)) {
                                            GameCanvas.l();
                                            GameCanvas.k();
                                            break label433;
                                        }

                                        Char.getMyChar().fp = new MovePoint(Char.getMyChar().mobFocus.curX, Char.getMyChar().cy);
                                        GameCanvas.l();
                                        GameCanvas.k();
                                        break label433;
                                    }

                                    GameCanvas.l();
                                    GameCanvas.k();
                                } else if ((Char.getMyChar().selectSkill.template.id == 24 || Char.getMyChar().selectSkill.template.id == 40 || Char.getMyChar().selectSkill.template.id == 42) && var2 <= Char.getMyChar().selectSkill.getNSkill() && var3 <= Char.getMyChar().selectSkill.getCSkill()) {
                                    GameCanvas.l();
                                    GameCanvas.k();
                                    Char.getMyChar().cvx = 0;
                                } else {
                                    if (var2 > Char.getMyChar().selectSkill.getNSkill() || var3 > Char.getMyChar().selectSkill.getCSkill() || Char.getMyChar().cy < Char.getMyChar().mobFocus.curY - 10) {
                                        if (AutoTeleTarget.moveToMob(Char.getMyChar().mobFocus)) {
                                            GameCanvas.l();
                                            GameCanvas.k();
                                            break label433;
                                        }

                                        Char.getMyChar().fp = new MovePoint(Char.getMyChar().mobFocus.curX + Char.getMyChar().mobFocus.g * 12, Char.getMyChar().cy);
                                        GameCanvas.l();
                                        GameCanvas.k();
                                        break label433;
                                    }

                                    GameCanvas.l();
                                    GameCanvas.k();
                                    Char.getMyChar().cvx = 0;
                                }
                            } else {
                                if (Char.getMyChar().npcFocus != null) {
                                    if (Char.getMyChar().cx < Char.getMyChar().npcFocus.cx) {
                                        Char.getMyChar().cDir = 1;
                                    } else {
                                        Char.getMyChar().cDir = -1;
                                    }

                                    if (Char.getMyChar().cx < Char.getMyChar().npcFocus.cx) {
                                        Char.getMyChar().npcFocus.cDir = -1;
                                    } else {
                                        Char.getMyChar().npcFocus.cDir = 1;
                                    }

                                    var2 = Math.abs(Char.getMyChar().cx - Char.getMyChar().npcFocus.cx);
                                    var3 = Math.abs(Char.getMyChar().cy - Char.getMyChar().npcFocus.cy);
                                    if (var2 < 60 && var3 < 40) {
                                        GameCanvas.l();
                                        GameCanvas.k();
                                        if (Char.getMyChar().npcFocus.template.npcTemplateId == 13) {
                                            InfoDlg.b();
                                            Service.getInstance().openUIZone();
                                        } else {
                                            Service.getInstance().openMenu(Char.getMyChar().npcFocus.template.npcTemplateId);
                                            InfoDlg.b();
                                        }
                                    } else if (AutoTeleTarget.moveToNpc(Char.getMyChar().npcFocus)) {
                                        GameCanvas.l();
                                        GameCanvas.k();
                                        AutoTeleTarget.openNpc(Char.getMyChar().npcFocus);
                                    } else {
                                        Char.getMyChar().fp = new MovePoint(Char.getMyChar().npcFocus.cx, Char.getMyChar().cy);
                                        GameCanvas.l();
                                        GameCanvas.k();
                                    }

                                    var4 = false;
                                    break label432;
                                }

                                if (Char.getMyChar().charFocus != null) {
                                    if (Char.getMyChar().cx < Char.getMyChar().charFocus.cx) {
                                        Char.getMyChar().cDir = 1;
                                    } else {
                                        Char.getMyChar().cDir = -1;
                                    }

                                    var2 = Math.abs(Char.getMyChar().cx - Char.getMyChar().charFocus.cx);
                                    var3 = Math.abs(Char.getMyChar().cy - Char.getMyChar().charFocus.cy);
                                    Char.getMyChar();
                                    if (!Char.b(Char.getMyChar().charFocus)) {
                                        if (var2 < 60 && var3 < 40 && Char.getMyChar().charFocus.charID >= 0) {
                                            GameCanvas.l();
                                            if (Char.getMyChar().charFocus.statusMe == 14 || Char.getMyChar().charFocus.statusMe == 5 || TileMap.typeMap != 1) {
                                                if (!this.gh) {
                                                    (var5 = new MyVector()).addElement(new Command(mResources.menuOtherChar[6], 110397));
                                                    var5.addElement(new Command(mResources.menuOtherChar[4], 110391));
                                                    if ((Char.getMyChar().ctypeClan == 4 || Char.getMyChar().ctypeClan == 3 || Char.getMyChar().ctypeClan == 2) && Char.getMyChar().charFocus.cClanName.equals("")) {
                                                        var5.addElement(new Command(mResources.menuOtherChar[8], 110398));
                                                    }

                                                    if ((Char.getMyChar().charFocus.ctypeClan == 4 || Char.getMyChar().charFocus.ctypeClan == 3 || Char.getMyChar().charFocus.ctypeClan == 2) && Char.getMyChar().cClanName.equals("")) {
                                                        var5.addElement(new Command(mResources.menuOtherChar[9], 110399));
                                                    }

                                                    var5.addElement(new Command(mResources.menuOtherChar[7], 12004, Char.getMyChar().charFocus.charName));
                                                    if (Char.getMyChar().nClass.classId == 6) {
                                                        var5.addElement(new Command(mResources.menuOtherChar[11] + ": " + (!Char.isAResuscitate ? mResources.av : mResources.aw), 1103991));
                                                    }

                                                    if (Char.getMyChar().charFocus.statusMe != 14 && Char.getMyChar().charFocus.statusMe != 5) {
                                                        var5.addElement(new Command(mResources.menuOtherChar[0], 110392));
                                                        var5.addElement(new Command(mResources.menuOtherChar[1], 110393));
                                                        var5.addElement(new Command(mResources.menuOtherChar[2], 110394));
                                                    } else if (Char.getMyChar().selectSkill.template.type == 4) {
                                                        var5.addElement(new Command(mResources.menuOtherChar[5], 110395));
                                                    }

                                                    var5.addElement(new Command(mResources.menuOtherChar[3], 110396));
                                                    GameCanvas.menu.showMenu(var5);
                                                }

                                                this.gh = false;
                                                break label407;
                                            }
                                            break label433;
                                        }

                                        if (AutoTeleTarget.moveToChar(Char.getMyChar().charFocus)) {
                                            GameCanvas.l();
                                            GameCanvas.k();
                                            break label407;
                                        }

                                        Char.getMyChar().fp = new MovePoint(Char.getMyChar().charFocus.cx, Char.getMyChar().cy);
                                        GameCanvas.l();
                                        GameCanvas.k();
                                        break label407;
                                    }

                                    if (Char.getMyChar().selectSkill == null) {
                                        break label433;
                                    }

                                    if (Char.getMyChar().arrItemBody[1] == null) {
                                        InfoMe.a(mResources.oh);
                                        break label433;
                                    }

                                    if (!bn()) {
                                        break label433;
                                    }

                                    if (Char.getMyChar().cx < Char.getMyChar().charFocus.cx) {
                                        Char.getMyChar().cDir = 1;
                                    } else {
                                        Char.getMyChar().cDir = -1;
                                    }

                                    Char.getMyChar().cvx = 0;
                                    if (Char.getMyChar().isNoiCong()) {
                                        if (var2 > Char.getMyChar().selectSkill.getNSkill() || var3 > Char.getMyChar().selectSkill.getCSkill()) {
                                            if (AutoTeleTarget.moveToChar(Char.getMyChar().charFocus)) {
                                                GameCanvas.l();
                                                GameCanvas.k();
                                                break label433;
                                            }

                                            Char.getMyChar().fp = new MovePoint(Char.getMyChar().charFocus.cx, Char.getMyChar().cy);
                                            GameCanvas.l();
                                            GameCanvas.k();
                                            break label433;
                                        }

                                        GameCanvas.l();
                                        GameCanvas.k();
                                    } else if ((Char.getMyChar().selectSkill.template.id == 24 || Char.getMyChar().selectSkill.template.id == 40 || Char.getMyChar().selectSkill.template.id == 42) && var2 <= Char.getMyChar().selectSkill.getNSkill() && var3 <= Char.getMyChar().selectSkill.getCSkill()) {
                                        GameCanvas.l();
                                        GameCanvas.k();
                                        Char.getMyChar().cvx = 0;
                                    } else {
                                        if (var2 > Char.getMyChar().selectSkill.getNSkill() || var3 > Char.getMyChar().selectSkill.getCSkill() || Char.getMyChar().cy < Char.getMyChar().charFocus.cy) {
                                            if (AutoTeleTarget.moveToChar(Char.getMyChar().charFocus)) {
                                                GameCanvas.l();
                                                GameCanvas.k();
                                                break label433;
                                            }

                                            Char.getMyChar().fp = new MovePoint(Char.getMyChar().charFocus.cx + Char.getMyChar().charFocus.cDir * 12, Char.getMyChar().cy);
                                            GameCanvas.l();
                                            GameCanvas.k();
                                            break label433;
                                        }

                                        GameCanvas.l();
                                        GameCanvas.k();
                                        Char.getMyChar().cvx = 0;
                                    }
                                } else if (Char.getMyChar().itemFocus != null) {
                                    if (Char.getMyChar().statusMe == 1) {
                                        if (Char.getMyChar().cx < Char.getMyChar().itemFocus.x) {
                                            Char.getMyChar().cDir = 1;
                                        } else {
                                            Char.getMyChar().cDir = -1;
                                        }

                                        var2 = Math.abs(Char.getMyChar().cx - Char.getMyChar().itemFocus.x);
                                        var3 = Math.abs(Char.getMyChar().cy - Char.getMyChar().itemFocus.y);
                                        if ((var2 > 35 || var3 >= 35) && (es == 0 || var2 > 48 || var3 > 48)) {
                                            if (AutoTeleTarget.moveToItem(Char.getMyChar().itemFocus)) {
                                                GameCanvas.l();
                                                GameCanvas.k();
                                                var4 = false;
                                                break label432;
                                            }

                                            Char.getMyChar().fp = new MovePoint(Char.getMyChar().itemFocus.x, Char.getMyChar().cy);
                                            GameCanvas.l();
                                            GameCanvas.k();
                                        } else {
                                            GameCanvas.l();
                                            GameCanvas.k();
                                            Service.getInstance().pickItem(Char.getMyChar().itemFocus.itemMapID);
                                        }

                                        var4 = false;
                                        break label432;
                                    }
                                    break label433;
                                }
                            }

                            var4 = true;
                            break label432;
                        }
                    }

                    var4 = false;
                    break label432;
                }

                var4 = false;
            }

            if (var4) {
                if (bp()) {
                    (var5 = new MyVector()).addElement(new Command(mResources.sa, 151301));
                    GameCanvas.menu.showMenu(var5);
                    return;
                }

                if (Char.getMyChar().mobFocus != null && Char.getMyChar().mobFocus.getMobTemplate().e == 144 && TileMap.mapID == 130) {
                    (var5 = new MyVector()).addElement(new Command(mResources.se, 151301));
                    GameCanvas.menu.showMenu(var5);
                    return;
                }

                Char.getMyChar().a(skillPaints[Char.getMyChar().selectSkill.template.id], 0);
                Char.getMyChar().gj = var1;
                if (Char.getMyChar().bp) {
                    Char.getMyChar().bp = false;
                    Char.getMyChar().fd = System.currentTimeMillis();
                    if (Char.getMyChar().fr >= 500) {
                        ServerEffect.a(60, Char.getMyChar(), 1);
                    }
                }

                if (Char.getMyChar().isBike() && !Char.getMyChar().bq) {
                    Char.getMyChar().bo = false;
                    Char.getMyChar().bq = true;
                    ServerEffect.a(60, Char.getMyChar(), 1);
                }
            }
        }

        if (!var1) {
            Char.getMyChar().gi = Char.getMyChar().selectSkill;
        }

    }

    private void bq() {
        this.ke = 0;

        int var1;
        for (var1 = 0; var1 < arrSkill.length; ++var1) {
            if (arrSkill[var1] != null) {
                ++this.ke;
            }
        }

        if (this.ke <= 1) {
            InfoMe.b();
        } else {
            if (!gz || this.kf == -1) {
                gz = true;

                for (var1 = 0; var1 < arrSkill.length; ++var1) {
                    if (arrSkill[var1] == Char.getMyChar().selectSkill) {
                        this.kf = var1;
                        break;
                    }
                }
            }

            ++this.kf;
            if (this.kf >= arrSkill.length) {
                this.kf = 0;
            }

            if (arrSkill[this.kf] == null) {
                this.kf = 0;
            }

            super.center = new Command("", 11059);
        }

    }

    public final void a(Skill var1, boolean var2) {
        this.kf = -1;
        if (var1 != null) {
            if (var1.template.type == 4 && Char.getMyChar().charFocus != null) {
                if (Char.getMyChar().charFocus.isNhanban) {
                    return;
                }

                if (Char.getMyChar().charFocus.statusMe == 14 || Char.getMyChar().charFocus.statusMe == 5) {
                    Service.getInstance().buffLive(Char.getMyChar().charFocus.charID);
                    if ((TileMap.a(Char.getMyChar().cx, Char.getMyChar().cy) & 2) == 2) {
                        Char.getMyChar().a(skillPaints[49], 0);
                    } else {
                        Char.getMyChar().a(skillPaints[49], 1);
                    }
                }
            } else {
                Service.getInstance().selectSkill(var1.template.id);
            }
        }

        if (var1.template.type != 2) {
            this.resetButton();
        }

        if (var1 != null) {
            Char.getMyChar().selectSkill = var1;
            if (var1.template.type == 1 && Code.auto != null) {
                Auto.selectSkill = var1;
            }

            if (Char.getMyChar().npcFocus == null && var1.template.type != 4) {
                this.b(var2);
            }
        }

    }

    public static void c(int var0) {
        MyVector var6 = var0 == 0 ? aa : ad;

        for (int var1 = 0; var1 < var6.size() - 1; ++var1) {
            Friend var2 = (Friend) var6.elementAt(var1);

            for (int var3 = var1 + 1; var3 < var6.size(); ++var3) {
                Friend var4;
                Friend var5;
                if ((var4 = (Friend) var6.elementAt(var3)).b > var2.b) {
                    var5 = var4;
                    var4 = var2;
                    var2 = var5;
                    var6.setElementAt(var5, var1);
                    var6.setElementAt(var4, var3);
                } else if (var4.b == var2.b && var2.a.compareTo(var4.a) > 0) {
                    var5 = var4;
                    var4 = var2;
                    var2 = var5;
                    var6.setElementAt(var5, var1);
                    var6.setElementAt(var4, var3);
                }
            }
        }

    }

    public static void x() {
        for (int var0 = 0; var0 < vClan.size() - 1; ++var0) {
            Member var1 = (Member) vClan.elementAt(var0);

            for (int var2 = var0 + 1; var2 < vClan.size(); ++var2) {
                Member var3 = (Member) vClan.elementAt(var2);
                Member var4;
                if (gq && !gr) {
                    if (var3.isOnline && !var1.isOnline) {
                        var4 = var3;
                        var3 = var1;
                        var1 = var4;
                        vClan.setElementAt(var4, var0);
                        vClan.setElementAt(var3, var2);
                    } else if (var3.isOnline && var1.isOnline) {
                        if (var3.type > var1.type) {
                            var4 = var3;
                            var3 = var1;
                            var1 = var4;
                            vClan.setElementAt(var4, var0);
                            vClan.setElementAt(var3, var2);
                        } else if (var3.type == var1.type) {
                            if (var3.pointClan > var1.pointClan) {
                                var4 = var3;
                                var3 = var1;
                                var1 = var4;
                                vClan.setElementAt(var4, var0);
                                vClan.setElementAt(var3, var2);
                            } else if (var1.pointClan == var3.pointClan) {
                                if (var3.pointClanWeek > var1.pointClanWeek) {
                                    var4 = var3;
                                    var3 = var1;
                                    var1 = var4;
                                    vClan.setElementAt(var4, var0);
                                    vClan.setElementAt(var3, var2);
                                } else if (var1.pointClanWeek == var3.pointClanWeek) {
                                    if (var3.level > var1.level) {
                                        var4 = var3;
                                        var3 = var1;
                                        var1 = var4;
                                        vClan.setElementAt(var4, var0);
                                        vClan.setElementAt(var3, var2);
                                    } else if (var1.level == var3.level && var1.name.compareTo(var3.name) > 0) {
                                        var4 = var3;
                                        var3 = var1;
                                        var1 = var4;
                                        vClan.setElementAt(var4, var0);
                                        vClan.setElementAt(var3, var2);
                                    }
                                }
                            }
                        }
                    }
                } else if (gr) {
                    if (gq) {
                        if (var3.isOnline && !var1.isOnline) {
                            var4 = var3;
                            var3 = var1;
                            var1 = var4;
                            vClan.setElementAt(var4, var0);
                            vClan.setElementAt(var3, var2);
                        } else if (var3.isOnline && var1.isOnline) {
                            if (var3.pointClanWeek > var1.pointClanWeek) {
                                var4 = var3;
                                var3 = var1;
                                var1 = var4;
                                vClan.setElementAt(var4, var0);
                                vClan.setElementAt(var3, var2);
                            } else if (var1.pointClanWeek == var3.pointClanWeek) {
                                if (var3.pointClan > var1.pointClan) {
                                    var4 = var3;
                                    var3 = var1;
                                    var1 = var4;
                                    vClan.setElementAt(var4, var0);
                                    vClan.setElementAt(var3, var2);
                                } else if (var1.pointClan == var3.pointClan) {
                                    if (var3.type > var1.type) {
                                        var4 = var3;
                                        var3 = var1;
                                        var1 = var4;
                                        vClan.setElementAt(var4, var0);
                                        vClan.setElementAt(var3, var2);
                                    } else if (var3.type == var1.type && var1.level == var3.level && var1.name.compareTo(var3.name) > 0) {
                                        var4 = var3;
                                        var3 = var1;
                                        var1 = var4;
                                        vClan.setElementAt(var4, var0);
                                        vClan.setElementAt(var3, var2);
                                    }
                                }
                            }
                        }
                    } else if (var3.pointClanWeek > var1.pointClanWeek) {
                        var4 = var3;
                        var3 = var1;
                        var1 = var4;
                        vClan.setElementAt(var4, var0);
                        vClan.setElementAt(var3, var2);
                    } else if (var1.pointClanWeek == var3.pointClanWeek) {
                        if (var3.pointClan > var1.pointClan) {
                            var4 = var3;
                            var3 = var1;
                            var1 = var4;
                            vClan.setElementAt(var4, var0);
                            vClan.setElementAt(var3, var2);
                        } else if (var1.pointClan == var3.pointClan) {
                            if (var3.type > var1.type) {
                                var4 = var3;
                                var3 = var1;
                                var1 = var4;
                                vClan.setElementAt(var4, var0);
                                vClan.setElementAt(var3, var2);
                            } else if (var3.type == var1.type && var1.level == var3.level && var1.name.compareTo(var3.name) > 0) {
                                var4 = var3;
                                var3 = var1;
                                var1 = var4;
                                vClan.setElementAt(var4, var0);
                                vClan.setElementAt(var3, var2);
                            }
                        }
                    }
                } else if (var3.type > var1.type) {
                    var4 = var3;
                    var3 = var1;
                    var1 = var4;
                    vClan.setElementAt(var4, var0);
                    vClan.setElementAt(var3, var2);
                } else if (var3.type == var1.type) {
                    if (var3.pointClan > var1.pointClan) {
                        var4 = var3;
                        var3 = var1;
                        var1 = var4;
                        vClan.setElementAt(var4, var0);
                        vClan.setElementAt(var3, var2);
                    } else if (var1.pointClan == var3.pointClan) {
                        if (var3.pointClanWeek > var1.pointClanWeek) {
                            var4 = var3;
                            var3 = var1;
                            var1 = var4;
                            vClan.setElementAt(var4, var0);
                            vClan.setElementAt(var3, var2);
                        } else if (var1.pointClanWeek == var3.pointClanWeek) {
                            if (var3.level > var1.level) {
                                var4 = var3;
                                var3 = var1;
                                var1 = var4;
                                vClan.setElementAt(var4, var0);
                                vClan.setElementAt(var3, var2);
                            } else if (var1.level == var3.level && var1.name.compareTo(var3.name) > 0) {
                                var4 = var3;
                                var3 = var1;
                                var1 = var4;
                                vClan.setElementAt(var4, var0);
                                vClan.setElementAt(var3, var2);
                            }
                        }
                    }
                }
            }
        }

    }

    public static void y() {
        for (int var0 = 0; var0 < Char.getMyChar().vSkillFight.size() - 1; ++var0) {
            Skill var1 = (Skill) Char.getMyChar().vSkillFight.elementAt(var0);

            for (int var2 = var0 + 1; var2 < Char.getMyChar().vSkillFight.size(); ++var2) {
                Skill var3;
                if ((var3 = (Skill) Char.getMyChar().vSkillFight.elementAt(var2)).template.id < var1.template.id) {
                    Skill var4 = var3;
                    var3 = var1;
                    var1 = var4;
                    Char.getMyChar().vSkillFight.setElementAt(var4, var0);
                    Char.getMyChar().vSkillFight.setElementAt(var3, var2);
                }
            }
        }

    }

    private static void k(int var0) {
        Char.getMyChar().cvy = Char.getMyChar().bk ? -10 : -8;
        Char.getMyChar().cvx = var0;
        Char.getMyChar().statusMe = 3;
        Char.getMyChar().cp1 = 0;
    }

    public final void c() {
        if (eq != 0 && !GameCanvas.lowGraphic) {
            cmx += NinjaUtil.a();
            if (++er > 20) {
                eq = 0;
                er = 0;
            }
        } else if (cmx != k || cmy != l) {
            if (!eo) {
                fw = k - cmx << 2;
                fx = l - cmy << 2;
            } else {
                fw = k - cmx << 1;
                fx = l - cmy << 2;
            }

            fu += fw;
            cmx += fu >> 4;
            fu &= 15;
            fv += fx;
            cmy += fv >> 4;
            fv &= 15;
            if (cmx < 24) {
                cmx = 24;
            }

            if (cmx > fy) {
                cmx = fy;
            }

            if (cmy < 0) {
                cmy = 0;
            }

            if (cmy > fz) {
                cmy = fz;
            }
        }

        if ((m = cmx / TileMap.size - 1) < 0) {
            m = 0;
        }

        n = cmy / TileMap.size;
        o = m + fs;
        p = n + ft;
        if (n < 0) {
            n = 0;
        }

        if (p > TileMap.b - 1) {
            p = TileMap.b - 1;
        }

        if ((TileMap.z = (Char.getMyChar().cx - 2 * gW) / TileMap.size) < 0) {
            TileMap.z = 0;
        }

        if ((TileMap.aa = TileMap.z + TileMap.ad) > TileMap.a) {
            TileMap.aa = TileMap.a;
            TileMap.z = TileMap.a - TileMap.ad;
        }

        if ((TileMap.ab = (Char.getMyChar().cy - 2 * gH) / TileMap.size) < 0) {
            TileMap.ab = 0;
        }

        if ((TileMap.ac = TileMap.ab + TileMap.ae) > TileMap.b) {
            TileMap.ac = TileMap.b;
            TileMap.ab = TileMap.b - TileMap.ae;
        }

        scrMain.c();
        gn.c();
        ChatTextField var1;
        if ((var1 = ChatTextField.a()).isShow) {
            var1.a.c();
            if (var1.a.i) {
                var1.a.i = false;
                var1.c.a(var1.a.getText(), var1.g);
                var1.a.a("");
                var1.e.caption = mResources.am;
            }
        }

        TileMap.b();
        TileMap.f();
        GameCanvas.f();
        int var6;
        Char var12;
        if (GameCanvas.c) {
            MyVector var2 = new MyVector();
            long var4 = System.currentTimeMillis();

            for (var6 = 0; var6 < vCharInMap.size(); ++var6) {
                (var12 = (Char) vCharInMap.elementAt(var6)).b();
                if (var12.u()) {
                    if (var12.gh && var4 - var12.lastUpdateTime > 10000L && var4 - this.kg > 10000L) {
                        var12.gh = false;
                        var12.lastUpdateTime = var4;
                        var2.addElement(var12);
                    }
                } else {
                    var12.lastUpdateTime = var4;
                    var12.gh = true;
                }
            }

            if (var2.size() > 0) {
                Service.getInstance().a(var2);
                this.kg = var4;
            }
        } else {
            for (var6 = 0; var6 < vCharInMap.size(); ++var6) {
                ((Char) vCharInMap.elementAt(var6)).b();
            }
        }

        Char.getMyChar().b();
        if (Char.getMyChar().cHP <= 0 && TileMap.ah && !LockGame.a) {
            TileMap.h();
        }

        if (Char.getMyChar().statusMe == 1 && GameCanvas.gameTick % 100 == 0) {
            System.gc();
        }

        for (var6 = 0; var6 < vMobAttack.size(); ++var6) {
            ((Mob) vMobAttack.elementAt(var6)).g();
        }

        for (var6 = 0; var6 < ah.size(); ++var6) {
            ((Npc) ah.elementAt(var6)).b();
        }

        GameCanvas.b().p();

        int var7;
        int[] var10000;
        boolean optimizeVisual = FormToiUu.isHideAll();
        if (!optimizeVisual) {
            for (var7 = 0; var7 < 5; ++var7) {
                if (lj[var7] != -1) {
                    var10000 = lj;
                    var10000[var7] += Res.e(li[var7]);
                    if (lj[var7] > 30) {
                        lj[var7] = -1;
                    }

                    var10000 = lf;
                    var10000[var7] += lh[var7];
                    var10000 = lg;
                    var10000[var7] += li[var7];
                }
            }

            for (var7 = 0; var7 < gi.size(); ++var7) {
                Lanterns var8;
                Lanterns var13 = var8 = (Lanterns) gi.elementAt(var7);
                var13.b -= var8.c;
                if (var8.d - var8.b > 150) {
                    var8.e = true;
                }

                if (((Lanterns) gi.elementAt(var7)).e) {
                    gi.removeElementAt(var7);
                }
            }

            for (var7 = 0; var7 < 2; ++var7) {
                if (ln[var7] != -1) {
                    int var10003 = ln[var7]++;
                    var10000 = ll;
                    var10000[var7] += lp[var7] << 2;
                    var10003 = lm[var7]--;
                    if (ln[var7] >= 6) {
                        ln[var7] = -1;
                    } else {
                        lo[var7] = (ln[var7] >> 1) % 3;
                    }
                }
            }
        } else {
            clearOptimizeTransientVisuals();
        }

        if (indexMenu != -1) {
            if (nx != 0) {
                oa = 0 - nx << 2;
                nz += oa;
                nx += nz >> 4;
                nz &= 15;
            }

            if (Math.abs(0 - nx) < 15 && nx < 0) {
                ny = 0;
            }

            if (Math.abs(0 - nx) < 15 && nx > 0) {
                ny = 0;
            }
        }

        GameCanvas.g();

        for (var6 = 0; var6 < vItemMap.size(); ++var6) {
            ItemMap itemMap;
            if ((itemMap = (ItemMap) vItemMap.elementAt(var6)).status == 2 && itemMap.x == itemMap.xEnd && itemMap.y == itemMap.yEnd) {
                vItemMap.removeElement(itemMap);
                if (Char.getMyChar().itemFocus != null && Char.getMyChar().itemFocus.equals(itemMap)) {
                    Char.getMyChar().itemFocus = null;
                }
            } else if (itemMap.status <= 0) {
                itemMap.status = (byte) (itemMap.status - 4);
                if (itemMap.status < -12) {
                    itemMap.y -= 12;
                    itemMap.status = 1;
                }
            } else {
                if (itemMap.vx == 0) {
                    itemMap.x = itemMap.xEnd;
                }

                if (itemMap.vy == 0) {
                    itemMap.y = itemMap.yEnd;
                }

                if (itemMap.x != itemMap.xEnd) {
                    itemMap.x += itemMap.vx;
                    if (itemMap.vx > 0 && itemMap.x > itemMap.xEnd || itemMap.vx < 0 && itemMap.x < itemMap.xEnd) {
                        itemMap.x = itemMap.xEnd;
                    }
                }

                if (itemMap.y != itemMap.yEnd) {
                    itemMap.y += itemMap.vy;
                    if (itemMap.vy > 0 && itemMap.y > itemMap.yEnd || itemMap.vy < 0 && itemMap.y < itemMap.yEnd) {
                        itemMap.y = itemMap.yEnd;
                    }
                }
            }

            if (itemMap.lastTimePickup > 0 && System.currentTimeMillis() - itemMap.lastTimePickup >= 30000L) {
                itemMap.status = 2;
            }
            if (itemMap.isPickedUp && System.currentTimeMillis() - itemMap.lastTimePickup > 5000L) {
                itemMap.isPickedUp = false;
            }
        }

        if (!optimizeVisual) {
            for (var6 = 0; var6 < w.size(); ++var6) {
                ((MobSoul) w.elementAt(var6)).a();
            }
        } else if (w.size() > 0) {
            w.removeAllElements();
        }

        if ((TileMap.a * TileMap.y >= TileMap.wMiniMap || TileMap.b * TileMap.y >= TileMap.hMiniMap) && System.currentTimeMillis() / 100L > 20L) {
            TileMap.a();
        }

        if (!optimizeVisual) {
            for (var6 = Effect2.vRemoveEffect2.size() - 1; var6 >= 0; --var6) {
                Effect2.vEffect2.removeElement(Effect2.vRemoveEffect2.elementAt(var6));
                Effect2.vRemoveEffect2.removeElementAt(var6);
            }

            for (var6 = 0; var6 < Effect2.vEffect2.size(); ++var6) {
                ((Effect2) Effect2.vEffect2.elementAt(var6)).update();
            }

            for (var6 = 0; var6 < Effect2.vEffect2Outside.size(); ++var6) {
                ((Effect2) Effect2.vEffect2Outside.elementAt(var6)).update();
            }

            for (var6 = 0; var6 < Effect2.vAnimateEffect.size(); ++var6) {
                ((Effect2) Effect2.vAnimateEffect.elementAt(var6)).update();
            }

            for (var6 = 0; var6 < Mob.aa.size(); ++var6) {
                EggMonters var5;
                EggMonters var10;
                if ((var5 = var10 = (EggMonters) Mob.aa.elementAt(var6)).a()) {
                    if (var5.d == 0) {
                        ++var5.e;
                        var5.b += var5.e;
                        ++var5.c;
                        if (var5.c > 3) {
                            var5.c = 0;
                        }

                        if ((TileMap.a(var5.a, var5.b) & 2) == 2) {
                            var5.d = 1;
                            var5.e = 0;
                        }
                    } else if (var5.d == 1) {
                        ++var5.c;
                        if (var5.c > 6) {
                            var5.c = 6;
                            EggMonters.f.h = 5;
                        }
                    }
                }

                if (var10.c == 6) {
                    Mob.aa.removeElementAt(var6);
                }
            }
        } else {
            updateOptimizeEffectQueue();
        }

        if (!optimizeVisual || GameCanvas.gameTick % 100 == 0) {
            SmallImage.a();
        }
        if (this.de >= 0 && vCharInMap.size() > 0) {
            int var11;
            if ((var11 = Char.d(this.de)) >= 0 && var11 < vCharInMap.size()) {
                if ((var12 = (Char) vCharInMap.elementAt(var11)) != null && Char.a(var12) && !var12.isNhanban) {
                    Char.getMyChar().mobFocus = null;
                    Char.getMyChar().y();
                    Char.getMyChar().itemFocus = null;
                    Char.getMyChar();
                    Char.ge = true;
                    Char.getMyChar().charFocus = var12;
                }
            } else {
                this.de = -1;
                Char.getMyChar().charFocus = null;
            }
        } else {
            this.de = -1;
        }

        Info.a();
        InfoMe.a();
        if (currentCharViewInfo != null && currentCharViewInfo.charID != Char.getMyChar().charID) {
            currentCharViewInfo.b();
        }

        ++this.kh;
        if (this.kh > 3) {
            this.kh = 0;
        }

        if (gt) {
            wItem = 40;
        } else {
            wItem = 28;
        }

        EffectAuto.b();
        EffectAuto.c();
        boolean var14 = GameCanvas.av;
    }

    private static void clearOptimizeTransientVisuals() {
        try {
            if (gi.size() > 0) {
                gi.removeAllElements();
            }

            for (int i = 0; i < lj.length; ++i) {
                lj[i] = -1;
            }

            for (int i = 0; i < ln.length; ++i) {
                ln[i] = -1;
            }
        } catch (Exception e) {
        }
    }

    private static void updateOptimizeEffectQueue() {
        try {
            if (w.size() > 0) {
                w.removeAllElements();
            }

            if (Mob.aa.size() > 0) {
                Mob.aa.removeAllElements();
            }

            if (Effect2.vRemoveEffect2.size() > 0) {
                Effect2.vRemoveEffect2.removeAllElements();
            }

            for (int i = Effect2.vEffect2.size() - 1; i >= 0; --i) {
                Effect2 effect = (Effect2) Effect2.vEffect2.elementAt(i);
                if (effect instanceof ChatPopup) {
                    effect.update();
                } else {
                    Effect2.vEffect2.removeElementAt(i);
                }
            }

            for (int i = Effect2.vEffect2Outside.size() - 1; i >= 0; --i) {
                Effect2 effect = (Effect2) Effect2.vEffect2Outside.elementAt(i);
                if (effect instanceof ChatPopup) {
                    effect.update();
                } else {
                    Effect2.vEffect2Outside.removeElementAt(i);
                }
            }

            if (Effect2.vAnimateEffect.size() > 0) {
                Effect2.vAnimateEffect.removeAllElements();
            }
        } catch (Exception e) {
        }
    }

    private static void paintSieuUpMarker(mGraphics graphic) {
        Char me = Char.getMyChar();
        if (me == null) {
            return;
        }

        int x = me.cx;
        int y = me.cy;
        graphic.setColor(16777215);
        graphic.fillRect(x - 4, y - 4, 9, 9);
        graphic.setColor(16711680);
        graphic.fillRect(x - 3, y - 3, 7, 7);
        graphic.setColor(65280);
        graphic.drawLine(x - 7, y, x + 7, y);
        graphic.drawLine(x, y - 7, x, y + 7);
    }

    private static void paintOptimizeNames(mGraphics graphic) {
        try {
            Char me = Char.getMyChar();
            if (me != null) {
                paintOptimizeCharName(graphic, me, true);
            }

            for (int i = 0; i < ah.size(); ++i) {
                Npc npc = (Npc) ah.elementAt(i);
                if (npc != null && npc.template != null && npc.template.name != null && npc.statusMe != 15 && npc.u()) {
                    mFont.tahoma_7_green.writeText(graphic, "[" + npc.template.npcTemplateId + "]" + npc.template.name, npc.cx, npc.cy - npc.bj - 12, 2, mFont.tahoma_7_grey);
                }
            }

            for (int i = 0; i < vCharInMap.size(); ++i) {
                Char c = (Char) vCharInMap.elementAt(i);
                paintOptimizeCharName(graphic, c, false);
            }

            for (int i = 0; i < vParty.size(); ++i) {
                Party party = (Party) vParty.elementAt(i);
                if (party != null && party.f != null && party.f != me) {
                    paintOptimizeCharName(graphic, party.f, false);
                }
            }

            for (int i = 0; i < vMobAttack.size(); ++i) {
                Mob mob = (Mob) vMobAttack.elementAt(i);
                if (isOptimizeMobVisible(mob)) {
                    int height = mob.l > 0 ? mob.l : 24;
                    String name = getOptimizeMobName(mob);
                    if (mob.isBoss || mob.levelBoss == 3) {
                        mFont.tahoma_7_blue1.writeText(graphic, name, mob.curX, mob.curY - height - 16, 2, mFont.tahoma_7_grey);
                    } else {
                        mFont.tahoma_7_yellow.writeText(graphic, name, mob.curX, mob.curY - height - 16, 2, mFont.tahoma_7_grey);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private static String getOptimizeMobName(Mob mob) {
        if (mob == null || Mob.mobTemplates == null || mob.id < 0 || mob.id >= Mob.mobTemplates.length || Mob.mobTemplates[mob.id] == null) {
            return "";
        }

        if (optimizeMobNameCache == null || optimizeMobNameCache.length != Mob.mobTemplates.length) {
            optimizeMobNameCache = new String[Mob.mobTemplates.length];
        }

        String name = optimizeMobNameCache[mob.id];
        if (name == null) {
            name = Mob.mobTemplates[mob.id].name + "(" + mob.id + ")";
            optimizeMobNameCache[mob.id] = name;
        }

        return name;
    }

    private static void paintOptimizeCharName(mGraphics graphic, Char c, boolean isMe) {
        if (c == null || c.charName == null || c.charName.length() == 0 || c.statusMe == 15 || !c.u()) {
            return;
        }

        String name = c.isNhanban ? "PT " + c.charName : c.charName;
        if (isMe) {
            mFont.tahoma_7_yellow.writeText(graphic, name, c.cx, c.cy - c.bj - 16, 2, mFont.tahoma_7_grey);
        } else {
            mFont.tahoma_7_white.writeText(graphic, name, c.cx, c.cy - c.bj - 16, 2, mFont.tahoma_7_grey);
        }
    }

    private static boolean isOptimizeMobVisible(Mob mob) {
        return mob != null
                && mob.hp > 0
                && mob.h != 0
                && mob.curX >= cmx
                && mob.curX <= cmx + gW
                && mob.curY >= cmy
                && mob.curY <= cmy + gH + 30
                && Mob.mobTemplates != null
                && mob.id >= 0
                && mob.id < Mob.mobTemplates.length
                && Mob.mobTemplates[mob.id] != null;
    }

    private static String getSkillNameWithId(SkillTemplate skillTemplate) {
        if (skillTemplate == null) {
            return "";
        }

        int id = skillTemplate.id;
        if (id < 0) {
            id += 256;
        }

        return skillTemplate.name + "(" + id + ")";
    }

    private static void ensureTouchImagesLoaded() {
        if (!GameCanvas.isTouch) {
            return;
        }

        try {
            if (jh == null) {
                jh = GameCanvas.loadImage("/hd/button.png");
            }
            if (ji == null) {
                ji = GameCanvas.loadImage("/hd/button2.png");
            }
            if (jj == null) {
                jj = GameCanvas.loadImage("/hd/hpp.png");
            }
            if (jk == null) {
                jk = GameCanvas.loadImage("/hd/mpp.png");
            }
            if (jl == null) {
                jl = GameCanvas.loadImage("/hd/right.png");
            }
            if (jm == null) {
                jm = GameCanvas.loadImage("/hd/right2.png");
            }
            if (jn == null) {
                jn = GameCanvas.loadImage("/hd/skill.png");
            }
            if (ds == null) {
                ds = GameCanvas.loadImage("/hd/btnl.png");
            }
            if (dt == null) {
                dt = GameCanvas.loadImage("/hd/btnlf.png");
            }
            if (jc == null) {
                jc = GameCanvas.loadImage("/hd/arrow.png");
            }
            if (jd == null) {
                jd = GameCanvas.loadImage("/hd/arrow2.png");
            }
            if (je == null) {
                je = GameCanvas.loadImage("/hd/chat.png");
            }
            if (jg == null) {
                jg = GameCanvas.loadImage("/hd/focus.png");
            }
            if (jf == null) {
                jf = GameCanvas.loadImage("/hd/menu.png");
            }
            if (ja == null) {
                ja = GameCanvas.loadImage("/hd/topbar.png");
            }
            if (jb == null) {
                jb = GameCanvas.loadImage("/hd/transparent.png");
            }
            if (dr == null) {
                dr = GameCanvas.loadImage("/hd/mapborder.png");
            }
        } catch (Exception e) {
        }
    }

    private static void ensureTouchUiReady() {
        ensureTouchImagesLoaded();
        if (!GameCanvas.isTouch) {
            return;
        }

        try {
            if ((kl == 0 && km == 0) || kp <= 0 || kq <= 0 || dxTableSkill == null || dyTableSkill == null || dxTableSkill.length != arrSkill.length) {
                if (Char.getMyChar() != null) {
                    a(false);
                }
            }
        } catch (Exception e) {
        }
    }

    private static void paintMyCharHiddenForLogic(mGraphics graphic) {
        try {
            graphic.setClip(-10000, -10000, 1, 1);
            Char.getMyChar().a(graphic);
        } catch (Exception e) {
        }

        try {
            graphic.setClip(0, -200, GameCanvas.width - graphic.getTranslateX(), 200 + GameCanvas.height - graphic.getTranslateY());
        } catch (Exception e) {
        }
    }

    public final void paint(mGraphics graphic) {
        if (Char.fk) {
            graphic.setColor(0);
            graphic.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
            mFont.tahoma_7b_yellow.writeText(graphic, mResources.ek, GameCanvas.centerX, GameCanvas.centerY + 20, 2);
            GameCanvas.a(GameCanvas.centerX, GameCanvas.centerY, graphic);
        } else {
            ensureTouchUiReady();
            GameCanvas.paint(graphic);
            graphic.translateXY(-cmx, -cmy);

            int var2;
            boolean compactPaint = FormToiUu.isHideAll();
            boolean hideMapLine = FormToiUu.isHideMapLine();
            boolean hideTree = FormToiUu.isHideTree();
            boolean hideCharEffect = FormToiUu.isHideCharEffect();
            if (compactPaint && GameCanvas.gameTick % 250 == 0) {
                FormAutoUp.clearVisualCache();
                FormToiUu.applyRamOptimize(false);
            }

            if (!hideMapLine) {
                if (!hideTree) {
                    for (var2 = 0; var2 < eh.size(); ++var2) {
                        ((ItemTree) eh.elementAt(var2)).a(graphic);
                    }
                }
                TileMap.a(graphic);
            } else {
                graphic.setColor(16711680);
                for (int mx = m; mx < o; ++mx) {
                    for (int my = n; my < p; ++my) {
                        int px = mx * TileMap.size;
                        int py = my * TileMap.size;
                        if (TileMap.a(px + TileMap.size / 2, py + TileMap.size / 2, 2)
                                && !TileMap.a(px + TileMap.size / 2, py - TileMap.size / 2, 2)) {
                            graphic.drawLine(px, py, px + TileMap.size - 1, py);
                        }
                    }
                }
            }

            if (!compactPaint) {
                if (!hideTree) {
                    for (var2 = 0; var2 < ei.size(); ++var2) {
                        ((ItemTree) ei.elementAt(var2)).a(graphic);
                    }
                }

                for (var2 = 0; var2 < vMobAttack.size(); ++var2) {
                    ((Mob) vMobAttack.elementAt(var2)).a(graphic);
                }

                for (var2 = 0; var2 < Mob.aa.size(); ++var2) {
                    EggMonters var3;
                    if ((var3 = (EggMonters) Mob.aa.elementAt(var2)).a()) {
                        graphic.drawRegion(ju, 0, var3.c << 5, 32, 32, 0, var3.a, var3.b, 33);
                    }
                }

                for (var2 = 0; var2 < ai.size(); ++var2) {
                    BuNhin var16;
                    BuNhin var4;
                    if ((var16 = var4 = (BuNhin) ai.elementAt(var2)).a >= cmx && var16.a <= cmx + gW && var16.b >= cmy && var16.b <= cmy + gH + 30) {
                        mFont.tahoma_7_yellow.writeText(graphic, var4.c, var4.a, var4.b - 32, 2, mFont.tahoma_7_grey);
                        SmallImage.paintImage(graphic, 1180, var4.a, var4.b, 0, 33);
                        if (var4.d) {
                            SmallImage.paintImage(graphic, 288, var4.a, var4.b, 0, 33);
                            var4.d = false;
                        }
                    }
                }

                for (var2 = 0; var2 < ah.size(); ++var2) {
                    ((Npc) ah.elementAt(var2)).a(graphic);
                }
            }

            GameScr var18 = this;

            int var5;
            int var6;
            if (!compactPaint) {
                for (var5 = 0; var5 < TileMap.vGo.size(); ++var5) {
                    Waypoint var7;
                    if ((var7 = (Waypoint) TileMap.vGo.elementAt(var5)).b != 0 && var7.d < TileMap.d - 24) {
                        if (var7.c <= TileMap.c / 2) {
                            if (!GameCanvas.isTouch) {
                                SmallImage.paintImage(graphic, 1213, var7.c + 12 + var18.kh, var7.d - 12, 2, StaticObj.g);
                            } else {
                                SmallImage.paintImage(graphic, 1213, var7.c + 12 + var18.kh, var7.d - 32, 2, StaticObj.g);
                            }
                        } else if (var7.a >= TileMap.c / 2) {
                            if (!GameCanvas.isTouch) {
                                SmallImage.paintImage(graphic, 1213, var7.a - 12 - var18.kh, var7.d - 12, 0, StaticObj.g);
                            } else {
                                SmallImage.paintImage(graphic, 1213, var7.a - 12 - var18.kh, var7.d - 32, 0, StaticObj.g);
                            }
                        }
                    } else if (var7.d <= TileMap.d / 2) {
                        var6 = var7.a + (var7.c - var7.a) / 2;
                        var2 = var7.b + (var7.d - var7.b) / 2 + var18.kh;
                        if (GameCanvas.isTouch) {
                            var2 = var7.d + (var7.d - var7.b) + var18.kh + 10;
                        }

                        SmallImage.paintImage(graphic, 1213, var6, var2, 6, StaticObj.g);
                    } else if (var7.b >= TileMap.d / 2) {
                        SmallImage.paintImage(graphic, 1213, var7.a + (var7.c - var7.a) / 2, var7.b - 12 - var18.kh, 4, StaticObj.g);
                    }
                }
            }

            graphic.setClip(0, -200, GameCanvas.width - graphic.getTranslateX(), 200 + GameCanvas.height - graphic.getTranslateY());
            GameCanvas.b().b(graphic);

            if (!compactPaint) {
                for (var2 = 0; var2 < vCharInMap.size(); ++var2) {
                    Char var23 = null;

                    try {
                        var23 = (Char) vCharInMap.elementAt(var2);
                    } catch (Exception var14) {
                    }

                    if (var23 != null) {
                        if (TileMap.mapID == 111 && var2 > 19) {
                            var23.c(graphic);
                        } else {
                            var23.a(graphic);
                        }
                    }
                }

                for (var2 = 0; var2 < vParty.size(); ++var2) {
                    Party var25;
                    if ((var25 = (Party) vParty.elementAt(var2)).f != null && var25.f != Char.getMyChar()) {
                        var25.f.b(graphic);
                    }
                }
            }

            mGraphics var26 = graphic;

            if (!compactPaint) {
                for (var2 = 0; var2 < 5; ++var2) {
                    if (lj[var2] != -1 && GameCanvas.e(lf[var2], lg[var2])) {
                        if (lk[var2] == 0) {
                            mFont.number_red.writeText(var26, le[var2], lf[var2], lg[var2], 2);
                        } else if (lk[var2] == 1) {
                            mFont.number_yellow.writeText(var26, le[var2], lf[var2], lg[var2], 2);
                        } else if (lk[var2] == 2) {
                            mFont.number_green.writeText(var26, le[var2], lf[var2], lg[var2], 2);
                        } else if (lk[var2] == 3) {
                            mFont.tahoma_7b_yellow.writeText(var26, le[var2], lf[var2], lg[var2], 2, mFont.tahoma_7b_blue);
                        } else if (lk[var2] == 8) {
                            mFont.tahoma_7b_white.writeText(var26, le[var2], lf[var2], lg[var2], 2, mFont.tahoma_7b_blue);
                        } else if (lk[var2] == 4) {
                            SmallImage.paintImage(var26, 1062, lf[var2], lg[var2], 0, 3);
                        } else if (lk[var2] == 5) {
                            mFont.number_orange.writeText(var26, le[var2], lf[var2], lg[var2], 2);
                        } else if (lk[var2] == 6) {
                            mFont.tahoma_7_yellow.writeText(var26, le[var2], lf[var2], lg[var2], 2, mFont.tahoma_7_red);
                        } else if (lk[var2] == 7) {
                            SmallImage.paintImage(var26, 655, lf[var2], lg[var2], 0, 3);
                        }
                    }
                }
            }

            var26 = graphic;

            if (!compactPaint) {
                for (var2 = 0; var2 < gi.size(); ++var2) {
                    Lanterns var21 = (Lanterns) gi.elementAt(var2);
                    if (GameCanvas.gameTick % 10 < 8) {
                        SmallImage.paintImage(var26, 1292, var21.a, var21.b, 0, 3);
                    } else {
                        SmallImage.paintImage(var26, 1291, var21.a, var21.b, 0, 3);
                    }
                }
            }

            var26 = graphic;

            if (!compactPaint) {
                for (var2 = 0; var2 < 2; ++var2) {
                    if (ln[var2] != -1) {
                        if (lp[var2] == 1) {
                            var26.drawImage(lq[lo[var2]], ll[var2], lm[var2], 3);
                        } else {
                            var26.drawRegion(lq[lo[var2]], 0, 0, mGraphics.getWidth(lq[lo[var2]]), mGraphics.getHeight(lq[lo[var2]]), 2, ll[var2], lm[var2], 3);
                        }
                    }
                }
            }

            if (compactPaint) {
                if (GameCanvas.isPointerClick || GameCanvas.isPointerJustRelease || GameCanvas.m || GameCanvas.gameTick % 8 == 0) {
                    paintMyCharHiddenForLogic(graphic);
                }
                paintSieuUpMarker(graphic);
                paintOptimizeNames(graphic);
            } else {
                Char.getMyChar().a(graphic);
            }
            graphic = graphic;
            var18 = this;
            if (!compactPaint && Char.getMyChar().isCaptcha) {
                for (var6 = 0; var6 < var18.pl.length; ++var6) {
                    if (var18.pl[var6] != -1) {
                        byte var22 = 0;
                        if (var18.pl[var6] == 0) {
                            var22 = 2;
                        } else if (var18.pl[var6] == 1) {
                            var22 = 6;
                        } else if (var18.pl[var6] == 2) {
                            var22 = 0;
                        }

                        SmallImage.paintImage(graphic, 989, Char.getMyChar().cx + var6 * 10 - (var18.pl.length - 1) * 10 / 2, Char.getMyChar().cy - 40, var22, 3);
                    }
                }
            }

            byte var24;
            if (!compactPaint) {
                for (var2 = 0; var2 < vItemMap.size(); ++var2) {
                    ItemMap var19;
                    if ((var19 = (ItemMap) vItemMap.elementAt(var2)).j != null && var19.j.img != null) {
                        var24 = 0;
                        if (var19.status <= 0) {
                            var24 = var19.status;
                        }

                        graphic.drawImage(var19.j.img, var19.x, var19.y + var24, 33);
                    } else {
                        var24 = 0;
                        if (var19.status <= 0) {
                            var24 = var19.status;
                        }
                        // paintImageAnim
                        SmallImage.paintImageAnim(graphic, var19.template.iconID, var19.x, var19.y + var24, 0, 33);
                        if (Char.getMyChar().itemFocus != null && Char.getMyChar().itemFocus.equals(var19) && var19.status != 2) {
                            // paintImageAnim
                            SmallImage.paintImageAnim(graphic, 988, var19.x, var19.y - 20, 0, 33);
                        }
                    }
                }
            }

            if (!hideCharEffect) {
                for (var2 = 0; var2 < w.size(); ++var2) {
                    ((MobSoul) w.elementAt(var2)).a(graphic);
                }
            }
            if (!compactPaint && !hideMapLine) {
                TileMap.c(graphic);
            }
            if (!compactPaint && Code.m > 0) {
                graphic.setColor(16711680);
                graphic.drawArc(Code.n - Code.m, Code.o - Code.m, Code.m << 1, Code.m << 1, 0, 360);
            }

            if (!compactPaint && Code.khoangCachNhat > 0) {
                graphic.setColor(65280);
                graphic.drawArc(Char.getMyChar().cx - Code.khoangCachNhat, Char.getMyChar().cy - Code.khoangCachNhat, Code.khoangCachNhat << 1, Code.khoangCachNhat << 1, 0, 360);
            }

            if (!hideCharEffect) {
                for (var2 = 0; var2 < Effect2.vEffect2.size(); ++var2) {
                    ((Effect2) Effect2.vEffect2.elementAt(var2)).paint(graphic);
                }
            }

            if (!compactPaint && !hideTree) {
                for (var2 = 0; var2 < eg.size(); ++var2) {
                    ((ItemTree) eg.elementAt(var2)).a(graphic);
                }
            }

            if (!GameCanvas.lowGraphic && !hideCharEffect) {
                for (var2 = 0; var2 < Effect2.vAnimateEffect.size(); ++var2) {
                    ((Effect2) Effect2.vAnimateEffect.elementAt(var2)).paint(graphic);
                }
            }

            graphic = graphic;

            int var20;
            if (!compactPaint) {
                try {
                    if ((var24 = ae()) != -1) {
                        Npc var12 = null;

                        for (var20 = 0; var20 < ah.size(); ++var20) {
                            Npc var13;
                            if ((var13 = (Npc) ah.elementAt(var20)).template.npcTemplateId == var24) {
                                if (var12 == null) {
                                    var12 = var13;
                                } else if (Res.e(var13.cx - Char.getMyChar().cx) < Res.e(var12.cx - Char.getMyChar().cx)) {
                                    var12 = var13;
                                }
                            }
                        }

                        if (var12 != null && var12.statusMe != 15 && (var12.cx <= cmx || var12.cx >= cmx + gW || var12.cy <= cmy || var12.cy >= cmy + gH) && GameCanvas.gameTick % 10 >= 5) {
                            var20 = var12.cx - Char.getMyChar().cx;
                            var5 = var12.cy - Char.getMyChar().cy;
                            var6 = 0;
                            var2 = 0;
                            byte var28 = 0;
                            if (var20 > 0 && var5 >= 0) {
                                if (Res.e(var20) >= Res.e(var5)) {
                                    var6 = gW - 10;
                                    var2 = gH / 2 + 30;
                                    if (GameCanvas.isTouch) {
                                        var2 = gH / 2 + 10;
                                    }

                                    var28 = 0;
                                } else {
                                    var6 = gW / 2;
                                    var2 = gH - 10;
                                    var28 = 5;
                                }
                            } else if (var20 >= 0 && var5 < 0) {
                                if (Res.e(var20) >= Res.e(var5)) {
                                    var6 = gW - 10;
                                    var2 = gH / 2 + 30;
                                    if (GameCanvas.isTouch) {
                                        var2 = gH / 2 + 10;
                                    }

                                    var28 = 0;
                                } else {
                                    var6 = gW / 2;
                                    var2 = 10;
                                    var28 = 6;
                                }
                            }

                            if (var20 < 0 && var5 >= 0) {
                                if (Res.e(var20) >= Res.e(var5)) {
                                    var6 = 10;
                                    var2 = gH / 2 + 30;
                                    if (GameCanvas.isTouch) {
                                        var2 = gH / 2 + 10;
                                    }

                                    var28 = 3;
                                } else {
                                    var6 = gW / 2;
                                    var2 = gH - 10;
                                    var28 = 5;
                                }
                            } else if (var20 <= 0 && var5 < 0) {
                                if (Res.e(var20) >= Res.e(var5)) {
                                    var6 = 10;
                                    var2 = gH / 2 + 30;
                                    if (GameCanvas.isTouch) {
                                        var2 = gH / 2 + 10;
                                    }

                                    var28 = 3;
                                } else {
                                    var6 = gW / 2;
                                    var2 = 10;
                                    var28 = 6;
                                }
                            }

                            resetCursor(graphic);
                            SmallImage.paintImage(graphic, 992, var6, var2, var28, StaticObj.g);
                        }
                    }
                } catch (Exception var15) {
                }
            }

            graphic = graphic;
            long var27;
            if (GameCanvas.isTouch) {
                resetCursor(graphic);
                this.bs();
                if (FormToiUu.isHideStatusBar()) {
                    paintOptimizeStatusInfo(graphic);
                } else {
                    var6 = Char.getMyChar().cHP * ly / Char.getMyChar().cMaxHP;
                    var2 = Char.getMyChar().cMP * lz / Char.getMyChar().cMaxMP;
                    var20 = (int) (Char.getMyChar().ah * (long) ma / exps[Char.getMyChar().cLevel]);
                    if (var6 > ly) {
                        var6 = 0;
                    }

                    graphic.setColor(-10585344);
                    graphic.fillRect(0, lx - 10, ma, 3);
                    graphic.setColor(-10427136);
                    graphic.fillRect(0, lx - 10, var20, 3);
                    graphic.setColor(-9756672);
                    graphic.fillRect(0, lx - 10, ma, 1);
                    graphic.fillRect(0, lx - 7, ma, 1);

                    for (var5 = 0; var5 < 10; ++var5) {
                        graphic.fillRect(var5 * ma / 10 - 1, lx - 10, 1, 3);
                    }

                    graphic.setColor(-1769452);
                    graphic.drawImage(jb, lw - 1, lx, 0);
                    graphic.fillRect(lw, lx, var6, 9);
                    graphic.setColor(-16755227);
                    graphic.drawImage(jb, lw - 28, lx + 13, 0);
                    graphic.fillRect(lw, lx + 16, var2, 7);
                    graphic.drawImage(ja, 0, lx - 7, 0);
                    mFont.number_white.writeText(graphic, "" + Char.getMyChar().cHP, lw + ly / 2 - 30, lx + 1, 0);
                    mFont.number_white.writeText(graphic, "" + Char.getMyChar().cMP, lw + ly / 2 - 30, lx + 15, 0);
                    mFont.tahoma_8b.writeText(graphic, "" + Char.getMyChar().cLevel, lw - 27, lx + 1, 2);
                    if (Char.getMyChar().cExpDown > 0L) {
                        var27 = Char.getMyChar().cExpDown * 10000L / exps[Char.getMyChar().cLevel];
                    } else {
                        var27 = Char.getMyChar().ah * 10000L / exps[Char.getMyChar().cLevel];
                    }
                    mFont.tahoma_7_yellow.writeText(graphic, Res.getCurrentTime().get(11) + ":" + Res.getCurrentTime().get(12) + ":" + Res.getCurrentTime().get(13) + "  X:" + Char.getMyChar().cx + " Y:" + Char.getMyChar().cy, lw + 10, lx + 27, 0, mFont.tahoma_7_red);
                    var2 = (int) (var27 % 100L);
                    mFont.tahoma_7_white.writeText(graphic, (Char.getMyChar().cExpDown > 0L ? "-" + var27 / 100L : "" + var27 / 100L) + "." + (var2 < 10 ? "0" + var2 : String.valueOf(var2)) + "%", lw - 27, lx + 13, 2);
                }
            }

            if (!Char.getMyChar().isCaptcha) {
                if (!compactPaint) {
                    this.g(graphic);
                    resetCursor(graphic);
                    this.f(graphic);
                    resetCursor(graphic);
                    if (!hideMapLine) {
                        TileMap.b(graphic);
                    }
                } else {
                    this.g(graphic);
                    resetCursor(graphic);
                    this.f(graphic);
                    resetCursor(graphic);
                }
                graphic.translateXY(-graphic.getTranslateX(), -graphic.getTranslateY());
                if (compactPaint) {
                    TileMap.paintNsoChenRightInfo(graphic);
                }
                if (GameCanvas.isTouch && (!GameCanvas.isTouch || GameCanvas.isMinWidth240)) {
                    Paint.a(graphic);
                } else {
                    if (FormToiUu.isHideStatusBar()) {
                        paintOptimizeStatusInfo(graphic);
                    } else {
                        graphic.setClip(0, lr - 4, GameCanvas.width, 100);
                        graphic.setColor(3612190);
                        graphic.fillRect(lw - 44, lx, 19, 19);
                        graphic.setColor(265220);
                        graphic.fillRect(lt, lr + 35, lv, 1);
                        graphic.fillRect(lt, lr + 33, lv, 1);
                        graphic.fillRect(lt, lr + 30, lv, 1);
                        graphic.fillRect(lt, lr + 28, lv, 1);
                        graphic.fillRect(lt, lr + 26, lv, 1);
                        graphic.fillRect(lt, lr + 12, lv, 1);
                        graphic.fillRect(lt, lr + 24, lv, 1);
                        graphic.fillRect(lt, lr + 18, lv, 1);
                        graphic.fillRect(lt, lr + 16, lv, 1);
                        graphic.fillRect(lt, lr + 2, lv, 1);
                        graphic.fillRect(lt, lr + 4, lv, 1);
                        graphic.fillRect(lt, lr + 6, lv, 1);
                        graphic.fillRect(lt, lr + 14, lv, 1);
                        graphic.setColor(12562018);
                        graphic.fillRect(lt, lr + 5, lv, 1);
                        graphic.fillRect(lt, lr + 17, lv, 1);
                        graphic.fillRect(lt, lr + 34, lv, 1);
                        graphic.fillRect(lt, lr + 29, lv, 1);
                        graphic.setColor(14667167);
                        graphic.fillRect(lt, lr + 3, lv, 1);
                        graphic.fillRect(lt, lr + 15, lv, 1);
                        graphic.fillRect(lt, lr + 27, lv, 1);
                        graphic.setColor(3355443);
                        graphic.fillRect(lt, lr + 7, lv, 5);
                        graphic.fillRect(lt, lr + 19, lv, 5);
                        graphic.fillRect(lt, lr + 31, lv, 2);
                        graphic.setColor(12281361);
                        graphic.fillRect(lt, lr + 25, lv, 1);
                        graphic.fillRect(lt, lr + 13, lv, 1);
                        graphic.drawImage(mc[0], 0, lr + 2, 0);
                        graphic.drawImage(mc[1], 0 + ls, lr - 4, 24);
                        if ((var6 = Char.getMyChar().cHP * ly / Char.getMyChar().cMaxHP) > ly) {
                            var6 = 0;
                        }

                        graphic.setColor(7798784);
                        graphic.fillRect(lw, lx, var6, 2);
                        graphic.setColor(13369344);
                        graphic.fillRect(lw, lx + 1, var6, 4);
                        if ((var6 = Char.getMyChar().cMP * ly / Char.getMyChar().cMaxMP) > ly) {
                            var6 = 0;
                        }

                        graphic.setColor(4488);
                        graphic.fillRect(lw, lx + 12, var6, 2);
                        graphic.setColor(4573);
                        graphic.fillRect(lw, lx + 14, var6, mb - 2);
                        var2 = (int) (Char.getMyChar().ah * (long) ma / exps[Char.getMyChar().cLevel]);
                        graphic.setColor(94373);
                        graphic.fillRect(46, lr + 31, var2, 1);
                        graphic.setColor(65535);
                        graphic.fillRect(46, lr + 32, var2, 1);
                        mFont.number_yellow.writeText(graphic, "" + Char.getMyChar().cLevel, 28, lr + 9, 2);
                        if (Char.getMyChar().cExpDown > 0L) {
                            var27 = Char.getMyChar().cExpDown * 10000L / exps[Char.getMyChar().cLevel];
                        } else {
                            var27 = Char.getMyChar().ah * 10000L / exps[Char.getMyChar().cLevel];
                        }

                        var6 = (int) (var27 % 100L);
                        mFont.tahoma_7_white.writeText(graphic, (Char.getMyChar().cExpDown > 0L ? "-" + var27 / 100L : "" + var27 / 100L) + "." + (var6 < 10 ? "0" + var6 : String.valueOf(var6)) + "%", 24, lr + 23, 2);
                        mFont.number_green.writeText(graphic, "" + r, ls - 11, lr + 6, 2);
                        mFont.number_green.writeText(graphic, "" + q, ls - 11, lr + 18, 2);
                        mFont.number_white.writeText(graphic, "" + Char.getMyChar().cHP, lw + ly / 2, lr + 6, 2);
                        mFont.number_white.writeText(graphic, "" + Char.getMyChar().cMP, lw + ly / 2, lr + 18, 2);
                        if (Char.getMyChar().vSkillFight.size() > 0 && Char.getMyChar().selectSkill != null) {
                            int var10001 = lt - 28;
                            int var10002 = lx + 7;
                            Char.getMyChar().selectSkill.drawIcon(var10001, var10002, graphic);
                        }

                        graphic.setColor(9463099);
                        graphic.fillRect(0, lr + 35, GameCanvas.width, 1);
                    }
                }

                if (GameCanvas.isTouch) {
                    gz = true;
                }

                resetCursor(graphic);
                this.drawTableSkill(graphic);
                resetCursor(graphic);
                graphic.setClip(0, 0, GameCanvas.width, GameCanvas.height);

                if (!hideCharEffect) {
                    for (var2 = 0; var2 < Effect2.vEffect2Outside.size(); ++var2) {
                        ((Effect2) Effect2.vEffect2Outside.elementAt(var2)).paint(graphic);
                    }
                }

                resetCursor(graphic);
                if (isPaintInfoMe) {
                    if (indexMenu == 0) {
                        this.a(graphic, mResources.nameMenuIndex);
                    }

                    this.n(graphic);
                    ae(graphic);
                    if (indexMenu == 3) {
                        label809:
                        {
                            resetCursor(graphic);
                            Paint.a(popupX, popupY, ew, ex, graphic);
                            if (gk == 1) {
                                graphic.setColor(Paint.COLORDARK);
                                graphic.fillRect(popupX + 7, popupY + 32, ew - 14, ex - 40);
                                graphic.setColor(16777215);
                            } else {
                                graphic.setColor(10249521);
                            }

                            graphic.drawRect(popupX + 7, popupY + 32, ew - 14, ex - 40);
                            drawTitle(graphic, mResources.nameMenuIndex[indexMenu], true);
                            leftMargin = popupX + 17;
                            nw = popupY + 34;
                            scrMain.a(totalRowSetting, 12, popupX, popupY + 35, ew, ex - 44, true, 1);
                            scrMain.a(graphic);
                            String var29;
                            if (jp == 0) {
                                totalRowSetting = 19;
                                var6 = nw;
                                if (currentCharViewInfo == null) {
                                    break label809;
                                }

                                mFont.tahoma_7b_white.writeText(graphic, mResources.labelInfo[0] + currentCharViewInfo.charName, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[1] + currentCharViewInfo.hieuChien;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[2] + currentCharViewInfo.cLevel;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[3] + currentCharViewInfo.nClass.nameClass;
                                var6 += 12;
                                mFont.tahoma_7_blue1.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[4] + mResources.nameSchool[currentCharViewInfo.getSys()];
                                var6 += 12;
                                mFont.tahoma_7_blue1.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[5] + currentCharViewInfo.cHP + "/" + currentCharViewInfo.cMaxHP;
                                var6 += 12;
                                mFont.tahoma_7_red.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[6] + currentCharViewInfo.cMP + "/" + currentCharViewInfo.cMaxMP;
                                var6 += 12;
                                mFont.tahoma_7_blue.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[7] + currentCharViewInfo.getSpeed();
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[8] + (currentCharViewInfo.cDmg - currentCharViewInfo.cDmg / 10) + "-" + currentCharViewInfo.cDmg;
                                var6 += 12;
                                mFont.tahoma_7_yellow.writeText(graphic, var29, leftMargin, var6, 0, mFont.tahoma_7_red);
                                var29 = mResources.labelInfo[9] + currentCharViewInfo.cResFire;
                                var6 += 12;
                                mFont.tahoma_7_red.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[10] + currentCharViewInfo.cResIce;
                                var6 += 12;
                                mFont.tahoma_7_blue.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[11] + currentCharViewInfo.cResWind;
                                var6 += 12;
                                mFont.tahoma_7_green.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[12] + currentCharViewInfo.cGiamDmg;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[13] + currentCharViewInfo.cExactly;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[14] + currentCharViewInfo.cMiss;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                String critRate = currentCharViewInfo.cCritRate > 0 ? "(" + currentCharViewInfo.cCritRate + "%)" : "";
                                var29 = mResources.labelInfo[15] + currentCharViewInfo.cCrit + critRate;
                                var6 += 12;
                                mFont.tahoma_7_yellow.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[16] + currentCharViewInfo.cReactDmg;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[17] + currentCharViewInfo.sysUp;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.labelInfo[18] + currentCharViewInfo.sysDown;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                            } else if (jp == 1) {
                                totalRowSetting = 20;
                                var6 = nw;
                                if (currentCharViewInfo == null) {
                                    break label809;
                                }

                                mFont.tahoma_7b_white.writeText(graphic, mResources.iw[15] + (currentCharViewInfo.cClanName.equals("") ? mResources.hu : currentCharViewInfo.cClanName), leftMargin, var6, 0);
                                var29 = mResources.iw[0] + currentCharViewInfo.ak;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[11] + currentCharViewInfo.aw + "/20";
                                var6 += 12;
                                mFont.tahoma_7_blue1.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[12] + currentCharViewInfo.ax + mResources.iu;
                                var6 += 12;
                                mFont.tahoma_7_blue1.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[16] + currentCharViewInfo.bc + mResources.iu;
                                var6 += 12;
                                mFont.tahoma_7_blue1.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[13] + currentCharViewInfo.ay;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[14] + currentCharViewInfo.az;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[18] + currentCharViewInfo.ba + mResources.iu;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[19] + currentCharViewInfo.bb + mResources.iu;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[17] + currentCharViewInfo.av;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[1] + currentCharViewInfo.am;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[2] + currentCharViewInfo.ao;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[3] + currentCharViewInfo.aq;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[4] + currentCharViewInfo.as;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[5] + currentCharViewInfo.au;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[6] + currentCharViewInfo.al;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[7] + currentCharViewInfo.an;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[8] + currentCharViewInfo.ap;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[9] + currentCharViewInfo.ar;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                                var29 = mResources.iw[10] + currentCharViewInfo.at;
                                var6 += 12;
                                mFont.tahoma_7_white.writeText(graphic, var29, leftMargin, var6, 0);
                            }

                            if (gk == 1 && indexRow >= 0) {
                                SmallImage.paintImage(graphic, 942, leftMargin - 8, nw + 2 + indexRow * 12, 0, StaticObj.b);
                            }
                        }
                    }

                    this.paintTabEquipment(graphic);
                    this.paintTabMount(graphic);
                    this.paintTabBijuu(graphic);
                } else if (z()) {
                    if (ha) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.gi, arrItemStore);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.gi, arrItemBook);
                        } else if (indexMenu == 2) {
                            this.a(graphic, mResources.gi, arrItemFashion);
                        } else if (indexMenu == 3) {
                            this.a(graphic, mResources.gi, arrItemClanShop);
                        }
                    }

                    if (hc) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemNonNam);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fp);
                        }
                    }

                    if (hd) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemNonNu);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fp);
                        }
                    }

                    if (he) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemAoNam);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fq);
                        }
                    }

                    if (hf) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemAoNu);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fq);
                        }
                    }

                    if (hg) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemGangTayNam);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fr);
                        }
                    }

                    if (hh) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemGangTayNu);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fr);
                        }
                    }

                    if (hi) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemQuanNam);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fs);
                        }
                    }

                    if (hj) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fs, arrItemQuanNu);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fs);
                        }
                    }

                    if (hk) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemGiayNam);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.ft);
                        }
                    }

                    if (hl) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fo, arrItemGiayNu);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.ft);
                        }
                    }

                    if (hm) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fu, arrItemLien);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fu);
                        }
                    }

                    if (hn) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fv, arrItemNhan);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fv);
                        }
                    }

                    if (ho) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fw, arrItemNgocBoi);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fw);
                        }
                    }

                    if (hp) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fx, arrItemPhu);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fx);
                        }
                    }

                    if (hq) {
                        if (indexMenu == 0) {
                            this.a(graphic, mResources.fy, arrItemWeapon);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.fy);
                        }
                    }

                    if (hb) {
                        this.a(graphic, mResources.gj, arrItemElites);
                    }

                    this.o(graphic);
                    this.p(graphic);
                    this.q(graphic);
                    this.r(graphic);
                    this.s(graphic);
                    if (hv) {
                        if (indexMenu == 0) {
                            this.b(graphic, mResources.gd);
                        } else if (indexMenu == 1) {
                            this.a(graphic, mResources.gd);
                        }
                    }

                    this.t(graphic);
                    aj(graphic);
                    this.u(graphic);
                    this.v(graphic);
                    this.w(graphic);
                    this.x(graphic);
                    this.z(graphic);
                    this.aa(graphic);
                    this.paintTabBox(graphic);
                    this.y(graphic);
                    this.ar(graphic);
                    this.as(graphic);
                    this.av(graphic);
                    this.at(graphic);
                    this.au(graphic);
                    this.showMenuPickItem(graphic);
                    this.showMenuItemOther(graphic);
                } else if (showZoneDialog) {
                    this.af(graphic);
                }

                drawAutoPanel(graphic);
                this.al(graphic);
                this.e(graphic);
                ai(graphic);
                ah(graphic);
                ak(graphic);
                d(graphic);
                this.l(graphic);
                this.writeItemInfo(graphic);
                ao(graphic);
                resetCursor(graphic);
                if (GameCanvas.isTouch && GameCanvas.width >= 320) {
                    if (super.left != null && super.left != this.jz) {
                        super.left.x = GameCanvas.width / 2 - 160;
                        super.left.y = GameCanvas.height - 26;
                    }

                    if (super.center != null) {
                        super.center.x = GameCanvas.width / 2 - 35;
                        super.center.y = GameCanvas.height - 26;
                    }

                    if (super.right != null && super.right != this.gf) {
                        super.right.x = GameCanvas.width / 2 + 88;
                        super.right.y = GameCanvas.height - 26;
                    }
                }
            }

            super.paint(graphic);
            if (GameCanvas.isTouch && GameCanvas.isMinWidth240) {
                this.h(graphic);
                gz = true;
            }

            resetCursor(graphic);
            this.ag(graphic);
            resetCursor(graphic);
            this.ap(graphic);
            resetCursor(graphic);
            Info.a(graphic);
            resetCursor(graphic);
            ChatTextField.a().a(graphic);
            resetCursor(graphic);
            InfoMe.a(graphic);
        }

    }

    private static void drawAutoPanel(mGraphics mgraphics) {
        if (showAutoPanel) {
            resetCursor(mgraphics);
            Paint.a(popupX, popupY, ew, ex, mgraphics);
            if (gk == 1) {
                mgraphics.setColor(Paint.COLORDARK);
                mgraphics.fillRect(popupX + 7, popupY + 32, ew - 14, ex - 55);
                mgraphics.setColor(16777215);
            } else {
                mgraphics.setColor(10249521);
            }

            mgraphics.drawRect(popupX + 7, popupY + 32, ew - 14, ex - 55);
            drawTitle(mgraphics, mResources.nw[7], false);
            leftMargin = popupX + 17;
            nw = popupY + 45;
            totalRowSetting = mResources.labelSettingAuto.length;
            scrMain.a(totalRowSetting, 30, popupX, popupY + 39, ew, ex - 63, true, 1);
            scrMain.a(mgraphics);
            int mgTopAuto = 5;
            for (int i = 0; i < totalRowSetting; i++) {
                dyRowAuto[i] = mgTopAuto;
                mgTopAuto += 30;
            }
            int var1 = nw;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[0], Char.tickAutoUseHP, Char.aHpValue + "%", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[1], Char.tickAutoUseMP, Char.aMpValue + "%", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[2], Char.tickAutoFood, String.valueOf(Char.aFoodValue), leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[3], Char.tickAutoBuff, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[4], Char.dl, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[5], Char.dm, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[6], Char.dk, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[7], Char.dn, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[8], Char.doa, "LV: " + Char.ev, leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[9], Char.tickNhatDa, "LV: " + Char.ew, leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[10], Char.tickLuyenDaMax, "LV: " + Char.ex, leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[11], Char.tickNhatTrangBi, "LV: " + Char.ey, leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[12], Char.tickNhatTrangBiLa, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[13], Char.tickNhatVPNhiemVu, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[14], Char.tickNhatVPSK, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[15], Char.tickNhatTatCa, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[16], Char.tickNhatSVC, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[17], Char.tickKhongNhat, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[18], Char.tickAutoTTT, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[19], Char.tickAutoTTC, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[20], Char.tickReMap, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[21], Char.tickTSMapEmpty, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[22], Char.tickAutoMuaTA, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[23], Char.tickDieKhiHetMP, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[24], Char.tickAutoReconnect, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[25], Char.tickChuyenMapHetBoss, Code.thoiGianChoChuyenKhu + "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[26], Char.tickSanTATL, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[27], Char.tickDanhQuaiThuong, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[28], Char.tickDanhTinhAnh, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[29], Char.tickDanhThuLinh, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[30], Char.tickCongTiemNang, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[31], Char.tickCongKyNang, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[32], Char.tickDanhTheoNhom, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[33], Char.tickNePK, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[34], Char.tickAutoCoLenh, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[35], Char.tickAutoMuaCoLenh, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[36], Char.tickAutoLangThuyenThuyet, Char.idTTL + "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[37], Char.tickAutoMuaTruyenThuyetLenh, Char.indexBuyTTL + "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[38], Code.tbNhanExp, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[39], Code.tbNhanVP, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[40], true, Code.speedTinhLuyen + "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[41], Char.tickAutoLocDo, "", leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[42], Char.tickAutoTTGT, String.valueOf(Char.ttgtOption), leftMargin, var1);
            var1 += 30;
            paintSettingAuto(mgraphics, mResources.labelSettingAuto[43], SelectCharScr.isQuickLogin(), "", leftMargin, var1);
            var1 += 30;
            if (gk == 1 && indexRow >= 0 && !GameCanvas.isTouch) {
                SmallImage.paintImage(mgraphics, 942, leftMargin - 8, nw + 2 + indexRow * 30, 0, StaticObj.b);
            }

            resetCursor(mgraphics);
            mFont.tahoma_7_green.writeText(mgraphics, GameCanvas.isTouch ? mResources.rg : mResources.rf, popupX + ew / 2, popupY + ex - 17, 2);
        }

    }

    private static void paintSettingAuto(mGraphics var0, String var1, boolean var2, String var3, int var4, int var5) {
        var0.setColor(16777215);
        var0.fillRect(var4, var5, 12, 12);
        if (var2) {
            var0.setColor(9650442);
            var0.drawLine(var4 + 2, var5 + 2, var4 + 2 + 7, var5 + 2 + 7);
            var0.drawLine(var4 + 2, var5 + 2 + 7, var4 + 2 + 7, var5 + 2);
        }

        mFont var6;
        (var6 = var2 ? mFont.tahoma_7_white : mFont.tahoma_7_grey).writeText(var0, var1, var4 + 18, var5, 0);
        if (!var3.equals("")) {
            var0.setColor(Paint.b);
            var0.fillRect(var4 + 115, var5 - 3, 30, 20);
            var0.setColor(var2 ? 16777215 : 0);
            var0.drawRect(var4 + 115, var5 - 3, 30, 20);
            var6.writeText(var0, var3, var4 + 133, var5 + 2, 2);
        }

    }

    private static void d(mGraphics var0) {
        if (gx) {
            Paint.a(popupX, popupY, ew, ex, var0);
            drawTitle(var0, mResources.dk, false);
            leftMargin = popupX + 5;
            nw = popupY + 40;
            if (ab.size() == 0) {
                mFont.tahoma_7_white.writeText(var0, mResources.nt, popupX + ew / 2, popupY + 40, 2);
                return;
            }

            var0.setColor(-16770791);
            var0.fillRect(leftMargin - 2, nw - 2, ew - 6, wItem * 5 + 8);
            resetCursor(var0);
            scrMain.a(ab.size(), wItem, leftMargin, nw, ew - 3, wItem * 5 + 4, true, 1);
            scrMain.a(var0, leftMargin, nw, ew - 3, wItem * 5 + 6);
            totalRowSetting = ab.size();

            for (int var1 = 0; var1 < ab.size(); ++var1) {
                DunItem var2 = null;

                try {
                    var2 = (DunItem) ab.elementAt(var1);
                } catch (Exception var4) {
                }

                if (var2 != null) {
                    if (indexRow == var1) {
                        var0.setColor(Paint.b);
                        var0.fillRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                        var0.setColor(16777215);
                        var0.drawRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                    } else {
                        var0.setColor(Paint.a);
                        var0.fillRect(leftMargin + 2, nw + var1 * wItem + 2, ew - 15, wItem - 4);
                        var0.setColor(13932896);
                        var0.drawRect(leftMargin + 2, nw + var1 * wItem + 2, ew - 15, wItem - 4);
                    }

                    mFont.tahoma_7_yellow.writeText(var0, var2.b, leftMargin + (ew - 10) / 2 - ew / 4, nw + var1 * wItem + wItem / 2 - 6, 2);
                    mFont.tahoma_7b_red.writeText(var0, " vs ", leftMargin + (ew - 10) / 2, nw + var1 * wItem + wItem / 2 - 6, 2);
                    mFont.tahoma_7_yellow.writeText(var0, var2.c, leftMargin + (ew - 10) / 2 + ew / 4, nw + var1 * wItem + wItem / 2 - 6, 2);
                }
            }

            m(var0);
        }

    }

    private void e(mGraphics var1) {
        if (ib) {
            Paint.a(popupX, popupY, ew, ex, var1);
            drawTitle(var1, mResources.dk, false);
            leftMargin = popupX + 5;
            nw = popupY + 40;
            if (vCharInMap.size() == 0) {
                mFont.tahoma_7_white.writeText(var1, mResources.ll, popupX + ew / 2, popupY + 40, 2);
                return;
            }

            var1.setColor(-16770791);
            var1.fillRect(leftMargin - 2, nw - 2, ew - 6, wItem * 5 + 8);
            resetCursor(var1);
            scrMain.a(vCharInMap.size(), wItem, leftMargin, nw, ew - 3, wItem * 5 + 4, true, 1);
            scrMain.a(var1, leftMargin, nw, ew - 3, wItem * 5 + 6);
            totalRowSetting = vCharInMap.size();

            for (int var2 = 0; var2 < vCharInMap.size(); ++var2) {
                Char var3 = null;

                try {
                    if ((var3 = (Char) vCharInMap.elementAt(var2)).isNhanban) {
                        continue;
                    }
                } catch (Exception var5) {
                }

                if (var3 != null) {
                    if (indexRow == var2) {
                        var1.setColor(Paint.b);
                        var1.fillRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                        var1.setColor(16777215);
                        var1.drawRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                    } else {
                        var1.setColor(Paint.a);
                        var1.fillRect(leftMargin + 2, nw + var2 * wItem + 2, ew - 15, wItem - 4);
                        var1.setColor(13932896);
                        var1.drawRect(leftMargin + 2, nw + var2 * wItem + 2, ew - 15, wItem - 4);
                    }

                    SmallImage.paintImage(var1, 647, leftMargin + 12, nw + var2 * wItem + wItem / 2, 0, 3);
                    if (this.de > 0 && this.de == var3.charID) {
                        mFont.tahoma_7_yellow.writeText(var1, var3.charName + " - " + mResources.ec + ": " + var3.cLevel, leftMargin + 22, nw + var2 * wItem + wItem / 2 - 6, 0);
                    } else if (var3.statusMe == 14) {
                        mFont.tahoma_7_grey.writeText(var1, var3.charName + " - " + mResources.ec + ": " + var3.cLevel, leftMargin + 22, nw + var2 * wItem + wItem / 2 - 6, 0);
                    } else {
                        mFont.tahoma_7_green.writeText(var1, var3.charName + " - " + mResources.ec + ": " + var3.cLevel, leftMargin + 22, nw + var2 * wItem + wItem / 2 - 6, 0);
                    }
                }
            }

            m(var1);
        }

    }

    private void f(mGraphics var1) {
        try {
            int var2 = (int) (System.currentTimeMillis() / 1000L);
            int var3 = 5;
            if (GameCanvas.isTouch && GameCanvas.isMinWidth240) {
                var3 = 45 + Info.a;
            }

            if (GameCanvas.isMaxWidth320 && Char.getMyChar().vSkillFight.size() > 4) {
                var3 += 25;
            }

            resetCursor(var1);
            if (!GameCanvas.isMinWidth320) {
                int var4;
                for (int var5 = 0; var5 < Char.getMyChar().vEff.size(); ++var5) {
                    Effect var6 = (Effect) Char.getMyChar().vEff.elementAt(var5);
                    SmallImage.paintImage(var1, var6.e.c, GameCanvas.width - 13 - (var5 * 13 << 1), var3 + 14, 0, 33);
                    var4 = var2 - var6.b;
                    int var7 = var6.c - (var2 - var6.b);
                    var4 = var6.c - var4;
                    if (var7 >= 0) {
                        mFont.tahoma_7_white.writeText(var1, NinjaUtil.b(var4), GameCanvas.width - 13 - (var5 * 13 << 1), var3 + 15, 2, mFont.tahoma_7_grey);
                    }
                }

                var4 = this.dy - (var2 - this.dz);
                if (Char.getMyChar().vEff.size() > 0) {
                    var3 += 27;
                }

                if (var4 > 0) {
                    mFont.tahoma_7_white.writeText(var1, mResources.dl + ": " + NinjaUtil.b(var4), GameCanvas.width - 2, var3, 1, mFont.tahoma_7_grey);
                    var3 += 12;
                }

                if (TileMap.typeMap == 1) {
                    mFont.tahoma_7_white.writeText(var1, mResources.dm + ": " + Char.getMyChar().dc, GameCanvas.width - 2, var3, 1, mFont.tahoma_7_grey);
                    var3 += 12;
                    mFont.tahoma_7_white.writeText(var1, mResources.dn + ": " + Char.getMyChar().dd, GameCanvas.width - 2, var3, 1, mFont.tahoma_7_grey);
                    var3 += 12;
                } else if (TileMap.typeMap != 2 && TileMap.mapID != 114 && TileMap.mapID != 115 && TileMap.mapID != 116) {
                    if (TileMap.typeMap == 3) {
                        mFont.tahoma_7_white.writeText(var1, mResources.doa + ": " + Char.fc, GameCanvas.width - 2, var3, 1, mFont.tahoma_7_grey);
                        var3 += 12;
                        mFont.tahoma_7_white.writeText(var1, mResources.iv[Char.ac()], GameCanvas.width - 2, var3, 1, mFont.tahoma_7_grey);
                        var3 += 12;
                    }
                } else {
                    mFont.tahoma_7_white.writeText(var1, mResources.doa + ": " + Char.fb, GameCanvas.width - 2, var3, 1, mFont.tahoma_7_grey);
                    var3 += 12;
                }
            }

            if (vParty.size() > 0 && GameCanvas.width > 128 && !z()) {
                var3 -= 18;

                for (var2 = 0; var2 < vParty.size(); ++var2) {
                    Party var9;
                    if ((var9 = (Party) vParty.elementAt(var2)).f != null) {
                        String var10 = var9.d + "(" + var9.f.cLevel + ")";
                        int var11 = GameCanvas.width - 14;
                        var3 += 18;
                        mFont.tahoma_7_white.writeText(var1, var10, var11, var3, 1, mFont.tahoma_7_grey);
                        var9.f.a(var1, GameCanvas.width - 41, var3 + 12);
                        SmallImage.paintImage(var1, var9.c, GameCanvas.width - 7, var3 + 9, 0, 3);
                    } else {
                        var3 += 16;
                        mFont.tahoma_7_green.writeText(var1, var9.d, GameCanvas.width - 14, var3 + 5, 1, mFont.tahoma_7_grey);
                        SmallImage.paintImage(var1, var9.c, GameCanvas.width - 7, var3 + 11, 0, 3);
                    }
                }

                return;
            }
        } catch (Exception var8) {
        }

    }

    public static void resetCursor(mGraphics var0) {
        var0.translateXY(-var0.getTranslateX(), -var0.getTranslateY());
        var0.setClip(0, -200, GameCanvas.width, 200 + GameCanvas.height);
    }

    private void g(mGraphics var1) {
        try {
            if (!GameCanvas.menu.showMenu && !InfoDlg.e && !cf()) {
                int var2 = -7;
                int var3 = 3;
                if ((!GameCanvas.isTouch || GameCanvas.isTouch && !GameCanvas.isMinWidth240) && gz) {
                    var3 += 30;
                }

                if (GameCanvas.isTouch) {
                    var2 = 38 + Info.a;
                    if (GameCanvas.isMaxWidth320) {
                        var2 += 35;
                        var2 += 30;
                    }
                }

                var1.translateXY(-var1.getTranslateX(), -var1.getTranslateY());
                int var10;
                String var11;
                if (GameCanvas.isMinWidth320) {
                    int var5 = (int) (System.currentTimeMillis() / 1000L);

                    for (int var6 = 0; var6 < Char.getMyChar().vEff.size(); ++var6) {
                        Effect var4 = (Effect) Char.getMyChar().vEff.elementAt(var6);
                        SmallImage.paintImage(var1, var4.e.c, var3 + 13 + (var6 * 13 << 1), var2 + 27, 0, 33);
                        int var7 = var5 - var4.b;
                        int var8 = var4.c - (var5 - var4.b);
                        var10 = var4.c - var7;
                        if (var8 >= 0) {
                            mFont.tahoma_7_white.writeText(var1, NinjaUtil.b(var10), var3 + 13 + (var6 * 13 << 1), var2 + 28, 2, mFont.tahoma_7_grey);
                        }
                    }

                    var10 = this.dy - (var5 - this.dz);
                    if (Char.getMyChar().vEff.size() > 0) {
                        var2 += 27;
                    }

                    if (var10 > 0) {
                        var11 = mResources.dl + ": " + NinjaUtil.b(var10);
                        var2 += 12;
                        mFont.tahoma_7_white.writeText(var1, var11, var3, var2, 0, mFont.tahoma_7_grey);
                    }
                }

                String var12;
                if (Char.getMyChar().cLevel <= 20) {
                    if (Char.getMyChar().ai > 0) {
                        var12 = "+" + Char.getMyChar().ai + " " + mResources.dg;
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, var12, var3, var2, 0, mFont.tahoma_7_grey);
                    }

                    if (Char.getMyChar().aj > 0) {
                        var12 = "+" + Char.getMyChar().aj + " " + mResources.dh;
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, var12, var3, var2, 0, mFont.tahoma_7_grey);
                    }
                }

                if (ChatManager.getInstance().g.size() > 0) {
                    this.jq[0] = var3;
                    this.jr[0] = var2 + 12;
                    var12 = "+" + ChatManager.getInstance().g.size() + " " + mResources.db;
                    if (GameCanvas.gameTick % 10 > 4) {
                        var2 += 12;
                        mFont.tahoma_7_red.writeText(var1, var12, var3, var2, 0, mFont.tahoma_7_grey);
                    } else {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, var12, var3, var2, 0, mFont.tahoma_7_grey);
                    }
                }

                if (ChatManager.e || ChatManager.f) {
                    var12 = "";
                    this.jq[1] = var3;
                    this.jr[1] = var2 + 12;
                    if (ChatManager.e && ChatManager.f) {
                        var12 = mResources.dc[0];
                    } else if (ChatManager.e) {
                        var12 = mResources.dc[1];
                    } else if (ChatManager.f) {
                        var12 = mResources.dc[2];
                    }

                    if (GameCanvas.gameTick % 10 > 7) {
                        var2 += 12;
                        mFont.tahoma_7_red.writeText(var1, var12, var3, var2, 0, mFont.tahoma_7_grey);
                    } else {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, var12, var3, var2, 0, mFont.tahoma_7_grey);
                    }
                }

                if (TileMap.typeMap == 3) {
                    if (Char.getMyChar().charFocus != null) {
                        if (Char.getMyChar().charFocus.cTypePk == 4) {
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, mResources.ie, var3, var2, 0, mFont.tahoma_7_grey);
                        } else if (Char.getMyChar().charFocus.cTypePk == 5) {
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, mResources.ifa, var3, var2, 0, mFont.tahoma_7_grey);
                        }
                    } else if (Char.getMyChar().mobFocus != null) {
                        if (Char.getMyChar().mobFocus.id == 96) {
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, mResources.ifa, var3, var2, 0, mFont.tahoma_7_grey);
                        } else if (Char.getMyChar().mobFocus.id == 97) {
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, mResources.ie, var3, var2, 0, mFont.tahoma_7_grey);
                        } else {
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, mResources.ig, var3, var2, 0, mFont.tahoma_7_grey);
                        }
                    }
                } else if (Char.getMyChar().isHuman) {
                    if (Char.getMyChar().taskMaint == null) {
                        byte var13;
                        if ((var13 = ad()) >= 0) {
                            var11 = mResources.id + " " + TileMap.mapNames[var13];
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, var11, var3, var2, 0, mFont.tahoma_7_grey);
                        }
                    } else {
                        var12 = Char.getMyChar().taskMaint.e[Char.getMyChar().taskMaint.a];

                        for (var10 = 0; var12 == null; var12 = Char.getMyChar().taskMaint.e[Char.getMyChar().taskMaint.a - var10]) {
                            ++var10;
                        }

                        if (Char.getMyChar().taskMaint.b[Char.getMyChar().taskMaint.a] != -1) {
                            var12 = var12 + " " + Char.getMyChar().taskMaint.f + "/" + Char.getMyChar().taskMaint.b[Char.getMyChar().taskMaint.a];
                        }

                        if (GameCanvas.v > 0 && GameCanvas.gameTick % 10 > 4) {
                            var2 += 12;
                            mFont.tahoma_7_yellow.writeText(var1, var12, var3, var2, 0, mFont.tahoma_7_grey);
                        } else {
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, var12, var3, var2, 0, mFont.tahoma_7_grey);
                        }
                    }
                }
                // write current auto
                String autoScheduleText = getAutoScheduleText1();
                if (autoScheduleText.length() > 0) {
                    var2 += 12;
                    mFont.tahoma_7_yellow.writeText(var1, autoScheduleText, var3, var2, 0, mFont.tahoma_7_yellow);
                }
                autoScheduleText = getAutoScheduleText2();
                if (autoScheduleText.length() > 0) {
                    var2 += 12;
                    mFont.tahoma_7_yellow.writeText(var1, autoScheduleText, var3, var2, 0, mFont.tahoma_7_yellow);
                }
                if (Code.auto != null) {
                    String autoText = Code.auto.toString();
                    if (GameCanvas.gameTick % 10 > 4) {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, autoText, var3, var2, 0, mFont.tahoma_7_red);
                    } else {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, autoText, var3, var2, 0, mFont.tahoma_7_yellow);
                    }
                    paintQuickStopAutoButton(var1, var3, var2, autoText);
                    String stuckText = Code.auto.getStuckStatusText();
                    if (stuckText != null) {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, stuckText, var3, var2, 0, mFont.tahoma_7_red);
                    }
                    if (AutoUp.hasRunningAutoUp(Code.auto) && FormAutoUp.hienYenUp) {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, AutoUp.getEarnedYenText(Code.auto), var3, var2, 0, mFont.tahoma_7_yellow);
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, AutoUp.getEarnedExpText(Code.auto), var3, var2, 0, mFont.tahoma_7_yellow);
                        if (AutoUp.shouldShowEarnedLuong(Code.auto)) {
                            var2 += 12;
                            mFont.tahoma_7_yellow.writeText(var1, AutoUp.getEarnedLuongText(Code.auto), var3, var2, 0, mFont.tahoma_7_yellow);
                        }
                    }
                }
                if (AutoTinhLuyen.isRunning()) {
                    String autoText = AutoTinhLuyen.getAutoText();
                    if (GameCanvas.gameTick % 10 > 4) {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, autoText, var3, var2, 0, mFont.tahoma_7_red);
                    } else {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, autoText, var3, var2, 0, mFont.tahoma_7_yellow);
                    }
                    if (Code.auto == null) {
                        paintQuickStopAutoButton(var1, var3, var2, autoText);
                    }
                } else if (Code.auto == null && AutoBiKip.isRunning()) {
                    String autoText = AutoBiKip.getAutoText();
                    if (GameCanvas.gameTick % 10 > 4) {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, autoText, var3, var2, 0, mFont.tahoma_7_red);
                    } else {
                        var2 += 12;
                        mFont.tahoma_7_yellow.writeText(var1, autoText, var3, var2, 0, mFont.tahoma_7_yellow);
                    }
                    paintQuickStopAutoButton(var1, var3, var2, autoText);
                }
                // write hp focus
                if (Char.getMyChar().mobFocus != null) {
                    MobTemplate var14 = Char.getMyChar().mobFocus.getMobTemplate();
                    var11 = "[" + Char.getMyChar().mobFocus.id + "]" + var14.name + " lv" + Char.getMyChar().mobFocus.lv;
                    if (Char.getMyChar().mobFocus.id != 0 && Char.getMyChar().mobFocus.id != 142 && Char.getMyChar().mobFocus.id != 143) {
                        var11 = var11 + ": " + Char.getMyChar().mobFocus.hp + "/" + Char.getMyChar().mobFocus.maxHp;
                    }

                    var1.setColor(Char.getMyChar().mobFocus.d());
                    var2 += 12;
                    var1.fillRect(var3, var2 + 3, 5, 5);
                    var1.setColor(0);
                    var1.drawRect(var3, var2 + 3, 5, 5);
                    mFont fontNameMob = mFont.tahoma_7_grey;
                    if (Char.getMyChar().mobFocus.sys == 1) {
                        fontNameMob = mFont.tahoma_7_red;
                    }
                    if (Char.getMyChar().mobFocus.sys == 2) {
                        fontNameMob = mFont.tahoma_7_blue;
                    }
                    if (Char.getMyChar().mobFocus.sys == 3) {
                        fontNameMob = mFont.tahoma_7_green;
                    }
                    mFont.tahoma_7_white.writeText(var1, var11, var3 + 12, var2, 0, fontNameMob);
                } else if (Char.getMyChar().npcFocus != null) {
                    var2 += 12;
                    mFont.tahoma_7_yellow.writeText(var1, "[" + Char.getMyChar().npcFocus.template.npcTemplateId + "]" + Char.getMyChar().npcFocus.template.name, var3, var2, 0, mFont.tahoma_7_grey);
                } else if (Char.getMyChar().charFocus != null) {
                    var1.setColor(Char.getMyChar().charFocus.w());
                    var2 += 12;
                    var1.fillRect(var3, var2 + 3, 5, 5);
                    var1.setColor(0);
                    var1.drawRect(var3, var2 + 3, 5, 5);
                    mFont fontNameChar = mFont.tahoma_7_grey;
                    if (Char.getMyChar().charFocus.getSys() == 1) {
                        fontNameChar = mFont.tahoma_7_red;
                    }
                    if (Char.getMyChar().charFocus.getSys() == 2) {
                        fontNameChar = mFont.tahoma_7_blue;
                    }
                    if (Char.getMyChar().charFocus.getSys() == 3) {
                        fontNameChar = mFont.tahoma_7_green;
                    }
                    mFont.tahoma_7_white.writeText(var1, Char.getMyChar().charFocus.charName + " lv" + Char.getMyChar().charFocus.cLevel + ": " + Char.getMyChar().charFocus.cHP + "/" + Char.getMyChar().charFocus.cMaxHP, var3 + 12, var2, 0, fontNameChar);
                }

                if (GameCanvas.isMinWidth320) {
                    if (TileMap.typeMap == 1) {
                        var11 = mResources.dm + ": " + Char.getMyChar().dc;
                        var2 += 12;
                        mFont.tahoma_7_white.writeText(var1, var11, var3, var2, 0, mFont.tahoma_7_grey);
                        var11 = mResources.dn + ": " + Char.getMyChar().dd;
                        var2 += 12;
                        mFont.tahoma_7_white.writeText(var1, var11, var3, var2, 0, mFont.tahoma_7_grey);
                    } else if (TileMap.typeMap != 2 && TileMap.mapID != 114 && TileMap.mapID != 115 && TileMap.mapID != 116) {
                        if (TileMap.typeMap == 3) {
                            var11 = mResources.doa + ": " + Char.fc;
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, var11, var3, var2, 0, mFont.tahoma_7_grey);
                            var11 = mResources.iv[Char.ac()];
                            var2 += 12;
                            mFont.tahoma_7_white.writeText(var1, var11, var3, var2, 0, mFont.tahoma_7_grey);
                        }
                    } else {
                        var11 = mResources.doa + ": " + Char.fb;
                        var2 += 12;
                        mFont.tahoma_7_white.writeText(var1, var11, var3, var2, 0, mFont.tahoma_7_grey);
                    }
                }

                var1.translateXY(-var1.getTranslateX(), -var1.getTranslateY());
                return;
            }
        } catch (Exception var11) {
        }

    }

    private void h(mGraphics var1) {
        if (GameCanvas.isTouch && (!GameCanvas.menu.showMenu || !GameCanvas.isMaxWidth320) && GameCanvas.currentDialog == null && ChatPopup.currentMultilineChatPopup == null && !GameCanvas.menu.showMenu && !cf()) {
            if (FormToiUu.isHideTouchButtons()) {
                this.paintOptimizeButtonBorders(var1);
                return;
            }
            resetCursor(var1);
            if (!ChatTextField.a().isShow) {
                var1.drawImage(je, kl + 17, km + 17, 3);
            }

            if (!this.cg()) {
                if (!ff) {
                    InfoDlg var2 = qg;
                    var1.drawImage((Image) null, var2.a, var2.b, 3);
                    var1.drawImage((Image) null, var2.c, var2.d, 3);
                } else {
                    var1.drawImage(jh, kj, kk, 0);
                    var1.drawRegion(jc, 0, 0, mGraphics.getWidth(jc), mGraphics.getHeight(jc), 3, kj + 15, kk + 16, 3);
                    if (mScreen.fr == 4) {
                        var1.drawImage(ji, kj, kk, 0);
                        var1.drawRegion(jd, 0, 0, mGraphics.getWidth(jc), mGraphics.getHeight(jc), 3, kj + 15, kk + 16, 3);
                    }

                    var1.drawImage(jh, kn, ko, 0);
                    var1.drawRegion(jc, 0, 0, mGraphics.getWidth(jc), mGraphics.getHeight(jc), 0, kn + 17, ko + 16, 3);
                    if (mScreen.fr == 6) {
                        var1.drawImage(ji, kn, ko, 0);
                        var1.drawRegion(jd, 0, 0, mGraphics.getWidth(jc), mGraphics.getHeight(jc), 0, kn + 17, ko + 16, 3);
                    }

                    var1.drawImage(jh, kr, ks, 0);
                    var1.drawRegion(jc, 0, 0, mGraphics.getWidth(jc), mGraphics.getHeight(jc), 7, kr + 17, ks + 14, 3);
                    if (mScreen.fr == 3) {
                        var1.drawImage(ji, kr, ks, 0);
                        var1.drawRegion(jd, 0, 0, mGraphics.getWidth(jc), mGraphics.getHeight(jc), 7, kr + 17, ks + 14, 3);
                    }
                }

                if (Char.getMyChar().ctaskId > 1) {
                    var1.drawImage(jh, kt, ku, 0);
                    if (mScreen.fr == 10) {
                        var1.drawImage(ji, kt, ku, 0);
                    }

                    var1.drawImage(jj, kt + 16, ku + 15, 3);
                    mFont.number_white.writeText(var1, "" + r, kt + 22, ku + 20, 1);
                    var1.drawImage(jh, kv, kw, 0);
                    if (mScreen.fr == 11) {
                        var1.drawImage(ji, kv, kw, 0);
                    }

                    var1.drawImage(jk, kv + 16, kw + 15, 3);
                    mFont.number_white.writeText(var1, "" + q, kv + 22, kw + 20, 1);
                    var1.drawImage(jh, kx, ky, 0);
                    if (mScreen.fr == 13) {
                        var1.drawImage(ji, kx, ky, 0);
                    }

                    var1.drawImage(jg, kx + 16, ky + 16, 3);
                }

                var1.drawImage(jl, kp, kq, 0);
                if (mScreen.fr == 5) {
                    var1.drawImage(jm, kp, kq, 0);
                }
            }
        }

    }

    private void drawTableSkill(mGraphics var1) {
        if (FormToiUu.isHideSkillTable()) {
            paintOptimizeSkillBorders(var1);
            return;
        }
        if (GameCanvas.currentDialog == null && ChatPopup.currentMultilineChatPopup == null && !GameCanvas.menu.showMenu && !cf() && super.center != this.fa && (!GameCanvas.isTouch || Char.getMyChar().vSkill.size() >= 2) && gz) {
            for (int index = 0; index < arrSkill.length; ++index) {
                if (GameCanvas.isMaxWidth320) {
                    if (Info.a > 0) {
                        dyTableSkill[index] = 55 + Info.a;
                    } else {
                        dyTableSkill[index] = 55;
                    }
                }

                if (GameCanvas.isTouch && GameCanvas.isMinWidth240) {
                    var1.drawImage(jn, marginLeftTableSkill + dxTableSkill[index] - 1, dyTableSkill[index] - 1, 0);
                } else {
                    var1.setColor(16764040);
                    var1.drawRect(marginLeftTableSkill + dxTableSkill[index] - 1, dyTableSkill[index] - 1, 25, 25);
                }

                Skill skill = arrSkill[index];
                if (index == this.kf && !z() && GameCanvas.gameTick % 10 > 5) {
                    var1.setColor(16777215);
                    var1.fillRect(marginLeftTableSkill + dxTableSkill[index] + 1, dyTableSkill[index] + 1, 22, 22);
                } else if (!GameCanvas.isTouch) {
                    var1.setColor(0);
                    var1.fillRect(marginLeftTableSkill + dxTableSkill[index], dyTableSkill[index], 24, 24);
                }

                if (skill != null) {
                    if (skill == Char.getMyChar().selectSkill) {
                        var1.setColor(16711680);
                        var1.drawRect(marginLeftTableSkill + dxTableSkill[index] - 1, dyTableSkill[index] - 1, 25, 25);
                    }

                    skill.drawIcon(marginLeftTableSkill + dxTableSkill[index] + 12, dyTableSkill[index] + 12, var1);
                }
            }
            String[] lableShortcuts = TField.isQwerty ? new String[]{"Q", "W", "E"} : new String[]{"7", "8", "9"};
            for (int index = 0; index < keySkill.length; ++index) {
                int marginTopTable2 = -30;
                if (GameCanvas.isMaxWidth320) {
                    marginTopTable2 = 30;
                    if (Info.a > 0) {
                        dyTableSkill[index] = 55 + Info.a;
                    } else {
                        dyTableSkill[index] = 55;
                    }
                }
                Skill skill = keySkill[index];
                if (GameCanvas.isTouch && GameCanvas.isMinWidth240) {
                    var1.drawImage(jn, marginLeftTableSkill + dxTableSkill[index] - 1, dyTableSkill[index] + marginTopTable2 - 1, 0);
                } else {
                    var1.setColor(16764040);
                    var1.drawRect(marginLeftTableSkill + dxTableSkill[index] - 1, dyTableSkill[index] + marginTopTable2 - 1, 25, 25);
                }
                if (skill != null) {
                    if (skill == Char.getMyChar().selectSkill) {
                        var1.setColor(16711680);
                        var1.drawRect(marginLeftTableSkill + dxTableSkill[index] - 1, dyTableSkill[index] + marginTopTable2 - 1, 25, 25);
                    }

                    skill.drawIcon(marginLeftTableSkill + dxTableSkill[index] + 12, dyTableSkill[index] + marginTopTable2 + 12, var1);
                }
                mFont.tahoma_7_yellow.writeText(var1, "" + lableShortcuts[index], marginLeftTableSkill + dxTableSkill[index] + 25, dyTableSkill[index] + marginTopTable2, 1, mFont.tahoma_7_red);
            }
        }

    }

    public static final void a(String var0, int var1, int var2, int var3) {
        int var4 = -1;

        for (int var5 = 0; var5 < 5; ++var5) {
            if (lj[var5] == -1) {
                var4 = var5;
                break;
            }
        }

        if (var4 != -1) {
            lk[var4] = var3;
            le[var4] = var0;
            lf[var4] = var1;
            lg[var4] = var2;
            lh[var4] = 0;
            li[var4] = -2;
            lj[var4] = 0;
        }

    }

    public static final void a(int var0, int var1) {
        gi.addElement(new Lanterns(var0, var1));
    }

    public static final boolean a(int var0, int var1, int var2) {
        int var3 = ln[0] == -1 ? 0 : 1;
        if (ln[var3] != -1) {
            return false;
        } else {
            ln[var3] = 0;
            lp[var3] = var2;
            ll[var3] = var0;
            lm[var3] = var1;
            return true;
        }
    }

    private void br() {
        if (mc == null) {
            mc = new Image[2];

            for (int var1 = 0; var1 < 2; ++var1) {
                mc[var1] = GameCanvas.loadImage("/u/c" + var1 + ".png");
            }
        }

        lt = mGraphics.getWidth(mc[0]);
        lu = mGraphics.getWidth(mc[1]);
        lv = gW - lt - lu + 1;
        lw = 63;
        lx = lr + 7;
        ly = gW - 84 - 30 + 15;
        ma = gW - 44 - 4;
        mb = 5;
        if (GameCanvas.width > 176) {
            lv -= 50;
            ly -= 50;
            ma -= 50;
            lw += 15;
            ly -= 15;
        }

        this.bs();
    }

    private void bs() {
        if (GameCanvas.isTouch) {
            ly = 82;
            lz = 57;
            lw = 52;
            boolean var10000 = GameCanvas.au;
            lx = 10 + Info.a;
            ma = gW - 61;
            if (GameCanvas.isMaxWidth320) {
                kl = gW / 2 - 2;
                km = ki + 50;
            } else {
                this.jz.y = 6 + Info.a;
                kl = gW - 100;
                km = 2 + Info.a;
            }

            int var1 = GameCanvas.width - 60;
            boolean var10001 = GameCanvas.au;
            TileMap.a(var1, Info.a, 60, 42);
        }

    }

    private void paintTabEquipment(mGraphics graphic) {
        if (indexMenu == 4) {
            graphic.translateXY(-graphic.getTranslateX(), -graphic.getTranslateY());
            Paint.a(popupX, popupY, ew, ex, graphic);
            graphic.setColor(Paint.a);
            drawTitle(graphic, showFashion ? mResources.trangBi2 : mResources.nameMenuIndex[indexMenu], true);
            if (currentCharViewInfo.arrItemBody == null) {
                GameCanvas.a(popupX + 90, popupY + 75, graphic);
                mFont.tahoma_7b_white.writeText(graphic, mResources.textLoading, popupX + ew / 2, popupY + 90, 2);
                return;
            }

            graphic.setColor(13606712);
            graphic.drawRect(popupX + 33, popupY + (GameCanvas.isMinWidth320 ? 87 : 34), ew - 67, GameCanvas.isMinWidth320 ? 76 : 128);
            int var2 = wItem - 2;
            int var3 = 0;

            int index;
            int var5;
            int desX;
            for (index = 0; index < 16; ++index) {
                String text0 = showFashion ? mResources.placeHolderFashion[index][0] : mResources.placeHolderEquip[index][0];
                String text1 = "";
                if (showFashion && mResources.placeHolderFashion[index].length > 1) {
                    text1 = mResources.placeHolderFashion[index][1];
                }
                if (!showFashion && mResources.placeHolderEquip[index].length > 1) {
                    text1 = mResources.placeHolderEquip[index][1];
                }
                if (index != 0 && index != 2 && index != 4 && index != 6 && index != 8) {
                    if (index != 1 && index != 3 && index != 5 && index != 7 && index != 9) {
                        if (index == 9 || index == 10 || index == 11 || index == 12 || index == 13 || index == 14 || index == 15) {
                            var5 = popupX + 4 + 1 + var3 * (var2 + 2);
                            desX = popupY + 35 + var2 * 5 + 1;
                            graphic.setColor(0);
                            graphic.fillRect(var5, popupY + 35 + var2 * 5 + 1, var2 - 1, var2 - 1);
                            if (!text1.equals("")) {
                                mFont.tahoma_7_grey.writeText(graphic, text0, var5 + var2 / 2, desX + 2, 2);
                                mFont.tahoma_7_grey.writeText(graphic, text1, var5 + var2 / 2, desX + 2 + 9, 2);
                            } else {
                                mFont.tahoma_7_grey.writeText(graphic, text0, var5 + var2 / 2, desX + 2 + 5, 2);
                            }

                            ++var3;
                        }
                    } else {
                        graphic.setColor(0);
                        graphic.fillRect(popupX + ew - var2 - 4, popupY + 35 + index / 2 * var2 + 1, var2 - 1, var2 - 1);
                        if (!text1.equals("")) {
                            mFont.tahoma_7_grey.writeText(graphic, text0, popupX + ew - var2 / 2 - 4, popupY + 36 + index / 2 * var2 + 2, 2);
                            mFont.tahoma_7_grey.writeText(graphic, text1, popupX + ew - var2 / 2 - 4, popupY + 36 + index / 2 * var2 + 2 + 9, 2);
                        } else {
                            mFont.tahoma_7_grey.writeText(graphic, text0, popupX + ew - var2 / 2 - 4, popupY + 36 + index / 2 * var2 + 2 + 5, 2);
                        }
                    }
                } else {
                    graphic.setColor(0);
                    graphic.fillRect(popupX + 4 + 1, popupY + 35 + index / 2 * var2 + 1, var2 - 1, var2 - 1);
                    if (!text1.equals("")) {
                        mFont.tahoma_7_grey.writeText(graphic, text0, popupX + 7 + 11, popupY + 36 + index / 2 * var2 + 2, 2);
                        mFont.tahoma_7_grey.writeText(graphic, text1, popupX + 7 + 11, popupY + 36 + index / 2 * var2 + 2 + 9, 2);
                    } else {
                        mFont.tahoma_7_grey.writeText(graphic, text0, popupX + 7 + 11, popupY + 36 + index / 2 * var2 + 2 + 5, 2);
                    }
                }
            }

            for (index = 0; index < 16; ++index) {
                Item item;
                if ((item = currentCharViewInfo.arrItemBody[index + (showFashion ? 16 : 0)]) != null) {
                    if (item.eff == null) {
                        item.eff = efs[56];
                    }

                    int desY;
                    int indexUI = item.indexUI - (showFashion ? 16 : 0);
                    boolean isItemSpecial = indexUI >= 10 && indexUI <= 15;
                    boolean isItemHP = indexUI % 2 == 1;
                    boolean isItemMP = indexUI % 2 == 0;
                    if (isItemSpecial) {
                        if (indexUI == 10) {
                            var3 = 1;
                        } else if (indexUI == 11) {
                            var3 = 2;
                        } else if (indexUI == 12) {
                            var3 = 3;
                        } else if (indexUI == 13) {
                            var3 = 4;
                        } else if (indexUI == 14) {
                            var3 = 5;
                        } else if (indexUI == 15) {
                            var3 = 6;
                        }

                        desX = popupX + 2 + 1 + var3 * (var2 + 2) - var2;
                        desY = popupY + 35 + var2 * 5;
                        this.drawItemUI(graphic, item, desX - 2, desY - 1, 0, 1);
                        int tl = item.getTinhLuyen();
                        String upgradeText = "";
                        if (item.upgrade > 0) {
                            upgradeText += "+" + item.upgrade;
                        }
                        if (tl >= 0) {
                            upgradeText += (item.upgrade > 0 ? ",TL" + tl : "TL" + tl);
                        }
                        if (item.upgrade >= 0 || tl >= 0) {
                            mFont.tahoma_7b_white.writeText(graphic, upgradeText, desX + var2 / 2, desY + 27, 2);
                        }
                    } else if (isItemMP) {
                        desX = popupX + 4;
                        desY = popupY + 34 + indexUI / 2 * var2;
                        this.drawItemUI(graphic, item, desX - 1, desY, 0, 1);
                        if (item.upgrade >= 0) {
                            int tl = item.getTinhLuyen();
                            String textTL = tl == -1 ? ",TL_" : ",TL" + tl;
                            String upgradeText = "+" + item.upgrade + textTL;
                            mFont.tahoma_7b_white.writeText(graphic, upgradeText, desX + var2 / 2 + 30, desY + 10, 2);
                        }
                    } else if (isItemHP) {
                        desX = popupX + ew - var2 - 5;
                        desY = popupY + 35 + indexUI / 2 * var2;
                        this.drawItemUI(graphic, item, desX - 1, desY - 1, 0, 1);
                        if (item.upgrade >= 0) {
                            int tl = item.getTinhLuyen();
                            String textTL = tl == -1 ? ",TL_" : ",TL" + tl;
                            String upgradeText = "+" + item.upgrade + textTL;
                            mFont.tahoma_7b_white.writeText(graphic, upgradeText, desX + var2 / 2 - 30, desY + 10, 2);
                        }
                    }

                    if (GameCanvas.gameTick % 4 == 0) {
                        ++item.indexEff;
                        if (item.indexEff >= item.eff.arrEfInfo.length) {
                            item.indexEff = 0;
                        }
                    }
                }
            }

            for (index = 0; index < 16; ++index) {
                if (gk == 1 && index == indexSelect) {
                    if (index != 0 && index != 2 && index != 4 && index != 6 && index != 8) {
                        if (index != 1 && index != 3 && index != 5 && index != 7 && index != 9) {
                            if (index == 9 || index == 10 || index == 11 || index == 12 || index == 13 || index == 14 || index == 15) {
                                if (index == 9) {
                                    var3 = 0;
                                } else if (index == 10) {
                                    var3 = 1;
                                } else if (index == 11) {
                                    var3 = 2;
                                } else if (index == 12) {
                                    var3 = 3;
                                } else if (index == 13) {
                                    var3 = 4;
                                } else if (index == 14) {
                                    var3 = 5;
                                } else if (index == 15) {
                                    var3 = 6;
                                }

                                var5 = popupX + 2 + 1 + var3 * (var2 + 2) - var2;
                                desX = popupY + 35 + var2 * 5;
                                graphic.setColor(16777215);
                                graphic.drawRect(var5 - 1, desX, var2, var2);
                                a(var5 - 2, desX - 1, graphic);
                            }
                        } else {
                            graphic.setColor(16777215);
                            graphic.drawRect(popupX + ew - var2 - 4 - 1, popupY + 35 + index / 2 * var2, var2, var2);
                            a(popupX + ew - var2 - 4 - 2, popupY + 35 + index / 2 * var2 - 1, graphic);
                        }
                    } else {
                        graphic.setColor(16777215);
                        graphic.drawRect(popupX + 4, popupY + 35 + index / 2 * var2, var2, var2);
                        a(popupX + 5 - 2, popupY + 35 + index / 2 * var2 - 1, graphic);
                    }
                }
            }

            index = GameCanvas.isMinWidth320 ? -25 : 16;
            Part var10 = parts[currentCharViewInfo.head];
            Part var12 = parts[currentCharViewInfo.leg];
            Part var8 = parts[currentCharViewInfo.body];
            Part var11 = parts[currentCharViewInfo.weapon];
            if (currentCharViewInfo.arrItemBody != null && currentCharViewInfo.arrItemBody[11] != null) {
                var10 = parts[currentCharViewInfo.arrItemBody[11].template.part];
            }

            label438:
            {
                if (var10.partImages != null && var10.partImages.length >= 8) {
                    var3 = 0;

                    while (true) {
                        if (var3 >= var10.partImages.length) {
                            break label438;
                        }

                        if (var10.partImages[var3] == null || !SmallImage.a(var10.partImages[var3].id)) {
                            Char.getMyChar();
                            break;
                        }

                        ++var3;
                    }
                }

                var10 = Char.b(Char.getMyChar().cgender);
            }

            int[] var13;
            if ((var13 = currentCharViewInfo.v()) != null) {
                if (Char.getMyChar().de == 0) {
                    SmallImage.paintImage(graphic, var13[Char.getMyChar().de], d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][1] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dx - 2, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][2] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dy + 16, 0, 0);
                } else if (Char.getMyChar().de == 1) {
                    SmallImage.paintImage(graphic, var13[Char.getMyChar().de], d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][1] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dx - 9, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][2] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dy + 16, 0, 0);
                } else if (Char.getMyChar().de == 2) {
                    SmallImage.paintImage(graphic, var13[Char.getMyChar().de], d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][1] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dx - 12, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][2] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dy + 16, 0, 0);
                } else {
                    SmallImage.paintImage(graphic, var13[Char.getMyChar().de], d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][1] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dx - 9, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][2] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dy + 16, 0, 0);
                }
            }

            currentCharViewInfo.b(graphic, d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][1] + var8.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][0]].dx + 18, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][2] + var12.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][0]].dy + 5);
            SmallImage.paintImage(graphic, var11.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][3][0]].id, d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][3][1] + var11.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][3][0]].dx, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][3][2] + var11.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][3][0]].dy, 0, 0);
            SmallImage.paintImage(graphic, var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].id, d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][1] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dx, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][2] + var10.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][0][0]].dy, 0, 0);
            SmallImage.paintImage(graphic, var12.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][0]].id, d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][1] + var12.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][0]].dx, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][2] + var12.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][0]].dy, 0, 0);
            SmallImage.paintImage(graphic, var8.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][0]].id, d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][1] + var8.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][0]].dx, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][2] + var8.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][0]].dy, 0, 0);
            currentCharViewInfo.b(graphic, d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][1] + var8.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][0]].dx + 5, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][2] + var12.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][0]].dy + 5);
            currentCharViewInfo.c(graphic, d + Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][1] + var8.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][2][0]].dx + 22, f + index - Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][2] + var12.partImages[Char.fe[currentCharViewInfo.cp1 % 15 < 5 ? 0 : 1][1][0]].dy + 5);
        }

    }

    private void paintTabMount(mGraphics var1) {
        if (indexMenu == 5) {
            var1.translateXY(-var1.getTranslateX(), -var1.getTranslateY());
            Paint.a(popupX, popupY, ew, ex, var1);
            var1.setColor(Paint.a);
            drawTitle(var1, mResources.nameMenuIndex[indexMenu], true);
            resetCursor(var1);
            var1.setColor(0);
            var1.fillRect(popupX + 2, popupY + 31, 171, ex - 34);
            var1.setColor(13606712);
            var1.drawRect(popupX + 3, popupY + 32, 168, ex - 37);
            var1.setColor(Paint.a);
            var1.fillRect(popupX + 4, popupY + 34, 166, ex - 39);
            int indexMount;
            int var3;
            if (currentCharViewInfo.arrItemMounts[4] != null) {
                mFont.tahoma_7b_white.writeText(var1, currentCharViewInfo.arrItemMounts[4].template.name, popupX + 90, nw + 2, 2);
                indexMount = currentCharViewInfo.arrItemMounts[4].sys + 1;

                for (var3 = 0; var3 < indexMount; ++var3) {
                    SmallImage.paintImage(var1, 628, popupX + 90 + var3 * 12 - indexMount * 6, nw + 20, 0, 3);
                }
            } else {
                mFont.tahoma_7b_white.writeText(var1, mResources.tenThuCuoi, popupX + 90, nw + 2, 2);
            }

            for (indexMount = 0; indexMount < currentCharViewInfo.arrItemMounts.length - 1; ++indexMount) {
                if (currentCharViewInfo.arrItemMounts[indexMount] != null) {
                    this.drawItemUI(var1, currentCharViewInfo.arrItemMounts[indexMount], this.js[indexMount], this.jt[indexMount]);
                } else {
                    var1.setColor(6425);
                    var1.fillRect(this.js[indexMount] - 1, this.jt[indexMount] - 1, wItem + 3, wItem + 3);
                    if (indexMount == 0) {
                        if (currentCharViewInfo.isBike()) {
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[22][0], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 - 10, 2);
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[22][1], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 + 2, 2);
                        } else {
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[19][0], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 - 10, 2);
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[19][1], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 + 2, 2);
                        }
                    } else if (indexMount == 1) {
                        if (currentCharViewInfo.isBike()) {
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[20][0], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 - 10, 2);
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[20][1], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 + 2, 2);
                        } else {
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[16][0], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 - 10, 2);
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[16][1], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 + 2, 2);
                        }
                    } else if (indexMount == 2) {
                        if (currentCharViewInfo.isBike()) {
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[21][0], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 - 10, 2);
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[21][1], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 + 2, 2);
                        } else {
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[17][0], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 - 10, 2);
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[17][1], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 + 2, 2);
                        }
                    } else if (indexMount == 3) {
                        if (currentCharViewInfo.isBike()) {
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[23][0], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 - 10, 2);
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[23][1], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 + 2, 2);
                        } else {
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[18][0], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 - 10, 2);
                            mFont.tahoma_7_grey.writeText(var1, mResources.placeHolderEquip[18][1], this.js[indexMount] + wItem / 2, this.jt[indexMount] + wItem / 2 + 2, 2);
                        }
                    }
                }

                if (indexSelect == indexMount && gk == 1 && indexSelect < 4) {
                    var1.setColor(16777215);
                } else {
                    var1.setColor(12281361);
                }

                var1.drawRect(this.js[indexMount], this.jt[indexMount], wItem, wItem);
            }

            indexMount = this.js[0] + wItem + 7;
            var3 = this.jt[0] - 5;
            var1.setColor(6425);
            var1.fillRect(indexMount, var3, 84, 75);
            if (indexSelect == 4) {
                var1.setColor(16777215);
            } else {
                var1.setColor(12281361);
            }

            var1.drawRect(indexMount, var3, 84, 75);
            int var4 = 0;
            int var5 = 0;
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;
            if (currentCharViewInfo.arrItemMounts[4] != null) {
                if (currentCharViewInfo.isBike()) {
                    if (currentCharViewInfo.arrItemMounts[4].template.id == 485) {
                        if (currentCharViewInfo.arrItemMounts[4].sys < 2) {
                            SmallImage.paintImage(var1, 1800, indexMount + 45, var3 + 35, 0, 3);
                        } else {
                            SmallImage.paintImage(var1, 2063, indexMount + 45, var3 + 35, 0, 3);
                        }
                    } else if (currentCharViewInfo.arrItemMounts[4].template.id == 524) {
                        if (currentCharViewInfo.arrItemMounts[4].sys < 2) {
                            SmallImage.paintImage(var1, 2067, indexMount + 45, var3 + 35, 0, 3);
                        } else {
                            SmallImage.paintImage(var1, 2071, indexMount + 45, var3 + 35, 0, 3);
                        }
                    }
                } else if (currentCharViewInfo.isWoft()) {
                    if (currentCharViewInfo.arrItemMounts[4].template.id == 443) {
                        if (currentCharViewInfo.arrItemMounts[4].sys < 2) {
                            if (GameCanvas.gameTick % 20 > 15) {
                                SmallImage.paintImage(var1, 1801, indexMount + 45, var3 + 35, 0, 3);
                            } else {
                                SmallImage.paintImage(var1, 1802, indexMount + 45, var3 + 35, 0, 3);
                            }
                        } else if (GameCanvas.gameTick % 20 > 15) {
                            SmallImage.paintImage(var1, 2080, indexMount + 45, var3 + 35, 0, 3);
                        } else {
                            SmallImage.paintImage(var1, 2081, indexMount + 45, var3 + 35, 0, 3);
                        }
                    } else if (currentCharViewInfo.arrItemMounts[4].template.id == 523) {
                        if (GameCanvas.gameTick % 20 > 15) {
                            SmallImage.paintImage(var1, 2062, indexMount + 45, var3 + 35, 0, 3);
                        } else {
                            SmallImage.paintImage(var1, 2061, indexMount + 45, var3 + 35, 0, 3);
                        }
                    }
                } else if (currentCharViewInfo.isNewMount()) {
                    if (GameCanvas.gameTick % 6 == 0) {
                        currentCharViewInfo.arrItemMounts[4].indexFrame = (currentCharViewInfo.arrItemMounts[4].indexFrame + 1) % 3;
                    }
                    SmallImage.paintImage(var1, currentCharViewInfo.arrItemMounts[4].template.iconID, indexMount + 45, var3 + 35, 0, 3, currentCharViewInfo.arrItemMounts[4].indexFrame);
                }

                if (currentCharViewInfo.arrItemMounts[4].options != null) {
                    for (indexMount = 0; indexMount < currentCharViewInfo.arrItemMounts[4].options.size(); ++indexMount) {
                        ItemOption var9;
                        if ((var9 = (ItemOption) currentCharViewInfo.arrItemMounts[4].options.elementAt(indexMount)).optionTemplate.id == 65) {
                            var4 = var9.param;
                        } else if (var9.optionTemplate.id == 66) {
                            var5 = var9.param;
                        }
                    }
                }

                var6 = var4 * 85 / 1000;
                var7 = var5 * 85 / 1000;
                var8 = currentCharViewInfo.arrItemMounts[4].upgrade + 1;
            }

            indexMount = leftMargin + 5;
            var3 = nw + 112;
            mFont.tahoma_7b_white.writeText(var1, mResources.cap1 + ": ", indexMount, var3, 0);
            mFont.tahoma_7b_white.writeText(var1, String.valueOf(var8), indexMount + 70, var3, 0);
            String var10;
            if (currentCharViewInfo.isBike()) {
                var10 = mResources.dongCo + ": ";
                var3 += 15;
                mFont.tahoma_7b_white.writeText(var1, var10, indexMount, var3, 0);
            } else {
                var10 = mResources.kinhNghiem + ": ";
                var3 += 15;
                mFont.tahoma_7b_white.writeText(var1, var10, indexMount, var3, 0);
            }

            var1.setColor(6425);
            var1.fillRect(indexMount + 70, var3, 85, 14);
            var1.setColor(371981);
            var1.fillRect(indexMount + 70, var3, var6, 14);
            var1.setColor(5131338);
            var1.drawRect(indexMount + 70, var3, 85, 14);
            mFont.tahoma_7_white.writeText(var1, var4 + "/1000", indexMount + 113, var3 + 2, 2);
            if (currentCharViewInfo.isBike()) {
                var10 = mResources.nhienLieu + ": ";
                var3 += 17;
                mFont.tahoma_7b_white.writeText(var1, var10, indexMount, var3, 0);
            } else {
                var10 = mResources.sinhLuc + ": ";
                var3 += 17;
                mFont.tahoma_7b_white.writeText(var1, var10, indexMount, var3, 0);
            }

            var1.setColor(6425);
            var1.fillRect(indexMount + 70, var3, 85, 14);
            var1.setColor(16711680);
            var1.fillRect(indexMount + 70, var3, var7, 14);
            var1.setColor(5131338);
            var1.drawRect(indexMount + 70, var3, 85, 14);
            mFont.tahoma_7_white.writeText(var1, var5 + "/1000", indexMount + 113, var3 + 2, 2);
        }

    }

    private void paintTabBijuu(mGraphics graphic) {
        if (indexMenu == 6) {
            graphic.translateXY(-graphic.getTranslateX(), -graphic.getTranslateY());
            Paint.a(popupX, popupY, ew, ex, graphic);
            graphic.setColor(Paint.a);
            drawTitle(graphic, mResources.nameMenuIndex[indexMenu], true);
            resetCursor(graphic);
            graphic.setColor(0);
            graphic.fillRect(popupX + 2, popupY + 31, 171, ex - 34);
            graphic.setColor(13606712);
            graphic.drawRect(popupX + 3, popupY + 32, 168, ex - 37);
            graphic.setColor(Paint.a);
            graphic.fillRect(popupX + 4, popupY + 34, 166, ex - 39);
            int index;
            int cursor;
            if (currentCharViewInfo.arrItemBijuus[4] != null) {
                mFont.tahoma_7b_white.writeText(graphic, currentCharViewInfo.arrItemBijuus[4].template.name, popupX + 90, nw + 2, 2);
                index = currentCharViewInfo.arrItemBijuus[4].sys;

                for (cursor = 0; cursor < index; ++cursor) {
                    SmallImage.paintImage(graphic, 628, popupX + 90 + cursor * 12 - index * 6, nw + 20, 0, 3);
                }
            } else {
                mFont.tahoma_7b_white.writeText(graphic, mResources.tenViThu, popupX + 90, nw + 2, 2);
            }

            for (index = 0; index < currentCharViewInfo.arrItemBijuus.length - 1; ++index) {
                if (currentCharViewInfo.arrItemBijuus[index] != null) {
                    this.drawItemUI(graphic, currentCharViewInfo.arrItemBijuus[index], this.js[index], this.jt[index]);
                } else {
                    graphic.setColor(6425);
                    graphic.fillRect(this.js[index] - 1, this.jt[index] - 1, wItem + 3, wItem + 3);
                    mFont.tahoma_7_grey.writeText(graphic, mResources.placeHolderEquip[index + 24][0], this.js[index] + wItem / 2, this.jt[index] + wItem / 2 - 10, 2);
                    mFont.tahoma_7_grey.writeText(graphic, mResources.placeHolderEquip[index + 24][1], this.js[index] + wItem / 2, this.jt[index] + wItem / 2 + 2, 2);
                }

                if (indexSelect == index && gk == 1 && indexSelect < 4) {
                    graphic.setColor(16777215);
                } else {
                    graphic.setColor(12281361);
                }

                graphic.drawRect(this.js[index], this.jt[index], wItem, wItem);
            }

            index = this.js[0] + wItem + 7;
            cursor = this.jt[0] - 5;
            graphic.setColor(6425);
            graphic.fillRect(index, cursor, 84, 75);
            if (indexSelect == 4) {
                graphic.setColor(16777215);
            } else {
                graphic.setColor(12281361);
            }

            graphic.drawRect(index, cursor, 84, 75);
            int exp = 0;
            int sinhLuc = 0;
            int sinhLucMax = 0;
            int theLuc = 0;
            int processExp = 0;
            int processSinhLuc = 0;
            int level = 0;
            if (currentCharViewInfo.arrItemBijuus[4] != null) {
                if (GameCanvas.gameTick % 6 == 0) {
                    currentCharViewInfo.arrItemBijuus[4].indexFrame = (currentCharViewInfo.arrItemBijuus[4].indexFrame + 1) % 3;
                }
                SmallImage.paintImage(graphic, currentCharViewInfo.arrItemBijuus[4].template.iconID, index + 45, cursor + 35, 0, 3, currentCharViewInfo.arrItemBijuus[4].indexFrame);
                if (currentCharViewInfo.arrItemBijuus[4].options != null) {
                    for (index = 0; index < currentCharViewInfo.arrItemBijuus[4].options.size(); ++index) {
                        ItemOption option;
                        if ((option = (ItemOption) currentCharViewInfo.arrItemBijuus[4].options.elementAt(index)).optionTemplate.id == 151) {
                            exp = option.param;
                        } else if (option.optionTemplate.id == 150) {
                            sinhLuc = option.param;
                        } else if (option.optionTemplate.id == 140) {
                            sinhLucMax += 1000;
                        } else if (option.optionTemplate.id == 141) {
                            sinhLucMax += 1500;
                        } else if (option.optionTemplate.id == 142) {
                            sinhLucMax += 2000;
                        } else if (option.optionTemplate.id == 143) {
                            sinhLucMax += 3000;
                        } else if (option.optionTemplate.id == 147) {
                            sinhLucMax += option.param * 10;
                        }
                    }
                }

                processExp = exp * 85 / 9999;
                processSinhLuc = sinhLuc * 85 / sinhLucMax;
                level = currentCharViewInfo.arrItemBijuus[4].upgrade;
            }

            index = leftMargin + 5;
            cursor = nw + 112;

            mFont.tahoma_7b_white.writeText(graphic, mResources.cap1 + ": ", index, cursor, 0);
            mFont.tahoma_7b_white.writeText(graphic, String.valueOf(level), index + 70, cursor, 0);

            String text;
            text = mResources.kinhNghiem + ": ";
            cursor += 15;
            mFont.tahoma_7b_white.writeText(graphic, text, index, cursor, 0);
            graphic.setColor(6425);
            graphic.fillRect(index + 70, cursor, 85, 14);
            graphic.setColor(371981);
            graphic.fillRect(index + 70, cursor, processExp, 14);
            graphic.setColor(5131338);
            graphic.drawRect(index + 70, cursor, 85, 14);
            mFont.tahoma_7_white.writeText(graphic, ItemOption.getValuePercent(exp) + "%", index + 113, cursor + 2, 2);

            text = mResources.sinhLuc + ": ";
            cursor += 17;
            mFont.tahoma_7b_white.writeText(graphic, text, index, cursor, 0);
            graphic.setColor(6425);
            graphic.fillRect(index + 70, cursor, 85, 14);
            graphic.setColor(16711680);
            graphic.fillRect(index + 70, cursor, processSinhLuc, 14);
            graphic.setColor(5131338);
            graphic.drawRect(index + 70, cursor, 85, 14);
            mFont.tahoma_7_white.writeText(graphic, sinhLuc + "/" + sinhLucMax, index + 113, cursor + 2, 2);
        }

    }

    private void l(mGraphics var1) {
        if (id) {
            resetCursor(var1);
            Paint.a(popupX, popupY, ew, ex, var1);
            drawTitle(var1, mResources.menuTongHopGiaToc[indexMenu], true);
            if (indexMenu == 0) {
                if (Char.clan != null && Char.clan.name != null && !Char.clan.name.equals("")) {
                    int[] var6 = new int[]{1692, 1693, 1694, 1695, 1696};

                    for (int var5 = 0; var5 < 5; ++var5) {
                        var1.setColor(6425);
                        var1.fillRect(popupX + var5 * wItem + 18, popupY + 32, wItem - 2, wItem - 2);
                        if (gk == 1 && var5 == indexSelect) {
                            var1.setColor(16777215);
                        } else {
                            var1.setColor(12281361);
                        }

                        var1.drawRect(popupX + var5 * wItem + 18, popupY + 32, wItem - 2, wItem - 2);
                        if (var5 > Char.clan.openDun - 1) {
                            SmallImage.paintImage(var1, 1697, popupX + var5 * wItem + 18 + wItem / 2, popupY + 32 + wItem / 2, 0, 3);
                        } else {
                            SmallImage.paintImage(var1, var6[var5], popupX + var5 * wItem + 18 + wItem / 2, popupY + 32 + wItem / 2, 0, 3);
                        }
                    }

                    if (gk == 2) {
                        var1.setColor(Paint.COLORDARK);
                        var1.fillRect(popupX + 7, popupY + 60, ew - 14, ex - 68);
                        var1.setColor(16777215);
                    } else {
                        var1.setColor(10249521);
                    }

                    var1.drawRect(popupX + 7, popupY + 60, ew - 14, ex - 68);
                    leftMargin = popupX + 17;
                    nw = popupY + 62;
                    totalRowSetting = 12;
                    scrMain.a(totalRowSetting, 12, popupX, popupY + 62, ew, ex - 72, true, 1);
                    scrMain.a(var1);
                    mFont.tahoma_7b_yellow.writeText(var1, mResources.mn[0] + Char.clan.name, leftMargin, nw, 0);
                    mFont.tahoma_7_blue1.writeText(var1, mResources.mn[1] + Char.clan.main_name, leftMargin, nw += 12, 0);
                    mFont.tahoma_7_white.writeText(var1, mResources.mn[2] + Char.clan.total + "/" + (Char.clan.level * 5 + 45), leftMargin, nw += 12, 0);
                    mFont.tahoma_7_white.writeText(var1, mResources.mn[3] + Char.clan.level, leftMargin, nw += 12, 0);
                    mFont.tahoma_7_white.writeText(var1, mResources.mn[4] + Char.clan.exp + "/" + Char.clan.expNext, leftMargin, nw += 12, 0);
                    mFont.tahoma_7_white.writeText(var1, mResources.mn[5] + NinjaUtil.a(String.valueOf(Char.clan.freeCoin)) + " " + mResources.ke, leftMargin, nw += 12, 0);
                    mFont.tahoma_7_white.writeText(var1, mResources.mn[8] + NinjaUtil.a(String.valueOf(Char.clan.coinUp)) + " " + mResources.ke, leftMargin, nw += 12, 0);
                    mFont.tahoma_7_white.writeText(var1, mResources.mn[9] + NinjaUtil.a(String.valueOf(Char.clan.i)) + " " + mResources.ke, leftMargin, nw += 12, 0);
                    if (mFont.tahoma_7_white.a(mResources.mn[10] + Char.clan.coin + " " + mResources.ml) > oj - 10) {
                        this.b(var1, mFont.tahoma_7_white, mResources.mn[10] + Char.clan.coin + " " + mResources.ml, leftMargin, nw += 12, ew - 20);
                    } else {
                        mFont.tahoma_7_white.writeText(var1, mResources.mn[10] + Char.clan.coin + " " + mResources.ml, leftMargin, nw += 12, 0);
                    }

                    if (mFont.tahoma_7_white.a(mResources.mn[12] + Char.clan.use_card + " " + mResources.ml) > oj - 10) {
                        this.b(var1, mFont.tahoma_7_white, mResources.mn[12] + Char.clan.use_card + " " + mResources.ml, leftMargin, nw += 12, ew - 20);
                    } else {
                        mFont.tahoma_7_white.writeText(var1, mResources.mn[12] + Char.clan.use_card + " " + mResources.ml, leftMargin, nw += 12, 0);
                    }

                    mFont.tahoma_7_white.writeText(var1, mResources.mn[6] + Char.clan.reg_date, leftMargin, nw += 12, 0);
                    if (this.me == null) {
                        this.me = a(mFont.tahoma_7_yellow, Char.clan.alert);
                    }

                    this.a(var1, mFont.tahoma_7_yellow, this.me, leftMargin, nw += 12);
                    if (gk == 2 && indexRow >= 0) {
                        SmallImage.paintImage(var1, 942, leftMargin - 8, popupY + 62 + 2 + indexRow * 12, 0, StaticObj.b);
                    }

                    scrMain.a(totalRowSetting, 12, popupX, popupY + 62, ew, ex - 72, true, 1);
                    return;
                }

                totalRowSetting = 1;
                mFont.tahoma_7_white.writeText(var1, mResources.nt, popupX + ew / 2, popupY + 40, 2);
                return;
            }

            if (indexMenu == 1) {
                leftMargin = popupX + 5;
                nw = popupY + 32;
                if (vClan.size() == 0) {
                    mFont.tahoma_7_white.writeText(var1, mResources.nt, popupX + ew / 2, popupY + 40, 2);
                    return;
                }

                var1.setColor(6425);
                var1.fillRect(leftMargin - 2, nw - 2, ew - 6, wItem * 5 + 8);
                resetCursor(var1);
                scrMain.a(var1, leftMargin, nw, ew - 3, wItem * 5 + 6);
                this.md = 0;

                for (int var4 = 0; var4 < vClan.size(); ++var4) {
                    Member var3 = (Member) vClan.elementAt(var4);
                    if (!gq || var3.isOnline) {
                        if (var4 * (wItem + wItem / 2) >= scrMain.b - (wItem + wItem / 2) && var4 * (wItem + wItem / 2) < scrMain.b + wItem * 5 + 8) {
                            if (indexRow == this.md) {
                                var1.setColor(Paint.b);
                                var1.fillRect(leftMargin + 2, nw + indexRow * (wItem + wItem / 2) + 2, ew - 15, wItem + wItem / 2 - 4);
                                var1.setColor(16777215);
                                var1.drawRect(leftMargin + 2, nw + indexRow * (wItem + wItem / 2) + 2, ew - 15, wItem + wItem / 2 - 4);
                            } else {
                                var1.setColor(Paint.a);
                                var1.fillRect(leftMargin + 2, nw + this.md * (wItem + wItem / 2) + 2, ew - 15, wItem + wItem / 2 - 4);
                                var1.setColor(13932896);
                                var1.drawRect(leftMargin + 2, nw + this.md * (wItem + wItem / 2) + 2, ew - 15, wItem + wItem / 2 - 4);
                            }

                            SmallImage.paintImage(var1, var3.iconId, leftMargin + 12, nw + this.md * (wItem + wItem / 2) + 13, 0, 3);
                            if (var3.type == 4) {
                                SmallImage.paintImage(var1, 1216, leftMargin + 12, nw + this.md * (wItem + wItem / 2) + 30, 0, 3);
                                if (var3.isOnline) {
                                    mFont.tahoma_7_yellow.writeText(var1, mResources.nz[0] + " ", leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0, mFont.tahoma_7_grey);
                                    mFont.tahoma_7_white.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 45, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_green.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                    mFont.tahoma_7_blue1.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                                } else {
                                    mFont.tahoma_7_grey.writeText(var1, mResources.nz[0] + " ", leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_grey.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 45, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_grey.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                    mFont.tahoma_7_grey.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                                }
                            } else if (var3.type == 3) {
                                SmallImage.paintImage(var1, 1215, leftMargin + 12, nw + this.md * (wItem + wItem / 2) + 30, 0, 3);
                                if (var3.isOnline) {
                                    mFont.tahoma_7_yellow.writeText(var1, mResources.nz[1] + " ", leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0, mFont.tahoma_7_grey);
                                    mFont.tahoma_7_white.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 45, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_green.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                    mFont.tahoma_7_blue1.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                                } else {
                                    mFont.tahoma_7_grey.writeText(var1, mResources.nz[1] + " ", leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_grey.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 45, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_grey.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                    mFont.tahoma_7_grey.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                                }
                            } else if (var3.type == 2) {
                                SmallImage.paintImage(var1, 1217, leftMargin + 12, nw + this.md * (wItem + wItem / 2) + 30, 0, 3);
                                if (var3.isOnline) {
                                    mFont.tahoma_7_yellow.writeText(var1, mResources.nz[2] + " ", leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0, mFont.tahoma_7_grey);
                                    mFont.tahoma_7_white.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 45, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_green.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                    mFont.tahoma_7_blue1.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                                } else {
                                    mFont.tahoma_7_grey.writeText(var1, mResources.nz[2] + " ", leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_grey.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 45, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_grey.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                    mFont.tahoma_7_grey.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                                }
                            } else if (var3.type == 1) {
                                SmallImage.paintImage(var1, 1214, leftMargin + 12, nw + this.md * (wItem + wItem / 2) + 30, 0, 3);
                                if (var3.isOnline) {
                                    mFont.tahoma_7_yellow.writeText(var1, mResources.nz[3] + " ", leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0, mFont.tahoma_7_grey);
                                    mFont.tahoma_7_white.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 45, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_green.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                    mFont.tahoma_7_blue1.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                                } else {
                                    mFont.tahoma_7_grey.writeText(var1, mResources.nz[3] + " ", leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_grey.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 45, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                    mFont.tahoma_7_grey.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                    mFont.tahoma_7_grey.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                                }
                            } else if (var3.isOnline) {
                                mFont.tahoma_7_white.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                mFont.tahoma_7_green.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                mFont.tahoma_7_blue1.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                            } else {
                                mFont.tahoma_7_grey.writeText(var1, var3.name + " - " + mResources.ec + ": " + var3.level, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 5, 0);
                                mFont.tahoma_7_grey.writeText(var1, mResources.mn[7] + var3.pointClan, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 16, 0);
                                mFont.tahoma_7_grey.writeText(var1, mResources.mn[11] + var3.pointClanWeek, leftMargin + 22, nw + this.md * (wItem + wItem / 2) + 26, 0);
                            }
                        }

                        ++this.md;
                    }
                }

                scrMain.a(this.md, wItem + wItem / 2, leftMargin, nw, ew - 3, wItem * 5 + 4, true, 1);
                totalRowSetting = this.md;
                m(var1);
                return;
            }

            if (indexMenu != 2) {
                if (indexMenu == 3) {
                    if (Char.clan != null && Char.clan.name != null && !Char.clan.name.equals("") && !Char.clan.log.equals("")) {
                        totalRowSetting = 1;
                        leftMargin = popupX + 17;
                        nw = popupY + 34;
                        oj = ew - 30;
                        scrMain.a(var1);
                        if (this.mf == null) {
                            this.mf = a(mFont.tahoma_7_white, Char.clan.log);
                        }

                        this.a(var1, mFont.tahoma_7_white, this.mf, leftMargin, nw);
                        if (gk == 1 && indexRow >= 0) {
                            SmallImage.paintImage(var1, 942, leftMargin - 8, popupY + 34 + 2 + indexRow * 12, 0, StaticObj.b);
                        }

                        scrMain.a(totalRowSetting, 12, popupX, popupY + 35, ew, ex - 44, true, 1);
                        return;
                    }

                    totalRowSetting = 1;
                    mFont.tahoma_7_white.writeText(var1, mResources.nt, popupX + ew / 2, popupY + 40, 2);
                    return;
                }

                if (indexMenu == 4) {
                    this.aq(var1);
                    return;
                }
            } else {
                leftMargin = popupX + 3;
                nw = popupY + 32;
                var1.setColor(6425);
                var1.fillRect(leftMargin - 1, nw - 1, ob * wItem + 3, 5 * wItem + 3);
                Item[] var2;
                if (Char.clan != null && Char.clan.items != null) {
                    var2 = Char.clan.items;
                } else {
                    var2 = new Item[30];
                }

                this.paintTabBag(var1, var2);
            }
        }

    }

    private static void m(mGraphics var0) {
        resetCursor(var0);
        int var1 = indexRow;
        if (ig) {
            var1 = indexSelect;
        }

        if (var1 >= 0 && totalRowSetting > 0) {
            var1 = var1 + 1 < totalRowSetting ? var1 + 1 : totalRowSetting;
            mFont.tahoma_7_yellow.writeText(var0, var1 + "/" + totalRowSetting, popupX + ew / 2, popupY + ex - 12, 2, mFont.tahoma_7_grey);
        }

    }

    private void bt() {
        if (!GameCanvas.menu.showMenu && GameCanvas.currentDialog == null) {
            if (GameCanvas.isPointerJustRelease && GameCanvas.b(popupX, popupY, ew, this.os) && (!cg || GameCanvas.width >= 320) && GameCanvas.isPointerClick) {
                if (GameCanvas.b(d - 90, popupY + 5, 60, 40)) {
                    indexSelect = 0;
                    --indexMenu;
                }

                if (GameCanvas.b(d + 20, popupY + 5, 60, 40)) {
                    indexSelect = 0;
                    ++indexMenu;
                }

                cg = false;
                scrMain.a();
                gn.a();
                if (currentCharViewInfo.charID != Char.getMyChar().charID) {
                    if (indexMenu < 3) {
                        indexMenu = mResources.nameMenuIndex.length - 1;
                    }

                    if (indexMenu > mResources.nameMenuIndex.length - 1) {
                        indexMenu = 3;
                    }
                } else {
                    if (indexMenu < 0) {
                        indexMenu = mResources.nameMenuIndex.length - 1;
                    }

                    if (indexMenu > mResources.nameMenuIndex.length - 1) {
                        indexMenu = 0;
                    }
                }

                gk = 1;
                indexSelect = -1;
                this.showMenuIndex();
            }

            ScrollResult var1;
            if (cg) {
                if ((var1 = gn.b()).a || var1.c) {
                    indexRow = var1.b;
                    gk = 1;
                }

                if (GameCanvas.isMaxWidth320) {
                    return;
                }
            }

            if (indexMenu == 0) {
                if ((var1 = scrMain.b()).a || var1.c) {
                    if (indexSelect != var1.b) {
                        indexSelect = var1.b;
                        super.left = super.center = null;
                        if (GameCanvas.isMaxWidth320) {
                            this.showButtonIndexMenu();
                        } else if (getCurrentItemSelectByTypeUI(3) != null) {
                            this.gq();
                        } else {
                            cg = false;
                            super.left = this.om;
                        }
                    }

                    gk = 1;
                    return;
                }
            } else if (indexMenu == 1) {
                if ((var1 = scrMain.b()).a || var1.c) {
                    if (indexSelect != var1.b) {
                        indexSelect = var1.b;
                        if (var1.b >= Char.getMyChar().nClass.skillTemplates.length) {
                            indexSelect = -1;
                        }

                        super.left = super.center = null;
                        this.showButtonIndexMenu();
                        gn.a();
                        indexRow = 0;
                    }

                    gk = 1;
                    return;
                }

                if (((var1 = gn.b()).a || var1.c) && indexRow != var1.b) {
                    indexRow = var1.b;
                    return;
                }
            } else {
                int var3;
                if (indexMenu == 2) {
                    if (GameCanvas.isPointerJustRelease && GameCanvas.b(popupX + 5, popupY + 52, ew - 10, 130) && GameCanvas.isPointerClick) {
                        var3 = (GameCanvas.q - (popupY + 52)) / 32;
                        ++var3;
                        if (var3 == this.mg) {
                            MyVector var2;
                            (var2 = new MyVector()).addElement(new Command(mResources.de, 11064));
                            var2.addElement(new Command(mResources.df, 11065));
                            GameCanvas.menu.showMenu(var2);
                        }

                        gk = var3;
                        this.mg = var3;
                        this.showButtonIndexMenu();
                        return;
                    }
                } else if (indexMenu == 3) {
                    if ((var1 = scrMain.b()).a || var1.c) {
                        indexRow = var1.b;
                        gk = 1;
                        return;
                    }
                } else if (indexMenu == 4) {
                    if (GameCanvas.isPointerJustRelease) {
                        gk = 1;
                        if (GameCanvas.b(popupX + 4, popupY + 35, wItem, 130)) {
                            indexSelect = (GameCanvas.q - (popupY + 35)) / wItem << 1;
                            super.left = super.center = null;
                            this.showButtonIndexMenu();
                        }

                        if (GameCanvas.b(popupX + ew - 30, popupY + 35, wItem, 130)) {
                            indexSelect = ((GameCanvas.s - (popupY + 35)) / wItem << 1) + 1;
                            super.left = super.center = null;
                            this.showButtonIndexMenu();
                        }

                        if (GameCanvas.b(popupX + 4, popupY + 165, ew - 8, wItem)) {
                            var3 = (GameCanvas.r - (popupX + 4)) / wItem;
                            var3 += 10;
                            indexSelect = var3;
                            super.left = super.center = null;
                            this.showButtonIndexMenu();
                            return;
                        }
                    }
                } else if (indexMenu == 5 && GameCanvas.isPointerJustRelease) {
                    for (var3 = 0; var3 < this.js.length; ++var3) {
                        if (var3 == 4) {
                            if (GameCanvas.b(this.js[var3], this.jt[var3], 84, 75) && GameCanvas.isPointerClick) {
                                gk = 1;
                                indexSelect = 4;
                                this.showButtonIndexMenu();
                                if (!GameCanvas.isMaxWidth320 && super.center != null) {
                                    this.b(super.center.idAction, super.center.obj);
                                }
                            }
                        } else if (GameCanvas.b(this.js[var3], this.jt[var3], wItem, wItem) && GameCanvas.isPointerClick) {
                            gk = 1;
                            indexSelect = var3;
                            this.showButtonIndexMenu();
                            if (!GameCanvas.isMaxWidth320) {
                                if (currentCharViewInfo.arrItemMounts[indexSelect] != null) {
                                    this.b(super.center.idAction, super.center.obj);
                                } else {
                                    cg = false;
                                }
                            }
                        }
                    }
                } else if (indexMenu == 6 && GameCanvas.isPointerJustRelease) {
                    for (var3 = 0; var3 < this.js.length; ++var3) {
                        if (var3 == 4) {
                            if (GameCanvas.b(this.js[var3], this.jt[var3], 84, 75) && GameCanvas.isPointerClick) {
                                gk = 1;
                                indexSelect = 4;
                                this.showButtonIndexMenu();
                                if (!GameCanvas.isMaxWidth320 && super.center != null) {
                                    this.b(super.center.idAction, super.center.obj);
                                }
                            }
                        } else if (GameCanvas.b(this.js[var3], this.jt[var3], wItem, wItem) && GameCanvas.isPointerClick) {
                            gk = 1;
                            indexSelect = var3;
                            this.showButtonIndexMenu();
                            if (!GameCanvas.isMaxWidth320) {
                                if (currentCharViewInfo.arrItemBijuus[indexSelect] != null) {
                                    this.b(super.center.idAction, super.center.obj);
                                } else {
                                    cg = false;
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private void bu() {
        if (id) {
            if (gk == 0) {
                if (GameCanvas.keyPressedz[8]) {
                    if (Char.clan == null) {
                        gk = 0;
                    } else {
                        gk = 1;
                    }

                    indexSelect = 0;
                    indexRow = -1;
                    if (indexMenu == 0) {
                        indexSelect = Char.clan.openDun;
                    }

                    scrMain.a();
                    gn.a();
                }

                if (GameCanvas.keyPressedz[4]) {
                    indexSelect = 0;
                    indexRow = -1;
                    --indexMenu;
                    scrMain.a();
                    gn.a();
                    if (indexMenu < 0) {
                        indexMenu = mResources.menuTongHopGiaToc.length - 1;
                    }

                    if (indexMenu >= mResources.menuTongHopGiaToc.length) {
                        indexMenu = 0;
                    }

                    if (indexMenu == 1 && ie) {
                        Service.getInstance().requestClanMember();
                        Service.getInstance().requestClanItem();
                        ie = false;
                    } else if (indexMenu == 2) {
                        Service.getInstance().requestClanItem();
                    } else if (indexMenu == 3) {
                        Service.getInstance().requestClanLog();
                    } else if (indexMenu == 4) {
                        Service.getInstance().requestClanItem();
                    }

                    b(175, 200);
                }

                if (GameCanvas.keyPressedz[6]) {
                    indexSelect = 0;
                    indexRow = -1;
                    ++indexMenu;
                    scrMain.a();
                    gn.a();
                    if (indexMenu < 0) {
                        indexMenu = mResources.menuTongHopGiaToc.length - 1;
                    }

                    if (indexMenu >= mResources.menuTongHopGiaToc.length) {
                        indexMenu = 0;
                    }

                    if (indexMenu == 1 && ie) {
                        Service.getInstance().requestClanMember();
                        Service.getInstance().requestClanItem();
                        ie = false;
                    } else if (indexMenu == 2) {
                        Service.getInstance().requestClanItem();
                    } else if (indexMenu == 3) {
                        Service.getInstance().requestClanLog();
                    } else if (indexMenu == 4) {
                        Service.getInstance().requestClanItem();
                    }

                    b(175, 200);
                }

                this.bw();
            } else if (cg) {
                if (GameCanvas.keyPressedz[2]) {
                    if (--indexRow < 0) {
                        indexRow = totalRowSetting - 1;
                    }

                    gn.a(indexRow * gn.h);
                } else if (GameCanvas.keyPressedz[8]) {
                    if (++indexRow >= totalRowSetting) {
                        indexRow = 0;
                    }

                    gn.a(indexRow * gn.h);
                }
            } else {
                if (indexRow < 0) {
                    indexRow = 0;
                }

                if (indexMenu == 2) {
                    if (Char.clan != null && Char.clan.items != null) {
                        if (GameCanvas.keyPressedz[4]) {
                            if (--indexSelect < 0) {
                                indexSelect = Char.clan.items.length - 1;
                            }
                        } else if (GameCanvas.keyPressedz[6]) {
                            if (++indexSelect >= Char.clan.items.length) {
                                indexSelect = 0;
                            }
                        } else if (GameCanvas.keyPressedz[8]) {
                            if (indexSelect + ob <= Char.clan.items.length - 1) {
                                indexSelect += ob;
                            }
                        } else if (GameCanvas.keyPressedz[2]) {
                            if (indexSelect >= 0 && indexSelect < ob) {
                                gk = 0;
                                indexSelect = 0;
                            } else if (indexSelect - ob >= 0) {
                                indexSelect -= ob;
                            }
                        }

                        scrMain.a(indexSelect / ob * scrMain.h);
                    }
                } else if (indexMenu == 0 && gk == 1) {
                    if (GameCanvas.keyPressedz[8]) {
                        ++gk;
                    } else if (GameCanvas.keyPressedz[2]) {
                        --gk;
                    }
                } else if (indexMenu == 4) {
                    if (GameCanvas.keyPressedz[2]) {
                        if (indexRow == 0) {
                            --gk;
                            indexRow = -1;
                        } else {
                            --indexRow;
                        }

                        scrMain.a(indexRow * scrMain.h);
                    } else if (GameCanvas.keyPressedz[8]) {
                        if (++indexRow >= totalRowSetting) {
                            indexRow = 0;
                        }

                        scrMain.a(indexRow * scrMain.h);
                    } else if (GameCanvas.keyPressedz[4]) {
                        --this.pm;
                        if (this.pm < 0) {
                            this.pm = 0;
                        }
                    } else if (GameCanvas.keyPressedz[6]) {
                        ++this.pm;
                        if (this.pm > this.pn.size() - 1) {
                            this.pm = (byte) (this.pn.size() - 1);
                        }
                    }
                } else if (GameCanvas.keyPressedz[2]) {
                    if (indexRow == 0) {
                        --gk;
                        indexRow = -1;
                    } else {
                        --indexRow;
                    }

                    scrMain.a(indexRow * scrMain.h);
                    if (indexMenu == 1 && ie) {
                        Service.getInstance().requestClanMember();
                        Service.getInstance().requestClanItem();
                        ie = false;
                    }
                } else if (GameCanvas.keyPressedz[8]) {
                    if (++indexRow >= totalRowSetting) {
                        indexRow = 0;
                    }

                    scrMain.a(indexRow * scrMain.h);
                }

                this.bw();
            }

            if (GameCanvas.isTouch && GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu) {
                label368:
                {
                    if (GameCanvas.isPointerJustRelease) {
                        if (GameCanvas.b(popupX, popupY, ew, this.os) && (!cg || GameCanvas.width >= 320) && GameCanvas.isPointerClick) {
                            if (GameCanvas.b(d - 90, popupY + 5, 60, 40)) {
                                indexSelect = 0;
                                --indexMenu;
                                indexRow = 0;
                            }

                            if (GameCanvas.b(d + 20, popupY + 5, 60, 40)) {
                                indexSelect = 0;
                                ++indexMenu;
                                indexRow = 0;
                            }

                            cg = false;
                            scrMain.a();
                            gn.a();
                            if (indexMenu < 0) {
                                indexMenu = mResources.menuTongHopGiaToc.length - 1;
                            }

                            if (indexMenu > mResources.menuTongHopGiaToc.length - 1) {
                                indexMenu = 0;
                            }

                            gk = 1;
                            if (indexMenu == 1 && ie) {
                                Service.getInstance().requestClanMember();
                                Service.getInstance().requestClanItem();
                                ie = false;
                            } else if (indexMenu == 2 && Char.clan != null && Char.clan.items == null) {
                                Service.getInstance().requestClanItem();
                            }

                            if (indexMenu == 3) {
                                Service.getInstance().requestClanLog();
                            }

                            if (indexMenu == 4) {
                                Service.getInstance().requestClanItem();
                            }

                            b(175, 200);
                            this.bw();
                        }

                        if (indexMenu == 4) {
                            int var1 = this.pw - this.py / 2;
                            int var2 = this.px - this.py / 2;
                            int var3 = (this.py + 5) * this.pn.size();
                            int var4 = this.py;
                            if (GameCanvas.b(var1, var2, var3, var4) && (var1 = (GameCanvas.p - var1) / (this.py + 5)) >= 0 && var1 < this.pn.size()) {
                                this.pm = (byte) var1;
                            }
                        }
                    }

                    ScrollResult var5;
                    if (cg) {
                        if ((var5 = gn.b()).a || var5.c) {
                            indexRow = var5.b;
                            gk = 1;
                        }

                        if (GameCanvas.isMaxWidth320) {
                            break label368;
                        }
                    }

                    if (indexMenu == 2) {
                        if ((var5 = scrMain.b()).a || var5.c) {
                            indexSelect = var5.b;
                            gk = 1;
                            this.b(1509, (Object) null);
                        }
                    } else if (indexMenu == 0 && GameCanvas.b(popupX + 18, popupY + 32, 5 * wItem, wItem) && GameCanvas.isPointerJustRelease && GameCanvas.isPointerClick) {
                        if (Char.clan != null) {
                            indexSelect = Char.clan.openDun;
                            gk = 1;
                        }
                    } else if (indexMenu != 0 && indexMenu != 3) {
                        if (indexMenu == 1 && vClan.size() != 0 && ((var5 = scrMain.b()).a || var5.c)) {
                            indexRow = var5.b;
                            this.ab();
                        }
                    } else if (!cg && ((var5 = scrMain.b()).a || var5.c)) {
                        indexRow = var5.b;
                        gk = indexMenu == 0 ? 2 : 1;
                        if (var5.c) {
                            scrMain.a(indexRow * scrMain.h);
                        }
                    }
                }
            }

            GameCanvas.l();
            GameCanvas.k();
        }

    }

    private static Member getMemberCurrentIndexRow() {
        return (Member) vClan.elementAt(indexRow);
    }

    private void bw() {
        if (!cg) {
            super.left = super.center = null;
            if (indexMenu == 0) {
                if (Char.getMyChar().ctypeClan == 4) {
                    super.left = new Command(mResources.ny[0], 14004);
                }

                if (Char.getMyChar().ctypeClan == 3) {
                    super.left = new Command(mResources.ny[1], 14004);
                }

                if (Char.getMyChar().ctypeClan == 2) {
                    super.left = new Command(mResources.ny[2], 14004);
                }

                if (!Char.getMyChar().cClanName.equals("")) {
                    if (gk == 1) {
                        super.center = new Command(mResources.xem, 140101);
                        return;
                    }

                    super.center = new Command(mResources.menuClan[3], 14010);
                    return;
                }
            } else if (indexMenu == 1) {
                Member var1;
                if (vClan.size() > 0 && indexRow >= 0 && indexRow < vClan.size() && (var1 = getMemberCurrentIndexRow()) != null) {
                    if (Char.getMyChar().ctypeClan == 4) {
                        super.left = new Command(mResources.ny[0], 14005);
                    }

                    if (Char.getMyChar().ctypeClan == 3) {
                        super.left = new Command(mResources.ny[1], 14005);
                    }

                    if (Char.getMyChar().ctypeClan != 4 && Char.getMyChar().ctypeClan != 3) {
                        super.left = new Command(mResources.ny[4], 14005);
                    }

                    if (!var1.name.equals(Char.getMyChar().charName) && (!gq || this.md != 0)) {
                        super.center = new Command(mResources.chon, 14006, var1.name);
                        return;
                    }
                }
            } else if (indexMenu == 2 && gk == 1) {
                if (Char.clan == null || Char.clan.items == null) {
                    return;
                }

                super.left = new Command(mResources.chon, 1508);
                if (!GameCanvas.isMinWidth320) {
                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 1509);
                }
            }
        }

    }

    private void bx() {
        if (ce && GameCanvas.currentDialog == null) {
            boolean var1 = false;
            if (GameCanvas.keyPressedz[8]) {
                if (++indexRow >= this.ix.size()) {
                    indexRow = 0;
                }

                var1 = true;
            } else if (GameCanvas.keyPressedz[2]) {
                if (--indexRow < 0) {
                    indexRow = this.ix.size() - 1;
                }

                var1 = true;
            }

            if (var1) {
                scrMain.a(indexRow * scrMain.h);
                GameCanvas.l();
                GameCanvas.k();
            }

            ScrollResult var2;
            if (GameCanvas.isTouch && ((var2 = scrMain.b()).a || var2.c)) {
                indexRow = var2.b;
                var1 = true;
            }

            ChatTab var5;
            if (ck && !GameCanvas.isTouch && (var5 = ChatManager.getInstance().e()).a == 2 && indexRow == 0) {
                ChatTextField.a().f = new Command(mResources.an, 120051, var5);
            }

            if (var1 && indexRow >= 0 && indexRow < this.ix.size()) {
                String var7 = (String) this.ix.elementAt(indexRow);
                this.nu = null;
                this.nt = null;
                super.center = null;
                ChatTextField.a().f = null;
                int var6;
                if ((var6 = var7.indexOf("http://")) >= 0) {
                    this.nt = var7.substring(var6);
                    super.center = new Command(mResources.ax, 12000);
                    if (!GameCanvas.isTouch) {
                        ChatTextField.a().f = new Command(mResources.ax, (IActionListener) null, 12000, (Object) null);
                        return;
                    }
                } else if (var7.indexOf("@") >= 0) {
                    var6 = (var7 = var7.substring(2).trim()).indexOf("@");
                    String var3;
                    int var4;
                    if ((var4 = (var3 = var7.substring(var6)).indexOf(" ")) <= 0) {
                        var4 = var6 + var3.length();
                    } else {
                        var4 += var6;
                    }

                    this.nu = var7.substring(var6 + 1, var4);
                    if (!this.nu.equals("") && !this.nu.equals(Char.getMyChar().charName) && !this.nu.equals(mResources.qt.substring(0, 5)) && !this.nu.equals(mResources.qt)) {
                        super.center = new Command(mResources.chon, 12009, this.nu);
                        if (!GameCanvas.isTouch) {
                            ChatTextField.a().f = new Command(mResources.chon, (IActionListener) null, 12009, this.nu);
                            return;
                        }
                    } else {
                        this.nu = null;
                        super.center = null;
                    }
                }
            }
        }

    }

    private void by() {
        if (ck) {
            boolean var1 = false;
            if (GameCanvas.keyPressedz[4]) {
                var1 = true;
                ChatManager.getInstance().b();
            } else if (GameCanvas.keyPressedz[6]) {
                var1 = true;
                ChatManager.getInstance().a();
            }

            if (var1) {
                this.fj();
            }

            if (GameCanvas.isTouch && GameCanvas.b(popupX, popupY, ew, this.os) && (!cg || GameCanvas.width >= 320) && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                if (GameCanvas.b(d - 90, popupY + 5, 60, 40)) {
                    ChatManager.getInstance().b();
                    this.fj();
                }

                if (GameCanvas.b(d + 20, popupY + 5, 60, 40)) {
                    ChatManager.getInstance().a();
                    this.fj();
                }
            }
        }

    }

    private void bz() {
        if (indexRow >= 0 && vCharInMap.size() > 0) {
            if (Char.d(this.de) == indexRow) {
                super.left = new Command(mResources.dw, 14002);
            } else {
                super.left = new Command(mResources.chon, 14003);
                super.center = new Command("", 14003);
            }
        } else {
            super.left = super.center = null;
        }

    }

    private void ca() {
        if (ab.size() > 0 && indexRow >= 0 && totalRowSetting > 0) {
            super.center = new Command(mResources.z, 14024);
            super.left = new Command(mResources.aa, 14025);
        } else {
            super.center = null;
            super.left = null;
        }

    }

    private void cb() {
        if (ab.size() > 0 && indexRow >= 0 && totalRowSetting > 0) {
            super.center = new Command(mResources.chon, 14021);
        } else {
            super.center = null;
        }

    }

    private void cc() {
        if (ad.size() > 0 && indexRow >= 0 && totalRowSetting > 0) {
            super.center = new Command(mResources.chon, 11078);
        } else {
            super.center = null;
        }

    }

    private void cd() {
        if (aa.size() > 0 && indexRow >= 0 && totalRowSetting > 0 && indexRow < aa.size()) {
            aa.elementAt(indexRow);
            super.center = new Command(mResources.chon, 11079);
        } else {
            super.center = null;
        }

    }

    private void ce() {
        super.center = null;
        if (indexRow != -1) {
            Party var1;
            if (((Party) vParty.elementAt(0)).a == Char.getMyChar().charID) {
                if ((var1 = (Party) vParty.elementAt(indexRow)).a != Char.getMyChar().charID) {
                    super.center = new Command(mResources.chon, 11080, var1.d);
                    return;
                }
            } else if ((var1 = (Party) vParty.elementAt(indexRow)).a != Char.getMyChar().charID) {
                super.center = new Command(mResources.chon, 12009, var1.d);
            }
        }

    }

    private static boolean cf() {
        return gt || ifa || cg || isPaintInfoMe || ha || hb || ig || hq || hc || hd || he || hf || hg || hh || hi || hj || hk || hl || hm || hn || ho || hp || hr || hs || ht || hu || hv || cd || hw || ia || ii || cl || hy || ih || showBox || showPickItem || showDelItem || showItemThrow || showItemGather || showAutoUseItem || ci || ce || showZoneDialog || showAutoPanel || gv || id || gw || gu || cf || gx || gy || ib || ck || in || ij || ik || io || il || im;
    }

    private static boolean canShowQuickStopAutoButton() {
        return GameCanvas.isTouch && (Code.auto != null || AutoBiKip.isRunning() || AutoTinhLuyen.isRunning()) && GameCanvas.currentDialog == null
                && ChatPopup.currentMultilineChatPopup == null && !GameCanvas.menu.showMenu
                && !ChatTextField.a().isShow && !InfoDlg.f && !cf();
    }

    private static String getAutoScheduleText1() {
        StringBuffer sb = new StringBuffer();
        if (FormAutoTask.batNvhn) {
            appendSchedule(sb, "NVHN", FormAutoTask.gioNvhn, FormAutoTask.phutNvhn);
        }
        if (FormAutoTask.batTaThu) {
            appendSchedule(sb, "TT", FormAutoTask.gioTaThu, FormAutoTask.phutTaThu);
        }
        return sb.toString();
    }

    private static String getAutoScheduleText2() {
        StringBuffer sb = new StringBuffer();
        if (SettingNVDV.tickHenGioLamDV == 0) {
            appendSchedule(sb, "NVDV", SettingNVDV.gioADV, SettingNVDV.phutADV);
        }
        if (SettingGomDo.onOffValue == 0) {
            appendSchedule(sb, "Gom do", AutoReceiver.gioNhanDo, AutoReceiver.phutNhanDo);
        }
        if (AutoHalloween.AutoTime) {
            appendSchedule(sb, "HT", AutoHalloween.Hour, AutoHalloween.Minute);
        }
        return sb.toString();
    }

    private static void appendSchedule(StringBuffer sb, String label, int hour, int minute) {
        if (sb.length() > 0) {
            sb.append("  ");
        }
        sb.append(label).append(":").append(formatScheduleTime(hour, minute));
    }

    private static String formatScheduleTime(int hour, int minute) {
        if (hour < 0) {
            hour = 0;
        }
        if (minute < 0) {
            minute = 0;
        }
        hour %= 24;
        minute %= 60;
        StringBuffer sb = new StringBuffer();
        if (hour < 10) {
            sb.append('0');
        }
        sb.append(hour).append(':');
        if (minute < 10) {
            sb.append('0');
        }
        sb.append(minute);
        return sb.toString();
    }

    private static void paintQuickStopAutoButton(mGraphics var0, int var1, int var2, String var3) {
        if (!canShowQuickStopAutoButton()) {
            quickStopAutoX = -1;
            quickStopAutoY = -1;
            return;
        }

        quickStopAutoX = var1 + mFont.tahoma_7_yellow.a(var3) + 4;
        if (quickStopAutoX + QUICK_STOP_AUTO_W > GameCanvas.width - 2) {
            quickStopAutoX = GameCanvas.width - QUICK_STOP_AUTO_W - 2;
        }
        if (quickStopAutoX < 2) {
            quickStopAutoX = 2;
        }

        quickStopAutoY = var2 - 3;
        if (quickStopAutoY < 1) {
            quickStopAutoY = 1;
        }

        var0.setColor(8519680);
        var0.fillRoundRect(quickStopAutoX, quickStopAutoY, QUICK_STOP_AUTO_W, QUICK_STOP_AUTO_H, 4, 4);
        var0.setColor(16777215);
        var0.drawRoundRect(quickStopAutoX, quickStopAutoY, QUICK_STOP_AUTO_W, QUICK_STOP_AUTO_H, 4, 4);
        mFont.tahoma_7b_white.writeText(var0, "X", quickStopAutoX + QUICK_STOP_AUTO_W / 2, quickStopAutoY + 2, 2, mFont.tahoma_7_red);
    }

    private void paintOptimizeButtonBorders(mGraphics var0) {
        resetCursor(var0);
        if (!ChatTextField.a().isShow) {
            drawOptimizeBorder(var0, kl, km, 34, 34);
        }

        if (!this.cg()) {
            drawOptimizeBorder(var0, kj, kk, 34, 34);
            drawOptimizeBorder(var0, kn - 5, ko, 40, 34);
            drawOptimizeBorder(var0, kr, ks, 34, 34);
            if (ff) {
                drawOptimizeBorder(var0, kr - 30, ks, 30, 34);
                drawOptimizeBorder(var0, kr + 34, ks, 30, 34);
            }

            Char var1 = Char.getMyChar();
            if (var1 != null && var1.ctaskId > 1) {
                drawOptimizeBorder(var0, kt, ku, 34, 34);
                drawOptimizeBorder(var0, kv, kw, 34, 34);
                drawOptimizeBorder(var0, kx, ky, 34, 34);
            }

            drawOptimizeBorder(var0, kp, kq, 54, 54);
        }

        paintOptimizeSkillBorders(var0);
    }

    private static void paintOptimizeSkillBorders(mGraphics var0) {
        if (!FormToiUu.isHideSkillTable() || dxTableSkill == null || dyTableSkill == null) {
            return;
        }

        int var1 = arrSkill == null ? 0 : arrSkill.length;
        for (int var2 = 0; var2 < var1 && var2 < dxTableSkill.length && var2 < dyTableSkill.length; ++var2) {
            drawOptimizeBorder(var0, marginLeftTableSkill + dxTableSkill[var2] - 1, dyTableSkill[var2] - 1, 25, 25);
        }

        int var3 = keySkill == null ? 0 : keySkill.length;
        int var4 = GameCanvas.isMaxWidth320 ? 30 : -30;
        for (int var5 = 0; var5 < var3 && var5 < dxTableSkill.length && var5 < dyTableSkill.length; ++var5) {
            drawOptimizeBorder(var0, marginLeftTableSkill + dxTableSkill[var5] - 1, dyTableSkill[var5] + var4 - 1, 25, 25);
        }
    }

    private static void drawOptimizeBorder(mGraphics var0, int var1, int var2, int var3, int var4) {
        var0.setColor(16711680);
        var0.drawRect(var1, var2, var3, var4);
        if (var3 > 4 && var4 > 4) {
            var0.drawRect(var1 + 1, var2 + 1, var3 - 2, var4 - 2);
        }
    }

    private static void paintOptimizeStatusInfo(mGraphics var0) {
        try {
            Char var1 = Char.getMyChar();
            if (var1 == null) {
                return;
            }

            resetCursor(var0);
            long now = System.currentTimeMillis();
            if (now - optimizeStatusCacheAt >= 500L || optimizeStatusLine1.length() == 0) {
                long var2 = 0L;
                if (exps != null && var1.cLevel >= 0 && var1.cLevel < exps.length && exps[var1.cLevel] > 0L) {
                    long var4 = var1.cExpDown > 0L ? var1.cExpDown : var1.ah;
                    var2 = var4 * 10000L / exps[var1.cLevel];
                }

                int var6 = (int) (var2 % 100L);
                String var7 = (var1.cExpDown > 0L ? "-" : "") + var2 / 100L + "." + (var6 < 10 ? "0" + var6 : String.valueOf(var6)) + "%";
                optimizeStatusLine1 = "Lv " + var1.cLevel + " HP " + var1.cHP + "/" + var1.cMaxHP;
                optimizeStatusLine2 = "MP " + var1.cMP + "/" + var1.cMaxMP + " EXP " + var7;
                optimizeStatusLine3 = "X:" + var1.cx + " Y:" + var1.cy;
                optimizeStatusCacheAt = now;
            }
            int var8 = GameCanvas.width > 240 ? 220 : GameCanvas.width - 4;
            if (var8 < 140) {
                var8 = 140;
            }

            int var9 = GameCanvas.isTouch ? 38 : 34;
            drawOptimizeBorder(var0, 1, 1, var8, var9);
            mFont.tahoma_7_yellow.writeText(var0, optimizeStatusLine1, 5, 4, 0, mFont.tahoma_7_red);
            mFont.tahoma_7_white.writeText(var0, optimizeStatusLine2, 5, 16, 0, mFont.tahoma_7_grey);
            if (GameCanvas.isTouch) {
                mFont.tahoma_7_yellow.writeText(var0, optimizeStatusLine3, 5, 28, 0, mFont.tahoma_7_red);
            }
        } catch (Exception var10) {
        }
    }

    private static boolean checkQuickStopAutoButton() {
        if (!canShowQuickStopAutoButton() || quickStopAutoX < 0 || quickStopAutoY < 0) {
            return false;
        }

        if (GameCanvas.b(quickStopAutoX, quickStopAutoY, QUICK_STOP_AUTO_W, QUICK_STOP_AUTO_H)) {
            mScreen.fr = -1;
            if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                Code.stopCurrentAuto();
                GameScr.chatPopup("Đã tắt auto hiện tại");
                GameCanvas.isPointerClick = false;
                GameCanvas.isPointerJustRelease = false;
                GameCanvas.l();
            }
            return true;
        }

        return false;
    }

    private boolean cg() {
        if (GameCanvas.isTouch && !Char.fk && !showZoneDialog && !showAutoPanel && !ChatTextField.a().isShow && (super.center != this.fa || !GameCanvas.isMinWidth320)) {
            return GameCanvas.currentDialog != null || ChatPopup.currentMultilineChatPopup != null || GameCanvas.menu.showMenu || cf();
        } else {
            return true;
        }
    }

    public static boolean z() {
        return showAutoPanel || ha || ifa || hb || hq || hc || hd || he || hf || hg || hh || hi || hj || hk || hl || hm || hn || ho || hp || hr || hs || ht || hu || hv || ig || cd || hw || ia || ii || cl || hy || ih || showBox || showPickItem || showItemThrow || showDelItem || showItemGather || showAutoUseItem || ci || ij || ik || io || il || im;
    }

    private static boolean ch() {
        return showAutoPanel || cg || ifa || isPaintInfoMe || ha || hb || hq || hc || hd || he || hf || hg || hh || hi || hj || hk || hl || hm || hn || ho || hp || hr || hs || ht || hu || hv || ig || cd || hw || ia || ii || cl || hy || ih || showBox || showPickItem || showItemThrow || showDelItem || showItemGather || showAutoUseItem || ci || ij || ik || io || il || im;
    }

    private static boolean ci() {
        return gt || isPaintInfoMe && indexMenu > 0 && indexMenu < 5 || showAutoPanel || showZoneDialog || id && (indexMenu == 0 || indexMenu == 1 || indexMenu == 3 || indexMenu == 4) || ib || gv || gw || cf || gx || gy || gu || ck || ce || in;
    }

    private static void a(Item var0) {
        Command var1 = new Command(mResources.di, 11055, var0);
        GameCanvas.ak.a(mResources.kc, var1, 1);
    }

    private static void b(Item var0) {
        // if (var0.i > 0 && var0.arrEfInfo()) {
        //    Class_gb.aj.idEf(Class_ay.ks, (Class_bx)null, new Class_bx(Class_ay.am, 110561), (Class_bx)null);
        //    Class_gb.ai = Class_gb.aj;
        // } else 
        if (var0.quantity > 1) {
            Command var1 = new Command(mResources.di, 110562, var0);
            GameCanvas.ak.a(mResources.kc, var1, 1);
        } else {
            GameCanvas.a(mResources.km, new Command(mResources.bn, 11061, var0), new Command(mResources.ca, 1));
        }

    }

    private static void cj() {
        Command var0 = new Command(mResources.di, 11042);
        GameCanvas.ak.a(mResources.kb, var0, 1);
    }

    private static void ck() {
        Command var0 = new Command(mResources.di, 110361);
        GameCanvas.ak.a(mResources.kb, var0, 1);
    }

    private static void cl() {
        Command var0 = new Command(mResources.di, 11043);
        GameCanvas.ak.a(mResources.kb, var0, 1);
    }

    public final void aa() {
        if (GameCanvas.currentDialog == null && z()) {
            int var1;
            int var2;
            int var3;
            int var5;
            if (gk == 0) {
                GameScr var8 = this;
                super.left = super.center = null;
                if (!ifa) {
                    if (showBox) {
                        if (indexMenu == 0) {
                            if (svTitle.equals("")) {
                                super.left = new Command(mResources.af, 11115);
                            }
                        } else if (indexMenu == 1) {
                            if (svTitle.equals("")) {
                                super.left = new Command(mResources.af, 11116);
                            } else {
                                super.left = this.om;
                            }

                        }
                    } else if (indexMenu == 1 && ci && this.cz == 0) {
                        super.left = this.mq;
                    } else if (indexMenu == 1 && !ha && !hb && !hy && !ih && !hv && !hw && !ia && !ii && !cl && !ci && !ig && !cd && !ij && !ik && !io && !il && !im) {
                        super.left = new Command(mResources.sapXep, 110221);
                    }

                    if (ih && indexMenu == 0) {
                        var1 = 0;
                        var2 = 0;
                        var3 = 0;
                        var5 = 0;

                        for (int var6 = 0; var6 < arrItemUpPeal.length; ++var6) {
                            Item var7;
                            if ((var7 = arrItemUpPeal[var6]) != null) {
                                if (var7.template.id == 455) {
                                    ++var1;
                                } else if (var7.template.id == 456) {
                                    ++var2;
                                } else if (var7.template.type == 26) {
                                    ++var3;
                                    var5 = var7.template.id;
                                }
                            }
                        }

                        if (var1 >= 9 || var2 >= 9 || var5 >= 10 && var1 >= 3 && var3 == 1 || var5 >= 11 && var2 >= 3 && var3 == 1) {
                            super.left = new Command(mResources.fc, 1600);
                        }
                    }

                    if (hy && indexMenu == 0) {
                        var1 = 0;

                        for (var2 = 0; var2 < arrItemUpPeal.length; ++var2) {
                            if (arrItemUpPeal[var2] != null) {
                                ++var1;
                                if (var1 >= 2) {
                                    super.left = new Command(mResources.fc, 11062);
                                    break;
                                }
                            }
                        }
                    }

                    if (hv && indexMenu == 0 && itemUpGrade != null) {
                        for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                            if (arrItemUpGrade[var1] != null) {
                                super.center = new Command("", 110981);
                                super.left = new Command(mResources.fc, 110981);
                                break;
                            }
                        }
                    }

                    if (ik && indexMenu == 0 && itemUpGrade != null && itemSplit != null) {
                        for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                            if (arrItemUpGrade[var1] != null) {
                                super.center = new Command("", 341);
                                super.left = new Command(mResources.fc, 341);
                                break;
                            }
                        }
                    }

                    if (cd && indexMenu == 0 && itemSell != null && this.iz != null && !this.iz.getText().equals("") && Char.getMyChar().xu >= 5000) {
                        super.left = new Command(mResources.bu, 15002);
                    }

                    if (hw && indexMenu == 0 && gk == 0) {
                        for (var1 = 0; var1 < arrItemConvert.length; ++var1) {
                            if (arrItemConvert[var1] == null) {
                                var8.left = null;
                                break;
                            }

                            if (var1 == arrItemConvert.length - 1) {
                                var8.left = new Command(mResources.fc, 140131);
                            }
                        }
                    }

                    if (ia && indexMenu == 0 && itemSplit != null && itemSplit.upgrade > 0) {
                        var8.left = new Command(mResources.fc, 11105);
                    }

                    if (ci && indexMenu == 0) {
                        if (var8.cz == 0) {
                            var8.left = var8.mi;
                        } else if (var8.cz == 1 && var8.da > 0 && (long) var8.dd - System.currentTimeMillis() / 1000L <= 0L) {
                            var8.left = var8.mj;
                        }
                    }

                    if (GameCanvas.keyPressedz[8]) {
                        gk = 1;
                        indexSelect = 0;
                        indexRow = -1;
                        scrMain.a();
                        gn.a();
                        var8.ab();
                    }

                    if (GameCanvas.keyPressedz[4]) {
                        indexSelect = 0;
                        indexRow = -1;
                        --indexMenu;
                        scrMain.a();
                        gn.a();
                        if (ig) {
                            if (indexMenu < 0) {
                                indexMenu = mResources.qs.length - 1;
                            }

                            Service.getInstance().menu(28, 0, indexMenu);
                            arrItemStands = null;
                            indexSelect = -1;
                        } else if (!hb && !showAutoPanel) {
                            if (indexMenu < 0) {
                                if (ha) {
                                    indexMenu = mResources.gi.length - 1;
                                } else {
                                    indexMenu = 1;
                                }
                            }
                        } else {
                            indexMenu = 0;
                        }

                        var8.left = var8.center = null;
                        if (ha) {
                            cn();
                        }

                        if (hb && arrItemElites == null && indexMenu == 0) {
                            Service.getInstance().requestItem(35);
                        }
                    }

                    if (GameCanvas.keyPressedz[6]) {
                        indexSelect = 0;
                        indexRow = -1;
                        ++indexMenu;
                        scrMain.a();
                        gn.a();
                        if (ig) {
                            if (indexMenu > mResources.qs.length - 1) {
                                indexMenu = 0;
                            }

                            Service.getInstance().menu(28, 0, indexMenu);
                            arrItemStands = null;
                            indexSelect = -1;
                        } else {
                            label1480:
                            {
                                if (!hb && !showAutoPanel) {
                                    if (ha) {
                                        if (indexMenu <= mResources.gi.length - 1) {
                                            break label1480;
                                        }
                                    } else if (indexMenu <= 1) {
                                        break label1480;
                                    }
                                }

                                indexMenu = 0;
                            }
                        }

                        var8.left = var8.center = null;
                        if (ha) {
                            cn();
                        }

                        if (hb && arrItemElites == null && indexMenu == 0) {
                            Service.getInstance().requestItem(35);
                        }
                    }
                }
            } else if (gk > 0) {
                if (cg) {
                    if (GameCanvas.keyPressedz[2]) {
                        if (--indexRow < 0) {
                            indexRow = totalRowSetting - 1;
                        }

                        gn.a(indexRow * gn.h);
                    } else if (GameCanvas.keyPressedz[8]) {
                        if (++indexRow >= totalRowSetting) {
                            indexRow = 0;
                        }

                        gn.a(indexRow * gn.h);
                    }
                } else {
                    if (showAutoPanel) {
                        if (GameCanvas.keyPressedz[2]) {
                            if (--indexRow < 0) {
                                indexRow = totalRowSetting - 1;
                            }

                            scrMain.a(indexRow * scrMain.h);
                        } else if (GameCanvas.keyPressedz[4]) {
                            if (indexRow == 0) {
                                if (Char.aHpValue <= 10) {
                                    if (--Char.aHpValue <= 0) {
                                        Char.aHpValue = 1;
                                    }
                                } else {
                                    Char.aHpValue -= 10;
                                }
                            } else if (indexRow == 1) {
                                if (Char.aMpValue <= 10) {
                                    if (--Char.aMpValue <= 0) {
                                        Char.aMpValue = 1;
                                    }
                                } else {
                                    Char.aMpValue -= 10;
                                }
                            } else if (indexRow == 2) {
                                if ((Char.aFoodValue -= 10) <= 0) {
                                    Char.aFoodValue = 1;
                                }
                            } else if (indexRow == 8) {
                                if ((Char.ev -= 20) <= 0) {
                                    Char.ev = 1;
                                }
                            } else if (indexRow == 9) {
                                if (--Char.ew <= 0) {
                                    Char.ew = 1;
                                }
                            } else if (indexRow == 10) {
                                if (--Char.ex <= 4) {
                                    Char.ex = 4;
                                }
                            } else if (indexRow == 11 && (Char.ey -= 10) <= 0) {
                                Char.ey = 1;
                            }
                        } else if (GameCanvas.keyPressedz[6]) {
                            if (indexRow == 0) {
                                if (Char.aHpValue >= 90) {
                                    if (++Char.aHpValue >= 100) {
                                        Char.aHpValue = 99;
                                    }
                                } else {
                                    Char.aHpValue += 10;
                                }
                            } else if (indexRow == 1) {
                                if (Char.aMpValue >= 90) {
                                    if (++Char.aMpValue >= 100) {
                                        Char.aMpValue = 99;
                                    }
                                } else {
                                    Char.aMpValue += 10;
                                }
                            } else if (indexRow == 2) {
                                if (Char.aFoodValue == 1) {
                                    Char.aFoodValue = 10;
                                } else if ((Char.aFoodValue += 10) > 70) {
                                    Char.aFoodValue = 70;
                                }
                            } else if (indexRow == 8) {
                                if (Char.ev == 1) {
                                    Char.ev = 10;
                                } else if ((Char.ev += 20) > 70) {
                                    Char.ev = 70;
                                }
                            } else if (indexRow == 9) {
                                if (++Char.ew > 7) {
                                    Char.ew = 7;
                                }
                            } else if (indexRow == 10) {
                                if (++Char.ex > 12) {
                                    Char.ex = 12;
                                }
                            } else if (indexRow == 11) {
                                if (Char.ey == 1) {
                                    Char.ey = 10;
                                } else if ((Char.ey += 10) > 70) {
                                    Char.ey = 70;
                                }
                            }
                        } else if (GameCanvas.keyPressedz[8]) {
                            if (++indexRow >= totalRowSetting) {
                                indexRow = 0;
                            }

                            scrMain.a(indexRow * scrMain.h);
                        }

                        if (!GameCanvas.isTouch) {
                            GameCanvas.l();
                            GameCanvas.k();
                        }
                    } else if (ci && indexMenu == 0) {
                        if (gk == 1) {
                            if (GameCanvas.keyPressedz[4]) {
                                if (--indexSelect < 0) {
                                    indexSelect = 11;
                                }

                                super.left = super.center = null;
                                this.ab();
                            } else if (GameCanvas.keyPressedz[6]) {
                                if (indexSelect == 2) {
                                    gk = 2;
                                    indexSelect = 0;
                                } else if (indexSelect == 5) {
                                    gk = 2;
                                    indexSelect = 3;
                                } else if (indexSelect == 8) {
                                    gk = 2;
                                    indexSelect = 6;
                                } else if (indexSelect == 11) {
                                    gk = 2;
                                    indexSelect = 9;
                                } else if (indexSelect == 14) {
                                    gk = 2;
                                    indexSelect = 12;
                                } else if (++indexSelect >= 12) {
                                    indexSelect = 0;
                                }

                                super.left = super.center = null;
                                this.ab();
                            } else if (GameCanvas.keyPressedz[8]) {
                                if (indexSelect + 3 <= 11) {
                                    indexSelect += 3;
                                }

                                super.left = super.center = null;
                                this.ab();
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (indexSelect >= 0 && indexSelect < 3) {
                                    gk = 0;
                                    indexSelect = 0;
                                } else if (indexSelect - 3 >= 0) {
                                    indexSelect -= 3;
                                }

                                super.left = super.center = null;
                                this.ab();
                            }
                        } else if (gk == 2) {
                            if (GameCanvas.keyPressedz[4]) {
                                if (indexSelect == 0) {
                                    gk = 1;
                                    indexSelect = 2;
                                } else if (indexSelect == 3) {
                                    gk = 1;
                                    indexSelect = 5;
                                } else if (indexSelect == 6) {
                                    gk = 1;
                                    indexSelect = 8;
                                } else if (indexSelect == 9) {
                                    gk = 1;
                                    indexSelect = 11;
                                } else if (indexSelect == 12) {
                                    gk = 1;
                                    indexSelect = 14;
                                } else if (--indexSelect < 0) {
                                    indexSelect = 11;
                                }

                                super.left = super.center = null;
                                this.ab();
                            } else if (GameCanvas.keyPressedz[6]) {
                                if (++indexSelect >= 12) {
                                    indexSelect = 0;
                                }

                                super.left = super.center = null;
                                this.ab();
                            } else if (GameCanvas.keyPressedz[8]) {
                                if (indexSelect + 3 <= 11) {
                                    indexSelect += 3;
                                }

                                super.left = super.center = null;
                                this.ab();
                            } else if (GameCanvas.keyPressedz[2]) {
                                if (indexSelect >= 0 && indexSelect < 3) {
                                    gk = 0;
                                    indexSelect = 0;
                                } else if (indexSelect - 3 >= 0) {
                                    indexSelect -= 3;
                                }

                                super.left = super.center = null;
                                this.ab();
                            }
                        }

                        if (!GameCanvas.isTouch) {
                            GameCanvas.l();
                            GameCanvas.k();
                        }
                    } else if (ifa) {
                        if (GameCanvas.keyPressedz[4]) {
                            if (--indexSelect < 0) {
                                indexSelect = 8;
                            }
                        } else if (GameCanvas.keyPressedz[6]) {
                            if (++indexSelect > 8) {
                                indexSelect = 0;
                            }
                        } else if (GameCanvas.keyPressedz[8]) {
                            if (indexSelect + 3 < 9) {
                                indexSelect += 3;
                            }
                        } else if (GameCanvas.keyPressedz[2] && indexSelect - 3 >= 0) {
                            indexSelect -= 3;
                        }

                        if (!GameCanvas.isTouch) {
                            GameCanvas.l();
                            GameCanvas.k();
                        }
                    } else if (cd && indexMenu == 0) {
                        if (GameCanvas.keyPressedz[2]) {
                            if (--gk < 0) {
                                gk = 0;
                            }
                        } else if (GameCanvas.keyPressedz[8] && ++gk > 2) {
                            gk = 2;
                        }

                        this.ab();
                    } else if (ig) {
                        if (GameCanvas.keyPressedz[4]) {
                            if (arrItemStands != null) {
                                if ((indexSelect -= 5) < 0) {
                                    indexSelect = arrItemStands.length - 1;
                                }

                                scrMain.a(indexSelect * scrMain.h);
                                this.ab();
                            }
                        } else if (GameCanvas.keyPressedz[6]) {
                            if (arrItemStands != null) {
                                if ((indexSelect += 5) >= arrItemStands.length) {
                                    indexSelect = 0;
                                }

                                scrMain.a(indexSelect * scrMain.h);
                                this.ab();
                            }
                        } else if (GameCanvas.keyPressedz[8]) {
                            if (arrItemStands != null) {
                                if (++indexSelect >= arrItemStands.length) {
                                    indexSelect = 0;
                                }

                                scrMain.a(indexSelect * scrMain.h);
                                this.ab();
                            }
                        } else if (GameCanvas.keyPressedz[2] && arrItemStands != null) {
                            if (--indexSelect < 0) {
                                gk = 0;
                            }

                            scrMain.a(indexSelect * scrMain.h);
                            this.ab();
                        }

                        GameCanvas.l();
                        GameCanvas.k();
                    } else if ((io || ik || hv || hw) && gk == 1 && indexMenu == 0) {
                        if (GameCanvas.keyPressedz[4]) {
                            if (--indexSelect < 0) {
                                indexSelect = 1;
                            }

                            super.left = super.center = null;
                            this.ab();
                        } else if (GameCanvas.keyPressedz[6]) {
                            if (++indexSelect > 1) {
                                indexSelect = 0;
                            }

                            super.left = super.center = null;
                            this.ab();
                        } else if (GameCanvas.keyPressedz[8]) {
                            gk = 2;
                            indexSelect = 0;
                            super.left = super.center = null;
                            this.ab();
                        } else if (GameCanvas.keyPressedz[2]) {
                            gk = 0;
                            indexSelect = 0;
                            super.left = super.center = null;
                            this.ab();
                        }

                        if (!GameCanvas.isTouch) {
                            GameCanvas.l();
                            GameCanvas.k();
                        }
                    } else if ((ia || ii || cl || ij) && gk == 1 && indexMenu == 0) {
                        if (GameCanvas.keyPressedz[8]) {
                            gk = 2;
                            indexSelect = 0;
                            super.left = super.center = null;
                            this.ab();
                        } else if (GameCanvas.keyPressedz[2]) {
                            gk = 0;
                            indexSelect = 0;
                            super.left = super.center = null;
                            this.ab();
                        }

                        GameCanvas.l();
                        GameCanvas.k();
                    } else if ((il || im) && indexMenu == 0) {
                        if (GameCanvas.keyPressedz[2]) {
                            if (--gk < 0) {
                                gk = 0;
                            }
                        } else if (GameCanvas.keyPressedz[8] && ++gk > 1) {
                            gk = 1;
                        }

                        this.ab();
                    }

                    int var4 = cm();
                    if (GameCanvas.keyPressedz[4]) {
                        if (--indexSelect < 0) {
                            indexSelect = var4 - 1;
                        }

                        super.left = super.center = null;
                        this.ab();
                        scrMain.a(indexSelect / ob * scrMain.h);
                    } else if (GameCanvas.keyPressedz[6]) {
                        if (++indexSelect >= var4) {
                            indexSelect = 0;
                        }

                        super.left = super.center = null;
                        this.ab();
                        scrMain.a(indexSelect / ob * scrMain.h);
                    } else if (GameCanvas.keyPressedz[8]) {
                        if (indexSelect + ob <= var4 - 1) {
                            indexSelect += ob;
                        }

                        super.left = super.center = null;
                        this.ab();
                        scrMain.a(indexSelect / ob * scrMain.h);
                    } else if (GameCanvas.keyPressedz[2]) {
                        if (indexSelect >= 0 && indexSelect < ob) {
                            gk = 0;
                            indexSelect = 0;
                            if ((ik || hv || ia || ii || cl || hw || ij) && indexMenu == 0) {
                                gk = 1;
                            }
                        } else if (indexSelect - ob >= 0) {
                            indexSelect -= ob;
                        }

                        super.left = super.center = null;
                        this.ab();
                        scrMain.a(indexSelect / ob * scrMain.h);
                    }
                }
            }

            if (GameCanvas.isTouch && !GameCanvas.menu.showMenu && GameCanvas.currentDialog == null) {
                label1722:
                {
                    if (GameCanvas.isPointerJustRelease && GameCanvas.b(popupX, popupY, ew, this.os) && (!cg || GameCanvas.width >= 320) && GameCanvas.isPointerClick) {
                        if (GameCanvas.b(d - 80, popupY + 5, 60, 40)) {
                            indexSelect = 0;
                            --indexMenu;
                            this.ab();
                        }

                        if (GameCanvas.b(d + 10, popupY + 5, 60, 40)) {
                            indexSelect = 0;
                            ++indexMenu;
                            this.ab();
                        }

                        gk = 0;
                        if (!z()) {
                            if (indexMenu < 0) {
                                indexMenu = mResources.nameMenuIndex.length - 1;
                            }

                            if (indexMenu > mResources.nameMenuIndex.length - 1) {
                                indexMenu = 0;
                            }
                        } else {
                            if (ha) {
                                if (indexMenu < 0) {
                                    indexMenu = mResources.gi.length - 1;
                                } else if (indexMenu > mResources.gi.length - 1) {
                                    indexMenu = 0;
                                }

                                cn();
                            } else if (ig) {
                                if (indexMenu < 0) {
                                    indexMenu = mResources.qs.length - 1;
                                } else if (indexMenu > mResources.qs.length - 1) {
                                    indexMenu = 0;
                                }

                                Service.getInstance().menu(28, 0, indexMenu);
                                indexSelect = -1;
                            } else {
                                label1447:
                                {
                                    if (!hb && !showAutoPanel) {
                                        if (indexMenu < 0) {
                                            indexMenu = 1;
                                            break label1447;
                                        }

                                        if (indexMenu <= 1) {
                                            break label1447;
                                        }
                                    }

                                    indexMenu = 0;
                                }
                            }

                            if (hb) {
                                indexMenu = 0;
                            }
                        }

                        cg = false;
                        scrMain.a();
                    }

                    ScrollResult var9;
                    if (cg) {
                        if ((var9 = gn.b()).a || var9.c) {
                            indexRow = var9.b;
                            gk = 1;
                        }

                        if (!GameCanvas.isMinWidth320) {
                            break label1722;
                        }
                    }

                    if (showAutoPanel) {

                        if (((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                            gk = 1;
                            indexRow = var9.b;
                            this.ab();
                        }
                        int LeftBtn_____ = 16;
                        int ____RightBtn = 131;

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 5, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoUseHP = !Char.tickAutoUseHP;
                            if (ea == 1) {
                                Char.tickAutoUseHP = false;
                                InfoMe.a(mResources.rl, 20, mFont.tahoma_7_yellow);
                            }

                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 35, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoUseMP = !Char.tickAutoUseMP;
                            if (ea == 1) {
                                Char.tickAutoUseMP = false;
                                InfoMe.a(mResources.rl, 20, mFont.tahoma_7_yellow);
                            }

                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 65, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoFood = !Char.tickAutoFood;
                            if (ea == 1) {
                                Char.tickAutoFood = false;
                                InfoMe.a(mResources.rl, 20, mFont.tahoma_7_yellow);
                            }

                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 95, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoBuff = !Char.tickAutoBuff;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 125, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.dl = !Char.dl;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 155, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.dm = !Char.dm;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 185, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.dk = !Char.dk;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 215, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.dn = !Char.dn;
                            GameCanvas.m = false;
                            if (Char.dn) {
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 245, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.doa = !Char.doa;
                            GameCanvas.m = false;
                            if (Char.doa) {
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 275, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickNhatDa = !Char.tickNhatDa;
                            GameCanvas.m = false;
                            if (Char.tickNhatDa) {
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 305, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickLuyenDaMax = !Char.tickLuyenDaMax;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 335, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickNhatTrangBi = !Char.tickNhatTrangBi;
                            GameCanvas.m = false;
                            if (Char.tickNhatTrangBi) {
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 365, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickNhatTrangBiLa = !Char.tickNhatTrangBiLa;
                            GameCanvas.m = false;
                            if (Char.tickNhatTrangBiLa) {
                                Char.tickNhatTrangBi = true;
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 395, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickNhatVPNhiemVu = !Char.tickNhatVPNhiemVu;
                            GameCanvas.m = false;
                            if (Char.tickNhatVPNhiemVu) {
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 425, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickNhatVPSK = !Char.tickNhatVPSK;
                            GameCanvas.m = false;
                            if (Char.tickNhatVPSK) {
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 455, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickNhatTatCa = !Char.tickNhatTatCa;
                            GameCanvas.m = false;
                            if (Char.tickNhatTatCa) {
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 485, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickNhatSVC = !Char.tickNhatSVC;
                            GameCanvas.m = false;
                            if (Char.tickNhatSVC) {
                                Char.tickKhongNhat = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 515, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickKhongNhat = !Char.tickKhongNhat;
                            GameCanvas.m = false;
                            if (Char.tickKhongNhat) {
                                Char.tickNhatDa = false;
                                Char.doa = false;
                                Char.dn = false;
                                Char.tickNhatTrangBi = false;
                                Char.tickNhatTrangBiLa = false;
                                Char.tickNhatVPNhiemVu = false;
                                Char.tickNhatVPSK = false;
                                Char.tickNhatTatCa = false;
                                Char.tickNhatSVC = false;
                            }
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 545, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoTTT = !Char.tickAutoTTT;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 575, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoTTC = !Char.tickAutoTTC;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 605, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickReMap = !Char.tickReMap;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 635, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickTSMapEmpty = !Char.tickTSMapEmpty;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 665, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoMuaTA = !Char.tickAutoMuaTA;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 695, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickDieKhiHetMP = !Char.tickDieKhiHetMP;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 725, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoReconnect = !Char.tickAutoReconnect;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 755, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickChuyenMapHetBoss = !Char.tickChuyenMapHetBoss;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 131, scrMain.d + 755, 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickChuyenMapHetBoss) {
                            GameCanvas.ak.a("Thời gian chờ chuyển khu (tính bằng giây)", new Command(mResources.okey, 1517), 1);
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 785, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickSanTATL = !Char.tickSanTATL;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 815, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickDanhQuaiThuong = !Char.tickDanhQuaiThuong;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 845, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickDanhTinhAnh = !Char.tickDanhTinhAnh;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 875, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickDanhThuLinh = !Char.tickDanhThuLinh;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 905, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickCongTiemNang = !Char.tickCongTiemNang;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 935, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickCongKyNang = !Char.tickCongKyNang;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + 965, 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickDanhTheoNhom = !Char.tickDanhTheoNhom;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + dyRowAuto[33], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickNePK = !Char.tickNePK;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + dyRowAuto[34], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoCoLenh = !Char.tickAutoCoLenh;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + dyRowAuto[35], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoMuaCoLenh = !Char.tickAutoMuaCoLenh;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + dyRowAuto[36], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoLangThuyenThuyet = !Char.tickAutoLangThuyenThuyet;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 131, scrMain.d + dyRowAuto[36], 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickAutoLangThuyenThuyet) {
                            GameCanvas.ak.a("Nhập ID của Truyền Thuyết Lệnh", new Command(mResources.okey, 1513), 1);
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + 16, scrMain.d + dyRowAuto[37], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoMuaTruyenThuyetLenh = !Char.tickAutoMuaTruyenThuyetLenh;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + ____RightBtn, scrMain.d + dyRowAuto[37], 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickAutoMuaTruyenThuyetLenh) {
                            GameCanvas.ak.a("Nhập vị trí mua Truyền Thuyết Lệnh trong shop", new Command(mResources.okey, 1514), 1);
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + LeftBtn_____, scrMain.d + dyRowAuto[38], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Code.tbNhanExp = !Code.tbNhanExp;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + LeftBtn_____, scrMain.d + dyRowAuto[39], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Code.tbNhanVP = !Code.tbNhanVP;
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + ____RightBtn, scrMain.d + dyRowAuto[40], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            GameCanvas.ak.a("Tốc độ tinh luyện (3-60s)", new Command("Đặt", 1518), 1);
                            GameCanvas.ak.tfInput.a(String.valueOf(Code.speedTinhLuyen));
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + LeftBtn_____, scrMain.d + dyRowAuto[41], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            Char.tickAutoLocDo = !Char.tickAutoLocDo;
                            GameCanvas.m = false;
                        }
                        if (GameCanvas.a(scrMain.c + LeftBtn_____, scrMain.d + dyRowAuto[42], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            if (!Char.tickAutoTTGT && !Code.isInClan(Char.getMyChar())) {
                                Code.setAutoTTGT(false);
                                GameScr.chatPopup("TTGT: chưa có gia tộc, không bật");
                            } else {
                                Code.setAutoTTGT(!Char.tickAutoTTGT);
                                GameScr.chatPopup((Char.tickAutoTTGT ? "Bật" : "Tắt") + " auto TTGT");
                            }

                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + ____RightBtn, scrMain.d + dyRowAuto[42], 30, 20, scrMain) && GameCanvas.isPointerClick) {
                            GameScr.chatPopup("TTGT option 604: " + Code.nextTTGTOption());
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + LeftBtn_____, scrMain.d + dyRowAuto[43], 16, 16, scrMain) && GameCanvas.isPointerClick) {
                            SelectCharScr.toggleQuickLogin();
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + ____RightBtn, scrMain.d + 2, 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickAutoUseHP) {
                            GameCanvas.ak.a(mResources.ri, new Command(mResources.okey, 1511), 1);
                            GameCanvas.m = false;
                        }

                        if (GameCanvas.a(scrMain.c + ____RightBtn, scrMain.d + 32, 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickAutoUseMP) {
                            GameCanvas.ak.a(mResources.ri, new Command(mResources.okey, 1512), 1);
                            GameCanvas.m = false;
                        }

                        MyVector var10;
                        if (GameCanvas.a(scrMain.c + ____RightBtn, scrMain.d + 62, 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickAutoFood) {
                            (var10 = new MyVector()).addElement(new Command(mResources.rj[0], 15130));
                            var10.addElement(new Command(mResources.rj[1], 15131));
                            var10.addElement(new Command(mResources.rj[2], 15132));
                            var10.addElement(new Command(mResources.rj[3], 15133));
                            var10.addElement(new Command(mResources.rj[4], 15134));
                            var10.addElement(new Command(mResources.rj[5], 15135));
                            var10.addElement(new Command(mResources.rj[6], 15136));
                            var10.addElement(new Command(mResources.rj[7], 15137));
                            GameCanvas.menu.showMenu(var10);
                            GameCanvas.menu.c = true;
                        }

                        if (GameCanvas.a(scrMain.c + 131, scrMain.d + 242, 30, 20, scrMain) && GameCanvas.isPointerClick && Char.doa) {
                            (var10 = new MyVector()).addElement(new Command(mResources.rj[0], 15150));
                            var10.addElement(new Command(mResources.rj[1], 15151));
                            var10.addElement(new Command(mResources.rj[3], 15153));
                            var10.addElement(new Command(mResources.rj[5], 15155));
                            var10.addElement(new Command(mResources.rj[7], 15157));
                            GameCanvas.menu.showMenu(var10);
                            GameCanvas.menu.c = true;
                        }

                        if (GameCanvas.a(scrMain.c + 131, scrMain.d + 272, 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickNhatDa) {
                            (var10 = new MyVector()).addElement(new Command(mResources.rk[0], 15161));
                            var10.addElement(new Command(mResources.rk[1], 15162));
                            var10.addElement(new Command(mResources.rk[2], 15163));
                            var10.addElement(new Command(mResources.rk[3], 15164));
                            var10.addElement(new Command(mResources.rk[4], 15165));
                            var10.addElement(new Command(mResources.rk[5], 15166));
                            var10.addElement(new Command(mResources.rk[6], 15167));
                            GameCanvas.menu.showMenu(var10);
                            GameCanvas.menu.c = true;
                        }

                        if (GameCanvas.a(scrMain.c + 131, scrMain.d + 302, 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickLuyenDaMax) {
                            (var10 = new MyVector()).addElement(new Command(mResources.rk[3], 15174));
                            var10.addElement(new Command(mResources.rk[4], 15175));
                            var10.addElement(new Command(mResources.rk[5], 15176));
                            var10.addElement(new Command(mResources.rk[6], 15177));
                            var10.addElement(new Command(mResources.rk[7], 15178));
                            var10.addElement(new Command(mResources.rk[8], 15179));
                            var10.addElement(new Command(mResources.rk[9], 151710));
                            var10.addElement(new Command(mResources.rk[10], 151711));
                            var10.addElement(new Command(mResources.rk[11], 151712));
                            GameCanvas.menu.showMenu(var10);
                            GameCanvas.menu.c = true;
                        }

                        if (GameCanvas.a(scrMain.c + 131, scrMain.d + 332, 30, 20, scrMain) && GameCanvas.isPointerClick && Char.tickNhatTrangBi) {
                            (var10 = new MyVector()).addElement(new Command(mResources.rj[0], 15130));
                            var10.addElement(new Command(mResources.rj[1], 15141));
                            var10.addElement(new Command(mResources.rj[2], 15142));
                            var10.addElement(new Command(mResources.rj[3], 15143));
                            var10.addElement(new Command(mResources.rj[4], 15144));
                            var10.addElement(new Command(mResources.rj[5], 15145));
                            var10.addElement(new Command(mResources.rj[6], 15146));
                            var10.addElement(new Command(mResources.rj[7], 15147));
                            GameCanvas.menu.showMenu(var10);
                            GameCanvas.menu.c = true;
                        }
                    } else if (!hb && !ha && !showBox && !showPickItem && !showDelItem && !showItemThrow && !showItemGather && !showAutoUseItem && !ht && !hu && !hr && !hs && !hc && !hd && !he && !hf && !hg && !hh && !hi && !hj && !hk && !hl && !hq && !hm && !hn && !ho && !hp) {
                        if (ci) {
                            if (indexMenu == 0) {
                                this.a(popupX + 4, popupY + this.os + 15, 3, 4, 1);
                                this.a(popupX + ew - 3 - 3 * wItem, popupY + this.os + 15, 3, 4, 2);
                            } else if (indexMenu == 1 && ((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                                gk = 1;
                                indexSelect = var9.b;
                                this.ab();
                            }
                        } else if (!hy && !ih) {
                            if (!ia && !ii && !cl && !ij) {
                                if (!hv && !hw && !ik) {
                                    if (io) {
                                        if (indexMenu == 0) {
                                            if (((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                                                gk = 1;
                                                indexSelect = var9.b;
                                                super.left = super.center = null;
                                                cg = false;
                                                this.ab();
                                            }
                                        } else if (indexMenu == 1 && ((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                                            gk = 1;
                                            indexSelect = var9.b;
                                            this.ab();
                                        }
                                    } else if (cd) {
                                        if (indexMenu == 0) {
                                            if (GameCanvas.b(popupX + 75, popupY + 69, wItem, wItem)) {
                                                if (GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                                                    gk = 1;
                                                    this.ab();
                                                }
                                            } else if (GameCanvas.b(this.iz.a, this.iz.b, this.iz.width, this.iz.height) && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                                                gk = 2;
                                                this.iz.a();
                                                this.ab();
                                            }
                                        } else if (indexMenu == 1 && ((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                                            gk = 1;
                                            indexSelect = var9.b;
                                            this.ab();
                                        }
                                    } else if (ifa) {
                                        if (GameCanvas.b(leftMargin, nw, 120, 120) && GameCanvas.isPointerJustRelease && GameCanvas.isPointerClick) {
                                            indexSelect = (GameCanvas.r - leftMargin) / 40 + (GameCanvas.s - nw) / 40 * 3;
                                            gk = 1;
                                            this.ab();
                                            this.cx();
                                        }
                                    } else if (il || im) {
                                        if (indexMenu == 0) {
                                            if (GameCanvas.b(popupX + 75, popupY + 69, wItem, wItem) && GameCanvas.isPointerClick && GameCanvas.isPointerJustRelease) {
                                                gk = 1;
                                                this.ab();
                                            }
                                        } else if (indexMenu == 1 && ((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                                            gk = 1;
                                            indexSelect = var9.b;
                                            this.ab();
                                        }
                                    }
                                } else if (indexMenu == 0) {
                                    var1 = popupX + 45;
                                    var2 = popupY + 32;
                                    var3 = popupX + 100;
                                    if (GameCanvas.isPointerJustRelease) {
                                        if (GameCanvas.b(var1, var2, 29, 29)) {
                                            gk = 1;
                                            indexSelect = 0;
                                            this.ab();
                                        }

                                        if (GameCanvas.b(var3, var2, 29, 29)) {
                                            gk = 1;
                                            indexSelect = 1;
                                            this.ab();
                                            if (hy && indexMenu == 0) {
                                                for (var5 = 0; var5 < arrItemUpPeal.length; ++var5) {
                                                    if (arrItemUpPeal[var5] != null) {
                                                        super.center = new Command(mResources.di, 11062);
                                                        break;
                                                    }
                                                }
                                            }
                                        }

                                        if (GameCanvas.b(popupX, popupY + 2 * this.os + 5, ew, ex - this.os * 3)) {
                                            this.a(popupX, popupY + 2 * this.os + 5, 6, 3, 2);
                                        }
                                    }
                                } else if (indexMenu == 1 && ((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                                    gk = 1;
                                    indexSelect = var9.b;
                                    this.ab();
                                }
                            } else if (indexMenu == 0) {
                                if (GameCanvas.b(popupX + 74, nw - wItem - 3, wItem, wItem)) {
                                    gk = 1;
                                    indexSelect = 0;
                                    this.ab();
                                }

                                this.a(popupX + 4, popupY + 2 * this.os + 5, 6, 4, 2);
                            } else if (indexMenu == 1 && ((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                                gk = 1;
                                indexSelect = var9.b;
                                this.ab();
                            }
                        } else if (indexMenu == 0) {
                            this.a(popupX + 4, popupY + this.os + 3, 6, 4, 1);
                        } else if (indexMenu == 1 && ((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                            gk = 1;
                            indexSelect = var9.b;
                            this.ab();
                        }
                    } else if (((var9 = scrMain.b()).c || var9.a) && indexSelect != var9.b) {
                        gk = 1;
                        indexSelect = var9.b;
                        super.left = super.center = null;
                        cg = false;
                        this.ab();
                        if (showAutoUseItem && var9.c) {
                            if (indexMenu == 0) {
                                if (indexSelect >= 0 && indexSelect < AutoUseItem.getListIds().length && AutoUseItem.getItemIdAt(indexSelect) > 0) {
                                    new FormAutoUseItem(indexSelect).select();
                                    return;
                                }
                            } else if (indexMenu == 1 && indexSelect >= 0 && indexSelect < Char.getMyChar().arrItemBag.length && Char.getMyChar().arrItemBag[indexSelect] != null) {
                                int itemId = Char.getMyChar().arrItemBag[indexSelect].template.id;
                                int ruleIndex = AutoUseItem.addDefault(itemId);
                                new FormAutoUseItem(ruleIndex).select();
                                return;
                            }
                        }
                    }
                }
            }

            GameCanvas.l();
            GameCanvas.k();
        }

    }

    private static int cm() {
        int var0 = 0;

        try {
            if (hb) {
                if (arrItemElites.length % ob == 0) {
                    var0 = arrItemElites.length;
                } else {
                    var0 = (arrItemElites.length / ob + 1) * ob;
                }
            } else if (ha) {
                if (indexMenu == 0) {
                    if (arrItemStore.length % ob == 0) {
                        var0 = arrItemStore.length;
                    } else {
                        var0 = (arrItemStore.length / ob + 1) * ob;
                    }
                } else if (indexMenu == 1) {
                    if (arrItemBook.length % ob == 0) {
                        var0 = arrItemBook.length;
                    } else {
                        var0 = (arrItemBook.length / ob + 1) * ob;
                    }
                } else if (indexMenu == 2) {
                    if (arrItemFashion.length % ob == 0) {
                        var0 = arrItemFashion.length;
                    } else {
                        var0 = (arrItemFashion.length / ob + 1) * ob;
                    }
                }
            } else if (hc) {
                if (arrItemNonNam.length % ob == 0) {
                    var0 = arrItemNonNam.length;
                } else {
                    var0 = (arrItemNonNam.length / ob + 1) * ob;
                }
            } else if (hd) {
                if (arrItemNonNu.length % ob == 0) {
                    var0 = arrItemNonNu.length;
                } else {
                    var0 = (arrItemNonNu.length / ob + 1) * ob;
                }
            } else if (he) {
                if (arrItemAoNam.length % ob == 0) {
                    var0 = arrItemAoNam.length;
                } else {
                    var0 = (arrItemAoNam.length / ob + 1) * ob;
                }
            } else if (hf) {
                if (arrItemAoNu.length % ob == 0) {
                    var0 = arrItemAoNu.length;
                } else {
                    var0 = (arrItemAoNu.length / ob + 1) * ob;
                }
            } else if (hg) {
                if (arrItemGangTayNam.length % ob == 0) {
                    var0 = arrItemGangTayNam.length;
                } else {
                    var0 = (arrItemGangTayNam.length / ob + 1) * ob;
                }
            } else if (hh) {
                if (arrItemGangTayNu.length % ob == 0) {
                    var0 = arrItemGangTayNu.length;
                } else {
                    var0 = (arrItemGangTayNu.length / ob + 1) * ob;
                }
            } else if (hi) {
                if (arrItemQuanNam.length % ob == 0) {
                    var0 = arrItemQuanNam.length;
                } else {
                    var0 = (arrItemQuanNam.length / ob + 1) * ob;
                }
            } else if (hj) {
                if (arrItemQuanNu.length % ob == 0) {
                    var0 = arrItemQuanNu.length;
                } else {
                    var0 = (arrItemQuanNu.length / ob + 1) * ob;
                }
            } else if (hk) {
                if (arrItemGiayNam.length % ob == 0) {
                    var0 = arrItemGiayNam.length;
                } else {
                    var0 = (arrItemGiayNam.length / ob + 1) * ob;
                }
            } else if (hl) {
                if (arrItemGiayNu.length % ob == 0) {
                    var0 = arrItemGiayNu.length;
                } else {
                    var0 = (arrItemGiayNu.length / ob + 1) * ob;
                }
            } else if (hm) {
                if (arrItemLien.length % ob == 0) {
                    var0 = arrItemLien.length;
                } else {
                    var0 = (arrItemLien.length / ob + 1) * ob;
                }
            } else if (hn) {
                if (arrItemNhan.length % ob == 0) {
                    var0 = arrItemNhan.length;
                } else {
                    var0 = (arrItemNhan.length / ob + 1) * ob;
                }
            } else if (ho) {
                if (arrItemNgocBoi.length % ob == 0) {
                    var0 = arrItemNgocBoi.length;
                } else {
                    var0 = (arrItemNgocBoi.length / ob + 1) * ob;
                }
            } else if (hp) {
                if (arrItemPhu.length % ob == 0) {
                    var0 = arrItemPhu.length;
                } else {
                    var0 = (arrItemPhu.length / ob + 1) * ob;
                }
            } else if (hq) {
                if (arrItemWeapon.length % ob == 0) {
                    var0 = arrItemWeapon.length;
                } else {
                    var0 = (arrItemWeapon.length / ob + 1) * ob;
                }
            } else if (hr) {
                if (arrItemStack.length % ob == 0) {
                    var0 = arrItemStack.length;
                } else {
                    var0 = (arrItemStack.length / ob + 1) * ob;
                }
            } else if (hs) {
                if (arrItemStackLock.length % ob == 0) {
                    var0 = arrItemStackLock.length;
                } else {
                    var0 = (arrItemStackLock.length / ob + 1) * ob;
                }
            } else if (ht) {
                if (arrItemGrocery.length % ob == 0) {
                    var0 = arrItemGrocery.length;
                } else {
                    var0 = (arrItemGrocery.length / ob + 1) * ob;
                }
            } else if (hu) {
                if (arrItemGroceryLock.length % ob == 0) {
                    var0 = arrItemGroceryLock.length;
                } else {
                    var0 = (arrItemGroceryLock.length / ob + 1) * ob;
                }
            }

            if (showBox) {
                var0 = Char.getMyChar().arrItemBox.length;
            }

            if (showAutoUseItem && indexMenu == 0) {
                var0 = AutoUseItem.getListIds().length;
            }

            if (indexMenu == 1 && !ha) {
                var0 = Char.getMyChar().arrItemBag.length;
            }
        } catch (Exception var2) {
        }

        if ((hy || ih || ia || ii || cl || ij) && indexMenu == 0) {
            var0 = 24;
        } else if ((ik || hv || hw) && indexMenu == 0) {
            var0 = 18;
        } else if (var0 < 30) {
            var0 = 30;
        }

        return var0;
    }

    private static void cn() {
        if (indexMenu == 0) {
            Service.getInstance().requestItem(14);
        } else if (indexMenu == 1) {
            Service.getInstance().requestItem(15);
        } else if (indexMenu == 2) {
            Service.getInstance().requestItem(32);
        } else if (indexMenu == 3) {
            Service.getInstance().requestItem(34);
        }

    }

    private void co() {
        if (Char.getMyChar().arrItemBag[indexSelect].quantity > 1) {
            super.left = new Command(mResources.bu, 11072);
        } else {
            super.left = new Command(mResources.bu, 11073);
        }

    }

    public final void ab() {
        super.left = super.center = null;
        if (indexSelect >= 0) {
            if (showAutoPanel) {
                if (gk == 1 && !GameCanvas.isTouch) {
                    super.left = new Command(mResources.chon, 1510);
                    return;
                }
            } else if (ifa) {
                if (gk == 1) {
                    super.left = new Command(mResources.chon, 1506);
                    super.center = new Command("", 1507);
                    return;
                }
            } else if (hb) {
                if (indexMenu == 0 && getCurrentItemSelectByTypeUI(35) != null) {
                    super.left = this.mx;
                    if (!GameCanvas.isMinWidth320) {
                        super.center = this.my;
                        return;
                    }

                    this.xemInfoItemTypeUI((byte) 35);
                    return;
                }
            } else if (ha) {
                if (indexMenu == 0) {
                    if (getCurrentItemSelectByTypeUI(14) != null) {
                        super.left = this.mz;
                        if (!GameCanvas.isMinWidth320) {
                            super.center = this.na;
                            return;
                        }

                        this.xemInfoItemTypeUI((byte) 14);
                        return;
                    }
                } else if (indexMenu == 1) {
                    if (getCurrentItemSelectByTypeUI(15) != null) {
                        super.left = this.nd;
                        if (!GameCanvas.isMinWidth320) {
                            super.center = this.ne;
                            return;
                        }

                        this.xemInfoItemTypeUI((byte) 15);
                        return;
                    }
                } else if (indexMenu == 2) {
                    if (getCurrentItemSelectByTypeUI(32) != null) {
                        super.left = this.nf;
                        if (!GameCanvas.isMinWidth320) {
                            super.center = this.ng;
                            return;
                        }

                        this.xemInfoItemTypeUI((byte) 32);
                        return;
                    }
                } else if (indexMenu == 3 && getCurrentItemSelectByTypeUI(34) != null) {
                    super.left = this.nb;
                    if (!GameCanvas.isMinWidth320) {
                        super.center = this.nc;
                        return;
                    }

                    this.xemInfoItemTypeUI((byte) 34);
                    return;
                }
            } else {
                if (hc) {
                    if (indexMenu == 0) {
                        if (getCurrentItemSelectByTypeUI(20) != null) {
                            super.left = this.nh;
                            if (!GameCanvas.isMinWidth320) {
                                super.center = this.ni;
                            } else {
                                this.xemInfoItemTypeUI((byte) 20);
                            }
                        }
                    } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                        this.co();
                    }
                } else if (hd) {
                    if (indexMenu == 0) {
                        if (getCurrentItemSelectByTypeUI(21) != null) {
                            super.left = this.nj;
                            if (!GameCanvas.isMinWidth320) {
                                super.center = this.nk;
                            } else {
                                this.xemInfoItemTypeUI((byte) 21);
                            }
                        }
                    } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                        this.co();
                    }
                } else if (he) {
                    if (indexMenu == 0) {
                        if (getCurrentItemSelectByTypeUI(22) != null) {
                            super.left = this.nl;
                            if (!GameCanvas.isMinWidth320) {
                                super.center = this.nm;
                            } else {
                                this.xemInfoItemTypeUI((byte) 22);
                            }
                        }
                    } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                        this.co();
                    }
                } else if (hf) {
                    if (indexMenu == 0) {
                        if (getCurrentItemSelectByTypeUI(23) != null) {
                            super.left = this.nn;
                            if (!GameCanvas.isMinWidth320) {
                                super.center = this.no;
                            } else {
                                this.xemInfoItemTypeUI((byte) 23);
                            }
                        }
                    } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                        this.co();
                    }
                } else if (hg) {
                    if (indexMenu == 0) {
                        if (getCurrentItemSelectByTypeUI(24) != null) {
                            super.left = this.np;
                            if (!GameCanvas.isMinWidth320) {
                                super.center = this.nq;
                            } else {
                                this.xemInfoItemTypeUI((byte) 24);
                            }
                        }
                    } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                        this.co();
                    }
                } else if (hh) {
                    if (indexMenu == 0) {
                        if (getCurrentItemSelectByTypeUI(25) != null) {
                            super.left = this.nr;
                            if (!GameCanvas.isMinWidth320) {
                                super.center = this.ns;
                            } else {
                                this.xemInfoItemTypeUI((byte) 25);
                            }
                        }
                    } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                        this.co();
                    }
                } else {
                    Item var10;
                    if (hi) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(26)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (!GameCanvas.isMinWidth320) {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11088, var10);
                                } else {
                                    this.getItemInfo((int) 26, (Item) var10);
                                }
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (hj) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(27)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (!GameCanvas.isMinWidth320) {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11089);
                                } else {
                                    this.getItemInfo((int) 27, (Item) var10);
                                }
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (hk) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(28)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (!GameCanvas.isMinWidth320) {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11090);
                                } else {
                                    this.getItemInfo((int) 28, (Item) var10);
                                }
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (hl) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(29)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (!GameCanvas.isMinWidth320) {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11091);
                                } else {
                                    this.getItemInfo((int) 29, (Item) var10);
                                }
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (hm) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(16)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 16, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 110923);
                                }
                            } else {
                                cg = false;
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (hn) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(17)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 17, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 110924);
                                }
                            } else {
                                cg = false;
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (ho) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(18)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 18, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 110925);
                                }
                            } else {
                                cg = false;
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (hp) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(19)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 19, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 110926);
                                }
                            } else {
                                cg = false;
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (hq) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(2)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 2, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11093);
                                }
                            } else {
                                cg = false;
                            }
                        } else if (indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                            this.co();
                        }
                    } else if (hr) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(6)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 6, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11094);
                                }
                            }
                        } else if (indexMenu == 1) {
                            if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                this.co();
                            } else {
                                super.left = this.om;
                            }
                        }
                    } else if (hs) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(7)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 7, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11095);
                                }
                            }
                        } else if (indexMenu == 1) {
                            if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                this.co();
                            } else {
                                super.left = this.om;
                            }
                        }
                    } else if (ht) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(8)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 8, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11096);
                                }
                            }
                        } else if (indexMenu == 1) {
                            if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                this.co();
                            } else {
                                super.left = this.om;
                            }
                        }
                    } else if (hu) {
                        if (indexMenu == 0) {
                            if ((var10 = getCurrentItemSelectByTypeUI(9)) != null) {
                                super.left = new Command(mResources.by, 11092, var10);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 9, (Item) var10);
                                } else {
                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11097);
                                }
                            }
                        } else if (indexMenu == 1) {
                            if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                this.co();
                            } else {
                                super.left = this.om;
                            }
                        }
                    } else {
                        int var2;
                        if (hv) {
                            if (indexMenu == 0) {
                                if (gk == 1) {
                                    if (itemUpGrade != null) {
                                        if (indexSelect == 0) {
                                            super.left = new Command(mResources.chon, 11098);
                                            if (GameCanvas.isMinWidth320) {
                                                gp = false;
                                                this.getItemInfo((int) 3, (Item) itemUpGrade);
                                            } else {
                                                super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11099);
                                            }
                                        } else if (indexSelect == 1 && !itemUpGrade.isUpMax()) {
                                            if (GameCanvas.isMinWidth320) {
                                                gp = true;
                                                this.getItemInfo((int) 3, (Item) itemUpGrade);
                                            } else {
                                                super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 110991);
                                            }
                                        }
                                    } else {
                                        cg = false;
                                    }
                                } else if (gk == 2) {
                                    var10 = getCurrentItemSelectByTypeUI(10);
                                    gp = false;
                                    if (var10 != null) {
                                        super.left = new Command(mResources.chon, 11100);
                                        if (GameCanvas.isMinWidth320) {
                                            this.getItemInfo((int) 3, (Item) var10);
                                        } else {
                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11101);
                                        }
                                    } else {
                                        super.left = null;
                                        cg = false;
                                        if (itemUpGrade != null) {
                                            for (var2 = 0; var2 < arrItemUpGrade.length; ++var2) {
                                                if (arrItemUpGrade[var2] != null) {
                                                    super.left = new Command(mResources.fc, 110981);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (indexMenu == 1) {
                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                    super.left = new Command(mResources.chon, 11102);
                                } else {
                                    super.left = null;
                                }
                            }
                        } else {
                            int var1;
                            if (hw) {
                                if (indexMenu != 0) {
                                    if (indexMenu == 1) {
                                        if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                            super.left = new Command(mResources.chon, 14012);
                                        } else {
                                            super.left = null;
                                        }
                                    }
                                } else if (gk == 1) {
                                    if (indexSelect == 0) {
                                        if (arrItemConvert[0] != null) {
                                            super.left = new Command(mResources.chon, 14013);
                                            if (GameCanvas.isMinWidth320) {
                                                this.getItemInfo((int) 3, (Item) arrItemConvert[indexSelect]);
                                            } else {
                                                super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 14016);
                                            }
                                        }
                                    } else if (indexSelect == 1) {
                                        if (arrItemConvert[1] != null) {
                                            super.left = new Command(mResources.chon, 14013);
                                            if (GameCanvas.isMinWidth320) {
                                                this.getItemInfo((int) 3, (Item) arrItemConvert[indexSelect]);
                                            } else {
                                                super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 14016);
                                            }
                                        }
                                    } else {
                                        cg = false;
                                    }
                                } else if (gk == 2) {
                                    var10 = null;
                                    if ((var2 = indexSelect + 2) <= arrItemConvert.length - 1) {
                                        var10 = arrItemConvert[var2];
                                    }

                                    if (var10 != null) {
                                        super.left = new Command(mResources.boRa, 140151);
                                        if (GameCanvas.isMinWidth320) {
                                            this.getItemInfo((int) 3, (Item) var10);
                                        } else {
                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 140161);
                                        }
                                    } else {
                                        super.left = new Command(mResources.fc, 140131);

                                        for (var1 = 0; var1 < arrItemConvert.length; ++var1) {
                                            if (arrItemConvert[var1] == null) {
                                                super.left = null;
                                                break;
                                            }
                                        }

                                        cg = false;
                                    }
                                }
                            } else {
                                int var3;
                                if (cd) {
                                    if (indexMenu == 0) {
                                        if (gk == 2) {
                                            this.iz.isFocus = true;
                                            super.right = this.iz.cmdClear;
                                        } else {
                                            this.iz.isFocus = false;
                                            super.right = this.mw;
                                        }

                                        var3 = 0;
                                        var3 = Integer.parseInt(this.iz.getText());

                                        if (itemSell != null && var3 > 0 && Char.getMyChar().xu >= 5000) {
                                            super.left = new Command(mResources.bu, 15002);
                                        }

                                        if (gk == 1 && itemSell != null) {
                                            super.left = new Command(mResources.chon, 1500);
                                            if (GameCanvas.isMinWidth320) {
                                                this.getItemInfo((int) 3, (Item) itemSell);
                                            } else {
                                                super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 1501);
                                            }
                                        }
                                    } else if (indexMenu == 1) {
                                        super.right = this.mw;
                                        if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                            super.left = new Command(mResources.chon, 1503);
                                        } else {
                                            super.left = null;
                                            cg = false;
                                        }
                                    }
                                } else {
                                    if (ig) {
                                        if (gk == 1 && arrItemStands != null && indexSelect >= 0 && indexSelect < arrItemStands.length && arrItemStands[indexSelect] != null) {
                                            super.left = new Command(mResources.chon, 1504);
                                            if (GameCanvas.isMinWidth320) {
                                                this.b(1505, (Object) null);
                                                return;
                                            }

                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 1505);
                                        }

                                        return;
                                    }

                                    if (ia) {
                                        if (indexMenu == 0) {
                                            if (gk != 1) {
                                                if (gk == 2) {
                                                    if ((var10 = arrItemSplit[indexSelect]) != null) {
                                                        if (GameCanvas.isMinWidth320) {
                                                            this.getItemInfo((int) 3, (Item) var10);
                                                        } else {
                                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, var10);
                                                        }
                                                    } else {
                                                        cg = false;
                                                    }

                                                    if (itemSplit != null && itemSplit.upgrade > 0) {
                                                        super.left = new Command(mResources.fc, 11105);
                                                    }
                                                }
                                            } else {
                                                if (itemSplit != null && itemSplit.upgrade > 0) {
                                                    super.left = new Command(mResources.chon, 11103);
                                                } else if (itemSplit != null) {
                                                    super.left = this.mp;
                                                } else {
                                                    cg = false;
                                                }

                                                if (GameCanvas.isMinWidth320) {
                                                    this.getItemInfo((int) 3, (Item) itemSplit);
                                                } else {
                                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, itemSplit);
                                                }
                                            }
                                        } else if (indexMenu == 1) {
                                            if (Char.getMyChar().arrItemBag[indexSelect] == null) {
                                                super.left = null;
                                                cg = false;
                                            } else {
                                                super.left = new Command(mResources.chon, 11106);
                                            }
                                        }
                                    } else if (ii) {
                                        try {
                                            if (indexMenu == 0) {
                                                if (gk == 1) {
                                                    if (itemSplit != null) {
                                                        super.left = new Command(mResources.chon, 11103);
                                                    } else {
                                                        cg = false;
                                                    }

                                                    if (GameCanvas.isMinWidth320) {
                                                        this.getItemInfo((int) 3, (Item) itemSplit);
                                                    } else {
                                                        super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, itemSplit);
                                                    }
                                                } else if (gk == 2) {
                                                    if ((var10 = arrItemSplit[indexSelect]) != null) {
                                                        if (GameCanvas.isMinWidth320) {
                                                            this.getItemInfo((int) 3, (Item) var10);
                                                        } else {
                                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, var10);
                                                        }

                                                        super.left = new Command(mResources.boRa, 1605);
                                                    } else {
                                                        cg = false;
                                                    }

                                                    if (itemSplit != null) {
                                                        super.left = new Command(mResources.chon, 1604);
                                                    }
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] == null) {
                                                    super.left = null;
                                                    cg = false;
                                                } else {
                                                    super.left = new Command(mResources.chon, 11106);
                                                }
                                            }
                                        } catch (Exception var9) {
                                        }
                                    } else if (cl) {
                                        if (indexMenu == 0) {
                                            if (gk != 1) {
                                                if (gk == 2) {
                                                    if ((var10 = arrItemSplit[indexSelect]) != null) {
                                                        if (GameCanvas.isMinWidth320) {
                                                            this.getItemInfo((int) 3, (Item) var10);
                                                        } else {
                                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, var10);
                                                        }
                                                    } else {
                                                        cg = false;
                                                    }

                                                    if (itemSplit != null && itemSplit.upgrade > 11) {
                                                        super.left = new Command(mResources.chon, 1604);
                                                    }
                                                }
                                            } else {
                                                if (itemSplit != null && itemSplit.upgrade > 11) {
                                                    super.left = new Command(mResources.chon, 11103);
                                                } else if (itemSplit != null) {
                                                    super.left = this.mp;
                                                } else {
                                                    cg = false;
                                                }

                                                if (GameCanvas.isMinWidth320) {
                                                    this.getItemInfo((int) 3, (Item) itemSplit);
                                                } else {
                                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, itemSplit);
                                                }
                                            }
                                        } else if (indexMenu == 1) {
                                            if (Char.getMyChar().arrItemBag[indexSelect] == null) {
                                                super.left = null;
                                                cg = false;
                                            } else {
                                                super.left = new Command(mResources.chon, 1606);
                                            }
                                        }
                                    } else if (hy) {
                                        if (indexMenu != 0) {
                                            if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(mResources.chon, 11109);
                                                } else {
                                                    cg = false;
                                                    super.left = null;
                                                }
                                            }
                                        } else {
                                            var3 = 0;

                                            for (var2 = 0; var2 < arrItemUpPeal.length; ++var2) {
                                                if (arrItemUpPeal[var2] != null) {
                                                    ++var3;
                                                    if (var3 >= 2) {
                                                        break;
                                                    }
                                                }
                                            }

                                            if ((var10 = getCurrentItemSelectByTypeUI(11)) != null) {
                                                if (var3 >= 2) {
                                                    super.left = new Command(mResources.chon, 11107);
                                                } else {
                                                    super.left = new Command(mResources.boRa, 111071);
                                                }

                                                if (GameCanvas.isMinWidth320) {
                                                    this.getItemInfo((int) 3, (Item) var10);
                                                } else {
                                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11108);
                                                }
                                            } else {
                                                cg = false;
                                                if (var3 >= 2) {
                                                    super.left = new Command(mResources.fc, 11062);
                                                }
                                            }
                                        }
                                    } else if (!ih) {
                                        if (ci) {
                                            if (indexMenu == 0) {
                                                if (gk == 1) {
                                                    if (arrItemTradeMe[indexSelect] != null) {
                                                        if (this.cz == 0) {
                                                            super.left = this.mv;
                                                        } else if (this.cz == 1 && this.da > 0 && (long) this.dd - System.currentTimeMillis() / 1000L <= 0L) {
                                                            super.left = this.mj;
                                                        }

                                                        if (GameCanvas.isMinWidth320) {
                                                            var10 = arrItemTradeMe[indexSelect];
                                                            this.getItemInfo((int) 3, (Item) var10);
                                                        } else {
                                                            super.center = this.ms;
                                                        }
                                                    } else {
                                                        cg = false;
                                                        if (this.cz == 0) {
                                                            super.left = this.mi;
                                                        } else if (this.cz == 1 && this.da > 0 && (long) this.dd - System.currentTimeMillis() / 1000L <= 0L) {
                                                            super.left = this.mj;
                                                        }
                                                    }
                                                }

                                                if (gk == 2) {
                                                    if (arrItemTradeOrder[indexSelect] != null) {
                                                        if (GameCanvas.isMinWidth320) {
                                                            var10 = arrItemTradeOrder[indexSelect];
                                                            this.getItemInfo((int) 30, (Item) var10);
                                                        } else {
                                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11110);
                                                        }
                                                    } else {
                                                        cg = false;
                                                    }
                                                }
                                            } else if (indexMenu == 1 && this.cz == 0) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = this.mu;
                                                } else {
                                                    super.left = this.mq;
                                                }
                                            }
                                        } else if (showBox) {
                                            if (indexMenu == 0) {
                                                if ((var10 = getCurrentItemSelectByTypeUI(4)) != null) {
                                                    String svAct = mResources.layRa;
                                                    if (!svTitle.equals("")) {
                                                        svAct = svAction;
                                                    }
                                                    super.left = new Command(svAct, 111101);
                                                    if (GameCanvas.isMinWidth320) {
                                                        this.getItemInfo((int) 4, (Item) var10);
                                                    } else {
                                                        super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11111);
                                                    }
                                                } else if (svTitle.equals("")) {
                                                    super.left = new Command(mResources.sapXep, 11112);
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(svTitle.equals("") ? mResources.bt : mResources.chon, 11113);
                                                } else {
                                                    super.left = this.om;
                                                }
                                            }
                                        } else if (showAutoUseItem) {
                                            if (indexMenu == 0) {
                                                if (indexSelect >= 0 && indexSelect < AutoUseItem.getListIds().length && AutoUseItem.getItemIdAt(indexSelect) > 0) {
                                                    super.left = new Command("Cài đặt", 11000944);
                                                    super.center = new Command(GameCanvas.isTouch ? "Cài đặt" : "", 11000944);
                                                } else {
                                                    super.left = new Command(mResources.sapXep, 11000945);
                                                }
                                            } else if (indexMenu == 1) {
                                                if (indexSelect >= 0 && indexSelect < Char.getMyChar().arrItemBag.length && Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(mResources.sr, 11000946);
                                                    super.center = new Command(GameCanvas.isTouch ? mResources.sr : "", 11000946);
                                                } else {
                                                    super.left = this.om;
                                                }
                                            }
                                        } else if (showPickItem) {
                                            if (indexMenu == 0) {
                                                if (indexSelect >= 0 && indexSelect <= Code.pickUpListID.length && Code.pickUpListID[indexSelect] > 0) {
                                                    super.left = new Command(mResources.sq, 1100077);
                                                } else {
                                                    super.left = new Command(mResources.sapXep, 1100078);
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(mResources.sr, 1100079);
                                                } else {
                                                    super.left = this.om;
                                                }
                                            }
                                        } else if (showItemThrow) {
                                            if (indexMenu == 0) {
                                                if (indexSelect >= 0 && indexSelect <= Code.throwListID.length && Code.throwListID[indexSelect] > 0) {
                                                    super.left = new Command(mResources.sq, 11000772);
                                                } else {
                                                    super.left = new Command(mResources.sapXep, 11000782);
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(mResources.sr, 11000792);
                                                } else {
                                                    super.left = this.om;
                                                }
                                            }
                                        } else if (showItemGather) {
                                            if (indexMenu == 0) {
                                                if (indexSelect >= 0 && indexSelect <= AutoReceiver.gatherListID.length && AutoReceiver.gatherListID[indexSelect] > 0) {
                                                    super.left = new Command(mResources.sq, 1100080122);
                                                } else {
                                                    super.left = new Command(mResources.sapXep, 1100080123);
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(mResources.sr, 1100080124);
                                                } else {
                                                    super.left = this.om;
                                                }
                                            }
                                        } else if (showDelItem) {
                                            if (indexMenu == 0) {
                                                if (indexSelect >= 0 && indexSelect <= Code.delListID.length && Code.delListID[indexSelect] > 0) {
                                                    super.left = new Command(mResources.sq, 11000771);
                                                } else {
                                                    super.left = new Command(mResources.sapXep, 11000781);
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(mResources.sr, 11000791);
                                                } else {
                                                    super.left = this.om;
                                                }
                                            }
                                        } else if (ij) {
                                            GameScr var12 = this;

                                            try {
                                                if (indexMenu == 0) {
                                                    if (gk == 1) {
                                                        if (itemSplit != null) {
                                                            var12.left = new Command(mResources.chon, 11103);
                                                        } else {
                                                            cg = false;
                                                        }

                                                        if (GameCanvas.isMinWidth320) {
                                                            var12.getItemInfo((int) 3, (Item) itemSplit);
                                                        } else {
                                                            var12.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, itemSplit);
                                                        }
                                                    } else if (gk == 2) {
                                                        if ((var10 = arrItemSplit[indexSelect]) != null) {
                                                            if (GameCanvas.isMinWidth320) {
                                                                var12.getItemInfo((int) 3, (Item) var10);
                                                            } else {
                                                                var12.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, var10);
                                                            }

                                                            var12.left = new Command(mResources.boRa, 1605);
                                                        } else {
                                                            cg = false;
                                                        }

                                                        if (itemSplit != null) {
                                                            var12.left = new Command(mResources.chon, 1604);
                                                        }
                                                    }
                                                } else if (indexMenu == 1) {
                                                    if (Char.getMyChar().arrItemBag[indexSelect] == null) {
                                                        var12.left = null;
                                                        cg = false;
                                                    } else {
                                                        var12.left = new Command(mResources.chon, 222);
                                                    }
                                                }
                                            } catch (Exception var8) {
                                            }
                                        } else if (ik) {
                                            if (indexMenu == 0) {
                                                if (gk == 1) {
                                                    if (itemSplit != null && indexSelect == 0) {
                                                        super.left = new Command(mResources.chon, 338);
                                                        if (GameCanvas.isMinWidth320) {
                                                            gp = false;
                                                            this.getItemInfo((int) 3, (Item) itemSplit);
                                                        } else {
                                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 335);
                                                        }
                                                    }

                                                    if (itemUpGrade != null && indexSelect == 1) {
                                                        super.left = new Command(mResources.chon, 344);
                                                        if (GameCanvas.isMinWidth320) {
                                                            gp = false;
                                                            this.getItemInfo((int) 3, (Item) itemUpGrade);
                                                        } else {
                                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 336);
                                                        }
                                                    }

                                                    if (itemSplit == null && itemUpGrade == null) {
                                                        cg = false;
                                                    }
                                                } else if (gk == 2) {
                                                    var10 = getCurrentItemSelectByTypeUI(47);
                                                    gp = false;
                                                    if (var10 != null) {
                                                        super.left = new Command(mResources.chon, 345);
                                                        if (GameCanvas.isMinWidth320) {
                                                            this.getItemInfo((int) 3, (Item) var10);
                                                        } else {
                                                            super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11101);
                                                        }
                                                    } else {
                                                        super.left = null;
                                                        cg = false;
                                                        if (itemUpGrade != null && itemSplit != null) {
                                                            for (var2 = 0; var2 < arrItemUpGrade.length; ++var2) {
                                                                if (arrItemUpGrade[var2] != null) {
                                                                    super.left = new Command(mResources.fc, 341);
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(mResources.chon, 337);
                                                } else {
                                                    super.left = null;
                                                }
                                            }
                                        } else if (io) {
                                            if (indexMenu == 0) {
                                                var10 = getCurrentItemSelectByTypeUI(48);
                                                gp = false;
                                                if (var10 != null) {
                                                    super.left = new Command(mResources.chon, 401);
                                                    if (GameCanvas.isMinWidth320) {
                                                        this.getItemInfo((int) 3, (Item) var10);
                                                    } else {
                                                        super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11101);
                                                    }
                                                } else {
                                                    for (var2 = 0; var2 < arrItemSplit.length; ++var2) {
                                                        if (arrItemSplit[var2] != null) {
                                                            super.left = new Command(mResources.gr[0], 403);
                                                            break;
                                                        }
                                                    }
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                    super.left = new Command(mResources.chon, 400);
                                                } else {
                                                    super.left = null;
                                                }
                                            }
                                        } else if (il || im) {
                                            if (indexMenu == 0) {
                                                if (itemSplit != null) {
                                                    super.left = new Command(mResources.chon, 11103);
                                                } else {
                                                    cg = false;
                                                }

                                                if (GameCanvas.isMinWidth320) {
                                                    this.getItemInfo((int) 3, (Item) itemSplit);
                                                } else {
                                                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11104, itemSplit);
                                                }
                                            } else if (indexMenu == 1) {
                                                if (Char.getMyChar().arrItemBag[indexSelect] == null) {
                                                    super.left = null;
                                                    cg = false;
                                                } else {
                                                    super.left = new Command(mResources.chon, 405);
                                                }
                                            }
                                        }
                                    } else if (indexMenu != 0) {
                                        if (indexMenu == 1) {
                                            if (Char.getMyChar().arrItemBag[indexSelect] != null) {
                                                super.left = new Command(mResources.chon, 1603);
                                            } else {
                                                cg = false;
                                                super.left = null;
                                            }
                                        }
                                    } else {
                                        var3 = 0;
                                        var2 = 0;
                                        var1 = 0;
                                        short var4 = 0;

                                        for (int var5 = 0; var5 < arrItemUpPeal.length; ++var5) {
                                            Item var6;
                                            if ((var6 = arrItemUpPeal[var5]) != null) {
                                                if (var6.template.id == 455) {
                                                    ++var3;
                                                } else if (var6.template.id == 456) {
                                                    ++var2;
                                                } else if (var6.template.type == 26) {
                                                    var4 = var6.template.id;
                                                    ++var1;
                                                }
                                            }

                                            if (var3 >= 9 || var2 >= 9 || var4 == 10 && var3 >= 3 || var4 == 11 && var2 >= 3) {
                                                break;
                                            }
                                        }

                                        Item var11;
                                        if ((var11 = getCurrentItemSelectByTypeUI(43)) == null) {
                                            cg = false;
                                            if (var3 >= 9 || var2 >= 9 || var4 >= 10 && (var3 >= 3 || var2 >= 3)) {
                                                super.left = new Command(mResources.fc, 1600);
                                            }
                                        } else {
                                            if (var3 != 9 && var2 != 9 && (var4 != 10 || var3 != 3 || var1 != 1) && (var4 != 11 || var2 != 3 || var1 != 1)) {
                                                super.left = new Command(mResources.boRa, 111071);
                                            } else {
                                                super.left = new Command(mResources.chon, 1601);
                                            }

                                            if (GameCanvas.isMinWidth320) {
                                                this.getItemInfo((int) 43, (Item) var11);
                                            } else {
                                                super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 1602);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (z() && indexMenu == 1 && Char.getMyChar().arrItemBag[indexSelect] != null) {
                    if (GameCanvas.isMinWidth320) {
                        this.getItemInfo((int) 3, (Item) Char.getMyChar().arrItemBag[indexSelect]);
                        return;
                    }

                    super.center = new Command(GameCanvas.isTouch ? mResources.xem : "", 11114);
                }
            }
        }

    }

    public static void b(int var0, int var1) {
        if (GameCanvas.width == 128 || GameCanvas.height <= 208) {
            var0 = 126;
            var1 = 160;
        }

        ew = var0;
        ex = var1;
        popupX = d - var0 / 2;
        popupY = f - var1 / 2;
        if (GameCanvas.height <= 250) {
            popupY -= 10;
        }

        if (GameCanvas.isMinWidth320 && !ci() && GameCanvas.mScreen instanceof GameScr) {
            ew = 310;
            popupX = gW / 2 - ew / 2;
        }

        if (popupY < -10) {
            popupY = -10;
        }

        if (GameCanvas.height > 208 && popupY < 0) {
            popupY = 0;
        }

        if (GameCanvas.height == 208 && popupY < 10) {
            popupY = 10;
        }

    }

    private void a(mGraphics var1, Skill var2) {
        if (Char.getMyChar().cLevel >= var2.level) {
            mFont.tahoma_7_white.writeText(var1, mResources.hz + " " + var2.level, leftMargin + 5, this.od += 12, 0);
        } else {
            mFont.tahoma_7_red.writeText(var1, mResources.hz + " " + var2.level, leftMargin + 5, this.od += 12, 0);
        }

        if (var2.template.type != 0) {
            totalRowSetting += 4;
            mFont.tahoma_7_white.writeText(var1, mResources.lf + ": " + var2.d(), leftMargin + 5, this.od += 12, 0);
            mFont.tahoma_7_white.writeText(var1, mResources.lh + ": " + var2.manaUse, leftMargin + 5, this.od += 12, 0);
            mFont.tahoma_7_white.writeText(var1, mResources.lg + ": " + var2.getNSkill(), leftMargin + 5, this.od += 12, 0);
            StringBuffer var3 = (new StringBuffer(String.valueOf(mResources.li))).append(": ");
            String var5;
            if (var2.coolDown % 1000 == 0) {
                var5 = String.valueOf(var2.coolDown / 1000);
            } else {
                int var6 = var2.coolDown % 1000;
                var5 = var2.coolDown / 1000 + "." + (var6 % 100 == 0 ? var6 / 100 : var6 / 10);
            }

            mFont.tahoma_7_white.writeText(var1, var3.append(var5).append(" ").append(mResources.ld).toString(), leftMargin + 5, this.od += 12, 0);
        }

        ++totalRowSetting;
    }

    private void b(mGraphics var1, Skill var2) {
        SkillOption[] var6 = var2.options;

        for (int var3 = 0; var3 < var6.length; ++var3) {
            SkillOption var4 = var6[var3];
            if (mFont.tahoma_7_white.a(var4.getOptionString()) > 145) {
                MyVector var7 = mFont.tahoma_7_white.a(var4.getOptionString(), 145);

                for (int var5 = 0; var5 < var7.size(); ++var5) {
                    mFont.tahoma_7_white.writeText(var1, var7.elementAt(var5).toString(), leftMargin + 5, this.od += 12, 0);
                    ++totalRowSetting;
                }
            } else {
                mFont.tahoma_7_white.writeText(var1, var4.getOptionString(), leftMargin + 5, this.od += 12, 0);
                ++totalRowSetting;
            }
        }

    }

    private void n(mGraphics mGraphics) {
        if (indexMenu == 1) {
            resetCursor(mGraphics);
            Paint.a(popupX, popupY, ew, ex, mGraphics);
            drawTitle(mGraphics, mResources.nameMenuIndex[indexMenu], true);
            mFont.tahoma_7b_white.writeText(mGraphics, mResources.jh, popupX + 10, popupY + 32, 0);
            mFont.tahoma_7b_white.writeText(mGraphics, "" + Char.getMyChar().aj, popupX + ew - 10, popupY + 32, 1);
            mGraphics.setColor(0);
            mGraphics.fillRect(popupX + 4, popupY + 44, ew - 7, wItem + 3);
            mGraphics.setColor(12281361);
            mGraphics.drawRect(popupX + 5, popupY + 45, ew - 10, wItem);
            if (gk > 0) {
                mGraphics.setColor(Paint.d);
                mGraphics.drawRect(popupX + 5, popupY + 48 + wItem, ew - 10, ex - 64 - wItem);
            }

            int var2 = Char.getMyChar().nClass.skillTemplates.length;
            leftMargin = popupX + 5;
            nw = popupY + 45;
            scrMain.a(var2, wItem + 2, leftMargin + 1, nw, ew - 12, wItem + 2, false, 1);
            scrMain.a(mGraphics);

            for (int var3 = 0; var3 < var2; ++var3) {
                int var10002 = leftMargin + var3 * (wItem + 2) + wItem / 2;
                SmallImage.paintImage(mGraphics, Char.getMyChar().nClass.skillTemplates[var3].iconId, var10002, nw + wItem / 2, 0, 3);
                if (gk == 1 && var3 == indexSelect) {
                    mGraphics.setColor(16777215);
                    mGraphics.drawRect(leftMargin + var3 * (wItem + 2) + 2, nw + 2, wItem - 4, wItem - 4);
                    a(leftMargin + var3 * (wItem + 2), nw, mGraphics);
                }
            }

            leftMargin += 8;
            nw += 6;
            if (gk == 1 && indexSelect >= 0) {
                resetCursor(mGraphics);
                SkillTemplate var10 = Char.getMyChar().nClass.skillTemplates[indexSelect];
                totalRowSetting = 4 + var10.description.length;
                Skill var9 = Char.getMyChar().a(var10);
                int var4 = popupX;
                int var5 = nw + wItem + 2;
                int var6 = ew - 6;
                int var7 = ex - 70 - wItem;
                gn.a(mGraphics, var4, var5, var6, var7);
                this.od = nw + 18;
                int var8;
                if (var9 == null) {
                    var9 = var10.skills.length > 1 ? var10.skills[1] : var10.skills[0];
                    mFont.tahoma_7b_red.writeText(mGraphics, getSkillNameWithId(var10), leftMargin + 5, this.od += 12, 0);

                    for (var8 = 0; var8 < var10.description.length; ++var8) {
                        mFont.tahoma_7_white.writeText(mGraphics, var10.description[var8], leftMargin + 5, this.od += 12, 0);
                    }

                    mFont.tahoma_7_white.writeText(mGraphics, mResources.is[var10.type], leftMargin + 5, this.od += 12, 0);
                    mFont.tahoma_7_white.writeText(mGraphics, mResources.hx + ": " + var10.maxPoint, leftMargin + 5, this.od += 12, 0);
                    mFont.tahoma_7_red.writeText(mGraphics, mResources.a(mResources.hv, String.valueOf(var9.point)), leftMargin + 5, this.od += 12, 0);
                    this.a(mGraphics, var9);
                    this.b(mGraphics, var9);
                } else {
                    mFont.tahoma_7b_white.writeText(mGraphics, getSkillNameWithId(var10), leftMargin + 5, this.od += 12, 0);

                    for (var8 = 0; var8 < var10.description.length; ++var8) {
                        mFont.tahoma_7_white.writeText(mGraphics, var10.description[var8], leftMargin + 5, this.od += 12, 0);
                    }

                    mFont.tahoma_7_white.writeText(mGraphics, mResources.is[var10.type], leftMargin + 5, this.od += 12, 0);
                    mFont.tahoma_7_white.writeText(mGraphics, mResources.hx + ": " + var10.maxPoint, leftMargin + 5, this.od += 12, 0);
                    if (var9.point == var10.maxPoint) {
                        mFont.tahoma_7_blue.writeText(mGraphics, mResources.hy, leftMargin + 5, this.od += 12, 0);
                        this.a(mGraphics, var9);
                        this.b(mGraphics, var9);
                    } else {
                        mFont.tahoma_7_blue.writeText(mGraphics, mResources.a(mResources.hw, String.valueOf(var9.point)), leftMargin + 5, this.od += 12, 0);
                        this.a(mGraphics, var9);
                        this.b(mGraphics, var9);

                        for (var8 = 0; var8 < var10.skills.length; ++var8) {
                            if (var10.skills[var8].equals(var9)) {
                                ++var8;
                                break;
                            }
                        }

                        mFont.tahoma_7_red.writeText(mGraphics, mResources.a(mResources.hv, String.valueOf(var10.skills[var8].point)), leftMargin + 5, this.od += 12, 0);
                        this.a(mGraphics, var10.skills[var8]);
                        ++totalRowSetting;
                        this.b(mGraphics, var10.skills[var8]);
                    }
                }

                gn.a(totalRowSetting, 12, var4, var5, var6, var7, true, 1);
                if (indexRow >= 0) {
                    SmallImage.paintImage(mGraphics, 942, leftMargin + 2, nw + 32 + indexRow * 12, 0, StaticObj.c);
                }
            }
        }

    }

    private void a(mGraphics var1, String[] var2) {
        try {
            resetCursor(var1);
            showUIBox(var1, var2, true);
            this.paintTabBag(var1, Char.getMyChar().arrItemBag);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private void paintTabBag(mGraphics var1, Item[] var2) {
        oc = var2.length / ob;
        scrMain.a(oc, wItem, leftMargin, nw, ob * wItem, 5 * wItem, true, 6);
        scrMain.a(var1, leftMargin, nw, scrMain.e + 2, scrMain.f + 2);

        int var3;
        int var4;
        for (var3 = 0; var3 < oc; ++var3) {
            for (var4 = 0; var4 < ob; ++var4) {
                SmallImage.paintImage(var1, 154, leftMargin + var4 * wItem + wItem / 2, nw + var3 * wItem + wItem / 2, 0, 3);
                var1.setColor(12281361);
                var1.drawRect(leftMargin + var4 * wItem, nw + var3 * wItem, wItem, wItem);
            }
        }

        for (var3 = 0; var3 < var2.length; ++var3) {
            Item item;
            if ((item = var2[var3]) != null) {
                int var5 = item.indexUI / ob;
                int var6 = item.indexUI - var5 * ob;
                this.drawItemUI(var1, item, leftMargin + var6 * wItem, nw + var5 * wItem);
                String textStatusItem = "";
                if (Code.containDelItem(item.template.id)) {
                    textStatusItem = "DEL";
                } else if (Code.containItemThrow(item.template.id)) {
                    textStatusItem = "THR";
                } else if (Code.hasTuDung(item.template.id)) {
                    textStatusItem = "AUTO";
                } else if (AutoReceiver.containItemGather(item.template.id)) {
                    textStatusItem = "GOM";
                }

                if (!textStatusItem.equals("")) {
                    mFont.tahoma_7_yellow.writeText(var1, textStatusItem, leftMargin + var6 * wItem + wItem, nw + var5 * wItem, 1, mFont.tahoma_7_red);
                }

                if (item.quantity > 1) {
                    mFont.number_yellow.writeText(var1, "" + item.quantity, leftMargin + var6 * wItem + wItem, nw + var5 * wItem + wItem - mFont.number_yellow.getHeight(), 1);
                }
            }
        }

        if (gk > 0 && indexSelect >= 0) {
            var3 = indexSelect / ob;
            var4 = indexSelect - var3 * ob;
            var1.setColor(16777215);
            var1.drawRect(leftMargin + var4 * wItem, nw + var3 * wItem, wItem, wItem);
            a(leftMargin + var4 * wItem, nw + var3 * wItem, var1);
        }

    }

    private static void paintTabNhatDo(mGraphics var0, short[] listID) {
        oc = listID.length / ob;
        scrMain.a(oc, wItem, leftMargin, nw, ob * wItem, 5 * wItem, true, 6);
        scrMain.a(var0, leftMargin, nw, scrMain.e + 2, scrMain.f + 2);
        int var2;
        int var3;
        for (var2 = 0; var2 < oc; ++var2) {
            for (var3 = 0; var3 < ob; ++var3) {
                // paintImageAnim
                SmallImage.paintImageAnim(var0, 154, leftMargin + var3 * wItem + wItem / 2, nw + var2 * wItem + wItem / 2, 0, 3);
                var0.setColor(12281361);
                var0.drawRect(leftMargin + var3 * wItem, nw + var2 * wItem, wItem, wItem);
            }
        }

        for (var2 = 0; var2 < listID.length; ++var2) {
            short var6;
            if ((var6 = listID[var2]) > 0) {
                int var4 = var2 / ob;
                int var5 = var2 - var4 * ob;
                ItemTemplate var7;
                if ((var7 = ItemTemplateManager.get(var6)) != null) {
                    // paintImageAnim
                    SmallImage.paintImageAnim(var0, var7.iconID, leftMargin + var5 * wItem + wItem / 2, nw + var4 * wItem + wItem / 2, 0, 3);
                }
            }
        }

        if (gk > 0 && indexSelect >= 0) {
            var2 = indexSelect / ob;
            var3 = indexSelect - var2 * ob;
            var0.setColor(16777215);
            var0.drawRect(leftMargin + var3 * wItem, nw + var2 * wItem, wItem, wItem);
            a(leftMargin + var3 * wItem, nw + var2 * wItem, var0);
        }

    }

    private static void a(int var0, int var1, mGraphics var2) {
        var2.drawImage(jo, var0 - 5, var1 - 5, 0);
    }

    private static int l(int var0) {
        int var1 = wItem - 2;
        if ((var0 %= var1 << 2) >= 0 && var0 < var1) {
            return 0;
        } else if (var1 <= var0 && var0 < var1 << 1) {
            return var0 % var1;
        } else {
            return var1 << 1 <= var0 && var0 < var1 * 3 ? var1 : var1 - var0 % var1;
        }
    }

    private static int m(int var0) {
        int var1 = wItem - 2;
        if ((var0 %= var1 << 2) >= 0 && var0 < var1) {
            return var0 % var1;
        } else if (var1 <= var0 && var0 < var1 << 1) {
            return var1;
        } else {
            return var1 << 1 <= var0 && var0 < var1 * 3 ? var1 - var0 % var1 : 0;
        }
    }

    private void a(mGraphics var1, String[] var2, Item[] var3) {
        try {
            resetCursor(var1);
            showUIBox(var1, var2, true);
            if (var3 == null) {
                GameCanvas.a(popupX + 90, popupY + 75, var1);
                mFont.tahoma_7b_white.writeText(var1, mResources.textLoading, popupX + 90, popupY + 90, 2);
                return;
            }

            if (var3.length <= 30) {
                oc = 5;
            } else if (var3.length % ob == 0) {
                oc = var3.length / ob;
            } else {
                oc = var3.length / ob + 1;
            }

            scrMain.a(oc, wItem, leftMargin, nw, ob * wItem, 5 * wItem, true, 6);
            scrMain.a(var1, leftMargin, nw, scrMain.e + 2, scrMain.f + 2);

            int var4;
            int var8;
            for (var4 = 0; var4 < oc; ++var4) {
                for (var8 = 0; var8 < ob; ++var8) {
                    SmallImage.paintImage(var1, 154, leftMargin + var8 * wItem + wItem / 2, nw + var4 * wItem + wItem / 2, 0, 3);
                    var1.setColor(12281361);
                    var1.drawRect(leftMargin + var8 * wItem, nw + var4 * wItem, wItem, wItem);
                }
            }

            for (var4 = 0; var4 < var3.length; ++var4) {
                Item var9;
                if ((var9 = var3[var4]) != null) {
                    int var5 = var9.indexUI / ob;
                    int var6 = var9.indexUI - var5 * ob;
                    if (!var9.isLock) {
                        var1.setColor(12083);
                        var1.fillRect(leftMargin + var6 * wItem + 1, nw + var5 * wItem + 1, wItem - 1, wItem - 1);
                        SmallImage.paintImage(var1, 154, leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2, 0, 3);
                    }

                    if (GameCanvas.gameTick % 6 == 0) {
                        var9.indexFrame = (var9.indexFrame + 1) % 3;
                    }

                    if (var9.isNewitem()) {
                        var1.drawImage(GameCanvas.dungHopImg, leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2, 3);
                    }

                    SmallImage.paintImage(var1, var9.template.iconID, leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2, 0, 3, var9.indexFrame);
                }
            }

            if (gk > 0 && indexSelect >= 0) {
                var4 = indexSelect / ob;
                var8 = indexSelect - var4 * ob;
                var1.setColor(16777215);
                var1.drawRect(leftMargin + var8 * wItem, nw + var4 * wItem, wItem, wItem);
                a(leftMargin + var8 * wItem, nw + var4 * wItem, var1);
                return;
            }
        } catch (Exception var9) {
        }

    }

    private static void showUIBox(mGraphics var0, String[] var1, boolean var2) {
        Paint.a(popupX, popupY, ew, ex, var0);
        if (var2) {
            mFont.tahoma_7_white.writeText(var0, mResources.ke + ": " + NinjaUtil.a(String.valueOf(Char.getMyChar().xu)), popupX + 6, popupY + ex - 26, 0);
            mFont.tahoma_7_white.writeText(var0, mResources.kf + ": " + NinjaUtil.a(String.valueOf(Char.getMyChar().yen)), popupX + ew - 6, popupY + ex - 26, 1);
            if (ci) {
                if (GameCanvas.gameTick % 10 > 4) {
                    mFont.tahoma_7_yellow.writeText(var0, mResources.ff, popupX + ew / 2, popupY + ex - 14, 2);
                }
            } else if (hy) {
                if (GameCanvas.gameTick % 10 > 4) {
                    mFont.tahoma_7_yellow.writeText(var0, mResources.fd, popupX + ew / 2, popupY + ex - 14, 2);
                }
            } else if (ia) {
                if (GameCanvas.gameTick % 10 > 4) {
                    mFont.tahoma_7_yellow.writeText(var0, mResources.fe, popupX + ew / 2, popupY + ex - 14, 2);
                }
            } else {
                mFont.tahoma_7_yellow.writeText(var0, mResources.kg + ": " + NinjaUtil.a(String.valueOf(Char.getMyChar().luong)), popupX + ew / 2, popupY + ex - 14, 2);
            }
        }

        drawTitle(var0, var1[indexMenu], var1.length > 1);
        leftMargin = popupX + 3;
        nw = popupY + 32;
        var0.setColor(6425);
        var0.fillRect(leftMargin - 1, nw - 1, ob * wItem + 3, 5 * wItem + 3);
    }

    private void b(mGraphics var1, String[] var2) {
        try {
            oc = 3;
            Paint.a(popupX, popupY, ew, ex, var1);
            drawTitle(var1, var2[indexMenu], var2.length > 1);
            leftMargin = popupX + 3;
            nw = popupY + 34 + wItem;
            int var9 = popupX + 45;
            int var3 = popupX + 100;
            int var4 = nw - wItem - 3;
            if (itemUpGrade != null) {
                this.drawItemUI(var1, itemUpGrade, var9, var4);
                var1.setColor(12281361);
                var1.drawRect(var9, var4, wItem, wItem);
                mFont.tahoma_7_yellow.writeText(var1, "(+" + itemUpGrade.upgrade + ")", var9 - 5, var4 + wItem / 2 - 5, 1);
            } else {
                var1.setColor(6425);
                var1.fillRect(var9 - 1, var4 - 1, wItem + 3, wItem + 3);
                SmallImage.paintImage(var1, 154, var9 + wItem / 2, var4 + wItem / 2, 0, 3);
                var1.setColor(12281361);
                var1.drawRect(var9, var4, wItem, wItem);
            }

            SmallImage.paintImage(var1, 942, var9 + 43, nw - 15, 0, StaticObj.g);
            if (itemUpGrade != null && !itemUpGrade.isUpMax()) {
                this.drawItemUI(var1, itemUpGrade, var3, var4, 1, 0);
                var1.setColor(12281361);
                var1.drawRect(var3, var4, wItem, wItem);
                mFont.tahoma_7_yellow.writeText(var1, "(+" + (itemUpGrade.upgrade + 1) + ")", var3 + wItem + 10, var4 + wItem / 2 - 5, 0);
            } else {
                var1.setColor(6425);
                var1.fillRect(var3 - 1, var4 - 1, wItem + 3, wItem + 3);
                SmallImage.paintImage(var1, 154, var3 + wItem / 2, var4 + wItem / 2, 0, 3);
                var1.setColor(12281361);
                var1.drawRect(var3, var4, wItem, wItem);
            }

            if (gk == 1) {
                if (indexSelect == 0) {
                    var1.setColor(16777215);
                    var1.drawRect(var9, var4, wItem, wItem);
                }

                if (indexSelect == 1) {
                    var1.setColor(16777215);
                    var1.drawRect(var3, var4, wItem, wItem);
                }
            }

            int var5;
            int var6;
            if (itemUpGrade == null) {
                for (var3 = 0; var3 < 3; ++var3) {
                    mFont.tahoma_7_white.writeText(var1, mResources.gv[var3], leftMargin, nw + oc * wItem + 5 + var3 * 12, 0);
                }
            } else if (itemUpGrade.isUpMax()) {
                if (!GameCanvas.isMinWidth320) {
                    mFont.tahoma_7_yellow.writeText(var1, mResources.gv[3], popupX + ew / 2, nw + oc * wItem + 5, 2);
                } else {
                    mFont.tahoma_7_yellow.writeText(var1, mResources.gv[3], popupX + 7, nw + oc * wItem + 5, 0);
                }
            } else {
                var3 = 0;

                for (var5 = 0; var5 < arrItemUpGrade.length; ++var5) {
                    if (arrItemUpGrade[var5] != null && arrItemUpGrade[var5].template.type == 26) {
                        var3 += crystals[arrItemUpGrade[var5].template.id];
                    }
                }

                var6 = 0;
                if (itemUpGrade.isTypeClothe()) {
                    if ((var5 = var3 * 100 / upClothe[itemUpGrade.upgrade]) > maxPercents[itemUpGrade.upgrade]) {
                        var5 = maxPercents[itemUpGrade.upgrade];
                    }

                    if (hx) {
                        var5 = (int) ((double) var5 * 1.5);
                        var6 = goldUps[itemUpGrade.upgrade];
                    }

                    if (coinUpClothes[itemUpGrade.upgrade] > Char.getMyChar().xu + Char.getMyChar().yen && coinUpClothes[itemUpGrade.upgrade] > Char.getMyChar().yen) {
                        mFont.tahoma_7_red.writeText(var1, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(coinUpClothes[itemUpGrade.upgrade]))), leftMargin, nw + oc * wItem + 5, 0);
                    } else {
                        mFont.tahoma_7_yellow.writeText(var1, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(coinUpClothes[itemUpGrade.upgrade]))), leftMargin, nw + oc * wItem + 5, 0);
                    }

                    if (var6 > Char.getMyChar().luong) {
                        mFont.tahoma_7_red.writeText(var1, mResources.a(mResources.canNumberLuong, String.valueOf(var6)), leftMargin, nw + oc * wItem + 17, 0);
                    } else {
                        mFont.tahoma_7_yellow.writeText(var1, mResources.a(mResources.canNumberLuong, String.valueOf(var6)), leftMargin, nw + oc * wItem + 17, 0);
                    }

                    mFont.tahoma_7_yellow.writeText(var1, mResources.hc + ": " + var5 + "%", leftMargin, nw + oc * wItem + 29, 0);
                } else if (itemUpGrade.isTypeAdorn()) {
                    if ((var5 = var3 * 100 / upAdorn[itemUpGrade.upgrade]) > maxPercents[itemUpGrade.upgrade]) {
                        var5 = maxPercents[itemUpGrade.upgrade];
                    }

                    if (hx) {
                        var5 = (int) ((double) var5 * 1.5);
                        var6 = goldUps[itemUpGrade.upgrade];
                    }

                    if (coinUpAdorns[itemUpGrade.upgrade] > Char.getMyChar().xu + Char.getMyChar().yen && coinUpAdorns[itemUpGrade.upgrade] > Char.getMyChar().yen) {
                        mFont.tahoma_7_red.writeText(var1, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(coinUpAdorns[itemUpGrade.upgrade]))), leftMargin, nw + oc * wItem + 5, 0);
                    } else {
                        mFont.tahoma_7_yellow.writeText(var1, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(coinUpAdorns[itemUpGrade.upgrade]))), leftMargin, nw + oc * wItem + 5, 0);
                    }

                    if (var6 > Char.getMyChar().luong) {
                        mFont.tahoma_7_red.writeText(var1, mResources.a(mResources.canNumberLuong, String.valueOf(var6)), leftMargin, nw + oc * wItem + 17, 0);
                    } else {
                        mFont.tahoma_7_yellow.writeText(var1, mResources.a(mResources.canNumberLuong, String.valueOf(var6)), leftMargin, nw + oc * wItem + 17, 0);
                    }

                    mFont.tahoma_7_yellow.writeText(var1, mResources.hc + ": " + var5 + "%", leftMargin, nw + oc * wItem + 29, 0);
                } else if (itemUpGrade.isTypeWeapon()) {
                    if ((var5 = var3 * 100 / upWeapon[itemUpGrade.upgrade]) > maxPercents[itemUpGrade.upgrade]) {
                        var5 = maxPercents[itemUpGrade.upgrade];
                    }

                    if (hx) {
                        var5 = (int) ((double) var5 * 1.5);
                        var6 = goldUps[itemUpGrade.upgrade];
                    }

                    if (coinUpWeapons[itemUpGrade.upgrade] > Char.getMyChar().xu + Char.getMyChar().yen && coinUpWeapons[itemUpGrade.upgrade] > Char.getMyChar().yen) {
                        mFont.tahoma_7_red.writeText(var1, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(coinUpWeapons[itemUpGrade.upgrade]))), leftMargin, nw + oc * wItem + 5, 0);
                    } else {
                        mFont.tahoma_7_yellow.writeText(var1, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(coinUpWeapons[itemUpGrade.upgrade]))), leftMargin, nw + oc * wItem + 5, 0);
                    }

                    if (var6 > Char.getMyChar().luong) {
                        mFont.tahoma_7_red.writeText(var1, mResources.a(mResources.canNumberLuong, String.valueOf(var6)), leftMargin, nw + oc * wItem + 17, 0);
                    } else {
                        mFont.tahoma_7_yellow.writeText(var1, mResources.a(mResources.canNumberLuong, String.valueOf(var6)), leftMargin, nw + oc * wItem + 17, 0);
                    }

                    mFont.tahoma_7_yellow.writeText(var1, mResources.hc + ": " + var5 + "%", leftMargin, nw + oc * wItem + 29, 0);
                }
            }

            var1.setColor(0);
            var1.fillRect(leftMargin - 1, nw - 1, ob * wItem + 3, oc * wItem + 3);

            for (var3 = 0; var3 < oc; ++var3) {
                for (var5 = 0; var5 < ob; ++var5) {
                    SmallImage.paintImage(var1, 154, leftMargin + var5 * wItem + wItem / 2, nw + var3 * wItem + wItem / 2, 0, 3);
                    var1.setColor(12281361);
                    var1.drawRect(leftMargin + var5 * wItem, nw + var3 * wItem, wItem, wItem);
                }
            }

            if (gk == 2) {
                var3 = indexSelect / ob;
                var5 = indexSelect - var3 * ob;
                var1.setColor(16777215);
                var1.drawRect(leftMargin + var5 * wItem, nw + var3 * wItem, wItem, wItem);
            }

            for (var3 = 0; var3 < arrItemUpGrade.length; ++var3) {
                Item var10;
                if ((var10 = arrItemUpGrade[var3]) != null) {
                    var6 = var3 / ob;
                    int var7 = var3 - var6 * ob;
                    if (!var10.isLock) {
                        var1.setColor(12083);
                        var1.fillRect(leftMargin + var7 * wItem + 1, nw + var6 * wItem + 1, wItem - 1, wItem - 1);
                    }

                    SmallImage.paintImage(var1, var10.template.iconID, leftMargin + var7 * wItem + wItem / 2, nw + var6 * wItem + wItem / 2, 0, 3);
                }
            }

            if (ez != null) {
                SmallImage.paintImage(var1, ez.arrEfInfo[ey].c, var9 + wItem / 2 + ez.arrEfInfo[ey].a + 1, var4 + wItem / 2 + 9 + ez.arrEfInfo[ey].b, 0, 3);
                if (GameCanvas.gameTick % 2 == 0 && ++ey >= ez.arrEfInfo.length) {
                    ey = 0;
                    ez = null;
                    return;
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    private void drawItemUI(mGraphics var1, Item var2, int var3, int var4) {
        this.drawItemUI(var1, var2, var3, var4, 0, 0);
    }

    private void drawItemUI(mGraphics graphic, Item item, int x, int y, int var5, int var6) {
        if (!item.isTypeMounts()) {
            if ((var5 += item.upgrade) > 0) {
                if (var5 >= 4) {
                    graphic.setColor(this.oe[var5]);
                    graphic.fillRect(x + 1 + var6, y + 1 + var6, wItem - 1 - (var6 << 1), wItem - 1 - (var6 << 1));
                    SmallImage.paintImage(graphic, 154, x + wItem / 2, y + wItem / 2, 0, 3);
                } else {
                    a(graphic, item, x, y, var6);
                }

                if (var5 > 0) {
                    var6 = x + wItem / 2;
                    int var7 = y + wItem / 2;
                    mGraphics var8 = graphic;
                    var7 = var7;
                    var6 = var6;
                    GameScr var9 = this;
                    int var10 = wItem - 2;
                    int var11 = var5 < 4 ? 0 : (var5 < 8 ? 1 : (var5 < 12 ? 2 : (var5 <= 14 ? 3 : 4)));

                    int var12;
                    int var13;
                    int var14;
                    for (var12 = 0; var12 < var9.og.length; ++var12) {
                        var13 = var6 - var10 / 2 + m(GameCanvas.gameTick - (var12 << 2));
                        var14 = var7 - var10 / 2 + l(GameCanvas.gameTick - (var12 << 2));
                        var8.setColor(var9.of[var11][var12]);
                        var8.fillRect(var13 - var9.og[var12] / 2, var14 - var9.og[var12] / 2, var9.og[var12], var9.og[var12]);
                    }

                    if (var5 == 4 || var5 == 8) {
                        for (var12 = 0; var12 < var9.og.length; ++var12) {
                            var13 = var6 - var10 / 2 + m(GameCanvas.gameTick - (var10 << 1) - (var12 << 2));
                            var14 = var7 - var10 / 2 + l(GameCanvas.gameTick - (var10 << 1) - (var12 << 2));
                            var8.setColor(var9.of[var11 - 1][var12]);
                            var8.fillRect(var13 - var9.og[var12] / 2, var14 - var9.og[var12] / 2, var9.og[var12], var9.og[var12]);
                        }
                    }

                    if (var5 != 1 && var5 != 4 && var5 != 8) {
                        for (var12 = 0; var12 < var9.og.length; ++var12) {
                            var13 = var6 - var10 / 2 + m(GameCanvas.gameTick - (var10 << 1) - (var12 << 2));
                            var14 = var7 - var10 / 2 + l(GameCanvas.gameTick - (var10 << 1) - (var12 << 2));
                            var8.setColor(var9.of[var11][var12]);
                            var8.fillRect(var13 - var9.og[var12] / 2, var14 - var9.og[var12] / 2, var9.og[var12], var9.og[var12]);
                        }
                    }

                    if (var5 != 1 && var5 != 4 && var5 != 8 && var5 != 12 && var5 != 2 && var5 != 5 && var5 != 9) {
                        for (var12 = 0; var12 < var9.og.length; ++var12) {
                            var13 = var6 - var10 / 2 + m(GameCanvas.gameTick - var10 - (var12 << 2));
                            var14 = var7 - var10 / 2 + l(GameCanvas.gameTick - var10 - (var12 << 2));
                            var8.setColor(var9.of[var11][var12]);
                            var8.fillRect(var13 - var9.og[var12] / 2, var14 - var9.og[var12] / 2, var9.og[var12], var9.og[var12]);
                        }
                    }

                    if (var5 != 1 && var5 != 4 && var5 != 8 && var5 != 12 && var5 != 2 && var5 != 5 && var5 != 9 && var5 != 13 && var5 != 3 && var5 != 6 && var5 != 10 && var5 != 15) {
                        for (var12 = 0; var12 < var9.og.length; ++var12) {
                            var13 = var6 - var10 / 2 + m(GameCanvas.gameTick - var10 * 3 - (var12 << 2));
                            var14 = var7 - var10 / 2 + l(GameCanvas.gameTick - var10 * 3 - (var12 << 2));
                            var8.setColor(var9.of[var11][var12]);
                            var8.fillRect(var13 - var9.og[var12] / 2, var14 - var9.og[var12] / 2, var9.og[var12], var9.og[var12]);
                        }
                    }
                }
            } else {
                a(graphic, item, x, y, var6);
            }
        }
        if (GameCanvas.gameTick % 6 == 0) {
            item.indexFrame = (item.indexFrame + 1) % 3;
        }

        if (item.isNewitem()) {
            graphic.drawImage(GameCanvas.dungHopImg, x + wItem / 2, y + wItem / 2, 3);
        }

        SmallImage.paintImage(graphic, item.template.iconID, x + wItem / 2, y + wItem / 2, 0, 3, item.indexFrame);
    }

    private static void a(mGraphics var0, Item var1, int var2, int var3, int var4) {
        if (!var1.isLock) {
            var0.setColor(12083);
        } else {
            var0.setColor(6425);
        }

        var0.fillRect(var2 + 1 + var4, var3 + 1 + var4, wItem - 2 - (var4 << 1), wItem - 2 - (var4 << 1));
        SmallImage.paintImage(var0, 154, var2 + wItem / 2, var3 + wItem / 2, 0, 3);
    }

    private void c(mGraphics var1, String[] var2) {
        try {
            Paint.a(popupX, popupY, ew, ex, var1);
            drawTitle(var1, var2[indexMenu], var2.length > 1);
            leftMargin = popupX + 3;
            nw = popupY + 34 + wItem;
            int var9 = popupX + 74;
            int var3 = nw - wItem - 3;
            oc = 4;
            if (itemSplit != null) {
                this.drawItemUI(var1, itemSplit, var9, var3);
            } else {
                var1.setColor(6425);
                var1.fillRect(var9 - 1, var3 - 1, wItem + 3, wItem + 3);
                SmallImage.paintImage(var1, 154, var9 + wItem / 2, var3 + wItem / 2, 0, 3);
            }

            var1.setColor(12281361);
            var1.drawRect(var9, var3, wItem, wItem);
            var1.setColor(6425);
            var1.fillRect(leftMargin - 1, nw - 1, wItem * ob + 3, wItem * oc + 3);

            int var4;
            int var5;
            for (var4 = 0; var4 < oc; ++var4) {
                for (var5 = 0; var5 < ob; ++var5) {
                    SmallImage.paintImage(var1, 154, leftMargin + var5 * wItem + wItem / 2, nw + var4 * wItem + wItem / 2, 0, 3);
                    var1.setColor(12281361);
                    var1.drawRect(leftMargin + var5 * wItem, nw + var4 * wItem, wItem, wItem);
                }
            }

            for (var4 = 0; var4 < arrItemSplit.length; ++var4) {
                Item var6;
                if ((var6 = arrItemSplit[var4]) != null) {
                    var5 = var4 / ob;
                    int var7 = var4 - var5 * ob;
                    if (!var6.isLock) {
                        var1.setColor(12083);
                        var1.fillRect(leftMargin + var7 * wItem + 1, nw + var5 * wItem + 1, wItem - 1, wItem - 1);
                    }

                    SmallImage.paintImage(var1, var6.template.iconID, leftMargin + var7 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2, 0, 3);
                }
            }

            if (gk == 1) {
                var1.setColor(16777215);
                var1.drawRect(var9, var3, wItem, wItem);
            } else if (gk == 2) {
                var4 = indexSelect / ob;
                var5 = indexSelect - var4 * ob;
                var1.setColor(16777215);
                var1.drawRect(leftMargin + var5 * wItem, nw + var4 * wItem, wItem, wItem);
            }

            if (ez != null) {
                SmallImage.paintImage(var1, ez.arrEfInfo[ey].c, var9 + wItem / 2 + ez.arrEfInfo[ey].a, var3 + wItem / 2 + ez.arrEfInfo[ey].b, 0, 3);
                if (GameCanvas.gameTick % 2 == 0 && ++ey >= ez.arrEfInfo.length) {
                    ey = 0;
                    ez = null;
                }
            }

            if (ii && itemSplit != null) {
                ItemOption var11 = null;

                for (var5 = 0; var5 < itemSplit.options.size() && (var11 = (ItemOption) itemSplit.options.elementAt(var5)).optionTemplate.id != 85; ++var5) {
                    var11 = null;
                }

                if (var11 != null) {
                    int[] var12 = new int[]{60, 45, 34, 26, 20, 15, 11, 8, 6};
                    int[] var13 = new int[]{150000, 247500, 408375, 673819, 1111801, 2056832, 4010822, 7420021, 12243035};
                    byte[] var10 = new byte[]{3, 5, 9, 4, 7, 10, 5, 7, 9};
                    mFont.tahoma_7_yellow.writeText(var1, mResources.doTinhLuyen + ": " + NinjaUtil.a(String.valueOf(var11.param + 1)), leftMargin + 1, nw + 114, 0);
                    mFont.tahoma_7_yellow.writeText(var1, mResources.hd + var12[var11.param] + "%)", leftMargin + 70, nw + 114, 0);
                    mFont.tahoma_7_yellow.writeText(var1, mResources.ag + ": " + NinjaUtil.a(String.valueOf(var13[var11.param])) + " " + mResources.kf + ", " + var10[var11.param] + " " + (var11.param < 3 ? mResources.sf[1] : (var11.param < 6 ? mResources.sf[2] : mResources.sf[3])), leftMargin + 1, nw + 126, 0);
                    return;
                }

                mFont.tahoma_7_red.writeText(var1, mResources.rx, leftMargin + 1, nw + 120, 0);
                return;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    private static void d(mGraphics var0, String[] var1) {
        try {
            resetCursor(var0);
            Paint.a(popupX, popupY, ew, ex, var0);
            drawTitle(var0, var1[indexMenu], var1.length > 1);
            leftMargin = popupX + 3;
            nw = popupY + 32;
            var0.setColor(6425);
            oc = 4;
            int var2;
            int var3;
            int var5;
            int var7;
            if (!hy) {
                mFont.tahoma_7_white.writeText(var0, mResources.ro[0], leftMargin + 3, nw + oc * wItem + 9, 0);
                mFont.tahoma_7_white.writeText(var0, mResources.ro[1], leftMargin + 3, nw + oc * wItem + 21, 0);
                mFont.tahoma_7_white.writeText(var0, mResources.ro[2], leftMargin + 3, nw + oc * wItem + 33, 0);
            } else {
                var3 = 0;
                var7 = 0;
                var2 = 0;
                boolean var4 = false;

                for (var5 = 0; var5 < arrItemUpPeal.length; ++var5) {
                    if (arrItemUpPeal[var5] != null) {
                        if (arrItemUpPeal[var5].isLock) {
                            var4 = true;
                        }

                        var3 += crystals[arrItemUpPeal[var5].template.id];
                        ++var7;
                    }
                }

                if (var3 > 0) {
                    for (var2 = crystals.length - 1; var2 >= 0 && var3 <= crystals[var2]; --var2) {
                    }
                }

                if (var2 >= crystals.length - 1) {
                    var2 = crystals.length - 2;
                }

                if (go) {
                    if (var7 > 1) {
                        mFont.tahoma_7_yellow.writeText(var0, mResources.gy + " " + (var2 + 2) + " " + (var4 ? mResources.ar : ""), leftMargin + 3, nw + oc * wItem + 9, 0);
                        if (coinUpCrystals[var2 + 1] > Char.getMyChar().xu) {
                            mFont.tahoma_7_red.writeText(var0, mResources.a(mResources.hb, NinjaUtil.a(String.valueOf(coinUpCrystals[var2 + 1]))), leftMargin + 3, nw + oc * wItem + 21, 0);
                        } else {
                            mFont.tahoma_7_yellow.writeText(var0, mResources.a(mResources.hb, NinjaUtil.a(String.valueOf(coinUpCrystals[var2 + 1]))), leftMargin + 3, nw + oc * wItem + 21, 0);
                        }

                        mFont.tahoma_7_yellow.writeText(var0, mResources.hc + ": " + var3 * 100 / crystals[var2 + 1] + "%", leftMargin + 3, nw + oc * wItem + 33, 0);
                    } else {
                        for (var5 = 1; var5 <= 2; ++var5) {
                            mFont.tahoma_7_white.writeText(var0, mResources.gu[var5], leftMargin + 3, nw + oc * wItem + 5 + (var5 - 1) * 12, 0);
                        }
                    }
                } else if (var7 > 1) {
                    mFont.tahoma_7_yellow.writeText(var0, mResources.gy + " " + (var2 + 2) + " " + mResources.ar, leftMargin + 3, nw + oc * wItem + 9, 0);
                    if (coinUpCrystals[var2 + 1] > Char.getMyChar().xu + Char.getMyChar().yen) {
                        mFont.tahoma_7_red.writeText(var0, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(coinUpCrystals[var2 + 1]))), leftMargin + 3, nw + oc * wItem + 21, 0);
                    } else {
                        mFont.tahoma_7_yellow.writeText(var0, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(coinUpCrystals[var2 + 1]))), leftMargin + 3, nw + oc * wItem + 21, 0);
                    }

                    mFont.tahoma_7_yellow.writeText(var0, mResources.hc + ": " + var3 * 100 / crystals[var2 + 1] + "%", leftMargin + 3, nw + oc * wItem + 33, 0);
                } else {
                    for (var5 = 0; var5 < 3; ++var5) {
                        mFont.tahoma_7_white.writeText(var0, mResources.gu[var5], leftMargin + 3, nw + oc * wItem + 5 + var5 * 12, 0);
                    }
                }
            }

            var0.setColor(0);
            var0.fillRect(leftMargin, nw, ob * wItem + 1, oc * wItem + 1);

            for (var3 = 0; var3 < oc; ++var3) {
                for (var7 = 0; var7 < ob; ++var7) {
                    SmallImage.paintImage(var0, 154, leftMargin + var7 * wItem + wItem / 2, nw + var3 * wItem + wItem / 2, 0, 3);
                    var0.setColor(12281361);
                    var0.drawRect(leftMargin + var7 * wItem, nw + var3 * wItem, wItem, wItem);
                }
            }

            for (var3 = 0; var3 < arrItemUpPeal.length; ++var3) {
                Item var8;
                if ((var8 = arrItemUpPeal[var3]) != null) {
                    var2 = var3 / ob;
                    var5 = var3 - var2 * ob;
                    if (!var8.isLock) {
                        var0.setColor(4543829);
                        var0.fillRect(leftMargin + var5 * wItem + 1, nw + var2 * wItem + 1, wItem - 1, wItem - 1);
                    }

                    SmallImage.paintImage(var0, var8.template.iconID, leftMargin + var5 * wItem + wItem / 2, nw + var2 * wItem + wItem / 2, 0, 3);
                }
            }

            if (gk > 0) {
                var3 = indexSelect / ob;
                var7 = indexSelect - var3 * ob;
                var0.setColor(16777215);
                var0.drawRect(leftMargin + var7 * wItem, nw + var3 * wItem, wItem, wItem);
            }

            if (ez != null) {
                SmallImage.paintImage(var0, ez.arrEfInfo[ey].c, leftMargin + wItem / 2 + ez.arrEfInfo[ey].a + 1, nw + wItem / 2 + 9 + ez.arrEfInfo[ey].b, 0, 3);
                if (GameCanvas.gameTick % 2 == 0 && ++ey >= ez.arrEfInfo.length) {
                    ey = 0;
                    ez = null;
                    return;
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    private void o(mGraphics var1) {
        if (ifa) {
            resetCursor(var1);
            Paint.a(popupX, popupY, ew, ex, var1);
            int var2 = ew;
            if (GameCanvas.isMinWidth320) {
                var2 = ew / 2 + 20;
            }

            var1.setColor(0);
            var1.fillRect(popupX + 7, popupY + 31, var2 - 14, ex - 58);
            var1.setColor(-3170504);
            var1.drawRect(popupX + 8, popupY + 32, var2 - 16, ex - 60);
            var1.setColor(Paint.a);
            var1.fillRect(popupX + 9, popupY + 33, var2 - 18, ex - 62);
            drawTitle(var1, mResources.qv, false);
            leftMargin = popupX + 33;
            nw = popupY + 40;

            int var3;
            for (var2 = 0; var2 < 3; ++var2) {
                for (var3 = 0; var3 < 3; ++var3) {
                    var1.setColor(Paint.COLORDARK);
                    var1.fillRect(leftMargin + var3 * 40, nw + 10 + var2 * 40, 29, 29);
                    var1.setColor(-6527695);
                    var1.drawRect(leftMargin + var3 * 40, nw + 10 + var2 * 40, 29, 29);
                    var1.setColor(-6737152);
                    var1.fillRect(leftMargin + var3 * 40 + 2, nw + 12 + var2 * 40, 26, 26);
                    var1.setColor(Paint.COLORDARK);
                    var1.fillRect(leftMargin + var3 * 40 + 4, nw + 14 + var2 * 40, 22, 22);
                    SmallImage.paintImage(var1, 1414, leftMargin + var3 * 40 + 20 - 5, nw + var2 * 40 + 20 + 4, 0, StaticObj.g);
                }
            }

            for (var2 = 0; var2 < 9; ++var2) {
                int var4;
                int var5;
                mGraphics var6;
                label77:
                {
                    var3 = var2 / 3;
                    var5 = var2 - var3 * 3;
                    if (arrItemSprin != null) {
                        var1.setColor(-16770791);
                        var1.fillRect(leftMargin + var5 * 40 + 4, nw + 14 + var3 * 40, 22, 22);
                        var1.setColor(var2 == indexSelect ? -1 : -6527695);
                        var1.drawRect(leftMargin + var5 * 40 + 4, nw + 14 + var3 * 40, 21, 21);
                        SmallImage.paintImage(var1, 154, leftMargin + var5 * 40 + 17 - 3, nw + 7 + var3 * 40 + 17, 0, 3);
                        if (System.currentTimeMillis() - this.eb < 1000L) {
                            if (var2 == an) {
                                SmallImage.paintImage(var1, ItemTemplateManager.getIconID(arrItemSprin[an]), leftMargin + var5 * 40 + 17 - 3, nw + 7 + var3 * 40 + 17, 0, 3);
                            } else {
                                SmallImage.paintImage(var1, 1414, leftMargin + var5 * 40 + 17 - 2, nw + 7 + var3 * 40 + 17, 0, StaticObj.g);
                            }
                        } else if (arrItemSprin[var2] >= 0 && arrItemSprin[var2] < ItemTemplateManager.itemTemplates.size()) {
                            SmallImage.paintImage(var1, ItemTemplateManager.getIconID(arrItemSprin[var2]), leftMargin + var5 * 40 + 17 - 3, nw + 7 + var3 * 40 + 17, 0, 3);
                        } else {
                            SmallImage.paintImage(var1, ItemTemplateManager.getIconID((short) 242), leftMargin + var5 * 40 + 17 - 3, nw + 7 + var3 * 40 + 17, 0, 3);
                        }

                        if (an == var2 && this.ed > 0) {
                            this.ef[var2] = String.valueOf(this.ed);
                        }

                        if (gk != 1) {
                            continue;
                        }

                        if (an == var2 && GameCanvas.gameTick % 10 > 4) {
                            var6 = var1;
                            var4 = -3368653;
                            break label77;
                        }

                        if (var2 == indexSelect) {
                            var6 = var1;
                            var4 = -1;
                            break label77;
                        }

                        var6 = var1;
                    } else {
                        if (gk != 1) {
                            continue;
                        }

                        var6 = var1;
                        if (var2 == indexSelect) {
                            var4 = -1;
                            break label77;
                        }
                    }

                    var4 = Paint.b;
                }

                var6.setColor(var4);
                var1.drawRect(leftMargin + var5 * 40, nw + 10 + var3 * 40, 29, 29);
            }

            mFont.tahoma_7_yellow.writeText(var1, mResources.qw + this.numSprinLeft, popupX + ew / 2, popupY + ex - 20, 2);
        }

    }

    private void p(mGraphics var1) {
        if (hr) {
            if (indexMenu == 0) {
                this.a(var1, mResources.fz, arrItemStack);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.fz);
            }
        }

    }

    private void q(mGraphics var1) {
        if (hs) {
            if (indexMenu == 0) {
                this.a(var1, mResources.ga, arrItemStackLock);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.ga);
            }
        }

    }

    private void r(mGraphics var1) {
        if (ht) {
            if (indexMenu == 0) {
                this.a(var1, mResources.gb, arrItemGrocery);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gb);
            }
        }

    }

    private void s(mGraphics var1) {
        if (hu) {
            if (indexMenu == 0) {
                this.a(var1, mResources.gc, arrItemGroceryLock);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gc);
            }
        }

    }

    private void t(mGraphics var1) {
        if (cd) {
            if (indexMenu == 0) {
                String[] var2 = mResources.ge;
                var1 = var1;
                GameScr var3 = this;

                try {
                    oc = 5;
                    Paint.a(popupX, popupY, ew, ex, var1);
                    showUIBox(var1, var2, false);
                    var1.setColor(6693376);
                    var1.fillRect(popupX + 3, popupY + 32, 168, 140);
                    var1.setColor(13408563);
                    var1.drawRect(popupX + 3, popupY + 32, 168, 140);
                    int var6 = popupX + 74;
                    int var4 = popupY + 40 + wItem;
                    mFont.tahoma_7_white.writeText(var1, mResources.mz, var6 + wItem / 2, var4 - wItem / 2 - 4, 2);
                    if (itemSell != null) {
                        var1.setColor(6425);
                        var1.fillRect(var6 - 1, var4 - 1, wItem + 3, wItem + 3);
                        SmallImage.paintImage(var1, 154, var6 + wItem / 2, var4 + wItem / 2, 0, 3);
                        var3.drawItemUI(var1, itemSell, var6, var4);
                        if (itemSell.quantity > 1) {
                            mFont.number_yellow.writeText(var1, String.valueOf(itemSell.quantity), var6 + wItem, var4 + wItem / 2 + 6, 1);
                        }

                        var1.setColor(gk == 1 ? 16777215 : 12281361);
                        var1.drawRect(var6, var4, wItem, wItem);
                    } else {
                        var1.setColor(6425);
                        var1.fillRect(var6 - 1, var4 - 1, wItem + 3, wItem + 3);
                        SmallImage.paintImage(var1, 154, var6 + wItem / 2, var4 + wItem / 2, 0, 3);
                        var1.setColor(12281361);
                        var1.drawRect(var6, var4, wItem, wItem);
                    }

                    mFont.tahoma_7_white.writeText(var1, mResources.na, var6 + wItem / 2, var4 + 3 * wItem / 2 + 2, 2);
                    if (Char.getMyChar().xu < 5000) {
                        mFont.tahoma_7_red.writeText(var1, mResources.qk, var6 + wItem / 2, popupY + ex - 25, 2);
                        mFont.tahoma_7_red.writeText(var1, mResources.ql, var6 + wItem / 2, popupY + ex - 13, 2);
                    } else {
                        mFont.tahoma_7_white.writeText(var1, mResources.qk, var6 + wItem / 2, popupY + ex - 25, 2);
                        mFont.tahoma_7_white.writeText(var1, mResources.ql, var6 + wItem / 2, popupY + ex - 13, 2);
                    }

                    var3.iz.a = popupX + 40;
                    var3.iz.b = popupY + 130;
                    var3.iz.a(var1);
                    return;
                } catch (Exception var6) {
                    var6.printStackTrace();
                    return;
                }
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.ge);
            }
        }

    }

    private void u(mGraphics var1) {
        if (hw) {
            if (indexMenu == 0) {
                String[] var2 = mResources.gf;
                var1 = var1;
                GameScr var3 = this;

                try {
                    oc = 3;
                    Paint.a(popupX, popupY, ew, ex, var1);
                    drawTitle(var1, var2[indexMenu], var2.length > 1);
                    leftMargin = popupX + 3;
                    nw = popupY + 34 + wItem;
                    int var8 = popupX + 45;
                    int var4 = popupX + 100;
                    int var5 = nw - wItem - 3;
                    if (arrItemConvert[0] != null) {
                        var3.drawItemUI(var1, arrItemConvert[0], var8, var5);
                        var1.setColor(12281361);
                        var1.drawRect(var8, var5, wItem, wItem);
                        mFont.tahoma_7_yellow.writeText(var1, "(+" + arrItemConvert[0].upgrade + ")", var8 - 5, var5 + wItem / 2 - 5, 1);
                    } else {
                        var1.setColor(6425);
                        var1.fillRect(var8 - 1, var5 - 1, wItem + 3, wItem + 3);
                        SmallImage.paintImage(var1, 154, var8 + wItem / 2, var5 + wItem / 2, 0, 3);
                        var1.setColor(12281361);
                        var1.drawRect(var8, var5, wItem, wItem);
                    }

                    SmallImage.paintImage(var1, 942, var8 + 43, nw - 15, 0, StaticObj.g);
                    Item var6;
                    if (arrItemConvert[1] != null) {
                        var6 = arrItemConvert[1].clone();
                        if (arrItemConvert[0] != null && arrItemConvert[0].template.type == var6.template.type && arrItemConvert[1].template.level >= arrItemConvert[0].template.level) {
                            var6.upgrade = arrItemConvert[0].upgrade;
                        }

                        var3.drawItemUI(var1, var6, var4, var5);
                        var1.setColor(12281361);
                        var1.drawRect(var4, var5, wItem, wItem);
                        mFont.tahoma_7_yellow.writeText(var1, "(+" + var6.upgrade + ")", var4 + wItem + 10, var5 + wItem / 2 - 5, 0);
                    } else {
                        var1.setColor(6425);
                        var1.fillRect(var4 - 1, var5 - 1, wItem + 3, wItem + 3);
                        SmallImage.paintImage(var1, 154, var4 + wItem / 2, var5 + wItem / 2, 0, 3);
                        var1.setColor(12281361);
                        var1.drawRect(var4, var5, wItem, wItem);
                    }

                    if (gk == 1) {
                        if (indexSelect == 0) {
                            var1.setColor(16777215);
                            var1.drawRect(var8, var5, wItem, wItem);
                        }

                        if (indexSelect == 1) {
                            var1.setColor(16777215);
                            var1.drawRect(var4, var5, wItem, wItem);
                        }
                    }

                    var1.setColor(0);
                    var1.fillRect(leftMargin - 1, nw - 1, ob * wItem + 3, oc * wItem + 3);

                    int var9;
                    for (var8 = 0; var8 < oc; ++var8) {
                        for (var9 = 0; var9 < ob; ++var9) {
                            SmallImage.paintImage(var1, 154, leftMargin + var9 * wItem + wItem / 2, nw + var8 * wItem + wItem / 2, 0, 3);
                            var1.setColor(12281361);
                            var1.drawRect(leftMargin + var9 * wItem, nw + var8 * wItem, wItem, wItem);
                        }
                    }

                    if ((var6 = arrItemConvert[2]) != null) {
                        var9 = 0 / ob;
                        var8 = 0 - var9 * ob;
                        if (!var6.isLock) {
                            var1.setColor(12083);
                            var1.fillRect(leftMargin + var8 * wItem + 1, nw + var9 * wItem + 1, wItem - 1, wItem - 1);
                        }

                        SmallImage.paintImage(var1, var6.template.iconID, leftMargin + var8 * wItem + wItem / 2, nw + var9 * wItem + wItem / 2, 0, 3);
                    }

                    mFont.tahoma_7_white.writeText(var1, "- " + mResources.ex[0], leftMargin, nw + oc * wItem + 10, 0);
                    mFont.tahoma_7_white.writeText(var1, "  " + mResources.ex[1], leftMargin, nw + oc * wItem + 22, 0);
                    mFont.tahoma_7_white.writeText(var1, "- " + mResources.ex[2], leftMargin, nw + oc * wItem + 34, 0);
                    if (gk == 2) {
                        var9 = indexSelect / ob;
                        var8 = indexSelect - var9 * ob;
                        var1.setColor(16777215);
                        var1.drawRect(leftMargin + var8 * wItem, nw + var9 * wItem, wItem, wItem);
                        return;
                    }
                } catch (Exception var9) {
                    var9.printStackTrace();
                }

                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gd);
            }
        }

    }

    private void v(mGraphics var1) {
        if (ia) {
            if (indexMenu == 0) {
                this.c(var1, mResources.gk);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gk);
            }
        }

    }

    private void w(mGraphics var1) {
        if (cl) {
            if (indexMenu == 0) {
                this.c(var1, mResources.gn);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gn);
            }
        }

    }

    private void x(mGraphics var1) {
        if (ii) {
            if (indexMenu == 0) {
                this.c(var1, mResources.go);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.go);
            }
        }

    }

    private void y(mGraphics var1) {
        if (ci) {
            if (indexMenu == 0) {
                String[] var2 = mResources.gl;
                var1 = var1;
                GameScr var3 = this;

                try {
                    Paint.a(popupX, popupY, ew, ex, var1);
                    drawTitle(var1, var2[indexMenu], var2.length > 1);
                    leftMargin = popupX + 3;
                    nw = popupY + 45;
                    oc = 4;
                    int var10003 = leftMargin + 1;
                    int var10004 = nw - 12;
                    mFont.tahoma_7_yellow.writeText(var1, Char.getMyChar().charName, var10003, var10004, 0);
                    int var8 = leftMargin;

                    int var4;
                    for (var4 = 0; var4 < 3; ++var4) {
                        if (var4 == var3.cz) {
                            mFont.tahoma_7_blue1.writeText(var1, String.valueOf(var4 + 1), var8 + 2 + var4 * 20, nw + oc * (wItem + 3) + 8, 0);
                        } else {
                            mFont.tahoma_7_grey.writeText(var1, String.valueOf(var4 + 1), var8 + 2 + var4 * 20, nw + oc * (wItem + 3) + 8, 0);
                        }

                        if (var4 < 2) {
                            SmallImage.paintImage(var1, 942, var8 + 14 + var4 * 20, nw + oc * (wItem + 3) + 13, 0, StaticObj.g);
                        }
                    }

                    mFont.tahoma_7_white.writeText(var1, NinjaUtil.a(String.valueOf(var3.db)) + " " + mResources.ke, leftMargin, nw + oc * wItem + 4, 0);
                    if (var3.cz == 0) {
                        var1.setColor(0);
                    }

                    if (var3.cz == 1) {
                        var1.setColor(210986);
                    }

                    if (var3.cz == 2) {
                        var1.setColor(805690);
                    }

                    var1.fillRect(leftMargin - 1, nw - 1, wItem * 3 + 3, (wItem << 2) + 3);

                    for (var4 = 0; var4 < oc; ++var4) {
                        for (var8 = 0; var8 < 3; ++var8) {
                            SmallImage.paintImage(var1, 154, leftMargin + var8 * wItem + wItem / 2, nw + var4 * wItem + wItem / 2, 0, 3);
                            var1.setColor(12281361);
                            var1.drawRect(leftMargin + var8 * wItem, nw + var4 * wItem, wItem, wItem);
                        }
                    }

                    if (gk == 1) {
                        var4 = indexSelect / 3;
                        var8 = indexSelect - var4 * 3;
                        var1.setColor(16777215);
                        var1.drawRect(leftMargin + var8 * wItem, nw + var4 * wItem, wItem, wItem);
                    }

                    int var5;
                    Item item;
                    if (arrItemTradeMe != null) {
                        for (var4 = 0; var4 < arrItemTradeMe.length; ++var4) {
                            if ((item = arrItemTradeMe[var4]) != null) {
                                var8 = var4 / 3;
                                var5 = var4 - var8 * 3;
                                if (!item.isLock) {
                                    var1.setColor(12083);
                                    var1.fillRect(leftMargin + var5 * wItem + 1, nw + var8 * wItem + 1, wItem - 1, wItem - 1);
                                }
                                if (GameCanvas.gameTick % 6 == 0) {
                                    item.indexFrame = (item.indexFrame + 1) % 3;
                                }

                                if (item.isNewitem()) {
                                    var1.drawImage(GameCanvas.dungHopImg, leftMargin + var5 * wItem + wItem / 2, nw + var8 * wItem + wItem / 2, 3);
                                }
                                SmallImage.paintImage(var1, item.template.iconID, leftMargin + var5 * wItem + wItem / 2, nw + var8 * wItem + wItem / 2, 0, 3, item.indexFrame);
                                if (item.quantity > 1) {
                                    mFont.number_yellow.writeText(var1, String.valueOf(item.quantity), leftMargin + var5 * wItem + wItem, nw + var8 * wItem + wItem - mFont.number_yellow.getHeight(), 1);
                                }

                                if (item.quantity > 1) {
                                    mFont.number_yellow.writeText(var1, String.valueOf(item.quantity), leftMargin + var5 * wItem + wItem, nw + var8 * wItem + wItem - mFont.number_yellow.getHeight(), 1);
                                }
                            }
                        }
                    }

                    leftMargin = popupX + ew - 2 - wItem * 3;
                    oc = 4;
                    mFont.tahoma_7_yellow.writeText(var1, var3.dw, popupX + ew - 2, nw - 12, 1);
                    var8 = popupX + ew - 3 - 60;

                    for (var4 = 0; var4 < 3; ++var4) {
                        if (var4 == var3.da) {
                            mFont.tahoma_7_blue1.writeText(var1, String.valueOf(var4 + 1), var8 + 2 + var4 * 20, nw + oc * (wItem + 3) + 8, 0);
                        } else {
                            mFont.tahoma_7_grey.writeText(var1, String.valueOf(var4 + 1), var8 + 2 + var4 * 20, nw + oc * (wItem + 3) + 8, 0);
                        }

                        if (var4 < 2) {
                            SmallImage.paintImage(var1, 942, var8 + 14 + var4 * 20, nw + oc * (wItem + 3) + 13, 0, StaticObj.g);
                        }
                    }

                    mFont.tahoma_7_white.writeText(var1, NinjaUtil.a(String.valueOf(var3.dc)) + " " + mResources.ke, popupX + ew - 2, nw + oc * wItem + 4, 1);
                    if (var3.da == 0) {
                        var1.setColor(0);
                    }

                    if (var3.da == 1) {
                        var1.setColor(210986);
                    }

                    if (var3.da == 2) {
                        var1.setColor(805690);
                    }

                    var1.fillRect(leftMargin - 1, nw - 1, wItem * 3 + 3, (wItem << 2) + 3);

                    for (var4 = 0; var4 < oc; ++var4) {
                        for (var8 = 0; var8 < 3; ++var8) {
                            SmallImage.paintImage(var1, 154, leftMargin + var8 * wItem + wItem / 2, nw + var4 * wItem + wItem / 2, 0, 3);
                            var1.setColor(12281361);
                            var1.drawRect(leftMargin + var8 * wItem, nw + var4 * wItem, wItem, wItem);
                        }
                    }

                    if (gk == 2) {
                        var4 = indexSelect / 3;
                        var8 = indexSelect - var4 * 3;
                        var1.setColor(16777215);
                        var1.drawRect(leftMargin + var8 * wItem, nw + var4 * wItem, wItem, wItem);
                    }

                    if (arrItemTradeOrder != null) {
                        for (var4 = 0; var4 < arrItemTradeOrder.length; ++var4) {
                            if ((item = arrItemTradeOrder[var4]) != null) {
                                var8 = var4 / 3;
                                var5 = var4 - var8 * 3;
                                if (!item.isLock) {
                                    var1.setColor(12083);
                                    var1.fillRect(leftMargin + var5 * wItem + 1, nw + var8 * wItem + 1, wItem - 1, wItem - 1);
                                }
                                if (GameCanvas.gameTick % 6 == 0) {
                                    item.indexFrame = (item.indexFrame + 1) % 3;
                                }

                                if (item.isNewitem()) {
                                    var1.drawImage(GameCanvas.dungHopImg, leftMargin + var5 * wItem + wItem / 2, nw + var8 * wItem + wItem / 2, 3);
                                }
                                SmallImage.paintImage(var1, item.template.iconID, leftMargin + var5 * wItem + wItem / 2, nw + var8 * wItem + wItem / 2, 0, 3, item.indexFrame);
                                if (item.quantity > 1) {
                                    mFont.number_yellow.writeText(var1, String.valueOf(item.quantity), leftMargin + var5 * wItem + wItem, nw + var8 * wItem + wItem - mFont.number_yellow.getHeight(), 1);
                                }

                                if (item.quantity > 1) {
                                    mFont.number_yellow.writeText(var1, String.valueOf(item.quantity), leftMargin + var5 * wItem + wItem, nw + var8 * wItem + wItem - mFont.number_yellow.getHeight(), 1);
                                }
                            }
                        }
                    }

                    var4 = (int) (System.currentTimeMillis() / 1000L);
                    if (var3.dd - var4 > 0 && var3.cz == 1 && var3.da == 1) {
                        mFont.tahoma_7_white.writeText(var1, mResources.ae + " " + (var3.dd - var4) + " " + mResources.ld, popupX + ew / 2, popupY + ex - 13, 2);
                        return;
                    }

                    if (var3.cz == 0) {
                        mFont.tahoma_7_white.writeText(var1, mResources.fg, popupX + ew / 2, popupY + ex - 13, 2);
                        return;
                    }
                } catch (Exception var10) {
                    var10.printStackTrace();
                }

                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gl);
            }
        }

    }

    private void z(mGraphics var1) {
        if (hy) {
            if (indexMenu == 0) {
                d(var1, mResources.gg);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gg);
            }
        }

    }

    private void aa(mGraphics var1) {
        if (ih) {
            if (indexMenu == 0) {
                d(var1, mResources.gm);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gm);
            }
        }

    }

    private void paintTabBox(mGraphics var1) {
        if (showBox) {
            if (indexMenu == 0) {
                String[] var2 = mResources.gh;
                var1 = var1;
                GameScr var3 = this;

                try {
                    resetCursor(var1);
                    showUIBox(var1, var2, false);
                    if (Char.getMyChar().arrItemBox == null) {
                        GameCanvas.a(popupX + 90, popupY + 75, var1);
                        mFont.tahoma_7b_white.writeText(var1, mResources.textLoading, popupX + 90, popupY + 90, 2);
                        return;
                    }
                    if (svTitle.equals("")) {
                        mFont.tahoma_7_white.writeText(var1, mResources.xuDangGui + ": " + NinjaUtil.a(String.valueOf(Char.getMyChar().bs)), popupX + ew / 2, popupY + ex - 18, 2);
                    }
                    var3.paintTabBag(var1, Char.getMyChar().arrItemBox);
                    return;
                } catch (Exception var5) {
                    var5.printStackTrace();
                    return;
                }
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gh);
            }
        }

    }

    private void showMenuPickItem(mGraphics var1) {
        if (showPickItem) {
            if (indexMenu == 0) {
                resetCursor(var1);
                showUIBox(var1, mResources.sp, false);
                paintTabNhatDo(var1, Code.pickUpListID);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.sp);
            }
        }

    }

    private void showMenuItemOther(mGraphics var1) {
        if (showAutoUseItem) {
            if (indexMenu == 0) {
                resetCursor(var1);
                showUIBox(var1, new String[]{"Tự Dùng Item", "Hành trang"}, false);
                paintTabNhatDo(var1, AutoUseItem.getListIds());
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, new String[]{"Tự Dùng Item", "Hành trang"});
            }
        }

        if (showDelItem) {
            if (indexMenu == 0) {
                resetCursor(var1);
                showUIBox(var1, mResources.menuItemDel, false);
                paintTabNhatDo(var1, Code.delListID);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.menuItemDel);
            }
        }

        if (showItemThrow) {
            if (indexMenu == 0) {
                resetCursor(var1);
                showUIBox(var1, mResources.menuItemThrow, false);
                paintTabNhatDo(var1, Code.throwListID);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.menuItemThrow);
            }
        }

        if (showItemGather) {
            if (indexMenu == 0) {
                resetCursor(var1);
                showUIBox(var1, mResources.menuItemGather, false);
                paintTabNhatDo(var1, AutoReceiver.gatherListID);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.menuItemGather);
            }
        }
    }

    private static void ae(mGraphics var0) {
        if (indexMenu == 2) {
            var0.translateXY(-var0.getTranslateX(), -var0.getTranslateY());
            Paint.a(popupX, popupY, ew, ex, var0);
            drawTitle(var0, mResources.nameMenuIndex[indexMenu], true);
            mFont.tahoma_8b.writeText(var0, mResources.jg, popupX + 10, popupY + 33, 0);
            mFont.tahoma_8b.writeText(var0, "" + Char.getMyChar().ai, popupX + ew - 10, popupY + 33, 1);
            int var1 = (ex - 80) / 5;

            for (int var2 = 0; var2 < Char.getMyChar().potential.length; ++var2) {
                var0.setColor(Paint.d);
                if (gk > 0 && gk - 1 == var2) {
                    var0.setColor(Paint.COLORDARK);
                    var0.fillRect(popupX + 5, popupY + 52 + var2 * (var1 + 4), ew - 10, var1);
                    var0.setColor(Paint.e);
                }

                var0.drawRect(popupX + 5, popupY + 52 + var2 * (var1 + 4), ew - 10, var1);
                mFont.tahoma_7b_white.writeText(var0, "" + Char.getMyChar().potential[var2], popupX + ew - 10, popupY + 52 + (var1 - 10) / 2 + var2 * (var1 + 4), 1);
                mFont.tahoma_7b_white.writeText(var0, mResources.iz[var2], popupX + 10, popupY + 52 + (var1 - 10) / 2 + var2 * (var1 + 4), 0);
            }

            if (gk > 0) {
                switch (Char.getMyChar().nClass.classId) {
                    case 0:
                        mFont.tahoma_7_green.writeText(var0, mResources.oj[0], popupX + 10, popupY + 52 + (var1 - 10) / 2 + 4 * (var1 + 4), 0);
                        return;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                        mFont.tahoma_7_green.writeText(var0, mResources.ok[gk - 1], popupX + 10, popupY + 52 + (var1 - 10) / 2 + 4 * (var1 + 4), 0);
                        return;
                    case 2:
                    case 4:
                    case 6:
                        mFont.tahoma_7_green.writeText(var0, mResources.ol[gk - 1], popupX + 10, popupY + 52 + (var1 - 10) / 2 + 4 * (var1 + 4), 0);
                }
            }
        }

    }

    private static Item getCurrentItemSelectByTypeUI(int typeUI) {
        try {
            if (indexSelect < 0) {
                return null;
            }

            switch (typeUI) {
                case 2:
                    if (arrItemWeapon.length > indexSelect) {
                        return arrItemWeapon[indexSelect];
                    }

                    return null;
                case 3:
                    return Char.getMyChar().arrItemBag[indexSelect];
                case 4:
                    return Char.getMyChar().arrItemBox[indexSelect];
                case 5:
                    return currentCharViewInfo.arrItemBody[indexSelect + (showFashion ? 16 : 0)];
                case 6:
                    if (arrItemStack.length > indexSelect) {
                        return arrItemStack[indexSelect];
                    }

                    return null;
                case 7:
                    if (arrItemStackLock.length > indexSelect) {
                        return arrItemStackLock[indexSelect];
                    }

                    return null;
                case 8:
                    if (arrItemGrocery.length > indexSelect) {
                        return arrItemGrocery[indexSelect];
                    }

                    return null;
                case 9:
                    if (arrItemGroceryLock.length > indexSelect) {
                        return arrItemGroceryLock[indexSelect];
                    }

                    return null;
                case 10:
                    return arrItemUpGrade[indexSelect];
                case 11:
                    return arrItemUpPeal[indexSelect];
                case 12:
                case 13:
                case 30:
                case 31:
                case 33:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                default:
                    break;
                case 14:
                    if (arrItemStore.length > indexSelect) {
                        return arrItemStore[indexSelect];
                    }

                    return null;
                case 15:
                    if (arrItemBook.length > indexSelect) {
                        return arrItemBook[indexSelect];
                    }

                    return null;
                case 16:
                    if (arrItemLien.length > indexSelect) {
                        return arrItemLien[indexSelect];
                    }

                    return null;
                case 17:
                    if (arrItemNhan.length > indexSelect) {
                        return arrItemNhan[indexSelect];
                    }

                    return null;
                case 18:
                    if (arrItemNgocBoi.length > indexSelect) {
                        return arrItemNgocBoi[indexSelect];
                    }

                    return null;
                case 19:
                    if (arrItemPhu.length > indexSelect) {
                        return arrItemPhu[indexSelect];
                    }

                    return null;
                case 20:
                    if (arrItemNonNam.length > indexSelect) {
                        return arrItemNonNam[indexSelect];
                    }

                    return null;
                case 21:
                    if (arrItemNonNu.length > indexSelect) {
                        return arrItemNonNu[indexSelect];
                    }

                    return null;
                case 22:
                    if (arrItemAoNam.length > indexSelect) {
                        return arrItemAoNam[indexSelect];
                    }

                    return null;
                case 23:
                    if (arrItemAoNu.length > indexSelect) {
                        return arrItemAoNu[indexSelect];
                    }

                    return null;
                case 24:
                    if (arrItemGangTayNam.length > indexSelect) {
                        return arrItemGangTayNam[indexSelect];
                    }

                    return null;
                case 25:
                    if (arrItemGangTayNu.length > indexSelect) {
                        return arrItemGangTayNu[indexSelect];
                    }

                    return null;
                case 26:
                    if (arrItemQuanNam.length > indexSelect) {
                        return arrItemQuanNam[indexSelect];
                    }

                    return null;
                case 27:
                    if (arrItemQuanNu.length > indexSelect) {
                        return arrItemQuanNu[indexSelect];
                    }

                    return null;
                case 28:
                    if (arrItemGiayNam.length > indexSelect) {
                        return arrItemGiayNam[indexSelect];
                    }

                    return null;
                case 29:
                    if (arrItemGiayNu.length > indexSelect) {
                        return arrItemGiayNu[indexSelect];
                    }

                    return null;
                case 32:
                    if (arrItemFashion.length > indexSelect) {
                        return arrItemFashion[indexSelect];
                    }

                    return null;
                case 34:
                    if (arrItemClanShop.length > indexSelect) {
                        return arrItemClanShop[indexSelect];
                    }

                    return null;
                case 35:
                    if (arrItemElites.length > indexSelect) {
                        return arrItemElites[indexSelect];
                    }

                    return null;
                case 43:
                    return arrItemUpPeal[indexSelect];
                case 44:
                    return arrItemSplit[indexSelect];
                case 45:
                    return arrItemSplit[indexSelect];
                case 46:
                    return arrItemSplit[indexSelect];
                case 47:
                    return arrItemUpGrade[indexSelect];
                case 48:
                    return arrItemSplit[indexSelect];
            }
        } catch (Exception var2) {
        }

        return null;
    }

    public static void ac() {
        TileMap.d();
    }

    private static void drawTitle(mGraphics var0, String title, boolean var2) {
        int var3 = gW / 2;
        if (!svTitle.equals("")) {
            title = svTitle;
        }
        var0.setColor(Paint.COLORDARK);
        var0.fillRoundRect(var3 - mFont.tahoma_8b.a(title) / 2 - 12, popupY + 4, mFont.tahoma_8b.a(title) + 22, 24, 6, 6);
        if ((gk == 0 || GameCanvas.isTouch) && var2) {
            SmallImage.paintImage(var0, 989, var3 - mFont.tahoma_8b.a(title) / 2 - 15 - 7 - (GameCanvas.gameTick % 8 <= 3 ? 2 : 0), popupY + 16, 2, StaticObj.g);
            SmallImage.paintImage(var0, 989, var3 + mFont.tahoma_8b.a(title) / 2 + 15 + 5 + (GameCanvas.gameTick % 8 <= 3 ? 2 : 0), popupY + 16, 0, StaticObj.g);
        }

        if (gk == 0) {
            var0.setColor(Paint.e);
        } else {
            var0.setColor(Paint.d);
        }

        var0.drawRoundRect(var3 - mFont.tahoma_8b.a(title) / 2 - 12, popupY + 4, mFont.tahoma_8b.a(title) + 22, 24, 6, 6);
        mFont.tahoma_8b.writeText(var0, title, var3, popupY + 9, 2);
    }

    private void af(mGraphics var1) {
        if (showZoneDialog) {
            resetCursor(var1);
            showUIBox(var1, new String[]{mResources.mh}, false);
            mFont.tahoma_7_yellow.writeText(var1, TileMap.mapName, popupX + ew / 2, popupY + ex - 25, 2);
            if (indexSelect >= 0 && indexSelect < this.zones.length) {
                mFont.tahoma_7_white.writeText(var1, mResources.mi + ": " + this.zones[indexSelect] + ", " + mResources.mj + ": " + this.ir[indexSelect], popupX + ew / 2, popupY + ex - 13, 2);
            }

            int var2 = indexSelect / this.iq;
            int var3 = indexSelect % this.iq;
            oc = this.zones.length / this.iq;
            if (this.zones.length % this.iq > 0) {
                ++oc;
            }

            if (oc < 5) {
                oc = 5;
            }

            scrMain.a(oc, wItem, leftMargin, nw, ob * wItem + 2, 5 * wItem + 2, true, 6);
            scrMain.a(var1);
            int var4 = 0;

            for (int var5 = 0; var5 < oc; ++var5) {
                for (int var6 = 0; var6 < this.iq; ++var6) {
                    var1.setColor(12281361);
                    var1.drawRect(leftMargin + var6 * wItem, nw + var5 * wItem, wItem, wItem);
                    if (var4 < this.zones.length) {
                        SmallImage.paintImage(var1, 154, leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2, 0, 3);
                        if (this.zones[var4] >= 20) {
                            mFont.tahoma_7b_red.writeText(var1, String.valueOf(var4), leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2 - 4, 2);
                        } else if (this.zones[var4] >= 15) {
                            mFont.tahoma_7b_yellow.writeText(var1, String.valueOf(var4), leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2 - 4, 2);
                        } else {
                            mFont.tahoma_7b_white.writeText(var1, String.valueOf(var4), leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2 - 4, 2);
                        }

                        ++var4;
                    }
                }
            }

            if (indexSelect >= 0) {
                var1.setColor(16777215);
                var1.drawRect(leftMargin + var3 * wItem, nw + var2 * wItem, wItem, wItem);
            }
        }

    }

    private static void cp() {
        int var0 = 0;
        int var1 = 0;
        boolean var2 = false;
        boolean var3 = false;

        int var4;
        for (var4 = 0; var4 < arrItemUpPeal.length; ++var4) {
            if (arrItemUpPeal[var4] != null) {
                ++var0;
                var1 += crystals[arrItemUpPeal[var4].template.id];
                if (arrItemUpPeal[var4].template.id == 11) {
                    GameCanvas.msgdlg.a(mResources.ln, (Command) null, new Command(mResources.am, 1), (Command) null);
                    GameCanvas.currentDialog = GameCanvas.msgdlg;
                    return;
                }
            }

            if (arrItemUpPeal[var4] != null && arrItemUpPeal[var4].isLock) {
                var2 = true;
            }

            if (arrItemUpPeal[var4] != null && !arrItemUpPeal[var4].isLock) {
                var3 = true;
            }
        }

        if (var0 <= 1) {
            GameCanvas.msgdlg.a(mResources.lj, (Command) null, new Command(mResources.am, 1), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        } else {
            for (var4 = crystals.length - 1; var4 >= 0 && var1 <= crystals[var4]; --var4) {
            }

            if (var4 >= crystals.length - 1) {
                var4 = crystals.length - 2;
            }

            if (go) {
                if (coinUpCrystals[var4 + 1] > Char.getMyChar().xu) {
                    GameCanvas.msgdlg.a(mResources.lp, (Command) null, new Command(mResources.am, 1), (Command) null);
                    GameCanvas.currentDialog = GameCanvas.msgdlg;
                } else if (var2) {
                    GameCanvas.a(mResources.lo, 88813, arrItemUpPeal, 8882, (Object) null);
                } else {
                    Service.getInstance().crystalCollect(arrItemUpPeal);
                }
            } else if (coinUpCrystals[var4 + 1] > Char.getMyChar().xu + Char.getMyChar().yen && coinUpCrystals[var4 + 1] > Char.getMyChar().yen) {
                GameCanvas.msgdlg.a(mResources.lp, (Command) null, new Command(mResources.am, 1), (Command) null);
                GameCanvas.currentDialog = GameCanvas.msgdlg;
            } else if (var3) {
                GameCanvas.a(mResources.lo, 88814, arrItemUpPeal, 8882, (Object) null);
            } else {
                Service.getInstance().crystalCollectLock(arrItemUpPeal);
            }
        }

    }

    private static void cq() {
        int var0 = 0;
        int var1 = 0;
        int var2 = 0;
        short var3 = 0;

        for (int var4 = 0; var4 < arrItemUpPeal.length; ++var4) {
            Item var5;
            if ((var5 = arrItemUpPeal[var4]) != null) {
                if (var5.template.id == 455) {
                    ++var0;
                } else if (var5.template.id == 456) {
                    ++var1;
                } else if (var5.template.type == 26) {
                    ++var2;
                    var3 = var5.template.id;
                }
            }
        }

        if (var2 > 1) {
            GameCanvas.setText(mResources.rr);
        } else if (var0 <= 9 && var1 <= 9 && (var3 < 10 || var0 <= 3 && var1 <= 3)) {
            if (var0 + var1 < 3) {
                GameCanvas.setText(mResources.rv);
            } else if ((var3 != 10 || var1 != 3) && (var3 != 11 || var0 != 3)) {
                Service.getInstance().d(arrItemUpPeal);
            } else {
                GameCanvas.setText(mResources.rs);
            }
        } else {
            GameCanvas.setText(mResources.rw);
        }

    }

    public static byte ad() {
        if (Char.getMyChar().ctaskId >= dg.length) {
            return -3;
        } else {
            byte var0;
            if (Char.getMyChar().taskMaint == null) {
                var0 = dh[Char.getMyChar().ctaskId][0];
            } else {
                var0 = dh[Char.getMyChar().ctaskId][Char.getMyChar().taskMaint.a + 1];
            }

            if (var0 == -1) {
                if (Char.getMyChar().nClass.classId == 0 && Char.getMyChar().ctaskId == 9) {
                    var0 = -2;
                } else if (Char.getMyChar().nClass.classId != 0 && Char.getMyChar().nClass.classId != 1 && Char.getMyChar().nClass.classId != 2 && Char.getMyChar().nClass.classId != 7) {
                    if (Char.getMyChar().nClass.classId != 3 && Char.getMyChar().nClass.classId != 4) {
                        if (Char.getMyChar().nClass.classId == 5 || Char.getMyChar().nClass.classId == 6) {
                            var0 = 27;
                        }
                    } else {
                        var0 = 72;
                    }
                } else {
                    var0 = 1;
                }
            }

            return var0;
        }
    }

    public static byte ae() {
        try {
            if (Char.getMyChar().ctaskId >= dg.length) {
                return -3;
            } else {
                byte var0;
                if (Char.getMyChar().taskMaint == null) {
                    var0 = dg[Char.getMyChar().ctaskId][0];
                } else {
                    var0 = dg[Char.getMyChar().ctaskId][Char.getMyChar().taskMaint.a + 1];
                }

                if (var0 == -1) {
                    if (Char.getMyChar().nClass.classId == 0 && Char.getMyChar().ctaskId == 9) {
                        var0 = -2;
                    } else if (Char.getMyChar().nClass.classId != 0 && Char.getMyChar().nClass.classId != 1 && Char.getMyChar().nClass.classId != 2 && Char.getMyChar().nClass.classId != 7) {
                        if (Char.getMyChar().nClass.classId != 3 && Char.getMyChar().nClass.classId != 4) {
                            if (Char.getMyChar().nClass.classId == 5 || Char.getMyChar().nClass.classId == 6) {
                                var0 = 11;
                            }
                        } else {
                            var0 = 10;
                        }
                    } else {
                        var0 = 9;
                    }
                }

                return var0;
            }
        } catch (Exception var1) {
            return -1;
        }
    }

    private static void cr() {
        int var0 = 0;

        for (int var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
            if (arrItemUpGrade[var1] != null && arrItemUpGrade[var1].template.type == 26) {
                var0 += crystals[arrItemUpGrade[var1].template.id];
            }
        }

        boolean var4 = false;
        boolean var2 = false;
        int var3 = 0;
        if (itemUpGrade.isTypeClothe()) {
            if (coinUpClothes[itemUpGrade.upgrade] > Char.getMyChar().xu + Char.getMyChar().yen && coinUpClothes[itemUpGrade.upgrade] > Char.getMyChar().yen) {
                var4 = true;
            }

            var3 = var0 * 100 / upClothe[itemUpGrade.upgrade];
        } else if (itemUpGrade.isTypeAdorn()) {
            if (coinUpAdorns[itemUpGrade.upgrade] > Char.getMyChar().xu + Char.getMyChar().yen && coinUpAdorns[itemUpGrade.upgrade] > Char.getMyChar().yen) {
                var4 = true;
            }

            var3 = var0 * 100 / upAdorn[itemUpGrade.upgrade];
        } else if (itemUpGrade.isTypeWeapon()) {
            if (coinUpWeapons[itemUpGrade.upgrade] > Char.getMyChar().xu + Char.getMyChar().yen && coinUpWeapons[itemUpGrade.upgrade] > Char.getMyChar().yen) {
                var4 = true;
            }

            var3 = var0 * 100 / upWeapon[itemUpGrade.upgrade];
        }

        if (hx && goldUps[itemUpGrade.upgrade] > Char.getMyChar().luong) {
            var2 = true;
        }

        if (var4) {
            InfoMe.a(mResources.lr, 15, mFont.tahoma_7_red);
        } else if (var2) {
            InfoMe.a(mResources.lq, 15, mFont.tahoma_7_red);
        } else if (var3 > 250) {
            GameCanvas.a(mResources.ls, 88815, (Object) null, 8882, (Object) null);
        } else {
            af();
        }

    }

    public static void af() {
        if (!itemUpGrade.isLock) {
            GameCanvas.a(mResources.lt, new Command(mResources.bn, 11063), new Command(mResources.ca, 1));
        } else {
            Service.getInstance().upgradeItem(itemUpGrade, arrItemUpGrade, hx);
        }

    }

    private static void cs() {
        if (ij) {
            Service.getInstance().ngockham((byte) 1, (Item) null, itemSplit, arrItemSplit);
        } else if (ii) {
            Service.getInstance().a(itemSplit, arrItemSplit);
        } else if (cl) {
            Service.getInstance().c(itemSplit, arrItemSplit);
        } else if (itemSplit.upgrade == 0) {
            GameCanvas.msgdlg.a(mResources.lu, (Command) null, new Command(mResources.am, 1), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        } else {
            int var0 = -1;
            int var1 = 0;

            int var2;
            for (var2 = 0; var2 < Char.getMyChar().arrItemBag.length; ++var2) {
                if (Char.getMyChar().arrItemBag[var2] == null) {
                    ++var0;
                }
            }

            for (var2 = 0; var2 < arrItemSplit.length; ++var2) {
                if (arrItemSplit[var2] != null) {
                    ++var1;
                }
            }

            if (var1 > var0) {
                GameCanvas.msgdlg.a(mResources.lv, (Command) null, new Command(mResources.am, 1), (Command) null);
                GameCanvas.currentDialog = GameCanvas.msgdlg;
            } else {
                GameCanvas.a(mResources.lw, new Command(mResources.bn, 11087, itemSplit), new Command(mResources.ca, 1));
            }
        }

    }

    private void a(Item var1, Command var2) {
        this.getItemInfo((int) 3, (Item) var1);
        if (var2 != null) {
            super.left = new Command(var2.caption, 11040);
        }

    }

    private void getItemInfo(int typeUI, Item item) {
        if (item != null) {
            this.currentItem = item;
            oj = 120;
            ok = 120;
            if (GameCanvas.isTouch && !GameCanvas.isMaxWidth320) {
                ok += 18;
            }

            cg = true;
            gn.a();
            indexRow = 0;
            if (item.expires == 0L) {
                if (ii || cl || ij || ik || im || il) {
                    Service.getInstance().requestItemInfo(item.typeUI, item.indexUI);
                }

                if (ig) {
                    Service.getInstance().requestItemAuction(item.itemId);
                } else if (currentCharViewInfo.charID == Char.getMyChar().charID) {
                    Service.getInstance().requestItemInfo(typeUI, item.indexUI);
                } else {
                    Service.getInstance().requestItemPlayer(currentCharViewInfo.charID, item.indexUI);
                }
            }

            if (typeUI == 5) {
                Char.getMyChar().z();
            }

            if (!GameCanvas.isTouch || GameCanvas.isTouch && GameCanvas.isMaxWidth320 || isPaintInfoMe && indexMenu > 0 && indexMenu < 4 || id && indexMenu == 0) {
                super.center = this.on;
                super.right = null;
                super.left = null;
            }

            GameCanvas.l();
            GameCanvas.k();
        }

    }

    public final void a(String var1, String var2, boolean var3) {
        InfoDlg.d();
        ce = true;
        this.iw = true;
        indexRow = 0;
        b(175, 200);
        if (var3) {
            ex -= 0;
        }

        super.right = new Command(mResources.am, 3);
        super.left = super.center = null;
        this.iy = var1;
        this.ix = mFont.tahoma_7.a(var2, ew - 30);
    }

    public final void closeDialog() {
        ce = false;
        this.iy = null;
        this.ix = null;
        super.center = null;
        this.resetButton();
    }

    public final void ah() {
        totalRowSetting = this.ix.size();
        scrMain.a(totalRowSetting, 12, popupX, nw + 12, ew, ex - 42 - (this.iy != null ? 10 : 0), true, 1);
        indexRow = this.ix.size() - 1;
        scrMain.a(indexRow * scrMain.h);
    }

    private void ag(mGraphics var1) {
        if (this.ix != null && ce) {
            resetCursor(var1);
            Paint.a(popupX, popupY, ew, ex, var1);
            if (this.iy != null) {
                drawTitle(var1, this.iy, ck);
            }

            leftMargin = popupX + 15;
            nw = popupY + 15;
            if (this.iy != null) {
                nw += 10;
            }

            totalRowSetting = this.ix.size();
            scrMain.a(totalRowSetting, 12, popupX, nw + 12, ew, ex - 42 - (this.iy != null ? 10 : 0), true, 1);
            scrMain.a(var1);
            this.od = nw;
            mFont var2 = mFont.tahoma_7_white;

            String var3;
            for (int var4 = 0; var4 < this.ix.size() && (var3 = (String) this.ix.elementAt(var4)) != null && this.ix != null && var2 != null; ++var4) {
                if (var3.startsWith("c")) {
                    if (var3.startsWith("c0")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7_white;
                    } else if (var3.startsWith("c1")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7b_yellow;
                    } else if (var3.startsWith("c2")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7b_white;
                    } else if (var3.startsWith("c3")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7_yellow;
                    } else if (var3.startsWith("c4")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7b_red;
                    } else if (var3.startsWith("c5")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7_red;
                    } else if (var3.startsWith("c6")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7_grey;
                    } else if (var3.startsWith("c7")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7b_blue;
                    } else if (var3.startsWith("c8")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7_blue;
                    } else if (var3.startsWith("c9")) {
                        var3 = var3.substring(2);
                        var2 = mFont.tahoma_7_green;
                    }
                }

                var2.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
            }

            if (indexRow >= 0) {
                SmallImage.paintImage(var1, 942, leftMargin - 5, nw + 12 + 1 + indexRow * 12, 0, StaticObj.b);
            }
        }

    }

    private static void ah(mGraphics var0) {
        if (gw) {
            Paint.a(popupX, popupY, ew, ex, var0);
            drawTitle(var0, mResources.ii, false);
            leftMargin = popupX + 5;
            nw = popupY + 40;
            if (z.size() == 0) {
                mFont.tahoma_7_white.writeText(var0, mResources.nu, popupX + ew / 2, popupY + 40, 2);
            } else {
                var0.setColor(6425);
                var0.fillRect(leftMargin - 2, nw - 2, ew - 6, wItem * 5 + 8);
                resetCursor(var0);
                scrMain.a(z.size(), wItem, leftMargin, nw, ew - 3, wItem * 5 + 4, true, 1);
                scrMain.a(var0, leftMargin, nw, ew - 3, wItem * 5 + 6);
                totalRowSetting = z.size();

                for (int var1 = 0; var1 < z.size(); ++var1) {
                    Party var2 = (Party) z.elementAt(var1);
                    if (indexRow == var1) {
                        var0.setColor(Paint.b);
                        var0.fillRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                        var0.setColor(16777215);
                        var0.drawRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                    } else {
                        var0.setColor(Paint.a);
                        var0.fillRect(leftMargin + 2, nw + var1 * wItem + 2, ew - 15, wItem - 4);
                        var0.setColor(13932896);
                        var0.drawRect(leftMargin + 2, nw + var1 * wItem + 2, ew - 15, wItem - 4);
                    }

                    SmallImage.paintImage(var0, 647, leftMargin + 12, nw + var1 * wItem + wItem / 2, 0, 3);
                    mFont.tahoma_7_white.writeText(var0, var2.d + " - " + mResources.ec + ": " + var2.b + " (" + var2.g + ")", leftMargin + 22, nw + var1 * wItem + wItem / 2 - 6, 0);
                }
            }

            m(var0);
        }

    }

    private static void ai(mGraphics var0) {
        if (gv) {
            Paint.a(popupX, popupY, ew, ex, var0);
            drawTitle(var0, mResources.ih, false);
            leftMargin = popupX + 5;
            nw = popupY + 40;
            if (vParty.size() == 0) {
                mFont.tahoma_7_white.writeText(var0, mResources.ns, popupX + ew / 2, popupY + 40, 2);
            } else {
                var0.setColor(6425);
                var0.fillRect(leftMargin - 2, nw - 2, ew - 6, wItem * 5 + 8);
                resetCursor(var0);
                scrMain.a(vParty.size(), wItem, leftMargin, nw, ew - 3, wItem * 5 + 4, true, 1);
                scrMain.a(var0, leftMargin, nw, ew - 3, wItem * 5 + 6);
                totalRowSetting = vParty.size();

                for (int var1 = 0; var1 < vParty.size(); ++var1) {
                    Party var2 = (Party) vParty.elementAt(var1);
                    if (indexRow == var1) {
                        var0.setColor(Paint.b);
                        var0.fillRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                        var0.setColor(16777215);
                        var0.drawRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                    } else {
                        var0.setColor(Paint.a);
                        var0.fillRect(leftMargin + 2, nw + var1 * wItem + 2, ew - 15, wItem - 4);
                        var0.setColor(13932896);
                        var0.drawRect(leftMargin + 2, nw + var1 * wItem + 2, ew - 15, wItem - 4);
                    }

                    SmallImage.paintImage(var0, var2.c, leftMargin + 12, nw + var1 * wItem + wItem / 2, 0, 3);
                    if (var2.f == null) {
                        mFont.tahoma_7_green.writeText(var0, var2.d, leftMargin + 22, nw + var1 * wItem + wItem / 2 - 6, 0);
                    } else if (var1 == 0) {
                        mFont.tahoma_7_yellow.writeText(var0, var2.d + " - " + mResources.ec + ": " + var2.f.cLevel, leftMargin + 22, nw + var1 * wItem + wItem / 2 - 6, 0);
                    } else {
                        mFont.tahoma_7_white.writeText(var0, var2.d + " - " + mResources.ec + ": " + var2.f.cLevel, leftMargin + 22, nw + var1 * wItem + wItem / 2 - 6, 0);
                    }
                }
            }

            m(var0);
        }

    }

    private static void aj(mGraphics var0) {
        if (ig) {
            int var1 = ew;
            if (GameCanvas.isMinWidth320) {
                var1 = ew / 2 + 20;
            }

            Paint.a(popupX, popupY, ew, ex, var0);
            drawTitle(var0, mResources.qs[indexMenu], GameCanvas.isTouch ? true : gk == 0);
            var0.setColor(6425);
            var0.fillRect(leftMargin - 2, nw - 2, var1 - 6, wItem * 5 + 4);
            if (arrItemStands == null) {
                GameCanvas.a(popupX + 90, popupY + 75, var0);
                mFont.tahoma_7b_white.writeText(var0, mResources.textLoading, popupX + 90, popupY + 90, 2);
                return;
            }

            ItemStands[] var2 = arrItemStands;
            leftMargin = popupX + 5;
            nw = popupY + 33;
            if (var2.length > 0) {
                totalRowSetting = var2.length;
                resetCursor(var0);
                scrMain.a(totalRowSetting, wItem, leftMargin, nw, var1 - 3, wItem * 5, true, 1);
                scrMain.a(var0, leftMargin, nw, var1 - 3, wItem * 5 + 2);

                for (int var3 = 0; var3 < var2.length; ++var3) {
                    ItemStands var4;
                    if ((var4 = var2[var3]) != null && var4.item != null && var4.item.template != null) {
                        int var5 = (int) (System.currentTimeMillis() / 1000L);
                        if (var3 * wItem >= scrMain.b - wItem && var3 * wItem < scrMain.b + wItem * 5 + 4) {
                            if (indexSelect == var3) {
                                var0.setColor(Paint.b);
                                var0.fillRect(leftMargin + 2, nw + indexSelect * wItem + 2, var1 - 15, wItem - 4);
                                var0.setColor(16777215);
                                var0.drawRect(leftMargin + 2, nw + indexSelect * wItem + 2, var1 - 15, wItem - 4);
                            } else {
                                var0.setColor(Paint.a);
                                var0.fillRect(leftMargin + 2, nw + var3 * wItem + 2, var1 - 15, wItem - 4);
                                var0.setColor(13932896);
                                var0.drawRect(leftMargin + 2, nw + var3 * wItem + 2, var1 - 15, wItem - 4);
                            }

                            var0.setColor(0);
                            var0.fillRect(leftMargin + 4, nw + var3 * wItem + 4, wItem - 1, wItem - 8);
                            var0.setColor(indexSelect == var3 ? 16777215 : 12281361);
                            var0.drawRect(leftMargin + 4, nw + var3 * wItem + 4, wItem - 1, wItem - 8);

                            if (GameCanvas.gameTick % 6 == 0) {
                                var4.item.indexFrame = (var4.item.indexFrame + 1) % 3;
                            }
                            var0.setColor(11403519);
                            if (var4.item.isNewitem()) {
                                var0.drawRect(leftMargin + 5, nw + var3 * wItem + 5, wItem - 3, wItem - 10);
                            }

                            SmallImage.paintImage(var0, var4.item.template.iconID, leftMargin + wItem / 2, nw + var3 * wItem + wItem / 2, 0, 3, var4.item.indexFrame);
                            if (var4.item.quantity > 1) {
                                mFont.number_yellow.writeText(var0, String.valueOf(var4.item.quantity), leftMargin + wItem, nw + var3 * wItem + wItem / 2 + 2, 1);
                            }

                            var5 = var4.c - (var5 - var4.d);
                            mFont.tahoma_7_white.writeText(var0, mResources.qo + ": " + var4.e, leftMargin + wItem + 7, nw + var3 * wItem + wItem / 2 - mFont.number_yellow.getHeight() - 2, 0);
                            mFont.tahoma_7_white.writeText(var0, mResources.bx + ": " + NinjaUtil.a(String.valueOf(var4.b)) + " " + mResources.ke, leftMargin + wItem + 7, nw + var3 * wItem + wItem / 2 - mFont.number_yellow.getHeight() + 9, 0);
                            if (var5 < 60) {
                                mFont.tahoma_7_blue.writeText(var0, mResources.qq, leftMargin + var1 - 30, nw + var3 * wItem + wItem / 2 - mFont.number_yellow.getHeight() - 2, 2);
                                mFont.tahoma_7_blue.writeText(var0, mResources.qr, leftMargin + var1 - 30, nw + var3 * wItem + wItem / 2 - mFont.number_yellow.getHeight() + 9, 2);
                            } else {
                                mFont.tahoma_7_green.writeText(var0, mResources.qp, leftMargin + var1 - 30, nw + var3 * wItem + wItem / 2 - mFont.number_yellow.getHeight() - 2, 2);
                                mFont.tahoma_7_green.writeText(var0, NinjaUtil.b(var5), leftMargin + var1 - 30, nw + var3 * wItem + wItem / 2 - mFont.number_yellow.getHeight() + 9, 2);
                            }
                        }
                    }
                }
            } else {
                totalRowSetting = var2.length;
                mFont.tahoma_7_white.writeText(var0, mResources.nt, popupX + var1 / 2, popupY + 40, 2);
            }

            m(var0);
        }

    }

    private static void ak(mGraphics var0) {
        if (cf || gy) {
            String var1 = cf ? mResources.ik[0] : mResources.ik[1];
            MyVector var2 = cf ? aa : ad;
            Paint.a(popupX, popupY, ew, ex, var0);
            drawTitle(var0, var1, false);
            if (var2.size() <= 0) {
                mFont.tahoma_7_white.writeText(var0, cf ? mResources.il : mResources.ja, popupX + ew / 2, popupY + 40, 2);
            } else {
                leftMargin = popupX + 5;
                nw = popupY + 40;
                var0.setColor(6425);
                var0.fillRect(leftMargin - 2, nw - 2, ew - 6, wItem * 5 + 8);
                resetCursor(var0);
                scrMain.a(var2.size(), wItem, leftMargin, nw, ew - 3, wItem * 5 + 4, true, 1);
                scrMain.a(var0, leftMargin, nw, ew - 3, wItem * 5 + 6);
                totalRowSetting = var2.size();
                int var6 = 0;
                int var3 = 0;

                while (true) {
                    if (var3 >= var2.size()) {
                        totalRowSetting = var6;
                        scrMain.a(var6, wItem, leftMargin, nw, ew - 3, wItem * 5 + 4, true, 1);
                        break;
                    }

                    Friend var4 = (Friend) var2.elementAt(var3);
                    if (!gq || var4.b == 3) {
                        mFont var5 = mFont.tahoma_7_grey;
                        if (var4.b != 1 && var4.b != 2) {
                            if (var4.b == 3) {
                                var5 = mFont.tahoma_7_white;
                            } else if (var4.b == 4) {
                                var5 = mFont.tahoma_7_red;
                            }
                        } else {
                            var5 = mFont.tahoma_7_green;
                        }

                        if (var3 * wItem >= scrMain.b - wItem && var3 * wItem < scrMain.b + wItem * 5 + 8) {
                            if (indexRow == var3) {
                                var0.setColor(Paint.b);
                                var0.fillRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                                var0.setColor(16777215);
                                var0.drawRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                            } else {
                                var0.setColor(Paint.a);
                                var0.fillRect(leftMargin + 2, nw + var3 * wItem + 2, ew - 15, wItem - 4);
                                var0.setColor(13932896);
                                var0.drawRect(leftMargin + 2, nw + var3 * wItem + 2, ew - 15, wItem - 4);
                            }

                            if (var4.b == 4) {
                                if (GameCanvas.gameTick % 10 > 7) {
                                    var5.writeText(var0, var4.a, leftMargin + 8, nw + var3 * wItem + wItem / 2 - 6, 0);
                                } else {
                                    mFont.tahoma_7_yellow.writeText(var0, var4.a, leftMargin + 8, nw + var3 * wItem + wItem / 2 - 6, 0);
                                }

                                mFont.tahoma_7_blue.writeText(var0, mResources.im, leftMargin + ew - 15, nw + var3 * wItem + wItem / 2 - 6, 1);
                            } else {
                                var5.writeText(var0, var4.a, leftMargin + 8, nw + var3 * wItem + wItem / 2 - 6, 0);
                            }
                        }

                        ++var6;
                    }

                    ++var3;
                }
            }

            m(var0);
        }

    }

    private void al(mGraphics var1) {
        if (gu) {
            totalRowSetting = 0;
            resetCursor(var1);
            Paint.a(popupX, popupY, ew, ex, var1);
            drawTitle(var1, mResources.ib[indexMenu], false);
            leftMargin = popupX + 10;
            nw = popupY + 32;
            int var2;
            String var3;
            if (indexMenu == 0) {
                boolean var8 = false;
                scrMain.a(totalRowSetting, 12, popupX, popupY + 32, ew, ex - 40, true, 1);
                scrMain.a(var1);
                int var5;
                if (Char.getMyChar().taskMaint != null) {
                    for (var2 = 0; var2 < Char.getMyChar().taskMaint.c.length; ++var2) {
                        mFont.tahoma_7b_white.writeText(var1, Char.getMyChar().taskMaint.c[var2], leftMargin, this.od = nw, 0);
                        ++totalRowSetting;
                    }

                    var2 = 0;

                    for (var5 = 0; var5 < Char.getMyChar().taskMaint.e.length; ++var5) {
                        mFont var10;
                        if (Char.getMyChar().taskMaint.e[var5] != null) {
                            var2 = var5;
                            var3 = "- " + Char.getMyChar().taskMaint.e[var5];
                            if (Char.getMyChar().taskMaint.b[var5] != -1) {
                                if (Char.getMyChar().taskMaint.a == var5) {
                                    var3 = var3 + " " + Char.getMyChar().taskMaint.f + "/" + Char.getMyChar().taskMaint.b[var5];
                                    if (Char.getMyChar().taskMaint.f == Char.getMyChar().taskMaint.b[var5]) {
                                        mFont.tahoma_7_white.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
                                    } else {
                                        var10 = mFont.tahoma_7_grey;
                                        if (!var8) {
                                            var8 = true;
                                            var10 = mFont.tahoma_7_yellow;
                                        }

                                        var10.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
                                    }
                                } else if (Char.getMyChar().taskMaint.a > var5) {
                                    var3 = var3 + " " + Char.getMyChar().taskMaint.b[var5] + "/" + Char.getMyChar().taskMaint.b[var5];
                                    mFont.tahoma_7_white.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
                                } else {
                                    var3 = var3 + " 0/" + Char.getMyChar().taskMaint.b[var5];
                                    var10 = mFont.tahoma_7_grey;
                                    if (!var8) {
                                        var8 = true;
                                        var10 = mFont.tahoma_7_yellow;
                                    }

                                    var10.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
                                }
                            } else if (Char.getMyChar().taskMaint.a > var5) {
                                mFont.tahoma_7_white.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
                            } else {
                                var10 = mFont.tahoma_7_grey;
                                if (!var8) {
                                    var8 = true;
                                    var10 = mFont.tahoma_7_yellow;
                                }

                                var10.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
                            }

                            ++totalRowSetting;
                        } else if (Char.getMyChar().taskMaint.a <= var5) {
                            var3 = "- " + Char.getMyChar().taskMaint.e[var2];
                            var10 = mFont.tahoma_7_grey;
                            if (!var8) {
                                var8 = true;
                                var10 = mFont.tahoma_7_yellow;
                            }

                            var10.writeText(var1, var3, leftMargin + 5, this.od, 0);
                        }
                    }

                    this.od += 5;

                    for (var5 = 0; var5 < Char.getMyChar().taskMaint.d.length; ++var5) {
                        mFont.tahoma_7_white.writeText(var1, Char.getMyChar().taskMaint.d[var5], leftMargin, this.od += 12, 0);
                        ++totalRowSetting;
                    }
                } else {
                    byte var7 = ad();
                    var5 = ae();
                    String var6;
                    if (var7 != -3 && var5 != -3) {
                        if (Char.getMyChar().taskMaint == null && Char.getMyChar().ctaskId == 9 && Char.getMyChar().nClass.classId == 0) {
                            var6 = mResources.nk;
                        } else {
                            if (var5 < 0 || var7 < 0) {
                                return;
                            }

                            var6 = mResources.ni[0] + Npc.arrNpcTemplate[var5].name + mResources.ni[1] + TileMap.mapNames[var7] + mResources.ni[2];
                        }
                    } else {
                        var6 = mResources.ni[3];
                    }

                    String[] var9 = mFont.tahoma_7_white.b(var6, 150);

                    for (var2 = 0; var2 < var9.length; ++var2) {
                        if (var2 == 0) {
                            mFont.tahoma_7_white.writeText(var1, var9[var2], leftMargin + 5, this.od = nw, 0);
                        } else {
                            mFont.tahoma_7_white.writeText(var1, var9[var2], leftMargin + 5, this.od += 12, 0);
                        }

                        ++totalRowSetting;
                    }
                }

                if (gk == 1 && indexRow >= 0 && totalRowSetting > 0) {
                    SmallImage.paintImage(var1, 942, leftMargin - 8, nw + 2 + indexRow * 12, 0, StaticObj.b);
                }

                scrMain.a(totalRowSetting, 12, popupX, popupY + 32, ew, ex - 44, true, 1);
                return;
            }

            if (indexMenu == 1) {
                this.od = nw - 12;
                scrMain.a(Char.getMyChar().taskOrders.size(), 12, popupX, popupY + 32, ew, ex - 44, true, 1);
                scrMain.a(var1);
                totalRowSetting = 0;

                for (var2 = 0; var2 < Char.getMyChar().taskOrders.size(); ++var2) {
                    TaskOrder var4 = (TaskOrder) Char.getMyChar().taskOrders.elementAt(var2);
                    mFont.tahoma_7b_white.writeText(var1, var4.name, leftMargin + 5, this.od += 12, 0);
                    var3 = "";
                    if (var4.taskId != 0 && var4.taskId != 3) {
                        if (var4.taskId == 1) {
                            var3 = mResources.hi + " " + Mob.mobTemplates[var4.killId].name;
                        } else if (var4.taskId == 2) {
                            var3 = mResources.qu;
                        } else if (var4.taskId == 4) {
                            var3 = mResources.hj + " " + Mob.mobTemplates[var4.killId].name;
                        } else if (var4.taskId == 5) {
                            var3 = mResources.hk + " " + Mob.mobTemplates[var4.killId].name;
                        } else if (var4.taskId == 6) {
                            var3 = mResources.hl;
                        }
                    } else {
                        var3 = mResources.hh + " " + Mob.mobTemplates[var4.killId].name;
                    }

                    if (var4.taskId == 6) {
                        if (var4.count == var4.maxCount) {
                            mFont.tahoma_7_white.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
                        } else {
                            mFont.tahoma_7_yellow.writeText(var1, var3, leftMargin + 5, this.od += 12, 0);
                        }
                    } else if (var4.count == var4.maxCount) {
                        mFont.tahoma_7_white.writeText(var1, var3 + " " + var4.count + "/" + var4.maxCount, leftMargin + 5, this.od += 12, 0);
                    } else {
                        mFont.tahoma_7_yellow.writeText(var1, var3 + " " + var4.count + "/" + var4.maxCount, leftMargin + 5, this.od += 12, 0);
                    }

                    totalRowSetting += 3;
                    oj = ew - 25;
                    this.a(var1, mFont.tahoma_7_white, var4.description, leftMargin + 5, this.od += 12, 0);
                    this.od += 12;
                }

                if (this.ee > 0) {
                    mFont.tahoma_7_white.writeText(var1, mResources.i, leftMargin + 5, this.od += 12, 0);
                    switch (this.ee) {
                        case 1:
                            mFont.tahoma_7_white.writeText(var1, mResources.j, leftMargin + 5, this.od += 12, 0);
                            break;
                        case 2:
                            mFont.tahoma_7_white.writeText(var1, mResources.k, leftMargin + 5, this.od += 12, 0);
                            break;
                        case 3:
                            mFont.tahoma_7_white.writeText(var1, mResources.l, leftMargin + 5, this.od += 12, 0);
                            break;
                        case 4:
                            mFont.tahoma_7_white.writeText(var1, mResources.m, leftMargin + 5, this.od += 12, 0);
                            break;
                        case 5:
                            mFont.tahoma_7_white.writeText(var1, mResources.n, leftMargin + 5, this.od += 12, 0);
                            break;
                        case 6:
                            mFont.tahoma_7_white.writeText(var1, mResources.o, leftMargin + 5, this.od += 12, 0);
                            break;
                        case 7:
                            mFont.tahoma_7_white.writeText(var1, mResources.p, leftMargin + 5, this.od += 12, 0);
                            break;
                        case 8:
                            mFont.tahoma_7_white.writeText(var1, mResources.q, leftMargin + 5, this.od += 12, 0);
                    }
                }

                if (gk == 1 && indexRow >= 0 && totalRowSetting > 0) {
                    SmallImage.paintImage(var1, 942, leftMargin - 8, nw + 2 + indexRow * 12, 0, StaticObj.b);
                }

                ++totalRowSetting;
                scrMain.a(totalRowSetting, 12, popupX, popupY + 32, ew, ex - 44, true, 1);
            }
        }

    }

    private static String[] a(mFont var0, String var1) {
        return var0.b(var1, ew - 20);
    }

    private void a(mGraphics var1, mFont var2, String[] var3, int var4, int var5) {
        int var6 = var5;

        for (int var7 = 0; var7 < var3.length; ++var7) {
            String var8;
            if ((var8 = var3[var7]).startsWith("c")) {
                if (var8.startsWith("c0")) {
                    var8 = var8.substring(2);
                    var2 = mFont.tahoma_7_white;
                } else if (var8.startsWith("c1")) {
                    var8 = var8.substring(2);
                    var2 = mFont.tahoma_7_yellow;
                } else if (var8.startsWith("c2")) {
                    var8 = var8.substring(2);
                    var2 = mFont.tahoma_7_green;
                }
            }

            if (var7 == 0) {
                var2.writeText(var1, var8, var4, var5, 0);
            } else {
                if (var7 * scrMain.h + var6 >= scrMain.b - 12 && var7 * scrMain.h < scrMain.b + ex - 44) {
                    var5 += 12;
                    var2.writeText(var1, var8, var4, var5, 0);
                } else {
                    var5 += 12;
                }

                this.od += 12;
                ++totalRowSetting;
            }
        }

    }

    private void a(mGraphics var1, mFont var2, String var3, int var4, int var5, int var6) {
        int var7 = GameCanvas.isTouch && GameCanvas.width >= 320 ? 20 : 10;
        int var8 = var5;
        String[] var10 = var2.b(var3, oj - var7);

        for (var7 = 0; var7 < var10.length; ++var7) {
            if (var7 == 0) {
                var2.writeText(var1, var10[var7], var4, var5, var6);
            } else {
                if (var7 * scrMain.h + var8 >= scrMain.b - 12 && var7 * scrMain.h < scrMain.b + ex - 44) {
                    String var9 = var10[var7];
                    var5 += 12;
                    var2.writeText(var1, var9, var4, var5, var6);
                    this.od += 12;
                } else {
                    var5 += 12;
                }

                ++totalRowSetting;
            }
        }

    }

    private void b(mGraphics var1, mFont var2, String var3, int var4, int var5, int var6) {
        int var7 = var5;
        String[] var9 = var2.b(var3, var6);

        for (var6 = 0; var6 < var9.length; ++var6) {
            if (var6 == 0) {
                var2.writeText(var1, var9[var6], var4, var5, 0);
            } else {
                if (var6 * scrMain.h + var7 >= scrMain.b - 12 && var6 * scrMain.h < scrMain.b + ex - 44) {
                    String var8 = var9[var6];
                    var5 += 12;
                    var2.writeText(var1, var8, var4, var5, 0);
                    this.od += 12;
                } else {
                    var5 += 12;
                }

                ++totalRowSetting;
            }
        }

    }

    private void writeItemInfo(mGraphics _mGraphic) {
        if (GameCanvas.isMinWidth320 && !ci() && (ch() || cf() || z())) {
            ct();
            an(_mGraphic);
            resetCursor(_mGraphic);
            this.a(_mGraphic, mFont.tahoma_7_white, mResources.chonVPDeXemInfo, oh + oj / 2, oi + ok / 2 - 20, 2);
        }

        if (cg && this.currentItem != null && this.currentItem.template != null) {
            Item _item = this.currentItem;
            if (gp && !this.currentItem.isUpMax() && indexMenu == 0) {
                _item = this.currentItem.viewNext(this.currentItem.upgrade + 1);
            }

            if (hw && indexMenu == 0 && gk == 1 && _item.isTypeBody() && _item.upgrade == 0 && arrItemConvert[0] != null && arrItemConvert[0].template.type == arrItemConvert[1].template.type && arrItemConvert[1].template.level >= arrItemConvert[0].template.level) {
                _item = this.currentItem.viewNext(arrItemConvert[0].upgrade);
            }

            resetCursor(_mGraphic);
            int var3;
            int var4;
            if (_item.expires != 0L && _item.options != null && _item.options.size() > 0) {
                for (var4 = 0; var4 < _item.options.size(); ++var4) {
                    if ((var3 = ((ItemOption) _item.options.elementAt(var4)).getOptionString().length() * 5) > oj && !GameCanvas.isMinWidth320) {
                        oj = var3;
                    }
                }
            }

            if ((var3 = mFont.tahoma_7b_white.a(_item.template.name) + 10) > oj && !GameCanvas.isMinWidth320) {
                oj = var3;
            }

            if (oj > GameCanvas.width - 4) {
                oj = GameCanvas.width - 4;
            }

            if (ok > GameCanvas.height - 4) {
                oj = GameCanvas.height - 4;
            }

            oh = gW / 2 - oj / 2;
            oi = gH / 2 - ok / 2;
            ct();
            if (oh < 2) {
                oh = 2;
            }

            if (oi < 2) {
                oi = 2;
            }

            an(_mGraphic);
            if (id && indexMenu == 0) {
                if (Char.clan != null) {
                    this.od = oi - 9;
                    totalRowSetting = 2;
                    gn.a(_mGraphic, oh, oi + 2, oj, ok - 2);
                    oj = mFont.tahoma_7_white.a(mResources.rd[Char.clan.openDun][1]) + 10;

                    for (var4 = 0; var4 < 2; ++var4) {
                        mFont.tahoma_7_white.writeText(_mGraphic, mResources.rd[Char.clan.openDun][var4], oh + 8, this.od += 12, 0);
                    }

                    if (indexRow >= 0 && (!GameCanvas.isTouch || GameCanvas.isTouch && GameCanvas.width < 320)) {
                        SmallImage.paintImage(_mGraphic, 942, oh + 1, oi + 5 + indexRow * 12, 0, StaticObj.b);
                    }

                    gn.a(totalRowSetting, 12, oh, oi + 2, oj, ok - 4, true, 1);
                    return;
                }
            } else {
                gn.a(_mGraphic, oh, oi + 2, oj, ok - 2);
                totalRowSetting = 3;
                this.od = oi + 3;
                mFont var9 = mFont.tahoma_7b_white;
                if (!_item.isTypeMounts()) {
                    if (_item.upgrade > 0 && _item.upgrade < 4) {
                        var9 = mFont.tahoma_7b_blue;
                    } else if (_item.upgrade >= 4 && _item.upgrade < 8) {
                        var9 = mFont.tahoma_7b_green;
                    } else if (_item.upgrade >= 8 && _item.upgrade < 12) {
                        var9 = mFont.tahoma_7b_yellow;
                    } else if (_item.upgrade >= 12 && _item.upgrade < 15) {
                        var9 = mFont.tahoma_7b_purple;
                    } else if (_item.upgrade >= 15) {
                        var9 = mFont.tahoma_7b_red;
                    }
                }

                if (_item.img != null) {
                    _mGraphic.drawRegion(_item.img, 0, 0, mGraphics.getWidth(_item.img), mGraphics.getHeight(_item.img), 0, oh + oj / 2, this.od + ok - 10, 33);
                }

                if (_item.isTypeMounts()) {
                    this.a(_mGraphic, var9, _item.template.name, oh + 8, this.od, 0);
                } else {
                    this.a(_mGraphic, var9, _item.template.name + (_item.upgrade > 0 ? " +" + _item.upgrade : ""), oh + 8, this.od, 0);
                }

                if (_item.upgrade >= 15 && !gs && !_item.isTypeMounts()) {
                    if (var9.b(_item.template.name + (_item.upgrade > 0 ? " +" + _item.upgrade : ""), oj - (GameCanvas.isTouch && GameCanvas.width >= 320 ? 20 : 10)).length > 1) {
                        this.od -= 12;
                    }

                    if (_item.isTypeMounts()) {
                        this.a(_mGraphic, mFont.tahoma_7b_white, _item.template.name, oh + 8, this.od, 0);
                    } else {
                        this.a(_mGraphic, mFont.tahoma_7b_white, _item.template.name + (_item.upgrade > 0 ? " +" + _item.upgrade : ""), oh + 8, this.od, 0);
                    }
                }
                String indexUIAndID = "IndexUI: " + _item.indexUI + " - ID: [" + _item.template.id + "]";
                mFont.tahoma_7_blue1.writeText(_mGraphic, indexUIAndID, oh + 8, this.od += 12, 0, mFont.tahoma_7);
                if (!_item.isTypeBody()) {
                    if (_item.isTypeMounts() && Char.getMyChar().arrItemMounts[4] != null) {
                        this.od += 12;
                        var3 = Char.getMyChar().arrItemMounts[4].sys + 1;

                        for (var4 = 0; var4 < var3; ++var4) {
                            SmallImage.paintImage(_mGraphic, 633, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                        }
                    }
                } else {
                    this.od += 12;
                    ++totalRowSetting;
                    if (gs && GameCanvas.gameTick % 5 == 0) {
                        gs = !gs;
                    } else if (!gs && GameCanvas.gameTick % 5 == 0) {
                        gs = !gs;
                    }

                    var3 = _item.upgrade / 2 + 1;
                    if (_item.upgrade == 0) {
                        for (var4 = 0; var4 < var3; ++var4) {
                            SmallImage.paintImage(_mGraphic, 633, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                        }
                    } else if (_item.upgrade > 0 && _item.upgrade < 4) {
                        for (var4 = 0; var4 < var3; ++var4) {
                            SmallImage.paintImage(_mGraphic, 625, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                        }

                        if (_item.upgrade == 3) {
                            SmallImage.paintImage(_mGraphic, 635, oh + 12 + var3 * 10, this.od + 5, 0, StaticObj.g);
                        }
                    } else if (_item.upgrade >= 4 && _item.upgrade < 8) {
                        for (var4 = 0; var4 < var3; ++var4) {
                            SmallImage.paintImage(_mGraphic, 626, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                        }

                        if (_item.upgrade % 2 != 0) {
                            SmallImage.paintImage(_mGraphic, 636, oh + 12 + var3 * 10, this.od + 5, 0, StaticObj.g);
                        }
                    } else if (_item.upgrade >= 8 && _item.upgrade < 12) {
                        for (var4 = 0; var4 < var3; ++var4) {
                            if (gs) {
                                SmallImage.paintImage(_mGraphic, 627, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                            } else {
                                SmallImage.paintImage(_mGraphic, 628, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                            }
                        }

                        if (_item.upgrade % 2 != 0) {
                            if (gs) {
                                SmallImage.paintImage(_mGraphic, 637, oh + 12 + var3 * 10, this.od + 5, 0, StaticObj.g);
                            } else {
                                SmallImage.paintImage(_mGraphic, 638, oh + 12 + var3 * 10, this.od + 5, 0, StaticObj.g);
                            }
                        }
                    } else if (_item.upgrade >= 12 && _item.upgrade < 15) {
                        for (var4 = 0; var4 < var3; ++var4) {
                            if (gs) {
                                SmallImage.paintImage(_mGraphic, 629, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                            } else {
                                SmallImage.paintImage(_mGraphic, 630, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                            }
                        }

                        if (_item.upgrade % 2 != 0) {
                            if (gs) {
                                SmallImage.paintImage(_mGraphic, 639, oh + 12 + var3 * 10, this.od + 5, 0, StaticObj.g);
                            } else {
                                SmallImage.paintImage(_mGraphic, 640, oh + 12 + var3 * 10, this.od + 5, 0, StaticObj.g);
                            }
                        }
                    } else {
                        for (var4 = 0; var4 < var3; ++var4) {
                            if (gs) {
                                SmallImage.paintImage(_mGraphic, 631, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                            } else {
                                SmallImage.paintImage(_mGraphic, 632, oh + 12 + var4 * 10, this.od + 5, 0, StaticObj.g);
                            }
                        }

                        if (_item.upgrade % 2 != 0) {
                            if (gs) {
                                SmallImage.paintImage(_mGraphic, 641, oh + 12 + var3 * 10, this.od + 5, 0, StaticObj.g);
                            } else {
                                SmallImage.paintImage(_mGraphic, 642, oh + 12 + var3 * 10, this.od + 5, 0, StaticObj.g);
                            }
                        }
                    }
                }

                mFont.tahoma_7_white.writeText(_mGraphic, _item.isLock ? mResources.daKhoa : mResources.khongKhoa, oh + 8, this.od += 12, 0);
                String var10;
                if ((_item.isTypeBody() || _item.isTypeMounts()) && (var10 = _item.template.type == 12 ? mResources.eu : (_item.template.level >= 10 && _item.template.type < 10 ? (_item.upgrade == 0 ? mResources.es : null) : mResources.et)) != null) {
                    this.a(_mGraphic, mFont.tahoma_7_white, var10, oh + 8, this.od += 12, 0);
                    ++totalRowSetting;
                }

                if (_item.template.gender == 0 || _item.template.gender == 1) {
                    if (_item.template.gender == Char.getMyChar().cgender) {
                        mFont.tahoma_7_white.writeText(_mGraphic, mResources.typeGioiTinh[_item.template.gender], oh + 8, this.od += 12, 0);
                        ++totalRowSetting;
                    } else {
                        mFont.tahoma_7_red.writeText(_mGraphic, mResources.typeGioiTinh[_item.template.gender], oh + 8, this.od += 12, 0);
                        ++totalRowSetting;
                    }
                }

                if (Char.getMyChar().cLevel != -1) {
                    if (Char.getMyChar().cLevel >= _item.template.level) {
                        this.a(_mGraphic, mFont.tahoma_7_white, mResources.hz + " " + _item.template.level, oh + 8, this.od += 12, 0);
                    } else {
                        this.a(_mGraphic, mFont.tahoma_7_red, mResources.hz + " " + _item.template.level, oh + 8, this.od += 12, 0);
                    }
                }

                if ((_item.template.id < 1186 || _item.template.id > 1198) && _item.template.id != 1161) {
                    if ((_item.template.id < 40 || _item.template.id > 48) && _item.template.id != 311 && _item.template.id != 375 && _item.template.id != 397 && _item.template.id != 552 && _item.template.id != 558 && _item.template.id != 844) {
                        if ((_item.template.id < 49 || _item.template.id > 57) && _item.template.id != 312 && _item.template.id != 376 && _item.template.id != 398 && _item.template.id != 553 && _item.template.id != 559 && _item.template.id != 841) {
                            if ((_item.template.id < 58 || _item.template.id > 66) && _item.template.id != 313 && _item.template.id != 377 && _item.template.id != 399 && _item.template.id != 554 && _item.template.id != 560 && _item.template.id != 842) {
                                if ((_item.template.id < 67 || _item.template.id > 75) && _item.template.id != 314 && _item.template.id != 378 && _item.template.id != 400 && _item.template.id != 555 && _item.template.id != 561 && _item.template.id != 839) {
                                    if ((_item.template.id < 76 || _item.template.id > 84) && _item.template.id != 315 && _item.template.id != 379 && _item.template.id != 401 && _item.template.id != 556 && _item.template.id != 562 && _item.template.id != 843) {
                                        if (_item.template.id >= 85 && _item.template.id <= 93 || _item.template.id == 316 || _item.template.id == 380 || _item.template.id == 402 || _item.template.id == 557 || _item.template.id == 563 && _item.template.id == 840) {
                                            if (Char.getMyChar().nClass.classId == 6) {
                                                mFont.tahoma_7_white.writeText(_mGraphic, mResources.ia + " " + nClass[6].nameClass, oh + 8, this.od += 12, 0);
                                            } else {
                                                mFont.tahoma_7_red.writeText(_mGraphic, mResources.ia + " " + nClass[6].nameClass, oh + 8, this.od += 12, 0);
                                            }

                                            ++totalRowSetting;
                                        }
                                    } else {
                                        if (Char.getMyChar().nClass.classId == 5) {
                                            mFont.tahoma_7_white.writeText(_mGraphic, mResources.ia + " " + nClass[5].nameClass, oh + 8, this.od += 12, 0);
                                        } else {
                                            mFont.tahoma_7_red.writeText(_mGraphic, mResources.ia + " " + nClass[5].nameClass, oh + 8, this.od += 12, 0);
                                        }

                                        ++totalRowSetting;
                                    }
                                } else {
                                    if (Char.getMyChar().nClass.classId == 4) {
                                        mFont.tahoma_7_white.writeText(_mGraphic, mResources.ia + " " + nClass[4].nameClass, oh + 8, this.od += 12, 0);
                                    } else {
                                        mFont.tahoma_7_red.writeText(_mGraphic, mResources.ia + " " + nClass[4].nameClass, oh + 8, this.od += 12, 0);
                                    }

                                    ++totalRowSetting;
                                }
                            } else {
                                if (Char.getMyChar().nClass.classId == 3) {
                                    mFont.tahoma_7_white.writeText(_mGraphic, mResources.ia + " " + nClass[3].nameClass, oh + 8, this.od += 12, 0);
                                } else {
                                    mFont.tahoma_7_red.writeText(_mGraphic, mResources.ia + " " + nClass[3].nameClass, oh + 8, this.od += 12, 0);
                                }

                                ++totalRowSetting;
                            }
                        } else {
                            if (Char.getMyChar().nClass.classId == 2) {
                                mFont.tahoma_7_white.writeText(_mGraphic, mResources.ia + " " + nClass[2].nameClass, oh + 8, this.od += 12, 0);
                            } else {
                                mFont.tahoma_7_red.writeText(_mGraphic, mResources.ia + " " + nClass[2].nameClass, oh + 8, this.od += 12, 0);
                            }

                            ++totalRowSetting;
                        }
                    } else {
                        if (Char.getMyChar().nClass.classId == 1) {
                            mFont.tahoma_7_white.writeText(_mGraphic, mResources.ia + " " + nClass[1].nameClass, oh + 8, this.od += 12, 0);
                        } else {
                            mFont.tahoma_7_red.writeText(_mGraphic, mResources.ia + " " + nClass[1].nameClass, oh + 8, this.od += 12, 0);
                        }

                        ++totalRowSetting;
                    }
                } else {
                    if (Char.getMyChar().nClass.classId == 7) {
                        mFont.tahoma_7_white.writeText(_mGraphic, mResources.ia + " " + nClass[7].nameClass, oh + 8, this.od += 12, 0);
                    } else {
                        mFont.tahoma_7_red.writeText(_mGraphic, mResources.ia + " " + nClass[7].nameClass, oh + 8, this.od += 12, 0);
                    }

                    ++totalRowSetting;
                }

                if (!_item.isTypeMounts()) {
                    if (_item.template.id == 420) {
                        if (Char.getMyChar().nClass.classId != 1 && Char.getMyChar().nClass.classId != 2 && Char.getMyChar().nClass.classId != 7) {
                            mFont.tahoma_7_red.writeText(_mGraphic, mResources.kp[1], oh + 8, this.od += 12, 0);
                        } else {
                            mFont.tahoma_7_white.writeText(_mGraphic, mResources.kp[1], oh + 8, this.od += 12, 0);
                        }

                        ++totalRowSetting;
                    } else if (_item.template.id == 421) {
                        if (Char.getMyChar().nClass.classId != 3 && Char.getMyChar().nClass.classId != 4) {
                            mFont.tahoma_7_red.writeText(_mGraphic, mResources.kp[2], oh + 8, this.od += 12, 0);
                        } else {
                            mFont.tahoma_7_white.writeText(_mGraphic, mResources.kp[2], oh + 8, this.od += 12, 0);
                        }

                        ++totalRowSetting;
                    } else if (_item.template.id == 422) {
                        if (Char.getMyChar().nClass.classId != 5 && Char.getMyChar().nClass.classId != 6) {
                            mFont.tahoma_7_red.writeText(_mGraphic, mResources.kp[3], oh + 8, this.od += 12, 0);
                        } else {
                            mFont.tahoma_7_white.writeText(_mGraphic, mResources.kp[3], oh + 8, this.od += 12, 0);
                        }

                        ++totalRowSetting;
                    }
                }

                if (_item.expires > 0L) {
                    if (!_item.isTypeUIShop() && !_item.isTypeUIShopLock() && !_item.l() && !_item.m() && !_item.n() && !_item.o() && _item.typeUI != 39) {
                        if ((var3 = mFont.tahoma_7.a(mResources.hn + ": " + _item.getExpiresString()) + 10) > oj && !GameCanvas.isMinWidth320) {
                            oj = var3;
                        }

                        this.a(_mGraphic, mFont.tahoma_7_yellow, mResources.hn + ": " + _item.getExpiresString(), oh + 8, this.od += 12, 0);
                    } else {
                        if ((var3 = mFont.tahoma_7.a(mResources.hn + ": " + _item.getExpiresShopString()) + 10) > oj && !GameCanvas.isMinWidth320) {
                            oj = var3;
                        }

                        this.a(_mGraphic, mFont.tahoma_7_yellow, mResources.hn + ": " + _item.getExpiresShopString(), oh + 8, this.od += 12, 0);
                    }

                    ++totalRowSetting;
                }

                if (!_item.template.description.equals("")) {
                    this.a(_mGraphic, mFont.tahoma_7_white, _item.template.description, oh + 8, this.od += 12, 0);
                    ++totalRowSetting;
                }

                if (!_item.isTypeUIMe() && _item.typeUI != 37) {
                    if (_item.isTypeUIShop() || _item.isTypeUIShopLock() || _item.l() || _item.m() || _item.n() || _item.o()) {
                        if (_item.buyCoin > 0) {
                            if (_item.o()) {
                                if ((var3 = mFont.tahoma_7.a(mResources.a(mResources.hq, NinjaUtil.a(String.valueOf(_item.buyCoin)))) + 10) > oj && !GameCanvas.isMinWidth320) {
                                    oj = var3;
                                }

                                mFont.tahoma_7_yellow.writeText(_mGraphic, mResources.a(mResources.hq, NinjaUtil.a(String.valueOf(_item.buyCoin))), oh + 8, this.od += 12, 0);
                            } else {
                                mFont.tahoma_7_yellow.writeText(_mGraphic, mResources.a(mResources.hp, NinjaUtil.a(String.valueOf(_item.buyCoin))), oh + 8, this.od += 12, 0);
                            }

                            ++totalRowSetting;
                        } else if (_item.buyCoinLock > 0) {
                            mFont.tahoma_7_yellow.writeText(_mGraphic, mResources.a(mResources.hr, NinjaUtil.a(String.valueOf(_item.buyCoinLock))), oh + 8, this.od += 12, 0);
                            ++totalRowSetting;
                        } else if (_item.buyGold > 0) {
                            mFont.tahoma_7_yellow.writeText(_mGraphic, mResources.a(mResources.hs, NinjaUtil.a(String.valueOf(_item.buyGold))), oh + 8, this.od += 12, 0);
                            ++totalRowSetting;
                        }
                    }
                } else {
                    mFont.tahoma_7_yellow.writeText(_mGraphic, mResources.a(mResources.ho, NinjaUtil.a(String.valueOf(_item.saleCoinLock))), oh + 8, this.od += 12, 0);
                    ++totalRowSetting;
                }

                if (_item.template.type == 33) {
                    mFont.tahoma_7_yellow.writeText(_mGraphic, mResources.cap1 + ": " + (_item.upgrade + 1), oh + 8, this.od += 12, 0);
                    ++totalRowSetting;
                }

                if (_item.isTypeBody() && _item.sys != 0) {
                    mFont.tahoma_7_blue1.writeText(_mGraphic, mResources.kp[_item.sys], oh + 8, this.od += 12, 0);
                    ++totalRowSetting;
                }

                if (_item.expires != 0L && _item.options != null && _item.options.size() > 0) {
                    boolean var11 = false;
                    boolean var5 = false;

                    for (int var6 = 0; var6 < _item.options.size(); ++var6) {
                        ItemOption _itemOption = (ItemOption) _item.options.elementAt(var6);
                        if (!var11 && _itemOption.optionTemplate.type == 2) {
                            var11 = true;
                            String stringKichAn = mResources.kichAn[0] + ": ";
                            if (_item.template.type == 1) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[_item.template.type] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 0) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[6] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[5] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 6) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[0] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[5] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 5) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[0] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[6] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 2) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[8] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[7] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 8) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[2] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[7] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 7) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[2] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[8] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 4) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[3] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[9] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 3) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[4] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[9] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            } else if (_item.template.type == 9) {
                                stringKichAn = stringKichAn + mResources.arrTypeBody[4] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + "), " + mResources.arrTypeBody[3] + "(" + mResources.arrThuocTinh[this.currentItem.sys] + ")";
                            }

                            if ((var3 = mFont.tahoma_7_white.a(stringKichAn) + 15) > oj && !GameCanvas.isMinWidth320) {
                                oj = var3;
                            }

                            this.a(_mGraphic, mFont.tahoma_7_white, stringKichAn, oh + 8, this.od += 12, 0);
                            ++totalRowSetting;
                        }

                        if (!var5 && _itemOption.optionTemplate.type > 2 && _itemOption.optionTemplate.type < 8) {
                            var5 = true;
                            mFont.tahoma_7_white.writeText(_mGraphic, mResources.kichAn[1], oh + 8, this.od += 12, 0);
                            ++totalRowSetting;
                        }
                        if (_itemOption.optionTemplate.id == 65) {
                            this.a(_mGraphic, mFont.tahoma_7_blue, _item.template.id == 485 ? NinjaUtil.replace(_itemOption.getOptionString(), mResources.kinhNghiem, mResources.dongCo) : _itemOption.getOptionString(), oh + 8, this.od += 12, 0);
                        } else if (_itemOption.optionTemplate.id == 66) {
                            this.a(_mGraphic, mFont.tahoma_7_blue1, _item.template.id == 485 ? NinjaUtil.replace(_itemOption.getOptionString(), mResources.sinhLuc, mResources.nhienLieu) : _itemOption.getOptionString(), oh + 8, this.od += 12, 0);
                        } else if (_itemOption.optionTemplate.type == 0) {
                            this.a(_mGraphic, mFont.tahoma_7_blue1, _item.i() ? _itemOption.getOptionShopString() : _itemOption.getOptionString(), oh + 8, this.od += 12, 0);
                        } else if (_itemOption.optionTemplate.type == 1) {
                            this.a(_mGraphic, mFont.tahoma_7_green, _item.i() ? _itemOption.getOptionShopString() : _itemOption.getOptionString(), oh + 8, this.od += 12, 0);
                        } else {
                            label963:
                            {
                                mGraphics var10001;
                                mFont var10002;
                                String var10003;
                                GameScr var10000;
                                if (_itemOption.optionTemplate.type == 8) {
                                    if (_itemOption.optionTemplate.id == 85) {
                                        this.a(_mGraphic, mFont.tahoma_7_yellow, _item.i() ? _itemOption.getOptionShopString() : NinjaUtil.replace(_itemOption.optionTemplate.name, "#", String.valueOf(_itemOption.param)), oh + 8, this.od += 12, 0);
                                        break label963;
                                    }

                                    var10000 = this;
                                    var10001 = _mGraphic;
                                    var10002 = mFont.tahoma_7b_blue;
                                    var10003 = _itemOption.getOptionShopString();
                                } else {
                                    if ((_itemOption.optionTemplate.type != 2 || _item.typeUI != 5 || _itemOption.active != 1) && (_itemOption.optionTemplate.type != 3 || _item.upgrade < 4) && (_itemOption.optionTemplate.type != 4 || _item.upgrade < 8) && (_itemOption.optionTemplate.type != 5 || _item.upgrade < 12) && (_itemOption.optionTemplate.type != 6 || _item.upgrade < 14) && (_itemOption.optionTemplate.type != 7 || _item.upgrade < 16)) {
                                        this.a(_mGraphic, mFont.tahoma_7_grey, _item.i() ? _itemOption.getOptionShopString() : _itemOption.getOptionString(), oh + 8, this.od += 12, 0);
                                        break label963;
                                    }

                                    var10000 = this;
                                    var10001 = _mGraphic;
                                    var10002 = mFont.tahoma_7_green;
                                    var10003 = _item.i() ? _itemOption.getOptionShopString() : _itemOption.getOptionString();
                                }

                                var10000.a(var10001, var10002, var10003, oh + 8, this.od += 12, 0);
                            }
                        }

                        ++totalRowSetting;
                    }
                }

                if (ifa) {
                    if (_item.template.id == 12) {
                        mFont.tahoma_7_red.writeText(_mGraphic, NinjaUtil.a(this.ef[indexSelect]) + " " + mResources.kf, oh + 8, this.od += 12, 0);
                    }

                    if (_item.template.type >= 0 && _item.template.type <= 9) {
                        mFont.tahoma_7_yellow.writeText(_mGraphic, mResources.qy, oh + 8, this.od += 12, 0);
                    }

                    ++totalRowSetting;
                }

                if (indexRow >= 0 && (!GameCanvas.isTouch || GameCanvas.isTouch && GameCanvas.width < 320)) {
                    SmallImage.paintImage(_mGraphic, 942, oh + 1, oi + 5 + indexRow * 12, 0, StaticObj.b);
                }

                gn.a(totalRowSetting, 12, oh, oi + 2, oj, ok - 4, true, 1);
            }
        }

    }

    private static void an(mGraphics var0) {
        resetCursor(var0);
        var0.setColor(0);
        var0.fillRect(oh - 2, oi - 2, oj + 5, ok + 5);
        var0.setColor(13606712);
        var0.drawRect(oh - 1, oi - 1, oj + 2, ok + 2);
        var0.setColor(Paint.a);
        var0.fillRect(oh, oi, oj, ok);
    }

    private static void ct() {
        if (GameCanvas.isMinWidth320 && (!id || indexMenu != 0)) {
            oh = popupX + 175;
            oj = ew - 179;
            oi = popupY + 33;
            ok = 138;
            if (ci && indexMenu == 0) {
                oh = popupX + 6 + 3 * wItem;
                oj = ew - (11 + 6 * wItem);
            }

            if (isPaintInfoMe) {
                if (indexMenu == 4) {
                    oh = popupX + 33;
                    oi = popupY + 87;
                    oj = ew - 67;
                    ok = 75;
                    return;
                }

                if (indexMenu == 5) {
                    ok = 161;
                }
            }
        }

    }

    public final void showButtonIndexMenu() {
        super.center = null;
        if (gk == 0 && (indexMenu == 1 || indexMenu == 3)) {
            super.left = null;
        } else {
            switch (indexMenu) {
                case 0:
                    if (gk != 1) {
                        break;
                    }

                    if (getCurrentItemSelectByTypeUI(3) == null) {
                        cg = false;
                        super.left = this.om;
                    } else {
                        super.left = this.or;
                        if (GameCanvas.isTouch && GameCanvas.width < 320 || !GameCanvas.isTouch) {
                            super.center = this.oq;
                        }
                    }
                    break;
                case 1:
                    if (gk != 1) {
                        break;
                    }

                    super.left = null;
                    if (indexSelect < 0) {
                        break;
                    }

                    SkillTemplate var3 = Char.getMyChar().nClass.skillTemplates[indexSelect];
                    Skill var2;
                    if ((var2 = Char.getMyChar().a(var3)) == null) {
                        break;
                    }

                    if (var2.point < var3.maxPoint) {
                        super.left = new Command(mResources.de, 14001);
                    }

                    if (var2.template.type != 1 && var2.template.type != 4 && var2.template.type != 2 && var2.template.type != 3) {
                        super.center = null;
                        break;
                    }

                    super.center = new Command(mResources.dy, 11081);
                    break;
                case 2:
                    if (gk > 0) {
                        super.left = new Command(mResources.de, 11084);
                        super.center = new Command("", 11084);
                    }
                    break;
                case 3:
                    super.left = null;
                    super.center = new Command(mResources.fh, 110854);
                    break;
                case 4:
                    if (gk == 0) {
                        super.left = showFashion ? new Command(mResources.trangBi1, 110822) : new Command(mResources.trangBi2, 110823);
                        break;
                    }
                    if (gk == 1) {
                        super.left = null;
                        Item var1 = getCurrentItemSelectByTypeUI(5);
                        if (var1 != null) {
                            if (currentCharViewInfo.charID == Char.getMyChar().charID) {
                                super.left = new Command(mResources.chon, 11082);
                                if (GameCanvas.isMinWidth320) {
                                    this.getItemInfo((int) 5, (Item) var1);
                                } else {
                                    super.center = new Command(mResources.xem, 11083);
                                }
                            } else if (GameCanvas.isMinWidth320) {
                                this.getItemInfo((int) 5, (Item) var1);
                            } else {
                                super.center = new Command(mResources.xem, 11083);
                                super.left = new Command(mResources.chon, 11082);
                            }
                        } else {
                            cg = false;
                            super.left = new Command(mResources.chon, 11082);
                        }
                    }
                    break;
                case 5:
                    super.left = null;
                    if (gk == 1 && indexSelect >= 0 && currentCharViewInfo.arrItemMounts[indexSelect] != null) {
                        if (Char.getMyChar().charID == currentCharViewInfo.charID) {
                            super.left = new Command(mResources.boRa, 1516);
                        }

                        super.center = new Command(GameCanvas.isMinWidth320 ? "" : mResources.xem, 1515);
                    }
                    break;
                case 6:
                    super.left = null;
                    if (gk == 1 && indexSelect >= 0 && currentCharViewInfo.arrItemBijuus[indexSelect] != null) {
                        if (Char.getMyChar().charID == currentCharViewInfo.charID) {
                            super.left = new Command(mResources.boRa, 508);
                        }
                        super.center = new Command(GameCanvas.isMinWidth320 ? "" : mResources.xem, 503);
                    }
                    break;
            }

            if (currentCharViewInfo.charID == Char.getMyChar().charID) {
                super.right = new Command(mResources.el, 11086);
            } else {
                super.right = this.mw;
            }
        }

    }

    public final void openUI(int typeUI) {
        gk = 0;
        super.right = this.mw;
        switch (typeUI) {
            case 2:
                indexMenu = 0;
                hq = true;
                if (arrItemWeapon == null) {
                    Service.getInstance().requestItem(2);
                }
            case 3:
            case 5:
            case 30:
            case 39:
            case 41:
            case 42:
            case 51:
            default:
                break;
            case 4:
                indexMenu = 0;
                showBox = true;
                if (Char.getMyChar().arrItemBox == null) {
                    Service.getInstance().requestItem(4);
                }
                break;
            case 6:
                indexMenu = 0;
                hr = true;
                if (arrItemStack == null) {
                    Service.getInstance().requestItem(6);
                }
                break;
            case 7:
                indexMenu = 0;
                hs = true;
                if (arrItemStackLock == null) {
                    Service.getInstance().requestItem(7);
                }
                break;
            case 8:
                indexMenu = 0;
                ht = true;
                if (arrItemGrocery == null) {
                    Service.getInstance().requestItem(8);
                }
                break;
            case 9:
                indexMenu = 0;
                hu = true;
                if (arrItemGroceryLock == null) {
                    Service.getInstance().requestItem(9);
                }
                break;
            case 10:
                indexMenu = 0;
                hv = true;
                arrItemUpGrade = new Item[18];
                break;
            case 11:
                indexMenu = 0;
                hy = true;
                go = true;
                arrItemUpPeal = new Item[24];
                break;
            case 12:
                indexMenu = 0;
                hy = true;
                go = false;
                arrItemUpPeal = new Item[24];
                break;
            case 13:
                indexMenu = 0;
                ia = true;
                arrItemSplit = new Item[24];
                break;
            case 14:
                ha = true;
                indexMenu = 0;
                if (arrItemStore == null) {
                    Service.getInstance().requestItem(14);
                }
                break;
            case 15:
                ha = true;
                indexMenu = 1;
                if (arrItemBook == null) {
                    Service.getInstance().requestItem(15);
                }
                break;
            case 16:
                indexMenu = 0;
                hm = true;
                if (arrItemLien == null) {
                    Service.getInstance().requestItem(16);
                }
                break;
            case 17:
                indexMenu = 0;
                hn = true;
                if (arrItemNhan == null) {
                    Service.getInstance().requestItem(17);
                }
                break;
            case 18:
                indexMenu = 0;
                ho = true;
                if (arrItemNgocBoi == null) {
                    Service.getInstance().requestItem(18);
                }
                break;
            case 19:
                indexMenu = 0;
                hp = true;
                if (arrItemPhu == null) {
                    Service.getInstance().requestItem(19);
                }
                break;
            case 20:
                indexMenu = 0;
                hc = true;
                if (arrItemNonNam == null) {
                    Service.getInstance().requestItem(20);
                }
                break;
            case 21:
                indexMenu = 0;
                hd = true;
                if (arrItemNonNu == null) {
                    Service.getInstance().requestItem(21);
                }
                break;
            case 22:
                indexMenu = 0;
                he = true;
                if (arrItemAoNam == null) {
                    Service.getInstance().requestItem(22);
                }
                break;
            case 23:
                indexMenu = 0;
                hf = true;
                if (arrItemAoNu == null) {
                    Service.getInstance().requestItem(23);
                }
                break;
            case 24:
                indexMenu = 0;
                hg = true;
                if (arrItemGangTayNam == null) {
                    Service.getInstance().requestItem(24);
                }
                break;
            case 25:
                indexMenu = 0;
                hh = true;
                if (arrItemGangTayNu == null) {
                    Service.getInstance().requestItem(25);
                }
                break;
            case 26:
                indexMenu = 0;
                hi = true;
                if (arrItemQuanNam == null) {
                    Service.getInstance().requestItem(26);
                }
                break;
            case 27:
                indexMenu = 0;
                hj = true;
                if (arrItemQuanNu == null) {
                    Service.getInstance().requestItem(27);
                }
                break;
            case 28:
                indexMenu = 0;
                hk = true;
                if (arrItemGiayNam == null) {
                    Service.getInstance().requestItem(28);
                }
                break;
            case 29:
                indexMenu = 0;
                hl = true;
                if (arrItemGiayNu == null) {
                    Service.getInstance().requestItem(29);
                }
                break;
            case 31:
                indexMenu = 0;
                hv = true;
                hx = true;
                arrItemUpGrade = new Item[18];
                break;
            case 32:
                ha = true;
                indexMenu = 2;
                if (arrItemFashion == null) {
                    Service.getInstance().requestItem(32);
                }
                break;
            case 33:
                indexMenu = 0;
                hw = true;
                arrItemConvert = new Item[3];
                break;
            case 34:
                ha = true;
                indexMenu = 3;
                if (arrItemClanShop == null) {
                    Service.getInstance().requestItem(34);
                }
                break;
            case 35:
                hb = true;
                indexMenu = 0;
                if (arrItemElites == null) {
                    Service.getInstance().requestItem(35);
                }
                break;
            case 36:
                indexMenu = 0;
                cd = true;
                itemSell = null;
                this.iz = new TField();
                this.iz.setMaxTextLenght(9);
                this.iz.c(1);
                this.iz.width = 100;
                this.iz.height = mScreen.fo + 2;
                break;
            case 37:
                ig = true;
                this.ab();
                break;
            case 38:
                arrItemSprin = null;
                ifa = true;
                gk = 1;
                this.ab();
                break;
            case 40:
                showAutoPanel = true;
                gk = 1;
                indexRow = 0;
                this.ab();
                break;
            case 43:
                indexMenu = 0;
                ih = true;
                arrItemUpPeal = new Item[24];
                break;
            case 44:
                indexMenu = 0;
                ii = true;
                arrItemSplit = new Item[24];
                break;
            case 45:
                indexMenu = 0;
                cl = true;
                arrItemSplit = new Item[24];
                break;
            case 46:
                indexMenu = 0;
                ij = true;
                arrItemSplit = new Item[24];
                break;
            case 47:
                indexMenu = 0;
                ik = true;
                arrItemUpGrade = new Item[18];
                break;
            case 48:
                indexMenu = 0;
                io = true;
                arrItemSplit = new Item[18];
                break;
            case 49:
                indexMenu = 0;
                il = true;
                itemSplit = null;
                break;
            case 50:
                indexMenu = 0;
                im = true;
                itemSplit = null;
        }

        b(175, 200);
    }

    public static Char getCharByID(int idChar) {
        for (int i = 0; i < vCharInMap.size(); ++i) {
            Char p = (Char) vCharInMap.elementAt(i);
            if (p.charID == idChar) {
                return p;
            }
        }

        return null;
    }

    public static Char getCharByName(String name) {
        for (int i = 0; i < vCharInMap.size(); ++i) {
            Char p = (Char) vCharInMap.elementAt(i);
            if (p.charName.equals(name)) {
                return p;
            }
        }

        return null;
    }

    public static BuNhin f(int var0) {
        return ai.size() > 0 ? (BuNhin) ai.elementAt(var0) : null;
    }

    public final void a(String var1, String var2) {
        if (!ck || GameCanvas.isTouch) {
            ChatTextField.a().isShow = false;
        }

        if (!var1.equals("")) {
            String quickCommand = var1.trim().toLowerCase();
            if (AutoChat.isCommand(quickCommand)) {
                Code.instance.f(quickCommand);
                return;
            }
            if (quickCommand.equals("ttgt") || quickCommand.equals("ttgt0") || quickCommand.equals("ttgt1") || quickCommand.equals("ttgt2")
                    || quickCommand.equals("ttgt 0") || quickCommand.equals("ttgt 1") || quickCommand.equals("ttgt 2")) {
                Code.instance.f(quickCommand);
                return;
            }

            if (var2.equals(mResources.on[0])) {
                if (!ChatCommandExtend.a.a(var1)) {
                    Service.getInstance().c(var1);
                    return;
                }
            } else {
                if (var2.equals(mResources.oo[0])) {
                    if (vParty.size() == 0) {
                        ChatManager.getInstance().e().a(mResources.mk);
                        return;
                    }

                    Service.getInstance().k(var1);
                    return;
                }

                if (var2.equals(mResources.op[0])) {
                    Service.getInstance().l(var1);
                    return;
                }

                if (var2.equals(mResources.oq[0])) {
                    if (var1.toLowerCase().equals("ttgt")) {
                        Code.instance.f(var1);
                        return;
                    }

                    if (Char.getMyChar().cClanName.equals("")) {
                        ChatManager.getInstance().e().a(mResources.mp);
                        return;
                    }

                    Service.getInstance().m(var1);
                    return;
                }

                ChatManager.getInstance().chat(var2, Char.getMyChar().charName, var1);
                Service.getInstance().a(var2, var1);
            }
        }

    }

    public final void aj() {
        if (ck) {
            this.closeDialog();
            ck = false;
            ChatTextField.a().f = null;
        }

    }

    private void a(int var1, int var2, int var3, int var4, int var5) {
        if (hy || ih || ii || cl || ia || ci || hv || hw || cd || ij || ik || il || im) {
            int var6 = var3 * wItem;
            var4 *= wItem;
            scrMain.a();
            if (GameCanvas.b(var1, var2, var6, var4)) {
                gk = var5;
                if (GameCanvas.isPointerClick) {
                    if ((var1 = (GameCanvas.r - var1) / wItem + (GameCanvas.s - var2) / wItem * var3) / ob < oc) {
                        indexSelect = var1;
                    }

                    super.left = super.center = null;
                    if (ci) {
                        if (indexSelect < 0) {
                            indexSelect = 11;
                        }

                        if (indexSelect > 11) {
                            indexSelect = 11;
                        }
                    }

                    boolean var10000 = GameCanvas.isPointerJustRelease;
                    this.ab();
                }
            }
        }

    }

    public final void b(int commandID, Object obj) {
        String var14;
        Item item1;
        Code var10000;
        Member member;
        int var18;
        MyVector var22;
        MyVector var23;
        Npc var25;
        Skill var27;
        Object[] var29;
        MobTemplate var20;
        Npc var24;
        switch (commandID) {
            case 1:
                GameCanvas.setMaxTextLenght();
                return;
            case 2:
                GameCanvas.setMaxTextLenght();
                super.left = super.center = null;
                this.ab();
                return;
            case 3:
                this.closeDialog();
                return;
            case 222:
                this.hk();
                return;
            case 333:
                this.openUI((int) 47);
                return;
            case 334:
                this.ei();
                return;
            case 335:
                gp = false;
                this.getItemInfo((int) 3, (Item) itemSplit);
                return;
            case 336:
                gp = false;
                this.getItemInfo((int) 3, (Item) itemUpGrade);
                return;
            case 337:
                this.hl();
                return;
            case 338:
                this.r(0);
                return;
            case 339:
                this.s(0);
                return;
            case 340:
                this.s(1);
                return;
            case 341:
                hm();
                return;
            case 342:
                hn();
                return;
            case 343:
                this.s(2);
                return;
            case 344:
                this.r(1);
                return;
            case 345:
                this.r(2);
                return;
            case 400:
                this.hp();
                return;
            case 401:
                this.hq();
                return;
            case 402:
                this.hr();
                return;
            case 403:
                hs();
                return;
            case 405:
                ho();
                return;
            case 503:
                this.getItemInfo((int) 51, (Item) currentCharViewInfo.arrItemBijuus[indexSelect]);
                return;
            case 508:
                Service.getInstance().doRemoveVithu(indexSelect);
                return;
            case 999:
                this.openUI((int) 35);
                return;
            case 1000:
                Service.getInstance().af();
                this.resetButton();
                return;
            case 1500:
                (var23 = new MyVector()).addElement(new Command(mResources.boRa, 15001));
                if (Char.getMyChar().xu >= 5000) {
                    var23.addElement(new Command(mResources.bu, 15002));
                }

                GameCanvas.menu.showMenu(var23);
                return;
            case 1501:
                this.getItemInfo((int) 3, (Item) itemSell);
                return;
            case 1502:
                this.iz.a();
                return;
            case 1503:
                dc();
                return;
            case 1504:
                da();
                return;
            case 1505:
                this.getItemInfo((int) 3, (Item) arrItemStands[indexSelect].item);
                return;
            case 1506:
                if (arrItemSprin != null) {
                    this.ed = 0;
                    cg = false;
                    an = -1;
                    arrItemSprin = null;
                    getInstance().left = new Command(mResources.chon, 1506);
                    return;
                }

                an = indexSelect;
                Service.getInstance().ah();
                GameCanvas.showWaiting();
                return;
            case 1507:
                this.cx();
                return;
            case 1508:
                var23 = new MyVector();
                if ((item1 = Char.clan.items[indexSelect]) != null) {
                    if (item1.template.id == 281) {
                        var23.addElement(new Command(mResources.suDung, 15081));
                    } else {
                        var23.addElement(new Command(mResources.phatChoTV, 15082));
                    }

                    GameCanvas.menu.showMenu(var23);
                    return;
                }
                break;
            case 1509:
                if (indexSelect >= 0 && Char.clan != null) {
                    this.getItemInfo((int) 39, (Item) Char.clan.items[indexSelect]);
                    return;
                }

                cg = false;
                return;
            case 1510:
                cw();
                return;
            case 1511:
                var14 = GameCanvas.ak.tfInput.getText();
                GameCanvas.setMaxTextLenght();

                if (var14.equals("")) {
                    GameCanvas.setText(mResources.qe);
                    return;
                }

                if ((commandID = Integer.parseInt(var14)) > 0 && commandID < 100) {
                    Char.aHpValue = commandID;
                    return;
                }

                GameCanvas.setText(mResources.qe);
                return;
            case 1512:
                var14 = GameCanvas.ak.tfInput.getText();
                GameCanvas.setMaxTextLenght();

                try {
                    if (var14.equals("")) {
                        GameCanvas.setText(mResources.qe);
                        return;
                    }

                    if ((commandID = Integer.parseInt(var14)) >= 10 && commandID <= 90) {
                        Char.aMpValue = commandID;
                        return;
                    }

                    GameCanvas.setText(mResources.qe);
                    return;
                } catch (Exception var28) {
                    GameCanvas.setText(mResources.qe);
                    return;
                }
            case 1513:
                var14 = GameCanvas.ak.tfInput.getText();
                GameCanvas.setMaxTextLenght();

                try {
                    if (var14.equals("")) {
                        GameCanvas.setText(mResources.qe);
                        return;
                    }

                    if ((commandID = Integer.parseInt(var14)) >= 0) {
                        Char.idTTL = commandID;
                        return;
                    }

                    GameCanvas.setText(mResources.qe);
                    return;
                } catch (Exception var28) {
                    GameCanvas.setText(mResources.qe);
                    return;
                }
            case 1514:
                var14 = GameCanvas.ak.tfInput.getText();
                GameCanvas.setMaxTextLenght();

                try {
                    if (var14.equals("")) {
                        GameCanvas.setText(mResources.qe);
                        return;
                    }

                    if ((commandID = Integer.parseInt(var14)) >= 0) {
                        Char.indexBuyTTL = commandID;
                        return;
                    }

                    GameCanvas.setText(mResources.qe);
                    return;
                } catch (Exception var28) {
                    GameCanvas.setText(mResources.qe);
                    return;
                }
            case 1515:
                this.getItemInfo((int) 41, (Item) currentCharViewInfo.arrItemMounts[indexSelect]);
                return;
            case 1516:
                Service.getInstance().itemMonToBag(indexSelect);
                return;
            case 1517:
                var14 = GameCanvas.ak.tfInput.getText();
                GameCanvas.setMaxTextLenght();

                try {
                    if (var14.equals("")) {
                        GameCanvas.setText(mResources.qe);
                        return;
                    }

                    if ((commandID = Integer.parseInt(var14)) >= 0) {
                        Code.thoiGianChoChuyenKhu = commandID;
                        return;
                    }

                    GameCanvas.setText(mResources.qe);
                    return;
                } catch (Exception var28) {
                    GameCanvas.setText(mResources.qe);
                    return;
                }
            case 1518:
                var14 = GameCanvas.ak.tfInput.getText();
                GameCanvas.setMaxTextLenght();

                try {
                    if (var14.equals("")) {
                        GameCanvas.setText(mResources.qe);
                        return;
                    }

                    if ((commandID = Integer.parseInt(var14)) >= 3) {
                        Code.speedTinhLuyen = commandID;
                        return;
                    }

                    GameCanvas.setText(mResources.qe);
                    return;
                } catch (Exception var28) {
                    GameCanvas.setText(mResources.qe);
                    return;
                }
            case 1600:
                cq();
                return;
            case 1601:
                ee();
                return;
            case 1602:
                item1 = getCurrentItemSelectByTypeUI(43);
                this.getItemInfo((int) 3, (Item) item1);
                return;
            case 1603:
                this.eb();
                return;
            case 1604:
                var23 = new MyVector();
                if (arrItemSplit[indexSelect] != null) {
                    var23.addElement(new Command(mResources.boRa, 1605));
                }

                var23.addElement(new Command(mResources.fc, 11105));
                GameCanvas.menu.showMenu(var23);
                return;
            case 1605:
                this.cv();
                return;
            case 1606:
                this.cu();
                return;
            case 1700:
                this.hg();
                return;
            case 1701:
                hh();
                return;
            case 1702:
                this.hi();
                return;
            case 2000:
                Service.getInstance().rewardCT();
                this.resetButton();
                return;
            case 11000:
                bf();
                return;
            case 11001:
                Char.getMyChar().x();
                return;
            case 11002:
                he();
                return;
            case 11003:
                hd();
                return;
            case 11004:
                this.xemInfoItemTypeUI((byte) 25);
                return;
            case 11005:
                hb();
                return;
            case 11006:
                hc();
                return;
            case 11007:
                this.xemInfoItemTypeUI((byte) 24);
                return;
            case 11008:
                ha();
                return;
            case 11009:
                this.xemInfoItemTypeUI((byte) 23);
                return;
            case 11010:
                gz();
                return;
            case 11011:
                this.xemInfoItemTypeUI((byte) 22);
                return;
            case 11012:
                gy();
                return;
            case 11013:
                this.xemInfoItemTypeUI((byte) 21);
                return;
            case 11014:
                gx();
                return;
            case 11015:
                this.xemInfoItemTypeUI((byte) 20);
                return;
            case 11016:
                gw();
                return;
            case 11017:
                this.xemInfoItemTypeUI((byte) 15);
                return;
            case 11018:
                gu();
                return;
            case 11019:
                this.xemInfoItemTypeUI((byte) 14);
                return;
            case 11020:
                gt();
                return;
            case 11021:
                svTitle = "";
                svAction = "";
                this.resetButton();
                return;
            case 11022:
                this.extendMenuUseItem();
                return;
            case 11023:
                this.gq();
                return;
            case 11024:
                gp();
                return;
            case 11025:
                this.ax();
                return;
            case 11026:
                gm();
                return;
            case 11027:
                this.gl();
                return;
            case 11028:
                this.gk();
                return;
            case 11029:
                this.gj();
                return;
            case 11030:
                this.gi();
                return;
            case 11032:
                this.gh();
                return;
            case 11033:
                this.gg();
                return;
            case 11034:
                this.ge();
                return;
            case 11035:
                this.gd();
                return;
            case 11036:
                ck();
                return;
            case 11037:
                this.gc();
                return;
            case 11038:
                ga();
                return;
            case 11040:
                this.fx();
                return;
            case 11041:
                this.fw();
                return;
            case 11042:
                ft();
                return;
            case 11043:
                fr();
                return;
            case 11044:
                di();
                return;
            case 11045:
                doa();
                return;
            case 11046:
                dn();
                return;
            case 11047:
                dk();
                return;
            case 11048:
                fn();
                return;
            case 11049:
                cj();
                return;
            case 11050:
                cl();
                return;
            case 11051:
                fm();
                return;
            case 11052:
                item1 = (Item) obj;
                Service.getInstance().useItemChangeMap(item1.indexUI, GameCanvas.menu.menuSelectedItem);
                return;
            case 11053:
                g((Item) obj);
                return;
            case 11054:
                this.fg();
                return;
            case 11055:
                f((Item) obj);
                return;
            case 11057:
                var25 = (Npc) obj;
                Service.getInstance().getTask(var25.template.npcTemplateId, GameCanvas.menu.menuSelectedItem);
                return;
            case 11058:
                item1 = (Item) obj;
                GameCanvas.setMaxTextLenght();
                Service.getInstance().saleItem(item1.indexUI, Integer.parseInt(GameCanvas.ak.tfInput.getText()));
                return;
            case 11059:
                this.ff();
                return;
            case 11060:
                this.fe();
                return;
            case 11061:
                d((Item) obj);
                return;
            case 11062:
                cp();
                return;
            case 11063:
                fd();
                return;
            case 11064:
                Service.getInstance().e(gk - 1, 1);
                this.showButtonIndexMenu();
                return;
            case 11065:
                this.ay();
                return;
            case 11066:
                this.closeDialog();
                ck = false;
                cc = false;
                ChatTextField.a().f = null;
                return;
            case 11067:
                if (TileMap.zoneID != indexSelect) {
                    Service.getInstance().requestChangeZone(indexSelect, this.iv);
                    InfoDlg.b();
                    return;
                }

                InfoMe.a(mResources.nh);
                return;
            case 11068:
                var14 = (String) obj;
                this.h(var14);
                return;
            case 11069:
                this.fc();
                return;
            case 11070:
                a((Party) obj);
                return;
            case 11071:
                Service.getInstance().t();
                return;
            case 11072:
                fb();
                return;
            case 11073:
                b(Char.getMyChar().arrItemBag[indexSelect]);
                return;
            case 11074:
                short var21 = Short.parseShort(String.valueOf((var23 = (MyVector) obj).elementAt(0)));
                var14 = String.valueOf(var23.elementAt(1));
                this.a(var21, var14);
                return;
            case 11075:
                this.fa();
                return;
            case 11076:
                var14 = (String) obj;
                Service.getInstance().addParty(var14);
                return;
            case 11077:
                g((String) obj);
                return;
            case 11078:
                ez();
                return;
            case 11079:
                ex();
                return;
            case 11080:
                e((String) obj);
                return;
            case 11081:
                ev();
                return;
            case 11082:
                if (showFashion) {
                    leftCmdFashion();
                } else {
                    leftCmdEquipment();
                }
                return;
            case 11083:
                this.xemInfoItemTypeUI((byte) 5);
                return;
            case 11084:
                et();
                return;
            case 11085:
                es();
                return;
            case 11086:
                this.er();
                return;
            case 11087:
                item1 = (Item) obj;
                GameCanvas.setMaxTextLenght();
                Service.getInstance().splitItem(item1);
                return;
            case 11088:
                this.xemInfoItemTypeUI((byte) 26);
                return;
            case 11089:
                this.xemInfoItemTypeUI((byte) 27);
                return;
            case 11090:
                this.xemInfoItemTypeUI((byte) 28);
                return;
            case 11091:
                this.xemInfoItemTypeUI((byte) 29);
                return;
            case 11092:
                c((Item) obj);
                return;
            case 11093:
                this.xemInfoItemTypeUI((byte) 2);
                return;
            case 11094:
                this.xemInfoItemTypeUI((byte) 6);
                return;
            case 11095:
                this.xemInfoItemTypeUI((byte) 7);
                return;
            case 11096:
                this.xemInfoItemTypeUI((byte) 8);
                return;
            case 11097:
                this.xemInfoItemTypeUI((byte) 9);
                return;
            case 11098:
                this.ep();
                return;
            case 11099:
                gp = false;
                this.getItemInfo((int) 3, (Item) itemUpGrade);
                return;
            case 11100:
                en();
                return;
            case 11101:
                item1 = getCurrentItemSelectByTypeUI(10);
                this.getItemInfo((int) 3, (Item) item1);
                return;
            case 11102:
                this.ei();
                return;
            case 11103:
                this.eh();
                return;
            case 11104:
                this.getItemInfo((int) 3, (Item) ((Item) obj));
                return;
            case 11105:
                cs();
                return;
            case 11106:
                this.eg();
                return;
            case 11107:
                ef();
                return;
            case 11108:
                this.xemInfoItemTypeUI((byte) 3);
                return;
            case 11109:
                this.ec();
                return;
            case 11110:
                item1 = arrItemTradeOrder[indexSelect];
                this.getItemInfo((int) 30, (Item) item1);
                return;
            case 11111:
                item1 = getCurrentItemSelectByTypeUI(4);
                this.getItemInfo((int) 4, (Item) item1);
                return;
            case 11112:
                Service.getInstance().boxSort();
                return;
            case 11113:
                Service.getInstance().e(Char.getMyChar().arrItemBag[indexSelect].indexUI);
                return;
            case 11114:
                this.getItemInfo((int) 3, (Item) Char.getMyChar().arrItemBag[indexSelect]);
                return;
            case 11115:
                ea();
                return;
            case 11116:
                dz();
                return;
            case 11120:
                var27 = (Skill) (var29 = (Object[]) ((Object[]) obj))[0];
                commandID = Integer.parseInt((String) var29[1]);
                arrSkill[commandID] = var27;
                aq();
                return;
            case 11121:
                var27 = (Skill) (var29 = (Object[]) ((Object[]) obj))[0];
                commandID = Integer.parseInt((String) var29[1]);
                keySkill[commandID] = var27;
                ar();
                return;
            case 12000:
                this.fl();
                return;
            case 12001:
                ChatManager.getInstance().a(((Integer) obj).intValue());
                this.fj();
                return;
            case 12002:
            case 12004:
                var14 = (String) obj;
                ChatTab var26;
                if ((var26 = ChatManager.getInstance().a(var14)) == null) {
                    ChatManager.getInstance().b(var14);
                    ChatManager.getInstance().c();
                } else {
                    ChatManager.getInstance().a(var26);
                }

                this.fj();
                gw = false;
                id = false;
                gy = false;
                cf = false;
                gv = false;
                ChatTextField.a().f = null;
                return;
            case 12003:
                this.bk();
                return;
            case 12005:
                fk();
                return;
            case 12006:
                fh();
                return;
            case 12007:
                this.ot = 1;
                this.ou = "";
                this.df();
                return;
            case 12008:
                de();
                return;
            case 12009:
                var14 = (String) obj;
                (var23 = new MyVector()).addElement(new Command(mResources.menuOtherChar[7], 12002, var14));
                var23.addElement(new Command(mResources.oc[2], 110803, var14));
                if (gv) {
                    var23.addElement(new Command(mResources.menuOtherChar[6], 110804));
                }

                if (ck) {
                    var23.addElement(new Command(mResources.ah, 14020, var14));
                    var23.addElement(new Command(mResources.menuOtherChar[6], 1108041, var14));
                }

                GameCanvas.menu.showMenu(var23);
                return;
            case 13001:
                this.xemInfoItemTypeUI((byte) 32);
                return;
            case 13002:
                gv();
                return;
            case 14001:
                ew();
                return;
            case 14002:
                this.dv();
                return;
            case 14003:
                this.dw();
                return;
            case 14004:
                dm();
                return;
            case 14005:
                showMenuMemberClan();
                return;
            case 14006:
                d((String) obj);
                return;
            case 14007:
                dg();
                return;
            case 14008:
                GameCanvas.a(mResources.kk, new Command(mResources.bn, 140081), new Command(mResources.ca, 1));
                return;
            case 14009:
                dp();
                return;
            case 14010:
                GameCanvas.ak.a(mResources.pn, new Command(mResources.di, GameCanvas.instance, 88833, (Object) null), 1);
                return;
            case 14011:
                cc = false;
                return;
            case 14012:
                this.ej();
                return;
            case 14013:
                this.eo();
                return;
            case 14014:
                this.gf();
                return;
            case 14015:
                this.el();
                return;
            case 14016:
                this.getItemInfo((int) 3, (Item) arrItemConvert[indexSelect]);
                return;
            case 14017:
                dh();
                return;
            case 14018:
                this.xemInfoItemTypeUI((byte) 34);
                return;
            case 14019:
                dd();
                return;
            case 14020:
                if (ds()) {
                    var14 = (String) obj;
                    Service.getInstance().setXuCuocLoiDai((short) 1, var14);
                    return;
                }

                GameCanvas.setText(mResources.pa);
                return;
            case 14021:
                ey();
                return;
            case 14022:
                gs();
                return;
            case 14023:
                this.xemInfoItemTypeUI((byte) 35);
                return;
            case 14024:
                this.a((byte) 1);
                return;
            case 14025:
                this.a((byte) 0);
                return;
            case 15001:
                this.db();
                return;
            case 15002:
                if ((commandID = Integer.parseInt(this.iz.getText())) <= 0) {
                    GameCanvas.setText(mResources.qe);
                }
                GameCanvas.a(mResources.a(mResources.qm, NinjaUtil.a(String.valueOf(commandID))), new Command(mResources.bn, 150021), new Command(mResources.ca, 1));
                return;
            case 15041:
                cz();
                return;
            case 15042:
                GameCanvas.a(mResources.a(mResources.qn, NinjaUtil.a(String.valueOf(arrItemStands[indexSelect].b))), new Command(mResources.bn, 150421), new Command(mResources.ca, 1));
                return;
            case 15081:
                Service.getInstance().ai();
                return;
            case 15082:
                GameCanvas.ak.a(mResources.rc, new Command(mResources.okey, GameCanvas.instance, 88843, new Integer(indexSelect)), 0);
                return;
            case 15130:
                Char.aFoodValue = 1;
                return;
            case 15131:
                Char.aFoodValue = 10;
                return;
            case 15132:
                Char.aFoodValue = 20;
                return;
            case 15133:
                Char.aFoodValue = 30;
                return;
            case 15134:
                Char.aFoodValue = 40;
                return;
            case 15135:
                Char.aFoodValue = 50;
                return;
            case 15136:
                Char.aFoodValue = 60;
                return;
            case 15137:
                Char.aFoodValue = 70;
                return;
            case 15140:
                Char.ey = 1;
                return;
            case 15141:
                Char.ey = 10;
                return;
            case 15142:
                Char.ey = 20;
                return;
            case 15143:
                Char.ey = 30;
                return;
            case 15144:
                Char.ey = 40;
                return;
            case 15145:
                Char.ey = 50;
                return;
            case 15146:
                Char.ey = 60;
                return;
            case 15147:
                Char.ey = 70;
                return;
            case 15150:
                Char.ev = 1;
                return;
            case 15151:
                Char.ev = 10;
                return;
            case 15153:
                Char.ev = 30;
                return;
            case 15155:
                Char.ev = 50;
                return;
            case 15157:
                Char.ev = 70;
                return;
            case 15161:
                Char.ew = 1;
                return;
            case 15162:
                Char.ew = 2;
                return;
            case 15163:
                Char.ew = 3;
                return;
            case 15164:
                Char.ew = 4;
                return;
            case 15165:
                Char.ew = 5;
                return;
            case 15166:
                Char.ew = 6;
                return;
            case 15167:
                Char.ew = 7;
                return;
            case 15174:
                Char.ex = 4;
                return;
            case 15175:
                Char.ex = 5;
                return;
            case 15176:
                Char.ex = 6;
                return;
            case 15177:
                Char.ex = 7;
                return;
            case 15178:
                Char.ex = 8;
                return;
            case 15179:
                Char.ex = 9;
                return;
            case 110001:
                this.bc();
                return;
            case 110002:
                bm();
                return;
            case 110003:
                be();
                return;
            case 110004:
                fz();
                return;
            case 110005:
                this.openUI((int) 14);
                return;
            case 110006:
                fv();
                return;
            case 110007:
                this.openUI((int) 7);
                return;
            case 110008:
                this.openUI((int) 6);
                return;
            case 110009:
                this.openUI((int) 9);
                return;
            case 110010:
                this.openUI((int) 8);
                return;
            case 110011:
                this.openUI((int) 10);
                return;
            case 110012:
                this.openUI((int) 11);
                return;
            case 110013:
                this.openUI((int) 12);
                return;
            case 110014:
                if ((var25 = findNpc(5)) != null && (Math.abs(var25.cx - Char.getMyChar().cx) > 22 || Math.abs(var25.cy - Char.getMyChar().cy) > 22)) {
                    Char.charMove(var25.cx, var25.cy);
                }

                this.openUI((int) 4);
                return;
            case 110015:
                this.openUI((int) 13);
                return;
            case 110016:
                if ((var25 = findNpc(13)) != null && var25.statusMe != 15) {
                    if (Math.abs(var25.cx - Char.getMyChar().cx) > 22 || Math.abs(var25.cy - Char.getMyChar().cy) > 22) {
                        Char.charMove(var25.cx, var25.cy);
                    }

                    Service.getInstance().openUIZone();
                    return;
                }

                if ((commandID = Char.getIndexItemById(37)) < 0) {
                    commandID = Char.getIndexItemById(35);
                }

                if (commandID >= 0) {
                    this.iv = commandID;
                    Service.getInstance().openUIZone();
                }

                return;
            case 110017:
                this.t();
                return;
            case 110018:
                fi();
                return;
            case 110019:
                this.dx();
                return;
            case 110020:
                var10000 = Code.instance;
                Code.n();
                return;
            case 110021:
                bd();
                return;
            case 110051:
                b((byte) 25);
                return;
            case 110052:
                c((byte) 25);
                return;
            case 110081:
                b((byte) 24);
                return;
            case 110082:
                c((byte) 24);
                return;
            case 110101:
                b((byte) 23);
                return;
            case 110102:
                c((byte) 23);
                return;
            case 110121:
                b((byte) 22);
                return;
            case 110122:
                c((byte) 22);
                return;
            case 110141:
                b((byte) 21);
                return;
            case 110142:
                c((byte) 21);
                return;
            case 110161:
                b((byte) 20);
                return;
            case 110162:
                c((byte) 20);
                return;
            case 110181:
                b((byte) 15);
                return;
            case 110182:
                c((byte) 15);
                return;
            case 110201:
                b((byte) 14);
                return;
            case 110202:
                c((byte) 14);
                return;
            case 110221:
                gb();
                return;
            case 110244:
                showInputDialogSplitItem();
                return;
            case 1102441:
                showInputDialogSplitItemAll();
                return;
            case 110261:
                useAllItem();
                return;
            case 110273:
                GameCanvas.ak.a("Delay Mở All (ms, tối thiểu 10)", new Command("OK", 110274), 1);
                GameCanvas.ak.tfInput.a(String.valueOf(UseAllItem.getDelayMs()));
                return;
            case 110274:
                if (!UseAllItem.setDelayText(GameCanvas.ak.tfInput.getText())) {
                    chatPopup("Delay Mở All không hợp lệ");
                } else {
                    chatPopup("Delay Mở All: " + UseAllItem.getDelayMs() + "ms");
                }
                GameCanvas.setMaxTextLenght();
                return;
            case 110262:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.addTuDung(item1.template.id);
                }

                return;
            case 110263:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.removeTuDung(item1.template.id);
                }

                return;
            case 11000948:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    if (AutoUseItem.contains(item1.template.id)) {
                        AutoUseItem.remove(item1.template.id);
                        chatPopup("Đã xóa khỏi list Tự Dùng");
                    } else {
                        int ruleIndex = Code.addTuDungList(item1.template.id);
                        new FormAutoUseItem(ruleIndex).select();
                    }
                }

                return;
            case 110264:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.b(item1);
                }

                return;
            case 110265:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.c(item1);
                }

                return;
            case 110266:
                GameCanvas.ak.a("Giá bán", new Command("Đặt", 110268), 1);
                GameCanvas.ak.tfInput.a("5000");
                return;
            case 110267:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.fsw(item1.template.id);
                }

                return;
            case 110268:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    var18 = 5000;

                    var18 = Integer.parseInt(GameCanvas.ak.tfInput.getText());

                    if (var18 < 5000) {
                        chatPopup("Giá > 5000k");
                    } else {
                        Code.b(item1.template.id, var18);
                    }
                }

                GameCanvas.setMaxTextLenght();
                return;
            case 110269:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.addDelItem(item1.template.id);
                }
                return;
            case 110270:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.removeDelItem(item1.template.id);
                }
                return;
            case 110271:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.addItemThrow(item1.template.id);
                }
                return;
            case 110272:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    Code.removeItemThrow(item1.template.id);
                }
                return;
            case 110361:
                this.fs();
                return;
            case 110382:
                Service.getInstance().returnTownFromDead();
                return;
            case 110383:
                Service.getInstance().l();
                return;
            case 110391:
                this.actionToOtherPlayer(1);
                return;
            case 110392:
                this.actionToOtherPlayer(2);
                return;
            case 110393:
                this.actionToOtherPlayer(3);
                return;
            case 110394:
                this.actionToOtherPlayer(4);
                return;
            case 110395:
                this.actionToOtherPlayer(5);
                return;
            case 110396:
                this.actionToOtherPlayer(6);
                return;
            case 110397:
                this.actionToOtherPlayer(7);
                return;
            case 110398:
                this.actionToOtherPlayer(8);
                return;
            case 110399:
                this.actionToOtherPlayer(9);
                return;
            case 110441:
                this.ba();
                return;
            case 110451:
                fq();
                return;
            case 110452:
                fp();
                return;
            case 110471:
                fo();
                return;
            case 110531:
                item1 = (Item) obj;
                Service.getInstance().useItemChangeMap(item1.indexUI, GameCanvas.menu.menuSelectedItem + 3);
                return;
            case 110561:
                GameCanvas.setMaxTextLenght();
                this.ab();
                return;
            case 110562:
                e((Item) obj);
                return;
            case 110701:
                Service.getInstance().t();
                return;
            case 110702:
                Service.getInstance().a(true);
                return;
            case 110703:
                Service.getInstance().a(false);
                return;
            case 110721:
                Service.getInstance().saleItem(indexSelect, 1);
                return;
            case 110722:
                b(Char.getMyChar().arrItemBag[indexSelect]);
                return;
            case 110723:
                Service.getInstance().saleItem(indexSelect, Char.getMyChar().arrItemBag[indexSelect].quantity);
                return;
            case 110771:
                var14 = (String) obj;
                GameCanvas.setMaxTextLenght();
                Service.getInstance().g(var14);
                return;
            case 110791:
                var14 = (String) obj;
                Service.getInstance().addParty(var14);
                return;
            case 110792:
                f((String) obj);
                return;
            case 110801:
                Service.getInstance().ab(indexRow);
                return;
            case 110802:
                Service.getInstance().aa(indexRow);
                return;
            case 110803:
                var14 = (String) obj;
                Service.getInstance().addFriend(var14);
                return;
            case 110804:
                dy();
                return;
            case 110805:
                dq();
                return;
            case 110811:
                b(Char.getMyChar().nClass.skillTemplates[indexSelect]);
                return;
            case 110812:
                a(Char.getMyChar().nClass.skillTemplates[indexSelect]);
                return;
            case 110821: //equip to bag
                this.center = null;
                Service.getInstance().itemBodyToBag(indexSelect + (showFashion ? 16 : 0));
                return;
            case 110822: // Trang bi 1
                showFashion = false;
                return;
            case 110823: // Trang bi 2
                showFashion = true;
                return;
            case 110841:
                Service.getInstance().e(gk - 1, 1);
                this.showButtonIndexMenu();
                return;
            case 110842:
                this.ay();
                return;
            case 110851:
                item1 = (Item) obj;
                Service.getInstance().buyItem(item1.typeUI, item1.indexUI, 1);
                return;
            case 110852:
                a((Item) obj);
                return;
            case 110854:
                eq();
                return;
            case 110921:
                item1 = (Item) obj;
                Service.getInstance().buyItem(item1.typeUI, item1.indexUI, 1);
                return;
            case 110922:
                a((Item) obj);
                return;
            case 110923:
                this.xemInfoItemTypeUI((byte) 16);
                return;
            case 110924:
                this.xemInfoItemTypeUI((byte) 17);
                return;
            case 110925:
                this.xemInfoItemTypeUI((byte) 18);
                return;
            case 110926:
                this.xemInfoItemTypeUI((byte) 19);
                return;
            case 110981:
                cr();
                return;
            case 110991:
                gp = true;
                this.getItemInfo((int) 3, (Item) itemUpGrade);
                return;
            case 111001:
                this.em();
                return;
            case 111031:
                if (ii) {
                    Service.getInstance().a(itemSplit, arrItemSplit);
                    return;
                }

                if (cl) {
                    Service.getInstance().c(itemSplit, arrItemSplit);
                    return;
                }

                if (ij) {
                    Service.getInstance().ngockham((byte) 1, (Item) null, itemSplit, arrItemSplit);
                    return;
                }

                if (il) {
                    Service.getInstance().ngockham((byte) 2, (Item) null, itemSplit, (Item[]) null);
                    return;
                }

                if (im) {
                    Service.getInstance().ngockham((byte) 3, (Item) null, itemSplit, (Item[]) null);
                    return;
                }
                break;
            case 111071:
                this.ed();
                return;
            case 111101:
                item1 = getCurrentItemSelectByTypeUI(4);
                Service.getInstance().d(item1.indexUI);
                return;
            case 120051:
                ChatTab var28 = (ChatTab) obj;
                ChatManager.getInstance().a.removeElement(var28);
                if (ChatManager.getInstance().b > ChatManager.getInstance().a.size() - 1) {
                    ChatManager.getInstance().b();
                }

                if (ChatManager.getInstance().e() != null) {
                    this.fj();
                    return;
                }

                ChatTextField.a().isShow = false;
                this.resetButton();
                return;
            case 120061:
                ChatManager.c = !ChatManager.c;
                GameCanvas.setText(mResources.or + (ChatManager.c ? mResources.av : mResources.aw));
                return;
            case 120062:
                ChatManager.d = !ChatManager.d;
                GameCanvas.setText(mResources.os + (ChatManager.d ? mResources.av : mResources.aw));
                return;
            case 120071:
                this.ot = 2;
                if (GameCanvas.al.d.getText().equals("")) {
                    GameCanvas.setText(mResources.jv);
                    return;
                }

                if (GameCanvas.al.e.getText().equals("")) {
                    GameCanvas.setText(mResources.jw);
                    return;
                }

                this.ou = "Loại thẻ: " + GameCanvas.al.d.getText();
                this.ou = this.ou + ", Mệnh giá: " + GameCanvas.al.e.getText();
                GameCanvas.setMaxTextLenght();
                this.df();
                return;
            case 120072:
                if (GameCanvas.al.d.getText().equals("")) {
                    GameCanvas.setText(mResources.jx);
                    return;
                }

                if (GameCanvas.al.e.getText().equals("")) {
                    GameCanvas.setText(mResources.jy);
                    return;
                }

                this.ou = this.ou + ", Số seri: " + GameCanvas.al.d.getText();
                this.ou = this.ou + ", Khoảng Thời gian nạp: " + GameCanvas.al.e.getText();
                Service.getInstance().d(this.ou);
                GameCanvas.setMaxTextLenght();
                return;
            case 120081:
                GameCanvas.ak.tfInput.setMaxTextLenght(11);
                GameCanvas.ak.a(mResources.pd, new Command("OK", (IActionListener) null, 120082, (Object) null), 1);
                return;
            case 120082:
                if ((var14 = GameCanvas.ak.tfInput.getText()).equals("")) {
                    GameCanvas.setText(mResources.pe);
                    return;
                }

                Service.getInstance().d("Số điện thoại đăng ký: " + var14);
                GameCanvas.setMaxTextLenght();
                return;
            case 130011:
                var25 = (Npc) obj;
                Service.getInstance().getTask(var25.template.npcTemplateId, 0);
                var25.chatPopup = null;
                this.resetButton();
                return;
            case 130012:
                ((Npc) obj).chatPopup = null;
                this.resetButton();
                return;
            case 130021:
                b((byte) 32);
                return;
            case 130022:
                c((byte) 32);
                return;
            case 140011:
                Service.getInstance().f(Char.getMyChar().nClass.skillTemplates[indexSelect].id, 1);
                this.showButtonIndexMenu();
                return;
            case 140012:
                this.az();
                return;
            case 140041:
                GameCanvas.ak.tfInput.setMaxTextLenght(180);
                GameCanvas.ak.a(mResources.pl, new Command(mResources.di, GameCanvas.instance, 88832, (Object) null), 0);
                return;
            case 140042:
                Service.getInstance().ab();
                return;
            case 140043:
                GameCanvas.ak.a(mResources.pm, new Command(mResources.di, GameCanvas.instance, 88834, (Object) null), 0);
                return;
            case 140044:
                Service.getInstance().ae();
                return;
            case 140071:
                indexRow = 0;
                indexSelect = 0;
                scrMain.a();
                gq = !gq;
                x();
                return;
            case 140072:
                indexRow = 0;
                indexSelect = 0;
                scrMain.a();
                gr = !gr;
                x();
                return;
            case 140081:
                Service.getInstance().aa();
                GameCanvas.setMaxTextLenght();
                return;
            case 140091:
                Service.getInstance().b(((Member) vClan.elementAt(indexRow)).name, 3);
                return;
            case 140092:
                Service.getInstance().b(((Member) vClan.elementAt(indexRow)).name, 2);
                return;
            case 140093:
                GameCanvas.a(mResources.ki, new Command(mResources.bn, 1400931), new Command(mResources.ca, 1));
                return;
            case 140094:
                GameCanvas.a(mResources.kj, new Command(mResources.bn, 1400941), new Command(mResources.ca, 1));
                return;
            case 140095:
                member = (Member) vClan.elementAt(indexRow);
                Service.getInstance().inviteClanDun(member.name);
                return;
            case 140096:
                (var23 = new MyVector()).addElement(new Command(mResources.qz, 1400961));
                var23.addElement(new Command(mResources.ra, 1400962));
                GameCanvas.menu.showMenu(var23);
                return;
            case 140097: //inviteAllClanDun
                for (int _indexMem = 0; _indexMem < vClan.size(); _indexMem++) {
                    Member _member = (Member) vClan.elementAt(_indexMem);
                    Char myChar = Char.getMyChar();
                    if (_member.isOnline && _member.name != myChar.charName) {
                        Service.getInstance().inviteClanDun(_member.name);
                    }
                }
                return;
            case 140098: //inviteAllClanBattlefield
                for (int _indexMem = 0; _indexMem < vClan.size(); _indexMem++) {
                    Member _member = (Member) vClan.elementAt(_indexMem);
                    Char myChar = Char.getMyChar();
                    if (_member.isOnline && _member.name != myChar.charName) {
                        Service.getInstance().inviteClanBattlefield(_member.name);
                    }
                }
                return;
            case 140099: //showClanItemCanGive
                try {
                    MyVector _menu = new MyVector();
                    member = (Member) vClan.elementAt(indexRow);
                    Item[] listItemClan = Char.clan.items;
                    for (int indexItemClan = 0; indexItemClan < listItemClan.length - 1; indexItemClan++) {
                        Item _itemClan = listItemClan[indexItemClan];
                        if (_itemClan != null && _itemClan.template.id != 281) {
                            _menu.addElement(new Command(_itemClan.template.name, 1400991, new Integer(indexItemClan)));
                        }
                    }
                    GameCanvas.menu.showMenu(_menu);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return;
            case 1400991: //giveClanItem
                member = (Member) vClan.elementAt(indexRow);
                int indexItemClan = ((Integer) obj).intValue();
                Service.getInstance().sendClanItem(member.name, indexItemClan);
                return;
            case 140101:
                (item1 = new Item()).template = ItemTemplateManager.get((short) 0);
                item1.expires = -1L;
                this.getItemInfo((int) 39, (Item) item1);
                return;
            case 140131:
                GameCanvas.a(mResources.lx, new Command(mResources.bn, 140132), new Command(mResources.ca, 1));
                return;
            case 140132:
                dl();
                return;
            case 140151:
                this.ek();
                return;
            case 140161:
                this.getItemInfo((int) 3, (Item) arrItemConvert[2]);
                return;
            case 140191:
                b((byte) 34);
                return;
            case 140192:
                c((byte) 34);
                return;
            case 140221:
                b((byte) 35);
                return;
            case 140222:
                c((byte) 35);
                return;
            case 150021:
                GameCanvas.pleaseWait();
                commandID = 0;
                commandID = Integer.parseInt(this.iz.getText());

                Service.getInstance().sendToSaleItem(itemSell, commandID);
                return;
            case 150411:
                this.it = 0;
                this.iu = 0;
                if (this.is == 0) {
                    this.is = 1;
                } else if (this.is == 1) {
                    this.is = 2;
                } else if (this.is == 2) {
                    this.is = 1;
                }

                this.cy();
                return;
            case 150412:
                this.it = 0;
                this.is = 0;
                if (this.iu == 0) {
                    this.iu = 1;
                } else if (this.iu == 1) {
                    this.iu = 2;
                } else if (this.iu == 2) {
                    this.iu = 1;
                }

                this.cy();
                return;
            case 150413:
                this.is = 0;
                this.iu = 0;
                if (this.it == 0) {
                    this.it = 1;
                } else if (this.it == 1) {
                    this.it = 2;
                } else if (this.it == 2) {
                    this.it = 1;
                }

                this.cy();
                return;
            case 150421:
                GameCanvas.setMaxTextLenght();
                Service.getInstance().ao(arrItemStands[indexSelect].item.itemId);
                return;
            case 151301:
                Service.getInstance().ap(Char.getMyChar().mobFocus.getMobTemplate().e);
                return;
            case 151710:
                Char.ex = 10;
                return;
            case 151711:
                Char.ex = 11;
                return;
            case 151712:
                Char.ex = 12;
                return;
            case 909090:
                this.openUI((int) 38);
                return;
            case 1100011:
                this.showTabBag();
                return;
            case 1100012:
                this.showTabSkill();
                return;
            case 1100013:
                this.showTabPotential();
                return;
            case 1100014:
                this.showTabInfoChar();
                return;
            case 1100015:
                showFashion = false;
                this.showTabEquip();
                return;
            case 1100016:
                this.showTabMount();
                return;
            case 1100017:
                this.showTabBijuu();
                return;
            case 1100018:
                showFashion = true;
                this.showTabEquip();
                return;
            case 1100032:
                this.bg();
                return;
            case 1100033:
                du();
                return;
            case 1100034:
                hf();
                return;
            case 1100041:
                fy();
                return;
            case 1100061:
                this.bh();
                return;
            case 1100062:
                this.bj();
                return;
            case 1100063:
                this.bk();
                return;
            case 1100064:
                this.bl();
                return;
            case 1100065:
                fu();
                return;
            case 1100067:
                dj();
                return;
            case 1100068:
                this.openUI((int) 40);
                return;
            case 1100069:
                (var22 = new MyVector()).addElement(new Command("Tàn sát all", 1100070, (Object) null));

                for (var18 = 0; var18 < qj.size(); ++var18) {
                    var20 = (MobTemplate) qj.elementAt(var18);
                    var22.addElement(new Command(var20.name, 1100070, var20));
                }

                GameCanvas.menu.showMenu(var22);
                return;
            case 1100070:
                var20 = (MobTemplate) obj;
                Code.instance.tanSat(var20 != null ? var20.e : -1, TileMap.mapID);
                return;
            case 1100071:
                MyVector var3 = new MyVector();
                var22 = new MyVector();

                for (var18 = 0; var18 < ah.size(); ++var18) {
                    var24 = (Npc) ah.elementAt(var18);
                    if (!var22.contains(var24.template)) {
                        var22.addElement(var24.template);
                        var3.addElement(new Command(var24.template.name + " ID: " + var24.template.npcTemplateId, 1100072, var24));
                    }
                }

                GameCanvas.menu.showMenu(var3);
                return;
            case 1100072:
                if ((var24 = (Npc) obj) != null) {
                    if (Math.abs(var24.cx - Char.getMyChar().cx) > 22) {
                        Char.charMove(var24.cx, var24.cy);
                    }

                    Service.getInstance().openMenu(var24.template.npcTemplateId);
                }

                return;
            case 1100073:
                var10000 = Code.instance;
                Code.stopCurrentAuto();
                return;
            case 1100074:
                Code.instance.startAutoNVHN();
                return;
            case 1100075:
               Code.instance.startAutoTaThuSolo();
                return;
            case 1100076:
                super.right = this.mw;
                indexMenu = 0;
                showPickItem = true;
                b(175, 200);
                return;
            case 1100077:
                Code.b(Code.pickUpListID[indexSelect]);
                return;
            case 1100078:
                Code.l();
                return;
            case 1100079:
                Code.a(Char.getMyChar().arrItemBag[indexSelect].template.id);
                return;
            case 1100080:
                Code.isHutVP = !Code.isHutVP;
                return;
            case 1100081:
                GameCanvas.ak.a("KC Nhặt", new Command("Đặt", 1100085), 1);
                GameCanvas.ak.tfInput.a(String.valueOf(Code.khoangCachNhat));
                return;
            case 1100082:
                GameCanvas.ak.a("KC Tàn sát", new Command("Đặt", 1100086), 1);
                GameCanvas.ak.tfInput.a(String.valueOf(Code.m));
                return;
            case 1100083:
                Code.attackChangePosition = !Code.attackChangePosition;
                return;
            case 1100084:
                Code.keepLevel = !Code.keepLevel;
                return;
            case 1100085:
                Code.khoangCachNhat = Integer.parseInt(GameCanvas.ak.tfInput.getText());

                GameCanvas.setMaxTextLenght();
                return;
            case 1100086:
                Code.m = Integer.parseInt(GameCanvas.ak.tfInput.getText());

                GameCanvas.setMaxTextLenght();
                return;
            case 1100087:
                GameCanvas.ak.a("Độ Trễ", new Command("Đặt", 1100088), 1);
                GameCanvas.ak.tfInput.a(String.valueOf(Code.speedGame));
                return;
            case 1100088:
                int var4 = Code.speedGame;

                var4 = Integer.parseInt(GameCanvas.ak.tfInput.getText());

                if (var4 >= 0 && var4 < 100) {
                    Code.speedGame = var4;
                } else {
                    chatPopup("Tốc độ game từ 0 đến 99");
                }

                GameCanvas.setMaxTextLenght();
                return;
            case 1100089:
                if (Code.attackChangeZone = !Code.attackChangeZone) {
                    GameCanvas.ak.a("Khu - mỗi khu cách nhau bằng dấu cách", new Command("Đặt", 1100090), 0);
                    GameCanvas.ak.tfInput.a(Code.k());
                }

                return;
            case 1100090:
                Code.e(GameCanvas.ak.tfInput.getText());
                GameCanvas.setMaxTextLenght();
                return;
            case 1100091:
                InfoDlg.d();
                ce = true;
                this.iw = true;
                indexRow = 0;
                b(175, 200);
                super.right = new Command(mResources.am, 3);
                super.left = super.center = null;
                this.iy = "Danh sách mua bán";
                this.ix = SettingAutoMuaBan.a();
                return;
            case 1100092:
                InfoDlg.d();
                ce = true;
                this.iw = true;
                indexRow = 0;
                b(175, 200);
                super.right = new Command(mResources.am, 3);
                super.left = super.center = null;
                this.iy = "Danh sách shinwa";
                this.ix = Code.i();
                return;
            case 1100093:
                InfoDlg.d();
                ce = true;
                this.iw = true;
                indexRow = 0;
                b(175, 200);
                super.right = new Command(mResources.am, 3);
                super.left = super.center = null;
                this.iy = "Danh sách PK";
                this.ix = SavePK.a();
                return;
            case 1100094:
                InfoDlg.d();
                ce = true;
                this.iw = true;
                indexRow = 0;
                b(175, 200);
                super.right = new Command(mResources.am, 3);
                super.left = super.center = null;
                this.iy = "Danh sách HS";
                this.ix = Code.g();
                return;

            case 1100099:
                Code.instance.startAutoDV();
                return;
            case 1100181:
                GameCanvas.a(mResources.h, new Command(mResources.sm, 1100182), new Command(mResources.ca, GameCanvas.instance, 8882, (Object) null));
                return;
            case 1100182:
                GameCanvas.currentDialog = null;
                GameCanvas.ag.a();
                return;
            case 1102211:
                GameCanvas.ak.a("Đặt giá" + (getCurrentItemSelectByTypeUI(3) != null ? " " + getCurrentItemSelectByTypeUI(3).template.name : ""), new Command("Đặt", 1102212), 1);
                GameCanvas.ak.tfInput.a(SettingAutoMuaBan.a(getCurrentItemSelectByTypeUI(3).template.id).f());
                return;
            case 1102212:
                if (getCurrentItemSelectByTypeUI(3) != null) {
                    SettingAutoMuaBan.a(getCurrentItemSelectByTypeUI(3).template, GameCanvas.ak.tfInput.getText());
                }

                GameCanvas.setMaxTextLenght();
                return;
            case 1102213:
                if (getCurrentItemSelectByTypeUI(3) != null) {
                    SettingAutoMuaBan.a(getCurrentItemSelectByTypeUI(3).template);
                }

                return;
            case 1103991:
                this.actionToOtherPlayer(10);
                return;
            case 1107921:
                var14 = (String) obj;
                GameCanvas.setMaxTextLenght();
                Service.getInstance().g(var14);
                b(var14);
                return;
            case 1107931:
                var14 = (String) obj;
                Service.getInstance().addFriend(var14);
                return;
            case 1107932:
                b((String) obj);
                return;
            case 1108041:
                var14 = (String) obj;
                Service.getInstance().viewInfo(var14);
                getInstance().resetButton();
                return;
            case 1400931:
                Service.getInstance().b(((Member) vClan.elementAt(indexRow)).name, 0);
                GameCanvas.setMaxTextLenght();
                return;
            case 1400941:
                Service.getInstance().p(((Member) vClan.elementAt(indexRow)).name);
                GameCanvas.setMaxTextLenght();
                return;
            case 1400961:
                member = (Member) vClan.elementAt(indexRow);
                Service.getInstance().inviteClanBattlefield(member.name);
                return;
            case 1400962:
                Service.getInstance().ad();
                return;
            case 11000601:
                this.openUI((int) 36);
                return;
            case 11000602:
                this.openUI((int) 43);
                return;
            case 11000603:
                this.openUI((int) 44);
                return;
            case 11000604:
                this.openUI((int) 45);
                return;
            case 11000651:
                p(1);
                return;
            case 11000652:
                p(2);
                return;
            case 11000653:
                p(3);
                return;
            case 11000661:
                indexMenu = 0;
                this.openClanTab();
                return;
            case 11000662:
                indexMenu = 1;
                this.openClanTab();
                Service.getInstance().requestClanItem();
                Service.getInstance().requestClanMember();
                return;
            case 11000663:
                indexMenu = 2;
                this.openClanTab();
                Service.getInstance().requestClanItem();
                return;
            case 11000664:
                indexMenu = 3;
                this.openClanTab();
                Service.getInstance().requestClanLog();
                return;
            case 11000665:
                if (cb = !cb) {
                    RMS.writeRecord(Char.getMyChar().charName + "vci", 1);
                    return;
                }

                RMS.writeRecord(Char.getMyChar().charName + "vci", 0);
                return;
            case 11000666:
                indexMenu = 4;
                this.openClanTab();
                Service.getInstance().requestClanItem();
                return;
            case 11000671:
                GameCanvas.a(mResources.pb, 88836, (Object) null, 8882, (Object) null);
                return;
            case 11000672:
                GameCanvas.ak.tfInput.setMaxTextLenght(6);
                GameCanvas.ak.a(mResources.pc, new Command(mResources.di, GameCanvas.instance, 88837, (Object) null), 1);
                return;
            case 11000673:
                GameCanvas.al.a(mResources.pi, mResources.pj);
                GameCanvas.al.d.setMaxTextLenght(6);
                GameCanvas.al.e.setMaxTextLenght(6);
                GameCanvas.al.a(mResources.pc, new Command(mResources.am, GameCanvas.instance, 8882, (Object) null), new Command(mResources.di, GameCanvas.instance, 88838, (Object) null), 1, 1);
                return;
            case 11000674:
                GameCanvas.ak.tfInput.setMaxTextLenght(6);
                GameCanvas.ak.a(mResources.pc, new Command(mResources.di, GameCanvas.instance, 88839, (Object) null), 1);
                return;
            case 11000761:
                super.right = this.mw;
                indexMenu = 0;
                showDelItem = true;
                b(175, 200);
                return;
            case 11000771:
                Code.removeDelItem(Code.delListID[indexSelect]);
                return;
            case 11000781:
                Code.sortDelItem();
                return;
            case 11000791:
                System.out.println("11000791");
                Code.addDelItem(Char.getMyChar().arrItemBag[indexSelect].template.id);
                return;
            case 11000762:
                super.right = this.mw;
                indexMenu = 0;
                showItemThrow = true;
                b(175, 200);
                return;
            case 11000772:
                Code.removeItemThrow(Code.throwListID[indexSelect]);
                return;
            case 11000782:
                Code.o();
                return;
            case 11000792:
                Code.addItemThrow(Char.getMyChar().arrItemBag[indexSelect].template.id);
                return;

            case 11000799:
                (new SettingMuaBan()).a();
                return;
            case 110008000:
                showMenuSetting();
                return;
            case 11000800:
                GameCanvas.ak.a("Nhập số phiếu cần lật", new Command("OK", 11000801), 1);
                return;
            case 11000801:
                AutoLuckyCard.c = Integer.parseInt(GameCanvas.ak.tfInput.getText());
                (new Thread(new AutoLuckyCard(AutoLuckyCard.c))).start();
                GameCanvas.setMaxTextLenght();
                return;
            case 11000802:
                GameCanvas.ak.a("Tốc Độ Lật Hình", new Command("OK", 11000803), 1);
                return;
            case 11000803:
                AutoLuckyCard.a = (long) Integer.parseInt(GameCanvas.ak.tfInput.getText());
                GameCanvas.setMaxTextLenght();
                return;
            case 11000804:
                hu();
                return;
            case 11000805:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    new Thread(new AutoLuyenNgoc(item1)).start();
                }

                return;
            case 11000807:
                (new Thread(new AutoDichChuyen(getCurrentItemSelectByTypeUI(3)))).start();
                return;
            case 11000808:
                var14 = Code.h("ln.txt");
                getInstance().a("* Chú ý *", var14, true);
                return;
            case 11000809:
                if ((item1 = getCurrentItemSelectByTypeUI(3)) != null) {
                    new Thread(new AutoVutRaDat(item1.template.id)).start();
                }
                return;
            case 110008011: //Setting ADV
                new SettingNVDV().show();
                return;
            case 110008012:
                showMenuSettingGomDo();
                return;
            case 1100080121:
                super.right = this.mw;
                indexMenu = 0;
                showItemGather = true;
                b(175, 200);
                return;
            case 1100080122:
                AutoReceiver.removeItemGather(AutoReceiver.gatherListID[indexSelect]);
                return;
            case 1100080123:
                AutoReceiver.sortListGather();
                return;
            case 1100080124:
                AutoReceiver.addItemGather(Char.getMyChar().arrItemBag[indexSelect].template.id);
                return;
            case 1100080125:
                (new SettingGomDo()).a();
                return;
            case 11000810:
                new FormAutoNpc().select();
                return;

            case 11000811:
                new FormLDGT().select();
                return;

            case 11000814:
                AutoNpc.startFormConfig(); // chạy npc
                return;
            case 11000910:
                new FormAutoUp().select();
                return;
            case 11000911:
                new FormAutoTask().select();
                return;
            case 11000912:
                showMenuNsoChen();
                return;
            case 11000950:
                new FormNsoChenSetting().select();
                return;
            case 11000962:
                showMenuNsoChenEvent();
                return;
            case 11000913:
                new FormAutoBoss().select();
                return;
            case 11000914:
                new FormHD9x().select();
                return;
            case 11000915:
                new FormAutoLuyenNgoc().select();
                return;
            case 11000954:
                new FormAutoLongDen().select();
                return;
            case 11000956:
                new FormAutoRuocDen().select();
                return;
            case 11000958:
                new FormAutoViThu().select();
                return;
            case 11000963:
                new FormAutoHalloween().select();
                return;
            case 11000940:
                new FormAutoTinhLuyen().select();
                return;
            case 11000965:
                new FormAutoUpFull().select();
                return;
            case 11000916:
                Code.setTeleTarget(!Code.teleTarget);
             //   chatPopup("Tele muc tieu: " + (Code.teleTarget ? "Bat" : "Tat"));
                return;
            case 11000917:
                new FormEventTrade().select();
                return;
            case 11000918:
                new FormAutoDapDo().select();
                return;
            case 11000932:
                new FormAutoDungHop().select();
                return;
            case 11000934:
                new FormAutoBiKip().select();
                return;
            case 11000919:
                showMenuNsoChenClanLeader();
                return;
            case 11000920:
                AutoNpc.callClanFromForm();
                return;
            case 11000921:
                AutoEventTrade.callClanFromForm();
                return;
            case 11000922:
                showMenuNsoChenGuide();
                return;
            case 11000923:
                showNsoChenGuide("Hướng dẫn Boss", "menuboss: mở form auto boss LC/LTT/VDMQ\nÔ Map muốn săn: để trống săn hết, nhập 163,164 để chỉ săn map đó trong vùng đã tick\nboss hoặc bossall: săn boss ngay theo khu vực đã chọn\ntb: tìm boss\ntsx + id: đánh boss theo id");
                return;
            case 11000924:
                showNsoChenGuide("Hướng dẫn HD9x", "menuhd9x: mở form auto hang động 9x\nhd9x: bắt đầu đi hang động 9x\nTrong form: chọn đánh boss hoặc farm rương, set acc thành viên, tick nhóm trưởng nếu là leader");
                return;
            case 11000925:
                showNsoChenGuide("Hướng dẫn Gom Sự Kiện", "menugomsk: mở form cấu hình gom sự kiện\ngomsk: acc chính bắt đầu đi gom đồ\nsetgomsk / setgomskgt / gomskgt: tộc trưởng gọi cấu hình qua chat gia tộc");
                return;
            case 11000949:
                showNsoChenGuide("Huong dan Gom Do", "gui / gomdo / gdngay: di giao dich ngay theo Cai Dat Gom Do\nnhan: acc nhan do dung o map/khu nhan va bat cho giao dich\nCai Dat Gom Do: ten nhan do, map/khu nhan, item gom va gom xu");
                return;
            case 11000926:
                showNsoChenGuide("Hướng dẫn Đập Đồ", "menudd: mở form Auto Đập Đồ\ndd hoặc dapdo: bật/tắt auto đập đồ\nstopdd: dừng auto đập đồ\nCó thể chọn index 0, vũ khí, trang sức, trang phục và cấp cần đập");
                return;
            case 11000927:
                showNsoChenGuide("Hướng dẫn Luyện Ngọc", "setln: mở form cài đặt luyện ngọc\naln: bật/tắt auto luyện ngọc ô 0\nTick Săn ngọc max trong setln để giữ ngọc 652-655 đạt max VK/TB/TS, không dùng làm phôi");
                return;
            case 11000955:
                showNsoChenGuide("Hướng dẫn Đổi Lồng Đèn", "menuden/setden: mở form auto đổi lồng đèn\nden/doiden: bật/tắt auto đổi lồng đèn\nstopden: dừng auto đổi lồng đèn\nChọn đổi bằng xu hoặc lượng trong form\nTrung Thu hiện có xu menu 2; lượng thường dùng menu 2,1 nếu server có mở\nRule ví dụ 87>=4500,6>=1800; bật Tự xóa nếu không đạt để chỉ xóa đúng đèn vừa đổi");
                return;
            case 11000957:
                showNsoChenGuide("Hướng dẫn Rước Đèn", "menurd/setrd: mở form auto rước đèn\nrd/ruocden: rước ngay\nstoprd: dừng auto rước đèn\nMặc định map 26, NPC 34, menu 0, dùng hộp diêm 310\nTrong form có giờ/phút tự đi; đến giờ auto sẽ tạm dừng auto hiện tại, đi map 26 và bám sát lồng đèn");
                return;
            case 11000959:
                showNsoChenGuide("Huong dan Vi Thu", "setvt/menuvt: mo form auto Vi Thu\nvt/vithu: chay ngay hoac tat neu dang chay\nstopvt: dung auto Vi Thu\nnhh/nguhanhhoa: bat/tat tu dung Ngu Hanh Hoa 988\nCo the bat mac dinh bang tick trong nsochen > Cai dat\ntrungvt/motrungvt: bat/tat auto mo trung 993\nstoptrungvt: dung auto mo trung\nCan 1 lenh 983 va 5 day Nenshi 946, thieu se tu mua shop type 8\nTick Danh boss: vao map 169 danh boss 237\nBo tick Danh boss: vao map 169 dung cho boss chet roi nhat trung 993\nTick xoa vi thu/trung co han moi xoa do khong vinh vien");
                return;
            case 11000964:
                showNsoChenGuide("H\u01b0\u1edbng d\u1eabn H\u00f3a Trang", "setht/menuht: m\u1edf form auto l\u1ec5 h\u1ed9i h\u00f3a trang\nht/hoatrang/halloween: b\u1eadt/t\u1eaft auto ngay\nstopht/dunght: d\u1eebng auto h\u00f3a trang\nNPC Tabemono(4), gi\u1edd 19-23, c\u1ea7n nh\u00f3m t\u1eeb 2 acc\nM\u1ed7i acc c\u1ea7n Th\u01b0 M\u1eddi 1071 v\u00e0 m\u1eb7t n\u1ea1 814-818\nTr\u01b0\u1edfng nh\u00f3m nh\u1eadp danh s\u00e1ch acc ph\u1ee5; th\u00e0nh vi\u00ean nh\u1eadp t\u00ean tr\u01b0\u1edfng ho\u1eb7c \u0111\u1ec3 tr\u1ed1ng");
                return;
            case 11000941:
                showNsoChenGuide("Hướng dẫn Tinh Luyện", "menutl: mở form auto tinh luyện\ntl: bật/tắt auto theo cấu hình\nstoptl: dừng auto tinh luyện\ndc: chạy nhanh chế độ chỉ dịch chuyển\nDịch chuyển cần đồ +12 và 20 item 454\nTinh luyện dùng 455/456/457, có thể tự ghép thạch");
                return;
            case 11000942:
                new FormToiUu().select();
                return;
            case 11000943:
                super.right = this.mw;
                indexMenu = 0;
                showAutoUseItem = true;
                b(175, 200);
                return;
            case 11000944:
                if (indexSelect >= 0 && indexSelect < AutoUseItem.getListIds().length && AutoUseItem.getItemIdAt(indexSelect) > 0) {
                    new FormAutoUseItem(indexSelect).select();
                }
                return;
            case 11000945:
                AutoUseItem.sort();
                return;
            case 11000946:
                if (indexSelect >= 0 && indexSelect < Char.getMyChar().arrItemBag.length && Char.getMyChar().arrItemBag[indexSelect] != null) {
                    int itemId = Char.getMyChar().arrItemBag[indexSelect].template.id;
                    int ruleIndex = AutoUseItem.addDefault(itemId);
                    new FormAutoUseItem(ruleIndex).select();
                }
                return;
            case 11000928:
                showNsoChenGuide("Hướng dẫn Mở All", "Mở all: trong menu item có số lượng > 1\nDelay Mở All: cài tốc độ mở theo ms\nDelay tối thiểu hiện tại là 10ms để tránh mở quá nhanh");
                return;
            case 11000930:
                new FormAutoBuyShop().select();
                return;
            case 11000931:
                showNsoChenGuide("Hướng dẫn Tự Mua Shop", "menumua: mở form tự mua shop\nautoshop: bật/tắt nhanh\nCài theo dạng ID item / ID shop / số lượng, ví dụ 545,285 / 3,14 / 1,4");
                return;
            case 11000952:
                new FormAutoCauCa().select();
                return;
            case 11000953:
                showNsoChenGuide("Hướng dẫn Câu Cá", "setcc hoặc menucauca: mở form cài auto câu cá\ncc hoặc cauca: bật/tắt auto câu cá\nstopcc: dừng auto câu cá\nMặc định dùng cần 597 shop 8, mồi 603 shop 14, delay 1000ms theo sleep server\nNếu shop cần/mồi khác thực tế thì chỉnh ID shop trong form");
                return;
            case 11000947:
                showNsoChenGuide("Hướng dẫn Tự Dùng Item", "Trong nsochen mở Tự Dùng Item để xem list icon như Item Nhặt\nChọn icon rồi Cài đặt: delay, shop, số lượng, tự mua, check hiệu ứng\nBody trống (-1 tắt): nhập index body, ví dụ 10 để khi body(10) trống thì tự mua và dùng item");
                return;
            case 11000933:
                showNsoChenGuide("Hướng dẫn Dung Hợp", "menudh: mở form Auto Dung Hợp\ndh hoặc dunghop: bật/tắt auto nâng cấp dung hợp\nstopdh: dừng auto dung hợp\nIndex đồ chính -1 sẽ tự quét nhiều đồ\nPhôi tự chọn cùng loại/giới/hệ và hơn 1 cấp\nSăn 6x có thể nhập rule chỉ số, ví dụ 0>=480,1>=480,21>=1750\nCó thể bật tự mua phôi 7x/8x trong form");
                return;
            case 11000935:
                showNsoChenGuide("Hướng dẫn Bí Kíp", "menubk: mở form auto săn bí kíp\nbk hoặc bikip: bật/tắt auto săn bí kíp\nstopbk: dừng auto bí kíp\nBí kíp phải đang mặc và chưa nâng cấp\nMỗi lần luyện tốn 1000 lượng, auto dừng khi đạt chỉ số đã chọn");
                return;
            case 11000936:
                showNsoChenGuide("Hướng dẫn Auto Nhiệm Vụ", "asall: auto nhiệm vụ, nếu đã vào lớp sẽ dùng phái hiện tại\nasallk: chọn Kiếm\nasallt: chọn Tiêu\nasallku: chọn Kunai\nasallc: chọn Cung\nasalld: chọn Đao\nasallq: chọn Quạt\nasallth: chọn Thương\nCó thể dùng dạng asall + k/t/ku/c/d/q/th");
                return;
            case 11000937:
                showNsoChenGuide("Huong dan Up Level", "uplv70: auto up den level 70\nuplvpt70: auto up phan than den level 70\nDieu kien PT: chu than >90 va da mo skill phan than\nstopuplv: dung auto up level\nAuto chon quai lv nhan vat +2 den +8\nPT tu mua 545, chu than mua 564 shop 8 va chuyen PT de dung\nTu level 100 tro len se tu vao VDMQ");
                return;
            case 11000966:
                showNsoChenGuide("Huong dan Auto Up Tong", "setuplv/menuuplv: mo form Auto Up Tong\nuplvfull70: up den level 70, tu hoc sach type 15, tu cong tiem nang/ky nang, tu mua do thieu va dap set theo cau hinh\nstopuplv: dung auto\nTien nang: ngoai cong cong Suc manh, noi cong cong Chakra\nKy nang: uu tien skill danh 1x,2x,3x,5x\nTrong form co set 3x/4x/5x/6x, loai do, muc dap chung, dung bao hiem, doi luong ra yen khi het yen va tu mua/dung Nam linh chi 248 x2");
                return;
            case 11000938:
                new FormAutoGiftCode().select();
                return;
            case 11000939:
                showNsoChenGuide("Huong dan Giftcode", "menugift: mo form auto giftcode\ngift hoac giftcode: chay nhap code tu giftcodes.txt\nstopgift: dung auto giftcode\ncleargift: xoa lich su code da thu\nMac dinh dung AutoNPC: NPC 30, menu 1, nhap lieu");
                return;
            case 11000960:
                new FormAutoChat().select();
                return;
            case 11000961:
                showNsoChenGuide("Huong dan Auto Chat", "setchat/menuchat: mo form Auto Chat\nachat/autochat: bat/tat auto chat\nachatnow/chatnow: gui ngay 1 cau\nstopchat: dung auto chat\nHo tro kenh Cong cong va The gioi; The gioi ton 5 luong/lan va delay toi thieu 60s");
                return;
            case 11000929:
                showNsoChenGuide("Hướng dẫn Tộc Trưởng", "Trong nsochen chỉ hiện menu Tộc Trưởng nếu là tộc trưởng\nanpcgt: gọi auto npc qua chat gia tộc\nSet Gom Sự Kiện GT: gọi gom sự kiện qua chat gia tộc");
                return;
        }

    }

    private void cu() {
        try {
            if (Char.getMyChar().arrItemBag[indexSelect].template.id == 454) {
                if (arrItemSplit == null) {
                    arrItemSplit = new Item[24];
                }

                for (int var3 = 0; var3 < arrItemSplit.length; ++var3) {
                    if (var3 == 20) {
                        GameCanvas.setText(mResources.js);
                        return;
                    }

                    if (arrItemSplit[var3] == null) {
                        arrItemSplit[var3] = Char.getMyChar().arrItemBag[indexSelect];
                        Char.getMyChar().arrItemBag[indexSelect] = null;
                        super.left = super.center = null;
                        this.ab();
                        return;
                    }
                }
            } else if (Char.getMyChar().arrItemBag[indexSelect].upgrade > 11) {
                if (itemSplit == null) {
                    itemSplit = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                } else {
                    Item var1 = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                    Char.getMyChar().arrItemBag[itemSplit.indexUI] = itemSplit;
                    itemSplit = var1;
                }

                Service.getInstance().requestItemInfo(itemSplit.typeUI, itemSplit.indexUI);
            } else {
                GameCanvas.setText(mResources.rz);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private void cv() {
        Item var1 = getCurrentItemSelectByTypeUI(44);
        arrItemSplit[indexSelect] = null;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        super.left = super.center = null;
        this.ab();
    }

    private static void cw() {
        switch (indexRow) {
            case 0:
                Char.tickAutoUseHP = !Char.tickAutoUseHP;
                if (ea == 1) {
                    Char.tickAutoUseHP = false;
                    InfoMe.a(mResources.rl, 20, mFont.tahoma_7_yellow);
                }

                return;
            case 1:
                Char.tickAutoUseMP = !Char.tickAutoUseMP;
                if (ea == 1) {
                    Char.tickAutoUseMP = false;
                    InfoMe.a(mResources.rl, 20, mFont.tahoma_7_yellow);
                }

                return;
            case 2:
                Char.tickAutoFood = !Char.tickAutoFood;
                if (ea == 1) {
                    Char.tickAutoFood = false;
                    InfoMe.a(mResources.rl, 20, mFont.tahoma_7_yellow);
                }

                return;
            case 3:
                Char.tickAutoBuff = !Char.tickAutoBuff;
                return;
            case 4:
                Char.dl = !Char.dl;
                return;
            case 5:
                Char.dm = !Char.dm;
                return;
            case 6:
                Char.dk = !Char.dk;
                return;
            case 7:
                if (Char.dn = !Char.dn) {
                    Char.tickKhongNhat = false;
                }

                return;
            case 8:
                if (Char.doa = !Char.doa) {
                    Char.tickKhongNhat = false;
                }

                return;
            case 9:
                if (Char.tickNhatDa = !Char.tickNhatDa) {
                    Char.tickKhongNhat = false;
                }

                return;
            case 10:
                Char.tickLuyenDaMax = !Char.tickLuyenDaMax;
                return;
            case 11:
                if (Char.tickNhatTrangBi = !Char.tickNhatTrangBi) {
                    Char.tickKhongNhat = false;
                }

                return;
            case 12:
                if (Char.tickNhatTrangBiLa = !Char.tickNhatTrangBiLa) {
                    Char.tickNhatTrangBi = true;
                    Char.tickKhongNhat = false;
                }

                return;
            case 13:
                if (Char.tickNhatVPNhiemVu = !Char.tickNhatVPNhiemVu) {
                    Char.tickKhongNhat = false;
                }

                return;
            case 14:
                if (Char.tickNhatVPSK = !Char.tickNhatVPSK) {
                    Char.tickKhongNhat = false;
                }

                return;
            case 15:
                if (Char.tickNhatTatCa = !Char.tickNhatTatCa) {
                    Char.tickKhongNhat = false;
                }

                return;
            case 16:
                if (Char.tickNhatSVC = !Char.tickNhatSVC) {
                    Char.tickKhongNhat = false;
                }

                return;
            case 17:
                if (Char.tickKhongNhat = !Char.tickKhongNhat) {
                    Char.tickNhatDa = false;
                    Char.doa = false;
                    Char.dn = false;
                    Char.tickNhatTrangBi = false;
                    Char.tickNhatTrangBiLa = false;
                    Char.tickNhatVPNhiemVu = false;
                    Char.tickNhatVPSK = false;
                    Char.tickNhatTatCa = false;
                    Char.tickNhatSVC = false;
                }

                return;
            case 18:
                Char.tickAutoTTT = !Char.tickAutoTTT;
                return;
            case 19:
                Char.tickAutoTTC = !Char.tickAutoTTC;
                return;
            case 20:
                Char.tickReMap = !Char.tickReMap;
                return;
            case 21:
                Char.tickTSMapEmpty = !Char.tickTSMapEmpty;
                return;
            case 22:
                Char.tickAutoMuaTA = !Char.tickAutoMuaTA;
                return;
            case 23:
                Char.tickDieKhiHetMP = !Char.tickDieKhiHetMP;
                return;
            case 24:
                Char.tickAutoReconnect = !Char.tickAutoReconnect;
                return;
            case 25:
                Char.tickChuyenMapHetBoss = !Char.tickChuyenMapHetBoss;
                return;
            case 26:
                Char.tickSanTATL = !Char.tickSanTATL;
                return;
            case 27:
                Char.tickDanhQuaiThuong = !Char.tickDanhQuaiThuong;
                return;
            case 28:
                Char.tickDanhTinhAnh = !Char.tickDanhTinhAnh;
                return;
            case 29:
                Char.tickDanhThuLinh = !Char.tickDanhThuLinh;
                return;
            case 30:
                Char.tickCongTiemNang = !Char.tickCongTiemNang;
                return;
            case 31:
                Char.tickCongKyNang = !Char.tickCongKyNang;
                return;
            case 32:
                Char.tickDanhTheoNhom = !Char.tickDanhTheoNhom;
                return;
            case 33:
                Char.tickNePK = !Char.tickNePK;
                return;
            case 34:
                Char.tickAutoCoLenh = !Char.tickAutoCoLenh;
                return;
            case 35:
                Char.tickAutoMuaCoLenh = !Char.tickAutoMuaCoLenh;
                return;
            case 36:
                Char.tickAutoLangThuyenThuyet = !Char.tickAutoLangThuyenThuyet;
                return;
            case 37:
                Char.tickAutoMuaTruyenThuyetLenh = !Char.tickAutoMuaTruyenThuyetLenh;
                return;
            case 38:
                Code.tbNhanExp = !Code.tbNhanExp;
                return;
            case 39:
                Code.tbNhanVP = !Code.tbNhanVP;
                return;
            case 41:
                Char.tickAutoLocDo = !Char.tickAutoLocDo;
                return;
            case 42:
                if (!Char.tickAutoTTGT && !Code.isInClan(Char.getMyChar())) {
                    Code.setAutoTTGT(false);
                    GameScr.chatPopup("TTGT: chưa có gia tộc, không bật");
                } else {
                    Code.setAutoTTGT(!Char.tickAutoTTGT);
                    GameScr.chatPopup((Char.tickAutoTTGT ? "Bật" : "Tắt") + " auto TTGT");
                }

                return;
            case 43:
                SelectCharScr.toggleQuickLogin();
                return;
            default:
        }
    }

    public static void hideCommandBoxUi() {
        try {
            showBox = false;
            GameCanvas.currentDialog = null;
            if (GameCanvas.menu != null) {
                GameCanvas.menu.showMenu = false;
            }
            getInstance().resetButton();
        } catch (Exception e) {
        }
    }

    public static void hideLuckyCardUi() {
        try {
            arrItemSprin = null;
            an = -1;
            ifa = false;
            gk = 0;
            GameScr var0 = getInstance();
            if (var0 != null) {
                var0.ef = null;
                var0.left = null;
                var0.center = null;
                var0.resetButton();
            }
            GameCanvas.currentDialog = null;
            if (GameCanvas.menu != null) {
                GameCanvas.menu.showMenu = false;
            }
            GameCanvas.setMaxTextLenght();
        } catch (Exception e) {
        }
    }

    private void cx() {
        if (arrItemSprin != null) {
            Item var1;
            (var1 = new Item()).template = ItemTemplateManager.get(arrItemSprin[indexSelect]);
            this.getItemInfo((int) 38, (Item) var1);
        }

    }

    private void cy() {
        if (this.it != 0 || this.is != 0 || this.iu != 0) {
            for (int var1 = 0; var1 < arrItemStands.length - 1; ++var1) {
                for (int var2 = var1 + 1; var2 < arrItemStands.length; ++var2) {
                    ItemStands var3;
                    if (this.is == 1) {
                        if (arrItemStands[var1].b < arrItemStands[var2].b) {
                            var3 = arrItemStands[var1];
                            arrItemStands[var1] = arrItemStands[var2];
                            arrItemStands[var2] = var3;
                        }
                    } else if (this.is == 2 && arrItemStands[var1].b > arrItemStands[var2].b) {
                        var3 = arrItemStands[var1];
                        arrItemStands[var1] = arrItemStands[var2];
                        arrItemStands[var2] = var3;
                    }

                    if (this.iu == 1) {
                        if (!arrItemStands[var1].item.template.name.equals(arrItemStands[var2].item.template.name) && arrItemStands[var1].item.template.name.compareTo(arrItemStands[var2].item.template.name) > 0) {
                            var3 = arrItemStands[var1];
                            arrItemStands[var1] = arrItemStands[var2];
                            arrItemStands[var2] = var3;
                        }
                    } else if (this.iu == 2 && !arrItemStands[var1].item.template.name.equals(arrItemStands[var2].item.template.name) && arrItemStands[var1].item.template.name.compareTo(arrItemStands[var2].item.template.name) < 0) {
                        var3 = arrItemStands[var1];
                        arrItemStands[var1] = arrItemStands[var2];
                        arrItemStands[var2] = var3;
                    }

                    if (this.it == 1) {
                        if (arrItemStands[var1].item.template.level < arrItemStands[var2].item.template.level) {
                            var3 = arrItemStands[var1];
                            arrItemStands[var1] = arrItemStands[var2];
                            arrItemStands[var2] = var3;
                        }
                    } else if (this.it == 2 && arrItemStands[var1].item.template.level > arrItemStands[var2].item.template.level) {
                        var3 = arrItemStands[var1];
                        arrItemStands[var1] = arrItemStands[var2];
                        arrItemStands[var2] = var3;
                    }
                }
            }
        }

    }

    private static void cz() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.qg, 150411));
        var0.addElement(new Command(mResources.qi, 150412));
        var0.addElement(new Command(mResources.qh, 150413));
        GameCanvas.menu.showMenu(var0);
    }

    private static void da() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.sapXep, 15041));
        var0.addElement(new Command(mResources.by, 15042));
        GameCanvas.menu.showMenu(var0);
    }

    private void db() {
        Char.getMyChar().arrItemBag[itemSell.indexUI] = itemSell;
        itemSell = null;
        super.left = super.center = null;
    }

    private static void dc() {
        Item var0;
        if ((var0 = Char.getMyChar().arrItemBag[indexSelect]) != null) {
            if (!var0.isLock && !var0.isExpires) {
                if (itemSell == null) {
                    itemSell = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                    return;
                }

                var0 = Char.getMyChar().arrItemBag[indexSelect];
                Char.getMyChar().arrItemBag[indexSelect] = null;
                Char.getMyChar().arrItemBag[itemSell.indexUI] = itemSell;
                itemSell = var0;
                return;
            }

            GameCanvas.setText(mResources.jk);
        }

    }

    private static void dd() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 140191));
        var0.addElement(new Command(mResources.bz, 140192));
        GameCanvas.menu.showMenu(var0);
    }

    private static void de() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.bf, (IActionListener) null, 120081, (Object) null));
        var0.addElement(new Command(mResources.bg, (IActionListener) null, 12007, (Object) null));
        GameCanvas.menu.showMenu(var0);
    }

    private void df() {
        if (this.ot == 1) {
            GameCanvas.al.a(mResources.ps, mResources.pr);
            GameCanvas.al.a(mResources.po, new Command(mResources.am, GameCanvas.b(), 8882, (Object) null), new Command(mResources.bh, (IActionListener) null, 120071, (Object) null), 0, 0);
        } else {
            GameCanvas.al.a(mResources.pt, mResources.pu);
            GameCanvas.al.a(mResources.pp, new Command(mResources.am, GameCanvas.b(), 8882, (Object) null), new Command(mResources.okey, (IActionListener) null, 120072, (Object) null), 0, 0);
        }

    }

    public static void b(String var0) {
        int var1;
        for (var1 = 0; var1 < aa.size(); ++var1) {
            Friend var2;
            if ((var2 = (Friend) aa.elementAt(var1)).a.equals(var0) && var2.b == 4) {
                aa.removeElementAt(var1);
                break;
            }
        }

        for (var1 = 0; var1 < ac.size(); ++var1) {
            if (((Friend) ac.elementAt(var1)).a.equals(var0)) {
                ac.removeElementAt(var1);
                return;
            }
        }

    }

    private static void dg() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.menuClan[0] + ": " + (gq ? mResources.aw : mResources.av), 140071));
        var0.addElement(new Command(mResources.ob, 140072));
        GameCanvas.menu.showMenu(var0);
    }

    private static void dh() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.menuClan[0] + ": " + (gq ? mResources.aw : mResources.av), 140071));
        GameCanvas.menu.showMenu(var0);
    }

    private static void di() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.og, 110441));
        var0.addElement(new Command(mResources.menuClan[0] + ": " + (gq ? mResources.aw : mResources.av), 140071));
        GameCanvas.menu.showMenu(var0);
    }

    private static void dj() {
        MyVector var0 = new MyVector();
        if (ea == 0) {
            var0.addElement(new Command(mResources.az, 11000671));
        } else if (ea == 1) {
            var0.addElement(new Command(mResources.as, 11000672));
        }

        if (ea == 1 || ea == 2) {
            var0.addElement(new Command(mResources.ph, 11000674));
            var0.addElement(new Command(mResources.pg, 11000673));
        }

        GameCanvas.menu.showMenu(var0);
    }

    private static void dk() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.nl, 110471));
        var0.addElement(new Command(mResources.nw[4], 1100061));
        GameCanvas.menu.showMenu(var0);
    }

    private static void dl() {
        GameCanvas.setMaxTextLenght();
        Service.getInstance().d(arrItemConvert[0].indexUI, arrItemConvert[1].indexUI, arrItemConvert[2].indexUI);
    }

    private static void dm() {
        MyVector var0 = new MyVector();
        if (Char.getMyChar().ctypeClan == 3 || Char.getMyChar().ctypeClan == 4) {
            if (gk == 1) {
                var0.addElement(new Command(mResources.menuClan[8], 140044));
            }

            var0.addElement(new Command(mResources.menuClan[2], 140041));
            var0.addElement(new Command(mResources.menuClan[5], 140042));
            if (Char.getMyChar().ctypeClan == 4) {
                var0.addElement(new Command(mResources.menuClan[4], 140043));
            }

            var0.addElement(new Command(mResources.menuOtherChar[10] + ": " + (cb ? mResources.aw : mResources.av), 11000665));
        }

        GameCanvas.menu.showMenu(var0);
    }

    private static void dn() {
        Party var0;
        if ((var0 = (Party) z.elementAt(indexRow)) != null && !Char.getMyChar().charName.equals(var0.d)) {
            MyVector var1;
            (var1 = new MyVector()).addElement(new Command(mResources.menuOtherChar[6], 1108041, var0.d));
            var1.addElement(new Command(mResources.cc, 12002, var0.d));
            var1.addElement(new Command(mResources.oc[2], 110803, var0.d));
            GameCanvas.menu.showMenu(var1);
        }

    }

    private static void doa() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.nr, 110452));
        if (z.size() > 0) {
            var0.addElement(new Command(mResources.nq, 110451));
        }

        GameCanvas.menu.showMenu(var0);
    }

    private static void dp() {
        Member var0 = (Member) vClan.elementAt(indexRow);
        MyVector var1;
        (var1 = new MyVector()).addElement(new Command(mResources.ny[1], 140091, var0.name));
        var1.addElement(new Command(mResources.ny[2], 140092, var0.name));
        GameCanvas.menu.showMenu(var1);
    }

    private static void dq() {
        Member var0;
        if (indexRow >= 0 && indexRow < vClan.size() && (var0 = getMemberCurrentIndexRow()) != null && !var0.name.equals("")) {
            Service.getInstance().viewInfo(var0.name);
            id = false;
            getInstance().resetButton();
        }

    }

    private static void showMenuMemberClan() {
        Member member = getMemberCurrentIndexRow();
        MyVector _menu = new MyVector();
        if (Char.getMyChar().ctypeClan == 4 || Char.getMyChar().ctypeClan == 3) {
            if (TileMap.mapID >= 80 && TileMap.mapID <= 90) {
                _menu.addElement(new Command(mResources.menuClan[9], 140097, member.name));
            }
            if (TileMap.mapID == 98 || TileMap.mapID == 104) {
                _menu.addElement(new Command(mResources.menuClan[10], 140098, member.name));
            }
            _menu.addElement(new Command(mResources.menuClan[11], 140099, member.name));
        }
        if (Char.getMyChar().ctypeClan == 4) {
            if (member.type != 4) {
                if (member.type != 3 && member.type != 2) {
                    _menu.addElement(new Command(mResources.ot, 14009, member.name));
                } else {
                    _menu.addElement(new Command(mResources.ou, 140093, member.name));
                }
                if (TileMap.mapID >= 80 && TileMap.mapID <= 90) {
                    _menu.addElement(new Command(mResources.menuClan[6], 140095, member.name));
                }
                if (TileMap.mapID == 98 || TileMap.mapID == 104) {
                    _menu.addElement(new Command(mResources.menuClan[7], 140096, member.name));
                }
                _menu.addElement(new Command(mResources.trucXuat, 140094, member.name));
            }
            _menu.addElement(new Command(mResources.cauHinh, 14007));
        } else if (Char.getMyChar().ctypeClan == 3) {
            if (member.type != 4 && !member.name.equals(Char.getMyChar().charName)) {
                if (TileMap.mapID >= 80 && TileMap.mapID <= 90) {
                    _menu.addElement(new Command(mResources.menuClan[6], 140095, member.name));
                }

                if (TileMap.mapID == 98 || TileMap.mapID == 104) {
                    _menu.addElement(new Command(mResources.menuClan[7], 140096, member.name));
                }
                _menu.addElement(new Command(mResources.trucXuat, 140094, member.name));
            }

            _menu.addElement(new Command(mResources.cauHinh, 14007));
            _menu.addElement(new Command(mResources.menuClan[1], 14008, member.name));
        } else {
            _menu.addElement(new Command(mResources.cauHinh, 14007));
            _menu.addElement(new Command(mResources.menuClan[1], 14008, member.name));
        }

        GameCanvas.menu.showMenu(_menu);
    }

    private static boolean ds() {
        for (int var0 = 0; var0 < Char.getMyChar().arrItemBag.length; ++var0) {
            Item var1;
            if ((var1 = Char.getMyChar().arrItemBag[var0]) != null && var1.template.id == 279 && var1.template.level <= Char.getMyChar().cLevel) {
                return true;
            }
        }

        return false;
    }

    private static void d(String var0) {
        MyVector var1;
        (var1 = new MyVector()).addElement(new Command(mResources.menuOtherChar[6], 110805));
        var1.addElement(new Command(mResources.cc, 12002, var0));
        var1.addElement(new Command(mResources.of, 110791, var0));
        var1.addElement(new Command(mResources.ah, 14020, var0));
        var1.addElement(new Command(mResources.oc[2], 110803, var0));
        GameCanvas.menu.showMenu(var1);
    }

    private void openClanTab() {
        id = true;
        ie = true;
        gr = false;
        this.me = this.mf = null;
        b(175, 200);
        this.ab();
        super.right = this.mw;
        Service.getInstance().requestClanInfo();
    }

    private static void du() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.menuTongHopGiaToc[0], 11000661));
        var0.addElement(new Command(mResources.menuTongHopGiaToc[1], 11000662));
        var0.addElement(new Command(mResources.menuTongHopGiaToc[2], 11000663));
        var0.addElement(new Command(mResources.menuTongHopGiaToc[3], 11000664));
        var0.addElement(new Command(mResources.menuTongHopGiaToc[4], 11000666));
        GameCanvas.menu.showMenu(var0);
    }

    private void dv() {
        Char.getMyChar().charFocus = null;
        Char.ge = false;
        this.de = -1;
        ib = false;
        this.resetButton();
    }

    private void dw() {
        Char var1;
        if (!(var1 = (Char) vCharInMap.elementAt(indexRow)).isNhanban) {
            this.de = var1.charID;
            Char.getMyChar().mobFocus = null;
            Char.getMyChar().y();
            Char.getMyChar().itemFocus = null;
            Char.getMyChar();
            Char.ge = true;
            ib = false;
            Char.getMyChar().charFocus = var1;
        }

        this.resetButton();
    }

    private void dx() {
        if (indexMenu == 0) {
            indexMenu = 1;
        } else {
            indexMenu = 0;
        }

        indexRow = 0;
        this.gg = indexMenu;
    }

    private static void dy() {
        Party var0;
        if ((var0 = (Party) vParty.elementAt(indexRow)).f != null && var0.f != Char.getMyChar()) {
            Service.getInstance().viewInfo(var0.f.charName);
            gv = false;
            getInstance().resetButton();
        }

    }

    private static void dz() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.sapXep, 110221));
        var0.addElement(new Command(mResources.br, 11050));
        GameCanvas.menu.showMenu(var0);
    }

    private static void ea() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.sapXep, 11048));
        var0.addElement(new Command(mResources.bq, 11049));
        GameCanvas.menu.showMenu(var0);
    }

    private void eb() {
        if (Char.getMyChar().arrItemBag[indexSelect].template.type != 26 && Char.getMyChar().arrItemBag[indexSelect].template.id != 455 && Char.getMyChar().arrItemBag[indexSelect].template.id != 456) {
            if (Char.getMyChar().arrItemBag[indexSelect].template.id == 457) {
                GameCanvas.msgdlg.a(mResources.rp, (Command) null, new Command(mResources.am, 2), (Command) null);
            } else {
                GameCanvas.msgdlg.a(mResources.rq, (Command) null, new Command(mResources.am, 2), (Command) null);
            }
        } else {
            if (Char.getMyChar().arrItemBag[indexSelect].template.type == 26 && Char.getMyChar().arrItemBag[indexSelect].template.id < 10) {
                GameCanvas.setText(mResources.rt);
                return;
            }

            short var1 = 0;
            int var2 = 0;
            boolean var3 = arrItemUpPeal[12] != null;

            int var4;
            for (var4 = 0; var4 < arrItemUpPeal.length; ++var4) {
                if (arrItemUpPeal[var4] != null && arrItemUpPeal[var4].template.type != 26) {
                    var1 = arrItemUpPeal[var4].template.id;
                    ++var2;
                }
            }

            for (var4 = 0; var4 < arrItemUpPeal.length; ++var4) {
                if (arrItemUpPeal[var4] == null) {
                    if (Char.getMyChar().arrItemBag[indexSelect].template.type == 26) {
                        if (arrItemUpPeal[12] == null) {
                            arrItemUpPeal[12] = Char.getMyChar().arrItemBag[indexSelect];
                            Char.getMyChar().arrItemBag[indexSelect] = null;
                        } else {
                            Item var5 = Char.getMyChar().arrItemBag[indexSelect];
                            Char.getMyChar().arrItemBag[indexSelect] = null;
                            Char.getMyChar().arrItemBag[arrItemUpPeal[12].indexUI] = arrItemUpPeal[12];
                            arrItemUpPeal[12] = var5;
                        }
                    } else if (var1 > 0 && Char.getMyChar().arrItemBag[indexSelect].template.id != var1) {
                        GameCanvas.setText(mResources.ru);
                    } else if ((!var3 || var2 < 3) && var2 < 9) {
                        arrItemUpPeal[var4] = Char.getMyChar().arrItemBag[indexSelect];
                        Char.getMyChar().arrItemBag[indexSelect] = null;
                    } else {
                        GameCanvas.setText(mResources.rw);
                    }

                    super.left = super.center = null;
                    this.ab();
                    return;
                }
            }

            GameCanvas.msgdlg.a(mResources.js, (Command) null, new Command(mResources.am, 2), (Command) null);
        }

        GameCanvas.currentDialog = GameCanvas.msgdlg;
    }

    private void ec() {
        if (Char.getMyChar().arrItemBag[indexSelect].template.type != 26) {
            GameCanvas.msgdlg.a(mResources.jl, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        } else {
            for (int var1 = 0; var1 < arrItemUpPeal.length; ++var1) {
                if (arrItemUpPeal[var1] == null) {
                    arrItemUpPeal[var1] = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                    super.left = super.center = null;
                    this.ab();
                    return;
                }
            }

            GameCanvas.msgdlg.a(mResources.js, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        }

    }

    private void ed() {
        Item var1 = getCurrentItemSelectByTypeUI(11);
        arrItemUpPeal[indexSelect] = null;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        super.left = super.center = null;
        this.ab();
    }

    private static void ee() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.boRa, 111071));

        for (int var1 = 0; var1 < arrItemUpPeal.length; ++var1) {
            if (arrItemUpPeal[var1] != null) {
                var0.addElement(new Command(mResources.fc, 1600));
                break;
            }
        }

        GameCanvas.menu.showMenu(var0);
    }

    private static void ef() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.boRa, 111071));

        for (int var1 = 0; var1 < arrItemUpPeal.length; ++var1) {
            if (arrItemUpPeal[var1] != null) {
                var0.addElement(new Command(mResources.fc, 11062));
                break;
            }
        }

        GameCanvas.menu.showMenu(var0);
    }

    private void eg() {
        try {
            int var1;
            int var2;
            if (ii) {
                if (!Char.getMyChar().arrItemBag[indexSelect].isTypeBody() && !Char.getMyChar().arrItemBag[indexSelect].isTypeMounts() && Char.getMyChar().arrItemBag[indexSelect].template.id != 455 && Char.getMyChar().arrItemBag[indexSelect].template.id != 456 && Char.getMyChar().arrItemBag[indexSelect].template.id != 457) {
                    GameCanvas.msgdlg.a(mResources.rx, (Command) null, new Command(mResources.am, 2), (Command) null);
                    GameCanvas.currentDialog = GameCanvas.msgdlg;
                } else if (Char.getMyChar().arrItemBag[indexSelect].template.id != 455 && Char.getMyChar().arrItemBag[indexSelect].template.id != 456 && Char.getMyChar().arrItemBag[indexSelect].template.id != 457) {
                    ItemOption var7 = null;

                    for (var1 = 0; var1 < Char.getMyChar().arrItemBag[indexSelect].options.size() && (var7 = (ItemOption) Char.getMyChar().arrItemBag[indexSelect].options.elementAt(var1)).optionTemplate.id != 85; ++var1) {
                        var7 = null;
                    }

                    if (var7 != null && var7.param >= 9) {
                        GameCanvas.setText(mResources.sg);
                    } else {
                        if (itemSplit == null) {
                            itemSplit = Char.getMyChar().arrItemBag[indexSelect];
                            Char.getMyChar().arrItemBag[indexSelect] = null;
                        } else {
                            Item var6 = Char.getMyChar().arrItemBag[indexSelect];
                            Char.getMyChar().arrItemBag[indexSelect] = null;
                            Char.getMyChar().arrItemBag[itemSplit.indexUI] = itemSplit;
                            itemSplit = var6;
                        }

                        Service.getInstance().requestItemInfo(itemSplit.typeUI, itemSplit.indexUI);
                    }
                } else {
                    if (arrItemSplit == null) {
                        arrItemSplit = new Item[24];
                    }

                    for (var2 = 0; var2 < arrItemSplit.length; ++var2) {
                        if (arrItemSplit[var2] == null) {
                            arrItemSplit[var2] = Char.getMyChar().arrItemBag[indexSelect];
                            Char.getMyChar().arrItemBag[indexSelect] = null;
                            super.left = super.center = null;
                            this.ab();
                            return;
                        }

                        if (var2 == arrItemSplit.length - 1) {
                            GameCanvas.setText(mResources.js);
                        }
                    }
                }
            } else if (Char.getMyChar().arrItemBag[indexSelect].isTypeBody() && Char.getMyChar().arrItemBag[indexSelect].upgrade > 0) {
                if (itemSplit == null) {
                    itemSplit = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                } else {
                    Item var3 = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                    Char.getMyChar().arrItemBag[itemSplit.indexUI] = itemSplit;
                    itemSplit = var3;
                }

                if (itemSplit != null) {
                    var2 = 0;
                    if (itemSplit.isTypeClothe()) {
                        for (var1 = 0; var1 < itemSplit.upgrade; ++var1) {
                            var2 += upClothe[var1];
                        }
                    } else if (itemSplit.isTypeAdorn()) {
                        for (var1 = 0; var1 < itemSplit.upgrade; ++var1) {
                            var2 += upAdorn[var1];
                        }
                    } else if (itemSplit.isTypeWeapon()) {
                        for (var1 = 0; var1 < itemSplit.upgrade; ++var1) {
                            var2 += upWeapon[var1];
                        }
                    }

                    var2 /= 2;
                    var1 = 0;
                    arrItemSplit = new Item[24];

                    for (int var5 = crystals.length - 1; var5 >= 0; --var5) {
                        if (var2 >= crystals[var5]) {
                            arrItemSplit[var1] = new Item();
                            arrItemSplit[var1].typeUI = 3;
                            arrItemSplit[var1].template = ItemTemplateManager.get((short) var5);
                            arrItemSplit[var1].isLock = itemSplit.isLock;
                            arrItemSplit[var1].expires = -1L;
                            var2 -= crystals[var5];
                            ++var5;
                            ++var1;
                        }
                    }
                }

                super.left = super.center = null;
                this.ab();
            } else {
                GameCanvas.msgdlg.a(mResources.jq, (Command) null, new Command(mResources.am, 2), (Command) null);
                GameCanvas.currentDialog = GameCanvas.msgdlg;
            }
        } catch (Exception var5) {
            GameCanvas.msgdlg.a(mResources.rx, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        }

    }

    private void eh() {
        MyVector var1;
        (var1 = new MyVector()).addElement(this.mp);
        if (itemSplit != null) {
            var1.addElement(new Command(mResources.fc, 111031));
        }

        GameCanvas.menu.showMenu(var1);
    }

    private void ei() {
        if (Char.getMyChar().arrItemBag[indexSelect].isTypeBody()) {
            if (Char.getMyChar().arrItemBag[indexSelect].template.level >= 10 && Char.getMyChar().arrItemBag[indexSelect].template.type < 10) {
                if (Char.getMyChar().arrItemBag[indexSelect].upgrade >= Char.getMyChar().arrItemBag[indexSelect].q()) {
                    GameCanvas.msgdlg.a(mResources.jp, (Command) null, new Command(mResources.am, 2), (Command) null);
                    GameCanvas.currentDialog = GameCanvas.msgdlg;
                } else {
                    if (itemUpGrade == null) {
                        itemUpGrade = Char.getMyChar().arrItemBag[indexSelect];
                        Char.getMyChar().arrItemBag[indexSelect] = null;
                    } else {
                        Item var3 = Char.getMyChar().arrItemBag[indexSelect];
                        Char.getMyChar().arrItemBag[indexSelect] = null;
                        Char.getMyChar().arrItemBag[itemUpGrade.indexUI] = itemUpGrade;
                        itemUpGrade = var3;
                    }

                    super.left = super.center = null;
                    this.ab();
                }
            } else {
                GameCanvas.msgdlg.a(mResources.jo, (Command) null, new Command(mResources.am, 2), (Command) null);
                GameCanvas.currentDialog = GameCanvas.msgdlg;
            }
        } else if (Char.getMyChar().arrItemBag[indexSelect].template.type != 26 && Char.getMyChar().arrItemBag[indexSelect].template.type != 28) {
            GameCanvas.msgdlg.a(mResources.jm, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        } else {
            int var1;
            if (Char.getMyChar().arrItemBag[indexSelect].template.type == 28) {
                for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                    if (arrItemUpGrade[var1] != null && arrItemUpGrade[var1].template.type == 28) {
                        Item var2 = Char.getMyChar().arrItemBag[indexSelect];
                        Char.getMyChar().arrItemBag[indexSelect] = null;
                        int var10001 = arrItemUpGrade[var1].indexUI;
                        Char.getMyChar().arrItemBag[var10001] = arrItemUpGrade[var1];
                        arrItemUpGrade[var1] = var2;
                        return;
                    }
                }
            }

            for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                if (arrItemUpGrade[var1] == null) {
                    arrItemUpGrade[var1] = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                    super.left = super.center = null;
                    this.ab();
                    return;
                }
            }

            GameCanvas.msgdlg.a(mResources.js, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        }

    }

    private void o(int var1) {
        if (arrItemConvert[var1] == null) {
            arrItemConvert[var1] = Char.getMyChar().arrItemBag[indexSelect];
            Char.getMyChar().arrItemBag[indexSelect] = null;
        } else {
            Item var2 = Char.getMyChar().arrItemBag[indexSelect];
            Char.getMyChar().arrItemBag[indexSelect] = null;
            int var10001 = arrItemConvert[var1].indexUI;
            Char.getMyChar().arrItemBag[var10001] = arrItemConvert[var1];
            arrItemConvert[var1] = var2;
        }

        super.left = super.center = null;
        this.ab();
    }

    private void ej() {
        if (Char.getMyChar().arrItemBag[indexSelect].isTypeBody()) {
            if (Char.getMyChar().arrItemBag[indexSelect].upgrade > 0) {
                this.o(0);
            } else {
                this.o(1);
            }
        } else if (Char.getMyChar().arrItemBag[indexSelect].template.id != 269 && Char.getMyChar().arrItemBag[indexSelect].template.id != 270 && Char.getMyChar().arrItemBag[indexSelect].template.id != 271) {
            GameCanvas.msgdlg.a(mResources.jn, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        } else {
            this.o(2);
        }

    }

    private void ek() {
        Item var1 = arrItemConvert[2];
        arrItemConvert[2] = null;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        super.left = super.center = null;
        this.ab();
    }

    private void el() {
        Item var1 = arrItemConvert[indexSelect];
        arrItemConvert[indexSelect] = null;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        super.left = super.center = null;
        this.ab();
    }

    private void em() {
        Item var1 = getCurrentItemSelectByTypeUI(10);
        arrItemUpGrade[indexSelect] = null;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        super.left = super.center = null;
        this.ab();
    }

    private static void en() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.boRa, 111001));
        if (itemUpGrade != null) {
            for (int var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                if (arrItemUpGrade[var1] != null) {
                    var0.addElement(new Command(mResources.fc, 110981));
                    break;
                }
            }
        }

        GameCanvas.menu.showMenu(var0);
    }

    private void eo() {
        MyVector var1;
        (var1 = new MyVector()).addElement(this.mo);

        for (int var2 = 0; var2 < arrItemConvert.length; ++var2) {
            if (arrItemConvert[var2] == null) {
                super.left = null;
                break;
            }

            if (var2 == arrItemConvert.length - 1) {
                var1.addElement(new Command(mResources.fc, 140131));
            }
        }

        GameCanvas.menu.showMenu(var1);
    }

    private void ep() {
        MyVector var1;
        (var1 = new MyVector()).addElement(this.mn);

        for (int var2 = 0; var2 < arrItemUpGrade.length; ++var2) {
            if (arrItemUpGrade[var2] != null) {
                var1.addElement(new Command(mResources.fc, 110981));
                break;
            }
        }

        GameCanvas.menu.showMenu(var1);
    }

    private static void c(Item var0) {
        MyVector var1;
        (var1 = new MyVector()).addElement(new Command(mResources.by, 110921, var0));
        var1.addElement(new Command(mResources.bz, 110922, var0));
        GameCanvas.menu.showMenu(var1);
    }

    private static void eq() {
        indexRow = 0;
        scrMain.a();
        if (jp == 0) {
            jp = 1;
        } else {
            jp = 0;
        }

    }

    private void er() {
        indexMenu = 0;
        isPaintInfoMe = false;
        super.left = this.jz;
        super.right = this.gf;
        super.center = null;
        System.gc();
        this.resetButton();
        this.bc();
    }

    private static void es() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110851));
        var0.addElement(new Command(mResources.bz, 110852));
        GameCanvas.menu.showMenu(var0);
    }

    private static void et() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.de, 110841));
        var0.addElement(new Command(mResources.df, 110842));
        GameCanvas.menu.showMenu(var0);
    }

    private static void leftCmdEquipment() {
        MyVector menu = new MyVector();
        if (currentCharViewInfo.charID == Char.getMyChar().charID && Char.getMyChar().arrItemBody[indexSelect] != null) {
            menu.addElement(new Command(mResources.catVaoHanhTrang, 110821));
        }
        menu.addElement(new Command(mResources.trangBi2, 110823));
        GameCanvas.menu.showMenu(menu);
    }

    private static void leftCmdFashion() {
        MyVector menu = new MyVector();
        if (currentCharViewInfo.charID == Char.getMyChar().charID && Char.getMyChar().arrItemBody[indexSelect + 16] != null) {
            menu.addElement(new Command(mResources.catVaoHanhTrang, 110821));
        }
        menu.addElement(new Command(mResources.trangBi1, 110822));
        GameCanvas.menu.showMenu(menu);
    }

    private static void ev() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.px[0], 110811));
        var0.addElement(new Command(mResources.px[1], 110812));
        GameCanvas.menu.showMenu(var0);
    }

    private static void e(String var0) {
        MyVector var1;
        (var1 = new MyVector()).addElement(new Command(mResources.oc[0], 110801));
        var1.addElement(new Command(mResources.oc[1], 110802));
        var1.addElement(new Command(mResources.oc[2], 110803, var0));
        var1.addElement(new Command(mResources.menuOtherChar[7], 12002, var0));
        var1.addElement(new Command(mResources.menuOtherChar[6], 110804));
        GameCanvas.menu.showMenu(var1);
    }

    private static void ew() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.de, 140011));
        var0.addElement(new Command(mResources.df, 140012));
        GameCanvas.menu.showMenu(var0);
    }

    private static void f(String var0) {
        GameCanvas.a(mResources.oi, new Command(mResources.bn, 1107921, var0), new Command(mResources.ca, 1));
    }

    private static void ex() {
        Friend var0 = (Friend) aa.elementAt(indexRow);
        MyVector var1;
        (var1 = new MyVector()).addElement(new Command(mResources.menuOtherChar[6], 1108041, var0.a));
        var1.addElement(new Command(mResources.cc, 12002, var0.a));
        if (var0.b == 4) {
            var1.addElement(new Command(mResources.di, 1107931, var0.a));
            var1.addElement(new Command(mResources.huy, 1107932, var0.a));
        } else {
            var1.addElement(new Command(mResources.of, 110791, var0.a));
            var1.addElement(new Command(mResources.ah, 14020, var0.a));
            var1.addElement(new Command(mResources.bb, 110792, var0.a));
        }

        GameCanvas.menu.showMenu(var1);
    }

    private void a(byte var1) {
        if (indexRow >= 0 && indexRow < ab.size()) {
            try {
                Ranked var2;
                String var4;
                if ((var2 = (Ranked) ab.elementAt(indexRow)) != null) {
                    var4 = var2.name;
                } else {
                    var4 = "raned=null";
                }

                Service.getInstance().a(var1, var4);
                this.resetButton();
                return;
            } catch (Exception var4) {
            }
        }

    }

    private static void ey() {
        if (indexRow >= 0 && indexRow < ab.size()) {
            int var0 = ((DunItem) ab.elementAt(indexRow)).a;
            Service.getInstance().ac(var0);
        }

    }

    private static void ez() {
        Friend var0 = (Friend) ad.elementAt(indexRow);
        MyVector var1;
        (var1 = new MyVector()).addElement(new Command(mResources.menuOtherChar[6], 1108041, var0.a));
        var1.addElement(new Command(mResources.cc, 12002, var0.a));
        var1.addElement(new Command(mResources.of, 11076, var0.a));
        var1.addElement(new Command(mResources.ah, 14020, var0.a));
        var1.addElement(new Command(mResources.bb, 11077, var0.a));
        GameCanvas.menu.showMenu(var1);
    }

    private static void g(String var0) {
        GameCanvas.a(mResources.oi, new Command(mResources.bn, 110771, var0), new Command(mResources.ca, 1));
    }

    private void fa() {
        ce = false;
        this.iy = null;
        this.ix = null;
        super.center = null;
        this.resetButton();
    }

    private void a(short var1, String var2) {
        NinjaUtil.a(var2, var1);
        ce = false;
        this.iy = null;
        this.ix = null;
        super.center = null;
        this.resetButton();
    }

    private static void fb() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.bu, 110721));
        var0.addElement(new Command(mResources.bv, 110722));
        var0.addElement(new Command(mResources.bw, 110723));
        GameCanvas.menu.showMenu(var0);
    }

    private static void a(Party var0) {
        MyVector var1;
        (var1 = new MyVector()).addElement(new Command(mResources.nm, 110701));
        if (!var0.e) {
            var1.addElement(new Command(mResources.no, 110702));
        } else {
            var1.addElement(new Command(mResources.np, 110703));
        }

        GameCanvas.menu.showMenu(var1);
    }

    private void fc() {
        ce = false;
        this.iy = null;
        this.ix = null;
        super.center = null;
        this.resetButton();
    }

    private void h(String var1) {
        NinjaUtil.b(var1);
        ce = false;
        this.iy = null;
        this.ix = null;
        super.center = null;
        this.resetButton();
    }

    private static void fd() {
        GameCanvas.setMaxTextLenght();
        Service.getInstance().upgradeItem(itemUpGrade, arrItemUpGrade, hx);
    }

    private static void d(Item var0) {
        GameCanvas.setMaxTextLenght();
        Service.getInstance().saleItem(var0.indexUI, 1);
    }

    private void fe() {
        indexMenu = 0;
        isPaintInfoMe = false;
        this.resetButton();
        if (currentCharViewInfo.charID == Char.getMyChar().charID) {
            this.bc();
        }

    }

    private void ff() {
        if (this.kf >= 0 && this.kf < arrSkill.length) {
            Skill var1 = arrSkill[this.kf];
            this.a(var1, false);
        }

    }

    private static void e(Item var0) {
        String var1;
        if (!(var1 = GameCanvas.ak.tfInput.getText()).trim().equals("")) {
            int var3;
            try {
                var3 = Integer.parseInt(var1);
            } catch (Exception var4) {
                GameCanvas.setMaxTextLenght();
                return;
            }

            if (var3 <= 0) {
                GameCanvas.setMaxTextLenght();
                return;
            }

            if (var3 > var0.quantity) {
                GameCanvas.setText(mResources.kw);
                return;
            }

            GameCanvas.setMaxTextLenght();
            GameCanvas.a(mResources.km, new Command(mResources.bn, 11058, var0), new Command(mResources.ca, 1));
        }

    }

    private static void f(Item var0) {
        String var1;
        if ((var1 = GameCanvas.ak.tfInput.getText()).trim().equals("")) {
            GameCanvas.msgdlg.a(mResources.kr, (Command) null, new Command(mResources.am, 1), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        } else {
            int var3;
            try {
                var3 = Integer.parseInt(var1);
            } catch (Exception var4) {
                GameCanvas.setMaxTextLenght();
                return;
            }

            if (var3 <= 0) {
                GameCanvas.setMaxTextLenght();
            } else {
                Service.getInstance().buyItem(var0.typeUI, var0.indexUI, var3);
                GameCanvas.setMaxTextLenght();
            }
        }

    }

    private void fg() {
        int var1 = indexSelect;
        this.resetButton();
        this.iv = var1;
        Service.getInstance().openUIZone();
    }

    private static void g(Item var0) {
        MyVector var1 = new MyVector();

        for (int var2 = 1; var2 < mResources.arrKhaDiLenh[3].length; ++var2) {
            var1.addElement(new Command(mResources.arrKhaDiLenh[3][var2], 110531, var0));
        }

        GameCanvas.menu.showMenu(var1);
    }

    private static void fh() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.or + (ChatManager.c ? mResources.av : mResources.aw), 120061));
        var0.addElement(new Command(mResources.os + (ChatManager.d ? mResources.av : mResources.aw), 120062));
        GameCanvas.menu.showMenu(var0);
    }

    private static void fi() {
        MyVector var0 = new MyVector();

        for (int var1 = 0; var1 < ChatManager.getInstance().a.size(); ++var1) {
            ChatTab var2 = (ChatTab) ChatManager.getInstance().a.elementAt(var1);
            var0.addElement(new Command(var2.b, 12001, new Integer(var1)));
        }

        var0.addElement(new Command(mResources.pv, 12006));
        var0.addElement(new Command(mResources.pw, 12008));
        GameCanvas.menu.showMenu(var0);
        cc = true;
    }

    private void fj() {
        ChatTab var1 = ChatManager.getInstance().e();
        ce = true;
        ck = true;
        this.iw = true;
        b(175, 200);
        if (GameCanvas.height - ex < 40 && !GameCanvas.isTouch) {
            ex -= 52;
        }

        super.right = new Command(mResources.am, 11066);
        super.left = super.center = null;
        if (!GameCanvas.isTouch) {
            fk();
        } else {
            super.left = new Command(mResources.cc, 12005);
        }

        if (var1.a == 2) {
            super.center = new Command(mResources.an, 120051, var1);
        }

        ChatTextField.a().f = null;
        this.iy = var1.b;
        this.ix = var1.c;
        ChatManager.getInstance().e(var1.b);
        if (var1.a == 1) {
            ChatManager.f = false;
        }

        if (var1.a == 4) {
            ChatManager.e = false;
        }

        this.ah();
    }

    private static void fk() {
        ChatTab var0;
        if ((var0 = ChatManager.getInstance().e()).a == 0) {
            ChatTextField.a().a(mResources.on[0]);
        }

        if (var0.a == 1) {
            ChatTextField.a().a(mResources.oo[0]);
        }

        if (var0.a == 2) {
            ChatTextField.a().a(var0.b);
        }

        if (var0.a == 3) {
            ChatTextField.a().a(mResources.op[0]);
        }

        if (var0.a == 4) {
            ChatTextField.a().a(mResources.oq[0]);
        }

    }

    private void fl() {
        try {
            GameMidlet.instance.platformRequest(this.nt);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    private static void fm() {
        GameCanvas.setMaxTextLenght();
        Service.getInstance().useItem(indexSelect);
    }

    private static void fn() {
        Service.getInstance().boxSort();
    }

    private static void fo() {
        Service.getInstance().s();
    }

    private static void fp() {
        indexRow = 0;
        Service.getInstance().d();
    }

    private static void fq() {
        Party var0;
        if (indexRow >= 0 && indexRow < z.size() && (var0 = (Party) z.elementAt(indexRow)) != null) {
            Service.getInstance().pleaseInputParty(var0.d);
        }

    }

    private static void p(int var0) {
        switch (var0) {
            case 1:
                Service.getInstance().z(0);
                return;
            case 2:
                Service.getInstance().z(1);
                return;
            case 3:
                Service.getInstance().z(3);
            default:
        }
    }

    private static void fr() {
        String var0;
        if ((var0 = GameCanvas.ak.tfInput.getText()).trim().equals("")) {
            GameCanvas.setMaxTextLenght();
        } else {
            int var2;
            try {
                var2 = Integer.parseInt(var0);
            } catch (Exception var3) {
                GameCanvas.setMaxTextLenght();
                return;
            }

            if (var2 <= 0) {
                GameCanvas.setMaxTextLenght();
            } else if (Char.getMyChar().xu != 0 && var2 <= Char.getMyChar().xu) {
                Service.getInstance().boxCoinIn(var2);
                GameCanvas.setMaxTextLenght();
            } else {
                GameCanvas.setText(mResources.ku);
            }
        }

    }

    private void fs() {
        String var1;
        if ((var1 = GameCanvas.ak.tfInput.getText()).trim().equals("")) {
            GameCanvas.setMaxTextLenght();
        } else {
            int var4;
            var4 = Integer.parseInt(var1);

            if (var4 <= 0) {
                GameCanvas.setMaxTextLenght();
            } else if (Char.getMyChar().xu != 0 && var4 <= Char.getMyChar().xu) {
                this.db += var4;
                Char var10000 = Char.getMyChar();
                var10000.xu -= var4;
                GameCanvas.setMaxTextLenght();
            } else {
                GameCanvas.setText(mResources.kt);
            }
        }

    }

    private static void ft() {
        String var0;
        if ((var0 = GameCanvas.ak.tfInput.getText()).trim().equals("")) {
            GameCanvas.setMaxTextLenght();
        } else {
            int var2;
            try {
                var2 = Integer.parseInt(var0);
            } catch (Exception var3) {
                GameCanvas.setMaxTextLenght();
                return;
            }

            if (var2 <= 0) {
                GameCanvas.setMaxTextLenght();
            } else if (Char.getMyChar().bs != 0 && var2 <= Char.getMyChar().bs) {
                Service.getInstance().boxCoinOut(var2);
                GameCanvas.setMaxTextLenght();
            } else {
                GameCanvas.setText(mResources.kv);
            }
        }

    }

    private static void fu() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.nv[0], 11000651));
        var0.addElement(new Command(mResources.nv[1], 11000652));
        var0.addElement(new Command(mResources.nv[3], 11000653));
        GameCanvas.menu.showMenu(var0);
    }

    private static void fv() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.nw[6], 1100067));
        var0.addElement(new Command(mResources.nw[3], 1100062));
        var0.addElement(new Command(mResources.nw[1], 1100063));
        var0.addElement(new Command(mResources.nw[2], 1100064));
        var0.addElement(new Command(mResources.nw[0], 1100065));
        var0.addElement(new Command(mResources.nw[8], 11000601));
        var0.addElement(new Command(mResources.nw[9], 11000602));
        var0.addElement(new Command(mResources.nw[10], 11000603));
        var0.addElement(new Command(mResources.nw[11], 11000604));
        var0.addElement(new Command(mResources.nw[7], 1100068));
        GameCanvas.menu.showMenu(var0);
    }

    private void fw() {
        this.ax();
        if (super.right != null) {
            super.right.a();
        }

    }

    private void fx() {
        this.ax();
        if (super.left != null) {
            super.left.a();
        }

    }

    private void actionToOtherPlayer(int var1) {
        if (Char.getMyChar().charFocus != null && !Char.getMyChar().charFocus.isNhanban) {
            switch (var1) {
                case 1:
                    Service.getInstance().addParty(Char.getMyChar().charFocus.charName);
                    return;
                case 2:
                    Service.getInstance().tradeInvite(Char.getMyChar().charFocus.charID);
                    return;
                case 3:
                    Service.getInstance().testInvite(Char.getMyChar().charFocus.charID);
                    return;
                case 4:
                    Service.getInstance().addCuuSat(Char.getMyChar().charFocus.charID);
                    return;
                case 5:
                    this.g(Char.getMyChar().charFocus.charID);
                    return;
                case 6:
                    Service.getInstance().addFriend(Char.getMyChar().charFocus.charName);
                    return;
                case 7:
                    Service.getInstance().viewInfo(Char.getMyChar().charFocus.charName);
                    getInstance().resetButton();
                    return;
                case 8:
                    Service.getInstance().clanInvite(Char.getMyChar().charFocus.charID);
                    return;
                case 9:
                    Service.getInstance().clanPlease(Char.getMyChar().charFocus.charID);
                    return;
                case 10:
                    Char.isAResuscitate = !Char.isAResuscitate;
                    Char.aCID = Char.getMyChar().charFocus.charID;
            }
        }

    }

    public final void g(int var1) {
        if (System.currentTimeMillis() - this.ov > 500L) {
            Service.getInstance().buffLive(var1);
            this.ov = System.currentTimeMillis();
        }

        if ((TileMap.a(Char.getMyChar().cx, Char.getMyChar().cy) & 2) == 2) {
            Char.getMyChar().a(skillPaints[49], 0);
        } else {
            Char.getMyChar().a(skillPaints[49], 1);
        }

    }

    private static void fy() {
        GameCanvas.pleaseWait();
        ChatManager.f();
        Session_ME.getInstance().b();
        RMS.i = 9999;
        RMS.j = System.currentTimeMillis() + 1000L;
        RMS.k = true;
    }

    private static void fz() {
        GameCanvas.a(mResources.dialogExit, new Command(mResources.bn, 1100041), new Command(mResources.ca, 1));
    }

    private static void ga() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.nj[1], 110381));
        var0.addElement(new Command(mResources.nj[2], 110382));
        var0.addElement(new Command(mResources.nj[3], 110383));
        GameCanvas.menu.showMenu(var0);
    }

    private static void gb() {
        Service.getInstance().bagSort();
    }

    private void gc() {
        Item var1 = arrItemTradeMe[indexSelect];
        arrItemTradeMe[indexSelect] = null;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        super.left = super.center = null;
        this.ab();
    }

    private void gd() {
        Item var1 = itemSplit;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        itemSplit = null;
        if (!ii && !cl && !ij && arrItemSplit != null) {
            for (int var2 = 0; var2 < arrItemSplit.length; ++var2) {
                arrItemSplit[var2] = null;
            }
        }

        super.left = super.center = null;
        this.ab();
    }

    private void ge() {
        Item var1 = itemUpGrade;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        itemUpGrade = null;
        super.left = super.center = null;
        this.ab();
    }

    private void gf() {
        Item var1 = arrItemConvert[indexSelect];
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        arrItemConvert[indexSelect] = null;
        super.left = super.center = null;
        this.ab();
    }

    private void gg() {
        Service.getInstance().j();
        this.cz = 2;
        if (getInstance().cz >= 2 && getInstance().da >= 2) {
            InfoDlg.b();
        }

    }

    private void gh() {
        Service.getInstance().tradeLock(this.db, arrItemTradeMe);
        this.cz = 1;
        if (getInstance().cz == 1 && getInstance().da == 1) {
            getInstance().dd = (int) (System.currentTimeMillis() / 1000L + 5L);
        }

        super.left = this.mj;
    }

    private void gi() {
        Item var1 = arrItemTradeMe[indexSelect];
        this.getItemInfo((int) 3, (Item) var1);
    }

    private void gj() {
        if (Char.getMyChar().arrItemBag[indexSelect].isLock) {
            GameCanvas.msgdlg.a(mResources.jj, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        } else {
            for (int var1 = 0; var1 < arrItemTradeMe.length; ++var1) {
                if (arrItemTradeMe[var1] == null) {
                    arrItemTradeMe[var1] = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                    super.left = super.center = null;
                    this.ab();
                    return;
                }
            }

            GameCanvas.msgdlg.a(mResources.jr, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        }

    }

    private void gk() {
        MyVector var1;
        (var1 = new MyVector()).addElement(this.mt);
        var1.addElement(this.mq);
        GameCanvas.menu.showMenu(var1);
    }

    private void gl() {
        MyVector var1;
        (var1 = new MyVector()).addElement(this.mr);
        if (this.cz == 0) {
            var1.addElement(this.mi);
        } else if (this.cz == 1 && this.da > 0 && (long) this.dd - System.currentTimeMillis() / 1000L <= 0L) {
            var1.addElement(this.mj);
        }

        GameCanvas.menu.showMenu(var1);
    }

    private static void gm() {
        try {
            Item var0;
            if ((var0 = getCurrentItemSelectByTypeUI(3)).template.gender != 2 && var0.template.gender != Char.getMyChar().cgender) {
                GameCanvas.msgdlg.a(mResources.lk, (Command) null, new Command(mResources.am, 1), (Command) null);
                GameCanvas.currentDialog = GameCanvas.msgdlg;
            } else if (var0.template.level > Char.getMyChar().cLevel) {
                GameCanvas.msgdlg.a(mResources.lm, (Command) null, new Command(mResources.am, 1), (Command) null);
                GameCanvas.currentDialog = GameCanvas.msgdlg;
            } else if ((var0.isTypeBody() || var0.isTypeMounts()) && !var0.isLock) {
                GameCanvas.a(mResources.kl, new Command(mResources.bn, 11051, (Object) null), new Command(mResources.ca, 1));
            } else if (var0.template.id != 35 && var0.template.id != 37) {
                if (var0.template.id == 514) {
                    GameCanvas.al.a("Đến: ", "Lời chúc: ");
                    GameCanvas.al.a("Chúc tết", new Command(mResources.am, GameCanvas.instance, 8882, (Object) null), new Command("Gửi", GameCanvas.instance, 1608, (Object) null), 0, 0);
                } else if (var0.template.id == 515) {
                    GameCanvas.al.a("Đến: ", "Lời chúc: ");
                    GameCanvas.al.a("Chúc tết", new Command(mResources.am, GameCanvas.instance, 8882, (Object) null), new Command("Gửi", GameCanvas.instance, 16081, (Object) null), 0, 0);
                } else {
                    Service.getInstance().useItem(indexSelect);
                }
            } else {
                MyVector var1 = new MyVector();

                for (int var2 = 0; var2 < 3; ++var2) {
                    var1.addElement(new Command(mResources.arrKhaDiLenh[var2][0], 11052, var0));
                }

                var1.addElement(new Command(mResources.arrKhaDiLenh[3][0], 11053, var0));
                var1.addElement(new Command(mResources.arrKhaDiLenh[4][0], 11054));
                GameCanvas.menu.showMenu(var1);
            }
        } catch (Exception var3) {
        }

    }

    private static void useAllItem() {
        Item item;
        if (UseAllItem.isRunning()) {
            UseAllItem.stop();
            GameScr.chatPopup("Đã tắt Mở all");
            return;
        }

        if ((item = getCurrentItemSelectByTypeUI(3)) != null) {
            if (item.template.level > Char.getMyChar().cLevel) {
                GameCanvas.msgdlg.a(mResources.ks, (Command) null, new Command(mResources.ab, 1), (Command) null);
                GameCanvas.currentDialog = GameCanvas.msgdlg;
                return;
            }

            UseAllItem.start(item);
            GameScr.chatPopup("Đã bật Mở all");
        }

    }

    private static void showInputDialogSplitItem() {
        if (Char.getMyChar().arrItemBag[indexSelect] != null && Char.getMyChar().arrItemBag[indexSelect].quantity > 1) {
            GameCanvas.ak.a(mResources.pq, new Command(mResources.okey, GameCanvas.instance, 88835, String.valueOf(indexSelect)), 1);
        }

    }

    private static void showInputDialogSplitItemAll() {
        if (Char.getMyChar().arrItemBag[indexSelect] != null && Char.getMyChar().arrItemBag[indexSelect].quantity > 1) {
            GameCanvas.ak.a(mResources.pq, new Command(mResources.okey, GameCanvas.instance, 888351, String.valueOf(indexSelect)), 1);
        }

    }

    private static void gp() {
        if (Char.getMyChar().arrItemBag[indexSelect] != null) {
            if (Char.getMyChar().arrItemBag[indexSelect].isLock) {
                InfoMe.a(mResources.my);
                return;
            }

            Service.getInstance().throwItem(indexSelect);
        }

    }

    private void gq() {
        if (gk == 1) {
            Item var1 = getCurrentItemSelectByTypeUI(3);
            if (GameCanvas.isMinWidth320) {
                this.a(var1, this.ol);
                return;
            }

            this.a(var1, (Command) null);
        }

    }

    private void extendMenuUseItem() {
        MyVector var1;
        (var1 = new MyVector()).addElement(this.ol);
        Item item = Char.getMyChar().arrItemBag[indexSelect];
        if (item != null) {
            if (Code.hasTuDungSimple(item.template.id)) {
                var1.addElement(new Command("Tắt Tự Dùng", 110263));
            } else {
                var1.addElement(new Command("Tự Dùng", 110262));
            }
            var1.addElement(new Command(AutoUseItem.contains(item.template.id) ? "Del List Tự Dùng" : "Add List Tự Dùng", 11000948));

            if (item.quantity > 1) {
                var1.addElement(new Command(UseAllItem.isRunning() ? "Tắt Mở all" : "Mở all", 110261));
                var1.addElement(this.op);
                var1.addElement(new Command("Tách all", 1102441));
            }

            if (item.upgrade >= 12 && !(item.getTinhLuyen(85) >= 0 && item.getTinhLuyen(85) < 9)) {
                var1.addElement(new Command("Dịch Chuyển", 11000807));
            }

            if ((item.template.id == 652 || item.template.id == 653 || item.template.id == 654 || item.template.id == 655)) {
                var1.addElement(new Command("Luyện ngọc", 11000805));
            }
            if (!item.isLock) {
                var1.addElement(new Command("Tự Vứt", 11000809));
                if (Code.d1(item.template.id)) {
                    var1.addElement(new Command("Tắt Tự Shinwa " + Code.e1(item.template.id), 110267));
                } else {
                    var1.addElement(new Command("Tự Bán Shinwa", 110266));
                }
            }

            if (Code.a(item)) {
                var1.addElement(new Command("Tắt Tự TL", 110265));
            } else if (item.getTinhLuyen(85) >= 0 && item.getTinhLuyen(85) < 9) {
                var1.addElement(new Command("Tự TL", 110264));
            }

            if (Code.containDelItem(item.template.id)) {
                var1.addElement(new Command("Remove ItemDel", 110270));
            } else {
                var1.addElement(new Command("Add ItemDel", 110269));
            }

            if (Code.containItemThrow(item.template.id)) {
                var1.addElement(new Command("Remove ItemThrow", 110272));
            } else {
                var1.addElement(new Command("Add ItemThrow", 110271));
            }
        }

        var1.addElement(new Command(mResources.bu, Char.getMyChar().arrItemBag[indexSelect].quantity > 1 ? 11072 : 11073));
        var1.addElement(this.oo);
        if (Char.getMyChar().arrItemBag[indexSelect] != null) {
            RMS var3 = SettingAutoMuaBan.a(Char.getMyChar().arrItemBag[indexSelect].template.id);
            var1.addElement(new Command(var3 == null ? "Đặt giá" : var3.b, 1102211));
            if (var3 != null) {
                var1.addElement(new Command("Hủy Mua Bán", 1102213));
            }
        }

        var1.addElement(new Command(mResources.sapXep, 110221));
        GameCanvas.menu.showMenu(var1);
    }

    private static void gs() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 140221));
        var0.addElement(new Command(mResources.bz, 140222));
        GameCanvas.menu.showMenu(var0);
    }

    private static void gt() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110201));
        var0.addElement(new Command(mResources.bz, 110202));
        GameCanvas.menu.showMenu(var0);
    }

    private static void gu() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110181));
        var0.addElement(new Command(mResources.bz, 110182));
        GameCanvas.menu.showMenu(var0);
    }

    private static void gv() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 130021));
        var0.addElement(new Command(mResources.bz, 130022));
        GameCanvas.menu.showMenu(var0);
    }

    private static void gw() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110161));
        var0.addElement(new Command(mResources.bz, 110162));
        GameCanvas.menu.showMenu(var0);
    }

    private static void gx() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110141));
        var0.addElement(new Command(mResources.bz, 110142));
        GameCanvas.menu.showMenu(var0);
    }

    private static void gy() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110121));
        var0.addElement(new Command(mResources.bz, 110122));
        GameCanvas.menu.showMenu(var0);
    }

    private static void gz() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110101));
        var0.addElement(new Command(mResources.bz, 110102));
        GameCanvas.menu.showMenu(var0);
    }

    private static void ha() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110081));
        var0.addElement(new Command(mResources.bz, 110082));
        GameCanvas.menu.showMenu(var0);
    }

    private static void hb() {
        MyVector var0;
        (var0 = new MyVector()).addElement(new Command(mResources.by, 110051));
        var0.addElement(new Command(mResources.bz, 110052));
        GameCanvas.menu.showMenu(var0);
    }

    private static void b(byte var0) {
        Item var1 = getCurrentItemSelectByTypeUI(var0);
        Service.getInstance().buyItem(var1.typeUI, var1.indexUI, 1);
    }

    private static void c(byte var0) {
        a(getCurrentItemSelectByTypeUI(var0));
    }

    private void xemInfoItemTypeUI(byte typeUI) {
        Item item = getCurrentItemSelectByTypeUI(typeUI);
        this.getItemInfo(item.typeUI, item);
    }

    private static void hc() {
        String var0;
        if ((var0 = GameCanvas.ak.tfInput.getText()).trim().equals("")) {
            GameCanvas.setText(mResources.ju);
        } else {
            int var2;
            try {
                var2 = Integer.parseInt(var0);
            } catch (Exception var3) {
                GameCanvas.setMaxTextLenght();
                return;
            }

            if (var2 <= 0) {
                GameCanvas.setMaxTextLenght();
            } else if (Char.getMyChar().ai != 0 && var2 <= Char.getMyChar().ai) {
                Service.getInstance().e(gk - 1, var2);
                GameCanvas.setMaxTextLenght();
            } else {
                GameCanvas.setText(mResources.jt);
            }
        }

    }

    private static void hd() {
        String var0;
        if ((var0 = GameCanvas.ak.tfInput.getText()).trim().equals("")) {
            GameCanvas.setText(mResources.ju);
        } else {
            int var2;
            try {
                var2 = Integer.parseInt(var0);
            } catch (Exception var3) {
                GameCanvas.setMaxTextLenght();
                return;
            }

            if (Char.getMyChar().aj != 0 && var2 <= Char.getMyChar().aj) {
                Service.getInstance().f(Char.getMyChar().nClass.skillTemplates[indexSelect].id, var2);
                GameCanvas.setMaxTextLenght();
            } else {
                GameCanvas.setText(mResources.ju);
                GameCanvas.currentDialog = GameCanvas.msgdlg;
            }
        }

    }

    private static void he() {
        String var0;
        if ((var0 = GameCanvas.ak.tfInput.getText()).trim().equals("")) {
            GameCanvas.setText(mResources.jz);
        } else {
            Service.getInstance().addFriend(var0);
            GameCanvas.setMaxTextLenght();
        }

    }

    public final void perform(int var1, Object var2) {
        GameCanvas.instance.q();
    }

    public final void ak() {
        this.showButtonIndexMenu();
        indexMenu = 3;
        isPaintInfoMe = true;
        b(175, 200);
        super.right = this.mw;
    }

    public static void al() {
        oy = new int[vMobAttack.size()];
        oz = new int[vMobAttack.size()];

        int var0;
        Mob var1;
        for (var0 = 0; var0 < vMobAttack.size(); ++var0) {
            if (var0 != ox) {
                var1 = (Mob) vMobAttack.elementAt(var0);
                int var2;
                int var3;
                if (!ep) {
                    var2 = Math.abs(var1.cx - fb);
                    oy[var0] = var2;
                    var3 = Math.abs(var1.cy - fc);
                    oz[var0] = var3;
                } else {
                    var2 = Math.abs(var1.cx - Char.getMyChar().cx);
                    oy[var0] = var2;
                    var3 = Math.abs(var1.cy - Char.getMyChar().cy);
                    oz[var0] = var3;
                    fd = 700;
                }
            }
        }

        if (es == 1 && Char.getMyChar().mobFocus == null && Char.getMyChar().npcFocus == null && Char.getMyChar().mobFocus == null && Char.getMyChar().statusMe != 14 && Char.getMyChar().cMP > 0 && Char.getMyChar().itemFocus == null && System.currentTimeMillis() - ow + 2000L >= 0L) {
            if (!ep && Char.getMyChar().mobFocus != null && (Char.getMyChar().cx < fb - fd || Char.getMyChar().cy > fb + fd || Char.getMyChar().cy < fc - fd || Char.getMyChar().cy > fc + fd)) {
                Char.getMyChar().cx = fb;
                Char.getMyChar().cy = fc;
            }

            for (var0 = 0; var0 < vMobAttack.size(); ++var0) {
                if (var0 != ox && oy[var0] < fd && oz[var0] < fd && Char.getMyChar().mobFocus == null && (var1 = (Mob) vMobAttack.elementAt(var0)).h != 0 && var1.h != 1 && var1.levelBoss != 3) {
                    ServerEffect.a(141, Char.getMyChar().cx, Char.getMyChar().cy, 2);
                    Char.getMyChar().cx = var1.cx;
                    Char.getMyChar().cy = var1.cy;
                    Char.getMyChar().statusMe = 4;
                    Char.getMyChar().mobFocus = var1;
                    ServerEffect.a(141, Char.getMyChar().cx, Char.getMyChar().cy, 2);
                    Char.getMyChar().ff = var1.cx;
                    Char.getMyChar().fg = var1.cy;
                    Service.getInstance().b(var1.m);
                    ox = var0;
                    ow = System.currentTimeMillis();
                }
            }
        }

    }

    private static void ao(mGraphics var0) {
        if (gt) {
            Paint.a(popupX, popupY, ew, ex, var0);
            drawTitle(var0, mResources.z, false);
            leftMargin = popupX + 5;
            nw = popupY + 40;
            if (ab.size() == 0) {
                mFont.tahoma_7_white.writeText(var0, mResources.nt, popupX + ew / 2, popupY + 40, 2);
                return;
            }

            var0.setColor(-16770791);
            var0.fillRect(leftMargin - 2, nw - 2, ew - 6, wItem * 3 + 8);
            resetCursor(var0);
            scrMain.a(ab.size(), wItem, leftMargin, nw, ew - 3, wItem * 3 + 4, true, 1);
            scrMain.a(var0, leftMargin, nw, ew - 3, wItem * 3 + 6);
            totalRowSetting = ab.size();

            for (int var1 = 0; var1 < ab.size(); ++var1) {
                Ranked var2 = null;

                try {
                    var2 = (Ranked) ab.elementAt(var1);
                } catch (Exception var4) {
                }

                if (var2 != null) {
                    if (indexRow == var1) {
                        var0.setColor(Paint.b);
                        var0.fillRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                        var0.setColor(16777215);
                        var0.drawRect(leftMargin + 2, nw + indexRow * wItem + 2, ew - 15, wItem - 4);
                    } else {
                        var0.setColor(Paint.a);
                        var0.fillRect(leftMargin + 2, nw + var1 * wItem + 2, ew - 15, wItem - 4);
                        var0.setColor(13932896);
                        var0.drawRect(leftMargin + 2, nw + var1 * wItem + 2, ew - 15, wItem - 4);
                    }

                    mFont.tahoma_7_yellow.writeText(var0, var2.name, leftMargin + (ew - 10) / 2 - ew / 4, nw + var1 * wItem + wItem / 2 - 10, 2);
                    mFont.tahoma_7_yellow.writeText(var0, "-", leftMargin + (ew - 10) / 2, nw + var1 * wItem + wItem / 2 - 10, 2);
                    mFont.tahoma_7_yellow.writeText(var0, mResources.y + ": " + var2.id, leftMargin + (ew - 10) / 2 + ew / 4, nw + var1 * wItem + wItem / 2 - 10, 2);
                    mFont.tahoma_7_white.writeText(var0, var2.stt, leftMargin + ew / 2, nw + var1 * wItem + wItem / 2 + 5, 2);
                }
            }
        }

    }

    private static void hf() {
        Service.getInstance().a((short) 102, "", fe);
    }

    private void hg() {
        this.pj = true;
        Service.getInstance().a((short) 101, "", fe);
    }

    private static void hh() {
        Short var0 = new Short((short) 1);
        GameCanvas.ak.a(mResources.w, new Command(mResources.x, GameCanvas.instance, 1700, var0), 1);
    }

    public final void a(String var1, short var2, String var3, short var4, String var5, short var6, String var7, String var8, byte var9) {
        InfoDlg.d();
        in = true;
        this.iw = true;
        indexRow = 0;
        b(175, 200);
        super.left = null;
        super.center = new Command(mResources.x, 1701);
        super.right = new Command(mResources.am, 1702);
        fe = var9;
        this.iy = var1;
        this.pa = var2;
        this.pe = var3;
        this.pb = var4;
        this.pf = var5;
        this.pc = var6;
        this.pg = var7;
        this.ph = var8;
        var1 = this.ph == "" ? "" : mResources.u + this.ph + mResources.ke;
        var1 = "c3" + this.pe + "\n" + mResources.v + var6 + "\n" + var1 + "\n\nc0" + this.pg;
        this.ix = mFont.tahoma_7.a(var1, ew - 30);
        this.pi = System.currentTimeMillis();
        this.pd = a(this.pi, this.pa);
        this.pj = false;
    }

    private static String a(long var0, int var2) {
        long var3;
        if ((var3 = (var0 + (long) (var2 * 1000) - System.currentTimeMillis()) / 1000L) <= 0L) {
            return "";
        } else {
            long var5;
            if ((var5 = var3 / 60L) <= 0L) {
                return var3 < 10L ? "0" + var3 + "s" : var3 + "s";
            } else if (var5 < 10L) {
                return var3 % 60L >= 0L && var3 % 60L < 10L ? "0" + var5 + ":0" + var3 % 60L : "0" + var5 + ":" + var3 % 60L;
            } else {
                return var3 % 60L >= 0L && var3 % 60L < 10L ? var5 + ":0" + var3 % 60L : var5 + ":" + var3 % 60L;
            }
        }
    }

    private void hi() {
        in = false;
        this.iy = null;
        this.ix = null;
        super.center = null;
        super.left = null;
        super.right = null;
        this.resetButton();
    }

    private void ap(mGraphics var1) {
        if (this.ix != null && in) {
            if (!this.pj) {
                if (this.pc > 1) {
                    this.pd = a(this.pi, this.pa);
                }

                if (this.pd == "") {
                    this.hg();
                }

                long var2;
                if ((var2 = (this.pi + (long) (this.pa * 1000) - System.currentTimeMillis()) / 1000L) > 8L && var2 % 5L == 0L) {
                    this.hg();
                }

                if (var2 <= 10L) {
                    super.center = null;
                }

                if (var2 <= 20L) {
                    this.pk = mFont.tahoma_7b_red;
                } else {
                    this.pk = mFont.tahoma_7b_yellow;
                }
            }

            resetCursor(var1);
            Paint.a(popupX, popupY, ew, ex, var1);
            if (this.iy != null) {
                drawTitle(var1, this.iy, ck);
            }

            leftMargin = popupX + 15;
            nw = popupY + 15;
            if (this.iy != null) {
                nw += 10;
            }

            this.pk.writeText(var1, this.pd, popupX + ew / 2, nw + 12, 2);
            mFont.tahoma_7_yellow.writeText(var1, mResources.t, popupX + ew / 2, nw + 30, 2);
            int var7 = popupX + ew / 2;
            int var3 = nw + 42;
            int var4 = ew / 2;
            String var5 = this.pb + "." + this.pf + "%";
            int var6 = this.pb;
            var1.setColor(0);
            var1.fillRect(var7 - var4 / 2, var3, var4, 12);
            if ((var6 = var6 * var4 / 100) <= 0) {
                var6 = 1;
            }

            var1.setClip(var7 - var4 / 2, var3, var6, 12);
            var1.setColor(16711680);
            var1.fillRect(var7 - var4 / 2, var3, var4, 12);
            resetCursor(var1);
            mFont.tahoma_7_yellow.writeText(var1, var5, popupX + ew / 2, var3, 2);
            totalRowSetting = this.ix.size();
            scrMain.a(totalRowSetting, 12, popupX, nw + 48, ew, ex - 42 - (this.iy != null ? 10 : 0), true, 1);
            scrMain.a(var1);
            this.od = nw + 48;
            mFont var8 = mFont.tahoma_7_white;

            String var9;
            for (var3 = 0; var3 < this.ix.size() && (var9 = (String) this.ix.elementAt(var3)) != null && this.ix != null && var8 != null; ++var3) {
                if (var9.startsWith("c")) {
                    if (var9.startsWith("c0")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7_white;
                    } else if (var9.startsWith("c1")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7b_yellow;
                    } else if (var9.startsWith("c2")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7b_white;
                    } else if (var9.startsWith("c3")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7_yellow;
                    } else if (var9.startsWith("c4")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7b_red;
                    } else if (var9.startsWith("c5")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7_red;
                    } else if (var9.startsWith("c6")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7_grey;
                    } else if (var9.startsWith("c7")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7b_blue;
                    } else if (var9.startsWith("c8")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7_blue;
                    } else if (var9.startsWith("c9")) {
                        var9 = var9.substring(2);
                        var8 = mFont.tahoma_7_green;
                    }
                }

                var8.writeText(var1, var9, popupX + ew / 2, this.od += 12, 2);
            }
        }

    }

    public final void update() {
        TileMap.e();
        Session_ME.p = false;
        Session_ME.e();
        TileMap.ag = false;
        Code.instance.a();
        super.update();
    }

    public final void am() {
        jy = new byte[this.jr.length];

        int var1;
        for (var1 = 0; var1 < this.jr.length; ++var1) {
            jy[var1] = keySkill[var1] == null ? -1 : keySkill[var1].template.id;
        }

        jx = new byte[this.jq.length];

        for (var1 = 0; var1 < this.jq.length; ++var1) {
            jx[var1] = arrSkill[var1] == null ? -1 : arrSkill[var1].template.id;
        }

    }

    public final void an() {
        this.pl = new byte[]{-1, -1, -1, -1, -1, -1};
    }

    private void hj() {
        if (GameCanvas.l[4]) {
            this.e((byte) 0);
            GameCanvas.l();
        } else if (GameCanvas.l[2]) {
            this.e((byte) 1);
            GameCanvas.l();
        } else if (GameCanvas.l[6]) {
            this.e((byte) 2);
            GameCanvas.l();
        }

    }

    public final void a(Image var1) {
        var1.getRGB(this.fi, 0, 60, 0, 15, 60, 10);
        chatPopup("Ma ám");
    }

    private void e(byte var1) {
        for (int var2 = 0; var2 < this.pl.length; ++var2) {
            if (var2 != this.pl.length - 1) {
                this.pl[var2] = this.pl[var2 + 1];
            } else {
                this.pl[var2] = var1;
                Service.getInstance().a(var1);
            }
        }

    }

    private void aq(mGraphics var1) {
        try {
            totalRowSetting = 1;
            this.pq = ew;
            this.pr = ex;
            this.po = popupX;
            this.pp = popupY;
            this.pz = this.po + 25;
            this.qa = this.pp + 60;
            this.qb = this.pq - 50;
            this.qc = 70;
            this.pu = this.pq - 49;
            this.pv = 10;
            this.ps = GameCanvas.centerX - this.pu / 2;
            this.pt = this.qa + this.qc - this.pv;
            this.py = 18;
            this.pw = GameCanvas.centerX - (this.pn.size() - 1) * ((this.py + 5) / 2);
            this.px = this.pp + this.pr - this.py / 2 - 5;
            Clan_ThanThu var2;
            if (this.pn.size() > 0 && this.pm <= this.pn.size()) {
                var2 = (Clan_ThanThu) this.pn.elementAt(this.pm);
            } else {
                var2 = null;
            }

            if (var2 == null) {
                var1.setColor(13606712);
                var1.drawRect(this.pz - 1, this.qa - 1, this.qb + 1, this.qc + 1);
                var1.setClip(this.pz, this.qa, this.qb, this.qc);
                var1.setColor(6425);
                var1.fillRect(this.pz, this.qa, this.qb, this.qc);
            } else {
                mFont.tahoma_7b_white.writeText(var1, var2.a, GameCanvas.centerX, this.pp + 35, 2);
                var1.setColor(13606712);
                var1.drawRect(this.pz - 1, this.qa - 1, this.qb + 1, this.qc + 1);
                var1.setClip(this.pz, this.qa, this.qb, this.qc);
                var1.setColor(6425);
                var1.fillRect(this.pz, this.qa, this.qb, this.qc);
                SmallImage.paintImage(var1, var2.d, this.pz + this.qb / 2, this.qa + this.qc / 2 - 10, 0, 3);
                resetCursor(var1);
                int var3;
                if (var2.e >= 0) {
                    this.qe = Res.a(var2.j, var2.e);
                    if (!this.qe.equals("")) {
                        mFont.tahoma_7_yellow.writeText(var1, var2.f + " " + this.qe, this.pz, this.pt + 15, 0);
                    } else {
                        --this.qd;
                        if (this.qd <= 0) {
                            Service.getInstance().requestClanItem();
                            this.qd = 100;
                        }
                    }
                } else {
                    for (var3 = 0; var3 < var2.b; ++var3) {
                        SmallImage.paintImage(var1, 628, this.po + 95 + var3 * 12 - var2.b * 6, this.pp + 50, 0, 3);
                    }

                    var3 = var2.h * this.pu / var2.i;
                    var1.setColor(2506246);
                    var1.fillRect(this.ps, this.pt, this.pu, this.pv);
                    var1.setColor(371981);
                    var1.fillRect(this.ps, this.pt, var3, this.pv);
                    var1.setColor(13606712);
                    var1.drawRect(this.ps, this.pt, this.pu, this.pv);
                    mFont.tahoma_7_white.writeText(var1, var2.h + "/" + var2.i, this.ps + this.pu / 2, this.pt, 2);

                    for (int var4 = 0; var4 < var2.g.size(); ++var4) {
                        String var6 = (String) var2.g.elementAt(var4);
                        mFont.tahoma_7_yellow.writeText(var1, var6, this.pz + this.qb / 2, this.pt + 15 + var4 * 10, 2);
                    }
                }

                for (var3 = 0; var3 < this.pn.size(); ++var3) {
                    Clan_ThanThu var7;
                    if (this.pn.size() > 0 && var3 <= this.pn.size()) {
                        var7 = (Clan_ThanThu) this.pn.elementAt(var3);
                    } else {
                        var7 = null;
                    }

                    if (var7 != null) {
                        var1.setColor(0);
                        var1.fillRect(this.pw + var3 * (this.py + 5) - this.py / 2, this.px - this.py / 2, this.py, this.py);
                        SmallImage.paintImage(var1, 154, this.pw + var3 * (this.py + 5), this.px, 0, 3);
                        var1.setColor(12281361);
                        var1.drawRect(this.pw + var3 * (this.py + 5) - this.py / 2, this.px - this.py / 2, this.py, this.py);
                        SmallImage.paintImage(var1, var7.c, this.pw + var3 * (this.py + 5), this.px, 0, 3);
                    }
                }

                var1.setColor(16777215);
                var1.drawRect(this.pw + this.pm * (this.py + 5) - this.py / 2, this.px - this.py / 2, this.py, this.py);
            }
        } catch (Exception var6) {
            System.out.println("e:" + var6.toString());
        }

    }

    public final void a(Clan_ThanThu var1) {
        this.pn.addElement(var1);
    }

    public final void ao() {
        this.pn.removeAllElements();
    }

    private void hk() {
        try {
            if (ij && Char.getMyChar().arrItemBag[indexSelect].isTypeNgocKham()) {
                if (itemSplit == null) {
                    if (Char.getMyChar().arrItemBag[indexSelect].upgrade < 10) {
                        itemSplit = Char.getMyChar().arrItemBag[indexSelect];
                        Char.getMyChar().arrItemBag[indexSelect] = null;
                        return;
                    }

                    GameCanvas.msgdlg.a(mResources.f, (Command) null, new Command(mResources.am, 2), (Command) null);
                    GameCanvas.currentDialog = GameCanvas.msgdlg;
                    return;
                }

                if (arrItemSplit == null) {
                    arrItemSplit = new Item[24];
                }

                for (int var1 = 0; var1 < arrItemSplit.length; ++var1) {
                    if (arrItemSplit[var1] == null) {
                        arrItemSplit[var1] = Char.getMyChar().arrItemBag[indexSelect];
                        Char.getMyChar().arrItemBag[indexSelect] = null;
                        super.left = super.center = null;
                        this.ab();
                        return;
                    }

                    if (var1 == arrItemSplit.length - 1) {
                        GameCanvas.setText(mResources.js);
                    }
                }

                return;
            }
        } catch (Exception var2) {
            GameCanvas.msgdlg.a(mResources.f, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
            var2.printStackTrace();
        }

    }

    private void hl() {
        Item var3;
        if (Char.getMyChar().arrItemBag[indexSelect].isTypeNgocKham()) {
            if (itemSplit == null) {
                itemSplit = Char.getMyChar().arrItemBag[indexSelect];
                Char.getMyChar().arrItemBag[indexSelect] = null;
            } else {
                var3 = Char.getMyChar().arrItemBag[indexSelect];
                Char.getMyChar().arrItemBag[indexSelect] = null;
                Char.getMyChar().arrItemBag[itemSplit.indexUI] = itemSplit;
                itemSplit = var3;
            }

            super.left = super.center = null;
            this.ab();
        } else if (Char.getMyChar().arrItemBag[indexSelect].isTypeBody()) {
            if (itemUpGrade == null) {
                itemUpGrade = Char.getMyChar().arrItemBag[indexSelect];
                Char.getMyChar().arrItemBag[indexSelect] = null;
            } else {
                var3 = Char.getMyChar().arrItemBag[indexSelect];
                Char.getMyChar().arrItemBag[indexSelect] = null;
                Char.getMyChar().arrItemBag[itemUpGrade.indexUI] = itemUpGrade;
                itemUpGrade = var3;
            }

            super.left = super.center = null;
            this.ab();
        } else if (Char.getMyChar().arrItemBag[indexSelect].template.type != 26 && Char.getMyChar().arrItemBag[indexSelect].template.type != 28) {
            GameCanvas.msgdlg.a(mResources.jm, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        } else {
            int var1;
            if (Char.getMyChar().arrItemBag[indexSelect].template.type == 28) {
                for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                    if (arrItemUpGrade[var1] != null && arrItemUpGrade[var1].template.type == 28) {
                        Item var2 = Char.getMyChar().arrItemBag[indexSelect];
                        Char.getMyChar().arrItemBag[indexSelect] = null;
                        int var10001 = arrItemUpGrade[var1].indexUI;
                        Char.getMyChar().arrItemBag[var10001] = arrItemUpGrade[var1];
                        arrItemUpGrade[var1] = var2;
                        return;
                    }
                }
            }

            for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                if (arrItemUpGrade[var1] == null) {
                    arrItemUpGrade[var1] = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                    super.left = super.center = null;
                    this.ab();
                    return;
                }
            }

            GameCanvas.msgdlg.a(mResources.js, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        }

    }

    private void r(int var1) {
        MyVector var2 = new MyVector();
        if (var1 == 0) {
            var2.addElement(this.mk);
        } else if (var1 == 1) {
            var2.addElement(this.ml);
        } else if (var1 == 2) {
            var2.addElement(this.mm);
        }

        if (itemUpGrade != null && itemSplit != null) {
            for (var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
                if (arrItemUpGrade[var1] != null) {
                    var2.addElement(new Command(mResources.fc, 341));
                    break;
                }
            }
        }

        GameCanvas.menu.showMenu(var2);
    }

    private void s(int var1) {
        Item var2 = itemSplit;
        if (var1 == 1) {
            var2 = itemUpGrade;
            itemUpGrade = null;
        } else if (var1 == 2) {
            var2 = getCurrentItemSelectByTypeUI(47);
            arrItemUpGrade[indexSelect] = null;
        } else {
            itemSplit = null;
        }

        Char.getMyChar().arrItemBag[var2.indexUI] = var2;
        super.left = super.center = null;
        this.ab();
    }

    private static void hm() {
        int var0 = 0;

        for (int var1 = 0; var1 < arrItemUpGrade.length; ++var1) {
            if (arrItemUpGrade[var1] != null && arrItemUpGrade[var1].template.type == 26) {
                var0 += crystals[arrItemUpGrade[var1].template.id];
            }
        }

        boolean var3 = false;
        int var2 = 0;
        if (itemSplit != null) {
            if (coinUpWeapons[itemSplit.upgrade] > Char.getMyChar().xu + Char.getMyChar().yen && coinUpWeapons[itemSplit.upgrade] > Char.getMyChar().yen) {
                var3 = true;
            }

            var2 = var0 * 100 / upWeapon[itemSplit.upgrade];
        }

        if (var3) {
            InfoMe.a(mResources.lr, 15, mFont.tahoma_7_red);
        } else if (var2 > 250) {
            GameCanvas.a(mResources.ls, new Command(mResources.bn, 342), new Command(mResources.ca, 1));
        } else if (itemSplit != null && itemUpGrade != null && arrItemUpGrade.length > 0) {
            if (!itemUpGrade.isLock) {
                GameCanvas.a(mResources.lt, new Command(mResources.bn, 342), new Command(mResources.ca, 1));
                return;
            }

            Service.getInstance().ngockham((byte) 0, itemUpGrade, itemSplit, arrItemUpGrade);
        }

    }

    private static void hn() {
        GameCanvas.setMaxTextLenght();
        if (itemSplit != null && itemUpGrade != null && arrItemUpGrade.length > 0) {
            Service.getInstance().ngockham((byte) 0, itemUpGrade, itemSplit, arrItemUpGrade);
        }

    }

    private void ar(mGraphics var1) {
        if (ij) {
            if (indexMenu == 0) {
                String[] var2 = mResources.gq;
                var1 = var1;
                GameScr var3 = this;

                try {
                    Paint.a(popupX, popupY, ew, ex, var1);
                    drawTitle(var1, var2[indexMenu], var2.length > 1);
                    leftMargin = popupX + 3;
                    nw = popupY + 34 + wItem;
                    int var14 = popupX + 74;
                    int var4 = nw - wItem - 3;
                    oc = 4;
                    int var5;
                    int var6;
                    if (itemSplit == null) {
                        var1.setColor(6425);
                        var1.fillRect(var14 - 1, var4 - 1, wItem + 3, wItem + 3);
                        SmallImage.paintImage(var1, 154, var14 + wItem / 2, var4 + wItem / 2, 0, 3);
                    } else {
                        var3.drawItemUI(var1, itemSplit, var14, var4);
                        var5 = var14 + 35;
                        var6 = var4 + 25;
                        GameScr var7 = var3;
                        int var8 = var3.qf[itemSplit.upgrade][0];
                        int var9 = 0;
                        int var10;
                        if (itemSplit.options != null) {
                            for (var10 = 0; var10 < itemSplit.options.size(); ++var10) {
                                ItemOption var11;
                                if ((var11 = (ItemOption) itemSplit.options.elementAt(var10)).optionTemplate.id == 104) {
                                    var9 = var11.param;
                                }
                            }
                        }

                        var10 = 0;

                        int var17;
                        for (var17 = 0; var17 < arrItemSplit.length; ++var17) {
                            Item var12;
                            if ((var12 = arrItemSplit[var17]) != null) {
                                var10 += var7.qf[var12.upgrade][1];
                            }
                        }

                        var1.setColor(0);
                        var1.fillRect(var5, var6 - 5, 60, 5);
                        if ((var17 = var9 * 60 / var8) <= 0) {
                            var17 = 0;
                        } else if (var17 > 60) {
                            var17 = 60;
                        }

                        var1.setColor(-16711936);
                        var1.fillRect(var5, var6 - 5, var17, 5);
                        int var18;
                        if ((var18 = var10 * 60 / var8) >= 60 - var17) {
                            var18 = 60 - var17;
                        }

                        var1.setColor(-16346586);
                        var1.fillRect(var5 + var17, var6 - 5, var18, 5);
                        mFont.tahoma_7_yellow.writeText(var1, var10 + var9 + "/" + var8, var5 + 30, var6 - 5 - 15, 2);
                    }

                    var1.setColor(12281361);
                    var1.drawRect(var14, var4, wItem, wItem);
                    var1.setColor(6425);
                    var1.fillRect(leftMargin - 1, nw - 1, wItem * ob + 3, wItem * oc + 3);

                    int var15;
                    for (var15 = 0; var15 < oc; ++var15) {
                        for (var5 = 0; var5 < ob; ++var5) {
                            SmallImage.paintImage(var1, 154, leftMargin + var5 * wItem + wItem / 2, nw + var15 * wItem + wItem / 2, 0, 3);
                            var1.setColor(12281361);
                            var1.drawRect(leftMargin + var5 * wItem, nw + var15 * wItem, wItem, wItem);
                        }
                    }

                    for (var15 = 0; var15 < arrItemSplit.length; ++var15) {
                        Item var16;
                        if ((var16 = arrItemSplit[var15]) != null) {
                            var5 = var15 / ob;
                            var6 = var15 - var5 * ob;
                            if (!var16.isLock) {
                                var1.setColor(12083);
                                var1.fillRect(leftMargin + var6 * wItem + 1, nw + var5 * wItem + 1, wItem - 1, wItem - 1);
                            }

                            var3.drawItemUI(var1, var16, leftMargin + var6 * wItem, nw + var5 * wItem);
                            if (var16.quantity > 1) {
                                mFont.number_yellow.writeText(var1, String.valueOf(var16.quantity), leftMargin + var6 * wItem + wItem, nw + var5 * wItem + wItem - mFont.number_yellow.getHeight(), 1);
                            }
                        }
                    }

                    if (gk == 1) {
                        var1.setColor(16777215);
                        var1.drawRect(var14, var4, wItem, wItem);
                        return;
                    }

                    if (gk == 2) {
                        var15 = indexSelect / ob;
                        var5 = indexSelect - var15 * ob;
                        var1.setColor(16777215);
                        var1.drawRect(leftMargin + var5 * wItem, nw + var15 * wItem, wItem, wItem);
                        return;
                    }
                } catch (Exception var14) {
                    var14.printStackTrace();
                }

                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gq);
            }
        }

    }

    private void as(mGraphics var1) {
        if (ik) {
            if (indexMenu == 0) {
                String[] var2 = mResources.gp;
                var1 = var1;
                GameScr var3 = this;

                try {
                    oc = 3;
                    Paint.a(popupX, popupY, ew, ex, var1);
                    drawTitle(var1, var2[indexMenu], var2.length > 1);
                    leftMargin = popupX + 3;
                    nw = popupY + 34 + wItem;
                    int var10 = popupX + 45;
                    int var4 = popupX + 100;
                    int var5 = nw - wItem - 3;
                    if (itemSplit != null) {
                        var3.drawItemUI(var1, itemSplit, var10, var5);
                    } else {
                        var1.setColor(6425);
                        var1.fillRect(var10 - 1, var5 - 1, wItem + 3, wItem + 3);
                        SmallImage.paintImage(var1, 154, var10 + wItem / 2, var5 + wItem / 2, 0, 3);
                    }

                    var1.setColor(12281361);
                    var1.drawRect(var10, var5, wItem, wItem);
                    if (itemUpGrade != null) {
                        var3.drawItemUI(var1, itemUpGrade, var4, var5);
                    } else {
                        var1.setColor(6425);
                        var1.fillRect(var4 - 1, var5 - 1, wItem + 3, wItem + 3);
                        SmallImage.paintImage(var1, 154, var4 + wItem / 2, var5 + wItem / 2, 0, 3);
                    }

                    var1.setColor(12281361);
                    var1.drawRect(var10, var5, wItem, wItem);
                    var1.drawRect(var4, var5, wItem, wItem);
                    mFont.tahoma_7b_yellow.writeText(var1, "+", var10 + wItem + 15, var5 + wItem / 2 - 5, 2);
                    if (gk == 1) {
                        if (indexSelect == 0) {
                            var1.setColor(16777215);
                            var1.drawRect(var10, var5, wItem, wItem);
                        }

                        if (indexSelect == 1) {
                            var1.setColor(16777215);
                            var1.drawRect(var4, var5, wItem, wItem);
                        }
                    }

                    int var11;
                    for (var11 = 0; var11 < oc; ++var11) {
                        for (var10 = 0; var10 < ob; ++var10) {
                            var1.setColor(6425);
                            var1.fillRect(leftMargin + var10 * wItem, nw + var11 * wItem, wItem + 3, wItem + 3);
                            SmallImage.paintImage(var1, 154, leftMargin + var10 * wItem + wItem / 2, nw + var11 * wItem + wItem / 2, 0, 3);
                            var1.setColor(12281361);
                            var1.drawRect(leftMargin + var10 * wItem, nw + var11 * wItem, wItem, wItem);
                        }
                    }

                    if (gk == 2) {
                        var11 = indexSelect / ob;
                        var10 = indexSelect - var11 * ob;
                        var1.setColor(16777215);
                        var1.drawRect(leftMargin + var10 * wItem, nw + var11 * wItem, wItem, wItem);
                    }

                    int var6;
                    int var8;
                    for (var11 = 0; var11 < arrItemUpGrade.length; ++var11) {
                        Item var7;
                        if ((var7 = arrItemUpGrade[var11]) != null) {
                            var8 = var11 / ob;
                            var6 = var11 - var8 * ob;
                            if (!var7.isLock) {
                                var1.setColor(12083);
                                var1.fillRect(leftMargin + var6 * wItem + 1, nw + var8 * wItem + 1, wItem - 1, wItem - 1);
                            }

                            SmallImage.paintImage(var1, var7.template.iconID, leftMargin + var6 * wItem + wItem / 2, nw + var8 * wItem + wItem / 2, 0, 3);
                        }
                    }

                    if (itemUpGrade != null && itemSplit != null) {
                        var11 = 0;

                        for (var10 = 0; var10 < arrItemUpGrade.length; ++var10) {
                            if (arrItemUpGrade[var10] != null && arrItemUpGrade[var10].template.type == 26) {
                                var11 += crystals[arrItemUpGrade[var10].template.id];
                            }
                        }

                        if ((var10 = var11 * 100 / upWeapon[itemSplit.upgrade]) > maxPercents[itemSplit.upgrade]) {
                            var10 = maxPercents[itemSplit.upgrade];
                        }

                        if (hx) {
                            var10 = (int) ((double) var10 * 1.5);
                        }

                        mFont var13 = mFont.tahoma_7_yellow;
                        var6 = 0;
                        if (itemSplit.options != null) {
                            for (var8 = 0; var8 < itemSplit.options.size(); ++var8) {
                                ItemOption var12;
                                if ((var12 = (ItemOption) itemSplit.options.elementAt(var8)).optionTemplate.id == 123) {
                                    var6 = var12.param;
                                }
                            }
                        } else {
                            Service.getInstance().requestItemInfo(itemSplit.typeUI, itemSplit.indexUI);
                        }

                        if (var6 > Char.getMyChar().xu + Char.getMyChar().yen && var6 > Char.getMyChar().yen) {
                            var13 = mFont.tahoma_7_red;
                        }

                        var13.writeText(var1, mResources.a(mResources.canNumberYen, NinjaUtil.a(String.valueOf(var6))), leftMargin, nw + oc * wItem + 5, 0);
                        mFont.tahoma_7_yellow.writeText(var1, mResources.hc + ": " + var10 + "%", leftMargin, nw + oc * wItem + 17, 0);
                    } else {
                        for (var11 = 0; var11 < mResources.gw.length; ++var11) {
                            mFont.tahoma_7_white.writeText(var1, mResources.gw[var11], leftMargin, nw + oc * wItem + 5 + var11 * 12, 0);
                        }
                    }

                    if (ez != null) {
                        SmallImage.paintImage(var1, ez.arrEfInfo[ey].c, var4 + wItem / 2 + ez.arrEfInfo[ey].a + 1, var5 + wItem / 2 + 9 + ez.arrEfInfo[ey].b, 0, 3);
                        if (GameCanvas.gameTick % 2 == 0 && ++ey >= ez.arrEfInfo.length) {
                            ey = 0;
                            ez = null;
                            return;
                        }
                    }
                } catch (Exception var12) {
                    var12.printStackTrace();
                }

                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gp);
            }
        }

    }

    private void at(mGraphics var1) {
        if (il) {
            if (indexMenu == 0) {
                this.a(var1, mResources.gs, (byte) 0);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gs);
            }
        }

    }

    private void au(mGraphics var1) {
        if (im) {
            if (indexMenu == 0) {
                this.a(var1, mResources.gt, (byte) 1);
                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gt);
            }
        }

    }

    private void a(mGraphics var1, String[] var2, byte var3) {
        try {
            oc = 5;
            Paint.a(popupX, popupY, ew, ex, var1);
            showUIBox(var1, var2, false);
            var1.setColor(6693376);
            var1.fillRect(popupX + 3, popupY + 32, 168, 140);
            var1.setColor(13408563);
            var1.drawRect(popupX + 3, popupY + 32, 168, 140);
            int var9 = popupX + 74;
            int var4 = popupY + 40 + wItem;
            if (itemSplit != null) {
                var1.setColor(6425);
                var1.fillRect(var9 - 1, var4 - 1, wItem + 3, wItem + 3);
                SmallImage.paintImage(var1, 154, var9 + wItem / 2, var4 + wItem / 2, 0, 3);
                this.drawItemUI(var1, itemSplit, var9, var4);
                if (itemSplit.quantity > 1) {
                    mFont.number_yellow.writeText(var1, "" + itemSplit.quantity, var9 + wItem, var4 + wItem / 2 + 6, 1);
                }

                var1.setColor(gk == 1 ? 16777215 : 12281361);
                var1.drawRect(var9, var4, wItem, wItem);
            } else {
                var1.setColor(6425);
                var1.fillRect(var9 - 1, var4 - 1, wItem + 3, wItem + 3);
                SmallImage.paintImage(var1, 154, var9 + wItem / 2, var4 + wItem / 2, 0, 3);
                var1.setColor(12281361);
                var1.drawRect(var9, var4, wItem, wItem);
            }

            if (itemSplit != null) {
                int var5 = 0;
                if (itemSplit.options != null) {
                    for (int var6 = 0; var6 < itemSplit.options.size(); ++var6) {
                        ItemOption var7;
                        if ((var7 = (ItemOption) itemSplit.options.elementAt(var6)).optionTemplate.id == 122) {
                            var5 = var7.param;
                        }
                    }
                } else {
                    Service.getInstance().requestItemInfo(itemSplit.typeUI, itemSplit.indexUI);
                }

                String var12 = mResources.nb;
                String var13 = var5 + mResources.kf;
                String var11 = mResources.s;
                if (var3 == 0) {
                    var12 = mResources.na;
                    var13 = ip[itemSplit.upgrade] + mResources.ke;
                    var11 = mResources.r;
                }

                mFont.tahoma_7_white.writeText(var1, var12, var9 + wItem / 2, var4 + 3 * wItem / 2 + 2, 2);
                mFont.tahoma_7_yellow.writeText(var1, var13, var9 + wItem / 2, var4 + 3 * wItem / 2 + 14, 2);
                String[] var10 = mFont.tahoma_7_white.b(var11, 130);

                for (var4 = 0; var4 < var10.length; ++var4) {
                    mFont.tahoma_7_white.writeText(var1, var10[var4], var9 + wItem / 2, popupY + ex - 25 + var4 * 12 - 2, 2);
                }

                return;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    private static void ho() {
        try {
            if (il) {
                if (Char.getMyChar().arrItemBag[indexSelect].isTypeNgocKham()) {
                    itemSplit = Char.getMyChar().arrItemBag[indexSelect];
                    Char.getMyChar().arrItemBag[indexSelect] = null;
                    return;
                }
            } else if (im && Char.getMyChar().arrItemBag[indexSelect].isTypeBody()) {
                itemSplit = Char.getMyChar().arrItemBag[indexSelect];
                Char.getMyChar().arrItemBag[indexSelect] = null;
                return;
            }
        } catch (Exception var1) {
            GameCanvas.msgdlg.a(mResources.g, (Command) null, new Command(mResources.am, 2), (Command) null);
            GameCanvas.currentDialog = GameCanvas.msgdlg;
        }

    }

    private void av(mGraphics var1) {
        if (io) {
            if (indexMenu == 0) {
                Item[] var2 = arrItemSplit;
                String[] var3 = mResources.gr;
                var1 = var1;

                try {
                    resetCursor(var1);
                    showUIBox(var1, var3, true);
                    if (var2 == null) {
                        GameCanvas.a(popupX + 90, popupY + 75, var1);
                        mFont.tahoma_7b_white.writeText(var1, mResources.textLoading, popupX + 90, popupY + 90, 2);
                        return;
                    }

                    if (var2.length <= 30) {
                        oc = 5;
                    } else if (var2.length % ob == 0) {
                        oc = var2.length / ob;
                    } else {
                        oc = var2.length / ob + 1;
                    }

                    scrMain.a(oc, wItem, leftMargin, nw, ob * wItem, 5 * wItem, true, 6);
                    scrMain.a(var1, leftMargin, nw, scrMain.e + 2, scrMain.f + 2);

                    int var4;
                    int var8;
                    for (var8 = 0; var8 < oc; ++var8) {
                        for (var4 = 0; var4 < ob; ++var4) {
                            SmallImage.paintImage(var1, 154, leftMargin + var4 * wItem + wItem / 2, nw + var8 * wItem + wItem / 2, 0, 3);
                            var1.setColor(12281361);
                            var1.drawRect(leftMargin + var4 * wItem, nw + var8 * wItem, wItem, wItem);
                        }
                    }

                    for (var8 = 0; var8 < var2.length; ++var8) {
                        Item var9;
                        if ((var9 = var2[var8]) != null) {
                            int var5 = var8 / ob;
                            int var6 = var8 - var5 * ob;
                            if (!var9.isLock) {
                                var1.setColor(12083);
                                var1.fillRect(leftMargin + var6 * wItem + 1, nw + var5 * wItem + 1, wItem - 1, wItem - 1);
                                SmallImage.paintImage(var1, 154, leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2, 0, 3);
                            }

                            SmallImage.paintImage(var1, var9.template.iconID, leftMargin + var6 * wItem + wItem / 2, nw + var5 * wItem + wItem / 2, 0, 3);
                        }
                    }

                    if (gk > 0 && indexSelect >= 0) {
                        var8 = indexSelect / ob;
                        var4 = indexSelect - var8 * ob;
                        var1.setColor(16777215);
                        var1.drawRect(leftMargin + var4 * wItem, nw + var8 * wItem, wItem, wItem);
                        a(leftMargin + var4 * wItem, nw + var8 * wItem, var1);
                        return;
                    }
                } catch (Exception var9) {
                }

                return;
            }

            if (indexMenu == 1) {
                this.a(var1, mResources.gr);
            }
        }

    }

    private void hp() {
        for (int var1 = 0; var1 < arrItemSplit.length; ++var1) {
            if (arrItemSplit[var1] == null) {
                arrItemSplit[var1] = Char.getMyChar().arrItemBag[indexSelect];
                Char.getMyChar().arrItemBag[indexSelect] = null;
                super.left = super.center = null;
                this.ab();
                return;
            }
        }

    }

    private void hq() {
        MyVector var1 = new MyVector();

        for (int var2 = 0; var2 < arrItemSplit.length; ++var2) {
            if (arrItemSplit[var2] != null) {
                var1.addElement(this.mh);
                break;
            }
        }

        if (arrItemSplit.length > 0) {
            var1.addElement(new Command(mResources.gr[0], 403));
        }

        GameCanvas.menu.showMenu(var1);
    }

    private void hr() {
        Item var1 = getCurrentItemSelectByTypeUI(48);
        arrItemSplit[indexSelect] = null;
        Char.getMyChar().arrItemBag[var1.indexUI] = var1;
        super.left = super.center = null;
        this.ab();
    }

    private static void hs() {
        Service.getInstance().f(arrItemSplit);
    }

    public static void createMob(Mob mob, int var1) {
        MobTemplate var2;
        if ((var2 = Mob.mobTemplates[mob.id]).c != 0) {
            mob.g = var1 % 3 == 0 ? -1 : 1;
            mob.curX += 10 - var1 % 20;
        }

        Auto.a(mob);
        if (!qj.contains(var2) && !mob.isBoss && (mob.id != 179 && mob.id != 175 && mob.id != 202 || mob.h != 8)) {
            qj.addElement(var2);
        }

        vMobAttack.addElement(mob);
    }

    public static void goNPC(int var0) {
        Npc var1;
        if ((var1 = findNpc(var0)) != null) {
            Char.charMove(var1.cx, var1.cy);
            Char.getMyChar().npcFocus = var1;
            Service.getInstance().openMenu(var1.template.npcTemplateId);
        }

    }

    public static void PickNpc(int var0, int var1, int var2) {
        if (System.currentTimeMillis() < 500L) {
            NinjaUtil.sleep(500L - System.currentTimeMillis());
        }

        Npc var3;
        if ((var3 = findNpc(var0)) != null) {
            Char.charMove(var3.cx, var3.cy);
            Char.getMyChar().npcFocus = var3;
            Service.getInstance().openMenu(var3.template.npcTemplateId);
            if (var0 == 8) {
                Service.getInstance().menu(var0, var1, var2);
            } else {
                Service.getInstance().menu(var0, var1, 0);
                Service.getInstance().menu(var0, var2, 0);
            }
        }

    }

    public static Npc findNpc(int var0) {
        Char var1 = Char.getMyChar();
        MyVector var2 = ah;
        int var3 = -1;
        Npc var4 = null;

        for (int var5 = 0; var5 < var2.size(); ++var5) {
            Npc var6;
            if ((var6 = (Npc) var2.elementAt(var5)) != null && var0 == var6.template.npcTemplateId) {
                int var7 = Res.distance(var1.cx, var1.cy, var6.cx, var6.cy);
                if (var3 == -1 || var7 < var3) {
                    var3 = var7;
                    var4 = var6;
                }
            }
        }

        return var4;
    }

    public static void chatPopup(String var0) {
        ChatPopup.a("[" + mResources.textLoading + "] " + var0, Char.getMyChar());
    }

    public final void j(int var1) {
        (new Thread(new ChangeZone(this, var1))).start();
    }

    static void a(GameScr var0, byte var1) {
        var0.e(var1);
    }

    private static void showMenuSetting() {
        MyVector var0 = new MyVector();
        var0.addElement(new Command(mResources.nw[7], 1100068));
        var0.addElement(new Command("NVHN TT", 11000911));
        var0.addElement(new Command("Cài Đặt ADV", 110008011));
        var0.addElement(new Command("Cài Đặt NPC", 11000810));
        var0.addElement(new Command("Cài Đặt LDGT", 11000811));
        var0.addElement(new Command("Cài Đặt Gom Đồ", 110008012));
        var0.addElement(new Command("Cài Đặt Mua Bán", 11000799));
        var0.addElement(new Command("Cài Đặt Lật Hình", 11000804));
        var0.addElement(new Command("Cài Auto Up", 11000910));
        GameCanvas.menu.showMenu(var0);
    }

    private static void showMenuNsoChen() {
        MyVector var0 = new MyVector();
        var0.addElement(new Command("Auto Up Tong", 11000965));
        var0.addElement(new Command("Hướng dẫn", 11000922));
        var0.addElement(new Command("Cài đặt", 11000950));
        var0.addElement(new Command("Săn Bí Kíp", 11000934));
        var0.addElement(new Command("Tối ưu", 11000942));
        var0.addElement(new Command("Săn Boss", 11000913));
        var0.addElement(new Command("Hang Động 9x", 11000914));
        var0.addElement(new Command("Luyện Ngọc", 11000915));
        var0.addElement(new Command("Sự Kiện", 11000962));
        var0.addElement(new Command("Tinh Luyện", 11000940));
        var0.addElement(new Command("Đập Đồ", 11000918));
        var0.addElement(new Command("Dung Hợp", 11000932));
        var0.addElement(new Command("Tự Mua Shop", 11000930));
        var0.addElement(new Command("Câu Cá", 11000952));
        var0.addElement(new Command("Tự Dùng Item", 11000943));
        var0.addElement(new Command("Vi Thu", 11000958));
        var0.addElement(new Command("Giftcode", 11000938));
        var0.addElement(new Command("Auto Chat", 11000960));
        if (Char.getMyChar().ctypeClan == 4) {
            var0.addElement(new Command("Tộc Trưởng", 11000919));
        }
        GameCanvas.menu.showMenu(var0);
    }

    private static void showMenuNsoChenEvent() {
        MyVector var0 = new MyVector();
        var0.addElement(new Command("Gom Sự Kiện", 11000917));
        var0.addElement(new Command("Đổi Lồng Đèn", 11000954));
        var0.addElement(new Command("Rước Đèn", 11000956));
        var0.addElement(new Command("H\u00f3a Trang", 11000963));
        GameCanvas.menu.showMenu(var0);
    }

    private static void showMenuNsoChenGuide() {
        MyVector var0 = new MyVector();
        var0.addElement(new Command("Auto Up Tong", 11000966));
        var0.addElement(new Command("Săn Boss", 11000923));
        var0.addElement(new Command("HD9x", 11000924));
        var0.addElement(new Command("Gom Do", 11000949));
        var0.addElement(new Command("Gom Sự Kiện", 11000925));
        var0.addElement(new Command("Đập Đồ", 11000926));
        var0.addElement(new Command("Luyện Ngọc", 11000927));
        var0.addElement(new Command("Đổi Lồng Đèn", 11000955));
        var0.addElement(new Command("Rước Đèn", 11000957));
        var0.addElement(new Command("H\u00f3a Trang", 11000964));
        var0.addElement(new Command("Tinh Luyện", 11000941));
        var0.addElement(new Command("Dung Hợp", 11000933));
        var0.addElement(new Command("Tự Mua Shop", 11000931));
        var0.addElement(new Command("Câu Cá", 11000953));
        var0.addElement(new Command("Tự Dùng Item", 11000947));
        var0.addElement(new Command("Mở All", 11000928));
        var0.addElement(new Command("Săn Bí Kíp", 11000935));
        var0.addElement(new Command("Auto Nhiệm Vụ", 11000936));
        var0.addElement(new Command("Up Level", 11000937));
        var0.addElement(new Command("Vi Thu", 11000959));
        var0.addElement(new Command("Giftcode", 11000939));
        var0.addElement(new Command("Auto Chat", 11000961));
        if (Char.getMyChar().ctypeClan == 4) {
            var0.addElement(new Command("Tộc Trưởng", 11000929));
        }
        GameCanvas.menu.showMenu(var0);
    }

    private static void showNsoChenGuide(String title, String text) {
        getInstance().a(title, text, true);
    }

    private static void showMenuNsoChenClanLeader() {
        MyVector var0 = new MyVector();
        var0.addElement(new Command("Auto NPC gia tộc", 11000920));
        var0.addElement(new Command("Set Gom Sự Kiện GT", 11000921));
        GameCanvas.menu.showMenu(var0);
    }

    private static void showMenuSettingGomDo() {
        MyVector var0 = new MyVector();
        var0.addElement(new Command("Item gom", 1100080121));
        var0.addElement(new Command("Cài Đặt", 1100080125));
        GameCanvas.menu.showMenu(var0);
    }

    private static void hu() {
        MyVector var0 = new MyVector();
        var0.addElement(new Command("Lật Hình", 11000800));
        var0.addElement(new Command("Set Time Lật", 11000802));
        GameCanvas.menu.showMenu(var0);
    }

    static {
        GameCanvas.aw = GameCanvas.loadImage("/plus12.png");
        GameCanvas.dungHopImg = GameCanvas.loadImage("/Big4.png");
        ju = GameCanvas.loadImage("/trung1.png");
        jo = GameCanvas.loadImage("/u/select.png");
        dq = GameCanvas.loadImage("/hd/tf.png");
        ej = GameCanvas.loadImage("/eff/g132.png");
        ek = GameCanvas.loadImage("/eff/g10.png");
        el = GameCanvas.loadImage("/eff/g6.png");
        em = GameCanvas.loadImage("/eff/g99.png");
        en = GameCanvas.loadImage("/eff/g9.png");
        if (GameCanvas.isTouch) {
            jh = GameCanvas.loadImage("/hd/button.png");
            ji = GameCanvas.loadImage("/hd/button2.png");
            jj = GameCanvas.loadImage("/hd/hpp.png");
            jk = GameCanvas.loadImage("/hd/mpp.png");
            jl = GameCanvas.loadImage("/hd/right.png");
            jm = GameCanvas.loadImage("/hd/right2.png");
            jn = GameCanvas.loadImage("/hd/skill.png");
            ds = GameCanvas.loadImage("/hd/btnl.png");
            dt = GameCanvas.loadImage("/hd/btnlf.png");
            jc = GameCanvas.loadImage("/hd/arrow.png");
            jd = GameCanvas.loadImage("/hd/arrow2.png");
            je = GameCanvas.loadImage("/hd/chat.png");
            jg = GameCanvas.loadImage("/hd/focus.png");
            jf = GameCanvas.loadImage("/hd/menu.png");
            ja = GameCanvas.loadImage("/hd/topbar.png");
            jb = GameCanvas.loadImage("/hd/transparent.png");
            dr = GameCanvas.loadImage("/hd/mapborder.png");
        }

        du = GameCanvas.loadImage("/hd/mat.png");
        dv = GameCanvas.loadImage("/hd/lua.png");
        byte[] var0 = RMS.getRecord(GameMidlet.VERSION_250_PLUS ? "dataVersion250" : "dataVersion");
        byte[] var1 = RMS.getRecord("mapVersion");
        byte[] var2 = RMS.getRecord("skillVersion");
        byte[] var3 = RMS.getRecord("itemVersion");
        if (var0 != null) {
            di = var0[0];
        }

        if (var1 != null) {
            dj = var1[0];
        }

        if (var2 != null) {
            dk = var2[0];
        }

        if (var3 != null) {
            dl = var3[0];
        }

        keySkill = new Skill[3];
        arrSkill = new Skill[5];
        er = 0;
        lf = new int[5];
        lg = new int[5];
        lh = new int[5];
        li = new int[5];
        lj = new int[5];
        le = new String[5];
        lk = new int[8];

        for (int var4 = 0; var4 < 5; ++var4) {
            lj[var4] = -1;
        }

        ew = 140;
        ex = 160;
        ob = 6;
        ey = 0;
        fe = 0;
        qj = new MyVector();
        fj = 1;
        fk = true;
        qk = 0L;
        svTitle = "";
        svAction = "";
    }
}
