package com.myrunning.leaderboard.model;

/**
 *  File: Rank.java
 *  Author:  Joshua Forester
 *  Date: 1/17/2010
 *  Description: Bean class for Rank object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;


@XStreamAlias("rank")
public class Rank implements Serializable, Comparable<Rank> {

  private long id;
  private long teamId;
  private Timestamp time;
  private int divisionPlace;
  private int coursePlace;

  @XStreamOmitField
  private int divisionPlaceChange;

  @XStreamOmitField
  private int coursePlaceChange;

  @XStreamOmitField
  private String isDeleted;

  /**
   * Creates a new instance of Rank
   */
  public Rank() {
  }

  /**
   * Gets the current value of id
   * @return Current value of id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the value of id
   * @param id New value for id
   */
  public void setId(long id) {
    this.id=id;
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
   * Gets the current value of time
   * @return Current value of time
   */
  public Timestamp getTime() {
    return time;
  }

  /**
   * Sets the value of time
   * @param time New value for time
   */
  public void setTime(Timestamp time) {
    this.time=time;
  }

  /**
   * Gets the current value of divisionPlace
   * @return Current value of divisionPlace
   */
  public int getDivisionPlace() {
    return divisionPlace;
  }

  /**
   * Sets the value of divisionPlace
   * @param divisionPlace New value for divisionPlace
   */
  public void setDivisionPlace(int divisionPlace) {
    this.divisionPlace=divisionPlace;
  }

  /**
   * Gets the current value of coursePlace
   * @return Current value of coursePlace
   */
  public int getCoursePlace() {
    return coursePlace;
  }

  /**
   * Sets the value of coursePlace
   * @param coursePlace New value for coursePlace
   */
  public void setCoursePlace(int coursePlace) {
    this.coursePlace=coursePlace;
  }

  /**
   * Gets the current value of divisionPlaceChange
   * @return Current value of divisionPlaceChange
   */
  public int getDivisionPlaceChange() {
    return divisionPlaceChange;
  }

  /**
   * Gets the current value of coursePlaceChange
   * @return Current value of coursePlaceChange
   */
  public int getCoursePlaceChange() {
    return coursePlaceChange;
  }

  /**
   *  Calculates chonological differences between Rank objects,
   *  assuming the parameter rank was chronologically before
   *  this one.
   *  @param previouRank the Rank object chronologically before this one
   */
  public void setRankChangeFrom(Rank previousRank) {
      if (previousRank == null) {
	  divisionPlaceChange = 0;
	  coursePlaceChange = 0;
      } else {
	  divisionPlaceChange = previousRank.getDivisionPlace() - divisionPlace;
	  coursePlaceChange = previousRank.getCoursePlace() - coursePlace;
      }
  }

  /**
   * Gets the current value of isDeleted
   * @return Current value of isDeleted
   */
  @JsonIgnore
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


    /*
    public static Rank getInstanceFromDecoratedRank(HashMap decoratedRank) {
	Rank r = new Rank();
	r.setTeamId(((Long) decoratedRank.get("teamId")).longValue());
	r.setTime((Timestamp) decoratedRank.get("time"));
	r.setDivisionPlace(((Integer) decoratedRank.get("divisionPlace")).intValue());
	r.setCoursePlace(((Integer) decoratedRank.get("coursePlace")).intValue());
	r.setIsDeleted((String) decoratedRank.get("isDeleted"));

	return r;
    }
    */


    public boolean sameValues(Rank rank) {
	
        if ((teamId != rank.getTeamId()) ||
	    (divisionPlace != rank.getDivisionPlace()) ||
	    (coursePlace != rank.getCoursePlace())) {
	    
	    return false;
	    
	}
	
	return true;
    }

  public int compareTo(Rank rank) {

      if (getTime().after(rank.getTime())) {
	  return 1;
      } else if (getTime().before(rank.getTime())) {
	  return -1;
      } else {
	  return 0;
      }
  }

  public String toString() {

      String r = new String();
      r += "Rank [";
      r += "id=" + id + ",";
      r += "teamId=" + teamId + ",";
      r += "time=" + time + ",";
      r += "divisionPlace=" + divisionPlace + ",";
      r += "coursePlace=" + coursePlace + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }


}