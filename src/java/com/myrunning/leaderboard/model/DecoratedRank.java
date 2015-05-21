package com.myrunning.leaderboard.model;

/**
 *  File: DecoratedRank.java
 *  Author:  Joshua Forester
 *  Date: 1/20/2010
 *  Description: Bean class for DecoratedRank object.
 **/


import java.io.Serializable;


public class DecoratedRank extends Rank implements Serializable {

  private int teamNumber;
  private String teamName;
  private long teamCourseId;
  private long teamDivisionId;

  /**
   * Creates a new instance of DecoratedRank
   */
  public DecoratedRank() {
  }

    /*
  public DecoratedRank(Rank rank) {
      this.id=rank.getId();
      this.teamId=rank.getTeamId();
      this.time=rank.getTime();
      this.divisionPlace=rank.getDivisionPlace();
      this.coursePlace=rank.getCoursePlace();
      this.isDeleted=rank.getIsDeleted();
  }
    */

  /**
   * Gets the current value of teamNumber
   * @return Current value of teamNumber
   */
  public int getTeamNumber() {
    return teamNumber;
  }

  /**
   * Sets the value of teamNumber
   * @param teamNumber New value for teamNumber
   */
  public void setTeamNumber(int teamNumber) {
    this.teamNumber=teamNumber;
  }

  /**
   * Gets the current value of teamName
   * @return Current value of teamName
   */
  public String getTeamName() {
    return teamName;
  }

  /**
   * Sets the value of teamName
   * @param teamName New value for teamName
   */
  public void setTeamName(String teamName) {
    this.teamName=teamName;
  }

  /**
   * Gets the current value of teamCourseId
   * @return Current value of teamCourseId
   */
  public long getTeamCourseId() {
    return teamCourseId;
  }

  /**
   * Sets the value of teamCourseId
   * @param teamCourseId New value for teamCourseId
   */
  public void setTeamCourseId(long teamCourseId) {
    this.teamCourseId=teamCourseId;
  }

  /**
   * Gets the current value of teamDivisionId
   * @return Current value of teamDivisionId
   */
  public long getTeamDivisionId() {
    return teamDivisionId;
  }

  /**
   * Sets the value of teamDivisionId
   * @param teamDivisionId New value for teamDivisionId
   */
  public void setTeamDivisionId(long teamDivisionId) {
    this.teamDivisionId=teamDivisionId;
  }

  public String toString() {

      String r = new String();
      r += "DecoratedRank [";
      r += "teamNumber=" + teamNumber + ",";
      r += "teamName=" + teamName + ",";
      r += "teamCourseId=" + teamCourseId + ",";
      r += "teamDivisionId=" + teamDivisionId + "]";

      return super.toString() + r;
  }


}