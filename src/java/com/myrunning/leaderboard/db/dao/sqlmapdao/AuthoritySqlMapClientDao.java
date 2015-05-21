package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: AuthoritySqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/10/24
 *  Description: SqlMapDao for Authority.
 **/


import java.util.List;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Authority;
import com.myrunning.leaderboard.db.dao.ifacedao.AuthorityDao;


public class AuthoritySqlMapClientDao extends SqlMapClientDaoSupport implements AuthorityDao {

    static Logger logger = Logger.getLogger(AuthoritySqlMapClientDao.class);


    public AuthoritySqlMapClientDao() {
	// empty
    }

    public List getAuthoritiesByPersonId(String id) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    return (List<Authority>) getSqlMapClientTemplate().queryForList("getAuthoritiesByPersonId", id);
	}
    }

    public List getAuthoritiesByPersonId(long id) {
	return getAuthoritiesByPersonId(String.valueOf(id));
    }

    public void insertAuthority(Authority authority) {
	getSqlMapClientTemplate().update("insertAuthority", authority);
    }

    public void deleteAuthoritiesByPersonId(String id) {
	getSqlMapClientTemplate().update("deleteAuthoritiesByPersonId", id);
    }

    public void deleteAuthoritiesByPersonId(long id) {
	deleteAuthoritiesByPersonId(String.valueOf(id));
    }

    public static Authority decorate(Authority authority) {

      return authority;

    }


}// END OF CLASS AuthoritySqlMapClientDao
