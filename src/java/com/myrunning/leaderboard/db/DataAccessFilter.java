package com.myrunning.leaderboard.db;

/**
 *  File: DataAccessFilter.java
 *  Author:  Joshua Forester
 *  Date: 2009/10/08
 *  Description: Class for ensuring web users can only access information
 *               they create.
 **/


import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.model.DataResource;
import com.myrunning.leaderboard.db.dao.ifacedao.DataResourceDao;


public class DataAccessFilter {

    static Logger logger = Logger.getLogger(DataAccessFilter.class);

    private static DataResourceDao dataResourceDao;


    public void setDataResourceDao(DataResourceDao dataResourceDao) {
	this.dataResourceDao=dataResourceDao;
    }


    private static HashMap<Long, String> initializeAdminResources(long adminId) {
    
	// build the HashMap
	HashMap<Long, String> hm = new HashMap<Long, String>();

	List<DataResource> adminList = dataResourceDao.getDataResourcesByAdminId(adminId);
	ListIterator<DataResource> li = adminList.listIterator();
	DataResource dataResource = null;
	while (li.hasNext()) {
	    dataResource = li.next();
	    //
	    //  Perhaps better data structure for this.  No real need to store
	    //  any object in the HashMap, just need constant speed lookup.
	    //  Put zero-length string in here so we can tell the difference
	    //  between a get call that returns null because the key wasn't
	    //  there or because the object was a null reference.
	    //
	    hm.put(new Long(dataResource.getId()), new String(""));
	}

	return hm;
    }



    /**
     * Filter a list to ensure the admin has access to the data.  This method
     * alters the collection.
     * @param col the collection to filter on access
     * @param adminId the id of the admin that the data should be filtered by
     * @return returns shortened list based on access
     **/
    public static Collection filterColById(Collection col, long adminId) {

	if ((col == null) || (col.isEmpty())) {
	    return col;
	}

	HashMap<Long, String> hm = initializeAdminResources(adminId);

	// subtract the HashMap from list
	Iterator<DataResource> i = col.iterator();
	DataResource dataResource = null;
	while (i.hasNext()) {
	    dataResource = (DataResource) i.next();
	    if (hm.get(new Long(dataResource.getId())) == null) {
		i.remove();
	    }
	}

	return col;
    }


    /**
     * Check if an admin has full access to the data in a collection.
     * This does NOT alter the collection.
     * @param col the collection to check
     * @param adminId the id of the admin that the data should be checked against
     * @return returns true if the admin has access to all data resources in the collection, false if not
     **/
    public static boolean hasFullAccessTo(Collection col, long adminId) {

	if ((col == null) || (col.isEmpty())) {
	    return true;
	}

	boolean accessGranted = true;
	HashMap<Long, String> hm = initializeAdminResources(adminId);

	// subtract the HashMap from list
	Iterator<DataResource> i = col.iterator();
	DataResource dataResource = null;
	while ((i.hasNext()) && (!accessGranted)) {
	    dataResource = (DataResource) i.next();
	    if (hm.get(new Long(dataResource.getId())) == null) {
		accessGranted = false;
	    }
	}

	return accessGranted;
    }


    /**
     * Filter data resource to ensure the admin has access to it.
     * @param dataResource the resource to check for access
     * @param adminId the id of the admin that the data should be filtered by
     * @return dataResource if access (original object), null if not
     **/
    public static DataResource filterDataResourceById(DataResource dataResource, long adminId) {

	if (dataResource == null) {
	    return null;
	}
	
	DataResource dr = filterDataResourceById(dataResource.getId(), adminId);
	if (dr == null) {
	    return null;
	} else {
	    return dataResource;
	}

    }


    /**
     * Filter data resource id to ensure the admin has access to it.
     * @param id the id of the resource to check for access
     * @param adminId the id of the admin that the data should be filtered by
     * @return dataResource object associated with access, null if not
     **/
    public static DataResource filterDataResourceById(long id, long adminId) {

	return dataResourceDao.getDataResourceByIdAndAdminId(id, adminId);

    }


}
