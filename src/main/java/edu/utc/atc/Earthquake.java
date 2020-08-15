package edu.utc.atc;
/**
 * Earthquake class for data to fill the query results table
 * the class file for a specific earthquake a line in the QueryView table
 *
 * 
 */
import java.util.Date;

import com.vaadin.ui.Component;


public class Earthquake {
	double longitude;
	double latitude;
	double depth;
	Component urlLink;
	String magnitude;
	String title;
	Date time;
	String urlString;
	
	public Earthquake(String t, Date ti, double lat, double lon, double d, String m, Component u, String us)
	{
		title = t;
		latitude = lat;
		longitude = lon;
		depth = d;
		magnitude = m;
		urlLink = u;
		time = ti;
		urlString = us;
		
	}
	public Date getTime(){
		return time;
	}
	public String getTitle(){
		return title;
	}
	public double getLatitude(){
		return latitude;
	}
	public double getLongitude(){
		return longitude;
	}
	public double getDepth(){
		return depth;
	}
	public String getMagnitude(){
		return magnitude;
	}
	public Component getUrlLink(){
		return urlLink;
	}
	public String getUrlString(){
		return urlString;
	}
	
	
	

}
