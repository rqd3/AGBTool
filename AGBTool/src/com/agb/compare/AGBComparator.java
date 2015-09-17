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
	
	private List <String> topTenAGBs = new ArrayList<String>();
	private List <String> allAGBSources = new ArrayList<String>();
	
	private List<String> agbversion1 = new ArrayList<String>();
	private List<String> agbversion2 = new ArrayList<String>();
	
	private String [] allAGBVersions = null;
	private String [] allAGBVersionsCorrectOrder = null;
	
	private List<String> versionDifferences = new ArrayList<String>();
	private List<String> readableDifferences = new ArrayList<String>();
	
	private List<String> version1WithLineNumbers = new ArrayList<String>();
	private List<String> version2WithLineNumbers = new ArrayList<String>();
	
	// Methoden
	
	@Override
	public void readTopTenAGB() {
		// TODO Auto-generated method stub
		
		APIController apic = new APIController();
		
		String s = apic.getTopTenAGBSources().toString();
		
		/*
		System.out.println("TopTenAGB mit getMethode: ");
		List <AGBSource> topTenAgb = new ArrayList<AGBSource>();
		for (int i = 0; i < topTenAgb.size(); i++)
		{
			String versionText = topTenAgb.get(i).getName();
			System.out.println(versionText);
		}*/
		
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
	
	


	@Override
	public void readAllAGBVersionsOfSource(int sourceId, int version1, int version2) {
		// TODO Auto-generated method stub
		
		APIController apic = new APIController();
		String sources = apic.getAllAGBVersionsOfSource(sourceId).toString();
		
		String agbtext1 = null;
		String agbtext2 = null;
		
		// Version in allAGBVersions[0] ist die neuste Version, z.B. Version 3
		// allAGBVersions[1], Version 2
		// allAGBVersions[2], Version 1
		allAGBVersions = sources.split("],");
		System.out.println("Array-Laenge: " + allAGBVersions.length);
		int counter = allAGBVersions.length;
		//System.out.println("Counter-Laenge: " + counter);
		
		allAGBVersionsCorrectOrder = new String[counter+1];
		System.out.println("Array-Laenge: " + allAGBVersionsCorrectOrder.length);
		int counter2 = counter;
		
		for (int i = 0; i < allAGBVersions.length; i++)
		{
			allAGBVersionsCorrectOrder[counter2] = allAGBVersions[i];
			counter2--;
		}
		
		
		/*
		for (int i = counter; i < 0 ; i--)
		{
			allAGBVersionsCorrectOrder[c] = allAGBVersions[i];
			c++;
			
		}*/
		
		System.out.print("Korrigierte Arraylaenge: " + allAGBVersionsCorrectOrder.length);
		
		for (int i = 1; i < allAGBVersionsCorrectOrder.length; i++)
		{
			if (i == version1)
			{
				agbtext1 = allAGBVersionsCorrectOrder[i];
				System.out.println("AGB1-Version gesetzt: ");
			}
			
			if (i == version2)
			{
				agbtext2 = allAGBVersionsCorrectOrder[i];
				System.out.println("AGB2-Version gesetzt: ");
			}
			else
			{
				System.out.println("Suche nach AGB-Version: ");
			}
		} //endfor
		
		
		
		
		//System.out.println("AGB-Version1: "+ agbtext1);
		//System.out.println("AGB-Version2: "+ agbtext2);
		
		// Speichere Version1 mit BreakIterator in ArrayListe
		System.out.println("Verarbeite AGB-Version1 mit BreakIterator: ");

		Locale currentLocale1 = new Locale("de", "DE");
		
		BreakIterator sentenceIterator1 = BreakIterator.getSentenceInstance(currentLocale1);
		sentenceIterator1.setText(agbtext1);

		for (int last = sentenceIterator1.first(), next = sentenceIterator1.next();
		        next != BreakIterator.DONE;
		        last = next, next = sentenceIterator1.next())
		  {
		    CharSequence part = agbtext1.subSequence( last, next );
		    
		    if (Character.isLetterOrDigit(part.charAt(0)))
		    {
		    	System.out.println(part);
		    	agbversion1.add((String)part);
		    } // endif
		      
		  } //endfor
		
		
		// Speichere Version2 mit BreakIterator in ArrayListe
		System.out.println(" ");
		System.out.println("Verarbeite AGB-Version2 mit BreakIterator: ");
		System.out.println(" ");

		Locale currentLocale2 = new Locale("de", "DE");
		
		BreakIterator sentenceIterator2 = BreakIterator.getSentenceInstance(currentLocale2);
		sentenceIterator2.setText(agbtext2);

		for (int last = sentenceIterator2.first(), next = sentenceIterator2.next();
		        next != BreakIterator.DONE;
		        last = next, next = sentenceIterator2.next())
		  {
		    CharSequence part = agbtext2.subSequence( last, next );
		    
		    if (Character.isLetterOrDigit(part.charAt(0)))
		    {
		    	System.out.println(part);
		    	agbversion2.add((String)part);
		    } // endif
		      
		  } //endfor
		
		
	} //endmethod
	
	
	@Override
	public List<String> getSelectedVersion1() {
		// TODO Auto-generated method stub
		
		return agbversion1;
	}


	@Override
	public List<String> getSelectedVersion2() {
		// TODO Auto-generated method stub
		
		return agbversion2;
		
	}


	@Override
	public void writeSelectedVersionsOfSource(List<String> agbversion1, List<String> agbversion2) {
		// TODO Auto-generated method stub
		
		System.out.println("Schreibe AGBVersion1 in Text-Datei: ");
		
		PrintWriter printWriter1 = null;
		int lineNumber1 = 1;
		
		try 
		{
            printWriter1 = new PrintWriter(new FileWriter("AGB1-SelectedVersion.txt"));
           
            //Iterator iter = agb1list.iterator();
            Iterator iter1 = agbversion1.iterator();
            
            while(iter1.hasNext() ) 
            {
                Object o = iter1.next();
                printWriter1.println(lineNumber1 + ": " + o + LINE_SEPARATOR);
                lineNumber1++;
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		finally 
		{
            if(printWriter1 != null) printWriter1.close();
        } 
		
		
		System.out.println("Schreibe AGBVersion2 in Text-Datei: ");
		
		PrintWriter printWriter2 = null; 
		int lineNumber2 = 1;
		
		try 
		{
            printWriter2 = new PrintWriter(new FileWriter("AGB2-SelectedVersion.txt"));
           
            //Iterator iter = agb1list.iterator();
            Iterator iter2 = agbversion2.iterator();
            
            while(iter2.hasNext() ) 
            {
                Object o = iter2.next();
                printWriter2.println(lineNumber2 + ": " + o + LINE_SEPARATOR);
                lineNumber2++;
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		finally 
		{
            if(printWriter2 != null) printWriter2.close();
        } 
		
		
		
	} //endmethod


	@Override
	public void compareSelectedVersions(List<String> agbversion1, List<String> agbversion2) {
		// TODO Auto-generated method stub
		
		System.out.println("Starte AGB-Vergleich: ");
		
		System.out.println("Achtung: Folgende Aenderung haben sich ergeben! ");
		
		Patch patch1 = DiffUtils.diff(agbversion1, agbversion2);
		
		 for (Delta delta: patch1.getDeltas()) 
		 {
           System.out.println(delta + LINE_SEPARATOR);
           versionDifferences.add(delta.toString());
        }
		
	}


	@Override
	public List<String> getVersionDifferences() {
		// TODO Auto-generated method stub
		
		
		return versionDifferences;
	}



	@Override
	public void readAGBVersionsWithLineNumbers() {
		// TODO Auto-generated method stub
		
		String lineVersion1 = null;
		String lineVersion2 = null;
		
		try 
		{
            BufferedReader br1 = new BufferedReader(new FileReader("AGB1-SelectedVersion.txt"));
            
            while ((lineVersion1 = br1.readLine()) != null) 
            {
            	version1WithLineNumbers.add(lineVersion1);
                //System.out.println(line);
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
		
		try 
		{
            BufferedReader br2 = new BufferedReader(new FileReader("AGB2-SelectedVersion.txt"));
            
            while ((lineVersion2 = br2.readLine()) != null) 
            {
            	version2WithLineNumbers.add(lineVersion2);
                //System.out.println(line);
            }
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
		
		
	}


	@Override
	public void showAGBVersion1WithLineNumbers() {
		// TODO Auto-generated method stub
		
		System.out.println("Zeige AGBVersion1 mit Zeilennummern an: ");
		Iterator iter = version1WithLineNumbers.iterator();
		
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
	}


	@Override
	public void showAGBVersion2WithLineNumbers() {
		// TODO Auto-generated method stub
		
		System.out.println("Zeige AGBVersion2 mit Zeilennummern an: ");
		Iterator iter = version2WithLineNumbers.iterator();
		
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
	}


	@Override
	public List<String> getAGBVersion1WithLineNumbers() {
		// TODO Auto-generated method stub
		
		return version1WithLineNumbers;
	}


	@Override
	public List<String> getAGBVersion2WithLineNumbers() {
		// TODO Auto-generated method stub
		
		return version2WithLineNumbers;
	}


	@Override
	public void showReadableDifferences() {
		// TODO Auto-generated method stub
		
		String s = versionDifferences.toString();
		String [] array = s.split("]],");
		//String [] array = s.split("to");
		
		for (int i=0; i < array.length; i++)
		{
			readableDifferences.add(array[i]);
			System.out.println(array[i]);
		}
		
		
		
		
		
	}


	@Override
	public void writeReadableDiffereces() {
		// TODO Auto-generated method stub
		
		PrintWriter printWriter = null; 
		
		String filename = "AGB-Versions-Aenderungen2.txt";
		
		System.out.println("Schreibe Textdatei mit Aenderungen: ");
		
		try 
		{
			try 
			{
				printWriter = new PrintWriter(new FileWriter(filename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Iterator iter = readableDifferences.iterator();
			
			while (iter.hasNext())
			{
				 printWriter.println(iter.next()+LINE_SEPARATOR);
			}
        } 
		finally 
		{
            if(printWriter != null) printWriter.close();
        } 
	}


	@Override
	public List<String> getAGBDifferences() {
		// TODO Auto-generated method stub
		
		return readableDifferences;
	}


	@Override
	public List<String> getAGBDifferencesForDemo(int sourceId, int version1, int version2) {
		// TODO Auto-generated method stub
		
		// Ablauf:
		// readAllAGBVersionsOfSource(42, 1, 2); notwendig
		// writeSelectedVersionsOfSource(c1.getSelectedVersion1(), c1.getSelectedVersion2()); notwendig fuer Zeilennummereiung
		// compareSelectedVersions(c1.getSelectedVersion1(), c1.getSelectedVersion2()); notwendig
		// readAGBVersionsWithLineNumbers(); 
		// showAGBVersion1WithLineNumbers(); Anzeige
		// showAGBVersion2WithLineNumbers(); Anzeige
		// showReadableDifferences();
		// writeReadableDiffereces();
		
		readAllAGBVersionsOfSource(sourceId, version1, version2);
		writeSelectedVersionsOfSource(getSelectedVersion1(), getSelectedVersion2());
		compareSelectedVersions(getSelectedVersion1(), getSelectedVersion2());
		showReadableDifferences();
		writeReadableDiffereces();
		
		
		
		return readableDifferences;
	}


} //endclass
