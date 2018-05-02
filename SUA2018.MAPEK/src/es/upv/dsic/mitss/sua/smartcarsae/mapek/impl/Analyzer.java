package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class Analyzer implements IAnalyzer {

	private IPlannifier plannifier;
	private IKnowledge knowledge;
	private BundleContext context;

	public Analyzer(BundleContext context) {
		this.context = context;
	}

	@Override
	public void notifyEvent(IEvent event) {
		switch (event.getRT()) {
			case ApproachingCity:
				break;
			case CollisionSensorFailure:
				break;
			case DistanceSensorFailure:
				ServiceReference<?>[] refs = null;
				int level = 0;
				try {

					refs = this.context.getAllServiceReferences(IAdaptiveReadyComponentConfigurator.class.getName(),
							"(id = SAE.L1.ACC)");
					try {
						for (ServiceReference ref : refs) {
							IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
							if(arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer") || arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer")) {
								level = 3;
								break;
							} else if(arcc.getId().contentEquals("SAE.L1.ACC"))
								level = 1;
						}
					} catch (Exception e) {
					}

				} catch (InvalidSyntaxException e) {
				}
				break;
			case DriverAsleep:
				break;
			case DriverAttentive:
				break;
			case DriverDistracted:
				break;
			case HighwayDetected:
				break;
			case TrafficJamDetected:
				break;
		}
	}

	public void setPlannifier(IPlannifier plannifier) {
		this.plannifier = plannifier;
	}

	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
	}
}
