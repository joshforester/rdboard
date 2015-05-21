package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: TeamIdAndCpIdQuery.java
 *  Author:  Joshua Forester
 *  Date: 8/25/2009
 *  Description: Bean class for TeamIdAndCpIdQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class TeamIdAndCpIdQuery implements Serializable {

  private long teamId;
  private long cpId;

  /**
   * Creates a new instance of TeamIdAndCpIdQuery
   */
  public TeamIdAndCpIdQuery() {
  }

  /**
   * Gets the current value of teamId
   * @return Current value of teamId
   */
  public long getTeamId() {
    return teamId;
  }

  /**
   * Sets the value of teamId
   * @param teamId New value for teamId
   */
  public void setTeamId(long teamId) {
    this.teamId=teamId;
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

}