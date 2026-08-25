import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoViThu implements CommandListener {

    private final Form form = new Form("Auto Vi Thu");
    private final Command save = new Command("Luu", Command.OK, 1);
    private final Command run = new Command("Chay", Command.OK, 2);
    private final Command stop = new Command("Dung", Command.OK, 3);
    private final Command cancel = new Command("Huy", Command.BACK, 1);
    private final ChoiceGroup options;
    private final TextField hour;
    private final TextField minute;
    private final TextField openEggDelay;

    public FormAutoViThu() {
        AutoViThu.load();
        this.options = new ChoiceGroup("Cai dat", ChoiceGroup.MULTIPLE, new String[]{
            "Tu di khi den gio",
            "Danh boss 237 (vao 169 danh)",
            "Nhat trung",
            "Tu mo trung sau hang",
            "Mo tiep trung phu vinh vien",
            "Xoa vi thu/trung co han"
        }, (Image[]) null);
        this.hour = new TextField("Gio tu di", String.valueOf(AutoViThu.Hour), 4, TextField.NUMERIC);
        this.minute = new TextField("Phut tu di", String.valueOf(AutoViThu.Minute), 4, TextField.NUMERIC);
        this.openEggDelay = new TextField("Delay mo trung ms", String.valueOf(AutoViThu.OpenEggDelayMs), 6, TextField.NUMERIC);
    }

    public void select() {
        AutoViThu.load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoViThu.AutoTime);
        this.options.setSelectedIndex(1, AutoViThu.AttackBoss);
        this.options.setSelectedIndex(2, AutoViThu.PickEgg);
        this.options.setSelectedIndex(3, AutoViThu.AutoOpenEggAfterHang);
        this.options.setSelectedIndex(4, AutoViThu.OpenForeverChildEgg);
        this.options.setSelectedIndex(5, AutoViThu.DeleteTimedEgg);
        this.hour.setString(String.valueOf(AutoViThu.Hour));
        this.minute.setString(String.valueOf(AutoViThu.Minute));
        this.openEggDelay.setString(String.valueOf(AutoViThu.OpenEggDelayMs));

        this.form.append("Trang thai: " + AutoViThu.getStatusText() + "\n");
        this.form.append("Che do: " + (AutoViThu.AttackBoss ? "vao 169 danh boss" : "vao 169 cho boss chet") + "\n");
        this.form.append("Can giu: 1 Vi Thu Lenh(983), 5 day Nenshi(946).\n");
        this.form.append("Neu thieu se mua o shop type 8. Trung Vi Thu id 993.\n");
        this.form.append("Mo trung: 993 -> 989-992, chi giu vinh vien; tick xoa moi xoa do co han.\n");
        this.form.append("Bo tick Danh boss: van vao map 169 nhung chi dung cho boss chet roi nhat trung.\n");
        this.form.append(this.options);
        this.form.append(this.hour);
        this.form.append(this.minute);
        this.form.append(this.openEggDelay);
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
            AutoViThu.start();
        } else if (command == this.stop) {
            AutoViThu.stop();
        } else if (command == this.save) {
            GameCanvas.setText("Da luu auto Vi Thu");
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            AutoViThu.AutoTime = this.options.isSelected(0);
            AutoViThu.AttackBoss = this.options.isSelected(1);
            AutoViThu.PickEgg = this.options.isSelected(2);
            AutoViThu.AutoOpenEggAfterHang = this.options.isSelected(3);
            AutoViThu.OpenForeverChildEgg = this.options.isSelected(4);
            AutoViThu.DeleteTimedEgg = this.options.isSelected(5);
            AutoViThu.Hour = parseInt(this.hour.getString(), 21);
            AutoViThu.Minute = parseInt(this.minute.getString(), 45);
            AutoViThu.OpenEggDelayMs = parseInt(this.openEggDelay.getString(), 1500);
            AutoViThu.save();
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Loi du lieu auto Vi Thu");
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
