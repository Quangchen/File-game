import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoGiftCode implements CommandListener {

    private final Form form = new Form("Auto Giftcode");
    private final Command save = new Command("Luu", Command.OK, 1);
    private final Command run = new Command("Chay", Command.OK, 2);
    private final Command stop = new Command("Dung", Command.OK, 3);
    private final Command clear = new Command("Xoa lich su", Command.OK, 4);
    private final Command cancel = new Command("Huy", Command.BACK, 1);
    private final ChoiceGroup options;
    private final TextField mapId;
    private final TextField zoneId;
    private final TextField npcId;
    private final TextField menuPath;
    private final TextField delayMs;

    public FormAutoGiftCode() {
        this.options = new ChoiceGroup("Cai dat", ChoiceGroup.MULTIPLE, new String[]{
            "Tu chay sau login",
            "Bo qua code da thu",
            "Dung neu full hanh trang"
        }, (Image[]) null);
        this.mapId = new TextField("Map Rakkii", String.valueOf(AutoGiftCode.MapID), 6, TextField.NUMERIC);
        this.zoneId = new TextField("Khu (-1 = khong doi)", String.valueOf(AutoGiftCode.KhuID), 4, TextField.ANY);
        this.npcId = new TextField("NPC ID", String.valueOf(AutoGiftCode.NpcID), 4, TextField.NUMERIC);
        this.menuPath = new TextField("Menu path", AutoGiftCode.MenuPath, 32, TextField.ANY);
        this.delayMs = new TextField("Delay ms", String.valueOf(AutoGiftCode.DelayMs), 6, TextField.NUMERIC);
    }

    public void select() {
        AutoGiftCode.load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoGiftCode.AutoAfterLogin);
        this.options.setSelectedIndex(1, AutoGiftCode.SkipAttempted);
        this.options.setSelectedIndex(2, AutoGiftCode.StopWhenBagFull);
        this.mapId.setString(String.valueOf(AutoGiftCode.MapID));
        this.zoneId.setString(String.valueOf(AutoGiftCode.KhuID));
        this.npcId.setString(String.valueOf(AutoGiftCode.NpcID));
        this.menuPath.setString(AutoGiftCode.MenuPath);
        this.delayMs.setString(String.valueOf(AutoGiftCode.DelayMs));

        this.form.append("Trang thai: " + AutoGiftCode.getStatusText() + "\n");
        this.form.append("List code doc tu src/giftcodes.txt: " + AutoGiftCode.getCodeCount() + " code\n");
        this.form.append("Mac dinh: NPC 30, menu 1, map 72.\n");
        this.form.append(this.options);
        this.form.append(this.mapId);
        this.form.append(this.zoneId);
        this.form.append(this.npcId);
        this.form.append(this.menuPath);
        this.form.append(this.delayMs);
        this.form.addCommand(this.save);
        this.form.addCommand(this.run);
        this.form.addCommand(this.stop);
        this.form.addCommand(this.clear);
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
            AutoGiftCode.start();
        } else if (command == this.stop) {
            AutoGiftCode.stop();
        } else if (command == this.clear) {
            AutoGiftCode.clearHistory();
        } else if (command == this.save) {
            GameCanvas.setText("Da luu Auto Giftcode");
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            AutoGiftCode.AutoAfterLogin = this.options.isSelected(0);
            AutoGiftCode.SkipAttempted = this.options.isSelected(1);
            AutoGiftCode.StopWhenBagFull = this.options.isSelected(2);
            AutoGiftCode.MapID = Integer.parseInt(this.mapId.getString().trim());
            AutoGiftCode.KhuID = Integer.parseInt(this.zoneId.getString().trim());
            AutoGiftCode.NpcID = Integer.parseInt(this.npcId.getString().trim());
            AutoGiftCode.MenuPath = this.menuPath.getString().trim();
            AutoGiftCode.DelayMs = Integer.parseInt(this.delayMs.getString().trim());

            if (AutoGiftCode.DelayMs < 1000) {
                AutoGiftCode.DelayMs = 1000;
            }
            if (AutoGiftCode.MenuPath == null || AutoGiftCode.MenuPath.length() == 0) {
                AutoGiftCode.MenuPath = "1";
            }
            if (AutoGiftCode.NpcID < 0) {
                AutoGiftCode.NpcID = 30;
            }

            AutoGiftCode.save();
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Loi du lieu Auto Giftcode");
            return false;
        }
    }
}
