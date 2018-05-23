package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationPlan;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class Knowledge implements IKnowledge {
	
	private ISystemConfiguration systemConfig;
	private IAdaptationPlan adaptionPlan;
	private BundleContext context;
	
	public Knowledge(BundleContext context) {
		this.context = context;
		List<IAdaptiveReadyComponentConfigurator> servicesList = new ArrayList<>();
		try {
			ServiceReference<?>[] refs = null;
			refs = this.context.getServiceReferences(IAdaptiveReadyComponentConfigurator.class.getName(), null);
			if(refs != null)
				for (ServiceReference<?> ref : refs) {
					IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
					servicesList.add(arcc);
				}
			SystemConfiguration sysconf = new SystemConfiguration();
			sysconf.setAdaptiveReadyComponentList(servicesList);
			this.systemConfig = sysconf;
		} catch(Exception e) {
			
		}
	}

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
