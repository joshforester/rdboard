package com.myrunning.leaderboard.model;

/**
 *  File: Team.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/05
 *  Description: Team object.
 **/



import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.sql.Timestamp;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.model.ControlPoint;
import com.myrunning.leaderboard.model.stats.StatisticalUnit;


@XStreamAlias("team")
public class Team extends DataResource implements Serializable, Comparable<Team>, StatisticalUnit {

    static Logger logger = Logger.getLogger(Team.class);

    private String name;
    private long courseId;
    private long divisionId;
    private int number;

    @XStreamOmitField
    private Timestamp registrationTime;

    @XStreamOmitField
    private String isDeleted;

    @XStreamOmitField
    private Division division;

    @XStreamOmitField
    private List<CpVisit> cpVisitList;

    @XStreamOmitField
    private Hashtable<Long, CpVisit> cpVisitTable;

    @XStreamOmitField
    private List<Rank> rankList;

    // event statistics
    @XStreamOmitField
    private int divisionPlace;

    @XStreamOmitField
    private int coursePlace;

    // statistics
    @XStreamOmitField
    private List<ControlPoint> controlPointList;

    @XStreamOmitField
    private boolean missedCutoff;

    @XStreamOmitField
    private ControlPoint missedCutoffControlPoint;

    @XStreamOmitField
    private boolean unofficial;

    @XStreamOmitField
    private ControlPoint unofficialControlPoint;

    @XStreamOmitField
    private boolean incomplete;

    @XStreamOmitField
    private ControlPoint incompleteControlPoint;

    @XStreamOmitField
    private boolean withdrawn;

    @XStreamOmitField
    private ControlPoint withdrawnControlPoint;

    @XStreamOmitField
    private boolean disqualified;

    @XStreamOmitField
    private ControlPoint disqualifiedControlPoint;

    @XStreamOmitField
    private boolean didNotStart;

    @XStreamOmitField
    private ControlPoint lastVisitedControlPoint;

    @XStreamOmitField
    private Timestamp lastVisitedCpArrival;

    @XStreamOmitField
    private Timestamp lastVisitedCpDeparture;

    @XStreamOmitField
    private Timestamp timeBonuses;

    @XStreamOmitField
    private Timestamp timePenalties;

    @XStreamOmitField
    private int cpBonuses;

    @XStreamOmitField
    private int cpPenalties;

    @XStreamOmitField
    private int cpWeightBonuses;

    @XStreamOmitField
    private int cpWeightPenalties;

    @XStreamOmitField
    private int mandatoryCps;

    @XStreamOmitField
    private int optionalCps;

    @XStreamOmitField
    private int cpWeight;

    @XStreamOmitField
    private int skippedMandatoryCps;

    @XStreamOmitField
    private int skippedOptionalCps;

    @XStreamOmitField
    private int skippedCpWeight;



    public Team() {
	this.cpVisitList = new ArrayList<CpVisit>();
	this.rankList = new ArrayList<Rank>();
	this.initializeCpVisitTable();
	this.isDeleted = new String("no");
    }

    public Team(int initialCpVisitTableSize) {
	this.cpVisitList = new ArrayList<CpVisit>();
	this.rankList = new ArrayList<Rank>();
	this.initializeCpVisitTable(initialCpVisitTableSize);
	this.isDeleted = new String("no");
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return this.name;
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
     * Gets the current value of divisionId
     * @return Current value of divisionId
     */
    public long getDivisionId() {
	return divisionId;
    }
    
    /**
     * Sets the value of divisionId
     * @param divisionId New value for divisionId
     */
    public void setDivisionId(long divisionId) {
	this.divisionId=divisionId;
    }
    
    /**
     * Gets the current value of number
     * @return Current value of number
     */
    public int getNumber() {
	return this.number;
    }
    
    /**
     * Sets the value of number
     * @param number New value for number
     */
    public void setNumber(int number) {
	this.number = number;
    }
    
    /**
     * Sets the value of registrationTime
     * @param registrationTime New value for registrationTime
     */
    public void setRegistrationTime(Timestamp registrationTime) {
	this.registrationTime=registrationTime;
    }
        
    /**
     * Gets the current value of registrationTime
     * @return Current value of registrationTime
     */
    @JsonIgnore
    public Timestamp getRegistrationTime() {
	return registrationTime;
    }
    
    public void setIsDeleted(String isDeleted) {
	this.isDeleted = isDeleted;
    }

    @JsonIgnore
    public String getIsDeleted() {
	return this.isDeleted;
    }

    public void setDivision(Division division) {
	this.division = division;
    }

    @JsonIgnore
    public Division getDivision() {
	return this.division;
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
	cpVisitTable.put(cpVisit.getCpId(), cpVisit);
    }
    
    @JsonIgnore
    public List<CpVisit> getCpVisitList() {
	return cpVisitList;
    }

    @JsonIgnore
    public Hashtable<Long, CpVisit> getCpVisitTable() {
	return cpVisitTable;
    }

    public void addRank(Rank rank) {
	if (rankList == null) {
	    rankList = new ArrayList<Rank>();
	}
	rankList.add(rank);
    }

    @JsonIgnore
    public List<Rank> getRankList() {
	return rankList;
    }

    /**
     * Sets the value of rankList.  Assumes rankList is NOT sorted.
     * @param rankList New value for rankList
     */
    public void setRankList(List<Rank> rankList) {
	this.rankList=rankList;
	Collections.sort(rankList);
    }


    /**
     * Sets the value of rankList.  Assumes rankList is already sorted.
     * @param rankList New value for rankList
     */
    public void setSortedRankList(List<Rank> rankList) {
	this.rankList=rankList;
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
     * Sets the value of controlPointList.  Assumes controlPointList is NOT sorted.
     * @param controlPointList New value for controlPointList
     */
    public void setControlPointList(List<ControlPoint> controlPointList) {
	this.controlPointList=controlPointList;
	Collections.sort(controlPointList);
    }


    /**
     * Sets the value of controlPointList.  Assumes controlPointList is already sorted.
     * @param controlPointList New value for controlPointList
     */
    public void setSortedControlPointList(List<ControlPoint> controlPointList) {
	this.controlPointList=controlPointList;
    }

   
    /**
     * Gets the current value of controlPointList
     * @return Current value of controlPointList
     */
    @JsonIgnore
    public List<ControlPoint> getControlPointList() {
	return controlPointList;
    }

    /**
     * Gets the current value of missedCutoff
     * @return Current value of missedCutoff
     */
    @JsonIgnore
    public boolean isMissedCutoff() {
	return missedCutoff;
    }

    /**
     * Sets the value of missedCutoff
     * @param missedCutoff New value for missedCutoff
     */
    public void setIsMissedCutoff(boolean missedCutoff) {
	this.missedCutoff=missedCutoff;
    }

    /**
     * Gets the current value of missedCutoffControlPoint
     * @return Current value of missedCutoffControlPoint
     */
    @JsonIgnore
    public ControlPoint getMissedCutoffControlPoint() {
	return missedCutoffControlPoint;
    }

    /**
     * Sets the value of missedCutoffControlPoint
     * @param missedCutoffControlPoint New value for missedCutoffControlPoint
     */
    public void setMissedCutoffControlPoint(ControlPoint missedCutoffControlPoint) {
	this.missedCutoffControlPoint=missedCutoffControlPoint;
    }

    /**
     * Gets the current value of unofficial
     * @return Current value of unofficial
     */
    @JsonIgnore
    public boolean isUnofficial() {
	return unofficial;
    }

    /**
     * Sets the value of unofficial
     * @param unofficial New value for unofficial
     */
    public void setIsUnofficial(boolean unofficial) {
	this.unofficial=unofficial;
    }

    /**
     * Gets the current value of unofficialControlPoint
     * @return Current value of unofficialControlPoint
     */
    @JsonIgnore
    public ControlPoint getUnofficialControlPoint() {
	return unofficialControlPoint;
    }

    /**
     * Sets the value of unofficialControlPoint
     * @param unofficialControlPoint New value for unofficialControlPoint
     */
    public void setUnofficialControlPoint(ControlPoint unofficialControlPoint) {
	this.unofficialControlPoint=unofficialControlPoint;
    }

    /**
     * Gets the current value of incomplete
     * @return Current value of incomplete
     */
    @JsonIgnore
    public boolean isIncomplete() {
	return incomplete;
    }

    /**
     * Sets the value of incomplete
     * @param incomplete New value for incomplete
     */
    public void setIsIncomplete(boolean incomplete) {
	this.incomplete=incomplete;
    }

    /**
     * Gets the current value of incompleteControlPoint
     * @return Current value of incompleteControlPoint
     */
    @JsonIgnore
    public ControlPoint getIncompleteControlPoint() {
	return incompleteControlPoint;
    }

    /**
     * Sets the value of incompleteControlPoint
     * @param incompleteControlPoint New value for incompleteControlPoint
     */
    public void setIncompleteControlPoint(ControlPoint incompleteControlPoint) {
	this.incompleteControlPoint=incompleteControlPoint;
    }

    /**
     * Gets the current value of withdrawn
     * @return Current value of withdrawn
     */
    @JsonIgnore
    public boolean isWithdrawn() {
	return withdrawn;
    }

    /**
     * Sets the value of withdrawn
     * @param withdrawn New value for withdrawn
     */
    public void setIsWithdrawn(boolean withdrawn) {
	this.withdrawn=withdrawn;
    }

    /**
     * Gets the current value of withdrawnControlPoint
     * @return Current value of withdrawnControlPoint
     */
    @JsonIgnore
    public ControlPoint getWithdrawnControlPoint() {
	return withdrawnControlPoint;
    }

    /**
     * Sets the value of withdrawnControlPoint
     * @param withdrawnControlPoint New value for withdrawnControlPoint
     */
    public void setWithdrawnControlPoint(ControlPoint withdrawnControlPoint) {
	this.withdrawnControlPoint=withdrawnControlPoint;
    }

    /**
     * Gets the current value of disqualified
     * @return Current value of disqualified
     */
    @JsonIgnore
    public boolean isDisqualified() {
	return disqualified;
    }

    /**
     * Sets the value of disqualified
     * @param disqualified New value for disqualified
     */
    public void setIsDisqualified(boolean disqualified) {
	this.disqualified=disqualified;
    }

    /**
     * Gets the current value of disqualifiedControlPoint
     * @return Current value of disqualifiedControlPoint
     */
    @JsonIgnore
    public ControlPoint getDisqualifiedControlPoint() {
	return disqualifiedControlPoint;
    }

    /**
     * Sets the value of disqualifiedControlPoint
     * @param disqualifiedControlPoint New value for disqualifiedControlPoint
     */
    public void setDisqualifiedControlPoint(ControlPoint disqualifiedControlPoint) {
	this.disqualifiedControlPoint=disqualifiedControlPoint;
    }

    /**
     * Gets the current value of didNotStart
     * @return Current value of didNotStart
     */
    @JsonIgnore
    public boolean isDidNotStart() {
	return didNotStart;
    }

    /**
     * Sets the value of didNotStart
     * @param didNotStart New value for didNotStart
     */
    public void setIsDidNotStart(boolean didNotStart) {
	this.didNotStart=didNotStart;
    }

    /**
     * Gets the current value of lastVisitedControlPoint
     * @return Current value of lastVisitedControlPoint
     */
    @JsonIgnore
    public ControlPoint getLastVisitedControlPoint() {
	return lastVisitedControlPoint;
    }

    /**
     * Sets the value of lastVisitedControlPoint
     * @param lastVisitedControlPoint New value for lastVisitedControlPoint
     */
    public void setLastVisitedControlPoint(ControlPoint lastVisitedControlPoint) {
	this.lastVisitedControlPoint=lastVisitedControlPoint;
    }

    /**
     * Gets the current value of lastVisitedCpArrival
     * @return Current value of lastVisitedCpArrival
     */
    @JsonIgnore
    public Timestamp getLastVisitedCpArrival() {
	return lastVisitedCpArrival;
    }

    /**
     * Sets the value of lastVisitedCpArrival
     * @param lastVisitedCpArrival New value for lastVisitedCpArrival
     */
    public void setLastVisitedCpArrival(Timestamp lastVisitedCpArrival) {
	this.lastVisitedCpArrival=lastVisitedCpArrival;
    }

    /**
     * Gets the current value of lastVisitedCpDeparture
     * @return Current value of lastVisitedCpDeparture
     */
    @JsonIgnore
    public Timestamp getLastVisitedCpDeparture() {
	return lastVisitedCpDeparture;
    }

    /**
     * Sets the value of lastVisitedCpDeparture
     * @param lastVisitedCpDeparture New value for lastVisitedCpDeparture
     */
    public void setLastVisitedCpDeparture(Timestamp lastVisitedCpDeparture) {
	this.lastVisitedCpDeparture=lastVisitedCpDeparture;
    }

    /**
     * Gets the transition time for the last visited CP
     * @return transition time for last visited CP, or 0 if the arrival or departure
     *         are not set
     */
    @JsonIgnore
    public Timestamp getLastVisitedCpTransition() {
	if ((lastVisitedCpArrival == null) ||
	    (lastVisitedCpDeparture == null) ||
	    (lastVisitedCpArrival.getTime() == 0) ||
	    (lastVisitedCpDeparture.getTime() == 0)) {
	    return new Timestamp(0);
	} else {
	    return new Timestamp(lastVisitedCpDeparture.getTime() - lastVisitedCpArrival.getTime());
	}
    }

    /**
     * Gets the current value of timeBonuses
     * @return Current value of timeBonuses
     */
    @JsonIgnore
    public Timestamp getTimeBonuses() {
	return timeBonuses;
    }

    /**
     * Sets the value of timeBonuses
     * @param timeBonuses New value for timeBonuses
     */
    public void setTimeBonuses(Timestamp timeBonuses) {
	this.timeBonuses=timeBonuses;
    }

    /**
     * Gets the current value of timePenalties
     * @return Current value of timePenalties
     */
    @JsonIgnore
    public Timestamp getTimePenalties() {
	return timePenalties;
    }

    /**
     * Sets the value of timePenalties
     * @param timePenalties New value for timePenalties
     */
    public void setTimePenalties(Timestamp timePenalties) {
	this.timePenalties=timePenalties;
    }

    /**
     * Gets the current value of cpBonuses
     * @return Current value of cpBonuses
     */
    @JsonIgnore
    public int getCpBonuses() {
	return cpBonuses;
    }

    /**
     * Sets the value of cpBonuses
     * @param cpBonuses New value for cpBonuses
     */
    public void setCpBonuses(int cpBonuses) {
	this.cpBonuses=cpBonuses;
    }

    /**
     * Gets the current value of cpPenalties
     * @return Current value of cpPenalties
     */
    @JsonIgnore
    public int getCpPenalties() {
	return cpPenalties;
    }

    /**
     * Sets the value of cpPenalties
     * @param cpPenalties New value for cpPenalties
     */
    public void setCpPenalties(int cpPenalties) {
	this.cpPenalties=cpPenalties;
    }

    /**
     * Gets the current value of cpWeightBonuses
     * @return Current value of cpWeightBonuses
     */
    @JsonIgnore
    public int getCpWeightBonuses() {
	return cpWeightBonuses;
    }

    /**
     * Sets the value of cpWeightBonuses
     * @param cpWeightBonuses New value for cpWeightBonuses
     */
    public void setCpWeightBonuses(int cpWeightBonuses) {
	this.cpWeightBonuses=cpWeightBonuses;
    }

    /**
     * Gets the current value of cpWeightPenalties
     * @return Current value of cpWeightPenalties
     */
    @JsonIgnore
    public int getCpWeightPenalties() {
	return cpWeightPenalties;
    }

    /**
     * Sets the value of cpWeightPenalties
     * @param cpWeightPenalties New value for cpWeightPenalties
     */
    public void setCpWeightPenalties(int cpWeightPenalties) {
	this.cpWeightPenalties=cpWeightPenalties;
    }

    /**
     * Gets the current value of mandatoryCps
     * @return Current value of mandatoryCps
     */
    @JsonIgnore
    public int getMandatoryCps() {
	return mandatoryCps;
    }

    /**
     * Sets the value of mandatoryCps
     * @param mandatoryCps New value for mandatoryCps
     */
    public void setMandatoryCps(int mandatoryCps) {
	this.mandatoryCps=mandatoryCps;
    }

    /**
     * Gets the current value of optionalCps
     * @return Current value of optionalCps
     */
    @JsonIgnore
    public int getOptionalCps() {
	return optionalCps;
    }

    /**
     * Sets the value of optionalCps
     * @param optionalCps New value for optionalCps
     */
    public void setOptionalCps(int optionalCps) {
	this.optionalCps=optionalCps;
    }

    /**
     * Gets the current value of cpWeight
     * @return Current value of cpWeight
     */
    @JsonIgnore
    public int getCpWeight() {
	return cpWeight;
    }

    /**
     * Sets the value of cpWeight
     * @param cpWeight New value for cpWeight
     */
    public void setCpWeight(int cpWeight) {
	this.cpWeight=cpWeight;
    }

    /**
     * Gets the current value of skippedMandatoryCps
     * @return Current value of skippedMandatoryCps
     */
    @JsonIgnore
    public int getSkippedMandatoryCps() {
	return skippedMandatoryCps;
    }

    /**
     * Sets the value of skippedMandatoryCps
     * @param skippedMandatoryCps New value for skippedMandatoryCps
     */
    public void setSkippedMandatoryCps(int skippedMandatoryCps) {
	this.skippedMandatoryCps=skippedMandatoryCps;
    }

    /**
     * Gets the current value of skippedOptionalCps
     * @return Current value of skippedOptionalCps
     */
    @JsonIgnore
    public int getSkippedOptionalCps() {
	return skippedOptionalCps;
    }

    /**
     * Sets the value of skippedOptionalCps
     * @param skippedOptionalCps New value for skippedOptionalCps
     */
    public void setSkippedOptionalCps(int skippedOptionalCps) {
	this.skippedOptionalCps=skippedOptionalCps;
    }

    /**
     * Gets the current value of skippedCpWeight
     * @return Current value of skippedCpWeight
     */
    @JsonIgnore
    public int getSkippedCpWeight() {
	return skippedCpWeight;
    }

    /**
     * Sets the value of skippedCpWeight
     * @param skippedCpWeight New value for skippedCpWeight
     */
    public void setSkippedCpWeight(int skippedCpWeight) {
	this.skippedCpWeight=skippedCpWeight;
    }


    public int compareTo(Team team) {
	
	if (this.getCourseId() != team.getCourseId()) {
	    return 0;
	} else {
	    return this.getNumber() - team.getNumber();
	}
    }

    /**
     * Generates statistics for Team.
     **/
    public void generateStatistics() {

	if (controlPointList == null) {
	    throw new IllegalStateException("Calling generateStatistics() on Team object without first calling setControlPointList not allowed");
	}

	// default the stats
	missedCutoff = false;
	missedCutoffControlPoint = null;
	unofficial = false;
	unofficialControlPoint = null;
	incomplete = false;
	incompleteControlPoint = null;
	withdrawn = false;
	withdrawnControlPoint = null;
	disqualified = false;
	disqualifiedControlPoint = null;
	didNotStart = false;
	lastVisitedControlPoint = null;
	lastVisitedCpArrival = null;
	lastVisitedCpDeparture = null;
	timeBonuses = new Timestamp(0);
	timePenalties = new Timestamp(0);
	cpBonuses = 0;
	cpPenalties = 0;
	cpWeightBonuses = 0;
	cpWeightPenalties = 0;
	mandatoryCps = 0;
	optionalCps = 0;
	cpWeight = 0;
	skippedMandatoryCps = 0;
	skippedOptionalCps = 0;
	skippedCpWeight = 0;

	// calculate didNotStart
	//
	// should set as did not start if calendar shows current time
	// time is after event start and no CPs have been visited
	//
	// for now, we'll just say if no CPs have been visited
	if (cpVisitTable.size() == 0) {
	    didNotStart = true;
	    return;
	}


	// iterate over ranks, and generate chronological 
	// differences
	ListIterator<Rank> rli = rankList.listIterator();
	Rank previousRank = null;
	Rank rank = null;
	while (rli.hasNext()) {
	    rank = rli.next();
	    rank.setRankChangeFrom(previousRank);
	    previousRank = rank;
	}

	
	//
	// PERHAPS ITS MORE EFFICIENT TO LOOP THROUGH CPVISITS HERE
	// RATHER CPS AND THEN CHECKING IF TEAM ACQUIRED IT.  SINCE
	// WE USE CP, MAY NOT BE THOUGH, BECAUSE A LOOKUP TO GRAB
	// CP USING CPVISIT.CPID WOULD BE REQUIRED.
	//


	ListIterator<ControlPoint> cpli = controlPointList.listIterator();
	ControlPoint cp = null;
	CpVisit cpVisit = null;
	while (cpli.hasNext()) {
	    cp = cpli.next();
	    cpVisit = cpVisitTable.get(cp.getId());
	    
	    if (cpVisit != null) {

		//
		// update statuses/tracking
		//

		// calculate missedCutoff/where
		if (cpVisit.getIsMissedCutoff().equals("yes")) {
		    missedCutoff = true;
		    missedCutoffControlPoint = cp;
		}

		// calculate unofficial/where
		if (cpVisit.getIsUnofficial().equals("yes")) {
		    unofficial = true;
		    unofficialControlPoint = cp;
		}

		// calculate incomplete/where
		if (cpVisit.getIsIncomplete().equals("yes")) {
		    incomplete = true;
		    incompleteControlPoint = cp;
		}

		// calculate withdrawn/where
		if (cpVisit.getIsWithdrawn().equals("yes")) {
		    withdrawn = true;
		    withdrawnControlPoint = cp;
		} 

		// calculate disqualified/where
		if (cpVisit.getIsDisqualified().equals("yes")) {
		    disqualified = true;
		    disqualifiedControlPoint = cp;
		}

		//
		// update stats
		//
		
		// calculate timeBonuses
		if (cpVisit.getTimeBonusAssessed().getTime() != 0) {
		    timeBonuses = new Timestamp(timeBonuses.getTime() + cpVisit.getTimeBonusAssessed().getTime());
		}

		// calculate timePenalties
		if (cpVisit.getTimePenaltyAssessed().getTime() != 0) {
		    timePenalties = new Timestamp(timePenalties.getTime() + cpVisit.getTimePenaltyAssessed().getTime());
		}
		
		// calculate cpBonuses
		cpBonuses += cpVisit.getCpBonusAssessed();
		
		// calculate cpPenalties
		cpPenalties += cpVisit.getCpPenaltyAssessed();

		// calculate cpWeightBonuses
		cpWeightBonuses += cpVisit.getWeightBonusAssessed();

		// calculate cpWeightPenalties
		cpWeightPenalties += cpVisit.getWeightPenaltyAssessed();
		
		//
		//  ASSUMPTION HERE IS IF CP_Visit_Rel EXISTS, THEN 
		//  IT IS EITHER ACQUIRED (VIA isAcquired, arrival, OR departure)
		//  OR IT IS SKIPPED (VIA isSkipped).  
		//

		// calculate lastVisitedControlPoint
		// calculate lastVisitedCpArrival
		// calculate lastVisitedCpDeparture
		if (cpVisit.getIsSkipped().equals("no")) {
		    lastVisitedControlPoint = cp;
		    lastVisitedCpArrival = cpVisit.getArrival();
		    lastVisitedCpDeparture = cpVisit.getDeparture();
		}

		// calculate mandatoryCps
		// calculate optionalCps
		// calculate cpWeight
		// calculate skippedMandatoryCps
		// calculate skippedOptionalCps
		// caluclate skippedCpWeight
		if (cp.getIsMandatory().equals("yes")) {
		    if (cpVisit.getIsSkipped().equals("yes")) {
			skippedMandatoryCps++;
			skippedCpWeight += cp.getWeight();
		    } else if (cpVisit.getIsSkipped().equals("no")) {
			mandatoryCps++;
			cpWeight += cp.getWeight();
		    }
		} else if (cp.getIsMandatory().equals("no")) {
		    if (cpVisit.getIsSkipped().equals("yes")) {
			skippedOptionalCps++;
			skippedCpWeight += cp.getWeight();
		    } else if (cpVisit.getIsSkipped().equals("no")) {
			optionalCps++;
			cpWeight += cp.getWeight();
		    }
		}

	    }//end of if (cpVisit != null)

	}//end of while(cpli.hasNext())
	
    }//end of method generateStatistics()

  public String toString() {

      String r = new String();
      r += "Team [";
      r += "id=" + getId() + ",";
      r += "name=" + name + ",";
      r += "courseId=" + courseId + ",";
      r += "divisionId=" + divisionId + ",";
      r += "number=" + number + ",";
      r += "registrationTime=" + registrationTime + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }

}
