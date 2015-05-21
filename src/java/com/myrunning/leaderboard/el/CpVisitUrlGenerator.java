package com.myrunning.leaderboard.el;

/**
 *  File: CpVisitUrlGenerator.java
 *  Author:  Joshua Forester
 *  Date: 2009/12/22
 *  Description: An EL function implementation for generating a URL for listing
 *               cpVisits.
 **/

/**
 *  An EL function implementation for generating a URL for listing cpVisits.
 */
public final class CpVisitUrlGenerator {


    /**
     *  Generate a URL based on provided cpId, teamId, courseId.
     *  @param cpId the control point id
     *  @param teamId the team id
     *  @param courseId the course id
     *  @param paramStr the parameter string to append to the end
     *  @return the formatted URL for listing the 
     **/
    public static String cpVisitUrl(long cpId,
				    long teamId,
				    long courseId,
				    String paramStr)  {
	String s = new String("");
	
	if ((cpId != 0) && (teamId != 0)) {
	    s += "/d/cpvisits/" + cpId + "/" + teamId;
	} else if ((cpId != 0) && (teamId == 0)) {
	    s += "/d/cps/" + cpId + "/cpvisits";
	} else if ((cpId == 0) && (teamId != 00)) {
	    s += "/d/teams/" + teamId + "/cpvisits";
	} else if (courseId != 0) {
	    s += "/d/courses/" + courseId + "/cpvisits";
	} else {
	    s += "/d/cpvisits";
	} 

	s += "?" + paramStr;

	return s;
    }


}//END OF CLASS CpVisitUrlGenerator
