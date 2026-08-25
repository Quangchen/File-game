import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class FormAutoCauCa implements CommandListener {

    private static final String STORE_NAME = "AutoCauCaCfg";

    public static boolean AutoBuyRod = true;
    public static boolean AutoBuyBait = true;
    public static int DelayMs = 1000;
    public static int MinEmptySlot = 1;
    public static int RodItemId = 597;
    public static int RodShopId = 14;
    public static int RodBuyCount = 10;
    public static int BaitItemId = 603;
    public static int BaitShopId = 8;
    public static int BaitBuyCount = 50;
    public static int BaitItemId2 = 602;
    public static int BaitShopId2 = 9;
    public static int BaitBuyCount2 = 50;
    public static int FishX = -1;
    public static int FishY = -1;

    private static boolean loaded = false;

    private final Form form = new Form("Auto Câu Cá");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);
    private final ChoiceGroup options;
    private final TextField delayMs;
    private final TextField minEmptySlot;
    private final TextField rodItemId;
    private final TextField rodShopId;
    private final TextField rodBuyCount;
    private final TextField baitItemId;
    private final TextField baitShopId;
    private final TextField baitBuyCount;
    private final TextField baitItemId2;
    private final TextField baitShopId2;
    private final TextField baitBuyCount2;
    private final TextField fishX;
    private final TextField fishY;

    public FormAutoCauCa() {
        load();
        this.options = new ChoiceGroup("Cài đặt", 2, new String[]{
            "Tự mua cần",
            "Tự mua mồi"
        }, (Image[]) null);
        this.delayMs = new TextField("Delay câu ms (server 1000)", String.valueOf(DelayMs), 8, TextField.NUMERIC);
        this.minEmptySlot = new TextField("Giữ ô trống", String.valueOf(MinEmptySlot), 4, TextField.NUMERIC);
        this.rodItemId = new TextField("ID cần", String.valueOf(RodItemId), 8, TextField.NUMERIC);
        this.rodShopId = new TextField("Shop cần", String.valueOf(RodShopId), 8, TextField.NUMERIC);
        this.rodBuyCount = new TextField("SL cần mua", String.valueOf(RodBuyCount), 8, TextField.NUMERIC);
        this.baitItemId = new TextField("ID mồi 1", String.valueOf(BaitItemId), 8, TextField.NUMERIC);
        this.baitShopId = new TextField("Shop mồi 1", String.valueOf(BaitShopId), 8, TextField.NUMERIC);
        this.baitBuyCount = new TextField("SL mồi 1", String.valueOf(BaitBuyCount), 8, TextField.NUMERIC);
        this.baitItemId2 = new TextField("ID mồi 2", String.valueOf(BaitItemId2), 8, TextField.NUMERIC);
        this.baitShopId2 = new TextField("Shop mồi 2", String.valueOf(BaitShopId2), 8, TextField.NUMERIC);
        this.baitBuyCount2 = new TextField("SL mồi 2", String.valueOf(BaitBuyCount2), 8, TextField.NUMERIC);
        this.fishX = new TextField("X câu (-1 tự tìm)", String.valueOf(FishX), 8, TextField.ANY);
        this.fishY = new TextField("Y câu (-1 tự tìm)", String.valueOf(FishY), 8, TextField.ANY);
    }

    public final void select() {
        load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, AutoBuyRod);
        this.options.setSelectedIndex(1, AutoBuyBait);
        this.delayMs.setString(String.valueOf(DelayMs));
        this.minEmptySlot.setString(String.valueOf(MinEmptySlot));
        this.rodItemId.setString(String.valueOf(RodItemId));
        this.rodShopId.setString(String.valueOf(RodShopId));
        this.rodBuyCount.setString(String.valueOf(RodBuyCount));
        this.baitItemId.setString(String.valueOf(BaitItemId));
        this.baitShopId.setString(String.valueOf(BaitShopId));
        this.baitBuyCount.setString(String.valueOf(BaitBuyCount));
        this.baitItemId2.setString(String.valueOf(BaitItemId2));
        this.baitShopId2.setString(String.valueOf(BaitShopId2));
        this.baitBuyCount2.setString(String.valueOf(BaitBuyCount2));
        this.fishX.setString(String.valueOf(FishX));
        this.fishY.setString(String.valueOf(FishY));
        this.form.append(this.options);
        this.form.append(this.delayMs);
        this.form.append(this.minEmptySlot);
        this.form.append(this.rodItemId);
        this.form.append(this.rodShopId);
        this.form.append(this.rodBuyCount);
        this.form.append(this.baitItemId);
        this.form.append(this.baitShopId);
        this.form.append(this.baitBuyCount);
        this.form.append(this.baitItemId2);
        this.form.append(this.baitShopId2);
        this.form.append(this.baitBuyCount2);
        this.form.append(this.fishX);
        this.form.append(this.fishY);
        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public final void commandAction(javax.microedition.lcdui.Command command, Displayable displayable) {
        if (command == this.luu) {
            try {
                AutoBuyRod = this.options.isSelected(0);
                AutoBuyBait = this.options.isSelected(1);
                DelayMs = parseInt(this.delayMs.getString(), 1000);
                MinEmptySlot = parseInt(this.minEmptySlot.getString(), 1);
                RodItemId = parseInt(this.rodItemId.getString(), 597);
                RodShopId = parseInt(this.rodShopId.getString(), 14);
                RodBuyCount = parseInt(this.rodBuyCount.getString(), 10);
                BaitItemId = parseInt(this.baitItemId.getString(), 603);
                BaitShopId = parseInt(this.baitShopId.getString(), 8);
                BaitBuyCount = parseInt(this.baitBuyCount.getString(), 50);
                BaitItemId2 = parseInt(this.baitItemId2.getString(), 602);
                BaitShopId2 = parseInt(this.baitShopId2.getString(), 9);
                BaitBuyCount2 = parseInt(this.baitBuyCount2.getString(), 50);
                FishX = parseInt(this.fishX.getString(), -1);
                FishY = parseInt(this.fishY.getString(), -1);
                normalize();
                save();
                GameCanvas.setText("Đã lưu auto câu cá");
            } catch (Exception e) {
                GameCanvas.setText("Lỗi dữ liệu auto câu cá");
            }
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    public static int getDelayMs() {
        load();
        return DelayMs < 1000 ? 1000 : DelayMs;
    }

    public static void save() {
        try {
            normalize();
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(AutoBuyRod);
            dataout.writeBoolean(AutoBuyBait);
            dataout.writeInt(DelayMs);
            dataout.writeInt(MinEmptySlot);
            dataout.writeInt(RodItemId);
            dataout.writeInt(RodShopId);
            dataout.writeInt(RodBuyCount);
            dataout.writeInt(BaitItemId);
            dataout.writeInt(BaitShopId);
            dataout.writeInt(BaitBuyCount);
            dataout.writeInt(BaitItemId2);
            dataout.writeInt(BaitShopId2);
            dataout.writeInt(BaitBuyCount2);
            dataout.writeInt(FishX);
            dataout.writeInt(FishY);
            dataout.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            dataout.close();
            byteout.close();
            loaded = true;
        } catch (Exception e) {
        }
    }

    public static void load() {
        if (loaded) {
            return;
        }

        try {
            byte[] data = RMS.getRecord(STORE_NAME);
            if (data != null) {
                ByteArrayInputStream bytein = new ByteArrayInputStream(data);
                DataInputStream datain = new DataInputStream(bytein);
                AutoBuyRod = datain.readBoolean();
                AutoBuyBait = datain.readBoolean();
                DelayMs = datain.readInt();
                MinEmptySlot = datain.readInt();
                RodItemId = datain.readInt();
                RodShopId = datain.readInt();
                RodBuyCount = datain.readInt();
                BaitItemId = datain.readInt();
                BaitShopId = datain.readInt();
                BaitBuyCount = datain.readInt();
                BaitItemId2 = datain.readInt();
                BaitShopId2 = datain.readInt();
                BaitBuyCount2 = datain.readInt();
                FishX = datain.readInt();
                FishY = datain.readInt();
                datain.close();
                bytein.close();
            }
        } catch (Exception e) {
        }

        normalize();
        loaded = true;
    }

    private static void normalize() {
        if (DelayMs < 1000) {
            DelayMs = 1000;
        }
        if (MinEmptySlot < 0) {
            MinEmptySlot = 0;
        }
        if (RodBuyCount <= 0) {
            RodBuyCount = 1;
        }
        if (BaitBuyCount <= 0) {
            BaitBuyCount = 1;
        }
        if (BaitBuyCount2 <= 0) {
            BaitBuyCount2 = 1;
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
