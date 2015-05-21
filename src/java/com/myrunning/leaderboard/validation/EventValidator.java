package com.myrunning.leaderboard.validation;

/**
 *  File: EventValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/17
 *  Description: Validator object for the event app.
 **/


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.global.LocaleUtils;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;


public class EventValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(EventValidator.class);

    private EventDao eventDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Event.class);
    }


    protected void validateCommon(Event event, Errors errors) {

	// validate name
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.generic.requiredfield", ErrorsUtils.createArray("name"), Constants.ERROR_CODE_NOT_FOUND);
    
	// validate startTime
	if ((event.getStartTime() != null) && (event.getStartTime().getTime() != 0)){
	    if (! RDValidationUtils.saneTime(event.getStartTime())) {
		errors.rejectValue("startTime", "errors.generic.insanedate", ErrorsUtils.createArray("StartTime", event.getStartTime()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}
	
	// validate endTime
	if ((event.getEndTime() != null) && (event.getEndTime().getTime() != 0)){
	    if (! RDValidationUtils.saneTime(event.getEndTime())) {
		errors.rejectValue("endTime", "errors.generic.insanedate", ErrorsUtils.createArray("EndTime", event.getEndTime()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate locale
	RDValidationUtils.validateLocale(errors, event.getCity(), event.getRegion(), event.getCountry(), event.getZip());

	// validate event url
	if (event.getEventUrl() != null) {
	    if (event.getEventUrl().length() > 2048) {
		errors.rejectValue("eventUrl", "errors.generic.longurl", ErrorsUtils.createArray("Event Image URL", event.getEventUrl().length()), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    if ((! "default".equals(event.getEventUrl())) && 
		(! RDValidationUtils.validURL(event.getEventUrl()))) {
		errors.rejectValue("eventUrl", "errors.generic.invalidurl", ErrorsUtils.createArray("Event Image URL"), Constants.ERROR_CODE_NOT_FOUND);	
	    }
	}

	// validate event Caption
	if ((event.getEventCaption() != null) &&
	    (event.getEventCaption().length() > 255)) {
	    errors.rejectValue("eventCaption", "errors.generic.lengthoutofrange", ErrorsUtils.createArray("Event Caption", 0, 255), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate Sponsor Website
	if (event.getSponsorWebsite() != null) {    
	    if (event.getSponsorWebsite().length() > 2048) {
		errors.rejectValue("sponsorWebsite", "errors.generic.longurl", ErrorsUtils.createArray("Sponsor Website URL", event.getSponsorWebsite().length()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    
	    if ((! "default".equals(event.getSponsorWebsite())) &&
		(! RDValidationUtils.validURL(event.getSponsorWebsite()))) {
		errors.rejectValue("sponsorWebsite", "errors.generic.invalidurl", ErrorsUtils.createArray("Sponsor Website URL"), Constants.ERROR_CODE_NOT_FOUND);	
	    }
	}

	// validate Sponsor URL
	if (event.getSponsorUrl() != null) {
	    if (event.getSponsorUrl().length() > 2048) {
		errors.rejectValue("sponsorUrl", "errors.generic.longurl", ErrorsUtils.createArray("Sponsor Image URL", event.getSponsorUrl().length()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    
	    if ((! "default".equals(event.getSponsorUrl())) &&
		(! RDValidationUtils.validURL(event.getSponsorUrl()))) {
		errors.rejectValue("sponsorUrl", "errors.generic.invalidurl", ErrorsUtils.createArray("Sponsor Image URL"), Constants.ERROR_CODE_NOT_FOUND);	
	    }    
	}
	
	// validate Sponsor Title
	if ((event.getSponsorTitle() != null) &&
	    (event.getSponsorTitle().length() > 32)) {
	    errors.rejectValue("sponsorTitle", "errors.generic.lengthoutofrange", ErrorsUtils.createArray("Sponsor Title", 0, 32), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate Sponsor Caption
	if ((event.getSponsorCaption() != null) &&
	    (event.getSponsorCaption().length() > 255)) {
	    errors.rejectValue("sponsorCaption", "errors.generic.lengthoutofrange", ErrorsUtils.createArray("Sponsor Caption", 0, 255), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate Sponsor Description
	if (event.getSponsorDescription().length() > 999999) {
	    errors.rejectValue("sponsorDescription", "errors.generic.lengthoutofrange", ErrorsUtils.createArray("Sponsor Description", 0, 999999), Constants.ERROR_CODE_NOT_FOUND);
	}

    }


    public void validate(Object obj, Errors errors) {
	Event event = (Event) obj;

	validateCommon(event, errors);

	// do business validation
	WebApplicationContext wac = getWebApplicationContext();
	eventDao = (EventDao) wac.getBean("eventDao");
	Event existingEvent = eventDao.getEventByName(event.getName());
	if (existingEvent != null) {
	    errors.rejectValue("name", "errors.editevent.namealreadyexists", ErrorsUtils.createArray(event.getName()), Constants.ERROR_CODE_NOT_FOUND);
	}

    }

}
