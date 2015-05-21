package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: ControlPointDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/20
 *  Description: ControlPointDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.ControlPoint;


public interface ControlPointDao {

    public ControlPoint getControlPointById(String id);

    public ControlPoint getControlPointById(long id);
    
    public ControlPoint getControlPointByCourseIdAndName(String id, String name);

    public ControlPoint getControlPointByCourseIdAndName(long id, String name);

    public ControlPoint getControlPointByCourseIdAndCpOrder(String id, String cpOrder);

    public ControlPoint getControlPointByCourseIdAndCpOrder(long id, String cpOrder);

    public ControlPoint getControlPointByCourseIdAndCpOrder(String id, int cpOrder);

    public ControlPoint getControlPointByCourseIdAndCpOrder(long id, int cpOrder);

    public int getMaxControlPointOrderByCourseId(String id);

    public int getMaxControlPointOrderByCourseId(long id);

    public int getMostLikelyZoneNumberByCourseId(String id);

    public int getMostLikelyZoneNumberByCourseId(long id);

    public String getMostLikelyZoneCharByCourseId(String id);

    public String getMostLikelyZoneCharByCourseId(long id);

    public List getControlPointsByCourseId(String id, String order);

    public List getControlPointsByCourseId(long id, String order);

    public void insertControlPoint(ControlPoint controlPoint);

    public void updateControlPoint(ControlPoint controlPoint);

    public void deleteControlPointById(String id);

    public void deleteControlPointById(long id);

}
