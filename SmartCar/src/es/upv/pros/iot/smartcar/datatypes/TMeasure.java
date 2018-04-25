package es.upv.pros.iot.smartcar.datatypes;

public abstract class TMeasure {
	
	protected String measureUnit = null;
	protected Object measure = null;
	protected Float precision = null;
	
	public TMeasure() {
	}
	
	public TMeasure(Object measure, String measureUnit) {
		this.measureUnit = measureUnit;
		this.measure = measure;
	}
	
	public Object getMeasure() {
		return measure;
	}
	
	public TMeasure setMeasure(Object measure) {
		this.measure = measure;
		return this;
	}
	
	public String getMeasureUnit() {
		return measureUnit;
	}
	
	public TMeasure setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
		return this;
	}
	
	public Float getPrecision() {
		return precision;
	}
	
	public TMeasure setPrecision(Float precision) {
		this.precision = precision;
		return this;
	}
	

}
