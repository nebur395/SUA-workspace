package es.upv.dsic.mitss.sua.smartcarsae.mapek.rules;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.AdaptationRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.SystemConfiguration;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.EMonitorRT;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class DriverUnattentiveRule extends AdaptationRule {

	private BundleContext context;
	private Dictionary<String, Object> properties = null;
	public DriverUnattentiveRule(BundleContext context) {
		super(context);
		this.context = context;
		this.properties = new Hashtable<String, Object>();
		properties.put("rt", EMonitorRT.DriverDistracted);
		this.context.registerService(IAdaptationRule.class.getName(), this, properties);
	}

	@Override
	public ISystemConfiguration executeRule(IEvent event) {
		ISystemConfiguration sysconfig = new SystemConfiguration();
		try {
			ServiceReference<?>[] refs = null;
			refs = this.context.getAllServiceReferences(IAdaptiveReadyComponentConfigurator.class.getName(),
					"(started=true)");
			List<IAdaptiveReadyComponentConfigurator> servicesList = new ArrayList<>();
			int level = 0;
			if(refs != null) {
				for (ServiceReference<?> ref : refs) {
					IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
					if(arcc.getId().contentEquals("SAE.L3.HighwayChauffer") || 
							arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer")) {
						level = 3;
						IAdaptiveReadyComponentConfigurator arc_DNS = this.getARC(arcc.getId());
						if(arc_DNS != null) {
							arc_DNS.setParameter("Timeout", 0);
							servicesList.add(arc_DNS);
						}
					}
				}
			}
			if(level == 3) {				
				IAdaptiveReadyComponentConfigurator arc_DNS = this.getARC("SmartCar.HiL.DriverNotifyingService");
				if(arc_DNS != null) {
					arc_DNS.setParameter("Timeout", 0);
					servicesList.add(arc_DNS);
				}
			}
			sysconfig.setAdaptiveReadyComponentList(servicesList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sysconfig;
	}

}
