package com.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agb.compare.AGBComparator;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.shared.models.AGBSource;
import com.shared.models.AGBVersion;

public class ComparePage extends Composite {
	
	protected static final String SERVER_ERROR = "Server nicht erreichbar";
	private final static AGBToolServiceAsync agbToolService = GWT.create(AGBToolService.class);
	final static HTML serverResponseLabel = new HTML();
	
	public static DialogBox CreateDialogBox(String name, List<AGBVersion> AllVersions) {
		
		final DialogBox compareDialog = new DialogBox();
		AGBVersion selectedVersion = null;
		final TextArea changedText = new TextArea();
		//static ListBox list = new ListBox();
		TextArea agbText = new TextArea();
		//Für die Demo
		final int changesCount = 1;
		//Parameter zum speichern aller Versionen
		
		
		//DialogBox erstellen
		///final DialogBox compareDialog = new DialogBox();
		compareDialog.setText("Compare Dialog Box - " + name);
		
		
		List<AGBVersion> allOfAGB = AllVersions;
		 
		
		//if um herauszufinden ob genug Versionen für einen Vergleich vorhanden sind
		//Keine Möglichkeit zum Vergleich!
		if(allOfAGB.size() == 1 || allOfAGB.size() == 0) {
			VerticalPanel vPanel = new VerticalPanel();
			vPanel.setSpacing(5);
			compareDialog.setWidget(vPanel);
			
			//Label um dem Nutzer anzuzeigen das ein Vergleich nicht möglich ist
			Label label = new Label("Es tut uns Leid, es liegen noch keine zwei AGB Versionen zum vergleichen vor");
			vPanel.add(label);
			
			//Button um die DialogBox zu schließen
			Button closeButton = new Button( "Close" , new ClickHandler() {
				public void onClick(ClickEvent event) {
				 	compareDialog.hide();
				}
			});
			vPanel.add(closeButton);
			if (LocaleInfo.getCurrentLocale().isRTL()) {
			vPanel.setCellHorizontalAlignment(
				closeButton, HasHorizontalAlignment.ALIGN_LEFT);
			} else {
				vPanel.setCellHorizontalAlignment(
				closeButton, HasHorizontalAlignment.ALIGN_RIGHT);
			}
		}
		
		
		
		
		
		
		//Der Vergleich wird angezeigt - 
		//Code um Test vorzuführen bitt normalerweise auskommentiert lassen oder löschen
		/*AGBSource agbS = null;
		if(agbS.getId() == 42) {
			
			//AGBComparator a = new AGBComparator();
			//final List<String> difForDemo = a.getAGBDifferencesForDemo(42, 1, 2);
			final List <String> difForDemo = new ArrayList<String>();
					agbToolService.getAGBDifferencesForDemo(42, 1, 2,new AsyncCallback<List<String>>() {
				public void onFailure(Throwable caught) {
				//do sth.
				}
				public void onSuccess(List<String> allAgbVersions) {
				//do sth.
				}
				});
			
		
			Date date = null;
			AGBVersion last = null;
			//Letzte AGBVersion herausziehen
			for(AGBVersion v: AllVersions) {
				if(date != null) {
					if(date.before(v.getPublishedAt())) {
						date = v.getPublishedAt();
						last = v;
					}
				}
				else {
					date = v.getPublishedAt();
					last = v;
				}
			}
			//Letzte Version automatisch als gesetzte Wählen
			selectedVersion = last;
			
	    	
	    	HorizontalPanel hPanel = new HorizontalPanel();
			hPanel.setSpacing(10);
			compareDialog.setWidget(hPanel);
			
			

		    
		    
		    //TextArea um die ausgewählten AGBs anzuzeigen
			agbText.setText(selectedVersion.getText());
			agbText.setReadOnly(true);
			agbText.setSize("350px", "400px");
			
			//Button um zur nächsten Änderung zu wechseln
			Button nextChange = new Button("Next!");
			nextChange.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					//changesCount++;
					changedText.setText(difForDemo.get(changesCount));
				    }
			});

			
			//TextArea um die Änderungen anzuzeigen
			changedText.setText(difForDemo.get(changesCount));
			changedText.setReadOnly(true);
			changedText.setSize("200px", "300px");

			//Button um die DialogBox zu schließen
			Button closeButton = new Button( "Close" , new ClickHandler() {
				public void onClick(ClickEvent event) {
				 	compareDialog.hide();
				}
			});
			hPanel.add(closeButton);
			if (LocaleInfo.getCurrentLocale().isRTL()) {
			hPanel.setCellHorizontalAlignment(
				closeButton, HasHorizontalAlignment.ALIGN_LEFT);
			} else {
				hPanel.setCellHorizontalAlignment(
				closeButton, HasHorizontalAlignment.ALIGN_RIGHT);
			}

			
			//Vertikales Panel zur AGB anzeige
			VerticalPanel agbVersion = new VerticalPanel();
			agbVersion.setSpacing(5);
			agbVersion.add(agbText);

			VerticalPanel agbChanges = new VerticalPanel();
			agbVersion.setSpacing(5);
			agbChanges.add(nextChange);
			agbChanges.add(changedText);
			
			hPanel.add(agbVersion);
			hPanel.add(agbChanges);
			hPanel.add(closeButton);
			
			
			
		}*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Korrekter Code
		else { 
			Date date = null;
			AGBVersion last = null;
			//Letzte AGBVersion herausziehen
			for(AGBVersion v: allOfAGB) {
				if(date != null) {
					if(date.before(v.getPublishedAt())) {
						date = v.getPublishedAt();
						last = v;
					}
				}
				else {
					date = v.getPublishedAt();
					last = v;
				}
			}
			//Letzte Version automatisch als gesetzte Wählen
			selectedVersion = last;
			final AGBVersion selectedV = selectedVersion;
	    	
	    	HorizontalPanel hPanel = new HorizontalPanel();
			hPanel.setSpacing(10);
			compareDialog.setWidget(hPanel);
			/*
			//Liste um das Datum aller AGB Versionen anzuzeigen
		    list.setVisibleItemCount(1);
		    for (AGBVersion v: AllVersions) {
		      list.addItem(v.getPublishedAt().toString());
		    }
		    
		    //Bei Änderungen der ausgewählten AGB sollen diese eingetragen werden
		    list.addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					int selected = list.getSelectedIndex();
					String selectedYear = list.getValue(selected);
					for (AGBVersion v: AllVersions) {
						if(selectedYear.equals(v.getPublishedAt())) {
							selectedVersion = v;
							agbText.setText("Hurra" + selectedVersion.getText());
							changedText.setText("jay");
						}
					      
					 }
					  
				}
			});*/
		    
		    
		    //TextArea um die ausgewählten AGBs anzuzeigen
			agbText.setText(selectedV.getText());
			agbText.setReadOnly(true);
			agbText.setSize("350px", "400px");
			
			//Button um zur nächsten Änderung zu wechseln
			Button nextChange = new Button("Next!");
			nextChange.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					changedText.setText("jep worked");
				    }
			});

			
			//TextArea um die Änderungen anzuzeigen
			changedText.setText("Hier könnte ihre AGB stehen!");
			changedText.setReadOnly(true);
			changedText.setSize("200px", "300px");

			//Button um die DialogBox zu schließen
			Button closeButton = new Button( "Close" , new ClickHandler() {
				public void onClick(ClickEvent event) {
				 	compareDialog.hide();
				}
			});
			hPanel.add(closeButton);
			if (LocaleInfo.getCurrentLocale().isRTL()) {
			hPanel.setCellHorizontalAlignment(
				closeButton, HasHorizontalAlignment.ALIGN_LEFT);
			} else {
				hPanel.setCellHorizontalAlignment(
				closeButton, HasHorizontalAlignment.ALIGN_RIGHT);
			}

			
			//Vertikales Panel zur AGB anzeige
			VerticalPanel agbVersion = new VerticalPanel();
			agbVersion.setSpacing(5);
			//agbVersion.add(list);
			agbVersion.add(agbText);

			VerticalPanel agbChanges = new VerticalPanel();
			agbVersion.setSpacing(5);
			agbChanges.add(nextChange);
			agbChanges.add(changedText);
			agbChanges.add(closeButton);
			
			
			hPanel.add(agbVersion);
			hPanel.add(agbChanges);
			
			
			
		}
		
		//Übergeben der DialogBox
	return compareDialog;

	}


	
}
