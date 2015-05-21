package com.myrunning.leaderboard.validation;

/**
 *  File: EditCourseValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Validator object for the edit course app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Course;


public class EditCourseValidator extends CourseValidator implements Validator {

    static Logger logger = Logger.getLogger(EditCourseValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Course.class);
    }


    public void validate(Object obj, Errors errors) {
	Course course = (Course) obj;

	// validate id provided to edit method
	if (course.getId() == 0) {
	    errors.rejectValue("id", "errors.generic.requiredfield", ErrorsUtils.createArray("id"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(course, errors);
    }

}
