package agb_version_compare;

import java.util.Date;
import java.util.List;

public interface VersionComparatorInterface 
{
	/* Setzt AGB1-Version, User waehlt ueber Drop-Down-Maske aus (Name/Anbieter, Season, Published_At)
	 * Setzt den Namen der AGB1-Version zusammen:
	 * agb1Name = "AGB " + anbieter1 + " - " + season1 + " " + published_at1; 
	 * @param: anbieter, season, published_at
	 * @return  
	 */
	public void setAGB1(String anbieter, String season, String published_at);
	
	
	/* Setzt AGB-Version 2, User waehlt ueber Drop-Down-Maske aus (Name/Anbieter, Season, Published_At)
	 * Setzt den Namen der AGB2-Version zusammen:
	 * agb2Name = "AGB " + anbieter2 + " - " + season2 + " " + published_at2; 
	 * @param: anbieter, season, published_at
	 * @return: 
	 */
	public void setAGB2(String anbieter, String season, String published_at);
	
	
	/* gibt den Dateinamen von AGB1-Version zuruek
	 * @param:
	 * @return: agb1Name
	 */
	public String getAGB1();
	
	
	/* gibt das AGB1-Objekt zurueck
	 * @param:
	 * @return: agb1 (Anbieter, Season und Pulish-Date
	 */
	public AGBVersionComparator getVersion1();
	
	
	/* gibt das AGB2-Objekt zurueck
	 * @param:
	 * @return: agb2 (Anbieter, Season und Pulish-Date
	 */
	public AGBVersionComparator getVersion2();
	
	
	/* gibt den vollen Dateinamen von AGB2-Version aus
	 * @param:
	 * @return: agb2Name
	 */
	public String getAGB2();
	
	
	// Getter- und Setter-Methoden von AGB1-Version 
	/* Setzt den Namen von Anbieter1
	 * @param: anbieter1
	 * @return: 
	 */
	public void setAnbieterName1(String anbieter1);
	
	/* Setzt die Saison von Anbieter1
	 * @param: season1
	 * @return: 
	 */
	public void setSeason1(String season1);
	
	/* Setzt das Publish-Date von Anbieter1
	 * @param: published_at1
	 * @return: 
	 */
	public void setPublishDate1(String published_at1);
	
	/* Gibt den Namen von Anbieter1 zurueck
	 * @param: 
	 * @return: anbieter1
	 */
	public String getAnbieterName1();
	
	/* Gibt die Saison von Anbieter1 zurueck
	 * @param: 
	 * @return: season1
	 */
	public String getSeason1();
	
	/* Gibt das Publish-Date von Anbieter1 zurueck
	 * @param: 
	 * @return: published_at1
	 */
	public String getPublishDate1();
	
	
	// Getter- und Setter-Methoden von AGB1-Version
	/* setzt den Namen von Anbieter2
	 * @param: anbieter2
	 * @return: 
	 */
    public void setAnbieterName2(String anbieter2);
	
    
 	/* setzt die Saison von Anbieter2
 	 * @param: season2
 	 * @return: 
 	 */
	public void setSeason2(String season2);
	
	/* setzt das Publish-Date von Anbieter2
 	 * @param: published_at2
 	 * @return: 
 	 */
	public void setPublishDate2(String published_at2);
	
	/* gibt den Namen von Anbieter2 zurueck
 	 * @param: 
 	 * @return: anbieter2
 	 */
	public String getAnbieterName2();
	
	/* gibt die Saison von Anbieter2 zurueck
 	 * @param: 
 	 * @return: season2
 	 */
	public String getSeason2();
	
	/* gibt das Publish-Date von Anbieter2 zurueck
 	 * @param: 
 	 * @return: published_at2
 	 */
	public String getPublishDate2();
	
	
	/* gibt den String des Verzeichnises zurueck, in dem die Original-AGB-Files liegen
 	 * @param: 
 	 * @return: sourcePath ("/Studium/Wirtschaftsinformatik/7. Semester (SS 2015)/Projektarbeit/AGB Source/")
 	 */
	public String getSourcePath();
	
	/* gibt den String des Verzeichnises zurueck, in dem die veraenderten AGB-Files liegen
 	 * @param: 
 	 * @return: destinationPath ("/Studium/Wirtschaftsinformatik/7. Semester (SS 2015)/Projektarbeit/AGB Destination/")
 	 */
	public String getDestinationPath();
	
	
	/* Prueft, ob zwei Versionen vorhanden sind und ob die Versionen verschieden sind.
	 * Gibt true zurueck, wenn beide AGB-Versionen denselben Anbieter und dieselbe Saison haben 
	 * und das Pulished_at-Daten verschieden ist.
	 * @param:
	 * @return: true/false
	 */
	public boolean twoVersionsExits();
	
	
	/* setzt Pfad und Dateinamen von AGB1- und AGB2-Version zusammen
	 * Voraussetzung:
	 * setAGB1(String anbieter, String season, String published_at) und
	 * setAGB2(String anbieter, String season, String published_at) muss bereits gesetzt worden sein.
	 * Sonst liegt kein gueltiger agb1Name und agb2Name String vor.
	 * @param:
	 * @return: 
	 */
	public void setPathFileName();
	
	
	/* gibt den zusammengesetzten Pfad und Dateinamen von AGB1 zurueck
	 * @param:
	 * @return: agb1FileName
	 */
	public String getPathFileNameAGB1();
	
	
	/* gibt den zusammengesetzten Pfad und Dateinamen von AGB2 zurueck
	 * @param:
	 * @return: agb2FileName
	 */
	public String getPathFileNameAGB2();
	
	/* Ueberprueft die Laenge eines uebergebenen AGB-Dokuments, d.h. die Anzahl der Zeilen wird gezaehlt.
	 * @param: agbFileName (z.B. vc1.documentLength(vc1.getPathFileNameAGB1()), vc1 ist ein AGB-VersionComparator-Objekt)
	 * @return: 
	 */
	public int documentLength(String agbFileName);
	
	
	/* Liest beide AGB-Versionen aus einer ArrayList ein und gibt zwei neue Text-Versionen ohne Leerzeilen aus.
	 * @param: List<String> linesArray1, List<String> linesArray2
	 * @return: 
	 */
	public void deleteEmptyLines(List<String> linesArray1, List<String> linesArray2);
	
	
	/* gibt den Pfad/Namen der korrigierte AGB1-Version, ohne Leerzeilen zurueck
	 * @param:
	 * @return: agbFileName_ohneLeerzeilen1
	 */
	public String getVersion1WithoutEmptyLines();
	
	
	/* gibt den Pfad/Namen der korrigierte AGB2-Version, ohne Leerzeilen zurueck
	 * @param:
	 * @return: agbFileName_ohneLeerzeilen2
	 */
	public String getVersion2WithoutEmptyLines();
	
	
	/* Liest die ArrayListen von AGB1 und AGB2 aus und 
	 * schreibt zwei neue AGB-Dokumente mit vorangestellter Zeilennummer
	 * @param: List<String> linesArray1, List<String> linesArray2
	 * @return: 
	 */
	public void LineNumberWriter(List<String> linesArray1, List<String> linesArray2);
	
	
	/* Gibt den Pfad und den Dateinamen der Version1 mit Zeilennummern wieder
	 * @param:
	 * @return: agbFileName_mitZeilenNummer1
	 */
	public String getVersion1WithLineNumber();
	
	/* Gibt den Pfad und den Dateinamen der Version2 mit Zeilennummern wieder
	 * @param:
	 * @return: agbFileName_mitZeilenNummer2
	 */
	public String getVersion2WithLineNumber();
	
	
	
	/* prueft die beiden AGB-Versionen ohne Leerzeilen auf Gleichheit und gibt drei Versionen aus
	 * AGB-Version 1: Markierung der Aenderungen
	 * AGB-Version 2: Markierung der Aenderungen
	 * 3. Dokument: gibt nur die Aenderungen aus
	 */
	//public void compareAGBVersions(List<String> linesArray1, List<String> linesArray2);
	
	
	// gibt die AGB unveraendert auf der Konsole aus
	//public void showAGB(String agb);
	
	
	// zeigt AGB1-Version an
	//public void ShowVersion1();
	
	//zeigt AGB2-Version an
	//public void ShowVersion2();
	
	// zeigt alte und neue AGB Version an und markiert die Unterschiede
	//public void showAGBVersionWithDifferences();
	
	// zeigt nur die Passagen beider Versionen, die sich geaendert haben
	//public void showOnlyDifferences(String agb1, String agb2);
	
	// der User kann zwei AGBs per URL laden und diese vergleichen
	//public void quickCompare(String url1, String url2);
	
	
	
	/* liest eine uebergebene AGB1-Datei ein und speichert jede Zeile in einer ArrayListe
	 * @param: agbFileName1
	 * @return:
	 */
	public void readAGB1File(String agbFileName1);
	
	
	/* gibt AGB1File-Liste zueruck
	 * @param:
	 * @return: linesArray1
	 */
	public List<String> getAGB1FileArray();
	
	
	/* Gibt den Inhalt von einem uebergebenen LineArray1 auf der Konsole aus
	 * @param: List<String> linesArray1
	 * @return: 
	 */
	public void showAGB1FileArray(List<String> linesArray1);
	
	
	/* Schreibt Inhalt der ArrayList LineArray1 in eine Textdatei
	 * @param: List<String> linesArray1
	 * @return: 
	 */
	public void writeAGB1FileArray(List<String> linesArray1);
	
	
	/* liest eine uebergebene AGB2-Datei ein und speichert jede Zeile in einer ArrayListe
	 * @param: agbFileName2
	 * @return: 
	 */
	public void readAGB2File(String agbFileName2);
	
	
	/* gibt eine AGB2File-ArrayListe zueruck
	 * @param:
	 * @return: linesArray2
	 */
	public List<String> getAGB2FileArray();
	
	
	/* Gibt den Inhalt von einem uebergebenen LineArray2 auf der Konsole aus
	 * @param: List<String> linesArray2
	 * @return: 
	 */
	public void showAGB2FileArray(List<String> linesArray2);
	
	
	/* Schreibt Inhalt eines uebergebenen LineArray2 in eine Textdatei
	 * @param: List<String> linesArray2
	 * @return: 
	 */
	public void writeAGB2FileArray(List<String> linesArray2);
	
	
	/* Vergleicht beide ArrayLists ueber das Diff Tool und gibt die Aenderungen auf der Konsole aus
	 * @param: List<String> linesArray1, List<String> linesArray2
	 * @return: 
	 */
	public void compareArrayLists(List<String> linesArray1, List<String> linesArray2);
	
	
	/* Vergleicht beide ArrayLists ueber das Diff Tool und gibt Aenderungen in einer Textdatei aus
	 * @param: List<String> linesArray1, List<String> linesArray2
	 * @return: 
	 */
	public void writeAGBDifferences(List<String> linesArray1, List<String> linesArray2);
	
	

}
