package sae.l1.acc;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected AdaptiveReadyComponent arc = null;

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
		arc.deploy(null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	protected IAdaptiveReadyComponentConfigurator getARC(String id) {
		Collection<ServiceReference<IAdaptiveReadyComponentConfigurator>> refs = null;
		try {
			refs = Activator.context.getServiceReferences(IAdaptiveReadyComponentConfigurator.class, "(id=" + id + ")");
			return (IAdaptiveReadyComponentConfigurator) Activator.context.getService(refs.iterator().next());
		} catch (Exception e) {
			return null;
		}
	}
}
