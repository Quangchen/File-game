

public final class Part {
    public PartImage[] partImages;
 
    public Part(int var1) {
       if (var1 == 0) {
          this.partImages = new PartImage[8];
       }
 
       if (var1 == 1) {
          this.partImages = new PartImage[18];
       }
 
       if (var1 == 2) {
          this.partImages = new PartImage[10];
       }
 
       if (var1 == 3) {
          this.partImages = new PartImage[2];
       }
 
    }
 }
 