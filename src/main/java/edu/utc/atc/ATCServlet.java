package edu.utc.atc;
/**
 * The is the main UI for the arrival time calculator
 * It contains global variables for a users session so that data can be passed between tabs for calculating arrival times
 * This creates a tabview which contains others tabs of "views" theses views extend components.
 * The "views" have implementation while the "components" are for layout using the vaadin editor
 * 
 */
import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
//import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import edu.utc.atc.views.TabView;

@SuppressWarnings("serial")
@Theme("atc")
@Widgetset("edu.utc.atc.Widgetset")
//@PreserveOnRefresh
public class ATCServlet extends UI {
	 //Global session variables
	 double latitude;
	 double longitude;
	 double distance;
	 double depth;
	Date date;

	@WebServlet(value = "/*", asyncSupported = true)
	//@VaadinServletConfiguration(productionMode = false, ui = ATCServlet.class , widgetset="edu.utc.atc.widgetset.ArrivaltimecalculatorWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		//creaetes the main window tab
		TabView tv1 = new TabView();
		setContent(tv1);
		
	}
	
	 //Global session variables setters and getters
	public void setLatitude(double lat ){
		latitude = lat ;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLongitude(double lon) {
		longitude = lon;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setDepth (double dep){
		depth = dep;	
	}
	public double getDepth (){
		return depth;
	}
	public void setDistance(double dist) {
		distance = dist;
	}
	public double getDistance(){
		return distance;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate(){
		return this.date;
	}
	

}