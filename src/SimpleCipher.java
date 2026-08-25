/**
 *
 * @author Kudo
 */
public class SimpleCipher {
    private static final byte KEY = 0x5A;

    public static String encrypt(String input) {
        byte[] inputBytes = input.getBytes();
        byte[] outputBytes = new byte[inputBytes.length];

        for (int i = 0; i < inputBytes.length; i++) {
            outputBytes[i] = (byte)(inputBytes[i] ^ KEY);
        }

        return base64Encode(outputBytes);
    }

    public static String decrypt(String input) {
        byte[] inputBytes = base64Decode(input);
        byte[] outputBytes = new byte[inputBytes.length];

        for (int i = 0; i < inputBytes.length; i++) {
            outputBytes[i] = (byte)(inputBytes[i] ^ KEY);
        }

        return new String(outputBytes);
    }

    private static String base64Encode(byte[] data) {
        final String base64chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        StringBuffer result = new StringBuffer();
        int padding = 0;

        for (int i = 0; i < data.length; i += 3) {
            int b = ((data[i] & 0xFF) << 16) & 0xFFFFFF;
            if (i + 1 < data.length) {
                b |= (data[i + 1] & 0xFF) << 8;
            } else {
                padding++;
            }
            if (i + 2 < data.length) {
                b |= (data[i + 2] & 0xFF);
            } else {
                padding++;
            }

            for (int j = 0; j < 4 - padding; j++) {
                int c = (b & 0xFC0000) >> 18;
                result.append(base64chars.charAt(c));
                b <<= 6;
            }
        }

        for (int i = 0; i < padding; i++) {
            result.append("=");
        }

        return result.toString();
    }

    private static byte[] base64Decode(String s) {
        final String base64chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        int pad = 0;
        if (s.endsWith("==")) pad = 2;
        else if (s.endsWith("=")) pad = 1;

        int len = s.length() * 6 / 8 - pad;
        byte[] result = new byte[len];

        int index = 0;
        int buffer = 0;
        int bitsLeft = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '=') break;

            int val = base64chars.indexOf(c);
            if (val == -1) continue;

            buffer = (buffer << 6) | val;
            bitsLeft += 6;

            if (bitsLeft >= 8) {
                bitsLeft -= 8;
                result[index++] = (byte)((buffer >> bitsLeft) & 0xFF);
            }
        }

        return result;
    }
}
