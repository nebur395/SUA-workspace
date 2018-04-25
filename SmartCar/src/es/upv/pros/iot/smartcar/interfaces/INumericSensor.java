package es.upv.pros.iot.smartcar.interfaces;

public interface INumericSensor extends ISmartCarDevice {

	public INumericSensor setValue(Double v);

	public Double getValue();

}
