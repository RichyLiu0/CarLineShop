/*
 * 文  件  名:  ParamReplace.java
 * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2019年9月19日
 * 修改内容:  修改内容
 */
package jum.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 * @author 李锦忠, 2019年9月19日
 * @see 相关类#方法
 * @since 产品/模块
 */
public class ParamReplace {
	private static Logger log = LoggerFactory.getLogger(com.ly.mp.common.utils.ParamReplace.class);

		/** 
		 * 功能简述:通过##来传参<br>
		 * 详细描述:<br>
		 * @author 李锦忠
		 * @CreateTime 2019年9月19日上午10:49:25
		 * @param originStr
		 * @param map
		 * @return
		 */
		public static String replaceParam(String originStr, Map<String, String> map) {
			if(originStr == null) {
				return "";
			}
			StringBuffer result = new StringBuffer();
			String[] split = originStr.split("#");	
			for(int i=0;i<split.length;i++) {
			    if(!originStr.startsWith("#")) {
			    	if(i%2==1) {
			    		String param = split[i];		
						String value = null;
						Set<String> set = map.keySet();
						Iterator<String> ite = set.iterator();
						while(ite.hasNext()) {
							String tmp = ite.next();
							if(tmp.equalsIgnoreCase(param)) {
								value = map.get(tmp);
								break ;
							}
						}
						if(value == null) {
							log.error("变量{}取不到对应的值", param);
						} else {
							result.append(value);
						}
			    	} else {
			    		result.append(split[i]);
			    	}	
			    } else {
			    	if(i%2==0) {
			    		String param = split[i];		
						String value = null;
						Set<String> set = map.keySet();
						Iterator<String> ite = set.iterator();
						while(ite.hasNext()) {
							String tmp = ite.next();
							if(tmp.equalsIgnoreCase(param)) {
								value = map.get(tmp);
								break ;
							}
						}
						if(value == null) {
							log.error("变量{}取不到对应的值", param);
						} else {
							result.append(value);
						}
			    	} else {
			    		result.append(split[i]);
			    	}
			    }
			}
			return result.toString();
		}
		
}
