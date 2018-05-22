package smartcar.hil.drivernotifyingservice;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.pros.iot.smartcar.services.interfaces.IDriverNotifyingService;

public class SmartCar_HiL_DriverNotifyingService implements IDriverNotifyingService {
	
	protected int timeout = 50; // s
	
	protected BundleContext context = null;
	protected ServiceRegistration reg = null;
	protected Dictionary<String, Object> properties = null;
		
	
	public SmartCar_HiL_DriverNotifyingService(BundleContext context, String id) {
		this.context = context;
		this.properties = new Hashtable<String, Object>();
		this.properties.put("id", id);
		this.properties.put("level", 1);
	}
	
	public void setTimeout(int seconds) {
		this.timeout = seconds;
	}
	
	public IDriverNotifyingService start() {
		
		this.reg = this.context.registerService(IDriverNotifyingService.class, this, this.properties);
		
		// La magia empieza. Se recomienda crear un worker (Thread o similar) y que empiece
		//   a simular movimiento ACC.
		// Se dispone de un motor, de un sensor de distancia y de una distancia de seguridad ...
		
		return this;
	}
	
	public IDriverNotifyingService stop() {
		if ( this.reg != null )
			this.reg.unregister();
		return this;
	}

}


