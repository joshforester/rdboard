package com.myrunning.leaderboard.web;

/**
 *  File: CourseFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Base Controller object for the course app.
 **/


import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;



@Controller
public class CourseFormController extends NewLayoutController {

    static Logger logger = Logger.getLogger(CourseFormController.class);

    @Autowired
    private EventDao eventDao;


    public CourseFormController() {
	//empty
    }


    @ModelAttribute("events")
    public Collection<Event> loadEvents(HttpServletRequest request) {

	List<Event> events = new ArrayList<Event>();

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return events;
	}

	events = eventDao.getAll("name");
	events = (List<Event>) DataAccessFilter.filterColById(events, admin.getId());

	return events;
    }


    @ModelAttribute("types")
    public Collection<String> loadTypes(HttpServletRequest request) {
        List<String> types = new ArrayList<String>();

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
        if (admin == null) {
            return types;
        }

        return Course.getTypes();
    }


}
