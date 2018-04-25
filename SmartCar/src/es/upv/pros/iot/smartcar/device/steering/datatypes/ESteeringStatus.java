package es.upv.pros.iot.smartcar.device.steering.datatypes;

public enum ESteeringStatus {

	UNKNOWN (-1, "unknown"),
	CENTERED (0, "centered"),
	RIGHT (1, "right"),
	LEFT (2, "left");
	
	
	private final int code;
	private final String action;
	
	ESteeringStatus(int code, String action) {
		this.code = code;
		this.action = action;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getAction() {
		return this.action;
	}


	public static ESteeringStatus getESteeringStatus(String status) {
		if ( CENTERED.getAction().equalsIgnoreCase(status) || 
			 CENTERED.name().equalsIgnoreCase(status) )
			return CENTERED;
		if ( RIGHT.getAction().equalsIgnoreCase(status) ||
			 RIGHT.name().equalsIgnoreCase(status) )
			return RIGHT;
		if ( LEFT.getAction().equalsIgnoreCase(status) ||
			 LEFT.name().equalsIgnoreCase(status) )
			return LEFT;
		return UNKNOWN;
	}

}
