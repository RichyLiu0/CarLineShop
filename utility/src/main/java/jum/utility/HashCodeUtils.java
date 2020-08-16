/*
 * 文  件  名:  HashCodeUtils.java
 * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2017年11月21日
 * 修改内容:  修改内容
 */
package jum.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 * 
 * @author 李锦忠, 2017年11月21日
 * @since 产品/模块
 */
public class HashCodeUtils {

    private static Logger log = LoggerFactory.getLogger(com.ly.mp.common.utils.HashCodeUtils.class);

    //字符编解码方式
    private static String EN_CODE = "utf-8";
    
    /** 
     * 功能简述:<br>
     * 详细描述:<br>
     * @author 李锦忠
     * @CreateTime 2017年11月21日下午2:28:37
     * @param userName 用户名
     * @param passwd  密码明文
     * @param timeStamp 时间戳，格式为'yyyymmddhh24miss'
     * @param salt 盐值
     * @param enCode 编解码方式
     * @return
     */
    public static String signInHashCode(String userName, String passwd, String timeStamp, String salt, String enCode) {
        if (userName == null || "".equals(userName)) {
            userName = "LOGIN_WQIP";
        }
        if (passwd == null || "".equals(passwd) || salt == null || "".equals(salt) || enCode == null || "".equals(enCode)) {
            return null;
        }
        if (timeStamp == null || timeStamp.length() != 14) {
            return null;
        }
        String md5Str = userName + passwd + timeStamp + salt;
        try {
            //MD5哈希
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(md5Str.getBytes(enCode));
            //base64编码
            return Base64.getEncoder().encodeToString(md5Bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("NoSuchAlgorithmException|UnsupportedEncodingException", e);
        }
        return null;
    }
    
    public static String signInHashCode(String userName, String passwd, String timeStamp, String salt) {
        return signInHashCode(userName, passwd, timeStamp, salt, EN_CODE);
    }
    
    /**
     * 功能简述:<br>
     * 详细描述:<br>
     * 
     * @author 李锦忠
     * @CreateTime 2017年11月21日上午11:57:24
     * @param args
     */
    public static void main(String[] args) {
        String userName = "xtadmin";
        String passwd = "lyzd1234";
        String timeStamp = "20171127150023";
        String salt = "619962";
        String result = signInHashCode(userName, passwd, timeStamp, salt);
        //URL编码
        String urlStr = URLEncoder.encode(result);
        System.out.println(result);
        System.out.println(urlStr);
    }
}
