package edu.utc.atc;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
			System.out.println(g.getString("coordinates"));
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
