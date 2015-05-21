package com.myrunning.leaderboard.model;

/**
 *  File: Admin.java
 *  Author:  Joshua Forester
 *  Date: 9/10/2009
 *  Description: Bean class for Admin object.
 **/


import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;


public class Admin extends Person implements Serializable {

  private String username;
  private String password;
  private String confirmPassword;
  private String isDisabled;
  private String isDeleted;


  /**
   * Creates a new instance of Admin
   */
  public Admin() {
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

  /**
   * Gets the current value of confirmPassword
   * @return Current value of confirmPassword
   */
  public String getConfirmPassword() {
    return confirmPassword;
  }

  /**
   * Sets the value of confirmPassword
   * @param confirmPassword New value for confirmPassword
   */
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword=confirmPassword;
  }

  /**
   * Gets the current value of isDisabled
   * @return Current value of isDisabled
   */
  public String getIsDisabled() {
    return isDisabled;
  }

  /**
   * Sets the value of isDisabled
   * @param isDisabled New value for isDisabled
   */
  public void setIsDisabled(String isDisabled) {
    this.isDisabled=isDisabled;
  }

  /**
   * Gets the current value of isDeleted
   * @return Current value of isDeleted
   */
  public String getIsDeleted() {
    return isDeleted;
  }

  /**
   * Sets the value of isDeleted
   * @param isDeleted New value for isDeleted
   */
  public void setIsDeleted(String isDeleted) {
    this.isDeleted=isDeleted;
  }

  public String toString() {

      String r = new String();
      r += "Admin [";
      r += "id=" + getId() + ",";
      r += "username=" + username + ",";
      r += "password=" + password + ",";
      r += "confirmPassword=" + confirmPassword + ",";
      r += "isDisabled=" + isDisabled + ",";
      r += "isDeleted=" + isDeleted + "]";
      r += super.toString();

      return r;
  }


}