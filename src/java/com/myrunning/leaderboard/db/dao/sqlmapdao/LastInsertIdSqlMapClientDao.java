package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: LastInsertIdSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/18
 *  Description: SqlMapDao for LastInsertId.
 **/


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.db.dao.ifacedao.LastInsertIdDao;


public class LastInsertIdSqlMapClientDao extends SqlMapClientDaoSupport implements LastInsertIdDao {

    public LastInsertIdSqlMapClientDao() {
	// empty
    }

    public long getLastInsertId() {
	return ((Long) getSqlMapClientTemplate().queryForObject("getLastInsertId", null)).longValue();
    }

}// END OF CLASS LastInsertIdSqlMapDao
