package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: ComparatorDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/26
 *  Description: ComparatorDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.Comparator;


public interface ComparatorDao {

    public Comparator getComparatorById(String id);

    public Comparator getComparatorById(long id);
    
    public int getMaxComparatorOrderByCourseId(String id);

    public int getMaxComparatorOrderByCourseId(long id);

    public List getComparatorsByCourseId(String id);

    public List getComparatorsByCourseId(long id);

    public void insertComparator(Comparator comparator);

    public void updateComparator(Comparator comparator);

    public void deleteComparatorById(String id);

    public void deleteComparatorById(long id);

}
