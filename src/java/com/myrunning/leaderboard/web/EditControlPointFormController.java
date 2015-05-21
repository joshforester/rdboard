package com.myrunning.leaderboard.web;

/**
 *  File: EditControlPointFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Controller object for the controlPoint app.
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
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.services.ifacesvc.ControlPointService;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.validation.EditControlPointValidator;



@Controller
@RequestMapping("/editControlPoint.htm")
public class EditControlPointFormController extends ControlPointFormController {

    static Logger logger = Logger.getLogger(EditControlPointFormController.class);

    private ControlPointDao controlPointDao;

    @Autowired
    private ControlPointService controlPointService;
    private EditControlPointValidator controlPointValidator;


    @Autowired
    public EditControlPointFormController(ControlPointDao controlPointDao,
					  @Qualifier("editControlPointValidator") EditControlPointValidator editControlPointValidator) {
        this.controlPointDao=controlPointDao;
	//        this.controlPointService=controlPointService;
        this.controlPointValidator=editControlPointValidator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showControlPointForm(HttpServletRequest request,
					     ModelMap model) {
	ControlPoint controlPoint = new ControlPoint();
	long cp_id = ServletRequestUtils.getLongParameter(request, "cp_id", 0);

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    model.addAttribute("controlPoint", controlPoint);
            return new ModelAndView(decorateView(getFormView(), request));
	} 

	if (DataAccessFilter.filterDataResourceById(cp_id, admin.getId()) == null) {
	    model.addAttribute("controlPoint", controlPoint);
            return new ModelAndView(decorateView(getFormView(), request));
	}

	if (cp_id != 0) {
	    controlPoint = controlPointDao.getControlPointById(cp_id);
	}

	model.addAttribute("controlPoint", controlPoint);
	return new ModelAndView(decorateView(getFormView(), request));
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit (@ModelAttribute("controlPoint") ControlPoint controlPoint,
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

	if (DataAccessFilter.filterDataResourceById(controlPoint, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	// get current control point and see if 
	// 1.  we have everything needed to discern location.
	// 2.  something that would affect the location changed.  
	// if so, call controlPointService.attachHeyWhatsThatId
	
	ControlPoint existingControlPoint = controlPointDao.getControlPointById(controlPoint.getId());
	controlPointDao.updateControlPoint(controlPoint);
	if (controlPoint.hasLocation()) {
	    if (controlPoint.locationEquals(existingControlPoint)) {
		logger.debug("before attachHeyWhatsThatId @Async call.");
		controlPointService.attachHeyWhatsThatId(controlPoint);
		logger.debug("after attachHeyWhatsThatId @Async call.");
	    }
	}


	return new ModelAndView(decorateView(getSuccessView(), request));
    }


}
