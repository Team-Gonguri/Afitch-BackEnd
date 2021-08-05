package com.project.utils;

import com.project.exception.UnExpectedError;

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

    public static Date now() {
        try {
            return parseStringToDate(parseDateToString(new Date()));
        } catch (ParseException e) {
            throw new UnExpectedError("Unexpected Date Parsing Error");
        }
    }
}
