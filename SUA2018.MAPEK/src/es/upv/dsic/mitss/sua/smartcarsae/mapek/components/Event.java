package es.upv.dsic.mitss.sua.smartcarsae.mapek.components;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;

public class Event implements IEvent {
	
	protected String RT = null;
	protected Object value  = null;
	
	public Event(String RT) {
		this(RT, null);
	}
	
	public Event(String RT, Object value) {
		this.RT = RT;
		this.value = value;
	}

	@Override
	public String getRT() {
		return this.RT;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

}
