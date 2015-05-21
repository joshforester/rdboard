package com.myrunning.leaderboard.web;

/**
 *  File: AddEventFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Controller object for the event app.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.services.ifacesvc.EventService;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimestampEditor;
import com.myrunning.leaderboard.validation.EventValidator;


@Controller
@RequestMapping("/addEvent.htm")
public class AddEventFormController extends EventFormController {

    static Logger logger = Logger.getLogger(AddEventFormController.class);

    private EventService eventService;
    private EventValidator eventValidator;


    @Autowired
    public AddEventFormController(EventService eventService,
				  @Qualifier("eventValidator") EventValidator eventValidator) {
	this.eventService=eventService;
	this.eventValidator=eventValidator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showEventForm(HttpServletRequest request,
				      ModelMap model) {
	Event event = new Event();
	model.addAttribute("event", event);
	return new ModelAndView(decorateView(getFormView(), request));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("event") Event event,
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
	
	eventService.addEvent(event, admin);
	
	return new ModelAndView(decorateView(getSuccessView(), request));

    }


}
