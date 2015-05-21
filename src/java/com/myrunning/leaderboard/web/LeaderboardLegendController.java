package com.myrunning.leaderboard.web;

/**
 *  File: LeaderboardLegendController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/11
 *  Description: Controller object for ActionForward-like controller.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaderboardLegendController {

	@RequestMapping("/leaderboardLegend.htm")
	public String redirect(HttpServletRequest request)
	{
	    String view = ViewUtils.decideView(".leaderboardLegend_view", request);
	    return view;
	}
}
