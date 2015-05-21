package com.myrunning.leaderboard.web;

/**
 *  File: LogoutController.java
 *  Author:  Joshua Forester
 *  Date: 2009/09/15
 *  Description: Controller object for logging out.
 **/


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LogoutController {

    @RequestMapping(value="/logoutSuccess.htm", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, SessionStatus status) {

	// invalidate session
	HttpSession session = request.getSession();
	session.invalidate();

	String view = ViewUtils.decideView(".logoutSuccess_view", request);
	return view;
    }

}
