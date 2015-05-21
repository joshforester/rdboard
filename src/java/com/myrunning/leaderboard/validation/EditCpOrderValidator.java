package com.myrunning.leaderboard.validation;

/**
 *  File: EditCpOrderValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/11/06
 *  Description: Validator object for the edit cp order app.
 **/


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.CpOrderShift;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;


public class EditCpOrderValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(EditCpOrderValidator.class);

    private ControlPointDao controlPointDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(CpOrderShift.class);
    }


    public void validate(Object obj, Errors errors) {

	CpOrderShift cpOrderShift = (CpOrderShift) obj;

	// validate cp id
	if (cpOrderShift.getCpId() == 0) {
	    errors.reject("errors.generic.requiredfield", ErrorsUtils.createArray("cpId"), Constants.ERROR_CODE_NOT_FOUND);
	}

	// validate change
	if (RDValidationUtils.nothingString(cpOrderShift.getChange())) {
	    errors.reject("errors.generic.requiredfield", ErrorsUtils.createArray("change"), Constants.ERROR_CODE_NOT_FOUND);
	}

	/*
	// validate cp id
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpId", "errors.generic.requiredfield", ErrorsUtils.createArray("cpId"), Constants.ERROR_CODE_NOT_FOUND);

	// validate change
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "change", "errors.generic.requiredfield", ErrorsUtils.createArray("change"), Constants.ERROR_CODE_NOT_FOUND);
	*/
    
	if ((!cpOrderShift.getChange().equals("increment")) &&
	    (!cpOrderShift.getChange().equals("decrement"))) {
	    errors.reject("errors.editcporder.invalidchange", ErrorsUtils.createArray(cpOrderShift.getChange()), Constants.ERROR_CODE_NOT_FOUND);
	}


	// do business validation
	WebApplicationContext wac = getWebApplicationContext();
	controlPointDao = (ControlPointDao) wac.getBean("controlPointDao");
	ControlPoint existingControlPoint = controlPointDao.getControlPointById(cpOrderShift.getCpId());
	if (existingControlPoint == null) {
	    errors.reject("errors.editcporder.controlpointdoesnotexist", ErrorsUtils.createArray(String.valueOf(cpOrderShift.getCpId())), Constants.ERROR_CODE_NOT_FOUND);
	} else {

	    // verify control point order is within valid range
	    if ((existingControlPoint.getCpOrder() == 1) &&
		(cpOrderShift.getChange().equals("decrement"))) {
		errors.reject("errors.editcporder.ordertoosmall", ErrorsUtils.createArray(existingControlPoint.getCpOrder()), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    int maxOrder = controlPointDao.getMaxControlPointOrderByCourseId(existingControlPoint.getCourseId());
	    if ((existingControlPoint.getCpOrder() == maxOrder) &&
		(cpOrderShift.getChange().equals("increment"))) {
		errors.reject("errors.editcporder.ordertoolarge", ErrorsUtils.createArray(existingControlPoint.getCpOrder()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	    
	}

    }
}
