package com.myrunning.leaderboard.web;

/**
 *  File: AddControlPointFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Controller object for the controlPoint app.
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
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.services.ifacesvc.ControlPointService;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.validation.ControlPointValidator;


@Controller
@RequestMapping("addControlPoint.htm")
public class AddControlPointFormController extends ControlPointFormController {

    static Logger logger = Logger.getLogger(AddControlPointFormController.class);

    private ControlPointService controlPointService;
    private ControlPointValidator controlPointValidator;


    @Autowired
    public AddControlPointFormController(ControlPointService controlPointService,
					 @Qualifier("controlPointValidator") ControlPointValidator controlPointValidator) {
	this.controlPointService=controlPointService;
	this.controlPointValidator=controlPointValidator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showControlPointForm(HttpServletRequest request,
					     ModelMap model) {
        ControlPoint controlPoint = new ControlPoint();
        model.addAttribute("controlPoint", controlPoint);
        return new ModelAndView(decorateView(getFormView(), request));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("controlPoint") ControlPoint controlPoint,
				 BindingResult result,
				 HttpServletRequest request)
        throws Exception {

        controlPointValidator.validate(controlPoint, result);
        if (result.hasErrors()) {
            return new ModelAndView(decorateView(getFormView(), request));
        }

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}
	
	if (DataAccessFilter.filterDataResourceById(controlPoint.getCourseId(), admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}


	controlPointService.addControlPoint(controlPoint, admin);
	if (controlPoint.hasLocation()) {
	    controlPointService.attachHeyWhatsThatId(controlPoint);
	}
	
	return new ModelAndView(decorateView(getSuccessView(), request));

    }


}
