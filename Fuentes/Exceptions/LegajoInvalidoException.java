package Exceptions;
/**
 *  
 * @author Juan_Santi
 * modela la excepcion ante un legajo que no se encuentre en la lista
 */
public class LegajoInvalidoException extends Exception{
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public LegajoInvalidoException(String msg) {
		super(msg);
	}
}
