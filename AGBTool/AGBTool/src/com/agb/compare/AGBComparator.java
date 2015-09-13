package com.agb.compare;

import java.util.*;
import java.io.*;
import java.text.*;

import com.server.backend.APIController;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;


public class AGBComparator implements AGBCompare 
{
	// Attribute
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private APIController apic_agb1 = new APIController();
	private APIController apic_agb2 = new APIController();
	
	private List<String> agb1list = new ArrayList<String>();
	private List<String> agb2list = new ArrayList<String>();
	
	private List <String> breakIteratorArray1 = new ArrayList<String>();
	private List <String> breakIteratorArray2 = new ArrayList<String>();
	
	private List <String> deltaList = new ArrayList<String>();
	
	private List <String> topTenAGBs = new ArrayList<String>();
	private List <String> allAGBSources = new ArrayList<String>();
	
	
	// Methoden

	@Override
	public void readAGBVersion1(int sourceId1, int version1) {
		// TODO Auto-generated method stub
		
		System.out.println("Lese AGBVersion1: ");
		
		String s = apic_agb1.getLatestAGBVersion(sourceId1).getText();
		
		Locale currentLocale = new Locale("de", "DE");
		
		BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
		sentenceIterator.setText(s);

		for (int last = sentenceIterator.first(), next = sentenceIterator.next();
		        next != BreakIterator.DONE;
		        last = next, next = sentenceIterator.next())
		  {
		    CharSequence part = s.subSequence( last, next );
		    
		    if (Character.isLetterOrDigit(part.charAt(0)))
		    {
		    	//System.out.println(part);
			    breakIteratorArray1.add((String)part);
		    } // endif
		      
		  } //endfor
		
		System.out.println("Lesevorgang erfolgreich! ");
		

	} //endmethod readAGBVersion1
	

	@Override
	public List<String> getAGBVersion1() {
		// TODO Auto-generated method stub
		
		//System.out.println("Gib AGBVersion1 zurueck: ");
		
		return breakIteratorArray1;
	}
	

	@Override
	public void showAGBVersion1() {
		// TODO Auto-generated method stub
		
		System.out.println("Zeige AGBVersion1 an: ");
		
		Iterator iter = breakIteratorArray1.iterator();
		
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}

	}
	

	@Override
	public void writeAGBVersion1() {
		// TODO Auto-generated method stub
		
		System.out.println("Schreibe AGBVersion1 in Text-Datei: ");
		
		PrintWriter printWriter = null; 
		
		try 
		{
            printWriter = new PrintWriter(new FileWriter("AGB1-LatestVersion-Test.txt"));
           
            //Iterator iter = agb1list.iterator();
            Iterator iter = breakIteratorArray1.iterator();
            
            while(iter.hasNext() ) 
            {
                Object o = iter.next();
                printWriter.println(o);
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		finally 
		{
            if(printWriter != null) printWriter.close();
        } 
		

	} //endmethod
	

	@Override
	public void readAGBVersion2(int sourceId2, int version2) {
		// TODO Auto-generated method stub
		
		System.out.println("Lese AGBVersion2: ");
		
		String s = apic_agb2.getLatestAGBVersion(sourceId2).getText();
		
		Locale currentLocale = new Locale("de", "DE");
		
		BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
		sentenceIterator.setText(s);

		for (int last = sentenceIterator.first(), next = sentenceIterator.next();
		        next != BreakIterator.DONE;
		        last = next, next = sentenceIterator.next())
		  {
		    CharSequence part = s.subSequence( last, next );
		    
		    if (Character.isLetterOrDigit(part.charAt(0)))
		    {
		    	//System.out.println(part);
			    breakIteratorArray2.add((String)part);
		    } // endif
		      
		  } //endfor
		
		System.out.println("Lesevorgang erfolgreich! ");
		

	}
	

	@Override
	public List<String> getAGBVersion2() {
		// TODO Auto-generated method stub
		
		return breakIteratorArray2;
	}

	
	@Override
	public void showAGBVersion2() {
		// TODO Auto-generated method stub
		
		System.out.println("Zeige AGBVersion2 an: ");
		
		Iterator iter = breakIteratorArray2.iterator();
		
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}

	}

	
	@Override
	public void writeAGBVersion2() {
		// TODO Auto-generated method stub
		
		System.out.println("Schreibe AGBVersion2 in Text-Datei: ");
		
		PrintWriter printWriter = null; 
		
		try 
		{
            printWriter = new PrintWriter(new FileWriter("AGB2-LatestVersion-Test.txt"));
           
            //Iterator iter = agb1list.iterator();
            Iterator iter = breakIteratorArray2.iterator();
            
            while(iter.hasNext() ) 
            {
                Object o = iter.next();
                printWriter.println(o);
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		finally 
		{
            if(printWriter != null) printWriter.close();
        } 

	}
	
	
	@Override
	public boolean twoAGBVersionsExist(int sourceId1, int version1, int sourceId2, int version2) {
		// TODO Auto-generated method stub
		
		if ((sourceId1 == sourceId2) && (version1 != version2) && (version2 != 0))
		{
			return true;
		}
		else
		{
			System.out.println("Es existiert keine zweite AGB-Version zum Vergleich.");
			return false;
		}
		
	}


	@Override
	public void compareAGBVersions(List<String> breakIteratorArray1, List<String> breakIteratorArray2) {
		// TODO Auto-generated method stub
		
		System.out.println("Starte AGB-Vergleich: ");
		System.out.println("Achtung: Folgende Aenderung haben sich ergeben! ");
		
		List<Delta> list = new ArrayList<Delta>();
		
		Patch patch1 = DiffUtils.diff(breakIteratorArray1, breakIteratorArray2);
		
		 for (Delta delta: patch1.getDeltas()) 
		 {
            //System.out.println(delta + LINE_SEPARATOR);
            list.add(delta);
         }
		 
		String s = list.toString();
		//System.out.println("String-Delta: " + delta);
		
		Locale currentLocale = new Locale("de", "DE");
		
		BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
		sentenceIterator.setText(s);

		for (int last = sentenceIterator.first(), next = sentenceIterator.next();
		        next != BreakIterator.DONE;
		        last = next, next = sentenceIterator.next())
		  {
		    CharSequence part = s.subSequence( last, next );
		    
		    if (Character.isLetterOrDigit(part.charAt(0)))
		    {
		    	System.out.println(part);
			    deltaList.add((String)part);
		    } // endif
		      
		  } //endfor
		 
		 System.out.println("Gibt Delta-List-Array aus: ");
		 Iterator iter = deltaList.iterator();
		 
		 while (iter.hasNext())
		 {
			 System.out.println(iter.next());
		 }
		
		
	} //endmethod

	
	@Override
	public void writeAGBDifferences(List<String> breakIteratorArray1, List<String> breakIteratorArray2) {
		// TODO Auto-generated method stub
		
		PrintWriter printWriter = null; 
		
		String filename = "AGB - Aenderungen.txt";
		
		System.out.println("Schreibe Textdatei mit Aenderungen: ");
		
		try 
		{
			try {
				printWriter = new PrintWriter(new FileWriter(filename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			Patch patch1 = DiffUtils.diff(breakIteratorArray1, breakIteratorArray2);
			
			 for (Delta delta: patch1.getDeltas()) 
			 {
				 printWriter.println(delta+LINE_SEPARATOR);
				
	    }	 
        } 
		finally 
		{
            if(printWriter != null) printWriter.close();
        } 
		
		
	} //endmethod

	
	@Override
	public void readTopTenAGB() {
		// TODO Auto-generated method stub
		
		APIController apic = new APIController();
		
		String s = apic.getTopTenAGBSources().toString();
		
		String [] array = s.split("],");
		
		System.out.println("Top Ten AGBs: ");
		System.out.println(" ");
		
		for (int i = 0; i<array.length; i++)
		{
			topTenAGBs.add(array[i]);
			//System.out.println(array[i]);
		}
		
		Iterator iter = topTenAGBs.iterator();
		
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}

	}

	
	@Override
	public List<String> getTopTenAGB() {
		// TODO Auto-generated method stub
		
		return topTenAGBs;
	}

	
	@Override
	public void readAllAGBSources() {
		// TODO Auto-generated method stub
		
		APIController apic = new APIController();
		
		String s = apic.getAllAGBSources().toString();
		
		String [] sarray = s.split("],");
		
		System.out.println("Alle AGBs der Datenbank: ");
		System.out.println(" ");
		
		for (int i=0; i<sarray.length; i++)
		{
			allAGBSources.add(sarray[i]);
			//System.out.println(sarray[i]);
		}
		
		
		Iterator iter = allAGBSources.iterator();
		
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}

	}

	
	@Override
	public List<String> getAllAGBSources() {
		// TODO Auto-generated method stub
		
		return allAGBSources;
	}


	


	

}
