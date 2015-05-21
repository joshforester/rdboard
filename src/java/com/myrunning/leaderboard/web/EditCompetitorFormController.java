package com.myrunning.leaderboard.web;

/**
 *  File: EditCompetitorFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/23
 *  Description: Controller object for the competitor app.
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
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.DataResource;
import com.myrunning.leaderboard.model.Identity;
import com.myrunning.leaderboard.model.Competitor;
import com.myrunning.leaderboard.model.stats.DataCorrelator;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.ifacedao.LastInsertIdDao;
import com.myrunning.leaderboard.db.dao.ifacedao.IdentityDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CompetitorDao;
import com.myrunning.leaderboard.db.dao.ifacedao.PersonDao;



public class EditCompetitorFormController extends CompetitorFormController {

    static Logger logger = Logger.getLogger(EditCompetitorFormController.class);

    private DataResourceDao dataResourceDao;
    private LastInsertIdDao lastInsertIdDao;
    private IdentityDao identityDao;
    private CompetitorDao competitorDao;
    private PersonDao personDao;


    public EditCompetitorFormController() {
	// empty
    }


    protected Object formBackingObject (HttpServletRequest request)
	throws Exception {
	Competitor competitor = new Competitor();
	long competitor_id = ServletRequestUtils.getLongParameter(request, "competitor_id", 0);

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return competitor;
	} 

	if (DataAccessFilter.filterDataResourceById(competitor_id, admin.getId()) == null) {
	    return competitor;
	}

	if (competitor_id != 0) {
	    WebApplicationContext wac = getWebApplicationContext();
	    competitorDao = (CompetitorDao) wac.getBean("competitorDao");
	    competitor = competitorDao.getCompetitorById(competitor_id);
	}

	return competitor;
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
	competitorDao = (CompetitorDao) wac.getBean("competitorDao");
	personDao = (PersonDao) wac.getBean("personDao");

	Competitor competitor = (Competitor) command;

	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
	if (admin == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}

	if (DataAccessFilter.filterDataResourceById(competitor, admin.getId()) == null) {
	    return new ModelAndView(Constants.OWNERSHIP_ERROR);
	}
       
	// this is where we need to ensure that someone isn't attempting
	// to escalate privileges by editing an admin's competitor object
	// and adding roles to it.  get the existing persons roles, add them
	// here.  they should be preserved.  not added to.
	Competitor existingCompetitor = competitorDao.getCompetitorById(competitor.getId());
	competitor.setIdentityId(existingCompetitor.getIdentityId());
	competitor.setAuthorities(existingCompetitor.getAuthorities());
	
	// set identity here, adding one (and associated DATA_RESOURCE
	// if it doesn't exist.  this means doing vetting to ensure it 
	// is a duplicate if it does exist.  this is global and should 
	// go beyond the scope of the RD's data, whereas validation 
	// should only be scoped for the RD's data.
	Identity identity = DataCorrelator.correlateIdentity(competitor);
	long identityId = competitor.getIdentityId();
	boolean identityChanged = false;
	if (identity == null) {

	    identity = new Identity();
	    DataResource dataResource = new DataResource();
	    dataResource.setAdminId(admin.getId());
	    dataResourceDao.insertDataResource(dataResource);
	    identity.setId(lastInsertIdDao.getLastInsertId());
	    identityDao.insertIdentity(identity);
	    competitor.setIdentityId(identity.getId());

	    identityChanged = true;

	} else if (identity.getId() != identityId) {

	    competitor.setIdentityId(identity.getId());

	    identityChanged = true;
	
	}


	competitorDao.updateCompetitor(competitor);


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
