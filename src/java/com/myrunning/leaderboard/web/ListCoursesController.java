package com.myrunning.leaderboard.web;

/**
 *  File: ListCoursesController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Controller object for listing Courses.
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
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;


@Controller
public class ListCoursesController extends NewLayoutController {

    static Logger logger = Logger.getLogger(ListCoursesController.class);

    @Autowired
    private DataResourceDao dataResourceDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private EventDao eventDao;


    @RequestMapping(value = "/events/{eventId}/courses", method = RequestMethod.GET)
    public String getEventCourses(@PathVariable Long eventId,
				  HttpServletRequest request, 
				  ModelMap model) {
	Event event = eventDao.getEventById(eventId);
	model.addAttribute("event", event);

	List<Course> courses = courseDao.getCoursesByEventId(eventId, "name");
	model.addAttribute("courses", courses);
	setMultiViewRenderObject(model, "courses");
	
	return decorateView(".listCourses_view", request);
    }


    @RequestMapping(value = "/courses/{courseId}", method = RequestMethod.GET)
    public String getCourse(@PathVariable Long courseId,
			    HttpServletRequest request, 
			    ModelMap model) {
	Event event = eventDao.getEventByCourseId(courseId);
	model.addAttribute("event", event);

	Course course = courseDao.getCourseById(courseId);
	List<Course> courses = new ArrayList<Course>();
	courses.add(course);

	model.addAttribute("courses", courses);
	model.addAttribute("courseId", courseId);

	setMultiViewRenderObject(model, "courses");
	
	return decorateView(".listCourses_view", request);
    }


    @RequestMapping(value = "/courses/{courseId}/description.htm", method = RequestMethod.GET)
    public String getDescription(@PathVariable Long courseId,
			    HttpServletRequest request, 
			    ModelMap model) {
	Event event = eventDao.getEventByCourseId(courseId);
	model.addAttribute("event", event);

	Course course = courseDao.getCourseById(courseId);
	String description = null;

	if (course != null) {
	    description = course.getDescription();
	}
	
	model.addAttribute("description", description);
	model.addAttribute("courseId", courseId);

	setMultiViewRenderObject(model, "description");
	
	return decorateView(".showDescription_view", request);
    }


    @RequestMapping(value="/deleteCourse.htm", method=RequestMethod.GET)
    public String deleteCourse(@RequestParam(value="course_id") Integer courseId, HttpServletRequest request, ModelMap model) {
	
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	if (DataAccessFilter.filterDataResourceById(courseId, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (courseId != null) {	    
	    Event event = eventDao.getEventByCourseId(courseId);
	    model.addAttribute("event", event);

	    Course course = courseDao.getCourseById(courseId);
	    model.addAttribute("course", course);
	}

	return decorateView(".deleteCourseConfirm_view", request);
    }

    @RequestMapping(value="/deleteCourseConfirmed.htm", method=RequestMethod.POST)
    public String deleteCourseConfirmed(@ModelAttribute("course") Course course, BindingResult result, SessionStatus status, HttpServletRequest request, ModelMap model) {

	// validate course
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(course, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	courseDao.deleteCourseById(course.getId());
	dataResourceDao.deleteDataResourceById(course.getId());

	Event event = eventDao.getEventByCourseId(course.getId());
	model.addAttribute("event", event);

	return decorateView(".deleteCourseSuccess_dashboard", request);
    }

}
