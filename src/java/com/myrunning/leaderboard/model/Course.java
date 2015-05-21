package com.myrunning.leaderboard.model;

/**
 *  File: Course.java
 *  Author:  Joshua Forester
 *  Date: 8/18/2009
 *  Description: Bean class for Course object.
 **/


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.StringUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;


@XStreamAlias("course")
public class Course extends DataResource implements Serializable {

  private long eventId;
  private String name;
  private String type;
  private int lengthHours;
  private int lengthMiles;
  private String description;
  private String scribbleliveId;
  private String coveritliveId;
  private String feedburnerAllId;
  private String feedburnerBlogsId;
  private String feedburnerPhotosId;
  private String feedburnerVideosId;
  private String feedburnerAudioId;
  private String competitorGmapSuffix;

    
  @XStreamOmitField  
  private String isDeleted;

  private java.util.Comparator comparator;

  private static List<String> types = 
      Arrays.asList("rogaine",
		    "weightedrogaine",
		    "modifiedrogaine",
		    "pointtopoint",
		    "stage");


  /**
   * Creates a new instance of Course
   */
  public Course() {
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
   * Gets the current value of lengthHours
   * @return Current value of lengthHours
   */
  public int getLengthHours() {
    return lengthHours;
  }

  /**
   * Sets the value of lengthHours
   * @param lengthHours New value for lengthHours
   */
  public void setLengthHours(int lengthHours) {
    this.lengthHours=lengthHours;
  }

  /**
   * Gets the current value of lengthMiles
   * @return Current value of lengthMiles
   */
  public int getLengthMiles() {
    return lengthMiles;
  }

  /**
   * Sets the value of lengthMiles
   * @param lengthMiles New value for lengthMiles
   */
  public void setLengthMiles(int lengthMiles) {
    this.lengthMiles=lengthMiles;
  }

  /**
   * Gets the current value of description
   * @return Current value of description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the value of description
   * @param description New value for description
   */
  public void setDescription(String description) {
    this.description=description;
  }

  /**
   * Gets the current value of scribbleliveId
   * @return Current value of scribbleliveId
   */
  public String getScribbleliveId() {
    return scribbleliveId;
  }

  /**
   * Gets the current value of scribbleliveId in its URL format
   * i.e.  31578 will be returned as 3/1/5/7/8
   * @return Current value of scribbleliveId in its URL format
   */
  @JsonIgnore
  public String getFormattedScribbleliveId() {
      if ((scribbleliveId == null) || (scribbleliveId.equals("default"))) {
	  return null;
      }

      StringBuffer s = new StringBuffer(scribbleliveId);
      for (int i = 5; 0 < i; i--) {
	  s.insert(i, '/');
      }
      return s.toString();
  }

  /**
   * Sets the value of scribbleliveId
   * @param scribbleliveId New value for scribbleliveId
   */
  public void setScribbleliveId(String scribbleliveId) {
      if (StringUtils.hasText(scribbleliveId)) {
	  this.scribbleliveId=scribbleliveId;
      }
  }

  /**
   * Gets the current value of coveritliveId
   * @return Current value of coveritliveId
   */
  public String getCoveritliveId() {
    return coveritliveId;
  }

  /**
   * Sets the value of coveritliveId
   * @param coveritliveId New value for coveritliveId
   */
  public void setCoveritliveId(String coveritliveId) {
      if (StringUtils.hasText(coveritliveId)) {
	  this.coveritliveId=coveritliveId;
      }
  }

  /**
   * Gets the current value of coveritliveId in its URL format
   * @return Current value of coveritliveId in its URL format
   */
  @JsonIgnore
  public String getFormattedCoveritliveId() {
      if ((coveritliveId == null) || (coveritliveId.equals("default"))) {
	  return null;
      }

      return coveritliveId;
  }

  /**
   * Gets the current value of feedburnerAllId
   * @return Current value of feedburnerAllId
   */
  public String getFeedburnerAllId() {
    return feedburnerAllId;
  }

  /**
   * Sets the value of feedburnerAllId
   * @param feedburnerAllId New value for feedburnerAllId
   */
  public void setFeedburnerAllId(String feedburnerAllId) {
      if (StringUtils.hasText(feedburnerAllId)) {
	  this.feedburnerAllId=feedburnerAllId;
      }
  }

  /**
   * Gets the current value of feedburnerBlogsId
   * @return Current value of feedburnerBlogsId
   */
  public String getFeedburnerBlogsId() {
    return feedburnerBlogsId;
  }

  /**
   * Sets the value of feedburnerBlogsId
   * @param feedburnerBlogsId New value for feedburnerBlogsId
   */
  public void setFeedburnerBlogsId(String feedburnerBlogsId) {
      if (StringUtils.hasText(feedburnerBlogsId)) {
	  this.feedburnerBlogsId=feedburnerBlogsId;
      }
  }

  /**
   * Gets the current value of feedburnerPhotosId
   * @return Current value of feedburnerPhotosId
   */
  public String getFeedburnerPhotosId() {
    return feedburnerPhotosId;
  }

  /**
   * Sets the value of feedburnerPhotosId
   * @param feedburnerPhotosId New value for feedburnerPhotosId
   */
  public void setFeedburnerPhotosId(String feedburnerPhotosId) {
      if (StringUtils.hasText(feedburnerPhotosId)) {
	  this.feedburnerPhotosId=feedburnerPhotosId;
      }
  }

  /**
   * Gets the current value of feedburnerVideosId
   * @return Current value of feedburnerVideosId
   */
  public String getFeedburnerVideosId() {
    return feedburnerVideosId;
  }

  /**
   * Sets the value of feedburnerVideosId
   * @param feedburnerVideosId New value for feedburnerVideosId
   */
  public void setFeedburnerVideosId(String feedburnerVideosId) {
      if (StringUtils.hasText(feedburnerVideosId)) {
	  this.feedburnerVideosId=feedburnerVideosId;
      }
  }

  /**
   * Gets the current value of feedburnerAudioId
   * @return Current value of feedburnerAudioId
   */
  public String getFeedburnerAudioId() {
    return feedburnerAudioId;
  }

  /**
   * Sets the value of feedburnerAudioId
   * @param feedburnerAudioId New value for feedburnerAudioId
   */
  public void setFeedburnerAudioId(String feedburnerAudioId) {
      if (StringUtils.hasText(feedburnerAudioId)) {
	  this.feedburnerAudioId=feedburnerAudioId;
      }
  }

  /**
   * Gets the current value of competitorGmapSuffix
   * @return Current value of competitorGmapSuffix
   */
  public String getCompetitorGmapSuffix() {
    return competitorGmapSuffix;
  }

  /**
   * Sets the value of competitorGmapSuffix
   * @param competitorGmapSuffix New value for competitorGmapSuffix
   */
  public void setCompetitorGmapSuffix(String competitorGmapSuffix) {
      if (StringUtils.hasText(competitorGmapSuffix)) {
	  this.competitorGmapSuffix=competitorGmapSuffix;
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
   * Gets the current value of comparator
   * @return Current value of comparator
   */
  @JsonWriteNullProperties(false)
  public java.util.Comparator getComparator() {
    return comparator;
  }

  /**
   * Sets the value of comparator
   * @param comparator New value for comparator
   */
  public void setComparator(java.util.Comparator comparator) {
    this.comparator=comparator;
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
      r += "Course [";
      r += "id=" + getId() + ",";
      r += "eventId=" + eventId + ",";
      r += "name=" + name + ",";
      r += "type=" + type + ",";
      r += "lengthHours=" + lengthHours + ",";
      r += "lengthMiles=" + lengthMiles + ",";
      r += "description=" + description + ",";
      r += "scribbleliveId=" + scribbleliveId + ",";
      r += "coveritliveId=" + coveritliveId + ",";
      r += "feedburnerAllId=" + feedburnerAllId + ",";
      r += "feedburnerBlogsId=" + feedburnerBlogsId + ",";
      r += "feedburnerVideosId=" + feedburnerVideosId + ",";
      r += "feedburnerPhotosId=" + feedburnerPhotosId + ",";
      r += "feedburnerAudioId=" + feedburnerAudioId + ",";
      r += "competitorGmapSuffix=" + competitorGmapSuffix + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }

}