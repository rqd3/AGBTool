package com.server.backend.tests;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import com.server.backend.DBDriver;

public class DBDriverTest {

		DBDriver dbDriverTester = new DBDriver();
		String link = "https://www.test.de/";
		
		@Test
		public void dbDriverTests(){
			
			//db access
			assertTrue("App can access db", dbDriverTester.connectToDB());
			
			//internet access
			assertTrue("App can access internet", dbDriverTester.isUrlReachable(link));
			
			assertTrue("App can save agb advices", dbDriverTester.setAGBAdvice(link));
			
			//check agb_favorite counter
			assertEquals("Counter must be 3(db dependent)",3, dbDriverTester.getCounterFromAGBFavorite(39));
			
		}
		
		@After 
		public void cleanDB(){
			dbDriverTester.deleteAGBAdvice(link);
		}

}
