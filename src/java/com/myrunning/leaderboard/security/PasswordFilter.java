package com.myrunning.leaderboard.security;

/**
 *  File: PasswordFilter.java
 *  Author: Joshua Forester
 *  Date: 2009/10/24
 *  Description: This file contains static methods for
 *               use with password strings.
 **/


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import pwdstr.analysis.PwdStrAnalysis;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;


/**
 *  A class for manipulating password strings..
 **/
public class PasswordFilter
{

    static Logger logger = Logger.getLogger(PasswordFilter.class);


    /**
     *  Checks to see if password is weak in that it is like the username
     *  @param username the username associated with the credential
     *  @param password the password to check for weakness
     *  @return true if it is weak, false if it is not
     **/
    private static boolean isUsernamePasswordComboWeak(String username, String password) {

        boolean weak = false;

        // SHOULD DO MORE THAN JUST A STRAIGHT COMPARE
        if (username.compareTo(password) == 0) {
            weak = true;
        }

        return weak;
    }



    /**
     *  Checks to see if password is easily guessable, used in dictionary, etc..
     *  @param password the password to check for weakness
     *  @return true if it is weak, false if it is not
     **/
    public static boolean isPasswordWeak(String password) {

        boolean weak = false;

        // SHOULD HAVE LOGIC TO DO DICTIONARY LOOKUP, PASSWORD COMPLEXITY MATCH, ETC.

	PwdStrAnalysis analysis = new PwdStrAnalysis();
	int r = analysis.analyse(password);
	
	if ((r < Constants.PASSWORD_STRENGTH_THRESHOLD) ||
	    (password.length() < Constants.PASSWORD_MIN_CHARS)) {
	    
	    logger.debug("Password strength:  " + r);

	    weak = true;
	}

        return weak;
    }



    /**
     *  Checks to see if password is easily guessable, used in dictionary, etc..
     *  @param username the username associated with the credential
     *  @param password the password to check for weakness
     *  @return true if it is weak, false if it is not
     **/
    public static boolean isAdminPasswordComboWeak(Admin admin, String password) {

        boolean weak = false;

        String username = admin.getUsername();
        if ((username != null) && (isUsernamePasswordComboWeak(username, password))) {
            weak = true;
        }

        // SHOULD ALSO CHECK FOR PASSWORD LIKENESS TO FULL NAME, ADDRESS, ETC.

        if (isPasswordWeak(password)) {
            weak = true;
        }

        return weak;
    }



    /**
     *  Method for hashing a password.
     *
     *  @param password The unhashed version of the password.
     *  @return The hashed version of the password, encoded in hex.
     **/
    public static String hash(String password)
    {
        return DigestUtils.md5Hex(password);
    }




}//END OF CLASS PasswordFilter
