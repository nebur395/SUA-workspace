package es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public interface IAdaptiveReadyComponentConfigurator {

	public IAdaptiveReadyComponentConfigurator deploy(ISystemComponentsManager theSystemComponentsManager);
	public IAdaptiveReadyComponentConfigurator undeploy(ISystemComponentsManager theSystemComponentsManager);
	public IAdaptiveReadyComponentConfigurator setParameter(String parameter, Object value);
	public IAdaptiveReadyComponentConfigurator bindService(String req, Object value);
	public IAdaptiveReadyComponentConfigurator unbindServices(String req);
	public IAdaptiveReadyComponentConfigurator unbindService(String req, Object value);

	public Object getServiceSupply(String serviceSupply);
	// public List<Object> getAllServicesSupply(String serviceSupply);

	public IAdaptiveReadyComponentConfigurator start();
	public IAdaptiveReadyComponentConfigurator stop();

}