package Exceptions;
/**
 * @author Juan_Santi
 * Modela la excepción ante recorridos que exceden los límites de la lista
 *
 */
public class BoundaryViolationException extends Exception{
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public BoundaryViolationException(String msg){
		super(msg);
	}
}
