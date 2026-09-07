import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoLuckyCard implements CommandListener {

    private final Form form = new Form("Auto Lật Hình");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command chay = new javax.microedition.lcdui.Command("Chạy", 4, 1);
    private final javax.microedition.lcdui.Command dung = new javax.microedition.lcdui.Command("Dừng", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);
    private final ChoiceGroup options;
    private final TextField countField;
    private final TextField keepTypeField;
    private final TextField delayField;
    private final TextField buyCountField;

    public FormAutoLuckyCard() {
        AutoLuckyCard.load();
        this.options = new ChoiceGroup("Cài đặt", ChoiceGroup.MULTIPLE, new String[]{
            "Giữ đồ gộp",
            "Tự mua vé lật khi hết"
        }, (Image[]) null);
        this.countField = new TextField("Số lượng lật", String.valueOf(AutoLuckyCard.c), 8, TextField.NUMERIC);
        this.keepTypeField = new TextField("Type cần giữ (26,27)", AutoLuckyCard.keepTypes, 120, TextField.ANY);
        this.delayField = new TextField("Delay ms", String.valueOf(AutoLuckyCard.a), 8, TextField.NUMERIC);
        this.buyCountField = new TextField("SL mua vé khi hết", String.valueOf(AutoLuckyCard.getBuyTicketCount()), 8, TextField.NUMERIC);
    }

    public final void select() {
        AutoLuckyCard.load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoLuckyCard.keepStackable);
        this.options.setSelectedIndex(1, AutoLuckyCard.autoBuyTicket);
        this.countField.setString(String.valueOf(AutoLuckyCard.c));
        this.keepTypeField.setString(AutoLuckyCard.keepTypes);
        this.delayField.setString(String.valueOf(AutoLuckyCard.a));
        this.buyCountField.setString(String.valueOf(AutoLuckyCard.getBuyTicketCount()));
        this.form.append("Trạng thái: " + AutoLuckyCard.getStatusText() + "\n");
        this.form.append("Lệnh chat: lh hoặc lh100\n");
        this.form.append(this.countField);
        this.form.append(this.options);
        this.form.append(this.keepTypeField);
        this.form.append(this.delayField);
        this.form.append(this.buyCountField);
        this.form.addCommand(this.luu);
        this.form.addCommand(this.chay);
        this.form.addCommand(this.dung);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public final void commandAction(javax.microedition.lcdui.Command command, Displayable displayable) {
        if (command == this.luu || command == this.chay) {
            saveFromForm();
            GameCanvas.setText("Đã lưu auto lật hình");
            if (command == this.chay) {
                AutoLuckyCard.start();
            }
        } else if (command == this.dung) {
            AutoLuckyCard.stop();
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private void saveFromForm() {
        AutoLuckyCard.c = parseInt(this.countField.getString(), 50);
        AutoLuckyCard.keepStackable = this.options.isSelected(0);
        AutoLuckyCard.autoBuyTicket = this.options.isSelected(1);
        AutoLuckyCard.keepTypes = this.keepTypeField.getString().trim();
        AutoLuckyCard.a = (long) parseInt(this.delayField.getString(), 50);
        AutoLuckyCard.buyTicketCount = parseInt(this.buyCountField.getString(), 5000);
        if (AutoLuckyCard.c <= 0) {
            AutoLuckyCard.c = 50;
        }
        if (AutoLuckyCard.a < 1L) {
            AutoLuckyCard.a = 1L;
        }
        if (AutoLuckyCard.buyTicketCount <= 0) {
            AutoLuckyCard.buyTicketCount = 5000;
        }
        AutoLuckyCard.save();
    }

    private static int parseInt(String text, int def) {
        try {
            return Integer.parseInt(text.trim());
        } catch (Exception e) {
            return def;
        }
    }
}
