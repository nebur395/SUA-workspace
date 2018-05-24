package sae.l3.highwaychauffer;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.pros.iot.smartcar.services.interfaces.IHighwayChauffer;

public class SAE_L3_HighwayChauffer implements IHighwayChauffer {
	
	protected int activation_distance = 50; // m
	
	protected BundleContext context = null;
	protected ServiceRegistration reg = null;
	protected Dictionary<String, Object> properties = null;
		
	
	public SAE_L3_HighwayChauffer(BundleContext context, String id) {
		this.context = context;
		this.properties = new Hashtable<String, Object>();
		this.properties.put("id", id);
		System.out.println(id);
		this.properties.put("level", 3);
	}
	
	public IHighwayChauffer start() {
		//try {
		//	this.reg = this.context.registerService(IHighwayChauffer.class, this, this.properties);
		//} catch(Exception e) {
		//	e.printStackTrace();
		//}
		// La magia empieza. Se recomienda crear un worker (Thread o similar) y que empiece
		//   a simular movimiento ACC.
		// Se dispone de un motor, de un sensor de distancia y de una distancia de seguridad ...
		
		return this;
	}
	
	public IHighwayChauffer stop() {
		if ( this.reg != null )
			this.reg.unregister();
		return this;
	}

}


