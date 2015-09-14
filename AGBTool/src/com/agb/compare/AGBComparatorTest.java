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
		
		// Test TopTenAGB und AllAGBSources
		c1.readTopTenAGB();
		System.out.println(" ");
		c1.readAllAGBSources();
		
		
		// AGB-Vergleich
		System.out.println(" ");
		c1.compareAGBVersions(c1.getAGBVersion1(), c1.getAGBVersion2());
		System.out.println(" ");
		c1.writeAGBDifferences(c1.getAGBVersion1(), c1.getAGBVersion2());
		
		
	} //endmethod main
	

} //endclass
