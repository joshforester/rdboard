package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: ComparatorSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/26
 *  Description: SqlMapDao for Comparator.
 **/


import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Comparator;
import com.myrunning.leaderboard.db.dao.ifacedao.ComparatorDao;


public class ComparatorSqlMapClientDao extends SqlMapClientDaoSupport implements ComparatorDao {

    static Logger logger = Logger.getLogger(ComparatorSqlMapClientDao.class);


    public ComparatorSqlMapClientDao() {
	// empty
    }

    public Comparator getComparatorById(String id) {
	return (Comparator) getSqlMapClientTemplate().queryForObject("getComparatorById", id);
    }

    public Comparator getComparatorById(long id) {
	return getComparatorById(String.valueOf(id));
    }

    public int getMaxComparatorOrderByCourseId(String id) {
	Integer order = (Integer) getSqlMapClientTemplate().queryForObject("getMaxComparatorOrderByCourseId", id);
	if (order == null) {
	    return 0;
	} else {
	    return order.intValue();
	}
    }

    public int getMaxComparatorOrderByCourseId(long id) {
	return getMaxComparatorOrderByCourseId(String.valueOf(id));
    }

    public List getComparatorsByCourseId(String id) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    return getSqlMapClientTemplate().queryForList("getComparatorsByCourseId", id);
	}
    }

    public List getComparatorsByCourseId(long id) {
	return getComparatorsByCourseId(String.valueOf(id));
    }

    public void insertComparator(Comparator comparator) {
	getSqlMapClientTemplate().update("insertComparator", comparator);
    }

    public void updateComparator(Comparator comparator) {
	/*
	 *  if order was affected, need to do something here
	 */
	getSqlMapClientTemplate().update("updateComparator", comparator);
    }

    public void deleteComparatorById(String id) {
	/*
	 *  need to lower all Comparators above this one's order
	 */
	getSqlMapClientTemplate().update("deleteComparatorById", id);
    }

    public void deleteComparatorById(long id) {
	deleteComparatorById(String.valueOf(id));
    }

    private static Comparator decorate(Comparator comparator) {

      return comparator;

    }


}// END OF CLASS ComparatorSqlMapClientDao
