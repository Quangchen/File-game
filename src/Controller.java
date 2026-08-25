
// Source code is decompiled from instance .class file using FernFlower decompiler.
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;

public final class Controller implements IController {

    private static Controller instance;
    private Message b;
    private static String[] c = new String[]{"Bạn chỉ có thể vào lại game sau ", " giây nữa"};
    private static String d = "Bạn chưa thể đi đến khu vực này.Hãy hoàn thành nhiệm vụ trước.";
    private static String e = "Cửa này vẫn chưa được mở.";
    private static String f = "Cửa này chỉ chứa được tối đa 2 người.";
    private static String g = "Số nhóm của khu vực này";
    private static String h = "Khu vực này đã đầy.";
    private static String i = "Thao tác quá nhanh.";

    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public final void b() {
        System.out.println("Connect ok");
    }

    public final void c() {
        if (Char.tickAutoReconnect && Code.auto != null) {
            Session_ME.getInstance().d();
        } else {
            GameCanvas.a(mResources.ef, 8884);
        }
    }

    public final void d() {
        System.out.println("Disconnected");
        Code.b();
        if (Code.auto instanceof Stanima && Res.getCurrentTime().get(11) == 3) {
            Stanima.d = true;
        }

        if (Char.tickAutoReconnect && Code.auto != null) {
            Session_ME.getInstance().d();
        } else {
            GameCanvas.instance.q();
        }
    }

    private static void b(Message var0) {
        try {
            int var1 = var0.reader().readUnsignedByte();
            Item var4;
            (var4 = GameScr.currentCharViewInfo.arrItemBody[var1]).expires = var0.reader().readLong();
            var4.saleCoinLock = var0.reader().readInt();
            var4.sys = var0.reader().readByte();
            var4.options = new MyVector();

            try {
                while (true) {
                    var4.options.addElement(new ItemOption(var0.reader().readUnsignedByte(), var0.reader().readInt()));
                }
            } catch (Exception var2) {
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    private static void c(Message var0) {
        try {
            Item var1 = null;
            int var2 = var0.reader().readInt();

            for (int var3 = 0; var3 < GameScr.arrItemStands.length; ++var3) {
                if (GameScr.arrItemStands[var3].item.itemId == var2) {
                    var1 = GameScr.arrItemStands[var3].item;
                    break;
                }
            }

            var1.typeUI = 37;
            var1.expires = -1L;
            var1.saleCoinLock = var0.reader().readInt();
            if (var1.isTypeBody() || var1.isTypeNgocKham()) {
                var1.options = new MyVector();

                try {
                    var1.upgrade = var0.reader().readByte();
                    var1.sys = var0.reader().readByte();

                    while (true) {
                        var1.options.addElement(new ItemOption(var0.reader().readUnsignedByte(), var0.reader().readInt()));
                    }
                } catch (Exception var4) {
                    return;
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public final void a(Message ms) {
        try {
            int var2;
            int var3;
            String var4;
            String name;
            int charID;
            int var7;
            Char var10000;
            long var40;
            Mob var70;
            int var77;
            int var78;
            String msgText;
            short var163;
            int var164;
            MyVector var165;
            Char var166;
            Item var167;
            short var168;
            Mob var169;
            Char var170;
            GameScr var171;
            Npc var172;
            short var173;
            Char[] var181;
            int type;
            Mob var184;
            short var185;
            BuNhin var186;
            String var187;
            byte var188;
            ItemMap var189;
            ItemMap var191;
            String var192;
            byte var197;
            Char var204;
            String var209;
            GameScr var222;
            switch (ms.command) {
                case -30:
                    g(ms);
                    return;
                case -29:
                    f(ms);
                    return;
                case -28:
                    this.e(ms);
                    return;
                case -27:
                case -17:
                case -9:
                case 12:
                case 24:
                case 28:
                case 29:
                case 32:
                case 35:
                case 41:
                case 44:
                case 56:
                case 73:
                case 74:
                case 80:
                case 81:
                case 105:
                case 110:
                case 111:
                case 113:
                case 115:
                case 120:
                default:
                    return;
                case -26:
                    GameCanvas.setText(msgText = ms.reader().readUTF());
                    boolean var199 = false;
                    boolean var190 = false;
                    if (msgText.startsWith(c[0])) {
                        var3 = 0;

                        try {
                            var3 = Integer.parseInt(msgText.substring(c[0].length(), msgText.indexOf(c[1])).trim());
                        } catch (Exception var148) {
                            var148.printStackTrace();
                        }

                        Session_ME.getInstance().b();
                        Session_ME.p = false;
                        Session_ME.e();
                        GameCanvas.ax = var3;
                        GameCanvas.az = GameCanvas.ay = System.currentTimeMillis();
                        return;
                    }

                    if (msgText.equals(i)) {
                        LockGame.r();
                        return;
                    }

                    if (!TileMap.ah || !msgText.equals(d) && !(var199 = msgText.equals(e)) && !(var190 = msgText.equals(f)) && !msgText.equals(h) && !msgText.startsWith(g)) {
                        return;
                    }

                    if (Code.auto != null && TileMap.isHang(TileMap.mapID)) {
                        if (var199) {
                            if ((var3 = TileMap.i(Code.auto.mapID)) > 0) {
                                Code.auto.mapID = var3;
                                if (Code.auto instanceof AutoHD9x) {
                                    ((AutoHD9x) Code.auto).syncTargetWithMap();
                                }
                            }

                            Code.auto.k = -1;
                        } else if (var190) {
                            if ((var3 = TileMap.h(Code.auto.mapID)) > 0) {
                                Code.auto.mapID = var3;
                                if (Code.auto instanceof AutoHD9x) {
                                    ((AutoHD9x) Code.auto).syncTargetWithMap();
                                }
                            }

                            Code.auto.k = -1;
                        }
                    }

                    if (TileMap.ag) {
                        TileMap.ag = false;
                    } else {
                        GameCanvas.setMaxTextLenght();
                    }

                    TileMap.h();
                    return;
                case -25:
                    Info.a(msgText = ms.reader().readUTF(), 150, mFont.tahoma_7b_yellow);
                    ChatManager.getInstance().chat(mResources.op[0], mResources.qt, msgText);
                    AutoBossScheduleManager.onWorldMessage(msgText);
                    if (Code.auto instanceof Stanima) {
                        Code.c.a(msgText);
                    }

                    if (Code.auto instanceof AutoBoss) {
                        ((AutoBoss) Code.auto).a(msgText);
                    }

                    return;
                case -24:
                    var192 = ms.reader().readUTF();
                    AutoLDGT.onInfoMessage(var192);
                    AutoHD9xManager.onInfoMessage(var192);
                    if (var192.indexOf("đang đứng nhìn bạn") > 0) {
                        Code.g(var192.substring(0, var192.indexOf("đang đứng nhìn bạn")).trim());
                    } else if (Char.tickDieKhiHetMP && Code.auto != null && var192.equals("Không đủ MP để sử dụng")) {
                        Auto.isHetMP = true;
                    } else if (LockGame.b && var192.equals("Vật phẩm của người khác")) {
                        LockGame.d();
                        if ((var191 = Char.getMyChar().itemFocus) != null) {
                            var191.isPickedUp = true;
                        }
                    } else if (Code.auto instanceof AutoSend) {
                        if (var192.endsWith("không đủ chỗ trống trong hành trang")) {
                            AutoSend.a = true;
                        }
                    } else if (Code.auto instanceof AutoReceiver) {
                        if (var192.endsWith("Bạn không đủ chỗ trống trong hành trang")) {
                            AutoSend.b = true;
                        }
                    } else if (Code.auto instanceof Stanima) {
                        if (Code.c.a == 2 && var192.equals("Cửa hang động đã được khép lại.")) {
                            Stanima.d = true;
                        } else if (Code.c.a == 4 && var192.equals("Chiến trường đã khép lại, xem kết quả tại Npc Rikudou.")) {
                            Stanima.f = true;
                            TileMap.h();
                        }
                    } else if (Code.g != null && !Code.g.equals(Char.getMyChar().charName) && var192.equals("Đối phương đang ở trong nhóm khác.")) {
                        Service.getInstance().t();
                    }
                    if (Code.auto instanceof AutoDV) {
                        AutoDV.checkMsgNV(var192);
                    }
                    if (Code.auto instanceof AutoJoinClanDun) {
                        if (StringUtils.contains(var192, "mời bạn vào lãnh địa gia tộc")) {
                            ((AutoJoinClanDun) Code.auto).joinClanDun();
                        }
                    }
                    if (!Code.tbNhanVP && var192.startsWith("Bạn nhận được")) {
                        return;
                    }
                    InfoMe.a(var192, 50, mFont.tahoma_7_yellow);
                    return;
                case -23:
                    var164 = ms.reader().readInt();
                    msgText = ms.reader().readUTF();
                    if (Char.getMyChar().charID == var164) {
                        var166 = Char.getMyChar();
                    } else {
                        var166 = GameScr.getCharByID(var164);
                    }

                    if (var166 == null) {
                        return;
                    }

                    ChatPopup.chat(msgText, 100, var166);
                    ChatManager.getInstance().chat(mResources.on[0], var166.charName, msgText);
                    return;
                case -22:
                    var192 = ms.reader().readUTF();
                    msgText = ms.reader().readUTF();
                    if (AutoEventTrade.onPrivateMessage(var192, msgText)) {
                        return;
                    }
                    ChatManager.getInstance().chat(var192, var192, msgText);
                    if ((!GameScr.ck || !ChatManager.getInstance().e().b.equals(var192)) && !ChatManager.d) {
                        ChatManager.getInstance().c(var192);
                    }

                    Code.instance.collectMsg(var192, msgText);
                    return;
                case -21:
                    msgText = ms.reader().readUTF();
                    var187 = ms.reader().readUTF();
                    ChatManager.getInstance().chat(mResources.op[0], msgText, var187);
                    if (!ChatManager.c) {
                        Info.a(msgText + ": " + var187, 80, mFont.tahoma_7b_yellow);
                    }

                    return;
                case -20:
                    name = ms.reader().readUTF();
                    var4 = ms.reader().readUTF();
                    ChatManager.getInstance().chat(mResources.oo[0], name, var4);
                    if (!GameScr.ck || ChatManager.getInstance().e().a != 1) {
                        ChatManager.f = true;
                    }

                    Code.instance.d(name, var4);
                    return;
                case -19:
                    name = ms.reader().readUTF();
                    msgText = ms.reader().readUTF();
                    if (AutoNpc.onClanChat(name, msgText)) {
                        return;
                    }
                    if (AutoEventTrade.onClanChat(name, msgText)) {
                        return;
                    }
                    AutoLDGT.onClanMessage(name, msgText);
                    ChatManager.getInstance().chat(mResources.oq[0], name, msgText);
                    if (!GameScr.ck || ChatManager.getInstance().e().a != 4) {
                        ChatManager.e = true;
                    }
                    if (Code.auto instanceof AutoNTGT) {
                        int idItem = -1;
                        if (msgText.startsWith("ntgt5")) {
                            idItem = 427;
                        }
                        if (msgText.startsWith("ntgt4")) {
                            idItem = 426;
                        }
                        if (msgText.startsWith("ntgt3")) {
                            idItem = 425;
                        }
                        if (msgText.startsWith("ntgt2")) {
                            idItem = 424;
                        }
                        if (msgText.startsWith("ntgt1")) {
                            idItem = 423;
                        }
                        if (msgText.startsWith("ttgt")) {
                            idItem = 604;
                        }
                        if (idItem != -1) {
                            if (Char.clan != null) {
                                Item ntgt = Char.clan.findItemById(idItem);
                                Service.getInstance().sendClanItem(name, ntgt.indexUI);
                                String textChat = "[" + StringUtils.getDateString(System.currentTimeMillis()) + "] Phát " + ntgt.template.name + " cho @" + name;
//                            ChatManager.getInstance().chat(mResources.oq[0], Char.getMyChar().charName, textChat);
                                GameScr.chatPopup(textChat);
                                Service.getInstance().m(textChat);
                                Service.getInstance().requestClanItem();
                            } else {
                                Service.getInstance().m("Có lỗi xảy ra. Vui lòng thử lại sau 60s nữa");
                            }

                        }

                    }
                    return;
                case -18:
                    GameCanvas.e = true;
                    GameScr.a();
                    TileMap.vGo.removeAllElements();
                    System.gc();
                    TileMap.mapID = (short) ms.reader().readUnsignedByte();
                    TileMap.e = ms.reader().readByte();
                    TileMap.bgID = ms.reader().readByte();
                    TileMap.typeMap = ms.reader().readByte();
                    TileMap.mapName = ms.reader().readUTF();
                    TileMap.zoneID = ms.reader().readByte();
                    TileMap.k();

                    try {
                        TileMap.i();
                    } catch (Exception var150) {
                        Service.getInstance().y(TileMap.mapID);
                        this.b = ms;
                        return;
                    }

                    d(ms);
                    if (Char.getMyChar().mobMe != null) {
                        Char.getMyChar().mobMe.curX = Char.getMyChar().cx;
                        Char.getMyChar().mobMe.curY = Char.getMyChar().cy - 40;
                    }

                    return;
                case -16:
                    Char.fl = true;
                    Char.fk = true;
                    GameScr.getInstance().dz = 0;
                    GameScr.getInstance().dy = 0;
                    Char.getMyChar().mobFocus = null;
                    Char.getMyChar().npcFocus = null;
                    Char.getMyChar().charFocus = null;
                    Char.getMyChar().itemFocus = null;
                    Char.getMyChar().focus.removeAllElements();
                    Char.getMyChar().cz = -9999;
                    Char.getMyChar().da = -9999;
                    GameScr.a();
                    GameCanvas.h();
                    if (GameScr.vParty.size() <= 1) {
                        GameScr.vParty.removeAllElements();
                    }

                    GameScr.getInstance().resetButton();
                    GameScr.getInstance().center = null;
                    return;
                case -15:
                    var163 = ms.reader().readShort();

                    for (var3 = 0; var3 < GameScr.vItemMap.size(); ++var3) {
                        if (((ItemMap) GameScr.vItemMap.elementAt(var3)).itemMapID == var163) {
                            GameScr.vItemMap.removeElementAt(var3);
                            return;
                        }
                    }

                    return;
                case -14:
                    Char.getMyChar().itemFocus = null;
                    var163 = ms.reader().readShort();

                    for (var3 = 0; var3 < GameScr.vItemMap.size(); ++var3) {
                        if ((var189 = (ItemMap) GameScr.vItemMap.elementAt(var3)).itemMapID == var163) {
                            var189.a(Char.getMyChar().cx, Char.getMyChar().cy - 10);
                            var189.isPickedUp = true;
                            if (var189.template.type == 19) {
                                var3 = ms.reader().readUnsignedShort();
                                var10000 = Char.getMyChar();
                                var10000.yen += var3;
                                if (var189.template.id == 238) {
                                    return;
                                }

                                InfoMe.a(mResources.kq + " " + var3 + " " + mResources.kf);
                                return;
                            }

                            if (var189.template.type != 25 || var189.template.id == 238) {
                                return;
                            }

                            InfoMe.a(mResources.kq + " " + var189.template.name, 15, mFont.tahoma_7_yellow);
                            return;
                        }
                    }

                    LockGame.d();
                    return;
                case -13:
                    var163 = ms.reader().readShort();

                    for (var3 = 0; var3 < GameScr.vItemMap.size(); ++var3) {
                        if ((var189 = (ItemMap) GameScr.vItemMap.elementAt(var3)).itemMapID == var163) {
                            if ((var166 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                                return;
                            }

                            var189.a(var166.cx, var166.cy - 10);
                            if (var189.x < var166.cx) {
                                var166.cDir = -1;
                            } else if (var189.x > var166.cx) {
                                var166.cDir = 1;
                            }

                            if (var189 == Char.getMyChar().itemFocus) {
                                var189.isPickedUp = true;
                                LockGame.d();
                            }

                            return;
                        }
                    }

                    return;
                case -12:
                    type = ms.reader().readByte();
                    GameScr.vItemMap.addElement(new ItemMap(ms.reader().readShort(), Char.getMyChar().arrItemBag[type].template.id, Char.getMyChar().cx, Char.getMyChar().cy, ms.reader().readShort(), ms.reader().readShort()));
                    Char.getMyChar().arrItemBag[type] = null;
                    return;
                case -11:
                    Char.getMyChar().hieuChien = ms.reader().readByte();
                    Char.getMyChar().a(ms.reader().readShort(), ms.reader().readShort());

                    try {
                        Char.getMyChar().cEXP = ms.reader().readLong();
                        GameScr.a(Char.getMyChar().cEXP);
                    } catch (Exception var147) {
                    }

                    Char.getMyChar().dc = 0;
                    return;
                case -10:
                    if (Char.getMyChar().gf != 0 || Char.getMyChar().gg != 0) {
                        Char.getMyChar().cx = Char.getMyChar().gf;
                        Char.getMyChar().cy = Char.getMyChar().gg;
                        Char.getMyChar().gf = Char.getMyChar().gg = 0;
                    }

                    Char.getMyChar().aa();
                    Char.fl = false;
                    return;
                case -8:
                    var3 = ms.reader().readInt();
                    var10000 = Char.getMyChar();
                    var10000.yen += var3;
                    GameScr.getInstance().ed = var3;
                    GameScr.a(var3 > 0 ? "+" + var3 : String.valueOf(var3), Char.getMyChar().cx, Char.getMyChar().cy - Char.getMyChar().bj - 10, 1);
                    return;
                case -7:
                    var3 = ms.reader().readInt();
                    var10000 = Char.getMyChar();
                    var10000.xu += var3;
                    var10000 = Char.getMyChar();
                    var10000.yen -= var3;
                    GameScr.a("+" + var3, Char.getMyChar().cx, Char.getMyChar().cy - Char.getMyChar().bj - 10, 1);
                    return;
                case -6:
                    if ((var166 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        GameScr.vItemMap.addElement(new ItemMap(ms.reader().readShort(), ms.reader().readShort(), var166.cx, var166.cy, ms.reader().readShort(), ms.reader().readShort()));
                        return;
                    }

                    return;
                case -5:
                    try {
                        (var184 = Mob.a(ms.reader().readUnsignedByte())).sys = ms.reader().readByte();
                        var184.levelBoss = ms.reader().readByte();
                        var184.curX = var184.cx;
                        var184.curY = var184.cy;
                        var184.h = 5;
                        var184.ad = false;
                        var184.hp = ms.reader().readInt();
                        var184.maxHp = var184.hp;
                        if (var184.getMobTemplate().e == 202) {
                            ServerEffect.a(148, var184.curX, var184.curY, 0);
                        } else {
                            ServerEffect.a(60, var184.curX, var184.curY, 1);
                        }

                        Auto.a(var184);
                    } catch (Exception var146) {
                        var146.printStackTrace();
                    }

                    return;
                case -4:
                    var184 = null;

                    try {
                        var184 = Mob.a(ms.reader().readUnsignedByte());
                    } catch (Exception var145) {
                    }

                    if (var184 != null && var184.h != 0 && var184.h != 0) {
                        try {
                            if ((var164 = ms.reader().readInt()) < 0) {
                                var164 = Res.e(var164) + 32767;
                            }

                            if (ms.reader().readBoolean()) {
                                GameScr.a("-" + var164, var184.curX, var184.curY - var184.l, 3);
                            } else {
                                GameScr.a("-" + var164, var184.curX, var184.curY - var184.l, 5);
                            }

                            ItemMap var198 = new ItemMap(ms.reader().readShort(), ms.reader().readShort(), var184.curX, var184.curY, ms.reader().readShort(), ms.reader().readShort());
                            GameScr.vItemMap.addElement(var198);
                            if (Res.e(var198.y - Char.getMyChar().cy) < 24 && Res.e(var198.x - Char.getMyChar().cx) < 24) {
                                Char.getMyChar().charFocus = null;
                            }
                        } catch (Exception var144) {
                        }

                        var184.e();
                    }

                    return;
                case -3:
                    var184 = null;

                    try {
                        var184 = Mob.a(ms.reader().readUnsignedByte());
                    } catch (Exception var143) {
                        System.out.println("----err null:NPC_ATTACK_ME");
                    }

                    if (var184 != null) {
                        var164 = ms.reader().readInt();

                        try {
                            var2 = ms.reader().readInt();
                        } catch (Exception var142) {
                            var2 = 0;
                        }

                        if (var184.ab) {
                            Char.getMyChar().a(var164, var2, false, -1);
                            var184.f();
                        } else {
                            var184.t = var164;
                            var184.u = var2;
                            var184.a(Char.getMyChar());
                        }

                        var173 = ms.reader().readShort();
                        var188 = ms.reader().readByte();
                        var197 = ms.reader().readByte();
                        var184.a(var173, var188, var197);
                        if (Char.getMyChar().cHP < Char.getMyChar().cMaxHP / 2) {
                            if (var184.isBoss) {
                                System.out.println("Bi Quai TG Danh");
                                return;
                            } else {
                                if (var184.levelBoss == 1) {
                                    System.out.println("Bi TA Danh");
                                } else if (var184.levelBoss == 2) {
                                    System.out.println("Bi TL Danh");
                                    return;
                                }

                                return;
                            }
                        }
                    }

                    return;
                case -2:
                    var184 = null;

                    try {
                        var184 = Mob.a(ms.reader().readUnsignedByte());
                    } catch (Exception var141) {
                    }

                    if (var184 != null) {
                        if ((var166 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                            return;
                        }

                        var2 = ms.reader().readInt();
                        var184.t = var166.cHP - var2;
                        var166.ad = var2;

                        try {
                            var166.cMP = ms.reader().readInt();
                        } catch (Exception var140) {
                        }

                        if (var184.ab) {
                            var166.a(var184.t, 0, false, -1);
                            var184.f();
                        } else {
                            var184.a(var166);
                        }

                        var173 = ms.reader().readShort();
                        var188 = ms.reader().readByte();
                        var197 = ms.reader().readByte();
                        var184.a(var173, var188, var197);
                    }

                    return;
                case -1:
                    var184 = null;

                    try {
                        var184 = Mob.a(ms.reader().readUnsignedByte());
                    } catch (Exception var139) {
                    }

                    if (var184 != null) {
                        var184.hp = ms.reader().readInt();
                        if ((var164 = ms.reader().readInt()) < 0) {
                            var164 = Res.e(var164) + 32767;
                        }

                        boolean var196 = ms.reader().readBoolean();

                        try {
                            var184.levelBoss = ms.reader().readByte();
                            var184.maxHp = ms.reader().readInt();
                            Auto.a(var184);
                        } catch (Exception var138) {
                        }

                        if (var196) {
                            GameScr.a("-" + var164, var184.curX, var184.curY - var184.l, 3);
                        } else {
                            GameScr.a("-" + var164, var184.curX, var184.curY - var184.l, 5);
                        }

                        return;
                    }

                    return;
                case 0:
                    if ((var166 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    var166.hieuChien = ms.reader().readByte();
                    if (var166.charID == Char.aCID) {
                        Char.er = true;
                    }

                    var166.a(ms.reader().readShort(), ms.reader().readShort());
                    if (Char.getMyChar().charFocus == var166) {
                        Char.getMyChar().charFocus = null;
                    }

                    return;
                case 1:
                    var2 = ms.reader().readInt();

                    for (var164 = 0; var164 < GameScr.vCharInMap.size(); ++var164) {
                        var170 = null;

                        try {
                            var170 = (Char) GameScr.vCharInMap.elementAt(var164);
                        } catch (Exception var122) {
                            System.out.println("Char null");
                        }

                        if (var170 == null) {
                            return;
                        }

                        if (var170.charID == var2) {
                            var170.fh = ms.reader().readShort();
                            var170.fi = ms.reader().readShort();
                            var170.a(var170.fh, var170.fi);
                            var170.lastUpdateTime = System.currentTimeMillis();
                            return;
                        }
                    }
                    break;
                case 2:
                    var2 = ms.reader().readInt();

                    for (var164 = 0; var164 < GameScr.vCharInMap.size(); ++var164) {
                        if ((var170 = (Char) GameScr.vCharInMap.elementAt(var164)) != null && var170.charID == var2) {
                            if (!var170.df && var170.isHuman && !var170.isNhanban) {
                                ServerEffect.a(60, var170.cx, var170.cy, 1);
                            } else if (!var170.df && var170.ae() && !var170.isHuman) {
                                ServerEffect.a(141, var170.cx, var170.cy, 0);
                            }

                            GameScr.vCharInMap.removeElementAt(var164);
                            Party.a(var2);
                            if (!var170.ad() && var170.charName.equals(Char.getMyChar().charName)) {
                                Char.getMyChar().d = null;
                            }

                            return;
                        }
                    }

                    return;
                case 3:
                    (var166 = new Char()).charID = ms.reader().readInt();
                    if (updateCharInfo(var166, ms)) {
                        GameScr.vCharInMap.addElement(var166);
                        Auto.b(var166);
                        if (!var166.ad() && var166.charName.equals(Char.getMyChar().charName)) {
                            Char.getMyChar().d = var166;
                            LockGame.ac();
                        }

                        if (var166.charID == -Char.getMyChar().charID) {
                            Char.getMyChar().e = var166;
                        }

                        if (Code.b(var166.charName) && !Code.c.f()) {
                            Service.getInstance().addParty(var166.charName);
                            return;
                        }
                    }

                    return;
                case 4:
                    if ((var166 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    if ((TileMap.a(var166.cx, var166.cy) & 2) == 2) {
                        var166.a(GameScr.skillPaints[ms.reader().readByte()], 0);
                    } else {
                        var166.a(GameScr.skillPaints[ms.reader().readByte()], 1);
                    }

                    if (var166.bp) {
                        var166.bp = false;
                        var166.fd = System.currentTimeMillis();
                        ServerEffect.a(60, var166, 1);
                    }

                    if (var166.bo) {
                        var166.bo = false;
                        var166.bq = true;
                    }

                    var188 = ms.reader().readByte();
                    var166.attMobs = new Mob[var188];

                    for (var2 = 0; var2 < var166.attMobs.length; ++var2) {
                        Mob var194 = Mob.a(ms.reader().readUnsignedByte());
                        var166.attMobs[var2] = var194;
                        if (var2 == 0) {
                            if (var166.cx <= var194.curX) {
                                var166.cDir = 1;
                            } else {
                                var166.cDir = -1;
                            }
                        }
                    }

                    var166.mobFocus = var166.attMobs[0];
                    var181 = new Char[10];
                    var3 = 0;

                    try {
                        for (var3 = 0; var3 < 10; ++var3) {
                            Char var195;
                            if ((charID = ms.reader().readInt()) == Char.getMyChar().charID) {
                                var195 = Char.getMyChar();
                            } else {
                                var195 = GameScr.getCharByID(charID);
                            }

                            var181[var3] = var195;
                            if (var3 == 0) {
                                if (var166.cx <= var195.cx) {
                                    var166.cDir = 1;
                                } else {
                                    var166.cDir = -1;
                                }
                            }
                        }
                    } catch (Exception var158) {
                    }

                    if (var3 <= 0) {
                        return;
                    }

                    var166.attChars = new Char[var3];

                    for (var3 = 0; var3 < var166.attChars.length; ++var3) {
                        var166.attChars[var3] = var181[var3];
                    }

                    var166.charFocus = var166.attChars[0];
                    return;
                case 5:
                    var40 = ms.reader().readLong();
                    Char.getMyChar().cExpDown = 0L;
                    var10000 = Char.getMyChar();
                    var10000.cEXP += var40;
                    charID = Char.getMyChar().cLevel;
                    GameScr.a(Char.getMyChar().cEXP);
                    if (charID != Char.getMyChar().cLevel) {
                        ServerEffect.a(58, Char.getMyChar(), 1);
                    }

                    GameScr.a("+" + var40, Char.getMyChar().cx, Char.getMyChar().cy - Char.getMyChar().bj, 2);
                    if (var40 >= 1000000L && Code.tbNhanExp) {
                        InfoMe.a(mResources.kq + " " + var40 + " " + mResources.kinhNghiem, 20, mFont.tahoma_7_yellow);
                    }

                    return;
                case 6:
                    var191 = new ItemMap(ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort());
                    byte[] var193;
                    if ((var193 = NinjaUtil.a(ms)) != null && var193.length > 0) {
                        var191.j = new MyImage();
                        var191.j.img = a(var193);
                    }

                    GameScr.vItemMap.addElement(var191);
                    return;
                case 7:
                    Char.getMyChar().arrItemBag[ms.reader().readByte()].quantity = ms.reader().readShort();
                    return;
                case 8:
                    type = ms.reader().readByte();
                    Char.getMyChar().arrItemBag[type] = new Item();
                    Char.getMyChar().arrItemBag[type].typeUI = 3;
                    Char.getMyChar().arrItemBag[type].indexUI = type;
                    Char.getMyChar().arrItemBag[type].template = ItemTemplateManager.get(ms.reader().readShort());
                    Char.getMyChar().arrItemBag[type].isLock = ms.reader().readBoolean();
                    if (Char.getMyChar().arrItemBag[type].isTypeBody() || Char.getMyChar().arrItemBag[type].isTypeNgocKham()) {
                        Char.getMyChar().arrItemBag[type].upgrade = ms.reader().readByte();
                    }

                    Char.getMyChar().arrItemBag[type].isExpires = ms.reader().readBoolean();

                    try {
                        Char.getMyChar().arrItemBag[type].quantity = ms.reader().readUnsignedShort();
                    } catch (Exception var137) {
                        Char.getMyChar().arrItemBag[type].quantity = 1;
                    }

                    if (Char.getMyChar().arrItemBag[type].template.type == 16) {
                        GameScr.r += Char.getMyChar().arrItemBag[type].quantity;
                    }

                    if (Char.getMyChar().arrItemBag[type].template.type == 17) {
                        GameScr.q += Char.getMyChar().arrItemBag[type].quantity;
                    }

                    if (Char.getMyChar().arrItemBag[type].template.id == 340) {
                        var222 = GameScr.getInstance();
                        var222.numSprinLeft += Char.getMyChar().arrItemBag[type].quantity;
                    }

                    if (GameScr.ci) {
                        if (GameScr.getInstance().dx.equals("")) {
                            (var171 = GameScr.getInstance()).dx = var171.dx + Char.getMyChar().arrItemBag[type].template.name;
                        } else {
                            (var171 = GameScr.getInstance()).dx = var171.dx + ", " + Char.getMyChar().arrItemBag[type].template.name;
                        }

                        return;
                    } else {
                        if (Char.getMyChar().arrItemBag[type].template.type != 20) {
                            // Nhan item
                            if (Code.tbNhanVP) {
                                InfoMe.a(mResources.kq + " " + Char.getMyChar().arrItemBag[type].template.name);
                            }
                            LockGame.t();
                            return;
                        }

                        return;
                    }
                case 9:
                    var167 = Char.getMyChar().arrItemBag[ms.reader().readUnsignedByte()];

                    try {
                        var163 = ms.reader().readShort();
                    } catch (Exception var136) {
                        var163 = 1;
                    }

                    var167.quantity += var163;
                    if (var167.template.type == 16) {
                        GameScr.r += var163;
                    }

                    if (var167.template.type == 17) {
                        GameScr.q += var163;
                    }

                    if (var167.template.id == 340) {
                        var222 = GameScr.getInstance();
                        var222.numSprinLeft += var163;
                    }

                    GameCanvas.setMaxTextLenght();
                    if (GameScr.ci) {
                        if (GameScr.getInstance().dx.equals("")) {
                            (var171 = GameScr.getInstance()).dx = var171.dx + var167.template.name;
                        } else {
                            (var171 = GameScr.getInstance()).dx = var171.dx + ", " + var167.template.name;
                        }

                        return;
                    } else {
                        if (var167.template.type != 20) {
                            if (Code.tbNhanVP) {
                                InfoMe.a(mResources.kq + " " + var167.template.name);
                            }

                            return;
                        }

                        return;
                    }
                case 10:
                    type = ms.reader().readByte();
                    if (Char.getMyChar().arrItemBag[type].template.type == 16) {
                        GameScr.r -= Char.getMyChar().arrItemBag[type].quantity;
                    }

                    if (Char.getMyChar().arrItemBag[type].template.type == 17) {
                        GameScr.q -= Char.getMyChar().arrItemBag[type].quantity;
                    }

                    Char.getMyChar().arrItemBag[type] = null;
                    if (GameScr.z()) {
                        GameScr.getInstance().left = GameScr.getInstance().center = null;
                    } else {
                        GameScr.getInstance().resetButton();
                    }

                    return;
                case 11:
                    type = ms.reader().readByte();
                    if (Char.getMyChar().arrItemBag[type].template.type == 24) {
                        InfoDlg.d();
                    }

                    Char.getMyChar().a(type);
                    Char.getMyChar().a(ms);
                    Char.getMyChar().eff5BuffHp = ms.reader().readShort();
                    Char.getMyChar().eff5BuffMp = ms.reader().readShort();
                    GameScr.getInstance().showButtonIndexMenu();
                    LockGame.r();
                    return;
                case 13:
                    Char.getMyChar().xu = ms.reader().readInt();
                    Char.getMyChar().yen = ms.reader().readInt();
                    Char.getMyChar().luong = ms.reader().readInt();
                    LockGame.h();
                    GameCanvas.setMaxTextLenght();
                    return;
                case 14:
                    var167 = Char.getMyChar().arrItemBag[ms.reader().readByte()];
                    Char.getMyChar().yen = ms.reader().readInt();

                    try {
                        var163 = ms.reader().readShort();
                    } catch (Exception var135) {
                        var163 = 1;
                    }

                    var167.quantity -= var163;
                    if (var167.template.type == 16) {
                        GameScr.r -= var163;
                    }

                    if (var167.template.type == 17) {
                        GameScr.q -= var163;
                    }

                    if (var167.quantity <= 0) {
                        Char.getMyChar().arrItemBag[var167.indexUI] = null;
                    }

                    if (GameScr.z()) {
                        GameScr.getInstance().left = GameScr.getInstance().center = null;
                        GameScr.getInstance().ab();
                    }

                    GameCanvas.setMaxTextLenght();
                    LockGame.t();
                    return;
                case 15:
                    Char.getMyChar().c(ms);
                    LockGame.r();
                    return;
                case 16:
                    Char.getMyChar().e(ms);
                    return;
                case 17:
                    Char.getMyChar().d(ms);
                    return;
                case 18:
                    type = ms.reader().readByte();
                    var163 = 1;

                    try {
                        var163 = ms.reader().readShort();
                    } catch (Exception var134) {
                    }

                    if (Char.getMyChar().arrItemBag[type].template.type == 24) {
                        InfoDlg.d();
                    }

                    if (Char.getMyChar().arrItemBag[type].template.type == 16) {
                        --GameScr.r;
                    }

                    if (Char.getMyChar().arrItemBag[type].template.type == 17) {
                        --GameScr.q;
                    }

                    if (Char.getMyChar().arrItemBag[type].quantity > var163) {
                        Item var221 = Char.getMyChar().arrItemBag[type];
                        var221.quantity -= var163;
                    } else {
                        Char.getMyChar().arrItemBag[type] = null;
                    }

                    if (GameScr.isPaintInfoMe) {
                        GameScr.getInstance().showButtonIndexMenu();
                    }

                    return;
                case 19:
                    Char.getMyChar();
                    Char.a(ms, true);
                    return;
                case 20:
                    Char.getMyChar();
                    Char.a(ms, false);
                    return;
                case 21:
                    var188 = ms.reader().readByte();
                    Char.getMyChar().luong = ms.reader().readInt();
                    Char.getMyChar().xu = ms.reader().readInt();
                    Char.getMyChar().yen = ms.reader().readInt();
                    if (GameScr.itemUpGrade != null) {
                        GameScr.itemUpGrade.upgrade = ms.reader().readByte();
                        GameScr.itemUpGrade.isLock = true;
                        GameScr.itemUpGrade.clearExpire();
                        if (var188 == 1) {
                            GameScr.ez = GameScr.efs[53];
                            GameScr.ey = 0;
                        }
                    }

                    if (GameScr.arrItemUpGrade != null) {
                        for (var7 = 0; var7 < GameScr.arrItemUpGrade.length; ++var7) {
                            GameScr.arrItemUpGrade[var7] = null;
                        }
                    }

                    if (var188 == 5 || var188 == 6) {
                        if (GameScr.itemSplit != null && var188 == 5) {
                            GameScr.itemSplit = null;
                        }

                        if (GameScr.arrItemSplit != null) {
                            for (var7 = 0; var7 < GameScr.arrItemSplit.length; ++var7) {
                                GameScr.arrItemSplit[var7] = null;
                            }
                        }
                    }

                    GameScr.getInstance().left = GameScr.getInstance().center = null;
                    GameScr.getInstance().aa();
                    GameCanvas.setMaxTextLenght();
                    if (var188 == 5) {
                        InfoMe.a(mResources.ky[0], 20, mFont.tahoma_7_white);
                        return;
                    } else if (var188 == 6) {
                        InfoMe.a(mResources.ky[1], 20, mFont.tahoma_7_red);
                        return;
                    } else {
                        if (var188 == 1) {
                            InfoMe.a(mResources.kx[0] + GameScr.itemUpGrade.upgrade, 20, mFont.tahoma_7_white);
                        } else {
                            InfoMe.a(mResources.kx[1] + GameScr.itemUpGrade.upgrade, 20, mFont.tahoma_7_red);
                        }

                        return;
                    }
                case 22:
                    var188 = ms.reader().readByte();
                    name = mResources.bm;

                    for (var3 = 0; var3 < GameScr.arrItemSplit.length; ++var3) {
                        GameScr.arrItemSplit[var3] = null;
                    }

                    for (var3 = 0; var3 < var188; ++var3) {
                        Item var179;
                        (var179 = new Item()).typeUI = 3;
                        var179.indexUI = ms.reader().readByte();
                        var179.template = ItemTemplateManager.get(ms.reader().readShort());
                        var179.expires = -1L;
                        var179.quantity = 1;
                        var179.isLock = GameScr.itemSplit.isLock;
                        Char.getMyChar().arrItemBag[var179.indexUI] = var179;
                        name = name + var179.template.name;
                        if (var3 < var188 - 1) {
                            name = name + ", ";
                        }
                    }

                    GameScr.itemSplit.upgrade = 0;
                    GameScr.itemSplit.clearExpire();
                    GameScr.getInstance().left = GameScr.getInstance().center = null;
                    GameScr.getInstance().ab();
                    GameCanvas.setMaxTextLenght();
                    InfoMe.a(name);
                    GameScr.ez = GameScr.efs[66];
                    GameScr.ey = 0;
                    LockGame.r();
                    return;
                case 23:
                    if (Code.c(msgText = ms.reader().readUTF())) {
                        Service.getInstance().i(msgText);
                    } else {
                        GameCanvas.a(msgText + " " + mResources.me, 8889, msgText, 8882, (Object) null);
                    }

                    return;
                case 25:
                    byte var176 = ms.reader().readByte();

                    for (var2 = 0; var2 < var176; ++var2) {
                        var3 = ms.reader().readInt();
                        var185 = ms.reader().readShort();
                        var168 = ms.reader().readShort();
                        int var178 = ms.reader().readInt();
                        Char var182;
                        if ((var182 = GameScr.getCharByID(var3)) != null) {
                            var182.cx = var185;
                            var182.cy = var168;
                            var182.cHP = var178;
                            var182.lastUpdateTime = System.currentTimeMillis();
                        }
                    }

                    return;
                case 26:
                    Char.getMyChar().dc = ms.reader().readUnsignedShort();
                    Char.getMyChar().dd = ms.reader().readUnsignedShort();
                    return;
                case 27:
                    var169 = Mob.a(ms.reader().readUnsignedByte());
                    if ((charID = ms.reader().readInt()) == Char.getMyChar().charID) {
                        var166 = Char.getMyChar();
                    } else {
                        var166 = GameScr.getCharByID(charID);
                    }

                    var166.moveFast = new short[3];
                    var166.moveFast[0] = 0;
                    var166.moveFast[1] = (short) var169.curX;
                    var166.moveFast[2] = (short) var169.curY;
                    var166.fo = false;
                    return;
                case 30:
                    type = ms.reader().readByte();
                    try {
                        GameScr.svTitle = ms.reader().readUTF();
                        GameScr.svAction = ms.reader().readUTF();
                    } catch (Exception var158) {
                    }
                    GameScr.getInstance().openUI(type);
                    if ((AutoBiKip.isRunning() || AutoDoiLongDen.isBusy()) && type == 4) {
                        GameScr.hideCommandBoxUi();
                    }
                    LockGame.r();
                    return;
                case 31:
                    Char.getMyChar().bs = ms.reader().readInt();
                    Char.getMyChar().arrItemBox = new Item[ms.reader().readUnsignedByte()];

                    for (var3 = 0; var3 < Char.getMyChar().arrItemBox.length; ++var3) {
                        if ((var185 = ms.reader().readShort()) != -1) {
                            Char.getMyChar().arrItemBox[var3] = new Item();
                            Char.getMyChar().arrItemBox[var3].typeUI = 4;
                            Char.getMyChar().arrItemBox[var3].indexUI = var3;
                            Char.getMyChar().arrItemBox[var3].template = ItemTemplateManager.get(var185);
                            Char.getMyChar().arrItemBox[var3].isLock = ms.reader().readBoolean();
                            if (Char.getMyChar().arrItemBox[var3].isTypeBody() || Char.getMyChar().arrItemBox[var3].isTypeNgocKham()) {
                                Char.getMyChar().arrItemBox[var3].upgrade = ms.reader().readByte();
                            }

                            Char.getMyChar().arrItemBox[var3].isExpires = ms.reader().readBoolean();
                            Char.getMyChar().arrItemBox[var3].quantity = ms.reader().readShort();
                        }
                    }

                    LockGame.t();
                    return;
                case 33:
                    if ((var185 = ms.reader().readByte()) == 14) {
                        GameScr.arrItemStore = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemStore.length; ++charID) {
                            GameScr.arrItemStore[charID] = new Item();
                            GameScr.arrItemStore[charID].typeUI = 14;
                            GameScr.arrItemStore[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemStore[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 15) {
                        GameScr.arrItemBook = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemBook.length; ++charID) {
                            GameScr.arrItemBook[charID] = new Item();
                            GameScr.arrItemBook[charID].typeUI = 15;
                            GameScr.arrItemBook[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemBook[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 32) {
                        GameScr.arrItemFashion = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemFashion.length; ++charID) {
                            GameScr.arrItemFashion[charID] = new Item();
                            GameScr.arrItemFashion[charID].typeUI = 32;
                            GameScr.arrItemFashion[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemFashion[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 34) {
                        GameScr.arrItemClanShop = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemClanShop.length; ++charID) {
                            GameScr.arrItemClanShop[charID] = new Item();
                            GameScr.arrItemClanShop[charID].typeUI = 34;
                            GameScr.arrItemClanShop[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemClanShop[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 35) {
                        GameScr.arrItemElites = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemElites.length; ++charID) {
                            GameScr.arrItemElites[charID] = new Item();
                            GameScr.arrItemElites[charID].typeUI = 35;
                            GameScr.arrItemElites[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemElites[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 20) {
                        GameScr.arrItemNonNam = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemNonNam.length; ++charID) {
                            GameScr.arrItemNonNam[charID] = new Item();
                            GameScr.arrItemNonNam[charID].typeUI = var185;
                            GameScr.arrItemNonNam[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemNonNam[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 21) {
                        GameScr.arrItemNonNu = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemNonNu.length; ++charID) {
                            GameScr.arrItemNonNu[charID] = new Item();
                            GameScr.arrItemNonNu[charID].typeUI = var185;
                            GameScr.arrItemNonNu[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemNonNu[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 22) {
                        GameScr.arrItemAoNam = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemAoNam.length; ++charID) {
                            GameScr.arrItemAoNam[charID] = new Item();
                            GameScr.arrItemAoNam[charID].typeUI = var185;
                            GameScr.arrItemAoNam[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemAoNam[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 23) {
                        GameScr.arrItemAoNu = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemAoNu.length; ++charID) {
                            GameScr.arrItemAoNu[charID] = new Item();
                            GameScr.arrItemAoNu[charID].typeUI = var185;
                            GameScr.arrItemAoNu[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemAoNu[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 24) {
                        GameScr.arrItemGangTayNam = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemGangTayNam.length; ++charID) {
                            GameScr.arrItemGangTayNam[charID] = new Item();
                            GameScr.arrItemGangTayNam[charID].typeUI = var185;
                            GameScr.arrItemGangTayNam[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemGangTayNam[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 25) {
                        GameScr.arrItemGangTayNu = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemGangTayNu.length; ++charID) {
                            GameScr.arrItemGangTayNu[charID] = new Item();
                            GameScr.arrItemGangTayNu[charID].typeUI = var185;
                            GameScr.arrItemGangTayNu[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemGangTayNu[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 26) {
                        GameScr.arrItemQuanNam = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemQuanNam.length; ++charID) {
                            GameScr.arrItemQuanNam[charID] = new Item();
                            GameScr.arrItemQuanNam[charID].typeUI = var185;
                            GameScr.arrItemQuanNam[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemQuanNam[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 27) {
                        GameScr.arrItemQuanNu = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemQuanNu.length; ++charID) {
                            GameScr.arrItemQuanNu[charID] = new Item();
                            GameScr.arrItemQuanNu[charID].typeUI = var185;
                            GameScr.arrItemQuanNu[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemQuanNu[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 28) {
                        GameScr.arrItemGiayNam = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemGiayNam.length; ++charID) {
                            GameScr.arrItemGiayNam[charID] = new Item();
                            GameScr.arrItemGiayNam[charID].typeUI = var185;
                            GameScr.arrItemGiayNam[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemGiayNam[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 29) {
                        GameScr.arrItemGiayNu = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemGiayNu.length; ++charID) {
                            GameScr.arrItemGiayNu[charID] = new Item();
                            GameScr.arrItemGiayNu[charID].typeUI = var185;
                            GameScr.arrItemGiayNu[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemGiayNu[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 16) {
                        GameScr.arrItemLien = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemLien.length; ++charID) {
                            GameScr.arrItemLien[charID] = new Item();
                            GameScr.arrItemLien[charID].typeUI = var185;
                            GameScr.arrItemLien[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemLien[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 17) {
                        GameScr.arrItemNhan = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemNhan.length; ++charID) {
                            GameScr.arrItemNhan[charID] = new Item();
                            GameScr.arrItemNhan[charID].typeUI = var185;
                            GameScr.arrItemNhan[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemNhan[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 == 18) {
                        GameScr.arrItemNgocBoi = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemNgocBoi.length; ++charID) {
                            GameScr.arrItemNgocBoi[charID] = new Item();
                            GameScr.arrItemNgocBoi[charID].typeUI = var185;
                            GameScr.arrItemNgocBoi[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemNgocBoi[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    if (var185 != 19) {
                        if (var185 == 2) {
                            GameScr.arrItemWeapon = new Item[ms.reader().readByte()];

                            for (charID = 0; charID < GameScr.arrItemWeapon.length; ++charID) {
                                GameScr.arrItemWeapon[charID] = new Item();
                                GameScr.arrItemWeapon[charID].typeUI = var185;
                                GameScr.arrItemWeapon[charID].indexUI = ms.reader().readUnsignedByte();
                                GameScr.arrItemWeapon[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                            }

                            return;
                        }

                        if (var185 == 6) {
                            GameScr.arrItemStack = new Item[ms.reader().readByte()];

                            for (charID = 0; charID < GameScr.arrItemStack.length; ++charID) {
                                GameScr.arrItemStack[charID] = new Item();
                                GameScr.arrItemStack[charID].typeUI = var185;
                                GameScr.arrItemStack[charID].indexUI = ms.reader().readUnsignedByte();
                                GameScr.arrItemStack[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                            }

                            return;
                        }

                        if (var185 == 7) {
                            GameScr.arrItemStackLock = new Item[ms.reader().readByte()];

                            for (charID = 0; charID < GameScr.arrItemStackLock.length; ++charID) {
                                GameScr.arrItemStackLock[charID] = new Item();
                                GameScr.arrItemStackLock[charID].typeUI = var185;
                                GameScr.arrItemStackLock[charID].isLock = true;
                                GameScr.arrItemStackLock[charID].indexUI = ms.reader().readUnsignedByte();
                                GameScr.arrItemStackLock[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                            }

                            return;
                        }

                        if (var185 != 8) {
                            if (var185 != 9) {
                                return;
                            }

                            GameScr.arrItemGroceryLock = new Item[ms.reader().readByte()];

                            for (charID = 0; charID < GameScr.arrItemGroceryLock.length; ++charID) {
                                GameScr.arrItemGroceryLock[charID] = new Item();
                                GameScr.arrItemGroceryLock[charID].typeUI = var185;
                                GameScr.arrItemGroceryLock[charID].isLock = true;
                                GameScr.arrItemGroceryLock[charID].indexUI = ms.reader().readUnsignedByte();
                                GameScr.arrItemGroceryLock[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                            }

                            return;
                        }

                        GameScr.arrItemGrocery = new Item[ms.reader().readByte()];

                        for (charID = 0; charID < GameScr.arrItemGrocery.length; ++charID) {
                            GameScr.arrItemGrocery[charID] = new Item();
                            GameScr.arrItemGrocery[charID].typeUI = var185;
                            GameScr.arrItemGrocery[charID].indexUI = ms.reader().readUnsignedByte();
                            GameScr.arrItemGrocery[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        return;
                    }

                    GameScr.arrItemPhu = new Item[ms.reader().readByte()];

                    for (charID = 0; charID < GameScr.arrItemPhu.length; ++charID) {
                        GameScr.arrItemPhu[charID] = new Item();
                        GameScr.arrItemPhu[charID].typeUI = var185;
                        GameScr.arrItemPhu[charID].indexUI = ms.reader().readUnsignedByte();
                        GameScr.arrItemPhu[charID].template = ItemTemplateManager.get(ms.reader().readShort());
                    }

                    return;
                case 34:
                    var165 = new MyVector();
                    if (!(msgText = ms.reader().readUTF()).equals("")) {
                        GameScr.getInstance().a((String) null, msgText, true);
                    }

                    var188 = ms.reader().readByte();

                    for (var7 = 0; var7 < var188; ++var7) {
                        var187 = ms.reader().readUTF();
                        Short var177 = new Short(ms.reader().readShort());
                        var165.addElement(new Command(var187, GameCanvas.instance, 88819, var177));
                    }

                    GameCanvas.menu.showMenu(var165);
                    return;
                case 36:
                    GameScr.getInstance().showZoneDialog(ms);
                    LockGame.f();
                    return;
                case 37:
                    GameScr.getInstance().dw = ms.reader().readUTF();
                    GameScr.getInstance().t();
                    LockGame.aa();
                    LockGame.ag();
                    return;
                case 38:
                    var163 = ms.reader().readShort();

                    for (var3 = 0; var3 < GameScr.ah.size(); ++var3) {
                        if ((var172 = (Npc) GameScr.ah.elementAt(var3)).template.npcTemplateId == var163 && var172.equals(Char.getMyChar().npcFocus)) {
                            String var175;
                            ChatPopup.a(var175 = ms.reader().readUTF(), var172);
                            if (var172.template.npcTemplateId == 0) {
                                if (var175.equals("Số lần vào trong hang hôm nay của con đã hết.")) {
                                    Stanima.e = true;
                                    if (Code.auto instanceof Stanima) {
                                        Stanima.d = true;
                                    }

                                    if (Code.auto instanceof AutoHD9x) {
                                        Code var220 = Code.instance;
                                        Code.backToInstance();
                                    }

                                    AutoHD9xManager.onNoMoreEntries();
                                    TileMap.h();
                                    return;
                                }

                                return;
                            } else {
                                if (var172.template.npcTemplateId == 5) {
                                    if (var175.equals("Tốt lắm, ngươi đã chọn nơi này làm nơi trở về khi bị trọng thương")) {
                                        LockGame.z();
                                        return;
                                    }
                                } else if (var172.template.npcTemplateId == 25) {
                                    if (Code.auto == Code.autoNVHN) {
                                        AutoNVHN.a(var175);
                                    } else if (var175.equals("Hôm nay con đã làm hết nhiệm vụ ta giao. Hãy quay lại vào ngày hôm sau.")) {
                                        Stanima.c = true;
                                        return;
                                    }

                                    return;
                                }

                                return;
                            }
                        }
                    }

                    return;
                case 39:
                    var163 = ms.reader().readShort();

                    for (var3 = 0; var3 < GameScr.ah.size(); ++var3) {
                        if ((var172 = (Npc) GameScr.ah.elementAt(var3)).template.npcTemplateId == var163 && var172.equals(Char.getMyChar().npcFocus)) {
                            ChatPopup.chat(ms.reader().readUTF(), 1000, var172);
                            String[] var174 = new String[ms.reader().readByte()];

                            for (var2 = 0; var2 < var174.length; ++var2) {
                                var174[var2] = ms.reader().readUTF();
                            }

                            GameScr.getInstance();
                            GameScr.a(var174, var172);
                            return;
                        }
                    }

                    return;
                case 40:
                    InfoDlg.d();
                    GameCanvas.l();
                    GameCanvas.k();
                    var165 = new MyVector();

                    try {
                        while (true) {
                            var165.addElement(new Command(ms.reader().readUTF(), GameCanvas.instance, 88822, (Object) null));
                        }
                    } catch (Exception var151) {
                        if (Char.getMyChar().npcFocus == null) {
                            return;
                        }

                        if (Char.getMyChar().npcFocus.charID == 25) {
                            GameScr.fj = var165.size();
                        }

                        for (var7 = 0; var7 < Char.getMyChar().npcFocus.template.menu.length; ++var7) {
                            String[] var180 = Char.getMyChar().npcFocus.template.menu[var7];
                            var165.addElement(new Command(var180[0], GameCanvas.instance, 88820, var180));
                        }

                        GameCanvas.menu.showMenu(var165);
                        return;
                    }
                case 42:
                    h(ms);
                    return;
                case 43:
                    int var8;
                    if ((var170 = GameScr.getCharByID(var8 = (new Integer(ms.reader().readInt())).intValue())) != null) {
                        if (AutoEventTrade.onTradeInvite(var170.charName, var8)) {
                            return;
                        }
                        if (Code.auto instanceof AutoReceiver) {
                            Service.getInstance().l(var8);
                            LockGame.ag();
                            return;
                        } else {
                            if (Code.auto instanceof AutoSell) {
                                Code.f.a(var170.charName, var8);
                                LockGame.ag();
                            } else {
                                GameCanvas.a(var170.charName + " " + mResources.ly, 88810, new Integer(var8), 88811, (Object) null);
                            }

                            return;
                        }
                    }

                    return;
                case 45:
                    GameScr.getInstance().da = 1;
                    GameScr.getInstance().dc = ms.reader().readInt();
                    GameScr.arrItemTradeOrder = new Item[12];
                    var168 = ms.reader().readByte();

                    for (var2 = 0; var2 < var168; ++var2) {
                        GameScr.arrItemTradeOrder[var2] = new Item();
                        GameScr.arrItemTradeOrder[var2].typeUI = 3;
                        GameScr.arrItemTradeOrder[var2].indexUI = var2;
                        GameScr.arrItemTradeOrder[var2].template = ItemTemplateManager.get(ms.reader().readShort());
                        GameScr.arrItemTradeOrder[var2].isLock = false;
                        if (GameScr.arrItemTradeOrder[var2].isTypeBody() || GameScr.arrItemTradeOrder[var2].isTypeNgocKham()) {
                            GameScr.arrItemTradeOrder[var2].upgrade = ms.reader().readByte();
                        }

                        GameScr.arrItemTradeOrder[var2].isExpires = ms.reader().readBoolean();
                        GameScr.arrItemTradeOrder[var2].quantity = ms.reader().readShort();
                    }

                    if (GameScr.getInstance().cz == 1 && GameScr.getInstance().da == 1) {
                        GameScr.getInstance().dd = (int) (System.currentTimeMillis() / 1000L + 5L);
                    }

                    return;
                case 46:
                    GameScr.getInstance().da = 2;
                    if (GameScr.getInstance().cz >= 2 && GameScr.getInstance().da >= 2) {
                        InfoDlg.b();
                        LockGame.aa();
                        LockGame.ag();
                    }

                    return;
                case 47:
                    GameCanvas.v = 150;
                    ms.reader().readShort();
                    var168 = ms.reader().readByte();
                    var192 = ms.reader().readUTF();
                    String var9 = ms.reader().readUTF();
                    String[] var64 = new String[ms.reader().readByte()];
                    short[] var65 = new short[var64.length];
                    short var66 = -1;

                    int var67;
                    for (var67 = 0; var67 < var64.length; ++var67) {
                        String var201 = ms.reader().readUTF();
                        var65[var67] = -1;
                        if (!var201.equals("")) {
                            var64[var67] = var201;
                        }
                    }

                    try {
                        var66 = ms.reader().readShort();

                        for (var67 = 0; var67 < var64.length; ++var67) {
                            var65[var67] = ms.reader().readShort();
                        }
                    } catch (Exception var157) {
                    }

                    Char.getMyChar().taskMaint = new Task((byte) var168, var192, var9, var64, var65, var66);
                    Char.getMyChar().c(21);
                    if (Char.getMyChar().npcFocus != null) {
                        Npc.a();
                    }

                    LockGame.p();
                    return;
                case 48:
                    if (Char.getMyChar().taskMaint != null) {
                        GameCanvas.v = 100;
                        ++Char.getMyChar().taskMaint.a;
                        Char.getMyChar().taskMaint.f = 0;
                        if (Char.getMyChar().npcFocus != null && Char.getMyChar().npcFocus.chatPopup != null && Char.getMyChar().taskMaint.a >= 2) {
                            Char.getMyChar().npcFocus.chatPopup = null;
                        }

                        if (Char.getMyChar().taskMaint.a >= Char.getMyChar().taskMaint.e.length - 1) {
                            Char.getMyChar().c(61);
                        } else {
                            Char.getMyChar().c(21);
                        }

                        Npc.a();
                    }

                    LockGame.p();
                    return;
                case 49:
                    ++Char.getMyChar().ctaskId;
                    Char.getMyChar();
                    Char.ab();
                    return;
                case 50:
                    GameCanvas.v = 50;
                    Char.getMyChar().taskMaint.f = ms.reader().readShort();
                    if (Char.getMyChar().npcFocus != null) {
                        Npc.a();
                    }

                    return;
                case 51:
                    var184 = null;

                    try {
                        var184 = Mob.a(ms.reader().readUnsignedByte());
                    } catch (Exception var133) {
                    }

                    if (var184 != null) {
                        var184.hp = ms.reader().readInt();
                        GameScr.a("", var184.curX, var184.curY - var184.l, 4);
                    }

                    return;
                case 52:
                    Char.fk = false;
                    Char.fl = false;
                    Char.getMyChar().cx = ms.reader().readShort();
                    Char.getMyChar().cy = ms.reader().readShort();
                    Char.getMyChar().ff = Char.getMyChar().cx;
                    Char.getMyChar().fg = Char.getMyChar().cy;
                    return;
                case 53:
                    GameScr.getInstance().resetButton();
                    if (!(msgText = ms.reader().readUTF()).equals("typemoi")) {
                        name = ms.reader().readUTF();
                        if (Code.auto instanceof AutoDV) {
                            AutoDV.checkNV(name);
                        } else {
                            GameScr.getInstance().a(msgText, name, false);
                        }
                    } else {
                        name = ms.reader().readUTF();
                        var163 = ms.reader().readShort();
                        var187 = ms.reader().readUTF();
                        short var200 = ms.reader().readShort();
                        var192 = ms.reader().readUTF();
                        short var203 = ms.reader().readShort();
                        String var207 = ms.reader().readUTF();
                        byte var208 = ms.reader().readByte();
                        var209 = ms.reader().readUTF();
                        GameScr.getInstance().a(name, var163, var187, var200, var192, var203, var207, var209, var208);
                    }

                    return;
                case 54:
                    GameCanvas.b().a(ms.reader().readUTF(), ms.reader().readUTF(), ms.reader().readUTF(), ms.reader().readUTF());
                    return;
                case 55:
                    GameCanvas.b().a(ms.reader().readUTF(), ms.reader().readUTF(), ms.reader().readShort(), ms.reader().readUTF(), ms.reader().readUTF());
                    return;
                case 57:
                    GameCanvas.setMaxTextLenght();
                    GameScr.getInstance().resetButton();
                    AutoSell.a = true;
                    LockGame.aa();
                    return;
                case 58:
                    GameScr.arrItemTradeMe = null;
                    GameScr.arrItemTradeOrder = null;
                    if (GameScr.getInstance().dc > 0) {
                        (var171 = GameScr.getInstance()).dx = var171.dx + ", " + GameScr.getInstance().dc + " " + mResources.ke;
                        GameScr.a("+" + GameScr.getInstance().dc, Char.getMyChar().cx, Char.getMyChar().cy - Char.getMyChar().bj - 10, 6);
                    }

                    GameScr.getInstance().db = GameScr.getInstance().dc = 0;
                    GameScr.getInstance().resetButton();
                    Char.getMyChar().xu = ms.reader().readInt();
                    InfoDlg.d();
                    if (!GameScr.getInstance().dx.equals("")) {
                        InfoMe.a(mResources.kq + " " + GameScr.getInstance().dx);
                    }

                    LockGame.aa();
                    LockGame.ag();
                    return;
                case 59:
                    name = ms.reader().readUTF();
                    Friend var68 = new Friend(name, (byte) 4);
                    GameScr.ac.addElement(var68);
                    InfoMe.a(name + " " + mResources.in, 20, mFont.tahoma_7_white);
                    if (!GameScr.cf) {
                        return;
                    }

                    boolean var202 = false;

                    for (var2 = 0; var2 < GameScr.aa.size(); ++var2) {
                        if (((Friend) GameScr.aa.elementAt(var2)).a.equals(name)) {
                            var202 = true;
                            break;
                        }
                    }

                    if (!var202) {
                        GameScr.aa.addElement(var68);
                        GameScr.getInstance();
                        GameScr.c(0);
                        GameScr.indexRow = 0;
                        GameScr.scrMain.a();
                    }

                    return;
                case 60:
                    if ((var166 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        Mob.z = var166;
                    }

                    if (var166 != null) {
                        if ((TileMap.a(var166.cx, var166.cy) & 2) == 2) {
                            var166.a(GameScr.skillPaints[ms.reader().readByte()], 0);
                        } else {
                            var166.a(GameScr.skillPaints[ms.reader().readByte()], 1);
                        }

                        if (var166.bp && var166.fr >= 500) {
                            var166.bp = false;
                            var166.fd = System.currentTimeMillis();
                            ServerEffect.a(60, var166, 1);
                        }

                        if (var166.bo) {
                            var166.bo = false;
                            var166.bq = true;
                            if (var166.fr > 500) {
                                ServerEffect.a(60, var166, 1);
                            }
                        }

                        Mob[] var69 = new Mob[10];
                        var3 = 0;

                        try {
                            for (var3 = 0; var3 < 10; ++var3) {
                                var70 = Mob.a(ms.reader().readUnsignedByte());
                                var69[var3] = var70;
                                if (var3 == 0) {
                                    if (var166.cx <= var70.curX) {
                                        var166.cDir = 1;
                                    } else {
                                        var166.cDir = -1;
                                    }
                                }
                            }
                        } catch (Exception var156) {
                        }

                        if (var3 <= 0) {
                            return;
                        }

                        var166.attMobs = new Mob[var3];

                        for (var3 = 0; var3 < var166.attMobs.length; ++var3) {
                            var166.attMobs[var3] = var69[var3];
                        }

                        var166.mobFocus = var166.attMobs[0];
                        return;
                    }

                    return;
                case 61:
                    if ((var166 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    if ((TileMap.a(var166.cx, var166.cy) & 2) == 2) {
                        var166.a(GameScr.skillPaints[ms.reader().readByte()], 0);
                    } else {
                        var166.a(GameScr.skillPaints[ms.reader().readByte()], 1);
                    }

                    if (var166.bp) {
                        var166.bp = false;
                        var166.fd = System.currentTimeMillis();
                        if (var166.fr >= 500) {
                            ServerEffect.a(60, var166, 1);
                        }
                    }

                    if (var166.bo) {
                        var166.bo = false;
                        var166.bq = true;
                        ServerEffect.a(60, var166, 1);
                    }

                    var181 = new Char[10];
                    var3 = 0;

                    try {
                        for (var3 = 0; var3 < 10; ++var3) {
                            if ((charID = ms.reader().readInt()) == Char.getMyChar().charID) {
                                var204 = Char.getMyChar();
                            } else {
                                var204 = GameScr.getCharByID(charID);
                            }

                            var181[var3] = var204;
                            if (var3 == 0) {
                                if (var166.cx <= var204.cx) {
                                    var166.cDir = 1;
                                } else {
                                    var166.cDir = -1;
                                }
                            }
                        }
                    } catch (Exception var155) {
                    }

                    if (var3 <= 0) {
                        return;
                    }

                    var166.attChars = new Char[var3];

                    for (var3 = 0; var3 < var166.attChars.length; ++var3) {
                        var166.attChars[var3] = var181[var3];
                    }

                    var166.charFocus = var166.attChars[0];
                    return;
                case 62:
                    if ((charID = ms.reader().readInt()) == Char.getMyChar().charID) {
                        (var166 = Char.getMyChar()).cHP = ms.reader().readInt();
                        charID = ms.reader().readInt();
                        var7 = 0;

                        try {
                            var166.cMP = ms.reader().readInt();
                            var7 = ms.reader().readInt();
                        } catch (Exception var132) {
                        }

                        if ((charID += var7) == 0) {
                            GameScr.a("", var166.cx, var166.cy - var166.bj, 7);
                        } else if (charID < 0) {
                            GameScr.a("-" + -charID, var166.cx, var166.cy - var166.bj, 8);
                        } else {
                            GameScr.a("-" + charID, var166.cx, var166.cy - var166.bj, 0);
                        }

                        if (Char.getMyChar().cHP < Char.getMyChar().cMaxHP / 2) {
                            System.out.println("Bi PK: " + charID);
                            return;
                        }

                        return;
                    } else {
                        if ((var166 = GameScr.getCharByID(charID)) == null) {
                            return;
                        }

                        var166.cHP = ms.reader().readInt();
                        charID = ms.reader().readInt();
                        var7 = 0;

                        try {
                            var166.cMP = ms.reader().readInt();
                            var7 = ms.reader().readInt();
                        } catch (Exception var131) {
                        }

                        if ((charID += var7) == 0) {
                            GameScr.a("", var166.cx, var166.cy - var166.bj, 4);
                            return;
                        } else {
                            if (charID < 0) {
                                GameScr.a("-" + -charID, var166.cx, var166.cy - var166.bj, 3);
                            } else {
                                GameScr.a("-" + charID, var166.cx, var166.cy - var166.bj, 5);
                            }

                            return;
                        }
                    }
                case 63:
                    var165 = new MyVector();

                    while (true) {
                        try {
                            var165.addElement(new Command(ms.reader().readUTF(), GameCanvas.instance, 88817, (Object) null));
                        } catch (Exception var149) {
                            GameCanvas.menu.showMenu(var165);
                            return;
                        }
                    }
                case 64:
                    if ((charID = ms.reader().readInt()) == Char.getMyChar().charID) {
                        var170 = Char.getMyChar();
                    } else {
                        var170 = GameScr.getCharByID(charID);
                    }

                    var170.moveFast = new short[3];
                    var170.moveFast[0] = 0;
                    var173 = ms.reader().readShort();
                    var163 = ms.reader().readShort();
                    var170.moveFast[1] = var173;
                    var170.moveFast[2] = var163;
                    var170.fo = false;

                    try {
                        if ((charID = ms.reader().readInt()) == Char.getMyChar().charID) {
                            var170 = Char.getMyChar();
                        } else {
                            var170 = GameScr.getCharByID(charID);
                        }

                        var170.cx = var173;
                        var170.cy = var163;
                    } catch (Exception var130) {
                        var130.printStackTrace();
                    }

                    return;
                case 65:
                    if ((var170 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        GameCanvas.a(var170.charName + " " + mResources.mb, 88812, var170, 8882, (Object) null);
                    }

                    return;
                case 66:
                    var3 = ms.reader().readInt();
                    charID = ms.reader().readInt();
                    if (var3 != Char.getMyChar().charID && charID != Char.getMyChar().charID) {
                        GameScr.getCharByID(var3).cz = charID;
                        GameScr.getCharByID(charID).cz = var3;
                        return;
                    }

                    if (var3 == Char.getMyChar().charID) {
                        Char.getMyChar().cz = charID;
                        Char.getMyChar().npcFocus = null;
                        Char.getMyChar().mobFocus = null;
                        Char.getMyChar().itemFocus = null;
                        Char.getMyChar().charFocus = GameScr.getCharByID(Char.getMyChar().cz);
                        Char.getMyChar().charFocus.cz = Char.getMyChar().charID;
                        GameScr.getInstance().df = GameScr.getInstance().de;
                        GameScr.getInstance().de = charID;
                        Char.ge = true;
                    } else if (charID == Char.getMyChar().charID) {
                        Char.getMyChar().cz = var3;
                        Char.getMyChar().npcFocus = null;
                        Char.getMyChar().mobFocus = null;
                        Char.getMyChar().itemFocus = null;
                        Char.getMyChar().charFocus = GameScr.getCharByID(Char.getMyChar().cz);
                        Char.getMyChar().charFocus.cz = Char.getMyChar().charID;
                        GameScr.getInstance().df = GameScr.getInstance().de;
                        GameScr.getInstance().de = var3;
                        Char.ge = true;
                        return;
                    }

                    return;
                case 67:
                    var3 = ms.reader().readInt();
                    charID = ms.reader().readInt();
                    var7 = 0;

                    try {
                        var7 = ms.reader().readInt();
                    } catch (Exception var129) {
                    }

                    if (var3 == Char.getMyChar().charID) {
                        var166 = GameScr.getCharByID(charID);
                        if (var7 > 0) {
                            InfoMe.a(mResources.a(mResources.jc, var166.charName));
                            Char.getMyChar().cHP = var7;
                            Char.getMyChar().db = 29;
                            if (var166 != null) {
                                var166.db = 89;
                            }
                        } else {
                            if (var166 != null) {
                                var166.db = 59;
                            }

                            Char.getMyChar().db = 59;
                            InfoMe.a(mResources.a(mResources.jd, var166.charName));
                        }

                        Char.getMyChar().cz = -9999;
                        Char.getMyChar().charFocus = null;
                        if (GameScr.getInstance().df >= 0) {
                            GameScr.getInstance().de = GameScr.getInstance().df;
                            GameScr.getInstance().df = -1;
                        } else {
                            GameScr.getInstance().de = -1;
                        }

                        if (var166 != null) {
                            var166.cz = -9999;
                            return;
                        }

                        return;
                    } else if (charID == Char.getMyChar().charID) {
                        var166 = GameScr.getCharByID(var3);
                        if (var7 > 0) {
                            if (var166 != null) {
                                var166.cHP = var7;
                            }

                            if (var166 != null) {
                                var166.db = 29;
                            }

                            Char.getMyChar().db = 89;
                            InfoMe.a(mResources.a(mResources.jb, var166.charName));
                        } else {
                            if (var166 != null) {
                                var166.db = 59;
                            }

                            Char.getMyChar().db = 59;
                            InfoMe.a(mResources.a(mResources.jd, var166.charName));
                        }

                        if (var166 != null) {
                            var166.cz = -9999;
                        }

                        Char.getMyChar().cz = -9999;
                        Char.getMyChar().charFocus = null;
                        if (GameScr.getInstance().df >= 0) {
                            GameScr.getInstance().de = GameScr.getInstance().df;
                            GameScr.getInstance().df = -1;
                        } else {
                            GameScr.getInstance().de = -1;
                        }

                        return;
                    } else {
                        var204 = GameScr.getCharByID(var3);
                        Char var206 = GameScr.getCharByID(charID);
                        if (var7 > 0) {
                            if (var204 != null) {
                                var204.cHP = var7;
                            }

                            if (var204 != null) {
                                var204.db = 29;
                            }

                            if (var206 != null) {
                                var206.db = 89;
                            }
                        } else {
                            if (var204 != null) {
                                var204.db = 59;
                            }

                            if (var206 != null) {
                                var206.db = 59;
                            }
                        }

                        if (var204 != null) {
                            var204.cz = -9999;
                        }

                        if (var206 != null) {
                            var206.cz = -9999;
                            return;
                        }

                        return;
                    }
                case 68:
                    if ((var166 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        var166.da = Char.getMyChar().charID;
                        Auto.b(var166);
                        Char.getMyChar().npcFocus = null;
                        Char.getMyChar().mobFocus = null;
                        Char.getMyChar().itemFocus = null;
                        Char.getMyChar().charFocus = var166;
                        Char.ge = true;
                        InfoMe.a(var166.charName + mResources.jf, 20, mFont.tahoma_7_red);
                    }

                    return;
                case 69:
                    Char.getMyChar().da = ms.reader().readInt();
                    Char.getMyChar().npcFocus = null;
                    Char.getMyChar().mobFocus = null;
                    Char.getMyChar().itemFocus = null;
                    Char.getMyChar().charFocus = GameScr.getCharByID(Char.getMyChar().da);
                    Char.ge = true;
                    return;
                case 70:
                    var166 = Char.getMyChar();

                    try {
                        var166 = GameScr.getCharByID(ms.reader().readInt());
                    } catch (Exception var128) {
                    }

                    var166.da = -9999;
                    return;
                case 71:
                    var40 = ms.reader().readLong();
                    var10000 = Char.getMyChar();
                    var10000.cExpDown -= var40;
                    GameScr.a("+" + var40, Char.getMyChar().cx, Char.getMyChar().cy - Char.getMyChar().bj, 2);
                    return;
                case 72:
                    Char.getMyChar().hieuChien = ms.reader().readByte();
                    Char.getMyChar().a(ms.reader().readShort(), ms.reader().readShort());
                    Char.getMyChar().cEXP = GameScr.b(Char.getMyChar().cLevel - 1);
                    Char.getMyChar().cExpDown = ms.reader().readLong();
                    GameScr.a(Char.getMyChar().cEXP);
                    return;
                case 75:
                    var186 = new BuNhin(ms.reader().readUTF(), ms.reader().readShort(), ms.reader().readShort());
                    GameScr.ai.addElement(var186);
                    ServerEffect.a(60, var186.a, var186.b, 1);
                    return;
                case 76:
                    if ((var70 = Mob.a(ms.reader().readUnsignedByte())) != null) {
                        if ((var186 = GameScr.f(ms.reader().readShort())) == null) {
                            return;
                        }

                        var173 = ms.reader().readShort();
                        var163 = ms.reader().readByte();
                        var168 = ms.reader().readByte();
                        var70.a(var186);
                        var70.a(var173, (byte) var163, (byte) var168);
                    }

                    return;
                case 77:
                    var186 = (BuNhin) GameScr.ai.elementAt(ms.reader().readShort());
                    GameScr.ai.removeElement(var186);
                    ServerEffect.a(60, var186.a, var186.b, 1);
                    return;
                case 78:
                    var184 = null;

                    try {
                        var184 = Mob.a(ms.reader().readUnsignedByte());
                    } catch (Exception var127) {
                    }

                    if (var184 != null && var184.h != 0 && var184.h != 0) {
                        var184.h = 0;
                        ServerEffect.a(60, var184.curX, var184.curY, 1);
                        ItemMap var205 = new ItemMap(ms.reader().readShort(), ms.reader().readShort(), var184.curX, var184.curY, ms.reader().readShort(), ms.reader().readShort());
                        GameScr.vItemMap.addElement(var205);
                        if (Res.e(var205.y - Char.getMyChar().cy) < 24 && Res.e(var205.x - Char.getMyChar().cx) < 24) {
                            Char.getMyChar().charFocus = null;
                            return;
                        }
                    }

                    return;
                case 79:
                    var3 = ms.reader().readInt();
                    if (AutoHD9xManager.shouldAcceptPartyInvite(var4 = ms.reader().readUTF()) || (var4.equals(Code.g) || Code.c(var4)) && !Code.c.f()) {
                        Service.getInstance().t(var3);
                        return;
                    }

                    GameCanvas.a(var4 + " " + mResources.md, 8887, new Integer(var3), 8888, new Integer(var3));
                    return;
                case 82:
                    GameScr.vParty.removeAllElements();
                    boolean var71 = ms.reader().readBoolean();

                    try {
                        for (int var72 = 0; var72 < 6; ++var72) {
                            GameScr.vParty.addElement(new Party(ms.reader().readInt(), ms.reader().readByte(), ms.reader().readUTF(), var71));
                        }
                    } catch (Exception var154) {
                    }

                    GameScr.getInstance().s();
                    var209 = ((Party) GameScr.vParty.firstElement()).d;
                    if (AutoHD9xManager.shouldUsePartyLeader(var209)) {
                        Code.g = var209;
                    } else if (Code.g == null) {
                        Code.g = var209;
                    } else if (!var209.equals(Code.g)) {
                        Service.getInstance().t();
                        return;
                    }

                    return;
                case 83:
                    GameScr.vParty.removeAllElements();
                    GameScr.getInstance().s();
                    return;
                case 84:
                    Friend var73 = new Friend(ms.reader().readUTF(), ms.reader().readByte());
                    GameScr.getInstance();
                    GameScr.b(var73.a);
                    if (var73.b == 0) {
                        InfoMe.a(mResources.io + " " + var73.a + " " + mResources.ip);
                        GameScr.aa.addElement(var73);
                    } else if (var73.b == 1) {
                        for (var3 = 0; var3 < GameScr.aa.size(); ++var3) {
                            if (((Friend) GameScr.aa.elementAt(var3)).a.equals(var73.a)) {
                                GameScr.aa.removeElementAt(var3);
                                break;
                            }
                        }

                        InfoMe.a(mResources.iq + " " + var73.a + " " + mResources.ej);
                        var73.b = 3;
                        GameScr.aa.insertElementAt(var73, 0);
                    }

                    if (GameScr.cf) {
                        GameScr.getInstance();
                        GameScr.c(0);
                        GameScr.indexRow = 0;
                        GameScr.scrMain.a();
                    }

                    return;
                case 85:
                    if ((var169 = Mob.a(ms.reader().readUnsignedByte())) != null) {
                        var169.n = ms.reader().readBoolean();
                    }

                    return;
                case 86:
                    if ((var169 = Mob.a(ms.reader().readUnsignedByte())) != null) {
                        var169.o = ms.reader().readBoolean();
                    }

                    return;
                case 87:
                    if ((charID = ms.reader().readInt()) == Char.getMyChar().charID) {
                        var166 = Char.getMyChar();
                    } else {
                        var166 = GameScr.getCharByID(charID);
                    }

                    if (var166 == null) {
                        return;
                    }

                    var3 = ms.reader().readUnsignedByte();
                    var173 = ms.reader().readShort();
                    var163 = ms.reader().readByte();
                    var168 = ms.reader().readByte();
                    byte var74 = 0;
                    var164 = -1;

                    try {
                        if ((var74 = ms.reader().readByte()) == 1) {
                            var164 = ms.reader().readInt();
                        }
                    } catch (Exception var126) {
                        var126.printStackTrace();
                    }

                    if (var166.mobMe != null) {
                        if (var74 == 0) {
                            Mob var210 = Mob.a(var3);
                            var166.mobMe.a(var210);
                        } else {
                            Char var211 = GameScr.getCharByID(var164);
                            var166.mobMe.b(var211);
                        }
                    }

                    var166.mobMe.a(var173, (byte) var163, (byte) var168);
                    return;
                case 88:
                    if ((charID = ms.reader().readInt()) == Char.getMyChar().charID) {
                        var166 = Char.getMyChar();
                    } else if ((var166 = GameScr.getCharByID(charID)) == null) {
                        return;
                    }

                    var166.cHP = var166.cMaxHP;
                    var166.cMP = var166.cMaxMP;
                    var166.cx = ms.reader().readShort();
                    var166.cy = ms.reader().readShort();
                    var166.aa();
                    return;
                case 89:
                    if ((var169 = Mob.a(ms.reader().readUnsignedByte())) != null) {
                        var169.p = ms.reader().readBoolean();
                    }

                    return;
                case 90:
                    if ((var169 = Mob.a(ms.reader().readUnsignedByte())) != null) {
                        var169.q = ms.reader().readBoolean();
                        if (!var169.q) {
                            ServerEffect.a(77, var169.curX, var169.curY - 9, 1);
                            return;
                        }
                    }

                    return;
                case 91:
                    if ((var169 = Mob.a(ms.reader().readUnsignedByte())) != null) {
                        var169.r = ms.reader().readBoolean();
                    }

                    return;
                case 92:
                    msgText = ms.reader().readUTF();
                    Short var75 = new Short(ms.reader().readShort());
                    GameCanvas.ak.a(msgText, new Command(mResources.di, GameCanvas.instance, 88818, var75), 0);
                    return;
                case 93:
                    charID = ms.reader().readInt();
                    GameScr.currentCharViewInfo = new Char();
                    if (Char.getMyChar().charID == charID) {
                        GameScr.currentCharViewInfo = Char.getMyChar();
                    } else {
                        if ((var166 = GameScr.getCharByID(charID)) == null) {
                            GameScr.currentCharViewInfo = new Char();
                        } else {
                            GameScr.currentCharViewInfo = var166;
                        }

                        GameScr.currentCharViewInfo.charID = charID;
                        GameScr.currentCharViewInfo.statusMe = 1;
                        GameScr.getInstance().ak();
                    }

                    GameScr.currentCharViewInfo.charName = ms.reader().readUTF();
                    GameScr.currentCharViewInfo.head = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cgender = ms.reader().readByte();
                    byte var76 = ms.reader().readByte();
                    GameScr.currentCharViewInfo.nClass = GameScr.nClass[var76];
                    GameScr.currentCharViewInfo.hieuChien = ms.reader().readByte();
                    GameScr.currentCharViewInfo.cHP = ms.reader().readInt();
                    GameScr.currentCharViewInfo.cMaxHP = ms.reader().readInt();
                    GameScr.currentCharViewInfo.cMP = ms.reader().readInt();
                    GameScr.currentCharViewInfo.cMaxMP = ms.reader().readInt();
                    GameScr.currentCharViewInfo.cspeed = ms.reader().readByte();
                    GameScr.currentCharViewInfo.cResFire = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cResIce = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cResWind = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cDmg = ms.reader().readInt();
                    GameScr.currentCharViewInfo.cGiamDmg = ms.reader().readInt();
                    GameScr.currentCharViewInfo.cExactly = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cMiss = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cCrit = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cReactDmg = ms.reader().readShort();
                    GameScr.currentCharViewInfo.sysUp = ms.reader().readShort();
                    GameScr.currentCharViewInfo.sysDown = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cLevel = ms.reader().readUnsignedByte();
                    GameScr.currentCharViewInfo.ak = ms.reader().readShort();
                    GameScr.currentCharViewInfo.cClanName = ms.reader().readUTF();
                    if (!GameScr.currentCharViewInfo.cClanName.equals("")) {
                        GameScr.currentCharViewInfo.ctypeClan = ms.reader().readByte();
                    }

                    GameScr.currentCharViewInfo.ak = ms.reader().readShort();
                    GameScr.currentCharViewInfo.al = ms.reader().readShort();
                    GameScr.currentCharViewInfo.an = ms.reader().readShort();
                    GameScr.currentCharViewInfo.ap = ms.reader().readShort();
                    GameScr.currentCharViewInfo.ar = ms.reader().readShort();
                    GameScr.currentCharViewInfo.at = ms.reader().readShort();
                    GameScr.currentCharViewInfo.am = ms.reader().readShort();
                    GameScr.currentCharViewInfo.ao = ms.reader().readShort();
                    GameScr.currentCharViewInfo.aq = ms.reader().readShort();
                    GameScr.currentCharViewInfo.as = ms.reader().readShort();
                    GameScr.currentCharViewInfo.au = ms.reader().readShort();
                    GameScr.currentCharViewInfo.aw = ms.reader().readByte();
                    GameScr.currentCharViewInfo.ax = ms.reader().readByte();
                    GameScr.currentCharViewInfo.bc = ms.reader().readByte();
                    GameScr.currentCharViewInfo.ay = ms.reader().readByte();
                    GameScr.currentCharViewInfo.az = ms.reader().readByte();
                    GameScr.currentCharViewInfo.arrItemBody = new Item[32];

                    try {
                        GameScr.currentCharViewInfo.n();
                        for (var77 = 0; var77 < 16; ++var77) {
                            short idItem = ms.reader().readShort();
                            if (idItem > -1) {
                                ItemTemplate iTemplate = ItemTemplateManager.get(idItem);
                                type = iTemplate.type;
                                GameScr.currentCharViewInfo.arrItemBody[type] = new Item();
                                GameScr.currentCharViewInfo.arrItemBody[type].indexUI = type;
                                GameScr.currentCharViewInfo.arrItemBody[type].typeUI = 5;
                                GameScr.currentCharViewInfo.arrItemBody[type].template = iTemplate;
                                GameScr.currentCharViewInfo.arrItemBody[type].isLock = true;
                                GameScr.currentCharViewInfo.arrItemBody[type].upgrade = ms.reader().readByte();
                                GameScr.currentCharViewInfo.arrItemBody[type].sys = ms.reader().readByte();
                                if (type == 1) {
                                    GameScr.currentCharViewInfo.weapon = GameScr.currentCharViewInfo.arrItemBody[type].template.part;
                                } else if (type == 2) {
                                    GameScr.currentCharViewInfo.body = GameScr.currentCharViewInfo.arrItemBody[type].template.part;
                                } else if (type == 6) {
                                    GameScr.currentCharViewInfo.leg = GameScr.currentCharViewInfo.arrItemBody[type].template.part;
                                }
                            }
                        }

                        for (var77 = 0; var77 < 16; ++var77) {
                            short idItem = ms.reader().readShort();
                            if (idItem > -1) {
                                ItemTemplate iTemplate = ItemTemplateManager.get(idItem);
                                type = iTemplate.type + 16;
                                GameScr.currentCharViewInfo.arrItemBody[type] = new Item();
                                GameScr.currentCharViewInfo.arrItemBody[type].indexUI = type;
                                GameScr.currentCharViewInfo.arrItemBody[type].typeUI = 5;
                                GameScr.currentCharViewInfo.arrItemBody[type].template = iTemplate;
                                GameScr.currentCharViewInfo.arrItemBody[type].isLock = true;
                                GameScr.currentCharViewInfo.arrItemBody[type].upgrade = ms.reader().readByte();
                                GameScr.currentCharViewInfo.arrItemBody[type].sys = ms.reader().readByte();
                                if (type == 1) {
                                    GameScr.currentCharViewInfo.weapon = GameScr.currentCharViewInfo.arrItemBody[type].template.part;
                                } else if (type == 2) {
                                    GameScr.currentCharViewInfo.body = GameScr.currentCharViewInfo.arrItemBody[type].template.part;
                                } else if (type == 6) {
                                    GameScr.currentCharViewInfo.leg = GameScr.currentCharViewInfo.arrItemBody[type].template.part;
                                }
                            }
                        }
                        GameScr.currentCharViewInfo.cCritRate = ms.reader().readShort();
                        return;
                    } catch (Exception e) {
                        return;
                    }
                case 94:
                    b(ms);
                    return;
                case 95:
                    var77 = ms.reader().readInt();
                    var10000 = Char.getMyChar();
                    var10000.xu += var77;
                    GameScr.a(var77 > 0 ? "+" + var77 : String.valueOf(var77), Char.getMyChar().cx, Char.getMyChar().cy - Char.getMyChar().bj - 10, 1);
                    return;
                case 96:
                    Char.getMyChar().taskOrders.addElement(new TaskOrder(ms.reader().readByte(), ms.reader().readInt(), ms.reader().readInt(), ms.reader().readUTF(), ms.reader().readUTF(), ms.reader().readUnsignedByte(), ms.reader().readUnsignedByte()));
                    Char.getMyChar().c(21);
                    LockGame.l();
                    return;
                case 97:
                    var163 = ms.reader().readByte();

                    for (var3 = 0; var3 < Char.getMyChar().taskOrders.size(); ++var3) {
                        TaskOrder var218;
                        if ((var218 = (TaskOrder) Char.getMyChar().taskOrders.elementAt(var3)).taskId == var163) {
                            var218.count = ms.reader().readInt();
                            if (var218.count == var218.maxCount) {
                                Char.getMyChar().c(61);
                            }

                            if (var218.taskId == 0) {
                                AutoNVHN.a = true;
                            }

                            return;
                        }
                    }

                    return;
                case 98:
                    var163 = ms.reader().readByte();

                    for (var3 = 0; var3 < Char.getMyChar().taskOrders.size(); ++var3) {
                        if (((TaskOrder) Char.getMyChar().taskOrders.elementAt(var3)).taskId == var163) {
                            Char.getMyChar().taskOrders.removeElementAt(var3);
                            break;
                        }
                    }

                    Char.getMyChar().c(21);
                    LockGame.n();
                    return;
                case 99:
                    if ((var170 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }
                    if (Code.auto instanceof AutoLoseLoiDai) {
                        for (int var12 = 0; var12 < Code.splitString(SettingNVDV.nameCharLoiDai, ",").length; ++var12) {
                            if (var170.charName.equals(Code.splitString(SettingNVDV.nameCharLoiDai, ",")[var12])) {
                                Service.getInstance().m(var170.charID);
                            }
                        }

                        return;
                    }
                    GameCanvas.a(var170.charName + " " + mResources.inviteLoiDaiMsg, 88840, var170, 8882, (Object) null);
                    return;
                case 100:
                    GameScr.ab.removeAllElements();
                    var163 = ms.reader().readByte();

                    for (var3 = 0; var3 < var163; ++var3) {
                        try {
                            DunItem var217;
                            (var217 = new DunItem()).a = ms.reader().readByte();
                            var217.b = ms.reader().readUTF();
                            var217.c = ms.reader().readUTF();
                            GameScr.ab.addElement(var217);
                        } catch (Exception var125) {
                        }
                    }

                    GameScr.getInstance().q();
                    return;
                case 101:
                    try {
                        GameScr.currentCharViewInfo.av = ms.reader().readInt();
                        GameScr.currentCharViewInfo.ba = ms.reader().readByte();
                        GameScr.currentCharViewInfo.bb = ms.reader().readByte();
                    } catch (Exception var124) {
                        var124.printStackTrace();
                    }

                    return;
                case 102:
                    if ((var167 = Char.getMyChar().arrItemBag[ms.reader().readByte()]) != null) {
                        GameScr.itemSell = var167;
                    }

                    Char.getMyChar().xu = ms.reader().readInt();
                    if (GameScr.itemSell != null) {
                        if (GameScr.itemSell.template.type == 16) {
                            GameScr.r -= GameScr.itemSell.quantity;
                        }

                        if (GameScr.itemSell.template.type == 17) {
                            GameScr.q -= GameScr.itemSell.quantity;
                        }

                        Char.getMyChar().arrItemBag[GameScr.itemSell.indexUI] = null;
                        GameScr.itemSell = null;
                        GameScr.getInstance().resetButton();
                        InfoMe.a(mResources.qj);
                    }

                    GameCanvas.setMaxTextLenght();
                    return;
                case 103:
                    GameScr.indexMenu = ms.reader().readByte();
                    GameScr.arrItemStands = new ItemStands[ms.reader().readInt()];

                    for (var78 = 0; var78 < GameScr.arrItemStands.length; ++var78) {
                        GameScr.arrItemStands[var78] = new ItemStands();
                        GameScr.arrItemStands[var78].item = new Item();
                        GameScr.arrItemStands[var78].item.itemId = ms.reader().readInt();
                        GameScr.arrItemStands[var78].d = (int) (System.currentTimeMillis() / 1000L);
                        GameScr.arrItemStands[var78].c = ms.reader().readInt();
                        GameScr.arrItemStands[var78].item.quantity = ms.reader().readUnsignedShort();
                        GameScr.arrItemStands[var78].e = ms.reader().readUTF();
                        GameScr.arrItemStands[var78].b = ms.reader().readInt();
                        GameScr.arrItemStands[var78].item.template = ItemTemplateManager.get(ms.reader().readShort());
                    }

                    GameScr.getInstance().openUI(37);
                    return;
                case 104:
                    c(ms);
                    return;
                case 106:
                    if ((var170 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        GameCanvas.a(var170.charName + " " + mResources.qx, 88841, var170, 8882, (Object) null);
                    }

                    return;
                case 107:
                    var168 = ms.reader().readByte();
                    GameCanvas.a(ms.reader().readUTF(), 8890, new Integer(var168), 8882, (Object) null);
                    return;
                case 108:
                    Char.getMyChar().b(ms);
                    return;
                case 109:
                    InfoDlg.d();
                    GameCanvas.l();
                    GameCanvas.k();
                    var165 = new MyVector();

                    try {
                        var78 = ms.reader().readByte();

                        for (var3 = 0; var3 < var78; ++var3) {
                            String[] var215 = new String[ms.reader().readByte()];

                            for (int var80 = 0; var80 < var215.length; ++var80) {
                                var215[var80] = ms.reader().readUTF();
                            }

                            var165.addElement(new Command(var215[0], GameCanvas.instance, 88820, var215));
                        }
                    } catch (Exception var153) {
                    }

                    if (Char.getMyChar().npcFocus == null) {
                        return;
                    }

                    GameCanvas.menu.showMenu(var165);
                    return;
                case 112:
                    (var167 = Char.getMyChar().arrItemBag[ms.reader().readByte()]).upgrade = ms.reader().readByte();
                    var167.expires = 0L;
                    return;
                case 114:
                    GameScr.getInstance().ee = ms.reader().readByte();
                    return;
                case 116:
                    if ((var166 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        updateCharInfo(var166, ms);
                    }

                    return;
                case 117:
                    if ((var163 = ms.reader().readByte()) == -1) {
                        GameCanvas.readMessage.messageBijuu(ms);
                        return;
                    }
                    if (GameCanvas.lowGraphic) {
                        return;
                    }

                    try {
                        Mob.aa.removeAllElements();
                        TileMap.itemMap.clear();
                        GameScr.eh.removeAllElements();
                        GameScr.ei.removeAllElements();
                        GameScr.eg.removeAllElements();
                        // var163 = ms.reader().readByte();

                        for (var3 = 0; var3 < var163; ++var3) {
                            var4 = String.valueOf(ms.reader().readShort());
                            byte[] var214 = new byte[ms.reader().readInt()];
                            ms.reader().read(var214);
                            Image var79 = a(var214);
                            TileMap.itemMap.put(var4, var79);
                        }

                        charID = ms.reader().readUnsignedByte();

                        ItemTree var216;
                        for (var3 = 0; var3 < charID; ++var3) {
                            var7 = ms.reader().readUnsignedByte();
                            var2 = ms.reader().readUnsignedByte();
                            var164 = ms.reader().readUnsignedByte();
                            (var216 = new ItemTree(var2, var164)).a = var7;
                            GameScr.eh.addElement(var216);
                        }

                        charID = ms.reader().readUnsignedByte();

                        for (var3 = 0; var3 < charID; ++var3) {
                            var7 = ms.reader().readUnsignedByte();
                            var2 = ms.reader().readUnsignedByte();
                            var164 = ms.reader().readUnsignedByte();
                            (var216 = new ItemTree(var2, var164)).a = var7;
                            GameScr.ei.addElement(var216);
                        }

                        charID = ms.reader().readUnsignedByte();

                        for (var3 = 0; var3 < charID; ++var3) {
                            var7 = ms.reader().readUnsignedByte();
                            var2 = ms.reader().readUnsignedByte();
                            var164 = ms.reader().readUnsignedByte();
                            (var216 = new ItemTree(var2, var164)).a = var7;
                            GameScr.eg.addElement(var216);
                        }

                        return;
                    } catch (Exception var152) {
                        var152.printStackTrace();
                        return;
                    }
                case 118:
                    msgText = ms.reader().readUTF();
                    RMS.writeRecord("acc", msgText);
                    name = ms.reader().readUTF();
                    RMS.writeRecord("pass", name);
                    SelectServerScr.uname = msgText;
                    SelectServerScr.pass = name;
                    SelectServerScr.unameChange = "";
                    SelectServerScr.passChange = "";
                    if (!msgText.startsWith("tmpusr")) {
                        GameScr.getInstance().update();
                    }

                    Session_ME.e();
                    return;
                case 119:
                    if ((var168 = ms.reader().readByte()) == -1) {
                        GameScr.eo = true;
                        GameScr.a(true);
                        if ((GameScr.fd = ms.reader().readInt()) > 360) {
                            GameScr.ep = true;
                        } else {
                            GameScr.ep = false;
                            GameScr.fb = Char.getMyChar().cx;
                            GameScr.fc = Char.getMyChar().cy;
                        }

                        return;
                    } else {
                        if (var168 == 0) {
                            Char var213;
                            if ((var213 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                                ServerEffect.a(141, var213.cx, var213.cy, 2);
                                var2 = ms.reader().readShort();
                                var213.fh = var2;
                                var2 = ms.reader().readShort();
                                var213.fi = var2;
                                ServerEffect.a(141, var213.cx, var213.cy, 2);
                                return;
                            }
                        } else {
                            GameScr.eo = false;
                            GameScr.es = 0;
                        }

                        return;
                    }
                case 121:
                    GameScr.ab.removeAllElements();
                    charID = ms.reader().readUnsignedByte();

                    for (var3 = 0; var3 < charID; ++var3) {
                        try {
                            Ranked var212;
                            (var212 = new Ranked()).name = ms.reader().readUTF();
                            var212.id = ms.reader().readInt();
                            var212.stt = ms.reader().readUTF();
                            GameScr.ab.addElement(var212);
                        } catch (Exception var123) {
                        }
                    }

                    GameScr.getInstance().p();
                    return;
                case 122:
                    if ((var2 = ms.reader().readByte()) == 0) {
                        i(ms);
                        return;
                    } else if (var2 == 1) {
                        j(ms);
                        return;
                    } else {
                        if (var2 == 2) {
                            l(ms);
                        } else if (var2 == 3) {
                            k(ms);
                            return;
                        }

                        return;
                    }
                case 123:
                    if ((var2 = ms.reader().readByte()) != 0 && var2 != 1) {
                    }

                    return;
                case 124:
                    m(ms);
                    return;
                case 125:
                    if ((var2 = ms.reader().readByte()) == 0) {
                        n(ms);
                        return;
                    } else {
                        if (var2 == 1) {
                            o(ms);
                        } else if (var2 == 2) {
                            p(ms);
                            return;
                        }

                        return;
                    }
                case 126:
                    var78 = ms.reader().readByte();
                    GameCanvas.setMaxTextLenght();
                    if (var78 == 0) {
                        GameScr.a.resetButton();
                    }

                    return;
            }
        } catch (Exception var160) {
            System.out.println("ERROR COMAND: " + ms.command);
            var160.printStackTrace();
            return;
        } finally {
            ms.cleanup();
        }

    }

    private static void a(DataInputStream var0) {
        try {
            GameScr.dl = var0.readByte();
            GameScr.iOptionTemplates = new ItemOptionTemplate[var0.readUnsignedByte()];

            for (int var1 = 0; var1 < GameScr.iOptionTemplates.length; ++var1) {
                GameScr.iOptionTemplates[var1] = new ItemOptionTemplate();
                GameScr.iOptionTemplates[var1].id = var1;
                GameScr.iOptionTemplates[var1].name = var0.readUTF();
                GameScr.iOptionTemplates[var1].type = var0.readByte();
            }

            short var3 = var0.readShort();

            for (int var2 = 0; var2 < var3; ++var2) {
                ItemTemplateManager.put(new ItemTemplate((short) var2, var0.readByte(), var0.readByte(), var0.readUTF(), var0.readUTF(), var0.readByte(), var0.readShort(), var0.readShort(), var0.readBoolean()));
            }
        } catch (IOException ex) {

        }

    }

    private static void b(DataInputStream var0) {
        try {
            GameScr.dk = var0.readByte();
            GameScr.sOptionTemplates = new SkillOptionTemplate[var0.readByte()];

            int var1;
            for (var1 = 0; var1 < GameScr.sOptionTemplates.length; ++var1) {
                GameScr.sOptionTemplates[var1] = new SkillOptionTemplate();
                GameScr.sOptionTemplates[var1].id = var1;
                GameScr.sOptionTemplates[var1].name = var0.readUTF();
            }

            GameScr.nClass = new NClass[var0.readUnsignedByte()];

            for (var1 = 0; var1 < GameScr.nClass.length; ++var1) {
                GameScr.nClass[var1] = new NClass();
                GameScr.nClass[var1].classId = var1;
                GameScr.nClass[var1].nameClass = var0.readUTF();
                GameScr.nClass[var1].skillTemplates = new SkillTemplate[var0.readByte()];

                for (int var2 = 0; var2 < GameScr.nClass[var1].skillTemplates.length; ++var2) {
                    GameScr.nClass[var1].skillTemplates[var2] = new SkillTemplate();
                    GameScr.nClass[var1].skillTemplates[var2].id = var0.readByte();
                    GameScr.nClass[var1].skillTemplates[var2].name = var0.readUTF();
                    GameScr.nClass[var1].skillTemplates[var2].maxPoint = var0.readByte();
                    GameScr.nClass[var1].skillTemplates[var2].type = var0.readByte();
                    GameScr.nClass[var1].skillTemplates[var2].iconId = var0.readShort();
                    int var3 = 150;
                    if (GameCanvas.width == 128 || GameCanvas.height <= 208) {
                        var3 = 100;
                    }

                    GameScr.nClass[var1].skillTemplates[var2].description = mFont.tahoma_7_white.b(var0.readUTF(), var3);
                    GameScr.nClass[var1].skillTemplates[var2].skills = new Skill[var0.readByte()];

                    for (var3 = 0; var3 < GameScr.nClass[var1].skillTemplates[var2].skills.length; ++var3) {
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3] = new Skill();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].skillId = var0.readShort();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].template = GameScr.nClass[var1].skillTemplates[var2];
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].point = var0.readByte();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].level = var0.readByte();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].manaUse = var0.readShort();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].coolDown = var0.readInt();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].dx = var0.readShort();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].dy = var0.readShort();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].maxFight = var0.readByte();
                        GameScr.nClass[var1].skillTemplates[var2].skills[var3].options = new SkillOption[var0.readByte()];

                        for (int var4 = 0; var4 < GameScr.nClass[var1].skillTemplates[var2].skills[var3].options.length; ++var4) {
                            GameScr.nClass[var1].skillTemplates[var2].skills[var3].options[var4] = new SkillOption();
                            GameScr.nClass[var1].skillTemplates[var2].skills[var3].options[var4].param = var0.readShort();
                            GameScr.nClass[var1].skillTemplates[var2].skills[var3].options[var4].optionTemplate = GameScr.sOptionTemplates[var0.readByte()];
                        }

                        Skills.a(GameScr.nClass[var1].skillTemplates[var2].skills[var3]);
                    }
                }
            }
        } catch (IOException ex) {

        }

    }

    private static void c(DataInputStream var0) {
        try {
            GameScr.dj = var0.readByte();
            TileMap.mapNames = new String[var0.readUnsignedByte()];

            int var1;
            for (var1 = 0; var1 < TileMap.mapNames.length; ++var1) {
                TileMap.mapNames[var1] = var0.readUTF();
            }

            Npc.arrNpcTemplate = new NpcTemplate[var0.readByte()];

            int var2;
            for (byte var4 = 0; var4 < Npc.arrNpcTemplate.length; ++var4) {
                Npc.arrNpcTemplate[var4] = new NpcTemplate();
                Npc.arrNpcTemplate[var4].npcTemplateId = var4;
                Npc.arrNpcTemplate[var4].name = var0.readUTF();
                Npc.arrNpcTemplate[var4].head = var0.readShort();
                Npc.arrNpcTemplate[var4].body = var0.readShort();
                Npc.arrNpcTemplate[var4].leg = var0.readShort();
                Npc.arrNpcTemplate[var4].menu = new String[var0.readByte()][];

                for (var2 = 0; var2 < Npc.arrNpcTemplate[var4].menu.length; ++var2) {
                    Npc.arrNpcTemplate[var4].menu[var2] = new String[var0.readByte()];

                    for (int var3 = 0; var3 < Npc.arrNpcTemplate[var4].menu[var2].length; ++var3) {
                        Npc.arrNpcTemplate[var4].menu[var2][var3] = var0.readUTF();
                    }
                }
            }

            Mob.mobTemplates = new MobTemplate[var1 = GameMidlet.VERSION_250_PLUS ? var0.readUnsignedShort() : var0.readUnsignedByte()];

            for (var2 = 0; var2 < var1; ++var2) {
                Mob.mobTemplates[var2] = new MobTemplate();
                Mob.mobTemplates[var2].e = (short) var2;
                Mob.mobTemplates[var2].c = var0.readByte();
                Mob.mobTemplates[var2].name = var0.readUTF();
                Mob.mobTemplates[var2].f = var0.readInt();
                Mob.mobTemplates[var2].a = var0.readByte();
                Mob.mobTemplates[var2].b = var0.readByte();
            }
        } catch (IOException ex) {

        }

    }

    private static void d(DataInputStream var0) {
        try {
            GameScr.di = var0.readByte();
            RMS.writeRecord("nj_arrow", NinjaUtil.a(var0));
            RMS.writeRecord("nj_effect", NinjaUtil.a(var0));
            RMS.writeRecord("nj_image", NinjaUtil.a(var0));
            RMS.writeRecord("nj_part", NinjaUtil.a(var0));
            RMS.writeRecord("nj_skill", NinjaUtil.a(var0));
            GameScr.dh = new byte[(GameScr.dg = new byte[var0.readByte()][]).length][];

            int var1;
            for (var1 = 0; var1 < GameScr.dg.length; ++var1) {
                GameScr.dg[var1] = new byte[var0.readByte()];
                GameScr.dh[var1] = new byte[GameScr.dg[var1].length];

                for (int var2 = 0; var2 < GameScr.dg[var1].length; ++var2) {
                    GameScr.dg[var1][var2] = var0.readByte();
                    GameScr.dh[var1][var2] = var0.readByte();
                }
            }

            GameScr.exps = new long[var0.readUnsignedByte()];

            for (var1 = 0; var1 < GameScr.exps.length; ++var1) {
                GameScr.exps[var1] = var0.readLong();
            }

            GameScr.crystals = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.crystals.length; ++var1) {
                GameScr.crystals[var1] = var0.readInt();
            }

            GameScr.upClothe = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.upClothe.length; ++var1) {
                GameScr.upClothe[var1] = var0.readInt();
            }

            GameScr.upAdorn = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.upAdorn.length; ++var1) {
                GameScr.upAdorn[var1] = var0.readInt();
            }

            GameScr.upWeapon = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.upWeapon.length; ++var1) {
                GameScr.upWeapon[var1] = var0.readInt();
            }

            GameScr.coinUpCrystals = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.coinUpCrystals.length; ++var1) {
                GameScr.coinUpCrystals[var1] = var0.readInt();
            }

            GameScr.coinUpClothes = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.coinUpClothes.length; ++var1) {
                GameScr.coinUpClothes[var1] = var0.readInt();
            }

            GameScr.coinUpAdorns = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.coinUpAdorns.length; ++var1) {
                GameScr.coinUpAdorns[var1] = var0.readInt();
            }

            GameScr.coinUpWeapons = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.coinUpWeapons.length; ++var1) {
                GameScr.coinUpWeapons[var1] = var0.readInt();
            }

            GameScr.goldUps = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.goldUps.length; ++var1) {
                GameScr.goldUps[var1] = var0.readInt();
            }

            GameScr.maxPercents = new int[var0.readByte()];

            for (var1 = 0; var1 < GameScr.maxPercents.length; ++var1) {
                GameScr.maxPercents[var1] = var0.readInt();
            }

            Effect.a = new EffectTemplate[var0.readByte()];

            for (var1 = 0; var1 < Effect.a.length; ++var1) {
                Effect.a[var1] = new EffectTemplate();
                Effect.a[var1].a = var0.readByte();
                Effect.a[var1].b = var0.readByte();
                var0.readUTF();
                Effect.a[var1].c = var0.readShort();
            }
        } catch (IOException ex) {

        }

    }

    public static Image a(byte[] var0) {
        try {
            return Image.createImage(var0, 0, var0.length);
        } catch (Exception var1) {
            return null;
        }
    }

    private static void d(Message ms) {
        try {
            Auto.g();
            Auto.h();
            Auto.isHetMP = false;
            Char var1;
            (var1 = Char.getMyChar()).e = null;
            var1.cx = var1.ff = ms.reader().readShort();
            var1.cy = var1.fg = ms.reader().readShort();
            byte var9 = ms.reader().readByte();

            int var2;
            for (var2 = 0; var2 < var9; ++var2) {
                TileMap.vGo.addElement(new Waypoint(ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort()));
            }

            Auto.g();
            var9 = ms.reader().readByte();

            byte var10;
            for (var10 = 0; var10 < var9; ++var10) {
                GameScr.createMob(new Mob(var10, ms.reader().readBoolean(), ms.reader().readBoolean(), ms.reader().readBoolean(), ms.reader().readBoolean(), ms.reader().readBoolean(), ms.reader().readUnsignedByte(), ms.reader().readByte(), ms.reader().readInt(), ms.reader().readUnsignedByte(), ms.reader().readInt(), ms.reader().readShort(), ms.reader().readShort(), ms.reader().readByte(), ms.reader().readByte(), ms.reader().readBoolean(), false), var10);
            }

            var9 = ms.reader().readByte();

            for (var10 = 0; var10 < var9; ++var10) {
                GameScr.ai.addElement(new BuNhin(ms.reader().readUTF(), ms.reader().readShort(), ms.reader().readShort()));
            }

            var9 = ms.reader().readByte();

            for (var2 = 0; var2 < var9; ++var2) {
                GameScr.ah.addElement(new Npc(ms.reader().readByte(), ms.reader().readShort(), ms.reader().readShort(), ms.reader().readByte()));
            }

            var9 = ms.reader().readByte();

            for (var2 = 0; var2 < var9; ++var2) {
                ItemMap var4 = new ItemMap(ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort());
                boolean var5 = false;

                for (int var3 = 0; var3 < GameScr.vItemMap.size(); ++var3) {
                    if (((ItemMap) GameScr.vItemMap.elementAt(var3)).itemMapID == var4.itemMapID) {
                        var5 = true;
                        break;
                    }
                }

                if (!var5) {
                    GameScr.vItemMap.addElement(var4);
                }
            }

            GameScr.a(false);

            try {
                TileMap.mapName1 = null;
                TileMap.mapName = TileMap.mapName1 = ms.reader().readUTF();
            } catch (Exception var6) {
            }

            try {
                TileMap.locationStand.clear();
                var2 = ms.reader().readUnsignedByte();

                for (int var12 = 0; var12 < var2; ++var12) {
                    int var13 = ms.reader().readUnsignedByte();
                    String var11 = String.valueOf((short) (ms.reader().readUnsignedByte() * TileMap.a + var13));
                    TileMap.locationStand.put(var11, "location");
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            TileMap.a(TileMap.e);
            Char.getMyChar().cvx = 0;
            Char.getMyChar().statusMe = 4;
            GameScr.getInstance().o();
            GameCanvas.setBackground(TileMap.bgID);
            Char.fl = false;
            Char.fk = false;
            GameCanvas.l();
            GameCanvas.k();
            if (!TileMap.ag || TileMap.af == TileMap.mapID) {
                GameScr.getInstance().update();
                InfoDlg.d();
                InfoDlg.a(TileMap.mapName, mResources.mh + " " + TileMap.zoneID, 30);
                Party.a();
                GameCanvas.setMaxTextLenght();
            }

            Session_ME.e();
            GameCanvas.e = false;
        } catch (Exception var8) {
        }

        TileMap.h();
    }

    private void e(Message ms) {
        try {
            try {
                int var2;
                int var3;
                short var4;
                int var10;
                int var11;
                Char var34;
                String var35;
                byte var36;
                byte[] var45;
                switch (ms.reader().readByte()) {
                    case -126:
                        var36 = ms.reader().readByte();
                        LoginScr.c = false;
                        SelectCharScr var48;
                        (var48 = SelectCharScr.a()).g = new String[3];
                        var48.b = new int[3];
                        var48.c = new int[3];
                        var48.d = new int[3];
                        var48.e = new int[3];
                        var48.f = new int[3];
                        var48.h = new String[3];
                        var48.i = new byte[3];
                        if (GameCanvas.isTouch) {
                            var48.a = -1;
                        } else {
                            var48.a = 0;
                        }

                        GameScr.getInstance();
                        GameScr.i();
                        SmallImage.d();

                        for (byte var49 = 0; var49 < var36; ++var49) {
                            SelectCharScr.a().i[var49] = ms.reader().readByte();
                            SelectCharScr.a().g[var49] = ms.reader().readUTF();
                            SelectCharScr.a().h[var49] = ms.reader().readUTF();
                            SelectCharScr.a().f[var49] = ms.reader().readUnsignedByte();
                            SelectCharScr.a().b[var49] = ms.reader().readShort();
                            SelectCharScr.a().e[var49] = ms.reader().readShort();
                            SelectCharScr.a().d[var49] = ms.reader().readShort();
                            SelectCharScr.a().c[var49] = ms.reader().readShort();
                            if (SelectCharScr.a().e[var49] == -1) {
                                SelectCharScr.a().e[var49] = 15;
                            }

                            if (SelectCharScr.a().d[var49] == -1) {
                                if (SelectCharScr.a().i[var49] == 0) {
                                    SelectCharScr.a().d[var49] = 10;
                                } else {
                                    SelectCharScr.a().d[var49] = 1;
                                }
                            }

                            if (SelectCharScr.a().c[var49] == -1) {
                                if (SelectCharScr.a().i[var49] == 0) {
                                    SelectCharScr.a().c[var49] = 9;
                                } else {
                                    SelectCharScr.a().c[var49] = 0;
                                }
                            }
                        }

                        SelectCharScr.a().update();
                        GameCanvas.setMaxTextLenght();
                        Session_ME.e();
                        SelectCharScr.a().tryQuickLoginFirstSlot();
                        return;
                    case -125:
                    case -124:
                    case -118:
                    case -110:
                    case -107:
                    case -105:
                    case -104:
                    case -103:
                    case -102:
                    case -101:
                    case -100:
                    case -94:
                    case -92:
                    case -91:
                    case -89:
                    case -87:
                    case -85:
                    case -82:
                    case -79:
                    case -78:
                    case -76:
                    case -75:
                    case -74:
                    case -73:
                    case -71:
                    case -69:
                    case -68:
                    case -65:
                    case -64:
                    case -63:
                    default:
                        return;
                    case -123:
                        GameScr.dm = ms.reader().readByte();
                        GameScr.dn = ms.reader().readByte();
                        GameScr.doa = ms.reader().readByte();
                        GameScr.dp = ms.reader().readByte();
                        System.out.println("****** DATA VERSION: Server " + GameScr.dm + " Client " + GameScr.di);
                        System.out.println("****** MAP VERSION: Server " + GameScr.dn + " Client " + GameScr.dj);
                        System.out.println("****** SKILL VERSION: Server " + GameScr.doa + " Client " + GameScr.dk);
                        System.out.println("****** ITEM VERSION: Server " + GameScr.dp + " Client " + GameScr.dl);
                        if (GameScr.dm != GameScr.di) {
                            Service.getInstance().m();
                        } else {
                            try {
                                d(new DataInputStream(new ByteArrayInputStream(RMS.getRecord(GameMidlet.VERSION_250_PLUS ? "data250" : "data"))));
                            } catch (Exception var30) {
                                GameScr.di = -1;
                                Service.getInstance().m();
                            }
                        }

                        if (GameScr.dn != GameScr.dj) {
                            Service.getInstance().n();
                        } else {
                            try {
                                c(new DataInputStream(new ByteArrayInputStream(RMS.getRecord("map"))));
                            } catch (Exception var29) {
                                GameScr.dj = -1;
                                Service.getInstance().n();
                            }
                        }

                        if (GameScr.doa != GameScr.dk) {
                            Service.getInstance().o();
                        } else {
                            try {
                                b(new DataInputStream(new ByteArrayInputStream(RMS.getRecord("skill"))));
                            } catch (Exception var28) {
                                GameScr.dk = -1;
                                Service.getInstance().o();
                            }
                        }

                        if (GameScr.dp != GameScr.dl) {
                            Service.getInstance().updateItem();
                        } else {
                            try {
                                a(new DataInputStream(new ByteArrayInputStream(RMS.getRecord("item"))));
                            } catch (Exception var27) {
                                GameScr.dl = -1;
                                Service.getInstance().updateItem();
                            }
                        }

                        if (GameScr.dm == GameScr.di && GameScr.dn == GameScr.dj && GameScr.doa == GameScr.dk && GameScr.dp == GameScr.dl) {
                            GameScr.getInstance();
                            GameScr.j();
                            GameScr.getInstance();
                            GameScr.k();
                            GameScr.getInstance();
                            GameScr.l();
                            Service.getInstance().clientOk();
                        }
                        return;
                    case -122:
                        System.out.println("GET UPDATE_DATA " + ms.reader().available() + " bytes");
                        ms.reader().mark(100000);
                        d(ms.reader());
                        ms.reader().reset();
                        var45 = new byte[ms.reader().available()];
                        ms.reader().readFully(var45);
                        RMS.writeRecord(GameMidlet.VERSION_250_PLUS ? "data250" : "data", var45);
                        var45 = new byte[]{GameScr.di};
                        RMS.writeRecord(GameMidlet.VERSION_250_PLUS ? "dataVersion250" : "dataVersion", var45);
                        if (GameScr.dm != GameScr.di || GameScr.dn != GameScr.dj || GameScr.doa != GameScr.dk || GameScr.dp != GameScr.dl) {
                            return;
                        }

                        GameScr.getInstance();
                        GameScr.j();
                        GameScr.getInstance();
                        GameScr.k();
                        GameScr.getInstance();
                        GameScr.l();
                        Service.getInstance().clientOk();
                        return;
                    case -121:
                        System.out.println("GET UPDATE_MAP " + ms.reader().available() + " bytes");
                        ms.reader().mark(100000);
                        c(ms.reader());
                        ms.reader().reset();
                        var45 = new byte[ms.reader().available()];
                        ms.reader().readFully(var45);
                        RMS.writeRecord("map", var45);
                        var45 = new byte[]{GameScr.dj};
                        RMS.writeRecord("mapVersion", var45);
                        if (GameScr.dm != GameScr.di || GameScr.dn != GameScr.dj || GameScr.doa != GameScr.dk || GameScr.dp != GameScr.dl) {
                            return;
                        }

                        GameScr.j();
                        GameScr.k();
                        GameScr.l();
                        Service.getInstance().clientOk();
                        return;
                    case -120:
                        System.out.println("GET UPDATE_SKILL " + ms.reader().available() + " bytes");
                        ms.reader().mark(100000);
                        b(ms.reader());
                        ms.reader().reset();
                        var45 = new byte[ms.reader().available()];
                        ms.reader().readFully(var45);
                        if (Char.getMyChar().isHuman) {
                            RMS.writeRecord("skill", var45);
                        } else {
                            RMS.writeRecord("skillnhanban", var45);
                        }

                        var45 = new byte[]{GameScr.dk};
                        RMS.writeRecord("skillVersion", var45);
                        if (GameScr.dm == GameScr.di && GameScr.dn == GameScr.dj && GameScr.doa == GameScr.dk && GameScr.dp == GameScr.dl) {
                            GameScr.j();
                            GameScr.k();
                            GameScr.l();
                            Service.getInstance().clientOk();
                            return;
                        }

                        return;
                    case -119:
                        System.out.println("GET UPDATE_ITEM " + ms.reader().available() + " bytes");
                        ms.reader().mark(100000);
                        a(ms.reader());
                        ms.reader().reset();
                        var45 = new byte[ms.reader().available()];
                        ms.reader().readFully(var45);
                        RMS.writeRecord("item", var45);
                        var45 = new byte[]{GameScr.dl};
                        RMS.writeRecord("itemVersion", var45);
                        if (GameScr.dm == GameScr.di && GameScr.dn == GameScr.dj && GameScr.doa == GameScr.dk && GameScr.dp == GameScr.dl) {
                            GameScr.j();
                            GameScr.k();
                            GameScr.l();
                            Service.getInstance().clientOk();
                            return;
                        }
                    case -117:
                        Char.getMyChar().hieuChien = ms.reader().readByte();
                        Info.a(mResources.ij + " " + Char.getMyChar().hieuChien, 15, mFont.tahoma_7_yellow);
                        Char.getMyChar().c(21);
                        return;
                    case -116:
                        Char.getMyChar().xu = ms.reader().readInt();
                        Char.clan.freeCoin = ms.reader().readInt();
                        return;
                    case -115:
                        var2 = ms.reader().readInt();
                        byte[] var39 = NinjaUtil.b(ms);
                        SmallImage.a(var2, var39);
                        return;
                    case -114:
                        if (Char.clan == null) {
                            Char.clan = new Clan();
                        }

                        Char.clan.a(ms.reader().readUTF());
                        return;
                    case -113:
                        if (Char.clan == null) {
                            Char.clan = new Clan();
                        }

                        Char.clan.name = ms.reader().readUTF();
                        Char.clan.main_name = ms.reader().readUTF();
                        ms.reader().readUTF();
                        Char.clan.total = ms.reader().readShort();
                        Char.clan.coin = ms.reader().readByte();
                        Char.clan.level = ms.reader().readByte();
                        Char.clan.exp = ms.reader().readInt();
                        Char.clan.expNext = ms.reader().readInt();
                        Char.clan.freeCoin = ms.reader().readInt();
                        Char.clan.coinUp = ms.reader().readInt();
                        Char.clan.i = ms.reader().readInt();
                        Char.clan.reg_date = ms.reader().readUTF();
                        Char.clan.alert = ms.reader().readUTF();
                        Char.clan.use_card = ms.reader().readInt();
                        Char.clan.openDun = ms.reader().readByte();
                        return;
                    case -112:
                        GameScr.vClan.removeAllElements();
                        var4 = ms.reader().readShort();

                        for (var3 = 0; var3 < var4; ++var3) {
                            GameScr.vClan.addElement(new Member(ms.reader().readByte(), ms.reader().readByte(), ms.reader().readByte(), ms.reader().readUTF(), ms.reader().readInt(), ms.reader().readBoolean()));
                        }

                        try {
                            for (var3 = 0; var3 < var4; ++var3) {
                                ((Member) GameScr.vClan.elementAt(var3)).pointClanWeek = ms.reader().readInt();
                            }
                        } catch (Exception var31) {
                        }

                        GameScr.getInstance();
                        GameScr.x();
                        return;
                    case -111:
                        Char.clan.items = new Item[30];
                        var36 = ms.reader().readByte();

                        for (var2 = 0; var2 < var36; ++var2) {
                            Char.clan.items[var2] = new Item();
                            Char.clan.items[var2].typeUI = 39;
                            Char.clan.items[var2].indexUI = var2;
                            Char.clan.items[var2].quantity = ms.reader().readShort();
                            Char.clan.items[var2].template = ItemTemplateManager.get(ms.reader().readShort());
                        }

                        GameScr.getInstance().ao();
                        byte var5 = ms.reader().readByte();

                        for (var3 = 0; var3 < var5; ++var3) {
                            String var40 = ms.reader().readUTF();
                            var4 = ms.reader().readShort();
                            short var38 = ms.reader().readShort();
                            int var42 = ms.reader().readInt();
                            var35 = "";
                            MyVector var43 = new MyVector();
                            var10 = -1;
                            var11 = -1;
                            byte var46 = ms.reader().readByte();
                            if (var42 >= 0) {
                                var35 = ms.reader().readUTF();
                            } else {
                                for (int var13 = 0; var13 < var46; ++var13) {
                                    String var44 = ms.reader().readUTF();
                                    var43.addElement(var44);
                                }

                                var10 = ms.reader().readInt();
                                var11 = ms.reader().readInt();
                            }

                            byte var47 = ms.reader().readByte();
                            GameScr.getInstance().a(new Clan_ThanThu(var40, var47, var4, var38, var42, var35, var43, var10, var11));
                        }

                        return;
                    case -109:
                        try {
                            GameCanvas.e = true;
                            TileMap.maps = null;
                            TileMap.types = null;
                            System.gc();
                            TileMap.a(TileMap.mapID, ms.reader());
                            TileMap.i();
                            d(this.b);
                        } catch (Exception var26) {
                            var26.printStackTrace();
                        }

                        ms.cleanup();
                        this.b.cleanup();
                        ms = this.b = null;
                        return;
                    case -108:
                        var4 = ms.reader().readShort();

                        try {
                            var3 = ms.reader().readByte();
                            Mob.mobTemplates[var4].d = (byte) var3;
                        } catch (Exception var25) {
                        }

                        var3 = ms.reader().readByte();
                        Mob.mobTemplates[var4].h = new Image[var3];
                        if (var4 != 98 && var4 != 99) {
                            for (var3 = 0; var3 < Mob.mobTemplates[var4].h.length; ++var3) {
                                Mob.mobTemplates[var4].h[var3] = a(NinjaUtil.b(ms));
                            }

                            if (var4 == 219) {
                                GameScr.getInstance().a(Mob.mobTemplates[var4].h[0]);
                            }
                        } else {
                            Mob.mobTemplates[var4].h = new Image[3];
                            Image var6 = a(NinjaUtil.b(ms));

                            for (var2 = 0; var2 < Mob.mobTemplates[var4].h.length; ++var2) {
                                Mob.mobTemplates[var4].h[var2] = var6;
                            }
                        }

                        int var37;
                        if (ms.reader().readBoolean()) {
                            var36 = ms.reader().readByte();
                            Mob.mobTemplates[var4].k = new byte[var36];

                            for (var2 = 0; var2 < var36; ++var2) {
                                Mob.mobTemplates[var4].k[var2] = ms.reader().readByte();
                            }

                            var36 = ms.reader().readByte();
                            Mob.mobTemplates[var4].l = new byte[var36][];

                            for (var2 = 0; var2 < var36; ++var2) {
                                Mob.mobTemplates[var4].l[var2] = new byte[ms.reader().readByte()];

                                for (var37 = 0; var37 < Mob.mobTemplates[var4].l[var2].length; ++var37) {
                                    Mob.mobTemplates[var4].l[var2][var37] = ms.reader().readByte();
                                }
                            }
                        }

                        if (ms.reader().readInt() > 0) {
                            Mob.mobTemplates[var4].i = new ImageInfo[ms.reader().readByte()];

                            for (var37 = 0; var37 < Mob.mobTemplates[var4].i.length; ++var37) {
                                Mob.mobTemplates[var4].i[var37] = new ImageInfo();
                                ms.reader().readByte();
                                Mob.mobTemplates[var4].i[var37].a = ms.reader().readUnsignedByte();
                                Mob.mobTemplates[var4].i[var37].b = ms.reader().readUnsignedByte();
                                Mob.mobTemplates[var4].i[var37].c = ms.reader().readUnsignedByte();
                                Mob.mobTemplates[var4].i[var37].d = ms.reader().readUnsignedByte();
                            }

                            Mob.mobTemplates[var4].j = new Frame[ms.reader().readShort()];

                            for (var37 = 0; var37 < Mob.mobTemplates[var4].j.length; ++var37) {
                                Mob.mobTemplates[var4].j[var37] = new Frame();
                                var36 = ms.reader().readByte();
                                Mob.mobTemplates[var4].j[var37].a = new short[var36];
                                Mob.mobTemplates[var4].j[var37].b = new short[var36];
                                Mob.mobTemplates[var4].j[var37].c = new byte[var36];

                                for (var2 = 0; var2 < var36; ++var2) {
                                    Mob.mobTemplates[var4].j[var37].a[var2] = ms.reader().readShort();
                                    Mob.mobTemplates[var4].j[var37].b[var2] = ms.reader().readShort();
                                    Mob.mobTemplates[var4].j[var37].c[var2] = ms.reader().readByte();
                                    if (GameMidlet.VERSION_250_PLUS) {
                                        ms.reader().readByte();
                                        ms.reader().readByte();
                                    }
                                }
                            }

                            var4 = GameMidlet.VERSION_250_PLUS ? ms.reader().readByte() : ms.reader().readShort();

                            for (var3 = 0; var3 < var4; ++var3) {
                                ms.reader().readShort();
                            }

                            if (GameMidlet.VERSION_250_PLUS) {
                                var4 = ms.reader().readByte();

                                for (var3 = 0; var3 < var4; ++var3) {
                                    var36 = ms.reader().readByte();

                                    for (var2 = 0; var2 < var36; ++var2) {
                                        ms.reader().readByte();
                                    }
                                }

                                for (var3 = 0; var3 < 4; ++var3) {
                                    ms.reader().readByte();
                                }
                            }
                        }

                        return;
                    case -106:
                        GameScr.ea = ms.reader().readByte();
                        return;
                    case -99:
                        GameCanvas.al.a(mResources.at, mResources.au);
                        var35 = ms.reader().readUTF();
                        GameCanvas.al.a(var35, new Command(mResources.am, GameCanvas.instance, 8882, (Object) null), new Command(mResources.ay, GameCanvas.instance, 88816, (Object) null), 0, 1);
                        return;
                    case -98:
                        Char.getMyChar();
                        Char.ab();
                        return;
                    case -97:
                        GameCanvas.e = false;
                        GameCanvas.setMaxTextLenght();
                        Integer var7 = new Integer(ms.reader().readInt());
                        GameCanvas.ak.a(mResources.ee, new Command(mResources.okey, GameCanvas.instance, 88829, var7), 0);
                        return;
                    case -96:
                        Char.getMyChar().cClanName = ms.reader().readUTF();
                        Char.getMyChar().ctypeClan = 4;
                        Char.getMyChar().luong = ms.reader().readInt();
                        Char.getMyChar().c(21);
                        return;
                    case -95:
                        if (Char.clan != null) {
                            Char.clan.alert = ms.reader().readUTF();
                        }

                        return;
                    case -93:
                        if ((var2 = ms.reader().readInt()) == Char.getMyChar().charID) {
                            GameScr.vClan.removeAllElements();
                            Char.getMyChar().cClanName = "";
                            Char.getMyChar().ctypeClan = -1;
                            Char.clan = null;
                        } else {
                            GameScr.vClan.removeAllElements();
                            Char var41;
                            (var41 = GameScr.getCharByID(var2)).cClanName = "";
                            var41.ctypeClan = -1;
                        }

                        return;
                    case -90:
                        Char.getMyChar().xu = ms.reader().readInt();
                        GameScr.getInstance().resetButton();
                        return;
                    case -88:
                        GameScr.getInstance().resetButton();
                        Item var8;
                        (var8 = Char.getMyChar().arrItemBag[ms.reader().readByte()]).clearExpire();
                        var8.isLock = true;
                        var8.upgrade = ms.reader().readByte();
                        (var8 = Char.getMyChar().arrItemBag[ms.reader().readByte()]).clearExpire();
                        var8.isLock = true;
                        var8.upgrade = ms.reader().readByte();
                        Info.a(mResources.mf, 20, mFont.tahoma_7b_yellow);
                        return;
                    case -86:
                        GameCanvas.setMaxTextLenght();
                        GameScr.getInstance().resetButton();
                        InfoMe.a(ms.reader().readUTF(), 20, mFont.tahoma_7_yellow);
                        return;
                    case -84:
                        Char.fb = ms.reader().readShort();
                        return;
                    case -83:
                        short var9 = ms.reader().readShort();
                        var4 = ms.reader().readShort();
                        var10 = ms.reader().readByte();
                        var11 = ms.reader().readShort();
                        if (var9 == 0) {
                            GameScr.getInstance().a(mResources.ea, "          " + mResources.nt, false);
                        } else {
                            var35 = mResources.doa + ": " + var9 + "\n\n";
                            if (var4 == 0) {
                                var35 = var35 + mResources.ds + "\n\n";
                            } else {
                                var35 = var35 + mResources.dt + ": " + NinjaUtil.b(var4) + "\n\n";
                            }

                            var35 = var35 + mResources.du + ": " + var10 + "\n\n";
                            var35 = var35 + mResources.em + ": " + var11 + " " + mResources.en + "\n\n";
                            GameScr.getInstance().a(mResources.ea, var35, false);
                            if (var11 > 0) {
                                GameScr.getInstance().left = new Command(mResources.em, 1000);
                                return;
                            }
                        }

                        return;
                    case -81:
                        Char.fc = ms.reader().readShort();
                        return;
                    case -80:
                        GameScr.getInstance().a(mResources.dz, ms.reader().readUTF(), false);
                        if (ms.reader().readBoolean()) {
                            GameScr.getInstance().left = new Command(mResources.em, 2000);
                            LockGame.z();
                        }

                        return;
                    case -77:
                        GameCanvas.setBackground(TileMap.bgID = ms.reader().readByte());
                        return;
                    case -72:
                        GameScr.getInstance().ef = new String[9];
                        GameScr.arrItemSprin = new short[9];
                        if (GameScr.indexSelect < 0 || GameScr.indexSelect > 8) {
                            GameScr.an = 0;
                            GameScr.indexSelect = 0;
                        }

                        for (var2 = 0; var2 < 9; ++var2) {
                            GameScr.arrItemSprin[var2] = ms.reader().readShort();
                            GameScr.getInstance().ef[var2] = GameScr.getInstance().ec[NinjaUtil.a(9)];
                        }

                        --GameScr.getInstance().numSprinLeft;
                        AutoDapDo.onLuckyCardResult();
                        if (AutoDapDo.isHidingLuckyCardUi()) {
                            AutoDapDo.hideLuckyCardUiNow();
                            return;
                        }

                        GameScr.getInstance().left = new Command(mResources.bd, (IActionListener) null, 1506, (Object) null);
                        GameScr.getInstance().eb = System.currentTimeMillis();
                        GameCanvas.setMaxTextLenght();
                        return;
                    case -70:
                        var35 = ms.reader().readUTF();
                        GameCanvas.a(NinjaUtil.replace(mResources.rb, "#", var35), new Command(mResources.bn, GameCanvas.instance, 88842, (Object) null), new Command(mResources.ca, GameCanvas.instance, 8882, (Object) null));
                        return;
                    case -67:
                        Mob var12 = null;

                        try {
                            var12 = Mob.a(ms.reader().readUnsignedByte());
                        } catch (Exception var24) {
                        }

                        if (var12 != null) {
                            if ((var2 = ms.reader().readInt()) == Char.getMyChar().charID) {
                                GameScr.w.addElement(new MobSoul(var12.curX, var12.curY, Char.getMyChar()));
                            } else if ((var34 = GameScr.getCharByID(var2)) != null) {
                                GameScr.w.addElement(new MobSoul(var12.curX, var12.curY, var34));
                                return;
                            }

                            return;
                        }

                        return;
                    case -66:
                        var2 = ms.reader().readInt();
                        if (Char.getMyChar().charID == var2) {
                            GameScr.w.addElement(new MobSoul(Char.getMyChar().cx, Char.getMyChar().cy));
                        } else if ((var34 = GameScr.getCharByID(var2)) != null) {
                            GameScr.w.addElement(new MobSoul(var34.cx, var34.cy));
                            return;
                        }

                        return;
                    case -62:
                        Char.clan.openDun = ms.reader().readByte();
                }
            } catch (Exception var32) {
            }

        } finally {
            if (ms != null) {
                ms.cleanup();
            }

        }
    }

    private static void f(Message var0) {
        try {
            byte var1 = var0.reader().readByte();
            System.out.println("subcmd Notlogin: " + var1);
            switch (var1) {
                case -124:
                    System.out.println("SEND SMS");
                    String var6 = var0.reader().readUTF();
                    SMS.a(var0.reader().readUTF(), "sms://" + var6, new Command("", GameCanvas.b(), 88825, (Object) null), new Command("", GameCanvas.b(), 88826, (Object) null));
                    return;
                case 2:
                    RMS.a();
                    return;
                default:
                    return;
            }
        } catch (Exception var4) {
        } finally {
            if (var0 != null) {
                var0.cleanup();
            }

        }

    }

    private static void g(Message ms) {
        try {
            int i;
            Skill var2;
            short var3;
            int index;
            GameScr var10000;
            int var30;
            byte var31;
            Char var32;
            Integer var34;
            byte var36;
            short var39;
            Effect var42;
            short idItem;
            EffectTemplate var44;
            String var46;
            Service var47;
            Effect var50;
            Char var54;
            switch (ms.reader().readByte()) {
                case -128:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        var32.cHP = ms.reader().readInt();
                        var32.cMaxHP = ms.reader().readInt();
                        var32.cLevel = ms.reader().readUnsignedByte();
                        return;
                    }

                    return;
                case -127:
                    GameScr.vCharInMap.removeAllElements();
                    GameScr.vItemMap.removeAllElements();
                    GameScr.ac();
                    GameScr.currentCharViewInfo = Char.getMyChar();
                    Char.getMyChar().charID = ms.reader().readInt();
                    Char.getMyChar().cClanName = ms.reader().readUTF();
                    if (!Char.getMyChar().cClanName.equals("")) {
                        Char.getMyChar().ctypeClan = ms.reader().readByte();
                    }

                    Char.getMyChar().ctaskId = ms.reader().readByte();
                    Char.getMyChar().cgender = ms.reader().readByte();
                    Char.getMyChar().head = ms.reader().readShort();
                    Char.getMyChar().cspeed = ms.reader().readByte();
                    Char.getMyChar().charName = ms.reader().readUTF();
                    Char.getMyChar().hieuChien = ms.reader().readByte();
                    Char.getMyChar().cTypePk = ms.reader().readByte();
                    Char.getMyChar().cMaxHP = ms.reader().readInt();
                    Char.getMyChar().cHP = ms.reader().readInt();
                    Char.getMyChar().cMaxMP = ms.reader().readInt();
                    Char.getMyChar().cMP = ms.reader().readInt();
                    Char.getMyChar().cEXP = ms.reader().readLong();
                    Char.getMyChar().cExpDown = ms.reader().readLong();
                    GameScr.a(Char.getMyChar().cEXP);
                    Char.getMyChar().eff5BuffHp = ms.reader().readShort();
                    Char.getMyChar().eff5BuffMp = ms.reader().readShort();
                    Char.getMyChar().nClass = GameScr.nClass[ms.reader().readByte()];
                    Char.getMyChar().ai = ms.reader().readShort();
                    Char.getMyChar().potential[0] = ms.reader().readShort();
                    Char.getMyChar().potential[1] = ms.reader().readShort();
                    Char.getMyChar().potential[2] = ms.reader().readInt();
                    Char.getMyChar().potential[3] = ms.reader().readInt();
                    Char.getMyChar().aj = ms.reader().readShort();
                    Char.getMyChar().vSkill.removeAllElements();
                    Char.getMyChar().vSkillFight.removeAllElements();
                    idItem = ms.reader().readByte();

                    for (var36 = 0; var36 < idItem; ++var36) {
                        var2 = Skills.a(ms.reader().readShort());
                        if (Char.getMyChar().selectSkill == null) {
                            Char.getMyChar().selectSkill = var2;
                        }

                        if (Code.auto != null && Auto.selectSkill != null && var2.template.id == Auto.selectSkill.template.id) {
                            Auto.selectSkill = var2;
                        }

                        Char.getMyChar().vSkill.addElement(var2);
                        if ((var2.template.type == 1 || var2.template.type == 4 || var2.template.type == 2 || var2.template.type == 3) && (var2.template.maxPoint == 0 || var2.template.maxPoint > 0 && var2.point > 0)) {
                            if (var2.template.id == 0) {
                                Service.getInstance().selectSkill(0);
                            }

                            Char.getMyChar().vSkillFight.addElement(var2);
                        }
                    }

                    GameScr.getInstance();
                    GameScr.y();
                    Char.getMyChar().xu = ms.reader().readInt();
                    Char.getMyChar().yen = ms.reader().readInt();
                    Char.getMyChar().luong = ms.reader().readInt();
                    Char.getMyChar().arrItemBag = new Item[ms.reader().readUnsignedByte()];
                    GameScr.q = 0;
                    GameScr.r = 0;

                    for (index = 0; index < Char.getMyChar().arrItemBag.length; ++index) {
                        if ((idItem = ms.reader().readShort()) != -1) {
                            Char.getMyChar().arrItemBag[index] = new Item();
                            Char.getMyChar().arrItemBag[index].typeUI = 3;
                            Char.getMyChar().arrItemBag[index].indexUI = index;
                            Char.getMyChar().arrItemBag[index].template = ItemTemplateManager.get(idItem);
                            Char.getMyChar().arrItemBag[index].isLock = ms.reader().readBoolean();
                            if (Char.getMyChar().arrItemBag[index].isTypeBody() || Char.getMyChar().arrItemBag[index].isTypeMounts() || Char.getMyChar().arrItemBag[index].isTypeNgocKham()) {
                                Char.getMyChar().arrItemBag[index].upgrade = ms.reader().readByte();
                                Service.getInstance().requestItemInfo(3, index);
                            }

                            Char.getMyChar().arrItemBag[index].isExpires = ms.reader().readBoolean();
                            Char.getMyChar().arrItemBag[index].quantity = ms.reader().readUnsignedShort();
                            if (Char.getMyChar().arrItemBag[index].template.type == 16) {
                                GameScr.r += Char.getMyChar().arrItemBag[index].quantity;
                            }

                            if (Char.getMyChar().arrItemBag[index].template.type == 17) {
                                GameScr.q += Char.getMyChar().arrItemBag[index].quantity;
                            }

                            if (Char.getMyChar().arrItemBag[index].template.id == 340) {
                                var10000 = GameScr.getInstance();
                                var10000.numSprinLeft += Char.getMyChar().arrItemBag[index].quantity;
                            }
                        }
                    }

                    Code.j();
                    Char.getMyChar().arrItemBody = new Item[32];

                    try {
                        Char.getMyChar().n();

                        for (index = 0; index < 16; ++index) {
                            if ((idItem = ms.reader().readShort()) != -1) {
                                ItemTemplate itemTemplate;
                                int indexTypeBody = (itemTemplate = ItemTemplateManager.get(idItem)).type;
                                Char.getMyChar().arrItemBody[indexTypeBody] = new Item();
                                Char.getMyChar().arrItemBody[indexTypeBody].indexUI = indexTypeBody;
                                Char.getMyChar().arrItemBody[indexTypeBody].typeUI = 5;
                                Char.getMyChar().arrItemBody[indexTypeBody].template = itemTemplate;
                                Char.getMyChar().arrItemBody[indexTypeBody].isLock = true;
                                Char.getMyChar().arrItemBody[indexTypeBody].upgrade = ms.reader().readByte();
                                Char.getMyChar().arrItemBody[indexTypeBody].sys = ms.reader().readByte();
                                Service.getInstance().requestItemInfo(5, indexTypeBody);
                                if (indexTypeBody == 1) {
                                    Char.getMyChar().weapon = Char.getMyChar().arrItemBody[indexTypeBody].template.part;
                                } else if (indexTypeBody == 2) {
                                    Char.getMyChar().body = Char.getMyChar().arrItemBody[indexTypeBody].template.part;
                                } else if (indexTypeBody == 6) {
                                    Char.getMyChar().leg = Char.getMyChar().arrItemBody[indexTypeBody].template.part;
                                }
                            }
                        }
                    } catch (Exception var26) {
                        var26.printStackTrace();
                    }

                    Char.getMyChar().isHuman = ms.reader().readBoolean();
                    Char.getMyChar().isNhanban = ms.reader().readBoolean();
                    short[] var52;
                    if ((var52 = new short[]{ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort()})[0] >= 0) {
                        Char.getMyChar().head = var52[0];
                    }

                    if (var52[1] >= 0) {
                        Char.getMyChar().weapon = var52[1];
                    }

                    if (var52[2] >= 0) {
                        Char.getMyChar().body = var52[2];
                    }

                    if (var52[3] >= 0) {
                        Char.getMyChar().leg = var52[3];
                    }

                    if (Char.getMyChar().isHuman) {
                        GameScr.getInstance();
                        GameScr.e();
                    } else if (Char.getMyChar().isNhanban) {
                        GameScr.getInstance();
                        GameScr.f();
                    }
                    short[] idFashion = new short[10];

                    try {
                        for (i = 0; i < 10; ++i) {
                            idFashion[i] = ms.reader().readShort();
                        }
                    } catch (Exception e) {
                        idFashion = null;
                    }

                    if (idFashion != null) {
                        Char.getMyChar().setFashion(idFashion);
                    }
                    try {
                        for (index = 0; index < 16; ++index) {
                            if ((idItem = ms.reader().readShort()) != -1) {
                                ItemTemplate itemTemplate;
                                int indexTypeFashion = (itemTemplate = ItemTemplateManager.get(idItem)).type + 16;
                                Char.getMyChar().arrItemBody[indexTypeFashion] = new Item();
                                Char.getMyChar().arrItemBody[indexTypeFashion].indexUI = indexTypeFashion;
                                Char.getMyChar().arrItemBody[indexTypeFashion].typeUI = 5;
                                Char.getMyChar().arrItemBody[indexTypeFashion].template = itemTemplate;
                                Char.getMyChar().arrItemBody[indexTypeFashion].isLock = true;
                                Char.getMyChar().arrItemBody[indexTypeFashion].upgrade = ms.reader().readByte();
                                Char.getMyChar().arrItemBody[indexTypeFashion].sys = ms.reader().readByte();
                                Service.getInstance().requestItemInfo(5, indexTypeFashion);
                                if (indexTypeFashion == 1) {
                                    Char.getMyChar().weapon = Char.getMyChar().arrItemBody[indexTypeFashion].template.part;
                                } else if (indexTypeFashion == 2) {
                                    Char.getMyChar().body = Char.getMyChar().arrItemBody[indexTypeFashion].template.part;
                                } else if (indexTypeFashion == 6) {
                                    Char.getMyChar().leg = Char.getMyChar().arrItemBody[indexTypeFashion].template.part;
                                }
                            }
                        }
                    } catch (Exception var26) {
                        var26.printStackTrace();
                    }

                    Char.getMyChar().statusMe = 4;
                    GameScr.cb = RMS.d(Char.getMyChar().charName + "vci") > 0;
                    return;
                case -126:
                    Char.getMyChar().a(ms);
                    Char.getMyChar().potential[0] = ms.reader().readShort();
                    Char.getMyChar().potential[1] = ms.reader().readShort();
                    Char.getMyChar().potential[2] = ms.reader().readInt();
                    Char.getMyChar().potential[3] = ms.reader().readInt();
                    Char.getMyChar().c(61);
                    Char.getMyChar().nClass = GameScr.nClass[ms.reader().readByte()];
                    Char.getMyChar().aj = ms.reader().readShort();
                    Char.getMyChar().ai = ms.reader().readShort();
                    Char.getMyChar().vSkill.removeAllElements();
                    Char.getMyChar().vSkillFight.removeAllElements();
                    Char.getMyChar().selectSkill = null;
                    return;
                case -125:
                    Char.getMyChar().a(ms);
                    if (Char.getMyChar().statusMe != 14 && Char.getMyChar().statusMe != 5) {
                        Char.getMyChar().cHP = Char.getMyChar().cMaxHP;
                        Char.getMyChar().cMP = Char.getMyChar().cMaxMP;
                    }

                    try {
                        Char.getMyChar().aj = ms.reader().readShort();
                        Char.getMyChar().vSkill.removeAllElements();
                        Char.getMyChar().vSkillFight.removeAllElements();
                        idItem = ms.reader().readByte();

                        for (var31 = 0; var31 < idItem; ++var31) {
                            Skill var48 = Skills.a(ms.reader().readShort());
                            if (Char.getMyChar().selectSkill == null) {
                                Char.getMyChar().selectSkill = var48;
                            } else if (var48.template.equals(Char.getMyChar().selectSkill.template)) {
                                Char.getMyChar().selectSkill = var48;
                            }

                            if (Code.auto != null && Auto.selectSkill != null && var48.template.id == Auto.selectSkill.template.id) {
                                Auto.selectSkill = var48;
                            }

                            Char.getMyChar().vSkill.addElement(var48);
                            if ((var48.template.type == 1 || var48.template.type == 4 || var48.template.type == 2 || var48.template.type == 3) && (var48.template.maxPoint == 0 || var48.template.maxPoint > 0 && var48.point > 0)) {
                                Char.getMyChar();
                                if (var48.template.id == 0) {
                                    var47 = Service.getInstance();
                                    Char.getMyChar();
                                    var47.selectSkill(0);
                                }

                                Char.getMyChar().vSkillFight.addElement(var48);
                            }
                        }

                        GameScr.y();
                        if (GameScr.isPaintInfoMe) {
                            GameScr.indexRow = -1;
                            GameScr.getInstance().showButtonIndexMenu();
                        }

                        System.out.println("LOAD XONG ME LOAD SKILL " + Char.getMyChar().vSkill.size());
                    } catch (Exception var25) {
                        var25.printStackTrace();
                    }

                    LockGame.v();
                    return;
                case -124:
                    Char.getMyChar().a(ms);
                    Char.getMyChar().cEXP = ms.reader().readLong();
                    GameScr.a(Char.getMyChar().cEXP);
                    Char.getMyChar().aj = ms.reader().readShort();
                    Char.getMyChar().ai = ms.reader().readShort();
                    Char.getMyChar().potential[0] = ms.reader().readShort();
                    Char.getMyChar().potential[1] = ms.reader().readShort();
                    Char.getMyChar().potential[2] = ms.reader().readInt();
                    Char.getMyChar().potential[3] = ms.reader().readInt();
                    return;
                case -123:
                    Char.getMyChar().xu = ms.reader().readInt();
                    Char.getMyChar().yen = ms.reader().readInt();
                    Char.getMyChar().luong = ms.reader().readInt();
                    Char.getMyChar().cHP = ms.reader().readInt();
                    Char.getMyChar().cMP = ms.reader().readInt();
                    if (ms.reader().readByte() == 1) {
                        GameScr.getInstance().an();
                        Char.getMyChar().isCaptcha = true;
                    } else {
                        Char.getMyChar().isCaptcha = false;
                        LockGame.j();
                    }

                    return;
                case -122:
                    Char.getMyChar().cHP = ms.reader().readInt();
                    return;
                case -121:
                    Char.getMyChar().cMP = ms.reader().readInt();
                    return;
                case -120:
                    System.out.println("PLAYER LOAD ALL");
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        updateCharInfo(var32, ms);
                    }

                    return;
                case -119:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    var32.cHP = ms.reader().readInt();
                    var32.cMaxHP = ms.reader().readInt();
                    return;
                case -117:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        var32.cHP = ms.reader().readInt();
                        var32.cMaxHP = ms.reader().readInt();
                        var32.eff5BuffHp = ms.reader().readShort();
                        var32.eff5BuffMp = ms.reader().readShort();
                        var32.weapon = ms.reader().readShort();
                        if (var32.weapon == -1) {
                            var32.o();
                        }

                        return;
                    }

                    return;
                case -116:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        var32.cHP = ms.reader().readInt();
                        var32.cMaxHP = ms.reader().readInt();
                        var32.eff5BuffHp = ms.reader().readShort();
                        var32.eff5BuffMp = ms.reader().readShort();
                        var32.body = ms.reader().readShort();
                        if (var32.body == -1) {
                            var32.p();
                        }

                        return;
                    }

                    return;
                case -113:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    var32.cHP = ms.reader().readInt();
                    var32.cMaxHP = ms.reader().readInt();
                    var32.eff5BuffHp = ms.reader().readShort();
                    var32.eff5BuffMp = ms.reader().readShort();
                    var32.leg = ms.reader().readShort();
                    if (var32.leg == -1) {
                        var32.q();
                    }

                    return;
                case -112:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    var32.cHP = ms.reader().readInt();
                    var32.cMaxHP = ms.reader().readInt();
                    var32.eff5BuffHp = ms.reader().readShort();
                    var32.eff5BuffMp = ms.reader().readShort();
                    return;
                case -111:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    var32.cHP = ms.reader().readInt();
                    return;
                case -110:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    var32.cHP = ms.reader().readInt();
                    var32.cMaxHP = ms.reader().readInt();
                    var32.cx = var32.fh = ms.reader().readShort();
                    var32.cy = var32.fi = ms.reader().readShort();
                    var32.statusMe = 1;
                    ServerEffect.a(20, var32, 2);
                    return;
                case -109:
                    Char.getMyChar().a(ms);
                    if (Char.getMyChar().statusMe != 14 && Char.getMyChar().statusMe != 5) {
                        Char.getMyChar().cHP = Char.getMyChar().cMaxHP;
                        Char.getMyChar().cMP = Char.getMyChar().cMaxMP;
                    }

                    Char.getMyChar().ai = ms.reader().readShort();
                    Char.getMyChar().potential[0] = ms.reader().readShort();
                    Char.getMyChar().potential[1] = ms.reader().readShort();
                    Char.getMyChar().potential[2] = ms.reader().readInt();
                    Char.getMyChar().potential[3] = ms.reader().readInt();
                    LockGame.x();
                    return;
                case -107:
                    Char.getMyChar().k();
                    return;
                case -106:
                    Char.getMyChar().l();
                    return;
                case -105:
                    var30 = ms.reader().readInt();
                    var54 = Char.getMyChar();
                    var54.xu -= var30;
                    var54 = Char.getMyChar();
                    var54.bs += var30;
                    return;
                case -104:
                    i = ms.reader().readInt();
                    var54 = Char.getMyChar();
                    var54.bs -= i;
                    var54 = Char.getMyChar();
                    var54.xu += i;
                    return;
                case -102:
                    Char.getMyChar().arrItemBag[ms.reader().readByte()] = null;
                    var2 = Skills.a(ms.reader().readShort());
                    Char.getMyChar().vSkill.addElement(var2);
                    if ((var2.template.type == 1 || var2.template.type == 4 || var2.template.type == 2 || var2.template.type == 3) && (var2.template.maxPoint == 0 || var2.template.maxPoint > 0 && var2.point > 0)) {
                        Char.getMyChar();
                        if (var2.template.id == 0) {
                            var47 = Service.getInstance();
                            Char.getMyChar();
                            var47.selectSkill(0);
                        }

                        Char.getMyChar().vSkillFight.addElement(var2);
                    }

                    GameScr.y();
                    GameScr.getInstance();
                    GameScr.a(var2);
                    GameScr.getInstance().showButtonIndexMenu();
                    InfoMe.a(mResources.pk + " " + var2.template.name);
                    return;
                case -101:
                    var50 = new Effect(ms.reader().readByte(), (int) (System.currentTimeMillis() / 1000L) - ms.reader().readInt(), ms.reader().readInt(), ms.reader().readShort());
                    Char.getMyChar().vEff.addElement(var50);
                    if (var50.e.b == 7) {
                        var54 = Char.getMyChar();
                        var54.cMiss += var50.d;
                        return;
                    }

                    if (var50.e.b != 12 && var50.e.b != 11) {
                        if (var50.e.b == 14) {
                            GameCanvas.k();
                            GameCanvas.l();
                            Char.getMyChar().cx = ms.reader().readShort();
                            Char.getMyChar().cy = ms.reader().readShort();
                            Char.getMyChar().statusMe = 1;
                            Char.getMyChar().fm = true;
                            ServerEffect.b(76, Char.getMyChar(), var50.c);
                            return;
                        } else {
                            if (var50.e.b == 1) {
                                ServerEffect.b(48, Char.getMyChar(), var50.c);
                            } else if (var50.e.b == 2) {
                                GameCanvas.k();
                                GameCanvas.l();
                                Char.getMyChar().cx = ms.reader().readShort();
                                Char.getMyChar().cy = ms.reader().readShort();
                                Char.getMyChar().statusMe = 1;
                                Char.getMyChar().fm = true;
                                Char.getMyChar().fn = true;
                            } else if (var50.e.b == 3) {
                                GameCanvas.k();
                                GameCanvas.l();
                                Char.getMyChar().cx = ms.reader().readShort();
                                Char.getMyChar().cy = ms.reader().readShort();
                                Char.getMyChar().statusMe = 1;
                                Char.fl = true;
                                ServerEffect.b(43, Char.getMyChar(), var50.c);
                            }
                            break;
                        }
                    }

                    Char.getMyChar().df = true;
                    ServerEffect.a(60, Char.getMyChar().cx, Char.getMyChar().cy, 1);
                    return;
                case -100:
                    var44 = Effect.a[ms.reader().readByte()];

                    for (i = 0; i < Char.getMyChar().vEff.size(); ++i) {
                        if ((var42 = (Effect) Char.getMyChar().vEff.elementAt(i)).e.b == var44.b) {
                            if (var42.e.b == 7) {
                                var54 = Char.getMyChar();
                                var54.cMiss -= var42.d;
                            }

                            var42.e = var44;
                            var42.b = (int) (System.currentTimeMillis() / 1000L) - ms.reader().readInt();
                            var42.c = ms.reader().readInt() / 1000;
                            var42.d = ms.reader().readShort();
                            if (var42.e.b == 7) {
                                var54 = Char.getMyChar();
                                var54.cMiss += var42.d;
                            }
                            break;
                        }
                    }

                    if (!GameScr.isPaintInfoMe) {
                        GameScr.getInstance().resetButton();
                    }

                    return;
                case -99:
                    var36 = ms.reader().readByte();
                    var42 = null;

                    for (i = 0; i < Char.getMyChar().vEff.size(); ++i) {
                        if ((var42 = (Effect) Char.getMyChar().vEff.elementAt(i)).e.a == var36) {
                            if (var42.e.b == 7) {
                                var54 = Char.getMyChar();
                                var54.cMiss -= var42.d;
                            }

                            Char.getMyChar().vEff.removeElementAt(i);
                            break;
                        }
                    }

                    if (var42.e.b != 0 && var42.e.b != 12) {
                        if (var42.e.b != 4 && var42.e.b != 13 && var42.e.b != 17) {
                            if (var42.e.b == 23) {
                                Char.getMyChar().cHP = ms.reader().readInt();
                                Char.getMyChar().cMaxHP = ms.reader().readInt();
                                return;
                            } else {
                                if (var42.e.b == 11) {
                                    Char.getMyChar().df = false;
                                    ServerEffect.a(60, Char.getMyChar().cx, Char.getMyChar().cy, 1);
                                } else if (var42.e.b == 14) {
                                    Char.getMyChar().fm = false;
                                } else if (var42.e.b == 2) {
                                    Char.getMyChar().fm = false;
                                    Char.getMyChar().fn = false;
                                    ServerEffect.a(77, Char.getMyChar().cx, Char.getMyChar().cy - 9, 1);
                                } else if (var42.e.b == 3) {
                                    Char.fl = false;
                                }
                                break;
                            }
                        }

                        Char.getMyChar().cHP = ms.reader().readInt();
                        return;
                    } else {
                        Char.getMyChar().cHP = ms.reader().readInt();
                        Char.getMyChar().cMP = ms.reader().readInt();
                        if (var42.e.b == 0) {
                            InfoMe.a(mResources.nc);
                        } else if (var42.e.b == 12) {
                            Char.getMyChar().df = false;
                            ServerEffect.a(60, Char.getMyChar().cx, Char.getMyChar().cy, 1);
                            break;
                        }

                        return;
                    }
                case -98:
                    try {
                        if ((var32 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                            return;
                        }

                        var50 = new Effect(ms.reader().readByte(), (int) (System.currentTimeMillis() / 1000L) - ms.reader().readInt(), ms.reader().readInt(), ms.reader().readShort());
                        var32.vEff.addElement(var50);
                        if (var50.e.b != 12 && var50.e.b != 11) {
                            if (var50.e.b == 14) {
                                var32.cx = var32.fh = ms.reader().readShort();
                                var32.cy = var32.fi = ms.reader().readShort();
                                var32.statusMe = 1;
                                ServerEffect.b(76, var32, var50.c);
                                return;
                            } else {
                                if (var50.e.b == 1) {
                                    ServerEffect.b(48, var32, var50.c);
                                } else if (var50.e.b == 2) {
                                    var32.cx = var32.fh = ms.reader().readShort();
                                    var32.cy = var32.fi = ms.reader().readShort();
                                    var32.statusMe = 1;
                                    var32.fn = true;
                                } else if (var50.e.b == 3) {
                                    var32.cx = var32.fh = ms.reader().readShort();
                                    var32.cy = var32.fi = ms.reader().readShort();
                                    var32.statusMe = 1;
                                    ServerEffect.b(43, var32, var50.c);
                                }
                                break;
                            }
                        }

                        var32.df = true;
                        ServerEffect.a(60, var32.cx, var32.cy, 1);
                    } catch (Exception var23) {
                    }

                    return;
                case -97:
                    try {
                        if ((var32 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                            var44 = Effect.a[ms.reader().readByte()];

                            for (i = 0; i < var32.vEff.size(); ++i) {
                                var42 = (Effect) var32.vEff.elementAt(i);
                                if (var44.b == var44.b) {
                                    var42.e = var44;
                                    var42.b = (int) (System.currentTimeMillis() / 1000L) - ms.reader().readInt();
                                    var42.c = ms.reader().readInt() / 1000;
                                    var42.d = ms.reader().readShort();
                                    return;
                                }
                            }

                            return;
                        }

                        return;
                    } catch (Exception var27) {
                        return;
                    }
                case -96:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        var36 = ms.reader().readByte();
                        var50 = null;

                        for (index = 0; index < var32.vEff.size(); ++index) {
                            if ((var50 = (Effect) var32.vEff.elementAt(index)).e.a == var36) {
                                var32.vEff.removeElementAt(index);
                                break;
                            }
                        }

                        if (var50 == null) {
                            return;
                        }

                        if (var50.e.b == 0) {
                            var32.cHP = ms.reader().readInt();
                            var32.cMP = ms.reader().readInt();
                            return;
                        }

                        if (var50.e.b == 11) {
                            var32.cx = var32.fh = ms.reader().readUnsignedShort();
                            var32.cy = var32.fi = ms.reader().readUnsignedShort();
                            var32.df = false;
                            ServerEffect.a(60, var32.cx, var32.cy, 1);
                            return;
                        }

                        if (var50.e.b == 12) {
                            var32.cHP = ms.reader().readInt();
                            var32.cMP = ms.reader().readInt();
                            var32.df = false;
                            ServerEffect.a(60, var32.cx, var32.cy, 1);
                            return;
                        }

                        if (var50.e.b != 4 && var50.e.b != 13 && var50.e.b != 17) {
                            if (var50.e.b == 23) {
                                Char.getMyChar().cHP = ms.reader().readInt();
                                Char.getMyChar().cMaxHP = ms.reader().readInt();
                            } else if (var50.e.b == 2) {
                                var32.fn = false;
                                ServerEffect.a(77, var32.cx, var32.cy - 9, 1);
                                break;
                            }

                            return;
                        }

                        var32.cHP = ms.reader().readInt();
                        return;
                    }

                    return;
                case -95:
                    GameScr.getInstance().dy = ms.reader().readInt();
                    GameScr.getInstance().dz = (int) (System.currentTimeMillis() / 1000L);
                    return;
                case -94:
                    var36 = ms.reader().readByte();
                    Npc var38;
                    (var38 = (Npc) GameScr.ah.elementAt(var36)).statusMe = ms.reader().readByte();
                    if (var38.template.npcTemplateId == 31 && var38.statusMe == 15) {
                        GameScr.a(var38.cx, var38.cy);
                    }

                    return;
                case -92:
                    if ((i = ms.reader().readInt()) == Char.getMyChar().charID) {
                        var32 = Char.getMyChar();
                    } else {
                        var32 = GameScr.getCharByID(i);
                    }

                    if (var32 != null) {
                        var32.cTypePk = ms.reader().readByte();
                        Auto.b(var32);
                        if (var32 == Char.getMyChar()) {
                            if (var32.cTypePk == 4) {
                                GameScr.fk = true;
                            } else if (var32.cTypePk == 5) {
                                GameScr.fk = false;
                            }
                            break;
                        }
                    }

                    return;
                case -91:
                    Item[] var49 = new Item[ms.reader().readUnsignedByte()];

                    for (i = 0; i < Char.getMyChar().arrItemBag.length; ++i) {
                        var49[i] = Char.getMyChar().arrItemBag[i];
                    }

                    Char.getMyChar().arrItemBag = var49;
                    Char.getMyChar().arrItemBag[ms.reader().readUnsignedByte()] = null;
                    InfoMe.a(mResources.eo + " " + Char.getMyChar().arrItemBag.length + " " + mResources.fi);
                    return;
                case -90:
                    for (i = 0; i < GameScr.ah.size(); ++i) {
                        Npc var41;
                        if ((var41 = (Npc) GameScr.ah.elementAt(i)).statusMe == 15) {
                            var41.statusMe = 1;
                            break;
                        }
                    }

                    if ((idItem = ms.reader().readByte()) == 1) {
                        InfoMe.a(mResources.ep, 20, mFont.tahoma_7_yellow);
                    } else if (idItem == 2) {
                        InfoMe.a(mResources.eq, 20, mFont.tahoma_7_yellow);
                        break;
                    }

                    return;
                case -89:
                    GameCanvas.e = false;

                    try {
                        InfoMe.a(ms.reader().readUTF(), 20, mFont.tahoma_7_yellow);
                    } catch (Exception var19) {
                    }

                    InfoDlg.d();
                    GameCanvas.setMaxTextLenght();
                    return;
                case -87:
                    var31 = ms.reader().readByte();
                    Party var40 = (Party) GameScr.vParty.elementAt(var31);
                    GameScr.vParty.setElementAt(GameScr.vParty.elementAt(0), var31);
                    GameScr.vParty.setElementAt(var40, 0);
                    GameScr.getInstance().s();
                    InfoMe.a(var40.d + mResources.od, 20, mFont.tahoma_7_yellow);
                    return;
                case -86:
                    GameScr.vParty.removeAllElements();
                    GameScr.getInstance().s();
                    InfoMe.a(mResources.oe, 20, mFont.tahoma_7_yellow);
                    Code.g = null;
                    return;
                case -85:
                    GameScr.aa.removeAllElements();

                    try {
                        while (true) {
                            GameScr.aa.addElement(new Friend(ms.reader().readUTF(), ms.reader().readByte()));
                        }
                    } catch (Exception var22) {
                        for (i = 0; i < GameScr.ac.size(); ++i) {
                            GameScr.aa.addElement(GameScr.ac.elementAt(i));
                        }

                        GameScr.getInstance();
                        GameScr.c(0);
                        return;
                    }
                case -84:
                    GameScr.ad.removeAllElements();

                    try {
                        while (true) {
                            GameScr.ad.addElement(new Friend(ms.reader().readUTF(), ms.reader().readByte()));
                        }
                    } catch (Exception var21) {
                        GameScr.getInstance();
                        GameScr.c(1);
                        return;
                    }
                case -83:
                    var46 = ms.reader().readUTF();

                    for (var30 = 0; var30 < GameScr.aa.size(); ++var30) {
                        if (((Friend) GameScr.aa.elementAt(var30)).a.equals(var46)) {
                            GameScr.indexRow = 0;
                            GameScr.aa.removeElementAt(var30);
                            GameScr.getInstance();
                            GameScr.b(var46);
                            break;
                        }
                    }

                    if (GameScr.cf) {
                        GameScr.getInstance();
                        GameScr.c(0);
                        GameScr.indexRow = 0;
                        GameScr.scrMain.a();
                    }

                    return;
                case -82:
                    var46 = ms.reader().readUTF();

                    for (var30 = 0; var30 < GameScr.ad.size(); ++var30) {
                        if (((Friend) GameScr.ad.elementAt(var30)).a.equals(var46)) {
                            GameScr.indexRow = 0;
                            GameScr.ad.removeElementAt(var30);
                            break;
                        }
                    }

                    GameScr.getInstance();
                    GameScr.c(1);
                    return;
                case -81:
                    Char.getMyChar().hieuChien = ms.reader().readByte();
                    Char.getMyChar().charFocus = null;
                    return;
                case -80:
                    Char.getMyChar().arrItemBody[ms.reader().readByte()] = null;
                    return;
                case -78:
                    ServerEffect.a(ms.reader().readShort(), Char.getMyChar().cx, Char.getMyChar().cy, 1);
                    return;
                case -77:
                    try {
                        GameScr.z.removeAllElements();

                        while (true) {
                            GameScr.z.addElement(new Party(ms.reader().readByte(), ms.reader().readUnsignedByte(), ms.reader().readUTF(), ms.reader().readByte()));
                        }
                    } catch (Exception var20) {
                        GameScr.getInstance().r();
                        return;
                    }
                case -76:
                    ((Party) GameScr.vParty.firstElement()).e = ms.reader().readBoolean();
                    return;
                case -75:
                    Char.getMyChar().arrItemBox[ms.reader().readByte()] = null;
                    return;
                case -74:
                    InfoDlg.a(ms.reader().readUTF());
                    return;
                case -73:
                    Mob var45 = Mob.a(ms.reader().readUnsignedByte());
                    ServerEffect.a(67, var45.curX, var45.curY, 1);
                    return;
                case -72:
                    Char.getMyChar().luong = ms.reader().readInt();
                    return;
                case -71:
                    i = ms.reader().readInt();
                    var54 = Char.getMyChar();
                    var54.luong += i;
                    GameScr.a("+" + i, Char.getMyChar().cx, Char.getMyChar().cy - Char.getMyChar().bj - 10, 6);
                    InfoMe.a(mResources.kq + " " + i + " " + mResources.ht, 20, mFont.tahoma_7_yellow);
                    return;
                case -69:
                    index = GameMidlet.VERSION_250_PLUS ? ms.reader().readUnsignedShort() : ms.reader().readUnsignedByte();
                    idItem = ms.reader().readByte();
                    if (index > 0) {
                        var39 = (short) Char.getMyChar().cx;
                        var3 = (short) (Char.getMyChar().cy - 40);
                        Char.getMyChar().mobMe = new Mob((short) -1, false, false, false, false, false, index, 1, 0, 0, 0, var39, var3, (byte) 4, (byte) 0, idItem != 0, false);
                        Char.getMyChar().mobMe.h = 5;
                    } else {
                        Char.getMyChar().mobMe = null;
                    }

                    return;
                case -68:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    index = GameMidlet.VERSION_250_PLUS ? ms.reader().readUnsignedShort() : ms.reader().readUnsignedByte();
                    idItem = ms.reader().readByte();
                    if (index > 0) {
                        var3 = (short) var32.cx;
                        var39 = (short) (var32.cy - 40);
                        var32.mobMe = new Mob((short) -1, false, false, false, false, false, index, 1, 0, 0, 0, var3, var39, (byte) 4, (byte) 0, idItem != 0, false);
                        var32.mobMe.h = 5;
                    } else {
                        var32.mobMe = null;
                    }

                    return;
                case -65:
                    String var5 = ms.reader().readUTF();
                    byte[] var37 = new byte[ms.reader().readInt()];
                    ms.reader().read(var37);
                    if (var37.length == 0) {
                        var37 = null;
                    }

                    try {
                        ms.reader().readByte();
                    } catch (Exception var18) {
                        var18.printStackTrace();
                    }

                    if (var5.equals("KSkill")) {
                        GameScr.getInstance();
                        GameScr.b(var37);
                        return;
                    } else {
                        if (var5.equals("OSkill")) {
                            GameScr.getInstance();
                            GameScr.a(var37);
                        } else if (var5.equals("CSkill")) {
                            GameScr.getInstance();
                            GameScr.c(var37);
                        }
                        break;
                    }
                case -64:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) == null) {
                        return;
                    }

                    var32.cHP = ms.reader().readInt();
                    var32.cMaxHP = ms.reader().readInt();
                    var32.eff5BuffHp = ms.reader().readShort();
                    var32.eff5BuffMp = ms.reader().readShort();
                    var32.head = ms.reader().readShort();
                    return;
                case -63:
                    var34 = new Integer(ms.reader().readInt());
                    if ((var32 = GameScr.getCharByID(var34.intValue())) != null) {
                        GameCanvas.a(var32.charName + " " + mResources.a(mResources.lz, ms.reader().readUTF()), 88830, var34, 88811, (Object) null);
                    }

                    return;
                case -62:
                    i = ms.reader().readInt();
                    if (Char.getMyChar().charID == i) {
                        Char.getMyChar().cClanName = ms.reader().readUTF();
                        Char.getMyChar().ctypeClan = ms.reader().readByte();
                        Char.getMyChar().c(21);
                    } else {
                        (var32 = GameScr.getCharByID(i)).cClanName = ms.reader().readUTF();
                        var32.ctypeClan = ms.reader().readByte();
                    }

                    return;
                case -61:
                    var34 = new Integer(ms.reader().readInt());

                    if (GameScr.cb && (var32 = GameScr.getCharByID(var34.intValue())) != null) {
                        if (Code.auto instanceof AutoNTGT) {
                            Service.getInstance().ah(var34.intValue());
                            return;
                        }
                        GameCanvas.a(var32.charName + " " + mResources.ma, 88831, var34, 88811, (Object) null);
                    }

                    return;
                case -59:
                    if ((i = ms.reader().readInt()) == Char.getMyChar().charID) {
                        var32 = Char.getMyChar();
                    } else {
                        var32 = GameScr.getCharByID(i);
                    }

                    var32.cHP = ms.reader().readInt();
                    var32.cMaxHP = ms.reader().readInt();
                    return;
                case -58:
                    GameScr.getInstance().resetButton();
                    GameCanvas.ao = 70;
                    GameCanvas.d = true;
                    ServerEffect.a(119, GameScr.d + GameScr.cmx, GameScr.f + GameScr.cmy, 1);
                    return;
                case -57:
                    GameCanvas.ao = 40;
                    GameCanvas.d = true;
                    return;
                case -56:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        var32.cHP = ms.reader().readInt();
                        var32.cMaxHP = ms.reader().readInt();
                        var32.fx = (short) ms.reader().readUnsignedShort();
                    }

                    return;
                case -55:
                    if ((var32 = GameScr.getCharByID(ms.reader().readInt())) != null) {
                        var32.cHP = ms.reader().readInt();
                        var32.cMaxHP = ms.reader().readInt();
                        var32.fy = (short) ms.reader().readUnsignedShort();
                    }

                    return;
                case -54:
                    i = ms.reader().readInt();
                    if (Char.getMyChar().charID == i) {
                        var32 = Char.getMyChar();
                    } else {
                        var32 = GameScr.getCharByID(i);
                    }

                    if (var32 == null) {
                        return;
                    }

                    var32.arrItemMounts = new Item[5];
                    var32.bp = var32.bo = var32.bq = false;

                    for (i = 0; i < var32.arrItemMounts.length; ++i) {
                        if ((var3 = ms.reader().readShort()) != -1) {
                            var32.arrItemMounts[i] = new Item();
                            var32.arrItemMounts[i].typeUI = 41;
                            var32.arrItemMounts[i].indexUI = i;
                            var32.arrItemMounts[i].template = ItemTemplateManager.get(var3);
                            var32.arrItemMounts[i].upgrade = ms.reader().readByte();
                            var32.arrItemMounts[i].expires = ms.reader().readLong();
                            var32.arrItemMounts[i].sys = ms.reader().readByte();
                            var32.arrItemMounts[i].isLock = true;
                            if (i == 4) {
                                if (var32.arrItemMounts[i].template.id != 485 && var32.arrItemMounts[i].template.id != 524) {
                                    if (var32.arrItemMounts[i].template.id == 443 || var32.arrItemMounts[i].template.id == 523) {
                                        var32.bp = true;
                                    }
                                } else {
                                    var32.bq = true;
                                }
                            }

                            var36 = ms.reader().readByte();
                            var32.arrItemMounts[i].options = new MyVector();

                            for (index = 0; index < var36; ++index) {
                                var32.arrItemMounts[i].options.addElement(new ItemOption(ms.reader().readUnsignedByte(), ms.reader().readInt()));
                            }
                        }
                    }

                    return;
                case 115:
                    System.out.println("UPDATE INFO ME");
                    GameScr.currentCharViewInfo = Char.getMyChar();
                    Char.getMyChar().gi = null;
                    Char.getMyChar().charID = ms.reader().readInt();
                    Char.getMyChar().cClanName = ms.reader().readUTF();
                    if (!Char.getMyChar().cClanName.equals("")) {
                        Char.getMyChar().ctypeClan = ms.reader().readByte();
                    }

                    Char.getMyChar().ctaskId = ms.reader().readByte();
                    Char.getMyChar().cgender = ms.reader().readByte();
                    Char.getMyChar().head = ms.reader().readShort();
                    Char.getMyChar().cspeed = ms.reader().readByte();
                    Char.getMyChar().charName = ms.reader().readUTF();
                    Char.getMyChar().hieuChien = ms.reader().readByte();
                    Char.getMyChar().cTypePk = ms.reader().readByte();
                    Char.getMyChar().cMaxHP = ms.reader().readInt();
                    Char.getMyChar().cHP = ms.reader().readInt();
                    Char.getMyChar().cMaxMP = ms.reader().readInt();
                    Char.getMyChar().cMP = ms.reader().readInt();
                    Char.getMyChar().cEXP = ms.reader().readLong();
                    Char.getMyChar().cExpDown = ms.reader().readLong();
                    GameScr.a(Char.getMyChar().cEXP);
                    Char.getMyChar().eff5BuffHp = ms.reader().readShort();
                    Char.getMyChar().eff5BuffMp = ms.reader().readShort();
                    Char.getMyChar().nClass = GameScr.nClass[ms.reader().readByte()];
                    Char.getMyChar().ai = ms.reader().readShort();
                    Char.getMyChar().potential[0] = ms.reader().readShort();
                    Char.getMyChar().potential[1] = ms.reader().readShort();
                    Char.getMyChar().potential[2] = ms.reader().readInt();
                    Char.getMyChar().potential[3] = ms.reader().readInt();
                    Char.getMyChar().aj = ms.reader().readShort();
                    Char.getMyChar().vSkill.removeAllElements();
                    Char.getMyChar().vSkillFight.removeAllElements();
                    i = ms.reader().readByte();

                    for (var3 = 0; var3 < i; var3 = (byte) (var3 + 1)) {
                        var2 = Skills.a(ms.reader().readShort());
                        if (Char.getMyChar().selectSkill == null) {
                            Char.getMyChar().selectSkill = var2;
                        }

                        if (Code.auto != null && Auto.selectSkill != null && var2.template.id == Auto.selectSkill.template.id) {
                            Auto.selectSkill = var2;
                        }

                        Char.getMyChar().vSkill.addElement(var2);
                        if ((var2.template.type == 1 || var2.template.type == 4 || var2.template.type == 2 || var2.template.type == 3) && (var2.template.maxPoint == 0 || var2.template.maxPoint > 0 && var2.point > 0)) {
                            if (var2.template.id == 0) {
                                Service.getInstance().selectSkill(0);
                            }

                            Char.getMyChar().vSkillFight.addElement(var2);
                        }
                    }

                    GameScr.y();
                    Char.getMyChar().xu = ms.reader().readInt();
                    Char.getMyChar().yen = ms.reader().readInt();
                    Char.getMyChar().luong = ms.reader().readInt();
                    Char.getMyChar().arrItemBag = new Item[ms.reader().readUnsignedByte()];
                    GameScr.q = 0;
                    GameScr.r = 0;

                    for (i = 0; i < Char.getMyChar().arrItemBag.length; ++i) {
                        if ((var30 = ms.reader().readShort()) != -1) {
                            Char.getMyChar().arrItemBag[i] = new Item();
                            Char.getMyChar().arrItemBag[i].typeUI = 3;
                            Char.getMyChar().arrItemBag[i].indexUI = i;
                            Char.getMyChar().arrItemBag[i].template = ItemTemplateManager.get((short) var30);
                            Char.getMyChar().arrItemBag[i].isLock = ms.reader().readBoolean();
                            if (Char.getMyChar().arrItemBag[i].isTypeBody() || Char.getMyChar().arrItemBag[i].isTypeMounts() || Char.getMyChar().arrItemBag[i].isTypeNgocKham()) {
                                Char.getMyChar().arrItemBag[i].upgrade = ms.reader().readByte();
                                Service.getInstance().requestItemInfo(3, i);
                            }

                            Char.getMyChar().arrItemBag[i].isExpires = ms.reader().readBoolean();
                            Char.getMyChar().arrItemBag[i].quantity = ms.reader().readUnsignedShort();
                            if (Char.getMyChar().arrItemBag[i].template.type == 16) {
                                GameScr.r += Char.getMyChar().arrItemBag[i].quantity;
                            }

                            if (Char.getMyChar().arrItemBag[i].template.type == 17) {
                                GameScr.q += Char.getMyChar().arrItemBag[i].quantity;
                            }

                            if (Char.getMyChar().arrItemBag[i].template.id == 340) {
                                var10000 = GameScr.getInstance();
                                var10000.numSprinLeft += Char.getMyChar().arrItemBag[i].quantity;
                            }
                        }
                    }

                    Code.j();
                    Char.getMyChar().arrItemBody = new Item[32];

                    try {
                        Char.getMyChar().n();

                        for (i = 0; i < 16; ++i) {
                            if ((var30 = ms.reader().readShort()) != -1) {
                                ItemTemplate var33;
                                var31 = (var33 = ItemTemplateManager.get((short) var30)).type;
                                Char.getMyChar().arrItemBody[var31] = new Item();
                                Char.getMyChar().arrItemBody[var31].indexUI = var31;
                                Char.getMyChar().arrItemBody[var31].typeUI = 5;
                                Char.getMyChar().arrItemBody[var31].template = var33;
                                Char.getMyChar().arrItemBody[var31].isLock = true;
                                Char.getMyChar().arrItemBody[var31].upgrade = ms.reader().readByte();
                                Char.getMyChar().arrItemBody[var31].sys = ms.reader().readByte();
                                if (var31 == 1) {
                                    Char.getMyChar().weapon = Char.getMyChar().arrItemBody[var31].template.part;
                                } else if (var31 == 2) {
                                    Char.getMyChar().body = Char.getMyChar().arrItemBody[var31].template.part;
                                } else if (var31 == 6) {
                                    Char.getMyChar().leg = Char.getMyChar().arrItemBody[var31].template.part;
                                }
                                Service.getInstance().requestItemInfo(5, i);
                            }
                        }
                    } catch (Exception var24) {
                        var24.printStackTrace();
                    }

                    Char.getMyChar().isHuman = ms.reader().readBoolean();
                    Char.getMyChar().isNhanban = ms.reader().readBoolean();
                    short[] var35;
                    if ((var35 = new short[]{ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort()})[0] >= 0) {
                        Char.getMyChar().head = var35[0];
                    }

                    if (var35[1] >= 0) {
                        Char.getMyChar().weapon = var35[1];
                    }

                    if (var35[2] >= 0) {
                        Char.getMyChar().body = var35[2];
                    }

                    if (var35[3] >= 0) {
                        Char.getMyChar().leg = var35[3];
                    }

                    try {
                        idFashion = new short[10];

                        try {
                            for (i = 0; i < 10; ++i) {
                                idFashion[i] = ms.reader().readShort();
                            }
                        } catch (Exception e) {
                            idFashion = null;
                        }
                        if (idFashion != null) {
                            Char.getMyChar().setFashion(idFashion);
                        }

                        for (i = 0; i < 16; ++i) {
                            if ((var30 = ms.reader().readShort()) != -1) {
                                ItemTemplate var33;
                                int indexUI = (var33 = ItemTemplateManager.get((short) var30)).type + 16;
                                Char.getMyChar().arrItemBody[indexUI] = new Item();
                                Char.getMyChar().arrItemBody[indexUI].indexUI = indexUI;
                                Char.getMyChar().arrItemBody[indexUI].typeUI = 5;
                                Char.getMyChar().arrItemBody[indexUI].template = var33;
                                Char.getMyChar().arrItemBody[indexUI].isLock = true;
                                Char.getMyChar().arrItemBody[indexUI].upgrade = ms.reader().readByte();
                                Char.getMyChar().arrItemBody[indexUI].sys = ms.reader().readByte();
                                Service.getInstance().requestItemInfo(5, indexUI);
                            }
                        }
                    } catch (Exception var24) {
                        var24.printStackTrace();
                    }

                    GameScr.getInstance();
                    GameScr.y();
                    if (Char.getMyChar().isHuman) {
                        GameScr.getInstance();
                        GameScr.e();
                    } else if (Char.getMyChar().isNhanban) {
                        GameScr.getInstance();
                        GameScr.f();
                    }

                    Char.getMyChar().statusMe = 4;
                    GameScr.cb = RMS.d(Char.getMyChar().charName + "vci") > 0;
                    break;
                default:
                    return;
            }
        } catch (Exception var28) {
            return;
        } finally {
            if (ms != null) {
                ms.cleanup();
            }

        }

    }

    private static boolean updateCharInfo(Char pl, Message ms) {
        try {
            pl.cClanName = ms.reader().readUTF();
            if (!pl.cClanName.equals("")) {
                pl.ctypeClan = ms.reader().readByte();
            }

            pl.df = ms.reader().readBoolean();
            pl.cTypePk = ms.reader().readByte();
            pl.nClass = GameScr.nClass[ms.reader().readByte()];
            pl.cgender = ms.reader().readByte();
            pl.head = ms.reader().readShort();
            pl.charName = ms.reader().readUTF();
            pl.cHP = ms.reader().readInt();
            pl.cMaxHP = ms.reader().readInt();
            pl.cLevel = ms.reader().readUnsignedByte();
            pl.weapon = ms.reader().readShort();
            pl.body = ms.reader().readShort();
            pl.leg = ms.reader().readShort();
            int var2 = GameMidlet.VERSION_250_PLUS ? ms.reader().readUnsignedShort() : ms.reader().readByte();
            if (pl.weapon == -1) {
                pl.weapon = 15;
            }

            if (pl.body == -1) {
                pl.p();
            }

            if (pl.leg == -1) {
                pl.q();
            }

            int var4;
            if (var2 <= 0) {
                pl.mobMe = null;
            } else {
                short var3 = (short) pl.cx;
                var4 = (short) (pl.cy - 40);
                pl.mobMe = new Mob((short) -1, false, false, false, false, false, var2, 1, 0, 0, 0, var3, (short) var4, (byte) 4, (byte) 0, false, false);
                pl.mobMe.h = 5;
            }

            pl.cx = pl.fh = ms.reader().readShort();
            pl.cy = pl.fi = ms.reader().readShort();
            pl.eff5BuffHp = ms.reader().readShort();
            pl.eff5BuffMp = ms.reader().readShort();
            byte var10 = ms.reader().readByte();

            for (var4 = 0; var4 < var10; ++var4) {
                Effect var7 = new Effect(ms.reader().readByte(), ms.reader().readInt(), ms.reader().readInt(), ms.reader().readShort());
                pl.vEff.addElement(var7);
                if (var7.e.b == 12 || var7.e.b == 11) {
                    pl.df = true;
                }
            }

            if (!pl.df) {
                ServerEffect.a(60, pl, 1);
            }

            if (pl.cHP == 0) {
                pl.statusMe = 14;
                if (Char.getMyChar().charID == pl.charID) {
                    GameScr.getInstance().resetButton();
                }
            }

            if (pl.charID == -Char.getMyChar().charID) {
                for (var4 = 0; var4 < GameScr.ah.size(); ++var4) {
                    Npc var8;
                    if ((var8 = (Npc) GameScr.ah.elementAt(var4)).template.name.equals(pl.charName)) {
                        var8.statusMe = 15;
                        var8.chatPopup = null;
                        break;
                    }
                }
            }

            pl.isHuman = ms.reader().readBoolean();
            pl.isNhanban = ms.reader().readBoolean();
            if (pl.isNhanban) {
                ServerEffect.a(141, pl.cx, pl.cy, 0);
            }

            short[] var9;
            if ((var9 = new short[]{ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort(), ms.reader().readShort()})[0] >= 0) {
                pl.head = var9[0];
            }

            if (var9[1] >= 0) {
                pl.weapon = var9[1];
            }

            if (var9[2] >= 0) {
                pl.body = var9[2];
            }

            if (var9[3] >= 0) {
                pl.leg = var9[3];
            }
            short[] var12 = new short[10];

            try {
                for (int var15 = 0; var15 < 10; ++var15) {
                    var12[var15] = ms.reader().readShort();
                }
            } catch (Exception e) {
                var12 = null;
            }

            if (var12 != null) {
                pl.setFashion(var12);
            }

            for (int var6 = 0; var6 < GameScr.vParty.size(); ++var6) {
                Party var11;
                if ((var11 = (Party) GameScr.vParty.elementAt(var6)).a == pl.charID) {
                    var11.f = pl;
                    break;
                }
            }

            return true;
        } catch (Exception var5) {
            return false;
        }
    }

    private static void h(Message var0) {
        try {
            byte var1;
            Item item;
            var1 = var0.reader().readByte();
            int var2 = var0.reader().readUnsignedByte();
            item = null;
            label186:
            switch (var1) {
                case 2:
                    item = GameScr.arrItemWeapon[var2];
                    break;
                case 3:
                    if ((item = Char.getMyChar().arrItemBag[var2]) != null) {
                        break;
                    }

                    if (GameScr.itemSplit != null && GameScr.itemSplit.indexUI == var2) {
                        item = GameScr.itemSplit;
                    }

                    if (GameScr.itemUpGrade != null && GameScr.itemUpGrade.indexUI == var2) {
                        item = GameScr.itemUpGrade;
                    }

                    if (GameScr.itemSell != null && GameScr.itemSell.indexUI == var2) {
                        item = GameScr.itemSell;
                    }

                    int var4;
                    if (item == null && GameScr.arrItemUpGrade != null) {
                        for (var4 = 0; var4 < GameScr.arrItemUpGrade.length; ++var4) {
                            if (GameScr.arrItemUpGrade[var4] != null && GameScr.arrItemUpGrade[var4].indexUI == var2) {
                                item = GameScr.arrItemUpGrade[var4];
                                break;
                            }
                        }
                    }

                    if (item == null && GameScr.arrItemConvert != null) {
                        for (var4 = 0; var4 < GameScr.arrItemConvert.length; ++var4) {
                            if (GameScr.arrItemConvert[var4] != null && GameScr.arrItemConvert[var4].indexUI == var2) {
                                item = GameScr.arrItemConvert[var4];
                                break;
                            }
                        }
                    }

                    if (item == null && GameScr.arrItemUpPeal != null) {
                        for (var4 = 0; var4 < GameScr.arrItemUpPeal.length; ++var4) {
                            if (GameScr.arrItemUpPeal[var4] != null && GameScr.arrItemUpPeal[var4].indexUI == var2) {
                                item = GameScr.arrItemUpPeal[var4];
                                break;
                            }
                        }
                    }

                    if (item == null && GameScr.arrItemTradeMe != null) {
                        for (var4 = 0; var4 < GameScr.arrItemTradeMe.length; ++var4) {
                            if (GameScr.arrItemTradeMe[var4] != null && GameScr.arrItemTradeMe[var4].indexUI == var2) {
                                item = GameScr.arrItemTradeMe[var4];
                                break;
                            }
                        }
                    }

                    if (item != null || GameScr.arrItemSplit == null) {
                        break;
                    }

                    var4 = 0;

                    while (true) {
                        if (var4 >= GameScr.arrItemSplit.length) {
                            break label186;
                        }

                        if (GameScr.arrItemSplit[var4] != null && GameScr.arrItemSplit[var4].indexUI == var2) {
                            item = GameScr.arrItemSplit[var4];
                            break label186;
                        }

                        ++var4;
                    }
                case 4:
                    item = Char.getMyChar().arrItemBox[var2];
                    break;
                case 5:
                    item = Char.getMyChar().arrItemBody[var2];
                    break;
                case 6:
                    item = GameScr.arrItemStack[var2];
                    break;
                case 7:
                    item = GameScr.arrItemStackLock[var2];
                    break;
                case 8:
                    item = GameScr.arrItemGrocery[var2];
                    break;
                case 9:
                    item = GameScr.arrItemGroceryLock[var2];
                case 10:
                case 11:
                case 12:
                case 13:
                case 31:
                case 33:
                case 36:
                case 37:
                case 38:
                default:
                    break;
                case 14:
                    item = GameScr.arrItemStore[var2];
                    break;
                case 15:
                    item = GameScr.arrItemBook[var2];
                    break;
                case 16:
                    item = GameScr.arrItemLien[var2];
                    break;
                case 17:
                    item = GameScr.arrItemNhan[var2];
                    break;
                case 18:
                    item = GameScr.arrItemNgocBoi[var2];
                    break;
                case 19:
                    item = GameScr.arrItemPhu[var2];
                    break;
                case 20:
                    item = GameScr.arrItemNonNam[var2];
                    break;
                case 21:
                    item = GameScr.arrItemNonNu[var2];
                    break;
                case 22:
                    item = GameScr.arrItemAoNam[var2];
                    break;
                case 23:
                    item = GameScr.arrItemAoNu[var2];
                    break;
                case 24:
                    item = GameScr.arrItemGangTayNam[var2];
                    break;
                case 25:
                    item = GameScr.arrItemGangTayNu[var2];
                    break;
                case 26:
                    item = GameScr.arrItemQuanNam[var2];
                    break;
                case 27:
                    item = GameScr.arrItemQuanNu[var2];
                    break;
                case 28:
                    item = GameScr.arrItemGiayNam[var2];
                    break;
                case 29:
                    item = GameScr.arrItemGiayNu[var2];
                    break;
                case 30:
                    item = GameScr.arrItemTradeOrder[var2];
                    break;
                case 32:
                    item = GameScr.arrItemFashion[var2];
                    break;
                case 34:
                    item = GameScr.arrItemClanShop[var2];
                    break;
                case 35:
                    item = GameScr.arrItemElites[var2];
                    break;
                case 39:
                    item = Char.clan.items[GameScr.indexSelect];
            }

            item.expires = var0.reader().readLong();
            if (item.isTypeUIMe()) {
                item.saleCoinLock = var0.reader().readInt();
            } else if (item.isTypeUIShop() || item.isTypeUIShopLock() || item.l() || item.m() || item.n() || item.o()) {
                item.buyCoin = var0.reader().readInt();
                item.buyCoinLock = var0.reader().readInt();
                item.buyGold = var0.reader().readInt();
            }

            if (!item.isTypeBody() && !item.isTypeMounts() && !item.isTypeNgocKham()) {
                if (item.template.id == 233) {
                    item.img = a(NinjaUtil.b(var0));
                } else if (item.template.id == 234) {
                    item.img = a(NinjaUtil.b(var0));
                } else if (item.template.id == 235) {
                    item.img = a(NinjaUtil.b(var0));
                }
            } else {
                item.sys = var0.reader().readByte();
                item.options = new MyVector();

                try {
                    while (true) {
                        item.options.addElement(new ItemOption(var0.reader().readUnsignedByte(), var0.reader().readInt()));
                    }
                } catch (Exception var5) {
                }
            }

            item.s = true;
            if (var1 == 5) {
                Char.getMyChar().z();
                return;
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            System.out.println("Controller.requestItemInfo()");
        }

        LockGame.t();
    }

    private static void i(Message var0) {
        try {
            byte var1 = var0.reader().readByte();

            for (byte var2 = 0; var2 < var1; ++var2) {
                short var3 = (short) (GameMidlet.VERSION_250_PLUS ? var0.reader().readUnsignedShort() : var0.reader().readUnsignedByte());
                boolean var4 = var0.reader().readBoolean();
                boolean var5 = var0.reader().readBoolean();
                boolean var6 = var0.reader().readBoolean();
                boolean var7 = var0.reader().readBoolean();
                boolean var8 = var0.reader().readBoolean();
                int var9 = var0.reader().readUnsignedByte();
                byte var10 = var0.reader().readByte();
                int var11 = var0.reader().readInt();
                int var12 = var0.reader().readUnsignedByte();
                int var13 = var0.reader().readInt();
                short var14 = var0.reader().readShort();
                short var15 = var0.reader().readShort();
                byte var16 = var0.reader().readByte();
                byte var17 = var0.reader().readByte();
                boolean var18 = var0.reader().readBoolean();
                GameScr.createMob(new Mob(var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, true), var2);
            }

        } catch (Exception var19) {
            var19.printStackTrace();
            System.out.println("err addMob");
        }
    }

    private static void j(Message var0) {
        try {
            short var1 = (short) var0.reader().readUnsignedByte();
            short var2 = var0.reader().readShort();
            short var3 = var0.reader().readShort();
            byte var4 = var0.reader().readByte();
            short var6 = var0.reader().readShort();
            EffectAuto.a(var1, var2, var3, var4, var6, 1);
        } catch (Exception var5) {
            var5.printStackTrace();
            System.out.println("err add effAuto");
        }
    }

    private static void k(Message var0) {
        try {
            short var1 = (short) var0.reader().readUnsignedByte();
            short var2 = var0.reader().readShort();
            byte[] var3 = null;
            if (var2 > 0) {
                var3 = new byte[var2];
                var0.reader().read(var3);
            }

            EffectAuto.a(var1, var3);
        } catch (Exception var4) {
            var4.printStackTrace();
            System.out.println("err add effAuto");
        }
    }

    private static void l(Message var0) {
        try {
            byte var1 = (byte) var0.reader().readUnsignedByte();
            byte[] var3 = NinjaUtil.a(var0);
            EffectAuto.a(var1, var3);
        } catch (Exception var2) {
            var2.printStackTrace();
            System.out.println("err getImgEffAuto");
        }
    }

    private static void m(Message var0) {
        try {
            boolean silentNgoc = AutoLuyenNgoc.isSilentMode();
            byte var1 = var0.reader().readByte();
            Char.getMyChar().luong = var0.reader().readInt();
            Char.getMyChar().xu = var0.reader().readInt();
            Char.getMyChar().yen = var0.reader().readInt();
            int var3;
            if (var1 == 0) {
                if (GameScr.itemSplit != null) {
                    GameScr.itemSplit = null;
                }

                if (GameScr.arrItemSplit != null) {
                    for (var3 = 0; var3 < GameScr.arrItemSplit.length; ++var3) {
                        GameScr.arrItemSplit[var3] = null;
                    }
                }
            } else if (var1 == 1) {
                if (GameScr.itemSplit != null) {
                    GameScr.itemSplit.isLock = true;
                    GameScr.itemSplit.upgrade = var0.reader().readByte();
                    GameScr.ez = GameScr.efs[53];
                    GameScr.ey = 0;
                }

                if (GameScr.arrItemSplit != null) {
                    for (var3 = 0; var3 < GameScr.arrItemSplit.length; ++var3) {
                        GameScr.arrItemSplit[var3] = null;
                    }
                }
            } else if ((var1 == 2 || var1 == 3) && GameScr.arrItemSplit != null) {
                for (var3 = 0; var3 < GameScr.arrItemSplit.length; ++var3) {
                    GameScr.arrItemSplit[var3] = null;
                }
            }

            if (!silentNgoc) {
                GameScr.getInstance().left = GameScr.getInstance().center = null;
                GameScr.getInstance().aa();
            }
            GameCanvas.setMaxTextLenght();
        } catch (Exception var2) {
            var2.printStackTrace();
            System.out.println("err getImgEffAuto");
        }
    }

    private static void n(Message var0) {
        try {
            Object var1;
            int var2;
            if (var0.reader().readByte() == 1) {
                var1 = Mob.a(var0.reader().readUnsignedByte());
            } else if ((var2 = var0.reader().readInt()) == Char.getMyChar().charID) {
                var1 = Char.getMyChar();
            } else {
                var1 = GameScr.getCharByID(var2);
            }

            if (var1 != null) {
                short var3 = var0.reader().readShort();
                int var4 = var0.reader().readInt();
                short var11 = var0.reader().readByte();
                long var6 = (long) var4 + System.currentTimeMillis();
                var2 = var11 * 1000;

                DataSkillEff var12;
                for (var4 = 0; var4 < ((MainObject) var1).a.size(); ++var4) {
                    if ((var12 = (DataSkillEff) ((MainObject) var1).a.elementAt(var4)) != null && var12.e == var3) {
                        var12.g = var6;
                        var12.h = var2;
                        return;
                    }
                }

                var12 = new DataSkillEff(var3, var6, var2);
                ((MainObject) var1).a.addElement(var12);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }
    }

    private static void o(Message var0) {
        try {
            short var1 = (short) var0.reader().readShort();
            byte[] var4 = NinjaUtil.a(var0);
            GameData.a(var1, var4);
            ImageIcon var2;
            if ((var2 = (ImageIcon) GameData.a.get(String.valueOf(var1))) == null) {
                var2 = new ImageIcon();
                GameData.a.put(String.valueOf(var1), var2);
            }

            var2.a = a(var4);
            if (GameMidlet.CLIENT_TYPE != 1) {
                RMS.writeRecord("ImgEffect " + var1, var4);
                return;
            }
        } catch (Exception var3) {
            var3.printStackTrace();
            System.out.println("err getImgEffect");
        }

    }

    private static void p(Message var0) {
        try {
            short var1 = (short) var0.reader().readShort();
            short var2 = var0.reader().readShort();
            byte[] var3 = null;
            if (var2 > 0) {
                var3 = new byte[var2];
                var0.reader().read(var3);
            }

            EffectData var5;
            if ((var5 = (EffectData) GameData.b.get(String.valueOf(var1))) != null) {
                var5.i = var3;
                if (!var5.h) {
                    DataSkillEff var6;
                    (var6 = new DataSkillEff()).a(var3);
                    var5.a = var6.a;
                    var5.b = var6.b;
                    var5.e = var6.j;
                    var5.f = var6.k;
                    var5.d = var6.d;
                    var5.g = var6.f;
                    var5.c = var6.c;
                    var5.h = true;
                }

                return;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            System.out.println("err getDataEffect ");
        }

    }
}
