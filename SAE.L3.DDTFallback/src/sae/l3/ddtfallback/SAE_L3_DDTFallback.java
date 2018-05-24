package sae.l3.ddtfallback;

import org.osgi.framework.BundleContext;

import es.upv.pros.iot.smartcar.services.interfaces.IDDTFallback;

public class SAE_L3_DDTFallback implements IDDTFallback {
	
	protected int activation_distance = 50; // m
	
	protected BundleContext context = null;
		
	
	public SAE_L3_DDTFallback(BundleContext context, String id) {
		this.context = context;
	}
		
	public void setActivationDistance(int d) {
		this.activation_distance = d;
	}
	
	public IDDTFallback start() {
		
		//this.reg = this.context.registerService(IDDTFallback.class, this, this.properties);
		
		// La magia empieza. Se recomienda crear un worker (Thread o similar) y que empiece
		//   a simular movimiento ACC.
		// Se dispone de un motor, de un sensor de distancia y de una distancia de seguridad ...
		
		return this;
	}
	
	public IDDTFallback stop() {
		return this;
	}

}

