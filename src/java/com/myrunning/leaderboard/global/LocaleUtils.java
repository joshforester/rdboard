package com.myrunning.leaderboard.global;

/**
 *  File: LocaleUtils.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/21
 *  Description: Helper object for Locale-related operations.  Probably good
 *               to replace this later with an actual locale/geolocation library.
 **/


import java.util.List;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;


public class LocaleUtils {

    static Logger logger = Logger.getLogger(LocaleUtils.class);

    private static List<String> countries = 
	Arrays.asList("US", 
		      "CA",
		      "MX");

    private static List<String> regions = 
	Arrays.asList("AL",
		      "AK",
		      "AZ",
		      "AR",
		      "CA",
		      "CO",
		      "CT",
		      "DE",
		      "DC",
		      "FL",
		      "GA",
		      "HI",
		      "ID",
		      "IL",
		      "IN",
		      "IA",
		      "KS",
		      "KY",
		      "LA",
		      "ME",
		      "MD",
		      "MA",
		      "MI",
		      "MN",
		      "MS",
		      "MO",
		      "MT",
		      "NE",
		      "NV",
		      "NH",
		      "NJ",
		      "NM",
		      "NY",
		      "NC",
		      "ND",
		      "OH",
		      "OK",
		      "OR",
		      "PA",
		      "RI",
		      "SC",
		      "SD",
		      "TN",
		      "TX",
		      "UT",
		      "VT",
		      "VA",
		      "WA",
		      "WV",
		      "WI",
		      "WY");


    /**
     * Determines whether a locale actually exists or not.
     * @param city the city of the locale
     * @param region the state/province/region of the locale
     * @param country the country of the locale
     * @param zip the postal code of the locale
     * @return true if the locale was found, false if not
     **/
    public static boolean isGeoLocatedLocale(String city, 
					     String region, 
					     String country,
					     String zip) {
	//
	//  stub method.  should use city/region/country/zip, falling
	//  back to city, region, country, falling back to zip,
	//  falling back to region, country, falling back to country
	//  if partial data (return false in case of inaccurate data)
	//
	//  use RDValidationUtils.isNothing to test inputs
	//

	return true;
    }


    /**
     * Determines whether a locale actually exists or not.
     * @param city the city of the locale
     * @param region the state/province/region of the locale
     * @param country the country of the locale
     * @param zip the postal code of the locale
     * @return true if the locale was found, false if not
     **/
    public static boolean isGeoLocatedLocale(String city, 
					     String region, 
					     String country,
					     int zip) {
	return isGeoLocatedLocale(city, region, country, String.valueOf(zip));
    }


    /**
     * Gets the current value of countries
     * @return Current value of countries
     */
    public static List<String> getCountries() {
	return countries;
    }

    /**
     * Gets the current value of regions
     * @return Current value of regions
     */
    public static List<String> getRegions() {
	return regions;
    }


}
