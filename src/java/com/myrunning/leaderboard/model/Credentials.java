package com.myrunning.leaderboard.model;

/**
 *  File: Credentials.java
 *  Author:  Joshua Forester
 *  Date: 9/10/2009
 *  Description: Bean class for Credentials object.
 **/


import java.io.Serializable;
import java.sql.Timestamp;


public class Credentials implements Serializable {

  private String username;
  private String password;

  /**
   * Creates a new instance of Credentials
   */
  public Credentials() {
  }

  /**
   * Gets the current value of username
   * @return Current value of username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the value of username
   * @param username New value for username
   */
  public void setUsername(String username) {
    this.username=username;
  }

  /**
   * Gets the current value of password
   * @return Current value of password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the value of password
   * @param password New value for password
   */
  public void setPassword(String password) {
    this.password=password;
  }

}