package com.myrunning.leaderboard.validation;

/**
 *  File: EditTeamValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Validator object for the edit team app.
 **/


import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Team;


public class EditTeamValidator extends TeamValidator implements Validator {

    static Logger logger = Logger.getLogger(EditTeamValidator.class);


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Team.class);
    }


    public void validate(Object obj, Errors errors) {
	Team team = (Team) obj;

	// validate id provided to edit method
	if (team.getId() == 0) {
	    errors.rejectValue("id", "errors.generic.requiredfield", ErrorsUtils.createArray("id"), Constants.ERROR_CODE_NOT_FOUND);
	}
	
	super.validateCommon(team, errors);
    }

}
