package com.myrunning.leaderboard.web;

/**
 *  File: DivisionFormController.java
 *  Author:  Joshua Forester
 *  Date: 2009/08/18
 *  Description: Controller object for the division app.
 **/


import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.global.Constants;
import com.myrunning.leaderboard.model.Admin;
import com.myrunning.leaderboard.model.Event;
import com.myrunning.leaderboard.model.Division;
import com.myrunning.leaderboard.db.DataAccessFilter;
import com.myrunning.leaderboard.db.dao.ifacedao.EventDao;



@Controller
public class DivisionFormController extends NewLayoutController {

    static Logger logger = Logger.getLogger(DivisionFormController.class);

    @Autowired
    private EventDao eventDao;


    public DivisionFormController() {
	// empty
    }


    @ModelAttribute("events")
    public Collection<Event> loadEvents(HttpServletRequest request) {

        List<Event> events = new ArrayList<Event>();

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
        if (admin == null) {
            return events;
        }

        events = eventDao.getAll("name");
        events = (List<Event>) DataAccessFilter.filterColById(events, admin.getId());

        return events;
    }


    @ModelAttribute("consistencies")
    public Collection<String> loadConsistencies(HttpServletRequest request) {
        List<String> consistencies = new ArrayList<String>();

        Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION_KEY);
        if (admin == null) {
            return consistencies;
        }

        return Division.getConsistencies();
    }


}
