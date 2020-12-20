package ExamenJava;

import java.util.ArrayList;

public class profesor {

	private String rutProfesor;
	private String Nombre;
	private String Apellido;
	
	private ArrayList<estudiante> estudiantes;
	
	
	public profesor(String rutProfesor) {
		this.rutProfesor = rutProfesor;	
	}
	
	
	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getApellido() {
		return Apellido;
	}


	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	
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
