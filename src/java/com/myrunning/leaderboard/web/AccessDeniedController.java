package com.myrunning.leaderboard.web;

/**
 *  File: AccessDeniedController.java
 *  Author:  Joshua Forester
 *  Date: 2009/10/23
 *  Description: Controller object for ActionForward-like controller.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController {

	@RequestMapping("/accessDenied.htm")
	public String redirect(HttpServletRequest request)
	{
	    String view = ViewUtils.decideView(".accessDenied_dashboard", request);
	    return view;
	}
}
