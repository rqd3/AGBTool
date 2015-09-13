package com.server.db;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shared.dbModels.AGBSource;
import com.shared.dbModels.AGBVersion;

public class DBDriver {
	Connection connection = null;

	/**
	 * Connect with db via jdbc
	 */
	public DBDriver() {

		try {
			// register driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		System.out.println("MySQL JDBC Driver Registered!");

		try {
			// get connection to db - username, pw
			// //db574069647.db.1and1.com:3306/db574069647 "dbo574069647",
			// "X4$2?3Mb"
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/agb_tool_db", "root", "");

			getAllAGBSources();
			getAllAGBVersionsOfSource("ebay");
			setAGBAdvice("https://www.youtube.com/watch?v=CGCMl-K09vg");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get all agb sources from db. Transform them into AGBSourceModels and add
	 * them to a list.
	 * 
	 * @return List<AGBSource> allAGBSources
	 * @throws SQLException
	 */
	public List<AGBSource> getAllAGBSources() throws SQLException {
		List<AGBSource> agbSources = new ArrayList<>();

		Statement myStatement = connection.createStatement();

		ResultSet result = myStatement.executeQuery("SELECT * FROM agb_source");

		while (result.next()) {
			int agbSourceId = result.getInt("agb_source_id");
			String name = result.getString("name");
			String link = result.getString("link");

			AGBSource agbSource = new AGBSource(agbSourceId, name, link);
			System.out.println(agbSource.toString());
			agbSources.add(agbSource);
		}

		return agbSources;
	}

	/**
	 * Get all agb versions of one agb source by name. Transform them into AGBVersionModels and add
	 * them to a list.
	 * @param String sourceName 
	 * @return List<AGBVersion> allAGBVersions
	 * @throws SQLException
	 */
	public List<AGBVersion> getAllAGBVersionsOfSource(String sourceName) throws SQLException {
		List<AGBVersion> agbVersions = new ArrayList<>();

		Statement myStatement = connection.createStatement();

		ResultSet result = myStatement.executeQuery("SELECT agb_version_id, agb_version.agb_source_id, text, version, published_at FROM agb_version LEFT JOIN agb_source ON agb_source.agb_source_id = agb_version.agb_source_id  WHERE agb_source.name LIKE '"+sourceName+"'");

		while (result.next()) {
			int agbVersionId = result.getInt("agb_version_id");
			int agbSourceId = result.getInt("agb_source_id");
			String text = result.getString("text");
			int version = result.getInt("version");
			Date publishedAt = result.getDate("published_at");
			
			AGBVersion agbVersion = new AGBVersion(agbVersionId, agbSourceId, text, version, publishedAt);
			System.out.println(agbVersion.toString());
			agbVersions.add(agbVersion);
		}

		return agbVersions;
	}
	
	/**
	 * Saves an agb advice to table agb_advice, if url is reachable return true
	 * @param String link to agb
	 * @return Boolean 
	 */
	public boolean setAGBAdvice(String link) throws SQLException {
		
		if(isUrlReachable(link)){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String dateFormated = dateFormat.format(date);
		
		Statement myStatement = connection.createStatement();

		myStatement.executeUpdate("INSERT INTO agb_tool_db.agb_advice  VALUES (NULL, '" + link + "', '"+ dateFormated + "', 'unchecked')");
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if string is a reachable url
	 * @param String url
	 * @return true = valid
	 */
	public boolean isUrlReachable(String url){
		boolean validState = false;
		
		url = url.replaceFirst("^https", "http");

	    try {
	        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	        connection.setConnectTimeout(7000);
	        connection.setReadTimeout(7000);
	        connection.setRequestMethod("HEAD");
	        int responseCode = connection.getResponseCode();
	        
	        if (200 <= responseCode && responseCode <= 399){
	        	validState = true;
	        }
	    } catch (IOException exception) {
	        return validState;
	    }
		
		return validState;
	}

}
