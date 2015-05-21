package com.myrunning.leaderboard.web;

/**
 *  File: ListEventsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Controller object for listing Events.
 **/


import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
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
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;


@Controller
public class ListEventsController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListEventsController.class);

    @Autowired
    private DataResourceDao dataResourceDao;

    @Autowired
    private EventDao eventDao;


    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String getAllEvents(@RequestParam(value="order", required=false) String order,
			       HttpServletRequest request, 
			       ModelMap model) {

	logger.debug("entering Restful getAllEvents()");

	List<Event> events = eventDao.getAll(order);
	model.addAttribute("events", events);
	setMultiViewRenderObject(model, "events");

        return decorateView(".listEvents_view", request);
    }

    @RequestMapping(value = "/events/{eventId}", method = RequestMethod.GET)
    public String getEvent(@PathVariable Long eventId, 
			   HttpServletRequest request, 
			   ModelMap model) {

	logger.debug("entering Restful getEvent()");

	List<Event> events = new ArrayList<Event>();
	Event event = eventDao.getEventById(eventId);
	events.add(event);
	model.addAttribute("events", events);
	setMultiViewRenderObject(model, "events");

	return decorateView(".listEvents_view", request);
    }


    @RequestMapping(value="/deleteEvent.htm", method=RequestMethod.GET)
    public String deleteEvent(@RequestParam(value="event_id") Integer eventId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(eventId, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (eventId != null) {
	    Event event = eventDao.getEventById(eventId);
	    model.addAttribute("event", event);
	}

	return decorateView(".deleteEventConfirm_view", request);
    }

    @RequestMapping(value="/deleteEventConfirmed.htm", method=RequestMethod.POST)
    public String deleteEventConfirmed(@ModelAttribute("event") Event event, BindingResult result, SessionStatus status, HttpServletRequest request) {

	// validate event
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(event, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	eventDao.deleteEventById(event.getId());
	dataResourceDao.deleteDataResourceById(event.getId());
	
	return decorateView(".deleteEventSuccess_dashboard", request);
    }

}
