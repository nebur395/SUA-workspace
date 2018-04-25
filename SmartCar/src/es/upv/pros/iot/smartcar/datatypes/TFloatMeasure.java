package es.upv.pros.iot.smartcar.datatypes;

public abstract class TFloatMeasure extends TMeasure {
			
	
	public TFloatMeasure(Float measure, String measureUnit) {
		super(measure, measureUnit);
	}

	
	public TFloatMeasure setMeasure(Float measure) {
		this.measure = measure;
		return this;
	}

	@Override
	public Float getMeasure() {
		// TODO Auto-generated method stub
		return (Float)super.getMeasure();
	}

}
