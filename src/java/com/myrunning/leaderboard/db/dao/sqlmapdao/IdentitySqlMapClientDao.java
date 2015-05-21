package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: IdentitySqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/10/25
 *  Description: SqlMapDao for Identity.
 **/


import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Identity;
import com.myrunning.leaderboard.db.dao.ifacedao.IdentityDao;


public class IdentitySqlMapClientDao extends SqlMapClientDaoSupport implements IdentityDao {

    static Logger logger = Logger.getLogger(IdentitySqlMapClientDao.class);


    public IdentitySqlMapClientDao() {
	// empty
    }

    public Identity getIdentityById(String id) {
	return (Identity) getSqlMapClientTemplate().queryForObject("getIdentityById", id);
    }

    public Identity getIdentityById(long id) {
	return getIdentityById(String.valueOf(id));
    }

    public Identity getIdentityByPersonId(String id) {
	return (Identity) getSqlMapClientTemplate().queryForObject("getIdentityByPersonId", id);
    }

    public Identity getIdentityByPersonId(long id) {
	return getIdentityByPersonId(String.valueOf(id));
    }

    public void insertIdentity(Identity identity) {
	getSqlMapClientTemplate().update("insertIdentity", identity);
    }

    public void updateIdentity(Identity identity) {
	getSqlMapClientTemplate().update("updateIdentity", identity);
    }

    public void deleteIdentityById(String id) {
	getSqlMapClientTemplate().update("deleteIdentityById", id);
    }

    public void deleteIdentityById(long id) {
	deleteIdentityById(String.valueOf(id));
    }

    public static Identity decorate(Identity identity) {

      return identity;

    }


}// END OF CLASS IdentitySqlMapClientDao
