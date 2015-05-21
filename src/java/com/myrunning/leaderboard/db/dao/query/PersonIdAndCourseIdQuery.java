package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: PersonIdAndCourseIdQuery.java
 *  Author:  Joshua Forester
 *  Date: 9/9/2009
 *  Description: Bean class for PersonIdAndCourseIdQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class PersonIdAndCourseIdQuery implements Serializable {

  private long personId;
  private long courseId;

  /**
   * Creates a new instance of PersonIdAndCourseIdQuery
   */
  public PersonIdAndCourseIdQuery() {
  }

  /**
   * Gets the current value of personId
   * @return Current value of personId
   */
  public long getPersonId() {
    return personId;
  }

  /**
   * Sets the value of personId
   * @param personId New value for personId
   */
  public void setPersonId(long personId) {
    this.personId=personId;
  }

  /**
   * Gets the current value of courseId
   * @return Current value of courseId
   */
  public long getCourseId() {
    return courseId;
  }

  /**
   * Sets the value of courseId
   * @param courseId New value for courseId
   */
  public void setCourseId(long courseId) {
    this.courseId=courseId;
  }

}