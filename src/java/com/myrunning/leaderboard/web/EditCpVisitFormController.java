package com.myrunning.leaderboard.web;

/**
 *  File: EditCpVisitFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/25
 *  Description: Controller object for the cpVisit app.
 **/


import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.model.CpVisit;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.services.ifacesvc.CpVisitService;
import com.myrunning.leaderboard.validation.EditCpVisitValidator;


@Controller
public class EditCpVisitFormController extends CpVisitFormController {

    static Logger logger = Logger.getLogger(EditCpVisitFormController.class);

    private CpVisitService cpVisitService;
    private ControlPointDao controlPointDao;
    private CourseDao courseDao;
    private EditCpVisitValidator cpVisitValidator;


    @Autowired
    public EditCpVisitFormController(CpVisitService cpVisitService,
				     ControlPointDao controlPointDao,
				     CourseDao courseDao,
				     @Qualifier("editCpVisitValidator") EditCpVisitValidator editCpVisitValidator) {
	this.cpVisitService=cpVisitService;
	this.controlPointDao=controlPointDao;
	this.courseDao=courseDao;
	this.cpVisitValidator=editCpVisitValidator;
    }


    /**
     *  Determines if the request has provided team_id and cp_id parameters
     *  and that the admin has access to them and the course to which they
     *  are associated.
     **/
    protected boolean providedAccessibleCpTeamIds(HttpServletRequest request) {

	long teamId = ServletRequestUtils.getLongParameter(request, "team_id", 0);
	long cpId = ServletRequestUtils.getLongParameter(request, "cp_id", 0);
	
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return false;
	}

	if (DataAccessFilter.filterDataResourceById(teamId, admin.getId()) == null) {
	    return false;
	}

	if (DataAccessFilter.filterDataResourceById(cpId, admin.getId()) == null) {
	    return false;
	}

	// a provided cp_id/team_id overrides the provided course_id
	if ((teamId != 0) && (cpId != 0)) {
	    Course course = courseDao.getCourseByTeamId(teamId);
	    course = (Course) DataAccessFilter.filterDataResourceById(course, admin.getId());
	    Course course2 = courseDao.getCourseByCpId(cpId);
	    course2 = (Course) DataAccessFilter.filterDataResourceById(course2, admin.getId());
	    if ((course == null) || (course2 == null)) {
		return false;
	    }
	}

	return true;
    }


    @ModelAttribute("teamId")
    protected long loadTeamId (HttpServletRequest request)
	throws Exception {

	if (providedAccessibleCpTeamIds(request)) {
	    return ServletRequestUtils.getLongParameter(request, "team_id", 0);
	} else {
	    return 0;
	}
    }


    @ModelAttribute("cpId")
    protected long loadCpId (HttpServletRequest request)
	throws Exception {

	if (providedAccessibleCpTeamIds(request)) {
	    return ServletRequestUtils.getLongParameter(request, "cp_id", 0);
	} else {
	    return 0;
	}
    }


    @RequestMapping(value = "/editCpVisit.htm", method = RequestMethod.GET)
    public ModelAndView showCpVisitForm (@RequestParam(value="team_id", required=false, defaultValue="0") long teamId,
					 @RequestParam(value="cp_id", required=false, defaultValue="0") long cpId,
					 HttpServletRequest request,
					 ModelMap model) {
	CpVisit cpVisit = new CpVisit();

	// verify admin has access to both team and cp
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    model.addAttribute("cpVisit", cpVisit);
	    return new ModelAndView(decorateView(getFormView(), request));
	}

	
	if ((DataAccessFilter.filterDataResourceById(teamId, admin.getId()) == null) ||
	    (DataAccessFilter.filterDataResourceById(cpId, admin.getId()) == null)) {
	    model.addAttribute("cpVisit", cpVisit);
	    return new ModelAndView(decorateView(getFormView(), request));
	}

	if ((teamId != 0) && (cpId != 0)) {
	    cpVisit = cpVisitService.getCpVisitByTeamIdAndCpId(teamId, cpId);
	}

	model.addAttribute("cpVisit", cpVisit);
	return new ModelAndView(decorateView(getFormView(), request));
    }
    
    

    @RequestMapping(value = "/editCpVisit.htm", method = RequestMethod.POST)
    public ModelAndView onSubmit (@ModelAttribute("cpVisit") CpVisit cpVisit,
				  BindingResult result,
				  HttpServletRequest request,
				  ModelMap model)
	throws Exception {

	cpVisitValidator.validate(cpVisit, result);
        if (result.hasErrors()) {
            return new ModelAndView(decorateView(getFormView(), request));
        }

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	
	if ((DataAccessFilter.filterDataResourceById(cpVisit.getTeamId(), admin.getId()) == null) ||
	    (DataAccessFilter.filterDataResourceById(cpVisit.getCpId(), admin.getId()) == null)) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}
	
	cpVisitService.saveCpVisit(cpVisit);

	// populate model so we can return to leaderboard and display edited cpVisits
	List<CpVisit> cpVisits = new ArrayList<CpVisit>();
	cpVisits.add(cpVisit);
	model.addAttribute("cpVisits", cpVisits);
	model.addAttribute("cpId", cpVisit.getCpId());
	model.addAttribute("teamId", cpVisit.getTeamId());

	Course course = courseDao.getCourseByCpId(cpVisit.getCpId());
	model.addAttribute("course", course);

	return new ModelAndView(decorateView(getSuccessView(), request));
    }


    /*
     * Mobile app save point.
     * difference being that it is a REST-based service, and would and would need
     * to respond HTTP faults upon error and HTTP response upon success.
     *
     * Using the annotation parameter:
     * @RequestBody CpVisit cpVisit,
     *  causes an HttpMediaTypeNotSupportedException:
     *  http://jira.springframework.org/browse/SPR-6729?page=com.atlassian.jira.plugin.system.issuetabpanels%3Achangehistory-tabpanel
     * fix available in   3.1 M1 [ 11378 ]
     *
     * need to actually include faults on error.
     * see: org.springframework.http.HttpStatus
     * and:
     * http://pastebin.com/isPmd7SB
     */
    @RequestMapping(value = "/cpvisits/{cpId}/{teamId}/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String onMobileSubmit (@PathVariable Long cpId,
				  @PathVariable Long teamId,
				  @ModelAttribute("cpVisit") CpVisit cpVisit,
				  BindingResult result,
				  HttpServletRequest request,
				  HttpServletResponse response,
				  ModelMap model) 
	throws Exception {
	
	logger.debug("submitted CpVisit:  " + cpVisit);

	String newLocation = determineLocationUri(request, "/cpvisits/" + cpVisit.getCpId() + "/" + cpVisit.getTeamId());
	response.addHeader("Location", newLocation);

	//return new ModelAndView("cpVisit").addObject(cpVisit);

	cpVisitValidator.validate(cpVisit, result);
        if (result.hasErrors()) {
	    logger.debug("mobile cpVisit submission has validation errors.");
	    return ".releasenotes_raw";
	    //return new ModelAndView(decorateView(getFormView(), request));
        }

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	
	if ((DataAccessFilter.filterDataResourceById(cpVisit.getTeamId(), admin.getId()) == null) ||
	    (DataAccessFilter.filterDataResourceById(cpVisit.getCpId(), admin.getId()) == null)) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	logger.debug("saving cpvisit: " + cpVisit);
	//cpVisitService.saveCpVisit(cpVisit);

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

    
    private String determineLocationUri(HttpServletRequest request, String newPathInfo) {
	StringBuffer url = request.getRequestURL();
	url.replace(url.indexOf(request.getPathInfo()), url.length(), newPathInfo);
	return url.toString();
    }



}
