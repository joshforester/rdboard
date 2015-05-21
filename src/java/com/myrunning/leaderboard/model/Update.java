package com.myrunning.leaderboard.model;

/**
 *  File: Update.java
 *  Author:  Joshua Forester
 *  Date: 1/4/2010
 *  Description: Bean class for Update object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;


@XStreamAlias("update")
public class Update implements Serializable {

  private long id;
  private long teamId;
  private Timestamp time;
  private String type;
  private String message;

  @XStreamOmitField
  private String isDeleted;

  public static String RANK_TYPE = "Rank";
  public static String CP_VISIT_TYPE = "CP Visit";

  private static List<String> types = 
      Arrays.asList(RANK_TYPE, 
		    CP_VISIT_TYPE);


  /**
   * Creates a new instance of Update
   */
  public Update() {
  }

  /**
   * Creates a new instance of Update
   */
  public Update(long teamId, Timestamp time, String type, String message) {
    this.teamId=teamId;
    this.time=time;
    this.type=type;
    this.message=message;
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
   * Gets the current value of message
   * @return Current value of message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the value of message
   * @param message New value for message
   */
  public void setMessage(String message) {
    this.message=message;
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

  /**
   * Gets the current value of types
   * @return Current value of types
   */
  public static List<String> getTypes() {
    return types;
  }

  public String toString() {

      String r = new String();
      r += "Update [";
      r += "id=" + getId() + ",";
      r += "teamId=" + teamId + ",";
      r += "time=" + time + ",";
      r += "type=" + type + ",";
      r += "message=" + message + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }
}