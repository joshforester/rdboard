package com.myrunning.leaderboard.model;

/**
 *  File: DecoratedCpVisit.java
 *  Author:  Joshua Forester
 *  Date: 1/19/2010
 *  Description: Bean class for DecoratedCpVisit object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class DecoratedCpVisit extends CpVisit implements Serializable {

  private int teamNumber;
  private String teamName;
  private String cpName;
  private String cpTaName;
  private String cpCpOrder;


  /**
   * Creates a new instance of DecoratedCpVisit
   */
  public DecoratedCpVisit() {
  }

    /*
  public DecoratedCpVisit(CpVisit cpVisit) {
      this.teamId=cpVisit.getTeamId();
      this.cpId=cpVisit.getCpId();
      this.lastModified=cpVisit.getLastModified();
      this.arrival=cpVisit.getArrival();
      this.departure=cpVisit.getDeparture();
      this.timeBonusAssessed=cpVisit.getTimeBonusAssessed();
      this.timePenaltyAssessed=cpVisit.getTimePenaltyAssessed();
      this.cpBonusAssessed=cpVisit.getCpBonusAssessed();
      this.cpPenaltyAssessed=cpVisit.getCpPenaltyAssessed();
      this.weightBonusAssessed=cpVisit.getWeightBonusAssessed();
      this.weightPenaltyAssessed=cpVisit.getWeightPenaltyAssessed();
      this.bonusAssessedReason=cpVisit.getBonusAssessedReason();
      this.penaltyAssessedReason=cpVisit.getPenaltyAssessedReason();
      this.isAcquired=cpVisit.getIsAcquired();
      this.isSkipped=cpVisit.getIsSkipped();
      this.isMissedCutoff=cpVisit.getIsMissedCutoff();
      this.isUnofficial=cpVisit.getIsUnofficial();
      this.isIncomplete=cpVisit.getIsIncomplete();
      this.isWithdrawn=cpVisit.getIsWithdrawn();
      this.isDisqualified=cpVisit.getIsDisqualfied();
      this.divisionPlace=cpVisit.getDivisionPlace();
      this.coursePlace=cpVisit.getCoursePlace();
      this.isDeleted=cpVisit.getIsDeleted();
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
   * Gets the current value of cpName
   * @return Current value of cpName
   */
  public String getCpName() {
    return cpName;
  }

  /**
   * Sets the value of cpName
   * @param cpName New value for cpName
   */
  public void setCpName(String cpName) {
    this.cpName=cpName;
  }

  /**
   * Gets the current value of cpTaName
   * @return Current value of cpTaName
   */
  public String getCpTaName() {
    return cpTaName;
  }

  /**
   * Sets the value of cpTaName
   * @param cpTaName New value for cpTaName
   */
  public void setCpTaName(String cpTaName) {
    this.cpTaName=cpTaName;
  }

  /**
   * Gets the current value of cpCpOrder
   * @return Current value of cpCpOrder
   */
  public String getCpCpOrder() {
    return cpCpOrder;
  }

  /**
   * Sets the value of cpCpOrder
   * @param cpCpOrder New value for cpCpOrder
   */
  public void setCpCpOrder(String cpCpOrder) {
    this.cpCpOrder=cpCpOrder;
  }
}