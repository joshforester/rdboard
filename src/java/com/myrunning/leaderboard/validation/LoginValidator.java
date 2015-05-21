package com.myrunning.leaderboard.validation;

/**
 *  File: LoginValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/09/10
 *  Description: Validator object for the login app.
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
import com.myrunning.leaderboard.model.Credentials;
import com.myrunning.leaderboard.db.dao.ifacedao.AdminDao;


public class LoginValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(LoginValidator.class);

    private AdminDao adminDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Credentials.class);
    }


    public void validate(Object obj, Errors errors) {

	Credentials credentials = (Credentials) obj;

	WebApplicationContext wac = getWebApplicationContext();
	adminDao = (AdminDao) wac.getBean("adminDao");
	

	// validate username
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "errors.generic.requiredfield", ErrorsUtils.createArray("username"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("username") == 0) {
	    if (adminDao.getAdminByUsername(credentials.getUsername()) == null) {
		errors.rejectValue("username", "errors.login.usernamedoesnotexist", ErrorsUtils.createArray(credentials.getUsername()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate password
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.generic.requiredfield", ErrorsUtils.createArray("password"), Constants.ERROR_CODE_NOT_FOUND);

	// validate user/password pair
	if (errors.getFieldErrorCount("username") == 0) {
	    if (adminDao.getAdminByUsernameAndPassword(credentials.getUsername(), credentials.getPassword()) == null) {
		errors.rejectValue("password", "errors.login.passwordinvalid", ErrorsUtils.createArray(credentials.getUsername()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

    }

}
