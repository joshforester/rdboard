package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: DivisionSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/18
 *  Description: SqlMapDao for Division.
 **/


import java.util.List;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Division;
import com.myrunning.leaderboard.model.util.DivisionNameComparator;
import com.myrunning.leaderboard.model.util.DivisionConsistencyComparator;
import com.myrunning.leaderboard.model.util.DivisionMemberCountComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.DivisionDao;
import com.myrunning.leaderboard.db.dao.query.EventIdAndNameQuery;


public class DivisionSqlMapClientDao extends SqlMapClientDaoSupport implements DivisionDao {

    static Logger logger = Logger.getLogger(DivisionSqlMapClientDao.class);


    private void sortDivisions(List<Division> divisions, String order) {
	if (order != null) {
	    if (order.equals("name")) {
		Collections.sort(divisions, new DivisionNameComparator());
	    } else if (order.equals("consistency")) {
		Collections.sort(divisions, new DivisionConsistencyComparator());
	    } else if (order.equals("membercount")) {
		Collections.sort(divisions, new DivisionMemberCountComparator());
	    } else {
		Collections.sort(divisions, new DivisionNameComparator());
	    }
	} else {
	    Collections.sort(divisions, new DivisionNameComparator());
	}
    }


    public DivisionSqlMapClientDao() {
	// empty
    }

    public Division getDivisionById(String id) {
	return (Division) getSqlMapClientTemplate().queryForObject("getDivisionById", id);
    }

    public Division getDivisionById(long id) {
	return getDivisionById(String.valueOf(id));
    }

    public Division getDivisionByTeamId(String id) {
	return (Division) getSqlMapClientTemplate().queryForObject("getDivisionByTeamId", id);
    }

    public Division getDivisionByTeamId(long id) {
	return getDivisionByTeamId(String.valueOf(id));
    }

    public Division getDivisionByEventIdAndName(String id, String name) {
	EventIdAndNameQuery query = new EventIdAndNameQuery();
	query.setEventId(Long.parseLong(id));
	query.setName(name);
	return (Division) getSqlMapClientTemplate().queryForObject("getDivisionByEventIdAndName", query);
    }

    public Division getDivisionByEventIdAndName(long id, String name) {
	return getDivisionByEventIdAndName(String.valueOf(id), name);
    }

    public List getDivisionsByEventId(String id, String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    List<Division> divisions = getSqlMapClientTemplate().queryForList("getDivisionsByEventId", id);
	    sortDivisions(divisions, order);
	    return divisions;
	}
    }

    public List getDivisionsByEventId(long id, String order) {
	return getDivisionsByEventId(String.valueOf(id), order);
    }

    public List getEventDivisions() {
	return getSqlMapClientTemplate().queryForList("getEventDivisions");
    }

    public void insertDivision(Division division) {
	getSqlMapClientTemplate().update("insertDivision", division);
    }

    public void updateDivision(Division division) {
	getSqlMapClientTemplate().update("updateDivision", division);
    }

    public void deleteDivisionById(String id) {
	getSqlMapClientTemplate().update("deleteDivisionById", id);
    }

    public void deleteDivisionById(long id) {
	deleteDivisionById(String.valueOf(id));
    }

    public static Division decorate(Division division) {

      return division;

    }


}// END OF CLASS DivisionSqlMapClientDao
