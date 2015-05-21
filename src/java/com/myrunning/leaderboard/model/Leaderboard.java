package com.myrunning.leaderboard.model;

/**
 *  File: Leaderboard.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/04
 *  Description: Leaderboard object.
 **/


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Hashtable;
import java.sql.Timestamp;
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;
import com.myrunning.leaderboard.model.util.CpVisitControlPointCpOrderComparator;
import com.myrunning.leaderboard.model.util.CpVisitTeamNumberComparator;



public class Leaderboard {

    private List<ControlPoint> cpList;
    private Hashtable<Long, ControlPoint> cpTable;
    private List<Team> teamList;
    private Hashtable<Long, Team> teamTable;
    private Course course;
    private Timestamp lastModified;


    public Leaderboard (Course course) {
	this.cpList = new ArrayList<ControlPoint>();
	this.cpTable = new Hashtable<Long, ControlPoint>();
	this.teamList = new ArrayList<Team>();
	this.teamTable = new Hashtable<Long, Team>();
	this.course = course;
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

    public void addControlPoint (ControlPoint cp) {
	cpList.add(cp);

        if (cpTable == null) {
            cpTable = new Hashtable<Long, ControlPoint>();
        }
        cpTable.put(cp.getId(), cp);

	// Need to add all the Teams to the CP, but really looking to capture CPVisits...
	// A CPVisit would start out with a blank arrival/departure time
	//
	// perhaps this should be for persisting the cp, and CPVisits should be for loading the model...
    }

    public void addAllControlPoints (Collection<ControlPoint> controlPoints) {
	cpList.addAll(controlPoints);

        if (cpTable == null) {
            cpTable = new Hashtable<Long, ControlPoint>();
        }

	Iterator<ControlPoint> cpi = controlPoints.iterator();
	ControlPoint cp = null;
	while (cpi.hasNext()) {
	    cp = cpi.next();
	    cpTable.put(cp.getId(), cp);
	}
    }

    public void addTeam(Team team) {
	teamList.add(team);

        if (teamTable == null) {
            teamTable = new Hashtable<Long, Team>();
        }
        teamTable.put(team.getId(), team);

	/*
	// Need to add all the CPs to a team, but really looking to capture CPVisits...
	ListIterator<ControlPoint> cpli = cpList.listIterator();
	while (cpli.hasNext()) {
	    ControlPoint cp = cpli.next();
	    cp.addTeam(team);
	}
	*/
    }

    public void addAllTeams (Collection<Team> teams) {
        teamList.addAll(teams);

        if (teamTable == null) {
            teamTable = new Hashtable<Long, Team>();
        }

	Iterator<Team> ti = teams.iterator();
	Team team = null;
	while (ti.hasNext()) {
	    team = ti.next();
	    teamTable.put(team.getId(), team);
	}
    }

    public List<Team> getTeamList() {
	return this.teamList;
    }

    public Hashtable<Long, Team> getTeamTable() {
	return this.teamTable;
    }

    public List<ControlPoint> getCpList() {
	return this.cpList;
    }

    public Hashtable<Long, ControlPoint> getCpTable() {
	return this.cpTable;
    }


    public void sortTeams() {
	if (course == null) {
	    throw new IllegalStateException("call to sortTeams() before setCourse()");
	}
	if (course.getComparator() == null) {
	    throw new IllegalStateException("call to sortTeams() before course.setComparator()");
	}
	Collections.sort(teamList, course.getComparator());
    }


    public void sortControlPoints() {
	Collections.sort(cpList);
    }


    public void sortCpVisits() {
	ListIterator<Team> teamli = teamList.listIterator();
	Team team = null;
	while (teamli.hasNext()) {
	    team = teamli.next();
	    Collections.sort(team.getCpVisitList(), new CpVisitControlPointCpOrderComparator(cpTable));
	}

	ListIterator<ControlPoint> cpli = cpList.listIterator();
	ControlPoint cp = null;
	while (cpli.hasNext()) {
	    cp = cpli.next();
	    Collections.sort(cp.getCpVisitList(), new CpVisitTeamNumberComparator(teamTable));
	}

    }


    /**
     *  Assumptions are:
     *  sortTeams() has been called
     *  Team.divisionId has been set.
     */
    public void rankTeams() {
	ListIterator<Team> teamli = teamList.listIterator();
	Bag divisionPlaceBag = new HashBag();
	while (teamli.hasNext()) {
	    Team team = teamli.next();
	    divisionPlaceBag.add(new Long(team.getDivisionId()));
	    team.setDivisionPlace(divisionPlaceBag.getCount(new Long(team.getDivisionId())));
	    team.setCoursePlace(divisionPlaceBag.size());
	}

    }

    /**
     * Gets the current value of course
     * @return Current value of course
     */
    public Course getCourse() {
	return course;
    }
    
    /**
     * Sets the value of course
     * @param course New value for course
     */
    public void setCourse(Course course) {
	this.course=course;
    }
    
    public String toString() {

	String r = new String();

	r += "Control Points\r\n";
	ListIterator<ControlPoint> cpli = cpList.listIterator();
	while (cpli.hasNext()) {
	    ControlPoint cp = cpli.next();
	    r += cp.toString();
	}

	r += "Teams\r\n";
	ListIterator<Team> teamli = teamList.listIterator();
	while (teamli.hasNext()) {
	    Team team = teamli.next();
	    r += team.toString();
	}

	return r;
    }

}
