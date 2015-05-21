package com.myrunning.leaderboard.model;

/**
 *  File: Registration.java
 *  Author:  Joshua Forester
 *  Date: 9/3/2009
 *  Description: Bean class for Registration object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;

public class Registration implements Serializable {

  private long teamId;
  private long eventId;
  private long courseId;
  private long divisionId;
  private long member1Id;
  private long member2Id;
  private long member3Id;
  private long member4Id;
  private String teamName;
  private String member1EmergencyContactFirstName;
  private String member1EmergencyContactLastName;
  private String member1EmergencyContactRelation;
  private String member1EmergencyPhone;
  private String member1ShirtSize;
  private String member1ShoeSize;
  private String member2EmergencyContactFirstName;
  private String member2EmergencyContactLastName;
  private String member2EmergencyContactRelation;
  private String member2EmergencyPhone;
  private String member2ShirtSize;
  private String member2ShoeSize;
  private String member3EmergencyContactFirstName;
  private String member3EmergencyContactLastName;
  private String member3EmergencyContactRelation;
  private String member3EmergencyPhone;
  private String member3ShirtSize;
  private String member3ShoeSize;
  private String member4EmergencyContactFirstName;
  private String member4EmergencyContactLastName;
  private String member4EmergencyContactRelation;
  private String member4EmergencyPhone;
  private String member4ShirtSize;
  private String member4ShoeSize;

  /**
   * Creates a new instance of Registration
   */
  public Registration() {
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
   * Gets the current value of member1EmergencyContactFirstName
   * @return Current value of member1EmergencyContactFirstName
   */
  public String getMember1EmergencyContactFirstName() {
    return member1EmergencyContactFirstName;
  }

  /**
   * Sets the value of member1EmergencyContactFirstName
   * @param member1EmergencyContactFirstName New value for member1EmergencyContactFirstName
   */
  public void setMember1EmergencyContactFirstName(String member1EmergencyContactFirstName) {
    this.member1EmergencyContactFirstName=member1EmergencyContactFirstName;
  }

  /**
   * Gets the current value of member1EmergencyContactLastName
   * @return Current value of member1EmergencyContactLastName
   */
  public String getMember1EmergencyContactLastName() {
    return member1EmergencyContactLastName;
  }

  /**
   * Sets the value of member1EmergencyContactLastName
   * @param member1EmergencyContactLastName New value for member1EmergencyContactLastName
   */
  public void setMember1EmergencyContactLastName(String member1EmergencyContactLastName) {
    this.member1EmergencyContactLastName=member1EmergencyContactLastName;
  }

  /**
   * Gets the current value of member1EmergencyContactRelation
   * @return Current value of member1EmergencyContactRelation
   */
  public String getMember1EmergencyContactRelation() {
    return member1EmergencyContactRelation;
  }

  /**
   * Sets the value of member1EmergencyContactRelation
   * @param member1EmergencyContactRelation New value for member1EmergencyContactRelation
   */
  public void setMember1EmergencyContactRelation(String member1EmergencyContactRelation) {
    this.member1EmergencyContactRelation=member1EmergencyContactRelation;
  }

  /**
   * Gets the current value of member1EmergencyPhone
   * @return Current value of member1EmergencyPhone
   */
  public String getMember1EmergencyPhone() {
    return member1EmergencyPhone;
  }

  /**
   * Sets the value of member1EmergencyPhone
   * @param member1EmergencyPhone New value for member1EmergencyPhone
   */
  public void setMember1EmergencyPhone(String member1EmergencyPhone) {
    this.member1EmergencyPhone=member1EmergencyPhone;
  }

  /**
   * Gets the current value of member1ShirtSize
   * @return Current value of member1ShirtSize
   */
  public String getMember1ShirtSize() {
    return member1ShirtSize;
  }

  /**
   * Sets the value of member1ShirtSize
   * @param member1ShirtSize New value for member1ShirtSize
   */
  public void setMember1ShirtSize(String member1ShirtSize) {
    this.member1ShirtSize=member1ShirtSize;
  }

  /**
   * Gets the current value of member1ShoeSize
   * @return Current value of member1ShoeSize
   */
  public String getMember1ShoeSize() {
    return member1ShoeSize;
  }

  /**
   * Sets the value of member1ShoeSize
   * @param member1ShoeSize New value for member1ShoeSize
   */
  public void setMember1ShoeSize(String member1ShoeSize) {
    this.member1ShoeSize=member1ShoeSize;
  }

  /**
   * Gets the current value of member2EmergencyContactFirstName
   * @return Current value of member2EmergencyContactFirstName
   */
  public String getMember2EmergencyContactFirstName() {
    return member2EmergencyContactFirstName;
  }

  /**
   * Sets the value of member2EmergencyContactFirstName
   * @param member2EmergencyContactFirstName New value for member2EmergencyContactFirstName
   */
  public void setMember2EmergencyContactFirstName(String member2EmergencyContactFirstName) {
    this.member2EmergencyContactFirstName=member2EmergencyContactFirstName;
  }

  /**
   * Gets the current value of member2EmergencyContactLastName
   * @return Current value of member2EmergencyContactLastName
   */
  public String getMember2EmergencyContactLastName() {
    return member2EmergencyContactLastName;
  }

  /**
   * Sets the value of member2EmergencyContactLastName
   * @param member2EmergencyContactLastName New value for member2EmergencyContactLastName
   */
  public void setMember2EmergencyContactLastName(String member2EmergencyContactLastName) {
    this.member2EmergencyContactLastName=member2EmergencyContactLastName;
  }

  /**
   * Gets the current value of member2EmergencyContactRelation
   * @return Current value of member2EmergencyContactRelation
   */
  public String getMember2EmergencyContactRelation() {
    return member2EmergencyContactRelation;
  }

  /**
   * Sets the value of member2EmergencyContactRelation
   * @param member2EmergencyContactRelation New value for member2EmergencyContactRelation
   */
  public void setMember2EmergencyContactRelation(String member2EmergencyContactRelation) {
    this.member2EmergencyContactRelation=member2EmergencyContactRelation;
  }

  /**
   * Gets the current value of member2EmergencyPhone
   * @return Current value of member2EmergencyPhone
   */
  public String getMember2EmergencyPhone() {
    return member2EmergencyPhone;
  }

  /**
   * Sets the value of member2EmergencyPhone
   * @param member2EmergencyPhone New value for member2EmergencyPhone
   */
  public void setMember2EmergencyPhone(String member2EmergencyPhone) {
    this.member2EmergencyPhone=member2EmergencyPhone;
  }

  /**
   * Gets the current value of member2ShirtSize
   * @return Current value of member2ShirtSize
   */
  public String getMember2ShirtSize() {
    return member2ShirtSize;
  }

  /**
   * Sets the value of member2ShirtSize
   * @param member2ShirtSize New value for member2ShirtSize
   */
  public void setMember2ShirtSize(String member2ShirtSize) {
    this.member2ShirtSize=member2ShirtSize;
  }

  /**
   * Gets the current value of member2ShoeSize
   * @return Current value of member2ShoeSize
   */
  public String getMember2ShoeSize() {
    return member2ShoeSize;
  }

  /**
   * Sets the value of member2ShoeSize
   * @param member2ShoeSize New value for member2ShoeSize
   */
  public void setMember2ShoeSize(String member2ShoeSize) {
    this.member2ShoeSize=member2ShoeSize;
  }

  /**
   * Gets the current value of member3EmergencyContactFirstName
   * @return Current value of member3EmergencyContactFirstName
   */
  public String getMember3EmergencyContactFirstName() {
    return member3EmergencyContactFirstName;
  }

  /**
   * Sets the value of member3EmergencyContactFirstName
   * @param member3EmergencyContactFirstName New value for member3EmergencyContactFirstName
   */
  public void setMember3EmergencyContactFirstName(String member3EmergencyContactFirstName) {
    this.member3EmergencyContactFirstName=member3EmergencyContactFirstName;
  }

  /**
   * Gets the current value of member3EmergencyContactLastName
   * @return Current value of member3EmergencyContactLastName
   */
  public String getMember3EmergencyContactLastName() {
    return member3EmergencyContactLastName;
  }

  /**
   * Sets the value of member3EmergencyContactLastName
   * @param member3EmergencyContactLastName New value for member3EmergencyContactLastName
   */
  public void setMember3EmergencyContactLastName(String member3EmergencyContactLastName) {
    this.member3EmergencyContactLastName=member3EmergencyContactLastName;
  }

  /**
   * Gets the current value of member3EmergencyContactRelation
   * @return Current value of member3EmergencyContactRelation
   */
  public String getMember3EmergencyContactRelation() {
    return member3EmergencyContactRelation;
  }

  /**
   * Sets the value of member3EmergencyContactRelation
   * @param member3EmergencyContactRelation New value for member3EmergencyContactRelation
   */
  public void setMember3EmergencyContactRelation(String member3EmergencyContactRelation) {
    this.member3EmergencyContactRelation=member3EmergencyContactRelation;
  }

  /**
   * Gets the current value of member3EmergencyPhone
   * @return Current value of member3EmergencyPhone
   */
  public String getMember3EmergencyPhone() {
    return member3EmergencyPhone;
  }

  /**
   * Sets the value of member3EmergencyPhone
   * @param member3EmergencyPhone New value for member3EmergencyPhone
   */
  public void setMember3EmergencyPhone(String member3EmergencyPhone) {
    this.member3EmergencyPhone=member3EmergencyPhone;
  }

  /**
   * Gets the current value of member3ShirtSize
   * @return Current value of member3ShirtSize
   */
  public String getMember3ShirtSize() {
    return member3ShirtSize;
  }

  /**
   * Sets the value of member3ShirtSize
   * @param member3ShirtSize New value for member3ShirtSize
   */
  public void setMember3ShirtSize(String member3ShirtSize) {
    this.member3ShirtSize=member3ShirtSize;
  }

  /**
   * Gets the current value of member3ShoeSize
   * @return Current value of member3ShoeSize
   */
  public String getMember3ShoeSize() {
    return member3ShoeSize;
  }

  /**
   * Sets the value of member3ShoeSize
   * @param member3ShoeSize New value for member3ShoeSize
   */
  public void setMember3ShoeSize(String member3ShoeSize) {
    this.member3ShoeSize=member3ShoeSize;
  }

  /**
   * Gets the current value of member4EmergencyContactFirstName
   * @return Current value of member4EmergencyContactFirstName
   */
  public String getMember4EmergencyContactFirstName() {
    return member4EmergencyContactFirstName;
  }

  /**
   * Sets the value of member4EmergencyContactFirstName
   * @param member4EmergencyContactFirstName New value for member4EmergencyContactFirstName
   */
  public void setMember4EmergencyContactFirstName(String member4EmergencyContactFirstName) {
    this.member4EmergencyContactFirstName=member4EmergencyContactFirstName;
  }

  /**
   * Gets the current value of member4EmergencyContactLastName
   * @return Current value of member4EmergencyContactLastName
   */
  public String getMember4EmergencyContactLastName() {
    return member4EmergencyContactLastName;
  }

  /**
   * Sets the value of member4EmergencyContactLastName
   * @param member4EmergencyContactLastName New value for member4EmergencyContactLastName
   */
  public void setMember4EmergencyContactLastName(String member4EmergencyContactLastName) {
    this.member4EmergencyContactLastName=member4EmergencyContactLastName;
  }

  /**
   * Gets the current value of member4EmergencyContactRelation
   * @return Current value of member4EmergencyContactRelation
   */
  public String getMember4EmergencyContactRelation() {
    return member4EmergencyContactRelation;
  }

  /**
   * Sets the value of member4EmergencyContactRelation
   * @param member4EmergencyContactRelation New value for member4EmergencyContactRelation
   */
  public void setMember4EmergencyContactRelation(String member4EmergencyContactRelation) {
    this.member4EmergencyContactRelation=member4EmergencyContactRelation;
  }

  /**
   * Gets the current value of member4EmergencyPhone
   * @return Current value of member4EmergencyPhone
   */
  public String getMember4EmergencyPhone() {
    return member4EmergencyPhone;
  }

  /**
   * Sets the value of member4EmergencyPhone
   * @param member4EmergencyPhone New value for member4EmergencyPhone
   */
  public void setMember4EmergencyPhone(String member4EmergencyPhone) {
    this.member4EmergencyPhone=member4EmergencyPhone;
  }

  /**
   * Gets the current value of member4ShirtSize
   * @return Current value of member4ShirtSize
   */
  public String getMember4ShirtSize() {
    return member4ShirtSize;
  }

  /**
   * Sets the value of member4ShirtSize
   * @param member4ShirtSize New value for member4ShirtSize
   */
  public void setMember4ShirtSize(String member4ShirtSize) {
    this.member4ShirtSize=member4ShirtSize;
  }

  /**
   * Gets the current value of member4ShoeSize
   * @return Current value of member4ShoeSize
   */
  public String getMember4ShoeSize() {
    return member4ShoeSize;
  }

  /**
   * Sets the value of member4ShoeSize
   * @param member4ShoeSize New value for member4ShoeSize
   */
  public void setMember4ShoeSize(String member4ShoeSize) {
    this.member4ShoeSize=member4ShoeSize;
  }

}