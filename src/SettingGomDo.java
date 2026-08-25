
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public final class SettingGomDo implements CommandListener {
   private final Form d = new Form("Cài đặt Gom Đồ");
   private TextField e;
   private TextField f;
   private TextField h;
   private TextField i;
   private TextField j;
   private TextField k;
   private Command l;
   public static String stringItemCat = "454,455,456,457";
   private final ChoiceGroup m;
   private final ChoiceGroup tradeCoinChoie;
   private final ChoiceGroup onOffChoice;
   public static byte c = 1;
   public static byte onOffValue = 1;
   public static byte tradeCoinValue = 1;

   public SettingGomDo() {
      boolean var1 = AutoReceiver.stringNameCharNhanDo == null;
      this.onOffChoice = new ChoiceGroup("Hẹn giờ gom đồ", 1, new String[]{"Bật", "Tắt"}, new Image[2]);
      this.e = new TextField("Tên nhận đồ", var1 ? "chen" : AutoReceiver.stringNameCharNhanDo, 100, 0);
      this.f = new TextField("Giờ nhận đồ", var1 ? "0" : String.valueOf(AutoReceiver.gioNhanDo), 2, 2);
      this.m = new ChoiceGroup("Auto cất đồ vào rương khi up", 1, new String[]{"Bật", "Tắt"}, new Image[2]);
      this.k = new TextField("Item cất, mỗi ID cách nhau bằng dấu phẩy", stringItemCat, 1000, 0);
      this.h = new TextField("Phút nhận đồ", var1 ? "0" : String.valueOf(AutoReceiver.phutNhanDo), 2, 2);
      this.i = new TextField("Map nhận đồ", var1 ? "22" : String.valueOf(AutoReceiver.mapNhanDo), 4, 2);
      this.j = new TextField("Khu vực nhận", var1 ? "20" : String.valueOf(AutoReceiver.khuNhanDo), 3, 2);
      this.tradeCoinChoie = new ChoiceGroup("Gom xu không?", 1, new String[]{"Có", "Không"}, new Image[2]);
      this.l = new Command("Lưu", 4, 0);
      this.e.setString(var1 ? "ytakim" : AutoReceiver.stringNameCharNhanDo);
      this.f.setString(String.valueOf(AutoReceiver.gioNhanDo));
      this.h.setString(String.valueOf(AutoReceiver.phutNhanDo));
      this.i.setString(String.valueOf(AutoReceiver.mapNhanDo <= 0 ? 22 : AutoReceiver.mapNhanDo));
      this.j.setString(String.valueOf(AutoReceiver.khuNhanDo <= 0 ? 20 : AutoReceiver.khuNhanDo));
   }

   public final void a() {
      this.d.append(this.onOffChoice);
      this.d.append(this.tradeCoinChoie);
      this.d.append(this.e);
      this.d.append(this.f);
      this.d.append(this.h);
      this.d.append(this.i);
      this.d.append(this.j);
      this.d.append(this.m);
      this.d.append(this.k);
      this.d.addCommand(this.l);
      this.d.addCommand(new Command("Thoát", 7, 0));
      this.m.setSelectedIndex(c, true);
      this.onOffChoice.setSelectedIndex(onOffValue, true);
      this.tradeCoinChoie.setSelectedIndex(tradeCoinValue, true);
      this.d.setCommandListener(this);
      a(this.d);
   }

   private static void a(Displayable var0) {
      Display.getDisplay(GameMidlet.instance).setCurrent(var0);
   }

   public final void commandAction(Command var1, Displayable var2) {
      if (var1 == this.l) {
         AutoReceiver.stringNameCharNhanDo = this.e.getString();
         stringItemCat = this.k.getString();

         try {
            c = (byte)this.m.getSelectedIndex();
            onOffValue = (byte)this.onOffChoice.getSelectedIndex();
            tradeCoinValue = (byte)this.tradeCoinChoie.getSelectedIndex();
            AutoReceiver.gioNhanDo = Byte.parseByte(this.f.getString());
            AutoReceiver.phutNhanDo = Byte.parseByte(this.h.getString());
            AutoReceiver.mapNhanDo = Integer.parseInt(this.i.getString());
            AutoReceiver.khuNhanDo = Byte.parseByte(this.j.getString());
         } catch (Exception var4) {
         }

         GameCanvas.setText("Lưu cài đặt thành công");
         AutoReceiver.save();
      }

      a(MotherCanvas.getInstance());
   }
}
