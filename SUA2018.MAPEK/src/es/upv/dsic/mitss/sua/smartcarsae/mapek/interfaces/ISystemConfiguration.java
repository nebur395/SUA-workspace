package es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces;

import java.util.List;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public interface ISystemConfiguration {
	public List<IAdaptiveReadyComponentConfigurator> getAdaptiveReadyComponentList();
	public void setAdaptiveReadyComponentList(List<IAdaptiveReadyComponentConfigurator> servicesList);
}
