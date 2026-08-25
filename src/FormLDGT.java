
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

public final class FormLDGT implements CommandListener {

    private static ByteArrayInputStream bytein;
    private static DataInputStream datain;
    private static ByteArrayOutputStream byteout;
    private static DataOutputStream dataout;
    private static RecordStore loadata;

    private final Form form = new Form("menu ldgt");
    private final javax.microedition.lcdui.Command luu = new javax.microedition.lcdui.Command("Lưu", 4, 1);
    private final javax.microedition.lcdui.Command huy = new javax.microedition.lcdui.Command("Hủy", 3, 1);

    public static int VaiTro;
    public static boolean HenGio = false;
    public static boolean KhongDi = false;
    public static int GioLDGT;
    public static int PhutLDGT;

    private final ChoiceGroup vaitro;
    private final ChoiceGroup hengio;
    private TextField gioldgt;
    private TextField phutldgt;

    public FormLDGT() {
        this.gioldgt = new TextField("Giờ đi LDGT", "" + GioLDGT, 2, 2);
        this.phutldgt = new TextField("Phút đi LDGT", "" + PhutLDGT, 2, 2);

        this.vaitro = new ChoiceGroup("Vai trò LDGT", 1, new String[]{
            "Tộc trưởng",
            "Acc đánh cửa 1",
            "Acc đánh cửa 2",
            "Acc đánh cửa 3",
            "Acc clone",
            "Không đi"
        }, (Image[]) null);

        this.hengio = new ChoiceGroup("Hẹn giờ LDGT", 2, new String[]{
            "Bật hẹn giờ"
        }, (Image[]) null);

    }

    public final void select() {
        this.form.append(this.vaitro);
        this.form.append(this.hengio);
        this.form.append(this.gioldgt);
        this.form.append(this.phutldgt);

        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);

        if (VaiTro < 0 || VaiTro > 5) {
            VaiTro = 0;
        }

        this.vaitro.setSelectedIndex(VaiTro, true);
        this.hengio.setSelectedIndex(0, HenGio);

        timelite(this.form);
    }

    private static void timelite(Displayable current) {
        Display.getDisplay(GameMidlet.instance).setCurrent(current);
    }

    public final void commandAction(javax.microedition.lcdui.Command command, Displayable displayable) {
        if (command == this.luu) {
            try {
                VaiTro = this.vaitro.getSelectedIndex();
                HenGio = this.hengio.isSelected(0);
                KhongDi = VaiTro == 5;
                GioLDGT = Integer.parseInt(this.gioldgt.getString());
                PhutLDGT = Integer.parseInt(this.phutldgt.getString());

                if (GioLDGT < 0) {
                    GioLDGT = 0;
                }

                if (GioLDGT > 23) {
                    GioLDGT = 23;
                }

                if (PhutLDGT < 0) {
                    PhutLDGT = 0;
                }

                if (PhutLDGT > 59) {
                    PhutLDGT = 59;
                }

                byteout = new ByteArrayOutputStream();
                dataout = new DataOutputStream(byteout);

                try {
                    dataout.writeInt(VaiTro);
                    dataout.writeBoolean(HenGio);
                    dataout.writeInt(GioLDGT);
                    dataout.writeInt(PhutLDGT);
                    dataout.writeBoolean(KhongDi);
                    dataout.flush();
                    dataout.close();
                    byteout.flush();

                    loadata = RecordStore.openRecordStore("MenuLDGT", true);
                    byte[] byteArray = byteout.toByteArray();
                    byteout.close();

                    if (loadata.getNumRecords() == 0) {
                        loadata.addRecord(byteArray, 0, byteArray.length);
                    } else {
                        loadata.setRecord(1, byteArray, 0, byteArray.length);
                    }

                    loadata.closeRecordStore();
                } catch (Exception var4) {
                }

                GameCanvas.setText("Lưu cài đặt LDGT thành công");
            } catch (NumberFormatException var5) {
            }
        }

        timelite(MotherCanvas.getInstance());
    }

    public static boolean isTocTruong() {
        return VaiTro == 0;
    }

    public static boolean isCua1() {
        return VaiTro == 1;
    }

    public static boolean isCua2() {
        return VaiTro == 2;
    }

    public static boolean isCua3() {
        return VaiTro == 3;
    }

    public static boolean isClone() {
        return VaiTro == 4;
    }

    public static boolean isKhongDi() {
        return VaiTro == 5;
    }

    public static String getRoleName() {
        switch (VaiTro) {
            case 0:
                return "Tộc trưởng";
            case 1:
                return "Cửa 1";
            case 2:
                return "Cửa 2";
            case 3:
                return "Cửa 3";
            case 4:
                return "Clone";
            case 5:
                return "Không đi";
            default:
                return "Không rõ";
        }
    }

    public static boolean denGioLDGT() {
        if (!HenGio) {
            return true;
        }

        try {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            int gio = calendar.get(java.util.Calendar.HOUR_OF_DAY);
            int phut = calendar.get(java.util.Calendar.MINUTE);

            if (gio > GioLDGT) {
                return true;
            }

            if (gio == GioLDGT && phut >= PhutLDGT) {
                return true;
            }
        } catch (Exception var3) {
        }

        return false;
    }

    static {
        VaiTro = 4;
        KhongDi = false;
        GioLDGT = 20;
        PhutLDGT = 0;

        try {
            loadata = RecordStore.openRecordStore("MenuLDGT", true);
            if (loadata.getNumRecords() != 0) {
                bytein = new ByteArrayInputStream(loadata.getRecord(1));
                datain = new DataInputStream(bytein);

                VaiTro = datain.readInt();
                HenGio = datain.readBoolean();
                GioLDGT = datain.readInt();
                PhutLDGT = datain.readInt();

                try {
                    KhongDi = datain.readBoolean();
                } catch (Exception var2) {
                    KhongDi = false;
                }

                if (KhongDi) {
                    VaiTro = 5;
                }
            }

            loadata.closeRecordStore();
        } catch (Exception var1) {
        }

    }
}
