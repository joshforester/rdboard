package com.myrunning.leaderboard.validation;

/**
 *  File: EditComparatorValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/26
 *  Description: Validator object for the edit comparator app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Comparator;


public class EditComparatorValidator extends ComparatorValidator implements Validator {

    static Logger logger = Logger.getLogger(EditComparatorValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Comparator.class);
    }


    public void validate(Object obj, Errors errors) {
	Comparator comparator = (Comparator) obj;

	// validate id provided to edit method
	if (comparator.getId() == 0) {
	    errors.rejectValue("id", "errors.generic.requiredfield", ErrorsUtils.createArray("id"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(comparator, errors);
    }

}
