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

public final class FormAutoDapDo implements CommandListener {

    private static final String STORE_NAME = "AutoDapDoCfg";

    public static final int MODE_BAG_INDEX0 = 0;
    public static final int MODE_WEAPON = 1;
    public static final int MODE_ADORN = 2;
    public static final int MODE_CLOTHE = 3;

    public static final int MASK_BAG_INDEX0 = 1;
    public static final int MASK_WEAPON = 2;
    public static final int MASK_ADORN = 4;
    public static final int MASK_CLOTHE = 8;

    public static int Mode = MODE_BAG_INDEX0;
    public static int ModeMask = MASK_BAG_INDEX0;
    public static int TargetUpgrade = 16;
    public static int TargetPercent = 0;
    public static int MinCrystalId = 8;
    public static int FlipCount = 20;
    public static int DelayMs = 1200;
    public static boolean Careful = false;
    public static boolean AutoFlip = true;
    public static boolean UseProtectUnder14 = true;
    public static boolean AutoBuyProtect475 = true;
    public static boolean ReEquipWhenDone = true;
    public static boolean UseXuWhenLackYen = false;

    private final Form form = new Form("Auto đập đồ");
    private final Command save = new Command("Lưu", Command.OK, 1);
    private final Command start = new Command("Bắt đầu", Command.OK, 2);
    private final Command stop = new Command("Dừng", Command.OK, 3);
    private final Command cancel = new Command("Hủy", Command.BACK, 1);
    private final ChoiceGroup modeChoice;
    private final ChoiceGroup optionChoice;
    private final TextField targetUpgrade;
    private final TextField delayMs;

    public FormAutoDapDo() {
        this.modeChoice = new ChoiceGroup("Loại Đập", ChoiceGroup.MULTIPLE, new String[]{
            "Hành Trang index 0",
            "Vũ Khí",
            "Trang Sức",
            "Trang phục"
        }, (Image[]) null);
        this.optionChoice = new ChoiceGroup("Cài đặt", ChoiceGroup.MULTIPLE, new String[]{
            "Đập cẩn thận",
            "Lật hình khi thiếu đá",
            "Dùng BH",
            "Tự Mua BH",
            "Đập xong mặc lại",
            "Đập yên+xu"
        }, (Image[]) null);
        this.targetUpgrade = new TextField("Đập đến +", String.valueOf(TargetUpgrade), 2, TextField.NUMERIC);
        this.delayMs = new TextField("Delay ms", String.valueOf(DelayMs), 5, TextField.NUMERIC);
    }

    public void select() {
        this.form.deleteAll();
        sanitizeModeMask();
        this.modeChoice.setSelectedIndex(0, isBagIndex0Mode());
        this.modeChoice.setSelectedIndex(1, isWeaponMode());
        this.modeChoice.setSelectedIndex(2, isAdornMode());
        this.modeChoice.setSelectedIndex(3, isClotheMode());
        this.optionChoice.setSelectedIndex(0, Careful);
        this.optionChoice.setSelectedIndex(1, AutoFlip);
        this.optionChoice.setSelectedIndex(2, UseProtectUnder14);
        this.optionChoice.setSelectedIndex(3, AutoBuyProtect475);
        this.optionChoice.setSelectedIndex(4, ReEquipWhenDone);
        this.optionChoice.setSelectedIndex(5, UseXuWhenLackYen);
        this.targetUpgrade.setString(String.valueOf(TargetUpgrade));
        this.delayMs.setString(String.valueOf(DelayMs));

        this.form.append("Trạng thái: " + AutoDapDo.getStatusText() + "\n");
        this.form.append(this.modeChoice);
        this.form.append(this.optionChoice);
        this.form.append(this.targetUpgrade);
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
            AutoDapDo.start();
        } else if (command == this.stop) {
            AutoDapDo.stop();
        }

        show(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            int modeMask = 0;
            if (this.modeChoice.isSelected(0)) {
                modeMask |= MASK_BAG_INDEX0;
            }
            if (this.modeChoice.isSelected(1)) {
                modeMask |= MASK_WEAPON;
            }
            if (this.modeChoice.isSelected(2)) {
                modeMask |= MASK_ADORN;
            }
            if (this.modeChoice.isSelected(3)) {
                modeMask |= MASK_CLOTHE;
            }
            if (modeMask == 0) {
                GameCanvas.setText("Chọn ít nhất 1 loại đập");
                return false;
            }
            if ((modeMask & MASK_BAG_INDEX0) != 0 && (modeMask & ~MASK_BAG_INDEX0) != 0) {
                GameCanvas.setText("Index 0 không chọn cùng loại khác");
                return false;
            }
            ModeMask = modeMask;
            Mode = getPrimaryModeFromMask(modeMask);
            TargetUpgrade = Integer.parseInt(this.targetUpgrade.getString().trim());
            DelayMs = Integer.parseInt(this.delayMs.getString().trim());
            TargetPercent = 0;
            MinCrystalId = 0;
            FlipCount = 0;
            Careful = this.optionChoice.isSelected(0);
            AutoFlip = this.optionChoice.isSelected(1);
            UseProtectUnder14 = this.optionChoice.isSelected(2);
            AutoBuyProtect475 = this.optionChoice.isSelected(3);
            ReEquipWhenDone = this.optionChoice.isSelected(4);
            UseXuWhenLackYen = this.optionChoice.isSelected(5);

            if (TargetUpgrade < 1) {
                TargetUpgrade = 1;
            }
            if (TargetUpgrade > 16) {
                TargetUpgrade = 16;
            }
            if (DelayMs < 100) {
                DelayMs = 100;
            }

            save();
            GameCanvas.setText("Đã lưu Auto Đập Đồ");
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Lỗi dữ liệu Auto Đập Đồ");
            return false;
        }
    }

    public static String getModeName() {
        sanitizeModeMask();
        if (isBagIndex0Mode()) {
            return "Index 0";
        }

        String name = "";
        if (isWeaponMode()) {
            name = appendModeName(name, "Vũ khí");
        }
        if (isAdornMode()) {
            name = appendModeName(name, "Trang sức");
        }
        if (isClotheMode()) {
            name = appendModeName(name, "Trang phục");
        }
        return name.length() > 0 ? name : "Index 0";
    }

    public static boolean isBagIndex0Mode() {
        sanitizeModeMask();
        return (ModeMask & MASK_BAG_INDEX0) != 0;
    }

    public static boolean isWeaponMode() {
        sanitizeModeMask();
        return (ModeMask & MASK_WEAPON) != 0;
    }

    public static boolean isAdornMode() {
        sanitizeModeMask();
        return (ModeMask & MASK_ADORN) != 0;
    }

    public static boolean isClotheMode() {
        sanitizeModeMask();
        return (ModeMask & MASK_CLOTHE) != 0;
    }

    public static int[] getSelectedBodyTypes() {
        sanitizeModeMask();
        int count = 0;
        if (isWeaponMode()) {
            ++count;
        }
        if (isAdornMode()) {
            count += 4;
        }
        if (isClotheMode()) {
            count += 5;
        }

        int[] types = new int[count];
        int index = 0;
        if (isWeaponMode()) {
            types[index++] = 1;
        }
        if (isAdornMode()) {
            types[index++] = 3;
            types[index++] = 5;
            types[index++] = 7;
            types[index++] = 9;
        }
        if (isClotheMode()) {
            types[index++] = 0;
            types[index++] = 2;
            types[index++] = 4;
            types[index++] = 6;
            types[index++] = 8;
        }
        return types;
    }

    private static String appendModeName(String current, String add) {
        return current.length() == 0 ? add : current + "+" + add;
    }

    private static int getPrimaryModeFromMask(int mask) {
        if ((mask & MASK_WEAPON) != 0) {
            return MODE_WEAPON;
        }
        if ((mask & MASK_ADORN) != 0) {
            return MODE_ADORN;
        }
        if ((mask & MASK_CLOTHE) != 0) {
            return MODE_CLOTHE;
        }
        return MODE_BAG_INDEX0;
    }

    private static int maskFromMode(int mode) {
        switch (mode) {
            case MODE_WEAPON:
                return MASK_WEAPON;
            case MODE_ADORN:
                return MASK_ADORN;
            case MODE_CLOTHE:
                return MASK_CLOTHE;
            default:
                return MASK_BAG_INDEX0;
        }
    }

    private static void sanitizeModeMask() {
        if ((ModeMask & (MASK_BAG_INDEX0 | MASK_WEAPON | MASK_ADORN | MASK_CLOTHE)) == 0) {
            ModeMask = maskFromMode(Mode);
        }
        if ((ModeMask & MASK_BAG_INDEX0) != 0 && (ModeMask & ~MASK_BAG_INDEX0) != 0) {
            ModeMask = MASK_BAG_INDEX0;
        }
        ModeMask &= MASK_BAG_INDEX0 | MASK_WEAPON | MASK_ADORN | MASK_CLOTHE;
        Mode = getPrimaryModeFromMask(ModeMask);
    }

    private static void show(Displayable current) {
        Display.getDisplay(GameMidlet.instance).setCurrent(current);
    }

    private static void save() {
        RecordStore rs = null;
        ByteArrayOutputStream byteout = null;
        DataOutputStream dataout = null;
        try {
            byteout = new ByteArrayOutputStream();
            dataout = new DataOutputStream(byteout);
            dataout.writeInt(Mode);
            dataout.writeInt(TargetUpgrade);
            dataout.writeInt(TargetPercent);
            dataout.writeInt(MinCrystalId);
            dataout.writeInt(FlipCount);
            dataout.writeInt(DelayMs);
            dataout.writeBoolean(Careful);
            dataout.writeBoolean(AutoFlip);
            dataout.writeBoolean(UseProtectUnder14);
            dataout.writeBoolean(AutoBuyProtect475);
            dataout.writeBoolean(ReEquipWhenDone);
            dataout.writeInt(ModeMask);
            dataout.writeBoolean(UseXuWhenLackYen);
            dataout.flush();

            rs = RecordStore.openRecordStore(STORE_NAME, true);
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
        RecordStore rs = null;
        ByteArrayInputStream bytein = null;
        DataInputStream datain = null;
        try {
            rs = RecordStore.openRecordStore(STORE_NAME, true);
            if (rs.getNumRecords() > 0) {
                bytein = new ByteArrayInputStream(rs.getRecord(1));
                datain = new DataInputStream(bytein);
                Mode = datain.readInt();
                TargetUpgrade = datain.readInt();
                TargetPercent = datain.readInt();
                MinCrystalId = datain.readInt();
                FlipCount = datain.readInt();
                DelayMs = datain.readInt();
                Careful = datain.readBoolean();
                AutoFlip = datain.readBoolean();
                UseProtectUnder14 = datain.readBoolean();
                AutoBuyProtect475 = datain.readBoolean();
                ReEquipWhenDone = datain.readBoolean();
                if (datain.available() >= 4) {
                    ModeMask = datain.readInt();
                } else {
                    ModeMask = maskFromMode(Mode);
                }
                if (datain.available() > 0) {
                    UseXuWhenLackYen = datain.readBoolean();
                }
                sanitizeModeMask();
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
