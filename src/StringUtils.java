
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Kudo
 */
public class StringUtils {
    public static boolean contains(String text, String keyword) {
        return text != null && keyword != null && text.indexOf(keyword) != -1;
    }
    
    public static String[] splitString(String var0, String var1) {
        int var2 = 0;
        int var3 = var1.length();

        int var4;
        for (var4 = var0.indexOf(var1, 0); var4 != -1; ++var2) {
            var4 += var3;
            var4 = var0.indexOf(var1, var4);
        }

        String[] var7 = new String[var2 + 1];
        var4 = var0.indexOf(var1);
        int var5 = 0;

        int var6;
        for (var6 = 0; var4 != -1; ++var6) {
            var7[var6] = var0.substring(var5, var4);
            var5 = var4 + var3;
            var4 = var0.indexOf(var1, var5);
        }

        var7[var6] = var0.substring(var5, var0.length());
        return var7;
    }
    
    public static String joinString(String[] parts, String delimiter) {
        if (parts == null || parts.length == 0) return "";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
            if (i < parts.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    
    public static String joinString(short[] parts, String delimiter) {
        if (parts == null || parts.length == 0) return "";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
            if (i < parts.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    
    public static String joinString(int[] parts, String delimiter) {
        if (parts == null || parts.length == 0) return "";

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
            if (i < parts.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    
    public static String getDateString(long miliseconds) {
      Calendar var1;
         (var1 = Calendar.getInstance()).setTimeZone(TimeZone.getTimeZone("GMT+7"));
         var1.setTime(new Date(miliseconds));
         int var2 = var1.get(11);
         int var3 = var1.get(12);
         int var4 = var1.get(5);
         int var5 = var1.get(2) + 1;
         int var6 = var1.get(1);
         return var4 + "/" + var5 + "/" + var6 + " " + var2 + "h" + var3 + "'";
   }
}
