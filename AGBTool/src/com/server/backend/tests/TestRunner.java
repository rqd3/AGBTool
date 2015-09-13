package com.server.backend.tests;

import com.server.backend.DBDriver;

public class TestRunner {

	public static void main(String[] args) {
	      /*Result result = JUnitCore.runClasses(DBDriverTest.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());*/
		DBDriver dbDriver = new DBDriver();
		dbDriver.countAGBSourceCalls(38);
	}
}
