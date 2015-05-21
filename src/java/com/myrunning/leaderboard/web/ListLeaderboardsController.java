package com.myrunning.leaderboard.web;

/**
 *  File: ListLeaderboardsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/27
 *  Description: Controller object for listing Leaderboards.
 **/



import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
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
import com.myrunning.leaderboard.global.TimeConverter;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.Update;
import com.myrunning.leaderboard.model.Leaderboard;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.services.ifacesvc.CpVisitService;
import com.myrunning.leaderboard.services.ifacesvc.UpdateService;
import com.myrunning.leaderboard.services.ifacesvc.CourseService;


@Controller
public class ListLeaderboardsController {

    static Logger logger = Logger.getLogger(ListLeaderboardsController.class);

    @Autowired
    private EventDao eventDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CpVisitService cpVisitService;

    @Autowired
    private UpdateService updateService;



    @RequestMapping(value="/courses/{courseId}/leaderboards.htm", method = RequestMethod.GET)
    public String renderLeaderboards(@PathVariable Long courseId, 
				     @RequestParam(value="division_id", required=false) Integer divisionId, 
				     @RequestParam(value="style", required=false) String style, 
				     HttpServletRequest request, 
				     ModelMap model) {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Event event = null;
	Course course = null;
	String updateUrl = null;
	List<Leaderboard> leaderboards = new ArrayList<Leaderboard>();
	List<Update> allLbdUpdates = new ArrayList<Update>();

	Timestamp since = new Timestamp(Calendar.getInstance().getTimeInMillis() -
					Constants.UPDATES_LOOKBACK);
	if (courseId != null) {

	    event = eventDao.getEventByCourseId(courseId);
	    course = courseDao.getCourseById(courseId);

	    if (course != null) {
		Leaderboard leaderboard = 
		    courseService.generateLeaderboard(course, true, true, true, true, true);
		leaderboards.add(leaderboard);
		allLbdUpdates.addAll(updateService.getUpdatesByCourseId(course.getId(), since, "timestamp"));
		updateUrl = "/d/courses/" + course.getId() + "/lbdupdates";
	    } else {
		String view = ViewUtils.decideView(".noleaderboardstodisplay_dashboard", request);
		return view;	    
	    }

	    /*
	      Add filters for divisions and timestamp (simulate a certain time)
	     */

	    if (divisionId != null) {
		// filter on divisions
	    }

	} else {
	    String view = ViewUtils.decideView(".noleaderboardstodisplay_dashboard", request);
	    return view;	    
	}

	model.addAttribute("updateUrl", updateUrl);
	model.addAttribute("allLbdUpdates", allLbdUpdates);
	model.addAttribute("courseId", courseId);
	model.addAttribute("event", event);
	model.addAttribute("course", course);
	model.addAttribute("leaderboards", leaderboards);


	String view = null;
	if (style != null) {
	    if (style.equals("full")) {
		view = ViewUtils.decideView(".listLeaderboardsFull_dashboard", request);
	    } else if (style.equals("list")) {
		view = ViewUtils.decideView(".listLeaderboardsList_dashboard", request);
	    } else if (style.equals("short")) {
		view = ViewUtils.decideView(".listLeaderboardsShort_raw", request);
	    } else {
		view = ViewUtils.decideView(".listLeaderboardsFull_dashboard", request);
	    }
	} else {
	    view = ViewUtils.decideView(".listLeaderboardsFull_dashboard", request);
	}

	return view;
    }


/*
    @RequestMapping(value="/listLeaderboards.htm", method = RequestMethod.GET)
    public String renderLeaderboards(@RequestParam(value="event_id", required=false) Integer eventId, @RequestParam(value="course_id", required=false) Integer courseId, @RequestParam(value="division_id", required=false) Integer divisionId, @RequestParam(value="style", required=false) String style, HttpServletRequest request, ModelMap model) {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Event event = null;
	String updateUrl = null;
	List<Leaderboard> leaderboards = new ArrayList<Leaderboard>();
	List<Update> allLbdUpdates = new ArrayList<Update>();

	Timestamp since = new Timestamp(Calendar.getInstance().getTimeInMillis() -
					Constants.UPDATES_LOOKBACK);
	if (courseId != null) {

	    event = eventDao.getEventByCourseId(courseId);
	    Course course = courseDao.getCourseById(courseId);

	    if (course != null) {
		Leaderboard leaderboard = 
		    courseService.generateLeaderboard(course, true, true, true, true, true);
		leaderboards.add(leaderboard);
		allLbdUpdates.addAll(updateService.getUpdatesByCourseId(course.getId(), since, "timestamp"));
		updateUrl = "/d/courses/" + course.getId() + "/lbdupdates";
	    } else {
		String view = ViewUtils.decideView(".noleaderboardstodisplay_dashboard", request);
		return view;	    
	    }

	    //  Add filters for divisions and timestamp (simulate a certain time)

	    if (divisionId != null) {
		// filter on divisions
	    }

	} else if (eventId != null) {
	    
	    event = eventDao.getEventById(eventId);

	    if (event != null) {
		List<Course> courses = courseDao.getCoursesByEventId(eventId, "name");
		ListIterator<Course> cli = courses.listIterator();
		while (cli.hasNext()) {
		    Course course = cli.next();
		    Leaderboard leaderboard = courseService.generateLeaderboard(course, true, true, true, true, true);
		    leaderboards.add(leaderboard);
		}
		allLbdUpdates.addAll(updateService.getUpdatesByEventId(event.getId(), since, "timestamp"));
		updateUrl = "/d/events/" + event.getId() + "/lbdupdates";
	    } else {
		String view = ViewUtils.decideView(".noleaderboardstodisplay_dashboard", request);
		return view;
	    }	    

	    // Add filters for divisions and timestamp (simulate a certain time)

	    if (divisionId != null) {
		// filter on divisions
	    }
	} else {
	    String view = ViewUtils.decideView(".noleaderboardstodisplay_dashboard", request);
	    return view;	    
	}

	model.addAttribute("updateUrl", updateUrl);
	model.addAttribute("allLbdUpdates", allLbdUpdates);
	model.addAttribute("courseId", courseId);
	model.addAttribute("eventId", eventId);
	model.addAttribute("event", event);
	model.addAttribute("leaderboards", leaderboards);


	String view = null;
	if (style != null) {
	    if (style.equals("full")) {
		view = ViewUtils.decideView(".listLeaderboardsFull_dashboard", request);
	    } else if (style.equals("list")) {
		view = ViewUtils.decideView(".listLeaderboardsList_dashboard", request);
	    } else if (style.equals("short")) {
		view = ViewUtils.decideView(".listLeaderboardsShort_raw", request);
	    } else {
		view = ViewUtils.decideView(".listLeaderboardsFull_dashboard", request);
	    }
	} else {
	    view = ViewUtils.decideView(".listLeaderboardsFull_dashboard", request);
	}

	return view;
    }
*/

}
