package com.myrunning.leaderboard.validation;

/**
 *  File: EditCompetitorValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Validator object for the edit competitor app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Competitor;


public class EditCompetitorValidator extends CompetitorValidator implements Validator {

    static Logger logger = Logger.getLogger(EditCompetitorValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Competitor.class);
    }


    public void validate(Object obj, Errors errors) {
	Competitor competitor = (Competitor) obj;

	// validate id provided to edit method
	if (competitor.getId() == 0) {
	    errors.rejectValue("id", "errors.generic.requiredfield", ErrorsUtils.createArray("id"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(competitor, errors);
    }

}
