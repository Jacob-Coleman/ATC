package edu.utc.atc.views;

import java.io.IOException;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

import edu.sc.seis.TauP.Arrival;
import edu.sc.seis.TauP.TauModelException;
import edu.utc.atc.ATCTime;
import edu.utc.atc.components.TimeCalcComponent;

public class TimeCalcView extends TimeCalcComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7323385200117791247L;


	public TimeCalcView()
	{
		//Sets the width of the slit panels in the TimeCalcComponent
		horizontalSplitPanel_1.setMaxSplitPosition(200, UNITS_PIXELS);
		horizontalSplitPanel_1.setMinSplitPosition(150, UNITS_PIXELS);
		
		//Initalizes the information in the Tabel
		resultsTable.addContainerProperty("Phase", Double.class, "none");
		resultsTable.addContainerProperty("Time", Double.class, "none");
		resultsTable.addContainerProperty("Distance", Double.class, "none");
		resultsTable.addContainerProperty("Ray Param", Double.class, "none");
		resultsTable.addContainerProperty("Ray Param Index", Double.class, "none");
		resultsTable.addContainerProperty("Name", String.class, "none");
		resultsTable.addContainerProperty("Purist Name", String.class, "none");
		resultsTable.addContainerProperty("Source Depth", Double.class, "none");
		resultsTable.addContainerProperty("Take Off Angle", Double.class, "none");
		resultsTable.addContainerProperty("Incident Angle", Double.class, "none");
		
		//Sets the possible models in the model box
		modelBox.addItem("iasp91");
		modelBox.addItem("prem");
		modelBox.addItem("ak135");
		modelBox.addItem("qdt");
		
		//Creats the action to be performed when the submit button is clicked 
		submitButton.addClickListener(new Button.ClickListener() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = -5638665006501170673L;

			public void buttonClick(ClickEvent event) {
				processForm();
		    }			
		});
		
		
	}
	
	
	private void processForm() {
		try {
    		System.out.println(modelBox.getValue().toString());
			@SuppressWarnings("unused")
			ATCTime atct = new ATCTime(Integer.parseInt(distanceField.getValue()),
									   Double.parseDouble(depthField.getValue()), 
									   modelBox.getValue().toString());
			
			
			BeanContainer<String, Arrival> arrivalTimes = new BeanContainer<String, Arrival>(Arrival.class);
			//BeanContainer<String, ArrivalForm>
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
			
			horizontalSplitPanel_1.removeComponent(resultsTable);
			resultsTable = new Table("results", arrivalTimes);
			resultsTable.setImmediate(false);
			resultsTable.setWidth("100.0%");
			resultsTable.setHeight("100.0%");
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
