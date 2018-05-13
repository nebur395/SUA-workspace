package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationAction;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class AdaptationAction implements IAdaptationAction {

	private EAdaptationAction currentAction;
	private IAdaptiveReadyComponentConfigurator currentComponent;
	
	public AdaptationAction (IAdaptiveReadyComponentConfigurator component, EAdaptationAction action) {
		this.currentComponent = component;
		this.currentAction = action;	
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
