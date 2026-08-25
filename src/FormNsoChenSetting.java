import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;

public final class FormNsoChenSetting implements CommandListener {
    private static final String STORE_EXACT_DELETE = "NsoChenExactDel";
    public static boolean exactDelete = RMS.d(STORE_EXACT_DELETE) != 0;

    private final Form form = new Form("Cài đặt NSOChen");
    private final Command save = new Command("Lưu", Command.OK, 1);
    private final Command cancel = new Command("Hủy", Command.BACK, 1);
    private final ChoiceGroup options;

    public FormNsoChenSetting() {
        this.options = new ChoiceGroup("Tùy chọn", ChoiceGroup.MULTIPLE, new String[]{
            "Lọc chính xác"
        }, (Image[]) null);
    }

    public void select() {
        load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, exactDelete);
        this.form.append("Bật Lọc chính xác: del chậm hơn nhưng an toàn khi mạng lag.\n");
        this.form.append("Tắt Lọc chính xác: del siêu tốc, có khả năng xóa nhầm nếu lag hoặc hành trang đổi slot.\n");
        this.form.append(this.options);
        this.form.addCommand(this.save);
        this.form.addCommand(this.cancel);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.save) {
            exactDelete = this.options.isSelected(0);
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
