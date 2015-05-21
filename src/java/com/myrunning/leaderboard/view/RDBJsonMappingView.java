package com.myrunning.leaderboard.view;

/**
 *  File: RDBJsonMappingView.java
 *  Author:  Joshua Forester
 *  Date:  12/20/09
 *  Description: Extension of Spring MappingJacksonJsonView.
 **/


import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import javax.servlet.ServletException;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;


public class RDBJsonMappingView extends MappingJacksonJsonView {

    public static final String JSON_OBJ_KEY = "jsonMappingKey";


    protected Object filterModel(Map<String, Object> model) {
	
	if (model.containsKey(JSON_OBJ_KEY)) {
	    Set<String> allowedSet = new HashSet<String>();
	    allowedSet.add((String) model.get(JSON_OBJ_KEY));
	    setRenderedAttributes(allowedSet);
	}
	
	return super.filterModel(model);
    }
    
}