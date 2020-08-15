package edu.utc.atc.views;

/**
 * Implementation details for querying the USGS 
 * Takes in the users input in the date and text field
 * It then checks that data and builds the parameters
 * to pass to USGSConnect which pulls data from the USGS
 * 
 * Once that data is collected a QueryReader breaks the returned GeoJson
 * into multiple earthquakes which are then added to the results table
 * 
 * Once the earthquake data is collected session global variables of 
 * latitude
 * longitude
 * depth
 * distance
 * 
 *  
 */
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.util.BeanContainer;
import com.vaadin.v7.data.util.converter.StringToDateConverter;
import com.vaadin.server.Page;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import edu.utc.atc.ATCServlet;
import edu.utc.atc.Earthquake;
import edu.utc.atc.USGSConnect;
import edu.utc.atc.components.InputValidatorComponent;
import edu.utc.atc.components.QueryComponent;

@SuppressWarnings("deprecation")
public class QueryView extends QueryComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7581919752425898971L;
	private List<String> paramaterList;
	double myLat;
	double myLon;
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public QueryView() {
		horizontalSplitPanel_1.setMaxSplitPosition(275, Unit.PIXELS);
		horizontalSplitPanel_1.setMinSplitPosition(250, Unit.PIXELS);
			
		quakeTable.addContainerProperty("Title", String.class,"none");
		quakeTable.addContainerProperty("Time UTC", String.class,"none");
		quakeTable.addContainerProperty("Latitude", String.class,"none");
		quakeTable.addContainerProperty("Longitude", String.class,"none");
		quakeTable.addContainerProperty("Magnitude", String.class,"none");
		quakeTable.addContainerProperty("Depth", String.class,"none");
		quakeTable.addContainerProperty("URL", String.class,"none");

		
		// Set the date and time to present
		inlineDateField_2.setValue(new java.util.Date().toInstant().atZone(ZoneId.systemDefault())
			      .toLocalDate());
		
		submitButton.addClickListener(new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6584318836243927223L;
			private Notification message2;

			@Override
			public void buttonClick(ClickEvent event) {
				
				boolean isValidLat = false;
				boolean isValidLong = false;
				boolean isValidRadius = false;
				boolean isValidMax = false;
				
					
				isValidLat =  new InputValidatorComponent(latField.getValue(), -90, 90, "Below valid latitude",
																						"Above Valid latitude", 
																						"Plese enter a latitude", 
																						"Not a number in latitude box").getIsValid();
				
				if(isValidLat == true)
				isValidLong =  new InputValidatorComponent(longField.getValue(), -180, 180, "Below valid longitude", 
																							"Above valid longitude",
																							"Plese enter a longitude", 
																							"Not a number in longitude box").getIsValid();
				
				if (isValidLong == true)
				isValidRadius =  new InputValidatorComponent(radiusField.getValue(), -180, 180, "Below valid radius", 
																								"Above valid radius",
																								"Plese enter a radius", 
																								"Not a number in radius box").getIsValid();

				if(isValidRadius == true)
				isValidMax =  new InputValidatorComponent(minMagField.getValue(), 0, 10, "Below valid longitude",
																						 "Above valid magnitude",
																						 "Plese enter a magnitude", 
																						 "Not a number magnitude box").getIsValid();
				
				
				if(isValidLong == true && isValidLat == true && isValidRadius == true && isValidMax == true)
				{
					Notification message = new Notification("Querying USGS",Notification.Type.TRAY_NOTIFICATION);
					
					paramaterList = new ArrayList<String>();
					
					myLat = Double.parseDouble(latField.getValue());
					myLon = Double.parseDouble(longField.getValue());
					
					
					paramaterList.add(processDate(Date.from(inlineDateField_2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())));
					paramaterList.add("latitude=" + latField.getValue());
					paramaterList.add("longitude=" + longField.getValue());
					paramaterList.add("maxradius=" + radiusField.getValue());
					paramaterList.add("minmagnitude=" + minMagField.getValue());
					
				
					
					try {
						try {
							processForm();
							
							
							
							
						} catch (JSONException e) {
							message2 = new Notification("Can not connect to USGS",Notification.Type.WARNING_MESSAGE);

						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						message2 = new Notification("Can not connect to USGS",Notification.Type.WARNING_MESSAGE);
					}
					
					message.show(Page.getCurrent());
				}
			}
			
		});
	}
	
	public String paramaterBuilder(List<String> paramaterList)
	{
		String paramaterString = "";
		
		for(int i = 0; i < paramaterList.size(); i ++)
		{
			paramaterString = paramaterString +"&" + paramaterList.get(i);
		}
		
		return paramaterString;
	}
	
	public String processDate(Date date){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
		
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(date);
		endCal.setTime(date);
		
		
		return "starttime=" + dateFormat.format(date.getTime())+
			   "&endtime=" 	+ dateFormat.format(date.getTime()) +"T23:59:59";
		
		//return "starttime=" 	+ dateFormat.format(startCal.getTime());
	}
	
	private void processForm() throws IOException, JSONException
	{
		
		USGSConnect currentEarthquakes = new USGSConnect(paramaterBuilder(paramaterList));
		
		
		
		BeanContainer<String, Earthquake> earthquakes = new BeanContainer<String, Earthquake>(Earthquake.class);
		
		//Iterates though the time table and adds each arrival time to the table
		for(int i = 0; i < currentEarthquakes.getQuakes().size(); i ++)
		{
			earthquakes.setBeanIdProperty("title");
			earthquakes.addBean(new Earthquake(currentEarthquakes.getQuakes().get(i).getTitle(),
											currentEarthquakes.getQuakes().get(i).getTime(),
											currentEarthquakes.getQuakes().get(i).getLatitude(),
											currentEarthquakes.getQuakes().get(i).getLongitude(),
											currentEarthquakes.getQuakes().get(i).getDepth(),
											currentEarthquakes.getQuakes().get(i).getMagnitude(),
											currentEarthquakes.getQuakes().get(i).getUrlLink(),
											currentEarthquakes.getQuakes().get(i).getUrlString()
											)
								);
			
		}
		
		//Removes current table from view
		System.out.println(quakeTable.getContainerDataSource().getClass());

		//OLD RMOVES TABLE
		//horizontalSplitPanel_1.removeComponent(quakeTable);
		
		//creates a new table with current values
		//OLD REMOVES TABLE
		//quakeTable = new Table("results", earthquakes);
		//NEW JUST UPDATES THE DTA SOURCE
		quakeTable.setContainerDataSource(earthquakes);
	
		
		//sets the values and order of values to show in the table
		quakeTable.setVisibleColumns(new Object[] {"title","time", "latitude","longitude","magnitude","depth","urlLink"});
		
		
		//Sets the names of the values to diaply in the table columns
		quakeTable.setColumnHeader("title", "Title");
		quakeTable.setColumnHeader("time", "Time (UTC)");
		quakeTable.setColumnHeader("latitude", "Latitude");
		quakeTable.setColumnHeader("longitude", "Longitude");
		quakeTable.setColumnHeader("magnitude", "Magnitude");
		quakeTable.setColumnHeader("depth", "Depth");
		quakeTable.setColumnHeader("urlLink", "URL");
		quakeTable.setConverter("time", new StringToDateConverter(){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 3808456648054033190L;

			@Override
			public DateFormat getFormat(Locale locale){
				return new SimpleDateFormat("MMM d, yyyy   HH:mm:ss.S");
			}
		});
		
		quakeTable.setSelectable(true);
		quakeTable.setImmediate(true);
	
		final Label current = new Label ("Selected:-");
		quakeTable.addValueChangeListener(new Property.ValueChangeListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 8308842280856556093L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				current.setValue("Selected: " + quakeTable.getValue());
				//System.out.println(quakeTable.getValue());
				if(quakeTable.getValue()!= null){
					System.out.println(quakeTable.getValue());
					
					Property<Double> latitudeProperty  = quakeTable.getContainerProperty(quakeTable.getValue(), "latitude");
					((ATCServlet)UI.getCurrent()).setLatitude(latitudeProperty.getValue());
					
					Property<Double> longitudeProperty  = quakeTable.getContainerProperty(quakeTable.getValue(), "longitude");
					((ATCServlet)UI.getCurrent()).setLongitude(longitudeProperty.getValue());
					
					Property<Double> depthProperty  = quakeTable.getContainerProperty(quakeTable.getValue(), "depth");
					((ATCServlet)UI.getCurrent()).setDepth(depthProperty.getValue());
					
					Property<Date> dateProperty = quakeTable.getContainerProperty(quakeTable.getValue(), "time");
					((ATCServlet)UI.getCurrent()).setDate(dateProperty.getValue());
					
					calculateDistance(latitudeProperty.getValue(),longitudeProperty.getValue());
					
					
				}
				
			}
		});
		
		
		
		quakeTable.setWidth("100.0%");
		quakeTable.setHeight("100.0%");
		quakeTabsheet.addTab(quakeTable, "Table");
		//Replaces component instead of updating current this is because markers would not update without changing tabs 
		quakeTabsheet.replaceComponent(quakeTabsheet.getTab(1).getComponent(), new MapView(currentEarthquakes,myLat,myLon,Double.parseDouble(radiusField.getValue())));
		
		
		
		
	}

	
	private double calculateDistance(double quakeLat, double quakeLon){
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
	
	
	
	
}
