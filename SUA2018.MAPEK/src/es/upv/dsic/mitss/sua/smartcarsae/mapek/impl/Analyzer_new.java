package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;

public class Analyzer_new implements IAnalyzer {

	private IPlannifier plannifier;
	private IKnowledge knowledge;
	private BundleContext context;
	
	public Analyzer_new(BundleContext context) {
		this.context = context;
	}
	@Override
	public void notifyEvent(IEvent event) {
		
		String rt_filter = "(rt="+event.getRT()+")";
		try {
			ServiceReference<?>[] rule = context.getServiceReferences(IAdaptationRule.class.getName(), rt_filter);
			if(rule != null) {
				for(ServiceReference<?> sr : rule) {
					IAdaptationRule r = (IAdaptationRule) context.getService(sr);
					if(r == null)
						return;
				}
			}
			
		} catch (InvalidSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void setPlannifier(IPlannifier plannifier) {
		this.plannifier = plannifier;

	}

	@Override
	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
	}

}
