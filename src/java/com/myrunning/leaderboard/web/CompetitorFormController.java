package com.myrunning.leaderboard.web;

/**
 *  File: CompetitorFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/21
 *  Description: Controller object for the competitor app.
 **/


import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.BindException;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.global.LocaleUtils;
import com.myrunning.leaderboard.model.Person;
import com.myrunning.leaderboard.model.propertyeditors.CustomTimestampEditor;
import com.myrunning.leaderboard.validation.PersonVerifier;



public class CompetitorFormController extends SimpleFormController {

    static Logger logger = Logger.getLogger(CompetitorFormController.class);

    @Autowired
    private PersonVerifier personVerifier;


    protected void initBinder(HttpServletRequest request,
			      ServletRequestDataBinder binder)
	throws Exception {
	SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
	CustomTimestampEditor tsEditor = new CustomTimestampEditor(dateFormat, true, 10);
	binder.registerCustomEditor(Timestamp.class, null, tsEditor);
    }


    public CompetitorFormController() {
	// empty
    }


    protected Map referenceData (HttpServletRequest request)
	throws Exception {
	
	Map data = new HashMap();
	data.put("countries", LocaleUtils.getCountries());
	data.put("regions", LocaleUtils.getRegions());
	data.put("shirtSizes", Person.getShirtSizes());
	data.put("emergencyContactRelations", Person.getEmergencyContactRelations());

	return data;
    }



    protected void onBindAndValidate(HttpServletRequest req, 
				     Object command, 
				     BindException errors) {
	personVerifier.verify(req, command, errors);
    }
	    

}
