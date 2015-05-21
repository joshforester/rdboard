package com.myrunning.leaderboard.web;

/**
 *  File: EditAdminFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Controller object for the admin app.
 **/


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.validation.BindException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.security.PasswordFilter;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.DataResource;
import com.myrunning.leaderboard.model.Identity;
import com.myrunning.leaderboard.model.Competitor;
import com.myrunning.leaderboard.model.stats.DataCorrelator;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.LastInsertIdDao;
import com.myrunning.leaderboard.db.dao.ifacedao.IdentityDao;
import com.myrunning.leaderboard.db.dao.ifacedao.AdminDao;
import com.myrunning.leaderboard.db.dao.ifacedao.PersonDao;


public class EditAdminFormController extends AdminFormController {

    static Logger logger = Logger.getLogger(EditAdminFormController.class);

    private DataResourceDao dataResourceDao;
    private LastInsertIdDao lastInsertIdDao;
    private IdentityDao identityDao;
    private AdminDao adminDao;
    private PersonDao personDao;


    public EditAdminFormController() {
	// empty
    }


    protected Object formBackingObject (HttpServletRequest request)
	throws Exception {
	Admin a = new Admin();
	long a_id = ServletRequestUtils.getLongParameter(request, "admin_id", 0);

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return a;
	} 

	if (DataAccessFilter.filterDataResourceById(a_id, admin.getId()) == null) {
	    return a;
	}

	if (a_id != 0) {
	    WebApplicationContext wac = getWebApplicationContext();
	    adminDao = (AdminDao) wac.getBean("adminDao");
	    a = adminDao.getAdminById(a_id);
	}

	return a;
    }
    
    

    public ModelAndView onSubmit (HttpServletRequest request, 
				  HttpServletResponse response, 
				  Object command, 
				  BindException errors) 
	throws Exception {

	MDC.put("Session", request.getSession().getId());
	logger.debug("handling request");

	WebApplicationContext wac = getWebApplicationContext();
	dataResourceDao = (DataResourceDao) wac.getBean("dataResourceDao");
	lastInsertIdDao = (LastInsertIdDao) wac.getBean("lastInsertIdDao");
	identityDao = (IdentityDao) wac.getBean("identityDao");
	personDao = (PersonDao) wac.getBean("personDao");
	adminDao = (AdminDao) wac.getBean("adminDao");

	Admin a = (Admin) command;

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(a, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}
       
	// set identity here, adding one (and associated DATA_RESOURCE
	// if it doesn't exist.  this means doing vetting to ensure it 
	// is a duplicate if it does exist.  this is global and should 
	// go beyond the scope of the RD's data, whereas validation 
	// should only be scoped for the RD's data.
	Identity identity = DataCorrelator.correlateIdentity(a);
	long identityId = a.getIdentityId();
	boolean identityChanged = false;
	if (identity == null) {

	    identity = new Identity();
	    DataResource dataResource = new DataResource();
	    dataResource.setAdminId(admin.getId());
	    dataResourceDao.insertDataResource(dataResource);
	    identity.setId(lastInsertIdDao.getLastInsertId());
	    identityDao.insertIdentity(identity);
	    a.setIdentityId(identity.getId());

	    identityChanged = true;

	} else if (identity.getId() != identityId) {

	    a.setIdentityId(identity.getId());

	    identityChanged = true;
	
	}


	a.setPassword(PasswordFilter.hash(a.getPassword()));
	adminDao.updateAdmin(a);


	// now get rid of old identity if it is orphaned
	if (identityChanged) {

	    if (personDao.getPersonsByIdentityId(identityId, null, false).isEmpty()) {
		identityDao.deleteIdentityById(identityId);
		dataResourceDao.deleteDataResourceById(identityId);
	    }
		
	}


	return new ModelAndView(this.getSuccessView());
    }


}
