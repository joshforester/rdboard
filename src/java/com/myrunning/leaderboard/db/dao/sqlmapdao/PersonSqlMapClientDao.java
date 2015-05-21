package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: PersonSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/18
 *  Description: SqlMapDao for Person.
 **/


import java.util.List;
import java.util.ListIterator;
import java.util.Collections;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.model.Authority;
import com.myrunning.leaderboard.model.util.PersonLastNameComparator;
import com.myrunning.leaderboard.model.util.PersonFirstNameComparator;
import com.myrunning.leaderboard.model.util.PersonLocaleComparator;
import com.myrunning.leaderboard.model.util.PersonBirthdayComparator;
import com.myrunning.leaderboard.model.util.PersonShirtSizeComparator;
import com.myrunning.leaderboard.db.dao.ifacedao.PersonDao;
import com.myrunning.leaderboard.db.dao.ifacedao.AuthorityDao;


public class PersonSqlMapClientDao extends LastInsertIdSqlMapClientDao implements PersonDao {

    static Logger logger = Logger.getLogger(PersonSqlMapClientDao.class);

    private static AuthorityDao authorityDao;

    public void setAuthorityDao(AuthorityDao authorityDao) {
	this.authorityDao=authorityDao;
    }


    private void sortPersons(List<Person> persons, String order) {
	if (order != null) {
	    if (order.equals("lastname")) {
		Collections.sort(persons, new PersonLastNameComparator());
	    } else if (order.equals("firstname")) {
		Collections.sort(persons, new PersonFirstNameComparator());
	    } else if (order.equals("locale")) {
		Collections.sort(persons, new PersonLocaleComparator());
	    } else if (order.equals("birthday")) {
		Collections.sort(persons, new PersonBirthdayComparator());
	    } else if (order.equals("shirtsize")) {
		Collections.sort(persons, new PersonShirtSizeComparator());
	    } else {
		Collections.sort(persons, new PersonLastNameComparator());
	    }
	} else {
	    Collections.sort(persons, new PersonLastNameComparator());
	}
    }


    public PersonSqlMapClientDao() {
	// empty
    }

    public Person getPersonById(String id) {
	return decorate((Person) getSqlMapClientTemplate().queryForObject("getPersonById", id));
    }

    public Person getPersonById(long id) {
	return getPersonById(String.valueOf(id));
    }

    public List getPersonsByEmail(String email, String order, boolean decorate) {
	List<Person> persons = getSqlMapClientTemplate().queryForList("getPersonsByEmail", email);
	if (decorate) {
	    persons = decorate(persons);
	}
	sortPersons(persons, order);
	return persons;
    }

    public List getPersonsByTraits(Person person, String order, boolean decorate) {
	List<Person> persons = getSqlMapClientTemplate().queryForList("getPersonsByNameAndBirthday", person);

	if (persons.isEmpty()) {
	    persons = getSqlMapClientTemplate().queryForList("getPersonsByNameAndLocale", person);
	}

	if (decorate) {
	    persons = decorate(persons);
	}
	sortPersons(persons, order);
	
	return persons;
    }

    public List getPersonsByIdentityId(String id, String order, boolean decorate) {
	List<Person> persons = getSqlMapClientTemplate().queryForList("getPersonsByIdentityId", id);
	if (decorate) {
	    persons = decorate(persons);
	} 
	sortPersons(persons, order);
	return persons;
    }

    public List getPersonsByIdentityId(long id, String order, boolean decorate) {
	return getPersonsByIdentityId(String.valueOf(id), order, decorate);
    }

    public List getAll(String order, boolean decorate) {
	List<Person> persons = getSqlMapClientTemplate().queryForList("getAllPersons");
	if (decorate) {
	    persons = decorate(persons);
	} 
	sortPersons(persons, order);
	return persons;
    }

    public void insertPerson(Person person) {
	getSqlMapClientTemplate().update("insertPerson", person);
	Authority authority = new Authority();
	authority.setId(person.getId());
	ListIterator<String> li = ((List<String>) person.getAuthorities()).listIterator();
	while (li.hasNext()) {
	    authority.setAuthority(li.next());
	    authorityDao.insertAuthority(authority);
	}
    }

    public void updatePerson(Person person) {
	getSqlMapClientTemplate().update("updatePerson", person);
	authorityDao.deleteAuthoritiesByPersonId(person.getId());
	Authority authority = new Authority();
	authority.setId(person.getId());
	ListIterator<String> li = ((List<String>) person.getAuthorities()).listIterator();
	while (li.hasNext()) {
	    authority.setAuthority(li.next());
	    authorityDao.insertAuthority(authority);
	}
    }

    public void deletePersonById(String id) {
	getSqlMapClientTemplate().update("deletePersonById", id);
	authorityDao.deleteAuthoritiesByPersonId(id);
    }

    public void deletePersonById(long id) {
	deletePersonById(String.valueOf(id));
    }

    public static List decorate(List list) {

	ListIterator<Person> li = list.listIterator();
	Person person = null;
	while (li.hasNext()) {
	    person = li.next();
	    person = decorate(person);
	}

	return list;
    }

    public static Person decorate(Person person) {

      if (person == null) {
          return null;
      }

      List<Authority> authorities = (List<Authority>) authorityDao.getAuthoritiesByPersonId(person.getId());
      ListIterator<Authority> li = authorities.listIterator();
      Authority authority = null;
      while (li.hasNext()) {
	  authority = li.next();
	  person.getAuthorities().add(authority.getAuthority());
      }

      return person;

    }


}// END OF CLASS PersonSqlMapClientDao
