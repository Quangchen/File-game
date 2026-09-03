import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoHalloween implements CommandListener {

    private final Form form = new Form("Auto H\u00f3a Trang");
    private final Command save = new Command("L\u01b0u", Command.OK, 1);
    private final Command run = new Command("Ch\u1ea1y", Command.OK, 2);
    private final Command stop = new Command("D\u1eebng", Command.OK, 3);
    private final Command cancel = new Command("H\u1ee7y", Command.BACK, 1);
    private final ChoiceGroup options;
    private final TextField partners;
    private final TextField hour;
    private final TextField minute;
    private final TextField delayMs;

    public FormAutoHalloween() {
        AutoHalloween.load();
        this.options = new ChoiceGroup("C\u00e0i \u0111\u1eb7t", ChoiceGroup.MULTIPLE, new String[]{
            "T\u1ef1 \u0111i khi \u0111\u1ebfn gi\u1edd",
            "Nh\u00f3m tr\u01b0\u1edfng",
            "T\u1ef1 l\u1eadt th\u1ebb th\u01b0\u1edfng",
            "Nh\u1eb7t m\u1eb7t n\u1ea1 r\u01a1i"
        }, (Image[]) null);
        this.partners = new TextField("Acc ph\u1ee5 / t\u00ean tr\u01b0\u1edfng", AutoHalloween.PartnerNames, 96, TextField.ANY);
        this.hour = new TextField("Gi\u1edd t\u1ef1 \u0111i", String.valueOf(AutoHalloween.Hour), 4, TextField.NUMERIC);
        this.minute = new TextField("Ph\u00fat t\u1ef1 \u0111i", String.valueOf(AutoHalloween.Minute), 4, TextField.NUMERIC);
        this.delayMs = new TextField("Delay thao t\u00e1c ms", String.valueOf(AutoHalloween.DelayMs), 6, TextField.NUMERIC);
    }

    public void select() {
        AutoHalloween.load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoHalloween.AutoTime);
        this.options.setSelectedIndex(1, AutoHalloween.Leader);
        this.options.setSelectedIndex(2, AutoHalloween.AutoFlip);
        this.options.setSelectedIndex(3, AutoHalloween.PickMask);
        this.partners.setString(AutoHalloween.PartnerNames == null ? "" : AutoHalloween.PartnerNames);
        this.hour.setString(String.valueOf(AutoHalloween.Hour));
        this.minute.setString(String.valueOf(AutoHalloween.Minute));
        this.delayMs.setString(String.valueOf(AutoHalloween.DelayMs));

        this.form.append("Tr\u1ea1ng th\u00e1i: " + AutoHalloween.getStatusText() + "\n");
        this.form.append("NPC Tabemono(4), gi\u1edd 19-23, c\u1ea7n nh\u00f3m t\u1eeb 2 acc.\n");
        this.form.append("M\u1ed7i acc c\u1ea7n Th\u01b0 M\u1eddi 1071 v\u00e0 m\u1eb7t n\u1ea1 814-818.\n");
        this.form.append("Tr\u01b0\u1edfng nh\u1eadp acc ph\u1ee5: acc1,acc2. Th\u00e0nh vi\u00ean nh\u1eadp t\u00ean tr\u01b0\u1edfng ho\u1eb7c \u0111\u1ec3 tr\u1ed1ng.\n");
        this.form.append(this.options);
        this.form.append(this.partners);
        this.form.append(this.hour);
        this.form.append(this.minute);
        this.form.append(this.delayMs);
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
            AutoHalloween.start();
        } else if (command == this.stop) {
            AutoHalloween.stop();
        } else if (command == this.save) {
            GameCanvas.setText("\u0110\u00e3 l\u01b0u auto h\u00f3a trang");
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            AutoHalloween.AutoTime = this.options.isSelected(0);
            AutoHalloween.Leader = this.options.isSelected(1);
            AutoHalloween.AutoFlip = this.options.isSelected(2);
            AutoHalloween.PickMask = this.options.isSelected(3);
            AutoHalloween.PartnerNames = this.partners.getString().trim();
            AutoHalloween.Hour = parseInt(this.hour.getString(), 19);
            AutoHalloween.Minute = parseInt(this.minute.getString(), 0);
            AutoHalloween.DelayMs = parseInt(this.delayMs.getString(), 1200);
            AutoHalloween.save();
            return true;
        } catch (Exception e) {
            GameCanvas.setText("L\u1ed7i d\u1eef li\u1ec7u auto h\u00f3a trang");
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
