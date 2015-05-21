package com.myrunning.leaderboard.web;

/**
 *  File: ListComparatorsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/26
 *  Description: Controller object for listing Control Points.
 **/


import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Comparator;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.ComparatorDao;


@Controller
public class ListComparatorsController {

    @Autowired
    private DataResourceDao dataResourceDao;

    @Autowired
    private ComparatorDao comparatorDao;


    @RequestMapping(value="/listComparators.htm", method = RequestMethod.GET)
    public String showForm(@RequestParam(value="course_id", required=false) Integer courseId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return null;
	}
    
	if (courseId != null) {
	    List<Comparator> comparators = comparatorDao.getComparatorsByCourseId(courseId);
	    comparators = (List<Comparator>) DataAccessFilter.filterColById(comparators, admin.getId());

	    model.addAttribute("comparators", comparators);
	}

	String view = ViewUtils.decideView(".listComparators_view", request);
	return view;
    }


    @RequestMapping(value="/deleteComparator.htm", method=RequestMethod.GET)
    public String deleteComparator(@RequestParam(value="comparator_id") Integer comparatorId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(comparatorId, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (comparatorId != null) {
	    Comparator comparator = comparatorDao.getComparatorById(comparatorId);
	    model.addAttribute("comparator", comparator);
	}

	String view = ViewUtils.decideView(".deleteComparatorConfirm_view", request);
	return view;
    }

    @RequestMapping(value="/deleteComparatorConfirmed.htm", method=RequestMethod.POST)
    public String deleteComparatorConfirmed(@ModelAttribute("comparator") Comparator comparator, BindingResult result, SessionStatus status, HttpServletRequest request) {

	// validate comparator
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(comparator, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	comparatorDao.deleteComparatorById(comparator.getId());
	dataResourceDao.deleteDataResourceById(comparator.getId());

	String view = ViewUtils.decideView(".deleteComparatorSuccess_dashboard", request);
	return view;
    }

}
