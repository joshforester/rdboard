package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: TeamSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/13
 *  Description: SqlMapDao for Team.
 **/


import java.util.List;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Team;
import com.myrunning.leaderboard.model.util.TeamNameComparator;
import com.myrunning.leaderboard.model.util.TeamNumberComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamDao;
import com.myrunning.leaderboard.db.dao.query.CourseIdAndDivisionIdQuery;
import com.myrunning.leaderboard.db.dao.query.CourseIdAndNameQuery;
import com.myrunning.leaderboard.db.dao.query.DivisionIdAndNameQuery;
import com.myrunning.leaderboard.db.dao.query.RegistrationQuery;


public class TeamSqlMapClientDao extends SqlMapClientDaoSupport implements TeamDao {

    static Logger logger = Logger.getLogger(TeamSqlMapClientDao.class);


    private void sortTeams(List<Team> teams, String order) {
	if (order != null) {
	    if (order.equals("name")) {
		Collections.sort(teams, new TeamNameComparator());
	    } else if (order.equals("number")) {
		Collections.sort(teams, new TeamNumberComparator());
	    } else {
		Collections.sort(teams, new TeamNumberComparator());
	    }
	} else {
	    Collections.sort(teams, new TeamNumberComparator());
	}
    }


    public TeamSqlMapClientDao() {
	// empty
    }

    public Team getTeamById(String id) {
	return (Team) getSqlMapClientTemplate().queryForObject("getTeamById", id);
    }

    public Team getTeamById(long id) {
	return getTeamById(String.valueOf(id));
    }

    public Team getTeamByCourseIdAndName(String id, String name) {
	CourseIdAndNameQuery query = new CourseIdAndNameQuery();
	query.setCourseId(Long.parseLong(id));
	query.setName(name);
	return (Team) getSqlMapClientTemplate().queryForObject("getTeamByCourseIdAndName", query);
    }

    public Team getTeamByCourseIdAndName(long id, String name) {
	return this.getTeamByCourseIdAndName(String.valueOf(id), name);
    }

    public Team getTeamByDivisionIdAndName(String id, String name) {
	DivisionIdAndNameQuery query = new DivisionIdAndNameQuery();
	query.setDivisionId(Long.parseLong(id));
	query.setName(name);
	return (Team) getSqlMapClientTemplate().queryForObject("getTeamByDivisionIdAndName", query);
    }

    public Team getTeamByDivisionIdAndName(long id, String name) {
	return this.getTeamByDivisionIdAndName(String.valueOf(id), name);
    }

    public int getRegistrationMemberCount(long courseId, 
					  long member1Id, 
					  long member2Id, 
					  long member3Id, 
					  long member4Id) {
	RegistrationQuery query = new RegistrationQuery();
	query.setCourseId(courseId);
	query.setMember1Id(member1Id);
	query.setMember2Id(member2Id);
	query.setMember3Id(member3Id);
	query.setMember4Id(member4Id);
	return ((Integer) getSqlMapClientTemplate().queryForObject("getRegistrationMemberCount", query)).intValue();
    }

    public List getTeamsByCourseId(String id, String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    List<Team> teams = getSqlMapClientTemplate().queryForList("getTeamsByCourseId", id);
	    sortTeams(teams, order);
	    return teams;
	}
    }

    public List getTeamsByCourseId(long id, String order) {
	return this.getTeamsByCourseId(String.valueOf(id), order);
    }

    public List getTeamsByDivisionId(String id, String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    List<Team> teams = getSqlMapClientTemplate().queryForList("getTeamsByDivisionId", id);
	    sortTeams(teams, order);
	    return teams;
	}
    }

    public List getTeamsByDivisionId(long id, String order) {
	return this.getTeamsByDivisionId(String.valueOf(id), order);
    }

    public List getTeamsByCourseIdAndDivisionId(String courseId, String divisionId, String order) {
	if ((courseId == null) || (courseId.equals("0"))) {
	    return getTeamsByDivisionId(divisionId, order);
	} else if ((divisionId == null) || (divisionId.equals("0"))) {
	    return getTeamsByCourseId(courseId, order);
	} else {
	    CourseIdAndDivisionIdQuery query = new CourseIdAndDivisionIdQuery();
	    query.setCourseId(Long.parseLong(courseId));
	    query.setDivisionId(Long.parseLong(divisionId));
	    List<Team> teams = getSqlMapClientTemplate().queryForList("getTeamsByCourseIdAndDivisionId", query);
	    sortTeams(teams, order);
	    return teams;
	}
    }

    public List getTeamsByCourseIdAndDivisionId(long courseId, String divisionId, String order) {
	return this.getTeamsByCourseIdAndDivisionId(String.valueOf(courseId), divisionId, order);
    }

    public List getTeamsByCourseIdAndDivisionId(String courseId, long divisionId, String order) {
	return this.getTeamsByCourseIdAndDivisionId(courseId, String.valueOf(divisionId), order);
    }

    public List getTeamsByCourseIdAndDivisionId(long courseId, long divisionId, String order) {
	return this.getTeamsByCourseIdAndDivisionId(String.valueOf(courseId), String.valueOf(divisionId), order);
    }

    public int getMaxTeamNumberByCourseId(String id) {
	Integer number = (Integer) getSqlMapClientTemplate().queryForObject("getMaxTeamNumberByCourseId", id);
	if (number == null) {
	    return 0;
	} else {
	    return number.intValue();
	}
    }

    public int getMaxTeamNumberByCourseId(long id) {
	return getMaxTeamNumberByCourseId(String.valueOf(id));
    }

    public int getTeamCountByCourseId(String id) {
	Integer number = (Integer) getSqlMapClientTemplate().queryForObject("getTeamCountByCourseId", id);
	return number.intValue();
    }

    public int getTeamCountByCourseId(long id) {
	return getTeamCountByCourseId(String.valueOf(id));
    }

    public int getTeamCountByDivisionId(String id) {
	Integer number = (Integer) getSqlMapClientTemplate().queryForObject("getTeamCountByDivisionId", id);
	return number.intValue();
    }

    public int getTeamCountByDivisionId(long id) {
    	return getTeamCountByDivisionId(String.valueOf(id));
    }

    public int getTeamCountByCourseIdAndDivisionId(String courseId, String divisionId) {
	CourseIdAndDivisionIdQuery query = new CourseIdAndDivisionIdQuery();
	query.setCourseId(Long.parseLong(courseId));
	query.setDivisionId(Long.parseLong(divisionId));
	Integer number = (Integer) getSqlMapClientTemplate().queryForObject("getTeamCountByCourseIdAndDivisionId", query);
	return number.intValue();
    }

    public int getTeamCountByCourseIdAndDivisionId(long courseId, String divisionId) {
	return getTeamCountByCourseIdAndDivisionId(String.valueOf(courseId), divisionId);
    }

    public int getTeamCountByCourseIdAndDivisionId(String courseId, long divisionId) {
	return getTeamCountByCourseIdAndDivisionId(courseId, String.valueOf(divisionId));
    }

    public int getTeamCountByCourseIdAndDivisionId(long courseId, long divisionId) {
	return getTeamCountByCourseIdAndDivisionId(String.valueOf(courseId), String.valueOf(divisionId));
    }

    public void insertTeam(Team team) {
	getSqlMapClientTemplate().update("insertTeam", team);
    }

    public void updateTeam(Team team) {
	getSqlMapClientTemplate().update("updateTeam", team);
    }

    public void deleteTeamById(String id) {
	getSqlMapClientTemplate().update("deleteTeamById", id);
    }

    public void deleteTeamById(long id) {
	deleteTeamById(String.valueOf(id));
    }

    public static Team decorate(Team team) {

      return team;
    }


}// END OF CLASS TeamSqlMapClientDao
