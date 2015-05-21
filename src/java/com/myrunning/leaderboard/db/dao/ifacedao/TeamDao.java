package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: TeamDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/13
 *  Description: TeamDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Team;


public interface TeamDao {

    public Team getTeamById(String id);

    public Team getTeamById(long id);

    public Team getTeamByCourseIdAndName(String id, String name);

    public Team getTeamByCourseIdAndName(long id, String name);

    public Team getTeamByDivisionIdAndName(String id, String name);

    public Team getTeamByDivisionIdAndName(long id, String name);

    public int getRegistrationMemberCount(long courseId, long member1Id, long member2Id, long member3Id, long member4Id);
    
    public List getTeamsByCourseId(String id, String order);

    public List getTeamsByCourseId(long id, String order);

    public List getTeamsByDivisionId(String id, String order);

    public List getTeamsByDivisionId(long id, String order);
    
    public List getTeamsByCourseIdAndDivisionId(String courseId, String divisionId, String order);

    public List getTeamsByCourseIdAndDivisionId(long courseId, String divisionId, String order);

    public List getTeamsByCourseIdAndDivisionId(String courseId, long divisionId, String order);

    public List getTeamsByCourseIdAndDivisionId(long courseId, long divisionId, String order);

    public int getMaxTeamNumberByCourseId(String id);

    public int getMaxTeamNumberByCourseId(long id);

    public int getTeamCountByCourseId(String id);

    public int getTeamCountByCourseId(long id);

    public int getTeamCountByDivisionId(String id);

    public int getTeamCountByDivisionId(long id);

    public int getTeamCountByCourseIdAndDivisionId(String courseId, String divisionId);

    public int getTeamCountByCourseIdAndDivisionId(long courseId, String divisionId);

    public int getTeamCountByCourseIdAndDivisionId(String courseId, long divisionId);

    public int getTeamCountByCourseIdAndDivisionId(long courseId, long divisionId);

    public void insertTeam(Team team);

    public void updateTeam(Team team);

    public void deleteTeamById(String id);

    public void deleteTeamById(long id);

}
