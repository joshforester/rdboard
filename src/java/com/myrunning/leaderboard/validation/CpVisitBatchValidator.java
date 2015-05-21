package com.myrunning.leaderboard.validation;

/**
 *  File: CpVisitBatchValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/09/18
 *  Description: Validator object for the cpVisitBatch app.
 **/


import java.util.ListIterator;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.CpVisit;
import com.myrunning.leaderboard.model.CpVisitBatch;


public class CpVisitBatchValidator extends EditCpVisitValidator implements Validator {

    static Logger logger = Logger.getLogger(CpVisitBatchValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(CpVisitBatch.class);
    }


    public void validate(Object obj, Errors errors) {
	CpVisitBatch batch = (CpVisitBatch) obj;

	// validate the group stuff, then validate the individual ones
	ListIterator<CpVisit> bli = batch.getBatch().listIterator();
	int i = 0;
	while (bli.hasNext()) {
	    CpVisit cpVisit = bli.next();
	    errors.setNestedPath("batch[" + i + "].");
	    
	    /*
	    // this is just a stub validator to make Snickers Marathon invalid
	    // it shows how to reject values
	    if (cpVisit.getTeamId() == 48) {
		errors.rejectValue(new String(fieldPrefix + "teamId"), "errors.generic.requiredfield", ErrorsUtils.createArray("team_id"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    */

	    // filter out default visits, as we don't want to validate them
	    if (!cpVisit.isDefaulted()) {

		CpVisit existingCpVisit = getCpVisitService().getCpVisitByTeamIdAndCpId(cpVisit.getTeamId(), cpVisit.getCpId());

		// an add
		if (existingCpVisit == null) {
		    
		    super.validateAddCpVisit(cpVisit, errors);
		    
		// an edit
		} else {
		    
		    super.validate(cpVisit, errors);
		    
		}
	    }

	    errors.setNestedPath("");
	    i++;
	}
    }

}
