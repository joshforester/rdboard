package com.myrunning.leaderboard.web;

/**
 *  File: TeamFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Base Controller object for the team app.
 **/


import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import javax.servlet.http.HttpServletRequest;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.EventDivision;
import com.myrunning.leaderboard.model.EventCourse;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.db.dao.ifacedao.DivisionDao;




@Controller
public class TeamFormController extends NewLayoutController {

    static Logger logger = Logger.getLogger(TeamFormController.class);

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private DivisionDao divisionDao;


    public TeamFormController() {
	// empty
    }


    @ModelAttribute("eventCourses")
    public Collection<EventCourse> loadEventCourses(HttpServletRequest request) {
	
	List<EventCourse> eventCourses = new ArrayList<EventCourse>();

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return eventCourses;
	}

	eventCourses = courseDao.getEventCourses();
	eventCourses = (List<EventCourse>) DataAccessFilter.filterColById(eventCourses, admin.getId());

	return eventCourses;
    }


    @ModelAttribute("eventDivisions")
    public Collection<EventDivision> loadEventDivisions(HttpServletRequest request) {
	
	List<EventDivision> eventDivisions = new ArrayList<EventDivision>();

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return eventDivisions;
	}

	eventDivisions = divisionDao.getEventDivisions();
	eventDivisions = (List<EventDivision>) DataAccessFilter.filterColById(eventDivisions, admin.getId());

	return eventDivisions;
    }


}
