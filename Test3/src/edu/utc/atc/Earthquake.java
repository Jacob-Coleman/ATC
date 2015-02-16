package edu.utc.atc;


public class Earthquake {
	double longitude;
	double latitude;
	double depth;
	String url;
	double magnitude;
	String title;
	public Earthquake(String t, double lat, double lon, double d, double m, String u)
	{
		title = t;
		latitude = lat;
		longitude = lon;
		depth = d;
		magnitude = m;
		url = u;
		
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
