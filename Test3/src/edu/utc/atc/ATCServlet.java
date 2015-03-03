package edu.utc.atc;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import edu.utc.atc.views.TabView;

@SuppressWarnings("serial")
@Theme("test3")
public class ATCServlet extends UI {
	 //Global session variables
	 double latitude;
	 double longitude;
	 double distance;
	 double depth;
	

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ATCServlet.class)
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
	

}