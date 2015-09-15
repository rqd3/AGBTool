package com.agb.compare;

import java.util.*;
import java.io.*;
import java.text.*;

import com.server.backend.APIController;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;


public class AGBComparatorTest 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		System.out.println("Starte AGB-Version-Comparator-Test: ");
		System.out.println(" ");
		
		AGBComparator c1 = new AGBComparator();
		
	    /*
		// Teste Version1
		c1.readAGBVersion1(45, 1);
		
		c1.getAGBVersion1();
		
		c1.showAGBVersion1();
		
		c1.writeAGBVersion1();
		
		// Teste Version2
		c1.readAGBVersion2(40, 1);
		
		c1.getAGBVersion2();
		
		c1.showAGBVersion2();
		
		c1.writeAGBVersion2();
		System.out.println(" ");
		*/
		
		
		// Test TopTenAGB und AllAGBSources
		c1.readTopTenAGB();
		System.out.println(" ");
		c1.readAllAGBSources();
		
		/*
		// AGB-Vergleich
		System.out.println(" ");
		c1.compareAGBVersions(c1.getAGBVersion1(), c1.getAGBVersion2());
		System.out.println(" ");
		c1.writeAGBDifferences(c1.getAGBVersion1(), c1.getAGBVersion2());
		*/
		
		// Test AllAGBVersionsOfSource
		System.out.println(" ");
		System.out.println("All AGBVersionsOfSource: ");
		c1.readAllAGBVersionsOfSource(45, 1, 3);
		
		
		System.out.println(" ");
        //System.out.println(c1.getSelectedVersion1());
        //System.out.println(c1.getSelectedVersion2());
		
		c1.writeSelectedVersionsOfSource(c1.getSelectedVersion1(), c1.getSelectedVersion2());
		System.out.println(" ");
		
		c1.compareSelectedVersions(c1.getSelectedVersion1(), c1.getSelectedVersion2());
		System.out.println(" ");
		//System.out.println(c1.getVersionDifferences());
		//c1.writeDifferences(c1.getSelectedVersion1(), c1.getSelectedVersion2());
		
		c1.readAGBVersionsWithLineNumbers();
		System.out.println(" ");
		c1.showAGBVersion1WithLineNumbers();
		System.out.println(" ");
		c1.showAGBVersion2WithLineNumbers();
		System.out.println(" ");
		
		// GetAGBVErsion1WithLineNumbers funktioniert
		System.out.println("GetAGBVErsion1WithLineNumbers: ");
		List <String> agb1 = new ArrayList<String>();
		agb1 = c1.getAGBVersion1WithLineNumbers();
		
		Iterator iter = agb1.iterator();
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
		
		// GetVersionDifferences funkioniert
		System.out.println("GetVersionDifferences");
		List <String> agbDiff = new ArrayList<String>();
		agbDiff = c1.getVersionDifferences();
		
		Iterator i = agbDiff.iterator();
		while (i.hasNext())
		{
			System.out.println(i.next());
		}
		
		// Teste compare mit Zeilenangabe
		//c1.compareSelectedVersions(c1.getAGBVersion1WithLineNumbers(), c1.getAGBVersion2WithLineNumbers());
		//c1.writeDifferences(c1.getAGBVersion1WithLineNumbers(), c1.getAGBVersion2WithLineNumbers());
		
		c1.showReadableDifferences();
		System.out.println(" ");
		c1.writeReadableDiffereces();
		
				
	} //endmethod main
	

} //endclass
