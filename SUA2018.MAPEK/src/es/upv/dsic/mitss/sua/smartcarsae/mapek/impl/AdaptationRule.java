package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class AdaptationRule implements IAdaptationRule {

	private BundleContext context;
	public AdaptationRule(BundleContext context) {
		this.context = context;
	}
	@Override
	public ISystemConfiguration executeRule(IEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected IAdaptiveReadyComponentConfigurator getARC(String id) {
		Collection<ServiceReference<IAdaptiveReadyComponentConfigurator>> refs = null;
		try {
			refs = this.context.getServiceReferences(IAdaptiveReadyComponentConfigurator.class, "(id=" + id + ")");
			return (IAdaptiveReadyComponentConfigurator) this.context.getService(refs.iterator().next());
		} catch (Exception e) {
			return null;
		}
	}

}
