package com.github.kuma.grocerymanager;

import org.joda.time.DateTime;
import org.joda.time.Days;

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

    /**
     * Utility method to count number of days between two Date objects
     * @param firstdate
     * @param seconddate
     * @return
     *  Negative numbers: the seconddate is before the firstdate
     *  Positive numbers: the seconddate is behind the firstdate
     *  Zero: firstdate is the same date as the seconddate
     */
    public static int getDaysBetween(Date firstdate, Date seconddate)
    {
        DateTime firstDate_joda = new DateTime(firstdate);
        DateTime secondDate_joda = new DateTime(seconddate);
        int daysBetween = Days.daysBetween(firstDate_joda.withTimeAtStartOfDay(),secondDate_joda.withTimeAtStartOfDay()).getDays();
        return daysBetween;
    }
}
