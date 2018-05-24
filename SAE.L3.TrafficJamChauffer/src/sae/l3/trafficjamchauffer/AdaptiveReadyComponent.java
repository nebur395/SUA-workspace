package sae.l3.trafficjamchauffer;

import java.util.Hashtable;

import org.osgi.framework.BundleContext;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.components.AdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public class AdaptiveReadyComponent extends AdaptiveReadyComponentConfigurator {
	
	public static String SS_TrafficJamChauffer = "TrafficJamChauffer";
	public static String PAR_ACTIVATIONDISTANCE = "ActivationDistance";
	
	
	protected SAE_L3_TrafficJamChauffer acc = null;
	public AdaptiveReadyComponent(BundleContext context) {
		super(context);
		this.properties = new Hashtable<String, Object>();
		this.acc = new SAE_L3_TrafficJamChauffer(context, this.getId());
	}

	@Override
	public IAdaptiveReadyComponentConfigurator deploy(ISystemComponentsManager theSystemComponentsManager) {
		super.deploy(theSystemComponentsManager);
		this.acc.start();
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator undeploy(ISystemComponentsManager theSystemComponentsManager) {
		super.undeploy(theSystemComponentsManager);
		this.acc.stop();
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator setParameter(String parameter, Object value) {
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
		if ( serviceSupply.equalsIgnoreCase(SS_TrafficJamChauffer) )
			return this.acc;
		return null;
	}

}
