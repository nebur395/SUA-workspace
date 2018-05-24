package simulation.console.commands;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected ServiceRegistration<?> commandProvReg = null;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		Hashtable<String, Object> props = new Hashtable<String, Object>();
		props.put("osgi.command.scope", "SmartCarSUA");
		props.put("osgi.command.function", new String[] { 
				"printCSC", 
				"approachingCity",
				"enteringHighway"
				});
		this.commandProvReg = Activator.context.registerService(MyCommandProvider.class.getName(),
				new MyCommandProvider(Activator.context), props);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
