package com.myrunning.leaderboard.model;

/**
 *  File: CpVisit.java
 *  Author:  Joshua Forester
 *  Date: 8/25/2009
 *  Description: Bean class for CpVisit object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;
import org.springframework.util.StringUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;


@XStreamAlias("cpvisit")
public class CpVisit {


  public static final int CP_BONUS_ASSESSED_MAX = 99;
  public static final int CP_BONUS_ASSESSED_MIN = 0;
  public static final int CP_PENALTY_ASSESSED_MAX = 99;
  public static final int CP_PENALTY_ASSESSED_MIN = 0;
  public static final int WEIGHT_BONUS_ASSESSED_MAX = 999;
  public static final int WEIGHT_BONUS_ASSESSED_MIN = 0;
  public static final int WEIGHT_PENALTY_ASSESSED_MAX = 999;
  public static final int WEIGHT_PENALTY_ASSESSED_MIN = 0;
    

  private long teamId;
  private long cpId;
  private Timestamp lastModified;
  private Timestamp arrival;
  private Timestamp departure;
  private Timestamp clientTime;
  private Timestamp timeBonusAssessed;
  private Timestamp timePenaltyAssessed;
  private int cpBonusAssessed;
  private int cpPenaltyAssessed;
  private int weightBonusAssessed;
  private int weightPenaltyAssessed;
  private String bonusAssessedReason;
  private String penaltyAssessedReason;
  private String isAcquired;
  private String isSkipped;
  private String isMissedCutoff;
  private String isUnofficial;
  private String isIncomplete;
  private String isWithdrawn;
  private String isDisqualified;

  // team statistics
  @XStreamOmitField
  private int divisionPlace;

  @XStreamOmitField
  private int coursePlace;


  @XStreamOmitField
  private String isDeleted;

  /**
   * Creates a new instance of CpVisit
   */
  public CpVisit() {
      this.setBooleans("no");
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
   * Gets the current value of cpId
   * @return Current value of cpId
   */
  public long getCpId() {
    return cpId;
  }

  /**
   * Sets the value of cpId
   * @param cpId New value for cpId
   */
  public void setCpId(long cpId) {
    this.cpId=cpId;
  }

  /**
   * Gets the current value of divisionPlace
   * @return Current value of divisionPlace
   */
  @JsonIgnore
  public int getDivisionPlace() {
    return divisionPlace;
  }

  /**
   * Sets the value of divisionPlace
   * @param divisionPlace New value for divisionPlace
   */
  public void setDivisionPlace(int divisionPlace) {
    this.divisionPlace=divisionPlace;
  }

  /**
   * Gets the current value of coursePlace
   * @return Current value of coursePlace
   */
  @JsonIgnore
  public int getCoursePlace() {
    return coursePlace;
  }

  /**
   * Sets the value of coursePlace
   * @param coursePlace New value for coursePlace
   */
  public void setCoursePlace(int coursePlace) {
    this.coursePlace=coursePlace;
  }

  /**
   * Gets the current value of lastModified
   * @return Current value of lastModified
   */
  public Timestamp getLastModified() {
    return lastModified;
  }

  /**
   * Sets the value of lastModified
   * @param lastModified New value for lastModified
   */
  public void setLastModified(Timestamp lastModified) {
    this.lastModified=lastModified;
  }

  /**
   * Gets the current value of arrival
   * @return Current value of arrival
   */
  public Timestamp getArrival() {
    return arrival;
  }

  /**
   * Sets the value of arrival
   * @param arrival New value for arrival
   */
  public void setArrival(Timestamp arrival) {
    this.arrival=arrival;
  }

  /**
   * Gets the current value of departure
   * @return Current value of departure
   */
  public Timestamp getDeparture() {
    return departure;
  }

  /**
   * Sets the value of departure
   * @param departure New value for departure
   */
  public void setDeparture(Timestamp departure) {
    this.departure=departure;
  }

  /**
   * Gets the current value of clientTime
   * @return Current value of clientTime
   */
  public Timestamp getClientTime() {
    return clientTime;
  }

  /**
   * Sets the value of clientTime
   * @param clientTime New value for clientTime
   */
  public void setClientTime(Timestamp clientTime) {
    this.clientTime=clientTime;
  }

  /**
   * Gets the current value of timeBonusAssessed
   * @return Current value of timeBonusAssessed
   */
  public Timestamp getTimeBonusAssessed() {
    return timeBonusAssessed;
  }

  /**
   * Sets the value of timeBonusAssessed
   * @param timeBonusAssessed New value for timeBonusAssessed
   */
  public void setTimeBonusAssessed(Timestamp timeBonusAssessed) {
    this.timeBonusAssessed=timeBonusAssessed;
  }

  /**
   * Gets the current value of timePenaltyAssessed
   * @return Current value of timePenaltyAssessed
   */
  public Timestamp getTimePenaltyAssessed() {
    return timePenaltyAssessed;
  }

  /**
   * Sets the value of timePenaltyAssessed
   * @param timePenaltyAssessed New value for timePenaltyAssessed
   */
  public void setTimePenaltyAssessed(Timestamp timePenaltyAssessed) {
    this.timePenaltyAssessed=timePenaltyAssessed;
  }

  /**
   * Gets the current value of cpBonusAssessed
   * @return Current value of cpBonusAssessed
   */
  public int getCpBonusAssessed() {
    return cpBonusAssessed;
  }

  /**
   * Sets the value of cpBonusAssessed
   * @param cpBonusAssessed New value for cpBonusAssessed
   */
  public void setCpBonusAssessed(int cpBonusAssessed) {
    this.cpBonusAssessed=cpBonusAssessed;
  }

  /**
   * Gets the current value of cpPenaltyAssessed
   * @return Current value of cpPenaltyAssessed
   */
  public int getCpPenaltyAssessed() {
    return cpPenaltyAssessed;
  }

  /**
   * Sets the value of cpPenaltyAssessed
   * @param cpPenaltyAssessed New value for cpPenaltyAssessed
   */
  public void setCpPenaltyAssessed(int cpPenaltyAssessed) {
    this.cpPenaltyAssessed=cpPenaltyAssessed;
  }

  /**
   * Gets the current value of weightBonusAssessed
   * @return Current value of weightBonusAssessed
   */
  public int getWeightBonusAssessed() {
    return weightBonusAssessed;
  }

  /**
   * Sets the value of weightBonusAssessed
   * @param weightBonusAssessed New value for weightBonusAssessed
   */
  public void setWeightBonusAssessed(int weightBonusAssessed) {
    this.weightBonusAssessed=weightBonusAssessed;
  }

  /**
   * Gets the current value of weightPenaltyAssessed
   * @return Current value of weightPenaltyAssessed
   */
  public int getWeightPenaltyAssessed() {
    return weightPenaltyAssessed;
  }

  /**
   * Sets the value of weightPenaltyAssessed
   * @param weightPenaltyAssessed New value for weightPenaltyAssessed
   */
  public void setWeightPenaltyAssessed(int weightPenaltyAssessed) {
    this.weightPenaltyAssessed=weightPenaltyAssessed;
  }

  /**
   * Gets the current value of bonusAssessedReason
   * @return Current value of bonusAssessedReason
   */
  public String getBonusAssessedReason() {
    return bonusAssessedReason;
  }

  /**
   * Sets the value of bonusAssessedReason
   * @param bonusAssessedReason New value for bonusAssessedReason
   */
  public void setBonusAssessedReason(String bonusAssessedReason) {
    if (StringUtils.hasText(bonusAssessedReason)) {
	this.bonusAssessedReason=bonusAssessedReason;
    }
  }

  /**
   * Gets the current value of penaltyAssessedReason
   * @return Current value of penaltyAssessedReason
   */
  public String getPenaltyAssessedReason() {
    return penaltyAssessedReason;
  }

  /**
   * Sets the value of penaltyAssessedReason
   * @param penaltyAssessedReason New value for penaltyAssessedReason
   */
  public void setPenaltyAssessedReason(String penaltyAssessedReason) {
    if (StringUtils.hasText(penaltyAssessedReason)) {
	this.penaltyAssessedReason=penaltyAssessedReason;
    }
  }

  /**
   * Gets the current value of isAcquired
   * @return Current value of isAcquired
   */
  public String getIsAcquired() {
    return isAcquired;
  }

  /**
   * Sets the value of isAcquired
   * @param isAcquired New value for isAcquired
   */
  public void setIsAcquired(String isAcquired) {
    if (StringUtils.hasText(isAcquired)) {
	this.isAcquired=isAcquired;
    }
  }

  /**
   * Gets the current value of isSkipped
   * @return Current value of isSkipped
   */
  public String getIsSkipped() {
    return isSkipped;
  }

  /**
   * Sets the value of isSkipped
   * @param isSkipped New value for isSkipped
   */
  public void setIsSkipped(String isSkipped) {
    if (StringUtils.hasText(isSkipped)) {
	this.isSkipped=isSkipped;
    }
  }

  /**
   * Gets the current value of isMissedCutoff
   * @return Current value of isMissedCutoff
   */
  public String getIsMissedCutoff() {
    return isMissedCutoff;
  }

  /**
   * Sets the value of isMissedCutoff
   * @param isMissedCutoff New value for isMissedCutoff
   */
  public void setIsMissedCutoff(String isMissedCutoff) {
    if (StringUtils.hasText(isMissedCutoff)) {
	this.isMissedCutoff=isMissedCutoff;
    }
  }

  /**
   * Gets the current value of isUnofficial
   * @return Current value of isUnofficial
   */
  public String getIsUnofficial() {
    return isUnofficial;
  }

  /**
   * Sets the value of isUnofficial
   * @param isUnofficial New value for isUnofficial
   */
  public void setIsUnofficial(String isUnofficial) {
    if (StringUtils.hasText(isUnofficial)) {
	this.isUnofficial=isUnofficial;
    }
  }

  /**
   * Gets the current value of isIncomplete
   * @return Current value of isIncomplete
   */
  public String getIsIncomplete() {
    return isIncomplete;
  }

  /**
   * Sets the value of isIncomplete
   * @param isIncomplete New value for isIncomplete
   */
  public void setIsIncomplete(String isIncomplete) {
    if (StringUtils.hasText(isIncomplete)) {
	this.isIncomplete=isIncomplete;
    }
  }

  /**
   * Gets the current value of isWithdrawn
   * @return Current value of isWithdrawn
   */
  public String getIsWithdrawn() {
    return isWithdrawn;
  }

  /**
   * Sets the value of isWithdrawn
   * @param isWithdrawn New value for isWithdrawn
   */
  public void setIsWithdrawn(String isWithdrawn) {
    if (StringUtils.hasText(isWithdrawn)) {
	this.isWithdrawn=isWithdrawn;
    }
  }

  /**
   * Gets the current value of isDisqualified
   * @return Current value of isDisqualified
   */
  public String getIsDisqualified() {
    return isDisqualified;
  }

  /**
   * Sets the value of isDisqualified
   * @param isDisqualified New value for isDisqualified
   */
  public void setIsDisqualified(String isDisqualified) {
    if (StringUtils.hasText(isDisqualified)) {
	this.isDisqualified=isDisqualified;
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
     * Gets the transition time for this CP visit
     * @return transition time for this CP visit, or 0 if the arrival or departure
     *         are not set
     */
  @JsonIgnore
    public Timestamp getTransition() {
	if ((arrival == null) ||
	    (departure == null) ||
	    (arrival.getTime() == 0) ||
	    (departure.getTime() == 0)) {
	    return new Timestamp(0);
	} else {
	    return new Timestamp(departure.getTime() - arrival.getTime());
	}
    }


  /**
   *  Convenience setter method to default the non-unique values of the CpVisit.
   */
  public void setDefault() {
      this.setArrival(new Timestamp(0));
      this.setDeparture(new Timestamp(0));
      this.setTimeBonusAssessed(new Timestamp(0));
      this.setTimePenaltyAssessed(new Timestamp(0));
      this.setBooleans("no");
  }


  /**
   *  Method to verify if this is simply a defaulted object of if any of 
   *  the values have changed beyond their default state.
   */
  @JsonIgnore
  public boolean isDefaulted() {
	
      boolean r = true;

      if ((this.getArrival() != null) && 
	  (this.getArrival().getTime() != 0)) {
	  r = false;
      }

      if ((this.getDeparture() != null) &&
	  (this.getDeparture().getTime() != 0)) {
	  r = false;
      }

      if ((this.getTimeBonusAssessed() != null) && 
	  (this.getTimeBonusAssessed().getTime() != 0)) {
	  r = false;
      }

      if ((this.getTimePenaltyAssessed() != null) &&
	  (this.getTimePenaltyAssessed().getTime() != 0)) {
	  r = false;
      }

      if ((this.getDivisionPlace() != 0) ||
	  (this.getCoursePlace() != 0) ||
	  (this.getCpBonusAssessed() != 0) ||
	  (this.getCpPenaltyAssessed() != 0) ||
	  (this.getWeightBonusAssessed() != 0) ||
	  (this.getWeightPenaltyAssessed() != 0) ||
	  ((this.getBonusAssessedReason() != null) && ((!this.getBonusAssessedReason().equals("default")))) ||
	  ((this.getPenaltyAssessedReason() != null) && ((!this.getPenaltyAssessedReason().equals("default")))) ||
	  (!this.getIsAcquired().equals("no")) ||
	  (!this.getIsSkipped().equals("no")) ||
	  (!this.getIsMissedCutoff().equals("no")) ||
	  (!this.getIsUnofficial().equals("no")) ||
	  (!this.getIsIncomplete().equals("no")) ||
	  (!this.getIsWithdrawn().equals("no")) ||
	  (!this.getIsDisqualified().equals("no")) ||
	  (!this.getIsDeleted().equals("no"))) {
	  r = false;
      }
	  
      return r;
  }


    private void setBooleans(String s) {
	this.setIsAcquired(s);  
	this.setIsSkipped(s);
	this.setIsMissedCutoff(s);
	this.setIsUnofficial(s);
	this.setIsIncomplete(s);
	this.setIsWithdrawn(s);
	this.setIsDisqualified(s);
	this.setIsDeleted(s);   
    }

    /**
     *  Shows version of this CPVisit that would return from the database after
     *  inserted.
     * 
     *  Note that this will NOT generate divisionPlace/coursePlace, as these are generated
     *  by aggregate comparison.
     **/
    @JsonIgnore
    public CpVisit getDatabaseVariant() {
	CpVisit cpVisit = new CpVisit();
	cpVisit.setTeamId(getTeamId());
	cpVisit.setCpId(getCpId());
	if (getArrival() == null) {
	    cpVisit.setArrival(new Timestamp(0));
	} else {
	    cpVisit.setArrival(getArrival());
	}
	if (getDeparture() == null) {
	    cpVisit.setDeparture(new Timestamp(0));
	} else {
	    cpVisit.setDeparture(getDeparture());
	}
	if (getTimeBonusAssessed() == null) {
	    cpVisit.setTimeBonusAssessed(new Timestamp(0));
	} else {
	    cpVisit.setTimeBonusAssessed(getTimeBonusAssessed());
	}
	if (getTimePenaltyAssessed() == null) {
	    cpVisit.setTimePenaltyAssessed(new Timestamp(0));
	} else {
	    cpVisit.setTimePenaltyAssessed(getTimePenaltyAssessed());
	}
	cpVisit.setCpBonusAssessed(getCpBonusAssessed());
	cpVisit.setCpPenaltyAssessed(getCpPenaltyAssessed());
	cpVisit.setWeightBonusAssessed(getWeightBonusAssessed());
	cpVisit.setWeightPenaltyAssessed(getWeightPenaltyAssessed());
	if (getBonusAssessedReason() == null) {
	    cpVisit.setBonusAssessedReason("default");
	} else {
	    cpVisit.setBonusAssessedReason(getBonusAssessedReason());
	}
	if (getPenaltyAssessedReason() == null) {
	    cpVisit.setPenaltyAssessedReason("default");
	} else {
	    cpVisit.setPenaltyAssessedReason(getPenaltyAssessedReason());
	}
	if (getIsAcquired() == null) {
	    cpVisit.setIsAcquired("no");
	} else {
	    cpVisit.setIsAcquired(getIsAcquired());
	}
	if (getIsSkipped() == null) {
	    cpVisit.setIsSkipped("no");
	} else {
	    cpVisit.setIsSkipped(getIsSkipped());
	}
	if (getIsMissedCutoff() == null) {
	    cpVisit.setIsMissedCutoff("no");
	} else {
	    cpVisit.setIsMissedCutoff(getIsMissedCutoff());
	}
	if (getIsUnofficial() == null) {
	    cpVisit.setIsUnofficial("no");
	} else {
	    cpVisit.setIsUnofficial(getIsUnofficial());
	}
	if (getIsIncomplete() == null) {
	    cpVisit.setIsIncomplete("no");
	} else {
	    cpVisit.setIsIncomplete(getIsIncomplete());
	}
	if (getIsWithdrawn() == null) {
	    cpVisit.setIsWithdrawn("no");
	} else {
	    cpVisit.setIsWithdrawn(getIsWithdrawn());
	}
	if (getIsDisqualified() == null) {
	    cpVisit.setIsDisqualified("no");
	} else {
	    cpVisit.setIsDisqualified(getIsDisqualified());
	}
	if (getIsDeleted() == null) {
	    cpVisit.setIsDeleted("no");
	} else {
	    cpVisit.setIsDeleted(getIsDeleted());
	}

	return cpVisit;
    }

    
    /**
     *  Note that this will NOT divisionPlace/coursePlace, as these are generated
     *  by aggregate comparison.
     **/
    public boolean sameValues(CpVisit cpVisit) {
	
        if ((teamId != cpVisit.getTeamId()) ||
	    (cpId != cpVisit.getCpId()) ||
	    (arrival.compareTo(cpVisit.getArrival()) != 0) ||
	    (departure.compareTo(cpVisit.getDeparture()) != 0) ||
	    (timeBonusAssessed.compareTo(cpVisit.getTimeBonusAssessed()) != 0) ||
	    (timePenaltyAssessed.compareTo(cpVisit.getTimePenaltyAssessed()) != 0) ||
	    (cpBonusAssessed != cpVisit.getCpBonusAssessed()) ||
	    (cpPenaltyAssessed != cpVisit.getCpPenaltyAssessed()) ||
	    (weightBonusAssessed != cpVisit.getWeightBonusAssessed()) ||
	    (weightPenaltyAssessed != cpVisit.getWeightPenaltyAssessed()) ||
	    (bonusAssessedReason.compareTo(cpVisit.getBonusAssessedReason()) != 0) ||
	    (penaltyAssessedReason.compareTo(cpVisit.getPenaltyAssessedReason()) != 0) ||
	    (isAcquired.compareTo(cpVisit.getIsAcquired()) != 0) ||
	    (isSkipped.compareTo(cpVisit.getIsSkipped()) != 0) ||
	    (isMissedCutoff.compareTo(cpVisit.getIsMissedCutoff()) != 0) ||
	    (isUnofficial.compareTo(cpVisit.getIsUnofficial()) != 0) ||
	    (isIncomplete.compareTo(cpVisit.getIsIncomplete()) != 0) ||
	    (isWithdrawn.compareTo(cpVisit.getIsWithdrawn()) != 0) ||
	    (isDisqualified.compareTo(cpVisit.getIsDisqualified()) != 0) ||
	    (isDeleted.compareTo(cpVisit.getIsDeleted()) != 0)) {
	    
	    return false;
	    
	}
	
	return true;
    }



  public String toString() {

      String r = new String();
      r += "CpVisit [";
      r += "teamId=" + teamId + ",";
      r += "cpId=" + cpId + ",";
      r += "divisionPlace=" + divisionPlace + ",";
      r += "coursePlace=" + coursePlace + ",";
      r += "lastModified=" + lastModified + ",";
      r += "arrival=" + arrival + ",";
      r += "departure=" + departure + ",";
      r += "timeBonusAssessed=" + timeBonusAssessed + ",";
      r += "timePenaltyAssessed=" + timePenaltyAssessed + ",";
      r += "cpBonusAssessed=" + cpBonusAssessed + ",";
      r += "cpPenaltyAssessed=" + cpPenaltyAssessed + ",";
      r += "weightBonusAssessed=" + weightBonusAssessed + ",";
      r += "weightPenaltyAssessed=" + weightPenaltyAssessed + ",";
      r += "bonusAssessedReason=" + bonusAssessedReason + ",";
      r += "penaltyAssessedReason=" + penaltyAssessedReason + ",";
      r += "isAcquired=" + isAcquired + ",";
      r += "isSkipped=" + isSkipped + ",";
      r += "isMissedCutoff=" + isMissedCutoff + ",";
      r += "isUnofficial=" + isUnofficial + ",";
      r += "isIncomplete=" + isIncomplete + ",";
      r += "isWithdrawn=" + isWithdrawn + ",";
      r += "isDisqualified=" + isDisqualified + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }

}