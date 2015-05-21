package com.myrunning.leaderboard.web;

/**
 *  File: ViewUtils.java
 *  Author:  Joshua Forester
 *  Date: 2009/11/16
 *  Description: Helper object for working with views.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.ServletRequestUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;


public class ViewUtils {

    static Logger logger = Logger.getLogger(ViewUtils.class);


    /**
     * Util method for deciding which layout to use based on a request.
     * @param defaultView the default view if errors during deciding layout
     * @param request the HTTP request 
     * @return the layout to render
     */
    private static String decideLayout(String defaultView, HttpServletRequest request) {

	String layout = ServletRequestUtils.getStringParameter(request, "layout", "default");

	// validate layouts
	// if in string array
	// else return formView in spring

	return layout;
    }


    /**
     * Util method for deciding which view to use based on a request.
     * @param defaultView the default view if errors deciding
     * @param request the HTTP request 
     * @return the view to render
     */
    public static String decideView(String defaultView, HttpServletRequest request) {

	String layout = decideLayout(defaultView, request);

	// if layout is default, then return default view
	if (layout.equals("default")) {
	    return defaultView;
	} 

	// decide on view here.  if defaulted, proceed with below

	String baseView = defaultView.substring(0, defaultView.lastIndexOf('_'));
	
	logger.debug("Baseview: " + baseView);

	String view = baseView + "_" + layout;

	logger.debug("Decided upon view:  " + view);

	return view;
    }

}