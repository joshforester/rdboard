package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: AuthorityDao.java
 *  Author: Joshua Forester
 *  Date: 2009/10/24
 *  Description: AuthorityDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Authority;


public interface AuthorityDao {

    public List getAuthoritiesByPersonId(String id);

    public List getAuthoritiesByPersonId(long id);

    public void insertAuthority(Authority authority);

    public void deleteAuthoritiesByPersonId(String id);

    public void deleteAuthoritiesByPersonId(long id);

}
