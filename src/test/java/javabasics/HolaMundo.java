package javabasics;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class HolaMundo {

	int sumatoria = 10;

	public static void main(String[] args) {
		System.out.println("Hola Mundo");
		System.out.println("Hola");
	}// end main

	@Test(priority = 2)
	public void showConsole() {
		System.out.println("Hola Mundo TestNG");
		Reporter.log("Hola Mundo", true);
		sumatoria = sumatoria + 1;
		Reporter.log("la sumatortia es " + sumatoria, true);
	}// end showConsole

	@Test(priority = 1)
	public void typeofVariables() {
		int numero1 = 10;
		char letra = 's';
		float numeroDecimal = 10.5f;
		double numeroGrande = 10.654869;
		boolean flag = true;
		String nombre = "Ramses";
		Boolean flag2 = false;
		Double numeroGrande2 = 102.2468546;
		int numero2 = 5;
		Reporter.log("El valor de la variable: " + numero1, flag);
		sumatoria = numero1 + sumatoria;
		Reporter.log("El valor nuevo de sumatoria es " + sumatoria, true);

	}

	@Test(priority = 3)
	public void operacionesAritmeticas() {
		int numero1 = 10;
		int numero2 = 90;
		double resultado = 0;

		resultado = numero1 + numero2;
		Reporter.log("El resultado de la suna es " + resultado, true);
		resultado = numero2 - numero1;
		Reporter.log("El resultado de la resta es " + resultado, true);
		resultado = numero2 * numero1;
		Reporter.log("El resultado de la multiplicacion es " + resultado, true);
		resultado = numero2 / numero1;
		Reporter.log("El resultado de la division es " + resultado, true);
		resultado = numero1 % numero2;
		Reporter.log("El resultado de la residuo es " + resultado, true);
	}

}// endclass
