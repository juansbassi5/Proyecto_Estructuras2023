package TDAColaCP;

import java.util.Comparator;

import Entrada.*;
import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;

/**
 * Clase ColaCPconHeap que implementa la interfaz PriorityQueue
 * 
 * @param <K> Tipo de dato de las claves de las entradas a almacenar en la cola
 *  		  con prioridad.
 * @param <V> Tipo de dato de los valores de las entradas a almacenar en la cola 
 *  		  con prioridad
 */
public class ColaCPconHeap<K, V extends Comparable<V>> implements PriorityQueue<K,V>{
	protected Entrada<K,V>[] elementos;
	protected int size;
	protected Comparator<V> comp;
	
	/**
	 * Crea una cola con prioridad con heap con un comparador y tamaño inicial
	 * parametrizados
	 * 
	 * @param comparador Comparador de claves de las entradas
	 * @param maxElems Tamaño inicial de la cola con prioridad
	 */
	
	public ColaCPconHeap(Comparator<V> comparador, int maxElems) {
		size = 0;
		elementos = (Entrada<K,V>[]) new Entrada[maxElems];
		comp = comparador;
	}
	/**
	 * Crea una cola con prioridad con heap con un comparador parametrizado y un
	 * tamaño inicial predeterminado
	 * 
	 * @param comparador Comparador de claves de las entradas
	 */
	public ColaCPconHeap(Comparator<V> comparador) {
		size = 0;
		elementos = (Entrada<K,V>[]) new Entrada[20];
		comp = comparador;
	}
	
	/**
	 * Retorna el tamaño de la cola
	 * @return int size
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Retorna verdadero si la cola esta vacia 
	 * y falso en caso contrario
	 * @return boolean (size == 0)
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("Esta vacia");

		return elementos[1];
	}
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("La clave es nula");

		Entrada<K, V> entrada = new Entrada<K, V>(key, value);

		if (size == elementos.length - 1)
			resize();

		elementos[++size] = entrada;
		int i = size;
		boolean seguir = true;

		while (i > 1 && seguir) {
			Entrada<K, V> actual = elementos[i];
			Entrada<K, V> padre = elementos[i / 2];

			if (comp.compare(actual.getValue(), padre.getValue()) < 0) {
				Entrada<K, V> aux = elementos[i];
				elementos[i] = elementos[i / 2];
				elementos[i / 2] = aux;
				i /= 2;
			} else
				seguir = false;

		}
		return entrada;
	}
	/**
	 * Agranda la cola con prioridad con heap actual para poder insertar más
	 * elementos
	 */
	private void resize() {
		Entrada<K, V>[] viejo = elementos;
		elementos = (Entrada<K, V>[]) new Entrada[viejo.length * 2];

		for (int i = 0; i < viejo.length; i++) {
			elementos[i] = viejo[i];
		}
	}
	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K, V> ent;
		int i, hi, hd, hMin;
		boolean cortar;

		if (size == 0)
			throw new EmptyPriorityQueueException("Cola vacia");

		ent = min();

		if (size == 1) {
			elementos[1] = null;
			size = 0;
		} else {
			elementos[1] = elementos[size];
			elementos[size] = null;
			size--;
			i = 1;
			cortar = false;

			while (!cortar) {
				hi = i * 2;
				hd = i * 2 + 1;

				if (!(hi < elementos.length) || !(hi <= size))
					cortar = true;
				else {
					if (hd < elementos.length && hd <= size)
						if (comp.compare(elementos[hi].getValue(), elementos[hd].getValue()) < 0)
							hMin = hi;
						else
							hMin = hd;
					else
						hMin = hi;

					if (comp.compare(elementos[i].getValue(), elementos[hMin].getValue()) > 0) {
						Entrada<K, V> aux = elementos[i];
						elementos[i] = elementos[hMin];
						elementos[hMin] = aux;
						i = hMin;
					} else
						cortar = true;
				}
			}
		}

		return ent;
	}
}
