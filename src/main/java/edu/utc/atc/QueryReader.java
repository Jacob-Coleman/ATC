package edu.utc.atc;
/**
 * Reads information passed by the buffered reader created by USGSConnect
 * It then parses the data and builds the table of earthquakes to return to the QueryView
 * A GeoJson for an earthquake comes in as a nested json so the first JSON Object opens up the earthquakes 
 * to read the array of earthquakes received. 
 */

import java.util.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.vaadin.server.ExternalResource;

import com.vaadin.ui.Component;
import com.vaadin.ui.Link;


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
			
			//Separates earthquake JSON container into pieces to pull individual earthquake detials 
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
					
					System.out.println("Title " + title + " Longitude"+" "+longitude+" "+" latitude"+" "+latitude+" "+" depth"+" "+depth +" mag"+ mag + " url"+" "+url );

					
					//sets date in server time zone
					Date qTime = new Date(time);
					//adjust date from server time zone to UTC time zone
					Date quakeTime = correctForTimeZone(qTime);
					// formats mag so that it returns a magnitudes in 6.0 instead of just 6	
					String magString = Double.toString(mag);
					//creates the Link for the earthquake
					Component urlLink = creatUrlComponent(url);

					
					
					
					quakes.add(new Earthquake(title,quakeTime,latitude,longitude,depth,magString,urlLink,url));
					
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	
	public List<Earthquake> getQuakes(){
		return quakes;
	}
	
	//Reads the servers time zone and changes the returns to UTC time.
	public Date correctForTimeZone(Date d){
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		TimeZone serverTimeZone = cal.getTimeZone();
		
		cal.setTimeZone(serverTimeZone);
		
		//serverTimeZone.getDSTSavings() accounts for DST offset so that date calculations return normally
		cal.add(Calendar.MILLISECOND, (serverTimeZone.getRawOffset() + serverTimeZone.getDSTSavings()) * - 1);
		System.out.println(serverTimeZone.getDSTSavings());
		
		return cal.getTime();
		
	}
	
	//creates a link for the details of the earthquake on the USGS website. 
	public Component creatUrlComponent(String url){
		Link quakeLink  = new Link("Details",new ExternalResource(url));
		quakeLink.setTargetName("_blank");
		
		return quakeLink ;
	}
	
	
	
}
