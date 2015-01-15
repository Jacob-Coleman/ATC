package edu.utc.atc.views;

import edu.utc.atc.TimeCalcComposition;
import edu.utc.atc.components.TabComponent;

public class TabView extends TabComponent{

	public TabView ()
	{
		tabSheet_3.addComponent(new TimeCalcComposition());
	}
}
