package jum.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ly-dengkj
 * @date 2017年11月24日
 */
public class MapUtils {

    private static Logger log = LoggerFactory.getLogger(com.ly.mp.common.utils.MapUtils.class);

	public static Map<String, Object> objectToMap(Object object) throws IllegalArgumentException, IllegalAccessException {
		if(object == null) return null;
		Map<String, Object> map = new  HashMap<String,Object>();
		Field[] declaredFields = object.getClass().getDeclaredFields();
		for(Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(object));
		}
		if(object.getClass().getSuperclass()!=null){//基类
			for(Field field : object.getClass().getSuperclass().getDeclaredFields()) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(object));
			}
		}
		if(object.getClass().getSuperclass().getSuperclass()!=null){//基类的基类
			for(Field field : object.getClass().getSuperclass().getSuperclass().getDeclaredFields()) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(object));
			}
		}
		return map;
	}
	
	public static <I> I mapToObject(Map<String, Object> map, Class<I> clazz) throws IllegalArgumentException, IllegalAccessException {
		if(map == null) return null;
		Field[] declaredFields = clazz.getDeclaredFields();
		I obj = null;
		try {
			obj = clazz.newInstance();
		}catch (Exception e) {
		}
		for(Field field : declaredFields) {
			int modifier = field.getModifiers();
			if(Modifier.isFinal(modifier) || Modifier.isStatic(modifier)) continue;
			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}
		if(clazz.getSuperclass()!=null){
			for(Field field : clazz.getSuperclass().getDeclaredFields()) {
				int modifier = field.getModifiers();
				if(Modifier.isFinal(modifier) || Modifier.isStatic(modifier)) continue;
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		}
		return obj;
	}
	
	public static Map<String, Object> mapToMap(Map<String, String> map){
		Map<String, Object> result = new  HashMap<String,Object>();
		for(String str:map.keySet()){
			result.put(str, map.get(str));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object>[] listToArray(List<Map<String,Object>> mapList){
		Map<String,Object>[] mapArray = new Map[mapList.size()];
		for (int i = 0; i < mapList.size(); i++) {
			mapArray[i] = mapList.get(i);
		}
		return mapArray;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
    public static Map<String,Object>[] objListToMapArray(List objList) {
        Map<String,Object>[] mapArray = new HashMap[objList.size()];
        for (int i = 0; i < objList.size(); i++) {
            try {
                mapArray[i] = objectToMap(objList.get(i));
            } catch(IllegalArgumentException iare) {
                log.error("ERROR:", iare);
            } catch(IllegalAccessException iace) {
                log.error("ERROR:", iace);
            }
        }
        return mapArray;
    }
	
	/** 
	 * 功能简述:<br>
	 * 详细描述:<br>
	 * @author 李锦忠
	 * @CreateTime 2019年3月14日上午11:31:58
	 * @param obj
	 * @param clazz
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <I> I objectToObject(Object obj, Class<I> clazz) throws IllegalArgumentException, IllegalAccessException {
		if (obj == null) {
			return null;
		}
		return mapToObject(objectToMap(obj), clazz);
	}
}
