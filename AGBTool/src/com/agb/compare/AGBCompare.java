package com.agb.compare;

import java.util.*;
import java.text.*;

import com.server.backend.APIController;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;

import difflib.Delta;


public interface AGBCompare 
{
	
	/* Stores TopTenAGBs form database in an ArrayList print them out on console
	 * @param: 
	 * @return: 
	 */
	public void readTopTenAGB();
	
	/* Returns TopTenAGBs as ArrayList
	 * @param: 
	 * @return: topTenAGBs
	 */
	public List<String> getTopTenAGB();
	
	/* Stores allAGBSources form database in an ArrayList print them out on console
	 * @param: 
	 * @return: 
	 */
	public void readAllAGBSources();
	
	/* Return allAGBSources
	 * @param: 
	 * @return: allAGBSources
	 */
	public List<String> getAllAGBSources();
	
	
	/* Stores two different AGB-Versions in separated ArrayList to make them ready to compare
	 * @param: 
	 * @return: 
	 */
	public void readAllAGBVersionsOfSource(int sourceId, int version1, int version2);
	
	/* Returns ArrayList of Version1
	 * @param: 
	 * @return: agbversion1
	 */
	public List<String> getSelectedVersion1();
	
	/* Returns ArrayList of Version2
	 * @param: 
	 * @return: agbversion2
	 */
	public List<String> getSelectedVersion2();
	
	
	/* Writes AGB-Version1 and AGB-Version2 in two separate textfiles
	 * @param: agbversion1, agbversion2
	 * @return: 
	 */
	public void writeSelectedVersionsOfSource(List<String> agbversion1, List<String> agbversion2);
	
	
	/* Add LineNumbers to selected AGB-Versions
	 * @param: 
	 * @return: 
	 */
	public void readAGBVersionsWithLineNumbers();
	
	/* Shows AGB-Version1 with LineNumbers on console
	 * @param: 
	 * @return: 
	 */
	public void showAGBVersion1WithLineNumbers();
	
	/* Shows AGB-Version2 with LineNumbers on console
	 * @param: 
	 * @return: 
	 */
	public void showAGBVersion2WithLineNumbers();
	
	/* Returns ArrayList version1WithLineNumbers
	 * @param: 
	 * @return: version1WithLineNumbers
	 */
	public List<String> getAGBVersion1WithLineNumbers();
	
	
	/* Returns ArrayList version2WithLineNumbers
	 * @param: 
	 * @return: version2WithLineNumbers
	 */
	public List<String> getAGBVersion2WithLineNumbers();
	
	
	/* Compares selected AGB-Versions via Diff Tool and shows the differences on console
	 * @param: agbversion1, agbversion2
	 * @return: 
	 */
	public void compareSelectedVersions(List<String> agbversion1, List<String> agbversion2);
	
	/* Returns ArrayList with differences between AGB-Versions
	 * @param: 
	 * @return: versionDifferences
	 */
	public List<String> getVersionDifferences();
	
	/* Shows readable version of AGB-Differences
	 * @param: 
	 * @return: 
	 */
	public void showReadableDifferences();
	
	
	/* Writes readable version of AGB-Differences in textfile
	 * @param: 
	 * @return: 
	 */
	public void writeReadableDiffereces();
	
	
	/* Returns readable version of AGB-Differences
	 * @param: 
	 * @return: readableDifferences
	 */
	public List<String> getAGBDifferences();
	
	
	/* Returns readable version of AGB-Differences for Demonstration
	 * @param: 
	 * @return: readableDifferences
	 */
	public List<String> getAGBDifferencesForDemo(int sourceId, int version1, int version2);
	
	

}
