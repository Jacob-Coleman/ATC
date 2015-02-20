package edu.utc.atc;

import java.sql.Date;

import javafx.scene.chart.PieChart.Data;


public class Earthquake {
	double longitude;
	double latitude;
	double depth;
	String url;
	double magnitude;
	String title;
	Date time;
	
	public Earthquake(String t, Date ti, double lat, double lon, double d, double m, String u)
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
	public double getMagnitude(){
		return magnitude;
	}
	public String getUrl(){
		return url;
	}
	
	
	

}
