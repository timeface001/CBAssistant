package com.crossborder.utils;

import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fengsong on 2018/4/15.
 */
public class GeneralUtils {

    public static String absPath(){
        return ClassUtils.getDefaultClassLoader().getResource("").getPath();
    }


    public static Map<String, Object> genMap(Object... keyAndValues) {
        Map<String, Object> result = new HashMap<>();
        if (keyAndValues != null && keyAndValues.length % 2 == 0) {
            for (int i = 0; i < keyAndValues.length; i++) {
                result.put(keyAndValues[i].toString(), keyAndValues[++i]);
            }
        }
        return result;
    }

    public static String getUUID32() {

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();

    }

    public static String getUUID16() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

    public static String getUserId(){
        return  ((Map<String, Object>) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user")).get("USER_ID").toString();
    }

    public static String nullToEmpty(Object obj) {
        return obj == null ? "" : obj.toString();
    }


    public static String getRandomString(int length){
        String str="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();

        StringBuffer sb=new StringBuffer();

        for(int i=0;i<length;i++){

            int number =random.nextInt(46);

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static Date getDateFromStr(String time) {
        if (org.apache.commons.lang3.StringUtils.isBlank(time)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String localToUTC(Date localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate = localTime;

        long localTimeInMillis = localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate = new Date(calendar.getTimeInMillis());
        return GeneralUtils.formatDate(utcDate, "yyyy-MM-dd'T'hh:mm:ss'Z'");
    }

    public static void main(String[] args) {
        System.out.println(formatDate(new Date(), "yyyy-MM-dd'T'HH:mm:ss Z"));
    }

    public static boolean isNotNullOrEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static String formatTwo(BigDecimal val) {
        java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");

        return myformat.format(val);
    }

    public static String mutiHalfTwo(BigDecimal val) {
        DecimalFormat df1 = new DecimalFormat("0.00");
        return df1.format(val);

    }

}
