package com.project.utils;

import com.project.exception.UnExpectedError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat detailDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String parseDateToSimpleString(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date parseStringToDate(String string) throws ParseException {
        return simpleDateFormat.parse(string);
    }

    public static String parseDateToDetailString(Date date){
        return detailDateFormat.format(date);
    }

    public static Date now() {
        try {
            return parseStringToDate(parseDateToSimpleString(new Date()));
        } catch (ParseException e) {
            throw new UnExpectedError("Unexpected Date Parsing Error");
        }
    }
}
