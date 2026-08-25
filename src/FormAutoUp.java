
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStore;

public final class FormAutoUp implements CommandListener, javax.microedition.lcdui.ItemCommandListener {

    private static ByteArrayInputStream bytein;
    private static DataInputStream datain;
    private static ByteArrayOutputStream byteout;
    private static DataOutputStream dataout;
    private static RecordStore loadata;

    private final Form form = new Form("Auto Up");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);

    private final javax.microedition.lcdui.Command getMap = new javax.microedition.lcdui.Command("Get", javax.microedition.lcdui.Command.ITEM, 1);

    public static boolean batAutoUp = false;
    public static int mapUp = 134;
    public static int khuUp = -1;

    public static boolean dungTnp = false;
    public static boolean dungDan = false;

    public static boolean animgmap = false;
    public static boolean sieeuup = false;
    public static boolean hienYenUp = true;
    public static boolean restartTTGTAtMaintenance = false;

    private static boolean appliedSieeuUp = false;
    private static boolean lowGraphicBeforeSieeuUp = false;
    private static boolean fakeSkillNgangBeforeSieeuUp = false;
    private static int nSkillFakeBeforeSieeuUp = 100;
    private static boolean fakeSkillCaoBeforeSieeuUp = false;
    private static int cSkillFakeBeforeSieeuUp = 100;
    private static final long AUTO_START_DELAY = 10000L;
    private static final long TTGT_RESTART_DELAY = 10800000L;
    private static String autoStartCharName = "";
    private static long autoStartLoginAt = 0L;
    private static boolean autoStartDone = false;
    private static long nextTTGTRestartAt = 0L;

    private TextField mapTf;
    private TextField khuTf;
    private javax.microedition.lcdui.StringItem currentMapItem;
    private ChoiceGroup mapChoice;
    private ChoiceGroup caidat;

    public FormAutoUp() {
        this.currentMapItem = new javax.microedition.lcdui.StringItem("Map hien tai", "", javax.microedition.lcdui.StringItem.BUTTON);
        this.currentMapItem.setDefaultCommand(this.getMap);
        this.currentMapItem.setItemCommandListener(this);
        this.mapChoice = new ChoiceGroup("Chọn Map Up", 4, getMapChoiceLabels(), (Image[]) null);
        this.mapTf = new TextField("Map ID thủ công (để trống = chọn danh sách)", "", 4, 2);
        this.khuTf = new TextField("Khu Up (-1 = tất cả)", String.valueOf(khuUp), 4, TextField.ANY);

        this.caidat = new ChoiceGroup("Cài đặt", 2, new String[]{
            "Bật auto up sau login 10s",
            "Dùng Thiên Nhãn Phù",
            "Dùng đan",
            "Hiện thống kê up",
            "Login nhanh",
            "Tự bật lại Auto TTGT sau 3h"
        }, (Image[]) null);
    }

    public final void select() {
        this.form.deleteAll();

        this.selectCurrentMap();
        this.mapTf.setString("");
        this.updateCurrentMapItem();

        this.form.append(this.currentMapItem);
        this.form.append(this.mapChoice);
        this.form.append(this.mapTf);
        this.form.append(this.khuTf);
        this.form.append(this.caidat);

        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);

        this.caidat.setSelectedIndex(0, batAutoUp);
        this.caidat.setSelectedIndex(1, dungTnp);
        this.caidat.setSelectedIndex(2, dungDan);
        this.caidat.setSelectedIndex(3, hienYenUp);
        this.caidat.setSelectedIndex(4, SelectCharScr.isQuickLogin());
        this.caidat.setSelectedIndex(5, restartTTGTAtMaintenance);

        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public final void commandAction(javax.microedition.lcdui.Command c, Displayable d) {
        if (c == this.getMap) {
            this.getCurrentMap();
            return;
        }

        if (c == this.luu) {
            try {
                mapUp = this.getMapUpValue();
                khuUp = Integer.parseInt(this.khuTf.getString().trim());

                batAutoUp = this.caidat.isSelected(0);
                dungTnp = this.caidat.isSelected(1);
                dungDan = this.caidat.isSelected(2);
                animgmap = false;
                sieeuup = false;
                hienYenUp = this.caidat.isSelected(3);
                SelectCharScr.setQuickLogin(this.caidat.isSelected(4));
                restartTTGTAtMaintenance = this.caidat.isSelected(5);

                save();
                applySetting();
                applyMapTicketSetting();

                GameCanvas.setText("Lưu Auto Up thành công");
            } catch (Exception e) {
                GameCanvas.setText("Lỗi lưu Auto Up");
                return;
            }
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    public final void commandAction(javax.microedition.lcdui.Command c, javax.microedition.lcdui.Item item) {
        if (c == this.getMap) {
            this.getCurrentMap();
        }
    }

    private static String[] getMapChoiceLabels() {
        int count = 170;
        if (TileMap.mapNames != null && TileMap.mapNames.length > count) {
            count = TileMap.mapNames.length;
        }

        String[] labels = new String[count];

        for (int i = 0; i < labels.length; i++) {
            String name = null;
            if (TileMap.mapNames != null && i < TileMap.mapNames.length) {
                name = TileMap.mapNames[i];
            }

            if (name == null || name.length() == 0) {
                name = "Map " + i;
            }

            labels[i] = i + "." + name;
        }

        return labels;
    }

    private void selectCurrentMap() {
        try {
            this.selectMapIndex(mapUp);
        } catch (Exception e) {
        }
    }

    private void selectMapIndex(int index) {
        if (index < 0 || index >= this.mapChoice.size()) {
            index = 0;
        }

        this.mapChoice.setSelectedIndex(index, true);
    }

    private void getCurrentMap() {
        try {
            mapUp = TileMap.mapID;
            this.selectMapIndex(mapUp);
            this.mapTf.setString("");
            this.updateCurrentMapItem();
            GameCanvas.setText("Auto Up: get map " + mapUp);
        } catch (Exception e) {
        }
    }

    private void updateCurrentMapItem() {
        try {
            this.currentMapItem.setText("Get map dang dung: " + TileMap.mapID + " / khu " + TileMap.zoneID);
        } catch (Exception e) {
        }
    }

    private int getMapUpValue() throws Exception {
        String value = this.mapTf.getString().trim();
        if (value.length() > 0) {
            return Integer.parseInt(value);
        }

        return this.mapChoice.getSelectedIndex();
    }

    public static void applySetting() {
        try {
            if (dungTnp) {
                Code.addTuDung(538);
            } else {
                Code.removeTuDung(538);
            }

            applyDanTuDung();

            if (appliedSieeuUp) {
                Code.isFakeSkillNgang = fakeSkillNgangBeforeSieeuUp;
                Code.nSkillFake = nSkillFakeBeforeSieeuUp;
                Code.isFakeSkillCao = fakeSkillCaoBeforeSieeuUp;
                Code.cSkillFake = cSkillFakeBeforeSieeuUp;
                GameCanvas.lowGraphic = lowGraphicBeforeSieeuUp;
                appliedSieeuUp = false;
            }
        } catch (Exception e) {
        }
    }

    public static void clearVisualCache() {
        try {
            Effect2.vEffect2.removeAllElements();
            Effect2.vRemoveEffect2.removeAllElements();
            Effect2.vEffect2Outside.removeAllElements();
            Effect2.vAnimateEffect.removeAllElements();
            GameScr.w.removeAllElements();
            Mob.aa.removeAllElements();
        } catch (Exception e) {
        }
    }

    private static void applyMapTicketSetting() {
        try {
            boolean changed = false;

            if (TileMap.isLangCo(mapUp)) {
                if (!Char.tickAutoCoLenh) {
                    Char.tickAutoCoLenh = true;
                    changed = true;
                }

                if (!Char.tickAutoMuaCoLenh) {
                    Char.tickAutoMuaCoLenh = true;
                    changed = true;
                }
            }

            if (TileMap.isLangTT(mapUp)) {
                if (!Char.tickAutoLangThuyenThuyet) {
                    Char.tickAutoLangThuyenThuyet = true;
                    changed = true;
                }

                if (!Char.tickAutoMuaTruyenThuyetLenh) {
                    Char.tickAutoMuaTruyenThuyetLenh = true;
                    changed = true;
                }
            }

            if (changed) {
                Char.saveAuto();
            }
        } catch (Exception e) {
        }
    }

    private static void applyDanTuDung() {
        int[] ids = new int[]{275, 276, 277, 278};

        for (int i = 0; i < ids.length; i++) {
            if (dungDan) {
                Code.addTuDung(ids[i]);
            } else {
                Code.removeTuDung(ids[i]);
            }
        }
    }

    public static void startAutoUp() {
        try {
            applySetting();
            applyMapTicketSetting();

            GameScr.chatPopup("Auto Up: map " + mapUp + " khu " + khuUp);
            Code.autoUp.init(mapUp, khuUp);
            Code.setAuto((Auto) Code.autoUp);
            Code.instance.a();
        } catch (Exception e) {
        }
    }

    public static void updateAutoStartAfterLogin() {
        try {
            if (!batAutoUp) {
                resetAutoStartAfterLogin();
                return;
            }

            Char me = Char.getMyChar();
            if (me == null || me.charName == null || me.charName.length() == 0) {
                resetAutoStartAfterLogin();
                return;
            }

            if (!me.charName.equals(autoStartCharName)) {
                autoStartCharName = me.charName;
                autoStartLoginAt = System.currentTimeMillis();
                autoStartDone = false;
            }

            if (autoStartDone || System.currentTimeMillis() - autoStartLoginAt < AUTO_START_DELAY) {
                return;
            }

            autoStartDone = true;
            if (Code.auto == null) {
                startAutoUp();
            }
        } catch (Exception e) {
        }
    }

    public static void updateMaintenanceRestart() {
        try {
            if (!restartTTGTAtMaintenance) {
                nextTTGTRestartAt = 0L;
                return;
            }

            Char me = Char.getMyChar();
            if (me == null || me.charName == null || me.charName.length() == 0) {
                return;
            }

            long now = System.currentTimeMillis();

            if (Char.tickAutoTTGT) {
                nextTTGTRestartAt = 0L;
            } else if (nextTTGTRestartAt <= 0L) {
                nextTTGTRestartAt = now + TTGT_RESTART_DELAY;
                GameScr.chatPopup("TTGT: sẽ tự bật lại sau 3 giờ");
            } else if (now >= nextTTGTRestartAt) {
                if (Code.isInClan(me)) {
                    Code.setAutoTTGT(true);
                    nextTTGTRestartAt = 0L;
                    GameScr.chatPopup("TTGT: tự bật lại sau 3 giờ");
                } else {
                    nextTTGTRestartAt = now + TTGT_RESTART_DELAY;
                    GameScr.chatPopup("TTGT: không có gia tộc, thử lại sau 3 giờ");
                }
            }
        } catch (Exception e) {
        }
    }

    public static void resetAutoStartAfterLogin() {
        autoStartCharName = "";
        autoStartLoginAt = 0L;
        autoStartDone = false;
    }

    public static void save() {
        try {
            byteout = new ByteArrayOutputStream();
            dataout = new DataOutputStream(byteout);

            dataout.writeBoolean(batAutoUp);
            dataout.writeInt(mapUp);
            dataout.writeInt(khuUp);

            dataout.writeBoolean(dungTnp);
            dataout.writeBoolean(dungDan);
            dataout.writeBoolean(animgmap);
            dataout.writeBoolean(sieeuup);
            dataout.writeBoolean(hienYenUp);
            dataout.writeBoolean(false);
            dataout.writeBoolean(restartTTGTAtMaintenance);

            dataout.flush();
            dataout.close();

            loadata = RecordStore.openRecordStore("AutoUpCfg", true);
            byte[] data = byteout.toByteArray();

            if (loadata.getNumRecords() == 0) {
                loadata.addRecord(data, 0, data.length);
            } else {
                loadata.setRecord(1, data, 0, data.length);
            }

            loadata.closeRecordStore();
        } catch (Exception e) {
        } finally {
            closeStore();
            closeOutput();
        }
    }

    static {
        boolean rewriteLegacyVisualCfg = false;

        try {
            loadata = RecordStore.openRecordStore("AutoUpCfg", true);

            if (loadata.getNumRecords() != 0) {
                bytein = new ByteArrayInputStream(loadata.getRecord(1));
                datain = new DataInputStream(bytein);

                batAutoUp = datain.readBoolean();
                mapUp = datain.readInt();

                if (bytein.available() >= 4) {
                    khuUp = datain.readInt();
                }

                if (bytein.available() > 0) {
                    dungTnp = datain.readBoolean();
                }
                if (bytein.available() > 0) {
                    dungDan = datain.readBoolean();
                }
                if (bytein.available() > 0) {
                    animgmap = datain.readBoolean();
                    if (animgmap) {
                        rewriteLegacyVisualCfg = true;
                    }
                }
                if (bytein.available() > 0) {
                    sieeuup = datain.readBoolean();
                    if (sieeuup) {
                        rewriteLegacyVisualCfg = true;
                    }
                }
                if (bytein.available() > 0) {
                    hienYenUp = datain.readBoolean();
                }
                if (bytein.available() > 0) {
                    datain.readBoolean();
                }
                if (bytein.available() > 0) {
                    restartTTGTAtMaintenance = datain.readBoolean();
                }
                animgmap = false;
                sieeuup = false;

                datain.close();
                bytein.close();
            }

            loadata.closeRecordStore();
        } catch (Exception e) {
        } finally {
            closeInput();
            closeStore();
        }

        if (rewriteLegacyVisualCfg) {
            save();
        }

        applySetting();
    }

    private static void closeInput() {
        try {
            if (datain != null) {
                datain.close();
            }
        } catch (Exception e) {
        }

        try {
            if (bytein != null) {
                bytein.close();
            }
        } catch (Exception e) {
        }
    }

    private static void closeOutput() {
        try {
            if (dataout != null) {
                dataout.close();
            }
        } catch (Exception e) {
        }

        try {
            if (byteout != null) {
                byteout.close();
            }
        } catch (Exception e) {
        }
    }

    private static void closeStore() {
        try {
            if (loadata != null) {
                loadata.closeRecordStore();
            }
        } catch (Exception e) {
        }
    }
}
