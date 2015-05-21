package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: DivisionDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/18
 *  Description: DivisionDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Division;


public interface DivisionDao {

    public Division getDivisionById(String id);

    public Division getDivisionById(long id);
    
    public Division getDivisionByTeamId(String id);

    public Division getDivisionByTeamId(long id);

    public Division getDivisionByEventIdAndName(String id, String name);

    public Division getDivisionByEventIdAndName(long id, String name);

    public List getDivisionsByEventId(String id, String order);

    public List getDivisionsByEventId(long id, String order);

    public List getEventDivisions();

    public void insertDivision(Division division);

    public void updateDivision(Division division);

    public void deleteDivisionById(String id);

    public void deleteDivisionById(long id);

}
