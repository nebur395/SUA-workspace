package es.upv.pros.iot.smartcar.device.steering.interfaces;

import es.upv.pros.iot.smartcar.device.steering.datatypes.ESteeringAction;


public interface ISteeringCommands {
	
	public ISteeringCommands setPosition(ESteeringAction action, int value);   // value = [0..6] 0=centered, 6=max position

	public ISteering getSteering();
	
	
}
