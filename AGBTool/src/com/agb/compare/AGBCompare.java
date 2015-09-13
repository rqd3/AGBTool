package com.agb.compare;

import java.util.*;
import java.text.*;

import com.server.backend.APIController;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;


public interface AGBCompare 
{
	
	/* Stores AGB-Version1-text form database in ArrayList
	 * @param: sourceId1, version1
	 * @return: 
	 */
	public void readAGBVersion1(int sourceId1, int version1);
	
	
	/* Returns ArrayList with AGB1-Text
	 * @param: 
	 * @return: breakIteratorArray1
	 */
	public List<String> getAGBVersion1();
	
	
	/* Shows text from AGB-Version1 on console
	 * @param: 
	 * @return: 
	 */
	public void showAGBVersion1();
	
	
	/* Writes text from AGB-Version1 as textfile
	 * @param: 
	 * @return: 
	 */
	public void writeAGBVersion1();
	
	
	
	/* Stores AGB-Version2-text form database in ArrayList
	 * @param: sourceId2, version2
	 * @return: 
	 */
	public void readAGBVersion2(int sourceId2, int version2);
	
	/* Returns ArrayList with AGB2-Text
	 * @param: 
	 * @return: breakIteratorArray2
	 */
	public List<String> getAGBVersion2();
	
	/* Shows text from AGB-Version2 on console
	 * @param: 
	 * @return: 
	 */
	public void showAGBVersion2();
	
	/* Writes text from AGB-Version2 as textfile
	 * @param: 
	 * @return: 
	 */
	public void writeAGBVersion2();
	
	
	
	/* Checks if two different AGB-Versions exist
	 * @param: sourceId1, version1, sourceId2, version2, 
	 * @return: true, if two versions exist / else false
	 */
	public boolean twoAGBVersionsExist(int sourceId1, int version1, int sourceId2, int version2);
	
	/* Compares two AGB-Versions and shows differences on console
	 * @param: breakIteratorArray1 ,breakIteratorArray2
	 * @return: 
	 */
	public void compareAGBVersions(List<String> breakIteratorArray1, List<String> breakIteratorArray2);
	
	/* Compares two AGB-Versions and writes differences in textfile
	 * @param: breakIteratorArray1, breakIteratorArray2
	 * @return: 
	 */
	public void writeAGBDifferences(List<String> breakIteratorArray1, List<String> breakIteratorArray2);
	
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
	
	

}
