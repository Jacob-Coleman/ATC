package edu.utc.atc.views;
/**
 * Maintains the views and setting passing the global
 * variables to the views when a tab is clicked
 */

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.UI;
import edu.utc.atc.ATCServlet;
import edu.utc.atc.components.TabComponent;

import java.util.Date;

public class TabView extends TabComponent {

    /**
     *
     */
    private static final long serialVersionUID = -8927740681632038714L;
    private final QueryView qv;
    private TimeCalcView tcv;
    private Date currentDate;

    public TabView() {
        this.tabSheet_MainTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            /**
             *
             */
            private static final long serialVersionUID = 4440033646804739740L;

            public void selectedTabChange(SelectedTabChangeEvent event) {

                //This is for the first run so that the variables get set on the tab
                processVariables();


                //This performs checking so that when a user processes arrival times and then selects a different earthquakes
                //the arrival times and chart are cleared so the users does not confuse what data is being presented to them
                if (currentDate != null && currentDate.equals(((ATCServlet) UI.getCurrent()).getDate()) == false) {

                    tabSheet_Tab2.removeComponent(tcv);
                    tcv = new TimeCalcView();
                    tabSheet_Tab2.addComponent(tcv);

                    processVariables();


                }
                currentDate = ((ATCServlet) UI.getCurrent()).getDate();

            }
        });

        //Adds View to the first tab on the tab sheet
        qv = new QueryView();
        tabSheet_Tab1.addComponent(qv);
        //Adds View to the second tab on the tab sheet
        tcv = new TimeCalcView();
        tabSheet_Tab2.addComponent(tcv);


    }

    private void processVariables() {
        //Sets properties in TimeCalcView component and updates each time the ATC view is clicked in the tab
        tcv.setDistance(((ATCServlet) UI.getCurrent()).getDistance());
        tcv.setDepth(((ATCServlet) UI.getCurrent()).getDepth());
        tcv.setLatitude(((ATCServlet) UI.getCurrent()).getLatitude());
        tcv.setLongitude(((ATCServlet) UI.getCurrent()).getLongitude());
        tcv.setDateField(((ATCServlet) UI.getCurrent()).getDate());
    }
}
