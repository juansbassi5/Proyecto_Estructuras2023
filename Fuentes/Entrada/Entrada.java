package Entrada;
/**
 * 
 * @author Juan_Santi
 * Clase Entrada<K,V> que implementa la interfaz Entry<K,V>
 * @param <K> clave de la entrada
 * @param <V> valor de la entrada
 */
public class Entrada<K, V> implements Entry<K, V> {
	private K key;
	private V value;
	/**
	 * Constructor de la clase Entrada<K,V>
	 * le asigna a los atributos de instancia key y value una clave K y un valor V 
	 * respectivamente pasados como parametros
	 * @param clave
	 * @param valor
	 */
	public Entrada(K clave, V valor) {
		key = clave;
		value = valor;
	}
	@Override
	public void setKey(K clave) {
		key = clave;
	}
	@Override
	public void setValue(V valor) {
		value = valor;
	}
	@Override
	public K getKey() {
		return key;
	}
	@Override
	public V getValue() {
		return value;
	}
	/**
	 * consulta que devuelve una cadena con la clave y el valor de la entrada
	 * @return un String
	 */
	public String toString() {
		return "("+getKey()+","+getValue()+")\n";
	}
}

