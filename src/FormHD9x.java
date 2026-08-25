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

public final class FormHD9x implements CommandListener {

    public static final int MODE_FARM_CHEST = 0;
    public static final int MODE_BOSS = 1;
    private static final String STORE_NAME = "MenuHD9x";

    public static int CheDo = MODE_FARM_CHEST;
    public static boolean HenGio = false;
    public static boolean NhomTruong = false;
    public static int Gio = 0;
    public static int Phut = 0;
    public static String TenNhomTruong = "";
    public static String[] Acc = new String[]{"", "", "", "", ""};

    private final Form form = new Form("Auto Hang Động 9x");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);
    private final ChoiceGroup chedo;
    private final ChoiceGroup caidat;
    private final TextField gio;
    private final TextField phut;
    private final TextField tennhomtruong;
    private final TextField[] acc = new TextField[5];

    public FormHD9x() {
        this.chedo = new ChoiceGroup("Chế độ hang động 9x", 1, new String[]{
            "Farm rương - chỉ đánh mob",
            "Đánh boss - xong nhanh"
        }, (Image[]) null);
        this.caidat = new ChoiceGroup("Cài đặt", 2, new String[]{
            "Bật hẹn giờ",
            "Đây là nhóm trưởng"
        }, (Image[]) null);
        this.gio = new TextField("Giờ đi", String.valueOf(Gio), 2, 2);
        this.phut = new TextField("Phút đi", String.valueOf(Phut), 2, 2);
        this.tennhomtruong = new TextField("Tên nhóm trưởng", TenNhomTruong, 30, 0);

        for (int i = 0; i < this.acc.length; i++) {
            this.acc[i] = new TextField("Acc " + (i + 1), Acc[i], 30, 0);
        }
    }

    public final void select() {
        this.form.deleteAll();
        this.chedo.setSelectedIndex(CheDo == MODE_BOSS ? MODE_BOSS : MODE_FARM_CHEST, true);
        this.caidat.setSelectedIndex(0, HenGio);
        this.caidat.setSelectedIndex(1, NhomTruong);
        this.form.append(this.chedo);
        this.form.append(this.caidat);
        this.form.append(this.gio);
        this.form.append(this.phut);
        this.form.append(this.tennhomtruong);

        for (int i = 0; i < this.acc.length; i++) {
            this.form.append(this.acc[i]);
        }

        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);
        show(this.form);
    }

    public final void commandAction(javax.microedition.lcdui.Command command, Displayable displayable) {
        if (command == this.luu) {
            try {
                CheDo = this.chedo.getSelectedIndex();
                HenGio = this.caidat.isSelected(0);
                NhomTruong = this.caidat.isSelected(1);
                Gio = Integer.parseInt(this.gio.getString().trim());
                Phut = Integer.parseInt(this.phut.getString().trim());
                TenNhomTruong = clean(this.tennhomtruong.getString());

                if (!isValidTime(Gio, Phut)) {
                    GameCanvas.setText("Giờ 0-23, phút 0-59");
                    return;
                }

                for (int i = 0; i < Acc.length; i++) {
                    Acc[i] = clean(this.acc[i].getString());
                }

                save();
                GameCanvas.setText("Lưu auto hang động 9x thành công");
            } catch (Exception e) {
                GameCanvas.setText("Lỗi lưu auto hang động 9x");
                return;
            }
        }

        show(MotherCanvas.getInstance());
    }

    public static boolean isFarmChest() {
        return CheDo == MODE_FARM_CHEST;
    }

    public static boolean isLeader() {
        return NhomTruong;
    }

    public static String getModeName() {
        return isFarmChest() ? "Farm rương" : "Đánh boss";
    }

    public static String getLeaderName() {
        if (NhomTruong && Char.getMyChar() != null) {
            return Char.getMyChar().charName;
        }

        return TenNhomTruong == null ? "" : TenNhomTruong.trim();
    }

    public static boolean isConfiguredMember(String name) {
        if (name == null) {
            return false;
        }

        for (int i = 0; i < Acc.length; i++) {
            if (name.equalsIgnoreCase(Acc[i])) {
                return true;
            }
        }

        return false;
    }

    public static int countMembers() {
        int count = 0;

        for (int i = 0; i < Acc.length; i++) {
            if (Acc[i] != null && Acc[i].trim().length() > 0) {
                count++;
            }
        }

        return count;
    }

    private static boolean isValidTime(int hour, int minute) {
        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }

    private static String clean(String value) {
        return value == null ? "" : value.trim();
    }

    private static void show(Displayable current) {
        Display.getDisplay(GameMidlet.instance).setCurrent(current);
    }

    private static void save() {
        RecordStore store = null;

        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeInt(CheDo);
            dataout.writeBoolean(HenGio);
            dataout.writeBoolean(NhomTruong);
            dataout.writeInt(Gio);
            dataout.writeInt(Phut);
            dataout.writeUTF(TenNhomTruong == null ? "" : TenNhomTruong);

            for (int i = 0; i < Acc.length; i++) {
                dataout.writeUTF(Acc[i] == null ? "" : Acc[i]);
            }

            dataout.flush();
            byte[] data = byteout.toByteArray();
            dataout.close();
            byteout.close();
            store = RecordStore.openRecordStore(STORE_NAME, true);

            if (store.getNumRecords() == 0) {
                store.addRecord(data, 0, data.length);
            } else {
                store.setRecord(1, data, 0, data.length);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (store != null) {
                    store.closeRecordStore();
                }
            } catch (Exception e) {
            }
        }
    }

    static {
        RecordStore store = null;

        try {
            store = RecordStore.openRecordStore(STORE_NAME, true);

            if (store.getNumRecords() > 0) {
                ByteArrayInputStream bytein = new ByteArrayInputStream(store.getRecord(1));
                DataInputStream datain = new DataInputStream(bytein);
                CheDo = datain.readInt();
                HenGio = datain.readBoolean();
                NhomTruong = datain.readBoolean();
                Gio = datain.readInt();
                Phut = datain.readInt();
                TenNhomTruong = clean(datain.readUTF());

                for (int i = 0; i < Acc.length; i++) {
                    Acc[i] = clean(datain.readUTF());
                }

                datain.close();
                bytein.close();
            }
        } catch (Exception e) {
        } finally {
            try {
                if (store != null) {
                    store.closeRecordStore();
                }
            } catch (Exception e) {
            }
        }
    }
}
