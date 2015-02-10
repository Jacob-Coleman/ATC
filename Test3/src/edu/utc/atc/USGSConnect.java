package edu.utc.atc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;

import jdk.nashorn.internal.parser.JSONParser;



public class USGSConnect  {
	String urlBase = "http://comcat.cr.usgs.gov/fdsnws/event/1/query?format=geojson";
	public USGSConnect(String paramaters) throws IOException {
		
		
		System.out.println(urlBase + paramaters);
		process( urlBase + paramaters);
	}
	
	public void process(String urlString) throws IOException{
		
		 BufferedReader reader = null;
		    try {
		        URL url = new URL(urlString);
		        reader = new BufferedReader(new InputStreamReader(url.openStream()));
		        StringBuffer buffer = new StringBuffer();
		        int read;
		        char[] chars = new char[1024];
		        while ((read = reader.read(chars)) != -1)
		            buffer.append(chars, 0, read); 

		        System.out.println( buffer.toString());
		    } finally {
		        if (reader != null)
		            reader.close();
		    }
		
	}
}

