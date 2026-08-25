

public final class TraNhiemVu extends As10 {
    private int a;
 
    public TraNhiemVu() {
       this.a = Char.getMyChar().ctaskId;
    }
 
    public final boolean isDone(Char var1) {
       return var1.ctaskId > this.a;
    }
 
    public final void doTask(Char var1, byte var2, byte var3) {
    }
 
    public final String toString() {
       return "Trả NV";
    }
 }
 