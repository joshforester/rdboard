package com.myrunning.leaderboard.global;

/**
 *  File: NumberPadder.java
 *  Author:  Joshua Forester
 *  Date: 2009/10/01
 *  Description: Util for padding numbers.
 **/



/**
 *  A utility class for padding number.
 */
public final class NumberPadder {



    /**
     *  Prepends "0" characters to a number until the desired width is met.
     *  @param number the number to zero pad
     *  @param width the total width of the desired number
     *  @return the padded representation of the number
     **/
    public static String zeroPad(int number, int width) {

        StringBuffer result = new StringBuffer("");
        for( int i = 0; i < width - Integer.toString(number).length(); i++ ) {
            result.append( "0" );
        }
        result.append(Integer.toString(number));

        return result.toString();
    }


    /**
     *  Prepends "0" characters to a number until the desired width is met.
     *  @param number the number to zero pad
     *  @param width the total width of the desired number
     *  @return the padded representation of the number
     **/
    public static String zeroPad(String number, int width) {

        int num = 0;

        try {
            num = Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            // do nothing
        }

        return zeroPad(num, width);
    }



}//END OF CLASS NumberPadder
