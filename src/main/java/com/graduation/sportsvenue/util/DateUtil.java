package com.graduation.sportsvenue.util;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {
    /**
     * 将时间转成字符串
     */
    private static  final String STANDARD_FORMATE="yyyy-MM-dd HH:mm:ss";

    public static String dateToString(Date date,String formate){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formate);
    }
    public static String dateToString(Date date){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMATE);
    }

    /**
     * 将字符串转成时间
     */
    public static  Date stringToDate(String str){
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(STANDARD_FORMATE);
        DateTime dateTime = dateTimeFormat.parseDateTime(str);
        return dateTime.toDate();
    }
    public static  Date stringToDate(String str,String format){
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormat.parseDateTime(str);
        return dateTime.toDate();
    }

    public static String timeTchange(String tTime)throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = formatter.parse(tTime);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate=sdf.format(date);
        return sDate;
    }

    public static String dateTochange(String time)throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(time);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String sDate=sdf.format(date);
        return sDate;
    }



    public static Integer getHour(String date1, String date2){
        Long ms = stringToDate(date1).getTime()-stringToDate(date2).getTime();
        return Math.toIntExact(ms / (60 * 60 * 1000));
    }
}