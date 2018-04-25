package es.upv.pros.iot.smartcar.device.engine.datatypes;

public enum EEngineType {

	ELECTRIC (0, "electric"),
	GASOIL (1, "gasoil"),
	GASOLINE (2, "gasoline"),
	HIDROGEN (3, "hidrogen"),
	HYBRID (4, "hybrid");
	
	
	private final int code;
	private final String name;
	
	EEngineType(int code, String name) {
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
