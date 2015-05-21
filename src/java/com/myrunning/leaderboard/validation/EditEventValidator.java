package com.myrunning.leaderboard.validation;

/**
 *  File: EditEventValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Validator object for the edit event app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Event;


public class EditEventValidator extends EventValidator implements Validator {

    static Logger logger = Logger.getLogger(EditEventValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Event.class);
    }


    public void validate(Object obj, Errors errors) {
	Event event = (Event) obj;

	// validate id provided to edit method
	if (event.getId() == 0) {
	    errors.rejectValue("id", "errors.generic.requiredfield", ErrorsUtils.createArray("id"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(event, errors);
    }

}
