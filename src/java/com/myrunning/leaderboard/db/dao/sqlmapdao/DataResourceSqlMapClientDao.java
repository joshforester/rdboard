package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: DataResourceSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/10/08
 *  Description: SqlMapDao for DataResource.
 **/


import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.myrunning.leaderboard.model.DataResource;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;
import com.myrunning.leaderboard.db.dao.query.IdAndAdminIdQuery;


public class DataResourceSqlMapClientDao extends SqlMapClientDaoSupport implements DataResourceDao {

    static Logger logger = Logger.getLogger(DataResourceSqlMapClientDao.class);


    public DataResourceSqlMapClientDao() {
	// empty
    }

    public DataResource getDataResourceById(String id) {
	return (DataResource) getSqlMapClientTemplate().queryForObject("getDataResourceById", id);
    }

    public DataResource getDataResourceById(long id) {
	return getDataResourceById(String.valueOf(id));
    }

    public DataResource getDataResourceByIdAndAdminId(String id, String adminId) {
	IdAndAdminIdQuery query = new IdAndAdminIdQuery();
	query.setId(Long.parseLong(id));
	query.setAdminId(Long.parseLong(adminId));
	return (DataResource) getSqlMapClientTemplate().queryForObject("getDataResourceByIdAndAdminId", query);
    }

    public DataResource getDataResourceByIdAndAdminId(long id, String adminId) {
	return getDataResourceByIdAndAdminId(String.valueOf(id), adminId);
    }

    public DataResource getDataResourceByIdAndAdminId(String id, long adminId) {
	return getDataResourceByIdAndAdminId(id, String.valueOf(adminId));
    }

    public DataResource getDataResourceByIdAndAdminId(long id, long adminId) {
	return getDataResourceByIdAndAdminId(String.valueOf(id), String.valueOf(adminId));
    }

    public List getDataResourcesByAdminId(String id) {
	if ((id == null) || (id.equals("0"))) {
	    return null;
	} else {
	    return getSqlMapClientTemplate().queryForList("getDataResourcesByAdminId", id);
	}
    }

    public List getDataResourcesByAdminId(long id) {
	return getDataResourcesByAdminId(String.valueOf(id));
    }

    public void insertDataResource(DataResource dataResource) {
	getSqlMapClientTemplate().update("insertDataResource", dataResource);
    }

    public void updateDataResource(DataResource dataResource) {
	getSqlMapClientTemplate().update("updateDataResource", dataResource);
    }

    public void deleteDataResourceById(String id) {
	getSqlMapClientTemplate().update("deleteDataResourceById", id);
    }

    public void deleteDataResourceById(long id) {
	deleteDataResourceById(String.valueOf(id));
    }

    public static DataResource decorate(DataResource dataResource) {

      return dataResource;

    }


}// END OF CLASS DataResourceSqlMapClientDao
