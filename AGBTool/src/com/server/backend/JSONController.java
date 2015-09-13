package com.server.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;

/**
 * Handles json data and transforms it into java objects
 * @author rqd3-u
 *
 */
public class JSONController {
	
	public JSONController(){
		
	}
	/**
	 * Gets Json Element
	 * @param url location of json
	 * @return JsonElement root
	 * @throws IOException
	 */
	public JsonElement getJsonElementFromUrl(String url) throws IOException{

	    // Connect to the URL using java's native library
	    URL purl = new URL(url);
	    HttpURLConnection request = (HttpURLConnection) purl.openConnection();
	    request.connect();

	    // Convert to a JSON object to print data
	    JsonParser jp = new JsonParser(); //from gson
	    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getInputStream(),"UTF-8")); //Convert the input stream to a json element
	    System.out.println("root"+root.toString());
	    return root;
	}
		
	/**
	 * Converting JsonElement to AGBVersion
	 * @param jsonElement
	 * @return AGBVersion agbVersion
	 */
	public AGBVersion jsonToAGBVersion(JsonElement jsonElement){
		AGBVersion agbVersion;
		//@TODO missing hh:mm:ss
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		JsonObject rootobj = jsonElement.getAsJsonObject(); //May be an array, may be an object. 
		
	    JsonParser jp = new JsonParser(); //from gson
	    JsonElement root = jp.parse(rootobj.toString()); //Convert the input stream to a json element
	    agbVersion = gson.fromJson(root, AGBVersion.class);
		
		return agbVersion;
	}
	
	/**
	 * Converting JsonObject to List<AGBVersion>
	 * @param json
	 * @return AGBSource AGBSource
	 */
	public List<AGBVersion> jsonToAGBVersions(JsonElement jsonElement){
		//@TODO missing hh:mm:ss
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		JsonArray rootobj = jsonElement.getAsJsonArray(); //May be an array, may be an object. 
		
	    Type collectionType = new TypeToken<List<AGBVersion>>(){}.getType();
	    List<AGBVersion> agbVersions = gson.fromJson(rootobj, collectionType);
		
		return agbVersions;
	}
	
	
	/**
	 * Converting JsonObject to List<AGBSource>
	 * @param jsonElement
	 * @return List<AGBSource> agbSources
	 */
	public List<AGBSource> jsonToAGBSources(JsonElement jsonElement){
		//@TODO missing hh:mm:ss
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	    
		JsonArray rootobj = jsonElement.getAsJsonArray(); //May be an array, may be an object. 
	
	    Type collectionType = new TypeToken<List<AGBSource>>(){}.getType();
	    List<AGBSource> agbSources = gson.fromJson(rootobj, collectionType);
		
		return agbSources;
	}
	

	
	/**
	 * Gets json from url by separating the string from the returned file and converting the string to JsonObject.
	 * @param String sUrl json source
	 * @return String json
	 * @deprecated
	 */
	public String getJSONFromUrl(String sUrl){
		String json =null;

	    try {
	    // Connect to the URL using java's native library
	    URL url = new URL(sUrl);
	    HttpURLConnection request;
		request = (HttpURLConnection) url.openConnection();
	    request.connect();

	    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

	    String readLine;
	    StringBuffer sb = new StringBuffer();

	    while  ((readLine = in.readLine()) != null){
	       sb.append(readLine);
	    }

	    String htmlResponse = sb.toString();
	    
	    if(htmlResponse.contains("{") && htmlResponse.contains("}")){
	    	json =htmlResponse.substring(htmlResponse.indexOf("{"), htmlResponse.indexOf("}")+1);
	    }
	    if(htmlResponse.contains("[") && htmlResponse.contains("]")){
	    	 json =htmlResponse.substring(htmlResponse.indexOf("["), htmlResponse.indexOf("]")+1);
	    }
	    else{
	    	System.out.println("No json data available.");
	    }
	    
	    
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    return json;
	}
}
