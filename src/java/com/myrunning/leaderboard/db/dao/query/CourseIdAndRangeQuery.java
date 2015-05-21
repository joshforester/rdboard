package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: CourseIdAndRangeQuery.java
 *  Author:  Joshua Forester
 *  Date: 1/2/2010
 *  Description: Bean class for CourseIdAndRangeQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class CourseIdAndRangeQuery implements Serializable {

  private long id;
  private Timestamp start;
  private Timestamp end;

  /**
   * Creates a new instance of CourseIdAndRangeQuery
   */
  public CourseIdAndRangeQuery() {
  }

  /**
   * Gets the current value of id
   * @return Current value of id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the value of id
   * @param id New value for id
   */
  public void setId(long id) {
    this.id=id;
  }

  /**
   * Gets the current value of start
   * @return Current value of start
   */
  public Timestamp getStart() {
    return start;
  }

  /**
   * Sets the value of start
   * @param start New value for start
   */
  public void setStart(Timestamp start) {
    this.start=start;
  }

  /**
   * Gets the current value of end
   * @return Current value of end
   */
  public Timestamp getEnd() {
    return end;
  }

  /**
   * Sets the value of end
   * @param end New value for end
   */
  public void setEnd(Timestamp end) {
    this.end=end;
  }

}