package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.List;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IExecutor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;

public class Plannifier implements IPlannifier {

	private IKnowledge knowledge;
	private IExecutor executor;
	
	@Override
	public void plan(ISystemConfiguration theSystemConfiguration) {
		List<IAdaptiveReadyComponentConfigurator> newActiveComponents = theSystemConfiguration.getAdaptiveReadyComponentList();
		List<IAdaptiveReadyComponentConfigurator> actualActiveComponents = this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList();
		
		for (IAdaptiveReadyComponentConfigurator component: actualActiveComponents) {
			
		}
	}
	
	public void setExecutor(IExecutor executor) {
		this.executor = executor;
	}
	
	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
	}

}
