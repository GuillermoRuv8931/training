package javabasics;

import java.util.Scanner;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Ciclos {

	@Test
	public void ciclos() {

		int index = 0;
		int num = 10;

		for (int i = 0; i <= num; i++) {

			Reporter.log("Valor del indice For es " + i, true);
		}
		System.out.println("\n");

		while (index <= num) {
			Reporter.log("Valor del indice While es " + index, true);
			index++;
		}

	}// End ciclo

	@Test
	public void condicionalSwitch() {
		Scanner reader = new Scanner(System.in);
		Reporter.log("Ingresa el numero de mes", true);
		String mes = reader.next();
		reader.close();

		switch (mes) {
		case "1":
			Reporter.log("Enero", true);
			break;
		case "2":
			Reporter.log("Febrero", true);
			break;

		case "3":
			Reporter.log("Marzo", true);
			break;
		case "4":
			Reporter.log("Abril", true);
			break;
		case "5":
			Reporter.log("Mayo", true);
			break;
		case "6":
			Reporter.log("Junio", true);
			break;
		case "7":
			Reporter.log("Julio", true);
			break;
		case "8":
			Reporter.log("Agsto", true);
			break;
		case "9":
			Reporter.log("Septiembre", true);
			break;
		case "10":
			Reporter.log("Octubre", true);
			break;
		case "11":
			Reporter.log("Noviembre", true);
			break;
		case "12":
			Reporter.log("Diciembre", true);
			break;

		default:
			Reporter.log("Numero invalido, no corresponde a nigun mes: " + mes, true);

		}
	}

}// End class
