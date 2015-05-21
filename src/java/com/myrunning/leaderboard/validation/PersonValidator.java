package com.myrunning.leaderboard.validation;

/**
 *  File: PersonValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Validator object for the person app.
 **/


import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.db.dao.ifacedao.PersonDao;


public class PersonValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(PersonValidator.class);

    private PersonDao personDao;



    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Person.class);
    }

    
    protected void commonValidate(Person person, Errors errors) {
	
	// validate name
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "errors.generic.requiredfield", ErrorsUtils.createArray("firstName"), Constants.ERROR_CODE_NOT_FOUND);

	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "errors.generic.requiredfield", ErrorsUtils.createArray("lastName"), Constants.ERROR_CODE_NOT_FOUND);

	// validate locale
	RDValidationUtils.validateLocale(errors, person.getCity(), person.getRegion(), person.getCountry(), person.getZip());
	
	// validate email
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errors.generic.requiredfield", ErrorsUtils.createArray("email"), Constants.ERROR_CODE_NOT_FOUND);

	if (errors.getFieldErrorCount("email") == 0) {
	    try {
		InternetAddress emailAddress = new InternetAddress(person.getEmail(), true);
	    } catch (AddressException ae) {
		errors.rejectValue("email", "errors.editperson.emailinvalid", ErrorsUtils.createArray(person.getEmail()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate gender
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "errors.generic.requiredfield", ErrorsUtils.createArray("gender"), Constants.ERROR_CODE_NOT_FOUND);

	if (errors.getFieldErrorCount("gender") == 0) {
	    if ((person.getGender() == null) ||
		((! person.getGender().equals("male")) &&
		 (! person.getGender().equals("female")))) {
		errors.rejectValue("gender", "errors.editperson.gendernotrecognized", ErrorsUtils.createArray(person.getGender()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate birthday
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "errors.generic.requiredfield", ErrorsUtils.createArray("birthday"), Constants.ERROR_CODE_NOT_FOUND);
	if (errors.getFieldErrorCount("birthday") == 0) {
	    if (! RDValidationUtils.saneBirthday(person.getBirthday())) {
		errors.rejectValue("birthday", "errors.generic.insanedate", ErrorsUtils.createArray("Birthday", person.getBirthday()), Constants.ERROR_CODE_NOT_FOUND);
	    }
	}

	// validate at least one phone number provided
	if ((RDValidationUtils.nothingString(person.getHomePhone())) &&
	    (RDValidationUtils.nothingString(person.getCellPhone())) &&
	    (RDValidationUtils.nothingString(person.getWorkPhone()))) {
	    errors.reject("errors.generic.threerequiredfields", ErrorsUtils.createArray("homePhone", "cellPhone", "workPhone"), Constants.ERROR_CODE_NOT_FOUND);
	} else {
	    
	    // validate home phone
	    if ((!RDValidationUtils.nothingString(person.getHomePhone())) &&
		(!RDValidationUtils.validPhoneNumber(person.getHomePhone()))) {
		errors.rejectValue("homePhone", "errors.generic.invalidphonenumber", ErrorsUtils.createArray(person.getHomePhone()), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    // validate cell phone
	    if ((!RDValidationUtils.nothingString(person.getCellPhone())) &&
		(!RDValidationUtils.validPhoneNumber(person.getCellPhone()))) {
		errors.rejectValue("cellPhone", "errors.generic.invalidphonenumber", ErrorsUtils.createArray(person.getCellPhone()), Constants.ERROR_CODE_NOT_FOUND);
	    }

	    // validate work phone
	    if ((!RDValidationUtils.nothingString(person.getWorkPhone())) &&
		(!RDValidationUtils.validPhoneNumber(person.getWorkPhone()))) {
		errors.rejectValue("workPhone", "errors.generic.invalidphonenumber", ErrorsUtils.createArray(person.getWorkPhone()), Constants.ERROR_CODE_NOT_FOUND);
	    }

	}


	// validate website URL syntax
	String website = person.getWebsite();
	if (website != null) {
	    if (StringUtils.hasText(website)) {

		// fix website if protocol not defined
		if ((! website.substring(0,7).equals("http://")) &&
		    (! website.substring(0,8).equals("https://"))) {
		    person.setWebsite("http://" + website);
		}
		
		// validate format
		try {
		    URL url = new URL(website);
		} catch (MalformedURLException mue) {
		    errors.rejectValue("website", "errors.editperson.websiteinvalid", ErrorsUtils.createArray(website), Constants.ERROR_CODE_NOT_FOUND);
		}
	    }
	}    	

    }


    public void validate(Object obj, Errors errors) {
	Person person = (Person) obj;

	commonValidate(person, errors);
    }


}
