package ru.javawebinar.topjava.util;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static String dateToHtml(LocalDateTime date) {
        return FORMATTER.format(date);
    }

    public static LocalDateTime stringToDate (String date){
            return LocalDateTime.parse(date, FORMATTER);

    }
}
