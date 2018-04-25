package es.upv.pros.iot.smartcar.device.steering.interfaces;




public interface ISteeringConfigurator {
	
	public ISteering getSteering();

	public ISteeringConfigurator start();
	public ISteeringConfigurator stop();	

}
