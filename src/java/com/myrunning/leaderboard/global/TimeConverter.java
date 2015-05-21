package com.myrunning.leaderboard.global;

/**
 *  File: TimeConverter.java
 *  Author:  Joshua Forester
 *  Date: 2009/10/01
 *  Description: Util for converting time or time period form input to data types.
 **/

import java.lang.IllegalArgumentException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Timestamp;



/**
 *  A utility class for converting time or time period form input to data types.
 */
public final class TimeConverter {


    public static final int MINUTES_IN_HOUR   =       60;
    public static final int SECONDS_IN_YEAR   = 31536000;
    public static final int SECONDS_IN_WEEK   =   604800;
    public static final int SECONDS_IN_DAY    =    86400;
    public static final int SECONDS_IN_HOUR   =     3600;
    public static final int SECONDS_IN_MINUTE =       60;
    public static final long MILLIS_IN_MINUTE =    60000L;
    public static final long MILLIS_IN_SECOND =     1000L;
    private static final int YEAR  = 0;
    private static final int MONTH = 1;
    private static final int DAY   = 2;



    /**
     *  Determines if a date is valid.
     *  @param year the year
     *  @param month the month (1-12)
     *  @param dayOfMonth the day of the month (1-31)
     *  @param return true if the date exists, false if it does not
     **/
    public static boolean isDateValid(String year, String month, String dayOfMonth) {

        try {
            Timestamp ts = getTimeFromForm(year, month, dayOfMonth);
        } catch (IllegalArgumentException iae) {
            return false;
        }

        return true;
    }



    /**
     *  Converts time period form input into an integer (seconds).
     *  @param weeks the number of weeks in the time period
     *  @param days the number of days in the time period
     *  @param hours the number of hours in the time period
     *  @param minutes the number of minutes in the time period
     *  @param seconds the number of seconds in the time period
     *  @return the number of seconds in the time period
     **/
    public static int getTimePeriodFromForm(String weeks, String days, String hours, String minutes, String seconds) {

        int result = 0;

        try {
            result += Integer.parseInt(weeks) * SECONDS_IN_WEEK;
        } catch (NumberFormatException nfe) {
            // do nothing
        }

        try {
            result += Integer.parseInt(days) * SECONDS_IN_DAY;
        } catch (NumberFormatException nfe) {
            // do nothing
        }

        try {
            result += Integer.parseInt(hours) * SECONDS_IN_HOUR;
        } catch (NumberFormatException nfe) {
            // do nothing
        }

        try {
            result += Integer.parseInt(minutes) * SECONDS_IN_MINUTE;
        } catch (NumberFormatException nfe) {
            // do nothing
        }

        try {
            result += Integer.parseInt(seconds);
        } catch (NumberFormatException nfe) {
            // do nothing
        }

        return result;
    }



    /**
     *  Converts time period form input into an integer (seconds).  Weeks and days are zeroed out.
     *  @param hours the number of hours in the time period
     *  @param minutes the number of minutes in the time period
     *  @param seconds the number of seconds in the time period
     *  @return the number of seconds in the time period
     **/
    public static int getTimePeriodFromForm(String hours, String minutes, String seconds) {

        return getTimePeriodFromForm("0", "0", hours, minutes, seconds);
    }



    /**
     *  Converts time period form input into an integer (seconds).  Weeks, days, and hours are zeroed out.
     *  @param minutes the number of minutes in the time period
     *  @param seconds the number of seconds in the time period
     *  @return the number of seconds in the time period
     **/
    public static int getTimePeriodFromForm(String minutes, String seconds) {

        return getTimePeriodFromForm("0", minutes, seconds);
    }



    /**
     *  Converts milliseconds to human-readable format.
     *  @param millis the number of milliseconds in the time period
     *  @return the human-readable string representing the time period, 
     *          in the format HH:mm
     **/
    public static String getFormattedTimeFromMillis(long millis) {
	return getFormattedTimeFromTimePeriod(String.valueOf(millis / MILLIS_IN_MINUTE));
    }


    /**
     *  Converts seconds to human-readable format.
     *  @param seconds the number of seconds in the time period
     *  @return the human-readable string representing the time period, 
     *          in the format HH:mm
     **/
    public static String getFormattedTimeFromTimePeriod(String minutes) {

        String formattedTime = new String("");
        int totalMinutes = 0;
        int numHours = 0;
        int numMinutes = 0;

        try {
            totalMinutes = Integer.parseInt(minutes);
        } catch (NumberFormatException nfe) {
            // do nothing
        }

        numHours = totalMinutes / MINUTES_IN_HOUR;
        totalMinutes %= MINUTES_IN_HOUR;
        numMinutes = totalMinutes;

	formattedTime = NumberPadder.zeroPad(numHours, 2) + ":" +
	    NumberPadder.zeroPad(numMinutes, 2);

        return formattedTime;
    }



    /**
     *  Converts time form input into a timestamp.
     *  @param year the year
     *  @param month the month (1-12)
     *  @param dayOfMonth the day of the month (1-31)
     *  @param hour the hour (0-23)
     *  @param minute the minute (0-59)
     *  @param second the second (0-59)
     *  @param nanosecond the millisecond (0-999999999)
     *  @return the Timestamp representing the time
     **/
    public static Timestamp getTimeFromForm(String year, String month, String dayOfMonth,
                                            String hour, String minute, String second, String nanosecond) throws IllegalArgumentException {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            java.util.Date dateObject = formatter.parse(year + "-" + month + "-" + dayOfMonth);
        } catch (ParseException pe) {
            throw new IllegalArgumentException();
        }

        month = NumberPadder.zeroPad(month, 2);
        dayOfMonth = NumberPadder.zeroPad(dayOfMonth, 2);
        hour = NumberPadder.zeroPad(hour, 2);
        minute = NumberPadder.zeroPad(minute, 2);
        second = NumberPadder.zeroPad(second, 2);
        nanosecond = NumberPadder.zeroPad(nanosecond, 9);

        return Timestamp.valueOf(year + "-" + month + "-" + dayOfMonth + " " +
                                 hour + ":" + minute + ":" + second + "." + nanosecond);
    }



    /**
     *  Converts time form input into a timestamp.  Minutes, seconds, and nanoseconds will be zeroed out.
     *  @param year the year
     *  @param month the month (1-12)
     *  @param dayOfMonth the day of the month (1-31)
     *  @param hour the hour (0-23)
     *  @return the Timestamp representing the time
     **/
    public static Timestamp getTimeFromForm(String year, String month, String dayOfMonth, String hour) {

        return getTimeFromForm(year, month, dayOfMonth, hour, "00", "00", "000000000");
    }



    /**
     *  Converts time form input into a timestamp.  Hours, minutes, seconds, and nanoseconds will be zeroed out.
     *  @param year the year
     *  @param month the month (1-12)
     *  @param dayOfMonth the day of the month (1-31)
     *  @return the Timestamp representing the time
     **/
    public static Timestamp getTimeFromForm(String year, String month, String dayOfMonth) {

        return getTimeFromForm(year, month, dayOfMonth, "00");
    }



    /**
     *  Converts timestamp to human-readable format.  Expected timestamp format is YYYY-MM-DD HH:MM:SS.mmm
     *  @param timestamp the timestamp to be converted
     *  @return the human-readable string representing the timestamp, in the format YYYY-MM-DD HH:MM:SS
     **/
    public static String getFormattedTimeFromTimestamp(String timestamp) {

        String formattedTime = new String("");

        formattedTime = timestamp.substring(0, timestamp.lastIndexOf('.'));

        return formattedTime;
    }



    /**
     *  Converts timestamp to human-readable format.
     *  @param timestamp the timestamp to be converted
     *  @param formatString the format into which to convert the timestamp
     *  @return the human-readable string representing the timestamp
     **/
    public static String getFormattedTimeFromTimestamp(String timestamp, String formatString) {

        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        Date defaultParsed = null;

        try {
            defaultParsed = defaultFormat.parse(timestamp);
        } catch (ParseException pe) {
            // do nothing
        }

        return format.format(defaultParsed);
    }



    /**
     *  Parses out month, date, or year from a date string based on a given format.
     *  @param date the date string from which to parse the values
     *  @param formatString the format from which to interpret the date values
     *  @param returnValue the date value to return, TimeConverter.YEAR, TimeConverter.MONTH, or TimeConverter.DAY
     *  @return the date value we're looking for based on the value of returnValue
     *
     *  @exception ParseException a parsing error is thrown
     *  @exception IllegalArgumentException if returnValue does not equal TimeConverter.YEAR, TimeConverter.MONTH, or
     *                                      TimeConverter.DAY
     **/
    private static int getDateValueFromDate(String date, String formatString, int returnValue)
        throws ParseException, IllegalArgumentException
    {
        // determine position of yyyy, MM, and dd in formatString.
        int yearIndex = formatString.indexOf("yyyy");
        int monthIndex = formatString.indexOf("MM");
        int dayIndex = formatString.indexOf("dd");
        int yearPosition = 0;
        int monthPosition = 0;
        int dayPosition = 0;

        switch (yearIndex) {
	case 0:
	    yearPosition = 0;
	    break;

	case 6:
	    yearPosition = 2;
	    break;

	default:
	    throw new ParseException(formatString, 0);
        }

        switch (monthIndex) {
	case 0:
	    monthPosition = 0;
	    break;

	case 3:
	    monthPosition = 1;
	    break;

	case 5:
	    monthPosition = 1;
	    break;

	default:
	    throw new ParseException(formatString, 0);
        }

        switch (dayIndex) {
	case 0:
	    dayPosition = 0;
	    break;

	case 3:
	    dayPosition = 1;
	    break;

	case 5:
	    dayPosition = 1;
	    break;

	case 8:
	    dayPosition = 2;
	    break;

	default:
	    throw new ParseException(formatString, 0);
        }


        // now construct a pattern from formatString
        String patternString = formatString.replaceFirst("yyyy", "(\\\\d{4})");
        patternString = patternString.replaceFirst("MM", "(\\\\d\\\\d?)");
        patternString = patternString.replaceFirst("dd", "(\\\\d\\\\d?)");

        //      System.out.println("patternString: " + patternString);
        //      System.out.println("date: " + date);

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(date);
        if (!matcher.matches()) {
            throw new ParseException(date, 0);
        }

        //      System.out.println("group0: " + matcher.group(0) + ", group1: " + matcher.group(1) + ", group2: " + matcher.group(2) + ", group3: " + matcher.group(3));

        // extract the year, month, and day values based on pattern and positions
        String yearString = null;
        String monthString = null;
        String dayString = null;

        String group1 = matcher.group(1);
        if (yearPosition == 0) {
            yearString = group1;
        } else if (monthPosition == 0) {
            monthString = group1;
        } else if (dayPosition == 0) {
            dayString = group1;
        } else {
            throw new ParseException(date, 0);
        }
        String group2 = matcher.group(2);
        if (yearPosition == 1) {
            yearString = group2;
        } else if (monthPosition == 1) {
            monthString = group2;
        } else if (dayPosition == 1) {
            dayString = group2;
        } else {
            throw new ParseException(date, 0);
        }
        String group3 = matcher.group(3);
        if (yearPosition == 2) {
            yearString = group3;
        } else if (monthPosition == 2) {
            monthString = group3;
        } else if (dayPosition == 2) {
            dayString = group3;
        } else {
            throw new ParseException(date, 0);
        }


        // return the value desired based on returnValue
        switch (returnValue) {

	case TimeConverter.YEAR:
	    int year;
	    try {
		year = Integer.parseInt(yearString);
	    } catch (NumberFormatException nfe) {
		throw new ParseException(date, 0);
	    }
	    return year;

	case TimeConverter.MONTH:
	    int month;
	    try {
		month = Integer.parseInt(monthString);
	    } catch (NumberFormatException nfe) {
		throw new ParseException(date, 0);
	    }
	    return month;

	case TimeConverter.DAY:
	    int day;
	    try {
		day = Integer.parseInt(dayString);
	    } catch (NumberFormatException nfe) {
		throw new ParseException(date, 0);
	    }
	    return day;

	default:
	    throw new IllegalArgumentException();

        }

    } // end of method getDateValueFromDate(...)


    /**
     *  Parses out year from a date string based on a given format.
     *  @param date the date string from which to parse the values
     *  @param formatString the format from which to interpret the date values
     *  @return the year for which we're looking
     *
     *  @exception ParseException a parsing error is thrown
     **/
    public static int getYearFromDate(String date, String formatString)
        throws ParseException
    {

        int value;

        try {
            value = TimeConverter.getDateValueFromDate(date, formatString, TimeConverter.YEAR);
        } catch (ParseException pe) {
            throw pe;
        }

        return value;
    }


    /**
     *  Parses out month from a date string based on a given format.
     *  @param date the date string from which to parse the values
     *  @param formatString the format from which to interpret the date values
     *  @return the month for which we're looking
     *
     *  @exception ParseException a parsing error is thrown
     **/
    public static int getMonthFromDate(String date, String formatString)
        throws ParseException
    {

        int value;

        try {
            value = TimeConverter.getDateValueFromDate(date, formatString, TimeConverter.MONTH);
        } catch (ParseException pe) {
            throw pe;
        }

        return value;
    }


    /**
     *  Parses out day from a date string based on a given format.
     *  @param date the date string from which to parse the values
     *  @param formatString the format from which to interpret the date values
     *  @return the day for which we're looking
     *
     *  @exception ParseException a parsing error is thrown
     **/
    public static int getDayFromDate(String date, String formatString)
        throws ParseException
    {

        int value;

        try {
            value = TimeConverter.getDateValueFromDate(date, formatString, TimeConverter.DAY);
        } catch (ParseException pe) {
            throw pe;
        }

        return value;
    }




}//END OF CLASS TimeConverter
