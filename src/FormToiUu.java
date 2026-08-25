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
import javax.microedition.rms.RecordStore;

public final class FormToiUu implements CommandListener {

    private static final String STORE_NAME = "NsoChenToiUuCfg";

    public static boolean anBackground = false;
    public static boolean anTree = false;
    public static boolean anHieuUngNhanVat = false;
    public static boolean anTrangBi2 = false;
    public static boolean anSkill = false;
    public static boolean anMapLine = false;
    public static boolean anAll = false;

    private static boolean lowGraphicApplied = false;
    private static boolean fakeSkillApplied = false;
    private static boolean lowGraphicBefore = false;
    private static boolean fakeSkillNgangBefore = false;
    private static int nSkillFakeBefore = 100;
    private static boolean fakeSkillCaoBefore = false;
    private static int cSkillFakeBefore = 100;
    private static long lastRamOptimizeAt = 0L;

    private final Form form = new Form("Tối ưu NSOChen");
    private final Command save = new Command("Lưu", Command.OK, 1);
    private final Command cancel = new Command("Hủy", Command.BACK, 1);
    private final ChoiceGroup options;

    public FormToiUu() {
        this.options = new ChoiceGroup("Thành phần ẩn", ChoiceGroup.MULTIPLE, new String[]{
            "Ẩn background",
            "Ẩn cây",
            "Ẩn hiệu ứng nhân vật",
            "Ẩn trang bị 2",
            "Ẩn skill",
            "Ẩn map (giữ line đỏ)",
            "Siêu tối ưu"
        }, (Image[]) null);
    }

    public void select() {
        load();
        this.form.deleteAll();
        this.options.setSelectedIndex(0, anBackground);
        this.options.setSelectedIndex(1, anTree);
        this.options.setSelectedIndex(2, anHieuUngNhanVat);
        this.options.setSelectedIndex(3, anTrangBi2);
        this.options.setSelectedIndex(4, anSkill);
        this.options.setSelectedIndex(5, anMapLine);
        this.options.setSelectedIndex(6, anAll);
        this.form.append("Siêu tối ưu sẽ giữ tên/marker, dọn cache ảnh/effect nhưng không xóa dữ liệu mob/người/item.\n");
        this.form.append(this.options);
        this.form.addCommand(this.save);
        this.form.addCommand(this.cancel);
        this.form.setCommandListener(this);
        Display.getDisplay(GameMidlet.instance).setCurrent(this.form);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.save) {
            anBackground = this.options.isSelected(0);
            anTree = this.options.isSelected(1);
            anHieuUngNhanVat = this.options.isSelected(2);
            anTrangBi2 = this.options.isSelected(3);
            anSkill = this.options.isSelected(4);
            anMapLine = this.options.isSelected(5);
            anAll = this.options.isSelected(6);
            save();
            applySetting();
            if (!anAll) {
                restoreAfterRamOptimize();
            }
            GameCanvas.setText("Đã lưu tối ưu NSOChen");
        }
        Display.getDisplay(GameMidlet.instance).setCurrent(MotherCanvas.getInstance());
    }

    public static boolean isAny() {
        return anBackground || anTree || anHieuUngNhanVat || anTrangBi2 || anSkill || anMapLine || anAll;
    }

    public static boolean isHideBackground() {
        return anBackground || anAll;
    }

    public static boolean isHideTree() {
        return anTree || anAll;
    }

    public static boolean isHideCharEffect() {
        return anHieuUngNhanVat || anTrangBi2 || anSkill || anAll;
    }

    public static boolean isHideTrangBi2() {
        return anTrangBi2 || anAll;
    }

    public static boolean isHideSkill() {
        return anSkill || anAll;
    }

    public static boolean isHideMapLine() {
        return anMapLine || anAll;
    }

    public static boolean isHideAll() {
        return anAll;
    }

    public static boolean isHideSkillTable() {
        return anAll;
    }

    public static boolean isHideBottomNotice() {
        return anAll;
    }

    public static boolean isHideTouchButtons() {
        return anAll;
    }

    public static boolean isHideStatusBar() {
        return anAll;
    }

    public static boolean shouldHideGameButtons() {
        return isHideTouchButtons() && GameCanvas.mScreen instanceof GameScr
                && GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu
                && ChatPopup.currentMultilineChatPopup == null && !ChatTextField.a().isShow;
    }

    public static boolean toggleHideAll() {
        load();
        anAll = !anAll;
        save();
        applySetting();
        if (!anAll) {
            restoreAfterRamOptimize();
        }
        return anAll;
    }

    public static void applySetting() {
        try {
            boolean needFakeSkill = isHideSkill() || isHideCharEffect();

            if (lowGraphicApplied) {
                GameCanvas.lowGraphic = lowGraphicBefore;
                lowGraphicApplied = false;
            }

            if (needFakeSkill) {
                if (!fakeSkillApplied) {
                    fakeSkillNgangBefore = Code.isFakeSkillNgang;
                    nSkillFakeBefore = Code.nSkillFake;
                    fakeSkillCaoBefore = Code.isFakeSkillCao;
                    cSkillFakeBefore = Code.cSkillFake;
                }
                Code.isFakeSkillNgang = true;
                Code.nSkillFake = 20;
                Code.isFakeSkillCao = true;
                Code.cSkillFake = 20;
                fakeSkillApplied = true;
            } else if (fakeSkillApplied) {
                Code.isFakeSkillNgang = fakeSkillNgangBefore;
                Code.nSkillFake = nSkillFakeBefore;
                Code.isFakeSkillCao = fakeSkillCaoBefore;
                Code.cSkillFake = cSkillFakeBefore;
                fakeSkillApplied = false;
            }

            if (isAny() || needFakeSkill) {
                FormAutoUp.clearVisualCache();
            }

            applyRamOptimize(true);
        } catch (Exception e) {
        }
    }

    public static void applyRamOptimize(boolean force) {
        try {
            if (!anAll) {
                return;
            }

            long now = System.currentTimeMillis();
            if (!force && now - lastRamOptimizeAt < 300000L) {
                return;
            }
            lastRamOptimizeAt = now;

            FormAutoUp.clearVisualCache();
            if (force) {
                GameCanvas.clearOptimizeBackgroundCache();
                TileMap.clearOptimizeImages();
            }
        } catch (Exception e) {
        }
    }

    private static void restoreAfterRamOptimize() {
        try {
            GameCanvas.setBackground(TileMap.bgID);
            TileMap.d();
        } catch (Exception e) {
        }
    }

    public static void save() {
        RecordStore rs = null;
        ByteArrayOutputStream byteout = null;
        DataOutputStream dataout = null;
        try {
            byteout = new ByteArrayOutputStream();
            dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(anBackground);
            dataout.writeBoolean(anTree);
            dataout.writeBoolean(anHieuUngNhanVat);
            dataout.writeBoolean(anTrangBi2);
            dataout.writeBoolean(anSkill);
            dataout.writeBoolean(anMapLine);
            dataout.writeBoolean(anAll);
            dataout.flush();
            byte[] data = byteout.toByteArray();
            rs = RecordStore.openRecordStore(STORE_NAME, true);
            if (rs.getNumRecords() == 0) {
                rs.addRecord(data, 0, data.length);
            } else {
                rs.setRecord(1, data, 0, data.length);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (dataout != null) {
                    dataout.close();
                }
                if (byteout != null) {
                    byteout.close();
                }
                if (rs != null) {
                    rs.closeRecordStore();
                }
            } catch (Exception e) {
            }
        }
    }

    public static void load() {
        RecordStore rs = null;
        ByteArrayInputStream bytein = null;
        DataInputStream datain = null;
        try {
            rs = RecordStore.openRecordStore(STORE_NAME, true);
            if (rs.getNumRecords() == 0) {
                return;
            }
            byte[] data = rs.getRecord(1);
            bytein = new ByteArrayInputStream(data);
            datain = new DataInputStream(bytein);
            anBackground = datain.readBoolean();
            anTree = datain.readBoolean();
            anHieuUngNhanVat = datain.readBoolean();
            anTrangBi2 = datain.readBoolean();
            anSkill = datain.readBoolean();
            anMapLine = datain.readBoolean();
            anAll = datain.readBoolean();
        } catch (Exception e) {
        } finally {
            try {
                if (datain != null) {
                    datain.close();
                }
                if (bytein != null) {
                    bytein.close();
                }
                if (rs != null) {
                    rs.closeRecordStore();
                }
            } catch (Exception e) {
            }
        }
    }

    static {
        load();
        applySetting();
    }
}
