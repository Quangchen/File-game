import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoLongDen implements CommandListener {

    private final Form form = new Form("Auto Đổi Lồng Đèn");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);
    private final ChoiceGroup options;
    private final ChoiceGroup currency;
    private final TextField ruleText;
    private final TextField delayMs;
    private final TextField minEmptySlot;
    private final TextField menuPathXu;
    private final TextField menuPathLuong;

    public FormAutoLongDen() {
        AutoDoiLongDen.load();
        this.options = new ChoiceGroup("Cài đặt", ChoiceGroup.MULTIPLE, new String[]{
            "Bật auto đổi lồng đèn",
            "Săn chỉ số",
            "Tự xóa nếu không đạt",
            "Cần đủ tất cả chỉ số",
            "Tự về làng/trường nếu không thấy NPC"
        }, (Image[])null);
        this.currency = new ChoiceGroup("Kiểu đổi", ChoiceGroup.EXCLUSIVE, new String[]{
            "Đổi bằng xu",
            "Đổi bằng lượng"
        }, (Image[])null);
        this.ruleText = new TextField("Rule CS (64:6,64:58 hoac 6,58)", AutoDoiLongDen.ruleText, 250, TextField.ANY);
        this.delayMs = new TextField("Delay đổi ms", String.valueOf(AutoDoiLongDen.delayMs), 8, TextField.NUMERIC);
        this.minEmptySlot = new TextField("Giữ ô trống (>=1)", String.valueOf(AutoDoiLongDen.minEmptySlot), 4, TextField.NUMERIC);
        this.menuPathXu = new TextField("Menu xu", AutoDoiLongDen.menuPathXu, 20, TextField.ANY);
        this.menuPathLuong = new TextField("Menu lượng", AutoDoiLongDen.menuPathLuong, 20, TextField.ANY);
    }

    public final void select() {
        AutoDoiLongDen.load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoDoiLongDen.enabled || AutoDoiLongDen.isRunning());
        this.options.setSelectedIndex(1, AutoDoiLongDen.huntOption);
        this.options.setSelectedIndex(2, AutoDoiLongDen.autoDeleteFail);
        this.options.setSelectedIndex(3, AutoDoiLongDen.requireAll);
        this.options.setSelectedIndex(4, AutoDoiLongDen.autoGoNpc);
        this.currency.setSelectedIndex(AutoDoiLongDen.currencyMode == 1 ? 1 : 0, true);
        this.ruleText.setString(AutoDoiLongDen.ruleText);
        this.delayMs.setString(String.valueOf(AutoDoiLongDen.delayMs));
        this.minEmptySlot.setString(String.valueOf(AutoDoiLongDen.minEmptySlot));
        this.menuPathXu.setString(AutoDoiLongDen.menuPathXu);
        this.menuPathLuong.setString(AutoDoiLongDen.menuPathLuong);
        this.form.append("Trạng thái: " + AutoDoiLongDen.getStatusText() + "\n");
        this.form.append("Cần lồng đèn 568-571 + phôi 1221. Xu: 50k, lượng: 1000.\n");
        this.form.append("CS lồng đèn: 0,1,2,3,4,5,6,8,9,57,58,87.\n");
        this.form.append("CS co the xuat hien: 0,1,2,3,4,5,6,8,9,57,58,87.\n");
        this.form.append("Nhap 64:6,64:58 de check dung cap option 64 va param 6/58.\n");
        this.form.append(this.options);
        this.form.append(this.currency);
        this.form.append(this.ruleText);
        this.form.append(this.delayMs);
        this.form.append(this.minEmptySlot);
        this.form.append(this.menuPathXu);
        this.form.append(this.menuPathLuong);
        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public final void commandAction(javax.microedition.lcdui.Command command, Displayable displayable) {
        if (command == this.luu) {
            try {
                AutoDoiLongDen.enabled = this.options.isSelected(0);
                AutoDoiLongDen.huntOption = this.options.isSelected(1);
                AutoDoiLongDen.autoDeleteFail = this.options.isSelected(2);
                AutoDoiLongDen.requireAll = this.options.isSelected(3);
                AutoDoiLongDen.autoGoNpc = this.options.isSelected(4);
                AutoDoiLongDen.currencyMode = this.currency.isSelected(1) ? 1 : 0;
                AutoDoiLongDen.ruleText = this.ruleText.getString().trim();
                AutoDoiLongDen.delayMs = parseInt(this.delayMs.getString(), 1200);
                AutoDoiLongDen.minEmptySlot = parseInt(this.minEmptySlot.getString(), 1);
                AutoDoiLongDen.menuPathXu = this.menuPathXu.getString().trim();
                AutoDoiLongDen.menuPathLuong = this.menuPathLuong.getString().trim();
                AutoDoiLongDen.save();
                if (AutoDoiLongDen.enabled) {
                    AutoDoiLongDen.start();
                } else if (AutoDoiLongDen.isRunning()) {
                    AutoDoiLongDen.stop();
                }
                GameCanvas.setText("Đã lưu auto đổi lồng đèn");
            } catch (Exception e) {
                GameCanvas.setText("Lỗi dữ liệu auto đổi lồng đèn");
            }
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private static int parseInt(String text, int def) {
        try {
            return Integer.parseInt(text.trim());
        } catch (Exception e) {
            return def;
        }
    }
}
