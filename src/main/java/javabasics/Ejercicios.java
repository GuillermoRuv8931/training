package javabasics;

import java.util.Scanner;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Ejercicios {

	@Test
	public void mayorOMenor() {

		Scanner reader = new Scanner(System.in);
		Reporter.log("Ingresa un numero ", true);
		double num1 = reader.nextDouble();
		Reporter.log("Ingresa un numero ", true);
		double num2 = reader.nextDouble();

		if (num1 > num2) {
			Reporter.log(num1 + " Es mayor que " + num2, true);
		} else if (num2 > num1) {
			Reporter.log(num2 + " Es mayor que " + num1, true);
		} else if (num2 == num1) {
			Reporter.log(num2 + " y  " + num1 + " son iguales ", true);
		} else {
			Reporter.log("Valores invalidos", true);
		}

	}//End mayorOMenor
	
	@Test
	public void numerosIguales(){
	
		Scanner reader = new Scanner(System.in);
		double num1;
		double num2;
		do {
		
		Reporter.log("Ingresa el primer  numero", true);
		num1 = reader.nextDouble();
		Reporter.log("Ingresa el segundo numero", true);
		num2 = reader.nextDouble();				
		
		} while(num1 != num2);
		Reporter.log("Los numeros son iguales", true);
		
		
		
		
	}//End numerosIguales
	

}// End Ejercicios
