package com.myrunning.leaderboard.view;

/**
 *  File: RDBMarshallingView.java
 *  Author:  Joshua Forester
 *  Date:  12/20/09
 *  Description: Extension of Spring MarshallingView.
 **/


import java.util.Map;
import javax.servlet.ServletException;
import org.springframework.web.servlet.view.xml.MarshallingView;


public class RDBMarshallingView extends MarshallingView {

  public static final String MARSHALL_OBJ_KEY = "xmlMarshallingKey";


  protected Object locateToBeMarshalled(Map model) throws ServletException {
      
      if (model.containsKey(MARSHALL_OBJ_KEY)) {
	  setModelKey((String) model.get(MARSHALL_OBJ_KEY));
      }

      return super.locateToBeMarshalled(model);
  }

}