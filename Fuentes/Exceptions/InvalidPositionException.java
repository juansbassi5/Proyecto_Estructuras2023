package Exceptions;
/**
 * @author Juan_Santi
 * Modela la excepcion ante posiciones invalidas o lista vacia
 * 
 */
public class InvalidPositionException extends Exception{
	/**
	 * Inicializa la excepcion indicando el origen del error
	 * @param msg especifica informacion adicional acerca de la excepcion
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
