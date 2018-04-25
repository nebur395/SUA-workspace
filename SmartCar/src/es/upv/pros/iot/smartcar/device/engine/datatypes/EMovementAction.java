package es.upv.pros.iot.smartcar.device.engine.datatypes;

public enum EMovementAction {

	UNKNOWN (-1, "unknown"),
	BRAKE (0, "brake"),
	FORWARD (1, "forward"),
	REVERSE (2, "reverse"),
	ACCELERATE (3, "accelerate"),
	DECELERATE (4, "decelerate");
	
	
	private final int code;
	private final String action;
	
	EMovementAction(int code, String action) {
		this.code = code;
		this.action = action;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getAction() {
		return this.action;
	}

	public static EMovementAction getEMovementAction(String action) {
		if ( FORWARD.getAction().equalsIgnoreCase(action) || 
			 FORWARD.name().equalsIgnoreCase(action)  )
			return FORWARD;
		if ( REVERSE.getAction().equalsIgnoreCase(action) || 
			 REVERSE.name().equalsIgnoreCase(action) )
			return REVERSE;
		if ( BRAKE.getAction().equalsIgnoreCase(action) || 
			 BRAKE.name().equalsIgnoreCase(action) )
			return BRAKE;
		if ( ACCELERATE.getAction().equalsIgnoreCase(action) || 
 			 ACCELERATE.name().equalsIgnoreCase(action) )
			return ACCELERATE;
		if ( DECELERATE.getAction().equalsIgnoreCase(action) || 
			 DECELERATE.name().equalsIgnoreCase(action) )
			return DECELERATE;
		
		return UNKNOWN;
	}

	
}
