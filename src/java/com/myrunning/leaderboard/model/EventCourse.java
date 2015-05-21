package com.myrunning.leaderboard.model;

/**
 *  File: EventCourse.java
 *  Author:  Joshua Forester
 *  Date: 8/20/2009
 *  Description: Bean class for EventCourse object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class EventCourse extends DataResource implements Serializable {

  private long eventId;
  private long courseId;
  private String eventName;
  private String courseName;

  /**
   * Creates a new instance of EventCourse
   */
  public EventCourse() {
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
   * Gets the current value of courseId
   * @return Current value of courseId
   */
  public long getCourseId() {
    return courseId;
  }

  /**
   * Sets the value of courseId
   * @param courseId New value for courseId
   */
  public void setCourseId(long courseId) {
    this.courseId=courseId;
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
   * Gets the current value of courseName
   * @return Current value of courseName
   */
  public String getCourseName() {
    return courseName;
  }

  /**
   * Sets the value of courseName
   * @param courseName New value for courseName
   */
  public void setCourseName(String courseName) {
    this.courseName=courseName;
  }

  public String toString() {
      String r = new String();
      r += "EventCourse [";
      r += "id=" + getId() + ",";
      r += "eventId=" + eventId + ",";
      r += "courseId=" + courseId + ",";
      r += "eventName=" + eventName + ",";
      r += "courseName=" + courseName + "]";

      return r;
  
  }


}