
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStore;

public final class FormAutoNpc implements CommandListener {

    private static ByteArrayInputStream bytein;
    private static DataInputStream datain;
    private static ByteArrayOutputStream byteout;
    private static DataOutputStream dataout;
    private static RecordStore loadata;

    public static int MapID = 1;
    public static int KhuID = -1;
    public static int NpcID = 24;
    public static String DuongDanNut = "1,0";
    public static String GiaTriNhap = "";
    public static int SoLan = 50;
    public static int Delay = 50;

    private final Form form = new Form("Menu Auto NPC");
    private final Command luu = new Command("Lưu", Command.OK, 1);
    private final Command huy = new Command("Hủy", Command.BACK, 1);

    private TextField txtMap;
    private TextField txtKhu;
    private TextField txtNpc;
    private TextField txtPath;
    private TextField txtInput;
    private TextField txtSoLan;
    private TextField txtDelay;

    public FormAutoNpc() {
        this.txtMap = new TextField("Map ID", String.valueOf(MapID), 6, TextField.NUMERIC);
        this.txtKhu = new TextField("Khu ID (-1 giữ nguyên)", String.valueOf(KhuID), 6, TextField.NUMERIC);
        this.txtNpc = new TextField("NPC ID", String.valueOf(NpcID), 6, TextField.NUMERIC);
        this.txtPath = new TextField("Đường dẫn nút (vd: 0 hoặc 1,0)", DuongDanNut, 64, TextField.ANY);
        this.txtInput = new TextField("Số cần nhập (để trống nếu không có)", GiaTriNhap, 64, TextField.ANY);
        this.txtSoLan = new TextField("Số lần", String.valueOf(SoLan), 6, TextField.NUMERIC);
        this.txtDelay = new TextField("Delay (ms)", String.valueOf(Delay), 6, TextField.NUMERIC);
    }

    public final void select() {
        this.form.deleteAll();

        this.form.append(this.txtMap);
        this.form.append(this.txtKhu);
        this.form.append(this.txtNpc);
        this.form.append(this.txtPath);
        this.form.append(this.txtInput);
        this.form.append(this.txtSoLan);
        this.form.append(this.txtDelay);

        this.form.addCommand(this.luu);
        this.form.addCommand(this.huy);
        this.form.setCommandListener(this);

        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public final void commandAction(Command command, Displayable displayable) {
        if (command == this.luu) {
            try {
                MapID = Integer.parseInt(this.txtMap.getString().trim());
                KhuID = Integer.parseInt(this.txtKhu.getString().trim());
                NpcID = Integer.parseInt(this.txtNpc.getString().trim());
                DuongDanNut = this.txtPath.getString().trim();
                GiaTriNhap = this.txtInput.getString().trim();
                SoLan = Integer.parseInt(this.txtSoLan.getString().trim());
                Delay = Integer.parseInt(this.txtDelay.getString().trim());

                byteout = new ByteArrayOutputStream();
                dataout = new DataOutputStream(byteout);

                dataout.writeInt(MapID);
                dataout.writeInt(KhuID);
                dataout.writeInt(NpcID);
                dataout.writeUTF(DuongDanNut);
                dataout.writeUTF(GiaTriNhap);
                dataout.writeInt(SoLan);
                dataout.writeInt(Delay);
                dataout.flush();

                loadata = RecordStore.openRecordStore("MenuAutoNpc", true);
                byte[] data = byteout.toByteArray();

                if (loadata.getNumRecords() == 0) {
                    loadata.addRecord(data, 0, data.length);
                } else {
                    loadata.setRecord(1, data, 0, data.length);
                }

                loadata.closeRecordStore();
                dataout.close();
                byteout.close();

                GameCanvas.setText("Lưu Auto NPC thành công, chat: anpc");
            } catch (Exception e) {
                GameCanvas.setText("Lỗi dữ liệu Auto NPC");
            }
        }

        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    static {
        try {
            loadata = RecordStore.openRecordStore("MenuAutoNpc", true);

            if (loadata.getNumRecords() > 0) {
                bytein = new ByteArrayInputStream(loadata.getRecord(1));
                datain = new DataInputStream(bytein);

                MapID = datain.readInt();
                KhuID = datain.readInt();
                NpcID = datain.readInt();
                DuongDanNut = datain.readUTF();

                if (bytein.available() == 8) {
                    GiaTriNhap = "";
                    SoLan = datain.readInt();
                    Delay = datain.readInt();
                } else {
                    GiaTriNhap = datain.readUTF();
                    SoLan = datain.readInt();
                    Delay = datain.readInt();
                }

                datain.close();
                bytein.close();
            }

            loadata.closeRecordStore();
        } catch (Exception e) {
        }
    }
   }
