package com.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.shared.models.AGBSource;

public class StartPage extends Composite{

	protected static final String SERVER_ERROR = "Server nicht erreichbar";
	private HorizontalPanel hPanel = new HorizontalPanel();
	private final AGBToolServiceAsync agbToolService = GWT.create(AGBToolService.class);
	final HTML serverResponseLabel = new HTML();
	
	//Listen zum speichern der AGBs
	private List<AGBSource> TopTenList = null;
	private List<AGBSource> AllAGBsList = null;
	String searchString = "";
	
	public StartPage() {
		initWidget(this.hPanel);
		hPanel.setSpacing(5);
		hPanel.setStyleName("center");
		
		//Vertikales Panel für Suchfunktionen (links)
		VerticalPanel Search = new VerticalPanel();
		Search.setSpacing(10);
		
		//TextBox für die Suchfunktion - Gesuchte AGB kann hier eingegeben werden
		final TextBox searchText = new TextBox();
		searchText.setText("Suche");
		searchText.setFocus(true);
		
		//Liste der gesuchten AGBs
		final TextCell textCell = new TextCell();
		final CellList<String> AllAGBList = new CellList<String>(textCell);
	    Label noLoadedData = new Label("Oops, no Data loaded! Please try again");
	    AllAGBList.setEmptyListWidget(noLoadedData);
	    
	    
		//ClickHandler: Beim klicken in TextBox der Suche soll der Inhalt "Suche" gelöscht werden
		searchText.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			      searchText.setText("");
				  
			    }
		});
		
		//Alle AGBs holen
		agbToolService.getAllAGBs( new AsyncCallback<List<AGBSource>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
			}
			public void onSuccess(List<AGBSource> allAGBs) {
				//Speichern der AGB in Liste
				AllAGBsList = allAGBs;
				
				serverResponseLabel.setHTML(allAGBs.get(1).getName());
			}
		});
		
		
		
		//Bei Verändern des Werts in der Suche TextBox und danach Enter:
		searchText.addValueChangeHandler(new ValueChangeHandler<String>() {
			 @Override
		        public void onValueChange(ValueChangeEvent<String> event) {
				 	//Speichern des eingegebenen Texts
		            searchString = event.getValue(); 
		            
		            //Alle AGBs die auf das gesuchte zutreffen könnten in Liste speichern
		            //Ist nötig um die CellList zu befüllen
		            final List<String> AGBNameList = new ArrayList<String>();
		            for (AGBSource agb: AllAGBsList) {
						if(agb.getName().toLowerCase().contains(searchString.toLowerCase()) || 
						   agb.getName().equalsIgnoreCase(searchString) ||
						   agb.getName().toLowerCase().startsWith(searchString.toLowerCase())) {
							
							AGBNameList.add(agb.getName());
						}
					}
		            AllAGBList.setRowCount(AGBNameList.size(), true);
		    	    AllAGBList.setRowData(0, AGBNameList);
		    		//Gefundenes ausgeben
		    	    AllAGBList.redraw();
		            
		    	    //Wenn etwas in der CellList ausgewählt wird:
		            AllAGBList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		    	    final SingleSelectionModel<String> selectionModel2 = new SingleSelectionModel<String>();
		    	    AllAGBList.setSelectionModel(selectionModel2);
		    	    selectionModel2.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		    	      public void onSelectionChange(SelectionChangeEvent event) {
		    	        String selected = selectionModel2.getSelectedObject();
		    	        if(selected != null) {
		    	        //Gewählte AGB zum übergeben speichern
		    	        AGBSource selectedAgb = null;
		    	        for (AGBSource agb: AllAGBsList) {
		    	        	if(agb.getName().equals(selected)) {
		    	        		selectedAgb = agb;
		    	        	}
		    	        	
		    	        } 
		    	        //DialogBox erstellen
		    	        final DialogBox dialogBox2 = ComparePage.CreateDialogBox(selectedAgb);
		    		    dialogBox2.setGlassEnabled(true);
		    		    dialogBox2.setAnimationEnabled(true);
		    		        
		    		    if (selected != null) {
		    		        dialogBox2.center();
		    		        dialogBox2.show();
		    		    }
		    	        }
		    	      }
		    	    });
		    	    

			 }
		});
		
		
    
		
		//Widgets zum Suchpanel hinzufügen
		Search.add(searchText);
		Search.add(AllAGBList);
		
		//Vertikales Panel für die TopTen
		VerticalPanel TopTen = new VerticalPanel();
		TopTen.setSpacing(10);
		
		//Überschrift für die TopTen 
		Label topTenHeader = new Label("Unsere Top 10 AGBs!");
		topTenHeader.setStyleName("Header");

		//Liste für die TopTen
	    final CellList<String> topTenCellList = new CellList<String>(textCell);
	    topTenCellList.setEmptyListWidget(noLoadedData);    
	    
	    //Holen der AGBFavorites und speichern der besten 10
		agbToolService.getTopTenAGBs( new AsyncCallback<List<AGBSource>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR);
			}
			public void onSuccess(List<AGBSource> topTenAGBVersions) {
				//db.setText("Erfolg: AGBs wurden geladen");
				TopTenList = topTenAGBVersions;
				List<String> topTenNameList = new ArrayList<String>();
				int count = 1;
				//Speichern der beliebtesten  10 AGBs
				for (AGBSource agb: TopTenList) {
					if(count <= 10) {
						topTenNameList.add(count + ". " +  agb.getName());
						count++;
					}
					
				}
				//In der TopTen CellList wird etwas ausgewählt:
			    topTenCellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
			    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
			    topTenCellList.setSelectionModel(selectionModel);
			    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			      public void onSelectionChange(SelectionChangeEvent event) { 
			        String selected = selectionModel.getSelectedObject();
			        if(selected != null) {
			        	 selected = selected.split(" ")[1];
					        AGBSource selectedAgb = null;
					        
					        //In den gespeicherten AGBs die ausgewählte suchen
			    	        for (AGBSource agb: TopTenList) {
			    	        	if(agb.getName().equals(selected)) {
			    	        		selectedAgb = agb;
			    	        	}
			    	        	
			    	        } 
			    	        
			    	        //DialogBox erstellen -> auf Klasse ComparePage zugreifen
			    	        final DialogBox dialogBox = ComparePage.CreateDialogBox(selectedAgb);
					        dialogBox.setGlassEnabled(true);
					        dialogBox.setAnimationEnabled(true);
					        
					        if (selected != null) {
					        	dialogBox.center();
					            dialogBox.show();
					        }
			        }
			       
			      }
			    });
			    topTenCellList.setRowCount(topTenNameList.size(), true);
			    topTenCellList.setRowData(0, topTenNameList);
			    
				
			    topTenCellList.redraw();
			    
				serverResponseLabel.setHTML(topTenAGBVersions.get(1).getName());
			}
		});
		
		//Widgets zum TopTenPanel hinzufügen
		TopTen.add(topTenHeader);
		TopTen.add(topTenCellList);
		
		//Vertikale Panel zum Horizontalen Panel hinzufügen
		hPanel.add(Search);
		hPanel.add(TopTen);
		
		
	}

	
}
