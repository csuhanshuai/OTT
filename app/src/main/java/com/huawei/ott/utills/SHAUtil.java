package com.huawei.ott.utills;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 提供SHA加密
 */
public class SHAUtil
{

    // 进行SHA-1加密
    public static String SHAEncode(String message)
    {
        String resultString = null;

        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(message.getBytes());
            resultString = bytes2HexString(md.digest());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultString;
    }

    // 将Bytes数据转换成16进制字符串格式
    public static String bytes2HexString(byte[] bts)
    {
        String des = "";
        String tmp = null;
        for(int i = 0; i<bts.length; i++)
        {
            tmp = ( Integer.toHexString(bts[i]&0xFF) );
            if(tmp.length() == 1)
            {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    //Random+"$"+EncryToken+"$"+userid+"$"+terminalid+"$"+terminalip+"$"+mac+"$"+Reserved+"$"+"CTC"
    private static final String Algorithm = "DESede/ECB/PKCS5Padding";// DESede/ECB/PKCS5Padding;DESede

    private static final String DESede = "DESede";

    public static byte[] encrypt(byte[] keybyte, byte[] src) throws NoSuchAlgorithmException, NoSuchPaddingException, Exception
    {
        SecretKey deskey = new SecretKeySpec(keybyte, DESede);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        return c1.doFinal(src);
    }

    public static byte[] decrypt(byte[] keybyte, byte[] src) throws NoSuchAlgorithmException, NoSuchPaddingException, Exception
    {
        SecretKey deskey = new SecretKeySpec(keybyte, DESede);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        return c1.doFinal(src);
    }

    public static String byte2hex(byte[] b)
    {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for(int n = 0; n<b.length; n++)
        {
            stmp = ( java.lang.Integer.toHexString(b[n]&0XFF) );
            if(stmp.length() == 1)
            {
                hs.append("0").append(stmp);
            }else
            {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase(Locale.getDefault());
    }

    public static byte[] hex2byte(String hexStr)
    {
        if(hexStr.length()%2 != 0)
        {
            return null;
        }

        byte[] toBytes = new byte[hexStr.length()/2];
        for(int i = 0, j = 0; i<hexStr.length(); j++, i = i+2)
        {
            int tmpa = Integer.decode("0X"+hexStr.charAt(i)+hexStr.charAt(i+1)).intValue();
            toBytes[j] = (byte)( tmpa&0XFF );
        }
        return toBytes;
    }

}
