package com.cs50.finalprojectcs50.utils;

import androidx.room.TypeConverter;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateConverters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date transformDateFormat(String dateString, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date startOfWeek() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withDayOfWeek(Calendar.SUNDAY).withTime(0, 0, 0, 0);

        return dateTime.toDate();
    }

    public static Date endOfWeek() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withDayOfWeek(Calendar.SATURDAY).withTime(0, 0, 0, 0 );

        return dateTime.toDate();
    }

    public static Date startOfMonth() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withDayOfMonth(1).withTime(0, 0, 0, 0);

        return dateTime.toDate();
    }

    public static Date endOfMonth() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withDayOfMonth(dateTime.dayOfMonth().getMaximumValue()).withTime(0, 0, 0, 0);

        return dateTime.toDate();
    }

    public static Date startOfDate() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withTime(0, 0, 0, 0);
        return dateTime.toDate();
    }

    public static Date endOfDate() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withTime(23, 59, 59, 59);
        return dateTime.toDate();
    }
}

