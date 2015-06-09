package com.server.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	 * 
	 * @return List<AGBVersion> allAGBVersions
	 * @throws SQLException
	 */
	public List<AGBVersion> getAllAGBVersionsOfSource(String sourceName)
			throws SQLException {
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

}
