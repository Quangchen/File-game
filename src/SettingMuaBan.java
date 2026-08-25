

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

public final class SettingMuaBan implements CommandListener {
   private static ByteArrayInputStream d;
   private static DataInputStream e;
   private static ByteArrayOutputStream f;
   private static DataOutputStream g;
   private static RecordStore h;
   public Form a = new Form("Menu Mua B\u00e1n !");
   private Command i;
   private Command j;
   private TextField k;
   public static long b = 62000L;
   private final ChoiceGroup l;
   public static byte c = 1;

   public SettingMuaBan() {
      this.k = new TextField("Qu\u00e3ng ngh\u1ec9 (milisecond)", "" + b, 500, 2);
      this.i = new Command("L\u01b0u", 4, 1);
      this.j = new Command("H\u1ee7y", 7, 1);
      this.l = new ChoiceGroup("B\u1ea1n Mu\u1ed1n Gh\u00ec ?", 1, new String[]{"Kh\u00f4ng ch\u00e1t ", "Ch\u1ec9 ch\u00e1t c\u00f4ng c\u1ed9ng !"}, new Image[2]);
   }

   public final void a() {
      this.a.append(this.l);
      this.a.append(this.k);
      this.a.addCommand(this.i);
      this.a.addCommand(this.j);
      this.a.setCommandListener(this);
      this.l.setSelectedIndex(c, true);
      a(this.a);
   }

   private static void a(Displayable var0) {
      Display.getDisplay(GameMidlet.instance).setCurrent(var0);
   }

   public final void commandAction(Command var1, Displayable var2) {
      if (var1 == this.i) {
         b = Long.parseLong(this.k.getString());
         c = (byte)this.l.getSelectedIndex();

         try {
            f = new ByteArrayOutputStream();
            (g = new DataOutputStream(f)).writeLong(b);
            g.flush();
            g.close();
            f.flush();
            h = RecordStore.openRecordStore("AutoChat", true);
            f.close();
            if (h.getNumRecords() == 0) {
               h.addRecord(f.toByteArray(), 0, f.toByteArray().length);
            } else {
               h.setRecord(1, f.toByteArray(), 0, f.toByteArray().length);
            }

            h.closeRecordStore();
         } catch (Exception var4) {
         }

         GameCanvas.setText("L\u01b0u c\u00e0i \u0111\u1eb7t th\u00e0nh c\u00f4ng");
      }

      a(MotherCanvas.getInstance());
   }

   static {
      try {
         if ((h = RecordStore.openRecordStore("AutoChat", true)).getNumRecords() != 0) {
            d = new ByteArrayInputStream(h.getRecord(1));
            e = new DataInputStream(d);
            b = e.readLong();
            c = e.readByte();
         }

         h.closeRecordStore();
      } catch (Exception var1) {
      }

   }
}
