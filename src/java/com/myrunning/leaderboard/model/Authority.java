package com.myrunning.leaderboard.model;

/**
 *  File: Authority.java
 *  Author:  Joshua Forester
 *  Date: 10/24/2009
 *  Description: Bean class for Authority object.
 **/


import java.util.List;
import java.util.Arrays;
import java.io.Serializable;
import java.sql.Timestamp;


public class Authority implements Serializable {

  private long id;
  private String authority;

  private static List<String> authorities = 
      Arrays.asList("ROLE_COMPETITOR",
		    "ROLE_EVENTADMIN",
		    "ROLE_PROMOTER",
		    "ROLE_ROOT");

  private static List<String> adminAuthorities = 
      Arrays.asList("ROLE_EVENTADMIN",
		    "ROLE_PROMOTER",
		    "ROLE_ROOT");

  private static List<String> racerAuthorities = 
      Arrays.asList("ROLE_COMPETITOR");
		    


  /**
   * Creates a new instance of Authority
   */
  public Authority() {
  }

  /**
   * Gets the current value of id
   * @return Current value of id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the value of id
   * @param id New value for id
   */
  public void setId(long id) {
    this.id=id;
  }

  /**
   * Gets the current value of authority
   * @return Current value of authority
   */
  public String getAuthority() {
    return authority;
  }

  /**
   * Sets the value of authority
   * @param authority New value for authority
   */
  public void setAuthority(String authority) {
    this.authority=authority;
  }


  /**
   * Gets the current value of authorities
   * @return Current value of authorities
   */
  public static List<String> getAuthorities() {
    return authorities;
  }

  /**
   * Gets the current value of adminAuthorities
   * @return Current value of adminAuthorities
   */
  public static List<String> getAdminAuthorities() {
    return adminAuthorities;
  }

  /**
   * Gets the current value of racerAuthorities
   * @return Current value of racerAuthorities
   */
  public static List<String> getRacerAuthorities() {
    return racerAuthorities;
  }


  public String toString() {

      String r = new String();
      r += "Authority [";
      r += "id=" + id + ",";
      r += "authority=" + authority + "]";

      return r;
  }

}