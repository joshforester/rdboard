package com.myrunning.leaderboard.validation;

/**
 *  File: EditDivisionValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Validator object for the edit division app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Division;


public class EditDivisionValidator extends DivisionValidator implements Validator {

    static Logger logger = Logger.getLogger(EditDivisionValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Division.class);
    }


    public void validate(Object obj, Errors errors) {
	Division division = (Division) obj;

	// validate id provided to edit method
	if (division.getId() == 0) {
	    errors.rejectValue("id", "errors.generic.requiredfield", ErrorsUtils.createArray("id"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(division, errors);
    }

}
