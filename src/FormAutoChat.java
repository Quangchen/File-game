import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoChat implements CommandListener {

    private final Form form = new Form("Auto Chat");
    private final Command save = new Command("Luu", Command.OK, 1);
    private final Command run = new Command("Bat", Command.OK, 2);
    private final Command sendNow = new Command("Gui ngay", Command.OK, 3);
    private final Command cancel = new Command("Huy", Command.BACK, 1);
    private final ChoiceGroup options;
    private final ChoiceGroup channel;
    private final TextField delaySeconds;
    private final TextField messages;

    public FormAutoChat() {
        this.options = new ChoiceGroup("Cai dat", ChoiceGroup.MULTIPLE, new String[]{
            "Bat auto chat",
            "Random noi dung",
            "Tam dung khi dang mo UI"
        }, (Image[]) null);
        this.channel = new ChoiceGroup("Kenh chat", ChoiceGroup.EXCLUSIVE, new String[]{
            "Cong cong",
            "The gioi"
        }, (Image[]) null);
        this.delaySeconds = new TextField("Delay giay", String.valueOf(AutoChat.delaySeconds), 6, TextField.NUMERIC);
        this.messages = new TextField("Noi dung, cach nhau bang |", AutoChat.messagesText, 1024, TextField.ANY);
    }

    public void select() {
        AutoChat.load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoChat.enabled);
        this.options.setSelectedIndex(1, AutoChat.randomMessage);
        this.options.setSelectedIndex(2, AutoChat.pauseWhenBusy);
        this.channel.setSelectedIndex(AutoChat.channel == AutoChat.CHANNEL_WORLD ? 1 : 0, true);
        this.delaySeconds.setString(String.valueOf(AutoChat.delaySeconds));
        this.messages.setString(AutoChat.messagesText);

        this.form.append("Trang thai: " + AutoChat.getStatusText() + "\n");
        this.form.append("The gioi ton 5 luong/lan, delay toi thieu 60s.\n");
        this.form.append(this.options);
        this.form.append(this.channel);
        this.form.append(this.delaySeconds);
        this.form.append(this.messages);
        this.form.addCommand(this.save);
        this.form.addCommand(this.run);
        this.form.addCommand(this.sendNow);
        this.form.addCommand(this.cancel);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.save || command == this.run || command == this.sendNow) {
            if (!readAndSave()) {
                return;
            }
        }

        if (command == this.run) {
            AutoChat.enabled = true;
            AutoChat.save();
            GameScr.chatPopup("Auto chat: Bat");
        } else if (command == this.sendNow) {
            AutoChat.sendNow();
        } else if (command == this.save) {
            GameCanvas.setText("Da luu Auto Chat");
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    private boolean readAndSave() {
        try {
            AutoChat.enabled = this.options.isSelected(0);
            AutoChat.randomMessage = this.options.isSelected(1);
            AutoChat.pauseWhenBusy = this.options.isSelected(2);
            AutoChat.channel = this.channel.getSelectedIndex() == 1 ? AutoChat.CHANNEL_WORLD : AutoChat.CHANNEL_PUBLIC;
            AutoChat.delaySeconds = Integer.parseInt(this.delaySeconds.getString().trim());
            AutoChat.messagesText = this.messages.getString();
            AutoChat.save();
            return true;
        } catch (Exception e) {
            GameCanvas.setText("Loi du lieu Auto Chat");
            return false;
        }
    }
}
