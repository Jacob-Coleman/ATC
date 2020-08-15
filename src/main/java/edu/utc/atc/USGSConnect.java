package edu.utc.atc;
/**
 * Connects to the USGS and receives back the GeoJson in a buffered reader
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.json.JSONException;




public class USGSConnect  {
	String urlBase = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson";
	private QueryReader qr;
	public USGSConnect(String paramaters) throws IOException, JSONException {
		
		
		System.out.println(urlBase + paramaters);
		process( urlBase + paramaters);
	}
	
	public void process(String urlString) throws IOException, JSONException{
		
		 BufferedReader reader = null;
		    try {
		        URL url = new URL(urlString);
		        reader = new BufferedReader(new InputStreamReader(url.openStream()));
		        StringBuffer buffer = new StringBuffer();
		        int read;
		        char[] chars = new char[1024];
		        while ((read = reader.read(chars)) != -1)
		            buffer.append(chars, 0, read); 
		        
		        
		 
		       qr = new QueryReader(buffer.toString());
		       //System.out.println(buffer.toString());
		       qr.seperateEarthquake();
		       
		    } finally {
		        if (reader != null)
		            reader.close();
		    }
		
	}
	
	public List<Earthquake> getQuakes()
	{
		return qr.getQuakes();
	}
	
}

