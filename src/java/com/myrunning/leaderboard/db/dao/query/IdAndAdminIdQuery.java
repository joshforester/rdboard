package com.myrunning.leaderboard.db.dao.query;

/**
 *  File: IdAndAdminIdQuery.java
 *  Author:  Joshua Forester
 *  Date: 10/16/2009
 *  Description: Bean class for IdAndAdminIdQuery object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class IdAndAdminIdQuery implements Serializable {

  private long id;
  private long adminId;

  /**
   * Creates a new instance of IdAndAdminIdQuery
   */
  public IdAndAdminIdQuery() {
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
   * Gets the current value of adminId
   * @return Current value of adminId
   */
  public long getAdminId() {
    return adminId;
  }

  /**
   * Sets the value of adminId
   * @param adminId New value for adminId
   */
  public void setAdminId(long adminId) {
    this.adminId=adminId;
  }

}