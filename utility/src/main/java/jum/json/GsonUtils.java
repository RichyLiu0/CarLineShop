/*
 * 文  件  名:  GsonUtils.java
 * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2017年11月27日
 * 修改内容:  修改内容
 */
package com.ly.mp.common.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 * @author 李锦忠, 2017年11月27日
 * @see 相关类#方法
 * @since 产品/模块
 */
public class GsonUtils {
    
    private static Gson instance = null;
    
    static {
        //instance = new Gson();
        instance = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
                .create();
    }
    
    public static Gson instance() {
        return instance;
    }
}
