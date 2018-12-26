package com.example.common.utils.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ ClassName  ：JsonUtils
 * @ Description：TODO
 * @ Author     ：yuqin
 * @ createTime : 2018-10-13
 */
public class JsonUtils {

    // 定义HFJson工具类实例变量，采用懒汉单例模式，使用volatile保证线程可见性
    private static volatile JsonUtils inst;

    // 默认的日期格式
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private JsonUtils(){}

    public static JsonUtils getInstance(){
        // 不直接使用同步加锁，先判断是否为null，不为null直接返回，以提高访问性能
        if(inst == null) {
            synchronized (JsonUtils.class) {
                // 因为先判断再进入同步代码块，所以进入同步代码块需要再次判断，不然同样会引发线程安全问题
                inst = inst == null
                        ? new JsonUtils()
                        : inst;
            }
        }
        return inst;
    }

    /**
     * @Author Administrator
     * @Description  TODO 将对象序列化为字符串，调用者指定日期格式
     * @Date 2018/10/13 9:43
     * @Param [o, dateFormat]
     * @return java.lang.String
     **/
    public String toJsonString(Object o, String dateFormat){
        String result = "";
        if(o != null){
            result = dateFormat != null
                    ? JSON.toJSONStringWithDateFormat(o, dateFormat)
                    : JSON.toJSONStringWithDateFormat(o, JsonUtils.DATE_FORMAT);
        }
        return result;
    }

    /**
     * @Author Administrator
     * @Description  TODO 将对象序列化为字符串，默认日期格式为“yyyy-MM-dd HH:mm:ss”
     * @Date 2018/10/13 9:44
     * @Param [o]
     * @return java.lang.String
     **/
    public String toJsonString(Object o){
        String result = "";
        if(o != null){
            result = toJsonString(o, JsonUtils.DATE_FORMAT);
        }
        return result;
    }

    /**
     * @Author Administrator
     * @Description  TODO 将字符串或JavaBean或Map转化为JSONObject
     * @Date 2018/10/13 9:47
     * @Param [o]
     * @return com.alibaba.fastjson.JSONObject
     **/
    public JSONObject parseJsonObject(Object o){
        JSONObject result = null;
        result = o instanceof String
                ? JSON.parseObject(String.class.cast(o))
                : JSON.parseObject(toJsonString(o));
        return result;
    }

    /**
     * @Author Administrator
     * @Description  TODO 将字符串或数组或List转化为JSONArray
     * @Date 2018/10/13 9:54
     * @Param [o]
     * @return com.alibaba.fastjson.JSONArray
     **/
    public JSONArray parseJSONArray(Object o){
        JSONArray result = null;
        result = o instanceof String
                ? JSON.parseArray(String.class.cast(o))
                : JSON.parseArray(toJsonString(o));
        return result;
    }

    /**
     * @Author Administrator
     * @Description  TODO 字符串Map、JavaBean转化为Map、JavaBean
     * @Date 2018/10/13 9:56
     * @Param [o, clazz]
     * @return T
     **/
    public <T> T parseObject(Object o, Class<T> clazz){
        T result = null;
        result = o instanceof String
                ? JSON.parseObject(String.class.cast(o), clazz)
                : JSON.parseObject(toJsonString(o), clazz);
        return result;
    }

    /**
     * @Author Administrator
     * @Description  TODO 将字符串、数组转换为List
     * @Date 2018/10/13 9:58
     * @Param [o, clazz]
     * @return java.util.List<T>
     **/
    public <T> List<T> parseList(Object o, Class<T> clazz){
        List<T> result = null;
        result = o instanceof String
                ? JSON.parseArray(String.class.cast(o), clazz)
                : JSON.parseArray(toJsonString(o), clazz);
        return result;
    }

    public List<Map> parseList(Object o){
        return parseList(o, Map.class);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("date", new Date());
        System.out.println(JsonUtils.getInstance().toJsonString(map));
        System.out.println(JsonUtils.getInstance().toJsonString(map, "yyyy-MM-dd"));
        JSONObject jsonObject = JsonUtils.getInstance().parseJsonObject(map);
        System.out.println(jsonObject.get("name"));
    }


}
