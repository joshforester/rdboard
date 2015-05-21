package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: CompetitorSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/21
 *  Description: SqlMapDao for Competitor.
 **/


import java.util.List;
import java.util.ListIterator;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.util.StringUtils;
import com.myrunning.leaderboard.model.Competitor;
import com.myrunning.leaderboard.db.dao.ifacedao.CompetitorDao;
import com.myrunning.leaderboard.db.dao.query.PersonIdAndCourseIdQuery;


public class CompetitorSqlMapClientDao extends PersonSqlMapClientDao implements CompetitorDao {

    static Logger logger = Logger.getLogger(CompetitorSqlMapClientDao.class);


    public CompetitorSqlMapClientDao() {
	// empty
    }

    public Competitor getCompetitorById(String id) {
	return decorate((Competitor) getSqlMapClientTemplate().queryForObject("getCompetitorById", id));
    }

    public Competitor getCompetitorById(long id) {
	return getCompetitorById(String.valueOf(id));
    }

    public Competitor getCompetitorByEmail(String email) {
	return decorate((Competitor) getSqlMapClientTemplate().queryForObject("getCompetitorByEmail", email));
    }

    public Competitor getCompetitorByPersonIdAndCourseId(String personId, String courseId) {
	PersonIdAndCourseIdQuery query = new PersonIdAndCourseIdQuery();
	query.setPersonId(Long.parseLong(personId));
	query.setCourseId(Long.parseLong(courseId));
	return decorate((Competitor) getSqlMapClientTemplate().queryForObject("getCompetitorByPersonIdAndCourseId", query));
    }

    public Competitor getCompetitorByPersonIdAndCourseId(long personId, String courseId) {
	return getCompetitorByPersonIdAndCourseId(String.valueOf(personId), courseId);
    }

    public Competitor getCompetitorByPersonIdAndCourseId(String personId, long courseId) {
	return getCompetitorByPersonIdAndCourseId(personId, String.valueOf(courseId));
    }

    public Competitor getCompetitorByPersonIdAndCourseId(long personId, long courseId) {
	return getCompetitorByPersonIdAndCourseId(String.valueOf(personId), String.valueOf(courseId));
    }

    public Competitor getCompetitorByTraits(Competitor competitor) {
	if ((competitor.getFirstName() == null) ||
	    (! StringUtils.hasText(competitor.getFirstName())) ||
	    (competitor.getLastName() == null) ||
	    (! StringUtils.hasText(competitor.getLastName())) ||
	    (competitor.getCity() == null) ||
	    (! StringUtils.hasText(competitor.getCity())) ||
	    (competitor.getRegion() == null) ||
	    (! StringUtils.hasText(competitor.getRegion())) ||
	    (competitor.getCountry() == null) ||
	    (! StringUtils.hasText(competitor.getCountry())) ||
	    (competitor.getZip() == 0) ||
	    (! StringUtils.hasText(String.valueOf(competitor.getZip()))) ||
	    (competitor.getGender() == null) ||
	    (! StringUtils.hasText(competitor.getGender())) ||
	    (competitor.getBirthday() == null) ||
	    (! StringUtils.hasText(competitor.getBirthday().toString()))) {
	    throw new IllegalArgumentException();
	} else {
	    return decorate((Competitor) getSqlMapClientTemplate().queryForObject("getCompetitorByTraits", competitor));
	}
    }

    public List getCompetitorsByPersonId(String personId, boolean decorate) {
	if (decorate) {
	    return decorate(getSqlMapClientTemplate().queryForList("getCompetitorsByPersonId", personId));
	} else {
	    return getSqlMapClientTemplate().queryForList("getCompetitorsByPersonId", personId);
	}
    }

    public List getCompetitorsByPersonId(long personId, boolean decorate) {
	return getCompetitorsByPersonId(String.valueOf(personId), decorate);
    }

    public List getAll(boolean decorate) {
	if (decorate) {
	    return decorate(getSqlMapClientTemplate().queryForList("getAllCompetitors"));
	} else {
	    return getSqlMapClientTemplate().queryForList("getAllCompetitors");
	}
    }

    public List getCompetitorsByTeamId(String id, boolean decorate) {
	if (decorate) {
	    return decorate(getSqlMapClientTemplate().queryForList("getCompetitorsByTeamId", id));
	} else {
	    return getSqlMapClientTemplate().queryForList("getCompetitorsByTeamId", id);
	}
    }

    public List getCompetitorsByTeamId(long id, boolean decorate) {
	return getCompetitorsByTeamId(String.valueOf(id), decorate);
    }

    public void insertCompetitor(Competitor competitor) {
	competitor.setDefaults();
	insertPerson(competitor);
	competitor.setPersonId(getLastInsertId());
	getSqlMapClientTemplate().update("insertCompetitor", competitor);
    }

    public void attachCompetitor(Competitor competitor) {
	getSqlMapClientTemplate().update("insertCompetitor", competitor);
    }

    public void updateCompetitor(Competitor competitor) {
	// should this exist?  permissions issues may mean that a person object
	// is updated by a race director?
	competitor.setDefaults();
	
	// since we are updating, we don't want to update the person
	// with the competitor's id.  so save state and restore after
	// person update for competitor update
	long competitorId = competitor.getId();
	competitor.setId(competitor.getPersonId());
	updatePerson(competitor);
	competitor.setId(competitorId);

	getSqlMapClientTemplate().update("updateCompetitor", competitor);
    }

    public void updateAttachedCompetitor(Competitor competitor) {
	getSqlMapClientTemplate().update("updateCompetitor", competitor);
    }

    public void deleteCompetitorById(String id) {
	// delete only the competitor here, not the associated PERSON object also
	getSqlMapClientTemplate().update("deleteCompetitorById", id);
    }

    public void deleteCompetitorById(long id) {
	deleteCompetitorById(String.valueOf(id));
    }

    public static List decorate(List list) {

	ListIterator<Competitor> li = list.listIterator();
	Competitor competitor = null;
	while (li.hasNext()) {
	    competitor = li.next();
	    competitor = decorate(competitor);
	}

	return list;
    }

    public static Competitor decorate(Competitor competitor) {

	if (competitor == null) {
	    return competitor;
	}

	long id = competitor.getId();
	competitor.setId(competitor.getPersonId());
	competitor = (Competitor) PersonSqlMapClientDao.decorate(competitor);
	competitor.setId(id);

	return competitor;
    }


}// END OF CLASS CompetitorSqlMapClientDao
