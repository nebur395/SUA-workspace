package sae.l1.acc;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.components.AdaptiveReadyComponentConfigurator;
import es.upv.pros.iot.smartcar.device.distancesensor.interfaces.IDistanceSensor;
import es.upv.pros.iot.smartcar.device.engine.interfaces.IEngine;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public class AdaptiveReadyComponent extends AdaptiveReadyComponentConfigurator {
	
	public static String SR_ENGINE = "Engine";
	public static String SR_DISTANCESENSOR = "DistanceSensor";
	public static String SS_AUTOPILOT = "AutoPilot";
	public static String PAR_SECURITYDISTANCE = "SecurityDistance";
	
	
	protected SAE_L1_ACC acc = null;
	protected Dictionary<String, Object> properties = null;
	protected ServiceRegistration<?> reg = null;
	public AdaptiveReadyComponent(BundleContext context) {
		super(context);
		this.properties = new Hashtable<String, Object>();
		this.acc = new SAE_L1_ACC(context, this.getId());
	}

	@Override
	public IAdaptiveReadyComponentConfigurator deploy(ISystemComponentsManager theSystemComponentsManager) {
		super.deploy(theSystemComponentsManager);
		this.acc.start();
		try {
			this.properties.put("id", this.getId());
			this.properties.put("level", 1);
			this.reg = this.context.registerService(IAdaptiveReadyComponentConfigurator.class.getName(), this, this.properties);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator undeploy(ISystemComponentsManager theSystemComponentsManager) {
		super.undeploy(theSystemComponentsManager);
		this.acc.stop();
		if ( this.reg != null )
			this.reg.unregister();
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator setParameter(String parameter, Object value) {
		if ( parameter.equalsIgnoreCase(PAR_SECURITYDISTANCE) )
			this.acc.setSecurityDistance((Integer)value);
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator bindService(String req, Object value) {
		if ( req.equalsIgnoreCase(SR_ENGINE) )
			this.acc.setEngine((IEngine)value);
		else if ( req.equalsIgnoreCase(SR_DISTANCESENSOR) )
			this.acc.setFrontalDistanceSensor((IDistanceSensor) value);
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator unbindServices(String req) {
		this.acc.setEngine(null);
		this.acc.setFrontalDistanceSensor(null);
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator unbindService(String req, Object value) {
		if ( req.equalsIgnoreCase(SR_ENGINE) )
			this.acc.setEngine(null);
		else if ( req.equalsIgnoreCase(SR_DISTANCESENSOR) )
			this.acc.setFrontalDistanceSensor(null);
		return this;
	}

	@Override
	public Object getServiceSupply(String serviceSupply) {
		if ( serviceSupply.equalsIgnoreCase(SS_AUTOPILOT) )
			return this.acc;
		return null;
	}

	public String getId() {
		return SS_AUTOPILOT;
	}
}
