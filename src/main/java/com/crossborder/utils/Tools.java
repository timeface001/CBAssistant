package com.crossborder.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by s on 2018/3/24.
 */
public class Tools {

    /**
     * 获取两个时间的时间差，精确到秒
     *
     * @param start
     * @param end
     * @return String
     */
    public static String timeDifference(long start, long end) {

        long between = end - start;
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String timeDifference = day + "天" + hour + "小时" + min + "分" + s + "秒";
        return timeDifference;
    }

    /**
     * 字符串转为date
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 判断是否当天
     *
     * @param str
     * @param formatStr
     * @return
     * @throws Exception
     */
    public static boolean isToday(String str, String formatStr) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        }
        return false;
    }

    public static String createIntlTrackNum() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("YT");
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 10);
            stringBuffer.append(random);
        }
        return stringBuffer.toString();
    }
    public static String getStatusStr(String status) {
        if (status.equals("1")) {
            return "新单";
        } else if (status.equals("2")) {
            return "备货";
        } else if (status.equals("3")) {
            return "缺货";
        } else if (status.equals("4")) {
            return "发货";
        } else if (status.equals("5")) {
            return "问题";
        } else if (status.equals("6")) {
            return "退款";
        } else if (status.equals("7")) {
            return "妥投";
        } else if (status.equals("8")) {
            return "代发";
        }
        return "";
    }
}

