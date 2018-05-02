package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;

public class Analyzer implements IAnalyzer {

	private IPlannifier plannifier;
	
	public Analyzer() {
		
	}
	
	@Override
	public void notifyEvent(IEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public void setPlannifier(IPlannifier plannifier) {
		this.plannifier = plannifier;
	}

}
