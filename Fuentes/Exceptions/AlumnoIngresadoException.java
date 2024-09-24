package Exceptions;
/**
 * 
 * @author Juan_Santi
 *
 * Modela la excepcion ante un alumno que ya se encuentra ingresado en la lista
 */
public class AlumnoIngresadoException extends Exception{
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public AlumnoIngresadoException(String msg) {
		super(msg);
	}
}
