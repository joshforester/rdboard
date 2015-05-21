package com.myrunning.leaderboard.web;

/**
 *  File: ListAdminsController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/24
 *  Description: Controller object for listing Admins.
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.IdentityDao;
import com.myrunning.leaderboard.db.dao.ifacedao.PersonDao;
import com.myrunning.leaderboard.db.dao.ifacedao.AdminDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CompetitorDao;


@Controller
public class ListAdminsController {

    static Logger logger = Logger.getLogger(ListAdminsController.class);

    @Autowired
    private DataResourceDao dataResourceDao;

    @Autowired
    private IdentityDao identityDao;

    @Autowired 
    @Qualifier("personDao")
    private PersonDao personDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private CompetitorDao competitorDao;


    @RequestMapping(value="/listAdmins.htm", method = RequestMethod.GET)
    public String showForm(@RequestParam(value="admin_id", required=false) Integer aId, @RequestParam(value="email", required=false) String email, @RequestParam(value="username", required=false) String username, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	List<Admin> admins = new ArrayList<Admin>();
	if (aId != null) {
	    admins.add(adminDao.getAdminById(aId));
	} else if (email != null) {
	    admins.add(adminDao.getAdminByEmail(email));
	} else if (username != null) {
	    admins.add(adminDao.getAdminByUsername(username));
	} else {
	    admins = adminDao.getAll(true);
	}

	admins = (List<Admin>) DataAccessFilter.filterColById(admins, admin.getId());

	model.addAttribute("admins", admins);

	String view = ViewUtils.decideView(".listAdmins_view", request);
	return view;
    }


    @RequestMapping(value="/deleteAdmin.htm", method=RequestMethod.GET)
    public String deleteAdmin(@RequestParam(value="admin_id") Integer aId, HttpServletRequest request, ModelMap model) {

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	if (DataAccessFilter.filterDataResourceById(aId, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (aId != null) {
	    Admin a = adminDao.getAdminById(aId);
	    model.addAttribute("admin", a);
	}

	String view = ViewUtils.decideView(".deleteAdminConfirm_view", request);
	return view;
    }

    @RequestMapping(value="/deleteAdminConfirmed.htm", method=RequestMethod.POST)
    public String deleteAdminConfirmed(@ModelAttribute("admin") Admin a, BindingResult result, SessionStatus status, HttpServletRequest request) {

	// validate admin
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	if (DataAccessFilter.filterDataResourceById(a, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}

	// get full admin object
	a = adminDao.getAdminById(a.getId());

	// only deletes admin
	adminDao.deleteAdminById(a.getId());

	// remove underlying PERSON/DATA_RESOURCE if no 
	// admin/competitors associated with it
	if (competitorDao.getCompetitorsByPersonId(a.getId(), false).isEmpty()) {

	    logger.debug("no competitors associated, deleting person " + a.getId());

	    personDao.deletePersonById(a.getId());
	    dataResourceDao.deleteDataResourceById(a.getId());

	    // remove underlying IDENTITY/DATA_RESOURCE if no persons associated with it
	    if (personDao.getPersonsByIdentityId(a.getIdentityId(), null, false).isEmpty()) {
		
		logger.debug("no identities associated, deleting identity " + a.getIdentityId());
		
		identityDao.deleteIdentityById(a.getIdentityId());
		dataResourceDao.deleteDataResourceById(a.getIdentityId());

	    } else {
		logger.debug("persons associated with identity found.  not removing identity" + a.getIdentityId());
	    }

	} else {
	    logger.debug("competitors associated with person found.  not removing person." + a.getId());
	}

	String view = ViewUtils.decideView(".deleteAdminSuccess_dashboard", request);
	return view;
    }

}
