package javabasics;

import org.testng.annotations.Test;

import javaMain.Vehiculo;
import javaMain.VehiculoDeportivo;
import javaMain.VehiculoFurgoneta;
import javaMain.VehiculoTurismo;

public class TestVehiculo {
	
	@Test
	public void datosVehiculo() {
		Vehiculo misVehiculos = new Vehiculo("jhc321", "Mazda", "2016");
		
		System.out.println(misVehiculos.mostrarDatosDeVehiculo());
		
		misVehiculos.setMatricula("HRM-654");
		System.out.println(misVehiculos.mostrarDatosDeVehiculo());

		misVehiculos = new VehiculoDeportivo("TRO-321", "Ford", "2018", 6);
		System.out.println(misVehiculos.mostrarDatosDeVehiculo());
		
				
	}//end datos
	
	@Test
	public void datosVehiculoEnArreglo() {
		Vehiculo misVehiculos[] = new Vehiculo[4];
		misVehiculos[0]= new Vehiculo("FRE-6542", "Chevrolet", "2018");
		misVehiculos[1]= new VehiculoDeportivo("PLI-514", "Nisan", "2020", 8);
		misVehiculos[2]= new VehiculoFurgoneta("KJH-5436", "Honda","2018", 500);
		misVehiculos[3]= new VehiculoTurismo(3, "YTR-541", "Mercedez", "2018");
		
		for (Vehiculo coche: misVehiculos) {
			System.out.println(coche.mostrarDatosDeVehiculo());
			System.out.println("***************");
		}
	}
		

	

}//end TestVehiculo
