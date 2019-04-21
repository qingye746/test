package com.qingye.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: qingye
 * @Date: 2019/4/1 0001 9:08
 * @Version 1.0
 */
public class DateUtils {
    /**
     * 日期转换字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 字符串转日期
     * @param str
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String str,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        return date;
    }
}
