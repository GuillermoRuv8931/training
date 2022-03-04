package javabasics;

import java.util.Scanner;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class PromedioDeCalificaciones {

	double cal1 = 90;
	double cal2 = 10;
	double cal3 = -80;
	double cal4 = -70;

	@Test
	public void calcularPromedio(double resultado) {
				Reporter.log("El resultado del promedio es " + resultado, true);

		if (resultado >= 70 && resultado <= 100) {
			Reporter.log("Aprobado con: " + resultado, true);
		} else if (resultado < 70 && resultado >= 0) {
			Reporter.log("Reprobado con: " + resultado, true);
		} else {
			Reporter.log("Calificacion fuera de rango valido " + resultado, true);
		}
  
		

	}// End method calcularPromedio

	@Test
	public void calcularPromedioPorTeclado() {
		Scanner reader = new Scanner(System.in);

		do {

			Reporter.log("Ingresa la primer calificacion", true);
			cal1 = reader.nextDouble();

			Reporter.log("Ingresa la segunda calificacion", true);
			cal2 = reader.nextDouble();
		} while (cal1 <= 0 || cal1 > 100 && cal2 <= 0 || cal2 > 100);

		double resultado = (cal1 + cal2) / 2;
		Reporter.log("El promedio es: " + resultado, true);

		calcularPromedio(resultado);
		
	}
}// End class
