package Logica;
/**
 * 
 * @author Juan_Santi
 *
 * @param <K> que corresponde a la clave
 * @param <V> que corresponde al valor
 */
public class Par <K,V>{ 
	protected K key;
	protected V value;
	
	/**
	 * Constructor de la clase Par
	 * @param key
	 * @param value
	 */
	public Par(K key, V value) {
		this.key = key;
		this.value = value;
	}
	/**
	 * Segundo constructor de la clase Par
	 */
	public Par() {
		this(null, null);
	}
	/**
	 * 
	 * @return K que corresponde a la clave
	 */
	public K getKey() {
		return key;
	}
	/**
	 * 
	 * @return V que corresponde al valor
	 */
	public V getValue() {
		return value;
	}
	/**
	 * devuelve una cadena con la clave y el valor 
	 * @return un String
	 */
	public String toString() {
		return "[" + getKey() + "," + getValue() + "]";
	}
}
