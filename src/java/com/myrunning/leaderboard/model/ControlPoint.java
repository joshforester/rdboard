package com.myrunning.leaderboard.model;

/**
 *  File: ControlPoint.java
 *  Author:  Joshua Forester
 *  Date: 8/20/2009
 *  Description: Bean class for ControlPoint object.
 **/


import java.io.Serializable;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Hashtable;
import java.sql.Timestamp;
import org.springframework.util.StringUtils;
import com.bbn.openmap.LatLonPoint;
import com.bbn.openmap.proj.Ellipsoid;
import com.bbn.openmap.proj.coords.ZonedUTMPoint;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import com.myrunning.leaderboard.model.stats.StatisticalUnit;


@XStreamAlias("cp")
public class ControlPoint extends DataResource implements Serializable, Comparable<ControlPoint>, StatisticalUnit {

  public static final int ZONE_NUMBER_MIN = 1;
  public static final int ZONE_NUMBER_MAX = 60;
  public static final int ALTITUDE_MIN = -1369;
  public static final int ALTITUDE_MAX = 29035;
  public static final int EASTERNLY_MIN = 0;
  public static final int EASTERNLY_MAX = 999999;
  public static final int NORTHERNLY_MIN = 0;
  public static final int NORTHERNLY_MAX = 9999999;


  private long courseId;
  private int cpOrder;
  private String name;
  private String taName;
  private int northernly;
  private int easternly;
  private int zoneNumber;
  private String zoneChar;
  private String hint;

  @XStreamOmitField
  private int altitude;

  private Timestamp cutoff;
  private String isMandatory;

  @XStreamOmitField
  private String isInMandatoryGroup;

  @XStreamOmitField
  private int mandatoryGroupRequirement;

  @XStreamOmitField
  private int mandatoryGroupSize;

  private int weight;
  private String fromDiscipline;
  private String toDiscipline;
  private String heyWhatsThatId;

  @XStreamOmitField
  private String isDeleted;

  @XStreamOmitField
  private List<CpVisit> cpVisitList;

  @XStreamOmitField
  private Hashtable<Long, CpVisit> cpVisitTable;

  @XStreamOmitField
  private Course course;

  private static List<String> disciplines = 
      Arrays.asList("running", 
		    "trekking", 
		    "mountaineering",
		    "coasteering",
		    "biking",
		    "orienteering",
		    "paddling",
		    "swimming",
		    "ropes",
		    "riverboarding",
		    "inlineskating",
		    "scootering",
		    "specialchallenge");
  private static List<String> zoneChars = 
      Arrays.asList("C",
		    "D",
		    "E",
		    "F",
		    "G",
		    "H",
		    "J",
		    "K",
		    "L",
		    "M",
		    "N",
		    "P",
		    "Q",
		    "S",
		    "T",
		    "U",
		    "V",
		    "W",
		    "X");


  // statistics
  @XStreamOmitField
  private List<Team> teamList;

  @XStreamOmitField
  private int teamsSkipped;

  @XStreamOmitField
  private int teamsAcquired;


  /**
   * Creates a new instance of ControlPoint
   */
  public ControlPoint() {
      this.cpVisitList = new ArrayList<CpVisit>();
      this.initializeCpVisitTable();
      this.setIsMandatory(new String("no"));
      this.setIsInMandatoryGroup(new String("no"));
      this.setIsDeleted(new String("no"));
  }

    public ControlPoint(int initialCpVisitTableSize) {
      this.cpVisitList = new ArrayList<CpVisit>();
      this.initializeCpVisitTable(initialCpVisitTableSize);
      this.setIsMandatory(new String("no"));
      this.setIsInMandatoryGroup(new String("no"));
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
   * Gets the current value of cpOrder
   * @return Current value of cpOrder
   */
  public int getCpOrder() {
    return cpOrder;
  }

  /**
   * Sets the value of cpOrder
   * @param cpOrder New value for cpOrder
   */
  public void setCpOrder(int cpOrder) {
    this.cpOrder=cpOrder;
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
   * Gets the current value of taName
   * @return Current value of taName
   */
  public String getTaName() {
    return taName;
  }

  /**
   * Sets the value of taName
   * @param taName New value for taName
   */
  public void setTaName(String taName) {
    this.taName=taName;
  }

  /**
   * Gets the current value of northernly
   * @return Current value of northernly
   */
  public int getNorthernly() {
    return northernly;
  }

  /**
   * Sets the value of northernly
   * @param northernly New value for northernly
   */
  public void setNorthernly(int northernly) {
    this.northernly=northernly;
  }

  /**
   * Gets the current value of easternly
   * @return Current value of easternly
   */
  public int getEasternly() {
    return easternly;
  }

  /**
   * Sets the value of easternly
   * @param easternly New value for easternly
   */
  public void setEasternly(int easternly) {
    this.easternly=easternly;
  }

  /**
   * Gets the current value of zoneNumber
   * @return Current value of zoneNumber
   */
  public int getZoneNumber() {
    return zoneNumber;
  }

  /**
   * Sets the value of zoneNumber
   * @param zoneNumber New value for zoneNumber
   */
  public void setZoneNumber(int zoneNumber) {
    this.zoneNumber=zoneNumber;
  }

  /**
   * Gets the current value of zoneChar
   * @return Current value of zoneChar
   */
  public String getZoneChar() {
    return zoneChar;
  }

  /**
   * Sets the value of zoneChar
   * @param zoneChar New value for zoneChar
   */
  public void setZoneChar(String zoneChar) {
    this.zoneChar=zoneChar;
  }

  /**
   * Gets the current value of hint
   * @return Current value of hint
   */
  public String getHint() {
    return hint;
  }

  /**
   * Sets the value of hint
   * @param hint New value for hint
   */
  public void setHint(String hint) {
    this.hint=hint;
  }

  /**
   * Gets the current value of altitude
   * @return Current value of altitude
   */
  @JsonIgnore
  public int getAltitude() {
    return altitude;
  }

  /**
   * Sets the value of altitude
   * @param altitude New value for altitude
   */
  public void setAltitude(int altitude) {
    this.altitude=altitude;
  }

  /**
   * Gets the current value of cutoff
   * @return Current value of cutoff
   */
  public Timestamp getCutoff() {
    return cutoff;
  }

  /**
   * Sets the value of cutoff
   * @param cutoff New value for cutoff
   */
  public void setCutoff(Timestamp cutoff) {
    this.cutoff=cutoff;
  }

  /**
   * Gets the current value of isMandatory
   * @return Current value of isMandatory
   */
  public String getIsMandatory() {
    return isMandatory;
  }

  /**
   * Sets the value of isMandatory
   * @param isMandatory New value for isMandatory
   */
  public void setIsMandatory(String isMandatory) {
    if (StringUtils.hasText(isMandatory)) {
	this.isMandatory=isMandatory;
    }
  }

  /**
   * Gets the current value of isInMandatoryGroup
   * @return Current value of isInMandatoryGroup
   */
  @JsonIgnore
  public String getIsInMandatoryGroup() {
    return isInMandatoryGroup;
  }

  /**
   * Sets the value of isInMandatoryGroup
   * @param isInMandatoryGroup New value for isInMandatoryGroup
   */
  public void setIsInMandatoryGroup(String isInMandatoryGroup) {
    if (StringUtils.hasText(isInMandatoryGroup)) {
	this.isInMandatoryGroup=isInMandatoryGroup;
    }
  }

  /**
   * Gets the current value of mandatoryGroupRequirement
   * @return Current value of mandatoryGroupRequirement
   */
  @JsonIgnore
  public int getMandatoryGroupRequirement() {
    return mandatoryGroupRequirement;
  }

  /**
   * Sets the value of mandatoryGroupRequirement
   * @param mandatoryGroupRequirement New value for mandatoryGroupRequirement
   */
  public void setMandatoryGroupRequirement(int mandatoryGroupRequirement) {
    this.mandatoryGroupRequirement=mandatoryGroupRequirement;
  }

  /**
   * Gets the current value of mandatoryGroupSize
   * @return Current value of mandatoryGroupSize
   */
  @JsonIgnore
  public int getMandatoryGroupSize() {
    return mandatoryGroupSize;
  }

  /**
   * Sets the value of mandatoryGroupSize
   * @param mandatoryGroupSize New value for mandatoryGroupSize
   */
  public void setMandatoryGroupSize(int mandatoryGroupSize) {
    this.mandatoryGroupSize=mandatoryGroupSize;
  }

  /**
   * Gets the current value of weight
   * @return Current value of weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Sets the value of weight
   * @param weight New value for weight
   */
  public void setWeight(int weight) {
    this.weight=weight;
  }

  /**
   * Gets the current value of fromDiscipline
   * @return Current value of fromDiscipline
   */
  public String getFromDiscipline() {
    return fromDiscipline;
  }

  /**
   * Sets the value of fromDiscipline
   * @param fromDiscipline New value for fromDiscipline
   */
  public void setFromDiscipline(String fromDiscipline) {
    this.fromDiscipline=fromDiscipline;
  }

  /**
   * Gets the current value of toDiscipline
   * @return Current value of toDiscipline
   */
  public String getToDiscipline() {
    return toDiscipline;
  }

  /**
   * Sets the value of toDiscipline
   * @param toDiscipline New value for toDiscipline
   */
  public void setToDiscipline(String toDiscipline) {
    this.toDiscipline=toDiscipline;
  }

  /**
   * Gets the current value of heyWhatsThatId
   * @return Current value of heyWhatsThatId
   */
  public String getHeyWhatsThatId() {
    return heyWhatsThatId;
  }

  /**
   * Sets the value of heyWhatsThatId
   * @param heyWhatsThatId New value for heyWhatsThatId
   */
  public void setHeyWhatsThatId(String heyWhatsThatId) {
    this.heyWhatsThatId=heyWhatsThatId;
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
   *  Method for converting CP to GPS.
   *  @return the Latitude and Longitude of the CP, or null if not possible.
   */
  @JsonIgnore
  public LatLonPoint getLatLonPoint() {
      return ZonedUTMPoint.ZonedUTMtoLL(Ellipsoid.WGS_84, 
					getNorthernly(), 
					getEasternly(), 
					getZoneNumber(), 
					getZoneChar().charAt(0), 
					null);
  }



    public void setCourse(Course course) {
	this.course = course;
    }

    @JsonIgnore
    public Course getCourse() {
	return this.course;
    }

    private void initializeCpVisitTable() {
      cpVisitTable = new Hashtable<Long, CpVisit>();
    }

    public void initializeCpVisitTable(int initialCpVisitTableSize) {
      cpVisitTable = new Hashtable<Long, CpVisit>(initialCpVisitTableSize);
    }

    public void addCpVisit(CpVisit cpVisit) {
	if (cpVisitList == null) {
	    cpVisitList = new ArrayList<CpVisit>();
	}
	cpVisitList.add(cpVisit);
	if (cpVisitTable == null) {
	    initializeCpVisitTable();
	}
	cpVisitTable.put(cpVisit.getTeamId(), cpVisit);
    }
    
    @JsonIgnore
    public List<CpVisit> getCpVisitList() {
	return cpVisitList;
    }

    @JsonIgnore
    public Hashtable<Long, CpVisit> getCpVisitTable() {
	return cpVisitTable;
    }

  /**
   * Gets the current value of disciplines
   * @return Current value of disciplines
   */
  public static List<String> getDisciplines() {
    return disciplines;
  }

  /**
   * Gets the current value of zoneChars
   * @return Current value of zoneChars
   */
  public static List<String> getZoneChars() {
    return zoneChars;
  }

  /**
   * Sets the value of teamList.  Assumes teamList is NOT sorted.
   * @param teamList New value for teamList
   */
  public void setTeamList(List<Team> teamList) {
      this.teamList=teamList;
      Collections.sort(teamList);
  }
  
  /**
   * Sets the value of teamList.  Assumes teamList is already sorted.
   * @param teamList New value for teamList
   */
  public void setSortedTeamList(List<Team> teamList) {
      this.teamList=teamList;
  }
    
  /**
   * Gets the current value of teamList
   * @return Current value of teamList
   */
  @JsonIgnore
  public List<Team> getTeamList() {
      return teamList;
  }
  
  @JsonIgnore
  public int getTeamsSkipped() {
      return teamsSkipped;
  }

  @JsonIgnore
  public int getTeamsAcquired() {
      return teamsAcquired;
  }


  /**
   *  Determine if all the location information is supplied.
   */
  public boolean hasLocation() {
      return getLatLonPoint() != null;
  }


  /**
   *  Compare the two locations of the control points to see if they differ.
   */
  public boolean locationEquals(ControlPoint controlPoint) {
      return ((this.northernly == controlPoint.getNorthernly()) &&
	      (this.easternly == controlPoint.getEasternly()) &&
	      (this.zoneNumber == controlPoint.getZoneNumber()) &&
	      (this.zoneChar.equals(controlPoint.getZoneChar())));
  }


  public int compareTo(ControlPoint controlPoint) {

      //
      //  NOTE THIS WONT WORK IF CPS SHARE THE SAME ORDER
      //

      if (this.getCourseId() != controlPoint.getCourseId()) {
	  return 0;
      } else {
	  return this.getCpOrder() - controlPoint.getCpOrder();
      }
  }


  /**
   * Generates statistics for Team.
   **/
  public void generateStatistics() {
      
      if (teamList == null) {
	  throw new IllegalStateException("Calling generateStatistics() on ControlPoint object without first calling setTeamList not allowed");
      }

      teamsAcquired = 0;
      teamsSkipped = 0;

      //
      // SEE SIMILAR NOTE IN Team.generateStatistics()
      //

      ListIterator<Team> tli = teamList.listIterator();
      Team team = null;
      CpVisit cpVisit = null;
      while (tli.hasNext()) {
	  team = tli.next();
	  cpVisit = cpVisitTable.get(team.getId());

	  if (cpVisit != null) {

	      if (cpVisit.getIsSkipped().equals("no")) {
		  teamsAcquired++;
	      } else if (cpVisit.getIsSkipped().equals("yes")) {
		  teamsSkipped++;
	      }
	  }
      }
      
  }
  
  public String toString() {

      String r = new String();
      r += "ControlPoint [";
      r += "id=" + getId() + ",";
      r += "courseId=" + courseId + ",";
      r += "cpOrder=" + cpOrder + ",";
      r += "name=" + name + ",";
      r += "taName=" + taName + ",";
      r += "northernly=" + northernly + ",";
      r += "easternly=" + easternly + ",";
      r += "zoneNumber=" + zoneNumber + ",";
      r += "zoneChar=" + zoneChar + ",";
      r += "hint=" + hint + ",";
      r += "altitude=" + altitude + ",";
      r += "cutoff=" + cutoff + ",";
      r += "isMandatory=" + isMandatory + ",";
      r += "weight=" + weight + ",";
      r += "fromDiscipline=" + fromDiscipline + ",";
      r += "toDiscipline=" + toDiscipline + ",";
      r += "heyWhatsThatId=" + heyWhatsThatId + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }

}