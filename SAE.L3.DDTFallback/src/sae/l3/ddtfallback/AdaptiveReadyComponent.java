package sae.l3.ddtfallback;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.components.AdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public class AdaptiveReadyComponent extends AdaptiveReadyComponentConfigurator {
	
	public static String SS_DDTFallback = "DDTFallback";
	public static String PAR_ACTIVATIONDISTANCE = "ActivationDistance";
	
	
	protected SAE_L3_DDTFallback acc = null;
	protected Dictionary<String, Object> properties = null;
	protected ServiceRegistration<?> reg = null;
	public AdaptiveReadyComponent(BundleContext context) {
		super(context);
		this.properties = new Hashtable<String, Object>();
		this.acc = new SAE_L3_DDTFallback(context, this.getId());
	}

	@Override
	public IAdaptiveReadyComponentConfigurator deploy(ISystemComponentsManager theSystemComponentsManager) {
		super.deploy(theSystemComponentsManager);
		this.acc.start();
		try {
			this.properties.put("id", this.getId());
			this.properties.put("level", 3);
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
		if ( parameter.equalsIgnoreCase(PAR_ACTIVATIONDISTANCE) )
			this.acc.setActivationDistance((Integer)value);
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator bindService(String req, Object value) {

		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator unbindServices(String req) {

		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator unbindService(String req, Object value) {

		return this;
	}

	@Override
	public Object getServiceSupply(String serviceSupply) {
		if ( serviceSupply.equalsIgnoreCase(SS_DDTFallback) )
			return this.acc;
		return null;
	}

	public String getId() {
		return SS_DDTFallback;
	}

}
