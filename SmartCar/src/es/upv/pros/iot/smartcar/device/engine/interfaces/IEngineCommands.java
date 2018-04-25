package es.upv.pros.iot.smartcar.device.engine.interfaces;




public interface IEngineCommands {
	
	public IEngineCommands goForward(int rpm);
	public IEngineCommands goReverse(int rpm);
	public IEngineCommands brake();
	public IEngineCommands accelerate(int rpm);
	public IEngineCommands decelerate(int rpm);
	
	public IEngine getEngine();

}
