package com.server.backend;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;

/**
 * Direct db access via jdbc
 * 
 * @author rqd3-u
 *
 */
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
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/agb_tool_db", "root", "");

			// testing

			// setAGBAdvice("https://www.youtube.com/watch?v=CGCMl-K09vg");
			

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
	 * @deprecated
	 */
	public List<AGBSource> getAllAGBSources() throws SQLException {
		List<AGBSource> agbSources = new ArrayList<>();

		Statement myStatement = connection.createStatement();

		ResultSet result = myStatement.executeQuery("SELECT * FROM agb_source");

		while (result.next()) {
			int agbSourceId = result.getInt("agb_source_id");
			String name = result.getString("name");
			String link = result.getString("link");
			String xPath = result.getString("xPath");

			AGBSource agbSource = new AGBSource(agbSourceId, name, link, xPath);
			System.out.println(agbSource.toString());
			agbSources.add(agbSource);
		}

		return agbSources;
	}

	/**
	 * @deprecated Get all agb versions of one agb source by name. Transform them
	 *             into AGBVersionModels and add them to a list.
	 * @param String
	 *            sourceName
	 * @return List<AGBVersion> allAGBVersions
	 * @throws SQLException
	 */
	public List<AGBVersion> getAllAGBVersionsOfSource(String sourceName)
			throws SQLException {
		List<AGBVersion> agbVersions = new ArrayList<>();

		Statement myStatement = connection.createStatement();

		ResultSet result = myStatement
				.executeQuery("SELECT agb_version_id, agb_version.agb_source_id, text, version, published_at FROM agb_version LEFT JOIN agb_source ON agb_source.agb_source_id = agb_version.agb_source_id  WHERE agb_source.name LIKE '"
						+ sourceName + "'");

		while (result.next()) {
			AGBVersion agbVersion = dbResultToAGBVersion(result);
			System.out.println(agbVersion.toString());
			agbVersions.add(agbVersion);
		}

		return agbVersions;
	}

	/**
	 * @deprecated
	 * @param agbSourceId
	 * @return
	 * @throws SQLException
	 */
	public AGBVersion getLatestVersionOfDB(int agbSourceId) throws SQLException {
		Statement myStatement = connection.createStatement();

		// Select row with max published_at value
		ResultSet result = myStatement
				.executeQuery("SELECT * FROM `agb_version` WHERE published_at = (SELECT MAX(published_at) FROM agb_version WHERE `agb_source_id` like "
						+ agbSourceId
						+ ") AND `agb_source_id` like "
						+ agbSourceId + "");

		AGBVersion agbVersion = dbResultToAGBVersion(result);

		return agbVersion;
	}

	/**
	 * @deprecated
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	public AGBVersion dbResultToAGBVersion(ResultSet result)
			throws SQLException {
		int agbVersionId = result.getInt("agb_version_id");
		int agbSourceId = result.getInt("agb_source_id");
		String text = result.getString("text");
		int version = result.getInt("version");
		Date publishedAt = result.getDate("published_at");

		AGBVersion agbVersion = new AGBVersion(agbVersionId, agbSourceId, text,
				version, publishedAt);

		return agbVersion;
	}

	/**
	 * Saves an agb advice to table agb_advice, if url is reachable return true
	 * 
	 * @param String
	 *            link to agb
	 * @return Boolean
	 */
	public boolean setAGBAdvice(String link) {

		if (isUrlReachable(link)) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			String dateFormated = dateFormat.format(date);

			String sql = "INSERT INTO agb_tool_db.agb_advice  VALUES (NULL, ?, ?, 'unchecked')";

			PreparedStatement preparedStatement;
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, link);
				preparedStatement.setString(2, dateFormated);
				
				preparedStatement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			return false;
		}
		return true;
	}

	/**
	 * Checks if string is a reachable url
	 * 
	 * @param String
	 *            url
	 * @return true = valid
	 */
	public boolean isUrlReachable(String url) {
		boolean validState = false;

		url = url.replaceFirst("^https", "http");

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setConnectTimeout(7000);
			connection.setReadTimeout(7000);
			connection.setRequestMethod("HEAD");
			int responseCode = connection.getResponseCode();

			if (200 <= responseCode && responseCode <= 399) {
				validState = true;
			}
		} catch (IOException exception) {
			return validState;
		}

		return validState;
	}
	
	/**
	 * counter for agb favorite. Everytime a agbversion is used count +1 an write to db
	 * 
	 * @param agbSourceId
	 */
	public void countAGBSourceCalls(int agbSourceId){
		String sql = "UPDATE  agb_tool_db.agb_favorite SET  `counter` = ? WHERE `agb_source_id` like ?";

		PreparedStatement preparedStatement;
		try {
			int counter = getCounterFromAGBFavorite(agbSourceId)+1;
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, counter);
			preparedStatement.setInt(2, agbSourceId);			
			
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the value of the column 'counter' for a specific agbSource
	 * 
	 * @param agbSourceId
	 */
	public int getCounterFromAGBFavorite(int agbSourceId){
		int counter = 0;
		String sql = "SELECT `counter` FROM `agb_favorite` WHERE `agb_source_id` like ?";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, agbSourceId);
			preparedStatement.execute();
			
			ResultSet resultSet = preparedStatement.getResultSet();
			if(resultSet.next()){
				counter = Integer.valueOf(resultSet.getString("counter"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

}
