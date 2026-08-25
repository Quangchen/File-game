
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

public final class TileMap {

    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static char[] maps;
    public static int[] types;
    private static Image image1;
    private static Image image2;
    public static Image imgMiniMap;
    private static Image imgWaterfall;
    private static Image imgTopWaterfall;
    private static Image imgWaterflow;
    private static Image imgLeaf;
    private static Image imgflowRiver;
    public static byte size = 24;
    private static int ap;
    private static int aq;
    public static String mapName1 = null;
    public static String mapName = "";
    public static byte zoneID;
    public static byte bgID;
    public static byte typeMap;
    public static short mapID;
    public static short p = 0;
    private static int ar;
    private static int as;
    private static int at;
    private static int au;
    private static int av;
    private static int aw;
    private static int ax;
    private static int ay;
    public static int wMiniMap;
    public static int hMiniMap;
    public static int posMiniMapX;
    public static int posMiniMapY;
    public static MyVector vGo = new MyVector();
    public static String[] mapNames;
    public static mHashtable locationStand = new mHashtable();
    public static mHashtable itemMap = new mHashtable();
    private static int az;
    public static int y = 2;
    public static int z;
    public static int aa;
    public static int ab;
    public static int ac;
    public static int ad;
    public static int ae;
    private static int[] ba = new int[]{5257738, 8807192};
    private static short[][] direction = new short[170][];
    private static boolean[] visited = new boolean[170];
    private static int[] arrayMap = new int[170];
    private static short[] backtrack = new short[170];
    public static int af;
    public static boolean ag;
    public static boolean ah;
    private static Object bf;
    private static byte[][] bg;
    private static Image[] bh;
    private static Image[] bi;
    private static int waypointFromMap = -1;
    private static int waypointNextMap = -1;
    private static int waypointIndex = -1;
    private static boolean waypointSpecial;
    private static long waypointStartTime;
    private static long waypointLastRetryTime;
    private static int waypointRetryCount;

    static {
        direction[0] = new short[]{27};
        direction[1] = new short[]{2, 3, 27, 72, 91, 94, 105, 114, 125, 157, 139, 113, 80};
        direction[2] = new short[]{6, 1};
        direction[3] = new short[]{1, 4};
        direction[4] = new short[]{3, 5};
        direction[5] = new short[]{7, 4};
        direction[6] = new short[]{7, 2, 20, 21};
        direction[7] = new short[]{6, 5, 8};
        direction[8] = new short[]{7, 9};
        direction[9] = new short[]{8, 10};
        direction[10] = new short[]{9, 11, 17, 22, 32, 38, 43, 48, 139};
        direction[11] = new short[]{12, 10};
        direction[12] = new short[]{11, 57};
        direction[13] = new short[]{57, 14};
        direction[14] = new short[]{13, 15};
        direction[15] = new short[]{14, 16};
        direction[16] = new short[]{15, 17};
        direction[17] = new short[]{16, 18, 10, 22, 32, 38, 43, 48, 139};
        direction[18] = new short[]{17, 19};
        direction[19] = new short[]{18, 58};
        direction[20] = new short[]{6};
        direction[21] = new short[]{22, 6};
        direction[22] = new short[]{23, 21, 10, 17, 32, 38, 43, 48, 139};
        direction[23] = new short[]{22, 69, 25};
        direction[24] = new short[]{59, 36};
        direction[25] = new short[]{23, 26};
        direction[26] = new short[]{27, 25};
        direction[27] = new short[]{26, 28, 1, 72, 91, 94, 105, 114, 125, 157, 139, 113, 80};
        direction[28] = new short[]{27, 60};
        direction[29] = new short[]{60, 30};
        direction[30] = new short[]{29, 31};
        direction[31] = new short[]{32, 30};
        direction[32] = new short[]{31, 61, 10, 17, 22, 38, 43, 48, 139};
        direction[33] = new short[]{61, 34};
        direction[34] = new short[]{35, 33};
        direction[35] = new short[]{34, 66};
        direction[36] = new short[]{37, 24};
        direction[37] = new short[]{36};
        direction[38] = new short[]{67, 68, 10, 17, 22, 32, 43, 48, 139};
        direction[39] = new short[]{72, 46, 40};
        direction[40] = new short[]{39, 65, 41};
        direction[41] = new short[]{42, 40, 43};
        direction[42] = new short[]{62, 41};
        direction[43] = new short[]{41, 44, 10, 17, 22, 32, 38, 48, 139};
        direction[44] = new short[]{43, 45};
        direction[45] = new short[]{44, 53};
        direction[46] = new short[]{63, 39, 47};
        direction[47] = new short[]{46, 48};
        direction[48] = new short[]{47, 50, 10, 17, 22, 32, 38, 43, 139};
        direction[49] = new short[]{50, 51};
        direction[50] = new short[]{48, 49};
        direction[51] = new short[]{52, 49};
        direction[52] = new short[]{51, 64};
        direction[53] = new short[]{54, 45};
        direction[54] = new short[]{55, 53};
        direction[55] = new short[]{54};
        direction[56] = new short[]{72};
        direction[57] = new short[]{12, 13};
        direction[58] = new short[]{19};
        direction[59] = new short[]{68, 24};
        direction[60] = new short[]{28, 29};
        direction[61] = new short[]{33, 32};
        direction[62] = new short[]{42};
        direction[63] = new short[]{46};
        direction[64] = new short[]{52};
        direction[65] = new short[]{40};
        direction[66] = new short[]{67, 35};
        direction[67] = new short[]{66, 38};
        direction[68] = new short[]{59, 38};
        direction[69] = new short[]{70, 23};
        direction[70] = new short[]{69, 71};
        direction[71] = new short[]{72, 70};
        direction[72] = new short[]{71, 39, 1, 27, 91, 94, 105, 114, 125, 157, 139, 113, 80};
        direction[73] = new short[]{1};
        direction[74] = new short[0];
        direction[75] = new short[0];
        direction[76] = new short[0];
        direction[77] = new short[0];
        direction[78] = new short[0];
        direction[79] = new short[0];
        direction[80] = new short[]{81, 82, 83};
        direction[81] = new short[]{80, 84};
        direction[82] = new short[]{80, 85};
        direction[83] = new short[]{80, 86};
        direction[84] = new short[]{81, 87};
        direction[85] = new short[]{82, 88};
        direction[86] = new short[]{83, 89};
        direction[87] = new short[]{84, 90};
        direction[88] = new short[]{85, 90};
        direction[89] = new short[]{86, 90};
        direction[90] = new short[0];
        direction[91] = new short[]{92};
        direction[92] = new short[]{91, 93};
        direction[93] = new short[]{92};
        direction[94] = new short[]{95};
        direction[95] = new short[]{94, 96};
        direction[96] = new short[]{95, 97};
        direction[97] = new short[]{96};
        direction[98] = new short[]{99};
        direction[99] = new short[]{98, 101, 100, 102};
        direction[100] = new short[]{99, 103};
        direction[101] = new short[]{99, 103};
        direction[102] = new short[]{99, 103};
        direction[103] = new short[]{101, 102, 104, 100};
        direction[104] = new short[]{103};
        direction[105] = new short[]{107, 106, 108};
        direction[106] = new short[]{105, 109};
        direction[107] = new short[]{105, 109};
        direction[108] = new short[]{105, 109};
        direction[109] = new short[]{106, 107, 108};
        direction[110] = new short[0];
        direction[111] = new short[0];
        direction[112] = new short[]{113};
        direction[113] = new short[]{112};
        direction[114] = new short[]{115};
        direction[115] = new short[]{114, 116};
        direction[116] = new short[]{115};
        direction[117] = new short[0];
        direction[118] = new short[0];
        direction[119] = new short[0];
        direction[120] = new short[0];
        direction[121] = new short[0];
        direction[122] = new short[0];
        direction[123] = new short[0];
        direction[124] = new short[0];
        direction[125] = new short[]{126};
        direction[126] = new short[]{125, 127};
        direction[127] = new short[]{126, 128};
        direction[128] = new short[]{127};
        direction[129] = new short[0];
        direction[130] = new short[0];
        direction[131] = new short[0];
        direction[132] = new short[0];
        direction[133] = new short[0];
        direction[134] = new short[]{138};
        direction[135] = new short[]{138};
        direction[136] = new short[]{138};
        direction[137] = new short[]{138};
        direction[138] = new short[]{134, 134, 135, 136, 137};
        direction[139] = new short[]{140};
        direction[140] = new short[]{139, 141};
        direction[141] = new short[]{140, 142};
        direction[142] = new short[]{141, 143};
        direction[143] = new short[]{142, 144};
        direction[144] = new short[]{143, 145};
        direction[145] = new short[]{144, 146};
        direction[146] = new short[]{145, 147};
        direction[147] = new short[]{146, 148};
        direction[148] = new short[]{147};
        direction[149] = new short[0];
        direction[150] = new short[0];
        direction[151] = new short[0];
        direction[152] = new short[0];
        direction[153] = new short[0];
        direction[154] = new short[0];
        direction[155] = new short[0];
        direction[156] = new short[0];
        direction[157] = new short[]{158, 159};
        direction[158] = new short[]{159, 157};
        direction[159] = new short[]{157, 158};
        direction[162] = new short[]{165, 163, 164};
        direction[163] = new short[]{162};
        direction[164] = new short[]{162};
        direction[165] = new short[]{162};
        direction[166] = new short[]{162};
        af = -1;
        ag = false;
        ah = false;
        bf = new Object();
        bg = new byte[170][];

        for (int var0 = 0; var0 < 160; ++var0) {
            int var1 = var0;
            InputStream var2 = null;

            try {
                var2 = "".getClass().getResourceAsStream("/map/" + var1);
                bg[var1] = new byte[var2.available()];
                var2.read(bg[var1]);
                var2.close();
            } catch (Exception var9) {
                var9.printStackTrace();
            } finally {
                try {
                    var2.close();
                } catch (Exception var8) {
                }

            }
        }

        bh = new Image[5];
        bi = new Image[5];
    }

    public static void a(int var0, int var1, int var2, int var3) {
        wMiniMap = var2;
        hMiniMap = var3;
        posMiniMapX = var0;
        posMiniMapY = var1;
    }

    public static void a() {
        av = Char.getMyChar().cx / 12;
        ar = Char.getMyChar().cy / 12;
        if (av > a * y - wMiniMap / 2) {
            av = a * y - wMiniMap;
        } else if (av < wMiniMap / 2) {
            av = 0;
        } else {
            av -= wMiniMap / 2;
        }

        if (ar < hMiniMap / 2) {
            ar = 0;
        } else {
            ar -= hMiniMap / 2;
        }

        if (ar > b * y - hMiniMap) {
            ar = b * y - hMiniMap;
        }

    }

    public static void b() {
        if (a * y >= wMiniMap || b * y >= hMiniMap) {
            if (as != ar) {
                au = ar - as << 2;
                at += au;
                as += at >> 4;
                at &= 15;
            }

            if (aw != av) {
                ay = av - aw << 2;
                ax += ay;
                aw += ax >> 4;
                ax &= 15;
            }
        }

    }

    public static void c() {
        image1 = null;
        System.gc();
    }

    static final void d() {
        if (imgLeaf == null) {
            imgLeaf = GameCanvas.loadImage("/t/uwt.png");
        }

        if (imgWaterfall == null) {
            imgWaterfall = GameCanvas.loadImage("/t/wtf.png");
        }

        if (imgTopWaterfall == null) {
            imgTopWaterfall = GameCanvas.loadImage("/t/twtf.png");
        }

        if (imgWaterflow == null) {
            imgWaterflow = GameCanvas.loadImage("/t/wts.png");
        }

        if (imgflowRiver == null) {
            imgflowRiver = GameCanvas.loadImage("/t/wts1.png");
        }

        System.gc();
    }

    public static void clearOptimizeImages() {
        try {
            imgMiniMap = null;
            imgWaterfall = null;
            imgTopWaterfall = null;
            imgWaterflow = null;
            imgLeaf = null;
            imgflowRiver = null;
        } catch (Exception var0) {
        }
    }

    public static void a(int var0) {
        d = b * size;
        c = a * size;

        try {
            for (int var1 = 0; var1 < a * b; ++var1) {
                int[] var10000;
                if (locationStand != null && locationStand.get(String.valueOf(var1)) != null) {
                    var10000 = types;
                    var10000[var1] |= 2;
                }

                if (var0 == 4) {
                    if (maps[var1] == 1 || maps[var1] == 2 || maps[var1] == 3 || maps[var1] == 4 || maps[var1] == 5 || maps[var1] == 6 || maps[var1] == '\t' || maps[var1] == '\n' || maps[var1] == 'O' || maps[var1] == 'P' || maps[var1] == '\r' || maps[var1] == 14 || maps[var1] == '+' || maps[var1] == ',' || maps[var1] == '-' || maps[var1] == '2') {
                        var10000 = types;
                        var10000[var1] |= 2;
                    }

                    if (maps[var1] == '\t' || maps[var1] == 11) {
                        var10000 = types;
                        var10000[var1] |= 4;
                    }

                    if (maps[var1] == '\n' || maps[var1] == '\f') {
                        var10000 = types;
                        var10000[var1] |= 8;
                    }

                    if (maps[var1] == '\r' || maps[var1] == 14) {
                        var10000 = types;
                        var10000[var1] |= 1024;
                    }

                    if (maps[var1] == 'L' || maps[var1] == 'M') {
                        var10000 = types;
                        var10000[var1] |= 64;
                        if (maps[var1] == 'N') {
                            var10000 = types;
                            var10000[var1] |= 4096;
                        }
                    }
                }

                if (var0 == 1) {
                    if (maps[var1] == 22) {
                        az = maps[var1] - 1;
                    }

                    if (maps[var1] == 1 || maps[var1] == 2 || maps[var1] == 3 || maps[var1] == 4 || maps[var1] == 5 || maps[var1] == 6 || maps[var1] == 7 || maps[var1] == '$' || maps[var1] == '%' || maps[var1] == '6' || maps[var1] == '[' || maps[var1] == '\\' || maps[var1] == ']' || maps[var1] == '^' || maps[var1] == 'I' || maps[var1] == 'J' || maps[var1] == 'a' || maps[var1] == 'b' || maps[var1] == 't' || maps[var1] == 'u' || maps[var1] == 'v' || maps[var1] == 'x' || maps[var1] == '=') {
                        var10000 = types;
                        var10000[var1] |= 2;
                    }

                    if (maps[var1] == 2 || maps[var1] == 3 || maps[var1] == 4 || maps[var1] == 5 || maps[var1] == 6 || maps[var1] == 20 || maps[var1] == 21 || maps[var1] == 22 || maps[var1] == 23 || maps[var1] == '$' || maps[var1] == '%' || maps[var1] == '&' || maps[var1] == '\'' || maps[var1] == '=') {
                        var10000 = types;
                        var10000[var1] |= 4096;
                    }

                    if (maps[var1] == '\b' || maps[var1] == '\t' || maps[var1] == '\n' || maps[var1] == '\f' || maps[var1] == '\r' || maps[var1] == 14 || maps[var1] == 30) {
                        var10000 = types;
                        var10000[var1] |= 16;
                    }

                    if (maps[var1] == 17) {
                        var10000 = types;
                        var10000[var1] |= 32;
                    }

                    if (maps[var1] == 18) {
                        var10000 = types;
                        var10000[var1] |= 128;
                    }

                    if (maps[var1] == '%' || maps[var1] == '&' || maps[var1] == '=') {
                        var10000 = types;
                        var10000[var1] |= 4;
                    }

                    if (maps[var1] == '$' || maps[var1] == '\'' || maps[var1] == '=') {
                        var10000 = types;
                        var10000[var1] |= 8;
                    }

                    if (maps[var1] == 19) {
                        var10000 = types;
                        var10000[var1] |= 64;
                        if ((types[var1 - a] & 4096) == 4096) {
                            var10000 = types;
                            var10000[var1] |= 4096;
                        }
                    }

                    if (maps[var1] == '#') {
                        var10000 = types;
                        var10000[var1] |= 2048;
                    }

                    if (maps[var1] == 7) {
                        var10000 = types;
                        var10000[var1] |= 1024;
                    }

                    if (maps[var1] == ' ' || maps[var1] == '!' || maps[var1] == '"') {
                        var10000 = types;
                        var10000[var1] |= 256;
                    }
                }

                if (var0 == 2) {
                    if (maps[var1] == 22 || maps[var1] == 'g' || maps[var1] == 'o') {
                        az = maps[var1] - 1;
                    }

                    if (maps[var1] == 1 || maps[var1] == 2 || maps[var1] == 3 || maps[var1] == 4 || maps[var1] == 5 || maps[var1] == 6 || maps[var1] == 7 || maps[var1] == '$' || maps[var1] == '%' || maps[var1] == '6' || maps[var1] == '=' || maps[var1] == 'I' || maps[var1] == 'L' || maps[var1] == 'M' || maps[var1] == 'N' || maps[var1] == 'O' || maps[var1] == 'R' || maps[var1] == 'S' || maps[var1] == 'b' || maps[var1] == 'c' || maps[var1] == 'd' || maps[var1] == 'f' || maps[var1] == 'g' || maps[var1] == 'l' || maps[var1] == 'm' || maps[var1] == 'n' || maps[var1] == 'p' || maps[var1] == 'q' || maps[var1] == 't' || maps[var1] == 'u' || maps[var1] == '}' || maps[var1] == '~' || maps[var1] == 127 || maps[var1] == 129 || maps[var1] == 130) {
                        var10000 = types;
                        var10000[var1] |= 2;
                    }

                    if (maps[var1] == 1 || maps[var1] == 3 || maps[var1] == 4 || maps[var1] == 5 || maps[var1] == 6 || maps[var1] == 20 || maps[var1] == 21 || maps[var1] == 22 || maps[var1] == 23 || maps[var1] == '$' || maps[var1] == '%' || maps[var1] == '&' || maps[var1] == '\'' || maps[var1] == '7' || maps[var1] == 'm' || maps[var1] == 'o' || maps[var1] == 'p' || maps[var1] == 'q' || maps[var1] == 'r' || maps[var1] == 's' || maps[var1] == 't' || maps[var1] == 127 || maps[var1] == 129 || maps[var1] == 130) {
                        var10000 = types;
                        var10000[var1] |= 4096;
                    }

                    if (maps[var1] == '\b' || maps[var1] == '\t' || maps[var1] == '\n' || maps[var1] == '\f' || maps[var1] == '\r' || maps[var1] == 14 || maps[var1] == 30 || maps[var1] == 135) {
                        var10000 = types;
                        var10000[var1] |= 16;
                    }

                    if (maps[var1] == 17) {
                        var10000 = types;
                        var10000[var1] |= 32;
                    }

                    if (maps[var1] == 18) {
                        var10000 = types;
                        var10000[var1] |= 128;
                    }

                    if (maps[var1] == '=' || maps[var1] == '%' || maps[var1] == '&' || maps[var1] == 127 || maps[var1] == 130 || maps[var1] == 131) {
                        var10000 = types;
                        var10000[var1] |= 4;
                    }

                    if (maps[var1] == '=' || maps[var1] == '$' || maps[var1] == '\'' || maps[var1] == 127 || maps[var1] == 129 || maps[var1] == 132) {
                        var10000 = types;
                        var10000[var1] |= 8;
                    }

                    if (maps[var1] == 19) {
                        var10000 = types;
                        var10000[var1] |= 64;
                        if ((types[var1 - a] & 4096) == 4096) {
                            var10000 = types;
                            var10000[var1] |= 4096;
                        }
                    }

                    if (maps[var1] == 134) {
                        var10000 = types;
                        var10000[var1] |= 64;
                        if ((types[var1 - a] & 4096) == 4096) {
                            var10000 = types;
                            var10000[var1] |= 4096;
                        }
                    }

                    if (maps[var1] == '#') {
                        var10000 = types;
                        var10000[var1] |= 2048;
                    }

                    if (maps[var1] == 7) {
                        var10000 = types;
                        var10000[var1] |= 1024;
                    }

                    if (maps[var1] == ' ' || maps[var1] == '!' || maps[var1] == '"') {
                        var10000 = types;
                        var10000[var1] |= 256;
                    }

                    if (maps[var1] == '=' || maps[var1] == 127) {
                        var10000 = types;
                        var10000[var1] |= 8192;
                    }
                }

                if (var0 == 3) {
                    if (maps[var1] == '\f' || maps[var1] == '3' || maps[var1] == 'X' || maps[var1] == 't' || maps[var1] == 128) {
                        az = maps[var1] - 1;
                    }

                    if (maps[var1] == 'm' || maps[var1] == 'n') {
                        az = maps[var1];
                    }

                    if (maps[var1] == 1 || maps[var1] == 2 || maps[var1] == 3 || maps[var1] == 4 || maps[var1] == 5 || maps[var1] == 6 || maps[var1] == 7 || maps[var1] == 11 || maps[var1] == 14 || maps[var1] == 17 || maps[var1] == '+' || maps[var1] == '3' || maps[var1] == '?' || maps[var1] == 'A' || maps[var1] == 'C' || maps[var1] == 'D' || maps[var1] == 'G' || maps[var1] == 'H' || maps[var1] == 'S' || maps[var1] == 'T' || maps[var1] == 'U' || maps[var1] == 'W' || maps[var1] == '[' || maps[var1] == '^' || maps[var1] == 'a' || maps[var1] == 'b' || maps[var1] == 'j' || maps[var1] == 'k' || maps[var1] == 'o' || maps[var1] == 'q' || maps[var1] == 'u' || maps[var1] == 'v' || maps[var1] == 'w' || maps[var1] == '}' || maps[var1] == '~' || maps[var1] == 129 || maps[var1] == 130 || maps[var1] == 131 || maps[var1] == 133 || maps[var1] == 136 || maps[var1] == 138 || maps[var1] == 139 || maps[var1] == 142) {
                        var10000 = types;
                        var10000[var1] |= 2;
                    }

                    if (maps[var1] == '|' || maps[var1] == 't' || maps[var1] == '{' || maps[var1] == ',' || maps[var1] == '\f' || maps[var1] == 15 || maps[var1] == 16 || maps[var1] == '-' || maps[var1] == '\n' || maps[var1] == '\t') {
                        var10000 = types;
                        var10000[var1] |= 4096;
                    }

                    if (maps[var1] == 23) {
                        var10000 = types;
                        var10000[var1] |= 32;
                    }

                    if (maps[var1] == 24) {
                        var10000 = types;
                        var10000[var1] |= 128;
                    }

                    if (maps[var1] == 6 || maps[var1] == 15 || maps[var1] == '3' || maps[var1] == '_' || maps[var1] == 'a' || maps[var1] == 'j' || maps[var1] == 'o' || maps[var1] == '{' || maps[var1] == '}' || maps[var1] == 138 || maps[var1] == 140) {
                        var10000 = types;
                        var10000[var1] |= 4;
                    }

                    if (maps[var1] == 7 || maps[var1] == 16 || maps[var1] == '3' || maps[var1] == '`' || maps[var1] == 'b' || maps[var1] == 'k' || maps[var1] == 'o' || maps[var1] == '|' || maps[var1] == '~' || maps[var1] == 139 || maps[var1] == 141) {
                        var10000 = types;
                        var10000[var1] |= 8;
                    }

                    if (maps[var1] == 25) {
                        var10000 = types;
                        var10000[var1] |= 64;
                        if ((types[var1 - a] & 4096) == 4096) {
                            var10000 = types;
                            var10000[var1] |= 4096;
                        }
                    }

                    if (maps[var1] == '"') {
                        var10000 = types;
                        var10000[var1] |= 2048;
                    }

                    if (maps[var1] == 17) {
                        var10000 = types;
                        var10000[var1] |= 1024;
                    }

                    if (maps[var1] == '!' || maps[var1] == 'g' || maps[var1] == 'h' || maps[var1] == 'i' || maps[var1] == 26 || maps[var1] == '!') {
                        var10000 = types;
                        var10000[var1] |= 256;
                    }

                    if (maps[var1] == '3' || maps[var1] == 'o' || maps[var1] == 'D') {
                        var10000 = types;
                        var10000[var1] |= 8192;
                    }

                    if (maps[var1] == 'R' || maps[var1] == 'n' || maps[var1] == 143) {
                        var10000 = types;
                        var10000[var1] |= 16384;
                    }

                    if (maps[var1] == 'q') {
                        var10000 = types;
                        var10000[var1] |= 65536;
                    }

                    if (maps[var1] == 142) {
                        var10000 = types;
                        var10000[var1] |= 32768;
                    }

                    if (maps[var1] == '(' || maps[var1] == ')') {
                        var10000 = types;
                        var10000[var1] |= 131072;
                    }

                    if (maps[var1] == 'n') {
                        var10000 = types;
                        var10000[var1] |= 262144;
                    }

                    if (maps[var1] == 143) {
                        var10000 = types;
                        var10000[var1] |= 524288;
                    }
                }

                if (var0 == 5) {
                    if (maps[var1] == 6 || maps[var1] == 7 || maps[var1] == 8 || maps[var1] == 36 || maps[var1] == 50
                            || maps[var1] == 51 || maps[var1] == 55 || maps[var1] == 56 || maps[var1] == 57 || maps[var1] == 58
                            || maps[var1] == 59 || maps[var1] == 68 || maps[var1] == 70 || maps[var1] == 77 || maps[var1] == 73
                            || maps[var1] == 74 || maps[var1] == 75 || maps[var1] == 78 || maps[var1] == 117 || maps[var1] == 118
                            || maps[var1] == 130) {
                        int[] var58 = types;
                        var58[var1] |= 2;
                    }

                    if (maps[var1] == 6 || maps[var1] == 117) {
                        int[] var59 = types;
                        var59[var1] |= 4;
                    }

                    if (maps[var1] == 11 || maps[var1] == 10) {
                        int[] var60 = types;
                        var60[var1] |= 32;
                    }

                    if (maps[var1] == 12 || maps[var1] == 13) {
                        int[] var61 = types;
                        var61[var1] |= 64;
                    }

                    if (maps[var1] == 7 || maps[var1] == 117) {
                        int[] var62 = types;
                        var62[var1] |= 8;
                    }
                }
            }

            imgMiniMap = Image.createImage(a * y * mGraphics.zoomLevel, b * y * mGraphics.zoomLevel);
            mGraphics var6;
            (var6 = new mGraphics(imgMiniMap.getGraphics())).setColor(0);
            var6.fillRect(0, 0, a * y, b * y);

            for (var0 = 0; var0 < a; ++var0) {
                for (int var2 = 0; var2 < b; ++var2) {
                    int var3;
                    if ((var3 = maps[var2 * a + var0] - 1) != -1) {
                        var6.drawRegion(image2, 0, var3 * y, y, y, 0, var0 * y, var2 * y, 0);
                    }
                }
            }

            if (!GameCanvas.lowGraphic && !FormToiUu.isHideCharEffect()) {
                if (mapID == 0 || mapID <= 4 || mapID >= 16 && mapID <= 18 || mapID >= 24 && mapID <= 27 || mapID == 22 || mapID == 33 || mapID == 34 || mapID == 38 || mapID == 57 || mapID == 58 || mapID == 60 || mapID == 68 || mapID >= 70 && mapID <= 75 || mapID == 81) {
                    Effect2.vAnimateEffect.addElement(new AnimateEffect((byte) 1, 10));
                }

                if (mapID >= 39 && mapID <= 44 || mapID >= 46 && mapID <= 48 || mapID == 56 || mapID >= 62 && mapID <= 65) {
                    Effect2.vAnimateEffect.addElement(new AnimateEffect((byte) 3, Res.b(150, 200)));
                    return;
                }
            }
        } catch (Exception var4) {
            System.out.println("Error Load Map");
            var4.printStackTrace();
            GameMidlet var5 = GameMidlet.instance;
            MotherCanvas.c = false;
            System.gc();
            var5.notifyDestroyed();
        }

    }

    public static final void a(mGraphics var0) {
        for (int var1 = GameScr.m; var1 < GameScr.o; ++var1) {
            for (int var2 = GameScr.n; var2 < GameScr.p; ++var2) {
                int var3 = maps[var2 * a + var1] - 1;
                if ((g(var1, var2) & 256) != 256) {
                    if (e == 4 && (g(var1, var2) & 64) == 64) {
                        var3 = var2 - 1;
                        if ((var3 = maps[var3 * a + var1] - 1) == 15) {
                            var0.drawRegion(image1, 0, 17 * size, 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if (var3 == 5) {
                            var0.drawRegion(image1, 0, 7 * size, 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if (var3 == 18 || var3 == 22 || var3 == 15) {
                            var0.drawRegion(image1, 0, 17 * size, 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if (var3 == 44 || var3 == 52 || var3 == 51) {
                            var0.drawRegion(image1, 0, 56 * size, 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if (var3 == 24 || var3 == 23 || var3 == 20 || var3 == 21 || var3 == 19 || var3 == 12 || var3 == 13) {
                            continue;
                        }

                        if (var3 != -1) {
                            var0.drawRegion(image1, 0, var3 * size, 24, 24, 0, var1 * size, var2 * size, 0);
                        } else if (var3 == -1) {
                            continue;
                        }
                    }

                    if (e == 1) {
                        if ((g(var1, var2) & 32) == 32) {
                            var0.drawRegion(imgWaterfall, 0, 24 * (GameCanvas.gameTick % 4), 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if ((g(var1, var2) & 64) == 64 || (g(var1, var2) & 2048) == 2048) {
                            if ((g(var1, var2 - 1) & 32) == 32) {
                                var0.drawRegion(imgWaterfall, 0, 24 * (GameCanvas.gameTick % 4), 24, 24, 0, var1 * size, var2 * size, 0);
                                continue;
                            }

                            if ((g(var1, var2 - 1) & 4096) == 4096) {
                                var0.drawRegion(image1, 0, 504, 24, 24, 0, var1 * size, var2 * size, 0);
                                continue;
                            }
                        }
                    }

                    if (e == 2) {
                        if ((g(var1, var2) & 32) == 32) {
                            var0.drawRegion(imgWaterfall, 0, 24 * (GameCanvas.gameTick % 8 >> 1), 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if (var3 == 17) {
                            var0.drawRegion(imgTopWaterfall, 0, 24 * (GameCanvas.gameTick % 8 >> 1), 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if (var3 == 133) {
                            var0.drawRegion(image1, 0, 132 * size, 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if ((g(var1, var2) & 64) == 64 || (g(var1, var2) & 2048) == 2048) {
                            if ((g(var1, var2 - 1) & 32) == 32) {
                                var0.drawRegion(imgWaterfall, 0, 24 * (GameCanvas.gameTick % 4), 24, 24, 0, var1 * size, var2 * size, 0);
                                continue;
                            }

                            if ((g(var1, var2 - 1) & 4096) == 4096) {
                                if ((var3 = f(var1, var2 - 1)) == 55) {
                                    var3 = 54;
                                } else if (var3 != 19 && var3 != 35) {
                                    if (var3 < 40) {
                                        var3 = 21;
                                    } else {
                                        var3 = 110;
                                    }
                                } else if ((var3 = f(var1, var2 - 2)) == 55) {
                                    var3 = 54;
                                } else if (var3 < 40) {
                                    var3 = 21;
                                }

                                var0.drawRegion(image1, 0, var3 * 24, 24, 24, 0, var1 * size, var2 * size, 0);
                                continue;
                            }
                        }
                    }

                    if (e == 3) {
                        if ((g(var1, var2) & 32) == 32) {
                            var0.drawRegion(imgWaterfall, 0, 24 * (GameCanvas.gameTick % 8 >> 1), 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if (var3 == 23) {
                            var0.drawRegion(imgTopWaterfall, 0, 24 * (GameCanvas.gameTick % 8 >> 1), 24, 24, 0, var1 * size, var2 * size, 0);
                            continue;
                        }

                        if ((g(var1, var2) & 64) == 64 || (g(var1, var2) & 2048) == 2048) {
                            if ((g(var1, var2 - 1) & 32) == 32) {
                                var0.drawRegion(imgWaterfall, 0, 24 * (GameCanvas.gameTick % 4), 24, 24, 0, var1 * size, var2 * size, 0);
                                continue;
                            }

                            if ((g(var1, var2 - 1) & 4096) == 4096) {
                                if ((var3 = f(var1, var2 - 1)) == 25) {
                                    var3 = f(var1, var2 - 2);
                                }

                                if (var3 == 45) {
                                    var3 = 44;
                                }

                                --var3;
                                var0.drawRegion(image1, 0, var3 * 24, 24, 24, 0, var1 * size, var2 * size, 0);
                                continue;
                            }
                        }
                    }

                    if ((g(var1, var2) & 16) == 16) {
                        ap = var1 * size - GameScr.cmx - GameScr.d;
                        aq = (size - 2) * ap / size + GameScr.d;
                        var0.drawRegion(image1, 0, var3 * size, 24, 24, 0, aq + GameScr.cmx, var2 * size, 0);
                    } else if ((g(var1, var2) & 512) == 512) {
                        if (var3 != -1) {
                            var0.drawRegion(image1, 0, var3 * size, 24, 1, 0, var1 * size, var2 * size, 0);
                            var0.drawRegion(image1, 0, var3 * size, 24, 24, 0, var1 * size, var2 * size + 1, 0);
                        }
                    } else if (var3 != -1) {
                        var0.drawRegion(image1, 0, var3 * size, 24, 24, 0, var1 * size, var2 * size, 0);
                    }
                }
            }
        }

    }

    public static final void b(mGraphics var0) {
        if (GameCanvas.width > 176) {
            if (imgMiniMap == null) {
                return;
            }

            Res.a(var0);
            var0.translateXY(posMiniMapX + 1, posMiniMapY + 2);
            var0.setColor(0);
            var0.fillRect(-2, -2, wMiniMap + 2, hMiniMap);
            var0.setClip(-2, -2, wMiniMap + 4, hMiniMap + 4);

            int var1;
            for (var1 = 0; var1 < 2; ++var1) {
                var0.setColor(ba[var1]);
                var0.drawRect(var1 - 2, var1 - 2, wMiniMap + 2 - (var1 << 1), hMiniMap - (var1 << 1));
            }

            var0.setClip(0, 0, wMiniMap - 2, hMiniMap - 3);
            if (mGraphics.getWidth(imgMiniMap) > wMiniMap || mGraphics.getHeight(imgMiniMap) > hMiniMap) {
                var0.translateXY(-aw, -as);
            }

            var0.drawImage(imgMiniMap, 0, 0, 0);

            int var2;
            int var3;
            for (var3 = 0; var3 < Auto.t.size(); ++var3) {
                Mob var4;
                var1 = (var4 = (Mob) Auto.t.elementAt(var3)).curX / 12;
                var2 = var4.curY / 12;
                if (var1 < ay) {
                    var1 = ay;
                }

                if (var2 < au) {
                    var2 = au;
                }

                if (var1 > ay + wMiniMap) {
                    var1 = ay + wMiniMap;
                }

                if (var2 > au + hMiniMap) {
                    var2 = au + hMiniMap;
                }

                if (GameCanvas.gameTick % 10 < 8) {
                    var0.setColor(16777215);
                    var0.fillRect(var1 - 2, var2 - 2, 5, 5);
                    var0.setColor(var4.levelBoss == 1 ? 255 : (var4.levelBoss == 2 ? 16711935 : '￿'));
                    var0.fillRect(var1 - 1, var2 - 1, 3, 3);
                }
            }

            var1 = Char.getMyChar().cx / 12;
            var2 = Char.getMyChar().cy / 12;
            var0.setColor(16777215);
            var0.fillRect(var1 - 2, var2 - 2, 5, 5);
            var0.setColor(16711680);
            var0.fillRect(var1 - 1, var2 - 1, 3, 3);
            if (Code.attackChangePosition) {
                for (var3 = 0; var3 < Code.s.size(); ++var3) {
                    var1 = ((Integer) Code.s.elementAt(var3)).intValue() / 12;
                    var2 = ((Integer) Code.t.elementAt(var3)).intValue() / 12;
                    if (Code.r == var3) {
                        var0.setColor(16777215);
                        var0.fillRect(var1 - 2, var2 - 2, 5, 5);
                    }

                    var0.setColor(16777215);
                    var0.fillRect(var1 - 1, var2 - 1, 3, 3);
                }
            }

            for (var3 = 0; var3 < GameScr.vParty.size(); ++var3) {
                Party var5;
                if ((var5 = (Party) GameScr.vParty.elementAt(var3)).f != null && var5.f != Char.getMyChar()) {
                    var1 = var5.f.cx / 12;
                    var2 = var5.f.cy / 12;
                    if (var1 < ay) {
                        var1 = ay;
                    }

                    if (var2 < au) {
                        var2 = au;
                    }

                    if (var1 > ay + wMiniMap) {
                        var1 = ay + wMiniMap;
                    }

                    if (var2 > au + hMiniMap) {
                        var2 = au + hMiniMap;
                    }

                    if (GameCanvas.gameTick % 10 < 8) {
                        var0.setColor(16777215);
                        var0.fillRect(var1 - 2, var2 - 2, 5, 5);
                        var0.setColor(65280);
                        var0.fillRect(var1 - 1, var2 - 1, 3, 3);
                    }
                }
            }

            Res.a(var0);
            if (GameCanvas.isTouch) {
                var0.drawImage(GameScr.dr, posMiniMapX - 1, posMiniMapY, 0);
                paintNsoChenRightInfo(var0);
            }
        }

    }

    public static void paintNsoChenRightInfo(mGraphics var0) {
        if (!GameCanvas.isTouch || GameCanvas.width <= 176) {
            return;
        }

        Res.a(var0);
        int var1 = posMiniMapX - 15;
        int var2 = posMiniMapY + 45;
        mFont.tahoma_7_yellow.writeText(var0, "Map: " + mapID + ". Khu: " + zoneID, var1, var2, 0, mFont.tahoma_7_red);
        paintNsoChenAutoStatus(var0, var1, var2 + 12);
    }

    private static void paintNsoChenAutoStatus(mGraphics var0, int var1, int var2) {
        String var3 = AutoLuyenNgoc.getAutoText();
        if (var3.length() > 0) {
            mFont.tahoma_7_yellow.writeText(var0, var3, var1, var2, 0, mFont.tahoma_7_red);
            var2 += 12;
        }

        var3 = AutoRuocDen.getAutoText();
        if (var3.length() > 0) {
            mFont.tahoma_7_yellow.writeText(var0, var3, var1, var2, 0, mFont.tahoma_7_red);
            var2 += 12;
        }

        var3 = AutoViThu.getAutoText();
        if (var3.length() > 0) {
            mFont.tahoma_7_yellow.writeText(var0, var3, var1, var2, 0, mFont.tahoma_7_red);
            var2 += 12;
        }

        var3 = AutoDoiLongDen.getAutoText();
        if (var3.length() > 0) {
            mFont.tahoma_7_yellow.writeText(var0, var3, var1, var2, 0, mFont.tahoma_7_red);
        }
    }

    public static final void c(mGraphics var0) {
        if (!GameCanvas.lowGraphic) {
            if (imgLeaf == null || imgWaterfall == null || imgTopWaterfall == null || imgWaterflow == null || imgflowRiver == null) {
                d();
            }

            int var1;
            int var2;
            for (var1 = GameScr.m; var1 < GameScr.o; ++var1) {
                for (var2 = GameScr.n; var2 < GameScr.p; ++var2) {
                    Image var3;
                    if (e == 4) {
                        var3 = imgflowRiver;
                    } else {
                        var3 = imgWaterflow;
                    }

                    if ((g(var1, var2) & 2048) == 2048) {
                        var0.drawImage(imgLeaf, var1, var2, 0);
                    }

                    if ((g(var1, var2) & 64) == 64) {
                        var0.drawRegion(var3, 0, (GameCanvas.gameTick % 8 >> 2) * 24, 24, 24, 0, var1 * size, var2 * size, 0);
                    }

                    if ((g(var1, var2) & 256) == 256) {
                        var0.drawRegion(image1, 0, (maps[var2 * a + var1] - 1) * size, 24, 24, 0, var1 * size, var2 * size, 0);
                    }
                }
            }

            if (e != 4 && GameCanvas.isTouch && GameCanvas.isMinWidth240 && GameScr.p >= b - 2) {
                for (var1 = GameScr.m; var1 < GameScr.o; ++var1) {
                    var2 = b - 2;
                    int var5 = maps[var2 * a + var1] - 1;
                    int var4;
                    if ((g(var1, var2) & 32) == 32) {
                        for (var4 = 1; var4 <= 4; ++var4) {
                            var0.drawRegion(imgWaterfall, 0, 24 * (GameCanvas.gameTick % 4), 24, 24, 0, var1 * size, (var2 + var4) * size, 0);
                        }
                    } else {
                        if (mapID == 64) {
                            az = 115;
                        }

                        if ((g(var1, var2) & 2) == 2 || (g(var1, var2) & 64) == 64) {
                            var5 = az;
                        }

                        if (var5 >= 0) {
                            for (var4 = 1; var4 <= 4; ++var4) {
                                var0.drawRegion(image1, 0, var5 * size, 24, 24, 0, var1 * size, (var2 + var4) * size, 0);
                            }
                        }
                    }
                }
            }
        }

    }

    private static int f(int var0, int var1) {
        try {
            return maps[var1 * a + var0];
        } catch (Exception var2) {
            return 1000;
        }
    }

    private static int g(int var0, int var1) {
        try {
            return types[var1 * a + var0];
        } catch (Exception var2) {
            return 1000;
        }
    }

    public static final int a(int var0, int var1) {
        try {
            return types[var1 / size * a + var0 / size];
        } catch (Exception var2) {
            return 1000;
        }
    }

    public static final boolean a(int var0, int var1, int var2) {
        try {
            return (types[var1 / size * a + var0 / size] & var2) == var2;
        } catch (Exception var3) {
            return false;
        }
    }

    public static final void b(int var0, int var1) {
        int[] var10000 = types;
        int var10001 = var1 / size * a + var0 / size;
        var10000[var10001] |= 512;
    }

    public static final void c(int var0, int var1) {
        int[] var10000 = types;
        int var10001 = var1 / size * a + var0 / size;
        var10000[var10001] &= -513;
    }

    public static final int b(int var0) {
        return var0 / size * size;
    }

    public static final int c(int var0) {
        return var0 / size * size;
    }

    public static void e() {
        if (p != mapID) {
            switch (mapID) {
                case 1:
                    return;
                case 10:
                    return;
                case 17:
                    return;
                case 22:
                    return;
                case 27:
                    return;
                case 32:
                    return;
                case 38:
                    return;
                case 43:
                    return;
                case 48:
                    return;
                case 72:
                    return;
            }
        }

    }

    public static void f() {
        if (GameCanvas.gameTick % 700 == 0 && mapID != 0 && mapID > 4 && (mapID < 16 || mapID > 18) && (mapID < 24 || mapID > 27) && mapID != 22 && mapID != 33 && mapID != 34 && mapID != 38 && mapID != 57 && mapID != 58 && mapID != 60 && mapID != 68 && (mapID < 70 || mapID > 75) && mapID != 81) {
            if (mapID >= 39 && mapID <= 44 || mapID >= 46 && mapID <= 48 || mapID == 56 || mapID >= 62 && mapID <= 65) {
                return;
            }

            if (mapID == 29 || mapID == 35) {
                return;
            }

            if (mapID == 50 || mapID == 51 || mapID == 52) {
                return;
            }

            if (mapID == 64) {
                if (Res.b(0, 8) % 2 == 0) {
                    return;
                }

                return;
            }
        }

    }

    public static boolean isLang(int var0) {
        return var0 == 10 || var0 == 17 || var0 == 22 || var0 == 32 || var0 == 38 || var0 == 43 || var0 == 48 || var0 == 138 || var0 == 162;
    }
    
    public static boolean isVDMQ(int var0) {
        return var0 >= 139 && var0 <= 148;
    }

    public static boolean isLangCo(int var0) {
        return var0 >= 134 && var0 <= 138;
    }
    
    public static boolean isLangTT(int var0) {
        return var0 >= 162 && var0 <= 165;
    }

    public static boolean isTruong(int var0) {
        return var0 == 1 || var0 == 27 || var0 == 72;
    }

    public static boolean isHang(int var0) {
        return var0 == 91 || var0 == 92 || var0 == 93 || var0 == 94 || var0 == 95 || var0 == 96 || var0 == 97 || var0 == 105 || var0 == 106 || var0 == 107 || var0 == 108 || var0 == 109 || var0 == 114 || var0 == 115 || var0 == 116 || var0 == 125 || var0 == 126 || var0 == 127 || var0 == 128 || var0 == 157 || var0 == 158 || var0 == 159;
    }
    
    public static boolean isClanDun() {
        int mapID = TileMap.mapID;
        return mapID == 80 || mapID == 81 || mapID == 82 || mapID == 83 || mapID == 84 || mapID == 85 || mapID == 86 || mapID == 87 || mapID == 88 || mapID == 89 || mapID == 90 || mapID == 167;
    }

    public static int h(int var0) {
        if (isHang(var0)) {
            switch (var0) {
                case 91:
                    return 92;
                case 92:
                    return 93;
                case 94:
                    return 95;
                case 95:
                    return 96;
                case 96:
                    return 97;
                case 105:
                    return 106;
                case 106:
                    return 107;
                case 107:
                    return 108;
                case 108:
                    return 109;
                case 114:
                    return 115;
                case 115:
                    return 116;
                case 125:
                    return 126;
                case 126:
                    return 127;
                case 127:
                    return 128;
                case 157:
                    return 158;
                case 158:
                    return 159;
                case 159:
                    return 157;
            }
        }

        return -1;
    }

    public static int i(int var0) {
        if (isHang(var0)) {
            switch (var0) {
                case 92:
                    return 91;
                case 93:
                    return 92;
                case 95:
                    return 94;
                case 96:
                    return 95;
                case 97:
                    return 96;
                case 106:
                    return 105;
                case 107:
                    return 106;
                case 108:
                    return 107;
                case 109:
                    return 108;
                case 115:
                    return 114;
                case 116:
                    return 115;
                case 126:
                    return 125;
                case 127:
                    return 126;
                case 128:
                    return 127;
                case 158:
                    return 157;
                case 159:
                    return 158;
            }
        }

        return -1;
    }

    public static void j(int var0) {
        try {
            Waypoint var3;
            int var1 = (var3 = (Waypoint) vGo.elementAt(var0)).a;
            int var2 = var3.b;
            if (var3.b != 0 && var3.d < d - 24) {
                if (var3.c <= c / 2) {
                    var1 = var3.c + 12;
                    var2 = var3.d;
                } else if (var3.a >= c / 2) {
                    var1 = var3.a - 12;
                    var2 = var3.d;
                }
            } else if (var3.d <= d / 2) {
                var1 = (var3.c + var3.a) / 2;
                var2 = var3.d + 24;
            } else if (var3.b >= d / 2) {
                var1 = (var3.c + var3.a) / 2 + 24;
                var2 = var3.d - 48;
            }

            if (mapID != 114 && mapID != 115 && mapID != 116) {
                Char.charMove(var1, var2);
            } else {
                Char.e(var1, var2);
            }

            Thread.sleep(10L);
            Service.getInstance().c();
        } catch (InterruptedException ex) {

        }
    }

    private static int getRightWaypointIndex() {
        Char me = Char.getMyChar();
        int charY = me != null ? me.cy : d / 2;
        int result = -1;
        int bestX = -1;
        int bestDy = 999999;

        for (int i = 0; i < vGo.size(); ++i) {
            Waypoint waypoint = (Waypoint) vGo.elementAt(i);
            int rightX = waypoint.a > waypoint.c ? waypoint.a : waypoint.c;
            int midY = (waypoint.b + waypoint.d) / 2;
            int dy = Math.abs(midY - charY);

            if (rightX > bestX || rightX == bestX && dy < bestDy) {
                bestX = rightX;
                bestDy = dy;
                result = i;
            }
        }

        return result;
    }

    private static void moveToWaypointCenter(int waypointIndex) {
        try {
            Waypoint waypoint = (Waypoint) vGo.elementAt(waypointIndex);
            Char me = Char.getMyChar();
            int minX = waypoint.a < waypoint.c ? waypoint.a : waypoint.c;
            int maxX = waypoint.a > waypoint.c ? waypoint.a : waypoint.c;
            int minY = waypoint.b < waypoint.d ? waypoint.b : waypoint.d;
            int maxY = waypoint.b > waypoint.d ? waypoint.b : waypoint.d;
            int x = (minX + maxX) / 2;
            int y = (minY + maxY) / 2;
            int fromMap = mapID;

            if (me == null) {
                return;
            }

            if (me.cx < minX || me.cx > maxX || me.cy < minY || me.cy > maxY) {
                if (mapID != 114 && mapID != 115 && mapID != 116) {
                    Char.charMove(x, y);
                } else {
                    Char.e(x, y);
                }
                Thread.sleep(350L);
            }

            if (mapID != 114 && mapID != 115 && mapID != 116) {
                Char.charMove(x, y);
            } else {
                Char.e(x, y);
            }

            Thread.sleep(250L);
            for (int i = 0; i < 3 && mapID == fromMap; ++i) {
                Service.getInstance().c();
                Thread.sleep(180L);
            }
        } catch (Exception ignored) {
        }
    }

    private static boolean goSpecialWaypoint(int fromMap, int nextMap) {
        if (fromMap == 40 && nextMap == 41) {
            return moveThroughNormalGate(3000, 3048, 348, 360);
        }

        if (fromMap == 41 && nextMap == 40) {
            return moveThroughNormalGate(480, 552, 0, 24);
        }

        return false;
    }

    private static boolean isSpecialWaypoint(int fromMap, int nextMap) {
        return fromMap == 40 && nextMap == 41 || fromMap == 41 && nextMap == 40;
    }

    private static boolean moveThroughNormalGate(int minX, int maxX, int minY, int maxY) {
        try {
            Char me = Char.getMyChar();
            if (me == null) {
                return false;
            }

            int x = (minX + maxX) / 2;
            int y = (minY + maxY) / 2;
            int fromMap = mapID;

            if (me.cx < minX || me.cx > maxX || me.cy < minY || me.cy > maxY) {
                if (mapID != 114 && mapID != 115 && mapID != 116) {
                    Char.charMove(x, y);
                } else {
                    Char.e(x, y);
                }
                Thread.sleep(800L);
            }

            for (int i = 0; i < 3 && mapID == fromMap; ++i) {
                Service.getInstance().c();
                Thread.sleep(220L);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void g() {
        ah = true;
        synchronized (bf) {
            try {
                bf.wait(10000L);
            } catch (InterruptedException var1) {
            }

        }
        if (ah) {
            ah = false;
            GameCanvas.setMaxTextLenght();
            GameCanvas.e = false;
        }
    }

    public static void h() {
        clearWaypointWatchIfArrived();
        if (ah) {
            ah = false;
            synchronized (bf) {
                bf.notifyAll();
            }
        }
    }

    private static void startWaypointWatch(int fromMap, int nextMap, int index, boolean special) {
        waypointFromMap = fromMap;
        waypointNextMap = nextMap;
        waypointIndex = index;
        waypointSpecial = special;
        waypointStartTime = System.currentTimeMillis();
        waypointLastRetryTime = 0L;
        waypointRetryCount = 0;
    }

    private static void clearWaypointWatch() {
        waypointFromMap = -1;
        waypointNextMap = -1;
        waypointIndex = -1;
        waypointSpecial = false;
        waypointStartTime = 0L;
        waypointLastRetryTime = 0L;
        waypointRetryCount = 0;
    }

    private static void clearWaypointWatchIfArrived() {
        if (waypointNextMap >= 0 && (mapID == waypointNextMap || mapID != waypointFromMap)) {
            clearWaypointWatch();
        }
    }

    public static boolean isWaypointStuck() {
        return waypointNextMap >= 0 && mapID == waypointFromMap && mapID != waypointNextMap
                && System.currentTimeMillis() - waypointStartTime > 2500L;
    }

    public static String getWaypointStuckStatus() {
        return waypointNextMap >= 0 ? "Kẹt cổng " + waypointFromMap + "->" + waypointNextMap : null;
    }

    public static boolean retryWaypointIfStuck() {
        if (!isWaypointStuck()) {
            return false;
        }

        long now = System.currentTimeMillis();
        if (now - waypointLastRetryTime < 1600L) {
            return true;
        }

        retryWaypointTravel();
        waypointLastRetryTime = now;
        waypointStartTime = now;
        ++waypointRetryCount;
        return true;
    }

    private static void retryWaypointTravel() {
        if (waypointSpecial) {
            goSpecialWaypoint(waypointFromMap, waypointNextMap);
        } else if (waypointIndex >= 0) {
            j(waypointIndex);
        } else {
            Service.getInstance().c();
        }
    }

    private static boolean waitWaypointMapChange(int fromMap, int nextMap, int index, boolean special) {
        startWaypointWatch(fromMap, nextMap, index, special);
        long start = System.currentTimeMillis();

        while (ag && mapID == fromMap && mapID != nextMap && System.currentTimeMillis() - start < 9000L) {
            ah = true;
            synchronized (bf) {
                try {
                    bf.wait(900L);
                } catch (InterruptedException e) {
                }
            }

            if (mapID != fromMap || mapID == nextMap) {
                break;
            }

            if (waypointRetryCount < 3) {
                retryWaypointIfStuck();
            }
        }

        boolean ok = mapID == nextMap;
        if (ok) {
            clearWaypointWatch();
        } else {
            ah = false;
            GameCanvas.setMaxTextLenght();
            GameCanvas.e = false;
        }

        return ok;
    }

    private static boolean isMapIndex(int map) {
        return map >= 0 && map < direction.length && direction[map] != null;
    }

    private static boolean canGoMapEdge(int from, int to) {
        if (!isMapIndex(to)) {
            return false;
        }

        if (isTruong(from) && isTruong(to)) {
            Char me = Char.getMyChar();
            return me == null || me.ctaskId >= 9;
        }

        return true;
    }

    private static int addRouteNode(int from, int to, int[] queue, int tail) {
        if (canGoMapEdge(from, to) && arrayMap[to] == -1) {
            arrayMap[to] = arrayMap[from] + 1;
            backtrack[to] = (short) from;
            queue[tail++] = to;
        }

        return tail;
    }

    private static int encodeRouteStep(int from, int to, TaskOrder taskOrder) {
        if (isLang(from)) {
            if (isLang(to) && to != 138 && to != 162) {
                byte menu = 1;
                switch (to) {
                    case 10:
                        menu = 1;
                        break;
                    case 17:
                        menu = 2;
                        break;
                    case 22:
                        menu = 3;
                        break;
                    case 32:
                        menu = 4;
                        break;
                    case 38:
                        menu = 5;
                        break;
                    case 43:
                        menu = 6;
                        break;
                    case 48:
                        menu = 7;
                        break;
                    default:
                        break;
                }

                return from | Integer.MIN_VALUE | 117440512 | menu << 20 & 15728640;
            } else if (to == 139) {
                return from | Integer.MIN_VALUE | 83886080 | 2097152;
            }
        } else if (isTruong(from)) {
            if (isTruong(to)) {
                byte menu = 0;
                switch (to) {
                    case 1:
                        menu = 0;
                        break;
                    case 27:
                        menu = 1;
                        break;
                    case 72:
                        menu = 2;
                        break;
                    default:
                        break;
                }

                return from | Integer.MIN_VALUE | 134217728 | menu << 20 & 15728640;
            } else {
                int menu;
                if (taskOrder != null && to == taskOrder.mapId) {
                    menu = GameScr.fj;
                    return from | Integer.MIN_VALUE | 419430400 | menu << 20 & 15728640 | 196608;
                }

                switch (to) {
                    case 80:
                        return from | Integer.MIN_VALUE | 1048576 | 65536;
                    case 91:
                        return from | Integer.MIN_VALUE | 2097152 | 65536;
                    case 94:
                        return from | Integer.MIN_VALUE | 2097152 | 131072;
                    case 98:
                        menu = GameScr.fj + 2;
                        return from | Integer.MIN_VALUE | 419430400 | menu << 20 & 15728640;
                    case 104:
                        menu = GameScr.fj + 2;
                        return from | Integer.MIN_VALUE | 419430400 | menu << 20 & 15728640 | 65536;
                    case 105:
                        return from | Integer.MIN_VALUE | 2097152 | 196608;
                    case 113:
                        menu = GameScr.fj + 3;
                        return from | Integer.MIN_VALUE | 419430400 | menu << 20 & 15728640;
                    case 114:
                        return from | Integer.MIN_VALUE | 2097152 | 262144;
                    case 125:
                        return from | Integer.MIN_VALUE | 2097152 | 327680;
                    case 139:
                        return from | Integer.MIN_VALUE | 83886080 | 2097152;
                    case 157:
                        return from | Integer.MIN_VALUE | 2097152 | 393216;
                    default:
                        break;
                }
            }
        }

        return from;
    }

    private static MyVector buildRoute(int start, int target, TaskOrder taskOrder) {
        if (!isMapIndex(start) || !isMapIndex(target)) {
            return null;
        }

        MyVector reverse = new MyVector();
        reverse.addElement(new Integer(target));

        for (int map = target; map != start; map = backtrack[map]) {
            int from = backtrack[map];
            if (!isMapIndex(from)) {
                return null;
            }

            reverse.addElement(new Integer(encodeRouteStep(from, map, taskOrder)));
        }

        MyVector route = new MyVector();
        for (int i = reverse.size() - 1; i >= 0; --i) {
            route.addElement(reverse.elementAt(i));
        }

        return route;
    }

    private static MyVector findNearestRoute(int start, int target) {
        if (!isMapIndex(start) || !isMapIndex(target)) {
            return null;
        }

        TaskOrder taskOrder = Char.getTaskOrderById(0);
        for (int i = 0; i < direction.length; ++i) {
            visited[i] = false;
            arrayMap[i] = -1;
            backtrack[i] = -1;
        }

        int[] queue = new int[direction.length];
        int head = 0;
        int tail = 0;
        arrayMap[start] = 0;
        queue[tail++] = start;

        while (head < tail && arrayMap[target] == -1) {
            int map = queue[head++];
            visited[map] = true;
            short[] nextMaps = direction[map];

            for (int i = 0; i < nextMaps.length; ++i) {
                tail = addRouteNode(map, nextMaps[i], queue, tail);
                if (arrayMap[target] != -1) {
                    break;
                }
            }

            if (arrayMap[target] != -1) {
                break;
            }

            if (isTruong(map)) {
                if (taskOrder != null) {
                    tail = addRouteNode(map, taskOrder.mapId, queue, tail);
                }

                tail = addRouteNode(map, GameScr.fk ? 98 : 104, queue, tail);
            }

            if ((target == 138 || isLangCo(target)) && Char.tickAutoCoLenh) {
                tail = addRouteNode(map, 138, queue, tail);
            }

            if ((target == 162 || isLangTT(target)) && Char.tickAutoLangThuyenThuyet) {
                tail = addRouteNode(map, 162, queue, tail);
            }
        }

        return arrayMap[target] == -1 ? null : buildRoute(start, target, taskOrder);
    }

    private static int getDirectionIndex(int from, int to) {
        if (from == 138 && isLangCo(to)) {
            return 0;
        }

        if (!isMapIndex(from)) {
            return -1;
        }

        for (int i = 0; i < direction[from].length; ++i) {
            if (direction[from][i] == to) {
                return i;
            }
        }

        return -1;
    }

    public static boolean direction(int var0) {
        af = var0;
        if (leaveSpecialRegionForRoute(var0)) {
            return direction(var0);
        }
        if (followNearestRoute(var0, findNearestRoute(mapID, var0), false)) {
            return true;
        }

        ag = false;
        return directionOld(var0);
    }

    private static boolean leaveSpecialRegionForRoute(int targetMap) {
        if (!shouldLeaveSpecialRegion(mapID, targetMap)) {
            return false;
        }

        Auto.goTruongIfNeeded();
        Char me = Char.getMyChar();
        if (me != null && Auto.checkDead(me)) {
            Auto.autoRemap(true);
        }
        long start = System.currentTimeMillis();
        while (shouldLeaveSpecialRegion(mapID, targetMap) && System.currentTimeMillis() - start < 15000L) {
            try {
                Thread.sleep(300L);
            } catch (Exception e) {
            }
        }

        return !shouldLeaveSpecialRegion(mapID, targetMap);
    }

    private static boolean shouldLeaveSpecialRegion(int fromMap, int targetMap) {
        return isVDMQ(fromMap) && !isVDMQ(targetMap)
                || isLangCo(fromMap) && !isLangCo(targetMap)
                || isLangTT(fromMap) && !isLangTT(targetMap);
    }

    private static boolean followNearestRoute(int targetMap, MyVector route) {
        return followNearestRoute(targetMap, route, true);
    }

    private static boolean followNearestRoute(int targetMap, MyVector route, boolean notify) {
        if (route == null) {
            if (notify) {
                InfoMe.a("Không thể chuyển map!", 50, mFont.tahoma_7_yellow);
            }

            return false;
        }

        ag = true;

        try {
            int nextMap = mapID;

            for (int i = 1; i < route.size() && ag && nextMap == mapID; ++i) {
                int fromMap = ((Integer) route.elementAt(i - 1)).intValue();
                nextMap = ((Integer) route.elementAt(i)).intValue() & '\uffff';
                boolean waypointTravel = false;
                int waypointIndex = -1;
                boolean specialWaypoint = false;

                if (fromMap < 0) {
                    int npcId = fromMap >> 24 & 127;
                    int menu = fromMap >> 20 & 15;
                    int option = fromMap >> 16 & 15;
                    GameScr.PickNpc(npcId, menu, option);
                } else if ((fromMap < 134 || fromMap > 138) && nextMap == 138) {
                    if (Char.getMyChar().hieuChien > 0) {
                        InfoMe.a("Hiếu chiến quá cao!", 50, mFont.tahoma_7_yellow);
                        return false;
                    }

                    Item item;
                    if ((item = Char.getItemByID(490)) == null || item.template.id != 490) {
                        if (Char.tickAutoMuaCoLenh && Char.getMyChar().luong >= 10) {
                            Service.getInstance().buyItem1(14, 28, 2);
                            LockGame.g();
                            return false;
                        }

                        InfoMe.a("Không đủ cổ lệnh!", 50, mFont.tahoma_7_yellow);
                        return false;
                    }

                    Service.getInstance().useItem(item.indexUI);
                } else if ((fromMap < 162 || fromMap > 165) && nextMap == 162) {
                    if (Char.getMyChar().hieuChien > 0) {
                        InfoMe.a("Hiếu chiến quá cao!", 50, mFont.tahoma_7_yellow);
                        return false;
                    }

                    Item item = Char.getItemByID(Char.idTTL);
                    if (item == null || item.template.id != Char.idTTL) {
                        if (Char.tickAutoMuaTruyenThuyetLenh && Char.getMyChar().luong >= 10) {
                            Service.getInstance().buyItem1(14, Char.indexBuyTTL, 2);
                            LockGame.g();
                            return false;
                        }

                        InfoMe.a("Không đủ truyền thuyết lệnh!", 50, mFont.tahoma_7_yellow);
                        return false;
                    }

                    Service.getInstance().useItem(item.indexUI);
                } else if (fromMap != 0 && fromMap != 56 && fromMap != 73) {
                    waypointIndex = getDirectionIndex(fromMap, nextMap);
                    if (waypointIndex == -1) {
                        if (notify) {
                            InfoMe.a("Không thể chuyển map!", 50, mFont.tahoma_7_yellow);
                        }

                        return false;
                    }

                    waypointTravel = true;
                    specialWaypoint = isSpecialWaypoint(fromMap, nextMap);
                    if (!specialWaypoint || !goSpecialWaypoint(fromMap, nextMap)) {
                        j(waypointIndex);
                    }
                } else {
                    Npc npc;
                    if ((npc = (Npc) GameScr.ah.elementAt(0)) != null && npc.statusMe != 15) {
                        Char.charMove(npc.cx, npc.cy);
                        Char.getMyChar().npcFocus = npc;
                        Service.getInstance().requestItem(npc.template.npcTemplateId);
                        Service.getInstance().menu(npc.template.npcTemplateId, 0, 0);
                        Service.getInstance().getTask(npc.template.npcTemplateId, 0);
                    }
                }

                if (mapID != nextMap) {
                    if (waypointTravel) {
                        if (!waitWaypointMapChange(fromMap, nextMap, waypointIndex, specialWaypoint)) {
                            return false;
                        }
                    } else {
                        g();
                    }
                }

                Thread.sleep(60L);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ag = false;
        }

        return mapID == targetMap;
    }

    private static boolean directionOld(int var0) {
        short var10000 = mapID;
        af = var0;
        int var2 = var0;
        short var1 = var10000;
        int var4;
        int var5;
        int var6;
        MyVector var21;
        if(var0 == 134) direction[138][0] = 134;
        if(var0 == 135) direction[138][0] = 135;
        if(var0 == 136) direction[138][0] = 136;
        if(var0 == 137) direction[138][0] = 137;
        if (var10000 >= 0 && var1 < direction.length && var0 >= 0 && var0 < direction.length) {
            if (direction[var1].length <= 0) {
                var21 = null;
            } else {
                TaskOrder var3 = Char.getTaskOrderById(0);

                for (var4 = 0; var4 < visited.length; ++var4) {
                    visited[var4] = true;
                    arrayMap[var4] = -1;
                    backtrack[var4] = -1;
                }

                arrayMap[var1] = 0;

                label381:
                while (true) {
                    int var7 = -1;
                    if (!visited[var2]) {
                        MyVector var17;
                        (var17 = new MyVector()).addElement(new Integer(var2));

                        for (var6 = var2; var6 != var1; var6 = backtrack[var6]) {
                            byte var19;
                            if (isLang(var7 = backtrack[var6])) {
                                if (isLang(var6) && var6 != 138 && var6 != 162) {
                                    var19 = 1;
                                    switch (var6) {
                                        case 10:
                                            var19 = 1;
                                            break;
                                        case 17:
                                            var19 = 2;
                                            break;
                                        case 22:
                                            var19 = 3;
                                            break;
                                        case 32:
                                            var19 = 4;
                                            break;
                                        case 38:
                                            var19 = 5;
                                            break;
                                        case 43:
                                            var19 = 6;
                                            break;
                                        case 48:
                                            var19 = 7;
                                            break;
                                        default:
                                            break;
                                    }

                                    var7 = var7 | Integer.MIN_VALUE | 117440512 | var19 << 20 & 15728640;
                                } else if (var6 == 139) {
                                    var7 = var7 | Integer.MIN_VALUE | 83886080 | 2097152;
                                }
                            } else if (isTruong(var7)) {
                                if (isTruong(var6)) {
                                    var19 = 0;
                                    switch (var6) {
                                        case 1:
                                            var19 = 0;
                                            break;
                                        case 27:
                                            var19 = 1;
                                            break;
                                        case 72:
                                            var19 = 2;
                                            break;
                                        default:
                                            break;
                                    }

                                    var7 = var7 | Integer.MIN_VALUE | 134217728 | var19 << 20 & 15728640;
                                } else {
                                    int var18;
                                    if (var3 != null && var6 == var3.mapId) {
                                        var18 = GameScr.fj;
                                        var7 = var7 | Integer.MIN_VALUE | 419430400 | var18 << 20 & 15728640 | 196608;
                                    } else {
                                        switch (var6) {
                                            case 80:
                                                var7 = var7 | Integer.MIN_VALUE | 1048576 | 65536;
                                                break;
                                            case 91:
                                                var7 = var7 | Integer.MIN_VALUE | 2097152 | 65536;
                                                break;
                                            case 94:
                                                var7 = var7 | Integer.MIN_VALUE | 2097152 | 131072;
                                                break;
                                            case 98:
                                                var18 = GameScr.fj + 2;
                                                var7 = var7 | Integer.MIN_VALUE | 419430400 | var18 << 20 & 15728640;
                                                break;
                                            case 104:
                                                var18 = GameScr.fj + 2;
                                                var7 = var7 | Integer.MIN_VALUE | 419430400 | var18 << 20 & 15728640 | 65536;
                                                break;
                                            case 105:
                                                var7 = var7 | Integer.MIN_VALUE | 2097152 | 196608;
                                                break;
                                            case 113:
                                                var18 = GameScr.fj + 3;
                                                var7 = var7 | Integer.MIN_VALUE | 419430400 | var18 << 20 & 15728640;
                                                break;
                                            case 114:
                                                var7 = var7 | Integer.MIN_VALUE | 2097152 | 262144;
                                                break;
                                            case 125:
                                                var7 = var7 | Integer.MIN_VALUE | 2097152 | 327680;
                                                break;
                                            case 139:
                                                var7 = var7 | Integer.MIN_VALUE | 83886080 | 2097152;
                                                break;
                                            case 157:
                                                var7 = var7 | Integer.MIN_VALUE | 2097152 | 393216;
                                        }
                                    }
                                }
                            }

                            var17.addElement(new Integer(var7));
                        }

                        MyVector var20 = new MyVector();

                        for (var4 = var17.size() - 1; var4 >= 0; --var4) {
                            var20.addElement(var17.elementAt(var4));
                        }

                        var21 = var20;
                        break label381;
                    }

                    var5 = -1;
                    var6 = -1;

                    for (var4 = 0; var4 < direction.length; ++var4) {
                        if (visited[var4] && arrayMap[var4] != -1 && (arrayMap[var4] < var5 || var5 == -1)) {
                            var5 = arrayMap[var4];
                            var6 = (short) var4;
                        }
                    }

                    if (var6 == -1) {
                        var21 = null;
                        break label381;
                    }

                    visited[var6] = false;
                    boolean var16 = isTruong(var6);
                    short[] var8 = direction[var6];

                    for (var4 = 0; var4 < var8.length; ++var4) {
                        var7 = var8[var4];
                        if (visited[var7]) {
                            boolean var22 = true;
                            if (var22 && (!var16 || !isTruong(var7) || Char.getMyChar().ctaskId >= 9) && (arrayMap[var7] == -1 || arrayMap[var7] > arrayMap[var6] + 1)) {
                                arrayMap[var7] = arrayMap[var6] + 1;
                                backtrack[var7] = (short) var6;
                            }
                        }
                    }

                    if (var16 && var3 != null && visited[var3.mapId] && (arrayMap[var3.mapId] == -1 || arrayMap[var3.mapId] > arrayMap[var6] + 1)) {
                        arrayMap[var3.mapId] = arrayMap[var6] + 1;
                        backtrack[var3.mapId] = (short) var6;
                    }

                    if (var16) {
                        var7 = (short) (GameScr.fk ? 98 : 104);
                        if (arrayMap[var7] == -1 || arrayMap[var7] > arrayMap[var6] + 1) {
                            arrayMap[var7] = arrayMap[var6] + 1;
                            backtrack[var7] = (short) var6;
                        }
                    }
                    if (Char.tickAutoCoLenh && (arrayMap[138] == -1 || arrayMap[138] > arrayMap[var6] + 1)) {
                        arrayMap[138] = arrayMap[var6] + 1;
                        backtrack[138] = (short) var6;
                    }
                    if (Char.tickAutoLangThuyenThuyet && (arrayMap[162] == -1 || arrayMap[162] > arrayMap[var6] + 1)) {
                        arrayMap[162] = arrayMap[var6] + 1;
                        backtrack[162] = (short) var6;
                    }
                }
            }
        } else {
            var21 = null;
        }
        MyVector var12 = var21;
        if (var21 == null) {
            InfoMe.a("Không thể chuyển map!", 50, mFont.tahoma_7_yellow);
            return false;
        } else {
            ag = true;

            try {
                var6 = mapID;

                for (var2 = 1; var2 < var12.size() && ag && var6 == mapID; ++var2) {
                    var5 = ((Integer) var12.elementAt(var2 - 1)).intValue();
                    var6 = ((Integer) var12.elementAt(var2)).intValue() & '\uffff';
                    var4 = -1;
                    boolean var23 = false;
                    boolean var24 = false;
                    int var14;
                    if (var5 < 0) {
                        var14 = var5 >> 24 & 127;
                        var4 = var5 >> 20 & 15;
                        var5 = var5 >> 16 & 15;
                        GameScr.PickNpc(var14, var4, var5);
                    } else if ((var5 < 134 || var5 > 138) && var6 == 138) {
                        if (Char.getMyChar().hieuChien > 0) {
                            InfoMe.a("Hiếu chiến quá cao!", 50, mFont.tahoma_7_yellow);
                            return false;
                        }

                        Item var15;
                        if ((var15 = Char.getItemByID(490)) == null || var15.template.id != 490) {
                            if (Char.tickAutoMuaCoLenh && Char.getMyChar().luong >= 10) {
                                Service.getInstance().buyItem1(14, 28, 2);
                                LockGame.g();
                                return false;
                            }

                            InfoMe.a("Không đủ cổ lệnh!", 50, mFont.tahoma_7_yellow);
                            return false;
                        }

                        System.out.println("Dung co lenh");
                        Service.getInstance().useItem(var15.indexUI);
                    } else if ((var5 < 162 || var5 > 165) && var6 == 162) {
                        if (Char.getMyChar().hieuChien > 0) {
                            InfoMe.a("Hiếu chiến quá cao!", 50, mFont.tahoma_7_yellow);
                            return false;
                        }

                        Item var15 = Char.getItemByID(Char.idTTL);
                        System.out.println("Char.idTTL" + Char.idTTL + " var15: " + var15);
                        if (var15 == null || var15.template.id != Char.idTTL) {
                            if (Char.tickAutoMuaTruyenThuyetLenh && Char.getMyChar().luong >= 10) {
                                Service.getInstance().buyItem1(14, Char.indexBuyTTL, 2);
                                LockGame.g();
                                return false;
                            }

                            InfoMe.a("Không đủ truyền thuyết lệnh!", 50, mFont.tahoma_7_yellow);
                            return false;
                        }

                        System.out.println("Dung truyen thuyet lenh: " + var15.indexUI);
                        Service.getInstance().useItem(var15.indexUI);
                    } else if (var5 != 0 && var5 != 56 && var5 != 73) {
                        var4 = -1;

                        for (var14 = 0; var14 < direction[var5].length; ++var14) {
                            if (direction[var5][var14] == var6) {
                                var4 = var14;
                                break;
                            }
                        }

                        if (var4 == -1) {
                            InfoMe.a("Không thể chuyển map!", 50, mFont.tahoma_7_yellow);
                            return false;
                        }

                        var23 = true;
                        var24 = isSpecialWaypoint(var5, var6);
                        if (!var24 || !goSpecialWaypoint(var5, var6)) {
                            j(var4);
                        }
                    } else {
                        Npc var13;
                        if ((var13 = (Npc) GameScr.ah.elementAt(0)) != null && var13.statusMe != 15) {
                            Char.charMove(var13.cx, var13.cy);
                            Char.getMyChar().npcFocus = var13;
                            Service.getInstance().requestItem(var13.template.npcTemplateId);
                            Service.getInstance().menu(var13.template.npcTemplateId, 0, 0);
                            Service.getInstance().getTask(var13.template.npcTemplateId, 0);
                        }
                    }

                    if (mapID != var6) {
                        if (var23) {
                            if (!waitWaypointMapChange(var5, var6, var4, var24)) {
                                return false;
                            }
                        } else {
                            g();
                        }
                    }

                    Thread.sleep(100L);
                }
            } catch (Exception var11) {
                var11.printStackTrace();
                return false;
            }

            ag = false;
            return mapID == var0;
        }
    }

    public static void gomap(int var0) {
        GameCanvas.showWaiting();
        (new Thread(new GoMap(var0))).start();
    }

    public static void m(int var0) {
        GameCanvas.showWaiting();
        (new Thread(new NextMap(var0))).start();
    }

    public static int d(int var0, int var1) {
        var1 = b(var1);
        if (!a(var0, var1, 2)) {
            for (int var2 = 0; var2 < 7; ++var2) {
                int var3;
                if ((var3 = var1 - 48 + var2 * 24) > 0 && var3 < d && a(var0, var3, 2)) {
                    return var3;
                }
            }
        }

        return var1;
    }

    public static int e(int var0, int var1) {
        if ((a(var0, var1 - 16) & 16386) != 0) {
            var1 = b(var1);

            int var2;
            int var3;
            for (var2 = 24; var2 < 240; var2 += 24) {
                var3 = a(var0, var1 - var2);
                if (var1 - var2 > 0 && (var3 & 16386) == 0) {
                    return var1 - var2 + 24;
                }
            }

            for (var2 = 24; var2 < 120; var2 += 24) {
                var3 = a(var0, var1 + var2);
                if (var1 + var2 < d && (var3 & 16386) == 0) {
                    return var1 + var2;
                }
            }
        }

        return var1;
    }

    public static boolean a(int var0, int var1, int[] var2) {
        var1 = b(var1);
        if (a(var0, var1, 2)) {
            var2[0] = var0;
            var2[1] = var1;
            return true;
        } else {
            for (int var3 = 0; var3 < 5; ++var3) {
                int var4 = var1 + var3 * 24;

                for (int var5 = 0; var5 < 5; ++var5) {
                    int var6 = var0 - 48 + var5 * 24;
                    if (var4 < d && var6 > 24 && var6 < c - 24 && a(var6, var4, 2)) {
                        var2[0] = var6;
                        var2[1] = var4;
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static void a(int var0, InputStream var1) {
        try {
            if (bg.length <= var0) {
                byte[][] var2 = new byte[var0 + 10][];
                System.arraycopy(bg, 0, var2, 0, bg.length);
                bg = var2;
            }

            bg[var0] = new byte[var1.available()];
            var1.read(bg[var0]);
        } catch (Exception var3) {
        }
    }

    public static void i() {
        try {
            ByteArrayInputStream var0 = new ByteArrayInputStream(bg[mapID]);
            DataInputStream var2;
            a = (char) (var2 = new DataInputStream(var0)).readUnsignedByte();
            b = (char) var2.readUnsignedByte();
            maps = new char[var2.available()];

            for (int var1 = 0; var1 < a * b; ++var1) {
                maps[var1] = (char) var2.readUnsignedByte();
            }

            types = new int[maps.length];
        } catch (IOException ex) {

        }
    }

    public static void j() {
        for (int var0 = 1; var0 < bh.length; ++var0) {
            bh[var0] = GameCanvas.loadImage("/t/" + var0 + ".png");
            bi[var0] = GameCanvas.loadImage("/t/mini_" + var0 + ".png");
        }

    }

    public static void k() {
        image1 = null;
        System.gc();
        image1 = bh[e];
        image2 = bi[e];
    }
}
