package com.myrunning.leaderboard.validation;

/**
 *  File: ControlPointValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/20
 *  Description: Validator object for the Control Point app.
 **/


import java.util.List;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import com.bbn.openmap.proj.coords.ZonedUTMPoint;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;


public class ControlPointValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(ControlPointValidator.class);

    private ControlPointDao controlPointDao;
    private EventDao eventDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(ControlPoint.class);
    }


    protected void validateCommon(ControlPoint controlPoint, Errors errors) {
    
	// validate name
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.generic.requiredfield", ErrorsUtils.createArray("name"), Constants.ERROR_CODE_NOT_FOUND);

	// validate courseId
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseId", "errors.generic.requiredfield", ErrorsUtils.createArray("courseId"), Constants.ERROR_CODE_NOT_FOUND);


	// fix order if not provided
	WebApplicationContext wac = getWebApplicationContext();
	controlPointDao = (ControlPointDao) wac.getBean("controlPointDao");
	if (controlPoint.getCpOrder() == 0) {
	    Integer maxCpOrder = controlPointDao.getMaxControlPointOrderByCourseId(controlPoint.getCourseId());
	    if (maxCpOrder == null) {
		controlPoint.setCpOrder(1);
	    } else {
		controlPoint.setCpOrder(Integer.valueOf(maxCpOrder) + 1);
	    }
	}
	
	// set to null so DB insert/update works
	if (RDValidationUtils.nothingString(controlPoint.getZoneChar())) {
	    controlPoint.setZoneChar(null);
	}

	// validate coordinates
	// 17D 630084 4833438  
	
	// validate if one of the coordinate variables is supplied, must supply all others
	boolean coordinateSupplied = false;
	if ((controlPoint.getZoneNumber() != 0) &&
	    (!RDValidationUtils.nothingString(controlPoint.getZoneChar())) &&
	    (controlPoint.getEasternly() != 0) &&
	    (controlPoint.getNorthernly() != 0)) {
	    
	    coordinateSupplied = true;

	} else if ((controlPoint.getZoneNumber() != 0) ||
		   (!RDValidationUtils.nothingString(controlPoint.getZoneChar())) ||
		   (controlPoint.getEasternly() != 0) ||
		   (controlPoint.getNorthernly() != 0)) {
	    
	    // identify which was not supplied and mention that if one is supplied
	    // then the others must be provided "blah, blah, blah, and blah must be 
	    // supplied together

	    if (controlPoint.getZoneNumber() == 0) {
		errors.rejectValue("zoneNumber", "errors.editcontrolpoint.fullcoordinatenotsupplied", ErrorsUtils.createArray("Zone Number"), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    if (RDValidationUtils.nothingString(controlPoint.getZoneChar())) {
		errors.rejectValue("zoneChar", "errors.editcontrolpoint.fullcoordinatenotsupplied", ErrorsUtils.createArray("Zone Letter"), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    if (controlPoint.getEasternly() == 0) {
		errors.rejectValue("easternly", "errors.editcontrolpoint.fullcoordinatenotsupplied", ErrorsUtils.createArray("Easternly"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    
	    if (controlPoint.getNorthernly() == 0) {
		errors.rejectValue("northernly", "errors.editcontrolpoint.fullcoordinatenotsupplied", ErrorsUtils.createArray("Northernly"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    
	}


	if (coordinateSupplied) {
	    
	    // validate zone number
	    if ((controlPoint.getZoneNumber() < ControlPoint.ZONE_NUMBER_MIN) ||
		(ControlPoint.ZONE_NUMBER_MAX < controlPoint.getZoneNumber())) {
		errors.rejectValue("zoneNumber", "errors.generic.outofrange", ErrorsUtils.createArray("Zone Number", controlPoint.getZoneNumber(), ControlPoint.ZONE_NUMBER_MIN, ControlPoint.ZONE_NUMBER_MAX), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    
	    // validate zone letter
	    ZonedUTMPoint point = null;
	    try {
		point = new ZonedUTMPoint(controlPoint.getNorthernly(),
					  controlPoint.getEasternly(),
					  controlPoint.getZoneNumber(),
					  controlPoint.getZoneChar().charAt(0));
	    } catch (NumberFormatException nfe) {
		errors.rejectValue("zoneChar", "errors.editcontrolpoint.zonecharnotrecognized", ErrorsUtils.createArray("zoneChar"), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    // validate easternly
	    if ((controlPoint.getEasternly() < ControlPoint.EASTERNLY_MIN) ||
		(ControlPoint.EASTERNLY_MAX < controlPoint.getEasternly())) {
		errors.rejectValue("easternly", "errors.generic.outofrange", ErrorsUtils.createArray("Easternly", controlPoint.getEasternly(), ControlPoint.EASTERNLY_MIN, ControlPoint.EASTERNLY_MAX), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    // validate northernly
	    if ((controlPoint.getNorthernly() < ControlPoint.NORTHERNLY_MIN) ||
		(ControlPoint.NORTHERNLY_MAX < controlPoint.getNorthernly())) {
		errors.rejectValue("northernly", "errors.generic.outofrange", ErrorsUtils.createArray("Northernly", controlPoint.getNorthernly(), ControlPoint.NORTHERNLY_MIN, ControlPoint.NORTHERNLY_MAX), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate altitude if exists makes sense < 27000ft
	if (controlPoint.getAltitude() != 0) {
	    if ((controlPoint.getAltitude() < ControlPoint.ALTITUDE_MIN) ||
		(ControlPoint.ALTITUDE_MAX < controlPoint.getAltitude())) {
		errors.rejectValue("altitude", "errors.generic.outofrange", ErrorsUtils.createArray("Altitude", controlPoint.getAltitude(), ControlPoint.ALTITUDE_MIN, ControlPoint.ALTITUDE_MAX), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate cutoff
	if ((controlPoint.getCutoff() != null) && (controlPoint.getCutoff().getTime() != 0)){
	    if (! RDValidationUtils.saneTime(controlPoint.getCutoff())) {
		errors.rejectValue("cutoff", "errors.generic.insanedate", ErrorsUtils.createArray("Cutoff", controlPoint.getCutoff()), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    // validate cutoff is within event's start and end time if it has them
	    eventDao = (EventDao) wac.getBean("eventDao");	
	    Event event = eventDao.getEventByCourseId(controlPoint.getCourseId());
	    if (event.getStartTime().getTime() != 0) {
		if (event.getStartTime().after(controlPoint.getCutoff())) {
		    errors.rejectValue("cutoff", "errors.editcontrolpoint.beforeeventstart", ErrorsUtils.createArray("Cutoff", controlPoint.getCutoff(), event.getStartTime()), Constants.ERROR_CODE_NOT_FOUND);
		}
	    }
	    if (event.getEndTime().getTime() != 0) {
		if (event.getEndTime().before(controlPoint.getCutoff())) {
		    errors.rejectValue("cutoff", "errors.editcontrolpoint.aftereventend", ErrorsUtils.createArray("Cutoff", controlPoint.getCutoff(), event.getEndTime()), Constants.ERROR_CODE_NOT_FOUND);
		}
	    }
	    
	}

	
	// validate fromDiscipline/toDiscipline is one of the existing disciplines
	// in the enum, but only if the weren't flagged as empty first
	List<String> disciplines = ControlPoint.getDisciplines();
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDiscipline", "errors.generic.requiredfield", ErrorsUtils.createArray("fromDiscipline"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("fromDiscipline") == 0) {
	    if (! disciplines.contains(controlPoint.getFromDiscipline())) {
		errors.rejectValue("fromDiscipline", "errors.editcontrolpoint.disciplinenotrecognized", ErrorsUtils.createArray("fromDiscipline"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDiscipline", "errors.generic.requiredfield", ErrorsUtils.createArray("toDiscipline"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("toDiscipline") == 0) {
	    if (! disciplines.contains(controlPoint.getToDiscipline())) {
		errors.rejectValue("toDiscipline", "errors.editcontrolpoint.disciplinenotrecognized", ErrorsUtils.createArray("toDiscipline"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}


    }

    
    public void validate(Object obj, Errors errors) {
	ControlPoint controlPoint = (ControlPoint) obj;

	validateCommon(controlPoint, errors);

	// do business validation
	WebApplicationContext wac = getWebApplicationContext();
	controlPointDao = (ControlPointDao) wac.getBean("controlPointDao");
	ControlPoint existingControlPoint = controlPointDao.getControlPointByCourseIdAndName(controlPoint.getCourseId(), controlPoint.getName());
	if (existingControlPoint != null) {
	    logger.debug("ControlPoint " + existingControlPoint.getName() + " already exists for course id " + existingControlPoint.getCourseId());
	    errors.rejectValue("name", "errors.editcontrolpoint.namealreadyexistsforcourse", ErrorsUtils.createArray(controlPoint.getName(), String.valueOf(controlPoint.getCourseId())), Constants.ERROR_CODE_NOT_FOUND);
	}
	
    }

}
