package com.myrunning.leaderboard.web;

/**
 *  File: NewLayoutController.java
 *  Author:  Joshua Forester
 *  Date: 2009/11/13
 *  Description: Controller object for the handling layout parameter.
 **/


import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;


public class NewLayoutController extends MultiViewController {

    static Logger logger = Logger.getLogger(NewLayoutController.class);

    private String successView;
    private String formView;

    
    /**
     * Gets the current value of successView
     * @return Current value of successView
     */
    public String getSuccessView() {
	return successView;
    }

    /**
     * Sets the value of successView
     * @param successView New value for successView
     */
    public void setSuccessView(String successView) {
	this.successView=successView;
    }

    /**
     * Gets the current value of formView
     * @return Current value of formView
     */
    public String getFormView() {
	return formView;
    }
    
    /**
     * Sets the value of formView
     * @param formView New value for formView
     */
    public void setFormView(String formView) {
	this.formView=formView;
    }


    public NewLayoutController() {
	//nothing
    }
    

    protected String decorateView(String view, HttpServletRequest request) {
	String newView = ViewUtils.decideView(view, request);
	if (newView.equals("default")) {
	    return view;
	} else {
	    return newView;
	}
    }


}
