package es.upv.pros.iot.smartcar.device.distancesensor.interfaces;

import es.upv.pros.iot.smartcar.interfaces.INumericSensor;



public interface IDistanceSensor extends INumericSensor {

	public Double getDistance();
	
}
