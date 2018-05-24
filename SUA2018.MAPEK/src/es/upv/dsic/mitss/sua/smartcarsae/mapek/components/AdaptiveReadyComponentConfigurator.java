package es.upv.dsic.mitss.sua.smartcarsae.mapek.components;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public abstract class AdaptiveReadyComponentConfigurator implements IAdaptiveReadyComponentConfigurator {

	protected BundleContext context = null;
	protected Dictionary<String, Object> properties = null;
	protected ServiceRegistration<?> reg = null;

	public AdaptiveReadyComponentConfigurator(BundleContext context) {
		this.context = context;
		this.properties = new Hashtable<String, Object>();
	}
	
	public String getId() {
		return this.context.getBundle().getSymbolicName();
	}

	@Override
	public IAdaptiveReadyComponentConfigurator start() {
		this.properties.put("id", this.context.getBundle().getSymbolicName());
		this.properties.put("prop", "sua");
		this.reg = this.context.registerService(IAdaptiveReadyComponentConfigurator.class.getName(), this,
				this.properties);
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator stop() {
		if (this.reg != null)
			this.reg.unregister();
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator deploy(ISystemComponentsManager theSystemComponentsManager) {
		/*
		 * Gestionamos el estado 'started' del ARC Modificamos el valor el registro de propiedades OSGi para: 
		 * 	- permitir búsquedas de IAdaptiveReadyComponentConfigurator con (started=true) 
		 * 	- añadir ServiceListeners para monitorizar si algún componente cambia sus propiedades (ServiceListener.MODIFIED)
		 */
		this.properties.put("started", true);
		if (this.reg != null)
			this.reg.setProperties(this.properties);
		return this;
	}

	@Override
	public IAdaptiveReadyComponentConfigurator undeploy(ISystemComponentsManager theSystemComponentsManager) {
		/*
		 * Gestionamos el estado 'started' del ARC Modificamos el valor el registro de propiedades OSGi para: 
		 * 	- permitir búsquedas de IAdaptiveReadyComponentConfigurator con (started=false) 
		 * - añadir ServiceListeners para monitorizar si algún componente cambia sus propiedades (ServiceListener.MODIFIED)
		 */
		this.properties.put("started", false);
		if (this.reg != null)
			this.reg.setProperties(this.properties);
		return this;
	}

}
