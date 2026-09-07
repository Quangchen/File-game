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

public final class FormAutoBiKip implements CommandListener {

    private static final String STORE_NAME = "AutoBiKipCfg";

    public static final int[] OPTION_IDS = new int[]{
        81, 82, 83, 84, 86, 87, 88, 89, 90, 91, 92, 94, 95, 96, 97, 98, 99, 100
    };

    public static final String[] OPTION_NAMES = new String[]{
        "Kháng tất cả(81)",
        "HP tối đa(82)",
        "MP tối đa(83)",
        "Né đòn(84)",
        "Chính xác(86)",
        "Tấn công(87)",
        "Hỏa công(88)",
        "Băng công(89)",
        "Phong lôi(90)",
        "Phản đòn(91)",
        "Chí mạng(92)",
        "Tấn công %(94)",
        "Kháng băng(95)",
        "Kháng hỏa(96)",
        "Kháng phong(97)",
        "Miễn giảm sát thương(98)",
        "Hồi HP/MP(99)",
        "Tăng kinh nghiệm(100)"
    };

    public static boolean[] Selected = new boolean[OPTION_IDS.length];
    public static String MinParams = "87:500,94:15";
    public static int NeedCount = 2;
    public static int MaxAttempts = 200;
    public static int DelayMs = 1200;
    public static int KeepGold = 0;
    public static String MenuPath = "0";

    private final Form form = new Form("Auto Săn Bí Kíp");
    private final Command save = new Command("Lưu", Command.OK, 1);
    private final Command start = new Command("Bắt đầu", Command.OK, 2);
    private final Command stop = new Command("Dừng", Command.OK, 3);
    private final Command cancel = new Command("Hủy", Command.BACK, 1);
    private final ChoiceGroup options;
    private final TextField minParams;
    private final TextField needCount;
    private final TextField maxAttempts;
    private final TextField delayMs;
    private final TextField keepGold;
    private final TextField menuPath;

    public FormAutoBiKip() {
        this.options = new ChoiceGroup("Chỉ số cần săn", ChoiceGroup.MULTIPLE, OPTION_NAMES, (Image[]) null);
        this.minParams = new TextField("Chỉ số tối thiểu id:param", MinParams, 80, TextField.ANY);
        this.needCount = new TextField("Số dòng cần lấy", String.valueOf(NeedCount), 2, TextField.NUMERIC);
        this.maxAttempts = new TextField("Số lần tối đa", String.valueOf(MaxAttempts), 5, TextField.NUMERIC);
        this.delayMs = new TextField("Delay ms", String.valueOf(DelayMs), 5, TextField.NUMERIC);
        this.keepGold = new TextField("Giữ lại lượng", String.valueOf(KeepGold), 6, TextField.NUMERIC);
        this.menuPath = new TextField("Menu Tashino", MenuPath, 20, TextField.ANY);
    }

    public void select() {
        load();
        this.form.deleteAll();
        for (int i = 0; i < Selected.length && i < this.options.size(); i++) {
            this.options.setSelectedIndex(i, Selected[i]);
        }
        this.minParams.setString(MinParams);
        this.needCount.setString(String.valueOf(NeedCount));
        this.maxAttempts.setString(String.valueOf(MaxAttempts));
        this.delayMs.setString(String.valueOf(DelayMs));
        this.keepGold.setString(String.valueOf(KeepGold));
        this.menuPath.setString(MenuPath);

        this.form.append("Trạng thái: " + AutoBiKip.getStatusText() + "\n");
        this.form.append("Bí kíp phải đang mặc và chưa nâng cấp.\n");
        this.form.append("Min là chỉ số bắt buộc phải có đủ param.\n");
        this.form.append("Số dòng = bắt buộc + chỉ số phụ đã tick. Ví dụ min 3 dòng, số dòng 5 thì cần thêm 2 dòng tick.\n");
        this.form.append("Có chỉ số săn ngoài tick sẽ bỏ qua.\n");
        this.form.append("Min ví dụ: 99:200,87:500,94:1\n");
        this.form.append("S\u1ed1 d\u00f2ng c\u1ea7n l\u1ea5y l\u00e0 m\u1ee9c t\u1ed1i thi\u1ec3u: nh\u1eadp 3 th\u00ec 3, 4, 5 d\u00f2ng h\u1ee3p l\u1ec7 \u0111\u1ec1u \u0111\u01b0\u1ee3c l\u1ea5y.\n");
        this.form.append("Ch\u1ec9 s\u1ed1 kh\u00f4ng tick kh\u00f4ng \u0111\u01b0\u1ee3c t\u00ednh v\u00e0o s\u1ed1 d\u00f2ng \u0111\u1ea1t.\n");
        this.form.append(this.options);
        this.form.append(this.minParams);
        this.form.append(this.needCount);
        this.form.append(this.maxAttempts);
        this.form.append(this.delayMs);
        this.form.append(this.keepGold);
        this.form.append(this.menuPath);
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
            AutoBiKip.start();
        } else if (command == this.stop) {
            AutoBiKip.stop();
        }

        show(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            for (int i = 0; i < Selected.length && i < this.options.size(); i++) {
                Selected[i] = this.options.isSelected(i);
            }
            MinParams = this.minParams.getString().trim();
            NeedCount = Integer.parseInt(this.needCount.getString().trim());
            MaxAttempts = Integer.parseInt(this.maxAttempts.getString().trim());
            DelayMs = Integer.parseInt(this.delayMs.getString().trim());
            KeepGold = Integer.parseInt(this.keepGold.getString().trim());
            MenuPath = this.menuPath.getString().trim();

            int requiredCount = countRequiredMinParams();
            int capacity = countTargetCapacity();
            if (capacity == 0) {
                GameCanvas.setText("Hãy chọn ít nhất 1 chỉ số");
                return false;
            }
            if (NeedCount <= 0) {
                NeedCount = capacity;
            }
            if (NeedCount < requiredCount) {
                NeedCount = requiredCount;
            }
            if (NeedCount > capacity) {
                NeedCount = capacity;
            }
            if (MaxAttempts < 1) {
                MaxAttempts = 1;
            }
            if (DelayMs < 50) {
                DelayMs = 50;
            }
            if (KeepGold < 0) {
                KeepGold = 0;
            }
            if (MenuPath == null || MenuPath.length() == 0) {
                MenuPath = "0";
            }

            save();
            GameCanvas.setText("Đã lưu Auto Bí Kíp");
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Lỗi dữ liệu Auto Bí Kíp");
            return false;
        }
    }

    public static int countSelected() {
        int count = 0;
        for (int i = 0; i < Selected.length; i++) {
            if (Selected[i]) {
                count++;
            }
        }
        return count;
    }

    public static int countRequiredMinParams() {
        int[] ids = new int[OPTION_IDS.length];
        int[] params = new int[OPTION_IDS.length];
        return parseRequiredMinParams(ids, params);
    }

    private static int countTargetCapacity() {
        int[] ids = new int[OPTION_IDS.length];
        int[] params = new int[OPTION_IDS.length];
        int count = parseRequiredMinParams(ids, params);
        for (int i = 0; i < OPTION_IDS.length; i++) {
            if (Selected[i] && !contains(ids, count, OPTION_IDS[i])) {
                count++;
            }
        }
        return count;
    }

    private static int parseRequiredMinParams(int[] ids, int[] params) {
        int count = 0;
        try {
            String cfg = MinParams;
            if (cfg == null || cfg.length() == 0) {
                return 0;
            }
            String[] parts = Code.splitString(cfg, ",");
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i].trim();
                int p = part.indexOf(':');
                if (p < 0) {
                    p = part.indexOf('=');
                }
                if (p > 0) {
                    int id = Integer.parseInt(part.substring(0, p).trim());
                    int param = Integer.parseInt(part.substring(p + 1).trim());
                    if (id > 0 && !contains(ids, count, id) && count < ids.length) {
                        ids[count] = id;
                        params[count] = param > 0 ? param : 1;
                        count++;
                    }
                }
            }
        } catch (Exception e) {
        }
        return count;
    }

    private static boolean contains(int[] arr, int size, int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                return true;
            }
        }
        return false;
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
            dataout.writeInt(Selected.length);
            for (int i = 0; i < Selected.length; i++) {
                dataout.writeBoolean(Selected[i]);
            }
            dataout.writeUTF(MinParams == null ? "" : MinParams);
            dataout.writeInt(NeedCount);
            dataout.writeInt(MaxAttempts);
            dataout.writeInt(DelayMs);
            dataout.writeInt(KeepGold);
            dataout.writeUTF(MenuPath == null ? "" : MenuPath);
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
            rs = RecordStore.openRecordStore(STORE_NAME, true);
            if (rs.getNumRecords() == 0) {
                ensureDefault();
                return;
            }
            byte[] data = rs.getRecord(1);
            bytein = new ByteArrayInputStream(data);
            datain = new DataInputStream(bytein);
            int length = datain.readInt();
            for (int i = 0; i < Selected.length; i++) {
                Selected[i] = i < length && datain.readBoolean();
            }
            for (int i = Selected.length; i < length; i++) {
                datain.readBoolean();
            }
            MinParams = datain.readUTF();
            NeedCount = datain.readInt();
            MaxAttempts = datain.readInt();
            DelayMs = datain.readInt();
            KeepGold = datain.readInt();
            MenuPath = datain.readUTF();
            ensureDefault();
        } catch (Exception e) {
            ensureDefault();
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

    private static void ensureDefault() {
        int requiredCount = countRequiredMinParams();
        int capacity = countTargetCapacity();
        if (countSelected() == 0 && requiredCount == 0) {
            setSelectedById(87, true);
            setSelectedById(94, true);
        }
        if (MinParams == null || MinParams.length() == 0) {
            MinParams = "87:500,94:15";
        }
        requiredCount = countRequiredMinParams();
        capacity = countTargetCapacity();
        if (NeedCount <= 0) {
            NeedCount = capacity;
        }
        if (NeedCount < requiredCount) {
            NeedCount = requiredCount;
        }
        if (NeedCount > capacity) {
            NeedCount = capacity;
        }
        if (MaxAttempts <= 0) {
            MaxAttempts = 200;
        }
        if (DelayMs < 50) {
            DelayMs = 500;
        }
        if (KeepGold < 0) {
            KeepGold = 0;
        }
        if (MenuPath == null || MenuPath.length() == 0) {
            MenuPath = "0";
        }
    }

    private static void setSelectedById(int id, boolean selected) {
        for (int i = 0; i < OPTION_IDS.length; i++) {
            if (OPTION_IDS[i] == id) {
                Selected[i] = selected;
                return;
            }
        }
    }
}
