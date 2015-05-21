package com.myrunning.leaderboard.web;

/**
 *  File: EditCourseFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Controller object for the course app.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.validation.EditCourseValidator;



@Controller
@RequestMapping("/editCourse.htm")
public class EditCourseFormController extends CourseFormController {

    static Logger logger = Logger.getLogger(EditCourseFormController.class);

    private CourseDao courseDao;
    private EditCourseValidator courseValidator;


    @Autowired
    public EditCourseFormController(CourseDao courseDao,
				    @Qualifier("editCourseValidator") EditCourseValidator editCourseValidator) {
	this.courseDao=courseDao;
	this.courseValidator=editCourseValidator;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showCourseForm (HttpServletRequest request,
					ModelMap model) {
	Course course = new Course();
	long course_id = ServletRequestUtils.getLongParameter(request, "course_id", 0);
	
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    model.addAttribute("course", course);
	    return new ModelAndView(decorateView(getFormView(), request));
	} 

	if (DataAccessFilter.filterDataResourceById(course_id, admin.getId()) == null) {
	    model.addAttribute("course", course);
	    return new ModelAndView(decorateView(getFormView(), request));
	}

	if (course_id != 0) {
	    course = courseDao.getCourseById(course_id);
	}

	model.addAttribute("course", course);
	return new ModelAndView(decorateView(getFormView(), request));
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit (@ModelAttribute("course") Course course,
				  BindingResult result,
				  HttpServletRequest request)
	throws Exception {
	
	courseValidator.validate(course, result);
	if (result.hasErrors()) {
	    return new ModelAndView(decorateView(getFormView(), request));
	}

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");


	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(course.getEventId(), admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(course, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}
       
	courseDao.updateCourse(course);
	return new ModelAndView(decorateView(getSuccessView(), request));
    }


}
