package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.EMonitorRT;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.rules.ApproachingCityRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.rules.DistanceSensorFailureRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.rules.DriverAttentiveRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.rules.DriverUnattentiveRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.rules.HighwayDetectedRule;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.rules.TrafficJamDetectedRule;

public class Analyzer_new implements IAnalyzer {

	private IPlannifier plannifier;
	private IKnowledge knowledge;
	private BundleContext context;
	private List<IAdaptationRule> rulesList;
	
	public Analyzer_new(BundleContext context) {
		this.context = context;
		this.rulesList = new ArrayList<>();
		this.rulesList.add(new ApproachingCityRule(context));
		this.rulesList.add(new DistanceSensorFailureRule(context));
		this.rulesList.add(new DriverAttentiveRule(context));
		this.rulesList.add(new DriverUnattentiveRule(context));
		this.rulesList.add(new HighwayDetectedRule(context));
		this.rulesList.add(new TrafficJamDetectedRule(context));
	}
	@Override
	public void notifyEvent(IEvent event) {
		String rt_filter = "(rt="+((event.getRT() == EMonitorRT.DriverAsleep) ? EMonitorRT.DriverDistracted : event.getRT())+")";
		try {
			ServiceReference<?>[] rules = context.getServiceReferences(IAdaptationRule.class.getName(), rt_filter);
			IAdaptationRule rule = null;
			if(rules != null) {
				for(ServiceReference<?> sr : rules) {
					rule = (IAdaptationRule) context.getService(sr);
					if(rule == null)
						return;
				}
				ISystemConfiguration newSC = rule.executeRule(event);
				this.plannifier.plan(newSC);
			}
			
		} catch (Exception e) {
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
	
	public IKnowledge getKnowledge() {
		return this.knowledge;
	}

}
