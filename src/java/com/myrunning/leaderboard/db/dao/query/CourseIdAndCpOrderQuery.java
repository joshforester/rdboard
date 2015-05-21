package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: CourseIdAndCpOrderQuery.java
 *  Author:  Joshua Forester
 *  Date: 11/6/2009
 *  Description: Bean class for CourseIdAndCpOrderQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class CourseIdAndCpOrderQuery implements Serializable {

  private String courseId;
  private String cpOrder;

  /**
   * Creates a new instance of CourseIdAndCpOrderQuery
   */
  public CourseIdAndCpOrderQuery() {
  }

  /**
   * Gets the current value of courseId
   * @return Current value of courseId
   */
  public String getCourseId() {
    return courseId;
  }

  /**
   * Sets the value of courseId
   * @param courseId New value for courseId
   */
  public void setCourseId(String courseId) {
    this.courseId=courseId;
  }

  /**
   * Gets the current value of cpOrder
   * @return Current value of cpOrder
   */
  public String getCpOrder() {
    return cpOrder;
  }

  /**
   * Sets the value of cpOrder
   * @param cpOrder New value for cpOrder
   */
  public void setCpOrder(String cpOrder) {
    this.cpOrder=cpOrder;
  }

}