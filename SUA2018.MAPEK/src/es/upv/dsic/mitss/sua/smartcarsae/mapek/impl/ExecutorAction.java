package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class ExecutorAction {

	private AdaptationActions currentAction;
	private IAdaptiveReadyComponentConfigurator currentComponent;
	
	public ExecutorAction() { }
	
	public ExecutorAction(IAdaptiveReadyComponentConfigurator component, AdaptationActions action) {
		this.currentComponent = component;
		this.currentAction = action;
	}
	
	public void setAction(AdaptationActions action) {
		this.currentAction = action;
	}
	
	public void setComponent(IAdaptiveReadyComponentConfigurator component) {
		this.currentComponent = component;
	}
	
	public AdaptationActions getAction() {
		return this.currentAction;
	}
	
	public IAdaptiveReadyComponentConfigurator getComponent() {
		return this.currentComponent;
	}
}
