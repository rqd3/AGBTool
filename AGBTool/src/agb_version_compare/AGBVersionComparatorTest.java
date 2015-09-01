package agb_version_compare;

import com.server.backend.APIController;
import com.shared.models.AGBVersion;

public class AGBVersionComparatorTest 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		/* Dokumentation: Schritte
		 * 
		 * Schritt 1: Anlegen eines AGBVersionComparator-Objektes vc
		 * 
		 * Schritt 2: vc.setAGB1("TUI", "Sommer", "2015-03-01");
		 *            vc.setAGB2("TUI", "Winter", "2014-11-01")
		 *   
		 * Schritt 3: Pruefen, ob zwei AGB-Versionen zum Vergleich vorliegen
		 *            vc.twoVersionsExits())  
		 *             --> soll spaeter automatisch von der Vergleichsmethode aufgerufen werden!      
		 * 
		 * Schritt 4: Pfad und Dateinamen der beiden AGBs zusammensetzen, um die Datei finden zu koennen
		 *            vc.setPathFileName();
		 *            --> soll spaeter innerhalb einer Methode automatisch aufgerufen werden!
		 * 
		 * Schritt 5: Beide AGBs einlesen und jeweils in eine ArrayList speichern
		 *            vc.readAGB1File(vc.getPathFileNameAGB1());
		 *            vc.readAGB2File(vc.getPathFileNameAGB2());
		 *            
		 * Schritt 6: Vergleich beider AGB-Versionen und Ausgabe der Zeile + Inhalt auf der Konsole, was sich geandert hat
		 *            vc.compareArrayLists(vc.getAGB1FileArray(), vc.getAGB2FileArray());
		 *            
		 * Schritt 7: Vergleich beider AGB-Versionen und Ausgabe der Zeile + Inhalt der alten und neuen AGB-Version
		 * 
		 * Beispiel-Ausgabe: [ChangeDelta, position: 0, lines: [Allgemeine Gesch‰ftsbedingungen TUI Deutschland GmbH] to [Allgemeine Gesch√§ftsbedingungen TUI Deutschland GmbH]]
		 * 
		 */
		
		AGBVersionComparator vc1 = new AGBVersionComparator();
		
		// User waehlt AGB aus
		vc1.setAGB1("TUI", "Sommer", "2015-03-01");
		vc1.setAGB2("TUI", "Winter", "2014-11-01");
		
		// AGB Name anzeigen
		vc1.getAGB1();
		vc1.getAGB2();
		
		// Zeigt an, ob zwei Versionen ausgewaehlt wurden und vergleichbar sind 
		// (d.h. ein Anbieter, zwei verschiedene Veroeffentlichungen)
		System.out.println(vc1.twoVersionsExits());
		
		// setzt den Pfad und den Dateinamen zusammen
		vc1.setPathFileName();
	
		
		vc1.getPathFileNameAGB1();
		
		System.out.println("");
		
		System.out.println(vc1.documentLength(vc1.getPathFileNameAGB1()));
		System.out.println(vc1.documentLength(vc1.getPathFileNameAGB2()));
		System.out.println("");
		
		
		System.out.println("");
		System.out.println(vc1.getPathFileNameAGB1());
		System.out.println(vc1.getPathFileNameAGB2());
		
		
		// Vergleich AGB-Versionen
		//System.out.println("");
		//vc1.compareAGBVersions(vc1.getPathFileNameAGB1(), vc1.getPathFileNameAGB2());
		
		// Entferne Leerzeilen mit neuer Methode
		//System.out.println("");
		//vc1.deleteEmptyLines(vc1.getPathFileNameAGB1(), vc1.getPathFileNameAGB2());
		
		//System.out.println("");
		//System.out.println(vc1.getVersion1WithoutEmptyLines());
		//System.out.println(vc1.getVersion2WithoutEmptyLines());
		
		// Nummeriere Zeilen mit nuer Methode
		//System.out.println("");
		//vc1.LineNumberWriter(vc1.getPathFileNameAGB1(), vc1.getPathFileNameAGB2());
		
		/*
		System.out.println("");
		System.out.println(vc1.getVersion1WithLineNumber());
		System.out.println(vc1.getVersion2WithLineNumber());
		
		// AGB-Versionen ohne Leerzeilen und mit Zeilennummerierung
		System.out.println("");
		vc1.LineNumberWriter(vc1.getVersion1WithoutEmptyLines(), vc1.getVersion2WithoutEmptyLines());
		
		
		// AGB-Vergleich mit Versionen ohne Leerzeilen mit Zeilennummer
		System.out.println("");
		vc1.compareAGBVersions(vc1.getVersion1WithLineNumber(), vc1.getVersion2WithLineNumber());
		
		// gib AGB1 auf Konsole aus (funktioniert)
		/*System.out.println("");
		vc1.showAGB(vc1.getPathFileNameAGB1());
		System.out.println("");
		vc1.showAGB(vc1.getPathFileNameAGB2());
		System.out.println("");
		vc1.showAGB(vc1.getVersion1WithoutEmptyLines()); */
		
		
		// Teste 2. Objekt
		AGBVersionComparator vc2 = new AGBVersionComparator();
		
		// User waehlt AGB aus
		vc2.setAGB1("Air Berlin", "Sommer", "2015-07-09");
		vc2.setAGB2("Air Berlin", "Winter", "2015-07-09");
		
		// Zeigt an, ob zwei Versionen ausgewaehlt wurden und vergleichbar sind 
		// (d.h. ein Anbieter, zwei verschiedene Veroeffentlichungen)
		System.out.println(vc2.twoVersionsExits());
				
		// setzt den Pfad und den Dateinamen zusammen
		vc2.setPathFileName();
		
		// Vergleich AGB-Versionen
		//System.out.println("");
		//vc2.compareAGBVersions(vc2.getPathFileNameAGB1(), vc2.getPathFileNameAGB2());
		
		
		
		// Teste 3. Objekt
		AGBVersionComparator vc3 = new AGBVersionComparator();
				
		// User waehlt AGB aus
		vc3.setAGB1("Alltours", "Sommer", "2014-09-01");
		vc3.setAGB2("Alltours", "Winter", "2015-05-01");
				
		// Zeigt an, ob zwei Versionen ausgewaehlt wurden und vergleichbar sind 
		// (d.h. ein Anbieter, zwei verschiedene Veroeffentlichungen)
		System.out.println(vc3.twoVersionsExits());
						
		// setzt den Pfad und den Dateinamen zusammen
		vc3.setPathFileName();
				
		// Vergleich AGB-Versionen
		System.out.println("");
		
		
		//vc3.deleteEmptyLines(vc3.getPathFileNameAGB1(), vc3.getPathFileNameAGB2());
		//vc3.compareAGBVersions(vc3.getVersion1WithoutEmptyLines(), vc3.getVersion2WithoutEmptyLines());
		
		//vc3.compareAGBVersions(vc3.getPathFileNameAGB1(), vc3.getPathFileNameAGB2());
		
		// Array-List-Test: erfolgreich 
		vc3.readAGB1File(vc3.getPathFileNameAGB1());
		System.out.println(vc3.getAGB1FileArray());
		
		vc3.showAGB1FileArray(vc3.getAGB1FileArray());
		vc3.writeAGB1FileArray(vc3.getAGB1FileArray());
		
		
		vc3.readAGB2File(vc3.getPathFileNameAGB2());
		System.out.println(vc3.getAGB2FileArray());
		
		vc3.showAGB2FileArray(vc3.getAGB2FileArray());
		vc3.writeAGB2FileArray(vc3.getAGB2FileArray());
		
		System.out.println("");
		
		vc3.compareArrayLists(vc3.getAGB1FileArray(), vc3.getAGB2FileArray());
		
		// TEST mit vc1:
		vc1.readAGB1File(vc1.getPathFileNameAGB1());
		System.out.println(vc1.getAGB1FileArray());
		
		vc1.showAGB1FileArray(vc1.getAGB1FileArray());
		vc1.writeAGB1FileArray(vc1.getAGB1FileArray());
		
		
		vc1.readAGB2File(vc1.getPathFileNameAGB2());
		System.out.println(vc1.getAGB2FileArray());
		
		vc1.showAGB2FileArray(vc1.getAGB2FileArray());
		vc1.writeAGB2FileArray(vc1.getAGB2FileArray());
		
		System.out.println("");
		
		vc1.compareArrayLists(vc1.getAGB1FileArray(), vc1.getAGB2FileArray());
		vc1.writeAGBDifferences(vc1.getAGB1FileArray(), vc1.getAGB2FileArray());
		
		// Teste neuen LineNumberWriter (Test erfolgreich!)
		vc1.LineNumberWriter(vc1.getAGB1FileArray(), vc1.getAGB2FileArray());
		
		// Teste neue Methode deleteEmptyLines (Test erfolgreich!)
		vc1.deleteEmptyLines(vc1.getAGB1FileArray(), vc1.getAGB2FileArray());
		
		// Teste neue Methode compareAGBVersions
		//vc1.compareAGBVersions(vc1.getAGB1FileArray(), vc1.getAGB2FileArray());
	 
		
		
		/*Patch patch1 = DiffUtils.diff(vc3.getAGB1FileArray(), vc3.getAGB2FileArray());
		 for (Delta delta: patch1.getDeltas()) {
             System.out.println(delta);
     }*/
		
		/*
		// Diff Tool testen
		// --------------------------------------------------------------------------
		List<String> original = vc1.fileToLines(vc1.getPathFileNameAGB1());
        List<String> revised  = vc1.fileToLines(vc1.getPathFileNameAGB2());
        
        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch1 = DiffUtils.diff(original, revised);

        for (Delta delta: patch1.getDeltas()) {
                System.out.println(delta);
        }
        
        
        // Test2
        // At first, parse the unified diff file and get the patch
        Patch patch2 = DiffUtils.parseUnifiedDiff(vc1.fileToLines2("/Studium/Wirtschaftsinformatik/7. Semester (SS 2015)/Projektarbeit/AGB TUI Deutschland GmbH - Sommer 2015-08-09.txt"));
        
        // Then apply the computed patch to the given text
        try {
			List result = DiffUtils.patch(original, patch2);
		} catch (PatchFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /// Or we can call patch.applyTo(original). There is no difference.*/
		

		
		
		
		// APIController-Test
		AGBVersion v1 = new AGBVersion();
		APIController apic1 = new APIController();
		
		
		//testing
		
		apic1.getAllAGBVersionsOfSource(4);
		System.out.println(apic1.getLatestAGBVersion(4).getText());
		apic1.getLatestAGBVersion(2).getText();
		apic1.getAllAGBSources();
		
		
		
		

	} //endmethod main

} //endclass
