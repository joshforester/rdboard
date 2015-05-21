package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: EventIdAndNameQuery.java
 *  Author:  Joshua Forester
 *  Date: 8/18/2009
 *  Description: Bean class for EventIdAndNameQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class EventIdAndNameQuery implements Serializable {

  private long eventId;
  private String name;

  /**
   * Creates a new instance of EventIdAndNameQuery
   */
  public EventIdAndNameQuery() {
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