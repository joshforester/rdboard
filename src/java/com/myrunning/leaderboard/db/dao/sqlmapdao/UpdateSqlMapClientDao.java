package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: UpdateSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2010/01/19
 *  Description: SqlMapDao for Update.
 **/


import java.util.List;
import java.util.Collections;
import java.sql.Timestamp;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Update;
import com.myrunning.leaderboard.model.util.UpdateTypeComparator;
import com.myrunning.leaderboard.model.util.UpdateTimeComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.UpdateDao;
import com.myrunning.leaderboard.db.dao.query.IdAndTypeAndRangeQuery;


public class UpdateSqlMapClientDao extends SqlMapClientDaoSupport implements UpdateDao {

    static Logger logger = Logger.getLogger(UpdateSqlMapClientDao.class);


    private void sortUpdates(List<Update> updates, String order) {
	
	if (!updates.isEmpty()) {
	
	    if (order != null) {
		if (order.equals("time")) {
		    Collections.sort(updates, new UpdateTimeComparator());
		} else if (order.equals("type")) {
		    Collections.sort(updates, new UpdateTypeComparator());
		} else {
		    Collections.sort(updates, new UpdateTimeComparator());
		}
	    } else {
		Collections.sort(updates, new UpdateTimeComparator());
	    }

	}
    }


    public UpdateSqlMapClientDao() {
	// empty
    }

    public Update getUpdateById(String id) {
    	return (Update) getSqlMapClientTemplate().queryForObject("getUpdateById", id);
    }

    public List getUpdatesByTeamIdAndType(String id, String type, Timestamp start, Timestamp end, String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	}
	
	IdAndTypeAndRangeQuery query = new IdAndTypeAndRangeQuery();
	query.setId(Long.parseLong(id));
	query.setType(type);
	query.setStart(start);
	query.setEnd(end);
	
	List<Update> updates = getSqlMapClientTemplate().queryForList("getUpdatesByTeamId", query);
	sortUpdates(updates, order);
	return updates;
    }

    public List getUpdatesByTeamId(String id, Timestamp start, Timestamp end, String order) {
	return getUpdatesByTeamIdAndType(id, null, start, end, order);
    }

    public List getUpdatesByCourseIdAndType(String id, String type, Timestamp start, Timestamp end, String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	}
	
	IdAndTypeAndRangeQuery query = new IdAndTypeAndRangeQuery();
	query.setId(Long.parseLong(id));
	query.setType(type);
	query.setStart(start);
	query.setEnd(end);
	
	List<Update> updates = getSqlMapClientTemplate().queryForList("getUpdatesByCourseId", query);
	sortUpdates(updates, order);
	return updates;
    }

    public List getUpdatesByCourseId(String id, Timestamp start, Timestamp end, String order) {
	return getUpdatesByCourseIdAndType(id, null, start, end, order);
    }

    public List getUpdatesByEventIdAndType(String id, String type, Timestamp start, Timestamp end, String order) {
    	if ((id == null) || (id.equals("0"))) {
	    return null;
	}
	
	IdAndTypeAndRangeQuery query = new IdAndTypeAndRangeQuery();
	query.setId(Long.parseLong(id));
	query.setType(type);
	query.setStart(start);
	query.setEnd(end);
	
	List<Update> updates = getSqlMapClientTemplate().queryForList("getUpdatesByEventId", query);
	sortUpdates(updates, order);
	return updates;
    }

    public List getUpdatesByEventId(String id, Timestamp start, Timestamp end, String order) {
	return getUpdatesByEventIdAndType(id, null, start, end, order);
    }

    public void insertUpdate(Update update) {
	if (update != null) {
	    getSqlMapClientTemplate().update("insertUpdate", update);
	}
    }

    public static Update decorate(Update update) {

      return update;

    }


}// END OF CLASS UpdateSqlMapClientDao
