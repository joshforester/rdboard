package com.myrunning.leaderboard.web;

/**
 *  File: CpVisitFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/25
 *  Description: Controller object for the cpVisit app.
 **/


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimestampEditor;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimePeriodEditor;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;


@Controller
public class CpVisitFormController extends NewLayoutController {

    static Logger logger = Logger.getLogger(CpVisitFormController.class);

    @Autowired
    private ControlPointDao controlPointDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private TeamDao teamDao;


    public CpVisitFormController() {
	// empty
    }


    private long getCourseId(HttpServletRequest request) {

	long courseId = ServletRequestUtils.getLongParameter(request, "course_id", 0);
	long cpId = ServletRequestUtils.getLongParameter(request, "cp_id", 0);
	long teamId = ServletRequestUtils.getLongParameter(request, "team_id", 0);

	// filter out course_id, cp_id, and team_id if the person adding 
	// the cpVisit doesn't have access to them.  
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return 0;
	}

	// disregard if the admin has no access to them
	if (DataAccessFilter.filterDataResourceById(courseId, admin.getId()) == null) {
	    courseId = 0;
	}
	
	if (DataAccessFilter.filterDataResourceById(cpId, admin.getId()) == null) {
	    cpId = 0;
	}
	
	if (DataAccessFilter.filterDataResourceById(teamId, admin.getId()) == null) {
	    teamId = 0;
	}

	// a provided cp_id/team_id overrides the provided course_id
	if (cpId != 0) {
	    ControlPoint controlPoint = controlPointDao.getControlPointById(cpId);
	    if (controlPoint != null) {
		courseId = controlPoint.getCourseId();
	    }
	} else if (teamId != 0) {
	    Course course = courseDao.getCourseByTeamId(teamId);
	    if (course != null) {
		courseId = course.getId();
	    }
	}

	return courseId;
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder)
        throws Exception {
	SimpleDateFormat tsFormat = new SimpleDateFormat(Constants.TIMESTAMP_FORMAT);
	CustomTimestampEditor tsEditor = new CustomTimestampEditor(tsFormat, true, 16);
	SimpleDateFormat timePeriodFormat = new SimpleDateFormat(Constants.TIME_PERIOD_FORMAT);
	CustomTimePeriodEditor tpEditor = new CustomTimePeriodEditor(timePeriodFormat, true, 5);
	binder.registerCustomEditor(Timestamp.class, "arrival", tsEditor);
	binder.registerCustomEditor(Timestamp.class, "departure", tsEditor);
	binder.registerCustomEditor(Timestamp.class, "clientTime", tsEditor);
	binder.registerCustomEditor(Timestamp.class, "timeBonusAssessed", tpEditor);
	binder.registerCustomEditor(Timestamp.class, "timePenaltyAssessed", tpEditor);
    }

    
    @ModelAttribute("courseId")
    public long loadCourseId(HttpServletRequest request) {
	return getCourseId(request);
    }


    @ModelAttribute("teams")
    public Collection<Team> loadTeams(HttpServletRequest request) {
	
	Collection<Team> teams = new ArrayList<Team>();

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return teams;
	}

	long courseId = getCourseId(request);

	if (courseId == 0) {
	    return teams;
	}

	teams = teamDao.getTeamsByCourseId(courseId, "number");
	teams = (List<Team>) DataAccessFilter.filterColById(teams, admin.getId());
	
	return teams;
    }


    @ModelAttribute("controlPoints")
    public Collection<ControlPoint> loadControlPoints(HttpServletRequest request) {

	Collection<ControlPoint> controlPoints = new ArrayList<ControlPoint>();

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return controlPoints;
	}

	long courseId = getCourseId(request);

	if (courseId == 0) {
	    return controlPoints;
	}	

	controlPoints = controlPointDao.getControlPointsByCourseId(courseId, "order");
	controlPoints = (List<ControlPoint>) DataAccessFilter.filterColById(controlPoints, admin.getId());

	return controlPoints;
    }


}
