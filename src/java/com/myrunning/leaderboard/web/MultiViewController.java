package com.myrunning.leaderboard.web;

/**
 *  File: MultiViewController.java
 *  Author:  Joshua Forester
 *  Date: 2009/12/20
 *  Description: Controller that deals with normalizing multiple views.
 **/


import org.springframework.ui.ModelMap;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import com.myrunning.leaderboard.view.RDBMarshallingView;
import com.myrunning.leaderboard.view.RDBJsonMappingView;


public class MultiViewController {

    static Logger logger = Logger.getLogger(NewLayoutController.class);


    /**
     *  Explicitly sets the model object to render, rather than the entire model.
     **/
    protected void setMultiViewRenderObject(ModelMap model, String objectKey) {
	model.addAttribute(RDBMarshallingView.MARSHALL_OBJ_KEY, objectKey);
	model.addAttribute(RDBJsonMappingView.JSON_OBJ_KEY, objectKey);
    }

}
