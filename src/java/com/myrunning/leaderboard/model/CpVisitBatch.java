package com.myrunning.leaderboard.model;

/**
 *  File: CpVisitBatch.java
 *  Author:  Joshua Forester
 *  Date: 9/18/2009
 *  Description: Bean class for CpVisitBatch object.
 **/


import java.util.List;
import java.util.ArrayList;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import java.io.Serializable;
import java.sql.Timestamp;


public class CpVisitBatch implements Serializable {

    private List batch = 
	LazyList.decorate(new ArrayList(), 
			  FactoryUtils.instantiateFactory(CpVisit.class));

  /**
   * Creates a new instance of Credentials
   */
    /*
  public Credentials() {
  }
    */

  /**
   * Gets the current value of batch
   * @return Current value of batch
   */
  public List getBatch() {
    return batch;
  }

  /**
   * Sets the value of batch
   * @param batch New value for batch
   */
  public void setBatch(List batch) {
    this.batch=batch;
  }

}