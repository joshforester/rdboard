package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: DivisionIdAndNameQuery.java
 *  Author:  Joshua Forester
 *  Date: 8/24/2009
 *  Description: Bean class for DivisionIdAndNameQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class DivisionIdAndNameQuery implements Serializable {

  private long divisionId;
  private String name;

  /**
   * Creates a new instance of DivisionIdAndNameQuery
   */
  public DivisionIdAndNameQuery() {
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