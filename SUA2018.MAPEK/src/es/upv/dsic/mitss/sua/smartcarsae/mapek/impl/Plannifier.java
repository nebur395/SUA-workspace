package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.LinkedList;
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
	
		
		for (IAdaptiveReadyComponentConfigurator actualComponent: actualActiveComponents) {
			boolean componentExists = false;
			
			// Check if the actual component is in the new configuration plan
			for (IAdaptiveReadyComponentConfigurator newComponent: newActiveComponents) {
				if (actualComponent.getId().equals(newComponent.getId())) {
					componentExists = true;
					//newComponent.
					break;
				}
			}
			
			if (componentExists) {
				// agregar a la lista el componente que se activa
			}
		}
	}
	
	public void setExecutor(IExecutor executor) {
		this.executor = executor;
	}
	
	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
	}

}
