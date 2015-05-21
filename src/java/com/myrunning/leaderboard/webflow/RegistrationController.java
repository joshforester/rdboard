package com.myrunning.leaderboard.webflow;

/**
 *  File: RegistrationController.java
 *  Author:  Joshua Forester
 *  Date: 2009/09/01
 *  Description: Controller for the Add Registration flow.
 **/



import java.util.List;
import java.util.ListIterator;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.webflow.context.ExternalContextHolder;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Competitor;
import com.myrunning.leaderboard.model.Registration;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.model.TeamInstanceRel;
import com.myrunning.leaderboard.services.ifacesvc.RegistrationService;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CompetitorDao;
import com.myrunning.leaderboard.db.dao.ifacedao.PersonDao;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;
import com.myrunning.leaderboard.db.dao.ifacedao.LastInsertIdDao;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamInstanceRelDao;


@Controller
public class RegistrationController {

    static Logger logger = Logger.getLogger(RegistrationController.class);
    
    @Autowired
    private EventDao eventDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    @Qualifier("personDao")
    private PersonDao personDao;

    @Autowired
    private CompetitorDao competitorDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    @Qualifier("lastInsertIdDao")
    private LastInsertIdDao lastInsertIdDao;

    @Autowired
    private TeamInstanceRelDao teamInstanceRelDao;

    @Autowired
    private RegistrationService registrationService;
    

    /*
     *  Creates a new instance of Registration.
     */
    public Registration newRegistration() {
	return new Registration();
    }


    /*
     *  Persists the registration bean.
     */
    public void saveRegistration(Registration registration) 
	throws Exception {

	Admin admin = (Admin) ExternalContextHolder.getExternalContext().getSessionMap().get(Constants.ADMIN_SESSION_KEY);

	/*
	 *  Need to figure out validation before this can change!!!!
	 */
	/*
	if (admin == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	
	if (DataAccessFilter.filterDataResourceById(course, admin.getId()) == null) {
	    return Constants.OWNERSHIP_ERROR;
	}
	*/

	registrationService.saveRegistration(registration, admin);
    }


    public Registration getRegistrationByTeamId(long teamId) {
	
	Registration registration = new Registration();
	Team team = teamDao.getTeamById(teamId);
	
	if (team == null) {
	    return registration;
	}
	
	registration.setTeamId(teamId);
	registration.setTeamName(team.getName());	
	registration.setCourseId(team.getCourseId());
	registration.setDivisionId(team.getDivisionId());

	Course course = courseDao.getCourseById(team.getCourseId());

	registration.setEventId(course.getEventId());

	List<TeamInstanceRel> teamInstanceRels = teamInstanceRelDao.getTeamInstanceRelsByTeamId(teamId);
	ListIterator<TeamInstanceRel> tirli = teamInstanceRels.listIterator();
	if (tirli.hasNext()) {
	    TeamInstanceRel teamInstanceRel = tirli.next();
	    Competitor competitor = competitorDao.getCompetitorById(teamInstanceRel.getCompetitorId());
	    registration.setMember1Id(competitor.getPersonId());
	    registration.setMember1EmergencyContactFirstName(competitor.getEmergencyContactFirstName());
	    registration.setMember1EmergencyContactLastName(competitor.getEmergencyContactLastName());
	    registration.setMember1EmergencyContactRelation(competitor.getEmergencyContactRelation());
	    registration.setMember1EmergencyPhone(competitor.getEmergencyPhone());
	    registration.setMember1ShirtSize(competitor.getShirtSize());
	    registration.setMember1ShoeSize(competitor.getShoeSize());
	}

	if (tirli.hasNext()) {
	    TeamInstanceRel teamInstanceRel = tirli.next();
	    Competitor competitor = competitorDao.getCompetitorById(teamInstanceRel.getCompetitorId());
	    registration.setMember2Id(competitor.getPersonId());
	    registration.setMember2EmergencyContactFirstName(competitor.getEmergencyContactFirstName());
	    registration.setMember2EmergencyContactLastName(competitor.getEmergencyContactLastName());
	    registration.setMember2EmergencyContactRelation(competitor.getEmergencyContactRelation());
	    registration.setMember2EmergencyPhone(competitor.getEmergencyPhone());
	    registration.setMember2ShirtSize(competitor.getShirtSize());
	    registration.setMember2ShoeSize(competitor.getShoeSize());
	}

	if (tirli.hasNext()) {
	    TeamInstanceRel teamInstanceRel = tirli.next();
	    Competitor competitor = competitorDao.getCompetitorById(teamInstanceRel.getCompetitorId());
	    registration.setMember3Id(competitor.getPersonId());
	    registration.setMember3EmergencyContactFirstName(competitor.getEmergencyContactFirstName());
	    registration.setMember3EmergencyContactLastName(competitor.getEmergencyContactLastName());
	    registration.setMember3EmergencyContactRelation(competitor.getEmergencyContactRelation());
	    registration.setMember3EmergencyPhone(competitor.getEmergencyPhone());
	    registration.setMember3ShirtSize(competitor.getShirtSize());
	    registration.setMember3ShoeSize(competitor.getShoeSize());
	}

	if (tirli.hasNext()) {
	    TeamInstanceRel teamInstanceRel = tirli.next();
	    Competitor competitor = competitorDao.getCompetitorById(teamInstanceRel.getCompetitorId());
	    registration.setMember4Id(competitor.getPersonId());
	    registration.setMember4EmergencyContactFirstName(competitor.getEmergencyContactFirstName());
	    registration.setMember4EmergencyContactLastName(competitor.getEmergencyContactLastName());
	    registration.setMember4EmergencyContactRelation(competitor.getEmergencyContactRelation());
	    registration.setMember4EmergencyPhone(competitor.getEmergencyPhone());
	    registration.setMember4ShirtSize(competitor.getShirtSize());
	    registration.setMember4ShoeSize(competitor.getShoeSize());
	}
	    
	return registration;
    }


    /*
     *  This belongs in Registration bean class.  Placing here 
     *  temporarily until Registration class is stable. 
     */
    public void populateMembers(Registration registration) {

	// set registration competitor values based on person defaults
	// really just need the person object here, not really the competitor
	// so grabbing first object in list

	if (registration.getMember1Id() != 0) {
	    Competitor competitor = (Competitor) competitorDao.getCompetitorByPersonIdAndCourseId(registration.getMember1Id(), registration.getCourseId());
	    if (competitor != null) {

		registration.setMember1EmergencyContactFirstName(competitor.getDefaultEmergencyContactFirstName());
		registration.setMember1EmergencyContactLastName(competitor.getDefaultEmergencyContactLastName());
		registration.setMember1EmergencyContactRelation(competitor.getDefaultEmergencyContactRelation());
		registration.setMember1EmergencyPhone(competitor.getDefaultEmergencyPhone());
		registration.setMember1ShirtSize(competitor.getDefaultShirtSize());
		registration.setMember1ShoeSize(competitor.getDefaultShoeSize());

	    } else {
	        Person person = personDao.getPersonById(registration.getMember1Id());

		registration.setMember1EmergencyContactFirstName(person.getDefaultEmergencyContactFirstName());
		registration.setMember1EmergencyContactLastName(person.getDefaultEmergencyContactLastName());
		registration.setMember1EmergencyContactRelation(person.getDefaultEmergencyContactRelation());
		registration.setMember1EmergencyPhone(person.getDefaultEmergencyPhone());
		registration.setMember1ShirtSize(person.getDefaultShirtSize());
		registration.setMember1ShoeSize(person.getDefaultShoeSize());
	    }
	}

	if (registration.getMember2Id() != 0) {
	    Competitor competitor = (Competitor) competitorDao.getCompetitorByPersonIdAndCourseId(registration.getMember2Id(), registration.getCourseId());
	    if (competitor != null) {
		registration.setMember2EmergencyContactFirstName(competitor.getDefaultEmergencyContactFirstName());
		registration.setMember2EmergencyContactLastName(competitor.getDefaultEmergencyContactLastName());
		registration.setMember2EmergencyContactRelation(competitor.getDefaultEmergencyContactRelation());
		registration.setMember2EmergencyPhone(competitor.getDefaultEmergencyPhone());
		registration.setMember2ShirtSize(competitor.getDefaultShirtSize());
		registration.setMember2ShoeSize(competitor.getDefaultShoeSize());
	    } else {
		Person person = personDao.getPersonById(registration.getMember2Id());

		registration.setMember2EmergencyContactFirstName(person.getDefaultEmergencyContactFirstName());
		registration.setMember2EmergencyContactLastName(person.getDefaultEmergencyContactLastName());
		registration.setMember2EmergencyContactRelation(person.getDefaultEmergencyContactRelation());
		registration.setMember2EmergencyPhone(person.getDefaultEmergencyPhone());
		registration.setMember2ShirtSize(person.getDefaultShirtSize());
		registration.setMember2ShoeSize(person.getDefaultShoeSize());
	    }
	}

	if (registration.getMember3Id() != 0) {
	    Competitor competitor = (Competitor) competitorDao.getCompetitorByPersonIdAndCourseId(registration.getMember3Id(), registration.getCourseId());
	    if (competitor != null) {
		registration.setMember3EmergencyContactFirstName(competitor.getDefaultEmergencyContactFirstName());
		registration.setMember3EmergencyContactLastName(competitor.getDefaultEmergencyContactLastName());
		registration.setMember3EmergencyContactRelation(competitor.getDefaultEmergencyContactRelation());
		registration.setMember3EmergencyPhone(competitor.getDefaultEmergencyPhone());
		registration.setMember3ShirtSize(competitor.getDefaultShirtSize());
		registration.setMember3ShoeSize(competitor.getDefaultShoeSize());
	    } else {
		Person person = personDao.getPersonById(registration.getMember3Id());
		registration.setMember3EmergencyContactFirstName(person.getDefaultEmergencyContactFirstName());
		registration.setMember3EmergencyContactLastName(person.getDefaultEmergencyContactLastName());
		registration.setMember3EmergencyContactRelation(person.getDefaultEmergencyContactRelation());
		registration.setMember3EmergencyPhone(person.getDefaultEmergencyPhone());
		registration.setMember3ShirtSize(person.getDefaultShirtSize());
		registration.setMember3ShoeSize(person.getDefaultShoeSize());
	    }

	}

	if (registration.getMember4Id() != 0) {
	    Competitor competitor = (Competitor) competitorDao.getCompetitorByPersonIdAndCourseId(registration.getMember4Id(), registration.getCourseId());
	    if (competitor != null) {
		registration.setMember4EmergencyContactFirstName(competitor.getDefaultEmergencyContactFirstName());
		registration.setMember4EmergencyContactLastName(competitor.getDefaultEmergencyContactLastName());
		registration.setMember4EmergencyContactRelation(competitor.getDefaultEmergencyContactRelation());
		registration.setMember4EmergencyPhone(competitor.getDefaultEmergencyPhone());
		registration.setMember4ShirtSize(competitor.getDefaultShirtSize());
		registration.setMember4ShoeSize(competitor.getDefaultShoeSize());
	    } else {
		Person person = personDao.getPersonById(registration.getMember4Id());
		registration.setMember4EmergencyContactFirstName(person.getDefaultEmergencyContactFirstName());
		registration.setMember4EmergencyContactLastName(person.getDefaultEmergencyContactLastName());
		registration.setMember4EmergencyContactRelation(person.getDefaultEmergencyContactRelation());
		registration.setMember4EmergencyPhone(person.getDefaultEmergencyPhone());
		registration.setMember4ShirtSize(person.getDefaultShirtSize());
		registration.setMember4ShoeSize(person.getDefaultShoeSize());
	    
	    }

	}
    }

}