package com.myrunning.leaderboard.model;

/**
 *  File: DataResource.java
 *  Author:  Joshua Forester
 *  Date: 10/5/2009
 *  Description: Bean class for DataResource object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;


public class DataResource implements Serializable {

  private long id;

  @XStreamOmitField
  private long adminId;

  /**
   * Creates a new instance of DataResource
   */
  public DataResource() {
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
  @JsonIgnore
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

  public String toString() {

      String r = new String();
      r += "DataResource [";
      r += "id=" + id + ",";
      r += "adminId=" + adminId + "]";

      return r;
  }

}