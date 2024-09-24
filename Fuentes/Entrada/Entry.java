package Entrada;
/**
 * 
 * @author Juan_Santi
 * interfaz Entry<K,V> que brinda los metodos getKey(), getValue()
 * setKey() y setValue()
 * @param <K> clave de la entrada
 * @param <V> valor de la entrada
 */
public interface Entry<K, V> {
	/**
	 * 
	 * @return clave K
	 */
	public K getKey();
	/**
	 * 
	 * @return valor V
	 */
	public V getValue();
	/**
	 * le establece a la clave una clave K pasada como parametro
	 * @param clave
	 */
	public void setKey(K clave);
	/**
	 * le establece al valor un valor V pasado como parametro
	 * @param valor
	 */
	public void setValue(V valor);
}
