package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: CpVisitDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/25
 *  Description: CpVisitDao interface.
 **/


import java.sql.Timestamp;
import java.util.List;
import com.myrunning.leaderboard.model.CpVisit;
import com.myrunning.leaderboard.model.DecoratedCpVisit;


public interface CpVisitDao {

    public CpVisit getCpVisitByTeamIdAndCpId(String teamId, String cpId);

    public DecoratedCpVisit getDecoratedCpVisitByTeamIdAndCpId(String teamId, String cpId);

    public List getCpVisitsByTeamId(String id, 
				    Timestamp start, 
				    Timestamp end, 
				    String order);

    public List getDecoratedCpVisitsByTeamId(String id, 
					     Timestamp start, 
					     Timestamp end);

    public List getCpVisitsByCourseId(String id, 
				      Timestamp start,
				      Timestamp end,
				      String order);

    public List getDecoratedCpVisitsByCourseId(String id, 
					       Timestamp start,
					       Timestamp end);

    public List getCpVisitsByCpId(String id, String order);

    /*
    queries here for leaderboard generation based
    on visit status (unofficial, dnf, etc.)

    also, based on CP.is_mandatory, etc.
    */

    public void insertCpVisit(CpVisit cpVisit);

    /*
      perhaps needs an insertIfNotExistsCpVisit, if the iBatis
      call to update doesn't already do it.
    */

    public void updateCpVisit(CpVisit cpVisit);

    public void deleteCpVisitByTeamIdAndCpId(String teamId, String cpId);

}
