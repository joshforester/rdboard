package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: CompetitorDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/21
 *  Description: CompetitorDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Competitor;


public interface CompetitorDao {

    public Competitor getCompetitorById(String id);

    public Competitor getCompetitorById(long id);
    
    public Competitor getCompetitorByEmail(String email);
    
    public Competitor getCompetitorByPersonIdAndCourseId(String personId, String courseId);

    public Competitor getCompetitorByPersonIdAndCourseId(long personId, String courseId);

    public Competitor getCompetitorByPersonIdAndCourseId(String personId, long courseId);

    public Competitor getCompetitorByPersonIdAndCourseId(long personId, long courseId);

    /*
     *  Expects firstName, lastName, city, region, country, zip, gender, birthday
     *  to be set.
     */
    public Competitor getCompetitorByTraits(Competitor competitor);
    
    public List getCompetitorsByPersonId(String personId, boolean decorate);

    public List getCompetitorsByPersonId(long personId, boolean decorate);

    public List getAll(boolean decorate);

    public List getCompetitorsByTeamId(String id, boolean decorate);

    public List getCompetitorsByTeamId(long id, boolean decorate);

    /*
    public List getCompetitorsByCourseId(String id);

    public List getCompetitorsByCourseId(long id);

    public List getCompetitorsByDivisionId(String id);

    public List getCompetitorsByDivisionId(long id);

    public List getCompetitorsByEventId(String id);

    public List getCompetitorsByEventId(long id);
    */

    public void insertCompetitor(Competitor competitor);

    /*
     *  Creates COMPETITOR entry, but assumes corresponding PERSON
     *  already exists.
     *
     *  Expects personId, emergencyContactFirstName, emergencyContactLastName,
     *  emergencyContactRelation, emergencyPhone, shirtSize, and shoeSize
     *  to be set.
     */
    public void attachCompetitor(Competitor competitor);

    public void updateCompetitor(Competitor competitor);

    /*
     *  Updates COMPETITOR entry, but assumes corresponding PERSON
     *  already exists.
     *
     *  Expects personId, emergencyContactFirstName, emergencyContactLastName,
     *  emergencyContactRelation, emergencyPhone, shirtSize, and shoeSize
     *  to be set.
     */
    public void updateAttachedCompetitor(Competitor competitor);

    public void deleteCompetitorById(String id);

    public void deleteCompetitorById(long id);

}
