package com.myrunning.leaderboard.web;

/**
 *  File: ComparatorFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/26
 *  Description: Controller object for the comparator app.
 **/


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.bind.ServletRequestUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Comparator;
import com.myrunning.leaderboard.model.EventCourse;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;



public class ComparatorFormController extends SimpleFormController {

    static Logger logger = Logger.getLogger(ComparatorFormController.class);

    private CourseDao courseDao;


    public ComparatorFormController() {
	// empty
    }


    public List<EventCourse> populateEventCourses() {

	logger.info("Entering populateEventCourses()");
	
	WebApplicationContext wac = getWebApplicationContext();
	courseDao = (CourseDao) wac.getBean("courseDao");
	List<EventCourse> eventCourses = courseDao.getEventCourses();
	
	logger.debug("EventCourses:  " + eventCourses);

	return eventCourses;
    }


    protected Map referenceData (HttpServletRequest request)
	throws Exception {
	
	Map data = new HashMap();
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return data;
	}

	List<EventCourse> eventCourses = populateEventCourses();
	eventCourses = (List<EventCourse>) DataAccessFilter.filterColById(eventCourses, admin.getId());

	data.put("eventCourses", eventCourses);
	data.put("tieActions", Comparator.getTieActions());
	data.put("types", Comparator.getTypes());

	return data;
    }


}
