package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: PersonDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/20
 *  Description: PersonDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Person;


public interface PersonDao {

    public Person getPersonById(String id);

    public Person getPersonById(long id);

    public List getPersonsByEmail(String email, String order, boolean decorate);

    public List getPersonsByTraits(Person person, String order, boolean decorate);
    
    public List getPersonsByIdentityId(String id, String order, boolean decorate);

    public List getPersonsByIdentityId(long id, String order, boolean decorate);

    public List getAll(String order, boolean decorate);

    public void insertPerson(Person person);

    public void updatePerson(Person person);

    public void deletePersonById(String id);

    public void deletePersonById(long id);

}
