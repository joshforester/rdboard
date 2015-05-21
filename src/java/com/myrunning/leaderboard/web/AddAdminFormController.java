package com.myrunning.leaderboard.web;

/**
 *  File: AddAdminFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Controller object for the admin app.
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
import com.myrunning.leaderboard.services.ifacesvc.AdminService;
import com.myrunning.leaderboard.db.DataAccessFilter;


public class AddAdminFormController extends AdminFormController {

    static Logger logger = Logger.getLogger(AddAdminFormController.class);

    private static AdminService adminService;


    public AddAdminFormController() {
	// empty
    }

    public void setAdminService(AdminService adminService) {
	this.adminService=adminService;
    }

    public ModelAndView onSubmit (HttpServletRequest request, 
				  HttpServletResponse response, 
				  Object command, 
				  BindException errors) 
	throws Exception {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	Admin a = (Admin) command;


	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	// filter out folks trying to escalate privileges;
	// promoter should only be able to create event staff and below
	/*
	if (!admin.hasAuthority("ROLE_ROOT")) {
	    if (a.hasAuthority("ROLE_ROOT")) {
		a.removeAuthority("ROLE_ROOT");
	    }
	    if (a.hasAuthority("ROLE_PROMOTER")) {
		a.removeAuthority("ROLE_PROMOTER");
	    }
	}
	*/

	adminService.addAdmin(a, admin);

	// add admin to ModelAndView and display username in success
	ModelAndView mav = new ModelAndView(this.getSuccessView());
	mav.addObject("username", a.getUsername());
	
	return mav;
    }


}
