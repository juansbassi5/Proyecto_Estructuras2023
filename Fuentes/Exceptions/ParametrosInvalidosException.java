package Exceptions;
/**
 * @author Juan_Santi
 * 
 * Modela la excepcion ante legajos o notas males ingresados
 * es decir, deben ser de tipo Integer
 *
 */
public class ParametrosInvalidosException extends Exception{
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public ParametrosInvalidosException(String msg) {
		super(msg);
	}
}
