package com.myrunning.leaderboard.el;

/**
 *  File: TimestampFormatter.java
 *  Author:  Joshua Forester
 *  Date: 2009/12/01
 *  Description: An EL function implementation for formatting 
 *               Timestamp strings.
 **/

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimestampEditor;


/**
 *  An EL function implementation for formatting Timestamp strings.
 */
public final class TimestampFormatter {

    
    /**
     *  Format the specified timestamp string.
     *  @param timestamp the default-formatted timestamp string.
     *  @param format the intended format.
     *  @return the timestamp formatted as per format.
     **/
    public static String formatTimestamp(String timestamp,
					 String format)  {
	return formatTimestampImpl(timestamp, format, false);
    }


    /**
     *  Format the specified timestamp string and localize it.
     *  @param timestamp the default-formatted timestamp string.
     *  @param format the intended format.
     *  @return the timestamp formatted as per format.
     **/
    public static String formatLocalizedTimestamp(String timestamp,
						  String format)  {
	return formatTimestampImpl(timestamp, format, true);	
    }


    /**
     *  Format the specified timestamp string.
     *  @param timestamp the default-formatted timestamp string.
     *  @param format the intended format.
     *  @param localize whether or not to transform timestamp to client format
     *  @return the timestamp formatted as per format.
     **/
    private static String formatTimestampImpl(String timestamp,
					      String format,
					      boolean localized) {
	Timestamp ts = Timestamp.valueOf(timestamp);
	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	CustomTimestampEditor tsEditor = new CustomTimestampEditor(dateFormat, false);
	tsEditor.setValue(ts);
	if (!localized) {
	    return tsEditor.getAsText();
	}

	String r = new String("");
	r += "<span class=\"time\">";
	r += tsEditor.getAsText() + " GMT";
	r += "<span class=\"ts\">";
	r += ts.getTime() + "|" + format;
	r += "</span>";
	r += "</span>";

	return r;	
    }


}//END OF CLASS TimestampFormatter
