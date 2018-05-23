package sua2018.mapek;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.Analyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.Executor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.Knowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.Monitor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.impl.Plannifier;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IExecutor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IMonitor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;
import es.upv.pros.iot.smartcar.services.interfaces.ILoop;

public class SUA2018_MAPEK_Loop implements ILoop {
	
	protected IMonitor monitor;
	protected IAnalyzer analyzer;
	protected IKnowledge knowledge;
	protected IPlannifier plannifier;
	protected IExecutor executor;
	
	protected BundleContext context = null;
	protected ServiceRegistration reg = null, monitorService = null;
	protected Dictionary<String, Object> properties = null;
		
	
	public SUA2018_MAPEK_Loop(BundleContext context, String id) {
		this.context = context;
		this.properties = new Hashtable<String, Object>();
		this.properties.put("id", id);
		this.monitor = new Monitor();
		this.analyzer = new Analyzer(context);
		this.plannifier = new Plannifier();
		this.executor = new Executor(context);
		this.knowledge = new Knowledge(context);
		this.monitor.setAnalyzer(analyzer);
		this.monitor.setKnowledge(knowledge);
		this.analyzer.setKnowledge(knowledge);
		this.analyzer.setPlannifier(plannifier);
		this.plannifier.setKnowledge(knowledge);
		this.plannifier.setExecutor(executor);
		this.executor.setKnowledge(knowledge);
	}
	
	public ILoop start() {
		this.reg = this.context.registerService(ILoop.class, this, this.properties);
		Hashtable props = new Hashtable();
		props.put("id", "NavigatorMonitor");
		this.monitorService = this.context.registerService(IMonitor.class.getName(),
				this.monitor, props);
		// La magia empieza. Se recomienda crear un worker (Thread o similar) y que empiece
		//   a simular movimiento ACC.
		// Se dispone de un motor, de un sensor de distancia y de una distancia de seguridad ...
		
		return this;
	}
	
	public ILoop stop() {
		if ( this.reg != null )
			this.reg.unregister();
		if ( this.monitorService != null )
			this.monitorService.unregister();
		return this;
	}

}


