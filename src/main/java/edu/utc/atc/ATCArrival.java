package edu.utc.atc;
/**
 * This is an arrival formated for the vaadin table as a bean
 * It is used in the TimeCalcView table
 */

import edu.sc.seis.TauP.Arrival;
import edu.sc.seis.TauP.SeismicPhase;

import java.util.Date;

public class ATCArrival extends Arrival {

    protected Date arrivalTime;


    public ATCArrival(SeismicPhase phase,
                      double time,
                      double dist,
                      double rayParam,
                      int rayParamIndex,
                      String name,
                      String puristName,
                      double sourceDepth,
                      double takeoffAngle,
                      double incidentAngle,
                      Date arrivalTime) {

        super(phase, time, dist, rayParam, rayParamIndex, name, puristName,
                sourceDepth, takeoffAngle, incidentAngle);
        // TODO Auto-generated constructor stub
        this.arrivalTime = arrivalTime;
    }

    public Date getArrival() {
        return arrivalTime;
    }

    public void setArrival(Date arrival) {
        arrivalTime = arrival;
    }

}
