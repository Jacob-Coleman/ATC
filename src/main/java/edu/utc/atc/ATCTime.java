package edu.utc.atc;
/**
 * @author Jacob D. Coleman
 * Used TauP_Time to calculate arrival times of waves and return a list of arrival times
 * Passes all branches.
 */

import edu.sc.seis.TauP.Arrival;
import edu.sc.seis.TauP.TauModelException;
import edu.sc.seis.TauP.TauP_Time;

import java.io.IOException;
import java.util.List;

public class ATCTime {

    private final TauP_Time atcTime;

    public ATCTime(double dist, double depth, String model) throws TauModelException, IOException {

        atcTime = new TauP_Time(model);

        atcTime.init();
        //atcTime.parsePhaseList("P,Pdiff,S,Sdiff,PKP,SKS");
        atcTime.parsePhaseList("p,s,P,S,Pn,Sn,PcP,ScS,Pdiff,Sdiff,PKP,SKS,PKiKP,SKiKS,PKIKP,SKIKS");
        atcTime.depthCorrect(depth);

        //For some reason the model has to be reset after being instantiated or it reverts to iasp91
        atcTime.loadTauModel(model);
        atcTime.calculate(dist);
        atcTime.getArrivals();


    }

    public List<Arrival> getTable() {

        return atcTime.getArrivals();

    }

}
