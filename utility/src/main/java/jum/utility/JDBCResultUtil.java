///*
// * 文  件  名:  JDBCResultUtil.java
// * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
// * 描        述:  描述
// * 创  建  人:  ly-lijinzhong
// * 创建时间:  2018年6月8日
// * 修改内容:  修改内容
// */
//package jum.utility;
//
//import oracle.sql.TIMESTAMP;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.beans.BeanInfo;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Method;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 功能简述:<br>
// * 详细描述:<br>
// * @author 李锦忠, 2018年6月8日
// * @see 相关类#方法
// * @since 产品/模块
// */
//public class JDBCResultUtil {
//	private static Logger log = LoggerFactory.getLogger(JDBCResultUtil.class);
//
//    /**
//     * resultSet转为list集合
//     * @param resultSet
//     * @param clazz
//     * @return
//     */
//    public static <T> List<T> resultToPojo(ResultSet resultSet,Class<T> clazz) {
//    	ArrayList<T> arrayList = new ArrayList<T>();
//    	try {
//            while(resultSet.next()){//遍历resultset集合
//                T newInstance = clazz.newInstance();
//                BeanInfo beanInfo = Introspector.getBeanInfo(newInstance.getClass());
//                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//                int columnCount = resultSet.getMetaData().getColumnCount();//获取总列数
//                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
//                    String name = propertyDescriptor.getName();//获取属性名称
//                    Method writeMethod = propertyDescriptor.getWriteMethod();//获取setter方法
//                    for (int i=1;i<=columnCount;i++) {
//                        String orgColumnName = resultSet.getMetaData().getColumnName(i);
//                        String columnName = underLineToUpcase(orgColumnName);
//                        if (columnName.equals(name) && writeMethod!=null) {
//                        	Object ob = resultSet.getObject(orgColumnName);
//                        	if (TIMESTAMP.class.isInstance(ob)) {
//                        	    ob = ((TIMESTAMP)ob).timestampValue();
//                        	}
//                            writeMethod.invoke(newInstance, ob);//对象属性赋值
//                        }
//                    }
//                }
//                arrayList.add(newInstance);
//            }
//    	} catch(Exception e) {
//    		log.error("结果集转list失败", e);
//    	}
//        return arrayList;
//    }
//
//    /**
//     * 下划线转驼峰
//     * @param str 需要为纯小写
//     * @return
//     */
//    public static String underLineToUpcase(String str){
//        str = StringUtils.lowerCase(str);
//        String[] split = str.split("_");
//        StringBuilder stringBuilder = new StringBuilder(split.length);
//        if(split!=null&&split.length>0){
//        	int j = 1;
//        	if (StringUtils.isBlank(split[0])) {
//        		j = 2;
//        		stringBuilder.append("_");
//        		stringBuilder.append(split[1]);
//        	} else {
//        		stringBuilder.append(split[0]);
//        	}
//            for(;j<split.length;j++){
//                stringBuilder.append(firstToUp(split[j]));
//            }
//
//        }
//        str=stringBuilder.toString();
//        return str;
//    }
//
//    /**
//     * 首字母转大写
//     * @param str
//     * @return
//     */
//    public static String firstToUp(String str){
//        String replaceFirst = str.replaceFirst(str.substring(0,1), str.substring(0,1).toUpperCase());
//        return replaceFirst;
//    }
//
//}
