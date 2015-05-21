package com.myrunning.leaderboard.global;

/**
 *  File: RDValidationUtils.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/28
 *  Description: Helper object for validation routines.
 **/


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.db.dao.ifacedao.AdminDao;


/*
 *  Class for keeping Spring Security and admin session variables in sync.
 **/
public class AuthenticatedUserFilter extends OncePerRequestFilter {

    static Logger logger = Logger.getLogger(AuthenticatedUserFilter.class);

    private static AdminDao adminDao;


    public void setAdminDao(AdminDao adminDao) {
	this.adminDao=adminDao;
    }


    /*
     * Add an admin object to the session based on the username that was used
     * upon login.  Note that remember me works to retain username.
     **/
    protected void doFilterInternal(HttpServletRequest request, 
				    HttpServletResponse response, 
				    FilterChain filterChain) {

	MDC.put("Session", request.getSession().getId());

	logger.debug("filtering request");

	// get username from Spring Security
	SecurityContext sctx = SecurityContextHolder.getContext();
	Authentication auth = sctx.getAuthentication();
	String username = (String) auth.getName();

	logger.debug("username:" + username);

	// set the session variable with admin
	Admin admin = adminDao.getAdminByUsername(username);
	HttpSession session = request.getSession();
	session.setAttribute(Constants.ADMIN_SESSION_KEY, admin);

	try {
	    filterChain.doFilter(request, response);
	} catch (ServletException se) {
	    se.printStackTrace();
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
    }


    /*
     * We only want to filter on the request after the user has logged in.
     * Specifically, when the Spring Security login exists but the admin doesn't.
     **/
    protected boolean shouldNotFilter(HttpServletRequest request) {

	MDC.put("Session", request.getSession().getId());

	if ((request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY) == null) &&
	    (!SecurityContextHolder.getContext().getAuthentication().getName().equals("roleAnonymous"))) {
	    logger.debug("Choosing to filter.");
	    return false;
	} else {
	    logger.debug("Choosing NOT to filter.");
	    return true;
	}
    }

}
