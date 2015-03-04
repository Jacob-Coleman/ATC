package edu.utc.atc.views;



import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;

import edu.utc.atc.USGSConnect;
import edu.utc.atc.components.GoogleMapComponent;

public class GoogleMapView extends GoogleMapComponent {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3044805767275418705L;

	public GoogleMapView( ) {
		GoogleMap googleMap = new GoogleMap("apiKey", null, "english");
		googleMap.setSizeFull();
		googleMap.setCenter(new LatLon( 39.8282, -98.5795));
		
		mainLayout.addComponent(googleMap);
		
	}

	public GoogleMapView(USGSConnect currentEarthquakes, double stationLat, double stationLon ) {
		
		GoogleMap googleMap = new GoogleMap("apiKey", null, "english");
		googleMap.setSizeFull();
		googleMap.setCenter(new LatLon( stationLat, stationLon));
		
		googleMap.addMarker("Seismic Station", new LatLon(
		        stationLat, stationLon), false, null);
		googleMap.setZoom(5);
		for(int i = 0; i < currentEarthquakes.getQuakes().size(); i ++)
		{
				currentEarthquakes.getQuakes().get(i).getTitle();
				currentEarthquakes.getQuakes().get(i).getTime();
				currentEarthquakes.getQuakes().get(i).getLatitude();
				currentEarthquakes.getQuakes().get(i).getLongitude();
				currentEarthquakes.getQuakes().get(i).getDepth();
				currentEarthquakes.getQuakes().get(i).getMagnitude();
				currentEarthquakes.getQuakes().get(i).getUrl();
			googleMap.addMarker(currentEarthquakes.getQuakes().get(i).getTitle(), new LatLon(
					currentEarthquakes.getQuakes().get(i).getLatitude(), currentEarthquakes.getQuakes().get(i).getLongitude()), false, null);
				

		}
		mainLayout.addComponent(googleMap);
	}
}
