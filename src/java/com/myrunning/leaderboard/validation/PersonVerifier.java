package com.myrunning.leaderboard.validation;

/**
 *  File: PersonVerifier.java
 *  Author:  Joshua Forester
 *  Date: 2009/10/28
 *  Description:  Business validation object for Person. 
 **/


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.global.LocaleUtils;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimestampEditor;
import com.myrunning.leaderboard.validation.ErrorsUtils;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.PersonDao;



public class PersonVerifier implements Verifier {

    static Logger logger = Logger.getLogger(PersonVerifier.class);


    private PersonDao personDao;

    
    public void setPersonDao(PersonDao personDao) {
	this.personDao=personDao;
    }


    public PersonVerifier() {
	// empty
    }


    public void verify (HttpServletRequest req, Object command, BindException errors) {

	Person person = (Person) command;

	logger.debug(person.getEmail());


	Admin admin = (Admin) req.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    throw new IllegalStateException("verifyPerson call, admin should not be null");
	}

	// do business validation
	List<Person> existingPersons = (List<Person>) personDao.getPersonsByEmail(person.getEmail(), null, false);
	existingPersons = (List<Person>) DataAccessFilter.filterColById(existingPersons, admin.getId());
	if (!existingPersons.isEmpty()) {
	    errors.rejectValue("email", "errors.editperson.personalreadyexistswithemail", ErrorsUtils.createArray(existingPersons.get(0).getEmail(), existingPersons.get(0).getFirstName(), existingPersons.get(0).getLastName()), Constants.ERROR_CODE_NOT_FOUND);
	} else {
   
	    // validate person's identity is unique based on 
	    // name, locale, gender
	    existingPersons = (List<Person>) personDao.getPersonsByTraits(person, null, false);
	    existingPersons = (List<Person>) DataAccessFilter.filterColById(existingPersons, admin.getId());
	    if (!existingPersons.isEmpty()) {

		errors.reject("errors.editperson.personalreadyexistswithidentity", ErrorsUtils.createArray(existingPersons.get(0).getFirstName(), existingPersons.get(0).getLastName(), existingPersons.get(0).getCity(), existingPersons.get(0).getRegion(), existingPersons.get(0).getCountry(), String.valueOf(existingPersons.get(0).getZip()), existingPersons.get(0).getBirthday()), Constants.ERROR_CODE_NOT_FOUND);

	    }
	}
    }
	    

}
