package work.mathwiki.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import work.mathwiki.core.logger.Logger;

public class MD5 {
    private static final String hexDigits[] = new String[]{
          "0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"
    };

    public static String encode(String msg){
        return encode(msg,null);
    }

    public static String encode(String msg,String charset){
        String result = null;
        try {
            result = msg;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null==charset || "".equals(charset)){
                result = byteArray2HexString(md.digest(msg.getBytes()));
            } else {
                result = byteArray2HexString(md.digest(msg.getBytes(charset)));
            }
        } catch (NoSuchAlgorithmException e) {
            // No way to call here!
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Logger.se("encording string error.");
            e.printStackTrace();
        }
        return result;
    }

    public static String byteArray2HexString(byte[] src){
        StringBuilder result = new StringBuilder();
        for (byte aSrc : src) {
            result.append(byte2HexString(aSrc));
        }
        return result.toString();
    }

    public static String byte2HexString(byte b){
        int n = b;
        if (n<0) n+= 0x100;
        int d1 = n / 0x10;
        int d2 = n % 0x10;
        return hexDigits[d1]+hexDigits[d2];
    }
}
