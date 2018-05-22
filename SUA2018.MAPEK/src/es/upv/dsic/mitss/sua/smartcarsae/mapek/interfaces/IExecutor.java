package es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces;

import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;

public interface IExecutor {

	public void execute(IAdaptationPlan plan) throws BundleException, InvalidSyntaxException;
	
}
