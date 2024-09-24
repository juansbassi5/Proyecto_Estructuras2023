package TDALista;

import java.util.*;
import Exceptions.*;

/**
 * Modela un iterador de PositionList
 * @param <E> Tipo de dato de las posiciones
 */
public class ElementIterator<E> implements Iterator<E>{
	protected PositionList<E> lista;
	protected Position<E> cursor;
	
	/**
	 * Inicializa un iterador a partir de una lista parametrizada
	 * @param L lista de elementos genéricos
	 */
	public ElementIterator(PositionList<E> L) {
		lista = L;
		if(lista.isEmpty())
			cursor = null;
		else 
			try {
				cursor = lista.first();
			} catch(EmptyListException e) {
				e.printStackTrace();
			}
			
	}
	/*
	 * Consulta si hay más elementos
	 * @return verdadero si hay más elementos
	 */
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return cursor != null;
	}

	/*
	 * Devuelve el el siguiente elemento
	 * @return El elemento actual E del cursor al llamar al método 
	 */
	public E next() throws NoSuchElementException {
		if(cursor == null)
			throw new NoSuchElementException("Error: No Hay siguiente");
		E toReturn = cursor.element();
		try {
			cursor = (cursor == lista.last()? null: lista.next(cursor));
			
		} catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}
