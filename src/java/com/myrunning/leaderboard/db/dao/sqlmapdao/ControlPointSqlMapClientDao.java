package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: ControlPointSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/20
 *  Description: SqlMapDao for ControlPoint.
 **/


import java.util.List;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
//import com.ibatis.sqlmap.client.SqlMapClient;
//import org.springframework.stereotype.Repository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.util.ControlPointOrderComparator;
import com.myrunning.leaderboard.model.util.ControlPointNameComparator;
import com.myrunning.leaderboard.model.util.ControlPointMandatoryComparator;
import com.myrunning.leaderboard.model.util.ControlPointWeightComparator;
import com.myrunning.leaderboard.model.util.ControlPointToDisciplineComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.ControlPointDao;
import com.myrunning.leaderboard.db.dao.query.CourseIdAndNameQuery;
import com.myrunning.leaderboard.db.dao.query.CourseIdAndCpOrderQuery;


//@Repository
public class ControlPointSqlMapClientDao extends SqlMapClientDaoSupport implements ControlPointDao {

    static Logger logger = Logger.getLogger(ControlPointSqlMapClientDao.class);

    /*
    @Autowired
    private SqlMapClient sqlMapClient;


    @Autowired
    public void createSqlMapClient(SqlMapClient sqlMapClient)
    {
	this.setSqlMapClient(sqlMapClient);
    }
    */


    private void sortControlPoints(List<ControlPoint> controlPoints, String order) {
    	if (order != null) {
	    if (order.equals("order")) {
		Collections.sort(controlPoints, new ControlPointOrderComparator());
	    } else if (order.equals("name")) {
		Collections.sort(controlPoints, new ControlPointNameComparator());
	    } else if (order.equals("mandatory")) {
		Collections.sort(controlPoints, new ControlPointMandatoryComparator());
	    } else if (order.equals("weight")) {
		Collections.sort(controlPoints, new ControlPointWeightComparator());
	    } else if (order.equals("todiscipline")) {
		Collections.sort(controlPoints, new ControlPointToDisciplineComparator());
	    } else {
		Collections.sort(controlPoints, new ControlPointOrderComparator());
	    }
	} else {
	    Collections.sort(controlPoints, new ControlPointOrderComparator());
	}
    }


    public ControlPointSqlMapClientDao() {
	// empty
    }

    public ControlPoint getControlPointById(String id) {
	return (ControlPoint) getSqlMapClientTemplate().queryForObject("getControlPointById", id);
    }

    public ControlPoint getControlPointById(long id) {
	return getControlPointById(String.valueOf(id));
    }

    public ControlPoint getControlPointByCourseIdAndName(String id, String name) {
	CourseIdAndNameQuery query = new CourseIdAndNameQuery();
	query.setCourseId(Long.parseLong(id));
	query.setName(name);
	return (ControlPoint) getSqlMapClientTemplate().queryForObject("getControlPointByCourseIdAndName", query);
    }

    public ControlPoint getControlPointByCourseIdAndName(long id, String name) {
	return getControlPointByCourseIdAndName(String.valueOf(id), name);
    }

    public ControlPoint getControlPointByCourseIdAndCpOrder(String id, String cpOrder) {
	CourseIdAndCpOrderQuery query = new CourseIdAndCpOrderQuery();
	query.setCourseId(id);
	query.setCpOrder(cpOrder);
	return (ControlPoint) getSqlMapClientTemplate().queryForObject("getControlPointByCourseIdAndCpOrder", query);
    }

    public ControlPoint getControlPointByCourseIdAndCpOrder(long id, String cpOrder) {
	return getControlPointByCourseIdAndCpOrder(String.valueOf(id), cpOrder);
    }

    public ControlPoint getControlPointByCourseIdAndCpOrder(String id, int cpOrder) {
	return getControlPointByCourseIdAndCpOrder(id, String.valueOf(cpOrder));
    }

    public ControlPoint getControlPointByCourseIdAndCpOrder(long id, int cpOrder) {
	return getControlPointByCourseIdAndCpOrder(String.valueOf(id), String.valueOf(cpOrder));
    }

    public int getMaxControlPointOrderByCourseId(String id) {
	Integer order = (Integer) getSqlMapClientTemplate().queryForObject("getMaxControlPointOrderByCourseId", id);
	if (order == null) {
	    return 0;
	} else {
	    return order.intValue();
	}
    }

    public int getMaxControlPointOrderByCourseId(long id) {
	return getMaxControlPointOrderByCourseId(String.valueOf(id));
    }

    public int getMostLikelyZoneNumberByCourseId(String id) {
	Integer number = (Integer) getSqlMapClientTemplate().queryForObject("getMostLikelyZoneNumberByCourseId", id);
	if (number == null) {
	    return 0;
	} else {
	    return number.intValue();
	}
    }

    public int getMostLikelyZoneNumberByCourseId(long id) {
	return getMostLikelyZoneNumberByCourseId(String.valueOf(id));
    }

    public String getMostLikelyZoneCharByCourseId(String id) {
	return (String) getSqlMapClientTemplate().queryForObject("getMostLikelyZoneCharByCourseId", id);
    }

    public String getMostLikelyZoneCharByCourseId(long id) {
	return getMostLikelyZoneCharByCourseId(String.valueOf(id));
    }

    public List getControlPointsByCourseId(String id, String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    List<ControlPoint> controlPoints = getSqlMapClientTemplate().queryForList("getControlPointsByCourseId", id);
	    sortControlPoints(controlPoints, order);
	    return controlPoints;
	}
    }

    public List getControlPointsByCourseId(long id, String order) {
	return getControlPointsByCourseId(String.valueOf(id), order);
    }

    public void insertControlPoint(ControlPoint controlPoint) {
	getSqlMapClientTemplate().update("insertControlPoint", controlPoint);
    }

    public void updateControlPoint(ControlPoint controlPoint) {
	/*
	 *  if order was affected, need to do something here
	 */
	getSqlMapClientTemplate().update("updateControlPoint", controlPoint);
    }

    public void deleteControlPointById(String id) {
	/*
	 *  need to lower all CPs above this one's order
	 */
	getSqlMapClientTemplate().update("deleteControlPointById", id);
    }

    public void deleteControlPointById(long id) {
	deleteControlPointById(String.valueOf(id));
    }

    public static ControlPoint decorate(ControlPoint controlPoint) {

      return controlPoint;

    }


}// END OF CLASS ControlPointSqlMapClientDao
