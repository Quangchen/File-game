import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoUseItem implements CommandListener {

    private final Form form;
    private final Command save = new Command("Luu", Command.OK, 1);
    private final Command delete = new Command("Xoa", Command.SCREEN, 2);
    private final Command cancel = new Command("Huy", Command.BACK, 1);
    private final ChoiceGroup options;
    private final TextField itemIdTf;
    private final TextField delayTf;
    private final TextField shopTf;
    private final TextField buyCountTf;
    private final TextField bodyIndexTf;
    private int index;

    public FormAutoUseItem(int index) {
        this.index = index;
        int itemId = AutoUseItem.getItemIdAt(index);
        if (itemId <= 0) {
            itemId = 0;
        }

        String title = "Tu dung item";
        try {
            ItemTemplate template = ItemTemplateManager.get((short) itemId);
            if (template != null) {
                title = template.name + "(" + itemId + ")";
            }
        } catch (Exception e) {
        }

        this.form = new Form(title);
        this.options = new ChoiceGroup("Tuy chon", ChoiceGroup.MULTIPLE, new String[]{
            "Bat",
            "Tu mua khi thieu",
            "Check hieu ung"
        }, (Image[]) null);
        this.itemIdTf = new TextField("ID item", String.valueOf(itemId), 6, TextField.NUMERIC);
        this.delayTf = new TextField("Delay ms", String.valueOf(AutoUseItem.getDelayMs(index)), 6, TextField.NUMERIC);
        this.shopTf = new TextField("Type shop", String.valueOf(AutoUseItem.getShopId(index)), 4, TextField.NUMERIC);
        this.buyCountTf = new TextField("SL mua", String.valueOf(AutoUseItem.getBuyCount(index)), 5, TextField.NUMERIC);
        this.bodyIndexTf = new TextField("Body trong (-1 tat)", String.valueOf(AutoUseItem.getBodyIndex(index)), 3, TextField.ANY);
    }

    public void select() {
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoUseItem.isEnabled(this.index));
        this.options.setSelectedIndex(1, AutoUseItem.isAutoBuy(this.index));
        this.options.setSelectedIndex(2, AutoUseItem.isCheckEffect(this.index));
        this.form.append(this.options);
        this.form.append(this.itemIdTf);
        this.form.append(this.delayTf);
        this.form.append(this.shopTf);
        this.form.append(this.buyCountTf);
        this.form.append(this.bodyIndexTf);
        this.form.append("Body index >= 0: neu o body do trong thi tu mua/dung item.\nVi du body 10 trong: nhap 10.");
        this.form.addCommand(this.save);
        this.form.addCommand(this.delete);
        this.form.addCommand(this.cancel);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.save) {
            try {
                int itemId = Integer.parseInt(this.itemIdTf.getString());
                int delay = Integer.parseInt(this.delayTf.getString());
                int shop = Integer.parseInt(this.shopTf.getString());
                int count = Integer.parseInt(this.buyCountTf.getString());
                int body = Integer.parseInt(this.bodyIndexTf.getString());

                if (this.index < 0 || this.index >= AutoUseItem.getListIds().length) {
                    this.index = AutoUseItem.addDefault(itemId);
                }

                AutoUseItem.updateRule(this.index, itemId, this.options.isSelected(0), this.options.isSelected(1), this.options.isSelected(2), delay, shop, count, body);
                GameCanvas.setText("Da luu tu dung item");
            } catch (Exception e) {
                GameCanvas.setText("Loi cau hinh tu dung item");
                return;
            }
        } else if (command == this.delete) {
            AutoUseItem.removeAt(this.index);
            GameCanvas.setText("Da xoa item tu dung");
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }
}
