import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStore;

public final class FormEventTrade implements CommandListener {

    private static final String RMS_NAME = "MenuGomSK";
    public static final int ROLE_MAIN = 0;
    public static final int ROLE_CLONE = 1;

    public static boolean Bat = false;
    public static int VaiTro = ROLE_MAIN;
    public static String TenAccChinh = "";
    public static String ListClone = "";
    public static int Delay = 1500;
    public static int Retry = 3;

    private final Form form = new Form("Gom Sự Kiện");
    private final Command luu = new Command("Lưu", Command.OK, 1);
    private final Command call = new Command("Gọi gia tộc", Command.OK, 2);
    private final Command start = new Command("Bắt đầu", Command.OK, 3);
    private final Command huy = new Command("Hủy", Command.BACK, 1);

    private final ChoiceGroup bat;
    private final ChoiceGroup vaitro;
    private final TextField mainName;
    private final TextField cloneNames;
    private final TextField delay;
    private final TextField retry;

    public FormEventTrade() {
        String defaultMain = TenAccChinh == null || TenAccChinh.length() == 0
                ? (Char.getMyChar() != null ? Char.getMyChar().charName : "")
                : TenAccChinh;
        this.bat = new ChoiceGroup("Auto gom sự kiện", ChoiceGroup.MULTIPLE, new String[]{"Bật"}, (Image[]) null);
        this.vaitro = new ChoiceGroup("Vai trò", ChoiceGroup.EXCLUSIVE, new String[]{"Acc chính", "Acc clone"}, (Image[]) null);
        this.mainName = new TextField("Tên acc chính", defaultMain, 64, TextField.ANY);
        this.cloneNames = new TextField("List clone (acc1,acc2)", ListClone == null ? "" : ListClone, 512, TextField.ANY);
        this.delay = new TextField("Delay mỗi clone(ms)", String.valueOf(Delay), 8, TextField.NUMERIC);
        this.retry = new TextField("Retry mỗi clone", String.valueOf(Retry), 3, TextField.NUMERIC);
    }

    public void select() {
        this.form.deleteAll();
        this.form.append(this.bat);
        this.form.append(this.vaitro);
        this.form.append(this.mainName);
        this.form.append(this.cloneNames);
        this.form.append(this.delay);
        this.form.append(this.retry);
        this.form.append("Item gom: " + AutoReceiver.getGatherItemsCsv());
        this.form.addCommand(this.luu);
        this.form.addCommand(this.call);
        this.form.addCommand(this.start);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);

        this.bat.setSelectedIndex(0, Bat);
        this.vaitro.setSelectedIndex(VaiTro == ROLE_CLONE ? ROLE_CLONE : ROLE_MAIN, true);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.luu || command == this.call || command == this.start) {
            saveFromForm();
        }

        if (command == this.call) {
            AutoEventTrade.callClanFromForm();
        } else if (command == this.start) {
            AutoEventTrade.startMain();
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private void saveFromForm() {
        try {
            Bat = this.bat.isSelected(0);
            VaiTro = this.vaitro.getSelectedIndex();
            TenAccChinh = this.mainName.getString().trim();
            ListClone = this.cloneNames.getString().trim();
            Delay = Integer.parseInt(this.delay.getString().trim());
            Retry = Integer.parseInt(this.retry.getString().trim());
            if (Delay < 500) {
                Delay = 500;
            }
            if (Retry < 1) {
                Retry = 1;
            }
            save();
            GameCanvas.setText("Đã lưu Gom Sự Kiện");
        } catch (Exception e) {
            GameCanvas.setText("Lỗi dữ liệu Gom Sự Kiện");
        }
    }

    public static boolean isMain() {
        return VaiTro == ROLE_MAIN;
    }

    public static boolean isClone() {
        return VaiTro == ROLE_CLONE;
    }

    public static String[] getCloneArray() {
        if (ListClone == null || ListClone.trim().length() == 0) {
            return new String[0];
        }
        return Code.splitString(ListClone, ",");
    }

    public static boolean containsClone(String name) {
        if (name == null) {
            return false;
        }
        String[] arr = getCloneArray();
        for (int i = 0; i < arr.length; ++i) {
            if (name.equalsIgnoreCase(arr[i].trim())) {
                return true;
            }
        }
        return false;
    }

    public static void save() {
        ByteArrayOutputStream byteout = null;
        DataOutputStream dataout = null;
        RecordStore rs = null;
        try {
            byteout = new ByteArrayOutputStream();
            dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(Bat);
            dataout.writeInt(VaiTro);
            dataout.writeUTF(TenAccChinh == null ? "" : TenAccChinh);
            dataout.writeUTF(ListClone == null ? "" : ListClone);
            dataout.writeInt(Delay);
            dataout.writeInt(Retry);
            dataout.flush();
            rs = RecordStore.openRecordStore(RMS_NAME, true);
            byte[] data = byteout.toByteArray();
            if (rs.getNumRecords() == 0) {
                rs.addRecord(data, 0, data.length);
            } else {
                rs.setRecord(1, data, 0, data.length);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (dataout != null) {
                    dataout.close();
                }
                if (byteout != null) {
                    byteout.close();
                }
                if (rs != null) {
                    rs.closeRecordStore();
                }
            } catch (Exception e) {
            }
        }
    }

    static {
        ByteArrayInputStream bytein = null;
        DataInputStream datain = null;
        RecordStore rs = null;
        try {
            rs = RecordStore.openRecordStore(RMS_NAME, true);
            if (rs.getNumRecords() > 0) {
                bytein = new ByteArrayInputStream(rs.getRecord(1));
                datain = new DataInputStream(bytein);
                Bat = datain.readBoolean();
                VaiTro = datain.readInt();
                TenAccChinh = datain.readUTF();
                ListClone = datain.readUTF();
                Delay = datain.readInt();
                Retry = datain.readInt();
            }
        } catch (Exception e) {
        } finally {
            try {
                if (datain != null) {
                    datain.close();
                }
                if (bytein != null) {
                    bytein.close();
                }
                if (rs != null) {
                    rs.closeRecordStore();
                }
            } catch (Exception e) {
            }
        }
    }
}
