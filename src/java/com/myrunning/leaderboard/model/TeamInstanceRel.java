package com.myrunning.leaderboard.model;

/**
 *  File: TeamInstanceRel.java
 *  Author:  Joshua Forester
 *  Date: 9/8/2009
 *  Description: Bean class for TeamInstanceRel object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class TeamInstanceRel implements Serializable {

  private long teamId;
  private long competitorId;
  private String isCaptain;
  private String isDeleted;

  /**
   * Creates a new instance of TeamInstanceRel
   */
  public TeamInstanceRel() {
      this.setIsCaptain("no");
      this.setIsDeleted("no");
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
   * Gets the current value of competitorId
   * @return Current value of competitorId
   */
  public long getCompetitorId() {
    return competitorId;
  }

  /**
   * Sets the value of competitorId
   * @param competitorId New value for competitorId
   */
  public void setCompetitorId(long competitorId) {
    this.competitorId=competitorId;
  }

  /**
   * Gets the current value of isCaptain
   * @return Current value of isCaptain
   */
  public String getIsCaptain() {
    return isCaptain;
  }

  /**
   * Sets the value of isCaptain
   * @param isCaptain New value for isCaptain
   */
  public void setIsCaptain(String isCaptain) {
    this.isCaptain=isCaptain;
  }

  /**
   * Gets the current value of isDeleted
   * @return Current value of isDeleted
   */
  public String getIsDeleted() {
    return isDeleted;
  }

  /**
   * Sets the value of isDeleted
   * @param isDeleted New value for isDeleted
   */
  public void setIsDeleted(String isDeleted) {
    this.isDeleted=isDeleted;
  }

  public String toString() {

      String r = new String();
      r += "TeamInstanceRel [";
      r += "teamId=" + teamId + ",";
      r += "competitorId=" + competitorId + ",";
      r += "isCaptain=" + isCaptain + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }

}