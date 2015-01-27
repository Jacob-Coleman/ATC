package edu.utc.atc.views;

import edu.utc.atc.TimeCalcCompositeOLD;
import edu.utc.atc.components.TabComponent;
import edu.utc.atc.components.TimeCalcComponent;

public class TabView extends TabComponent{

	public TabView ()
	{
		//Adds View to the second tab on the tab sheet
		tabSheet_Tab2.addComponent(new TimeCalcView());
	}
}
