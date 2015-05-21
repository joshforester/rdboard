package com.myrunning.leaderboard.validation;

/**
 *  File: DivisionValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Validator object for the division app.
 **/


import java.util.List;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Division;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.db.dao.ifacedao.DivisionDao;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;



public class DivisionValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(DivisionValidator.class);

    private DivisionDao divisionDao;
    private EventDao eventDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Division.class);
    }

    
    protected void validateCommon(Division division, Errors errors) {

	// validate name
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.generic.requiredfield", ErrorsUtils.createArray("name"), Constants.ERROR_CODE_NOT_FOUND);

	// validate eventId
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventId", "errors.generic.requiredfield", ErrorsUtils.createArray("eventId"), Constants.ERROR_CODE_NOT_FOUND);

	// validate memberCount
	if ((division.getMemberCount() < Division.MEMBER_COUNT_MIN) || (Division.MEMBER_COUNT_MAX < division.getMemberCount())) {
	    errors.rejectValue("memberCount", "errors.generic.outofrange", ErrorsUtils.createArray("memberCount", division.getMemberCount(), Division.MEMBER_COUNT_MIN, Division.MEMBER_COUNT_MAX), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate consistency
	List<String> consistencies = Division.getConsistencies();
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "consistency", "errors.generic.requiredfield", ErrorsUtils.createArray("consistency"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("consistency") == 0) {
	    if (! consistencies.contains(division.getConsistency())) {
		errors.rejectValue("consistency", "errors.editdivision.consistencynotrecognized", ErrorsUtils.createArray("consistency"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	//
	// validate is elite
	//
	RDValidationUtils.rejectIfInvalidCheckboxValue(errors, "isElite", "errors.generic.invalidcheckboxvalue", ErrorsUtils.createArray(division.getIsElite()), Constants.ERROR_CODE_NOT_FOUND);

	// do business validation
	WebApplicationContext wac = getWebApplicationContext();
	eventDao = (EventDao) wac.getBean("eventDao");
	Event existingEvent = eventDao.getEventById(division.getEventId());
	if (existingEvent == null) {
	    errors.rejectValue("eventId", "errors.editdivision.eventdoesnotexist", ErrorsUtils.createArray(String.valueOf(division.getEventId())), Constants.ERROR_CODE_NOT_FOUND);
	}

    }


    public void validate(Object obj, Errors errors) {
	Division division = (Division) obj;

	validateCommon(division, errors);

	// do business validation
	WebApplicationContext wac = getWebApplicationContext();
	divisionDao = (DivisionDao) wac.getBean("divisionDao");
	Division existingDivision = divisionDao.getDivisionByEventIdAndName(division.getEventId(), division.getName());
	if (existingDivision != null) {
	    logger.debug("Division " + existingDivision.getName() + " already exists for event id " + existingDivision.getEventId());
	    errors.rejectValue("name", "errors.editdivision.namealreadyexistsforevent", ErrorsUtils.createArray(division.getName(), String.valueOf(division.getEventId())), Constants.ERROR_CODE_NOT_FOUND);
	}

    }

}
