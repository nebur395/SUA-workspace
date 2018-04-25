package es.upv.pros.iot.smartcar.device.steering.datatypes;

public enum ESteeringAction {

	UNKNOWN(-1, "unkonwn"),
	CENTER (0, "center"),
	RIGHT (1, "right"),
	LEFT (2, "left");
	
	
	private final int code;
	private final String action;
	
	ESteeringAction(int code, String action) {
		this.code = code;
		this.action = action;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getAction() {
		return this.action;
	}
	
	
	public static ESteeringAction getESteeringAction(String action) {
		if ( CENTER.getAction().equalsIgnoreCase(action) || 
			 CENTER.name().equalsIgnoreCase(action) )
			return CENTER;
		if ( RIGHT.getAction().equalsIgnoreCase(action) ||
			 RIGHT.name().equalsIgnoreCase(action) )
			return RIGHT;
		if ( LEFT.getAction().equalsIgnoreCase(action) ||
			 LEFT.name().equalsIgnoreCase(action) )
			return LEFT;
		return UNKNOWN;
	}


	
}
