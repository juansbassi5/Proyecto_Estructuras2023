package TDAColaCP;

/**
 * Clase Comparator implementa la interfaz java.util.Comparator
 *
 * @param <E> Tipo de dato que se utiliza para comparar elementos del mismo tipo
 */
public class Comparator<E> implements java.util.Comparator<E> {

	@Override
	public int compare(E o1, E o2) {		
		return ((Comparable<E>) o1).compareTo(o2);
	}
}