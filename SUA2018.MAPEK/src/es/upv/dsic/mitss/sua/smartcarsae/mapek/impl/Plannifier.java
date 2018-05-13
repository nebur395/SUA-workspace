package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.LinkedList;
import java.util.List;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationAction;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationPlan;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IExecutor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;

public class Plannifier implements IPlannifier {

	private IKnowledge knowledge;
	private IExecutor executor;
	
	@Override
	public void plan(ISystemConfiguration theSystemConfiguration) {
		List<IAdaptiveReadyComponentConfigurator> newActiveComponents = theSystemConfiguration.getAdaptiveReadyComponentList();
		List<IAdaptiveReadyComponentConfigurator> currentActiveComponents = this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList();
		List<IAdaptationAction> newAdaptationActionsList = new LinkedList<IAdaptationAction>();
		
		/*
		 * First iteration: It checks which of the current components have to be undeployed since they are
		 * not in the new system configuration.
		 */
		for (IAdaptiveReadyComponentConfigurator currentComponent: currentActiveComponents) {
			boolean componentExists = false;
			// Check if the current component is in the new configuration plan
			for (IAdaptiveReadyComponentConfigurator newComponent: newActiveComponents) {
				if (currentComponent.getId().equals(newComponent.getId())) {
					componentExists = true;
					//newComponent.
					break;
				}
			}
			
			if (!componentExists) {
				IAdaptationAction newAdaptationAction = new AdaptationAction(currentComponent, EAdaptationAction.undeploy);
				newAdaptationActionsList.add(newAdaptationAction);
			}
		}
		
		/*
		 * Second iteration: It checks which of the new components have to be deployed since they are
		 * not in the current system configuration.
		 */
		for (IAdaptiveReadyComponentConfigurator newComponent: newActiveComponents) {
			boolean componentDoesNotExist = true;
			// Check if the new component is in the current configuration plan
			for (IAdaptiveReadyComponentConfigurator currentComponent: currentActiveComponents) {
				if (currentComponent.getId().equals(newComponent.getId())) {
					componentDoesNotExist = false;
					//newComponent.
					break;
				}
			}
			
			if (componentDoesNotExist) {
				IAdaptationAction newAdaptationAction = new AdaptationAction(newComponent, EAdaptationAction.deploy);
				newAdaptationActionsList.add(newAdaptationAction);
			}
		}
		
		IAdaptationPlan newAdaptationPlan = new AdaptationPlan(newAdaptationActionsList);
		this.executor.execute(newAdaptationPlan);
	}
	
	public void setExecutor(IExecutor executor) {
		this.executor = executor;
	}
	
	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
	}

}
