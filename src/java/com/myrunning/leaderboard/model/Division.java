package com.myrunning.leaderboard.model;

/**
 *  File: Division.java
 *  Author:  Joshua Forester
 *  Date: 8/18/2009
 *  Description: Bean class for Division object.
 **/


import java.util.List;
import java.util.Arrays;
import java.io.Serializable;
import org.springframework.util.StringUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;


@XStreamAlias("division")
public class Division extends DataResource implements Serializable {

  public static int MEMBER_COUNT_MIN = 1;
  public static int MEMBER_COUNT_MAX = 4;

  private long eventId;
  private String name;
  private int memberCount;
  private String consistency;
  private String isElite;

  @XStreamOmitField
  private String isDeleted;

  private static List<String> consistencies = 
      Arrays.asList("coed",
		    "open",
		    "male", 
		    "female",
		    "masters",
		    "masterscoed",
		    "mastersopen",
		    "mastersmale",
		    "mastersfemale");

  /**
   * Creates a new instance of Division
   */
  public Division() {
      setIsElite(new String("no"));
      setIsDeleted(new String("no"));
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

  /**
   * Gets the current value of memberCount
   * @return Current value of memberCount
   */
  public int getMemberCount() {
    return memberCount;
  }

  /**
   * Sets the value of memberCount
   * @param memberCount New value for memberCount
   */
  public void setMemberCount(int memberCount) {
    this.memberCount=memberCount;
  }

  /**
   * Gets the current value of consistency
   * @return Current value of consistency
   */
  public String getConsistency() {
    return consistency;
  }

  /**
   * Sets the value of consistency
   * @param consistency New value for consistency
   */
  public void setConsistency(String consistency) {
    this.consistency=consistency;
  }

  /**
   * Gets the current value of isElite
   * @return Current value of isElite
   */
  public String getIsElite() {
    return isElite;
  }

  /**
   * Sets the value of isElite
   * @param isElite New value for isElite
   */
  public void setIsElite(String isElite) {
    if (StringUtils.hasText(isElite)) {
	this.isElite=isElite;
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

  /**
   * Gets the current value of consistencies
   * @return Current value of consistencies
   */
  public static List<String> getConsistencies() {
    return consistencies;
  }

  public String toString() {

      String r = new String();
      r += "Division [";
      r += "id=" + getId() + ",";
      r += "eventId=" + eventId + ",";
      r += "name=" + name + ",";
      r += "memberCount=" + memberCount + ",";
      r += "consistency=" + consistency + ",";
      r += "isElite=" + isElite + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }

}