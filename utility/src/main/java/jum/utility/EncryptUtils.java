/*
 * 文  件  名:  EncryptUtils.java
 * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2017年11月21日
 * 修改内容:  修改内容
 */
package jum.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 * 
 * @author 李锦忠, 2017年11月21日
 * @since 产品/模块
 */
public class EncryptUtils {

    private static Logger log = LoggerFactory.getLogger(com.ly.mp.common.utils.EncryptUtils.class);
    //加密字符编解码方式
    private static String EN_CODE = "utf-8";
    
    /**
     * 功能简述:<br>
     * 详细描述:new String(cipherBytesTmp, enCode)用utf-8编码存在bug<br>
     * 
     * @author 李锦忠
     * @CreateTime 2017年11月21日下午2:13:08
     * @param clearText 明文
     * @param passwd 密码
     * @param salt 盐
     * @param enCode 编解码方式
     * @return
     */
    public static String encrypt(String clearText, String passwd, String salt, String enCode) {
        if (clearText == null || "".equals(clearText) || passwd == null || "".equals(passwd) || enCode == null) {
            return null;
        }
        try {
            //3DES加密
            DESedeKeySpec dks = new DESedeKeySpec(getEnKey(passwd));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherBytesTmp = cipher.doFinal(clearText.getBytes(enCode));
            String cipherStr = new String(cipherBytesTmp, enCode) + salt;
            //MD5哈希
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Src = cipherStr.getBytes(enCode);
            byte[] cipherBytes = md5.digest(md5Src);
            //base64编码
            return Base64.getEncoder().encodeToString(cipherBytes);
        } catch (Exception e) {
            log.error("ERROR:", e);
        }
        return null;
    }
    
    /**
     * 功能简述:<br>
     * 详细描述:<br>
     * 
     * @author 李锦忠
     * @CreateTime 2017年11月21日下午2:13:08
     * @param clearText 明文
     * @param passwd 密码
     * @param salt 盐
     * @return
     */
    public static String encrypt(String clearText, String passwd, String salt) {
        return encrypt(clearText, passwd, salt, EN_CODE);
    }
    
    /** 
     * 功能简述:取得对称秘钥的字节数组<br>
     * 详细描述:3DES要求秘钥长度为24，多截少补0<br>
     * @author 李锦忠
     * @CreateTime 2017年11月21日下午4:22:53
     * @param key
     * @return
     */
    private static byte[] getEnKey(String key) {
        byte[] desKey = null;
        try {
            byte[] keyBytes = key.getBytes("ASCII");
            desKey = new byte[24];
            for (int i=0;i<24;i++) {
                if (i < keyBytes.length) {
                    desKey[i] = keyBytes[i];
                } else {
                    desKey[i] = 0;
                }
            }
        } catch (Exception e) {
            log.error("Exception", e);
        }
        return desKey;
    }
    
    /**
     * 功能简述:<br>
     * 详细描述:<br>
     * 
     * @author 李锦忠
     * @CreateTime 2017年11月21日上午11:55:49
     * @param args
     */
    public static void main(String[] args) {
        String key = "szly98*75EEE373AB70A0C06E050007";
        String salt = "5648245";
        String result = encrypt("f3kds4ds", key, salt);
        //URL编码
        String urlStr = URLEncoder.encode(result);
        System.out.println(result);
        System.out.println(urlStr);
    }
}
