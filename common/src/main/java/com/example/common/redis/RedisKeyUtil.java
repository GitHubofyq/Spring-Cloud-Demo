package com.example.common.redis;

/**
 * redisKey设计
 */
public class RedisKeyUtil {

    /**
     * 主数据系统标识
     */
    public static final String KEY_PREFIX = "hython";
    /**
     * 分割字符，默认[:]，使用:可用于rdm分组查看
     */
    private static final String KEY_SPLIT_CHAR = ":";

    /**
     * 其他命名方式
     * 日常任务
     **/
    public static final String KEY_DAILY_TASK = "daily_task";

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值:列名
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @param column 列名
     * @return
     */
    public static String getKeyWithColumn(String tableName,String majorKey,String majorKeyValue,String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }
    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @return
     */
    public static String getKey(String tableName,String majorKey,String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue);
        return buffer.toString();
    }

    /**
     * redis的key
     * 形式为：
     * 模块名:表名:
     * @Param [moduleName, tableName]
     * @return java.lang.String
     **/
    public static String getKeyWithModule(String moduleName, String tableName, String... args){
        return keyBuilder(null, moduleName, tableName, null, args);
    }

    /**
     * redis的key键规则定义
     * @param prefix 项目前缀
     * @param module 模块名称
     * @param func 方法名称
     * @param args 参数..
     * @return key
     */
    public static String keyBuilder(String prefix, String module, String tableName, String func, String... args) {
        // 项目前缀
        if (prefix == null) {
            prefix = KEY_PREFIX;
        }
        StringBuilder key = new StringBuilder(prefix);
        // KEY_SPLIT_CHAR 为分割字符
        key.append(KEY_SPLIT_CHAR).append(module);

        if (null != tableName && !"".equals(tableName.trim())) {
            key.append(KEY_SPLIT_CHAR).append(tableName);
        }
        if (null != func && !"".equals(func.trim())) {
            key.append(KEY_SPLIT_CHAR).append(func);
        }
        for (String arg : args) {
            key.append(KEY_SPLIT_CHAR).append(arg);
        }
        return key.toString();
    }

}
