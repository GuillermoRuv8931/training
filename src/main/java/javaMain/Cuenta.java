package javaMain;

public class Cuenta {
	private String nombre;
	private String numerCuenta;
	private double interes;
	private double saldo;

	/**
	 * Descripcion Constructor sin parametros
	 * 
	 * @Date 17/3/22
	 */
	public Cuenta() {
	}

	/**
	 * Descripcion Constructor con parametros
	 * 
	 * @Date 17/3/22
	 */
	public Cuenta(String nombre, String numerCuenta, double interes, double saldo) {
		this.nombre = nombre;
		this.numerCuenta = numerCuenta;
		this.interes = interes;
		this.saldo = saldo;
	}

	/**
	 * Descripcion Constructor recibiendo parametros tipo cuenta
	 * 
	 * @Date 17/3/22
	 */

	public Cuenta(Cuenta c) {
		nombre = c.nombre;
		numerCuenta = c.numerCuenta;
		interes = c.interes;
		saldo = c.saldo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the numerCuenta
	 */
	public String getNumerCuenta() {
		return numerCuenta;
	}

	/**
	 * @return the interes
	 */
	public double getInteres() {
		return interes;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param numerCuenta the numerCuenta to set
	 */
	public void setNumerCuenta(String numerCuenta) {
		this.numerCuenta = numerCuenta;
	}

	/**
	 * @param interes the interes to set
	 */
	public void setInteres(double interes) {
		this.interes = interes;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Descripcion Deposito a la cuenta
	 * 
	 * @Date 17/3/22
	 * @param double
	 */
	public boolean ingreso(double n) {
		boolean ingresoCorrecto = true;

		if (n < 0) {
			ingresoCorrecto = false;
		} else {
			saldo = saldo + n;
		}
		return ingresoCorrecto;

	}

	/**
	 * Descripcion retiro a la cuenta
	 * 
	 * @Date 17/3/22
	 * @param double
	 */
	public boolean retiro(double n) {
		boolean retiroCorrecto = true;

		if (n < 0) {
			retiroCorrecto = false;
		} else if(saldo>=n){
			saldo = saldo - n;
		}else {
			retiroCorrecto = false;
			
		}

		return retiroCorrecto;

	}
	/**
	 * Descripcion transferencia a la cuenta
	 * 
	 * @Date 17/3/22
	 * @param cuenta, double
	 */

	public boolean transferencia(Cuenta c, double n){
		boolean correcto =true;
		if (n>0) {
			correcto=false;
			
		}else if (saldo>=n) {
			retiro(n);
			c.ingreso(n);
		}else {
			correcto=false;
			
		}
		return correcto;
		
}
}
