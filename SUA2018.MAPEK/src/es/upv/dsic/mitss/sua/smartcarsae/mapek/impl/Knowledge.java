package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationPlan;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;

public class Knowledge implements IKnowledge {
	
	private ISystemConfiguration systemConfig;
	private IAdaptationPlan adaptionPlan;

	@Override
	public ISystemConfiguration getCurrentSystemConfiguration() {
		// TODO Auto-generated method stub
		return this.systemConfig;
	}

	@Override
	public void setCurrentSystemConfiguration(ISystemConfiguration systemConfig) {
		this.systemConfig = systemConfig;
		// TODO Auto-generated method stub
		
	}

	@Override
	public IAdaptationPlan getCurrentAdaptionPlan() {
		// TODO Auto-generated method stub
		return this.adaptionPlan;
	}

	@Override
	public void setCurrentAdaptionPlan(IAdaptationPlan adaptionPlan) {
		// TODO Auto-generated method stub
		this.adaptionPlan = adaptionPlan;
		
	}
	
	

}
