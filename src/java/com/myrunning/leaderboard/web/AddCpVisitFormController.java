package com.myrunning.leaderboard.web;

/**
 *  File: AddCpVisitFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/25
 *  Description: Controller object for the cpVisit app.
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
import com.myrunning.leaderboard.model.CpVisit;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.services.ifacesvc.CpVisitService;
import com.myrunning.leaderboard.validation.CpVisitValidator;


@Controller
@RequestMapping("addCpVisit.htm")
public class AddCpVisitFormController extends CpVisitFormController {

    static Logger logger = Logger.getLogger(AddCpVisitFormController.class);

    private CpVisitService cpVisitService;
    private CpVisitValidator cpVisitValidator;


    @Autowired
    public AddCpVisitFormController(CpVisitService cpVisitService,
				    @Qualifier("cpVisitValidator") CpVisitValidator cpVisitValidator) {
	this.cpVisitService=cpVisitService;
	this.cpVisitValidator=cpVisitValidator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showCpVisitForm(HttpServletRequest request,
					ModelMap model) {
        CpVisit cpVisit = new CpVisit();
	long teamId = ServletRequestUtils.getLongParameter(request, "team_id", 0);
	long cpId = ServletRequestUtils.getLongParameter(request, "cp_id", 0);
	cpVisit.setTeamId(teamId);
	cpVisit.setCpId(cpId);

        model.addAttribute("cpVisit", cpVisit);
        return new ModelAndView(decorateView(getFormView(), request));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit (@ModelAttribute("cpVisit") CpVisit cpVisit,
				  BindingResult result,
				  HttpServletRequest request)
	throws Exception {

	cpVisitValidator.validate(cpVisit, result);
        if (result.hasErrors()) {
            return new ModelAndView(decorateView(getFormView(), request));
        }

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}  

	if ((DataAccessFilter.filterDataResourceById(cpVisit.getTeamId(), admin.getId()) == null) ||
	    (DataAccessFilter.filterDataResourceById(cpVisit.getCpId(), admin.getId()) == null)) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}
	
	cpVisitService.saveCpVisit(cpVisit);

	return new ModelAndView(decorateView(getSuccessView(), request));
    }


}
