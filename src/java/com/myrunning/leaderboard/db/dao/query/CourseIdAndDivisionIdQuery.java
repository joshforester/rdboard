package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: CourseIdAndDivisionIdQuery.java
 *  Author:  Joshua Forester
 *  Date: 9/4/2009
 *  Description: Bean class for CourseIdAndDivisionIdQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class CourseIdAndDivisionIdQuery implements Serializable {

  private long courseId;
  private long divisionId;

  /**
   * Creates a new instance of CourseIdAndDivisionIdQuery
   */
  public CourseIdAndDivisionIdQuery() {
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

  /**
   * Gets the current value of divisionId
   * @return Current value of divisionId
   */
  public long getDivisionId() {
    return divisionId;
  }

  /**
   * Sets the value of divisionId
   * @param divisionId New value for divisionId
   */
  public void setDivisionId(long divisionId) {
    this.divisionId=divisionId;
  }

}