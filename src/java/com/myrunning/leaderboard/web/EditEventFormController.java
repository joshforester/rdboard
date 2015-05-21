package com.myrunning.leaderboard.web;

/**
 *  File: EditEventFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Controller object for the event app.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;
import com.myrunning.leaderboard.validation.EditEventValidator;



@Controller
@RequestMapping("/editEvent.htm")
public class EditEventFormController extends EventFormController {

    static Logger logger = Logger.getLogger(EditEventFormController.class);

    private EventDao eventDao;
    private EditEventValidator eventValidator;


    @Autowired
    public EditEventFormController(EventDao eventDao,
				   @Qualifier("editEventValidator") EditEventValidator editEventValidator) {
	this.eventDao=eventDao;
	this.eventValidator=editEventValidator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showEventForm(HttpServletRequest request,
				      ModelMap model) {
	Event event = new Event();
	long event_id = ServletRequestUtils.getLongParameter(request, "event_id", 0);

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    model.addAttribute("event", event);
	    return new ModelAndView(decorateView(getFormView(), request));
	}

	if (DataAccessFilter.filterDataResourceById(event_id, admin.getId()) == null) {
	    model.addAttribute("event", event);
	    return new ModelAndView(decorateView(getFormView(), request));
	} 
	
	if (event_id != 0) {
	    event = eventDao.getEventById(event_id);
	}

	model.addAttribute("event", event);
	return new ModelAndView(decorateView(getFormView(), request));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit (@ModelAttribute("event") Event event,
				  BindingResult result,
				  HttpServletRequest request)
	throws Exception {

	eventValidator.validate(event, result);
	if (result.hasErrors()) {
	    return new ModelAndView(decorateView(getFormView(), request));
	}

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");


	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(event, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	eventDao.updateEvent(event);
	return new ModelAndView(decorateView(getSuccessView(), request));
    }

}
