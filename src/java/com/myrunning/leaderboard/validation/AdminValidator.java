package com.myrunning.leaderboard.validation;

/**
 *  File: AdminValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/21
 *  Description: Validator object for the admin app.
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
import com.myrunning.leaderboard.security.PasswordFilter;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.db.dao.ifacedao.AdminDao;


public class AdminValidator extends PersonValidator implements Validator {

    static Logger logger = Logger.getLogger(AdminValidator.class);

    private AdminDao adminDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Admin.class);
    }

    
    protected void validateCommon(Admin admin, Errors errors) {

	// validate username
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "errors.generic.requiredfield", ErrorsUtils.createArray("username"), Constants.ERROR_CODE_NOT_FOUND);

	// validate passwords 
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.generic.requiredfield", ErrorsUtils.createArray("password"), Constants.ERROR_CODE_NOT_FOUND);
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "errors.generic.requiredfield", ErrorsUtils.createArray("confirmPassword"), Constants.ERROR_CODE_NOT_FOUND);
	if ((errors.getFieldErrorCount("password") == 0) &&
	    (errors.getFieldErrorCount("confirmPassword") == 0)) {
	    if (! admin.getPassword().equals(admin.getConfirmPassword())) {
		errors.rejectValue("confirmPassword", "errors.editadmin.passwordconfirmmismatch", Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// now validate password complexity
	if ((errors.getFieldErrorCount("password") == 0) &&
	    (errors.getFieldErrorCount("confirmPassword") == 0)) {
	 
	    if (PasswordFilter.isAdminPasswordComboWeak(admin, admin.getPassword())) {
		errors.rejectValue("password", "errors.editadmin.insufficientpasswordstrength", ErrorsUtils.createArray(String.valueOf(Constants.PASSWORD_MIN_CHARS)), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

    }


    public void validate(Object obj, Errors errors) {

	super.validate(obj, errors);

	Admin admin = (Admin) obj;
	validateCommon(admin, errors);

	// validate username
	WebApplicationContext wac = getWebApplicationContext();
	adminDao = (AdminDao) wac.getBean("adminDao");
	if (errors.getFieldErrorCount("username") == 0) {
	    Admin existingAdmin = adminDao.getAdminByUsername(admin.getUsername());
	    if ((existingAdmin != null) &&
		(admin.getId() != existingAdmin.getId())) {
		errors.rejectValue("username", "errors.editadmin.usernamealreadyexists", ErrorsUtils.createArray(admin.getUsername()), Constants.ERROR_CODE_NOT_FOUND);	    
	    }

	}
	
    }

}
