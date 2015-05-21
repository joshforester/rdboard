package com.myrunning.leaderboard.validation;

/**
 *  File: EditAdminValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Validator object for the edit admin app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;


public class EditAdminValidator extends AdminValidator implements Validator {

    static Logger logger = Logger.getLogger(EditAdminValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Admin.class);
    }


    public void validate(Object obj, Errors errors) {
	Admin admin = (Admin) obj;

	// validate id provided to edit method
	if (admin.getId() == 0) {
	    errors.rejectValue("id", "errors.generic.requiredfield", ErrorsUtils.createArray("id"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(admin, errors);
    }

}
