package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: CourseIdAndNameQuery.java
 *  Author:  Joshua Forester
 *  Date: 8/20/2009
 *  Description: Bean class for CourseIdAndNameQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class CourseIdAndNameQuery implements Serializable {

  private long courseId;
  private String name;

  /**
   * Creates a new instance of CourseIdAndNameQuery
   */
  public CourseIdAndNameQuery() {
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
   * Gets the current value of name
   * @return Current value of name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of name
   * @param name New value for name
   */
  public void setName(String name) {
    this.name=name;
  }

}