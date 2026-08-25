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

public final class FormAutoDungHop implements CommandListener {

    private static final String STORE_NAME = "AutoDungHopCfg";

    public static int MainIndex = -1;
    public static int TargetLevelX = 7;
    public static int MaxAttempts = 30;
    public static int DelayMs = 1200;
    public static String MenuPath = "5,0";
    public static boolean HoldLine = false;
    public static boolean UseProtect = true;
    public static boolean AutoGoKanata = true;
    public static boolean AutoBuyPhoi = true;
    public static boolean HuntFullSixX = false;
    public static boolean SellBadSixX = true;
    public static int HuntSixXNpcId = 0;
    public static String HuntSixXMenuPath = "8,0";
    public static int HuntSixXCardNeed = 10;
    public static boolean HuntSixXByOption = false;
    public static boolean HuntSixXRequireAll = true;
    public static String HuntSixXRuleText = "";
    private static boolean loaded = false;

    private final Form form = new Form("Auto Dung Hợp");
    private final Command save = new Command("Lưu", Command.OK, 1);
    private final Command start = new Command("Bắt đầu", Command.OK, 2);
    private final Command stop = new Command("Dừng", Command.OK, 3);
    private final Command cancel = new Command("Hủy", Command.BACK, 1);
    private final ChoiceGroup options;
    private final TextField mainIndex;
    private final TextField targetLevelX;
    private final TextField maxAttempts;
    private final TextField delayMs;
    private final TextField menuPath;
    private final TextField huntNpcId;
    private final TextField huntMenuPath;
    private final TextField huntCardNeed;
    private final TextField huntRuleText;

    public FormAutoDungHop() {
        this.options = new ChoiceGroup("Cài đặt", ChoiceGroup.MULTIPLE, new String[]{
            "Giữ dòng",
            "Dùng bảo hiểm 1214",
            "Tự về Kanata",
            "Tự mua phôi 7x/8x"
        }, (Image[]) null);
        this.options.append("Săn đồ 6x đủ dòng", null);
        this.options.append("Tự bán đồ 6x không đạt", null);
        this.options.append("Săn chỉ số 6x", null);
        this.options.append("Cần đủ rule chỉ số", null);
        this.mainIndex = new TextField("Index đồ chính (-1 = tự quét)", String.valueOf(MainIndex), 4, TextField.ANY);
        this.targetLevelX = new TextField("Nâng đến cấp x (7-11)", String.valueOf(TargetLevelX), 2, TextField.NUMERIC);
        this.maxAttempts = new TextField("Số lần tối đa", String.valueOf(MaxAttempts), 4, TextField.NUMERIC);
        this.delayMs = new TextField("Delay ms", String.valueOf(DelayMs), 5, TextField.NUMERIC);
        this.menuPath = new TextField("Menu Kanata", MenuPath, 20, TextField.ANY);
        this.huntNpcId = new TextField("NPC tạo 6x (0/1/2)", String.valueOf(HuntSixXNpcId), 3, TextField.NUMERIC);
        this.huntMenuPath = new TextField("Menu tạo 6x", HuntSixXMenuPath, 20, TextField.ANY);
        this.huntCardNeed = new TextField("Thẻ mỗi loại", String.valueOf(HuntSixXCardNeed), 3, TextField.NUMERIC);
        this.huntRuleText = new TextField("Rule chỉ số 6x", HuntSixXRuleText, 80, TextField.ANY);
    }

    public void select() {
        load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, HoldLine);
        this.options.setSelectedIndex(1, UseProtect);
        this.options.setSelectedIndex(2, AutoGoKanata);
        this.options.setSelectedIndex(3, AutoBuyPhoi);
        this.options.setSelectedIndex(4, HuntFullSixX);
        this.options.setSelectedIndex(5, SellBadSixX);
        this.options.setSelectedIndex(6, HuntSixXByOption);
        this.options.setSelectedIndex(7, HuntSixXRequireAll);
        this.mainIndex.setString(String.valueOf(MainIndex));
        this.targetLevelX.setString(String.valueOf(TargetLevelX));
        this.maxAttempts.setString(String.valueOf(MaxAttempts));
        this.delayMs.setString(String.valueOf(DelayMs));
        this.menuPath.setString(MenuPath);
        this.huntNpcId.setString(String.valueOf(HuntSixXNpcId));
        this.huntMenuPath.setString(HuntSixXMenuPath);
        this.huntCardNeed.setString(String.valueOf(HuntSixXCardNeed));
        this.huntRuleText.setString(HuntSixXRuleText);

        this.form.append("Trạng thái: " + AutoDungHop.getStatusText() + "\n");
        this.form.append("Nhiều đồ chính: để index -1, auto làm từng món hợp lệ.\n");
        this.form.append("Phôi: chọn đồ thường cùng loại/giới/hệ, hơn dung hợp 1 cấp.\n");
        this.form.append("Tự mua phôi chỉ áp dụng khi thiếu phôi 7x hoặc 8x trong shop.\n");
        this.form.append("Săn 6x: đạt khi có 13 dòng, bắt buộc có 155 và 176.\n");
        this.form.append("Rule ví dụ: 0>=480,1>=480,21>=1750. Bỏ trống rule sẽ chỉ lọc đủ dòng.\n");
        this.form.append(this.options);
        this.form.append(this.mainIndex);
        this.form.append(this.targetLevelX);
        this.form.append(this.maxAttempts);
        this.form.append(this.delayMs);
        this.form.append(this.menuPath);
        this.form.append(this.huntNpcId);
        this.form.append(this.huntMenuPath);
        this.form.append(this.huntCardNeed);
        this.form.append(this.huntRuleText);
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
            AutoDungHop.start();
        } else if (command == this.stop) {
            AutoDungHop.stop();
        }

        show(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            MainIndex = Integer.parseInt(this.mainIndex.getString().trim());
            TargetLevelX = Integer.parseInt(this.targetLevelX.getString().trim());
            MaxAttempts = Integer.parseInt(this.maxAttempts.getString().trim());
            DelayMs = Integer.parseInt(this.delayMs.getString().trim());
            MenuPath = this.menuPath.getString().trim();
            HoldLine = this.options.isSelected(0);
            UseProtect = this.options.isSelected(1);
            AutoGoKanata = this.options.isSelected(2);
            AutoBuyPhoi = this.options.isSelected(3);
            HuntFullSixX = this.options.isSelected(4);
            SellBadSixX = this.options.isSelected(5);
            HuntSixXByOption = this.options.isSelected(6);
            HuntSixXRequireAll = this.options.isSelected(7);
            HuntSixXNpcId = Integer.parseInt(this.huntNpcId.getString().trim());
            HuntSixXMenuPath = this.huntMenuPath.getString().trim();
            HuntSixXCardNeed = Integer.parseInt(this.huntCardNeed.getString().trim());
            HuntSixXRuleText = this.huntRuleText.getString().trim();

            if (TargetLevelX < 7) {
                TargetLevelX = 7;
            }
            if (TargetLevelX > 11) {
                TargetLevelX = 11;
            }
            if (MaxAttempts < 1) {
                MaxAttempts = 1;
            }
            if (DelayMs < 300) {
                DelayMs = 300;
            }
            if (MenuPath == null || MenuPath.length() == 0) {
                MenuPath = "5,0";
            }
            if (HuntSixXNpcId < 0) {
                HuntSixXNpcId = 0;
            }
            if (HuntSixXMenuPath == null || HuntSixXMenuPath.length() == 0) {
                HuntSixXMenuPath = "8,0";
            }
            if (HuntSixXNpcId == 0 && "6,0".equals(HuntSixXMenuPath)) {
                HuntSixXMenuPath = "8,0";
            }
            if (HuntSixXCardNeed < 1) {
                HuntSixXCardNeed = 1;
            }
            if (HuntSixXRuleText == null) {
                HuntSixXRuleText = "";
            }
            if (HuntSixXByOption && HuntSixXRuleText.length() == 0) {
                GameCanvas.setText("Nhập rule chỉ số 6x trước khi bật săn chỉ số");
                return false;
            }

            save();
            GameCanvas.setText("Đã lưu Auto Dung Hợp");
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Lỗi dữ liệu Auto Dung Hợp");
            return false;
        }
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
            dataout.writeInt(MainIndex);
            dataout.writeInt(TargetLevelX);
            dataout.writeInt(MaxAttempts);
            dataout.writeInt(DelayMs);
            dataout.writeUTF(MenuPath == null ? "" : MenuPath);
            dataout.writeBoolean(HoldLine);
            dataout.writeBoolean(UseProtect);
            dataout.writeBoolean(AutoGoKanata);
            dataout.writeBoolean(AutoBuyPhoi);
            dataout.writeBoolean(HuntFullSixX);
            dataout.writeBoolean(SellBadSixX);
            dataout.writeInt(HuntSixXNpcId);
            dataout.writeUTF(HuntSixXMenuPath == null ? "" : HuntSixXMenuPath);
            dataout.writeInt(HuntSixXCardNeed);
            dataout.writeBoolean(HuntSixXByOption);
            dataout.writeBoolean(HuntSixXRequireAll);
            dataout.writeUTF(HuntSixXRuleText == null ? "" : HuntSixXRuleText);
            dataout.flush();
            byte[] data = byteout.toByteArray();
            rs = RecordStore.openRecordStore(STORE_NAME, true);
            if (rs.getNumRecords() == 0) {
                rs.addRecord(data, 0, data.length);
            } else {
                rs.setRecord(1, data, 0, data.length);
            }
            loaded = true;
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
        if (loaded) {
            return;
        }
        RecordStore rs = null;
        ByteArrayInputStream bytein = null;
        DataInputStream datain = null;
        try {
            rs = RecordStore.openRecordStore(STORE_NAME, true);
            if (rs.getNumRecords() == 0) {
                return;
            }
            byte[] data = rs.getRecord(1);
            bytein = new ByteArrayInputStream(data);
            datain = new DataInputStream(bytein);
            MainIndex = datain.readInt();
            TargetLevelX = datain.readInt();
            MaxAttempts = datain.readInt();
            DelayMs = datain.readInt();
            MenuPath = datain.readUTF();
            HoldLine = datain.readBoolean();
            UseProtect = datain.readBoolean();
            AutoGoKanata = datain.readBoolean();
            try {
                AutoBuyPhoi = datain.readBoolean();
            } catch (Exception e) {
                AutoBuyPhoi = true;
            }
            try {
                HuntFullSixX = datain.readBoolean();
                SellBadSixX = datain.readBoolean();
                HuntSixXNpcId = datain.readInt();
                HuntSixXMenuPath = datain.readUTF();
                HuntSixXCardNeed = datain.readInt();
                try {
                    HuntSixXByOption = datain.readBoolean();
                    HuntSixXRequireAll = datain.readBoolean();
                    HuntSixXRuleText = datain.readUTF();
                } catch (Exception e) {
                    HuntSixXByOption = false;
                    HuntSixXRequireAll = true;
                    HuntSixXRuleText = "";
                }
            } catch (Exception e) {
                HuntFullSixX = false;
                SellBadSixX = true;
                HuntSixXNpcId = 0;
                HuntSixXMenuPath = "8,0";
                HuntSixXCardNeed = 10;
                HuntSixXByOption = false;
                HuntSixXRequireAll = true;
                HuntSixXRuleText = "";
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
                if (HuntSixXMenuPath == null || HuntSixXMenuPath.length() == 0) {
                    HuntSixXMenuPath = "8,0";
                }
                if (HuntSixXNpcId == 0 && "6,0".equals(HuntSixXMenuPath)) {
                    HuntSixXMenuPath = "8,0";
                }
                if (HuntSixXCardNeed < 1) {
                    HuntSixXCardNeed = 10;
                }
                if (HuntSixXRuleText == null) {
                    HuntSixXRuleText = "";
                }
                loaded = true;
            } catch (Exception e) {
            }
        }
    }
}
