package edu.utc.atc.views;
/**
 * Creates a google map for the QueryView
 */

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import edu.utc.atc.ATCServlet;
import edu.utc.atc.USGSConnect;
import edu.utc.atc.components.MapComponent;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.addon.leaflet.*;

import java.util.ArrayList;
import java.util.List;

public class MapView extends MapComponent {

    /**
     *
     */
    private static final long serialVersionUID = 3044805767275418705L;
    //GoogleMapMarker stationMarker;
    LMap leafletMap;
    LMarker stationMarker;
    Double stationLat;
    Double stationLon;
    List<LMarker> markers;

    // Generic empty map for initialization or no data to show.
    public MapView() {
        leafletMap = new LMap();
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
    public MapView(USGSConnect ce, double stationLat, double stationLon, double radius) {
        int zoomLevel = 0;
        leafletMap = new LMap();
        LTileLayer osmTiles = new LOpenStreetMapLayer();
        leafletMap.addBaseLayer(osmTiles, "OSM");

        leafletMap.setCenter(39.8282, -98.5795);
        leafletMap.setZoomLevel(15);
        leafletMap.setSizeFull();

        addStation(stationLat, stationLon);
        addQuakes(ce);


        if (radius <= 10)
            zoomLevel = 5;
        else if (radius <= 20)
            zoomLevel = 4;
        else if (radius <= 60)
            zoomLevel = 3;
        else if (radius <= 120)
            zoomLevel = 2;
        else if (radius <= 180)
            zoomLevel = 1;

        leafletMap.setZoomLevel(zoomLevel);


        mainLayout.addComponent(leafletMap);
    }

    //Adds the coordinates for the seismic station.
    public void addStation(double stationLat, double stationLon) {
        System.out.println(leafletMap);
        leafletMap.setCenter(stationLat, stationLon);
        this.stationLat = stationLat;
        this.stationLon = stationLon;

        //StationMarker
        stationMarker = new LMarker(stationLat, stationLon);
        stationMarker.setIcon("S");
        stationMarker.setIconPathFill("#239657");
        stationMarker.setCaption("Station");
        stationMarker.addClickListener(new LeafletClickListener() {

            @Override
            public void onClick(LeafletClickEvent event) {
                // TODO Auto-generated method stub
                Notification stationMessage = new Notification("Seismic Station", Notification.Type.TRAY_NOTIFICATION);
                stationMessage.setDescription("Coordinates (" + stationLat + "," + stationLon + ")");

                stationMessage.setDelayMsec(10000);
                stationMessage.show(Page.getCurrent());
            }

        });
        leafletMap.addComponent(stationMarker);

    }

    //Adds each point for an earthquake with relevant data to be displayed when selected
    public void addQuakes(USGSConnect currentEarthquakes) {
        for (int i = 0; i < currentEarthquakes.getQuakes().size(); i++) {
            final int temp = i;
            currentEarthquakes.getQuakes().get(i).getTitle();
            currentEarthquakes.getQuakes().get(i).getTime();
            currentEarthquakes.getQuakes().get(i).getLatitude();
            currentEarthquakes.getQuakes().get(i).getLongitude();
            currentEarthquakes.getQuakes().get(i).getDepth();
            currentEarthquakes.getQuakes().get(i).getMagnitude();
            currentEarthquakes.getQuakes().get(i).getUrlLink();

            LMarker currentMarker = new LMarker(currentEarthquakes.getQuakes().get(i).getLatitude(), currentEarthquakes.getQuakes().get(i).getLongitude());
            currentMarker.addClickListener(new LeafletClickListener() {

                @Override
                public void onClick(LeafletClickEvent event) {
                    // TODO Auto-generated method stub
                    ((ATCServlet) UI.getCurrent()).setLatitude(currentEarthquakes.getQuakes().get(temp).getLatitude());

                    ((ATCServlet) UI.getCurrent()).setLongitude(currentEarthquakes.getQuakes().get(temp).getLongitude());

                    ((ATCServlet) UI.getCurrent()).setDepth(currentEarthquakes.getQuakes().get(temp).getDepth());
                    ((ATCServlet) UI.getCurrent()).setDate(currentEarthquakes.getQuakes().get(temp).getTime());

                    calculateDistance(currentEarthquakes.getQuakes().get(temp).getLatitude(),
                            currentEarthquakes.getQuakes().get(temp).getLongitude(),
                            stationLat,
                            stationLon);
                    Notification message = new Notification("Selected \n"
                            , Notification.Type.TRAY_NOTIFICATION);
                    message.setDescription(currentEarthquakes.getQuakes().get(temp).getTitle()
                            + "<br> " + currentEarthquakes.getQuakes().get(temp).getTime()
                            + "<br> Coordinates (" + currentEarthquakes.getQuakes().get(temp).getLatitude()
                            + " ," + currentEarthquakes.getQuakes().get(temp).getLongitude()
                            + ")<br> Magnitude " + currentEarthquakes.getQuakes().get(temp).getMagnitude()
                            + "<br> Depth " + currentEarthquakes.getQuakes().get(temp).getDepth()
                            + "<br> <a href=" + currentEarthquakes.getQuakes().get(temp).getUrlString() + " target=" + "_blank" + ">Details</a>"
                    );
                    message.setHtmlContentAllowed(true);
                    message.setDelayMsec(10000);
                    message.show(Page.getCurrent());

                }
            });
            currentMarker.setCaption(currentEarthquakes.getQuakes().get(i).getTitle());
            currentMarker.setIcon(StringUtils.substringBefore(currentEarthquakes.getQuakes().get(i).getMagnitude(), "."));


            markers = new ArrayList<LMarker>();
            markers.add(currentMarker);
            leafletMap.addComponent(currentMarker);

        }

    }

    //calculates the distance between the selected earthquake and the seismic station that is selected
    private double calculateDistance(double quakeLat, double quakeLon, double myLat, double myLon) {
        double dist = rad2deg(Math.acos(Math.cos(deg2rad(90 - myLat))
                * Math.cos(deg2rad(90 - quakeLat))
                + Math.sin(deg2rad(90 - myLat))
                * Math.sin(deg2rad(90 - quakeLat))
                * Math.cos(deg2rad(myLon - quakeLon)))

        );

        ((ATCServlet) UI.getCurrent()).setDistance(dist);


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

    public void removeAllMarkers() {

        leafletMap.removeAllComponents();

    }

    public LMap getMap() {
        return leafletMap;
    }

}
