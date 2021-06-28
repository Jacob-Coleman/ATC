package edu.utc.atc.views;
/**
 * @version 1.0
 * <p>
 * Implementation details on how the TimeCalculation occurs.
 * Uses the TimeCalcCompoent for its layout.
 * <p>
 * The data can be passed from the QueryView
 * or the data can be entered manually in this view
 * <p>
 * The input fields are checked for legal parameters
 * Once all fields have legal parameters
 * <p>
 * Passes collected values to ATC_Time that processes the data to return
 * the travel times of the different wave types
 * <p>
 * ATC_Time returns a list of arrival times which are displayed in the results table
 * and in the chart
 * @author Jacob D. Coleman
 */


import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.v7.data.util.BeanContainer;
import com.vaadin.v7.data.util.converter.StringToDateConverter;
import com.vaadin.v7.shared.ui.datefield.Resolution;
import com.vaadin.v7.ui.Table;
import edu.sc.seis.TauP.Arrival;
import edu.sc.seis.TauP.TauModelException;
import edu.utc.atc.ATCArrival;
import edu.utc.atc.ATCServlet;
import edu.utc.atc.ATCTime;
import edu.utc.atc.components.ChartComponent;
import edu.utc.atc.components.InputValidatorComponent;
import edu.utc.atc.components.TimeCalcComponent;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@SuppressWarnings("deprecation")
public class TimeCalcView extends TimeCalcComponent {


    private static final long serialVersionUID = 7323385200117791247L;


    public TimeCalcView() {

        //Sets the width of the slit panels in the TimeCalcComponent
        horizontalSplitPanel_1.setMaxSplitPosition(210, Unit.PIXELS);
        horizontalSplitPanel_1.setMinSplitPosition(250, Unit.PIXELS);
        verticalSplitPanel_1.setSplitPosition(350, Unit.PIXELS);
        popupDateField_1.setDateFormat("HH:mm:ss.S");

        //Initializes the information in the Table
        resultsTable.addContainerProperty("Name", String.class, "none");
        resultsTable.addContainerProperty("Ray Param", Double.class, "none");
        resultsTable.addContainerProperty("Time UTC", Double.class, "none");

        //Sets the possible models in the model box
        modelBox.addItem("iasp91");
        modelBox.addItem("prem");
        modelBox.addItem("ak135");
        modelBox.addItem("qdt");
        modelBox.setTextInputAllowed(false);
        modelBox.setNullSelectionAllowed(false);


        //Creates the action to be performed when the submit button is clicked
        submitButton.addClickListener(new Button.ClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = -5638665006501170673L;

            public void buttonClick(ClickEvent event) {

                boolean isValidDistance = false;
                boolean isValidDepth = false;
                boolean isValidModel = true;
                boolean isValidDate = true;


                //Checks for valid distance and gives error codes if they exists
                isValidDistance = new InputValidatorComponent(distanceField.getValue(), -180, 180, "Below valid distance", "Above valid distance", "Plese enter a distance", "Not a number").getIsValid();

                //Checks for valid distance and gives error codes if they exists
                if (isValidDistance == true)
                    isValidDepth = new InputValidatorComponent(depthField.getValue(), 0, 6378, "Below valid depth", "Above valid depth", "Plese enter a depth", "Not a number").getIsValid();


                //Checks if a model box is selected
                if (isValidDepth == true && isValidDistance == true && modelBox.getValue() == null) {

                    Notification distanceError = new Notification("Please select a model", Notification.Type.WARNING_MESSAGE);
                    distanceError.show(Page.getCurrent());
                    isValidModel = false;
                }
                //Check if an earthquake date is collected
                if (isValidDepth == true && isValidDistance == true && isValidModel && ((ATCServlet) UI.getCurrent()).getDate() == null) {
                    isValidDate = false;
                }
                if (popupDateField_1.getValue() == null)
                    isValidDate = false;


                /**
                 * if all information is properly filled then process will occur
                 * if a date is  included the first if runs and process the form with a date ( an earthquake was selected from the queryview)
                 * if a date is not included then the form is processed without the date ( data is manually entered)
                 *
                 **/
                if (isValidDistance == true && isValidDepth == true && isValidModel == true && isValidDate == true) {
                    processFormWithDate();
                    Notification message = new Notification("Processed with a selected earthquake", Notification.Type.TRAY_NOTIFICATION);
                    message.show(Page.getCurrent());
                } else if (isValidDistance == true && isValidDepth == true && isValidModel == true && isValidDate == false) {
                    processFormWithoutDate();
                    Notification message = new Notification("Processed without a selected earthquake", Notification.Type.TRAY_NOTIFICATION);
                    message.show(Page.getCurrent());
                }
            }
        });
        popupDateField_1.setResolution(Resolution.SECOND);

    }


    private void processFormWithDate() {
        try {
            System.out.println(modelBox.getValue().toString());

            ATCTime atct = new ATCTime(Double.parseDouble(distanceField.getValue()),
                    Double.parseDouble(depthField.getValue()),
                    modelBox.getValue().toString());

            ArrayList<Date> chartTimes = new ArrayList<Date>();
            ArrayList<String> chartNames = new ArrayList<String>();


            BeanContainer<String, ATCArrival> arrivalTimes = new BeanContainer<String, ATCArrival>(ATCArrival.class);

            //Iterates though the time table and adds each arrival time to the table
            for (int i = 0; i < atct.getTable().size(); i++) {
                double time = atct.getTable().get(i).getTime();
                System.out.println(time);
                Date date = ((ATCServlet) UI.getCurrent()).getDate();
                System.out.println(date.getTime());

                //convert travel time in seconds to milliseconds then add that to the millisecond time long for the arrival time in a date
                long sum = (long) (date.getTime() + (time * 1000));

                System.out.println(sum);
                Date newDate = new Date();
                newDate.setTime(sum);
                System.out.println(newDate);
                arrivalTimes.setBeanIdProperty("name");
                arrivalTimes.addBean(new ATCArrival(atct.getTable().get(i).getPhase(),
                        atct.getTable().get(i).getTime(),
                        atct.getTable().get(i).getDist(),
                        atct.getTable().get(i).getRayParam(),
                        atct.getTable().get(i).getRayParamIndex(),
                        atct.getTable().get(i).getName(),
                        atct.getTable().get(i).getPuristName(),
                        atct.getTable().get(i).getSourceDepth(),
                        atct.getTable().get(i).getTakeoffAngle(),
                        atct.getTable().get(i).getIncidentAngle(),
                        newDate)
                );
                chartTimes.add(newDate);
                chartNames.add(atct.getTable().get(i).getName());


            }


            //read adds the updated results table
            resultsTable.setContainerDataSource(arrivalTimes);

            //selects the values from the arrival to be displayed in the table
            resultsTable.setVisibleColumns("name", "rayParamDeg", "time", "arrival");

            //formats the tables name per the item in the column being added
            resultsTable.setColumnHeader("name", "Name");

            //resultsTable.setColumnHeader("dist", "Dist (km)");
            resultsTable.setColumnHeader("rayParamDeg", "Ray Param");
            resultsTable.setColumnHeader("time", "Time (s)");
            resultsTable.setColumnHeader("arrival", "Arrival Time (UTC)");

            //Formats date to show milliseconds
            resultsTable.setConverter("arrival", new StringToDateConverter() {
                private static final long serialVersionUID = 2590997315265667139L;

                @Override
                public DateFormat getFormat(Locale locale) {
                    return new SimpleDateFormat("MMM d, yyyy   HH:mm:ss.S");
                }
            });
            //Updates chart
            verticalSplitPanel_1.removeComponent(chartComponent_1);
            chartComponent_1 = new ChartComponent(popupDateField_1.getValue(), chartTimes, chartNames);
            verticalSplitPanel_1.addComponent(chartComponent_1);


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


    private void processFormWithoutDate() {
        try {
            System.out.println(modelBox.getValue().toString());

            ATCTime atct = new ATCTime(Double.parseDouble(distanceField.getValue()),
                    Double.parseDouble(depthField.getValue()),
                    modelBox.getValue().toString());


            BeanContainer<String, Arrival> arrivalTimes = new BeanContainer<String, Arrival>(Arrival.class);

            //Iterates though the time table and adds each arrival time to the table
            for (int i = 0; i < atct.getTable().size(); i++) {
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
            verticalSplitPanel_1.removeComponent(resultsTable);
            verticalSplitPanel_1.removeComponent(chartComponent_1);


            resultsTable = new Table("results", arrivalTimes);
            resultsTable.setImmediate(false);
            resultsTable.setWidth("100.0%");
            resultsTable.setHeight("100.0%");

            //selects the values from the arrival to be displayed in the table
            resultsTable.setVisibleColumns("name", "rayParamDeg", "time");

            //formats the tables name per the item in the column being added
            resultsTable.setColumnHeader("name", "Name");
            resultsTable.setColumnHeader("rayParamDeg", "Ray Param");
            resultsTable.setColumnHeader("time", "Time (s)");

            //clears the chart since there are no arrival times to show
            chartComponent_1 = new ChartComponent();
            verticalSplitPanel_1.addComponent(resultsTable);
            verticalSplitPanel_1.addComponent(chartComponent_1);

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

    public void setDistance(double d) {
        distanceField.setValue(Double.toString(d));
    }

    public void setDepth(double d) {
        depthField.setValue(Double.toString(d));
    }

    public void setLatitude(double lat) {
        latField.setValue(Double.toString(lat));
    }

    public void setLongitude(double lon) {
        lonField.setValue(Double.toString(lon));
    }

    public void setDateField(Date date) {
        popupDateField_1.setValue(date);
    }


}
