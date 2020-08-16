package jum.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * ClassName:Truncate
 * Description: 传入Map<String,String>集合,截取字符串，返回list，方便mybatis使用
 * @author BaiZe
 * @date 2020年6月12日
 *
 */
public class Truncate {

    private static Logger log = LoggerFactory.getLogger(com.ly.mp.common.utils.Truncate.class);

    public static Map<String, List> toSingleQuote(Map<String, String> map) {
        String regexp = "\'";
        HashMap<String, List> mp = new HashMap<>();
        for (String key : map.keySet()) {
            ArrayList<String> list = new ArrayList<>();
            if ("".equals(map.get(key)) || map.get(key) == null) {
                map.put(key,map.get(key));
            } else{
                String param = map.get(key).replaceAll(regexp, "");
                String[] split = param.split(",");
                for (String item : split) {
                    list.add(item);
                }
                mp.put(key, list);
            }
        }
        return mp;
    }
}
