package smartcar.hil.drivernotifyingservice;

import org.osgi.framework.BundleContext;

import es.upv.pros.iot.smartcar.services.interfaces.IDriverNotifyingService;

public class SmartCar_HiL_DriverNotifyingService implements IDriverNotifyingService {
	
	protected int timeout = 50; // s
	
	protected BundleContext context = null;

		
	
	public SmartCar_HiL_DriverNotifyingService(BundleContext context, String id) {
		this.context = context;

	}
	
	public void setTimeout(int seconds) {
		this.timeout = seconds;
	}
	
	public IDriverNotifyingService start() {
		// La magia empieza. Se recomienda crear un worker (Thread o similar) y que empiece
		//   a simular movimiento ACC.
		// Se dispone de un motor, de un sensor de distancia y de una distancia de seguridad ...
		
		return this;
	}
	
	public IDriverNotifyingService stop() {
		return this;
	}

}


