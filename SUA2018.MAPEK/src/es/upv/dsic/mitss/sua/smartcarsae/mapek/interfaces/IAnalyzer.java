package es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces;

public interface IAnalyzer {

	public void notifyEvent(IEvent event);
	public void setPlannifier(IPlannifier plannifier);
	public void setKnowledge(IKnowledge knowledge);

}
