package com.myrunning.leaderboard.validation;

/**
 *  File: EditCpVisitValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Validator object for the edit cpVisit app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.CpVisit;


public class EditCpVisitValidator extends CpVisitValidator implements Validator {

    static Logger logger = Logger.getLogger(EditCpVisitValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(CpVisit.class);
    }

    
    protected void validateAddCpVisit(CpVisit cpVisit, Errors errors) {
	super.validate(cpVisit, errors);
    }


    public void validate(Object obj, Errors errors) {
	CpVisit cpVisit = (CpVisit) obj;

	// validate team_id provided to edit method
	if (cpVisit.getTeamId() == 0) {
	    errors.rejectValue("teamId", "errors.generic.requiredfield", ErrorsUtils.createArray("teamId"), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate team_id provided to edit method
	if (cpVisit.getCpId() == 0) {
	    errors.rejectValue("cpId", "errors.generic.requiredfield", ErrorsUtils.createArray("cpId"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(cpVisit, errors);

	logger.debug("Validating CpVisit:  " + cpVisit);
    }

}
