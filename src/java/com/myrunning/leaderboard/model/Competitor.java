package com.myrunning.leaderboard.model;

/**
 *  File: Competitor.java
 *  Author:  Joshua Forester
 *  Date: 8/21/2009
 *  Description: Bean class for Competitor object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;


@XStreamAlias("competitor")
public class Competitor extends Person implements Serializable {

  @XStreamOmitField
  private long personId;

  @XStreamOmitField
  private String emergencyContactFirstName;

  @XStreamOmitField
  private String emergencyContactLastName;

  @XStreamOmitField
  private String emergencyContactRelation;

  @XStreamOmitField
  private String emergencyPhone;

  @XStreamOmitField
  private String shirtSize;

  @XStreamOmitField
  private String shoeSize;


  /**
   * Creates a new instance of Competitor
   */
  public Competitor() {
  }

  /**
   * Gets the current value of personId
   * @return Current value of personId
   */
  @JsonIgnore
  public long getPersonId() {
    return personId;
  }

  /**
   * Sets the value of personId
   * @param personId New value for personId
   */
  public void setPersonId(long personId) {
    this.personId=personId;
  }

  /**
   * Gets the current value of emergencyContactFirstName
   * @return Current value of emergencyContactFirstName
   */
  @JsonIgnore
  public String getEmergencyContactFirstName() {
    return emergencyContactFirstName;
  }

  /**
   * Sets the value of emergencyContactFirstName
   * @param emergencyContactFirstName New value for emergencyContactFirstName
   */
  public void setEmergencyContactFirstName(String emergencyContactFirstName) {
    this.emergencyContactFirstName=emergencyContactFirstName;
  }

  /**
   * Gets the current value of emergencyContactLastName
   * @return Current value of emergencyContactLastName
   */
  @JsonIgnore
  public String getEmergencyContactLastName() {
    return emergencyContactLastName;
  }

  /**
   * Sets the value of emergencyContactLastName
   * @param emergencyContactLastName New value for emergencyContactLastName
   */
  public void setEmergencyContactLastName(String emergencyContactLastName) {
    this.emergencyContactLastName=emergencyContactLastName;
  }

  /**
   * Gets the current value of emergencyContactRelation
   * @return Current value of emergencyContactRelation
   */
  @JsonIgnore
  public String getEmergencyContactRelation() {
    return emergencyContactRelation;
  }

  /**
   * Sets the value of emergencyContactRelation
   * @param emergencyContactRelation New value for emergencyContactRelation
   */
  public void setEmergencyContactRelation(String emergencyContactRelation) {
    this.emergencyContactRelation=emergencyContactRelation;
  }

  /**
   * Gets the current value of emergencyPhone
   * @return Current value of emergencyPhone
   */
  @JsonIgnore
  public String getEmergencyPhone() {
    return emergencyPhone;
  }

  /**
   * Sets the value of emergencyPhone
   * @param emergencyPhone New value for emergencyPhone
   */
  public void setEmergencyPhone(String emergencyPhone) {
    this.emergencyPhone=emergencyPhone;
  }

  /**
   * Gets the current value of shirtSize
   * @return Current value of shirtSize
   */
  @JsonIgnore
  public String getShirtSize() {
    return shirtSize;
  }

  /**
   * Sets the value of shirtSize
   * @param shirtSize New value for shirtSize
   */
  public void setShirtSize(String shirtSize) {
    this.shirtSize=shirtSize;
  }

  /**
   * Gets the current value of shoeSize
   * @return Current value of shoeSize
   */
  @JsonIgnore
  public String getShoeSize() {
    return shoeSize;
  }

  /**
   * Sets the value of shoeSize
   * @param shoeSize New value for shoeSize
   */
  public void setShoeSize(String shoeSize) {
    this.shoeSize=shoeSize;
  }

  /**
   * Sets the default values in superclass with these values.
   **/
  public void setDefaults() {
      this.setDefaultEmergencyContactFirstName(this.emergencyContactFirstName);
      this.setDefaultEmergencyContactLastName(this.emergencyContactLastName);
      this.setDefaultEmergencyContactRelation(this.emergencyContactRelation);
      this.setDefaultEmergencyPhone(this.emergencyPhone);
      this.setDefaultShirtSize(this.shirtSize);
      this.setDefaultShoeSize(this.shoeSize);
  }


  public String toString() {

      String r = new String();
      r += "Competitor [";
      r += "id=" + getId() + ",";
      r += "personId=" + personId + ",";
      r += "emergencyContactFirstName=" + emergencyContactFirstName + ",";
      r += "emergencyContactLastName=" + emergencyContactLastName + ",";
      r += "emergencyContactRelation=" + emergencyContactRelation + ",";
      r += "emergencyPhone=" + emergencyPhone + ",";
      r += "shirtSize=" + shirtSize + ",";
      r += "shoeSize=" + shoeSize + "," + "]";

      return r;
  }

}