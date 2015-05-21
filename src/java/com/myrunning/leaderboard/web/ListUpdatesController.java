package com.myrunning.leaderboard.web;

/**
 *  File: ListUpdatesController.java
 *  Author:  Joshua Forester
 *  Date: 2010/01/04
 *  Description: Controller object for listing Updates.
 **/


import java.sql.Timestamp;
import java.util.Calendar;
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
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.Update;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.services.ifacesvc.UpdateService;


@Controller
public class ListUpdatesController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListUpdatesController.class);

    @Autowired
    private UpdateService updateService;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private CourseDao courseDao;

    @RequestMapping(value = "/courses/{courseId}/lbdupdates", method = RequestMethod.GET)
    public String getCourseLeaderboardUpdates(@PathVariable Long courseId,
					      @RequestParam(value="lookback", required=false) Integer lookback,
					      HttpServletRequest request, 
					      ModelMap model) {
	String updateUrl = "/d/courses/" + courseId + "/lbdupdates";

	Timestamp since = null;
	if (lookback != null) {
	    since = new Timestamp(Calendar.getInstance().getTimeInMillis() -
				  lookback);
	} else {
	    since = new Timestamp(Calendar.getInstance().getTimeInMillis() -
				  Constants.UPDATES_DEFAULT_LOOKBACK);
	}

	Event event = eventDao.getEventByCourseId(courseId);
	model.addAttribute("event", event);

	List<Update> allLbdUpdates = updateService.getUpdatesByCourseId(courseId, since, "timestamp");
	model.addAttribute("updateUrl", updateUrl);
	model.addAttribute("allLbdUpdates", allLbdUpdates);
	model.addAttribute("courseId", courseId);

	setMultiViewRenderObject(model, "allLbdUpdates");
	
	return decorateView(".listLbdUpdates_view", request);
    }


    @RequestMapping(value = "/events/{eventId}/lbdupdates", method = RequestMethod.GET)
    public String getEventLeaderboardUpdates(@PathVariable Long eventId,
					     @RequestParam(value="lookback", required=false) Integer lookback,
					     HttpServletRequest request, 
					     ModelMap model) {
	String updateUrl = "/d/events/" + eventId + "/lbdupdates";

	Timestamp since = null;
	if (lookback != null) {
	    since = new Timestamp(Calendar.getInstance().getTimeInMillis() -
				  lookback);
	} else {
	    since = new Timestamp(Calendar.getInstance().getTimeInMillis() -
				  Constants.UPDATES_DEFAULT_LOOKBACK);
	}

	Event event = eventDao.getEventById(eventId);
	model.addAttribute("event", event);

	List<Update> allLbdUpdates = updateService.getUpdatesByEventId(eventId, since, "timestamp");
	model.addAttribute("updateUrl", updateUrl);
	model.addAttribute("allLbdUpdates", allLbdUpdates);
	setMultiViewRenderObject(model, "allLbdUpdates");
	
	return decorateView(".listLbdUpdates_view", request);
    }


    @RequestMapping(value = "/courses/{courseId}/updates.htm", method = RequestMethod.GET)
    public String getCourseUpdates(@PathVariable Long courseId,
				   @RequestParam(value="lookback", required=false) Integer lookback,
				   HttpServletRequest request, 
				   ModelMap model) {

	Event event = eventDao.getEventByCourseId(courseId);
	model.addAttribute("event", event);

	Course course = courseDao.getCourseById(courseId);

	model.addAttribute("course", course);
	model.addAttribute("courseId", courseId);

	return decorateView(".listUpdates_view", request);
    }


}
