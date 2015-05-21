package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: CourseSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/18
 *  Description: SqlMapDao for Course.
 **/


import java.util.List;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.Course;
import com.myrunning.leaderboard.model.util.CourseNameComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.CourseDao;
import com.myrunning.leaderboard.db.dao.query.EventIdAndNameQuery;


public class CourseSqlMapClientDao extends SqlMapClientDaoSupport implements CourseDao {

    static Logger logger = Logger.getLogger(CourseSqlMapClientDao.class);


    private void sortCourses(List<Course> courses, String order) {
	
	if (order != null) {
	    if (order.equals("name")) {
		Collections.sort(courses, new CourseNameComparator());
	    } else {
		Collections.sort(courses, new CourseNameComparator());
	    }
	} else {
	    Collections.sort(courses, new CourseNameComparator());
	}
	
    }


    public CourseSqlMapClientDao() {
	// empty
    }

    public Course getCourseById(String id) {
	return (Course) getSqlMapClientTemplate().queryForObject("getCourseById", id);
    }

    public Course getCourseById(long id) {
	return getCourseById(String.valueOf(id));
    }

    public Course getCourseByTeamId(String id) {
	return (Course) getSqlMapClientTemplate().queryForObject("getCourseByTeamId", id);
    }

    public Course getCourseByTeamId(long id) {
	return getCourseByTeamId(String.valueOf(id));
    }

    public Course getCourseByCpId(String id) {
	return (Course) getSqlMapClientTemplate().queryForObject("getCourseByCpId", id);
    }

    public Course getCourseByCpId(long id) {
	return getCourseByCpId(String.valueOf(id));
    }

    public Course getCourseByEventIdAndName(String id, String name) {
	EventIdAndNameQuery query = new EventIdAndNameQuery();
	query.setEventId(Long.parseLong(id));
	query.setName(name);
	return (Course) getSqlMapClientTemplate().queryForObject("getCourseByEventIdAndName", query);
    }

    public Course getCourseByEventIdAndName(long id, String name) {
	return getCourseByEventIdAndName(String.valueOf(id), name);
    }

    public List getCoursesByEventId(String id, String order) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	}

	List<Course> courses = getSqlMapClientTemplate().queryForList("getCoursesByEventId", id);
	sortCourses(courses, order);
	return courses;

    }

    public List getCoursesByEventId(long id, String order) {
	return getCoursesByEventId(String.valueOf(id), order);
    }

    public List getEventCourses() {
	return getSqlMapClientTemplate().queryForList("getEventCourses");
    }

    public void insertCourse(Course course) {
	getSqlMapClientTemplate().update("insertCourse", course);
    }

    public void updateCourse(Course course) {
	getSqlMapClientTemplate().update("updateCourse", course);
    }

    public void deleteCourseById(String id) {
	getSqlMapClientTemplate().update("deleteCourseById", id);
    }

    public void deleteCourseById(long id) {
	deleteCourseById(String.valueOf(id));
    }

    public static Course decorate(Course course) {

      return course;

    }


}// END OF CLASS CourseSqlMapClientDao
