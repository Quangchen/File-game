

public final class Skills {
    private static mHashtable a = new mHashtable();
 
    public static void a(Skill var0) {
       a.put(new Short(var0.skillId), var0);
    }
 
    public static Skill a(short var0) {
       return (Skill)a.get(new Short(var0));
    }
 }
 