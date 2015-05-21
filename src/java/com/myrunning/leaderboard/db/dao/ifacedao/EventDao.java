package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: EventDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/17
 *  Description: EventDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Event;


public interface EventDao {

    public List getAll(String order);

    public Event getEventById(String id);

    public Event getEventById(long id);

    public Event getEventByCourseId(String id);

    public Event getEventByCourseId(long id);

    public Event getEventByName(String name);

    public void insertEvent(Event event);

    public void updateEvent(Event event);

    public void deleteEventById(String id);

    public void deleteEventById(long id);

}
