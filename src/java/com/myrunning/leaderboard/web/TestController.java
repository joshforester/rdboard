package com.myrunning.leaderboard.web;

/**
 *  File: TestController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/11
 *  Description: Controller object for testing ActionForward-like controller.
 **/


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("/test.htm")
	public String redirect()
	{
	    return ".test";
	}
}
