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

public final class FormAutoUpFull implements CommandListener {

    private static final String STORE_NAME = "AutoUpFullCfg";

    public static final int MASK_TIER_3X = 1;
    public static final int MASK_TIER_4X = 2;
    public static final int MASK_TIER_5X = 4;
    public static final int MASK_TIER_6X = 8;

    public static final int MASK_SLOT_WEAPON = 1;
    public static final int MASK_SLOT_ADORN = 2;
    public static final int MASK_SLOT_CLOTHE = 4;

    public static int TargetLevel = 70;
    public static boolean LearnBooks = true;
    public static boolean AutoPotential = true;
    public static boolean AutoSkill = true;
    public static int MaxBookLevel = 50;
    public static boolean BuyGear = true;
    public static boolean UpgradeGear = true;
    public static boolean UseXuWhenLackYen = false;
    public static boolean UseProtectUpgrade = true;
    public static boolean ExchangeLuongToYen = false;
    public static boolean UseNamLinhChiX2 = false;
    public static int TierMask = MASK_TIER_3X | MASK_TIER_4X | MASK_TIER_5X | MASK_TIER_6X;
    public static int SlotMask = MASK_SLOT_WEAPON | MASK_SLOT_ADORN | MASK_SLOT_CLOTHE;
    public static int TargetUpgrade = 12;
    public static int CheckDelayMs = 10000;

    private final Form form = new Form("Auto Up Tong");
    private final Command save = new Command("Luu", Command.OK, 1);
    private final Command start = new Command("Chay", Command.OK, 2);
    private final Command stop = new Command("Dung", Command.OK, 3);
    private final Command cancel = new Command("Huy", Command.BACK, 1);
    private final ChoiceGroup optionChoice;
    private final ChoiceGroup tierChoice;
    private final ChoiceGroup slotChoice;
    private final TextField targetLevel;
    private final TextField maxBookLevel;
    private final TextField targetUpgrade;
    private final TextField checkDelay;

    public FormAutoUpFull() {
        this.optionChoice = new ChoiceGroup("Cai dat", ChoiceGroup.MULTIPLE, new String[]{
            "Tu hoc sach ky nang",
            "Tu cong tiem nang",
            "Tu cong ky nang danh",
            "Tu mua do thieu",
            "Tu dap set",
            "Het yen dung xu",
            "Dung bao hiem khi dap",
            "Doi luong ra yen khi het yen",
            "Tu mua/dung Nam linh chi 248 (x2)"
        }, (Image[]) null);
        this.tierChoice = new ChoiceGroup("Set can nang", ChoiceGroup.MULTIPLE, new String[]{
            "Set 3x",
            "Set 4x",
            "Set 5x",
            "Set 6x"
        }, (Image[]) null);
        this.slotChoice = new ChoiceGroup("Loai do", ChoiceGroup.MULTIPLE, new String[]{
            "Vu khi",
            "Trang suc",
            "Trang phuc"
        }, (Image[]) null);
        this.targetLevel = new TextField("Up den level", String.valueOf(TargetLevel), 3, TextField.NUMERIC);
        this.maxBookLevel = new TextField("Mua sach den lv", String.valueOf(MaxBookLevel), 3, TextField.NUMERIC);
        this.targetUpgrade = new TextField("Dap den +", String.valueOf(TargetUpgrade), 2, TextField.NUMERIC);
        this.checkDelay = new TextField("Delay check ms", String.valueOf(CheckDelayMs), 6, TextField.NUMERIC);
    }

    public void select() {
        load();
        this.form.deleteAll();
        this.targetLevel.setString(String.valueOf(TargetLevel));
        this.maxBookLevel.setString(String.valueOf(MaxBookLevel));
        this.targetUpgrade.setString(String.valueOf(TargetUpgrade));
        this.checkDelay.setString(String.valueOf(CheckDelayMs));

        this.optionChoice.setSelectedIndex(0, LearnBooks);
        this.optionChoice.setSelectedIndex(1, AutoPotential);
        this.optionChoice.setSelectedIndex(2, AutoSkill);
        this.optionChoice.setSelectedIndex(3, BuyGear);
        this.optionChoice.setSelectedIndex(4, UpgradeGear);
        this.optionChoice.setSelectedIndex(5, UseXuWhenLackYen);
        this.optionChoice.setSelectedIndex(6, UseProtectUpgrade);
        this.optionChoice.setSelectedIndex(7, ExchangeLuongToYen);
        this.optionChoice.setSelectedIndex(8, UseNamLinhChiX2);
        this.tierChoice.setSelectedIndex(0, (TierMask & MASK_TIER_3X) != 0);
        this.tierChoice.setSelectedIndex(1, (TierMask & MASK_TIER_4X) != 0);
        this.tierChoice.setSelectedIndex(2, (TierMask & MASK_TIER_5X) != 0);
        this.tierChoice.setSelectedIndex(3, (TierMask & MASK_TIER_6X) != 0);
        this.slotChoice.setSelectedIndex(0, (SlotMask & MASK_SLOT_WEAPON) != 0);
        this.slotChoice.setSelectedIndex(1, (SlotMask & MASK_SLOT_ADORN) != 0);
        this.slotChoice.setSelectedIndex(2, (SlotMask & MASK_SLOT_CLOTHE) != 0);

        this.form.append("Lenh: uplvfull70 / setuplv / stopuplv\n");
        this.form.append(this.targetLevel);
        this.form.append(this.optionChoice);
        this.form.append(this.maxBookLevel);
        this.form.append(this.tierChoice);
        this.form.append(this.slotChoice);
        this.form.append(this.targetUpgrade);
        this.form.append(this.checkDelay);
        this.form.addCommand(this.save);
        this.form.addCommand(this.start);
        this.form.addCommand(this.stop);
        this.form.addCommand(this.cancel);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.save || command == this.start) {
            if (!readAndSave()) {
                return;
            }
        }

        if (command == this.start) {
            AutoUpLevel.startFull(TargetLevel);
        } else if (command == this.stop) {
            AutoUpLevel.stop();
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            TargetLevel = Integer.parseInt(this.targetLevel.getString().trim());
            MaxBookLevel = Integer.parseInt(this.maxBookLevel.getString().trim());
            TargetUpgrade = Integer.parseInt(this.targetUpgrade.getString().trim());
            CheckDelayMs = Integer.parseInt(this.checkDelay.getString().trim());
            LearnBooks = this.optionChoice.isSelected(0);
            AutoPotential = this.optionChoice.isSelected(1);
            AutoSkill = this.optionChoice.isSelected(2);
            BuyGear = this.optionChoice.isSelected(3);
            UpgradeGear = this.optionChoice.isSelected(4);
            UseXuWhenLackYen = this.optionChoice.isSelected(5);
            UseProtectUpgrade = this.optionChoice.isSelected(6);
            ExchangeLuongToYen = this.optionChoice.isSelected(7);
            UseNamLinhChiX2 = this.optionChoice.isSelected(8);

            int tierMask = 0;
            if (this.tierChoice.isSelected(0)) {
                tierMask |= MASK_TIER_3X;
            }
            if (this.tierChoice.isSelected(1)) {
                tierMask |= MASK_TIER_4X;
            }
            if (this.tierChoice.isSelected(2)) {
                tierMask |= MASK_TIER_5X;
            }
            if (this.tierChoice.isSelected(3)) {
                tierMask |= MASK_TIER_6X;
            }

            int slotMask = 0;
            if (this.slotChoice.isSelected(0)) {
                slotMask |= MASK_SLOT_WEAPON;
            }
            if (this.slotChoice.isSelected(1)) {
                slotMask |= MASK_SLOT_ADORN;
            }
            if (this.slotChoice.isSelected(2)) {
                slotMask |= MASK_SLOT_CLOTHE;
            }

            if ((BuyGear || UpgradeGear) && tierMask == 0) {
                GameCanvas.setText("Chon it nhat 1 set");
                return false;
            }
            if ((BuyGear || UpgradeGear) && slotMask == 0) {
                GameCanvas.setText("Chon it nhat 1 loai do");
                return false;
            }

            TierMask = tierMask;
            SlotMask = slotMask;
            sanitize();
            save();
            GameCanvas.setText("Da luu Auto Up Tong");
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Loi du lieu Auto Up Tong");
            return false;
        }
    }

    public static int getTargetTierForLevel(int level) {
        int maxTier = level / 10;
        int tier = -1;
        if (maxTier >= 3 && (TierMask & MASK_TIER_3X) != 0) {
            tier = 3;
        }
        if (maxTier >= 4 && (TierMask & MASK_TIER_4X) != 0) {
            tier = 4;
        }
        if (maxTier >= 5 && (TierMask & MASK_TIER_5X) != 0) {
            tier = 5;
        }
        if (maxTier >= 6 && (TierMask & MASK_TIER_6X) != 0) {
            tier = 6;
        }
        return tier;
    }

    public static int getTargetUpgradeForTier(int tier) {
        int target = TargetUpgrade;
        if (tier <= 0 || target < 0) {
            target = 0;
        }
        if (target > 16) {
            target = 16;
        }
        return target;
    }

    public static int getUpgradeModeMask() {
        int mask = 0;
        if ((SlotMask & MASK_SLOT_WEAPON) != 0) {
            mask |= FormAutoDapDo.MASK_WEAPON;
        }
        if ((SlotMask & MASK_SLOT_ADORN) != 0) {
            mask |= FormAutoDapDo.MASK_ADORN;
        }
        if ((SlotMask & MASK_SLOT_CLOTHE) != 0) {
            mask |= FormAutoDapDo.MASK_CLOTHE;
        }
        return mask;
    }

    public static int[] getSelectedBodyTypes() {
        int count = 0;
        if ((SlotMask & MASK_SLOT_WEAPON) != 0) {
            ++count;
        }
        if ((SlotMask & MASK_SLOT_ADORN) != 0) {
            count += 4;
        }
        if ((SlotMask & MASK_SLOT_CLOTHE) != 0) {
            count += 5;
        }
        int[] result = new int[count];
        int index = 0;
        if ((SlotMask & MASK_SLOT_WEAPON) != 0) {
            result[index++] = 1;
        }
        if ((SlotMask & MASK_SLOT_CLOTHE) != 0) {
            result[index++] = 8;
        }
        if ((SlotMask & MASK_SLOT_ADORN) != 0) {
            result[index++] = 9;
        }
        if ((SlotMask & MASK_SLOT_CLOTHE) != 0) {
            result[index++] = 6;
        }
        if ((SlotMask & MASK_SLOT_ADORN) != 0) {
            result[index++] = 7;
        }
        if ((SlotMask & MASK_SLOT_CLOTHE) != 0) {
            result[index++] = 4;
        }
        if ((SlotMask & MASK_SLOT_ADORN) != 0) {
            result[index++] = 5;
        }
        if ((SlotMask & MASK_SLOT_CLOTHE) != 0) {
            result[index++] = 2;
        }
        if ((SlotMask & MASK_SLOT_ADORN) != 0) {
            result[index++] = 3;
        }
        if ((SlotMask & MASK_SLOT_CLOTHE) != 0) {
            result[index++] = 0;
        }
        return result;
    }

    public static String getSummary() {
        return "up " + TargetLevel + ", sach " + MaxBookLevel + ", set " + getTierText() + ", dap +" + TargetUpgrade
                + (AutoPotential ? ", tn" : "")
                + (AutoSkill ? ", skill" : "")
                + (UseProtectUpgrade ? ", bh" : ", ko bh")
                + (ExchangeLuongToYen ? ", doi yen" : "")
                + (UseNamLinhChiX2 ? ", x2" : "");
    }

    private static String getTierText() {
        String text = "";
        if ((TierMask & MASK_TIER_3X) != 0) {
            text = append(text, "3x");
        }
        if ((TierMask & MASK_TIER_4X) != 0) {
            text = append(text, "4x");
        }
        if ((TierMask & MASK_TIER_5X) != 0) {
            text = append(text, "5x");
        }
        if ((TierMask & MASK_TIER_6X) != 0) {
            text = append(text, "6x");
        }
        return text.length() == 0 ? "tat" : text;
    }

    private static String append(String current, String add) {
        return current.length() == 0 ? add : current + "," + add;
    }

    private static void sanitize() {
        if (TargetLevel < 1) {
            TargetLevel = 1;
        }
        if (TargetLevel > 200) {
            TargetLevel = 200;
        }
        if (MaxBookLevel < 10) {
            MaxBookLevel = 10;
        }
        if (MaxBookLevel > 120) {
            MaxBookLevel = 120;
        }
        if (CheckDelayMs < 3000) {
            CheckDelayMs = 3000;
        }
        if (CheckDelayMs > 60000) {
            CheckDelayMs = 60000;
        }
        TargetUpgrade = clampUpgrade(TargetUpgrade);
    }

    private static int clampUpgrade(int value) {
        if (value < 0) {
            return 0;
        }
        return value > 16 ? 16 : value;
    }

    public static void save() {
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeInt(TargetLevel);
            dataout.writeBoolean(LearnBooks);
            dataout.writeInt(MaxBookLevel);
            dataout.writeBoolean(BuyGear);
            dataout.writeBoolean(UpgradeGear);
            dataout.writeBoolean(UseXuWhenLackYen);
            dataout.writeInt(TierMask);
            dataout.writeInt(SlotMask);
            dataout.writeInt(TargetUpgrade);
            dataout.writeInt(CheckDelayMs);
            dataout.writeBoolean(UseProtectUpgrade);
            dataout.writeBoolean(ExchangeLuongToYen);
            dataout.writeBoolean(UseNamLinhChiX2);
            dataout.writeBoolean(AutoPotential);
            dataout.writeBoolean(AutoSkill);
            dataout.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            dataout.close();
            byteout.close();
        } catch (Exception e) {
        }
    }

    public static void load() {
        try {
            byte[] data = RMS.getRecord(STORE_NAME);
            if (data != null) {
                ByteArrayInputStream bytein = new ByteArrayInputStream(data);
                DataInputStream datain = new DataInputStream(bytein);
                TargetLevel = datain.readInt();
                LearnBooks = datain.readBoolean();
                MaxBookLevel = datain.readInt();
                BuyGear = datain.readBoolean();
                UpgradeGear = datain.readBoolean();
                UseXuWhenLackYen = datain.readBoolean();
                TierMask = datain.readInt();
                SlotMask = datain.readInt();
                if (datain.available() >= 20) {
                    int old3x = datain.readInt();
                    int old4x = datain.readInt();
                    datain.readInt();
                    datain.readInt();
                    TargetUpgrade = old4x > 0 ? old4x : old3x;
                    if (datain.available() >= 4) {
                        CheckDelayMs = datain.readInt();
                    }
                } else {
                    if (datain.available() >= 4) {
                        TargetUpgrade = datain.readInt();
                    }
                    if (datain.available() >= 4) {
                        CheckDelayMs = datain.readInt();
                    }
                }
                int remaining = datain.available();
                if (remaining >= 5) {
                    UseProtectUpgrade = datain.readBoolean();
                    ExchangeLuongToYen = datain.readBoolean();
                    UseNamLinhChiX2 = datain.readBoolean();
                    AutoPotential = datain.readBoolean();
                    AutoSkill = datain.readBoolean();
                } else if (remaining >= 3) {
                    UseProtectUpgrade = datain.readBoolean();
                    ExchangeLuongToYen = datain.readBoolean();
                    UseNamLinhChiX2 = datain.readBoolean();
                } else if (remaining == 1) {
                    UseNamLinhChiX2 = datain.readBoolean();
                }
                datain.close();
                bytein.close();
            }
        } catch (Exception e) {
        }
        sanitize();
    }

    static {
        load();
    }
}
