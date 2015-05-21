package com.myrunning.leaderboard.web;

/**
 *  File: LayoutController.java
 *  Author:  Joshua Forester
 *  Date: 2009/11/13
 *  Description: Controller object for the handling layout parameter.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.BindException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;


public class LayoutController extends SimpleFormController {

    static Logger logger = Logger.getLogger(LayoutController.class);


    public LayoutController() {
	// empty
    }

    
    protected Object formBackingObject (HttpServletRequest request)
	throws Exception {

	String newFormView = ViewUtils.decideView(getFormView(), request);
	if (!newFormView.equals("default")) {
	    setFormView(newFormView);
	}

	String newSuccessView = ViewUtils.decideView(getSuccessView(), request);
	if (!newSuccessView.equals("default")) {
	    setSuccessView(newSuccessView);
	}

	return super.formBackingObject(request);
    }



}
