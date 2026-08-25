import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoRuocDen implements CommandListener {

    private final Form form = new Form("Auto Rước Đèn");
    private final Command save = new Command("Lưu", Command.OK, 1);
    private final Command run = new Command("Chạy", Command.OK, 2);
    private final Command stop = new Command("Dừng", Command.OK, 3);
    private final Command cancel = new Command("Hủy", Command.BACK, 1);
    private final ChoiceGroup options;
    private final TextField mapId;
    private final TextField zoneId;
    private final TextField hour;
    private final TextField minute;
    private final TextField matchCount;
    private final TextField delayMs;
    private final TextField maxEscortMinute;

    public FormAutoRuocDen() {
        AutoRuocDen.load();
        this.options = new ChoiceGroup("Cài đặt", ChoiceGroup.MULTIPLE, new String[]{
            "Tự đi khi đến giờ",
            "Lặp khi còn hộp diêm",
            "Nhặt phôi rơi"
        }, (Image[]) null);
        this.mapId = new TextField("Map rước (gốc 26)", String.valueOf(AutoRuocDen.MapID), 6, TextField.NUMERIC);
        this.zoneId = new TextField("Khu (-1 = giữ nguyên)", String.valueOf(AutoRuocDen.ZoneID), 4, TextField.ANY);
        this.hour = new TextField("Giờ tự đi", String.valueOf(AutoRuocDen.Hour), 4, TextField.NUMERIC);
        this.minute = new TextField("Phút tự đi", String.valueOf(AutoRuocDen.Minute), 4, TextField.NUMERIC);
        this.matchCount = new TextField("Số hộp diêm/lượt", String.valueOf(AutoRuocDen.MatchCount), 6, TextField.NUMERIC);
        this.delayMs = new TextField("Delay NPC ms", String.valueOf(AutoRuocDen.DelayMs), 6, TextField.NUMERIC);
        this.maxEscortMinute = new TextField("Timeout rước phút", String.valueOf(AutoRuocDen.MaxEscortMinute), 4, TextField.NUMERIC);
    }

    public void select() {
        AutoRuocDen.load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoRuocDen.AutoTime);
        this.options.setSelectedIndex(1, AutoRuocDen.RepeatWhileHasMatch);
        this.options.setSelectedIndex(2, AutoRuocDen.PickDroppedPhoi);
        this.mapId.setString(String.valueOf(AutoRuocDen.MapID));
        this.zoneId.setString(String.valueOf(AutoRuocDen.ZoneID));
        this.hour.setString(String.valueOf(AutoRuocDen.Hour));
        this.minute.setString(String.valueOf(AutoRuocDen.Minute));
        this.matchCount.setString(String.valueOf(AutoRuocDen.MatchCount));
        this.delayMs.setString(String.valueOf(AutoRuocDen.DelayMs));
        this.maxEscortMinute.setString(String.valueOf(AutoRuocDen.MaxEscortMinute));

        this.form.append("Trạng thái: " + AutoRuocDen.getStatusText() + "\n");
        this.form.append("Map hợp lệ: 2,3,26,28,39,71. Mặc định dùng map 26.\n");
        this.form.append("NPC lồng đèn 34, menu 0, item hộp diêm 310.\n");
        this.form.append(this.options);
        this.form.append(this.mapId);
        this.form.append(this.zoneId);
        this.form.append(this.hour);
        this.form.append(this.minute);
        this.form.append(this.matchCount);
        this.form.append(this.delayMs);
        this.form.append(this.maxEscortMinute);
        this.form.addCommand(this.save);
        this.form.addCommand(this.run);
        this.form.addCommand(this.stop);
        this.form.addCommand(this.cancel);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.save || command == this.run) {
            if (!readAndSave()) {
                return;
            }
        }

        if (command == this.run) {
            AutoRuocDen.start();
        } else if (command == this.stop) {
            AutoRuocDen.stop();
        } else if (command == this.save) {
            GameCanvas.setText("Đã lưu auto rước đèn");
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            AutoRuocDen.AutoTime = this.options.isSelected(0);
            AutoRuocDen.RepeatWhileHasMatch = this.options.isSelected(1);
            AutoRuocDen.PickDroppedPhoi = this.options.isSelected(2);
            AutoRuocDen.MapID = parseInt(this.mapId.getString(), 26);
            AutoRuocDen.ZoneID = parseInt(this.zoneId.getString(), -1);
            AutoRuocDen.Hour = parseInt(this.hour.getString(), 20);
            AutoRuocDen.Minute = parseInt(this.minute.getString(), 0);
            AutoRuocDen.MatchCount = parseInt(this.matchCount.getString(), 1);
            AutoRuocDen.DelayMs = parseInt(this.delayMs.getString(), 700);
            AutoRuocDen.MaxEscortMinute = parseInt(this.maxEscortMinute.getString(), 10);
            AutoRuocDen.save();
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Lỗi dữ liệu auto rước đèn");
            return false;
        }
    }

    private static int parseInt(String text, int def) {
        try {
            return Integer.parseInt(text.trim());
        } catch (Exception e) {
            return def;
        }
    }
}
