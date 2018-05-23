package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;



public class Analyzer implements IAnalyzer {

	private IPlannifier plannifier;
	private IKnowledge knowledge;
	private BundleContext context;

	public Analyzer(BundleContext context) {
		this.context = context;
	}

	@Override
	public void notifyEvent(IEvent event) {
		System.out.println("Recibido por el analizador");
		try {
			List<IAdaptiveReadyComponentConfigurator> servicesList = new ArrayList<>(this.knowledge.getCurrentSystemConfiguration().getAdaptiveReadyComponentList());
			if(servicesList == null)
				servicesList = new ArrayList<>();
			ServiceReference<?>[] refs = null;
			refs = this.context.getAllServiceReferences(IAdaptiveReadyComponentConfigurator.class.getName(),
					null);
			int level = 0;
			switch (event.getRT()) {
				case ApproachingCity:
					try {						
						try {
							if(refs != null)
								for (ServiceReference<?> ref : refs) {
									IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
									if(arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer") || arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer")) {
										level = 3;
										break;
									}
								}
						} catch (Exception e) {
							throw new Exception(e);
						}
	
					} catch (InvalidSyntaxException e) {
						throw new Exception(e);
					}
					if(level == 3) {
						servicesList.add(new sae.l3.ddtfallback.AdaptiveReadyComponent(context).setParameter("ActivationDistance", 500));
						servicesList.add(new smartcar.hil.drivernotifyingservice.AdaptiveReadyComponent(context).setParameter("Timeout", 10));
					}
					break;
				case CollisionSensorFailure:
					break;
				case DistanceSensorFailure:
					try {
	
						
						try {
							if(refs != null)
								for (ServiceReference<?> ref : refs) {
									IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
									if(arcc.getId().contentEquals("SAE.L3.HighwayChauffer") || arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer")) {
										level = 3;
										break;
									} else if(arcc.getId().contentEquals("SAE.L1.ACC"))
										level = 1;
								}
						} catch (Exception e) {
							throw new Exception(e);
						}
	
					} catch (InvalidSyntaxException e) {
						throw new Exception(e);
					}
					if(level == 3) {
						servicesList.add(new sae.l3.ddtfallback.AdaptiveReadyComponent(context).setParameter("ActivationDistance", 0));
					} else if(level == 1) {
						servicesList.removeIf(service -> service.getId().contentEquals("SAE_L1_ACC"));
						servicesList.add(new sae.l0.manualdriving.AdaptiveReadyComponent(context));
					}
					break;
				case DriverAsleep:
				case DriverDistracted:
					try {						
						try {
							if(refs != null)
								for (ServiceReference<?> ref : refs) {
									IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
									if(arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer") || arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer")) {
										level = 3;
										break;
									}
								}
						} catch (Exception e) {
							throw new Exception(e);
						}
	
					} catch (InvalidSyntaxException e) {
						throw new Exception(e);
					}
					if(level == 3) {
						servicesList.add(new smartcar.hil.drivernotifyingservice.AdaptiveReadyComponent(context));
					}
					break;
				case DriverAttentive:
					try {						
						try {
							if(refs != null)
								for (ServiceReference<?> ref : refs) {
									IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
									if(arcc.getId().contentEquals("SAE.L3.DDTFallback")) {
										servicesList.removeIf(service -> service.getId().contentEquals("SAE.L3.DDTFallback"));
										servicesList.add(new sae.l1.acc.AdaptiveReadyComponent(context));
									}
									if(arcc.getId().contentEquals("SmartCar.HiL.DriverNotifyingService")) {
										servicesList.removeIf(service -> service.getId().contentEquals("SmartCar.HiL.DriverNotifyingService"));
									}
								}
						} catch (Exception e) {
							throw new Exception(e);
						}
	
					} catch (InvalidSyntaxException e) {
						throw new Exception(e);
					}
					if(level == 3) {
						servicesList.add(new smartcar.hil.drivernotifyingservice.AdaptiveReadyComponent(context));
					}
					break;
				case HighwayDetected:
					try {						
						try {
							if(refs != null)
								for (ServiceReference<?> ref : refs) {
									IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
									if(arcc.getId().contentEquals("SAE.L3.TrafficJamChauffer")) {
										servicesList.removeIf(service -> service.getId().contentEquals("SAE.L3.TrafficJamChauffer"));
										break;
									}
								}
						} catch (Exception e) {
							throw new Exception(e);
						}
	
					} catch (InvalidSyntaxException e) {
						throw new Exception(e);
					}
					servicesList.add(new sae.l3.highwaychauffer.AdaptiveReadyComponent(context));
					break;
				case TrafficJamDetected:
					try {						
						try {
							if(refs != null)
								for (ServiceReference<?> ref : refs) {
									IAdaptiveReadyComponentConfigurator arcc = (IAdaptiveReadyComponentConfigurator) this.context.getService(ref);
									if(arcc.getId().contentEquals("SAE.L3.HighwayChauffer")) {
										servicesList.removeIf(service -> service.getId().contentEquals("SAE.L3.HighwayChauffer"));
										break;
									}
								}
						} catch (Exception e) {
							throw new Exception(e);
						}
	
					} catch (InvalidSyntaxException e) {
						throw new Exception(e);
					}
					servicesList.add(new sae.l3.trafficjamchauffer.AdaptiveReadyComponent(context));
					break;
			}
			SystemConfiguration systemConfig = new SystemConfiguration();
			systemConfig.setAdaptiveReadyComponentList(servicesList);
			this.plannifier.plan(systemConfig);			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setPlannifier(IPlannifier plannifier) {
		this.plannifier = plannifier;
	}

	public void setKnowledge(IKnowledge knowledge) {
		this.knowledge = knowledge;
	}
}
