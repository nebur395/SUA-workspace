package es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.EAdaptationAction;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public interface IAdaptationAction {

	public void execute();
	public EAdaptationAction getCurrentAction();
	public void setCurrentAction(EAdaptationAction currentAction);
	public IAdaptiveReadyComponentConfigurator getCurrentComponent();
	public void setCurrentComponent(IAdaptiveReadyComponentConfigurator currentComponent);

}
