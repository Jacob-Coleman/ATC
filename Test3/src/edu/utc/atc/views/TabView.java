package edu.utc.atc.views;



import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;

import edu.utc.atc.components.TabComponent;

public class TabView extends TabComponent{
	public static double longitude;
	public static double latitude;
	public static double distance;
	public static double depth;
	private QueryView qv;
	private TimeCalcView tcv;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8927740681632038714L;

	public TabView ()
	{
		
		this.tabSheet_MainTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
			public void selectedTabChange(SelectedTabChangeEvent event) {
				
			   //Sets properties in TimeCalcView component and updates each time the ATC view is clicked in the tab
	           tcv.setDistance(distance);
	           tcv.setDepth(depth);

			}
	        });
		
		//Adds View to the first tab on the tab sheet
		qv = new QueryView();
		tabSheet_Tab1.addComponent(qv);
		//Adds View to the second tab on the tab sheet
		tcv = new TimeCalcView();
		tabSheet_Tab2.addComponent(tcv);
		
	}
}
