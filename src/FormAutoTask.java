
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
import javax.microedition.rms.RecordStore;

public final class FormAutoTask implements CommandListener {

    private static ByteArrayInputStream bytein;
    private static DataInputStream datain;
    private static ByteArrayOutputStream byteout;
    private static DataOutputStream dataout;
    private static RecordStore loadata;

    private final Form form = new Form("Cài Đặt NVHN TT");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);

    public static boolean batNvhn = true;
    public static int gioNvhn = 1;
    public static int phutNvhn = 0;

    public static boolean batTaThu = true;
    public static int gioTaThu = 3;
    public static int phutTaThu = 0;
    private static int randomSeed = 0;

    private TextField gioNvhnTf;
    private TextField phutNvhnTf;
    private TextField gioTaThuTf;
    private TextField phutTaThuTf;
    private ChoiceGroup caidat;

    public FormAutoTask() {
        this.caidat = new ChoiceGroup("Cài đặt", 2, new String[]{"Bật NVHN", "Bật Tà Thú"}, (Image[]) null);
        this.gioNvhnTf = new TextField("Giờ NVHN", String.valueOf(gioNvhn), 2, 2);
        this.phutNvhnTf = new TextField("Phút NVHN", String.valueOf(phutNvhn), 2, 2);
        this.gioTaThuTf = new TextField("Giờ Tà Thú", String.valueOf(gioTaThu), 2, 2);
        this.phutTaThuTf = new TextField("Phút Tà Thú", String.valueOf(phutTaThu), 2, 2);
    }

    public final void select() {
        this.form.deleteAll();
        this.caidat.setSelectedIndex(0, batNvhn);
        this.caidat.setSelectedIndex(1, batTaThu);
        this.form.append(this.caidat);

        this.form.append(this.gioNvhnTf);
        this.form.append(this.phutNvhnTf);
        this.form.append(this.gioTaThuTf);
        this.form.append(this.phutTaThuTf);

        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);

        show(this.form);
    }

    private static void show(Displayable current) {
        Display.getDisplay(GameMidlet.instance).setCurrent(current);
    }

    public final void commandAction(javax.microedition.lcdui.Command command, Displayable displayable) {
        if (command == this.luu) {
            try {
                batNvhn = this.caidat.isSelected(0);
                batTaThu = this.caidat.isSelected(1);
                gioNvhn = Integer.parseInt(this.gioNvhnTf.getString());
                phutNvhn = Integer.parseInt(this.phutNvhnTf.getString());
                gioTaThu = Integer.parseInt(this.gioTaThuTf.getString());
                phutTaThu = Integer.parseInt(this.phutTaThuTf.getString());

                if (!isValidTime(gioNvhn, phutNvhn) || !isValidTime(gioTaThu, phutTaThu)) {
                    GameCanvas.setText("Giờ 0-23, phút 0-59");
                    return;
                }

                save();
                GameCanvas.setText("Lưu cài đặt thành công");
            } catch (Exception e) {
                GameCanvas.setText("Lỗi lưu dữ liệu");
                return;
            }
        }

        show(MotherCanvas.getInstance());
    }

    private static boolean isValidTime(int hour, int minute) {
        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }

    private static int randomInt(int maxExclusive) {
        if (randomSeed == 0) {
            randomSeed = (int) (System.currentTimeMillis() & 2147483647L);
            if (randomSeed == 0) {
                randomSeed = 1;
            }
        }

        randomSeed = randomSeed * 1103515245 + 12345;
        return (randomSeed >>> 1) % maxExclusive;
    }

    private static void randomDefaultTime() {
        gioNvhn = randomInt(24);
        phutNvhn = randomInt(60);
        gioTaThu = randomInt(24);
        phutTaThu = randomInt(60);

        if (gioNvhn == gioTaThu && phutNvhn == phutTaThu) {
            phutTaThu = (phutTaThu + 1) % 60;
        }
    }

    private static void save() {
        try {
            byteout = new ByteArrayOutputStream();
            dataout = new DataOutputStream(byteout);
            dataout.writeInt(gioNvhn);
            dataout.writeInt(phutNvhn);
            dataout.writeBoolean(batNvhn);
            dataout.writeInt(gioTaThu);
            dataout.writeInt(phutTaThu);
            dataout.writeBoolean(batTaThu);
            dataout.flush();
            dataout.close();
            byteout.flush();

            loadata = RecordStore.openRecordStore("MenuAuto", true);
            byte[] data = byteout.toByteArray();
            byteout.close();
            if (loadata.getNumRecords() == 0) {
                loadata.addRecord(data, 0, data.length);
            } else {
                loadata.setRecord(1, data, 0, data.length);
            }

            loadata.closeRecordStore();
        } catch (Exception e) {
        }
    }

    static {
        boolean saveRandomDefault = false;

        try {
            loadata = RecordStore.openRecordStore("MenuAuto", true);
            if (loadata.getNumRecords() != 0) {
                bytein = new ByteArrayInputStream(loadata.getRecord(1));
                datain = new DataInputStream(bytein);

                gioNvhn = datain.readInt();
                phutNvhn = datain.readInt();
                batNvhn = datain.readBoolean();

                gioTaThu = datain.readInt();
                phutTaThu = datain.readInt();
                batTaThu = datain.readBoolean();

                datain.close();
                bytein.close();
            } else {
                randomDefaultTime();
                saveRandomDefault = true;
            }

            loadata.closeRecordStore();
        } catch (Exception e) {
        }

        if (saveRandomDefault) {
            save();
        }
    }
}
