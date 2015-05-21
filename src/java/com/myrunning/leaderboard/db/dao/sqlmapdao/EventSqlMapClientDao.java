package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: EventSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/13
 *  Description: SqlMapDao for Event.
 **/


import java.util.List;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.util.EventNameComparator;
import com.myrunning.leaderboard.model.util.EventCityComparator;
import com.myrunning.leaderboard.model.util.EventRegionComparator;
import com.myrunning.leaderboard.model.util.EventCountryComparator;
import com.myrunning.leaderboard.model.util.EventStartTimeComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;


public class EventSqlMapClientDao extends SqlMapClientDaoSupport implements EventDao {

    static Logger logger = Logger.getLogger(EventSqlMapClientDao.class);


    private void sortEvents(List<Event> events, String order) {
	if (order != null) {
	    if (order.equals("name")) {
		Collections.sort(events, new EventNameComparator());
	    } else if (order.equals("city")) {
		Collections.sort(events, new EventCityComparator());
	    } else if (order.equals("region")) {
		Collections.sort(events, new EventRegionComparator());
	    } else if (order.equals("country")) {
		Collections.sort(events, new EventCountryComparator());
	    } else if (order.equals("starttime")) {
		Collections.sort(events, new EventStartTimeComparator());
	    } else {
		Collections.sort(events, new EventNameComparator());
	    }
	} else {
	    Collections.sort(events, new EventNameComparator());
	}
    }

    public EventSqlMapClientDao() {
	// empty
    }

    public List getAll(String order) {
	List<Event> events = getSqlMapClientTemplate().queryForList("getAllEvents");
	sortEvents(events, order);
	return events;
    }

    public Event getEventById(String id) {
	return (Event) getSqlMapClientTemplate().queryForObject("getEventById", id);
    }

    public Event getEventById(long id) {
	return getEventById(String.valueOf(id));
    }

    public Event getEventByCourseId(String id) {
	return (Event) getSqlMapClientTemplate().queryForObject("getEventByCourseId", id);
    }

    public Event getEventByCourseId(long id) {
	return getEventByCourseId(String.valueOf(id));
    }

    public Event getEventByName(String name) {
	return (Event) getSqlMapClientTemplate().queryForObject("getEventByName", name);
    }

    public void insertEvent(Event event) {
	getSqlMapClientTemplate().update("insertEvent", event);
    }

    public void updateEvent(Event event) {
	getSqlMapClientTemplate().update("updateEvent", event);
    }

    public void deleteEventById(String id) {
	getSqlMapClientTemplate().update("deleteEventById", id);
    }

    public void deleteEventById(long id) {
	deleteEventById(String.valueOf(id));
    }

    public static Event decorate(Event event) {

      return event;

    }


}// END OF CLASS EventSqlMapClientDao
