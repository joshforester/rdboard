package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: IdentityDao.java
 *  Author: Joshua Forester
 *  Date: 2009/10/25
 *  Description: IdentityDao interface.
 **/


import com.myrunning.leaderboard.model.Identity;


public interface IdentityDao {

    public Identity getIdentityById(String id);

    public Identity getIdentityById(long id);

    public Identity getIdentityByPersonId(String id);

    public Identity getIdentityByPersonId(long id);

    public void insertIdentity(Identity identity);

    public void updateIdentity(Identity identity);

    public void deleteIdentityById(String id);

    public void deleteIdentityById(long id);

}
