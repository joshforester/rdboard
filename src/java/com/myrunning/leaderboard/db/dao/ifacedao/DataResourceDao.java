package com.myrunning.leaderboard.db.dao.ifacedao;

/**
 *  File: DataResourceDao.java
 *  Author: Joshua Forester
 *  Date: 2009/10/08
 *  Description: DataResourceDao interface.
 **/


import java.util.List;
import com.myrunning.leaderboard.model.DataResource;


public interface DataResourceDao {

    public DataResource getDataResourceById(String id);

    public DataResource getDataResourceById(long id);

    public DataResource getDataResourceByIdAndAdminId(String id, String adminId);

    public DataResource getDataResourceByIdAndAdminId(String id, long adminId);

    public DataResource getDataResourceByIdAndAdminId(long id, String adminId);

    public DataResource getDataResourceByIdAndAdminId(long id, long adminId);

    public List getDataResourcesByAdminId(String id);

    public List getDataResourcesByAdminId(long id);

    public void insertDataResource(DataResource dataResource);

    public void updateDataResource(DataResource dataResource);

    public void deleteDataResourceById(String id);

    public void deleteDataResourceById(long id);

}
