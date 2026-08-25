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
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStore;

public final class FormAutoLuyenNgoc implements CommandListener, ItemStateListener {

    private static final String STORE_NAME = "AutoLuyenNgocCfg";
    public static final String[] HUNT_GEM_NAMES = new String[]{
        "Huyền tinh ngọc 652",
        "Huyết ngọc 653",
        "Lam tinh ngọc 654",
        "Lục ngọc 655"
    };
    public static final String[] HUNT_ROLE_NAMES = new String[]{
        "Vũ khí",
        "Trang bị",
        "Trang sức"
    };
    public static int targetUpgrade = 8;
    public static int delayMs = 900;
    public static int scanDelayMs = 15000;
    public static boolean huntMaxNgoc;
    public static boolean[] huntGems = new boolean[]{true, true, true, true};
    public static boolean[] huntRoles = new boolean[]{true, true, true};

    private final Form form = new Form("Auto Luyện Ngọc");
    private final Command luu = new Command("Lưu", Command.OK, 1);
    private final Command huy = new Command("Hủy", Command.BACK, 1);
    private final TextField targetTf;
    private final TextField delayTf;
    private final TextField scanDelayTf;
    private final ChoiceGroup huntMode;
    private final ChoiceGroup huntGemChoice;
    private final ChoiceGroup huntRoleChoice;

    public FormAutoLuyenNgoc() {
        this.targetTf = new TextField("Luyện đến +", String.valueOf(targetUpgrade), 2, TextField.NUMERIC);
        this.delayTf = new TextField("Delay ms", String.valueOf(delayMs), 5, TextField.NUMERIC);
        this.scanDelayTf = new TextField("Delay quét hành trang (s)", String.valueOf(getScanDelaySeconds()), 4, TextField.NUMERIC);
        this.huntMode = new ChoiceGroup("Săn ngọc", ChoiceGroup.MULTIPLE, new String[]{
            "Săn ngọc max"
        }, (Image[])null);
        this.huntGemChoice = new ChoiceGroup("Loại ngọc cần lọc", ChoiceGroup.MULTIPLE, HUNT_GEM_NAMES, (Image[])null);
        this.huntRoleChoice = new ChoiceGroup("Lọc chỉ số max", ChoiceGroup.MULTIPLE, HUNT_ROLE_NAMES, (Image[])null);
    }

    public final void select() {
        this.targetTf.setString(String.valueOf(targetUpgrade));
        this.delayTf.setString(String.valueOf(delayMs));
        this.scanDelayTf.setString(String.valueOf(getScanDelaySeconds()));
        this.huntMode.setSelectedIndex(0, huntMaxNgoc);
        setChoice(this.huntGemChoice, huntGems);
        setChoice(this.huntRoleChoice, huntRoles);
        this.renderForm();
        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);
        this.form.setItemStateListener(this);
        show(this.form);
    }

    private void renderForm() {
        this.form.deleteAll();
        this.form.append(AutoLuyenNgoc.getStatusText());
        this.form.append("\nĐặt ngọc chính ở ô đầu tiên.\n");
        this.form.append(this.targetTf);
        this.form.append(this.delayTf);
        this.form.append(this.scanDelayTf);
        this.form.append(this.huntMode);
        ensureDefaultHuntChoices();
        this.form.append("Phần lọc chỉ số chỉ có tác dụng khi bật Săn ngọc max.\n");
        this.form.append("Ngọc đạt max theo loại đã chọn sẽ được giữ lại, không dùng làm phôi.\n");
        this.form.append(this.huntGemChoice);
        this.form.append(this.huntRoleChoice);
    }

    private static void show(Displayable current) {
        Display.getDisplay(GameMidlet.instance).setCurrent(current);
    }

    public void itemStateChanged(Item item) {
        if (item == this.huntMode) {
            this.renderForm();
        }
    }

    public final void commandAction(Command command, Displayable displayable) {
        if (command == this.luu) {
            if (!readAndSave()) {
                return;
            }

            show(MotherCanvas.getInstance());
            GameCanvas.setText("Đã lưu luyện ngọc");
            return;
        }

        show(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            targetUpgrade = Integer.parseInt(this.targetTf.getString().trim());
            delayMs = Integer.parseInt(this.delayTf.getString().trim());
            scanDelayMs = Integer.parseInt(this.scanDelayTf.getString().trim()) * 1000;
            huntMaxNgoc = this.huntMode.isSelected(0);
            readChoice(this.huntGemChoice, huntGems);
            readChoice(this.huntRoleChoice, huntRoles);
            if (targetUpgrade < 2) {
                targetUpgrade = 2;
            }
            if (targetUpgrade > 10) {
                targetUpgrade = 10;
            }
            if (delayMs < 200) {
                delayMs = 200;
            }
            if (scanDelayMs < 1000) {
                scanDelayMs = 1000;
            }
            if (huntMaxNgoc && (!hasSelected(huntGems) || !hasSelected(huntRoles))) {
                GameCanvas.setText("Hãy chọn loại ngọc và dòng cần săn");
                return false;
            }
            save();
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Lỗi lưu luyện ngọc");
            return false;
        }
    }

    private static void save() {
        RecordStore rs = null;
        ByteArrayOutputStream byteout = null;
        DataOutputStream dataout = null;
        try {
            byteout = new ByteArrayOutputStream();
            dataout = new DataOutputStream(byteout);
            dataout.writeInt(targetUpgrade);
            dataout.writeInt(delayMs);
            dataout.writeInt(scanDelayMs);
            dataout.writeBoolean(huntMaxNgoc);
            writeBooleans(dataout, huntGems);
            writeBooleans(dataout, huntRoles);
            dataout.flush();

            rs = RecordStore.openRecordStore(STORE_NAME, true);
            byte[] data = byteout.toByteArray();
            if (rs.getNumRecords() == 0) {
                rs.addRecord(data, 0, data.length);
            } else {
                rs.setRecord(1, data, 0, data.length);
            }
        } catch (Exception e) {
        }

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
        try {
            if (rs != null) {
                rs.closeRecordStore();
            }
        } catch (Exception e) {
        }
    }

    static {
        RecordStore rs = null;
        ByteArrayInputStream bytein = null;
        DataInputStream datain = null;
        try {
            rs = RecordStore.openRecordStore(STORE_NAME, true);
            if (rs.getNumRecords() != 0) {
                bytein = new ByteArrayInputStream(rs.getRecord(1));
                datain = new DataInputStream(bytein);
                targetUpgrade = datain.readInt();
                delayMs = datain.readInt();
                scanDelayMs = datain.readInt();
                if (datain.available() > 0) {
                    huntMaxNgoc = datain.readBoolean();
                    readBooleans(datain, huntGems);
                    readBooleans(datain, huntRoles);
                }
            }
        } catch (Exception e) {
        }

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
        try {
            if (rs != null) {
                rs.closeRecordStore();
            }
        } catch (Exception e) {
        }
    }

    public static int getScanDelayMs() {
        return scanDelayMs < 1000 ? 1000 : scanDelayMs;
    }

    public static int getScanDelaySeconds() {
        int delay = getScanDelayMs();
        return (delay + 999) / 1000;
    }

    public static boolean isHuntGemSelected(int index) {
        return index >= 0 && index < huntGems.length && huntGems[index];
    }

    public static boolean isHuntRoleSelected(int index) {
        return index >= 0 && index < huntRoles.length && huntRoles[index];
    }

    private static void setChoice(ChoiceGroup choice, boolean[] values) {
        for (int i = 0; i < values.length && i < choice.size(); ++i) {
            choice.setSelectedIndex(i, values[i]);
        }
    }

    private static void readChoice(ChoiceGroup choice, boolean[] values) {
        for (int i = 0; i < values.length && i < choice.size(); ++i) {
            values[i] = choice.isSelected(i);
        }
    }

    private void ensureDefaultHuntChoices() {
        if (!hasSelected(huntGems)) {
            for (int i = 0; i < huntGems.length; ++i) {
                huntGems[i] = true;
            }
            setChoice(this.huntGemChoice, huntGems);
        }
        if (!hasSelected(huntRoles)) {
            for (int i = 0; i < huntRoles.length; ++i) {
                huntRoles[i] = true;
            }
            setChoice(this.huntRoleChoice, huntRoles);
        }
    }

    private static boolean hasSelected(boolean[] values) {
        for (int i = 0; i < values.length; ++i) {
            if (values[i]) {
                return true;
            }
        }
        return false;
    }

    private static void writeBooleans(DataOutputStream out, boolean[] values) throws Exception {
        for (int i = 0; i < values.length; ++i) {
            out.writeBoolean(values[i]);
        }
    }

    private static void readBooleans(DataInputStream in, boolean[] values) throws Exception {
        for (int i = 0; i < values.length && in.available() > 0; ++i) {
            values[i] = in.readBoolean();
        }
    }
}
