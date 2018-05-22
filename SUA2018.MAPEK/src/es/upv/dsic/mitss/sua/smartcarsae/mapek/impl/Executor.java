package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		ServiceReference<?>[] refs = null;
		refs = this.context.getAllServiceReferences(IAdaptiveReadyComponentConfigurator.class.getName(), "");
		for (IAdaptationAction n : plan.getActions()) {
			for (ServiceReference<?> a : refs) {
				IAdaptiveReadyComponentConfigurator adaptiveReady = (IAdaptiveReadyComponentConfigurator) this.context
						.getService(a);
				if (adaptiveReady.getId().equals(n.getCurrentComponent().getId())) {
					if (n.getCurrentAction() == EAdaptationAction.deploy) {
						adaptiveReady.deploy((ISystemComponentsManager) this.knowledge.getCurrentSystemConfiguration());
					} else if (n.getCurrentAction() == EAdaptationAction.undeploy) {
						adaptiveReady.undeploy((ISystemComponentsManager) this.knowledge.getCurrentSystemConfiguration());
					}
				}
			}
		}

	}

}
