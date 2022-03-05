package javaMain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Arreglos {

	private int numero1;
	private int numero2;

	// @Description Constructor sin parametros

	public Arreglos() {

	}

	/**
	 * @Description Constructor con dos parametros
	 * @param numero1
	 * @param numero2
	 */
	public Arreglos(int numero1, int numero2) {
		this.numero1 = numero1;
		this.numero2 = numero2;
	}

	/**
	 * @return the numero1
	 */
	public int getNumero1() {
		return numero1;
	}

	/**
	 * @param numero1 the numero1 to set
	 */
	public void setNumero1(int numero1) {
		this.numero1 = numero1;
	}

	/**
	 * @return the numero2
	 */
	public int getNumero2() {
		return numero2;
	}

	/**
	 * @param numero2 the numero2 to set
	 */
	public void setNumero2(int numero2) {
		this.numero2 = numero2;
	}

	public void crearArreglos() {
		int[] arreglo1 = { 1, 2, 3, 4 };

		for (int i = 0; i < arreglo1.length; i++) {
			System.out.println("El vaor del arreglo es  " + arreglo1[i]);

		}
		System.out.println("\n");

		int[] arreglo2 = new int[9];

		for (int i = 0; i < arreglo2.length; i++) {

			arreglo2[i] = 1 + i;

			System.out.println("El vaor del arreglo 2 es  " + arreglo2[i]);
		}

		int suma = 0;
		System.out.println("\n");
		for (int i = 0; i < arreglo1.length; i++) {
			suma = suma + arreglo1[i];

		}
		System.out.println("La suma de los valores es " + suma);
		System.out.println("\n");

		int suma2 = 0;
		for (int i = 0; i < arreglo2.length; i++) {
			suma2 = suma2 + arreglo2[i];
			System.out.println("La suma de los valores del arreglo 2  es " + suma2);

		}

	}// end

	public void listasDeEnteros() {
		List<Integer> listaNumeros = new ArrayList<>();
		listaNumeros.add(3);
		listaNumeros.add(3);
		listaNumeros.add(3);
		listaNumeros.add(5);
		listaNumeros.add(4);

		System.out.println("Estos son los valores en la lista " + listaNumeros);
		Collections.sort(listaNumeros);
		System.out.println("Los valores ordenados de menor a mayor son " + listaNumeros);
		Collections.sort(listaNumeros, Collections.reverseOrder());
		System.out.println("Los valores ordenados de mayor a menor son " + listaNumeros);
		System.out.println("El numero mayor es " + Collections.max(listaNumeros));

		Object ultimo = null;
		int contador = 0;
		Iterator<Integer> i=listaNumeros.iterator();
		
		while(i.hasNext()) {
			Object temporal  = i.next();
			if(temporal.equals(ultimo)){
				i.remove();
				contador++;
			}else {
				ultimo= temporal;
				
			}
			
	}//end while
		System.out.println("La cantidad de numeros repetidos es " + contador );
		System.out.println("La lista sin numeros repetidos es "+listaNumeros);

}//end listadeEnteros

	public void listaDeStrings() {
		String[] nombres = {"Ramses", "Guillermo", "Abraham"};
		
		for(String nombre: nombres) {
			System.out.println("El nombre es " + nombre);
		}
		}//end listasDeStrings

}// End Arreglos
