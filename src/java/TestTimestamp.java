/**
 *  File: TestTimestamp.java
 *  Author: Joshua Forester
 *  Date: 2009/09/22
 *  Description: This program tests a java.sql.Timestamp to ensure proper value.
 **/


import java.sql.Timestamp;


public class TestTimestamp {

    public static final void main(String args[]) {

	Timestamp ts1 = new Timestamp(0);
	System.out.println(ts1);
	System.out.println(ts1.getTime());
	System.out.println();
	System.out.println("If you don't see 1970-01-01 00:00:00.0, then you need to \"export JAVA_OPTS=-Duser.timezone=GMT\" and relaunch with java $JAVA_OPTS.");
	
    }
}