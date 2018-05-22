package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.List;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationAction;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAdaptationPlan;

public class AdaptationPlan implements IAdaptationPlan {
	
	private List<IAdaptationAction> actionsList;

	public AdaptationPlan (List<IAdaptationAction> list) {
		this.actionsList = list;
	}
	
	@Override
	public List<IAdaptationAction> getActions() {
		return actionsList;
	}

}
