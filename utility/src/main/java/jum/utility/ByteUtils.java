/*
 * 文  件  名:  ByteUtils.java
 * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2018年10月19日
 * 修改内容:  修改内容
 */
package jum.utility;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 * @author 李锦忠, 2018年10月19日
 * @see 相关类#方法
 * @since 产品/模块
 */
public class ByteUtils {
    
    /** 
     * 功能简述:数组合并<br>
     * 详细描述:<br>
     * @author 李锦忠
     * @CreateTime 2018年10月19日下午5:58:42
     * @param data1
     * @param data2
     * @return
     */
    public static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }
    
    /**  
     * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。数组长度为3 
     */  
    public static int bytesToInt(byte[] src) {  
     int value;    
     value = (int) (((src[0] & 0xFF)<<16)  
             |((src[1] & 0xFF)<<8)  
             |(src[2] & 0xFF));  
     return value;  
 }
}
