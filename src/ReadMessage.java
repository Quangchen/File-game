/**
 *
 * @author kudo
 */
public class ReadMessage {
    public final void messageBijuu(Message ms) {
      try {
         Message _ms;
         int idChar;
         Char p;
         switch(ms.reader().readByte()) {
         case 0:
            _ms = ms;

            try {
               ms = null;
               idChar = _ms.reader().readInt();
               if (Char.getMyChar().charID == idChar) {
                  p = Char.getMyChar();
               } else {
                  p = GameScr.getCharByID(idChar);
               }

               if (p != null) {
                  p.arrItemBijuus = new Item[5];

                  for(int indexUI = 0; indexUI < p.arrItemBijuus.length; ++indexUI) {
                     short idItem;
                     if ((idItem = _ms.reader().readShort()) > -1) {
                        p.arrItemBijuus[indexUI] = new Item();
                        p.arrItemBijuus[indexUI].indexUI = indexUI;
                        p.arrItemBijuus[indexUI].typeUI = 51;
                        p.arrItemBijuus[indexUI].template = ItemTemplateManager.get(idItem);
                        p.arrItemBijuus[indexUI].upgrade = _ms.reader().readByte();
                        p.arrItemBijuus[indexUI].expires = _ms.reader().readLong();
                        p.arrItemBijuus[indexUI].sys = _ms.reader().readByte();
                        byte optionLenght = _ms.reader().readByte();
                        p.arrItemBijuus[indexUI].options = new MyVector();

                        for(int i = 0; i < optionLenght; ++i) {
                           p.arrItemBijuus[indexUI].options.addElement(new ItemOption(_ms.reader().readUnsignedByte(), _ms.reader().readInt()));
                        }
                     }
                  }
               }
               break;
            } catch (Exception e) {
               return;
            }
         default:
            return;
         }
      } catch (Exception var18) {
      }

   }
}
