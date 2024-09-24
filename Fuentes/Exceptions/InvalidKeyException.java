package Exceptions;
/**
 * @author Juan_Santi
 * Modela la excepción ante una clave inválida
 *
 */
public class InvalidKeyException extends Exception{
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param s Especifica información adicional acerca de la excepción
	 */
	public InvalidKeyException(String s) {
		super(s);
	}
}
