package com.myrunning.leaderboard.web;

/**
 *  File: LoginController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/11
 *  Description: Controller object for ActionForward-like controller.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login.htm")
	public String redirect(HttpServletRequest request)
	{
	    String view = ViewUtils.decideView(".loginForm_dashboard", request);
	    return view;
	}
}
