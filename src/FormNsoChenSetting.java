import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormNsoChenSetting implements CommandListener {
    private static final String STORE_EXACT_DELETE = "NsoChenExactDel";
    public static boolean exactDelete = RMS.d(STORE_EXACT_DELETE) != 0;

    private final Form form = new Form("Cài đặt NSOChen");
    private final Command save = new Command("Lưu", Command.OK, 1);
    private final Command cancel = new Command("Hủy", Command.BACK, 1);
    private final ChoiceGroup options;
    private final TextField useAllDelay;

    public FormNsoChenSetting() {
        this.options = new ChoiceGroup("Tùy chọn", ChoiceGroup.MULTIPLE, new String[]{
            "Lọc chính xác",
            "Tự dùng Ngũ Hành Hoa",
            "Tele mục tiêu"
        }, (Image[]) null);
        this.useAllDelay = new TextField("Delay Mở All (ms)", String.valueOf(UseAllItem.getDelayMs()), 6, TextField.NUMERIC);
    }

    public void select() {
        load();
        AutoNguHanhHoa.load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, exactDelete);
        this.options.setSelectedIndex(1, AutoNguHanhHoa.isEnabled());
        this.options.setSelectedIndex(2, Code.teleTarget);
        this.useAllDelay.setString(String.valueOf(UseAllItem.getDelayMs()));
        this.form.append(this.options);
        this.form.append(this.useAllDelay);
        this.form.addCommand(this.save);
        this.form.addCommand(this.cancel);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.save) {
            exactDelete = this.options.isSelected(0);
            AutoNguHanhHoa.setEnabled(this.options.isSelected(1));
            Code.setTeleTarget(this.options.isSelected(2));
            if (!UseAllItem.setDelayText(this.useAllDelay.getString())) {
                UseAllItem.setDelayMs(UseAllItem.getDelayMs());
            }
            save();
            GameCanvas.setText("Đã lưu cài đặt NSOChen");
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    public static boolean isExactDelete() {
        return exactDelete;
    }

    public static void load() {
        int value = RMS.d(STORE_EXACT_DELETE);
        exactDelete = value != 0;
    }

    private static void save() {
        RMS.writeRecord(STORE_EXACT_DELETE, exactDelete ? 1 : 0);
    }
}
