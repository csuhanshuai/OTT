package com.huawei.ott.utills;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil
{
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
            stmp = ( Integer.toHexString(b[n]&0XFF) );
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
            //			AppLogger.error("hex2bytes's hexStr length is not even.");
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

    /**
     * 加密入口
     *
     * @param arg
     *         需要加密的明文
     * @param pw
     *         密码
     * @return 密文
     */
    public static String caculate(String arg, String pw)
    {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        String hwmd = null;
        try
        {
            hwmd = huaWeiMD5(pw);
        }catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        final byte[] rawKey = hwmd.getBytes();
        final byte[] keyBytes = new byte[24];

        for(int i = 0; i<rawKey.length; i++)
        {
            keyBytes[i] = rawKey[i];
        }

        for(int i = rawKey.length; i<keyBytes.length; i++)
        {
            keyBytes[i] = (byte)0;
        }

        String szSrc = arg;
        byte[] encoded = null;

        try
        {
            encoded = encrypt(keyBytes, szSrc.getBytes());
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return new String(byte2hex(encoded));
    }

    /**
     * @param plainPwd
     *         明文密码
     * @return md5之后的 密码
     *
     * @throws NoSuchAlgorithmException
     */
    public static String huaWeiMD5(String plainPwd) throws NoSuchAlgorithmException
    {
        byte[] id = plainPwd.getBytes();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(id);
        md.update("99991231".getBytes());            // “99991231” mentioned in XML-API DOC

        byte[] buffer = md.digest();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<buffer.length; i++)
        {
            sb.append(Integer.toHexString((int)buffer[i]&0xff));
        }
        return sb.substring(0, 8);        // only use first 8 characters
    }

    /**
     * 发送3.3认证 Request请求 键值对转XML格式
     *
     * @param map
     *         键值对
     * @return
     */
    public static String maptoXML(Map map)
    {
        String XMLreq = "<AuthenticateReq>";
        Iterator iter = map.entrySet().iterator();

        while(iter.hasNext())
        {
            Map.Entry entry = (Map.Entry)iter.next();
            String key = entry.getKey().toString();
            String val = entry.getValue().toString();
            String result = "<"+key+">"+val+"</"+key+">";
            XMLreq += result;
        }
        XMLreq += "</AuthenticateReq>";

        return new String(XMLreq);
    }

}
