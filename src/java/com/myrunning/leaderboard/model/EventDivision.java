package com.myrunning.leaderboard.model;

/**
 *  File: EventDivision.java
 *  Author:  Joshua Forester
 *  Date: 8/24/2009
 *  Description: Bean class for EventDivision object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class EventDivision extends DataResource implements Serializable {

  private long eventId;
  private long divisionId;
  private String eventName;
  private String divisionName;

  /**
   * Creates a new instance of EventDivision
   */
  public EventDivision() {
  }

  /**
   * Gets the current value of eventId
   * @return Current value of eventId
   */
  public long getEventId() {
    return eventId;
  }

  /**
   * Sets the value of eventId
   * @param eventId New value for eventId
   */
  public void setEventId(long eventId) {
    this.eventId=eventId;
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
   * Gets the current value of eventName
   * @return Current value of eventName
   */
  public String getEventName() {
    return eventName;
  }

  /**
   * Sets the value of eventName
   * @param eventName New value for eventName
   */
  public void setEventName(String eventName) {
    this.eventName=eventName;
  }

  /**
   * Gets the current value of divisionName
   * @return Current value of divisionName
   */
  public String getDivisionName() {
    return divisionName;
  }

  /**
   * Sets the value of divisionName
   * @param divisionName New value for divisionName
   */
  public void setDivisionName(String divisionName) {
    this.divisionName=divisionName;
  }


  public String toString() {
      String r = new String();
      r += "EventDivision [";
      r += "id=" + getId() + ",";
      r += "eventId=" + eventId + ",";
      r += "divisionId=" + divisionId + ",";
      r += "eventName=" + eventName + ",";
      r += "divisionName=" + divisionName + "]";

      return r;
  
  }


}