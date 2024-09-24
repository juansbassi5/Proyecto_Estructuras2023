package Logica;
import TDALista.*; 
import TDADiccionario.*;
import TDAColaCP.*;
import Entrada.*;
import java.util.Iterator;

import Exceptions.*;

/**
 * 
 * @author Juan_Santi
 *	Clase Programa
 */
public class Programa {
	// Atributos de instancia
	protected String materia;
	protected PositionList<Par<Integer,Integer>> informacion;
	
	/**
	 * Constructor de la clase Programa
	 * crea una materia con su respectivo nombre y una lista que almacena pares de enteros
	 * @param nombre
	 */
	public Programa(String nombre) {
		materia = nombre;
		informacion = new ListaDE<Par<Integer,Integer>>();
	}
	/**
	 * Agrega un alumno en la lista, siempre y cuando los parametros esten bien ingresados y el alumno no se encuentre en la misma
	 * @param lu
	 * @param nota
	 * @throws ParametrosInvalidosException si el lu ingresado o la nota ingresada no son del tipo Integer
	 * @throws NotaInvalidaException si la nota ingresada no se encuentra en un rango entre 1 y 10
	 * @throws AlumnoIngresadoException si el alumno con legajo lu ya se encuentra en la lista, no se puede volver a agregar
	 * @throws RangoDeLegajoInvalidoException si el legajo ingresado no es un numero positivo ni se encuentra en el rango de entre 5 y 6 digitos 
	 */
	public void agregarAlumno(Integer lu, Integer nota) throws ParametrosInvalidosException, NotaInvalidaException, AlumnoIngresadoException,RangoDeLegajoInvalidoException{
		if(!(lu instanceof Integer) || !(nota instanceof Integer))
			throw new ParametrosInvalidosException("Los parametros ingresados deben ser un numero");
		if(lu < 10000 || lu > 999999)
			throw new RangoDeLegajoInvalidoException("El legajo debe ser un numero positivo entre 5 y 6 digitos");
		if((nota < 1) || (nota > 10))
			throw new NotaInvalidaException("La nota debe encontrarse en un rango entre 1 y 10");
		
		if(listaVacia()) {
			Par<Integer,Integer> p = new Par<Integer,Integer>(lu,nota);
			informacion.addFirst(p);	
		} else {
			try {
				if(estaAlumno(lu)) 
					throw new AlumnoIngresadoException("El alumno con legajo "+lu+" ya se encuentra ingresado, no se puede volver a agregar");
				Par<Integer,Integer> p = new Par<Integer,Integer>(lu,nota);
				informacion.addLast(p);	
			} 	catch(EmptyListException e) {
					e.getMessage();
				}
			} 	
		}
		
	/**
	 * Consulta si la lista de informacion esta vacia
	 * @return verdadero si informacion esta vacia, falso caso contrario
	 */
	public boolean listaVacia() {
		return informacion.isEmpty();
	}
	/**
	 * nombre de la materia 
	 * @return un String que contiene al nombre de la materia
	 */
	public String getMateria() {
		return materia;
	}
	/**
	 * chequea si se encuentra el alumno en la lista
	 * @param lu
	 * @return esta == true si el alumno se encuentra
	 * 
	 */
	public boolean estaAlumno(Integer lu) throws EmptyListException{
		if(listaVacia())
			throw new EmptyListException("El alumno no esta en la materia");
		boolean esta = false;
		Iterator<Position<Par<Integer,Integer>>> it = informacion.positions().iterator();
		Position<Par<Integer, Integer>> p1;
		while(!esta && it.hasNext()) {
			p1 = it.next();
			esta = p1.element().getKey().equals(lu);
		}
		return esta;
		
	}
	/**
	 * devuelve la nota del alumno con legajo lu
	 * 
	 * @param lu
	 * @return nota del alumno con legajo lu ingresado como parametro
	 * @throws LegajoInvalidoException si el legajo que busca en la lista no se encuentra,
	 *  	   entonces no deberia devolver la nota y lanzar esa excepcion
	 * @throws EmptyListException si la lista a la que le envia el mensaje el metodo encontrarAlumno(lu) se encuentra vacia
	 */
	public Integer getNota(Integer lu) throws LegajoInvalidoException, EmptyListException{
		Integer nota = 0;
		
		Position<Par<Integer, Integer>> p1 = encontrarAlumno(lu);
		
		nota = p1.element().getValue();
		
		if(nota == 0) throw new LegajoInvalidoException("El legajo ingresado no se encuentra");
		return nota; 
	}
	/**
	 * metodo que se encarga de eliminar un alumno de la lista que coincida con el pasado como parametro
	 * 
	 * @param lu
	 * @return un String aclarando que el alumno con determinado lu fue removido de la materia
	 * @throws EmptyListException si la lista informacion esta vacia 
	 * @throws InvalidPositionException si la posicion que se busca al intentar remover el alumno es invalida
	 * @throws ParametrosInvalidosException si el parametro ingresado no es del tipo Integer
	 * @throws RangoDeLegajoInvalidoException si el legajo ingresado no es un numero positivo ni se encuentra en el rango de entre 5 y 6 digitos
	 */
	public String eliminarAlumno(Integer lu) throws EmptyListException, InvalidPositionException, RangoDeLegajoInvalidoException,ParametrosInvalidosException{
		if(!(lu instanceof Integer))
			throw new ParametrosInvalidosException("El parametro ingresado debe ser un numero");
		if(lu < 10000 || lu > 999999)
			throw new RangoDeLegajoInvalidoException("El legajo debe ser un numero positivo entre 5 y 6 digitos");
		try {
			
			informacion.remove(encontrarAlumno(lu));
				
		} catch(EmptyListException e) {
			e.getMessage();
		} catch(InvalidPositionException e) {
			e.getMessage();
		}
		return ("El alumno con lu: "+lu+" fue removido de la materia");
	}
	/**
	 * metodo privado que se encarga de encontrar el alumno con un lu pasado como parametro
	 * se utiliza para simplificar el codigo del metodo principal que es eliminarAlumno(Integer lu)
	 * @param lu
	 * @return la posicion que hace referencia al Par de enteros, es decir la posicion que hace referencia al alumno
	 * @throws EmptyListException si la lista informacion se encuentra vacia
	 */
	private Position<Par<Integer,Integer>> encontrarAlumno(Integer lu) throws EmptyListException{
		if(listaVacia())
			throw new EmptyListException("Lista vacia");
		
		Position<Par<Integer,Integer>> toReturn = null;
		boolean esta = false;
		
		Iterator<Position<Par<Integer,Integer>>> it = informacion.positions().iterator();
		
		Position<Par<Integer, Integer>> p1;
		while(!esta && it.hasNext()) {
			p1 = it.next();
			if(p1.element().getKey().equals(lu)) {
				esta = true;
				toReturn = p1;
			}
		}
		return toReturn;
	}
	
	/**
	 * se puede visualizar todos los alumnos con su lu y nota correspondiente 
	 * @return un String que contiene cada clave (legajo) y valor(nota) del elemento de la posicion de la lista
	 * @throws EmptyListException si la lista informacion esta vacia
	 */
	public String visualizarAlumnos() throws EmptyListException{
		if(listaVacia())
			throw new EmptyListException("La materia no tiene alumnos");
		String s = "--------------------------------\n";
        Iterator<Position<Par<Integer,Integer>>> it = informacion.positions().iterator();
        Position<Par<Integer, Integer>> p1;
        while(it.hasNext()) {
        	p1 =  it.next();
            s+= "alumno: "+p1.element().getKey() +" nota: "+ p1.element().getValue()+"\n";
        }
        return s + "--------------------------------";
    	}
	
	/**
	 * calcula el promedio de todas las notas de la lista 
	 * @return un float que corresponde al promedio 
	 * @throws EmptyListException si la lista informacion esta vacia
	 */
	public float calcularPromedio() throws EmptyListException {
        if(listaVacia())
            throw new EmptyListException("La materia no tiene alumnos");
        float promedio = 0;
        Iterator<Position<Par<Integer,Integer>>> it = informacion.positions().iterator();
        Position<Par<Integer, Integer>> p1;
        while(it.hasNext()) {
            p1 = it.next();
            promedio += p1.element().getValue();
        }
        return (promedio / informacion.size());
    }
	/**
	 * calcula utilizando una Cola con Prioridad con Heap la minima nota
	 * @return un entero que corresponde a la minima nota de toda la lista
	 * @throws EmptyListException si la lista de informacion esta vacia
	 * @throws InvalidKeyException si la clave es invalida cuando se le intente insertar una entrada
	 * @throws EmptyPriorityQueueException si cuando se intente asignarle a la variable local minimaNota el valor con menor prioridad de la cola, esta misma se encuentre vacia 
	 */
	public int minimaNota() throws EmptyListException,InvalidKeyException, EmptyPriorityQueueException{
		int minimaNota = 0;
		if(listaVacia())
            throw new EmptyListException("La materia no tiene alumnos");
		
		PriorityQueue<Integer,Integer> cola = new ColaCPconHeap<Integer,Integer>(new TDAColaCP.Comparator<Integer>());
		Iterable<Position<Par<Integer,Integer>>> it = informacion.positions();
		for(Position<Par<Integer,Integer>> j : it) {
			try {
				cola.insert(j.element().getKey(), j.element().getValue());
			} catch(InvalidKeyException e) {
				e.getMessage();
			}			
		}		
		try {
			minimaNota = cola.min().getValue();
		} catch(EmptyPriorityQueueException s) {
			s.getMessage();
		}
		
		return minimaNota;
	}
	
	/**
	 * metodo que se encarga de ordenar las notas de los alumnos de mayor a menor
	 * @return un String con cada una de las notas de los alumnos ordenadas de mayor a menor
	 * @throws InvalidKeyException si la clave es invalida cuando se le intente insertar una entrada
	 * @throws EmptyPriorityQueueException si la cola esta vacia cuando se le intente remover el elemento con menor prioridad
	 * @throws EmptyListException si la lista esta vacia cuando se le intente concatenar al String local s el ultimo par de la lista para despues removerlo 
	 * @throws InvalidPositionException si la posicion del ultimo par a eliminar de la lista es invalida
	 */
	public String notasAlumnos() throws InvalidKeyException, EmptyPriorityQueueException, EmptyListException, InvalidPositionException{
		String s = "--------------------------------\n";
		
		PriorityQueue<Integer,Integer> cola = new ColaCPconHeap<Integer,Integer>(new TDAColaCP.Comparator<Integer>());
		Iterable<Position<Par<Integer,Integer>>> it = informacion.positions();
		for(Position<Par<Integer,Integer>> j : it) {
			try {
				cola.insert(j.element().getKey(), j.element().getValue());
			} catch(InvalidKeyException e) {
				e.getMessage();
			}			
		}		
		ListaDE<Par<Integer,Integer>> lista = new ListaDE<Par<Integer,Integer>>();
		try {			
			while(!cola.isEmpty()) {
				Par<Integer,Integer> p = new Par<Integer,Integer>(cola.min().getKey(),cola.min().getValue());
				lista.addLast(p);
				cola.removeMin();
			}
		} catch(EmptyPriorityQueueException e) {
			e.getMessage();
		}
		
		try {
			while(!lista.isEmpty()) {
				s+= "alumno "+lista.last().element().getKey()+" nota "+lista.last().element().getValue()+"\n";
				lista.remove(lista.last());
			}
		} catch (EmptyListException e) {
			e.getMessage();
		} catch (InvalidPositionException i) {
			i.getMessage();
		}
		
        return s + "--------------------------------";
	}
	/**
	 * metodo que se encarga de almacenar en un diccionario cada una de las notas que coinciden con la ingresada como parametro y
	 * devolverlas gracias al metodo entries() que contiene a cada par insertado en el diccionario
	 * @param nota
	 * @return un String que contiene a cada alumno con legajo LU y la misma nota que la ingresada como parametro
	 * @throws ParametrosInvalidosException  si la nota ingresada no es del tipo Integer
	 * @throws NotaInvalidaException si la nota ingresada no se encuentra en un rango entre 1 y 10
	 */
	public String alumnosConMismaNota(Integer nota) throws ParametrosInvalidosException, NotaInvalidaException{
		if(!(nota instanceof Integer))
			throw new ParametrosInvalidosException("El parametro ingresado debe ser un numero");
		if((nota < 1) || (nota > 10))
			throw new NotaInvalidaException("La nota debe encontrarse en un rango entre 1 y 10");
		Dictionary<Integer,Integer> diccionario = new Diccionario<Integer,Integer>();
		Iterable<Position<Par<Integer,Integer>>> p = informacion.positions();
		
		String s = "--------------------------------\n";
		
		for(Position<Par<Integer,Integer>> j : p) {
			try {
				if(j.element().getValue().equals(nota))
					diccionario.insert(j.element().getKey(), j.element().getValue());
			} catch(InvalidKeyException e) {
				e.getMessage();
			}			
		}
		for(Entry<Integer,Integer> j : diccionario.entries()) {
			s+="Alumno con LU: "+j.getKey()+" y nota: "+j.getValue()+"\n";
		}
		
		 return s + "--------------------------------";
	}
	/**
	 * metodo que se encarga utilizando dos diccionarios de insertar en en el primero los que aprobaron
	 * y en el segundo los que desaprobaron
	 * @return un String que contiene por una parte los alumnos aprobados y debajo de los aprobados, los alumnos desaprobados
	 */
	public String alumnosAprobadosYDesaprobados(){
		Dictionary<Integer,Integer> diccionarioA = new Diccionario<Integer,Integer>();
		Dictionary<Integer,Integer> diccionarioD = new Diccionario<Integer,Integer>();
		Iterable<Position<Par<Integer,Integer>>> p = informacion.positions();
		
		String a = "-----------Aprobados-----------\n\n";
		String d = "\n-----------Desaprobados-----------\n\n";
		
		for(Position<Par<Integer,Integer>> j : p) {
			try {
				if(j.element().getValue() >= 6) {
					diccionarioA.insert(j.element().getKey(), j.element().getValue());
				} else {
					diccionarioD.insert(j.element().getKey(), j.element().getValue());
				}					
			} catch(InvalidKeyException e) {
				e.getMessage();
			}			
		}
		for(Entry<Integer,Integer> j : diccionarioA.entries()) {
			a+="Alumno con LU: "+j.getKey()+" y nota: "+j.getValue()+"\n";
		}
		for(Entry<Integer,Integer> j : diccionarioD.entries()) {
			d+="Alumno con LU: "+j.getKey()+" y nota: "+j.getValue()+"\n";
		}
		
		return a + d + "\n";
	}

	
	
}
