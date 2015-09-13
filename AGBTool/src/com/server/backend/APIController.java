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
	DBDriver dbDriver;

	public APIController() {
		jsonController = new JSONController();

		//testing
		//getAllAGBVersionsOfSource(4);
		//System.out.println(getLatestAGBVersion(39).getText());
		//getLatestAGBVersion(2).getText();
		//getAllAGBSources();
	}
	
	/**
	 * Get all agb sources
	 * @return List<AGBSource> agbSources
	 */
	public List<AGBSource> getAllAGBSources() {
		String sUrl = "http://localhost/agbApi/api1.0/agbsources/"; 
		System.out.println("getall");
		List<AGBSource> agbSources =null;
		try {
			System.out.println("vor jsonelemenet");
			JsonElement jsonElement = jsonController.getJsonElementFromUrl(sUrl);
			System.out.println("testjson"+jsonElement);
			agbSources = jsonController.jsonToAGBSources(jsonElement);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agbSources;
	}
	
	/**
	 * Get top ten agb sources
	 * @return List<AGBSource> agbSources
	 */
	public List<AGBSource> getTopTenAGBSources() {
		String sUrl = "http://localhost/agbApi/api1.0/agbsources/topten/"; 
		
		List<AGBSource> agbSources =null;
		try {
			JsonElement jsonElement = jsonController.getJsonElementFromUrl(sUrl);
			System.out.println(jsonElement.toString());
			agbSources = jsonController.jsonToAGBSources(jsonElement);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agbSources;
	}
	
	/**
	 * Get latest agbVersion
	 * Count in agb_favorite +1
	 * @return AGBVersion> latest agbVersion
	 */
	public AGBVersion getLatestAGBVersion(int sourceId) {
		String sUrl = "http://localhost/agbApi/api1.0/agbversions/latest"+sourceId; 
		
		AGBVersion latestAgbVersion =null;
		try {
			JsonElement jsonElement = jsonController.getJsonElementFromUrl(sUrl);
			latestAgbVersion = jsonController.jsonToAGBVersion(jsonElement);
			
			//System.out.println(jsonController.jsonToAGBVersion(jsonController.getJsonElementFromUrl(sUrl)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbDriver.countAGBSourceCalls(sourceId);
		return latestAgbVersion;
	}
	
	/**
	 * Get all versions of one source sorted by date. 0 = newest version and so on..
	 * Count in agb_favorite +1
	 * 
	 * @param sourceName
	 * @return List<AGBVersion> agbVersions
	 */
	public List<AGBVersion> getAllAGBVersionsOfSource(int sourceId){
		String sUrl = "http://localhost/agbApi/api1.0/agbversions/"+sourceId;
		
		List<AGBVersion> agbVersions =null;
		try {
			JsonElement jsonElement = jsonController.getJsonElementFromUrl(sUrl);
			agbVersions = jsonController.jsonToAGBVersions(jsonElement);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbDriver.countAGBSourceCalls(sourceId);
		return agbVersions;
		
		
	}


}
