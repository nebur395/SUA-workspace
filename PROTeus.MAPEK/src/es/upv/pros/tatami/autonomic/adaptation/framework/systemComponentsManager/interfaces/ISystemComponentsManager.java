package es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces;

import java.util.ArrayList;
import java.util.Hashtable;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.exceptions.NotFoundException;

public interface ISystemComponentsManager {
	
	public final String MANAGING_TECHNOLOGY = "es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.ManagingTechnology"; 
	
	public void setManagingTechnology(String managingTechnology);
	public String getManagingTechnology();
	
	public void register();
	public void unregister();
	

	public ISystemComponent deploy(String componentToDeploy, String version, ArrayList<Hashtable<String, String>> deploymentParameterValues, ArrayList<Hashtable<String, String>> deploymentServiceBindings) throws NotFoundException;

	public void undeploy(String componentToUndeploy, String version) throws NotFoundException;

	public void bindService(String componentRequiringService, String serviceRequirement, String componentSupplyingService, String serviceSupply) throws NotFoundException;

	public void unbindService(String componentRequiringService, String serviceRequirement, String componentSupplyingService, String serviceSupply) throws NotFoundException;

	public void setParameter(String componentToConfigure, String parameterName, Object parameterValue) throws NotFoundException;
}
