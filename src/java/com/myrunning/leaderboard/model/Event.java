package com.myrunning.leaderboard.model;

/**
 *  File: Event.java
 *  Author:  Joshua Forester
 *  Date: 8/17/2009
 *  Description: Bean class for Event object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;
import org.springframework.util.StringUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;


@XStreamAlias("event")
public class Event extends DataResource implements Serializable {

  private String name;
  private Timestamp startTime;
  private Timestamp endTime;
  private String city;
  private String region;
  private String country;
  private int zip;
  private String eventUrl;
  private String eventCaption;
  private String sponsorWebsite;
  private String sponsorUrl;
  private String sponsorTitle;
  private String sponsorCaption;
  private String sponsorDescription;

  @XStreamOmitField
  private String isDeleted;


  /**
   * Creates a new instance of Event
   */
  public Event() {
      setIsDeleted(new String("no"));
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
   * Gets the current value of startTime
   * @return Current value of startTime
   */
  public Timestamp getStartTime() {
    return startTime;
  }

  /**
   * Sets the value of startTime
   * @param startTime New value for startTime
   */
  public void setStartTime(Timestamp startTime) {
    this.startTime=startTime;
  }

  /**
   * Gets the current value of endTime
   * @return Current value of endTime
   */
  public Timestamp getEndTime() {
    return endTime;
  }

  /**
   * Sets the value of endTime
   * @param endTime New value for endTime
   */
  public void setEndTime(Timestamp endTime) {
    this.endTime=endTime;
  }

  /**
   * Gets the current value of city
   * @return Current value of city
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the value of city
   * @param city New value for city
   */
  public void setCity(String city) {
      if (StringUtils.hasText(city)) {
	  this.city=city;
      }
  }

  /**
   * Gets the current value of region
   * @return Current value of region
   */
  public String getRegion() {
    return region;
  }

  /**
   * Sets the value of region
   * @param region New value for region
   */
  public void setRegion(String region) {
      if (StringUtils.hasText(region)) {
	  this.region=region;
      }
  }

  /**
   * Gets the current value of country
   * @return Current value of country
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the value of country
   * @param country New value for country
   */
  public void setCountry(String country) {
      if (StringUtils.hasText(country)) {
	  this.country=country;
      }
  }

  /**
   * Gets the current value of zip
   * @return Current value of zip
   */
  public int getZip() {
    return zip;
  }

  /**
   * Sets the value of zip
   * @param zip New value for zip
   */
  public void setZip(int zip) {
    this.zip=zip;
  }


  /**
   * Gets the current value of eventUrl
   * @return Current value of eventUrl
   */
  public String getEventUrl() {
    return eventUrl;
  }

  /**
   * Sets the value of eventUrl
   * @param eventUrl New value for eventUrl
   */
  public void setEventUrl(String eventUrl) {
      if (StringUtils.hasText(eventUrl)) {
	  this.eventUrl=eventUrl;
      }
  }

  /**
   * Gets the current value of eventCaption
   * @return Current value of eventCaption
   */
  public String getEventCaption() {
    return eventCaption;
  }

  /**
   * Sets the value of eventCaption
   * @param eventCaption New value for eventCaption
   */
  public void setEventCaption(String eventCaption) {
      if (StringUtils.hasText(eventCaption)) {
	  this.eventCaption=eventCaption;
      }
  }

  /**
   * Gets the current value of sponsorWebsite
   * @return Current value of sponsorWebsite
   */
  public String getSponsorWebsite() {
    return sponsorWebsite;
  }

  /**
   * Sets the value of sponsorWebsite
   * @param sponsorWebsite New value for sponsorWebsite
   */
  public void setSponsorWebsite(String sponsorWebsite) {
      if (StringUtils.hasText(sponsorWebsite)) {
	  this.sponsorWebsite=sponsorWebsite;
      }
  }

  /**
   * Gets the current value of sponsorUrl
   * @return Current value of sponsorUrl
   */
  public String getSponsorUrl() {
    return sponsorUrl;
  }

  /**
   * Sets the value of sponsorUrl
   * @param sponsorUrl New value for sponsorUrl
   */
  public void setSponsorUrl(String sponsorUrl) {
      if (StringUtils.hasText(sponsorUrl)) {
	  this.sponsorUrl=sponsorUrl;
      }
  }

  /**
   * Gets the current value of sponsorTitle
   * @return Current value of sponsorTitle
   */
  public String getSponsorTitle() {
    return sponsorTitle;
  }

  /**
   * Sets the value of sponsorTitle
   * @param sponsorTitle New value for sponsorTitle
   */
  public void setSponsorTitle(String sponsorTitle) {
      if (StringUtils.hasText(sponsorTitle)) {
	  this.sponsorTitle=sponsorTitle;
      }
  }

  /**
   * Gets the current value of sponsorCaption
   * @return Current value of sponsorCaption
   */
  public String getSponsorCaption() {
    return sponsorCaption;
  }

  /**
   * Sets the value of sponsorCaption
   * @param sponsorCaption New value for sponsorCaption
   */
  public void setSponsorCaption(String sponsorCaption) {
      if (StringUtils.hasText(sponsorCaption)) {
	  this.sponsorCaption=sponsorCaption;
      }
  }

  /**
   * Gets the current value of sponsorDescription
   * @return Current value of sponsorDescription
   */
  public String getSponsorDescription() {
    return sponsorDescription;
  }

  /**
   * Sets the value of sponsorDescription
   * @param sponsorDescription New value for sponsorDescription
   */
  public void setSponsorDescription(String sponsorDescription) {
    this.sponsorDescription=sponsorDescription;
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

  public String toString() {

      String r = new String();
      r += "Event [";
      r += "id=" + getId() + ",";
      r += "name=" + name + ",";
      r += "startTime=" + startTime + ",";
      r += "endTime=" + endTime + ",";
      r += "city=" + city + ",";
      r += "region=" + region + ",";
      r += "country=" + country + ",";
      r += "zip=" + zip + ",";
      r += "eventUrl=" + eventUrl + ",";
      r += "eventCaption=" + eventCaption + ",";
      r += "sponsorWebsite=" + sponsorWebsite + ",";
      r += "sponsorUrl=" + sponsorUrl + ",";
      r += "sponsorTitle=" + sponsorTitle + ",";
      r += "sponsorCaption=" + sponsorCaption + ",";
      //r += "sponsorDescription=" + sponsorDescription + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }

}