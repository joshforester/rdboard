package com.myrunning.leaderboard.validation;

/**
 *  File: CourseValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Validator object for the course app.
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
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;


public class CourseValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(CourseValidator.class);

    private CourseDao courseDao;
    private EventDao eventDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Course.class);
    }


    protected void validateCommon(Course course, Errors errors) {

	// validate name
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.generic.requiredfield", ErrorsUtils.createArray("name"), Constants.ERROR_CODE_NOT_FOUND);

	// validate eventId
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventId", "errors.generic.requiredfield", ErrorsUtils.createArray("eventId"), Constants.ERROR_CODE_NOT_FOUND);

	// validate type
	List<String> types = Course.getTypes();
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "errors.generic.requiredfield", ErrorsUtils.createArray("type"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("type") == 0) {
	    if (! types.contains(course.getType())) {
		errors.rejectValue("type", "errors.editcourse.typenotrecognized", ErrorsUtils.createArray("type"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}



	// validate length hours and length miles
	if ((course.getLengthHours() < 0) || (288 < course.getLengthHours())) {
	    errors.rejectValue("lengthHours", "errors.generic.outofrange", ErrorsUtils.createArray("Length Hours", course.getLengthHours(), 0, 288), Constants.ERROR_CODE_NOT_FOUND);
	}
	if ((course.getLengthMiles() < 0) || (999 < course.getLengthMiles())) {
	    errors.rejectValue("lengthMiles", "errors.generic.outofrange", ErrorsUtils.createArray("Length Miles", course.getLengthMiles(), 0, 999), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate ScribbleLive ID
	// ex.  34243
	try {
	    int scribbleliveId = Integer.parseInt(course.getScribbleliveId());

	    if ((scribbleliveId < 10000) || (99999 < scribbleliveId)) {
		errors.rejectValue("scribbleliveId", "errors.generic.outofrange", ErrorsUtils.createArray("ScribbleLive ID", scribbleliveId, 10000, 99999), Constants.ERROR_CODE_NOT_FOUND);
	    }
	} catch (NumberFormatException nfe) {
	    if (! course.getScribbleliveId().equals("default")) {
		errors.rejectValue("scribbleliveId", "typeMismatch.int", ErrorsUtils.createArray("ScribbleLive ID"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate CoverItLive ID
	// ex.  a55d6c7c98
	if ((! "default".equals(course.getCoveritliveId())) &&
	    (! "".equals(course.getCoveritliveId()))) {
	    if (course.getCoveritliveId().length() != 10) {
		errors.rejectValue("coveritliveId", "errors.generic.length", ErrorsUtils.createArray("CoverItLive ID", course.getCoveritliveId(), 10), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    
	    if (! RDValidationUtils.validAlphaNumeric(course.getCoveritliveId())) {
		errors.rejectValue("coveritliveId", "errors.generic.alphanumeric", ErrorsUtils.createArray("CoverItLive ID"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate feedburner ids
	// TODO



	// validate competitorGmapSuffix
	// ex.  ms?ie=UTF8&hl=en&msa=0&msid=109614054830900156277.000481882f3d4c6dfd9bb&ll=36.949892,-95.273437&spn=19.211007,27.777215
	if (course.getCompetitorGmapSuffix() != null) {
	    if (course.getCompetitorGmapSuffix().length() > 1024) {
		errors.rejectValue("competitorGmapSuffix", "errors.generic.lengthoutofrange", ErrorsUtils.createArray("Google Maps Suffix", 0, 1024), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    
	    if ((! "default".equals(course.getCompetitorGmapSuffix())) &&
		(! "".equals(course.getCompetitorGmapSuffix()))) {
		
		if (! RDValidationUtils.validURL("http://maps.google.com/maps/" + course.getCompetitorGmapSuffix())) {
		    errors.rejectValue("competitorGmapSuffix", "errors.generic.invalidurl", ErrorsUtils.createArray("Google Maps Suffix"), Constants.ERROR_CODE_NOT_FOUND);	
		}
		
		// note:  should NOT contain the "source" parameter (ex.  source=embed)
		// remove the source parameter, because we add it in upon display
		course.setCompetitorGmapSuffix(course.getCompetitorGmapSuffix().replaceAll("&source=[^&]*", ""));
		
		// note:  should NOT contain the "output" parameter (ex.  output=embed)
		// remove the output parameter, because we add it in upon display
		course.setCompetitorGmapSuffix(course.getCompetitorGmapSuffix().replaceAll("&output=[^&]*", ""));
	    }
	}

	    
	// validate Description
	if (course.getDescription().length() > 999999) {
	    errors.rejectValue("description", "errors.generic.lengthoutofrange", ErrorsUtils.createArray("Description", 0, 999999), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	
    
	// do business validation
	WebApplicationContext wac = getWebApplicationContext();
	eventDao = (EventDao) wac.getBean("eventDao");
	Event existingEvent = eventDao.getEventById(course.getEventId());
	if (existingEvent == null) {
	    errors.rejectValue("eventId", "errors.editcourse.eventdoesnotexist", ErrorsUtils.createArray(String.valueOf(course.getEventId())), Constants.ERROR_CODE_NOT_FOUND);
	}

    }


    public void validate(Object obj, Errors errors) {
	Course course = (Course) obj;

	validateCommon(course, errors);

	// do business validation
	WebApplicationContext wac = getWebApplicationContext();
	courseDao = (CourseDao) wac.getBean("courseDao");
	Course existingCourse = courseDao.getCourseByEventIdAndName(course.getEventId(), course.getName());
	if (existingCourse != null) {
	    logger.debug("Course " + existingCourse.getName() + " already exists for event id " + existingCourse.getEventId());
	    errors.rejectValue("name", "errors.editcourse.namealreadyexistsforevent", ErrorsUtils.createArray(course.getName(), String.valueOf(course.getEventId())), Constants.ERROR_CODE_NOT_FOUND);
	}

    }

}
