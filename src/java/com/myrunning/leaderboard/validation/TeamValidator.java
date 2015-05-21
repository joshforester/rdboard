package com.myrunning.leaderboard.validation;

/**
 *  File: TeamValidator.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Validator object for the team app.
 **/


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;


public class TeamValidator extends WebApplicationObjectSupport implements Validator {

    static Logger logger = Logger.getLogger(TeamValidator.class);

    private TeamDao teamDao;


    public boolean supports(Class clazz) {
	return clazz.isAssignableFrom(Team.class);
    }


    protected void validateCommon(Team team, Errors errors) {
    
	// validate name
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.generic.requiredfield", ErrorsUtils.createArray("name"), Constants.ERROR_CODE_NOT_FOUND);

	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseId", "errors.generic.requiredfield", ErrorsUtils.createArray("courseId"), Constants.ERROR_CODE_NOT_FOUND);

	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "divisionId", "errors.generic.requiredfield", ErrorsUtils.createArray("divisionId"), Constants.ERROR_CODE_NOT_FOUND);


	// fix number if not provided
	WebApplicationContext wac = getWebApplicationContext();
	teamDao = (TeamDao) wac.getBean("teamDao");
	if (team.getNumber() == 0) {
	    int maxNumber = teamDao.getMaxTeamNumberByCourseId(team.getCourseId());
	    team.setNumber(maxNumber + 1);
	}

    }


    public void validate(Object obj, Errors errors) {

	//
	//  FOR NOW, JUST VALIDATE THE TEAM.  THE ACTUAL REGISTRATION OBJECT
	//  WILL HAVE VALIDATION THAT CROSSCUTS TEAM, COURSE, DIVISION, and COMPETITOR
	//  TABLES.
	//

	Team team = (Team) obj;

	validateCommon(team, errors);


	// do business validation
	WebApplicationContext wac = getWebApplicationContext();
	teamDao = (TeamDao) wac.getBean("teamDao");
	Team existingTeam = teamDao.getTeamByCourseIdAndName(team.getCourseId(), team.getName());
	if (existingTeam != null) {
	    errors.rejectValue("name", "errors.editteam.namealreadyexistsforcourse", ErrorsUtils.createArray(team.getName(), String.valueOf(team.getCourseId())), Constants.ERROR_CODE_NOT_FOUND);
	}

	Team existingTeam2 = teamDao.getTeamByDivisionIdAndName(team.getDivisionId(), team.getName());
	if (existingTeam2 != null) {
	    errors.rejectValue("name", "errors.editteam.namealreadyexistsfordivision", ErrorsUtils.createArray(team.getName(), String.valueOf(team.getDivisionId())), Constants.ERROR_CODE_NOT_FOUND);
	}
	
    }

}
