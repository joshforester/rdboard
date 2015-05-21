package com.myrunning.leaderboard.web;

/**
 *  File: EditCpOrderController.java
 *  Author:  Joshua Forester
 *  Date: 2009/11/06
 *  Description: Controller object for editing control point numbers.
 **/


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.validation.BindException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.CpOrderShift;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;



public class EditCpOrderController extends SimpleFormController {

    static Logger logger = Logger.getLogger(EditCpOrderController.class);

    private ControlPointDao controlPointDao;


    public EditCpOrderController() {
	// empty
    }


    public ModelAndView onSubmit (HttpServletRequest request, 
				  HttpServletResponse response, 
				  Object command, 
				  BindException errors) 
	throws Exception {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	WebApplicationContext wac = getWebApplicationContext();
	controlPointDao = (ControlPointDao) wac.getBean("controlPointDao");

	CpOrderShift cpOrderShift = (CpOrderShift) command;
	ControlPoint controlPoint = controlPointDao.getControlPointById(cpOrderShift.getCpId());
	int newOrder;
	if (cpOrderShift.getChange().equals("increment")) {

	    newOrder = controlPoint.getCpOrder() + 1;

	} else if (cpOrderShift.getChange().equals("decrement")) {

	    newOrder = controlPoint.getCpOrder() - 1;

        // shouldn't ever happen
	} else {

	    newOrder = controlPoint.getCpOrder() + 1;

	}
	ControlPoint controlPoint2 = controlPointDao.getControlPointByCourseIdAndCpOrder(controlPoint.getCourseId(), newOrder);

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(controlPoint, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	// check access for control point we're switching places with
	if (DataAccessFilter.filterDataResourceById(controlPoint2, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}


	// get control point, update with new order
	controlPoint2.setCpOrder(controlPoint.getCpOrder());
	controlPoint.setCpOrder(newOrder);

	// get control point switched out, and update with new order
	controlPointDao.updateControlPoint(controlPoint);
	controlPointDao.updateControlPoint(controlPoint2);

	String referer = request.getHeader("Referer");
	return new ModelAndView("redirect:" + referer);
    }


}
