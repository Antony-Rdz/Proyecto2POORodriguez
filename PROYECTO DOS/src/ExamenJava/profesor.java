package ExamenJava;

import java.util.*;

public class profesor {

	private String rutProfesor;

	public profesor(String rutProfesor) {
		this.rutProfesor = rutProfesor;	
	}
	//private static ArrayList<String> profesores = new ArrayList<String>();



	/*public static ArrayList<String> getProfesores() {
		return profesores;
	}

	public static void setProfesores(ArrayList<String> profesores) {
		profesor.profesores = profesores;
	}*/

	/*public static void menuProfesor() {
		int eleccion = 0;
		do {
			System.out.print("Que desea hacer?\n 1)Iniciar Sesion\n2)Volver\nELIJA: ");
			eleccion = Integer.parseInt(complementos.leerDeTeclado());
			while (eleccion > 2) {
				System.err.println("Opcion fuera de rango\n");
				System.out.print("Que desea hacer?\n 1)Ingresar\n2)Volver\nELIJA: ");
				eleccion = Integer.parseInt(complementos.leerDeTeclado());
			}
			switch (eleccion) {
			case 1: {
				System.out.println("Ingrese rut de profesor");
				String rut = complementos.leerDeTeclado();
				if (ingresoProfesor(rut)) {
					System.out.println("Bienvenido\n");

				} else {
					System.err.println("RUT INCORRECTO");
				}
				break;
			}
			}
		} while (eleccion != 2);

	}*/
	
	public String getRutProfesor() {
		return rutProfesor;
	}

	public void setRutProfesor(String rutProfesor) {
		this.rutProfesor = rutProfesor;
	}
	
	public void mostrarResultados() {
		for (estudiante std : Exam.datosEstudiantes) {
			System.out.println(std);
		}
	}

	public String toString() {
		return "Rut: " + rutProfesor;
	}

}
