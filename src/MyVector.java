import java.util.Vector;

public final class MyVector extends Vector {
   public MyVector() {
   }

   public final synchronized void addElement(Object var1) {
      super.addElement(var1);
   }

   public final synchronized Object elementAt(int var1) {
      return super.elementAt(var1);
   }
   
   public final synchronized void setElementAt(Object var1, int var2) {
      super.setElementAt(var1, var2);
   }
   
//   public final synchronized int indexOf(Object var1) {
//      return super.indexOf(var1);
//   }
   
   public final synchronized boolean setElementAt(Object oldObj, Object newObj) {
        int index = this.indexOf(oldObj);
        if (index == -1) {
            return false; // không tìm thấy
        }
        super.setElementAt(newObj, index);
        return true;
    }

   public final synchronized void removeAllElements() {
      super.removeAllElements();
   }

   public final synchronized boolean removeElement(Object var1) {
      return super.removeElement(var1);
   }

   public final synchronized void insertElementAt(Object var1, int var2) {
      super.insertElementAt(var1, var2);
   }

   public final synchronized void removeElementAt(int var1) {
      super.removeElementAt(var1);
   }
}
