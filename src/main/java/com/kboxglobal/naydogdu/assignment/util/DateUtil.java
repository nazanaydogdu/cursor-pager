package com.kboxglobal.naydogdu.assignment.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static Date stringToDate(String dateString){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(dateString));
        return cal.getTime();
    }

    public static Date currentDate(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date addDays(Integer days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date resultDate = calendar.getTime();
        return resultDate;
    }
}
