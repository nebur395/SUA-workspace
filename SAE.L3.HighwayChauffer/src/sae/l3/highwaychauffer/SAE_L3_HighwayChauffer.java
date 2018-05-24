package sae.l3.highwaychauffer;

import org.osgi.framework.BundleContext;

import es.upv.pros.iot.smartcar.services.interfaces.IHighwayChauffer;

public class SAE_L3_HighwayChauffer implements IHighwayChauffer {
	
	protected int activation_distance = 50; // m
	
	protected BundleContext context = null;
		
	
	public SAE_L3_HighwayChauffer(BundleContext context, String id) {
		this.context = context;
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
		return this;
	}

}


