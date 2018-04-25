package es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces;

public enum EMonitorRT {
	
	CollisionSensorFailure,
		// Fallo en sensor de colisión
		// Condición: (ninguna) -> Acción: deshabilita el SmartCar.Service.OnCollision (real del vehículo)
	
	DistanceSensorFailure,
		// Fallo en sensor de distancia (frontal)
		// Condición: ADS L3 activo (HighwayChauffer o TrafficJamChauffer) i Conductor Distraído o Dormido -> Acción: realiza DDT Fallback de emergencia (sin esperar que conductor tome el control). Activa DDTFallback, con trigger=0. Desactiva SAE.L3.*
		// Condición: ADS L3 activo (HighwayChauffer o TrafficJamChauffer) i Conductor Atento -> Acción: Activa SAE.L0.ManualDriving i Desactiva SAE.L3.*
		// Condición: ADS L1 activo -> Acción: deshabilita SAE.L1.ACC, activa SAE.L0.ManualDriving
	
	ApproachingCity,
		// Nos aproximamos a una ciudad (a 1000m de distancia)
		// Condición: ADS L3 activo -> Acción: activar DTT Fallback 'normal', permitiendo que el conductor tome el control. Activa DDTFallback con trigger=500 (representa que se espera 500 m antes de activarse), i activa DriverNotifyingService ('VAMOS A ENTRAR EN CIUDAD. POR FAVOR, TOME EL CONTROL DEL VEHÍCULO') con un timeout de 10 segundos
	
	TrafficJamDetected,
		// Se detecta atasco
		// Condición: ADS L3 HighwayChauffer activo -> Acción: desactivar HighwayChauffer i activar TrafficJamChauffer

	HighwayDetected,
		// Se detecta via rápida (sin atasco)
		// Condición: ADS L3 TrafficJamChauffer activo -> Acción: desactivar TrafficJamChauffer i activar HighwayChauffer
	
	DriverDistracted,
	DriverAsleep,
		// Se detecta que el conductor se distrae o se duerme
		// Condición: ADS L3 activo -> Acción: activamos SmartCar.HiL.DriverNotifyingService ('POR FAVOR, ESTÉ ATENTO A LA CONDUCCIÓN') sin timeout
	
	DriverAttentive
		// Se detecta que el conductor está atento
		// Condición: DDT Fallback activo (en curso) -> desactivar DDT Fallback i activa SAE.L1.ACC
		// Condición: SmartCar.HiL.DriverNotifyingService activo -> Acción: desactivamos SmartCar.HiL.DriverNotifyingService activo

//	DriverSeatOccuppied,
//	DriverSeatFree
}
