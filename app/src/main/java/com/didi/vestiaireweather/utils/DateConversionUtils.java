package com.didi.vestiaireweather.utils;

import android.content.Context;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by didi on 11/12/2016.
 * Utils to manipulate dates
 */

public class DateConversionUtils {

    private DateConversionUtils() {}

    /**
     * Format the date as you want to see in weather forecast
     * @param context context
     * @param locale France
     * @param calSelected date
     * @return formatted date
     */
    public static String formatDate(Context context, Locale locale, Calendar calSelected, int flags){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", locale);
        long time = 0;
        try {
            time = sdf.parse(sdf.format(calSelected.getTime())).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DateUtils.formatDateTime(context, time, flags);
    }

    /**
     * <p>Checks if two calendars represent the same day ignoring time.</p>
     * @param cal1  the first calendar, not altered, not null
     * @param cal2  the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * <p>Checks if a calendar date is today.</p>
     * @param cal  the calendar, not altered, not null
     * @return true if cal date is today
     * @throws IllegalArgumentException if the calendar is <code>null</code>
     */
    public static boolean isToday(Calendar cal) {
        return isSameDay(cal, Calendar.getInstance());
    }
}
