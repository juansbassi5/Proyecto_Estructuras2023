package TDADiccionario;

import java.util.Iterator;

import javax.swing.text.Position;

import Exceptions.*;
import TDALista.*;
import Entrada.*;
/**
 * 
 * @author  Santi
 * 
 *	Clase Diccionario que implementa la interfaz Dictionary
 *
 * @param <K> Tipo de dato de las claves de las entradas a almacenar en el diccionario
 *  		  
 * @param <V> Tipo de dato de los valores de las entradas a almacenar en el diccionario 
 *  		  
 */
public class Diccionario<K,V> implements Dictionary<K,V> {
	private int n,N;
	private static final float factor = 0.5f;
	private PositionList<Entrada<K,V>> arregloBucket[]; 
	
	/**
	 * Crea una instancia de Diccionario.
	 */
	public Diccionario() {
		N = 11;
		n = 0;
		arregloBucket = new ListaDE[N];
		for(int i = 0; i < N; i++) {
			arregloBucket[i] = new ListaDE<Entrada<K,V>>();
		}
	}
	@Override
	public int size() {
		return n;
	}
	@Override
	public boolean isEmpty() {
		return n == 0;
	}
	@Override
	public Entrada<K,V> find(K key) throws InvalidKeyException{
		checkKey(key);
		int valorHash = hashThisKey(key);
		Iterator<Entrada<K,V>> it = arregloBucket[valorHash].iterator();		
		boolean encontre = false;
		Entrada<K,V> aux = null;
		
		while(it.hasNext() && !encontre) {
			aux = it.next();
			if(aux.getKey().equals(key)) {
				encontre = true;
			}
		}
		if(!encontre) {
			aux = null;
		}
		return aux;
	}
	@Override
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		checkKey(key);
		PositionList<Entry<K,V>> valoresDeKey = new ListaDE<Entry<K,V>>();
		int valorHash = hashThisKey(key);
		for(Entry<K,V> p: arregloBucket[valorHash]) {
			if(p.getKey().equals(key)) {
				valoresDeKey.addLast(p);
			}
		}
		return valoresDeKey;
	}
	@Override
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		checkKey(key);
		Entrada<K,V> nueva = new Entrada<K,V>(key, value);
		int valorHash = hashThisKey(key);
		arregloBucket[valorHash].addLast(nueva);
		n++;
		if(n/N > factor) {
			reHash();
		}
		return nueva;
	}
	@Override
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{
		if(e == null) {
			throw new InvalidEntryException("Entrada nula");
		}
		int valorHash;
		try {
			valorHash = hashThisKey(e.getKey());		
		}catch(InvalidKeyException p) {
			throw new InvalidEntryException("Clave de entrada inválida");
		}
		Entrada<K,V> aRetornar = null;
		TDALista.Position<Entrada<K,V>> aux = null;
		boolean removido = false;
		

		Iterator<TDALista.Position<Entrada<K,V>>> it = arregloBucket[valorHash].positions().iterator();
		
		while(it.hasNext() && !removido) {
			aux = it.next();
			if(aux.element().getKey().equals(e.getKey()) && aux.element().getValue().equals(e.getValue())) {
				try {
					aRetornar = arregloBucket[valorHash].remove(aux);
					removido = true;
				}catch(InvalidPositionException n) {
					throw new InvalidEntryException("La entrada no pertenece al diccionario");
				}
			}								
		}
		if(!removido) {
			throw new InvalidEntryException("Entrada inválida");
		}	
		n--;
		return aRetornar;
	}
	@Override
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> entradas = new ListaDE<Entry<K,V>>();
		for(int i = 0; i < N; i++) {
			for(TDALista.Position<Entrada<K, V>> p : arregloBucket[i].positions()) {
				entradas.addLast(p.element());				
			}
		}
		return entradas;
	}
	/**
	 * Chequea que la clave pasada por parámetro sea válida.
	 * @param key una clave.
	 * @throws InvalidKeyException si la clave por parámetro es inválida.
	 */
	private void checkKey(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("Clave inválida");
	}
	/**
	 * Devuelve el valor hash.
	 * @param key una clave.
	 * @return int que representa el valor hash.
	 * @throws InvalidKeyException si la clase es inválida.
	 */
	private int hashThisKey(K key) throws InvalidKeyException{
		checkKey(key);
		return Math.abs(key.hashCode() % N);
	}
	/**
	 * Agranda el tamaño del Diccionario
	 * @throws InvalidKeyException
	 */
	private void reHash() throws InvalidKeyException{
		Iterable<Entry<K,V>> entradas = entries();
		N= nextPrimo(N); 
		n=0;
		arregloBucket= new PositionList[N];
		try {
			for(int i=0;i<N;i++){
				arregloBucket[i]=new ListaDE<Entrada<K,V>>();
			}
			for(Entry<K,V> e: entradas){
				this.insert(e.getKey(), e.getValue());
			}
		}catch(InvalidKeyException e) {
			throw new InvalidKeyException("Clave inválida al reescribir en nuevo diccionario");
		}
	}
	/**
	 * Devuelve el siguiente número primo al pasado por parámetro.
	 * @param p numero que pasa por parámetro.
	 * @return el siguiente primo a p.
	 */
	private int nextPrimo(int p) {
		p++;
		for(int i = 2; i < p; i++) {
			if(p % i == 0) {
				p++;
				i = 2;
			}
		}
		return p;
	}
}
