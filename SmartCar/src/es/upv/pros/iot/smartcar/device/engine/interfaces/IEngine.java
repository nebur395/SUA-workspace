package es.upv.pros.iot.smartcar.device.engine.interfaces;

import es.upv.pros.iot.smartcar.device.engine.datatypes.EMovementStatus;
import es.upv.pros.iot.smartcar.interfaces.ISmartCarDevice;

public interface IEngine extends ISmartCarDevice {

	public IEngine setMovement(EMovementStatus mov);

	public IEngine setRPM(int rpm);

	public EMovementStatus getMovement();

	public int getRPM();

	public IEngineCommands getCommands();

}
