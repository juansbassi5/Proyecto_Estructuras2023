package TDALista;

import java.util.Iterator;
import java.util.ListIterator;

import Exceptions.*;

/**
 * Clase ListaDE (Doblemente Enlazada) que implementa a la interface PositionList
 *
 * @param <E> Tipo de dato de los elementos a almacenar en la lista.
 */
public class ListaDE<E> implements PositionList<E>{
	protected int size;
	protected DNodo<E> header, trailer; 
	/**
     * Crea una nueva lista doble enlazada
     */
	public ListaDE() {
		size = 0;
		header = new DNodo<E>(null, null, null);
		trailer = new DNodo<E>(null, null, header);
		header.setNext(trailer);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("No hay elementos en la lista");
		
		return header.getNext();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("No hay elementos en la lista");
		
		return trailer.getPrev();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> pos= checkPosition(p);
		
		if (pos.getNext() == trailer)
			throw new BoundaryViolationException("Posicion invalida rabo");
		
		return pos.getNext();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> pos= checkPosition(p);
		
		if (pos.getPrev() == header)
			throw new BoundaryViolationException("Posicion invalida cabeza");
		
		return pos.getPrev();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> n = new DNodo<E>(element, header.getNext(), header);
		header.getNext().setPrev(n);
		header.setNext(n);	
		size++;
		
	}

	@Override
	public void addLast(E element) {
		DNodo<E> n = new DNodo<E>(element, trailer, trailer.getPrev());
		trailer.getPrev().setNext(n);
		trailer.setPrev(n);
		size++;
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> nuevo;
		DNodo<E> pos= checkPosition(p);
		
		if(pos == trailer)
			throw new InvalidPositionException("Posicion invalida para añadir");
		
		nuevo = new DNodo<E>(element);
		nuevo.setNext(pos.getNext());
		nuevo.setPrev(pos);
		pos.getNext().setPrev(nuevo);
		pos.setNext(nuevo);
		size++;	
		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> nuevo;
		DNodo<E> pos= checkPosition(p);
		
		if(pos == header)
			throw new InvalidPositionException("Posicion invalida para añadir");
		
		nuevo = new DNodo<E>(element);
		nuevo.setPrev(pos.getPrev());
		nuevo.setNext(pos);
		pos.getPrev().setNext(nuevo);
		pos.setPrev(nuevo);
		size++;
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		E aux;
		DNodo<E> pos;
		
		if(isEmpty())
			throw new InvalidPositionException("Lista vacia");
		
		pos= checkPosition(p);
		aux=pos.element();
		pos.getPrev().setNext(pos.getNext());
		pos.getNext().setPrev(pos.getPrev());
		pos.setElement(null);
		pos.setNext(null);
		pos.setPrev(null);
		size--;
		
		return aux;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos;
		E aux;
		
		if(isEmpty())
			throw new InvalidPositionException("Lista vacia");
		
		pos= checkPosition(p);
		aux=pos.element();
		pos.setElement(element);
		
		return aux;
	}

	@Override
	public Iterator<E> iterator() {
		return (Iterator<E>) new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> LP = new ListaDE<Position<E>>();
		if (!isEmpty()) {
			try {
				Position<E> pos = first();
				boolean seguir = true;
				while (seguir) {
					LP.addLast(pos);
					if (pos == last())
						seguir = false;
					else
						pos = next(pos);
				}
			} catch (EmptyListException | BoundaryViolationException | InvalidPositionException e) {
				System.out.println("ERROR");
			}
		}
		return LP;
	}
	
	/**
	 * Revisa que la posición pasada por parámetro no sea inválida
	 * @param p Posición a revisar
	 * @return Posición pasada por parámetro
	 * @throws InvalidPositionException si la posicion es inválida o la posición previa o siguiente es inválida
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null || p == header || p == trailer)
			throw new InvalidPositionException("posicion nula");
		try {
			DNodo<E> temp = (DNodo<E>) p;
			if ((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("posicion nula");
			return temp;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("p no es un nodo de la lista");
		}
	}
	
	
	
	/**
	 * Clase DNodo que implementa a la interface Position
	 *
	 * @param <E> Tipo de dato del elemento del nodo.
	 */
	private class DNodo<E> implements Position<E>{
		private E element; // String element stored by a node
		private DNodo<E> next, prev; // Pointers to next and previous nodes
		
		/**
		 * Crea un nuevo nodo doblemente enlazado 
		 * @param e Elemento del nodo
		 * @param n Referencia al nodo siguiente
		 * @param p Referencia al nodo previo
		 */
		public DNodo(E e, DNodo<E> n, DNodo<E> p) {
			element = e;
			next = n;
			prev = p;
		}
		/**
		 * Crea un nuevo nodo doblemente enlazado con referencias al siguiente nodo y al previo nulas
		 * @param e Elemento del nodo
		 */
		public DNodo(E e) {
			this(e,null, null);
		}
		/**
		 * Establece el elemento pasado por parámetro en el nodo
		 * @param newElem Elemento a establecer en el nodo
		 */
		public void setElement(E newElem) {
			element = newElem;
		}
		/**
		 * Devuelve el nodo siguiente referenciado
		 * @return Nodo siguiente
		 */
		public DNodo<E> getNext() {
			return next;
		}
		/**
		 * Establece la referencia al nodo siguiente pasada por parámetro en el nodo
		 * @param newNext Referencia al nodo siguiente a establecer en el nodo
		 */
		public void setNext(DNodo<E> newNext) {
			next = newNext;
		}
		/**
		 * Devuelve el nodo previo referenciado
		 * @return Nodo previo
		 */
		public DNodo<E> getPrev() {
			return prev;
		}			
		/**
		 * Establece la referencia al nodo previo pasada por parámetro en el nodo
		 * @param newPrev Referencia al nodo previo a establecer en el nodo
		 */
		public void setPrev(DNodo<E> newPrev) {
			prev = newPrev;
		}
		@Override
		public E element() {
			return element;
		}
		
	}
}



