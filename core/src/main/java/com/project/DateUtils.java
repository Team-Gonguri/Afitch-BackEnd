package com.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static String parseDateToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date parseStringToDate(String string) throws ParseException {
        return dateFormat.parse(string);
    }

    public static Date now() throws ParseException {
        return parseStringToDate(parseDateToString(new Date()));
    }
}
