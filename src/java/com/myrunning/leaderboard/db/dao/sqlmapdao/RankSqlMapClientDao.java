package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: RankSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2010/01/16
 *  Description: SqlMapDao for Rank.
 **/


import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Collections;
import java.sql.Timestamp;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Rank;
import com.myrunning.leaderboard.model.DecoratedRank;
import com.myrunning.leaderboard.model.util.RankTimeComparator;
import com.myrunning.leaderboard.model.util.RankDivisionPlaceComparator;
import com.myrunning.leaderboard.model.util.RankCoursePlaceComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.RankDao;
import com.myrunning.leaderboard.db.dao.query.TeamIdAndRangeQuery;
import com.myrunning.leaderboard.db.dao.query.CourseIdAndRangeQuery;
import com.myrunning.leaderboard.db.dao.query.CourseIdAndDivisionIdQuery;


public class RankSqlMapClientDao extends SqlMapClientDaoSupport implements RankDao {

    static Logger logger = Logger.getLogger(RankSqlMapClientDao.class);


    private void sortRanks(List<Rank> ranks, String order) {
	
	if (!ranks.isEmpty()) {
	
	    if (order != null) {
		if (order.equals("time")) {
		    Collections.sort(ranks, new RankTimeComparator());
		} else if (order.equals("divisionplace")) {
		    Collections.sort(ranks, new RankDivisionPlaceComparator());
		} else if (order.equals("courseplace")) {
		    Collections.sort(ranks, new RankCoursePlaceComparator());
		} else {
		    Collections.sort(ranks, new RankTimeComparator());
		}
	    } else {
		Collections.sort(ranks, new RankTimeComparator());
	    }

	}
    }


    public RankSqlMapClientDao() {
	// empty
    }


    public DecoratedRank getDecoratedRankById(String id) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	}
	
	return (DecoratedRank) getSqlMapClientTemplate().queryForObject("getDecoratedRankById", id);
    }

    public Map<Integer, DecoratedRank> getDecoratedRanksByCourseIdAndDivisionId(String courseId, String divisionId) {
	if ((divisionId == null) || (divisionId.equals("0")) ||
	    (courseId == null) || (courseId.equals("0"))) {
	    return null;
	}

	CourseIdAndDivisionIdQuery query = new CourseIdAndDivisionIdQuery();
	query.setCourseId(Long.parseLong(courseId));
	query.setDivisionId(Long.parseLong(divisionId));

	List<DecoratedRank> ranks = getSqlMapClientTemplate().queryForList("getDecoratedRanksByCourseIdAndDivisionId", query);
	Collections.sort(ranks, new RankTimeComparator());
	Map teamRanks = new HashMap<Long, DecoratedRank>();
	ListIterator<DecoratedRank> rli = ranks.listIterator();
	DecoratedRank rank = null;
	while (rli.hasNext()) {
	    rank = rli.next();
	    teamRanks.put(rank.getTeamId(), rank);
	}

	Map<Integer, DecoratedRank> placeRanks = 
	    new HashMap<Integer, DecoratedRank>(teamRanks.size());
	Iterator<DecoratedRank> i = teamRanks.values().iterator();
	while (i.hasNext()) {
	    rank = i.next();
	    placeRanks.put(rank.getDivisionPlace(), rank);
	}

	return placeRanks;
    }

    public Map<Integer, DecoratedRank> getDecoratedRanksByCourseId(String id) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	}

	List<DecoratedRank> ranks = getSqlMapClientTemplate().queryForList("getDecoratedRanksByCourseId", id);
	Collections.sort(ranks, new RankTimeComparator());
	Map teamRanks = new HashMap<Long, DecoratedRank>();
	ListIterator<DecoratedRank> rli = ranks.listIterator();
	DecoratedRank rank = null;
	while (rli.hasNext()) {
	    rank = rli.next();
	    teamRanks.put(rank.getTeamId(), rank);
	}

	Map<Integer, DecoratedRank> placeRanks = 
	    new HashMap<Integer, DecoratedRank>(teamRanks.size());
	Iterator<DecoratedRank> i = teamRanks.values().iterator();
	while (i.hasNext()) {
	    rank = i.next();
	    placeRanks.put(rank.getCoursePlace(), rank);
	}

	return placeRanks;
    }

    public List getRanksByTeamId(String id, 
				 Timestamp start, 
				 Timestamp end, 
				 String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	}

	TeamIdAndRangeQuery query = new TeamIdAndRangeQuery();
	query.setId(Long.parseLong(id));
	query.setStart(start);
	query.setEnd(end);
	List<Rank> ranks = getSqlMapClientTemplate().queryForList("getRanksByTeamId", query);
	sortRanks(ranks, order);
	return ranks;
    }

    public DecoratedRank getDecoratedRankByTeamId(String id, 
						  Timestamp start, 
						  Timestamp end) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	}
	
	TeamIdAndRangeQuery query = new TeamIdAndRangeQuery();
	query.setId(Long.parseLong(id));
	query.setStart(start);
	query.setEnd(end);
	
	return (DecoratedRank) getSqlMapClientTemplate().queryForObject("getDecoratedRankByTeamId", query);
    }

    public List getRanksByCourseId(String id, 
				   Timestamp start, 
				   Timestamp end, 
				   String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	}

	CourseIdAndRangeQuery query = new CourseIdAndRangeQuery();
	query.setId(Long.parseLong(id));
	query.setStart(start);
	query.setEnd(end);
	List<Rank> ranks = getSqlMapClientTemplate().queryForList("getRanksByCourseId", query);
	sortRanks(ranks, order);
	return ranks;
    }
    
    public void insertRank(Rank rank) {
	getSqlMapClientTemplate().update("insertRank", rank);
    }

    public static Rank decorate(Rank rank) {

      return rank;

    }


}// END OF CLASS RankSqlMapClientDao
