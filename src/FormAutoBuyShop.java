import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoBuyShop implements CommandListener {

    private final Form form = new Form("Tự Mua Shop");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);
    private final ChoiceGroup options;
    private final TextField itemIds;
    private final TextField shopIds;
    private final TextField buyCounts;
    private final TextField delayMs;

    public FormAutoBuyShop() {
        this.options = new ChoiceGroup("Cài đặt", 2, new String[]{
            "Bật tự mua shop",
            "Tự về làng/trường để mua",
            "Chỉ mua khi còn ô trống"
        }, (Image[]) null);
        this.itemIds = new TextField("ID item (545,285,444)", AutoBuyShop.itemIds, 500, TextField.ANY);
        this.shopIds = new TextField("ID shop (3,14,14)", AutoBuyShop.shopIds, 500, TextField.ANY);
        this.buyCounts = new TextField("SL mua mỗi lần (1,4,3)", AutoBuyShop.buyCounts, 500, TextField.ANY);
        this.delayMs = new TextField("Delay kiểm tra ms", String.valueOf(AutoBuyShop.delayMs), 8, TextField.NUMERIC);
    }

    public final void select() {
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoBuyShop.enabled);
        this.options.setSelectedIndex(1, AutoBuyShop.allowGoBuy);
        this.options.setSelectedIndex(2, AutoBuyShop.requireEmptySlot);
        this.itemIds.setString(AutoBuyShop.itemIds);
        this.shopIds.setString(AutoBuyShop.shopIds);
        this.buyCounts.setString(AutoBuyShop.buyCounts);
        this.delayMs.setString(String.valueOf(AutoBuyShop.delayMs));
        this.form.append(this.options);
        this.form.append(this.itemIds);
        this.form.append(this.shopIds);
        this.form.append(this.buyCounts);
        this.form.append(this.delayMs);
        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public final void commandAction(javax.microedition.lcdui.Command command, Displayable displayable) {
        if (command == this.luu) {
            try {
                AutoBuyShop.enabled = this.options.isSelected(0);
                AutoBuyShop.allowGoBuy = this.options.isSelected(1);
                AutoBuyShop.requireEmptySlot = this.options.isSelected(2);
                AutoBuyShop.itemIds = this.itemIds.getString().trim();
                AutoBuyShop.shopIds = this.shopIds.getString().trim();
                AutoBuyShop.buyCounts = this.buyCounts.getString().trim();
                AutoBuyShop.delayMs = Integer.parseInt(this.delayMs.getString().trim());
                if (AutoBuyShop.delayMs < 1000) {
                    AutoBuyShop.delayMs = 1000;
                }

                if (!AutoBuyShop.isConfigValid()) {
                    GameCanvas.setText("Lỗi: 3 dòng item/shop/sl phải cùng số lượng");
                    Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
                    return;
                }

                AutoBuyShop.save();
                GameCanvas.setText("Đã lưu tự mua shop: " + AutoBuyShop.getConfigSummary());
            } catch (Exception e) {
                GameCanvas.setText("Lỗi dữ liệu tự mua shop");
            }
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }
}
