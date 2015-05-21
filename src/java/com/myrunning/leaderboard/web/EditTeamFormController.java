package com.myrunning.leaderboard.web;

/**
 *  File: EditTeamFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Controller object for the team app.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;
import com.myrunning.leaderboard.validation.EditTeamValidator;



@Controller
@RequestMapping("/editTeam.htm")
public class EditTeamFormController extends TeamFormController {

    static Logger logger = Logger.getLogger(EditTeamFormController.class);

    private TeamDao teamDao;
    private EditTeamValidator teamValidator;


    @Autowired
    public EditTeamFormController(TeamDao teamDao,
				  @Qualifier("editTeamValidator") EditTeamValidator editTeamValidator) {
	this.teamDao=teamDao;
	this.teamValidator=editTeamValidator;
    }

    

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showTeamForm (HttpServletRequest request,
				      ModelMap model) {
	Team team = new Team();
	long team_id = ServletRequestUtils.getLongParameter(request, "team_id", 0);

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    model.addAttribute("team", team);
	    return new ModelAndView(decorateView(getFormView(), request));
	}

	if (DataAccessFilter.filterDataResourceById(team_id, admin.getId()) == null) {
	    model.addAttribute("team", team);
	    return new ModelAndView(decorateView(getFormView(), request));
	} 
	
	if (team_id != 0) {
	    team = teamDao.getTeamById(team_id);
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

	if (DataAccessFilter.filterDataResourceById(team, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}
	
	teamDao.updateTeam(team);
	return new ModelAndView(decorateView(getSuccessView(), request));
    }


}
