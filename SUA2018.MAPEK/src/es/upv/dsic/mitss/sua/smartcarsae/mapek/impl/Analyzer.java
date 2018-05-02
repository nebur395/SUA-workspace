package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;

public class Analyzer implements IAnalyzer {

	private IPlannifier plannifier;
	private IKnowledge knowledge;
	public Analyzer() {
		
	}
	
	@Override
	public void notifyEvent(IEvent event) {
		
	}
	
	public void setPlannifier(IPlannifier plannifier) {
		this.plannifier = plannifier;
	}
	
	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
	}
}
