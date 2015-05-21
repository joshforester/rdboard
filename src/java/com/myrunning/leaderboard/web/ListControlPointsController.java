package com.myrunning.leaderboard.web;

/**
 *  File: ListControlPointsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/20
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.bbn.openmap.LatLonPoint;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.Leaderboard;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.services.ifacesvc.CourseService;


@Controller
public class ListControlPointsController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListControlPointsController.class);

    @Autowired
    private DataResourceDao dataResourceDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private ControlPointDao controlPointDao;

    @Autowired
    private CourseService courseService;


    @RequestMapping(value = "/courses/{courseId}/cps", method = RequestMethod.GET)
    public String getCourseControlPoints(@PathVariable Long courseId,
					 @RequestParam(value="order", required=false) String order,
					 HttpServletRequest request,
					 ModelMap model) {
	Event event = eventDao.getEventByCourseId(courseId);
	model.addAttribute("event", event);

	List<ControlPoint> controlPoints = controlPointDao.getControlPointsByCourseId(courseId, order);
	model.addAttribute("controlPoints", controlPoints);
	model.addAttribute("courseId", courseId);
	setMultiViewRenderObject(model, "controlPoints");

        return decorateView(".listControlPoints_view", request);
    }

    @RequestMapping(value = "/courses/{courseId}/cps/gmap.htm", method = RequestMethod.GET)
    public String getCourseControlPointsMap(@PathVariable Long courseId,
					    @RequestParam(value="center", required=false) String center,
					    @RequestParam(value="order", required=false) String order,
					    HttpServletRequest request,
					    ModelMap model) {
	Leaderboard leaderboard = courseService.generateLeaderboard(courseId, false, true, true, false, true);
	model.addAttribute("leaderboard", leaderboard);
	model.addAttribute("course", leaderboard.getCourse());
	model.addAttribute("courseId", courseId);

	Event event = eventDao.getEventByCourseId(courseId);
	model.addAttribute("event", event);

	// ignore center coordinate if not valid lat/long point
	if (center != null) {
	    String latitude = center.substring(0, center.indexOf(","));
	    String longitude = center.substring(center.indexOf(",") + 1);
	    LatLonPoint pt = null;
	    try {
		pt = new LatLonPoint(Float.parseFloat(latitude),
				     Float.parseFloat(longitude));
	    } catch (NumberFormatException nfe) {
		pt = null;
	    }
	    
	    model.addAttribute("center", pt);
	}

	setMultiViewRenderObject(model, "leaderboard");

        return decorateView(".listControlPointsGmap_dashboard", request);
    }

    @RequestMapping(value = "/cps/{controlPointId}", method = RequestMethod.GET)
    public String getControlPoint(@PathVariable Long controlPointId,
				  HttpServletRequest request,
				  ModelMap model) {
        ControlPoint controlPoint = controlPointDao.getControlPointById(controlPointId);
        List<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
        controlPoints.add(controlPoint);
        model.addAttribute("controlPoints", controlPoints);
	model.addAttribute("courseId", controlPoint.getCourseId());

	Event event = eventDao.getEventByCourseId(controlPoint.getCourseId());
	model.addAttribute("event", event);

	setMultiViewRenderObject(model, "controlPoints");

        return decorateView(".listControlPoints_view", request);
    }

    @RequestMapping(value="/deleteControlPoint.htm", method=RequestMethod.GET)
    public String deleteControlPoint(@RequestParam(value="cp_id") Integer controlPointId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	if (DataAccessFilter.filterDataResourceById(controlPointId, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	ControlPoint controlPoint = controlPointDao.getControlPointById(controlPointId);
	model.addAttribute("controlPoint", controlPoint);
	
	Event event = eventDao.getEventByCourseId(controlPoint.getCourseId());
	model.addAttribute("event", event);

	return decorateView(".deleteControlPointConfirm_view", request);
    }

    @RequestMapping(value="/deleteControlPointConfirmed.htm", method=RequestMethod.POST)
    public String deleteControlPointConfirmed(@ModelAttribute("controlPoint") ControlPoint controlPoint, BindingResult result, SessionStatus status, HttpServletRequest request, ModelMap model) {

	// validate controlPoint
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	} 

	if (DataAccessFilter.filterDataResourceById(controlPoint, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	controlPointDao.deleteControlPointById(controlPoint.getId());
	dataResourceDao.deleteDataResourceById(controlPoint.getId());
	    
	Event event = eventDao.getEventByCourseId(controlPoint.getCourseId());
	model.addAttribute("event", event);

	return decorateView(".deleteControlPointSuccess_dashboard", request);
    }

}
