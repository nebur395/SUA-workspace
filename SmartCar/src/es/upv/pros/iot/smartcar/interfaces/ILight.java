package es.upv.pros.iot.smartcar.interfaces;


public interface ILight {

	public int getPosition();
	public char getStatus();
	public char getConfig();
	public ILight setStatus(char status);
	public ILight setConfig(char config);
}
