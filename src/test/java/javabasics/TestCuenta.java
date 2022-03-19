package javabasics;

import java.util.Scanner;

import org.testng.annotations.Test;

import javaMain.Cuenta;

public class TestCuenta {
	@Test
	public void probarCuenta() {
		Scanner teclado= new Scanner(System.in);
		String nombre;
		String numeroCuenta;
		double tipo;
		double importer;
		System.out.println("Ingresa el nombre ");
		nombre = teclado.next();
		
		System.out.println("Ingresa el numero de cuenta ");
		numeroCuenta = teclado.next();
		
		System.out.println("Ingresa el tipo de interes ");
		tipo = teclado.nextDouble();
		
		System.out.println("Saldo: ");
		importer = teclado.nextDouble();
		
		teclado.close();
		Cuenta cuenta1 = new Cuenta();
		
		cuenta1.setNombre(nombre);
		cuenta1.setNumerCuenta(numeroCuenta);
		cuenta1.setInteres(tipo);
		cuenta1.setSaldo(importer);
		
		System.out.println("\nEl nombre del usuario 1 es: " + cuenta1.getNombre());
		System.out.println("El numero de cuenta 1 es  es: " + cuenta1.getNumerCuenta());
		System.out.println("El Interes de la cuenta 1 es: " + cuenta1.getInteres());
		System.out.println("El Saldo del usuario 1 es: " + cuenta1.getSaldo());
		
		
		Cuenta cuenta2= new Cuenta("Gmo", "321654", 15.2, 10000 );
		
		System.out.println("\nEl nombre del usuario 2 es : " + cuenta2.getNombre());
		System.out.println("El numero de cuenta 2 es  es: " + cuenta2.getNumerCuenta());
		System.out.println("El Interes de la cuenta 2 es: " + cuenta2.getInteres());
		System.out.println("El Saldo del usuario 2 es: " + cuenta2.getSaldo());
		
		Cuenta cuenta3 = new Cuenta (cuenta1);
		cuenta3.setNombre("Alex");
		System.out.println("\nEl nombre del usuario 3 es : " + cuenta3.getNombre());
		System.out.println("El numero de cuenta 3 es  es: " + cuenta3.getNumerCuenta());
		System.out.println("El Interes de la cuenta 3 es: " + cuenta3.getInteres());
		System.out.println("El Saldo del usuario 3 es: " + cuenta3.getSaldo());
		
		cuenta3.transferencia(cuenta2, 500);
		
		System.out.println("\nEl saldo de la cuenta 3 es " + cuenta3.getSaldo());
		System.out.println("\nEl saldo de la cuenta 2 es " + cuenta2.getSaldo());
		
		cuenta3.ingreso(1000);
		cuenta2.retiro(541);
		
		System.out.println("\nEl saldo de la cuenta 3 es " + cuenta3.getSaldo());
		System.out.println("\nEl saldo de la cuenta 2 es " + cuenta2.getSaldo());
		
		
		
		
	}

}
