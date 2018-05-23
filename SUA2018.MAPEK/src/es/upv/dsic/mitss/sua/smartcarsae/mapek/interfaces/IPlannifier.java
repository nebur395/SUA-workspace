package es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces;

import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;

public interface IPlannifier {

	public void plan(ISystemConfiguration theSystemConfiguration) throws BundleException, InvalidSyntaxException;

	public void setExecutor(IExecutor executor);
	
	public void setKnowledge(IKnowledge knowledge);

}
