package com.github.kuma.grocerymanager;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility class for working with dates.
 */
public class DateUtils
{
    /**
     * Utility method to create a Date automatically.
     * @param year The year.
     * @param month The month.
     * @param day The day.
     * @return The created date.
     */
    public static Date makeDate(int year, int month, int day)
    {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
