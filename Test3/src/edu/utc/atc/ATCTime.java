package edu.utc.atc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.sc.seis.TauP.*;
public class ATCTime {
	
	private TauP_Time atcTime;
	public ATCTime (int distance, double depth, String model) throws TauModelException, IOException
	{
		
		atcTime = new TauP_Time(model);
		
		atcTime.init();
		atcTime.parsePhaseList("P,Pdiff,S,Sdiff,PKP,SKS");
		atcTime.depthCorrect(depth);
		
		//For some reason the model has to be reset after being instantiated or it reverts to iasp91
		atcTime.loadTauModel(model);
		atcTime.calculate(distance);
		atcTime.getArrivals();
		
		
	}
	
	public List<Arrival> getTable() {
		
		return atcTime.getArrivals();
		
	}

}
