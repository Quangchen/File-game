
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStore;

/**
 *
 * @author baomi
 */
public class SettingNVDV implements CommandListener {

    private static ByteArrayInputStream bis;
    private static DataInputStream dis;
    private static ByteArrayOutputStream bos;
    private static DataOutputStream dos;
    private static RecordStore recoreStore;
    private static int randomSeed;
    public static byte tickHenGioLamDV;
    public static byte tickDanhQuaiThuong;
    public static byte tickGietTA;
    public static byte tickGietTL;
    public static byte tickNangCapVP;
    public static byte tickWinLoiDai;
    public static byte tickCuuSat;
    public static byte tickUseBHNang7;
    public static byte tickUseBHNang8;
    public static byte tickNongDan;
    
    public static int gioADV;
    public static int phutADV;
    public static String nameCharLoiDai;
    public static int mapLoiDai;
    public static int khuLoiDai;
    public static int mapDanhVong;
    public static int khuDanhVong;
    public static int xuCuocLoiDai;
    public static int mapCuuSat;
    public static int khuCuuSat;
    
    public static int upgrade;
    public static int daUpgrade;
    
    private final Form formNVDV = new Form("Cài đặt NVDV <Kudo>");
    private final Command cmdSave = new Command("Lưu", 4, 1);
    private final Command cmdCancel = new Command("Hủy", 3, 1);
    private final ChoiceGroup choiceHenGioLamDV = new ChoiceGroup("Hẹn giờ làm NVDV!", 1, new String[]{"Bật", "Tắt"}, new Image[2]);
    private final ChoiceGroup choiceDanhQuaiThuong = new ChoiceGroup("- Tiêu diệt quái thường", 1, new String[]{"Làm NV", "Hủy NV"}, new Image[2]);
    private final ChoiceGroup choiceNangCapVP = new ChoiceGroup("- Nâng cấp vật phẩm!", 1, new String[]{"Làm NV", "Hủy NV"}, new Image[2]);
    private final ChoiceGroup choiceWinLoiDai = new ChoiceGroup("- Chiến thắng lôi đài!", 1, new String[]{"Làm NV", "Hủy NV"}, new Image[2]);
    private final ChoiceGroup choiceCuuSat = new ChoiceGroup("- Cừu sát người khác!", 1, new String[]{"Làm NV", "Hủy NV", "Dừng Auto"}, new Image[3]);
    private final ChoiceGroup choiceNongDan = new ChoiceGroup("- Nông dân chăm chỉ!", 1, new String[]{"Làm NV", "Hủy NV"}, new Image[2]);
    private final ChoiceGroup choiceGietTA = new ChoiceGroup("- Tiêu diệt Tinh Anh", 1, new String[]{"Làm NV", "Hủy NV"}, new Image[2]);
    private final ChoiceGroup choiceGietTL = new ChoiceGroup("- Tiêu diệt Thủ Lĩnh", 1, new String[]{"Làm NV", "Hủy NV"}, new Image[2]);
    private final ChoiceGroup choiceUseBHNang7 = new ChoiceGroup("- Sử dụng bảo hiểm để nâng lên 7", 1, new String[]{"Dùng Bảo Hiểm", "Không Bảo Hiểm"}, new Image[2]);
    private final ChoiceGroup choiceUseBHNang8 = new ChoiceGroup("- Sử dụng bảo hiểm để nâng lên 8", 1, new String[]{"Dùng Bảo Hiểm", "Không Bảo Hiểm"}, new Image[2]);
    private TextField fieldTenDoiThu;
    private TextField fieldMapLoiDai;
    private TextField fieldKhuLoiDai;
    private TextField fieldMapDV;
    private TextField fieldKhuDV;
    private TextField fieldMapCuuSat;
    private TextField fieldKhuCuuSat;
    private TextField fieldXuCuoc;
    private TextField fieldGioDV;
    private TextField fieldPhutDV;
    
    public SettingNVDV() {
        this.formNVDV.append(this.choiceHenGioLamDV);
        this.formNVDV.append(this.fieldGioDV = new TextField("Giờ làm danh vọng", "" + gioADV, 2, 2));
        this.formNVDV.append(this.fieldPhutDV = new TextField("Phút làm danh vọng", "" + phutADV, 2, 2));
        
        this.formNVDV.append(this.choiceWinLoiDai);
        this.formNVDV.append(this.fieldXuCuoc = new TextField("Xu cược", "" + xuCuocLoiDai, 10, 2));
        this.formNVDV.append(this.fieldTenDoiThu = new TextField("Tên Đối Thủ + ',': <Cách nhau bằng dấu phảy ','> ;", nameCharLoiDai, 1024, 0));
        this.formNVDV.append(this.fieldMapLoiDai = new TextField("Map lôi đài", "" + mapLoiDai, 3, 2));
        this.formNVDV.append(this.fieldKhuLoiDai = new TextField("Khu lôi đài", "" + khuLoiDai, 3, 2));
        
        this.formNVDV.append(this.choiceDanhQuaiThuong);
        this.formNVDV.append(this.choiceGietTA);
        this.formNVDV.append(this.choiceGietTL);
        this.formNVDV.append(this.choiceNongDan);
        this.formNVDV.append(this.choiceNangCapVP);
        this.formNVDV.append(this.choiceUseBHNang7);
        this.formNVDV.append(this.choiceUseBHNang8);
        this.formNVDV.append(this.fieldMapDV = new TextField("Map farm quái", "" + mapDanhVong, 3, 1));
        this.formNVDV.append(this.fieldKhuDV = new TextField("Khu farm quái", "" + khuDanhVong, 3, 1));
        
        this.formNVDV.append(this.choiceCuuSat);
        this.formNVDV.append(this.fieldMapCuuSat = new TextField("Map Cừu Sát", "" + mapCuuSat, 3, 3));
        this.formNVDV.append(this.fieldKhuCuuSat = new TextField("Khu Cừu Sát", "" + khuCuuSat, 3, 3));
        
        this.formNVDV.addCommand(this.cmdSave);
        this.formNVDV.addCommand(this.cmdCancel);
        this.formNVDV.setCommandListener(this);
        
        this.choiceHenGioLamDV.setSelectedIndex(tickHenGioLamDV, true);
        this.choiceDanhQuaiThuong.setSelectedIndex(tickDanhQuaiThuong, true);
        this.choiceNangCapVP.setSelectedIndex(tickNangCapVP, true);
        this.choiceWinLoiDai.setSelectedIndex(tickWinLoiDai, true);
        this.choiceCuuSat.setSelectedIndex(tickCuuSat, true);
        this.choiceNongDan.setSelectedIndex(tickNongDan, true);
        this.choiceGietTA.setSelectedIndex(tickGietTA, true);
        this.choiceGietTL.setSelectedIndex(tickGietTL, true);
        this.choiceUseBHNang7.setSelectedIndex(tickUseBHNang7, true);
        this.choiceUseBHNang8.setSelectedIndex(tickUseBHNang8, true);
    }

    public final void show() {
        Display.getDisplay(GameMidlet.instance).setCurrent(this.formNVDV);
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
        gioADV = randomInt(24);
        phutADV = randomInt(60);
    }

    private static void saveCurrentSetting() {
        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            dos.writeByte(tickHenGioLamDV);
            dos.writeByte(tickDanhQuaiThuong);
            dos.writeByte(tickGietTA);
            dos.writeByte(tickGietTL);
            dos.writeByte(tickNangCapVP);
            dos.writeByte(tickWinLoiDai);
            dos.writeByte(tickCuuSat);
            dos.writeByte(tickUseBHNang7);
            dos.writeByte(tickUseBHNang8);
            dos.writeByte(tickNongDan);
            dos.writeInt(gioADV);
            dos.writeInt(phutADV);
            dos.writeInt(xuCuocLoiDai);
            dos.writeUTF(nameCharLoiDai);
            dos.writeInt(mapLoiDai);
            dos.writeInt(khuLoiDai);
            dos.writeInt(mapDanhVong);
            dos.writeInt(khuDanhVong);
            dos.writeInt(mapCuuSat);
            dos.writeInt(khuCuuSat);
            dos.flush();
            dos.close();
            bos.flush();
            recoreStore = RecordStore.openRecordStore("formdv", true);
            byte[] data = bos.toByteArray();
            bos.close();
            if (recoreStore.getNumRecords() == 0) {
                recoreStore.addRecord(data, 0, data.length);
            } else {
                recoreStore.setRecord(1, data, 0, data.length);
            }

            recoreStore.closeRecordStore();
        } catch (Exception ex) {
        }
    }

    public final void commandAction(Command var1, Displayable var2) {
        if (var1 == this.cmdSave) {
            try {
                tickHenGioLamDV = (byte) this.choiceHenGioLamDV.getSelectedIndex();
                tickDanhQuaiThuong = (byte) this.choiceDanhQuaiThuong.getSelectedIndex();
                tickGietTA = (byte) this.choiceGietTA.getSelectedIndex();
                tickGietTL = (byte) this.choiceGietTL.getSelectedIndex();
                tickNangCapVP = (byte) this.choiceNangCapVP.getSelectedIndex();
                tickWinLoiDai = (byte) this.choiceWinLoiDai.getSelectedIndex();
                tickCuuSat = (byte) this.choiceCuuSat.getSelectedIndex();
                tickNongDan = (byte) this.choiceNongDan.getSelectedIndex();
                tickUseBHNang8 = (byte) this.choiceUseBHNang8.getSelectedIndex();
                tickUseBHNang7 = (byte) this.choiceUseBHNang7.getSelectedIndex();
                
                gioADV = Integer.parseInt(this.fieldGioDV.getString());
                phutADV = Integer.parseInt(this.fieldPhutDV.getString());
                xuCuocLoiDai = Integer.parseInt(this.fieldXuCuoc.getString());
                nameCharLoiDai = this.fieldTenDoiThu.getString().trim();
                mapLoiDai = Integer.parseInt(this.fieldMapLoiDai.getString());
                khuLoiDai = Integer.parseInt(this.fieldKhuLoiDai.getString());
                mapDanhVong = Integer.parseInt(this.fieldMapDV.getString());
                khuDanhVong = Integer.parseInt(this.fieldKhuDV.getString());
                mapCuuSat = Integer.parseInt(this.fieldMapCuuSat.getString());
                khuCuuSat = Integer.parseInt(this.fieldKhuCuuSat.getString());
                
                bos = new ByteArrayOutputStream();
                dos = new DataOutputStream(bos);

                try {
                    dos.writeByte(tickHenGioLamDV);
                    dos.writeByte(tickDanhQuaiThuong);
                    dos.writeByte(tickGietTA);
                    dos.writeByte(tickGietTL);
                    dos.writeByte(tickNangCapVP);
                    dos.writeByte(tickWinLoiDai);
                    dos.writeByte(tickCuuSat);
                    dos.writeByte(tickUseBHNang7);
                    dos.writeByte(tickUseBHNang8);
                    dos.writeByte(tickNongDan);
                    dos.writeInt(gioADV);
                    dos.writeInt(phutADV);
                    dos.writeInt(xuCuocLoiDai);
                    dos.writeUTF(nameCharLoiDai);
                    dos.writeInt(mapLoiDai);
                    dos.writeInt(khuLoiDai);
                    dos.writeInt(mapDanhVong);
                    dos.writeInt(khuDanhVong);
                    dos.writeInt(mapCuuSat);
                    dos.writeInt(khuCuuSat);
                    dos.flush();
                    dos.close();
                    bos.flush();
                    recoreStore = RecordStore.openRecordStore("formdv", true);
                    byte[] var3 = bos.toByteArray();
                    bos.close();
                    if (recoreStore.getNumRecords() == 0) {
                        recoreStore.addRecord(var3, 0, var3.length);
                    } else {
                        recoreStore.setRecord(1, var3, 0, var3.length);
                    }

                    recoreStore.closeRecordStore();
                } catch (Exception ex) {
                }

                GameCanvas.setText("Lưu cài đặt thành công");
            } catch (NumberFormatException var5) {
            }
        }
        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    public static void load() {
        boolean saveRandomDefault = false;
        tickHenGioLamDV = 1;
        tickCuuSat = 1;
        gioADV = 0;
        phutADV = 0;
        xuCuocLoiDai = 1000000;
        nameCharLoiDai = "";
        mapLoiDai = 1;
        khuLoiDai = 22;
        mapDanhVong = -1;
        khuDanhVong = -1;
        mapCuuSat = 23;
        khuCuuSat = 6;
        upgrade = 8;
        daUpgrade = 5;
        try {
            if ((recoreStore = RecordStore.openRecordStore("formdv", true)).getNumRecords() != 0) {
                bis = new ByteArrayInputStream(recoreStore.getRecord(1));
                dis = new DataInputStream(bis);
                tickHenGioLamDV = dis.readByte();
                tickDanhQuaiThuong = dis.readByte();
                tickGietTA = dis.readByte();
                tickGietTL = dis.readByte();
                tickNangCapVP = dis.readByte();
                tickWinLoiDai = dis.readByte();
                tickCuuSat = dis.readByte();
                tickUseBHNang7 = dis.readByte();
                tickUseBHNang8 = dis.readByte();
                tickNongDan = dis.readByte();
                gioADV = (dis = new DataInputStream(bis)).readInt();
                phutADV = dis.readInt();
                xuCuocLoiDai = dis.readInt();
                nameCharLoiDai = dis.readUTF();
                mapLoiDai = dis.readInt();
                khuLoiDai = dis.readInt();
                mapDanhVong = dis.readInt();
                khuDanhVong = dis.readInt();
                mapCuuSat = dis.readInt();
                khuCuuSat = dis.readInt();
            } else {
                randomDefaultTime();
                saveRandomDefault = true;
            }

            recoreStore.closeRecordStore();
        } catch (Exception var1) {
        }

        if (saveRandomDefault) {
            saveCurrentSetting();
        }

    }

    static {
        load();
    }

    public static void init() {
        bis = null;
        dis = null;
        bos = null;
        dos = null;
        recoreStore = null;
        tickHenGioLamDV = 0;
        tickDanhQuaiThuong = 0;
        tickGietTA = 0;
        tickGietTL = 0;
        tickNangCapVP = 0;
        tickWinLoiDai = 0;
        tickCuuSat = 0;
        tickUseBHNang7 = 0;
        tickUseBHNang8 = 0;
        tickNongDan = 0;
        xuCuocLoiDai = 0;
        mapCuuSat = 0;
        khuCuuSat = 0;
    }
}
