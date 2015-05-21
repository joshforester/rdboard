package com.myrunning.leaderboard.web;

/**
 *  File: ControlPointFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Controller object for the controlPoint app.
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.EventCourse;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimestampEditor;



@Controller
public class ControlPointFormController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ControlPointFormController.class);

    @Autowired
    private CourseDao courseDao;


    public ControlPointFormController() {
	// empty
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder)
        throws Exception {
	SimpleDateFormat timestampFormat = new SimpleDateFormat(Constants.TIMESTAMP_FORMAT);
	CustomTimestampEditor tsEditor = new CustomTimestampEditor(timestampFormat, true, 16);
	binder.registerCustomEditor(Timestamp.class, null, tsEditor);
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


    @ModelAttribute("disciplines")
    public Collection<String> loadDisciplines(HttpServletRequest request) {
        List<String> disciplines = new ArrayList<String>();

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
        if (admin == null) {
            return disciplines;
        }

        return ControlPoint.getDisciplines();
    }


    @ModelAttribute("zoneChars")
    public Collection<String> loadZoneChars(HttpServletRequest request) {
        List<String> zoneChars = new ArrayList<String>();

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
        if (admin == null) {
            return zoneChars;
        }

        return ControlPoint.getZoneChars();
    }

}
