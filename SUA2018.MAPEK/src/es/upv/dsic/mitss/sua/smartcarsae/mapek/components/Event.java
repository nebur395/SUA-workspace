package es.upv.dsic.mitss.sua.smartcarsae.mapek.components;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.EMonitorRT;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;

public class Event implements IEvent {
	
	protected EMonitorRT RT = null;
	protected Object value  = null;
	
	public Event(EMonitorRT RT) {
		this(RT, null);
	}
	
	public Event(EMonitorRT RT, Object value) {
		this.RT = RT;
		this.value = value;
	}

	@Override
	public EMonitorRT getRT() {
		return this.RT;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
