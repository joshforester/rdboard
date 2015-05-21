package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: TeamInstanceRelSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/09/08
 *  Description: SqlMapDao for TeamInstanceRel.
 **/


import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.TeamInstanceRel;
import com.myrunning.leaderboard.db.dao.ifacedao.TeamInstanceRelDao;


public class TeamInstanceRelSqlMapClientDao extends SqlMapClientDaoSupport implements TeamInstanceRelDao {

    static Logger logger = Logger.getLogger(TeamInstanceRelSqlMapClientDao.class);


    public TeamInstanceRelSqlMapClientDao() {
	// empty
    }


    public TeamInstanceRel getTeamInstanceRelByCompetitorId(String competitorId) {
	return (TeamInstanceRel) getSqlMapClientTemplate().queryForObject("getTeamInstanceRelByCompetitorId", competitorId);
    }

    public TeamInstanceRel getTeamInstanceRelByCompetitorId(long competitorId) {
	return getTeamInstanceRelByCompetitorId(String.valueOf(competitorId));
    }

    public List getTeamInstanceRelsByTeamId(String teamId) {
   	if ((teamId == null) || (teamId.equals("0"))) {
	    return null;
	} else {
	    return getSqlMapClientTemplate().queryForList("getTeamInstanceRelsByTeamId", teamId);
	}
    }

    public List getTeamInstanceRelsByTeamId(long teamId) {
	return this.getTeamInstanceRelsByTeamId(String.valueOf(teamId));
    }

    public void insertTeamInstanceRel(TeamInstanceRel teamInstanceRel) {
	getSqlMapClientTemplate().update("insertTeamInstanceRel", teamInstanceRel);
    }

    public void updateTeamInstanceRel(TeamInstanceRel teamInstanceRel) {
	getSqlMapClientTemplate().update("updateTeamInstanceRel", teamInstanceRel);
    }

    public void deleteTeamInstanceRelsByTeamId(String teamId) {
    	getSqlMapClientTemplate().update("deleteTeamInstanceRelsByTeamId", teamId);
    }

    public void deleteTeamInstanceRelsByTeamId(long teamId) {
    	deleteTeamInstanceRelsByTeamId(String.valueOf(teamId));
    }

    public void deleteTeamInstanceRelByCompetitorId(String competitorId) {
	getSqlMapClientTemplate().update("deleteTeamInstanceRelsByCompetitorId", competitorId);
    }

    public void deleteTeamInstanceRelByCompetitorId(long competitorId) {
	deleteTeamInstanceRelByCompetitorId(String.valueOf(competitorId));
    }

    public static TeamInstanceRel decorate(TeamInstanceRel teamInstanceRel) {

      return teamInstanceRel;
    }


}// END OF CLASS TeamInstanceRelSqlMapClientDao
