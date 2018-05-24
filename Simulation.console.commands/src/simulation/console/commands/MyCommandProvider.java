package simulation.console.commands;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.components.Event;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.EMonitorRT;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IMonitor;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;

public class MyCommandProvider {

	BundleContext context = null;

	public MyCommandProvider(BundleContext context) {
		this.context = context;
	}

	public void printCSC() {
		// muestra por pantalla la configuración de sistema actual
		// - Componentes activos
		// - Bindings entre componentes (para máxima nota)

		List<String> arc_list = new ArrayList<String>();
		IAdaptiveReadyComponentConfigurator arcc = null;
		ServiceReference<?>[] refs = null;
		try {

			refs = this.context.getServiceReferences(IAdaptiveReadyComponentConfigurator.class.getName(),
					"(started=true)");
			try {
				if(refs != null)
					for (ServiceReference ref : refs) {
						arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
						arc_list.add(arcc.getClass().getName());
					}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (InvalidSyntaxException e) {
		}

		StringBuffer buff = new StringBuffer("[");
		if (arc_list.size() > 1) {
			for (int i = 0; i < arc_list.size() - 1; i++) {
				buff.append(arc_list.get(i));
				buff.append(",");
			}
		}

		if (arc_list.size() > 0) {
			buff.append(arc_list.get(arc_list.size() - 1));
		}
		buff.append("]");

		System.out.println(buff.toString());
	}

	public void approachingCity() {
		
		ServiceReference<?>[] refs = null;

		try {
			refs = this.context.getServiceReferences(IMonitor.class.getName(), "(id=NavigatorMonitor)");
			if (refs == null || refs.length <= 0) {
				System.out.println("Servicio no encontrado");
				return;
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			IMonitor navigatorMonitor = (IMonitor) this.context.getService(refs[0]);
			if(navigatorMonitor != null) {
				Event event = new Event(EMonitorRT.ApproachingCity);
				navigatorMonitor.notifyEvent(new Event(EMonitorRT.ApproachingCity));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

public void enteringHighway() {
		
		ServiceReference<?>[] refs = null;

		try {
			refs = this.context.getServiceReferences(IMonitor.class.getName(), "(id=NavigatorMonitor)");
			if (refs == null || refs.length <= 0) {
				System.out.println("Servicio no encontrado");
				return;
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			IMonitor navigatorMonitor = (IMonitor) this.context.getService(refs[0]);
			if(navigatorMonitor != null) {
				Event event = new Event(EMonitorRT.HighwayDetected);
				navigatorMonitor.notifyEvent(event);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
