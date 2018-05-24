package sua2018.mapek;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import sae.l1.acc.AdaptiveReadyComponent;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private IAdaptiveReadyComponentConfigurator arc = null;

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
		arc.start();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		if ( this.arc != null ) {
			this.arc.stop();
		}
		Activator.context = null;
	}

}
