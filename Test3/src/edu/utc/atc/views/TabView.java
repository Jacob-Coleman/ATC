package edu.utc.atc.views;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.vaadin.server.WebBrowser;

import java.util.TimeZone;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;

import edu.utc.atc.components.TabComponent;

public class TabView extends TabComponent{
	public static double latitude;
	public static double longitude;
	public static double distance;
	public static double depth;

	public static TimeZone usersTimeZone;
	
	
	private int offset;
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
	           tcv.setDistance(distance);
	           tcv.setDepth(depth);
	           tcv.setLatitude(latitude);
	           tcv.setLongitude(longitude);
	   
	           WebBrowser browser = (WebBrowser) Page.getCurrent().getWebBrowser();
	           offset = browser.getTimezoneOffset();
	           System.out.println(offset);
	           /**
	           usersTimeZone.setRawOffset(offset);;
	           
	           Calendar cal = Calendar.getInstance();
	   		   TimeZone serverTimeZone = cal.getTimeZone();
	   		
	   		   cal.setTimeZone(serverTimeZone);
	   		   cal.add(Calendar.MILLISECOND, getUI().getPage().getWebBrowser().getTimezoneOffset() * - 1);
	   		   System.out.println(cal.toString());
	           
	           Notification distanceError = new Notification(usersTimeZone.getID(),Notification.TYPE_WARNING_MESSAGE);
				distanceError.show(Page.getCurrent());
	   		   //usersTimeZone.getOffset(browser.getRawTimezoneOffset());
	            **/
				
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
