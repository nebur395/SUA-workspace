package sae.l0.manualdriving;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.pros.iot.smartcar.services.interfaces.IManualDriving;

public class SAE_L0_ManualDriving implements IManualDriving {
	
	protected BundleContext context = null;
	protected ServiceRegistration reg = null;
	protected Dictionary<String, Object> properties = null;
		
	
	public SAE_L0_ManualDriving(BundleContext context, String id) {
		this.context = context;
		this.properties = new Hashtable<String, Object>();
		this.properties.put("id", id);
		this.properties.put("level", 0);
	}
	
	public IManualDriving start() {
		
		this.reg = this.context.registerService(IManualDriving.class, this, this.properties);
		
		// La magia empieza. Se recomienda crear un worker (Thread o similar) y que empiece
		//   a simular movimiento ACC.
		// Se dispone de un motor, de un sensor de distancia y de una distancia de seguridad ...
		
		return this;
	}
	
	public IManualDriving stop() {
		if ( this.reg != null )
			this.reg.unregister();
		return this;
	}

}

