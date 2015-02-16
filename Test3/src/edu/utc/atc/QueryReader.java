package edu.utc.atc;


import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class QueryReader 
{
	private String query;
	
	JSONObject properties;
	JSONObject propertiesObject;
	JSONObject geometryObject;
	JSONArray featuresArray;
	JSONArray cordinatesArray;
	double longitude;
	double latitude;
	double depth;
	String url;
	double mag;
	String title;
	
	private List<Earthquake> quakes = new  LinkedList();
	
	public QueryReader(String q)
	{
		query = q;
	}
	
	public void seperateEarthquake() throws JSONException
	{
		
		JSONTokener tokener = new JSONTokener(query);
		JSONObject ob = (JSONObject) tokener.nextValue();
		try {
			
				JSONArray features = (JSONArray) ob.get("features");
				for(int i = 0; features.length() > i; i++)
				{
					
					
					properties = features.optJSONObject(i);
					propertiesObject = (JSONObject) properties.get("properties");
					geometryObject = (JSONObject) properties.get("geometry");
				
					//System.out.println(propertiesObject.names());
					cordinatesArray = (JSONArray) geometryObject.get("coordinates");
					longitude = cordinatesArray.getDouble(0);
					latitude = cordinatesArray.getDouble(1);
					depth = cordinatesArray.getDouble(2);
					url = propertiesObject.getString("url");
					mag = propertiesObject.getDouble("mag");
					title = propertiesObject.getString("title");
					
					System.out.println("Title " + title + " Longitude"+" "+longitude+" "+" latitude"+" "+latitude+" "+" depth"+" "+depth +" mag"+ mag + " url"+" "+url );
					
					quakes.add(new Earthquake(title,latitude,longitude,depth,mag,url));
					
				}
				
	
				
				//			JSONObject meta = (JSONObject) ob.get("meta");
				//			System.out.println(meta.names());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	public void earthquakeCreator()
	{
		
	}
	
	public List<Earthquake> getQuakes(){
		return quakes;
	}
	
	
	
	
}
