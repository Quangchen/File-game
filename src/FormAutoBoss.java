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

public final class FormAutoBoss implements CommandListener {

    private static final String STORE_NAME = "MenuAutoBoss";

    public static boolean LangCo = false;
    public static boolean LTT = false;
    public static boolean VDMQ = false;
    public static String MapFilter = "";

    private final Form form = new Form("Auto Boss");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);
    private final ChoiceGroup boss;
    private final TextField mapFilter;

    public FormAutoBoss() {
        this.boss = new ChoiceGroup("Chọn khu vực boss", 2, new String[]{
            "Làng Cổ",
            "Làng Truyền Thuyết",
            "Vùng Đất Ma Quỷ"
        }, (Image[]) null);
        this.mapFilter = new TextField("Map muốn săn", MapFilter, 64, TextField.ANY);
    }

    public final void select() {
        this.form.deleteAll();
        this.boss.setSelectedIndex(0, LangCo);
        this.boss.setSelectedIndex(1, LTT);
        this.boss.setSelectedIndex(2, VDMQ);
        this.mapFilter.setString(MapFilter);
        this.form.append(this.boss);
        this.form.append(this.mapFilter);
        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);
        show(this.form);
    }

    public final void commandAction(javax.microedition.lcdui.Command command, Displayable displayable) {
        if (command == this.luu) {
            LangCo = this.boss.isSelected(0);
            LTT = this.boss.isSelected(1);
            VDMQ = this.boss.isSelected(2);
            MapFilter = normalizeMapFilter(this.mapFilter.getString());
            save();
            GameCanvas.setText("Lưu auto boss thành công");
        }

        show(MotherCanvas.getInstance());
    }

    public static boolean hasSelectedRegion() {
        return LangCo || LTT || VDMQ;
    }

    public static boolean isMapAllowed(int map) {
        String value = normalizeMapFilter(MapFilter);
        if (value.length() == 0) {
            return true;
        }

        String[] parts = Code.splitString(value, ",");
        for (int i = 0; i < parts.length; i++) {
            try {
                if (Integer.parseInt(parts[i]) == map) {
                    return true;
                }
            } catch (Exception e) {
            }
        }

        return false;
    }

    private static String normalizeMapFilter(String value) {
        if (value == null) {
            return "";
        }

        StringBuffer result = new StringBuffer();
        boolean hasDigit = false;
        boolean needComma = false;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= '0' && c <= '9') {
                if (needComma && result.length() > 0) {
                    result.append(',');
                    needComma = false;
                }
                result.append(c);
                hasDigit = true;
            } else if (hasDigit) {
                needComma = true;
                hasDigit = false;
            }
        }

        return result.toString();
    }

    private static void show(Displayable current) {
        Display.getDisplay(GameMidlet.instance).setCurrent(current);
    }

    private static void save() {
        RecordStore store = null;

        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(LangCo);
            dataout.writeBoolean(LTT);
            dataout.writeBoolean(VDMQ);
            dataout.writeUTF(MapFilter == null ? "" : MapFilter);
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
                LangCo = datain.readBoolean();
                LTT = datain.readBoolean();
                VDMQ = datain.readBoolean();
                if (datain.available() > 0) {
                    MapFilter = normalizeMapFilter(datain.readUTF());
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
