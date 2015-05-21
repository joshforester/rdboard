package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: CourseDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/18
 *  Description: CourseDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Course;


public interface CourseDao {

    public Course getCourseById(String id);

    public Course getCourseById(long id);

    public Course getCourseByTeamId(String id);

    public Course getCourseByTeamId(long id);

    public Course getCourseByCpId(String id);

    public Course getCourseByCpId(long id);
    
    public Course getCourseByEventIdAndName(String id, String name);

    public Course getCourseByEventIdAndName(long id, String name);

    public List getCoursesByEventId(String id, String order);

    public List getCoursesByEventId(long id, String order);

    public List getEventCourses();

    public void insertCourse(Course course);

    public void updateCourse(Course course);

    public void deleteCourseById(String id);

    public void deleteCourseById(long id);

}
