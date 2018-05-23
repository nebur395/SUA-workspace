package es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces;

public interface IMonitor {

	public void notifyEvent(IEvent event);
	public void setAnalyzer(IAnalyzer analyzer);
	public void setKnowledge(IKnowledge knowledge);

}
