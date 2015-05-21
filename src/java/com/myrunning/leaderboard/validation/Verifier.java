package com.myrunning.leaderboard.validation;

/**
 *  File: Verifier.java
 *  Author: Joshua Forester
 *  Date: 2009/10/28
 *  Description: Verifier interface.
 **/


import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;


public interface Verifier {

    public void verify (HttpServletRequest req, Object command, BindException errors);

}
