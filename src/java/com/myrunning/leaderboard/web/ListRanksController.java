package com.myrunning.leaderboard.web;

/**
 *  File: ListRanksController.java
 *  Author:  Joshua Forester
 *  Date: 2010/01/16
 *  Description: Controller object for listing Ranks.
 **/


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.model.Rank;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.services.ifacesvc.RankService;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;


@Controller
public class ListRanksController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListRanksController.class);

    private RankService rankService;
    private CourseDao courseDao;


    @Autowired
    public ListRanksController(RankService rankService,
			       CourseDao courseDao) {
	this.rankService=rankService;
	this.courseDao=courseDao;
    }


    @RequestMapping(value = "/teams/{teamId}/ranks", method = RequestMethod.GET)
    public String getTeamRanks(@PathVariable Long teamId,
			       @RequestParam(value="order", required=false) String order,
			       HttpServletRequest request,
			       ModelMap model) {

	List<Rank> ranks = rankService.getRanksByTeamId(teamId, order);
	Course course = courseDao.getCourseByTeamId(teamId);

	model.addAttribute("ranks", ranks);
	model.addAttribute("teamId", teamId);
	model.addAttribute("courseId", course.getId());

        setMultiViewRenderObject(model, "ranks");
	
	return decorateView(".listRanks_view", request);
    }

}
