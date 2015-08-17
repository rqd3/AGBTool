package com.server.backend;

import java.io.IOException;
import java.util.List;

import com.google.gson.JsonElement;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;

/**
 * Layer between middlelayer(agb comparison) and API(PHP SLIM Framework)
 * @author rqd3-u
 *
 */
public class APIController {
	
	JSONController jsonController;

	public APIController() {
		jsonController = new JSONController();

		//testing
		//getAllAGBVersionsOfSource(4);
		System.out.println(getLatestAGBVersion(49).getText());
		//getLatestAGBVersion(2).getText();
		//getAllAGBSources();
	}
	
	/**
	 * Get all agb sources
	 * @return List<AGBSource> agbSources
	 */
	public List<AGBSource> getAllAGBSources() {
		String sUrl = "http://localhost/agbApi/api1.0/agbsources/"; 
		
		List<AGBSource> agbSources =null;
		try {
			JsonElement jsonElement = jsonController.getJsonElementFromUrl(sUrl);
			agbSources = jsonController.jsonToAGBSources(jsonElement);
			
			System.out.println(jsonController.jsonToAGBSources(jsonController.getJsonElementFromUrl(sUrl)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agbSources;
	}
	
	/**
	 * Get all agb sources
	 * @return List<AGBSource> agbSources
	 */
	public AGBVersion getLatestAGBVersion(int sourceId) {
		String sUrl = "http://localhost/agbApi/api1.0/latestagbversion/"+sourceId; 
		
		AGBVersion latestAgbVersion =null;
		try {
			JsonElement jsonElement = jsonController.getJsonElementFromUrl(sUrl);
			latestAgbVersion = jsonController.jsonToAGBVersion(jsonElement);
			
			//System.out.println(jsonController.jsonToAGBVersion(jsonController.getJsonElementFromUrl(sUrl)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return latestAgbVersion;
	}
	
	/**
	 * Get all versions of one source
	 * @param sourceName
	 * @return
	 */
	public List<AGBVersion> getAllAGBVersionsOfSource(int sourceId){
		String sUrl = "http://localhost/agbApi/api1.0/agbversions/"+sourceId;
		
		List<AGBVersion> agbVersions =null;
		try {
			JsonElement jsonElement = jsonController.getJsonElementFromUrl(sUrl);
			agbVersions = jsonController.jsonToAGBVersions(jsonElement);
			
			System.out.println(jsonController.jsonToAGBVersions(jsonController.getJsonElementFromUrl(sUrl)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agbVersions;
		
		
	}


}
