package edu.utc.atc.views;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.UI;

import edu.utc.atc.ATCServlet;
import edu.utc.atc.components.TabComponent;

public class TabView extends TabComponent{

	private QueryView qv;
	private TimeCalcView tcv;
	private GoogleMapView gm;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8927740681632038714L;

	public TabView ()
	{
		this.tabSheet_MainTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			public void selectedTabChange(SelectedTabChangeEvent event) {

			   //Sets properties in TimeCalcView component and updates each time the ATC view is clicked in the tab
	           tcv.setDistance(((ATCServlet)UI.getCurrent()).getDistance());
	           tcv.setDepth(((ATCServlet)UI.getCurrent()).getDepth());
	           tcv.setLatitude(((ATCServlet)UI.getCurrent()).getLatitude());
	           tcv.setLongitude(((ATCServlet)UI.getCurrent()).getLongitude());
	         
				
			}
	        });
		
		//Adds View to the first tab on the tab sheet
		qv = new QueryView();
		tabSheet_Tab1.addComponent(qv);
		//Adds View to the second tab on the tab sheet
		tcv = new TimeCalcView();
		tabSheet_Tab2.addComponent(tcv);
		//gm = new GoogleMapView();
		//tabSheet_Tab3.addComponent(gm);
		
		
		
	}
}
