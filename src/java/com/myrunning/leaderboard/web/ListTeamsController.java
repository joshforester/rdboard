package com.myrunning.leaderboard.web;

/**
 *  File: ListTeamsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Controller object for listing Teams.
 **/


import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.model.Competitor;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CompetitorDao;


@Controller
public class ListTeamsController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListTeamsController.class);

    @Autowired
    private DataResourceDao dataResourceDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private CompetitorDao competitorDao;


    @RequestMapping(value = "/teams/{teamId}", method = RequestMethod.GET)
    public String getDivisionTeams(@PathVariable Long teamId,
				   HttpServletRequest request,
				   ModelMap model) {

	List<Team> teams = new ArrayList<Team>();
	teams.add(teamDao.getTeamById(teamId));
	model.addAttribute("teams", teams);

        setMultiViewRenderObject(model, "teams");

        return decorateView(".listTeams_view", request);
    }

    @RequestMapping(value = "/teams/{courseId}/{divisionId}", method = RequestMethod.GET)
    public String getDivisionTeams(@PathVariable Long courseId,
				   @PathVariable Long divisionId,
				   @RequestParam(value="order", required=false) String order,
				   HttpServletRequest request,
				   ModelMap model) {

	List<Team> teams = teamDao.getTeamsByCourseIdAndDivisionId(courseId, divisionId, order);
	model.addAttribute("teams", teams);
	model.addAttribute("courseId", courseId);
	model.addAttribute("divisionId", divisionId);

        setMultiViewRenderObject(model, "teams");

        return decorateView(".listTeams_view", request);
    }

    @RequestMapping(value = "/courses/{courseId}/teams", method = RequestMethod.GET)
    public String getCourseTeams(@PathVariable Long courseId,
				 @RequestParam(value="order", required=false) String order,
				 HttpServletRequest request,
				 ModelMap model) {

	List<Team> teams = teamDao.getTeamsByCourseId(courseId, order);
	model.addAttribute("teams", teams);
	model.addAttribute("courseId", courseId);

        setMultiViewRenderObject(model, "teams");

        return decorateView(".listTeams_view", request);
    }

    @RequestMapping(value = "/divisions/{divisionId}/teams", method = RequestMethod.GET)
    public String getDivisionTeams(@PathVariable Long divisionId,
				   @RequestParam(value="order", required=false) String order,
				   HttpServletRequest request,
				   ModelMap model) {

	List<Team> teams = teamDao.getTeamsByDivisionId(divisionId, order);
	model.addAttribute("teams", teams);
	model.addAttribute("divisionId", divisionId);

        setMultiViewRenderObject(model, "teams");

        return decorateView(".listTeams_view", request);
    }

    @RequestMapping(value="/deleteTeam.htm", method=RequestMethod.GET)
    public String deleteTeam(@RequestParam(value="team_id") Integer teamId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(teamId, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	Team team = teamDao.getTeamById(teamId);
	model.addAttribute("team", team);

	String view = ViewUtils.decideView(".deleteTeamConfirm_view", request);
	return view;
    }

    @RequestMapping(value="/deleteTeamConfirmed.htm", method=RequestMethod.POST)
    public String deleteTeamConfirmed(@ModelAttribute("team") Team team, BindingResult result, SessionStatus status, HttpServletRequest request) {

	// validate team
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(team, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	// validate competitors
	List<Competitor> competitors = competitorDao.getCompetitorsByTeamId(team.getId(), false);
	if (!DataAccessFilter.hasFullAccessTo(competitors, admin.getId())) {
	    return Constants.OWNERSHIP_ERROR;
	}
	    
	ListIterator<Competitor> cli = competitors.listIterator();
	Competitor competitor = null;
	while (cli.hasNext()) {
	    competitor = cli.next();
	    competitorDao.deleteCompetitorById(competitor.getId());
	    dataResourceDao.deleteDataResourceById(competitor.getId());
	}
	
	teamDao.deleteTeamById(team.getId());
	dataResourceDao.deleteDataResourceById(team.getId());
	
	String view = ViewUtils.decideView(".deleteTeamSuccess_dashboard", request);
	return view;
    }

}
