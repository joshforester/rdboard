package com.myrunning.leaderboard.validation;

/**
 *  File: ComparatorValidator.java
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
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Comparator;
import com.myrunning.leaderboard.db.dao.ifacedao.ComparatorDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;


public class ComparatorValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(ComparatorValidator.class);

    private ComparatorDao comparatorDao;
    private CourseDao courseDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Comparator.class);
    }

    
    protected void validateCommon(Comparator comparator, Errors errors) {

	// validate courseId
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseId", "errors.generic.requiredfield", ErrorsUtils.createArray("courseId"), Constants.ERROR_CODE_NOT_FOUND);

	// validate tieAction
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tieAction", "errors.generic.requiredfield", ErrorsUtils.createArray("tieAction"), Constants.ERROR_CODE_NOT_FOUND);
	

	// do business validation
	
	// fix order if not provided
	WebApplicationContext wac = getWebApplicationContext();
	comparatorDao = (ComparatorDao) wac.getBean("comparatorDao");
	if (comparator.getComparatorOrder() == 0) {
	    Integer maxComparatorOrder = comparatorDao.getMaxComparatorOrderByCourseId(comparator.getCourseId());
	    if (maxComparatorOrder == null) {
		comparator.setComparatorOrder(1);
	    } else {
		comparator.setComparatorOrder(Integer.valueOf(maxComparatorOrder) + 1);
	    }
	}

	// validate course exists
	courseDao = (CourseDao) wac.getBean("courseDao");
	if (courseDao.getCourseById(comparator.getCourseId()) == null) {
	    errors.rejectValue("courseId", "errors.editcomparator.courseiddoesnotexist", ErrorsUtils.createArray("courseId"), Constants.ERROR_CODE_NOT_FOUND);
	}
	

	// validate tieAction is one of the existing tieActions
	// in the enum, but only if the wasn't flagged as empty first
	List<String> tieActions = Comparator.getTieActions();
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tieAction", "errors.generic.requiredfield", ErrorsUtils.createArray("tieAction"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("tieAction") == 0) {
	    if (! tieActions.contains(comparator.getTieAction())) {
		errors.rejectValue("tieAction", "errors.editcomparator.tieactionnotrecognized", ErrorsUtils.createArray("tieAction"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate type is one of the existing types
	// in the enum, but only if the weren't flagged as empty first
	List<String> types = Comparator.getTypes();
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "errors.generic.requiredfield", ErrorsUtils.createArray("type"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("type") == 0) {
	    if (! types.contains(comparator.getType())) {
		errors.rejectValue("type", "errors.editcomparator.typenotrecognized", ErrorsUtils.createArray("type"), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

    }


    public void validate(Object obj, Errors errors) {
	Comparator comparator = (Comparator) obj;

	validateCommon(comparator, errors);
    }

}
