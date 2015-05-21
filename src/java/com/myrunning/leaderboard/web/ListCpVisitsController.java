package com.myrunning.leaderboard.web;

/**
 *  File: ListCpVisitsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/25
 *  Description: Controller object for listing CpVisits.
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
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.CpVisit;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.services.ifacesvc.CpVisitService;


@Controller
public class ListCpVisitsController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListCpVisitsController.class);

    private CpVisitService cpVisitService;
    private ControlPointDao controlPointDao;
    private CourseDao courseDao;


    @Autowired
    public ListCpVisitsController(CpVisitService cpVisitService,
				  ControlPointDao controlPointDao,
				  CourseDao courseDao) {
	this.cpVisitService=cpVisitService;
	this.controlPointDao=controlPointDao;
	this.courseDao=courseDao;
    }


    @RequestMapping(value = "/cps/{cpId}/cpvisits", method = RequestMethod.GET)
    public String getCpCpVisits(@PathVariable Long cpId,
				@RequestParam(value="order", required=false) String order,
				HttpServletRequest request,
				ModelMap model) {

	List<CpVisit> cpVisits = cpVisitService.getCpVisitsByCpId(cpId, order);
	model.addAttribute("cpVisits", cpVisits);
	ControlPoint controlPoint = controlPointDao.getControlPointById(cpId);
	if (controlPoint != null) {
	    model.addAttribute("courseId", controlPoint.getCourseId());
	    model.addAttribute("cpId", cpId);
	}
        setMultiViewRenderObject(model, "cpVisits");
	    
	return decorateView(".listCpVisits_view", request);
    }


    @RequestMapping(value = "/teams/{teamId}/cpvisits", method = RequestMethod.GET)
    public String getTeamCpVisits(@PathVariable Long teamId,
				  @RequestParam(value="order", required=false) String order,
				  HttpServletRequest request,
				  ModelMap model) {

	List<CpVisit> cpVisits = cpVisitService.getCpVisitsByTeamId(teamId, order);
	model.addAttribute("cpVisits", cpVisits);
	Course course = courseDao.getCourseByTeamId(teamId);
	if (course != null) {
	    model.addAttribute("courseId", course.getId());
	    model.addAttribute("teamId", teamId);
	}
        setMultiViewRenderObject(model, "cpVisits");
	
	return decorateView(".listCpVisits_view", request);
    }


    @RequestMapping(value = "/courses/{courseId}/cpvisits", method = RequestMethod.GET)
    public String getCourseCpVisits(@PathVariable Long courseId,
				    @RequestParam(value="order", required=false) String order,
				    HttpServletRequest request,
				    ModelMap model) {

	List<CpVisit> cpVisits = cpVisitService.getCpVisitsByCourseId(courseId, order);
	model.addAttribute("cpVisits", cpVisits);
	Course course = courseDao.getCourseById(courseId);
	if (course != null) {
	    model.addAttribute("courseId", course.getId());
	}
        setMultiViewRenderObject(model, "cpVisits");
	
	return decorateView(".listCpVisits_view", request);
    }


    @RequestMapping(value = "/cpvisits/{cpId}/{teamId}", method = RequestMethod.GET)
    public String getCpTeamCpVisits(@PathVariable Long cpId,
				    @PathVariable Long teamId,
				    @RequestParam(value="order", required=false) String order,
				    HttpServletRequest request,
				    ModelMap model) {

	// note that team_id will override cp_id	    
	CpVisit cpVisit = cpVisitService.getCpVisitByTeamIdAndCpId(teamId, cpId);
	List<CpVisit> cpVisits = new ArrayList<CpVisit>();
	cpVisits.add(cpVisit);
	model.addAttribute("cpId", cpId);
	model.addAttribute("teamId", teamId);
	model.addAttribute("cpVisits", cpVisits);
	ControlPoint controlPoint = controlPointDao.getControlPointById(cpId);
	if (controlPoint != null) {
	    model.addAttribute("courseId", controlPoint.getCourseId());
	}
        setMultiViewRenderObject(model, "cpVisits");
	
	return decorateView(".listCpVisits_view", request);
    }



    @RequestMapping(value="/deleteCpVisit.htm", method=RequestMethod.GET)
    public String deleteCpVisit(@RequestParam(value="team_id") Integer teamId, @RequestParam(value="cp_id") Integer cpId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	if ((DataAccessFilter.filterDataResourceById(teamId, admin.getId()) == null) ||
	    (DataAccessFilter.filterDataResourceById(cpId, admin.getId()) == null)) {
	    return Constants.OWNERSHIP_ERROR;
	}
	

	if ((teamId != null) && (cpId != null)) {
	    CpVisit cpVisit = cpVisitService.getCpVisitByTeamIdAndCpId(teamId, cpId);
	    model.addAttribute("cpVisit", cpVisit);
	}

	return decorateView(".deleteCpVisitConfirm_view", request);
    }

    @RequestMapping(value="/deleteCpVisitConfirmed.htm", method=RequestMethod.POST)
    public String deleteCpVisitConfirmed(@ModelAttribute("cpVisit") CpVisit cpVisit, BindingResult result, SessionStatus status, HttpServletRequest request) {

	// validate cpVisit
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	} 

	if ((DataAccessFilter.filterDataResourceById(cpVisit.getTeamId(), admin.getId()) == null) ||
	    (DataAccessFilter.filterDataResourceById(cpVisit.getCpId(), admin.getId()) == null)) {
	    return Constants.OWNERSHIP_ERROR;
	}

	cpVisitService.deleteCpVisitByTeamIdAndCpId(cpVisit.getTeamId(), cpVisit.getCpId());

	return decorateView(".deleteCpVisitSuccess_dashboard", request);
    }

}
