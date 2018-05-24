package sae.l3.trafficjamchauffer;

import org.osgi.framework.BundleContext;

import es.upv.pros.iot.smartcar.services.interfaces.ITrafficJamChauffer;

public class SAE_L3_TrafficJamChauffer implements ITrafficJamChauffer {
	
	protected int activation_distance = 50; // m
	
	protected BundleContext context = null;
		
	
	public SAE_L3_TrafficJamChauffer(BundleContext context, String id) {
		this.context = context;
	}
	
	public ITrafficJamChauffer start() {
		
		
		// La magia empieza. Se recomienda crear un worker (Thread o similar) y que empiece
		//   a simular movimiento ACC.
		// Se dispone de un motor, de un sensor de distancia y de una distancia de seguridad ...
		
		return this;
	}
	
	public ITrafficJamChauffer stop() {
		return this;
	}

}


