package com.myrunning.leaderboard.web;

/**
 *  File: AddTeamFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Controller object for the team app.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.services.ifacesvc.TeamService;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.validation.TeamValidator;


@Controller
@RequestMapping("addTeam.htm")
public class AddTeamFormController extends TeamFormController {

    static Logger logger = Logger.getLogger(AddTeamFormController.class);

    private TeamService teamService;
    private TeamValidator teamValidator;

    @Autowired
    public AddTeamFormController(TeamService teamService,
				 @Qualifier("teamValidator") TeamValidator teamValidator) {
        this.teamService=teamService;
        this.teamValidator=teamValidator;
    }

    public AddTeamFormController() {
	// empty
    }

    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showTeamForm(HttpServletRequest request,
				     ModelMap model) {
	Team team = new Team();
	long course_id = ServletRequestUtils.getLongParameter(request, "course_id", 0);
	long division_id = ServletRequestUtils.getLongParameter(request, "division_id", 0);
	
	// filter out course_id and division_id if the person adding the team
	// doesn't have access to them.  
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    model.addAttribute("team", team);
	    return new ModelAndView(decorateView(getFormView(), request));
	}

	if (DataAccessFilter.filterDataResourceById(course_id, admin.getId()) == null) {
	    course_id = 0;
	}
	
	if (DataAccessFilter.filterDataResourceById(division_id, admin.getId()) == null) {
	    division_id = 0;
	}

	if (course_id != 0) {
	    team.setCourseId(course_id);
	}

	if (division_id != 0) {
	    team.setDivisionId(division_id);
	}
	
	model.addAttribute("team", team);
	return new ModelAndView(decorateView(getFormView(), request));
    }

	
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit (@ModelAttribute("team") Team team,
				  BindingResult result,
				  HttpServletRequest request)
	throws Exception {

	teamValidator.validate(team, result);
        if (result.hasErrors()) {
            return new ModelAndView(decorateView(getFormView(), request));
	}

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if ((DataAccessFilter.filterDataResourceById(team.getCourseId(), admin.getId()) == null) ||
	    (DataAccessFilter.filterDataResourceById(team.getDivisionId(), admin.getId()) == null)) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	teamService.addTeam(team, admin);
	
	return new ModelAndView(decorateView(getSuccessView(), request));

    }


}
