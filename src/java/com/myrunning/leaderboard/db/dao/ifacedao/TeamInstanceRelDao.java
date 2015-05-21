package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: TeamInstanceRelDao.java
 *  Author: Joshua Forester
 *  Date: 2009/09/08
 *  Description: TeamInstanceRelDao interface.
 **/


import java.util.List; 
import com.myrunning.leaderboard.model.TeamInstanceRel;


public interface TeamInstanceRelDao {

    public TeamInstanceRel getTeamInstanceRelByCompetitorId(String competitorId);

    public TeamInstanceRel getTeamInstanceRelByCompetitorId(long competitorId);

    public List getTeamInstanceRelsByTeamId(String teamId);

    public List getTeamInstanceRelsByTeamId(long teamId);

    public void insertTeamInstanceRel(TeamInstanceRel teamInstanceRel);

    public void updateTeamInstanceRel(TeamInstanceRel teamInstanceRel);

    public void deleteTeamInstanceRelsByTeamId(String teamId);

    public void deleteTeamInstanceRelsByTeamId(long teamId);

    public void deleteTeamInstanceRelByCompetitorId(String competitorId);

    public void deleteTeamInstanceRelByCompetitorId(long competitorId);

}
