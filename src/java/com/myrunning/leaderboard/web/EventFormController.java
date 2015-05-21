package com.myrunning.leaderboard.web;

/**
 *  File: EventFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/17
 *  Description: Controller object for the event app.
 **/


import java.text.SimpleDateFormat;
import java.util.Collection;
import java.sql.Timestamp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.global.LocaleUtils;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimestampEditor;


@Controller
public class EventFormController extends NewLayoutController {

    static Logger logger = Logger.getLogger(EventFormController.class);


    public EventFormController() {
	// empty
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder)
	throws Exception {
	SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
	CustomTimestampEditor tsEditor = new CustomTimestampEditor(dateFormat, true, 10);
	binder.registerCustomEditor(Timestamp.class, "startTime", tsEditor);
	binder.registerCustomEditor(Timestamp.class, "endTime", tsEditor);
    }


    @ModelAttribute("countries")
    public Collection<String> loadCountries() {
	return LocaleUtils.getCountries();
    }

    @ModelAttribute("regions")
    public Collection<String> loadRegions() {
	return LocaleUtils.getRegions();
    }



}
