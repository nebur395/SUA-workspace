package sae.l3.ddtfallback;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.pros.iot.smartcar.services.interfaces.IDDTFallback;

public class SAE_L3_DDTFallback implements IDDTFallback {
	
	protected int activation_distance = 50; // m
	
	protected BundleContext context = null;
	protected ServiceRegistration reg = null;
	protected Dictionary<String, Object> properties = null;
		
	
	public SAE_L3_DDTFallback(BundleContext context, String id) {
		this.context = context;
		this.properties = new Hashtable<String, Object>();
		this.properties.put("id", id);
		this.properties.put("level", 1);
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
		if ( this.reg != null )
			this.reg.unregister();
		return this;
	}

}

