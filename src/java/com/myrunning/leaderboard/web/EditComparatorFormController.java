package com.myrunning.leaderboard.web;

/**
 *  File: EditComparatorFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/26
 *  Description: Controller object for the comparator app.
 **/


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.BindException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Comparator;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.ComparatorDao;



public class EditComparatorFormController extends ComparatorFormController {

    static Logger logger = Logger.getLogger(EditComparatorFormController.class);

    private ComparatorDao comparatorDao;


    public EditComparatorFormController() {
	// empty
    }


    protected Object formBackingObject (HttpServletRequest request)
	throws Exception {
	Comparator comparator = new Comparator();
	long comparator_id = ServletRequestUtils.getLongParameter(request, "comparator_id", 0);

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return comparator;
	}

	if (DataAccessFilter.filterDataResourceById(comparator_id, admin.getId()) == null) {
	    return comparator;
	}
	
	if (comparator_id != 0) {
	    WebApplicationContext wac = getWebApplicationContext();
	    comparatorDao = (ComparatorDao) wac.getBean("comparatorDao");
	    comparator = comparatorDao.getComparatorById(comparator_id);
	}

	return comparator;
    }
    
    

    public ModelAndView onSubmit (HttpServletRequest request, 
				  HttpServletResponse response, 
				  Object command, 
				  BindException errors) 
	throws Exception {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	WebApplicationContext wac = getWebApplicationContext();
	comparatorDao = (ComparatorDao) wac.getBean("comparatorDao");
	
	Comparator comparator = (Comparator) command;
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	} 
	
	if (DataAccessFilter.filterDataResourceById(comparator.getCourseId(), admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(comparator, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}
	
	comparatorDao.updateComparator(comparator);
	return new ModelAndView(this.getSuccessView());
    }


}
