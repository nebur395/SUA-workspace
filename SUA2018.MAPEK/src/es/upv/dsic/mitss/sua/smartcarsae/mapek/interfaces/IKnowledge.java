package es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces;

public interface IKnowledge {

	public ISystemConfiguration getCurrentSystemConfiguration();

	public void setCurrentSystemConfiguration(ISystemConfiguration systemConfig);
	
	public IAdaptationPlan getCurrentAdaptionPlan();
	
	public void setCurrentAdaptionPlan(IAdaptationPlan adaptionPlan);
	

}
