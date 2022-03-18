package javaMain;

public class VehiculoFurgoneta extends Vehiculo{
	private int carga;

	/**
	 * @Description Constructor de VehiculoFurgoneta
	 * @param matricula
	 * @param marca
	 * @param modelo
	 */
	public VehiculoFurgoneta(String matricula, String marca, String modelo, int carga) {
		super(matricula, marca, modelo);
		this.carga=carga;
		
	}

	/**
	 * @return the carga
	 */
	public int getCarga() {
		return carga;
	}

	/**
	 * @param carga the carga to set
	 */
	public void setCarga(int carga) {
		this.carga = carga;
	}
	
	@Override
	public String mostrarDatosDeVehiculo() {
		return "Matricula: " + getMatricula() + "\nMarca: " + getMarca() + "\nModelo: " + getModelo()+"\nCarga: "+ getCarga();
		

	}

}//end VehiculoFurgoneta
