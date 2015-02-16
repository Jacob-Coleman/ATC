package edu.utc.atc.views;

import edu.utc.atc.components.TabComponent;

public class TabView extends TabComponent{
	public static double longitude;
	public static double latitude;
	public static double distance;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8927740681632038714L;

	public TabView ()
	{
		//Adds View to the first tab on the tab sheet
		tabSheet_Tab1.addComponent(new QueryView());
		//Adds View to the second tab on the tab sheet
		tabSheet_Tab2.addComponent(new TimeCalcView());
		
	}
}
