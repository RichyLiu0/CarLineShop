package com.ly.mp.common.utils.json;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ly.mp.common.utils.DateUtils;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;

/**
 * @author ly-dengkj
 * @date 2017年11月24日
 */
@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
public class JsonUtils {

	static {
		AbstractObjectMorpher timestampMorpher = new AbstractObjectMorpher() {
			@Override
			public Class<Timestamp> morphsTo() {
				return Timestamp.class;
			}
			@Override
			public Object morph(Object value) {
				if (value == null) {
					return null;
				}
				if (Timestamp.class.isAssignableFrom(value.getClass())) {
					return (Timestamp) value;
				}
				if (!supports(value.getClass())) {
					throw new MorphException(value.getClass() + "是不支持的类型");
				}
				String strValue = (String) value;
				try {
					return DateUtils.formatTimestamp(strValue);
				} catch (ParseException e) {
					throw new MorphException(value.getClass() + "不符合timestamp转换格式");
				}
			}

			public boolean supports(Class clazz) {
				return String.class.isAssignableFrom(clazz);
			}
		};
		AbstractObjectMorpher dateMorpher = new AbstractObjectMorpher() {
			@Override
			public Class<java.sql.Date> morphsTo() {
				return java.sql.Date.class;
			}

			@Override
			public Object morph(Object value) {
				if (value == null) {
					return null;
				}
				if (java.sql.Date.class.isAssignableFrom(value.getClass())) {
					return (java.sql.Date) value;
				}
				if (!supports(value.getClass())) {
					throw new MorphException(value.getClass() + "是不支持的类型");
				}
				String strValue = (String) value;
				try {
					return new java.sql.Date(DateUtils.formatDate(strValue).getTime());
				} catch (ParseException e) {
					throw new MorphException(value.getClass() + "不符合date转换格式");
				}
			}

			public boolean supports(Class clazz) {
				return String.class.isAssignableFrom(clazz);
			}
		};
		AbstractObjectMorpher utilDateMorpher = new AbstractObjectMorpher() {
			@Override
			public Class<java.util.Date> morphsTo() {
				return java.util.Date.class;
			}

			@Override
			public Object morph(Object value) {
				if (value == null) {
					return null;
				}
				if (java.util.Date.class.isAssignableFrom(value.getClass())) {
					return (java.util.Date) value;
				}
				if (!supports(value.getClass())) {
					throw new MorphException(value.getClass() + "是不支持的类型");
				}
				String strValue = (String) value;
				try {
					return new java.util.Date(DateUtils.formatDate(strValue).getTime());
				} catch (ParseException e) {
					throw new MorphException(value.getClass() + "不符合date转换格式");
				}
			}

			public boolean supports(Class clazz) {
				return String.class.isAssignableFrom(clazz);
			}
		};
		JSONUtils.getMorpherRegistry().registerMorpher(dateMorpher);
		JSONUtils.getMorpherRegistry().registerMorpher(utilDateMorpher);
		JSONUtils.getMorpherRegistry().registerMorpher(timestampMorpher);
	}

	/**JSON字符串转为List<T>,可配置<T>成员属性类型
	 * @param jsonString
	 * @param clazz
	 * @param classMap
	 * @return
	 */
	public static <T> List<T> toJavaObjectList(String jsonString, Class<T> clazz, Map<String, Class> classMap) {
		if(jsonString==null||"".equals(jsonString.trim())){
			return new ArrayList<T>();
		}
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List<T> list = Arrays.asList((T[])JSONArray.toArray(jsonArray, clazz, classMap));
		return list;
	}

	/**JSON字符串转为List<T>
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> toJavaObjectList(String jsonString, Class<T> clazz) {
		if(jsonString==null||"".equals(jsonString.trim())){
			return new ArrayList<T>();
		}
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List<T> list =  new ArrayList<T>(jsonArray.toCollection(jsonArray, clazz));
		return list;
	}

	/**JSON字符串转为List<Map<String, Object>>
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> toJavaObjectList(String jsonString) {
		if(jsonString==null||"".equals(jsonString.trim())){
			return new ArrayList<Map<String, Object>>();
		}
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(jsonArray.toCollection(jsonArray, Map.class));
		return list;
	}

	/**JSON字符串转为<T>,可配置<T>成员属性类型
	 * @param jsonString
	 * @param clazz
	 * @param classMap
	 * @return
	 */
	public static <T> T toJavaObject(String jsonString, Class<T> clazz, Map<String, Class> classMap) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return (T) JSONObject.toBean(jsonObject, clazz, classMap);
	}

	/**SON字符串转为<T>
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T toJavaObject(String jsonString, Class<T> clazz) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return (T) JSONObject.toBean(jsonObject, clazz);
	}

	/**JSON字符串转为Map<String, Object>
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> toMap(String jsonString) {
		return toJavaObject(jsonString, Map.class);
	}

	/**返回JsonConfig,已配置将对象成员属性date,timestamp可转为指定格式,默认yyyy-MM-dd HH:mm:ss
	 * @param formatStr
	 * @return
	 */
	private static JsonConfig getJsonConfig(String formatStr) {
		if (formatStr == null || "".equals(formatStr.trim())) {
			formatStr = "yyyy-MM-dd HH:mm:ss";
		}
		final SimpleDateFormat datePaser = new SimpleDateFormat(formatStr);
		JsonValueProcessor jsonValueProcessor1 = new JsonValueProcessor() {
			public Object processObjectValue(String key, Object value, JsonConfig arg2) {
				if (value == null)
					return "";
				if (value instanceof Date) {
					String str = datePaser.format(value);
					return str;
				}
				return value.toString();
			}
			public Object processArrayValue(Object value, JsonConfig arg1) {
				String[] obj = {};
				if (value instanceof Date[]) {
					Date[] dates = (Date[]) value;
					obj = new String[dates.length];
					for (int i = 0; i < dates.length; i++) {
						obj[i] = datePaser.format(dates[i]);
					}
				}
				return obj;
			}
		};
		JsonValueProcessor jsonValueProcessor2 = new JsonValueProcessor() {
			public Object processObjectValue(String key, Object value, JsonConfig arg2) {
				if (value == null)
					return "";
				if (value instanceof Timestamp) {
					String str = datePaser.format(value);
					return str;
				}
				return value.toString();
			}

			public Object processArrayValue(Object value, JsonConfig arg1) {
				String[] obj = {};
				if (value instanceof Timestamp[]) {
					Timestamp[] dates = (Timestamp[]) value;
					obj = new String[dates.length];
					for (int i = 0; i < dates.length; i++) {
						obj[i] = datePaser.format(dates[i]);
					}
				}
				return obj;
			}
		};
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.sql.Date.class,jsonValueProcessor1);
		config.registerJsonValueProcessor(Date.class,jsonValueProcessor1);
		config.registerJsonValueProcessor(java.sql.Timestamp.class,jsonValueProcessor2);
		return config;
	}
	
	public static List<String> toListStr(String jsonStr){
		return new ArrayList<String>(JSONArray.toCollection(JSONArray.fromObject(jsonStr), String.class));
	}

	public static String toJson(Object object, String format) {
		JSONObject json = JSONObject.fromObject(object, getJsonConfig(format));
		return json.toString();
	}
	
	public static String toJson(Object object) {
		return toJson(object, null);
	}
	
	public static String toJsonArray(Object object, String format) {
		JSONArray json = JSONArray.fromObject(object, getJsonConfig(format));
		return json.toString();
	}
	
	public static String toJsonArray(Object object) {
		return toJsonArray(object, null);
	}
	
}