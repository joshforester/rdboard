package com.myrunning.leaderboard.web;

/**
 *  File: AddDivisionFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Controller object for the division app.
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
import com.myrunning.leaderboard.model.Division;
import com.myrunning.leaderboard.services.ifacesvc.DivisionService;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.validation.DivisionValidator;



@Controller
@RequestMapping("addDivision.htm")
public class AddDivisionFormController extends DivisionFormController {

    static Logger logger = Logger.getLogger(AddDivisionFormController.class);

    private DivisionService divisionService;
    private DivisionValidator divisionValidator;
 

    @Autowired
    public AddDivisionFormController(DivisionService divisionService,
				     @Qualifier("divisionValidator") DivisionValidator divisionValidator) {
	this.divisionService=divisionService;
	this.divisionValidator=divisionValidator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showDivisionForm(HttpServletRequest request,
					 ModelMap model) {
        Division division = new Division();
        model.addAttribute("division", division);
        return new ModelAndView(decorateView(getFormView(), request));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit (@ModelAttribute("division") Division division,
				  BindingResult result,
				  HttpServletRequest request)
	throws Exception {
	
	divisionValidator.validate(division, result);
        if (result.hasErrors()) {
            return new ModelAndView(decorateView(getFormView(), request));
        }

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(division.getEventId(), admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	divisionService.addDivision(division, admin);
	
	return new ModelAndView(decorateView(getSuccessView(), request));

    }


}
