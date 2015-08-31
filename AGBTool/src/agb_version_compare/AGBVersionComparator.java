package agb_version_compare;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;


public class AGBVersionComparator implements VersionComparatorInterface 
{
	// Attributliste
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
		
	private String agb1Name;
	private String agb2Name;
		
	private File agb1file;
	private File agb2file;
	private String agb1FileName;
	private String agb2FileName;
		
	private String sourcePath = "/Studium/Wirtschaftsinformatik/7. Semester (SS 2015)/Projektarbeit/AGB Source/";
	private String destinationPath = "/Studium/Wirtschaftsinformatik/7. Semester (SS 2015)/Projektarbeit/AGB Destination/";
		
	private String anbieter1;
	private String season1;
	private String published_at1;
	//private DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		
	private String anbieter2;
	private String season2;
	private String published_at2;
	//private DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		
	private AGBVersionComparator agb1 = null;
	private AGBVersionComparator agb2 = null;
	private int documentLength = 0;
	private int counter = 1;
		
	File versionOhneLeerzeilen = null;
	String agbFileName_ohneLeerzeilen = null;
		
	File versionOhneLeerzeilen1 = null;
	String agbFileName_ohneLeerzeilen1 = null;
	File versionOhneLeerzeilen2 = null;
	String agbFileName_ohneLeerzeilen2 = null;
		
	File versionMitZeilenNummer = null;
	String agbFileName_mitZeilenNummer = null;
		
	File versionMitZeilenNummer1 = null;
	String agbFileName_mitZeilenNummer1 = null;
	File versionMitZeilenNummer2 = null;
	String agbFileName_mitZeilenNummer2 = null;
		
	private List<String> linesArray1 = null;
	private List<String> linesArray2 = null;
	
	
	// leerer Konstruktur
	public AGBVersionComparator()
	{
			
	}
	
	
	// ueberladener Konsturktur
	public AGBVersionComparator(String anbieter, String season, String published_at)
	{
		if (counter % 2 == 0)
		{
			agb2 = new AGBVersionComparator(anbieter, season, published_at);
			counter++;
		}
		else
		{
			agb1 = new AGBVersionComparator(anbieter, season, published_at);
			counter++;
		}
			
	}
	
	
    // Methoden
	@Override
	public void setAGB1(String anbieter1, String season1, String published_at1) {
		// TODO Auto-generated method stub
		
		if (anbieter1.equals(null))
			System.out.println("Bitte Anbieter auswaehlen!");
		else
			this.anbieter1 = anbieter1;
		
		if (season1.equals(null))
			System.out.println("Bitte Saison auswaehlen!");
		else
			this.season1 = season1;
		
		if (published_at1.equals(null))
			System.out.println("Bitte Datum auswaehlen!");
		else
			this.published_at1 = published_at1;
		
		/*
		// Datum in String umwandeln
		String date1 = df1.format(published_at1);
		System.out.println(date1);
		*/
		
		// AGB-Name zusammensetzen (Datum fehlt noch!)
		agb1Name = "AGB " + anbieter1 + " - " + season1 + " " + published_at1;
		System.out.println(agb1Name);

	} //endmethod
	

	@Override
	public void setAGB2(String anbieter2, String season2, String published_at2) {
		// TODO Auto-generated method stub
		
		if (anbieter2.equals(null))
			System.out.println("Bitte Anbieter auswaehlen!");
		else
			this.anbieter2 = anbieter2;
		
		if (season2.equals(null))
			System.out.println("Bitte Saison auswaehlen!");
		else
			this.season2 = season2;
		
		if (published_at2.equals(null))
			System.out.println("Bitte Datum auswaehlen!");
		else
			this.published_at2 = published_at2;
		/*
		// Datum in String umwandeln, Format: 2015-08-09
		String date2 = df2.format(published_at2);
		System.out.println(date2);
		*/
		
		// AGB-Name zusammensetzen (Datum fehlt noch!)
		agb2Name = "AGB " + anbieter2 + " - " + season2 + " " + published_at2;
		System.out.println(agb2Name);

	} //endmethod
	

	@Override
	public String getAGB1() {
		// TODO Auto-generated method stub
		
		return agb1Name;
	} //endmethod
	

	@Override
	public AGBVersionComparator getVersion1() {
		// TODO Auto-generated method stub
		
		return agb1;
	} //endmethod
	

	@Override
	public AGBVersionComparator getVersion2() {
		// TODO Auto-generated method stub
		
		return agb2;
	} //endmethod
	

	@Override
	public String getAGB2() {
		// TODO Auto-generated method stub
		
		return agb2Name;
	} //endmethod
	

	@Override
	public void setAnbieterName1(String anbieter1) {
		// TODO Auto-generated method stub
		
		this.anbieter1 = anbieter1;

	} //endmethod
	

	@Override
	public void setSeason1(String season1) {
		// TODO Auto-generated method stub
		
		this.season1 = season1;

	} //endmethod
	

	@Override
	public void setPublishDate1(String published_at1) {
		// TODO Auto-generated method stub
		
		this.published_at1 = published_at1;

	} //endmethod
	

	@Override
	public String getAnbieterName1() {
		// TODO Auto-generated method stub
		
		return anbieter1;
	} //endmethod
	

	@Override
	public String getSeason1() {
		// TODO Auto-generated method stub
		
		return season1;
	} //endmethod
	

	@Override
	public String getPublishDate1() {
		// TODO Auto-generated method stub
		
		return published_at1;
	} //endmethod
	

	@Override
	public void setAnbieterName2(String anbieter2) {
		// TODO Auto-generated method stub
		
		this.anbieter2 = anbieter2;

	} //endmethod
	

	@Override
	public void setSeason2(String season2) {
		// TODO Auto-generated method stub
		
		this.season2 = season2;

	} //endmethod
	

	@Override
	public void setPublishDate2(String published_at2) {
		// TODO Auto-generated method stub
		
		this.published_at2 = published_at2;

	} //endmethod
	

	@Override
	public String getAnbieterName2() {
		// TODO Auto-generated method stub
		
		return anbieter2;
	} //endmethod
	

	@Override
	public String getSeason2() {
		// TODO Auto-generated method stub
		
		return season2;
	} //endmethod
	

	@Override
	public String getPublishDate2() {
		// TODO Auto-generated method stub
		
		return published_at2;
	} //endmethod
	

	@Override
	public String getSourcePath() {
		// TODO Auto-generated method stub
		
		return sourcePath;
	} //endmethod
	

	@Override
	public String getDestinationPath() {
		// TODO Auto-generated method stub
		
		return destinationPath;
	} //endmethod
	

	@Override
	public boolean twoVersionsExits() {
		// TODO Auto-generated method stub
		
		// fuege Season hinzu, es sollen nur AGBs der gleichen Season vergleichbar sein, z.B. Winter vs. Winter
		if ((anbieter1 == anbieter2) && (published_at1 != published_at2))
				return true;
				
			if (anbieter1 != anbieter2)
			{
				System.out.println("Um AGB-Aenderungen des Anbieters festzustellen, muessen die beiden gewaehlten Anbieter gleich sein.");
				System.out.println("Bitte waehlen Sie einen geeigneten Anbieter aus.");
				return false;
			}
		
		return false;
	} //endmethod
	

	@Override
	public void setPathFileName() {
		// TODO Auto-generated method stub
		
		// liest beide AGB-Files ein (Pfad + Name (agb1Name/agb2Name))
		agb1file = new File(sourcePath + agb1Name + ".txt");
		agb1FileName = sourcePath + agb1Name + ".txt";
		System.out.println(agb1file);
		agb2file = new File(sourcePath + agb2Name + ".txt");
		agb2FileName = sourcePath + agb2Name + ".txt";
		System.out.println(agb2file);

	} //endmethod
	

	@Override
	public String getPathFileNameAGB1() {
		// TODO Auto-generated method stub
		
		return agb1FileName;
	} //endmethod
	

	@Override
	public String getPathFileNameAGB2() {
		// TODO Auto-generated method stub
		
		return agb2FileName;
	} //endmethod
	

	@Override
	public int documentLength(String agbFileName) {
		// TODO Auto-generated method stub
		
		FileReader fr = null;
		BufferedReader br = null;
		
		int documentLength = 0;
		
		FileWriter fw = null;
		
		String line = null;
		
		boolean eof = false;
		
		// oeffne AGB-Version
		try {
			fr = new FileReader(agbFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		br = new BufferedReader(fr);
		
		System.out.print("Zaehle Zeilen des AGB-Dokuments: ");
		
		try
		{
			while ((line = br.readLine()) != null)
			{
				documentLength++;
			}
		} catch (Exception ex)
		{
			
		}
	
		return documentLength;
		
	} //endmethod
	

	@Override
	public void deleteEmptyLines(List<String> linesArray1, List<String> linesArray2) {
		// TODO Auto-generated method stub
		
		// fileArray1 lesen, fileArray2 lesen
		FileWriter fileWriter1 = null;
		
		String line1 = null;
		
		agbFileName_ohneLeerzeilen1 = getDestinationPath() + "AGB " + getAnbieterName1() + " - " + getSeason1() + " " + getPublishDate1() + " ohne Leerzeilen.txt";
		
		System.out.println("Entferne Leerzeilen aus AGB1-Version: ");
		
		try 
		{
            fileWriter1 = new FileWriter(agbFileName_ohneLeerzeilen1);
           
            Iterator iter1 = linesArray1.iterator();
            
            while(iter1.hasNext() ) 
            {
            	line1 = (String) iter1.next();
            	
            	if (!line1.equals(""))
                {
            		fileWriter1.write(line1 + LINE_SEPARATOR);
                }
            	else
            	{
            		System.out.println("Leerzeile aus AGB1-Version entfernt!");
            	}
            	
                
                
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		finally 
		{
            if(fileWriter1 != null)
				try {
					fileWriter1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        } 
		
		// FileArray2
		FileWriter fileWriter2 = null;
		
		String line2 = null;
		
		agbFileName_ohneLeerzeilen2 = getDestinationPath() + "AGB " + getAnbieterName2() + " - " + getSeason2() + " " + getPublishDate2() + " ohne Leerzeilen.txt";
		
		System.out.println("Entferne Leerzeilen aus AGB2-Version: ");
		
		try 
		{
            fileWriter2 = new FileWriter(agbFileName_ohneLeerzeilen2);
           
            Iterator iter2 = linesArray2.iterator();
            
            while(iter2.hasNext() ) 
            {
            	line2 = (String) iter2.next();
            	
            	if (!line2.equals(""))
                {
            		fileWriter2.write(line2 + LINE_SEPARATOR);
                }
            	else
            	{
            		System.out.println("Leerzeile aus AGB2-Version entfernt!");
            	}
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		finally 
		{
            if(fileWriter2 != null)
				try {
					fileWriter2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        } 

	} //endmethod
	

	@Override
	public String getVersion1WithoutEmptyLines() {
		// TODO Auto-generated method stub
		
		return agbFileName_ohneLeerzeilen1;
	} //endmethod
	

	@Override
	public String getVersion2WithoutEmptyLines() {
		// TODO Auto-generated method stub
		
		return agbFileName_ohneLeerzeilen2;
	} //endmethod
	

	@Override
	public void LineNumberWriter(List<String> linesArray1, List<String> linesArray2) {
		// TODO Auto-generated method stub
		
		// fileArray1 lesen, fileArray2 lesen
		FileWriter fileWriter1 = null;
		int lineNumber1 = 1;
		String line1 = null;
		
		agbFileName_mitZeilenNummer1 = getDestinationPath() + "AGB " + getAnbieterName1() + " - " + getSeason1() + " " + getPublishDate1() + " mit ZeilenNummern.txt";
		
		System.out.println("Schreibe AGB1 mit Zeilennummer: ");
		
		try 
		{
            fileWriter1 = new FileWriter(agbFileName_mitZeilenNummer1);
           
            Iterator iter1 = linesArray1.iterator();
            
            while(iter1.hasNext() ) 
            {
                line1 = (String) iter1.next();
                fileWriter1.write(lineNumber1 + ". " + line1 + LINE_SEPARATOR);
                lineNumber1++;
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		finally 
		{
            if(fileWriter1 != null)
				try {
					fileWriter1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        } 
		
		// FileArray2
		FileWriter fileWriter2 = null;
		int lineNumber2 = 1;
		String line2 = null;
		
		agbFileName_mitZeilenNummer2 = getDestinationPath() + "AGB " + getAnbieterName2() + " - " + getSeason2() + " " + getPublishDate2() + " mit ZeilenNummern.txt";
		
		System.out.println("Schreibe AGB2 mit Zeilennummer: ");
		
		try 
		{
            fileWriter2 = new FileWriter(agbFileName_mitZeilenNummer2);
           
            Iterator iter2 = linesArray2.iterator();
            
            while(iter2.hasNext() ) 
            {
                line2 = (String) iter2.next();
                fileWriter2.write(lineNumber2 + ". " + line2 + LINE_SEPARATOR);
                lineNumber2++;
            }
        } 
		catch (IOException e) {
            e.printStackTrace();
        } 
		finally 
		{
            if(fileWriter2 != null)
				try {
					fileWriter2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        } 
		

	} //endmethod

	@Override
	public String getVersion1WithLineNumber() {
		// TODO Auto-generated method stub
		
		return agbFileName_mitZeilenNummer1;
	} //endmethod

	@Override
	public String getVersion2WithLineNumber() {
		// TODO Auto-generated method stub
		
		return agbFileName_mitZeilenNummer2;
	} //endmethod
	

	/*/@Override
	public void compareAGBVersions(List<String> linesArray1, List<String> linesArray2) {
		// TODO Auto-generated method stub
		
		
		

	} //endmethod
	*/

	@Override
	public void readAGB1File(String agbFileName1) {
		// TODO Auto-generated method stub
		
		linesArray1 = new LinkedList<String>();
        String line = "";
        try {
                BufferedReader in = new BufferedReader(new FileReader(agbFileName1));
                while ((line = in.readLine()) != null) 
                {
                        linesArray1.add(line);
                        //System.out.println(line);
                }
        } catch (IOException e) {
                e.printStackTrace();
        }

	} //endmethod
	

	@Override
	public List<String> getAGB1FileArray() {
		// TODO Auto-generated method stub
		
		return linesArray1;
	} //endmethod
	

	@Override
	public void showAGB1FileArray(List<String> linesArray1) {
		// TODO Auto-generated method stub
		
		System.out.println("Inhalt des AGB-Files: ");
		
		for (String line : linesArray1)
		{
			System.out.println(line);
		}

	} //endmethod
	

	@Override
	public void writeAGB1FileArray(List<String> linesArray1) {
		// TODO Auto-generated method stub
		
		PrintWriter printWriter = null; 
		
		String agb1FileName = getDestinationPath() + "AGB " + getAnbieterName1() + " - " + getSeason1() + " " + getPublishDate1() + " ArrayList1.txt";
		
		System.out.println("Schreibe Textdatei: ");
		
		try 
		{
            printWriter = new PrintWriter(new FileWriter(agb1FileName));
           
            Iterator iter = linesArray1.iterator();
            
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
	public void readAGB2File(String agbFileName2) {
		// TODO Auto-generated method stub
		
		linesArray2 = new LinkedList<String>();
        String line = "";
        try {
                BufferedReader in = new BufferedReader(new FileReader(agbFileName2));
                while ((line = in.readLine()) != null) 
                {
                        linesArray2.add(line);
                        //System.out.println(line);
                }
        } catch (IOException e) {
                e.printStackTrace();
        }

	} //endmethod
	

	@Override
	public List<String> getAGB2FileArray() {
		// TODO Auto-generated method stub
		
		return linesArray2;
	} //endmethod
	

	@Override
	public void showAGB2FileArray(List<String> linesArray2) {
		// TODO Auto-generated method stub
		
		System.out.println("Inhalt des AGB-Files: ");
		
		for (String line : linesArray2)
		{
			System.out.println(line);
		}
		

	} //endmethod
	

	@Override
	public void writeAGB2FileArray(List<String> linesArray2) {
		// TODO Auto-generated method stub
		
		PrintWriter printWriter = null; 
		
		String agb2FileName = getDestinationPath() + "AGB " + getAnbieterName2() + " - " + getSeason2() + " " + getPublishDate2() + " ArrayList2.txt";
		
		System.out.println("Schreibe Textdatei: ");
		
		try 
		{
            printWriter = new PrintWriter(new FileWriter(agb2FileName));
           
            Iterator iter = linesArray2.iterator();
            
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
	public void compareArrayLists(List<String> linesArray1, List<String> linesArray2) {
		// TODO Auto-generated method stub
		
		System.out.println("Starte AGB-Vergleich: ");
		System.out.println("Achtung: Folgende Aenderung haben sich ergeben! ");
		
		Patch patch1 = DiffUtils.diff(linesArray1, linesArray2);
		
		 for (Delta delta: patch1.getDeltas()) 
		 {
            System.out.println(delta);
         }
		

	} //endmethod
	

	@Override
	public void writeAGBDifferences(List<String> linesArray1, List<String> linesArray2) {
		// TODO Auto-generated method stub
		
		PrintWriter printWriter = null; 
		
		String agb2FileName = getDestinationPath() + "AGB " + getAnbieterName2() + " - Aenderungen.txt";
		
		System.out.println("Schreibe Textdatei mit Aenderungen: ");
		
		try 
		{
			try {
				printWriter = new PrintWriter(new FileWriter(agb2FileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Patch patch1 = DiffUtils.diff(linesArray1, linesArray2);
			
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
	

	
} //endclass
