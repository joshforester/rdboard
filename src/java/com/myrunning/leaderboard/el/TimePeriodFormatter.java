package com.myrunning.leaderboard.el;

/**
 *  File: TimePeriodFormatter.java
 *  Author:  Joshua Forester
 *  Date: 2009/12/02
 *  Description: An EL function implementation for formatting 
 *               TimePeriod strings.
 **/

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimePeriodEditor;


/**
 *  An EL function implementation for formatting TimePeriod strings.
 */
public final class TimePeriodFormatter {


    /**
     *  Format the specified time period string.
     *  @param timePeriod the default-formatted timestamp string.
     *  @param format the intended format.
     *  @return the time period formatted as per format.
     **/
    public static String formatTimePeriod(String timePeriod,
					  String format)  {

	Timestamp tp = Timestamp.valueOf(timePeriod);

	//
	//
	//  format IS HARD-CODED TO BE HH:mm IN TimeConverter, WHICH IS
	//  REFERENCED BY CustomTimePeriodEditor.  SO FOR NOW, IT CANNOT
	//  BE OVERIDDEN IN THE EL FUNCTION CALL EXCEPT BY CHANGING THAT 
	//  CODE.  THIS IS BASICALLY A STUB UNTIL THAT TIME.
	//
	//

	SimpleDateFormat timePeriodFormat = new SimpleDateFormat(format);
	CustomTimePeriodEditor tpEditor = new CustomTimePeriodEditor(timePeriodFormat, false);
	tpEditor.setValue(tp);
	return tpEditor.getAsText();
    }

    
    /**
     *  Format the specified time period string per default format.
     *  @param timePeriod the default-formatted timestamp string.
     *  @return the time period formatted as per format.
     **/
    public static String formatTimePeriod(String timePeriod) {
	return formatTimePeriod(timePeriod, Constants.TIME_PERIOD_FORMAT);
    }


}//END OF CLASS TimePeriodFormatter
