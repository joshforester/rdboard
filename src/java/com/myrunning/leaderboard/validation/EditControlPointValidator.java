package com.myrunning.leaderboard.validation;

/**
 *  File: EditControlPointValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Validator object for the edit controlPoint app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.ControlPoint;


public class EditControlPointValidator extends ControlPointValidator implements Validator {

    static Logger logger = Logger.getLogger(EditControlPointValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(ControlPoint.class);
    }


    public void validate(Object obj, Errors errors) {
	ControlPoint controlPoint = (ControlPoint) obj;

	// validate id provided to edit method
	if (controlPoint.getId() == 0) {
	    errors.rejectValue("id", "errors.generic.requiredfield", ErrorsUtils.createArray("id"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(controlPoint, errors);
    }

}
