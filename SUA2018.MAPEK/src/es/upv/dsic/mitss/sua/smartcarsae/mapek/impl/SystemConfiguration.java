package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.ArrayList;
import java.util.List;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.components.AdaptiveReadyComponentConfigurator;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class SystemConfiguration implements ISystemConfiguration {
	private List<AdaptiveReadyComponentConfigurator> activeComponents;
	
	public SystemConfiguration() {
		activeComponents = new ArrayList<>();
	}

	@Override
	public List<IAdaptiveReadyComponentConfigurator> getAdaptiveReadyComponentList() {
		// TODO Auto-generated method stub
		return null;
	}
}
