package com.myrunning.leaderboard.model;

/**
 *  File: Person.java
 *  Author:  Joshua Forester
 *  Date: 8/21/2009
 *  Description: Bean class for Person object.
 **/


import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;
import java.sql.Timestamp;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import com.myrunning.leaderboard.global.RDStringUtils;
import com.myrunning.leaderboard.model.stats.Correlator;


@XStreamAlias("person")
public class Person extends DataResource implements Correlator<Person>, Serializable {

  @XStreamOmitField
  private long identityId;

  @XStreamOmitField
  private String email;

  private String firstName;
  private String lastName;
  private String city;
  private String region;
  private String country;
  private int zip;
  private String gender;

  @XStreamOmitField
  private Timestamp birthday;

  @XStreamOmitField
  private String homePhone;

  @XStreamOmitField
  private String cellPhone;

  @XStreamOmitField
  private String workPhone;

  @XStreamOmitField
  private String defaultEmergencyContactFirstName;

  @XStreamOmitField  
  private String defaultEmergencyContactLastName;
  
  @XStreamOmitField
  private String defaultEmergencyContactRelation;

  @XStreamOmitField
  private String defaultEmergencyPhone;

  @XStreamOmitField
  private String defaultShirtSize;

  @XStreamOmitField
  private String defaultShoeSize;

  private String occupation;
  private String selfDescription;
  private String hobbiesInterests;
  private String favoriteQuote;
  private String favoriteThings;
  private String website;

  @XStreamOmitField
  private List authorities;

  @XStreamOmitField
  private String isDisabled;

  @XStreamOmitField
  private String isDeleted;

  private static List<String> shirtSizes = 
      Arrays.asList("x-small", 
		    "small",
		    "medium",
		    "large",
		    "x-large",
		    "xx-large",
		    "xxx-large");

  private static List<String> emergencyContactRelations = 
      Arrays.asList("spouse",
		    "sibling",
		    "parent",
		    "child",
		    "other");

  /**
   * Creates a new instance of Person
   */
  public Person() {
      authorities = new ArrayList();
      this.setIsDisabled(new String("no"));
      this.setIsDeleted(new String("no"));
  }

  /**
   * Gets the current value of identityId
   * @return Current value of identityId
   */
  @JsonIgnore
  public long getIdentityId() {
    return identityId;
  }

  /**
   * Sets the value of identityId
   * @param identityId New value for identityId
   */
  public void setIdentityId(long identityId) {
    this.identityId=identityId;
  }

  /**
   * Gets the current value of email
   * @return Current value of email
   */
  @JsonIgnore
  public String getEmail() {
    return email;
  }

  /**
   * Sets the value of email
   * @param email New value for email
   */
  public void setEmail(String email) {
    this.email=email;
  }

  /**
   * Gets the current value of firstName
   * @return Current value of firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the value of firstName
   * @param firstName New value for firstName
   */
  public void setFirstName(String firstName) {
    this.firstName=firstName;
  }

  /**
   * Gets the current value of lastName
   * @return Current value of lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the value of lastName
   * @param lastName New value for lastName
   */
  public void setLastName(String lastName) {
    this.lastName=lastName;
  }

  /**
   * Gets the current value of city
   * @return Current value of city
   */
  
  public String getCity() {
    return city;
  }

  /**
   * Sets the value of city
   * @param city New value for city
   */
  public void setCity(String city) {
    this.city=city;
  }

  /**
   * Gets the current value of region
   * @return Current value of region
   */
  public String getRegion() {
    return region;
  }

  /**
   * Sets the value of region
   * @param region New value for region
   */
  public void setRegion(String region) {
    this.region=region;
  }

  /**
   * Gets the current value of country
   * @return Current value of country
   */
  
  public String getCountry() {
    return country;
  }

  /**
   * Sets the value of country
   * @param country New value for country
   */
  public void setCountry(String country) {
    this.country=country;
  }

  /**
   * Gets the current value of zip
   * @return Current value of zip
   */
  public int getZip() {
    return zip;
  }

  /**
   * Sets the value of zip
   * @param zip New value for zip
   */
  public void setZip(int zip) {
    this.zip=zip;
  }

  /**
   * Gets the current value of gender
   * @return Current value of gender
   */
  
  public String getGender() {
    return gender;
  }

  /**
   * Sets the value of gender
   * @param gender New value for gender
   */
  public void setGender(String gender) {
    this.gender=gender;
  }

  /**
   * Gets the current value of birthday
   * @return Current value of birthday
   */
  @JsonIgnore
  public Timestamp getBirthday() {
    return birthday;
  }

  /**
   * Sets the value of birthday
   * @param birthday New value for birthday
   */
  public void setBirthday(Timestamp birthday) {
    this.birthday=birthday;
  }

  /**
   * Gets the current value of homePhone
   * @return Current value of homePhone
   */
  @JsonIgnore
  public String getHomePhone() {
    return homePhone;
  }

  /**
   * Sets the value of homePhone
   * @param homePhone New value for homePhone
   */
  public void setHomePhone(String homePhone) {
    this.homePhone=homePhone;
  }

  /**
   * Gets the current value of cellPhone
   * @return Current value of cellPhone
   */
  @JsonIgnore
  public String getCellPhone() {
    return cellPhone;
  }

  /**
   * Sets the value of cellPhone
   * @param cellPhone New value for cellPhone
   */
  public void setCellPhone(String cellPhone) {
    this.cellPhone=cellPhone;
  }

  /**
   * Gets the current value of workPhone
   * @return Current value of workPhone
   */
  @JsonIgnore
  public String getWorkPhone() {
    return workPhone;
  }

  /**
   * Sets the value of workPhone
   * @param workPhone New value for workPhone
   */
  public void setWorkPhone(String workPhone) {
    this.workPhone=workPhone;
  }

  /**
   * Gets the current value of defaultEmergencyContactFirstName
   * @return Current value of defaultEmergencyContactFirstName
   */
  @JsonIgnore
  public String getDefaultEmergencyContactFirstName() {
    return defaultEmergencyContactFirstName;
  }

  /**
   * Sets the value of defaultEmergencyContactFirstName
   * @param defaultEmergencyContactFirstName New value for defaultEmergencyContactFirstName
   */
  public void setDefaultEmergencyContactFirstName(String defaultEmergencyContactFirstName) {
    this.defaultEmergencyContactFirstName=defaultEmergencyContactFirstName;
  }

  /**
   * Gets the current value of defaultEmergencyContactLastName
   * @return Current value of defaultEmergencyContactLastName
   */
  @JsonIgnore
  public String getDefaultEmergencyContactLastName() {
    return defaultEmergencyContactLastName;
  }

  /**
   * Sets the value of defaultEmergencyContactLastName
   * @param defaultEmergencyContactLastName New value for defaultEmergencyContactLastName
   */
  public void setDefaultEmergencyContactLastName(String defaultEmergencyContactLastName) {
    this.defaultEmergencyContactLastName=defaultEmergencyContactLastName;
  }

  /**
   * Gets the current value of defaultEmergencyContactRelation
   * @return Current value of defaultEmergencyContactRelation
   */
  @JsonIgnore
  public String getDefaultEmergencyContactRelation() {
    return defaultEmergencyContactRelation;
  }

  /**
   * Sets the value of defaultEmergencyContactRelation
   * @param defaultEmergencyContactRelation New value for defaultEmergencyContactRelation
   */
  public void setDefaultEmergencyContactRelation(String defaultEmergencyContactRelation) {
    this.defaultEmergencyContactRelation=defaultEmergencyContactRelation;
  }

  /**
   * Gets the current value of defaultEmergencyPhone
   * @return Current value of defaultEmergencyPhone
   */
  @JsonIgnore
  public String getDefaultEmergencyPhone() {
    return defaultEmergencyPhone;
  }

  /**
   * Sets the value of defaultEmergencyPhone
   * @param defaultEmergencyPhone New value for defaultEmergencyPhone
   */
  public void setDefaultEmergencyPhone(String defaultEmergencyPhone) {
    this.defaultEmergencyPhone=defaultEmergencyPhone;
  }

  /**
   * Gets the current value of defaultShirtSize
   * @return Current value of defaultShirtSize
   */
  @JsonIgnore
  public String getDefaultShirtSize() {
    return defaultShirtSize;
  }

  /**
   * Sets the value of defaultShirtSize
   * @param defaultShirtSize New value for defaultShirtSize
   */
  public void setDefaultShirtSize(String defaultShirtSize) {
    this.defaultShirtSize=defaultShirtSize;
  }

  /**
   * Gets the current value of defaultShoeSize
   * @return Current value of defaultShoeSize
   */
  @JsonIgnore
  public String getDefaultShoeSize() {
    return defaultShoeSize;
  }

  /**
   * Sets the value of defaultShoeSize
   * @param defaultShoeSize New value for defaultShoeSize
   */
  public void setDefaultShoeSize(String defaultShoeSize) {
    this.defaultShoeSize=defaultShoeSize;
  }

  /**
   * Gets the current value of occupation
   * @return Current value of occupation
   */
  public String getOccupation() {
    return occupation;
  }

  /**
   * Sets the value of occupation
   * @param occupation New value for occupation
   */
  public void setOccupation(String occupation) {
    this.occupation=occupation;
  }

  /**
   * Gets the current value of selfDescription
   * @return Current value of selfDescription
   */
  public String getSelfDescription() {
    return selfDescription;
  }

  /**
   * Sets the value of selfDescription
   * @param selfDescription New value for selfDescription
   */
  public void setSelfDescription(String selfDescription) {
    this.selfDescription=selfDescription;
  }

  /**
   * Gets the current value of hobbiesInterests
   * @return Current value of hobbiesInterests
   */
  public String getHobbiesInterests() {
    return hobbiesInterests;
  }

  /**
   * Sets the value of hobbiesInterests
   * @param hobbiesInterests New value for hobbiesInterests
   */
  public void setHobbiesInterests(String hobbiesInterests) {
    this.hobbiesInterests=hobbiesInterests;
  }

  /**
   * Gets the current value of favoriteQuote
   * @return Current value of favoriteQuote
   */
  public String getFavoriteQuote() {
    return favoriteQuote;
  }

  /**
   * Sets the value of favoriteQuote
   * @param favoriteQuote New value for favoriteQuote
   */
  public void setFavoriteQuote(String favoriteQuote) {
    this.favoriteQuote=favoriteQuote;
  }

  /**
   * Gets the current value of favoriteThings
   * @return Current value of favoriteThings
   */
  public String getFavoriteThings() {
    return favoriteThings;
  }

  /**
   * Sets the value of favoriteThings
   * @param favoriteThings New value for favoriteThings
   */
  public void setFavoriteThings(String favoriteThings) {
    this.favoriteThings=favoriteThings;
  }

  /**
   * Gets the current value of website
   * @return Current value of website
   */
  public String getWebsite() {
    return website;
  }

  /**
   * Sets the value of website
   * @param website New value for website
   */
  public void setWebsite(String website) {
    this.website=website;
  }

  /**
   * Gets the current value of authorities
   * @return Current value of authorities
   */
  @JsonIgnore
  public List getAuthorities() {
    if (authorities == null) {
      setAuthorities(new ArrayList());
    }
    return authorities;
  }

  /**
   * Sets the value of authorities
   * @param authorities New value for authorities
   */
  public void setAuthorities(List authorities) {
    this.authorities=authorities;
  }

  /**
   * Checks to see if the person has a certain authority.
   * @param authority the authority to check
   * @return true if the person has it, false if not
   */
  public boolean hasAuthority(String authority) {
    ListIterator<String> li = ((List<String>) getAuthorities()).listIterator();
    while (li.hasNext()) {
	if (li.next().equals(authority)) {
	    return true;
	}
    }

    return false;
  }

  /**
   * Gets the current value of isDisabled
   * @return Current value of isDisabled
   */
  @JsonIgnore
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
  @JsonIgnore
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

  /**
   * Gets the current value of shirtSizes
   * @return Current value of shirtSizes
   */
  @JsonIgnore
  public static List<String> getShirtSizes() {
    return shirtSizes;
  }

  /**
   * Gets the current value of emergencyContactRelations
   * @return Current value of emergencyContactRelations
   */
  @JsonIgnore
  public static List<String> getEmergencyContactRelations() {
    return emergencyContactRelations;
  }


  public int likenessTo(Person o) {

      if (o == null) {
	  return 0;
      }

      int r = 0;
      int primaryMatchWeight = 60;
      int secondaryMatchWeight = 35;
      int tertiaryMatchWeight = 25;


      // email comparison
      if ((email != null) && (email.equals(o.getEmail()))) {
	  r += primaryMatchWeight;
      } 
      

      // name/birthday comparison
      if ((RDStringUtils.stringsEqual(firstName, o.getFirstName())) &&
	  (RDStringUtils.stringsEqual(lastName, o.getLastName())) &&
	  (birthday != null) && (birthday.getTime() == o.getBirthday().getTime())) {
	  r += primaryMatchWeight;
      } else if ((RDStringUtils.stringsFuzzyEqual(firstName, o.getFirstName())) &&
		 (RDStringUtils.stringsFuzzyEqual(lastName, o.getLastName())) &&
		 (birthday != null) && (birthday.getTime() == o.getBirthday().getTime())) {
	  r += secondaryMatchWeight;
      }
		     

      // name/locale comparison
      if ((RDStringUtils.stringsEqual(firstName, o.getFirstName())) &&
	  (RDStringUtils.stringsEqual(lastName, o.getLastName())) &&
	  (zip != 0) && (o.getZip() != 0) && (zip == o.getZip())) {
	  r += primaryMatchWeight;
      } else if ((RDStringUtils.stringsEqual(firstName, o.getFirstName())) &&
		 (RDStringUtils.stringsEqual(lastName, o.getLastName())) &&
		 (zip != 0) && (o.getZip() != 0) && (zip == o.getZip())) {
          r += secondaryMatchWeight;
      }


      // name/locale comparison 2
      if ((RDStringUtils.stringsEqual(firstName, o.getFirstName())) &&
	  (RDStringUtils.stringsEqual(lastName, o.getLastName())) &&
	  (RDStringUtils.stringsEqual(city, o.getCity())) &&
	  (region != null) && (region.equals(o.getRegion())) &&
	  (country != null) && (country.equals(o.getCountry()))) {
	  r += secondaryMatchWeight;
      } else if ((RDStringUtils.stringsFuzzyEqual(firstName, o.getFirstName())) &&
	  (RDStringUtils.stringsFuzzyEqual(lastName, o.getLastName())) &&
	  (RDStringUtils.stringsFuzzyEqual(city, o.getCity())) &&
	  (region != null) && (region.equals(o.getRegion())) &&
	  (country != null) && (country.equals(o.getCountry()))) {
	  r += tertiaryMatchWeight;
      }


      // name/phone number matches
      if ((RDStringUtils.stringsFuzzyEqual(firstName, o.getFirstName())) &&
	  (RDStringUtils.phoneNumbersEqual(homePhone, o.getHomePhone()))) {
	  r += secondaryMatchWeight;
      }

      if ((RDStringUtils.stringsFuzzyEqual(firstName, o.getFirstName())) &&
	  (RDStringUtils.phoneNumbersEqual(cellPhone, o.getCellPhone()))) {
	  r += secondaryMatchWeight;
      }

      if ((RDStringUtils.stringsFuzzyEqual(firstName, o.getFirstName())) &&
	  (RDStringUtils.phoneNumbersEqual(workPhone, o.getWorkPhone()))) {
	  r += secondaryMatchWeight;
      }


      // name/emergency contact info match
      if ((RDStringUtils.stringsFuzzyEqual(firstName, o.getFirstName())) &&
	  (RDStringUtils.stringsFuzzyEqual(defaultEmergencyContactFirstName, o.getDefaultEmergencyContactFirstName())) &&
	  (RDStringUtils.stringsFuzzyEqual(defaultEmergencyContactLastName, o.getDefaultEmergencyContactLastName())) &&
	  (defaultEmergencyContactRelation != null) && (defaultEmergencyContactRelation.equals(o.getDefaultEmergencyContactRelation())) &&
	  (RDStringUtils.phoneNumbersEqual(defaultEmergencyPhone, o.getDefaultEmergencyPhone()))) {
	  r += secondaryMatchWeight;
      }


      // name/body shape match
      if ((RDStringUtils.stringsEqual(firstName, o.getFirstName())) &&
	  (defaultShirtSize != null) && (defaultShirtSize.equals(o.getDefaultShirtSize())) &&
	  (defaultShoeSize != null) && (defaultShoeSize.equals(o.getDefaultShoeSize()))) {
	  r += tertiaryMatchWeight;
      }


      // ceiling r
      if (r > 100) {
	  r = 100;
      }

      return r;
  }


  public String toString() {

      String r = new String();
      r += "Person [";
      r += "id=" + getId() + ",";
      r += "identityId=" + identityId + ",";
      r += "email=" + email + ",";
      r += "firstName=" + firstName + ",";
      r += "lastName=" + lastName + ",";
      r += "city=" + city + ",";
      r += "region=" + region + ",";
      r += "country=" + country + ",";
      r += "zip=" + zip + ",";
      r += "gender=" + gender + ",";
      r += "birthday=" + birthday + ",";
      r += "homePhone=" + homePhone + ",";
      r += "cellPhone=" + cellPhone + ",";
      r += "workPhone=" + workPhone + ",";
      r += "defaultEmergencyContactFirstName=" + defaultEmergencyContactFirstName + ",";
      r += "defaultEmergencyContactLastName=" + defaultEmergencyContactLastName + ",";
      r += "defaultEmergencyContactRelation=" + defaultEmergencyContactRelation + ",";
      r += "defaultEmergencyPhone=" + defaultEmergencyPhone + ",";
      r += "defaultShirtSize=" + defaultShirtSize + ",";
      r += "defaultShoeSize=" + defaultShoeSize + ",";
      r += "occupation=" + occupation + ",";
      r += "selfDescription=" + selfDescription + ",";
      r += "hobbiesInterests=" + hobbiesInterests + ",";
      r += "favoriteQuote=" + favoriteQuote + ",";
      r += "favoriteThings=" + favoriteThings + ",";
      r += "website=" + website + ",";
      r += "authorities=" + authorities + ",";
      r += "isDisabled=" + isDisabled + ",";
      r += "isDeleted=" + isDeleted + "]";

      return r;
  }


}