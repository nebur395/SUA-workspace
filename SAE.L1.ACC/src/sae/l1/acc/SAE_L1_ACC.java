package sae.l1.acc;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.pros.iot.smartcar.device.distancesensor.interfaces.IDistanceSensor;
import es.upv.pros.iot.smartcar.device.engine.interfaces.IEngine;
import es.upv.pros.iot.smartcar.services.interfaces.IAutoPilot;

public class SAE_L1_ACC implements IAutoPilot {
	
	protected IEngine engine = null;
	protected IDistanceSensor fds =  null;
	protected int security_distance = 50; // m
	
	protected BundleContext context = null;
	protected ServiceRegistration reg = null;
	protected Dictionary<String, Object> properties = null;
		
	
	public SAE_L1_ACC(BundleContext context, String id) {
		this.context = context;
		this.properties = new Hashtable<String, Object>();
		this.properties.put("id", id);
		this.properties.put("level", 1);
	}
		
	public void setSecurityDistance(int d) {
		this.security_distance = d;
	}
	
	public void setEngine(IEngine engine) {
		this.engine =  engine;
	}
	
	public void setFrontalDistanceSensor(IDistanceSensor sensor) {
		this.fds = sensor;
	}
	
	public IAutoPilot start() {
		
		this.reg = this.context.registerService(IAutoPilot.class, this, this.properties);
		
		// La magia empieza. Se recomienda crear un worker (Thread o similar) y que empiece
		//   a simular movimiento ACC.
		// Se dispone de un motor, de un sensor de distancia y de una distancia de seguridad ...
		
		return this;
	}
	
	public IAutoPilot stop() {
		if ( this.reg != null )
			this.reg.unregister();
		return this;
	}

}
