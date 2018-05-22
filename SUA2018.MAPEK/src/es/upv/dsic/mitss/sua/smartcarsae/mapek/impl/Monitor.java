package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IMonitor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;

import java.util.List;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.EMonitorRT;

public class Monitor implements IMonitor {

	Analyzer analyzer;
	IKnowledge knowledge;
	boolean sleep = false;

	@Override
	public void notifyEvent(IEvent event) {
		// TODO Auto-generated method stub
		List<IAdaptiveReadyComponentConfigurator> currentActiveComponents = this.knowledge
				.getCurrentSystemConfiguration().getAdaptiveReadyComponentList();

		if (event.getRT().equals(EMonitorRT.DriverAsleep) || event.getRT().equals(EMonitorRT.DriverDistracted)) {
			boolean cond1 = false;
			for (IAdaptiveReadyComponentConfigurator item : currentActiveComponents) {
				if (item.getId().contains("SAE.L3"))
					cond1 = true;
			}
			if (cond1)
				this.analyzer.notifyEvent(event);
			sleep = true;

		} else if (event.getRT().equals(EMonitorRT.DriverAttentive)) {
			boolean cond1 = false;
			boolean cond2 = false;
			for (IAdaptiveReadyComponentConfigurator item : currentActiveComponents) {
				if (item.getId().equals("SAE.L3.DDTFallback")) {
					cond1 = true;
				}
				if (item.getId().equals("SmartCar.HiL.DriverNotifyingService")) {
					cond2 = true;
				}
			}
			if (cond1 || cond2)
				this.analyzer.notifyEvent(event);
			sleep = false;
		} else if (event.getRT().equals(EMonitorRT.CollisionSensorFailure)) {
			this.getAnalyzer().notifyEvent(event);
		}

		else if (event.getRT().equals(EMonitorRT.ApproachingCity)) {
			boolean cond1 = false;
			for (IAdaptiveReadyComponentConfigurator item : currentActiveComponents) {
				if (item.getId().contains("SAE.L3")) {
					cond1 = true;
				}
			}
			if (cond1)
				this.analyzer.notifyEvent(event);

		} else if (event.getRT().equals(EMonitorRT.TrafficJamDetected)) {
			boolean cond1 = false;
			for (IAdaptiveReadyComponentConfigurator item : currentActiveComponents) {
				if (item.getId().equals("SAE.L3.HighwayChauffer")) {
					cond1 = true;
				}
			}
			if (cond1)
				this.analyzer.notifyEvent(event);

		} else if (event.getRT().equals(EMonitorRT.DistanceSensorFailure)) {
			boolean cond1 = false;
			boolean cond2 = false;
			boolean cond3 = false;

			for (IAdaptiveReadyComponentConfigurator item : currentActiveComponents) {
				if ((item.getId().equals("SAE.L3.HighwayChauffer") || item.getId().equals("SAE.L3.TrafficJamChauffer")) &&!sleep) {
					cond1 = true;
				} else if ((item.getId().equals("SAE.L3.HighwayChauffer") || item.getId().equals("SAE.L3.TrafficJamChauffer")) && sleep) {
					cond2 = true;
				} else if (item.getId().contains("SAE.L1")) {
					cond3 = true;
				}
			}
			if (cond1 || cond2 || cond3)
				this.analyzer.notifyEvent(event);
		}else if (event.getRT().equals(EMonitorRT.HighwayDetected)) {
			boolean cond1 = false;
			for (IAdaptiveReadyComponentConfigurator item : currentActiveComponents) {
				if (item.getId().equals("SAE.L3.TrafficJamChauffer")) {
					cond1 = true;
				}
			}
			if (cond1)
				this.analyzer.notifyEvent(event);

		}

	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public Analyzer getAnalyzer() {
		return this.analyzer;
	}

	public IKnowledge getKnowledge() {
		return knowledge;
	}

}
