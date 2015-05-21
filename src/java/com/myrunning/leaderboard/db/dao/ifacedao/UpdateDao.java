package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: UpdateDao.java
 *  Author: Joshua Forester
 *  Date: 2010/01/19
 *  Description: UpdateDao interface.
 **/


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import com.myrunning.leaderboard.model.Update;


public interface UpdateDao {

    public Update getUpdateById(String id);

    public List getUpdatesByTeamIdAndType(String id, String type, Timestamp start, Timestamp end, String order);

    public List getUpdatesByTeamId(String id, Timestamp start, Timestamp end, String order);

    public List getUpdatesByCourseIdAndType(String id, String type, Timestamp start, Timestamp end, String order);

    public List getUpdatesByCourseId(String id, Timestamp start, Timestamp end, String order);

    public List getUpdatesByEventIdAndType(String id, String type, Timestamp start, Timestamp end, String order);

    public List getUpdatesByEventId(String id, Timestamp start, Timestamp end, String order);

    public void insertUpdate(Update update);

}
