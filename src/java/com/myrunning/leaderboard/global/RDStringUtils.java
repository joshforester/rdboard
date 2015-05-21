package com.myrunning.leaderboard.global;

/**
 *  File: RDStringUtils.java
 *  Author:  Joshua Forester
 *  Date: 2009/10/28
 *  Description: Helper object for string manipulation.
 **/


import org.apache.commons.codec.language.Soundex;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.stats.LevenshteinDistance;



public class RDStringUtils extends StringUtils {

    static Logger logger = Logger.getLogger(RDStringUtils.class);


    // phone number patterns
    private static String phoneNumberPattern1 = "\\d{10}";
    private static String phoneNumberPattern2 = "\\d{3}(-)\\d{3}(-)\\d{4}";


    /**
     * Normalizing phone number into digit-only representation.
     * @param number phone number string.
     * @return phone number with just digits.
     */
    public static String normalizePhoneNumber(String number) {

	if (number == null) {
	    return null;
	}
	
	if (number.matches(phoneNumberPattern2)) {
	    number.replaceAll("-", "");
	}

	return number;
    }


    /**
     *  Compare two phone numbers for equality.
     *  @param number1 first phone number
     *  @param number2 second phone number
     *  @return true if the phone numbers are effectively the same, false if not or if
     *               either is null.
     */
    public static boolean phoneNumbersEqual(String number1, String number2) {

	if ((number1 == null) || (number2 == null)) {
	    return false;
	}

	number1 = normalizePhoneNumber(number1);
	number2 = normalizePhoneNumber(number2);

	return number1.equals(number2);
    }



    /**
     *  Compare two strings for case-insensitive equality.
     *  @param s1 first string
     *  @param s2 second string
     *  @return true if the strings are effectively the same, not considering case;
     *          false if not or if either is null.
     */
    public static boolean stringsEqual(String s1, String s2) {

	if ((s1 == null) || (s2 == null)) {
	    return false;
	}

	s1 = s1.toLowerCase();
	s2 = s2.toLowerCase();

	return s1.equals(s2);
    }


    /**
     *  Compare two strings for equality using fuzzy logic. 
     *  Treat them as lower-case for comparison.
     *  @param s1 first string
     *  @param s2 second string
     *  @return true if the strings are similar enough, false if not or if
     *               either is null.
     */
    public static boolean stringsFuzzyEqual(String s1, String s2) {
	
	if ((s1 == null) || (s2 == null)) {
	    return false;
	}

	// first check for case-insensitive equality
	if (stringsEqual(s1, s2)) {
	    return true;
	}

	s1 = s1.toLowerCase();
	s2 = s2.toLowerCase();

	// next, compute the levenshtein distance and see if that is a match
	if (LevenshteinDistance.LD(s1, s2) < Constants.FUZZY_STRING_THRESHOLD) {
	    return true;
	} 

	
	return false;
    }


    /**
     *  Compare two strings for phonetic similarity. 
     *  Treat them as lower-case for comparison.
     *  Uses the Soundex algorithm.  See http://en.wikipedia.org/wiki/Soundex for details.
     *  See here to try:  http://resources.rootsweb.ancestry.com/cgi-bin/soundexconverter
     *  @param s1 first string
     *  @param s2 second string
     *  @return true if the strings are similar, false if not or if
     *               either is null.
     */
    public static boolean stringsPhoneticallySimilar(String s1, String s2) {
	
	if ((s1 == null) || (s2 == null)) {
	    return false;
	}

	// attempt a phonetic match using the soundex library.
	Soundex soundex = new Soundex();
	try {
	    s1 = soundex.soundex(s1);
	    s2 = soundex.soundex(s2);
	    if (s1.equals(s2)) {
		return true;
	    } else {
		return false;
	    }
	} catch (IllegalArgumentException iae) {
	    return false;
	}

    }

}
