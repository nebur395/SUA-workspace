package es.upv.pros.iot.smartcar.datatypes;

public enum EDriveMode {

	MANUAL (0, "manual"),
	ASSISTED (1, "assisted"),
	AUTONOMOUS (2, "autonomous");
	
	
	private final int code;
	private final String name;
	
	EDriveMode(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCode() {
		return this.code;
	}

	
}
