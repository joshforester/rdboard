package com.myrunning.leaderboard.web;

/**
 *  File: LoginFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/09/10
 *  Description: Controller object for the login app.
 **/


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.validation.BindException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Credentials;
import com.myrunning.leaderboard.db.dao.ifacedao.AdminDao;



public class LoginFormController extends SimpleFormController {

    static Logger logger = Logger.getLogger(LoginFormController.class);

    private AdminDao adminDao;



    public LoginFormController() {
	// empty
    }


    public ModelAndView onSubmit (HttpServletRequest request, 
				  HttpServletResponse response, 
				  Object command, 
				  BindException errors) 
	throws Exception {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	WebApplicationContext wac = getWebApplicationContext();
	adminDao = (AdminDao) wac.getBean("adminDao");

	Credentials creds = (Credentials) command;
	Admin admin = adminDao.getAdminByUsername(creds.getUsername());
	HttpSession session = request.getSession();
	session.setAttribute(Constants.ADMIN_SESSION_KEY, admin);

	return new ModelAndView(this.getSuccessView());
    }


}
