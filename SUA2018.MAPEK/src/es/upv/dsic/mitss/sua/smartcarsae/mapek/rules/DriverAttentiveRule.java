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

public class DriverAttentiveRule extends AdaptationRule {

	private BundleContext context;
	private Dictionary<String, Object> properties = null;
	public DriverAttentiveRule(BundleContext context) {
		super(context);
		this.context = context;
		this.properties = new Hashtable<String, Object>();
		properties.put("rt", EMonitorRT.DriverAttentive);
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
			if(refs != null) {
				for (ServiceReference<?> ref : refs) {
					IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
					if(arcc.getId().contentEquals("SAE.L3.DDTFallback")) {
						IAdaptiveReadyComponentConfigurator arc_SAE_L1_ACC = this.getARC("SAE.L1.ACC");
						if(arc_SAE_L1_ACC != null) {
							servicesList.add(arc_SAE_L1_ACC);
						}
						break;
					}
				}
			}
			
			sysconfig.setAdaptiveReadyComponentList(servicesList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sysconfig;
	}

}
