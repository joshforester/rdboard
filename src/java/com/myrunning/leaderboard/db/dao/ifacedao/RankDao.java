package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: RankDao.java
 *  Author: Joshua Forester
 *  Date: 2010/01/16
 *  Description: RankDao interface.
 **/


import java.sql.Timestamp;
import java.util.Map;
import java.util.List;
import com.myrunning.leaderboard.model.Rank;
import com.myrunning.leaderboard.model.DecoratedRank;


public interface RankDao {

    public DecoratedRank getDecoratedRankById(String id);
			
    /*
     *  Gets the latest ranks per team in a division.  
     *  Note these are not guaranteed to exist if none have been created.
     *  @return a map of divisionPlace to Rank object
     **/
    public Map<Integer, DecoratedRank> getDecoratedRanksByCourseIdAndDivisionId(String courseId, String divisionId);

    /*
     *  Gets the latest ranks per team in a course.  
     *  Note these are not guaranteed to exist if none have been created.
     *  @return a map of coursePlace to Rank object
     **/
    public Map<Integer, DecoratedRank> getDecoratedRanksByCourseId(String id);

    public List getRanksByTeamId(String id, 
				 Timestamp start, 
				 Timestamp end, 
				 String order);

    public DecoratedRank getDecoratedRankByTeamId(String id, 
						  Timestamp start, 
						  Timestamp end);

    public List getRanksByCourseId(String id, 
				   Timestamp start, 
				   Timestamp end, 
				   String order);

    public void insertRank(Rank rank);

}
