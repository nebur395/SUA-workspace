package es.upv.pros.iot.smartcar.device.steering.interfaces;

import es.upv.pros.iot.smartcar.device.steering.datatypes.ESteeringStatus;
import es.upv.pros.iot.smartcar.interfaces.ISmartCarDevice;

public interface ISteering extends ISmartCarDevice {

	public ESteeringStatus getStatus();

	public int getPosition();

	public ISteeringCommands getCommands();

}
