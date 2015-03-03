package edu.utc.atc;
/**
 * Eathquake class for data to fill the query results table
 * 
 */
import java.util.Date;
import com.vaadin.ui.Component;


public class Earthquake {
	double longitude;
	double latitude;
	double depth;
	Component url;
	String magnitude;
	String title;
	Date time;
	
	public Earthquake(String t, Date ti, double lat, double lon, double d, String m, Component u)
	{
		title = t;
		latitude = lat;
		longitude = lon;
		depth = d;
		magnitude = m;
		url = u;
		time = ti;
		
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
	public Component getUrl(){
		return url;
	}
	
	
	

}
