package com.myrunning.leaderboard.validation;

/**
 *  File: RDValidationUtils.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/28
 *  Description: Helper object for validation routines.
 **/


import java.util.List;
import java.util.Calendar;
import java.net.URL;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.global.LocaleUtils;



public class RDValidationUtils extends ValidationUtils {

    static Logger logger = Logger.getLogger(RDValidationUtils.class);

    // 5 years
    private static final long DEFAULT_YOUNGEST_AGE = 157788000000L;

    // 90 years
    private static final long DEFAULT_OLDEST_AGE =  2840184000000L;

    // roughly 6 months, 1 day
    private static final long DEFAULT_PAST_MARGIN = 15854400000L;

    // roughly 2 years, 1 day
    private static final long DEFAULT_FUTURE_MARGIN = 63158400000L;

    // 24 hours
    private static final long DEFAULT_TIME_PERIOD_MAX = 86400000L;

    // phone number patterns
    private static String phoneNumberPattern1 = "\\d{10}";
    private static String phoneNumberPattern2 = "\\d{3}(-)\\d{3}(-)\\d{4}";


    /**
     * Determines if a string is null/empty/contains only whitespace or not.
     * @return true if sane, false if not sane
     */
    public static boolean nothingString(String s) {
	if (s == null) {
	    return true;
	} else if (!StringUtils.hasText(s)) {
	    return true;
	} else {
	    return false;
	}
    }


    /**
     * Ensure a string is an alphanumeric.
     * @param s the string.
     * @return true if it is an alphanumerical string, false if not.
     */
    public static boolean validAlphaNumeric(String s) {
	
	String alphaNumericPattern = "\\w*";

	if (s.matches(alphaNumericPattern)) {
	    return true;
	} else {
	    return false;
	}
    }


    /**
     * Ensure a URL is valid.
     * @param url the url to check.
     * @return true if it is a valid URL, false if it is malformed.
     */
    public static boolean validURL(String url) {
	
	try {
	    URL u = new URL(url);
	} catch (MalformedURLException mue) {
	    return false;
	}

	return true;
    }



    /**
     * Determine if a locale is valid or not.
     * @param city the city of the locale
     * @param region the state/province/region of the locale
     * @param country the country of the locale
     * @param zip the zip code of the locale
     * @param errors the errors object associated with the form
     **/
    public static void validateLocale(Errors errors,
				      String city, 
				      String region, 
				      String country,
				      int zip) {

	// validate region
	if ((!nothingString(region)) &&
	    (!nothingString(country))) {
	    
	    rejectIfInvalidRegion(errors, "region", "country", "errors.generic.invalidregion", ErrorsUtils.createArray(region, country), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate country
	if (!nothingString(country)) {
	    rejectIfInvalidCountry(errors, "country", "errors.generic.invalidcountry", ErrorsUtils.createArray(country), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate zip format
	rejectIfInvalidZipCode(errors, "zip", "errors.generic.invalidzip", ErrorsUtils.createArray(zip), Constants.ERROR_CODE_NOT_FOUND);

	// validate locale
	if ((!nothingString(city)) &&
	    (!nothingString(region)) &&
	    (!nothingString(country)) &&
	    (zip != 0)) {
	    if (!LocaleUtils.isGeoLocatedLocale(city, region, country, zip)) {
		errors.reject("errors.generic.invalidlocale", ErrorsUtils.createArray(city, region, country, String.valueOf(zip)), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}
    }



    /**
     * Vetting zip code conforms to format.
     * @param zip Zip code string.
     */
    public static boolean validZipCode(String zip) {
	
	String zipCodePattern = "\\d{5}(-\\d{4})?";

	if (zip.matches(zipCodePattern)) {
	    return true;
	} else {
	    return false;
	}
    }


    /**
     * Vetting zip code conforms to format.
     * @param zip Zip code Integer.
     */
    public static boolean validZipCode(Integer zip) {
	return validZipCode(zip.intValue());
    }


    /**
     * Vetting zip code conforms to format.
     * @param zip Zip code int.
     */
    public static boolean validZipCode(int zip) {
	return validZipCode(String.valueOf(zip));
    }


    /**
     * Vetting phone number conforms to format.
     * @param number phone number string.
     */
    public static boolean validPhoneNumber(String number) {
	if ((number.matches(phoneNumberPattern1)) ||
	    (number.matches(phoneNumberPattern2))) {
	    return true;
	} else {
	    return false;
	}
    }


    /**
     * Vetting phone number conforms to format.
     * @param number phone number Integer.
     */
    public static boolean validPhoneNumber(Integer number) {
	return validPhoneNumber(number.intValue());
    }


    /**
     * Vetting phone number conforms to format.
     * @param number phone number int.
     */
    public static boolean validPhoneNumber(int number) {
	return validPhoneNumber(String.valueOf(number));
    }


    /**
     * Determines whether a time period makes sense.
     * Time periods too large are considered insane.
     * @return true if sane, false if not sane
     */
    public static boolean saneTimePeriod(Timestamp ts) {
	return saneTimePeriod(ts, DEFAULT_TIME_PERIOD_MAX);
    }


    /**
     * Determines whether a time period makes sense.
     * Time periods outside of that range are considered insane.
     * @param ts the timestamp to check
     * @param max the maximum length in milliseconds the time period can be
     * @return true if sane, false if not sane
     * @throws IllegalArgumentException if max is too large
     */
    public static boolean saneTimePeriod(Timestamp ts, long max) {

	if (max > DEFAULT_TIME_PERIOD_MAX) {
	    throw new IllegalArgumentException("max parameter in saneTimePeriod check cannot be greater than 24 hours");
	}

	Timestamp maxBoundary = new Timestamp(max);
	if (ts.after(maxBoundary)) {
	    return false;
	} else {
	    return true;
	}
    }


    /**
     * Determines whether a time makes sense given the current time.
     * Times too far in the past or future are insane.
     * @return true if sane, false if not sane
     */
    public static boolean saneTime(Timestamp ts) {
	return saneTime(ts, DEFAULT_PAST_MARGIN, DEFAULT_FUTURE_MARGIN);
    }


    /**
     * Determines whether a time makes sense given the current time,
     * and margins from for past/future.  Times outside of the current
     * time minus/plus those margins are considered insane.
     * @return true if sane, false if not sane
     */
    public static boolean saneTime(Timestamp ts, long pastMargin, long futureMargin) {
	Timestamp current = new Timestamp(Calendar.getInstance().getTimeInMillis());
	Timestamp pastBoundary = new Timestamp(current.getTime() - pastMargin);
	Timestamp futureBoundary = new Timestamp(current.getTime() + futureMargin);
	if (current.after(ts)) {
	    return ts.after(pastBoundary);
	} else {
	    return ts.before(futureBoundary);
	}
    }


    /**
     * Determines whether a time makes sense given the current time.
     * Times beyond reasonable human lifespan are insane.
     * @return true if sane, false if not sane
     */
    public static boolean saneBirthday(Timestamp ts) {
	return saneBirthday(ts, DEFAULT_YOUNGEST_AGE, DEFAULT_OLDEST_AGE);
    }


    /**
     * Determines whether a birthday makes sense given the current time.  
     * Times beyond reasonable human lifespan are insane.
     * @return true if sane, false if not sane
     */
    public static boolean saneBirthday(Timestamp ts, long youngest, long oldest) {
	Timestamp current = new Timestamp(Calendar.getInstance().getTimeInMillis());
	Timestamp oldestBoundary = new Timestamp(current.getTime() - oldest);
	Timestamp youngestBoundary = new Timestamp(current.getTime() - youngest);
	if ((ts.before(oldestBoundary)) || (ts.after(youngestBoundary))) {
	    return false;
	} else {
	    return true;
	}
    }


    /**
     * Vetting checkbox values.  
     * @param errors Errors object to populate with validation errors
     * @param field HTML Form field to be validated
     * @param messageKey resource bundle key containing error message
     * @param args parameters to the error message
     * @param defaultMessage default error message if messageKey is not found
     */
    public static void rejectIfInvalidCheckboxValue(Errors errors, 
						    String field, 
						    String errorCode,
						    Object[] errorArgs,
						    String defaultMessage) {
	
	Assert.notNull(errors, "Errors object must not be null");
	String value = (String) errors.getFieldValue(field);
	if ((! value.equals("yes")) && (! value.equals("no"))) {
	    errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
	}
	
    }


    /**
     * Vetting region.  
     * @param errors Errors object to populate with validation errors
     * @param regionField HTML Form field to be validated
     * @param countryField HTML Form field to be validated
     * @param messageKey resource bundle key containing error message
     * @param args parameters to the error message
     * @param defaultMessage default error message if messageKey is not found
     */
    public static void rejectIfInvalidRegion(Errors errors, 
					     String regionField, 
					     String countryField, 
					     String errorCode,
					     Object[] errorArgs,
					     String defaultMessage) {
	
	Assert.notNull(errors, "Errors object must not be null");
	String regionValue = (String) errors.getFieldValue(regionField);

	//
	//  Future validation should check to ensure the region is a valid
	//  region WITHIN the country.
	//

	/*
	String countryValue = (String) errors.getFieldValue(countryField);

	List<String> countries = LocaleUtils.getCountries();
	if (!countries.contains(countryValue)) {
	    errors.rejectValue(field, errorCode, errorArgs, defaultMessage);	    
	}
	*/

	List<String> regions = LocaleUtils.getRegions();
	if (!regions.contains(regionValue)) {
	    errors.rejectValue(regionField, errorCode, errorArgs, defaultMessage);
	}
	
    }


    /**
     * Vetting country.  
     * @param errors Errors object to populate with validation errors
     * @param field HTML Form field to be validated
     * @param messageKey resource bundle key containing error message
     * @param args parameters to the error message
     * @param defaultMessage default error message if messageKey is not found
     */
    public static void rejectIfInvalidCountry(Errors errors, 
					      String field, 
					      String errorCode,
					      Object[] errorArgs,
					      String defaultMessage) {
	
	Assert.notNull(errors, "Errors object must not be null");
	String value = (String) errors.getFieldValue(field);

	List<String> countries = LocaleUtils.getCountries();
	if (!countries.contains(value)) {
	    errors.rejectValue(field, errorCode, errorArgs, defaultMessage);	    
	}

    }


    /**
     * Vetting zip code.  
     * @param errors Errors object to populate with validation errors
     * @param field HTML Form field to be validated
     * @param messageKey resource bundle key containing error message
     * @param args parameters to the error message
     * @param defaultMessage default error message if messageKey is not found
     */
    public static void rejectIfInvalidZipCode(Errors errors, 
					      String field, 
					      String errorCode,
					      Object[] errorArgs,
					      String defaultMessage) {
	
	Assert.notNull(errors, "Errors object must not be null");
	Object value = errors.getFieldValue(field);
	
	if (value.getClass().getName().equals("java.lang.String")) {
	    if (! validZipCode((String) value)) {
		errors.rejectValue(field, errorCode, errorArgs, defaultMessage); 
	    }
	} else if (value.getClass().getName().equals("java.lang.Integer")) {
	    if (! validZipCode((Integer) value)) {
		errors.rejectValue(field, errorCode, errorArgs, defaultMessage); 
	    }
	} else {
	    throw new ClassCastException("Unable to determine value object type.");
	}

    }

    
}
