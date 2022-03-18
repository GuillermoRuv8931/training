package javaMain;

public class VehiculoTurismo extends Vehiculo {

	int numeroPuertas;

	/**
	 * @Description Constructor de VehiculoTurismo
	 * @param matricula
	 * @param marca
	 * @param modelo
	 */
	public VehiculoTurismo(int numeroPuertas, String matricula, String marca, String modelo) {
		super(matricula, marca, modelo);
		this.numeroPuertas = numeroPuertas;

	}

	/**
	 * @return the numeroPuertas
	 */
	public int getNumeroPuertas() {
		return numeroPuertas;
	}

	/**
	 * @param numeroPuertas the numeroPuertas to set
	 */
	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}

	@Override
	public String mostrarDatosDeVehiculo() {
		return "Matricula: " + getMatricula() + "\nMarca: " + getMarca() + "\nModelo: " + getModelo() + "\nNumero de puertas: "
				+ getNumeroPuertas();

	}
}// end VehiculoTurismo
