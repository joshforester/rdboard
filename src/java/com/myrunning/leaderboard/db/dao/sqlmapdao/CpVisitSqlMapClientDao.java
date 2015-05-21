package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: CpVisitSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/25
 *  Description: SqlMapDao for CpVisit.
 **/


import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Collections;
import java.sql.Timestamp;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.CpVisit;
import com.myrunning.leaderboard.model.DecoratedCpVisit;
import com.myrunning.leaderboard.model.util.CpVisitControlPointCpOrderComparator;
import com.myrunning.leaderboard.model.util.CpVisitTeamNumberComparator;
import com.myrunning.leaderboard.model.util.CpVisitDepartureComparator;
import com.myrunning.leaderboard.model.util.CpVisitLastModifiedComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.db.dao.ifacedao.CpVisitDao;
import com.myrunning.leaderboard.db.dao.query.TeamIdAndCpIdQuery;
import com.myrunning.leaderboard.db.dao.query.TeamIdAndRangeQuery;
import com.myrunning.leaderboard.db.dao.query.CourseIdAndRangeQuery;




public class CpVisitSqlMapClientDao extends SqlMapClientDaoSupport implements CpVisitDao {

    static Logger logger = Logger.getLogger(CpVisitSqlMapClientDao.class);

    private TeamDao teamDao;
    private CourseDao courseDao;
    private ControlPointDao controlPointDao;


    private void sortCpVisits(List<CpVisit> cpVisits, String order) {
	
	if (!cpVisits.isEmpty()) {
	
	    if (order != null) {
		if (order.equals("lastmodified")) {
		    Collections.sort(cpVisits, new CpVisitLastModifiedComparator());
		} else if (order.equals("departure")) {
		    Collections.sort(cpVisits, new CpVisitDepartureComparator());
		} else if (order.equals("cporder")) {

		    Map<Long, ControlPoint> cpMap = new HashMap<Long, ControlPoint>();
		    ListIterator<CpVisit> cpvli = cpVisits.listIterator();
		    CpVisit cpVisit = cpvli.next();
		    Course course = courseDao.getCourseByTeamId(cpVisit.getTeamId());
		    List<ControlPoint> controlPoints = controlPointDao.getControlPointsByCourseId(course.getId(), null);
		    ListIterator<ControlPoint> cpli = controlPoints.listIterator();
		    while (cpli.hasNext()) {
			ControlPoint cp = cpli.next();
			cpMap.put(cp.getId(), cp);
		    }
		    
		    Collections.sort(cpVisits, new CpVisitControlPointCpOrderComparator(cpMap));

		} else if (order.equals("teamnumber")) {

		    Map<Long, Team> teamMap = new HashMap<Long, Team>();
		    ListIterator<CpVisit> cpvli = cpVisits.listIterator();
		    CpVisit cpVisit = cpvli.next();
		    Course course = courseDao.getCourseByTeamId(cpVisit.getTeamId());
		    List<Team> teams = teamDao.getTeamsByCourseId(course.getId(), null);
		    ListIterator<Team> tli = teams.listIterator();
		    while (tli.hasNext()) {
			Team team = tli.next();
			teamMap.put(team.getId(), team);
		    }

		    Collections.sort(cpVisits, new CpVisitTeamNumberComparator(teamMap));

		} else {
		    Collections.sort(cpVisits, new CpVisitDepartureComparator());
		}
	    } else {
		Collections.sort(cpVisits, new CpVisitDepartureComparator());
	    }

	}
    }


    public void setTeamDao(TeamDao teamDao) {
	this.teamDao=teamDao;
    }

    public void setCourseDao(CourseDao courseDao) {
	this.courseDao=courseDao;
    }

    public void setControlPointDao(ControlPointDao controlPointDao) {
	this.controlPointDao=controlPointDao;
    }


    public CpVisitSqlMapClientDao() {
	// empty
    }

    public CpVisit getCpVisitByTeamIdAndCpId(String teamId, String cpId) {
	TeamIdAndCpIdQuery query = new TeamIdAndCpIdQuery();
	query.setTeamId(Long.parseLong(teamId));
	query.setCpId(Long.parseLong(cpId));	
	return (CpVisit) getSqlMapClientTemplate().queryForObject("getCpVisitByTeamIdAndCpId", query);
    }

    public DecoratedCpVisit getDecoratedCpVisitByTeamIdAndCpId(String teamId, String cpId) {
	TeamIdAndCpIdQuery query = new TeamIdAndCpIdQuery();
	query.setTeamId(Long.parseLong(teamId));
	query.setCpId(Long.parseLong(cpId));	
	return (DecoratedCpVisit) getSqlMapClientTemplate().queryForObject("getDecoratedCpVisitByTeamIdAndCpId", query);
    }

    public List getCpVisitsByTeamId(String id, 
				    Timestamp start, 
				    Timestamp end, 
				    String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    TeamIdAndRangeQuery query = new TeamIdAndRangeQuery();
	    query.setId(Long.parseLong(id));
	    query.setStart(start);
	    query.setEnd(end);
	    List<CpVisit> cpVisits = getSqlMapClientTemplate().queryForList("getCpVisitsByTeamId", query);
	    sortCpVisits(cpVisits, order);
	    return cpVisits;
	}
    }

    public List getDecoratedCpVisitsByTeamId(String id, 
					     Timestamp start, 
					     Timestamp end) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    TeamIdAndRangeQuery query = new TeamIdAndRangeQuery();
	    query.setId(Long.parseLong(id));
	    query.setStart(start);
	    query.setEnd(end);
	    List<HashMap> cpVisits = getSqlMapClientTemplate().queryForList("getDecoratedCpVisitsByTeamId", query);
	    return cpVisits;
	}
    }

    public List getCpVisitsByCourseId(String id, 
				      Timestamp start,
				      Timestamp end,
				      String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    CourseIdAndRangeQuery query = new CourseIdAndRangeQuery();
	    query.setId(Long.parseLong(id));
	    query.setStart(start);
	    query.setEnd(end);
	    List<CpVisit> cpVisits = getSqlMapClientTemplate().queryForList("getCpVisitsByCourseId", query);
	    sortCpVisits(cpVisits, order);
	    return cpVisits;
	}
    }

    public List getDecoratedCpVisitsByCourseId(String id, 
					       Timestamp start,
					       Timestamp end) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    CourseIdAndRangeQuery query = new CourseIdAndRangeQuery();
	    query.setId(Long.parseLong(id));
	    query.setStart(start);
	    query.setEnd(end);
	    List<HashMap> cpVisits = getSqlMapClientTemplate().queryForList("getDecoratedCpVisitsByCourseId", query);
	    return cpVisits;
	}
    }

    public List getCpVisitsByCpId(String id, String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    List<CpVisit> cpVisits = getSqlMapClientTemplate().queryForList("getCpVisitsByCpId", id);
	    sortCpVisits(cpVisits, order);
	    return cpVisits;
	}
    }

    public void insertCpVisit(CpVisit cpVisit) {
	cpVisit.setLastModified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	getSqlMapClientTemplate().update("insertCpVisit", cpVisit);
    }

    public void updateCpVisit(CpVisit cpVisit) {
	cpVisit.setLastModified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	getSqlMapClientTemplate().update("updateCpVisit", cpVisit);
    }

    public void deleteCpVisitByTeamIdAndCpId(String teamId, String cpId) {
	TeamIdAndCpIdQuery query = new TeamIdAndCpIdQuery();
	query.setTeamId(Long.parseLong(teamId));
	query.setCpId(Long.parseLong(cpId));	
	getSqlMapClientTemplate().update("deleteCpVisitByTeamIdAndCpId", query);
    }

    public static CpVisit decorate(CpVisit cpVisit) {

      return cpVisit;

    }


}// END OF CLASS CpVisitSqlMapClientDao
