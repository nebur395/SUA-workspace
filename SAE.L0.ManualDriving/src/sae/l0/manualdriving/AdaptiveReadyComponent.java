package sae.l0.manualdriving;

import org.osgi.framework.BundleContext;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.components.AdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public class AdaptiveReadyComponent extends AdaptiveReadyComponentConfigurator {
	
	public static String SS_ManualDriving = "ManualDriving";
	
	
	protected SAE_L0_ManualDriving acc = null;
	
	public AdaptiveReadyComponent(BundleContext context) {
		super(context);
		this.acc = new SAE_L0_ManualDriving(context, this.getId());
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
		if ( serviceSupply.equalsIgnoreCase(SS_ManualDriving) )
			return this.acc;
		return null;
	}


}
