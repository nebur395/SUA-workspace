package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationAction;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationPlan;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IExecutor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemComponentsManager.interfaces.ISystemComponentsManager;

public class Executor implements IExecutor {

	private IKnowledge knowledge;
	private BundleContext context;

	public Executor(BundleContext context) {
		this.context = context;
	}

	@Override
	public void execute(IAdaptationPlan plan) throws BundleException, InvalidSyntaxException {
		System.out.println("Recibido por el ejecutor: "+plan.getActions().size());
		// TODO Auto-generated method stub
		ServiceReference<?>[] refs = null;
		refs = this.context.getAllServiceReferences(IAdaptiveReadyComponentConfigurator.class.getName(), null);
//		this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList().clear();		
			for (IAdaptationAction n : plan.getActions()) {
				System.out.println(n.getCurrentComponent().getName());
					ServiceReference<?> a = this.context.getServiceReference(n.getCurrentComponent().getName());
					IAdaptiveReadyComponentConfigurator adaptiveReady = (IAdaptiveReadyComponentConfigurator) this.context
							.getService(a);
					if (adaptiveReady.getId().equals(n.getCurrentComponent().getId())) {
						if (n.getCurrentAction() == EAdaptationAction.deploy) {
							adaptiveReady.deploy((ISystemComponentsManager) this.knowledge.getCurrentSystemConfiguration());
						} else if (n.getCurrentAction() == EAdaptationAction.undeploy) {
							adaptiveReady.undeploy((ISystemComponentsManager) this.knowledge.getCurrentSystemConfiguration());
						}
					}
	//			this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList().add(n.getCurrentComponent());
			}
		this.knowledge.setCurrentAdaptionPlan(plan);
	}
	
	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
	}

}

//Hemos supuesto que (adaptiveReady.deploy() y adaptiveReady.undeploy) ya actualizan el systemConfiguration que le pasamos por argumento.
//No vemos conveniente remplazar la antigua por la actua, de todas manera hemos valorado esta opcion:

// this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList().clear();
// y luego
// this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList().add(n.getCurrentComponent()); 
