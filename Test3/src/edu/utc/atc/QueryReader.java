package edu.utc.atc;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.sun.javafx.scene.paint.GradientUtils.Point;

public class QueryReader 
{
	private String query;
	public QueryReader(String q)
	{
		query = q;
	}
	
	public void seperateEarthquake()
	{
		JSONTokener tokener = new JSONTokener(query);
		try {
		
			JSONObject ob = (JSONObject) tokener.nextValue();
		
			JSONArray features = (JSONArray) ob.get("features");
			JSONObject properties = features.optJSONObject(4);
			JSONObject f = (JSONObject) properties.get("properties");
			JSONObject g = (JSONObject) properties.get("geometry");
		
			System.out.println(f.names());
			JSONArray p = (JSONArray) g.get("coordinates");
			Double longitude = p.getDouble(0);
			Double latitude = p.getDouble(1);
			Double depth = p.getDouble(2);
			System.out.println("Longitude"+" "+longitude+" "+"latitude"+" "+latitude+" "+"depth"+" "+depth);
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
	
	
}
