package com.myrunning.leaderboard.web;

/**
 *  File: ListDivisionsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Controller object for listing Divisions.
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Division;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.DivisionDao;


@Controller
public class ListDivisionsController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListDivisionsController.class);

    @Autowired
    private DataResourceDao dataResourceDao;

    @Autowired
    private DivisionDao divisionDao;


    @RequestMapping(value = "/events/{eventId}/divisions", method = RequestMethod.GET)
    public String getEventDivisions(@PathVariable Long eventId,
				    @RequestParam(value="order", required=false) String order,
				    HttpServletRequest request,
				    ModelMap model) {
        List<Division> divisions = divisionDao.getDivisionsByEventId(eventId, order);
        model.addAttribute("divisions", divisions);
	model.addAttribute("eventId", eventId);
	setMultiViewRenderObject(model, "divisions");

        return decorateView(".listDivisions_view", request);
    }

    @RequestMapping(value = "/divisions/{divisionId}", method = RequestMethod.GET)
    public String getDivision(@PathVariable Long divisionId,
			      HttpServletRequest request,
			      ModelMap model) {
        Division division = divisionDao.getDivisionById(divisionId);
        List<Division> divisions = new ArrayList<Division>();
        divisions.add(division);
        model.addAttribute("divisions", divisions);
	setMultiViewRenderObject(model, "divisions");

        return decorateView(".listDivisions_view", request);
    }

    @RequestMapping(value="/deleteDivision.htm", method=RequestMethod.GET)
    public String deleteDivision(@RequestParam(value="division_id") Integer divisionId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(divisionId, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (divisionId != null) {
	    Division division = divisionDao.getDivisionById(divisionId);
	    model.addAttribute("division", division);
	}

	return decorateView(".deleteDivisionConfirm_view", request);
    }

    @RequestMapping(value="/deleteDivisionConfirmed.htm", method=RequestMethod.POST)
    public String deleteDivisionConfirmed(@ModelAttribute("division") Division division, BindingResult result, SessionStatus status, HttpServletRequest request) {

	// validate division
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(division, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	divisionDao.deleteDivisionById(division.getId());
	dataResourceDao.deleteDataResourceById(division.getId());

	return decorateView(".deleteEventSuccess_dashboard", request);
    }
}
