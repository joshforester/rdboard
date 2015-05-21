package com.myrunning.leaderboard.web;

/**
 *  File: CpVisitBatchFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/09/18
 *  Description: Controller object for editing cpVisits per cp.
 **/


import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Collections;
import java.util.Collection;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimestampEditor;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimePeriodEditor;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.services.ifacesvc.CpVisitService;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.DataResource;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.CpVisit;
import com.myrunning.leaderboard.model.CpVisitBatch;
import com.myrunning.leaderboard.model.util.CpVisitTeamNumberComparator;
import com.myrunning.leaderboard.model.util.CpVisitControlPointCpOrderComparator;
import com.myrunning.leaderboard.validation.CpVisitBatchValidator;


@Controller
@RequestMapping("/editCpVisitBatch.htm")
public class CpVisitBatchFormController extends NewLayoutController {

    static Logger logger = Logger.getLogger(CpVisitBatchFormController.class);

    private TeamDao teamDao;
    private ControlPointDao controlPointDao;
    private CourseDao courseDao;
    private CpVisitService cpVisitService;
    private CpVisitBatchValidator cpVisitBatchValidator;


    @Autowired
    public CpVisitBatchFormController(TeamDao teamDao,
				      ControlPointDao controlPointDao,
				      CpVisitService cpVisitService,
				      CourseDao courseDao,
				      @Qualifier("cpVisitBatchValidator") CpVisitBatchValidator cpVisitBatchValidator) {
	this.teamDao=teamDao;
	this.controlPointDao=controlPointDao;
	this.cpVisitService=cpVisitService;
	this.courseDao=courseDao;
	this.cpVisitBatchValidator=cpVisitBatchValidator;
    }


    private long getAccessibleCpIdFromParameters(HttpServletRequest request) {

	long cpId = ServletRequestUtils.getLongParameter(request, "cp_id", 0);

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
        if (admin == null) {
            return 0;
        }

	if (DataAccessFilter.filterDataResourceById(cpId, admin.getId()) == null) {
	    cpId = 0;
	}

	return cpId;
    }


    private long getAccessibleTeamIdFromParameters(HttpServletRequest request) {

	long teamId = ServletRequestUtils.getLongParameter(request, "team_id", 0);

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
        if (admin == null) {
            return 0;
        }

	if (DataAccessFilter.filterDataResourceById(teamId, admin.getId()) == null) {
	    teamId = 0;
	}

	return teamId;
    }


    private Course getAccessibleCourseFromParameters(HttpServletRequest request) {

	Course course = null;
	long cpId = getAccessibleCpIdFromParameters(request);
	long teamId = getAccessibleTeamIdFromParameters(request);

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);

	// not cp_id overrides team_id
	if (cpId != 0) {
	    course = courseDao.getCourseByCpId(cpId);
       	} else if (teamId != 0) {
	    course = courseDao.getCourseByTeamId(teamId);
	}

	// verify course ownership
	if (DataAccessFilter.filterDataResourceById(course, admin.getId()) == null) {
	    return null;
	}

	return course;
    }

    
    @InitBinder
    protected void initBinder(WebDataBinder binder)
	throws Exception {
	SimpleDateFormat tsFormat = new SimpleDateFormat(Constants.TIMESTAMP_FORMAT);
	CustomTimestampEditor tsEditor = new CustomTimestampEditor(tsFormat, true, 16);
	SimpleDateFormat timePeriodFormat = new SimpleDateFormat(Constants.TIME_PERIOD_FORMAT);
	CustomTimePeriodEditor tpEditor = new CustomTimePeriodEditor(timePeriodFormat, true, 5);
	binder.registerCustomEditor(Timestamp.class, "batch.arrival", tsEditor);
	binder.registerCustomEditor(Timestamp.class, "batch.departure", tsEditor);
	binder.registerCustomEditor(Timestamp.class, "batch.timeBonusAssessed", tpEditor);
	binder.registerCustomEditor(Timestamp.class, "batch.timePenaltyAssessed", tpEditor);
    }


    @ModelAttribute("cpId")
    public long loadCpId(HttpServletRequest request) {
	return getAccessibleCpIdFromParameters(request);
    }


    @ModelAttribute("teamId")
    public long loadTeamId(HttpServletRequest request) {
	return getAccessibleTeamIdFromParameters(request);
    }


    @ModelAttribute("teams")
    public Collection<Team> loadTeams(HttpServletRequest request) {

	List<Team> teams = new ArrayList<Team>();

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);

	Course course = getAccessibleCourseFromParameters(request);
        if (course == null) {
            return teams;
        }

        teams = teamDao.getTeamsByCourseId(course.getId(), "number");
	teams = (List<Team>) DataAccessFilter.filterColById(teams, admin.getId());
	
	return teams;
    }


    @ModelAttribute("controlPoints")
    public Collection<ControlPoint> loadControlPoints(HttpServletRequest request) {

	List<ControlPoint> controlPoints = new ArrayList<ControlPoint>();

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	Course course = getAccessibleCourseFromParameters(request);
	if (course == null) {
	    return controlPoints;
	}

        controlPoints = controlPointDao.getControlPointsByCourseId(course.getId(), "number");
	controlPoints = (List<ControlPoint>) DataAccessFilter.filterColById(controlPoints, admin.getId());
	
	return controlPoints;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showCpVisitBatchForm(HttpServletRequest request,
					     ModelMap model) {
	
	CpVisitBatch batch = new CpVisitBatch();

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);

	long cpId = getAccessibleCpIdFromParameters(request);
	long teamId = getAccessibleTeamIdFromParameters(request);
	
	if (cpId != 0) {
	    
	    List<CpVisit> cpVisits = cpVisitService.getCpVisitsByCpId(cpId, "departure");
	    Course course = getAccessibleCourseFromParameters(request);
	    
	    // verify course ownership
	    if (course == null) {
		model.addAttribute("cpVisitBatch", batch);
		return new ModelAndView(decorateView(getFormView(), request));
	    }
	    
	    List<Team> teams = teamDao.getTeamsByCourseId(course.getId(), "number");
	    teams = (List<Team>) DataAccessFilter.filterColById(teams, admin.getId());
	    
	    // for each team, if they don't exist in cpVisits, create empty
	    // object and add to list
	    ListIterator<Team> tli = teams.listIterator();
	    Team team = null;
	    while (tli.hasNext()) {
		team = tli.next();
		
		// search for entry in cpVisits
		ListIterator<CpVisit> cpvli = cpVisits.listIterator();
		CpVisit cpVisit = null;
		boolean isFound = false;
		while ((cpvli.hasNext()) && (!isFound)) {
		    cpVisit = cpvli.next();
		    
		    if (team.getId() == cpVisit.getTeamId()) {
			isFound = true;
		    }
		}
		
		// if not already in the list, add it
		if (!isFound) {
		    cpVisit = new CpVisit();
		    cpVisit.setTeamId(team.getId());
		    cpVisit.setCpId(cpId);
		    cpVisit.setDefault();
		    
		    cpVisits.add(cpVisit);
		}
		
	    }
	    
	    // sort cpVisits based on team number
	    //
	    // could be name as well if specified
	    //
	    int teamLoadFactor = teams.size() + (teams.size() / 3);
	    Hashtable<Long, Team> teamTable = new Hashtable<Long, Team>(teamLoadFactor);
	    tli = teams.listIterator();
	    while (tli.hasNext()) {
		team = tli.next();
		teamTable.put(team.getId(), team);
	    }
	    
	    Collections.sort(cpVisits, new CpVisitTeamNumberComparator(teamTable));
	    
	    
	    batch.setBatch(cpVisits);
	    
	} else if (teamId != 0) {
	    
	    List<CpVisit> cpVisits = cpVisitService.getCpVisitsByTeamId(teamId, "departure");
	    Course course = getAccessibleCourseFromParameters(request);
	    
	    // verify course ownership
	    if (course == null) {
		model.addAttribute("cpVisitBatch", batch);
		return new ModelAndView(decorateView(getFormView(), request));
	    }
	    
	    List<ControlPoint> controlPoints = controlPointDao.getControlPointsByCourseId(course.getId(), "number");
	    controlPoints = (List<ControlPoint>) DataAccessFilter.filterColById(controlPoints, admin.getId());
	    
	    // for each control point, if they don't exist in cpVisits, 
	    // create empty object and add to list
	    ListIterator<ControlPoint> cpli = controlPoints.listIterator();
	    ControlPoint controlPoint = null;
	    while (cpli.hasNext()) {
		controlPoint = cpli.next();
		
		// search for entry in cpVisits
		ListIterator<CpVisit> cpvli = cpVisits.listIterator();
		CpVisit cpVisit = null;
		boolean isFound = false;
		while ((cpvli.hasNext()) && (!isFound)) {
		    cpVisit = cpvli.next();
		    
		    if (controlPoint.getId() == cpVisit.getCpId()) {
			isFound = true;
		    }
		}
		
		// if not already in the list, add it
		if (!isFound) {
		    cpVisit = new CpVisit();
		    cpVisit.setTeamId(teamId);
		    cpVisit.setCpId(controlPoint.getId());
		    cpVisit.setDefault();
		    
		    cpVisits.add(cpVisit);
		}
		
	    }
	    
	    // sort cpVisits based on cpOrder
	    int cpLoadFactor = controlPoints.size() + (controlPoints.size() / 3);
	    Hashtable<Long, ControlPoint> cpTable = new Hashtable<Long, ControlPoint>(cpLoadFactor);
	    cpli = controlPoints.listIterator();
	    while (cpli.hasNext()) {
		controlPoint = cpli.next();
		cpTable.put(controlPoint.getId(), controlPoint);
	    }
	    
	    Collections.sort(cpVisits, new CpVisitControlPointCpOrderComparator(cpTable));
	    
	    batch.setBatch(cpVisits);
	    
	}
	
	model.addAttribute("cpVisitBatch", batch);
	return new ModelAndView(decorateView(getFormView(), request));
    }
    
    

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit (@ModelAttribute("cpVisitBatch") CpVisitBatch batch,
				  BindingResult result,
				  HttpServletRequest request,
				  ModelMap model)
	throws Exception {
	
	cpVisitBatchValidator.validate(batch, result);
	if (result.hasErrors()) {
	    return new ModelAndView(decorateView(getFormView(), request));
	}
	
	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	// validate admin
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	// to validate ownership of all cps in the batch, we'll
	// build a list and then verify access to all rather than
	// validating each one individually.  this saves us many calls
	// to the DB
	// 
	// additionally, we'll save time and determine if this is a 
	// editBatchByCpId or editBatchByTeamId for the successView
	ListIterator<CpVisit> cvli = batch.getBatch().listIterator();
	Collection<DataResource> col = new ArrayList<DataResource>();
	long previousTeamId = 0;
	long previousCpId = 0;
	boolean first = true;
	boolean editBatchByTeamId = true;
	boolean editBatchByCpId = true;
	DataResource dr = null;
	while (cvli.hasNext()) {
	    CpVisit cpVisit = cvli.next();
	    
	    if (first) {
		previousTeamId = cpVisit.getTeamId();
		previousCpId = cpVisit.getCpId();
		first = false;
	    } else {
		if (cpVisit.getTeamId() != previousTeamId) {
		    editBatchByTeamId = false;
		}
		if (cpVisit.getCpId() != previousCpId) {
		    editBatchByCpId = false;
		}
	    }

	    // filter out default visits, as we don't want to save them
	    if (!cpVisit.isDefaulted()) {
		dr = new DataResource();
		dr.setId(cpVisit.getTeamId());
		col.add(dr);
		dr = new DataResource();
		dr.setId(cpVisit.getCpId());
		col.add(dr);
	    }
	}

	if (!DataAccessFilter.hasFullAccessTo(col, admin.getId())) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	
	// validation of ownership is done, proceed.
	List<CpVisit> cpVisits = cpVisitService.saveCpVisitBatch(batch.getBatch());
	model.addAttribute("cpVisits", cpVisits);
	if (editBatchByTeamId) {
	    model.addAttribute("teamId", previousTeamId);
	}
	if (editBatchByCpId) {
	    model.addAttribute("cpId", previousCpId);
	}

	Course course = courseDao.getCourseByCpId(previousCpId);
	model.addAttribute("course", course);

	return new ModelAndView(decorateView(getSuccessView(), request));
    }


}
