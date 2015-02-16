package edu.utc.atc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.json.JSONException;



public class USGSConnect  {
	String urlBase = "http://comcat.cr.usgs.gov/fdsnws/event/1/query?format=geojson";
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
		       
		        
		       /** 
					       JsonNode rootNode = mapper.readTree(buffer.toString());
					        JsonNode featureNode = rootNode.path("Earthquake");
					        System.out.println(featureNode.toString());
					       
					        
					        System.out.println(buffer.toString());
					      ObjectReader objectReader = mapper.reader(Feature.class).withRootName("type");
					      Features feat = objectReader.readValue(buffer.toString());
					        
					      
					        
					       mapper.configure(Feature.valueOf("Earthquake"), true);
					       
					        System.out.println(buffer.toString());
					       ObjectMapper mapper = new ObjectMapper(); 
					       Earthquake quake =   
					         mapper.readValue(buffer.toString(), Earthquake.class);
					       System.out.println(mapper.writeValueAsString(quake));
					       
					       List earth = quake.getFeatures();
					       
					       
					       
					       ObjectMapper mapper2 = new ObjectMapper(); 
					     List<Features> feats =   
					       mapper2.readValue("""+earth.toString(), new TypeReference<List<Features>>(){});
					      System.out.println(mapper.writeValueAsString(feats));
					       
					     for(int i = 0; i < earth.size();i ++)
					       {
					         
					    	System.out.println(earth.get(i));
					    	 
		         
		       				}
		        
		          
		       **/
		        
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

