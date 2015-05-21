package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: RegistrationQuery.java
 *  Author:  Joshua Forester
 *  Date: 9/3/2009
 *  Description: Bean class for RegistrationQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class RegistrationQuery implements Serializable {

  private long courseId;
  private long member1Id;
  private long member2Id;
  private long member3Id;
  private long member4Id;

  /**
   * Creates a new instance of RegistrationQuery
   */
  public RegistrationQuery() {
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
   * Gets the current value of member1Id
   * @return Current value of member1Id
   */
  public long getMember1Id() {
    return member1Id;
  }

  /**
   * Sets the value of member1Id
   * @param member1Id New value for member1Id
   */
  public void setMember1Id(long member1Id) {
    this.member1Id=member1Id;
  }

  /**
   * Gets the current value of member2Id
   * @return Current value of member2Id
   */
  public long getMember2Id() {
    return member2Id;
  }

  /**
   * Sets the value of member2Id
   * @param member2Id New value for member2Id
   */
  public void setMember2Id(long member2Id) {
    this.member2Id=member2Id;
  }

  /**
   * Gets the current value of member3Id
   * @return Current value of member3Id
   */
  public long getMember3Id() {
    return member3Id;
  }

  /**
   * Sets the value of member3Id
   * @param member3Id New value for member3Id
   */
  public void setMember3Id(long member3Id) {
    this.member3Id=member3Id;
  }

  /**
   * Gets the current value of member4Id
   * @return Current value of member4Id
   */
  public long getMember4Id() {
    return member4Id;
  }

  /**
   * Sets the value of member4Id
   * @param member4Id New value for member4Id
   */
  public void setMember4Id(long member4Id) {
    this.member4Id=member4Id;
  }

}