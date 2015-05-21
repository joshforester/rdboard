package com.myrunning.leaderboard.global;

/**
 *  File: Constants.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/17
 *  Description: Some basic constants for use with the application.
 **/


import java.util.TimeZone;


public final class Constants {

    // ninja.etrade.com
    //public static final String GMAPS_API_KEY = "ABQIAAAA6UV9BnyUf1mpl1JzHccxqhTumxdtTsGjJDeKr48KezIMEebifxR0cTjAT5RdM4xmxOCpuEr3AYTD6A";

    // rdboard.com
    public static final String GMAPS_API_KEY = "ABQIAAAA6UV9BnyUf1mpl1JzHccxqhS7tTlN5c8Hc1lmll9sR-WG7dLc8RRtz1r7PL5oLeEQsNEZjA4jLgwFug";

    // ninja.etrade.com
    //public static final boolean PROXIED = true;

    // rdboard.com
    public static final boolean PROXIED = false;

    public static final String PROXY_USERNAME = "jforest1";
    public static final String PROXY_PASSWORD = "Mailmail1";
    public static final String PROXY_IP = "10.50.79.200";
    public static final int PROXY_PORT = 8080;

    public static final String ERROR_CODE_NOT_FOUND = "??? Error code not found, email webmaster. ???";

    // see pwdstr.analysis.PwdStrAnalysis for info on how its calculated
    public static final int PASSWORD_STRENGTH_THRESHOLD = 30;

    // see com.myrunning.leaderboard.model.stats.Correlation for more info
    public static final int CORRELATION_THRESHOLD = 65;

    // see com.myrunning.leaderboard.model.stats.LevenshteinDistance for more info
    // or http://www.merriampark.com/ld.htm
    public static final int FUZZY_STRING_THRESHOLD = 1;

    public static final int PASSWORD_MIN_CHARS = 6;

    public static final String TIMESTAMP_FORMAT = new String("yyyy-MM-dd HH:mm");

    public static final String DATE_FORMAT = new String("yyyy-MM-dd");

    public static final String TIME_PERIOD_FORMAT = new String("HH:mm");

    public static final String ADMIN_SESSION_KEY = new String("currentAdmin");

    public static final String OWNERSHIP_ERROR = new String(".ownershiperror_dashboard");

    // list updates
    public static final long UPDATES_DEFAULT_LOOKBACK = 1L * TimeConverter.MILLIS_IN_SECOND * TimeConverter.SECONDS_IN_DAY;

    // ajax polling of updates
    public static final long UPDATES_LOOKBACK = 5L * TimeConverter.MILLIS_IN_MINUTE;
    public static final long UPDATES_INTERVAL = 5L * TimeConverter.MILLIS_IN_MINUTE;
    public static final long UPDATES_TIMEOUT = 180L * TimeConverter.MILLIS_IN_MINUTE;

}
