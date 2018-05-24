package sua2018.mapek;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private AdaptiveReadyComponent arc = null;
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Hashtable<String, Object> props = new Hashtable<String, Object>();
		Activator.context = bundleContext;
		arc = new AdaptiveReadyComponent(bundleContext);
		props.put("id", arc.getId());
		arc.deploy(null);
		Activator.context.registerService(AdaptiveReadyComponent.class.getName(), arc, 
				props);
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
