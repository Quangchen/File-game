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

public final class FormAutoTinhLuyen implements CommandListener {

    private static final String STORE_NAME = "AutoTinhLuyenCfg";

    public static final int MODE_BAG_INDEX0 = 0;
    public static final int MODE_WEAPON = 1;
    public static final int MODE_ADORN = 2;
    public static final int MODE_CLOTHE = 3;
    public static final int MODE_ALL_BODY = 4;

    public static final int ACTION_CONVERT_REFINE = 0;
    public static final int ACTION_CONVERT_ONLY = 1;
    public static final int ACTION_REFINE_ONLY = 2;

    public static int Mode = MODE_BAG_INDEX0;
    public static int ActionMode = ACTION_CONVERT_REFINE;
    public static int TargetLevel = 9;
    public static int MaxAttempts = 0;
    public static int DelayMs = 3000;
    public static boolean AutoCombineStone = true;
    public static boolean UseCatalystStone = false;
    public static boolean ReEquipWhenDone = true;
    public static int SlotMask = 0;

    private final Form form = new Form("Auto Tinh Luyện");
    private final Command save = new Command("Lưu", Command.OK, 1);
    private final Command start = new Command("Bắt đầu", Command.OK, 2);
    private final Command stop = new Command("Dừng", Command.OK, 3);
    private final Command cancel = new Command("Hủy", Command.BACK, 1);
    private static final String[] SLOT_LABELS = new String[]{
        "Vũ khí",
        "Bội",
        "Quần",
        "Bùa",
        "Giày",
        "Yoroi",
        "Nón",
        "Áo",
        "Găng",
        "Liên",
        "Nhẫn",
        "Trang bị thú"
    };
    private static final int[] SLOT_TYPES = new int[]{1, 7, 6, 9, 8, 12, 0, 2, 4, 3, 5, -1};
    private final ChoiceGroup modeChoice;
    private final ChoiceGroup slotChoice;
    private final ChoiceGroup actionChoice;
    private final ChoiceGroup optionChoice;
    private final TextField targetLevel;
    private final TextField maxAttempts;
    private final TextField delayMs;

    public FormAutoTinhLuyen() {
        this.modeChoice = new ChoiceGroup("Loại đồ", ChoiceGroup.EXCLUSIVE, new String[]{
            "Hành trang index 0",
            "Vũ khí",
            "Trang sức",
            "Trang phục",
            "Tất cả đang mặc"
        }, (Image[]) null);
        this.slotChoice = new ChoiceGroup("Chọn món cần làm (bỏ trống = theo Loại đồ)", ChoiceGroup.MULTIPLE, SLOT_LABELS, (Image[]) null);
        this.actionChoice = new ChoiceGroup("Chế độ", ChoiceGroup.EXCLUSIVE, new String[]{
            "Dịch chuyển + tinh luyện",
            "Chỉ dịch chuyển",
            "Chỉ tinh luyện"
        }, (Image[]) null);
        this.optionChoice = new ChoiceGroup("Cài đặt", ChoiceGroup.MULTIPLE, new String[]{
            "Tự ghép thạch 455/456/457",
            "Dùng đá 10/11 khi thiếu thạch",
            "Xong mặc lại đồ"
        }, (Image[]) null);
        this.targetLevel = new TextField("Tinh luyện đến cấp (1-9)", String.valueOf(TargetLevel), 2, TextField.NUMERIC);
        this.maxAttempts = new TextField("Số lần tối đa (0 = vô hạn)", String.valueOf(MaxAttempts), 5, TextField.NUMERIC);
        this.delayMs = new TextField("Delay ms", String.valueOf(DelayMs), 6, TextField.NUMERIC);
    }

    public void select() {
        load();
        this.form.deleteAll();
        this.modeChoice.setSelectedIndex(Mode, true);
        for (int i = 0; i < SLOT_LABELS.length; ++i) {
            this.slotChoice.setSelectedIndex(i, isSlotChoiceSelected(i));
        }
        this.actionChoice.setSelectedIndex(ActionMode, true);
        this.optionChoice.setSelectedIndex(0, AutoCombineStone);
        this.optionChoice.setSelectedIndex(1, UseCatalystStone);
        this.optionChoice.setSelectedIndex(2, ReEquipWhenDone);
        this.targetLevel.setString(String.valueOf(TargetLevel));
        this.maxAttempts.setString(String.valueOf(MaxAttempts));
        this.delayMs.setString(String.valueOf(DelayMs));

        this.form.append("Trạng thái: " + AutoTinhLuyen.getStatusText() + "\n");
        this.form.append("Dịch chuyển cần đồ +12 và 20 item 454.\n");
        this.form.append("Tinh luyện dùng 455/456/457 theo cấp, chạy ngầm không cần mở UI.\n");
        this.form.append(this.modeChoice);
        this.form.append(this.slotChoice);
        this.form.append(this.actionChoice);
        this.form.append(this.optionChoice);
        this.form.append(this.targetLevel);
        this.form.append(this.maxAttempts);
        this.form.append(this.delayMs);
        this.form.addCommand(this.save);
        this.form.addCommand(this.start);
        this.form.addCommand(this.stop);
        this.form.addCommand(this.cancel);
        this.form.setCommandListener(this);
        show(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.save || command == this.start) {
            if (!readAndSave()) {
                return;
            }
        }

        if (command == this.start) {
            AutoTinhLuyen.start();
        } else if (command == this.stop) {
            AutoTinhLuyen.stop();
        }

        show(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            Mode = this.modeChoice.getSelectedIndex();
            SlotMask = 0;
            for (int i = 0; i < SLOT_LABELS.length; ++i) {
                if (this.slotChoice.isSelected(i)) {
                    SlotMask |= 1 << i;
                }
            }
            ActionMode = this.actionChoice.getSelectedIndex();
            TargetLevel = Integer.parseInt(this.targetLevel.getString().trim());
            MaxAttempts = Integer.parseInt(this.maxAttempts.getString().trim());
            DelayMs = Integer.parseInt(this.delayMs.getString().trim());
            AutoCombineStone = this.optionChoice.isSelected(0);
            UseCatalystStone = this.optionChoice.isSelected(1);
            ReEquipWhenDone = this.optionChoice.isSelected(2);

            if (TargetLevel < 1) {
                TargetLevel = 1;
            }
            if (TargetLevel > 9) {
                TargetLevel = 9;
            }
            if (MaxAttempts < 0) {
                MaxAttempts = 0;
            }
            if (DelayMs < 500) {
                DelayMs = 500;
            }
            save();
            GameCanvas.setText("Đã lưu Auto Tinh Luyện");
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Lỗi dữ liệu Auto Tinh Luyện");
            return false;
        }
    }

    public static String getModeName() {
        if (hasSelectedSlots()) {
            return getSelectedSlotSummary();
        }
        switch (Mode) {
            case MODE_WEAPON:
                return "Vũ khí";
            case MODE_ADORN:
                return "Trang sức";
            case MODE_CLOTHE:
                return "Trang phục";
            case MODE_ALL_BODY:
                return "Tất cả đang mặc";
            default:
                return "Index 0";
        }
    }

    public static boolean hasSelectedSlots() {
        return SlotMask != 0;
    }

    public static int[] getSelectedTypes() {
        if (!hasSelectedSlots()) {
            return new int[0];
        }

        int count = 0;
        for (int i = 0; i < SLOT_TYPES.length; ++i) {
            if (isSlotChoiceSelected(i)) {
                count += SLOT_TYPES[i] == -1 ? 5 : 1;
            }
        }

        int[] types = new int[count];
        int pos = 0;
        for (int i = 0; i < SLOT_TYPES.length; ++i) {
            if (!isSlotChoiceSelected(i)) {
                continue;
            }

            if (SLOT_TYPES[i] == -1) {
                for (int type = 29; type <= 33; ++type) {
                    types[pos++] = type;
                }
            } else {
                types[pos++] = SLOT_TYPES[i];
            }
        }

        return types;
    }

    public static boolean isSelectedType(int type) {
        if (!hasSelectedSlots()) {
            return true;
        }

        for (int i = 0; i < SLOT_TYPES.length; ++i) {
            if (!isSlotChoiceSelected(i)) {
                continue;
            }

            if (SLOT_TYPES[i] == -1) {
                if (type >= 29 && type <= 33) {
                    return true;
                }
            } else if (SLOT_TYPES[i] == type) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSlotChoiceSelected(int index) {
        return index >= 0 && index < SLOT_LABELS.length && (SlotMask & (1 << index)) != 0;
    }

    private static String getSelectedSlotSummary() {
        String text = "";
        int count = 0;
        for (int i = 0; i < SLOT_LABELS.length; ++i) {
            if (!isSlotChoiceSelected(i)) {
                continue;
            }
            if (count > 0) {
                text += ",";
            }
            text += SLOT_LABELS[i];
            ++count;
            if (count >= 3) {
                break;
            }
        }
        if (count == 0) {
            return "Theo loại đồ";
        }
        if (countSelectedSlots() > count) {
            text += "...";
        }
        return text;
    }

    private static int countSelectedSlots() {
        int count = 0;
        for (int i = 0; i < SLOT_LABELS.length; ++i) {
            if (isSlotChoiceSelected(i)) {
                ++count;
            }
        }
        return count;
    }

    private static void show(Displayable current) {
        Display.getDisplay(GameMidlet.instance).setCurrent(current);
    }

    public static void save() {
        RecordStore rs = null;
        ByteArrayOutputStream byteout = null;
        DataOutputStream dataout = null;
        try {
            byteout = new ByteArrayOutputStream();
            dataout = new DataOutputStream(byteout);
            dataout.writeInt(Mode);
            dataout.writeInt(ActionMode);
            dataout.writeInt(TargetLevel);
            dataout.writeInt(MaxAttempts);
            dataout.writeInt(DelayMs);
            dataout.writeBoolean(AutoCombineStone);
            dataout.writeBoolean(UseCatalystStone);
            dataout.writeBoolean(ReEquipWhenDone);
            dataout.writeInt(SlotMask);
            dataout.flush();
            byte[] data = byteout.toByteArray();
            rs = RecordStore.openRecordStore(STORE_NAME, true);
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

    public static void load() {
        RecordStore rs = null;
        ByteArrayInputStream bytein = null;
        DataInputStream datain = null;
        try {
            SlotMask = 0;
            rs = RecordStore.openRecordStore(STORE_NAME, true);
            if (rs.getNumRecords() == 0) {
                return;
            }
            byte[] data = rs.getRecord(1);
            bytein = new ByteArrayInputStream(data);
            datain = new DataInputStream(bytein);
            Mode = datain.readInt();
            ActionMode = datain.readInt();
            TargetLevel = datain.readInt();
            MaxAttempts = datain.readInt();
            DelayMs = datain.readInt();
            AutoCombineStone = datain.readBoolean();
            UseCatalystStone = datain.readBoolean();
            ReEquipWhenDone = datain.readBoolean();
            if (bytein.available() >= 4) {
                SlotMask = datain.readInt();
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
