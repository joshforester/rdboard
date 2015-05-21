package com.myrunning.leaderboard.db.dao.sqlmapdao;

/**
 *  File: AdminSqlMapClientDao.java
 *  Author: Joshua Forester
 *  Date: 2009/08/21
 *  Description: SqlMapDao for Admin.
 **/


import java.util.List;
import java.util.ListIterator;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.util.StringUtils;
import com.myrunning.leaderboard.model.Credentials;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.db.dao.ifacedao.AdminDao;


public class AdminSqlMapClientDao extends PersonSqlMapClientDao implements AdminDao {

    static Logger logger = Logger.getLogger(AdminSqlMapClientDao.class);


    public AdminSqlMapClientDao() {
	// empty
    }

    public Admin getAdminById(String id) {
	return decorate((Admin) getSqlMapClientTemplate().queryForObject("getAdminById", id));
    }

    public Admin getAdminById(long id) {
	return getAdminById(String.valueOf(id));
    }

    public Admin getAdminByEmail(String email) {
	return decorate((Admin) getSqlMapClientTemplate().queryForObject("getAdminByEmail", email));
    }

    public Admin getAdminByUsername(String username) {
	return decorate((Admin) getSqlMapClientTemplate().queryForObject("getAdminByUsername", username));
    }

    public Admin getAdminByUsernameAndPassword(String username, String password) {
	Credentials credentials = new Credentials();
	credentials.setUsername(username);
	credentials.setPassword(password);
	return decorate((Admin) getSqlMapClientTemplate().queryForObject("getAdminByUsernameAndPassword", credentials));
    }

    public Admin getAdminByTraits(Admin admin) {
	if ((admin.getFirstName() == null) ||
	    (! StringUtils.hasText(admin.getFirstName())) ||
	    (admin.getLastName() == null) ||
	    (! StringUtils.hasText(admin.getLastName())) ||
	    (admin.getCity() == null) ||
	    (! StringUtils.hasText(admin.getCity())) ||
	    (admin.getRegion() == null) ||
	    (! StringUtils.hasText(admin.getRegion())) ||
	    (admin.getCountry() == null) ||
	    (! StringUtils.hasText(admin.getCountry())) ||
	    (admin.getZip() == 0) ||
	    (! StringUtils.hasText(String.valueOf(admin.getZip()))) ||
	    (admin.getGender() == null) ||
	    (! StringUtils.hasText(admin.getGender())) ||
	    (admin.getBirthday() == null) ||
	    (! StringUtils.hasText(admin.getBirthday().toString()))) {
	    throw new IllegalArgumentException();
	} else {
	    return decorate((Admin) getSqlMapClientTemplate().queryForObject("getAdminByTraits", admin));
	}
    }

    public List getAll(boolean decorate) {
	if (decorate) {
	    return decorate(getSqlMapClientTemplate().queryForList("getAllAdmins"));
	} else {
	    return getSqlMapClientTemplate().queryForList("getAllAdmins");
	}
    }

    public void insertAdmin(Admin admin) {
	insertPerson(admin);
	getSqlMapClientTemplate().update("insertAdmin", admin);
    }

    public void attachAdmin(Admin admin) {
	getSqlMapClientTemplate().update("insertAdmin", admin);
    }

    public void updateAdmin(Admin admin) {
	this.updatePerson(admin);
	getSqlMapClientTemplate().update("updateAdmin", admin);
    }

    public void updateAttachedAdmin(Admin admin) {
	getSqlMapClientTemplate().update("updateAdmin", admin);
    }

    public void deleteAdminById(String id) {
	// delete only the admin here, not the associated PERSON object also
	getSqlMapClientTemplate().update("deleteAdminById", id);
    }

    public void deleteAdminById(long id) {
	deleteAdminById(String.valueOf(id));
    }


    public static List decorate(List list) {

	ListIterator<Admin> li = list.listIterator();
	Admin admin = null;
	while (li.hasNext()) {
	    admin = li.next();
	    admin = decorate(admin);
	}

	return list;
    }

    public static Admin decorate(Admin admin) {

	if (admin == null) {
	    return admin;
	}

	return (Admin) PersonSqlMapClientDao.decorate(admin);
    }


}// END OF CLASS AdminSqlMapClientDao
