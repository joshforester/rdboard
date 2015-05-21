package com.myrunning.leaderboard.model;

/**
 *  File: CpOrderShift.java
 *  Author:  Joshua Forester
 *  Date: 11/6/2009
 *  Description: Bean class for CpOrderShift object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class CpOrderShift implements Serializable {

  private long cpId;
  private String change;

  /**
   * Creates a new instance of CpOrderShift
   */
  public CpOrderShift() {
  }

  /**
   * Gets the current value of cpId
   * @return Current value of cpId
   */
  public long getCpId() {
    return cpId;
  }

  /**
   * Sets the value of cpId
   * @param cpId New value for cpId
   */
  public void setCpId(long cpId) {
    this.cpId=cpId;
  }

  /**
   * Gets the current value of change
   * @return Current value of change
   */
  public String getChange() {
    return change;
  }

  /**
   * Sets the value of change
   * @param change New value for change
   */
  public void setChange(String change) {
    this.change=change;
  }

}