package com.myrunning.leaderboard.validation;

/**
 *  File: ErrorsUtils.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/21
 *  Description: Helper object for dealing with Errors objects.
 **/


import org.apache.log4j.Logger;
import org.apache.log4j.MDC;


public class ErrorsUtils {

    static Logger logger = Logger.getLogger(ErrorsUtils.class);

    protected static Object[] createArray(Object object) {
	Object[] array = new Object[1];
	array[0] = object;

	return array;
    }

    protected static Object[] createArray(Object object, Object object2) {
	Object[] array = new Object[2];
	array[0] = object;
	array[1] = object2;

	return array;
    }

    protected static Object[] createArray(Object object, Object object2, Object object3) {
	Object[] array = new Object[3];
	array[0] = object;
	array[1] = object2;
	array[2] = object3;

	return array;
    }

    protected static Object[] createArray(Object object, Object object2, Object object3, Object object4) {
	Object[] array = new Object[4];
	array[0] = object;
	array[1] = object2;
	array[2] = object3;
	array[3] = object4;

	return array;
    }

    protected static Object[] createArray(Object object, Object object2, Object object3, Object object4, Object object5) {
	Object[] array = new Object[5];
	array[0] = object;
	array[1] = object2;
	array[2] = object3;
	array[3] = object4;
	array[4] = object5;

	return array;
    }

    protected static Object[] createArray(Object object, Object object2, Object object3, Object object4, Object object5, Object object6) {
	Object[] array = new Object[6];
	array[0] = object;
	array[1] = object2;
	array[2] = object3;
	array[3] = object4;
	array[4] = object5;
	array[5] = object6;

	return array;
    }

    protected static Object[] createArray(Object object, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7) {
	Object[] array = new Object[7];
	array[0] = object;
	array[1] = object2;
	array[2] = object3;
	array[3] = object4;
	array[4] = object5;
	array[5] = object6;
	array[6] = object7;

	return array;
    }

}
