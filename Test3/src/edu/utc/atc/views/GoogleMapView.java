package edu.utc.atc.views;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;

import edu.utc.atc.components.GoogleMapComponent;

public class GoogleMapView extends GoogleMapComponent {
	
	public GoogleMapView(){
		
		
		GoogleMap googleMap = new GoogleMap("apiKey", null, "en");
		googleMap.setSizeFull();
		googleMap.addMarker("DRAGGABLE: Paavo Nurmi Stadion", new LatLon(
		        60.442423, 22.26044), true, "VAADIN/1377279006_stadium.png");
		GoogleMapMarker kakolaMarker = googleMap.addMarker("DRAGGABLE: Kakolan vankila",
		        new LatLon(60.44291, 22.242415), true, null);
		googleMap.addMarker("NOT DRAGGABLE: Iso-Heikkilä", new LatLon(
		        60.450403, 22.230399), false, null);
		googleMap.setMinZoom( 4);
		googleMap.setMaxZoom( 16);
		
		
		horizontalSplitPanel_1.addComponent(googleMap);
		
	}
}
