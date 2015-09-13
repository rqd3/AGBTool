package com.server.backend.tests;



import static org.junit.Assert.*;

import org.junit.Test;

import com.server.backend.DBDriver;

public class DBDriverTest {

		DBDriver dbDriverTester = new DBDriver();
		
		@Test
		public void countAGBSourceCallsTest(){
			//will fail because counter(2) is changing
			assertEquals(3, dbDriverTester.getCounterFromAGBFavorite(39));
		}
		

}
