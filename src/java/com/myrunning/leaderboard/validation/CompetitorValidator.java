package com.myrunning.leaderboard.validation;

/**
 *  File: CompetitorValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/21
 *  Description: Validator object for the competitor app.
 **/


import java.util.List;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.model.Competitor;
import com.myrunning.leaderboard.db.dao.ifacedao.CompetitorDao;


public class CompetitorValidator extends PersonValidator implements Validator {

    static Logger logger = Logger.getLogger(CompetitorValidator.class);

    private CompetitorDao competitorDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Competitor.class);
    }


    protected void validateCommon(Competitor competitor, Errors errors) {

	// validate emergency contact name
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emergencyContactFirstName", "errors.generic.requiredfield", ErrorsUtils.createArray("emergencyContactFirstName"), Constants.ERROR_CODE_NOT_FOUND);

	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emergencyContactLastName", "errors.generic.requiredfield", ErrorsUtils.createArray("emergencyContactLastName"), Constants.ERROR_CODE_NOT_FOUND);

	// validate emergency contact relation
	List<String> emergencyContactRelations = Person.getEmergencyContactRelations();
	
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emergencyContactRelation", "errors.generic.requiredfield", ErrorsUtils.createArray("emergencyContactRelation"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("emergencyContactRelation") == 0) {
	    if (! emergencyContactRelations.contains(competitor.getEmergencyContactRelation())) {
		errors.rejectValue("emergencyContactRelation", "errors.editcompetitor.relationnotrecognized", ErrorsUtils.createArray(competitor.getEmergencyContactRelation()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}
	
	// validate emergency phone
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emergencyPhone", "errors.generic.requiredfield", ErrorsUtils.createArray("emergencyPhone"), Constants.ERROR_CODE_NOT_FOUND);

	// validate phone number format
	if ((!RDValidationUtils.nothingString(competitor.getEmergencyPhone())) &&
	    (!RDValidationUtils.validPhoneNumber(competitor.getEmergencyPhone()))) {
	    errors.rejectValue("emergencyPhone", "errors.generic.invalidphonenumber", ErrorsUtils.createArray(competitor.getEmergencyPhone()), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate shirt size
	List<String> shirtSizes = Person.getShirtSizes();
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shirtSize", "errors.generic.requiredfield", ErrorsUtils.createArray("shirtSize"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("shirtSize") == 0) {
	    if (! shirtSizes.contains(competitor.getShirtSize())) {
		errors.rejectValue("shirtSize", "errors.editcompetitor.shirtsizenotrecognized", ErrorsUtils.createArray(competitor.getShirtSize()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate shoe size
	//
	//  all different kinds...
	//  uk, us, eur, japan
	//  us 11M, women us 11
	//
    }


    public void validate(Object obj, Errors errors) {

	super.validate(obj, errors);

	Competitor competitor = (Competitor) obj;

	validateCommon(competitor, errors);
	
    }

}
