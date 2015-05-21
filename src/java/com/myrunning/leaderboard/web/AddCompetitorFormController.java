package com.myrunning.leaderboard.web;

/**
 *  File: AddCompetitorFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Controller object for the competitor app.
 **/


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.validation.BindException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Competitor;
import com.myrunning.leaderboard.services.ifacesvc.CompetitorService;
import com.myrunning.leaderboard.db.DataAccessFilter;



public class AddCompetitorFormController extends CompetitorFormController {

    static Logger logger = Logger.getLogger(AddCompetitorFormController.class);

    private static CompetitorService competitorService;


    public AddCompetitorFormController() {
	// empty
    }

    public void setCompetitorService(CompetitorService competitorService) {
	this.competitorService=competitorService;
    }

    public ModelAndView onSubmit (HttpServletRequest request, 
				  HttpServletResponse response, 
				  Object command, 
				  BindException errors) 
	throws Exception {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Competitor competitor = (Competitor) command;

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	competitorService.addCompetitor(competitor, admin);

	return new ModelAndView(this.getSuccessView());
    }


}
