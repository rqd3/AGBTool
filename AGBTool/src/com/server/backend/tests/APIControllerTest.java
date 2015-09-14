package com.server.backend.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.server.backend.APIController;

public class APIControllerTest {

	@Test
	public void APIControllerTests(){
		APIController apiController = new APIController();
		
		assertNotNull("Must return List<AGBSource>", apiController.getAllAGBSources());
		assertNotNull("Must return List<AGBVersion>", apiController.getAllAGBVersionsOfSource(40));//carefull +1 counter
		assertNotNull("Must return AGBVersion", apiController.getLatestAGBVersion(40));//carefull +1 counter
		assertNotNull("Must return AGBVersion", apiController.getLatestAGBVersions());
		assertNotNull("Must return List<AGBSources>", apiController.getTopTenAGBSources());

	}
}
