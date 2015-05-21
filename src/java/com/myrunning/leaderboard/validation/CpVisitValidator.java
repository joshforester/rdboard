package com.myrunning.leaderboard.validation;

/**
 *  File: CpVisitValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/25
 *  Description: Validator object for the cpVisit app.
 **/


import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.global.TimeConverter;
import com.myrunning.leaderboard.model.CpVisit;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;
import com.myrunning.leaderboard.services.ifacesvc.CpVisitService;


public class CpVisitValidator implements Validator {

    static Logger logger = Logger.getLogger(CpVisitValidator.class);

    protected CpVisitService cpVisitService;
    protected ControlPointDao controlPointDao;
    protected TeamDao teamDao;


    public void setCpVisitService(CpVisitService cpVisitService) {
	this.cpVisitService=cpVisitService;
    }

    protected CpVisitService getCpVisitService() {
	return cpVisitService;
    }

    public void setControlPointDao(ControlPointDao controlPointDao) {
	this.controlPointDao=controlPointDao;
    }

    public void setTeamDao(TeamDao teamDao) {
	this.teamDao=teamDao;
    }

    
    public CpVisitValidator() {
	//empty
    }


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(CpVisit.class);
    }


    protected void validateCommon(CpVisit cpVisit, Errors errors) {

	// validate ids provided 
	if (cpVisit.getTeamId() == 0) {
	    errors.rejectValue("teamId", "errors.generic.requiredfield", ErrorsUtils.createArray("team_id"), Constants.ERROR_CODE_NOT_FOUND);
	}

	if (cpVisit.getCpId() == 0) {
	    errors.rejectValue("cpId", "errors.generic.requiredfield", ErrorsUtils.createArray("cp_id"), Constants.ERROR_CODE_NOT_FOUND);
	}

	// setup some initial common variables for some of the global error messages
	Team team = teamDao.getTeamById(cpVisit.getTeamId());
	String teamStr = new String("");
	if (team != null) {
	    teamStr = team.getNumber() + ": " + team.getName();
	}

	ControlPoint controlPoint = controlPointDao.getControlPointById(cpVisit.getCpId());
	String cpStr = new String("");

	if (controlPoint != null) {
	    cpStr = controlPoint.getCpOrder() + ": " + controlPoint.getName();
	    if (controlPoint.getTaName() != null) {
		cpStr += "/" + controlPoint.getTaName();
	    }
	}
    
	// validate one of the following exists:
	// arrival, departure, isSkipped, isAcquired
	if (((cpVisit.getArrival() == null) || (cpVisit.getArrival().getTime() == 0)) &&
	    ((cpVisit.getDeparture() == null) || (cpVisit.getDeparture().getTime() == 0)) && 
	    (!cpVisit.getIsAcquired().equals("yes")) &&
	    (!cpVisit.getIsSkipped().equals("yes"))) {
	    errors.reject("errors.editcpvisit.fourrequiredfields", ErrorsUtils.createArray(teamStr, cpStr, "isAcquired", "arrival", "departure", "isSkipped"), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate isSkipped is not checked with arrival or departure
	if ((((cpVisit.getArrival() != null) && (cpVisit.getArrival().getTime() != 0)) ||
	     ((cpVisit.getDeparture() != null) && (cpVisit.getDeparture().getTime() != 0))) &&
	    (cpVisit.getIsSkipped().equals("yes"))) {
	    errors.reject("errors.editcpvisit.skippedwithtime", ErrorsUtils.createArray(teamStr, cpStr, "arrival", "departure", "isSkipped"), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate isAcquired is not checked with arrival or departure
	if ((((cpVisit.getArrival() != null) && (cpVisit.getArrival().getTime() != 0)) ||
	     ((cpVisit.getDeparture() != null) && (cpVisit.getDeparture().getTime() != 0))) &&
	    (cpVisit.getIsAcquired().equals("yes"))) {
	    errors.reject("errors.editcpvisit.acquiredwithtime", ErrorsUtils.createArray(teamStr, cpStr, "arrival", "departure", "isAcquired"), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate isAcquired and isSkipped are not both checked
	if ((cpVisit.getIsAcquired().equals("yes")) &&
	    (cpVisit.getIsSkipped().equals("yes"))) {
	    errors.reject("errors.editcpvisit.acquiredwithskipped", ErrorsUtils.createArray(teamStr, cpStr, "isAcquired", "isSkipped"), Constants.ERROR_CODE_NOT_FOUND);
	}


	// validate arrival
	if ((cpVisit.getArrival() != null) && (cpVisit.getArrival().getTime() != 0)) {
	    if (! RDValidationUtils.saneTime(cpVisit.getArrival())) {
		errors.rejectValue("arrival", "errors.generic.insanedate", ErrorsUtils.createArray("Arrival", cpVisit.getArrival()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate departure
	if ((cpVisit.getDeparture() != null) && (cpVisit.getDeparture().getTime() != 0)) {
	    if (! RDValidationUtils.saneTime(cpVisit.getDeparture())) {
		errors.rejectValue("departure", "errors.generic.insanedate", ErrorsUtils.createArray("Departure", cpVisit.getDeparture()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// also make sure arrival is not after departure if both are set
	if ((cpVisit.getArrival() != null) && 
	    (cpVisit.getDeparture() != null) &&
	    (cpVisit.getDeparture().getTime() != 0)) {
	    if (cpVisit.getDeparture().before(cpVisit.getArrival())) {
		errors.reject("errors.editcpvisit.arrivalafterdeparture", ErrorsUtils.createArray(teamStr, cpStr, cpVisit.getArrival(), cpVisit.getDeparture()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate timeBonusAssessed is valid time period
	if ((cpVisit.getTimeBonusAssessed() != null) && (cpVisit.getTimeBonusAssessed().getTime() != 0)) {
	    if (! RDValidationUtils.saneTimePeriod(cpVisit.getTimeBonusAssessed())) {
		errors.rejectValue("timeBonusAssessed", "errors.generic.insanedate", ErrorsUtils.createArray("timeBonusAssessed", TimeConverter.getFormattedTimeFromMillis(cpVisit.getTimeBonusAssessed().getTime())), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}
	
	// validate timePenaltyAssessed is valid time period
	if ((cpVisit.getTimePenaltyAssessed() != null) && (cpVisit.getTimePenaltyAssessed().getTime() != 0)) {
	    if (! RDValidationUtils.saneTimePeriod(cpVisit.getTimePenaltyAssessed())) {
		errors.rejectValue("timePenaltyAssessed", "errors.generic.insanedate", ErrorsUtils.createArray("timePenaltyAssessed", TimeConverter.getFormattedTimeFromMillis(cpVisit.getTimePenaltyAssessed().getTime())), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// make sure cpBonusAssessed is within sane range
	if ((cpVisit.getCpBonusAssessed() < CpVisit.CP_BONUS_ASSESSED_MIN) ||
	    (CpVisit.CP_BONUS_ASSESSED_MAX < cpVisit.getCpBonusAssessed())) {
	    errors.rejectValue("cpBonusAssessed", "errors.generic.outofrange", ErrorsUtils.createArray("cpBonusAssessed", cpVisit.getCpBonusAssessed(), CpVisit.CP_BONUS_ASSESSED_MIN, CpVisit.CP_BONUS_ASSESSED_MAX), Constants.ERROR_CODE_NOT_FOUND);
	}

	// make sure cpPenaltyAssessed is within sane range
	if ((cpVisit.getCpPenaltyAssessed() < CpVisit.CP_PENALTY_ASSESSED_MIN) ||
	    (CpVisit.CP_PENALTY_ASSESSED_MAX < cpVisit.getCpPenaltyAssessed())) {
	    errors.rejectValue("cpPenaltyAssessed", "errors.generic.outofrange", ErrorsUtils.createArray("cpPenaltyAssessed", cpVisit.getCpPenaltyAssessed(), CpVisit.CP_PENALTY_ASSESSED_MIN, CpVisit.CP_PENALTY_ASSESSED_MAX), Constants.ERROR_CODE_NOT_FOUND);
	}

	// make sure weightBonusAssessed is within sane range
	if ((cpVisit.getWeightBonusAssessed() < CpVisit.WEIGHT_BONUS_ASSESSED_MIN) ||
	    (CpVisit.WEIGHT_BONUS_ASSESSED_MAX < cpVisit.getWeightBonusAssessed())) {
	    errors.rejectValue("weightBonusAssessed", "errors.generic.outofrange", ErrorsUtils.createArray("weightBonusAssessed", cpVisit.getWeightBonusAssessed(), CpVisit.WEIGHT_BONUS_ASSESSED_MIN, CpVisit.WEIGHT_BONUS_ASSESSED_MAX), Constants.ERROR_CODE_NOT_FOUND);
	}

	// make sure weightPenaltyAssessed is within sane range
	if ((cpVisit.getWeightPenaltyAssessed() < CpVisit.WEIGHT_PENALTY_ASSESSED_MIN) ||
	    (CpVisit.WEIGHT_PENALTY_ASSESSED_MAX < cpVisit.getWeightPenaltyAssessed())) {
	    errors.rejectValue("weightPenaltyAssessed", "errors.generic.outofrange", ErrorsUtils.createArray("weightPenaltyAssessed", cpVisit.getWeightPenaltyAssessed(), CpVisit.WEIGHT_PENALTY_ASSESSED_MIN, CpVisit.WEIGHT_PENALTY_ASSESSED_MAX), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate isAcquired
	RDValidationUtils.rejectIfInvalidCheckboxValue(errors, "isAcquired", "errors.generic.invalidcheckboxvalue", ErrorsUtils.createArray(cpVisit.getIsAcquired()), Constants.ERROR_CODE_NOT_FOUND);

	// validate isSkipped
	RDValidationUtils.rejectIfInvalidCheckboxValue(errors, "isSkipped", "errors.generic.invalidcheckboxvalue", ErrorsUtils.createArray(cpVisit.getIsSkipped()), Constants.ERROR_CODE_NOT_FOUND);

	// validate isUnofficial
	RDValidationUtils.rejectIfInvalidCheckboxValue(errors, "isUnofficial", "errors.generic.invalidcheckboxvalue", ErrorsUtils.createArray(cpVisit.getIsUnofficial()), Constants.ERROR_CODE_NOT_FOUND);

	// validate isIncomplete
	RDValidationUtils.rejectIfInvalidCheckboxValue(errors, "isIncomplete", "errors.generic.invalidcheckboxvalue", ErrorsUtils.createArray(cpVisit.getIsIncomplete()), Constants.ERROR_CODE_NOT_FOUND);

	// validate isWithdrawn
	RDValidationUtils.rejectIfInvalidCheckboxValue(errors, "isWithdrawn", "errors.generic.invalidcheckboxvalue", ErrorsUtils.createArray(cpVisit.getIsWithdrawn()), Constants.ERROR_CODE_NOT_FOUND);

	// validate isDisqualified
	RDValidationUtils.rejectIfInvalidCheckboxValue(errors, "isDisqualified", "errors.generic.invalidcheckboxvalue", ErrorsUtils.createArray(cpVisit.getIsDisqualified()), Constants.ERROR_CODE_NOT_FOUND);

    }


    public void validate(Object obj, Errors errors) {
	CpVisit cpVisit = (CpVisit) obj;

	validateCommon(cpVisit, errors);

	// do business validation
	CpVisit existingCpVisit = cpVisitService.getCpVisitByTeamIdAndCpId(cpVisit.getTeamId(), cpVisit.getCpId());
	if (existingCpVisit != null) {
	    errors.rejectValue("teamId", "errors.editcpvisit.teamidcpidalreadyexistsforcpvisit", ErrorsUtils.createArray(String.valueOf(cpVisit.getTeamId()), String.valueOf(cpVisit.getCpId())), Constants.ERROR_CODE_NOT_FOUND);
	    errors.rejectValue("cpId", "errors.editcpvisit.teamidcpidalreadyexistsforcpvisit", ErrorsUtils.createArray(String.valueOf(cpVisit.getTeamId()), String.valueOf(cpVisit.getCpId())), Constants.ERROR_CODE_NOT_FOUND);
	}

	logger.debug("Validating CpVisit:  " + cpVisit);

    }

}
