package edu.utc.atc;


import java.util.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;





import java.util.TimeZone;

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
	long time;
	
	private List<Earthquake> quakes = new  LinkedList<Earthquake>();
	
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
					time = propertiesObject.getLong("time");
					
					//sets date in server time zone
					Date qTime = new Date(time);
					
					//adjust date from server time zone to UTC time zone
					Date quakeTime = correctForTimeZone(qTime);
					
					System.out.println("Title " + title + " Longitude"+" "+longitude+" "+" latitude"+" "+latitude+" "+" depth"+" "+depth +" mag"+ mag + " url"+" "+url );
					
					// formats mag so that it returns a magnitudes in 6.0 instead of just 6	
					String magString = Double.toString(mag);
					
					quakes.add(new Earthquake(title,quakeTime,latitude,longitude,depth,magString,url));
					
				}
				
	
				
				//			JSONObject meta = (JSONObject) ob.get("meta");
				//			System.out.println(meta.names());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	
	public List<Earthquake> getQuakes(){
		return quakes;
	}
	public Date correctForTimeZone(Date d){
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		TimeZone serverTimeZone = cal.getTimeZone();
		
		cal.setTimeZone(serverTimeZone);
		cal.add(Calendar.MILLISECOND, serverTimeZone.getRawOffset() * - 1);
		
	
		
		return cal.getTime();
		
	}
	
	
	
}
