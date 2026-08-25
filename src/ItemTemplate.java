

public final class ItemTemplate {
    public short id;
    public byte type;
    public byte gender;
    public String name;
    public String description;
    public byte level;
    public short iconID;
    public short part;
    public boolean isUpToUp;
 
    public ItemTemplate(short var1, byte var2, byte var3, String var4, String var5, byte var6, short var7, short var8, boolean var9) {
       this.id = var1;
       this.type = var2;
       this.gender = var3;
       this.name = var4;
       this.description = var5;
       this.level = var6;
       this.iconID = var7;
       this.part = var8;
       this.isUpToUp = var9;
    }
 
    public final boolean isTypeBody() {
       return this.type >= 0 && this.type <= 15;
    }
 
    public final boolean isTypeTask() {
       return this.type >= 23 && this.type <= 25;
    }
    public final boolean isTypeMounts() {
        return 29 <= this.type && this.type <= 33;
    }
    public final boolean isTypeBijuu() {
        return 35 <= this.type && this.type <= 38;
    }
 }
 