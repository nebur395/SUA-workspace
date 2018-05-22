package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.List;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationAction;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationPlan;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IExecutor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class Executor implements IExecutor {

	private IKnowledge knowledge;
	
	@Override
	public void execute(IAdaptationPlan plan) {
		// TODO Auto-generated method stub
		List<IAdaptiveReadyComponentConfigurator> currentActiveComponents = this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList();
		for (IAdaptationAction n : plan.getActions()) {
			for (IAdaptiveReadyComponentConfigurator a : currentActiveComponents) {
				if(a.getId().equals(n.))
			}
		}
		 
		this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList() = currentActiveComponents;
	}

}
