package es.upv.pros.iot.smartcar.interfaces;

public interface IBistateSensor extends ISmartCarDevice {

	public IBistateSensor setValue(boolean v);

	public boolean getValue();

}
