package Exceptions;
/**
 * 
 * @author Juan_Santi
 * modela la excepcion ante una nota fuera del rango entre 1 y 10
 *
 */
public class NotaInvalidaException extends Exception{
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public NotaInvalidaException(String msg) {
		super(msg);
	}
}
