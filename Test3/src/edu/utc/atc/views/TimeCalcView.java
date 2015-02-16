package edu.utc.atc.views;
/**
 * @version 1.0 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Jacob D. Coleman
 */


import java.io.IOException;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

import edu.sc.seis.TauP.Arrival;
import edu.sc.seis.TauP.TauModelException;
import edu.utc.atc.ATCTime;
import edu.utc.atc.components.InputValidatorComponent;
import edu.utc.atc.components.TimeCalcComponent;

public class TimeCalcView extends TimeCalcComponent {

	
	private static final long serialVersionUID = 7323385200117791247L;


	public TimeCalcView()
	{
		//Sets the width of the slit panels in the TimeCalcComponent
		horizontalSplitPanel_1.setMaxSplitPosition(200, UNITS_PIXELS);
		horizontalSplitPanel_1.setMinSplitPosition(150, UNITS_PIXELS);
		
		//Initializes the information in the Table
		resultsTable.addContainerProperty("Name", String.class, "none");
		resultsTable.addContainerProperty("Ray Param", Double.class, "none");
		resultsTable.addContainerProperty("Time", Double.class, "none");
		 
		
		
		//Sets the possible models in the model box
		modelBox.addItem("iasp91");
		modelBox.addItem("prem");
		modelBox.addItem("ak135");
		modelBox.addItem("qdt");
		
		//Creates the action to be performed when the submit button is clicked 
		submitButton.addClickListener(new Button.ClickListener() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = -5638665006501170673L;

			public void buttonClick(ClickEvent event) {
				
				boolean isValidDistance = false;
				boolean isValidDepth = false;
				boolean isValidModel = false;
				
				isValidDistance =  new InputValidatorComponent(distanceField.getValue(), -180, 180, "Below valid distance","Above valid distance", "Plese enter a distance", "Not a number").getIsValid();
				if(isValidDistance == true)
				isValidDepth =  new InputValidatorComponent(depthField.getValue(), 0, 6378, "Below valid depth","Above valid depth", "Plese enter a depth", "Not a number").getIsValid();
				
				
				//Checks if a model box is selected 
				if(isValidDepth == true && isValidDistance == true && modelBox.getValue() == null)
				{
					Notification distanceError = new Notification("Please select a model",Notification.TYPE_WARNING_MESSAGE);
					distanceError.show(Page.getCurrent());
					isValidModel = false;
				}
				
				//if all information is properly filled then process will occur
				if (isValidDistance == true && isValidDepth == true && isValidModel == true ){
					processForm();
				}
		    }			
		});
		
		
	}
	
	
	private void processForm() {
		try {
    		System.out.println(modelBox.getValue().toString());
    		
			ATCTime atct = new ATCTime(Double.parseDouble(distanceField.getValue()),
									   Double.parseDouble(depthField.getValue()), 
									   modelBox.getValue().toString());
			
			
			BeanContainer<String, Arrival> arrivalTimes = new BeanContainer<String, Arrival>(Arrival.class);
			
			//Iterates though the time table and adds each arrival time to the table
			for(int i = 0; i < atct.getTable().size(); i ++)
			{
				arrivalTimes.setBeanIdProperty("name");
				arrivalTimes.addBean(new Arrival(atct.getTable().get(i).getPhase(),
												 atct.getTable().get(i).getTime(),
												 atct.getTable().get(i).getDist(),
												 atct.getTable().get(i).getRayParam(),
												 atct.getTable().get(i).getRayParamIndex(),
												 atct.getTable().get(i).getName(),
												 atct.getTable().get(i).getPuristName(),
												 atct.getTable().get(i).getSourceDepth(),
												 atct.getTable().get(i).getTakeoffAngle(),
												 atct.getTable().get(i).getIncidentAngle()));
			}
			
			//read adds the updated results table
			horizontalSplitPanel_1.removeComponent(resultsTable);
			resultsTable = new Table("results", arrivalTimes);
			resultsTable.setImmediate(false);
			resultsTable.setWidth("100.0%");
			resultsTable.setHeight("100.0%");
			
			//selects the values from the arrival to be displayed in the table
			resultsTable.setVisibleColumns(new Object[]{"name","rayParamDeg", "time"});
			
			//formats the tables name per the item in the column being added
			resultsTable.setColumnHeader("name", "Name");
			//resultsTable.setColumnHeader("dist", "Dist (km)");
			resultsTable.setColumnHeader("rayParamDeg", "Ray Param");
			resultsTable.setColumnHeader("time", "Time");
			
			horizontalSplitPanel_1.addComponent(resultsTable);
			System.out.println("Table Size is now " + resultsTable.size());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Bad Number");
		} catch (TauModelException e) {
			// TODO Auto-generated catch block
			System.out.println("No Model");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO");
		}

	}
	

	
	
	
}
