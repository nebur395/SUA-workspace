package es.upv.pros.iot.smartcar.device.engine.datatypes;

public enum EMovementStatus {

	UNKNOWN (-1, "unkn", "unknown"),
	STOPPED (0, "stop", "stopped"),
	FORWARD (1, "forw", "forward"),
	REVERSE (2, "rev", "reverse");
	
	
	final int code;
	final String status;
	final String desc;
	
	EMovementStatus(int code, String status, String desc) {
		this.code = code;
		this.status = status;
		this.desc = desc;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public static EMovementStatus getEMovementStatus(String status) {
		if ( FORWARD.getStatus().equalsIgnoreCase(status) || 
			 FORWARD.name().equalsIgnoreCase(status) ||
			 FORWARD.getDesc().equalsIgnoreCase(status) )
			return FORWARD;
		if ( REVERSE.getStatus().equalsIgnoreCase(status) || 
			 REVERSE.name().equalsIgnoreCase(status) ||
			 REVERSE.getDesc().equalsIgnoreCase(status))
			return REVERSE;
		if ( STOPPED.getStatus().equalsIgnoreCase(status) || 
			 STOPPED.name().equalsIgnoreCase(status) ||
			 STOPPED.getDesc().equalsIgnoreCase(status))
			return STOPPED;
		return UNKNOWN;
	}

	
}
