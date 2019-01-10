package com.example.common.utils.base;


import java.util.HashMap;
import java.util.Map;

/**
 * @ ClassName  ：MapUtils
 * @ Description：TODO
 * @ Author     ：yuqin
 * @ createTime : 2018-10-13
 */
public class MapUtils {

    /**
     *  bean -> map
     **/
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {

        }
        return map;
    }


    /**
     *  比较两个map是否相等
     **/
    public boolean compareMap(Map<String, Object> m1, Map<String, Object> m2){
        boolean flag = false;
        for(Map.Entry<String, Object> entry1 : m1.entrySet()){

            Object m1value = entry1.getValue() == null ? "" : entry1.getValue();
            Object m2value = m2.get(entry1.getKey()) == null ? "" : m2.get(entry1.getKey());

            if (!m1value.equals(m2value)) {//若两个map中相同key对应的value不相等
                flag = true;
            }
        }
        return flag;
    }

    /**
     * key : 下划线转大写
     **/
    public static Map<String, Object> keyUnLineToUpCase(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> oldMap : map.entrySet()) {
            String key = oldMap.getKey();
            boolean contains = key.contains("_");
            if (contains) {
                newMap.put(formatKey(key), oldMap.getValue());
            } else {
                newMap.put(key, oldMap.getValue());
            }
        }
        return newMap;
    }

    private static String formatKey(String key) {
        StringBuffer sb = new StringBuffer();
        String[] split = key.toLowerCase().split("_");// 下划线
        int length = split.length;
        sb.append(split[0]);
        for (int i=1; i<length; i++) {
            if (length > 1) {
                String cutfiled = split[i];
                sb.append(cutfiled.substring(0, 1).toUpperCase()
                        + cutfiled.substring(1, cutfiled.length()));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name_yes", "用户名");
        map.put("user_age_yes", "用户名");
        map.put("user_gender_yes", "用户名");
        map.put("user", "用户名");
        Map<String, Object> newMap = MapUtils.keyUnLineToUpCase(map);
        System.out.println(newMap);
    }
}
