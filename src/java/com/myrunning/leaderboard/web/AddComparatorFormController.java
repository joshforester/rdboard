package com.myrunning.leaderboard.web;

/**
 *  File: AddComparatorFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/26
 *  Description: Controller object for the comparator app.
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
import com.myrunning.leaderboard.model.Comparator;
import com.myrunning.leaderboard.services.ifacesvc.ComparatorService;
import com.myrunning.leaderboard.db.DataAccessFilter;



public class AddComparatorFormController extends ComparatorFormController {

    static Logger logger = Logger.getLogger(AddComparatorFormController.class);

    private static ComparatorService comparatorService;


    public AddComparatorFormController() {
	// empty
    }


    public void setComparatorService(ComparatorService comparatorService) {
	this.comparatorService=comparatorService;
    }


    public ModelAndView onSubmit (HttpServletRequest request, 
				  HttpServletResponse response, 
				  Object command, 
				  BindException errors) 
	throws Exception {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Comparator comparator = (Comparator) command;

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(comparator.getCourseId(), admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	comparatorService.addComparator(comparator, admin);
	
	return new ModelAndView(this.getSuccessView());
    }


}
