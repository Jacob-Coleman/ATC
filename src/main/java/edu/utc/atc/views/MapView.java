package edu.utc.atc.views;
/**
 * Creates a google map for the QueryView
 */

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.LOpenStreetMapLayer;
import org.vaadin.addon.leaflet.LTileLayer;

import com.vaadin.server.Page;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import edu.utc.atc.ATCServlet;
import edu.utc.atc.USGSConnect;
import edu.utc.atc.components.MapComponent;

public class MapView extends MapComponent {
	
	GoogleMapMarker stationMarker;
	GoogleMap googleMap;
	Double stationLat;
	Double stationLon;
	List<GoogleMapMarker> markers;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044805767275418705L;
	
	// Generic empty map for initialization or no data to show. 
	public MapView( ) {
		LMap leafletMap = new LMap();
		LTileLayer osmTiles = new LOpenStreetMapLayer();
		leafletMap.addBaseLayer(osmTiles, "OSM");
		
		leafletMap.setCenter(39.8282, -98.5795);
		leafletMap.setZoomLevel(15);
		leafletMap.setSizeFull();
		
		
		
		//googleMap = new GoogleMap("apiKey", null, "english");
		//googleMap.setSizeFull();
		//googleMap.setCenter(new LatLon( 39.8282, -98.5795));
				
		mainLayout.addComponent(leafletMap);
		
	}
	//Creates a google map with points for earthquakes.
	public MapView(USGSConnect ce, double stationLat, double stationLon,double radius ) {
		int zoomLevel = 0;
		googleMap = new GoogleMap("apiKey", null, "english");
		googleMap.setSizeFull();
		
		addStation(stationLat, stationLon);
		addQuakes(ce);
		
	
		if(radius <= 10)
			zoomLevel = 5;
		else if(radius <= 20)
			zoomLevel = 4;
		else if(radius <= 60)
			zoomLevel = 3;
		else if(radius <= 120)
			zoomLevel = 2;
		else if(radius <= 180)
			zoomLevel = 1;
		
		googleMap.setZoom(zoomLevel);
		
		
		mainLayout.addComponent(googleMap);
	}
	//Adds the coordinates for the seismic station.
	public void addStation(double stationLat, double stationLon) {
		System.out.println(googleMap);
		googleMap.setCenter(new LatLon( stationLat, stationLon));
		this.stationLat = stationLat;
		this.stationLon = stationLon;
		
		stationMarker = new GoogleMapMarker ("Seismic Station", new LatLon(
		        stationLat, stationLon), false, null);
		stationMarker.setIconUrl("http://labs.google.com/ridefinder/images/mm_20_blue.png");
		
		stationMarker.setId(-1);
		
		googleMap.addMarker(stationMarker);
		
	}
	//Adds each point for an earthquake with relevant data to be displayed when selected
	public void addQuakes(USGSConnect currentEarthquakes){
		for(int i = 0; i < currentEarthquakes.getQuakes().size(); i ++)
		{
				currentEarthquakes.getQuakes().get(i).getTitle();
				currentEarthquakes.getQuakes().get(i).getTime();
				currentEarthquakes.getQuakes().get(i).getLatitude();
				currentEarthquakes.getQuakes().get(i).getLongitude();
				currentEarthquakes.getQuakes().get(i).getDepth();
				currentEarthquakes.getQuakes().get(i).getMagnitude();
				currentEarthquakes.getQuakes().get(i).getUrlLink();
				
				GoogleMapMarker currentMarker = new GoogleMapMarker (currentEarthquakes.getQuakes().get(i).getTitle(), new LatLon(
						currentEarthquakes.getQuakes().get(i).getLatitude(), currentEarthquakes.getQuakes().get(i).getLongitude()), false, null);
				
				currentMarker.setCaption(currentEarthquakes.getQuakes().get(i).getTitle());
				
				currentMarker.setId(i);
				
				markers = new ArrayList<GoogleMapMarker>();
				markers.add(currentMarker);
				googleMap.addMarker(currentMarker);
				
			

		}
		googleMap.addMarkerClickListener(new MarkerClickListener() {
			
            /**
			 * 
			 */
			private static final long serialVersionUID = 2625401361527147267L;

			@Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
				System.out.println("marker clicked");
			
				if(clickedMarker.getId() != stationMarker.getId()){
            	
	            	int selectedEqrthQuakesIndex =  (int) clickedMarker.getId();
	            	
	            	((ATCServlet)UI.getCurrent()).setLatitude(currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getLatitude());
					
					((ATCServlet)UI.getCurrent()).setLongitude(currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getLongitude());
					
					((ATCServlet)UI.getCurrent()).setDepth(currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getDepth());
					((ATCServlet)UI.getCurrent()).setDate(currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getTime());
					
					calculateDistance(currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getLatitude(),
							          currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getLongitude(),
									  stationLat,
									  stationLon);
					Notification message = new Notification ("Selected \n"
															,Notification.Type.TRAY_NOTIFICATION);
					message.setDescription( currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getTitle()
															+"<br> "+currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getTime()
															+"<br> Coordinates ("+currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getLatitude()
															+" ,"+currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getLongitude()
															+")<br> Magnitude "+currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getMagnitude()
															+"<br> Depth "+currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getDepth()
															+ "<br> <a href="+currentEarthquakes.getQuakes().get(selectedEqrthQuakesIndex).getUrlString()+" target="+"_blank"+">Details</a>"
															);
					message.setHtmlContentAllowed(true);
					message.setDelayMsec(10000);
					message.show(Page.getCurrent());
				
				
				}else if(clickedMarker.getId() == stationMarker.getId())
				{
					Notification stationMessage = new Notification ("Seismic Station",Notification.Type.TRAY_NOTIFICATION);
					stationMessage.setDescription("Coordinates (" + stationLat + ","+ stationLon +")");
								
					stationMessage.setDelayMsec(10000);
					stationMessage.show(Page.getCurrent());
					
				}
				
            }
        });
	}

	//calculates the distance between the selected earthquake and the seismic station that is selected
	private double calculateDistance(double quakeLat, double quakeLon, double myLat , double myLon){
		double dist = rad2deg(Math.acos( Math.cos(deg2rad(90-myLat))
								* Math.cos(deg2rad(90-quakeLat))
								+ Math.sin(deg2rad(90 - myLat))
								* Math.sin(deg2rad(90-quakeLat))
								* Math.cos(deg2rad(myLon-quakeLon)))

				);
		
		((ATCServlet)UI.getCurrent()).setDistance(dist);
		
		
	    System.out.println("Distance " + dist);

		return dist;
	}

		
	//This function converts decimal degrees to radians
	private double deg2rad(double deg) {
	  return (deg * Math.PI / 180.0);
	}
	 
	//This function converts radians to decimal degrees
	private double rad2deg(double rad) {
	  return (rad * 180 / Math.PI);

	}
	public void removeAllMarkers(){
		
		googleMap.clearMarkers();
		googleMap.markAsDirty();
				
	}
	public GoogleMap getMap(){
		return googleMap;
	}

}
