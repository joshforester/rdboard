package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: AdminDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/21
 *  Description: AdminDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Admin;


public interface AdminDao {

    public Admin getAdminById(String id);

    public Admin getAdminById(long id);
    
    public Admin getAdminByEmail(String email);
    
    public Admin getAdminByUsername(String username);

    public Admin getAdminByUsernameAndPassword(String username, String password);

    /*
     *  Expects firstName, lastName, city, region, country, zip, gender, birthday
     *  to be set.
     */
    public Admin getAdminByTraits(Admin admin);

    public List getAll(boolean decorate);

    public void insertAdmin(Admin admin);

    /*
     *  Creates ADMIN entry, but assumes corresponding PERSON
     *  already exists.
     *
     *  Expects id, username, password to be set.
     */
    public void attachAdmin(Admin admin);

    public void updateAdmin(Admin admin);

    /*
     *  Updates ADMIN entry, but assumes corresponding PERSON
     *  already exists.
     *
     *  Expects id, username, password to be set.
     */
    public void updateAttachedAdmin(Admin admin);

    public void deleteAdminById(String id);

    public void deleteAdminById(long id);

}
