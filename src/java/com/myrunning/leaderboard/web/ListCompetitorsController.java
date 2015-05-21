package com.myrunning.leaderboard.web;

/**
 *  File: ListCompetitorsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Controller object for listing Competitors.
 **/


import java.util.List;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.model.Competitor;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.IdentityDao;
import com.myrunning.leaderboard.db.dao.ifacedao.PersonDao;
import com.myrunning.leaderboard.db.dao.ifacedao.AdminDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CompetitorDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;


@Controller
public class ListCompetitorsController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListCompetitorsController.class);

    @Autowired
    private DataResourceDao dataResourceDao;

    @Autowired
    private IdentityDao identityDao;

    @Autowired 
    @Qualifier("personDao")
    private PersonDao personDao;

    @Autowired
    private CompetitorDao competitorDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private CourseDao courseDao;


    @RequestMapping(value="/competitors", method = RequestMethod.GET)
    public String getAllCompetitors(HttpServletRequest request, 
				    ModelMap model) {

	List<Competitor> competitors = competitorDao.getAll(false);
	model.addAttribute("competitors", competitors);
	setMultiViewRenderObject(model, "competitors");

	return decorateView(".listCompetitors_view", request);	
    }

    @RequestMapping(value="/competitors/{competitorId}", method = RequestMethod.GET)
    public String getCompetitor(@PathVariable Long competitorId,
				HttpServletRequest request, 
				ModelMap model) {

	List<Competitor> competitors = new ArrayList<Competitor>();
	competitors.add(competitorDao.getCompetitorById(competitorId));
	model.addAttribute("competitors", competitors);
	setMultiViewRenderObject(model, "competitors");

	return decorateView(".listCompetitors_view", request);	
    }

    @RequestMapping(value="/teams/{teamId}/competitors", method = RequestMethod.GET)
    public String getTeamCompetitors(@PathVariable Long teamId, 
				     HttpServletRequest request, 
				     ModelMap model) {

	List<Competitor> competitors = competitorDao.getCompetitorsByTeamId(teamId, false);
	model.addAttribute("competitors", competitors);
	setMultiViewRenderObject(model, "competitors");

	return decorateView(".listCompetitors_view", request);
    }


    /*
    //TODO:  implement getCompetitorByCourseId
    @RequestMapping(value = "/courses/{courseId}/competitors", method = RequestMethod.GET)
    public String getCourseCompetitors(@PathVariable Long courseId,
				       HttpServletRequest request, 
				       ModelMap model) {
	List<Competitor> competitors = competitorDao.getCompetitorsByCourseId(courseId, false);
	model.addAttribute("competitors", competitors);
	setMultiViewRenderObject(model, "competitors");

	return decorateView(".listCompetitors_view", request);
    }
    */



    @RequestMapping(value = "/courses/{courseId}/competitors/map.htm", method = RequestMethod.GET)
    public String getDescription(@PathVariable Long courseId,
				 HttpServletRequest request, 
				 ModelMap model) {

	Course course = courseDao.getCourseById(courseId);
	model.addAttribute("course", course);

	model.addAttribute("courseId", courseId);
	setMultiViewRenderObject(model, "courseId");
	return decorateView(".showCompetitorMap_dashboard", request);
    }

    @RequestMapping(value="/deleteCompetitor.htm", method=RequestMethod.GET)
    public String deleteCompetitor(@RequestParam(value="competitor_id") Integer competitorId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	if (DataAccessFilter.filterDataResourceById(competitorId, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (competitorId != null) {
	    Competitor competitor = competitorDao.getCompetitorById(competitorId);
	    model.addAttribute("competitor", competitor);
	}

	return decorateView(".deleteCompetitorConfirm_view", request);
    }

    @RequestMapping(value="/deleteCompetitorConfirmed.htm", method=RequestMethod.POST)
    public String deleteCompetitorConfirmed(@ModelAttribute("competitor") Competitor competitor, BindingResult result, SessionStatus status, HttpServletRequest request) {

	// validate competitor
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(competitor, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	// get full competitor object
	competitor = competitorDao.getCompetitorById(competitor.getId());

	// deletes competitor and associated DATA_RESOURCE
	competitorDao.deleteCompetitorById(competitor.getId());
	dataResourceDao.deleteDataResourceById(competitor.getId());

	// remove underlying PERSON/DATA_RESOURCE if no 
	// admin/competitors associated with it
	if ((adminDao.getAdminById(competitor.getPersonId()) == null) &&
	    (competitorDao.getCompetitorsByPersonId(competitor.getPersonId(), false).isEmpty())) {

	    logger.debug("no admins associated, deleting person " + competitor.getPersonId());

	    Person person = personDao.getPersonById(competitor.getPersonId());

	    personDao.deletePersonById(person.getId());
	    dataResourceDao.deleteDataResourceById(person.getId());

	    // remove underlying IDENTITY/DATA_RESOURCE if no persons associated with it
	    if (personDao.getPersonsByIdentityId(person.getIdentityId(), null, false).isEmpty()) {
		
		logger.debug("no identities associated, deleting identity " + person.getIdentityId());
		
		identityDao.deleteIdentityById(person.getIdentityId());
		dataResourceDao.deleteDataResourceById(person.getIdentityId());

	    } else {
		logger.debug("persons associated with identity found.  not removing identity" + person.getIdentityId());
	    }

	} else {
	    logger.debug("competitors/admin associated with person found.  not removing person." + competitor.getPersonId());
	}

	return decorateView(".deleteCompetitorSuccess_dashboard", request);
    }

}
