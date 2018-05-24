package smartcar.starter;

import java.util.Collection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public class Activator_Example implements BundleActivator {

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
		Activator_Example.context = bundleContext;

		// SmartCar
		IAdaptiveReadyComponentConfigurator arc_SmartCar = this.getARC("SmartCar");
		if (arc_SmartCar != null) {
			arc_SmartCar.setParameter("smartcar.id", System.getProperty("smartcar"));
			arc_SmartCar.setParameter("smartcar.cruiser.speed", "90");
			arc_SmartCar.deploy(this.getSystemComponentsManager());
		}

		// SmartCar.PhysicalDevicesConnector.Serial
		IAdaptiveReadyComponentConfigurator arc_PhysicalDevicesConnector_Serial = this
				.getARC("SmartCar.PhysicalDevicesConnector.Serial");
		if (arc_PhysicalDevicesConnector_Serial != null) {
			arc_PhysicalDevicesConnector_Serial.setParameter("physicalconnection.serial.port",
					System.getProperty("serial.port"));
			arc_PhysicalDevicesConnector_Serial.setParameter("physicalconnection.serial.bauds",
					System.getProperty("serial.bauds"));
			if (System.getProperty("serial.commandqueue") != null)
				arc_PhysicalDevicesConnector_Serial.setParameter("physicalconnection.commandqueue",
						System.getProperty("serial.commandqueue"));
			arc_PhysicalDevicesConnector_Serial.bindService("physicalDeviceConnector.smartcar",
					arc_SmartCar.getServiceSupply("smartcar"));
			arc_PhysicalDevicesConnector_Serial.deploy(this.getSystemComponentsManager());
		}

		// SmartCar.Device.Engine
		IAdaptiveReadyComponentConfigurator arc_Device_Engine = this.getARC("SmartCar.Device.Engine");
		if (arc_Device_Engine != null) {
			arc_Device_Engine.setParameter("engine.id", "engine");
			arc_Device_Engine.deploy(this.getSystemComponentsManager());
		}

		// SmartCar.Device.Steering
		IAdaptiveReadyComponentConfigurator arc_Device_Steering = this.getARC("SmartCar.Device.Steering");
		if (arc_Device_Steering != null) {
			arc_Device_Steering.setParameter("steering.id", "steering");
			arc_Device_Steering.deploy(this.getSystemComponentsManager());
		}

		// SmartCar.Device.FrontalDistanceSensor
		IAdaptiveReadyComponentConfigurator arc_Device_FDS = this.getARC("SmartCar.Device.FrontalDistanceSensor");
		if (arc_Device_FDS != null) {
			arc_Device_FDS.setParameter("frontaldistancesensor.id", "fdistance");
			arc_Device_FDS.deploy(this.getSystemComponentsManager());
		}

		// SmartCar.Device.RearDistanceSensor
		IAdaptiveReadyComponentConfigurator arc_Device_RDS = this.getARC("SmartCar.Device.RearDistanceSensor");
		if (arc_Device_RDS != null) {
			arc_Device_RDS.setParameter("reardistancesensor.id", "rdistance");
			arc_Device_RDS.deploy(this.getSystemComponentsManager());
		}

		// SmartCar.Device.FrontalCollisionSensor
		IAdaptiveReadyComponentConfigurator arc_Device_FCS = this.getARC("SmartCar.Device.FrontalCollisionSensor");
		if (arc_Device_FCS != null) {
			arc_Device_FCS.setParameter("frontalcollisionsensor.id", "fcollision");
			arc_Device_FCS.deploy(this.getSystemComponentsManager());
		}

		// SmartCar.Device.RearCollisionSensor
		IAdaptiveReadyComponentConfigurator arc_Device_RCS = this.getARC("SmartCar.Device.RearCollisionSensor");
		if (arc_Device_RCS != null) {
			arc_Device_RCS.setParameter("rearcollisionsensor.id", "rcollision");
			arc_Device_RCS.deploy(this.getSystemComponentsManager());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator_Example.context = null;
	}

	protected IAdaptiveReadyComponentConfigurator getARC(String id) {
		Collection<ServiceReference<IAdaptiveReadyComponentConfigurator>> refs = null;
		try {
			refs = Activator_Example.context.getServiceReferences(IAdaptiveReadyComponentConfigurator.class, "(id=" + id + ")");
			return (IAdaptiveReadyComponentConfigurator) Activator_Example.context.getService(refs.iterator().next());

		} catch (InvalidSyntaxException e) {
			return null;
		}
	}

	public ISystemComponentsManager getSystemComponentsManager() {
		if (theSystemComponentsManager != null)
			return theSystemComponentsManager;

		ServiceReference<ISystemComponentsManager> sr = Activator_Example.context
				.getServiceReference(ISystemComponentsManager.class);
		if (sr != null) {
			theSystemComponentsManager = Activator_Example.context.getService(sr);
			return theSystemComponentsManager;
		}
		return null;
	}

}
