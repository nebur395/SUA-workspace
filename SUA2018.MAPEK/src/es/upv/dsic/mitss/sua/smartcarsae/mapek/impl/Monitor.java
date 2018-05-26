package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.ArrayList;
import java.util.List;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.monitores.MonitorAbstract;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.EMonitorRT;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationAction;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IMonitor;

public class Monitor extends MonitorAbstract implements IMonitor {

	IAnalyzer analyzer;
	IKnowledge knowledge;
	boolean sleep = false;

	@Override
	public void notifyEvent(IEvent event) {
		try {
			System.out.println("Notificado "+event.getRT());
		//Al final no se ha realizado la implementación del Patron de diseño Template.
		//this.analyzer.notifyEvent(event);
		if( this.knowledge.getCurrentAdaptionPlan() == null) {

			this.analyzer.notifyEvent(event);
		} else {
			List<IAdaptationAction> currentActions = this.knowledge.getCurrentAdaptionPlan().getActions();
			List<String> deployedComponents = new ArrayList<String>();
			 
			for (IAdaptationAction cac : currentActions) {
				if (cac.getCurrentAction() == EAdaptationAction.deploy || cac.getCurrentAction() == EAdaptationAction.undeploy)
					deployedComponents.add(cac.getCurrentComponent().getId());
			}
			
			if (event.getRT().equals(EMonitorRT.DriverAsleep) || event.getRT().equals(EMonitorRT.DriverDistracted)) {
				boolean cond1 = false;
				for (String item : deployedComponents) {
					if (item.contains("SAE.L3.ACC"))
						cond1 = true;
				}
				if (cond1)
					this.analyzer.notifyEvent(event);
				sleep = true;
	
			} else if (event.getRT().equals(EMonitorRT.DriverAttentive)) {
				boolean cond1 = false;
				boolean cond2 = false;
				for (String item : deployedComponents) {
					if (item.equals("SAE.L3.DDTFallback")) {
						cond1 = true;
					}
					if (item.equals("SmartCar.HiL.DriverNotifyingService")) {
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
				for (String item : deployedComponents) { 
					if (item.contains("SAE.L3.ACC")) {
						cond1 = true;
					}
				}
				if (cond1)
					this.analyzer.notifyEvent(event);
	
			} else if (event.getRT().equals(EMonitorRT.TrafficJamDetected)) {
				boolean cond1 = false;
				for (String item : deployedComponents) {
					if (item.equals("SAE.L3.HighwayChauffer")) {
						cond1 = true;
					}
				}
				if (cond1)
					this.analyzer.notifyEvent(event);
	
			} else if (event.getRT().equals(EMonitorRT.DistanceSensorFailure)) {
				boolean cond1 = false;
				boolean cond2 = false;
				boolean cond3 = false;
	
				for (String item : deployedComponents) {
					if ((item.equals("SAE.L3.HighwayChauffer") || item.equals("SAE.L3.TrafficJamChauffer")) && !sleep) {
						cond1 = true;
					} else if ((item.equals("SAE.L3.HighwayChauffer") || item .equals("SAE.L3.TrafficJamChauffer")) && sleep) {
						cond2 = true;
					} else if (item.contains("SAE.L1")) {
						cond3 = true;
					}
				}
				if (cond1 || cond2 || cond3)
					this.analyzer.notifyEvent(event);
			} else if (event.getRT().equals(EMonitorRT.HighwayDetected)) {
				boolean cond1 = false;
				for (String item : deployedComponents) {
					if (item.equals("SAE.L3.TrafficJamChauffer")) {
						cond1 = true;
					}
				}
				if (cond1)
					this.analyzer.notifyEvent(event);
	
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void setAnalyzer(IAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	public IAnalyzer getAnalyzer() {
		return this.analyzer;
	}

	public IKnowledge getKnowledge() {
		return knowledge;
	}

	@Override
	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
		
	}

}
