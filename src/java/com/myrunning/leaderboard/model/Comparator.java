package com.myrunning.leaderboard.model;

/**
 *  File: Comparator.java
 *  Author:  Joshua Forester
 *  Date: 8/26/2009
 *  Description: Bean class for Comparator object.
 **/


import java.util.List;
import java.util.Arrays;
import java.io.Serializable;
import java.sql.Timestamp;


public class Comparator extends DataResource implements Serializable, Comparable<Comparator> {

  private long courseId;
  private String type;
  private String tieAction;
  private int comparatorOrder;
  private String isDeleted;


  private static List<String> tieActions = 
      Arrays.asList("continue", 
		    "rankeven");

  private static List<String> types = 
      Arrays.asList("missedcutoff", 
		    "unofficial", 
		    "incomplete", 
		    "withdrawn", 
		    "disqualified",
		    "didnotstart",
		    "mandatorycps", 
		    "optionalcps", 
		    "cpweight", 
		    "lastvisitedcporder", 
		    "lastvisitedcporderdeparture", 
		    "lastvisitedcporderarrival", 
		    "leastskippedmandatorycps", 
		    "leastskippedoptionalcps", 
		    "leastskippedcpweight");


  /**
   * Creates a new instance of Comparator
   */
  public Comparator() {
      this.setIsDeleted(new String("no"));
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
   * Gets the current value of type
   * @return Current value of type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the value of type
   * @param type New value for type
   */
  public void setType(String type) {
    this.type=type;
  }

  /**
   * Gets the current value of tieAction
   * @return Current value of tieAction
   */
  public String getTieAction() {
    return tieAction;
  }

  /**
   * Sets the value of tieAction
   * @param tieAction New value for tieAction
   */
  public void setTieAction(String tieAction) {
    this.tieAction=tieAction;
  }

  /**
   * Gets the current value of comparatorOrder
   * @return Current value of comparatorOrder
   */
  public int getComparatorOrder() {
    return comparatorOrder;
  }

  /**
   * Sets the value of cOrder
   * @param comparatorOrder New value for cOrder
   */
  public void setComparatorOrder(int comparatorOrder) {
    this.comparatorOrder=comparatorOrder;
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

  /**
   * Gets the current value of tieActions
   * @return Current value of tieActions
   */
  public static List<String> getTieActions() {
    return tieActions;
  }

  /**
   * Gets the current value of types
   * @return Current value of types
   */
  public static List<String> getTypes() {
    return types;
  }

  /**
   * Implementation of the Comparable interface, based on comparatorOrder
   * @return negative if this is less than, 0 if it is equal to, and positive 
   *         is greater than o
   */
  public int compareTo(Comparator o) {
      return this.getComparatorOrder() - o.getComparatorOrder();
  }


  public String toString() {

      String r = new String();
      r += "Comparator [";
      r += "id=" + getId() + ",";
      r += "courseId=" + courseId + ",";
      r += "type=" + type + ",";
      r += "tieAction=" + tieAction + ",";
      r += "comparatorOrder=" + comparatorOrder + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }


}