package Logica;

import Exceptions.*; 

/**
 * 
 * @author Juan_Santi 
 * Clase Tester con metodo main que se encarga de testear mediante casos de pruebas significativos cada uno de los metodos
 * Estos casos de prueba si bien son signicativos, estan pensados para que no lancen ningun tipo de error
 * Caso contrario no podra ejecutarse correctamente el programa
 *
 */
public class Tester {
	public static <E> void main(String[] args) throws EmptyListException, ParametrosInvalidosException, NotaInvalidaException, AlumnoIngresadoException, LegajoInvalidoException, InvalidKeyException, InvalidPositionException, EmptyPriorityQueueException,RangoDeLegajoInvalidoException {
		Programa p1 = new Programa("Algebra");
		
		System.out.println("Esta vacia la lista de p1? "+p1.listaVacia());
		
		
		// metodo agregarAlumno(int lu, int nota)		
		p1.agregarAlumno(133646, 7);
		p1.agregarAlumno(133965, 7);
		p1.agregarAlumno(133212, 5);
		p1.agregarAlumno(132852, 8);
		p1.agregarAlumno(132345, 5);
		p1.agregarAlumno(134218, 9);
		p1.agregarAlumno(133984, 4);
		
		System.out.println("Y ahora? "+p1.listaVacia()+"\n\n");
		
		
		// metodo estaAlumno(int lu)
		System.out.println("Esta alumno 133646? : "+p1.estaAlumno(133646)+"\n\n");
		
		
		// metodo getNota(int lu)
		System.out.println("metodo getNota(Integer 133984) "+p1.getNota(133984)+"\n\n");
		System.out.println("metodo getNota(Integer 133636) "+p1.getNota(133646)+"\n\n");
		System.out.println("metodo getNota(Integer 134218) "+p1.getNota(134218)+"\n\n");
		
		// eliminarAlumno(Integer lu)
		System.out.println("metodo eliminarAlumno(Integer 132852) "+p1.eliminarAlumno(132852)+"\n\n");
		
		// visualizar alumnos agregados con sus legajos y notas
		System.out.println("metodo visualizarAlumnos(): \n"+p1.visualizarAlumnos()+"\n\n");
		
		// calcularPromedio()
		System.out.println("metodo calcularPromedio(): "+p1.calcularPromedio()+"\n\n");
		
		// minimaNota()
		System.out.println("metodo minimaNota(): "+p1.minimaNota()+"\n\n");
		
		// notasAlumnos()
		System.out.println("metodo notasAlumnos()\n"+p1.notasAlumnos()+"\n\n");
		
		// alumnosConMismaNota()
		System.out.println("metodo alumnosConMismaNota(7): \n"+p1.alumnosConMismaNota(7)+"\n\n");
		
		// alumnosAprobadosYDesaprobados
		System.out.println("metodo alumnosAprobadosYDesaprobados(): \n\n"+p1.alumnosAprobadosYDesaprobados()+"\n\n");
	}
}