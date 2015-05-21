package com.myrunning.leaderboard.model;

/**
 *  File: Identity.java
 *  Author:  Joshua Forester
 *  Date: 10/25/2009
 *  Description: Bean class for Identity object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class Identity extends DataResource implements Serializable {

  private String isDeleted;

  /**
   * Creates a new instance of Identity
   */
  public Identity() {
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
      r += "Identity [";
      r += "id=" + getId() + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }

}