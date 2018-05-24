package sae.l0.manualdriving;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private AdaptiveReadyComponent arc;
	protected Dictionary<String, Object> properties = null;
	protected ServiceRegistration<?> reg = null;
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		arc = new AdaptiveReadyComponent(bundleContext);
		try {
			this.properties = new Hashtable<String, Object>();
			this.properties.put("id", arc.getId());
			this.properties.put("level", 0);
			this.reg = Activator.context.registerService(IAdaptiveReadyComponentConfigurator.class.getName(), arc, this.properties);
		} catch(Exception e) {
			e.printStackTrace();
		}
		arc.deploy(null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		if ( this.arc != null ) {
			this.arc.undeploy(null);
		}
		Activator.context = null;
	}

}
