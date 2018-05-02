package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.ArrayList;
import java.util.List;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.components.AdaptiveReadyComponentConfigurator;

public class SystemConfiguration {
	private List<AdaptiveReadyComponentConfigurator> activeComponents;
	
	public SystemConfiguration() {
		activeComponents = new ArrayList<>();
	}
	
	public void addActiveComponent(AdaptiveReadyComponentConfigurator component) {
		this.activeComponents.add(component);
	}
	
	public void removeActiveComponent(AdaptiveReadyComponentConfigurator component) {
		this.activeComponents.remove(component);
	}
}
