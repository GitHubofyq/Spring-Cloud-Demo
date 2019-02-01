package com.example.common.utils.date;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @ ClassName  ：DateUtil
 * @ Description：TODO
 * @ Author     ：yuqin
 * @ createTime : 2019-01-09
 */
public class DateUtil {

    public static final String UTC = "UTC"; // 协调世界时间
    public static final String GMT = "GMT"; // 格林尼治时间
    public static final String EST = "EST"; // 东部标准时间
    public static final String CST = "CST"; // 中央标准时间
    public static final String PST = "PST"; // 太平洋标准时间

    public static final SimpleDateFormat Standard_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat UTC_Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static void main(String[] args) {
        String utcTimeStr = getUTCTimeStr();
        System.out.println("utcTimeStr: "+utcTimeStr);
        try {
            System.out.println("utcTimeStr: "+Standard_Format.format(UTC_Format.parse(utcTimeStr)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date gmtDate = UTCTimeToDateTime(utcTimeStr);
        System.out.println("gmtDate: "+Standard_Format.format(gmtDate));

    }

    public static String getUTCTimeStr() {
        Date utcTime = getUTCTime();
        String format = UTC_Format.format(utcTime);
        return format;
    }

    private static String dateFormat(Date dateTime, String pattern) {
        String dateStr = null;
        if (null != dateTime) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateStr = dateFormat.format(dateTime);
        }
        return dateStr;
    }

    public static Date getUTCTime() {
        // 1. 获得本地时间
        Calendar cal = Calendar.getInstance();
        // 2. 获得时区和 GMT-0 的时间差,偏移量
        int zoneOffSet = cal.get(Calendar.ZONE_OFFSET);
        // 3. 获取夏令时差
        int dstOffSet = cal.get(Calendar.DST_OFFSET);
        // 4. 从本地时间里扣除这些差量，即可以取得UTC时间
        cal.add(Calendar.MILLISECOND, -(zoneOffSet + dstOffSet));
        return cal.getTime();
    }

    // 日期转换 UTC 2019-01-10T02:20:40.741Z --> 2019-01-10 10:20:40
    public static Date UTCTimeToDateTime(String utcTime) {
        UTC_Format.setTimeZone(TimeZone.getTimeZone(GMT)); //时区
        try {
            Date date = UTC_Format.parse(utcTime);
            return date;
        } catch (ParseException e) {
            System.out.println("日期转换异常");
            e.printStackTrace();
        }
        return null;
    }
}
