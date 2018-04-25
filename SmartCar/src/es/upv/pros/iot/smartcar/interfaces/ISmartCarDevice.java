package es.upv.pros.iot.smartcar.interfaces;

public interface ISmartCarDevice {

	public String getId();

	public String getSmartCarId();

	public ISmartCar getSmartCar();

	public String getDeviceType();

	public ISmartCarDevice setDeviceType(String type);

	public ISmartCarDevice setInitialState();

}
