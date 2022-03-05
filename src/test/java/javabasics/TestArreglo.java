package javabasics;

import org.testng.annotations.Test;
import javaMain.Arreglos;

public class TestArreglo {
	
	@Test
	public void probarArreglos() {
		
		Arreglos arr = new Arreglos();
		
		//arr.crearArreglos();
		
		arr.listaDeStrings();
	}

}
