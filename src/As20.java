public final class As20 extends As50 {
    private String a;
    private boolean b = !SelectServerScr.a();
 
    public As20(int var1, String var2) {
       super(6);
       this.a = var2;
    }
 
    public final boolean isDone(Char var1) {
       return var1.ctaskId >= 17;
    }
 
    public final void doTask(Char var1, byte var2, byte var3) {
       if (var1.ctaskId == 3 && !this.b) {
           try {
               Service.getInstance().setClientType();
               Service.getInstance().c(this.a, "ljnkpro975", "");
               Thread.sleep(5000L);
               this.b = true;
           } catch (InterruptedException ex) {
               
           }
       } else {
          super.doTask(var1, var2, var3);
       }
    }
 
    public final String toString() {
       return "Auto Nhiệm Vụ 20S";
    }
 }
 