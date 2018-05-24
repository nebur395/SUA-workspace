package smartcar.starter;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public class Activator implements BundleActivator {

	private static BundleContext context;
	protected ISystemComponentsManager theSystemComponentsManager = null;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;


		// SmartCar.Device.Engine
		IAdaptiveReadyComponentConfigurator arc_Device_Engine = this.getARC("SmartCar.Device.Engine");

		// SmartCar.Device.FrontalDistanceSensor
		IAdaptiveReadyComponentConfigurator arc_Device_FDS = this.getARC("SmartCar.Device.FrontalDistanceSensor");

		// SmartCar
		IAdaptiveReadyComponentConfigurator arc_SAE_L1_ACC = this.getARC("SAE.L1.ACC");
		if (arc_SAE_L1_ACC != null) {
			arc_SAE_L1_ACC.bindService("Engine", arc_Device_Engine);
			arc_SAE_L1_ACC.bindService("DistanceSensor", arc_Device_FDS);
			arc_SAE_L1_ACC.setParameter("SecurityDistance", 100);
			arc_SAE_L1_ACC.deploy(null);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
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
