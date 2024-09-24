package Exceptions;
/**
 * 
 * @author Juan_Santi
 * modela la excepcion que exige que el legajo debe ser un numero positivo de entre 5 y 6 digitos
 */
public class RangoDeLegajoInvalidoException extends Exception{
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public RangoDeLegajoInvalidoException(String msg) {
		super(msg);
	}
}
